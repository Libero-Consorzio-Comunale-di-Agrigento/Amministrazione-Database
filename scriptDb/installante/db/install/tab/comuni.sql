--liquibase formatted sql

--changeset mturra:201901301231_33


create table COMUNI
(
  provincia_stato         NUMBER(3) not null,
  comune                  NUMBER(3) not null,
  denominazione           VARCHAR2(40),
  denominazione_al1       VARCHAR2(40),
  denominazione_al2       VARCHAR2(40),
  denominazione_breve     VARCHAR2(16),
  denominazione_breve_al1 VARCHAR2(16),
  denominazione_breve_al2 VARCHAR2(16),
  capoluogo_provincia     VARCHAR2(1),
  cap                     NUMBER(5),
  provincia_tribunale     NUMBER(3),
  comune_tribunale        NUMBER(3),
  provincia_distretto     NUMBER(3),
  comune_distretto        NUMBER(3),
  data_soppressione       DATE,
  provincia_fusione       NUMBER(3),
  comune_fusione          NUMBER(3),
  sigla_cfis              VARCHAR2(4),
  consolato               NUMBER(5),
  tipo_consolato          NUMBER(2),
  utente_aggiornamento    VARCHAR2(8),
  data_aggiornamento      DATE
)
;
comment on table COMUNI
  is 'COMU - Tabella dei comuni italiani';
comment on column COMUNI.provincia_stato
  is 'Codice provincia per i comuni italiani dello stato per gli esteri';
comment on column COMUNI.comune
  is 'Codice comune';
comment on column COMUNI.denominazione
  is 'Descrizione ufficiale del comune';
comment on column COMUNI.denominazione_al1
  is 'Descrizione ufficiale del comune';
comment on column COMUNI.denominazione_al2
  is 'Descrizione ufficiale del comune';
comment on column COMUNI.denominazione_breve
  is 'Descrizione breve del comune';
comment on column COMUNI.denominazione_breve_al1
  is 'Descrizione breve del comune';
comment on column COMUNI.denominazione_breve_al2
  is 'Descrizione breve del comune';
comment on column COMUNI.capoluogo_provincia
  is 'Indicatore di capoluogo di provincia per i comuni italiani';
comment on column COMUNI.cap
  is 'codice avviamento postale per i comuni italiani';
comment on column COMUNI.provincia_tribunale
  is 'Codice della provincia';
comment on column COMUNI.comune_tribunale
  is 'Codice comune';
comment on column COMUNI.provincia_distretto
  is 'Codice provincia per i comuni italiani dello stato per gli esteri';
comment on column COMUNI.comune_distretto
  is 'Codice comune';
comment on column COMUNI.data_soppressione
  is 'Data nella quale il comune e" stato fuso con altri o soppresso';
comment on column COMUNI.provincia_fusione
  is 'Codice provincia per i comuni italiani dello stato per gli esteri';
comment on column COMUNI.comune_fusione
  is 'Codice comune';
comment on column COMUNI.sigla_cfis
  is 'Sigla relativa ai comuni italiani per composizione codice fiscale';
comment on column COMUNI.consolato
  is 'Codice del consolato';
create index COMU_CFIS_IK on COMUNI (SIGLA_CFIS);
create index COMU_IK on COMUNI (DENOMINAZIONE);
alter table COMUNI
  add constraint COMU_PK primary key (PROVINCIA_STATO, COMUNE);

