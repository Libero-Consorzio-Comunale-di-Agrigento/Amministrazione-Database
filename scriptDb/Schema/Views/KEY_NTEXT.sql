CREATE OR REPLACE FORCE VIEW KEY_NTEXT
(TABELLA, COLONNA, PK, TESTO)
BEQUEATH DEFINER
AS 
SELECT TABELLA, COLONNA, PK, TESTO
  FROM KEY_DICTIONARY
 where LINGUA = 'I';


