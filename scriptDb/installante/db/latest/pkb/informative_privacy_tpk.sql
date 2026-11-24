--liquibase formatted sql

--changeset mturra:201901301231_145 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY informative_privacy_tpk is
/******************************************************************************
 NOME:        informative_privacy_tpk
 DESCRIZIONE: Gestione tabella INFORMATIVE_PRIVACY.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   12/03/2018  adadamo  Prima emissione.
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
end versione; -- informative_privacy_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_id  in INFORMATIVE_PRIVACY.id%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.id := p_id;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_id => d_result.id
                                        )
           , 'canHandle on informative_privacy_tpk.PK'
           );
   return  d_result;
end PK; -- informative_privacy_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_id  in INFORMATIVE_PRIVACY.id%type
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
          p_id is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on informative_privacy_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- informative_privacy_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_id  in INFORMATIVE_PRIVACY.id%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave è manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_id => p_id
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- informative_privacy_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_id  in INFORMATIVE_PRIVACY.id%type
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
                                         p_id => p_id
                                        )
           , 'canHandle on informative_privacy_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   INFORMATIVE_PRIVACY
      where
      id = p_id
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on informative_privacy_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- informative_privacy_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_id  in INFORMATIVE_PRIVACY.id%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_id => p_id
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- informative_privacy_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_id  in INFORMATIVE_PRIVACY.id%type default null
, p_ente  in INFORMATIVE_PRIVACY.ente%type default null
, p_modulo  in INFORMATIVE_PRIVACY.modulo%type default null
, p_data_informativa  in INFORMATIVE_PRIVACY.data_informativa%type default null
, p_url_documento_privacy  in INFORMATIVE_PRIVACY.url_documento_privacy%type default null
, p_note  in INFORMATIVE_PRIVACY.note%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_ente is not null or /*default value*/ 'default' is not null
           , 'p_ente on informative_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ 'default' is not null
           , 'p_modulo on informative_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_informativa is not null or /*default value*/ 'default' is not null
           , 'p_data_informativa on informative_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_url_documento_privacy is not null or /*default value*/ 'default' is not null
           , 'p_url_documento_privacy on informative_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on informative_privacy_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id => p_id
                           )
           , 'not existsId on informative_privacy_tpk.ins'
           );
   insert into INFORMATIVE_PRIVACY
   (
     id
   , ente
   , modulo
   , data_informativa
   , url_documento_privacy
   , note
   )
   values
   (
     p_id
   , p_ente
   , p_modulo
   , p_data_informativa
   , p_url_documento_privacy
   , p_note
   );
