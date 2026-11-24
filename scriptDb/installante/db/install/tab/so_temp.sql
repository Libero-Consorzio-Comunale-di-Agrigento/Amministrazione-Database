--liquibase formatted sql

--changeset mturra:201901301231_79


create global temporary table SO_TEMP
(
  figlio    VARCHAR2(8) not null,
  padre     VARCHAR2(8),
  struttura VARCHAR2(4000) not null
)
on commit delete rows;
comment on table SO_TEMP
  is 'SOTE - Tabella temporanea per memorizzazione struttura AD4';
create unique index SOTE_UK on SO_TEMP (FIGLIO, PADRE);

