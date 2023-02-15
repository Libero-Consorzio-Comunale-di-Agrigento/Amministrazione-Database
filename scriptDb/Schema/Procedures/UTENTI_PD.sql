CREATE OR REPLACE procedure UTENTI_PD
/******************************************************************************
 NOME:        UTENTI_PD
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at DELETE on Table UTENTI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20006, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger UTENTI_TD
              Salvata nella directory ins di AD4 con nome UTEN_PD.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
    1 30/05/2003 MM     Eliminato il controllo sull'esistenza dell'utente da
                       cancellare nel campo UTENTE_AGGIORNAMENTO di UTENTI
                  stessa per evitare il MUTATING.
******************************************************************************/
(old_utente IN varchar)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   --  Declaration of DeleteParentRestrict constraint for "DIRITTI_ACCESSO"
   cursor cfk1_diritti_accesso(var_utente varchar) is
      select 1
      from   DIRITTI_ACCESSO
      where  UTENTE = var_utente
       and   var_utente is not null;
   --  Declaration of DeleteParentRestrict constraint for "UTENTI_SOGGETTI"
   cursor cfk2_utenti_soggetti(var_utente varchar) is
      select 1
      from   UTENTI_SOGGETTI
      where  UTENTE = var_utente
       and   var_utente is not null;
--    --  Declaration of DeleteParentRestrict constraint for "UTENTI"
--    cursor cfk3_utenti(var_utente varchar) is
--       select 1
--       from   UTENTI
--       where  UTENTE_AGGIORNAMENTO = var_utente
--        and   var_utente is not null;
   --  Declaration of DeleteParentRestrict constraint for "UTENTI_GRUPPO"
   cursor cfk4_utenti_gruppo(var_utente varchar) is
      select 1
      from   UTENTI_GRUPPO
      where  UTENTE = var_utente
       and   var_utente is not null;
   --  Declaration of DeleteParentRestrict constraint for "UTENTI_GRUPPO"
   cursor cfk5_utenti_gruppo(var_utente varchar) is
      select 1
      from   UTENTI_GRUPPO
      where  GRUPPO = var_utente
       and   var_utente is not null;
   --  Declaration of DeleteParentRestrict constraint for "DIRITTI_ACCESSO"
   cursor cfk6_diritti_accesso(var_utente varchar) is
      select 1
      from   DIRITTI_ACCESSO
      where  GRUPPO = var_utente
       and   var_utente is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      --  Cannot delete parent "UTENTI" if children still exist in "DIRITTI_ACCESSO"
      open  cfk1_diritti_accesso(OLD_UTENTE);
      fetch cfk1_diritti_accesso into dummy;
      found := cfk1_diritti_accesso%FOUND; /* %FOUND */
      close cfk1_diritti_accesso;
      if found then
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Diritti Accesso. La registrazione di Utenti non e'' eliminabile.';
          raise integrity_error;
      end if;
      --  Cannot delete parent "UTENTI" if children still exist in "UTENTI_SOGGETTI"
      open  cfk2_utenti_soggetti(OLD_UTENTE);
      fetch cfk2_utenti_soggetti into dummy;
      found := cfk2_utenti_soggetti%FOUND; /* %FOUND */
      close cfk2_utenti_soggetti;
      if found then
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Utenti Soggetti. La registrazione di Utenti non e'' eliminabile.';
          raise integrity_error;
      end if;
--       --  Cannot delete parent "UTENTI" if children still exist in "UTENTI"
--       open  cfk3_utenti(OLD_UTENTE);
--       fetch cfk3_utenti into dummy;
--       found := cfk3_utenti%FOUND; /* %FOUND */
--       close cfk3_utenti;
--       if found then
--           errno  := -20006;
--           errmsg := 'Esistono riferimenti su Utenti. La registrazione di Utenti non e'' eliminabile.';
--           raise integrity_error;
--       end if;
      --  Cannot delete parent "UTENTI" if children still exist in "UTENTI_GRUPPO"
      open  cfk4_utenti_gruppo(OLD_UTENTE);
      fetch cfk4_utenti_gruppo into dummy;
      found := cfk4_utenti_gruppo%FOUND; /* %FOUND */
      close cfk4_utenti_gruppo;
      if found then
          errno  := -20006;
          errmsg := 'Esistono riferimenti su UTENTI_GRUPPO. La registrazione di Utenti non e'' eliminabile.';
          raise integrity_error;
      end if;
      --  Cannot delete parent "UTENTI" if children still exist in "UTENTI_GRUPPO"
      open  cfk5_utenti_gruppo(OLD_UTENTE);
      fetch cfk5_utenti_gruppo into dummy;
      found := cfk5_utenti_gruppo%FOUND; /* %FOUND */
      close cfk5_utenti_gruppo;
      if found then
          errno  := -20006;
          errmsg := 'Esistono riferimenti su UTENTI_GRUPPO. La registrazione di Utenti non e'' eliminabile.';
          raise integrity_error;
      end if;
      --  Cannot delete parent "UTENTI" if children still exist in "DIRITTI_ACCESSO"
      open  cfk6_diritti_accesso(OLD_UTENTE);
      fetch cfk6_diritti_accesso into dummy;
      found := cfk6_diritti_accesso%FOUND; /* %FOUND */
      close cfk6_diritti_accesso;
      if found then
          errno  := -20006;
          errmsg := 'Esistono riferimenti su Diritti Accesso. La registrazione di Utenti non e'' eliminabile.';
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

