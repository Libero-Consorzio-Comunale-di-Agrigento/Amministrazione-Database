CREATE OR REPLACE package body spid_handler is
    /******************************************************************************
     NOME: AD4.spid_handler
     DESCRIZIONE:   Package che contiene i metodi per la gestione dell'identità SPID
     ARGOMENTI:
     RITORNA:
     ECCEZIONI:
     ANNOTAZIONI:
     REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ --------------------------------------------------------
     000  24/11/2017 AD     Prima Emissione
     001  05/02/2018 AD     Aggiunta gestione delle utenze con nominativo utente uguale
                            al codice fiscale e utilizzo della verifica su SO4 per
                            discriminare utenti interni da quelli esterni
     002  08/02/2018 AD     Aggiunta gestione del profilo in fase di conferma dei dati
                            del soggetto per garantire l'aggiornamento di eventuali
                            dati memorizzati su AS4 e da allineare verso il portale
     003  20/02/2018 AD     Corretto utilizzo di so4_so4_util.is_soggetto_componente
                            con soggetto.is_soggetto_componente
     004  28/02/2018 AD     Aggiunto parametro telefono residenza in conferma dei dati
                            anagrafici
     005  05/04/2018 AD     Aggiunta generazione casuale password per nuovo utente
                            gestendo lunghezza 20 caratteri, uso dei numeri e non uso
                            dei caratteri speciali, per salvaguardare eventuali
                            caratteristiche di accesso definite
    006  25/06/2019  AD     Aggiunta gestione del Profile trasversale PORTAL per la creazione dell'utenza
                            ed abilitazione a tutti gli applicativi censiti come integrati SPID
    007   27/06/2019 AD     Corretta determinazione del soggetto in caso di più soggetti presenti in
                            anagrafica con stesso codice fiscale
    008  12/07/2019  AD     Gestito parametro spid_id in caso di integrazione con FedERa
    009  19/07/2019  AD     Corretta determinazione del codice del comune in caso di comuni
                            soppressi che non hanno cambiato denominazione e che sono rimasti nella stessa
                            provincia
    010 27/02/2020  SN      Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
                                       (modificato quanto introdotto precedentemente con indicazione md5)
    011 25/05/2020  SN    Calcolo utente prima di sistemare la password
    012 11/06/2020  SN    Cercare di trovare comune e provincia con le info fornite e al limite
                                       non inserire il dato Bug #42973
    013 24/06/2020  AD    Introdotta gestione dello stato utente in ricerca dell'abbinamento utente/soggetto
                                      con dati coerenti
    014 29/10/2020  AD     Corretta gestione della creazione del soggetto via package per evitare di
                                        riciclare soggetti già presenti a parità di codice fiscale
    015  17/12/2020  AD    Corretto controllo per inibire l'aggiornamento in caso di mancato recupero
                                        dei dati relativi a comune e provincia di residenza     Bug #46826
    016  17/12/2020  SN    Utilizzo package utenti_salt presente dalla 4.5.18
    017  04/02/2021  SN     Chiamare la procedure corretta Bug #46147
    018  12/02/2021  SN     Allargare il campo per la gestione della password con i nuovi algoritmi Bug #48221
    019  15/03/2021  AD     Integrata modifica 013 nel caso di utente e soggetto compatibile per CF e nominativo #48963
    020  19/04/2021  AD     Aggiunta gestione degli errori in caso di problemi in chiamata delle funzioni
                                        di provisioning #49682
    021  22/03/2022   AD   Aggiunta traccia dell'accesso #55958
    ******************************************************************************/
    s_revisione_body AFC.t_revision := '021';
    function versione return AFC.t_revision is
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce versione e revisione di distribuzione del package.
 RITORNA:     stringa VARCHAR2 contenente versione e revisione.
 NOTE:        Primo numero  : versione compatibilita del Package.
              Secondo numero: revisione del Package specification.
              Terzo numero  : revisione del Package body.
