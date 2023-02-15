CREATE OR REPLACE package omonimie_gestite_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        omonimie_gestite_tpk
 DESCRIZIONE: Gestione tabella OMONIMIE_GESTITE.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    27/02/2012  snegroni  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'OMONIMIE_GESTITE';
   subtype t_rowtype is OMONIMIE_GESTITE%rowtype;
   -- Tipo del record primary key
subtype t_id_omonimia  is OMONIMIE_GESTITE.id_omonimia%type;
   type t_PK is record
   (
id_omonimia  t_id_omonimia
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrità chiave
   function can_handle /* SLAVE_COPY */
   (
    p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrità chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type default null
   , p_primario  in OMONIMIE_GESTITE.primario%type default null
   , p_secondario  in OMONIMIE_GESTITE.secondario%type default null
   , p_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default null
   , p_unificato  in OMONIMIE_GESTITE.unificato%type default 0
   , p_copiato  in OMONIMIE_GESTITE.copiato%type default 0
   , p_ignorare  in OMONIMIE_GESTITE.ignorare%type default 0
   , p_note  in OMONIMIE_GESTITE.note%type default null
   , p_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default null
   , p_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default null
   , p_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default null
   , p_data_agg  in OMONIMIE_GESTITE.data_agg%type default null
   );
   function ins  /*+ SOA  */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type default null
   , p_primario  in OMONIMIE_GESTITE.primario%type default null
   , p_secondario  in OMONIMIE_GESTITE.secondario%type default null
   , p_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default null
   , p_unificato  in OMONIMIE_GESTITE.unificato%type default 0
   , p_copiato  in OMONIMIE_GESTITE.copiato%type default 0
   , p_ignorare  in OMONIMIE_GESTITE.ignorare%type default 0
   , p_note  in OMONIMIE_GESTITE.note%type default null
   , p_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default null
   , p_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default null
   , p_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default null
   , p_data_agg  in OMONIMIE_GESTITE.data_agg%type default null
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_OLD_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type default null
   , p_NEW_primario  in OMONIMIE_GESTITE.primario%type default afc.default_null('OMONIMIE_GESTITE.primario')
   , p_OLD_primario  in OMONIMIE_GESTITE.primario%type default null
   , p_NEW_secondario  in OMONIMIE_GESTITE.secondario%type default afc.default_null('OMONIMIE_GESTITE.secondario')
   , p_OLD_secondario  in OMONIMIE_GESTITE.secondario%type default null
   , p_NEW_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default afc.default_null('OMONIMIE_GESTITE.scelto_primario')
   , p_OLD_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default null
   , p_NEW_unificato  in OMONIMIE_GESTITE.unificato%type default afc.default_null('OMONIMIE_GESTITE.unificato')
   , p_OLD_unificato  in OMONIMIE_GESTITE.unificato%type default null
   , p_NEW_copiato  in OMONIMIE_GESTITE.copiato%type default afc.default_null('OMONIMIE_GESTITE.copiato')
   , p_OLD_copiato  in OMONIMIE_GESTITE.copiato%type default null
   , p_NEW_ignorare  in OMONIMIE_GESTITE.ignorare%type default afc.default_null('OMONIMIE_GESTITE.ignorare')
   , p_OLD_ignorare  in OMONIMIE_GESTITE.ignorare%type default null
   , p_NEW_note  in OMONIMIE_GESTITE.note%type default afc.default_null('OMONIMIE_GESTITE.note')
   , p_OLD_note  in OMONIMIE_GESTITE.note%type default null
   , p_NEW_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default afc.default_null('OMONIMIE_GESTITE.nominativo_primario')
   , p_OLD_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default null
   , p_NEW_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default afc.default_null('OMONIMIE_GESTITE.nominativo_secondario')
   , p_OLD_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default null
   , p_NEW_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default afc.default_null('OMONIMIE_GESTITE.utente_agg')
   , p_OLD_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default null
   , p_NEW_data_agg  in OMONIMIE_GESTITE.data_agg%type default afc.default_null('OMONIMIE_GESTITE.data_agg')
   , p_OLD_data_agg  in OMONIMIE_GESTITE.data_agg%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   procedure upd_column
   (
p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_column in varchar2
   , p_value  in date
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_primario  in OMONIMIE_GESTITE.primario%type default null
   , p_secondario  in OMONIMIE_GESTITE.secondario%type default null
   , p_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default null
   , p_unificato  in OMONIMIE_GESTITE.unificato%type default null
   , p_copiato  in OMONIMIE_GESTITE.copiato%type default null
   , p_ignorare  in OMONIMIE_GESTITE.ignorare%type default null
   , p_note  in OMONIMIE_GESTITE.note%type default null
   , p_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default null
   , p_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default null
   , p_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default null
   , p_data_agg  in OMONIMIE_GESTITE.data_agg%type default null
   );
-- Getter per attributo primario di riga identificata da chiave
   function get_primario /* SLAVE_COPY */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return OMONIMIE_GESTITE.primario%type;
   pragma restrict_references( get_primario, WNDS );
-- Getter per attributo secondario di riga identificata da chiave
   function get_secondario /* SLAVE_COPY */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return OMONIMIE_GESTITE.secondario%type;
   pragma restrict_references( get_secondario, WNDS );
-- Getter per attributo scelto_primario di riga identificata da chiave
   function get_scelto_primario /* SLAVE_COPY */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return OMONIMIE_GESTITE.scelto_primario%type;
   pragma restrict_references( get_scelto_primario, WNDS );
-- Getter per attributo unificato di riga identificata da chiave
   function get_unificato /* SLAVE_COPY */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return OMONIMIE_GESTITE.unificato%type;
   pragma restrict_references( get_unificato, WNDS );
-- Getter per attributo copiato di riga identificata da chiave
   function get_copiato /* SLAVE_COPY */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return OMONIMIE_GESTITE.copiato%type;
   pragma restrict_references( get_copiato, WNDS );
-- Getter per attributo ignorare di riga identificata da chiave
   function get_ignorare /* SLAVE_COPY */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return OMONIMIE_GESTITE.ignorare%type;
   pragma restrict_references( get_ignorare, WNDS );
-- Getter per attributo note di riga identificata da chiave
   function get_note /* SLAVE_COPY */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return OMONIMIE_GESTITE.note%type;
   pragma restrict_references( get_note, WNDS );
-- Getter per attributo nominativo_primario di riga identificata da chiave
   function get_nominativo_primario /* SLAVE_COPY */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return OMONIMIE_GESTITE.nominativo_primario%type;
   pragma restrict_references( get_nominativo_primario, WNDS );
-- Getter per attributo nominativo_secondario di riga identificata da chiave
   function get_nominativo_secondario /* SLAVE_COPY */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return OMONIMIE_GESTITE.nominativo_secondario%type;
   pragma restrict_references( get_nominativo_secondario, WNDS );
-- Getter per attributo utente_agg di riga identificata da chiave
   function get_utente_agg /* SLAVE_COPY */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return OMONIMIE_GESTITE.utente_agg%type;
   pragma restrict_references( get_utente_agg, WNDS );
-- Getter per attributo data_agg di riga identificata da chiave
   function get_data_agg /* SLAVE_COPY */
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   ) return OMONIMIE_GESTITE.data_agg%type;
   pragma restrict_references( get_data_agg, WNDS );
