--liquibase formatted sql

--changeset mturra:201901301231_240 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE incarichi_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        incarichi_tpk
 DESCRIZIONE: Gestione tabella INCARICHI.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    12/02/2010  snegroni  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'INCARICHI';
   subtype t_rowtype is INCARICHI%rowtype;
   -- Tipo del record primary key
subtype t_incarico  is INCARICHI.incarico%type;
   type t_PK is record
   (
incarico  t_incarico
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_incarico  in INCARICHI.incarico%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_incarico  in INCARICHI.incarico%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_incarico  in INCARICHI.incarico%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_incarico  in INCARICHI.incarico%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_incarico  in INCARICHI.incarico%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_incarico  in INCARICHI.incarico%type
   , p_descrizione  in INCARICHI.descrizione%type
   , p_descrizione_al1  in INCARICHI.descrizione_al1%type default null
   , p_descrizione_al2  in INCARICHI.descrizione_al2%type default null
   , p_responsabilita  in INCARICHI.responsabilita%type default null
   );
   function ins  /*+ SOA  */
   (
     p_incarico  in INCARICHI.incarico%type
   , p_descrizione  in INCARICHI.descrizione%type
   , p_descrizione_al1  in INCARICHI.descrizione_al1%type default null
   , p_descrizione_al2  in INCARICHI.descrizione_al2%type default null
   , p_responsabilita  in INCARICHI.responsabilita%type default null
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_incarico  in INCARICHI.incarico%type
   , p_OLD_incarico  in INCARICHI.incarico%type default null
   , p_NEW_descrizione  in INCARICHI.descrizione%type default afc.default_null('INCARICHI.descrizione')
   , p_OLD_descrizione  in INCARICHI.descrizione%type default null
   , p_NEW_descrizione_al1  in INCARICHI.descrizione_al1%type default afc.default_null('INCARICHI.descrizione_al1')
   , p_OLD_descrizione_al1  in INCARICHI.descrizione_al1%type default null
   , p_NEW_descrizione_al2  in INCARICHI.descrizione_al2%type default afc.default_null('INCARICHI.descrizione_al2')
   , p_OLD_descrizione_al2  in INCARICHI.descrizione_al2%type default null
   , p_NEW_responsabilita  in INCARICHI.responsabilita%type default afc.default_null('INCARICHI.responsabilita')
   , p_OLD_responsabilita  in INCARICHI.responsabilita%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_incarico  in INCARICHI.incarico%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_incarico  in INCARICHI.incarico%type
   , p_descrizione  in INCARICHI.descrizione%type default null
   , p_descrizione_al1  in INCARICHI.descrizione_al1%type default null
   , p_descrizione_al2  in INCARICHI.descrizione_al2%type default null
   , p_responsabilita  in INCARICHI.responsabilita%type default null
   );
-- Getter per attributo descrizione di riga identificata da chiave
   function get_descrizione /* SLAVE_COPY */
   (
     p_incarico  in INCARICHI.incarico%type
   ) return INCARICHI.descrizione%type;
   pragma restrict_references( get_descrizione, WNDS );
-- Getter per attributo descrizione_al1 di riga identificata da chiave
   function get_descrizione_al1 /* SLAVE_COPY */
   (
     p_incarico  in INCARICHI.incarico%type
   ) return INCARICHI.descrizione_al1%type;
   pragma restrict_references( get_descrizione_al1, WNDS );
-- Getter per attributo descrizione_al2 di riga identificata da chiave
   function get_descrizione_al2 /* SLAVE_COPY */
   (
     p_incarico  in INCARICHI.incarico%type
   ) return INCARICHI.descrizione_al2%type;
   pragma restrict_references( get_descrizione_al2, WNDS );
-- Getter per attributo responsabilita di riga identificata da chiave
   function get_responsabilita /* SLAVE_COPY */
   (
     p_incarico  in INCARICHI.incarico%type
   ) return INCARICHI.responsabilita%type;
   pragma restrict_references( get_responsabilita, WNDS );
-- Setter per attributo incarico di riga identificata da chiave
   procedure set_incarico
   (
     p_incarico  in INCARICHI.incarico%type
   , p_value  in INCARICHI.incarico%type default null
   );
-- Setter per attributo descrizione di riga identificata da chiave
   procedure set_descrizione
   (
     p_incarico  in INCARICHI.incarico%type
   , p_value  in INCARICHI.descrizione%type default null
   );
-- Setter per attributo descrizione_al1 di riga identificata da chiave
   procedure set_descrizione_al1
   (
     p_incarico  in INCARICHI.incarico%type
   , p_value  in INCARICHI.descrizione_al1%type default null
   );
-- Setter per attributo descrizione_al2 di riga identificata da chiave
   procedure set_descrizione_al2
   (
     p_incarico  in INCARICHI.incarico%type
   , p_value  in INCARICHI.descrizione_al2%type default null
   );
-- Setter per attributo responsabilita di riga identificata da chiave
   procedure set_responsabilita
   (
     p_incarico  in INCARICHI.incarico%type
   , p_value  in INCARICHI.responsabilita%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_incarico  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_descrizione_al1  in varchar2 default null
   , p_descrizione_al2  in varchar2 default null
   , p_responsabilita  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_incarico  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_descrizione_al1  in varchar2 default null
   , p_descrizione_al2  in varchar2 default null
   , p_responsabilita  in varchar2 default null
   ) return integer;
end incarichi_tpk;
/

