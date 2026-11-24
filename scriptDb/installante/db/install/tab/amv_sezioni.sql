--liquibase formatted sql

--changeset mturra:201901301231_27


create table AMV_SEZIONI
(
  id_sezione      NUMBER(10) not null,
  nome            VARCHAR2(100),
  descrizione     VARCHAR2(2000),
  zona            VARCHAR2(1),
  sequenza        NUMBER(4),
  immagine        VARCHAR2(200),
  max_vis         NUMBER(4),
  id_padre        NUMBER(10),
  visibilita      VARCHAR2(2),
  zona_formato    VARCHAR2(1) default 'T',
  zona_tipo       VARCHAR2(1) default 'S',
  zona_espansione VARCHAR2(1) default 'S',
  zona_visibilita VARCHAR2(1) default 'S',
  style           VARCHAR2(100),
  logo_sx         VARCHAR2(100),
  logo_sx_link    VARCHAR2(200),
  logo_dx         VARCHAR2(100),
  logo_dx_link    VARCHAR2(200),
  img_header      VARCHAR2(100),
  intestazione    VARCHAR2(2000),
  copyright       VARCHAR2(2000),
  id_area         NUMBER(10),
  icona           VARCHAR2(200)
)
;
create index AMV_SEZI_AREE_FK on AMV_SEZIONI (ID_AREA);
alter table AMV_SEZIONI
  add constraint AMV_SEZIONI_PK primary key (ID_SEZIONE);
alter table AMV_SEZIONI
  add constraint AMV_SEZI_AREE_FK foreign key (ID_AREA)
  references AMV_AREE (ID_AREA);
alter table AMV_SEZIONI
  add constraint AMV_SEZIONI_VISIBILITA_CC
  check (VISIBILITA is null or ( VISIBILITA in ('N','T','I') ));
alter table AMV_SEZIONI
  add constraint AMV_SEZIONI_ZONA_CC
  check (ZONA is null or ( ZONA in ('S','C','D','A') ));
alter table AMV_SEZIONI
  add constraint AMV_SEZIONI_ZONA_ESPANSIO_CC
  check (ZONA_ESPANSIONE is null or ( ZONA_ESPANSIONE in ('S','N') ));
alter table AMV_SEZIONI
  add constraint AMV_SEZIONI_ZONA_FORMATO_CC
  check (ZONA_FORMATO is null or ( ZONA_FORMATO in ('T','I') ));
alter table AMV_SEZIONI
  add constraint AMV_SEZIONI_ZONA_TIPO_CC
  check (ZONA_TIPO is null or ( ZONA_TIPO in ('D','S','E') ));
alter table AMV_SEZIONI
  add constraint AMV_SEZIONI_ZONA_VISIBILI_CC
  check (ZONA_VISIBILITA is null or ( ZONA_VISIBILITA in ('S','H','C','E') ));

