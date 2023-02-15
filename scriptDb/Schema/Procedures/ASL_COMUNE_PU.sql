CREATE OR REPLACE procedure ASL_COMUNE_PU
/******************************************************************************
 NOME:        ASL_COMUNE_PU
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at UPDATE on Table ASL_COMUNE
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20001, Informazione COLONNA non modificabile
             -20003, Non esiste riferimento su PARENT TABLE
             -20004, Identificazione di TABLE non modificabile
             -20005, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger ASL_COMUNE_TIU
              Salvata nella directory ins di AD4 con nome ASCO_PU.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(  old_provincia IN number
 , old_comune IN number
 , old_regione_asl IN number
 , old_codice_asl IN number
 , new_provincia IN number
 , new_comune IN number
 , new_regione_asl IN number
 , new_codice_asl IN number
)
is
   integrity_error  exception;
   MUTATING  exception;
   PRAGMA exception_init(MUTATING,-4091);
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   seq NUMBER;
   --  Declaration of UpdateChildParentExist constraint for the parent "ASL"
   cursor cpk1_asl_comune(var_regione_asl number,
                   var_codice_asl number) is
      select 1
      from   ASL
      where  REGIONE_ASL = var_regione_asl
       and   CODICE_ASL = var_codice_asl
       and   var_regione_asl is not null
       and   var_codice_asl is not null;
   --  Declaration of UpdateChildParentExist constraint for the parent "PROVINCE"
   cursor cpk2_asl_comune(var_provincia number) is
      select 1
      from   PROVINCE
      where  PROVINCIA = var_provincia
       and   var_provincia is not null;
   --  Declaration of UpdateChildParentExist constraint for the parent "COMUNI"
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
      seq := IntegrityPackage.GetNestLevel;
      begin  --  Parent "ASL" deve esistere quando si modifica "ASL_COMUNE"
         if  NEW_REGIONE_ASL is not null and
             NEW_CODICE_ASL is not null and ( seq = 0 )
         and (   (NEW_REGIONE_ASL != OLD_REGIONE_ASL or OLD_REGIONE_ASL is null)
              or (NEW_CODICE_ASL != OLD_CODICE_ASL or OLD_CODICE_ASL is null) ) then
            open  cpk1_asl_comune(NEW_REGIONE_ASL,
                           NEW_CODICE_ASL);
            fetch cpk1_asl_comune into dummy;
            found := cpk1_asl_comune%FOUND; /* %FOUND */
            close cpk1_asl_comune;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su ASL. La registrazione ASL_COMUNE non e'''' modificabile.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "PROVINCE" deve esistere quando si modifica "ASL_COMUNE"
         if  NEW_PROVINCIA is not null and ( seq = 0 )
         and (   (NEW_PROVINCIA != OLD_PROVINCIA or OLD_PROVINCIA is null) ) then
            open  cpk2_asl_comune(NEW_PROVINCIA);
            fetch cpk2_asl_comune into dummy;
            found := cpk2_asl_comune%FOUND; /* %FOUND */
            close cpk2_asl_comune;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su Provincia. La registrazione ASL_COMUNE non e'''' modificabile.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "COMUNI" deve esistere quando si modifica "ASL_COMUNE"
         if  NEW_PROVINCIA is not null and
             NEW_COMUNE is not null and ( seq = 0 )
         and (   (NEW_PROVINCIA != OLD_PROVINCIA or OLD_PROVINCIA is null)
              or (NEW_COMUNE != OLD_COMUNE or OLD_COMUNE is null) ) then
            open  cpk3_asl_comune(NEW_PROVINCIA,
                           NEW_COMUNE);
            fetch cpk3_asl_comune into dummy;
            found := cpk3_asl_comune%FOUND; /* %FOUND */
            close cpk3_asl_comune;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su Comuni. La registrazione ASL_COMUNE non e'''' modificabile.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      --  Chiave di "ASL" non modificabile sul figlio: "ASL_COMUNE"
      if (OLD_REGIONE_ASL != NEW_REGIONE_ASL) or
         (OLD_CODICE_ASL != NEW_CODICE_ASL) then
         if IntegrityPackage.GetNestLevel = 0 then
          errno  := -20004;
          errmsg := 'L''''identificazione di ASL non e'''' modificabile su ASL_COMUNE.';
          raise integrity_error;
         end if;
      end if;
      --  Chiave di "PROVINCE" non modificabile sul figlio: "ASL_COMUNE"
      if (OLD_PROVINCIA != NEW_PROVINCIA) then
         if IntegrityPackage.GetNestLevel = 0 then
          errno  := -20004;
          errmsg := 'L''''identificazione di Provincia non e'''' modificabile su ASL_COMUNE.';
          raise integrity_error;
         end if;
      end if;
      --  Chiave di "COMUNI" non modificabile sul figlio: "ASL_COMUNE"
      if (OLD_PROVINCIA != NEW_PROVINCIA) or
         (OLD_COMUNE != NEW_COMUNE) then
         if IntegrityPackage.GetNestLevel = 0 then
          errno  := -20004;
          errmsg := 'L''''identificazione di Comuni non e'''' modificabile su ASL_COMUNE.';
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

