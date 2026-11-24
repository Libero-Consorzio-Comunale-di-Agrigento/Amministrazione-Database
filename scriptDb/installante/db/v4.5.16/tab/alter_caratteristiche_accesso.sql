--liquibase formatted sql

--changeset snegroni:201904021321

--comment aggiunta colonna a tabella CARATTERISTICHE_ACCESSO

--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 select count(1) from user_tab_columns s where s.TABLE_NAME='CARATTERISTICHE_ACCESSO' and column_name = 'GG_PRIMA_RIUTILIZZO_PWD'

ALTER TABLE CARATTERISTICHE_ACCESSO
 ADD (GG_PRIMA_RIUTILIZZO_PWD  NUMBER(3));

--rollback ALTER TABLE CARATTERISTICHE_ACCESSO drop (GG_PRIMA_RIUTILIZZO_PWD);