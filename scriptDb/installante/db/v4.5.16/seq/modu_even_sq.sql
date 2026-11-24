--liquibase formatted sql

--changeset mturra:201904021237
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_sequences s where s.SEQUENCE_NAME='MODU_EVEN_SQ'

CREATE SEQUENCE MODU_EVEN_SQ
nocache;
--rollback drop sequence MODU_EVEN_SQ;