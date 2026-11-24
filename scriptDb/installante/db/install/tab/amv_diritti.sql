--liquibase formatted sql

--changeset mturra:201901301231_21


create table AMV_DIRITTI
(
  id_diritto   NUMBER(10) not null,
  id_area      NUMBER(10) not null,
  gruppo       VARCHAR2(8),
  accesso      VARCHAR2(1) default 'R' not null,
  id_tipologia NUMBER(10)
)
;
comment on table AMV_DIRITTI
  is 'DIRI - Diritti per Gruppo e Categoria di articoli';
create index AMV_DIRI_AREE_FK on AMV_DIRITTI (ID_AREA);
create index AMV_DIRI_UTEN_FK on AMV_DIRITTI (GRUPPO);
alter table AMV_DIRITTI
  add constraint AMV_DIRITTI_PK primary key (ID_DIRITTO);
alter table AMV_DIRITTI
  add constraint AMV_DIRI_AREE_FK foreign key (ID_AREA)
  references AMV_AREE (ID_AREA);
alter table AMV_DIRITTI
  add constraint AMV_DIRI_TIPO_FK foreign key (ID_TIPOLOGIA)
  references AMV_TIPOLOGIE (ID_TIPOLOGIA);
alter table AMV_DIRITTI
  add constraint AMV_DIRI_UTEN_FK foreign key (GRUPPO)
  references UTENTI (UTENTE);
alter table AMV_DIRITTI
  add constraint AMV_DIRITTI_ACCESSO_CC
  check (ACCESSO in ('R','C','U','V','A'));
alter table AMV_DIRITTI
  add constraint AMV_DIRITTI_ID_AREA_CC
  check (ID_AREA >= 1);
alter table AMV_DIRITTI
  add constraint AMV_DIRITTI_ID_DIRITTO_CC
  check (ID_DIRITTO >= 1);
alter table AMV_DIRITTI
  add constraint AMV_DIRITTI_ID_TIPOLOGIA_CC
  check (ID_TIPOLOGIA is null or (ID_TIPOLOGIA >= 1 ));

