CREATE OR REPLACE package omonimie_gestite_pkg is /* MASTER_LINK */
/******************************************************************************
 NOME:        omonimie_gestite_pkg
 DESCRIZIONE: Gestione tabella OMONIMIE_GESTITE.
 ANNOTAZIONI: .
 REVISIONI:   Template Revision: 1.53.
 <CODE>
 Rev.  Data       Autore  Descrizione.
 00    19/05/2011  snegroni  Prima emissione.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'OMONIMIE_GESTITE';
    -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   -- Inserimento o aggiornamento di una riga
   procedure ins_upd
   ( p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type default null
   , p_primario  in OMONIMIE_GESTITE.primario%type default null
   , p_secondario  in OMONIMIE_GESTITE.secondario%type default null
   , p_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default null
   , p_unificato  in OMONIMIE_GESTITE.unificato%type default 0
   , p_copiato  in OMONIMIE_GESTITE.copiato%type default 0
   , p_ignorare  in OMONIMIE_GESTITE.ignorare%type default 0
   , p_note  in OMONIMIE_GESTITE.note%type default null
   , p_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default null
   , p_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default null
   , p_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default null
   );
   function get_id
   ( p_primario  in OMONIMIE_GESTITE.primario%type
   , p_secondario  in OMONIMIE_GESTITE.secondario%type
   ) return omonimie_gestite.id_omonimia%TYPE;
     procedure ins_upd_column
   ( p_primario     in OMONIMIE_GESTITE.primario%type
   , p_secondario  in OMONIMIE_GESTITE.secondario%type
   , p_column       in varchar2
   , p_value          in varchar2 default null
   , p_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default null
   ) ;
end omonimie_gestite_pkg;
/

