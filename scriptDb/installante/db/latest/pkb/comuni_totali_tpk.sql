--liquibase formatted sql

--changeset mturra:201901301231_127 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY comuni_totali_tpk is
/******************************************************************************
 NOME:        comuni_totali_tpk
 DESCRIZIONE: Gestione tabella COMUNI_TOTALI.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   12/02/2010  snegroni  Prima emissione.
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '000';
--------------------------------------------------------------------------------
function versione
return varchar2 is /* SLAVE_COPY */
/******************************************************************************
 NOME:        versione
 DESCRIZIONE: Versione e revisione di distribuzione del package.
 RITORNA:     varchar2 stringa contenente versione e revisione.
 NOTE:        Primo numero  : versione compatibilita del Package.
              Secondo numero: revisione del Package specification.
              Terzo numero  : revisione del Package body.
******************************************************************************/
begin
   return AFC.version ( s_revisione, s_revisione_body );
end versione; -- comuni_totali_tpk.versione
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
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
) return AFC.t_statement is /* SLAVE_COPY */
/******************************************************************************
 NOME:        where_condition
 DESCRIZIONE: Ritorna la where_condition per lo statement di select di get_rows e count_rows.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo e presente
                       un operatore, altrimenti viene usato quello di default ('=')
                    1: viene utilizzato l'operatore specificato all'inizio di ogni
                       attributo.
              p_other_condition: condizioni aggiuntive di base
              Chiavi e attributi della table
 RITORNA:     AFC.t_statement.
 NOTE:        Se p_QBE = 1 , ogni parametro deve contenere, nella prima parte,
              l'operatore da utilizzare nella where-condition.
******************************************************************************/
   d_statement AFC.t_statement;
begin
   d_statement := ' where ( 1 = 1 '
               || AFC.get_field_condition( ' and ( regione ', p_regione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( provincia ', p_provincia , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( comune ', p_comune , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione ', p_denominazione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( sigla ', p_sigla , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( cap ', p_cap , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( istat ', p_istat , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( sigla_cfis ', p_sigla_cfis , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data_soppressione ', p_data_soppressione , ' )', p_QBE, AFC.date_format )
               || AFC.get_field_condition( ' and ( provincia_fusione ', p_provincia_fusione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( comune_fusione ', p_comune_fusione , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- comuni_totali_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
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
) return AFC.t_ref_cursor is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_rows
 DESCRIZIONE: Ritorna il risultato di una query in base ai valori che passiamo.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo e presente
                       un operatore, altrimenti viene usato quello di default ('=')
                    1: viene utilizzato l'operatore specificato all'inizio di ogni
                       attributo.
              p_other_condition: condizioni aggiuntive di base
              p_order_by: condizioni di ordinamento
              p_extra_columns: colonne da aggiungere alla select
              p_extra_condition: condizioni aggiuntive
              Chiavi e attributi della table
 RITORNA:     Un ref_cursor che punta al risultato della query.
 NOTE:        Se p_QBE = 1 , ogni parametro deve contenere, nella prima parte,
              l'operatore da utilizzare nella where-condition.
              In p_extra_columns e p_order_by non devono essere passati anche la
              virgola iniziale (per p_extra_columns) e la stringa 'order by' (per
              p_order_by)
******************************************************************************/
   d_statement       AFC.t_statement;
   d_ref_cursor      AFC.t_ref_cursor;
begin
   d_statement := ' select COMUNI_TOTALI.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from COMUNI_TOTALI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_regione => p_regione
                                 , p_provincia => p_provincia
                                 , p_comune => p_comune
                                 , p_denominazione => p_denominazione
                                 , p_sigla => p_sigla
                                 , p_cap => p_cap
                                 , p_istat => p_istat
                                 , p_sigla_cfis => p_sigla_cfis
                                 , p_data_soppressione => p_data_soppressione
                                 , p_provincia_fusione => p_provincia_fusione
                                 , p_comune_fusione => p_comune_fusione
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- comuni_totali_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
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
) return integer is /* SLAVE_COPY */
/******************************************************************************
 NOME:        count_rows
 DESCRIZIONE: Ritorna il numero di righe della tabella gli attributi delle quali
              rispettano i valori indicati.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo e presente
                       un operatore, altrimenti viene usato quello di default ('=')
                    1: viene utilizzato l'operatore specificato all'inizio di ogni
                       attributo.
              p_other_condition: condizioni aggiuntive di base
              Chiavi e attributi della table
 RITORNA:     Numero di righe che rispettano la selezione indicata.
******************************************************************************/
   d_result          integer;
   d_statement       AFC.t_statement;
begin
   d_statement := ' select count( * ) from COMUNI_TOTALI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_regione => p_regione
                                 , p_provincia => p_provincia
                                 , p_comune => p_comune
                                 , p_denominazione => p_denominazione
                                 , p_sigla => p_sigla
                                 , p_cap => p_cap
                                 , p_istat => p_istat
                                 , p_sigla_cfis => p_sigla_cfis
                                 , p_data_soppressione => p_data_soppressione
                                 , p_provincia_fusione => p_provincia_fusione
                                 , p_comune_fusione => p_comune_fusione
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- comuni_totali_tpk.count_rows
--------------------------------------------------------------------------------
end comuni_totali_tpk;
/

