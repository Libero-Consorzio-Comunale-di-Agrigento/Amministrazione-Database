ALTER TABLE SERVIZI ADD (
  CONSTRAINT ISTA_SERV_FK 
  FOREIGN KEY (ISTANZA) 
  REFERENCES ISTANZE (ISTANZA)
  ENABLE VALIDATE);

ALTER TABLE SERVIZI ADD (
  CONSTRAINT LISI_SERV_FK 
  FOREIGN KEY (LIVELLO) 
  REFERENCES LIVELLI_SICUREZZA (LIVELLO)
  ENABLE VALIDATE);

ALTER TABLE SERVIZI ADD (
  CONSTRAINT MODU_SERV_FK 
  FOREIGN KEY (MODULO) 
  REFERENCES MODULI (MODULO)
  ENABLE VALIDATE);

ALTER TABLE SERVIZI ADD (
  CONSTRAINT UTEN_SERV_FK 
  FOREIGN KEY (GRUPPO_ABILITAZIONE) 
  REFERENCES UTENTI (UTENTE)
  ENABLE VALIDATE);