end ins; -- informative_privacy_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_id  in INFORMATIVE_PRIVACY.id%type default null
, p_ente  in INFORMATIVE_PRIVACY.ente%type default null
, p_modulo  in INFORMATIVE_PRIVACY.modulo%type default null
, p_data_informativa  in INFORMATIVE_PRIVACY.data_informativa%type default null
, p_url_documento_privacy  in INFORMATIVE_PRIVACY.url_documento_privacy%type default null
, p_note  in INFORMATIVE_PRIVACY.note%type default null
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
   DbC.PRE ( not DbC.PreOn or p_ente is not null or /*default value*/ 'default' is not null
           , 'p_ente on informative_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ 'default' is not null
           , 'p_modulo on informative_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_informativa is not null or /*default value*/ 'default' is not null
           , 'p_data_informativa on informative_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_url_documento_privacy is not null or /*default value*/ 'default' is not null
           , 'p_url_documento_privacy on informative_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on informative_privacy_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id => p_id
                           )
           , 'not existsId on informative_privacy_tpk.ins'
           );
   begin
      insert into INFORMATIVE_PRIVACY
      (
        id
      , ente
      , modulo
      , data_informativa
      , url_documento_privacy
      , note
      )
      values
      (
        p_id
      , p_ente
      , p_modulo
      , p_data_informativa
      , p_url_documento_privacy
      , p_note
      ) returning id
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
end ins; -- informative_privacy_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_id  in INFORMATIVE_PRIVACY.id%type
, p_OLD_id  in INFORMATIVE_PRIVACY.id%type default null
, p_NEW_ente  in INFORMATIVE_PRIVACY.ente%type default afc.default_null('INFORMATIVE_PRIVACY.ente')
, p_OLD_ente  in INFORMATIVE_PRIVACY.ente%type default null
, p_NEW_modulo  in INFORMATIVE_PRIVACY.modulo%type default afc.default_null('INFORMATIVE_PRIVACY.modulo')
, p_OLD_modulo  in INFORMATIVE_PRIVACY.modulo%type default null
, p_NEW_data_informativa  in INFORMATIVE_PRIVACY.data_informativa%type default afc.default_null('INFORMATIVE_PRIVACY.data_informativa')
, p_OLD_data_informativa  in INFORMATIVE_PRIVACY.data_informativa%type default null
, p_NEW_url_documento_privacy  in INFORMATIVE_PRIVACY.url_documento_privacy%type default afc.default_null('INFORMATIVE_PRIVACY.url_documento_privacy')
, p_OLD_url_documento_privacy  in INFORMATIVE_PRIVACY.url_documento_privacy%type default null
, p_NEW_note  in INFORMATIVE_PRIVACY.note%type default afc.default_null('INFORMATIVE_PRIVACY.note')
, p_OLD_note  in INFORMATIVE_PRIVACY.note%type default null
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
              Se p_check_old è NULL, viene controllato se il record corrispondente
                  ai soli campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_key t_PK;
   d_row_found number;
