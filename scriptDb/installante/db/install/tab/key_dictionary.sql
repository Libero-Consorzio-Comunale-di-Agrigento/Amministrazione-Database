--liquibase formatted sql

--changeset mturra:201901301231_53


create table KEY_DICTIONARY
(
  tabella VARCHAR2(30) not null,
  colonna VARCHAR2(30) not null,
  pk      VARCHAR2(240) not null,
  lingua  VARCHAR2(1) not null,
  testo   VARCHAR2(2000)
)
;
alter table KEY_DICTIONARY
  add constraint KEDI_PK primary key (TABELLA, COLONNA, PK, LINGUA);

