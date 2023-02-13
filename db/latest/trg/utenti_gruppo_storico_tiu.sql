--liquibase formatted sql

--changeset snegroni:20190506_1618 runOnChange:true stripComments:false

CREATE OR REPLACE TRIGGER UTENTI_GRUPPO_STORICO_TIU
   /******************************************************************************
          NOME:        UTENTI_GRUPPO_STORICO_TIU
          DESCRIZIONE: Trigger for salvare dati storici
                                at INSERT or UPDATE or DELETE on Table UTENTI
          ECCEZIONI:
          ANNOTAZIONI: -
          REVISIONI:
          Rev. Data       Autore Descrizione
          ---- ---------- ------ ------------------------------------------------------
             1 17/04/2019 SNeg   Prima distribuzione
         ******************************************************************************/
   AFTER INSERT OR UPDATE OR DELETE
   ON UTENTI_GRUPPO
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
      SELECT   utgs_sq.NEXTVAL INTO v_id_evento FROM DUAL;

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
         INSERT INTO UTENTI_GRUPPO_STORICO (ID_EVENTO,
                                     UTENTE,
                                     GRUPPO,
                                     DATA,
                                     OPERAZIONE,
                                     BI_RIFERIMENTO,
                                     UTENTE_AGG,
                                     USER_ORACLE,
                                     INFO,
                                     PROGRAMMA)
           VALUES   (v_id_evento,
                     :new.UTENTE,
                     :new.GRUPPO,
                     SYSDATE,
                     p_operazione,
                     p_id_evento_rif,
                     SI4.UTENTE,
                     USER,
                     gv_$session_pkg.get_info (USERENV ('sessionid')),
                     gv_$session_pkg.get_program (USERENV ('sessionid')));
      ELSIF p_info = 'OLD'
      THEN
         INSERT INTO UTENTI_GRUPPO_STORICO (ID_EVENTO,
                                     UTENTE,
                                     GRUPPO,
                                     DATA,
                                     OPERAZIONE,
                                     BI_RIFERIMENTO,
                                     UTENTE_AGG,
                                     USER_ORACLE,
                                     INFO,
                                     PROGRAMMA)
           VALUES   (v_id_evento,
                     :old.UTENTE,
                     :old.GRUPPO,
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
      IF INSERTING
      THEN
         inserisci (p_operazione => 'I', p_info => 'NEW');
      ELSIF DELETING
      THEN
         inserisci (p_operazione => 'D', p_info => 'OLD');
      ELSIF UPDATING AND (:new.UTENTE != :old.UTENTE
         OR :new.GRUPPO != :old.GRUPPO )
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
END;
/