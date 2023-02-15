CREATE OR REPLACE PACKAGE BODY regioni_tpk is
/******************************************************************************
 NOME:        regioni_tpk
 DESCRIZIONE: Gestione tabella REGIONI.
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
end versione; -- regioni_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_regione  in REGIONI.regione%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.regione := p_regione;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_regione => d_result.regione
                                        )
           , 'canHandle on regioni_tpk.PK'
           );
   return  d_result;
end PK; -- regioni_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_regione  in REGIONI.regione%type
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
          p_regione is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on regioni_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- regioni_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_regione  in REGIONI.regione%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_regione => p_regione
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- regioni_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_regione  in REGIONI.regione%type
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
                                         p_regione => p_regione
                                        )
           , 'canHandle on regioni_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   REGIONI
      where
      regione = p_regione
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on regioni_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- regioni_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_regione  in REGIONI.regione%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_regione => p_regione
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- regioni_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_regione  in REGIONI.regione%type default null
, p_denominazione  in REGIONI.denominazione%type default null
, p_denominazione_al1  in REGIONI.denominazione_al1%type default null
, p_denominazione_al2  in REGIONI.denominazione_al2%type default null
, p_id_regione  in REGIONI.id_regione%type
, p_utente_aggiornamento  in REGIONI.utente_aggiornamento%type default null
, p_data_aggiornamento  in REGIONI.data_aggiornamento%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_denominazione is not null or /*default value*/ 'default' is not null
           , 'p_denominazione on regioni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al1 on regioni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al2 on regioni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_id_regione is not null or /*default value*/ '' is not null
           , 'p_id_regione on regioni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_utente_aggiornamento on regioni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_data_aggiornamento on regioni_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_regione is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_regione => p_regione
                           )
           , 'not existsId on regioni_tpk.ins'
           );
   insert into REGIONI
   (
     regione
   , denominazione
   , denominazione_al1
   , denominazione_al2
   , id_regione
   , utente_aggiornamento
   , data_aggiornamento
   )
   values
   (
     p_regione
   , p_denominazione
   , p_denominazione_al1
   , p_denominazione_al2
   , p_id_regione
   , p_utente_aggiornamento
   , p_data_aggiornamento
   );
end ins; -- regioni_tpk.ins
--------------------------------------------------------------------------------
function ins  /*+ SOA  */
(
  p_regione  in REGIONI.regione%type default null
, p_denominazione  in REGIONI.denominazione%type default null
, p_denominazione_al1  in REGIONI.denominazione_al1%type default null
, p_denominazione_al2  in REGIONI.denominazione_al2%type default null
, p_id_regione  in REGIONI.id_regione%type
, p_utente_aggiornamento  in REGIONI.utente_aggiornamento%type default null
, p_data_aggiornamento  in REGIONI.data_aggiornamento%type default null
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
   DbC.PRE ( not DbC.PreOn or p_denominazione is not null or /*default value*/ 'default' is not null
           , 'p_denominazione on regioni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al1 on regioni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al2 on regioni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_id_regione is not null or /*default value*/ '' is not null
           , 'p_id_regione on regioni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_utente_aggiornamento on regioni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_data_aggiornamento on regioni_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_regione is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_regione => p_regione
                           )
           , 'not existsId on regioni_tpk.ins'
           );
   begin
      insert into REGIONI
      (
        regione
      , denominazione
      , denominazione_al1
      , denominazione_al2
      , id_regione
      , utente_aggiornamento
      , data_aggiornamento
      )
      values
      (
        p_regione
      , p_denominazione
      , p_denominazione_al1
      , p_denominazione_al2
      , p_id_regione
      , p_utente_aggiornamento
      , p_data_aggiornamento
      ) returning regione
      into d_result;
      if d_result < 0
      then
         d_result := 0;
      end if;
   exception
      when others then
         d_result := sqlcode;
   end;
   return d_result;
