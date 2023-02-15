CREATE TABLE GRUPPI_LINGUISTICI
(
  LINGUA       VARCHAR2(1 BYTE)                 NOT NULL,
  LINGUA_AL    VARCHAR2(1 BYTE)                 NOT NULL,
  SEQUENZA     NUMBER(2)                        NOT NULL,
  DESCRIZIONE  VARCHAR2(40 BYTE)
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

COMMENT ON TABLE GRUPPI_LINGUISTICI IS 'GRLI - Tabella dei gruppi linguistici';



