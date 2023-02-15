CREATE OR REPLACE package xslprocessorcover is
FUNCTION newProcessor return number is language java
name 'oracle.xml.parser.plsql.XSLProcessorCover.newProcessor() returns int';
PROCEDURE freeProcessor(id number) is language java
name 'oracle.xml.parser.plsql.XSLProcessorCover.freeProcessor(int)';
FUNCTION processXSLFromDoc(id number, stylesheet number, xmldoc number, err in out varchar2) return number is language java name 'oracle.xml.parser.plsql.XSLProcessorCover.processXSLFromDoc(int, int, long, java.lang.String[]) returns long';
FUNCTION processXSLFromDoc(id number, stylesheet number, url varchar2, err in out varchar2) return number is language java name 'oracle.xml.parser.plsql.XSLProcessorCover.processXSLFromDoc(int, int, java.lang.String, java.lang.String[]) returns long';
PROCEDURE processXSLFromDocToFile(id number, stylesheet number, xmldoc number, dir varchar2, filename varchar2, err in out varchar2) is language java name 'oracle.xml.parser.plsql.XSLProcessorCover.processXSLFromDocToFile(int, int, long, java.lang.String, java.lang.String, java.lang.String[])';
PROCEDURE processXSLFromDocToFile(id number, stylesheet number, url varchar2, dir varchar2, filename varchar2, err in out varchar2) is language java name 'oracle.xml.parser.plsql.XSLProcessorCover.processXSLFromDocToFile(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String[])';
PROCEDURE processXSLFromDocToBuffer(id number, stylesheet number, xmldoc number, buffer in out varchar2, err in out varchar2) is language java name 'oracle.xml.parser.plsql.XSLProcessorCover.processXSLFromDocToBuffer(int, int, long, oracle.sql.CHAR[], java.lang.String[])';
PROCEDURE processXSLFromDocToClob(id number, stylesheet number, xmldoc number, cl in out clob, err in out varchar2) is language java name 'oracle.xml.parser.plsql.XSLProcessorCover.processXSLFromDocToClob(int, int, long, oracle.sql.CLOB[], java.lang.String[])';
FUNCTION processXSLFromDocFrag(id number, stylesheet number, xmldf number, err in out varchar2) return number is language java name 'oracle.xml.parser.plsql.XSLProcessorCover.processXSLFromDocFrag(int, int, long, java.lang.String[]) returns long';
PROCEDURE processXSLFromDocFragToFile(id number, stylesheet number, xmldoc number, dir varchar2, filename varchar2, err in out varchar2) is language java name 'oracle.xml.parser.plsql.XSLProcessorCover.processXSLFromDocFragToFile(int, int, long, java.lang.String, java.lang.String, java.lang.String[])';
PROCEDURE processXSLFromDocFragToBuffer(id number, stylesheet number, xmldoc number, buffer in out varchar2, err in out varchar2) is language java name 'oracle.xml.parser.plsql.XSLProcessorCover.processXSLFromDocFragToBuffer(int, int, long, oracle.sql.CHAR[], java.lang.String[])';
PROCEDURE processXSLFromDocFragToClob(id number, stylesheet number, xmldoc number, cl in out clob, err in out varchar2) is language java name 'oracle.xml.parser.plsql.XSLProcessorCover.processXSLFromDocFragToClob(int, int, long, oracle.sql.CLOB[], java.lang.String[])';
PROCEDURE setErrorLog(id number, fileName varchar2) is language java
name 'oracle.xml.parser.plsql.XSLProcessorCover.setErrorLog(int, java.lang.String)';
PROCEDURE showWarnings(id number, yes number) is language java
name 'oracle.xml.parser.plsql.XSLProcessorCover.showWarnings(int, boolean)';
end;
/

