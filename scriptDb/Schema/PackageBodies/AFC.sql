CREATE OR REPLACE PACKAGE BODY AFC IS
/******************************************************************************
 Procedure e Funzioni di utilita' comune.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ----------------------------------------------------
 000   20/01/2003  MM      Prima emissione.
 001   26/04/2005  CZ      Aggiunte to_boolean e xor.
 002   14/06/2005  MM      Introduzione funczione get_substr.
 003   01/09/2005  FT      Aggiunta dei metodi protect_wildcard, version; aggiunta dei subtype t_object_name, t_message, t_statement, t_revision.
 004   27/09/2005  MF      Cambio nomenclatura s_revisione e s_revisione_body. Tolta dipendenza get_stringParm da Package Si4. Inserimento SQL_execute per istruzioni dinamiche.
 005   24/11/2005  FT      Aggiunta di mxor.
 006   04/01/2006  MM      Aggiunta is_number.
 007   12/01/2006  MM      Aggiunte is_numeric e to_number(p_value in varchar2), corretta is_number, corretta get_substr in modo che gestisca stringhe fino a 32000 caratteri.
 008   01/02/2006  FT      Aumento di parametri per mxor.
 009   22/02/2006  FT      Aggiunta dei metodi get_field_condition e decode_value.
 010   02/03/2006  FT      Aggiunta function SQL_execute.
 011   21/03/2006  MF      get_filed_condition:
                           - Introdotto prefix e suffix
                           - return type t_statement
                           decode_value:
                           - return type t_statement
 012   26/04/2006  MM      Modifica get_stringParm.
 013   19/05/2006  FT      Aggiunta metodo to_clob.
 014   25/06/2006  MF      Parametro in to_clob per ottenere empty in caso di null.
 015   28/06/2006  FT      Aggiunta function date_format; in get_field_condition, modificata gestione di p_field_value (gestione di operatori di default e scorporo dell'operatore da p_field_value) e aggiunto parametro p_date_format.
 016   30/08/2006  FT      Eliminazione della funzione to_clob.
 017   04/09/2006  FT      Corretto get_field_condition: aggiunta di apici a p_field_value in caso di 'like' implicito; controllo con 'lower' per caso di like.
 018   05/09/2006  FT      Modificata gestione 'like' implicito e raddoppio apici singoli in get_field_condition.
 019   19/10/2006  FT      Aggiunta funzione quote.
 020   30/10/2006  FT      Aggiunta funzione countOccurrenceOf.
 021   21/12/2006  FT      Aggiunta funzione init_cronologia.
 022   03/01/2007  FT      Modificata logica di gestione del flag in get_field_condition per permettere di passare NULL e comportarsi come fosse il valore di default.
 023   27/02/2007  FT      Spostata funzione init_cronologia nel package SI4.
 024   14/03/2007  FT      Aggiunta overloading di get_field_condition per p_field_value di tipo DATE.
 025   09/01/2008  FT      get_field_condition: aggiunta possibilita di passare l'operatore anche con p_flag = 0; modificata gestione DATE.
 026   18/03/2008  FT      get_field_condition: aggiunta possibilita di cercare per like su campi di tipo date.
 027   08/10/2008  FT      get_field_condition: aggiunta degli operatori IN, BETWEEN, EXISTS e NOT.
 028   06/04/2009  MF      Aggiunte funzioni di "default_null".
 029   18/05/2008  MF      Variabile d_item_name di tipo t_statement in default_null e is_default_null.
 030   15/09/2009  FT      Datatype del valore di ritorno della function version portato a varchar2(30).
 031   14/10/2009  FT      Aggiunto metodo string_extract; corretto get_field_condition: sbagliava nel caso in cui l'operatore passato ? BETWEEN.
 032   29/04/2010  FT      Aggiunto parametro p_delimitatori in string_extract per indicare se restituire o meno i delimitatori.
 033   11/11/2010  SN      Aggiunto parametro nella get_substr per decidere se tenere Inizio o Fine stringa.
 034   03/05/2011  FT      Aggiunti years_between, months_between, days_between hours_between, minutes_between e seconds_between.
 035   07/07/2011  FT      Allineati i commenti col nuovo standard di plsqldoc.
 036   02/12/2012  FT      Aumentato a 32000 il buffer di quote
******************************************************************************/
-- Package body revision value
s_revisione_body t_revision := '036';
-- Statement per gestione default_null
s_default_null              t_statement;
-- Object name per gestione default_null
s_default_null_object_name  t_object_name;
--------------------------------------------------------------------------------
/******************************************************************************
 Restituisce versione e revisione di distribuzione del package.
******************************************************************************/
function versione return t_revision is
begin
   return version( s_revisione, s_revisione_body );
