CREATE TABLE SPORTELLI
(
  ABI          VARCHAR2(5 BYTE)                 NOT NULL,
  CAB          VARCHAR2(5 BYTE)                 NOT NULL,
  CIN_CAB      VARCHAR2(1 BYTE),
  DESCRIZIONE  VARCHAR2(40 BYTE),
  INDIRIZZO    VARCHAR2(60 BYTE),
  LOCALITA     VARCHAR2(20 BYTE),
  COMUNE       VARCHAR2(20 BYTE),
  PROVINCIA    VARCHAR2(2 BYTE),
  CAP          VARCHAR2(5 BYTE),
  DIPENDENZA   VARCHAR2(4 BYTE),
  BIC          VARCHAR2(11 BYTE)
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

COMMENT ON TABLE SPORTELLI IS 'SPOR - Sportelli bancari degli istituti di credito';

COMMENT ON COLUMN SPORTELLI.DESCRIZIONE IS 'Denominazione dello Sportello <NLS>';



