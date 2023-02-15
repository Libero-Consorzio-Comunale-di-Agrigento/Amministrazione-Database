CREATE OR REPLACE PACKAGE BODY Soggetto IS
/******************************************************************************
Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 03  15/03/2010 SNeg   Get non usano piu variabili di package
 04  11/08/2010 MM     Correzione passaggio date come stringhe
 05  13/06/2011 SNeg   Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
 06  06/03/2012 SNeg   Aggiunta is_soggetto_componente
 07  17/01/2013 SNeg   Tolti spazi in fondo se nome vuoto
 08  31/01/2018 SNeg   Aggiunta scegli_fra_soggetti
 09  01/07/2019 SNegroni Scegliere soggetto in struttura con il codice fiscale dato Bug #36242
 10  26/09/2019 SNeg   Aggiunte: GET_COMPETENZA_ESCLUSIVA,SET_COMPETENZA_ESCLUSIVA,
                        set_data_aggiornamento, set_utente_aggiornamento Bug #36471
 11  04/02/2021 SNeg   Modificato dimensionamento di alcuni campi Bug #48033
******************************************************************************/
------------------------------------------------------------
-- Variabili del package body.
------------------------------------------------------------
d_body_soggetto       NUMBER(8);
d_body_nome           VARCHAR2(240);
d_body_cognome        VARCHAR2(240);
d_body_denominazione  VARCHAR2(240);
d_body_sesso          VARCHAR2(1);
d_body_data_nascita   VARCHAR2(10);
d_body_provincia_nas  NUMBER(3);
d_body_comune_nas     NUMBER(3);
d_body_codice_fiscale VARCHAR2(16);
d_body_indirizzo      VARCHAR2(120); -- rev. 11
d_body_cap            VARCHAR2(5);
d_body_comune         NUMBER(3);
d_body_provincia      NUMBER(3);
d_body_telefono       VARCHAR2(100); -- rev. 11
d_body_fax            VARCHAR2(100); -- rev. 11
d_body_indirizzo_web  VARCHAR2(2000);
d_body_note           VARCHAR2(240);
d_body_utente_agg     VARCHAR2(8);
d_body_data_agg       VARCHAR2(10);
d_body_competenza     varchar2(10);
d_body_competenza_esclusiva     varchar2(10);
   FUNCTION VERSIONE /* SLAVE_COPY */
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 ECCEZIONI:   --
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
******************************************************************************/
   RETURN VARCHAR2
   IS
   BEGIN
      RETURN 'V1.9';
   END VERSIONE;
   PROCEDURE INIT
/******************************************************************************
 NOME:        INIT.
 DESCRIZIONE: Svuota tutti gli attributi del soggetto (variabili del package body)
 ARGOMENTI:   -
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 2    20/09/2006 MM     Gestione separata nome e cognome (aggiunte var
                        d_body_cognome e d_body_denominazione).
******************************************************************************/
   IS
   BEGIN
      d_body_soggetto       := TO_NUMBER(NULL);
      d_body_nome           := TO_CHAR(NULL);
      d_body_cognome        := TO_CHAR(NULL);
      d_body_denominazione  := TO_CHAR(NULL);
      d_body_sesso          := TO_CHAR(NULL);
      d_body_data_nascita   := TO_CHAR(NULL);
      d_body_provincia_nas  := TO_NUMBER(NULL);
      d_body_comune_nas     := TO_NUMBER(NULL);
      d_body_codice_fiscale := TO_CHAR(NULL);
      d_body_indirizzo      := TO_CHAR(NULL);
      d_body_cap            := TO_CHAR(NULL);
      d_body_comune         := TO_NUMBER(NULL);
      d_body_provincia      := TO_NUMBER(NULL);
      d_body_telefono       := TO_CHAR(NULL);
      d_body_fax            := TO_CHAR(NULL);
      d_body_indirizzo_web  := TO_CHAR(NULL);
      d_body_note           := TO_CHAR(NULL);
      d_body_utente_agg     := TO_CHAR(NULL);
      d_body_data_agg       := TO_CHAR(NULL);
      d_body_competenza     := TO_CHAR(NULL);
      d_body_competenza_esclusiva     := TO_CHAR(NULL);
   END INIT;
   PROCEDURE INITIALIZE
