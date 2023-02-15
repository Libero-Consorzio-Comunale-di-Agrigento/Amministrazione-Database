CREATE OR REPLACE TRIGGER CREDENZIALI_TIU
/******************************************************************************
 NOME:        CREDENZIALI_TIU
 DESCRIZIONE: Trigger for Set DATA Integrity
                          Set FUNCTIONAL Integrity
                       on Table CREDENZIALI
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    1 11/10/2001 MM     CREAZIONE.
******************************************************************************/
   BEFORE INSERT OR UPDATE ON CREDENZIALI
FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   FOUND            BOOLEAN;
BEGIN
   BEGIN  -- Set DATA Integrity
      /* NONE */ NULL;
   END;
  BEGIN  -- Set FUNCTIONAL Integrity
      IF Integritypackage.GetNestLevel = 0 THEN
         Integritypackage.NextNestLevel;
         BEGIN  -- Global FUNCTIONAL Integrity at Level 0
            -- Inizializzazione campi 'UTENTE_AGGIORNAMENTO' e 'DATA_AGGIORNAMENTO'
            IF  Si4.Utente IS NOT NULL
            AND NVL(:NEW.utente_aggiornamento,' ') = NVL(:OLD.utente_aggiornamento,' ') THEN
               :NEW.utente_aggiornamento := Si4.Utente;
            END IF;
            IF :NEW.data_aggiornamento IS NULL
            OR :NEW.data_aggiornamento = :OLD.data_aggiornamento THEN
               :NEW.data_aggiornamento := SYSDATE;
            END IF;
            --  Column "ID_CREDENZIALE" attribuisce MAX+1
            IF :NEW.ID_CREDENZIALE IS NULL THEN
               :NEW.ID_CREDENZIALE := Si4.NEXT_ID('CREDENZIALI','ID_CREDENZIALE');
            END IF;
         END;
         Integritypackage.PreviousNestLevel;
      END IF;
      Integritypackage.NextNestLevel;
      BEGIN  -- Full FUNCTIONAL Integrity at Any Level
         /* NONE */ NULL;
      END;
      Integritypackage.PreviousNestLevel;
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


