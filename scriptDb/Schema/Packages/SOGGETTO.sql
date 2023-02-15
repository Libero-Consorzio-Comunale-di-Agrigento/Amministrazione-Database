CREATE OR REPLACE PACKAGE Soggetto IS /* MASTER_LINK */
/******************************************************************************
 NOME:        SOGGETTO
 DESCRIZIONE: Package per gestione SOGGETTI.
              L'inizializzione del soggetto avviene tramite selezione da
           SOGGETTI; quest'ultima puo' essere una table od una vista sull'
           anagrafica di riferimento.
           L'aggiornamento, invece, avviene tramite la procedure SOGGETTI_PM;
           sara quest'ultima ad effettuare l'inserimento o aggiornamento del
           soggetto.
           L'integrazione con un'anagrafica deve prevedere, percio', la
           creazione della vista SOGGETTI e della procedure SOGGETTI_PM, mentre
           lascia inalterato il package SOGGETTO.
 DIPENDENZE:  Utilizzato da:
              - UTENTE.
           Utilizza TABLE:
              - COMUNI.
              - PROVINCE.
              - SOGGETTI.
              Utilizza PROCEDURE:
              - SOGGETTI_PM.
 ANNOTAZIONI: Salvato nella directory ins di AD4 nel file sogg.pkg.
 ECCEZIONI:.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 1    21/11/2005 MM     Modifica lunghezza campi NOME, TELEFONO, FAX e
                        INDIRIZZO_WEB.
 2    20/09/2006 MM     Gestione separata nome e cognome.
 3    06/03/2012 SNeg   Aggiunta is_soggetto_componente
 4    31/01/2018 SNeg   Aggiunta scegli_fra_soggetti
 5    26/09/2019 SNeg   Aggiunte: GET_COMPETENZA_ESCLUSIVA,SET_COMPETENZA_ESCLUSIVA,
                        set_data_aggiornamento, set_utente_aggiornamento Bug #36471
 NOTA: NON inserisco la get_dal in quanto nella tabella soggetti che esiste
 se non c'è as4 non c'è la colonna dal
 *****************************************************************************/
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
******************************************************************************/
   FUNCTION  VERSIONE /* SLAVE_COPY */
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        INIT.
 DESCRIZIONE: Svuota tutti gli attributi del soggetto (variabili del package body)
 ARGOMENTI:   -
******************************************************************************/
    PROCEDURE INIT;
/******************************************************************************
 NOME:        INITIALIZE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, inizializza tutti i
              suoi attributi.
 ARGOMENTI:   p_soggetto: numero identificativo del soggetto che si vuole
              inizializzare.
 ECCEZIONI:   20999, Soggetto inesistente.
******************************************************************************/
    PROCEDURE INITIALIZE
    (p_soggetto IN NUMBER);
-------------------------------------------------------------------------------
--                             LETTURA ATTRIBUTI.
-------------------------------------------------------------------------------
/******************************************************************************
 NOME:        GET_NOME.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il nome
              (cognome||'  '||nome).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
              p_completo :  se passato con valore 0, restituisce il solo nome.
******************************************************************************/
    FUNCTION GET_NOME /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N'
    , p_completo IN NUMBER DEFAULT 1)
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_COGNOME.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il
              cognome.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
   FUNCTION GET_COGNOME /* SLAVE_COPY */
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_DENOMINAZIONE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              denominazione (cognome||'  '||nome).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
   FUNCTION GET_DENOMINAZIONE /* SLAVE_COPY */
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_SESSO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il sesso
              (F / M).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_SESSO /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_CODICE_FISCALE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              fiscale.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_CODICE_FISCALE /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_INDIRIZZO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera l'indirizzo.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_INDIRIZZO /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_CAP.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il CAP di
              residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_CAP /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_COMUNE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              del comune di residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_COMUNE /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN NUMBER;
