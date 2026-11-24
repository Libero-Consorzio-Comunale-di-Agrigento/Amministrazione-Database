--liquibase formatted sql

--changeset mturra:201901301231_78


create table SOGGETTI
(
  soggetto             NUMBER(8) not null,
  nome                 VARCHAR2(240),
  sesso                VARCHAR2(1),
  data_nascita         DATE,
  provincia_nas        NUMBER(3),
  comune_nas           NUMBER(3),
  codice_fiscale       VARCHAR2(16),
  indirizzo            VARCHAR2(40),
  cap                  VARCHAR2(5),
  comune               NUMBER(3),
  provincia            NUMBER(3),
  telefono             VARCHAR2(14),
  fax                  VARCHAR2(14),
  indirizzo_web        VARCHAR2(2000),
  note                 VARCHAR2(240),
  utente_aggiornamento VARCHAR2(8),
  data_aggiornamento   DATE,
  competenza           VARCHAR2(8),
  competenza_esclusiva VARCHAR2(1)
)
;
alter table SOGGETTI
  add constraint SOGG_PK primary key (SOGGETTO);

