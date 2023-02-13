--liquibase formatted sql

--changeset mturra:201901301231_160 runOnChange:true stripComments:false


create or replace package body omonimie_gestite_tpk is
/******************************************************************************
 NOME:        omonimie_gestite_tpk
 DESCRIZIONE: Gestione tabella OMONIMIE_GESTITE.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   27/02/2012  snegroni  Prima emissione.
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
end versione; -- omonimie_gestite_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.id_omonimia := p_id_omonimia;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_id_omonimia => d_result.id_omonimia
                                        )
           , 'canHandle on omonimie_gestite_tpk.PK'
           );
   return  d_result;
end PK; -- omonimie_gestite_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
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
          p_id_omonimia is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on omonimie_gestite_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- omonimie_gestite_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_id_omonimia => p_id_omonimia
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- omonimie_gestite_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
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
                                         p_id_omonimia => p_id_omonimia
                                        )
           , 'canHandle on omonimie_gestite_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   OMONIMIE_GESTITE
      where
      id_omonimia = p_id_omonimia
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on omonimie_gestite_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- omonimie_gestite_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_id_omonimia => p_id_omonimia
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- omonimie_gestite_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type default null
, p_primario  in OMONIMIE_GESTITE.primario%type default null
, p_secondario  in OMONIMIE_GESTITE.secondario%type default null
, p_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default null
, p_unificato  in OMONIMIE_GESTITE.unificato%type default 0
, p_copiato  in OMONIMIE_GESTITE.copiato%type default 0
, p_ignorare  in OMONIMIE_GESTITE.ignorare%type default 0
, p_note  in OMONIMIE_GESTITE.note%type default null
, p_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default null
, p_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default null
, p_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default null
, p_data_agg  in OMONIMIE_GESTITE.data_agg%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_primario is not null or /*default value*/ 'default' is not null
           , 'p_primario on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_secondario is not null or /*default value*/ 'default' is not null
           , 'p_secondario on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_scelto_primario is not null or /*default value*/ 'default' is not null
           , 'p_scelto_primario on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_unificato is not null or /*default value*/ 'default' is not null
           , 'p_unificato on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_copiato is not null or /*default value*/ 'default' is not null
           , 'p_copiato on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ignorare is not null or /*default value*/ 'default' is not null
           , 'p_ignorare on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_nominativo_primario is not null or /*default value*/ 'default' is not null
           , 'p_nominativo_primario on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_nominativo_secondario is not null or /*default value*/ 'default' is not null
           , 'p_nominativo_secondario on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente_agg is not null or /*default value*/ 'default' is not null
           , 'p_utente_agg on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_agg is not null or /*default value*/ 'default' is not null
           , 'p_data_agg on omonimie_gestite_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id_omonimia is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id_omonimia => p_id_omonimia
                           )
           , 'not existsId on omonimie_gestite_tpk.ins'
           );
   insert into OMONIMIE_GESTITE
   (
     id_omonimia
   , primario
   , secondario
   , scelto_primario
   , unificato
   , copiato
   , ignorare
   , note
   , nominativo_primario
   , nominativo_secondario
   , utente_agg
   , data_agg
   )
   values
   (
     p_id_omonimia
   , p_primario
   , p_secondario
   , p_scelto_primario
   , p_unificato
   , p_copiato
   , p_ignorare
   , p_note
   , p_nominativo_primario
   , p_nominativo_secondario
   , p_utente_agg
   , p_data_agg
   );
