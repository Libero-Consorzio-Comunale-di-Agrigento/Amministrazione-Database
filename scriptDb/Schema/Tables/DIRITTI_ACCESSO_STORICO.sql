CREATE TABLE DIRITTI_ACCESSO_STORICO
(
  ID_EVENTO             NUMBER                  NOT NULL,
  UTENTE                VARCHAR2(8 BYTE)        NOT NULL,
  MODULO                VARCHAR2(10 BYTE)       NOT NULL,
  ISTANZA               VARCHAR2(10 BYTE)       NOT NULL,
  RUOLO                 VARCHAR2(8 BYTE)        NOT NULL,
  SEQUENZA              NUMBER(4),
  ULTIMO_ACCESSO        DATE,
  NUMERO_ACCESSI        NUMBER,
  GRUPPO                VARCHAR2(8 BYTE),
  NOTE                  VARCHAR2(2000 BYTE),
  DATA                  DATE                    NOT NULL,
  OPERAZIONE            VARCHAR2(2 BYTE),
  BI_RIFERIMENTO        NUMBER,
  UTENTE_AGGIORNAMENTO  VARCHAR2(8 BYTE),
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

COMMENT ON TABLE DIRITTI_ACCESSO_STORICO IS 'DIAS - DIRITTI ACCESSO STORICO';

COMMENT ON COLUMN DIRITTI_ACCESSO_STORICO.OPERAZIONE IS 'I=Insert, D=Delete, BI=Before Image, AI=After Image';

COMMENT ON COLUMN DIRITTI_ACCESSO_STORICO.BI_RIFERIMENTO IS 'id_evento di Before Image di riferimento';



