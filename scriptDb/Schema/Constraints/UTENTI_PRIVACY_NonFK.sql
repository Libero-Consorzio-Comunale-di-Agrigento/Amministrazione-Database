ALTER TABLE UTENTI_PRIVACY ADD (
  CONSTRAINT UTENTI_PRIVACY_PK
  PRIMARY KEY
  (ID)
  USING INDEX UTENTI_PRIVACY_PK
  ENABLE VALIDATE);

ALTER TABLE UTENTI_PRIVACY ADD (
  CONSTRAINT UTENTI_PRIVACY_UK
  UNIQUE (UTENTE, ENTE, MODULO)
  USING INDEX UTENTI_PRIVACY_UK
  ENABLE VALIDATE);
