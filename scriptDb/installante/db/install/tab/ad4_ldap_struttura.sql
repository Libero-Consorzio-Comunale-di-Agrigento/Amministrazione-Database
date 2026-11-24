--liquibase formatted sql

--changeset mturra:201901301231_12


create table AD4_LDAP_STRUTTURA
(
  dn          VARCHAR2(4000),
  tipo        VARCHAR2(18),
  descrizione VARCHAR2(4000),
  figlio      VARCHAR2(8)
)
;
create index AD4_LDST_FIGLIO_IK on AD4_LDAP_STRUTTURA (FIGLIO);
create index AD4_LDST_TIPO_IK on AD4_LDAP_STRUTTURA (TIPO);

