--liquibase formatted sql

--changeset mturra:201901301231_380 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER CONSOLATI_TIU
/******************************************************************************
 NOME:        CONSOLATI_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                            Set REFERENTIAL Integrity
                            Set FUNCTIONAL Integrity
                       at INSERT or UPDATE on Table CONSOLATI
 ECCEZIONI:   -20007, Identificazione CHIAVE presente in TABLE
 ANNOTAZIONI: Richiama Procedure CONSOLATI_PU
              Salvato nella directory ins di AD4 con nome cons_tiu.trg.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 24/07/2002 MM     Gestione utente_aggiornamento data_aggiornamento.
    1 22/05/2003 MM     Sostituzione user con substr(user,1,8).
    2 25/11/2013 SN     Forzato aggiornamento data di modifica
******************************************************************************/
   BEFORE INSERT OR UPDATE ON CONSOLATI
FOR EACH ROW
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
         Consolati_Pu(:OLD.CONSOLATO,
                      :OLD.TIPO_CONSOLATO,
                      :OLD.STATO,
                         :NEW.CONSOLATO,
                         :NEW.TIPO_CONSOLATO,
                         :NEW.STATO);
         NULL;
      END IF;
      IF INSERTING THEN
         IF Integritypackage.GetNestLevel = 0 THEN
            DECLARE  --  Check UNIQUE PK Integrity per la tabella "CONSOLATI"
            CURSOR cpk_consolati(var_CONSOLATO NUMBER,
                                 var_TIPO_CONSOLATO NUMBER,
                                 var_STATO NUMBER) IS
               SELECT 1
                 FROM   CONSOLATI
                WHERE  CONSOLATO = var_CONSOLATO AND
                       TIPO_CONSOLATO = var_TIPO_CONSOLATO AND
                       STATO = var_STATO;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on PK of "CONSOLATI"
               IF :NEW.CONSOLATO IS NOT NULL AND
                  :NEW.TIPO_CONSOLATO IS NOT NULL AND
                  :NEW.STATO IS NOT NULL THEN
                  OPEN  cpk_consolati(:NEW.CONSOLATO,
                                      :NEW.TIPO_CONSOLATO,
                                      :NEW.STATO);
                  FETCH cpk_consolati INTO dummy;
                  FOUND := cpk_consolati%FOUND;
                  CLOSE cpk_consolati;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.CONSOLATO||' '||
                               :NEW.TIPO_CONSOLATO||' '||
                               :NEW.STATO||
                               '" gia'' presente in Consolati. La registrazione  non puo'' essere inserita.';
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

