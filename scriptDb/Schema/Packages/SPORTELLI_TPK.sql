CREATE OR REPLACE package sportelli_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        sportelli_tpk
 DESCRIZIONE: Gestione tabella SPORTELLI.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    17/03/2017  snegroni  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'SPORTELLI';
   subtype t_rowtype is SPORTELLI%rowtype;
   -- Tipo del record primary key
subtype t_abi  is SPORTELLI.abi%type;
subtype t_cab  is SPORTELLI.cab%type;
   type t_PK is record
   (
abi  t_abi,
cab  t_cab
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrità chiave
   function can_handle /* SLAVE_COPY */
   (
    p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrità chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_cin_cab  in SPORTELLI.cin_cab%type default null
   , p_descrizione  in SPORTELLI.descrizione%type default null
   , p_indirizzo  in SPORTELLI.indirizzo%type default null
   , p_localita  in SPORTELLI.localita%type default null
   , p_comune  in SPORTELLI.comune%type default null
   , p_provincia  in SPORTELLI.provincia%type default null
   , p_cap  in SPORTELLI.cap%type default null
   , p_dipendenza  in SPORTELLI.dipendenza%type default null
   , p_bic  in SPORTELLI.bic%type default null
   );
   function ins  /*+ SOA  */
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_cin_cab  in SPORTELLI.cin_cab%type default null
   , p_descrizione  in SPORTELLI.descrizione%type default null
   , p_indirizzo  in SPORTELLI.indirizzo%type default null
   , p_localita  in SPORTELLI.localita%type default null
   , p_comune  in SPORTELLI.comune%type default null
   , p_provincia  in SPORTELLI.provincia%type default null
   , p_cap  in SPORTELLI.cap%type default null
   , p_dipendenza  in SPORTELLI.dipendenza%type default null
   , p_bic  in SPORTELLI.bic%type default null
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_abi  in SPORTELLI.abi%type
   , p_OLD_abi  in SPORTELLI.abi%type default null
, p_NEW_cab  in SPORTELLI.cab%type
, p_OLD_cab  in SPORTELLI.cab%type default null
   , p_NEW_cin_cab  in SPORTELLI.cin_cab%type default afc.default_null('SPORTELLI.cin_cab')
   , p_OLD_cin_cab  in SPORTELLI.cin_cab%type default null
   , p_NEW_descrizione  in SPORTELLI.descrizione%type default afc.default_null('SPORTELLI.descrizione')
   , p_OLD_descrizione  in SPORTELLI.descrizione%type default null
   , p_NEW_indirizzo  in SPORTELLI.indirizzo%type default afc.default_null('SPORTELLI.indirizzo')
   , p_OLD_indirizzo  in SPORTELLI.indirizzo%type default null
   , p_NEW_localita  in SPORTELLI.localita%type default afc.default_null('SPORTELLI.localita')
   , p_OLD_localita  in SPORTELLI.localita%type default null
   , p_NEW_comune  in SPORTELLI.comune%type default afc.default_null('SPORTELLI.comune')
   , p_OLD_comune  in SPORTELLI.comune%type default null
   , p_NEW_provincia  in SPORTELLI.provincia%type default afc.default_null('SPORTELLI.provincia')
   , p_OLD_provincia  in SPORTELLI.provincia%type default null
   , p_NEW_cap  in SPORTELLI.cap%type default afc.default_null('SPORTELLI.cap')
   , p_OLD_cap  in SPORTELLI.cap%type default null
   , p_NEW_dipendenza  in SPORTELLI.dipendenza%type default afc.default_null('SPORTELLI.dipendenza')
   , p_OLD_dipendenza  in SPORTELLI.dipendenza%type default null
   , p_NEW_bic  in SPORTELLI.bic%type default afc.default_null('SPORTELLI.bic')
   , p_OLD_bic  in SPORTELLI.bic%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_abi  in SPORTELLI.abi%type
, p_cab  in SPORTELLI.cab%type
   , p_cin_cab  in SPORTELLI.cin_cab%type default null
   , p_descrizione  in SPORTELLI.descrizione%type default null
   , p_indirizzo  in SPORTELLI.indirizzo%type default null
   , p_localita  in SPORTELLI.localita%type default null
   , p_comune  in SPORTELLI.comune%type default null
   , p_provincia  in SPORTELLI.provincia%type default null
   , p_cap  in SPORTELLI.cap%type default null
   , p_dipendenza  in SPORTELLI.dipendenza%type default null
   , p_bic  in SPORTELLI.bic%type default null
   );
