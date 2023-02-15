CREATE OR REPLACE PACKAGE accessi_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        accessi_tpk
 DESCRIZIONE: Gestione tabella ACCESSI.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    11/05/2009  mmalferrari  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'ACCESSI';
   subtype t_rowtype is ACCESSI%rowtype;
   -- Tipo del record primary key
   subtype t_acce_id  is ACCESSI.acce_id%type;
   type t_PK is record
   (
    acce_id  t_acce_id
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_acce_id  in ACCESSI.acce_id%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_acce_id  in ACCESSI.acce_id%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_acce_id  in ACCESSI.acce_id%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_acce_id  in ACCESSI.acce_id%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_acce_id  in ACCESSI.acce_id%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_acce_id  in ACCESSI.acce_id%type default null
   , p_session_id  in ACCESSI.session_id%type default null
   , p_data  in ACCESSI.data%type default null
   , p_log  in ACCESSI.log%type default null
   , p_utente  in ACCESSI.utente%type default null
   , p_istanza  in ACCESSI.istanza%type default null
   , p_modulo  in ACCESSI.modulo%type default null
   , p_db_user  in ACCESSI.db_user%type default null
   , p_terminale  in ACCESSI.terminale%type default null
   , p_note  in ACCESSI.note%type default null
   , p_id_credenziale  in ACCESSI.id_credenziale%type default null
   , p_tipo_autenticazione  in ACCESSI.tipo_autenticazione%type default null
   , p_stato  in ACCESSI.stato%type default null
   );
   function ins  /*+ SOA  */
   (
     p_acce_id  in ACCESSI.acce_id%type default null
   , p_session_id  in ACCESSI.session_id%type default null
   , p_data  in ACCESSI.data%type default null
   , p_log  in ACCESSI.log%type default null
   , p_utente  in ACCESSI.utente%type default null
   , p_istanza  in ACCESSI.istanza%type default null
   , p_modulo  in ACCESSI.modulo%type default null
   , p_db_user  in ACCESSI.db_user%type default null
   , p_terminale  in ACCESSI.terminale%type default null
   , p_note  in ACCESSI.note%type default null
   , p_id_credenziale  in ACCESSI.id_credenziale%type default null
   , p_tipo_autenticazione  in ACCESSI.tipo_autenticazione%type default null
   , p_stato  in ACCESSI.stato%type default null
   ) return integer;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_acce_id  in ACCESSI.acce_id%type
   , p_OLD_acce_id  in ACCESSI.acce_id%type default null
   , p_NEW_session_id  in ACCESSI.session_id%type default null
   , p_OLD_session_id  in ACCESSI.session_id%type default null
   , p_NEW_data  in ACCESSI.data%type default null
   , p_OLD_data  in ACCESSI.data%type default null
   , p_NEW_log  in ACCESSI.log%type default null
   , p_OLD_log  in ACCESSI.log%type default null
   , p_NEW_utente  in ACCESSI.utente%type default null
   , p_OLD_utente  in ACCESSI.utente%type default null
   , p_NEW_istanza  in ACCESSI.istanza%type default null
   , p_OLD_istanza  in ACCESSI.istanza%type default null
   , p_NEW_modulo  in ACCESSI.modulo%type default null
   , p_OLD_modulo  in ACCESSI.modulo%type default null
   , p_NEW_db_user  in ACCESSI.db_user%type default null
   , p_OLD_db_user  in ACCESSI.db_user%type default null
   , p_NEW_terminale  in ACCESSI.terminale%type default null
   , p_OLD_terminale  in ACCESSI.terminale%type default null
   , p_NEW_note  in ACCESSI.note%type default null
   , p_OLD_note  in ACCESSI.note%type default null
   , p_NEW_id_credenziale  in ACCESSI.id_credenziale%type default null
   , p_OLD_id_credenziale  in ACCESSI.id_credenziale%type default null
   , p_NEW_tipo_autenticazione  in ACCESSI.tipo_autenticazione%type default null
   , p_OLD_tipo_autenticazione  in ACCESSI.tipo_autenticazione%type default null
   , p_NEW_stato  in ACCESSI.stato%type default null
   , p_OLD_stato  in ACCESSI.stato%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   procedure upd_column
   (
p_acce_id  in ACCESSI.acce_id%type
   , p_column in varchar2
   , p_value  in date
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_acce_id  in ACCESSI.acce_id%type
   , p_session_id  in ACCESSI.session_id%type default null
   , p_data  in ACCESSI.data%type default null
   , p_log  in ACCESSI.log%type default null
   , p_utente  in ACCESSI.utente%type default null
   , p_istanza  in ACCESSI.istanza%type default null
   , p_modulo  in ACCESSI.modulo%type default null
   , p_db_user  in ACCESSI.db_user%type default null
   , p_terminale  in ACCESSI.terminale%type default null
   , p_note  in ACCESSI.note%type default null
   , p_id_credenziale  in ACCESSI.id_credenziale%type default null
   , p_tipo_autenticazione  in ACCESSI.tipo_autenticazione%type default null
   , p_stato  in ACCESSI.stato%type default null
   );
   -- Getter per attributo session_id di riga identificata da chiave
   function get_session_id /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.session_id%type;
   pragma restrict_references( get_session_id, WNDS );
   -- Getter per attributo data di riga identificata da chiave
   function get_data /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.data%type;
   pragma restrict_references( get_data, WNDS );
   -- Getter per attributo log di riga identificata da chiave
   function get_log /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.log%type;
   pragma restrict_references( get_log, WNDS );
   -- Getter per attributo utente di riga identificata da chiave
   function get_utente /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.utente%type;
   pragma restrict_references( get_utente, WNDS );
   -- Getter per attributo istanza di riga identificata da chiave
   function get_istanza /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.istanza%type;
   pragma restrict_references( get_istanza, WNDS );
   -- Getter per attributo modulo di riga identificata da chiave
   function get_modulo /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.modulo%type;
   pragma restrict_references( get_modulo, WNDS );
   -- Getter per attributo db_user di riga identificata da chiave
   function get_db_user /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.db_user%type;
   pragma restrict_references( get_db_user, WNDS );
   -- Getter per attributo terminale di riga identificata da chiave
   function get_terminale /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.terminale%type;
   pragma restrict_references( get_terminale, WNDS );
   -- Getter per attributo note di riga identificata da chiave
   function get_note /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.note%type;
   pragma restrict_references( get_note, WNDS );
   -- Getter per attributo id_credenziale di riga identificata da chiave
   function get_id_credenziale /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.id_credenziale%type;
   pragma restrict_references( get_id_credenziale, WNDS );
   -- Getter per attributo tipo_autenticazione di riga identificata da chiave
   function get_tipo_autenticazione /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.tipo_autenticazione%type;
   pragma restrict_references( get_tipo_autenticazione, WNDS );
   -- Getter per attributo stato di riga identificata da chiave
   function get_stato /* SLAVE_COPY */
   (
     p_acce_id  in ACCESSI.acce_id%type
   ) return ACCESSI.stato%type;
   pragma restrict_references( get_stato, WNDS );
   -- Setter per attributo acce_id di riga identificata da chiave
   procedure set_acce_id
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.acce_id%type default null
   );
   -- Setter per attributo session_id di riga identificata da chiave
   procedure set_session_id
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.session_id%type default null
   );
   -- Setter per attributo data di riga identificata da chiave
   procedure set_data
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.data%type default null
   );
   -- Setter per attributo log di riga identificata da chiave
   procedure set_log
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.log%type default null
   );
   -- Setter per attributo utente di riga identificata da chiave
   procedure set_utente
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.utente%type default null
   );
   -- Setter per attributo istanza di riga identificata da chiave
   procedure set_istanza
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.istanza%type default null
   );
   -- Setter per attributo modulo di riga identificata da chiave
   procedure set_modulo
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.modulo%type default null
   );
   -- Setter per attributo db_user di riga identificata da chiave
   procedure set_db_user
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.db_user%type default null
   );
   -- Setter per attributo terminale di riga identificata da chiave
   procedure set_terminale
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.terminale%type default null
   );
   -- Setter per attributo note di riga identificata da chiave
   procedure set_note
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.note%type default null
   );
   -- Setter per attributo id_credenziale di riga identificata da chiave
   procedure set_id_credenziale
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.id_credenziale%type default null
   );
   -- Setter per attributo tipo_autenticazione di riga identificata da chiave
   procedure set_tipo_autenticazione
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.tipo_autenticazione%type default null
   );
   -- Setter per attributo stato di riga identificata da chiave
   procedure set_stato
   (
     p_acce_id  in ACCESSI.acce_id%type
   , p_value  in ACCESSI.stato%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_acce_id  in varchar2 default null
   , p_session_id  in varchar2 default null
   , p_data  in varchar2 default null
   , p_log  in varchar2 default null
   , p_utente  in varchar2 default null
   , p_istanza  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_db_user  in varchar2 default null
   , p_terminale  in varchar2 default null
   , p_note  in varchar2 default null
   , p_id_credenziale  in varchar2 default null
   , p_tipo_autenticazione  in varchar2 default null
   , p_stato  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_acce_id  in varchar2 default null
   , p_session_id  in varchar2 default null
   , p_data  in varchar2 default null
   , p_log  in varchar2 default null
   , p_utente  in varchar2 default null
   , p_istanza  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_db_user  in varchar2 default null
   , p_terminale  in varchar2 default null
   , p_note  in varchar2 default null
   , p_id_credenziale  in varchar2 default null
   , p_tipo_autenticazione  in varchar2 default null
   , p_stato  in varchar2 default null
   ) return integer;
end accessi_tpk;
/

