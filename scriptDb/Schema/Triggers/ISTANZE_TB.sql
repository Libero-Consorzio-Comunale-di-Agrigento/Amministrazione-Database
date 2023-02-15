CREATE OR REPLACE TRIGGER ISTANZE_TB
BEFORE INSERT
    OR UPDATE
    OR DELETE
ON ISTANZE
BEGIN
   -- RESET PostEvent for Custom Functional Check
   IF Integritypackage.GetNestLevel = 0 THEN
      Integritypackage.InitNestLevel;
   END IF;
END;
/


