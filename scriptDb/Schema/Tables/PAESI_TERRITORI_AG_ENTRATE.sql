CREATE TABLE PAESI_TERRITORI_AG_ENTRATE
(
  CODICE_PAESE_TERRITORIO  NUMBER(3)            NOT NULL,
  DENOMINAZIONE            VARCHAR2(40 BYTE)    NOT NULL,
  CODIFICA_ISTAT           NUMBER(3),
  BLACK_LIST               VARCHAR2(1 BYTE)     DEFAULT 'N'                   NOT NULL
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

COMMENT ON TABLE PAESI_TERRITORI_AG_ENTRATE IS 'TABELLA PAESI_TERRITORI_AG_ENTRATE - CODIFICA PAESI e TERRITORI - AGENZIA DELLE ENTRATE';

COMMENT ON COLUMN PAESI_TERRITORI_AG_ENTRATE.CODICE_PAESE_TERRITORIO IS 'Codice Paese Territorio Estero come da Codifica Agenzia delle ENTRATE';

COMMENT ON COLUMN PAESI_TERRITORI_AG_ENTRATE.DENOMINAZIONE IS 'Denominazione come da Codifica Agenzia delle ENTRATE';

COMMENT ON COLUMN PAESI_TERRITORI_AG_ENTRATE.CODIFICA_ISTAT IS 'Codifica ISTAT indica il legame con: stati_territori.stato_territorio ';

COMMENT ON COLUMN PAESI_TERRITORI_AG_ENTRATE.BLACK_LIST IS 'Indica se il paese territorio e attualmente presente nella black list (valori possibili ''S'',''N'')';



