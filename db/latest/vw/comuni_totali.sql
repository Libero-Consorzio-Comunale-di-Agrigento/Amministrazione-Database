--liquibase formatted sql

--changeset mturra:201901301231_437 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW COMUNI_TOTALI
(
    REGIONE,
    PROVINCIA,
    COMUNE,
    DENOMINAZIONE,
    SIGLA,
    CAP,
    ISTAT,
    SIGLA_CFIS,
    DATA_SOPPRESSIONE,
    PROVINCIA_FUSIONE,
    COMUNE_FUSIONE,
    DENOMINAZIONE_PROVINCIA_STATO,
    UTENTE_AGGIORNAMENTO,
    DATA_AGGIORNAMENTO
)
AS
/******************************************************************************
 Handling of Definition Schema and Data Definition Manipulation
 REVISIONI.
 Rev. Data       Autore     Descrizione
 ---- ---------- ---------- ------------------------------------------------------
 00   19/04/2005            Prima emissione.
 01   17/04/2019 SNegroni   Visualizzazione info del comune/provincia
                            visualizzazione di tutti i comuni anche esteri
                            Feature #34456
******************************************************************************/
    SELECT pr.regione,
           co.provincia_stato
               provincia,
           co.comune,
           co.denominazione,
           pr.sigla,
           DECODE (TO_CHAR (co.cap),
                   NULL, NULL,
                   LPAD (TO_CHAR (co.cap), 5, '0'))
               cap,
              LPAD (TO_CHAR (co.provincia_stato), 3, '0')
           || LPAD (TO_CHAR (co.comune), 3, '0')
               istat,
           co.sigla_cfis,
           co.data_soppressione,
           co.provincia_fusione,
           co.comune_fusione,
           pr.denominazione
               DENOMINAZIONE_PROVINCIA_STATO,
           GREATEST (co.utente_aggiornamento, pr.utente_aggiornamento)
               utente_aggiornamento,
           GREATEST (co.data_aggiornamento, pr.data_aggiornamento)
               data_aggiornamento
      FROM provincie pr, comuni co
     WHERE     pr.provincia = co.provincia_stato
           AND co.provincia_stato BETWEEN 1 AND 199
           AND co.comune > 0
    UNION
    SELECT 0,
           co.provincia_stato,
           co.comune,
           co.denominazione,
           st.sigla,
           DECODE (TO_CHAR (co.cap),
                   NULL, NULL,
                   LPAD (TO_CHAR (co.cap), 5, '0')),
              LPAD (TO_CHAR (co.provincia_stato), 3, '0')
           || LPAD (TO_CHAR (co.comune), 3, '0'),
           co.sigla_cfis,
           co.data_soppressione,
           co.provincia_fusione,
           co.comune_fusione,
           st.denominazione,
           GREATEST (co.utente_aggiornamento, st.utente_aggiornamento)
               utente_aggiornamento,
           GREATEST (co.data_aggiornamento, st.data_aggiornamento)
               data_aggiornamento
      FROM stati_territori st, comuni co
     WHERE     st.stato_territorio = co.provincia_stato
           AND co.provincia_stato > 199
-- AND co.comune = 0
;

