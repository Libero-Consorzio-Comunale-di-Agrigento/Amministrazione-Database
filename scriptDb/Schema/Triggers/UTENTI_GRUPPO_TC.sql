CREATE OR REPLACE TRIGGER UTENTI_GRUPPO_TC
AFTER INSERT OR UPDATE OR DELETE
ON UTENTI_GRUPPO
BEGIN
   -- Exec PostEvent Check REFERENTIAL Integrity
   Integritypackage.Exec_PostEvent;
END;
/


