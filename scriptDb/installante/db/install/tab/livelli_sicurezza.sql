--liquibase formatted sql

--changeset mturra:201901301231_60


create table LIVELLI_SICUREZZA
(
  livello     VARCHAR2(8) not null,
  descrizione VARCHAR2(40)
)
;
comment on column LIVELLI_SICUREZZA.descrizione
  is 'Descrizione LIVELLO di SICUREZZA <NLS>';
alter table LIVELLI_SICUREZZA
  add constraint LISI_PK primary key (LIVELLO);

