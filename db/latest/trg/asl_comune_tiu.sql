--liquibase formatted sql

--changeset snegroni:202002181538 runOnChange:true stripComments:false


create or replace TRIGGER ASL_COMUNE_TIU
   BEFORE INSERT OR UPDATE ON ASL_COMUNE
FOR EACH ROW
/******************************************************************************
 NOME:        ASL_COMUNE_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                       at INSERT or UPDATE on Table ASL_COMUNE
 ECCEZIONI:   -20007, Identificazione CHIAVE presente in TABLE
 ANNOTAZIONI: Richiama Procedure ASL_COMUNE_PI e ASL_COMUNE_PU
              Salvato nella directory ins di AD4 con nome asco_tiu.trg.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
    0 22/05/2002 MM     Aggiunto lancio in POSTEVENT di ASL_COMUNE_RRI.
    1 22/05/2003 MM     Sostituzione user con substr(user,1,8).
******************************************************************************/
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
         Asl_Comune_Pu(:OLD.Provincia
                       , :OLD.Comune
                       , :OLD.REGIONE_ASL
                       , :OLD.CODICE_ASL
                         , :NEW.Provincia
                         , :NEW.Comune
                         , :NEW.REGIONE_ASL
                         , :NEW.CODICE_ASL
                         );
         NULL;
      END IF;
      IF INSERTING THEN
         Asl_Comune_Pi(:NEW.Provincia,
                         :NEW.Comune,
                         :NEW.REGIONE_ASL,
                         :NEW.CODICE_ASL);
         IF Integritypackage.GetNestLevel = 0 THEN
            DECLARE  --  Check UNIQUE PK Integrity per la tabella "ASL_COMUNE"
            CURSOR cpk_asl_comune(var_PROVINCIA NUMBER,
                            var_COMUNE NUMBER,
                            var_REGIONE_ASL NUMBER,
                            var_CODICE_ASL NUMBER) IS
               SELECT 1
                 FROM   ASL_COMUNE
                WHERE  Provincia = var_PROVINCIA AND
                       Comune = var_COMUNE AND
                       REGIONE_ASL = var_REGIONE_ASL AND
                       CODICE_ASL = var_CODICE_ASL;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on PK of "ASL_COMUNE"
               IF :NEW.Provincia IS NOT NULL AND
                  :NEW.Comune IS NOT NULL AND
                  :NEW.REGIONE_ASL IS NOT NULL AND
                  :NEW.CODICE_ASL IS NOT NULL THEN
                  OPEN  cpk_asl_comune(:NEW.Provincia,
                                 :NEW.Comune,
                                 :NEW.REGIONE_ASL,
                                 :NEW.CODICE_ASL);
                  FETCH cpk_asl_comune INTO dummy;
                  FOUND := cpk_asl_comune%FOUND;
                  CLOSE cpk_asl_comune;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.Provincia||' '||
                               :NEW.Comune||' '||
                               :NEW.REGIONE_ASL||' '||
                               :NEW.CODICE_ASL||
                               '" gia'' presente in ASL_COMUNE. La registrazione non puo'' essere inserita.';
                     RAISE integrity_error;
                  END IF;
               END IF;
            EXCEPTION
               WHEN MUTATING THEN NULL;  -- Ignora Check su UNIQUE PK Integrity
            END;
         END IF;
      END IF;
   END;
   BEGIN  -- Set PostEvent Check REFERENTIAL Integrity
      DECLARE a_istruzione  VARCHAR2(2000);
              a_messaggio   VARCHAR2(2000);
      BEGIN
         a_messaggio  := '';
         a_istruzione := 'begin ASL_COMUNE_RRI ('||:NEW.Provincia||','||:NEW.Comune||','||:NEW.regione_asl||', '||:NEW.codice_asl||','''||:NEW.proposta||'''); end;';
         Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
      EXCEPTION
         WHEN OTHERS THEN
            Integritypackage.InitNestLevel;
            RAISE;
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