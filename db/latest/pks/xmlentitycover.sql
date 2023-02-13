--liquibase formatted sql

--changeset mturra:201901301231_298 runOnChange:true stripComments:false endDelimiter:/


create or replace package xmlentitycover is
FUNCTION getPublicId(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLEntityCover.getPublicId(long) returns java.lang.String';
FUNCTION getSystemId(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLEntityCover.getSystemId(long) returns java.lang.String';
FUNCTION getNotationName(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLEntityCover.getNotationName(long)
   returns java.lang.String';
end;
/


