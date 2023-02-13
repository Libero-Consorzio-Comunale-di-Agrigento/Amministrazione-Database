--liquibase formatted sql

--changeset mturra:201901301231_208 runOnChange:true stripComments:false endDelimiter:/


create or replace package AFC_Error is
/******************************************************************************
 Package for error handling (see also SI4.get_error).
 REVISIONI:.
 Rev.  Data        Autore  Descrizione.
 00    03/05/2005  CZ      Prima emissione.
 01    06/09/2005  FT      Gestione tabella errori t_error_table.
 02    22/02/2006  FT      Aggiunta definizione errore modified_by_other_user.
 03    30/08/2006  FT      Modifica dichiarazione subtype per incompatibilità con versione 7 di Oracle.
 04    29/11/2006  FT      Aggiunta definizione errore exception_not_in_table.
 05    08/07/2011  FT      Allineati i commenti col nuovo standard di plsqldoc.
******************************************************************************/
   -- Package revision value
   s_revisione constant varchar2(30) := 'V1.05';
   /******************************************************************************
    Restituisce versione e revisione di distribuzione del package.
    %return varchar2: contiene versione e revisione.
    %note <UL>
          <LI> Primo numero: versione compatibilita del Package.</LI>
          <LI> Secondo numero: revisione del Package specification.</LI>
          <LI> Terzo numero: revisione del Package body.</LI>
          </UL>
   ******************************************************************************/
   function versione
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Type of error number code associated with the exceptions
   subtype t_error_number is binary_integer;
   -- Type of initial part of the messages to be interpreted by SI4.get_error
   d_error_msg varchar2(240);
   subtype t_error_msg is d_error_msg%type;
   -- Type of table for join error codes and application errors
   type t_error_table is table of t_error_msg index by binary_integer;
   -- Error number for successful check
   ok constant t_error_number := 1;
   -- Exception for diagnostic for insertion of null values: violation of not null constraints raised by the db
   null_value_ins_db            exception;
   -- Error message for diagnostic for insertion of null values: violation of not null constraints raised by the db
   null_value_ins_db_msg        constant t_error_msg := 'null value db';
   -- Error number for diagnostic for insertion of null values: violation of not null constraints raised by the db
   null_value_ins_db_number     constant t_error_number := -01400;
   pragma exception_init( null_value_ins_db, -01400 );
   -- Exception for diagnostic for update with null values: violation of not null constraints raised by the db
   null_value_upd_db            exception;
   -- Error message for diagnostic for update with null values: violation of not null constraints raised by the db
   null_value_upd_db_msg        constant t_error_msg := 'null value db';
   -- Error number for diagnostic for update with null values: violation of not null constraints raised by the db
   null_value_upd_db_number     constant t_error_number := -01407;
   pragma exception_init( null_value_upd_db, -01407 );
   -- Exception for diagnostic for validation of attribute check violation
   CC_violated_constraint               exception;
   -- Error message for diagnostic for validation of attribute check violation
   CC_violated_constraint_msg           constant t_error_msg := 'check constraint violated';
   -- Error number for diagnostic for validation of attribute check violation
   CC_violated_constraint_number        constant t_error_number := -02290;
   pragma exception_init( CC_violated_constraint, -02290 );
   -- Exception for RI administration: trigger
      ----------------------------------------
   -- Exception for diagnostic for insertion of row with attribute with FK constraint whose value is inexistent in father
   FK_insert_trigger               exception;
   -- Error message diagnostic for insertion of row with attribute with FK constraint whose value is inexistent in father
   FK_insert_trigger_msg           constant t_error_msg := 'not found on insert';
   -- Error number diagnostic for insertion of row with attribute with FK constraint whose value is inexistent in father
   FK_insert_trigger_number        constant t_error_number := -20002;
   pragma exception_init( FK_insert_trigger, -20002 );
   -- Exception for diagnostic for modification of row with attribute with FK constraint whose value is inexistent in father
   FK_update_trigger               exception;
   -- Error message for diagnostic for modification of row with attribute with FK constraint whose value is inexistent in father
   FK_update_trigger_msg           constant t_error_msg := 'not found on update';
   -- Error number for diagnostic for modification of row with attribute with FK constraint whose value is inexistent in father
   FK_update_trigger_number        constant t_error_number := -20003;
   pragma exception_init( FK_update_trigger, -20003 );
   -- Exception for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FR_update_trigger             exception;
   -- Error message for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FR_update_trigger_msg         constant t_error_msg := 'child exists on update';
   -- Error number for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FR_update_trigger_number      constant t_error_number := -20005;
   pragma exception_init( FR_update_trigger, -20005 );
   -- Exception for diagnostic for existence of child rows on restrict delete (FR: Foreign Referece)
   FR_delete_trigger             exception;
   -- Error message for diagnostic for existence of child rows on restrict delete (FR: Foreign Referece)
   FR_delete_trigger_msg         constant t_error_msg := 'child exists on delete';
   -- Error number for diagnostic for existence of child rows on restrict delete (FR: Foreign Referece)
   FR_delete_trigger_number      constant t_error_number := -20006;
   pragma exception_init( FR_delete_trigger, -20006 );
   -- Exception for RI administration: constraint
      -------------------------------------------
   -- Exception for diagnostic for insertion of row with attribute with FK constraint whose value is inexistent in father
   FK_insert_constraint               exception;
   -- Error message for diagnostic for insertion of row with attribute with FK constraint whose value is inexistent in father
   FK_insert_constraint_msg           constant t_error_msg := 'not found on insert';
   -- Error number for diagnostic for insertion of row with attribute with FK constraint whose value is inexistent in father
   FK_insert_constraint_number        constant t_error_number := -02291;
   pragma exception_init( FK_insert_constraint, -02291 );
   -- Exception for diagnostic for modification of row with attribute with FK constraint whose value is inexistent in father
   FK_update_constraint               exception;
   -- Error message for diagnostic for modification of row with attribute with FK constraint whose value is inexistent in father
   FK_update_constraint_msg           constant t_error_msg := 'not found on update';
   -- Error number for diagnostic for modification of row with attribute with FK constraint whose value is inexistent in father
   FK_update_constraint_number        constant t_error_number := -02291;
   pragma exception_init( FK_update_constraint, -02291 );
   -- Exception for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FR_update_constraint             exception;
   -- Error message for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FR_update_constraint_msg         constant t_error_msg := 'child exists on update';
   -- Error number for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FR_update_constraint_number      constant t_error_number := -02292;
   pragma exception_init( FR_update_constraint, -02292 );
   -- Exception for diagnostic for existence of child rows on restrict delete (FR: Foreign Referece)
   FR_delete_constraint             exception;
   -- Error message for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FR_delete_constraint_msg         constant t_error_msg := 'child exists on delete';
   -- Error number for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FR_delete_constraint_number      constant t_error_number := -02292;
   pragma exception_init( FR_delete_constraint, -02292 );
   -- Exception for RI administration: trigger postEvent
      --------------------------------------------------
   -- Exception for diagnostic for insertion of row with attribute with FK constraint whose value is inexistent in father
   FK_insert_postEvent               exception;
   -- Error message for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FK_insert_postEvent_msg           constant t_error_msg := 'not found on insert';
   -- Error number for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FK_insert_postEvent_number        constant t_error_number := -20008;
   pragma exception_init( FK_insert_postEvent, -20008 );
   -- Exception for diagnostic for modification of row with attribute with FK constraint whose value is inexistent in father
   FK_update_postEvent               exception;
   -- Error message for diagnostic for modification of row with attribute with FK constraint whose value is inexistent in father
   FK_update_postEvent_msg           constant t_error_msg := 'not found on update';
   -- Error number for diagnostic for modification of row with attribute with FK constraint whose value is inexistent in father
   FK_update_postEvent_number        constant t_error_number := -20008;
   pragma exception_init( FK_update_postEvent, -20008 );
   -- Exception for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FR_update_postEvent             exception;
   -- Error message for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FR_update_postEvent_msg         constant t_error_msg := 'child exists on update';
   -- Error number for diagnostic for existence of child rows on restrict update (FR: Foreign Referece)
   FR_update_postEvent_number      constant t_error_number := -20008;
   pragma exception_init( FR_update_postEvent, -20008 );
   -- Exception for diagnostic for existence of child rows on restrict delete (FR: Foreign Referece)
   FR_delete_postEvent             exception;
   -- Error message for diagnostic for existence of child rows on restrict delete (FR: Foreign Referece)
   FR_delete_postEvent_msg         constant t_error_msg := 'child exists on delete';
   -- Error number for diagnostic for existence of child rows on restrict delete (FR: Foreign Referece)
   FR_delete_postEvent_number      constant t_error_number := -20008;
   pragma exception_init( FR_delete_postEvent, -20008 );
   -- Exception for diagnostic for violation of unique constraint; the value -20007 is hard-coded in the templates Si4ForOracleX wich are used by PowerDesigner to generate the TIU triggers
   unique_key              exception;
   -- Error message for diagnostic for violation of unique constraint; the value -20007 is hard-coded in the templates Si4ForOracleX wich are used by PowerDesigner to generate the TIU triggers
   unique_key_msg          constant t_error_msg := 'unique key';
   -- Error number for diagnostic for violation of unique constraint; the value -20007 is hard-coded in the templates Si4ForOracleX wich are used by PowerDesigner to generate the TIU triggers
   unique_key_number       constant t_error_number := -20007;
   pragma exception_init( unique_key, -20007 );
   -- Exception for diagnostic for violation of unique constraints raised by the db
   unique_key_db           exception;
   -- Error message for diagnostic for violation of unique constraints raised by the db
   unique_key_db_msg       constant t_error_msg := 'unique key db';
   -- Error number for diagnostic for violation of unique constraints raised by the db
   unique_key_db_number    constant t_error_number := -00001;
   pragma exception_init( unique_key_db, -00001 );
   -- Exception for diagnostic for access to a record modified by anorher user
   modified_by_other_user           exception;
   -- Error message for diagnostic for access to a record modified by anorher user
   modified_by_other_user_msg       constant t_error_msg := 'Modified by another user';
   -- Error number for diagnostic for access to a record modified by anorher user
   modified_by_other_user_number    constant t_error_number := -20010;
   pragma exception_init( modified_by_other_user, -20010 );
   -- Exception for diagnostic for access to a Table_Package error inexistent in its error_table
   exception_not_in_table           exception;
   -- Error message for diagnostic for access to a Table_Package error inexistent in its error_table
   exception_not_in_table_msg       constant t_error_msg := 'Exception not in error_table';
   -- Error number for diagnostic for access to a Table_Package error inexistent in its error_table
   exception_not_in_table_number    constant t_error_number := -20011;
   pragma exception_init( exception_not_in_table, -20011 );
   -- Exception for diagnostic for the end user
   generic_error           exception;
   -- Error number for diagnostic for the end user
   generic_error_number    constant t_error_number := -20999;
   pragma exception_init( generic_error, -20999 );
   /******************************************************************************
    Generates a diagnostic message that can be interpreted by SI4.get_error structured as follows: ORA-<I>nnnnn</I>: <I> msg header </I> ( <I>table_name.constraint_name</I> )
   ******************************************************************************/
   procedure raise_application_error
   ( p_error_number in t_error_number
   , p_table_name in varchar2
   , p_constraint_name in varchar2
   );
   pragma restrict_references( raise_application_error, WNDS );
end AFC_Error;
/

