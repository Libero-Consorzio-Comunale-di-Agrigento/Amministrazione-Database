CREATE TABLE RUOLI
(
  RUOLO            VARCHAR2(8 BYTE)             NOT NULL,
  DESCRIZIONE      VARCHAR2(40 BYTE)            NOT NULL,
  PROGETTO         VARCHAR2(8 BYTE),
  MODULO           VARCHAR2(10 BYTE),
  PROFILO          NUMBER(2),
  DESCRIZIONE_AL1  VARCHAR2(40 BYTE),
  DESCRIZIONE_AL2  VARCHAR2(40 BYTE),
  STATO            VARCHAR2(1 BYTE)             DEFAULT 'U'                   NOT NULL,
  GRUPPO_LAVORO    VARCHAR2(1 BYTE)             DEFAULT 'N'                   NOT NULL,
  GRUPPO_SO        VARCHAR2(1 BYTE)             DEFAULT 'N'                   NOT NULL,
  INCARICO         VARCHAR2(1 BYTE)             DEFAULT 'N'                   NOT NULL,
  RESPONSABILITA   VARCHAR2(1 BYTE)             DEFAULT 'N'                   NOT NULL
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

COMMENT ON TABLE RUOLI IS 'RUOL - Ruoli di abilitazione Utenti';

COMMENT ON COLUMN RUOLI.DESCRIZIONE IS 'Descrizione RUOLO <NLS>';

COMMENT ON COLUMN RUOLI.PROFILO IS 'Profilo numerico assegnato al Ruolo';

COMMENT ON COLUMN RUOLI.INCARICO IS 'Se incarico = S/N';

COMMENT ON COLUMN RUOLI.RESPONSABILITA IS 'Se incarico con responsabilita = S/N';



