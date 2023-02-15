CREATE OR REPLACE PACKAGE BODY accessi_tpk is
/******************************************************************************
 NOME:        accessi_tpk
 DESCRIZIONE: Gestione tabella ACCESSI.
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
end versione; -- accessi_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_acce_id  in ACCESSI.acce_id%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.acce_id := p_acce_id;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_acce_id => d_result.acce_id
                                        )
           , 'canHandle on accessi_tpk.PK'
           );
   return  d_result;
end PK; -- accessi_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_acce_id  in ACCESSI.acce_id%type
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
          p_acce_id is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on accessi_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- accessi_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_acce_id  in ACCESSI.acce_id%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_acce_id => p_acce_id
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- accessi_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_acce_id  in ACCESSI.acce_id%type
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
                                         p_acce_id => p_acce_id
                                        )
           , 'canHandle on accessi_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   ACCESSI
      where
      acce_id = p_acce_id
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on accessi_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- accessi_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_acce_id  in ACCESSI.acce_id%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_acce_id => p_acce_id
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- accessi_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_acce_id  in ACCESSI.acce_id%type default null
, p_session_id  in ACCESSI.session_id%type default null
, p_data  in ACCESSI.data%type default null
, p_log  in ACCESSI.log%type default null
, p_utente  in ACCESSI.utente%type default null
, p_istanza  in ACCESSI.istanza%type default null
, p_modulo  in ACCESSI.modulo%type default null
, p_db_user  in ACCESSI.db_user%type default null
, p_terminale  in ACCESSI.terminale%type default null
, p_note  in ACCESSI.note%type default null
, p_id_credenziale  in ACCESSI.id_credenziale%type default null
, p_tipo_autenticazione  in ACCESSI.tipo_autenticazione%type default null
, p_stato  in ACCESSI.stato%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_session_id is not null or /*default value*/ 'default' is not null
           , 'p_session_id on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data is not null or /*default value*/ 'default' is not null
           , 'p_data on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_log is not null or /*default value*/ 'default' is not null
           , 'p_log on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente is not null or /*default value*/ 'default' is not null
           , 'p_utente on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_istanza is not null or /*default value*/ 'default' is not null
           , 'p_istanza on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ 'default' is not null
           , 'p_modulo on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_db_user is not null or /*default value*/ 'default' is not null
           , 'p_db_user on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_terminale is not null or /*default value*/ 'default' is not null
           , 'p_terminale on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_id_credenziale is not null or /*default value*/ 'default' is not null
           , 'p_id_credenziale on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_tipo_autenticazione is not null or /*default value*/ 'default' is not null
           , 'p_tipo_autenticazione on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_stato is not null or /*default value*/ 'default' is not null
           , 'p_stato on accessi_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_acce_id is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_acce_id => p_acce_id
                           )
           , 'not existsId on accessi_tpk.ins'
           );
   insert into ACCESSI
   (
     acce_id
   , session_id
   , data
   , log
   , utente
   , istanza
   , modulo
   , db_user
   , terminale
   , note
   , id_credenziale
   , tipo_autenticazione
   , stato
   )
   values
   (
     p_acce_id
   , p_session_id
   , p_data
   , p_log
   , p_utente
   , p_istanza
   , p_modulo
   , p_db_user
   , p_terminale
   , p_note
   , p_id_credenziale
   , p_tipo_autenticazione
   , p_stato
   );
