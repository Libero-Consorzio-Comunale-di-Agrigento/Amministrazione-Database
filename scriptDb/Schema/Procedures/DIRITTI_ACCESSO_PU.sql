CREATE OR REPLACE procedure DIRITTI_ACCESSO_PU
/******************************************************************************
 NOME:        DIRITTI_ACCESSO_PU
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at UPDATE on Table DIRITTI_ACCESSO
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20001, Informazione COLONNA non modificabile
             -20003, Non esiste riferimento su PARENT TABLE
             -20004, Identificazione di TABLE non modificabile
             -20005, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger DIRITTI_ACCESSO_TIU
              Salvata nella directory ins di AD4 con nome DIAC_PU.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
    1 03/01/2003 MM     Gestione diritti di gruppo.
   2 30/05/2003 MM     Gestione del modulo * per gestione integrazione con
                       A00 ed SI.
******************************************************************************/
(  old_utente IN varchar
 , old_modulo IN varchar
 , old_istanza IN varchar
 , old_ruolo IN varchar
 , new_utente IN varchar
 , new_modulo IN varchar
 , new_istanza IN varchar
 , new_ruolo IN varchar
)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   seq              number;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "ISTANZE"
   cursor cpk1_diritti_accesso(var_istanza varchar) is
      select 1
      from   ISTANZE
      where  ISTANZA = var_istanza
       and   var_istanza is not null;
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "MODULI"
   cursor cpk2_diritti_accesso(var_modulo varchar) is
      select 1
      from   MODULI
      where  ((MODULO = var_modulo
        and   var_modulo is not null)
        or   var_modulo = '*');
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "RUOLI"
   cursor cpk3_diritti_accesso(var_ruolo varchar) is
      select 1
      from   RUOLI
      where  RUOLO = var_ruolo
       and   var_ruolo is not null;
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "UTENTI"
   cursor cpk4_diritti_accesso(var_utente varchar) is
      select 1
      from   UTENTI
      where  UTENTE = var_utente
       and   var_utente is not null;
--   --  Declaration of UpdateParentRestrict constraint for "ACCESSI"
--   cursor cfk1_accessi(var_utente varchar,
--                   var_modulo varchar,
--                   var_istanza varchar) is
--      select 1
--      from   ACCESSI
--      where  UTENTE = var_utente
--       and   MODULO = var_modulo
--       and   ISTANZA = var_istanza
--       and   var_utente is not null
--       and   var_modulo is not null
--       and   var_istanza is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      seq := IntegrityPackage.GetNestLevel;
      begin  --  Parent "ISTANZE" deve esistere quando si modifica "DIRITTI_ACCESSO"
         if  NEW_ISTANZA is not null and ( seq = 0 )
         and (   (NEW_ISTANZA != OLD_ISTANZA or OLD_ISTANZA is null) ) then
            open  cpk1_diritti_accesso(NEW_ISTANZA);
            fetch cpk1_diritti_accesso into dummy;
            found := cpk1_diritti_accesso%FOUND; /* %FOUND */
            close cpk1_diritti_accesso;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su Istanze. La registrazione Diritti Accesso non e'' modificabile.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "MODULI" deve esistere quando si modifica "DIRITTI_ACCESSO"
         if  NEW_MODULO is not null and ( seq = 0 )
         and (   (NEW_MODULO != OLD_MODULO or OLD_MODULO is null) ) then
            open  cpk2_diritti_accesso(NEW_MODULO);
            fetch cpk2_diritti_accesso into dummy;
            found := cpk2_diritti_accesso%FOUND; /* %FOUND */
            close cpk2_diritti_accesso;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su Moduli. La registrazione Diritti Accesso non e'' modificabile.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "RUOLI" deve esistere quando si modifica "DIRITTI_ACCESSO"
         if  NEW_RUOLO is not null and ( seq = 0 )
         and (   (NEW_RUOLO != OLD_RUOLO or OLD_RUOLO is null) ) then
            open  cpk3_diritti_accesso(NEW_RUOLO);
            fetch cpk3_diritti_accesso into dummy;
            found := cpk3_diritti_accesso%FOUND; /* %FOUND */
            close cpk3_diritti_accesso;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su Ruoli. La registrazione Diritti Accesso non e'' modificabile.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "UTENTI" deve esistere quando si modifica "DIRITTI_ACCESSO"
         if  NEW_UTENTE is not null and ( seq = 0 )
         and (   (NEW_UTENTE != OLD_UTENTE or OLD_UTENTE is null) ) then
            open  cpk4_diritti_accesso(NEW_UTENTE);
            fetch cpk4_diritti_accesso into dummy;
            found := cpk4_diritti_accesso%FOUND; /* %FOUND */
            close cpk4_diritti_accesso;
            if not found then
          errno  := -20003;
          errmsg := 'Non esiste riferimento su Utenti. La registrazione Diritti Accesso non e'' modificabile.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      --  Chiave di "UTENTI" non modificabile sul figlio: "DIRITTI_ACCESSO"
      if (OLD_UTENTE != NEW_UTENTE) then
         if IntegrityPackage.GetNestLevel = 0 then
          errno  := -20004;
          errmsg := 'L''identificazione di Utenti non e'' modificabile su Diritti Accesso.';
          raise integrity_error;
         end if;
      end if;
--      --  Chiave di "DIRITTI_ACCESSO" non modificabile se esistono referenze su "ACCESSI"
--      if (OLD_UTENTE != NEW_UTENTE) or
--         (OLD_MODULO != NEW_MODULO) or
--         (OLD_ISTANZA != NEW_ISTANZA) then
--         open  cfk1_accessi(OLD_UTENTE,
--                        OLD_MODULO,
--                        OLD_ISTANZA);
--         fetch cfk1_accessi into dummy;
--         found := cfk1_accessi%FOUND; /* %FOUND */
--         close cfk1_accessi;
--         if found then
--          errno  := -20005;
--          errmsg := 'Esistono riferimenti su Accessi. La registrazione di Diritti Accesso non e'' modificabile.';
--          raise integrity_error;
--         end if;
--      end if;
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

