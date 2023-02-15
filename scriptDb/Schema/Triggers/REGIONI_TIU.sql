CREATE OR REPLACE TRIGGER REGIONI_TIU
/******************************************************************************
 NOME:        REGIONI_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                            Set REFERENTIAL Integrity
                            Set FUNCTIONAL Integrity
                       at INSERT or UPDATE on Table REGIONI
 ECCEZIONI:   -20007, Identificazione CHIAVE presente in TABLE
 ANNOTAZIONI: Richiama Procedure REGIONI_PU
              Salvato nella directory ins di AD4 con nome regi_tiu.trg.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 24/07/2002 MM     Gestione utente_aggiornamento data_aggiornamento.
    1 22/05/2003 MM     Sostituzione user con substr(user,1,8).
    2 25/11/2013 SN     Forzato aggiornamento data di modifica
******************************************************************************/
   BEFORE INSERT OR UPDATE ON REGIONI
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
         Regioni_Pu(:OLD.Regione,
                         :NEW.Regione);
         NULL;
      END IF;
      IF INSERTING THEN
         IF Integritypackage.GetNestLevel = 0 THEN
            DECLARE  --  Check UNIQUE PK Integrity per la tabella "REGIONI"
            CURSOR cpk_regioni(var_REGIONE NUMBER) IS
               SELECT 1
                 FROM   REGIONI
                WHERE  Regione = var_REGIONE;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on PK of "REGIONI"
               IF :NEW.Regione IS NOT NULL THEN
                  OPEN  cpk_regioni(:NEW.Regione);
                  FETCH cpk_regioni INTO dummy;
                  FOUND := cpk_regioni%FOUND;
                  CLOSE cpk_regioni;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.Regione||
                               '" gia'' presente in Regioni. La registrazione  non puo'' essere inserita.';
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


