--liquibase formatted sql

--changeset mturra:201901301231_26


create table AMV_RILEVANZE
(
  id_rilevanza    NUMBER(10) not null,
  nome            VARCHAR2(40) not null,
  importanza      VARCHAR2(2),
  sequenza        NUMBER(4),
  zona            VARCHAR2(1),
  zona_formato    VARCHAR2(1) default 'T',
  zona_visibilita VARCHAR2(1) default 'H',
  max_vis         NUMBER(4),
  immagine        VARCHAR2(200),
  icona           VARCHAR2(200)
)
;
comment on table AMV_RILEVANZE
  is 'RILE - Rilevanze usate principalmente per la esposizione degli articoli';
alter table AMV_RILEVANZE
  add constraint AMV_RILEVANZE_PK primary key (ID_RILEVANZA);
alter table AMV_RILEVANZE
  add constraint AMV_RILE_NOME_AK unique (NOME);
alter table AMV_RILEVANZE
  add constraint AMV_RILEVANZE_ID_RILEVANZA_CC
  check (ID_RILEVANZA >= 1);
alter table AMV_RILEVANZE
  add constraint AMV_RILEVANZE_IMPORTANZA_CC
  check (IMPORTANZA is null or ( IMPORTANZA in ('HL','HP') ));
alter table AMV_RILEVANZE
  add constraint AMV_RILEVANZE_ZONA_CC
  check (ZONA is null or ( ZONA in ('S','C','D') ));
alter table AMV_RILEVANZE
  add constraint AMV_RILEVANZE_ZONA_FORMATO_CC
  check (ZONA_FORMATO is null or ( ZONA_FORMATO in ('T','I') ));
alter table AMV_RILEVANZE
  add constraint AMV_RILEVANZE_ZONA_VISIBILI_CC
  check (ZONA_VISIBILITA is null or ( ZONA_VISIBILITA in ('S','H','C') ));

