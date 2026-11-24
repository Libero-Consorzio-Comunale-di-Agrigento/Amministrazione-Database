--liquibase formatted sql

--changeset snegroni:201905021630

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_tables where table_name='REGISTRO_STORICO';

CREATE TABLE REGISTRO_STORICO
(
  ID_EVENTO       NUMBER                        NOT NULL,
  CHIAVE          VARCHAR2(512)            NOT NULL,
  STRINGA         VARCHAR2(100)            NOT NULL,
  COMMENTO        VARCHAR2(2000),
  VALORE          VARCHAR2(2000),
  DATA            DATE                          NOT NULL,
  OPERAZIONE      VARCHAR2(2),
  BI_RIFERIMENTO  NUMBER,
  UTENTE_AGG      VARCHAR2(8),
  USER_ORACLE     VARCHAR2(30),
  INFO            VARCHAR2(2000),
  PROGRAMMA       VARCHAR2(50)
);

--rollback drop table REGISTRO_STORICO;