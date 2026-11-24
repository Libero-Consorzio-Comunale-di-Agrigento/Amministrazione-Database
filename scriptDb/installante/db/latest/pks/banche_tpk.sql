--liquibase formatted sql

--changeset mturra:201901301231_219 runOnChange:true stripComments:false endDelimiter:/


create or replace package banche_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        banche_tpk
 DESCRIZIONE: Gestione tabella BANCHE.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    30/08/2016  snegroni  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'BANCHE';
   subtype t_rowtype is BANCHE%rowtype;
   -- Tipo del record primary key
subtype t_abi  is BANCHE.abi%type;
   type t_PK is record
   (
abi  t_abi
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_abi  in BANCHE.abi%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrità chiave
   function can_handle /* SLAVE_COPY */
   (
    p_abi  in BANCHE.abi%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrità chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_abi  in BANCHE.abi%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_abi  in BANCHE.abi%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_abi  in BANCHE.abi%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_abi  in BANCHE.abi%type
   , p_cin_abi  in BANCHE.cin_abi%type
   , p_denominazione  in BANCHE.denominazione%type
   );
   function ins  /*+ SOA  */
   (
     p_abi  in BANCHE.abi%type
   , p_cin_abi  in BANCHE.cin_abi%type
   , p_denominazione  in BANCHE.denominazione%type
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_abi  in BANCHE.abi%type
   , p_OLD_abi  in BANCHE.abi%type default null
   , p_NEW_cin_abi  in BANCHE.cin_abi%type default afc.default_null('BANCHE.cin_abi')
   , p_OLD_cin_abi  in BANCHE.cin_abi%type default null
   , p_NEW_denominazione  in BANCHE.denominazione%type default afc.default_null('BANCHE.denominazione')
   , p_OLD_denominazione  in BANCHE.denominazione%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_abi  in BANCHE.abi%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_abi  in BANCHE.abi%type
   , p_cin_abi  in BANCHE.cin_abi%type default null
   , p_denominazione  in BANCHE.denominazione%type default null
   );
-- Getter per attributo cin_abi di riga identificata da chiave
   function get_cin_abi /* SLAVE_COPY */
   (
     p_abi  in BANCHE.abi%type
   ) return BANCHE.cin_abi%type;
   pragma restrict_references( get_cin_abi, WNDS );
-- Getter per attributo denominazione di riga identificata da chiave
   function get_denominazione /* SLAVE_COPY */
   (
     p_abi  in BANCHE.abi%type
   ) return BANCHE.denominazione%type;
   pragma restrict_references( get_denominazione, WNDS );
-- Setter per attributo abi di riga identificata da chiave
   procedure set_abi
   (
     p_abi  in BANCHE.abi%type
   , p_value  in BANCHE.abi%type default null
   );
-- Setter per attributo cin_abi di riga identificata da chiave
   procedure set_cin_abi
   (
     p_abi  in BANCHE.abi%type
   , p_value  in BANCHE.cin_abi%type default null
   );
-- Setter per attributo denominazione di riga identificata da chiave
   procedure set_denominazione
   (
     p_abi  in BANCHE.abi%type
   , p_value  in BANCHE.denominazione%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_abi  in varchar2 default null
   , p_cin_abi  in varchar2 default null
   , p_denominazione  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_abi  in varchar2 default null
   , p_cin_abi  in varchar2 default null
   , p_denominazione  in varchar2 default null
   ) return integer;
end banche_tpk;
/

