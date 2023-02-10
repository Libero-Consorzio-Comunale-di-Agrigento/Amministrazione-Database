--liquibase formatted sql

--changeset mturra:201901301231_43


create table ENTI
(
  ente            VARCHAR2(4) not null,
  descrizione     VARCHAR2(80) not null,
  descrizione_al1 VARCHAR2(80),
  descrizione_al2 VARCHAR2(80),
  bitmap          VARCHAR2(80),
  note            VARCHAR2(2000),
  disegno         VARCHAR2(80),
  soggetto        NUMBER(8)
)
;
comment on column ENTI.descrizione
  is 'Denominazione Ente <NLS>';
create index ENTI_SOGG_FK on ENTI (SOGGETTO);
alter table ENTI
  add constraint ENTI_PK primary key (ENTE);

