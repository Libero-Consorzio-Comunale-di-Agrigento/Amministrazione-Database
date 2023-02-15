CREATE OR REPLACE package body sportelli_tpk is
/******************************************************************************
 NOME:        sportelli_tpk
 DESCRIZIONE: Gestione tabella SPORTELLI.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   17/03/2017  snegroni  Prima emissione.
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
end versione; -- sportelli_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.abi := p_abi;
d_result.cab := p_cab;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_abi => d_result.abi,
p_cab => d_result.cab
                                        )
           , 'canHandle on sportelli_tpk.PK'
           );
   return  d_result;
end PK; -- sportelli_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
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
          p_abi is null
or p_cab is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on sportelli_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- sportelli_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave è manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_abi => p_abi
,p_cab => p_cab
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- sportelli_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
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
,p_cab => p_cab
                                        )
           , 'canHandle on sportelli_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   SPORTELLI
      where
      abi = p_abi
and cab = p_cab
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on sportelli_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- sportelli_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_abi => p_abi
,p_cab => p_cab
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- sportelli_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_cin_cab  in SPORTELLI.cin_cab%type default null
, p_descrizione  in SPORTELLI.descrizione%type default null
, p_indirizzo  in SPORTELLI.indirizzo%type default null
, p_localita  in SPORTELLI.localita%type default null
, p_comune  in SPORTELLI.comune%type default null
, p_provincia  in SPORTELLI.provincia%type default null
, p_cap  in SPORTELLI.cap%type default null
, p_dipendenza  in SPORTELLI.dipendenza%type default null
, p_bic  in SPORTELLI.bic%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_cin_cab is not null or /*default value*/ 'default' is not null
           , 'p_cin_cab on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione is not null or /*default value*/ 'default' is not null
           , 'p_descrizione on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_indirizzo is not null or /*default value*/ 'default' is not null
           , 'p_indirizzo on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_localita is not null or /*default value*/ 'default' is not null
           , 'p_localita on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_comune is not null or /*default value*/ 'default' is not null
           , 'p_comune on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_provincia is not null or /*default value*/ 'default' is not null
           , 'p_provincia on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_cap is not null or /*default value*/ 'default' is not null
           , 'p_cap on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_dipendenza is not null or /*default value*/ 'default' is not null
           , 'p_dipendenza on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_bic is not null or /*default value*/ 'default' is not null
           , 'p_bic on sportelli_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_abi is null and /*default value*/ '' is not null ) -- PK nullable on insert
or (   p_cab is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_abi => p_abi
,p_cab => p_cab
                           )
           , 'not existsId on sportelli_tpk.ins'
           );
   insert into SPORTELLI
   (
     abi
,cab
   , cin_cab
   , descrizione
   , indirizzo
   , localita
   , comune
   , provincia
   , cap
   , dipendenza
   , bic
   )
   values
   (
     p_abi
,p_cab
   , p_cin_cab
   , p_descrizione
   , p_indirizzo
   , p_localita
   , p_comune
   , p_provincia
   , p_cap
   , p_dipendenza
   , p_bic
   );
end ins; -- sportelli_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_cin_cab  in SPORTELLI.cin_cab%type default null
, p_descrizione  in SPORTELLI.descrizione%type default null
, p_indirizzo  in SPORTELLI.indirizzo%type default null
, p_localita  in SPORTELLI.localita%type default null
, p_comune  in SPORTELLI.comune%type default null
, p_provincia  in SPORTELLI.provincia%type default null
, p_cap  in SPORTELLI.cap%type default null
, p_dipendenza  in SPORTELLI.dipendenza%type default null
, p_bic  in SPORTELLI.bic%type default null
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
   DbC.PRE ( not DbC.PreOn or p_cin_cab is not null or /*default value*/ 'default' is not null
           , 'p_cin_cab on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione is not null or /*default value*/ 'default' is not null
           , 'p_descrizione on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_indirizzo is not null or /*default value*/ 'default' is not null
           , 'p_indirizzo on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_localita is not null or /*default value*/ 'default' is not null
           , 'p_localita on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_comune is not null or /*default value*/ 'default' is not null
           , 'p_comune on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_provincia is not null or /*default value*/ 'default' is not null
           , 'p_provincia on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_cap is not null or /*default value*/ 'default' is not null
           , 'p_cap on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_dipendenza is not null or /*default value*/ 'default' is not null
           , 'p_dipendenza on sportelli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_bic is not null or /*default value*/ 'default' is not null
           , 'p_bic on sportelli_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_abi is null and /*default value*/ '' is not null ) -- PK nullable on insert
or (   p_cab is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_abi => p_abi
,p_cab => p_cab
                           )
           , 'not existsId on sportelli_tpk.ins'
           );
   begin
      insert into SPORTELLI
      (
        abi
,cab
      , cin_cab
      , descrizione
      , indirizzo
      , localita
      , comune
      , provincia
      , cap
      , dipendenza
      , bic
      )
      values
      (
        p_abi
,p_cab
      , p_cin_cab
      , p_descrizione
      , p_indirizzo
      , p_localita
      , p_comune
      , p_provincia
      , p_cap
      , p_dipendenza
      , p_bic
      );
      d_result := 0;
   exception
      when others then
         d_result := sqlcode;
   end;
   return d_result;
