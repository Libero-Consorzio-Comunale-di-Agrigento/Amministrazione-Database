--liquibase formatted sql

--changeset mturra:201901301231_321 runOnChange:true endDelimiter:/ stripComments:false


create or replace procedure COMUNI_RRI
/******************************************************************************
 NOME:        COMUNI_RRI
 DESCRIZIONE: Verifica modificabilita' della chiave.
 ARGOMENTI:   p_provincia    IN number
              p_comune       IN number
 ECCEZIONI:   20999, Esistono riferimenti su Comuni.
                     La registrazione di Comuni non e' modificabile.
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    12/03/2008 MM     Prima emissione.
******************************************************************************/
( old_provincia_stato IN number,
 old_comune IN number,
 new_provincia_stato IN number,
 new_comune IN number
)
is
   dummy            integer;
   found            boolean;
   errno            integer := -20005;
   errmsg           varchar2(1000) := 'Esistono riferimenti su Comuni';
   integrity_error  exception;
   --  Declaration of UpdateParentRestrict constraint for "COMUNI"
   cursor cfk1_comuni(var_comune number,
                      var_provincia_stato number) is
      select 1
      from   COMUNI
      where  COMUNE_FUSIONE = var_comune
       and   PROVINCIA_FUSIONE = var_provincia_stato
       and   var_comune is not null
       and   var_provincia_stato is not null;
   --  Declaration of UpdateParentRestrict constraint for "COMUNI"
   cursor cfk2_comuni(var_comune number,
                      var_provincia_stato number) is
      select 1
      from   COMUNI
      where  COMUNE_TRIBUNALE = var_comune
       and   PROVINCIA_TRIBUNALE = var_provincia_stato
       and   var_comune is not null
       and   var_provincia_stato is not null;
   --  Declaration of UpdateParentRestrict constraint for "COMUNI"
   cursor cfk3_comuni(var_comune number,
                      var_provincia_stato number) is
      select 1
      from   COMUNI
      where  COMUNE_DISTRETTO = var_comune
       and   PROVINCIA_DISTRETTO = var_provincia_stato
       and   var_comune is not null
       and   var_provincia_stato is not null;
begin
   --  Chiave di "COMUNI" non modificabile se esistono referenze su "COMUNI"
   if (OLD_PROVINCIA_STATO != NEW_PROVINCIA_STATO) or
      (OLD_COMUNE != NEW_COMUNE) then
      open  cfk1_comuni(OLD_COMUNE,
                        OLD_PROVINCIA_STATO);
      fetch cfk1_comuni into dummy;
      found := cfk1_comuni%FOUND;
      close cfk1_comuni;
      if found then
         errmsg := errmsg||' (fusione)';
      else
         open  cfk2_comuni(OLD_COMUNE,
                           OLD_PROVINCIA_STATO);
         fetch cfk2_comuni into dummy;
         found := cfk2_comuni%FOUND;
         close cfk2_comuni;
         if found then
            errmsg := errmsg||' (tribunale)';
         else
            open  cfk3_comuni(OLD_COMUNE,
                              OLD_PROVINCIA_STATO);
            fetch cfk3_comuni into dummy;
            found := cfk3_comuni%FOUND;
            close cfk3_comuni;
            if found then
               errmsg := errmsg||' (distretto)';
            end if;
         end if;
      end if;
      if found then
         errmsg := errmsg||'. La registrazione di Comuni non e'' modificabile.';
         raise integrity_error;
      end if;
   end if;
exception
   when integrity_error then
        raise_application_error(errno, errmsg);
   when others then
        raise;
end;
/

