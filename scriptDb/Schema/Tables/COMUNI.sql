CREATE TABLE COMUNI
(
  PROVINCIA_STATO          NUMBER(3)            NOT NULL,
  COMUNE                   NUMBER(3)            NOT NULL,
  DENOMINAZIONE            VARCHAR2(40 BYTE),
  DENOMINAZIONE_AL1        VARCHAR2(40 BYTE),
  DENOMINAZIONE_AL2        VARCHAR2(40 BYTE),
  DENOMINAZIONE_BREVE      VARCHAR2(16 BYTE),
  DENOMINAZIONE_BREVE_AL1  VARCHAR2(16 BYTE),
  DENOMINAZIONE_BREVE_AL2  VARCHAR2(16 BYTE),
  CAPOLUOGO_PROVINCIA      VARCHAR2(1 BYTE),
  CAP                      NUMBER(5),
  PROVINCIA_TRIBUNALE      NUMBER(3),
  COMUNE_TRIBUNALE         NUMBER(3),
  PROVINCIA_DISTRETTO      NUMBER(3),
  COMUNE_DISTRETTO         NUMBER(3),
  DATA_SOPPRESSIONE        DATE,
  PROVINCIA_FUSIONE        NUMBER(3),
  COMUNE_FUSIONE           NUMBER(3),
  SIGLA_CFIS               VARCHAR2(4 BYTE),
  CONSOLATO                NUMBER(5),
  TIPO_CONSOLATO           NUMBER(2),
  UTENTE_AGGIORNAMENTO     VARCHAR2(8 BYTE),
  DATA_AGGIORNAMENTO       DATE,
  DATA_ISTITUZIONE         DATE
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

COMMENT ON TABLE COMUNI IS 'COMU - Tabella dei comuni italiani';

COMMENT ON COLUMN COMUNI.PROVINCIA_STATO IS 'Codice provincia per i comuni italiani dello stato per gli esteri';

COMMENT ON COLUMN COMUNI.COMUNE IS 'Codice comune';

COMMENT ON COLUMN COMUNI.DENOMINAZIONE IS 'Descrizione ufficiale del comune';

COMMENT ON COLUMN COMUNI.DENOMINAZIONE_AL1 IS 'Descrizione ufficiale del comune';

COMMENT ON COLUMN COMUNI.DENOMINAZIONE_AL2 IS 'Descrizione ufficiale del comune';

COMMENT ON COLUMN COMUNI.DENOMINAZIONE_BREVE IS 'Descrizione breve del comune';

COMMENT ON COLUMN COMUNI.DENOMINAZIONE_BREVE_AL1 IS 'Descrizione breve del comune';

COMMENT ON COLUMN COMUNI.DENOMINAZIONE_BREVE_AL2 IS 'Descrizione breve del comune';

COMMENT ON COLUMN COMUNI.CAPOLUOGO_PROVINCIA IS 'Indicatore di capoluogo di provincia per i comuni italiani';

COMMENT ON COLUMN COMUNI.CAP IS 'codice avviamento postale per i comuni italiani';

COMMENT ON COLUMN COMUNI.PROVINCIA_TRIBUNALE IS 'Codice della provincia';

COMMENT ON COLUMN COMUNI.COMUNE_TRIBUNALE IS 'Codice comune';

COMMENT ON COLUMN COMUNI.PROVINCIA_DISTRETTO IS 'Codice provincia per i comuni italiani dello stato per gli esteri';

COMMENT ON COLUMN COMUNI.COMUNE_DISTRETTO IS 'Codice comune';

COMMENT ON COLUMN COMUNI.DATA_SOPPRESSIONE IS 'Data nella quale il comune e" stato fuso con altri o soppresso';

COMMENT ON COLUMN COMUNI.PROVINCIA_FUSIONE IS 'Codice provincia per i comuni italiani dello stato per gli esteri';

COMMENT ON COLUMN COMUNI.COMUNE_FUSIONE IS 'Codice comune';

COMMENT ON COLUMN COMUNI.SIGLA_CFIS IS 'Sigla relativa ai comuni italiani per composizione codice fiscale';

COMMENT ON COLUMN COMUNI.CONSOLATO IS 'Codice del consolato';



