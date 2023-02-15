CREATE OR REPLACE PACKAGE Comune IS
/******************************************************************************
 NOME:        COMUNE.
 DESCRIZIONE: Package della tabella COMUNI.
 ANNOTAZIONI: Versione 1.0
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/06/2005 MM     Prima emissione.
 1    02/05/2007 MM     Create: GET_DENOMINAZIONE, GET_DENOMINAZIONE_AL1,
                        GET_DENOMINAZIONE_AL2, GET_DENOMINAZIONE_BREVE,
                        GET_DENOMINAZIONE_BREVE_AL1, GET_DENOMINAZIONE_BREVE_AL2,
                        IS_CAPOLUOGO_PROVINCIA, GET_CAP, GET_COMUNE_TRIBUNALE,
                        GET_COMUNE_DISTRETTO, GET_DATA_SOPPRESSIONE,
                        GET_COMUNE_FUSIONE, IS_SOPPRESSO, GET_SIGLA_CFIS,
                        GET_CONSOLATO, GET_TIPO_CONSOLATO.
2    27/07/2011  SNeg Create: GET_
3    10/02/2020  SNeg  Gestione nuova colonna data_istituzione
******************************************************************************/
   FUNCTION  VERSIONE
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE.
    DESCRIZIONE: Restituisce DENOMINAZIONE del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Denominazione del comune identificato dalla chiave.
   ******************************************************************************/
   Function GET_DENOMINAZIONE
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE%TYPE;
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_AL1.
    DESCRIZIONE: Restituisce prima DENOMINAZIONE alternativa del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Prima Denominazione alternativa del comune identificato dalla
                 chiave.
   ******************************************************************************/
   Function GET_DENOMINAZIONE_AL1
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE_AL1%TYPE;
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_AL2.
    DESCRIZIONE: Restituisce seconda DENOMINAZIONE alternativa del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Seconda Denominazione alternativa del comune identificato dalla
                 chiave.
   ******************************************************************************/
   Function GET_DENOMINAZIONE_AL2
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE_AL2%TYPE;
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_BREVE.
    DESCRIZIONE: Restituisce DENOMINAZIONE_BREVE del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Denominazione breve del comune identificato dalla chiave.
   ******************************************************************************/
   Function GET_DENOMINAZIONE_BREVE
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE_BREVE%TYPE;
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_BREVE_AL1.
    DESCRIZIONE: Restituisce prima DENOMINAZIONE_BREVE alternativa del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Prima denominazione breve alternativa del comune identificato
                 dalla chiave.
   ******************************************************************************/
   Function GET_DENOMINAZIONE_BREVE_AL1
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE_BREVE_AL1%TYPE;
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_BREVE_AL2.
    DESCRIZIONE: Restituisce seconda DENOMINAZIONE_BREVE alternativa del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Seconda denominazione breve alternativa del comune identificato
                 dalla chiave.
   ******************************************************************************/
   Function GET_DENOMINAZIONE_BREVE_AL2
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE_BREVE_AL2%TYPE;
   /******************************************************************************
    NOME:        IS_CAPOLUOGO_PROVINCIA.
    DESCRIZIONE: Restituisce 1 se il comune e' capoluogo di provincia, 0 se non lo e'.
    PARAMETRI:   Campi chiave.
    RITORNA:     NUMBER 1/0.
                 Se la select non trova record, ritorna null.
   ******************************************************************************/
   Function IS_CAPOLUOGO_PROVINCIA
   ( p_provincia IN number
   , p_comune IN number)
   RETURN number;
   /******************************************************************************
    NOME:        GET_CAP.
    DESCRIZIONE: Restituisce CAP del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     CAP del comune identificato dalla chiave.
   ******************************************************************************/
   Function GET_CAP
   ( p_provincia IN number
   , p_comune IN number)
   RETURN varchar2;
   /******************************************************************************
    NOME:         GET_COMUNE_TRIBUNALE.
    DESCRIZIONE: Seleziona il comune in cui ha sede il tribunale competente.
    ARGOMENTI:   Campi chiave.
                 p_provincia_tribunale OUT codice provincia in cui ha sede il tribunale
                 p_comune_tribunale    OUT codice comune in cui ha sede il tribunale
    RITORNA:     Denominazione del comune identificato dalla chiave.
   ******************************************************************************/
   Procedure GET_COMUNE_TRIBUNALE
   ( p_provincia IN number
   , p_comune IN number
   , p_provincia_tribunale IN OUT number
   , p_comune_tribunale IN OUT number);
   /******************************************************************************
    NOME:        GET_COMUNE_DISTRETTO.
    DESCRIZIONE: Seleziona il comune in cui ha sede il distretto.
    ARGOMENTI:   Campi chiave.
                 p_provincia_distretto OUT codice provincia in cui ha sede il distretto
                 p_comune_distretto    OUT codice comune in cui ha sede il distretto
    RITORNA:     Denominazione del comune identificato dalla chiave.
   ******************************************************************************/
   Procedure GET_COMUNE_DISTRETTO
   ( p_provincia IN number
   , p_comune IN number
   , p_provincia_distretto IN OUT number
   , p_comune_distretto IN OUT number);
   /******************************************************************************
    NOME:        GET_DATA_SOPPRESSIONE.
    DESCRIZIONE: Restituisce eventuale data di soppressione del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Data di soppressione del comune identificato dalla chiave in
                 formato dd/mm/yyyy.
   ******************************************************************************/
   Function GET_DATA_SOPPRESSIONE
   ( p_provincia IN number
   , p_comune IN number)
   RETURN varchar2;
   /******************************************************************************
    NOME:        GET_DATA_ISTITUZIONE.
    DESCRIZIONE: Restituisce eventuale data di istituzione del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Data di istituzione del comune identificato dalla chiave in
                 formato dd/mm/yyyy.
   ******************************************************************************/

   Function GET_DATA_ISTITUZIONE
   ( p_provincia IN number
   , p_comune IN number)
   RETURN varchar2;
   /******************************************************************************
    NOME:        GET_COMUNE_FUSIONE.
    DESCRIZIONE: Seleziona il comune in cui ha e' confluito.
    ARGOMENTI:   Campi chiave.
                 p_provincia_fusione OUT codice provincia in cui e' confluito
                 p_comune_fusione    OUT codice comune in cui e' confluito
    RITORNA:     Denominazione del comune identificato dalla chiave.
   ******************************************************************************/
   Procedure GET_COMUNE_FUSIONE
   ( p_provincia IN number
   , p_comune IN number
   , p_provincia_fusione IN OUT number
   , p_comune_fusione IN OUT number);
   /******************************************************************************
    NOME:        IS_SOPPRESSO.
    DESCRIZIONE: Restituisce 1 se il comune e soppresso, 0 se non lo e'.
    PARAMETRI:   Campi chiave.
    RITORNA:     NUMBER 1/0.
                 Se la select non trova record, ritorna NULL.
   ******************************************************************************/
   Function IS_SOPPRESSO
   ( p_provincia IN number
   , p_comune IN number)
   RETURN number;
   /******************************************************************************
    NOME:        GET_SIGLA_CFIS.
    DESCRIZIONE: Restituisce SIGLA_CFIS del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     SIGLA_CFIS del comune identificato dalla chiave.
   ******************************************************************************/
   Function GET_SIGLA_CFIS
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.SIGLA_CFIS%TYPE;
   /******************************************************************************
    NOME:        GET_CONSOLATO.
    DESCRIZIONE: Restituisce CONSOLATO del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     CONSOLATO del comune identificato dalla chiave.
   ******************************************************************************/
   Function GET_CONSOLATO
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.CONSOLATO%TYPE;
   /******************************************************************************
    NOME:        GET_TIPO_CONSOLATO.
    DESCRIZIONE: Restituisce TIPO_CONSOLATO del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     TIPO_CONSOLATO del comune identificato dalla chiave.
   ******************************************************************************/
   Function GET_TIPO_CONSOLATO
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.TIPO_CONSOLATO%TYPE;
   FUNCTION GET_COMUNE
   ( p_denominazione IN VARCHAR2
   , p_sigla_provincia IN VARCHAR2
   , p_soppresso IN NUMBER DEFAULT NULL)
   RETURN NUMBER;
   FUNCTION GET_PROVINCIA
   ( p_denominazione IN VARCHAR2
   , p_sigla_provincia IN VARCHAR2
   , p_soppresso IN NUMBER DEFAULT NULL)
   RETURN NUMBER;
   FUNCTION GET_CAP
   ( p_denominazione IN VARCHAR2
   , p_sigla_provincia IN VARCHAR2
   , p_soppresso IN NUMBER DEFAULT NULL)
   RETURN NUMBER;
   FUNCTION GET_CFIS
   ( p_denominazione IN VARCHAR2
   , p_sigla_provincia IN VARCHAR2
   , p_soppresso IN NUMBER DEFAULT NULL)
   RETURN VARCHAR2;
   FUNCTION IS_SOPPRESSO
   ( p_denominazione IN VARCHAR2
   , p_sigla_provincia IN VARCHAR2)
   RETURN NUMBER;
   Function GET_CODICE_ISTAT
   ( p_sigla_cfis IN varchar2
   , p_data IN date default SYSDATE)
   RETURN varchar2
   /******************************************************************************
    NOME:        GET_CODICE_ISTAT.
    DESCRIZIONE: Restituisce il CODICE ISTAT del comune ottenuto con:
                          lpad(to_char(provincia),3,'0')||lpad(to_char(comune),3,'0')
    PARAMETRI:   Campo SIGLA_CFIS e data alla quale ottenere il valore
    RITORNA:     GET_CODICE_ISTAT del comune identificato dalla sigla CFIS.
   ******************************************************************************/
   ;
END Comune;
/

