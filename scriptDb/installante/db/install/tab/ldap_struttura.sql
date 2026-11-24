--liquibase formatted sql

--changeset mturra:201901301231_58


create table LDAP_STRUTTURA
(
  id_dn     NUMBER not null,
  id_padre  NUMBER,
  dn        VARCHAR2(4000) not null,
  categoria VARCHAR2(64) not null
)
;
alter table LDAP_STRUTTURA
  add constraint LDST_PK primary key (ID_DN);
alter table LDAP_STRUTTURA
  add constraint LDAP_STRUTTURA_CATEGORIA_CC
  check (categoria in ('PERSON','GROUP','ORGANIZATIONALUNIT','DOMAIN','CONTAINER','OTHER'));

