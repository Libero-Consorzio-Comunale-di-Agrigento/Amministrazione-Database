CREATE OR REPLACE procedure DIRITTI_ACCESSO_PI
/******************************************************************************
 NOME:        DIRITTI_ACCESSO_PI
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at INSERT on Table DIRITTI_ACCESSO
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20002, Non esiste riferimento su TABLE
             -20008, Numero di CHILD assegnato a TABLE non ammesso
 ANNOTAZIONI: Richiamata da Trigger DIRITTI_ACCESSO_TMB
              Salvata nella directory ins di AD4 con nome DIAC_PI.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
   1 30/05/2003 MM     Gestione del modulo * per gestione integrazione con
                       A00 ed SI.
******************************************************************************/
(new_utente IN varchar,
 new_modulo IN varchar,
 new_istanza IN varchar,
 new_ruolo IN varchar)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   cardinality      integer;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione di InsertChildParentExist per la tabella padre "ISTANZE"
   cursor cpk1_diritti_accesso(var_istanza varchar) is
      select 1
      from   ISTANZE
      where  ISTANZA = var_istanza
       and   var_istanza is not null;
   --  Dichiarazione di InsertChildParentExist per la tabella padre "MODULI"
   cursor cpk2_diritti_accesso(var_modulo varchar) is
      select 1
      from   MODULI
      where  ((MODULO = var_modulo
       and   var_modulo is not null)
      or   var_modulo = '*');
   --  Dichiarazione di InsertChildParentExist per la tabella padre "RUOLI"
   cursor cpk3_diritti_accesso(var_ruolo varchar) is
      select 1
      from   RUOLI
      where  RUOLO = var_ruolo
       and   var_ruolo is not null;
   --  Dichiarazione di InsertChildParentExist per la tabella padre "UTENTI"
   cursor cpk4_diritti_accesso(var_utente varchar) is
      select 1
      from   UTENTI
      where  UTENTE = var_utente
       and   var_utente is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      begin  --  Parent "ISTANZE" deve esistere quando si inserisce su "DIRITTI_ACCESSO"
         if NEW_ISTANZA is not null then
            open  cpk1_diritti_accesso(NEW_ISTANZA);
            fetch cpk1_diritti_accesso into dummy;
            found := cpk1_diritti_accesso%FOUND; /* %FOUND */
            close cpk1_diritti_accesso;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su Istanze. La registrazione Diritti Accesso non puo'' essere inserita.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "MODULI" deve esistere quando si inserisce su "DIRITTI_ACCESSO"
         if NEW_MODULO is not null then
            open  cpk2_diritti_accesso(NEW_MODULO);
            fetch cpk2_diritti_accesso into dummy;
            found := cpk2_diritti_accesso%FOUND; /* %FOUND */
            close cpk2_diritti_accesso;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su Moduli. La registrazione Diritti Accesso non puo'' essere inserita.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "RUOLI" deve esistere quando si inserisce su "DIRITTI_ACCESSO"
         if NEW_RUOLO is not null then
            open  cpk3_diritti_accesso(NEW_RUOLO);
            fetch cpk3_diritti_accesso into dummy;
            found := cpk3_diritti_accesso%FOUND; /* %FOUND */
            close cpk3_diritti_accesso;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su Ruoli. La registrazione Diritti Accesso non puo'' essere inserita.';
          raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "UTENTI" deve esistere quando si inserisce su "DIRITTI_ACCESSO"
         if NEW_UTENTE is not null then
            open  cpk4_diritti_accesso(NEW_UTENTE);
            fetch cpk4_diritti_accesso into dummy;
            found := cpk4_diritti_accesso%FOUND; /* %FOUND */
            close cpk4_diritti_accesso;
            if not found then
          errno  := -20002;
          errmsg := 'Non esiste riferimento su Utenti. La registrazione Diritti Accesso non puo'' essere inserita.';
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

