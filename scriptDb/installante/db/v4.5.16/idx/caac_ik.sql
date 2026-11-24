--liquibase formatted sql

--changeset snegroni:201912101656

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from user_ind_columns where table_name='CARATTERISTICHE_ACCESSO' and column_name in('UTENTE', 'MODULO', 'ISTANZA') and index_name = 'CAAC_IK';

CREATE INDEX CAAC_IK ON CARATTERISTICHE_ACCESSO (UTENTE, MODULO, ISTANZA);
