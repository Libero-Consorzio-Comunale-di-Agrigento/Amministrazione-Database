CREATE OR REPLACE package si4_user_source_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        si4_user_source_tpk
 DESCRIZIONE: Gestione tabella SI4_USER_SOURCE.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    11/05/2009  mmalferrari  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'SI4_USER_SOURCE';
   subtype t_rowtype is SI4_USER_SOURCE%rowtype;
   -- Tipo del record primary key
   subtype t_name  is SI4_USER_SOURCE.name%type;
subtype t_type  is SI4_USER_SOURCE.type%type;
   type t_PK is record
   (
    name  t_name,
type  t_type
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrità chiave
   function can_handle /* SLAVE_COPY */
   (
    p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrità chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
   , p_text  in SI4_USER_SOURCE.text%type default null
   , p_filename  in SI4_USER_SOURCE.filename%type default null
   );
   function ins  /*+ SOA  */
   (
     p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
   , p_text  in SI4_USER_SOURCE.text%type default null
   , p_filename  in SI4_USER_SOURCE.filename%type default null
   ) return integer;
   procedure ins  /*+ SOA  */
   (
p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
, p_filename  in SI4_USER_SOURCE.filename%type default null
, p_text  in varchar2 default null
   );
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_name  in SI4_USER_SOURCE.name%type
   , p_OLD_name  in SI4_USER_SOURCE.name%type default null
, p_NEW_type  in SI4_USER_SOURCE.type%type
, p_OLD_type  in SI4_USER_SOURCE.type%type default null
   , p_NEW_text  in SI4_USER_SOURCE.text%type default null
   , p_OLD_text  in SI4_USER_SOURCE.text%type default null
   , p_NEW_filename  in SI4_USER_SOURCE.filename%type default null
   , p_OLD_filename  in SI4_USER_SOURCE.filename%type default null
   );
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer
, p_NEW_name  in SI4_USER_SOURCE.name%type
, p_OLD_name  in SI4_USER_SOURCE.name%type
, p_NEW_type  in SI4_USER_SOURCE.type%type
, p_OLD_type  in SI4_USER_SOURCE.type%type
, p_NEW_filename  in SI4_USER_SOURCE.filename%type
, p_OLD_filename  in SI4_USER_SOURCE.filename%type
, p_NEW_text  in varchar2
, p_OLD_text  in varchar2
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_name  in SI4_USER_SOURCE.name%type
, p_type  in SI4_USER_SOURCE.type%type
   , p_text  in SI4_USER_SOURCE.text%type default null
   , p_filename  in SI4_USER_SOURCE.filename%type default null
   );
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer
, p_name  in SI4_USER_SOURCE.name%type
, p_type  in SI4_USER_SOURCE.type%type
, p_filename  in SI4_USER_SOURCE.filename%type
, p_text  in varchar2
   );
   -- Getter per attributo text di riga identificata da chiave
   function get_text /* SLAVE_COPY */
   (
     p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
   ) return SI4_USER_SOURCE.text%type;
   pragma restrict_references( get_text, WNDS );
   -- Getter per attributo filename di riga identificata da chiave
   function get_filename /* SLAVE_COPY */
   (
     p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
   ) return SI4_USER_SOURCE.filename%type;
   pragma restrict_references( get_filename, WNDS );
   -- Setter per attributo name di riga identificata da chiave
   procedure set_name
   (
     p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
   , p_value  in SI4_USER_SOURCE.name%type default null
   );
-- Setter per attributo type di riga identificata da chiave
   procedure set_type
   (
     p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
   , p_value  in SI4_USER_SOURCE.type%type default null
);
   -- Setter per attributo text di riga identificata da chiave
   procedure set_text
   (
     p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
   , p_value  in SI4_USER_SOURCE.text%type default null
   );
   -- Setter per attributo filename di riga identificata da chiave
   procedure set_filename
   (
     p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
   , p_value  in SI4_USER_SOURCE.filename%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_name  in varchar2 default null
, p_type  in varchar2 default null
   , p_filename  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_name  in varchar2 default null
, p_type  in varchar2 default null
   , p_filename  in varchar2 default null
   ) return integer;
end si4_user_source_tpk;
/

