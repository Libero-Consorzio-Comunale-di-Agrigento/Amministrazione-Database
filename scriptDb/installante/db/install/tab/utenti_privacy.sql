--liquibase formatted sql

--changeset mturra:201901301231_91


create table UTENTI_PRIVACY
(
  id                         NUMBER not null,
  utente                     VARCHAR2(8),
  soggetto                   NUMBER,
  ente                       VARCHAR2(4),
  modulo                     VARCHAR2(10),
  data_prima_accettazione    DATE,
  client_prima_accettazione  VARCHAR2(2000),
  data_ultima_accettazione   DATE,
  client_ultima_accettazione VARCHAR2(2000),
  note                       VARCHAR2(2000)
)
;
comment on table UTENTI_PRIVACY
  is 'UTPY - Utenti Privacy';
alter table UTENTI_PRIVACY
  add constraint UTENTI_PRIVACY_PK primary key (ID);
alter table UTENTI_PRIVACY
  add constraint UTENTI_PRIVACY_UK unique (UTENTE, ENTE, MODULO);

