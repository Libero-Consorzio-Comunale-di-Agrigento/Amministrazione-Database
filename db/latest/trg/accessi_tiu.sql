--liquibase formatted sql

--changeset mturra:201901301231_352 runAlways:true stripComments:false


CREATE OR REPLACE TRIGGER ACCESSI_TIU
   INSTEAD OF INSERT OR UPDATE ON ACCESSI FOR EACH ROW
/******************************************************************************
 NOME:        ACCESSI_TIU
 DESCRIZIONE: Trigger for Set DATA Integrity
                          Set FUNCTIONAL Integrity
                       on Table ACCESSI
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 16/10/2002 MM     Creazione
    1 13/01/2006 MM     Gestione registrazione traccia di una pagina: verifica
                        che abbia estensione .do, .html, .htm, .jsp; se cosi'
                        non e' ignora la richiesta di registrazione dell'accesso.
    2 19/04/2010 SNeg   Modificato dimensionamento variabile d_user_oracle
******************************************************************************/
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   FOUND            BOOLEAN;
   d_user_oracle   istanze.user_oracle%TYPE;
BEGIN
   BEGIN
      SELECT USER_ORACLE
        INTO d_user_oracle
        FROM ISTANZE
       WHERE Istanza = :NEW.Istanza
      ;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
        d_user_oracle := USER;
      WHEN OTHERS THEN
           RAISE;
   END;
   IF INSERTING THEN
      BEGIN
         INSERT INTO EVENTI
                ( ID_EVENTO
                , TESTO
                , ANNOTAZIONI
                , TIPO
                , DB_USER
                , Utente
                , MODULO
                , Istanza
                , DATA
                , STATO)
         VALUES (:NEW.ACCE_ID
                , 'session_id='||:NEW.SESSION_ID||
                 ' terminale='||:NEW.TERMINALE||
                 DECODE(:NEW.ID_CREDENZIALE, TO_NUMBER(NULL),'',' id_credenziale='||:NEW.ID_CREDENZIALE)||
                 DECODE(:NEW.TIPO_AUTENTICAZIONE, TO_CHAR(NULL),'',' tipo_autenticazione='||:NEW.TIPO_AUTENTICAZIONE)
                , :NEW.NOTE
                , 'LOG'||:NEW.LOG
                , d_user_oracle
                , :NEW.Utente
                , :NEW.MODULO
                , :NEW.Istanza
                , :NEW.DATA
                , NVL(:NEW.STATO,'U'))
         ;
      EXCEPTION
         WHEN OTHERS THEN
            RAISE;
      END;
   END IF;
   IF UPDATING THEN
      DECLARE
         d_traccia BOOLEAN := TRUE;
      BEGIN
         -- se registra traccia di una pagina, verifica che abbia estensione
         -- .do, .html, .htm, .jsp; se cosi' non e' elimina la registrazione
         -- dell'accesso precedentemente inserita con il campo note =
         -- 'Accesso a risorsa Web'.
         IF :NEW.LOG = 'TRC' AND SUBSTR(LOWER(:NEW.note),1,7) = 'http://' AND :OLD.note = 'Accesso a risorsa Web' THEN
            IF  SUBSTR(LOWER(:NEW.note), -3, 3) <> '.do'
            AND SUBSTR(LOWER(:NEW.note), -4, 4) NOT IN ('.jsp', '.htm')
            AND SUBSTR(LOWER(:NEW.note), -5, 5) <> '.html' THEN
               d_traccia := FALSE;
            END IF;
         END IF;
         IF d_traccia THEN
            UPDATE EVENTI
               SET TESTO       = 'session_id='||:NEW.SESSION_ID||
                                 ' terminale='||:NEW.TERMINALE||
                                 DECODE(:NEW.ID_CREDENZIALE, TO_NUMBER(NULL),'',' id_credenziale='||:NEW.ID_CREDENZIALE)||
                                 DECODE(:NEW.TIPO_AUTENTICAZIONE, TO_CHAR(NULL),'',' tipo_autenticazione='||:NEW.TIPO_AUTENTICAZIONE)
                 , ANNOTAZIONI = :NEW.NOTE
                 , TIPO        = 'LOG'||:NEW.LOG
                 , DB_USER     = d_user_oracle
                 , Utente      = :NEW.Utente
                 , MODULO      = :NEW.MODULO
                 , Istanza     = :NEW.Istanza
                 , DATA        = :NEW.DATA
                 , STATO       = NVL(:NEW.STATO,'U')
             WHERE ID_EVENTO = :OLD.ACCE_ID
            ;
         ELSE
            DELETE EVENTI
             WHERE ID_EVENTO = :OLD.ACCE_ID
            ;
         END IF;
      EXCEPTION
         WHEN OTHERS THEN
              RAISE;
      END;
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

