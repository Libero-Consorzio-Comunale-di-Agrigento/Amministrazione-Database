--liquibase formatted sql

--changeset mmonari:dimensiona_campo_numero_accessi

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:1 select nvl(max(1),0) from user_tab_columns where table_name='DIRITTI_ACCESSO' and column_name='NUMERO_ACCESSI' and nvl(data_precision,999) < 10;

alter table diritti_accesso modify numero_accessi number;