--liquibase formatted sql

--changeset mturra:201901301231_351 runAlways:true stripComments:false


CREATE OR REPLACE TRIGGER ACCESSI_TD
   INSTEAD OF DELETE ON ACCESSI FOR EACH ROW
/******************************************************************************
 NOME:        ACCESSI_TD
 DESCRIZIONE: Trigger for Set DATA Integrity
                          Set FUNCTIONAL Integrity
                       on Table ACCESSI
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 16/10/2002 MM     Creazione
******************************************************************************/
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   FOUND            BOOLEAN;
BEGIN
   DELETE EVENTI
    WHERE ID_EVENTO = :OLD.ACCE_ID
   ;
EXCEPTION
   WHEN integrity_error THEN
        Integritypackage.InitNestLevel;
        RAISE_APPLICATION_ERROR(errno, errmsg);
   WHEN OTHERS THEN
        Integritypackage.InitNestLevel;
        RAISE;
END;

/

