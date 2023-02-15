CREATE OR REPLACE package stati_territori_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        stati_territori_tpk
 DESCRIZIONE: Gestione tabella STATI_TERRITORI.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    07/12/2016  snegroni  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'STATI_TERRITORI';
   subtype t_rowtype is STATI_TERRITORI%rowtype;
   -- Tipo del record primary key
subtype t_stato_territorio  is STATI_TERRITORI.stato_territorio%type;
   type t_PK is record
   (
stato_territorio  t_stato_territorio
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrità chiave
   function can_handle /* SLAVE_COPY */
   (
    p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrità chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type default null
   , p_denominazione  in STATI_TERRITORI.denominazione%type default null
   , p_denominazione_al1  in STATI_TERRITORI.denominazione_al1%type default null
   , p_denominazione_al2  in STATI_TERRITORI.denominazione_al2%type default null
   , p_sigla  in STATI_TERRITORI.sigla%type default null
   , p_desc_cittadinanza  in STATI_TERRITORI.desc_cittadinanza%type default null
   , p_desc_cittadinanza_al1  in STATI_TERRITORI.desc_cittadinanza_al1%type default null
   , p_desc_cittadinanza_al2  in STATI_TERRITORI.desc_cittadinanza_al2%type default null
   , p_raggruppamento  in STATI_TERRITORI.raggruppamento%type default null
   , p_stato_appartenenza  in STATI_TERRITORI.stato_appartenenza%type default null
   , p_utente_aggiornamento  in STATI_TERRITORI.utente_aggiornamento%type default null
   , p_data_aggiornamento  in STATI_TERRITORI.data_aggiornamento%type default null
   , p_sigla_iso3166_alpha2  in STATI_TERRITORI.sigla_iso3166_alpha2%type default null
   );
   function ins  /*+ SOA  */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type default null
   , p_denominazione  in STATI_TERRITORI.denominazione%type default null
   , p_denominazione_al1  in STATI_TERRITORI.denominazione_al1%type default null
   , p_denominazione_al2  in STATI_TERRITORI.denominazione_al2%type default null
   , p_sigla  in STATI_TERRITORI.sigla%type default null
   , p_desc_cittadinanza  in STATI_TERRITORI.desc_cittadinanza%type default null
   , p_desc_cittadinanza_al1  in STATI_TERRITORI.desc_cittadinanza_al1%type default null
   , p_desc_cittadinanza_al2  in STATI_TERRITORI.desc_cittadinanza_al2%type default null
   , p_raggruppamento  in STATI_TERRITORI.raggruppamento%type default null
   , p_stato_appartenenza  in STATI_TERRITORI.stato_appartenenza%type default null
   , p_utente_aggiornamento  in STATI_TERRITORI.utente_aggiornamento%type default null
   , p_data_aggiornamento  in STATI_TERRITORI.data_aggiornamento%type default null
   , p_sigla_iso3166_alpha2  in STATI_TERRITORI.sigla_iso3166_alpha2%type default null
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_OLD_stato_territorio  in STATI_TERRITORI.stato_territorio%type default null
   , p_NEW_denominazione  in STATI_TERRITORI.denominazione%type default afc.default_null('STATI_TERRITORI.denominazione')
   , p_OLD_denominazione  in STATI_TERRITORI.denominazione%type default null
   , p_NEW_denominazione_al1  in STATI_TERRITORI.denominazione_al1%type default afc.default_null('STATI_TERRITORI.denominazione_al1')
   , p_OLD_denominazione_al1  in STATI_TERRITORI.denominazione_al1%type default null
   , p_NEW_denominazione_al2  in STATI_TERRITORI.denominazione_al2%type default afc.default_null('STATI_TERRITORI.denominazione_al2')
   , p_OLD_denominazione_al2  in STATI_TERRITORI.denominazione_al2%type default null
   , p_NEW_sigla  in STATI_TERRITORI.sigla%type default afc.default_null('STATI_TERRITORI.sigla')
   , p_OLD_sigla  in STATI_TERRITORI.sigla%type default null
   , p_NEW_desc_cittadinanza  in STATI_TERRITORI.desc_cittadinanza%type default afc.default_null('STATI_TERRITORI.desc_cittadinanza')
   , p_OLD_desc_cittadinanza  in STATI_TERRITORI.desc_cittadinanza%type default null
   , p_NEW_desc_cittadinanza_al1  in STATI_TERRITORI.desc_cittadinanza_al1%type default afc.default_null('STATI_TERRITORI.desc_cittadinanza_al1')
   , p_OLD_desc_cittadinanza_al1  in STATI_TERRITORI.desc_cittadinanza_al1%type default null
   , p_NEW_desc_cittadinanza_al2  in STATI_TERRITORI.desc_cittadinanza_al2%type default afc.default_null('STATI_TERRITORI.desc_cittadinanza_al2')
   , p_OLD_desc_cittadinanza_al2  in STATI_TERRITORI.desc_cittadinanza_al2%type default null
   , p_NEW_raggruppamento  in STATI_TERRITORI.raggruppamento%type default afc.default_null('STATI_TERRITORI.raggruppamento')
   , p_OLD_raggruppamento  in STATI_TERRITORI.raggruppamento%type default null
   , p_NEW_stato_appartenenza  in STATI_TERRITORI.stato_appartenenza%type default afc.default_null('STATI_TERRITORI.stato_appartenenza')
   , p_OLD_stato_appartenenza  in STATI_TERRITORI.stato_appartenenza%type default null
   , p_NEW_utente_aggiornamento  in STATI_TERRITORI.utente_aggiornamento%type default afc.default_null('STATI_TERRITORI.utente_aggiornamento')
   , p_OLD_utente_aggiornamento  in STATI_TERRITORI.utente_aggiornamento%type default null
   , p_NEW_data_aggiornamento  in STATI_TERRITORI.data_aggiornamento%type default afc.default_null('STATI_TERRITORI.data_aggiornamento')
   , p_OLD_data_aggiornamento  in STATI_TERRITORI.data_aggiornamento%type default null
   , p_NEW_sigla_iso3166_alpha2  in STATI_TERRITORI.sigla_iso3166_alpha2%type default afc.default_null('STATI_TERRITORI.sigla_iso3166_alpha2')
   , p_OLD_sigla_iso3166_alpha2  in STATI_TERRITORI.sigla_iso3166_alpha2%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   procedure upd_column
   (
p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_column in varchar2
   , p_value  in date
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_denominazione  in STATI_TERRITORI.denominazione%type default null
   , p_denominazione_al1  in STATI_TERRITORI.denominazione_al1%type default null
   , p_denominazione_al2  in STATI_TERRITORI.denominazione_al2%type default null
   , p_sigla  in STATI_TERRITORI.sigla%type default null
   , p_desc_cittadinanza  in STATI_TERRITORI.desc_cittadinanza%type default null
   , p_desc_cittadinanza_al1  in STATI_TERRITORI.desc_cittadinanza_al1%type default null
   , p_desc_cittadinanza_al2  in STATI_TERRITORI.desc_cittadinanza_al2%type default null
   , p_raggruppamento  in STATI_TERRITORI.raggruppamento%type default null
   , p_stato_appartenenza  in STATI_TERRITORI.stato_appartenenza%type default null
   , p_utente_aggiornamento  in STATI_TERRITORI.utente_aggiornamento%type default null
   , p_data_aggiornamento  in STATI_TERRITORI.data_aggiornamento%type default null
   , p_sigla_iso3166_alpha2  in STATI_TERRITORI.sigla_iso3166_alpha2%type default null
   );
-- Getter per attributo denominazione di riga identificata da chiave
   function get_denominazione /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.denominazione%type;
   pragma restrict_references( get_denominazione, WNDS );
-- Getter per attributo denominazione_al1 di riga identificata da chiave
   function get_denominazione_al1 /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.denominazione_al1%type;
   pragma restrict_references( get_denominazione_al1, WNDS );
-- Getter per attributo denominazione_al2 di riga identificata da chiave
   function get_denominazione_al2 /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.denominazione_al2%type;
   pragma restrict_references( get_denominazione_al2, WNDS );
-- Getter per attributo sigla di riga identificata da chiave
   function get_sigla /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.sigla%type;
   pragma restrict_references( get_sigla, WNDS );
-- Getter per attributo desc_cittadinanza di riga identificata da chiave
   function get_desc_cittadinanza /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.desc_cittadinanza%type;
   pragma restrict_references( get_desc_cittadinanza, WNDS );
-- Getter per attributo desc_cittadinanza_al1 di riga identificata da chiave
   function get_desc_cittadinanza_al1 /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.desc_cittadinanza_al1%type;
   pragma restrict_references( get_desc_cittadinanza_al1, WNDS );
-- Getter per attributo desc_cittadinanza_al2 di riga identificata da chiave
   function get_desc_cittadinanza_al2 /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.desc_cittadinanza_al2%type;
   pragma restrict_references( get_desc_cittadinanza_al2, WNDS );
-- Getter per attributo raggruppamento di riga identificata da chiave
   function get_raggruppamento /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.raggruppamento%type;
   pragma restrict_references( get_raggruppamento, WNDS );
-- Getter per attributo stato_appartenenza di riga identificata da chiave
   function get_stato_appartenenza /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.stato_appartenenza%type;
   pragma restrict_references( get_stato_appartenenza, WNDS );
-- Getter per attributo utente_aggiornamento di riga identificata da chiave
   function get_utente_aggiornamento /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.utente_aggiornamento%type;
   pragma restrict_references( get_utente_aggiornamento, WNDS );
-- Getter per attributo data_aggiornamento di riga identificata da chiave
   function get_data_aggiornamento /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.data_aggiornamento%type;
   pragma restrict_references( get_data_aggiornamento, WNDS );
-- Getter per attributo sigla_iso3166_alpha2 di riga identificata da chiave
   function get_sigla_iso3166_alpha2 /* SLAVE_COPY */
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   ) return STATI_TERRITORI.sigla_iso3166_alpha2%type;
   pragma restrict_references( get_sigla_iso3166_alpha2, WNDS );
