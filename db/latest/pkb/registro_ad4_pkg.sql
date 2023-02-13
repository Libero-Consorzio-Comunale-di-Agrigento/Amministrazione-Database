--liquibase formatted sql

--changeset mturra:201901301231_167 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY registro_ad4_pkg is
/******************************************************************************
 NOME:        external_functions_pkg
 DESCRIZIONE: Gestione tabella EXTERNAL_FUNCTIONS.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   27/11/2008  MMalferrari  Prima emissione.
 001   28/12/2009  SNeg          Gestione controlli caratteri delimitatori
 002   01/04/2010  SNeg          Impedimento cancellazione voci di registro
 003   21/11/2012 SNeg          Anche se esiste server alternativo posso ribaltare ldap sul primo
******************************************************************************/
s_revisione_body      constant AFC.t_revision := '003';
s_error_table AFC_Error.t_error_table;
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
end versione; -- external_functions_tpk.versione
function error_message
( p_error_number  in AFC_Error.t_error_number
) return AFC_Error.t_error_msg is
/******************************************************************************
 NOME:        error_message
 DESCRIZIONE: Messaggio previsto per il numero di eccezione indicato.
 NOTE:        Restituisce il messaggio abbinato al numero indicato nella tabella
              s_error_table del Package. Se p_error_number non e presente nella
              tabella s_error_table viene lanciata l'exception -20011 (vedi AFC_Error)
******************************************************************************/
   d_result AFC_Error.t_error_msg;
begin
   if s_error_table.exists( p_error_number )
   then
      d_result := s_error_table( p_error_number );
   else
      raise_application_error( AFC_Error.exception_not_in_table_number
                             , AFC_Error.exception_not_in_table_msg
                             );
   end if;
   return  d_result;
end error_message; -- anagrafe_familiari_pkg.error_message
function is_DI_post_ok
( p_new_chiave in registro.chiave%type
, p_new_stringa in registro.stringa%type
, p_new_valore in registro.valore%type
, p_old_chiave in registro.chiave%type
, p_old_stringa in registro.stringa%type
, p_old_valore in registro.valore%type
) return AFC_Error.t_error_number
/******************************************************************************
 NOME:        is_DI_ok.
 DESCRIZIONE: gestione della Data Integrity:
 PARAMETRI:
 RETURN:      1 se tutti i controlli danno esito positivo, l'error_code altrimenti
 NOTE:        --
******************************************************************************/
is
   d_result AFC_Error.t_error_number;
   d_inserting boolean;
   d_updating boolean;
   d_deleting boolean;
   d_chiave_ldap registro.chiave%type:='PRODUCTS/LDAPCONFIG';
   d_ldapMapping registro.valore%type;
   d_authorizationMapping registro.valore%type;
   d_existsServerAlternativo number:=0;
   d_valore varchar2(40);
begin
   if nvl(p_new_chiave, ' ') <> nvl(p_old_chiave, ' ')
   or nvl(p_new_stringa, ' ') <> nvl(p_old_stringa, ' ')
   or nvl(p_new_valore, ' ') <> nvl(p_old_valore, ' ')
   then
      ACCESSO.ATTIVA_ELIMINAZIONE_TRACCIA(p_new_chiave, p_new_stringa, p_new_valore, p_old_chiave, p_old_stringa, p_old_valore);
      d_inserting := p_new_chiave is not null and p_new_stringa is not null and p_old_chiave is null and p_old_stringa is null;
      d_updating  := p_new_chiave is not null and p_new_stringa is not null and p_old_chiave is not null and p_old_stringa is not null;
      d_deleting  := p_new_chiave is null and p_new_stringa is null and p_old_chiave is not null and p_old_stringa is not null;
      -- Controlli sulle stringhe esistenti sotto la chiave 'PRODUCTS/LDAPCONFIG'
      if instr(p_new_chiave, d_chiave_ldap) > 0 then
         if not d_deleting then
            select lower(valore)
              into d_authorizationMapping
              from registro
             where chiave = d_chiave_ldap
               and upper(stringa) = 'AUTHORIZATIONMAPPING'
            ;
            select lower(valore)
              into d_ldapMapping
              from registro
             where chiave = d_chiave_ldap
               and upper(stringa) = 'LDAPMAPPING'
            ;
            select count(1)
              into d_existsServerAlternativo
              from registro
             where chiave like d_chiave_ldap||'/SERVER%'
            ;
            -- tolto controllo, se anche esiste server alternativo il ribaltamento è fatto solo sul primo
