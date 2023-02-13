--liquibase formatted sql

--changeset mturra:201901301231_403 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER PROVINCE_TIU
   BEFORE INSERT OR UPDATE ON PROVINCE
FOR EACH ROW
/******************************************************************************
 NOME:        PROVINCE_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                            Set REFERENTIAL Integrity
                            Set FUNCTIONAL Integrity
                       at INSERT or UPDATE on Table PROVINCE
 ECCEZIONI:   -20007, Identificazione CHIAVE presente in TABLE
 ANNOTAZIONI: Richiama Procedure PROVINCE_PI e PROVINCE_PU
              Salvato nella directory ins di AD4 con nome prov_tiu.trg.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 24/07/2002 MM     Gestione utente_aggiornamento data_aggiornamento.
    1 22/05/2003 MM     Sostituzione user con substr(user,1,8).
    2 25/11/2013 SN     Forzato aggiornamento data di modifica
******************************************************************************/
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
BEGIN
   BEGIN  -- Check DATA Integrity on INSERT or UPDATE
      /* NONE */ NULL;
   END;
   BEGIN  -- Check REFERENTIAL Integrity on INSERT or UPDATE
      IF UPDATING THEN
         Province_Pu(:OLD.Provincia,
                     :OLD.Regione,
                         :NEW.Provincia,
                         :NEW.Regione);
         NULL;
      END IF;
      IF INSERTING THEN
         Province_Pi(:NEW.Regione);
         IF Integritypackage.GetNestLevel = 0 THEN
            DECLARE  --  Check UNIQUE PK Integrity per la tabella "PROVINCE"
            CURSOR cpk_province(var_PROVINCIA NUMBER) IS
               SELECT 1
                 FROM   PROVINCE
                WHERE  Provincia = var_PROVINCIA;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on PK of "PROVINCE"
               IF :NEW.Provincia IS NOT NULL THEN
                  OPEN  cpk_province(:NEW.Provincia);
                  FETCH cpk_province INTO dummy;
                  FOUND := cpk_province%FOUND;
                  CLOSE cpk_province;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.Provincia||
                               '" gia'' presente in Provincia. La registrazione  non puo'' essere inserita.';
                     RAISE integrity_error;
                  END IF;
               END IF;
            EXCEPTION
               WHEN MUTATING THEN NULL;  -- Ignora Check su UNIQUE PK Integrity
            END;
         END IF;
      END IF;
   END;
   BEGIN  -- Set REFERENTIAL Integrity on UPDATE
      IF UPDATING THEN
         Integritypackage.NextNestLevel;
         Integritypackage.PreviousNestLevel;
      END IF;
   END;
   BEGIN  -- Set FUNCTIONAL Integrity on INSERT or UPDATE
      IF Integritypackage.GetNestLevel = 0 THEN
         Integritypackage.NextNestLevel;
         BEGIN  -- Global FUNCTIONAL Integrity at Level 0
            IF :NEW.utente_aggiornamento IS NULL AND Si4.Utente IS NOT NULL THEN
               :NEW.utente_aggiornamento := Si4.Utente;
            END IF;
            IF nvl(:NEW.data_aggiornamento ,trunc(SYSDATE) ) < SYSDATE THEN
               :NEW.data_aggiornamento := SYSDATE;
            END IF;
         END;
         IF Integritypackage.Functional THEN
            BEGIN  -- Switched FUNCTIONAL Integrity at Level 0
               /* NONE */ NULL;
            END;
         END IF;
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

