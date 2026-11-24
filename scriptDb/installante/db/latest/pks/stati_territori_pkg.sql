--liquibase formatted sql

--changeset mturra:201901301231_284 runOnChange:true stripComments:false endDelimiter:/


create or replace package stati_territori_pkg is
/******************************************************************************
 NOME:        stati_territori_pkg
 DESCRIZIONE: Gestione tabella STATI_TERRITORI.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    14/01/2008  MMalferrari  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   -- Versione e revisione
   function versione
   return varchar2;
   pragma restrict_references( versione, WNDS );
   function get_stato_territorio
   (
     p_sigla  in STATI_TERRITORI.sigla%type
   ) return STATI_TERRITORI.stato_territorio%type;
   function get_cittadinanze return AFC.t_ref_cursor;
   -- Getter per attributo desc_cittadinanza di riga identificata da chiave
   function get_desc_cittadinanza
   (
     p_cittadinanza  in varchar2
   ) return STATI_TERRITORI.desc_cittadinanza%type;
end stati_territori_pkg;
/

