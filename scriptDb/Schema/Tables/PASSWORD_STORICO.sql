CREATE TABLE PASSWORD_STORICO
(
  ID_EVENTO             NUMBER,
  UTENTE                VARCHAR2(8 BYTE),
  PASSWORD              VARCHAR2(200 BYTE),
  DATA_PASSWORD         DATE,
  UTENTE_AGGIORNAMENTO  VARCHAR2(8 BYTE),
  DATA_AGGIORNAMENTO    DATE,
  DATA                  DATE                    NOT NULL,
  OPERAZIONE            VARCHAR2(2 BYTE),
  BI_RIFERIMENTO        NUMBER,
  UTENTE_AGG            VARCHAR2(8 BYTE),
  USER_ORACLE           VARCHAR2(30 BYTE),
  INFO                  VARCHAR2(2000 BYTE),
  PROGRAMMA             VARCHAR2(50 BYTE)
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


