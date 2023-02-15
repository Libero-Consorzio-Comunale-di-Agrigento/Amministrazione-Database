CREATE OR REPLACE PACKAGE BODY utenti_privacy_tpk is
/******************************************************************************
 NOME:        utenti_privacy_tpk
 DESCRIZIONE: Gestione tabella UTENTI_PRIVACY.
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
end versione; -- utenti_privacy_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_id  in UTENTI_PRIVACY.id%type
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
           , 'canHandle on utenti_privacy_tpk.PK'
           );
   return  d_result;
end PK; -- utenti_privacy_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_id  in UTENTI_PRIVACY.id%type
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
            , 'd_result = 1  or  d_result = 0 on utenti_privacy_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- utenti_privacy_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_id  in UTENTI_PRIVACY.id%type
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
end canHandle; -- utenti_privacy_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_id  in UTENTI_PRIVACY.id%type
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
           , 'canHandle on utenti_privacy_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   UTENTI_PRIVACY
      where
      id = p_id
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on utenti_privacy_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- utenti_privacy_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_id  in UTENTI_PRIVACY.id%type
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
end existsId; -- utenti_privacy_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_id  in UTENTI_PRIVACY.id%type default null
, p_utente  in UTENTI_PRIVACY.utente%type default null
, p_soggetto  in UTENTI_PRIVACY.soggetto%type default null
, p_ente  in UTENTI_PRIVACY.ente%type default null
, p_modulo  in UTENTI_PRIVACY.modulo%type default null
, p_data_prima_accettazione  in UTENTI_PRIVACY.data_prima_accettazione%type default null
, p_client_prima_accettazione  in UTENTI_PRIVACY.client_prima_accettazione%type default null
, p_data_ultima_accettazione  in UTENTI_PRIVACY.data_ultima_accettazione%type default null
, p_client_ultima_accettazione  in UTENTI_PRIVACY.client_ultima_accettazione%type default null
, p_note  in UTENTI_PRIVACY.note%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_utente is not null or /*default value*/ 'default' is not null
           , 'p_utente on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_soggetto is not null or /*default value*/ 'default' is not null
           , 'p_soggetto on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ente is not null or /*default value*/ 'default' is not null
           , 'p_ente on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ 'default' is not null
           , 'p_modulo on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_prima_accettazione is not null or /*default value*/ 'default' is not null
           , 'p_data_prima_accettazione on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_client_prima_accettazione is not null or /*default value*/ 'default' is not null
           , 'p_client_prima_accettazione on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_ultima_accettazione is not null or /*default value*/ 'default' is not null
           , 'p_data_ultima_accettazione on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_client_ultima_accettazione is not null or /*default value*/ 'default' is not null
           , 'p_client_ultima_accettazione on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on utenti_privacy_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id => p_id
                           )
           , 'not existsId on utenti_privacy_tpk.ins'
           );
   insert into UTENTI_PRIVACY
   (
     id
   , utente
   , soggetto
   , ente
   , modulo
   , data_prima_accettazione
   , client_prima_accettazione
   , data_ultima_accettazione
   , client_ultima_accettazione
   , note
   )
   values
   (
     p_id
   , p_utente
   , p_soggetto
   , p_ente
   , p_modulo
   , p_data_prima_accettazione
   , p_client_prima_accettazione
   , p_data_ultima_accettazione
   , p_client_ultima_accettazione
   , p_note
   );
