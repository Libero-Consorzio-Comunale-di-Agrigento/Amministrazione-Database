CREATE OR REPLACE TRIGGER SERVIZI_TIU
/******************************************************************************
 NOME:        SERVIZI_TIU
 DESCRIZIONE: Trigger for Set DATA Integrity
                          Set FUNCTIONAL Integrity
                       on Table SERVIZI
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    1 11/10/2001 MM     CREAZIONE.
******************************************************************************/
   BEFORE INSERT OR UPDATE ON SERVIZI
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
            --  Column "ID_SERVIZIO" attribuisce MAX+1
            IF :NEW.ID_SERVIZIO IS NULL THEN
               :NEW.ID_SERVIZIO := Si4.NEXT_ID('SERVIZI','ID_SERVIZIO');
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


