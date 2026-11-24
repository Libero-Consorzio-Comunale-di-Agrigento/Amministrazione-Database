--liquibase formatted sql

--changeset snegroni:202003061108
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_sequences s where s.SEQUENCE_NAME='ISST_SQ'

CREATE SEQUENCE ISST_SQ
NOCACHE;
