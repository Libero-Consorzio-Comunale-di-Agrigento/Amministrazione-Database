--liquibase formatted sql

--changeset snegroni:202002181537 runOnChange:true stripComments:false

create or replace TRIGGER ASL_COMUNE_TC
AFTER DELETE OR INSERT OR UPDATE
ON ASL_COMUNE
BEGIN
   -- Exec PostEvent Check REFERENTIAL Integrity
   Integritypackage.Exec_PostEvent;
END;
/