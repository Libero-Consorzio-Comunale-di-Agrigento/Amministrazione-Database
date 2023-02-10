--liquibase formatted sql

--changeset mturra:201901301231_73


create table RUOLI
(
  ruolo           VARCHAR2(8) not null,
  descrizione     VARCHAR2(40) not null,
  progetto        VARCHAR2(8),
  modulo          VARCHAR2(10),
  profilo         NUMBER(2),
  descrizione_al1 VARCHAR2(40),
  descrizione_al2 VARCHAR2(40),
  stato           VARCHAR2(1) default 'U' not null,
  gruppo_lavoro   VARCHAR2(1) default 'N' not null,
  gruppo_so       VARCHAR2(1) default 'N' not null,
  incarico        VARCHAR2(1) default 'N' not null,
  responsabilita  VARCHAR2(1) default 'N' not null
)
;
comment on table RUOLI
  is 'RUOL - Ruoli di abilitazione Utenti';
comment on column RUOLI.descrizione
  is 'Descrizione RUOLO <NLS>';
comment on column RUOLI.profilo
  is 'Profilo numerico assegnato al Ruolo';
comment on column RUOLI.incarico
  is 'Se incarico = S/N';
comment on column RUOLI.responsabilita
  is 'Se incarico con responsabilita = S/N';
create unique index RUOL_DESCRIZIONE_AK on RUOLI (DESCRIZIONE);
alter table RUOLI
  add constraint RUOL_PK primary key (RUOLO);
alter table RUOLI
  add constraint RUOLI_GRUPPO_LAVORO_CC
  check (GRUPPO_LAVORO IN ('S', 'N'));
alter table RUOLI
  add constraint RUOLI_GRUPPO_SO_CC
  check (GRUPPO_SO IN ('S', 'N'));
alter table RUOLI
  add constraint RUOLI_INCARICO_CC
  check (INCARICO IN ('S', 'N'));
alter table RUOLI
  add constraint RUOLI_RESPONSABILITA_CC
  check (RESPONSABILITA IN ('S', 'N'));
alter table RUOLI
  add constraint RUOLI_STATO_CC
  check (stato IN ('U', 'R'));

