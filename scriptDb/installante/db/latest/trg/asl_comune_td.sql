--liquibase formatted sql

--changeset snegroni:202002181535 runOnChange:true stripComments:false


create or replace TRIGGER ASL_COMUNE_TD
   BEFORE DELETE ON ASL_COMUNE
FOR EACH ROW
/******************************************************************************
 NOME:        ASL_COMUNE_TD
 DESCRIZIONE: Trigger for Set FUNCTIONAL Integrity
                        Check REFERENTIAL Integrity
                       at DELETE on Table ASL_COMUNE
 ANNOTAZIONI: Richiama Procedure ASL_COMUNE_TD
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
    0 22/05/2002 MM     Aggiunto lancio in POSTEVENT di ASL_COMUNE_RRI.
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
      Asl_Comune_Pd(:OLD.Provincia,
                    :OLD.Comune,
                    :OLD.REGIONE_ASL,
                    :OLD.CODICE_ASL);
   END;
   BEGIN  -- Set PostEvent Check REFERENTIAL Integrity
      DECLARE a_istruzione  VARCHAR2(2000);
              a_messaggio   VARCHAR2(2000);
      BEGIN
         a_messaggio := '';
         a_istruzione := 'begin ASL_COMUNE_DEL_RRI ('||:OLD.regione_asl||','||:OLD.codice_asl||'); end;';
         Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
      EXCEPTION
         WHEN OTHERS THEN
           Integritypackage.InitNestLevel;
           RAISE;
      END;
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