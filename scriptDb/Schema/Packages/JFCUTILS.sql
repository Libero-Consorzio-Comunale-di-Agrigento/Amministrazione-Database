CREATE OR REPLACE PACKAGE JFCUTILS AS
--
-- versione e' comune per tutte le JfcUtilsLite
--
FUNCTION versione
RETURN VARCHAR2;
--
-- metodi per il package it.finmatica.jfc.ftp
--
FUNCTION FTP_AppendBinaryFile(localFile IN VARCHAR2, remoteFile IN VARCHAR2, remoteHost IN VARCHAR2, remoteUser IN VARCHAR2, remotePwd IN VARCHAR2)
RETURN NUMBER;
FUNCTION FTP_RenameFile(oldFile IN VARCHAR2, newFile IN VARCHAR2, remoteHost IN VARCHAR2, remoteUser IN VARCHAR2, remotePwd IN VARCHAR2)
RETURN NUMBER;
--
-- metodi per il package it.finmatica.jfc.io
--
PROCEDURE IOFILEDB_copy(tableNameQuery IN VARCHAR2, fieldNameQuery IN VARCHAR2, whereClauseQuery IN VARCHAR2, tableNameUpdate IN VARCHAR2, fieldNameUpdate IN VARCHAR2, whereClauseUpdate IN VARCHAR2, dbUser IN VARCHAR2, dbPwdCrypted IN VARCHAR2, dbServer IN VARCHAR2);
PROCEDURE copy(tableNameQuery IN VARCHAR2, fieldNameQuery IN VARCHAR2, whereClauseQuery IN VARCHAR2, tableNameUpdate IN VARCHAR2, fieldNameUpdate IN VARCHAR2, whereClauseUpdate IN VARCHAR2, dbUser IN VARCHAR2, dbPwdCrypted IN VARCHAR2, dbServer IN VARCHAR2);
PROCEDURE copyfile(tableNameQuery IN VARCHAR2, fieldNameQuery IN VARCHAR2, whereClauseQuery IN VARCHAR2, dbUser IN VARCHAR2, dbPwdCrypted IN VARCHAR2, dbServer IN VARCHAR2, filename IN VARCHAR2);
--
-- metodi per il package it.finmatica.jfc.utility
--
PROCEDURE fillTableWithFileList(dirName IN VARCHAR2, filter IN VARCHAR2, field IN VARCHAR2, tablename IN VARCHAR2);
END JFCUTILS;
/

