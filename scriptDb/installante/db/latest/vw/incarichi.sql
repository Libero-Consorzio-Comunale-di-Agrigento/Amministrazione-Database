--liquibase formatted sql

--changeset mturra:201901301231_439 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW INCARICHI
(incarico, descrizione, descrizione_al1, descrizione_al2, responsabilita)
AS
SELECT ruolo
        , descrizione
        , descrizione_al1
        , descrizione_al2
        , responsabilita
   FROM ruoli
   WHERE incarico = 'S' AND stato = 'U';

