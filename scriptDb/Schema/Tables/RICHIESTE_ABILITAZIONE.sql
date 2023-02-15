CREATE TABLE RICHIESTE_ABILITAZIONE
(
  ID_RICHIESTA        NUMBER                    NOT NULL,
  UTENTE              VARCHAR2(8 BYTE)          NOT NULL,
  MODULO              VARCHAR2(10 BYTE)         NOT NULL,
  ISTANZA             VARCHAR2(10 BYTE)         NOT NULL,
  STATO               VARCHAR2(1 BYTE)          DEFAULT 'F'                   NOT NULL,
  DATA                DATE,
  NOTE                VARCHAR2(2000 BYTE),
  TIPO_NOTIFICA       VARCHAR2(10 BYTE),
  INDIRIZZO_NOTIFICA  VARCHAR2(2000 BYTE),
  NOTE_NOTIFICA       VARCHAR2(2000 BYTE),
  NOTIFICATA          VARCHAR2(1 BYTE)          DEFAULT 'N'                   NOT NULL
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


