CREATE OR REPLACE TRIGGER ASL_TIU
/******************************************************************************
 NOME:        ASL_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                       at INSERT or UPDATE on Table ASL
 ECCEZIONI:   -20007, Identificazione CHIAVE presente in TABLE
 ANNOTAZIONI: Richiama Procedure ASL_PI e ASL_PU
              Salvato nella directory ins di AD4 con nome asl_tiu.trg.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
   1 22/05/2003 MM     Sostituzione user con substr(user,1,8).
******************************************************************************/
   BEFORE INSERT OR UPDATE ON ASL
FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
BEGIN
   BEGIN  -- Check DATA Integrity on INSERT or UPDATE
      -- Inizializzazione campi 'UTENTE_AGGIORNAMENTO' e 'DATA_AGGIORNAMENTO'
      IF  Si4.Utente IS NOT NULL
      AND NVL(:NEW.utente_aggiornamento,' ') = NVL(:OLD.utente_aggiornamento,' ') THEN
         :NEW.utente_aggiornamento := Si4.Utente;
      END IF;
      IF :NEW.data_aggiornamento IS NULL
      OR :NEW.data_aggiornamento = :OLD.data_aggiornamento THEN
         :NEW.data_aggiornamento := SYSDATE;
      END IF;
   END;
   BEGIN  -- Check REFERENTIAL Integrity on INSERT or UPDATE
      IF UPDATING THEN
         Asl_Pu( :OLD.REGIONE_ASL
               , :OLD.CODICE_ASL
               , :NEW.REGIONE_ASL
               , :NEW.CODICE_ASL
               );
      END IF;
      IF INSERTING THEN
         IF Integritypackage.GetNestLevel = 0 THEN
            DECLARE  --  Check UNIQUE PK Integrity per la tabella "ASL"
            CURSOR cpk_asl(var_REGIONE_ASL NUMBER,
                            var_CODICE_ASL NUMBER) IS
               SELECT 1
                 FROM   ASL
                WHERE  REGIONE_ASL = var_REGIONE_ASL AND
                       CODICE_ASL = var_CODICE_ASL;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on PK of "ASL"
               IF :NEW.REGIONE_ASL IS NOT NULL AND
                  :NEW.CODICE_ASL IS NOT NULL THEN
                  OPEN  cpk_asl(:NEW.REGIONE_ASL,
                                 :NEW.CODICE_ASL);
                  FETCH cpk_asl INTO dummy;
                  FOUND := cpk_asl%FOUND;
                  CLOSE cpk_asl;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.REGIONE_ASL||' '||
                               :NEW.CODICE_ASL||
                               '" gia'' presente in ASL. La registrazione  non puo'' essere inserita.';
                     RAISE integrity_error;
                  END IF;
               END IF;
            EXCEPTION
               WHEN MUTATING THEN NULL;  -- Ignora Check su UNIQUE PK Integrity
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