-- Setter per attributo id_omonimia di riga identificata da chiave
   procedure set_id_omonimia
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.id_omonimia%type default null
   );
-- Setter per attributo primario di riga identificata da chiave
   procedure set_primario
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.primario%type default null
   );
-- Setter per attributo secondario di riga identificata da chiave
   procedure set_secondario
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.secondario%type default null
   );
-- Setter per attributo scelto_primario di riga identificata da chiave
   procedure set_scelto_primario
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.scelto_primario%type default null
   );
-- Setter per attributo unificato di riga identificata da chiave
   procedure set_unificato
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.unificato%type default null
   );
-- Setter per attributo copiato di riga identificata da chiave
   procedure set_copiato
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.copiato%type default null
   );
-- Setter per attributo ignorare di riga identificata da chiave
   procedure set_ignorare
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.ignorare%type default null
   );
-- Setter per attributo note di riga identificata da chiave
   procedure set_note
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.note%type default null
   );
-- Setter per attributo nominativo_primario di riga identificata da chiave
   procedure set_nominativo_primario
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.nominativo_primario%type default null
   );
-- Setter per attributo nominativo_secondario di riga identificata da chiave
   procedure set_nominativo_secondario
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.nominativo_secondario%type default null
   );
-- Setter per attributo utente_agg di riga identificata da chiave
   procedure set_utente_agg
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.utente_agg%type default null
   );
-- Setter per attributo data_agg di riga identificata da chiave
   procedure set_data_agg
   (
     p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
   , p_value  in OMONIMIE_GESTITE.data_agg%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_id_omonimia  in varchar2 default null
   , p_primario  in varchar2 default null
   , p_secondario  in varchar2 default null
   , p_scelto_primario  in varchar2 default null
   , p_unificato  in varchar2 default null
   , p_copiato  in varchar2 default null
   , p_ignorare  in varchar2 default null
   , p_note  in varchar2 default null
   , p_nominativo_primario  in varchar2 default null
   , p_nominativo_secondario  in varchar2 default null
   , p_utente_agg  in varchar2 default null
   , p_data_agg  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_id_omonimia  in varchar2 default null
   , p_primario  in varchar2 default null
   , p_secondario  in varchar2 default null
   , p_scelto_primario  in varchar2 default null
   , p_unificato  in varchar2 default null
   , p_copiato  in varchar2 default null
   , p_ignorare  in varchar2 default null
   , p_note  in varchar2 default null
   , p_nominativo_primario  in varchar2 default null
   , p_nominativo_secondario  in varchar2 default null
   , p_utente_agg  in varchar2 default null
   , p_data_agg  in varchar2 default null
   ) return integer;
end omonimie_gestite_tpk;
/

