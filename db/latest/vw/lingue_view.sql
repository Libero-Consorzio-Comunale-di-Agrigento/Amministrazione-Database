--liquibase formatted sql

--changeset mturra:201901301231_442 runOnChange:true stripComments:false


create or replace force view lingue_view as
select LINGUE.LINGUA, LINGUE.DESCRIZIONE
from LINGUE
UNION
select '*', '*'
from DUAL_PB;

