--liquibase formatted sql

--changeset mturra:201901301231_30


create table ANAG_UNITA
(
  amministrazione           VARCHAR2(16),
  codice_uo                 VARCHAR2(16),
  des_unita_organizzativa   VARCHAR2(120),
  des_abb                   VARCHAR2(20),
  utente_ad4                VARCHAR2(8),
  codice_aoo                VARCHAR2(4000),
  progr_aoo                 NUMBER(8),
  progr_unita_organizzativa NUMBER(8)
)
;

