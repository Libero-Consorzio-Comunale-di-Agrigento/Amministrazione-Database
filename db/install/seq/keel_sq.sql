--liquibase formatted sql

--changeset mturra:201901301231_8


create sequence KEEL_SQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
nocache;

