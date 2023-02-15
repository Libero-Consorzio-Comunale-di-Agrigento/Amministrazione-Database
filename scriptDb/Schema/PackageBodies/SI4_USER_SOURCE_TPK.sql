CREATE OR REPLACE package body si4_user_source_tpk is
/******************************************************************************
 NOME:        si4_user_source_tpk
 DESCRIZIONE: Gestione tabella SI4_USER_SOURCE.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   11/05/2009  mmalferrari  Prima emissione.
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '000';
--------------------------------------------------------------------------------
function versione
return varchar2 is /* SLAVE_COPY */
/******************************************************************************
 NOME:        versione
 DESCRIZIONE: Versione e revisione di distribuzione del package.
 RITORNA:     varchar2 stringa contenente versione e revisione.
 NOTE:        Primo numero  : versione compatibilità del Package.
              Secondo numero: revisione del Package specification.
              Terzo numero  : revisione del Package body.
******************************************************************************/
begin
   return AFC.version ( s_revisione, s_revisione_body );
end versione; -- si4_user_source_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.name := p_name;
d_result.type := p_type;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_name => d_result.name,
p_type => d_result.type
                                        )
           , 'canHandle on si4_user_source_tpk.PK'
           );
   return  d_result;
end PK; -- si4_user_source_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
) return number is /* SLAVE_COPY */
/******************************************************************************
 NOME:        can_handle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: 1 se la chiave è manipolabile, 0 altrimenti.
 NOTE:        cfr. canHandle per ritorno valori boolean.
******************************************************************************/
   d_result number;
begin
   d_result := 1;
   -- nelle chiavi primarie composte da più attributi, ciascun attributo deve essere not null
   if  d_result = 1
   and (
          p_name is null
or p_type is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on si4_user_source_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- si4_user_source_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave è manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_name => p_name
,p_type => p_type
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- si4_user_source_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
) return number is /* SLAVE_COPY */
/******************************************************************************
 NOME:        exists_id
 DESCRIZIONE: Esistenza riga con chiave indicata.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
 NOTE:        cfr. existsId per ritorno valori boolean.
******************************************************************************/
   d_result number;
begin
   DbC.PRE ( not DbC.PreOn or canHandle (
                                         p_name => p_name
,p_type => p_type
                                        )
           , 'canHandle on si4_user_source_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   SI4_USER_SOURCE
      where
      name = p_name
and type = p_type
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on si4_user_source_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- si4_user_source_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_name => p_name
,p_type => p_type
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- si4_user_source_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
, p_text  in SI4_USER_SOURCE.text%type default null
, p_filename  in SI4_USER_SOURCE.filename%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_text is not null or /*default value*/ 'default' is not null
           , 'p_text on si4_user_source_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_filename is not null or /*default value*/ 'default' is not null
           , 'p_filename on si4_user_source_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_name is null and /*default value*/ '' is not null ) -- PK nullable on insert
or (   p_type is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_name => p_name
,p_type => p_type
                           )
           , 'not existsId on si4_user_source_tpk.ins'
           );
   insert into SI4_USER_SOURCE
   (
     name
,type
   , text
   , filename
   )
   values
   (
     p_name
,p_type
   , p_text
   , p_filename
   );
end ins; -- si4_user_source_tpk.ins
--------------------------------------------------------------------------------
function ins  /*+ SOA  */
(
  p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
, p_text  in SI4_USER_SOURCE.text%type default null
, p_filename  in SI4_USER_SOURCE.filename%type default null
) return integer
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
 RITORNA:     In caso di PK formata da colonna numerica, ritorna il valore della PK
              (se positivo), in tutti gli altri casi ritorna 0; in caso di errore,
              ritorna il codice di errore
******************************************************************************/
is
   d_result integer;
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_text is not null or /*default value*/ 'default' is not null
           , 'p_text on si4_user_source_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_filename is not null or /*default value*/ 'default' is not null
           , 'p_filename on si4_user_source_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_name is null and /*default value*/ '' is not null ) -- PK nullable on insert
