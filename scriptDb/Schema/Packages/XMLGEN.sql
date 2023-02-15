CREATE OR REPLACE package xmlgen authid current_user as
  ----------------
  -- constants relevant to put and get XML
  DEFAULT_ROWTAG      CONSTANT VARCHAR2(3) := 'ROW';         /* rowtag */
  -- used to signal that the DB encoding is to be used
  DB_ENCODING          CONSTANT VARCHAR2(1) := '_';
  ----------------
  -- constants relevant to getXML
  ALL_ROWS            CONSTANT NUMBER      := -1;               /* ALL ROWS  */
  SKIP_NONE           CONSTANT NUMBER      := 0;                /* SKIP NONE */
  NONE                CONSTANT NUMBER      := 0;                  /* NO META */
  DTD                 CONSTANT NUMBER      := 1;                 /* NEED DTD */
  SCHEMA              CONSTANT NUMBER      := 2;              /* NEED SCHEMA */
  NO_DTD              CONSTANT NUMBER      := NONE;      /* NO DTD; legacy */
  DEFAULT_ROWSETTAG   CONSTANT VARCHAR2(6) := 'ROWSET';      /* rowsettag */
  DEFAULT_ERRORTAG    CONSTANT VARCHAR2(5) := 'ERROR';      /* error tag */
  DEFAULT_ROWIDATTR   CONSTANT VARCHAR2(3) := 'NUM';         /* Row id attr */
  -----------------
  -- constants relevant to insertXML/deleteXML/updateXML
  MATCH_CASE          CONSTANT NUMBER       := 0;               /* match case*/
  IGNORE_CASE         CONSTANT NUMBER       := 1;             /* ignore case */
  DEFAULT_DATE_FORMAT CONSTANT VARCHAR2(21) := 'MM/dd/yyyy HH:mm:ss';
  --------------------------------------------
  -- get and put xml relevant methods
  lobDuration  NUMBER :=  DBMS_LOB.SESSION;         /* local variable */
                                              /* default durations for lobs */
  PROCEDURE setRowTag(tag IN VARCHAR2 );
  PROCEDURE resetOptions;
  --------------------------------------------
  -- getXML relevant methods
  FUNCTION  getXML(query IN VARCHAR2, metaType IN NUMBER := NONE) RETURN CLOB;
  PROCEDURE getXML(query IN VARCHAR2, metaType IN NUMBER, xmlCLob IN CLOB);
  FUNCTION  getXML(query IN CLOB, metaType IN NUMBER := NONE) RETURN CLOB;
  PROCEDURE getXML(query IN CLOB, metaType IN NUMBER, xmlClob IN CLOB);
  -- functions and procedures to extract the DTD information
  FUNCTION  getDTD(query IN VARCHAR2) RETURN CLOB;
  FUNCTION  getDTD(query IN VARCHAR2, withVer IN BOOLEAN) RETURN CLOB;
  PROCEDURE getDTD(query IN VARCHAR2, withVer IN BOOLEAN, xmlClob IN CLOB);
  FUNCTION  getDTD(query IN CLOB) RETURN CLOB;
  FUNCTION  getDTD(query IN CLOB, withVer IN BOOLEAN) RETURN CLOB;
  PROCEDURE getDTD(query IN CLOB, withVer IN BOOLEAN, xmlClob IN CLOB);
  -- changes the row and rowset tag settings.
  PROCEDURE setRowSetTag(tag IN VARCHAR2 );
  PROCEDURE setErrorTag(tag IN VARCHAR2);
  PROCEDURE setRowIdAttrName(tag IN VARCHAR2);
  PROCEDURE setRowIdColumn(columnname IN VARCHAR2);
  PROCEDURE setCollIdAttr(attrname IN VARCHAR2);
  PROCEDURE useNullAttributeIndicator(flag IN BOOLEAN);
  PROCEDURE setQueryDateFormat(mask IN VARCHAR2);
  -- case for tag names..
  PROCEDURE useUpperCaseTagNames;
  PROCEDURE useLowerCaseTagNames;
  PROCEDURE useDefaultCaseTagNames;
  PROCEDURE setMaxRows(rows IN NUMBER);
  PROCEDURE setSkipRows(rows IN NUMBER);
  PROCEDURE setStylesheetType(type in VARCHAR2);
  PROCEDURE setStylesheet(uri IN VARCHAR2);
  PROCEDURE setStylesheet(uri IN VARCHAR2, type IN VARCHAR2);
  PROCEDURE setEncodingTag(enc IN VARCHAR2 := DB_ENCODING);
  PROCEDURE setLobDuration(duration IN NUMBER);
  -- bind values for SQL queries..
  PROCEDURE setBindValue(bName IN VARCHAR2, bValue IN VARCHAR2);
  PROCEDURE clearBindValues;
  -- header information to be appended to the result..
  -- metaheader refers to DTD headers.
  PROCEDURE setMetaHeader(header IN CLOB := null);
  -- data header portions appended to the data portion of the result..
  PROCEDURE setDataHeader(header IN CLOB := null, docTag IN VARCHAR2 := null);
  -- exception handling..
  PROCEDURE setRaiseException(flag IN BOOLEAN);
  PROCEDURE propagateOriginalException(flag IN BOOLEAN);
  PROCEDURE getExceptionContent(errNo OUT NUMBER, errMsg OUT VARCHAR2);
  --------------------------------------------------------------------------
  ----- INSERT/UPDATE/DELETE routines.
  PROCEDURE setIgnoreTagCase(ignore IN NUMBER);
  PROCEDURE setDateFormat(dateFormat IN VARCHAR2);
  -- set the columns to update. Relevant for insert and update routines..
  PROCEDURE setUpdateColumn(colName IN VARCHAR2);
  PROCEDURE clearUpdateColumnList;
  -- set the key column name to be used for updates and deletes.
  PROCEDURE setKeyColumn(colName IN VARCHAR2);
  PROCEDURE clearKeyColumnList;
  -- set the batch size for executing update, insert and delete routines..
  PROCEDURE setBatchSize(batchSize IN NUMBER);
  PROCEDURE setCommitBatch(batchSize IN NUMBER);
  -- insertXML relevant methods
  FUNCTION  insertXML(tablename IN VARCHAR2, xmldoc IN VARCHAR2) RETURN NUMBER;
  FUNCTION  insertXML(tablename IN VARCHAR2, xmldoc IN CLOB) RETURN NUMBER;
  -- updateXML relevant routines..
  FUNCTION  updateXML(tablename IN VARCHAR2, xmldoc IN VARCHAR2) RETURN NUMBER;
  FUNCTION  updateXML(tablename IN VARCHAR2, xmldoc IN CLOB) RETURN NUMBER;
  -- updateXML relevant routines..
  FUNCTION  deleteXML(tablename IN VARCHAR2, xmldoc IN VARCHAR2) RETURN NUMBER;
  FUNCTION  deleteXML(tablename IN VARCHAR2, xmldoc IN CLOB) RETURN NUMBER;
  -------private method declarations------------------------------------------
  -- we must do this as a bug workaround; otherwise we get ora-600 [kgmexchi11]
  PROCEDURE p_useNullAttrInd(flag IN number);
  PROCEDURE p_propOriginalExc(flag IN number);
  PROCEDURE p_setRaiseException(flag IN number);
  PROCEDURE p_setEncodingTag(enc IN VARCHAR2);
  PROCEDURE p_setMetaHeader(header IN CLOB);
  PROCEDURE p_setDataHeader(header IN CLOB, docTag IN VARCHAR2);
  PROCEDURE p_getDTD(query IN VARCHAR2, metaType IN NUMBER, withVer IN NUMBER, xmlClob IN CLOB);
  PROCEDURE p_getDTD(query IN CLOB, metaType IN NUMBER, withVer IN NUMBER, xmlClob IN CLOB);
end;
/

