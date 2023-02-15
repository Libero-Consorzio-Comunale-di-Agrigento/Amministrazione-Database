CREATE TABLE EVENTI
(
  ID_EVENTO     NUMBER                          NOT NULL,
  TESTO         VARCHAR2(2000 BYTE)             NOT NULL,
  ANNOTAZIONI   VARCHAR2(2000 BYTE),
  TIPO          VARCHAR2(8 BYTE)                NOT NULL,
  GRAVITA       VARCHAR2(1 BYTE)                DEFAULT 'I',
  DB_USER       VARCHAR2(30 BYTE)               NOT NULL,
  UTENTE        VARCHAR2(8 BYTE),
  MODULO        VARCHAR2(10 BYTE),
  ISTANZA       VARCHAR2(10 BYTE),
  DATA          DATE                            DEFAULT sysdate               NOT NULL,
  NOTIFICATO    NUMBER                          DEFAULT 0                     NOT NULL,
  FILE_LOCATOR  BFILE,
  STATO         VARCHAR2(1 BYTE)                DEFAULT 'U'                   NOT NULL,
  SESSION_ID    NUMBER
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

COMMENT ON TABLE EVENTI IS 'Traccia degli eventi e delle  eventuali segnalazioni';

COMMENT ON COLUMN EVENTI.ID_EVENTO IS 'Identificativo dell''evento';

COMMENT ON COLUMN EVENTI.TESTO IS 'Testo';

COMMENT ON COLUMN EVENTI.ANNOTAZIONI IS 'Eventuali informazioni aggiuntive <NLS>';

COMMENT ON COLUMN EVENTI.TIPO IS 'Tipologia dell''evento. Ad esempio LOG.';

COMMENT ON COLUMN EVENTI.GRAVITA IS 'Gravita dell''evento';

COMMENT ON COLUMN EVENTI.DB_USER IS 'DB User dell''evento';

COMMENT ON COLUMN EVENTI.UTENTE IS 'Eventuale utente di procedura dell''evento';

COMMENT ON COLUMN EVENTI.MODULO IS 'Eventuale modulo di procedura dell''evento';

COMMENT ON COLUMN EVENTI.ISTANZA IS 'Eventuale  istanza di ambiente dell''evento';

COMMENT ON COLUMN EVENTI.DATA IS 'Data di registrazione';

COMMENT ON COLUMN EVENTI.NOTIFICATO IS 'Indicatore di avvenuta notifica dell''evento';



