--liquibase formatted sql

--changeset snegroni:202003051308 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE package utenti_salt_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        utenti_salt_tpk
 DESCRIZIONE: Gestione tabella UTENTI_SALT.
 ANNOTAZIONI: .
 REVISIONI:   Table Revision:
              SiaPKGen Revision: .
              SiaTPKDeclare Revision: .
 <CODE>
 Rev.  Data        Autore      Descrizione.
 00    02/03/2020  SNegroni  Generazione automatica.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'UTENTI_SALT';
   subtype t_rowtype is UTENTI_SALT%rowtype;
   -- Tipo del record primary key
subtype t_utente  is UTENTI_SALT.utente%type;
   type t_PK is record
   (
utente  t_utente
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_utente  in UTENTI_SALT.utente%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_utente  in UTENTI_SALT.utente%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_utente  in UTENTI_SALT.utente%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_utente  in UTENTI_SALT.utente%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_utente  in UTENTI_SALT.utente%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins
   (
     p_utente  in UTENTI_SALT.utente%type
   , p_salt  in UTENTI_SALT.salt%type default null
   , p_algoritmo  in UTENTI_SALT.algoritmo%type default null
   );
   function ins  /*+ SOA  */
   (
     p_utente  in UTENTI_SALT.utente%type
   , p_salt  in UTENTI_SALT.salt%type default null
   , p_algoritmo  in UTENTI_SALT.algoritmo%type default null
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_utente  in UTENTI_SALT.utente%type
   , p_OLD_utente  in UTENTI_SALT.utente%type default null
   , p_NEW_salt  in UTENTI_SALT.salt%type default afc.default_null('UTENTI_SALT.salt')
   , p_OLD_salt  in UTENTI_SALT.salt%type default null
   , p_NEW_algoritmo  in UTENTI_SALT.algoritmo%type default afc.default_null('UTENTI_SALT.algoritmo')
   , p_OLD_algoritmo  in UTENTI_SALT.algoritmo%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_utente  in UTENTI_SALT.utente%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_utente  in UTENTI_SALT.utente%type
   , p_salt  in UTENTI_SALT.salt%type default null
   , p_algoritmo  in UTENTI_SALT.algoritmo%type default null
   );
-- Getter per attributo salt di riga identificata da chiave
   function get_salt /* SLAVE_COPY */
   (
     p_utente  in UTENTI_SALT.utente%type
   ) return UTENTI_SALT.salt%type;
   pragma restrict_references( get_salt, WNDS );
-- Getter per attributo algoritmo di riga identificata da chiave
   function get_algoritmo /* SLAVE_COPY */
   (
     p_utente  in UTENTI_SALT.utente%type
   ) return UTENTI_SALT.algoritmo%type;
   pragma restrict_references( get_algoritmo, WNDS );
-- Setter per attributo utente di riga identificata da chiave
   procedure set_utente
   (
     p_utente  in UTENTI_SALT.utente%type
   , p_value  in UTENTI_SALT.utente%type default null
   );
-- Setter per attributo salt di riga identificata da chiave
   procedure set_salt
   (
     p_utente  in UTENTI_SALT.utente%type
   , p_value  in UTENTI_SALT.salt%type default null
   );
-- Setter per attributo algoritmo di riga identificata da chiave
   procedure set_algoritmo
   (
     p_utente  in UTENTI_SALT.utente%type
   , p_value  in UTENTI_SALT.algoritmo%type default null
   );
   -- where_condition per statement di ricerca
   function where_condition /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_utente  in varchar2 default null
   , p_salt  in varchar2 default null
   , p_algoritmo  in varchar2 default null
   ) return AFC.t_statement;
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_utente  in varchar2 default null
   , p_salt  in varchar2 default null
   , p_algoritmo  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_utente  in varchar2 default null
   , p_salt  in varchar2 default null
   , p_algoritmo  in varchar2 default null
   ) return integer;
end utenti_salt_tpk;
/
