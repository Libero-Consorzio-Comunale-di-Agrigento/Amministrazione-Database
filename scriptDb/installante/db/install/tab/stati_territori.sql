--liquibase formatted sql

--changeset mturra:201901301231_86


create table STATI_TERRITORI
(
  stato_territorio      NUMBER(3) not null,
  denominazione         VARCHAR2(40),
  denominazione_al1     VARCHAR2(40),
  denominazione_al2     VARCHAR2(40),
  sigla                 VARCHAR2(5),
  desc_cittadinanza     VARCHAR2(40),
  desc_cittadinanza_al1 VARCHAR2(40),
  desc_cittadinanza_al2 VARCHAR2(40),
  raggruppamento        NUMBER(2),
  stato_appartenenza    NUMBER(3),
  utente_aggiornamento  VARCHAR2(8),
  data_aggiornamento    DATE,
  sigla_iso3166_alpha2  VARCHAR2(2)
)
;
comment on table STATI_TERRITORI
  is 'STTE - Tabella degli stati e dei territori';
comment on column STATI_TERRITORI.stato_territorio
  is 'Codice dello stato/territorio estero';
comment on column STATI_TERRITORI.denominazione
  is 'Denominazione dello stato/territorio estero';
comment on column STATI_TERRITORI.denominazione_al1
  is 'Denominazione dello stato/territorio estero';
comment on column STATI_TERRITORI.denominazione_al2
  is 'Denominazione dello stato/territorio estero';
comment on column STATI_TERRITORI.sigla
  is 'Sigla automobilistica';
comment on column STATI_TERRITORI.desc_cittadinanza
  is 'Descrizione della cittadinanza';
comment on column STATI_TERRITORI.desc_cittadinanza_al1
  is 'Descrizione della cittadinanza';
comment on column STATI_TERRITORI.desc_cittadinanza_al2
  is 'Descrizione della cittadinanza';
comment on column STATI_TERRITORI.raggruppamento
  is 'Codice del raggruppamento';
comment on column STATI_TERRITORI.stato_appartenenza
  is 'Codice dello stato/territorio estero';
create index STTE_RAST_FK on STATI_TERRITORI (RAGGRUPPAMENTO);
alter table STATI_TERRITORI
  add constraint STTE_PK primary key (STATO_TERRITORIO);

