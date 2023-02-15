CREATE OR REPLACE TRIGGER UTENTI_TIU
/******************************************************************************
 NOME:        UTENTI_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                       at INSERT or UPDATE on Table UTENTI
 ECCEZIONI:   -20007, Identificazione CHIAVE presente in TABLE
 ANNOTAZIONI: Richiama Procedure UTENTI_PI e UTENTI_PU
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
 1    17/05/2007 MM     A21229: Inibita la possibilita' di creare utenti con
                        upper(nominativo) = 'GUEST' e codice diverso da GUEST.
 2    04/06/2008 SN     Inserito controllo per evitare che esista un altro
                        record con upper(nominativo) o upper(utente)
 3    04/06/2010 SN     Se modificata importanza sistemare diritti accesso
 4    11/11/2010 SN     Se password aggiornata anche da ldap aggiorno data.
 5    15/06/2011 SN    Se utente ldap non mette pwd_da_modificare = SI
 6    25/10/2011 SN    Controllo su upper(nominativo) o upper(utente) solo se cambiati
 7    12/04/2012 SN    Pulite note da password solo se note non cambiate.
 8    23/07/2013 SN    Tolta forzatura per upper con utente 'ric_abil'
 9    13/06/2014 SN    Corretto errore in togliere forzatura per utente 'ric_abil'
 10   10/03/2015 SN    Modifiche a codice e nominativo x forzarli upper solo in inserimento.
 11   20/02/2018 SN    Problemi in registrazione utente che ha effettuato le modifiche
 12   21/09/2018 SN    Controllo su informazioni_identificazione valorizzate se utente di tipo amministratore
 13   08/01/2019 SN    Non consentire modifica di gruppi standard: _GR_AMM_ e _GR_APP_
 14   21/01/2019 SN    Invio mail se modificato amministratore (si/no) x un utente
 15   22/05/2019 SN    Utenti SEMPRE posizionati nel gruppo standard
 16   19/05/2020 SN    Sistemare posizionamento nel gruppo standard  Bug#41840
 17   27/05/2020 SN    Forzare il cambio password se utente diventa amministratore  Feature#41089
 18   11/06/2020 SB     Errore se il numero dei diritti di accesso supera le 3 cifre Bug #42950
 19   08/02/2021 SN    Tracciare informazioni nella eventi di tipo APPTRC se
                       modificato amministratore (si/no) x un modulo Feature #48075
******************************************************************************/
   BEFORE INSERT OR UPDATE ON UTENTI FOR EACH ROW
DECLARE
   integrity_error         EXCEPTION;
   errno                   INTEGER;
   errmsg                  CHAR(200);
   dummy                   INTEGER;
   FOUND                   BOOLEAN;
   d_min_lung_pwd          NUMBER(2);
   v_messaggio             VARCHAR2 (2000);
   v_id_evento             eventi.id_evento%TYPE;
BEGIN
   IF INSERTING THEN
       if :new.tipo_utente <> 'O' and :new.utente != 'ric_abil' then
          :NEW.utente := upper(:NEW.utente);
       end if;
       if :new.tipo_utente = 'U' then
          :NEW.nominativo := upper(:NEW.nominativo);
       end if;
   END IF; -- inserting
   --rev.13 Inizio
     if :old.utente in ('_GR_AMM_','_GR_APP_') then
        if :old.utente != :new.utente or
           :old.nominativo != :new.nominativo or
           nvl(:old.amministratore,'x') != nvl(:new.amministratore,'y') or
           nvl(:old.INFO_IDENTIFICAZIONE,'x') != nvl(:new.INFO_IDENTIFICAZIONE,'y') then
         raise_application_error(-20999,'ERRORE: Impossibile modificare gruppo Standard.('|| :old.utente || ')');
        end if;
     end if;
     -- rev. 16 inizio
     if  (updating and :old.tipo_utente = :new.tipo_utente and
        :old.tipo_utente = 'U' and
        nvl(:old.amministratore,'N')!= nvl(:new.amministratore,'N'))
        or inserting --rev.16 fine
        -- rev. 15 inizio
        then
            DECLARE
               a_istruzione   VARCHAR2 (2000);
               a_messaggio    VARCHAR2 (2000);
            BEGIN
               a_istruzione :='begin utente.sistema_posizione_gruppi('''
                  || replace(:new.utente,'''','''''')
                  || '''); end;';
               a_messaggio := 'ERRORE in RIGENERA posizione per utente ('''
                  || replace(:new.utente,'''','''''')
                  || ''')';
               integritypackage.set_postevent (a_istruzione, a_messaggio);
            END;
        if global_utility.get_registro_amministratore != 'no'  -- solo se verifica da effettuar
        then
