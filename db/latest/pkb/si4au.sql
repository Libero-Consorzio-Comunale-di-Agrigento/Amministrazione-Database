--liquibase formatted sql

--changeset mturra:201901301231_175 runOnChange:true stripComments:false
CREATE OR REPLACE PACKAGE BODY SI4AU
AS
    -- con modifiche x Appeon
    FUNCTION getIssuerDN (certificate IN VARCHAR2)
        RETURN VARCHAR2
    AS
    BEGIN
        RETURN SI4AUPLSQLJ.getIssuerDN(certificate);
END;

FUNCTION getSubjectDN (certificate IN VARCHAR2)
        RETURN VARCHAR2
    AS
BEGIN
    RETURN SI4AUPLSQLJ.getSubjectDN(certificate);
END;

FUNCTION getUser (certificate IN BLOB)
        RETURN VARCHAR2
    AS
BEGIN
    RETURN SI4AUPLSQLJ.getUser(certificate);
END;

FUNCTION versione
        RETURN VARCHAR2
    AS
BEGIN
    RETURN SI4AUPLSQLJ.versione;
END;

FUNCTION authenticate (certificate IN BLOB)
        RETURN NUMBER
    AS
BEGIN
    RETURN SI4AUPLSQLJ.authenticate(certificate);
END;

FUNCTION authenticate (
        certificate   IN   BLOB
    , jdbcDriver    IN   VARCHAR2
    , database      IN   VARCHAR2
    )
        RETURN NUMBER
    AS
BEGIN
    RETURN SI4AUPLSQLJ.authenticate(certificate, jdbcDriver, database);
END;

FUNCTION authenticate (token IN VARCHAR2)
        RETURN NUMBER
    AS
BEGIN
    RETURN SI4AUPLSQLJ.authenticate(token);
END;

FUNCTION authenticate (
        token        IN   VARCHAR2
    , jdbcDriver   IN   VARCHAR2
    , database     IN   VARCHAR2
    )
        RETURN NUMBER
    AS
BEGIN
    RETURN SI4AUPLSQLJ.authenticate(token, jdbcDriver, database);
END;


FUNCTION si4_authenticate(username IN VARCHAR2, password IN VARCHAR2)
        -- Utilizzata da Appeon
        RETURN NUMBER
    AS
        tmp_password varchar2(32000) := password;
tmp_timestamp date;
BEGIN
    if length(password) = 54 then
        tmp_timestamp := to_date(ad4.crypt.decrypt(utl_encode.uudecode(password), utl_encode.base64_decode(utl_raw.cast_to_raw('/UXa/4ZHuFE7eRGY0oJorGlXFoGHpUZsYVQGDxz0tTw='))),'YYYYMMDDHH24miss');
        tmp_password := utl_encode.uuencode(ad4.crypt.crypt(rpad(username, 40, ' ')||to_char(tmp_timestamp,'YYYYMMDDHH24miss')
                                                ,utl_encode.base64_decode(utl_raw.cast_to_raw('/UXa/4ZHuFE7eRGY0oJorGlXFoGHpUZsYVQGDxz0tTw='))
                                                )
            ,3
            )
        ;
        if tmp_timestamp > sysdate then
            tmp_password := password;
        end if;
    end if;
    return authenticate (username => username, password => tmp_password);
exception when others then
    return authenticate (username => username, password => password);
END;

FUNCTION authenticate (username IN VARCHAR2, password IN VARCHAR2)
        RETURN NUMBER
    AS
        pragma autonomous_transaction;
d_ret number;
d_utente utenti.utente%type;
BEGIN
    d_ret := SI4AUPLSQLJ.authenticate(USERNAME => username, password => password);
    if d_ret = 0 then
        begin
            select utente
            into d_utente
            from ad4_utenti
            where nominativo = upper(username)
            ;
            UTENTE.RIGENERA_SO(d_utente);
        exception
            when others then
                null;
        end;
    end if;
    commit;
    return d_ret;
exception
    when others then
        rollback;
        return -1;
END;

FUNCTION authenticate (
        username     IN   VARCHAR2
    , password     IN   VARCHAR2
    , jdbcDriver   IN   VARCHAR2
    , database     IN   VARCHAR2
    )
        RETURN NUMBER
    AS
