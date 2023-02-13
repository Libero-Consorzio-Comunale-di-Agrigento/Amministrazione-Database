--liquibase formatted sql

--changeset mturra:201901301231_246 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE istanze_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        istanze_tpk
 DESCRIZIONE: Gestione tabella ISTANZE.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    11/05/2009  mmalferrari  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'ISTANZE';
   subtype t_rowtype is ISTANZE%rowtype;
   -- Tipo del record primary key
   subtype t_istanza  is ISTANZE.istanza%type;
   type t_PK is record
   (
    istanza  t_istanza
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_istanza  in ISTANZE.istanza%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_istanza  in ISTANZE.istanza%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_istanza  in ISTANZE.istanza%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_istanza  in ISTANZE.istanza%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_istanza  in ISTANZE.istanza%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins  /*+ SOA  */
   (
     p_istanza  in ISTANZE.istanza%type
   , p_progetto  in ISTANZE.progetto%type
   , p_ente  in ISTANZE.ente%type
   , p_descrizione  in ISTANZE.descrizione%type
   , p_user_oracle  in ISTANZE.user_oracle%type
   , p_password_oracle  in ISTANZE.password_oracle%type
   , p_dislocazione  in ISTANZE.dislocazione%type
   , p_dislocazione_temporanea  in ISTANZE.dislocazione_temporanea%type default null
   , p_installazione  in ISTANZE.installazione%type default null
   , p_versione  in ISTANZE.versione%type default null
   , p_dislocazione_dimensionamenti  in ISTANZE.dislocazione_dimensionamenti%type default null
   , p_note  in ISTANZE.note%type default null
   , p_lingua  in ISTANZE.lingua%type default 'I'
   , p_link_oracle  in ISTANZE.link_oracle%type default null
   , p_database_link  in ISTANZE.database_link%type default null
   , p_servizio  in ISTANZE.servizio%type default null
   , p_database_driver  in ISTANZE.database_driver%type default null
   , p_istanza_amministratore  in ISTANZE.istanza_amministratore%type default null
   );
   function ins  /*+ SOA  */
   (
     p_istanza  in ISTANZE.istanza%type
   , p_progetto  in ISTANZE.progetto%type
   , p_ente  in ISTANZE.ente%type
   , p_descrizione  in ISTANZE.descrizione%type
   , p_user_oracle  in ISTANZE.user_oracle%type
   , p_password_oracle  in ISTANZE.password_oracle%type
   , p_dislocazione  in ISTANZE.dislocazione%type
   , p_dislocazione_temporanea  in ISTANZE.dislocazione_temporanea%type default null
   , p_installazione  in ISTANZE.installazione%type default null
   , p_versione  in ISTANZE.versione%type default null
   , p_dislocazione_dimensionamenti  in ISTANZE.dislocazione_dimensionamenti%type default null
   , p_note  in ISTANZE.note%type default null
   , p_lingua  in ISTANZE.lingua%type default 'I'
   , p_link_oracle  in ISTANZE.link_oracle%type default null
   , p_database_link  in ISTANZE.database_link%type default null
   , p_servizio  in ISTANZE.servizio%type default null
   , p_database_driver  in ISTANZE.database_driver%type default null
   , p_istanza_amministratore  in ISTANZE.istanza_amministratore%type default null
   ) return integer;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_istanza  in ISTANZE.istanza%type
   , p_OLD_istanza  in ISTANZE.istanza%type default null
   , p_NEW_progetto  in ISTANZE.progetto%type default null
   , p_OLD_progetto  in ISTANZE.progetto%type default null
   , p_NEW_ente  in ISTANZE.ente%type default null
   , p_OLD_ente  in ISTANZE.ente%type default null
   , p_NEW_descrizione  in ISTANZE.descrizione%type default null
   , p_OLD_descrizione  in ISTANZE.descrizione%type default null
   , p_NEW_user_oracle  in ISTANZE.user_oracle%type default null
   , p_OLD_user_oracle  in ISTANZE.user_oracle%type default null
   , p_NEW_password_oracle  in ISTANZE.password_oracle%type default null
   , p_OLD_password_oracle  in ISTANZE.password_oracle%type default null
   , p_NEW_dislocazione  in ISTANZE.dislocazione%type default null
   , p_OLD_dislocazione  in ISTANZE.dislocazione%type default null
   , p_NEW_dislocazione_temporanea  in ISTANZE.dislocazione_temporanea%type default null
   , p_OLD_dislocazione_temporanea  in ISTANZE.dislocazione_temporanea%type default null
   , p_NEW_installazione  in ISTANZE.installazione%type default null
   , p_OLD_installazione  in ISTANZE.installazione%type default null
   , p_NEW_versione  in ISTANZE.versione%type default null
   , p_OLD_versione  in ISTANZE.versione%type default null
   , p_NEW_dislocazione_dimensionam  in ISTANZE.dislocazione_dimensionamenti%type default null
   , p_OLD_dislocazione_dimensionam  in ISTANZE.dislocazione_dimensionamenti%type default null
   , p_NEW_note  in ISTANZE.note%type default null
   , p_OLD_note  in ISTANZE.note%type default null
   , p_NEW_lingua  in ISTANZE.lingua%type default null
   , p_OLD_lingua  in ISTANZE.lingua%type default null
   , p_NEW_link_oracle  in ISTANZE.link_oracle%type default null
   , p_OLD_link_oracle  in ISTANZE.link_oracle%type default null
   , p_NEW_database_link  in ISTANZE.database_link%type default null
   , p_OLD_database_link  in ISTANZE.database_link%type default null
   , p_NEW_servizio  in ISTANZE.servizio%type default null
   , p_OLD_servizio  in ISTANZE.servizio%type default null
   , p_NEW_database_driver  in ISTANZE.database_driver%type default null
   , p_OLD_database_driver  in ISTANZE.database_driver%type default null
   , p_NEW_istanza_amministratore  in ISTANZE.istanza_amministratore%type default null
   , p_OLD_istanza_amministratore  in ISTANZE.istanza_amministratore%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_istanza  in ISTANZE.istanza%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_istanza  in ISTANZE.istanza%type
   , p_progetto  in ISTANZE.progetto%type default null
   , p_ente  in ISTANZE.ente%type default null
   , p_descrizione  in ISTANZE.descrizione%type default null
   , p_user_oracle  in ISTANZE.user_oracle%type default null
   , p_password_oracle  in ISTANZE.password_oracle%type default null
   , p_dislocazione  in ISTANZE.dislocazione%type default null
   , p_dislocazione_temporanea  in ISTANZE.dislocazione_temporanea%type default null
   , p_installazione  in ISTANZE.installazione%type default null
   , p_versione  in ISTANZE.versione%type default null
   , p_dislocazione_dimensionamenti  in ISTANZE.dislocazione_dimensionamenti%type default null
   , p_note  in ISTANZE.note%type default null
   , p_lingua  in ISTANZE.lingua%type default null
   , p_link_oracle  in ISTANZE.link_oracle%type default null
   , p_database_link  in ISTANZE.database_link%type default null
   , p_servizio  in ISTANZE.servizio%type default null
   , p_database_driver  in ISTANZE.database_driver%type default null
   , p_istanza_amministratore  in ISTANZE.istanza_amministratore%type default null
   );
   -- Getter per attributo progetto di riga identificata da chiave
   function get_progetto /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.progetto%type;
   pragma restrict_references( get_progetto, WNDS );
   -- Getter per attributo ente di riga identificata da chiave
   function get_ente /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.ente%type;
   pragma restrict_references( get_ente, WNDS );
   -- Getter per attributo descrizione di riga identificata da chiave
   function get_descrizione /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.descrizione%type;
   pragma restrict_references( get_descrizione, WNDS );
   -- Getter per attributo user_oracle di riga identificata da chiave
   function get_user_oracle /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.user_oracle%type;
   pragma restrict_references( get_user_oracle, WNDS );
   -- Getter per attributo password_oracle di riga identificata da chiave
   function get_password_oracle /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.password_oracle%type;
   pragma restrict_references( get_password_oracle, WNDS );
   -- Getter per attributo dislocazione di riga identificata da chiave
   function get_dislocazione /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.dislocazione%type;
   pragma restrict_references( get_dislocazione, WNDS );
   -- Getter per attributo dislocazione_temporanea di riga identificata da chiave
   function get_dislocazione_temporanea /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.dislocazione_temporanea%type;
   pragma restrict_references( get_dislocazione_temporanea, WNDS );
   -- Getter per attributo installazione di riga identificata da chiave
   function get_installazione /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.installazione%type;
   pragma restrict_references( get_installazione, WNDS );
   -- Getter per attributo versione di riga identificata da chiave
   function get_versione /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.versione%type;
   pragma restrict_references( get_versione, WNDS );
   -- Getter per attributo dislocazione_dimensionamenti di riga identificata da chiave
   function get_dislocazione_dimensionamen /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.dislocazione_dimensionamenti%type;
   pragma restrict_references( get_dislocazione_dimensionamen, WNDS );
   -- Getter per attributo note di riga identificata da chiave
   function get_note /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.note%type;
   pragma restrict_references( get_note, WNDS );
   -- Getter per attributo lingua di riga identificata da chiave
   function get_lingua /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.lingua%type;
   pragma restrict_references( get_lingua, WNDS );
   -- Getter per attributo link_oracle di riga identificata da chiave
   function get_link_oracle /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.link_oracle%type;
   pragma restrict_references( get_link_oracle, WNDS );
   -- Getter per attributo database_link di riga identificata da chiave
   function get_database_link /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.database_link%type;
   pragma restrict_references( get_database_link, WNDS );
   -- Getter per attributo servizio di riga identificata da chiave
   function get_servizio /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.servizio%type;
   pragma restrict_references( get_servizio, WNDS );
   -- Getter per attributo database_driver di riga identificata da chiave
   function get_database_driver /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.database_driver%type;
   pragma restrict_references( get_database_driver, WNDS );
   -- Getter per attributo istanza_amministratore di riga identificata da chiave
   function get_istanza_amministratore /* SLAVE_COPY */
   (
     p_istanza  in ISTANZE.istanza%type
   ) return ISTANZE.istanza_amministratore%type;
   pragma restrict_references( get_istanza_amministratore, WNDS );
   -- Setter per attributo istanza di riga identificata da chiave
   procedure set_istanza
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.istanza%type default null
   );
   -- Setter per attributo progetto di riga identificata da chiave
   procedure set_progetto
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.progetto%type default null
   );
   -- Setter per attributo ente di riga identificata da chiave
   procedure set_ente
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.ente%type default null
   );
   -- Setter per attributo descrizione di riga identificata da chiave
   procedure set_descrizione
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.descrizione%type default null
   );
   -- Setter per attributo user_oracle di riga identificata da chiave
   procedure set_user_oracle
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.user_oracle%type default null
   );
   -- Setter per attributo password_oracle di riga identificata da chiave
   procedure set_password_oracle
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.password_oracle%type default null
   );
   -- Setter per attributo dislocazione di riga identificata da chiave
   procedure set_dislocazione
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.dislocazione%type default null
   );
   -- Setter per attributo dislocazione_temporanea di riga identificata da chiave
   procedure set_dislocazione_temporanea
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.dislocazione_temporanea%type default null
   );
   -- Setter per attributo installazione di riga identificata da chiave
   procedure set_installazione
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.installazione%type default null
   );
   -- Setter per attributo versione di riga identificata da chiave
   procedure set_versione
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.versione%type default null
   );
   -- Setter per attributo dislocazione_dimensionamenti di riga identificata da chiave
   procedure set_dislocazione_dimensionamen
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.dislocazione_dimensionamenti%type default null
   );
   -- Setter per attributo note di riga identificata da chiave
   procedure set_note
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.note%type default null
   );
   -- Setter per attributo lingua di riga identificata da chiave
   procedure set_lingua
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.lingua%type default null
   );
   -- Setter per attributo link_oracle di riga identificata da chiave
   procedure set_link_oracle
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.link_oracle%type default null
   );
   -- Setter per attributo database_link di riga identificata da chiave
   procedure set_database_link
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.database_link%type default null
   );
   -- Setter per attributo servizio di riga identificata da chiave
   procedure set_servizio
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.servizio%type default null
   );
   -- Setter per attributo database_driver di riga identificata da chiave
   procedure set_database_driver
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.database_driver%type default null
   );
   -- Setter per attributo istanza_amministratore di riga identificata da chiave
   procedure set_istanza_amministratore
   (
     p_istanza  in ISTANZE.istanza%type
   , p_value  in ISTANZE.istanza_amministratore%type default null
   );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_istanza  in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_ente  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_user_oracle  in varchar2 default null
   , p_password_oracle  in varchar2 default null
   , p_dislocazione  in varchar2 default null
   , p_dislocazione_temporanea  in varchar2 default null
   , p_installazione  in varchar2 default null
   , p_versione  in varchar2 default null
   , p_dislocazione_dimensionamenti  in varchar2 default null
   , p_note  in varchar2 default null
   , p_lingua  in varchar2 default null
   , p_link_oracle  in varchar2 default null
   , p_database_link  in varchar2 default null
   , p_servizio  in varchar2 default null
   , p_database_driver  in varchar2 default null
   , p_istanza_amministratore  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_istanza  in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_ente  in varchar2 default null
   , p_descrizione  in varchar2 default null
   , p_user_oracle  in varchar2 default null
   , p_password_oracle  in varchar2 default null
   , p_dislocazione  in varchar2 default null
   , p_dislocazione_temporanea  in varchar2 default null
   , p_installazione  in varchar2 default null
   , p_versione  in varchar2 default null
   , p_dislocazione_dimensionamenti  in varchar2 default null
   , p_note  in varchar2 default null
   , p_lingua  in varchar2 default null
   , p_link_oracle  in varchar2 default null
   , p_database_link  in varchar2 default null
   , p_servizio  in varchar2 default null
   , p_database_driver  in varchar2 default null
   , p_istanza_amministratore  in varchar2 default null
   ) return integer;
end istanze_tpk;
/

