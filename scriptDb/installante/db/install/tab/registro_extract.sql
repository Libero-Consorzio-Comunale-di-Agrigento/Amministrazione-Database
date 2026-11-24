--liquibase formatted sql

--changeset mturra:201901301231_71


create table REGISTRO_EXTRACT
(
  chiave   VARCHAR2(512) not null,
  stringa  VARCHAR2(100) not null,
  commento VARCHAR2(2000),
  valore   VARCHAR2(2000)
)
;

