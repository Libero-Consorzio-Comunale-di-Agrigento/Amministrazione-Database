CREATE OR REPLACE TRIGGER UTENTI_GRUPPO_TB
BEFORE INSERT
    OR UPDATE
    OR DELETE
ON UTENTI_GRUPPO
BEGIN
   -- RESET PostEvent for Custom Functional Check
   IF Integritypackage.GetNestLevel = 0 THEN
      Integritypackage.InitNestLevel;
   END IF;
END;
/


