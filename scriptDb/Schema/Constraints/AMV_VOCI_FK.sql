ALTER TABLE AMV_VOCI ADD (
  CONSTRAINT AMV_VOCI_PROG_FK 
  FOREIGN KEY (PROGETTO) 
  REFERENCES PROGETTI (PROGETTO)
  ENABLE VALIDATE);

ALTER TABLE AMV_VOCI ADD (
  CONSTRAINT AMV_VOCI_VOCI_FK 
  FOREIGN KEY (VOCE_GUIDA) 
  REFERENCES AMV_VOCI (VOCE)
  ON DELETE SET NULL
  ENABLE VALIDATE);

