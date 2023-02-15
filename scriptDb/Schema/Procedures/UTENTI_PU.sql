CREATE OR REPLACE PROCEDURE Utenti_Pu
/******************************************************************************
 NOME:        UTENTI_PU
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at UPDATE on Table UTENTI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20001, Informazione COLONNA non modificabile
             -20003, Non esiste riferimento su PARENT TABLE
             -20004, Identificazione di TABLE non modificabile
             -20005, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger UTENTI_TIU.
              Salvata nella directory ins di AD4 nel file UTEN_PU.CRP.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
    1 24/10/2018 SN     Tolto controllo su utente_aggiornamto x problemi in caso
                        di trasco.
******************************************************************************/
(  old_utente IN VARCHAR
 , old_nominativo IN VARCHAR
 , old_id_utente IN NUMBER
 , old_gruppo_lavoro IN VARCHAR
 , old_utente_aggiornamento IN VARCHAR
 , new_utente IN VARCHAR
 , new_nominativo IN VARCHAR
 , new_id_utente IN NUMBER
 , new_gruppo_lavoro IN VARCHAR
 , new_utente_aggiornamento IN VARCHAR
)
IS
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
   seq              NUMBER;
   mutating         EXCEPTION;
   PRAGMA EXCEPTION_INIT(mutating, -4091);
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "UTENTI"
   CURSOR cpk1_utenti(var_utente_aggiornamento VARCHAR) IS
      SELECT 1
      FROM   UTENTI
      WHERE  Utente = var_utente_aggiornamento
       AND   var_utente_aggiornamento IS NOT NULL;
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "GRUPPI_LAVORO"
   CURSOR cpk2_utenti(var_gruppo_lavoro VARCHAR) IS
      SELECT 1
      FROM   GRUPPI_LAVORO
      WHERE  GRUPPO_LAVORO = var_gruppo_lavoro
       AND   var_gruppo_lavoro IS NOT NULL;
   --  Declaration of UpdateParentRestrict constraint for "DIRITTI_ACCESSO"
   CURSOR cfk1_diritti_accesso(var_utente VARCHAR) IS
      SELECT 1
      FROM   DIRITTI_ACCESSO
      WHERE  Utente = var_utente
       AND   var_utente IS NOT NULL;
   --  Declaration of UpdateParentRestrict constraint for "UTENTI_SOGGETTI"
   CURSOR cfk2_utenti_soggetti(var_utente VARCHAR) IS
      SELECT 1
      FROM   UTENTI_SOGGETTI
      WHERE  Utente = var_utente
       AND   var_utente IS NOT NULL;
BEGIN
   BEGIN  -- Check REFERENTIAL Integrity
      seq := Integritypackage.GetNestLevel;
   -- rev.1 inizio
--      BEGIN  --  Parent "UTENTI" deve esistere quando si modifica "UTENTI"
--         IF  NEW_UTENTE_AGGIORNAMENTO IS NOT NULL AND ( seq = 0 )
--         AND (   (NEW_UTENTE_AGGIORNAMENTO != OLD_UTENTE_AGGIORNAMENTO OR OLD_UTENTE_AGGIORNAMENTO IS NULL) ) THEN
--            OPEN  cpk1_utenti(NEW_UTENTE_AGGIORNAMENTO);
--            FETCH cpk1_utenti INTO dummy;
--            FOUND := cpk1_utenti%FOUND; /* %FOUND */
--            CLOSE cpk1_utenti;
--            IF NOT FOUND THEN
--          errno  := -20003;
--          errmsg := 'Non esiste riferimento su Utenti. La registrazione Utenti non e'' modificabile.';
--          RAISE integrity_error;
--            END IF;
--         END IF;
--      EXCEPTION
--         WHEN MUTATING THEN NULL;  -- Ignora Check su Relazioni Ricorsive
--      END;
   -- rev.1 fine
      BEGIN  --  Parent "GRUPPI_LAVORO" deve esistere quando si modifica "UTENTI"
         IF  NEW_GRUPPO_LAVORO IS NOT NULL AND ( seq = 0 )
         AND (   (NEW_GRUPPO_LAVORO != OLD_GRUPPO_LAVORO OR OLD_GRUPPO_LAVORO IS NULL) ) THEN
            OPEN  cpk2_utenti(NEW_GRUPPO_LAVORO);
            FETCH cpk2_utenti INTO dummy;
            FOUND := cpk2_utenti%FOUND; /* %FOUND */
            CLOSE cpk2_utenti;
            IF NOT FOUND THEN
          errno  := -20003;
          errmsg := 'Non esiste riferimento su GRUPPI_LAVORO. La registrazione Utenti non e'' modificabile.';
          RAISE integrity_error;
            END IF;
         END IF;
      EXCEPTION
         WHEN MUTATING THEN NULL;  -- Ignora Check su Relazioni Ricorsive
      END;
      --  Chiave di "UTENTI" non modificabile se esistono referenze su "DIRITTI_ACCESSO"
      IF (OLD_UTENTE != NEW_UTENTE) THEN
         OPEN  cfk1_diritti_accesso(OLD_UTENTE);
         FETCH cfk1_diritti_accesso INTO dummy;
         FOUND := cfk1_diritti_accesso%FOUND; /* %FOUND */
         CLOSE cfk1_diritti_accesso;
         IF FOUND THEN
          errno  := -20005;
          errmsg := 'Esistono riferimenti su Diritti Accesso. La registrazione di Utenti non e'' modificabile.';
          RAISE integrity_error;
         END IF;
      END IF;
      --  Chiave di "UTENTI" non modificabile se esistono referenze su "UTENTI_SOGGETTI"
      IF (OLD_UTENTE != NEW_UTENTE) THEN
         OPEN  cfk2_utenti_soggetti(OLD_UTENTE);
         FETCH cfk2_utenti_soggetti INTO dummy;
         FOUND := cfk2_utenti_soggetti%FOUND; /* %FOUND */
         CLOSE cfk2_utenti_soggetti;
         IF FOUND THEN
          errno  := -20005;
          errmsg := 'Esistono riferimenti su Utenti Soggetti. La registrazione di Utenti non e'' modificabile.';
          RAISE integrity_error;
         END IF;
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

