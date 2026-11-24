--liquibase formatted sql

--changeset mturra:201901301231_147 runOnChange:true stripComments:false

CREATE OR REPLACE PACKAGE BODY Istanza IS
/******************************************************************************
 NOME:        ISTANZA
 DESCRIZIONE: Gestione tabella ISTANZE.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   21/09/2006  MMALFERRARI  Prima emissione.
 001   11/07/2007  MMALFERRARI  Gestione campo ISTANZA_AMMINISTRATORE
                   Rimappate le funzioni  a quelle di ISTANZE_TPK.
 002   13/01/2009  MM   CREAZIONE IS_ISTANZA_MASTER.
 003   27/11/2009  SNeg Creazione set_istanza
 004   21/04/2010 SNeg  Correzione set_istanza per errato uso db link
 005  27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
                       (modificato quanto introdotto precedentemente con indicazione md5)
******************************************************************************/
   s_revisione_body      CONSTANT Afc.t_revision := '005';
   s_error_table Afc_Error.t_error_table;
--------------------------------------------------------------------------------
FUNCTION versione
RETURN VARCHAR2 IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        versione
 DESCRIZIONE: Versione e revisione di distribuzione del package.
 RITORNA:     varchar2 stringa contenente versione e revisione.
 NOTE:        Primo numero  : versione compatibilita del Package.
              Secondo numero: revisione del Package specification.
              Terzo numero  : revisione del Package body.
******************************************************************************/
BEGIN
   RETURN Afc.VERSION ( s_revisione, s_revisione_body );
END; -- ISTANZA.versione
--------------------------------------------------------------------------------
FUNCTION PK
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN istanze_tpk.t_PK IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
BEGIN
   return istanze_tpk.PK(p_ISTANZA);
END; -- end ISTANZA.PK
--------------------------------------------------------------------------------
FUNCTION can_handle
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN NUMBER IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        can_handle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: 1 se la chiave e manipolabile, 0 altrimenti.
 NOTE:        cfr. canHandle per ritorno valori boolean.
******************************************************************************/
   d_result NUMBER;
BEGIN
   d_result := istanze_tpk.can_handle(p_ISTANZA);
   RETURN  d_result;
END; -- ISTANZA.can_handle
--------------------------------------------------------------------------------
FUNCTION canHandle
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN BOOLEAN IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result CONSTANT BOOLEAN := Afc.to_boolean ( istanze_tpk.can_handle ( p_ISTANZA
                                                            )
                                               );
BEGIN
   RETURN  d_result;
END; -- ISTANZA.canHandle
--------------------------------------------------------------------------------
FUNCTION exists_id
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN NUMBER IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        exists_id
 DESCRIZIONE: Esistenza riga con chiave indicata.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
 NOTE:        cfr. existsId per ritorno valori boolean.
******************************************************************************/
   d_result NUMBER;
BEGIN
   RETURN  istanze_tpk.exists_id(p_ISTANZA);
END; -- ISTANZA.exists_id
--------------------------------------------------------------------------------
FUNCTION existsId
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN BOOLEAN IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.existsid ( p_ISTANZA );
END; -- ISTANZA.existsId
--------------------------------------------------------------------------------
FUNCTION error_message
( p_error_number  IN Afc_Error.t_error_number
) RETURN Afc_Error.t_error_msg IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        error_message
 DESCRIZIONE: Messaggio previsto per il numero di eccezione indicato.
 NOTE:        Restituisce il messaggio abbinato al numero indicato nella tabella
              s_error_table del PACKAGE.
******************************************************************************/
   d_result CONSTANT Afc_Error.t_error_msg := s_error_table( p_error_number );
BEGIN
   RETURN  d_result;
END; -- ISTANZA.error_message
--------------------------------------------------------------------------------
PROCEDURE ins
   ( p_ISTANZA  IN ISTANZE.Istanza%TYPE
   , p_PROGETTO  IN ISTANZE.PROGETTO%TYPE
   , p_ENTE  IN ISTANZE.ENTE%TYPE
   , p_DESCRIZIONE  IN ISTANZE.DESCRIZIONE%TYPE
   , p_DATABASE_USER IN ISTANZE.USER_ORACLE%TYPE
   , p_DATABASE_PASSWORD IN ISTANZE.PASSWORD_ORACLE%TYPE
   , p_DISLOCAZIONE  IN ISTANZE.DISLOCAZIONE%TYPE
   , p_DISLOCAZIONE_TEMPORANEA  IN ISTANZE.DISLOCAZIONE_TEMPORANEA%TYPE  DEFAULT NULL
   , p_INSTALLAZIONE  IN ISTANZE.INSTALLAZIONE%TYPE  DEFAULT NULL
   , p_VERSIONE  IN ISTANZE.VERSIONE%TYPE  DEFAULT NULL
   , p_DISLOCAZIONE_DIMENSIONAMENTI  IN ISTANZE.DISLOCAZIONE_DIMENSIONAMENTI%TYPE  DEFAULT NULL
   , p_NOTE  IN ISTANZE.NOTE%TYPE  DEFAULT NULL
   , p_LINGUA  IN ISTANZE.LINGUA%TYPE  DEFAULT 'I'
   , p_LINK_ORACLE  IN ISTANZE.LINK_ORACLE%TYPE  DEFAULT NULL
   , p_DATABASE_LINK  IN ISTANZE.DATABASE_LINK%TYPE  DEFAULT NULL
   , p_DATABASE_DRIVER  IN ISTANZE.DATABASE_DRIVER%TYPE  DEFAULT NULL
   , p_SERVIZIO  IN ISTANZE.SERVIZIO%TYPE  DEFAULT NULL
   , p_ISTANZA_AMMINISTRATORE IN ISTANZE.ISTANZA_AMMINISTRATORE%TYPE  DEFAULT NULL
) IS
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
BEGIN
   istanze_tpk.INS(p_ISTANZA
   , p_PROGETTO
   , p_ENTE
   , p_DESCRIZIONE
   , p_DATABASE_USER
   , p_DATABASE_PASSWORD
   , p_DISLOCAZIONE
   , p_DISLOCAZIONE_TEMPORANEA
   , p_INSTALLAZIONE
   , p_VERSIONE
   , p_DISLOCAZIONE_DIMENSIONAMENTI
   , p_NOTE
   , p_LINGUA
   , p_LINK_ORACLE
   , p_DATABASE_LINK
   , p_DATABASE_DRIVER
   , p_SERVIZIO
   , p_ISTANZA_AMMINISTRATORE);
