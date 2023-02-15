CREATE OR REPLACE PACKAGE BODY Utente_Gruppo IS
/******************************************************************************
 NOME:        utente_gruppo
 DESCRIZIONE: Gestione tabella utenti_gruppo.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   03/11/2006  MMALFERRARI  Prima emissione.
******************************************************************************/
   s_revisione_body      CONSTANT Afc.t_revision := '000';
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
   END; -- utente_gruppo.versione
   --------------------------------------------------------------------------------
   FUNCTION PK
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   ) RETURN t_PK IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        PK
    DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
   ******************************************************************************/
      d_result t_PK;
   BEGIN
      d_result.Utente := p_utente;
      d_result.Gruppo := p_gruppo;
      Dbc.PRE ( NOT Dbc.PreOn OR canHandle ( d_result.Utente
                                           , d_result.Gruppo
                                           )
              , 'canHandle on utente_gruppo.PK'
              );
      RETURN  d_result;
   END; -- end utente_gruppo.PK
   --------------------------------------------------------------------------------
   FUNCTION can_handle
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
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
      d_result := 1;
      -- nelle chiavi primarie composte da piu attributi, ciascun attributo deve essere not null
      IF  d_result = 1
      AND (  p_utente IS NULL
          OR p_gruppo IS NULL
          )
      THEN
         d_result := 0;
      END IF;
      Dbc.POST ( d_result = 1  OR  d_result = 0
               , 'd_result = 1  or  d_result = 0 on utente_gruppo.can_handle'
               );
      RETURN  d_result;
   END; -- utente_gruppo.can_handle
   --------------------------------------------------------------------------------
   FUNCTION canHandle
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   ) RETURN BOOLEAN IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        canHandle
    DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
    PARAMETRI:   Attributi chiave.
    RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
    NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
   ******************************************************************************/
      d_result CONSTANT BOOLEAN := Afc.to_boolean ( can_handle ( p_utente
                                                               , p_gruppo
                                                               )
                                                  );
   BEGIN
      RETURN  d_result;
   END; -- utente_gruppo.canHandle
   --------------------------------------------------------------------------------
   FUNCTION exists_id
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
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
      Dbc.PRE ( NOT Dbc.PreOn OR canHandle ( p_utente
                                           , p_gruppo
                                           )
              , 'canHandle on utente_gruppo.exists_id'
              );
      BEGIN
         SELECT 1
         INTO   d_result
         FROM   UTENTI_GRUPPO
         WHERE  Utente = p_utente
         AND    Gruppo = p_gruppo
         ;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            d_result := 0;
      END;
      Dbc.POST ( d_result = 1  OR  d_result = 0
               , 'd_result = 1  or  d_result = 0 on utente_gruppo.exists_id'
               );
      RETURN  d_result;
   END; -- utente_gruppo.exists_id
   --------------------------------------------------------------------------------
   FUNCTION existsId
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   ) RETURN BOOLEAN IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        existsId
    DESCRIZIONE: Esistenza riga con chiave indicata.
    NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
   ******************************************************************************/
      d_result CONSTANT BOOLEAN := Afc.to_boolean ( exists_id ( p_utente
                                                              , p_gruppo
                                                              )
                                                  );
   BEGIN
      RETURN  d_result;
   END; -- utente_gruppo.existsId
   --------------------------------------------------------------------------------
   FUNCTION error_message
   ( p_error_number  IN Afc_Error.t_error_number
   ) RETURN Afc_Error.t_error_msg IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        error_message
    DESCRIZIONE: Messaggio previsto per il numero di eccezione indicato.
    NOTE:        Restituisce il messaggio abbinato al numero indicato nella tabella
                 s_error_table del Package.
   ******************************************************************************/
      d_result CONSTANT Afc_Error.t_error_msg := s_error_table( p_error_number );
   BEGIN
      RETURN  d_result;
   END; -- utente_gruppo.error_message
   --------------------------------------------------------------------------------
   PROCEDURE ins
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   ) IS
   /******************************************************************************
    NOME:        ins
    DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
    PARAMETRI:   Chiavi e attributi della table.
   ******************************************************************************/
   BEGIN
     -- Check Mandatory on Insert
      Dbc.PRE (  NOT Dbc.PreOn
              OR (   p_utente IS NULL AND /*default value*/ '' IS NOT NULL ) -- PK nullable on insert
              OR (   p_gruppo IS NULL AND /*default value*/ '' IS NOT NULL )
              OR NOT existsId ( p_utente
                              , p_gruppo
                              )
              , 'not existsId on utente_gruppo.ins'
              );
      INSERT INTO UTENTI_GRUPPO
      ( Utente
      , Gruppo
      )
      VALUES
      ( p_utente
      , p_gruppo
      );
   END; -- utente_gruppo.ins
   --------------------------------------------------------------------------------
   PROCEDURE upd
   ( p_NEW_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_NEW_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   , p_OLD_utente  IN UTENTI_GRUPPO.Utente%TYPE DEFAULT NULL
   , p_OLD_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE DEFAULT NULL
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
      d_key t_PK;
      d_row_found NUMBER;
   BEGIN
      Dbc.PRE (  NOT Dbc.PreOn
              , ' <OLD values> is not null on utente_gruppo.upd'
              );
      Dbc.PRE ( NOT Dbc.PreOn OR p_check_OLD IS NOT NULL
              , 'p_check_OLD is not null on utente_gruppo.upd'
              );
      d_key := PK ( NVL( p_OLD_utente, p_NEW_utente )
                  , NVL( p_OLD_gruppo, p_NEW_gruppo )
                  );
      Dbc.PRE ( NOT Dbc.PreOn OR existsId ( d_key.Utente
                                          , d_key.Gruppo
                                          )
              , 'existsId on utente_gruppo.upd'
              );
      UPDATE UTENTI_GRUPPO
         SET Utente = p_NEW_utente
           , Gruppo = p_NEW_gruppo
      WHERE Utente = d_key.Utente
        AND Gruppo = d_key.Gruppo
      ;
      d_row_found := SQL%ROWCOUNT;
      Dbc.ASSERTION ( NOT Dbc.AssertionOn OR d_row_found <= 1
                    , 'd_row_found <= 1 on utente_gruppo.upd'
                    );
      IF d_row_found < 1
      THEN
         RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                                 , Afc_Error.modified_by_other_user_msg
                                 );
      END IF;
   END; -- utente_gruppo.upd
   --------------------------------------------------------------------------------
   PROCEDURE del
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   , p_check_old  IN INTEGER DEFAULT 0
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
      Dbc.PRE (  NOT Dbc.PreOn
              , ' <OLD values> is not null on utente_gruppo.del'
              );
      Dbc.PRE ( NOT Dbc.PreOn OR existsId ( p_utente
                                          , p_gruppo
                                          )
              , 'existsId on utente_gruppo.del'
              );
      DELETE FROM UTENTI_GRUPPO
      WHERE Utente = p_utente
        AND Gruppo = p_gruppo
      ;
      d_row_found := SQL%ROWCOUNT;
      Dbc.ASSERTION ( NOT Dbc.AssertionOn OR d_row_found <= 1
                    , 'd_row_found <= 1 on utente_gruppo.del'
                    );
      IF d_row_found < 1
      THEN
         RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number, Afc_Error.modified_by_other_user_msg );
      END IF;
      Dbc.POST ( NOT Dbc.PostOn OR NOT existsId ( p_utente
                                                , p_gruppo
                                                )
               , 'existsId on utente_gruppo.del'
               );
   END; -- utente_gruppo.del
   --------------------------------------------------------------------------------
   FUNCTION where_condition
   ( p_utente  IN VARCHAR2 DEFAULT NULL
   , p_gruppo  IN VARCHAR2 DEFAULT NULL
   , p_other_condition IN VARCHAR2 DEFAULT NULL
   , p_QBE  IN NUMBER DEFAULT 0
   ) RETURN Afc.t_statement IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        where_condition
    DESCRIZIONE: Ritorna la where_condition per lo statement di select di get_rows e count_rows.
    PARAMETRI:   Chiavi e attributi della table
                 p_other_condition
                 p_QBE 0: se l'operatore da utilizzare nella where-condition e
                          quello di default ('=')
                       1: se l'operatore da utilizzare nella where-condition e
                          quello specificato per ogni attributo.
    RITORNA:     AFC.t_statement.
    NOTE:        Se p_QBE = 1 , ogni parametro deve contenere, nella prima parte,
                 l'operatore da utilizzare nella where-condition.
   ******************************************************************************/
      d_statement Afc.t_statement;
   BEGIN
      d_statement := ' where ( 1 = 1 '
                  || Afc.get_field_condition( ' and ( utente ', p_utente, ' )', p_QBE )
                  || Afc.get_field_condition( ' and ( gruppo ', p_gruppo , ' )', p_QBE )
                  || ' ) ' || p_other_condition
                  ;
      RETURN d_statement;
   END; --- utente_gruppo.where_condition
   --------------------------------------------------------------------------------
   FUNCTION get_rows
   ( p_utente  IN VARCHAR2 DEFAULT NULL
   , p_gruppo  IN VARCHAR2 DEFAULT NULL
   , p_other_condition IN VARCHAR2 DEFAULT NULL
   , p_QBE  IN NUMBER DEFAULT 0
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
      d_statement       Afc.t_statement;
      d_ref_cursor      Afc.t_ref_cursor;
   BEGIN
      Dbc.PRE ( NOT Dbc.PreOn OR p_QBE IN ( 0, 1 )
              , 'check p_QBE on utente_gruppo.get_rows'
              );
      d_statement := ' select * from utenti_gruppo '
                  || where_condition( p_utente
                                    , p_gruppo
                                    , p_other_condition
                                    , p_QBE
                                    );
      d_ref_cursor := Afc_Dml.get_ref_cursor( d_statement );
      RETURN d_ref_cursor;
   END; -- utente_gruppo.get_rows
   --------------------------------------------------------------------------------
   FUNCTION count_rows
   ( p_utente  IN VARCHAR2 DEFAULT NULL
   , p_gruppo  IN VARCHAR2 DEFAULT NULL
   , p_other_condition IN VARCHAR2 DEFAULT NULL
   , p_QBE  IN NUMBER DEFAULT 0
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
      d_result          INTEGER;
      d_statement       Afc.t_statement;
   BEGIN
      Dbc.PRE ( NOT Dbc.PreOn OR p_QBE IN ( 0, 1 )
              , 'check p_QBE on utente_gruppo.count_rows'
              );
      d_statement := ' select count( * ) from utenti_gruppo '
                  || where_condition( p_utente
                                    , p_gruppo
                                    , p_other_condition
                                    , p_QBE
                                    );
      d_result := Afc.SQL_execute( d_statement );
      RETURN d_result;
   END; -- utente_gruppo.count_rows
   --------------------------------------------------------------------------------
   FUNCTION check_ricorsione
   ( p_padre   IN  UTENTI_GRUPPO.Gruppo%TYPE
   , p_figlio  IN  UTENTI_GRUPPO.Utente%TYPE)
   RETURN NUMBER
   IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        check_ricorsione
 DESCRIZIONE: Verifica se qualcuno dei padri del gruppo 'p_padre' appartiene
              al gruppo 'p_figlio'.
 PARAMETRI:   p_padre  IN  codice gruppo padre.
              p_figlio IN  codice gruppo figlio.
 RITORNA:     number:  1: appartiene;
                       0: non appartiene;
                      -1: errore.
******************************************************************************/
      d_check NUMBER;
      d_is_gruppo BOOLEAN;
   BEGIN
      d_is_gruppo := Gruppo.GET_TIPO(p_padre) IS NOT NULL;
      IF NOT d_is_gruppo THEN
         d_check := -1;
      ELSE
         d_is_gruppo := Gruppo.GET_TIPO(p_figlio) IS NOT NULL;
         IF d_is_gruppo THEN
            SELECT COUNT(1)
              INTO d_check
              FROM UTENTI_GRUPPO
             WHERE Gruppo = p_figlio
           CONNECT BY PRIOR Gruppo = Utente
             START WITH Utente = p_padre
            ;
         ELSE
            d_check := 0;
         END IF;
      END IF;
      RETURN d_check;
   EXCEPTION
      WHEN OTHERS THEN
         RETURN -1;
   END check_ricorsione;
      --------------------------------------------------------------------------------
BEGIN
   -- inserimento degli errori nella tabella
   s_error_table( s_not_null_expected_number ) := s_not_null_expected_msg;
END Utente_Gruppo;
/

