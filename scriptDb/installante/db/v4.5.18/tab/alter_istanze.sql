--liquibase formatted sql

--changeset snegroni:202003061042 runOnChange:true

--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 select count(1) from user_tab_columns where table_name = 'ISTANZE_STORICO' and column_name = 'VERSIONE' and data_length = 30


alter table istanze modify (versione varchar2(30));
alter table istanze_storico modify (versione varchar2(30));