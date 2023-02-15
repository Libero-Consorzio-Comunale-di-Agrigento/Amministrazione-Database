CREATE OR REPLACE FORCE VIEW RUOLI_APPLICATIVI
(RUOLO, DESCRIZIONE, DESCRIZIONE_AL1, DESCRIZIONE_AL2, STATO)
BEQUEATH DEFINER
AS 
SELECT ruolo, descrizione, descrizione_al1, descrizione_al2, stato
   FROM ruoli
   WHERE gruppo_lavoro = 'S' AND gruppo_so = 'S';