end ins; -- utenti_privacy_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_id  in UTENTI_PRIVACY.id%type default null
, p_utente  in UTENTI_PRIVACY.utente%type default null
, p_soggetto  in UTENTI_PRIVACY.soggetto%type default null
, p_ente  in UTENTI_PRIVACY.ente%type default null
, p_modulo  in UTENTI_PRIVACY.modulo%type default null
, p_data_prima_accettazione  in UTENTI_PRIVACY.data_prima_accettazione%type default null
, p_client_prima_accettazione  in UTENTI_PRIVACY.client_prima_accettazione%type default null
, p_data_ultima_accettazione  in UTENTI_PRIVACY.data_ultima_accettazione%type default null
, p_client_ultima_accettazione  in UTENTI_PRIVACY.client_ultima_accettazione%type default null
, p_note  in UTENTI_PRIVACY.note%type default null
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
   DbC.PRE ( not DbC.PreOn or p_utente is not null or /*default value*/ 'default' is not null
           , 'p_utente on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_soggetto is not null or /*default value*/ 'default' is not null
           , 'p_soggetto on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ente is not null or /*default value*/ 'default' is not null
           , 'p_ente on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ 'default' is not null
           , 'p_modulo on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_prima_accettazione is not null or /*default value*/ 'default' is not null
           , 'p_data_prima_accettazione on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_client_prima_accettazione is not null or /*default value*/ 'default' is not null
           , 'p_client_prima_accettazione on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_ultima_accettazione is not null or /*default value*/ 'default' is not null
           , 'p_data_ultima_accettazione on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_client_ultima_accettazione is not null or /*default value*/ 'default' is not null
           , 'p_client_ultima_accettazione on utenti_privacy_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on utenti_privacy_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id => p_id
                           )
           , 'not existsId on utenti_privacy_tpk.ins'
           );
   begin
      insert into UTENTI_PRIVACY
      (
        id
      , utente
      , soggetto
      , ente
      , modulo
      , data_prima_accettazione
      , client_prima_accettazione
      , data_ultima_accettazione
      , client_ultima_accettazione
      , note
      )
      values
      (
        p_id
      , p_utente
      , p_soggetto
      , p_ente
      , p_modulo
      , p_data_prima_accettazione
      , p_client_prima_accettazione
      , p_data_ultima_accettazione
      , p_client_ultima_accettazione
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
end ins; -- utenti_privacy_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_id  in UTENTI_PRIVACY.id%type
, p_OLD_id  in UTENTI_PRIVACY.id%type default null
, p_NEW_utente  in UTENTI_PRIVACY.utente%type default afc.default_null('UTENTI_PRIVACY.utente')
, p_OLD_utente  in UTENTI_PRIVACY.utente%type default null
, p_NEW_soggetto  in UTENTI_PRIVACY.soggetto%type default afc.default_null('UTENTI_PRIVACY.soggetto')
, p_OLD_soggetto  in UTENTI_PRIVACY.soggetto%type default null
, p_NEW_ente  in UTENTI_PRIVACY.ente%type default afc.default_null('UTENTI_PRIVACY.ente')
, p_OLD_ente  in UTENTI_PRIVACY.ente%type default null
, p_NEW_modulo  in UTENTI_PRIVACY.modulo%type default afc.default_null('UTENTI_PRIVACY.modulo')
, p_OLD_modulo  in UTENTI_PRIVACY.modulo%type default null
, p_NEW_data_prima_accettazione  in UTENTI_PRIVACY.data_prima_accettazione%type default afc.default_null('UTENTI_PRIVACY.data_prima_accettazione')
, p_OLD_data_prima_accettazione  in UTENTI_PRIVACY.data_prima_accettazione%type default null
, p_NEW_client_prima_accettazion  in UTENTI_PRIVACY.client_prima_accettazione%type default afc.default_null('UTENTI_PRIVACY.client_prima_accettazione')
, p_OLD_client_prima_accettazion  in UTENTI_PRIVACY.client_prima_accettazione%type default null
, p_NEW_data_ultima_accettazione  in UTENTI_PRIVACY.data_ultima_accettazione%type default afc.default_null('UTENTI_PRIVACY.data_ultima_accettazione')
, p_OLD_data_ultima_accettazione  in UTENTI_PRIVACY.data_ultima_accettazione%type default null
, p_NEW_client_ultima_accettazio  in UTENTI_PRIVACY.client_ultima_accettazione%type default afc.default_null('UTENTI_PRIVACY.client_ultima_accettazione')
, p_OLD_client_ultima_accettazio  in UTENTI_PRIVACY.client_ultima_accettazione%type default null
, p_NEW_note  in UTENTI_PRIVACY.note%type default afc.default_null('UTENTI_PRIVACY.note')
, p_OLD_note  in UTENTI_PRIVACY.note%type default null
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
p_OLD_utente is not null
 or p_OLD_soggetto is not null
 or p_OLD_ente is not null
 or p_OLD_modulo is not null
 or p_OLD_data_prima_accettazione is not null
 or p_OLD_client_prima_accettazion is not null
 or p_OLD_data_ultima_accettazione is not null
 or p_OLD_client_ultima_accettazio is not null
 or p_OLD_note is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on utenti_privacy_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_id, p_NEW_id )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id => d_key.id
                                       )
           , 'existsId on utenti_privacy_tpk.upd'
           );
   update UTENTI_PRIVACY
   set
       id = nvl( p_NEW_id, decode( afc.is_default_null( 'UTENTI_PRIVACY.id'), 1, id, null) )
     , utente = nvl( p_NEW_utente, decode( afc.is_default_null( 'UTENTI_PRIVACY.utente'), 1, utente, null) )
     , soggetto = nvl( p_NEW_soggetto, decode( afc.is_default_null( 'UTENTI_PRIVACY.soggetto'), 1, soggetto, null) )
     , ente = nvl( p_NEW_ente, decode( afc.is_default_null( 'UTENTI_PRIVACY.ente'), 1, ente, null) )
     , modulo = nvl( p_NEW_modulo, decode( afc.is_default_null( 'UTENTI_PRIVACY.modulo'), 1, modulo, null) )
     , data_prima_accettazione = nvl( p_NEW_data_prima_accettazione, decode( afc.is_default_null( 'UTENTI_PRIVACY.data_prima_accettazione'), 1, data_prima_accettazione, null) )
     , client_prima_accettazione = nvl( p_NEW_client_prima_accettazion, decode( afc.is_default_null( 'UTENTI_PRIVACY.client_prima_accettazione'), 1, client_prima_accettazione, null) )
     , data_ultima_accettazione = nvl( p_NEW_data_ultima_accettazione, decode( afc.is_default_null( 'UTENTI_PRIVACY.data_ultima_accettazione'), 1, data_ultima_accettazione, null) )
     , client_ultima_accettazione = nvl( p_NEW_client_ultima_accettazio, decode( afc.is_default_null( 'UTENTI_PRIVACY.client_ultima_accettazione'), 1, client_ultima_accettazione, null) )
     , note = nvl( p_NEW_note, decode( afc.is_default_null( 'UTENTI_PRIVACY.note'), 1, note, null) )
   where
     id = d_key.id
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( utente = p_OLD_utente or ( p_OLD_utente is null and ( p_check_OLD is null or utente is null ) ) )
           and ( soggetto = p_OLD_soggetto or ( p_OLD_soggetto is null and ( p_check_OLD is null or soggetto is null ) ) )
           and ( ente = p_OLD_ente or ( p_OLD_ente is null and ( p_check_OLD is null or ente is null ) ) )
           and ( modulo = p_OLD_modulo or ( p_OLD_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( data_prima_accettazione = p_OLD_data_prima_accettazione or ( p_OLD_data_prima_accettazione is null and ( p_check_OLD is null or data_prima_accettazione is null ) ) )
           and ( client_prima_accettazione = p_OLD_client_prima_accettazion or ( p_OLD_client_prima_accettazion is null and ( p_check_OLD is null or client_prima_accettazione is null ) ) )
           and ( data_ultima_accettazione = p_OLD_data_ultima_accettazione or ( p_OLD_data_ultima_accettazione is null and ( p_check_OLD is null or data_ultima_accettazione is null ) ) )
           and ( client_ultima_accettazione = p_OLD_client_ultima_accettazio or ( p_OLD_client_ultima_accettazio is null and ( p_check_OLD is null or client_ultima_accettazione is null ) ) )
           and ( note = p_OLD_note or ( p_OLD_note is null and ( p_check_OLD is null or note is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on utenti_privacy_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- utenti_privacy_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_id  in UTENTI_PRIVACY.id%type
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
           , 'existsId on utenti_privacy_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on utenti_privacy_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on utenti_privacy_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on utenti_privacy_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update UTENTI_PRIVACY '
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
end upd_column; -- utenti_privacy_tpk.upd_column
--------------------------------------------------------------------------------
procedure upd_column
(
p_id  in UTENTI_PRIVACY.id%type
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
end upd_column; -- utenti_privacy_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_id  in UTENTI_PRIVACY.id%type
, p_utente  in UTENTI_PRIVACY.utente%type default null
, p_soggetto  in UTENTI_PRIVACY.soggetto%type default null
, p_ente  in UTENTI_PRIVACY.ente%type default null
, p_modulo  in UTENTI_PRIVACY.modulo%type default null
, p_data_prima_accettazione  in UTENTI_PRIVACY.data_prima_accettazione%type default null
, p_client_prima_accettazione  in UTENTI_PRIVACY.client_prima_accettazione%type default null
, p_data_ultima_accettazione  in UTENTI_PRIVACY.data_ultima_accettazione%type default null
, p_client_ultima_accettazione  in UTENTI_PRIVACY.client_ultima_accettazione%type default null
, p_note  in UTENTI_PRIVACY.note%type default null
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
p_utente is not null
 or p_soggetto is not null
 or p_ente is not null
 or p_modulo is not null
 or p_data_prima_accettazione is not null
 or p_client_prima_accettazione is not null
 or p_data_ultima_accettazione is not null
 or p_client_ultima_accettazione is not null
 or p_note is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on utenti_privacy_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id => p_id
                                       )
           , 'existsId on utenti_privacy_tpk.del'
           );
   delete from UTENTI_PRIVACY
   where
     id = p_id
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( utente = p_utente or ( p_utente is null and ( p_check_OLD is null or utente is null ) ) )
           and ( soggetto = p_soggetto or ( p_soggetto is null and ( p_check_OLD is null or soggetto is null ) ) )
           and ( ente = p_ente or ( p_ente is null and ( p_check_OLD is null or ente is null ) ) )
           and ( modulo = p_modulo or ( p_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( data_prima_accettazione = p_data_prima_accettazione or ( p_data_prima_accettazione is null and ( p_check_OLD is null or data_prima_accettazione is null ) ) )
           and ( client_prima_accettazione = p_client_prima_accettazione or ( p_client_prima_accettazione is null and ( p_check_OLD is null or client_prima_accettazione is null ) ) )
           and ( data_ultima_accettazione = p_data_ultima_accettazione or ( p_data_ultima_accettazione is null and ( p_check_OLD is null or data_ultima_accettazione is null ) ) )
           and ( client_ultima_accettazione = p_client_ultima_accettazione or ( p_client_ultima_accettazione is null and ( p_check_OLD is null or client_ultima_accettazione is null ) ) )
           and ( note = p_note or ( p_note is null and ( p_check_OLD is null or note is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on utenti_privacy_tpk.del'
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
            , 'existsId on utenti_privacy_tpk.del'
            );
end del; -- utenti_privacy_tpk.del
--------------------------------------------------------------------------------
function get_utente
(
  p_id  in UTENTI_PRIVACY.id%type
) return UTENTI_PRIVACY.utente%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_utente
 DESCRIZIONE: Getter per attributo utente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_PRIVACY.utente%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result UTENTI_PRIVACY.utente%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.get_utente'
           );
   select utente
   into   d_result
   from   UTENTI_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on utenti_privacy_tpk.get_utente'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'utente')
                    , ' AFC_DDL.IsNullable on utenti_privacy_tpk.get_utente'
                    );
   end if;
   return  d_result;
end get_utente; -- utenti_privacy_tpk.get_utente
--------------------------------------------------------------------------------
function get_soggetto
(
  p_id  in UTENTI_PRIVACY.id%type
) return UTENTI_PRIVACY.soggetto%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_soggetto
 DESCRIZIONE: Getter per attributo soggetto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_PRIVACY.soggetto%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result UTENTI_PRIVACY.soggetto%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.get_soggetto'
           );
   select soggetto
   into   d_result
   from   UTENTI_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on utenti_privacy_tpk.get_soggetto'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'soggetto')
                    , ' AFC_DDL.IsNullable on utenti_privacy_tpk.get_soggetto'
                    );
   end if;
   return  d_result;
end get_soggetto; -- utenti_privacy_tpk.get_soggetto
--------------------------------------------------------------------------------
function get_ente
(
  p_id  in UTENTI_PRIVACY.id%type
) return UTENTI_PRIVACY.ente%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_ente
 DESCRIZIONE: Getter per attributo ente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_PRIVACY.ente%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result UTENTI_PRIVACY.ente%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.get_ente'
           );
   select ente
   into   d_result
   from   UTENTI_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on utenti_privacy_tpk.get_ente'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'ente')
                    , ' AFC_DDL.IsNullable on utenti_privacy_tpk.get_ente'
                    );
   end if;
   return  d_result;
end get_ente; -- utenti_privacy_tpk.get_ente
--------------------------------------------------------------------------------
function get_modulo
(
  p_id  in UTENTI_PRIVACY.id%type
) return UTENTI_PRIVACY.modulo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_modulo
 DESCRIZIONE: Getter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_PRIVACY.modulo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result UTENTI_PRIVACY.modulo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.get_modulo'
           );
   select modulo
   into   d_result
   from   UTENTI_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on utenti_privacy_tpk.get_modulo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'modulo')
                    , ' AFC_DDL.IsNullable on utenti_privacy_tpk.get_modulo'
                    );
   end if;
   return  d_result;