/******************************************************************************
 NOME:        INITIALIZE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, inizializza tutti i
              suoi attributi.
 ARGOMENTI:   p_soggetto: numero identificativo del soggetto che si vuole
              inizializzare.
 ECCEZIONI:   20999, Soggetto inesistente.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 2    20/09/2006 MM     Gestione separata nome e cognome (aggiunte var
                        d_body_cognome e d_body_denominazione).
******************************************************************************/
   (p_soggetto IN NUMBER)
   IS
   d_esiste  NUMBER(1);
   d_pointer NUMBER;
   BEGIN
      BEGIN
         INIT;
         SELECT COUNT(1)
           INTO d_esiste
           FROM SOGGETTI
          WHERE Soggetto = p_soggetto
         ;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            INIT;
            RAISE_APPLICATION_ERROR(-20999,'Soggetto inesistente.');
         WHEN OTHERS THEN
            INIT;
            RAISE;
      END;
      BEGIN
         INIT;
         SELECT p_soggetto, SOGGETTI.nome,
                SOGGETTI.sesso, TO_CHAR(SOGGETTI.data_nascita,'dd/mm/yyyy'),
                SOGGETTI.provincia_nas, SOGGETTI.comune_nas, SOGGETTI.Codice_Fiscale,
                SOGGETTI.indirizzo, SOGGETTI.cap, SOGGETTI.Comune, SOGGETTI.Provincia,
                SOGGETTI.telefono, SOGGETTI.fax, SOGGETTI.indirizzo_web, SOGGETTI.note,
                SOGGETTI.utente_aggiornamento,
                TO_CHAR(SOGGETTI.data_aggiornamento,'dd/mm/yyyy'), competenza,
                competenza_esclusiva
           INTO d_body_soggetto, d_body_denominazione,
                d_body_sesso, d_body_data_nascita, d_body_provincia_nas,
                d_body_comune_nas, d_body_codice_fiscale, d_body_indirizzo,
                d_body_cap, d_body_comune, d_body_provincia, d_body_telefono,
                d_body_fax, d_body_indirizzo_web, d_body_note,
                d_body_utente_agg, d_body_data_agg, d_body_competenza,
                d_body_competenza_esclusiva
           FROM SOGGETTI
          WHERE Soggetto = p_soggetto
         ;
         d_pointer := INSTR(d_body_denominazione,'  ');
         IF d_pointer = 0 THEN
            d_body_cognome := RTRIM(d_body_denominazione);
            d_body_nome    := NULL;
         ELSE
            d_body_cognome := RTRIM(SUBSTR(d_body_denominazione, 1, d_pointer - 1 ));
            d_body_nome    := RTRIM(SUBSTR(d_body_denominazione, d_pointer + 2 ));
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            INIT;
            d_body_soggetto := p_soggetto;
         WHEN OTHERS THEN
            INIT;
            RAISE;
      END;
   END INITIALIZE;
   FUNCTION GET_NOME
/******************************************************************************
 NOME:        GET_NOME.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il nome
              o la denominazione (cognome||'  '||nome).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
              p_completo :  se passato con valore 0, restituisce il solo nome.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 2    20/09/2006 MM     Gestione separata nome e cognome.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_completo IN NUMBER DEFAULT 1)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return VARCHAR2(2000);
   BEGIN
         IF p_completo = 0 THEN
        IF INSTR(get_denominazione(p_soggetto),'  ') = 0 THEN
           d_return := NULL;
        ELSE
           d_return := RTRIM(SUBSTR(get_denominazione(p_soggetto),
                        INSTR(get_denominazione(p_soggetto),'  ') + 2 ));
        END IF;
     ELSE
        d_return := get_denominazione(p_soggetto);
     END IF;
     RETURN d_return;
   END GET_NOME;
   FUNCTION GET_COGNOME
/******************************************************************************
 NOME:        GET_COGNOME.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il
              cognome.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    20/09/2006 MM     Prima emissione.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_return varchar2(2000);
   BEGIN
         IF INSTR(get_denominazione(p_soggetto),'  ') = 0 THEN
            d_return := RTRIM(get_denominazione(p_soggetto));
         ELSE
            d_return := RTRIM(SUBSTR(get_denominazione(p_soggetto), 1,
                        INSTR(get_denominazione(p_soggetto),'  ') - 1 ));
         END IF;
     RETURN d_return;
   END GET_COGNOME;
   FUNCTION GET_DENOMINAZIONE
/******************************************************************************
 NOME:        GET_DENOMINAZIONE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              denominazione (cognome||'  '||nome).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    20/09/2006 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_denominazione   soggetti.nome%TYPE;
   BEGIN
      SELECT nome
        INTO d_denominazione
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_denominazione;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
        WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente attributi del soggetto ' || p_soggetto);
         WHEN OTHERS THEN
            RAISE;
   END GET_DENOMINAZIONE;
   FUNCTION GET_SESSO
/******************************************************************************
 NOME:        GET_SESSO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il sesso
              (F / M).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_sesso   soggetti.sesso%TYPE;
   BEGIN
      SELECT sesso
        INTO d_sesso
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_sesso;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
       WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente il sesso del soggetto ' || p_soggetto);
         WHEN OTHERS THEN
            RAISE;
   END GET_SESSO;
   FUNCTION GET_CODICE_FISCALE
/******************************************************************************
 NOME:        GET_CODICE_FISCALE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              fiscale.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_codice_fiscale   soggetti.codice_fiscale%TYPE;
   BEGIN
      SELECT codice_fiscale
        INTO d_codice_fiscale
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_codice_fiscale;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
        WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente il codice fiscale del soggetto ' || p_soggetto);
         WHEN OTHERS THEN
            RAISE;
   END GET_CODICE_FISCALE;
   FUNCTION GET_INDIRIZZO
/******************************************************************************
 NOME:        GET_INDIRIZZO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera l'indirizzo.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_indirizzo   soggetti.indirizzo%TYPE;
   BEGIN
      SELECT indirizzo
        INTO d_indirizzo
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_indirizzo;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
         WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente indirizzo del soggetto ' || p_soggetto);
         WHEN OTHERS THEN
            RAISE;
   END GET_INDIRIZZO;
   FUNCTION GET_CAP
/******************************************************************************
 NOME:        GET_CAP.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il CAP di
              residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_cap   soggetti.cap%TYPE;
   BEGIN
      SELECT cap
        INTO d_cap
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_cap;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
         WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente il cap del soggetto ' || p_soggetto);
         WHEN OTHERS THEN
            RAISE;
   END GET_CAP;
   FUNCTION GET_COMUNE
/******************************************************************************
 NOME:        GET_COMUNE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              del comune di residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN NUMBER
   IS /* SLAVE_COPY */
      d_comune   soggetti.comune%TYPE;
   BEGIN
      SELECT comune
        INTO d_comune
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_comune;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
        WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente il comune del soggetto ' || p_soggetto);
         WHEN OTHERS THEN
            RAISE;
   END GET_COMUNE;
   FUNCTION GET_DES_COMUNE
