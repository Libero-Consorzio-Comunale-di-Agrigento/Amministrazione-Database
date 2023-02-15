CREATE OR REPLACE PROCEDURE DISABILITA_UTENTI
( p_giorni IN NUMBER)
IS
   d_num_utenti_dis NUMBER;
   d_utenti_dis     VARCHAR2(32000);
   d_session        NUMBER;
BEGIN
   FOR uten IN (SELECT utente||' - ' ||nominativo||', ' utente
                      FROM utenti
                     WHERE SYSDATE > ultimo_tentativo + p_giorni
                       AND stato NOT IN ('S','R'))
   LOOP
      d_utenti_dis := d_utenti_dis||uten.utente;
   END LOOP;
   d_utenti_dis := SUBSTR(NVL(d_utenti_dis,', '),1,LENGTH(NVL(d_utenti_dis,', ')) - 2 );
   IF LENGTH(d_utenti_dis) > 2000 THEN
      d_utenti_dis := SUBSTR(d_utenti_dis,1,1997)||'...';
   END IF;
   UPDATE utenti
      SET stato = 'S'
 WHERE SYSDATE > ultimo_tentativo + p_giorni
      AND stato NOT IN ('S','R')
   ;
   d_num_utenti_dis := SQL%ROWCOUNT;
   SELECT TO_NUMBER(USERENV('sessionid'))
     INTO d_session
     FROM dual
   ;
   INSERT INTO key_error_log ( error_session, error_date, error_text, error_user
                             , error_usertext, error_type)
         VALUES (d_session, SYSDATE,'Sospesi '||d_num_utenti_dis||' utenti che non fanno accesso al sistema da piu'' di '||p_giorni||' giorni.',
          'AD4',d_utenti_dis,'I')
   ;
   COMMIT;
EXCEPTION
   WHEN OTHERS THEN
      DECLARE
         d_err VARCHAR2(2000);
      BEGIN
         d_err := SQLERRM;
         INSERT INTO key_error_log ( error_session, error_date, error_text, error_user
                                   , error_usertext, error_type)
         VALUES (d_session, SYSDATE,d_err,'AD4',
                'Impossibile aggiornare lo stato degli utenti che non fanno accesso al sistema da piu'' di '||p_giorni||' giorni.',
                'I')
      ;
      COMMIT;
      END;
END DISABILITA_UTENTI;
/

