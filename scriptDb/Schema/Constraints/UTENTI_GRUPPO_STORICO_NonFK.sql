ALTER TABLE UTENTI_GRUPPO_STORICO ADD (
  CONSTRAINT UTGS_OPERAZIONE_CK
  CHECK (operazione in ('I','D','BI','AI'))
  ENABLE VALIDATE);

ALTER TABLE UTENTI_GRUPPO_STORICO ADD (
  CONSTRAINT UTGS_PK
  PRIMARY KEY
  (ID_EVENTO)
  USING INDEX UTGS_PK
  ENABLE VALIDATE);

