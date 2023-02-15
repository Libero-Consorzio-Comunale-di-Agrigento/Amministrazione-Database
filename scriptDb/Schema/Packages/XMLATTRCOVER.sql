CREATE OR REPLACE package xmlattrcover is
FUNCTION getName(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLAttrCover.getName(long) returns java.lang.String';
FUNCTION getQualifiedName(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLAttrCover.getQualifiedName(long)
   returns java.lang.String';
FUNCTION getNamespace(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLAttrCover.getNamespace(long) returns java.lang.String';
FUNCTION getLocalName(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLAttrCover.getLocalName(long) returns java.lang.String';
FUNCTION getExpandedName(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLAttrCover.getExpandedName(long)
   returns java.lang.String';
FUNCTION getValue(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLAttrCover.getValue(long) returns java.lang.String';
PROCEDURE setValue(id number, value varchar2)
is language java
name 'oracle.xml.parser.plsql.XMLAttrCover.setValue(long, java.lang.String)';
FUNCTION getSpecified(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLAttrCover.getSpecified(long) returns boolean';
end;
/

