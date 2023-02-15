CREATE OR REPLACE TRIGGER RUOLI_TIU
/******************************************************************************
 NOME:        RUOLI_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                       at INSERT or UPDATE on Table RUOLI
 ECCEZIONI:   -20007, Identificazione CHIAVE presente in TABLE
 ANNOTAZIONI: Richiama Procedure RUOLI_PI e RUOLI_PU
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
******************************************************************************/
   BEFORE INSERT OR UPDATE ON RUOLI
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
         Ruoli_Pu(:OLD.RUOLO
                         , :NEW.RUOLO
                         );
         IF NVL(:OLD.RUOLO,' ') = '*' THEN
            IF :NEW.RUOLO <> '*' THEN
               RAISE_APPLICATION_ERROR(-20999,'Ruolo ''*'' non modificabile!');
            END IF;
            IF :NEW.PROGETTO IS NOT NULL OR :NEW.MODULO IS NOT NULL THEN
               RAISE_APPLICATION_ERROR(-20999,'Il Ruolo ''*'' non puo'' essere associato a nessun Progetto o suo Modulo!');
            END IF;
          END IF;
          IF  NVL(:OLD.descrizione,' ') <> NVL(:NEW.descrizione,' ')
          AND :NEW.gruppo_so = 'S' THEN
             BEGIN
                UPDATE UTENTI
                   SET nominativo = :NEW.descrizione
                 WHERE nominativo = :OLD.descrizione
                   AND tipo_utente = 'O'
                   AND gruppo_lavoro = :NEW.ruolo
                ;
             EXCEPTION
                WHEN OTHERS THEN
                     RAISE;
             END;
         END IF;
      END IF;
      IF INSERTING THEN
         IF Integritypackage.GetNestLevel = 0 THEN
            DECLARE  --  Check UNIQUE PK Integrity per la tabella "RUOLI"
            CURSOR cpk_ruoli(var_RUOLO VARCHAR) IS
               SELECT 1
                 FROM   RUOLI
                WHERE  RUOLO = var_RUOLO;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on PK of "RUOLI"
               IF :NEW.RUOLO IS NOT NULL THEN
                  OPEN  cpk_ruoli(:NEW.RUOLO);
                  FETCH cpk_ruoli INTO dummy;
                  FOUND := cpk_ruoli%FOUND;
                  CLOSE cpk_ruoli;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.RUOLO||
                               '" gia'' presente in Ruoli. La registrazione  non puo'' essere inserita.';
                     RAISE integrity_error;
                  END IF;
               END IF;
            EXCEPTION
               WHEN MUTATING THEN NULL;  -- Ignora Check su UNIQUE PK Integrity
            END;
            DECLARE  --  Check UNIQUE Integrity per la tabella "RUOLI"
            CURSOR cpk_ruoli_desc(var_desc VARCHAR) IS
               SELECT 1
                 FROM   RUOLI
                WHERE  descrizione = var_desc;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on UK of "RUOLI"
               IF :NEW.descrizione IS NOT NULL THEN
                  OPEN  cpk_ruoli_desc(:NEW.descrizione);
                  FETCH cpk_ruoli_desc INTO dummy;
                  FOUND := cpk_ruoli_desc%FOUND;
                  CLOSE cpk_ruoli_desc;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.descrizione||
                               '" gia'' presente in Ruoli. La registrazione  non puo'' essere inserita.';
                     RAISE integrity_error;
                  END IF;
               END IF;
            EXCEPTION
               WHEN MUTATING THEN NULL;  -- Ignora Check su UNIQUE UK Integrity
            END;
         END IF;
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