/******************************************************************************
 NOME:        GET_DES_COMUNE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              denominazione del comune di residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_DES_COMUNE /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_PROVINCIA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              della provincia di residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_PROVINCIA /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN NUMBER;
/******************************************************************************
 NOME:        GET_DES_PROVINCIA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              denominazione della provincia di residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_DES_PROVINCIA /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_PROVINCIA_SIGLA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              sigla della provincia di residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_PROVINCIA_SIGLA /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_INDIRIZZO_COMPLETO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera l'indirizzo
              di residenza completo di cap, comune e sigla della provincia.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_INDIRIZZO_COMPLETO /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_DATA_NASCITA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la data di
              nascita (come stringa in formato dd/mm/yyyy).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_DATA_NASCITA /* SLAVE_COPY */
    (p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_COMUNE_NAS.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              del comune di nascita.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_COMUNE_NAS /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN NUMBER;
/******************************************************************************
 NOME:        GET_DES_COMUNE_NAS.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              denominazione del comune di nascita.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_DES_COMUNE_NAS /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_PROVINCIA_NAS.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              della provincia di nascita.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_PROVINCIA_NAS /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN NUMBER;
/******************************************************************************
 NOME:        GET_DES_PROVINCIA_NAS.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              denominazione della provincia di nascita.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_DES_PROVINCIA_NAS /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_PROVINCIA_NAS_SIGLA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              sigla della provincia di nascita.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_PROVINCIA_NAS_SIGLA /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_TELEFONO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il numero
              di telefono (come stringa).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_TELEFONO /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_FAX.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il numero
              di fax (come stringa).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_FAX /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_INDIRIZZO_WEB.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera l'indirizzo
              web.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_INDIRIZZO_WEB /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_NOTE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera le note.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_NOTE /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_UTENTE_AGGIORNAMENTO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              dell'utente che ha effettuato le ultime modifiche al soggetto.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_UTENTE_AGGIORNAMENTO /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_DATA_AGGIORNAMENTO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la data in
              cui sono state effettuate le ultime modifiche al soggetto.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
    FUNCTION GET_DATA_AGGIORNAMENTO /* SLAVE_COPY */
    ( p_soggetto IN NUMBER
    , p_initialize IN VARCHAR2 DEFAULT 'N')
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_COMPETENZA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il progetto
              di competenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
   FUNCTION GET_COMPETENZA /* SLAVE_COPY */
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_COMPETENZA_ESCLUSIVA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il valore
              di competenza esclusiva.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
******************************************************************************/
   FUNCTION GET_COMPETENZA_ESCLUSIVA /* SLAVE_COPY */
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2;
-------------------------------------------------------------------------------
--                             SCRITTURA ATTRIBUTI.
-------------------------------------------------------------------------------
/******************************************************************************
 NOME:        SET_NOME.
 DESCRIZIONE: Modifica l'attributo NOME o denominazione del soggetto corrente.
 ARGOMENTI:   p_nome: denominazione.
              p_completo :  se passato con valore 0, modifica il solo nome.
******************************************************************************/
   PROCEDURE SET_NOME                ( p_nome IN VARCHAR2
                                     , p_completo IN NUMBER DEFAULT 1 );
/******************************************************************************
 NOME:        SET_COGNOME.
 DESCRIZIONE: Modifica l'attributo COGNOME del soggetto corrente.
 ARGOMENTI:   p_cognome: cognome.
******************************************************************************/
   PROCEDURE SET_COGNOME             ( p_cognome IN VARCHAR2);
/******************************************************************************
 NOME:        SET_DENOMINAZIONE.
 DESCRIZIONE: Modifica l'attributo NOME o denominazione del soggetto corrente.
 ARGOMENTI:   p_denominazione: denominazione.
******************************************************************************/
   PROCEDURE SET_DENOMINAZIONE       ( p_denominazione IN VARCHAR2);
/******************************************************************************
 NOME:        SET_SESSO.
 DESCRIZIONE: Modifica l'attributo SESSO del soggetto corrente.
 ARGOMENTI:   p_sesso: sesso (M, F, null).
 ECCEZIONI:   20998, Valore non ammesso (Valori possibili: F, M o null).
******************************************************************************/
    PROCEDURE SET_SESSO          ( p_sesso IN VARCHAR2);
/******************************************************************************
 NOME:        SET_CODICE_FISCALE.
 DESCRIZIONE: Modifica l'attributo CODICE FISCALE del soggetto corrente.
 ARGOMENTI:   p_codice_fiscale: codice fiscale.
******************************************************************************/
    PROCEDURE SET_CODICE_FISCALE ( p_codice_fiscale IN VARCHAR2);
/******************************************************************************
 NOME:        SET_INDIRIZZO.
 DESCRIZIONE: Modifica l'attributo INDIRIZZO del soggetto corrente.
 ARGOMENTI:   p_indirizzo: indirizzo.
******************************************************************************/
    PROCEDURE SET_INDIRIZZO      ( p_indirizzo IN VARCHAR2);
/******************************************************************************
 NOME:        SET_CAP.
 DESCRIZIONE: Modifica l'attributo CAP del soggetto corrente.
 ARGOMENTI:   p_cap: cap.
******************************************************************************/
    PROCEDURE SET_CAP            ( p_cap IN VARCHAR2);
/******************************************************************************
 NOME:        SET_COMUNE.
 DESCRIZIONE: Modifica l'attributo COMUNE del soggetto corrente.
 ARGOMENTI:   p_comune: codice comune di residenza.
******************************************************************************/
    PROCEDURE SET_COMUNE         ( p_comune IN NUMBER);
/******************************************************************************
 NOME:        SET_PROVINCIA.
 DESCRIZIONE: Modifica l'attributo PROVINCIA del soggetto corrente.
 ARGOMENTI:   p_provincia: codice provincia di residenza.
******************************************************************************/
    PROCEDURE SET_PROVINCIA      ( p_provincia IN NUMBER);
/******************************************************************************
 NOME:        SET_DATA_NASCITA.
 DESCRIZIONE: Modifica l'attributo DATA DI NASCITA del soggetto corrente.
 ARGOMENTI:   p_data_nascita: stringa contenente la data di nascita in formato
              dd/mm/yyyy.
******************************************************************************/
    PROCEDURE SET_DATA_NASCITA   ( p_data_nascita IN VARCHAR2);
/******************************************************************************
 NOME:        SET_COMUNE_NAS.
 DESCRIZIONE: Modifica l'attributo COMUNE DI NASCITA del soggetto corrente.
 ARGOMENTI:   p_comune: codice comune di nascita.
******************************************************************************/
    PROCEDURE SET_COMUNE_NAS     ( p_comune IN NUMBER);
/******************************************************************************
 NOME:        SET_PROVINCIA_NAS.
 DESCRIZIONE: Modifica l'attributo PROVINCIA DI NASCITA del soggetto corrente.
 ARGOMENTI:   p_provincia: codice provincia di nascita.
******************************************************************************/
    PROCEDURE SET_PROVINCIA_NAS  ( p_provincia IN NUMBER);
/******************************************************************************
 NOME:        SET_TELEFONO.
 DESCRIZIONE: Modifica l'attributo TELEFONO del soggetto corrente.
 ARGOMENTI:   p_telefono: numero di telefono.
******************************************************************************/
    PROCEDURE SET_TELEFONO       ( p_telefono IN VARCHAR2);
/******************************************************************************
 NOME:        SET_FAX.
 DESCRIZIONE: Modifica l'attributo FAX del soggetto corrente.
 ARGOMENTI:   p_fax: numero di fax.
******************************************************************************/
    PROCEDURE SET_FAX            ( p_fax IN VARCHAR2);
/******************************************************************************
 NOME:        SET_INDIRIZZO_WEB.
 DESCRIZIONE: Modifica l'attributo INDIRIZZO WEB del soggetto corrente.
 ARGOMENTI:   p_indirizzo_web: indirizzo web.
******************************************************************************/
    PROCEDURE SET_INDIRIZZO_WEB  ( p_indirizzo_web IN VARCHAR2);
/******************************************************************************
 NOME:        SET_NOTE.
 DESCRIZIONE: Modifica l'attributo NOTE del soggetto corrente.
 ARGOMENTI:   p_note: note.
******************************************************************************/
    PROCEDURE SET_NOTE           ( p_note IN VARCHAR2);
/******************************************************************************
 NOME:        SET_COMPETENZA.
 DESCRIZIONE: Modifica l'attributo COMPETENZA del soggetto corrente.
 ARGOMENTI:   p_competenza: progetto di competenza.
******************************************************************************/
   PROCEDURE SET_COMPETENZA
   ( p_competenza IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_COMPETENZA_ESCLUSIVA.
 DESCRIZIONE: Modifica l'attributo COMPETENZA_ESCLUSIVA del soggetto corrente.
 ARGOMENTI:   p_competenza_esclusiva: competenza esclusiva.
******************************************************************************/
   PROCEDURE SET_COMPETENZA_ESCLUSIVA
   ( p_competenza_esclusiva IN varchar2 );
/******************************************************************************
 NOME:        SET_UTENTE_AGGIORNAMENTO.
 DESCRIZIONE: Modifica l'attributo UTENTE_AGGIORNAMENTO del soggetto corrente.
 ARGOMENTI:   p_utente_aggiornamento: utente di aggiornamento.
******************************************************************************/
   PROCEDURE SET_UTENTE_AGGIORNAMENTO
   ( p_utente_aggiornamento IN VARCHAR2 );
-------------------------------------------------------------------------------
--                          INSERT o UPDATE di SOGGETTI.
-------------------------------------------------------------------------------
/******************************************************************************
 NOME:        UPDATE_SOGGETTO.
 DESCRIZIONE: Insert o update di soggetti.
 ARGOMENTI:   p_soggetto: numero identificativo del soggetto inserito o da
                          aggiornare.
              p_modifica: Totale (T) o Parziale (P).
                       Se totale:
                             tutti i valori passati vengono aggiornati
                             indipendentemente   dal fatto che siano nulli o
                             meno,
                          altrimenti:
                             aggiorna i soli dati significativi (not null).
                          Default: 'T'.
 ECCEZIONI:   20999, Impossibile associare al Soggetto un nome vuoto.
 ANNOTAZIONI: Lancia la procedure SOGGETTI_PM.
******************************************************************************/
    PROCEDURE UPDATE_SOGGETTO ( p_soggetto IN OUT NUMBER
                              , p_modifica IN     VARCHAR2 DEFAULT 'T');
      function is_soggetto_componente(P_ni number) return afc_error.t_error_number;
       /******************************************************************************
      NOME:        is_soggetto_componente
      DESCRIZIONE: Verifica se l'ni del soggetto e' utilizzato nella tabella so4.COMPONENTI
      PARAMETRI:   p_ni                    ni di as4.anagrafe_soggetti
      RITORNA:     number
                   1 : esiste
                   0 : non esiste
      REVISIONI:
      Rev.  Data        Autore    Descrizione
      ----  ----------  --------  ----------------------------------------------------
      000  06/03/2012  SNeg        Prima emissione.
      ******************************************************************************/
      FUNCTION scegli_fra_soggetti
   (p_codice_fiscale in soggetti.codice_fiscale%TYPE
   ,p_competenza in soggetti.competenza%TYPE default '%'
   ) RETURN number;
/******************************************************************************
 NOME:        scegli_fra_soggetti.
 DESCRIZIONE: Sceglie fra i soggetti in soggetti con il codice fiscale passato come parametro quello
                       da utilizzare nell'applicativo chiamante per assegnare nuovi dirititi di accesso o da usare
                       come componente.
                       COPIATO da AS4_ANAGRAFE_SOGGETTI_PKG
 PARAMETRI:   p_codice_fiscale                IN   p_codice_fiscale
              p_competenza      IN   competenza
 RITORNA:     number
              se trovato gia record
              altrimenti,
                   NULL.
 NOTE:        Sceglie il soggetto secondo le seguenti precedenze:
              1. Il soggetto ha un utente abbinato
              2. Il soggetto ha un utente e diritti di accesso
              3. Il soggetto ha la competenza indicata come parametro
              Nel caso in cui piu soggetti soddisfino i requisiti ne prende
              uno a caso (max).
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  09/06/2015 SNegroni   copia da. as4.anagrafe_soggetti_pkg
 001  24/11/2016 SNegroni  Modificata in modo che legga da SOGGETTI.
 010  27/03/2017 SNeg Eliminato controllo del codice fiscale per consentire inserimento
                     di soggetti con codice identificativo estero
******************************************************************************/
END Soggetto;
/

