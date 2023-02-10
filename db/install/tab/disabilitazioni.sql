--liquibase formatted sql

--changeset mturra:201901301231_41


create table DISABILITAZIONI
(
  ruolo  VARCHAR2(8) not null,
  modulo VARCHAR2(10) not null,
  window VARCHAR2(60) not null
)
;
comment on table DISABILITAZIONI
  is 'DISA - Tabella delle disabilitazioni';
alter table DISABILITAZIONI
  add constraint DISA_PK primary key (RUOLO, MODULO, WINDOW);

