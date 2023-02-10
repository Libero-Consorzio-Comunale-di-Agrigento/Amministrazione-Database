--liquibase formatted sql

--changeset mturra:201901301231_64


create table PARAMETRI_RICHIESTA
(
  id_parametro         NUMBER not null,
  id_richiesta         NUMBER not null,
  parametro            VARCHAR2(60) not null,
  valore               VARCHAR2(2000),
  utente_aggiornamento VARCHAR2(8),
  data_aggiornamento   DATE
)
;
alter table PARAMETRI_RICHIESTA
  add constraint PARI_PK primary key (ID_PARAMETRO);
alter table PARAMETRI_RICHIESTA
  add constraint RIAB_PARI_FK foreign key (ID_RICHIESTA)
  references RICHIESTE_ABILITAZIONE (ID_RICHIESTA);

