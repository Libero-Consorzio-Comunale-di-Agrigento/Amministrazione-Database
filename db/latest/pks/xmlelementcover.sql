--liquibase formatted sql

--changeset mturra:201901301231_297 runOnChange:true stripComments:false endDelimiter:/


create or replace package xmlelementcover is
FUNCTION getTagName(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.getTagName(long) returns java.lang.String';
FUNCTION getQualifiedName(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.getQualifiedName(long)
   returns java.lang.String';
FUNCTION getNamespace(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.getNamespace(long) returns java.lang.String';
FUNCTION getLocalName(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.getLocalName(long) returns java.lang.String';
FUNCTION getExpandedName(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.getExpandedName(long)
   returns java.lang.String';
FUNCTION getAttribute(id number, name varchar2) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.getAttribute(long, java.lang.String)
   returns java.lang.String';
PROCEDURE setAttribute(id number, name varchar2, value varchar2,
                       err in out number)
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.setAttribute(long, java.lang.String,
               java.lang.String, int[])';
PROCEDURE removeAttribute(id number, name varchar2, err in out number)
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.removeAttribute(long, java.lang.String,
                                      int[])';
FUNCTION getAttributeNode(id number, name varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.getAttributeNode(long, java.lang.String)
   returns long';
FUNCTION setAttributeNode(id number, newAttr number) return number
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.setAttributeNode(long, long)
   returns long';
FUNCTION removeAttributeNode(id number, oldAttr number) return number
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.removeAttributeNode(long, long)
   returns long';
FUNCTION getChildrenByTagName(id number, name varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.getChildrenByTagName(long, java.lang.String)
    returns long';
FUNCTION getElementsByTagName(id number, name varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.getElementsByTagName(long, java.lang.String)
    returns long';
FUNCTION getChildrenByTagName(id number, name varchar2, ns varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLElementCover.getChildrenByTagName(long, java.lang.String, java.lang.String) returns long';
FUNCTION getElementsByTagName(id number, name varchar2, ns varchar2) return number is language java name 'oracle.xml.parser.plsql.XMLElementCover.getElementsByTagName(long, java.lang.String, java.lang.String) returns long';
FUNCTION resolveNamespacePrefix(id number, prefix varchar2) return varchar2 is language java name 'oracle.xml.parser.plsql.XMLElementCover.resolveNamespacePrefix(long, java.lang.String) returns java.lang.String';
PROCEDURE normalize(id number) is language java
name 'oracle.xml.parser.plsql.XMLElementCover.normalize(long)';
end;
/
