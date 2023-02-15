ALTER TABLE RICHIESTE_ABILITAZIONE ADD (
  CONSTRAINT RICHIESTE_ABI_NOTIFICATA_CC
  CHECK (NOTIFICATA in ('N','S','F'))
  ENABLE VALIDATE);

ALTER TABLE RICHIESTE_ABILITAZIONE ADD (
  CONSTRAINT RICHIESTE_ABI_STATO_CC
  CHECK (STATO in ('A','R','C','F'))
  ENABLE VALIDATE);

ALTER TABLE RICHIESTE_ABILITAZIONE ADD (
  CONSTRAINT RICHIESTE_ABI_TIPO_NOTIFICA_CC
  CHECK (TIPO_NOTIFICA is null or (TIPO_NOTIFICA in ('MAIL','POSTA','SMS','FAX')))
  ENABLE VALIDATE);

ALTER TABLE RICHIESTE_ABILITAZIONE ADD (
  CONSTRAINT RIAB_PK
  PRIMARY KEY
  (ID_RICHIESTA)
  USING INDEX RIAB_PK
  ENABLE VALIDATE);