or (   p_type is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_name => p_name
,p_type => p_type
                           )
           , 'not existsId on si4_user_source_tpk.ins'
           );
   begin
      insert into SI4_USER_SOURCE
      (
        name
,type
      , text
      , filename
      )
      values
      (
        p_name
,p_type
      , p_text
      , p_filename
      );
      d_result := 0;
   exception
      when others then
         d_result := sqlcode;
   end;
   return d_result;
end ins; -- si4_user_source_tpk.ins
--------------------------------------------------------------------------------
procedure ins
(
p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
, p_filename  in SI4_USER_SOURCE.filename%type default null
, p_text  in varchar2 default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
 NOTE:        Overloading con attributi VARCHAR2 per campi CLOB
******************************************************************************/
begin
   ins(
p_name => p_name
,p_type => p_type
, p_filename => p_filename
, p_text => AFC_LOB.to_clob( p_text )
      );
end ins; -- si4_user_source_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_name  in SI4_USER_SOURCE.name%type
, p_OLD_name  in SI4_USER_SOURCE.name%type default null
, p_NEW_type  in SI4_USER_SOURCE.type%type
, p_OLD_type  in SI4_USER_SOURCE.type%type default null
, p_NEW_text  in SI4_USER_SOURCE.text%type default null
, p_OLD_text  in SI4_USER_SOURCE.text%type default null
, p_NEW_filename  in SI4_USER_SOURCE.filename%type default null
, p_OLD_filename  in SI4_USER_SOURCE.filename%type default null
) is
/******************************************************************************
 NOME:        upd
 DESCRIZIONE: Aggiornamento di una riga con chiave.
 PARAMETRI:   Chiavi e attributi della table
              p_check_OLD: 0 e null, ricerca senza controllo su attributi precedenti
                           1       , ricerca con controllo anche su attributi precedenti.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
              Se p_check_old è NULL, gli attributi vengono annullati solo se viene
              indicato anche il relativo attributo OLD.
              Se p_check_old = 1, viene controllato se il record corrispondente a
              tutti i campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_key t_PK;
   d_row_found number;
begin
   DbC.PRE (  not DbC.PreOn
           or not ( (
p_OLD_text is not null or
p_OLD_filename is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on si4_user_source_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_name, p_NEW_name )
,nvl( p_OLD_type, p_NEW_type )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_name => d_key.name
,p_type => d_key.type
                                       )
           , 'existsId on si4_user_source_tpk.upd'
           );
   update SI4_USER_SOURCE
   set
       name = decode( p_check_OLD, 0,p_NEW_name, decode(p_NEW_name, p_OLD_name,name,p_NEW_name))
,type = decode( p_check_OLD, 0,p_NEW_type, decode(p_NEW_type, p_OLD_type,type,p_NEW_type))
     , filename = decode( p_check_OLD, 0,p_NEW_filename, decode(p_NEW_filename, p_OLD_filename,filename,p_NEW_filename))
     , text = afc_lob.decode_value( afc_lob.to_clob(p_check_OLD), afc_lob.to_clob(0),p_NEW_text, afc_lob.decode_value(p_NEW_text, p_OLD_text,text,p_NEW_text))
   where
     name = d_key.name
and type = d_key.type
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( filename = p_OLD_filename or ( p_OLD_filename is null and ( p_check_OLD is null or filename is null ) ) )
           and ( dbms_lob.compare( text, p_OLD_text ) = 0 or ( p_OLD_text is null and ( p_check_OLD is null or text is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on si4_user_source_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- si4_user_source_tpk.upd
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer
, p_NEW_name  in SI4_USER_SOURCE.name%type
, p_OLD_name  in SI4_USER_SOURCE.name%type
, p_NEW_type  in SI4_USER_SOURCE.type%type
, p_OLD_type  in SI4_USER_SOURCE.type%type
, p_NEW_filename  in SI4_USER_SOURCE.filename%type
, p_OLD_filename  in SI4_USER_SOURCE.filename%type
, p_NEW_text  in varchar2
, p_OLD_text  in varchar2
) is
/******************************************************************************
 NOME:        upd
 DESCRIZIONE: Aggiornamento di una riga con chiave.
 PARAMETRI:   Chiavi e attributi della table
              p_check_OLD: 0 e null, ricerca senza controllo su attributi precedenti
                           1       , ricerca con controllo anche su attributi precedenti.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
              Se p_check_old è NULL, gli attributi vengono annullati solo se viene
              indicato anche il relativo attributo OLD.
              Se p_check_old = 1, viene controllato se il record corrispondente a
              tutti i campi passati come parametri esiste nella tabella.
 NOTE:        Overloading con attributi VARCHAR2 per campi CLOB
******************************************************************************/
begin
   upd(
         p_check_OLD => p_check_OLD
, p_NEW_name => p_NEW_name
, p_OLD_name => p_OLD_name
, p_NEW_type => p_NEW_type
, p_OLD_type => p_OLD_type
, p_NEW_filename => p_NEW_filename
, p_OLD_filename => p_OLD_filename
, p_NEW_text => afc_lob.to_clob( p_NEW_text )
, p_OLD_text => afc_lob.to_clob( p_OLD_text )
      );
