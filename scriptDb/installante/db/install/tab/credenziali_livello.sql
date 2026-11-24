--liquibase formatted sql

--changeset mturra:201901301231_37


create table CREDENZIALI_LIVELLO
(
  livello          VARCHAR2(8) not null,
  tipo_credenziale VARCHAR2(8) not null
)
;
alter table CREDENZIALI_LIVELLO
  add constraint CREDENZIALI_LIVELLO_PK primary key (LIVELLO, TIPO_CREDENZIALE);
alter table CREDENZIALI_LIVELLO
  add constraint LISI_CRLI_FK foreign key (LIVELLO)
  references LIVELLI_SICUREZZA (LIVELLO);
alter table CREDENZIALI_LIVELLO
  add constraint TICR_CRLI_FK foreign key (TIPO_CREDENZIALE)
  references TIPI_CREDENZIALE (TIPO_CREDENZIALE);

