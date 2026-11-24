--liquibase formatted sql

--changeset mturra:201901301231_47


create table INFORMATIVE_PRIVACY
(
  id                    NUMBER not null,
  ente                  VARCHAR2(4),
  modulo                VARCHAR2(10),
  data_informativa      DATE,
  url_documento_privacy VARCHAR2(2000),
  note                  VARCHAR2(2000),
  chiave_privacy        VARCHAR2(60)
)
;
comment on table INFORMATIVE_PRIVACY
  is 'INPY - Informative Privacy';
alter table INFORMATIVE_PRIVACY
  add constraint INFORMATIVE_PRIVACY_PK primary key (ID);
alter table INFORMATIVE_PRIVACY
  add constraint INFORMATIVE_PRIVACY_UK unique (ENTE, MODULO, CHIAVE_PRIVACY);

