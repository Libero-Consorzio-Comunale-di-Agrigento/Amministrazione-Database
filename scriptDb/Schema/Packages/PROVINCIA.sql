CREATE OR REPLACE PACKAGE Provincia IS /* MASTER_LINK */
/******************************************************************************
 NOME:        PROVINCIA.
 DESCRIZIONE: Package della tabella PROVINCE.
 ANNOTAZIONI: Versione 1.0
 REVISIONI:
 Rev. Data        Autore Descrizione
 ---- ----------  ------ ------------------------------------------------------
 0    03/06/2005  MM     Prima emissione.
 1    02/05/2007  MM     Create: GET_DENOMINAZIONE_AL1, GET_DENOMINAZIONE_AL2,
                         GET_SIGLA.
******************************************************************************/
FUNCTION  VERSIONE /* SLAVE_COPY */
RETURN VARCHAR2;
FUNCTION GET_PROVINCIA /* SLAVE_COPY */
( p_denominazione IN VARCHAR2, p_sigla IN VARCHAR2 DEFAULT NULL)
RETURN province.provincia%TYPE;
FUNCTION GET_REGIONE /* SLAVE_COPY */
( p_denominazione IN VARCHAR2)
RETURN province.regione%TYPE;
FUNCTION GET_REGIONE /* SLAVE_COPY */
( p_provincia IN NUMBER)
RETURN province.regione%TYPE;
FUNCTION GET_DENOMINAZIONE /* SLAVE_COPY */
( p_provincia IN NUMBER)
RETURN province.DENOMINAZIONE%TYPE;
FUNCTION GET_DENOMINAZIONE_AL1 /* SLAVE_COPY */
( p_provincia IN NUMBER)
RETURN province.DENOMINAZIONE_AL1%TYPE;
FUNCTION GET_DENOMINAZIONE_AL2 /* SLAVE_COPY */
( p_provincia IN NUMBER)
RETURN province.DENOMINAZIONE_AL2%TYPE;
FUNCTION GET_SIGLA /* SLAVE_COPY */
( p_provincia IN NUMBER)
RETURN province.SIGLA%TYPE;
END Provincia;
/

