ALTER TABLE EVENTI ADD (
  CONSTRAINT EVENTI_GRAVITA_CC
  CHECK (GRAVITA is null or ( GRAVITA in ('I','S','E') ))
  ENABLE VALIDATE);

ALTER TABLE EVENTI ADD (
  CONSTRAINT EVENTI_NOTIFICATO_CC
  CHECK (NOTIFICATO in (1,0))
  ENABLE VALIDATE);

ALTER TABLE EVENTI ADD (
  CONSTRAINT EVENTI_STATO_CC
  CHECK (stato in ('U', 'D', 'A'))
  ENABLE VALIDATE);

ALTER TABLE EVENTI ADD (
  CONSTRAINT EVENTI_TIPO_CC
  CHECK (TIPO LIKE 'LOG%' OR TIPO IN ('ERROR','ARCLOG','APPTRC','SIALOG'))
  ENABLE VALIDATE);

ALTER TABLE EVENTI ADD (
  CONSTRAINT EVENTI_PK
  PRIMARY KEY
  (ID_EVENTO)
  USING INDEX EVENTI_PK
  ENABLE VALIDATE);

