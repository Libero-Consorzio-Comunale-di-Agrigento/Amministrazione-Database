--liquibase formatted sql

--changeset mturra:201901301231_87


create table STRUTTURA_UTENTI_TEMP
(
  descrizione VARCHAR2(4000),
  figlio      VARCHAR2(8),
  tipo_figlio VARCHAR2(1),
  padre       VARCHAR2(8),
  tipo_padre  VARCHAR2(1),
  struttura   VARCHAR2(4000),
  livello     NUMBER,
  ord         VARCHAR2(4000),
  gruppo_so   VARCHAR2(1)
)
;
create index STUT_TMP_FIGLIO_IK on STRUTTURA_UTENTI_TEMP (FIGLIO);
create index STUT_TMP_PADRE_IK on STRUTTURA_UTENTI_TEMP (PADRE);
create index STUT_TMP_TIPO_FIGLIO_IK on STRUTTURA_UTENTI_TEMP (TIPO_FIGLIO);

