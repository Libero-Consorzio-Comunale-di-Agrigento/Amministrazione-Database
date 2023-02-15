CREATE OR REPLACE PACKAGE BODY progetti_tpk is
/******************************************************************************
 NOME:        progetti_tpk
 DESCRIZIONE: Gestione tabella PROGETTI.
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
 NOTE:        Primo numero  : versione compatibilita del Package.
              Secondo numero: revisione del Package specification.
              Terzo numero  : revisione del Package body.
******************************************************************************/
begin
   return AFC.version ( s_revisione, s_revisione_body );
end versione; -- progetti_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_progetto  in PROGETTI.progetto%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.progetto := p_progetto;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_progetto => d_result.progetto
                                        )
           , 'canHandle on progetti_tpk.PK'
           );
   return  d_result;
end PK; -- progetti_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_progetto  in PROGETTI.progetto%type
) return number is /* SLAVE_COPY */
/******************************************************************************
 NOME:        can_handle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: 1 se la chiave e manipolabile, 0 altrimenti.
 NOTE:        cfr. canHandle per ritorno valori boolean.
******************************************************************************/
   d_result number;
begin
   d_result := 1;
   -- nelle chiavi primarie composte da piu attributi, ciascun attributo deve essere not null
   if  d_result = 1
   and (
          p_progetto is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on progetti_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- progetti_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_progetto  in PROGETTI.progetto%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_progetto => p_progetto
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- progetti_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_progetto  in PROGETTI.progetto%type
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
                                         p_progetto => p_progetto
                                        )
           , 'canHandle on progetti_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   PROGETTI
      where
      progetto = p_progetto
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on progetti_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- progetti_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_progetto  in PROGETTI.progetto%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_progetto => p_progetto
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- progetti_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_progetto  in PROGETTI.progetto%type
, p_descrizione  in PROGETTI.descrizione%type default null
, p_priorita  in PROGETTI.priorita%type default null
, p_note  in PROGETTI.note%type default null
, p_descrizione_al1  in PROGETTI.descrizione_al1%type default null
, p_descrizione_al2  in PROGETTI.descrizione_al2%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_descrizione is not null or /*default value*/ 'default' is not null
           , 'p_descrizione on progetti_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_priorita is not null or /*default value*/ 'default' is not null
           , 'p_priorita on progetti_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on progetti_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al1 on progetti_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al2 on progetti_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_progetto is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_progetto => p_progetto
                           )
           , 'not existsId on progetti_tpk.ins'
           );
   insert into PROGETTI
   (
     progetto
   , descrizione
   , priorita
   , note
   , descrizione_al1
   , descrizione_al2
   )
   values
   (
     p_progetto
   , p_descrizione
   , p_priorita
   , p_note
   , p_descrizione_al1
   , p_descrizione_al2
   );
end ins; -- progetti_tpk.ins
--------------------------------------------------------------------------------
function ins  /*+ SOA  */
(
  p_progetto  in PROGETTI.progetto%type
, p_descrizione  in PROGETTI.descrizione%type default null
, p_priorita  in PROGETTI.priorita%type default null
, p_note  in PROGETTI.note%type default null
, p_descrizione_al1  in PROGETTI.descrizione_al1%type default null
, p_descrizione_al2  in PROGETTI.descrizione_al2%type default null
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
   DbC.PRE ( not DbC.PreOn or p_descrizione is not null or /*default value*/ 'default' is not null
           , 'p_descrizione on progetti_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_priorita is not null or /*default value*/ 'default' is not null
           , 'p_priorita on progetti_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on progetti_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al1 on progetti_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al2 on progetti_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_progetto is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_progetto => p_progetto
                           )
           , 'not existsId on progetti_tpk.ins'
           );
   begin
      insert into PROGETTI
      (
        progetto
      , descrizione
      , priorita
      , note
      , descrizione_al1
      , descrizione_al2
      )
      values
      (
        p_progetto
      , p_descrizione
      , p_priorita
      , p_note
      , p_descrizione_al1
      , p_descrizione_al2
      );
      d_result := 0;
   exception
      when others then
         d_result := sqlcode;
   end;
   return d_result;
