--liquibase formatted sql

--changeset mturra:201901301231_9


create sequence SPLO_SQ
minvalue 0
maxvalue 999999999999999999999999999
start with 0
increment by 1
nocache;

