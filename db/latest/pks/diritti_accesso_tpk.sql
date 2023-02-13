--liquibase formatted sql

--changeset mturra:201901301231_231 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE diritti_accesso_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        diritti_accesso_tpk
 DESCRIZIONE: Gestione tabella DIRITTI_ACCESSO.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    11/05/2009  mmalferrari  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'DIRITTI_ACCESSO';
   subtype t_rowtype is DIRITTI_ACCESSO%rowtype;
   -- Tipo del record primary key
   subtype t_utente  is DIRITTI_ACCESSO.utente%type;
subtype t_modulo  is DIRITTI_ACCESSO.modulo%type;
subtype t_istanza  is DIRITTI_ACCESSO.istanza%type;
   type t_PK is record
   (
    utente  t_utente,
modulo  t_modulo,
istanza  t_istanza
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_ruolo  in DIRITTI_ACCESSO.ruolo%type
   , p_sequenza  in DIRITTI_ACCESSO.sequenza%type default null
   , p_ultimo_accesso  in DIRITTI_ACCESSO.ultimo_accesso%type default null
   , p_numero_accessi  in DIRITTI_ACCESSO.numero_accessi%type default null
   , p_gruppo  in DIRITTI_ACCESSO.gruppo%type default null
   , p_note  in DIRITTI_ACCESSO.note%type default null
   );
   function ins  /*+ SOA  */
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_ruolo  in DIRITTI_ACCESSO.ruolo%type
   , p_sequenza  in DIRITTI_ACCESSO.sequenza%type default null
   , p_ultimo_accesso  in DIRITTI_ACCESSO.ultimo_accesso%type default null
   , p_numero_accessi  in DIRITTI_ACCESSO.numero_accessi%type default null
   , p_gruppo  in DIRITTI_ACCESSO.gruppo%type default null
   , p_note  in DIRITTI_ACCESSO.note%type default null
   ) return integer;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_utente  in DIRITTI_ACCESSO.utente%type
   , p_OLD_utente  in DIRITTI_ACCESSO.utente%type default null