END; -- ISTANZA.ins
--------------------------------------------------------------------------------
PROCEDURE upd
   ( p_NEW_ISTANZA  IN ISTANZE.Istanza%TYPE
   , p_NEW_PROGETTO  IN ISTANZE.PROGETTO%TYPE
   , p_NEW_ENTE  IN ISTANZE.ENTE%TYPE
   , p_NEW_DESCRIZIONE  IN ISTANZE.DESCRIZIONE%TYPE
   , p_NEW_DATABASE_USER IN ISTANZE.USER_ORACLE%TYPE
   , p_NEW_DATABASE_PASSWORD IN ISTANZE.PASSWORD_ORACLE%TYPE
   , p_NEW_DISLOCAZIONE  IN ISTANZE.DISLOCAZIONE%TYPE
   , p_NEW_DISLOCAZIONE_TEMPORANEA  IN ISTANZE.DISLOCAZIONE_TEMPORANEA%TYPE
   , p_NEW_INSTALLAZIONE  IN ISTANZE.INSTALLAZIONE%TYPE
   , p_NEW_VERSIONE  IN ISTANZE.VERSIONE%TYPE
   , p_NEW_DISL_DIMENSIONAMENTI  IN ISTANZE.DISLOCAZIONE_DIMENSIONAMENTI%TYPE
   , p_NEW_NOTE  IN ISTANZE.NOTE%TYPE  DEFAULT NULL
   , p_NEW_LINGUA  IN ISTANZE.LINGUA%TYPE
   , p_NEW_LINK_ORACLE  IN ISTANZE.LINK_ORACLE%TYPE  DEFAULT NULL
   , p_NEW_DATABASE_LINK  IN ISTANZE.DATABASE_LINK%TYPE  DEFAULT NULL
   , p_NEW_DATABASE_DRIVER  IN ISTANZE.DATABASE_DRIVER%TYPE  DEFAULT NULL
   , p_NEW_SERVIZIO  IN ISTANZE.SERVIZIO%TYPE  DEFAULT NULL
   , p_NEW_ISTANZA_AMMINISTRATORE IN ISTANZE.ISTANZA_AMMINISTRATORE%TYPE  DEFAULT NULL
   , p_OLD_ISTANZA  IN ISTANZE.Istanza%TYPE DEFAULT NULL
   , p_OLD_PROGETTO  IN ISTANZE.PROGETTO%TYPE DEFAULT NULL
   , p_OLD_ENTE  IN ISTANZE.ENTE%TYPE DEFAULT NULL
   , p_OLD_DESCRIZIONE  IN ISTANZE.DESCRIZIONE%TYPE DEFAULT NULL
   , p_OLD_DATABASE_USER IN ISTANZE.USER_ORACLE%TYPE DEFAULT NULL
   , p_OLD_DATABASE_PASSWORD IN ISTANZE.PASSWORD_ORACLE%TYPE DEFAULT NULL
   , p_OLD_DISLOCAZIONE  IN ISTANZE.DISLOCAZIONE%TYPE DEFAULT NULL
   , p_OLD_DISLOCAZIONE_TEMPORANEA  IN ISTANZE.DISLOCAZIONE_TEMPORANEA%TYPE DEFAULT NULL
   , p_OLD_INSTALLAZIONE  IN ISTANZE.INSTALLAZIONE%TYPE DEFAULT NULL
   , p_OLD_VERSIONE  IN ISTANZE.VERSIONE%TYPE DEFAULT NULL
   , p_OLD_DISL_DIMENSIONAMENTI  IN ISTANZE.DISLOCAZIONE_DIMENSIONAMENTI%TYPE DEFAULT NULL
   , p_OLD_NOTE  IN ISTANZE.NOTE%TYPE  DEFAULT NULL
   , p_OLD_LINGUA  IN ISTANZE.LINGUA%TYPE DEFAULT NULL
   , p_OLD_LINK_ORACLE  IN ISTANZE.LINK_ORACLE%TYPE  DEFAULT NULL
   , p_OLD_DATABASE_LINK  IN ISTANZE.DATABASE_LINK%TYPE  DEFAULT NULL
   , p_OLD_DATABASE_DRIVER  IN ISTANZE.DATABASE_DRIVER%TYPE  DEFAULT NULL
   , p_OLD_SERVIZIO  IN ISTANZE.SERVIZIO%TYPE  DEFAULT NULL
   , p_OLD_ISTANZA_AMMINISTRATORE IN ISTANZE.ISTANZA_AMMINISTRATORE%TYPE  DEFAULT NULL
   , p_check_OLD  IN INTEGER DEFAULT 0
) IS
/******************************************************************************
 NOME:        upd
 DESCRIZIONE: Aggiornamento di una riga con chiave.
 PARAMETRI:   Chiavi e attributi della table
              p_check_OLD: 0, ricerca senza controllo su attributi precedenti
                           1, ricerca con controllo anche su attributi precedenti.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
              Se p_check_old = 1, viene controllato se il record corrispondente a
              tutti i campi passati come parametri esiste nella tabella.
******************************************************************************/
BEGIN
   istanze_tpk.UPD(p_NEW_ISTANZA
   , p_NEW_PROGETTO
   , p_NEW_ENTE
   , p_NEW_DESCRIZIONE
   , p_NEW_DATABASE_USER
   , p_NEW_DATABASE_PASSWORD
   , p_NEW_DISLOCAZIONE
   , p_NEW_DISLOCAZIONE_TEMPORANEA
   , p_NEW_INSTALLAZIONE
   , p_NEW_VERSIONE
   , p_NEW_DISL_DIMENSIONAMENTI
   , p_NEW_NOTE
   , p_NEW_LINGUA
   , p_NEW_LINK_ORACLE
   , p_NEW_DATABASE_LINK
   , p_NEW_DATABASE_DRIVER
   , p_NEW_SERVIZIO
   , p_NEW_ISTANZA_AMMINISTRATORE
   , p_OLD_ISTANZA
   , p_OLD_PROGETTO
   , p_OLD_ENTE
   , p_OLD_DESCRIZIONE
   , p_OLD_DATABASE_USER
   , p_OLD_DATABASE_PASSWORD
   , p_OLD_DISLOCAZIONE
   , p_OLD_DISLOCAZIONE_TEMPORANEA
   , p_OLD_INSTALLAZIONE
   , p_OLD_VERSIONE
   , p_OLD_DISL_DIMENSIONAMENTI
   , p_OLD_NOTE
   , p_OLD_LINGUA
   , p_OLD_LINK_ORACLE
   , p_OLD_DATABASE_LINK
   , p_OLD_DATABASE_DRIVER
   , p_OLD_SERVIZIO
   , p_OLD_ISTANZA_AMMINISTRATORE
   , p_check_OLD);
