CREATE TABLE DISABILITAZIONI
(
  RUOLO   VARCHAR2(8 BYTE)                      NOT NULL,
  MODULO  VARCHAR2(10 BYTE)                     NOT NULL,
  WINDOW  VARCHAR2(60 BYTE)                     NOT NULL
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

COMMENT ON TABLE DISABILITAZIONI IS 'DISA - Tabella delle disabilitazioni';



