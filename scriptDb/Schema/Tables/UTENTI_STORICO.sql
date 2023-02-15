CREATE TABLE UTENTI_STORICO
(
  ID_EVENTO             NUMBER                  NOT NULL,
  UTENTE                VARCHAR2(8 BYTE)        NOT NULL,
  ID_UTENTE             NUMBER(10)              NOT NULL,
  NOMINATIVO            VARCHAR2(40 BYTE)       NOT NULL,
  PASSWORD              VARCHAR2(200 BYTE),
  DATA_PASSWORD         DATE,
  RINNOVO_PASSWORD      VARCHAR2(2 BYTE),
  PWD_DA_MODIFICARE     VARCHAR2(2 BYTE)        DEFAULT 'NO',
  TIPO_UTENTE           VARCHAR2(1 BYTE)        DEFAULT 'U'                   NOT NULL,
  STATO                 VARCHAR2(1 BYTE)        DEFAULT 'U'                   NOT NULL,
  LINGUA                VARCHAR2(1 BYTE)        DEFAULT '*'                   NOT NULL,
  GRUPPO_LAVORO         VARCHAR2(8 BYTE)        DEFAULT 'def',
  IMPORTANZA            NUMBER(2),
  NOTE                  VARCHAR2(2000 BYTE),
  DATA_INSERIMENTO      DATE                    DEFAULT SYSDATE,
  UTENTE_AGGIORNAMENTO  VARCHAR2(8 BYTE),
  DATA_AGGIORNAMENTO    DATE,
  DATA                  DATE                    NOT NULL,
  OPERAZIONE            VARCHAR2(2 BYTE),
  BI_RIFERIMENTO        NUMBER,
  UTENTE_AGG            VARCHAR2(8 BYTE),
  USER_ORACLE           VARCHAR2(30 BYTE),
  INFO                  VARCHAR2(2000 BYTE),
  PROGRAMMA             VARCHAR2(50 BYTE),
  AMMINISTRATORE        VARCHAR2(1 BYTE)        DEFAULT 'N',
  INFO_IDENTIFICAZIONE  VARCHAR2(2000 BYTE)
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

COMMENT ON TABLE UTENTI_STORICO IS 'UTES - UTENTI STORICO';

COMMENT ON COLUMN UTENTI_STORICO.OPERAZIONE IS 'I=Insert, D=Delete, BI=Before Image, AI=After Image';

COMMENT ON COLUMN UTENTI_STORICO.BI_RIFERIMENTO IS 'id_evento di Before Image di riferimento';



