CREATE OR REPLACE PACKAGE Regione IS /* MASTER_LINK */
/******************************************************************************
 NOME:        REGIONE.
 DESCRIZIONE: Package della tabella REGIONI.
 ANNOTAZIONI: Versione 1.0
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/06/2005 MM     Prima emissione.
******************************************************************************/
FUNCTION  VERSIONE /* SLAVE_COPY */
RETURN VARCHAR2;
FUNCTION GET_REGIONE /* SLAVE_COPY */
( p_denominazione IN VARCHAR2)
RETURN NUMBER;
FUNCTION GET_ID /* SLAVE_COPY */
( p_denominazione IN VARCHAR2)
RETURN NUMBER;
FUNCTION GET_ID /* SLAVE_COPY */
( p_regione IN NUMBER)
RETURN NUMBER;
FUNCTION GET_DENOMINAZIONE /* SLAVE_COPY */
( p_regione IN NUMBER)
RETURN VARCHAR2;
END Regione;
/

