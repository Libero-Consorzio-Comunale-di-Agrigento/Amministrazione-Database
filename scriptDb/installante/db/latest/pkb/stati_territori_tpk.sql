--liquibase formatted sql

--changeset mturra:201901301231_187 runOnChange:true stripComments:false


create or replace package body stati_territori_tpk is
/******************************************************************************
 NOME:        stati_territori_tpk
 DESCRIZIONE: Gestione tabella STATI_TERRITORI.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   07/12/2016  snegroni  Prima emissione.
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
end versione; -- stati_territori_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.stato_territorio := p_stato_territorio;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_stato_territorio => d_result.stato_territorio
                                        )
           , 'canHandle on stati_territori_tpk.PK'
           );
   return  d_result;
end PK; -- stati_territori_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
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
          p_stato_territorio is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on stati_territori_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- stati_territori_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave è manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_stato_territorio => p_stato_territorio
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- stati_territori_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
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
                                         p_stato_territorio => p_stato_territorio
                                        )
           , 'canHandle on stati_territori_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   STATI_TERRITORI
      where
      stato_territorio = p_stato_territorio
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on stati_territori_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- stati_territori_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_stato_territorio => p_stato_territorio
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- stati_territori_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type default null
, p_denominazione  in STATI_TERRITORI.denominazione%type default null
, p_denominazione_al1  in STATI_TERRITORI.denominazione_al1%type default null
, p_denominazione_al2  in STATI_TERRITORI.denominazione_al2%type default null
, p_sigla  in STATI_TERRITORI.sigla%type default null
, p_desc_cittadinanza  in STATI_TERRITORI.desc_cittadinanza%type default null
, p_desc_cittadinanza_al1  in STATI_TERRITORI.desc_cittadinanza_al1%type default null
, p_desc_cittadinanza_al2  in STATI_TERRITORI.desc_cittadinanza_al2%type default null
, p_raggruppamento  in STATI_TERRITORI.raggruppamento%type default null
, p_stato_appartenenza  in STATI_TERRITORI.stato_appartenenza%type default null
, p_utente_aggiornamento  in STATI_TERRITORI.utente_aggiornamento%type default null
, p_data_aggiornamento  in STATI_TERRITORI.data_aggiornamento%type default null
, p_sigla_iso3166_alpha2  in STATI_TERRITORI.sigla_iso3166_alpha2%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_denominazione is not null or /*default value*/ 'default' is not null
           , 'p_denominazione on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al1 on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al2 on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_sigla is not null or /*default value*/ 'default' is not null
           , 'p_sigla on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_desc_cittadinanza is not null or /*default value*/ 'default' is not null
           , 'p_desc_cittadinanza on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_desc_cittadinanza_al1 is not null or /*default value*/ 'default' is not null
           , 'p_desc_cittadinanza_al1 on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_desc_cittadinanza_al2 is not null or /*default value*/ 'default' is not null
           , 'p_desc_cittadinanza_al2 on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_raggruppamento is not null or /*default value*/ 'default' is not null
           , 'p_raggruppamento on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_stato_appartenenza is not null or /*default value*/ 'default' is not null
           , 'p_stato_appartenenza on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_utente_aggiornamento on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_data_aggiornamento on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_sigla_iso3166_alpha2 is not null or /*default value*/ 'default' is not null
           , 'p_sigla_iso3166_alpha2 on stati_territori_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_stato_territorio is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_stato_territorio => p_stato_territorio
                           )
           , 'not existsId on stati_territori_tpk.ins'
           );
   insert into STATI_TERRITORI
   (
     stato_territorio
   , denominazione
   , denominazione_al1
   , denominazione_al2
   , sigla
   , desc_cittadinanza
   , desc_cittadinanza_al1
   , desc_cittadinanza_al2
   , raggruppamento
   , stato_appartenenza
   , utente_aggiornamento
   , data_aggiornamento
   , sigla_iso3166_alpha2
   )
   values
   (
     p_stato_territorio
   , p_denominazione
   , p_denominazione_al1
   , p_denominazione_al2
   , p_sigla
   , p_desc_cittadinanza
   , p_desc_cittadinanza_al1
   , p_desc_cittadinanza_al2
   , p_raggruppamento
   , p_stato_appartenenza
   , p_utente_aggiornamento
   , p_data_aggiornamento
   , p_sigla_iso3166_alpha2
   );
