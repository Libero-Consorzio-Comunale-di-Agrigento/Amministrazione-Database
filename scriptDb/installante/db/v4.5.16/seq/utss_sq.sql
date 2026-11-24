--liquibase formatted sql

--changeset snegroni:20190612_1633
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_sequences s where s.SEQUENCE_NAME='UTSS_SQ'

CREATE SEQUENCE UTSS_SQ
NOCACHE;
--rollback drop sequence UTSS_SQ;
