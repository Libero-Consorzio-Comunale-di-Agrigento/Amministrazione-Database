CREATE TABLE SOGGETTI_OLD
(
  SOGGETTO              NUMBER(8)               NOT NULL,
  NOME                  VARCHAR2(240 BYTE),
  SESSO                 VARCHAR2(1 BYTE),
  DATA_NASCITA          DATE,
  PROVINCIA_NAS         NUMBER(3),
  COMUNE_NAS            NUMBER(3),
  CODICE_FISCALE        VARCHAR2(16 BYTE),
  INDIRIZZO             VARCHAR2(40 BYTE),
  CAP                   VARCHAR2(5 BYTE),
  COMUNE                NUMBER(3),
  PROVINCIA             NUMBER(3),
  TELEFONO              VARCHAR2(14 BYTE),
  FAX                   VARCHAR2(14 BYTE),
  INDIRIZZO_WEB         VARCHAR2(2000 BYTE),
  NOTE                  VARCHAR2(240 BYTE),
  UTENTE_AGGIORNAMENTO  VARCHAR2(8 BYTE),
  DATA_AGGIORNAMENTO    DATE,
  COMPETENZA            VARCHAR2(8 BYTE),
  COMPETENZA_ESCLUSIVA  VARCHAR2(1 BYTE)
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

