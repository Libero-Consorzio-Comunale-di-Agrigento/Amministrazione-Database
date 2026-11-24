--liquibase formatted sql

--changeset mturra:201901301231_63


create table PAESI_TERRITORI_AG_ENTRATE
(
  codice_paese_territorio NUMBER(3) not null,
  denominazione           VARCHAR2(40) not null,
  codifica_istat          NUMBER(3),
  black_list              VARCHAR2(1) default 'N' not null
)
;
comment on table PAESI_TERRITORI_AG_ENTRATE
  is 'TABELLA PAESI_TERRITORI_AG_ENTRATE - CODIFICA PAESI e TERRITORI - AGENZIA DELLE ENTRATE';
comment on column PAESI_TERRITORI_AG_ENTRATE.codice_paese_territorio
  is 'Codice Paese Territorio Estero come da Codifica Agenzia delle ENTRATE';
comment on column PAESI_TERRITORI_AG_ENTRATE.denominazione
  is 'Denominazione come da Codifica Agenzia delle ENTRATE';
comment on column PAESI_TERRITORI_AG_ENTRATE.codifica_istat
  is 'Codifica ISTAT indica il legame con: stati_territori.stato_territorio ';
comment on column PAESI_TERRITORI_AG_ENTRATE.black_list
  is 'Indica se il paese territorio e attualmente presente nella black list (valori possibili ''S'',''N'')';
alter table PAESI_TERRITORI_AG_ENTRATE
  add constraint PAESI_TERRITORI_AG_ENTRATE_PK primary key (DENOMINAZIONE);

CREATE UNIQUE INDEX CODIFICA_ISTAT_UK ON PAESI_TERRITORI_AG_ENTRATE
(CODIFICA_ISTAT);
