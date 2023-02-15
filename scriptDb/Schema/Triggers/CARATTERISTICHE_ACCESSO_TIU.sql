CREATE OR REPLACE TRIGGER CARATTERISTICHE_ACCESSO_TIU
BEFORE INSERT OR UPDATE
ON CARATTERISTICHE_ACCESSO
FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   FOUND            BOOLEAN;
   dTipoUtente      VARCHAR2(1);
   a_istruzione     VARCHAR2(2000);
   a_messaggio      VARCHAR2(2000);
BEGIN
   BEGIN  -- Set DATA Integrity
      /* NONE */ NULL;
   END;
   BEGIN  -- Set FUNCTIONAL Integrity
      IF Integritypackage.GetNestLevel = 0 THEN
         Integritypackage.NextNestLevel;
         BEGIN  -- Global FUNCTIONAL Integrity at Level 0
            IF NVL(:NEW.ARCHIVIAZIONE_TRACCIA,'NO') = 'SI' THEN
               IF NVL(:NEW.giorni_traccia, 0) <= 0 THEN
                  RAISE_APPLICATION_ERROR(-20999,'Prevista archiviazione della traccia; il numero di giorni di registrazione della traccia deve essere un numero positivo ( >0 ) !');
               END IF;
               IF :NEW.dislocazione_traccia IS NULL THEN
                  RAISE_APPLICATION_ERROR(-20999,'Prevista archiviazione della traccia; specificare il percorso della directory su file system del db server in cui memorizzarla !');
               END IF;
            END IF;
            --  Column "CAAC_ID" attribuisce MAX+1
            IF :NEW.CAAC_ID IS NULL THEN
               :NEW.CAAC_ID := Si4.NEXT_ID('CARATTERISTICHE_ACCESSO','CAAC_ID');
            END IF;
         END;
         Integritypackage.PreviousNestLevel;
      END IF;
      Integritypackage.NextNestLevel;
     DECLARE
         d_dir_alias      VARCHAR2(100);
         d_err            NUMBER;
      BEGIN  -- Full FUNCTIONAL Integrity at Any Level
--DBMS_OUTPUT.PUT_LINE('fuori if '||:NEW.dislocazione_traccia||' '||:OLD.dislocazione_traccia);
         IF  NVL(:NEW.dislocazione_traccia,' ') <> ' '
         AND NVL(:NEW.dislocazione_traccia, ' ') <> NVL(:OLD.dislocazione_traccia, ' ') THEN
            d_dir_alias := Afc_Bfile.next_directory('AD4',:NEW.dislocazione_traccia);
            -- create_directory ritorna:
            -- 0  OK
            -- -1 errore in creazione dell'alias
            -- -2 errore in creazione del file di test nella directory
            -- -3 errore in eliminazione del file di test dalla directory
            d_err := Afc_Bfile.create_directory(d_dir_alias,:NEW.dislocazione_traccia);
            IF d_err = -1 THEN
               RAISE_APPLICATION_ERROR(-20999,'Errore in creazione dell''alias della directory '||:NEW.dislocazione_traccia);
            ELSIF d_err = -2 THEN
               RAISE_APPLICATION_ERROR(-20999,'Errore in creazione del file di test nella directory '||:NEW.dislocazione_traccia);
            END IF;
         END IF;
      END;
      Integritypackage.PreviousNestLevel;
     IF :NEW.Utente IS NULL THEN
         IF NVL(:OLD.MOD_PWD_PRIMO_ACCESSO,'NO') <> NVL(:NEW.MOD_PWD_PRIMO_ACCESSO,'NO') THEN
            FOR uten IN (SELECT DISTINCT Utente
                           FROM DIRITTI_ACCESSO
                          WHERE ( :NEW.TIPO_ACCESSO = 'P' AND
                                  Istanza IN (SELECT Istanza
                                                FROM ISTANZE
                                               WHERE progetto = :NEW.PROGETTO)
                                )
                             OR ( :NEW.TIPO_ACCESSO = 'I' AND
                                  Istanza = :NEW.Istanza)
                             OR ( :NEW.TIPO_ACCESSO = 'M' AND
                                  modulo = :NEW.modulo)
                        )
            LOOP
               BEGIN
                   a_istruzione := 'begin UTENTE.AGGIORNA_PWD_DA_MODIFICARE('''||replace(UTEN.Utente,'''','''''')||'''); end;';
                   a_messaggio := '';
                   Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
                EXCEPTION
                   WHEN OTHERS THEN
                      Integritypackage.InitNestLevel;
                      RAISE;
               END;
            END LOOP;
         END IF;
      ELSE -- caratteristiche di un gruppo/utente
         BEGIN
          -- se si stanno inserendo o modificando le caratteristiche di accesso di un
          -- gruppo, inserisce o modifica le stesse anche per tutti gli utenti del
          -- gruppo per cui il diritto di accesso non sia stato personalizzato.
            BEGIN
               SELECT tipo_utente
                 INTO dTipoUtente
                 FROM UTENTI
                WHERE Utente = :NEW.Utente
               ;
               -- Set PostEvent Check REFERENTIAL Integrity
               IF NVL(dTipoUtente, 'U') != 'U' THEN
                  IF UPDATING THEN
                      a_istruzione := 'begin DIRITTO_ACCESSO.AGGIORNA_GRUPPO('''||replace(:NEW.Utente,'''','''''')||''', '''||:NEW.MODULO||''', '''||:NEW.Istanza||'''); end;';
                      a_messaggio := '';
                      Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
                      a_istruzione := 'begin GRUPPO.CAAC_GRUPPO_UPDATE('''||replace(:NEW.Utente,'''','''''')||''', '''||:NEW.MODULO||''', '''||:NEW.Istanza||''', '''||:NEW.PROGETTO||''',''E''); end;';
                      a_messaggio := '';
                      Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
                  END IF;
                  IF INSERTING THEN
                     a_istruzione := 'begin GRUPPO.CAAC_GRUPPO_INSERT('''||replace(:NEW.Utente,'''','''''')||''', ''%'', '''||:NEW.MODULO||''', '''||:NEW.Istanza||''',''E''); end;';
                     a_messaggio := '';
                     Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
                  END IF;
               ELSE
                  a_istruzione := 'begin UTENTE.AGGIORNA_PWD_DA_MODIFICARE('''||replace(:NEW.Utente,'''','''''')||'''); end;';
                  a_messaggio := '';
                  Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
               END IF;
               Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
            EXCEPTION
               WHEN OTHERS THEN
                  Integritypackage.InitNestLevel;
                  RAISE;
            END;
         END;
      END IF;
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


