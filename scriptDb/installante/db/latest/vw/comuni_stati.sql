--liquibase formatted sql

--changeset mturra:201901301231_436 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW COMUNI_STATI AS
SELECT 100 stato,
          pr.regione,
          co.provincia_stato provincia,
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
          co.comune_fusione
     FROM provincie pr, comuni co
    WHERE     pr.provincia = co.provincia_stato
          AND co.provincia_stato BETWEEN 1 AND 199
          AND co.comune > 0
   UNION ALL
   SELECT co.provincia_stato,
          0,
          0,
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
          co.comune_fusione
     FROM stati_territori st, comuni co
    WHERE     st.stato_territorio = co.provincia_stato
          AND co.provincia_stato > 199
          AND co.comune = 0;

