--liquibase formatted sql

--changeset mturra:2019032811338

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_ind_columns where table_name='TIPI_CREDENZIALE' and column_name = 'TIPO_CREDENZIALE';

CREATE UNIQUE INDEX TICR_PK ON TIPI_CREDENZIALE(TIPO_CREDENZIALE);
