CREATE OR REPLACE PACKAGE UTENTI_VERIFICHE_SISTEMAZIONI
IS
   /******************************************************************************
    NOME:        UTENTI_VERIFICHE
    DESCRIZIONE: Procedure e Funzioni per verificare situazione utenti
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
     00  18/12/2018 SN     Creazione
     01  09/01/2019 SN     Nuove procedure
     02  30/04/2019 SNeg   Nuove Funzioni
   ******************************************************************************/
   s_revisione Afc.t_revision := 'V1.01';
   FUNCTION GET_IS_UTENZA_ADS (p_utente VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION GET_IS_SO4_COMPONENTE (p_soggetto NUMBER)
      RETURN VARCHAR2;

   FUNCTION GET_IS_UTENZA_LDAP (p_utente VARCHAR2)
      RETURN VARCHAR2;

    FUNCTION GET_IS_DIRITTO_DA_SERVIZIO (p_utente VARCHAR2, p_modulo VARCHAR2, p_gruppo VARCHAR2)
        RETURN VARCHAR2;

   FUNCTION GET_ELENCO_DIAC (p_utente_o_gruppo VARCHAR2, p_modulo VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION get_elenco_utenti (p_utente VARCHAR2 DEFAULT '%')
      RETURN Afc.t_ref_cursor;
      
   PROCEDURE sistemazione_soggetto (p_utente varchar2 DEFAULT '%') ;
   
   PROCEDURE cambio_nominativo_ins_GRPLDAP(p_utente varchar2 DEFAULT '%') ;
   
END;
/

