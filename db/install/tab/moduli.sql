--liquibase formatted sql

--changeset mturra:201901301231_61


create table MODULI
(
  modulo          VARCHAR2(10) not null,
  descrizione     VARCHAR2(40) not null,
  descrizione_al1 VARCHAR2(40),
  descrizione_al2 VARCHAR2(40),
  progetto        VARCHAR2(8) not null,
  note            VARCHAR2(2000)
)
;
comment on table MODULI
  is 'MODU - Tabella dei moduli applicativi';
comment on column MODULI.descrizione
  is 'Descrizione MODULO <NLS>';
alter table MODULI
  add constraint MODU_PK primary key (MODULO);