end upd; -- si4_user_source_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_name  in SI4_USER_SOURCE.name%type,
p_type  in SI4_USER_SOURCE.type%type
, p_column         in varchar2
, p_value          in varchar2 default null
, p_literal_value  in number   default 1
) is
/******************************************************************************
 NOME:        upd_column
 DESCRIZIONE: Aggiornamento del campo p_column col valore p_value.
 PARAMETRI:   p_column:        identificatore del campo da aggiornare.
              p_value:         valore da modificare.
              p_literal_value: indica se il valore è un stringa e non un numero
                               o una funzione.
******************************************************************************/
   d_statement AFC.t_statement;
   d_literal   varchar2(2);
begin
   DbC.PRE ( not DbC.PreOn or existsId (
                                        p_name => p_name,
p_type => p_type
                                       )
           , 'existsId on si4_user_source_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on si4_user_source_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on si4_user_source_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on si4_user_source_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update SI4_USER_SOURCE '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( name ', p_name, ' )', 0, null ), ' and ( name is null ) ' )
 || nvl( AFC.get_field_condition( ' and ( type ', p_type, ' )', 0, null ), ' and ( type is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- si4_user_source_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_name  in SI4_USER_SOURCE.name%type
, p_type  in SI4_USER_SOURCE.type%type
, p_text  in SI4_USER_SOURCE.text%type default null
, p_filename  in SI4_USER_SOURCE.filename%type default null
) is
/******************************************************************************
 NOME:        del
 DESCRIZIONE: Cancellazione della riga indicata.
 PARAMETRI:   Chiavi e attributi della table.
              p_check_OLD: 0, ricerca senza controllo su attributi precedenti
                           1, ricerca con controllo anche su attributi precedenti.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
              Se p_check_old = 1, viene controllato se il record corrispondente a
              tutti i campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_row_found number;
begin
   DbC.PRE (  not DbC.PreOn
           or not ( (
p_text is not null or
p_filename is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on si4_user_source_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_name => p_name,
p_type => p_type
                                       )
           , 'existsId on si4_user_source_tpk.del'
           );
   delete from SI4_USER_SOURCE
   where
     name = p_name and
type = p_type
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( filename = p_filename or ( p_filename is null and ( p_check_OLD is null or filename is null ) ) )
           and ( dbms_lob.compare( text, p_text ) = 0 or ( p_text is null and ( p_check_OLD is null or text is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on si4_user_source_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_name => p_name,
p_type => p_type
                                             )
            , 'existsId on si4_user_source_tpk.del'
            );
end del; -- si4_user_source_tpk.del
--------------------------------------------------------------------------------
procedure del
(
  p_check_OLD  in integer
, p_name  in SI4_USER_SOURCE.name%type
, p_type  in SI4_USER_SOURCE.type%type
, p_filename  in SI4_USER_SOURCE.filename%type
, p_text  in varchar2
) is
/******************************************************************************
 NOME:        del
 DESCRIZIONE: Cancellazione della riga indicata.
 PARAMETRI:   Chiavi e attributi della table.
              p_check_OLD: 0, ricerca senza controllo su attributi precedenti
                           1, ricerca con controllo anche su attributi precedenti.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
              Se p_check_old = 1, viene controllato se il record corrispondente a
              tutti i campi passati come parametri esiste nella tabella.
 NOTE:        Overloading con attributi VARCHAR2 per campi CLOB
******************************************************************************/
begin
   del(
         p_check_OLD => p_check_OLD
, p_name => p_name
, p_type => p_type
, p_filename => p_filename
, p_text => afc_lob.to_clob( p_text )
      );
end del; -- si4_user_source_tpk.del
--------------------------------------------------------------------------------
function get_text
(
  p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
) return SI4_USER_SOURCE.text%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_text
 DESCRIZIONE: Getter per attributo text di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SI4_USER_SOURCE.text%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SI4_USER_SOURCE.text%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_name => p_name
, p_type => p_type
                                        )
           , 'existsId on si4_user_source_tpk.get_text'
           );
   select text
   into   d_result
   from   SI4_USER_SOURCE
   where
   name = p_name and