END; -- ISTANZA.upd
--------------------------------------------------------------------------------
PROCEDURE upd_column
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_column         IN VARCHAR2
, p_value          IN VARCHAR2 DEFAULT NULL
, p_literal_value  IN NUMBER   DEFAULT 1
) IS
/******************************************************************************
 NOME:        upd_column
 DESCRIZIONE: Aggiornamento del campo p_column col valore p_value.
 PARAMETRI:   p_column:        identificatore del campo da aggiornare.
              p_value:         valore da modificare.
              p_literal_value: indica se il valore e un stringa e non un numero
                               o una funzione.
******************************************************************************/
BEGIN
   istanze_tpk.upd_column(p_ISTANZA, p_column, p_value, p_literal_value);
END; -- ISTANZA.upd_column
--------------------------------------------------------------------------------
PROCEDURE upd_column
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_column  IN VARCHAR2
, p_value   IN DATE
) IS
/******************************************************************************
 NOME:        upd_column
 DESCRIZIONE: Aggiornamento del campo p_column col valore p_value.
 NOTE:        Richiama se stessa con il parametro date convertito in stringa.
******************************************************************************/
BEGIN
   istanze_tpk.upd_column(p_ISTANZA, p_column, p_value);
END; -- ISTANZA.upd_column
--------------------------------------------------------------------------------
PROCEDURE del
   ( p_ISTANZA  IN ISTANZE.Istanza%TYPE
   , p_PROGETTO  IN ISTANZE.PROGETTO%TYPE DEFAULT NULL
   , p_ENTE  IN ISTANZE.ENTE%TYPE DEFAULT NULL
   , p_DESCRIZIONE  IN ISTANZE.DESCRIZIONE%TYPE DEFAULT NULL
   , p_DATABASE_USER IN ISTANZE.USER_ORACLE%TYPE DEFAULT NULL
   , p_DATABASE_PASSWORD IN ISTANZE.PASSWORD_ORACLE%TYPE  DEFAULT NULL
   , p_DISLOCAZIONE  IN ISTANZE.DISLOCAZIONE%TYPE DEFAULT NULL
   , p_DISLOCAZIONE_TEMPORANEA  IN ISTANZE.DISLOCAZIONE_TEMPORANEA%TYPE DEFAULT NULL
   , p_INSTALLAZIONE  IN ISTANZE.INSTALLAZIONE%TYPE DEFAULT NULL
   , p_VERSIONE  IN ISTANZE.VERSIONE%TYPE DEFAULT NULL
   , p_DISL_DIMENSIONAMENTI  IN ISTANZE.DISLOCAZIONE_DIMENSIONAMENTI%TYPE DEFAULT NULL
   , p_NOTE  IN ISTANZE.NOTE%TYPE  DEFAULT NULL
   , p_LINGUA  IN ISTANZE.LINGUA%TYPE DEFAULT NULL
   , p_LINK_ORACLE  IN ISTANZE.LINK_ORACLE%TYPE  DEFAULT NULL
   , p_DATABASE_LINK  IN ISTANZE.DATABASE_LINK%TYPE  DEFAULT NULL
   , p_DATABASE_DRIVER  IN ISTANZE.DATABASE_DRIVER%TYPE  DEFAULT NULL
   , p_SERVIZIO  IN ISTANZE.SERVIZIO%TYPE  DEFAULT NULL
   , p_ISTANZA_AMMINISTRATORE IN ISTANZE.ISTANZA_AMMINISTRATORE%TYPE  DEFAULT NULL
   , p_check_OLD  IN INTEGER DEFAULT 0
) IS
/******************************************************************************
 NOME:        del
 DESCRIZIONE: Cancellazione della riga indicata.
 PARAMETRI:   Chiavi e attributi della table.
              p_check_OLD: 0, ricerca senza controllo su attributi precedenti
                           1, ricerca con controllo anche su attributi precedenti.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
              Se p_check_old = 1, viene controllato se il record corrispondente a
              tutti i campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_row_found NUMBER;
BEGIN
   istanze_tpk.DEL(p_ISTANZA
   , p_PROGETTO
   , p_ENTE
   , p_DESCRIZIONE
   , p_DATABASE_USER
   , p_DATABASE_PASSWORD
   , p_DISLOCAZIONE
   , p_DISLOCAZIONE_TEMPORANEA
   , p_INSTALLAZIONE
   , p_VERSIONE
   , p_DISL_DIMENSIONAMENTI
   , p_NOTE
   , p_LINGUA
   , p_LINK_ORACLE
   , p_DATABASE_LINK
   , p_DATABASE_DRIVER
   , p_SERVIZIO
   , p_ISTANZA_AMMINISTRATORE
   , p_check_OLD);
END; -- ISTANZA.del
--------------------------------------------------------------------------------
FUNCTION get_descriptor
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN VARCHAR2 IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descriptor
 DESCRIZIONE: Descriptor di riga esistente identificata da chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     varchar2: null in caso di chiave nulla.
 NOTE:        -
******************************************************************************/
   d_result VARCHAR2(32000);
