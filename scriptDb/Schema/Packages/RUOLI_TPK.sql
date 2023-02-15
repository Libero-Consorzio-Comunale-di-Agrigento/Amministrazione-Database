CREATE OR REPLACE PACKAGE ruoli_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        ruoli_tpk
 DESCRIZIONE: Gestione tabella RUOLI.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    28/12/2009  snegroni  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'RUOLI';
   subtype t_rowtype is RUOLI%rowtype;
   -- Tipo del record primary key
subtype t_ruolo  is RUOLI.ruolo%type;
   type t_PK is record
   (
ruolo  t_ruolo
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_ruolo  in RUOLI.ruolo%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_ruolo  in RUOLI.ruolo%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_ruolo  in RUOLI.ruolo%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_ruolo  in RUOLI.ruolo%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_ruolo  in RUOLI.ruolo%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_descrizione  in RUOLI.descrizione%type
   , p_progetto  in RUOLI.progetto%type default null
   , p_modulo  in RUOLI.modulo%type default null
   , p_profilo  in RUOLI.profilo%type default null
   , p_descrizione_al1  in RUOLI.descrizione_al1%type default null
   , p_descrizione_al2  in RUOLI.descrizione_al2%type default null
   , p_stato  in RUOLI.stato%type default 'U'
   , p_gruppo_lavoro  in RUOLI.gruppo_lavoro%type default 'N'
   , p_gruppo_so  in RUOLI.gruppo_so%type default 'N'
   , p_incarico  in RUOLI.incarico%type default 'N'
   , p_responsabilita  in RUOLI.responsabilita%type default 'N'
   );
   function ins  /*+ SOA  */
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_descrizione  in RUOLI.descrizione%type
   , p_progetto  in RUOLI.progetto%type default null
   , p_modulo  in RUOLI.modulo%type default null
   , p_profilo  in RUOLI.profilo%type default null
   , p_descrizione_al1  in RUOLI.descrizione_al1%type default null
   , p_descrizione_al2  in RUOLI.descrizione_al2%type default null
   , p_stato  in RUOLI.stato%type default 'U'
   , p_gruppo_lavoro  in RUOLI.gruppo_lavoro%type default 'N'
   , p_gruppo_so  in RUOLI.gruppo_so%type default 'N'
   , p_incarico  in RUOLI.incarico%type default 'N'
   , p_responsabilita  in RUOLI.responsabilita%type default 'N'
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_ruolo  in RUOLI.ruolo%type
   , p_OLD_ruolo  in RUOLI.ruolo%type default null
   , p_NEW_descrizione  in RUOLI.descrizione%type default afc.default_null('RUOLI.descrizione')
   , p_OLD_descrizione  in RUOLI.descrizione%type default null
   , p_NEW_progetto  in RUOLI.progetto%type default afc.default_null('RUOLI.progetto')
   , p_OLD_progetto  in RUOLI.progetto%type default null
   , p_NEW_modulo  in RUOLI.modulo%type default afc.default_null('RUOLI.modulo')
   , p_OLD_modulo  in RUOLI.modulo%type default null
   , p_NEW_profilo  in RUOLI.profilo%type default afc.default_null('RUOLI.profilo')
   , p_OLD_profilo  in RUOLI.profilo%type default null
   , p_NEW_descrizione_al1  in RUOLI.descrizione_al1%type default afc.default_null('RUOLI.descrizione_al1')
   , p_OLD_descrizione_al1  in RUOLI.descrizione_al1%type default null
   , p_NEW_descrizione_al2  in RUOLI.descrizione_al2%type default afc.default_null('RUOLI.descrizione_al2')
   , p_OLD_descrizione_al2  in RUOLI.descrizione_al2%type default null
   , p_NEW_stato  in RUOLI.stato%type default afc.default_null('RUOLI.stato')
   , p_OLD_stato  in RUOLI.stato%type default null
   , p_NEW_gruppo_lavoro  in RUOLI.gruppo_lavoro%type default afc.default_null('RUOLI.gruppo_lavoro')
   , p_OLD_gruppo_lavoro  in RUOLI.gruppo_lavoro%type default null
   , p_NEW_gruppo_so  in RUOLI.gruppo_so%type default afc.default_null('RUOLI.gruppo_so')
   , p_OLD_gruppo_so  in RUOLI.gruppo_so%type default null
   , p_NEW_incarico  in RUOLI.incarico%type default afc.default_null('RUOLI.incarico')
   , p_OLD_incarico  in RUOLI.incarico%type default null
   , p_NEW_responsabilita  in RUOLI.responsabilita%type default afc.default_null('RUOLI.responsabilita')
   , p_OLD_responsabilita  in RUOLI.responsabilita%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_ruolo  in RUOLI.ruolo%type
   , p_descrizione  in RUOLI.descrizione%type default null
   , p_progetto  in RUOLI.progetto%type default null
   , p_modulo  in RUOLI.modulo%type default null
   , p_profilo  in RUOLI.profilo%type default null
   , p_descrizione_al1  in RUOLI.descrizione_al1%type default null
   , p_descrizione_al2  in RUOLI.descrizione_al2%type default null
   , p_stato  in RUOLI.stato%type default null
   , p_gruppo_lavoro  in RUOLI.gruppo_lavoro%type default null
   , p_gruppo_so  in RUOLI.gruppo_so%type default null
   , p_incarico  in RUOLI.incarico%type default null
   , p_responsabilita  in RUOLI.responsabilita%type default null
   );
