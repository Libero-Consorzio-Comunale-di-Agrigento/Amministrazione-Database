CREATE TABLE LIVELLI_SICUREZZA
(
  LIVELLO      VARCHAR2(8 BYTE)                 NOT NULL,
  DESCRIZIONE  VARCHAR2(40 BYTE)
)
TABLESPACE AD4
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            MAXSIZE          UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );

COMMENT ON COLUMN LIVELLI_SICUREZZA.DESCRIZIONE IS 'Descrizione LIVELLO di SICUREZZA <NLS>';



