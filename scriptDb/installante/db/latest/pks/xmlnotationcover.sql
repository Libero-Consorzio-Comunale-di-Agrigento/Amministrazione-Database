--liquibase formatted sql

--changeset mturra:201901301231_303 runOnChange:true stripComments:false endDelimiter:/


create or replace package xmlnotationcover is
FUNCTION getPublicId(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLNotationCover.getPublicId(long) returns java.lang.String';
FUNCTION getSystemId(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLNotationCover.getSystemId(long) returns java.lang.String';
end;
/

