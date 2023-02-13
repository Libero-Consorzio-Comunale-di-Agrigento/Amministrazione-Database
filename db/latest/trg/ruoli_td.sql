--liquibase formatted sql

--changeset mturra:201901301231_411 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER RUOLI_TD
   BEFORE DELETE ON RUOLI
FOR EACH ROW
/******************************************************************************
 NOME:        RUOLI_TD
 DESCRIZIONE: Trigger for Set FUNCTIONAL Integrity
                        Check REFERENTIAL Integrity
                       at DELETE on Table RUOLI
 ANNOTAZIONI: Richiama Procedure RUOLI_TD
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
      Ruoli_Pd(:OLD.RUOLO);
   END;
   IF :OLD.RUOLO = '*' THEN
      RAISE_APPLICATION_ERROR(-20999,'Impossibile eliminare il ruolo ''*''!');
   END IF;
EXCEPTION
   WHEN integrity_error THEN
        Integritypackage.InitNestLevel;
        RAISE_APPLICATION_ERROR(errno, errmsg);
   WHEN OTHERS THEN
        Integritypackage.InitNestLevel;
        RAISE;
END;

/

