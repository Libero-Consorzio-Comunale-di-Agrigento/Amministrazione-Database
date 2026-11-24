--liquibase formatted sql

--changeset mturra:201901301231_336 runOnChange:true endDelimiter:/ stripComments:false


create or replace procedure REGIONI_PU
/******************************************************************************
 NOME:        REGIONI_PU
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at UPDATE on Table REGIONI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20001, Informazione COLONNA non modificabile
             -20003, Non esiste riferimento su PARENT TABLE
             -20004, Identificazione di TABLE non modificabile
             -20005, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger REGIONI_TIU
              Salvata nella directory ins di AD4 con nome REGI_PU.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(old_regione IN number,
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
   --  Declaration of UpdateParentRestrict constraint for "PROVINCE"
   cursor cfk1_regioni(var_regione number) is
      select 1
      from   PROVINCE
      where  REGIONE = var_regione
       and   var_regione is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      seq := IntegrityPackage.GetNestLevel;
      --  Chiave di "REGIONI" non modificabile se esistono referenze su "PROVINCE"
      if (OLD_REGIONE != NEW_REGIONE) then
         open  cfk1_regioni(OLD_REGIONE);
         fetch cfk1_regioni into dummy;
         found := cfk1_regioni%FOUND;
         close cfk1_regioni;
         if found then
            errno  := -20005;
            errmsg := 'Esistono riferimenti su Provincia. La registrazione di Regioni non e'' modificabile.';
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