/******************************************************************************
 NOME:        GET_DES_COMUNE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              denominazione del comune di residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_return VARCHAR2(2000);
   BEGIN
      BEGIN
         SELECT denominazione
           INTO d_return
           FROM COMUNI
          WHERE COMUNI.Comune          = get_comune(p_soggetto)
            AND COMUNI.provincia_stato = get_provincia(p_soggetto)
         ;
      EXCEPTION WHEN NO_DATA_FOUND THEN
         d_return := TO_CHAR(NULL);
      END;
      RETURN d_return;
   END GET_DES_COMUNE;
   FUNCTION GET_PROVINCIA
/******************************************************************************
 NOME:        GET_PROVINCIA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              della provincia di residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN NUMBER
   IS /* SLAVE_COPY */
       d_provincia   soggetti.provincia%TYPE;
   BEGIN
      SELECT provincia
        INTO d_provincia
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_provincia;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
         WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamentela provincia del soggetto ' || p_soggetto);
         WHEN OTHERS THEN
            RAISE;
   END GET_PROVINCIA;
   FUNCTION GET_DES_PROVINCIA
/******************************************************************************
 NOME:        GET_DES_PROVINCIA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              denominazione della provincia di residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_return VARCHAR2(2000);
   BEGIN
      BEGIN
         SELECT denominazione
           INTO d_return
           FROM PROVINCE
          WHERE PROVINCE.Provincia = get_provincia(p_soggetto)
         ;
      EXCEPTION WHEN NO_DATA_FOUND THEN
         d_return := TO_CHAR(NULL);
      END;
      RETURN d_return;
   END GET_DES_PROVINCIA;
   FUNCTION GET_PROVINCIA_SIGLA
/******************************************************************************
 NOME:        GET_PROVINCIA_SIGLA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              sigla della provincia di residenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_return VARCHAR2(2000);
   BEGIN
      BEGIN
         SELECT sigla
           INTO d_return
           FROM PROVINCE
          WHERE PROVINCE.Provincia = get_provincia(p_soggetto)
         ;
      EXCEPTION WHEN NO_DATA_FOUND THEN
         d_return := TO_CHAR(NULL);
      END;
      RETURN d_return;
   END GET_PROVINCIA_SIGLA;
   FUNCTION GET_INDIRIZZO_COMPLETO
/******************************************************************************
 NOME:        GET_INDIRIZZO_COMPLETO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera l'indirizzo
              di residenza completo di cap, comune e sigla della provincia.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_return VARCHAR2(2000);
   BEGIN
      BEGIN
--     dbms_output.PUT_LINE(d_body_comune||' '||d_body_provincia||' '||d_body_soggetto);
         SELECT get_indirizzo(p_soggetto)||
                DECODE(get_cap(p_soggetto),NULL,'',' - '||get_cap(p_soggetto))||
                DECODE(get_comune(p_soggetto),NULL,'',' '||COMUNI.denominazione||
                DECODE(get_provincia(p_soggetto),NULL,'',' ('||PROVINCE.sigla||')'))
           INTO d_return
           FROM COMUNI, PROVINCE
          WHERE COMUNI.Comune(+)          = get_comune(p_soggetto)
            AND COMUNI.provincia_stato(+) = get_provincia(p_soggetto)
            AND PROVINCE.Provincia(+)     = get_provincia(p_soggetto)
         ;
      EXCEPTION WHEN NO_DATA_FOUND THEN
         d_return := TO_CHAR(NULL);
      END;
      RETURN d_return;
   END GET_INDIRIZZO_COMPLETO;
   FUNCTION GET_DATA_NASCITA
/******************************************************************************
 NOME:        GET_DATA_NASCITA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la data di
              nascita (come stringa in formato dd/mm/yyyy).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_data_nascita   varchar2(10);
   BEGIN
      SELECT TO_CHAR(data_nascita, 'DD/MM/YYYY')
        INTO d_data_nascita
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_data_nascita;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
          WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente la data di nascita del soggetto ' || p_soggetto);
        WHEN OTHERS THEN
            RAISE;
   END GET_DATA_NASCITA;
   FUNCTION GET_COMUNE_NAS
/******************************************************************************
 NOME:        GET_COMUNE_NAS.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              del comune di nascita.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN NUMBER
   IS /* SLAVE_COPY */
      d_comune_nas   soggetti.comune_nas%TYPE;
   BEGIN
      SELECT comune_nas
        INTO d_comune_nas
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_comune_nas;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
          WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente il comune di nascita del soggetto ' || p_soggetto);
        WHEN OTHERS THEN
            RAISE;
   END GET_COMUNE_NAS;
   FUNCTION GET_DES_COMUNE_NAS
