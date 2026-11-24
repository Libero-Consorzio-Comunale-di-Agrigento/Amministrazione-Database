--liquibase formatted sql

--changeset mturra:201901301231_68


create table RAGGRUPPAMENTI_STATI
(
  raggruppamento       NUMBER(2) not null,
  descrizione          VARCHAR2(40),
  descrizione_al1      VARCHAR2(40),
  descrizione_al2      VARCHAR2(40),
  utente_aggiornamento VARCHAR2(8),
  data_aggiornamento   DATE
)
;
comment on table RAGGRUPPAMENTI_STATI
  is 'RAST - Tabella dei raggruppamenti degli stati';
comment on column RAGGRUPPAMENTI_STATI.raggruppamento
  is 'Codice del raggruppamento';
comment on column RAGGRUPPAMENTI_STATI.descrizione
  is 'Descrizione';
comment on column RAGGRUPPAMENTI_STATI.descrizione_al1
  is 'Descrizione';
comment on column RAGGRUPPAMENTI_STATI.descrizione_al2
  is 'Descrizione';
alter table RAGGRUPPAMENTI_STATI
  add constraint RAST_PK primary key (RAGGRUPPAMENTO);

