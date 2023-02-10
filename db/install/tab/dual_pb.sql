--liquibase formatted sql

--changeset mturra:201901301231_42


create table DUAL_PB
(
  pb       VARCHAR2(20) not null,
  numero   NUMBER,
  stringa  VARCHAR2(60),
  data     DATE,
  stringa2 VARCHAR2(2000),
  decimale NUMBER(11,11)
)
;
comment on table DUAL_PB
  is 'DUPB - Tabella di appoggio';

