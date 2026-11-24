--liquibase formatted sql

--changeset mturra:201901301231_76


create table SLAVES
(
  db_link     VARCHAR2(200) not null,
  link_oracle VARCHAR2(2000) not null,
  stato       VARCHAR2(1) default 'A' not null
)
;
alter table SLAVES
  add constraint SLAV_PK primary key (DB_LINK);
alter table SLAVES
  add constraint SLAV_STATO_CC
  check (stato in ('A', 'D'));

