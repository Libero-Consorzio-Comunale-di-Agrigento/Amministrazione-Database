--liquibase formatted sql

--changeset mturra:201901301231_34


create table CONSOLATI
(
  consolato            NUMBER(5) not null,
  tipo_consolato       NUMBER(2) not null,
  stato                NUMBER(3) not null,
  indirizzo_1          VARCHAR2(50),
  indirizzo_2          VARCHAR2(50),
  indirizzo_3          VARCHAR2(50),
  sede                 VARCHAR2(40),
  utente_aggiornamento VARCHAR2(8),
  data_aggiornamento   DATE
)
;
comment on table CONSOLATI
  is 'CONS - Tabella dei consolati';
comment on column CONSOLATI.consolato
  is 'Codice del consolato';
comment on column CONSOLATI.tipo_consolato
  is 'Codice del tipo consolato';
comment on column CONSOLATI.stato
  is 'Codice dello stato/territorio estero';
comment on column CONSOLATI.indirizzo_1
  is 'Primi 50 caratteri dell"indirizzo';
comment on column CONSOLATI.indirizzo_2
  is 'Secondi 50 caratteri dell"indirizzo';
comment on column CONSOLATI.indirizzo_3
  is 'Terzi 50 caratteri dell"indirizzo';
comment on column CONSOLATI.sede
  is 'Denominazione della citta" dove risiede l"ambasciata/il consolato';
create index CONS_STTE_FK on CONSOLATI (STATO);
alter table CONSOLATI
  add constraint CONS_PK primary key (CONSOLATO, TIPO_CONSOLATO, STATO);