BEGIN
   IF p_ISTANZA IS NULL
   THEN
      d_result := NULL;
   ELSE
      SELECT 'Istanza: '||descrizione||' ('||Istanza||')'
        INTO d_result
        FROM ISTANZE
       WHERE Istanza = p_ISTANZA
      ;
   END IF;
   RETURN  d_result;
END; -- ISTANZA.get_descriptor
--------------------------------------------------------------------------------
FUNCTION get_PROGETTO
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.PROGETTO%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_PROGETTO
 DESCRIZIONE: Attributo PROGETTO di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.PROGETTO%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_PROGETTO(p_ISTANZA);
END; -- ISTANZA.get_PROGETTO
--------------------------------------------------------------------------------
FUNCTION get_ENTE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.ENTE%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_ENTE
 DESCRIZIONE: Attributo ENTE di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.ENTE%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.ENTE%TYPE;
BEGIN
   RETURN  istanze_tpk.GET_ENTE(p_ISTANZA);
END; -- ISTANZA.get_ENTE
--------------------------------------------------------------------------------
FUNCTION get_DESCRIZIONE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.DESCRIZIONE%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_DESCRIZIONE
 DESCRIZIONE: Attributo DESCRIZIONE di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.DESCRIZIONE%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_DESCRIZIONE(p_ISTANZA);
END; -- ISTANZA.get_DESCRIZIONE
--------------------------------------------------------------------------------
FUNCTION get_DATABASE_USER
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.USER_ORACLE%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_DATABASE_USER
 DESCRIZIONE: Attributo DATABASE_USER (USER_ORACLE) di riga esistente
              identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.USER_ORACLE%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_USER_ORACLE(p_ISTANZA);
END; -- ISTANZA.get_DATABASE_USER
--------------------------------------------------------------------------------
FUNCTION get_DATABASE_PASSWORD
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.PASSWORD_ORACLE%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_DATABASE_PASSWORD
 DESCRIZIONE: Attributo DATABASE_PASSWORD (PASSWORD_ORACLE) di riga esistente
              identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.PASSWORD_ORACLE%type.
 NOTE:        La riga identificata deve essere presente.
              La password restituita e' crittata!!!!
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_PASSWORD_ORACLE(p_ISTANZA);
END; -- ISTANZA.get_DATABASE_PASSWORD
--------------------------------------------------------------------------------
FUNCTION get_DISLOCAZIONE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.DISLOCAZIONE%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_DISLOCAZIONE
 DESCRIZIONE: Attributo DISLOCAZIONE di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.DISLOCAZIONE%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_DISLOCAZIONE(p_ISTANZA);
END; -- ISTANZA.get_DISLOCAZIONE
--------------------------------------------------------------------------------
FUNCTION get_DISLOCAZIONE_TEMPORANEA
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.DISLOCAZIONE_TEMPORANEA%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_DISLOCAZIONE_TEMPORANEA
 DESCRIZIONE: Attributo DISLOCAZIONE_TEMPORANEA di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.DISLOCAZIONE_TEMPORANEA%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_DISLOCAZIONE_TEMPORANEA(p_ISTANZA);
END; -- ISTANZA.get_DISLOCAZIONE_TEMPORANEA
--------------------------------------------------------------------------------
FUNCTION get_INSTALLAZIONE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.INSTALLAZIONE%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_INSTALLAZIONE
 DESCRIZIONE: Attributo INSTALLAZIONE di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.INSTALLAZIONE%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_INSTALLAZIONE(p_ISTANZA);
END; -- ISTANZA.get_INSTALLAZIONE
--------------------------------------------------------------------------------
FUNCTION get_VERSIONE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.VERSIONE%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_VERSIONE
 DESCRIZIONE: Attributo VERSIONE di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.VERSIONE%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_VERSIONE(p_ISTANZA);
END; -- ISTANZA.get_VERSIONE
--------------------------------------------------------------------------------
FUNCTION get_DISL_DIMENSIONAMENTI
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.DISLOCAZIONE_DIMENSIONAMENTI%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_DISLOCAZIONE_DIMENSIONAMENTI
 DESCRIZIONE: Attributo DISLOCAZIONE_DIMENSIONAMENTI di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.DISLOCAZIONE_DIMENSIONAMENTI%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_DISLOCAZIONE_DIMENSIONAMEN(p_ISTANZA);
END; -- ISTANZA.get_DISLOCAZIONE_DIMENSIONAMENTI
--------------------------------------------------------------------------------
FUNCTION get_NOTE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.NOTE%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_NOTE
 DESCRIZIONE: Attributo NOTE di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.NOTE%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_NOTE(p_ISTANZA);
END; -- ISTANZA.get_NOTE
--------------------------------------------------------------------------------
FUNCTION get_LINGUA
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.LINGUA%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_LINGUA
 DESCRIZIONE: Attributo LINGUA di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.LINGUA%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_LINGUA(p_ISTANZA);
END; -- ISTANZA.get_LINGUA
--------------------------------------------------------------------------------
FUNCTION get_LINK_ORACLE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.LINK_ORACLE%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_LINK_ORACLE
 DESCRIZIONE: Attributo LINK_ORACLE di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.LINK_ORACLE%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_LINK_ORACLE(p_ISTANZA);
END; -- ISTANZA.get_LINK_ORACLE
--------------------------------------------------------------------------------
FUNCTION get_DATABASE_LINK
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.DATABASE_LINK%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_DATABASE_LINK
 DESCRIZIONE: Attributo DATABASE_LINK di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.DATABASE_LINK%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_DATABASE_LINK(p_ISTANZA);
END; -- ISTANZA.get_DATABASE_LINK
--------------------------------------------------------------------------------
FUNCTION get_DATABASE_DRIVER
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.DATABASE_DRIVER%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_DATABASE_DRIVER
 DESCRIZIONE: Attributo DATABASE_DRIVER di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.DATABASE_DRIVER%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_DATABASE_DRIVER(p_ISTANZA);
END; -- ISTANZA.get_DATABASE_DRIVER
--------------------------------------------------------------------------------
FUNCTION get_SERVIZIO
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
) RETURN ISTANZE.SERVIZIO%TYPE IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_SERVIZIO
 DESCRIZIONE: Attributo SERVIZIO di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.SERVIZIO%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
