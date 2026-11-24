--liquibase formatted sql

--changeset snegroni:201911191610

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_indexes where index_name='EVEN_DIAC_UMID_IK';

CREATE INDEX EVEN_DIAC_UMID_IK ON EVENTI (UTENTE, MODULO, ISTANZA, DATA);
