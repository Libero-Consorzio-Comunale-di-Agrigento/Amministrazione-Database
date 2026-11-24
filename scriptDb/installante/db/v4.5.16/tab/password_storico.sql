--liquibase formatted sql

--changeset mturra:201903281133

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_tables s where s.TABLE_NAME='PASSWORD_STORICO'



CREATE TABLE PASSWORD_STORICO
(
  ID_EVENTO             NUMBER,
  UTENTE                VARCHAR2(8),
  PASSWORD              VARCHAR2(40),
  DATA_PASSWORD         DATE,
  UTENTE_AGGIORNAMENTO  VARCHAR2(8),
  DATA_AGGIORNAMENTO    DATE,
  DATA                  DATE NOT NULL,
  OPERAZIONE            VARCHAR2(2),
  BI_RIFERIMENTO        NUMBER,
  UTENTE_AGG            VARCHAR2(8),
  USER_ORACLE           VARCHAR2(30),
  INFO                  VARCHAR2(2000),
  PROGRAMMA             VARCHAR2(50)
);
--rollback drop table PASSWORD_STORICO;