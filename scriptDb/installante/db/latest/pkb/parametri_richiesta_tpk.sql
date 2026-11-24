--liquibase formatted sql

--changeset mturra:201901301231_162 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY parametri_richiesta_tpk is
/******************************************************************************
 NOME:        parametri_richiesta_tpk
 DESCRIZIONE: Gestione tabella PARAMETRI_RICHIESTA.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   30/09/2009  mmalferrari  Prima emissione.
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
end versione; -- parametri_richiesta_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.id_parametro := p_id_parametro;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_id_parametro => d_result.id_parametro
                                        )
           , 'canHandle on parametri_richiesta_tpk.PK'
           );
   return  d_result;
end PK; -- parametri_richiesta_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
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
          p_id_parametro is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on parametri_richiesta_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- parametri_richiesta_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_id_parametro => p_id_parametro
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- parametri_richiesta_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
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
                                         p_id_parametro => p_id_parametro
                                        )
           , 'canHandle on parametri_richiesta_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   PARAMETRI_RICHIESTA
      where
      id_parametro = p_id_parametro
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on parametri_richiesta_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- parametri_richiesta_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_id_parametro => p_id_parametro
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- parametri_richiesta_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type default null
, p_id_richiesta  in PARAMETRI_RICHIESTA.id_richiesta%type
, p_parametro  in PARAMETRI_RICHIESTA.parametro%type
, p_valore  in PARAMETRI_RICHIESTA.valore%type default null
, p_utente_aggiornamento  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default null
, p_data_aggiornamento  in PARAMETRI_RICHIESTA.data_aggiornamento%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_id_richiesta is not null or /*default value*/ '' is not null
           , 'p_id_richiesta on parametri_richiesta_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_parametro is not null or /*default value*/ '' is not null
           , 'p_parametro on parametri_richiesta_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_valore is not null or /*default value*/ 'default' is not null
           , 'p_valore on parametri_richiesta_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_utente_aggiornamento on parametri_richiesta_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_data_aggiornamento on parametri_richiesta_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id_parametro is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id_parametro => p_id_parametro
                           )
           , 'not existsId on parametri_richiesta_tpk.ins'
           );
   insert into PARAMETRI_RICHIESTA
   (
     id_parametro
   , id_richiesta
   , parametro
   , valore
   , utente_aggiornamento
   , data_aggiornamento
   )
   values
   (
     p_id_parametro
   , p_id_richiesta
   , p_parametro
   , p_valore
   , p_utente_aggiornamento
   , p_data_aggiornamento
   );
end ins; -- parametri_richiesta_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type default null
, p_id_richiesta  in PARAMETRI_RICHIESTA.id_richiesta%type
, p_parametro  in PARAMETRI_RICHIESTA.parametro%type
, p_valore  in PARAMETRI_RICHIESTA.valore%type default null
, p_utente_aggiornamento  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default null
, p_data_aggiornamento  in PARAMETRI_RICHIESTA.data_aggiornamento%type default null
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
   DbC.PRE ( not DbC.PreOn or p_id_richiesta is not null or /*default value*/ '' is not null
           , 'p_id_richiesta on parametri_richiesta_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_parametro is not null or /*default value*/ '' is not null
           , 'p_parametro on parametri_richiesta_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_valore is not null or /*default value*/ 'default' is not null
           , 'p_valore on parametri_richiesta_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_utente_aggiornamento on parametri_richiesta_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_data_aggiornamento on parametri_richiesta_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id_parametro is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id_parametro => p_id_parametro
                           )
           , 'not existsId on parametri_richiesta_tpk.ins'
           );
   begin
      insert into PARAMETRI_RICHIESTA
      (
        id_parametro
      , id_richiesta
      , parametro
      , valore
      , utente_aggiornamento
      , data_aggiornamento
      )
      values
      (
        p_id_parametro
      , p_id_richiesta
      , p_parametro
      , p_valore
      , p_utente_aggiornamento
      , p_data_aggiornamento
      ) returning id_parametro
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
end ins; -- parametri_richiesta_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
, p_OLD_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type default null
, p_NEW_id_richiesta  in PARAMETRI_RICHIESTA.id_richiesta%type default afc.default_null('PARAMETRI_RICHIESTA.id_richiesta')
, p_OLD_id_richiesta  in PARAMETRI_RICHIESTA.id_richiesta%type default null
, p_NEW_parametro  in PARAMETRI_RICHIESTA.parametro%type default afc.default_null('PARAMETRI_RICHIESTA.parametro')
, p_OLD_parametro  in PARAMETRI_RICHIESTA.parametro%type default null
, p_NEW_valore  in PARAMETRI_RICHIESTA.valore%type default afc.default_null('PARAMETRI_RICHIESTA.valore')
, p_OLD_valore  in PARAMETRI_RICHIESTA.valore%type default null
, p_NEW_utente_aggiornamento  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default afc.default_null('PARAMETRI_RICHIESTA.utente_aggiornamento')
, p_OLD_utente_aggiornamento  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default null
, p_NEW_data_aggiornamento  in PARAMETRI_RICHIESTA.data_aggiornamento%type default afc.default_null('PARAMETRI_RICHIESTA.data_aggiornamento')
, p_OLD_data_aggiornamento  in PARAMETRI_RICHIESTA.data_aggiornamento%type default null
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
              Se p_check_old e NULL, viene controllato se il record corrispondente
              ai soli campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_key t_PK;
   d_row_found number;
