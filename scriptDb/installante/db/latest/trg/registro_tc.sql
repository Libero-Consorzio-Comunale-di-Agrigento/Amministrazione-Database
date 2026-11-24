--liquibase formatted sql

--changeset mturra:201901301231_408 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER REGISTRO_TC
AFTER DELETE OR INSERT OR UPDATE
ON REGISTRO
BEGIN
   -- Exec PostEvent Check REFERENTIAL Integrity
   Integritypackage.Exec_PostEvent;
END;

/

