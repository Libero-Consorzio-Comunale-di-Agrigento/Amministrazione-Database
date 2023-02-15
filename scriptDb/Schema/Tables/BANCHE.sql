CREATE TABLE BANCHE
(
  ABI            VARCHAR2(5 BYTE)               NOT NULL,
  CIN_ABI        VARCHAR2(1 BYTE)               NOT NULL,
  DENOMINAZIONE  VARCHAR2(60 BYTE)              NOT NULL
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

COMMENT ON TABLE BANCHE IS 'BANC - Banche e Istituti di credito';

COMMENT ON COLUMN BANCHE.DENOMINAZIONE IS 'Denominazione della Banca <NLS>';