end ins; -- accessi_tpk.ins
--------------------------------------------------------------------------------
function ins  /*+ SOA  */
(
  p_acce_id  in ACCESSI.acce_id%type default null
, p_session_id  in ACCESSI.session_id%type default null
, p_data  in ACCESSI.data%type default null
, p_log  in ACCESSI.log%type default null
, p_utente  in ACCESSI.utente%type default null
, p_istanza  in ACCESSI.istanza%type default null
, p_modulo  in ACCESSI.modulo%type default null
, p_db_user  in ACCESSI.db_user%type default null
, p_terminale  in ACCESSI.terminale%type default null
, p_note  in ACCESSI.note%type default null
, p_id_credenziale  in ACCESSI.id_credenziale%type default null
, p_tipo_autenticazione  in ACCESSI.tipo_autenticazione%type default null
, p_stato  in ACCESSI.stato%type default null
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
   DbC.PRE ( not DbC.PreOn or p_session_id is not null or /*default value*/ 'default' is not null
           , 'p_session_id on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data is not null or /*default value*/ 'default' is not null
           , 'p_data on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_log is not null or /*default value*/ 'default' is not null
           , 'p_log on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente is not null or /*default value*/ 'default' is not null
           , 'p_utente on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_istanza is not null or /*default value*/ 'default' is not null
           , 'p_istanza on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ 'default' is not null
           , 'p_modulo on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_db_user is not null or /*default value*/ 'default' is not null
           , 'p_db_user on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_terminale is not null or /*default value*/ 'default' is not null
           , 'p_terminale on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_id_credenziale is not null or /*default value*/ 'default' is not null
           , 'p_id_credenziale on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_tipo_autenticazione is not null or /*default value*/ 'default' is not null
           , 'p_tipo_autenticazione on accessi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_stato is not null or /*default value*/ 'default' is not null
           , 'p_stato on accessi_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_acce_id is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_acce_id => p_acce_id
                           )
           , 'not existsId on accessi_tpk.ins'
           );
   begin
      insert into ACCESSI
      (
        acce_id
      , session_id
      , data
      , log
      , utente
      , istanza
      , modulo
      , db_user
      , terminale
      , note
      , id_credenziale
      , tipo_autenticazione
      , stato
      )
      values
      (
        p_acce_id
      , p_session_id
      , p_data
      , p_log
      , p_utente
      , p_istanza
      , p_modulo
      , p_db_user
      , p_terminale
      , p_note
      , p_id_credenziale
      , p_tipo_autenticazione
      , p_stato
      ) returning acce_id
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
end ins; -- accessi_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_acce_id  in ACCESSI.acce_id%type
, p_OLD_acce_id  in ACCESSI.acce_id%type default null
, p_NEW_session_id  in ACCESSI.session_id%type default null
, p_OLD_session_id  in ACCESSI.session_id%type default null
, p_NEW_data  in ACCESSI.data%type default null
, p_OLD_data  in ACCESSI.data%type default null
, p_NEW_log  in ACCESSI.log%type default null
, p_OLD_log  in ACCESSI.log%type default null
, p_NEW_utente  in ACCESSI.utente%type default null
, p_OLD_utente  in ACCESSI.utente%type default null
, p_NEW_istanza  in ACCESSI.istanza%type default null
, p_OLD_istanza  in ACCESSI.istanza%type default null
, p_NEW_modulo  in ACCESSI.modulo%type default null
, p_OLD_modulo  in ACCESSI.modulo%type default null
, p_NEW_db_user  in ACCESSI.db_user%type default null
, p_OLD_db_user  in ACCESSI.db_user%type default null
, p_NEW_terminale  in ACCESSI.terminale%type default null
, p_OLD_terminale  in ACCESSI.terminale%type default null
, p_NEW_note  in ACCESSI.note%type default null
, p_OLD_note  in ACCESSI.note%type default null
, p_NEW_id_credenziale  in ACCESSI.id_credenziale%type default null
, p_OLD_id_credenziale  in ACCESSI.id_credenziale%type default null
, p_NEW_tipo_autenticazione  in ACCESSI.tipo_autenticazione%type default null
, p_OLD_tipo_autenticazione  in ACCESSI.tipo_autenticazione%type default null
, p_NEW_stato  in ACCESSI.stato%type default null
, p_OLD_stato  in ACCESSI.stato%type default null
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
p_OLD_session_id is not null or
p_OLD_data is not null or
p_OLD_log is not null or
p_OLD_utente is not null or
p_OLD_istanza is not null or
p_OLD_modulo is not null or
p_OLD_db_user is not null or
p_OLD_terminale is not null or
p_OLD_note is not null or
p_OLD_id_credenziale is not null or
p_OLD_tipo_autenticazione is not null or
p_OLD_stato is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on accessi_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_acce_id, p_NEW_acce_id )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_acce_id => d_key.acce_id
                                       )
           , 'existsId on accessi_tpk.upd'
           );
   update ACCESSI
   set
       acce_id = decode( p_check_OLD, 0,p_NEW_acce_id, decode(p_NEW_acce_id, p_OLD_acce_id,acce_id,p_NEW_acce_id))
     , session_id = decode( p_check_OLD, 0,p_NEW_session_id, decode(p_NEW_session_id, p_OLD_session_id,session_id,p_NEW_session_id))
     , data = decode( p_check_OLD, 0,p_NEW_data, decode(p_NEW_data, p_OLD_data,data,p_NEW_data))
     , log = decode( p_check_OLD, 0,p_NEW_log, decode(p_NEW_log, p_OLD_log,log,p_NEW_log))
     , utente = decode( p_check_OLD, 0,p_NEW_utente, decode(p_NEW_utente, p_OLD_utente,utente,p_NEW_utente))
     , istanza = decode( p_check_OLD, 0,p_NEW_istanza, decode(p_NEW_istanza, p_OLD_istanza,istanza,p_NEW_istanza))
     , modulo = decode( p_check_OLD, 0,p_NEW_modulo, decode(p_NEW_modulo, p_OLD_modulo,modulo,p_NEW_modulo))
     , db_user = decode( p_check_OLD, 0,p_NEW_db_user, decode(p_NEW_db_user, p_OLD_db_user,db_user,p_NEW_db_user))
     , terminale = decode( p_check_OLD, 0,p_NEW_terminale, decode(p_NEW_terminale, p_OLD_terminale,terminale,p_NEW_terminale))
     , note = decode( p_check_OLD, 0,p_NEW_note, decode(p_NEW_note, p_OLD_note,note,p_NEW_note))
     , id_credenziale = decode( p_check_OLD, 0,p_NEW_id_credenziale, decode(p_NEW_id_credenziale, p_OLD_id_credenziale,id_credenziale,p_NEW_id_credenziale))
     , tipo_autenticazione = decode( p_check_OLD, 0,p_NEW_tipo_autenticazione, decode(p_NEW_tipo_autenticazione, p_OLD_tipo_autenticazione,tipo_autenticazione,p_NEW_tipo_autenticazione))
     , stato = decode( p_check_OLD, 0,p_NEW_stato, decode(p_NEW_stato, p_OLD_stato,stato,p_NEW_stato))
   where
     acce_id = d_key.acce_id
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( session_id = p_OLD_session_id or ( p_OLD_session_id is null and ( p_check_OLD is null or session_id is null ) ) )
           and ( data = p_OLD_data or ( p_OLD_data is null and ( p_check_OLD is null or data is null ) ) )
           and ( log = p_OLD_log or ( p_OLD_log is null and ( p_check_OLD is null or log is null ) ) )
           and ( utente = p_OLD_utente or ( p_OLD_utente is null and ( p_check_OLD is null or utente is null ) ) )
           and ( istanza = p_OLD_istanza or ( p_OLD_istanza is null and ( p_check_OLD is null or istanza is null ) ) )
           and ( modulo = p_OLD_modulo or ( p_OLD_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( db_user = p_OLD_db_user or ( p_OLD_db_user is null and ( p_check_OLD is null or db_user is null ) ) )
           and ( terminale = p_OLD_terminale or ( p_OLD_terminale is null and ( p_check_OLD is null or terminale is null ) ) )
           and ( note = p_OLD_note or ( p_OLD_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( id_credenziale = p_OLD_id_credenziale or ( p_OLD_id_credenziale is null and ( p_check_OLD is null or id_credenziale is null ) ) )
           and ( tipo_autenticazione = p_OLD_tipo_autenticazione or ( p_OLD_tipo_autenticazione is null and ( p_check_OLD is null or tipo_autenticazione is null ) ) )
           and ( stato = p_OLD_stato or ( p_OLD_stato is null and ( p_check_OLD is null or stato is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on accessi_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- accessi_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_acce_id  in ACCESSI.acce_id%type
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
                                        p_acce_id => p_acce_id
                                       )
           , 'existsId on accessi_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on accessi_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on accessi_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on accessi_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update ACCESSI '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( acce_id ', p_acce_id, ' )', 0, null ), ' and ( acce_id is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- accessi_tpk.upd_column
--------------------------------------------------------------------------------
procedure upd_column
(
p_acce_id  in ACCESSI.acce_id%type
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
p_acce_id => p_acce_id
              , p_column => p_column
              , p_value => 'to_date( ''' || d_data || ''', ''' || AFC.date_format || ''' )'
              , p_literal_value => 0
              );
end upd_column; -- accessi_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_acce_id  in ACCESSI.acce_id%type
, p_session_id  in ACCESSI.session_id%type default null
, p_data  in ACCESSI.data%type default null
, p_log  in ACCESSI.log%type default null
, p_utente  in ACCESSI.utente%type default null
, p_istanza  in ACCESSI.istanza%type default null
, p_modulo  in ACCESSI.modulo%type default null
, p_db_user  in ACCESSI.db_user%type default null
, p_terminale  in ACCESSI.terminale%type default null
, p_note  in ACCESSI.note%type default null
, p_id_credenziale  in ACCESSI.id_credenziale%type default null
, p_tipo_autenticazione  in ACCESSI.tipo_autenticazione%type default null
, p_stato  in ACCESSI.stato%type default null
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
p_session_id is not null or
p_data is not null or
p_log is not null or
p_utente is not null or
p_istanza is not null or
p_modulo is not null or
p_db_user is not null or
p_terminale is not null or
p_note is not null or
p_id_credenziale is not null or
p_tipo_autenticazione is not null or
p_stato is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on accessi_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_acce_id => p_acce_id
                                       )
           , 'existsId on accessi_tpk.del'
           );
   delete from ACCESSI
   where
     acce_id = p_acce_id
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( session_id = p_session_id or ( p_session_id is null and ( p_check_OLD is null or session_id is null ) ) )
           and ( data = p_data or ( p_data is null and ( p_check_OLD is null or data is null ) ) )
           and ( log = p_log or ( p_log is null and ( p_check_OLD is null or log is null ) ) )
           and ( utente = p_utente or ( p_utente is null and ( p_check_OLD is null or utente is null ) ) )
           and ( istanza = p_istanza or ( p_istanza is null and ( p_check_OLD is null or istanza is null ) ) )
           and ( modulo = p_modulo or ( p_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( db_user = p_db_user or ( p_db_user is null and ( p_check_OLD is null or db_user is null ) ) )
           and ( terminale = p_terminale or ( p_terminale is null and ( p_check_OLD is null or terminale is null ) ) )
           and ( note = p_note or ( p_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( id_credenziale = p_id_credenziale or ( p_id_credenziale is null and ( p_check_OLD is null or id_credenziale is null ) ) )
           and ( tipo_autenticazione = p_tipo_autenticazione or ( p_tipo_autenticazione is null and ( p_check_OLD is null or tipo_autenticazione is null ) ) )
           and ( stato = p_stato or ( p_stato is null and ( p_check_OLD is null or stato is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on accessi_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_acce_id => p_acce_id
                                             )
            , 'existsId on accessi_tpk.del'
            );
end del; -- accessi_tpk.del
--------------------------------------------------------------------------------
function get_session_id
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.session_id%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_session_id
 DESCRIZIONE: Getter per attributo session_id di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.session_id%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.session_id%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_session_id'
           );
   select session_id
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_session_id'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'session_id')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_session_id'
                    );
   end if;
   return  d_result;
end get_session_id; -- accessi_tpk.get_session_id
--------------------------------------------------------------------------------
function get_data
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.data%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data
 DESCRIZIONE: Getter per attributo data di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.data%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.data%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_data'
           );
   select data
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_data'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_data'
                    );
   end if;
   return  d_result;
end get_data; -- accessi_tpk.get_data
--------------------------------------------------------------------------------
function get_log
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.log%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_log
 DESCRIZIONE: Getter per attributo log di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.log%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.log%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_log'
           );
   select log
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_log'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'log')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_log'
                    );
   end if;
   return  d_result;
end get_log; -- accessi_tpk.get_log
--------------------------------------------------------------------------------
function get_utente
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.utente%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_utente
 DESCRIZIONE: Getter per attributo utente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.utente%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.utente%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_utente'
           );
   select utente
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_utente'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'utente')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_utente'
                    );
   end if;
   return  d_result;
end get_utente; -- accessi_tpk.get_utente
--------------------------------------------------------------------------------
function get_istanza
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.istanza%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_istanza
 DESCRIZIONE: Getter per attributo istanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.istanza%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.istanza%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_istanza'
           );
   select istanza
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_istanza'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'istanza')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_istanza'
                    );
   end if;
   return  d_result;
end get_istanza; -- accessi_tpk.get_istanza
--------------------------------------------------------------------------------
function get_modulo
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.modulo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_modulo
 DESCRIZIONE: Getter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.modulo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.modulo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_modulo'
           );
   select modulo
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_modulo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'modulo')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_modulo'
                    );
   end if;
   return  d_result;
end get_modulo; -- accessi_tpk.get_modulo
--------------------------------------------------------------------------------
function get_db_user
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.db_user%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_db_user
 DESCRIZIONE: Getter per attributo db_user di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.db_user%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.db_user%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_db_user'
           );
   select db_user
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_db_user'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'db_user')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_db_user'
                    );
   end if;
   return  d_result;
end get_db_user; -- accessi_tpk.get_db_user
--------------------------------------------------------------------------------
function get_terminale
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.terminale%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_terminale
 DESCRIZIONE: Getter per attributo terminale di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.terminale%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.terminale%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_terminale'
           );
   select terminale
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_terminale'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'terminale')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_terminale'
                    );
   end if;
   return  d_result;
end get_terminale; -- accessi_tpk.get_terminale
--------------------------------------------------------------------------------
function get_note
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.note%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_note
 DESCRIZIONE: Getter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.note%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.note%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_note'
           );
   select note
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_note'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'note')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_note'
                    );
   end if;
   return  d_result;
end get_note; -- accessi_tpk.get_note
--------------------------------------------------------------------------------
function get_id_credenziale
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.id_credenziale%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_id_credenziale
 DESCRIZIONE: Getter per attributo id_credenziale di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.id_credenziale%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.id_credenziale%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_id_credenziale'
           );
   select id_credenziale
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_id_credenziale'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'id_credenziale')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_id_credenziale'
                    );
   end if;
   return  d_result;
end get_id_credenziale; -- accessi_tpk.get_id_credenziale
--------------------------------------------------------------------------------
function get_tipo_autenticazione
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.tipo_autenticazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_tipo_autenticazione
 DESCRIZIONE: Getter per attributo tipo_autenticazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.tipo_autenticazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.tipo_autenticazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_tipo_autenticazione'
           );
   select tipo_autenticazione
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_tipo_autenticazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'tipo_autenticazione')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_tipo_autenticazione'
                    );
   end if;
   return  d_result;
end get_tipo_autenticazione; -- accessi_tpk.get_tipo_autenticazione
--------------------------------------------------------------------------------
function get_stato
(
  p_acce_id  in ACCESSI.acce_id%type
) return ACCESSI.stato%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_stato
 DESCRIZIONE: Getter per attributo stato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ACCESSI.stato%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ACCESSI.stato%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.get_stato'
           );
   select stato
   into   d_result
   from   ACCESSI
   where
   acce_id = p_acce_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on accessi_tpk.get_stato'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'stato')
                    , ' AFC_DDL.IsNullable on accessi_tpk.get_stato'
                    );
   end if;
   return  d_result;
end get_stato; -- accessi_tpk.get_stato
--------------------------------------------------------------------------------
procedure set_acce_id
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.acce_id%type default null
) is
/******************************************************************************
 NOME:        set_acce_id
 DESCRIZIONE: Setter per attributo acce_id di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_acce_id'
           );
   update ACCESSI
   set acce_id = p_value
   where
   acce_id = p_acce_id
   ;
end set_acce_id; -- accessi_tpk.set_acce_id
--------------------------------------------------------------------------------
procedure set_session_id
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.session_id%type default null
) is
/******************************************************************************
 NOME:        set_session_id
 DESCRIZIONE: Setter per attributo session_id di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_session_id'
           );
   update ACCESSI
   set session_id = p_value
   where
   acce_id = p_acce_id
   ;
end set_session_id; -- accessi_tpk.set_session_id
--------------------------------------------------------------------------------
procedure set_data
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.data%type default null
) is
/******************************************************************************
 NOME:        set_data
 DESCRIZIONE: Setter per attributo data di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_data'
           );
   update ACCESSI
   set data = p_value
   where
   acce_id = p_acce_id
   ;
end set_data; -- accessi_tpk.set_data
--------------------------------------------------------------------------------
procedure set_log
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.log%type default null
) is
/******************************************************************************
 NOME:        set_log
 DESCRIZIONE: Setter per attributo log di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_log'
           );
   update ACCESSI
   set log = p_value
   where
   acce_id = p_acce_id
   ;
end set_log; -- accessi_tpk.set_log
--------------------------------------------------------------------------------
procedure set_utente
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.utente%type default null
) is
/******************************************************************************
 NOME:        set_utente
 DESCRIZIONE: Setter per attributo utente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_utente'
           );
   update ACCESSI
   set utente = p_value
   where
   acce_id = p_acce_id
   ;
end set_utente; -- accessi_tpk.set_utente
--------------------------------------------------------------------------------
procedure set_istanza
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.istanza%type default null
) is
/******************************************************************************
 NOME:        set_istanza
 DESCRIZIONE: Setter per attributo istanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_istanza'
           );
   update ACCESSI
   set istanza = p_value
   where
   acce_id = p_acce_id
   ;
end set_istanza; -- accessi_tpk.set_istanza
--------------------------------------------------------------------------------
procedure set_modulo
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.modulo%type default null
) is
/******************************************************************************
 NOME:        set_modulo
 DESCRIZIONE: Setter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_modulo'
           );
   update ACCESSI
   set modulo = p_value
   where
   acce_id = p_acce_id
   ;
end set_modulo; -- accessi_tpk.set_modulo
--------------------------------------------------------------------------------
procedure set_db_user
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.db_user%type default null
) is
/******************************************************************************
 NOME:        set_db_user
 DESCRIZIONE: Setter per attributo db_user di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_db_user'
           );
   update ACCESSI
   set db_user = p_value
   where
   acce_id = p_acce_id
   ;
end set_db_user; -- accessi_tpk.set_db_user
--------------------------------------------------------------------------------
procedure set_terminale
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.terminale%type default null
) is
/******************************************************************************
 NOME:        set_terminale
 DESCRIZIONE: Setter per attributo terminale di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_terminale'
           );
   update ACCESSI
   set terminale = p_value
   where
   acce_id = p_acce_id
   ;
end set_terminale; -- accessi_tpk.set_terminale
--------------------------------------------------------------------------------
procedure set_note
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.note%type default null
) is
/******************************************************************************
 NOME:        set_note
 DESCRIZIONE: Setter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_note'
           );
   update ACCESSI
   set note = p_value
   where
   acce_id = p_acce_id
   ;
end set_note; -- accessi_tpk.set_note
--------------------------------------------------------------------------------
procedure set_id_credenziale
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.id_credenziale%type default null
) is
/******************************************************************************
 NOME:        set_id_credenziale
 DESCRIZIONE: Setter per attributo id_credenziale di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_id_credenziale'
           );
   update ACCESSI
   set id_credenziale = p_value
   where
   acce_id = p_acce_id
   ;
end set_id_credenziale; -- accessi_tpk.set_id_credenziale
--------------------------------------------------------------------------------
procedure set_tipo_autenticazione
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.tipo_autenticazione%type default null
) is
/******************************************************************************
 NOME:        set_tipo_autenticazione
 DESCRIZIONE: Setter per attributo tipo_autenticazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_tipo_autenticazione'
           );
   update ACCESSI
   set tipo_autenticazione = p_value
   where
   acce_id = p_acce_id
   ;
end set_tipo_autenticazione; -- accessi_tpk.set_tipo_autenticazione
--------------------------------------------------------------------------------
procedure set_stato
(
  p_acce_id  in ACCESSI.acce_id%type
, p_value  in ACCESSI.stato%type default null
) is
/******************************************************************************
 NOME:        set_stato
 DESCRIZIONE: Setter per attributo stato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_acce_id => p_acce_id
                                        )
           , 'existsId on accessi_tpk.set_stato'
           );
   update ACCESSI
   set stato = p_value
   where
   acce_id = p_acce_id
   ;
end set_stato; -- accessi_tpk.set_stato
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_acce_id  in varchar2 default null
, p_session_id  in varchar2 default null
, p_data  in varchar2 default null
, p_log  in varchar2 default null
, p_utente  in varchar2 default null
, p_istanza  in varchar2 default null
, p_modulo  in varchar2 default null
, p_db_user  in varchar2 default null
, p_terminale  in varchar2 default null
, p_note  in varchar2 default null
, p_id_credenziale  in varchar2 default null
, p_tipo_autenticazione  in varchar2 default null
, p_stato  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( acce_id ', p_acce_id, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( session_id ', p_session_id , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data ', p_data , ' )', p_QBE, AFC.date_format )
               || AFC.get_field_condition( ' and ( log ', p_log , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( utente ', p_utente , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( istanza ', p_istanza , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( modulo ', p_modulo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( db_user ', p_db_user , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( terminale ', p_terminale , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( note ', p_note , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( id_credenziale ', p_id_credenziale , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( tipo_autenticazione ', p_tipo_autenticazione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( stato ', p_stato , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- accessi_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_acce_id  in varchar2 default null
, p_session_id  in varchar2 default null
, p_data  in varchar2 default null
, p_log  in varchar2 default null
, p_utente  in varchar2 default null
, p_istanza  in varchar2 default null
, p_modulo  in varchar2 default null
, p_db_user  in varchar2 default null
, p_terminale  in varchar2 default null
, p_note  in varchar2 default null
, p_id_credenziale  in varchar2 default null
, p_tipo_autenticazione  in varchar2 default null
, p_stato  in varchar2 default null
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
   d_statement := ' select ACCESSI.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from ACCESSI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_acce_id => p_acce_id
                                 , p_session_id => p_session_id
                                 , p_data => p_data
                                 , p_log => p_log
                                 , p_utente => p_utente
                                 , p_istanza => p_istanza
                                 , p_modulo => p_modulo
                                 , p_db_user => p_db_user
                                 , p_terminale => p_terminale
                                 , p_note => p_note
                                 , p_id_credenziale => p_id_credenziale
                                 , p_tipo_autenticazione => p_tipo_autenticazione
                                 , p_stato => p_stato
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- accessi_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_acce_id  in varchar2 default null
, p_session_id  in varchar2 default null
, p_data  in varchar2 default null
, p_log  in varchar2 default null
, p_utente  in varchar2 default null
, p_istanza  in varchar2 default null
, p_modulo  in varchar2 default null
, p_db_user  in varchar2 default null
, p_terminale  in varchar2 default null
, p_note  in varchar2 default null
, p_id_credenziale  in varchar2 default null
, p_tipo_autenticazione  in varchar2 default null
, p_stato  in varchar2 default null
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
   d_statement := ' select count( * ) from ACCESSI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_acce_id => p_acce_id
                                 , p_session_id => p_session_id
                                 , p_data => p_data
                                 , p_log => p_log
                                 , p_utente => p_utente
                                 , p_istanza => p_istanza
                                 , p_modulo => p_modulo
                                 , p_db_user => p_db_user
                                 , p_terminale => p_terminale
                                 , p_note => p_note
                                 , p_id_credenziale => p_id_credenziale
                                 , p_tipo_autenticazione => p_tipo_autenticazione
                                 , p_stato => p_stato
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- accessi_tpk.count_rows
--------------------------------------------------------------------------------
end accessi_tpk;
/

