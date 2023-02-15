CREATE TABLE EXTERNAL_FUNCTIONS
(
  FUNCTION_ID      NUMBER                       NOT NULL,
  TABLE_NAME       VARCHAR2(30 BYTE)            NOT NULL,
  FUNCTION_OWNER   VARCHAR2(30 BYTE)            NOT NULL,
  FIRING_FUNCTION  VARCHAR2(4000 BYTE)          NOT NULL,
  FIRING_EVENT     VARCHAR2(1 BYTE)             NOT NULL
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

COMMENT ON TABLE EXTERNAL_FUNCTIONS IS 'EXFU - Funzioni esterne da lanciare in seguito a modifica della tabella specificata.';



