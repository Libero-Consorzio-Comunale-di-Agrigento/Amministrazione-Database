CREATE TABLE PROGETTI
(
  PROGETTO         VARCHAR2(8 BYTE)             NOT NULL,
  DESCRIZIONE      VARCHAR2(40 BYTE),
  PRIORITA         NUMBER(2),
  NOTE             VARCHAR2(2000 BYTE),
  DESCRIZIONE_AL1  VARCHAR2(40 BYTE),
  DESCRIZIONE_AL2  VARCHAR2(40 BYTE)
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

COMMENT ON TABLE PROGETTI IS 'PROG - Progetti. (Valori: 0 bassa - 99 alta)';

COMMENT ON COLUMN PROGETTI.DESCRIZIONE IS 'Descrizione PROGETTO <NLS>';

COMMENT ON COLUMN PROGETTI.PRIORITA IS 'Valori: 0 bassa - 99 alta';



