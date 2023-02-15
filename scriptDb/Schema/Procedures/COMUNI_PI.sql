CREATE OR REPLACE procedure COMUNI_PI
/******************************************************************************
 NOME:        COMUNI_PI
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at INSERT on Table COMUNI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20002, Non esiste riferimento su TABLE
             -20008, Numero di CHILD assegnato a TABLE non ammesso
 ANNOTAZIONI: Richiamata da Trigger COMUNI_TIU
              Salvata nella directory ins di AD4 con nome COMU_PI.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(new_provincia_stato IN number,
 new_provincia_tribunale IN number,
 new_comune_tribunale IN number,
 new_provincia_distretto IN number,
 new_comune_distretto IN number,
 new_provincia_fusione IN number,
 new_comune_fusione IN number,
 new_consolato IN number,
 new_tipo_consolato IN number)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   cardinality      integer;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione di InsertChildParentExist per la tabella padre "CONSOLATI"
   cursor cpk1_comuni(var_consolato number,
                      var_tipo_consolato number,
                      var_provincia_stato number) is
      select 1
      from   CONSOLATI
      where  CONSOLATO = var_consolato
       and   TIPO_CONSOLATO = var_tipo_consolato
       and   STATO = var_provincia_stato
       and   var_consolato is not null
       and   var_tipo_consolato is not null
       and   var_provincia_stato is not null;
   --  Dichiarazione di InsertChildParentExist per la tabella padre "COMUNI"
   cursor cpk2_comuni(var_comune_fusione number,
                      var_provincia_fusione number) is
      select 1
      from   COMUNI
      where  COMUNE = var_comune_fusione
       and   PROVINCIA_STATO = var_provincia_fusione
       and   var_comune_fusione is not null
       and   var_provincia_fusione is not null;
   --  Dichiarazione di InsertChildParentExist per la tabella padre "COMUNI"
   cursor cpk3_comuni(var_comune_tribunale number,
                      var_provincia_tribunale number) is
      select 1
      from   COMUNI
      where  COMUNE = var_comune_tribunale
       and   PROVINCIA_STATO = var_provincia_tribunale
       and   var_comune_tribunale is not null
       and   var_provincia_tribunale is not null;
   --  Dichiarazione di InsertChildParentExist per la tabella padre "COMUNI"
   cursor cpk4_comuni(var_comune_distretto number,
                      var_provincia_distretto number) is
      select 1
      from   COMUNI
      where  COMUNE = var_comune_distretto
       and   PROVINCIA_STATO = var_provincia_distretto
       and   var_comune_distretto is not null
       and   var_provincia_distretto is not null;
   --  Dichiarazione di InsertChildParentExist per la tabella padre "STATI_TERRITORI"
   cursor cpk_stte_comuni(var_provincia_stato number) is
      select 1
      from   STATI_TERRITORI
      where  STATO_TERRITORIO = var_provincia_stato
       and   var_provincia_stato is not null
       and   var_provincia_stato >= 200;
   --  Dichiarazione di InsertChildParentExist per la tabella padre "PROVINCE"
   cursor cpk_prov_comuni(var_provincia_stato number) is
      select 1
      from   PROVINCE
      where  PROVINCIA = var_provincia_stato
       and   var_provincia_stato is not null
       and   var_provincia_stato < 200;
begin
   begin  -- Check REFERENTIAL Integrity
      begin  --  Parent "CONSOLATI" deve esistere quando si inserisce su "COMUNI"
         if NEW_CONSOLATO is not null and
            NEW_TIPO_CONSOLATO is not null and
            NEW_PROVINCIA_STATO is not null then
            open  cpk1_comuni(NEW_CONSOLATO,
                              NEW_TIPO_CONSOLATO,
                              NEW_PROVINCIA_STATO);
            fetch cpk1_comuni into dummy;
            found := cpk1_comuni%FOUND;
            close cpk1_comuni;
            if not found then
               errno  := -20002;
               errmsg := 'Non esiste riferimento su Consolati. La registrazione Comuni non puo'' essere inserita.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "COMUNI" deve esistere quando si inserisce su "COMUNI"
         if NEW_COMUNE_FUSIONE is not null and
            NEW_PROVINCIA_FUSIONE is not null then
            open  cpk2_comuni(NEW_COMUNE_FUSIONE,
                              NEW_PROVINCIA_FUSIONE);
            fetch cpk2_comuni into dummy;
            found := cpk2_comuni%FOUND;
            close cpk2_comuni;
            if not found then
               errno  := -20002;
               errmsg := 'Non esiste riferimento su Comuni. La registrazione Comuni non puo'' essere inserita.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
--
      begin  --  Parent "COMUNI" deve esistere quando si inserisce su "COMUNI"
         if NEW_COMUNE_TRIBUNALE is not null and
            NEW_PROVINCIA_TRIBUNALE is not null then
            open  cpk3_comuni(NEW_COMUNE_TRIBUNALE,
                              NEW_PROVINCIA_TRIBUNALE);
            fetch cpk3_comuni into dummy;
            found := cpk3_comuni%FOUND;
            close cpk3_comuni;
            if not found then
               errno  := -20002;
               errmsg := 'Non esiste riferimento su Comuni. La registrazione Comuni non puo'' essere inserita.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "COMUNI" deve esistere quando si inserisce su "COMUNI"
         if NEW_COMUNE_DISTRETTO is not null and
            NEW_PROVINCIA_DISTRETTO is not null then
            open  cpk4_comuni(NEW_COMUNE_DISTRETTO,
                              NEW_PROVINCIA_DISTRETTO);
            fetch cpk4_comuni into dummy;
            found := cpk4_comuni%FOUND;
            close cpk4_comuni;
            if not found then
               errno  := -20002;
               errmsg := 'Non esiste riferimento su Comuni. La registrazione Comuni non puo'' essere inserita.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "STATI_TERRITORI" deve esistere quando si inserisce su "COMUNI"
         if NEW_PROVINCIA_STATO is not null and NEW_PROVINCIA_STATO >= 200 then
            open  cpk_stte_comuni(NEW_PROVINCIA_STATO);
            fetch cpk_stte_comuni into dummy;
            found := cpk_stte_comuni%FOUND;
            close cpk_stte_comuni;
            if not found then
               errno  := -20002;
               errmsg := 'Non esiste riferimento su Stato o territorio estero. La registrazione Comuni non puo'' essere inserita.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "PROVINCE" deve esistere quando si inserisce su "COMUNI"
         if NEW_PROVINCIA_STATO is not null and NEW_PROVINCIA_STATO < 200 then
            open  cpk_prov_comuni(NEW_PROVINCIA_STATO);
            fetch cpk_prov_comuni into dummy;
            found := cpk_prov_comuni%FOUND;
            close cpk_prov_comuni;
            if not found then
               errno  := -20002;
               errmsg := 'Non esiste riferimento su Provincia. La registrazione Comuni non puo'' essere inserita.';
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

