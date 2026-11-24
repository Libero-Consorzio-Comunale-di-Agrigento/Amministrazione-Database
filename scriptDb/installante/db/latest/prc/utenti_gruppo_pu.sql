--liquibase formatted sql

--changeset mturra:201901301231_347 runOnChange:true endDelimiter:/ stripComments:false


create or replace procedure UTENTI_GRUPPO_PU
/******************************************************************************
 NOME:        UTENTI_GRUPPO_PU
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at UPDATE on Table UTENTI_GRUPPO
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20001, Informazione COLONNA non modificabile
             -20003, Non esiste riferimento su PARENT TABLE
             -20004, Identificazione di TABLE non modificabile
             -20005, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger UTENTI_GRUPPO_TIU
              Salvata nella directory ins di AD4 con nome UTGR_PU.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(  old_utente IN varchar
 , old_gruppo IN varchar
 , new_utente IN varchar
 , new_gruppo IN varchar
)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   seq              number;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "UTENTI"
   cursor cpk1_utenti_gruppo(var_utente varchar) is
      select 1
      from   UTENTI
      where  UTENTE = var_utente
       and   var_utente is not null;
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "UTENTI"
   cursor cpk2_utenti_gruppo(var_gruppo varchar) is
      select 1
      from   UTENTI
      where  UTENTE = var_gruppo
       and   var_gruppo is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      seq := IntegrityPackage.GetNestLevel;
      begin  --  Parent "UTENTI" deve esistere quando si modifica "UTENTI_GRUPPO"
         if  NEW_UTENTE is not null and ( seq = 0 )
         and (   (NEW_UTENTE != OLD_UTENTE or OLD_UTENTE is null) ) then
            open  cpk1_utenti_gruppo(NEW_UTENTE);
            fetch cpk1_utenti_gruppo into dummy;
            found := cpk1_utenti_gruppo%FOUND; /* %FOUND */
            close cpk1_utenti_gruppo;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su Utenti. La registrazione UTENTI_GRUPPO non e'' modificabile.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "UTENTI" deve esistere quando si modifica "UTENTI_GRUPPO"
         if  NEW_GRUPPO is not null and ( seq = 0 )
         and (   (NEW_GRUPPO != OLD_GRUPPO or OLD_GRUPPO is null) ) then
            open  cpk2_utenti_gruppo(NEW_GRUPPO);
            fetch cpk2_utenti_gruppo into dummy;
            found := cpk2_utenti_gruppo%FOUND; /* %FOUND */
            close cpk2_utenti_gruppo;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su Utenti. La registrazione UTENTI_GRUPPO non e'' modificabile.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      --  Chiave di "UTENTI" non modificabile sul figlio: "UTENTI_GRUPPO"
      if (OLD_UTENTE != NEW_UTENTE) then
         if IntegrityPackage.GetNestLevel = 0 then
          errno  := -20004;
          errmsg := 'L''identificazione di Utenti non e'' modificabile su UTENTI_GRUPPO.';
          raise integrity_error;
         end if;
      end if;
      --  Chiave di "UTENTI" non modificabile sul figlio: "UTENTI_GRUPPO"
      if (OLD_GRUPPO != NEW_GRUPPO) then
         if IntegrityPackage.GetNestLevel = 0 then
          errno  := -20004;
          errmsg := 'L''identificazione di Utenti non e'' modificabile su UTENTI_GRUPPO.';
          raise integrity_error;
         end if;
      end if;
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

