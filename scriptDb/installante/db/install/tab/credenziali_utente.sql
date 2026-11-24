--liquibase formatted sql

--changeset mturra:201901301231_38


create table CREDENZIALI_UTENTE
(
  id_credenziale NUMBER not null,
  utente         VARCHAR2(8) not null
)
;
create index CREDENZIALI_UTENTE_UTEN_FK on CREDENZIALI_UTENTE (UTENTE);
alter table CREDENZIALI_UTENTE
  add constraint CREDENZIALI_UTENTE_PK primary key (ID_CREDENZIALE, UTENTE);
alter table CREDENZIALI_UTENTE
  add constraint CRED_CRUN_FK foreign key (ID_CREDENZIALE)
  references CREDENZIALI (ID_CREDENZIALE);
alter table CREDENZIALI_UTENTE
  add constraint UTEN_CRUN_FK foreign key (UTENTE)
  references UTENTI (UTENTE);

