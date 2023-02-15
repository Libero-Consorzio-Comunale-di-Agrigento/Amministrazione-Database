CREATE OR REPLACE PACKAGE ldap_to_db
IS
   /******************************************************************************
    NOME:        ldap_to_db.
    DESCRIZIONE: Funzioni per la scrittura nel DB delle informazioni presenti in LDAP.
                 Viene richiamata da accesso se l'utente ha fatto accesso con
                 credenziali ldap
                 se ToDbMapping = yes
    ANNOTAZIONI: Guarda la voce di registro:
    Insert into REGISTRO   (CHIAVE, STRINGA, COMMENTO, VALORE)
    Values  ('PRODUCTS/LDAPCONFIG', 'ToDbMapping', 'Indica se in fase di login devono essere acquisite informazioni da LDAP', 'no');
    (preparato x distribuzione il package ma il registro si è deciso di non distribuirlo)
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    00   28/08/2014 SN     Prima emissione
   ******************************************************************************/
   s_revisione   CONSTANT VARCHAR2 (30) := 'V1.00';
   FUNCTION versione
      RETURN VARCHAR2;
   PRAGMA RESTRICT_REFERENCES (versione, WNDS, WNPS);
   FUNCTION crea_server_alternativo
      RETURN VARCHAR2;
   return                 NUMBER;
   PROCEDURE allinea_DB (p_nominativo    VARCHAR2,
                         p_server        VARCHAR2,
                         p_debug         NUMBER DEFAULT 0);
   FUNCTION get_RootOu
      RETURN VARCHAR2;
   FUNCTION get_RootUsers
      RETURN VARCHAR2;
   FUNCTION pulisci (p_stringa VARCHAR2)
      RETURN VARCHAR2;
   FUNCTION get_name (p_cn VARCHAR2)
      RETURN VARCHAR2;
END;
/

