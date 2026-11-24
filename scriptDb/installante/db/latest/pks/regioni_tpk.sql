--liquibase formatted sql

--changeset mturra:201901301231_263 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE regioni_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        regioni_tpk
 DESCRIZIONE: Gestione tabella REGIONI.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    11/05/2009  mmalferrari  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'REGIONI';
   subtype t_rowtype is REGIONI%rowtype;
   -- Tipo del record primary key
   subtype t_regione  is REGIONI.regione%type;
   type t_PK is record
   (
    regione  t_regione
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_regione  in REGIONI.regione%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_regione  in REGIONI.regione%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_regione  in REGIONI.regione%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_regione  in REGIONI.regione%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_regione  in REGIONI.regione%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_regione  in REGIONI.regione%type default null
   , p_denominazione  in REGIONI.denominazione%type default null
   , p_denominazione_al1  in REGIONI.denominazione_al1%type default null
   , p_denominazione_al2  in REGIONI.denominazione_al2%type default null
   , p_id_regione  in REGIONI.id_regione%type
   , p_utente_aggiornamento  in REGIONI.utente_aggiornamento%type default null
   , p_data_aggiornamento  in REGIONI.data_aggiornamento%type default null
   );
   function ins  /*+ SOA  */
   (
     p_regione  in REGIONI.regione%type default null
   , p_denominazione  in REGIONI.denominazione%type default null
   , p_denominazione_al1  in REGIONI.denominazione_al1%type default null
   , p_denominazione_al2  in REGIONI.denominazione_al2%type default null
   , p_id_regione  in REGIONI.id_regione%type
   , p_utente_aggiornamento  in REGIONI.utente_aggiornamento%type default null
   , p_data_aggiornamento  in REGIONI.data_aggiornamento%type default null
   ) return integer;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_regione  in REGIONI.regione%type
   , p_OLD_regione  in REGIONI.regione%type default null
   , p_NEW_denominazione  in REGIONI.denominazione%type default null
   , p_OLD_denominazione  in REGIONI.denominazione%type default null
   , p_NEW_denominazione_al1  in REGIONI.denominazione_al1%type default null
   , p_OLD_denominazione_al1  in REGIONI.denominazione_al1%type default null
   , p_NEW_denominazione_al2  in REGIONI.denominazione_al2%type default null
   , p_OLD_denominazione_al2  in REGIONI.denominazione_al2%type default null
   , p_NEW_id_regione  in REGIONI.id_regione%type default null
   , p_OLD_id_regione  in REGIONI.id_regione%type default null
   , p_NEW_utente_aggiornamento  in REGIONI.utente_aggiornamento%type default null
   , p_OLD_utente_aggiornamento  in REGIONI.utente_aggiornamento%type default null
   , p_NEW_data_aggiornamento  in REGIONI.data_aggiornamento%type default null
   , p_OLD_data_aggiornamento  in REGIONI.data_aggiornamento%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_regione  in REGIONI.regione%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   procedure upd_column
   (
p_regione  in REGIONI.regione%type
   , p_column in varchar2
   , p_value  in date
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_regione  in REGIONI.regione%type
   , p_denominazione  in REGIONI.denominazione%type default null
   , p_denominazione_al1  in REGIONI.denominazione_al1%type default null
   , p_denominazione_al2  in REGIONI.denominazione_al2%type default null
   , p_id_regione  in REGIONI.id_regione%type default null
   , p_utente_aggiornamento  in REGIONI.utente_aggiornamento%type default null
   , p_data_aggiornamento  in REGIONI.data_aggiornamento%type default null
   );
   -- Getter per attributo denominazione di riga identificata da chiave
   function get_denominazione /* SLAVE_COPY */
   (
     p_regione  in REGIONI.regione%type
   ) return REGIONI.denominazione%type;
   pragma restrict_references( get_denominazione, WNDS );
   -- Getter per attributo denominazione_al1 di riga identificata da chiave
   function get_denominazione_al1 /* SLAVE_COPY */
   (
     p_regione  in REGIONI.regione%type
   ) return REGIONI.denominazione_al1%type;
   pragma restrict_references( get_denominazione_al1, WNDS );
   -- Getter per attributo denominazione_al2 di riga identificata da chiave
   function get_denominazione_al2 /* SLAVE_COPY */
   (
     p_regione  in REGIONI.regione%type
   ) return REGIONI.denominazione_al2%type;
   pragma restrict_references( get_denominazione_al2, WNDS );
   -- Getter per attributo id_regione di riga identificata da chiave
   function get_id_regione /* SLAVE_COPY */
   (
     p_regione  in REGIONI.regione%type
   ) return REGIONI.id_regione%type;
   pragma restrict_references( get_id_regione, WNDS );
   -- Getter per attributo utente_aggiornamento di riga identificata da chiave
   function get_utente_aggiornamento /* SLAVE_COPY */
   (
     p_regione  in REGIONI.regione%type
   ) return REGIONI.utente_aggiornamento%type;
   pragma restrict_references( get_utente_aggiornamento, WNDS );
   -- Getter per attributo data_aggiornamento di riga identificata da chiave
   function get_data_aggiornamento /* SLAVE_COPY */
   (
     p_regione  in REGIONI.regione%type
   ) return REGIONI.data_aggiornamento%type;
   pragma restrict_references( get_data_aggiornamento, WNDS );
   -- Setter per attributo regione di riga identificata da chiave
   procedure set_regione
   (
     p_regione  in REGIONI.regione%type
   , p_value  in REGIONI.regione%type default null
   );
   -- Setter per attributo denominazione di riga identificata da chiave
   procedure set_denominazione
   (
     p_regione  in REGIONI.regione%type
   , p_value  in REGIONI.denominazione%type default null
   );
   -- Setter per attributo denominazione_al1 di riga identificata da chiave
   procedure set_denominazione_al1
   (
     p_regione  in REGIONI.regione%type
   , p_value  in REGIONI.denominazione_al1%type default null
   );
   -- Setter per attributo denominazione_al2 di riga identificata da chiave
   procedure set_denominazione_al2
   (
     p_regione  in REGIONI.regione%type
   , p_value  in REGIONI.denominazione_al2%type default null
   );
   -- Setter per attributo id_regione di riga identificata da chiave
   procedure set_id_regione
   (
     p_regione  in REGIONI.regione%type
   , p_value  in REGIONI.id_regione%type default null
   );
   -- Setter per attributo utente_aggiornamento di riga identificata da chiave
   procedure set_utente_aggiornamento
   (
     p_regione  in REGIONI.regione%type
   , p_value  in REGIONI.utente_aggiornamento%type default null
   );
   -- Setter per attributo data_aggiornamento di riga identificata da chiave
   procedure set_data_aggiornamento
   (
     p_regione  in REGIONI.regione%type
   , p_value  in REGIONI.data_aggiornamento%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_regione  in varchar2 default null
   , p_denominazione  in varchar2 default null
   , p_denominazione_al1  in varchar2 default null
   , p_denominazione_al2  in varchar2 default null
   , p_id_regione  in varchar2 default null
   , p_utente_aggiornamento  in varchar2 default null
   , p_data_aggiornamento  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_regione  in varchar2 default null
   , p_denominazione  in varchar2 default null
   , p_denominazione_al1  in varchar2 default null
   , p_denominazione_al2  in varchar2 default null
   , p_id_regione  in varchar2 default null
   , p_utente_aggiornamento  in varchar2 default null
   , p_data_aggiornamento  in varchar2 default null
   ) return integer;
end regioni_tpk;
/

