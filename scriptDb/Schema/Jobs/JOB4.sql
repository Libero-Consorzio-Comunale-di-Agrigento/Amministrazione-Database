DECLARE
  X NUMBER;
BEGIN
    SYS.DBMS_JOB.SUBMIT
    ( job       => X 
     ,what      => 'begin EVENTI_ELIMINAZIONE; end;'
     ,next_date => trunc(sysdate +1) + 2/24
     ,interval  => 'trunc(sysdate +1) + 2/24'
     ,no_parse  => TRUE
    );
  COMMIT;
END;
/



