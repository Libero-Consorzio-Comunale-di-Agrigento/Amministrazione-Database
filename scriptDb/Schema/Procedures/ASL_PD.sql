CREATE OR REPLACE procedure ASL_PD
/******************************************************************************
 NOME:        ASL_PD
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at DELETE on Table ASL
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20006, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger ASL_TD
              Salvata nella directory ins di AD4 con nome ASL_PD.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(old_regione_asl IN number,
 old_codice_asl IN number)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   --  Declaration of DeleteParentRestrict constraint for "ASL_COMUNE"
   cursor cfk1_asl_comune(var_regione_asl number,
                   var_codice_asl number) is
      select 1
      from   ASL_COMUNE
      where  REGIONE_ASL = var_regione_asl
       and   CODICE_ASL = var_codice_asl
       and   var_regione_asl is not null
       and   var_codice_asl is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      --  Cannot delete parent "ASL" if children still exist in "ASL_COMUNE"
      open  cfk1_asl_comune(OLD_REGIONE_ASL,
                     OLD_CODICE_ASL);
      fetch cfk1_asl_comune into dummy;
      found := cfk1_asl_comune%FOUND; /* %FOUND */
      close cfk1_asl_comune;
      if found then
          errno  := -20006;
          errmsg := 'Esistono riferimenti su ASL_COMUNE. La registrazione di ASL non e'''' eliminabile.';
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

