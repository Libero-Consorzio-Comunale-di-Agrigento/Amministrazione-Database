CREATE UNIQUE INDEX INFORMATIVE_PRIVACY_UK ON INFORMATIVE_PRIVACY
(ENTE, MODULO, CHIAVE_PRIVACY)
TABLESPACE AD4
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            MAXSIZE          UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