--            if p_new_chiave = d_chiave_ldap and upper(p_new_stringa) = 'LDAPMAPPING' and d_existsServerAlternativo > 0 and p_new_valore <> 'no' then
--               d_result := s_exists_server_alt_num;
--            end if;
            if p_new_chiave = d_chiave_ldap and upper(p_new_stringa) = 'LDAPMAPPING' and d_authorizationMapping <> 'no'
            or p_new_chiave = d_chiave_ldap and upper(p_new_stringa) = 'AUTHORIZATIONMAPPING' and d_ldapMapping <> 'no' then
               d_result := s_mapping_alternativi_num;
            end if;
            if p_new_chiave = d_chiave_ldap and upper(p_new_stringa) = 'DELIMITATORE' and
               length(p_new_valore) != length(translate(p_new_valore,'a,=+<>#;/','a'))  then
               -- sta inserendo caratteri non ammessi
               d_result := s_caratteri_non_ammessi_num;
            end if;
         end if;
         if d_inserting then
            -- Se ldapMapping e' attivo, non e' possibile definire server alternativi.
            if instr(p_new_chiave, d_chiave_ldap||'/SERVER') > 0 and d_ldapMapping <> 'no' then
               d_result := s_ldap_mapping_attivo_num;
            end if;
         end if;
         if( d_result = afc_error.ok )
         then
            null;
         end if;
      end if;
      -- controlli sulle chiavi relative a invio password
      if p_old_chiave in ('PRODUCTS/AD4/TESTO_INVIO_PASSWORD','PRODUCTS/AD4/TESTO_INVIO_PASSWORD/DEFAULT') then
         if d_deleting then -- non si possono cancellare
            d_result := s_canc_non_ammessa_num;
         elsif d_updating and p_old_chiave =  'PRODUCTS/AD4/TESTO_INVIO_PASSWORD/DEFAULT' then
            d_result := s_modifica_non_ammessa_num;
         end if;
      end if;
      -- crypt con md5 possibile solo se characterset del db:
       if upper(p_new_stringa) = upper('Md5') and
        upper(nvl(p_new_valore,'x')) != upper(nvl(p_old_valore,'x')) -- se cambia il valore
        and upper(p_new_valore) in ('YES','SI','S') -- deve essere criptata con md5
        then
         select value
           into d_valore
           from nls_database_parameters
          where parameter='NLS_CHARACTERSET';
        if d_valore != 'WE8MSWIN1252' then
            d_result := s_md5_non_compatibile_char_num;
        end if;
    end if;
   end if;
   return d_result;
end;
procedure chk_DI_post
( p_new_chiave in registro.chiave%type
, p_new_stringa in registro.stringa%type
, p_new_valore in registro.valore%type
, p_old_chiave in registro.chiave%type
, p_old_stringa in registro.stringa%type
, p_old_valore in registro.valore%type
) is
/******************************************************************************
 NOME:        chk_DI.
 DESCRIZIONE: gestione della Data Integrity:
 PARAMETRI:
 NOTE:        --
******************************************************************************/
   d_result AFC_Error.t_error_number;
begin
   d_result := is_DI_post_ok ( p_new_chiave => p_new_chiave
                             , p_new_stringa => p_new_stringa
                             , p_new_valore => p_new_valore
                             , p_old_chiave => p_old_chiave
                             , p_old_stringa => p_old_stringa
                             , p_old_valore => p_old_valore
                             );
   if not ( d_result = AFC_Error.ok )
   then
      raise_application_error( d_result, s_error_table( d_result ) );
   end if;
end;
--------------------------------------------------------------------------------
begin
   -- inserimento degli errori nella tabella
   s_error_table( s_ldap_mapping_attivo_num ) := s_ldap_mapping_attivo_msg;
   s_error_table( s_exists_server_alt_num ) := s_exists_server_alt_msg;
   s_error_table( s_mapping_alternativi_num ) := s_mapping_alternativi_msg;
   s_error_table( s_caratteri_non_ammessi_num ) := s_caratteri_non_ammessi_msg;
   s_error_table( s_canc_non_ammessa_num ) := s_canc_non_ammessa_msg;
   s_error_table( s_modifica_non_ammessa_num ) := s_modifica_non_ammessa_msg;
   s_error_table( s_md5_non_compatibile_char_num ) := s_md5_non_compatibile_char_msg;
end registro_ad4_pkg;
/
