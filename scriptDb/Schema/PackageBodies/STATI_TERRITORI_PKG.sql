CREATE OR REPLACE PACKAGE BODY stati_territori_pkg is
/******************************************************************************
 NOME:        stati_territori_pkg
 DESCRIZIONE: Gestione tabella STATI_TERRITORI.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   14/01/2008  MMalferrari  Prima emissione.
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
end versione; -- stati_territori_tpk.versione
--------------------------------------------------------------------------------
function get_stato_territorio
(
  p_sigla  in STATI_TERRITORI.sigla%type
) return STATI_TERRITORI.stato_territorio%type is
/******************************************************************************
 NOME:        get_stato_territorio
 DESCRIZIONE: Getter per attributo get_stato_territorio data la sigla.
 PARAMETRI:   p_sigla.
 RITORNA:     STATI_TERRITORI.stato_territorio%type.
 NOTE:        --.
******************************************************************************/
   d_result STATI_TERRITORI.stato_territorio%type;
begin
   if afc.IS_NUMBER(p_sigla) = 1
   then
      d_result := p_sigla;
   else
      begin
         select stato_territorio
           into d_result
           from stati_territori
          where sigla = p_sigla
         ;
      exception
         when no_data_found then
            if p_sigla in ('I', 'IT', 'ITA')
            then
               d_result := 100;
            end if;
      end;
   end if;
   return  d_result;
exception
   when others
   then
      return null;
end get_stato_territorio; -- stati_territori_pkg.get_dstato_territorio
--------------------------------------------------------------------------------
function get_cittadinanze return AFC.t_ref_cursor is
/******************************************************************************
 NOME:        get_cittadinanze
 DESCRIZIONE: Ritorna il risultato di una query in base ai valori che passiamo.
 PARAMETRI:   --.
 RITORNA:     Un ref_cursor che punta al risultato della query.
 NOTE:        --.
******************************************************************************/
   d_statement       AFC.t_statement;
   d_ref_cursor      AFC.t_ref_cursor;
begin
   d_statement :=   'select stato_territorio, decode(desc_cittadinanza,'''',denominazione,desc_cittadinanza||decode(desc_cittadinanza,denominazione,'''','' - ''||denominazione)) desc_cittadinanza'
                  ||'  from STATI_TERRITORI ';
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_cittadinanze; -- stati_territori_tpk.get_cittadinanze
--------------------------------------------------------------------------------
function get_desc_cittadinanza
(
  p_cittadinanza  in varchar2
) return STATI_TERRITORI.desc_cittadinanza%type is
/******************************************************************************
 NOME:        get_desc_cittadinanza
 DESCRIZIONE: Getter per attributo desc_cittadinanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.desc_cittadinanza%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.desc_cittadinanza%type;
   d_stato_territorio stati_territori.STATO_TERRITORIO%type;
begin
   d_stato_territorio := get_stato_territorio(p_cittadinanza);
   if d_stato_territorio is not null
   then
      d_result := stati_territori_tpk.GET_DESC_CITTADINANZA(d_stato_territorio);
   else
      d_result := '';
   end if;
   return  d_result;
exception
   when others
   then
      return null;
end get_desc_cittadinanza; -- stati_territori_pkg.get_desc_cittadinanza
--------------------------------------------------------------------------------
end stati_territori_pkg;
/

