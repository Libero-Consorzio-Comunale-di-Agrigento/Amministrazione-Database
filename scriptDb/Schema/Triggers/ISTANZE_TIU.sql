CREATE OR REPLACE TRIGGER istanze_tiu
/******************************************************************************
 NOME:        ISTANZE_TIU
 DESCRIZIONE: Trigger for Check / Set DATA Integrity
                          Check FUNCTIONAL Integrity
                            Set FUNCTIONAL Integrity
                       at INSERT or UPDATE or DELETE on Table ISTANZE
******************************************************************************/
   BEFORE INSERT OR UPDATE OR DELETE
   ON istanze
   FOR EACH ROW
DECLARE
   functionalnestlevel   INTEGER;
   integrity_error       EXCEPTION;
   errno                 INTEGER;
   errmsg                CHAR (200);
   dummy                 INTEGER;
   FOUND                 BOOLEAN;
BEGIN
   functionalnestlevel := integritypackage.getnestlevel;
   /***************************************************************************
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ---------------------------------------------------
    0    __/__/____ __
   ***************************************************************************/
   BEGIN                                        -- Check / Set DATA Integrity
      IF UPDATING
      THEN
         --  Colonna "ISTANZA_AMMINISTRATORE" non modificabile
         IF NVL (:OLD.istanza_amministratore, ' ') !=
                                       NVL (:NEW.istanza_amministratore, ' ')
         THEN
            IF functionalnestlevel = 0
            THEN
               errno := -20001;
               errmsg :=
                  'L''informazione ISTANZA_AMMINISTRATORE non puo'' essere modificata.';
               RAISE integrity_error;
            END IF;
         END IF;
      END IF;
      IF UPDATING
      THEN
         --  Colonna "USER_ORACLE" non modificabile
         IF UPPER (:OLD.user_oracle) != UPPER (:NEW.user_oracle)
         THEN
            errno := -20001;
            errmsg :=
                 'L''informazione DATABASE_USER non puo'' essere modificata.';
            RAISE integrity_error;
         END IF;
      END IF;
      IF UPDATING --and instr(:NEW.versione,'@') > 0
      THEN
         declare
         v_slave_d number;
         begin
         --  Non si può aggiornare se almeno uno slave in stato 'D'
         select count(*)
           into v_slave_d
           from slaves
          where stato = 'D';
         IF v_slave_d > 0
         THEN
            errno := -20999;
            errmsg :=
                 'Non si può aggiornare: esistono ' || v_slave_d || ' slave non raggiungibili ';
            RAISE integrity_error;
         END IF;
         end;
      END IF;
      NULL;
   END;
   BEGIN                                         -- Check FUNCTIONAL Integrity
      BEGIN                      -- Check UNIQUE Integrity on PK of "ISTANZE"
         IF integritypackage.getnestlevel = 0 AND NOT DELETING
         THEN
            DECLARE
               CURSOR cpk_istanze (
                  var_istanza   VARCHAR
               )
               IS
                  SELECT 1
                    FROM istanze
                   WHERE istanza = var_istanza;
               mutating   EXCEPTION;
               PRAGMA EXCEPTION_INIT (mutating, -4091);
            BEGIN
               IF :NEW.istanza IS NOT NULL
               THEN
                  OPEN cpk_istanze (:NEW.istanza);
                  FETCH cpk_istanze
                   INTO dummy;
                  FOUND := cpk_istanze%FOUND;
                  CLOSE cpk_istanze;
                  IF FOUND
                  THEN
                     errno := -20007;
                     errmsg :=
                           'Identificazione "'
                        || :NEW.istanza
                        || '" gia'' presente in Istanze. La registrazione  non puo'' essere inserita.';
                     RAISE integrity_error;
                  END IF;
               END IF;
            EXCEPTION
               WHEN mutating
               THEN
                  NULL;                -- Ignora Check su UNIQUE PK Integrity
            END;
         END IF;
      END;
      NULL;
   END;
   BEGIN                                           -- Set FUNCTIONAL Integrity
      IF functionalnestlevel = 0
      THEN
         integritypackage.nextnestlevel;
         BEGIN                      -- Global FUNCTIONAL Integrity at Level 0
            /* NONE */
            NULL;
         END;
         integritypackage.previousnestlevel;
      END IF;
      integritypackage.nextnestlevel;
      BEGIN                          -- Full FUNCTIONAL Integrity at Any Level
         IF NOT DELETING
         THEN
            IF UPPER (SUBSTR (:NEW.link_oracle, -4)) IN (' O73', ' O84')
            THEN
               :NEW.link_oracle :=
                     REPLACE (SUBSTR (:NEW.link_oracle
                                    , 1
                                    , LENGTH (:NEW.link_oracle) - 4
                                     )
                            , ' '
                            , ''
                             )
                  || UPPER (SUBSTR (:NEW.link_oracle, -4));
            ELSE
               :NEW.link_oracle := REPLACE (:NEW.link_oracle
                                          , ' '
                                          , ''
                                           );
            END IF;
         END IF;
         /* NONE */
         NULL;
      END;
      integritypackage.previousnestlevel;
   END;
   BEGIN                          -- Set PostEvent Check REFERENTIAL Integrity
      DECLARE
         a_istruzione   VARCHAR2 (2000);
         a_messaggio    VARCHAR2 (2000);
      BEGIN
         IF INSERTING or UPDATING THEN
            -- controllo che se esiste istanza master questa istanza abbia dblink valorizzato
            a_istruzione :=
                  'select 0 from istanze where instr(''.''|| installazione ||''.'', ''.MASTER.'') >0 '
               || 'and progetto = ''AD4'' and '''
               || :NEW.link_oracle
               || ''' is null';
            a_messaggio :=
                  'Esiste una installazione master/slave AD4 il link oracle dell''istanza '
               || :NEW.istanza
               || ' non puo'' essere NULLO';
            integritypackage.set_postevent (a_istruzione, a_messaggio);
         END IF;
         IF functionalnestlevel = 0 AND (DELETING OR INSERTING)
         THEN
            a_messaggio := '';
            IF DELETING
            THEN
               a_istruzione :=
                     'begin '
                  || '   if nvl('''
                  || :OLD.istanza_amministratore
                  || ''', istanza_amm_pkg.GET_ISTANZA_AD4()) <> istanza_amm_pkg.GET_ISTANZA_AD4() '
                  || '   and istanza_amm_pkg.count_database_user(upper('''
                  || :OLD.user_oracle
                  || '''), '''
                  || :OLD.istanza
                  || ''') = 0 then '
                  || '      raise_application_error(-20999,''Eliminazione non consentita.''); '
                  || '   end if; '
                  || 'end;';
            END IF;
            IF INSERTING
            THEN
               a_istruzione :=
                     'declare '
                  || '   d_count integer; '
                  || '   d_altre_ista_amm istanze.istanza%type; '
                  || '   d_ista_amm istanze.istanza_amministratore%type; '
                  || 'begin '
                  || '   d_count := istanza_amm_pkg.count_database_user(upper('''
                  || :NEW.user_oracle
                  || '''), '''
                  || :NEW.istanza
                  || '''); '
                  || '   if d_count > 0 then '
                  || '      begin '
                  || '         d_altre_ista_amm := istanza_amm_pkg.get_istanza_amministratore(upper('''
                  || :NEW.user_oracle
                  || '''), '''
                  || :NEW.istanza
                  || '''); '
                  || '      exception '
                  || '         when too_many_rows '
                  || '         then '
                  || '            raise_application_error(-20999,''Esistono istanze legate allo stesso user di database ma con istanza di amministratore diversa.''); '
                  || '      end; '
                  || '   end if;  '
                  || '   d_ista_amm := nvl('''
                  || :NEW.istanza_amministratore
                  || ''', istanza_amm_pkg.get_istanza_ad4()); '
                  || '   if (d_count = 0 and istanza_amm_pkg.get_istanza_ad4() <> d_ista_amm) '
                  || '   or (d_count > 0 and d_altre_ista_amm <> d_ista_amm) '
                  || '   then '
                  || '      raise_application_error(-20999,''Inserimento non consentito.''); '
                  || '   end if; '
                  || 'end;';
            END IF;
            integritypackage.set_postevent (a_istruzione, a_messaggio);
         END IF;
      END;
   END;
EXCEPTION
   WHEN integrity_error
   THEN
      integritypackage.initnestlevel;
      raise_application_error (errno, errmsg);
   WHEN OTHERS
   THEN
      integritypackage.initnestlevel;
      RAISE;
END;
/


