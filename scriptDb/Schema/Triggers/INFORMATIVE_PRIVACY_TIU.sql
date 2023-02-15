CREATE OR REPLACE TRIGGER INFORMATIVE_PRIVACY_TIU
/******************************************************************************
 NOME:        INFORMATIVE_PRIVACY_TIU
 DESCRIZIONE: Trigger for Set DATA Integrity
                          Set FUNCTIONAL Integrity
                       on Table EVENTI
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 07/03/2018 AD     Creazione
******************************************************************************/
   BEFORE INSERT OR UPDATE ON INFORMATIVE_PRIVACY
FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   FOUND            BOOLEAN;
BEGIN
   BEGIN  -- Set DATA Integrity
      /* NONE */ NULL;
      -- Rev. 1 06/06/2007 MM     Gestione della sequence EVEN_SQ
      if :NEW.ID IS NULL and not DELETING then
         :new.ID := SI4.NEXT_ID('INFORMATIVE_PRIVACY');
      end if;
      -- Rev. 1 06/06/2007 MM fine mod.
   END;
  BEGIN  -- Set FUNCTIONAL Integrity
      IF Integritypackage.GetNestLevel = 0 THEN
         Integritypackage.NextNestLevel;
         BEGIN  -- Global FUNCTIONAL Integrity at Level 0
            null;
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


