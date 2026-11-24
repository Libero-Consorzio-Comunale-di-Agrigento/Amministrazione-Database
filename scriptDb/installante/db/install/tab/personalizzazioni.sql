--liquibase formatted sql

--changeset mturra:201901301231_65


create table PERSONALIZZAZIONI
(
  modulo            VARCHAR2(10) not null,
  ente              VARCHAR2(4) not null,
  window            VARCHAR2(60) not null,
  personalizzazione VARCHAR2(60) not null
)
;
alter table PERSONALIZZAZIONI
  add constraint PERS_PK primary key (MODULO, ENTE, WINDOW);

