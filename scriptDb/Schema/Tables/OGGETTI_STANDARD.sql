CREATE TABLE OGGETTI_STANDARD
(
  OBJECT_NAME  VARCHAR2(128 BYTE),
  OBJECT_TYPE  VARCHAR2(23 BYTE)
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

COMMENT ON TABLE OGGETTI_STANDARD IS 'OGST - Tabella con gli oggetti standard distribuiti da ad4 ';



