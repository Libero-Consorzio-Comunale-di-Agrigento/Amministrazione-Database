CREATE OR REPLACE TRIGGER COMUNI_TB
BEFORE INSERT
    OR UPDATE
    OR DELETE
ON COMUNI
BEGIN
   -- RESET PostEvent for Custom Functional Check
   IF Integritypackage.GetNestLevel = 0 THEN
      Integritypackage.InitNestLevel;
   END IF;
END;
/