end ins; -- sportelli_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_abi  in SPORTELLI.abi%type
, p_OLD_abi  in SPORTELLI.abi%type default null
, p_NEW_cab  in SPORTELLI.cab%type
, p_OLD_cab  in SPORTELLI.cab%type default null
, p_NEW_cin_cab  in SPORTELLI.cin_cab%type default afc.default_null('SPORTELLI.cin_cab')
, p_OLD_cin_cab  in SPORTELLI.cin_cab%type default null
, p_NEW_descrizione  in SPORTELLI.descrizione%type default afc.default_null('SPORTELLI.descrizione')
, p_OLD_descrizione  in SPORTELLI.descrizione%type default null
, p_NEW_indirizzo  in SPORTELLI.indirizzo%type default afc.default_null('SPORTELLI.indirizzo')
, p_OLD_indirizzo  in SPORTELLI.indirizzo%type default null
, p_NEW_localita  in SPORTELLI.localita%type default afc.default_null('SPORTELLI.localita')
, p_OLD_localita  in SPORTELLI.localita%type default null
, p_NEW_comune  in SPORTELLI.comune%type default afc.default_null('SPORTELLI.comune')
, p_OLD_comune  in SPORTELLI.comune%type default null
, p_NEW_provincia  in SPORTELLI.provincia%type default afc.default_null('SPORTELLI.provincia')
, p_OLD_provincia  in SPORTELLI.provincia%type default null
, p_NEW_cap  in SPORTELLI.cap%type default afc.default_null('SPORTELLI.cap')
, p_OLD_cap  in SPORTELLI.cap%type default null
, p_NEW_dipendenza  in SPORTELLI.dipendenza%type default afc.default_null('SPORTELLI.dipendenza')
, p_OLD_dipendenza  in SPORTELLI.dipendenza%type default null
, p_NEW_bic  in SPORTELLI.bic%type default afc.default_null('SPORTELLI.bic')
, p_OLD_bic  in SPORTELLI.bic%type default null
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
p_OLD_cin_cab is not null
 or p_OLD_descrizione is not null
 or p_OLD_indirizzo is not null
 or p_OLD_localita is not null
 or p_OLD_comune is not null
 or p_OLD_provincia is not null
 or p_OLD_cap is not null
 or p_OLD_dipendenza is not null
 or p_OLD_bic is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on sportelli_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_abi, p_NEW_abi )
,nvl( p_OLD_cab, p_NEW_cab )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_abi => d_key.abi
,p_cab => d_key.cab
                                       )
           , 'existsId on sportelli_tpk.upd'
           );
   update SPORTELLI
   set
       abi = nvl( p_NEW_abi, decode( afc.is_default_null( 'SPORTELLI.abi'), 1, abi, null) )
,cab = nvl( p_NEW_cab, decode( afc.is_default_null( 'SPORTELLI.cab'), 1, cab, null) )
     , cin_cab = nvl( p_NEW_cin_cab, decode( afc.is_default_null( 'SPORTELLI.cin_cab'), 1, cin_cab, null) )
     , descrizione = nvl( p_NEW_descrizione, decode( afc.is_default_null( 'SPORTELLI.descrizione'), 1, descrizione, null) )
     , indirizzo = nvl( p_NEW_indirizzo, decode( afc.is_default_null( 'SPORTELLI.indirizzo'), 1, indirizzo, null) )
     , localita = nvl( p_NEW_localita, decode( afc.is_default_null( 'SPORTELLI.localita'), 1, localita, null) )
     , comune = nvl( p_NEW_comune, decode( afc.is_default_null( 'SPORTELLI.comune'), 1, comune, null) )
     , provincia = nvl( p_NEW_provincia, decode( afc.is_default_null( 'SPORTELLI.provincia'), 1, provincia, null) )
     , cap = nvl( p_NEW_cap, decode( afc.is_default_null( 'SPORTELLI.cap'), 1, cap, null) )
     , dipendenza = nvl( p_NEW_dipendenza, decode( afc.is_default_null( 'SPORTELLI.dipendenza'), 1, dipendenza, null) )
     , bic = nvl( p_NEW_bic, decode( afc.is_default_null( 'SPORTELLI.bic'), 1, bic, null) )
   where
     abi = d_key.abi
