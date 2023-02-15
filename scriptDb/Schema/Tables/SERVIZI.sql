CREATE TABLE SERVIZI
(
  ID_SERVIZIO          NUMBER                   NOT NULL,
  MODULO               VARCHAR2(10 BYTE)        NOT NULL,
  ISTANZA              VARCHAR2(10 BYTE)        NOT NULL,
  LIVELLO              VARCHAR2(8 BYTE),
  ABILITAZIONE         VARCHAR2(1 BYTE)         DEFAULT 'C'                   NOT NULL,
  GRUPPO_ABILITAZIONE  VARCHAR2(8 BYTE)         NOT NULL,
  MAIL_NOTIFICHE       VARCHAR2(100 BYTE)       NOT NULL,
  CCR_NOTIFICHE        VARCHAR2(100 BYTE),
  MULTIPLO             VARCHAR2(1 BYTE)         DEFAULT 'N'                   NOT NULL,
  TIPO_NOTIFICA        VARCHAR2(10 BYTE),
  RECUPERO_PASSWORD    VARCHAR2(1 BYTE)         DEFAULT 'N'                   NOT NULL
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

