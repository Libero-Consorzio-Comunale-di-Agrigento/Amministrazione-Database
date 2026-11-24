--liquibase formatted sql

--changeset mturra:201901301231_302 runOnChange:true stripComments:false endDelimiter:/


create or replace package xmlnodelistcover is
FUNCTION getLength(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeListCover.getLength(long) returns int';
FUNCTION item(id number, idx number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeListCover.item(long, int) returns long';
end;
/

