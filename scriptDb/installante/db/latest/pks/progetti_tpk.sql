--liquibase formatted sql

--changeset mturra:201901301231_260 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE progetti_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        progetti_tpk
 DESCRIZIONE: Gestione tabella PROGETTI.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    11/05/2009  mmalferrari  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'PROGETTI';
   subtype t_rowtype is PROGETTI%rowtype;
   -- Tipo del record primary key
   subtype t_progetto  is PROGETTI.progetto%type;
   type t_PK is record
   (
    progetto  t_progetto
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_progetto  in PROGETTI.progetto%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_progetto  in PROGETTI.progetto%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_progetto  in PROGETTI.progetto%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_progetto  in PROGETTI.progetto%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_progetto  in PROGETTI.progetto%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_progetto  in PROGETTI.progetto%type
   , p_descrizione  in PROGETTI.descrizione%type default null
   , p_priorita  in PROGETTI.priorita%type default null
   , p_note  in PROGETTI.note%type default null
   , p_descrizione_al1  in PROGETTI.descrizione_al1%type default null
   , p_descrizione_al2  in PROGETTI.descrizione_al2%type default null
   );
   function ins  /*+ SOA  */
   (
     p_progetto  in PROGETTI.progetto%type
   , p_descrizione  in PROGETTI.descrizione%type default null
   , p_priorita  in PROGETTI.priorita%type default null
   , p_note  in PROGETTI.note%type default null
   , p_descrizione_al1  in PROGETTI.descrizione_al1%type default null
   , p_descrizione_al2  in PROGETTI.descrizione_al2%type default null
   ) return integer;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_progetto  in PROGETTI.progetto%type
   , p_OLD_progetto  in PROGETTI.progetto%type default null
   , p_NEW_descrizione  in PROGETTI.descrizione%type default null
   , p_OLD_descrizione  in PROGETTI.descrizione%type default null
   , p_NEW_priorita  in PROGETTI.priorita%type default null
   , p_OLD_priorita  in PROGETTI.priorita%type default null
   , p_NEW_note  in PROGETTI.note%type default null
   , p_OLD_note  in PROGETTI.note%type default null
   , p_NEW_descrizione_al1  in PROGETTI.descrizione_al1%type default null
   , p_OLD_descrizione_al1  in PROGETTI.descrizione_al1%type default null
   , p_NEW_descrizione_al2  in PROGETTI.descrizione_al2%type default null
   , p_OLD_descrizione_al2  in PROGETTI.descrizione_al2%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_progetto  in PROGETTI.progetto%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_progetto  in PROGETTI.progetto%type
   , p_descrizione  in PROGETTI.descrizione%type default null
   , p_priorita  in PROGETTI.priorita%type default null
   , p_note  in PROGETTI.note%type default null
   , p_descrizione_al1  in PROGETTI.descrizione_al1%type default null
   , p_descrizione_al2  in PROGETTI.descrizione_al2%type default null
   );
   -- Getter per attributo descrizione di riga identificata da chiave
   function get_descrizione /* SLAVE_COPY */
   (
     p_progetto  in PROGETTI.progetto%type
   ) return PROGETTI.descrizione%type;
   pragma restrict_references( get_descrizione, WNDS );
   -- Getter per attributo priorita di riga identificata da chiave
   function get_priorita /* SLAVE_COPY */
   (
     p_progetto  in PROGETTI.progetto%type
   ) return PROGETTI.priorita%type;
   pragma restrict_references( get_priorita, WNDS );
   -- Getter per attributo note di riga identificata da chiave
   function get_note /* SLAVE_COPY */
   (
     p_progetto  in PROGETTI.progetto%type
   ) return PROGETTI.note%type;
   pragma restrict_references( get_note, WNDS );
   -- Getter per attributo descrizione_al1 di riga identificata da chiave
   function get_descrizione_al1 /* SLAVE_COPY */
   (
     p_progetto  in PROGETTI.progetto%type
   ) return PROGETTI.descrizione_al1%type;
   pragma restrict_references( get_descrizione_al1, WNDS );
   -- Getter per attributo descrizione_al2 di riga identificata da chiave
   function get_descrizione_al2 /* SLAVE_COPY */
   (
     p_progetto  in PROGETTI.progetto%type
   ) return PROGETTI.descrizione_al2%type;
   pragma restrict_references( get_descrizione_al2, WNDS );
   -- Setter per attributo progetto di riga identificata da chiave
   procedure set_progetto
   (
     p_progetto  in PROGETTI.progetto%type
   , p_value  in PROGETTI.progetto%type default null
   );
   -- Setter per attributo descrizione di riga identificata da chiave
   procedure set_descrizione
   (
     p_progetto  in PROGETTI.progetto%type
   , p_value  in PROGETTI.descrizione%type default null
   );
   -- Setter per attributo priorita di riga identificata da chiave
   procedure set_priorita
   (
     p_progetto  in PROGETTI.progetto%type
   , p_value  in PROGETTI.priorita%type default null
   );
   -- Setter per attributo note di riga identificata da chiave
   procedure set_note
   (
     p_progetto  in PROGETTI.progetto%type
   , p_value  in PROGETTI.note%type default null
   );
   -- Setter per attributo descrizione_al1 di riga identificata da chiave
   procedure set_descrizione_al1
   (
     p_progetto  in PROGETTI.progetto%type
   , p_value  in PROGETTI.descrizione_al1%type default null
   );
   -- Setter per attributo descrizione_al2 di riga identificata da chiave
   procedure set_descrizione_al2
   (
     p_progetto  in PROGETTI.progetto%type
   , p_value  in PROGETTI.descrizione_al2%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_priorita  in varchar2 default null
   , p_note  in varchar2 default null
   , p_descrizione_al1  in varchar2 default null
   , p_descrizione_al2  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_priorita  in varchar2 default null
   , p_note  in varchar2 default null
   , p_descrizione_al1  in varchar2 default null
   , p_descrizione_al2  in varchar2 default null
   ) return integer;
end progetti_tpk;
/