and cab = d_key.cab
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( cin_cab = p_OLD_cin_cab or ( p_OLD_cin_cab is null and ( p_check_OLD is null or cin_cab is null ) ) )
           and ( descrizione = p_OLD_descrizione or ( p_OLD_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( indirizzo = p_OLD_indirizzo or ( p_OLD_indirizzo is null and ( p_check_OLD is null or indirizzo is null ) ) )
           and ( localita = p_OLD_localita or ( p_OLD_localita is null and ( p_check_OLD is null or localita is null ) ) )
           and ( comune = p_OLD_comune or ( p_OLD_comune is null and ( p_check_OLD is null or comune is null ) ) )
           and ( provincia = p_OLD_provincia or ( p_OLD_provincia is null and ( p_check_OLD is null or provincia is null ) ) )
           and ( cap = p_OLD_cap or ( p_OLD_cap is null and ( p_check_OLD is null or cap is null ) ) )
           and ( dipendenza = p_OLD_dipendenza or ( p_OLD_dipendenza is null and ( p_check_OLD is null or dipendenza is null ) ) )
           and ( bic = p_OLD_bic or ( p_OLD_bic is null and ( p_check_OLD is null or bic is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on sportelli_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- sportelli_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_abi  in SPORTELLI.abi%type,
p_cab  in SPORTELLI.cab%type
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
                                        p_abi => p_abi,
p_cab => p_cab
                                       )
           , 'existsId on sportelli_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on sportelli_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on sportelli_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on sportelli_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update SPORTELLI '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( abi ', p_abi, ' )', 0, null ), ' and ( abi is null ) ' )
 || nvl( AFC.get_field_condition( ' and ( cab ', p_cab, ' )', 0, null ), ' and ( cab is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- sportelli_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_abi  in SPORTELLI.abi%type
, p_cab  in SPORTELLI.cab%type
, p_cin_cab  in SPORTELLI.cin_cab%type default null
, p_descrizione  in SPORTELLI.descrizione%type default null
, p_indirizzo  in SPORTELLI.indirizzo%type default null
, p_localita  in SPORTELLI.localita%type default null
, p_comune  in SPORTELLI.comune%type default null
, p_provincia  in SPORTELLI.provincia%type default null
, p_cap  in SPORTELLI.cap%type default null
, p_dipendenza  in SPORTELLI.dipendenza%type default null
, p_bic  in SPORTELLI.bic%type default null
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
p_cin_cab is not null
 or p_descrizione is not null
 or p_indirizzo is not null
 or p_localita is not null
 or p_comune is not null
 or p_provincia is not null
 or p_cap is not null
 or p_dipendenza is not null
 or p_bic is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on sportelli_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_abi => p_abi,
p_cab => p_cab
                                       )
           , 'existsId on sportelli_tpk.del'
           );
   delete from SPORTELLI
   where
     abi = p_abi and
cab = p_cab
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( cin_cab = p_cin_cab or ( p_cin_cab is null and ( p_check_OLD is null or cin_cab is null ) ) )
           and ( descrizione = p_descrizione or ( p_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( indirizzo = p_indirizzo or ( p_indirizzo is null and ( p_check_OLD is null or indirizzo is null ) ) )
           and ( localita = p_localita or ( p_localita is null and ( p_check_OLD is null or localita is null ) ) )
           and ( comune = p_comune or ( p_comune is null and ( p_check_OLD is null or comune is null ) ) )
           and ( provincia = p_provincia or ( p_provincia is null and ( p_check_OLD is null or provincia is null ) ) )
           and ( cap = p_cap or ( p_cap is null and ( p_check_OLD is null or cap is null ) ) )
           and ( dipendenza = p_dipendenza or ( p_dipendenza is null and ( p_check_OLD is null or dipendenza is null ) ) )
           and ( bic = p_bic or ( p_bic is null and ( p_check_OLD is null or bic is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on sportelli_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_abi => p_abi,
p_cab => p_cab
                                             )
            , 'existsId on sportelli_tpk.del'
            );
end del; -- sportelli_tpk.del
--------------------------------------------------------------------------------
function get_cin_cab
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
) return SPORTELLI.cin_cab%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_cin_cab
 DESCRIZIONE: Getter per attributo cin_cab di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SPORTELLI.cin_cab%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SPORTELLI.cin_cab%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.get_cin_cab'
           );
   select cin_cab
   into   d_result
   from   SPORTELLI
   where
   abi = p_abi and
cab = p_cab
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on sportelli_tpk.get_cin_cab'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'cin_cab')
                    , ' AFC_DDL.IsNullable on sportelli_tpk.get_cin_cab'
                    );
   end if;
   return  d_result;
end get_cin_cab; -- sportelli_tpk.get_cin_cab
--------------------------------------------------------------------------------
function get_descrizione
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
) return SPORTELLI.descrizione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione
 DESCRIZIONE: Getter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SPORTELLI.descrizione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SPORTELLI.descrizione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.get_descrizione'
           );
   select descrizione
   into   d_result
   from   SPORTELLI
   where
   abi = p_abi and
cab = p_cab
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on sportelli_tpk.get_descrizione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione')
                    , ' AFC_DDL.IsNullable on sportelli_tpk.get_descrizione'
                    );
   end if;
   return  d_result;