--        utente.rigenera_so(:new.utente);
            DECLARE
               a_istruzione   VARCHAR2 (2000);
               a_messaggio    VARCHAR2 (2000);
            BEGIN
            -- rev. 15 inizio
--               a_istruzione :='begin utente.sistema_posizione_gruppi('''
--                  || replace(:new.utente,'''','''''')
--                  || '''); end;';
--               a_messaggio := 'ERRORE in RIGENERA posizione per utente ('''
--                  || replace(:new.utente,'''','''''')
--                  || ''')';
--               integritypackage.set_postevent (a_istruzione, a_messaggio);

            -- rev. 15 fine

               a_istruzione :='begin utente.RISISTEMA_DIAC_UTENTE('''
                  || replace(:new.utente,'''','''''')
                  || '''); end;';
               a_messaggio := 'ERRORE in SISTEMAZIONE diritti accesso utente ('''
                  || replace(:new.utente,'''','''''')
                  || ''')';
               integritypackage.set_postevent (a_istruzione, a_messaggio);
                null;
            END;


            -- rev. 19 inizio
            -- rev. 14 inizio
--             raise_application_error (-20999,'situazione ' || nvl(:old.amministratore,'N') ||':'|| nvl(:new.amministratore,'N') );
            if nvl(:old.amministratore,'N') != nvl(:new.amministratore,'N') then

                if nvl(:old.amministratore,'N') ='N' and nvl(:new.amministratore,'S')= 'S' then
                  v_messaggio := ' L''''utente in oggetto e'''' diventato AMMINISTRATORE';
                else
                  v_messaggio :=  ' L''''utente in oggetto NON e'''' piu'''' AMMINISTRATORE';
                end if;

            IF upper(NVL (registro_utility.leggi_stringa ('PRODUCTS/AD4/UTENTE'
                                            , 'NotificaModificaAmministratoreSiNo'
                                            , 1
              )
            , 'NO')) != 'NO' THEN
            -- invio mail x notificare attributi modificati di utente
              DECLARE --invio in post-event cosi va soltanto se effettivamente ha avuto successo
               a_istruzione   VARCHAR2 (2000);
               a_messaggio    VARCHAR2 (2000);
            BEGIN
               a_istruzione :='begin utente.SEND_MSG_MODIFICA_UTENTE('''
                  || ' utente: '
                  || replace(:new.utente,'''','''''')
                  || ' nominativo: '
                  || replace(:new.nominativo,'''','''''')
                  || '    Modificato ! '', ''';
                  a_istruzione := a_istruzione
                  ||  v_messaggio;
                  a_istruzione := a_istruzione
                  ||'''); end;';
                  null;
               a_messaggio := 'ERRORE in invio mail per modifiche utente ('''
                  || replace(:new.utente,'''','''''')
                  || ''')';
               integritypackage.set_postevent (a_istruzione, a_messaggio);
            END;
            END IF;
            v_id_evento:= ad4_evento.INSERT_EVENTO
                            ( p_testo        =>substr('Utente '''
                                         || :new.utente
                                         || ''' modificato attributo amministratore : '
                                         || :new.amministratore, 1, 2000)
                             , p_db_user    => user
                             , p_data       => to_char(sysdate, 'dd/mm/yyyy hh24:mi:ss' )
                             , p_notificato => 0 --eventi
                             , p_gravita    => 'I'
                             , p_tipo       => 'APPTRC'
                             , p_annotazioni => replace(v_messaggio,'''''','''')
                             , p_utente     => :new.utente
                             , p_modulo    => null
                             , p_istanza     => null);
            -- rev. 14 fine
           end if;
            -- rev. 19 fine
          end if; -- rev.15 fine
     end if;
   --rev.13 Fine
   BEGIN  -- Check DATA Integrity on INSERT or UPDATE
   -- Rev. 1: 20/02/2018 SN: Problemi in registrazione utente che ha effettuato le modifiche
        IF :new.utente_aggiornamento is null then
            IF  Si4.Utente IS NOT NULL THEN
                :NEW.utente_aggiornamento := Si4.Utente;
            ELSE
                IF UPDATING THEN
                    :NEW.utente_aggiornamento := :new.Utente;
                END IF;
            END IF;
        END IF;
   -- Rev. 1: 20/02/2018 SN:fine mod.
      IF :NEW.data_aggiornamento IS NULL THEN
         :NEW.data_aggiornamento := SYSDATE;
      END IF;
      if instr(:NEW.utente,'''') > 0 then
         raise_application_error(-20999,'Carattere '' non ammesso nel codice utente.');
      end if;
      -- Rev. 1: 17/05/2007 MM A21229: Inibita la possibilita' di creare utenti
      -- con upper(nominativo) = 'GUEST' e codice diverso da GUEST.
      if upper(:new.nominativo) = 'GUEST' and :new.utente <> 'GUEST'
      then
         raise_application_error(-20999, 'Nominativo '''||:new.nominativo||''' non valido.');
      end if;
      if upper(:old.nominativo) = 'GUEST' and upper(:new.nominativo) <> 'GUEST'
      then
         raise_application_error(-20999, 'Nominativo '''||:old.nominativo||''' non modificabile.');
      end if;
      -- Rev. 1: 17/05/2007 MM: fine mod.
      -- Rev. 12 inizio
       if nvl(:NEW.amministratore,'N') = 'S' and :new.INFO_IDENTIFICAZIONE is null then
         raise_application_error(-20999,'Informazioni identificazione obbligatorie per utenza AMMINISTRATORE');
      end if;
      -- Rev. 12 fine
      -- Rev. 17 inizio
       if nvl(:NEW.amministratore,'N') = 'S'  and nvl(:OLD.amministratore,'N') != nvl(:NEW.amministratore,'N')then
         :new.pwd_da_modificare := 'SI';
         DECLARE --invio in post-event cosi va dopo le sistemazioni standard del campo pwd_da_modificare
         -- fatte in base alle caratteristiche di accesso che avrebbero sovrascritto la modifica
               a_istruzione   VARCHAR2 (2000);
               a_messaggio    VARCHAR2 (2000);
            BEGIN
            a_istruzione := 'update utenti set pwd_da_modificare = ''SI'' where utente = '''|| :new.utente|| ''';';
            a_messaggio := 'ERRORE indicazione password da modificare';
               integritypackage.set_postevent (a_istruzione, a_messaggio);
            END;
      end if;
      -- Rev. 17 fine
   END;
   BEGIN  -- Check REFERENTIAL Integrity on INSERT or UPDATE
      IF UPDATING THEN
         Utenti_Pu(:OLD.Utente
                       , :OLD.NOMINATIVO
                       , :OLD.ID_UTENTE
                       , :OLD.GRUPPO_LAVORO
                       , :OLD.UTENTE_AGGIORNAMENTO
                         , :NEW.Utente
                         , :NEW.NOMINATIVO
                         , :NEW.ID_UTENTE
                         , :NEW.GRUPPO_LAVORO
                         , :NEW.UTENTE_AGGIORNAMENTO
                         );
         IF NVL(:OLD.PASSWORD,' ') <> NVL(:NEW.PASSWORD,' ') THEN
           DECLARE
              d_min_lung_pwd          NUMBER(2);
              d_mod_pwd_primo_accesso NUMBER(1);
              d_diac_count            NUMBER(3);
              d_dep_pwd               VARCHAR2(100);
            BEGIN
               d_min_lung_pwd          := Caratteristica_Accesso.GET_MINPWDLENGTH(:NEW.Utente);
               d_mod_pwd_primo_accesso := Caratteristica_Accesso.IS_PWD_DA_MOD_PRIMO_USO(:NEW.Utente, :new.tipo_utente);
               IF NVL(LENGTH(:NEW.PASSWORD),0) < NVL(d_min_lung_pwd,0) THEN
                  /*  Inserito controllo lunghezza password minima */
                  errno  := -20007;
                  errmsg := 'La Password deve essere almeno di '||d_min_lung_pwd||' caratteri.';
                  RAISE integrity_error;
               END IF;
               :new.data_password := sysdate;
               SELECT COUNT(1)
                 INTO d_diac_count
                 FROM DUAL
                -- rev.18 inizio
                where exists (SELECT 1
                                FROM DIRITTI_ACCESSO
                               WHERE Utente = :NEW.Utente)
                -- rev.18 fine
               ;
               IF d_diac_count = 0 THEN
                  -- nuovo utente senza diritti di accesso, quindi non sottoposto a nessun
                  -- controllo della password, percio' settiamo pwd_da_modificare := 'SI'
                  :NEW.pwd_da_modificare := 'SI';
               ELSE
                  -- se l'utente non ha diritti di accesso per cui non e' stata prevista la
                  -- modifica della password al primo accesso o se non puo' modificarsi la
                  -- password autonomamente, settiamo pwd_da_modificare := 'NO'
                  IF (NVL(d_mod_pwd_primo_accesso,0) = 0 AND NVL(:NEW.importanza,0) <> -1)
                  OR NVL(:NEW.rinnovo_password,'SI') = 'NO' THEN
                     :NEW.pwd_da_modificare := 'NO';
                  END IF;
               END IF;
               IF  NVL(d_mod_pwd_primo_accesso,0) = 1
               AND NVL(:NEW.utente_aggiornamento,' ') <> :NEW.Utente
               AND NVL(:NEW.rinnovo_password,'SI') = 'SI'
               AND caratteristica_accesso.is_ldapuser(:old.utente,'U') = 0 -- non ldap user
               THEN
               -- Impone la modifica della password se l'utente non
               -- e' quello che sta effettuando la modica.
                  :NEW.pwd_da_modificare := 'SI';
               END IF;
               IF  NVL(:NEW.utente_aggiornamento,' ') = :NEW.Utente
               AND NVL(:NEW.rinnovo_password,'SI') = 'SI' THEN
                  :NEW.pwd_da_modificare := 'NO';
                  :NEW.importanza := NULL;
               END IF;
              if nvl(:new.note,'x') = nvl(:old.note,'Y') then
               -- se le note non sono cambiate tolgo la password dalle note
                   d_dep_pwd := Afc.get_stringParm(:NEW.NOTE, 'PWD');
                   :NEW.NOTE := REPLACE(:NEW.NOTE,'PWD='||d_dep_pwd,'');
               end if;
            END;
         END IF /* updating('PASSWORD') */;
         IF NVL(:OLD.IMPORTANZA,0) <> NVL(:NEW.IMPORTANZA,0) THEN
            -- cambiata importanza
            -- devo risistemare i diritti di accesso e caratteristiche
            -- tolgo e rimetto tutti gli utenti dal gruppo che sto modificando
            for v_gruppo in (select utente, gruppo
                               from utenti_gruppo
                              where gruppo = :NEW.UTENTE)loop
            DECLARE
               a_istruzione   VARCHAR2 (2000);
               a_messaggio    VARCHAR2 (2000);
            BEGIN
               a_istruzione :='delete utenti_gruppo where utente = '''
                  || replace(v_gruppo.utente,'''','''''')
                  || ''' and gruppo = '''
                  || replace(v_gruppo.gruppo,'''','''''')
                  || '''';
               a_messaggio := 'ERRORE in cacnellazione diritti accesso per utente '
                  || replace(v_gruppo.utente,'''','''''')
                  || ''' e gruppo '''
                  || replace(v_gruppo.gruppo,'''','''''')
                  || '''';
               integritypackage.set_postevent (a_istruzione, a_messaggio);
               a_istruzione :='insert into utenti_gruppo (utente,gruppo) values  ('''
                  || replace(v_gruppo.utente,'''','''''')
                  || ''','''
                  || replace(v_gruppo.gruppo,'''','''''')
                  || ''')';
               a_messaggio := 'ERRORE in inserimento diritti accesso per utente '
                  || replace(v_gruppo.utente,'''','''''')
                  || ''' e gruppo '''
                  || replace(v_gruppo.gruppo,'''','''''')
                  || '''';
               integritypackage.set_postevent (a_istruzione, a_messaggio);
            END;
            end loop;
         END IF;
      END IF /* UPDATING */;
      IF INSERTING THEN
         Utenti_Pi(:NEW.GRUPPO_LAVORO,
                         :NEW.UTENTE_AGGIORNAMENTO);
    -- Inizializzazione campo 'DATA_INSERIMENTO'
         :NEW.DATA_INSERIMENTO := SYSDATE;
         -- modifica x creare codice utente significativo
         if :NEW.Utente is null  -- da calcolare in modo automatico
          then
           :NEW.Utente := utente.determina_utente_libero(upper(:new.nominativo));
         end if;
         Utente.CALCOLA_UTENTE(:NEW.Utente, :NEW.ID_UTENTE);
         IF Integritypackage.GetNestLevel = 0 THEN
            DECLARE  --  Check UNIQUE PK Integrity per la tabella "UTENTI"
            CURSOR cpk_utenti(var_UTENTE VARCHAR) IS
               SELECT 1
                 FROM   UTENTI
                WHERE  Utente = var_UTENTE;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on PK of "UTENTI"
               IF :NEW.Utente IS NOT NULL THEN
                  OPEN  cpk_utenti(:NEW.Utente);
                  FETCH cpk_utenti INTO dummy;
                  FOUND := cpk_utenti%FOUND;
                  CLOSE cpk_utenti;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.Utente||
                               '" gia'' presente in Utenti. La registrazione  non puo'' essere inserita.';
                     RAISE integrity_error;
                  END IF;
               END IF;
            EXCEPTION
               WHEN MUTATING THEN NULL;  -- Ignora Check su UNIQUE PK Integrity
            END;
         END IF;
      END IF;
   END;
   DECLARE
      a_istruzione   VARCHAR2 (2000);
      a_messaggio    VARCHAR2 (2000);
   BEGIN
     if nvl(:old.nominativo,'ON!') != nvl(:new.nominativo,'NN!')
        or nvl(:old.utente,'OU!') != nvl(:new.utente,'NU!') then
        -- solo se utente o nominativo da cambiare
      -- controllo se esiste utente con upper(nominativo) = upper(:new.nominativo) o
      -- upper(utente) = upper(:new.utente)
          a_istruzione :=
                'select 0 from utenti where (upper(nominativo) = upper('''
             || replace(:NEW.nominativo,'''','''''')
             || ''') or upper(utente) = upper('''
             || replace(:NEW.utente,'''','''''')
             || ''') )and id_utente != ' ||:new.id_utente;
          a_messaggio :=
                'Inserimento gruppo: nominativo('
             || replace(:NEW.nominativo,'''','''''')
             || ') o codice('
             || replace(:NEW.utente,'''','''''')
             || ') GIA'' PRESENTE';
          integritypackage.set_postevent (a_istruzione, a_messaggio);
      end if;
      if updating and :new.utente != :old.utente then
         a_istruzione :=
               'select 0 from utenti '
            ||'  where upper(utente_aggiornamento) = upper('''
            || replace(:OLD.utente,'''','''''')
            || ''')';
         a_messaggio :=
               'Esistono riferimenti su Utenti. La registrazione di Utenti non e'' modificabile.';
         integritypackage.set_postevent (a_istruzione, a_messaggio);
      end if;
   END;
EXCEPTION
   WHEN integrity_error THEN
        Integritypackage.InitNestLevel;
        RAISE_APPLICATION_ERROR(errno, errmsg);
   WHEN OTHERS THEN
        Integritypackage.InitNestLevel;
       RAISE;
END;
/


