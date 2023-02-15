CREATE OR REPLACE FORCE VIEW VISTA_GRUPPI_UTENTI
(FIGLIO, PADRE, LIVELLO, UTENTE, GRUPPO, 
 GRUPPO_PREC)
BEQUEATH DEFINER
AS 
SELECT Utente figlio
     , '0' padre
    , 0 livello
     , Utente UTENTE
     ,'0' Gruppo
     , '' GRUPPO_PREC
FROM UTENTI uten
WHERE NOT EXISTS (SELECT 'x' FROM UTENTI_GRUPPO
                   WHERE Utente = uten.Utente)
AND Utente != 'ric_abil'
UNION
SELECT Gruppo||'['||Utente figlio
     , DECODE(PRIOR Gruppo,NULL,'', PRIOR Gruppo||'[' )|| Gruppo  padre
     , LEVEL livello
     , Utente UTENTE
     , Gruppo Gruppo
     , PRIOR Gruppo GRUPPO_PREC
  FROM UTENTI_GRUPPO grup
  WHERE Utente != 'ric_abil'
  START WITH NOT EXISTS (SELECT 'x' FROM UTENTI_GRUPPO WHERE grup.Gruppo =Utente)
 CONNECT BY PRIOR  Utente= Gruppo;


