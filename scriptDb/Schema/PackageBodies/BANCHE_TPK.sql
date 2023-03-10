CREATE OR REPLACE package body banche_tpk is
/******************************************************************************
 NOME:        banche_tpk
 DESCRIZIONE: Gestione tabella BANCHE.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   30/08/2016  snegroni  Prima emissione.
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '000';
--------------------------------------------------------------------------------
function versione
return varchar2 is /* SLAVE_COPY */
/******************************************************************************
 NOME:        versione
 DESCRIZIONE: Versione e revisione di distribuzione del package.
 RITORNA:     varchar2 stringa contenente versione e revisione.
 NOTE:        Primo numero  : versione compatibilitÓ del Package.
              Secondo numero: revisione del Package specification.
              Terzo numero  : revisione del Package body.
******************************************************************************/
begin
   return AFC.version ( s_revisione, s_revisione_body );
end versione; -- banche_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_abi  in BANCHE.abi%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.abi := p_abi;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_abi => d_result.abi
                                        )
           , 'canHandle on banche_tpk.PK'
           );
   return  d_result;
end PK; -- banche_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_abi  in BANCHE.abi%type
) return number is /* SLAVE_COPY */
/******************************************************************************
 NOME:        can_handle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: 1 se la chiave Ŕ manipolabile, 0 altrimenti.
 NOTE:        cfr. canHandle per ritorno valori boolean.
******************************************************************************/
   d_result number;
begin
   d_result := 1;
   -- nelle chiavi primarie composte da pi¨ attributi, ciascun attributo deve essere not null
   if  d_result = 1
   and (
          p_abi is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on banche_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- banche_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_abi  in BANCHE.abi%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave Ŕ manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_abi => p_abi
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- banche_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_abi  in BANCHE.abi%type
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
                                         p_abi => p_abi
                                        )
           , 'canHandle on banche_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   BANCHE
      where
      abi = p_abi
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on banche_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- banche_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_abi  in BANCHE.abi%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_abi => p_abi
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- banche_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_abi  in BANCHE.abi%type
, p_cin_abi  in BANCHE.cin_abi%type
, p_denominazione  in BANCHE.denominazione%type
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_cin_abi is not null or /*default value*/ '' is not null
           , 'p_cin_abi on banche_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione is not null or /*default value*/ '' is not null
           , 'p_denominazione on banche_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_abi is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_abi => p_abi
                           )
           , 'not existsId on banche_tpk.ins'
           );
   insert into BANCHE
   (
     abi
   , cin_abi
   , denominazione
   )
   values
   (
     p_abi
   , p_cin_abi
   , p_denominazione
   );
end ins; -- banche_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_abi  in BANCHE.abi%type
, p_cin_abi  in BANCHE.cin_abi%type
, p_denominazione  in BANCHE.denominazione%type
) return number
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
 RITORNA:     In caso di PK formata da colonna numerica, ritorna il valore della PK
              (se positivo), in tutti gli altri casi ritorna 0; in caso di errore,
              ritorna il codice di errore
******************************************************************************/
is
   d_result number;
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_cin_abi is not null or /*default value*/ '' is not null
           , 'p_cin_abi on banche_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione is not null or /*default value*/ '' is not null
           , 'p_denominazione on banche_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_abi is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_abi => p_abi
                           )
           , 'not existsId on banche_tpk.ins'
           );
   begin
      insert into BANCHE
      (
        abi
      , cin_abi
      , denominazione
      )
      values
      (
        p_abi
      , p_cin_abi
      , p_denominazione
      );
      d_result := 0;
   exception
      when others then
         d_result := sqlcode;
   end;
   return d_result;
