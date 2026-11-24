--liquibase formatted sql

--changeset mturra:201904021231
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_sequences s where s.SEQUENCE_NAME='UTPS_SQ'

CREATE SEQUENCE UTPS_SQ
nocache;
--rollback drop sequence UTPS_SQ;