begin
   DbC.PRE (  not DbC.PreOn
           or not ( (
p_OLD_id_richiesta is not null
 or p_OLD_parametro is not null
 or p_OLD_valore is not null
 or p_OLD_utente_aggiornamento is not null
 or p_OLD_data_aggiornamento is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on parametri_richiesta_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_id_parametro, p_NEW_id_parametro )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id_parametro => d_key.id_parametro
                                       )
           , 'existsId on parametri_richiesta_tpk.upd'
           );
   update PARAMETRI_RICHIESTA
   set
       id_parametro = nvl( p_NEW_id_parametro, decode( afc.is_default_null( 'PARAMETRI_RICHIESTA.id_parametro'), 1, id_parametro, null) )
     , id_richiesta = nvl( p_NEW_id_richiesta, decode( afc.is_default_null( 'PARAMETRI_RICHIESTA.id_richiesta'), 1, id_richiesta, null) )
     , parametro = nvl( p_NEW_parametro, decode( afc.is_default_null( 'PARAMETRI_RICHIESTA.parametro'), 1, parametro, null) )
     , valore = nvl( p_NEW_valore, decode( afc.is_default_null( 'PARAMETRI_RICHIESTA.valore'), 1, valore, null) )
     , utente_aggiornamento = nvl( p_NEW_utente_aggiornamento, decode( afc.is_default_null( 'PARAMETRI_RICHIESTA.utente_aggiornamento'), 1, utente_aggiornamento, null) )
     , data_aggiornamento = nvl( p_NEW_data_aggiornamento, decode( afc.is_default_null( 'PARAMETRI_RICHIESTA.data_aggiornamento'), 1, data_aggiornamento, null) )
   where
     id_parametro = d_key.id_parametro
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( id_richiesta = p_OLD_id_richiesta or ( p_OLD_id_richiesta is null and ( p_check_OLD is null or id_richiesta is null ) ) )
           and ( parametro = p_OLD_parametro or ( p_OLD_parametro is null and ( p_check_OLD is null or parametro is null ) ) )
           and ( valore = p_OLD_valore or ( p_OLD_valore is null and ( p_check_OLD is null or valore is null ) ) )
           and ( utente_aggiornamento = p_OLD_utente_aggiornamento or ( p_OLD_utente_aggiornamento is null and ( p_check_OLD is null or utente_aggiornamento is null ) ) )
           and ( data_aggiornamento = p_OLD_data_aggiornamento or ( p_OLD_data_aggiornamento is null and ( p_check_OLD is null or data_aggiornamento is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on parametri_richiesta_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- parametri_richiesta_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
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
                                        p_id_parametro => p_id_parametro
                                       )
           , 'existsId on parametri_richiesta_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on parametri_richiesta_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on parametri_richiesta_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on parametri_richiesta_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update PARAMETRI_RICHIESTA '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( id_parametro ', p_id_parametro, ' )', 0, null ), ' and ( id_parametro is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- parametri_richiesta_tpk.upd_column
--------------------------------------------------------------------------------
procedure upd_column
(
p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
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
p_id_parametro => p_id_parametro
              , p_column => p_column
              , p_value => 'to_date( ''' || d_data || ''', ''' || AFC.date_format || ''' )'
              , p_literal_value => 0
              );
end upd_column; -- parametri_richiesta_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
, p_id_richiesta  in PARAMETRI_RICHIESTA.id_richiesta%type default null
, p_parametro  in PARAMETRI_RICHIESTA.parametro%type default null
, p_valore  in PARAMETRI_RICHIESTA.valore%type default null
, p_utente_aggiornamento  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default null
, p_data_aggiornamento  in PARAMETRI_RICHIESTA.data_aggiornamento%type default null
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
p_id_richiesta is not null
 or p_parametro is not null
 or p_valore is not null
 or p_utente_aggiornamento is not null
 or p_data_aggiornamento is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on parametri_richiesta_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id_parametro => p_id_parametro
                                       )
           , 'existsId on parametri_richiesta_tpk.del'
           );
   delete from PARAMETRI_RICHIESTA
   where
     id_parametro = p_id_parametro
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( id_richiesta = p_id_richiesta or ( p_id_richiesta is null and ( p_check_OLD is null or id_richiesta is null ) ) )
           and ( parametro = p_parametro or ( p_parametro is null and ( p_check_OLD is null or parametro is null ) ) )
           and ( valore = p_valore or ( p_valore is null and ( p_check_OLD is null or valore is null ) ) )
           and ( utente_aggiornamento = p_utente_aggiornamento or ( p_utente_aggiornamento is null and ( p_check_OLD is null or utente_aggiornamento is null ) ) )
           and ( data_aggiornamento = p_data_aggiornamento or ( p_data_aggiornamento is null and ( p_check_OLD is null or data_aggiornamento is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on parametri_richiesta_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_id_parametro => p_id_parametro
                                             )
            , 'existsId on parametri_richiesta_tpk.del'
            );
end del; -- parametri_richiesta_tpk.del
--------------------------------------------------------------------------------
function get_id_richiesta
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
) return PARAMETRI_RICHIESTA.id_richiesta%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_id_richiesta
 DESCRIZIONE: Getter per attributo id_richiesta di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     PARAMETRI_RICHIESTA.id_richiesta%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result PARAMETRI_RICHIESTA.id_richiesta%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_parametro => p_id_parametro
                                        )
           , 'existsId on parametri_richiesta_tpk.get_id_richiesta'
           );
   select id_richiesta
   into   d_result
   from   PARAMETRI_RICHIESTA
   where
   id_parametro = p_id_parametro
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on parametri_richiesta_tpk.get_id_richiesta'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'id_richiesta')
                    , ' AFC_DDL.IsNullable on parametri_richiesta_tpk.get_id_richiesta'
                    );
   end if;
   return  d_result;
end get_id_richiesta; -- parametri_richiesta_tpk.get_id_richiesta
--------------------------------------------------------------------------------
function get_parametro
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
) return PARAMETRI_RICHIESTA.parametro%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_parametro
 DESCRIZIONE: Getter per attributo parametro di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     PARAMETRI_RICHIESTA.parametro%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result PARAMETRI_RICHIESTA.parametro%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_parametro => p_id_parametro
                                        )
           , 'existsId on parametri_richiesta_tpk.get_parametro'
           );
   select parametro
   into   d_result
   from   PARAMETRI_RICHIESTA
   where
   id_parametro = p_id_parametro
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on parametri_richiesta_tpk.get_parametro'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'parametro')
                    , ' AFC_DDL.IsNullable on parametri_richiesta_tpk.get_parametro'
                    );
   end if;
   return  d_result;
end get_parametro; -- parametri_richiesta_tpk.get_parametro
--------------------------------------------------------------------------------
function get_valore
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
) return PARAMETRI_RICHIESTA.valore%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_valore
 DESCRIZIONE: Getter per attributo valore di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     PARAMETRI_RICHIESTA.valore%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result PARAMETRI_RICHIESTA.valore%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_parametro => p_id_parametro
                                        )
           , 'existsId on parametri_richiesta_tpk.get_valore'
           );
   select valore
   into   d_result
   from   PARAMETRI_RICHIESTA
   where
   id_parametro = p_id_parametro
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on parametri_richiesta_tpk.get_valore'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'valore')
                    , ' AFC_DDL.IsNullable on parametri_richiesta_tpk.get_valore'
                    );
   end if;
   return  d_result;
end get_valore; -- parametri_richiesta_tpk.get_valore
--------------------------------------------------------------------------------
function get_utente_aggiornamento
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
) return PARAMETRI_RICHIESTA.utente_aggiornamento%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_utente_aggiornamento
 DESCRIZIONE: Getter per attributo utente_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     PARAMETRI_RICHIESTA.utente_aggiornamento%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result PARAMETRI_RICHIESTA.utente_aggiornamento%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_parametro => p_id_parametro
                                        )
           , 'existsId on parametri_richiesta_tpk.get_utente_aggiornamento'
           );
   select utente_aggiornamento
   into   d_result
   from   PARAMETRI_RICHIESTA
   where
   id_parametro = p_id_parametro
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on parametri_richiesta_tpk.get_utente_aggiornamento'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'utente_aggiornamento')
                    , ' AFC_DDL.IsNullable on parametri_richiesta_tpk.get_utente_aggiornamento'
                    );
   end if;
   return  d_result;
end get_utente_aggiornamento; -- parametri_richiesta_tpk.get_utente_aggiornamento
--------------------------------------------------------------------------------
function get_data_aggiornamento
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
) return PARAMETRI_RICHIESTA.data_aggiornamento%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data_aggiornamento
 DESCRIZIONE: Getter per attributo data_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     PARAMETRI_RICHIESTA.data_aggiornamento%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result PARAMETRI_RICHIESTA.data_aggiornamento%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_parametro => p_id_parametro
                                        )
           , 'existsId on parametri_richiesta_tpk.get_data_aggiornamento'
           );
   select data_aggiornamento
   into   d_result
   from   PARAMETRI_RICHIESTA
   where
   id_parametro = p_id_parametro
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on parametri_richiesta_tpk.get_data_aggiornamento'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data_aggiornamento')
                    , ' AFC_DDL.IsNullable on parametri_richiesta_tpk.get_data_aggiornamento'
                    );
   end if;
   return  d_result;
end get_data_aggiornamento; -- parametri_richiesta_tpk.get_data_aggiornamento
--------------------------------------------------------------------------------
procedure set_id_parametro
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
, p_value  in PARAMETRI_RICHIESTA.id_parametro%type default null
) is
/******************************************************************************
 NOME:        set_id_parametro
 DESCRIZIONE: Setter per attributo id_parametro di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_parametro => p_id_parametro
                                        )
           , 'existsId on parametri_richiesta_tpk.set_id_parametro'
           );
   update PARAMETRI_RICHIESTA
   set id_parametro = p_value
   where
   id_parametro = p_id_parametro
   ;
end set_id_parametro; -- parametri_richiesta_tpk.set_id_parametro
--------------------------------------------------------------------------------
procedure set_id_richiesta
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
, p_value  in PARAMETRI_RICHIESTA.id_richiesta%type default null
) is
/******************************************************************************
 NOME:        set_id_richiesta
 DESCRIZIONE: Setter per attributo id_richiesta di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_parametro => p_id_parametro
                                        )
           , 'existsId on parametri_richiesta_tpk.set_id_richiesta'
           );
   update PARAMETRI_RICHIESTA
   set id_richiesta = p_value
   where
   id_parametro = p_id_parametro
   ;
end set_id_richiesta; -- parametri_richiesta_tpk.set_id_richiesta
--------------------------------------------------------------------------------
procedure set_parametro
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
, p_value  in PARAMETRI_RICHIESTA.parametro%type default null
) is
/******************************************************************************
 NOME:        set_parametro
 DESCRIZIONE: Setter per attributo parametro di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_parametro => p_id_parametro
                                        )
           , 'existsId on parametri_richiesta_tpk.set_parametro'
           );
   update PARAMETRI_RICHIESTA
   set parametro = p_value
   where
   id_parametro = p_id_parametro
   ;
end set_parametro; -- parametri_richiesta_tpk.set_parametro
--------------------------------------------------------------------------------
procedure set_valore
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
, p_value  in PARAMETRI_RICHIESTA.valore%type default null
) is
/******************************************************************************
 NOME:        set_valore
 DESCRIZIONE: Setter per attributo valore di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_parametro => p_id_parametro
                                        )
           , 'existsId on parametri_richiesta_tpk.set_valore'
           );
   update PARAMETRI_RICHIESTA
   set valore = p_value
   where
   id_parametro = p_id_parametro
   ;
end set_valore; -- parametri_richiesta_tpk.set_valore
--------------------------------------------------------------------------------
procedure set_utente_aggiornamento
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
, p_value  in PARAMETRI_RICHIESTA.utente_aggiornamento%type default null
) is
/******************************************************************************
 NOME:        set_utente_aggiornamento
 DESCRIZIONE: Setter per attributo utente_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_parametro => p_id_parametro
                                        )
           , 'existsId on parametri_richiesta_tpk.set_utente_aggiornamento'
           );
   update PARAMETRI_RICHIESTA
   set utente_aggiornamento = p_value
   where
   id_parametro = p_id_parametro
   ;
end set_utente_aggiornamento; -- parametri_richiesta_tpk.set_utente_aggiornamento
--------------------------------------------------------------------------------
procedure set_data_aggiornamento
(
  p_id_parametro  in PARAMETRI_RICHIESTA.id_parametro%type
, p_value  in PARAMETRI_RICHIESTA.data_aggiornamento%type default null
) is
/******************************************************************************
 NOME:        set_data_aggiornamento
 DESCRIZIONE: Setter per attributo data_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_parametro => p_id_parametro
                                        )
           , 'existsId on parametri_richiesta_tpk.set_data_aggiornamento'
           );
   update PARAMETRI_RICHIESTA
   set data_aggiornamento = p_value
   where
   id_parametro = p_id_parametro
   ;
end set_data_aggiornamento; -- parametri_richiesta_tpk.set_data_aggiornamento
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id_parametro  in varchar2 default null
, p_id_richiesta  in varchar2 default null
, p_parametro  in varchar2 default null
, p_valore  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( id_parametro ', p_id_parametro, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( id_richiesta ', p_id_richiesta , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( parametro ', p_parametro , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( valore ', p_valore , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( utente_aggiornamento ', p_utente_aggiornamento , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data_aggiornamento ', p_data_aggiornamento , ' )', p_QBE, AFC.date_format )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- parametri_richiesta_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_id_parametro  in varchar2 default null
, p_id_richiesta  in varchar2 default null
, p_parametro  in varchar2 default null
, p_valore  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
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
   d_statement := ' select PARAMETRI_RICHIESTA.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from PARAMETRI_RICHIESTA '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id_parametro => p_id_parametro
                                 , p_id_richiesta => p_id_richiesta
                                 , p_parametro => p_parametro
                                 , p_valore => p_valore
                                 , p_utente_aggiornamento => p_utente_aggiornamento
                                 , p_data_aggiornamento => p_data_aggiornamento
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- parametri_richiesta_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id_parametro  in varchar2 default null
, p_id_richiesta  in varchar2 default null
, p_parametro  in varchar2 default null
, p_valore  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
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
   d_statement := ' select count( * ) from PARAMETRI_RICHIESTA '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id_parametro => p_id_parametro
                                 , p_id_richiesta => p_id_richiesta
                                 , p_parametro => p_parametro
                                 , p_valore => p_valore
                                 , p_utente_aggiornamento => p_utente_aggiornamento
                                 , p_data_aggiornamento => p_data_aggiornamento
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- parametri_richiesta_tpk.count_rows
--------------------------------------------------------------------------------
end parametri_richiesta_tpk;
/

