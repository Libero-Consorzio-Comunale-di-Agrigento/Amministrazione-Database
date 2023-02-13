--liquibase formatted sql

--changeset mturra:201901301231_170 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY richieste_abilitazione_tpk is
/******************************************************************************
 NOME:        richieste_abilitazione_tpk
 DESCRIZIONE: Gestione tabella RICHIESTE_ABILITAZIONE.
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
end versione; -- richieste_abilitazione_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.id_richiesta := p_id_richiesta;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_id_richiesta => d_result.id_richiesta
                                        )
           , 'canHandle on richieste_abilitazione_tpk.PK'
           );
   return  d_result;
end PK; -- richieste_abilitazione_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
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
          p_id_richiesta is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on richieste_abilitazione_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- richieste_abilitazione_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_id_richiesta => p_id_richiesta
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- richieste_abilitazione_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
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
                                         p_id_richiesta => p_id_richiesta
                                        )
           , 'canHandle on richieste_abilitazione_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   RICHIESTE_ABILITAZIONE
      where
      id_richiesta = p_id_richiesta
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on richieste_abilitazione_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- richieste_abilitazione_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_id_richiesta => p_id_richiesta
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- richieste_abilitazione_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type default null
, p_utente  in RICHIESTE_ABILITAZIONE.utente%type
, p_modulo  in RICHIESTE_ABILITAZIONE.modulo%type
, p_istanza  in RICHIESTE_ABILITAZIONE.istanza%type
, p_stato  in RICHIESTE_ABILITAZIONE.stato%type default 'F'
, p_data  in RICHIESTE_ABILITAZIONE.data%type default null
, p_note  in RICHIESTE_ABILITAZIONE.note%type default null
, p_tipo_notifica  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default null
, p_indirizzo_notifica  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default null
, p_note_notifica  in RICHIESTE_ABILITAZIONE.note_notifica%type default null
, p_notificata  in RICHIESTE_ABILITAZIONE.notificata%type default 'N'
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_utente is not null or /*default value*/ '' is not null
           , 'p_utente on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ '' is not null
           , 'p_modulo on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_istanza is not null or /*default value*/ '' is not null
           , 'p_istanza on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_stato is not null or /*default value*/ 'default' is not null
           , 'p_stato on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data is not null or /*default value*/ 'default' is not null
           , 'p_data on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_tipo_notifica is not null or /*default value*/ 'default' is not null
           , 'p_tipo_notifica on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_indirizzo_notifica is not null or /*default value*/ 'default' is not null
           , 'p_indirizzo_notifica on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note_notifica is not null or /*default value*/ 'default' is not null
           , 'p_note_notifica on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_notificata is not null or /*default value*/ 'default' is not null
           , 'p_notificata on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id_richiesta is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id_richiesta => p_id_richiesta
                           )
           , 'not existsId on richieste_abilitazione_tpk.ins'
           );
   insert into RICHIESTE_ABILITAZIONE
   (
     id_richiesta
   , utente
   , modulo
   , istanza
   , stato
   , data
   , note
   , tipo_notifica
   , indirizzo_notifica
   , note_notifica
   , notificata
   )
   values
   (
     p_id_richiesta
   , p_utente
   , p_modulo
   , p_istanza
   , p_stato
   , p_data
   , p_note
   , p_tipo_notifica
   , p_indirizzo_notifica
   , p_note_notifica
   , p_notificata
   );
