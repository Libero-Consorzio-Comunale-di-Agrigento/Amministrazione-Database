ALTER TABLE LIVELLI_SICUREZZA ADD (
  CONSTRAINT LISI_PK
  PRIMARY KEY
  (LIVELLO)
  USING INDEX LISI_PK
  ENABLE VALIDATE);