end versione;
--------------------------------------------------------------------------------
/******************************************************************************
 Restituisce versione e revisione di distribuzione del package.
******************************************************************************/
function version
( p_revisione t_revision
, p_revisione_body t_revision
) return t_revision is
   d_result varchar2(30);
begin
   d_result := p_revisione||'.'||p_revisione_body;
   return d_result;
end version;
--------------------------------------------------------------------------------
/******************************************************************************
 Ottiene la stringa precedente alla stringa di separazione, modificando
 la stringa di partenza con la parte seguente, escludendo la stringa di
 separazione.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 000   7/01/2003   MM      Prima emissione.
******************************************************************************/
function get_substr
( p_stringa    IN OUT varchar2
, p_separatore IN     varchar2
) return VARCHAR2 is
   sStringa     VARCHAR2(32000);
   iPos         INTEGER;
begin
   iPos := instr(p_stringa,p_separatore);
   if iPos = 0 then
      sStringa  := p_stringa;
      p_stringa := '';
   else
      sStringa  := substr(p_stringa,1,iPos - 1);
      p_stringa := substr(p_stringa,iPos+length(p_separatore));
   end if;
   RETURN sStringa;
end get_substr;
--------------------------------------------------------------------------------
/******************************************************************************
 Ottiene la stringa precedente alla stringa di separazione.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 000   14/04/2005  MM      Prima emissione.
 001   11/11/2010  SN      Aggiunto parametro per decidere se tenere inizio o fine della stringa.
******************************************************************************/
function get_substr
( p_stringa    IN  varchar2
, p_separatore IN  varchar2
, p_occorrenza IN  varchar2
, p_parte      IN  varchar2 default inizio_stringa
) return VARCHAR2 is
   sStringa     VARCHAR2(32000);
   iPos         INTEGER;
   iOcc         INTEGER;
begin
   if p_occorrenza = prima_occorrenza then
      iOcc := 1;
   elsif p_occorrenza = ultima_occorrenza then
      iOcc := -1;
   else
      iOcc := 1;
   end if;
   iPos := instr(p_stringa,p_separatore,iOcc,1);
   if iPos = 0 then
      sStringa  := p_stringa;
   else
      if p_parte = inizio_stringa then
         sStringa  := substr(p_stringa,1,iPos - 1);
      else -- tengo fine stringa
         sStringa  :=substr(p_stringa,iPos +length(p_separatore));
      end if;
   end if;
   RETURN sStringa;
end get_substr;
--------------------------------------------------------------------------------
/******************************************************************************
 Estrapola un Parametro da una Stringa.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 000   17/01/2003  MM      Prima emissione.
 004   27/09/2005  MF      Tolta dipendenza get_stringParm da Package Si4.
 012   26/04/2006  MM      (BO14061) Ritorna un risultato errato se il valore del parametro richiesto e nullo.
******************************************************************************/
function get_stringParm
( p_stringa        IN varchar2
, p_identificativo IN varchar2
) return VARCHAR2 is
   d_stringa   varchar2(2000);
   d_parametro varchar2(2000);
   d_termine   varchar2(2000);
   d_pos       integer;
