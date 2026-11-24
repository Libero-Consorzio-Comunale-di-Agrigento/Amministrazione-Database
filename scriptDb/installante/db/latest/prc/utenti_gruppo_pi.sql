--liquibase formatted sql

--changeset mturra:201901301231_346 runOnChange:true endDelimiter:/ stripComments:false


create or replace procedure UTENTI_GRUPPO_PI
/******************************************************************************
 NOME:        UTENTI_GRUPPO_PI
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at INSERT on Table UTENTI_GRUPPO
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20002, Non esiste riferimento su TABLE
             -20008, Numero di CHILD assegnato a TABLE non ammesso
 ANNOTAZIONI: Richiamata da Trigger UTENTI_GRUPPO_TMB
              Salvata nella directory ins di AD4 con nome UTGR_PI.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(new_utente IN varchar,
 new_gruppo IN varchar)
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
   cursor cpk1_utenti_gruppo(var_utente varchar) is
      select 1
      from   UTENTI
      where  UTENTE = var_utente
       and   var_utente is not null;
   --  Dichiarazione di InsertChildParentExist per la tabella padre "UTENTI"
   cursor cpk2_utenti_gruppo(var_gruppo varchar) is
      select 1
      from   UTENTI
      where  UTENTE = var_gruppo
       and   var_gruppo is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      begin  --  Parent "UTENTI" deve esistere quando si inserisce su "UTENTI_GRUPPO"
         if NEW_UTENTE is not null then
            open  cpk1_utenti_gruppo(NEW_UTENTE);
            fetch cpk1_utenti_gruppo into dummy;
            found := cpk1_utenti_gruppo%FOUND; /* %FOUND */
            close cpk1_utenti_gruppo;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su Utenti. La registrazione UTENTI_GRUPPO non puo'' essere inserita.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "UTENTI" deve esistere quando si inserisce su "UTENTI_GRUPPO"
         if NEW_GRUPPO is not null then
            open  cpk2_utenti_gruppo(NEW_GRUPPO);
            fetch cpk2_utenti_gruppo into dummy;
            found := cpk2_utenti_gruppo%FOUND; /* %FOUND */
            close cpk2_utenti_gruppo;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su Utenti. La registrazione UTENTI_GRUPPO non puo'' essere inserita.';
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

