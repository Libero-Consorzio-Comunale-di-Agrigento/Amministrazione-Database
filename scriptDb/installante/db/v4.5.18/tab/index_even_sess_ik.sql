--liquibase formatted sql

--changeset snegroni:index_even_sess_ik

--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 select count(1) from user_indexes where index_name='EVEN_SESS_IK';


CREATE INDEX EVEN_SESS_IK ON EVENTI (SESSION_ID);