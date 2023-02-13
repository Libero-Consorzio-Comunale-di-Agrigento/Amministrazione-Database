--liquibase formatted sql

--changeset mturra:201901301231_371 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER BANCHE_TD
   BEFORE DELETE ON BANCHE
FOR EACH ROW
/******************************************************************************
 NOME:        BANCHE_TD
 DESCRIZIONE: Trigger for Set FUNCTIONAL Integrity
                        Check REFERENTIAL Integrity
                       at DELETE on Table BANCHE
 ANNOTAZIONI: Richiama Procedure BANCHE_TD
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
******************************************************************************/
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
BEGIN
   BEGIN -- Set FUNCTIONAL Integrity on DELETE
      IF Integritypackage.GetNestLevel = 0 THEN
         Integritypackage.NextNestLevel;
         BEGIN  -- Global FUNCTIONAL Integrity at Level 0
            /* NONE */ NULL;
         END;
         Integritypackage.PreviousNestLevel;
      END IF;
   END;
   BEGIN  -- Check REFERENTIAL Integrity on DELETE
      Banche_Pd(:OLD.ABI);
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