begin
   d_stringa := lTrim(rTrim(p_stringa));
   if substr(p_identificativo,1,1) in ('/','-') then
      d_parametro := p_identificativo;
      d_pos       := instr(d_stringa,d_parametro);
   else
      d_parametro := upper(p_identificativo)||'=';
      d_pos       := instr(Upper(d_stringa),d_parametro);
   end if;
   if d_pos = 0 then
      RETURN '';
   else
     d_pos := d_pos + length(d_parametro);
   end if;
   d_stringa := rTrim(substr(d_stringa, d_pos));
   -- Carattere finale determinato in funzione del carattere iniziale
   if substr(ltrim(d_stringa),1,1) = '''' or substr(ltrim(d_stringa),1,1) = '"' then
      d_stringa := ltrim(d_stringa);
      d_termine := substr(d_stringa,1,1);
      d_stringa := substr(d_stringa,2);
   else
      d_termine := ' ';
   end if;
   d_stringa := GET_SUBSTR(d_stringa,d_termine);
   RETURN d_stringa;
end get_stringParm;
--------------------------------------------------------------------------------
/******************************************************************************
 Preleva la parte di stringa compresa/esclusa dai delimitatori indicati come parametro
******************************************************************************/
function string_extract
( p_stringa in varchar2
, p_left in varchar2
, p_right in varchar2
, p_include in boolean default true
, p_delimitatori in boolean default false
) return afc.t_statement
is
   d_result afc.t_statement;
   d_length number;
   d_chr varchar2(1);
   d_preleva number(1);
   d_start varchar2(1);
   d_stop varchar2(1);
   d_n_char integer;
begin
   if p_include
   then
      d_start := p_left;
      d_stop := p_right;
      d_preleva := 0;
   else
      d_start := p_right;
      d_stop := p_left;
      d_preleva := 1;
   end if;
   d_length := nvl( length( p_stringa ), 0 );
   for i in 1 .. d_length
   loop
      d_chr := substr( p_stringa, i, 1 );
      if d_chr = d_start
      then
         d_preleva := 1;
         if p_delimitatori
         then
            d_result := d_result || d_chr;
         end if;
      elsif d_chr = d_stop
      then
         d_preleva := 0;
         if p_delimitatori
         then
            d_result := d_result || d_chr;
         end if;
      else
         if d_preleva = 1
         then
            d_result := d_result || d_chr;
         end if;
      end if;
   end loop;
   return d_result;
end string_extract;
--------------------------------------------------------------------------------
/******************************************************************************
 Numero di occorrenze di p_sottostringa in p_stringa
******************************************************************************/
function countOccurrenceOf
( p_stringa in varchar2
, p_sottostringa in varchar2)
return number
is
   d_result integer:= 0;
   d_pos integer := 0;
begin
   d_pos := instr( p_stringa, p_sottostringa );
   while d_pos > 0 loop
      d_result := d_result + 1;
      d_pos := instr( p_stringa, p_sottostringa, d_pos + 1 );
   end loop;
   return d_result;
end countOccurrenceOf;
--------------------------------------------------------------------------------
/******************************************************************************
 Protezione dei caratteri speciali ('_' e '%') nella stringa p_stringa
******************************************************************************/
function protect_wildcard
( p_stringa        in varchar2
) return varchar2 is
   d_result varchar2(2000);
begin
   d_result := replace( p_stringa, '_', '\_' );
   d_result := replace( d_result, '%', '\%' );
   return d_result;
end protect_wildcard;
--------------------------------------------------------------------------------
/******************************************************************************
 Gestione apici (aggiunta di quelli esterni e raddoppio di quelli interni) per la stringa p_stringa
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 019   19/10/2006  FT      Aggiunta funzione quote
 036   02/12/2012  FT      Aumentato a 32000 il buffer di quote
******************************************************************************/
function quote
( p_stringa   in varchar2
) return varchar2 is
   d_result varchar2(32000);
begin
   d_result := replace( p_stringa, '''', '''''' );
   d_result := '''' || d_result || '''';
   return d_result;
end quote;
--------------------------------------------------------------------------------
/******************************************************************************
 Conversione booleana di valori number (1,0)
******************************************************************************/
function to_boolean
( p_value in number
)
return boolean is
   d_result boolean;
begin
   DbC.PRE( p_value is not null );
   DbC.PRE( p_value = 1  or  p_value = 0 );
   if p_value = 1 then
      d_result := true;
   else
      d_result := false;
   end if;
   DbC.POST( d_result is not null );
   return  d_result;
end; -- AFC.to_boolean
--------------------------------------------------------------------------------
/******************************************************************************
 Conversione number di valori booleani
******************************************************************************/
function to_number
( p_value in boolean
)
return number
is
   d_result number;
begin
   DbC.PRE( p_value is not null );
   if p_value then
      d_result := 1;
   else
      d_result := 0;
   end if;
   DbC.POST( d_result is not null );
   DbC.POST( d_result = 1  or  d_result = 0 );
   return  d_result;
end; -- AFC.to_number
--------------------------------------------------------------------------------
/******************************************************************************
 Conversione number di stringhe
******************************************************************************/
function to_number
( p_value in varchar2
)
return number
is
   d_result number;
begin
   d_result := standard.to_number(p_value);
   return  d_result;
end to_number;
--------------------------------------------------------------------------------
/******************************************************************************
 Esegue lo statement passato.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 004   27/09/2005  MF      Cambio nomenclatura s_revisione e s_revisione_body. Tolta dipendenza get_stringParm da Package Si4. Inserimento SQL_execute per istruzioni dinamiche.
******************************************************************************/
procedure SQL_execute
( p_stringa t_statement
) is
   d_cursor         INTEGER;
   d_rows_processed INTEGER;
BEGIN
   d_cursor := dbms_sql.open_cursor;
   dbms_sql.parse( d_cursor, p_stringa, dbms_sql.native );
   d_rows_processed := dbms_sql.execute( d_cursor );
   dbms_sql.close_cursor( d_cursor );
EXCEPTION
   WHEN OTHERS THEN
      dbms_sql.close_cursor( d_cursor );
      raise;
END SQL_EXECUTE;
--------------------------------------------------------------------------------
/******************************************************************************
 Esegue lo statement passato e rotorna il valore di ritorno.
******************************************************************************/
function SQL_execute
( p_stringa t_statement
) return varchar2 is
   d_cursor         INTEGER;
   d_rows_processed INTEGER;
   d_result         varchar2(32000);
begin
   d_cursor := dbms_sql.open_cursor;
   dbms_sql.parse( d_cursor, p_stringa, dbms_sql.native );
   dbms_sql.define_column( d_cursor, 1, d_result, 32000 );
   d_rows_processed := dbms_sql.execute( d_cursor );
   if dbms_sql.fetch_rows( d_cursor ) > 0
   then
      dbms_sql.column_value( d_cursor, 1, d_result );
   end if;
   dbms_sql.close_cursor( d_cursor );
   return d_result;
exception
   when others then
      dbms_sql.close_cursor( d_cursor );
      raise;
end SQL_execute;
--------------------------------------------------------------------------------
/******************************************************************************
 Operatore booleano di or esclusivo
******************************************************************************/
function xor
( p_value_1 in boolean
, p_value_2 in boolean
) return boolean is
   d_result boolean;
begin
   DbC.PRE( p_value_1 is not null );
   DbC.PRE( p_value_2 is not null );
   d_result := p_value_1 != p_value_2;
   DbC.POST( d_result is not null );
   return  d_result;
end; -- AFC.xor
--------------------------------------------------------------------------------
/******************************************************************************
 Operatore booleano di or esclusivo
******************************************************************************/
function xor
( p_value_1 in boolean
, p_value_2 in boolean
, p_value_3 in boolean
) return boolean is
   d_result boolean;
begin
   DbC.PRE( p_value_3 is not null );
   -- p_value_1 and p_value_2 checked into the binary base function
   d_result  := xor( p_value_1,  p_value_2 ) != p_value_3;
   DbC.POST( d_result is not null );
   return  d_result;
end; -- AFC.xor
--------------------------------------------------------------------------------
/******************************************************************************
 Operatore booleano di or esclusivo
******************************************************************************/
function xor
( p_value_1 in boolean
, p_value_2 in boolean
, p_value_3 in boolean
, p_value_4 in boolean
) return boolean is
   d_result boolean;
begin
   d_result  := xor( p_value_1,  p_value_2 ) != xor( p_value_3,  p_value_4 );
   DbC.POST( d_result is not null );
   return  d_result;
end; -- AFC.xor
--------------------------------------------------------------------------------
/******************************************************************************
 Operatore booleano di or esclusivo: ritorna true se solo uno dei
******************************************************************************/
function mxor
( p_value_1 in boolean
, p_value_2 in boolean
, p_value_3 in boolean default false
, p_value_4 in boolean default false
, p_value_5 in boolean default false
, p_value_6 in boolean default false
, p_value_7 in boolean default false
, p_value_8 in boolean default false
) return boolean is
   d_result boolean;
begin
   d_result :=     p_value_1 and not p_value_2 and not p_value_3 and not p_value_4 and not p_value_5 and not p_value_6 and not p_value_7 and not p_value_8
            or not p_value_1 and     p_value_2 and not p_value_3 and not p_value_4 and not p_value_5 and not p_value_6 and not p_value_7 and not p_value_8
            or not p_value_1 and not p_value_2 and     p_value_3 and not p_value_4 and not p_value_5 and not p_value_6 and not p_value_7 and not p_value_8
            or not p_value_1 and not p_value_2 and not p_value_3 and     p_value_4 and not p_value_5 and not p_value_6 and not p_value_7 and not p_value_8
            or not p_value_1 and not p_value_2 and not p_value_3 and not p_value_4 and     p_value_5 and not p_value_6 and not p_value_7 and not p_value_8
            or not p_value_1 and not p_value_2 and not p_value_3 and not p_value_4 and not p_value_5 and     p_value_6 and not p_value_7 and not p_value_8
            or not p_value_1 and not p_value_2 and not p_value_3 and not p_value_4 and not p_value_5 and not p_value_6 and     p_value_7 and not p_value_8
            or not p_value_1 and not p_value_2 and not p_value_3 and not p_value_4 and not p_value_5 and not p_value_6 and not p_value_7 and     p_value_8
            ;
   DbC.POST( d_result is not null );
   return  d_result;
end; -- AFC.mxor
--------------------------------------------------------------------------------
/******************************************************************************
 Verifica che la stringa passata sia un numero.
******************************************************************************/
function is_number
( p_char in varchar2
) return number is
   d_result    number := 1;
   d_test      number;
begin
   d_test := to_number(p_char);
   RETURN d_result;
EXCEPTION
   WHEN OTHERS THEN
      if sqlcode = -6502 then
         RETURN 0;
      else
         raise;
      end if;
end is_number;
--------------------------------------------------------------------------------
/******************************************************************************
 Verifica che la stringa passata sia formata da soli numeri.
******************************************************************************/
function is_numeric
( p_char in varchar2
) return number is
   d_result    number := 0;
   d_translate varchar2(32000);
   d_compare   varchar2(32000);
   d_len       number := length(p_char);
   d_loop      number := 1;
begin
   if p_char is not null then
      d_translate := translate(p_char, '0123456789x', 'xxxxxxxxxxa');
      while d_loop <= d_len loop
         d_compare := d_compare||'x';
         d_loop := d_loop + 1;
      end loop;
      if d_compare = d_translate then
         d_result := 1;
      end if;
   end if;
   RETURN d_result;
end is_numeric;
--------------------------------------------------------------------------------
/******************************************************************************
 Ottiene stringa con condizione SQL.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 009   22/02/2006  FT      Aggiunta dei metodi get_field_condition e decode_value.
 011   21/03/2006  MF      - Introdotto prefix e suffix
                           - return type t_statement
 015   28/06/2006  FT      modificata gestione di p_field_value: gestione di operatori di default e scorporo dell'operatore dal valore; aggiunto parametro p_date_format.
 017   04/09/2006  FT      Aggiunta di apici a p_field_value in caso di 'like' implicito; controllo con 'lower' per caso di like.
 018   05/09/2006  FT      Modificata gestione 'like' implicito e raddoppio apici singoli.
 022   03/01/2007  FT      Modificata logica di gestione di p_flag per permettere di passare NULL e comportarsi come fosse il valore di default (0).
 025   09/01/2008  FT      Aggiunta possibilita di passare l'operatore anche con p_flag = 0; modificata gestione DATE: se il valore inizia per 'to_date(' non vengono applicate le regole di parsing ma viene concatenato direttamente al risultato finale.
 026   18/03/2008  FT      Aggiunta possibilita di cercare per like su campi di tipo date.
 027   08/10/2008  FT      Aggiunta degli operatori IN, BETWEEN, EXISTS e NOT.
 031   14/10/2009  FT      Correzione relativa al caso in cui l'operatore passato sia between.
******************************************************************************/
function get_field_condition
( p_prefix      in varchar2
, p_field_value in varchar2
, p_suffix      in varchar2
, p_flag        in number   default 0
, p_date_format in varchar2 default null
) return varchar2 is
   d_result t_statement;
   d_operator varchar2(7);
   d_char_operator number := 0;
   d_value t_statement;
   d_prefix t_statement;
   d_field_value AFC.t_statement;
   d_field_value_op AFC.t_statement;
begin
   d_field_value := ltrim( rtrim( p_field_value ) );
   d_field_value_op := lower( replace( p_field_value, ' ', '' ) );
   if d_field_value is not null
   then
      if p_flag = 0
      or p_flag is null
      then
         -- presenza del NOT iniziale
        if substr( d_field_value_op, 1, 3 ) = 'not'
         then
            d_prefix := substr( p_prefix, 1, instr( p_prefix, '(' ) - 1 )
                     || ' not '
                     || substr( p_prefix, instr( p_prefix, '(' ) )
                     ;
            d_field_value := ltrim( substr( d_field_value, 4 ) );
            d_field_value_op := ltrim( substr( d_field_value_op, 4 ) );
         else
            -- caso like generico
            d_prefix := p_prefix;
         end if;
         if substr( d_field_value_op, 1, 2 ) = '=='
         or substr( d_field_value_op, 1, 2 ) = '=>'
         or substr( d_field_value_op, 1, 2 ) = '>='
         or substr( d_field_value_op, 1, 2 ) = '<='
         or substr( d_field_value_op, 1, 2 ) = '=<'
         or substr( d_field_value_op, 1, 2 ) = '<>'
         or substr( d_field_value_op, 1, 2 ) = '!='
         or substr( d_field_value_op, 1, 2 ) = '!>'
         or substr( d_field_value_op, 1, 2 ) = '!<'
         then
            d_operator := substr( d_field_value_op, 1, 2 );
            d_char_operator := 2;
         elsif substr( d_field_value_op, 1, 1 ) = '>'
         or    substr( d_field_value_op, 1, 1 ) = '<'
         then
            d_operator := substr( d_field_value_op, 1, 1 );
            d_char_operator := 1;
         elsif substr( d_field_value_op, 1, 1 ) = '='
         then
            d_operator := '=';
            d_char_operator := 1;
         elsif substr( d_field_value_op, 1, 7 ) = 'between'
         then
            d_operator := substr( d_field_value_op, 1, 7 );
            d_char_operator := 7;
         elsif substr( d_field_value_op, 1, 7 ) = 'exists('
         then
            d_operator := substr( d_field_value_op, 1, 6 );
            d_char_operator := 6;
         elsif substr( d_field_value_op, 1, 3 ) = 'in('
         then
            d_operator := substr( d_field_value_op, 1, 2 );
            d_char_operator := 2;
         elsif(  (   instr( d_field_value_op, '_' ) != 0
                 and instr( lower( d_field_value_op ), 'to_date(' ) = 0
                 )
              or instr( d_field_value_op, '%' ) != 0
              )
         and not(   substr( d_field_value_op, 1, 1 ) = ''''
                and substr( d_field_value_op, -1 ) = ''''
                )
         then
            -- operatore 'like'
            if p_date_format is not null
            then
               -- caso like per date: trasformo in stringa il campo della tabella
               d_prefix := substr( p_prefix, 1, instr( p_prefix, '(' ) )
                        || ' to_char( '
                        || substr( p_prefix, instr( p_prefix, '(' ) + 1 )
                        || ', ''' || p_date_format || ''' ) '
                        ;
            else
               -- caso like generico
               d_prefix := p_prefix;
            end if;
            d_operator := 'like';
            d_field_value := '''' || replace( d_field_value, '''', '''''' ) || '''';
         else
            d_operator := '=';
         end if;
         if d_operator = '=='
         then
            d_operator := '=';
         elsif d_operator = '=>'
         then
            d_operator := '>=';
         elsif d_operator = '=<'
         then
            d_operator := '<=';
         end if;
         d_value := ltrim( rtrim( substr( d_field_value, d_char_operator + 1 ) ) );
         -- gestione apici
         if p_date_format is not null
         then
            if  substr( lower( d_value ), 1, 8 ) != 'to_date('
            then
               -- ? stata passata una data convertita in stringa (es: '15/01/1981 00:00:00')
               d_value := 'to_date( ''' || d_value || ''', ''' || p_date_format || ''' ) ';
            end if;
         else
            if  substr( d_value, 1, 1 ) = ''''
            and substr( d_value, -1 ) = ''''
            or  lower( d_operator ) in ( 'in', 'between', 'exists' )
            then
               d_value := d_value;
            else
               d_value := '''' || replace( d_value, '''', '''''' ) || '''';
            end if;
         end if;
         d_value := d_operator || ' ' || d_value;
      elsif p_flag = 1
      then
         d_prefix := p_prefix;
         d_value := p_field_value;
      end if;
      d_result := d_prefix || ' ' || d_value || ' ' || p_suffix;
   end if;
   return d_result;
