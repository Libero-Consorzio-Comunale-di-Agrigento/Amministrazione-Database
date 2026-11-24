--liquibase formatted sql

--changeset mturra:2019032811338

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_ind_columns where table_name='RICHIESTE_ABILITAZIONE' and column_name = 'ID_RICHIESTA';

CREATE UNIQUE INDEX RIAB_PK ON RICHIESTE_ABILITAZIONE(ID_RICHIESTA);
