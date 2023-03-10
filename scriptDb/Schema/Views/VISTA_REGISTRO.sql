CREATE OR REPLACE FORCE VIEW VISTA_REGISTRO
(FIGLIO, PADRE, CHIAVE, IMMAGINE, IMMAGINE_OPEN)
BEQUEATH DEFINER
AS 
SELECT DISTINCT chiave figlio
  , DECODE
    (Registro_Utility.livello_chiave (chiave)
   , 0, ''
   , 1, SUBSTR (chiave, 1, INSTR (chiave, '/') - 1)
   , SUBSTR (chiave
  , 1
  ,   INSTR (chiave
     , '/'
     , 1
     , Registro_Utility.livello_chiave (chiave)
   )
    - 1
   )
    ) padre
  , SUBSTR
    (chiave
   ,   INSTR (chiave
   , '/'
   , 1
   , DECODE (Registro_Utility.livello_chiave (chiave)
     , 0, 1
     ,
    --altrimenti instr con occorrenza = 0 = ERRORE
    Registro_Utility.livello_chiave (chiave)
   )
    )
  + 1
    ) chiave
  , '../common/images/AMV/cmsfolderclosed.gif'
  , '../common/images/AMV/cmslastfolderopen.gif'
  FROM REGISTRO
 WHERE Registro_Utility.livello_chiave (chiave) > 0
UNION
SELECT 'DB_USERS', '0', 'DB_USERS'
  , '../common/images/AMV/cmsfolderclosed.gif'
  , '../common/images/AMV/cmslastfolderopen.gif'
  FROM dual_pb
UNION
SELECT 'PRODUCTS', '0', 'PRODUCTS'
  , '../common/images/AMV/cmsfolderclosed.gif'
  , '../common/images/AMV/cmslastfolderopen.gif'
  FROM dual_pb
UNION
SELECT 'SI4_DB_USERS', '0', 'SI4_DB_USERS'
  , '../common/images/AMV/cmsfolderclosed.gif'
  , '../common/images/AMV/cmslastfolderopen.gif'
  FROM dual_pb
UNION
SELECT 'SI4_USERS', '0', 'SI4_USERS'
  , '../common/images/AMV/cmsfolderclosed.gif'
  , '../common/images/AMV/cmslastfolderopen.gif'
  FROM dual_pb;


