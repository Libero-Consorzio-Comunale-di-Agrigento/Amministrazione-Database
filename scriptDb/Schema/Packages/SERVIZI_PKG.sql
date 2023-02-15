CREATE OR REPLACE package servizi_pkg is
/******************************************************************************
 NOME:        servizi_tpk
 DESCRIZIONE: Gestione tabella SERVIZI.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    18/05/2007  MMalferrari  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'SERVIZI';
   -- Versione e revisione
   function versione
   return varchar2;
   function get_mailtag
   (
     p_istanza  in SERVIZI.istanza%type
   , p_modulo   in SERVIZI.modulo%type
   ) return varchar2;
end servizi_pkg;
/

