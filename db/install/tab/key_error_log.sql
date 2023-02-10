--liquibase formatted sql

--changeset mturra:201901301231_55


create table KEY_ERROR_LOG
(
  error_id       NUMBER not null,
  error_session  NUMBER,
  error_date     DATE,
  error_text     VARCHAR2(2000),
  error_user     VARCHAR2(8),
  error_usertext VARCHAR2(2000),
  error_type     VARCHAR2(1)
)
;
comment on table KEY_ERROR_LOG
  is 'KEEL - Tabella degli Errori di applicazione';
alter table KEY_ERROR_LOG
  add constraint KEEL_PK primary key (ERROR_ID);