end get_field_condition;
--------------------------------------------------------------------------------
/******************************************************************************
 Ottiene stringa con condizione SQL.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 024   14/03/2007  FT      Aggiunta overloading di get_field_condition per p_field_value di tipo DATE.
******************************************************************************/
function get_field_condition
( p_prefix      in varchar2
, p_field_value in date
, p_suffix      in varchar2
, p_flag        in number   default 0
, p_date_format in varchar2 default null
) return varchar2 is
   d_result t_statement;
begin
   d_result := get_field_condition( p_prefix => p_prefix
                                  , p_field_value => to_char( p_field_value, date_format )
                                  , p_suffix => p_suffix
                                  , p_flag => p_flag
                                  , p_date_format => p_date_format
                                  );
   return d_result;
end get_field_condition;
--------------------------------------------------------------------------------
/******************************************************************************
 Istruzione "decode" per PL/SQL.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 009   22/02/2006  FT      Aggiunta dei metodi get_field_condition e decode_value.
 011   21/03/2006  MF      Return type t_statement.
******************************************************************************/
function decode_value
( p_check_value in varchar2
, p_against_value in varchar2
, p_then_result in varchar2
, p_else_result in varchar2
) return varchar2 is
   d_result t_statement;
begin
   if p_check_value = p_against_value
   or (   p_check_value is null
      and p_against_value is null
      )
   then
      d_result := p_then_result;
   else
      d_result := p_else_result;
   end if;
   return d_result;
