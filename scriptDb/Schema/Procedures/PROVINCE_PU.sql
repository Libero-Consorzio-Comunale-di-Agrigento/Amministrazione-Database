CREATE OR REPLACE procedure PROVINCE_PU
/******************************************************************************
 NOME:        PROVINCE_PU
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at UPDATE on Table PROVINCE
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20001, Informazione COLONNA non modificabile
             -20003, Non esiste riferimento su PARENT TABLE
             -20004, Identificazione di TABLE non modificabile
             -20005, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger PROVINCE_TIU
              Salvata nella directory ins di AD4 con nome PROV_PU.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(old_provincia IN number,
 old_regione IN number,
 new_provincia IN number,
 new_regione IN number)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   seq              number;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "REGIONI"
   cursor cpk1_province(var_regione number) is
      select 1
      from   REGIONI
      where  REGIONE = var_regione
       and   var_regione is not null;
   --  Declaration of UpdateParentRestrict constraint for "COMUNI"
   cursor cfk1_province(var_provincia number) is
      select 1
      from   COMUNI
      where  PROVINCIA_STATO = var_provincia
       and   var_provincia is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      seq := IntegrityPackage.GetNestLevel;
      begin  --  Parent "REGIONI" deve esistere quando si modifica "PROVINCE"
         if  NEW_REGIONE is not null and ( seq = 0 )
         and (   (NEW_REGIONE != OLD_REGIONE or OLD_REGIONE is null) ) then
            open  cpk1_province(NEW_REGIONE);
            fetch cpk1_province into dummy;
            found := cpk1_province%FOUND;
            close cpk1_province;
            if not found then
               errno  := -20003;
               errmsg := 'Non esiste riferimento su Regioni. La registrazione Provincia non e'' modificabile.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      --  Chiave di "PROVINCE" non modificabile se esistono referenze su "COMUNI"
      if (OLD_PROVINCIA != NEW_PROVINCIA) then
         open  cfk1_province(OLD_PROVINCIA);
         fetch cfk1_province into dummy;
         found := cfk1_province%FOUND;
         close cfk1_province;
         if found then
            errno  := -20005;
            errmsg := 'Esistono riferimenti su Comuni. La registrazione di Provincia non e'' modificabile.';
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

