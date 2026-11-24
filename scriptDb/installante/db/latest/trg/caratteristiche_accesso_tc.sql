--liquibase formatted sql

--changeset mturra:201901301231_374 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER CARATTERISTICHE_ACCESSO_TC
AFTER INSERT
   OR UPDATE
   OR DELETE
ON CARATTERISTICHE_ACCESSO
BEGIN
   -- Exec PostEvent Check REFERENTIAL Integrity
   Integritypackage.Exec_PostEvent;
END;

/

