--liquibase formatted sql

--changeset mturra:201901301231_384 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER DIRITTI_ACCESSO_TC
AFTER INSERT
   OR UPDATE
   OR DELETE
ON DIRITTI_ACCESSO
BEGIN
   -- Exec PostEvent Check REFERENTIAL Integrity
   Integritypackage.Exec_PostEvent;
END;

/

