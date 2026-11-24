--liquibase formatted sql

--changeset snegroni:201904021300 runOnChange:true stripComments:false

CREATE OR REPLACE TRIGGER UTENTI_PWD_STORICO_TIU
/******************************************************************************
          NOME:        UTENTI_PWD_STORICO_TIU
          DESCRIZIONE: Trigger for salvare dati storici relativi alla password
                                at INSERT or UPDATE or DELETE on Table UTENTI
          ECCEZIONI:
          ANNOTAZIONI: -
          REVISIONI:
          Rev. Data       Autore Descrizione
          ---- ---------- ------ ------------------------------------------------------
             1 28/09/2018 SNeg   Prima distribuzione
         ******************************************************************************/
   AFTER INSERT OR UPDATE OR DELETE
   ON UTENTI
   FOR EACH ROW
DECLARE
   integrity_error EXCEPTION;
   errno         INTEGER;
   errmsg        CHAR (200);
   dummy         INTEGER;
   FOUND         BOOLEAN;
   d_id_evento   NUMBER;
   FUNCTION get_id_evento
      RETURN NUMBER
   IS
      v_id_evento   NUMBER;
   BEGIN
      SELECT utps_sq.NEXTVAL INTO v_id_evento FROM DUAL;
      RETURN v_id_evento;
   END;
   PROCEDURE inserisci (p_operazione       VARCHAR2,
                        p_info             VARCHAR2,
                        p_id_evento        NUMBER DEFAULT NULL ,
                        p_id_evento_rif    NUMBER DEFAULT NULL )
   IS
      v_id_evento   NUMBER;
   BEGIN
      IF p_id_evento IS NULL
      THEN
         v_id_evento := get_id_evento;
      ELSE                                                -- passato id evento
         v_id_evento := p_id_evento;
      END IF;
      IF p_info = 'NEW'
      THEN
      
      INSERT INTO PASSWORD_STORICO (
   ID_EVENTO, UTENTE, PASSWORD, DATA_PASSWORD, 
   UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, DATA, 
   OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, 
   USER_ORACLE, INFO, PROGRAMMA) 
VALUES  (v_id_evento,
                     :new.UTENTE,
                     :new.PASSWORD,
                     :new.DATA_PASSWORD,
                     :new.UTENTE_AGGIORNAMENTO,
                     :new.DATA_AGGIORNAMENTO,
                     SYSDATE,
                     p_operazione,
                     p_id_evento_rif,
                     SI4.UTENTE,
                     USER,
                     gv_$session_pkg.get_info (USERENV ('sessionid')),
                     gv_$session_pkg.get_program (USERENV ('sessionid')));
      ELSIF p_info = 'OLD'
      THEN
          INSERT INTO PASSWORD_STORICO (
   ID_EVENTO, UTENTE, PASSWORD, DATA_PASSWORD, 
   UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, DATA, 
   OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, 
   USER_ORACLE, INFO, PROGRAMMA) 
VALUES  (v_id_evento,
                     :old.UTENTE,
                     :old.PASSWORD,
                     :old.DATA_PASSWORD,
                     :old.UTENTE_AGGIORNAMENTO,
                     :old.DATA_AGGIORNAMENTO,
                     SYSDATE,
                     p_operazione,
                     p_id_evento_rif,
                     SI4.UTENTE,
                     USER,
                     gv_$session_pkg.get_info (USERENV ('sessionid')),
                     gv_$session_pkg.get_program (USERENV ('sessionid')));
      END IF;
   END;
BEGIN
    IF :new.tipo_utente = 'U' THEN
      IF INSERTING
      THEN
         inserisci (p_operazione => 'I', p_info => 'NEW');
      ELSIF DELETING
      THEN
         inserisci (p_operazione => 'D', p_info => 'OLD');
      ELSIF UPDATING AND ( NVL (:new.PASSWORD, 'x') != NVL (:old.PASSWORD, 'x')
         OR NVL (:new.DATA_PASSWORD,  sysdate + 100) != NVL (:old.DATA_PASSWORD,  sysdate + 100)
         OR NVL (:new.UTENTE_AGGIORNAMENTO, 'x') != NVL (:old.UTENTE_AGGIORNAMENTO, 'x')
         OR NVL (:new.DATA_AGGIORNAMENTO, sysdate + 100) != NVL (:old.DATA_AGGIORNAMENTO, sysdate + 100))
      THEN
         d_id_evento := get_id_evento;
         inserisci (p_operazione   => 'BI',
                    p_info         => 'OLD',
                    p_id_evento    => d_id_evento);
         inserisci (p_operazione      => 'AI',
                    p_info            => 'NEW',
                    p_id_evento       => NULL,
                    p_id_evento_rif   => d_id_evento);
      END IF;
    END IF;
END;
/