BEGIN
   RETURN  istanze_tpk.GET_SERVIZIO(p_ISTANZA);
END; -- ISTANZA.get_SERVIZIO
--------------------------------------------------------------------------------
PROCEDURE set_PROGETTO
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.PROGETTO%TYPE
) IS
/******************************************************************************
 NOME:        set_PROGETTO
 DESCRIZIONE: Aggiornamento del campo PROGETTO col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   IF p_value IS NULL THEN
      RAISE_APPLICATION_ERROR(s_not_null_expected_number, s_not_null_expected_msg||' (ISTANZE.PROGETTO)');
   END IF;
   UPDATE ISTANZE
      SET PROGETTO = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_PROGETTO
--------------------------------------------------------------------------------
PROCEDURE set_ENTE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.ENTE%TYPE
) IS
/******************************************************************************
 NOME:        set_ENTE
 DESCRIZIONE: Aggiornamento del campo ENTE col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   IF p_value IS NULL THEN
      RAISE_APPLICATION_ERROR(s_not_null_expected_number, s_not_null_expected_msg||' (ISTANZE.ENTE)');
   END IF;
   UPDATE ISTANZE
      SET ENTE = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_ENTE
--------------------------------------------------------------------------------
PROCEDURE set_DESCRIZIONE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.DESCRIZIONE%TYPE
) IS
/******************************************************************************
 NOME:        set_DESCRIZIONE
 DESCRIZIONE: Aggiornamento del campo DESCRIZIONE col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   IF p_value IS NULL THEN
      RAISE_APPLICATION_ERROR(s_not_null_expected_number, s_not_null_expected_msg||' (ISTANZE.DESCRIZIONE)');
   END IF;
   UPDATE ISTANZE
      SET DESCRIZIONE = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_DESCRIZIONE
--------------------------------------------------------------------------------
PROCEDURE set_DATABASE_USER
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.USER_ORACLE%TYPE
) IS
/******************************************************************************
 NOME:        set_DATABASE_USER
 DESCRIZIONE: Aggiornamento del campo DATABASE_USER col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   IF p_value IS NULL THEN
      RAISE_APPLICATION_ERROR(s_not_null_expected_number, s_not_null_expected_msg||' (ISTANZE.USER_ORACLE)');
   END IF;
   UPDATE ISTANZE
      SET USER_ORACLE = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_DATABASE_USER
--------------------------------------------------------------------------------
PROCEDURE set_DATABASE_PASSWORD
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.PASSWORD_ORACLE%TYPE
, p_IS_PWD_CRYPTED NUMBER
) IS
/******************************************************************************
 NOME:        set_DATABASE_PASSWORD
 DESCRIZIONE: Aggiornamento del campo DATABASE_PASSWORD col valore p_value.
 PARAMETRI:   p_ISTANZA:        chiave.
              p_value:          password.
              p_IS_PWD_CRYPTED: indica se la password viene passata in chiaro(0)
                                o gia' crittata (1).
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 005  27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
                       (modificato quanto introdotto precedentemente con indicazione md5)
******************************************************************************/
   d_pwd_encrypted VARCHAR2(50);
