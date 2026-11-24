--liquibase formatted sql

--changeset mturra:201901301231_11


create table AD4_LDAP_ATTRIBUTI
(
  dn               VARCHAR2(4000),
  nome_attributo   VARCHAR2(100),
  valore_attributo VARCHAR2(4000)
)
;

