--liquibase formatted sql

--changeset mturra:201901301231_15


create table AMV_ABILITAZIONI
(
  abilitazione NUMBER(8) not null,
  ruolo        VARCHAR2(8) not null,
  modulo       VARCHAR2(10),
  voce_menu    VARCHAR2(8),
  padre        NUMBER(8),
  sequenza     NUMBER(2),
  dispositivo  VARCHAR2(100)
)
;
comment on table AMV_ABILITAZIONI
  is 'ABIL - Abilitazione delle voci di Menu';
create index AMV_ABIL_MENU_FK on AMV_ABILITAZIONI (PADRE, RUOLO);
create index AMV_ABIL_MODU_FK on AMV_ABILITAZIONI (MODULO);
create index AMV_ABIL_RUOL_FK on AMV_ABILITAZIONI (RUOLO);
create index AMV_ABIL_VOCI_FK on AMV_ABILITAZIONI (VOCE_MENU);
alter table AMV_ABILITAZIONI
  add constraint AMV_ABILITAZIONI_PK primary key (ABILITAZIONE, RUOLO);
alter table AMV_ABILITAZIONI
  add constraint AMV_ABIL_MENU_FK foreign key (PADRE, RUOLO)
  references AMV_ABILITAZIONI (ABILITAZIONE, RUOLO) on delete cascade;
alter table AMV_ABILITAZIONI
  add constraint AMV_ABIL_MODU_FK foreign key (MODULO)
  references MODULI (MODULO);
alter table AMV_ABILITAZIONI
  add constraint AMV_ABIL_RUOL_FK foreign key (RUOLO)
  references RUOLI (RUOLO);
alter table AMV_ABILITAZIONI
  add constraint AMV_ABIL_VOCI_FK foreign key (VOCE_MENU)
  references AMV_VOCI (VOCE);

