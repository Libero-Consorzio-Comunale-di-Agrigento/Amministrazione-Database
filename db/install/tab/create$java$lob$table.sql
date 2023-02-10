--liquibase formatted sql

--changeset mturra:201901301231_35


create table CREATE$JAVA$LOB$TABLE
(
  name     VARCHAR2(700),
  lob      BLOB,
  loadtime DATE
)
;
alter table CREATE$JAVA$LOB$TABLE
  add unique (NAME);

