--liquibase formatted sql

--changeset snegroni:201911191609

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:1 select count(1) from user_indexes where index_name='EVEN_DIAC_IK';

DROP INDEX EVEN_DIAC_IK;
