ALTER TABLE TIPI_CREDENZIALE ADD (
  CONSTRAINT TICR_PK
  PRIMARY KEY
  (TIPO_CREDENZIALE)
  USING INDEX TICR_PK
  ENABLE VALIDATE);

