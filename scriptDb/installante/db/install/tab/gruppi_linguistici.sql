--liquibase formatted sql

--changeset mturra:201901301231_46


create table GRUPPI_LINGUISTICI
(
  lingua      VARCHAR2(1) not null,
  lingua_al   VARCHAR2(1) not null,
  sequenza    NUMBER(2) not null,
  descrizione VARCHAR2(40)
)
;
comment on table GRUPPI_LINGUISTICI
  is 'GRLI - Tabella dei gruppi linguistici';
alter table GRUPPI_LINGUISTICI
  add constraint GRLI_PK primary key (LINGUA, LINGUA_AL);
alter table GRUPPI_LINGUISTICI
  add constraint LING_GRLI_FK foreign key (LINGUA)
  references LINGUE (LINGUA);

