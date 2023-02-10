--liquibase formatted sql

--changeset mturra:201901301231_77


create global temporary table SO4_SO_TEMP
(
  figlio    VARCHAR2(8) not null,
  padre     VARCHAR2(8),
  struttura VARCHAR2(4000) not null
)
on commit delete rows;
comment on table SO4_SO_TEMP
  is 'SSTE - Tabella temporanea per memorizzazione struttura SO4';
create unique index SSTE_UK on SO4_SO_TEMP (FIGLIO, PADRE);