end get_modulo; -- utenti_privacy_tpk.get_modulo
--------------------------------------------------------------------------------
function get_data_prima_accettazione
(
  p_id  in UTENTI_PRIVACY.id%type
) return UTENTI_PRIVACY.data_prima_accettazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data_prima_accettazione
 DESCRIZIONE: Getter per attributo data_prima_accettazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_PRIVACY.data_prima_accettazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result UTENTI_PRIVACY.data_prima_accettazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.get_data_prima_accettazione'
           );
   select data_prima_accettazione
   into   d_result
   from   UTENTI_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on utenti_privacy_tpk.get_data_prima_accettazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data_prima_accettazione')
                    , ' AFC_DDL.IsNullable on utenti_privacy_tpk.get_data_prima_accettazione'
                    );
   end if;
   return  d_result;
end get_data_prima_accettazione; -- utenti_privacy_tpk.get_data_prima_accettazione
--------------------------------------------------------------------------------
function get_client_prima_accettazione
(
  p_id  in UTENTI_PRIVACY.id%type
) return UTENTI_PRIVACY.client_prima_accettazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_client_prima_accettazione
 DESCRIZIONE: Getter per attributo client_prima_accettazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_PRIVACY.client_prima_accettazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result UTENTI_PRIVACY.client_prima_accettazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.get_client_prima_accettazione'
           );
   select client_prima_accettazione
   into   d_result
   from   UTENTI_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on utenti_privacy_tpk.get_client_prima_accettazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'client_prima_accettazione')
                    , ' AFC_DDL.IsNullable on utenti_privacy_tpk.get_client_prima_accettazione'
                    );
   end if;
   return  d_result;
