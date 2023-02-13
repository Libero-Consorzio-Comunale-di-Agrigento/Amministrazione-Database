--liquibase formatted sql

--changeset mturra:201901301231_225 runOnChange:true stripComments:false endDelimiter:/

CREATE OR REPLACE package comuni_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        comuni_tpk
 DESCRIZIONE: Gestione tabella COMUNI.
 ANNOTAZIONI: .
 REVISIONI:   Table Revision: 10/02/2020 14:46:31
              SiaPKGen Revision: V1.05.014.
              SiaTPKDeclare Revision: V1.17.001.
 <CODE>
 Rev.  Data        Autore      Descrizione.
 00    01/10/2010  snegroni  Prima emissione.
 01    10/02/2020  SNegroni  Generazione automatica.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.01';
   s_table_name constant AFC.t_object_name := 'COMUNI';
   subtype t_rowtype is COMUNI%rowtype;
   -- Tipo del record primary key
subtype t_provincia_stato  is COMUNI.provincia_stato%type;
subtype t_comune  is COMUNI.comune%type;
   type t_PK is record
   (
provincia_stato  t_provincia_stato,
comune  t_comune
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins
   (
     p_provincia_stato  in COMUNI.provincia_stato%type default null
,p_comune  in COMUNI.comune%type default null
   , p_denominazione  in COMUNI.denominazione%type default null
   , p_denominazione_al1  in COMUNI.denominazione_al1%type default null
   , p_denominazione_al2  in COMUNI.denominazione_al2%type default null
   , p_denominazione_breve  in COMUNI.denominazione_breve%type default null
   , p_denominazione_breve_al1  in COMUNI.denominazione_breve_al1%type default null
   , p_denominazione_breve_al2  in COMUNI.denominazione_breve_al2%type default null
   , p_capoluogo_provincia  in COMUNI.capoluogo_provincia%type default null
   , p_cap  in COMUNI.cap%type default null
   , p_provincia_tribunale  in COMUNI.provincia_tribunale%type default null
   , p_comune_tribunale  in COMUNI.comune_tribunale%type default null
   , p_provincia_distretto  in COMUNI.provincia_distretto%type default null
   , p_comune_distretto  in COMUNI.comune_distretto%type default null
   , p_data_soppressione  in COMUNI.data_soppressione%type default null
   , p_provincia_fusione  in COMUNI.provincia_fusione%type default null
   , p_comune_fusione  in COMUNI.comune_fusione%type default null
   , p_sigla_cfis  in COMUNI.sigla_cfis%type default null
   , p_consolato  in COMUNI.consolato%type default null
   , p_tipo_consolato  in COMUNI.tipo_consolato%type default null
   , p_utente_aggiornamento  in COMUNI.utente_aggiornamento%type default null
   , p_data_aggiornamento  in COMUNI.data_aggiornamento%type default null
   , p_data_istituzione  in COMUNI.data_istituzione%type default null
   );
   function ins  /*+ SOA  */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type default null
,p_comune  in COMUNI.comune%type default null
   , p_denominazione  in COMUNI.denominazione%type default null
   , p_denominazione_al1  in COMUNI.denominazione_al1%type default null
   , p_denominazione_al2  in COMUNI.denominazione_al2%type default null
   , p_denominazione_breve  in COMUNI.denominazione_breve%type default null
   , p_denominazione_breve_al1  in COMUNI.denominazione_breve_al1%type default null
   , p_denominazione_breve_al2  in COMUNI.denominazione_breve_al2%type default null
   , p_capoluogo_provincia  in COMUNI.capoluogo_provincia%type default null
   , p_cap  in COMUNI.cap%type default null
   , p_provincia_tribunale  in COMUNI.provincia_tribunale%type default null
   , p_comune_tribunale  in COMUNI.comune_tribunale%type default null
   , p_provincia_distretto  in COMUNI.provincia_distretto%type default null
   , p_comune_distretto  in COMUNI.comune_distretto%type default null
   , p_data_soppressione  in COMUNI.data_soppressione%type default null
   , p_provincia_fusione  in COMUNI.provincia_fusione%type default null
   , p_comune_fusione  in COMUNI.comune_fusione%type default null
   , p_sigla_cfis  in COMUNI.sigla_cfis%type default null
   , p_consolato  in COMUNI.consolato%type default null
   , p_tipo_consolato  in COMUNI.tipo_consolato%type default null
   , p_utente_aggiornamento  in COMUNI.utente_aggiornamento%type default null
   , p_data_aggiornamento  in COMUNI.data_aggiornamento%type default null
   , p_data_istituzione  in COMUNI.data_istituzione%type default null
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_provincia_stato  in COMUNI.provincia_stato%type
   , p_OLD_provincia_stato  in COMUNI.provincia_stato%type default null
, p_NEW_comune  in COMUNI.comune%type
   , p_OLD_comune  in COMUNI.comune%type default null
   , p_NEW_denominazione  in COMUNI.denominazione%type default afc.default_null('COMUNI.denominazione')
   , p_OLD_denominazione  in COMUNI.denominazione%type default null
   , p_NEW_denominazione_al1  in COMUNI.denominazione_al1%type default afc.default_null('COMUNI.denominazione_al1')
   , p_OLD_denominazione_al1  in COMUNI.denominazione_al1%type default null
   , p_NEW_denominazione_al2  in COMUNI.denominazione_al2%type default afc.default_null('COMUNI.denominazione_al2')
   , p_OLD_denominazione_al2  in COMUNI.denominazione_al2%type default null
   , p_NEW_denominazione_breve  in COMUNI.denominazione_breve%type default afc.default_null('COMUNI.denominazione_breve')
   , p_OLD_denominazione_breve  in COMUNI.denominazione_breve%type default null
   , p_NEW_denominazione_breve_al1  in COMUNI.denominazione_breve_al1%type default afc.default_null('COMUNI.denominazione_breve_al1')
   , p_OLD_denominazione_breve_al1  in COMUNI.denominazione_breve_al1%type default null
   , p_NEW_denominazione_breve_al2  in COMUNI.denominazione_breve_al2%type default afc.default_null('COMUNI.denominazione_breve_al2')
   , p_OLD_denominazione_breve_al2  in COMUNI.denominazione_breve_al2%type default null
   , p_NEW_capoluogo_provincia  in COMUNI.capoluogo_provincia%type default afc.default_null('COMUNI.capoluogo_provincia')
   , p_OLD_capoluogo_provincia  in COMUNI.capoluogo_provincia%type default null
   , p_NEW_cap  in COMUNI.cap%type default afc.default_null('COMUNI.cap')
   , p_OLD_cap  in COMUNI.cap%type default null
   , p_NEW_provincia_tribunale  in COMUNI.provincia_tribunale%type default afc.default_null('COMUNI.provincia_tribunale')
   , p_OLD_provincia_tribunale  in COMUNI.provincia_tribunale%type default null
   , p_NEW_comune_tribunale  in COMUNI.comune_tribunale%type default afc.default_null('COMUNI.comune_tribunale')
   , p_OLD_comune_tribunale  in COMUNI.comune_tribunale%type default null
   , p_NEW_provincia_distretto  in COMUNI.provincia_distretto%type default afc.default_null('COMUNI.provincia_distretto')
   , p_OLD_provincia_distretto  in COMUNI.provincia_distretto%type default null
   , p_NEW_comune_distretto  in COMUNI.comune_distretto%type default afc.default_null('COMUNI.comune_distretto')
   , p_OLD_comune_distretto  in COMUNI.comune_distretto%type default null
   , p_NEW_data_soppressione  in COMUNI.data_soppressione%type default afc.default_null('COMUNI.data_soppressione')
   , p_OLD_data_soppressione  in COMUNI.data_soppressione%type default null
   , p_NEW_provincia_fusione  in COMUNI.provincia_fusione%type default afc.default_null('COMUNI.provincia_fusione')
   , p_OLD_provincia_fusione  in COMUNI.provincia_fusione%type default null
   , p_NEW_comune_fusione  in COMUNI.comune_fusione%type default afc.default_null('COMUNI.comune_fusione')
   , p_OLD_comune_fusione  in COMUNI.comune_fusione%type default null
   , p_NEW_sigla_cfis  in COMUNI.sigla_cfis%type default afc.default_null('COMUNI.sigla_cfis')
   , p_OLD_sigla_cfis  in COMUNI.sigla_cfis%type default null
   , p_NEW_consolato  in COMUNI.consolato%type default afc.default_null('COMUNI.consolato')
   , p_OLD_consolato  in COMUNI.consolato%type default null
   , p_NEW_tipo_consolato  in COMUNI.tipo_consolato%type default afc.default_null('COMUNI.tipo_consolato')
   , p_OLD_tipo_consolato  in COMUNI.tipo_consolato%type default null
   , p_NEW_utente_aggiornamento  in COMUNI.utente_aggiornamento%type default afc.default_null('COMUNI.utente_aggiornamento')
   , p_OLD_utente_aggiornamento  in COMUNI.utente_aggiornamento%type default null
   , p_NEW_data_aggiornamento  in COMUNI.data_aggiornamento%type default afc.default_null('COMUNI.data_aggiornamento')
   , p_OLD_data_aggiornamento  in COMUNI.data_aggiornamento%type default null
   , p_NEW_data_istituzione  in COMUNI.data_istituzione%type default afc.default_null('COMUNI.data_istituzione')
   , p_OLD_data_istituzione  in COMUNI.data_istituzione%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   procedure upd_column
   (
p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_column in varchar2
   , p_value  in date
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_provincia_stato  in COMUNI.provincia_stato%type
, p_comune  in COMUNI.comune%type
   , p_denominazione  in COMUNI.denominazione%type default null
   , p_denominazione_al1  in COMUNI.denominazione_al1%type default null
   , p_denominazione_al2  in COMUNI.denominazione_al2%type default null
   , p_denominazione_breve  in COMUNI.denominazione_breve%type default null
   , p_denominazione_breve_al1  in COMUNI.denominazione_breve_al1%type default null
   , p_denominazione_breve_al2  in COMUNI.denominazione_breve_al2%type default null
   , p_capoluogo_provincia  in COMUNI.capoluogo_provincia%type default null
   , p_cap  in COMUNI.cap%type default null
   , p_provincia_tribunale  in COMUNI.provincia_tribunale%type default null
   , p_comune_tribunale  in COMUNI.comune_tribunale%type default null
   , p_provincia_distretto  in COMUNI.provincia_distretto%type default null
   , p_comune_distretto  in COMUNI.comune_distretto%type default null
   , p_data_soppressione  in COMUNI.data_soppressione%type default null
   , p_provincia_fusione  in COMUNI.provincia_fusione%type default null
   , p_comune_fusione  in COMUNI.comune_fusione%type default null
   , p_sigla_cfis  in COMUNI.sigla_cfis%type default null
   , p_consolato  in COMUNI.consolato%type default null
   , p_tipo_consolato  in COMUNI.tipo_consolato%type default null
   , p_utente_aggiornamento  in COMUNI.utente_aggiornamento%type default null
   , p_data_aggiornamento  in COMUNI.data_aggiornamento%type default null
   , p_data_istituzione  in COMUNI.data_istituzione%type default null
   );
-- Getter per attributo denominazione di riga identificata da chiave
   function get_denominazione /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.denominazione%type;
   pragma restrict_references( get_denominazione, WNDS );
-- Getter per attributo denominazione_al1 di riga identificata da chiave
   function get_denominazione_al1 /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.denominazione_al1%type;
   pragma restrict_references( get_denominazione_al1, WNDS );
-- Getter per attributo denominazione_al2 di riga identificata da chiave
   function get_denominazione_al2 /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.denominazione_al2%type;
   pragma restrict_references( get_denominazione_al2, WNDS );
-- Getter per attributo denominazione_breve di riga identificata da chiave
   function get_denominazione_breve /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.denominazione_breve%type;
   pragma restrict_references( get_denominazione_breve, WNDS );
-- Getter per attributo denominazione_breve_al1 di riga identificata da chiave
   function get_denominazione_breve_al1 /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.denominazione_breve_al1%type;
   pragma restrict_references( get_denominazione_breve_al1, WNDS );
-- Getter per attributo denominazione_breve_al2 di riga identificata da chiave
   function get_denominazione_breve_al2 /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.denominazione_breve_al2%type;
   pragma restrict_references( get_denominazione_breve_al2, WNDS );
-- Getter per attributo capoluogo_provincia di riga identificata da chiave
   function get_capoluogo_provincia /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.capoluogo_provincia%type;
   pragma restrict_references( get_capoluogo_provincia, WNDS );
-- Getter per attributo cap di riga identificata da chiave
   function get_cap /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.cap%type;
   pragma restrict_references( get_cap, WNDS );
-- Getter per attributo provincia_tribunale di riga identificata da chiave
   function get_provincia_tribunale /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.provincia_tribunale%type;
   pragma restrict_references( get_provincia_tribunale, WNDS );
-- Getter per attributo comune_tribunale di riga identificata da chiave
   function get_comune_tribunale /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.comune_tribunale%type;
   pragma restrict_references( get_comune_tribunale, WNDS );
-- Getter per attributo provincia_distretto di riga identificata da chiave
   function get_provincia_distretto /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.provincia_distretto%type;
   pragma restrict_references( get_provincia_distretto, WNDS );
-- Getter per attributo comune_distretto di riga identificata da chiave
   function get_comune_distretto /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.comune_distretto%type;
   pragma restrict_references( get_comune_distretto, WNDS );
-- Getter per attributo data_soppressione di riga identificata da chiave
   function get_data_soppressione /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.data_soppressione%type;
   pragma restrict_references( get_data_soppressione, WNDS );
-- Getter per attributo provincia_fusione di riga identificata da chiave
   function get_provincia_fusione /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.provincia_fusione%type;
   pragma restrict_references( get_provincia_fusione, WNDS );
-- Getter per attributo comune_fusione di riga identificata da chiave
   function get_comune_fusione /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.comune_fusione%type;
   pragma restrict_references( get_comune_fusione, WNDS );
-- Getter per attributo sigla_cfis di riga identificata da chiave
   function get_sigla_cfis /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.sigla_cfis%type;
   pragma restrict_references( get_sigla_cfis, WNDS );
-- Getter per attributo consolato di riga identificata da chiave
   function get_consolato /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.consolato%type;
   pragma restrict_references( get_consolato, WNDS );
-- Getter per attributo tipo_consolato di riga identificata da chiave
   function get_tipo_consolato /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.tipo_consolato%type;
   pragma restrict_references( get_tipo_consolato, WNDS );
-- Getter per attributo utente_aggiornamento di riga identificata da chiave
   function get_utente_aggiornamento /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.utente_aggiornamento%type;
   pragma restrict_references( get_utente_aggiornamento, WNDS );
-- Getter per attributo data_aggiornamento di riga identificata da chiave
   function get_data_aggiornamento /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.data_aggiornamento%type;
   pragma restrict_references( get_data_aggiornamento, WNDS );
-- Getter per attributo data_istituzione di riga identificata da chiave
   function get_data_istituzione /* SLAVE_COPY */
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   ) return COMUNI.data_istituzione%type;
   pragma restrict_references( get_data_istituzione, WNDS );
