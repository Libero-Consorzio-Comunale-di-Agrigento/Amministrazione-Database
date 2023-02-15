CREATE OR REPLACE PACKAGE ldap_utility
IS
/******************************************************************************
 NOME:        ldap_utility.
 DESCRIZIONE: Funzioni per la gestione della configurazione dei server LDAP
              all'interno della tabella Registro (analogo al Registry di
              Windows).
 ANNOTAZIONI: .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 00   25/09/2006 MM     Prima emissione.
 01  17/03/2008  SN    Inserimento allineamento struttura ldap con AD4.
 02  18/01/2010  SNeg   Modifica struttura albero ldap generato.
 03  26/03/2010  SNeg   Inserimento calcolo attributi
 04  29/09/2010  SNeg   Inserimento funzione di concatenazione
 05  28/05/2018  SN    Default parametro per schedulare il job a null
******************************************************************************/
   s_revisione   CONSTANT VARCHAR2 (30) := 'V1.05';
   FUNCTION versione
      RETURN VARCHAR2;
   PRAGMA RESTRICT_REFERENCES (versione, WNDS, WNPS);
   FUNCTION crea_server_alternativo
      RETURN VARCHAR2;
   PROCEDURE set_attributo (
      p_dn        VARCHAR2
    , p_stringa   VARCHAR2
    , p_valore    VARCHAR2
    , p_scrivi_allegato number default 1
   );
   function setPassword(
      p_nominativo VARCHAR2
    , p_valore     VARCHAR2
   )
      return number;
   PROCEDURE allinea_ldap
   ( p_rigenera_so integer default 1
   , p_debug integer default 0);
   FUNCTION concatena (p_stringa1 varchar2
                     , p_stringa2 varchar2
   )
      RETURN VARCHAR2;
   FUNCTION get_delimitatore
      RETURN VARCHAR2;
           FUNCTION get_RootOu
      RETURN VARCHAR2;
    FUNCTION get_RootUsers
      RETURN VARCHAR2;
      FUNCTION pulisci (p_stringa varchar2)
   RETURN VARCHAR2;
   FUNCTION formatta_struttura (
      p_struttura   VARCHAR2
     ,p_figlio      VARCHAR2
     ,p_tipo        VARCHAR2 -- O,G
   )
      RETURN VARCHAR2;
   FUNCTION get_name (
      p_cn   VARCHAR2
   )
      RETURN VARCHAR2;
   FUNCTION allinea_ldap_job (
      p_num_ore_dopo_mezzanotte   NUMBER DEFAULT NULL
   )
      RETURN NUMBER;
      procedure scarica_ldap;
      procedure ad4_viste_to_tabelle;
      FUNCTION aggiungi_aoo_amm (p_utente_ad4_uo VARCHAR2)
       RETURN VARCHAR2;
      FUNCTION  CALCOLA_ATTRIBUTO(p_valore varchar2, p_utente varchar2,p_descrizione varchar2)
      RETURN varchar2;
END ldap_utility;
/

