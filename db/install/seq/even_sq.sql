--liquibase formatted sql

--changeset mturra:201901301231_6


create sequence EVEN_SQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 100;
