--liquibase formatted sql

--changeset mturra:201901301231_24


create table AMV_DOCUMENTI_REVISIONI
(
  id_documento        NUMBER(10) not null,
  revisione           NUMBER(10) not null,
  data_redazione      DATE,
  utente_redazione    VARCHAR2(8),
  data_verifica       DATE,
  utente_verifica     VARCHAR2(8),
  data_approvazione   DATE,
  utente_approvazione VARCHAR2(8),
  cronologia          VARCHAR2(4000),
  note                VARCHAR2(4000)
)
;
create index AMV_DOCU_REVI_UTEN_APP on AMV_DOCUMENTI_REVISIONI (UTENTE_APPROVAZIONE);
create index AMV_DOCU_REVI_UTEN_RED on AMV_DOCUMENTI_REVISIONI (UTENTE_REDAZIONE);
create index AMV_DOCU_REVI_UTEN_VER on AMV_DOCUMENTI_REVISIONI (UTENTE_VERIFICA);
alter table AMV_DOCUMENTI_REVISIONI
  add constraint AMV_DOCUMENTI_REVISIONI_PK primary key (ID_DOCUMENTO, REVISIONE);
alter table AMV_DOCUMENTI_REVISIONI
  add constraint AMV_DORE_DOCU_FK foreign key (ID_DOCUMENTO, REVISIONE)
  references AMV_DOCUMENTI (ID_DOCUMENTO, REVISIONE);
alter table AMV_DOCUMENTI_REVISIONI
  add constraint AMV_DORE_UTEN_APP_FK foreign key (UTENTE_APPROVAZIONE)
  references UTENTI (UTENTE);
alter table AMV_DOCUMENTI_REVISIONI
  add constraint AMV_DORE_UTEN_RED_FK foreign key (UTENTE_REDAZIONE)
  references UTENTI (UTENTE);
alter table AMV_DOCUMENTI_REVISIONI
  add constraint AMV_DORE_UTEN_VER_FK foreign key (UTENTE_VERIFICA)
  references UTENTI (UTENTE);

