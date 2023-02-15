CREATE OR REPLACE package registro_ad4_pkg is
/******************************************************************************
 NOME:        registro_ad4_pkg
 DESCRIZIONE: Gestione tabella REGISTRO per funzionalita' specifiche di AD4.
 ANNOTAZIONI: .
 REVISIONI:
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    27/11/2008  MMalferrari  Prima emissione.
 01    28/12/2009  SNeg          Gestione controlli caratteri delimitatori
 02    01/04/2010  SNeg          Impedimento cancellazione voci di registro
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.02';
   s_table_name constant AFC.t_object_name := 'registro';
   -- Exceptions
   ldap_mapping_attivo exception;
   pragma exception_init( ldap_mapping_attivo, -20902 );
   s_ldap_mapping_attivo_num constant AFC_Error.t_error_number := -20902;
   s_ldap_mapping_attivo_msg constant AFC_Error.t_error_msg := 'Impossibile completare l''operazione. LdapMapping attivo.';
   exists_server_alternativo exception;
   pragma exception_init( exists_server_alternativo, -20903 );
   s_exists_server_alt_num constant AFC_Error.t_error_number := -20903;
   s_exists_server_alt_msg constant AFC_Error.t_error_msg := 'Impossibile completare l''operazione. Esistono server alternativi.';
   mapping_alternativi exception;
   pragma exception_init( mapping_alternativi, -20904 );
   s_mapping_alternativi_num constant AFC_Error.t_error_number := -20904;
   s_mapping_alternativi_msg constant AFC_Error.t_error_msg := 'LdapMapping ed AuthorizationMapping alternativi.';
   caratteri_non_ammessi exception;
   pragma exception_init( caratteri_non_ammessi, -20905 );
   s_caratteri_non_ammessi_num constant AFC_Error.t_error_number := -20905;
   s_caratteri_non_ammessi_msg constant AFC_Error.t_error_msg := 'Impossibile usare i caratteri ,=+<>#; nel delimitatore.';
   canc_non_ammessa exception;
   pragma exception_init( canc_non_ammessa, -20906 );
   s_canc_non_ammessa_num constant AFC_Error.t_error_number := -20906;
   s_canc_non_ammessa_msg constant AFC_Error.t_error_msg := 'Impossibile eliminare le voci dal registro.';
   modifica_non_ammessa exception;
   pragma exception_init( modifica_non_ammessa, -20907 );
   s_modifica_non_ammessa_num constant AFC_Error.t_error_number := -20907;
   s_modifica_non_ammessa_msg constant AFC_Error.t_error_msg := 'Impossibile modificare i valori.';
   md5_non_compatibile_char exception;
   pragma exception_init( md5_non_compatibile_char, -20908 );
   s_md5_non_compatibile_char_num constant AFC_Error.t_error_number := -20908;
   s_md5_non_compatibile_char_msg constant AFC_Error.t_error_msg := 'Impossibile criptare le password con MD5 se characterset diverso da WE8MSWIN1252.';
   -- Versione e revisione
   function versione
   return varchar2;
      -- Messaggio previsto per il numero di eccezione indicato
   function error_message
   ( p_error_number  in AFC_Error.t_error_number
   ) return AFC_Error.t_error_msg;
   function is_DI_post_ok
   ( p_new_chiave in registro.chiave%type
   , p_new_stringa in registro.stringa%type
   , p_new_valore in registro.valore%type
   , p_old_chiave in registro.chiave%type
   , p_old_stringa in registro.stringa%type
   , p_old_valore in registro.valore%type
   ) return AFC_Error.t_error_number;
   procedure chk_DI_post
   ( p_new_chiave in registro.chiave%type
   , p_new_stringa in registro.stringa%type
   , p_new_valore in registro.valore%type
   , p_old_chiave in registro.chiave%type
   , p_old_stringa in registro.stringa%type
   , p_old_valore in registro.valore%type
   );
end registro_ad4_pkg;
/

