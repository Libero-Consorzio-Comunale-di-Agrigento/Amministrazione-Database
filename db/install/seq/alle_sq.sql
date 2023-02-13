--liquibase formatted sql

--changeset mturra:201901301231_1


create sequence ALLE_SQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;

