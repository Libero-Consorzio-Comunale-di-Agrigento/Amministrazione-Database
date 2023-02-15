CREATE OR REPLACE TRIGGER ALLINEAMENTO_SO4_LDAP_TIU
/******************************************************************************
 NOME:        ALLINEAMENTO_SO4_LDAP_TIU
 DESCRIZIONE: Trigger for Check / Set DATA Integrity
                          Check FUNCTIONAL Integrity
                            Set FUNCTIONAL Integrity
                       at INSERT or UPDATE or DELETE on Table ALLEGATI
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    1 06/06/2007 MM     Gestione della sequence ALSL_SQ
******************************************************************************/
   before INSERT or UPDATE or DELETE on ALLINEAMENTO_SO4_LDAP
for each row
declare
   functionalNestLevel integer;
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
begin
   functionalNestLevel := IntegrityPackage.GetNestLevel;
   /***************************************************************************
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ---------------------------------------------------
    1    24/06/2005 MF     Revisione Trigger con nuovo PowerDesigner Template.
   ***************************************************************************/
   begin  -- Check / Set DATA Integrity
      -- Rev. 1 06/06/2007 MM     Gestione della sequence EVEN_SQ
--      if :NEW.ID_ALLEGATO IS NULL and not DELETING then
--         :NEW.ID_ALLEGATO := SI4.NEXT_ID('ALLEGATI','ID_ALLEGATO');
--      end if;
      if :NEW.ID_ALLEGATO IS NULL and not DELETING then
         select ALSL_SQ.NEXTVAL
           into :new.ID_ALLEGATO
           from dual;
      end if;
      -- Rev. 1 06/06/2007 MM fine mod.
      null;
   end;
   begin  -- Check FUNCTIONAL Integrity
      begin  -- Check UNIQUE Integrity on PK of "ALLEGATI"
         if IntegrityPackage.GetNestLevel = 0 and not DELETING then
            declare
            cursor cpk_allegati(var_ID_ALLEGATO number) is
               select 1
                 from   ALLINEAMENTO_SO4_LDAP
                where  ID_ALLEGATO = var_ID_ALLEGATO;
            mutating         exception;
            PRAGMA exception_init(mutating, -4091);
            begin
               if :new.ID_ALLEGATO is not null then
                  open  cpk_allegati(:new.ID_ALLEGATO);
                  fetch cpk_allegati into dummy;
                  found := cpk_allegati%FOUND;
                  close cpk_allegati;
                  if found then
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :new.ID_ALLEGATO||
                               '" gia'' presente in ALLINEAMENTO_SO4_LDAP. La registrazione  non puo'' essere inserita.';
                     raise integrity_error;
                  end if;
               end if;
            exception
               when MUTATING then null;  -- Ignora Check su UNIQUE PK Integrity
            end;
         end if;
      end;
      null;
   end;
   begin  -- Set FUNCTIONAL Integrity
      if functionalNestLevel = 0 then
         IntegrityPackage.NextNestLevel;
         begin  -- Global FUNCTIONAL Integrity at Level 0
            /* NONE */ null;
         end;
        IntegrityPackage.PreviousNestLevel;
      end if;
      IntegrityPackage.NextNestLevel;
      begin  -- Full FUNCTIONAL Integrity at Any Level
         /* NONE */ null;
      end;
      IntegrityPackage.PreviousNestLevel;
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


