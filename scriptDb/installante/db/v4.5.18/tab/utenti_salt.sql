--liquibase formatted sql

--changeset snegroni:202003051319
--comment creazione della tabella UTENTI_SALT

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_tables s where s.TABLE_NAME='UTENTI_SALT'

CREATE TABLE UTENTI_SALT
(
  UTENTE     VARCHAR2(8),
  SALT       VARCHAR2(100),
  ALGORITMO  VARCHAR2(20)
);


CREATE UNIQUE INDEX UTSA_PK ON UTENTI_SALT (UTENTE);


ALTER TABLE UTENTI_SALT ADD (
  CONSTRAINT UTSA_PK
  PRIMARY KEY
  (UTENTE)
  USING INDEX UTSA_PK
  ENABLE VALIDATE);