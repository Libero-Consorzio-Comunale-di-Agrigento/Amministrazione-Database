--liquibase formatted sql

--changeset mturra:201901301231_20


create table AMV_CLASSIFICAZIONI
(
  id_classificazione NUMBER(10) not null,
  id_categoria       NUMBER(10) not null,
  id_argomento       NUMBER(10) not null,
  note               VARCHAR2(2000)
)
;
comment on table AMV_CLASSIFICAZIONI
  is 'CLAS - Classificazione Categorie di Argomento';
create index AMV_CLAS_ARGO_FK on AMV_CLASSIFICAZIONI (ID_ARGOMENTO);
create index AMV_CLAS_CATE_FK on AMV_CLASSIFICAZIONI (ID_CATEGORIA);
alter table AMV_CLASSIFICAZIONI
  add constraint AMV_CLASSIFICAZIONI_PK primary key (ID_CLASSIFICAZIONE);
alter table AMV_CLASSIFICAZIONI
  add constraint AMV_CLAS_ARGO_FK foreign key (ID_ARGOMENTO)
  references AMV_ARGOMENTI (ID_ARGOMENTO);
alter table AMV_CLASSIFICAZIONI
  add constraint AMV_CLAS_CATE_FK foreign key (ID_CATEGORIA)
  references AMV_CATEGORIE (ID_CATEGORIA);
alter table AMV_CLASSIFICAZIONI
  add constraint AMV_CLASSIFIC_ID_ARGOMENTO_CC
  check (ID_ARGOMENTO >= 1);
alter table AMV_CLASSIFICAZIONI
  add constraint AMV_CLASSIFIC_ID_CATEGORIA_CC
  check (ID_CATEGORIA >= 1);
alter table AMV_CLASSIFICAZIONI
  add constraint AMV_CLASSIFIC_ID_CLASSIFICA_CC
  check (ID_CLASSIFICAZIONE >= 1);

