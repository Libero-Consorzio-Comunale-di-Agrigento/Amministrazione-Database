--liquibase formatted sql

--changeset mturra:201901301231_265 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE Registro_Pac IS /* MASTER_LINK */
/******************************************************************************
 NOME:        registro_pac.
 DESCRIZIONE: .
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    26/11/2002 MM     Creazione.
 1    27/10/2009 SNeg   Modificata set_ad4_string
******************************************************************************/
       TYPE registro_rc IS REF CURSOR;
       FUNCTION VERSIONE /* SLAVE_COPY */
       RETURN VARCHAR2;
       FUNCTION get_valore /* SLAVE_COPY */
       ( p_chiave IN VARCHAR2
       , p_stringa IN VARCHAR2
       , p_utente IN VARCHAR2)
       RETURN VARCHAR2;
       FUNCTION get_commento /* SLAVE_COPY */
       ( p_chiave IN VARCHAR2
       , p_stringa IN VARCHAR2
       , p_utente IN VARCHAR2)
       RETURN VARCHAR2;
       PROCEDURE STR_DELETE  ( p_chiave IN VARCHAR2
                             , p_stringa IN VARCHAR2
                             , p_tipor IN VARCHAR2
                             , p_utente IN VARCHAR2);
       PROCEDURE STR_UPDATE ( p_chiave IN VARCHAR2
                            , p_chiave_old IN VARCHAR2
                            , p_stringa IN VARCHAR2
                            , p_stringa_old IN VARCHAR2
                            , p_valore IN VARCHAR2
                            , p_valore_old IN VARCHAR2
                            , p_commento IN VARCHAR2
                            , p_tipor IN VARCHAR2
                            , p_utente IN VARCHAR2);
       PROCEDURE STR_UPDATE
       ( p_chiave IN VARCHAR2
       , p_stringa IN VARCHAR2
       , p_valore IN VARCHAR2
       , p_valore_old IN VARCHAR2
       , p_utente IN VARCHAR2);
       FUNCTION albero_registro /* SLAVE_COPY */
       (
          P_GRUPPO_ID   VARCHAR2,
          P_MODULO      VARCHAR2,
          P_PAGE        VARCHAR2,
          P_RICERCA     VARCHAR2,
          P_UTENTE      VARCHAR2
       )
          RETURN CLOB;
       FUNCTION get_registro_rc /* SLAVE_COPY */
       ( p_chiave IN VARCHAR2
       , p_stringa IN VARCHAR2
       , p_utente IN VARCHAR2
       )
       RETURN AFC.T_REF_CURSOR;
   FUNCTION EXISTS_STRINGA /* SLAVE_COPY */
   ( p_chiave IN VARCHAR2
   , p_stringa IN VARCHAR2
   , p_utente IN VARCHAR2)
   RETURN NUMBER;
   FUNCTION EXISTS_CHIAVE /* SLAVE_COPY */
   ( p_chiave IN VARCHAR2
   , p_utente IN VARCHAR2)
   RETURN NUMBER;
   FUNCTION IS_CHIAVE_MODIFICABILE /* SLAVE_COPY */
   ( p_chiave IN VARCHAR2
   , p_utente IN VARCHAR2)
   RETURN NUMBER;
   FUNCTION GET_CHIAVE /* SLAVE_COPY */
   ( p_chiave IN VARCHAR2
   , p_utente IN VARCHAR2)
   RETURN VARCHAR2;
   FUNCTION GET_PADRE /* SLAVE_COPY */
   ( p_chiave IN VARCHAR2
   , p_utente IN VARCHAR2)
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        get_ad4_string.
              Legge il valore della stringa p_stringa della chiave p_chiave
              nella tabella registro di p_user con il seguente criterio di
              ricerca:
              1. DB_USERS/<p_user>/PRODUCTS/<p_modulo>/p_chiave;
              2. DB_USERS/<p_user>/p_chiave;
              3. PRODUCTS/<p_modulo>/p_chiave;
              4. PRODUCTS/AD4.
 PARAMETRI:   p_chiave:  chiave del registro in cui cercare la stringa.
              p_stringa: stringa da cercare nel registro.
              p_modulo:  modulo applicativo per cui cercare la stringa.
              p_user:    user oracle a cui appartiene il registro in cui cercare.
 RITORNA:     varchar2 valore della stringa data nella chiave data.
******************************************************************************/
   FUNCTION get_ad4_string /* SLAVE_COPY */
   ( p_chiave VARCHAR2
   , p_stringa VARCHAR2
   , p_modulo VARCHAR2
   , p_user VARCHAR2)
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        set_ad4_string.
              Scrive il valore p_valore nella stringa p_stringa della chiave
              p_chiave nella tabella registro di p_user.
              Se p_valore e' null, la stringa viene eliminata da
                 DB_USERS/<p_user>/PRODUCTS/<p_modulo>/p_chiave.
              Lo scrive in
                 DB_USERS/<p_user>/PRODUCTS/<p_modulo>/p_chiave
              se non esiste gia' con uguale valore in
                 DB_USERS/<p_user>/p_chiave
              o, se non esiste la chiave DB_USERS/<p_user>/p_chiave, in
                 PRODUCTS/<p_modulo>/p_chiave
              o, se non esiste la chiave PRODUCTS/<p_modulo>/p_chiave, in
                 PRODUCTS/AD4.
 ARGOMENTI:   p_chiave:  chiave del registro in cui cercare la stringa.
              p_stringa: stringa da cercare nel registro.
              p_valore:  valore da associare alla stringa, se = null, la stringa
                         viene eliminata da
                         DB_USERS/<p_user>/PRODUCTS/<p_modulo>/p_chiave.
              p_modulo:  modulo applicativo per cui cercare la stringa.
              p_user:    user oracle a cui appartiene il registro in cui cercare.
******************************************************************************/
   PROCEDURE set_ad4_string
   ( p_chiave VARCHAR2
   , p_stringa VARCHAR2
   , p_valore VARCHAR2
   , p_modulo VARCHAR2
   , p_user VARCHAR2);
END Registro_Pac;
/

