--liquibase formatted sql

--changeset mturra:201901301231_255 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE package moduli_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        moduli_tpk
 DESCRIZIONE: Gestione tabella MODULI.
 ANNOTAZIONI: .
 REVISIONI:   Table Revision: 28/09/2018 10:10:25
              SiaPKGen Revision: V1.05.014.
              SiaTPKDeclare Revision: V1.17.001.
 <CODE>
 Rev.  Data        Autore      Descrizione.
 00    11/05/2009  mmalferrari  Prima emissione.
 01    15/11/2018  snegroni  Generazione automatica.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.01';
   s_table_name constant AFC.t_object_name := 'MODULI';
   subtype t_rowtype is MODULI%rowtype;
   -- Tipo del record primary key
subtype t_modulo  is MODULI.modulo%type;
   type t_PK is record
   (
modulo  t_modulo
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_modulo  in MODULI.modulo%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_modulo  in MODULI.modulo%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_modulo  in MODULI.modulo%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_modulo  in MODULI.modulo%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_modulo  in MODULI.modulo%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins
   (
     p_modulo  in MODULI.modulo%type
   , p_descrizione  in MODULI.descrizione%type
   , p_descrizione_al1  in MODULI.descrizione_al1%type default null
   , p_descrizione_al2  in MODULI.descrizione_al2%type default null
   , p_progetto  in MODULI.progetto%type
   , p_note  in MODULI.note%type default null
   , p_amministratore  in MODULI.amministratore%type default 'N'
   );
   function ins  /*+ SOA  */
   (
     p_modulo  in MODULI.modulo%type
   , p_descrizione  in MODULI.descrizione%type
   , p_descrizione_al1  in MODULI.descrizione_al1%type default null
   , p_descrizione_al2  in MODULI.descrizione_al2%type default null
   , p_progetto  in MODULI.progetto%type
   , p_note  in MODULI.note%type default null
   , p_amministratore  in MODULI.amministratore%type default 'N'
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_modulo  in MODULI.modulo%type
   , p_OLD_modulo  in MODULI.modulo%type default null
   , p_NEW_descrizione  in MODULI.descrizione%type default afc.default_null('MODULI.descrizione')
   , p_OLD_descrizione  in MODULI.descrizione%type default null
   , p_NEW_descrizione_al1  in MODULI.descrizione_al1%type default afc.default_null('MODULI.descrizione_al1')
   , p_OLD_descrizione_al1  in MODULI.descrizione_al1%type default null
   , p_NEW_descrizione_al2  in MODULI.descrizione_al2%type default afc.default_null('MODULI.descrizione_al2')
   , p_OLD_descrizione_al2  in MODULI.descrizione_al2%type default null
   , p_NEW_progetto  in MODULI.progetto%type default afc.default_null('MODULI.progetto')
   , p_OLD_progetto  in MODULI.progetto%type default null
   , p_NEW_note  in MODULI.note%type default afc.default_null('MODULI.note')
   , p_OLD_note  in MODULI.note%type default null
   , p_NEW_amministratore  in MODULI.amministratore%type default afc.default_null('MODULI.amministratore')
   , p_OLD_amministratore  in MODULI.amministratore%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_modulo  in MODULI.modulo%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_modulo  in MODULI.modulo%type
   , p_descrizione  in MODULI.descrizione%type default null
   , p_descrizione_al1  in MODULI.descrizione_al1%type default null
   , p_descrizione_al2  in MODULI.descrizione_al2%type default null
   , p_progetto  in MODULI.progetto%type default null
   , p_note  in MODULI.note%type default null
   , p_amministratore  in MODULI.amministratore%type default null
   );
-- Getter per attributo descrizione di riga identificata da chiave
   function get_descrizione /* SLAVE_COPY */
   (
     p_modulo  in MODULI.modulo%type
   ) return MODULI.descrizione%type;
   pragma restrict_references( get_descrizione, WNDS );
-- Getter per attributo descrizione_al1 di riga identificata da chiave
   function get_descrizione_al1 /* SLAVE_COPY */
   (
     p_modulo  in MODULI.modulo%type
   ) return MODULI.descrizione_al1%type;
   pragma restrict_references( get_descrizione_al1, WNDS );
-- Getter per attributo descrizione_al2 di riga identificata da chiave
   function get_descrizione_al2 /* SLAVE_COPY */
   (
     p_modulo  in MODULI.modulo%type
   ) return MODULI.descrizione_al2%type;
   pragma restrict_references( get_descrizione_al2, WNDS );
-- Getter per attributo progetto di riga identificata da chiave
   function get_progetto /* SLAVE_COPY */
   (
     p_modulo  in MODULI.modulo%type
   ) return MODULI.progetto%type;
   pragma restrict_references( get_progetto, WNDS );
-- Getter per attributo note di riga identificata da chiave
   function get_note /* SLAVE_COPY */
   (
     p_modulo  in MODULI.modulo%type
   ) return MODULI.note%type;
   pragma restrict_references( get_note, WNDS );
-- Getter per attributo amministratore di riga identificata da chiave
   function get_amministratore /* SLAVE_COPY */
   (
     p_modulo  in MODULI.modulo%type
   ) return MODULI.amministratore%type;
   pragma restrict_references( get_amministratore, WNDS );
-- Setter per attributo modulo di riga identificata da chiave
   procedure set_modulo
   (
     p_modulo  in MODULI.modulo%type
   , p_value  in MODULI.modulo%type default null
   );
-- Setter per attributo descrizione di riga identificata da chiave
   procedure set_descrizione
   (
     p_modulo  in MODULI.modulo%type
   , p_value  in MODULI.descrizione%type default null
   );
-- Setter per attributo descrizione_al1 di riga identificata da chiave
   procedure set_descrizione_al1
   (
     p_modulo  in MODULI.modulo%type
   , p_value  in MODULI.descrizione_al1%type default null
   );
-- Setter per attributo descrizione_al2 di riga identificata da chiave
   procedure set_descrizione_al2
   (
     p_modulo  in MODULI.modulo%type
   , p_value  in MODULI.descrizione_al2%type default null
   );
-- Setter per attributo progetto di riga identificata da chiave
   procedure set_progetto
   (
     p_modulo  in MODULI.modulo%type
   , p_value  in MODULI.progetto%type default null
   );
-- Setter per attributo note di riga identificata da chiave
   procedure set_note
   (
     p_modulo  in MODULI.modulo%type
   , p_value  in MODULI.note%type default null
   );
-- Setter per attributo amministratore di riga identificata da chiave
   procedure set_amministratore
   (
     p_modulo  in MODULI.modulo%type
   , p_value  in MODULI.amministratore%type default null
   );
   -- where_condition per statement di ricerca
   function where_condition /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_descrizione_al1  in varchar2 default null
   , p_descrizione_al2  in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_note  in varchar2 default null
   , p_amministratore  in varchar2 default null
   ) return AFC.t_statement;
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_descrizione_al1  in varchar2 default null
   , p_descrizione_al2  in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_note  in varchar2 default null
   , p_amministratore  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_descrizione_al1  in varchar2 default null
   , p_descrizione_al2  in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_note  in varchar2 default null
   , p_amministratore  in varchar2 default null
   ) return integer;
end moduli_tpk;
/
