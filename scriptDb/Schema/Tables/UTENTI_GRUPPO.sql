CREATE TABLE UTENTI_GRUPPO
(
  UTENTE  VARCHAR2(8 BYTE)                      NOT NULL,
  GRUPPO  VARCHAR2(8 BYTE)                      NOT NULL
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

COMMENT ON TABLE UTENTI_GRUPPO IS 'UTGR - Utenti del Gruppo';



