CREATE OR REPLACE procedure RAGGRUPPAMENTI_STATI_PU
/******************************************************************************
 NOME:        RAGGRUPPAMENTI_STATI_PU
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at UPDATE on Table RAGGRUPPAMENTI_STATI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20001, Informazione COLONNA non modificabile
             -20003, Non esiste riferimento su PARENT TABLE
             -20004, Identificazione di TABLE non modificabile
             -20005, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger RAGGRUPPAMENTI_STATI_TIU
              Salvata nella directory ins di AD4 con nome RAST_PU.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(old_raggruppamento IN number,
 new_raggruppamento IN number)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   seq              number;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Declaration of UpdateParentRestrict constraint for "STATI_TERRITORI"
   cursor cfk1_raggruppamenti_stati(var_raggruppamento number) is
      select 1
      from   STATI_TERRITORI
      where  RAGGRUPPAMENTO = var_raggruppamento
       and   var_raggruppamento is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      seq := IntegrityPackage.GetNestLevel;
      --  Chiave di "RAGGRUPPAMENTI_STATI" non modificabile se esistono referenze su "STATI_TERRITORI"
      if (OLD_RAGGRUPPAMENTO != NEW_RAGGRUPPAMENTO) then
         open  cfk1_raggruppamenti_stati(OLD_RAGGRUPPAMENTO);
         fetch cfk1_raggruppamenti_stati into dummy;
         found := cfk1_raggruppamenti_stati%FOUND;
         close cfk1_raggruppamenti_stati;
         if found then
            errno  := -20005;
            errmsg := 'Esistono riferimenti su Stato o territorio estero. La registrazione di Raggruppamento stati esteri non e'' modificabile.';
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

