CREATE OR REPLACE TRIGGER DIRITTI_ACCESSO_STORICO_TIU
/******************************************************************************
       NOME:        DIRITTI_ACCESSO_STORICO_TIU
       DESCRIZIONE: Trigger for salvare dati storici
                             at INSERT or UPDATE or DELETE on Table DIRITTI_ACCESSO
       ECCEZIONI:
       ANNOTAZIONI: -
       REVISIONI:
       Rev. Data       Autore Descrizione
       ---- ---------- ------ ------------------------------------------------------
          1 21/12/2016 SNeg   Prima distribuzione
          2 27/02/2017 SNeg   Introduzione riferimento a BI (Before Image)
          3 07/05/2019 SNeg   Traccia tenuta SEMPRE
      ******************************************************************************/
   AFTER INSERT OR UPDATE OR DELETE
   ON DIRITTI_ACCESSO
   FOR EACH ROW
DECLARE
   integrity_error EXCEPTION;
   errno    INTEGER;
   errmsg   CHAR (200);
   dummy    INTEGER;
   FOUND    BOOLEAN;
   d_id_evento number;
   FUNCTION get_id_evento
   RETURN NUMBER is
   v_id_evento number;
   BEGIN
   select dias_sq.nextval
               into v_id_evento
               from dual;
   return v_id_evento;
   END;
   PROCEDURE inserisci (p_operazione VARCHAR2, p_info VARCHAR2, p_id_evento number default null, p_id_evento_rif number default null)
   IS
   v_id_evento number;
   BEGIN
     if p_id_evento is null then
       v_id_evento:= get_id_evento;
     else -- passato id evento
        v_id_evento := p_id_evento;
     end if;
      IF p_info = 'NEW'
      THEN
         INSERT INTO DIRITTI_ACCESSO_STORICO (ID_EVENTO,
                                              UTENTE,
                                              MODULO,
                                              ISTANZA,
                                              RUOLO,
                                              SEQUENZA,
                                              ULTIMO_ACCESSO,
                                              NUMERO_ACCESSI,
                                              GRUPPO,
                                              NOTE,
                                              DATA,
                                              OPERAZIONE,
                                              BI_RIFERIMENTO,
                                              UTENTE_AGGIORNAMENTO,
                                              USER_ORACLE,
                                              INFO,
                                              PROGRAMMA)
           VALUES   (v_id_evento,
                     :new.UTENTE,
                     :new.MODULO,
                     :new.ISTANZA,
                     :new.RUOLO,
                     :new.SEQUENZA,
                     :new.ULTIMO_ACCESSO,
                     :new.NUMERO_ACCESSI,
                     :new.GRUPPO,
                     :new.NOTE,
                     SYSDATE,
                     p_operazione,
                     p_id_evento_rif,
                     SI4.UTENTE,
                     USER,
                     gv_$session_pkg.get_info (USERENV ('sessionid')),
                     gv_$session_pkg.get_program (USERENV ('sessionid')));
      ELSIF p_info = 'OLD'
      THEN
         INSERT INTO DIRITTI_ACCESSO_STORICO (ID_EVENTO,
                                              UTENTE,
                                              MODULO,
                                              ISTANZA,
                                              RUOLO,
                                              SEQUENZA,
                                              ULTIMO_ACCESSO,
                                              NUMERO_ACCESSI,
                                              GRUPPO,
                                              NOTE,
                                              DATA,
                                              OPERAZIONE,
                                              BI_RIFERIMENTO,
                                              UTENTE_AGGIORNAMENTO,
                                              USER_ORACLE,
                                              INFO,
                                              PROGRAMMA)
           VALUES   (v_id_evento,
                     :old.UTENTE,
                     :old.MODULO,
                     :old.ISTANZA,
                     :old.RUOLO,
                     :old.SEQUENZA,
                     :old.ULTIMO_ACCESSO,
                     :old.NUMERO_ACCESSI,
                     :old.GRUPPO,
                     :old.NOTE,
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
      ELSIF UPDATING AND (:new.UTENTE != :old.UTENTE OR :new.MODULO != :old.MODULO OR
                        :new.ISTANZA != :old.ISTANZA OR :new.RUOLO != :old.RUOLO OR
                        :new.GRUPPO != :old.GRUPPO)
      THEN
         d_id_evento := get_id_evento;
         inserisci (p_operazione => 'BI', p_info => 'OLD', p_id_evento => d_id_evento);
         inserisci (p_operazione => 'AI', p_info => 'NEW', p_id_evento =>  null,p_id_evento_rif =>  d_id_evento);
      END IF;
END;
/


