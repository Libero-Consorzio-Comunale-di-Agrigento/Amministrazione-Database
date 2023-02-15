CREATE OR REPLACE procedure SPORTELLI_PU
-- Salvata nella directory ins di AD4 con nome SPOR_PU.CRP
(old_abi IN varchar,
 old_cab IN varchar,
 new_abi IN varchar,
 new_cab IN varchar)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   seq              number;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "BANCHE"
   cursor cpk1_sportelli(var_abi varchar) is
      select 1
      from   BANCHE
      where  ABI = var_abi
       and   var_abi is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      seq := IntegrityPackage.GetNestLevel;
      begin  --  Parent "BANCHE" Deve esistere quando si inserisce "SPORTELLI"
         if (NEW_ABI is not null and (NEW_ABI != OLD_ABI or OLD_ABI is null)) and (seq = 0) then
            open  cpk1_sportelli(NEW_ABI);
            fetch cpk1_sportelli into dummy;
            found := cpk1_sportelli%FOUND;
            close cpk1_sportelli;
            if not found then
               errno  := -20003;
               errmsg := 'Non esiste riferimento su BANCHE. La registrazione SPORTELLI non e'' modificabile.';
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

