--liquibase formatted sql

--changeset snegroni:2019004021256 runOnChange:true stripComments:false

CREATE OR REPLACE TRIGGER CARATTERISTICH_ACC_STORICO_TIU
   /******************************************************************************
          NOME:        CARATTERISTICH_ACC_STORICO_TIU
          DESCRIZIONE: Trigger for salvare dati storici
                                at INSERT or UPDATE or DELETE on Table CARATTERISTICHE_ACC_STORICO
          ECCEZIONI:
          ANNOTAZIONI: -
          REVISIONI:
          Rev. Data       Autore Descrizione
          ---- ---------- ------ ------------------------------------------------------
             1 04/12/2018 SNeg   Prima distribuzione
         ******************************************************************************/
   AFTER INSERT OR UPDATE OR DELETE
   ON CARATTERISTICHE_ACCESSO
   FOR EACH ROW
DECLARE
   integrity_error   EXCEPTION;
   errno             INTEGER;
   errmsg            CHAR (200);
   dummy             INTEGER;
   FOUND             BOOLEAN;
   d_id_evento       NUMBER;

   FUNCTION get_id_evento
      RETURN NUMBER
   IS
      v_id_evento   NUMBER;
   BEGIN
      SELECT caas_sq.NEXTVAL INTO v_id_evento FROM DUAL;

      RETURN v_id_evento;
   END;

   PROCEDURE inserisci (p_operazione       VARCHAR2,
                        p_info             VARCHAR2,
                        p_id_evento        NUMBER DEFAULT NULL,
                        p_id_evento_rif    NUMBER DEFAULT NULL)
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
         INSERT INTO CARATTERISTICHE_ACC_STORICO (ID_EVENTO,
                                                  CAAC_ID,
                                                  TIPO_ACCESSO,
                                                  PROGETTO,
                                                  ISTANZA,
                                                  MODULO,
                                                  UTENTE,
                                                  ACCESSO,
                                                  ACCESSO_SE,
                                                  TRACCIA,
                                                  GIORNI_TRACCIA,
                                                  TENTATIVI_PASSWORD,
                                                  VALIDITA_PASSWORD,
                                                  SINGLE_SIGN_ON,
                                                  SLEEP,
                                                  LDAP,
                                                  MIN_LUNGHEZZA_PWD,
                                                  MOD_PWD_PRIMO_ACCESSO,
                                                  ARCHIVIAZIONE_TRACCIA,
                                                  DISLOCAZIONE_TRACCIA,
                                                  AMMESSI_CAR_SPECIALI_PWD,
                                                  NUMERI_OBB_PWD,
                                                  GG_PRIMA_RIUTILIZZO_PWD,
                                                  DATA,
                                                  OPERAZIONE,
                                                  BI_RIFERIMENTO,
                                                  UTENTE_AGGIORNAMENTO,
                                                  USER_ORACLE,
                                                  INFO,
                                                  PROGRAMMA)
              VALUES (v_id_evento,
                      :new.CAAC_ID,
                      :new.TIPO_ACCESSO,
                      :new.PROGETTO,
                      :new.ISTANZA,
                      :new.MODULO,
                      :new.UTENTE,
                      :new.ACCESSO,
                      :new.ACCESSO_SE,
                      :new.TRACCIA,
                      :new.GIORNI_TRACCIA,
                      :new.TENTATIVI_PASSWORD,
                      :new.VALIDITA_PASSWORD,
                      :new.SINGLE_SIGN_ON,
                      :new.SLEEP,
                      :new.LDAP,
                      :new.MIN_LUNGHEZZA_PWD,
                      :new.MOD_PWD_PRIMO_ACCESSO,
                      :new.ARCHIVIAZIONE_TRACCIA,
                      :new.DISLOCAZIONE_TRACCIA,
                      :new.AMMESSI_CAR_SPECIALI_PWD,
                      :new.NUMERI_OBB_PWD,
                      :new.GG_PRIMA_RIUTILIZZO_PWD,
                      SYSDATE,
                      p_operazione,
                      p_id_evento_rif,
                      SI4.UTENTE,
                      USER,
                      gv_$session_pkg.get_info (USERENV ('sessionid')),
                      gv_$session_pkg.get_program (USERENV ('sessionid')));
      ELSIF p_info = 'OLD'
      THEN
          INSERT INTO CARATTERISTICHE_ACC_STORICO (ID_EVENTO,
                                                  CAAC_ID,
                                                  TIPO_ACCESSO,
                                                  PROGETTO,
                                                  ISTANZA,
                                                  MODULO,
                                                  UTENTE,
                                                  ACCESSO,
                                                  ACCESSO_SE,
                                                  TRACCIA,
                                                  GIORNI_TRACCIA,
                                                  TENTATIVI_PASSWORD,
                                                  VALIDITA_PASSWORD,
                                                  SINGLE_SIGN_ON,
                                                  SLEEP,
                                                  LDAP,
                                                  MIN_LUNGHEZZA_PWD,
                                                  MOD_PWD_PRIMO_ACCESSO,
                                                  ARCHIVIAZIONE_TRACCIA,
                                                  DISLOCAZIONE_TRACCIA,
                                                  AMMESSI_CAR_SPECIALI_PWD,
                                                  NUMERI_OBB_PWD,
                                                  GG_PRIMA_RIUTILIZZO_PWD,
                                                  DATA,
                                                  OPERAZIONE,
                                                  BI_RIFERIMENTO,
                                                  UTENTE_AGGIORNAMENTO,
                                                  USER_ORACLE,
                                                  INFO,
                                                  PROGRAMMA)
              VALUES (v_id_evento,
                      :old.CAAC_ID,
                      :old.TIPO_ACCESSO,
                      :old.PROGETTO,
                      :old.ISTANZA,
                      :old.MODULO,
                      :old.UTENTE,
                      :old.ACCESSO,
                      :old.ACCESSO_SE,
                      :old.TRACCIA,
                      :old.GIORNI_TRACCIA,
                      :old.TENTATIVI_PASSWORD,
                      :old.VALIDITA_PASSWORD,
                      :old.SINGLE_SIGN_ON,
                      :old.SLEEP,
                      :old.LDAP,
                      :old.MIN_LUNGHEZZA_PWD,
                      :old.MOD_PWD_PRIMO_ACCESSO,
                      :old.ARCHIVIAZIONE_TRACCIA,
                      :old.DISLOCAZIONE_TRACCIA,
                      :old.AMMESSI_CAR_SPECIALI_PWD,
                      :old.NUMERI_OBB_PWD,
                      :old.GG_PRIMA_RIUTILIZZO_PWD,
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
      ELSIF     UPDATING
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