end ins; -- banche_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_abi  in BANCHE.abi%type
, p_OLD_abi  in BANCHE.abi%type default null
, p_NEW_cin_abi  in BANCHE.cin_abi%type default afc.default_null('BANCHE.cin_abi')
, p_OLD_cin_abi  in BANCHE.cin_abi%type default null
, p_NEW_denominazione  in BANCHE.denominazione%type default afc.default_null('BANCHE.denominazione')
, p_OLD_denominazione  in BANCHE.denominazione%type default null
) is
/******************************************************************************
 NOME:        upd
 DESCRIZIONE: Aggiornamento di una riga con chiave.
 PARAMETRI:   Chiavi e attributi della table
              p_check_OLD: 0    , ricerca senza controllo su attributi precedenti
                           1    , ricerca con controllo su tutti gli attributi precedenti.
                           null , ricerca con controllo sui soli attributi precedenti passati.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
              Se p_check_old = 1, viene controllato se il record corrispondente a
              tutti i campi passati come parametri esiste nella tabella.
              Se p_check_old Ŕ NULL, viene controllato se il record corrispondente
              ai soli campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_key t_PK;
   d_row_found number;
begin
   DbC.PRE (  not DbC.PreOn
           or not ( (
p_OLD_cin_abi is not null
 or p_OLD_denominazione is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on banche_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_abi, p_NEW_abi )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_abi => d_key.abi
                                       )
           , 'existsId on banche_tpk.upd'
           );
   update BANCHE
   set
       abi = nvl( p_NEW_abi, decode( afc.is_default_null( 'BANCHE.abi'), 1, abi, null) )
     , cin_abi = nvl( p_NEW_cin_abi, decode( afc.is_default_null( 'BANCHE.cin_abi'), 1, cin_abi, null) )
     , denominazione = nvl( p_NEW_denominazione, decode( afc.is_default_null( 'BANCHE.denominazione'), 1, denominazione, null) )
   where
     abi = d_key.abi
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( cin_abi = p_OLD_cin_abi or ( p_OLD_cin_abi is null and ( p_check_OLD is null or cin_abi is null ) ) )
           and ( denominazione = p_OLD_denominazione or ( p_OLD_denominazione is null and ( p_check_OLD is null or denominazione is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on banche_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- banche_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_abi  in BANCHE.abi%type
, p_column         in varchar2
, p_value          in varchar2 default null
, p_literal_value  in number   default 1
) is
/******************************************************************************
 NOME:        upd_column
 DESCRIZIONE: Aggiornamento del campo p_column col valore p_value.
 PARAMETRI:   p_column:        identificatore del campo da aggiornare.
              p_value:         valore da modificare.
              p_literal_value: indica se il valore Ŕ un stringa e non un numero
                               o una funzione.
******************************************************************************/
   d_statement AFC.t_statement;
   d_literal   varchar2(2);
