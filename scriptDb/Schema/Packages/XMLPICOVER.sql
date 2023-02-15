CREATE OR REPLACE package xmlpicover is
FUNCTION getTarget(id number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLPICover.getTarget(long) returns java.lang.String';
FUNCTION getData(id number, err in out number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLPICover.getData(long, int[])
returns java.lang.String';
PROCEDURE setData(id number, data varchar2, err in out number)
is language java
name 'oracle.xml.parser.plsql.XMLPICover.setData(long, java.lang.String,
                         int[])';
end;
/

