CREATE OR REPLACE TRIGGER UTENTI_TD
/******************************************************************************
 NOME:        UTENTI_TD
 DESCRIZIONE: Trigger for Set FUNCTIONAL Integrity
                        Check REFERENTIAL Integrity
                       at DELETE on Table UTENTI
 ANNOTAZIONI: Richiama Procedure UTENTI_TD
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
    1 29/11/2006 MM
******************************************************************************/
   BEFORE DELETE ON UTENTI
FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
BEGIN
   --raise_application_error(-20999,'ecco');
   BEGIN -- Set FUNCTIONAL Integrity on DELETE
      IF Integritypackage.GetNestLevel = 0 THEN
         Integritypackage.NextNestLevel;
         BEGIN  -- Global FUNCTIONAL Integrity at Level 0
            NULL;
            IF :OLD.tipo_utente = 'O' THEN
               RAISE_APPLICATION_ERROR(-20999, 'Gruppo '''||:old.UTENTE||''' corrispondente ad elemento di Struttura Organizzativa (unita''o ruolo). Eliminazione non consentita.');
            END IF;
         END;
         Integritypackage.PreviousNestLevel;
      END IF;
   END;
   BEGIN  -- Check REFERENTIAL Integrity on DELETE
      Utenti_Pd(:OLD.Utente);
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