type = p_type
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on si4_user_source_tpk.get_text'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'text')
                    , ' AFC_DDL.IsNullable on si4_user_source_tpk.get_text'
                    );
   end if;
   return  d_result;
end get_text; -- si4_user_source_tpk.get_text
--------------------------------------------------------------------------------
function get_filename
(
  p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
) return SI4_USER_SOURCE.filename%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_filename
 DESCRIZIONE: Getter per attributo filename di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SI4_USER_SOURCE.filename%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SI4_USER_SOURCE.filename%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_name => p_name
, p_type => p_type
                                        )
           , 'existsId on si4_user_source_tpk.get_filename'
           );
   select filename
   into   d_result
   from   SI4_USER_SOURCE
   where
   name = p_name and
type = p_type
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on si4_user_source_tpk.get_filename'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'filename')
                    , ' AFC_DDL.IsNullable on si4_user_source_tpk.get_filename'
                    );
   end if;
   return  d_result;
end get_filename; -- si4_user_source_tpk.get_filename
--------------------------------------------------------------------------------
procedure set_name
(
  p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
, p_value  in SI4_USER_SOURCE.name%type default null
) is
/******************************************************************************
 NOME:        set_name
 DESCRIZIONE: Setter per attributo name di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_name => p_name
, p_type => p_type
                                        )
           , 'existsId on si4_user_source_tpk.set_name'
           );
   update SI4_USER_SOURCE
   set name = p_value
   where
   name = p_name and
type = p_type
   ;
end set_name; -- si4_user_source_tpk.set_name
--------------------------------------------------------------------------------
procedure set_type
(
  p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
, p_value  in SI4_USER_SOURCE.type%type default null
) is
/******************************************************************************
 NOME:        set_type
 DESCRIZIONE: Setter per attributo type di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_name => p_name
, p_type => p_type
                                        )
           , 'existsId on si4_user_source_tpk.set_type'
           );
   update SI4_USER_SOURCE
   set type = p_value
   where
   name = p_name and
type = p_type
   ;
end set_type; -- si4_user_source_tpk.set_type
--------------------------------------------------------------------------------
procedure set_text
(
  p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
, p_value  in SI4_USER_SOURCE.text%type default null
) is
/******************************************************************************
 NOME:        set_text
 DESCRIZIONE: Setter per attributo text di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_name => p_name
, p_type => p_type
                                        )
           , 'existsId on si4_user_source_tpk.set_text'
           );
   update SI4_USER_SOURCE
   set text = p_value
   where
   name = p_name and
type = p_type
   ;
end set_text; -- si4_user_source_tpk.set_text
--------------------------------------------------------------------------------
procedure set_filename
(
  p_name  in SI4_USER_SOURCE.name%type
,p_type  in SI4_USER_SOURCE.type%type
, p_value  in SI4_USER_SOURCE.filename%type default null
) is
/******************************************************************************
 NOME:        set_filename
 DESCRIZIONE: Setter per attributo filename di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_name => p_name
, p_type => p_type
                                        )
           , 'existsId on si4_user_source_tpk.set_filename'
           );
   update SI4_USER_SOURCE
   set filename = p_value
   where
   name = p_name and
type = p_type
   ;
end set_filename; -- si4_user_source_tpk.set_filename
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_name  in varchar2 default null
, p_type  in varchar2 default null
, p_filename  in varchar2 default null
) return AFC.t_statement is /* SLAVE_COPY */
/******************************************************************************
 NOME:        where_condition
 DESCRIZIONE: Ritorna la where_condition per lo statement di select di get_rows e count_rows.
 PARAMETRI:   p_other_condition
              p_QBE 0: se l'operatore da utilizzare nella where-condition è
                       quello di default ('=')
                    1: se l'operatore da utilizzare nella where-condition è
                       quello specificato per ogni attributo.
              Chiavi e attributi della table
 RITORNA:     AFC.t_statement.
 NOTE:        Se p_QBE = 1 , ogni parametro deve contenere, nella prima parte,
              l'operatore da utilizzare nella where-condition.
******************************************************************************/
   d_statement AFC.t_statement;
