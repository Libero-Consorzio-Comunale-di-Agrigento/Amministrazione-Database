--liquibase formatted sql

--changeset mturra:201901301231_341 runOnChange:true endDelimiter:/ stripComments:false


create or replace procedure SPORTELLI_PI
-- Salvata nella directory ins di AD4 con nome SPOR_PI.CRP
(new_abi IN varchar)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione di InsertChildParentExist per la tabella padre "BANCHE"
   cursor cpk1_sportelli(var_abi varchar) is
      select 1
      from   BANCHE
      where  ABI = var_abi
       and   var_abi is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      begin  --  Parent "BANCHE" deve esistere quando si inserisce su "SPORTELLI"
         if NEW_ABI is not null then
            open  cpk1_sportelli(NEW_ABI);
            fetch cpk1_sportelli into dummy;
            found := cpk1_sportelli%FOUND;
            close cpk1_sportelli;
            if not found then
               errno  := -20002;
               errmsg := 'Non esiste riferimento su BANCHE. La registrazione SPORTELLI non puo'' essere inserita.';
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

