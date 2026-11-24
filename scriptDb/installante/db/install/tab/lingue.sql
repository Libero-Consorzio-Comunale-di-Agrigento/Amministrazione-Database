--liquibase formatted sql

--changeset mturra:201901301231_59


create table LINGUE
(
  lingua      VARCHAR2(1) not null,
  descrizione VARCHAR2(40)
)
;
alter table LINGUE
  add constraint LING_PK primary key (LINGUA);

