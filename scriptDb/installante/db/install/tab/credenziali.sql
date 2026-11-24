--liquibase formatted sql

--changeset mturra:201901301231_36


create table CREDENZIALI
(
  id_credenziale       NUMBER not null,
  tipo_credenziale     VARCHAR2(8) not null,
  chiave_credenziale   VARCHAR2(1000) not null,
  emittente            VARCHAR2(1000),
  stato                VARCHAR2(1) default 'U' not null,
  utente_aggiornamento VARCHAR2(8),
  data_aggiornamento   DATE,
  credenziale          BLOB
)
;
comment on table CREDENZIALI
  is 'CRED - Tabella delle credenziali';
alter table CREDENZIALI
  add constraint CRED_PK primary key (ID_CREDENZIALE);
alter table CREDENZIALI
  add constraint CRED_CHIAVE_AK unique (CHIAVE_CREDENZIALE);
alter table CREDENZIALI
  add constraint CRED_STATO_CC
  check (STATO in ('U','S','R'));