-- Setter per attributo provincia_stato di riga identificata da chiave
   procedure set_provincia_stato
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.provincia_stato%type default null
   );
-- Setter per attributo comune di riga identificata da chiave
   procedure set_comune
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.comune%type default null
   );
-- Setter per attributo denominazione di riga identificata da chiave
   procedure set_denominazione
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.denominazione%type default null
   );
-- Setter per attributo denominazione_al1 di riga identificata da chiave
   procedure set_denominazione_al1
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.denominazione_al1%type default null
   );
-- Setter per attributo denominazione_al2 di riga identificata da chiave
   procedure set_denominazione_al2
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.denominazione_al2%type default null
   );
-- Setter per attributo denominazione_breve di riga identificata da chiave
   procedure set_denominazione_breve
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.denominazione_breve%type default null
   );
-- Setter per attributo denominazione_breve_al1 di riga identificata da chiave
   procedure set_denominazione_breve_al1
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.denominazione_breve_al1%type default null
   );
-- Setter per attributo denominazione_breve_al2 di riga identificata da chiave
   procedure set_denominazione_breve_al2
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.denominazione_breve_al2%type default null
   );
-- Setter per attributo capoluogo_provincia di riga identificata da chiave
   procedure set_capoluogo_provincia
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.capoluogo_provincia%type default null
   );
