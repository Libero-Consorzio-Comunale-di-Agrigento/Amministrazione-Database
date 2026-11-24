--liquibase formatted sql

--changeset mturra:201901301231_330 runOnChange:true endDelimiter:/ stripComments:false


create or replace procedure ISTANZE_PI
/******************************************************************************
 NOME:        ISTANZE_PI
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at INSERT on Table ISTANZE
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20002, Non esiste riferimento su TABLE
             -20008, Numero di CHILD assegnato a TABLE non ammesso
 ANNOTAZIONI: Richiamata da Trigger ISTANZE_TMB
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
( new_istanza IN varchar
, new_progetto IN varchar
, new_ente IN varchar
, new_lingua IN varchar
, new_istanza_amministratore IN varchar )
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   cardinality      integer;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Declaration of InsertChildParentExist constraint for the parent "ENTI"
   cursor cpk1_istanze(var_ente varchar) is
      select 1
        from ENTI
       where ENTE = var_ente
         and var_ente is not null
         ;
   --  Declaration of InsertChildParentExist constraint for the parent "PROGETTI"
   cursor cpk2_istanze(var_progetto varchar) is
      select 1
        from PROGETTI
       where PROGETTO = var_progetto
         and var_progetto is not null
         ;
   --  Declaration of InsertChildParentExist constraint for the parent "LINGUE"
   cursor cpk3_istanze(var_lingua varchar) is
      select 1
        from LINGUE
       where LINGUA = var_lingua
         and var_lingua is not null
          or new_lingua = '*' /*Parent*/;
begin
   begin  -- Check REFERENTIAL Integrity
      begin  --  Parent "ENTI" deve esistere quando si inserisce su "ISTANZE"
         if NEW_ENTE is not null
         then
            open  cpk1_istanze(NEW_ENTE);
            fetch cpk1_istanze into dummy;
            found := cpk1_istanze%FOUND; /* %FOUND */
            close cpk1_istanze;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su Enti. La registrazione Istanze non puo'' essere inserita. (ISTANZE.ISTA_ENTI_FK)';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "PROGETTI" deve esistere quando si inserisce su "ISTANZE"
         if NEW_PROGETTO is not null
         then
            open  cpk2_istanze(NEW_PROGETTO);
            fetch cpk2_istanze into dummy;
            found := cpk2_istanze%FOUND; /* %FOUND */
            close cpk2_istanze;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su Progetti. La registrazione Istanze non puo'' essere inserita. (ISTANZE.ISTA_PROG_FK)';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "LINGUE" deve esistere quando si inserisce su "ISTANZE"
             --  where "... or new_lingua = '*' /*Parent*/"
         if NEW_LINGUA is not null
         then
            open  cpk3_istanze(NEW_LINGUA);
            fetch cpk3_istanze into dummy;
            found := cpk3_istanze%FOUND; /* %FOUND */
            close cpk3_istanze;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su Lingue. La registrazione Istanze non puo'' essere inserita. (ISTANZE.ISTA_LING_FK)';
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