end decode_value;
--------------------------------------------------------------------------------
/******************************************************************************
 Ritorna il formato standard di conversione di una data.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 015   19/05/2006  --      Prima emissione.
******************************************************************************/
function date_format
return varchar2 is
   d_result  varchar2(21);
begin
   d_result := 'dd/mm/yyyy hh24:mi:ss';
   return d_result;
end date_format;
--------------------------------------------------------------------------------
/******************************************************************************
 Memorizza nome item per gestione "default_null".
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 028   6/04/2008   MF      Prima emissione.
 029   18/05/2008  MF      Variabile d_item_name di tipo t_statement.
******************************************************************************/
procedure default_null
(
 p_item_name  in VARCHAR2 default null
) is
d_item_name   t_statement := upper(p_item_name);
d_object_name t_object_name;
begin
   if ( instr( d_item_name, '.' ) > 0 ) then
      -- Se il nome contiene oggetto.item
      d_object_name := afc.get_substr( d_item_name, '.' );
      if (  s_default_null_object_name != d_object_name
         or s_default_null_object_name is NULL
         ) then
         -- A cambio object inizializza il "defaul_value"
         s_default_null_object_name := d_object_name;
         s_default_null := NULL;
      end if;
   end if;
   if ( d_item_name is NULL ) then
      -- Se item NULL inizializza il "defaul_value"
      s_default_null_object_name := NULL;
      s_default_null := NULL;
   else
      -- Memorizza item inizializzato a NULL
      s_default_null := s_default_null || '#' || d_item_name;
   end if;
