CREATE TABLE CARATTERISTICHE_ACCESSO
(
  CAAC_ID                   NUMBER              NOT NULL,
  TIPO_ACCESSO              VARCHAR2(1 BYTE)    NOT NULL,
  PROGETTO                  VARCHAR2(8 BYTE),
  ISTANZA                   VARCHAR2(10 BYTE),
  MODULO                    VARCHAR2(10 BYTE),
  UTENTE                    VARCHAR2(8 BYTE),
  ACCESSO                   VARCHAR2(1 BYTE),
  ACCESSO_SE                VARCHAR2(2 BYTE),
  TRACCIA                   VARCHAR2(1 BYTE),
  GIORNI_TRACCIA            NUMBER(3),
  TENTATIVI_PASSWORD        NUMBER(2),
  VALIDITA_PASSWORD         NUMBER(3),
  SINGLE_SIGN_ON            VARCHAR2(2 BYTE)    DEFAULT 'SI',
  SLEEP                     NUMBER,
  LDAP                      VARCHAR2(2 BYTE)    DEFAULT 'NO',
  MIN_LUNGHEZZA_PWD         NUMBER(2)           DEFAULT 0,
  MOD_PWD_PRIMO_ACCESSO     VARCHAR2(2 BYTE)    DEFAULT 'NO',
  ARCHIVIAZIONE_TRACCIA     VARCHAR2(2 BYTE)    DEFAULT 'NO'                  NOT NULL,
  DISLOCAZIONE_TRACCIA      VARCHAR2(100 BYTE),
  AMMESSI_CAR_SPECIALI_PWD  VARCHAR2(2 BYTE)    DEFAULT 'SI'                  NOT NULL,
  NUMERI_OBB_PWD            VARCHAR2(2 BYTE)    DEFAULT 'NO'                  NOT NULL,
  GG_PRIMA_RIUTILIZZO_PWD   NUMBER(3)
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

COMMENT ON TABLE CARATTERISTICHE_ACCESSO IS 'CAAC - Tabella di registrazione delle caratteristiche di accesso al sistema';



