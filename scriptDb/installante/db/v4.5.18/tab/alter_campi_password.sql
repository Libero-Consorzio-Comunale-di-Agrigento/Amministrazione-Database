--liquibase formatted sql

--changeset snegroni:alter_campi_password runOnChange:true stripComments:false endDelimiter:;

alter table utenti modify (password varchar2(200));
alter table utenti_storico modify (password varchar2(200));
alter table password_storico modify (password varchar2(200));
alter table utenti_salt modify (salt varchar2(200));