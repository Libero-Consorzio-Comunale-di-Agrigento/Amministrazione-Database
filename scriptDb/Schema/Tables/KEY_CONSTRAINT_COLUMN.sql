CREATE TABLE KEY_CONSTRAINT_COLUMN
(
  NOME         VARCHAR2(30 BYTE)                NOT NULL,
  SEQUENZA     NUMBER(3)                        NOT NULL,
  COLONNA      VARCHAR2(30 BYTE)                NOT NULL,
  RIF_COLONNA  VARCHAR2(30 BYTE)
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


