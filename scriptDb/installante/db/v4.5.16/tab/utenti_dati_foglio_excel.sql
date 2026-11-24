--liquibase formatted sql

--changeset snegroni:201904021302

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(1) from user_tables s where s.TABLE_NAME='UTENTI_DATI_FOGLIO_EXCEL'


CREATE TABLE UTENTI_DATI_FOGLIO_EXCEL
(
  UTENTE                         VARCHAR2(8) NOT NULL,
  NOMINATIVO                     VARCHAR2(40) NOT NULL,
  IS_UTENZA_TECNICA_ADS          VARCHAR2(2),
  SOGGETTO_NUMERO                NUMBER,
  SOGGETTO_NOME                  VARCHAR2(240),
  SOGGETTO_CODICE_FISCALE        VARCHAR2(16),
  IS_IN_STRUTTURA_ORGANIZZATIVA  VARCHAR2(2),
  ULTIMO_TENTATIVO               DATE,
  DIRITTI                        VARCHAR2(4000),
  NUOVE_INFO                     VARCHAR2(1),
  DISABILITARE                   VARCHAR2(2),
  NUOVO_NOMINATIVO_UTENTE        VARCHAR2(30),
  NUOVO_NI_ABBINARE              NUMBER,
  NUOVO_COGNOME                  VARCHAR2(30),
  NUOVO_NOME                     VARCHAR2(30),
  NUOVO_CODICE_FISCALE           VARCHAR2(16),
  UTENZA_LDAP                    VARCHAR2(2)
);


--rollback drop table UTENTI_DATI_FOGLIO_EXCEL;