-- Getter per attributo cin_cab di riga identificata da chiave
   function get_cin_cab /* SLAVE_COPY */
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   ) return SPORTELLI.cin_cab%type;
   pragma restrict_references( get_cin_cab, WNDS );
-- Getter per attributo descrizione di riga identificata da chiave
   function get_descrizione /* SLAVE_COPY */
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   ) return SPORTELLI.descrizione%type;
   pragma restrict_references( get_descrizione, WNDS );
-- Getter per attributo indirizzo di riga identificata da chiave
   function get_indirizzo /* SLAVE_COPY */
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   ) return SPORTELLI.indirizzo%type;
   pragma restrict_references( get_indirizzo, WNDS );
-- Getter per attributo localita di riga identificata da chiave
   function get_localita /* SLAVE_COPY */
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   ) return SPORTELLI.localita%type;
   pragma restrict_references( get_localita, WNDS );
-- Getter per attributo comune di riga identificata da chiave
   function get_comune /* SLAVE_COPY */
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   ) return SPORTELLI.comune%type;
   pragma restrict_references( get_comune, WNDS );
-- Getter per attributo provincia di riga identificata da chiave
   function get_provincia /* SLAVE_COPY */
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   ) return SPORTELLI.provincia%type;
   pragma restrict_references( get_provincia, WNDS );
-- Getter per attributo cap di riga identificata da chiave
   function get_cap /* SLAVE_COPY */
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   ) return SPORTELLI.cap%type;
   pragma restrict_references( get_cap, WNDS );
-- Getter per attributo dipendenza di riga identificata da chiave
   function get_dipendenza /* SLAVE_COPY */
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   ) return SPORTELLI.dipendenza%type;
   pragma restrict_references( get_dipendenza, WNDS );
-- Getter per attributo bic di riga identificata da chiave
   function get_bic /* SLAVE_COPY */
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   ) return SPORTELLI.bic%type;
   pragma restrict_references( get_bic, WNDS );
-- Setter per attributo abi di riga identificata da chiave
   procedure set_abi
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_value  in SPORTELLI.abi%type default null
   );
-- Setter per attributo cab di riga identificata da chiave
   procedure set_cab
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_value  in SPORTELLI.cab%type default null
);
-- Setter per attributo cin_cab di riga identificata da chiave
   procedure set_cin_cab
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_value  in SPORTELLI.cin_cab%type default null
   );
-- Setter per attributo descrizione di riga identificata da chiave
   procedure set_descrizione
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_value  in SPORTELLI.descrizione%type default null
   );
-- Setter per attributo indirizzo di riga identificata da chiave
   procedure set_indirizzo
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_value  in SPORTELLI.indirizzo%type default null
   );
-- Setter per attributo localita di riga identificata da chiave
   procedure set_localita
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_value  in SPORTELLI.localita%type default null
   );
-- Setter per attributo comune di riga identificata da chiave
   procedure set_comune
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_value  in SPORTELLI.comune%type default null
   );
-- Setter per attributo provincia di riga identificata da chiave
   procedure set_provincia
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_value  in SPORTELLI.provincia%type default null
   );
-- Setter per attributo cap di riga identificata da chiave
   procedure set_cap
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_value  in SPORTELLI.cap%type default null
   );
-- Setter per attributo dipendenza di riga identificata da chiave
   procedure set_dipendenza
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_value  in SPORTELLI.dipendenza%type default null
   );
-- Setter per attributo bic di riga identificata da chiave
   procedure set_bic
   (
     p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
   , p_value  in SPORTELLI.bic%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_abi  in varchar2 default null
, p_cab  in varchar2 default null
   , p_cin_cab  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_indirizzo  in varchar2 default null
   , p_localita  in varchar2 default null
   , p_comune  in varchar2 default null
   , p_provincia  in varchar2 default null
   , p_cap  in varchar2 default null
   , p_dipendenza  in varchar2 default null
   , p_bic  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_abi  in varchar2 default null
, p_cab  in varchar2 default null
   , p_cin_cab  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_indirizzo  in varchar2 default null
   , p_localita  in varchar2 default null
   , p_comune  in varchar2 default null
   , p_provincia  in varchar2 default null
   , p_cap  in varchar2 default null
   , p_dipendenza  in varchar2 default null
   , p_bic  in varchar2 default null
   ) return integer;
end sportelli_tpk;
/

