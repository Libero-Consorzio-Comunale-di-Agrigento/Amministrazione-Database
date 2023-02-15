CREATE OR REPLACE FORCE VIEW INCARICHI
(INCARICO, DESCRIZIONE, DESCRIZIONE_AL1, DESCRIZIONE_AL2, RESPONSABILITA)
BEQUEATH DEFINER
AS 
SELECT ruolo
        , descrizione
        , descrizione_al1
        , descrizione_al2
        , responsabilita
   FROM ruoli
   WHERE incarico = 'S' AND stato = 'U';


