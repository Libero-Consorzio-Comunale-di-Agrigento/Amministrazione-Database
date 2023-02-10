--liquibase formatted sql

--changeset mturra:201901301231_49


create table KEY_CONSTRAINT
(
  oggetto        VARCHAR2(30) not null,
  tipo           VARCHAR2(2) not null,
  sequenza       NUMBER(3) not null,
  nome           VARCHAR2(30) not null,
  note           VARCHAR2(240),
  procedura      LONG,
  label_success  VARCHAR2(30),
  flag_abort     VARCHAR2(1),
  label_failure  VARCHAR2(30),
  rif_oggetto    VARCHAR2(30),
  rif_descriptor VARCHAR2(240),
  cascade_update VARCHAR2(1),
  cascade_delete VARCHAR2(1)
)
;
create index KECO_IK on KEY_CONSTRAINT (TIPO, RIF_OGGETTO);
create unique index KECO_PK on KEY_CONSTRAINT (NOME);
create unique index KECO_UK on KEY_CONSTRAINT (OGGETTO, TIPO, SEQUENZA);

