CREATE OR REPLACE PROCEDURE Ruoli_Pd
/******************************************************************************
 NOME:        RUOLI_PD
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at DELETE on Table RUOLI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20006, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger RUOLI_TD
              Salvata nella directory ins di AD4 con nome RUOL_PD.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(old_ruolo IN VARCHAR)
IS
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
   --  Declaration of DeleteParentRestrict constraint for "DIRITTI_ACCESSO"
   CURSOR cfk1_diritti_accesso(var_ruolo VARCHAR) IS
      SELECT 1
      FROM   DIRITTI_ACCESSO
      WHERE  RUOLO = var_ruolo
       AND   var_ruolo IS NOT NULL;
   --  Declaration of DeleteParentRestrict constraint for "DISABILITAZIONI"
   CURSOR cfk2_disabilitazioni(var_ruolo VARCHAR) IS
      SELECT 1
      FROM   DISABILITAZIONI
      WHERE  RUOLO = var_ruolo
       AND   var_ruolo IS NOT NULL;
   --  Declaration of DeleteParentRestrict constraint for "UTENTI"
   CURSOR cfk3_utenti(var_ruolo VARCHAR) IS
      SELECT 1
      FROM   UTENTI
      WHERE  gruppo_lavoro = var_ruolo
       AND   var_ruolo IS NOT NULL;
BEGIN
   BEGIN  -- Check REFERENTIAL Integrity
      --  Cannot delete parent "RUOLI" if children still exist in "DIRITTI_ACCESSO"
      OPEN  cfk1_diritti_accesso(OLD_RUOLO);
      FETCH cfk1_diritti_accesso INTO dummy;
      FOUND := cfk1_diritti_accesso%FOUND; /* %FOUND */
      CLOSE cfk1_diritti_accesso;
      IF FOUND THEN
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Diritti Accesso. La registrazione di Ruoli non e'' eliminabile.';
          RAISE integrity_error;
      END IF;
      --  Cannot delete parent "RUOLI" if children still exist in "DISABILITAZIONI"
      OPEN  cfk2_disabilitazioni(OLD_RUOLO);
      FETCH cfk2_disabilitazioni INTO dummy;
      FOUND := cfk2_disabilitazioni%FOUND; /* %FOUND */
      CLOSE cfk2_disabilitazioni;
      IF FOUND THEN
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Disabilitazioni. La registrazione di Ruoli non e'' eliminabile.';
          RAISE integrity_error;
      END IF;
      --  Cannot delete parent "RUOLI" if children still exist in "UTENTI"
      OPEN  cfk3_utenti(OLD_RUOLO);
      FETCH cfk3_utenti INTO dummy;
      FOUND := cfk3_utenti%FOUND; /* %FOUND */
      CLOSE cfk3_utenti;
      IF FOUND THEN
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Utenti e/o Gruppi. La registrazione di Ruoli non e'' eliminabile.';
          RAISE integrity_error;
      END IF;
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

