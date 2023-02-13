--liquibase formatted sql

--changeset mturra:201901301231_349 runOnChange:true endDelimiter:/ stripComments:false


CREATE OR REPLACE procedure UTENTI_PI
/******************************************************************************
 NOME:        UTENTI_PI
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at INSERT on Table UTENTI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20002, Non esiste riferimento su TABLE
             -20008, Numero di CHILD assegnato a TABLE non ammesso
 ANNOTAZIONI: Richiamata da Trigger UTENTI_TIU.
              Salvata nella directory ins di AD4 nel file UTEN_PI.CRP.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
    1 24/10/2018 SN     Tolto controllo su utente_aggiornamto x problemi in caso
                        di trasco.
******************************************************************************/
(new_gruppo_lavoro IN varchar,
 new_utente_aggiornamento IN varchar)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   cardinality      integer;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione di InsertChildParentExist per la tabella padre "UTENTI"
   cursor cpk1_utenti(var_utente_aggiornamento varchar) is
      select 1
      from   UTENTI
      where  UTENTE = var_utente_aggiornamento
       and   var_utente_aggiornamento is not null;
   --  Dichiarazione di InsertChildParentExist per la tabella padre "GRUPPI_LAVORO"
   cursor cpk2_utenti(var_gruppo_lavoro varchar) is
      select 1
      from   GRUPPI_LAVORO
      where  GRUPPO_LAVORO = var_gruppo_lavoro
       and   var_gruppo_lavoro is not null;
begin
   begin  -- Check REFERENTIAL Integrity
   -- rev.1 inizio
--      begin  --  Parent "UTENTI" deve esistere quando si inserisce su "UTENTI"
--         if NEW_UTENTE_AGGIORNAMENTO is not null then
--            open  cpk1_utenti(NEW_UTENTE_AGGIORNAMENTO);
--            fetch cpk1_utenti into dummy;
--            found := cpk1_utenti%FOUND; /* %FOUND */
--            close cpk1_utenti;
--            if not found then
--          errno  := -20002;
--          errmsg := 'Non esiste riferimento per Utente Aggiornamento. La registrazione Utenti non puo'' essere inserita.';
--          raise integrity_error;
--            end if;
--         end if;
--      exception
--         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
--      end;
   -- rev.1 fine
      begin  --  Parent "GRUPPI_LAVORO" deve esistere quando si inserisce su "UTENTI"
         if NEW_GRUPPO_LAVORO is not null then
            open  cpk2_utenti(NEW_GRUPPO_LAVORO);
            fetch cpk2_utenti into dummy;
            found := cpk2_utenti%FOUND; /* %FOUND */
            close cpk2_utenti;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su GRUPPI_LAVORO. La registrazione Utenti non puo'' essere inserita.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      null;
   end;
exception
   when integrity_error then
        IntegrityPackage.InitNestLevel;
        raise_application_error(errno, errmsg);
   when others then
        IntegrityPackage.InitNestLevel;
        raise;
end;
/
