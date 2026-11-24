--liquibase formatted sql

--changeset mturra:201901301231_90


create table UTENTI_GRUPPO
(
  utente VARCHAR2(8) not null,
  gruppo VARCHAR2(8) not null
)
;
comment on table UTENTI_GRUPPO
  is 'UTGR - Utenti del Gruppo';
create index UTGR_GRUP_FK on UTENTI_GRUPPO (GRUPPO);
alter table UTENTI_GRUPPO
  add constraint UTGR_PK primary key (UTENTE, GRUPPO);

