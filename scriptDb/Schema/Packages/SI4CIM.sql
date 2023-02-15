CREATE OR REPLACE PACKAGE Si4cim IS /* MASTER_LINK */
s_implementazione varchar2(10);
s_messageType varchar2(30);
s_oggetto_messaggio VARCHAR2(4000); /* Porzione della riga di comando che indica l'oggetto del messaggio */
s_testo_messaggio VARCHAR2(4000);
s_name_recipient varchar2(4000);
s_address_recipient varchar2(4000);
s_progetto VARCHAR2(4000);
s_modulo varchar2(4000);
s_fase varchar2(4000);
FUNCTION versione RETURN VARCHAR2;
PROCEDURE addAttachment( newPathName IN VARCHAR2, newFileName IN VARCHAR2);
PROCEDURE addFileAttachment ( newPathName IN VARCHAR2, newFileName IN VARCHAR2);
PROCEDURE addAttachment( newpathName IN VARCHAR2, newfileName IN VARCHAR2, newrotation IN NUMBER);
PROCEDURE addFileAttachment ( newPathName IN VARCHAR2, newFileName IN VARCHAR2, newrotation IN NUMBER);
PROCEDURE addAttachment( dbAlias IN VARCHAR2, dbDriver IN VARCHAR2
, dbConnect IN VARCHAR2, dbUser IN VARCHAR2, dbEncPwd IN VARCHAR2
, newTableName IN VARCHAR2, newBlobField IN VARCHAR2
, newWhereCondition IN VARCHAR2 , newDataType IN VARCHAR2
, newFileName IN VARCHAR2);
PROCEDURE addDataAttachment( dbAlias IN VARCHAR2, dbDriver IN VARCHAR2
, dbConnect IN VARCHAR2, dbUser IN VARCHAR2, dbEncPwd IN VARCHAR2
, newTableName IN VARCHAR2, newBlobField IN VARCHAR2
, newWhereCondition IN VARCHAR2 , newDataType IN VARCHAR2
, newFileName IN VARCHAR2);
PROCEDURE addAttachment( dbAlias IN VARCHAR2, dbDriver IN VARCHAR2, dbConnect IN VARCHAR2
, dbUser IN VARCHAR2, dbEncPwd IN VARCHAR2, tableName IN VARCHAR2
, field IN VARCHAR2, whereCondition IN VARCHAR2
, dataType IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER);
PROCEDURE addDataAttachment( dbAlias IN VARCHAR2, dbDriver IN VARCHAR2, dbConnect IN VARCHAR2
, dbUser IN VARCHAR2, dbEncPwd IN VARCHAR2, tableName IN VARCHAR2
, field IN VARCHAR2, whereCondition IN VARCHAR2
, dataType IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER);
PROCEDURE addAttachment( urlStr IN VARCHAR2, fileName IN VARCHAR2);
PROCEDURE addUrlAttachment( urlStr IN VARCHAR2, fileName IN VARCHAR2);
PROCEDURE addAttachment( urlStr IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER);
PROCEDURE addUrlAttachment( urlStr IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER);
PROCEDURE addTextAttachment( textContent IN VARCHAR2, fileName IN VARCHAR2);
PROCEDURE addTextAttachment( textContent IN VARCHAR2, fileName IN VARCHAR2, rotation IN NUMBER);
PROCEDURE addBcc( ID IN VARCHAR2, NAME IN VARCHAR2, company IN VARCHAR2
, address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
, PROVINCE IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
, phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2);
PROCEDURE addCc( ID IN VARCHAR2, NAME IN VARCHAR2, company IN VARCHAR2
, address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
, PROVINCE IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
, phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2);
PROCEDURE addNotifyTo( ID IN VARCHAR2, NAME IN VARCHAR2, company IN VARCHAR2
, address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
, PROVINCE IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
, phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2, message IN VARCHAR2);
PROCEDURE addRecipient( ID IN VARCHAR2, NAME IN VARCHAR2, company IN VARCHAR2
, address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
, PROVINCE IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
, phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2);
FUNCTION getMessageType RETURN VARCHAR2;
FUNCTION initializeMessage(MessageType IN VARCHAR2) RETURN NUMBER;
FUNCTION send RETURN NUMBER;
PROCEDURE setBanner(newBanner IN VARCHAR2);
PROCEDURE setCoverPage(newCoverPage IN VARCHAR2);
PROCEDURE setCsTag ( csTag IN VARCHAR2);
PROCEDURE setDate(newDate IN VARCHAR2);
PROCEDURE setParam ( pKey IN VARCHAR2, pValue IN VARCHAR2);
PROCEDURE setPriority(newPriority IN NUMBER);
PROCEDURE setProject ( newProject IN VARCHAR2, newModule IN VARCHAR2, newPhase IN VARCHAR2);
PROCEDURE setResolution (newResolution IN NUMBER);
PROCEDURE setReturnReceipt ( returnReceipt IN NUMBER);
PROCEDURE setSender( senderIP IN VARCHAR2, senderName IN VARCHAR2, ID IN VARCHAR2
, NAME IN VARCHAR2, company IN VARCHAR2, address IN VARCHAR2
, code IN VARCHAR2, city IN VARCHAR2, PROVINCE IN VARCHAR2
, state IN VARCHAR2, email IN VARCHAR2, phoneNumber IN VARCHAR2
, faxNumber IN VARCHAR2);
PROCEDURE setSender( senderIP IN VARCHAR2, senderName IN VARCHAR2, ID IN VARCHAR2
, NAME IN VARCHAR2, company IN VARCHAR2, address IN VARCHAR2
, code IN VARCHAR2, city IN VARCHAR2, PROVINCE IN VARCHAR2
, state IN VARCHAR2, email IN VARCHAR2, phoneNumber IN VARCHAR2
, faxNumber IN VARCHAR2, USER IN VARCHAR2, PASSWORD IN VARCHAR2);
PROCEDURE setSubject(newSubject IN VARCHAR2);
PROCEDURE setText(newText IN VARCHAR2);
PROCEDURE setTime(newTime IN VARCHAR2);
PROCEDURE addReplyTo
( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
, address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
, province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
, phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2);
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
END Si4cim;
/

