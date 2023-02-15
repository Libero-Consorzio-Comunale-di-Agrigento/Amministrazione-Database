CREATE OR REPLACE package comuni_totali_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        comuni_totali_tpk
 DESCRIZIONE: Gestione tabella COMUNI_TOTALI.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    12/02/2010  snegroni  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'COMUNI_TOTALI';
   subtype t_rowtype is COMUNI_TOTALI%rowtype;
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_regione  in varchar2 default null
   , p_provincia  in varchar2 default null
   , p_comune  in varchar2 default null
   , p_denominazione  in varchar2 default null
   , p_sigla  in varchar2 default null
   , p_cap  in varchar2 default null
   , p_istat  in varchar2 default null
   , p_sigla_cfis  in varchar2 default null
   , p_data_soppressione  in varchar2 default null
   , p_provincia_fusione  in varchar2 default null
   , p_comune_fusione  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_regione  in varchar2 default null
   , p_provincia  in varchar2 default null
   , p_comune  in varchar2 default null
   , p_denominazione  in varchar2 default null
   , p_sigla  in varchar2 default null
   , p_cap  in varchar2 default null
   , p_istat  in varchar2 default null
   , p_sigla_cfis  in varchar2 default null
   , p_data_soppressione  in varchar2 default null
   , p_provincia_fusione  in varchar2 default null
   , p_comune_fusione  in varchar2 default null
   ) return integer;
end comuni_totali_tpk;
/

