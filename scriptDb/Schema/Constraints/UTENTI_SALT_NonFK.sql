ALTER TABLE UTENTI_SALT ADD (
  CONSTRAINT UTSA_PK
  PRIMARY KEY
  (UTENTE)
  USING INDEX UTSA_PK
  ENABLE VALIDATE);

