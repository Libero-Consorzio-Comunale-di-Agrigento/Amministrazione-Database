--liquibase formatted sql

--changeset mturra:201901301231_238 runOnChange:true stripComments:false endDelimiter:/


create or replace package GV_$SESSION_MASTER_pkg is
/******************************************************************************
 NOME:        sys.GV_$SESSION_pkg
 DESCRIZIONE: Gestione tabella sys.GV_$SESSION.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    06/04/2009  MMalferrari  Prima emissione.
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
   -- Getter per attributo terminal di riga identificata da chiave
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
end GV_$SESSION_MASTER_pkg;
/

