--liquibase formatted sql

--changeset mturra:201901301231_296 runOnChange:true stripComments:false endDelimiter:/


create or replace package xmldtdcover is
FUNCTION getName(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.getName(long) returns java.lang.String';
FUNCTION getEntities(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.getEntities(long) returns long';
FUNCTION getNotations(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.getNotations(long) returns long';
FUNCTION getElementDecls(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.getElementDecls(long) returns long';
FUNCTION findEntity(id number, name varchar2, par number) return number
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.findEntity(long, java.lang.String, boolean) returns long';
FUNCTION findElementDecl(id number, name varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.findElementDecl(long, java.lang.String) returns long';
FUNCTION findNotation(id number, name varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.findNotation(long, java.lang.String) returns long';
FUNCTION getPublicId(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.getPublicId(long) returns java.lang.String';
FUNCTION getSystemId(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.getSystemId(long) returns java.lang.String';
PROCEDURE writeExternalDTDToFile(id number, fileName varchar2,
                                 charset varchar2, err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.writeExternalDTDToFile(long, java.lang.String,
                                   java.lang.String, java.lang.String[])';
PROCEDURE writeExternalDTDToBuffer(id number, buffer in out varchar2,
                                   charset varchar2, err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.writeExternalDTDToBuffer(long, oracle.sql.CHAR[],
                                     java.lang.String, java.lang.String[])';
PROCEDURE writeExternalDTDToClob(id number, cl in out clob,
                                 charset varchar2, err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDTDCover.writeExternalDTDToClob(long, oracle.sql.CLOB[],
                                   java.lang.String, java.lang.String[])';
end;
/
