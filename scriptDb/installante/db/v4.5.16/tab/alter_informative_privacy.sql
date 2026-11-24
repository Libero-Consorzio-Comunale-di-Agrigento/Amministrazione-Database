--liquibase formatted sql

--changeset snegroni:2019004021434

--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 select count(1) from user_tab_columns s where s.TABLE_NAME='INFORMATIVE_PRIVACY' and column_name = 'URL_NOTE_LEGALI'


ALTER TABLE INFORMATIVE_PRIVACY drop 
  CONSTRAINT INFORMATIVE_PRIVACY_UK;

ALTER TABLE INFORMATIVE_PRIVACY ADD (
  CONSTRAINT INFORMATIVE_PRIVACY_UK
  UNIQUE (ENTE, MODULO, CHIAVE_PRIVACY));

alter table informative_privacy add (url_note_legali varchar2(2000));