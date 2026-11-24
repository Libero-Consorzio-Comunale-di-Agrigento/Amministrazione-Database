--liquibase formatted sql

--changeset mturra:201901301231_286 runOnChange:true stripComments:false endDelimiter:/

CREATE OR REPLACE PACKAGE Utente IS /* MASTER_LINK */
/******************************************************************************
 NOME:        UTENTE.
 DESCRIZIONE: Package per gestione UTENTI.
 ANNOTAZIONI: Salvato nella directory ins di AD4 nel file uten.pkg.
 ECCEZIONI:.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    13/01/2003 MM     Prima emissione.
 1    13/03/2003 MM     Gestione campo TIPO_NOTIFICA della tabella SERVIZI
                        in fase di spedizione notifiche (NOTIFICA_RICHIESTA).
 2    23/05/2003 MM     Gestione campi lingua, gruppo di lavoro ed importanza.
 3    06/02/2004 MM     - (BO8063) Introduzione del parametro p_error_msg nelle
                          funzioni INITIALIZE e GET_...
                        - (BO 7146) NOTIFICA_RICHIESTA: Modificato il controllo
                          per decidere se recapitare all'utente la seconda meta
                          della password in modo che controlli se:
                          1. esiste la chiave 'PWD=' nel campo NOTE della
                             tabella UTENTI;
                          2. non esiste nessun diritto di accesso abilitato a
                             cui l'utente abbia fatto accesso;
                          3. non esiste nessuna richiesta dello stesso utente
                             da prendere in carico.
 4    13/04/2005 MM     Introduzione della funzione CALCOLA_ACCESSI.
 5    06/10/2005 MM     Possibilita' di personalizzare messaggi di notifica
                        (NOTIFICA_RICHIESTA).
      24/11/2005 MM     Modificata NOTIFICA_RICHIESTA.
 6    05/01/2006 MM     Modificata SET_PASSWORD
 7    23/10/2006 MM     Gestione registrazione "lite".
 8    26/04/2007 MM     A20741.0.0: modificata rigenera_so.
                        Inserimento funzioni exists_id, exists_codice,
                        exists_utente, exists_nominativo e exists_username.
 09   13/07/2007 MM     Adeguamento versione a nuovi standard.
                        Creazione IS_PASSWORD_VALIDA.
 10   16/02/2009 MM     Creazione SET_UTENTE.
 11   29/12/2009 SNeg   Aggiunta sistemazione tabella utenti_aoo_ruolo_gruppo
 12   10/09/2010 SNeg   Modificati registra_web e aggiorna_web per valorizzare
                        di default la competenza. Tolta slave_copy per get_note
                        per problemi in abilitazione servizi
 13   08/03/2011 SNeg  funzioni per profilo utente e determinazione nominativo.
 14  27/04/2011 SNeg   Tolta get_utenti_aoo_ruolo_gruppo
 15  28/03/2017 SNeg   Esposta: verifica_utente x depag
 16  21/09/2018 SNeg  Introdotti campi AMMINISTRATORE e INFORMAZIONI_IDENTIFICAZIONE
 17  21/01/2019 SNeg   Introdotta procedure Send_msg_modifica_utente
 18   19/06/2019 SNeg  Aggiunte variabili per individuare se registrazione web
 19  27/05/2020 SNeg   Funzione per sapere se si stanno sistemando i gruppi in modo automatico
******************************************************************************/
   -- variabili per indicare registrazione da web
   s_registrazione_da_web_on NUMBER := 0;
   s_utente_registrazione_da_web VARCHAR2(8);
   -- Revisione del Package
   s_revisione CONSTANT Afc.t_revision := 'V1.18';
/******************************************************************************
 NOME:        VERSIONE.
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
******************************************************************************/
    FUNCTION  VERSIONE /* SLAVE_COPY */
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        INIT.
 DESCRIZIONE: Svuota tutti gli attributi dell'utente (variabili del package body)
              e dell'eventuale soggetto associato.
******************************************************************************/
    PROCEDURE INIT;
/******************************************************************************
 NOME:        INITIALIZE.
 DESCRIZIONE: Dato l'utente inizializza tutti i suoi attributi e quelli della
              eventuale registrazione anagrafica associata.
 ARGOMENTI:   p_utente    Codice dell'utente che si vuole inizializzare.
              p_error_msg 1 se si vuole che sia segnalato un errore in caso
                            l'utente passato non esista;
                          0 altrimenti.
 ECCEZIONI:   20999, Utente inesistente.
******************************************************************************/
   PROCEDURE INITIALIZE
   ( p_utente IN VARCHAR2
   , p_error_msg IN NUMBER DEFAULT 1);
/******************************************************************************
 NOME:        del
 DESCRIZIONE: Eliminazione di una riga con chiave indicata.
 PARAMETRI:   Chiave della table.
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 7    27/10/2006 MM     Prima emissione.
******************************************************************************/
   PROCEDURE del( p_utente           IN  UTENTI.Utente%TYPE);
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
   PROCEDURE ins
   ( p_nominativo           IN UTENTI.nominativo%TYPE
   , p_utente           IN OUT UTENTI.Utente%TYPE
   , p_id_utente        IN OUT UTENTI.id_utente%TYPE
   , p_password             IN UTENTI.PASSWORD%TYPE  DEFAULT NULL
   , p_data_password        IN VARCHAR2  DEFAULT NULL
   , p_rinnovo_password     IN UTENTI.rinnovo_password%TYPE  DEFAULT NULL
   , p_pwd_da_modificare    IN UTENTI.pwd_da_modificare%TYPE  DEFAULT NULL
   , p_stato                IN UTENTI.stato%TYPE  DEFAULT NULL
   , p_note                 IN UTENTI.note%TYPE  DEFAULT NULL
   , p_lingua               IN UTENTI.lingua%TYPE  DEFAULT NULL
   , p_gruppo_lavoro        IN UTENTI.gruppo_lavoro%TYPE  DEFAULT NULL
   , p_importanza           IN UTENTI.importanza%TYPE  DEFAULT NULL
   , p_utente_aggiornamento IN UTENTI.Utente%TYPE  DEFAULT NULL
   , p_soggetto             IN UTENTI_SOGGETTI.Soggetto%TYPE DEFAULT NULL
   , p_ultimo_tentativo     IN VARCHAR2 DEFAULT NULL
   , p_numero_tentativi     IN UTENTI.numero_tentativi%type DEFAULT NULL
   , p_data_inserimento     IN VARCHAR2 DEFAULT to_char(sysdate, 'dd/mm/yyyy hh24:mi:ss')
   , p_data_aggiornamento   IN VARCHAR2 DEFAULT NULL
   , p_amministratore    in utenti.amministratore%TYPE default null
   , p_INFO_IDENTIFICAZIONE    in utenti.INFO_IDENTIFICAZIONE%TYPE default null
   , p_is_psw_crypted       IN NUMBER DEFAULT 0
   );
-- LETTURA ATTRIBUTI.
/******************************************************************************
 NOME:        GET_TIPO_UTENTE.
 DESCRIZIONE: Dato l'utente ritorna il TIPO.
 ARGOMENTI:   p_utente:     codice dell'utente.
******************************************************************************/
   FUNCTION GET_TIPO_UTENTE /* SLAVE_COPY */
   ( p_utente_o_gruppo IN VARCHAR2)
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_ID_UTENTE.
 DESCRIZIONE: Dato l'utente ritorna il suo id.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
    FUNCTION GET_ID_UTENTE /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN NUMBER;
