--liquibase formatted sql

--changeset mturra:201901301231_307 runOnChange:true stripComments:false endDelimiter:/


create or replace package xmltextcover is
FUNCTION splitText(id number, offset number, err in out number)
return number
is language java
name 'oracle.xml.parser.plsql.XMLTextCover.splitText(long, int, int[])
returns long';
end;
/
