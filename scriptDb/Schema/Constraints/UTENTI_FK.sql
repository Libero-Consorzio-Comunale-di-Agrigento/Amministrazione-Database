ALTER TABLE UTENTI ADD (
  CONSTRAINT RUOL_UTEN_FK 
  FOREIGN KEY (GRUPPO_LAVORO) 
  REFERENCES RUOLI (RUOLO)
  ENABLE VALIDATE);

