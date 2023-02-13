--liquibase formatted sql

--changeset mturra:201901301231_301 runOnChange:true stripComments:false endDelimiter:/


create or replace package xmlnodecover is
PROCEDURE freeDocument(id number)
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.freeDocument(long)';
FUNCTION getNodeName(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.getNodeName(long) returns java.lang.String';
FUNCTION getNodeValue(id number, err in out number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.getNodeValue(long, int[])
returns java.lang.String';
PROCEDURE setNodeValue(id number, nodeValue varchar2, err in out number)
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.setNodeValue(long, java.lang.String,
                                int[])';
FUNCTION getNodeType(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.getNodeType(long) returns short';
FUNCTION getParentNode(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.getParentNode(long) returns long';
FUNCTION getChildNodes(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.getChildNodes(long) returns long';
FUNCTION getFirstChild(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.getFirstChild(long) returns long';
FUNCTION getLastChild(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.getLastChild(long) returns long';
FUNCTION getPreviousSibling(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.getPreviousSibling(long)
   returns long';
FUNCTION getNextSibling(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.getNextSibling(long) returns long';
FUNCTION getAttributes(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.getAttributes(long) returns long';
FUNCTION getOwnerDocument(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.getOwnerDocument(long)
returns long';
FUNCTION insertBefore(id number, newChild number, refChild number,
                      err in out number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.insertBefore(long, long,
            long, int[])
returns long';
FUNCTION replaceChild(id number, newChild number, oldChild number,
                      err in out number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.replaceChild(long, long,
            long, int[])
returns long';
FUNCTION removeChild(id number, oldChild number, err in out number)
return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.removeChild(long, long,
                            int[]) returns long';
FUNCTION appendChild(id number, newChild number, err in out number)
return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.appendChild(long, long,
                               int[]) returns long';
FUNCTION hasChildNodes(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.hasChildNodes(long) returns boolean';
FUNCTION cloneNode(id number, deep number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.cloneNode(long, boolean)
returns   long';
PROCEDURE writeToFile(id number, fileName varchar2, charset varchar2,
                      err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.writeToFile(long, java.lang.String,
                                   java.lang.String, java.lang.String[])';
PROCEDURE writeToBuffer(id number, buffer in out varchar2,
                        charset varchar2, err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.writeToBuffer(long, oracle.sql.CHAR[],
                                     java.lang.String, java.lang.String[])';
PROCEDURE writeToClob(id number, cl in out clob,
                      charset varchar2, err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLNodeCover.writeToClob(long, oracle.sql.CLOB[],
                                   java.lang.String, java.lang.String[])';
FUNCTION transformNode(id number, stylesheet number, err in out varchar2) return number is language java name 'oracle.xml.parser.plsql.XMLNodeCover.transformNode(long, int, java.lang.String[]) returns long';
FUNCTION selectNodes(id number, pattern varchar2, err in out varchar2) return number is language java name 'oracle.xml.parser.plsql.XMLNodeCover.selectNodes(long, java.lang.String, java.lang.String[]) returns long';
FUNCTION selectSingleNode(id number, pattern varchar2, err in out varchar2) return number is language java name 'oracle.xml.parser.plsql.XMLNodeCover.selectSingleNode(long, java.lang.String, java.lang.String[]) returns long';
FUNCTION valueOf(id number, pattern varchar2, err in out varchar2) return varchar2 is language java name 'oracle.xml.parser.plsql.XMLNodeCover.valueOf(long, java.lang.String, java.lang.String[]) returns java.lang.String';
end;
/