-- Setter per attributo cap di riga identificata da chiave
   procedure set_cap
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.cap%type default null
   );
-- Setter per attributo provincia_tribunale di riga identificata da chiave
   procedure set_provincia_tribunale
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.provincia_tribunale%type default null
   );
-- Setter per attributo comune_tribunale di riga identificata da chiave
   procedure set_comune_tribunale
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.comune_tribunale%type default null
   );
-- Setter per attributo provincia_distretto di riga identificata da chiave
   procedure set_provincia_distretto
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.provincia_distretto%type default null
   );
-- Setter per attributo comune_distretto di riga identificata da chiave
   procedure set_comune_distretto
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.comune_distretto%type default null
   );
-- Setter per attributo data_soppressione di riga identificata da chiave
   procedure set_data_soppressione
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.data_soppressione%type default null
   );
-- Setter per attributo provincia_fusione di riga identificata da chiave
   procedure set_provincia_fusione
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.provincia_fusione%type default null
   );
-- Setter per attributo comune_fusione di riga identificata da chiave
   procedure set_comune_fusione
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.comune_fusione%type default null
   );
-- Setter per attributo sigla_cfis di riga identificata da chiave
   procedure set_sigla_cfis
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.sigla_cfis%type default null
   );
-- Setter per attributo consolato di riga identificata da chiave
   procedure set_consolato
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.consolato%type default null
   );
