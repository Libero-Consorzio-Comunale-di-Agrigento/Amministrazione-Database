--liquibase formatted sql

--changeset mturra:201901301231_40


create table DIRITTI_ACCESSO_STORICO
(
  id_evento            NUMBER not null,
  utente               VARCHAR2(8) not null,
  modulo               VARCHAR2(10) not null,
  istanza              VARCHAR2(10) not null,
  ruolo                VARCHAR2(8) not null,
  sequenza             NUMBER(4),
  ultimo_accesso       DATE,
  numero_accessi       NUMBER,
  gruppo               VARCHAR2(8),
  note                 VARCHAR2(2000),
  data                 DATE not null,
  operazione           VARCHAR2(2),
  bi_riferimento       NUMBER,
  utente_aggiornamento VARCHAR2(8),
  user_oracle          VARCHAR2(30),
  info                 VARCHAR2(2000),
  programma            VARCHAR2(50)
)
;
comment on table DIRITTI_ACCESSO_STORICO
  is 'DIAS - DIRITTI ACCESSO STORICO';
comment on column DIRITTI_ACCESSO_STORICO.operazione
  is 'I=Insert, D=Delete, BI=Before Image, AI=After Image';
create index DIAS_IK on DIRITTI_ACCESSO_STORICO (UTENTE, MODULO, ISTANZA, RUOLO, DATA, OPERAZIONE);
alter table DIRITTI_ACCESSO_STORICO
  add constraint DIAS_PK primary key (ID_EVENTO);
alter table DIRITTI_ACCESSO_STORICO
  add constraint DIAS_OPERAZIONE_CK
  check (operazione in ('I','D','BI','AI'));

