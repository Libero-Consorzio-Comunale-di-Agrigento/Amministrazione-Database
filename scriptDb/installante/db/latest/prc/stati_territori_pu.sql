--liquibase formatted sql

--changeset mturra:201901301231_344 runOnChange:true endDelimiter:/ stripComments:false


create or replace procedure STATI_TERRITORI_PU
/******************************************************************************
 NOME:        STATI_TERRITORI_PU
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at UPDATE on Table STATI_TERRITORI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20001, Informazione COLONNA non modificabile
             -20003, Non esiste riferimento su PARENT TABLE
             -20004, Identificazione di TABLE non modificabile
             -20005, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger STATI_TERRITORI_TIU
              Salvata nella directory ins di AD4 con nome STTE_PU.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(old_stato_territorio IN number,
 old_raggruppamento IN number,
 old_stato_appartenenza IN number,
 new_stato_territorio IN number,
 new_raggruppamento IN number,
 new_stato_appartenenza IN number)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   seq              number;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "STATI_TERRITORI"
   cursor cpk1_stati_territori(var_stato_appartenenza number) is
      select 1
      from   STATI_TERRITORI
      where  STATO_TERRITORIO = var_stato_appartenenza
       and   var_stato_appartenenza is not null;
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "RAGGRUPPAMENTI_STATI"
   cursor cpk2_stati_territori(var_raggruppamento number) is
      select 1
      from   RAGGRUPPAMENTI_STATI
      where  RAGGRUPPAMENTO = var_raggruppamento
       and   var_raggruppamento is not null;
   --  Declaration of UpdateParentRestrict constraint for "COMUNI"
   cursor cfk1_stati_territori(var_stato_territorio number) is
      select 1
      from   COMUNI
      where  PROVINCIA_STATO = var_stato_territorio
       and   var_stato_territorio is not null;
   --  Declaration of UpdateParentRestrict constraint for "STATI_TERRITORI"
   cursor cfk2_stati_territori(var_stato_territorio number) is
      select 1
      from   STATI_TERRITORI
      where  STATO_APPARTENENZA = var_stato_territorio
       and   var_stato_territorio is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      seq := IntegrityPackage.GetNestLevel;
      begin  --  Parent "STATI_TERRITORI" deve esistere quando si modifica "STATI_TERRITORI"
         if  NEW_STATO_APPARTENENZA is not null and ( seq = 0 )
         and (   (NEW_STATO_APPARTENENZA != OLD_STATO_APPARTENENZA or OLD_STATO_APPARTENENZA is null) ) then
            open  cpk1_stati_territori(NEW_STATO_APPARTENENZA);
            fetch cpk1_stati_territori into dummy;
            found := cpk1_stati_territori%FOUND;
            close cpk1_stati_territori;
            if not found then
               errno  := -20003;
               errmsg := 'Non esiste riferimento su Stato o territorio estero. La registrazione Stato o territorio estero non e'' modificabile.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "RAGGRUPPAMENTI_STATI" deve esistere quando si modifica "STATI_TERRITORI"
         if  NEW_RAGGRUPPAMENTO is not null and ( seq = 0 )
         and (   (NEW_RAGGRUPPAMENTO != OLD_RAGGRUPPAMENTO or OLD_RAGGRUPPAMENTO is null) ) then
            open  cpk2_stati_territori(NEW_RAGGRUPPAMENTO);
            fetch cpk2_stati_territori into dummy;
            found := cpk2_stati_territori%FOUND;
            close cpk2_stati_territori;
            if not found then
               errno  := -20003;
               errmsg := 'Non esiste riferimento su Raggruppamento stati esteri. La registrazione Stato o territorio estero non e'' modificabile.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      --  Chiave di "STATI_TERRITORI" non modificabile se esistono referenze su "COMUNI"
      if (OLD_STATO_TERRITORIO != NEW_STATO_TERRITORIO) then
         open  cfk1_stati_territori(OLD_STATO_TERRITORIO);
         fetch cfk1_stati_territori into dummy;
         found := cfk1_stati_territori%FOUND;
         close cfk1_stati_territori;
         if found then
            errno  := -20005;
            errmsg := 'Esistono riferimenti su Comuni. La registrazione di Stato o territorio estero non e'' modificabile.';
            raise integrity_error;
         end if;
      end if;
      --  Chiave di "STATI_TERRITORI" non modificabile se esistono referenze su "STATI_TERRITORI"
      if (OLD_STATO_TERRITORIO != NEW_STATO_TERRITORIO) then
         open  cfk2_stati_territori(OLD_STATO_TERRITORIO);
         fetch cfk2_stati_territori into dummy;
         found := cfk2_stati_territori%FOUND;
         close cfk2_stati_territori;
         if found then
            errno  := -20005;
            errmsg := 'Esistono riferimenti su Stato o territorio estero. La registrazione di Stato o territorio estero non e'' modificabile.';
            raise integrity_error;
         end if;
      end if;
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

