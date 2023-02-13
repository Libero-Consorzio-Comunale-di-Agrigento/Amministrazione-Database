--liquibase formatted sql

--changeset mturra:201901301231_410 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER RUOLI_TAIU
   AFTER INSERT OR UPDATE ON RUOLI FOR EACH ROW
BEGIN
   IF NVL(:NEW.gruppo_so,'N') <> NVL(:OLD.gruppo_so,'N') THEN
      DECLARE
         d_codice VARCHAR2(8);
         d_id     NUMBER;
         d_ruolo  VARCHAR2(8);
         d_msg    VARCHAR2(32767) := 'Esiste gia'' il gruppo '''||:NEW.descrizione||'''';
         d_error  BOOLEAN := FALSE;
      BEGIN
         d_codice := Gruppo.get_codice(:NEW.descrizione);
         IF :NEW.gruppo_so = 'S' THEN -- ruolo corrispondente a S.O.
            IF :NEW.incarico != 'S' THEN -- NON è incarico
               IF d_codice IS NULL THEN
                  Gruppo.ins_uo(:NEW.descrizione, d_codice, d_id, :NEW.ruolo);
               ELSIF Gruppo.get_tipo(d_codice) <> 'O' THEN
                  d_error := TRUE;
                  d_msg   := d_msg ||' non appartenente alla struttura.';
               ELSE
                  d_ruolo := NVL(Gruppo.get_gruppo_lavoro(d_codice), 'xxxxxxxx');
                  IF d_ruolo <> :NEW.ruolo THEN
                     d_error := TRUE;
                     IF d_ruolo <> 'xxxxxxxx' THEN
                        d_msg   := d_msg ||' associato al ruolo '''||d_ruolo||'''.';
                     ELSE
                        d_msg   := d_msg ||' non associato ad un ruolo.';
                     END IF;
                  END IF;
               END IF;
               d_msg := d_msg ||' Modificare la descrizione e ripetere l''operazione.';
               IF d_error THEN
                  RAISE_APPLICATION_ERROR(-20999, d_msg);
               END IF;
            ELSE -- è incarico
               RAISE_APPLICATION_ERROR(-20999, 'Un Incarico non può essere anche ruolo di struttura');
            END IF;
         ELSE  -- ruolo non piu' corrispondente a S.O.
            DELETE UTENTI_GRUPPO
             WHERE Gruppo = d_codice
            ;
         END IF;
      END;
   END IF;
EXCEPTION
   WHEN OTHERS THEN
        RAISE;
END;

/

