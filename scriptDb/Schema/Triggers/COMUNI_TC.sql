CREATE OR REPLACE TRIGGER COMUNI_TC
AFTER INSERT
   OR UPDATE
   OR DELETE
ON COMUNI
BEGIN
   -- Exec PostEvent Check REFERENTIAL Integrity
   Integritypackage.Exec_PostEvent;
END;
/


