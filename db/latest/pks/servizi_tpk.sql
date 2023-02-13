--liquibase formatted sql

--changeset mturra:201901301231_270 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE servizi_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        servizi_tpk
 DESCRIZIONE: Gestione tabella SERVIZI.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    11/05/2009  mmalferrari  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'SERVIZI';
   subtype t_rowtype is SERVIZI%rowtype;
   -- Tipo del record primary key
   subtype t_id_servizio  is SERVIZI.id_servizio%type;
   type t_PK is record
   (
    id_servizio  t_id_servizio
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_id_servizio  in SERVIZI.id_servizio%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_id_servizio  in SERVIZI.id_servizio%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_id_servizio  in SERVIZI.id_servizio%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_id_servizio  in SERVIZI.id_servizio%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_id_servizio  in SERVIZI.id_servizio%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_id_servizio  in SERVIZI.id_servizio%type default null
   , p_modulo  in SERVIZI.modulo%type
   , p_istanza  in SERVIZI.istanza%type
   , p_livello  in SERVIZI.livello%type default null
   , p_abilitazione  in SERVIZI.abilitazione%type default 'C'
   , p_gruppo_abilitazione  in SERVIZI.gruppo_abilitazione%type
   , p_mail_notifiche  in SERVIZI.mail_notifiche%type
   , p_ccr_notifiche  in SERVIZI.ccr_notifiche%type default null
   , p_multiplo  in SERVIZI.multiplo%type default 'N'
   , p_tipo_notifica  in SERVIZI.tipo_notifica%type default null
   , p_recupero_password  in SERVIZI.recupero_password%type default 'N'
   );
   function ins  /*+ SOA  */
   (
     p_id_servizio  in SERVIZI.id_servizio%type default null
   , p_modulo  in SERVIZI.modulo%type
   , p_istanza  in SERVIZI.istanza%type
   , p_livello  in SERVIZI.livello%type default null
   , p_abilitazione  in SERVIZI.abilitazione%type default 'C'
   , p_gruppo_abilitazione  in SERVIZI.gruppo_abilitazione%type
   , p_mail_notifiche  in SERVIZI.mail_notifiche%type
   , p_ccr_notifiche  in SERVIZI.ccr_notifiche%type default null
   , p_multiplo  in SERVIZI.multiplo%type default 'N'
   , p_tipo_notifica  in SERVIZI.tipo_notifica%type default null
   , p_recupero_password  in SERVIZI.recupero_password%type default 'N'
   ) return integer;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_id_servizio  in SERVIZI.id_servizio%type
   , p_OLD_id_servizio  in SERVIZI.id_servizio%type default null
   , p_NEW_modulo  in SERVIZI.modulo%type default null
   , p_OLD_modulo  in SERVIZI.modulo%type default null
   , p_NEW_istanza  in SERVIZI.istanza%type default null
   , p_OLD_istanza  in SERVIZI.istanza%type default null
   , p_NEW_livello  in SERVIZI.livello%type default null
   , p_OLD_livello  in SERVIZI.livello%type default null
   , p_NEW_abilitazione  in SERVIZI.abilitazione%type default null
   , p_OLD_abilitazione  in SERVIZI.abilitazione%type default null
   , p_NEW_gruppo_abilitazione  in SERVIZI.gruppo_abilitazione%type default null
   , p_OLD_gruppo_abilitazione  in SERVIZI.gruppo_abilitazione%type default null
   , p_NEW_mail_notifiche  in SERVIZI.mail_notifiche%type default null
   , p_OLD_mail_notifiche  in SERVIZI.mail_notifiche%type default null
   , p_NEW_ccr_notifiche  in SERVIZI.ccr_notifiche%type default null
   , p_OLD_ccr_notifiche  in SERVIZI.ccr_notifiche%type default null
   , p_NEW_multiplo  in SERVIZI.multiplo%type default null
   , p_OLD_multiplo  in SERVIZI.multiplo%type default null
   , p_NEW_tipo_notifica  in SERVIZI.tipo_notifica%type default null
   , p_OLD_tipo_notifica  in SERVIZI.tipo_notifica%type default null
   , p_NEW_recupero_password  in SERVIZI.recupero_password%type default null
   , p_OLD_recupero_password  in SERVIZI.recupero_password%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_id_servizio  in SERVIZI.id_servizio%type
   , p_modulo  in SERVIZI.modulo%type default null
   , p_istanza  in SERVIZI.istanza%type default null
   , p_livello  in SERVIZI.livello%type default null
   , p_abilitazione  in SERVIZI.abilitazione%type default null
   , p_gruppo_abilitazione  in SERVIZI.gruppo_abilitazione%type default null
   , p_mail_notifiche  in SERVIZI.mail_notifiche%type default null
   , p_ccr_notifiche  in SERVIZI.ccr_notifiche%type default null
   , p_multiplo  in SERVIZI.multiplo%type default null
   , p_tipo_notifica  in SERVIZI.tipo_notifica%type default null
   , p_recupero_password  in SERVIZI.recupero_password%type default null
   );
   -- Getter per attributo modulo di riga identificata da chiave
   function get_modulo /* SLAVE_COPY */
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   ) return SERVIZI.modulo%type;
   pragma restrict_references( get_modulo, WNDS );
   -- Getter per attributo istanza di riga identificata da chiave
   function get_istanza /* SLAVE_COPY */
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   ) return SERVIZI.istanza%type;
   pragma restrict_references( get_istanza, WNDS );
   -- Getter per attributo livello di riga identificata da chiave
   function get_livello /* SLAVE_COPY */
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   ) return SERVIZI.livello%type;
   pragma restrict_references( get_livello, WNDS );
   -- Getter per attributo abilitazione di riga identificata da chiave
   function get_abilitazione /* SLAVE_COPY */
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   ) return SERVIZI.abilitazione%type;
   pragma restrict_references( get_abilitazione, WNDS );
   -- Getter per attributo gruppo_abilitazione di riga identificata da chiave
   function get_gruppo_abilitazione /* SLAVE_COPY */
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   ) return SERVIZI.gruppo_abilitazione%type;
   pragma restrict_references( get_gruppo_abilitazione, WNDS );
   -- Getter per attributo mail_notifiche di riga identificata da chiave
   function get_mail_notifiche /* SLAVE_COPY */
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   ) return SERVIZI.mail_notifiche%type;
   pragma restrict_references( get_mail_notifiche, WNDS );
   -- Getter per attributo ccr_notifiche di riga identificata da chiave
   function get_ccr_notifiche /* SLAVE_COPY */
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   ) return SERVIZI.ccr_notifiche%type;
   pragma restrict_references( get_ccr_notifiche, WNDS );
   -- Getter per attributo multiplo di riga identificata da chiave
   function get_multiplo /* SLAVE_COPY */
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   ) return SERVIZI.multiplo%type;
   pragma restrict_references( get_multiplo, WNDS );
   -- Getter per attributo tipo_notifica di riga identificata da chiave
   function get_tipo_notifica /* SLAVE_COPY */
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   ) return SERVIZI.tipo_notifica%type;
   pragma restrict_references( get_tipo_notifica, WNDS );
   -- Getter per attributo recupero_password di riga identificata da chiave
   function get_recupero_password /* SLAVE_COPY */
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   ) return SERVIZI.recupero_password%type;
   pragma restrict_references( get_recupero_password, WNDS );
   -- Setter per attributo id_servizio di riga identificata da chiave
   procedure set_id_servizio
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_value  in SERVIZI.id_servizio%type default null
   );
   -- Setter per attributo modulo di riga identificata da chiave
   procedure set_modulo
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_value  in SERVIZI.modulo%type default null
   );
   -- Setter per attributo istanza di riga identificata da chiave
   procedure set_istanza
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_value  in SERVIZI.istanza%type default null
   );
   -- Setter per attributo livello di riga identificata da chiave
   procedure set_livello
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_value  in SERVIZI.livello%type default null
   );
   -- Setter per attributo abilitazione di riga identificata da chiave
   procedure set_abilitazione
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_value  in SERVIZI.abilitazione%type default null
   );
   -- Setter per attributo gruppo_abilitazione di riga identificata da chiave
   procedure set_gruppo_abilitazione
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_value  in SERVIZI.gruppo_abilitazione%type default null
   );
   -- Setter per attributo mail_notifiche di riga identificata da chiave
   procedure set_mail_notifiche
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_value  in SERVIZI.mail_notifiche%type default null
   );
   -- Setter per attributo ccr_notifiche di riga identificata da chiave
   procedure set_ccr_notifiche
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_value  in SERVIZI.ccr_notifiche%type default null
   );
   -- Setter per attributo multiplo di riga identificata da chiave
   procedure set_multiplo
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_value  in SERVIZI.multiplo%type default null
   );
   -- Setter per attributo tipo_notifica di riga identificata da chiave
   procedure set_tipo_notifica
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_value  in SERVIZI.tipo_notifica%type default null
   );
   -- Setter per attributo recupero_password di riga identificata da chiave
   procedure set_recupero_password
   (
     p_id_servizio  in SERVIZI.id_servizio%type
   , p_value  in SERVIZI.recupero_password%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_id_servizio  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_istanza  in varchar2 default null
   , p_livello  in varchar2 default null
   , p_abilitazione  in varchar2 default null
   , p_gruppo_abilitazione  in varchar2 default null
   , p_mail_notifiche  in varchar2 default null
   , p_ccr_notifiche  in varchar2 default null
   , p_multiplo  in varchar2 default null
   , p_tipo_notifica  in varchar2 default null
   , p_recupero_password  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_id_servizio  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_istanza  in varchar2 default null
   , p_livello  in varchar2 default null
   , p_abilitazione  in varchar2 default null
   , p_gruppo_abilitazione  in varchar2 default null
   , p_mail_notifiche  in varchar2 default null
   , p_ccr_notifiche  in varchar2 default null
   , p_multiplo  in varchar2 default null
   , p_tipo_notifica  in varchar2 default null
   , p_recupero_password  in varchar2 default null
   ) return integer;
end servizi_tpk;
/

