--liquibase formatted sql

--changeset mturra:201901301231_375 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER CARATTERISTICHE_ACCESSO_TD
   BEFORE DELETE ON CARATTERISTICHE_ACCESSO
FOR EACH ROW
/******************************************************************************
 NOME:        CARATTERISTICHE_ACCESSO_TD
 DESCRIZIONE: Trigger for Set FUNCTIONAL Integrity
                        Check REFERENTIAL Integrity
                       at DELETE on Table CARATTERISTICHE_ACCESSO
 ANNOTAZIONI: Richiama Procedure CARATTERISTICHE_ACCESSO_TD
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
 1    03/01/2003 MM     GESTIONE DELETE caratteristiche DEL GRUPPO IN POST.
 2    03/11/2004 VA     Gestione tipo_utente=O (Organizzazione).
******************************************************************************/
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
   dTipoUtente      VARCHAR2(1);
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
      /* NONE */ NULL;
   END;
   BEGIN  -- Set PostEvent Check REFERENTIAL Integrity
      -- se si stanno eliminando le caratteristiche di accesso di un gruppo,
      -- elimina le stesse anche da tutti gli utenti del gruppo per cui il
     -- diritto di accesso non sia stato personalizzato.
      SELECT tipo_utente
        INTO dTipoUtente
        FROM UTENTI
       WHERE Utente = :OLD.Utente
      ;
      DECLARE a_istruzione  VARCHAR2(2000);
              a_messaggio   VARCHAR2(2000);
      BEGIN
         BEGIN
            a_messaggio := '';
   -- 03/11/2004
   --      if nvl(dTipoUtente, 'U') = 'G' then
            IF NVL(dTipoUtente, 'U') != 'U' THEN
               a_istruzione := 'begin GRUPPO.CAAC_GRUPPO_DELETE('''||replace(:OLD.Utente,'''','''''')||''', ''%'', '''||:OLD.MODULO||''', '''||:OLD.Istanza||''', ''E''); end;';
            ELSE
               a_istruzione := 'begin UTENTE.AGGIORNA_PWD_DA_MODIFICARE('''||replace(:OLD.Utente,'''','''''')||'''); end;';
            END IF;
            Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
         EXCEPTION
            WHEN OTHERS THEN
               Integritypackage.InitNestLevel;
               RAISE;
         END;
      END;
   EXCEPTION WHEN NO_DATA_FOUND THEN
     NULL;
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

