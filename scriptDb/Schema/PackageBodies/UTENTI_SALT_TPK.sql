CREATE OR REPLACE package body utenti_salt_tpk is
/******************************************************************************
 NOME:        utenti_salt_tpk
 DESCRIZIONE: Gestione tabella UTENTI_SALT.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore      Descrizione.
 000   02/03/2020  SNegroni  Generazione automatica.
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '000 - 02/03/2020';
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
end versione; -- utenti_salt_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_utente  in UTENTI_SALT.utente%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.utente := p_utente;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_utente => d_result.utente
                                        )
           , 'canHandle on utenti_salt_tpk.PK'
           );
   return  d_result;
end PK; -- utenti_salt_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_utente  in UTENTI_SALT.utente%type
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
          p_utente is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on utenti_salt_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- utenti_salt_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_utente  in UTENTI_SALT.utente%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_utente => p_utente
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- utenti_salt_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_utente  in UTENTI_SALT.utente%type
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
                                         p_utente => p_utente
                                        )
           , 'canHandle on utenti_salt_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   UTENTI_SALT
      where
      utente = p_utente
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on utenti_salt_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- utenti_salt_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_utente  in UTENTI_SALT.utente%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_utente => p_utente
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- utenti_salt_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_utente  in UTENTI_SALT.utente%type
, p_salt  in UTENTI_SALT.salt%type default null
, p_algoritmo  in UTENTI_SALT.algoritmo%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert

   DbC.PRE ( not DbC.PreOn or p_salt is not null or /*default value*/ 'default' is not null
           , 'p_salt on utenti_salt_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_algoritmo is not null or /*default value*/ 'default' is not null
           , 'p_algoritmo on utenti_salt_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_utente is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_utente => p_utente
                           )
           , 'not existsId on utenti_salt_tpk.ins'
           );
   insert into UTENTI_SALT
   (
     utente
   , salt
   , algoritmo
   )
   values
   (
     p_utente
, p_salt
, p_algoritmo
   );
end ins; -- utenti_salt_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_utente  in UTENTI_SALT.utente%type
, p_salt  in UTENTI_SALT.salt%type default null
, p_algoritmo  in UTENTI_SALT.algoritmo%type default null
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

   DbC.PRE ( not DbC.PreOn or p_salt is not null or /*default value*/ 'default' is not null
           , 'p_salt on utenti_salt_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_algoritmo is not null or /*default value*/ 'default' is not null
           , 'p_algoritmo on utenti_salt_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_utente is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_utente => p_utente
                           )
           , 'not existsId on utenti_salt_tpk.ins'
           );
   insert into UTENTI_SALT
   (
     utente
   , salt
   , algoritmo
   )
   values
   (
     p_utente
, p_salt
, p_algoritmo
   );
   d_result := 0;
   return d_result;
end ins; -- utenti_salt_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_utente  in UTENTI_SALT.utente%type
, p_OLD_utente  in UTENTI_SALT.utente%type default null
, p_NEW_salt  in UTENTI_SALT.salt%type default afc.default_null('UTENTI_SALT.salt')
, p_OLD_salt  in UTENTI_SALT.salt%type default null
, p_NEW_algoritmo  in UTENTI_SALT.algoritmo%type default afc.default_null('UTENTI_SALT.algoritmo')
, p_OLD_algoritmo  in UTENTI_SALT.algoritmo%type default null
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
              Se p_check_old e NULL, gli attributi vengono annullati solo se viene
              indicato anche il relativo attributo OLD.
              Se p_check_old = 1, viene controllato se il record corrispondente a
              tutti i campi passati come parametri esiste nella tabella.
              Se p_check_old e NULL, viene controllato se il record corrispondente
              ai soli campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_key t_PK;
   d_row_found number;
