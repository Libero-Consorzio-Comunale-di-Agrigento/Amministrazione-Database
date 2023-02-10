--liquibase formatted sql

--changeset mturra:201901301231_50


create table KEY_CONSTRAINT_COLUMN
(
  nome        VARCHAR2(30) not null,
  sequenza    NUMBER(3) not null,
  colonna     VARCHAR2(30) not null,
  rif_colonna VARCHAR2(30)
)
;
create unique index KCCO_PK on KEY_CONSTRAINT_COLUMN (NOME, COLONNA);

