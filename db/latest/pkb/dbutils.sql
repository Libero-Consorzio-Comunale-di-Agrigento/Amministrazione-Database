--liquibase formatted sql

--changeset mturra:201901301231_133 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY DBUTILS AS
--
-- versione specifica per le DbUtilsLite
--
FUNCTION versione
RETURN VARCHAR2
AS LANGUAGE JAVA NAME 'it.finmatica.jfc.dbUtil.Versione.versione() return java.lang.String';
END DBUTILS;
/

