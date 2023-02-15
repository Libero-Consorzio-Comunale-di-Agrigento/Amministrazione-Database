CREATE OR REPLACE PACKAGE richieste_abilitazione_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        richieste_abilitazione_tpk
 DESCRIZIONE: Gestione tabella RICHIESTE_ABILITAZIONE.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    30/09/2009  mmalferrari  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'RICHIESTE_ABILITAZIONE';
   subtype t_rowtype is RICHIESTE_ABILITAZIONE%rowtype;
   -- Tipo del record primary key
subtype t_id_richiesta  is RICHIESTE_ABILITAZIONE.id_richiesta%type;
   type t_PK is record
   (
id_richiesta  t_id_richiesta
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type default null
   , p_utente  in RICHIESTE_ABILITAZIONE.utente%type
   , p_modulo  in RICHIESTE_ABILITAZIONE.modulo%type
   , p_istanza  in RICHIESTE_ABILITAZIONE.istanza%type
   , p_stato  in RICHIESTE_ABILITAZIONE.stato%type default 'F'
   , p_data  in RICHIESTE_ABILITAZIONE.data%type default null
   , p_note  in RICHIESTE_ABILITAZIONE.note%type default null
   , p_tipo_notifica  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default null
   , p_indirizzo_notifica  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default null
   , p_note_notifica  in RICHIESTE_ABILITAZIONE.note_notifica%type default null
   , p_notificata  in RICHIESTE_ABILITAZIONE.notificata%type default 'N'
   );
   function ins  /*+ SOA  */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type default null
   , p_utente  in RICHIESTE_ABILITAZIONE.utente%type
   , p_modulo  in RICHIESTE_ABILITAZIONE.modulo%type
   , p_istanza  in RICHIESTE_ABILITAZIONE.istanza%type
   , p_stato  in RICHIESTE_ABILITAZIONE.stato%type default 'F'
   , p_data  in RICHIESTE_ABILITAZIONE.data%type default null
   , p_note  in RICHIESTE_ABILITAZIONE.note%type default null
   , p_tipo_notifica  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default null
   , p_indirizzo_notifica  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default null
   , p_note_notifica  in RICHIESTE_ABILITAZIONE.note_notifica%type default null
   , p_notificata  in RICHIESTE_ABILITAZIONE.notificata%type default 'N'
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_OLD_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type default null
   , p_NEW_utente  in RICHIESTE_ABILITAZIONE.utente%type default afc.default_null('RICHIESTE_ABILITAZIONE.utente')
   , p_OLD_utente  in RICHIESTE_ABILITAZIONE.utente%type default null
   , p_NEW_modulo  in RICHIESTE_ABILITAZIONE.modulo%type default afc.default_null('RICHIESTE_ABILITAZIONE.modulo')
   , p_OLD_modulo  in RICHIESTE_ABILITAZIONE.modulo%type default null
   , p_NEW_istanza  in RICHIESTE_ABILITAZIONE.istanza%type default afc.default_null('RICHIESTE_ABILITAZIONE.istanza')
   , p_OLD_istanza  in RICHIESTE_ABILITAZIONE.istanza%type default null
   , p_NEW_stato  in RICHIESTE_ABILITAZIONE.stato%type default afc.default_null('RICHIESTE_ABILITAZIONE.stato')
   , p_OLD_stato  in RICHIESTE_ABILITAZIONE.stato%type default null
   , p_NEW_data  in RICHIESTE_ABILITAZIONE.data%type default afc.default_null('RICHIESTE_ABILITAZIONE.data')
   , p_OLD_data  in RICHIESTE_ABILITAZIONE.data%type default null
   , p_NEW_note  in RICHIESTE_ABILITAZIONE.note%type default afc.default_null('RICHIESTE_ABILITAZIONE.note')
   , p_OLD_note  in RICHIESTE_ABILITAZIONE.note%type default null
   , p_NEW_tipo_notifica  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default afc.default_null('RICHIESTE_ABILITAZIONE.tipo_notifica')
   , p_OLD_tipo_notifica  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default null
   , p_NEW_indirizzo_notifica  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default afc.default_null('RICHIESTE_ABILITAZIONE.indirizzo_notifica')
   , p_OLD_indirizzo_notifica  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default null
   , p_NEW_note_notifica  in RICHIESTE_ABILITAZIONE.note_notifica%type default afc.default_null('RICHIESTE_ABILITAZIONE.note_notifica')
   , p_OLD_note_notifica  in RICHIESTE_ABILITAZIONE.note_notifica%type default null
   , p_NEW_notificata  in RICHIESTE_ABILITAZIONE.notificata%type default afc.default_null('RICHIESTE_ABILITAZIONE.notificata')
   , p_OLD_notificata  in RICHIESTE_ABILITAZIONE.notificata%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   procedure upd_column
   (
p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_column in varchar2
   , p_value  in date
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_utente  in RICHIESTE_ABILITAZIONE.utente%type default null
   , p_modulo  in RICHIESTE_ABILITAZIONE.modulo%type default null
   , p_istanza  in RICHIESTE_ABILITAZIONE.istanza%type default null
   , p_stato  in RICHIESTE_ABILITAZIONE.stato%type default null
   , p_data  in RICHIESTE_ABILITAZIONE.data%type default null
   , p_note  in RICHIESTE_ABILITAZIONE.note%type default null
   , p_tipo_notifica  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default null
   , p_indirizzo_notifica  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default null
   , p_note_notifica  in RICHIESTE_ABILITAZIONE.note_notifica%type default null
   , p_notificata  in RICHIESTE_ABILITAZIONE.notificata%type default null
   );