end ins; -- stati_territori_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type default null
, p_denominazione  in STATI_TERRITORI.denominazione%type default null
, p_denominazione_al1  in STATI_TERRITORI.denominazione_al1%type default null
, p_denominazione_al2  in STATI_TERRITORI.denominazione_al2%type default null
, p_sigla  in STATI_TERRITORI.sigla%type default null
, p_desc_cittadinanza  in STATI_TERRITORI.desc_cittadinanza%type default null
, p_desc_cittadinanza_al1  in STATI_TERRITORI.desc_cittadinanza_al1%type default null
, p_desc_cittadinanza_al2  in STATI_TERRITORI.desc_cittadinanza_al2%type default null
, p_raggruppamento  in STATI_TERRITORI.raggruppamento%type default null
, p_stato_appartenenza  in STATI_TERRITORI.stato_appartenenza%type default null
, p_utente_aggiornamento  in STATI_TERRITORI.utente_aggiornamento%type default null
, p_data_aggiornamento  in STATI_TERRITORI.data_aggiornamento%type default null
, p_sigla_iso3166_alpha2  in STATI_TERRITORI.sigla_iso3166_alpha2%type default null
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
   DbC.PRE ( not DbC.PreOn or p_denominazione is not null or /*default value*/ 'default' is not null
           , 'p_denominazione on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al1 on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al2 on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_sigla is not null or /*default value*/ 'default' is not null
           , 'p_sigla on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_desc_cittadinanza is not null or /*default value*/ 'default' is not null
           , 'p_desc_cittadinanza on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_desc_cittadinanza_al1 is not null or /*default value*/ 'default' is not null
           , 'p_desc_cittadinanza_al1 on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_desc_cittadinanza_al2 is not null or /*default value*/ 'default' is not null
           , 'p_desc_cittadinanza_al2 on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_raggruppamento is not null or /*default value*/ 'default' is not null
           , 'p_raggruppamento on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_stato_appartenenza is not null or /*default value*/ 'default' is not null
           , 'p_stato_appartenenza on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_utente_aggiornamento on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_data_aggiornamento on stati_territori_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_sigla_iso3166_alpha2 is not null or /*default value*/ 'default' is not null
           , 'p_sigla_iso3166_alpha2 on stati_territori_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_stato_territorio is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_stato_territorio => p_stato_territorio
                           )
           , 'not existsId on stati_territori_tpk.ins'
           );
   begin
      insert into STATI_TERRITORI
      (
        stato_territorio
      , denominazione
      , denominazione_al1
      , denominazione_al2
      , sigla
      , desc_cittadinanza
      , desc_cittadinanza_al1
      , desc_cittadinanza_al2
      , raggruppamento
      , stato_appartenenza
      , utente_aggiornamento
      , data_aggiornamento
      , sigla_iso3166_alpha2
      )
      values
      (
        p_stato_territorio
      , p_denominazione
      , p_denominazione_al1
      , p_denominazione_al2
      , p_sigla
      , p_desc_cittadinanza
      , p_desc_cittadinanza_al1
      , p_desc_cittadinanza_al2
      , p_raggruppamento
      , p_stato_appartenenza
      , p_utente_aggiornamento
      , p_data_aggiornamento
      , p_sigla_iso3166_alpha2
      ) returning stato_territorio
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
end ins; -- stati_territori_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_OLD_stato_territorio  in STATI_TERRITORI.stato_territorio%type default null
, p_NEW_denominazione  in STATI_TERRITORI.denominazione%type default afc.default_null('STATI_TERRITORI.denominazione')
, p_OLD_denominazione  in STATI_TERRITORI.denominazione%type default null
, p_NEW_denominazione_al1  in STATI_TERRITORI.denominazione_al1%type default afc.default_null('STATI_TERRITORI.denominazione_al1')
, p_OLD_denominazione_al1  in STATI_TERRITORI.denominazione_al1%type default null
, p_NEW_denominazione_al2  in STATI_TERRITORI.denominazione_al2%type default afc.default_null('STATI_TERRITORI.denominazione_al2')
, p_OLD_denominazione_al2  in STATI_TERRITORI.denominazione_al2%type default null
, p_NEW_sigla  in STATI_TERRITORI.sigla%type default afc.default_null('STATI_TERRITORI.sigla')
, p_OLD_sigla  in STATI_TERRITORI.sigla%type default null
, p_NEW_desc_cittadinanza  in STATI_TERRITORI.desc_cittadinanza%type default afc.default_null('STATI_TERRITORI.desc_cittadinanza')
, p_OLD_desc_cittadinanza  in STATI_TERRITORI.desc_cittadinanza%type default null
, p_NEW_desc_cittadinanza_al1  in STATI_TERRITORI.desc_cittadinanza_al1%type default afc.default_null('STATI_TERRITORI.desc_cittadinanza_al1')
, p_OLD_desc_cittadinanza_al1  in STATI_TERRITORI.desc_cittadinanza_al1%type default null
, p_NEW_desc_cittadinanza_al2  in STATI_TERRITORI.desc_cittadinanza_al2%type default afc.default_null('STATI_TERRITORI.desc_cittadinanza_al2')
, p_OLD_desc_cittadinanza_al2  in STATI_TERRITORI.desc_cittadinanza_al2%type default null
, p_NEW_raggruppamento  in STATI_TERRITORI.raggruppamento%type default afc.default_null('STATI_TERRITORI.raggruppamento')
, p_OLD_raggruppamento  in STATI_TERRITORI.raggruppamento%type default null
, p_NEW_stato_appartenenza  in STATI_TERRITORI.stato_appartenenza%type default afc.default_null('STATI_TERRITORI.stato_appartenenza')
, p_OLD_stato_appartenenza  in STATI_TERRITORI.stato_appartenenza%type default null
, p_NEW_utente_aggiornamento  in STATI_TERRITORI.utente_aggiornamento%type default afc.default_null('STATI_TERRITORI.utente_aggiornamento')
, p_OLD_utente_aggiornamento  in STATI_TERRITORI.utente_aggiornamento%type default null
, p_NEW_data_aggiornamento  in STATI_TERRITORI.data_aggiornamento%type default afc.default_null('STATI_TERRITORI.data_aggiornamento')
, p_OLD_data_aggiornamento  in STATI_TERRITORI.data_aggiornamento%type default null
, p_NEW_sigla_iso3166_alpha2  in STATI_TERRITORI.sigla_iso3166_alpha2%type default afc.default_null('STATI_TERRITORI.sigla_iso3166_alpha2')
, p_OLD_sigla_iso3166_alpha2  in STATI_TERRITORI.sigla_iso3166_alpha2%type default null
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
p_OLD_denominazione is not null
 or p_OLD_denominazione_al1 is not null
 or p_OLD_denominazione_al2 is not null
 or p_OLD_sigla is not null
 or p_OLD_desc_cittadinanza is not null
 or p_OLD_desc_cittadinanza_al1 is not null
 or p_OLD_desc_cittadinanza_al2 is not null
 or p_OLD_raggruppamento is not null
 or p_OLD_stato_appartenenza is not null
 or p_OLD_utente_aggiornamento is not null
 or p_OLD_data_aggiornamento is not null
 or p_OLD_sigla_iso3166_alpha2 is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on stati_territori_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_stato_territorio, p_NEW_stato_territorio )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_stato_territorio => d_key.stato_territorio
                                       )
           , 'existsId on stati_territori_tpk.upd'
           );
   update STATI_TERRITORI
   set
       stato_territorio = nvl( p_NEW_stato_territorio, decode( afc.is_default_null( 'STATI_TERRITORI.stato_territorio'), 1, stato_territorio, null) )
     , denominazione = nvl( p_NEW_denominazione, decode( afc.is_default_null( 'STATI_TERRITORI.denominazione'), 1, denominazione, null) )
     , denominazione_al1 = nvl( p_NEW_denominazione_al1, decode( afc.is_default_null( 'STATI_TERRITORI.denominazione_al1'), 1, denominazione_al1, null) )
     , denominazione_al2 = nvl( p_NEW_denominazione_al2, decode( afc.is_default_null( 'STATI_TERRITORI.denominazione_al2'), 1, denominazione_al2, null) )
     , sigla = nvl( p_NEW_sigla, decode( afc.is_default_null( 'STATI_TERRITORI.sigla'), 1, sigla, null) )
     , desc_cittadinanza = nvl( p_NEW_desc_cittadinanza, decode( afc.is_default_null( 'STATI_TERRITORI.desc_cittadinanza'), 1, desc_cittadinanza, null) )
     , desc_cittadinanza_al1 = nvl( p_NEW_desc_cittadinanza_al1, decode( afc.is_default_null( 'STATI_TERRITORI.desc_cittadinanza_al1'), 1, desc_cittadinanza_al1, null) )
     , desc_cittadinanza_al2 = nvl( p_NEW_desc_cittadinanza_al2, decode( afc.is_default_null( 'STATI_TERRITORI.desc_cittadinanza_al2'), 1, desc_cittadinanza_al2, null) )
     , raggruppamento = nvl( p_NEW_raggruppamento, decode( afc.is_default_null( 'STATI_TERRITORI.raggruppamento'), 1, raggruppamento, null) )
     , stato_appartenenza = nvl( p_NEW_stato_appartenenza, decode( afc.is_default_null( 'STATI_TERRITORI.stato_appartenenza'), 1, stato_appartenenza, null) )
     , utente_aggiornamento = nvl( p_NEW_utente_aggiornamento, decode( afc.is_default_null( 'STATI_TERRITORI.utente_aggiornamento'), 1, utente_aggiornamento, null) )
     , data_aggiornamento = nvl( p_NEW_data_aggiornamento, decode( afc.is_default_null( 'STATI_TERRITORI.data_aggiornamento'), 1, data_aggiornamento, null) )
     , sigla_iso3166_alpha2 = nvl( p_NEW_sigla_iso3166_alpha2, decode( afc.is_default_null( 'STATI_TERRITORI.sigla_iso3166_alpha2'), 1, sigla_iso3166_alpha2, null) )
   where
     stato_territorio = d_key.stato_territorio
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( denominazione = p_OLD_denominazione or ( p_OLD_denominazione is null and ( p_check_OLD is null or denominazione is null ) ) )
           and ( denominazione_al1 = p_OLD_denominazione_al1 or ( p_OLD_denominazione_al1 is null and ( p_check_OLD is null or denominazione_al1 is null ) ) )
           and ( denominazione_al2 = p_OLD_denominazione_al2 or ( p_OLD_denominazione_al2 is null and ( p_check_OLD is null or denominazione_al2 is null ) ) )
           and ( sigla = p_OLD_sigla or ( p_OLD_sigla is null and ( p_check_OLD is null or sigla is null ) ) )
           and ( desc_cittadinanza = p_OLD_desc_cittadinanza or ( p_OLD_desc_cittadinanza is null and ( p_check_OLD is null or desc_cittadinanza is null ) ) )
           and ( desc_cittadinanza_al1 = p_OLD_desc_cittadinanza_al1 or ( p_OLD_desc_cittadinanza_al1 is null and ( p_check_OLD is null or desc_cittadinanza_al1 is null ) ) )
           and ( desc_cittadinanza_al2 = p_OLD_desc_cittadinanza_al2 or ( p_OLD_desc_cittadinanza_al2 is null and ( p_check_OLD is null or desc_cittadinanza_al2 is null ) ) )
           and ( raggruppamento = p_OLD_raggruppamento or ( p_OLD_raggruppamento is null and ( p_check_OLD is null or raggruppamento is null ) ) )
           and ( stato_appartenenza = p_OLD_stato_appartenenza or ( p_OLD_stato_appartenenza is null and ( p_check_OLD is null or stato_appartenenza is null ) ) )
           and ( utente_aggiornamento = p_OLD_utente_aggiornamento or ( p_OLD_utente_aggiornamento is null and ( p_check_OLD is null or utente_aggiornamento is null ) ) )
           and ( data_aggiornamento = p_OLD_data_aggiornamento or ( p_OLD_data_aggiornamento is null and ( p_check_OLD is null or data_aggiornamento is null ) ) )
           and ( sigla_iso3166_alpha2 = p_OLD_sigla_iso3166_alpha2 or ( p_OLD_sigla_iso3166_alpha2 is null and ( p_check_OLD is null or sigla_iso3166_alpha2 is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on stati_territori_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- stati_territori_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
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
                                        p_stato_territorio => p_stato_territorio
                                       )
           , 'existsId on stati_territori_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on stati_territori_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on stati_territori_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on stati_territori_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update STATI_TERRITORI '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( stato_territorio ', p_stato_territorio, ' )', 0, null ), ' and ( stato_territorio is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- stati_territori_tpk.upd_column
--------------------------------------------------------------------------------
procedure upd_column
(
p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
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
p_stato_territorio => p_stato_territorio
              , p_column => p_column
              , p_value => 'to_date( ''' || d_data || ''', ''' || AFC.date_format || ''' )'
              , p_literal_value => 0
              );
end upd_column; -- stati_territori_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_denominazione  in STATI_TERRITORI.denominazione%type default null
, p_denominazione_al1  in STATI_TERRITORI.denominazione_al1%type default null
, p_denominazione_al2  in STATI_TERRITORI.denominazione_al2%type default null
, p_sigla  in STATI_TERRITORI.sigla%type default null
, p_desc_cittadinanza  in STATI_TERRITORI.desc_cittadinanza%type default null
, p_desc_cittadinanza_al1  in STATI_TERRITORI.desc_cittadinanza_al1%type default null
, p_desc_cittadinanza_al2  in STATI_TERRITORI.desc_cittadinanza_al2%type default null
, p_raggruppamento  in STATI_TERRITORI.raggruppamento%type default null
, p_stato_appartenenza  in STATI_TERRITORI.stato_appartenenza%type default null
, p_utente_aggiornamento  in STATI_TERRITORI.utente_aggiornamento%type default null
, p_data_aggiornamento  in STATI_TERRITORI.data_aggiornamento%type default null
, p_sigla_iso3166_alpha2  in STATI_TERRITORI.sigla_iso3166_alpha2%type default null
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
p_denominazione is not null
 or p_denominazione_al1 is not null
 or p_denominazione_al2 is not null
 or p_sigla is not null
 or p_desc_cittadinanza is not null
 or p_desc_cittadinanza_al1 is not null
 or p_desc_cittadinanza_al2 is not null
 or p_raggruppamento is not null
 or p_stato_appartenenza is not null
 or p_utente_aggiornamento is not null
 or p_data_aggiornamento is not null
 or p_sigla_iso3166_alpha2 is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on stati_territori_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_stato_territorio => p_stato_territorio
                                       )
           , 'existsId on stati_territori_tpk.del'
           );
   delete from STATI_TERRITORI
   where
     stato_territorio = p_stato_territorio
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( denominazione = p_denominazione or ( p_denominazione is null and ( p_check_OLD is null or denominazione is null ) ) )
           and ( denominazione_al1 = p_denominazione_al1 or ( p_denominazione_al1 is null and ( p_check_OLD is null or denominazione_al1 is null ) ) )
           and ( denominazione_al2 = p_denominazione_al2 or ( p_denominazione_al2 is null and ( p_check_OLD is null or denominazione_al2 is null ) ) )
           and ( sigla = p_sigla or ( p_sigla is null and ( p_check_OLD is null or sigla is null ) ) )
           and ( desc_cittadinanza = p_desc_cittadinanza or ( p_desc_cittadinanza is null and ( p_check_OLD is null or desc_cittadinanza is null ) ) )
           and ( desc_cittadinanza_al1 = p_desc_cittadinanza_al1 or ( p_desc_cittadinanza_al1 is null and ( p_check_OLD is null or desc_cittadinanza_al1 is null ) ) )
           and ( desc_cittadinanza_al2 = p_desc_cittadinanza_al2 or ( p_desc_cittadinanza_al2 is null and ( p_check_OLD is null or desc_cittadinanza_al2 is null ) ) )
           and ( raggruppamento = p_raggruppamento or ( p_raggruppamento is null and ( p_check_OLD is null or raggruppamento is null ) ) )
           and ( stato_appartenenza = p_stato_appartenenza or ( p_stato_appartenenza is null and ( p_check_OLD is null or stato_appartenenza is null ) ) )
           and ( utente_aggiornamento = p_utente_aggiornamento or ( p_utente_aggiornamento is null and ( p_check_OLD is null or utente_aggiornamento is null ) ) )
           and ( data_aggiornamento = p_data_aggiornamento or ( p_data_aggiornamento is null and ( p_check_OLD is null or data_aggiornamento is null ) ) )
           and ( sigla_iso3166_alpha2 = p_sigla_iso3166_alpha2 or ( p_sigla_iso3166_alpha2 is null and ( p_check_OLD is null or sigla_iso3166_alpha2 is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on stati_territori_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_stato_territorio => p_stato_territorio
                                             )
            , 'existsId on stati_territori_tpk.del'
            );
end del; -- stati_territori_tpk.del
--------------------------------------------------------------------------------
function get_denominazione
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.denominazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione
 DESCRIZIONE: Getter per attributo denominazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.denominazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.denominazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_denominazione'
           );
   select denominazione
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_denominazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_denominazione'
                    );
   end if;
   return  d_result;
end get_denominazione; -- stati_territori_tpk.get_denominazione
--------------------------------------------------------------------------------
function get_denominazione_al1
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.denominazione_al1%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione_al1
 DESCRIZIONE: Getter per attributo denominazione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.denominazione_al1%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.denominazione_al1%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_denominazione_al1'
           );
   select denominazione_al1
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_denominazione_al1'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione_al1')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_denominazione_al1'
                    );
   end if;
   return  d_result;
end get_denominazione_al1; -- stati_territori_tpk.get_denominazione_al1
--------------------------------------------------------------------------------
function get_denominazione_al2
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.denominazione_al2%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione_al2
 DESCRIZIONE: Getter per attributo denominazione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.denominazione_al2%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.denominazione_al2%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_denominazione_al2'
           );
   select denominazione_al2
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_denominazione_al2'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione_al2')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_denominazione_al2'
                    );
   end if;
   return  d_result;
end get_denominazione_al2; -- stati_territori_tpk.get_denominazione_al2
--------------------------------------------------------------------------------
function get_sigla
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.sigla%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_sigla
 DESCRIZIONE: Getter per attributo sigla di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.sigla%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.sigla%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_sigla'
           );
   select sigla
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_sigla'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'sigla')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_sigla'
                    );
   end if;
   return  d_result;
end get_sigla; -- stati_territori_tpk.get_sigla
--------------------------------------------------------------------------------
function get_desc_cittadinanza
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.desc_cittadinanza%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_desc_cittadinanza
 DESCRIZIONE: Getter per attributo desc_cittadinanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.desc_cittadinanza%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.desc_cittadinanza%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_desc_cittadinanza'
           );
   select desc_cittadinanza
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_desc_cittadinanza'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'desc_cittadinanza')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_desc_cittadinanza'
                    );
   end if;
   return  d_result;
end get_desc_cittadinanza; -- stati_territori_tpk.get_desc_cittadinanza
--------------------------------------------------------------------------------
function get_desc_cittadinanza_al1
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.desc_cittadinanza_al1%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_desc_cittadinanza_al1
 DESCRIZIONE: Getter per attributo desc_cittadinanza_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.desc_cittadinanza_al1%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.desc_cittadinanza_al1%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_desc_cittadinanza_al1'
           );
   select desc_cittadinanza_al1
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_desc_cittadinanza_al1'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'desc_cittadinanza_al1')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_desc_cittadinanza_al1'
                    );
   end if;
   return  d_result;
end get_desc_cittadinanza_al1; -- stati_territori_tpk.get_desc_cittadinanza_al1
--------------------------------------------------------------------------------
function get_desc_cittadinanza_al2
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.desc_cittadinanza_al2%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_desc_cittadinanza_al2
 DESCRIZIONE: Getter per attributo desc_cittadinanza_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.desc_cittadinanza_al2%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.desc_cittadinanza_al2%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_desc_cittadinanza_al2'
           );
   select desc_cittadinanza_al2
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_desc_cittadinanza_al2'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'desc_cittadinanza_al2')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_desc_cittadinanza_al2'
                    );
   end if;
   return  d_result;
end get_desc_cittadinanza_al2; -- stati_territori_tpk.get_desc_cittadinanza_al2
--------------------------------------------------------------------------------
function get_raggruppamento
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.raggruppamento%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_raggruppamento
 DESCRIZIONE: Getter per attributo raggruppamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.raggruppamento%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.raggruppamento%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_raggruppamento'
           );
   select raggruppamento
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_raggruppamento'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'raggruppamento')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_raggruppamento'
                    );
   end if;
   return  d_result;
end get_raggruppamento; -- stati_territori_tpk.get_raggruppamento
--------------------------------------------------------------------------------
function get_stato_appartenenza
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.stato_appartenenza%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_stato_appartenenza
 DESCRIZIONE: Getter per attributo stato_appartenenza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.stato_appartenenza%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.stato_appartenenza%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_stato_appartenenza'
           );
   select stato_appartenenza
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_stato_appartenenza'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'stato_appartenenza')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_stato_appartenenza'
                    );
   end if;
   return  d_result;
end get_stato_appartenenza; -- stati_territori_tpk.get_stato_appartenenza
--------------------------------------------------------------------------------
function get_utente_aggiornamento
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.utente_aggiornamento%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_utente_aggiornamento
 DESCRIZIONE: Getter per attributo utente_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.utente_aggiornamento%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.utente_aggiornamento%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_utente_aggiornamento'
           );
   select utente_aggiornamento
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_utente_aggiornamento'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'utente_aggiornamento')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_utente_aggiornamento'
                    );
   end if;
   return  d_result;
end get_utente_aggiornamento; -- stati_territori_tpk.get_utente_aggiornamento
--------------------------------------------------------------------------------
function get_data_aggiornamento
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.data_aggiornamento%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data_aggiornamento
 DESCRIZIONE: Getter per attributo data_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.data_aggiornamento%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.data_aggiornamento%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_data_aggiornamento'
           );
   select data_aggiornamento
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_data_aggiornamento'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data_aggiornamento')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_data_aggiornamento'
                    );
   end if;
   return  d_result;
end get_data_aggiornamento; -- stati_territori_tpk.get_data_aggiornamento
--------------------------------------------------------------------------------
function get_sigla_iso3166_alpha2
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
) return STATI_TERRITORI.sigla_iso3166_alpha2%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_sigla_iso3166_alpha2
 DESCRIZIONE: Getter per attributo sigla_iso3166_alpha2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     STATI_TERRITORI.sigla_iso3166_alpha2%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result STATI_TERRITORI.sigla_iso3166_alpha2%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.get_sigla_iso3166_alpha2'
           );
   select sigla_iso3166_alpha2
   into   d_result
   from   STATI_TERRITORI
   where
   stato_territorio = p_stato_territorio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on stati_territori_tpk.get_sigla_iso3166_alpha2'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'sigla_iso3166_alpha2')
                    , ' AFC_DDL.IsNullable on stati_territori_tpk.get_sigla_iso3166_alpha2'
                    );
   end if;
   return  d_result;
end get_sigla_iso3166_alpha2; -- stati_territori_tpk.get_sigla_iso3166_alpha2
--------------------------------------------------------------------------------
procedure set_stato_territorio
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.stato_territorio%type default null
) is
/******************************************************************************
 NOME:        set_stato_territorio
 DESCRIZIONE: Setter per attributo stato_territorio di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_stato_territorio'
           );
   update STATI_TERRITORI
   set stato_territorio = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_stato_territorio; -- stati_territori_tpk.set_stato_territorio
--------------------------------------------------------------------------------
procedure set_denominazione
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.denominazione%type default null
) is
/******************************************************************************
 NOME:        set_denominazione
 DESCRIZIONE: Setter per attributo denominazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_denominazione'
           );
   update STATI_TERRITORI
   set denominazione = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_denominazione; -- stati_territori_tpk.set_denominazione
--------------------------------------------------------------------------------
procedure set_denominazione_al1
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.denominazione_al1%type default null
) is
/******************************************************************************
 NOME:        set_denominazione_al1
 DESCRIZIONE: Setter per attributo denominazione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_denominazione_al1'
           );
   update STATI_TERRITORI
   set denominazione_al1 = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_denominazione_al1; -- stati_territori_tpk.set_denominazione_al1
--------------------------------------------------------------------------------
procedure set_denominazione_al2
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.denominazione_al2%type default null
) is
/******************************************************************************
 NOME:        set_denominazione_al2
 DESCRIZIONE: Setter per attributo denominazione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_denominazione_al2'
           );
   update STATI_TERRITORI
   set denominazione_al2 = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_denominazione_al2; -- stati_territori_tpk.set_denominazione_al2
--------------------------------------------------------------------------------
procedure set_sigla
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.sigla%type default null
) is
/******************************************************************************
 NOME:        set_sigla
 DESCRIZIONE: Setter per attributo sigla di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_sigla'
           );
   update STATI_TERRITORI
   set sigla = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_sigla; -- stati_territori_tpk.set_sigla
--------------------------------------------------------------------------------
procedure set_desc_cittadinanza
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.desc_cittadinanza%type default null
) is
/******************************************************************************
 NOME:        set_desc_cittadinanza
 DESCRIZIONE: Setter per attributo desc_cittadinanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_desc_cittadinanza'
           );
   update STATI_TERRITORI
   set desc_cittadinanza = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_desc_cittadinanza; -- stati_territori_tpk.set_desc_cittadinanza
--------------------------------------------------------------------------------
procedure set_desc_cittadinanza_al1
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.desc_cittadinanza_al1%type default null
) is
/******************************************************************************
 NOME:        set_desc_cittadinanza_al1
 DESCRIZIONE: Setter per attributo desc_cittadinanza_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_desc_cittadinanza_al1'
           );
   update STATI_TERRITORI
   set desc_cittadinanza_al1 = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_desc_cittadinanza_al1; -- stati_territori_tpk.set_desc_cittadinanza_al1
--------------------------------------------------------------------------------
procedure set_desc_cittadinanza_al2
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.desc_cittadinanza_al2%type default null
) is
/******************************************************************************
 NOME:        set_desc_cittadinanza_al2
 DESCRIZIONE: Setter per attributo desc_cittadinanza_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_desc_cittadinanza_al2'
           );
   update STATI_TERRITORI
   set desc_cittadinanza_al2 = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_desc_cittadinanza_al2; -- stati_territori_tpk.set_desc_cittadinanza_al2
--------------------------------------------------------------------------------
procedure set_raggruppamento
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.raggruppamento%type default null
) is
/******************************************************************************
 NOME:        set_raggruppamento
 DESCRIZIONE: Setter per attributo raggruppamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_raggruppamento'
           );
   update STATI_TERRITORI
   set raggruppamento = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_raggruppamento; -- stati_territori_tpk.set_raggruppamento
--------------------------------------------------------------------------------
procedure set_stato_appartenenza
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.stato_appartenenza%type default null
) is
/******************************************************************************
 NOME:        set_stato_appartenenza
 DESCRIZIONE: Setter per attributo stato_appartenenza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_stato_appartenenza'
           );
   update STATI_TERRITORI
   set stato_appartenenza = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_stato_appartenenza; -- stati_territori_tpk.set_stato_appartenenza
--------------------------------------------------------------------------------
procedure set_utente_aggiornamento
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.utente_aggiornamento%type default null
) is
/******************************************************************************
 NOME:        set_utente_aggiornamento
 DESCRIZIONE: Setter per attributo utente_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_utente_aggiornamento'
           );
   update STATI_TERRITORI
   set utente_aggiornamento = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_utente_aggiornamento; -- stati_territori_tpk.set_utente_aggiornamento
--------------------------------------------------------------------------------
procedure set_data_aggiornamento
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.data_aggiornamento%type default null
) is
/******************************************************************************
 NOME:        set_data_aggiornamento
 DESCRIZIONE: Setter per attributo data_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_data_aggiornamento'
           );
   update STATI_TERRITORI
   set data_aggiornamento = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_data_aggiornamento; -- stati_territori_tpk.set_data_aggiornamento
--------------------------------------------------------------------------------
procedure set_sigla_iso3166_alpha2
(
  p_stato_territorio  in STATI_TERRITORI.stato_territorio%type
, p_value  in STATI_TERRITORI.sigla_iso3166_alpha2%type default null
) is
/******************************************************************************
 NOME:        set_sigla_iso3166_alpha2
 DESCRIZIONE: Setter per attributo sigla_iso3166_alpha2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_stato_territorio => p_stato_territorio
                                        )
           , 'existsId on stati_territori_tpk.set_sigla_iso3166_alpha2'
           );
   update STATI_TERRITORI
   set sigla_iso3166_alpha2 = p_value
   where
   stato_territorio = p_stato_territorio
   ;
end set_sigla_iso3166_alpha2; -- stati_territori_tpk.set_sigla_iso3166_alpha2
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_stato_territorio  in varchar2 default null
, p_denominazione  in varchar2 default null
, p_denominazione_al1  in varchar2 default null
, p_denominazione_al2  in varchar2 default null
, p_sigla  in varchar2 default null
, p_desc_cittadinanza  in varchar2 default null
, p_desc_cittadinanza_al1  in varchar2 default null
, p_desc_cittadinanza_al2  in varchar2 default null
, p_raggruppamento  in varchar2 default null
, p_stato_appartenenza  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
, p_sigla_iso3166_alpha2  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( stato_territorio ', p_stato_territorio, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione ', p_denominazione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione_al1 ', p_denominazione_al1 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione_al2 ', p_denominazione_al2 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( sigla ', p_sigla , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( desc_cittadinanza ', p_desc_cittadinanza , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( desc_cittadinanza_al1 ', p_desc_cittadinanza_al1 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( desc_cittadinanza_al2 ', p_desc_cittadinanza_al2 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( raggruppamento ', p_raggruppamento , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( stato_appartenenza ', p_stato_appartenenza , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( utente_aggiornamento ', p_utente_aggiornamento , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data_aggiornamento ', p_data_aggiornamento , ' )', p_QBE, AFC.date_format )
               || AFC.get_field_condition( ' and ( sigla_iso3166_alpha2 ', p_sigla_iso3166_alpha2 , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- stati_territori_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_stato_territorio  in varchar2 default null
, p_denominazione  in varchar2 default null
, p_denominazione_al1  in varchar2 default null
, p_denominazione_al2  in varchar2 default null
, p_sigla  in varchar2 default null
, p_desc_cittadinanza  in varchar2 default null
, p_desc_cittadinanza_al1  in varchar2 default null
, p_desc_cittadinanza_al2  in varchar2 default null
, p_raggruppamento  in varchar2 default null
, p_stato_appartenenza  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
, p_sigla_iso3166_alpha2  in varchar2 default null
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
   d_statement := ' select STATI_TERRITORI.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from STATI_TERRITORI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_stato_territorio => p_stato_territorio
                                 , p_denominazione => p_denominazione
                                 , p_denominazione_al1 => p_denominazione_al1
                                 , p_denominazione_al2 => p_denominazione_al2
                                 , p_sigla => p_sigla
                                 , p_desc_cittadinanza => p_desc_cittadinanza
                                 , p_desc_cittadinanza_al1 => p_desc_cittadinanza_al1
                                 , p_desc_cittadinanza_al2 => p_desc_cittadinanza_al2
                                 , p_raggruppamento => p_raggruppamento
                                 , p_stato_appartenenza => p_stato_appartenenza
                                 , p_utente_aggiornamento => p_utente_aggiornamento
                                 , p_data_aggiornamento => p_data_aggiornamento
                                 , p_sigla_iso3166_alpha2 => p_sigla_iso3166_alpha2
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- stati_territori_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_stato_territorio  in varchar2 default null
, p_denominazione  in varchar2 default null
, p_denominazione_al1  in varchar2 default null
, p_denominazione_al2  in varchar2 default null
, p_sigla  in varchar2 default null
, p_desc_cittadinanza  in varchar2 default null
, p_desc_cittadinanza_al1  in varchar2 default null
, p_desc_cittadinanza_al2  in varchar2 default null
, p_raggruppamento  in varchar2 default null
, p_stato_appartenenza  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
, p_sigla_iso3166_alpha2  in varchar2 default null
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
   d_statement := ' select count( * ) from STATI_TERRITORI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_stato_territorio => p_stato_territorio
                                 , p_denominazione => p_denominazione
                                 , p_denominazione_al1 => p_denominazione_al1
                                 , p_denominazione_al2 => p_denominazione_al2
                                 , p_sigla => p_sigla
                                 , p_desc_cittadinanza => p_desc_cittadinanza
                                 , p_desc_cittadinanza_al1 => p_desc_cittadinanza_al1
                                 , p_desc_cittadinanza_al2 => p_desc_cittadinanza_al2
                                 , p_raggruppamento => p_raggruppamento
                                 , p_stato_appartenenza => p_stato_appartenenza
                                 , p_utente_aggiornamento => p_utente_aggiornamento
                                 , p_data_aggiornamento => p_data_aggiornamento
                                 , p_sigla_iso3166_alpha2 => p_sigla_iso3166_alpha2
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- stati_territori_tpk.count_rows
--------------------------------------------------------------------------------
end stati_territori_tpk;
/

