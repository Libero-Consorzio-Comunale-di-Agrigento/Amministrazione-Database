--liquibase formatted sql

--changeset mturra:201901301231_13


create table ALLEGATI
(
  id_allegato NUMBER not null,
  nome        VARCHAR2(2000),
  note        VARCHAR2(2000),
  allegato    BLOB
)
;
comment on table ALLEGATI
  is 'ALLE - Tabella degli allegati';
alter table ALLEGATI
  add constraint ALLE_PK primary key (ID_ALLEGATO);

