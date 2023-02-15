CREATE OR REPLACE procedure BANCHE_PD
/******************************************************************************
 NOME:        BANCHE_PD
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at DELETE on Table BANCHE
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20006, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger BANCHE_TD
              Salvata nella directory ins di AD4 con nome BANC_PD.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(old_abi IN varchar)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   --  Declaration of DeleteParentRestrict constraint for "SPORTELLI"
   cursor cfk1_sportelli(var_abi varchar) is
      select 1
      from   SPORTELLI
      where  ABI = var_abi
       and   var_abi is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      --  Cannot delete parent "BANCHE" if children still exist in "SPORTELLI"
      open  cfk1_sportelli(OLD_ABI);
      fetch cfk1_sportelli into dummy;
      found := cfk1_sportelli%FOUND; /* %FOUND */
      close cfk1_sportelli;
      if found then
          errno  := -20006;
          errmsg := 'Esistono riferimenti su SPORTELLI. La registrazione di BANCHE non e'''' eliminabile.';
          raise integrity_error;
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

