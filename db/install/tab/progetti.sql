--liquibase formatted sql

--changeset mturra:201901301231_66


create table PROGETTI
(
  progetto        VARCHAR2(8) not null,
  descrizione     VARCHAR2(40),
  priorita        NUMBER(2),
  note            VARCHAR2(2000),
  descrizione_al1 VARCHAR2(40),
  descrizione_al2 VARCHAR2(40)
)
;
comment on table PROGETTI
  is 'PROG - Progetti. (Valori: 0 bassa - 99 alta)';
comment on column PROGETTI.descrizione
  is 'Descrizione PROGETTO <NLS>';
comment on column PROGETTI.priorita
  is 'Valori: 0 bassa - 99 alta';
alter table PROGETTI
  add constraint PROG_PK primary key (PROGETTO);

