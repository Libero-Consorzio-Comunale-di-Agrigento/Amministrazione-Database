--liquibase formatted sql

--changeset mturra:201901301231_14


create table ALLINEAMENTO_SO4_LDAP
(
  id_allegato NUMBER not null,
  nome        VARCHAR2(2000),
  note        VARCHAR2(2000),
  allegato    CLOB,
  data        DATE default sysdate
)
;
comment on table ALLINEAMENTO_SO4_LDAP
  is 'ALSL - Log di allineamento fra So4 e Ldap';