end ins; -- richieste_abilitazione_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type default null
, p_utente  in RICHIESTE_ABILITAZIONE.utente%type
, p_modulo  in RICHIESTE_ABILITAZIONE.modulo%type
, p_istanza  in RICHIESTE_ABILITAZIONE.istanza%type
, p_stato  in RICHIESTE_ABILITAZIONE.stato%type default 'F'
, p_data  in RICHIESTE_ABILITAZIONE.data%type default null
, p_note  in RICHIESTE_ABILITAZIONE.note%type default null
, p_tipo_notifica  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default null
, p_indirizzo_notifica  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default null
, p_note_notifica  in RICHIESTE_ABILITAZIONE.note_notifica%type default null
, p_notificata  in RICHIESTE_ABILITAZIONE.notificata%type default 'N'
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
   DbC.PRE ( not DbC.PreOn or p_utente is not null or /*default value*/ '' is not null
           , 'p_utente on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ '' is not null
           , 'p_modulo on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_istanza is not null or /*default value*/ '' is not null
           , 'p_istanza on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_stato is not null or /*default value*/ 'default' is not null
           , 'p_stato on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data is not null or /*default value*/ 'default' is not null
           , 'p_data on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_tipo_notifica is not null or /*default value*/ 'default' is not null
           , 'p_tipo_notifica on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_indirizzo_notifica is not null or /*default value*/ 'default' is not null
           , 'p_indirizzo_notifica on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note_notifica is not null or /*default value*/ 'default' is not null
           , 'p_note_notifica on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_notificata is not null or /*default value*/ 'default' is not null
           , 'p_notificata on richieste_abilitazione_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id_richiesta is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id_richiesta => p_id_richiesta
                           )
           , 'not existsId on richieste_abilitazione_tpk.ins'
           );
   begin
      insert into RICHIESTE_ABILITAZIONE
      (
        id_richiesta
      , utente
      , modulo
      , istanza
      , stato
      , data
      , note
      , tipo_notifica
      , indirizzo_notifica
      , note_notifica
      , notificata
      )
      values
      (
        p_id_richiesta
      , p_utente
      , p_modulo
      , p_istanza
      , p_stato
      , p_data
      , p_note
      , p_tipo_notifica
      , p_indirizzo_notifica
      , p_note_notifica
      , p_notificata
      ) returning id_richiesta
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
end ins; -- richieste_abilitazione_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_OLD_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type default null
, p_NEW_utente  in RICHIESTE_ABILITAZIONE.utente%type default afc.default_null('RICHIESTE_ABILITAZIONE.utente')
, p_OLD_utente  in RICHIESTE_ABILITAZIONE.utente%type default null
, p_NEW_modulo  in RICHIESTE_ABILITAZIONE.modulo%type default afc.default_null('RICHIESTE_ABILITAZIONE.modulo')
, p_OLD_modulo  in RICHIESTE_ABILITAZIONE.modulo%type default null
, p_NEW_istanza  in RICHIESTE_ABILITAZIONE.istanza%type default afc.default_null('RICHIESTE_ABILITAZIONE.istanza')
, p_OLD_istanza  in RICHIESTE_ABILITAZIONE.istanza%type default null
, p_NEW_stato  in RICHIESTE_ABILITAZIONE.stato%type default afc.default_null('RICHIESTE_ABILITAZIONE.stato')
, p_OLD_stato  in RICHIESTE_ABILITAZIONE.stato%type default null
, p_NEW_data  in RICHIESTE_ABILITAZIONE.data%type default afc.default_null('RICHIESTE_ABILITAZIONE.data')
, p_OLD_data  in RICHIESTE_ABILITAZIONE.data%type default null
, p_NEW_note  in RICHIESTE_ABILITAZIONE.note%type default afc.default_null('RICHIESTE_ABILITAZIONE.note')
, p_OLD_note  in RICHIESTE_ABILITAZIONE.note%type default null
, p_NEW_tipo_notifica  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default afc.default_null('RICHIESTE_ABILITAZIONE.tipo_notifica')
, p_OLD_tipo_notifica  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default null
, p_NEW_indirizzo_notifica  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default afc.default_null('RICHIESTE_ABILITAZIONE.indirizzo_notifica')
, p_OLD_indirizzo_notifica  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default null
, p_NEW_note_notifica  in RICHIESTE_ABILITAZIONE.note_notifica%type default afc.default_null('RICHIESTE_ABILITAZIONE.note_notifica')
, p_OLD_note_notifica  in RICHIESTE_ABILITAZIONE.note_notifica%type default null
, p_NEW_notificata  in RICHIESTE_ABILITAZIONE.notificata%type default afc.default_null('RICHIESTE_ABILITAZIONE.notificata')
, p_OLD_notificata  in RICHIESTE_ABILITAZIONE.notificata%type default null
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
p_OLD_utente is not null
 or p_OLD_modulo is not null
 or p_OLD_istanza is not null
 or p_OLD_stato is not null
 or p_OLD_data is not null
 or p_OLD_note is not null
 or p_OLD_tipo_notifica is not null
 or p_OLD_indirizzo_notifica is not null
 or p_OLD_note_notifica is not null
 or p_OLD_notificata is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on richieste_abilitazione_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_id_richiesta, p_NEW_id_richiesta )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id_richiesta => d_key.id_richiesta
                                       )
           , 'existsId on richieste_abilitazione_tpk.upd'
           );
   update RICHIESTE_ABILITAZIONE
   set
       id_richiesta = nvl( p_NEW_id_richiesta, decode( afc.is_default_null( 'RICHIESTE_ABILITAZIONE.id_richiesta'), 1, id_richiesta, null) )
     , utente = nvl( p_NEW_utente, decode( afc.is_default_null( 'RICHIESTE_ABILITAZIONE.utente'), 1, utente, null) )
     , modulo = nvl( p_NEW_modulo, decode( afc.is_default_null( 'RICHIESTE_ABILITAZIONE.modulo'), 1, modulo, null) )
     , istanza = nvl( p_NEW_istanza, decode( afc.is_default_null( 'RICHIESTE_ABILITAZIONE.istanza'), 1, istanza, null) )
     , stato = nvl( p_NEW_stato, decode( afc.is_default_null( 'RICHIESTE_ABILITAZIONE.stato'), 1, stato, null) )
     , data = nvl( p_NEW_data, decode( afc.is_default_null( 'RICHIESTE_ABILITAZIONE.data'), 1, data, null) )
     , note = nvl( p_NEW_note, decode( afc.is_default_null( 'RICHIESTE_ABILITAZIONE.note'), 1, note, null) )
     , tipo_notifica = nvl( p_NEW_tipo_notifica, decode( afc.is_default_null( 'RICHIESTE_ABILITAZIONE.tipo_notifica'), 1, tipo_notifica, null) )
     , indirizzo_notifica = nvl( p_NEW_indirizzo_notifica, decode( afc.is_default_null( 'RICHIESTE_ABILITAZIONE.indirizzo_notifica'), 1, indirizzo_notifica, null) )
     , note_notifica = nvl( p_NEW_note_notifica, decode( afc.is_default_null( 'RICHIESTE_ABILITAZIONE.note_notifica'), 1, note_notifica, null) )
     , notificata = nvl( p_NEW_notificata, decode( afc.is_default_null( 'RICHIESTE_ABILITAZIONE.notificata'), 1, notificata, null) )
   where
     id_richiesta = d_key.id_richiesta
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( utente = p_OLD_utente or ( p_OLD_utente is null and ( p_check_OLD is null or utente is null ) ) )
           and ( modulo = p_OLD_modulo or ( p_OLD_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( istanza = p_OLD_istanza or ( p_OLD_istanza is null and ( p_check_OLD is null or istanza is null ) ) )
           and ( stato = p_OLD_stato or ( p_OLD_stato is null and ( p_check_OLD is null or stato is null ) ) )
           and ( data = p_OLD_data or ( p_OLD_data is null and ( p_check_OLD is null or data is null ) ) )
           and ( note = p_OLD_note or ( p_OLD_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( tipo_notifica = p_OLD_tipo_notifica or ( p_OLD_tipo_notifica is null and ( p_check_OLD is null or tipo_notifica is null ) ) )
           and ( indirizzo_notifica = p_OLD_indirizzo_notifica or ( p_OLD_indirizzo_notifica is null and ( p_check_OLD is null or indirizzo_notifica is null ) ) )
           and ( note_notifica = p_OLD_note_notifica or ( p_OLD_note_notifica is null and ( p_check_OLD is null or note_notifica is null ) ) )
           and ( notificata = p_OLD_notificata or ( p_OLD_notificata is null and ( p_check_OLD is null or notificata is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on richieste_abilitazione_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- richieste_abilitazione_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
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
                                        p_id_richiesta => p_id_richiesta
                                       )
           , 'existsId on richieste_abilitazione_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on richieste_abilitazione_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on richieste_abilitazione_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on richieste_abilitazione_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update RICHIESTE_ABILITAZIONE '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( id_richiesta ', p_id_richiesta, ' )', 0, null ), ' and ( id_richiesta is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- richieste_abilitazione_tpk.upd_column
--------------------------------------------------------------------------------
procedure upd_column
(
p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
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
p_id_richiesta => p_id_richiesta
              , p_column => p_column
              , p_value => 'to_date( ''' || d_data || ''', ''' || AFC.date_format || ''' )'
              , p_literal_value => 0
              );
end upd_column; -- richieste_abilitazione_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_utente  in RICHIESTE_ABILITAZIONE.utente%type default null
, p_modulo  in RICHIESTE_ABILITAZIONE.modulo%type default null
, p_istanza  in RICHIESTE_ABILITAZIONE.istanza%type default null
, p_stato  in RICHIESTE_ABILITAZIONE.stato%type default null
, p_data  in RICHIESTE_ABILITAZIONE.data%type default null
, p_note  in RICHIESTE_ABILITAZIONE.note%type default null
, p_tipo_notifica  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default null
, p_indirizzo_notifica  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default null
, p_note_notifica  in RICHIESTE_ABILITAZIONE.note_notifica%type default null
, p_notificata  in RICHIESTE_ABILITAZIONE.notificata%type default null
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
p_utente is not null
 or p_modulo is not null
 or p_istanza is not null
 or p_stato is not null
 or p_data is not null
 or p_note is not null
 or p_tipo_notifica is not null
 or p_indirizzo_notifica is not null
 or p_note_notifica is not null
 or p_notificata is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on richieste_abilitazione_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id_richiesta => p_id_richiesta
                                       )
           , 'existsId on richieste_abilitazione_tpk.del'
           );
   delete from RICHIESTE_ABILITAZIONE
   where
     id_richiesta = p_id_richiesta
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( utente = p_utente or ( p_utente is null and ( p_check_OLD is null or utente is null ) ) )
           and ( modulo = p_modulo or ( p_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( istanza = p_istanza or ( p_istanza is null and ( p_check_OLD is null or istanza is null ) ) )
           and ( stato = p_stato or ( p_stato is null and ( p_check_OLD is null or stato is null ) ) )
           and ( data = p_data or ( p_data is null and ( p_check_OLD is null or data is null ) ) )
           and ( note = p_note or ( p_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( tipo_notifica = p_tipo_notifica or ( p_tipo_notifica is null and ( p_check_OLD is null or tipo_notifica is null ) ) )
           and ( indirizzo_notifica = p_indirizzo_notifica or ( p_indirizzo_notifica is null and ( p_check_OLD is null or indirizzo_notifica is null ) ) )
           and ( note_notifica = p_note_notifica or ( p_note_notifica is null and ( p_check_OLD is null or note_notifica is null ) ) )
           and ( notificata = p_notificata or ( p_notificata is null and ( p_check_OLD is null or notificata is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on richieste_abilitazione_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_id_richiesta => p_id_richiesta
                                             )
            , 'existsId on richieste_abilitazione_tpk.del'
            );
end del; -- richieste_abilitazione_tpk.del
--------------------------------------------------------------------------------
function get_utente
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return RICHIESTE_ABILITAZIONE.utente%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_utente
 DESCRIZIONE: Getter per attributo utente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RICHIESTE_ABILITAZIONE.utente%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RICHIESTE_ABILITAZIONE.utente%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.get_utente'
           );
   select utente
   into   d_result
   from   RICHIESTE_ABILITAZIONE
   where
   id_richiesta = p_id_richiesta
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on richieste_abilitazione_tpk.get_utente'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'utente')
                    , ' AFC_DDL.IsNullable on richieste_abilitazione_tpk.get_utente'
                    );
   end if;
   return  d_result;
end get_utente; -- richieste_abilitazione_tpk.get_utente
--------------------------------------------------------------------------------
function get_modulo
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return RICHIESTE_ABILITAZIONE.modulo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_modulo
 DESCRIZIONE: Getter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RICHIESTE_ABILITAZIONE.modulo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RICHIESTE_ABILITAZIONE.modulo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.get_modulo'
           );
   select modulo
   into   d_result
   from   RICHIESTE_ABILITAZIONE
   where
   id_richiesta = p_id_richiesta
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on richieste_abilitazione_tpk.get_modulo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'modulo')
                    , ' AFC_DDL.IsNullable on richieste_abilitazione_tpk.get_modulo'
                    );
   end if;
   return  d_result;
end get_modulo; -- richieste_abilitazione_tpk.get_modulo
--------------------------------------------------------------------------------
function get_istanza
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return RICHIESTE_ABILITAZIONE.istanza%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_istanza
 DESCRIZIONE: Getter per attributo istanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RICHIESTE_ABILITAZIONE.istanza%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RICHIESTE_ABILITAZIONE.istanza%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.get_istanza'
           );
   select istanza
   into   d_result
   from   RICHIESTE_ABILITAZIONE
   where
   id_richiesta = p_id_richiesta
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on richieste_abilitazione_tpk.get_istanza'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'istanza')
                    , ' AFC_DDL.IsNullable on richieste_abilitazione_tpk.get_istanza'
                    );
   end if;
   return  d_result;
end get_istanza; -- richieste_abilitazione_tpk.get_istanza
--------------------------------------------------------------------------------
function get_stato
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return RICHIESTE_ABILITAZIONE.stato%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_stato
 DESCRIZIONE: Getter per attributo stato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RICHIESTE_ABILITAZIONE.stato%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RICHIESTE_ABILITAZIONE.stato%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.get_stato'
           );
   select stato
   into   d_result
   from   RICHIESTE_ABILITAZIONE
   where
   id_richiesta = p_id_richiesta
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on richieste_abilitazione_tpk.get_stato'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'stato')
                    , ' AFC_DDL.IsNullable on richieste_abilitazione_tpk.get_stato'
                    );
   end if;
   return  d_result;
end get_stato; -- richieste_abilitazione_tpk.get_stato
--------------------------------------------------------------------------------
function get_data
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return RICHIESTE_ABILITAZIONE.data%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data
 DESCRIZIONE: Getter per attributo data di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RICHIESTE_ABILITAZIONE.data%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RICHIESTE_ABILITAZIONE.data%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.get_data'
           );
   select data
   into   d_result
   from   RICHIESTE_ABILITAZIONE
   where
   id_richiesta = p_id_richiesta
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on richieste_abilitazione_tpk.get_data'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data')
                    , ' AFC_DDL.IsNullable on richieste_abilitazione_tpk.get_data'
                    );
   end if;
   return  d_result;
end get_data; -- richieste_abilitazione_tpk.get_data
--------------------------------------------------------------------------------
function get_note
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return RICHIESTE_ABILITAZIONE.note%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_note
 DESCRIZIONE: Getter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RICHIESTE_ABILITAZIONE.note%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RICHIESTE_ABILITAZIONE.note%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.get_note'
           );
   select note
   into   d_result
   from   RICHIESTE_ABILITAZIONE
   where
   id_richiesta = p_id_richiesta
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on richieste_abilitazione_tpk.get_note'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'note')
                    , ' AFC_DDL.IsNullable on richieste_abilitazione_tpk.get_note'
                    );
   end if;
   return  d_result;
end get_note; -- richieste_abilitazione_tpk.get_note
--------------------------------------------------------------------------------
function get_tipo_notifica
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return RICHIESTE_ABILITAZIONE.tipo_notifica%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_tipo_notifica
 DESCRIZIONE: Getter per attributo tipo_notifica di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RICHIESTE_ABILITAZIONE.tipo_notifica%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RICHIESTE_ABILITAZIONE.tipo_notifica%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.get_tipo_notifica'
           );
   select tipo_notifica
   into   d_result
   from   RICHIESTE_ABILITAZIONE
   where
   id_richiesta = p_id_richiesta
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on richieste_abilitazione_tpk.get_tipo_notifica'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'tipo_notifica')
                    , ' AFC_DDL.IsNullable on richieste_abilitazione_tpk.get_tipo_notifica'
                    );
   end if;
   return  d_result;
end get_tipo_notifica; -- richieste_abilitazione_tpk.get_tipo_notifica
--------------------------------------------------------------------------------
function get_indirizzo_notifica
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return RICHIESTE_ABILITAZIONE.indirizzo_notifica%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_indirizzo_notifica
 DESCRIZIONE: Getter per attributo indirizzo_notifica di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RICHIESTE_ABILITAZIONE.indirizzo_notifica%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RICHIESTE_ABILITAZIONE.indirizzo_notifica%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.get_indirizzo_notifica'
           );
   select indirizzo_notifica
   into   d_result
   from   RICHIESTE_ABILITAZIONE
   where
   id_richiesta = p_id_richiesta
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on richieste_abilitazione_tpk.get_indirizzo_notifica'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'indirizzo_notifica')
                    , ' AFC_DDL.IsNullable on richieste_abilitazione_tpk.get_indirizzo_notifica'
                    );
   end if;
   return  d_result;
end get_indirizzo_notifica; -- richieste_abilitazione_tpk.get_indirizzo_notifica
--------------------------------------------------------------------------------
function get_note_notifica
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return RICHIESTE_ABILITAZIONE.note_notifica%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_note_notifica
 DESCRIZIONE: Getter per attributo note_notifica di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RICHIESTE_ABILITAZIONE.note_notifica%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RICHIESTE_ABILITAZIONE.note_notifica%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.get_note_notifica'
           );
   select note_notifica
   into   d_result
   from   RICHIESTE_ABILITAZIONE
   where
   id_richiesta = p_id_richiesta
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on richieste_abilitazione_tpk.get_note_notifica'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'note_notifica')
                    , ' AFC_DDL.IsNullable on richieste_abilitazione_tpk.get_note_notifica'
                    );
   end if;
   return  d_result;
end get_note_notifica; -- richieste_abilitazione_tpk.get_note_notifica
--------------------------------------------------------------------------------
function get_notificata
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
) return RICHIESTE_ABILITAZIONE.notificata%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_notificata
 DESCRIZIONE: Getter per attributo notificata di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RICHIESTE_ABILITAZIONE.notificata%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RICHIESTE_ABILITAZIONE.notificata%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.get_notificata'
           );
   select notificata
   into   d_result
   from   RICHIESTE_ABILITAZIONE
   where
   id_richiesta = p_id_richiesta
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on richieste_abilitazione_tpk.get_notificata'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'notificata')
                    , ' AFC_DDL.IsNullable on richieste_abilitazione_tpk.get_notificata'
                    );
   end if;
   return  d_result;
end get_notificata; -- richieste_abilitazione_tpk.get_notificata
--------------------------------------------------------------------------------
procedure set_id_richiesta
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_value  in RICHIESTE_ABILITAZIONE.id_richiesta%type default null
) is
/******************************************************************************
 NOME:        set_id_richiesta
 DESCRIZIONE: Setter per attributo id_richiesta di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.set_id_richiesta'
           );
   update RICHIESTE_ABILITAZIONE
   set id_richiesta = p_value
   where
   id_richiesta = p_id_richiesta
   ;
end set_id_richiesta; -- richieste_abilitazione_tpk.set_id_richiesta
--------------------------------------------------------------------------------
procedure set_utente
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_value  in RICHIESTE_ABILITAZIONE.utente%type default null
) is
/******************************************************************************
 NOME:        set_utente
 DESCRIZIONE: Setter per attributo utente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.set_utente'
           );
   update RICHIESTE_ABILITAZIONE
   set utente = p_value
   where
   id_richiesta = p_id_richiesta
   ;
end set_utente; -- richieste_abilitazione_tpk.set_utente
--------------------------------------------------------------------------------
procedure set_modulo
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_value  in RICHIESTE_ABILITAZIONE.modulo%type default null
) is
/******************************************************************************
 NOME:        set_modulo
 DESCRIZIONE: Setter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.set_modulo'
           );
   update RICHIESTE_ABILITAZIONE
   set modulo = p_value
   where
   id_richiesta = p_id_richiesta
   ;
end set_modulo; -- richieste_abilitazione_tpk.set_modulo
--------------------------------------------------------------------------------
procedure set_istanza
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_value  in RICHIESTE_ABILITAZIONE.istanza%type default null
) is
/******************************************************************************
 NOME:        set_istanza
 DESCRIZIONE: Setter per attributo istanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.set_istanza'
           );
   update RICHIESTE_ABILITAZIONE
   set istanza = p_value
   where
   id_richiesta = p_id_richiesta
   ;
end set_istanza; -- richieste_abilitazione_tpk.set_istanza
--------------------------------------------------------------------------------
procedure set_stato
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_value  in RICHIESTE_ABILITAZIONE.stato%type default null
) is
/******************************************************************************
 NOME:        set_stato
 DESCRIZIONE: Setter per attributo stato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.set_stato'
           );
   update RICHIESTE_ABILITAZIONE
   set stato = p_value
   where
   id_richiesta = p_id_richiesta
   ;
end set_stato; -- richieste_abilitazione_tpk.set_stato
--------------------------------------------------------------------------------
procedure set_data
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_value  in RICHIESTE_ABILITAZIONE.data%type default null
) is
/******************************************************************************
 NOME:        set_data
 DESCRIZIONE: Setter per attributo data di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.set_data'
           );
   update RICHIESTE_ABILITAZIONE
   set data = p_value
   where
   id_richiesta = p_id_richiesta
   ;
end set_data; -- richieste_abilitazione_tpk.set_data
--------------------------------------------------------------------------------
procedure set_note
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_value  in RICHIESTE_ABILITAZIONE.note%type default null
) is
/******************************************************************************
 NOME:        set_note
 DESCRIZIONE: Setter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.set_note'
           );
   update RICHIESTE_ABILITAZIONE
   set note = p_value
   where
   id_richiesta = p_id_richiesta
   ;
end set_note; -- richieste_abilitazione_tpk.set_note
--------------------------------------------------------------------------------
procedure set_tipo_notifica
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_value  in RICHIESTE_ABILITAZIONE.tipo_notifica%type default null
) is
/******************************************************************************
 NOME:        set_tipo_notifica
 DESCRIZIONE: Setter per attributo tipo_notifica di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.set_tipo_notifica'
           );
   update RICHIESTE_ABILITAZIONE
   set tipo_notifica = p_value
   where
   id_richiesta = p_id_richiesta
   ;
end set_tipo_notifica; -- richieste_abilitazione_tpk.set_tipo_notifica
--------------------------------------------------------------------------------
procedure set_indirizzo_notifica
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_value  in RICHIESTE_ABILITAZIONE.indirizzo_notifica%type default null
) is
/******************************************************************************
 NOME:        set_indirizzo_notifica
 DESCRIZIONE: Setter per attributo indirizzo_notifica di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.set_indirizzo_notifica'
           );
   update RICHIESTE_ABILITAZIONE
   set indirizzo_notifica = p_value
   where
   id_richiesta = p_id_richiesta
   ;
end set_indirizzo_notifica; -- richieste_abilitazione_tpk.set_indirizzo_notifica
--------------------------------------------------------------------------------
procedure set_note_notifica
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_value  in RICHIESTE_ABILITAZIONE.note_notifica%type default null
) is
/******************************************************************************
 NOME:        set_note_notifica
 DESCRIZIONE: Setter per attributo note_notifica di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.set_note_notifica'
           );
   update RICHIESTE_ABILITAZIONE
   set note_notifica = p_value
   where
   id_richiesta = p_id_richiesta
   ;
end set_note_notifica; -- richieste_abilitazione_tpk.set_note_notifica
--------------------------------------------------------------------------------
procedure set_notificata
(
  p_id_richiesta  in RICHIESTE_ABILITAZIONE.id_richiesta%type
, p_value  in RICHIESTE_ABILITAZIONE.notificata%type default null
) is
/******************************************************************************
 NOME:        set_notificata
 DESCRIZIONE: Setter per attributo notificata di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_richiesta => p_id_richiesta
                                        )
           , 'existsId on richieste_abilitazione_tpk.set_notificata'
           );
   update RICHIESTE_ABILITAZIONE
   set notificata = p_value
   where
   id_richiesta = p_id_richiesta
   ;
end set_notificata; -- richieste_abilitazione_tpk.set_notificata
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id_richiesta  in varchar2 default null
, p_utente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_istanza  in varchar2 default null
, p_stato  in varchar2 default null
, p_data  in varchar2 default null
, p_note  in varchar2 default null
, p_tipo_notifica  in varchar2 default null
, p_indirizzo_notifica  in varchar2 default null
, p_note_notifica  in varchar2 default null
, p_notificata  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( id_richiesta ', p_id_richiesta, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( utente ', p_utente , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( modulo ', p_modulo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( istanza ', p_istanza , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( stato ', p_stato , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data ', p_data , ' )', p_QBE, AFC.date_format )
               || AFC.get_field_condition( ' and ( note ', p_note , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( tipo_notifica ', p_tipo_notifica , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( indirizzo_notifica ', p_indirizzo_notifica , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( note_notifica ', p_note_notifica , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( notificata ', p_notificata , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- richieste_abilitazione_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_id_richiesta  in varchar2 default null
, p_utente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_istanza  in varchar2 default null
, p_stato  in varchar2 default null
, p_data  in varchar2 default null
, p_note  in varchar2 default null
, p_tipo_notifica  in varchar2 default null
, p_indirizzo_notifica  in varchar2 default null
, p_note_notifica  in varchar2 default null
, p_notificata  in varchar2 default null
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
   d_statement := ' select RICHIESTE_ABILITAZIONE.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from RICHIESTE_ABILITAZIONE '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id_richiesta => p_id_richiesta
                                 , p_utente => p_utente
                                 , p_modulo => p_modulo
                                 , p_istanza => p_istanza
                                 , p_stato => p_stato
                                 , p_data => p_data
                                 , p_note => p_note
                                 , p_tipo_notifica => p_tipo_notifica
                                 , p_indirizzo_notifica => p_indirizzo_notifica
                                 , p_note_notifica => p_note_notifica
                                 , p_notificata => p_notificata
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- richieste_abilitazione_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id_richiesta  in varchar2 default null
, p_utente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_istanza  in varchar2 default null
, p_stato  in varchar2 default null
, p_data  in varchar2 default null
, p_note  in varchar2 default null
, p_tipo_notifica  in varchar2 default null
, p_indirizzo_notifica  in varchar2 default null
, p_note_notifica  in varchar2 default null
, p_notificata  in varchar2 default null
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
   d_statement := ' select count( * ) from RICHIESTE_ABILITAZIONE '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id_richiesta => p_id_richiesta
                                 , p_utente => p_utente
                                 , p_modulo => p_modulo
                                 , p_istanza => p_istanza
                                 , p_stato => p_stato
                                 , p_data => p_data
                                 , p_note => p_note
                                 , p_tipo_notifica => p_tipo_notifica
                                 , p_indirizzo_notifica => p_indirizzo_notifica
                                 , p_note_notifica => p_note_notifica
                                 , p_notificata => p_notificata
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- richieste_abilitazione_tpk.count_rows
--------------------------------------------------------------------------------
end richieste_abilitazione_tpk;
/

