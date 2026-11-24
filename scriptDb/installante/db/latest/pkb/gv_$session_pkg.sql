--liquibase formatted sql

--changeset mturra:201901301231_142 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY gv_$session_pkg is
/******************************************************************************
 NOME:        sys.GV_$SESSION_tpk
 DESCRIZIONE: Gestione tabella sys.GV_$SESSION.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   06/04/2009  MMalferrari  Prima emissione.
 001   16/01/2017  SNegroni     Aggiunta get_program
 002   25/03/2019  SNegroni     Aggiunta la min alla get_program #33980
 003   28/03/2019  SNegroni     Aggiunta la (action) nella get_info
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '003';
--------------------------------------------------------------------------------
function versione
return varchar2 is
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
end versione; -- sys.GV_$SESSION_tpk.versione
function exists_id
(
 p_audsid  in number
) return number is
/******************************************************************************
 NOME:        exists_id
 DESCRIZIONE: Esistenza riga con chiave indicata.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
 NOTE:        cfr. existsId per ritorno valori boolean.
******************************************************************************/
   d_result number;
begin
   select count(1)
   into   d_result
   from   sys.GV_$SESSION
   where
   audsid = p_audsid
   ;
   if d_result <> 0 then
      d_result := 1;
   end if;
   return  d_result;
end exists_id; -- sys.GV_$SESSION_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_audsid  in number
) return boolean is
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_audsid => p_audsid
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- sys.GV_$SESSION_tpk.existsId
--------------------------------------------------------------------------------
function get_sid
(
  p_audsid  in number
) return number is
/******************************************************************************
 NOME:        get_sid
 DESCRIZIONE: Getter per attributo serial# di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.sid%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result number;
begin
   select min(sid)
   into   d_result
   from   sys.GV_$SESSION
   where
   audsid = p_audsid
   ;
   return  d_result;
end get_sid; -- sys.GV_$SESSION_tpk.get_sid
--------------------------------------------------------------------------------
function get_serial#
(
  p_audsid  in number
) return number is
/******************************************************************************
 NOME:        get_serial#
 DESCRIZIONE: Getter per attributo serial# di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.serial#%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result number;
begin
   select min(serial#)
   into   d_result
   from   sys.GV_$SESSION
   where
   audsid = p_audsid
   ;
   return  d_result;
end get_serial#; -- sys.GV_$SESSION_tpk.get_serial#
--------------------------------------------------------------------------------
function get_osuser
(
  p_audsid  in number
) return varchar2 is
/******************************************************************************
 NOME:        get_osuser
 DESCRIZIONE: Getter per attributo osuser di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.osuser%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result varchar2(100);
begin
   select min(osuser)
   into   d_result
   from   sys.GV_$SESSION
   where
   audsid = p_audsid
   ;
   return  d_result;
end get_osuser; -- sys.GV_$SESSION_tpk.get_osuser
--------------------------------------------------------------------------------
function get_machine
(
  p_audsid  in number
) return varchar2 is
/******************************************************************************
 NOME:        get_machine
 DESCRIZIONE: Getter per attributo machine di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.machine%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result varchar2(100);
begin
   select min(machine)
   into   d_result
   from   sys.GV_$SESSION
   where
   audsid = p_audsid
   ;
   return  d_result;
end get_machine; -- sys.GV_$SESSION_tpk.get_machine
--------------------------------------------------------------------------------
function get_terminal
(
  p_audsid  in number
) return varchar2 is
/******************************************************************************
 NOME:        get_terminal
 DESCRIZIONE: Getter per attributo terminal di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.terminal%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result varchar2(100);
begin
   select min(terminal)
   into   d_result
   from   sys.GV_$SESSION
   where
   audsid = p_audsid
   ;
   return  d_result;
end get_terminal; -- sys.GV_$SESSION_tpk.get_terminal
--------------------------------------------------------------------------------
function get_status
(
  p_audsid  in number
) return varchar2 is
/******************************************************************************
 NOME:        get_status
 DESCRIZIONE: Getter per attributo status di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.terminal%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result varchar2(100);
begin
   select min(status)
   into   d_result
   from   sys.GV_$SESSION
   where
   audsid = p_audsid
   ;
   return  d_result;
end get_status; -- sys.GV_$SESSION_tpk.get_terminal
--------------------------------------------------------------------------------
function get_info
(
  p_audsid in number
) return varchar2 is
/******************************************************************************
 NOME:        get_info
 DESCRIZIONE: Getter per attributo info di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     stringa contenente machine:: termina::osuser e action se presente
 NOTE:        A volte la action ha solo numeri e virgole in quel caso non la memorizziamo
******************************************************************************/
   d_result varchar2(1000);
