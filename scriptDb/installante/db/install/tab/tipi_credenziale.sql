--liquibase formatted sql

--changeset mturra:201901301231_88


create table TIPI_CREDENZIALE
(
  tipo_credenziale VARCHAR2(8) not null,
  descrizione      VARCHAR2(40)
)
;
comment on column TIPI_CREDENZIALE.descrizione
  is 'Descrizione TIPO di CREDENZIALE <NLS>';
alter table TIPI_CREDENZIALE
  add constraint TICR_PK primary key (TIPO_CREDENZIALE);

