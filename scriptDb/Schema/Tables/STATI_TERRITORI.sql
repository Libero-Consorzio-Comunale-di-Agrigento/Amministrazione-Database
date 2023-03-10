CREATE TABLE STATI_TERRITORI
(
  STATO_TERRITORIO       NUMBER(3)              NOT NULL,
  DENOMINAZIONE          VARCHAR2(40 BYTE),
  DENOMINAZIONE_AL1      VARCHAR2(40 BYTE),
  DENOMINAZIONE_AL2      VARCHAR2(40 BYTE),
  SIGLA                  VARCHAR2(5 BYTE),
  DESC_CITTADINANZA      VARCHAR2(40 BYTE),
  DESC_CITTADINANZA_AL1  VARCHAR2(40 BYTE),
  DESC_CITTADINANZA_AL2  VARCHAR2(40 BYTE),
  RAGGRUPPAMENTO         NUMBER(2),
  STATO_APPARTENENZA     NUMBER(3),
  UTENTE_AGGIORNAMENTO   VARCHAR2(8 BYTE),
  DATA_AGGIORNAMENTO     DATE,
  SIGLA_ISO3166_ALPHA2   VARCHAR2(2 BYTE)
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

COMMENT ON TABLE STATI_TERRITORI IS 'STTE - Tabella degli stati e dei territori';

COMMENT ON COLUMN STATI_TERRITORI.STATO_TERRITORIO IS 'Codice dello stato/territorio estero';

COMMENT ON COLUMN STATI_TERRITORI.DENOMINAZIONE IS 'Denominazione dello stato/territorio estero';

COMMENT ON COLUMN STATI_TERRITORI.DENOMINAZIONE_AL1 IS 'Denominazione dello stato/territorio estero';

COMMENT ON COLUMN STATI_TERRITORI.DENOMINAZIONE_AL2 IS 'Denominazione dello stato/territorio estero';

COMMENT ON COLUMN STATI_TERRITORI.SIGLA IS 'Sigla automobilistica';

COMMENT ON COLUMN STATI_TERRITORI.DESC_CITTADINANZA IS 'Descrizione della cittadinanza';

COMMENT ON COLUMN STATI_TERRITORI.DESC_CITTADINANZA_AL1 IS 'Descrizione della cittadinanza';

COMMENT ON COLUMN STATI_TERRITORI.DESC_CITTADINANZA_AL2 IS 'Descrizione della cittadinanza';

COMMENT ON COLUMN STATI_TERRITORI.RAGGRUPPAMENTO IS 'Codice del raggruppamento';

COMMENT ON COLUMN STATI_TERRITORI.STATO_APPARTENENZA IS 'Codice dello stato/territorio estero';



