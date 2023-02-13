--liquibase formatted sql

--changeset snegroni:202002181527 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW USL
(
    COD_PROVINCIA,
    COD_COMUNE,
    REGIONE,
    ID_REGIONE,
    USL,
    DESCRIZIONE,
    PROPOSTA,
    ATTIVA
)
AS
    SELECT ASL_COMUNE.Provincia      COD_PROVINCIA,
           ASL_COMUNE.Comune         COD_COMUNE,
           PROVINCE.Regione          Regione,
           PROVINCIE.Regione         ID_REGIONE,
           ASL_COMUNE.CODICE_ASL     USL,
           ASL.DESCRIZIONE           DESCRIZIONE,
           ASL_COMUNE.PROPOSTA       PROPOSTA,
           ASL_COMUNE.ATTIVA
      FROM ASL,
           PROVINCIE,
           PROVINCE,
           ASL_COMUNE
     WHERE     ASL.REGIONE_ASL = ASL_COMUNE.REGIONE_ASL
           AND ASL.CODICE_ASL = ASL_COMUNE.CODICE_ASL
           AND PROVINCE.Provincia = ASL_COMUNE.Provincia
           AND PROVINCIE.Provincia = PROVINCE.Provincia;

COMMENT ON COLUMN USL.DESCRIZIONE IS 'Descrizione USL <NLS>';