BEGIN
   IF p_value IS NULL THEN
      RAISE_APPLICATION_ERROR(s_not_null_expected_number, s_not_null_expected_msg||' (ISTANZE.PASSWORD_ORACLE)');
   END IF;
   d_pwd_encrypted := p_value;
   IF p_IS_PWD_CRYPTED = 0 THEN
      BEGIN
         Crypt.CRYPT_PASSWORD(d_pwd_encrypted, 'STANDARD'); -- rev. 5
      EXCEPTION
         WHEN OTHERS THEN
            RAISE;
      END;
   END IF;
   UPDATE ISTANZE
      SET PASSWORD_ORACLE = d_pwd_encrypted
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_DATABASE_PASSWORD
--------------------------------------------------------------------------------
PROCEDURE set_DISLOCAZIONE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.DISLOCAZIONE%TYPE
) IS
/******************************************************************************
 NOME:        set_DISLOCAZIONE
 DESCRIZIONE: Aggiornamento del campo DISLOCAZIONE col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   IF p_value IS NULL THEN
      RAISE_APPLICATION_ERROR(s_not_null_expected_number, s_not_null_expected_msg||' (ISTANZE.DISLOCAZIONE)');
   END IF;
   UPDATE ISTANZE
      SET DISLOCAZIONE = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_DISLOCAZIONE
--------------------------------------------------------------------------------
PROCEDURE set_DISLOCAZIONE_TEMPORANEA
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.DISLOCAZIONE_TEMPORANEA%TYPE
) IS
/******************************************************************************
 NOME:        set_DISLOCAZIONE_TEMPORANEA
 DESCRIZIONE: Aggiornamento del campo DISLOCAZIONE_TEMPORANEA col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   UPDATE ISTANZE
      SET DISLOCAZIONE_TEMPORANEA = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_DISLOCAZIONE_TEMPORANEA
--------------------------------------------------------------------------------
PROCEDURE set_INSTALLAZIONE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.INSTALLAZIONE%TYPE
) IS
/******************************************************************************
 NOME:        set_INSTALLAZIONE
 DESCRIZIONE: Aggiornamento del campo INSTALLAZIONE col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   UPDATE ISTANZE
      SET INSTALLAZIONE = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_INSTALLAZIONE
--------------------------------------------------------------------------------
PROCEDURE set_VERSIONE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.VERSIONE%TYPE
) IS
/******************************************************************************
 NOME:        set_VERSIONE
 DESCRIZIONE: Aggiornamento del campo VERSIONE col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   UPDATE ISTANZE
      SET VERSIONE = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_VERSIONE
--------------------------------------------------------------------------------
PROCEDURE set_DISL_DIMENSIONAMENTI
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.DISLOCAZIONE_DIMENSIONAMENTI%TYPE
) IS
/******************************************************************************
 NOME:        set_DISL_DIMENSIONAMENTI
 DESCRIZIONE: Aggiornamento del campo DISLOCAZIONE_DIMENSIONAMENTI col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   UPDATE ISTANZE
      SET DISLOCAZIONE_DIMENSIONAMENTI = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_DISL_DIMENSIONAMENTI
--------------------------------------------------------------------------------
PROCEDURE set_NOTE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.NOTE%TYPE
) IS
/******************************************************************************
 NOME:        set_VERSIONE
 DESCRIZIONE: Aggiornamento del campo NOTE col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   UPDATE ISTANZE
      SET NOTE = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_NOTE
--------------------------------------------------------------------------------
PROCEDURE set_LINGUA
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.LINGUA%TYPE
) IS
/******************************************************************************
 NOME:        set_LINGUA
 DESCRIZIONE: Aggiornamento del campo LINGUA col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   UPDATE ISTANZE
      SET LINGUA = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_LINGUA
--------------------------------------------------------------------------------
PROCEDURE set_LINK_ORACLE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.LINK_ORACLE%TYPE
) IS
/******************************************************************************
 NOME:        set_LINK_ORACLE
 DESCRIZIONE: Aggiornamento del campo LINK_ORACLE col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   UPDATE ISTANZE
      SET LINK_ORACLE = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_LINK_ORACLE
--------------------------------------------------------------------------------
PROCEDURE set_link_oracle_like_ad4
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_check_exists_master in number default 1
) IS
/******************************************************************************
 NOME:        set_LINK_ORACLE_like_ad4
 DESCRIZIONE: Aggiornamento del campo LINK_ORACLE con lo stesso valore
              dell' istanza di ad4.
 PARAMETRI:   p_ISTANZA:       chiave.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
   d_esiste_master number := 1;
BEGIN
   if p_check_exists_master = 1 then
      begin
         select 1
           into d_esiste_master
           from istanze
          where instr('.'|| installazione ||'.', '.MASTER.') > 0
            and progetto = 'AD4'
         ;
      exception
         when others then
            d_esiste_master := 0;
      end;
   end if;
   if d_esiste_master = 1 then
      UPDATE ISTANZE
         SET LINK_ORACLE = istanze_tpk.GET_LINK_ORACLE(get_istanza)
      WHERE Istanza = p_ISTANZA
      ;
      IF SQL%ROWCOUNT < 1
      THEN
         RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                                 , Afc_Error.modified_by_other_user_msg
                                 );
      END IF;
   end if;
END; -- ISTANZA.set_LINK_ORACLE_like_ad4
--------------------------------------------------------------------------------
PROCEDURE set_DATABASE_LINK
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.DATABASE_LINK%TYPE
) IS
/******************************************************************************
 NOME:        set_DATABASE_LINK
 DESCRIZIONE: Aggiornamento del campo DATABASE_LINK col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   UPDATE ISTANZE
      SET DATABASE_LINK = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_DATABASE_LINK
--------------------------------------------------------------------------------
PROCEDURE set_DATABASE_DRIVER
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.DATABASE_DRIVER%TYPE
) IS
/******************************************************************************
 NOME:        set_DATABASE_DRIVER
 DESCRIZIONE: Aggiornamento del campo DATABASE_DRIVER col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   UPDATE ISTANZE
      SET DATABASE_DRIVER = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_DATABASE_DRIVER
--------------------------------------------------------------------------------
PROCEDURE set_SERVIZIO
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.SERVIZIO%TYPE
) IS
/******************************************************************************
 NOME:        set_SERVIZIO
 DESCRIZIONE: Aggiornamento del campo SERVIZIO col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   UPDATE ISTANZE
      SET SERVIZIO = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_SERVIZIO
--------------------------------------------------------------------------------
PROCEDURE set_ISTANZA_AMMINISTRATORE
( p_ISTANZA  IN ISTANZE.Istanza%TYPE
, p_value IN ISTANZE.ISTANZA_AMMINISTRATORE%TYPE
) IS
/******************************************************************************
 NOME:        set_ISTANZA_AMMINISTRATORE
 DESCRIZIONE: Aggiornamento del campo ISTANZA_AMMINISTRATORE col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
BEGIN
   UPDATE ISTANZE
      SET ISTANZA_AMMINISTRATORE = p_value
   WHERE Istanza = p_ISTANZA
   ;
   IF SQL%ROWCOUNT < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                              , Afc_Error.modified_by_other_user_msg
                              );
   END IF;
END; -- ISTANZA.set_ISTANZA_AMMINISTRATORE
PROCEDURE set_istanza (p_old_istanza IN istanze.istanza%TYPE
                     , p_new_istanza IN istanze.istanza%TYPE
)
IS
/******************************************************************************
 NOME:        set_ISTANZA
 DESCRIZIONE: Aggiornamento del campo ISTANZA col valore p_new_istanza.
               In cascata vengono aggiornate tutte le tabelle che possono
               contenere riferimenti alle istanze.
 PARAMETRI:   p_old_ISTANZA:       istanza da rinominare
              p_new_ISTANZA:       nuovo valore
 NOTE:        .
******************************************************************************/
BEGIN
   si4.sql_execute ('alter table istanze disable all triggers');
   si4.sql_execute ('alter table diritti_accesso disable all triggers');
   si4.sql_execute('alter table caratteristiche_accesso disable all triggers');
   si4.sql_execute('alter table caratteristiche_accesso disable constraint caac_diac_fk');
   si4.sql_execute ('alter table SERVIZI disable constraint ISTA_SERV_FK');
   -- disabilito e agisco solo sul master, queste informazioni non ci sono sugli slave
   UPDATE istanze
   SET istanza    = p_new_istanza
   WHERE istanza = p_old_istanza;
   -- se istanza non ad4 sposto tutti i riferimenti sulla
   -- nuova istanza
   UPDATE caratteristiche_accesso
   SET istanza    = p_new_istanza
   WHERE istanza = p_old_istanza;
   UPDATE diritti_accesso
   SET istanza    = p_new_istanza
   WHERE istanza = p_old_istanza;
   UPDATE eventi
   SET istanza    = p_new_istanza
   WHERE istanza = p_old_istanza;
   UPDATE richieste_abilitazione
   SET istanza    = p_new_istanza
   WHERE istanza = p_old_istanza;
   UPDATE servizi
   SET istanza    = p_new_istanza
   WHERE istanza = p_old_istanza;
   -- per ogni utente sistemo le tavole
   FOR v_utente IN (SELECT user_oracle
      FROM istanze i
      WHERE link_oracle IS NULL
            OR (link_oracle =
                   (SELECT link_oracle
                    FROM istanze i_mod
                    WHERE istanza = p_new_istanza
                          AND EXISTS (SELECT 'x'
                                      FROM istanze i_mas
                                      WHERE i_mod.link_oracle = i_mas.link_oracle
                                            AND INSTR ('.' || installazione || '.'
                                                     , '.MASTER.'
                                               ) > 0))))
   LOOP
      DECLARE
         v_trovato   NUMBER;
      BEGIN
         SELECT 1
         INTO v_trovato
         FROM all_tables
         WHERE     table_name = 'DBFW_APPLICAZIONI'
               AND owner = v_utente.user_oracle
               AND table_name NOT IN (SELECT table_name
                                      FROM all_snapshots
                                      WHERE owner = v_utente.user_oracle);
            si4.sql_execute( 'begin ' || v_utente.user_oracle||  '.afc.sql_execute('''||
            'UPDATE '
            || 'DBFW_APPLICAZIONI set istanza = '''''
            || p_new_istanza
            || ''''''
            || ' WHERE istanza = '''''
            || p_old_istanza
            || '''''''); end;');
         COMMIT;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            NULL;
      END;
   END LOOP;
   declare
     v_db_link varchar2(1000);
   begin
      FOR v_utente IN (-- su un altro server a causa di master/slave
         SELECT user_oracle, dbl.db_link
         FROM istanze i, all_db_links dbl
         WHERE UPPER (dbl.host(+)) = UPPER (i.link_oracle)
               AND i.istanza = p_new_istanza
               AND i.link_oracle =
                     (SELECT link_oracle
                      FROM istanze i_mod
                      WHERE istanza = p_new_istanza
                            AND EXISTS (SELECT 'x'
                                        FROM istanze i_mas
                                        WHERE i_mod.link_oracle = i_mas.link_oracle
                                              AND (INSTR ('.' || installazione || '.'
                                                        , '.SLAVEGEO.'
                                                   ) > 0
                                                   OR INSTR ('.' || installazione || '.'
                                                           , '.SLAVEGEOU.'
                                                     ) > 0)))
         )
      LOOP
      if v_utente.db_link is null then
         v_db_link := null;
      else
         v_db_link :='@'||v_utente.db_link;
      end if;
      si4.sql_execute(   'begin for v_upd in (select 1 from all_tables'
                      || v_db_link
                      || ' WHERE     table_name = ''DBFW_APPLICAZIONI'''
                      || '   AND owner = '''
                      || v_utente.user_oracle
                      || ''''
                      || '   AND table_name NOT IN (SELECT table_name FROM all_snapshots WHERE owner = '''
                      || v_utente.user_oracle
                      || '''))LOOP '
                      || v_utente.user_oracle
                      || '.afc.sql_execute'||v_db_link ||' ('''
                      || 'UPDATE '
                      || 'DBFW_APPLICAZIONI set istanza = '''''
                      || p_new_istanza
                      || ''''''
                      || ' WHERE istanza = '''''
                      || p_old_istanza
                      || '''''''); end loop; end;');
      COMMIT;
      END LOOP;
   end;
   si4.sql_execute ('alter table istanze enable all triggers');
   si4.sql_execute ('alter table diritti_accesso enable all triggers');
   si4.sql_execute ('alter table caratteristiche_accesso enable all triggers'
   );
   si4.sql_execute('alter table caratteristiche_accesso enable constraint caac_diac_fk');
   si4.sql_execute ('alter table SERVIZI ENABLE constraint ISTA_SERV_FK');
