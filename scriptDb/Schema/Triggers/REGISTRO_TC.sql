CREATE OR REPLACE TRIGGER REGISTRO_TC
AFTER DELETE OR INSERT OR UPDATE
ON REGISTRO
BEGIN
   -- Exec PostEvent Check REFERENTIAL Integrity
   Integritypackage.Exec_PostEvent;
END;
/


