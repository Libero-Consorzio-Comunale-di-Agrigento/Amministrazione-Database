CREATE OR REPLACE PROCEDURE Istanze_Pd
/******************************************************************************
 NOME:        ISTANZE_PD
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at DELETE on Table MODULI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20006, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger MODULI_TD
              Salvata nella directory ins di AD4 con nome ISTA_PD.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    1 29/12/2006 MM     Creazione.
******************************************************************************/
(old_istanza IN VARCHAR)
IS
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
   --  Declaration of DeleteParentRestrict constraint for "DIRITTI_ACCESSO"
   CURSOR cfk1_diritti_accesso(var_istanza VARCHAR) IS
      SELECT 1
      FROM   DIRITTI_ACCESSO
      WHERE  Istanza = var_istanza
       AND   var_istanza IS NOT NULL;
   --  Declaration of DeleteParentRestrict constraint for "CARATTERISTICHE_ACCESSO"
   CURSOR cfk2_caratteristiche_accesso(var_istanza VARCHAR) IS
      SELECT 1
      FROM   CARATTERISTICHE_ACCESSO
      WHERE  Istanza = var_istanza
       AND   var_istanza IS NOT NULL;
BEGIN
   BEGIN  -- Check REFERENTIAL Integrity
      --  Cannot delete parent "ISTANZE" if children still exist in "DIRITTI_ACCESSO"
      OPEN  cfk1_diritti_accesso(OLD_ISTANZA);
      FETCH cfk1_diritti_accesso INTO dummy;
      FOUND := cfk1_diritti_accesso%FOUND; /* %FOUND */
      CLOSE cfk1_diritti_accesso;
      IF FOUND THEN
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Diritti Accesso. La registrazione di Istanze non e'' eliminabile.';
          RAISE integrity_error;
      END IF;
      --  Cannot delete parent "ISTANZE" if children still exist in "CARATTERISTICHE_ACCESSO"
      OPEN  cfk2_caratteristiche_accesso(OLD_ISTANZA);
      FETCH cfk2_caratteristiche_accesso INTO dummy;
      FOUND := cfk2_caratteristiche_accesso%FOUND; /* %FOUND */
      CLOSE cfk2_caratteristiche_accesso;
      IF FOUND THEN
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Caratteristiche Accesso. La registrazione di Istanze non e'' eliminabile.';
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

