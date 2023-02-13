--liquibase formatted sql

--changeset mturra:201901301231_235 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE finmatica_ldap
AS
    function is_member_of(url VARCHAR2, user VARCHAR2, password VARCHAR2, groupDn VARCHAR2, dn VARCHAR2)
    RETURN BOOLEAN;
    function is_server_alive(url VARCHAR2, user VARCHAR2, password VARCHAR2)
    RETURN BOOLEAN;
    function versione return VARCHAR2;
    function is_accountenabled(
    url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2
    ) return NUMBER;
   FUNCTION get_vendor_name(
      url         VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2
   ) RETURN VARCHAR2;
    FUNCTION manage_ad4(
    username    VARCHAR2,
    serverkey       VARCHAR2
   ) RETURN VARCHAR2;
   PROCEDURE bind (url VARCHAR2, USER VARCHAR2, PASSWORD VARCHAR2, dn VARCHAR2);
   PROCEDURE bind_group (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2,
      cn         VARCHAR2
   );
   PROCEDURE bind_ou (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2,
      cn         VARCHAR2
   );
   PROCEDURE bind_o (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2
   );
   PROCEDURE unbind (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2
   );
   FUNCTION lookup (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      dn         VARCHAR2
   )
      RETURN NUMBER;
   FUNCTION get_distinguished_name (
      url           VARCHAR2,
      USER          VARCHAR2,
      PASSWORD      VARCHAR2,
      search_base   VARCHAR2,
      filter        VARCHAR2
   )
      RETURN VARCHAR2;
   PROCEDURE RENAME (
      url        VARCHAR2,
      USER       VARCHAR2,
      PASSWORD   VARCHAR2,
      old_dn     VARCHAR2,
      new_dn     VARCHAR2
   );
   FUNCTION get_attribute (
      url              VARCHAR2,
      USER             VARCHAR2,
      PASSWORD         VARCHAR2,
      dn               VARCHAR2,
      attribute_name   VARCHAR2
   )
      RETURN VARCHAR2;
   FUNCTION get_attribute_with_filter (
      url              VARCHAR2,
      USER             VARCHAR2,
      PASSWORD         VARCHAR2,
      base           VARCHAR2,
      filter           VARCHAR2,
      attribute_name   VARCHAR2
   )
      RETURN VARCHAR2;
   FUNCTION search_dn (
      url               VARCHAR2,
      USER              VARCHAR2,
      PASSWORD          VARCHAR2,
      base              VARCHAR2,
      attribute_name    VARCHAR2,
      attribute_value   VARCHAR2
   )
      RETURN VARCHAR2;
   FUNCTION add_attribute (
      url              VARCHAR2,
      USER             VARCHAR2,
      PASSWORD         VARCHAR2,
      dn               VARCHAR2,
      attribute_name   VARCHAR2,
      new_value        VARCHAR2
   )
      RETURN VARCHAR2;
   FUNCTION modify_attribute (
      url              VARCHAR2,
      USER             VARCHAR2,
      PASSWORD         VARCHAR2,
      dn               VARCHAR2,
      attribute_name   VARCHAR2,
      new_value        VARCHAR2
   )
    RETURN VARCHAR2;
   FUNCTION remove_attribute (
      url              VARCHAR2,
      USER             VARCHAR2,
      PASSWORD         VARCHAR2,
      dn               VARCHAR2,
      attribute_name   VARCHAR2
   )
    RETURN VARCHAR2;
   FUNCTION remove_attribute (
      url               VARCHAR2,
      USER              VARCHAR2,
      PASSWORD          VARCHAR2,
      dn                VARCHAR2,
      attribute_name    VARCHAR2,
      attribute_value   VARCHAR2
   )
    RETURN VARCHAR2;
   PROCEDURE download_schema (
      url           VARCHAR2,
      USER          VARCHAR2,
      PASSWORD      VARCHAR2,
      livello       NUMBER,
      search_base   VARCHAR2,
      filter        VARCHAR2
   );
END;
/