******************************************************************************/
    begin
        return AFC.version( s_revisione, s_revisione_body );
    end versione; -- Servererror_Handler.versione;

    procedure registra_accesso_portale(P_PROFILO IN VARCHAR2, P_UTENTE IN VARCHAR2) is
        d_errm  varchar2(4000);
    begin
        IF P_PROFILO = 'PORTAL' THEN -- ACCESSO PORTALE SERVIZI GENERICO
            FOR SEL_MODULI_PORTALE IN ( select SUBSTR(CHIAVE,INSTR(CHIAVE,'/',-1,1)+1) istanza , D.MODULO
                                        from registro , UTENTI U, DIRITTI_ACCESSO D
                                        where chiave like 'PRODUCTS/SPIDHANDLER/%'
                                          AND stringa = 'ProcedureName'
                                          AND VALORE IS NOT NULL
                                          AND U.UTENTE = D.UTENTE
                                          AND D.ISTANZA = SUBSTR(CHIAVE,INSTR(CHIAVE,'/',-1,1)+1)
                                          and d.utente = p_utente
                ) loop
                    begin
                        ACCESSO.REGISTRA_ACCESSO(USERENV('SESSIONID'),'ON',sel_moduli_portale.istanza,sel_moduli_portale.modulo,P_UTENTE,'Accesso servizio '||sel_moduli_portale.modulo,NULL);
                    exception when others then
                        d_errm := sqlerrm;
                        insert into key_error_log (error_session, error_date, error_text, error_user, error_usertext, error_type) values
                            ( userenv('sessionid'),sysdate,'Errore in registrazione accesso  '||p_profilo,'AD4', d_errm,'E');
                    end;
                end loop;
        ELSE
            FOR SEL_MODULI_PORTALE IN ( select  d.istanza , D.MODULO
                                        from UTENTI U, DIRITTI_ACCESSO D
                                        where  U.UTENTE = D.UTENTE
                                          AND D.ISTANZA = p_profilo
                                          and d.utente = p_utente
                ) loop
                    begin
                        ACCESSO.REGISTRA_ACCESSO(USERENV('SESSIONID'),'ON',sel_moduli_portale.istanza,sel_moduli_portale.modulo,P_UTENTE,'Accesso servizio '||sel_moduli_portale.modulo,NULL);
                    exception when others then
                        d_errm := sqlerrm;
                        insert into key_error_log (error_session, error_date, error_text, error_user, error_usertext, error_type) values
                            ( userenv('sessionid'),sysdate,'Errore in registrazione accesso  '||p_profilo,'AD4', d_errm,'E');
                    end;
                end loop;
        END IF;
    end;

    procedure set_profilo(p_nominativo_utente in varchar2, p_profilo in varchar2) is
        d_procedure_name    varchar2(4000);
        d_statement         varchar2(4000);
    begin
        IF UPPER(P_PROFILO) = 'PORTAL' THEN
            FOR SEL_MODULI_SPID IN (
                select Valore
                from registro
                where chiave like 'PRODUCTS/SPIDHANDLER/%'
                  AND stringa = 'ProcedureName'
                  and instr(chiave,'PORTAL') = 0
                  AND VALORE IS NOT NULL
                ) LOOP
                    BEGIN
                        d_statement := 'begin '||SEL_MODULI_SPID.valore||'(:p_nominativo_utente);  end;'; -- rev. 17
                        execute immediate d_statement
                            using in  p_nominativo_utente;
                    EXCEPTION WHEN OTHERS THEN
                        /* introdotto tracciamento errori  #49682 */
                        declare
                            d_errm  varchar2(4000) := sqlerrm;
                        begin
                            insert into key_error_log (error_session, error_date, error_text, error_user, error_usertext, error_type) values
                                ( userenv('sessionid'),sysdate,'Errore in chiamata a funzione di provisioning '||SEL_MODULI_SPID.valore,'AD4', d_errm,'E');
                        exception when others then
                            null;
                        end;
                    /* fine #49682 */
                    END;
                END LOOP;
        ELSE
            d_procedure_name := REGISTRO_UTILITY.LEGGI_STRINGA('PRODUCTS/SPIDHANDLER/'||UPPER(P_PROFILO),'ProcedureName',0);
            if d_procedure_name is not null then
                d_statement := 'begin '||d_procedure_name||'(:p_nominativo_utente);  end;';
                execute immediate d_statement
                    using in  p_nominativo_utente;
            end if;
        END IF;
    end;
    function spid2ad4(p_codice_fiscale in varchar2     -- codice fiscale del soggetto
    ,p_cognome in varchar2            -- cognome del soggetto
    ,p_nome in varchar2               -- nome del soggetto
    ,p_indirizzo_mail in varchar2     -- indirizzo mail del soggetto
    ,p_spid_id in varchar2            -- spid_id valorizzato solo se accesso mediante spid
    ,p_profilo in varchar2            -- utente di gruppo per attribuzione profilo
    ,p_new_utente out number          -- parametro numerico per identificare se è stato creato un nuobo utente (0/1)
    ) return number is
        d_soggetto          number;
        d_utente            varchar2(8);
        D_NOMINATIVO        VARCHAR2(80);
        d_id_utente         number;
        D_DATA_NAS          varchar2(10);
        D_COMUNE_NAS        NUMBER;
        D_PROVINCIA_NAS     NUMBER;
        D_SESSO             VARCHAR2(1);
        d_pwd               utenti.password%TYPE; -- rev. 018
        D_CHECK_UTENTE_SOGG BOOLEAN := FALSE;
        CONFERMA_DATI       BOOLEAN := FALSE;
        d_crypt_algoritm    utenti_salt.algoritmo%TYPE;
        d_stato_utente      utenti.stato%type;
    begin
        IF NVL(REGISTRO_UTILITY.LEGGI_STRINGA('PRODUCTS/SPIDHANDLER','ConfermaDatiObbligatoria',0),'NO') = 'SI' THEN
            CONFERMA_DATI := TRUE;
        ELSE
            CONFERMA_DATI := FALSE;
        END IF;
        BEGIN
            SELECT U.UTENTE, S.SOGGETTO, stato
            INTO D_UTENTE, D_SOGGETTO, d_stato_utente
            FROM UTENTI U, SOGGETTI S, UTENTI_SOGGETTI US
            WHERE U.NOMINATIVO   = upper(P_CODICE_FISCALE)
              AND S.CODICE_FISCALE = upper(P_CODICE_FISCALE)
              AND US.UTENTE = U.UTENTE
              AND US.SOGGETTO = S.SOGGETTO
            ;
            --        and u.stato='U';    -- cerco tra i soggetti attivi   -- rimosso controllo in quanto l'utente potrebbe essere stato sospeso o revocata dai job di disabilitazione
            D_CHECK_UTENTE_SOGG := TRUE;
        EXCEPTION WHEN NO_DATA_FOUND THEN
            D_CHECK_UTENTE_SOGG := FALSE;
        END;
        IF D_CHECK_UTENTE_SOGG THEN -- HO TROVATO UN UTENTE CON NOMINATIVO UGUALE AL CF ABBINATO A SOGGETTO CON CODICE FISCALE CONGRUENTE
        -- rev. 019 inizio
            if d_stato_utente != 'U' then
                update utenti set stato = 'U', numero_tentativi = 0,  UTENTE_AGGIORNAMENTO = D_UTENTE, DATA_AGGIORNAMENTO = SYSDATE  where utente = d_utente;
            end if;
            -- rev. 019 fine
            set_profilo(utente.get_nominativo(d_utente), p_profilo);
            IF CONFERMA_DATI THEN -- FORZO LA PAGINA DI CONFERMA
                P_NEW_UTENTE := 1;
            ELSE
                p_new_utente := 0;  -- utente già esistente
            END IF;
            REGISTRA_ACCESSO_PORTALE(P_PROFILO,D_UTENTE);
            return d_soggetto;  -- ritorno ni del soggetto
        else
            if instr(p_spid_id,'PasswordProtectedTransport') = 0  -- SPID
            -- 12/07/2019 FedERA passa lo spid_id con PasswordProtectedTransport quindi devo testare altre informazioni
                or (instr(p_spid_id,'PasswordProtectedTransport') != 0
                    and p_nome is not null
                    and p_cognome is not null
                    and NVL(REGISTRO_UTILITY.LEGGI_STRINGA('PRODUCTS/SPIDHANDLER','IntegrazioneFedera',0),'NO') = 'SI'
                   )
                -- 12/07/2019
            then
                begin
                    select soggetto
                    into d_soggetto
                    from soggetti
                    where codice_fiscale = p_codice_fiscale
                      -- 05/02/2018 escludo i soggetti per i quali esiste
                      AND soggetto.is_soggetto_componente(SOGGETTO) = 0
                    --
                    ;
                exception when no_data_found then
                    null;
                when too_many_rows then -- ne ho trovati più di uno, prendo quello che è stato aggiornato per ultimo
