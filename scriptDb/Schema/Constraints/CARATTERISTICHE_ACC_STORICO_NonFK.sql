ALTER TABLE CARATTERISTICHE_ACC_STORICO ADD (
  CONSTRAINT CAAS_OPERAZIONE_CK
  CHECK (operazione in ('I','D','BI','AI'))
  ENABLE VALIDATE);

ALTER TABLE CARATTERISTICHE_ACC_STORICO ADD (
  CONSTRAINT CAAS_PK
  PRIMARY KEY
  (ID_EVENTO)
  USING INDEX CAAS_PK
  ENABLE VALIDATE);