-- Getter per attributo descrizione di riga identificata da chiave
   function get_descrizione /* SLAVE_COPY */
   (
     p_ruolo  in RUOLI.ruolo%type
   ) return RUOLI.descrizione%type;
   pragma restrict_references( get_descrizione, WNDS );
-- Getter per attributo progetto di riga identificata da chiave
   function get_progetto /* SLAVE_COPY */
   (
     p_ruolo  in RUOLI.ruolo%type
   ) return RUOLI.progetto%type;
   pragma restrict_references( get_progetto, WNDS );
-- Getter per attributo modulo di riga identificata da chiave
   function get_modulo /* SLAVE_COPY */
   (
     p_ruolo  in RUOLI.ruolo%type
   ) return RUOLI.modulo%type;
   pragma restrict_references( get_modulo, WNDS );
-- Getter per attributo profilo di riga identificata da chiave
   function get_profilo /* SLAVE_COPY */
   (
     p_ruolo  in RUOLI.ruolo%type
   ) return RUOLI.profilo%type;
   pragma restrict_references( get_profilo, WNDS );
-- Getter per attributo descrizione_al1 di riga identificata da chiave
   function get_descrizione_al1 /* SLAVE_COPY */
   (
     p_ruolo  in RUOLI.ruolo%type
   ) return RUOLI.descrizione_al1%type;
   pragma restrict_references( get_descrizione_al1, WNDS );
-- Getter per attributo descrizione_al2 di riga identificata da chiave
   function get_descrizione_al2 /* SLAVE_COPY */
   (
     p_ruolo  in RUOLI.ruolo%type
   ) return RUOLI.descrizione_al2%type;
   pragma restrict_references( get_descrizione_al2, WNDS );
-- Getter per attributo stato di riga identificata da chiave
   function get_stato /* SLAVE_COPY */
   (
     p_ruolo  in RUOLI.ruolo%type
   ) return RUOLI.stato%type;
   pragma restrict_references( get_stato, WNDS );
-- Getter per attributo gruppo_lavoro di riga identificata da chiave
   function get_gruppo_lavoro /* SLAVE_COPY */
   (
     p_ruolo  in RUOLI.ruolo%type
   ) return RUOLI.gruppo_lavoro%type;
   pragma restrict_references( get_gruppo_lavoro, WNDS );
-- Getter per attributo gruppo_so di riga identificata da chiave
   function get_gruppo_so /* SLAVE_COPY */
   (
     p_ruolo  in RUOLI.ruolo%type
   ) return RUOLI.gruppo_so%type;
   pragma restrict_references( get_gruppo_so, WNDS );
-- Getter per attributo incarico di riga identificata da chiave
   function get_incarico /* SLAVE_COPY */
   (
     p_ruolo  in RUOLI.ruolo%type
   ) return RUOLI.incarico%type;
   pragma restrict_references( get_incarico, WNDS );
-- Getter per attributo responsabilita di riga identificata da chiave
   function get_responsabilita /* SLAVE_COPY */
   (
     p_ruolo  in RUOLI.ruolo%type
   ) return RUOLI.responsabilita%type;
   pragma restrict_references( get_responsabilita, WNDS );
-- Setter per attributo ruolo di riga identificata da chiave
   procedure set_ruolo
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.ruolo%type default null
   );
-- Setter per attributo descrizione di riga identificata da chiave
   procedure set_descrizione
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.descrizione%type default null
   );
-- Setter per attributo progetto di riga identificata da chiave
   procedure set_progetto
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.progetto%type default null
   );
-- Setter per attributo modulo di riga identificata da chiave
   procedure set_modulo
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.modulo%type default null
   );
-- Setter per attributo profilo di riga identificata da chiave
   procedure set_profilo
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.profilo%type default null
   );
-- Setter per attributo descrizione_al1 di riga identificata da chiave
   procedure set_descrizione_al1
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.descrizione_al1%type default null
   );
-- Setter per attributo descrizione_al2 di riga identificata da chiave
   procedure set_descrizione_al2
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.descrizione_al2%type default null
   );
-- Setter per attributo stato di riga identificata da chiave
   procedure set_stato
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.stato%type default null
   );
-- Setter per attributo gruppo_lavoro di riga identificata da chiave
   procedure set_gruppo_lavoro
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.gruppo_lavoro%type default null
   );
-- Setter per attributo gruppo_so di riga identificata da chiave
   procedure set_gruppo_so
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.gruppo_so%type default null
   );
-- Setter per attributo incarico di riga identificata da chiave
   procedure set_incarico
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.incarico%type default null
   );
-- Setter per attributo responsabilita di riga identificata da chiave
   procedure set_responsabilita
   (
     p_ruolo  in RUOLI.ruolo%type
   , p_value  in RUOLI.responsabilita%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_ruolo  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_profilo  in varchar2 default null
   , p_descrizione_al1  in varchar2 default null
   , p_descrizione_al2  in varchar2 default null
   , p_stato  in varchar2 default null
   , p_gruppo_lavoro  in varchar2 default null
   , p_gruppo_so  in varchar2 default null
   , p_incarico  in varchar2 default null
   , p_responsabilita  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_ruolo  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_profilo  in varchar2 default null
   , p_descrizione_al1  in varchar2 default null
   , p_descrizione_al2  in varchar2 default null
   , p_stato  in varchar2 default null
   , p_gruppo_lavoro  in varchar2 default null
   , p_gruppo_so  in varchar2 default null
   , p_incarico  in varchar2 default null
   , p_responsabilita  in varchar2 default null
   ) return integer;
end ruoli_tpk;
/