END set_istanza;
--------------------------------------------------------------------------------
-- < Metodi getter: espandere template "getter" >
--------------------------------------------------------------------------------
FUNCTION get_rows
   ( p_ISTANZA  IN VARCHAR2 DEFAULT NULL
   , p_PROGETTO  IN VARCHAR2 DEFAULT NULL
   , p_ENTE  IN VARCHAR2 DEFAULT NULL
   , p_DESCRIZIONE  IN VARCHAR2 DEFAULT NULL
   , p_DATABASE_USER  IN VARCHAR2 DEFAULT NULL
   , p_DATABASE_PASSWORD  IN VARCHAR2 DEFAULT NULL
   , p_DISLOCAZIONE  IN VARCHAR2 DEFAULT NULL
   , p_DISLOCAZIONE_TEMPORANEA  IN VARCHAR2 DEFAULT NULL
   , p_INSTALLAZIONE  IN VARCHAR2 DEFAULT NULL
   , p_VERSIONE  IN VARCHAR2 DEFAULT NULL
   , p_DISLOCAZIONE_DIMENSIONAMENTI  IN VARCHAR2 DEFAULT NULL
   , p_NOTE  IN VARCHAR2 DEFAULT NULL
   , p_LINGUA  IN VARCHAR2 DEFAULT NULL
   , p_LINK_ORACLE  IN VARCHAR2 DEFAULT NULL
   , p_DATABASE_LINK  IN VARCHAR2 DEFAULT NULL
   , p_DATABASE_DRIVER  IN VARCHAR2 DEFAULT NULL
   , p_SERVIZIO  IN VARCHAR2 DEFAULT NULL
   , p_ISTANZA_AMMINISTRATORE IN VARCHAR2 DEFAULT NULL
   , p_other_condition IN VARCHAR2 DEFAULT NULL
   , p_QBE IN NUMBER DEFAULT 0
) RETURN Afc.t_ref_cursor IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_rows
 DESCRIZIONE: Ritorna il risultato di una query in base ai valori che passiamo.
 PARAMETRI:   Chiavi e attributi della table
              p_other_condition
              p_QBE 0: se l'operatore da utilizzare nella where-condition e
                       quello di default ('=')
                    1: se l'operatore da utilizzare nella where-condition e
                       quello specificato per ogni attributo.
 RITORNA:     Un ref_cursor che punta al risultato della query.
 NOTE:        Se p_QBE = 1 , ogni parametro deve contenere, nella prima parte,
              l'operatore da utilizzare nella where-condition.
