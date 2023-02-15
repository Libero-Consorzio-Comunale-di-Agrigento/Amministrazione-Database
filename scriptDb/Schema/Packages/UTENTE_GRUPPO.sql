CREATE OR REPLACE PACKAGE Utente_Gruppo IS /* MASTER_LINK */
/******************************************************************************
 NOME:        utente_gruppo
 DESCRIZIONE: Gestione tabella utenti_gruppo.
 ANNOTAZIONI: .
 REVISIONI:   .
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    03/11/2006  MMALFERRARI  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione CONSTANT Afc.t_revision := 'V1.00';
   s_table_name CONSTANT Afc.t_object_name := 'utenti_gruppo';
   SUBTYPE t_rowtype IS UTENTI_GRUPPO%ROWTYPE;
   -- Tipo del record primary key
   TYPE t_PK IS RECORD
   ( Utente  UTENTI_GRUPPO.Utente%TYPE
   , Gruppo  UTENTI_GRUPPO.Gruppo%TYPE
   );
   -- Exceptions
   not_null_expected EXCEPTION;
   PRAGMA EXCEPTION_INIT( not_null_expected, -20901 );
   s_not_null_expected_number CONSTANT Afc_Error.t_error_number := -20901;
   s_not_null_expected_msg    CONSTANT Afc_Error.t_error_msg := 'Il valore passato non puo'' essere nullo.';
   -- Versione e revisione
   FUNCTION versione /* SLAVE_COPY */
   RETURN VARCHAR2;
   PRAGMA RESTRICT_REFERENCES( versione, WNDS );
   -- Costruttore di record chiave
   FUNCTION PK /* SLAVE_COPY */
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   ) RETURN t_PK;
   PRAGMA RESTRICT_REFERENCES( PK, WNDS );
   -- Controllo integrita chiave
   FUNCTION can_handle /* SLAVE_COPY */
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   ) RETURN NUMBER;
   PRAGMA RESTRICT_REFERENCES( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   FUNCTION canHandle /* SLAVE_COPY */
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   ) RETURN BOOLEAN;
   PRAGMA RESTRICT_REFERENCES( canHandle, WNDS );
   -- Esistenza riga con chiave indicata
   FUNCTION exists_id /* SLAVE_COPY */
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   ) RETURN NUMBER;
   PRAGMA RESTRICT_REFERENCES( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   FUNCTION existsId /* SLAVE_COPY */
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   ) RETURN BOOLEAN;
   PRAGMA RESTRICT_REFERENCES( existsId, WNDS );
   -- Messaggio previsto per il numero di eccezione indicato
   FUNCTION error_message /* SLAVE_COPY */
   ( p_error_number  IN Afc_Error.t_error_number
   ) RETURN Afc_Error.t_error_msg;
   PRAGMA RESTRICT_REFERENCES( error_message, WNDS );
   -- Inserimento di una riga
   PROCEDURE ins
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   );
   -- Aggiornamento di una riga
   PROCEDURE upd
   ( p_NEW_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_NEW_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   , p_OLD_utente  IN UTENTI_GRUPPO.Utente%TYPE DEFAULT NULL
   , p_OLD_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE DEFAULT NULL
   , p_check_OLD  IN INTEGER DEFAULT 0
   );
   -- Cancellazione di una riga
   PROCEDURE del
   ( p_utente  IN UTENTI_GRUPPO.Utente%TYPE
   , p_gruppo  IN UTENTI_GRUPPO.Gruppo%TYPE
   , p_check_OLD  IN INTEGER DEFAULT 0
   );
   -- righe corrispondenti alla selezione indicata
   FUNCTION get_rows /* SLAVE_COPY */
   ( p_utente  IN VARCHAR2 DEFAULT NULL
   , p_gruppo  IN VARCHAR2 DEFAULT NULL
   , p_other_condition IN VARCHAR2 DEFAULT NULL
   , p_QBE IN NUMBER DEFAULT 0
   ) RETURN Afc.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   FUNCTION count_rows /* SLAVE_COPY */
   ( p_utente  IN VARCHAR2 DEFAULT NULL
   , p_gruppo  IN VARCHAR2 DEFAULT NULL
   , p_other_condition IN VARCHAR2 DEFAULT NULL
   , p_QBE IN NUMBER DEFAULT 0
   ) RETURN INTEGER;
   -- Verifica se qualcuno dei padri del gruppo 'p_padre' appartiene
   -- gia' al gruppo 'p_figlio'.
   FUNCTION check_ricorsione /* SLAVE_COPY */
   ( p_padre   IN  UTENTI_GRUPPO.Gruppo%TYPE
   , p_figlio  IN  UTENTI_GRUPPO.Utente%TYPE
   ) RETURN NUMBER;
END Utente_Gruppo;
/

