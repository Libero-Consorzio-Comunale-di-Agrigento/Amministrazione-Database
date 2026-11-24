--liquibase formatted sql

--changeset adadamo:202604010900_123 runOnChange:true stripComments:false endDelimiter:/

CREATE OR REPLACE PACKAGE si4cimv2 is
    function version return varchar2;
    function initializemessage (P_tag in varchar2) return number;
    PROCEDURE setSender
    ( senderIP IN VARCHAR2
    , senderName IN VARCHAR2
    , ID IN VARCHAR2
    , NAME IN VARCHAR2
    , company IN VARCHAR2
    , address IN VARCHAR2
    , code IN VARCHAR2
    , city IN VARCHAR2
    , PROVINCE IN VARCHAR2
    , state IN VARCHAR2
    , email IN VARCHAR2
    , phoneNumber IN VARCHAR2
    , faxNumber IN VARCHAR2
    , user      in varchar2 default null
    , password in varchar2 default null);
 PROCEDURE addrecipient ( ID IN VARCHAR2
                           , NAME IN VARCHAR2
                           , company IN VARCHAR2
                           , address IN VARCHAR2
                           , code IN VARCHAR2
                           , city IN VARCHAR2
                           , PROVINCE IN VARCHAR2
                           , state IN VARCHAR2
                           , email IN VARCHAR2
                           , phoneNumber IN VARCHAR2
                           , faxNumber IN VARCHAR2
                           );
 procedure  setsubject (newSubject IN VARCHAR2);
 PROCEDURE setText  (newText IN VARCHAR2);
  PROCEDURE addAttachment
    ( dbAlias IN VARCHAR2
    , dbDriver IN VARCHAR2
    , dbConnect IN VARCHAR2
    , dbUser IN VARCHAR2
    , dbEncPwd IN VARCHAR2
    , newTableName IN VARCHAR2
    , newBlobField IN VARCHAR2
    , newWhereCondition IN VARCHAR2
    , newDataType IN VARCHAR2
    , newFileName IN VARCHAR2);
FUNCTION send RETURN NUMBER;    

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
                              
end;
/