******************************************************************************/
BEGIN
   RETURN    istanze_tpk.get_rows
                     ( p_ISTANZA => p_ISTANZA
                     , p_PROGETTO => p_PROGETTO
                     , p_ENTE => p_ENTE
                     , p_DESCRIZIONE => p_DESCRIZIONE
                     , p_USER_ORACLE => p_DATABASE_USER
                     , p_PASSWORD_ORACLE => p_DATABASE_PASSWORD
                     , p_DISLOCAZIONE => p_DISLOCAZIONE
                     , p_DISLOCAZIONE_TEMPORANEA => p_DISLOCAZIONE_TEMPORANEA
                     , p_INSTALLAZIONE => p_INSTALLAZIONE
                     , p_VERSIONE => p_VERSIONE
                     , p_DISLOCAZIONE_DIMENSIONAMENTI => p_DISLOCAZIONE_DIMENSIONAMENTI
                     , p_NOTE => p_NOTE
                     , p_LINGUA => p_LINGUA
                     , p_LINK_ORACLE => p_LINK_ORACLE
                     , p_DATABASE_LINK => p_DATABASE_LINK
                     , p_DATABASE_DRIVER => p_DATABASE_DRIVER
                     , p_SERVIZIO => p_SERVIZIO
                     , p_ISTANZA_AMMINISTRATORE => p_ISTANZA_AMMINISTRATORE
                     , p_other_condition => p_other_condition
                     , p_QBE => p_QBE);
END; -- ISTANZA.get_rows
--------------------------------------------------------------------------------
FUNCTION count_rows
( p_ISTANZA  IN VARCHAR2 DEFAULT NULL
, p_PROGETTO  IN VARCHAR2 DEFAULT NULL
, p_ENTE  IN VARCHAR2 DEFAULT NULL
, p_DESCRIZIONE  IN VARCHAR2 DEFAULT NULL
, p_DATABASE_USER  IN VARCHAR2 DEFAULT NULL
, p_DATABASE_PASSWORD  IN VARCHAR2 DEFAULT NULL
, p_DISLOCAZIONE  IN VARCHAR2 DEFAULT NULL
, p_DISLOCAZIONE_TEMPORANEA  IN VARCHAR2 DEFAULT NULL
, p_INSTALLAZIONE  IN VARCHAR2 DEFAULT NULL
, p_VERSIONE  IN VARCHAR2 DEFAULT NULL
, p_DISLOCAZIONE_DIMENSIONAMENTI  IN VARCHAR2 DEFAULT NULL
, p_NOTE  IN VARCHAR2 DEFAULT NULL
, p_LINGUA  IN VARCHAR2 DEFAULT NULL
, p_LINK_ORACLE  IN VARCHAR2 DEFAULT NULL
, p_DATABASE_LINK  IN VARCHAR2 DEFAULT NULL
, p_DATABASE_DRIVER  IN VARCHAR2 DEFAULT NULL
, p_SERVIZIO  IN VARCHAR2 DEFAULT NULL
, p_ISTANZA_AMMINISTRATORE IN VARCHAR2 DEFAULT NULL
, p_other_condition IN VARCHAR2 DEFAULT NULL
, p_QBE IN NUMBER DEFAULT 0
) RETURN INTEGER IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        count_rows
 DESCRIZIONE: Ritorna il numero di righe della tabella gli attributi delle quali
              rispettano i valori indicati.
 PARAMETRI:   Almeno uno dei parametri della tabella.
              p_other_condition
              p_QBE
 RITORNA:     Numero di righe che rispettano la selezione indicata.
******************************************************************************/
BEGIN
   RETURN    istanze_tpk.count_rows
                     ( p_ISTANZA => p_ISTANZA
                     , p_PROGETTO => p_PROGETTO
                     , p_ENTE => p_ENTE
                     , p_DESCRIZIONE => p_DESCRIZIONE
                     , p_USER_ORACLE => p_DATABASE_USER
                     , p_PASSWORD_ORACLE => p_DATABASE_PASSWORD
                     , p_DISLOCAZIONE => p_DISLOCAZIONE
                     , p_DISLOCAZIONE_TEMPORANEA => p_DISLOCAZIONE_TEMPORANEA
                     , p_INSTALLAZIONE => p_INSTALLAZIONE
                     , p_VERSIONE => p_VERSIONE
                     , p_DISLOCAZIONE_DIMENSIONAMENTI => p_DISLOCAZIONE_DIMENSIONAMENTI
                     , p_NOTE => p_NOTE
                     , p_LINGUA => p_LINGUA
                     , p_LINK_ORACLE => p_LINK_ORACLE
                     , p_DATABASE_LINK => p_DATABASE_LINK
                     , p_DATABASE_DRIVER => p_DATABASE_DRIVER
                     , p_SERVIZIO => p_SERVIZIO
                     , p_ISTANZA_AMMINISTRATORE => p_ISTANZA_AMMINISTRATORE
                     , p_other_condition => p_other_condition
                     , p_QBE => p_QBE);
END; -- ISTANZA.count_rows
--------------------------------------------------------------------------------
FUNCTION IS_ISTANZA_MASTER (p_istanza varchar2)
return number
is /* SLAVE_COPY */
   d_istanza istanze.ISTANZA%type:= nvl(p_istanza, si4.istanza);
   d_return  number;
begin
   select 1
     into d_return
     from istanze
    where istanza = d_istanza
      and instr('.'||installazione||'.', '.MASTER.') > 0
   ;
   return d_return;
exception
   when others then
      return 0;
end;
FUNCTION GET_ISTANZA_MASTER
(p_progetto in varchar2)
return varchar2
is /* SLAVE_COPY */
   d_istanza istanze.ISTANZA%type;
begin
   select istanza
     into d_istanza
     from istanze
    where instr('.'||installazione||'.', '.MASTER.') > 0
      and progetto = p_progetto
   ;
   return d_istanza;
exception
   when others then
      return '';
end;
BEGIN
   -- inserimento degli errori nella tabella
   s_error_table( s_not_null_expected_number ) := s_not_null_expected_msg;
END Istanza;
/
