--liquibase formatted sql

--changeset mturra:201901301231_85


create table SPORTELLI
(
  abi         VARCHAR2(5) not null,
  cab         VARCHAR2(5) not null,
  cin_cab     VARCHAR2(1),
  descrizione VARCHAR2(40),
  indirizzo   VARCHAR2(60),
  localita    VARCHAR2(20),
  comune      VARCHAR2(20),
  provincia   VARCHAR2(2),
  cap         VARCHAR2(5),
  dipendenza  VARCHAR2(4),
  bic         VARCHAR2(11)
)
;
comment on table SPORTELLI
  is 'SPOR - Sportelli bancari degli istituti di credito';
comment on column SPORTELLI.descrizione
  is 'Denominazione dello Sportello <NLS>';
create index SPOR_DIPENDENZA_IK on SPORTELLI (DIPENDENZA);
alter table SPORTELLI
  add constraint SPORTELLI_PK primary key (ABI, CAB);

