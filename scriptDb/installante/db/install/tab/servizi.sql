--liquibase formatted sql

--changeset mturra:201901301231_74


create table SERVIZI
(
  id_servizio         NUMBER not null,
  modulo              VARCHAR2(10) not null,
  istanza             VARCHAR2(10) not null,
  livello             VARCHAR2(8),
  abilitazione        VARCHAR2(1) default 'C' not null,
  gruppo_abilitazione VARCHAR2(8) not null,
  mail_notifiche      VARCHAR2(100) not null,
  ccr_notifiche       VARCHAR2(100),
  multiplo            VARCHAR2(1) default 'N' not null,
  tipo_notifica       VARCHAR2(10),
  recupero_password   VARCHAR2(1) default 'N' not null
)
;
create index SERV_IK on SERVIZI (GRUPPO_ABILITAZIONE);
alter table SERVIZI
  add constraint SERV_PK primary key (ID_SERVIZIO);
alter table SERVIZI
  add constraint SERV_AK unique (ISTANZA, MODULO);
alter table SERVIZI
  add constraint ISTA_SERV_FK foreign key (ISTANZA)
  references ISTANZE (ISTANZA);
alter table SERVIZI
  add constraint LISI_SERV_FK foreign key (LIVELLO)
  references LIVELLI_SICUREZZA (LIVELLO);
alter table SERVIZI
  add constraint MODU_SERV_FK foreign key (MODULO)
  references MODULI (MODULO);
alter table SERVIZI
  add constraint UTEN_SERV_FK foreign key (GRUPPO_ABILITAZIONE)
  references UTENTI (UTENTE);
alter table SERVIZI
  add constraint SERVIZI_ABILITAZIONE_CC
  check (ABILITAZIONE in ('A','C'));
alter table SERVIZI
  add constraint SERVIZI_MULTIPLO_CC
  check (MULTIPLO in ('S','N'));
alter table SERVIZI
  add constraint SERVIZI_RECUPERO_PASSWORD_CC
  check (RECUPERO_PASSWORD IN ('S','N'));
alter table SERVIZI
  add constraint SERVIZI_TIPO_NOTIFICA_CC
  check (TIPO_NOTIFICA is null or (TIPO_NOTIFICA in ('MAIL','POSTA','SMS','FAX')));

