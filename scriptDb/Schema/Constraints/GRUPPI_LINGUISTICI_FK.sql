ALTER TABLE GRUPPI_LINGUISTICI ADD (
  CONSTRAINT LING_GRLI_FK 
  FOREIGN KEY (LINGUA) 
  REFERENCES LINGUE (LINGUA)
  ENABLE VALIDATE);

