--liquibase formatted sql

--changeset mturra:201901301231_70


create table REGISTRO
(
  chiave   VARCHAR2(512) not null,
  stringa  VARCHAR2(100) not null,
  commento VARCHAR2(2000),
  valore   VARCHAR2(2000)
)
;
comment on table REGISTRO
  is 'REGS - Voci di Registro';
alter table REGISTRO
  add constraint REGISTRO_PK primary key (CHIAVE, STRINGA);

