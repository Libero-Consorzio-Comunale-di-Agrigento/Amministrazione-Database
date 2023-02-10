--liquibase formatted sql

--changeset mturra:201901301231_32


create table CARATTERISTICHE_ACCESSO
(
  caac_id                  NUMBER not null,
  tipo_accesso             VARCHAR2(1) not null,
  progetto                 VARCHAR2(8),
  istanza                  VARCHAR2(10),
  modulo                   VARCHAR2(10),
  utente                   VARCHAR2(8),
  accesso                  VARCHAR2(1),
  accesso_se               VARCHAR2(2),
  traccia                  VARCHAR2(1),
  giorni_traccia           NUMBER(3),
  tentativi_password       NUMBER(2),
  validita_password        NUMBER(3),
  single_sign_on           VARCHAR2(2) default 'SI',
  sleep                    NUMBER,
  ldap                     VARCHAR2(2) default 'NO',
  min_lunghezza_pwd        NUMBER(2) default 0,
  mod_pwd_primo_accesso    VARCHAR2(2) default 'NO',
  archiviazione_traccia    VARCHAR2(2) default 'NO' not null,
  dislocazione_traccia     VARCHAR2(100),
  ammessi_car_speciali_pwd VARCHAR2(2) default 'SI' not null,
  numeri_obb_pwd           VARCHAR2(2) default 'NO' not null
)
;
comment on table CARATTERISTICHE_ACCESSO
  is 'CAAC - Tabella di registrazione delle caratteristiche di accesso al sistema';
create index CAAC_PROG_IK on CARATTERISTICHE_ACCESSO (PROGETTO, TIPO_ACCESSO);
create unique index CAAC_UK on CARATTERISTICHE_ACCESSO (TIPO_ACCESSO, PROGETTO, ISTANZA, MODULO, UTENTE);
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_PK primary key (CAAC_ID);
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_DIAC_FK foreign key (UTENTE, MODULO, ISTANZA)
  references DIRITTI_ACCESSO (UTENTE, MODULO, ISTANZA) on delete cascade;
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_ACCE_CC
  check (
            ACCESSO is null or (ACCESSO in ('I','U','S','L')));
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_ACCESSO_SE_CC
  check (
            ACCESSO_SE is null or (ACCESSO_SE in ('SI','NO')));
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_AMMESSI_CAR_SPECIALI_PWD
  check (AMMESSI_CAR_SPECIALI_PWD IN ('SI','NO'));
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_ARCHIVIAZIONE_TRACCIA
  check (ARCHIVIAZIONE_TRACCIA IN ('SI','NO'));
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_LDAP
  check (LDAP IN ('SI','NO'));
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_MIN_LUNGHEZZA_PWD
  check (MIN_LUNGHEZZA_PWD between 0 and 8);
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_MOD_PWD_PRIMO_ACCESSO
  check (MOD_PWD_PRIMO_ACCESSO IN ('SI','NO'));
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_NUMERI_OBB_PWD
  check (numeri_obb_pwd IN ('SI','NO'));
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_TIPO_ACCESSO_CC
  check (TIPO_ACCESSO in ('D','M','G','I','P'));
alter table CARATTERISTICHE_ACCESSO
  add constraint CAAC_TRACCIA_CC
  check (TRACCIA is null or (TRACCIA in ('M','F','I')));

