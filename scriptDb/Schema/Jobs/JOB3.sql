DECLARE
  X NUMBER;
BEGIN
    SYS.DBMS_JOB.SUBMIT
    ( job       => X 
     ,what      => 'begin disabilita_utenti(180); end;'
     ,next_date => SYSDATE + 1
     ,interval  => 'SYSDATE + 1'
     ,no_parse  => TRUE
    );
  COMMIT;
END;
/