begin
   d_statement := ' where ( 1 = 1 '
               || AFC.get_field_condition( ' and ( name ', p_name, ' )', p_QBE, null )
|| AFC.get_field_condition( ' and ( type ', p_type, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( filename ', p_filename , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- si4_user_source_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_name  in varchar2 default null
, p_type  in varchar2 default null
, p_filename  in varchar2 default null
) return AFC.t_ref_cursor is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_rows
 DESCRIZIONE: Ritorna il risultato di una query in base ai valori che passiamo.
 PARAMETRI:   p_QBE 0: se l'operatore da utilizzare nella where-condition è
                       quello di default ('=')
                    1: se l'operatore da utilizzare nella where-condition è
                       quello specificato per ogni attributo.
              p_other_condition: condizioni aggiuntive di base
              p_order_by: condizioni di ordinamento
              p_extra_columns: colonne aggiungere alla select
              p_extra_condition: condizioni aggiuntive
              Chiavi e attributi della table
 RITORNA:     Un ref_cursor che punta al risultato della query.
 NOTE:        Se p_QBE = 1 , ogni parametro deve contenere, nella prima parte,
              l'operatore da utilizzare nella where-condition.
              In p_extra_columns e p_order_by non devono essere passati anche la
              virgola iniziale (per p_extra_columns) e la stringa 'order by' (per
              p_order_by)
******************************************************************************/
   d_statement       AFC.t_statement;
   d_ref_cursor      AFC.t_ref_cursor;
begin
   d_statement := ' select SI4_USER_SOURCE.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from SI4_USER_SOURCE '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_name => p_name
, p_type => p_type
                                 , p_filename => p_filename
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- si4_user_source_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_name  in varchar2 default null
, p_type  in varchar2 default null
, p_filename  in varchar2 default null
) return integer is /* SLAVE_COPY */
/******************************************************************************
 NOME:        count_rows
 DESCRIZIONE: Ritorna il numero di righe della tabella gli attributi delle quali
              rispettano i valori indicati.
 PARAMETRI:   p_other_condition
              p_QBE 0: se l'operatore da utilizzare nella where-condition è
                       quello di default ('=')
                    1: se l'operatore da utilizzare nella where-condition è
                       quello specificato per ogni attributo.
              Chiavi e attributi della table
 RITORNA:     Numero di righe che rispettano la selezione indicata.
******************************************************************************/
   d_result          integer;
   d_statement       AFC.t_statement;
begin
   d_statement := ' select count( * ) from SI4_USER_SOURCE '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_name => p_name
, p_type => p_type
                                 , p_filename => p_filename
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- si4_user_source_tpk.count_rows
--------------------------------------------------------------------------------
end si4_user_source_tpk;
/

