ALTER TABLE DIRITTI_ACCESSO ADD (
  CONSTRAINT DIAC_PK
  PRIMARY KEY
  (UTENTE, MODULO, ISTANZA)
  USING INDEX DIAC_PK
  ENABLE VALIDATE);

