--liquibase formatted sql

--changeset mturra:201901301231_2


create sequence ALSL_SQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;

