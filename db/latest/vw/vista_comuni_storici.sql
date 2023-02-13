--liquibase formatted sql

--changeset mturra:201901301231_449 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW VISTA_COMUNI_STORICI
(
    DAL,
    AL,
    PROVINCIA_STATO,
    COMUNE,
    DENOMINAZIONE,
    DENOMINAZIONE_AL1,
    DENOMINAZIONE_AL2,
    DENOMINAZIONE_BREVE,
    DENOMINAZIONE_BREVE_AL1,
    DENOMINAZIONE_BREVE_AL2,
    CAPOLUOGO_PROVINCIA,
    CAP,
    PROVINCIA_TRIBUNALE,
    COMUNE_TRIBUNALE,
    PROVINCIA_DISTRETTO,
    COMUNE_DISTRETTO,
    DATA_SOPPRESSIONE,
    PROVINCIA_FUSIONE,
    COMUNE_FUSIONE,
    SIGLA_CFIS,
    CONSOLATO,
    TIPO_CONSOLATO,
    UTENTE_AGGIORNAMENTO,
    DATA_AGGIORNAMENTO,
    FLAG_ISTAT,
    FLAG_SOPPRESSO,
    ARCHIVIO,
    DENOMINAZIONE_PROVINCIA_STATO,
    SIGLA_PROVINCIA_STATO
)
AS
    SELECT GREATEST (NVL (com.data_istituzione, pro.data_inizio_validita)),
           pro.data_fine_validita,
           com.PROVINCIA_STATO,
           com.COMUNE,
           com.DENOMINAZIONE,
           com.DENOMINAZIONE_AL1,
           com.DENOMINAZIONE_AL2,
           com.DENOMINAZIONE_BREVE,
           com.DENOMINAZIONE_BREVE_AL1,
           com.DENOMINAZIONE_BREVE_AL2,
           com.CAPOLUOGO_PROVINCIA,
           com.CAP,
           com.PROVINCIA_TRIBUNALE,
           com.COMUNE_TRIBUNALE,
           com.PROVINCIA_DISTRETTO,
           com.COMUNE_DISTRETTO,
           com.DATA_SOPPRESSIONE,
           com.PROVINCIA_FUSIONE,
           com.COMUNE_FUSIONE,
           com.SIGLA_CFIS,
           com.CONSOLATO,
           com.TIPO_CONSOLATO,
           com.UTENTE_AGGIORNAMENTO,
           com.DATA_AGGIORNAMENTO,
           pro.flag_istat     flag_istat,
           TO_CHAR (NULL)     flag_soppresso,
           'ITA_ATTIVI',
           pro.denominazione DENOMINAZIONE_PROVINCIA_STATO,
           pro.sigla SIGLA_PROVINCIA_STATO
      FROM province_storico pro, comuni com_f, comuni com
     WHERE     pro.provincia = com.provincia_stato
           AND com_f.provincia_stato(+) = com.provincia_fusione
           AND com_f.comune(+) = com.comune_fusione
           AND NVL (com.data_soppressione, com_f.data_soppressione) IS NULL
           AND com.provincia_stato < 198
           AND NVL (com.data_istituzione, pro.data_inizio_validita) <=
               NVL (NVL (com.data_soppressione, pro.data_fine_validita),
                    TO_DATE ('5373484', 'j'))
    UNION
    --
    -- comuni italiani soppressi
    -- (compreso vecchie denoninazioni con data soppressione)
    --
    SELECT NVL (com.data_istituzione, pro.data_inizio_validita),
           DECODE (
               pro.data_fine_validita,
               '', NVL (com.data_soppressione, com_f.data_soppressione),
               DECODE (
                   SIGN (
                         pro.data_fine_validita
                       - NVL (com.data_soppressione, com_f.data_soppressione)),
                   -1, pro.data_fine_validita,
                   NVL (com.data_soppressione, com_f.data_soppressione))),
           com.PROVINCIA_STATO,
           com.COMUNE,
           com.DENOMINAZIONE,
           com.DENOMINAZIONE_AL1,
           com.DENOMINAZIONE_AL2,
           com.DENOMINAZIONE_BREVE,
           com.DENOMINAZIONE_BREVE_AL1,
           com.DENOMINAZIONE_BREVE_AL2,
           com.CAPOLUOGO_PROVINCIA,
           com.CAP,
           com.PROVINCIA_TRIBUNALE,
           com.COMUNE_TRIBUNALE,
           com.PROVINCIA_DISTRETTO,
           com.COMUNE_DISTRETTO,
           NVL (com.data_soppressione, com_f.data_soppressione),
           com.PROVINCIA_FUSIONE,
           com.COMUNE_FUSIONE,
           com.SIGLA_CFIS,
           com.CONSOLATO,
           com.TIPO_CONSOLATO,
           com.UTENTE_AGGIORNAMENTO,
           com.DATA_AGGIORNAMENTO,
           pro.flag_istat
               flag_istat,
           'S'
               flag_soppresso,
           'ITA_SOPPRESSI',
           pro.denominazione DENOMINAZIONE_PROVINCIA_STATO,
           pro.sigla SIGLA_PROVINCIA_STATO
      FROM province_storico pro, comuni com_f, comuni com
     WHERE     pro.provincia = com.provincia_stato
           AND pro.data_inizio_validita <
               NVL (com.data_soppressione, com_f.data_soppressione)
           AND com_f.provincia_stato(+) = com.provincia_fusione
           AND com_f.comune(+) = com.comune_fusione
           AND NVL (com.data_soppressione, com_f.data_soppressione)
                   IS NOT NULL
           AND com.provincia_stato < 198
           AND NVL (com.data_istituzione, pro.data_inizio_validita) <=
               NVL (NVL (pro.data_fine_validita, com.data_soppressione),
                    TO_DATE ('5373484', 'j'))
    UNION
    --
    -- stati esteri: situazione attuale
    --
    SELECT sta.data_inizio_validita,
           sta.data_fine_validita,
           com.PROVINCIA_STATO,
           com.COMUNE,
           com.DENOMINAZIONE,
           com.DENOMINAZIONE_AL1,
           com.DENOMINAZIONE_AL2,
           com.DENOMINAZIONE_BREVE,
           com.DENOMINAZIONE_BREVE_AL1,
           com.DENOMINAZIONE_BREVE_AL2,
           com.CAPOLUOGO_PROVINCIA,
           com.CAP,
           com.PROVINCIA_TRIBUNALE,
           com.COMUNE_TRIBUNALE,
           com.PROVINCIA_DISTRETTO,
           com.COMUNE_DISTRETTO,
           com.DATA_SOPPRESSIONE,
           com.PROVINCIA_FUSIONE,
           com.COMUNE_FUSIONE,
           sta.codice_catasto,
           com.CONSOLATO,
           com.TIPO_CONSOLATO,
           com.UTENTE_AGGIORNAMENTO,
           com.DATA_AGGIORNAMENTO,
           sta.flag_istat                                  flag_istat,
           DECODE (com.data_soppressione, '', '', 'S')     flag_soppresso,
           'EST_ATTUALE_1',
           sta.denominazione DENOMINAZIONE_PROVINCIA_STATO,
           sta.sigla SIGLA_PROVINCIA_STATO
      FROM STATI_TERRITORI_STORICO sta, comuni com
     WHERE     sta.stato_territorio = com.provincia_stato
           --   and sta.data_fine_validita  is null
           --   and sta.data_inizio_validita  <= nvl(com.data_soppressione,to_date('5373484','j'))
           AND com.data_soppressione IS NULL
           AND com.provincia_stato NOT IN (701, 702, 703)
    UNION
    SELECT sta.data_inizio_validita,
           sta.data_fine_validita,
           com.PROVINCIA_STATO,
           com.COMUNE,
           com.DENOMINAZIONE,
           com.DENOMINAZIONE_AL1,
           com.DENOMINAZIONE_AL2,
           com.DENOMINAZIONE_BREVE,
           com.DENOMINAZIONE_BREVE_AL1,
           com.DENOMINAZIONE_BREVE_AL2,
           com.CAPOLUOGO_PROVINCIA,
           com.CAP,
           com.PROVINCIA_TRIBUNALE,
           com.COMUNE_TRIBUNALE,
           com.PROVINCIA_DISTRETTO,
           com.COMUNE_DISTRETTO,
           com.DATA_SOPPRESSIONE,
           com.PROVINCIA_FUSIONE,
           com.COMUNE_FUSIONE,
           sta.codice_catasto,
           com.CONSOLATO,
           com.TIPO_CONSOLATO,
           com.UTENTE_AGGIORNAMENTO,
           com.DATA_AGGIORNAMENTO,
           sta.flag_istat                                  flag_istat,
           DECODE (com.data_soppressione, '', '', 'S')     flag_soppresso,
           'EST_ATTUALE_2',
           sta.denominazione DENOMINAZIONE_PROVINCIA_STATO,
           sta.sigla SIGLA_PROVINCIA_STATO
      FROM STATI_TERRITORI_STORICO sta, comuni com
     WHERE     sta.stato_territorio = com.provincia_stato
           --   and sta.data_fine_validita  is null
           --   and sta.data_inizio_validita  <= nvl(com.data_soppressione,to_date('5373484','j'))
           AND com.data_soppressione IS NULL
           AND com.provincia_stato IN (701, 702, 703)
           AND com.comune < 500
    UNION
    --
    -- stati esteri: situazione storica
    --
    SELECT sta.data_inizio_validita,
           com.data_soppressione,
           com.PROVINCIA_STATO,
           com.COMUNE,
           com.DENOMINAZIONE,
           com.DENOMINAZIONE_AL1,
           com.DENOMINAZIONE_AL2,
           com.DENOMINAZIONE_BREVE,
           com.DENOMINAZIONE_BREVE_AL1,
           com.DENOMINAZIONE_BREVE_AL2,
           com.CAPOLUOGO_PROVINCIA,
           com.CAP,
           com.PROVINCIA_TRIBUNALE,
           com.COMUNE_TRIBUNALE,
           com.PROVINCIA_DISTRETTO,
           com.COMUNE_DISTRETTO,
           com.DATA_SOPPRESSIONE,
           com.PROVINCIA_FUSIONE,
           com.COMUNE_FUSIONE,
           sta.codice_catasto,
           com.CONSOLATO,
           com.TIPO_CONSOLATO,
           com.UTENTE_AGGIORNAMENTO,
           com.DATA_AGGIORNAMENTO,
           sta.flag_istat     flag_istat,
           'S'                flag_soppresso,
           'EST_STORICO_1',
           sta.denominazione DENOMINAZIONE_PROVINCIA_STATO,
           sta.sigla SIGLA_PROVINCIA_STATO
      FROM STATI_TERRITORI_STORICO sta, comuni com
     WHERE     sta.stato_territorio = com.provincia_stato
           --   and sta.data_fine_validita  is not null
           AND sta.data_inizio_validita <= com.data_soppressione
           AND com.provincia_stato NOT IN (701, 702, 703)
    UNION
    SELECT sta.data_inizio_validita,
           com.data_soppressione,
           com.PROVINCIA_STATO,
           com.COMUNE,
           com.DENOMINAZIONE,
           com.DENOMINAZIONE_AL1,
           com.DENOMINAZIONE_AL2,
           com.DENOMINAZIONE_BREVE,
           com.DENOMINAZIONE_BREVE_AL1,
           com.DENOMINAZIONE_BREVE_AL2,
           com.CAPOLUOGO_PROVINCIA,
           com.CAP,
           com.PROVINCIA_TRIBUNALE,
           com.COMUNE_TRIBUNALE,
           com.PROVINCIA_DISTRETTO,
           com.COMUNE_DISTRETTO,
           com.DATA_SOPPRESSIONE,
           com.PROVINCIA_FUSIONE,
           com.COMUNE_FUSIONE,
           sta.codice_catasto,
           com.CONSOLATO,
           com.TIPO_CONSOLATO,
           com.UTENTE_AGGIORNAMENTO,
           com.DATA_AGGIORNAMENTO,
           sta.flag_istat     flag_istat,
           'S'                flag_soppresso,
           'EST_STORICO_2',
           sta.denominazione DENOMINAZIONE_PROVINCIA_STATO,
           sta.sigla SIGLA_PROVINCIA_STATO
      FROM STATI_TERRITORI_STORICO sta, comuni com
     WHERE     sta.stato_territorio = com.provincia_stato
           --   and sta.data_fine_validita  is not null
           AND sta.data_inizio_validita <= com.data_soppressione
           AND com.provincia_stato IN (701, 702, 703)
           AND com.comune < 500
    UNION
    --
    -- comuni ceduti (fiume - pola - zara)
    --
    SELECT NVL (com.data_istituzione, TO_DATE (2400777, 'j')),
           NVL (com.data_soppressione, com_f.data_soppressione),
           com.PROVINCIA_STATO,
           com.COMUNE,
           com.DENOMINAZIONE,
           com.DENOMINAZIONE_AL1,
           com.DENOMINAZIONE_AL2,
           com.DENOMINAZIONE_BREVE,
           com.DENOMINAZIONE_BREVE_AL1,
           com.DENOMINAZIONE_BREVE_AL2,
           com.CAPOLUOGO_PROVINCIA,
           com.CAP,
           com.PROVINCIA_TRIBUNALE,
           com.COMUNE_TRIBUNALE,
           com.PROVINCIA_DISTRETTO,
           com.COMUNE_DISTRETTO,
           com.DATA_SOPPRESSIONE,
           com.PROVINCIA_FUSIONE,
           com.COMUNE_FUSIONE,
           com.sigla_cfis,
           com.CONSOLATO,
           com.TIPO_CONSOLATO,
           com.UTENTE_AGGIORNAMENTO,
           com.DATA_AGGIORNAMENTO,
           'S'     flag_istat,
           'S'     flag_soppresso,
           'CEDUTI',
           '' DENOMINAZIONE_PROVINCIA_STATO,--?
           '' SIGLA_PROVINCIA_STATO--?
      FROM comuni com_f, comuni com
     WHERE     com_f.provincia_stato(+) = com.provincia_fusione
           AND com_f.comune(+) = com.comune_fusione
           AND com.provincia_stato IN (701, 702, 703)
           AND com.comune >= 500
    UNION
    --
    -- comuni NON ISTAT
    --
    SELECT NVL (com.data_istituzione, TO_DATE (2400777, 'j')),
           NVL (com.data_soppressione, com_f.data_soppressione),
           com.PROVINCIA_STATO,
           com.COMUNE,
           com.DENOMINAZIONE,
           com.DENOMINAZIONE_AL1,
           com.DENOMINAZIONE_AL2,
           com.DENOMINAZIONE_BREVE,
           com.DENOMINAZIONE_BREVE_AL1,
           com.DENOMINAZIONE_BREVE_AL2,
           com.CAPOLUOGO_PROVINCIA,
           com.CAP,
           com.PROVINCIA_TRIBUNALE,
           com.COMUNE_TRIBUNALE,
           com.PROVINCIA_DISTRETTO,
           com.COMUNE_DISTRETTO,
           NVL (com.data_soppressione, com_f.data_soppressione),
           com.PROVINCIA_FUSIONE,
           com.COMUNE_FUSIONE,
           com.sigla_cfis,
           com.CONSOLATO,
           com.TIPO_CONSOLATO,
           com.UTENTE_AGGIORNAMENTO,
           com.DATA_AGGIORNAMENTO,
           ''
               flag_istat,
           DECODE (NVL (com.data_soppressione, com_f.data_soppressione),
                   '', '',
                   'S')
               flag_soppresso,
           'NON_ISTAT_1',
           '' DENOMINAZIONE_PROVINCIA_STATO,--?
           '' SIGLA_PROVINCIA_STATO--?
      FROM comuni com_f, comuni com
     WHERE     com_f.provincia_stato(+) = com.provincia_fusione
           AND com_f.comune(+) = com.comune_fusione
           AND com.provincia_stato IN (0, 198, 199)
    UNION
    SELECT NVL (com.data_istituzione, TO_DATE (2400777, 'j')),
           com.data_soppressione,
           com.PROVINCIA_STATO,
           com.COMUNE,
           com.DENOMINAZIONE,
           com.DENOMINAZIONE_AL1,
           com.DENOMINAZIONE_AL2,
           com.DENOMINAZIONE_BREVE,
           com.DENOMINAZIONE_BREVE_AL1,
           com.DENOMINAZIONE_BREVE_AL2,
           com.CAPOLUOGO_PROVINCIA,
           com.CAP,
           com.PROVINCIA_TRIBUNALE,
           com.COMUNE_TRIBUNALE,
           com.PROVINCIA_DISTRETTO,
           com.COMUNE_DISTRETTO,
           com.data_soppressione,
           com.PROVINCIA_FUSIONE,
           com.COMUNE_FUSIONE,
           com.sigla_cfis,
           com.CONSOLATO,
           com.TIPO_CONSOLATO,
           com.UTENTE_AGGIORNAMENTO,
           com.DATA_AGGIORNAMENTO,
           ''                                              flag_istat,
           DECODE (com.data_soppressione, '', '', 'S')     flag_soppresso,
           'NON_ISTAT_2',
           '' DENOMINAZIONE_PROVINCIA_STATO, --?
           '' SIGLA_PROVINCIA_STATO --?
      FROM comuni com
     WHERE     com.provincia_stato > 199
           AND NOT EXISTS
                   (SELECT 'x'
                      FROM STATI_TERRITORI_STORICO sta
                     WHERE stato_territorio = com.provincia_stato)
--UNION
--select com.provincia_stato, com.comune, com.denominazione, com.denominazione_al1,
--        'com.sigla_provincia',
--    -- ?, ?
--       com.data_soppressione,com.provincia_fusione, com.comune_fusione,
--       'S' flag_istat, 'S' flag_soppresso
--  from  comuni com
-- where com.provincia_stato in (701,702,703)
--   and com.comune between 701 and 799
--UNION
--
-- comuni sardegna: codifica del 1974
--
--select com.provincia_stato, com.comune, com.denominazione, com.denominazione_al1,
--       pro.sigla, pro.data_inizio_validita, com.data_soppressione,
--       com.data_soppressione, com.provincia_fusione, com.comune_fusione,
--      to_char(null) flag_istat, 'S' flag_soppresso
--  from province_storico pro, comuni_CA_1974 com
-- where pro.provincia                 = com.provincia_stato
--   and pro.data_inizio_validita          < com.data_soppressione
;

COMMENT ON TABLE VISTA_COMUNI_STORICI IS 'Elenco di tutti i comuni con dal e al ricavato in base alle date di soppressione x sigla CFIS. Dal e al possono essere nulli.';

