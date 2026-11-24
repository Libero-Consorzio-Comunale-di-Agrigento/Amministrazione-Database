--liquibase formatted sql

--changeset mturra:201901301231_16


create table AMV_AREE
(
  id_area     NUMBER(10) not null,
  nome        VARCHAR2(40) not null,
  descrizione VARCHAR2(2000)
)
;
comment on table AMV_AREE
  is 'AREE - Aree di competenza dei documenti';
alter table AMV_AREE
  add constraint AMV_AREE_PK primary key (ID_AREA);
alter table AMV_AREE
  add constraint AMV_AREE_NOME_AK unique (NOME);
alter table AMV_AREE
  add constraint AMV_AREE_ID_AREA_CC
  check (ID_AREA >= 1);

