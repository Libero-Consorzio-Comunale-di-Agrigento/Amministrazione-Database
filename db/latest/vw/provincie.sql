--liquibase formatted sql

--changeset mturra:201901301231_443 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW PROVINCIE AS
SELECT prov.Provincia Provincia,
          prov.denominazione denominazione,
          prov.denominazione_al1 denominazione_al1,
          prov.denominazione_al2 denominazione_al2,
          regione.Get_Id (Regione) Regione,
          prov.sigla sigla,
          UTENTE_AGGIORNAMENTO,
          DATA_AGGIORNAMENTO
     FROM PROVINCE prov;

