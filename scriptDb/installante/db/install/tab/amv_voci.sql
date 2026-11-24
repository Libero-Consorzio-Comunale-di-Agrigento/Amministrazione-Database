--liquibase formatted sql

--changeset mturra:201901301231_29


create table AMV_VOCI
(
  voce         VARCHAR2(8) not null,
  progetto     VARCHAR2(8),
  acronimo     VARCHAR2(5),
  acronimo_al1 VARCHAR2(5),
  acronimo_al2 VARCHAR2(5),
  titolo       VARCHAR2(40),
  titolo_al1   VARCHAR2(40),
  titolo_al2   VARCHAR2(40),
  tipo_voce    VARCHAR2(1) not null,
  tipo         VARCHAR2(1) not null,
  modulo       VARCHAR2(40),
  stringa      VARCHAR2(200),
  profilo      NUMBER(2),
  voce_guida   VARCHAR2(8),
  proprieta    VARCHAR2(1),
  note         VARCHAR2(2000)
)
;
comment on table AMV_VOCI
  is 'VOCI - Voci di Menu';
create index AMV_VOCI_MODULO_IK on AMV_VOCI (MODULO);
create index AMV_VOCI_PROG_FK on AMV_VOCI (PROGETTO);
create index AMV_VOCI_STRINGA_IK on AMV_VOCI (STRINGA);
create index AMV_VOCI_VOCI_FK on AMV_VOCI (VOCE_GUIDA);
alter table AMV_VOCI
  add constraint AMV_VOCI_PK primary key (VOCE);
alter table AMV_VOCI
  add constraint AMV_VOCI_PROG_FK foreign key (PROGETTO)
  references PROGETTI (PROGETTO);
alter table AMV_VOCI
  add constraint AMV_VOCI_VOCI_FK foreign key (VOCE_GUIDA)
  references AMV_VOCI (VOCE) on delete set null;
alter table AMV_VOCI
  add constraint AMV_VOCI_TIPO_CC
  check (TIPO in ('M','C','N','E','F','Q','D','L','A'));
alter table AMV_VOCI
  add constraint AMV_VOCI_TIPO_VOCE_CC
  check (TIPO_VOCE in ('N','F','A'));

