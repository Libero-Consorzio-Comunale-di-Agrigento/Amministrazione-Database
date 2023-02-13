--liquibase formatted sql

--changeset mturra:201901301231_322 runOnChange:true endDelimiter:/ stripComments:false


create or replace procedure CONSOLATI_PU
/******************************************************************************
 NOME:        CONSOLATI_PU
 DESCRIZIONE: Procedure for Check REFERENTIAL Integrity
                         at UPDATE on Table CONSOLATI
 ARGOMENTI:   Rigenerati in automatico.
 ECCEZIONI:  -20001, Informazione COLONNA non modificabile
             -20003, Non esiste riferimento su PARENT TABLE
             -20004, Identificazione di TABLE non modificabile
             -20005, Esistono riferimenti su CHILD TABLE
 ANNOTAZIONI: Richiamata da Trigger CONSOLATI_TIU
              Salvata nella directory ins di AD4 con nome CONS_PU.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generata in automatico.
******************************************************************************/
(old_consolato IN number,
 old_tipo_consolato IN number,
 old_stato IN number,
 new_consolato IN number,
 new_tipo_consolato IN number,
 new_stato IN number)
is
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
   seq              number;
   mutating         exception;
   PRAGMA exception_init(mutating, -4091);
   --  Declaration of UpdateParentRestrict constraint for "COMUNI"
   cursor cfk1_consolati(var_consolato number,
                         var_tipo_consolato number,
                         var_stato number) is
      select 1
      from   COMUNI
      where  CONSOLATO = var_consolato
       and   TIPO_CONSOLATO = var_tipo_consolato
       and   PROVINCIA_STATO = var_stato
       and   var_consolato is not null
       and   var_tipo_consolato is not null
       and   var_stato is not null;
begin
   begin  -- Check REFERENTIAL Integrity
      seq := IntegrityPackage.GetNestLevel;
      --  Chiave di "CONSOLATI" non modificabile se esistono referenze su "COMUNI"
      if (OLD_CONSOLATO != NEW_CONSOLATO) or
         (OLD_TIPO_CONSOLATO != NEW_TIPO_CONSOLATO) or
         (OLD_STATO != NEW_STATO) then
         open  cfk1_consolati(OLD_CONSOLATO,
                              OLD_TIPO_CONSOLATO,
                              OLD_STATO);
         fetch cfk1_consolati into dummy;
         found := cfk1_consolati%FOUND;
         close cfk1_consolati;
         if found then
            errno  := -20005;
            errmsg := 'Esistono riferimenti su Comuni. La registrazione di Consolati non e'' modificabile.';
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