begin
   select min(UPPER(RTRIM(MACHINE,CHR(0))||'::'||TERMINAL||'::'||OSUSER)) || min(decode(length(translate(action,'a,0123456789','a')),null,to_char(null),' ('|| action||')'))
   into   d_result
   from   sys.GV_$SESSION
   where
   audsid = p_audsid
   ;
   return  d_result;
end;

function get_program
   (
     p_audsid in number
   ) return varchar2
   is
   d_result varchar2(1000);
begin
   select min(program) -- rev.2 #33980 aggiunta min
   into   d_result
   from   sys.GV_$SESSION
   where
   audsid = p_audsid
   ;
   return  d_result;
end;
--------------------------------------------------------------------------------
function where_condition
( p_QBE  in number default 0
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
) return AFC.t_statement is
/******************************************************************************
 NOME:        where_condition
 DESCRIZIONE: Ritorna la where_condition per lo statement di select di get_rows e count_rows.
 PARAMETRI:   p_other_condition
              p_QBE 0: se l'operatore da utilizzare nella where-condition e
                       quello di default ('=')
                    1: se l'operatore da utilizzare nella where-condition e
                       quello specificato per ogni attributo.
              Chiavi e attributi della table
 RITORNA:     AFC.t_statement.
 NOTE:        Se p_QBE = 1 , ogni parametro deve contenere, nella prima parte,
              l'operatore da utilizzare nella where-condition.
******************************************************************************/
   d_statement AFC.t_statement;
