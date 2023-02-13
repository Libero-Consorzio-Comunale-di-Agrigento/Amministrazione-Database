--liquibase formatted sql

--changeset mturra:201901301231_295 runOnChange:true stripComments:false endDelimiter:/


create or replace package xmldomimplcover is
FUNCTION hasFeature(id number, feature varchar2, version varchar2)
   return number
is language java
name 'oracle.xml.parser.plsql.XMLDOMImplCover.hasFeature(int, java.lang.String,
   java.lang.String) returns boolean';
end;
/
