--liquibase formatted sql

--changeset mturra:201901301231_138 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY finmatica_ldap
AS
    function is_member_of
        (url VARCHAR2,
        user VARCHAR2,
        password VARCHAR2,
        groupDn VARCHAR2,
        dn VARCHAR2)
    RETURN BOOLEAN
    as
    language java
    name
    'it.finmatica.ldap.DBWrapper.isAMemberOf(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) return boolean';
    function is_server_alive
        (url VARCHAR2,
        user VARCHAR2,
        password VARCHAR2
        )
    RETURN BOOLEAN
    as
    language java
    name
    'it.finmatica.ldap.DBWrapper.isServerAlive(java.lang.String, java.lang.String, java.lang.String) return boolean';
    function versione
    return VARCHAR2
    as
    language java
    name 'it.finmatica.ldap.Versione.getVersione() return String';
    function is_accountenabled(
    url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2
    ) return NUMBER
    as
    language java
    name 'it.finmatica.ldap.DBWrapper.isAccountEnabled(java.lang.String, java.lang.String, java.lang.String, java.lang.String) return int';
   FUNCTION get_vendor_name(
      url         VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2
   ) RETURN VARCHAR2
        AS
        LANGUAGE JAVA
        NAME 'it.finmatica.ldap.DBWrapper.getVendorName(java.lang.String, java.lang.String, java.lang.String ) return String';
   PROCEDURE bind (url VARCHAR2, USER VARCHAR2, PASSWORD VARCHAR2, dn VARCHAR2)
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.bind(java.lang.String, java.lang.String, java.lang.String, java.lang.String )';
   FUNCTION manage_ad4(
    username    VARCHAR2,
    serverkey       VARCHAR2
   ) RETURN VARCHAR2
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.login.DBLoginWrapper.manageAd4(java.lang.String, java.lang.String)return String';
   PROCEDURE bind_group (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2,
      cn         VARCHAR2
   )
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.bindGroup(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String )';
   PROCEDURE bind_ou (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2,
      cn         VARCHAR2
   )
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.bindOu(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String )';
   PROCEDURE bind_o (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2
   )
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.bindO(java.lang.String, java.lang.String, java.lang.String, java.lang.String )';
   PROCEDURE unbind (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2
   )
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.unbind(java.lang.String, java.lang.String, java.lang.String, java.lang.String )';
   PROCEDURE RENAME (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      old_dn     VARCHAR2,
      new_dn     VARCHAR2
   )
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.rename(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String )';
   FUNCTION add_attribute (
      url              VARCHAR2,
      USER             VARCHAR2,
      PASSWORD         VARCHAR2,
      dn               VARCHAR2,
      attribute_name   VARCHAR2,
      new_value        VARCHAR2
   ) RETURN VARCHAR2
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.addAttribute(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) return String';
   FUNCTION modify_attribute (
      url              VARCHAR2,
      USER             VARCHAR2,
      PASSWORD         VARCHAR2,
      dn               VARCHAR2,
      attribute_name   VARCHAR2,
      new_value        VARCHAR2
   ) RETURN VARCHAR2
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.modifyAttribute(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) return String';
   FUNCTION remove_attribute (
      url              VARCHAR2,
      USER             VARCHAR2,
      PASSWORD         VARCHAR2,
      dn               VARCHAR2,
      attribute_name   VARCHAR2
   ) RETURN VARCHAR2
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.removeAttribute(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String ) return String';
   FUNCTION remove_attribute (
      url               VARCHAR2,
      USER              VARCHAR2,
      PASSWORD          VARCHAR2,
      dn                VARCHAR2,
      attribute_name    VARCHAR2,
      attribute_value   VARCHAR2
   ) RETURN VARCHAR2
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.removeAttribute(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String ) return String';
   FUNCTION get_attribute (
      url              VARCHAR2,
      USER             VARCHAR2,
      PASSWORD         VARCHAR2,
      dn               VARCHAR2,
      attribute_name   VARCHAR2
   )
      RETURN VARCHAR2
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.getAttribute(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) return String';
   FUNCTION get_attribute_with_filter (
      url              VARCHAR2,
      USER             VARCHAR2,
      PASSWORD         VARCHAR2,
      base           VARCHAR2,
      filter           VARCHAR2,
      attribute_name   VARCHAR2
   )
    RETURN VARCHAR2
   AS
    LANGUAGE JAVA
    NAME 'it.finmatica.ldap.DBWrapper.getAttributeWithFilter(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) return String';
   FUNCTION lookup (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2
   )
      RETURN NUMBER
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.lookup(java.lang.String, java.lang.String, java.lang.String, java.lang.String) return int';
   FUNCTION search_dn (
      url               VARCHAR2,
      USER              VARCHAR2,
      PASSWORD          VARCHAR2,
      base              VARCHAR2,
      attribute_name    VARCHAR2,
      attribute_value   VARCHAR2
   )
      RETURN VARCHAR2
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.searchDn(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String ) return String';
   FUNCTION get_distinguished_name (
      url           VARCHAR2,
      USER          VARCHAR2,
      PASSWORD      VARCHAR2,
      search_base   VARCHAR2,
      filter        VARCHAR2
   )
      RETURN VARCHAR2
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.getDistinguishedName(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String ) return String';
   PROCEDURE download_schema (
      url           VARCHAR2,
      USER          VARCHAR2,
      PASSWORD      VARCHAR2,
      livello       NUMBER,
      search_base   VARCHAR2,
      filter        VARCHAR2
   )
   AS
      LANGUAGE JAVA
      NAME 'it.finmatica.ldap.DBWrapper.downloadSchema(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String )';
END finmatica_ldap;
/