-- Setter per attributo tipo_consolato di riga identificata da chiave
   procedure set_tipo_consolato
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.tipo_consolato%type default null
   );
-- Setter per attributo utente_aggiornamento di riga identificata da chiave
   procedure set_utente_aggiornamento
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.utente_aggiornamento%type default null
   );
-- Setter per attributo data_aggiornamento di riga identificata da chiave
   procedure set_data_aggiornamento
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.data_aggiornamento%type default null
   );
-- Setter per attributo data_istituzione di riga identificata da chiave
   procedure set_data_istituzione
   (
     p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
   , p_value  in COMUNI.data_istituzione%type default null
   );
   -- where_condition per statement di ricerca
   function where_condition /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_provincia_stato  in varchar2 default null
, p_comune  in varchar2 default null
   , p_denominazione  in varchar2 default null
   , p_denominazione_al1  in varchar2 default null
   , p_denominazione_al2  in varchar2 default null
   , p_denominazione_breve  in varchar2 default null
   , p_denominazione_breve_al1  in varchar2 default null
   , p_denominazione_breve_al2  in varchar2 default null
   , p_capoluogo_provincia  in varchar2 default null
   , p_cap  in varchar2 default null
   , p_provincia_tribunale  in varchar2 default null
   , p_comune_tribunale  in varchar2 default null
   , p_provincia_distretto  in varchar2 default null
   , p_comune_distretto  in varchar2 default null
   , p_data_soppressione  in varchar2 default null
   , p_provincia_fusione  in varchar2 default null
   , p_comune_fusione  in varchar2 default null
   , p_sigla_cfis  in varchar2 default null
   , p_consolato  in varchar2 default null
   , p_tipo_consolato  in varchar2 default null
   , p_utente_aggiornamento  in varchar2 default null
   , p_data_aggiornamento  in varchar2 default null
   , p_data_istituzione  in varchar2 default null
   ) return AFC.t_statement;
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_provincia_stato  in varchar2 default null
, p_comune  in varchar2 default null
   , p_denominazione  in varchar2 default null
   , p_denominazione_al1  in varchar2 default null
   , p_denominazione_al2  in varchar2 default null
   , p_denominazione_breve  in varchar2 default null
   , p_denominazione_breve_al1  in varchar2 default null
   , p_denominazione_breve_al2  in varchar2 default null
   , p_capoluogo_provincia  in varchar2 default null
   , p_cap  in varchar2 default null
   , p_provincia_tribunale  in varchar2 default null
   , p_comune_tribunale  in varchar2 default null
   , p_provincia_distretto  in varchar2 default null
   , p_comune_distretto  in varchar2 default null
   , p_data_soppressione  in varchar2 default null
   , p_provincia_fusione  in varchar2 default null
   , p_comune_fusione  in varchar2 default null
   , p_sigla_cfis  in varchar2 default null
   , p_consolato  in varchar2 default null
   , p_tipo_consolato  in varchar2 default null
   , p_utente_aggiornamento  in varchar2 default null
   , p_data_aggiornamento  in varchar2 default null
   , p_data_istituzione  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_provincia_stato  in varchar2 default null
, p_comune  in varchar2 default null
   , p_denominazione  in varchar2 default null
   , p_denominazione_al1  in varchar2 default null
   , p_denominazione_al2  in varchar2 default null
   , p_denominazione_breve  in varchar2 default null
   , p_denominazione_breve_al1  in varchar2 default null
   , p_denominazione_breve_al2  in varchar2 default null
   , p_capoluogo_provincia  in varchar2 default null
   , p_cap  in varchar2 default null
   , p_provincia_tribunale  in varchar2 default null
   , p_comune_tribunale  in varchar2 default null
   , p_provincia_distretto  in varchar2 default null
   , p_comune_distretto  in varchar2 default null
   , p_data_soppressione  in varchar2 default null
   , p_provincia_fusione  in varchar2 default null
   , p_comune_fusione  in varchar2 default null
   , p_sigla_cfis  in varchar2 default null
   , p_consolato  in varchar2 default null
   , p_tipo_consolato  in varchar2 default null
   , p_utente_aggiornamento  in varchar2 default null
   , p_data_aggiornamento  in varchar2 default null
   , p_data_istituzione  in varchar2 default null
   ) return integer;
end comuni_tpk;
/