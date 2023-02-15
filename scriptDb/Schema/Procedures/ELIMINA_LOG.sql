CREATE OR REPLACE PROCEDURE ELIMINA_LOG
( p_giorni IN NUMBER)
IS
BEGIN
   delete key_error_log
    where trunc(sysdate) - trunc(error_date) > p_giorni
   ;
   commit;
END ELIMINA_LOG;
/

