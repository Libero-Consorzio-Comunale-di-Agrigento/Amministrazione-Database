--liquibase formatted sql

--changeset mturra:201901301231_57


create table LDAP_ATTRIBUTI
(
  id_dn            NUMBER not null,
  nome_attributo   VARCHAR2(4000) not null,
  valore_attributo VARCHAR2(4000)
)
;
create index LDAT_IK on LDAP_ATTRIBUTI (ID_DN);

