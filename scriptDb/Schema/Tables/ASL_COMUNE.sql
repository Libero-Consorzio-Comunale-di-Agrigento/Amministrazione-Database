CREATE TABLE ASL_COMUNE
(
  PROVINCIA             NUMBER(3)               NOT NULL,
  COMUNE                NUMBER(3)               NOT NULL,
  REGIONE_ASL           NUMBER(3)               NOT NULL,
  CODICE_ASL            NUMBER(4)               NOT NULL,
  PROPOSTA              VARCHAR2(1 BYTE),
  UTENTE_AGGIORNAMENTO  VARCHAR2(8 BYTE),
  DATA_AGGIORNAMENTO    DATE,
  ATTIVA                VARCHAR2(1 BYTE)        DEFAULT 'S'                   NOT NULL
)
TABLESPACE AD4
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MAXSIZE          UNLIMITED
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );

COMMENT ON TABLE ASL_COMUNE IS 'ASCO - Unita Sanitarie Locali e Aziende Ospedaliere del Comune';



