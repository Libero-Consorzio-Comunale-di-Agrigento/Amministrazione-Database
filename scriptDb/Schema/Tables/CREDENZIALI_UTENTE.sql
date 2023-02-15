CREATE TABLE CREDENZIALI_UTENTE
(
  ID_CREDENZIALE  NUMBER                        NOT NULL,
  UTENTE          VARCHAR2(8 BYTE)              NOT NULL
)
TABLESPACE AD4
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            MAXSIZE          UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


