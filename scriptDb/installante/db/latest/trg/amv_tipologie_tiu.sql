--liquibase formatted sql

--changeset mturra:201901301231_368 runOnChange:true stripComments:false


create or replace trigger AMV_TIPOLOGIE_TIU
   before INSERT or UPDATE on AMV_TIPOLOGIE
for each row
/******************************************************************************
 NOME:        AMV_TIPOLOGIE_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                       at INSERT or UPDATE on Table AMV_TIPOLOGIE
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
      --  Column "ID_TIPOLOGIA" attribuisce MAX+1
      if :NEW.ID_TIPOLOGIA IS NULL and INSERTING then
         select nvl(max(ID_TIPOLOGIA),0) + 1
           into :NEW.ID_TIPOLOGIA
           from AMV_TIPOLOGIE;
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

