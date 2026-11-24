--liquibase formatted sql

--changeset snegroni:201912101657

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from user_ind_columns where table_name='UTENTI' and column_name = 'GRUPPO_LAVORO' and column_position = 1;

CREATE INDEX UTENTI_GRLA_IK ON UTENTI (GRUPPO_LAVORO);
