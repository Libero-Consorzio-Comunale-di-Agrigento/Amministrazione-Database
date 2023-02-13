--liquibase formatted sql

--changeset mturra:201901301231_273 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE SI4AUPLSQLJ AS
 FUNCTION getIssuerDN(certificate IN VARCHAR2) RETURN VARCHAR2;
 FUNCTION getSubjectDN(certificate IN VARCHAR2) RETURN VARCHAR2;
 FUNCTION getUser(certificate IN BLOB) RETURN VARCHAR2;
 FUNCTION versione RETURN VARCHAR2;
 FUNCTION authenticate(certificate IN BLOB) RETURN NUMBER;
 FUNCTION authenticate(certificate IN BLOB, jdbcDriver IN VARCHAR2, database IN VARCHAR2) RETURN NUMBER;
 FUNCTION authenticate(token IN VARCHAR2) RETURN NUMBER;
 FUNCTION authenticate(token IN VARCHAR2, jdbcDriver IN VARCHAR2, database IN VARCHAR2) RETURN NUMBER;
 FUNCTION authenticate(username IN VARCHAR2, password IN VARCHAR2) RETURN NUMBER;
 FUNCTION authenticate(username IN VARCHAR2, password IN VARCHAR2, jdbcDriver IN VARCHAR2, database IN VARCHAR2) RETURN NUMBER;
 FUNCTION authorize(userName IN VARCHAR2, module IN VARCHAR2, instance IN VARCHAR2, credentialID IN NUMBER, accessType IN VARCHAR2) RETURN NUMBER;
 FUNCTION authorize(userName IN VARCHAR2, module IN VARCHAR2, instance IN VARCHAR2, credentialID IN NUMBER, jdbcDriver IN VARCHAR2, database IN VARCHAR2, accessType IN VARCHAR2) RETURN NUMBER;
 FUNCTION authorize(userName IN VARCHAR2, module IN VARCHAR2, instance IN VARCHAR2, credentialID IN NUMBER, profile IN NUMBER, accessType IN VARCHAR2) RETURN NUMBER;
 FUNCTION authorize(userName IN VARCHAR2, module IN VARCHAR2, instance IN VARCHAR2, credentialID IN NUMBER, profile IN NUMBER, jdbcDriver IN VARCHAR2, database IN VARCHAR2, accessType IN VARCHAR2) RETURN NUMBER;
 FUNCTION getAuthenticationType(username IN VARCHAR2) RETURN VARCHAR2;
 FUNCTION getAuthenticationType(userName IN VARCHAR2, jdbcDriver IN VARCHAR2, database IN VARCHAR2) RETURN VARCHAR2;
 FUNCTION getUser(certificate IN BLOB, jdbcDriver IN VARCHAR2, database IN VARCHAR2) RETURN VARCHAR2;
 FUNCTION getUser(certificate IN VARCHAR2) RETURN VARCHAR2;
 FUNCTION getUser(token IN VARCHAR2, jdbcDriver IN VARCHAR2, database IN VARCHAR2) RETURN VARCHAR2;
 FUNCTION setAttributo(connectionURL IN VARCHAR2, nominativo IN VARCHAR2, password IN VARCHAR2, attributo IN VARCHAR2, valore IN VARCHAR2) RETURN NUMBER;
 FUNCTION setAttributo(nominativo IN VARCHAR2, password IN VARCHAR2, attributo IN VARCHAR2, valore IN VARCHAR2) RETURN NUMBER;
END SI4AUPLSQLJ;
/

