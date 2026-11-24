--liquibase formatted sql

--changeset mturra:201901301231_440 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW KEY_NTEXT AS
SELECT TABELLA, COLONNA, PK, TESTO
  FROM KEY_DICTIONARY
 where LINGUA = 'I';

