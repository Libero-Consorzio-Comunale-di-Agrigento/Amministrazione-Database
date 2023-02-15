CREATE OR REPLACE FUNCTION Get_Gruppi_Utente
( p_utente VARCHAR2
, p_codice VARCHAR2 DEFAULT 'Y'
, p_descrizione VARCHAR2 DEFAULT 'N')
RETURN VARCHAR2
IS
/******************************************************************************
 NOME:        GET_GRUPPI_UTENTE
 DESCRIZIONE: Dato un utente, ritorna i gruppi a cui egli appartiene.
 PARAMETRI:   p_utente varchar2   codice dell'utente
 RITORNA:     varchar2: lista dei gruppi a cui l'utente appartiene separati da
              virgola
 ANNOTAZIONI: Salvata nella directory ins di AD4 con nome GET_GRUT.CRF.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    15/03/2001 MM     Prima emissione.
 1    13/01/2003 MM     Aggiunta where condition: gruppo <> 'ric_abil'.
 2    26/10/2006 MM     Spostata la funzione nel package UTENTE.
******************************************************************************/
   BEGIN
      RETURN Utente.get_gruppi(p_utente, p_codice, p_descrizione);
   END;
/

