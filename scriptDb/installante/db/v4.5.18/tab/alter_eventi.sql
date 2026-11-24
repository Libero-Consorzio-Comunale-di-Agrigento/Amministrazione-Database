--liquibase formatted sql

--changeset snegroni:alter_eventi

--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 select count(1) from user_tab_columns where table_name = 'EVENTI' and column_name = 'SESSION_ID'


alter table EVENTI add (SESSION_ID NUMBER);