end ins; -- omonimie_gestite_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type default null
, p_primario  in OMONIMIE_GESTITE.primario%type default null
, p_secondario  in OMONIMIE_GESTITE.secondario%type default null
, p_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default null
, p_unificato  in OMONIMIE_GESTITE.unificato%type default 0
, p_copiato  in OMONIMIE_GESTITE.copiato%type default 0
, p_ignorare  in OMONIMIE_GESTITE.ignorare%type default 0
, p_note  in OMONIMIE_GESTITE.note%type default null
, p_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default null
, p_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default null
, p_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default null
, p_data_agg  in OMONIMIE_GESTITE.data_agg%type default null
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
   DbC.PRE ( not DbC.PreOn or p_primario is not null or /*default value*/ 'default' is not null
           , 'p_primario on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_secondario is not null or /*default value*/ 'default' is not null
           , 'p_secondario on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_scelto_primario is not null or /*default value*/ 'default' is not null
           , 'p_scelto_primario on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_unificato is not null or /*default value*/ 'default' is not null
           , 'p_unificato on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_copiato is not null or /*default value*/ 'default' is not null
           , 'p_copiato on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ignorare is not null or /*default value*/ 'default' is not null
           , 'p_ignorare on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_nominativo_primario is not null or /*default value*/ 'default' is not null
           , 'p_nominativo_primario on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_nominativo_secondario is not null or /*default value*/ 'default' is not null
           , 'p_nominativo_secondario on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente_agg is not null or /*default value*/ 'default' is not null
           , 'p_utente_agg on omonimie_gestite_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_agg is not null or /*default value*/ 'default' is not null
           , 'p_data_agg on omonimie_gestite_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id_omonimia is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id_omonimia => p_id_omonimia
                           )
           , 'not existsId on omonimie_gestite_tpk.ins'
           );
   begin
      insert into OMONIMIE_GESTITE
      (
        id_omonimia
      , primario
      , secondario
      , scelto_primario
      , unificato
      , copiato
      , ignorare
      , note
      , nominativo_primario
      , nominativo_secondario
      , utente_agg
      , data_agg
      )
      values
      (
        p_id_omonimia
      , p_primario
      , p_secondario
      , p_scelto_primario
      , p_unificato
      , p_copiato
      , p_ignorare
      , p_note
      , p_nominativo_primario
      , p_nominativo_secondario
      , p_utente_agg
      , p_data_agg
      ) returning id_omonimia
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
end ins; -- omonimie_gestite_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_OLD_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type default null
, p_NEW_primario  in OMONIMIE_GESTITE.primario%type default afc.default_null('OMONIMIE_GESTITE.primario')
, p_OLD_primario  in OMONIMIE_GESTITE.primario%type default null
, p_NEW_secondario  in OMONIMIE_GESTITE.secondario%type default afc.default_null('OMONIMIE_GESTITE.secondario')
, p_OLD_secondario  in OMONIMIE_GESTITE.secondario%type default null
, p_NEW_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default afc.default_null('OMONIMIE_GESTITE.scelto_primario')
, p_OLD_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default null
, p_NEW_unificato  in OMONIMIE_GESTITE.unificato%type default afc.default_null('OMONIMIE_GESTITE.unificato')
, p_OLD_unificato  in OMONIMIE_GESTITE.unificato%type default null
, p_NEW_copiato  in OMONIMIE_GESTITE.copiato%type default afc.default_null('OMONIMIE_GESTITE.copiato')
, p_OLD_copiato  in OMONIMIE_GESTITE.copiato%type default null
, p_NEW_ignorare  in OMONIMIE_GESTITE.ignorare%type default afc.default_null('OMONIMIE_GESTITE.ignorare')
, p_OLD_ignorare  in OMONIMIE_GESTITE.ignorare%type default null
, p_NEW_note  in OMONIMIE_GESTITE.note%type default afc.default_null('OMONIMIE_GESTITE.note')
, p_OLD_note  in OMONIMIE_GESTITE.note%type default null
, p_NEW_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default afc.default_null('OMONIMIE_GESTITE.nominativo_primario')
, p_OLD_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default null
, p_NEW_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default afc.default_null('OMONIMIE_GESTITE.nominativo_secondario')
, p_OLD_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default null
, p_NEW_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default afc.default_null('OMONIMIE_GESTITE.utente_agg')
, p_OLD_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default null
, p_NEW_data_agg  in OMONIMIE_GESTITE.data_agg%type default afc.default_null('OMONIMIE_GESTITE.data_agg')
, p_OLD_data_agg  in OMONIMIE_GESTITE.data_agg%type default null
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
p_OLD_primario is not null
 or p_OLD_secondario is not null
 or p_OLD_scelto_primario is not null
 or p_OLD_unificato is not null
 or p_OLD_copiato is not null
 or p_OLD_ignorare is not null
 or p_OLD_note is not null
 or p_OLD_nominativo_primario is not null
 or p_OLD_nominativo_secondario is not null
 or p_OLD_utente_agg is not null
 or p_OLD_data_agg is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on omonimie_gestite_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_id_omonimia, p_NEW_id_omonimia )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id_omonimia => d_key.id_omonimia
                                       )
           , 'existsId on omonimie_gestite_tpk.upd'
           );
   update OMONIMIE_GESTITE
   set
       id_omonimia = nvl( p_NEW_id_omonimia, decode( afc.is_default_null( 'OMONIMIE_GESTITE.id_omonimia'), 1, id_omonimia, null) )
     , primario = nvl( p_NEW_primario, decode( afc.is_default_null( 'OMONIMIE_GESTITE.primario'), 1, primario, null) )
     , secondario = nvl( p_NEW_secondario, decode( afc.is_default_null( 'OMONIMIE_GESTITE.secondario'), 1, secondario, null) )
     , scelto_primario = nvl( p_NEW_scelto_primario, decode( afc.is_default_null( 'OMONIMIE_GESTITE.scelto_primario'), 1, scelto_primario, null) )
     , unificato = nvl( p_NEW_unificato, decode( afc.is_default_null( 'OMONIMIE_GESTITE.unificato'), 1, unificato, null) )
     , copiato = nvl( p_NEW_copiato, decode( afc.is_default_null( 'OMONIMIE_GESTITE.copiato'), 1, copiato, null) )
     , ignorare = nvl( p_NEW_ignorare, decode( afc.is_default_null( 'OMONIMIE_GESTITE.ignorare'), 1, ignorare, null) )
     , note = nvl( p_NEW_note, decode( afc.is_default_null( 'OMONIMIE_GESTITE.note'), 1, note, null) )
     , nominativo_primario = nvl( p_NEW_nominativo_primario, decode( afc.is_default_null( 'OMONIMIE_GESTITE.nominativo_primario'), 1, nominativo_primario, null) )
     , nominativo_secondario = nvl( p_NEW_nominativo_secondario, decode( afc.is_default_null( 'OMONIMIE_GESTITE.nominativo_secondario'), 1, nominativo_secondario, null) )
     , utente_agg = nvl( p_NEW_utente_agg, decode( afc.is_default_null( 'OMONIMIE_GESTITE.utente_agg'), 1, utente_agg, null) )
     , data_agg = nvl( p_NEW_data_agg, decode( afc.is_default_null( 'OMONIMIE_GESTITE.data_agg'), 1, data_agg, null) )
   where
     id_omonimia = d_key.id_omonimia
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( primario = p_OLD_primario or ( p_OLD_primario is null and ( p_check_OLD is null or primario is null ) ) )
           and ( secondario = p_OLD_secondario or ( p_OLD_secondario is null and ( p_check_OLD is null or secondario is null ) ) )
           and ( scelto_primario = p_OLD_scelto_primario or ( p_OLD_scelto_primario is null and ( p_check_OLD is null or scelto_primario is null ) ) )
           and ( unificato = p_OLD_unificato or ( p_OLD_unificato is null and ( p_check_OLD is null or unificato is null ) ) )
           and ( copiato = p_OLD_copiato or ( p_OLD_copiato is null and ( p_check_OLD is null or copiato is null ) ) )
           and ( ignorare = p_OLD_ignorare or ( p_OLD_ignorare is null and ( p_check_OLD is null or ignorare is null ) ) )
           and ( note = p_OLD_note or ( p_OLD_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( nominativo_primario = p_OLD_nominativo_primario or ( p_OLD_nominativo_primario is null and ( p_check_OLD is null or nominativo_primario is null ) ) )
           and ( nominativo_secondario = p_OLD_nominativo_secondario or ( p_OLD_nominativo_secondario is null and ( p_check_OLD is null or nominativo_secondario is null ) ) )
           and ( utente_agg = p_OLD_utente_agg or ( p_OLD_utente_agg is null and ( p_check_OLD is null or utente_agg is null ) ) )
           and ( data_agg = p_OLD_data_agg or ( p_OLD_data_agg is null and ( p_check_OLD is null or data_agg is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on omonimie_gestite_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- omonimie_gestite_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
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
                                        p_id_omonimia => p_id_omonimia
                                       )
           , 'existsId on omonimie_gestite_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on omonimie_gestite_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on omonimie_gestite_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on omonimie_gestite_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update OMONIMIE_GESTITE '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( id_omonimia ', p_id_omonimia, ' )', 0, null ), ' and ( id_omonimia is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- omonimie_gestite_tpk.upd_column
--------------------------------------------------------------------------------
procedure upd_column
(
p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
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
p_id_omonimia => p_id_omonimia
              , p_column => p_column
              , p_value => 'to_date( ''' || d_data || ''', ''' || AFC.date_format || ''' )'
              , p_literal_value => 0
              );
end upd_column; -- omonimie_gestite_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_primario  in OMONIMIE_GESTITE.primario%type default null
, p_secondario  in OMONIMIE_GESTITE.secondario%type default null
, p_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default null
, p_unificato  in OMONIMIE_GESTITE.unificato%type default null
, p_copiato  in OMONIMIE_GESTITE.copiato%type default null
, p_ignorare  in OMONIMIE_GESTITE.ignorare%type default null
, p_note  in OMONIMIE_GESTITE.note%type default null
, p_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default null
, p_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default null
, p_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default null
, p_data_agg  in OMONIMIE_GESTITE.data_agg%type default null
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
p_primario is not null
 or p_secondario is not null
 or p_scelto_primario is not null
 or p_unificato is not null
 or p_copiato is not null
 or p_ignorare is not null
 or p_note is not null
 or p_nominativo_primario is not null
 or p_nominativo_secondario is not null
 or p_utente_agg is not null
 or p_data_agg is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on omonimie_gestite_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id_omonimia => p_id_omonimia
                                       )
           , 'existsId on omonimie_gestite_tpk.del'
           );
   delete from OMONIMIE_GESTITE
   where
     id_omonimia = p_id_omonimia
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( primario = p_primario or ( p_primario is null and ( p_check_OLD is null or primario is null ) ) )
           and ( secondario = p_secondario or ( p_secondario is null and ( p_check_OLD is null or secondario is null ) ) )
           and ( scelto_primario = p_scelto_primario or ( p_scelto_primario is null and ( p_check_OLD is null or scelto_primario is null ) ) )
           and ( unificato = p_unificato or ( p_unificato is null and ( p_check_OLD is null or unificato is null ) ) )
           and ( copiato = p_copiato or ( p_copiato is null and ( p_check_OLD is null or copiato is null ) ) )
           and ( ignorare = p_ignorare or ( p_ignorare is null and ( p_check_OLD is null or ignorare is null ) ) )
           and ( note = p_note or ( p_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( nominativo_primario = p_nominativo_primario or ( p_nominativo_primario is null and ( p_check_OLD is null or nominativo_primario is null ) ) )
           and ( nominativo_secondario = p_nominativo_secondario or ( p_nominativo_secondario is null and ( p_check_OLD is null or nominativo_secondario is null ) ) )
           and ( utente_agg = p_utente_agg or ( p_utente_agg is null and ( p_check_OLD is null or utente_agg is null ) ) )
           and ( data_agg = p_data_agg or ( p_data_agg is null and ( p_check_OLD is null or data_agg is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on omonimie_gestite_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_id_omonimia => p_id_omonimia
                                             )
            , 'existsId on omonimie_gestite_tpk.del'
            );
end del; -- omonimie_gestite_tpk.del
--------------------------------------------------------------------------------
function get_primario
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return OMONIMIE_GESTITE.primario%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_primario
 DESCRIZIONE: Getter per attributo primario di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     OMONIMIE_GESTITE.primario%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result OMONIMIE_GESTITE.primario%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.get_primario'
           );
   select primario
   into   d_result
   from   OMONIMIE_GESTITE
   where
   id_omonimia = p_id_omonimia
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on omonimie_gestite_tpk.get_primario'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'primario')
                    , ' AFC_DDL.IsNullable on omonimie_gestite_tpk.get_primario'
                    );
   end if;
   return  d_result;
end get_primario; -- omonimie_gestite_tpk.get_primario
--------------------------------------------------------------------------------
function get_secondario
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return OMONIMIE_GESTITE.secondario%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_secondario
 DESCRIZIONE: Getter per attributo secondario di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     OMONIMIE_GESTITE.secondario%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result OMONIMIE_GESTITE.secondario%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.get_secondario'
           );
   select secondario
   into   d_result
   from   OMONIMIE_GESTITE
   where
   id_omonimia = p_id_omonimia
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on omonimie_gestite_tpk.get_secondario'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'secondario')
                    , ' AFC_DDL.IsNullable on omonimie_gestite_tpk.get_secondario'
                    );
   end if;
   return  d_result;
end get_secondario; -- omonimie_gestite_tpk.get_secondario
--------------------------------------------------------------------------------
function get_scelto_primario
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return OMONIMIE_GESTITE.scelto_primario%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_scelto_primario
 DESCRIZIONE: Getter per attributo scelto_primario di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     OMONIMIE_GESTITE.scelto_primario%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result OMONIMIE_GESTITE.scelto_primario%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.get_scelto_primario'
           );
   select scelto_primario
   into   d_result
   from   OMONIMIE_GESTITE
   where
   id_omonimia = p_id_omonimia
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on omonimie_gestite_tpk.get_scelto_primario'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'scelto_primario')
                    , ' AFC_DDL.IsNullable on omonimie_gestite_tpk.get_scelto_primario'
                    );
   end if;
   return  d_result;
end get_scelto_primario; -- omonimie_gestite_tpk.get_scelto_primario
--------------------------------------------------------------------------------
function get_unificato
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return OMONIMIE_GESTITE.unificato%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_unificato
 DESCRIZIONE: Getter per attributo unificato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     OMONIMIE_GESTITE.unificato%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result OMONIMIE_GESTITE.unificato%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.get_unificato'
           );
   select unificato
   into   d_result
   from   OMONIMIE_GESTITE
   where
   id_omonimia = p_id_omonimia
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on omonimie_gestite_tpk.get_unificato'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'unificato')
                    , ' AFC_DDL.IsNullable on omonimie_gestite_tpk.get_unificato'
                    );
   end if;
   return  d_result;
end get_unificato; -- omonimie_gestite_tpk.get_unificato
--------------------------------------------------------------------------------
function get_copiato
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return OMONIMIE_GESTITE.copiato%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_copiato
 DESCRIZIONE: Getter per attributo copiato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     OMONIMIE_GESTITE.copiato%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result OMONIMIE_GESTITE.copiato%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.get_copiato'
           );
   select copiato
   into   d_result
   from   OMONIMIE_GESTITE
   where
   id_omonimia = p_id_omonimia
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on omonimie_gestite_tpk.get_copiato'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'copiato')
                    , ' AFC_DDL.IsNullable on omonimie_gestite_tpk.get_copiato'
                    );
   end if;
   return  d_result;
end get_copiato; -- omonimie_gestite_tpk.get_copiato
--------------------------------------------------------------------------------
function get_ignorare
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return OMONIMIE_GESTITE.ignorare%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_ignorare
 DESCRIZIONE: Getter per attributo ignorare di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     OMONIMIE_GESTITE.ignorare%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result OMONIMIE_GESTITE.ignorare%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.get_ignorare'
           );
   select ignorare
   into   d_result
   from   OMONIMIE_GESTITE
   where
   id_omonimia = p_id_omonimia
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on omonimie_gestite_tpk.get_ignorare'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'ignorare')
                    , ' AFC_DDL.IsNullable on omonimie_gestite_tpk.get_ignorare'
                    );
   end if;
   return  d_result;
end get_ignorare; -- omonimie_gestite_tpk.get_ignorare
--------------------------------------------------------------------------------
function get_note
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return OMONIMIE_GESTITE.note%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_note
 DESCRIZIONE: Getter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     OMONIMIE_GESTITE.note%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result OMONIMIE_GESTITE.note%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.get_note'
           );
   select note
   into   d_result
   from   OMONIMIE_GESTITE
   where
   id_omonimia = p_id_omonimia
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on omonimie_gestite_tpk.get_note'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'note')
                    , ' AFC_DDL.IsNullable on omonimie_gestite_tpk.get_note'
                    );
   end if;
   return  d_result;
end get_note; -- omonimie_gestite_tpk.get_note
--------------------------------------------------------------------------------
function get_nominativo_primario
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return OMONIMIE_GESTITE.nominativo_primario%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_nominativo_primario
 DESCRIZIONE: Getter per attributo nominativo_primario di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     OMONIMIE_GESTITE.nominativo_primario%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result OMONIMIE_GESTITE.nominativo_primario%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.get_nominativo_primario'
           );
   select nominativo_primario
   into   d_result
   from   OMONIMIE_GESTITE
   where
   id_omonimia = p_id_omonimia
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on omonimie_gestite_tpk.get_nominativo_primario'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'nominativo_primario')
                    , ' AFC_DDL.IsNullable on omonimie_gestite_tpk.get_nominativo_primario'
                    );
   end if;
   return  d_result;
end get_nominativo_primario; -- omonimie_gestite_tpk.get_nominativo_primario
--------------------------------------------------------------------------------
function get_nominativo_secondario
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return OMONIMIE_GESTITE.nominativo_secondario%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_nominativo_secondario
 DESCRIZIONE: Getter per attributo nominativo_secondario di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     OMONIMIE_GESTITE.nominativo_secondario%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result OMONIMIE_GESTITE.nominativo_secondario%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.get_nominativo_secondario'
           );
   select nominativo_secondario
   into   d_result
   from   OMONIMIE_GESTITE
   where
   id_omonimia = p_id_omonimia
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on omonimie_gestite_tpk.get_nominativo_secondario'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'nominativo_secondario')
                    , ' AFC_DDL.IsNullable on omonimie_gestite_tpk.get_nominativo_secondario'
                    );
   end if;
   return  d_result;
end get_nominativo_secondario; -- omonimie_gestite_tpk.get_nominativo_secondario
--------------------------------------------------------------------------------
function get_utente_agg
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return OMONIMIE_GESTITE.utente_agg%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_utente_agg
 DESCRIZIONE: Getter per attributo utente_agg di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     OMONIMIE_GESTITE.utente_agg%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result OMONIMIE_GESTITE.utente_agg%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.get_utente_agg'
           );
   select utente_agg
   into   d_result
   from   OMONIMIE_GESTITE
   where
   id_omonimia = p_id_omonimia
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on omonimie_gestite_tpk.get_utente_agg'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'utente_agg')
                    , ' AFC_DDL.IsNullable on omonimie_gestite_tpk.get_utente_agg'
                    );
   end if;
   return  d_result;
end get_utente_agg; -- omonimie_gestite_tpk.get_utente_agg
--------------------------------------------------------------------------------
function get_data_agg
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
) return OMONIMIE_GESTITE.data_agg%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data_agg
 DESCRIZIONE: Getter per attributo data_agg di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     OMONIMIE_GESTITE.data_agg%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result OMONIMIE_GESTITE.data_agg%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.get_data_agg'
           );
   select data_agg
   into   d_result
   from   OMONIMIE_GESTITE
   where
   id_omonimia = p_id_omonimia
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on omonimie_gestite_tpk.get_data_agg'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data_agg')
                    , ' AFC_DDL.IsNullable on omonimie_gestite_tpk.get_data_agg'
                    );
   end if;
   return  d_result;
end get_data_agg; -- omonimie_gestite_tpk.get_data_agg
--------------------------------------------------------------------------------
procedure set_id_omonimia
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.id_omonimia%type default null
) is
/******************************************************************************
 NOME:        set_id_omonimia
 DESCRIZIONE: Setter per attributo id_omonimia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_id_omonimia'
           );
   update OMONIMIE_GESTITE
   set id_omonimia = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_id_omonimia; -- omonimie_gestite_tpk.set_id_omonimia
--------------------------------------------------------------------------------
procedure set_primario
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.primario%type default null
) is
/******************************************************************************
 NOME:        set_primario
 DESCRIZIONE: Setter per attributo primario di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_primario'
           );
   update OMONIMIE_GESTITE
   set primario = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_primario; -- omonimie_gestite_tpk.set_primario
--------------------------------------------------------------------------------
procedure set_secondario
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.secondario%type default null
) is
/******************************************************************************
 NOME:        set_secondario
 DESCRIZIONE: Setter per attributo secondario di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_secondario'
           );
   update OMONIMIE_GESTITE
   set secondario = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_secondario; -- omonimie_gestite_tpk.set_secondario
--------------------------------------------------------------------------------
procedure set_scelto_primario
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.scelto_primario%type default null
) is
/******************************************************************************
 NOME:        set_scelto_primario
 DESCRIZIONE: Setter per attributo scelto_primario di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_scelto_primario'
           );
   update OMONIMIE_GESTITE
   set scelto_primario = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_scelto_primario; -- omonimie_gestite_tpk.set_scelto_primario
--------------------------------------------------------------------------------
procedure set_unificato
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.unificato%type default null
) is
/******************************************************************************
 NOME:        set_unificato
 DESCRIZIONE: Setter per attributo unificato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_unificato'
           );
   update OMONIMIE_GESTITE
   set unificato = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_unificato; -- omonimie_gestite_tpk.set_unificato
--------------------------------------------------------------------------------
procedure set_copiato
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.copiato%type default null
) is
/******************************************************************************
 NOME:        set_copiato
 DESCRIZIONE: Setter per attributo copiato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_copiato'
           );
   update OMONIMIE_GESTITE
   set copiato = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_copiato; -- omonimie_gestite_tpk.set_copiato
--------------------------------------------------------------------------------
procedure set_ignorare
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.ignorare%type default null
) is
/******************************************************************************
 NOME:        set_ignorare
 DESCRIZIONE: Setter per attributo ignorare di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_ignorare'
           );
   update OMONIMIE_GESTITE
   set ignorare = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_ignorare; -- omonimie_gestite_tpk.set_ignorare
--------------------------------------------------------------------------------
procedure set_note
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.note%type default null
) is
/******************************************************************************
 NOME:        set_note
 DESCRIZIONE: Setter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_note'
           );
   update OMONIMIE_GESTITE
   set note = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_note; -- omonimie_gestite_tpk.set_note
--------------------------------------------------------------------------------
procedure set_nominativo_primario
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.nominativo_primario%type default null
) is
/******************************************************************************
 NOME:        set_nominativo_primario
 DESCRIZIONE: Setter per attributo nominativo_primario di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_nominativo_primario'
           );
   update OMONIMIE_GESTITE
   set nominativo_primario = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_nominativo_primario; -- omonimie_gestite_tpk.set_nominativo_primario
--------------------------------------------------------------------------------
procedure set_nominativo_secondario
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.nominativo_secondario%type default null
) is
/******************************************************************************
 NOME:        set_nominativo_secondario
 DESCRIZIONE: Setter per attributo nominativo_secondario di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_nominativo_secondario'
           );
   update OMONIMIE_GESTITE
   set nominativo_secondario = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_nominativo_secondario; -- omonimie_gestite_tpk.set_nominativo_secondario
--------------------------------------------------------------------------------
procedure set_utente_agg
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.utente_agg%type default null
) is
/******************************************************************************
 NOME:        set_utente_agg
 DESCRIZIONE: Setter per attributo utente_agg di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_utente_agg'
           );
   update OMONIMIE_GESTITE
   set utente_agg = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_utente_agg; -- omonimie_gestite_tpk.set_utente_agg
--------------------------------------------------------------------------------
procedure set_data_agg
(
  p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type
, p_value  in OMONIMIE_GESTITE.data_agg%type default null
) is
/******************************************************************************
 NOME:        set_data_agg
 DESCRIZIONE: Setter per attributo data_agg di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_omonimia => p_id_omonimia
                                        )
           , 'existsId on omonimie_gestite_tpk.set_data_agg'
           );
   update OMONIMIE_GESTITE
   set data_agg = p_value
   where
   id_omonimia = p_id_omonimia
   ;
end set_data_agg; -- omonimie_gestite_tpk.set_data_agg
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id_omonimia  in varchar2 default null
, p_primario  in varchar2 default null
, p_secondario  in varchar2 default null
, p_scelto_primario  in varchar2 default null
, p_unificato  in varchar2 default null
, p_copiato  in varchar2 default null
, p_ignorare  in varchar2 default null
, p_note  in varchar2 default null
, p_nominativo_primario  in varchar2 default null
, p_nominativo_secondario  in varchar2 default null
, p_utente_agg  in varchar2 default null
, p_data_agg  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( id_omonimia ', p_id_omonimia, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( primario ', p_primario , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( secondario ', p_secondario , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( scelto_primario ', p_scelto_primario , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( unificato ', p_unificato , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( copiato ', p_copiato , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( ignorare ', p_ignorare , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( note ', p_note , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( nominativo_primario ', p_nominativo_primario , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( nominativo_secondario ', p_nominativo_secondario , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( utente_agg ', p_utente_agg , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data_agg ', p_data_agg , ' )', p_QBE, AFC.date_format )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- omonimie_gestite_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_id_omonimia  in varchar2 default null
, p_primario  in varchar2 default null
, p_secondario  in varchar2 default null
, p_scelto_primario  in varchar2 default null
, p_unificato  in varchar2 default null
, p_copiato  in varchar2 default null
, p_ignorare  in varchar2 default null
, p_note  in varchar2 default null
, p_nominativo_primario  in varchar2 default null
, p_nominativo_secondario  in varchar2 default null
, p_utente_agg  in varchar2 default null
, p_data_agg  in varchar2 default null
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
   d_statement := ' select OMONIMIE_GESTITE.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from OMONIMIE_GESTITE '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id_omonimia => p_id_omonimia
                                 , p_primario => p_primario
                                 , p_secondario => p_secondario
                                 , p_scelto_primario => p_scelto_primario
                                 , p_unificato => p_unificato
                                 , p_copiato => p_copiato
                                 , p_ignorare => p_ignorare
                                 , p_note => p_note
                                 , p_nominativo_primario => p_nominativo_primario
                                 , p_nominativo_secondario => p_nominativo_secondario
                                 , p_utente_agg => p_utente_agg
                                 , p_data_agg => p_data_agg
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- omonimie_gestite_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id_omonimia  in varchar2 default null
, p_primario  in varchar2 default null
, p_secondario  in varchar2 default null
, p_scelto_primario  in varchar2 default null
, p_unificato  in varchar2 default null
, p_copiato  in varchar2 default null
, p_ignorare  in varchar2 default null
, p_note  in varchar2 default null
, p_nominativo_primario  in varchar2 default null
, p_nominativo_secondario  in varchar2 default null
, p_utente_agg  in varchar2 default null
, p_data_agg  in varchar2 default null
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
   d_statement := ' select count( * ) from OMONIMIE_GESTITE '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id_omonimia => p_id_omonimia
                                 , p_primario => p_primario
                                 , p_secondario => p_secondario
                                 , p_scelto_primario => p_scelto_primario
                                 , p_unificato => p_unificato
                                 , p_copiato => p_copiato
                                 , p_ignorare => p_ignorare
                                 , p_note => p_note
                                 , p_nominativo_primario => p_nominativo_primario
                                 , p_nominativo_secondario => p_nominativo_secondario
                                 , p_utente_agg => p_utente_agg
                                 , p_data_agg => p_data_agg
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- omonimie_gestite_tpk.count_rows
--------------------------------------------------------------------------------
end omonimie_gestite_tpk;
/