end ins; -- progetti_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_progetto  in PROGETTI.progetto%type
, p_OLD_progetto  in PROGETTI.progetto%type default null
, p_NEW_descrizione  in PROGETTI.descrizione%type default null
, p_OLD_descrizione  in PROGETTI.descrizione%type default null
, p_NEW_priorita  in PROGETTI.priorita%type default null
, p_OLD_priorita  in PROGETTI.priorita%type default null
, p_NEW_note  in PROGETTI.note%type default null
, p_OLD_note  in PROGETTI.note%type default null
, p_NEW_descrizione_al1  in PROGETTI.descrizione_al1%type default null
, p_OLD_descrizione_al1  in PROGETTI.descrizione_al1%type default null
, p_NEW_descrizione_al2  in PROGETTI.descrizione_al2%type default null
, p_OLD_descrizione_al2  in PROGETTI.descrizione_al2%type default null
) is
/******************************************************************************
 NOME:        upd
 DESCRIZIONE: Aggiornamento di una riga con chiave.
 PARAMETRI:   Chiavi e attributi della table
              p_check_OLD: 0 e null, ricerca senza controllo su attributi precedenti
                           1       , ricerca con controllo anche su attributi precedenti.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
              Se p_check_old e NULL, gli attributi vengono annullati solo se viene
              indicato anche il relativo attributo OLD.
              Se p_check_old = 1, viene controllato se il record corrispondente a
              tutti i campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_key t_PK;
   d_row_found number;
begin
   DbC.PRE (  not DbC.PreOn
           or not ( (
p_OLD_descrizione is not null or
p_OLD_priorita is not null or
p_OLD_note is not null or
p_OLD_descrizione_al1 is not null or
p_OLD_descrizione_al2 is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on progetti_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_progetto, p_NEW_progetto )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_progetto => d_key.progetto
                                       )
           , 'existsId on progetti_tpk.upd'
           );
   update PROGETTI
   set
       progetto = decode( p_check_OLD, 0,p_NEW_progetto, decode(p_NEW_progetto, p_OLD_progetto,progetto,p_NEW_progetto))
     , descrizione = decode( p_check_OLD, 0,p_NEW_descrizione, decode(p_NEW_descrizione, p_OLD_descrizione,descrizione,p_NEW_descrizione))
     , priorita = decode( p_check_OLD, 0,p_NEW_priorita, decode(p_NEW_priorita, p_OLD_priorita,priorita,p_NEW_priorita))
     , note = decode( p_check_OLD, 0,p_NEW_note, decode(p_NEW_note, p_OLD_note,note,p_NEW_note))
     , descrizione_al1 = decode( p_check_OLD, 0,p_NEW_descrizione_al1, decode(p_NEW_descrizione_al1, p_OLD_descrizione_al1,descrizione_al1,p_NEW_descrizione_al1))
     , descrizione_al2 = decode( p_check_OLD, 0,p_NEW_descrizione_al2, decode(p_NEW_descrizione_al2, p_OLD_descrizione_al2,descrizione_al2,p_NEW_descrizione_al2))
   where
     progetto = d_key.progetto
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( descrizione = p_OLD_descrizione or ( p_OLD_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( priorita = p_OLD_priorita or ( p_OLD_priorita is null and ( p_check_OLD is null or priorita is null ) ) )
           and ( note = p_OLD_note or ( p_OLD_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( descrizione_al1 = p_OLD_descrizione_al1 or ( p_OLD_descrizione_al1 is null and ( p_check_OLD is null or descrizione_al1 is null ) ) )
           and ( descrizione_al2 = p_OLD_descrizione_al2 or ( p_OLD_descrizione_al2 is null and ( p_check_OLD is null or descrizione_al2 is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on progetti_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- progetti_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_progetto  in PROGETTI.progetto%type
, p_column         in varchar2
, p_value          in varchar2 default null
, p_literal_value  in number   default 1
) is
/******************************************************************************
 NOME:        upd_column
 DESCRIZIONE: Aggiornamento del campo p_column col valore p_value.
 PARAMETRI:   p_column:        identificatore del campo da aggiornare.
              p_value:         valore da modificare.
              p_literal_value: indica se il valore e un stringa e non un numero
                               o una funzione.
******************************************************************************/
   d_statement AFC.t_statement;
   d_literal   varchar2(2);
