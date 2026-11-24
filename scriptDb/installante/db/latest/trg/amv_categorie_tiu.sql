--liquibase formatted sql

--changeset mturra:201901301231_362 runOnChange:true stripComments:false


create or replace trigger AMV_CATEGORIE_TIU
   before INSERT or UPDATE on AMV_CATEGORIE
for each row
/******************************************************************************
 NOME:        AMV_CATEGORIE_TIU
 DESCRIZIONE: Trigger for Check DATA Integrity
                          Check REFERENTIAL Integrity
                       at INSERT or UPDATE on Table AMV_CATEGORIE
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
      --  Column "ID_CATEGORIA" attribuisce MAX+1
      if :NEW.ID_CATEGORIA IS NULL and INSERTING then
         select nvl(max(ID_CATEGORIA),0) + 1
           into :NEW.ID_CATEGORIA
           from AMV_CATEGORIE;
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

