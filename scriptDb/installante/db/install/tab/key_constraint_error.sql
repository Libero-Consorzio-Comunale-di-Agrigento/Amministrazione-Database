--liquibase formatted sql

--changeset mturra:201901301231_51


create table KEY_CONSTRAINT_ERROR
(
  nome         VARCHAR2(30) not null,
  tipo_errore  VARCHAR2(2) not null,
  errore       VARCHAR2(6) not null,
  precisazione VARCHAR2(2000)
)
;
create unique index KCER_PK on KEY_CONSTRAINT_ERROR (NOME, TIPO_ERRORE);

