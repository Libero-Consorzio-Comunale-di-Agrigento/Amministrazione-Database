DECLARE
  X NUMBER;
BEGIN
    SYS.DBMS_JOB.SUBMIT
    ( job       => X 
     ,what      => 'begin utente.rigenera_so(''%''); end;'
     ,next_date => TRUNC(SYSDATE+1)+5/24
     ,interval  => 'TRUNC(SYSDATE+1)+5/24'
     ,no_parse  => TRUE
    );
  COMMIT;
END;
/