end get_descrizione; -- sportelli_tpk.get_descrizione
--------------------------------------------------------------------------------
function get_indirizzo
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
) return SPORTELLI.indirizzo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_indirizzo
 DESCRIZIONE: Getter per attributo indirizzo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SPORTELLI.indirizzo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SPORTELLI.indirizzo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.get_indirizzo'
           );
   select indirizzo
   into   d_result
   from   SPORTELLI
   where
   abi = p_abi and
cab = p_cab
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on sportelli_tpk.get_indirizzo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'indirizzo')
                    , ' AFC_DDL.IsNullable on sportelli_tpk.get_indirizzo'
                    );
   end if;
   return  d_result;
end get_indirizzo; -- sportelli_tpk.get_indirizzo
--------------------------------------------------------------------------------
function get_localita
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
) return SPORTELLI.localita%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_localita
 DESCRIZIONE: Getter per attributo localita di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SPORTELLI.localita%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SPORTELLI.localita%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.get_localita'
           );
   select localita
   into   d_result
   from   SPORTELLI
   where
   abi = p_abi and
cab = p_cab
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on sportelli_tpk.get_localita'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'localita')
                    , ' AFC_DDL.IsNullable on sportelli_tpk.get_localita'
                    );
   end if;
   return  d_result;
end get_localita; -- sportelli_tpk.get_localita
--------------------------------------------------------------------------------
function get_comune
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
) return SPORTELLI.comune%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_comune
 DESCRIZIONE: Getter per attributo comune di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SPORTELLI.comune%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SPORTELLI.comune%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.get_comune'
           );
   select comune
   into   d_result
   from   SPORTELLI
   where
   abi = p_abi and
cab = p_cab
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on sportelli_tpk.get_comune'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'comune')
                    , ' AFC_DDL.IsNullable on sportelli_tpk.get_comune'
                    );
   end if;
   return  d_result;
end get_comune; -- sportelli_tpk.get_comune
--------------------------------------------------------------------------------
function get_provincia
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
) return SPORTELLI.provincia%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_provincia
 DESCRIZIONE: Getter per attributo provincia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SPORTELLI.provincia%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SPORTELLI.provincia%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.get_provincia'
           );
   select provincia
   into   d_result
   from   SPORTELLI
   where
   abi = p_abi and
cab = p_cab
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on sportelli_tpk.get_provincia'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'provincia')
                    , ' AFC_DDL.IsNullable on sportelli_tpk.get_provincia'
                    );
   end if;
   return  d_result;
end get_provincia; -- sportelli_tpk.get_provincia
--------------------------------------------------------------------------------
function get_cap
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
) return SPORTELLI.cap%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_cap
 DESCRIZIONE: Getter per attributo cap di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SPORTELLI.cap%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SPORTELLI.cap%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.get_cap'
           );
   select cap
   into   d_result
   from   SPORTELLI
   where
   abi = p_abi and
cab = p_cab
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on sportelli_tpk.get_cap'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'cap')
                    , ' AFC_DDL.IsNullable on sportelli_tpk.get_cap'
                    );
   end if;
   return  d_result;
