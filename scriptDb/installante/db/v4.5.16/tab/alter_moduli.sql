--liquibase formatted sql

--changeset mturra:2019004011029_2

--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 select count(1) from user_tab_columns s where s.TABLE_NAME='MODULI' and column_name = 'AMMINISTRATORE'


ALTER TABLE MODULI
 ADD (AMMINISTRATORE  VARCHAR2(1) DEFAULT 'N');

ALTER TABLE MODULI
 ADD CONSTRAINT MODU_AMM_CK
  CHECK (amministratore in ('S','N'));