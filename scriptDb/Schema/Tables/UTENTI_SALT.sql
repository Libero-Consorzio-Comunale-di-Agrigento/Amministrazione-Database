CREATE TABLE UTENTI_SALT
(
  UTENTE     VARCHAR2(8 BYTE),
  SALT       VARCHAR2(200 BYTE),
  ALGORITMO  VARCHAR2(20 BYTE)
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