-- Setter per attributo stato_territorio di riga identificata da chiave
   procedure set_stato_territorio
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.stato_territorio%type default null
   );
-- Setter per attributo denominazione di riga identificata da chiave
   procedure set_denominazione
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.denominazione%type default null
   );
-- Setter per attributo denominazione_al1 di riga identificata da chiave
   procedure set_denominazione_al1
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.denominazione_al1%type default null
   );
-- Setter per attributo denominazione_al2 di riga identificata da chiave
   procedure set_denominazione_al2
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.denominazione_al2%type default null
   );
-- Setter per attributo sigla di riga identificata da chiave
   procedure set_sigla
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.sigla%type default null
   );
-- Setter per attributo desc_cittadinanza di riga identificata da chiave
   procedure set_desc_cittadinanza
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.desc_cittadinanza%type default null
   );
-- Setter per attributo desc_cittadinanza_al1 di riga identificata da chiave
   procedure set_desc_cittadinanza_al1
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.desc_cittadinanza_al1%type default null
   );
-- Setter per attributo desc_cittadinanza_al2 di riga identificata da chiave
   procedure set_desc_cittadinanza_al2
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.desc_cittadinanza_al2%type default null
   );
-- Setter per attributo raggruppamento di riga identificata da chiave
   procedure set_raggruppamento
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.raggruppamento%type default null
   );
