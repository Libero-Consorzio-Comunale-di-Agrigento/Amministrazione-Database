CREATE OR REPLACE FORCE VIEW PROVINCIE
(PROVINCIA, DENOMINAZIONE, DENOMINAZIONE_AL1, DENOMINAZIONE_AL2, REGIONE, 
 SIGLA, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO)
BEQUEATH DEFINER
AS 
SELECT prov.Provincia Provincia,
          prov.denominazione denominazione,
          prov.denominazione_al1 denominazione_al1,
          prov.denominazione_al2 denominazione_al2,
          regione.Get_Id (Regione) Regione,
          prov.sigla sigla,
          UTENTE_AGGIORNAMENTO,
          DATA_AGGIORNAMENTO
     FROM PROVINCE prov;


