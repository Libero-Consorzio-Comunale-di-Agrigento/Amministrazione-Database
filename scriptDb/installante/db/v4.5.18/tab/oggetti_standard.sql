--liquibase formatted sql

--changeset snegroni:20200201200


--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_tables where table_name='OGGETTI_STANDARD';

CREATE TABLE OGGETTI_STANDARD
(
  OBJECT_NAME  VARCHAR2(128),
  OBJECT_TYPE  VARCHAR2(23)
);

COMMENT ON TABLE OGGETTI_STANDARD IS 'OGST - Tabella con gli oggetti standard distribuiti da ad4 ';