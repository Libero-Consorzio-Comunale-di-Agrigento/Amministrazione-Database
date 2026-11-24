--liquibase formatted sql

--changeset mturra:2019032811338

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_ind_columns where table_name='LIVELLI_SICUREZZA' and column_name = 'LIVELLO';

CREATE UNIQUE INDEX LISI_PK ON LIVELLI_SICUREZZA(LIVELLO);
