CREATE OR REPLACE PACKAGE UTENTI_PKG IS
/******************************************************************************
 NOME:        UTENTE_PKG.
 DESCRIZIONE: Package per gestione UTENTI e GRUPPI.
 ANNOTAZIONI:
 ECCEZIONI:.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 00   26/02/2009 MM     Prima emissione.
******************************************************************************/
   -- Revisione del Package
   s_revisione CONSTANT Afc.t_revision := 'V1.00';
/******************************************************************************
 NOME:        VERSIONE.
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
******************************************************************************/
    FUNCTION  VERSIONE RETURN VARCHAR2;
/******************************************************************************
 NOME:        rinomina_utente
 DESCRIZIONE: Rinomina p_utente in p_nuovo_utente.
 PARAMETRI:   p_utente:     codice dell'utente.
              p_utente:     nuovo codice da associare a p_utente.
 ECCEZIONI:   -
 ANNOTAZIONI: Vengono DISABILITATI TRIGGER di UTENTI, UTENTI_GRUPPO,
              DIRITTI_ACCESSO.
******************************************************************************/
   procedure rinomina_utente
   ( p_utente in utenti.UTENTE%type
   , p_nominativo in utenti.nominativo%type
   , p_nuovo_utente in utenti.utente%TYPE
   , p_nuovo_nominativo in utenti.nominativo%type);
END UTENTI_PKG;
/

