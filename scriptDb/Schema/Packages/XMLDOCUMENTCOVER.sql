CREATE OR REPLACE package xmldocumentcover is
FUNCTION newDocument return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.newDocument() returns long';
PROCEDURE setDoctype(id number, rootname varchar2,
                     sysid varchar2, pubid varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.setDoctype(long,
                java.lang.String, java.lang.String, java.lang.String)';
FUNCTION getDocType(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.getDoctype(long) returns long';
FUNCTION getImplementation(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.getImplementation(long)
   returns int';
FUNCTION getDocumentElement(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.getDocumentElement(long)
   returns long';
FUNCTION createElement(id number, tagName varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.createElement(long, java.lang.String)
   returns long';
FUNCTION createDocumentFragment(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.createDocumentFragment(long)
   returns long';
FUNCTION createTextNode(id number, data varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.createTextNode(long, java.lang.String)
   returns long';
FUNCTION createComment(id number, data varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.createComment(long, java.lang.String)
   returns long';
FUNCTION createCDATASection(id number, data varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.createCDATASection(long, java.lang.String)
   returns long';
FUNCTION createProcessingInstruction(id number, target varchar2,
                                     data varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.createProcessingInstruction(long, java.lang.String, java.lang.String) returns long';
FUNCTION createAttribute(id number, name varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.createAttribute(long, java.lang.String)
   returns long';
FUNCTION createEntityReference(id number, name varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.createEntityReference(long,
   java.lang.String)
   returns long';
FUNCTION getElementsByTagName(id number, name varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.getElementsByTagName(long, java.lang.String)
    returns long';
FUNCTION importNode(id number, src number, deep number,
                    err in out number) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.importNode(long, long, boolean,
int[]) returns long';
FUNCTION adoptNode(id number, src number, err in out number) return number
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.adoptNode(long, long, int[])
returns long';
FUNCTION getVersion(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.getVersion(long) returns java.lang.String';
PROCEDURE setVersion(id number, version varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.setVersion(long, java.lang.String)';
FUNCTION getCharset(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.getCharset(long) returns java.lang.String';
PROCEDURE setCharset(id number, charset varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.setCharset(long, java.lang.String)';
FUNCTION getStandalone(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.getStandalone(long)
      returns java.lang.String';
PROCEDURE setStandalone(id number, value varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.setStandalone(long, java.lang.String)';
PROCEDURE writeToFile(id number, fileName varchar2, charset varchar2,
                      err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.writeToFile(long, java.lang.String,
                                   java.lang.String, java.lang.String[])';
PROCEDURE writeToBuffer(id number, buffer in out varchar2,
                        charset varchar2, err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.writeToBuffer(long, oracle.sql.CHAR[],
                                     java.lang.String, java.lang.String[])';
PROCEDURE writeToClob(id number, cl in out clob,
                      charset varchar2, err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.writeToClob(long, oracle.sql.CLOB[],
                                   java.lang.String, java.lang.String[])';
PROCEDURE writeExternalDTDToFile(id number, fileName varchar2,
                                 charset varchar2, err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.writeExternalDTDToFile(long, java.lang.String,
                                   java.lang.String, java.lang.String[])';
PROCEDURE writeExternalDTDToBuffer(id number, buffer in out varchar2,
                                   charset varchar2, err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.writeExternalDTDToBuffer(long, oracle.sql.CHAR[],
                                     java.lang.String, java.lang.String[])';
PROCEDURE writeExternalDTDToClob(id number, cl in out clob,
                                 charset varchar2, err in out varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLDocumentCover.writeExternalDTDToClob(long, oracle.sql.CLOB[],
                                   java.lang.String, java.lang.String[])';
end;
/

