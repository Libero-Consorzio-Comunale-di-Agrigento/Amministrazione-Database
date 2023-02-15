CREATE TABLE UTENTI
(
  UTENTE                VARCHAR2(8 BYTE)        NOT NULL,
  ID_UTENTE             NUMBER(10)              NOT NULL,
  NOMINATIVO            VARCHAR2(40 BYTE)       NOT NULL,
  PASSWORD              VARCHAR2(200 BYTE),
  DATA_PASSWORD         DATE,
  RINNOVO_PASSWORD      VARCHAR2(2 BYTE),
  PWD_DA_MODIFICARE     VARCHAR2(2 BYTE)        DEFAULT 'NO',
  ULTIMO_TENTATIVO      DATE,
  NUMERO_TENTATIVI      NUMBER(2),
  TIPO_UTENTE           VARCHAR2(1 BYTE)        DEFAULT 'U'                   NOT NULL,
  STATO                 VARCHAR2(1 BYTE)        DEFAULT 'U'                   NOT NULL,
  LINGUA                VARCHAR2(1 BYTE)        DEFAULT '*'                   NOT NULL,
  GRUPPO_LAVORO         VARCHAR2(8 BYTE)        DEFAULT 'def',
  IMPORTANZA            NUMBER(2),
  NOTE                  VARCHAR2(2000 BYTE),
  DATA_INSERIMENTO      DATE                    DEFAULT SYSDATE,
  UTENTE_AGGIORNAMENTO  VARCHAR2(8 BYTE),
  DATA_AGGIORNAMENTO    DATE,
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

COMMENT ON TABLE UTENTI IS 'UTEN - Tabella degli utenti ( importanza: 0 alta - 99 bassa)';

COMMENT ON COLUMN UTENTI.DATA_PASSWORD IS 'Data di modifica della password';

COMMENT ON COLUMN UTENTI.RINNOVO_PASSWORD IS 'Indica se l''utente puo rinnovare la password';

COMMENT ON COLUMN UTENTI.ULTIMO_TENTATIVO IS 'Data dell''ultimo tentativo di connessione';

COMMENT ON COLUMN UTENTI.NUMERO_TENTATIVI IS 'Numero di tentativi di connessione falliti dopo dopo l''ultimo valido';

COMMENT ON COLUMN UTENTI.STATO IS 'Stato dell''utente';

COMMENT ON COLUMN UTENTI.IMPORTANZA IS 'Valori: 0 alta - 99 bassa';

COMMENT ON COLUMN UTENTI.NOTE IS 'Annotazioni sull''utente';

COMMENT ON COLUMN UTENTI.UTENTE_AGGIORNAMENTO IS 'Autore dell''inserimento o dell''aggiornamento';

COMMENT ON COLUMN UTENTI.AMMINISTRATORE IS 'Indica se utenza utilizzata per funzioni di AMMINISTRAZIONE';

COMMENT ON COLUMN UTENTI.INFO_IDENTIFICAZIONE IS 'Informazioni per individuare chi utilizza una specifica utenza di AMMINISTRAZIONE';



