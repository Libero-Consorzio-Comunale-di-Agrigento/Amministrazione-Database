CREATE OR REPLACE PACKAGE BODY servizi_pkg is
/******************************************************************************
 NOME:        servizi_pkg
 DESCRIZIONE: Gestione tabella SERVIZI.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   18/05/2007  MMalferrari  Prima emissione.
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '000';
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
end versione; -- servizi_tpk.versione
--------------------------------------------------------------------------------
function get_mailtag
   (
     p_istanza  in SERVIZI.istanza%type
   , p_modulo   in SERVIZI.modulo%type
   ) return varchar2 is
/******************************************************************************
 NOME:        get_mailtag
 DESCRIZIONE: Tag di invio mail associato al servizio.
 PARAMETRI:   p_istanza: codice istanza.
              p_modulo: codice modulo.
 RITORNA:     Stringa corrispondente al tag associato.
 NOTE:        --.
******************************************************************************/
   d_result varchar2(4000);
begin
   select upper(user_oracle)
     into d_result
     from istanze
    where istanza = p_istanza
   ;
   d_result := LOWER(Registro_Pac.get_ad4_string('RICHIESTA_ABILITAZIONE/MAIL/CIM', 'Tag', p_modulo, d_result));
   return  d_result;
exception
   when no_data_found then
      return '';
end get_mailtag;
--------------------------------------------------------------------------------
end servizi_pkg;
/

