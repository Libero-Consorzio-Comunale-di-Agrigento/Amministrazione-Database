--liquibase formatted sql

--changeset mturra:201901301231_202 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE Ad4_Evento IS /* MASTER_LINK */
/******************************************************************************
 DESCRIZIONE: Package di gestione EVENTI.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    17/12/2003 MM     Prima emissione.
 1    22/11/2005 MM     Modificata UPDATE_EVENTO.
 2    07/12/2005 MM     Introduzione delle funzioni di gestione FILE_LOCATOR.
 3    06/06/2007 MM     Modificata UPDATE_EVENTO per gestione EVEN_SQ.
******************************************************************************/
   /******************************************************************************
    NOME:        VERSIONE
    DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
    PARAMETRI:   --
    RITORNA:     stringa varchar2 contenente versione e data.
    NOTE:        Il secondo numero della versione corrisponde alla revisione
                 del package.
   ******************************************************************************/
   FUNCTION  VERSIONE /* SLAVE_COPY */
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_TESTO
    DESCRIZIONE: Restituisce il testo dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente il testo.
   ******************************************************************************/
   FUNCTION GET_TESTO
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_ANNOTAZIONI
    DESCRIZIONE: Restituisce le annotazioni dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente le annotazioni.
   ******************************************************************************/
   FUNCTION GET_ANNOTAZIONI
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_TIPO
    DESCRIZIONE: Restituisce il tipo dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente il tipo.
   ******************************************************************************/
   FUNCTION GET_TIPO /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_TIPO_DESC
    DESCRIZIONE: Restituisce la descrizione  del tipo di evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente il tipo.
   ******************************************************************************/
   FUNCTION GET_TIPO_DESC /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   FUNCTION GET_TIPO_DESC_RIDOTTA /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_GRAVITA
    DESCRIZIONE: Restituisce la gravita' dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente la gravita'.
   ******************************************************************************/
   FUNCTION GET_GRAVITA /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_DESC_GRAVITA
    DESCRIZIONE: Restituisce la descrizione della gravita' dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente la gravita'.
   ******************************************************************************/
   FUNCTION GET_DESC_GRAVITA /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_DB_USER
    DESCRIZIONE: Restituisce lo user di db dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente lo user di db.
   ******************************************************************************/
   FUNCTION GET_DB_USER /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_UTENTE
    DESCRIZIONE: Restituisce l'utente dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente l'utente.
   ******************************************************************************/
   FUNCTION GET_UTENTE /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_MODULO
    DESCRIZIONE: Restituisce il modulo dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente il modulo.
   ******************************************************************************/
   FUNCTION GET_MODULO /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_ISTANZA
    DESCRIZIONE: Restituisce l'istanza dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente l'istanza.
   ******************************************************************************/
   FUNCTION GET_ISTANZA /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_DATA
    DESCRIZIONE: Restituisce la data di registrazione dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente la data in formato
                 'dd/mm/yyyy hh24:mi:ss'.
   ******************************************************************************/
   FUNCTION GET_DATA /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_NOTIFICATO
    DESCRIZIONE: Restituisce 1 se l'evento e' stato notificato, 0 altrimenti.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     number (1/0) contenente l'informazione della notifica.
   ******************************************************************************/
   FUNCTION GET_NOTIFICATO /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN NUMBER;
   /******************************************************************************
    NOME:        GET_STATO
    DESCRIZIONE: Restituisce il codice dello stato dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     varchar2      contenente codice dello stato.
   ******************************************************************************/
   FUNCTION GET_STATO /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_DESC_STATO
    DESCRIZIONE: Restituisce la descrizione dello stato dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     varchar2      contenente descrizione dello stato.
   ******************************************************************************/
   FUNCTION GET_DESC_STATO /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_FILE_LOCATOR
    DESCRIZIONE: Restituisce l'eventuale puntatore al file associato all'evento.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     BFILE.
   ******************************************************************************/
   FUNCTION GET_FILE_LOCATOR /* SLAVE_COPY */
   (p_id_evento IN NUMBER)
   RETURN BFILE;
   /******************************************************************************
    NOME:        GET_FILE_LOCATOR_INFO
    DESCRIZIONE: Restituisce il nome del file su disco con o senza percorso.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
                p_path        1 se si vuole il percorso completo.
                               0 se si vuole solo il nome del file.
                               2 se si vuole il solo percorso della directory.
    RITORNA:     varchar2.
   ******************************************************************************/
   FUNCTION GET_FILE_LOCATOR_INFO
   ( p_id_evento IN NUMBER
   , p_path      IN NUMBER DEFAULT 1) RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_FILENAME
    DESCRIZIONE: Restituisce il nome del file su disco con o senza percorso.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
                p_path        1 se si vuole anche il percorso completo
                               0 se si vuole solo il nome del file.
    RITORNA:     varchar2.
   ******************************************************************************/
   FUNCTION GET_FILENAME /* SLAVE_COPY */
   ( p_id_evento IN NUMBER
   , p_path      IN NUMBER DEFAULT 1)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_DIR_ALIAS
    DESCRIZIONE: Restituisce il nome del file su disco con o senza percorso.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
                p_path        1 se si vuole anche il percorso completo
                               0 se si vuole solo il nome del file.
    RITORNA:     varchar2.
   ******************************************************************************/
   FUNCTION GET_DIR_ALIAS ( p_id_evento IN NUMBER)
   RETURN VARCHAR2;
   /******************************************************************************
    NOME:        INFO_DIR_ALIAS
    DESCRIZIONE: Ottiene il numero di file presenti nella directory.
    ARGOMENTI:   p_dir_alias   alias o percorso della directory.
                p_file_num    numero di file presenti nella directory.
                 p_file_size   dimensione totale dei file presenti nella directory.
                 p_file_filter eventuale filtro da applicare sul nome del file.
   ******************************************************************************/
   PROCEDURE INFO_DIR_ALIAS  ( p_dir_alias   IN VARCHAR2
                             , p_file_num    IN OUT NUMBER
                             , p_file_size   IN OUT NUMBER
                             , p_file_filter IN VARCHAR2 DEFAULT '');
   /******************************************************************************
    NOME:        get_dir_num_file
    DESCRIZIONE: Ottiene il numero di file presenti nella directory.
    PARAMETRI:   p_dir_alias   alias o percorso della directory.
                 p_file_filter eventuale filtro da applicare sul nome del file.
                 p_initialize  indica se si vuole rieseguire la select (1) o
                           leggere il valore della variabile(0).
    RITORNA:     number numero di file presenti.
   ******************************************************************************/
   FUNCTION get_dir_num_file ( p_dir_alias IN VARCHAR2
                             , p_file_filter IN VARCHAR2 DEFAULT ''
                             , p_initialize IN NUMBER DEFAULT 0)
   RETURN NUMBER;
   /******************************************************************************
    NOME:        get_dir_tot_dim
    DESCRIZIONE: Ottiene la dimensione totale dei file presenti nella directory.
    PARAMETRI:   p_dir_alias   alias o percorso della directory.
                 p_file_filter eventuale filtro da applicare sul nome del file.
                 p_initialize  indica se si vuole rieseguire la select (1) o
                           leggere il valore della variabile (0).
    RITORNA:     number dimensione totale dei file.
   ******************************************************************************/
   FUNCTION get_dir_tot_dim ( p_dir_alias IN VARCHAR2
                            , p_file_filter IN VARCHAR2 DEFAULT ''
                            , p_initialize IN NUMBER DEFAULT 0)
   RETURN NUMBER;
   /******************************************************************************
    NOME:        copy_file
    DESCRIZIONE: Copia i file presenti nella directory di partenza in quella di
                arrivo.
    ARGOMENTI:   p_dir_from   alias o percorso directory di partenza.
                p_dir_to     alias o percorso directory di arrivo.
                 p_file_filter eventuale filtro da applicare sul nome del file.
   ******************************************************************************/
   PROCEDURE copy_file  ( p_dir_from    IN VARCHAR2
                        , p_dir_to      IN VARCHAR2
                        , p_file_filter IN VARCHAR2);
   /******************************************************************************
    NOME:        CHECK_FILE_LOCATOR
    DESCRIZIONE: Verifica l' esistenza del file associato all'evento sia sulla
                tabella che sul file system.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     1 se il file locator e' presente ed il file esiste.
                0 se il file locator non e' presente
                -1 se il file locator e' presente ma il file non esiste.
                -2 se l'evento passato non esiste.
   ******************************************************************************/
   FUNCTION CHECK_FILE_LOCATOR (p_id_evento IN NUMBER)
   RETURN NUMBER;
   /******************************************************************************
    NOME:        SET_TESTO
    DESCRIZIONE: Modifica l'attributo TESTO dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_testo IN VARCHAR2 valore da associare all'attributo TESTO.
   ******************************************************************************/
   PROCEDURE SET_TESTO            ( p_id_evento IN NUMBER
                                  , p_testo IN VARCHAR2);
   /******************************************************************************
    NOME:        SET_ANNOTAZIONI
    DESCRIZIONE: Modifica l'attributo ANNOTAZIONI dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_annotazioni IN VARCHAR2 valore da associare all'attributo ANNOTAZIONI.
   ******************************************************************************/
   PROCEDURE SET_ANNOTAZIONI      ( p_id_evento IN NUMBER
                                  , p_annotazioni IN VARCHAR2);
   /******************************************************************************
    NOME:        SET_TIPO
    DESCRIZIONE: Modifica l'attributo TIPO dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_tipo IN VARCHAR2 valore da associare all'attributo TIPO.
   ******************************************************************************/
   PROCEDURE SET_TIPO             ( p_id_evento IN NUMBER
                                  , p_tipo IN VARCHAR2);
   /******************************************************************************
    NOME:        SET_GRAVITA
    DESCRIZIONE: Modifica l'attributo GRAVITA dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_gravita IN VARCHAR2 valore da associare all'attributo GRAVITA.
   ******************************************************************************/
   PROCEDURE SET_GRAVITA          ( p_id_evento IN NUMBER
                                  , p_gravita IN VARCHAR2);
   /******************************************************************************
    NOME:        SET_DB_USER
    DESCRIZIONE: Modifica l'attributo DB_USER dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_db_user IN VARCHAR2 valore da associare all'attributo DB_USER.
   ******************************************************************************/
   PROCEDURE SET_DB_USER          ( p_id_evento IN NUMBER
                                  , p_db_user IN VARCHAR2);
   /******************************************************************************
    NOME:        SET_UTENTE
    DESCRIZIONE: Modifica l'attributo UTENTE dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_utente IN VARCHAR2 valore da associare all'attributo UTENTE.
   ******************************************************************************/
   PROCEDURE SET_UTENTE           ( p_id_evento IN NUMBER
                                  , p_utente IN VARCHAR2);
   /******************************************************************************
    NOME:        SET_MODULO
    DESCRIZIONE: Modifica l'attributo MODULO dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_modulo IN VARCHAR2 valore da associare all'attributo MODULO.
   ******************************************************************************/
   PROCEDURE SET_MODULO           ( p_id_evento IN NUMBER
                                  , p_modulo IN VARCHAR2);
   /******************************************************************************
    NOME:        SET_ISTANZA
    DESCRIZIONE: Modifica l'attributo ISTANZA dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_istanza IN VARCHAR2 valore da associare all'attributo ISTANZA.
   ******************************************************************************/
   PROCEDURE SET_ISTANZA          ( p_id_evento IN NUMBER
                                  , p_istanza IN VARCHAR2);
   /******************************************************************************
    NOME:        SET_DATA
    DESCRIZIONE: Modifica l'attributo DATA dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER   identificativo dell'evento.
                 p_data      IN VARCHAR2 valore da associare all'attributo DATA in
                                         formato 'dd/mm/yyyy hh24:mi:ss'.
   ******************************************************************************/
   PROCEDURE SET_DATA             ( p_id_evento IN NUMBER
                                  , p_data IN VARCHAR2);
   /******************************************************************************
    NOME:        SET_NOTIFICATO
    DESCRIZIONE: Modifica l'evento in modo che risulti NOTIFICATO.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
   ******************************************************************************/
   PROCEDURE SET_NOTIFICATO       ( p_id_evento NUMBER );
   /******************************************************************************
    NOME:        SET_NOTIFICATO
    DESCRIZIONE: Modifica l'attributo NOTIFICATO dell'evento con il valore
                 passato.
    ARGOMENTI:   p_id_evento  IN NUMBER identificativo dell'evento.
                 p_notificato IN NUMBER valore da associare all'attributo
                                        NOTIFICATO.
   ******************************************************************************/
   PROCEDURE SET_NOTIFICATO       ( p_id_evento NUMBER
                                  , p_notificato NUMBER);
   /******************************************************************************
    NOME:        SET_FILE_LOCATOR
    DESCRIZIONE: Modifica l'attributo FILE_LOCATOR dell'evento con il valore
                 passato.
    ARGOMENTI:   p_id_evento    IN NUMBER identificativo dell'evento.
                 p_file_locator IN BFILE  valore da associare all'attributo
                                          FILE_LOCATOR.
   ******************************************************************************/
   PROCEDURE SET_FILE_LOCATOR /* SLAVE_COPY */
                                ( p_id_evento    IN NUMBER
                                , p_file_locator IN BFILE);
   PROCEDURE SET_FILE_LOCATOR   ( p_id_evento    IN NUMBER
                                , p_dir_alias    IN VARCHAR2
                                , p_filename     IN VARCHAR2);
   /******************************************************************************
    NOME:        UPDATE_EVENTO
    DESCRIZIONE: Permette l'aggiornamento / inserimento di un evento.
                 Se viene passato un identificativo di evento significativo
                 (p_id_evento not null),
                    aggiorna l'evento con i dati passati (tutti),
                 altrimenti
                    inserisce un nuovo evento con i dati passati.
                 L'evento sara' registrato:
                 - in data p_data
                 - notificato o non notificato (p_notificato)
                 - con gravita' specificatata (p_gravita)
                 - di tipo dato (p_tipo)
                 - contenente il testo dato(p_testo)
                 - relativo allo user di database dato (p_db_user)
                 - con le informazioni aggiuntive date (p_annotazioni)
                 - per l'utente, il modulo e l'istanza  di procedura dati (p_utente
                   p_modulo, p_istanza).
    ARGOMENTI:   p_id_evento   IN NUMBER   Identificativo dell'evento.
                 p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
                 p_data        IN VARCHAR2 Data di registrazione dell'evento in
                                           formato 'dd/mm/yyyy hh24:mi:ss' (se
                                           non specificata assume il valore
                                           sysdate).
                 p_notificato  IN VARCHAR2 Indicatore di avvenuta notifica
                                           dell'evento (se non specificato assume
                                           il valore 0 - NON Notificato).
                 p_gravita     IN VARCHAR2 Gravita' dell'evento (se non specificata
                                           assume il valore 'I' - Informazione).
                 p_tipo        IN VARCHAR2 Tipologia dell'evento (se non specificata
                                           assume il valore 'REPLAY').
                 p_annotazioni IN VARCHAR2 Eventuali informazioni aggiuntive
                                           dell'evento.
                 p_utente      IN VARCHAR2 Eventuale utente di procedura
                                           dell'evento.
                 p_modulo      IN VARCHAR2 Eventuale modulo di procedura
                                           dell'evento.
                 p_istanza     IN VARCHAR2 Eventuale  istanza di ambiente
                                           dell'evento.
    ECCEZIONI:   -20999, Il testo e l'utente di database (db_user) dell'evento
                         non possono essere nulli.
   ******************************************************************************/
   PROCEDURE UPDATE_EVENTO   ( p_id_evento   IN OUT NUMBER
                             , p_testo       IN     VARCHAR2
                             , p_db_user     IN     VARCHAR2
                             , p_data        IN     VARCHAR2
                             , p_notificato  IN     NUMBER
                             , p_gravita     IN     VARCHAR2
                             , p_tipo        IN     VARCHAR2
                             , p_annotazioni IN     VARCHAR2
                             , p_utente      IN     VARCHAR2
                             , p_modulo      IN     VARCHAR2
                             , p_istanza     IN     VARCHAR2);
   /******************************************************************************
    NOME:        UPDATE_EVENTO
    DESCRIZIONE: Permette l'aggiornamento / inserimento di un evento.
                 Se viene passato un identificativo di evento significativo
                 (p_id_evento not null),
                    aggiorna l'evento con i dati passati (tutti),
                 altrimenti
                    inserisce un nuovo evento con i dati passati.
                 L'evento sara' registrato:
                 - in data odierna
                 - non notificato
                 - con gravita' Informazione
                 - di tipo REPLAY
                 - contenente il testo dato(p_testo)
                 - relativo allo user di database dato (p_db_user)
                 - con le informazioni aggiuntive date (p_annotazioni)
                 - per l'utente, il modulo e l'istanza  di procedura dati (p_utente
                   p_modulo, p_istanza).
    ARGOMENTI:   p_id_evento   IN NUMBER   Identificativo dell'evento.
                 p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
                 p_annotazioni IN VARCHAR2 Eventuali informazioni aggiuntive
                                           dell'evento.
                 p_utente      IN VARCHAR2 Eventuale utente di procedura
                                           dell'evento.
                 p_modulo      IN VARCHAR2 Eventuale modulo di procedura
                                           dell'evento.
                 p_istanza     IN VARCHAR2 Eventuale  istanza di ambiente
                                           dell'evento.
    ECCEZIONI:   -20999, Il testo e l'utente di database (db_user) dell'evento
                         non possono essere nulli.
   ******************************************************************************/
   PROCEDURE UPDATE_EVENTO   ( p_id_evento   IN OUT NUMBER
                             , p_testo       IN VARCHAR2
                             , p_db_user     IN VARCHAR2
                             , p_annotazioni IN VARCHAR2
                             , p_utente      IN VARCHAR2
                             , p_modulo      IN VARCHAR2
                             , p_istanza     IN VARCHAR2);
   /******************************************************************************
    NOME:        UPDATE_EVENTO
    DESCRIZIONE: Permette l'aggiornamento / inserimento di un evento.
                 Se viene passato un identificativo di evento significativo
                 (p_id_evento not null),
                    aggiorna l'evento con i dati passati (tutti),
                 altrimenti
                    inserisce un nuovo evento con i dati passati.
                 L'evento sara' registrato:
                 - in data odierna
                 - non notificato
                 - con gravita' Informazione
                 - di tipo REPLAY
                 - contenente il testo dato(p_testo)
                 - relativo allo user di database dato (p_db_user).
    ARGOMENTI:   p_id_evento   IN NUMBER   Identificativo dell'evento.
                 p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
    ECCEZIONI:   -20999, Il testo e l'utente di database (db_user) dell'evento
                         non possono essere nulli.
   ******************************************************************************/
   PROCEDURE UPDATE_EVENTO   ( p_id_evento IN OUT NUMBER
                             , p_testo     IN     VARCHAR2
                             , p_db_user   IN     VARCHAR2);
   /******************************************************************************
    NOME:        INSERT_EVENTO
    DESCRIZIONE: Permette l'inserimento di un evento:
                 - in data p_data
                 - notificato o non notificato (p_notificato)
                 - con gravita' specificatata (p_gravita)
                 - di tipo dato (p_tipo)
                 - contenente il testo dato(p_testo)
                 - relativo allo user di database dato (p_db_user)
                 - con le informazioni aggiuntive date (p_annotazioni)
                 - per l'utente, il modulo e l'istanza  di procedura dati (p_utente
                   p_modulo, p_istanza).
    PARAMETRI:   p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
                 p_data        IN VARCHAR2 Data di registrazione dell'evento (se
                                           non specificata assume il valore
                                           sysdate).
                 p_notificato  IN VARCHAR2 Indicatore di avvenuta notifica
                                           dell'evento (se non specificato assume
                                           il valore 0 - NON Notificato).
                 p_gravita     IN VARCHAR2 Gravita' dell'evento (se non specificata
                                           assume il valore 'I' - Informazione).
                 p_tipo        IN VARCHAR2   Tipologia dell'evento (se non specificata
                                           assume il valore 'REPLAY').
                 p_annotazioni IN VARCHAR2 Eventuali informazioni aggiuntive
                                           dell'evento.
                 p_utente      IN VARCHAR2 Eventuale utente di procedura
                                           dell'evento.
                 p_modulo      IN VARCHAR2 Eventuale modulo di procedura
                                           dell'evento.
                 p_istanza     IN VARCHAR2 Eventuale  istanza di ambiente
                                           dell'evento.
    RITORNA:     number contenente l'identificativo dell'evento.
   ******************************************************************************/
   FUNCTION INSERT_EVENTO    ( p_testo       IN VARCHAR2
                             , p_db_user     IN VARCHAR2
                             , p_data        IN VARCHAR2
                             , p_notificato  IN NUMBER
                             , p_gravita     IN VARCHAR2
                             , p_tipo        IN VARCHAR2
                             , p_annotazioni IN VARCHAR2
                             , p_utente      IN VARCHAR2
                             , p_modulo      IN VARCHAR2
                             , p_istanza     IN VARCHAR2)
   RETURN NUMBER;
   /******************************************************************************
    NOME:        INSERT_EVENTO
    DESCRIZIONE: Permette l'inserimento di un evento:
                 - in data odierna
                 - non notificato
                 - con gravita' "Informazione"
                 - di tipo REPLAY
                 - contenente il testo specificato (p_testo)
                 - relativo allo user di database specificato (p_db_user)
                 - con le informazioni aggiuntive date (p_annotazioni),
                 - per l'utente, il modulo e l'istanza  di procedura dati (p_utente,
                   p_modulo, p_istanza).
    PARAMETRI:   p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
                 p_annotazioni IN VARCHAR2 Eventuali informazioni aggiuntive
                                           dell'evento.
                 p_utente      IN VARCHAR2 Eventuale utente di procedura
                                           dell'evento.
                 p_modulo      IN VARCHAR2 Eventuale modulo di procedura
                                           dell'evento.
                 p_istanza     IN VARCHAR2 Eventuale  istanza di ambiente
                                           dell'evento.
    RITORNA:     number contenente l'identificativo dell'evento.
   ******************************************************************************/
   FUNCTION INSERT_EVENTO    ( p_testo       IN VARCHAR2
                             , p_db_user     IN VARCHAR2
                             , p_annotazioni IN VARCHAR2
                             , p_utente      IN VARCHAR2
                             , p_modulo      IN VARCHAR2
                             , p_istanza     IN VARCHAR2)
   RETURN NUMBER;
   /******************************************************************************
    NOME:        INSERT_EVENTO
    DESCRIZIONE: Permette l'inserimento di un evento:
                 - in data odierna
                 - non notificato
                 - con gravita' "Informazione"
                 - di tipo REPLAY
                 - contenente il testo specificato (p_testo)
                 - relativo allo user di database specificato (p_db_user).
    PARAMETRI:   p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
    RITORNA:     NUMBER contenente l'identificativo dell'evento.
   ******************************************************************************/
   FUNCTION INSERT_EVENTO    ( p_testo       IN VARCHAR2
                             , p_db_user     IN VARCHAR2)
   RETURN NUMBER;
   FUNCTION ripristina
   ( p_id_evento IN NUMBER
   , p_table     IN VARCHAR2
   , p_rowtag     IN VARCHAR2 DEFAULT 'row')
   RETURN NUMBER;
   FUNCTION GET_LOCATOR_MIMETYPE ( p_id_evento NUMBER )
   RETURN VARCHAR2;

   PROCEDURE INSERT_EVENTO_COMMIT    ( p_testo       IN VARCHAR2
                             , p_db_user     IN VARCHAR2
                             , p_data        IN VARCHAR2
                             , p_notificato  IN NUMBER
                             , p_gravita     IN VARCHAR2
                             , p_tipo        IN VARCHAR2
                             , p_annotazioni IN VARCHAR2
                             , p_utente      IN VARCHAR2
                             , p_modulo      IN VARCHAR2
                             , p_istanza     IN VARCHAR2)
   ;
END Ad4_Evento;
/