end default_null;
--------------------------------------------------------------------------------
/******************************************************************************
 Ritorna valore NULL per inizializzazione default value e memorizza nome item per gestione "default_null".
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 028   6/04/2008   MF      Prima emissione.
******************************************************************************/
function default_null
(
 p_item_name  in VARCHAR2
) return VARCHAR2 is
begin
   default_null(p_item_name);
   return  NULL;
end default_null;
--------------------------------------------------------------------------------
/******************************************************************************
 Ritorna 1 se nome item ? stato valorizzato con gestione "default_null".
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 028   6/04/2008   MF      Prima emissione.
 029   18/05/2008  MF      Variabile d_item_name di tipo t_statement.
******************************************************************************/
function is_default_null
(
 p_item_name  in VARCHAR2
) return number is
d_item_name   t_statement := upper(p_item_name);
d_object_name t_object_name;
begin
   if ( instr( d_item_name, '.' ) > 0 ) then
      -- Se il nome contiene oggetto.item
      d_object_name := afc.get_substr( d_item_name, '.' );
   end if;
   if (   ( d_object_name is NULL or d_object_name = s_default_null_object_name )
      and ( instr( s_default_null, '#' || d_item_name ) > 0 )
      ) then
      -- Se object non indicato o uguale a ultimo trattato
      -- e item ? stato valorizzato gestione con "default_null".
      return 1;
   else
      return 0;
   end if;
