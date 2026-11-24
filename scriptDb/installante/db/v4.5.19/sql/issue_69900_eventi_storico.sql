--liquibase formatted sql

--changeset mmonari:issue_69900_eventi_storico

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select nvl(max(1),0) from obj where object_name='EVENTI_STORICO' and object_type='TABLE';

-- Create table
create table EVENTI_STORICO
(
  id_evento    NUMBER not null,
  testo        VARCHAR2(2000) not null,
  annotazioni  VARCHAR2(2000),
  tipo         VARCHAR2(8) not null,
  gravita      VARCHAR2(1),
  db_user      VARCHAR2(30) not null,
  utente       VARCHAR2(8),
  modulo       VARCHAR2(10),
  istanza      VARCHAR2(10),
  data         DATE not null,
  notificato   NUMBER not null,
  file_locator BFILE,
  stato        VARCHAR2(1) not null,
  session_id   NUMBER
)
;
-- Add comments to the table
comment on table EVENTI_STORICO
  is 'Backup delle righe cancellate da EVENTI';
-- Create/Recreate indexes
create index EVST_DATA_IK on EVENTI_STORICO (DATA)
;
create index EVST_DIAC_UMID_IK on EVENTI_STORICO (UTENTE, MODULO, ISTANZA, DATA)
;
create index EVST_TIPO_IK on EVENTI_STORICO (TIPO)
;
-- Create/Recreate primary, unique and foreign key constraints
alter table EVENTI_STORICO
  add constraint EVST_PK primary key (ID_EVENTO)
  using index
;