begin
   DbC.PRE ( not DbC.PreOn or existsId (
                                        p_abi => p_abi
                                       )
           , 'existsId on banche_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on banche_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on banche_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on banche_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update BANCHE '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( abi ', p_abi, ' )', 0, null ), ' and ( abi is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- banche_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_abi  in BANCHE.abi%type
, p_cin_abi  in BANCHE.cin_abi%type default null
, p_denominazione  in BANCHE.denominazione%type default null
) is
/******************************************************************************
 NOME:        del
 DESCRIZIONE: Cancellazione della riga indicata.
 PARAMETRI:   Chiavi e attributi della table
              p_check_OLD: 0    , ricerca senza controllo su attributi precedenti
                           1    , ricerca con controllo su tutti gli attributi precedenti.
                           null , ricerca con controllo sui soli attributi precedenti passati.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
              Se p_check_old = 1, viene controllato se il record corrispondente a
              tutti i campi passati come parametri esiste nella tabella.
              Se p_check_old Ŕ NULL, viene controllato se il record corrispondente
              ai soli campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_row_found number;
begin
   DbC.PRE (  not DbC.PreOn
           or not ( (
p_cin_abi is not null
 or p_denominazione is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on banche_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_abi => p_abi
                                       )
           , 'existsId on banche_tpk.del'
           );
   delete from BANCHE
   where
     abi = p_abi
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( cin_abi = p_cin_abi or ( p_cin_abi is null and ( p_check_OLD is null or cin_abi is null ) ) )
           and ( denominazione = p_denominazione or ( p_denominazione is null and ( p_check_OLD is null or denominazione is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on banche_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_abi => p_abi
                                             )
            , 'existsId on banche_tpk.del'
            );
end del; -- banche_tpk.del
--------------------------------------------------------------------------------
function get_cin_abi
(
  p_abi  in BANCHE.abi%type
) return BANCHE.cin_abi%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_cin_abi
 DESCRIZIONE: Getter per attributo cin_abi di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     BANCHE.cin_abi%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result BANCHE.cin_abi%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
                                        )
           , 'existsId on banche_tpk.get_cin_abi'
           );
   select cin_abi
   into   d_result
   from   BANCHE
   where
   abi = p_abi
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on banche_tpk.get_cin_abi'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'cin_abi')
                    , ' AFC_DDL.IsNullable on banche_tpk.get_cin_abi'
                    );
   end if;
   return  d_result;
end get_cin_abi; -- banche_tpk.get_cin_abi
--------------------------------------------------------------------------------
function get_denominazione
(
  p_abi  in BANCHE.abi%type
) return BANCHE.denominazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione
 DESCRIZIONE: Getter per attributo denominazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     BANCHE.denominazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result BANCHE.denominazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
                                        )
           , 'existsId on banche_tpk.get_denominazione'
           );
   select denominazione
   into   d_result
   from   BANCHE
   where
   abi = p_abi
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on banche_tpk.get_denominazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione')
                    , ' AFC_DDL.IsNullable on banche_tpk.get_denominazione'
                    );
   end if;
   return  d_result;
end get_denominazione; -- banche_tpk.get_denominazione
--------------------------------------------------------------------------------
procedure set_abi
(
  p_abi  in BANCHE.abi%type
, p_value  in BANCHE.abi%type default null
) is
/******************************************************************************
 NOME:        set_abi
 DESCRIZIONE: Setter per attributo abi di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
                                        )
           , 'existsId on banche_tpk.set_abi'
           );
   update BANCHE
   set abi = p_value
   where
   abi = p_abi
   ;
end set_abi; -- banche_tpk.set_abi
--------------------------------------------------------------------------------
procedure set_cin_abi
(
  p_abi  in BANCHE.abi%type
, p_value  in BANCHE.cin_abi%type default null
) is
/******************************************************************************
 NOME:        set_cin_abi
 DESCRIZIONE: Setter per attributo cin_abi di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
                                        )
           , 'existsId on banche_tpk.set_cin_abi'
           );
   update BANCHE
   set cin_abi = p_value
   where
   abi = p_abi
   ;
end set_cin_abi; -- banche_tpk.set_cin_abi
--------------------------------------------------------------------------------
procedure set_denominazione
(
  p_abi  in BANCHE.abi%type
, p_value  in BANCHE.denominazione%type default null
) is
/******************************************************************************
 NOME:        set_denominazione
 DESCRIZIONE: Setter per attributo denominazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
                                        )
           , 'existsId on banche_tpk.set_denominazione'
           );
   update BANCHE
   set denominazione = p_value
   where
   abi = p_abi
   ;
end set_denominazione; -- banche_tpk.set_denominazione
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_abi  in varchar2 default null
, p_cin_abi  in varchar2 default null
, p_denominazione  in varchar2 default null
) return AFC.t_statement is /* SLAVE_COPY */
/******************************************************************************
 NOME:        where_condition
 DESCRIZIONE: Ritorna la where_condition per lo statement di select di get_rows e count_rows.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo Ŕ presente
                       un operatore, altrimenti viene usato quello di default ('=')
                    1: viene utilizzato l'operatore specificato all'inizio di ogni
                       attributo.
              p_other_condition: condizioni aggiuntive di base
              Chiavi e attributi della table
 RITORNA:     AFC.t_statement.
 NOTE:        Se p_QBE = 1 , ogni parametro deve contenere, nella prima parte,
              l'operatore da utilizzare nella where-condition.
******************************************************************************/
   d_statement AFC.t_statement;
begin
   d_statement := ' where ( 1 = 1 '
               || AFC.get_field_condition( ' and ( abi ', p_abi, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( cin_abi ', p_cin_abi , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione ', p_denominazione , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- banche_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_abi  in varchar2 default null
, p_cin_abi  in varchar2 default null
, p_denominazione  in varchar2 default null
) return AFC.t_ref_cursor is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_rows
 DESCRIZIONE: Ritorna il risultato di una query in base ai valori che passiamo.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo Ŕ presente
                       un operatore, altrimenti viene usato quello di default ('=')
                    1: viene utilizzato l'operatore specificato all'inizio di ogni
                       attributo.
              p_other_condition: condizioni aggiuntive di base
              p_order_by: condizioni di ordinamento
              p_extra_columns: colonne da aggiungere alla select
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
   d_statement := ' select BANCHE.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from BANCHE '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_abi => p_abi
                                 , p_cin_abi => p_cin_abi
                                 , p_denominazione => p_denominazione
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- banche_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_abi  in varchar2 default null
, p_cin_abi  in varchar2 default null
, p_denominazione  in varchar2 default null
) return integer is /* SLAVE_COPY */
/******************************************************************************
 NOME:        count_rows
 DESCRIZIONE: Ritorna il numero di righe della tabella gli attributi delle quali
              rispettano i valori indicati.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo Ŕ presente
                       un operatore, altrimenti viene usato quello di default ('=')
                    1: viene utilizzato l'operatore specificato all'inizio di ogni
                       attributo.
              p_other_condition: condizioni aggiuntive di base
              Chiavi e attributi della table
 RITORNA:     Numero di righe che rispettano la selezione indicata.
******************************************************************************/
   d_result          integer;
   d_statement       AFC.t_statement;
begin
   d_statement := ' select count( * ) from BANCHE '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_abi => p_abi
                                 , p_cin_abi => p_cin_abi
                                 , p_denominazione => p_denominazione
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- banche_tpk.count_rows
--------------------------------------------------------------------------------
end banche_tpk;
/

