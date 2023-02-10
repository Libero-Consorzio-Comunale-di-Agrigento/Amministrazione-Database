--liquibase formatted sql

--changeset mturra:201901301231_92


create table UTENTI_PROFILO
(
  ad4_utente              VARCHAR2(8),
  amministrazione         VARCHAR2(16),
  aoo                     VARCHAR2(16),
  ruolo                   VARCHAR2(8),
  gruppo                  VARCHAR2(8),
  assegnazione_prevalente NUMBER(2)
)
;

