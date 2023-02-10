--liquibase formatted sql

--changeset mturra:201901301231_22


create table AMV_DOCUMENTI
(
  id_documento         NUMBER(10) not null,
  id_tipologia         NUMBER(10) not null,
  id_categoria         NUMBER(10) not null,
  id_argomento         NUMBER(10) not null,
  id_rilevanza         NUMBER(10) not null,
  id_area              NUMBER(10) not null,
  titolo               VARCHAR2(4000) not null,
  tipo_testo           VARCHAR2(10) default 'Testo' not null,
  testo                CLOB,
  link                 VARCHAR2(200),
  data_riferimento     DATE default SYSDATE not null,
  inizio_pubblicazione DATE,
  fine_pubblicazione   DATE,
  autore               VARCHAR2(8),
  data_inserimento     DATE default SYSDATE,
  utente_aggiornamento VARCHAR2(8),
  data_aggiornamento   DATE default SYSDATE,
  id_sezione           NUMBER(10),
  revisione            NUMBER(10) default 0 not null,
  stato                VARCHAR2(1),
  xml                  CLOB,
  immagine             VARCHAR2(200),
  id_documento_padre   NUMBER(10),
  icona                VARCHAR2(200),
  sequenza             NUMBER(10),
  abstract             CLOB
)
;
comment on table AMV_DOCUMENTI
  is 'DOCU - Documenti da pubblicare';
create index AMV_DOCU_AREE_FK on AMV_DOCUMENTI (ID_AREA);
create index AMV_DOCU_ARGO_FK on AMV_DOCUMENTI (ID_ARGOMENTO);
create index AMV_DOCU_CATE_FK on AMV_DOCUMENTI (ID_CATEGORIA);
create index AMV_DOCU_PADRE_IK on AMV_DOCUMENTI (ID_DOCUMENTO_PADRE);
create index AMV_DOCU_RILE_FK on AMV_DOCUMENTI (ID_RILEVANZA);
create index AMV_DOCU_SEZI_FK on AMV_DOCUMENTI (ID_SEZIONE);
create index AMV_DOCU_STATO_IK on AMV_DOCUMENTI (STATO);
create index AMV_DOCU_TIPO_FK on AMV_DOCUMENTI (ID_TIPOLOGIA);
create index AMV_DOCU_UTEN_AGG_FK on AMV_DOCUMENTI (UTENTE_AGGIORNAMENTO);
create index AMV_DOCU_UTEN_AUT_FK on AMV_DOCUMENTI (AUTORE);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCUMENTI_PK primary key (ID_DOCUMENTO, REVISIONE);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCU_AREE_FK foreign key (ID_AREA)
  references AMV_AREE (ID_AREA);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCU_ARGO_FK foreign key (ID_ARGOMENTO)
  references AMV_ARGOMENTI (ID_ARGOMENTO);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCU_CATE_FK foreign key (ID_CATEGORIA)
  references AMV_CATEGORIE (ID_CATEGORIA);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCU_DOCU_FK foreign key (ID_DOCUMENTO_PADRE, REVISIONE)
  references AMV_DOCUMENTI (ID_DOCUMENTO, REVISIONE);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCU_RILE_FK foreign key (ID_RILEVANZA)
  references AMV_RILEVANZE (ID_RILEVANZA);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCU_SEZI_FK foreign key (ID_SEZIONE)
  references AMV_SEZIONI (ID_SEZIONE);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCU_TIPO_FK foreign key (ID_TIPOLOGIA)
  references AMV_TIPOLOGIE (ID_TIPOLOGIA);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCU_UTEN_AGG_FK foreign key (UTENTE_AGGIORNAMENTO)
  references UTENTI (UTENTE);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCU_UTEN_AUT_FK foreign key (AUTORE)
  references UTENTI (UTENTE);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCUMENTI_ID_AREA_CC
  check (ID_AREA >= 1);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCUMENTI_ID_ARGOMENTO_CC
  check (ID_ARGOMENTO >= 1);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCUMENTI_ID_DOCUMENTO_CC
  check (ID_DOCUMENTO >= 1);
alter table AMV_DOCUMENTI
  add constraint AMV_DOCUMENTI_ID_PADRE_CC
  check (ID_DOCUMENTO_PADRE is null or (ID_DOCUMENTO_PADRE >= 1 ));
alter table AMV_DOCUMENTI
  add constraint AMV_DOCUMENTI_ID_RILEVANZA_CC
  check (ID_RILEVANZA >= 1);

