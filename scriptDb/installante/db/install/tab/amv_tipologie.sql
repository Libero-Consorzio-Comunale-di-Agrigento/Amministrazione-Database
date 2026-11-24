--liquibase formatted sql

--changeset mturra:201901301231_28


create table AMV_TIPOLOGIE
(
  id_tipologia    NUMBER(10) not null,
  nome            VARCHAR2(40) not null,
  descrizione     VARCHAR2(2000),
  zona            VARCHAR2(1),
  sequenza        NUMBER(4),
  immagine        VARCHAR2(200),
  link            VARCHAR2(200),
  zona_visibilita VARCHAR2(1) default 'H',
  zona_formato    VARCHAR2(1) default 'T',
  max_vis         NUMBER(4),
  icona           VARCHAR2(200)
)
;
comment on table AMV_TIPOLOGIE
  is 'TIPO - Tipologie di articoli';
alter table AMV_TIPOLOGIE
  add constraint AMV_TIPOLOGIE_PK primary key (ID_TIPOLOGIA);
alter table AMV_TIPOLOGIE
  add constraint AMV_TIPO_NOME_AK unique (NOME);
alter table AMV_TIPOLOGIE
  add constraint AMV_TIPO_FOR_CC
  check (ZONA_FORMATO is null or ( ZONA_FORMATO in ('T','I') ));
alter table AMV_TIPOLOGIE
  add constraint AMV_TIPOLOGIE_ID_TIPOLOGIA_CC
  check (ID_TIPOLOGIA >= 1);
alter table AMV_TIPOLOGIE
  add constraint AMV_TIPOLOGIE_ZONA_CC
  check (ZONA is null or ( ZONA in ('A','S','D','C') ));
alter table AMV_TIPOLOGIE
  add constraint AMV_TIPO_ZONA_VISIB_CC
  check (ZONA_VISIBILITA is null or ( ZONA_VISIBILITA in ('S','H','C') ));