begin
   DbC.PRE (  not DbC.PreOn
           or not ( (
p_OLD_ente is not null
 or p_OLD_modulo is not null
 or p_OLD_data_informativa is not null
 or p_OLD_url_documento_privacy is not null
 or p_OLD_note is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on informative_privacy_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_id, p_NEW_id )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id => d_key.id
                                       )
           , 'existsId on informative_privacy_tpk.upd'
           );
   update INFORMATIVE_PRIVACY
   set
       id = nvl( p_NEW_id, decode( afc.is_default_null( 'INFORMATIVE_PRIVACY.id'), 1, id, null) )
     , ente = nvl( p_NEW_ente, decode( afc.is_default_null( 'INFORMATIVE_PRIVACY.ente'), 1, ente, null) )
     , modulo = nvl( p_NEW_modulo, decode( afc.is_default_null( 'INFORMATIVE_PRIVACY.modulo'), 1, modulo, null) )
     , data_informativa = nvl( p_NEW_data_informativa, decode( afc.is_default_null( 'INFORMATIVE_PRIVACY.data_informativa'), 1, data_informativa, null) )
     , url_documento_privacy = nvl( p_NEW_url_documento_privacy, decode( afc.is_default_null( 'INFORMATIVE_PRIVACY.url_documento_privacy'), 1, url_documento_privacy, null) )
     , note = nvl( p_NEW_note, decode( afc.is_default_null( 'INFORMATIVE_PRIVACY.note'), 1, note, null) )
   where
     id = d_key.id
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( ente = p_OLD_ente or ( p_OLD_ente is null and ( p_check_OLD is null or ente is null ) ) )
           and ( modulo = p_OLD_modulo or ( p_OLD_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( data_informativa = p_OLD_data_informativa or ( p_OLD_data_informativa is null and ( p_check_OLD is null or data_informativa is null ) ) )
           and ( url_documento_privacy = p_OLD_url_documento_privacy or ( p_OLD_url_documento_privacy is null and ( p_check_OLD is null or url_documento_privacy is null ) ) )
           and ( note = p_OLD_note or ( p_OLD_note is null and ( p_check_OLD is null or note is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on informative_privacy_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- informative_privacy_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_id  in INFORMATIVE_PRIVACY.id%type
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
                                        p_id => p_id
                                       )
           , 'existsId on informative_privacy_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on informative_privacy_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on informative_privacy_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on informative_privacy_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update INFORMATIVE_PRIVACY '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( id ', p_id, ' )', 0, null ), ' and ( id is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- informative_privacy_tpk.upd_column
--------------------------------------------------------------------------------
procedure upd_column
(
p_id  in INFORMATIVE_PRIVACY.id%type
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
p_id => p_id
              , p_column => p_column
              , p_value => 'to_date( ''' || d_data || ''', ''' || AFC.date_format || ''' )'
              , p_literal_value => 0
              );
end upd_column; -- informative_privacy_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_id  in INFORMATIVE_PRIVACY.id%type
, p_ente  in INFORMATIVE_PRIVACY.ente%type default null
, p_modulo  in INFORMATIVE_PRIVACY.modulo%type default null
, p_data_informativa  in INFORMATIVE_PRIVACY.data_informativa%type default null
, p_url_documento_privacy  in INFORMATIVE_PRIVACY.url_documento_privacy%type default null
, p_note  in INFORMATIVE_PRIVACY.note%type default null
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
              Se p_check_old è NULL, viene controllato se il record corrispondente
                  ai soli campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_row_found number;
begin
   DbC.PRE (  not DbC.PreOn
           or not ( (
p_ente is not null
 or p_modulo is not null
 or p_data_informativa is not null
 or p_url_documento_privacy is not null
 or p_note is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on informative_privacy_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id => p_id
                                       )
           , 'existsId on informative_privacy_tpk.del'
           );
   delete from INFORMATIVE_PRIVACY
   where
     id = p_id
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( ente = p_ente or ( p_ente is null and ( p_check_OLD is null or ente is null ) ) )
           and ( modulo = p_modulo or ( p_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( data_informativa = p_data_informativa or ( p_data_informativa is null and ( p_check_OLD is null or data_informativa is null ) ) )
           and ( url_documento_privacy = p_url_documento_privacy or ( p_url_documento_privacy is null and ( p_check_OLD is null or url_documento_privacy is null ) ) )
           and ( note = p_note or ( p_note is null and ( p_check_OLD is null or note is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on informative_privacy_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_id => p_id
                                             )
            , 'existsId on informative_privacy_tpk.del'
            );
end del; -- informative_privacy_tpk.del
--------------------------------------------------------------------------------
function get_ente
(
  p_id  in INFORMATIVE_PRIVACY.id%type
) return INFORMATIVE_PRIVACY.ente%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_ente
 DESCRIZIONE: Getter per attributo ente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     INFORMATIVE_PRIVACY.ente%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result INFORMATIVE_PRIVACY.ente%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on informative_privacy_tpk.get_ente'
           );
   select ente
   into   d_result
   from   INFORMATIVE_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on informative_privacy_tpk.get_ente'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'ente')
                    , ' AFC_DDL.IsNullable on informative_privacy_tpk.get_ente'
                    );
   end if;
   return  d_result;
end get_ente; -- informative_privacy_tpk.get_ente
--------------------------------------------------------------------------------
function get_modulo
(
  p_id  in INFORMATIVE_PRIVACY.id%type
) return INFORMATIVE_PRIVACY.modulo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_modulo
 DESCRIZIONE: Getter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     INFORMATIVE_PRIVACY.modulo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result INFORMATIVE_PRIVACY.modulo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on informative_privacy_tpk.get_modulo'
           );
   select modulo
   into   d_result
   from   INFORMATIVE_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on informative_privacy_tpk.get_modulo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'modulo')
                    , ' AFC_DDL.IsNullable on informative_privacy_tpk.get_modulo'
                    );
   end if;
   return  d_result;
end get_modulo; -- informative_privacy_tpk.get_modulo
--------------------------------------------------------------------------------
function get_data_informativa
(
  p_id  in INFORMATIVE_PRIVACY.id%type
) return INFORMATIVE_PRIVACY.data_informativa%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data_informativa
 DESCRIZIONE: Getter per attributo data_informativa di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     INFORMATIVE_PRIVACY.data_informativa%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result INFORMATIVE_PRIVACY.data_informativa%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on informative_privacy_tpk.get_data_informativa'
           );
   select data_informativa
   into   d_result
   from   INFORMATIVE_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on informative_privacy_tpk.get_data_informativa'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data_informativa')
                    , ' AFC_DDL.IsNullable on informative_privacy_tpk.get_data_informativa'
                    );
   end if;
   return  d_result;
end get_data_informativa; -- informative_privacy_tpk.get_data_informativa
--------------------------------------------------------------------------------
function get_url_documento_privacy
(
  p_id  in INFORMATIVE_PRIVACY.id%type
) return INFORMATIVE_PRIVACY.url_documento_privacy%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_url_documento_privacy
 DESCRIZIONE: Getter per attributo url_documento_privacy di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     INFORMATIVE_PRIVACY.url_documento_privacy%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result INFORMATIVE_PRIVACY.url_documento_privacy%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on informative_privacy_tpk.get_url_documento_privacy'
           );
   select url_documento_privacy
   into   d_result
   from   INFORMATIVE_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on informative_privacy_tpk.get_url_documento_privacy'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'url_documento_privacy')
                    , ' AFC_DDL.IsNullable on informative_privacy_tpk.get_url_documento_privacy'
                    );
   end if;
   return  d_result;
end get_url_documento_privacy; -- informative_privacy_tpk.get_url_documento_privacy
--------------------------------------------------------------------------------
function get_note
(
  p_id  in INFORMATIVE_PRIVACY.id%type
) return INFORMATIVE_PRIVACY.note%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_note
 DESCRIZIONE: Getter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     INFORMATIVE_PRIVACY.note%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result INFORMATIVE_PRIVACY.note%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on informative_privacy_tpk.get_note'
           );
   select note
   into   d_result
   from   INFORMATIVE_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on informative_privacy_tpk.get_note'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'note')
                    , ' AFC_DDL.IsNullable on informative_privacy_tpk.get_note'
                    );
   end if;
   return  d_result;
end get_note; -- informative_privacy_tpk.get_note
--------------------------------------------------------------------------------
procedure set_id
(
  p_id  in INFORMATIVE_PRIVACY.id%type
, p_value  in INFORMATIVE_PRIVACY.id%type default null
) is
/******************************************************************************
 NOME:        set_id
 DESCRIZIONE: Setter per attributo id di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on informative_privacy_tpk.set_id'
           );
   update INFORMATIVE_PRIVACY
   set id = p_value
   where
   id = p_id
   ;
end set_id; -- informative_privacy_tpk.set_id
--------------------------------------------------------------------------------
procedure set_ente
(
  p_id  in INFORMATIVE_PRIVACY.id%type
, p_value  in INFORMATIVE_PRIVACY.ente%type default null
) is
/******************************************************************************
 NOME:        set_ente
 DESCRIZIONE: Setter per attributo ente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on informative_privacy_tpk.set_ente'
           );
   update INFORMATIVE_PRIVACY
   set ente = p_value
   where
   id = p_id
   ;
end set_ente; -- informative_privacy_tpk.set_ente
--------------------------------------------------------------------------------
procedure set_modulo
(
  p_id  in INFORMATIVE_PRIVACY.id%type
, p_value  in INFORMATIVE_PRIVACY.modulo%type default null
) is
/******************************************************************************
 NOME:        set_modulo
 DESCRIZIONE: Setter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on informative_privacy_tpk.set_modulo'
           );
   update INFORMATIVE_PRIVACY
   set modulo = p_value
   where
   id = p_id
   ;
end set_modulo; -- informative_privacy_tpk.set_modulo
--------------------------------------------------------------------------------
procedure set_data_informativa
(
  p_id  in INFORMATIVE_PRIVACY.id%type
, p_value  in INFORMATIVE_PRIVACY.data_informativa%type default null
) is
/******************************************************************************
 NOME:        set_data_informativa
 DESCRIZIONE: Setter per attributo data_informativa di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on informative_privacy_tpk.set_data_informativa'
           );
   update INFORMATIVE_PRIVACY
   set data_informativa = p_value
   where
   id = p_id
   ;
end set_data_informativa; -- informative_privacy_tpk.set_data_informativa
--------------------------------------------------------------------------------
procedure set_url_documento_privacy
(
  p_id  in INFORMATIVE_PRIVACY.id%type
, p_value  in INFORMATIVE_PRIVACY.url_documento_privacy%type default null
) is
/******************************************************************************
 NOME:        set_url_documento_privacy
 DESCRIZIONE: Setter per attributo url_documento_privacy di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on informative_privacy_tpk.set_url_documento_privacy'
           );
   update INFORMATIVE_PRIVACY
   set url_documento_privacy = p_value
   where
   id = p_id
   ;
end set_url_documento_privacy; -- informative_privacy_tpk.set_url_documento_privacy
--------------------------------------------------------------------------------
procedure set_note
(
  p_id  in INFORMATIVE_PRIVACY.id%type
, p_value  in INFORMATIVE_PRIVACY.note%type default null
) is
/******************************************************************************
 NOME:        set_note
 DESCRIZIONE: Setter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on informative_privacy_tpk.set_note'
           );
   update INFORMATIVE_PRIVACY
   set note = p_value
   where
   id = p_id
   ;
end set_note; -- informative_privacy_tpk.set_note
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id  in varchar2 default null
, p_ente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_data_informativa  in varchar2 default null
, p_url_documento_privacy  in varchar2 default null
, p_note  in varchar2 default null
) return AFC.t_statement is /* SLAVE_COPY */
/******************************************************************************
 NOME:        where_condition
 DESCRIZIONE: Ritorna la where_condition per lo statement di select di get_rows e count_rows.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo è presente
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
               || AFC.get_field_condition( ' and ( id ', p_id, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( ente ', p_ente , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( modulo ', p_modulo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data_informativa ', p_data_informativa , ' )', p_QBE, AFC.date_format )
               || AFC.get_field_condition( ' and ( url_documento_privacy ', p_url_documento_privacy , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( note ', p_note , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- informative_privacy_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_id  in varchar2 default null
, p_ente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_data_informativa  in varchar2 default null
, p_url_documento_privacy  in varchar2 default null
, p_note  in varchar2 default null
) return AFC.t_ref_cursor is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_rows
 DESCRIZIONE: Ritorna il risultato di una query in base ai valori che passiamo.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo è presente
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
   d_statement := ' select INFORMATIVE_PRIVACY.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from INFORMATIVE_PRIVACY '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id => p_id
                                 , p_ente => p_ente
                                 , p_modulo => p_modulo
                                 , p_data_informativa => p_data_informativa
                                 , p_url_documento_privacy => p_url_documento_privacy
                                 , p_note => p_note
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- informative_privacy_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id  in varchar2 default null
, p_ente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_data_informativa  in varchar2 default null
, p_url_documento_privacy  in varchar2 default null
, p_note  in varchar2 default null
) return integer is /* SLAVE_COPY */
/******************************************************************************
 NOME:        count_rows
 DESCRIZIONE: Ritorna il numero di righe della tabella gli attributi delle quali
              rispettano i valori indicati.
 PARAMETRI:   p_QBE 0: viene controllato se all'inizio di ogni attributo è presente
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
   d_statement := ' select count( * ) from INFORMATIVE_PRIVACY '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id => p_id
                                 , p_ente => p_ente
                                 , p_modulo => p_modulo
                                 , p_data_informativa => p_data_informativa
                                 , p_url_documento_privacy => p_url_documento_privacy
                                 , p_note => p_note
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- informative_privacy_tpk.count_rows
--------------------------------------------------------------------------------
end informative_privacy_tpk;
/

