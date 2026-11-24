--liquibase formatted sql

--changeset mturra:201901301231_331 runOnChange:true endDelimiter:/ stripComments:false


create or replace procedure ISTANZE_PU
/******************************************************************************
 NOME:        ISTANZE_PU
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at UPDATE on Table ISTANZE
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20001, Informazione COLONNA non modificabile
             -20003, Non esiste riferimento su PARENT TABLE
             -20004, Identificazione di TABLE non modificabile
             -20005, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger ISTANZE_TMB
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
( old_istanza IN varchar
, old_progetto IN varchar
, old_ente IN varchar
, old_lingua IN varchar
, old_istanza_amministratore IN varchar
, new_istanza IN varchar
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
   seq              number;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Declaration of UpdateChildParentExist constraint for the parent "ENTI"
   cursor cpk1_istanze(var_ente varchar) is
      select 1
        from ENTI
       where ENTE = var_ente
         and var_ente is not null
         ;
   --  Declaration of UpdateChildParentExist constraint for the parent "PROGETTI"
   cursor cpk2_istanze(var_progetto varchar) is
      select 1
        from PROGETTI
       where PROGETTO = var_progetto
         and var_progetto is not null
         ;
   --  Declaration of UpdateChildParentExist constraint for the parent "LINGUE"
   cursor cpk3_istanze(var_lingua varchar) is
      select 1
        from LINGUE
       where LINGUA = var_lingua
         and var_lingua is not null
          or new_lingua = '*' /*Parent*/;
   --  Declaration of UpdateParentRestrict constraint for "DIRITTI_ACCESSO"
   cursor cfk1_diritti_accesso(var_istanza varchar) is
      select 1
        from DIRITTI_ACCESSO
       where ISTANZA = var_istanza
         and var_istanza is not null
         ;
   --  Declaration of UpdateParentRestrict constraint for "CARATTERISTICHE_ACCESSO"
   cursor cfk2_caratteristiche_accesso(var_istanza varchar) is
      select 1
        from CARATTERISTICHE_ACCESSO
       where ISTANZA = var_istanza
         and var_istanza is not null
         ;
begin
   begin  -- Check REFERENTIAL Integrity
      seq := IntegrityPackage.GetNestLevel;
      begin  --  Parent "ENTI" deve esistere quando si modifica "ISTANZE"
         if  NEW_ENTE is not null and ( seq = 0 )
         then
            open  cpk1_istanze(NEW_ENTE);
            fetch cpk1_istanze into dummy;
            found := cpk1_istanze%FOUND; /* %FOUND */
            close cpk1_istanze;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su Enti. La registrazione Istanze non e'' modificabile. (ISTANZE.ISTA_ENTI_FK)';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "PROGETTI" deve esistere quando si modifica "ISTANZE"
         if  NEW_PROGETTO is not null and ( seq = 0 )
         then
            open  cpk2_istanze(NEW_PROGETTO);
            fetch cpk2_istanze into dummy;
            found := cpk2_istanze%FOUND; /* %FOUND */
            close cpk2_istanze;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su Progetti. La registrazione Istanze non e'' modificabile. (ISTANZE.ISTA_PROG_FK)';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "LINGUE" deve esistere quando si modifica "ISTANZE"
             --  where "... or new_lingua = '*' /*Parent*/"
         if  NEW_LINGUA is not null and ( seq = 0 )
         then
            open  cpk3_istanze(NEW_LINGUA);
            fetch cpk3_istanze into dummy;
            found := cpk3_istanze%FOUND; /* %FOUND */
            close cpk3_istanze;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su Lingue. La registrazione Istanze non e'' modificabile. (ISTANZE.ISTA_LING_FK)';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      --  Chiave di "ISTANZE" non modificabile se esistono referenze su "DIRITTI_ACCESSO"
      if NEW_ISTANZA != OLD_ISTANZA
      then
         open  cfk1_diritti_accesso(OLD_ISTANZA);
         fetch cfk1_diritti_accesso into dummy;
         found := cfk1_diritti_accesso%FOUND; /* %FOUND */
         close cfk1_diritti_accesso;
         if found then
          errno  := -20005;
          errmsg := 'Esistono riferimenti su Diritti Accesso. La registrazione di Istanze non e'' modificabile. (DIRITTI_ACCESSO.DIAC_ISTA_FK)';
          raise integrity_error;
         end if;
      end if;
      --  Chiave di "ISTANZE" non modificabile se esistono referenze su "CARATTERISTICHE_ACCESSO"
      if NEW_ISTANZA != OLD_ISTANZA
      then
         open  cfk2_caratteristiche_accesso(OLD_ISTANZA);
         fetch cfk2_caratteristiche_accesso into dummy;
         found := cfk2_caratteristiche_accesso%FOUND; /* %FOUND */
         close cfk2_caratteristiche_accesso;
         if found then
          errno  := -20005;
          errmsg := 'Esistono riferimenti su Caratteristiche_Accesso. La registrazione di Istanze non e'' modificabile. (CARATTERISTICHE_ACCESSO.CAAC_ISTA_FK)';
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

