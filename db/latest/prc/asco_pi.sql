--liquibase formatted sql

--changeset snegroni:202002181539_2 runOnChange:true endDelimiter:/ stripComments:false


create or replace procedure ASL_COMUNE_PI
/******************************************************************************
 NOME:        ASL_COMUNE_PI
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at INSERT on Table ASL_COMUNE
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20002, Non esiste riferimento su TABLE
             -20008, Numero di CHILD assegnato a TABLE non ammesso
 ANNOTAZIONI: Richiamata da Trigger ASL_COMUNE_TIU
              Salvata nella directory ins di AD4 con nome ASCO_PI.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(new_provincia IN number,
 new_comune IN number,
 new_regione_asl IN number,
 new_codice_asl IN number)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   cardinality      integer;
   MUTATING  exception;
   PRAGMA exception_init(MUTATING,-4091);
   --  Declaration of InsertChildParentExist constraint for the parent "ASL"
   cursor cpk1_asl_comune(var_regione_asl number,
                   var_codice_asl number) is
      select 1
      from   ASL
      where  REGIONE_ASL = var_regione_asl
       and   CODICE_ASL = var_codice_asl
       and   var_regione_asl is not null
       and   var_codice_asl is not null;
   --  Declaration of InsertChildParentExist constraint for the parent "PROVINCE"
   cursor cpk2_asl_comune(var_provincia number) is
      select 1
      from   PROVINCE
      where  PROVINCIA = var_provincia
       and   var_provincia is not null;
   --  Declaration of InsertChildParentExist constraint for the parent "COMUNI"
   cursor cpk3_asl_comune(var_provincia number,
                   var_comune number) is
      select 1
      from   COMUNI
      where  PROVINCIA_STATO = var_provincia
       and   COMUNE = var_comune
       and   var_provincia is not null
       and   var_comune is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      begin  --  Parent "ASL" deve esistere quando si inserisce su "ASL_COMUNE"
         if NEW_REGIONE_ASL is not null and
            NEW_CODICE_ASL is not null then
            open  cpk1_asl_comune(NEW_REGIONE_ASL,
                           NEW_CODICE_ASL);
            fetch cpk1_asl_comune into dummy;
            found := cpk1_asl_comune%FOUND; /* %FOUND */
            close cpk1_asl_comune;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su ASL. La registrazione ASL_COMUNE non puo'''' essere inserita.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "PROVINCE" deve esistere quando si inserisce su "ASL_COMUNE"
         if NEW_PROVINCIA is not null then
            open  cpk2_asl_comune(NEW_PROVINCIA);
            fetch cpk2_asl_comune into dummy;
            found := cpk2_asl_comune%FOUND; /* %FOUND */
            close cpk2_asl_comune;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su Provincia. La registrazione ASL_COMUNE non puo'''' essere inserita.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "COMUNI" deve esistere quando si inserisce su "ASL_COMUNE"
         if NEW_PROVINCIA is not null and
            NEW_COMUNE is not null then
            open  cpk3_asl_comune(NEW_PROVINCIA,
                           NEW_COMUNE);
            fetch cpk3_asl_comune into dummy;
            found := cpk3_asl_comune%FOUND; /* %FOUND */
            close cpk3_asl_comune;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su Comuni. La registrazione ASL_COMUNE non puo'''' essere inserita.';
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