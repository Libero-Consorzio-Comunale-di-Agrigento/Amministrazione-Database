--liquibase formatted sql

--changeset mturra:201901301231_31


create table BANCHE
(
  abi           VARCHAR2(5) not null,
  cin_abi       VARCHAR2(1) not null,
  denominazione VARCHAR2(60) not null
)
;
comment on table BANCHE
  is 'BANC - Banche e Istituti di credito';
comment on column BANCHE.denominazione
  is 'Denominazione della Banca <NLS>';
create index BANC_DENOMINAZIONE_IK on BANCHE (DENOMINAZIONE);
alter table BANCHE
  add constraint BANCHE_PK primary key (ABI);