end get_cap; -- sportelli_tpk.get_cap
--------------------------------------------------------------------------------
function get_dipendenza
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
) return SPORTELLI.dipendenza%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_dipendenza
 DESCRIZIONE: Getter per attributo dipendenza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SPORTELLI.dipendenza%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SPORTELLI.dipendenza%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.get_dipendenza'
           );
   select dipendenza
   into   d_result
   from   SPORTELLI
   where
   abi = p_abi and
cab = p_cab
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on sportelli_tpk.get_dipendenza'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'dipendenza')
                    , ' AFC_DDL.IsNullable on sportelli_tpk.get_dipendenza'
                    );
   end if;
   return  d_result;
end get_dipendenza; -- sportelli_tpk.get_dipendenza
--------------------------------------------------------------------------------
function get_bic
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
) return SPORTELLI.bic%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_bic
 DESCRIZIONE: Getter per attributo bic di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SPORTELLI.bic%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SPORTELLI.bic%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.get_bic'
           );
   select bic
   into   d_result
   from   SPORTELLI
   where
   abi = p_abi and
cab = p_cab
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on sportelli_tpk.get_bic'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'bic')
                    , ' AFC_DDL.IsNullable on sportelli_tpk.get_bic'
                    );
   end if;
   return  d_result;
end get_bic; -- sportelli_tpk.get_bic
--------------------------------------------------------------------------------
procedure set_abi
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_value  in SPORTELLI.abi%type default null
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
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.set_abi'
           );
   update SPORTELLI
   set abi = p_value
   where
   abi = p_abi and
cab = p_cab
   ;
