--liquibase formatted sql

--changeset mturra:201901301231_48


create table ISTANZE
(
  istanza                      VARCHAR2(10) not null,
  progetto                     VARCHAR2(8) not null,
  ente                         VARCHAR2(4) not null,
  descrizione                  VARCHAR2(40) not null,
  user_oracle                  VARCHAR2(50) not null,
  password_oracle              VARCHAR2(50) not null,
  dislocazione                 VARCHAR2(80) not null,
  dislocazione_temporanea      VARCHAR2(80),
  installazione                VARCHAR2(2000),
  versione                     VARCHAR2(10),
  dislocazione_dimensionamenti VARCHAR2(20),
  note                         VARCHAR2(2000),
  lingua                       VARCHAR2(1) default 'I' not null,
  link_oracle                  VARCHAR2(2000),
  database_link                VARCHAR2(2000),
  servizio                     VARCHAR2(100),
  database_driver              VARCHAR2(2000),
  istanza_amministratore       VARCHAR2(10)
)
;
comment on column ISTANZE.descrizione
  is 'Descrizione ISTANZA <NLS>';
create index ISTA_UK on ISTANZE (USER_ORACLE);
alter table ISTANZE
  add constraint ISTA_PK primary key (ISTANZA);
alter table ISTANZE
  add constraint ISTA_ISTA_FK foreign key (ISTANZA_AMMINISTRATORE)
  references ISTANZE (ISTANZA);