begin
   DbC.PRE ( not DbC.PreOn or existsId (
                                        p_progetto => p_progetto
                                       )
           , 'existsId on progetti_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on progetti_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on progetti_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on progetti_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update PROGETTI '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( progetto ', p_progetto, ' )', 0, null ), ' and ( progetto is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- progetti_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_progetto  in PROGETTI.progetto%type
, p_descrizione  in PROGETTI.descrizione%type default null
, p_priorita  in PROGETTI.priorita%type default null
, p_note  in PROGETTI.note%type default null
, p_descrizione_al1  in PROGETTI.descrizione_al1%type default null
, p_descrizione_al2  in PROGETTI.descrizione_al2%type default null
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
p_descrizione is not null or
p_priorita is not null or
p_note is not null or
p_descrizione_al1 is not null or
p_descrizione_al2 is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on progetti_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_progetto => p_progetto
                                       )
           , 'existsId on progetti_tpk.del'
           );
   delete from PROGETTI
   where
     progetto = p_progetto
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( descrizione = p_descrizione or ( p_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( priorita = p_priorita or ( p_priorita is null and ( p_check_OLD is null or priorita is null ) ) )
           and ( note = p_note or ( p_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( descrizione_al1 = p_descrizione_al1 or ( p_descrizione_al1 is null and ( p_check_OLD is null or descrizione_al1 is null ) ) )
           and ( descrizione_al2 = p_descrizione_al2 or ( p_descrizione_al2 is null and ( p_check_OLD is null or descrizione_al2 is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on progetti_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_progetto => p_progetto
                                             )
            , 'existsId on progetti_tpk.del'
            );
end del; -- progetti_tpk.del
--------------------------------------------------------------------------------
function get_descrizione
(
  p_progetto  in PROGETTI.progetto%type
) return PROGETTI.descrizione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione
 DESCRIZIONE: Getter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     PROGETTI.descrizione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result PROGETTI.descrizione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_progetto => p_progetto
                                        )
           , 'existsId on progetti_tpk.get_descrizione'
           );
   select descrizione
   into   d_result
   from   PROGETTI
   where
   progetto = p_progetto
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on progetti_tpk.get_descrizione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione')
                    , ' AFC_DDL.IsNullable on progetti_tpk.get_descrizione'
                    );
   end if;
   return  d_result;
end get_descrizione; -- progetti_tpk.get_descrizione
--------------------------------------------------------------------------------
function get_priorita
(
  p_progetto  in PROGETTI.progetto%type
) return PROGETTI.priorita%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_priorita
 DESCRIZIONE: Getter per attributo priorita di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     PROGETTI.priorita%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result PROGETTI.priorita%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_progetto => p_progetto
                                        )
           , 'existsId on progetti_tpk.get_priorita'
           );
   select priorita
   into   d_result
   from   PROGETTI
   where
   progetto = p_progetto
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on progetti_tpk.get_priorita'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'priorita')
                    , ' AFC_DDL.IsNullable on progetti_tpk.get_priorita'
                    );
   end if;
   return  d_result;
end get_priorita; -- progetti_tpk.get_priorita
--------------------------------------------------------------------------------
function get_note
(
  p_progetto  in PROGETTI.progetto%type
) return PROGETTI.note%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_note
 DESCRIZIONE: Getter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     PROGETTI.note%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result PROGETTI.note%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_progetto => p_progetto
                                        )
           , 'existsId on progetti_tpk.get_note'
           );
   select note
   into   d_result
   from   PROGETTI
   where
   progetto = p_progetto
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on progetti_tpk.get_note'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'note')
                    , ' AFC_DDL.IsNullable on progetti_tpk.get_note'
                    );
   end if;
   return  d_result;