end ins; -- regioni_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_regione  in REGIONI.regione%type
, p_OLD_regione  in REGIONI.regione%type default null
, p_NEW_denominazione  in REGIONI.denominazione%type default null
, p_OLD_denominazione  in REGIONI.denominazione%type default null
, p_NEW_denominazione_al1  in REGIONI.denominazione_al1%type default null
, p_OLD_denominazione_al1  in REGIONI.denominazione_al1%type default null
, p_NEW_denominazione_al2  in REGIONI.denominazione_al2%type default null
, p_OLD_denominazione_al2  in REGIONI.denominazione_al2%type default null
, p_NEW_id_regione  in REGIONI.id_regione%type default null
, p_OLD_id_regione  in REGIONI.id_regione%type default null
, p_NEW_utente_aggiornamento  in REGIONI.utente_aggiornamento%type default null
, p_OLD_utente_aggiornamento  in REGIONI.utente_aggiornamento%type default null
, p_NEW_data_aggiornamento  in REGIONI.data_aggiornamento%type default null
, p_OLD_data_aggiornamento  in REGIONI.data_aggiornamento%type default null
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
p_OLD_denominazione is not null or
p_OLD_denominazione_al1 is not null or
p_OLD_denominazione_al2 is not null or
p_OLD_id_regione is not null or
p_OLD_utente_aggiornamento is not null or
p_OLD_data_aggiornamento is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on regioni_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_regione, p_NEW_regione )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_regione => d_key.regione
                                       )
           , 'existsId on regioni_tpk.upd'
           );
   update REGIONI
   set
       regione = decode( p_check_OLD, 0,p_NEW_regione, decode(p_NEW_regione, p_OLD_regione,regione,p_NEW_regione))
     , denominazione = decode( p_check_OLD, 0,p_NEW_denominazione, decode(p_NEW_denominazione, p_OLD_denominazione,denominazione,p_NEW_denominazione))
     , denominazione_al1 = decode( p_check_OLD, 0,p_NEW_denominazione_al1, decode(p_NEW_denominazione_al1, p_OLD_denominazione_al1,denominazione_al1,p_NEW_denominazione_al1))
     , denominazione_al2 = decode( p_check_OLD, 0,p_NEW_denominazione_al2, decode(p_NEW_denominazione_al2, p_OLD_denominazione_al2,denominazione_al2,p_NEW_denominazione_al2))
     , id_regione = decode( p_check_OLD, 0,p_NEW_id_regione, decode(p_NEW_id_regione, p_OLD_id_regione,id_regione,p_NEW_id_regione))
     , utente_aggiornamento = decode( p_check_OLD, 0,p_NEW_utente_aggiornamento, decode(p_NEW_utente_aggiornamento, p_OLD_utente_aggiornamento,utente_aggiornamento,p_NEW_utente_aggiornamento))
     , data_aggiornamento = decode( p_check_OLD, 0,p_NEW_data_aggiornamento, decode(p_NEW_data_aggiornamento, p_OLD_data_aggiornamento,data_aggiornamento,p_NEW_data_aggiornamento))
   where
     regione = d_key.regione
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( denominazione = p_OLD_denominazione or ( p_OLD_denominazione is null and ( p_check_OLD is null or denominazione is null ) ) )
           and ( denominazione_al1 = p_OLD_denominazione_al1 or ( p_OLD_denominazione_al1 is null and ( p_check_OLD is null or denominazione_al1 is null ) ) )
           and ( denominazione_al2 = p_OLD_denominazione_al2 or ( p_OLD_denominazione_al2 is null and ( p_check_OLD is null or denominazione_al2 is null ) ) )
           and ( id_regione = p_OLD_id_regione or ( p_OLD_id_regione is null and ( p_check_OLD is null or id_regione is null ) ) )
           and ( utente_aggiornamento = p_OLD_utente_aggiornamento or ( p_OLD_utente_aggiornamento is null and ( p_check_OLD is null or utente_aggiornamento is null ) ) )
           and ( data_aggiornamento = p_OLD_data_aggiornamento or ( p_OLD_data_aggiornamento is null and ( p_check_OLD is null or data_aggiornamento is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on regioni_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- regioni_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_regione  in REGIONI.regione%type
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
                                        p_regione => p_regione
                                       )
           , 'existsId on regioni_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on regioni_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on regioni_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on regioni_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update REGIONI '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( regione ', p_regione, ' )', 0, null ), ' and ( regione is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- regioni_tpk.upd_column
--------------------------------------------------------------------------------
procedure upd_column
(
p_regione  in REGIONI.regione%type
, p_column  in varchar2
, p_value   in date
) is
/******************************************************************************
 NOME:        upd_column
 DESCRIZIONE: Aggiornamento del campo p_column col valore p_value.
 NOTE:        Richiama se stessa con il parametro date convertito in stringa.
******************************************************************************/
   d_data varchar2(19);
begin
   d_data := to_char( p_value, AFC.date_format );
   upd_column (
p_regione => p_regione
              , p_column => p_column
              , p_value => 'to_date( ''' || d_data || ''', ''' || AFC.date_format || ''' )'
              , p_literal_value => 0
              );
end upd_column; -- regioni_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_regione  in REGIONI.regione%type
, p_denominazione  in REGIONI.denominazione%type default null
, p_denominazione_al1  in REGIONI.denominazione_al1%type default null
, p_denominazione_al2  in REGIONI.denominazione_al2%type default null
, p_id_regione  in REGIONI.id_regione%type default null
, p_utente_aggiornamento  in REGIONI.utente_aggiornamento%type default null
, p_data_aggiornamento  in REGIONI.data_aggiornamento%type default null
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
p_denominazione is not null or
p_denominazione_al1 is not null or
p_denominazione_al2 is not null or
p_id_regione is not null or
p_utente_aggiornamento is not null or
p_data_aggiornamento is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on regioni_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_regione => p_regione
                                       )
           , 'existsId on regioni_tpk.del'
           );
   delete from REGIONI
   where
     regione = p_regione
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( denominazione = p_denominazione or ( p_denominazione is null and ( p_check_OLD is null or denominazione is null ) ) )
           and ( denominazione_al1 = p_denominazione_al1 or ( p_denominazione_al1 is null and ( p_check_OLD is null or denominazione_al1 is null ) ) )
           and ( denominazione_al2 = p_denominazione_al2 or ( p_denominazione_al2 is null and ( p_check_OLD is null or denominazione_al2 is null ) ) )
           and ( id_regione = p_id_regione or ( p_id_regione is null and ( p_check_OLD is null or id_regione is null ) ) )
           and ( utente_aggiornamento = p_utente_aggiornamento or ( p_utente_aggiornamento is null and ( p_check_OLD is null or utente_aggiornamento is null ) ) )
           and ( data_aggiornamento = p_data_aggiornamento or ( p_data_aggiornamento is null and ( p_check_OLD is null or data_aggiornamento is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on regioni_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_regione => p_regione
                                             )
            , 'existsId on regioni_tpk.del'
            );
end del; -- regioni_tpk.del
--------------------------------------------------------------------------------
function get_denominazione
(
  p_regione  in REGIONI.regione%type
) return REGIONI.denominazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione
 DESCRIZIONE: Getter per attributo denominazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     REGIONI.denominazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result REGIONI.denominazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.get_denominazione'
           );
   select denominazione
   into   d_result
   from   REGIONI
   where
   regione = p_regione
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on regioni_tpk.get_denominazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione')
                    , ' AFC_DDL.IsNullable on regioni_tpk.get_denominazione'
                    );
   end if;
   return  d_result;
end get_denominazione; -- regioni_tpk.get_denominazione
--------------------------------------------------------------------------------
function get_denominazione_al1
(
  p_regione  in REGIONI.regione%type
) return REGIONI.denominazione_al1%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione_al1
 DESCRIZIONE: Getter per attributo denominazione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     REGIONI.denominazione_al1%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result REGIONI.denominazione_al1%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.get_denominazione_al1'
           );
   select denominazione_al1
   into   d_result
   from   REGIONI
   where
   regione = p_regione
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on regioni_tpk.get_denominazione_al1'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione_al1')
                    , ' AFC_DDL.IsNullable on regioni_tpk.get_denominazione_al1'
                    );
   end if;
   return  d_result;
end get_denominazione_al1; -- regioni_tpk.get_denominazione_al1
--------------------------------------------------------------------------------
function get_denominazione_al2
(
  p_regione  in REGIONI.regione%type
) return REGIONI.denominazione_al2%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione_al2
 DESCRIZIONE: Getter per attributo denominazione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     REGIONI.denominazione_al2%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result REGIONI.denominazione_al2%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.get_denominazione_al2'
           );
   select denominazione_al2
   into   d_result
   from   REGIONI
   where
   regione = p_regione
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on regioni_tpk.get_denominazione_al2'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione_al2')
                    , ' AFC_DDL.IsNullable on regioni_tpk.get_denominazione_al2'
                    );
   end if;
   return  d_result;
end get_denominazione_al2; -- regioni_tpk.get_denominazione_al2
--------------------------------------------------------------------------------
function get_id_regione
(
  p_regione  in REGIONI.regione%type
) return REGIONI.id_regione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_id_regione
 DESCRIZIONE: Getter per attributo id_regione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     REGIONI.id_regione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result REGIONI.id_regione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.get_id_regione'
           );
   select id_regione
   into   d_result
   from   REGIONI
   where
   regione = p_regione
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on regioni_tpk.get_id_regione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'id_regione')
                    , ' AFC_DDL.IsNullable on regioni_tpk.get_id_regione'
                    );
   end if;
   return  d_result;
end get_id_regione; -- regioni_tpk.get_id_regione
--------------------------------------------------------------------------------
function get_utente_aggiornamento
(
  p_regione  in REGIONI.regione%type
) return REGIONI.utente_aggiornamento%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_utente_aggiornamento
 DESCRIZIONE: Getter per attributo utente_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     REGIONI.utente_aggiornamento%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result REGIONI.utente_aggiornamento%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.get_utente_aggiornamento'
           );
   select utente_aggiornamento
   into   d_result
   from   REGIONI
   where
   regione = p_regione
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on regioni_tpk.get_utente_aggiornamento'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'utente_aggiornamento')
                    , ' AFC_DDL.IsNullable on regioni_tpk.get_utente_aggiornamento'
                    );
   end if;
   return  d_result;
end get_utente_aggiornamento; -- regioni_tpk.get_utente_aggiornamento
--------------------------------------------------------------------------------
function get_data_aggiornamento
(
  p_regione  in REGIONI.regione%type
) return REGIONI.data_aggiornamento%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data_aggiornamento
 DESCRIZIONE: Getter per attributo data_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     REGIONI.data_aggiornamento%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result REGIONI.data_aggiornamento%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.get_data_aggiornamento'
           );
   select data_aggiornamento
   into   d_result
   from   REGIONI
   where
   regione = p_regione
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on regioni_tpk.get_data_aggiornamento'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data_aggiornamento')
                    , ' AFC_DDL.IsNullable on regioni_tpk.get_data_aggiornamento'
                    );
   end if;
   return  d_result;
end get_data_aggiornamento; -- regioni_tpk.get_data_aggiornamento
--------------------------------------------------------------------------------
procedure set_regione
(
  p_regione  in REGIONI.regione%type
, p_value  in REGIONI.regione%type default null
) is
/******************************************************************************
 NOME:        set_regione
 DESCRIZIONE: Setter per attributo regione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.set_regione'
           );
   update REGIONI
   set regione = p_value
   where
   regione = p_regione
   ;
end set_regione; -- regioni_tpk.set_regione
--------------------------------------------------------------------------------
procedure set_denominazione
(
  p_regione  in REGIONI.regione%type
, p_value  in REGIONI.denominazione%type default null
) is
/******************************************************************************
 NOME:        set_denominazione
 DESCRIZIONE: Setter per attributo denominazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.set_denominazione'
           );
   update REGIONI
   set denominazione = p_value
   where
   regione = p_regione
   ;
end set_denominazione; -- regioni_tpk.set_denominazione
--------------------------------------------------------------------------------
procedure set_denominazione_al1
(
  p_regione  in REGIONI.regione%type
, p_value  in REGIONI.denominazione_al1%type default null
) is
/******************************************************************************
 NOME:        set_denominazione_al1
 DESCRIZIONE: Setter per attributo denominazione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.set_denominazione_al1'
           );
   update REGIONI
   set denominazione_al1 = p_value
   where
   regione = p_regione
   ;
end set_denominazione_al1; -- regioni_tpk.set_denominazione_al1
--------------------------------------------------------------------------------
procedure set_denominazione_al2
(
  p_regione  in REGIONI.regione%type
, p_value  in REGIONI.denominazione_al2%type default null
) is
/******************************************************************************
 NOME:        set_denominazione_al2
 DESCRIZIONE: Setter per attributo denominazione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.set_denominazione_al2'
           );
   update REGIONI
   set denominazione_al2 = p_value
   where
   regione = p_regione
   ;
end set_denominazione_al2; -- regioni_tpk.set_denominazione_al2
--------------------------------------------------------------------------------
procedure set_id_regione
(
  p_regione  in REGIONI.regione%type
, p_value  in REGIONI.id_regione%type default null
) is
/******************************************************************************
 NOME:        set_id_regione
 DESCRIZIONE: Setter per attributo id_regione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.set_id_regione'
           );
   update REGIONI
   set id_regione = p_value
   where
   regione = p_regione
   ;
end set_id_regione; -- regioni_tpk.set_id_regione
--------------------------------------------------------------------------------
procedure set_utente_aggiornamento
(
  p_regione  in REGIONI.regione%type
, p_value  in REGIONI.utente_aggiornamento%type default null
) is
/******************************************************************************
 NOME:        set_utente_aggiornamento
 DESCRIZIONE: Setter per attributo utente_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.set_utente_aggiornamento'
           );
   update REGIONI
   set utente_aggiornamento = p_value
   where
   regione = p_regione
   ;
end set_utente_aggiornamento; -- regioni_tpk.set_utente_aggiornamento
--------------------------------------------------------------------------------
procedure set_data_aggiornamento
(
  p_regione  in REGIONI.regione%type
, p_value  in REGIONI.data_aggiornamento%type default null
) is
/******************************************************************************
 NOME:        set_data_aggiornamento
 DESCRIZIONE: Setter per attributo data_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_regione => p_regione
                                        )
           , 'existsId on regioni_tpk.set_data_aggiornamento'
           );
   update REGIONI
   set data_aggiornamento = p_value
   where
   regione = p_regione
   ;
end set_data_aggiornamento; -- regioni_tpk.set_data_aggiornamento
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_regione  in varchar2 default null
, p_denominazione  in varchar2 default null
, p_denominazione_al1  in varchar2 default null
, p_denominazione_al2  in varchar2 default null
, p_id_regione  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( regione ', p_regione, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione ', p_denominazione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione_al1 ', p_denominazione_al1 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione_al2 ', p_denominazione_al2 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( id_regione ', p_id_regione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( utente_aggiornamento ', p_utente_aggiornamento , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data_aggiornamento ', p_data_aggiornamento , ' )', p_QBE, AFC.date_format )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- regioni_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_regione  in varchar2 default null
, p_denominazione  in varchar2 default null
, p_denominazione_al1  in varchar2 default null
, p_denominazione_al2  in varchar2 default null
, p_id_regione  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
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
   d_statement := ' select REGIONI.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from REGIONI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_regione => p_regione
                                 , p_denominazione => p_denominazione
                                 , p_denominazione_al1 => p_denominazione_al1
                                 , p_denominazione_al2 => p_denominazione_al2
                                 , p_id_regione => p_id_regione
                                 , p_utente_aggiornamento => p_utente_aggiornamento
                                 , p_data_aggiornamento => p_data_aggiornamento
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- regioni_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_regione  in varchar2 default null
, p_denominazione  in varchar2 default null
, p_denominazione_al1  in varchar2 default null
, p_denominazione_al2  in varchar2 default null
, p_id_regione  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
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
   d_statement := ' select count( * ) from REGIONI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_regione => p_regione
                                 , p_denominazione => p_denominazione
                                 , p_denominazione_al1 => p_denominazione_al1
                                 , p_denominazione_al2 => p_denominazione_al2
                                 , p_id_regione => p_id_regione
                                 , p_utente_aggiornamento => p_utente_aggiornamento
                                 , p_data_aggiornamento => p_data_aggiornamento
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- regioni_tpk.count_rows
--------------------------------------------------------------------------------
end regioni_tpk;
/

