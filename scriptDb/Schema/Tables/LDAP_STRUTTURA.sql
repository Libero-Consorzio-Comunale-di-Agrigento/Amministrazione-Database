CREATE TABLE LDAP_STRUTTURA
(
  ID_DN      NUMBER                             NOT NULL,
  ID_PADRE   NUMBER,
  DN         VARCHAR2(4000 BYTE)                NOT NULL,
  CATEGORIA  VARCHAR2(64 BYTE)                  NOT NULL
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


