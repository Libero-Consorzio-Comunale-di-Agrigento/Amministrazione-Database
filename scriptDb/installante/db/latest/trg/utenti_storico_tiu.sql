--liquibase formatted sql

--changeset snegroni:201902041150_431 runOnChange:true stripComments:false

CREATE OR REPLACE TRIGGER UTENTI_STORICO_TIU
/******************************************************************************
          NOME:        UTENTI_STORICO_TIU
          DESCRIZIONE: Trigger for salvare dati storici
                                at INSERT or UPDATE or DELETE on Table UTENTI
          ECCEZIONI:
          ANNOTAZIONI: -
          REVISIONI:
          Rev. Data       Autore Descrizione
          ---- ---------- ------ ------------------------------------------------------
             1 21/12/2016 SNeg   Prima distribuzione
             2 27/02/2017 SNeg   Introduzione riferimento a BI (Before Image)
             3 28/09/2018 SNeg   Tolto check su registro, storico tenuto SEMPRE
                                 Gestite nuove colonne AMMINISTRATORE e INFO_IDENTIFICAZIONE
             4 29/07/2020 SNeg   Registrare le informazioni se non cambia solo data_aggiornamento
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
      SELECT   utes_sq.NEXTVAL INTO v_id_evento FROM DUAL;
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
         INSERT INTO UTENTI_STORICO (ID_EVENTO,
                                     UTENTE,
                                     ID_UTENTE,
                                     NOMINATIVO,
                                     PASSWORD,
                                     DATA_PASSWORD,
                                     RINNOVO_PASSWORD,
                                     PWD_DA_MODIFICARE,
                                     TIPO_UTENTE,
                                     STATO,
                                     LINGUA,
                                     GRUPPO_LAVORO,
                                     IMPORTANZA,
                                     NOTE,
                                     DATA_INSERIMENTO,
                                     UTENTE_AGGIORNAMENTO,
                                     DATA_AGGIORNAMENTO,
                                     DATA,
                                     OPERAZIONE,
                                     BI_RIFERIMENTO,
                                     UTENTE_AGG,
                                     USER_ORACLE,
                                     INFO,
                                     PROGRAMMA,
                                     AMMINISTRATORE,
                                     INFO_IDENTIFICAZIONE)
           VALUES   (v_id_evento,
                      :new.UTENTE,
                     :new.ID_UTENTE,
                     :new.NOMINATIVO,
                     :new.PASSWORD,
                     :new.DATA_PASSWORD,
                     :new.RINNOVO_PASSWORD,
                     :new.PWD_DA_MODIFICARE,
                     :new.TIPO_UTENTE,
                     :new.STATO,
                     :new.LINGUA,
                     :new.GRUPPO_LAVORO,
                     :new.IMPORTANZA,
                     :new.NOTE,
                     :new.DATA_INSERIMENTO,
                     :new.UTENTE_AGGIORNAMENTO,
                     :new.DATA_AGGIORNAMENTO,
                     SYSDATE,
                     p_operazione,
                     p_id_evento_rif,
                     SI4.UTENTE,
                     USER,
                     gv_$session_pkg.get_info (USERENV ('sessionid')),
                     gv_$session_pkg.get_program (USERENV ('sessionid')),
                     :new.AMMINISTRATORE,
                     :new.INFO_IDENTIFICAZIONE);
      ELSIF p_info = 'OLD'
      THEN
         INSERT INTO UTENTI_STORICO (ID_EVENTO,
                                     UTENTE,
                                     ID_UTENTE,
                                     NOMINATIVO,
                                     PASSWORD,
                                     DATA_PASSWORD,
                                     RINNOVO_PASSWORD,
                                     PWD_DA_MODIFICARE,
                                     TIPO_UTENTE,
                                     STATO,
                                     LINGUA,
                                     GRUPPO_LAVORO,
                                     IMPORTANZA,
                                     NOTE,
                                     DATA_INSERIMENTO,
                                     UTENTE_AGGIORNAMENTO,
                                     DATA_AGGIORNAMENTO,
                                     DATA,
                                     OPERAZIONE,
                                     BI_RIFERIMENTO,
                                     UTENTE_AGG,
                                     USER_ORACLE,
                                     INFO,
                                     PROGRAMMA,
                                     AMMINISTRATORE,
                                     INFO_IDENTIFICAZIONE)
           VALUES   (v_id_evento,
                     :old.UTENTE,
                     :old.ID_UTENTE,
                     :old.NOMINATIVO,
                     :old.PASSWORD,
                     :old.DATA_PASSWORD,
                     :old.RINNOVO_PASSWORD,
                     :old.PWD_DA_MODIFICARE,
                     :old.TIPO_UTENTE,
                     :old.STATO,
                     :old.LINGUA,
                     :old.GRUPPO_LAVORO,
                     :old.IMPORTANZA,
                     :old.NOTE,
                     :old.DATA_INSERIMENTO,
                     :old.UTENTE_AGGIORNAMENTO,
                     :old.DATA_AGGIORNAMENTO,
                     SYSDATE,
                     p_operazione,
                     p_id_evento_rif,
                     SI4.UTENTE,
                     USER,
                     gv_$session_pkg.get_info (USERENV ('sessionid')),
                     gv_$session_pkg.get_program (USERENV ('sessionid')),
                     :old.AMMINISTRATORE,
                     :old.INFO_IDENTIFICAZIONE);
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
         OR :new.NOMINATIVO != :old.NOMINATIVO
         OR NVL (:new.PASSWORD, 'x') != NVL (:old.PASSWORD, 'x')
         OR NVL (:new.DATA_PASSWORD,  sysdate + 100) != NVL (:old.DATA_PASSWORD,  sysdate + 100)
         OR NVL (:new.RINNOVO_PASSWORD, 'x') != NVL (:old.RINNOVO_PASSWORD, 'x')
         OR NVL (:new.PWD_DA_MODIFICARE, 'x') != NVL (:old.PWD_DA_MODIFICARE, 'x')
         OR :new.TIPO_UTENTE != :old.TIPO_UTENTE
         OR :new.STATO != :old.STATO
         OR :new.LINGUA != :old.LINGUA
         OR NVL (:new.GRUPPO_LAVORO, 'x') != NVL (:old.GRUPPO_LAVORO, 'x')
         OR NVL (:new.IMPORTANZA, -1) != NVL (:old.IMPORTANZA, -1)
         OR NVL (:new.NOTE, 'x') != NVL (:old.NOTE, 'x')
         OR NVL (:new.DATA_INSERIMENTO, sysdate + 100) != NVL (:old.DATA_INSERIMENTO, sysdate + 100)
         OR NVL (:new.UTENTE_AGGIORNAMENTO, 'x') != NVL (:old.UTENTE_AGGIORNAMENTO, 'x')
         -- rev. 4 OR NVL (:new.DATA_AGGIORNAMENTO, sysdate + 100) != NVL (:old.DATA_AGGIORNAMENTO, sysdate + 100)
         OR NVL (:new.AMMINISTRATORE,'x') != nvl(:OLD.AMMINISTRATORE,'x')
         OR NVL (:new.INFO_IDENTIFICAZIONE,'x') != nvl(:OLD.INFO_IDENTIFICAZIONE,'x'))
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
