--liquibase formatted sql

--changeset mturra:201901301231_292 runOnChange:true stripComments:false endDelimiter:/


create or replace package xmlchardatacover is
FUNCTION getData(id number, err in out number) return varchar2
is language java
name 'oracle.xml.parser.plsql.XMLCharDataCover.getData(long, int[])
returns java.lang.String';
PROCEDURE setData(id number, data varchar2, err in out number)
is language java
name 'oracle.xml.parser.plsql.XMLCharDataCover.setData(long, java.lang.String,
                               int[])';
FUNCTION getLength(id number) return number
is language java
name 'oracle.xml.parser.plsql.XMLCharDataCover.getLength(long) returns int';
FUNCTION substringData(id number, offset number, count number,
                       err in out number)
   return varchar2 is language java
name 'oracle.xml.parser.plsql.XMLCharDataCover.substringData(long, int, int,
                                     int[])
   returns java.lang.String';
PROCEDURE appendData(id number, arg varchar2, err in out number)
is language java
name 'oracle.xml.parser.plsql.XMLCharDataCover.appendData(long, java.lang.String,
                                  int[])';
PROCEDURE insertData(id number, offset number, arg varchar2,
                     err in out number)
is language java
name 'oracle.xml.parser.plsql.XMLCharDataCover.insertData(long, int, java.lang.String,
                                  int[])';
PROCEDURE deleteData(id number, offset number, count number,
                     err in out number)
is language java
name 'oracle.xml.parser.plsql.XMLCharDataCover.deleteData(long, int, int,
                                  int[])';
PROCEDURE replaceData(id number, offset number, count number, arg varchar2,
                      err in out number)
is language java
name 'oracle.xml.parser.plsql.XMLCharDataCover.replaceData(long, int, int,
                                   java.lang.String, int[])';
end;
/