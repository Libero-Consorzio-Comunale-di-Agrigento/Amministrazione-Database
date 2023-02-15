CREATE OR REPLACE PROCEDURE Moduli_Pd
/******************************************************************************
 NOME:        MODULI_PD
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at DELETE on Table MODULI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20006, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger MODULI_TD
              Salvata nella directory ins di AD4 con nome MODU_PD.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    1 29/12/2006 MM     Creazione.
******************************************************************************/
(old_modulo IN VARCHAR)
IS
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
   --  Declaration of DeleteParentRestrict constraint for "DIRITTI_ACCESSO"
   CURSOR cfk1_diritti_accesso(var_modulo VARCHAR) IS
      SELECT 1
      FROM   DIRITTI_ACCESSO
      WHERE  modulo = var_modulo
       AND   var_modulo IS NOT NULL;
   --  Declaration of DeleteParentRestrict constraint for "CARATTERISTICHE_ACCESSO"
   CURSOR cfk2_caratteristiche_accesso(var_modulo VARCHAR) IS
      SELECT 1
      FROM   CARATTERISTICHE_ACCESSO
      WHERE  modulo = var_modulo
       AND   var_modulo IS NOT NULL;
   --  Declaration of DeleteParentRestrict constraint for "SERVIZI"
   CURSOR cfk3_servizi(var_modulo VARCHAR) IS
      SELECT 1
      FROM   SERVIZI
      WHERE  modulo = var_modulo
       AND   var_modulo IS NOT NULL;
   --  Declaration of DeleteParentRestrict constraint for "DISABILITAZIONI"
   CURSOR cfk4_disabilitazioni(var_modulo VARCHAR) IS
      SELECT 1
      FROM   DISABILITAZIONI
      WHERE  modulo = var_modulo
       AND   var_modulo IS NOT NULL;
   --  Declaration of DeleteParentRestrict constraint for "PERSONALIZZAZIONI"
   CURSOR cfk5_personalizzazioni(var_modulo VARCHAR) IS
      SELECT 1
      FROM   PERSONALIZZAZIONI
      WHERE  modulo = var_modulo
       AND   var_modulo IS NOT NULL;
   --  Declaration of DeleteParentRestrict constraint for "RUOLI"
   CURSOR cfk6_ruoli(var_modulo VARCHAR) IS
      SELECT 1
      FROM   RUOLI
      WHERE  modulo = var_modulo
       AND   var_modulo IS NOT NULL;
BEGIN
   BEGIN  -- Check REFERENTIAL Integrity
      --  Cannot delete parent "MODULI" if children still exist in "DIRITTI_ACCESSO"
      OPEN  cfk1_diritti_accesso(OLD_MODULO);
      FETCH cfk1_diritti_accesso INTO dummy;
      FOUND := cfk1_diritti_accesso%FOUND; /* %FOUND */
      CLOSE cfk1_diritti_accesso;
      IF FOUND THEN
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Diritti Accesso. La registrazione di Moduli non e'' eliminabile.';
          RAISE integrity_error;
      END IF;
      --  Cannot delete parent "MODULI" if children still exist in "CARATTERISTICHE_ACCESSO"
      OPEN  cfk2_caratteristiche_accesso(OLD_MODULO);
      FETCH cfk2_caratteristiche_accesso INTO dummy;
      FOUND := cfk2_caratteristiche_accesso%FOUND; /* %FOUND */
      CLOSE cfk2_caratteristiche_accesso;
      IF FOUND THEN
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Caratteristiche Accesso. La registrazione di Moduli non e'' eliminabile.';
          RAISE integrity_error;
      END IF;
      --  Cannot delete parent "MODULI" if children still exist in "SERVIZI"
      OPEN  cfk3_servizi(OLD_MODULO);
      FETCH cfk3_servizi INTO dummy;
      FOUND := cfk3_servizi%FOUND; /* %FOUND */
      CLOSE cfk3_servizi;
      IF FOUND THEN
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Servizi. La registrazione di Moduli non e'' eliminabile.';
          RAISE integrity_error;
      END IF;
      --  Cannot delete parent "MODULI" if children still exist in "DISABILITAZIONI"
      OPEN  cfk4_disabilitazioni(OLD_MODULO);
      FETCH cfk4_disabilitazioni INTO dummy;
      FOUND := cfk4_disabilitazioni%FOUND; /* %FOUND */
      CLOSE cfk4_disabilitazioni;
      IF FOUND THEN
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Disabilitazioni. La registrazione di Moduli non e'' eliminabile.';
          RAISE integrity_error;
      END IF;
      --  Cannot delete parent "MODULI" if children still exist in "PERSONALIZZAZIONI"
      OPEN  cfk5_personalizzazioni(OLD_MODULO);
      FETCH cfk5_personalizzazioni INTO dummy;
      FOUND := cfk5_personalizzazioni%FOUND; /* %FOUND */
      CLOSE cfk5_personalizzazioni;
      IF FOUND THEN
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Personalizzazioni. La registrazione di Moduli non e'' eliminabile.';
          RAISE integrity_error;
      END IF;
      --  Cannot delete parent "MODULI" if children still exist in "RUOLI"
      OPEN  cfk6_ruoli(OLD_MODULO);
      FETCH cfk6_ruoli INTO dummy;
      FOUND := cfk6_ruoli%FOUND; /* %FOUND */
      CLOSE cfk6_ruoli;
      IF FOUND THEN
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Ruoli. La registrazione di Moduli non e'' eliminabile.';
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

