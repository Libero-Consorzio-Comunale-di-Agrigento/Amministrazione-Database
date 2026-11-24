--liquibase formatted sql

--changeset mturra:201901301231_390 runAlways:true stripComments:false


CREATE OR REPLACE TRIGGER GRUPPI_LAVORO_TIU
   INSTEAD OF INSERT OR UPDATE ON GRUPPI_LAVORO
FOR EACH ROW
/******************************************************************************
 NOME:        GRUPPI_LAVORO_TIU
 DESCRIZIONE: Trigger for Set DATA Integrity
                          Set FUNCTIONAL Integrity
                       on Table GRUPPI_LAVORO
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 29/12/2006 MM     Creazione
******************************************************************************/
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   FOUND            BOOLEAN;
BEGIN
   IF INSERTING THEN
      BEGIN
         INSERT INTO RUOLI
                ( RUOLO
                , DESCRIZIONE
                , DESCRIZIONE_AL1
                , DESCRIZIONE_AL2
                , GRUPPO_LAVORO)
         VALUES ( :NEW.GRUPPO_LAVORO
                , :NEW.DESCRIZIONE
                , :NEW.DESCRIZIONE_AL1
                , :NEW.DESCRIZIONE_AL2
                , 'S')
         ;
      EXCEPTION
         WHEN OTHERS THEN
            RAISE;
      END;
   ELSE
      UPDATE RUOLI
         SET RUOLO = :NEW.GRUPPO_LAVORO
           , DESCRIZIONE = :NEW.DESCRIZIONE
           , DESCRIZIONE_AL1 = :NEW.DESCRIZIONE_AL1
           , DESCRIZIONE_AL2 = :NEW.DESCRIZIONE_AL2
           , GRUPPO_LAVORO = 'S'
       WHERE RUOLO = :OLD.GRUPPO_LAVORO
      ;
   END IF;
EXCEPTION
   WHEN integrity_error THEN
        Integritypackage.InitNestLevel;
        RAISE_APPLICATION_ERROR(errno, errmsg);
   WHEN OTHERS THEN
        Integritypackage.InitNestLevel;
        RAISE;
END;

/

