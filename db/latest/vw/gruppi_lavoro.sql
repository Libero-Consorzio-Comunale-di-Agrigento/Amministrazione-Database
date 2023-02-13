--liquibase formatted sql

--changeset mturra:201901301231_438 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW GRUPPI_LAVORO
(gruppo_lavoro, descrizione, descrizione_al1, descrizione_al2)
AS
SELECT ruolo, descrizione, descrizione_al1, descrizione_al2
  FROM RUOLI
 WHERE GRUPPO_LAVORO = 'S'
   AND stato = 'U';

