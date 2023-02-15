CREATE OR REPLACE package xmlnnmcover is
FUNCTION getNamedItem(id number, name varchar2) return number
is language java
name 'oracle.xml.parser.plsql.XMLNNMCover.getNamedItem(long, java.lang.String)
   returns long';
FUNCTION setNamedItem(id number, arg number, err in out number)
return number
is language java
name 'oracle.xml.parser.plsql.XMLNNMCover.setNamedItem(long, long,
                               int[])
returns long';
FUNCTION removeNamedItem(id number, name varchar2, err in out number)
return number
is language java
name 'oracle.xml.parser.plsql.XMLNNMCover.removeNamedItem(long,
        java.lang.String, int[]) returns long';
FUNCTION item(id number, idx number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNNMCover.item(long, int) returns long';
FUNCTION getLength(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNNMCover.getLength(long) returns int';
end;
/

