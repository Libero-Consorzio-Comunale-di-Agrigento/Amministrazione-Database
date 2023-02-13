--liquibase formatted sql

--changeset mturra:201901301231_7


create sequence EXFU_SQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

