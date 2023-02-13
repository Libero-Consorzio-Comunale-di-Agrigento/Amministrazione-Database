--liquibase formatted sql

--changeset mturra:201901301231_415 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER SI4_USER_SOURCE_TIU
BEFORE INSERT OR UPDATE
ON SI4_USER_SOURCE
FOR EACH ROW
BEGIN
   :new.name := TRIM(upper(:new.name));
   :new.type := TRIM(upper(:new.type));
   :new.filename := TRIM(upper(:new.filename));
END SI4_USER_SOURCE_TIU;
/

