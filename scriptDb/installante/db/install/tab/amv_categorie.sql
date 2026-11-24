--liquibase formatted sql

--changeset mturra:201901301231_19


create table AMV_CATEGORIE
(
  id_categoria NUMBER(10) not null,
  nome         VARCHAR2(100) not null,
  descrizione  VARCHAR2(2000)
)
;
comment on table AMV_CATEGORIE
  is 'CATE - Categirie degli articoli';
alter table AMV_CATEGORIE
  add constraint AMV_CATEGORIE_PK primary key (ID_CATEGORIA);
alter table AMV_CATEGORIE
  add constraint AMV_CATE_NOME_AK unique (NOME);
alter table AMV_CATEGORIE
  add constraint AMV_CATEGORIE_ID_CATEGORIA_CC
  check (ID_CATEGORIA >= 1);

