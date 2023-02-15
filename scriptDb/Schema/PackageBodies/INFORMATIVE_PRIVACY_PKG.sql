CREATE OR REPLACE PACKAGE BODY informative_privacy_pkg is
/******************************************************************************
 NOME:        informative_privacy_pkg
 DESCRIZIONE: Gestione tabella INFORMATIVE_PRIVACY.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore      Descrizione.
 000   12/03/2018  adadamo  Generazione automatica
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '000 - 12/03/2018';
   -- Variabili di appoggio per i parametri di ins, upd e del   /* SIAPKGen: generazione automatica */
   s_inserting boolean := false;
   s_updating boolean := false;
   s_deleting boolean := false;
   --   /* SIAPKGen: generazione automatica */
   s_error_table AFC_Error.t_error_table;
   s_error_detail AFC_Error.t_error_table;
   s_warning afc.t_statement;
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
end versione; -- informative_privacy_pkg.versione
--------------------------------------------------------------------------------
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
   d_detail AFC_Error.t_error_msg;
begin
   if s_error_detail.exists( p_error_number )
   then
      d_detail := s_error_detail( p_error_number );
   end if;
   if s_error_table.exists( p_error_number )
   then
      d_result := s_error_table( p_error_number ) || d_detail;
      s_error_detail( p_error_number ) := '';
   else
      raise_application_error( AFC_Error.exception_not_in_table_number
                             , AFC_Error.exception_not_in_table_msg
                             );
   end if;
   return  d_result;
end error_message; -- informative_privacy_pkg.error_message
--------------------------------------------------------------------------------
function is_informativa_required
( P_ente  in varchar2
, p_modulo in varchar2
, p_utente in varchar2
) return number is
    d_result    number;
begin
    begin
        select 0
          into d_result
          from informative_privacy inpr, utenti_privacy utpr
         where UTPR.ENTE = inpr.ente
           and utpr.modulo = inpr.modulo
           and utpr.utente = p_utente
           and inpr.ente = p_ente
           and inpr.modulo = p_modulo
           and UTPR.DATA_ULTIMA_ACCETTAZIONE >= INPR.DATA_INFORMATIVA;
    exception when no_data_found then
        d_result := 1;
    end;
    return d_result;
end;
begin
   -- inserimento degli errori nella tabella
   NULL;
end informative_privacy_pkg;
/

