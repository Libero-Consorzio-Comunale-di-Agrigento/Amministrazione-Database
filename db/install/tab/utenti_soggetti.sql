--liquibase formatted sql

--changeset mturra:201901301231_93


create table UTENTI_SOGGETTI
(
  utente   VARCHAR2(8) not null,
  soggetto NUMBER not null
)
;
comment on table UTENTI_SOGGETTI
  is 'UTSO - Legame tra l''utente ed i dati anagrafici';
create index UTSO_SOGGETTO_IK on UTENTI_SOGGETTI (SOGGETTO);
alter table UTENTI_SOGGETTI
  add constraint UTSO_PK primary key (UTENTE);

