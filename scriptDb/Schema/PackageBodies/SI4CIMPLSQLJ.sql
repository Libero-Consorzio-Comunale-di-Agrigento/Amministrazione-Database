CREATE OR REPLACE PACKAGE BODY Si4cimPlSqlJ AS
 PROCEDURE addBcc
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addBcc
      ( java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String)';
 PROCEDURE addCc
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addCc
      ( java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String)';
 PROCEDURE addDataAttachment
      ( dbAlias IN VARCHAR2, dbDriver IN VARCHAR2, dbConnect IN VARCHAR2
       , dbUser IN VARCHAR2, dbEncPwd IN VARCHAR2, tableName IN VARCHAR2
       , field IN VARCHAR2, whereCondition IN VARCHAR2
       , dataType IN VARCHAR2, fileName IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addDataAttachment
      ( java.lang.String, java.lang.String, java.lang.String
      , java.lang.String, java.lang.String, java.lang.String
      , java.lang.String, java.lang.String, java.lang.String
      , java.lang.String)';
 PROCEDURE addDataAttachment
      ( dbAlias IN VARCHAR2, dbDriver IN VARCHAR2, dbConnect IN VARCHAR2
       , dbUser IN VARCHAR2, dbEncPwd IN VARCHAR2, tableName IN VARCHAR2
       , field IN VARCHAR2, whereCondition IN VARCHAR2
       , dataType IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addDataAttachment
      ( java.lang.String, java.lang.String, java.lang.String
      , java.lang.String, java.lang.String, java.lang.String
      , java.lang.String, java.lang.String, java.lang.String
      , java.lang.String, int)';
 PROCEDURE addFileAttachment
      ( pathName IN VARCHAR2, fileName IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addFileAttachment
      ( java.lang.String, java.lang.String)';
 PROCEDURE addFileAttachment
      ( pathName IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addFileAttachment
      ( java.lang.String, java.lang.String, int)';
 PROCEDURE addNotifyTo
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2, message IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addNotifyTo
      ( java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String)';
 PROCEDURE addRecipient
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addRecipient
      ( java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String)';
 PROCEDURE addReplyTo
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addReplyTo
      ( java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String)';
 PROCEDURE addTextAttachment
      ( textContent IN VARCHAR2, fileName IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addTextAttachment
      ( java.lang.String, java.lang.String)';
 PROCEDURE addTextAttachment
      ( textContent IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addTextAttachment
      ( java.lang.String, java.lang.String, int)';
 PROCEDURE addUrlAttachment
      ( urlStr IN VARCHAR2, fileName IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addUrlAttachment
      ( java.lang.String, java.lang.String)';
 PROCEDURE addUrlAttachment
      ( urlStr IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.addUrlAttachment
      ( java.lang.String, java.lang.String, int)';
 FUNCTION getMessageType RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getMessageType
      () return java.lang.String';
 FUNCTION getLastMessageDbIndex RETURN NUMBER
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getLastMessageDbIndex
      ( ) return int';
 FUNCTION getHostUserInfos RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getHostUserInfos
      ( ) return java.lang.String';
 FUNCTION getContentForTag(tag IN VARCHAR2) RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getContentForTag
      ( java.lang.String) return java.lang.String';
 FUNCTION getFileSeparator RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getFileSeparator
      ( ) return java.lang.String';
 FUNCTION getJavaLibraryPath RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getJavaLibraryPath
      ( ) return java.lang.String';
 FUNCTION getPathSeparator RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getPathSeparator
      ( ) return java.lang.String';
 FUNCTION getSearchFile RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getSearchFile
      ( ) return java.lang.String';
 FUNCTION getSearchPath RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getSearchPath
      ( ) return java.lang.String';
 FUNCTION getSi4cimContent RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getSi4cimContent
      ( ) return java.lang.String';
 FUNCTION getSi4cimKeys( keySeparator IN VARCHAR2) RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getSi4cimKeys
      ( java.lang.String) return java.lang.String';
 FUNCTION getSi4cimTags( tagSeparator IN VARCHAR2) RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getSi4cimTags
      ( java.lang.String) return java.lang.String';
 FUNCTION getSi4cimValues(valueSeparator IN VARCHAR2)RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getSi4cimValues
      ( java.lang.String) return java.lang.String';
 FUNCTION getUserHome RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getUserHome
      ( ) return java.lang.String';
 FUNCTION getValueForTagKey(tag IN VARCHAR2, key IN VARCHAR2) RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.getValueForTagKey
      ( java.lang.String, java.lang.String) return java.lang.String';
 FUNCTION initializeMessage
      ( pMessageType IN VARCHAR2) RETURN NUMBER
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.initializeMessage
      ( java.lang.String) return int';
 FUNCTION send RETURN NUMBER
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.send
      ( ) return int';
 PROCEDURE setBanner
      ( banner IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setBanner
      ( java.lang.String)';
 PROCEDURE setCoverPage
      ( coverPage IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setCoverPage
      ( java.lang.String)';
 PROCEDURE setCsTag
      ( csTag IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setCsTag
      ( java.lang.String)';
 PROCEDURE setDate
      ( pDate IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setDate
      ( java.lang.String)';
 PROCEDURE setParam
      ( pKey IN VARCHAR2, pValue IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setParam
      ( java.lang.String, java.lang.String)';
 PROCEDURE setPriority
      ( priority IN NUMBER)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setPriority
      ( int)';
 PROCEDURE setProject
      ( project IN VARCHAR2, module IN VARCHAR2, phase IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setProject
      ( java.lang.String, java.lang.String, java.lang.String)';
 PROCEDURE setResolution
      ( resolution IN NUMBER)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setResolution
      ( int)';
 PROCEDURE setReturnReceipt
      ( returnReceipt IN NUMBER)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setReturnReceipt
      ( int)';
 PROCEDURE setSender
      ( senderIP IN VARCHAR2, senderName IN VARCHAR2, ID IN VARCHAR2
       , name IN VARCHAR2, company IN VARCHAR2, address IN VARCHAR2
       , code IN VARCHAR2, city IN VARCHAR2, province IN VARCHAR2
       , state IN VARCHAR2, email IN VARCHAR2, phoneNumber IN VARCHAR2
       , faxNumber IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setSender
      ( java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String)';
 PROCEDURE setSender
      ( senderIP IN VARCHAR2, senderName IN VARCHAR2, ID IN VARCHAR2
       , name IN VARCHAR2, company IN VARCHAR2, address IN VARCHAR2
       , code IN VARCHAR2, city IN VARCHAR2, province IN VARCHAR2
       , state IN VARCHAR2, email IN VARCHAR2, phoneNumber IN VARCHAR2
       , faxNumber IN VARCHAR2, user IN VARCHAR2, password IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setSender
      ( java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String
       , java.lang.String, java.lang.String, java.lang.String)';
 PROCEDURE setSubject
      ( subject IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setSubject
      ( java.lang.String)';
 PROCEDURE setText
      ( text IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setText
      ( java.lang.String)';
 PROCEDURE setTime
      ( pTime IN VARCHAR2)
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.setTime
      ( java.lang.String)';
 FUNCTION versione RETURN VARCHAR2
 AS LANGUAGE JAVA NAME 'it.finmatica.cim.plsqlj.Si4cimPlSqlJ.versione
      ( ) return java.lang.String';
 END Si4cimPlSqlJ;
/

