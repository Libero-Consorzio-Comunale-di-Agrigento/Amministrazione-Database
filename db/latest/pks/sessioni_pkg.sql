--liquibase formatted sql

--changeset snegroni:20190603_1444 runOnChange:true stripComments:false endDelimiter:/


create or replace package SESSIONI_pkg is
/******************************************************************************
 NOME:        SESSIONI
 DESCRIZIONE: Gestione tabella sys.GV_$SESSION.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore   Descrizione.
 00    03/06/2019 SNegroni Funzioni per ottenere informazioni sulle sessioni
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   -- Versione e revisione
   function versione
   return varchar2;
    -- Esistenza riga con chiave indicata
   function exists_id
   (
    p_audsid  in number
   ) return number;
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId
   (
    p_audsid  in number
   ) return boolean;
   -- Getter per attributo sid di riga identificata da chiave
   function get_sid
   (
     p_audsid  in number
   ) return number;
   -- Getter per attributo serial# di riga identificata da chiave
   function get_serial#
   (
     p_audsid  in number
   ) return number;
   -- Getter per attributo machine di riga identificata da chiave
   function get_machine
   (
     p_audsid  in number
   ) return varchar2;
   -- Getter per attributo terminal di riga identificata da chiave
   function get_terminal
   (
     p_audsid  in number
   ) return varchar2;
   -- Getter per attributo osuser di riga identificata da chiave
   function get_osuser
   (
     p_audsid  in number
   ) return varchar2;
   -- Getter per attributo status di riga identificata da chiave
   function get_status
   (
     p_audsid  in number
   ) return varchar2;
   function get_info
   (
     p_audsid in number
   ) return varchar2;
   function get_program
   (
     p_audsid in number
   ) return varchar2;
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_audsid  in varchar2 default null
   , p_inst_id  in varchar2 default null
   , p_saddr  in varchar2 default null
   , p_sid  in varchar2 default null
   , p_serial#  in varchar2 default null
   , p_paddr  in varchar2 default null
   , p_user#  in varchar2 default null
   , p_username  in varchar2 default null
   , p_command  in varchar2 default null
   , p_ownerid  in varchar2 default null
   , p_taddr  in varchar2 default null
   , p_lockwait  in varchar2 default null
   , p_status  in varchar2 default null
   , p_server  in varchar2 default null
   , p_schema#  in varchar2 default null
   , p_schemaname  in varchar2 default null
   , p_osuser  in varchar2 default null
   , p_process  in varchar2 default null
   , p_machine  in varchar2 default null
   , p_terminal  in varchar2 default null
   , p_program  in varchar2 default null
   , p_type  in varchar2 default null
   , p_sql_address  in varchar2 default null
   , p_sql_hash_value  in varchar2 default null
   , p_prev_sql_addr  in varchar2 default null
   , p_prev_hash_value  in varchar2 default null
   , p_module  in varchar2 default null
   , p_module_hash  in varchar2 default null
   , p_action  in varchar2 default null
   , p_action_hash  in varchar2 default null
   , p_client_info  in varchar2 default null
   , p_fixed_table_sequence  in varchar2 default null
   , p_row_wait_obj#  in varchar2 default null
   , p_row_wait_file#  in varchar2 default null
   , p_row_wait_block#  in varchar2 default null
   , p_row_wait_row#  in varchar2 default null
   , p_logon_time  in varchar2 default null
   , p_last_call_et  in varchar2 default null
   , p_pdml_enabled  in varchar2 default null
   , p_failover_type  in varchar2 default null
   , p_failover_method  in varchar2 default null
   , p_failed_over  in varchar2 default null
   , p_resource_consumer_group  in varchar2 default null
   , p_pdml_status  in varchar2 default null
   , p_pddl_status  in varchar2 default null
   , p_pq_status  in varchar2 default null
   , p_current_queue_duration  in varchar2 default null
   , p_client_identifier  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_audsid  in varchar2 default null
   , p_inst_id  in varchar2 default null
   , p_saddr  in varchar2 default null
   , p_sid  in varchar2 default null
   , p_serial#  in varchar2 default null
   , p_paddr  in varchar2 default null
   , p_user#  in varchar2 default null
   , p_username  in varchar2 default null
   , p_command  in varchar2 default null
   , p_ownerid  in varchar2 default null
   , p_taddr  in varchar2 default null
   , p_lockwait  in varchar2 default null
   , p_status  in varchar2 default null
   , p_server  in varchar2 default null
   , p_schema#  in varchar2 default null
   , p_schemaname  in varchar2 default null
   , p_osuser  in varchar2 default null
   , p_process  in varchar2 default null
   , p_machine  in varchar2 default null
   , p_terminal  in varchar2 default null
   , p_program  in varchar2 default null
   , p_type  in varchar2 default null
   , p_sql_address  in varchar2 default null
   , p_sql_hash_value  in varchar2 default null
   , p_prev_sql_addr  in varchar2 default null
   , p_prev_hash_value  in varchar2 default null
   , p_module  in varchar2 default null
   , p_module_hash  in varchar2 default null
   , p_action  in varchar2 default null
   , p_action_hash  in varchar2 default null
   , p_client_info  in varchar2 default null
   , p_fixed_table_sequence  in varchar2 default null
   , p_row_wait_obj#  in varchar2 default null
   , p_row_wait_file#  in varchar2 default null
   , p_row_wait_block#  in varchar2 default null
   , p_row_wait_row#  in varchar2 default null
   , p_logon_time  in varchar2 default null
   , p_last_call_et  in varchar2 default null
   , p_pdml_enabled  in varchar2 default null
   , p_failover_type  in varchar2 default null
   , p_failover_method  in varchar2 default null
   , p_failed_over  in varchar2 default null
   , p_resource_consumer_group  in varchar2 default null
   , p_pdml_status  in varchar2 default null
   , p_pddl_status  in varchar2 default null
   , p_pq_status  in varchar2 default null
   , p_current_queue_duration  in varchar2 default null
   , p_client_identifier  in varchar2 default null
   ) return integer;
end SESSIONI_pkg;
/