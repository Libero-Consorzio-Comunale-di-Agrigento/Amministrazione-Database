CREATE TABLE UTENTI_DATI_FOGLIO_EXCEL
(
  UTENTE                         VARCHAR2(8 BYTE) NOT NULL,
  NOMINATIVO                     VARCHAR2(40 BYTE) NOT NULL,
  IS_UTENZA_TECNICA_ADS          VARCHAR2(2 BYTE),
  SOGGETTO_NUMERO                NUMBER,
  SOGGETTO_NOME                  VARCHAR2(240 BYTE),
  SOGGETTO_CODICE_FISCALE        VARCHAR2(16 BYTE),
  IS_IN_STRUTTURA_ORGANIZZATIVA  VARCHAR2(2 BYTE),
  ULTIMO_TENTATIVO               DATE,
  DIRITTI                        VARCHAR2(4000 BYTE),
  NUOVE_INFO                     VARCHAR2(1 BYTE),
  DISABILITARE                   VARCHAR2(2 BYTE),
  NUOVO_NOMINATIVO_UTENTE        VARCHAR2(30 BYTE),
  NUOVO_NI_ABBINARE              NUMBER,
  NUOVO_COGNOME                  VARCHAR2(30 BYTE),
  NUOVO_NOME                     VARCHAR2(30 BYTE),
  NUOVO_CODICE_FISCALE           VARCHAR2(16 BYTE),
  UTENZA_LDAP                    VARCHAR2(2 BYTE)
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

