CREATE OR REPLACE TRIGGER RICHIESTE_ABILITAZIONE_TIU
/******************************************************************************
 NOME:        RICHIESTE_ABILITAZIONE_TIU
 DESCRIZIONE: Trigger for Set DATA Integrity
                          Set FUNCTIONAL Integrity
                       on Table RICHIESTE_ABILITAZIONE
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    1 11/10/2001 MM     CREAZIONE.
******************************************************************************/
   BEFORE INSERT OR UPDATE ON RICHIESTE_ABILITAZIONE
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
            IF :NEW.DATA IS NULL THEN
               :NEW.DATA := SYSDATE;
            END IF;
            --  Column "ID_RICHIESTA" attribuisce MAX+1
            IF :NEW.ID_RICHIESTA IS NULL THEN
               :NEW.ID_RICHIESTA := Si4.NEXT_ID('RICHIESTE_ABILITAZIONE','ID_RICHIESTA');
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


