ALTER TABLE STATI_TERRITORI_STORICO ADD (
  CONSTRAINT STTS_PK
  PRIMARY KEY
  (STATO_TERRITORIO, DATA_INIZIO_VALIDITA)
  USING INDEX STTS_PK
  ENABLE VALIDATE);

