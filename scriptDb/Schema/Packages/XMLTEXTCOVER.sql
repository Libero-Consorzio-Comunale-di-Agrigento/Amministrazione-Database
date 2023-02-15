CREATE OR REPLACE package xmltextcover is
FUNCTION splitText(id number, offset number, err in out number)
return number
is language java
name 'oracle.xml.parser.plsql.XMLTextCover.splitText(long, int, int[])
returns long';
end;
/

