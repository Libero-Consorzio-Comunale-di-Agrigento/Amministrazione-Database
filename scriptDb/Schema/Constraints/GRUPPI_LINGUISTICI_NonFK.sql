ALTER TABLE GRUPPI_LINGUISTICI ADD (
  CONSTRAINT GRLI_PK
  PRIMARY KEY
  (LINGUA, LINGUA_AL)
  USING INDEX GRLI_PK
  ENABLE VALIDATE);

