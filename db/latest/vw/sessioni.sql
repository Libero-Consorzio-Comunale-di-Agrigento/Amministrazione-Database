--liquibase formatted sql

--changeset mturra:201905201121 runOnChange:true stripComments:false


create or replace force view sessioni as
select *
  from SYS.GV_$SESSION;

