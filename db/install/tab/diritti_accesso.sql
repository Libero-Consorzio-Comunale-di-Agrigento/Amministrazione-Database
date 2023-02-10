--liquibase formatted sql

--changeset mturra:201901301231_39


create table DIRITTI_ACCESSO
(
  utente         VARCHAR2(8) not null,
  modulo         VARCHAR2(10) not null,
  istanza        VARCHAR2(10) not null,
  ruolo          VARCHAR2(8) not null,
  sequenza       NUMBER(4),
  ultimo_accesso DATE,
  numero_accessi NUMBER,
  gruppo         VARCHAR2(8),
  note           VARCHAR2(2000)
)
;
comment on table DIRITTI_ACCESSO
  is 'DIAC - Tabella dei diritti di accesso';
alter table DIRITTI_ACCESSO
  add constraint DIAC_PK primary key (UTENTE, MODULO, ISTANZA);

