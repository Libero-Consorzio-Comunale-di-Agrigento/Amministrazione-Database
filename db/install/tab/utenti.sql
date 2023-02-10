--liquibase formatted sql

--changeset mturra:201901301231_89


create table UTENTI
(
  utente               VARCHAR2(8) not null,
  id_utente            NUMBER(10) not null,
  nominativo           VARCHAR2(40) not null,
  password             VARCHAR2(40),
  data_password        DATE,
  rinnovo_password     VARCHAR2(2),
  pwd_da_modificare    VARCHAR2(2) default 'NO',
  ultimo_tentativo     DATE,
  numero_tentativi     NUMBER(2),
  tipo_utente          VARCHAR2(1) default 'U' not null,
  stato                VARCHAR2(1) default 'U' not null,
  lingua               VARCHAR2(1) default '*' not null,
  gruppo_lavoro        VARCHAR2(8) default 'def',
  importanza           NUMBER(2),
  note                 VARCHAR2(2000),
  data_inserimento     DATE default SYSDATE,
  utente_aggiornamento VARCHAR2(8),
  data_aggiornamento   DATE
)
;
comment on table UTENTI
  is 'UTEN - Tabella degli utenti ( importanza: 0 alta - 99 bassa)';
comment on column UTENTI.data_password
  is 'Data di modifica della password';
comment on column UTENTI.rinnovo_password
  is 'Indica se l''utente puo rinnovare la password';
comment on column UTENTI.ultimo_tentativo
  is 'Data dell''ultimo tentativo di connessione';
comment on column UTENTI.numero_tentativi
  is 'Numero di tentativi di connessione falliti dopo dopo l''ultimo valido';
comment on column UTENTI.stato
  is 'Stato dell''utente';
comment on column UTENTI.importanza
  is 'Valori: 0 alta - 99 bassa';
comment on column UTENTI.note
  is 'Annotazioni sull''utente';
comment on column UTENTI.utente_aggiornamento
  is 'Autore dell''inserimento o dell''aggiornamento';
alter table UTENTI
  add constraint UTEN_PK primary key (UTENTE);
alter table UTENTI
  add constraint UTEN_ID_UTENTE_AK unique (ID_UTENTE);
alter table UTENTI
  add constraint UTEN_NOMINATIVO_AK unique (NOMINATIVO);
alter table UTENTI
  add constraint RUOL_UTEN_FK foreign key (GRUPPO_LAVORO)
  references RUOLI (RUOLO);
alter table UTENTI
  add constraint UTEN_PWD_DA_MODIFICARE
  check (PWD_DA_MODIFICARE IN ('SI','NO'));
alter table UTENTI
  add constraint UTEN_RINNOVO_PASSWORD_CC
  check (
               RINNOVO_PASSWORD is null or (RINNOVO_PASSWORD in ('SI','NO')));
alter table UTENTI
  add constraint UTEN_TIPO_UTENTE_CC
  check ( TIPO_UTENTE in ('G','U','O'));
alter table UTENTI
  add constraint UTENTI_STATO_CC
  check (STATO in ('U','S','R','A'));

