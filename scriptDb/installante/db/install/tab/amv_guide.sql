--liquibase formatted sql

--changeset mturra:201901301231_25


create table AMV_GUIDE
(
  guida            VARCHAR2(8) not null,
  sequenza         NUMBER(2) not null,
  alias            VARCHAR2(6) not null,
  titolo           VARCHAR2(20),
  titolo_al1       VARCHAR2(20),
  titolo_al2       VARCHAR2(20),
  titolo_breve     VARCHAR2(9),
  titolo_breve_al1 VARCHAR2(9),
  titolo_breve_al2 VARCHAR2(9),
  lettera          VARCHAR2(1),
  lettera_al1      VARCHAR2(1),
  lettera_al2      VARCHAR2(1),
  voce_guida       VARCHAR2(8),
  voce_menu        VARCHAR2(8),
  voce_rif         VARCHAR2(8),
  proprieta        VARCHAR2(1)
)
;
comment on table AMV_GUIDE
  is 'GUID - Voci Guida ';
create index AMV_GUID_VOCI_FK on AMV_GUIDE (GUIDA);
create index AMV_GUID_VOCI_GUI_FK on AMV_GUIDE (VOCE_GUIDA);
create index AMV_GUID_VOCI_MEN_FK on AMV_GUIDE (VOCE_MENU);
create index AMV_GUID_VOCI_RIF_FK on AMV_GUIDE (VOCE_RIF);
alter table AMV_GUIDE
  add constraint AMV_GUIDE_PK primary key (GUIDA, SEQUENZA);
alter table AMV_GUIDE
  add constraint AMV_GUID_VOCI_FK foreign key (GUIDA)
  references AMV_VOCI (VOCE) on delete cascade;
alter table AMV_GUIDE
  add constraint AMV_GUID_VOCI_GUI_FK foreign key (VOCE_GUIDA)
  references AMV_VOCI (VOCE);
alter table AMV_GUIDE
  add constraint AMV_GUID_VOCI_MEN_FK foreign key (VOCE_MENU)
  references AMV_VOCI (VOCE);
alter table AMV_GUIDE
  add constraint AMV_GUID_VOCI_RIF_FK foreign key (VOCE_RIF)
  references AMV_VOCI (VOCE);