--                27/06/2019 gestione di più soggetti con stesso codice fiscale
                    select soggetto
                    into d_soggetto
                    from soggetti s1
                    where codice_fiscale = p_codice_fiscale
                      -- 05/02/2018 escludo i soggetti per i quali esiste
                      AND soggetto.is_soggetto_componente(SOGGETTO) = 0
                      and data_aggiornamento = ( select max(data_aggiornamento) from soggetti s2 where s1.codice_fiscale = s2.codice_fiscale and soggetto.is_soggetto_componente(s2.SOGGETTO) = 0);
--
                end;
                if d_soggetto is not null then      -- soggetto già presente in anagrafica cerco se ha un utente con nominativo uguale al CF
                    begin
                        select uten.utente, UTEN.NOMINATIVO, uten.stato
                        into d_utente, D_NOMINATIVO,d_stato_utente
                        from utenti_soggetti utso, utenti uten
                        where soggetto = d_soggetto
                          and utso.utente = uten.utente
                        --   and uten.nominativo = p_codice_fiscale
                        ;
                    exception when no_data_found then
                        d_utente := null;
                    end;
                    if d_utente is not null then   -- se ho trovato utente
                        IF D_NOMINATIVO !=  p_codice_fiscale then  -- se ha nominativo diverso lo aggiorno
                            UPDATE UTENTI SET NOMINATIVO = P_CODICE_FISCALE,  UTENTE_AGGIORNAMENTO = D_UTENTE, DATA_AGGIORNAMENTO = SYSDATE WHERE UTENTE = D_UTENTE;
                        end if;
                        -- AD 23/06/2020
                        -- AGGIUNTA GESTIONE SU UTENZA CON CREDENZIALI E ABBINAMENTO A SOGGETTO CORRETTA, MA IN STATO NON COMPATIBILE CON L'ACCESSO
                        if d_stato_utente != 'U' then -- utente soispeso o revocato
                            delete utenti_gruppo where utente = d_utente; -- rimuovo diritti che l'utente ha per appartenenza a gruppi di AD4
                            delete diritti_accesso where utente = d_utente; -- rimuovo diritti che l'utente per assegnazione diretta
                            update utenti set stato = 'U', numero_tentativi = 0,  UTENTE_AGGIORNAMENTO = D_UTENTE, DATA_AGGIORNAMENTO = SYSDATE  where utente = d_utente;
                            begin
                                D_DATA_NAS      := CODICE_FISCALE.GET_DATA_NAS(P_CODICE_FISCALE);
                                D_COMUNE_NAS    := CODICE_FISCALE.GET_COMUNE_NAS(P_CODICE_FISCALE);
                                D_PROVINCIA_NAS := CODICE_FISCALE.GET_PROVINCIA_NAS(P_CODICE_FISCALE);
                                D_SESSO         := CODICE_FISCALE.GET_SESSO(P_CODICE_FISCALE);
                                si4.utente := nvl(UTENTE.GET_UTENTE(p_soggetto => d_soggetto),'AD4');  --
                                soggetto.initialize(d_soggetto);
                                soggetto.set_nome(nvl(P_cognome,substr(p_codice_fiscale,1,3)) || '  ' || nvl(P_nome,substr(p_codice_fiscale,4,3)));
                                soggetto.set_codice_fiscale(P_codice_fiscale);
                                soggetto.set_comune_nas(D_COMUNE_NAS);
                                soggetto.set_provincia_nas(D_PROVINCIA_NAS );
                                soggetto.set_data_nascita(D_DATA_NAS);
                                soggetto.set_sesso(D_SESSO);
                                soggetto.set_indirizzo_web(p_indirizzo_mail);
                                soggetto.update_soggetto(d_soggetto);
                            exception when others then
                                null;
                            end;
                        end if;
                        /* chiamata dinamica? */
                        set_profilo(p_codice_fiscale
                            --utente.get_nominativo(d_utente)
                            , p_profilo);
                        /*------*/
                        IF CONFERMA_DATI THEN -- FORZO LA PAGINA DI CONFERMA
                            P_NEW_UTENTE := 1 ;
                        ELSE
                            p_new_utente := 0;  -- utente già esistente
                        END IF;
                        REGISTRA_ACCESSO_PORTALE(P_PROFILO,D_UTENTE);
                        return d_soggetto;  -- ritorno ni del soggetto

                    else -- esiste il soggetto ma non utente o legame utente/soggetto per utente con nominativo coerente
                        BEGIN
                            SELECT UTENTE
                            INTO D_UTENTE
                            FROM UTENTI
                            WHERE NOMINATIVO = P_CODICE_FISCALE;
                        EXCEPTION WHEN NO_DATA_FOUND THEN
                            D_UTENTE := NULL;
                        END;
                        IF D_UTENTE IS NULL THEN   --non trovato utente con nominativo compatibile,devo crearlo
                            d_pwd := crypt.genera_password(20,0,1); -- genero password senza caratteri speciali ma con numeri
                            d_utente:= utente.determina_utente_libero(upper(p_codice_fiscale)); --rev.11
                            -- rev. 10 inizio
                            UTENTI_SALT_PKG.sistema_password(d_utente,d_pwd,d_crypt_algoritm);
                            CRYPT.CRYPT_PASSWORD(d_pwd,d_crypt_algoritm);
                            -- rev. 10 fine
                            UTENTE.INS( P_NOMINATIVO => p_codice_fiscale
                                , P_UTENTE => d_utente
                                , P_PASSWORD => D_PWD
                                , p_pwd_da_modificare   => 'NO'
                                , p_utente_aggiornamento => 'AD4'
                                , P_ID_UTENTE => d_id_utente);
                        end if;
                        INSERT INTO UTENTI_SOGGETTI (UTENTE,SOGGETTO) VALUES (D_UTENTE,D_SOGGETTO);     -- inserimento legame utente/soggetto
                        /* chiamata dinamica? */
                        set_profilo(utente.get_nominativo(d_utente), p_profilo);
                        /*------*/
                        p_new_utente := 1;
                        REGISTRA_ACCESSO_PORTALE(P_PROFILO,D_UTENTE);
                        return d_soggetto;
                    end if;
                else
                    /* creazione del soggetto */
                    D_DATA_NAS      := CODICE_FISCALE.GET_DATA_NAS(P_CODICE_FISCALE);
                    D_COMUNE_NAS    := CODICE_FISCALE.GET_COMUNE_NAS(P_CODICE_FISCALE);
                    D_PROVINCIA_NAS := CODICE_FISCALE.GET_PROVINCIA_NAS(P_CODICE_FISCALE);
                    D_SESSO         := CODICE_FISCALE.GET_SESSO(P_CODICE_FISCALE);
                    utente.s_registrazione_da_web_on := 1;
                    soggetto.init;
                    soggetto.set_nome(nvl(P_cognome,substr(p_codice_fiscale,1,3)) || '  ' || nvl(P_nome,substr(p_codice_fiscale,4,3)));
                    soggetto.set_codice_fiscale(P_codice_fiscale);
                    soggetto.set_comune_nas(D_COMUNE_NAS);
                    soggetto.set_provincia_nas(D_PROVINCIA_NAS );
                    soggetto.set_data_nascita(D_DATA_NAS);
                    soggetto.set_sesso(D_SESSO);
                    soggetto.set_indirizzo_web(p_indirizzo_mail);
                    soggetto.update_soggetto(d_soggetto);
                    utente.s_registrazione_da_web_on := 0;
                    BEGIN         -- cerco se esiste già un utente con nominativo uguale al codice fiscale
                    SELECT UTENTE
                    INTO D_UTENTE
                    FROM UTENTI
                    WHERE NOMINATIVO = P_CODICE_FISCALE;
                    EXCEPTION WHEN NO_DATA_FOUND THEN
                        D_UTENTE := NULL;
                    END;
                    IF D_UTENTE IS NULL THEN   --non trovato utente con nominativo uguale, devo crearlo
                        d_pwd := crypt.genera_password (20,0,1);
                        d_utente:= utente.determina_utente_libero(upper(p_codice_fiscale)); --rev.11
                        -- rev. 10 inizio
                        UTENTI_SALT_PKG.sistema_password(d_utente,d_pwd,d_crypt_algoritm);
                        CRYPT.CRYPT_PASSWORD(d_pwd,d_crypt_algoritm);
                        -- rev. 10 fine
                        UTENTE.INS( P_NOMINATIVO => p_codice_fiscale
                            , P_UTENTE => d_utente
                            , P_PASSWORD => D_PWD
                            , p_pwd_da_modificare   => 'NO'
                            , p_utente_aggiornamento => 'AD4'
                            , P_ID_UTENTE => d_id_utente);
                    end if;
                    INSERT INTO UTENTI_SOGGETTI (UTENTE,SOGGETTO) VALUES (D_UTENTE,D_SOGGETTO);     -- inserimento legame utente/soggetto
                    /* chiamata dinamica? */
                    set_profilo(utente.get_nominativo(d_utente), p_profilo);
                    /*------*/
                    p_new_utente := 1;
                    REGISTRA_ACCESSO_PORTALE(P_PROFILO,D_UTENTE);
                    return d_soggetto;
                end if;
            else            -- utente interno
                select utente
                into d_utente
                from utenti
                where nominativo = upper(p_codice_fiscale);
                REGISTRA_ACCESSO_PORTALE(P_PROFILO,D_UTENTE);
                p_new_utente := 0;  -- utente già esistente
                return null;
            end if;
        end if;
        return null;
    end;

    procedure conferma_dati_soggetto( p_ni in number
    ,p_cognome in varchar2            -- cognome del soggetto
    ,p_nome in varchar2               -- nome del soggetto
    ,p_indirizzo_mail in varchar2
    ,p_indirizzo_res in varchar2
    ,p_cap_res in varchar2
    ,p_tel_res in varchar2
    ,p_denominazione_comune_res in varchar2
    ,p_denominazione_provincia_res in varchar2
    ,p_profilo in varchar2 default null -- ripasso il profilo per gestire uno lancio della procedure
    )
        is
        d_soggetto          number := p_ni;
        d_comune_res        number(10);
        d_provincia_res     number(10);
        d_utente            varchar2(8);
    begin
        begin
            select provincia
            into d_provincia_res
            from province
            where denominazione = upper(p_denominazione_provincia_res);
        EXCEPTION WHEN NO_DATA_FOUND THEN
            begin -- cerco ipotizzando che l'utente abbia scritto la sigla
            select provincia
            into d_provincia_res
            from province
            where sigla = upper(p_denominazione_provincia_res);
            exception when no_data_found thEn
                D_PROVINCIA_RES := NULL;
            end;
        END;
        BEGIN
            select COMUNE, decode(d_provincia_res,null,provincia_stato,d_provincia_res)
            into d_comune_res, d_provincia_res
            from COMUNI
            where denominazione = upper(p_denominazione_comune_res)
              AND nvl(d_provincia_res,provincia_stato) = provincia_stato
              AND data_soppressione is null AND PROVINCIA_FUSIONE IS NULL
            ;
        exception when no_data_found then
            d_comune_res := null;
        when too_many_rows then -- comuni con stessa denominazione se non ho la provincia
            d_comune_res := null;
        end;
        si4.utente := nvl(UTENTE.GET_UTENTE(p_soggetto => d_soggetto),'AD4');  --
        SOGGETTO.INITIALIZE(d_soggetto);
        soggetto.set_nome(p_cognome||'  '||p_nome,1);
        soggetto.set_indirizzo_web(p_indirizzo_mail);
        if (d_provincia_res is not null and --#46826
            d_comune_res is not null ) then
            soggetto.set_indirizzo(p_indirizzo_res);
            soggetto.set_cap(p_cap_res);
            soggetto.set_comune(d_comune_res);
            soggetto.set_provincia(d_provincia_res);
            soggetto.set_telefono(p_tel_res);
        end if;
        SOGGETTO.update_soggetto(d_soggetto);
        if p_profilo is not null then  -- rilancio la profilazione se la pagina di conferma mi passa il profilo per il quale avviEne l'accesso
            d_utente:=UTENTE.GET_UTENTE(p_soggetto => d_soggetto);
            set_profilo(utente.get_nominativo(d_utente), p_profilo);
        end if;
    end;
end;
/

