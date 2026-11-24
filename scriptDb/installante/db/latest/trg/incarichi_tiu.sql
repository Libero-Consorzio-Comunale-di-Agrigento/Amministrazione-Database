--liquibase formatted sql

--changeset mturra:201901301231_391 runAlways:true stripComments:false


CREATE OR REPLACE TRIGGER INCARICHI_TIU
   INSTEAD OF INSERT OR UPDATE OR DELETE ON INCARICHI FOR EACH ROW
/******************************************************************************
 NOME:        INCARICHI_TIU
 DESCRIZIONE: Trigger for Set DATA Integrity
                          Set FUNCTIONAL Integrity
                       on Table GRUPPI_LAVORO
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 28/12/2009 SNeg    Creazione
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
                , INCARICO
                , RESPONSABILITA)
         VALUES ( :NEW.INCARICO
                , :NEW.DESCRIZIONE
                , :NEW.DESCRIZIONE_AL1
                , :NEW.DESCRIZIONE_AL2
                , 'S'
                , :NEW.RESPONSABILITA)
         ;
      EXCEPTION
         WHEN OTHERS THEN
            RAISE;
      END;
   ELSIF UPDATING THEN
      UPDATE RUOLI
         SET RUOLO = :NEW.INCARICO
           , DESCRIZIONE = :NEW.DESCRIZIONE
           , DESCRIZIONE_AL1 = :NEW.DESCRIZIONE_AL1
           , DESCRIZIONE_AL2 = :NEW.DESCRIZIONE_AL2
           , RESPONSABILITA = :NEW.RESPONSABILITA
           , INCARICO = 'S'
       WHERE RUOLO = :OLD.INCARICO
      ;
   ELSIF DELETING THEN
      UPDATE RUOLI
         SET STATO = 'R'
       WHERE RUOLO = :OLD.INCARICO
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

