--liquibase formatted sql

--changeset snegroni:20190506_1633
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_sequences s where s.SEQUENCE_NAME='UTES_SQ'

CREATE SEQUENCE utes_sq NOCACHE;

--rollback drop sequence utes_sq;
