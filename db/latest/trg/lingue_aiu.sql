--liquibase formatted sql

--changeset mturra:201901301231_399 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER LINGUE_AIU
   AFTER INSERT OR UPDATE ON LINGUE
FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
BEGIN
   INSERT INTO GRUPPI_LINGUISTICI(LINGUA, LINGUA_AL, SEQUENZA, DESCRIZIONE)
   SELECT :NEW.LINGUA, :NEW.LINGUA, 1, :NEW.DESCRIZIONE
     FROM DUAL
    WHERE NOT EXISTS (SELECT 1
                        FROM GRUPPI_LINGUISTICI
                       WHERE LINGUA    = :NEW.LINGUA
                         AND LINGUA_AL = :OLD.LINGUA)
   ;
   IF SQL%ROWCOUNT = 0 THEN
      UPDATE GRUPPI_LINGUISTICI
         SET LINGUA      = :NEW.LINGUA,
             LINGUA_AL   = :NEW.LINGUA,
             DESCRIZIONE = :NEW.DESCRIZIONE
       WHERE LINGUA    = :OLD.LINGUA
         AND LINGUA_AL = :OLD.LINGUA
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

