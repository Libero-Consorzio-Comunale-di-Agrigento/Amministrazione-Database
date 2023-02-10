--liquibase formatted sql

--changeset snegroni:202002181522 stripComments:false runOnChange:true

--preconditions onFail:MARK_RAN onError:CONTINUE
--precondition-sql-check expectedResult:0 select count(1) from user_tables where table_name = 'ASL_COMUNE';

CREATE TABLE ASL_COMUNE
(
  PROVINCIA             NUMBER(3)               NOT NULL,
  COMUNE                NUMBER(3)               NOT NULL,
  REGIONE_ASL           NUMBER(3)               NOT NULL,
  CODICE_ASL            NUMBER(4)               NOT NULL,
  PROPOSTA              VARCHAR2(1),
  UTENTE_AGGIORNAMENTO  VARCHAR2(8),
  DATA_AGGIORNAMENTO    DATE,
  ATTIVA                VARCHAR2(1)        DEFAULT 'S'                   NOT NULL
);

COMMENT ON TABLE ASL_COMUNE IS 'ASCO - Unita Sanitarie Locali e Aziende Ospedaliere del Comune';


CREATE INDEX ASCO_REAS_IK ON ASL_COMUNE
(REGIONE_ASL, CODICE_ASL);

CREATE UNIQUE INDEX ASL_COMUNE_PK ON ASL_COMUNE
(PROVINCIA, COMUNE, REGIONE_ASL, CODICE_ASL);