-- Setter per attributo stato_appartenenza di riga identificata da chiave
   procedure set_stato_appartenenza
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.stato_appartenenza%type default null
   );
-- Setter per attributo utente_aggiornamento di riga identificata da chiave
   procedure set_utente_aggiornamento
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.utente_aggiornamento%type default null
   );
-- Setter per attributo data_aggiornamento di riga identificata da chiave
   procedure set_data_aggiornamento
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.data_aggiornamento%type default null
   );
-- Setter per attributo sigla_iso3166_alpha2 di riga identificata da chiave
   procedure set_sigla_iso3166_alpha2
   (
     p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
   , p_value  in STATI_TERRITORI.sigla_iso3166_alpha2%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_stato_territorio  in varchar2 default null
   , p_denominazione  in varchar2 default null
   , p_denominazione_al1  in varchar2 default null
   , p_denominazione_al2  in varchar2 default null
   , p_sigla  in varchar2 default null
   , p_desc_cittadinanza  in varchar2 default null
   , p_desc_cittadinanza_al1  in varchar2 default null
   , p_desc_cittadinanza_al2  in varchar2 default null
   , p_raggruppamento  in varchar2 default null
   , p_stato_appartenenza  in varchar2 default null
   , p_utente_aggiornamento  in varchar2 default null
   , p_data_aggiornamento  in varchar2 default null
   , p_sigla_iso3166_alpha2  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_stato_territorio  in varchar2 default null
   , p_denominazione  in varchar2 default null
   , p_denominazione_al1  in varchar2 default null
   , p_denominazione_al2  in varchar2 default null
   , p_sigla  in varchar2 default null
   , p_desc_cittadinanza  in varchar2 default null
   , p_desc_cittadinanza_al1  in varchar2 default null
   , p_desc_cittadinanza_al2  in varchar2 default null
   , p_raggruppamento  in varchar2 default null
   , p_stato_appartenenza  in varchar2 default null
   , p_utente_aggiornamento  in varchar2 default null
   , p_data_aggiornamento  in varchar2 default null
   , p_sigla_iso3166_alpha2  in varchar2 default null
   ) return integer;
end stati_territori_tpk;
/

