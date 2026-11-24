--liquibase formatted sql

--changeset snegroni:20190612_1632
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_sequences s where s.SEQUENCE_NAME='REST_SQ'

CREATE SEQUENCE REST_SQ
NOCACHE;
--rollback drop sequence REST_SQ;