end get_client_prima_accettazione; -- utenti_privacy_tpk.get_client_prima_accettazione
--------------------------------------------------------------------------------
function get_data_ultima_accettazione
(
  p_id  in UTENTI_PRIVACY.id%type
) return UTENTI_PRIVACY.data_ultima_accettazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data_ultima_accettazione
 DESCRIZIONE: Getter per attributo data_ultima_accettazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_PRIVACY.data_ultima_accettazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result UTENTI_PRIVACY.data_ultima_accettazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.get_data_ultima_accettazione'
           );
   select data_ultima_accettazione
   into   d_result
   from   UTENTI_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on utenti_privacy_tpk.get_data_ultima_accettazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data_ultima_accettazione')
                    , ' AFC_DDL.IsNullable on utenti_privacy_tpk.get_data_ultima_accettazione'
                    );
   end if;
   return  d_result;
end get_data_ultima_accettazione; -- utenti_privacy_tpk.get_data_ultima_accettazione
--------------------------------------------------------------------------------
function get_client_ultima_accettazione
(
  p_id  in UTENTI_PRIVACY.id%type
) return UTENTI_PRIVACY.client_ultima_accettazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_client_ultima_accettazione
 DESCRIZIONE: Getter per attributo client_ultima_accettazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_PRIVACY.client_ultima_accettazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result UTENTI_PRIVACY.client_ultima_accettazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.get_client_ultima_accettazione'
           );
   select client_ultima_accettazione
   into   d_result
   from   UTENTI_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on utenti_privacy_tpk.get_client_ultima_accettazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'client_ultima_accettazione')
                    , ' AFC_DDL.IsNullable on utenti_privacy_tpk.get_client_ultima_accettazione'
                    );
   end if;
   return  d_result;