/******************************************************************************
 NOME:        GET_DES_COMUNE_NAS.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              denominazione del comune di nascita.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_return VARCHAR2(2000);
   BEGIN
      BEGIN
         SELECT denominazione
           INTO d_return
           FROM COMUNI
          WHERE COMUNI.Comune          = get_comune_nas(p_soggetto)
            AND COMUNI.provincia_stato = get_provincia_nas(p_soggetto)
         ;
      EXCEPTION WHEN NO_DATA_FOUND THEN
         d_return := TO_CHAR(NULL);
      END;
      RETURN d_return;
   END GET_DES_COMUNE_NAS;
   FUNCTION GET_PROVINCIA_NAS
/******************************************************************************
 NOME:        GET_PROVINCIA_NAS.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              della provincia di nascita.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN NUMBER
   IS /* SLAVE_COPY */
        d_provincia_nas   soggetti.provincia_nas%TYPE;
   BEGIN
      SELECT provincia_nas
        INTO d_provincia_nas
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_provincia_nas;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
          WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente la provincia di nascita del soggetto ' || p_soggetto);
        WHEN OTHERS THEN
            RAISE;
   END GET_PROVINCIA_NAS;
   FUNCTION GET_DES_PROVINCIA_NAS
/******************************************************************************
 NOME:        GET_DES_PROVINCIA_NAS.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              denominazione della provincia di nascita.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_return VARCHAR2(2000);
   BEGIN
      BEGIN
         SELECT denominazione
           INTO d_return
           FROM PROVINCE
          WHERE PROVINCE.Provincia = get_provincia_nas(p_soggetto)
         ;
      EXCEPTION WHEN NO_DATA_FOUND THEN
         d_return := TO_CHAR(NULL);
      END;
      RETURN d_return;
   END GET_DES_PROVINCIA_NAS;
   FUNCTION GET_PROVINCIA_NAS_SIGLA
/******************************************************************************
 NOME:        GET_PROVINCIA_NAS_SIGLA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la
              sigla della provincia di nascita.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_return VARCHAR2(2000);
   BEGIN
      BEGIN
         SELECT sigla
           INTO d_return
           FROM PROVINCE
          WHERE PROVINCE.Provincia = get_provincia_nas(p_soggetto)
         ;
      EXCEPTION WHEN NO_DATA_FOUND THEN
         d_return := TO_CHAR(NULL);
      END;
      RETURN d_return;
   END GET_PROVINCIA_NAS_SIGLA;
   FUNCTION GET_TELEFONO
/******************************************************************************
 NOME:        GET_TELEFONO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il numero
              di telefono (come stringa).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_telefono   soggetti.telefono%TYPE;
   BEGIN
      SELECT telefono
        INTO d_telefono
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_telefono;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
          WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente il telefono del soggetto ' || p_soggetto);
        WHEN OTHERS THEN
            RAISE;
   END GET_TELEFONO;
   FUNCTION GET_INDIRIZZO_WEB
/******************************************************************************
 NOME:        GET_INDIRIZZO_WEB.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera l'indirizzo
              web.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_indirizzo_web   soggetti.indirizzo_web%TYPE;
   BEGIN
      SELECT indirizzo_web
        INTO d_indirizzo_web
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_indirizzo_web;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
          WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente indirizzo web del soggetto ' || p_soggetto);
        WHEN OTHERS THEN
            RAISE;
   END GET_INDIRIZZO_WEB;
   FUNCTION GET_NOTE
/******************************************************************************
 NOME:        GET_NOTE.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera le note.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_note   soggetti.note%TYPE;
   BEGIN
      SELECT note
        INTO d_note
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_note;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
          WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente le note del soggetto ' || p_soggetto);
        WHEN OTHERS THEN
            RAISE;
   END GET_NOTE;
   FUNCTION GET_UTENTE_AGGIORNAMENTO
/******************************************************************************
 NOME:        GET_UTENTE_AGGIORNAMENTO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il codice
              dell'utente che ha effettuato le ultime modifiche al soggetto.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_utente_agg   soggetti.utente_aggiornamento%TYPE;
   BEGIN
      SELECT utente_aggiornamento
        INTO d_utente_agg
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_utente_agg;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
          WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente utente di aggiornamento del soggetto ' || p_soggetto);
        WHEN OTHERS THEN
            RAISE;
   END GET_UTENTE_AGGIORNAMENTO;
   FUNCTION GET_DATA_AGGIORNAMENTO
/******************************************************************************
 NOME:        GET_DATA_AGGIORNAMENTO.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera la data in
              cui sono state effettuate le ultime modifiche al soggetto.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_data_agg   varchar2(10);
   BEGIN
      SELECT TO_CHAR(data_aggiornamento, 'DD/MM/YYYY')
        INTO d_data_agg
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_data_agg;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
         WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente la data di aggiornamento del soggetto ' || p_soggetto);
         WHEN OTHERS THEN
            RAISE;
   END GET_DATA_AGGIORNAMENTO;
   FUNCTION GET_FAX
/******************************************************************************
 NOME:        GET_FAX.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il numero
              di fax (come stringa).
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_fax   soggetti.fax%TYPE;
   BEGIN
      SELECT fax
        INTO d_fax
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_fax;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
          WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente il fax del soggetto ' || p_soggetto);
        WHEN OTHERS THEN
            RAISE;
   END GET_FAX;
   FUNCTION GET_COMPETENZA
/******************************************************************************
 NOME:        GET_COMPETENZA.
 DESCRIZIONE: Dato il numero identificativo del soggetto, recupera il progetto
              di competenza.
 ARGOMENTI:   p_soggetto:   numero identificativo del soggetto.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5   13/06/2011 SNeg  Modificata segnalazione di errore in caso di piu soggetti con stesso ni e al nullo.
******************************************************************************/
   ( p_soggetto IN NUMBER
   , p_initialize IN VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_competenza   soggetti.competenza%TYPE;
   BEGIN
      SELECT competenza
        INTO d_competenza
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_competenza;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
          WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente la competenza del soggetto ' || p_soggetto);
        WHEN OTHERS THEN
            RAISE;
   END GET_COMPETENZA;

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
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   d_competenza_esclusiva   soggetti.competenza_esclusiva%TYPE;
   BEGIN
      SELECT competenza_esclusiva
        INTO d_competenza_esclusiva
        FROM soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_competenza_esclusiva;
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
            return null;
          WHEN TOO_MANY_ROWS THEN
            raise_application_error (-20999, 'Impossibile determinare univocamente la competenza esclusiva del soggetto ' || p_soggetto);
        WHEN OTHERS THEN
            RAISE;
   END GET_COMPETENZA_ESCLUSIVA;

   PROCEDURE SET_NOME
/******************************************************************************
 NOME:        SET_NOME.
 DESCRIZIONE: Modifica l'attributo NOME o denominazione del soggetto corrente.
 ARGOMENTI:   p_nome: denominazione.
              p_completo :  se passato con valore 0, modifica il solo nome.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 2    20/09/2006 MM     Gestione separata nome e cognome.
 7  17/01/2013 SNeg Tolti spazi in fondo se nome vuoto
******************************************************************************/
   ( p_nome IN VARCHAR2
   , p_completo IN NUMBER DEFAULT 1 )
   IS
      d_return  VARCHAR2(2000);
      d_pointer NUMBER;
   BEGIN
      IF p_completo = 0 THEN
        d_body_nome := p_nome;
        if d_body_nome is not null then
         d_body_denominazione := SUBSTR(d_body_cognome||'  '||d_body_nome,1,240);
        elsif d_body_nome is null then
         d_body_denominazione := SUBSTR(d_body_cognome,1,240);
        end if;
      ELSE
         d_body_denominazione := p_nome;
         d_pointer := INSTR(d_body_denominazione,'  ');
         IF d_pointer = 0 THEN
            d_body_cognome := RTRIM(d_body_denominazione);
            d_body_nome    := NULL;
         ELSE
            d_body_cognome := RTRIM(SUBSTR(d_body_denominazione, 1, d_pointer - 1 ));
            d_body_nome    := RTRIM(SUBSTR(d_body_denominazione, d_pointer + 2 ));
         END IF;
      END IF;
   END SET_NOME;
   PROCEDURE SET_COGNOME
/******************************************************************************
 NOME:        SET_COGNOME.
 DESCRIZIONE: Modifica l'attributo COGNOME del soggetto corrente.
 ARGOMENTI:   p_cognome: cognome.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    20/09/2006 MM     Prima emissione.
 7  17/01/2013 SNeg Tolti spazi in fondo se nome vuoto
******************************************************************************/
   ( p_cognome IN VARCHAR2)
   IS
      d_return  VARCHAR2(2000);
      d_pointer NUMBER;
   BEGIN
     d_body_cognome := p_cognome;
     if d_body_nome is not null then
        d_body_denominazione := SUBSTR(d_body_cognome||'  '||d_body_nome,1,240);
     elsif d_body_nome is null then
        d_body_denominazione := SUBSTR(d_body_cognome,1,240);
     end if;
   END SET_COGNOME;
   PROCEDURE SET_DENOMINAZIONE
/******************************************************************************
 NOME:        SET_DENOMINAZIONE.
 DESCRIZIONE: Modifica l'attributo NOME o denominazione del soggetto corrente.
 ARGOMENTI:   p_denominazione: denominazione.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    20/09/2006 MM     Prima emissione.
******************************************************************************/
   ( p_denominazione IN VARCHAR2)
   IS
      d_return  VARCHAR2(2000);
      d_pointer NUMBER;
   BEGIN
      d_body_denominazione := p_denominazione;
      d_pointer := INSTR(d_body_denominazione,'  ');
      IF d_pointer = 0 THEN
         d_body_cognome := RTRIM(d_body_denominazione);
         d_body_nome    := NULL;
      ELSE
         d_body_cognome := RTRIM(SUBSTR(d_body_denominazione, 1, d_pointer - 1 ));
         d_body_nome    := RTRIM(SUBSTR(d_body_denominazione, d_pointer + 2 ));
      END IF;
   END SET_DENOMINAZIONE;
   PROCEDURE SET_SESSO
/******************************************************************************
 NOME:        SET_SESSO.
 DESCRIZIONE: Modifica l'attributo SESSO del soggetto corrente.
 ARGOMENTI:   p_sesso: sesso (M, F, null).
 ECCEZIONI:   20998, Valore non ammesso (Valori possibili: F, M o null).
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_sesso IN VARCHAR2 )
   IS
   BEGIN
     IF p_sesso IS NOT NULL AND p_sesso <> 'F' AND p_sesso <> 'M' THEN
        RAISE_APPLICATION_ERROR(-20998,'Valore non ammesso (Valori possibili: F, M o null).');
     ELSE
        d_body_sesso := p_sesso;
     END IF;
   END SET_SESSO;
   PROCEDURE SET_CODICE_FISCALE
/******************************************************************************
 NOME:        SET_CODICE_FISCALE.
 DESCRIZIONE: Modifica l'attributo CODICE FISCALE del soggetto corrente.
 ARGOMENTI:   p_codice_fiscale: codice fiscale.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_codice_fiscale IN VARCHAR2 )
   IS
   BEGIN
      d_body_codice_fiscale := UPPER(p_codice_fiscale);
   END SET_CODICE_FISCALE;
   PROCEDURE SET_INDIRIZZO
/******************************************************************************
 NOME:        SET_INDIRIZZO.
 DESCRIZIONE: Modifica l'attributo INDIRIZZO del soggetto corrente.
 ARGOMENTI:   p_indirizzo: indirizzo.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_indirizzo IN VARCHAR2 )
   IS
   BEGIN
      d_body_indirizzo := p_indirizzo;
   END SET_INDIRIZZO;
   PROCEDURE SET_CAP
/******************************************************************************
 NOME:        SET_CAP.
 DESCRIZIONE: Modifica l'attributo CAP del soggetto corrente.
 ARGOMENTI:   p_cap: cap.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_cap IN VARCHAR2 )
   IS
   BEGIN
      d_body_cap := p_cap;
   END SET_CAP;
   PROCEDURE SET_COMUNE
/******************************************************************************
 NOME:        SET_COMUNE.
 DESCRIZIONE: Modifica l'attributo COMUNE del soggetto corrente.
 ARGOMENTI:   p_comune: codice comune di residenza.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_comune IN NUMBER )
   IS
   BEGIN
      d_body_comune := p_comune;
   END SET_COMUNE;
   PROCEDURE SET_PROVINCIA
/******************************************************************************
 NOME:        SET_PROVINCIA.
 DESCRIZIONE: Modifica l'attributo PROVINCIA del soggetto corrente.
 ARGOMENTI:   p_provincia: codice provincia di residenza.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_provincia IN NUMBER )
   IS
   BEGIN
      d_body_provincia := p_provincia;
   END SET_PROVINCIA;
   PROCEDURE SET_DATA_NASCITA
/******************************************************************************
 NOME:        SET_DATA_NASCITA.
 DESCRIZIONE: Modifica l'attributo DATA DI NASCITA del soggetto corrente.
 ARGOMENTI:   p_data_nascita: stringa contenente la data di nascita in formato
              dd/mm/yyyy.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_data_nascita IN VARCHAR2 )
   IS
   BEGIN
      d_body_data_nascita := p_data_nascita;
   END SET_DATA_NASCITA;
   PROCEDURE SET_COMUNE_NAS
/******************************************************************************
 NOME:        SET_COMUNE_NAS.
 DESCRIZIONE: Modifica l'attributo COMUNE DI NASCITA del soggetto corrente.
 ARGOMENTI:   p_comune: codice comune di nascita.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_comune IN NUMBER )
   IS
   BEGIN
      d_body_comune_nas := p_comune;
   END SET_COMUNE_NAS;
   PROCEDURE SET_PROVINCIA_NAS
/******************************************************************************
 NOME:        SET_PROVINCIA_NAS.
 DESCRIZIONE: Modifica l'attributo PROVINCIA DI NASCITA del soggetto corrente.
 ARGOMENTI:   p_provincia: codice provincia di nascita.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_provincia IN NUMBER )
   IS
   BEGIN
      d_body_provincia_nas := p_provincia;
   END SET_PROVINCIA_NAS;
   PROCEDURE SET_TELEFONO
/******************************************************************************
 NOME:        SET_TELEFONO.
 DESCRIZIONE: Modifica l'attributo TELEFONO del soggetto corrente.
 ARGOMENTI:   p_telefono: numero di telefono.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_telefono IN VARCHAR2 )
   IS
   BEGIN
      d_body_telefono := p_telefono;
   END SET_TELEFONO;
   PROCEDURE SET_FAX
/******************************************************************************
 NOME:        SET_FAX.
 DESCRIZIONE: Modifica l'attributo FAX del soggetto corrente.
 ARGOMENTI:   p_fax: numero di fax.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_fax IN VARCHAR2 )
   IS
   BEGIN
      d_body_fax := p_fax;
   END SET_FAX;
   PROCEDURE SET_INDIRIZZO_WEB
/******************************************************************************
 NOME:        SET_INDIRIZZO_WEB.
 DESCRIZIONE: Modifica l'attributo INDIRIZZO WEB del soggetto corrente.
 ARGOMENTI:   p_indirizzo_web: indirizzo web.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_indirizzo_web IN VARCHAR2 )
   IS
   BEGIN
      d_body_indirizzo_web := p_indirizzo_web;
   END SET_INDIRIZZO_WEB;
   PROCEDURE SET_NOTE
/******************************************************************************
 NOME:        SET_NOTE.
 DESCRIZIONE: Modifica l'attributo NOTE del soggetto corrente.
 ARGOMENTI:   p_note: note.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_note IN VARCHAR2 )
   IS
   BEGIN
      d_body_note := p_note;
   END SET_NOTE;
   PROCEDURE SET_COMPETENZA
/******************************************************************************
 NOME:        SET_COMPETENZA.
 DESCRIZIONE: Modifica l'attributo COMPETENZA del soggetto corrente.
 ARGOMENTI:   p_competenza: progetto di competenza.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_competenza IN VARCHAR2 )
   IS
   BEGIN
      d_body_competenza := p_competenza;
   END SET_COMPETENZA;


   PROCEDURE SET_COMPETENZA_ESCLUSIVA
/******************************************************************************
 NOME:        SET_COMPETENZA_ESCLUSIVA.
 DESCRIZIONE: Modifica l'attributo COMPETENZA_ESCLUSIVA del soggetto corrente.
 ARGOMENTI:   p_competenza_esclusiva: valore di competenza esclusiva.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/09/2019   SN     Prima emissione.
******************************************************************************/
   ( p_competenza_esclusiva IN VARCHAR2 )
   IS
   BEGIN
      d_body_competenza_esclusiva := p_competenza_esclusiva;
   END SET_COMPETENZA_ESCLUSIVA;


   PROCEDURE SET_UTENTE_AGGIORNAMENTO
/******************************************************************************
 NOME:        SET_UTENTE_AGGIORNAMENTO.
 DESCRIZIONE: Modifica l'attributo UTENTE DI aggionamento del soggetto corrente.
 ARGOMENTI:   p_utente_aggiornamento: stringa contenente utente di aggiornamento
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/09/2019   SN     Prima emissione.
******************************************************************************/
   ( p_utente_aggiornamento IN VARCHAR2 )
   IS
   BEGIN
      d_body_utente_agg := p_utente_aggiornamento;
   END SET_UTENTE_AGGIORNAMENTO;

   PROCEDURE UPDATE_SOGGETTO
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
 ECCEZIONI:   20999, Impossibile associare al Soggetto un nome vuoto.>
 ANNOTAZIONI: Lancia la procedure SOGGETTI_PM.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   ( p_soggetto IN OUT NUMBER
   , p_modifica IN     VARCHAR2 DEFAULT 'T')
   IS
   BEGIN
--      IF d_body_nome IS NULL THEN
--         RAISE_APPLICATION_ERROR(-20999,'Impossibile associare al Soggetto un nome vuoto');
--      ELSE
        BEGIN
           Soggetti_Pm ( p_soggetto, d_body_denominazione, d_body_sesso
                       , d_body_data_nascita, d_body_provincia_nas, d_body_comune_nas
                       , d_body_codice_fiscale, d_body_indirizzo, d_body_cap
                       , d_body_comune, d_body_provincia
                       , d_body_telefono, d_body_fax, d_body_indirizzo_web, d_body_note
                       , p_modifica, 'N', d_body_competenza
                       , d_body_competenza_esclusiva, d_body_utente_agg)
           ;
        EXCEPTION WHEN OTHERS THEN
           d_body_soggetto := TO_NUMBER(NULL);
           RAISE;
        END;
        d_body_soggetto := p_soggetto;
--     END IF;
   END UPDATE_SOGGETTO;
    function is_soggetto_componente(P_ni number) return afc_error.t_error_number
    IS
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
    BEGIN
    return so4_util.is_soggetto_componente(p_ni);
    END;
    FUNCTION scegli_fra_soggetti
   (p_codice_fiscale in soggetti.codice_fiscale%TYPE
   ,p_competenza in soggetti.competenza%TYPE default '%'
   ) RETURN number
   IS
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
 009  01/07/2019 SNegroni Scegliere soggetto in struttura con il codice fiscale dato Bug #36242
******************************************************************************/
   v_ni_soggetto soggetti.soggetto%TYPE;
   v_codice_fiscale soggetti.codice_fiscale%TYPE := p_codice_fiscale;
   BEGIN
   --Rev 010 Inizio Modifica
--   IF  not(   length(p_codice_fiscale) = 16
--      and ascii(upper(substr(ltrim(rtrim(p_codice_fiscale)),1,1))) between 65 and 90  -- inizia con lettera
--      and ascii(upper(substr(ltrim(rtrim(p_codice_fiscale)),-1,1))) between 65 and 90 -- finisce con lettera
--      )-- se non è un codice fiscale di un soggetto
--   then
--      v_codice_fiscale := null;
--   end if;
   --Rev 010 Fine Modifica
   -- se con quel codice fiscale è in struttura uso quel soggetto
   select max(soggetto)
      into v_ni_soggetto
      from soggetti anso
     where anso.codice_fiscale = v_codice_fiscale
       and ad4_soggetto.is_soggetto_componente(soggetto) = 1
       and anso.competenza  like p_competenza;
   if v_ni_soggetto is null then
   select max(anso.soggetto)
      into v_ni_soggetto
     from ad4_utenti_soggetti utso
           , soggetti anso
           , ad4_utenti uten
   where anso.codice_fiscale = v_codice_fiscale
       --soggetti solo con al nullo and sysdate between anso.dal and nvl(anso.al,trunc(sysdate) +1)
       and uten.utente = utso.utente
       and anso.soggetto = utso.soggetto
       and exists (select 'x'
                          from ad4_diritti_accesso
                         where utente = uten.utente)
       and anso.competenza  like p_competenza
         ;
     if v_ni_soggetto is null then
         select max(anso.soggetto)
          into v_ni_soggetto
         from ad4_utenti_soggetti utso
               , soggetti anso
               , ad4_utenti uten
       where anso.codice_fiscale = v_codice_fiscale
           --and sysdate between anso.dal and nvl(anso.al,trunc(sysdate) +1)
           and uten.utente = utso.utente
           and anso.soggetto = utso.soggetto
           and exists (select 'x'
                              from ad4_diritti_accesso
                             where utente = uten.utente)
             ;
          if v_ni_soggetto is null then
             select max(anso.soggetto)
              into v_ni_soggetto
             from ad4_utenti_soggetti utso
                   , soggetti anso
                   , ad4_utenti uten
           where anso.codice_fiscale = v_codice_fiscale
              -- and sysdate between anso.dal and nvl(anso.al,trunc(sysdate) +1)
               and uten.utente = utso.utente
               and anso.soggetto = utso.soggetto
                 ;
                 if v_ni_soggetto is null then
                     select max(anso.soggetto)
                      into v_ni_soggetto
                     from soggetti anso
                   where anso.codice_fiscale = v_codice_fiscale
                    -- and sysdate between anso.dal and nvl(anso.al,trunc(sysdate) +1)
                         ;
                 end if;
         end if;
     end if;
    end if;
     return v_ni_soggetto;
   END scegli_fra_soggetti;
END Soggetto;
/