begin
   DbC.PRE (  not DbC.PreOn
           or not ( (
p_OLD_salt is not null
 or p_OLD_algoritmo is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on utenti_salt_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_utente, p_NEW_utente )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_utente => d_key.utente
                                       )
           , 'existsId on utenti_salt_tpk.upd'
           );
   update UTENTI_SALT
   set
       utente = NVL( p_NEW_utente, DECODE( AFC.IS_DEFAULT_NULL( 'UTENTI_SALT.utente' ), 1, utente,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_utente, null, utente, null ) ) ) )
     , salt = NVL( p_NEW_salt, DECODE( AFC.IS_DEFAULT_NULL( 'UTENTI_SALT.salt' ), 1, salt,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_salt, null, salt, null ) ) ) )
     , algoritmo = NVL( p_NEW_algoritmo, DECODE( AFC.IS_DEFAULT_NULL( 'UTENTI_SALT.algoritmo' ), 1, algoritmo,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_algoritmo, null, algoritmo, null ) ) ) )
   where
     utente = d_key.utente
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( salt = p_OLD_salt or ( p_OLD_salt is null and ( p_check_OLD is null or salt is null ) ) )
           and ( algoritmo = p_OLD_algoritmo or ( p_OLD_algoritmo is null and ( p_check_OLD is null or algoritmo is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on utenti_salt_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- utenti_salt_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_utente  in UTENTI_SALT.utente%type
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
                                        p_utente => p_utente
                                       )
           , 'existsId on utenti_salt_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on utenti_salt_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on utenti_salt_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on utenti_salt_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update UTENTI_SALT '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( utente ', p_utente, ' )', 0, null ), ' and ( utente is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- utenti_salt_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_utente  in UTENTI_SALT.utente%type
, p_salt  in UTENTI_SALT.salt%type default null
, p_algoritmo  in UTENTI_SALT.algoritmo%type default null
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
              Se p_check_old e NULL, viene controllato se il record corrispondente
              ai soli campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_row_found number;
begin
   DbC.PRE (  not DbC.PreOn
           or not ( (
p_salt is not null
 or p_algoritmo is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on utenti_salt_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_utente => p_utente
                                       )
           , 'existsId on utenti_salt_tpk.del'
           );
   delete from UTENTI_SALT
   where
     utente = p_utente
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( salt = p_salt or ( p_salt is null and ( p_check_OLD is null or salt is null ) ) )
           and ( algoritmo = p_algoritmo or ( p_algoritmo is null and ( p_check_OLD is null or algoritmo is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on utenti_salt_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_utente => p_utente
                                             )
            , 'existsId on utenti_salt_tpk.del'
            );
end del; -- utenti_salt_tpk.del
--------------------------------------------------------------------------------
function get_salt
(
  p_utente  in UTENTI_SALT.utente%type
) return UTENTI_SALT.salt%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_salt
 DESCRIZIONE: Getter per attributo salt di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_SALT.salt%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result UTENTI_SALT.salt%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
                                        )
           , 'existsId on utenti_salt_tpk.get_salt'
           );
   select salt
   into   d_result
   from   UTENTI_SALT
   where
   utente = p_utente
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on utenti_salt_tpk.get_salt'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'salt')
                    , ' AFC_DDL.IsNullable on utenti_salt_tpk.get_salt'
                    );
   end if;
   return  d_result;
end get_salt; -- utenti_salt_tpk.get_salt
--------------------------------------------------------------------------------
function get_algoritmo
(
  p_utente  in UTENTI_SALT.utente%type
) return UTENTI_SALT.algoritmo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_algoritmo
 DESCRIZIONE: Getter per attributo algoritmo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_SALT.algoritmo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result UTENTI_SALT.algoritmo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
                                        )
           , 'existsId on utenti_salt_tpk.get_algoritmo'
           );
   select algoritmo
   into   d_result
   from   UTENTI_SALT
   where
   utente = p_utente
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on utenti_salt_tpk.get_algoritmo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'algoritmo')
                    , ' AFC_DDL.IsNullable on utenti_salt_tpk.get_algoritmo'
                    );
   end if;
   return  d_result;
end get_algoritmo; -- utenti_salt_tpk.get_algoritmo
--------------------------------------------------------------------------------
procedure set_utente
(
  p_utente  in UTENTI_SALT.utente%type
, p_value  in UTENTI_SALT.utente%type default null
) is
/******************************************************************************
 NOME:        set_utente
 DESCRIZIONE: Setter per attributo utente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
                                        )
           , 'existsId on utenti_salt_tpk.set_utente'
           );
   update UTENTI_SALT
   set utente = p_value
   where
   utente = p_utente
   ;
end set_utente; -- utenti_salt_tpk.set_utente
--------------------------------------------------------------------------------
procedure set_salt
(
  p_utente  in UTENTI_SALT.utente%type
, p_value  in UTENTI_SALT.salt%type default null
) is
/******************************************************************************
 NOME:        set_salt
 DESCRIZIONE: Setter per attributo salt di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
                                        )
           , 'existsId on utenti_salt_tpk.set_salt'
           );
   update UTENTI_SALT
   set salt = p_value
   where
   utente = p_utente
   ;
end set_salt; -- utenti_salt_tpk.set_salt
--------------------------------------------------------------------------------
procedure set_algoritmo
(
  p_utente  in UTENTI_SALT.utente%type
, p_value  in UTENTI_SALT.algoritmo%type default null
) is
/******************************************************************************
 NOME:        set_algoritmo
 DESCRIZIONE: Setter per attributo algoritmo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
                                        )
           , 'existsId on utenti_salt_tpk.set_algoritmo'
           );
   update UTENTI_SALT
   set algoritmo = p_value
   where
   utente = p_utente
   ;
end set_algoritmo; -- utenti_salt_tpk.set_algoritmo
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_utente  in varchar2 default null
, p_salt  in varchar2 default null
, p_algoritmo  in varchar2 default null
) return AFC.t_statement is /* SLAVE_COPY */
/******************************************************************************
 NOME:        where_condition
 DESCRIZIONE: Ritorna la where_condition per lo statement di select di get_rows e count_rows.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo e presente
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
               || AFC.get_field_condition( ' and ( utente ', p_utente, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( salt ', p_salt , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( algoritmo ', p_algoritmo , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- utenti_salt_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_utente  in varchar2 default null
, p_salt  in varchar2 default null
, p_algoritmo  in varchar2 default null
) return AFC.t_ref_cursor is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_rows
 DESCRIZIONE: Ritorna il risultato di una query in base ai valori che passiamo.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo e presente
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
   d_statement := ' select UTENTI_SALT.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from UTENTI_SALT '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_utente => p_utente
                                 , p_salt => p_salt
                                 , p_algoritmo => p_algoritmo
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- utenti_salt_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_utente  in varchar2 default null
, p_salt  in varchar2 default null
, p_algoritmo  in varchar2 default null
) return integer is /* SLAVE_COPY */
/******************************************************************************
 NOME:        count_rows
 DESCRIZIONE: Ritorna il numero di righe della tabella gli attributi delle quali
              rispettano i valori indicati.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo e presente
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
   d_statement := ' select count( * ) from UTENTI_SALT '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_utente => p_utente
                                 , p_salt => p_salt
                                 , p_algoritmo => p_algoritmo
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- utenti_salt_tpk.count_rows
--------------------------------------------------------------------------------

end utenti_salt_tpk;
/

