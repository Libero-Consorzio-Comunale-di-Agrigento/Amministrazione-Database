--liquibase formatted sql

--changeset mturra:201901301231_69


create table REGIONI
(
  regione              NUMBER(3) not null,
  denominazione        VARCHAR2(40),
  denominazione_al1    VARCHAR2(40),
  denominazione_al2    VARCHAR2(40),
  id_regione           NUMBER(3) not null,
  utente_aggiornamento VARCHAR2(8),
  data_aggiornamento   DATE
)
;
comment on table REGIONI
  is 'REGI - Tabella delle regioni italiane';
comment on column REGIONI.regione
  is 'Codice regione';
comment on column REGIONI.denominazione
  is 'Denominazione della regione';
comment on column REGIONI.denominazione_al1
  is 'Denominazione della regione';
comment on column REGIONI.denominazione_al2
  is 'Denominazione della regione';
create index REGI_ID_REGIONE_IK on REGIONI (ID_REGIONE);
alter table REGIONI
  add constraint REGI_PK primary key (REGIONE);

