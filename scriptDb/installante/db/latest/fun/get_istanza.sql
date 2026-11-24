--liquibase formatted sql

--property name:istanza value:UNDEF

--changeset mturra:201901301231_97 runOnChange:true endDelimiter:/ stripComments:false

create or replace function GET_ISTANZA return varchar2 is begin
    return '${istanza}';
end;
/

