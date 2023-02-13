--liquibase formatted sql

--changeset mturra:201901301231_259 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE parametri_richiesta_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        parametri_richiesta_tpk
 DESCRIZIONE: Gestione tabella PARAMETRI_RICHIESTA.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    30/09/2009  mmalferrari  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'PARAMETRI_RICHIESTA';
   subtype t_rowtype is PARAMETRI_RICHIESTA%rowtype;
   -- Tipo del record primary key
subtype t_id_parametro  is PARAMETRI_RICHIESTA.id_parametro%type;
   type t_PK is record
   (
id_parametro  t_id_parametro
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type default null
   , p_id_richiesta  in PARAMETRI_RICHIESTA.id_richiesta%type
   , p_parametro  in PARAMETRI_RICHIESTA.parametro%type
   , p_valore  in PARAMETRI_RICHIESTA.valore%type default null
   , p_utente_aggiornamento  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default null
   , p_data_aggiornamento  in PARAMETRI_RICHIESTA.data_aggiornamento%type default null
   );
   function ins  /*+ SOA  */
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type default null
   , p_id_richiesta  in PARAMETRI_RICHIESTA.id_richiesta%type
   , p_parametro  in PARAMETRI_RICHIESTA.parametro%type
   , p_valore  in PARAMETRI_RICHIESTA.valore%type default null
   , p_utente_aggiornamento  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default null
   , p_data_aggiornamento  in PARAMETRI_RICHIESTA.data_aggiornamento%type default null
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   , p_OLD_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type default null
   , p_NEW_id_richiesta  in PARAMETRI_RICHIESTA.id_richiesta%type default afc.default_null('PARAMETRI_RICHIESTA.id_richiesta')
   , p_OLD_id_richiesta  in PARAMETRI_RICHIESTA.id_richiesta%type default null
   , p_NEW_parametro  in PARAMETRI_RICHIESTA.parametro%type default afc.default_null('PARAMETRI_RICHIESTA.parametro')
   , p_OLD_parametro  in PARAMETRI_RICHIESTA.parametro%type default null
   , p_NEW_valore  in PARAMETRI_RICHIESTA.valore%type default afc.default_null('PARAMETRI_RICHIESTA.valore')
   , p_OLD_valore  in PARAMETRI_RICHIESTA.valore%type default null
   , p_NEW_utente_aggiornamento  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default afc.default_null('PARAMETRI_RICHIESTA.utente_aggiornamento')
   , p_OLD_utente_aggiornamento  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default null
   , p_NEW_data_aggiornamento  in PARAMETRI_RICHIESTA.data_aggiornamento%type default afc.default_null('PARAMETRI_RICHIESTA.data_aggiornamento')
   , p_OLD_data_aggiornamento  in PARAMETRI_RICHIESTA.data_aggiornamento%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   procedure upd_column
   (
p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   , p_column in varchar2
   , p_value  in date
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   , p_id_richiesta  in PARAMETRI_RICHIESTA.id_richiesta%type default null
   , p_parametro  in PARAMETRI_RICHIESTA.parametro%type default null
   , p_valore  in PARAMETRI_RICHIESTA.valore%type default null
   , p_utente_aggiornamento  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default null
   , p_data_aggiornamento  in PARAMETRI_RICHIESTA.data_aggiornamento%type default null
   );
-- Getter per attributo id_richiesta di riga identificata da chiave
   function get_id_richiesta /* SLAVE_COPY */
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   ) return PARAMETRI_RICHIESTA.id_richiesta%type;
   pragma restrict_references( get_id_richiesta, WNDS );
-- Getter per attributo parametro di riga identificata da chiave
   function get_parametro /* SLAVE_COPY */
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   ) return PARAMETRI_RICHIESTA.parametro%type;
   pragma restrict_references( get_parametro, WNDS );
-- Getter per attributo valore di riga identificata da chiave
   function get_valore /* SLAVE_COPY */
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   ) return PARAMETRI_RICHIESTA.valore%type;
   pragma restrict_references( get_valore, WNDS );
-- Getter per attributo utente_aggiornamento di riga identificata da chiave
   function get_utente_aggiornamento /* SLAVE_COPY */
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   ) return PARAMETRI_RICHIESTA.utente_aggiornamento%type;
   pragma restrict_references( get_utente_aggiornamento, WNDS );
-- Getter per attributo data_aggiornamento di riga identificata da chiave
   function get_data_aggiornamento /* SLAVE_COPY */
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   ) return PARAMETRI_RICHIESTA.data_aggiornamento%type;
   pragma restrict_references( get_data_aggiornamento, WNDS );
-- Setter per attributo id_parametro di riga identificata da chiave
   procedure set_id_parametro
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   , p_value  in PARAMETRI_RICHIESTA.id_parametro%type default null
   );
-- Setter per attributo id_richiesta di riga identificata da chiave
   procedure set_id_richiesta
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   , p_value  in PARAMETRI_RICHIESTA.id_richiesta%type default null
   );
-- Setter per attributo parametro di riga identificata da chiave
   procedure set_parametro
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   , p_value  in PARAMETRI_RICHIESTA.parametro%type default null
   );
-- Setter per attributo valore di riga identificata da chiave
   procedure set_valore
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   , p_value  in PARAMETRI_RICHIESTA.valore%type default null
   );
-- Setter per attributo utente_aggiornamento di riga identificata da chiave
   procedure set_utente_aggiornamento
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   , p_value  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default null
   );
-- Setter per attributo data_aggiornamento di riga identificata da chiave
   procedure set_data_aggiornamento
   (
     p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
   , p_value  in PARAMETRI_RICHIESTA.data_aggiornamento%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_id_parametro  in varchar2 default null
   , p_id_richiesta  in varchar2 default null
   , p_parametro  in varchar2 default null
   , p_valore  in varchar2 default null
   , p_utente_aggiornamento  in varchar2 default null
   , p_data_aggiornamento  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_id_parametro  in varchar2 default null
   , p_id_richiesta  in varchar2 default null
   , p_parametro  in varchar2 default null
   , p_valore  in varchar2 default null
   , p_utente_aggiornamento  in varchar2 default null
   , p_data_aggiornamento  in varchar2 default null
   ) return integer;
end parametri_richiesta_tpk;
/

