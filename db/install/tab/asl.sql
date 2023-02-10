--liquibase formatted sql

--changeset snegroni:202002181521 stripComments:false runOnChange:true

--preconditions onFail:MARK_RAN onError:CONTINUE
--precondition-sql-check expectedResult:0 select count(1) from user_tables where table_name = 'ASL';

CREATE TABLE ASL
(
  REGIONE_ASL           NUMBER(3)               NOT NULL,
  CODICE_ASL            NUMBER(4)               NOT NULL,
  DESCRIZIONE           VARCHAR2(30),
  UTENTE_AGGIORNAMENTO  VARCHAR2(8),
  DATA_AGGIORNAMENTO    DATE,
  ATTIVA                VARCHAR2(1)        DEFAULT 'S'                   NOT NULL
);

COMMENT ON TABLE ASL IS 'ASL - Unita Sanitarie Locali e Aziende Ospedaliere';

COMMENT ON COLUMN ASL.DESCRIZIONE IS 'Descrizione della ASL <NLS>';


CREATE INDEX ASL_DESCRIZIONE_IK ON ASL
(DESCRIZIONE);

CREATE UNIQUE INDEX ASL_PK ON ASL
(REGIONE_ASL, CODICE_ASL);

