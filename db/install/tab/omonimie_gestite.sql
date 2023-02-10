--liquibase formatted sql

--changeset mturra:201901301231_62


create table OMONIMIE_GESTITE
(
  id_omonimia           NUMBER(10) not null,
  primario              VARCHAR2(10),
  secondario            VARCHAR2(10),
  scelto_primario       NUMBER(1),
  unificato             NUMBER(1) default 0,
  copiato               NUMBER(1) default 0,
  ignorare              NUMBER(1) default 0,
  note                  VARCHAR2(4000),
  nominativo_primario   VARCHAR2(40),
  nominativo_secondario VARCHAR2(40),
  utente_agg            VARCHAR2(8),
  data_agg              DATE
)
;
comment on table OMONIMIE_GESTITE
  is 'Vengono memorizzate le omonimie che sono state gestite in AD4 relativamente a appartenenza a gruppi, diritti di accesso, caratteristiche di accesso e soggetto abbinato.';
comment on column OMONIMIE_GESTITE.primario
  is 'Utente su cui riversare.';
comment on column OMONIMIE_GESTITE.secondario
  is 'Utente di origine.';
comment on column OMONIMIE_GESTITE.scelto_primario
  is 'Se è stato scelto dall''utente o proposto dall''applicativo.';
comment on column OMONIMIE_GESTITE.unificato
  is 'Rimane attivo solo utente su cui riversare.';
comment on column OMONIMIE_GESTITE.copiato
  is 'Utente di origine rimane comunque attivo.';
comment on column OMONIMIE_GESTITE.ignorare
  is 'Se non far vedere da applicativo.';
comment on column OMONIMIE_GESTITE.note
  is 'Commenti.';
comment on column OMONIMIE_GESTITE.nominativo_primario
  is 'Nominativo utente primario al momento dell''attività.';
comment on column OMONIMIE_GESTITE.nominativo_secondario
  is 'Nominativo utente secondario al momento dell''attività.';
create unique index OMONIMIE_GESTITKE_UK on OMONIMIE_GESTITE (PRIMARIO, SECONDARIO);
alter table OMONIMIE_GESTITE
  add constraint OMONIMIE_GESTITE_PK primary key (ID_OMONIMIA);

