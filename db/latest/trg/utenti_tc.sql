--liquibase formatted sql

--changeset mturra:201901301231_429 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER UTENTI_TC
AFTER DELETE OR INSERT OR UPDATE
ON UTENTI
BEGIN
   -- Exec PostEvent Check REFERENTIAL Integrity
   Integritypackage.Exec_PostEvent;
END;

/

