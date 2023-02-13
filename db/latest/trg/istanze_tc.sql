--liquibase formatted sql

--changeset mturra:201901301231_394 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER ISTANZE_TC
AFTER DELETE OR INSERT OR UPDATE
ON ISTANZE
BEGIN
   -- Exec PostEvent Check REFERENTIAL Integrity
   Integritypackage.Exec_PostEvent;
END;

/

