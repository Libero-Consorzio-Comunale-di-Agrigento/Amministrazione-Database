--liquibase formatted sql

--changeset mturra:201901301231_10


create sequence SPTA_SQ
minvalue 0
maxvalue 999999999999999999999999999
start with 0
increment by 1
nocache;