end set_abi; -- sportelli_tpk.set_abi
--------------------------------------------------------------------------------
procedure set_cab
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_value  in SPORTELLI.cab%type default null
) is
/******************************************************************************
 NOME:        set_cab
 DESCRIZIONE: Setter per attributo cab di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.set_cab'
           );
   update SPORTELLI
   set cab = p_value
   where
   abi = p_abi and
cab = p_cab
   ;
end set_cab; -- sportelli_tpk.set_cab
--------------------------------------------------------------------------------
procedure set_cin_cab
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_value  in SPORTELLI.cin_cab%type default null
) is
/******************************************************************************
 NOME:        set_cin_cab
 DESCRIZIONE: Setter per attributo cin_cab di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.set_cin_cab'
           );
   update SPORTELLI
   set cin_cab = p_value
   where
   abi = p_abi and
cab = p_cab
   ;
end set_cin_cab; -- sportelli_tpk.set_cin_cab
--------------------------------------------------------------------------------
procedure set_descrizione
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_value  in SPORTELLI.descrizione%type default null
) is
/******************************************************************************
 NOME:        set_descrizione
 DESCRIZIONE: Setter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.set_descrizione'
           );
   update SPORTELLI
   set descrizione = p_value
   where
   abi = p_abi and
cab = p_cab
   ;
end set_descrizione; -- sportelli_tpk.set_descrizione
--------------------------------------------------------------------------------
procedure set_indirizzo
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_value  in SPORTELLI.indirizzo%type default null
) is
/******************************************************************************
 NOME:        set_indirizzo
 DESCRIZIONE: Setter per attributo indirizzo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.set_indirizzo'
           );
   update SPORTELLI
   set indirizzo = p_value
   where
   abi = p_abi and
cab = p_cab
   ;
end set_indirizzo; -- sportelli_tpk.set_indirizzo
--------------------------------------------------------------------------------
procedure set_localita
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_value  in SPORTELLI.localita%type default null
) is
/******************************************************************************
 NOME:        set_localita
 DESCRIZIONE: Setter per attributo localita di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.set_localita'
           );
   update SPORTELLI
   set localita = p_value
   where
   abi = p_abi and
cab = p_cab
   ;
end set_localita; -- sportelli_tpk.set_localita
--------------------------------------------------------------------------------
procedure set_comune
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_value  in SPORTELLI.comune%type default null
) is
/******************************************************************************
 NOME:        set_comune
 DESCRIZIONE: Setter per attributo comune di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.set_comune'
           );
   update SPORTELLI
   set comune = p_value
   where
   abi = p_abi and
cab = p_cab
   ;
end set_comune; -- sportelli_tpk.set_comune
--------------------------------------------------------------------------------
procedure set_provincia
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_value  in SPORTELLI.provincia%type default null
) is
/******************************************************************************
 NOME:        set_provincia
 DESCRIZIONE: Setter per attributo provincia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.set_provincia'
           );
   update SPORTELLI
   set provincia = p_value
   where
   abi = p_abi and
cab = p_cab
   ;
end set_provincia; -- sportelli_tpk.set_provincia
--------------------------------------------------------------------------------
procedure set_cap
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_value  in SPORTELLI.cap%type default null
) is
/******************************************************************************
 NOME:        set_cap
 DESCRIZIONE: Setter per attributo cap di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.set_cap'
           );
   update SPORTELLI
   set cap = p_value
   where
   abi = p_abi and
cab = p_cab
   ;
end set_cap; -- sportelli_tpk.set_cap
--------------------------------------------------------------------------------
procedure set_dipendenza
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_value  in SPORTELLI.dipendenza%type default null
) is
/******************************************************************************
 NOME:        set_dipendenza
 DESCRIZIONE: Setter per attributo dipendenza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.set_dipendenza'
           );
   update SPORTELLI
   set dipendenza = p_value
   where
   abi = p_abi and
cab = p_cab
   ;
end set_dipendenza; -- sportelli_tpk.set_dipendenza
--------------------------------------------------------------------------------
procedure set_bic
(
  p_abi  in SPORTELLI.abi%type
,p_cab  in SPORTELLI.cab%type
, p_value  in SPORTELLI.bic%type default null
) is
/******************************************************************************
 NOME:        set_bic
 DESCRIZIONE: Setter per attributo bic di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_abi => p_abi
, p_cab => p_cab
                                        )
           , 'existsId on sportelli_tpk.set_bic'
           );
   update SPORTELLI
   set bic = p_value
   where
   abi = p_abi and
cab = p_cab
   ;
end set_bic; -- sportelli_tpk.set_bic
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_abi  in varchar2 default null
, p_cab  in varchar2 default null
, p_cin_cab  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_indirizzo  in varchar2 default null
, p_localita  in varchar2 default null
, p_comune  in varchar2 default null
, p_provincia  in varchar2 default null
, p_cap  in varchar2 default null
, p_dipendenza  in varchar2 default null
, p_bic  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( abi ', p_abi, ' )', p_QBE, null )
|| AFC.get_field_condition( ' and ( cab ', p_cab, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( cin_cab ', p_cin_cab , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione ', p_descrizione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( indirizzo ', p_indirizzo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( localita ', p_localita , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( comune ', p_comune , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( provincia ', p_provincia , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( cap ', p_cap , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( dipendenza ', p_dipendenza , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( bic ', p_bic , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- sportelli_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_abi  in varchar2 default null
, p_cab  in varchar2 default null
, p_cin_cab  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_indirizzo  in varchar2 default null
, p_localita  in varchar2 default null
, p_comune  in varchar2 default null
, p_provincia  in varchar2 default null
, p_cap  in varchar2 default null
, p_dipendenza  in varchar2 default null
, p_bic  in varchar2 default null
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
   d_statement := ' select SPORTELLI.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from SPORTELLI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_abi => p_abi
, p_cab => p_cab
                                 , p_cin_cab => p_cin_cab
                                 , p_descrizione => p_descrizione
                                 , p_indirizzo => p_indirizzo
                                 , p_localita => p_localita
                                 , p_comune => p_comune
                                 , p_provincia => p_provincia
                                 , p_cap => p_cap
                                 , p_dipendenza => p_dipendenza
                                 , p_bic => p_bic
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- sportelli_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_abi  in varchar2 default null
, p_cab  in varchar2 default null
, p_cin_cab  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_indirizzo  in varchar2 default null
, p_localita  in varchar2 default null
, p_comune  in varchar2 default null
, p_provincia  in varchar2 default null
, p_cap  in varchar2 default null
, p_dipendenza  in varchar2 default null
, p_bic  in varchar2 default null
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
   d_statement := ' select count( * ) from SPORTELLI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_abi => p_abi
, p_cab => p_cab
                                 , p_cin_cab => p_cin_cab
                                 , p_descrizione => p_descrizione
                                 , p_indirizzo => p_indirizzo
                                 , p_localita => p_localita
                                 , p_comune => p_comune
                                 , p_provincia => p_provincia
                                 , p_cap => p_cap
                                 , p_dipendenza => p_dipendenza
                                 , p_bic => p_bic
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- sportelli_tpk.count_rows
--------------------------------------------------------------------------------
end sportelli_tpk;
/

