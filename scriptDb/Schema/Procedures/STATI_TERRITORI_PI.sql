CREATE OR REPLACE procedure STATI_TERRITORI_PI
/******************************************************************************
 NOME:        STATI_TERRITORI_PI
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at INSERT on Table STATI_TERRITORI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20002, Non esiste riferimento su TABLE
             -20008, Numero di CHILD assegnato a TABLE non ammesso
 ANNOTAZIONI: Richiamata da Trigger STATI_TERRITORI_TIU
              Salvata nella directory ins di AD4 con nome STTE_PI.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(new_raggruppamento IN number,
 new_stato_appartenenza IN number)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   cardinality      integer;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione di InsertChildParentExist per la tabella padre "STATI_TERRITORI"
   cursor cpk1_stati_territori(var_stato_appartenenza number) is
      select 1
      from   STATI_TERRITORI
      where  STATO_TERRITORIO = var_stato_appartenenza
       and   var_stato_appartenenza is not null;
   --  Dichiarazione di InsertChildParentExist per la tabella padre "RAGGRUPPAMENTI_STATI"
   cursor cpk2_stati_territori(var_raggruppamento number) is
      select 1
      from   RAGGRUPPAMENTI_STATI
      where  RAGGRUPPAMENTO = var_raggruppamento
       and   var_raggruppamento is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      begin  --  Parent "STATI_TERRITORI" deve esistere quando si inserisce su "STATI_TERRITORI"
         if NEW_STATO_APPARTENENZA is not null then
            open  cpk1_stati_territori(NEW_STATO_APPARTENENZA);
            fetch cpk1_stati_territori into dummy;
            found := cpk1_stati_territori%FOUND;
            close cpk1_stati_territori;
            if not found then
               errno  := -20002;
               errmsg := 'Non esiste riferimento su Stato o territorio estero. La registrazione Stato o territorio estero non puo'' essere inserita.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "RAGGRUPPAMENTI_STATI" deve esistere quando si inserisce su "STATI_TERRITORI"
         if NEW_RAGGRUPPAMENTO is not null then
            open  cpk2_stati_territori(NEW_RAGGRUPPAMENTO);
            fetch cpk2_stati_territori into dummy;
            found := cpk2_stati_territori%FOUND;
            close cpk2_stati_territori;
            if not found then
               errno  := -20002;
               errmsg := 'Non esiste riferimento su Raggruppamento stati esteri. La registrazione Stato o territorio estero non puo'' essere inserita.';
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