, p_NEW_modulo  in DIRITTI_ACCESSO.modulo%type
, p_OLD_modulo  in DIRITTI_ACCESSO.modulo%type default null
, p_NEW_istanza  in DIRITTI_ACCESSO.istanza%type
, p_OLD_istanza  in DIRITTI_ACCESSO.istanza%type default null
   , p_NEW_ruolo  in DIRITTI_ACCESSO.ruolo%type default null
   , p_OLD_ruolo  in DIRITTI_ACCESSO.ruolo%type default null
   , p_NEW_sequenza  in DIRITTI_ACCESSO.sequenza%type default null
   , p_OLD_sequenza  in DIRITTI_ACCESSO.sequenza%type default null
   , p_NEW_ultimo_accesso  in DIRITTI_ACCESSO.ultimo_accesso%type default null
   , p_OLD_ultimo_accesso  in DIRITTI_ACCESSO.ultimo_accesso%type default null
   , p_NEW_numero_accessi  in DIRITTI_ACCESSO.numero_accessi%type default null
   , p_OLD_numero_accessi  in DIRITTI_ACCESSO.numero_accessi%type default null
   , p_NEW_gruppo  in DIRITTI_ACCESSO.gruppo%type default null
   , p_OLD_gruppo  in DIRITTI_ACCESSO.gruppo%type default null
   , p_NEW_note  in DIRITTI_ACCESSO.note%type default null
   , p_OLD_note  in DIRITTI_ACCESSO.note%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   procedure upd_column
   (
p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_column in varchar2
   , p_value  in date
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_utente  in DIRITTI_ACCESSO.utente%type
, p_modulo  in DIRITTI_ACCESSO.modulo%type
, p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_ruolo  in DIRITTI_ACCESSO.ruolo%type default null
   , p_sequenza  in DIRITTI_ACCESSO.sequenza%type default null
   , p_ultimo_accesso  in DIRITTI_ACCESSO.ultimo_accesso%type default null
   , p_numero_accessi  in DIRITTI_ACCESSO.numero_accessi%type default null
   , p_gruppo  in DIRITTI_ACCESSO.gruppo%type default null
   , p_note  in DIRITTI_ACCESSO.note%type default null
   );
   -- Getter per attributo ruolo di riga identificata da chiave
   function get_ruolo /* SLAVE_COPY */
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   ) return DIRITTI_ACCESSO.ruolo%type;
   pragma restrict_references( get_ruolo, WNDS );
   -- Getter per attributo sequenza di riga identificata da chiave
   function get_sequenza /* SLAVE_COPY */
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   ) return DIRITTI_ACCESSO.sequenza%type;
   pragma restrict_references( get_sequenza, WNDS );
   -- Getter per attributo ultimo_accesso di riga identificata da chiave
   function get_ultimo_accesso /* SLAVE_COPY */
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   ) return DIRITTI_ACCESSO.ultimo_accesso%type;
   pragma restrict_references( get_ultimo_accesso, WNDS );
   -- Getter per attributo numero_accessi di riga identificata da chiave
   function get_numero_accessi /* SLAVE_COPY */
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   ) return DIRITTI_ACCESSO.numero_accessi%type;
   pragma restrict_references( get_numero_accessi, WNDS );
   -- Getter per attributo gruppo di riga identificata da chiave
   function get_gruppo /* SLAVE_COPY */
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   ) return DIRITTI_ACCESSO.gruppo%type;
   pragma restrict_references( get_gruppo, WNDS );
   -- Getter per attributo note di riga identificata da chiave
   function get_note /* SLAVE_COPY */
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   ) return DIRITTI_ACCESSO.note%type;
   pragma restrict_references( get_note, WNDS );
   -- Setter per attributo utente di riga identificata da chiave
   procedure set_utente
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_value  in DIRITTI_ACCESSO.utente%type default null
   );
-- Setter per attributo modulo di riga identificata da chiave
   procedure set_modulo
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_value  in DIRITTI_ACCESSO.modulo%type default null
);
-- Setter per attributo istanza di riga identificata da chiave
   procedure set_istanza
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_value  in DIRITTI_ACCESSO.istanza%type default null
);
   -- Setter per attributo ruolo di riga identificata da chiave
   procedure set_ruolo
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_value  in DIRITTI_ACCESSO.ruolo%type default null
   );
   -- Setter per attributo sequenza di riga identificata da chiave
   procedure set_sequenza
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_value  in DIRITTI_ACCESSO.sequenza%type default null
   );
   -- Setter per attributo ultimo_accesso di riga identificata da chiave
   procedure set_ultimo_accesso
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_value  in DIRITTI_ACCESSO.ultimo_accesso%type default null
   );
   -- Setter per attributo numero_accessi di riga identificata da chiave
   procedure set_numero_accessi
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_value  in DIRITTI_ACCESSO.numero_accessi%type default null
   );
   -- Setter per attributo gruppo di riga identificata da chiave
   procedure set_gruppo
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_value  in DIRITTI_ACCESSO.gruppo%type default null
   );
   -- Setter per attributo note di riga identificata da chiave
   procedure set_note
   (
     p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
   , p_value  in DIRITTI_ACCESSO.note%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_utente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_istanza  in varchar2 default null
   , p_ruolo  in varchar2 default null
   , p_sequenza  in varchar2 default null
   , p_ultimo_accesso  in varchar2 default null
   , p_numero_accessi  in varchar2 default null
   , p_gruppo  in varchar2 default null
   , p_note  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_utente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_istanza  in varchar2 default null
   , p_ruolo  in varchar2 default null
   , p_sequenza  in varchar2 default null
   , p_ultimo_accesso  in varchar2 default null
   , p_numero_accessi  in varchar2 default null
   , p_gruppo  in varchar2 default null
   , p_note  in varchar2 default null
   ) return integer;
end diritti_accesso_tpk;
/

