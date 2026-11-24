--liquibase formatted sql

--changeset mturra:201901301231_54


create table KEY_ERROR
(
  errore      VARCHAR2(6) not null,
  descrizione VARCHAR2(240) not null,
  tipo        VARCHAR2(1),
  key         VARCHAR2(30)
)
;
comment on column KEY_ERROR.descrizione
  is 'Descrizione ERRORE <NLS>';
alter table KEY_ERROR
  add constraint KERR_PK primary key (ERRORE);

