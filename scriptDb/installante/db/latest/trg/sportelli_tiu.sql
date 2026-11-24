--liquibase formatted sql

--changeset mturra:201901301231_421 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER SPORTELLI_TIU
/******************************************************************************
 NOME:        SPORTELLI_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                       at INSERT or UPDATE on Table SPORTELLI
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    1 04/02/2013 SNeg   tolta verifica sul livello = 0, devono sempre essere fatti
                        a qualsiasi livello i controlli.
******************************************************************************/
BEFORE INSERT
    OR UPDATE
ON SPORTELLI
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
   BEGIN  -- Check REFERENTIAL Integrity at Level 0
      --IF Integritypackage.GetNestLevel = 0 THEN
      -- modifica rev.1 sempre fare i controlli
    IF INSERTING THEN
            DECLARE  --  Check UNIQUE PK Integrity per la tabella "SPORTELLI"
            CURSOR cpk_sportelli(var_ABI VARCHAR,
                                 var_CAB VARCHAR) IS
               SELECT 1
                 FROM   SPORTELLI
                WHERE  ABI = var_ABI AND
                       CAB = var_CAB;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on PK of "SPORTELLI"
               IF :NEW.ABI IS NOT NULL AND
                  :NEW.CAB IS NOT NULL THEN
                  OPEN  cpk_sportelli(:NEW.ABI,
                                      :NEW.CAB);
                  FETCH cpk_sportelli INTO dummy;
                  FOUND := cpk_sportelli%FOUND;
                  CLOSE cpk_sportelli;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.ABI||' '||
                               :NEW.CAB||
                               '" gia'' presente in SPORTELLI. La registrazione  non puo'' essere inserita.';
                     RAISE integrity_error;
                  END IF;
               END IF;
            EXCEPTION
               WHEN MUTATING THEN NULL;  -- Ignora Check su UNIQUE PK Integrity
            END;
            Sportelli_Pi(:NEW.ABI);
         END IF;
         IF UPDATING THEN
            Sportelli_Pu(:OLD.ABI,
                         :OLD.CAB,
                          :NEW.ABI,
                          :NEW.CAB);
            NULL;
         END IF;
    --  END IF; -- rev.1
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
         BEGIN  -- Global FUNCTIONAL Integrity
            /* NONE */ NULL;
         END;
         IF Integritypackage.Functional THEN
            BEGIN  -- Switched FUNCTIONAL Integrity
               /* NONE */ NULL;
            END;
         END IF;
         Integritypackage.PreviousNestLevel;
      END IF;
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

