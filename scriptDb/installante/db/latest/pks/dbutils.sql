--liquibase formatted sql

--changeset mturra:201901301231_230 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE DBUTILS AS
--
-- versione specifica per le DbUtilsLite
--
FUNCTION versione
RETURN VARCHAR2;
END DBUTILS;
/

