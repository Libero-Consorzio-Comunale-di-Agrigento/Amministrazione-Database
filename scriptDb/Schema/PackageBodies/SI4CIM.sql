CREATE OR REPLACE PACKAGE BODY Si4cim AS
    FUNCTION versione RETURN VARCHAR2
    AS
    BEGIN
        if nvl(s_implementazione,'X') = 'PLS' then
            return '2022';
        else
            RETURN Si4cimplsqlj.versione;
        end if;
    END;
    PROCEDURE addAttachment
    ( newPathName IN VARCHAR2
    , newFileName IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.addFileAttachment(newPathName, newFileName);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addFileAttachment
    ( newPathName IN VARCHAR2
    , newFileName IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.addFileAttachment(newPathName, newFileName);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addAttachment
    ( newPathName IN VARCHAR2
    , newFileName IN VARCHAR2
    , newRotation IN NUMBER)
    AS
    BEGIN
        Si4cimplsqlj.addFileAttachment(newPathName, newFileName, newRotation);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addFileAttachment
    ( newPathName IN VARCHAR2
    , newFileName IN VARCHAR2
    , newRotation IN NUMBER)
    AS
    BEGIN
        Si4cimplsqlj.addFileAttachment(newPathName, newFileName, newRotation);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
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
    , newFileName IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.addDataAttachment(dbAlias, dbDriver, dbConnect, dbUser, dbEncPwd, newTableName, newBlobField, newWhereCondition, newDataType, newFileName);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addDataAttachment
    ( dbAlias IN VARCHAR2
    , dbDriver IN VARCHAR2
    , dbConnect IN VARCHAR2
    , dbUser IN VARCHAR2
    , dbEncPwd IN VARCHAR2
    , newTableName IN VARCHAR2
    , newBlobField IN VARCHAR2
    , newWhereCondition IN VARCHAR2
    , newDataType IN VARCHAR2
    , newFileName IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.addDataAttachment(dbAlias, dbDriver, dbConnect, dbUser, dbEncPwd, newTableName, newBlobField, newWhereCondition, newDataType, newFileName);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addAttachment
    ( dbAlias IN VARCHAR2
    , dbDriver IN VARCHAR2
    , dbConnect IN VARCHAR2
    , dbUser IN VARCHAR2
    , dbEncPwd IN VARCHAR2
    , TableName IN VARCHAR2
    , Field IN VARCHAR2
    , WhereCondition IN VARCHAR2
    , DataType IN VARCHAR2
    , FileName IN VARCHAR2
    , rotation IN NUMBER)
    AS
    BEGIN
        Si4cimplsqlj.addDataAttachment(dbAlias, dbDriver, dbConnect, dbUser, dbEncPwd, TableName, Field, WhereCondition, DataType, FileName, rotation);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addDataAttachment
    ( dbAlias IN VARCHAR2
    , dbDriver IN VARCHAR2
    , dbConnect IN VARCHAR2
    , dbUser IN VARCHAR2
    , dbEncPwd IN VARCHAR2
    , TableName IN VARCHAR2
    , Field IN VARCHAR2
    , WhereCondition IN VARCHAR2
    , DataType IN VARCHAR2
    , FileName IN VARCHAR2
    , rotation IN NUMBER)
    AS
    BEGIN
        Si4cimplsqlj.addDataAttachment(dbAlias, dbDriver, dbConnect, dbUser, dbEncPwd, TableName, Field, WhereCondition, DataType, FileName, rotation);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addAttachment
    ( urlStr IN VARCHAR2,
      fileName IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.addUrlAttachment(urlStr, fileName);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addUrlAttachment
    ( urlStr IN VARCHAR2,
      fileName IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.addUrlAttachment(urlStr, fileName);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addAttachment
    ( urlStr IN VARCHAR2
    , fileName IN VARCHAR2
    , rotation IN NUMBER)
    AS
    BEGIN
        Si4cimplsqlj.addUrlAttachment(urlStr, fileName , rotation);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addUrlAttachment
    ( urlStr IN VARCHAR2
    , fileName IN VARCHAR2
    , rotation IN NUMBER)
    AS
    BEGIN
        Si4cimplsqlj.addUrlAttachment(urlStr, fileName , rotation);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addTextAttachment
    ( textContent IN VARCHAR2,
      fileName IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.addTextAttachment(textContent, fileName);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addTextAttachment
    ( textContent IN VARCHAR2
    , fileName IN VARCHAR2
    , rotation IN NUMBER)
    AS
    BEGIN
        Si4cimplsqlj.addTextAttachment(textContent, fileName , rotation);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addBcc
    ( ID IN VARCHAR2
    , NAME IN VARCHAR2
    , company IN VARCHAR2
    , address IN VARCHAR2
    , code IN VARCHAR2
    , city IN VARCHAR2
    , PROVINCE IN VARCHAR2
    , state IN VARCHAR2
    , email IN VARCHAR2
    , phoneNumber IN VARCHAR2
    , faxNumber IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.addBcc( ID, NAME, company, address, code, city, PROVINCE, state
            , email, phoneNumber, faxNumber);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addCc
    ( ID IN VARCHAR2
    , NAME IN VARCHAR2
    , company IN VARCHAR2
    , address IN VARCHAR2
    , code IN VARCHAR2
    , city IN VARCHAR2
    , PROVINCE IN VARCHAR2
    , state IN VARCHAR2
    , email IN VARCHAR2
    , phoneNumber IN VARCHAR2
    , faxNumber IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.addCc( ID, NAME, company, address, code, city, PROVINCE, state
            , email, phoneNumber, faxNumber);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addNotifyTo
    ( ID IN VARCHAR2
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
    , message IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.addNotifyTo( ID, NAME, company, address, code, city, PROVINCE, state
            , email, phoneNumber, faxNumber, message);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addRecipient
    ( ID IN VARCHAR2
    , NAME IN VARCHAR2
    , company IN VARCHAR2
    , address IN VARCHAR2
    , code IN VARCHAR2
    , city IN VARCHAR2
    , PROVINCE IN VARCHAR2
    , state IN VARCHAR2
    , email IN VARCHAR2
    , phoneNumber IN VARCHAR2
    , faxNumber IN VARCHAR2)
    AS
    BEGIN
        if nvl(s_implementazione,'X') = 'PLS' then
            s_name_recipient := NAME;
            s_address_recipient := email;
        else
            Si4cimplsqlj.addRecipient( ID, NAME, company, address, code, city, PROVINCE, state
                , email, phoneNumber, faxNumber);
        end if;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    FUNCTION getMessageType RETURN VARCHAR2 AS
        ret VARCHAR2(2000);
    BEGIN
        if nvl(s_implementazione,'X') = 'PLS' then
            ret := null;
        else
            ret := Si4cimplsqlj.getMessageType;
        end if;
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
        END IF;
    END;
    FUNCTION initializeMessage(MessageType IN VARCHAR2) RETURN NUMBER AS
        ret NUMBER;
        msgType varchar2(100) := MessageType;
    BEGIN
        --registro_utility.leggi_stringa('PRODUCTS/CIM/CONFIG/'||msgType,'Implementazione',s_implementazione,FALSE);
        begin
            SELECT VALORE
            INTO s_implementazione
            FROM REGISTRO
            WHERE CHIAVE = 'PRODUCTS/CIM/CONFIG/'||msgType
              AND STRINGA = 'Implementazione';
        exception when no_data_found then
            s_implementazione := null;
        end;

        if msgType = 'csmail' then
            -- non si usa registro_utility per evitare dipendenze da esso
            BEGIN
                SELECT nvl(VALORE, MessageType)
                INTO msgType
                FROM REGISTRO
                WHERE CHIAVE = 'PRODUCTS/SI4AFCPB/CS'
                  AND STRINGA = 'Tag'
                ;
            EXCEPTION
                WHEN OTHERS THEN
                    msgType := MessageType;
            END;
        end if;
        if nvl(s_implementazione,'X') = 'PLS' then
            SELECT VALORE
            INTO s_messageType
            FROM REGISTRO
            WHERE CHIAVE = 'PRODUCTS/CIM/CONFIG/'||msgType
              AND STRINGA = 'CsTag';
            --s_messageType := msgType;
            --dbms_output.put_line('Inizialize-Implementazione = '||s_implementazione);
            ret := 1;
        else
            ret := Si4cimplsqlj.initializeMessage(msgType);
        end if;

        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
        END IF;
    END;
    FUNCTION send RETURN NUMBER AS
        ret NUMBER;
        d_statement varchar2(4000);
    BEGIN
        if nvl(s_implementazione,'X') = 'PLS' then
            ret := 1;
            d_statement := 'begin si4cs_si4cssendplsqlproc(:p_message_type,:p_oggetto_messaggio,:p_testo_messaggio,:p_name_recipient,:p_adress_recipient,:p_progetto,:p_modulo,:p_fase); end;';
            execute immediate  d_statement using in  s_messageType,s_oggetto_messaggio,s_testo_messaggio,s_name_recipient,s_address_recipient,s_progetto,s_modulo,s_fase ;
        else
            ret := Si4cimplsqlj.send;
        end if;
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    PROCEDURE setCoverPage
    (newCoverPage IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.setCoverPage(newCoverPage);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE setBanner
    (newBanner IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.setBanner(newBanner);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE setCsTag
    ( csTag IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.setCsTag(csTag);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE setDate
    (newDate IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.setDate(newDate);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE setParam
    ( pKey IN VARCHAR2, pValue IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.setParam( pKey, pValue);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE setPriority
    (newPriority IN NUMBER)
    AS
    BEGIN
        Si4cimplsqlj.setPriority(newPriority);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE setProject
    ( newProject IN VARCHAR2
    , newModule IN VARCHAR2
    , newPhase IN VARCHAR2)
    AS
    BEGIN
        if nvl(s_implementazione,'X') = 'PLS' then
            s_progetto := newProject;
            s_modulo := newModule;
            s_fase := newPhase;
        else
            Si4cimplsqlj.setProject(newProject, newModule, newPhase);
        end if;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE setResolution
    (newResolution IN NUMBER)
    AS
    BEGIN
        Si4cimplsqlj.setResolution(newResolution);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE setReturnReceipt
    ( returnReceipt IN NUMBER)
    AS
    BEGIN
        Si4cimplsqlj.setReturnReceipt(returnReceipt);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
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
    , faxNumber IN VARCHAR2)
    AS
    BEGIN
        if nvl(s_implementazione,'X') = 'PLS' then
            null;
        else
            Si4cimplsqlj.setSender( senderIP, senderName, ID, NAME, company, address, code
                , city, PROVINCE, state, email, phoneNumber, faxNumber);
        end if;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
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
    , USER IN VARCHAR2
    , PASSWORD IN VARCHAR2)
    AS
    BEGIN
        if nvl(s_implementazione,'X') = 'PLS' then
            null;
        else
            Si4cimplsqlj.setSender( senderIP, senderName, ID, NAME, company, address, code
                , city, PROVINCE, state, email, phoneNumber, faxNumber, USER, PASSWORD);
        end if;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE setSubject
    (newSubject IN VARCHAR2)
    AS
    BEGIN
        if nvl(s_implementazione,'X') = 'PLS' then
            s_oggetto_messaggio := newSubject;
        else
            Si4cimplsqlj.setSubject(newSubject);
        end if;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE setText
    (newText IN VARCHAR2)
    AS
    BEGIN
        if nvl(s_implementazione,'X') = 'PLS' then
            s_testo_messaggio := newText;
        else
            Si4cimplsqlj.setText(newText);
        end if;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE setTime
    (newTime IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.setTime(newTime);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    PROCEDURE addReplyTo
    ( ID IN VARCHAR2, name IN VARCHAR2, company IN VARCHAR2
    , address IN VARCHAR2, code IN VARCHAR2, city IN VARCHAR2
    , province IN VARCHAR2, state IN VARCHAR2, email IN VARCHAR2
    , phoneNumber IN VARCHAR2, faxNumber IN VARCHAR2)
    AS
    BEGIN
        Si4cimplsqlj.addReplyTo(ID, name, company, address, code, city, province, state, email, phoneNumber, faxNumber);
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
    END;
    FUNCTION getLastMessageDbIndex RETURN NUMBER AS
        ret NUMBER;
    BEGIN
        ret := Si4cimplsqlj.getLastMessageDbIndex;
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getHostUserInfos RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getHostUserInfos;
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getContentForTag(tag IN VARCHAR2) RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getContentForTag(tag);
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getFileSeparator RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getFileSeparator;
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getJavaLibraryPath RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getJavaLibraryPath;
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getPathSeparator RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getPathSeparator;
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getSearchFile RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getSearchFile;
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getSearchPath RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getSearchPath;
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getSi4cimContent RETURN VARCHAR2 AS
        ret VARCHAR2(32767);
    BEGIN
        ret := Si4cimplsqlj.getSi4cimContent;
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getSi4cimKeys( keySeparator IN VARCHAR2) RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getSi4cimKeys( keySeparator);
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getSi4cimTags( tagSeparator IN VARCHAR2) RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getSi4cimTags( tagSeparator);
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getSi4cimValues(valueSeparator IN VARCHAR2)RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getSi4cimValues(valueSeparator);
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getUserHome RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getUserHome;
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
    FUNCTION getValueForTagKey(tag IN VARCHAR2, key IN VARCHAR2) RETURN VARCHAR2 AS
        ret VARCHAR2(4000);
    BEGIN
        ret := Si4cimplsqlj.getValueForTagKey(tag, key);
        RETURN ret;
    EXCEPTION WHEN OTHERS THEN
        IF INSTR(LOWER(SQLERRM),'-unsupported method') > 0 THEN
            RETURN 0;
        ELSE
            RAISE;
            RETURN -1;
        END IF;
    END;
END Si4cim;
/

