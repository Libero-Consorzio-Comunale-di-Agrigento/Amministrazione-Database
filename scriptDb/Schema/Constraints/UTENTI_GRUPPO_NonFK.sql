ALTER TABLE UTENTI_GRUPPO ADD (
  CONSTRAINT UTGR_PK
  PRIMARY KEY
  (UTENTE, GRUPPO)
  USING INDEX UTGR_PK
  ENABLE VALIDATE);

