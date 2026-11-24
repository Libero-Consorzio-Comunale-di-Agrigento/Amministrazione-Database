--liquibase formatted sql

--changeset mturra:201901301231_18


create table AMV_BLOB
(
  id_blob   NUMBER(10) not null,
  nome      VARCHAR2(100),
  tipo      VARCHAR2(10),
  blob_file BLOB
)
;
alter table AMV_BLOB
  add constraint AMV_BLOB_PK primary key (ID_BLOB);

