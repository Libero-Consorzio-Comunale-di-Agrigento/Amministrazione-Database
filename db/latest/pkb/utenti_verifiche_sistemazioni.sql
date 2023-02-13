--liquibase formatted sql

--changeset snegroni:2019004021437 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE BODY UTENTI_VERIFICHE_SISTEMAZIONI
IS
    /******************************************************************************
     NOME:        UTENTI_VERIFICHE_SISTEMAZIONI
     DESCRIZIONE: Procedure e Funzioni per verificare e sistemare situazione utenti
     ANNOTAZIONI:
     REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
      00  18/12/2018 SN     Creazione
      01  09/01/2019 SN     Nuove procedure
      02  30/04/2019 SNeg   Nuove Funzioni
      03  15/10/2020 SNeg   Aggiornamento utenze tecniche Gruppo Finmatica #45388
    ******************************************************************************/
    s_revisione_body   Afc.t_revision := '003';

    FUNCTION VERSIONE
        RETURN VARCHAR2
    IS
    /******************************************************************************
     NOME:        VERSIONE
     DESCRIZIONE: Restituisce versione e revisione di distribuzione del package.
     RITORNA:     stringa VARCHAR2 contenente versione e revisione.
     NOTE:        Primo numero  : versione compatibilita del Package.
                  Secondo numero: revisione del Package specification.
                  Terzo numero  : revisione del Package body.
    ******************************************************************************/
    BEGIN
        RETURN Afc.VERSION (s_revisione, s_revisione_body);
    END VERSIONE;

    FUNCTION GET_IS_UTENZA_ADS (p_utente VARCHAR2)
        RETURN VARCHAR2
    IS

    /******************************************************************************
     NOME:        GET_IS_UTENZA_ADS
     DESCRIZIONE: Indica se utenza è distribuita da ADS
     ANNOTAZIONI:
     REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
      03  15/10/2020 SNeg   Aggiornamento utenze tecniche Gruppo Finmatica #45388
    ******************************************************************************/
        v_risultato   VARCHAR2 (2) := '';
        v_esiste      NUMBER := 0;
    BEGIN
        SELECT COUNT (1)
          INTO v_esiste
          FROM DUAL
         WHERE    p_utente IN ('ric_abil',
                               'ADMMENU',
                               'ZDM',
                               'SIA',
                               'SIAWEB',
                               'SI4JS',
                               'CE4',
                               'BCS',
                               'AS4',
                               'CGS',
                               'CU',
                               'CU2015',
                               'CI4',
                               'SO4',
                               'SI4CS',
                               'FS4',
                               'CF4',
                               'AD4',
                               'FSA',
                               'FS4AMM',
                               'CG4',
                               'AC4',
                               'GC4',
                               'GE4',
                               'GST',
                               'IP4',
                               'SI4MM',
                               'ADMINS',
                               'GUEST',
                               'A00',
                               'JWF',
                               'XML',
                               'CFA',
                               'WDD',
                               'GSWS1',
                               'BC4',
                               'DBFW',
                               'JSD',
                               'SI',
                               'GDM',
                               'GPSDI', --rev. 3 inizio
                               'GPSDO',
                               'GPS',
                               'CUWEB',
                               'GE4GG') --rev. 3 fine
               OR p_utente LIKE 'M770%'
               OR p_utente LIKE 'CF4%'
               OR p_utente LIKE 'GS4%'
               OR p_utente LIKE 'GP4%'
               OR p_utente LIKE 'P00%'
               OR (    utente.get_nominativo (p_utente) LIKE '%MONTOSI%'
                   AND utente.get_nominativo (p_utente) LIKE '%PAOLO%');

        IF v_esiste = 1
        THEN
            v_risultato := 'SI';
        END IF;

        RETURN v_risultato;
    END get_is_utenza_ads;

    FUNCTION GET_IS_SO4_COMPONENTE (p_soggetto NUMBER)
        RETURN VARCHAR2
    IS
        v_risultato    VARCHAR2 (2) := 'NO';
        d_statement    afc.t_statement;
        v_esiste       NUMBER := 0;
        d_cur_valori   afc.t_ref_cursor;
        d_valori       VARCHAR2 (1000);
    BEGIN
        SELECT COUNT (1)
          INTO v_esiste
          FROM all_tables
         WHERE owner = 'SO4' AND table_name = 'COMPONENTI';

        IF v_esiste = 1 AND p_soggetto IS NOT NULL
        THEN
            d_statement :=
                   ' select  max(''SI'') '
                || '   from so4.componenti '
                || '  where ni = '
                || p_soggetto;

            d_cur_valori := afc_dml.get_ref_cursor (d_statement);

            FETCH d_cur_valori INTO d_valori;

            IF d_cur_valori%FOUND
            THEN
                v_risultato := d_valori;
            END IF;
            if v_risultato is null then -- non ha trovato componenti
               v_risultato := 'NO';
            end if;
        END IF;


        RETURN v_risultato;
    END GET_IS_SO4_COMPONENTE;

    FUNCTION GET_IS_UTENZA_LDAP (p_utente VARCHAR2)
        RETURN VARCHAR2
    IS
    v_is_utenza_ldap varchar2(2):= 'NO';
    BEGIN
       if accesso.is_ldapuser (utente.get_nominativo (p_utente)) = 1 then
          v_is_utenza_ldap := 'SI';
       end if;
       return v_is_utenza_ldap;
    END;

    FUNCTION GET_IS_DIRITTO_DA_SERVIZIO (p_utente VARCHAR2, p_modulo VARCHAR2, p_gruppo VARCHAR2)
        RETURN VARCHAR2
    IS
    v_is_diritto_da_servizio varchar2(2):= 'NO';
    BEGIN
       select max(decode(s.modulo,null,'NO','SI'))
         into v_is_diritto_da_servizio
        from richieste_abilitazione rich
           , servizi s
        where p_utente = rich.utente
          and p_gruppo = s.GRUPPO_ABILITAZIONE
          and s.modulo = p_modulo
          and rich.stato = 'A';
       return v_is_diritto_da_servizio;
    END;

    FUNCTION GET_ELENCO_DIAC (p_utente_o_gruppo VARCHAR2, p_modulo VARCHAR2)
        RETURN VARCHAR2
    IS
        dep_elenco_diac   VARCHAR2 (32000);
        d_statement       afc.t_statement;
        v_esiste          NUMBER := 0;
        d_cur_valori      afc.t_ref_cursor;
        d_valori          VARCHAR2 (1000);
    BEGIN
        dep_elenco_diac := NULL;

        FOR diac
            IN (  SELECT *
                    FROM diritti_accesso
                   WHERE utente = p_utente_o_gruppo AND modulo LIKE p_modulo
                ORDER BY istanza)
        LOOP
            --      dbms_output.put_line(dep_elenco_diac || 'x' || diac.modulo || '('|| diac.istanza ||'),');
            --     dbms_output.put_line('instr:' ||nvl(instr(dep_elenco_diac, diac.modulo || '('|| diac.istanza ||')') ,0) );
            IF NVL (
                   INSTR (dep_elenco_diac,
                          diac.modulo || '(' || diac.istanza || ')'),
                   0) =
               0
            THEN
                dep_elenco_diac :=
                       dep_elenco_diac
                    || diac.modulo
                    || '('
                    || diac.istanza
                    || '),';
            END IF;
        END LOOP;

        SELECT COUNT (1)
          INTO v_esiste
          FROM all_tables
         WHERE owner = 'A00' AND table_name = 'A_DIRITTI_ACCESSO';

        IF v_esiste = 1
        THEN
            NULL;
            d_statement :=
                   ' select  applicazione  || ''(''||  ambiente||ente||gruppo_ling ||'')'''
                || '   from a00.a_diritti_accesso '
                || '  where utente = '''
                || REPLACE (p_utente_o_gruppo, '''', '''''')
                || ''''
                || '    and applicazione like '''
                || p_modulo
                || '''  ORDER BY ambiente||ente||gruppo_ling ';

            --              raise_application_error( -20999,d_statement);

            d_cur_valori := afc_dml.get_ref_cursor (d_statement);

            FETCH d_cur_valori INTO d_valori;

            WHILE d_cur_valori%FOUND
            LOOP
                IF INSTR (dep_elenco_diac, d_valori) = 0
                THEN
                    dep_elenco_diac := dep_elenco_diac || d_valori || ',';
                END IF;

                FETCH d_cur_valori INTO d_valori;
            END LOOP;
        END IF;

        dep_elenco_diac := RTRIM (dep_elenco_diac, ',');
        RETURN dep_elenco_diac;
    END GET_ELENCO_DIAC;

    FUNCTION GET_ELENCO_UTENTI (p_utente VARCHAR2 DEFAULT '%')
        RETURN Afc.t_ref_cursor
    IS
        d_result   afc.t_ref_cursor;
    BEGIN
        OPEN d_result FOR
            SELECT u.utente,
                   u.nominativo,
                   UTENTI_VERIFICHE_SISTEMAZIONI.get_is_utenza_ads (u.utente)
                       is_utenza_tecnica_ads,
                   s.soggetto
                       soggetto_numero,
                   s.nome
                       soggetto_nome,
                   s.codice_fiscale
                       soggetto_codice_fiscale,
                   UTENTI_VERIFICHE_SISTEMAZIONI.get_is_so4_componente (
                       s.soggetto)
                       is_in_struttura_organizzativa,
                   ad4_utente.get_ultiMo_tentativo (u.utente)
                       ultimo_tentativo,
                   UTENTI_VERIFICHE_SISTEMAZIONI.get_elenco_diac (u.utente,
                                                                  '%')
                       "DIRITTI: MODULO(ISTANZA)",
                   ''
                       "NUOVE INFO ->",
                   ''
                       "DISABILITARE (SI/NO=VUOTO)",
                   ''
                       "NOMINATIVO LDAP(CAMBIARE)",
                   ''
                       nuovo_soggetto_abbinare,
                   ''
                       nuovo_cognome,
                   ''
                       nuovo_nome,
                   ''
                       nuovo_codice_fiscale
              FROM utenti u, utenti_soggetti us, soggetti s
             WHERE     u.utente = us.utente(+)
                   AND us.soggetto = s.soggetto(+)
                   AND u.utente LIKE p_utente
                   AND u.stato = 'U'
                   -- and s.al is not null
                   AND tipo_utente = 'U';

        RETURN d_result;
    END GET_ELENCO_UTENTI;

    PROCEDURE SISTEMAZIONE_SOGGETTO (p_utente VARCHAR2 DEFAULT '%')
    IS
        v_ni       NUMBER;
        d_utente   utenti.utente%TYPE := p_utente;
    BEGIN
        FOR v_utente
            IN (                                         -- UTENTI da inserire
                SELECT *
                  FROM UTENTI_DATI_FOGLIO_EXCEL p
                 WHERE     utente IN (SELECT utente FROM utenti)
                       AND utente LIKE p_utente)
        LOOP
            -- utente esiste già
            IF utente.exists_utente (v_utente.utente) = 1
            THEN
                utente.initialize (v_utente.utente);
            END IF;

            IF UPPER (v_utente.disabilitare) IN ('S', 'SI', 'YES')
            THEN
                UPDATE utenti
                   SET stato = 'S'
                 WHERE utente = v_utente.utente;
            END IF;

            IF v_utente.nuovo_ni_abbinare IS NULL
            THEN
                -- non esiste gia soggetto da abbinare
                IF v_utente.nuovo_cognome IS NOT NULL
                THEN
                    IF p_utente IS NOT NULL
                    THEN
                        soggetto.set_cognome (v_utente.nuovo_cognome);
                        soggetto.set_nome (v_utente.nuovo_nome,
                                           p_completo   => 0);
                        soggetto.set_codice_fiscale (
                            v_utente.nuovo_codice_fiscale);
                        soggetto.set_comune_nas (
                            codice_fiscale.GET_COMUNE_NAS (
                                v_utente.nuovo_codice_fiscale));
                        soggetto.set_provincia_nas (
                            codice_fiscale.GET_PROVINCIA_NAS (
                                v_utente.nuovo_codice_fiscale));
                        soggetto.set_data_nascita (
                            (codice_fiscale.GET_DATA_NAS (
                                 (v_utente.nuovo_codice_fiscale))));
                        soggetto.set_sesso (
                            codice_fiscale.GET_SESSO (
                                v_utente.nuovo_codice_fiscale));
                    END IF;

                    utente.update_utente_commit (d_utente, 'P');
                END IF;
            ELSE                                     -- ni da abbinare fornito
                INSERT INTO utenti_soggetti (utente, soggetto)
                     VALUES (v_utente.utente, v_utente.nuovo_ni_abbinare);
            END IF;
        END LOOP;
    END SISTEMAZIONE_SOGGETTO;


    PROCEDURE CAMBIO_NOMINATIVO_INS_GRPLDAP (p_utente VARCHAR2 DEFAULT '%')
    IS
    BEGIN
        FOR v_utente
            IN (                                         -- UTENTI da inserire
                SELECT *
                  FROM UTENTI_DATI_FOGLIO_EXCEL p
                 WHERE     utente IN (SELECT utente FROM utenti)
                       AND utente LIKE p_utente)
        LOOP
            IF v_utente.nominativo != v_utente.nuovo_nominativo_utente
            THEN
                UPDATE utenti
                   SET nominativo = v_utente.nuovo_nominativo_utente
                 WHERE utente = v_utente.utente;
            END IF;

            IF     UPPER (NVL (v_utente.utenza_ldap, 'S')) IN
                       ('S', 'SI', 'YES')
               AND NOT (UPPER (NVL (v_utente.is_utenza_tecnica_ads, 'S')) IN
                            ('S', 'SI', 'YES'))
            THEN
                INSERT INTO utenti_gruppo (utente, gruppo)
                     VALUES (v_utente.utente, 'GRPLDAP');
            END IF;
        END LOOP;
    END CAMBIO_NOMINATIVO_INS_GRPLDAP;
END UTENTI_VERIFICHE_SISTEMAZIONI;
/
