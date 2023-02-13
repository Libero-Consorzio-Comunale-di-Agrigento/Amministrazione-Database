--liquibase formatted sql

--changeset mturra:201901301231_397 runOnChange:true stripComments:false


create or replace trigger ISTANZE_TMB
   before INSERT or UPDATE on ISTANZE
for each row
/******************************************************************************
 NOME:        ISTANZE_TMB
 DESCRIZIONE: Trigger for Check REFERENTIAL Integrity
                       at INSERT or UPDATE on Table ISTANZE
 ANNOTAZIONI: Richiama Procedure ISTANZE_PI e ISTANZE_PU
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
******************************************************************************/
declare
   integrity_error  exception;
   errno            integer;
   errmsg           char(200);
   dummy            integer;
   found            boolean;
begin
   begin  -- Check REFERENTIAL Integrity on INSERT or UPDATE
      if INSERTING then
         ISTANZE_PI( :NEW.ISTANZA
                        , :NEW.PROGETTO
                        , :NEW.ENTE
                        , :NEW.LINGUA
                        , :NEW.ISTANZA_AMMINISTRATORE );
         null;
      end if;
      if UPDATING then
         ISTANZE_PU( :OLD.ISTANZA
                        , :OLD.PROGETTO
                        , :OLD.ENTE
                        , :OLD.LINGUA
                        , :OLD.ISTANZA_AMMINISTRATORE
                        , :NEW.ISTANZA
                        , :NEW.PROGETTO
                        , :NEW.ENTE
                        , :NEW.LINGUA
                        , :NEW.ISTANZA_AMMINISTRATORE );
         null;
      end if;
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

