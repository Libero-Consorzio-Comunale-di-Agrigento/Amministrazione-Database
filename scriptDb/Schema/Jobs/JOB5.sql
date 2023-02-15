DECLARE
  X NUMBER;
BEGIN
    SYS.DBMS_JOB.SUBMIT
    ( job       => X 
     ,what      => 'begin GESTIONE_DATI_LOCALI.ELIMINA_ACCESSI_MAX_RETENTION; end;'
     ,next_date => TRUNC(SYSDATE+1)+2/24
     ,interval  => 'TRUNC(SYSDATE+1)+2/24'
     ,no_parse  => TRUE
    );
  COMMIT;
END;
/



