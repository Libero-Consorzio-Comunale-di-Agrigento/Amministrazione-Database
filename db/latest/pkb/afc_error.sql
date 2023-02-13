--liquibase formatted sql

--changeset mturra:201901301231_111 runOnChange:true stripComments:false


create or replace package body AFC_Error is
/******************************************************************************
 Package for error handling (see also SI4.get_error).
 REVISIONI.
 Rev.  Data        Autore  Descrizione.
 000   03/05/2005  CZ      Prima emissione.
 001   06/09/2005  FT      Gestione tabella errori t_error_table.
 002   08/07/2011  FT      Allineati i commenti col nuovo standard di plsqldoc.
******************************************************************************/
   -- Package body revision value
   s_revisione_body constant AFC.t_revision := '002';
--------------------------------------------------------------------------------
/******************************************************************************
 Versione e revisione di distribuzione del package.
******************************************************************************/
function versione
return varchar2 is
begin
   return AFC.version( s_revisione, s_revisione_body );
end; -- AFC_Error.versione
--------------------------------------------------------------------------------
/******************************************************************************
 Generates a diagnostic message that can be interpreted by SI4.get_error structured as follows: ORA-<I>nnnnn</I>: <I> msg header </I> ( <I>table_name.constraint_name</I> )
******************************************************************************/
procedure raise_application_error
( p_error_number in t_error_number
, p_table_name in varchar2
, p_constraint_name in varchar2
)
is
   d_error_msg varchar2(2000);
   d_msg_header t_error_msg;
begin
   if    p_error_number = FK_insert_trigger_number then
      d_msg_header := FK_insert_trigger_msg;
   elsif p_error_number = FK_update_trigger_number then
      d_msg_header := FK_update_trigger_msg;
   elsif p_error_number = FR_update_trigger_number then
      d_msg_header := FR_update_trigger_msg;
   elsif p_error_number = FR_delete_trigger_number then
      d_msg_header := FR_delete_trigger_msg;
   elsif p_error_number = FK_insert_constraint_number then
      d_msg_header := FK_insert_constraint_msg;
   elsif p_error_number = FK_update_constraint_number then
      d_msg_header := FK_update_constraint_msg;
   elsif p_error_number = FR_update_constraint_number then
      d_msg_header := FR_update_constraint_msg;
   elsif p_error_number = FR_delete_constraint_number then
      d_msg_header := FR_delete_constraint_msg;
   elsif p_error_number = FK_insert_postEvent_number then
      d_msg_header := FK_insert_postEvent_msg;
   elsif p_error_number = FK_update_postEvent_number then
      d_msg_header := FK_update_postEvent_msg;
   elsif p_error_number = FR_update_postEvent_number then
      d_msg_header := FR_update_postEvent_msg;
   elsif p_error_number = FR_delete_postEvent_number then
      d_msg_header := FR_delete_postEvent_msg;
   elsif p_error_number = unique_key_number then
      d_msg_header := unique_key_msg;
   elsif p_error_number = unique_key_db_number then
      d_msg_header := unique_key_db_msg;
   else
      DbC.PRE( false, 'invalid p_error_number' );
   end if;
   d_error_msg := d_msg_header || ' (' || p_table_name || '.' || p_constraint_name || ')';
   dbms_standard.raise_application_error( p_error_number, d_error_msg, true );
end; -- AFC_Error.raise_application_error
--------------------------------------------------------------------------------
end AFC_Error;
/

