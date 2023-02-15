CREATE TABLE CONSOLATI_NEW
(
  CONSOLATO             NUMBER(5)               NOT NULL,
  TIPO_CONSOLATO        NUMBER(2)               NOT NULL,
  STATO                 NUMBER(3)               NOT NULL,
  INDIRIZZO_1           VARCHAR2(50 BYTE),
  INDIRIZZO_2           VARCHAR2(50 BYTE),
  INDIRIZZO_3           VARCHAR2(50 BYTE),
  SEDE                  VARCHAR2(40 BYTE),
  UTENTE_AGGIORNAMENTO  VARCHAR2(8 BYTE),
  DATA_AGGIORNAMENTO    DATE
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