end get_note; -- progetti_tpk.get_note
--------------------------------------------------------------------------------
function get_descrizione_al1
(
  p_progetto  in PROGETTI.progetto%type
) return PROGETTI.descrizione_al1%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione_al1
 DESCRIZIONE: Getter per attributo descrizione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     PROGETTI.descrizione_al1%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result PROGETTI.descrizione_al1%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_progetto => p_progetto
                                        )
           , 'existsId on progetti_tpk.get_descrizione_al1'
           );
   select descrizione_al1
   into   d_result
   from   PROGETTI
   where
   progetto = p_progetto
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on progetti_tpk.get_descrizione_al1'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione_al1')
                    , ' AFC_DDL.IsNullable on progetti_tpk.get_descrizione_al1'
                    );
   end if;
   return  d_result;
end get_descrizione_al1; -- progetti_tpk.get_descrizione_al1
--------------------------------------------------------------------------------
function get_descrizione_al2
(
  p_progetto  in PROGETTI.progetto%type
) return PROGETTI.descrizione_al2%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione_al2
 DESCRIZIONE: Getter per attributo descrizione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     PROGETTI.descrizione_al2%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result PROGETTI.descrizione_al2%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_progetto => p_progetto
                                        )
           , 'existsId on progetti_tpk.get_descrizione_al2'
           );
   select descrizione_al2
   into   d_result
   from   PROGETTI
   where
   progetto = p_progetto
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on progetti_tpk.get_descrizione_al2'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione_al2')
                    , ' AFC_DDL.IsNullable on progetti_tpk.get_descrizione_al2'
                    );
   end if;
   return  d_result;
end get_descrizione_al2; -- progetti_tpk.get_descrizione_al2
--------------------------------------------------------------------------------
procedure set_progetto
(
  p_progetto  in PROGETTI.progetto%type
, p_value  in PROGETTI.progetto%type default null
) is
/******************************************************************************
 NOME:        set_progetto
 DESCRIZIONE: Setter per attributo progetto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_progetto => p_progetto
                                        )
           , 'existsId on progetti_tpk.set_progetto'
           );
   update PROGETTI
   set progetto = p_value
   where
   progetto = p_progetto
   ;
end set_progetto; -- progetti_tpk.set_progetto
--------------------------------------------------------------------------------
procedure set_descrizione
(
  p_progetto  in PROGETTI.progetto%type
, p_value  in PROGETTI.descrizione%type default null
) is
/******************************************************************************
 NOME:        set_descrizione
 DESCRIZIONE: Setter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_progetto => p_progetto
                                        )
           , 'existsId on progetti_tpk.set_descrizione'
           );
   update PROGETTI
   set descrizione = p_value
   where
   progetto = p_progetto
   ;
end set_descrizione; -- progetti_tpk.set_descrizione
--------------------------------------------------------------------------------
procedure set_priorita
(
  p_progetto  in PROGETTI.progetto%type
, p_value  in PROGETTI.priorita%type default null
) is
/******************************************************************************
 NOME:        set_priorita
 DESCRIZIONE: Setter per attributo priorita di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_progetto => p_progetto
                                        )
           , 'existsId on progetti_tpk.set_priorita'
           );
   update PROGETTI
   set priorita = p_value
   where
   progetto = p_progetto
   ;
end set_priorita; -- progetti_tpk.set_priorita
--------------------------------------------------------------------------------
procedure set_note
(
  p_progetto  in PROGETTI.progetto%type
, p_value  in PROGETTI.note%type default null
) is
/******************************************************************************
 NOME:        set_note
 DESCRIZIONE: Setter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_progetto => p_progetto
                                        )
           , 'existsId on progetti_tpk.set_note'
           );
   update PROGETTI
   set note = p_value
   where
   progetto = p_progetto
   ;
end set_note; -- progetti_tpk.set_note
--------------------------------------------------------------------------------
procedure set_descrizione_al1
(
  p_progetto  in PROGETTI.progetto%type
, p_value  in PROGETTI.descrizione_al1%type default null
) is
/******************************************************************************
 NOME:        set_descrizione_al1
 DESCRIZIONE: Setter per attributo descrizione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_progetto => p_progetto
                                        )
           , 'existsId on progetti_tpk.set_descrizione_al1'
           );
   update PROGETTI
   set descrizione_al1 = p_value
   where
   progetto = p_progetto
   ;
end set_descrizione_al1; -- progetti_tpk.set_descrizione_al1
--------------------------------------------------------------------------------
procedure set_descrizione_al2
(
  p_progetto  in PROGETTI.progetto%type
, p_value  in PROGETTI.descrizione_al2%type default null
) is
/******************************************************************************
 NOME:        set_descrizione_al2
 DESCRIZIONE: Setter per attributo descrizione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_progetto => p_progetto
                                        )
           , 'existsId on progetti_tpk.set_descrizione_al2'
           );
   update PROGETTI
   set descrizione_al2 = p_value
   where
   progetto = p_progetto
   ;
end set_descrizione_al2; -- progetti_tpk.set_descrizione_al2
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_progetto  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_priorita  in varchar2 default null
, p_note  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
) return AFC.t_statement is /* SLAVE_COPY */
/******************************************************************************
 NOME:        where_condition
 DESCRIZIONE: Ritorna la where_condition per lo statement di select di get_rows e count_rows.
 PARAMETRI:   p_other_condition
              p_QBE 0: se l'operatore da utilizzare nella where-condition e
                       quello di default ('=')
                    1: se l'operatore da utilizzare nella where-condition e
                       quello specificato per ogni attributo.
              Chiavi e attributi della table
 RITORNA:     AFC.t_statement.
 NOTE:        Se p_QBE = 1 , ogni parametro deve contenere, nella prima parte,
              l'operatore da utilizzare nella where-condition.
******************************************************************************/
   d_statement AFC.t_statement;
