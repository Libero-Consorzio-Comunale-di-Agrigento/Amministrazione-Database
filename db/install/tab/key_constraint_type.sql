--liquibase formatted sql

--changeset mturra:201901301231_52


create table KEY_CONSTRAINT_TYPE
(
  db_error    VARCHAR2(10) not null,
  tipo_errore VARCHAR2(2) not null
)
;
create unique index KCTY_PK on KEY_CONSTRAINT_TYPE (DB_ERROR);