BEGIN
    RETURN SI4AUPLSQLJ.authenticate(username, password, jdbcDriver, database);
END;

FUNCTION authorize (
        userName       IN   VARCHAR2
    , module         IN   VARCHAR2
    , instance       IN   VARCHAR2
    , credentialID   IN   NUMBER
    , accessType     IN   VARCHAR2
    )
        RETURN NUMBER
    AS
BEGIN
    RETURN SI4AUPLSQLJ.authorize(username, module, instance, credentialID, accessType);
END;

FUNCTION authorize (
        userName       IN   VARCHAR2
    , module         IN   VARCHAR2
    , instance       IN   VARCHAR2
    , credentialID   IN   NUMBER
    , jdbcDriver     IN   VARCHAR2
    , database       IN   VARCHAR2
    , accessType     IN   VARCHAR2
    )
        RETURN NUMBER
    AS
BEGIN
    RETURN SI4AUPLSQLJ.authorize(username, module, instance, credentialID, jdbcDriver, database, accessType);
END;

FUNCTION authorize (
        userName       IN   VARCHAR2
    , module         IN   VARCHAR2
    , instance       IN   VARCHAR2
    , credentialID   IN   NUMBER
    , profile        IN   NUMBER
    , accessType     IN   VARCHAR2
    )
        RETURN NUMBER
    AS
BEGIN
    RETURN SI4AUPLSQLJ.authorize(username, module, instance, credentialID, profile, accessType);
END;

FUNCTION authorize (
        userName       IN   VARCHAR2
    , module         IN   VARCHAR2
    , instance       IN   VARCHAR2
    , credentialID   IN   NUMBER
    , profile        IN   NUMBER
    , jdbcDriver     IN   VARCHAR2
    , database       IN   VARCHAR2
    , accessType     IN   VARCHAR2
    )
        RETURN NUMBER
    AS
BEGIN
    RETURN SI4AUPLSQLJ.authorize(username, module, instance, credentialID, profile, jdbcDriver, database, accessType);
END;

FUNCTION getAuthenticationType (username IN VARCHAR2)
        RETURN VARCHAR2
    AS
BEGIN
    RETURN SI4AUPLSQLJ.getAuthenticationType (username);
END;

FUNCTION getAuthenticationType (
        userName     IN   VARCHAR2
    , jdbcDriver   IN   VARCHAR2
    , database     IN   VARCHAR2
    )
        RETURN VARCHAR2
    AS
BEGIN
    RETURN SI4AUPLSQLJ.getAuthenticationType (username, jdbcDriver, database);
END;

FUNCTION getUser (
        certificate   IN   BLOB
    , jdbcDriver    IN   VARCHAR2
    , database      IN   VARCHAR2
    )
        RETURN VARCHAR2
    AS
BEGIN
    RETURN SI4AUPLSQLJ.getUser (certificate, jdbcDriver, database);
END;

FUNCTION getUser (certificate IN VARCHAR2)
        RETURN VARCHAR2
    AS
BEGIN
    RETURN SI4AUPLSQLJ.getUser (certificate);
END;

FUNCTION getUser (
        token        IN   VARCHAR2
    , jdbcDriver   IN   VARCHAR2
    , database     IN   VARCHAR2
    )
        RETURN VARCHAR2
    AS
BEGIN
    RETURN SI4AUPLSQLJ.getUser (token, jdbcDriver, database);
END;

FUNCTION setAttributo (
        connectionURL   IN   VARCHAR2
    , nominativo      IN   VARCHAR2
    , password        IN   VARCHAR2
    , attributo       IN   VARCHAR2
    , valore          IN   VARCHAR2
    )
        RETURN NUMBER
    AS
BEGIN
    RETURN SI4AUPLSQLJ.setAttributo (connectionURL, nominativo, password, attributo, valore);
END;

FUNCTION setAttributo (
        nominativo   IN   VARCHAR2
    , password     IN   VARCHAR2
    , attributo    IN   VARCHAR2
    , valore       IN   VARCHAR2
    )
        RETURN NUMBER
    AS
BEGIN
    RETURN SI4AUPLSQLJ.setAttributo (nominativo, password, attributo, valore);
END;

END;
/
