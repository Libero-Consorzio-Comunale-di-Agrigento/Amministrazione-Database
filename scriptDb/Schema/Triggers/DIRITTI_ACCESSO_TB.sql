CREATE OR REPLACE TRIGGER DIRITTI_ACCESSO_TB
BEFORE INSERT
    OR UPDATE
    OR DELETE
ON DIRITTI_ACCESSO
BEGIN
   -- RESET PostEvent for Custom Functional Check
   IF Integritypackage.GetNestLevel = 0 THEN
      Integritypackage.InitNestLevel;
   END IF;
END;
/


