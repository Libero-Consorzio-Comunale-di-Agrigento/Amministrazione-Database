--liquibase formatted sql

--changeset mturra:201901301231_75


create table SI4_USER_SOURCE
(
  name     VARCHAR2(100) not null,
  type     VARCHAR2(30) not null,
  text     CLOB,
  filename VARCHAR2(200)
)
;
comment on table SI4_USER_SOURCE
  is 'SUSO - Codice sorgente degli oggetti di db dello user.';
comment on column SI4_USER_SOURCE.name
  is 'Name of the object.';
comment on column SI4_USER_SOURCE.type
  is 'Type of the object.';
comment on column SI4_USER_SOURCE.text
  is 'Source text.';
comment on column SI4_USER_SOURCE.filename
  is 'Source filename.';
create unique index SI4_USER_SOURCE_FILENAME_UK on SI4_USER_SOURCE (FILENAME);
alter table SI4_USER_SOURCE
  add constraint SI4_USER_SOURCE_PK primary key (NAME, TYPE);
alter table SI4_USER_SOURCE
  add constraint SI4_USER_SOUR_TYPE_CC
  check (TYPE in ('PROCEDURE','FUNCTION','PACKAGE','PACKAGE BODY','PL/SQL BLOCK','VIEW','TABLE','INDEX','UNIQUE CONSTRAINT','PK CONSTRAINT','FK CONSTRAINT','CHECK CONSTRAINT','SEQUENCE','COMMENT TABLE','COMMENT COLUMN','TRIGGER'));

