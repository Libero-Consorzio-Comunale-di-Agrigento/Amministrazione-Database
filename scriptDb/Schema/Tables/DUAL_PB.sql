CREATE TABLE DUAL_PB
(
  PB        VARCHAR2(20 BYTE)                   NOT NULL,
  NUMERO    NUMBER,
  STRINGA   VARCHAR2(60 BYTE),
  DATA      DATE,
  STRINGA2  VARCHAR2(2000 BYTE),
  DECIMALE  NUMBER(11,11)
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

COMMENT ON TABLE DUAL_PB IS 'DUPB - Tabella di appoggio';



