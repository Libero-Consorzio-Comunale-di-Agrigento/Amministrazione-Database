CREATE OR REPLACE PACKAGE BODY xmlgen AS
  FUNCTION insertXML(tableName in varchar2, xmlDoc in varchar2) return number
  as LANGUAGE JAVA NAME
   'OracleXMLStore.insertXML(java.lang.String,java.lang.String) return int';
  FUNCTION insertXML(tableName in varchar2, xmlDoc in CLOB) return number
  as LANGUAGE JAVA NAME
   'OracleXMLStore.insertXML(java.lang.String,oracle.sql.CLOB) return int';
  FUNCTION updateXML(tableName in varchar2, xmlDoc in varchar2) return number
  as LANGUAGE JAVA NAME
   'OracleXMLStore.updateXML(java.lang.String,java.lang.String) return int';
  FUNCTION updateXML(tableName in varchar2, xmlDoc in CLOB) return number
  as LANGUAGE JAVA NAME
   'OracleXMLStore.updateXML(java.lang.String,oracle.sql.CLOB) return int';
  FUNCTION deleteXML(tableName in varchar2, xmlDoc in varchar2) return number
  as LANGUAGE JAVA NAME
   'OracleXMLStore.deleteXML(java.lang.String,java.lang.String) return int';
  FUNCTION deleteXML(tableName in varchar2, xmlDoc in CLOB) return number
  as LANGUAGE JAVA NAME
   'OracleXMLStore.deleteXML(java.lang.String,oracle.sql.CLOB) return int';
  -- set the columns to update. Relevant for insert and update routines..
  PROCEDURE setUpdateColumn(colName IN VARCHAR2)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.setUpdateColumn(java.lang.String)';
  PROCEDURE clearUpdateColumnList
  as LANGUAGE JAVA NAME
   'OracleXMLStore.clearUpdateColumnList()';
  -- set the key column name to be used for updates and deletes.
  PROCEDURE setKeyColumn(colName IN VARCHAR2)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.setKeyColumn(java.lang.String)';
  PROCEDURE clearKeyColumnList
   as LANGUAGE JAVA NAME
   'OracleXMLStore.clearKeyColumnList()';
  PROCEDURE setRowsetTag(tag VARCHAR2 )
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setRowsetTag(java.lang.String)';
  PROCEDURE setRowTag(tag VARCHAR2 )
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setRowTag(java.lang.String)';
  PROCEDURE setRowIdAttrName(tag VARCHAR2)
  AS LANGUAGE JAVA NAME
  'OracleXMLStore.setRowIdAttrName(java.lang.String)';
  PROCEDURE setRowIdColumn(columnName IN VARCHAR2)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setRowIdColumn(java.lang.String)';
  PROCEDURE setCollIdAttr(attrname VARCHAR2)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setCollIdAttr(java.lang.String)';
  PROCEDURE p_useNullAttrInd(flag IN number)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.useNullAttributeIndicator(int)';
  PROCEDURE useNullAttributeIndicator(flag IN BOOLEAN) is
  begin
    if flag = true then
      p_useNullAttrInd(1);
    else
      p_useNullAttrInd(0);
    end if;
  end;
  PROCEDURE setQueryDateFormat(mask IN VARCHAR2)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.setQueryDateFormat(java.lang.String)';
  PROCEDURE p_propOriginalExc(flag IN number)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.propagateOriginalException(int)';
  PROCEDURE propagateOriginalException(flag IN BOOLEAN) is
  begin
    if flag = true then
      p_propOriginalExc(1);
    else
      p_propOriginalExc(0);
    end if;
  end;
  PROCEDURE p_setRaiseException(flag IN number)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setRaiseException(int)';
  PROCEDURE setRaiseException(flag IN BOOLEAN) is
  begin
    if flag = true then
      p_setRaiseException(1);
    else
      p_setRaiseException(0);
    end if;
  end;
  PROCEDURE useUpperCaseTagNames
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.useUpperCaseTagNames()';
  PROCEDURE useLowerCaseTagNames
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.useLowerCaseTagNames()';
  PROCEDURE useDefaultCaseTagNames
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.useDefaultCaseTagNames()';
  PROCEDURE setIgnoreTagCase(ignore IN number)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.ignoreTagCase(int)';
  PROCEDURE setErrorTag(tag VARCHAR2 )
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setErrorTag(java.lang.String)';
  PROCEDURE setMaxRows(rows NUMBER)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setMaxRows(int)';
  PROCEDURE setSkipRows(rows NUMBER)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setSkipRows(int)';
  PROCEDURE setDateFormat(dateFormat IN varchar2)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setDateFormat(java.lang.String)';
  PROCEDURE setBatchSize(batchSize IN number)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setBatchSize(int)';
  PROCEDURE setCommitBatch(batchSize IN number)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setCommitBatch(int)';
  PROCEDURE setStylesheetType(type VARCHAR2)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setStylesheetHeaderType(java.lang.String)';
  PROCEDURE setStylesheet( uri VARCHAR2)
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.setStylesheetHeader(java.lang.String)';
  PROCEDURE setStylesheet( uri VARCHAR2, type VARCHAR2) IS
  begin
    setStylesheet(uri);
    setStylesheetType(type);
  end;
  PROCEDURE p_setEncodingTag(enc IN VARCHAR2)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.setEncodingTag(java.lang.String)';
  PROCEDURE setEncodingTag(enc IN VARCHAR2 := DB_ENCODING) is
  begin
    p_setEncodingTag(enc);
  end;
  PROCEDURE setLobDuration(duration IN NUMBER) is
  begin
    lobDuration := duration ;
  end;
  PROCEDURE resetOptions
  AS LANGUAGE JAVA NAME
   'OracleXMLStore.resetOptions()';
  PROCEDURE getExceptionContent(errNo OUT NUMBER, errMsg OUT VARCHAR2)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.getExceptionContent(int[], java.lang.String[])';
  FUNCTION getXML(query IN VARCHAR2,metaType IN NUMBER := NONE) return CLOB is
   clb CLOB;
  begin
    dbms_lob.createtemporary(clb, true, lobDuration);
    getXML(query, metaType, clb);
    return clb;
  end;
  FUNCTION getXML(query IN CLOB, metaType IN NUMBER := NONE) return CLOB is
   clb CLOB;
  begin
    dbms_lob.createtemporary(clb, true, lobDuration);
    getXML(query, metaType, clb);
    return clb;
  end;
  PROCEDURE getXML(query IN CLOB, metaType IN NUMBER, xmlClob IN CLOB)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.getXML(oracle.sql.CLOB, int, oracle.sql.CLOB)';
  PROCEDURE getXML(query IN VARCHAR2, metaType IN NUMBER, xmlClob IN CLOB)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.getXML(java.lang.String, int, oracle.sql.CLOB)';
  PROCEDURE setBindValue(bName IN VARCHAR2, bValue IN VARCHAR2)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.bindValue(java.lang.String, java.lang.String)';
  PROCEDURE clearBindValues
  as LANGUAGE JAVA NAME
   'OracleXMLStore.clearBindValues()';
  PROCEDURE p_setMetaHeader(header IN CLOB)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.setMetaHeader(oracle.sql.CLOB)';
  PROCEDURE setMetaHeader(header IN CLOB := null) is
  begin
    p_setMetaHeader(header);
  end;
  PROCEDURE p_setDataHeader(header IN CLOB, docTag IN VARCHAR2)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.setDataHeader(oracle.sql.CLOB, java.lang.String)';
  PROCEDURE setDataHeader(header IN CLOB := null,
          docTag IN VARCHAR2 := null)  IS
  begin
    p_setDataHeader(header, docTag);
  end;
  PROCEDURE p_getDTD(query IN VARCHAR2, metaType IN NUMBER,
         withVer IN NUMBER, xmlClob IN CLOB)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.getXMLMetaData(java.lang.String, int, int,oracle.sql.CLOB)';
  FUNCTION getDTD(query IN VARCHAR2) RETURN CLOB is
  begin
   return getDTD(query, false);
  end;
  FUNCTION getDTD(query IN VARCHAR2, withVer IN BOOLEAN) RETURN CLOB IS
   clb CLOB;
  begin
    dbms_lob.createtemporary(clb,true,lobDuration);
    getDTD(query, withVer, clb);
    return clb;
  end;
  PROCEDURE getDTD(query IN VARCHAR2, withVer IN BOOLEAN, xmlCLob IN CLOB) is
   a integer;
  begin
    if withVer = true then
      a:= 1;
    else
      a:=0;
    end if;
    p_getDTD(query,DTD,a,xmlCLob);
  end;
  PROCEDURE p_getDTD(query IN CLOB, metaType IN NUMBER,
         withVer IN NUMBER, xmlClob IN CLOB)
  as LANGUAGE JAVA NAME
   'OracleXMLStore.getXMLMetaData(oracle.sql.CLOB, int, int, oracle.sql.CLOB)';
  PROCEDURE getDTD(query IN CLOB, withVer IN BOOLEAN, xmlCLob IN CLOB) is
   a integer;
  begin
    if withVer = true then
     a := 1;
    else
     a := 0;
    end if;
    p_getDTD(query,DTD,a,xmlCLob);
  end;
  FUNCTION getDTD(query IN CLOB) RETURN CLOB IS
  begin
    return getDTD(query, false);
  end;
  FUNCTION getDTD(query IN CLOB, withVer IN BOOLEAN) RETURN CLOB IS
    clb CLOB;
  begin
    dbms_lob.createtemporary(clb,true,lobDuration);
    getDTD(query,withVer,clb);
    return clb;
  end;
end xmlgen;
/

