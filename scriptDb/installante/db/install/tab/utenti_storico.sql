--liquibase formatted sql

--changeset snegroni:201905021509

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_tables where table_name='UTENTI_STORICO';
CREATE TABLE UTENTI_STORICO
(
  ID_EVENTO             NUMBER                  NOT NULL,
  UTENTE                VARCHAR2(8)        NOT NULL,
  ID_UTENTE             NUMBER(10)              NOT NULL,
  NOMINATIVO            VARCHAR2(40)       NOT NULL,
  PASSWORD              VARCHAR2(40),
  DATA_PASSWORD         DATE,
  RINNOVO_PASSWORD      VARCHAR2(2),
  PWD_DA_MODIFICARE     VARCHAR2(2)        DEFAULT 'NO',
  TIPO_UTENTE           VARCHAR2(1)        DEFAULT 'U'                   NOT NULL,
  STATO                 VARCHAR2(1)        DEFAULT 'U'                   NOT NULL,
  LINGUA                VARCHAR2(1)        DEFAULT '*'                   NOT NULL,
  GRUPPO_LAVORO         VARCHAR2(8)        DEFAULT 'def',
  IMPORTANZA            NUMBER(2),
  NOTE                  VARCHAR2(2000),
  DATA_INSERIMENTO      DATE                    DEFAULT SYSDATE,
  UTENTE_AGGIORNAMENTO  VARCHAR2(8),
  DATA_AGGIORNAMENTO    DATE,
  DATA                  DATE                    NOT NULL,
  OPERAZIONE            VARCHAR2(2),
  BI_RIFERIMENTO        NUMBER,
  UTENTE_AGG            VARCHAR2(8),
  USER_ORACLE           VARCHAR2(30),
  INFO                  VARCHAR2(2000),
  PROGRAMMA             VARCHAR2(50)
);

CREATE INDEX UTES_IK ON UTENTI_STORICO
(ID_UTENTE)
LOGGING;

CREATE INDEX UTES_IK2 ON UTENTI_STORICO
(UTENTE)
LOGGING;

CREATE UNIQUE INDEX UTES_PK ON UTENTI_STORICO
(ID_EVENTO)
LOGGING;

ALTER TABLE UTENTI_STORICO ADD (
  CONSTRAINT UTES_OPERAZIONE_CK
  CHECK (operazione in ('I','D','BI','AI'))
  ENABLE VALIDATE,
  CONSTRAINT UTES_PK
  PRIMARY KEY
  (ID_EVENTO)
  USING INDEX UTES_PK
  ENABLE VALIDATE);



--rollback drop table UTENTI_STORICO;