CREATE OR REPLACE TRIGGER UTENTI_GRUPPO_TIU
/******************************************************************************
 NOME:        UTENTI_GRUPPO_TIU
 DESCRIZIONE: Trigger for Set FUNCTIONAL Integrity
                        Check REFERENTIAL Integrity
                       at DELETE on Table UTENTI_GRUPPO
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
    1 27/05/2020 SNeg   controlli sui gruppi _GR_ non modificabili se non da procedura apposita
******************************************************************************/
BEFORE INSERT OR UPDATE
ON UTENTI_GRUPPO
REFERENCING NEW AS NEW OLD AS OLD
FOR EACH ROW
DECLARE
   -- 18-06-2010 ultima modifica
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
   a_istruzione     VARCHAR2(2000);
   a_messaggio      VARCHAR2(2000);
   a_esiste         NUMBER(1);
BEGIN
   BEGIN  -- Check DATA Integrity on INSERT or UPDATE
      IF :NEW.Utente = :NEW.Gruppo THEN
         RAISE_APPLICATION_ERROR(-20999,'Impossibile inserire il gruppo '''||:NEW.Utente||''' in se stesso.');
      END IF;
      IF UPDATING THEN
         RAISE_APPLICATION_ERROR(-20999, 'Aggiornamento non consentito. Eliminare la registrazione e reinserirla.');
      END IF;
   END;
   -- 27/05/2020 controlli sui gruppi _GR_
--     dbms_output.put_line ('gruppo ' || nvl(:new.gruppo, :old.gruppo));
      if :new.gruppo like '\_GR\_%\_' escape '\' and not utente.get_is_sistemazione_gruppi
      then
         RAISE_APPLICATION_ERROR(-20999, 'Appartenenza ai gruppi standard non modificabile');
      end if;

   BEGIN  -- Check REFERENTIAL Integrity on INSERT or UPDATE
      IF UPDATING THEN
         Utenti_Gruppo_Pu(:OLD.Utente
                       , :OLD.Gruppo
                         , :NEW.Utente
                         , :NEW.Gruppo
                         );
      END IF;
      IF INSERTING THEN
         Utenti_Gruppo_Pi(:NEW.Utente,
                         :NEW.Gruppo);
         IF Integritypackage.GetNestLevel = 0 THEN
            DECLARE  --  Check UNIQUE PK Integrity per la tabella "UTENTI_GRUPPO"
            CURSOR cpk_utenti_gruppo(var_UTENTE VARCHAR,
                            var_GRUPPO VARCHAR) IS
               SELECT 1
                 FROM   UTENTI_GRUPPO
                WHERE  Utente = var_UTENTE AND
                       Gruppo = var_GRUPPO;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on PK of "UTENTI_GRUPPO"
               IF :NEW.Utente IS NOT NULL AND
                  :NEW.Gruppo IS NOT NULL THEN
                  OPEN  cpk_utenti_gruppo(:NEW.Utente,
                                 :NEW.Gruppo);
                  FETCH cpk_utenti_gruppo INTO dummy;
                  FOUND := cpk_utenti_gruppo%FOUND;
                  CLOSE cpk_utenti_gruppo;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.Utente||' '||
                               :NEW.Gruppo||
                               '" gia'' presente in UTENTI_GRUPPO. La registrazione  non puo'' essere inserita.';
                     RAISE integrity_error;
                  END IF;
               END IF;
            EXCEPTION
               WHEN MUTATING THEN NULL;  -- Ignora Check su UNIQUE PK Integrity
            END;
         END IF;
         -- INSERIMENTO DEI DIRITTI E DELLE CARATTERISTICHE DI ACCESSO DEL GRUPPO.
         BEGIN
            a_messaggio := '';
            a_istruzione := 'begin '||
                            '   GRUPPO.DIAC_GRUPPO_INSERT('''||
                                          replace(:NEW.Gruppo,'''','''''')||''', '''||
                                          replace(:NEW.Utente,'''','''''')||
                                          ''', ''%'', ''%'', ''E''); '||
                            '   GRUPPO.CAAC_GRUPPO_INSERT('''||
                                          replace(:NEW.Gruppo,'''','''''')||''', '''||
                                          replace(:NEW.Utente,'''','''''')||
                                          ''', ''%'', ''%'', ''E''); '||
                            'end;';
--DBMS_OUTPUT.PUT_LINE(a_istruzione);
            Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
         EXCEPTION
            WHEN OTHERS THEN
               Integritypackage.InitNestLevel;
               RAISE;
         END;
      END IF;
      DECLARE
         d_tipo_utente VARCHAR2(1);
         d_check NUMBER;
      BEGIN
         SELECT tipo_utente
          INTO d_tipo_utente
          FROM UTENTI
         WHERE Utente = :NEW.Utente
        ;
        IF d_tipo_utente <> 'U' THEN
         -- Se qualcuno dei padri del gruppo in cui si vuole inserire (:NEW.gruppo)
         -- appartiene gia' al gruppo :NEW.utente, non permette l'inserimento per
         -- evitare la ricorsione.
            BEGIN
               d_check := Utente_Gruppo.check_ricorsione(:NEW.Gruppo, :NEW.Utente);
               IF d_check > 0 THEN
                  RAISE_APPLICATION_ERROR(-20999,'Impossibile inserire il Gruppo '''||
                                          :NEW.Utente||''' nel Gruppo '''||:NEW.Gruppo||
                                          '''. Ricorsione sui gruppi non ammessa.');
               END IF;
            END;
         -- Se qualcuno dei padri del gruppo da inserire in :NEW.gruppo appartiene gia'
         -- a :NEW.gruppo, non permette l'inserimento.
            BEGIN
               d_check := Utente_Gruppo.check_ricorsione(:NEW.Utente, :NEW.Gruppo);
               IF d_check > 0 THEN
                  RAISE_APPLICATION_ERROR(-20999,'Il Gruppo '''||:NEW.Utente||
                                          ''' appartiene gia'' al Gruppo '''||:NEW.Gruppo||
                                          ''' IN quanto vi appartiene un suo padre.');
               END IF;
            END;
         END IF;
      END;
      FOR c_diac IN ( SELECT DISTINCT ista.progetto, UPPER(ista.user_oracle) user_oracle
                        FROM DIRITTI_ACCESSO diac, ISTANZE ista
                       WHERE ista.Istanza = diac.Istanza
                         AND diac.Utente = :NEW.Gruppo)
      LOOP
         SELECT COUNT(1)
           INTO a_esiste
           FROM USER_TAB_PRIVS
          WHERE table_name = c_diac.progetto||'_AD4'
            AND grantee IN (user,'PUBLIC')
            AND owner = c_diac.user_oracle
         ;
         IF a_esiste > 0 THEN
            a_messaggio := '';
            a_istruzione := 'begin '||c_diac.user_oracle||'.'||c_diac.progetto||
                            '_AD4.UTENTE_GRUPPO('''||replace(:NEW.Gruppo,'''','''''')||
                            ''', '''||replace(:NEW.Utente,'''','''''')||''','''||
                            replace(:OLD.Gruppo,'''','''''')||''', '''||
                            replace(:OLD.Utente,'''','''''')||'''); end;';
            BEGIN
               Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
            EXCEPTION
               WHEN OTHERS THEN
                  Integritypackage.InitNestLevel;
                  RAISE;
            END;
         END IF;
      END LOOP;
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