end is_default_null;
--------------------------------------------------------------------------------
/******************************************************************************
 Ritorna il numero di anni che intercorrono tra la data p_dal e la data p_al.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 033   03/05/2011  FT      Aggiunto years_between.
******************************************************************************/
function years_between
( p_dal date
, p_al date
, p_left integer default 0
) return number
is
   d_diff number := ( p_al - p_dal ) / 365;
   d_left number := d_diff - trunc( d_diff );
   d_result number;
begin
   d_result := trunc( d_diff );
   if p_left = 0
   then
      d_result := d_result + d_left;
   end if;
   return d_result;
end years_between;
--------------------------------------------------------------------------------
/******************************************************************************
 Ritorna il numero di mesi che intercorrono tra la data p_dal e la data p_al.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 033   03/05/2011  FT      Aggiunto months_between.
******************************************************************************/
function months_between
( p_dal date
, p_al date
, p_trunc integer default 0
, p_left integer default 0
) return number
is
   d_diff number := standard.months_between( p_al, p_dal );
   d_left number := d_diff - trunc( d_diff );
   d_years number := years_between( p_dal => p_dal
                                  , p_al => p_al
                                  , p_left => 1
                                  );
   d_result number;
begin
   if p_trunc = 0
   then
      d_result := trunc( d_diff );
   else
      d_result := trunc( d_diff - ( d_years * 12 ) );
   end if;
   if p_left = 0
   then
      d_result := d_result + d_left;
   end if;
   return d_result;