begin
   d_statement := ' where ( 1 = 1 '
               || AFC.get_field_condition( ' and ( audsid ', p_audsid, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( inst_id ', p_inst_id , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( saddr ', p_saddr , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( sid ', p_sid , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( serial# ', p_serial# , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( paddr ', p_paddr , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( user# ', p_user# , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( username ', p_username , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( command ', p_command , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( ownerid ', p_ownerid , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( taddr ', p_taddr , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( lockwait ', p_lockwait , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( status ', p_status , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( server ', p_server , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( schema# ', p_schema# , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( schemaname ', p_schemaname , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( osuser ', p_osuser , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( process ', p_process , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( machine ', p_machine , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( terminal ', p_terminal , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( program ', p_program , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( type ', p_type , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( sql_address ', p_sql_address , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( sql_hash_value ', p_sql_hash_value , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( prev_sql_addr ', p_prev_sql_addr , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( prev_hash_value ', p_prev_hash_value , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( module ', p_module , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( module_hash ', p_module_hash , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( action ', p_action , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( action_hash ', p_action_hash , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( client_info ', p_client_info , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( fixed_table_sequence ', p_fixed_table_sequence , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( row_wait_obj# ', p_row_wait_obj# , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( row_wait_file# ', p_row_wait_file# , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( row_wait_block# ', p_row_wait_block# , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( row_wait_row# ', p_row_wait_row# , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( logon_time ', p_logon_time , ' )', p_QBE, AFC.date_format )
               || AFC.get_field_condition( ' and ( last_call_et ', p_last_call_et , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( pdml_enabled ', p_pdml_enabled , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( failover_type ', p_failover_type , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( failover_method ', p_failover_method , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( failed_over ', p_failed_over , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( resource_consumer_group ', p_resource_consumer_group , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( pdml_status ', p_pdml_status , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( pddl_status ', p_pddl_status , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( pq_status ', p_pq_status , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( current_queue_duration ', p_current_queue_duration , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( client_identifier ', p_client_identifier , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- sys.GV_$SESSION_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
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
) return AFC.t_ref_cursor is
/******************************************************************************
 NOME:        get_rows
 DESCRIZIONE: Ritorna il risultato di una query in base ai valori che passiamo.
 PARAMETRI:   p_QBE 0: se l'operatore da utilizzare nella where-condition e
                       quello di default ('=')
                    1: se l'operatore da utilizzare nella where-condition e
                       quello specificato per ogni attributo.
              p_other_condition: condizioni aggiuntive di base
              p_order_by: condizioni di ordinamento
              p_extra_columns: colonne aggiungere alla select
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
   d_statement := ' select sys.GV_$SESSION.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from sys.GV_$SESSION '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_audsid => p_audsid
                                 , p_inst_id => p_inst_id
                                 , p_saddr => p_saddr
                                 , p_sid => p_sid
                                 , p_serial# => p_serial#
                                 , p_paddr => p_paddr
                                 , p_user# => p_user#
                                 , p_username => p_username
                                 , p_command => p_command
                                 , p_ownerid => p_ownerid
                                 , p_taddr => p_taddr
                                 , p_lockwait => p_lockwait
                                 , p_status => p_status
                                 , p_server => p_server
                                 , p_schema# => p_schema#
                                 , p_schemaname => p_schemaname
                                 , p_osuser => p_osuser
                                 , p_process => p_process
                                 , p_machine => p_machine
                                 , p_terminal => p_terminal
                                 , p_program => p_program
                                 , p_type => p_type
                                 , p_sql_address => p_sql_address
                                 , p_sql_hash_value => p_sql_hash_value
                                 , p_prev_sql_addr => p_prev_sql_addr
                                 , p_prev_hash_value => p_prev_hash_value
                                 , p_module => p_module
                                 , p_module_hash => p_module_hash
                                 , p_action => p_action
                                 , p_action_hash => p_action_hash
                                 , p_client_info => p_client_info
                                 , p_fixed_table_sequence => p_fixed_table_sequence
                                 , p_row_wait_obj# => p_row_wait_obj#
                                 , p_row_wait_file# => p_row_wait_file#
                                 , p_row_wait_block# => p_row_wait_block#
                                 , p_row_wait_row# => p_row_wait_row#
                                 , p_logon_time => p_logon_time
                                 , p_last_call_et => p_last_call_et
                                 , p_pdml_enabled => p_pdml_enabled
                                 , p_failover_type => p_failover_type
                                 , p_failover_method => p_failover_method
                                 , p_failed_over => p_failed_over
                                 , p_resource_consumer_group => p_resource_consumer_group
                                 , p_pdml_status => p_pdml_status
                                 , p_pddl_status => p_pddl_status
                                 , p_pq_status => p_pq_status
                                 , p_current_queue_duration => p_current_queue_duration
                                 , p_client_identifier => p_client_identifier
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- sys.GV_$SESSION_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
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
) return integer is
/******************************************************************************
 NOME:        count_rows
 DESCRIZIONE: Ritorna il numero di righe della tabella gli attributi delle quali
              rispettano i valori indicati.
 PARAMETRI:   p_other_condition
              p_QBE 0: se l'operatore da utilizzare nella where-condition e
                       quello di default ('=')
                    1: se l'operatore da utilizzare nella where-condition e
                       quello specificato per ogni attributo.
              Chiavi e attributi della table
 RITORNA:     Numero di righe che rispettano la selezione indicata.
******************************************************************************/
   d_result          integer;
   d_statement       AFC.t_statement;
begin
   d_statement := ' select count( * ) from sys.GV_$SESSION '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_audsid => p_audsid
                                 , p_inst_id => p_inst_id
                                 , p_saddr => p_saddr
                                 , p_sid => p_sid
                                 , p_serial# => p_serial#
                                 , p_paddr => p_paddr
                                 , p_user# => p_user#
                                 , p_username => p_username
                                 , p_command => p_command
                                 , p_ownerid => p_ownerid
                                 , p_taddr => p_taddr
                                 , p_lockwait => p_lockwait
                                 , p_status => p_status
                                 , p_server => p_server
                                 , p_schema# => p_schema#
                                 , p_schemaname => p_schemaname
                                 , p_osuser => p_osuser
                                 , p_process => p_process
                                 , p_machine => p_machine
                                 , p_terminal => p_terminal
                                 , p_program => p_program
                                 , p_type => p_type
                                 , p_sql_address => p_sql_address
                                 , p_sql_hash_value => p_sql_hash_value
                                 , p_prev_sql_addr => p_prev_sql_addr
                                 , p_prev_hash_value => p_prev_hash_value
                                 , p_module => p_module
                                 , p_module_hash => p_module_hash
                                 , p_action => p_action
                                 , p_action_hash => p_action_hash
                                 , p_client_info => p_client_info
                                 , p_fixed_table_sequence => p_fixed_table_sequence
                                 , p_row_wait_obj# => p_row_wait_obj#
                                 , p_row_wait_file# => p_row_wait_file#
                                 , p_row_wait_block# => p_row_wait_block#
                                 , p_row_wait_row# => p_row_wait_row#
                                 , p_logon_time => p_logon_time
                                 , p_last_call_et => p_last_call_et
                                 , p_pdml_enabled => p_pdml_enabled
                                 , p_failover_type => p_failover_type
                                 , p_failover_method => p_failover_method
                                 , p_failed_over => p_failed_over
                                 , p_resource_consumer_group => p_resource_consumer_group
                                 , p_pdml_status => p_pdml_status
                                 , p_pddl_status => p_pddl_status
                                 , p_pq_status => p_pq_status
                                 , p_current_queue_duration => p_current_queue_duration
                                 , p_client_identifier => p_client_identifier
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- sys.GV_$SESSION_tpk.count_rows
--------------------------------------------------------------------------------
end GV_$SESSION_pkg;
/
