--liquibase formatted sql

--changeset snegroni:20190706_1334

--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 select count(1) from user_tab_columns s where s.TABLE_NAME='UTENTI_STORICO' and column_name = 'AMMINISTRATORE'

ALTER TABLE UTENTI_STORICO
 ADD (AMMINISTRATORE        VARCHAR2(1)        DEFAULT 'N',
      INFO_IDENTIFICAZIONE  VARCHAR2(2000));