begin
   d_statement := ' where ( 1 = 1 '
               || AFC.get_field_condition( ' and ( progetto ', p_progetto, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione ', p_descrizione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( priorita ', p_priorita , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( note ', p_note , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione_al1 ', p_descrizione_al1 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione_al2 ', p_descrizione_al2 , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- progetti_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_progetto  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_priorita  in varchar2 default null
, p_note  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
) return AFC.t_ref_cursor is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_rows
 DESCRIZIONE: Ritorna il risultato di una query in base ai valori che passiamo.
 PARAMETRI:   p_QBE 0: se l'operatore da utilizzare nella where-condition e
                       quello di default ('=')
                    1: se l'operatore da utilizzare nella where-condition e
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
   d_statement := ' select PROGETTI.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from PROGETTI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_progetto => p_progetto
                                 , p_descrizione => p_descrizione
                                 , p_priorita => p_priorita
                                 , p_note => p_note
                                 , p_descrizione_al1 => p_descrizione_al1
                                 , p_descrizione_al2 => p_descrizione_al2
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- progetti_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_progetto  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_priorita  in varchar2 default null
, p_note  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
) return integer is /* SLAVE_COPY */
/******************************************************************************
 NOME:        count_rows
 DESCRIZIONE: Ritorna il numero di righe della tabella gli attributi delle quali
              rispettano i valori indicati.
 PARAMETRI:   p_other_condition
              p_QBE 0: se l'operatore da utilizzare nella where-condition e
                       quello di default ('=')
                    1: se l'operatore da utilizzare nella where-condition e
                       quello specificato per ogni attributo.
              Chiavi e attributi della table
 RITORNA:     Numero di righe che rispettano la selezione indicata.
******************************************************************************/
   d_result          integer;
   d_statement       AFC.t_statement;
begin
   d_statement := ' select count( * ) from PROGETTI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_progetto => p_progetto
                                 , p_descrizione => p_descrizione
                                 , p_priorita => p_priorita
                                 , p_note => p_note
                                 , p_descrizione_al1 => p_descrizione_al1
                                 , p_descrizione_al2 => p_descrizione_al2
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- progetti_tpk.count_rows
--------------------------------------------------------------------------------
end progetti_tpk;
/

