CREATE OR REPLACE package xmlnodelistcover is
FUNCTION getLength(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeListCover.getLength(long) returns int';
FUNCTION item(id number, idx number) return number
is language java
name 'oracle.xml.parser.plsql.XMLNodeListCover.item(long, int) returns long';
end;
/

