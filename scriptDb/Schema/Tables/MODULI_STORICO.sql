CREATE TABLE MODULI_STORICO
(
  ID_EVENTO        NUMBER                       NOT NULL,
  MODULO           VARCHAR2(10 BYTE)            NOT NULL,
  DESCRIZIONE      VARCHAR2(40 BYTE)            NOT NULL,
  DESCRIZIONE_AL1  VARCHAR2(40 BYTE),
  DESCRIZIONE_AL2  VARCHAR2(40 BYTE),
  PROGETTO         VARCHAR2(8 BYTE)             NOT NULL,
  NOTE             VARCHAR2(2000 BYTE),
  AMMINISTRATORE   VARCHAR2(1 BYTE)             DEFAULT 'N',
  DATA             DATE                         NOT NULL,
  OPERAZIONE       VARCHAR2(2 BYTE),
  BI_RIFERIMENTO   NUMBER,
  UTENTE_AGG       VARCHAR2(8 BYTE),
  USER_ORACLE      VARCHAR2(30 BYTE),
  INFO             VARCHAR2(2000 BYTE),
  PROGRAMMA        VARCHAR2(50 BYTE)
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

