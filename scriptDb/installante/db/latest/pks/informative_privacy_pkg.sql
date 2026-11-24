--liquibase formatted sql

--changeset mturra:201901301231_241 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE informative_privacy_pkg is
/******************************************************************************
 NOME:        informative_privacy_pkg
 DESCRIZIONE: Gestione tabella INFORMATIVE_PRIVACY.
 ANNOTAZIONI: .
 REVISIONI:
 <CODE>
 Rev.  Data        Autore      Descrizione.
 00    12/03/2018  adadamo  Generazione automatica
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'INFORMATIVE_PRIVACY';
   -- Versione e revisione
   function versione
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Messaggio previsto per il numero di eccezione indicato
   function error_message
   ( p_error_number  in AFC_Error.t_error_number
   ) return AFC_Error.t_error_msg;
   pragma restrict_references( error_message, WNDS );
   /* SIAPKGen: Inizio blocco di generazione automatica per gestione db senza trigger */
   function is_informativa_required
   (P_ente  in varchar2
   ,p_modulo in varchar2
   ,p_utente in varchar2
   ) return number;
end informative_privacy_pkg;
/

