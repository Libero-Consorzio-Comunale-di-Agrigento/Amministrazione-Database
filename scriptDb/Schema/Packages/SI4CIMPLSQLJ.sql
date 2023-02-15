CREATE OR REPLACE PACKAGE Si4cimPlSqlJ AS
 PROCEDURE addBcc
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2);
 PROCEDURE addCc
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2);
 PROCEDURE addDataAttachment
      ( dbAlias IN VARCHAR2, dbDriver IN VARCHAR2, dbConnect IN VARCHAR2
       , dbUser IN VARCHAR2, dbEncPwd IN VARCHAR2, tableName IN VARCHAR2
       , field IN VARCHAR2, whereCondition IN VARCHAR2
       , dataType IN VARCHAR2, fileName IN VARCHAR2);
 PROCEDURE addDataAttachment
      ( dbAlias IN VARCHAR2, dbDriver IN VARCHAR2, dbConnect IN VARCHAR2
       , dbUser IN VARCHAR2, dbEncPwd IN VARCHAR2, tableName IN VARCHAR2
       , field IN VARCHAR2, whereCondition IN VARCHAR2
       , dataType IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER);
 PROCEDURE addFileAttachment( pathName IN VARCHAR2, fileName IN VARCHAR2);
 PROCEDURE addFileAttachment( pathName IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER);
 PROCEDURE addNotifyTo
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2, message IN VARCHAR2);
 PROCEDURE addRecipient
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2);
 PROCEDURE addReplyTo
      ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
       , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
       , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
       , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2);
 PROCEDURE addTextAttachment( textContent IN VARCHAR2, fileName IN VARCHAR2);
 PROCEDURE addTextAttachment( textContent IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER);
 PROCEDURE addUrlAttachment( urlStr IN VARCHAR2, fileName IN VARCHAR2);
 PROCEDURE addUrlAttachment( urlStr IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER);
 FUNCTION getMessageType RETURN VARCHAR2;
 FUNCTION getLastMessageDbIndex RETURN NUMBER;
 FUNCTION getHostUserInfos RETURN VARCHAR2;
 FUNCTION getContentForTag(tag IN VARCHAR2) RETURN VARCHAR2;
 FUNCTION getFileSeparator RETURN VARCHAR2;
 FUNCTION getJavaLibraryPath RETURN VARCHAR2;
 FUNCTION getPathSeparator RETURN VARCHAR2;
 FUNCTION getSearchFile RETURN VARCHAR2;
 FUNCTION getSearchPath RETURN VARCHAR2;
 FUNCTION getSi4cimContent RETURN VARCHAR2;
 FUNCTION getSi4cimKeys( keySeparator IN VARCHAR2) RETURN VARCHAR2;
 FUNCTION getSi4cimTags( tagSeparator IN VARCHAR2) RETURN VARCHAR2;
 FUNCTION getSi4cimValues(valueSeparator IN VARCHAR2)RETURN VARCHAR2;
 FUNCTION getUserHome RETURN VARCHAR2;
 FUNCTION getValueForTagKey(tag IN VARCHAR2, key IN VARCHAR2) RETURN VARCHAR2;
 FUNCTION initializeMessage( pMessageType IN VARCHAR2) RETURN NUMBER;
 FUNCTION send RETURN NUMBER;
 PROCEDURE setBanner( banner IN VARCHAR2);
 PROCEDURE setCoverPage( coverPage IN VARCHAR2);
 PROCEDURE setCsTag( csTag IN VARCHAR2);
 PROCEDURE setDate( pDate IN VARCHAR2);
 PROCEDURE setParam( pKey IN VARCHAR2, pValue IN VARCHAR2);
 PROCEDURE setPriority( priority IN NUMBER);
 PROCEDURE setProject( project IN VARCHAR2, module IN VARCHAR2, phase IN VARCHAR2);
 PROCEDURE setResolution( resolution IN NUMBER);
 PROCEDURE setReturnReceipt( returnReceipt IN NUMBER);
 PROCEDURE setSender
      ( senderIP IN VARCHAR2, senderName IN VARCHAR2, ID IN VARCHAR2
       , name IN VARCHAR2, company IN VARCHAR2, address IN VARCHAR2
       , code IN VARCHAR2, city IN VARCHAR2, province IN VARCHAR2
       , state IN VARCHAR2, email IN VARCHAR2, phoneNumber IN VARCHAR2
       , faxNumber IN VARCHAR2);
 PROCEDURE setSender
      ( senderIP IN VARCHAR2, senderName IN VARCHAR2, ID IN VARCHAR2
       , name IN VARCHAR2, company IN VARCHAR2, address IN VARCHAR2
       , code IN VARCHAR2, city IN VARCHAR2, province IN VARCHAR2
       , state IN VARCHAR2, email IN VARCHAR2, phoneNumber IN VARCHAR2
       , faxNumber IN VARCHAR2, user IN VARCHAR2, password IN VARCHAR2);
 PROCEDURE setSubject( subject IN VARCHAR2);
 PROCEDURE setText( text IN VARCHAR2);
 PROCEDURE setTime( pTime IN VARCHAR2);
 FUNCTION versione RETURN VARCHAR2;
END Si4cimPlSqlJ;
/

