CREATE OR REPLACE procedure CARATTERISTICHE_ACCESSO_PU
-- Salvata nella directory ins di AD4 con nome CAAC_PU.CRP
(old_caac_id number,
 old_progetto IN varchar,
 old_istanza IN varchar,
 old_modulo IN varchar,
 old_utente IN varchar,
 new_caac_id number,
 new_progetto IN varchar,
 new_istanza IN varchar,
 new_modulo IN varchar,
 new_utente IN varchar)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   seq              number;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "DIRITTI_ACCESSO"
   cursor cpk1_caratteristiche_accesso(var_utente varchar,
                                       var_modulo varchar,
                                       var_istanza varchar) is
      select 1
      from   DIRITTI_ACCESSO
      where  UTENTE = var_utente
       and   MODULO = var_modulo
       and   ISTANZA = var_istanza
       and   var_utente is not null
       and   var_modulo is not null
       and   var_istanza is not null;
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "MODULI"
   cursor cpk2_caratteristiche_accesso(var_modulo varchar) is
      select 1
      from   MODULI
      where  ((MODULO = var_modulo
       and   var_modulo is not null)
      or   var_modulo = '*');
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "ISTANZE"
   cursor cpk3_caratteristiche_accesso(var_istanza varchar) is
      select 1
      from   ISTANZE
      where  ISTANZA = var_istanza
       and   var_istanza is not null;
   --  Dichiarazione UpdateChildParentExist constraint per la tabella "PROGETTI"
   cursor cpk4_caratteristiche_accesso(var_progetto varchar) is
      select 1
      from   PROGETTI
      where  PROGETTO = var_progetto
       and   var_progetto is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      seq := IntegrityPackage.GetNestLevel;
      begin  --  Parent "DIRITTI_ACCESSO" deve esistere quando si modifica "CARATTERISTICHE_ACCESSO"
         if  NEW_UTENTE is not null and
             NEW_MODULO is not null and
             NEW_ISTANZA is not null and ( seq = 0 )
         and (   (NEW_UTENTE != OLD_UTENTE or OLD_UTENTE is null)
              or (NEW_MODULO != OLD_MODULO or OLD_MODULO is null)
              or (NEW_ISTANZA != OLD_ISTANZA or OLD_ISTANZA is null) ) then
            open  cpk1_caratteristiche_accesso(NEW_UTENTE,
                                               NEW_MODULO,
                                               NEW_ISTANZA);
            fetch cpk1_caratteristiche_accesso into dummy;
            found := cpk1_caratteristiche_accesso%FOUND;
            close cpk1_caratteristiche_accesso;
            if not found then
               errno  := -20003;
               errmsg := 'Non esiste riferimento su Diritti Accesso. La registrazione CaratteristicheAccesso non e'' modificabile.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "MODULI" deve esistere quando si modifica "CARATTERISTICHE_ACCESSO"
         if  NEW_MODULO is not null and ( seq = 0 )
         and (   (NEW_MODULO != OLD_MODULO or OLD_MODULO is null) ) then
            open  cpk2_caratteristiche_accesso(NEW_MODULO);
            fetch cpk2_caratteristiche_accesso into dummy;
            found := cpk2_caratteristiche_accesso%FOUND;
            close cpk2_caratteristiche_accesso;
            if not found then
               errno  := -20003;
               errmsg := 'Non esiste riferimento su Moduli. La registrazione CaratteristicheAccesso non e'' modificabile.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "ISTANZE" deve esistere quando si modifica "CARATTERISTICHE_ACCESSO"
         if  NEW_ISTANZA is not null and ( seq = 0 )
         and (   (NEW_ISTANZA != OLD_ISTANZA or OLD_ISTANZA is null) ) then
            open  cpk3_caratteristiche_accesso(NEW_ISTANZA);
            fetch cpk3_caratteristiche_accesso into dummy;
            found := cpk3_caratteristiche_accesso%FOUND;
            close cpk3_caratteristiche_accesso;
            if not found then
               errno  := -20003;
               errmsg := 'Non esiste riferimento su Istanze. La registrazione CaratteristicheAccesso non e'' modificabile.';
               raise integrity_error;
            end if;
         end if;
      exception
         when MUTATING then null;  -- Ignora Check su Relazioni Ricorsive
      end;
      begin  --  Parent "PROGETTI" deve esistere quando si modifica "CARATTERISTICHE_ACCESSO"
         if  NEW_PROGETTO is not null and ( seq = 0 )
         and (   (NEW_PROGETTO != OLD_PROGETTO or OLD_PROGETTO is null) ) then
            open  cpk4_caratteristiche_accesso(NEW_PROGETTO);
            fetch cpk4_caratteristiche_accesso into dummy;
            found := cpk4_caratteristiche_accesso%FOUND;
            close cpk4_caratteristiche_accesso;
            if not found then
               errno  := -20003;
               errmsg := 'Non esiste riferimento su Progetti. La registrazione CaratteristicheAccesso non e'' modificabile.';
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

