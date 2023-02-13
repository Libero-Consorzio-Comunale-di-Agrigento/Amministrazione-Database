--liquibase formatted sql

--changeset mturra:201901301231_242 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE informative_privacy_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        informative_privacy_tpk
 DESCRIZIONE: Gestione tabella INFORMATIVE_PRIVACY.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    12/03/2018  adadamo  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'INFORMATIVE_PRIVACY';
   subtype t_rowtype is INFORMATIVE_PRIVACY%rowtype;
   -- Tipo del record primary key
subtype t_id  is INFORMATIVE_PRIVACY.id%type;
   type t_PK is record
   (
id  t_id
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_id  in INFORMATIVE_PRIVACY.id%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrità chiave
   function can_handle /* SLAVE_COPY */
   (
    p_id  in INFORMATIVE_PRIVACY.id%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrità chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_id  in INFORMATIVE_PRIVACY.id%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_id  in INFORMATIVE_PRIVACY.id%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_id  in INFORMATIVE_PRIVACY.id%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_id  in INFORMATIVE_PRIVACY.id%type default null
   , p_ente  in INFORMATIVE_PRIVACY.ente%type default null
   , p_modulo  in INFORMATIVE_PRIVACY.modulo%type default null
   , p_data_informativa  in INFORMATIVE_PRIVACY.data_informativa%type default null
   , p_url_documento_privacy  in INFORMATIVE_PRIVACY.url_documento_privacy%type default null
   , p_note  in INFORMATIVE_PRIVACY.note%type default null
   );
   function ins  /*+ SOA  */
   (
     p_id  in INFORMATIVE_PRIVACY.id%type default null
   , p_ente  in INFORMATIVE_PRIVACY.ente%type default null
   , p_modulo  in INFORMATIVE_PRIVACY.modulo%type default null
   , p_data_informativa  in INFORMATIVE_PRIVACY.data_informativa%type default null
   , p_url_documento_privacy  in INFORMATIVE_PRIVACY.url_documento_privacy%type default null
   , p_note  in INFORMATIVE_PRIVACY.note%type default null
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_id  in INFORMATIVE_PRIVACY.id%type
   , p_OLD_id  in INFORMATIVE_PRIVACY.id%type default null
   , p_NEW_ente  in INFORMATIVE_PRIVACY.ente%type default afc.default_null('INFORMATIVE_PRIVACY.ente')
   , p_OLD_ente  in INFORMATIVE_PRIVACY.ente%type default null
   , p_NEW_modulo  in INFORMATIVE_PRIVACY.modulo%type default afc.default_null('INFORMATIVE_PRIVACY.modulo')
   , p_OLD_modulo  in INFORMATIVE_PRIVACY.modulo%type default null
   , p_NEW_data_informativa  in INFORMATIVE_PRIVACY.data_informativa%type default afc.default_null('INFORMATIVE_PRIVACY.data_informativa')
   , p_OLD_data_informativa  in INFORMATIVE_PRIVACY.data_informativa%type default null
   , p_NEW_url_documento_privacy  in INFORMATIVE_PRIVACY.url_documento_privacy%type default afc.default_null('INFORMATIVE_PRIVACY.url_documento_privacy')
   , p_OLD_url_documento_privacy  in INFORMATIVE_PRIVACY.url_documento_privacy%type default null
   , p_NEW_note  in INFORMATIVE_PRIVACY.note%type default afc.default_null('INFORMATIVE_PRIVACY.note')
   , p_OLD_note  in INFORMATIVE_PRIVACY.note%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   procedure upd_column
   (
p_id  in INFORMATIVE_PRIVACY.id%type
   , p_column in varchar2
   , p_value  in date
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_id  in INFORMATIVE_PRIVACY.id%type
   , p_ente  in INFORMATIVE_PRIVACY.ente%type default null
   , p_modulo  in INFORMATIVE_PRIVACY.modulo%type default null
   , p_data_informativa  in INFORMATIVE_PRIVACY.data_informativa%type default null
   , p_url_documento_privacy  in INFORMATIVE_PRIVACY.url_documento_privacy%type default null
   , p_note  in INFORMATIVE_PRIVACY.note%type default null
   );
-- Getter per attributo ente di riga identificata da chiave
   function get_ente /* SLAVE_COPY */
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   ) return INFORMATIVE_PRIVACY.ente%type;
   pragma restrict_references( get_ente, WNDS );
-- Getter per attributo modulo di riga identificata da chiave
   function get_modulo /* SLAVE_COPY */
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   ) return INFORMATIVE_PRIVACY.modulo%type;
   pragma restrict_references( get_modulo, WNDS );
-- Getter per attributo data_informativa di riga identificata da chiave
   function get_data_informativa /* SLAVE_COPY */
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   ) return INFORMATIVE_PRIVACY.data_informativa%type;
   pragma restrict_references( get_data_informativa, WNDS );
-- Getter per attributo url_documento_privacy di riga identificata da chiave
   function get_url_documento_privacy /* SLAVE_COPY */
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   ) return INFORMATIVE_PRIVACY.url_documento_privacy%type;
   pragma restrict_references( get_url_documento_privacy, WNDS );
-- Getter per attributo note di riga identificata da chiave
   function get_note /* SLAVE_COPY */
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   ) return INFORMATIVE_PRIVACY.note%type;
   pragma restrict_references( get_note, WNDS );
-- Setter per attributo id di riga identificata da chiave
   procedure set_id
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   , p_value  in INFORMATIVE_PRIVACY.id%type default null
   );
-- Setter per attributo ente di riga identificata da chiave
   procedure set_ente
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   , p_value  in INFORMATIVE_PRIVACY.ente%type default null
   );
-- Setter per attributo modulo di riga identificata da chiave
   procedure set_modulo
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   , p_value  in INFORMATIVE_PRIVACY.modulo%type default null
   );
-- Setter per attributo data_informativa di riga identificata da chiave
   procedure set_data_informativa
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   , p_value  in INFORMATIVE_PRIVACY.data_informativa%type default null
   );
-- Setter per attributo url_documento_privacy di riga identificata da chiave
   procedure set_url_documento_privacy
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   , p_value  in INFORMATIVE_PRIVACY.url_documento_privacy%type default null
   );
-- Setter per attributo note di riga identificata da chiave
   procedure set_note
   (
     p_id  in INFORMATIVE_PRIVACY.id%type
   , p_value  in INFORMATIVE_PRIVACY.note%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_id  in varchar2 default null
   , p_ente  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_data_informativa  in varchar2 default null
   , p_url_documento_privacy  in varchar2 default null
   , p_note  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_id  in varchar2 default null
   , p_ente  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_data_informativa  in varchar2 default null
   , p_url_documento_privacy  in varchar2 default null
   , p_note  in varchar2 default null
   ) return integer;
end informative_privacy_tpk;
/

