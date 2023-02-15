CREATE TABLE DIRITTI_ACCESSO
(
  UTENTE          VARCHAR2(8 BYTE)              NOT NULL,
  MODULO          VARCHAR2(10 BYTE)             NOT NULL,
  ISTANZA         VARCHAR2(10 BYTE)             NOT NULL,
  RUOLO           VARCHAR2(8 BYTE)              NOT NULL,
  SEQUENZA        NUMBER(4),
  ULTIMO_ACCESSO  DATE,
  NUMERO_ACCESSI  NUMBER,
  GRUPPO          VARCHAR2(8 BYTE),
  NOTE            VARCHAR2(2000 BYTE)
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

COMMENT ON TABLE DIRITTI_ACCESSO IS 'DIAC - Tabella dei diritti di accesso';



