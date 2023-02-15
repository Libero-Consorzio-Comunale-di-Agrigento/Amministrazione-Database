CREATE OR REPLACE TRIGGER COMUNI_TIU
/******************************************************************************
 NOME:        COMUNI_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                            Set REFERENTIAL Integrity
                            Set FUNCTIONAL Integrity
                       at INSERT or UPDATE on Table COMUNI
 ECCEZIONI:   -20007, Identificazione CHIAVE presente in TABLE
 ANNOTAZIONI: Richiama Procedure COMUNI_PI e COMUNI_PU
              Salvato nella directory ins di AD4 con nome comu_tiu.trg.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 24/07/2002 MM     Gestione utente_aggiornamento data_aggiornamento.
    1 22/05/2003 MM     Sostituzione user con substr(user,1,8).
    2 25/11/2013 SN     Forzato aggiornamento data di modifica
******************************************************************************/
   BEFORE INSERT OR UPDATE ON COMUNI
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
         Comuni_Pu(:OLD.PROVINCIA_STATO,
                   :OLD.COMUNE,
                   :OLD.PROVINCIA_TRIBUNALE,
                   :OLD.COMUNE_TRIBUNALE,
                   :OLD.PROVINCIA_DISTRETTO,
                   :OLD.COMUNE_DISTRETTO,
                   :OLD.PROVINCIA_FUSIONE,
                   :OLD.COMUNE_FUSIONE,
                   :OLD.CONSOLATO,
                   :OLD.TIPO_CONSOLATO,
                   :NEW.PROVINCIA_STATO,
                   :NEW.COMUNE,
                   :NEW.PROVINCIA_TRIBUNALE,
                   :NEW.COMUNE_TRIBUNALE,
                   :NEW.PROVINCIA_DISTRETTO,
                   :NEW.COMUNE_DISTRETTO,
                   :NEW.PROVINCIA_FUSIONE,
                   :NEW.COMUNE_FUSIONE,
                   :NEW.CONSOLATO,
                   :NEW.TIPO_CONSOLATO);
         NULL;
      END IF;
      IF INSERTING THEN
         Comuni_Pi(:NEW.PROVINCIA_STATO,
                   :NEW.PROVINCIA_TRIBUNALE,
                   :NEW.COMUNE_TRIBUNALE,
                   :NEW.PROVINCIA_DISTRETTO,
                   :NEW.COMUNE_DISTRETTO,
                   :NEW.PROVINCIA_FUSIONE,
                   :NEW.COMUNE_FUSIONE,
                   :NEW.CONSOLATO,
                   :NEW.TIPO_CONSOLATO);
         IF Integritypackage.GetNestLevel = 0 THEN
            DECLARE  --  Check UNIQUE PK Integrity per la tabella "COMUNI"
            CURSOR cpk_comuni(var_PROVINCIA_STATO NUMBER,
                              var_COMUNE NUMBER) IS
               SELECT 1
                 FROM   COMUNI
                WHERE  PROVINCIA_STATO = var_PROVINCIA_STATO AND
                       Comune = var_COMUNE;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on PK of "COMUNI"
               IF :NEW.PROVINCIA_STATO IS NOT NULL AND
                  :NEW.Comune IS NOT NULL THEN
                  OPEN  cpk_comuni(:NEW.PROVINCIA_STATO,
                                   :NEW.Comune);
                  FETCH cpk_comuni INTO dummy;
                  FOUND := cpk_comuni%FOUND;
                  CLOSE cpk_comuni;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.PROVINCIA_STATO||' '||
                               :NEW.Comune||
                               '" gia'' presente in Comuni. La registrazione  non puo'' essere inserita.';
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
      IF UPDATING then
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
      BEGIN
         IF UPDATING AND (:OLD.PROVINCIA_STATO != :NEW.PROVINCIA_STATO or :OLD.COMUNE != :NEW.COMUNE) then
            DECLARE
               a_istruzione     VARCHAR2(2000);
               a_messaggio      VARCHAR2(2000);
            BEGIN
               a_messaggio := '';
               a_istruzione := 'begin '||
                               '   COMUNI_RRI ('||:OLD.PROVINCIA_STATO||
                               '              , '||:OLD.COMUNE||
                               '              , '||:NEW.PROVINCIA_STATO||
                               '              , '||:NEW.COMUNE||'); '||
                               'end;';
               Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
            EXCEPTION
               WHEN OTHERS THEN
                  Integritypackage.InitNestLevel;
                  RAISE;
            END;
         END IF;
         /* NONE */ NULL;
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


