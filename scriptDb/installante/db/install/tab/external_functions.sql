--liquibase formatted sql

--changeset mturra:201901301231_45


create table EXTERNAL_FUNCTIONS
(
  function_id     NUMBER not null,
  table_name      VARCHAR2(30) not null,
  function_owner  VARCHAR2(30) not null,
  firing_function VARCHAR2(4000) not null,
  firing_event    VARCHAR2(1) not null
)
;
comment on table EXTERNAL_FUNCTIONS
  is 'EXFU - Funzioni esterne da lanciare in seguito a modifica della tabella specificata.';
create unique index EXFU_UK on EXTERNAL_FUNCTIONS (TABLE_NAME, FUNCTION_OWNER, FIRING_EVENT);
alter table EXTERNAL_FUNCTIONS
  add constraint EXTERNAL_FUNCTIONS_PK primary key (FUNCTION_ID);
alter table EXTERNAL_FUNCTIONS
  add constraint EXTERNAL_FUNC_FIRING_EVENT_CC
  check (FIRING_EVENT in ('I','U','D'));