-- Getter per attributo utente di riga identificata da chiave
   function get_utente /* SLAVE_COPY */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return RICHIESTE_ABILITAZIONE.utente%type;
   pragma restrict_references( get_utente, WNDS );
-- Getter per attributo modulo di riga identificata da chiave
   function get_modulo /* SLAVE_COPY */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return RICHIESTE_ABILITAZIONE.modulo%type;
   pragma restrict_references( get_modulo, WNDS );
-- Getter per attributo istanza di riga identificata da chiave
   function get_istanza /* SLAVE_COPY */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return RICHIESTE_ABILITAZIONE.istanza%type;
   pragma restrict_references( get_istanza, WNDS );
-- Getter per attributo stato di riga identificata da chiave
   function get_stato /* SLAVE_COPY */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return RICHIESTE_ABILITAZIONE.stato%type;
   pragma restrict_references( get_stato, WNDS );
-- Getter per attributo data di riga identificata da chiave
   function get_data /* SLAVE_COPY */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return RICHIESTE_ABILITAZIONE.data%type;
   pragma restrict_references( get_data, WNDS );
-- Getter per attributo note di riga identificata da chiave
   function get_note /* SLAVE_COPY */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return RICHIESTE_ABILITAZIONE.note%type;
   pragma restrict_references( get_note, WNDS );
-- Getter per attributo tipo_notifica di riga identificata da chiave
   function get_tipo_notifica /* SLAVE_COPY */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return RICHIESTE_ABILITAZIONE.tipo_notifica%type;
   pragma restrict_references( get_tipo_notifica, WNDS );
-- Getter per attributo indirizzo_notifica di riga identificata da chiave
   function get_indirizzo_notifica /* SLAVE_COPY */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return RICHIESTE_ABILITAZIONE.indirizzo_notifica%type;
   pragma restrict_references( get_indirizzo_notifica, WNDS );
-- Getter per attributo note_notifica di riga identificata da chiave
   function get_note_notifica /* SLAVE_COPY */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return RICHIESTE_ABILITAZIONE.note_notifica%type;
   pragma restrict_references( get_note_notifica, WNDS );
-- Getter per attributo notificata di riga identificata da chiave
   function get_notificata /* SLAVE_COPY */
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   ) return RICHIESTE_ABILITAZIONE.notificata%type;
   pragma restrict_references( get_notificata, WNDS );
-- Setter per attributo id_richiesta di riga identificata da chiave
   procedure set_id_richiesta
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_value  in RICHIESTE_ABILITAZIONE.id_richiesta%type default null
   );
-- Setter per attributo utente di riga identificata da chiave
   procedure set_utente
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_value  in RICHIESTE_ABILITAZIONE.utente%type default null
   );
-- Setter per attributo modulo di riga identificata da chiave
   procedure set_modulo
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_value  in RICHIESTE_ABILITAZIONE.modulo%type default null
   );
-- Setter per attributo istanza di riga identificata da chiave
   procedure set_istanza
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_value  in RICHIESTE_ABILITAZIONE.istanza%type default null
   );
-- Setter per attributo stato di riga identificata da chiave
   procedure set_stato
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_value  in RICHIESTE_ABILITAZIONE.stato%type default null
   );
-- Setter per attributo data di riga identificata da chiave
   procedure set_data
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_value  in RICHIESTE_ABILITAZIONE.data%type default null
   );
-- Setter per attributo note di riga identificata da chiave
   procedure set_note
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_value  in RICHIESTE_ABILITAZIONE.note%type default null
   );
-- Setter per attributo tipo_notifica di riga identificata da chiave
   procedure set_tipo_notifica
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_value  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default null
   );
-- Setter per attributo indirizzo_notifica di riga identificata da chiave
   procedure set_indirizzo_notifica
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_value  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default null
   );
-- Setter per attributo note_notifica di riga identificata da chiave
   procedure set_note_notifica
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_value  in RICHIESTE_ABILITAZIONE.note_notifica%type default null
   );
-- Setter per attributo notificata di riga identificata da chiave
   procedure set_notificata
   (
     p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
   , p_value  in RICHIESTE_ABILITAZIONE.notificata%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_id_richiesta  in varchar2 default null
   , p_utente  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_istanza  in varchar2 default null
   , p_stato  in varchar2 default null
   , p_data  in varchar2 default null
   , p_note  in varchar2 default null
   , p_tipo_notifica  in varchar2 default null
   , p_indirizzo_notifica  in varchar2 default null
   , p_note_notifica  in varchar2 default null
   , p_notificata  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_id_richiesta  in varchar2 default null
   , p_utente  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_istanza  in varchar2 default null
   , p_stato  in varchar2 default null
   , p_data  in varchar2 default null
   , p_note  in varchar2 default null
   , p_tipo_notifica  in varchar2 default null
   , p_indirizzo_notifica  in varchar2 default null
   , p_note_notifica  in varchar2 default null
   , p_notificata  in varchar2 default null
   ) return integer;
end richieste_abilitazione_tpk;
/

