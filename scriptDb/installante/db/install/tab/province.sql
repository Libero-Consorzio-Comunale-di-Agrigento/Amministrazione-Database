--liquibase formatted sql

--changeset mturra:201901301231_67


create table PROVINCE
(
  provincia            NUMBER(3) not null,
  denominazione        VARCHAR2(40),
  denominazione_al1    VARCHAR2(40),
  denominazione_al2    VARCHAR2(40),
  regione              NUMBER(3) not null,
  sigla                VARCHAR2(2) not null,
  utente_aggiornamento VARCHAR2(8),
  data_aggiornamento   DATE
)
;
comment on table PROVINCE
  is 'PROV - Tabella delle province italiane';
comment on column PROVINCE.provincia
  is 'Codice della provincia';
comment on column PROVINCE.denominazione
  is 'Dominazione della provincia';
comment on column PROVINCE.denominazione_al1
  is 'Dominazione della provincia';
comment on column PROVINCE.denominazione_al2
  is 'Dominazione della provincia';
comment on column PROVINCE.regione
  is 'Codice regione';
comment on column PROVINCE.sigla
  is 'Sigla automobilistica';
alter table PROVINCE
  add constraint PROV_PK primary key (PROVINCIA);