/******************************************************************************
 NOME:        GET_NOMINATIVO.
 DESCRIZIONE: Dato l'utente ritorna il suo nominativo.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
    FUNCTION GET_NOMINATIVO /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_PASSWORD.
 DESCRIZIONE: Dato l'utente ritorna la sua password (criptata).
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
    FUNCTION GET_PASSWORD  /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_DATA_PASSWORD.
 DESCRIZIONE: Dato l'utente ritorna la data di ultima modifica della sua
              password come stringa in formato dd/mm/yyyy.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
    FUNCTION GET_DATA_PASSWORD /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_RINNOVO_PASSWORD.
 DESCRIZIONE: Dato l'utente, restituisce:
                 SI se l'utente puo' rinnovare la password,
             NO altrimenti.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
    FUNCTION GET_RINNOVO_PASSWORD /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_ULTIMO_TENTATIVO.
 DESCRIZIONE: Dato l'utente ritorna la data del suo ultimo tentativo di accesso
              come stringa in formato dd/mm/yyyy.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
    FUNCTION GET_ULTIMO_TENTATIVO /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_NUMERO_TENTATIVI.
 DESCRIZIONE: Dato l'utente ritorna il numero dei suoi tentativi di accesso
              falliti.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
    FUNCTION GET_NUMERO_TENTATIVI /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_STATO.
 DESCRIZIONE: Dato l'utente ritorna lo stato ad esso associato.
              Valori possibili:
                 'U': Attivo,
                 'S': Sospeso,
                 'R'; Revocato,
                 'A': Automatico.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
    FUNCTION GET_STATO /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_LINGUA.
 DESCRIZIONE: Dato l'utente ritorna la lingua ad esso associata.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
   FUNCTION GET_LINGUA /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_GRUPPO_LAVORO.
 DESCRIZIONE: Dato l'utente ritorna Il gruppo di lavoro ad esso associato.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
   FUNCTION GET_GRUPPO_LAVORO /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_IMPORTANZA.
 DESCRIZIONE: Dato l'utente ritorna l'importanza ad esso associata.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
   FUNCTION GET_IMPORTANZA /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
   RETURN NUMBER;
/******************************************************************************
 NOME:        GET_NOTE.
 DESCRIZIONE: Dato l'utente ritorna le note ad esso associate.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
  REVISIONI:
  Rev. Data       Autore Descrizione
  ---- ---------- ------ ------------------------------------------------------
  12   10/09/2010 SNeg   Tolta slave_copy per get_note per problemi in
                         abilitazione servizi (leggeva su slave info non
                         ancora replicate)
******************************************************************************/
    FUNCTION GET_NOTE
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN VARCHAR2;

    /******************************************************************************
 NOME:        GET_AMMINISTRATORE
 DESCRIZIONE: Dato l'utente ritorna il campo AMMINISTRATORE.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
  REVISIONI:
  Rev. Data       Autore Descrizione
  ---- ---------- ------ ------------------------------------------------------
  16   21/09/2018 SNeg   Introduzione campo
******************************************************************************/

    FUNCTION GET_AMMINISTRATORE
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN utenti.AMMINISTRATORE%TYPE;

    /******************************************************************************
 NOME:        GET_INFO_IDENTIFICAZIONE
 DESCRIZIONE: Dato l'utente ritorna il campo INFO_IDENTIFICAZIONE.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
  REVISIONI:
  Rev. Data       Autore Descrizione
  ---- ---------- ------ ------------------------------------------------------
  16   21/09/2018 SNeg   Introduzione campo
******************************************************************************/


    FUNCTION GET_INFO_IDENTIFICAZIONE
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN utenti.INFO_IDENTIFICAZIONE%TYPE;


/******************************************************************************
 NOME:        GET_SOGGETTO.
 DESCRIZIONE: Dato l'utente ritorna il numero individuale dell'eventuale
              registrazione anagrafica associata.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
******************************************************************************/
    FUNCTION GET_SOGGETTO /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN NUMBER;
/******************************************************************************
 NOME:        GET_UTENTE.
 DESCRIZIONE: Dato un soggetto, ritorna il codice utente associato.
              Se al soggetto non e' associato nessun utente ritorna null; se al
              soggetto sono associati piu' utenti ritorna il minore.
 ARGOMENTI:   p_soggetto:   identificativo del soggetto.
******************************************************************************/
   FUNCTION GET_UTENTE /* SLAVE_COPY */
   ( p_soggetto IN NUMBER)
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_UTENTE.
 DESCRIZIONE: Dato un nominativo, ritorna il codice utente associato.
 ARGOMENTI:   p_nominativo:   nominativo utente.
******************************************************************************/
   FUNCTION GET_UTENTE /* SLAVE_COPY */
   ( p_nominativo IN VARCHAR2)
   RETURN VARCHAR2;
-- SCRITTURA ATTRIBUTI.
/******************************************************************************
 NOME:        SET_UTENTE.
 DESCRIZIONE: Modifica l'attributo UTENTE dell'utente corrente.
 ARGOMENTI:   p_utente: codice utente.
******************************************************************************/
   PROCEDURE SET_UTENTE
   ( p_utente IN varchar2 );
/******************************************************************************
 NOME:        SET_ID_UTENTE.
 DESCRIZIONE: Modifica l'attributo ID_UTENTE dell'utente corrente.
 ARGOMENTI:   p_id_utente: identificativo utente.
******************************************************************************/
    PROCEDURE SET_ID_UTENTE        ( p_id_utente IN NUMBER );
/******************************************************************************
 NOME:        SET_NOMINATIVO.
 DESCRIZIONE: Modifica l'attributo NOMINATIVO dell'utente corrente.
 ARGOMENTI:   p_nominativo: nominativo.
******************************************************************************/
    PROCEDURE SET_NOMINATIVO       ( p_nominativo IN VARCHAR2 );
/******************************************************************************
 NOME:        CHECK_PASSWORD.
 DESCRIZIONE: Esegue i controlli di validita' sulla nuova password da associare
              all'utente passato.
 ARGOMENTI:   p_password:     nuova password NON CRIPTATA.
              p_old_password: password attuale NON CRIPTATA.
              p_utente:       codice utente da modificare.
 ECCEZIONI:   -20999 'Password attuale ERRATA !
                     'Nuova Password uguale a Password attuale !'
                     'La password deve essere di almeno x caratteri.'
                     'La password non puo' contenere caratteri speciali.'
                     'La password deve contenere almeno un numero.'
******************************************************************************/
   PROCEDURE CHECK_PASSWORD /* SLAVE_COPY */
   ( p_utente       IN VARCHAR2
   , p_password     IN VARCHAR2
   , p_old_password IN VARCHAR2);
/******************************************************************************
 NOME:        SET_PASSWORD.
 DESCRIZIONE: Modifica l'attributo PASSWORD dell'utente corrente.
 ARGOMENTI:   p_password: password NON CRIPTATA.
******************************************************************************/
    PROCEDURE SET_PASSWORD         ( p_password     IN VARCHAR2,
                                     p_old_password IN VARCHAR2 DEFAULT 'da non verificare');
   PROCEDURE SET_PASSWORD_CRYPTED
