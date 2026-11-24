--liquibase formatted sql

--changeset mturra:201901301231_444 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW RUOLI_APPLICATIVI AS
SELECT ruolo, descrizione, descrizione_al1, descrizione_al2, stato
   FROM ruoli
   WHERE gruppo_lavoro = 'S' AND gruppo_so = 'S';

