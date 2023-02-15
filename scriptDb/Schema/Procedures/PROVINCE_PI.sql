CREATE OR REPLACE procedure PROVINCE_PI
/******************************************************************************
 NOME:        PROVINCE_PI
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at INSERT on Table PROVINCE
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20002, Non esiste riferimento su TABLE
             -20008, Numero di CHILD assegnato a TABLE non ammesso
 ANNOTAZIONI: Richiamata da Trigger PROVINCE_TIU
              Salvata nella directory ins di AD4 con nome PROV_PI.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(new_regione IN number)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   cardinality      integer;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione di InsertChildParentExist per la tabella padre "REGIONI"
   cursor cpk1_province(var_regione number) is
      select 1
      from   REGIONI
      where  REGIONE = var_regione
       and   var_regione is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      begin  --  Parent "REGIONI" deve esistere quando si inserisce su "PROVINCE"
         if NEW_REGIONE is not null then
            open  cpk1_province(NEW_REGIONE);
            fetch cpk1_province into dummy;
            found := cpk1_province%FOUND;
            close cpk1_province;
            if not found then
               errno  := -20002;
               errmsg := 'Non esiste riferimento su Regioni. La registrazione Provincia non puo'' essere inserita.';
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