end months_between;
--------------------------------------------------------------------------------
/******************************************************************************
 Ritorna il numero di giorni che intercorrono tra la data p_dal e la data p_al.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 033   03/05/2011  FT      Aggiunto years_between.
******************************************************************************/
function days_between
( p_dal date
, p_al date
, p_trunc integer default 0
, p_left integer default 0
) return number
is
   d_diff number := p_al - p_dal;
   d_left number := d_diff - trunc( d_diff );
   d_dal_gg number := to_number( to_char( p_dal, 'dd' ) );
   d_al_gg number := to_number( to_char( p_al, 'dd' ) );
   d_result number;
begin
   if p_trunc = 0
   then
      d_result := trunc( d_diff );
   else
      if d_al_gg >= d_dal_gg
      then
         d_result := d_al_gg - d_dal_gg;
      else
         d_result := to_number( to_char( last_day( p_al ), 'dd' ) ) - d_dal_gg + d_al_gg;
      end if;
      if to_number( to_char( p_dal, 'HH24' ) ) < to_number( to_char( p_al, 'HH24' ) )
      then
         d_result := d_result - 1;
      end if;
   end if;
   if p_left = 0
   then
      d_result := d_result + d_left;
   end if;
   return d_result;
end days_between;
--------------------------------------------------------------------------------
/******************************************************************************
 Ritorna il numero di ore che intercorrono tra la data p_dal e la data p_al.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 033   03/05/2011  FT      Aggiunto years_between.
******************************************************************************/
function hours_between
( p_dal date
, p_al date
, p_trunc integer default 0
, p_left integer default 0
) return number
is
   d_diff number := ( p_al - p_dal ) * 24;
   d_left number := d_diff - trunc( d_diff );
   d_days number := days_between( p_dal => p_dal
                                , p_al => p_al
                                , p_trunc => 0
                                , p_left => 1
                                );
   d_result number;
begin
   if p_trunc = 0
   then
      d_result := trunc( d_diff );
   else
      d_result := trunc( d_diff - ( d_days * 24 ) );
   end if;
   if p_left = 0
   then
      d_result := d_result + d_left;
   end if;
   return d_result;
end hours_between;
--------------------------------------------------------------------------------
/******************************************************************************
 Ritorna il numero di minuti che intercorrono tra la data p_dal e la data p_al.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 033   03/05/2011  FT      Aggiunto years_between.
******************************************************************************/
function minutes_between
( p_dal date
, p_al date
, p_trunc integer default 0
, p_left integer default 0
) return number
is
   d_diff number := ( p_al - p_dal ) * 24 * 60;
   d_left number := d_diff - trunc( d_diff );
   d_hours integer := hours_between( p_dal => p_dal
                                   , p_al => p_al
                                   , p_trunc => 0
                                   , p_left => 1
                                   );
   d_result number;
begin
   if p_trunc = 0
   then
      d_result := trunc( d_diff );
   else
      d_result := trunc( d_diff - ( d_hours * 60 ) );
   end if;
   if p_left = 0
   then
      d_result := d_result + d_left;
   end if;
   return d_result;
end minutes_between;
--------------------------------------------------------------------------------
/******************************************************************************
 Ritorna il numero di secondi che intercorrono tra la data p_dal e la data p_al.
 REVISIONI.
 Rev.  Data        Autore  Descrizione
 ----  ----------  ------  ------------------------------------------------------
 033   03/05/2011  FT      Aggiunto years_between.
******************************************************************************/
function seconds_between
( p_dal date
, p_al date
, p_trunc integer default 0
) return number
is
   d_diff number := ( p_al - p_dal ) * 24 * 60 * 60;
   d_minutes integer := minutes_between( p_dal => p_dal
                                       , p_al => p_al
                                       , p_trunc => 0
                                       , p_left => 1
                                       );
   d_result number;
begin
   if p_trunc = 0
   then
      d_result := trunc( d_diff );
   else
      d_result := trunc( d_diff - ( d_minutes * 60 ) );
   end if;
   return d_result;
end seconds_between;
--------------------------------------------------------------------------------
end afc;
/

