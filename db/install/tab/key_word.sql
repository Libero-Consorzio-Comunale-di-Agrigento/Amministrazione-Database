--liquibase formatted sql

--changeset mturra:201901301231_56


create table KEY_WORD
(
  testo      VARCHAR2(240) not null,
  lingua     VARCHAR2(1) not null,
  traduzione VARCHAR2(2000)
)
;
comment on table KEY_WORD
  is 'KEWO - Tabella dei testi tradotti';
alter table KEY_WORD
  add constraint KEWO_PK primary key (TESTO, LINGUA);

