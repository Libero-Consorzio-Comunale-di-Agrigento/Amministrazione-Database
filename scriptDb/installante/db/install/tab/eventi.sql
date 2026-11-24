--liquibase formatted sql

--changeset mturra:201901301231_44


create table EVENTI
(
  id_evento    NUMBER not null,
  testo        VARCHAR2(2000) not null,
  annotazioni  VARCHAR2(2000),
  tipo         VARCHAR2(8) not null,
  gravita      VARCHAR2(1) default 'I',
  db_user      VARCHAR2(30) not null,
  utente       VARCHAR2(8),
  modulo       VARCHAR2(10),
  istanza      VARCHAR2(10),
  data         DATE default sysdate not null,
  notificato   NUMBER default 0 not null,
  file_locator BFILE,
  stato        VARCHAR2(1) default 'U' not null
)
;
comment on table EVENTI
  is 'Traccia degli eventi e delle  eventuali segnalazioni';
comment on column EVENTI.id_evento
  is 'Identificativo dell''evento';
comment on column EVENTI.testo
  is 'Testo';
comment on column EVENTI.annotazioni
  is 'Eventuali informazioni aggiuntive <NLS>';
comment on column EVENTI.tipo
  is 'Tipologia dell''evento. Ad esempio LOG.';
comment on column EVENTI.gravita
  is 'Gravita dell''evento';
comment on column EVENTI.db_user
  is 'DB User dell''evento';
comment on column EVENTI.utente
  is 'Eventuale utente di procedura dell''evento';
comment on column EVENTI.modulo
  is 'Eventuale modulo di procedura dell''evento';
comment on column EVENTI.istanza
  is 'Eventuale  istanza di ambiente dell''evento';
comment on column EVENTI.data
  is 'Data di registrazione';
comment on column EVENTI.notificato
  is 'Indicatore di avvenuta notifica dell''evento';
create index EVEN_DATA_IK on EVENTI (DATA);
create index EVEN_DIAC_IK on EVENTI (UTENTE, MODULO, ISTANZA);
create index EVEN_TIPO_IK on EVENTI (TIPO);
alter table EVENTI
  add constraint EVENTI_PK primary key (ID_EVENTO);
alter table EVENTI
  add constraint EVENTI_GRAVITA_CC
  check (GRAVITA is null or ( GRAVITA in ('I','S','E') ));
alter table EVENTI
  add constraint EVENTI_NOTIFICATO_CC
  check (NOTIFICATO in (1,0));
alter table EVENTI
  add constraint EVENTI_STATO_CC
  check (stato in ('U', 'D', 'A'));
alter table EVENTI
  add constraint EVENTI_TIPO_CC
  check (TIPO LIKE 'LOG%' OR TIPO IN ('ERROR','ARCLOG','APPTRC','SIALOG'));