/******************************************************************************
 NOME:        SET_PASSWORD_CRYPTED
 DESCRIZIONE: Modifica l'attributo PASSWORD dell'utente corrente.
 ARGOMENTI:   p_password: STRINGA contenente la password CRIPTATA.
******************************************************************************/
   ( p_password IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_DATA_PASSWORD.
 DESCRIZIONE: Modifica l'attributo DATA_PASSWORD dell'utente corrente.
 ARGOMENTI:   p_data_password: STRINGA contenente la data della password in
                               formato dd/mm/yyyy.
******************************************************************************/
    PROCEDURE SET_DATA_PASSWORD    ( p_data_password IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_RINNOVO_PASSWORD.
 DESCRIZIONE: Modifica l'attributo RINNOVO_PASSWORD dell'utente corrente.
 ARGOMENTI:   p_rinnovo_password: possibilita' di rinnovo della password.
                                  Valori possibili: SI/NO.
 ECCEZIONI:   20998, Valore 'Rinnovo Password''non valido.
******************************************************************************/
    PROCEDURE SET_RINNOVO_PASSWORD ( p_rinnovo_password IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_ULTIMO_TENTATIVO.
 DESCRIZIONE: Modifica l'attributo ULTIMO_TENTATIVO dell'utente corrente.
 ARGOMENTI:   p_ultimo_tentativo: STRINGA contenente la data dell'ultimo
                                  tentativo di accesso in formato dd/mm/yyyy.
******************************************************************************/
    PROCEDURE SET_ULTIMO_TENTATIVO ( p_ultimo_tentativo IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_NUMERO_TENTATIVI.
 DESCRIZIONE: Modifica l'attributo NUMERO_TENTATIVI dell'utente corrente.
 ARGOMENTI:   p_numero_tentativi: numero di tentativi di accesso falliti.
******************************************************************************/
    PROCEDURE SET_NUMERO_TENTATIVI ( p_numero_tentativi IN NUMBER );
/******************************************************************************
 NOME:        SET_STATO.
 DESCRIZIONE: Modifica l'attributo STATO dell'utente corrente.
 ARGOMENTI:   p_stato: Stato dell'utente.
                       Valori possibili:
                       'U': Attivo,'S': Sospeso,'R'; Revocato,'A': Automatico.'
 ECCEZIONI:   20998,'Valore 'Stato' non valido.
******************************************************************************/
    PROCEDURE SET_STATO            ( p_stato IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_LINGUA.
 DESCRIZIONE: Modifica l'attributo LINGUA dell'utente corrente.
 ARGOMENTI:   p_lingua: lingua.
 ECCEZIONI:   20998, Lingua non codificata.
******************************************************************************/
    PROCEDURE SET_LINGUA           ( p_lingua IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_GRUPPO_LAVORO.
 DESCRIZIONE: Modifica l'attributo GRUPPO_LAVORO dell'utente corrente.
 ARGOMENTI:   p_gruppo_lavoro: gruppo di lavoro.
 ECCEZIONI:   20998, Gruppo di lavoro non codificato.
******************************************************************************/
    PROCEDURE SET_GRUPPO_LAVORO    ( p_gruppo_lavoro IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_IMPORTANZA.
 DESCRIZIONE: Modifica l'attributo IMPORTANZA dell'utente corrente.
 ARGOMENTI:   p_importanza: importanza.
 ECCEZIONI:   20998, L'importanza deve essere minore di 100.
******************************************************************************/
    PROCEDURE SET_IMPORTANZA       ( p_importanza IN NUMBER );
/******************************************************************************
 NOME:        SET_NOTE.
 DESCRIZIONE: Modifica l'attributo NOTE dell'utente corrente.
 ARGOMENTI:   p_note: note.
******************************************************************************/
    PROCEDURE SET_NOTE             ( p_note IN VARCHAR2 );

/******************************************************************************
 NOME:        SET_AMMINISTRATORE.
 DESCRIZIONE: Modifica l'attributo AMMINISTRATORE dell'utente corrente.
 ARGOMENTI:   p_note: note.
******************************************************************************/
    PROCEDURE SET_AMMINISTRATORE             ( p_AMMINISTRATORE IN VARCHAR2 );

/******************************************************************************
 NOME:        SET_INFO_IDENTIFICAZIONE
 DESCRIZIONE: Modifica l'attributo INFO_IDENTIFICAZIONE dell'utente corrente.
 ARGOMENTI:   p_note: note.
******************************************************************************/
    PROCEDURE SET_INFO_IDENTIFICAZIONE             ( p_INFO_IDENTIFICAZIONE IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_DATA_INSERIMENTO
 DESCRIZIONE: Predispone la data di inserimento dell'utente.
 ARGOMENTI:   p_data_inserimento: data di inserimento utente come stringa in
                                  formato dd/mm/yyyy.
******************************************************************************/
   PROCEDURE SET_DATA_INSERIMENTO
   ( p_data_inserimento IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_UTENTE_AGG.
 DESCRIZIONE: Predispone l'utente di aggiornamento.
 ARGOMENTI:   p_utente_agg: codice dell'utente che effettua l'aggiornamento.
******************************************************************************/
   PROCEDURE SET_UTENTE_AGG       ( p_utente_agg IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_DATA_AGGIORNAMENTO
 DESCRIZIONE: Predispone la data di aggiornamento dell'utente.
 ARGOMENTI:   p_data_aggiornamento: data di aggiornamento utente come stringa
                                    in formato dd/mm/yyyy.
******************************************************************************/
   PROCEDURE SET_DATA_AGGIORNAMENTO
   ( p_data_aggiornamento IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_PWD_DA_MODIFICARE.
 DESCRIZIONE: Modifica l'attributo PWD_DA_MODIFICARE dell'utente corrente.
 ARGOMENTI:   p_pwd_da_modificare: PWD_DA_MODIFICARE.
******************************************************************************/
   PROCEDURE SET_PWD_DA_MODIFICARE   ( p_pwd_da_modificare IN VARCHAR2 );
/******************************************************************************
 NOME:        SET_SOGGETTO.
 DESCRIZIONE: Associa all'utente una registrazione anagrafica.
 ARGOMENTI:   p_soggetto: numero individuale.
******************************************************************************/
    PROCEDURE SET_SOGGETTO         ( p_soggetto IN NUMBER );
/******************************************************************************
 NOME:        CALCOLA_UTENTE.
 DESCRIZIONE: Procedura da utilizzare in fase di INSERIMENTO di un nuovo utente.
              Calcola l'id e/o il codice utente.
           Passi Eseguiti:
                 Se viene passato l'utente e non l'id,
                    calcola il primo id libero e lo restituisce in p_id_utente;
                 se non viene passato ne' l'utente ne' l'id,
                    calcola il primo id libero per cui non esiste un utente che
                    abbia come codice l'id calcolato, lo restituisce in
                    p_id_utente e riempie p_utente con p_id_utente;
                 se viene passato l'id e non l'utente,
                    riempie p_utente con p_id_utente;
                 se vengono passati entrambi,
                    non fa nulla.'
 ARGOMENTI:   p_utente:    codice utente.
              p_id_utente: identificativo utente.
 ECCEZIONI:   -
 ANNOTAZIONI: Utilizzata da UTENTI_TIU.
******************************************************************************/
    PROCEDURE CALCOLA_UTENTE  ( p_utente    IN OUT VARCHAR2
                              , p_id_utente IN OUT NUMBER);
/******************************************************************************
 NOME:        UPDATE_UTENTE.
 DESCRIZIONE: Effettua insert o update di utenti, inserisce eventuale anagrafica
              associata e crea legame tra utente ed anagrafica.
 ARGOMENTI:   -
 ECCEZIONI:   -
 ANNOTAZIONI: Lancia la procedure omonima con p_modifica_sogg = 'T'.
******************************************************************************/
    PROCEDURE UPDATE_UTENTE;
/******************************************************************************
 NOME:        UPDATE_UTENTE.
 DESCRIZIONE: Effettua insert o update di utenti, inserisce eventuale anagrafica
              associata e crea legame tra utente ed anagrafica.
 ARGOMENTI:   p_modifica_sogg: Totale (T), Parziale (P) o da non modificare (N).
                            Se totale:
                               tutti i valori passati vengono aggiornati
                               indipendentemente   dal fatto che siano nulli o
                               meno,
                            se Parziale:
                               aggiorna i soli dati significativi (not null),
                            se da non modificare (N):
                               non vengono apportate modifiche al soggetto.
 ECCEZIONI: -20999: Parametro ''p_modifica_sogg'' non valido.
                    Valori possibili: Totale (''T''), Parziale (''P'') o da
                    non modificare (''N'').
 ANNOTAZIONI: Lancia la procedure omonima ignorando il parametro di output.
******************************************************************************/
    PROCEDURE UPDATE_UTENTE ( p_modifica_sogg IN  VARCHAR2);
/******************************************************************************
 NOME:        UPDATE_UTENTE.
 DESCRIZIONE: Effettua insert o update di utenti, inserisce eventuale anagrafica
              associata e crea legame tra utente ed anagrafica.
              Restituisce in 'p_utente' il codice dell'utente inserito od
              aggiornato.
 ARGOMENTI:   p_utente:        codice dell'utente inserito.
              p_modifica_sogg: Totale (T), Parziale (P) o da non modificare (N).
                            Se totale:
                               tutti i valori passati vengono aggiornati
                               indipendentemente   dal fatto che siano nulli o
                               meno,
                            se Parziale:
                               aggiorna i soli dati significativi (not null),
                            se da non modificare (N):
                               non vengono apportate modifiche al soggetto.
 ECCEZIONI:   -20998: Impossibile associare ad un Utente un Nominativo vuoto.
              -20999: Utente 'd_body_nominativo' non inserito.
                      Utente 'd_body_nominativo' non aggiornato.
                      Parametro ''p_modifica_sogg'' non valido.
                      Valori possibili: Totale (''T''), Parziale (''P'') o da
                      non modificare (''N'').
 ANNOTAZIONI: Chiama CALCOLA_UTENTE e UPDATE_SOGGETTO.
******************************************************************************/
    PROCEDURE UPDATE_UTENTE ( p_utente        IN OUT VARCHAR2
                            , p_modifica_sogg IN  VARCHAR2);
    PROCEDURE UPDATE_UTENTE_COMMIT ( p_utente        IN OUT VARCHAR2
                            , p_modifica_sogg IN  VARCHAR2);
/******************************************************************************
 NOME:        REGISTRA_WEB.
 DESCRIZIONE: Registrazione di un utente da un portale.
            Passi Eseguiti:
              - Verifica presenza parametri obbligatori:
                 . p_nominativo;
                 . p_cognome;
               . p_nome;
                . p_codice_fiscale;
                . p_comune_nas;
     . p_provincia_nas;
                . p_data_nascita;
                . p_sesso;
           - Controllo codice fiscale;
           - Verifica che non esista un utente con stesso nominativo o, se
             esiste gia', che abbia gli stessi dati anagrafici e non abbia
             ancora nessun diritto di accesso.
              - Inserisce o aggiorna l'utente ed il soggetto (per i soli dati
             significativi - not null).
              - Riempie i parametri di output.
           L'utente registrato viene messo in stato SOSPESO.
           In seguito a esplicita conferma dei dati (AGGIORNA_WEB), l'utente
           viene messo 'In Uso'.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente           OUT: codice dell'utente registrato.
              p_mezza_password   OUT: prima meta' della password.
              p_nominativo:           nominativo.
              p_cognome:              cognome del soggetto.
              p_nome:                 nome del soggetto.
              p_codice_fiscale:       codice fiscale del soggetto.
              p_comune        IN OUT: comune di residenza del soggetto.
              p_provincia     IN OUT: provincia di residenza del soggetto.
              p_cap           IN OUT: cap di residenza del soggetto.
              p_indirizzo     IN OUT: indirizzo di residenza del soggetto.
              p_comune_nas:           codice comune di nascita del soggetto.
              p_provincia_nas:        codice provincia di nascita del soggetto.
              p_data_nascita:         data di nascita del soggetto come STRINGA
                                   in formato dd/mm/yyyy.
              p_sesso:                sesso del soggetto.
                                   Valori possibili: M (Maschio) / F (Femmina).
              p_indirizzo_web IN OUT: indirizzo e-mail del soggetto.
              p_telefono      IN OUT: numero di telefono del soggetto.
              p_fax           IN OUT: numero di fax del soggetto.
 ECCEZIONI:   -20999: Parametro obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
******************************************************************************/
    PROCEDURE REGISTRA_WEB ( p_utente IN OUT VARCHAR2
                           , p_mezza_password OUT VARCHAR2
                           , p_nominativo IN VARCHAR2
                           , p_cognome IN VARCHAR2
                           , p_nome IN VARCHAR2
                           , p_codice_fiscale IN VARCHAR2
                           , p_comune IN OUT NUMBER
                           , p_provincia IN OUT NUMBER
                           , p_cap IN OUT VARCHAR2
                           , p_indirizzo IN OUT VARCHAR2
                           , p_comune_nas IN NUMBER
                           , p_provincia_nas IN NUMBER
                           , p_data_nascita IN VARCHAR2
                           , p_sesso IN VARCHAR2
                           , p_indirizzo_web IN OUT VARCHAR2
                           , p_telefono IN OUT VARCHAR2
                           , p_fax IN OUT VARCHAR2
                           , p_competenza IN VARCHAR2 default null);
/******************************************************************************
 NOME:        AGGIORNA_WEB.
 DESCRIZIONE: Aggiornamento dei dati anagrafici di un utente esistente da un
              portale.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente:          codice dell'utente.
              p_indirizzo_web:   indirizzo e-mail del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
 ECCEZIONI:   -20999: Parametro 'p_utente' obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              Chiama la funzione omonima senza specificare il numero di telefono
              (p_telefono => 'NO').
******************************************************************************/
    PROCEDURE AGGIORNA_WEB ( p_utente IN VARCHAR2
                           , p_indirizzo_web IN VARCHAR2);
/******************************************************************************
 NOME:        AGGIORNA_WEB.
 DESCRIZIONE: Aggiornamento dei dati anagrafici di un utente esistente da un
              portale.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente:          codice dell'utente.
              p_indirizzo_web:   indirizzo e-mail del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_telefono:        numero di telefono del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
 ECCEZIONI:   -20999: Parametro 'p_utente' obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              Chiama la funzione omonima senza specificare il numero di fax
              (p_fax => 'NO').
******************************************************************************/
    PROCEDURE AGGIORNA_WEB ( p_utente IN VARCHAR2
                           , p_indirizzo_web IN VARCHAR2
                           , p_telefono IN VARCHAR2);
/******************************************************************************
 NOME:        AGGIORNA_WEB.
 DESCRIZIONE: Aggiornamento dei dati anagrafici di un utente esistente da un
              portale.
              Verifica presenza del parametro p_utente.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente:          codice dell'utente.
              p_indirizzo_web:   indirizzo e-mail del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_telefono:        numero di telefono del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_fax:             numero di fax del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
 ECCEZIONI:   -20999: Parametro 'p_utente' obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              Chiama la funzione omonima senza i dati di residenza (p_comune => 0,
              p_provincia => 0, p_cap => 'NO').
******************************************************************************/
    PROCEDURE AGGIORNA_WEB ( p_utente IN VARCHAR2
                           , p_indirizzo_web IN VARCHAR2
                           , p_telefono IN VARCHAR2
                           , p_fax IN VARCHAR2);
/******************************************************************************
 NOME:        AGGIORNA_WEB.
 DESCRIZIONE: Aggiornamento dei dati anagrafici di un utente esistente da un
              portale.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente:          codice dell'utente.
              p_comune:          comune di residenza del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 0.
              p_provincia:       provincia di residenza del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 0.
              p_cap:             cap di residenza del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_indirizzo:       indirizzo di residenza del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_indirizzo_web:   indirizzo e-mail del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_telefono:        numero di telefono del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_fax:             numero di fax del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
 ECCEZIONI:   -20999: Parametro 'p_utente' obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              Chiama la funzione omonima senza modificare lo stato dell'utente
              (p_utente_in_uso => 'N').
******************************************************************************/
    PROCEDURE AGGIORNA_WEB ( p_utente IN VARCHAR2
                           , p_comune IN  NUMBER
                           , p_provincia IN  NUMBER
                           , p_cap IN VARCHAR2
                           , p_indirizzo IN VARCHAR2
                           , p_indirizzo_web IN VARCHAR2
                           , p_telefono IN VARCHAR2
                           , p_fax IN VARCHAR2);
/******************************************************************************
 NOME:        AGGIORNA_WEB.
 DESCRIZIONE: Aggiornamento dei dati anagrafici di un utente esistente da un
              portale ed, eventualmente, mette l'utente 'In Uso'.
              Verifica presenza del parametro p_utente.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente:          codice dell'utente.
              p_comune:          comune di residenza del soggetto.
                                 Per evitare che l'attributo venga aggiornato
                                 passare 0.
              p_provincia:       provincia di residenza del soggetto.
                                 Per evitare che l'attributo venga aggiornato
                                 passare 0.
              p_cap:             cap di residenza del soggetto.
                                 Per evitare che l'attributo venga aggiornato
                                 passare 'NO'.
              p_indirizzo:       indirizzo di residenza del soggetto.
                                 Per evitare che l'attributo venga aggiornato
                                 passare 'NO'.
              p_indirizzo_web:   indirizzo e-mail del soggetto.
                                 Per evitare che l'attributo venga aggiornato
                                 passare 'NO'.
              p_telefono:        numero di telefono del soggetto.
                                 Per evitare che l'attributo venga aggiornato
                                 passare 'NO'.
              p_fax:             numero di fax del soggetto.
                                 Per evitare che l'attributo venga aggiornato
                                 passare 'NO'.
           p_utente_in_uso:     se Y mette l'utente 'In Uso',
                                altrimenti lo lascia nello stato in cui e'.
 ECCEZIONI:   -20999: Parametro 'p_utente' obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
******************************************************************************/
    PROCEDURE AGGIORNA_WEB ( p_utente IN VARCHAR2
                          , p_comune IN  NUMBER
                           , p_provincia IN  NUMBER
                           , p_cap IN VARCHAR2
                           , p_indirizzo IN VARCHAR2
                           , p_indirizzo_web IN VARCHAR2
                           , p_telefono IN VARCHAR2
                           , p_fax IN VARCHAR2
                           , p_utente_in_uso IN VARCHAR2
                           , p_competenza IN VARCHAR2 default null);
/******************************************************************************
 NOME:        RICHIESTA_ABILITAZIONE.
              Inserimento di una richiesta di abilitazione ad un servizio (Modulo
            - Istanza) da parte di un utente.
           La richiesta viene creata in stato 'Fallita' ('F').
           Sara' la procedure GESTISCI_RICHIESTA che andra' ad associare alla
           richiesta lo stato appropriato ('In carico', 'Abilitata', ...) a
           seconda degli attributi del servizio (Modulo - Istanza) per cui e'
           richiesta l'abilitazione ed effettuera' le operazioni conclusive
           in base ai PARAMETRI_RICHIESTA specifici di ogni singolo servizio.
           Restituisce il numero identificativo della richiesta creata nel
           parametro 'p_id_richiesta'.
           Passi Eseguiti:
              - Verifica presenza campi obbligatori:
                 . p_utente;
                 . p_modulo;
               . p_istanza;
           - Controllo esistenza utente;
              - Controllo esistenza modulo;
              - Controllo esistenza istanza;
              - Controllo compatibilita' tra modulo ed istanza;
              - Controllo esistenza diritto di accesso al modulo e all'istanza
             per cui e' richiesta l'abilitazione da parte dell'utente (NON
            deve esistere gia').
           - Crea record in RICHIESTE_ABILITAZIONE.
              - Inserisce l'utente nel gruppo degli utenti richiedenti
             un'abilitazione ('ric_abil').
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_id_richiesta OUT: identificativo della richiesta.
              p_utente:           codice utente richiedente l'abilitazione.
              p_modulo:           modulo per cui si richiede l'abilitazione.
              p_istanza:          istanza per cui si richiede l'abilitazione.
 ECCEZIONI:   -20999: Parametro obbligatorio.
              -20999: Utente (<p_utente>) non definito.
           -20999: Modulo (<p_modulo>) non definito.
           -20999: Istanza ('p_istanza') non definita.
           -20999: Istanza (<p_istanza>) e Modulo (<p_modulo>) incompatibili.
           -20999: Utente (<p_utente>) gia' abilitato al servizio
                   (Modulo: <p_modulo> - Istanza: <p_istanza>).
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
******************************************************************************/
    PROCEDURE RICHIESTA_ABILITAZIONE ( p_id_richiesta OUT NUMBER
                                     , p_utente IN VARCHAR2
                                     , p_modulo IN VARCHAR2
                                     , p_istanza IN VARCHAR2);
/******************************************************************************
 NOME:        GESTISCI_RICHIESTA.
 DESCRIZIONE: Permette di cambiare lo stato della richiesta, chiudendo, cosi',
              la parte automatica di gestione.
              Una richiesta pur essere portata in stato:
              - 'A': Abilitata;
              - 'R': Respinta;
              - 'C': Da Prendere in Carico.
              Passi Eseguiti:
              - Verifica presenza parametri obbligatori:
                 . p_id_richiesta;
                 . p_stato.
              - Verifica validita' del valore del parametro 'p_stato'.
              - Verifica che la richiesta esista e non sia gia' abilitata.
              - Se la richiesta deve essere 'Abilitata' (p_stato = 'A'),
                  - seleziona il Gruppo di Abilitazione (p_gruppo se passato, il
                    valore di GRUPPO_ABILITAZIONE della tabella SERVIZI altrimenti).
                 - Verifica esistenza dell'eventuale gruppo.
                 - Eventuale inserimento Utente nel Gruppo di Abilitazione con
                   conseguente inserimento dei diritti e delle caratteristiche di
                   accesso del gruppo.
                 - Verifica che il gruppo possieda Diritti di Accesso al Servizio.
                 - Associa il ruolo, se passato, all'utente per l'accesso al servizio
                   a cui viene abilitato.
              - Aggiorna lo stato della richiesta, associando il valore di 'p_stato'
                se tutto e' andato bene, il valore 'F' ('Fallita') altrimenti.
              - Manda eventuale notifica della richiesta.
              -----------------------------------------------------
                  ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              -----------------------------------------------------
 ARGOMENTI:   p_id_richiesta: identificativo della richiesta.
              p_stato:        stato da associare alla richiesta.
                              Valori possibili: 'A', 'R' oppure 'C'.
              p_gruppo:       gruppo di appartenenza dell'utente.
                              Significativo solo in caso di approvazione della
                              richiesta (p_stato = 'A').
                              Default: null.
              p_ruolo:        ruolo dell'utente nell'accesso al servizio.
                              Significativo solo in caso di approvazione della
                              richiesta (p_stato = 'A').
                              Default: null.
              p_note:         note da associare alla richiesta.
                              Default: null.
 ECCEZIONI:   -20999: Parametro ''p_id_richiesta'' obbligatorio.
              -20999: Una richiesta puo'' essere Abilitata o Respinta o Messa
                      in Carico (Stato = 'A', 'R' oppure 'C').
              -20999: Richiesta N.<p_id_richiesta> gia' abilitata.
              -20999: Richiesta N.<p_id_richiesta> inesistente.
              -20999: Errore in selezione Richiesta N.<p_id_richiesta>.
              -20999: Errore in selezione Gruppo di Abilitazione per la Richiesta
                      N.<p_id_richiesta>.
              -20999: Gruppo di Abilitazione (<d_gruppo>) inesistente. Richiesta
                      N.'||p_id_richiesta||'.
              -20999: Errore in fase di Inserimento Utente nel Gruppo di Abilitazione
                      per la Richiesta N.<p_id_richiesta>.
              -20999: Errore in accesso alla tabella 'DIRITTI_ACCESSO' per la Richiesta
                      N.<p_id_richiesta>.
              -20999: Il gruppo '<d_gruppo>' non possiede Diritti di Accesso al Servizio
                      (Modulo: '<d_modulo>' - Istanza: '<d_istanza>').
              -20999: Il Gruppo oppure il Ruolo deve essere specificato.
              -20999: Errore in fase di Inserimento Diritto di Accesso per la Richiesta
                      N.<p_id_richiesta>.
              -20999: Errore in fase di Aggiornamento Diritto di Accesso per la Richiesta
                      N.<p_id_richiesta>.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
******************************************************************************/
    PROCEDURE GESTISCI_RICHIESTA ( p_id_richiesta  IN NUMBER
                                 , p_stato         IN VARCHAR2
                                 , p_gruppo        IN VARCHAR2 DEFAULT NULL
                                 , p_ruolo         IN VARCHAR2 DEFAULT NULL
                                 , p_note_notifica IN VARCHAR2 DEFAULT NULL
                                 , p_note          IN VARCHAR2 DEFAULT NULL);
/******************************************************************************
 NOME:        NOTIFICA_RICHIESTA.
 DESCRIZIONE: Notifica la richiesta identificata da 'p_id_richiesta' se previsto
              il tipo di notifica.
              Attualmente gestisce SOLO MAIL.
              Se lo stato della richiesta e' 'Fallita' ('F'),
                 non fa nulla;
              Se lo stato della richiesta e' 'In Carico' ('C'),
                 la notifica e' prevista solo se esiste un'azienda che deve
                 confermare i dati dell'individuo che richiede di essere abilitato
                 al servizio in quanto referente dell'azienda stessa.
                 Quindi, la notifica e' viene spedita solo se esiste in
                 PARAMETRI_RICHIESTA il parametro RS_AZIENDA significativo;
              Se lo stato della richiesta e' 'Abilitata' ('A'),
                 viene mandata la notifica (all'utente richiedente ed all'eventuale
                 azienda di cui e' referente) di avvenuta abilitazione dell'utente
                 al servizio; se, inoltre, e' il primo servizio abilitato all'
                 utente (presenza della chiave 'PWD=' nel campo NOTE della tabella
                 UTENTI, nessun diritto di accesso abilitato a cui l'utente abbia
                 fatto accesso e nessuna richiesta da prendere in carico), viene
                 mandata anche la seconda meta' della password.
              Se lo stato della richiesta e' 'Respinta' ('R'),
                 viene mandata la notifica (all'utente richiedente ed all'eventuale
                 azienda di cui e' referente) che la richiesta di abilitazione dell'
                 utente al servizio e' stata respinta.
              Passi Eseguiti:
              - Verifica presenza campi obbligatori:
                 . p_id_richiesta.
              - Controllo esistenza di un tipo di notifica, se alla richiesta o al
                servizio non e' associato nessun tipo di notifica, non fa nulla.
              - Controllo stato della richiesta (deve essere <> 'F').
              - Verifica se in PARAMETRI_RICHIESTA esiste il parametro RS_AZIENDA
                per selezione nome del destinatario.
              - Seleziona, in base al servizio, l'indirizzo del mittente e
                l'eventuale indirizzo a cui mandare in CC la notifica.
              - Prepara il messaggio in base allo stato della richiesta.
              - Selezione eventuale Ente Mittente (campo SOGGETTO tabella ENTI).
              - Selezione Indirizzo Utente Destinatario.
              - Se la notifica avviene via MAIL:
              - Controllo esistenza indirizzo email mittente (campo
                MAIL_NOTIFICHE della tabella SERVIZI).
              - Se stato richiesta <> 'C':
                  Controllo esistenza indirizzo email utente destinatario.
              - Spedisce il messaggio.
              -  Aggiorna il campo NOTIFICATA di RICHIESTE_ABILITAZIONE a 'Spedita'
                 ('S') se tutto e' bene, a 'Fallita' ('F') altrimenti.
              -----------------------------------------------------
                 ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              -----------------------------------------------------
 ARGOMENTI:   p_id_richiesta: identificativo della richiesta.
              p_note:         eventuali note da aggiungere al messaggio.
 ECCEZIONI:   -20999: Parametro 'p_id_richiesta' obbligatorio.
              -20999: Richiesta N.'<p_id_richiesta> inesistente.
              -20999: Errore in selezione Richiesta N.<p_id_richiesta>.
              -20999: Notifica non prevista per richieste Fallite (Richiesta
                      N.<p_id_richiesta>).
              -20999: Errore in selezione Azienda destinataria Richiesta
                      N.<p_id_richiesta>: deve esistere un solo parametro
                     'AZIENDA'.
              -20999: Errore in selezione Azienda destinataria Richiesta
                      N.<p_id_richiesta>.
              -20999: Errore in selezione Indirizzo Mittente Richiesta
                      N.<p_id_richiesta>.
              -20999: Errore in selezione Ente Mittente Richiesta
                      N.<p_id_richiesta>.
              -20999: Errore in selezione Indirizzo Utente Destinatario Richiesta
                      N.<p_id_richiesta>.
              -20999. Impossibile inviare notifica Richiesta N.<p_id_richiesta>.
                      Dati anagrafici dell''utente non presenti.
              -20999: Impossibile notificare la Richiesta N.<p_id_richiesta>.
                      Indirizzo del Mittente inesistente.
              -20999: Errore in selezione Indirizzo Soggetto Destinatario Richiesta
                      N.<p_id_richiesta>.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              Il messaggio di notifica e':
              "Richiesta N.<p_id_richiesta>
              Richiesta di abilitazione al modulo <codice modulo> - <descrizione modulo>
              (Istanza: <codice istanza> - <descrizione istanza>).
                 Utente richiedente l'abilitazione: <nominativo utente>
                 Dati anagrafici:
                     <cognome>  <nome>
                     Nato a <comune di nascita> (<sigla provincia>) il <data di nascita>
                     Codice Fiscale: <codice fiscale>"
              A questo viene aggiunto:
             - se lo stato della richiesta e' 'Da prendere in carico' ('C'):
                  "Si richiede conferma per effettuare l'abilitazione dell'utente al servizio.
                  Si prega rispondere mandando una mail con oggetto 'Richiesta di abilitazione
                  N.<p_id_richiesta>' all'indirizzo: <mail_notifiche>.
                  mailto:<mail_notifiche>?subject=Richiesta_di_abilitazione_N.<p_id_richiesta>"
             - se lo stato della richiesta e' 'Respinta' ('R'):
                  "La richiesta e' stata RESPINTA."
             - se lo stato della richiesta e' 'Abilitata' ('A'):
                  "La richiesta e' stata ABILITATA.
                  La seconda meta' della password e':<seconda meta' della password>
                  L'utente puo' accedere al servizio."
             In coda viene, in ogni caso, aggiunto:
             "________________________________________________________________________
                 Messaggio automatico
                     Ente Mittente:
                     <descrizione ente>
                     <indirizzo ente> - <cap ente> <comune ente> (<sigla provincia>)
              ________________________________________________________________________"
******************************************************************************/
    PROCEDURE NOTIFICA_RICHIESTA ( p_id_richiesta IN NUMBER
                                 , p_note         IN VARCHAR2 DEFAULT NULL);
/******************************************************************************
 NOME:        CALCOLA_ACCESSI.
 DESCRIZIONE: Dato l'utente ritorna il numero totale degli accessi.
 ARGOMENTI:   p_utente:     codice dell'utente.
******************************************************************************/
   FUNCTION CALCOLA_ACCESSI /* SLAVE_COPY */
   ( p_utente IN VARCHAR2) RETURN NUMBER;
/******************************************************************************
 NOME:        AGGIORNA_PWD_DA_MODIFICARE.
 DESCRIZIONE: Dato l'utente, verifica se e' prevista la modifica della password
              al primo login successivo, se e' cosi' e l'utente non ha ancora
              fatto nessun accesso, setta il campo PWD_DA_MODIFICARE a SI.
 ARGOMENTI:   p_utente:     codice dell'utente.
******************************************************************************/
   PROCEDURE AGGIORNA_PWD_DA_MODIFICARE ( p_utente IN VARCHAR2);
/******************************************************************************
 NOME:        GENERA_PASSWORD.
 DESCRIZIONE: Dato l'utente genera una password casuale.
 DESCRIZIONE: Dato l'utente genera una password casuale e la associa all'utente.
 PARAMETRI:   p_utente:     codice dell'utente.
              p_lenght:     lunghezza della pwd da generare, se non passata
                            prende quella prevista per l'utente.
 RITORNA:     password generata in chiaro.
******************************************************************************/
   FUNCTION GENERA_PASSWORD
   ( p_utente IN VARCHAR2
   , p_lenght IN NUMBER DEFAULT NULL)
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_ATTRIBUTI_PASSWORD.
 DESCRIZIONE: Dato l'utente, calcola gli attributi che deve avere la password.
 PARAMETRI:   p_utente:     codice dell'utente.
 RITORNA:     stringa descrittiva degli attributi della password.
******************************************************************************/
   FUNCTION GET_ATTRIBUTI_PASSWORD /* SLAVE_COPY */
   ( p_utente IN VARCHAR2)
   RETURN VARCHAR2;
   FUNCTION GET_ASCENDENZA /* SLAVE_COPY */
   ( p_utente_o_gruppo IN VARCHAR2
   , p_gruppo IN VARCHAR2
   , p_concatena IN VARCHAR2 DEFAULT 'TCD') RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_GRUPPI_UTENTE
 DESCRIZIONE: Dato un utente, ritorna i gruppi a cui egli appartiene.
 PARAMETRI:   p_utente varchar2   codice dell'utente
 RITORNA:     varchar2: lista dei gruppi a cui l'utente appartiene separati da
                        virgola
******************************************************************************/
   FUNCTION GET_GRUPPI /* SLAVE_COPY */
   ( p_utente_o_gruppo VARCHAR2
   , p_codice VARCHAR2 DEFAULT 'Y'
   , p_descrizione VARCHAR2 DEFAULT 'N')
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        RIGENERA_SO.
 DESCRIZIONE: Permette di rigenerare l'albero dei gruppi corrispondenti alla
              struttura organizzativa (viene lanciata ad ogni login di ogni
              utente per la rigenerazione del sottoalbero a cui appartiene e
              puo' essere lanciata 'a comando' tramite l'attivazione di un job
              da applicativo per rigenerare l'intero albero).
 ARGOMENTI:   p_utente_o_gruppo: codice utente o gruppo per cui si vuole
                                 rigenerare la struttura (% per rigenerare
                                 l'intero albero).
 ECCEZIONI:   --
 ANNOTAZIONI: La procedure viene eseguita in una transaction separata in quanto
              esegue commit/rollback.
******************************************************************************/
PROCEDURE sistema_posizione_gruppi
/******************************************************************************

043 07/01/2019 SNeg In rigenera_so verificare che utente sia nel gruppo corretto
                    in base al fatto che utente sia amministratore o meno
******************************************************************************/
    (p_utente_o_gruppo IN VARCHAR2);

   FUNCTION RIGENERA_SO ( p_utente_o_gruppo IN VARCHAR2) RETURN NUMBER;
/******************************************************************************
 NOME:        RIGENERA_SO.
 DESCRIZIONE: Permette di rigenerare l'albero dei gruppi corrispondenti alla
              struttura organizzativa (viene lanciata ad ogni login di ogni
              utente per la rigenerazione del sottoalbero a cui appartiene e
              puo' essere lanciata 'a comando' tramite l'attivazione di un job
              da applicativo per rigenerare l'intero albero).
 ARGOMENTI:   p_utente_o_gruppo: codice utente o gruppo per cui si vuole
                                 rigenerare la struttura (% per rigenerare
                                 l'intero albero).
 ECCEZIONI:   --
 ANNOTAZIONI: La procedure viene eseguita in una transaction separata in quanto
              esegue commit/rollback.
******************************************************************************/
   PROCEDURE RIGENERA_SO ( p_utente_o_gruppo IN VARCHAR2);
/******************************************************************************
 NOME:        IS_SO4_USER
 DESCRIZIONE: Dato un utente, controlla se appartiene alla struttura organizzativa.
 PARAMETRI:   p_utente varchar2   codice dell'utente
 RITORNA:     number: 1 appartiene
                      0 non appartiene
******************************************************************************/
   FUNCTION IS_SO4_USER /* SLAVE_COPY */
   ( p_utente VARCHAR2)
   RETURN NUMBER;
/******************************************************************************
 NOME:        exists_id
 DESCRIZIONE: Esistenza riga con chiave indicata.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
FUNCTION exists_id /* SLAVE_COPY */
( p_id_utente  IN UTENTI.id_utente%TYPE
) RETURN NUMBER;
/******************************************************************************
 NOME:        exists_codice
 DESCRIZIONE: Esistenza riga con codice utente indicato.
 PARAMETRI:   p_utente codice utente/gruppo.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
FUNCTION exists_codice /* SLAVE_COPY */
( p_utente  IN UTENTI.utente%TYPE
) RETURN NUMBER;
/******************************************************************************
 NOME:        exists_utente
 DESCRIZIONE: Esistenza utente con codice indicato.
 PARAMETRI:   p_utente codice utente.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
FUNCTION exists_utente /* SLAVE_COPY */
( p_utente  IN UTENTI.utente%TYPE
) RETURN NUMBER;
/******************************************************************************
 NOME:        exists_nominativo
 DESCRIZIONE: Esistenza riga con nominativo indicato.
 PARAMETRI:   p_nominativo nominativo utente/gruppo.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
FUNCTION exists_nominativo /* SLAVE_COPY */
( p_nominativo  IN UTENTI.nominativo%TYPE
) RETURN NUMBER;
/******************************************************************************
 NOME:        exists_username
 DESCRIZIONE: Esistenza riga con nominativo indicato.
 PARAMETRI:   p_username username utente.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
FUNCTION exists_username /* SLAVE_COPY */
( p_username  IN UTENTI.nominativo%TYPE
) RETURN NUMBER;
/******************************************************************************
 NOME:        IS_PASSWORD_VALIDA
 DESCRIZIONE: Dato un codice utente verifica che la sua password:
              - sia maggiore della lunghezza minima prevista;
              - contenga almeno un numero se previsto;
              - non contenga dei caratteri speciali se non previsto.
 PARAMETRI:   p_utente        IN varchar2 codice utente di cui verificare la
                                          password
 RITORNA:     number:  ritorno della funzione ICRYPT.IS_PASSWORD_UTENTE_VALIDA;
                       cioe':
                       1   = Verifica effettuata con successo.
                       0   = La password non puo' essere nulla.
                      -1   = La password deve essere di almeno 'p_min_length' caratteri.
                      -2   = La password non puo' contenere caratteri speciali.
                      -3   = La password deve contenere almeno un numero.
 ECCEZIONI:   -20999: Utente '<p_utente>' non definito.
******************************************************************************/
FUNCTION IS_PASSWORD_VALIDA /* SLAVE_COPY */
( p_utente VARCHAR2
) RETURN NUMBER;
procedure REMOVE_LDAP_AUTHENTICATION
( p_utente in utenti.UTENTE%type
);
procedure elimina_caac_utente
( p_utente in utenti.UTENTE%type
);
PROCEDURE gestisci_si_si3 (
   p_si_si3                   VARCHAR2,
   p_insert_or_update         VARCHAR2,
   p_new_utente               VARCHAR2,
   p_new_nominativo           VARCHAR2,
   p_new_password             VARCHAR2,
   p_new_data_password        VARCHAR2,
   p_new_note                 VARCHAR2,
   p_new_lingua               VARCHAR2,
   p_new_gruppo_lavoro        VARCHAR2,
   p_new_importanza           VARCHAR2,
   p_new_data_accesso         VARCHAR2,
   p_new_tentativi            VARCHAR2,
   p_new_giorni_password      VARCHAR2,
   p_new_traccia              VARCHAR2,
   p_new_tentativi_password   VARCHAR2,
   p_new_accesso              VARCHAR2,
   p_new_rinnovo_password     VARCHAR2,
   p_new_utente_aggiornamento VARCHAR2,
   p_old_utente               VARCHAR2,
   p_old_nominativo           VARCHAR2,
   p_old_giorni_password      VARCHAR2,
   p_old_traccia              VARCHAR2,
   p_old_tentativi_password   VARCHAR2,
   p_old_accesso              VARCHAR2,
   p_is_accesso               NUMBER
);
FUNCTION get_mess_invio_password /* SLAVE_COPY */
  (p_utente varchar2)
      RETURN VARCHAR2;
FUNCTION get_ogg_invio_password /* SLAVE_COPY */
  (p_utente varchar2)
      RETURN VARCHAR2;
/******************************************************************************
 NOME:         unificazione_profilo
 DESCRIZIONE: Unifica il profilo di due utenti tenendone uno e l'altro lo mette a revocato.
 PARAMETRI:   p_utente_alimentare IN utente che rimarrà abilitato
                      p_utente_revocare  IN utente da revocare
                     p_soggetto_assegnare IN soggetto da assegnare all'utente da tenere sovrascrive eventuali registrazioni
                     p_tenere_utgr_alimentare varchar2  indica se preservo i gruppi dell'utente da alimentare
                     p_tenere_utgr_revocare varchar2  indica se copiare nell'utente da tenere i gruppi dell'utente da revocare
                     p_tenere_diac_alimentare varchar2  indica se preservo i diritti di accesso dell'utente da alimentare
                     p_tenere_diac_revocare varchar2  indica se copiare nell'utente da tenere i diac dell'utente da revocare
                     p_tenere_caac_alimentare varchar2  indica se preservo carattteristiche di accesso dell'utente da alimentare
                     p_tenere_caac_revocare varchar2  indica se copiare nell'utente da tenere le caac dell'utente da revocare
******************************************************************************/
PROCEDURE  unifica_profilo
( p_utente_alimentare  IN UTENTI.UTENTE%TYPE
, p_utente_revocare  IN UTENTI.UTENTE%TYPE
, p_soggetto_assegnare IN UTENTI_SOGGETTI.SOGGETTO%TYPE default null
, p_tenere_utgr_alimentare varchar2 default 'SI'
, p_copiare_utgr_revocare varchar2 default 'SI'
, p_tenere_diac_alimentare varchar2 default 'SI'
, p_copiare_diac_revocare varchar2 default 'SI'
, p_tenere_caac_alimentare varchar2 default 'SI'
, p_copiare_caac_revocare varchar2 default 'SI'
, p_utente_agg utenti.utente%TYPE default user
) ;
/******************************************************************************
 NOME:         copia_profilo
 DESCRIZIONE: Copia il profilo di un utente sull'altro.
 PARAMETRI:   p_utente_alimentare IN utente su cui copiare
                      p_utente_origine  IN utente da cui copiare
                      p_copiare_utgr IN indica se copiare i gruppi di appartenenza
                     p_copiare_diac varchar2  indica se copiare i diritti di accesso
                     p_copiare_caac varchar2  indica se copiare le caratteristiche di accesso
******************************************************************************/
PROCEDURE  copia_profilo
( p_utente_alimentare  IN UTENTI.UTENTE%TYPE
, p_utente_origine  IN UTENTI.UTENTE%TYPE
, p_copiare_utgr varchar2 default 'SI'
, p_copiare_diac varchar2 default 'SI'
, p_copiare_caac  varchar2 default 'SI'
, p_utente_agg utenti.utente%TYPE default user
, p_da_unificare number default 0
) ;
function determina_utente
(p_ni number
) return varchar2
;
function determina_nominativo
( p_ni in number
) return varchar2
;
function leggi_regola_nominativo
 return varchar2
;
function leggi_commento_regola_nom
 return varchar2
;
procedure imposta_registro_regola_nom
(p_valore registro.stringa%TYPE)
;
FUNCTION determina_utente_libero
(p_nominativo VARCHAR2)
   RETURN VARCHAR2;
PROCEDURE verifica_utente (
      p_utente           OUT      VARCHAR2,
      p_nominativo       IN       VARCHAR2,
      p_nome             IN       VARCHAR2,
      p_codice_fiscale   IN       VARCHAR2,
      p_comune_nas       IN       NUMBER,
      p_provincia_nas    IN       NUMBER,
      p_data_nascita     IN       VARCHAR2,
      p_sesso            IN       VARCHAR2
   );
PROCEDURE SEND_MSG_MODIFICA_UTENTE
( subject IN VARCHAR2
, text IN VARCHAR2
)   ;

PROCEDURE RISISTEMA_DIAC_UTENTE (p_utente VARCHAR2, p_modulo varchar2 default '%');

PROCEDURE RISISTEMA_DIAC_MODULO (p_modulo VARCHAR2);

PROCEDURE RISISTEMA_DIAC_TUTTI_UTENTI ;

FUNCTION GET_IS_SISTEMAZIONE_GRUPPI return boolean;

END Utente;
/
