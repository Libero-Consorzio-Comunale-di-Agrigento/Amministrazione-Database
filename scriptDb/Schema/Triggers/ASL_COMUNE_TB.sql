CREATE OR REPLACE TRIGGER ASL_COMUNE_TB
BEFORE INSERT
    OR UPDATE
    OR DELETE
ON ASL_COMUNE
BEGIN
   -- RESET PostEvent for Custom Functional Check
   IF Integritypackage.GetNestLevel = 0 THEN
      Integritypackage.InitNestLevel;
   END IF;
END;
/


