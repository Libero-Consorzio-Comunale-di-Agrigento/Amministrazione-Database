--liquibase formatted sql

--changeset mturra:201901301231_360 runOnChange:true stripComments:false


create or replace trigger AMV_ARGOMENTI_TIU
   before INSERT or UPDATE on AMV_ARGOMENTI
for each row
/******************************************************************************
 NOME:        AMV_ARGOMENTI_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                       at INSERT or UPDATE on Table AMV_ARGOMENTI
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 09/10/2002 MF     Creazione.
******************************************************************************/
declare
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
begin
   begin  -- Check DATA Integrity on INSERT or UPDATE
      --  Column "ID_ARGOMENTO" attribuisce MAX+1
      if :NEW.ID_ARGOMENTO IS NULL and INSERTING then
         select nvl(max(ID_ARGOMENTO),0) + 1
           into :NEW.ID_ARGOMENTO
           from AMV_ARGOMENTI;
      end if;
      /* NONE */ null;
   end;
   begin  -- Check REFERENTIAL Integrity on INSERT or UPDATE
      /* NONE */ null;
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