end get_client_ultima_accettazione; -- utenti_privacy_tpk.get_client_ultima_accettazione
--------------------------------------------------------------------------------
function get_note
(
  p_id  in UTENTI_PRIVACY.id%type
) return UTENTI_PRIVACY.note%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_note
 DESCRIZIONE: Getter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_PRIVACY.note%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result UTENTI_PRIVACY.note%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.get_note'
           );
   select note
   into   d_result
   from   UTENTI_PRIVACY
   where
   id = p_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on utenti_privacy_tpk.get_note'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'note')
                    , ' AFC_DDL.IsNullable on utenti_privacy_tpk.get_note'
                    );
   end if;
   return  d_result;
end get_note; -- utenti_privacy_tpk.get_note
--------------------------------------------------------------------------------
procedure set_id
(
  p_id  in UTENTI_PRIVACY.id%type
, p_value  in UTENTI_PRIVACY.id%type default null
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
           , 'existsId on utenti_privacy_tpk.set_id'
           );
   update UTENTI_PRIVACY
   set id = p_value
   where
   id = p_id
   ;
end set_id; -- utenti_privacy_tpk.set_id
--------------------------------------------------------------------------------
procedure set_utente
(
  p_id  in UTENTI_PRIVACY.id%type
, p_value  in UTENTI_PRIVACY.utente%type default null
) is
/******************************************************************************
 NOME:        set_utente
 DESCRIZIONE: Setter per attributo utente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.set_utente'
           );
   update UTENTI_PRIVACY
   set utente = p_value
   where
   id = p_id
   ;
end set_utente; -- utenti_privacy_tpk.set_utente
--------------------------------------------------------------------------------
procedure set_soggetto
(
  p_id  in UTENTI_PRIVACY.id%type
, p_value  in UTENTI_PRIVACY.soggetto%type default null
) is
/******************************************************************************
 NOME:        set_soggetto
 DESCRIZIONE: Setter per attributo soggetto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.set_soggetto'
           );
   update UTENTI_PRIVACY
   set soggetto = p_value
   where
   id = p_id
   ;
end set_soggetto; -- utenti_privacy_tpk.set_soggetto
--------------------------------------------------------------------------------
procedure set_ente
(
  p_id  in UTENTI_PRIVACY.id%type
, p_value  in UTENTI_PRIVACY.ente%type default null
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
           , 'existsId on utenti_privacy_tpk.set_ente'
           );
   update UTENTI_PRIVACY
   set ente = p_value
   where
   id = p_id
   ;
end set_ente; -- utenti_privacy_tpk.set_ente
--------------------------------------------------------------------------------
procedure set_modulo
(
  p_id  in UTENTI_PRIVACY.id%type
, p_value  in UTENTI_PRIVACY.modulo%type default null
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
           , 'existsId on utenti_privacy_tpk.set_modulo'
           );
   update UTENTI_PRIVACY
   set modulo = p_value
   where
   id = p_id
   ;
end set_modulo; -- utenti_privacy_tpk.set_modulo
--------------------------------------------------------------------------------
procedure set_data_prima_accettazione
(
  p_id  in UTENTI_PRIVACY.id%type
, p_value  in UTENTI_PRIVACY.data_prima_accettazione%type default null
) is
/******************************************************************************
 NOME:        set_data_prima_accettazione
 DESCRIZIONE: Setter per attributo data_prima_accettazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.set_data_prima_accettazione'
           );
   update UTENTI_PRIVACY
   set data_prima_accettazione = p_value
   where
   id = p_id
   ;
end set_data_prima_accettazione; -- utenti_privacy_tpk.set_data_prima_accettazione
--------------------------------------------------------------------------------
procedure set_client_prima_accettazione
(
  p_id  in UTENTI_PRIVACY.id%type
, p_value  in UTENTI_PRIVACY.client_prima_accettazione%type default null
) is
/******************************************************************************
 NOME:        set_client_prima_accettazione
 DESCRIZIONE: Setter per attributo client_prima_accettazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.set_client_prima_accettazione'
           );
   update UTENTI_PRIVACY
   set client_prima_accettazione = p_value
   where
   id = p_id
   ;
end set_client_prima_accettazione; -- utenti_privacy_tpk.set_client_prima_accettazione
--------------------------------------------------------------------------------
procedure set_data_ultima_accettazione
(
  p_id  in UTENTI_PRIVACY.id%type
, p_value  in UTENTI_PRIVACY.data_ultima_accettazione%type default null
) is
/******************************************************************************
 NOME:        set_data_ultima_accettazione
 DESCRIZIONE: Setter per attributo data_ultima_accettazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.set_data_ultima_accettazione'
           );
   update UTENTI_PRIVACY
   set data_ultima_accettazione = p_value
   where
   id = p_id
   ;
end set_data_ultima_accettazione; -- utenti_privacy_tpk.set_data_ultima_accettazione
--------------------------------------------------------------------------------
procedure set_client_ultima_accettazione
(
  p_id  in UTENTI_PRIVACY.id%type
, p_value  in UTENTI_PRIVACY.client_ultima_accettazione%type default null
) is
/******************************************************************************
 NOME:        set_client_ultima_accettazione
 DESCRIZIONE: Setter per attributo client_ultima_accettazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id => p_id
                                        )
           , 'existsId on utenti_privacy_tpk.set_client_ultima_accettazione'
           );
   update UTENTI_PRIVACY
   set client_ultima_accettazione = p_value
   where
   id = p_id
   ;
end set_client_ultima_accettazione; -- utenti_privacy_tpk.set_client_ultima_accettazione
--------------------------------------------------------------------------------
procedure set_note
(
  p_id  in UTENTI_PRIVACY.id%type
, p_value  in UTENTI_PRIVACY.note%type default null
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
           , 'existsId on utenti_privacy_tpk.set_note'
           );
   update UTENTI_PRIVACY
   set note = p_value
   where
   id = p_id
   ;
end set_note; -- utenti_privacy_tpk.set_note
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id  in varchar2 default null
, p_utente  in varchar2 default null
, p_soggetto  in varchar2 default null
, p_ente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_data_prima_accettazione  in varchar2 default null
, p_client_prima_accettazione  in varchar2 default null
, p_data_ultima_accettazione  in varchar2 default null
, p_client_ultima_accettazione  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( utente ', p_utente , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( soggetto ', p_soggetto , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( ente ', p_ente , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( modulo ', p_modulo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data_prima_accettazione ', p_data_prima_accettazione , ' )', p_QBE, AFC.date_format )
               || AFC.get_field_condition( ' and ( client_prima_accettazione ', p_client_prima_accettazione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data_ultima_accettazione ', p_data_ultima_accettazione , ' )', p_QBE, AFC.date_format )
               || AFC.get_field_condition( ' and ( client_ultima_accettazione ', p_client_ultima_accettazione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( note ', p_note , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- utenti_privacy_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_id  in varchar2 default null
, p_utente  in varchar2 default null
, p_soggetto  in varchar2 default null
, p_ente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_data_prima_accettazione  in varchar2 default null
, p_client_prima_accettazione  in varchar2 default null
, p_data_ultima_accettazione  in varchar2 default null
, p_client_ultima_accettazione  in varchar2 default null
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
   d_statement := ' select UTENTI_PRIVACY.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from UTENTI_PRIVACY '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id => p_id
                                 , p_utente => p_utente
                                 , p_soggetto => p_soggetto
                                 , p_ente => p_ente
                                 , p_modulo => p_modulo
                                 , p_data_prima_accettazione => p_data_prima_accettazione
                                 , p_client_prima_accettazione => p_client_prima_accettazione
                                 , p_data_ultima_accettazione => p_data_ultima_accettazione
                                 , p_client_ultima_accettazione => p_client_ultima_accettazione
                                 , p_note => p_note
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- utenti_privacy_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id  in varchar2 default null
, p_utente  in varchar2 default null
, p_soggetto  in varchar2 default null
, p_ente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_data_prima_accettazione  in varchar2 default null
, p_client_prima_accettazione  in varchar2 default null
, p_data_ultima_accettazione  in varchar2 default null
, p_client_ultima_accettazione  in varchar2 default null
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
   d_statement := ' select count( * ) from UTENTI_PRIVACY '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id => p_id
                                 , p_utente => p_utente
                                 , p_soggetto => p_soggetto
                                 , p_ente => p_ente
                                 , p_modulo => p_modulo
                                 , p_data_prima_accettazione => p_data_prima_accettazione
                                 , p_client_prima_accettazione => p_client_prima_accettazione
                                 , p_data_ultima_accettazione => p_data_ultima_accettazione
                                 , p_client_ultima_accettazione => p_client_ultima_accettazione
                                 , p_note => p_note
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- utenti_privacy_tpk.count_rows
--------------------------------------------------------------------------------
end utenti_privacy_tpk;
/

