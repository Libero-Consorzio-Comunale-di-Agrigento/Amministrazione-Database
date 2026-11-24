--liquibase formatted sql

--changeset mturra:201901301231_17


create table AMV_ARGOMENTI
(
  id_argomento NUMBER(10) not null,
  nome         VARCHAR2(100) not null,
  descrizione  VARCHAR2(2000)
)
;
comment on table AMV_ARGOMENTI
  is 'ARGO - Argomenti delle Categorie di Documenti';
alter table AMV_ARGOMENTI
  add constraint AMV_ARGOMENTI_PK primary key (ID_ARGOMENTO);
alter table AMV_ARGOMENTI
  add constraint AMV_ARGO_NOME_AK unique (NOME);
alter table AMV_ARGOMENTI
  add constraint AMV_ARGOMENTI_ID_ARGOMENTO_CC
  check (ID_ARGOMENTO >= 1);

