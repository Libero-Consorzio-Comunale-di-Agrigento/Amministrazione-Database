--liquibase formatted sql

--changeset snegroni:20190704_1027

create or replace synonym DBMS_BACKUP_RESTORE  for sys.DBMS_BACKUP_RESTORE;

