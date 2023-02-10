--liquibase formatted sql

--changeset mturra:201901301231_72


create table RICHIESTE_ABILITAZIONE
(
  id_richiesta       NUMBER not null,
  utente             VARCHAR2(8) not null,
  modulo             VARCHAR2(10) not null,
  istanza            VARCHAR2(10) not null,
  stato              VARCHAR2(1) default 'F' not null,
  data               DATE,
  note               VARCHAR2(2000),
  tipo_notifica      VARCHAR2(10),
  indirizzo_notifica VARCHAR2(2000),
  note_notifica      VARCHAR2(2000),
  notificata         VARCHAR2(1) default 'N' not null
)
;
alter table RICHIESTE_ABILITAZIONE
  add constraint RIAB_PK primary key (ID_RICHIESTA);
alter table RICHIESTE_ABILITAZIONE
  add constraint RICHIESTE_ABI_NOTIFICATA_CC
  check (NOTIFICATA in ('N','S','F'));
alter table RICHIESTE_ABILITAZIONE
  add constraint RICHIESTE_ABI_STATO_CC
  check (STATO in ('A','R','C','F'));
alter table RICHIESTE_ABILITAZIONE
  add constraint RICHIESTE_ABI_TIPO_NOTIFICA_CC
  check (TIPO_NOTIFICA is null or (TIPO_NOTIFICA in ('MAIL','POSTA','SMS','FAX')));

