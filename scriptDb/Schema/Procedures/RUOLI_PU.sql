CREATE OR REPLACE procedure RUOLI_PU
/******************************************************************************
 NOME:        RUOLI_PU
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at UPDATE on Table RUOLI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20001, Informazione COLONNA non modificabile
             -20003, Non esiste riferimento su PARENT TABLE
             -20004, Identificazione di TABLE non modificabile
             -20005, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger RUOLI_TIU
              Salvata nella directory ins di AD4 con nome RUOL_PU.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(  old_ruolo IN varchar
 , new_ruolo IN varchar
)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   seq NUMBER;
   --  Declaration of UpdateParentRestrict constraint for "DIRITTI_ACCESSO"
   cursor cfk1_diritti_accesso(var_ruolo varchar) is
      select 1
      from   DIRITTI_ACCESSO
      where  RUOLO = var_ruolo
       and   var_ruolo is not null;
   --  Declaration of UpdateParentRestrict constraint for "DISABILITAZIONI"
   cursor cfk2_disabilitazioni(var_ruolo varchar) is
      select 1
      from   DISABILITAZIONI
      where  RUOLO = var_ruolo
       and   var_ruolo is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      seq := IntegrityPackage.GetNestLevel;
      --  Chiave di "RUOLI" non modificabile se esistono referenze su "DIRITTI_ACCESSO"
      if (OLD_RUOLO != NEW_RUOLO) then
         open  cfk1_diritti_accesso(OLD_RUOLO);
         fetch cfk1_diritti_accesso into dummy;
         found := cfk1_diritti_accesso%FOUND; /* %FOUND */
         close cfk1_diritti_accesso;
         if found then
          errno  := -20005;
          errmsg := 'Esistono riferimenti su Diritti Accesso. La registrazione di Ruoli non e'' modificabile.';
          raise integrity_error;
         end if;
      end if;
      --  Chiave di "RUOLI" non modificabile se esistono referenze su "DISABILITAZIONI"
      if (OLD_RUOLO != NEW_RUOLO) then
         open  cfk2_disabilitazioni(OLD_RUOLO);
         fetch cfk2_disabilitazioni into dummy;
         found := cfk2_disabilitazioni%FOUND; /* %FOUND */
         close cfk2_disabilitazioni;
         if found then
          errno  := -20005;
          errmsg := 'Esistono riferimenti su Disabilitazioni. La registrazione di Ruoli non e'' modificabile.';
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

