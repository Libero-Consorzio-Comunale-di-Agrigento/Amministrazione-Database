CREATE OR REPLACE TRIGGER CARATTERISTICHE_ACCESSO_TB
BEFORE INSERT
    OR UPDATE
    OR DELETE
ON CARATTERISTICHE_ACCESSO
BEGIN
   -- RESET PostEvent for Custom Functional Check
   IF Integritypackage.GetNestLevel = 0 THEN
      Integritypackage.InitNestLevel;
   END IF;
END;
/


