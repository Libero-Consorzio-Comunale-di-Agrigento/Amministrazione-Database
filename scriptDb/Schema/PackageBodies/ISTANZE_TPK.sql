CREATE OR REPLACE PACKAGE BODY istanze_tpk is
/******************************************************************************
 NOME:        istanze_tpk
 DESCRIZIONE: Gestione tabella ISTANZE.
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
end versione; -- istanze_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_istanza  in ISTANZE.istanza%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.istanza := p_istanza;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_istanza => d_result.istanza
                                        )
           , 'canHandle on istanze_tpk.PK'
           );
   return  d_result;
end PK; -- istanze_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_istanza  in ISTANZE.istanza%type
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
          p_istanza is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on istanze_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- istanze_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_istanza  in ISTANZE.istanza%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_istanza => p_istanza
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- istanze_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_istanza  in ISTANZE.istanza%type
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
                                         p_istanza => p_istanza
                                        )
           , 'canHandle on istanze_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   ISTANZE
      where
      istanza = p_istanza
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on istanze_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- istanze_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_istanza  in ISTANZE.istanza%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_istanza => p_istanza
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- istanze_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_istanza  in ISTANZE.istanza%type
, p_progetto  in ISTANZE.progetto%type
, p_ente  in ISTANZE.ente%type
, p_descrizione  in ISTANZE.descrizione%type
, p_user_oracle  in ISTANZE.user_oracle%type
, p_password_oracle  in ISTANZE.password_oracle%type
, p_dislocazione  in ISTANZE.dislocazione%type
, p_dislocazione_temporanea  in ISTANZE.dislocazione_temporanea%type default null
, p_installazione  in ISTANZE.installazione%type default null
, p_versione  in ISTANZE.versione%type default null
, p_dislocazione_dimensionamenti  in ISTANZE.dislocazione_dimensionamenti%type default null
, p_note  in ISTANZE.note%type default null
, p_lingua  in ISTANZE.lingua%type default 'I'
, p_link_oracle  in ISTANZE.link_oracle%type default null
, p_database_link  in ISTANZE.database_link%type default null
, p_servizio  in ISTANZE.servizio%type default null
, p_database_driver  in ISTANZE.database_driver%type default null
, p_istanza_amministratore  in ISTANZE.istanza_amministratore%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_progetto is not null or /*default value*/ '' is not null
           , 'p_progetto on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ente is not null or /*default value*/ '' is not null
           , 'p_ente on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione is not null or /*default value*/ '' is not null
           , 'p_descrizione on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_user_oracle is not null or /*default value*/ '' is not null
           , 'p_user_oracle on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_password_oracle is not null or /*default value*/ '' is not null
           , 'p_password_oracle on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_dislocazione is not null or /*default value*/ '' is not null
           , 'p_dislocazione on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_dislocazione_temporanea is not null or /*default value*/ 'default' is not null
           , 'p_dislocazione_temporanea on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_installazione is not null or /*default value*/ 'default' is not null
           , 'p_installazione on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_versione is not null or /*default value*/ 'default' is not null
           , 'p_versione on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_dislocazione_dimensionamenti is not null or /*default value*/ 'default' is not null
           , 'p_dislocazione_dimensionamenti on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_lingua is not null or /*default value*/ 'default' is not null
           , 'p_lingua on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_link_oracle is not null or /*default value*/ 'default' is not null
           , 'p_link_oracle on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_database_link is not null or /*default value*/ 'default' is not null
           , 'p_database_link on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_servizio is not null or /*default value*/ 'default' is not null
           , 'p_servizio on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_database_driver is not null or /*default value*/ 'default' is not null
           , 'p_database_driver on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_istanza_amministratore is not null or /*default value*/ 'default' is not null
           , 'p_istanza_amministratore on istanze_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_istanza is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_istanza => p_istanza
                           )
           , 'not existsId on istanze_tpk.ins'
           );
   insert into ISTANZE
   (
     istanza
   , progetto
   , ente
   , descrizione
   , user_oracle
   , password_oracle
   , dislocazione
   , dislocazione_temporanea
   , installazione
   , versione
   , dislocazione_dimensionamenti
   , note
   , lingua
   , link_oracle
   , database_link
   , servizio
   , database_driver
   , istanza_amministratore
   )
   values
   (
     p_istanza
   , p_progetto
   , p_ente
   , p_descrizione
   , p_user_oracle
   , p_password_oracle
   , p_dislocazione
   , p_dislocazione_temporanea
   , p_installazione
   , p_versione
   , p_dislocazione_dimensionamenti
   , p_note
   , p_lingua
   , p_link_oracle
   , p_database_link
   , p_servizio
   , p_database_driver
   , p_istanza_amministratore
   );
end ins; -- istanze_tpk.ins
--------------------------------------------------------------------------------
function ins  /*+ SOA  */
(
  p_istanza  in ISTANZE.istanza%type
, p_progetto  in ISTANZE.progetto%type
, p_ente  in ISTANZE.ente%type
, p_descrizione  in ISTANZE.descrizione%type
, p_user_oracle  in ISTANZE.user_oracle%type
, p_password_oracle  in ISTANZE.password_oracle%type
, p_dislocazione  in ISTANZE.dislocazione%type
, p_dislocazione_temporanea  in ISTANZE.dislocazione_temporanea%type default null
, p_installazione  in ISTANZE.installazione%type default null
, p_versione  in ISTANZE.versione%type default null
, p_dislocazione_dimensionamenti  in ISTANZE.dislocazione_dimensionamenti%type default null
, p_note  in ISTANZE.note%type default null
, p_lingua  in ISTANZE.lingua%type default 'I'
, p_link_oracle  in ISTANZE.link_oracle%type default null
, p_database_link  in ISTANZE.database_link%type default null
, p_servizio  in ISTANZE.servizio%type default null
, p_database_driver  in ISTANZE.database_driver%type default null
, p_istanza_amministratore  in ISTANZE.istanza_amministratore%type default null
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
   DbC.PRE ( not DbC.PreOn or p_progetto is not null or /*default value*/ '' is not null
           , 'p_progetto on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ente is not null or /*default value*/ '' is not null
           , 'p_ente on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione is not null or /*default value*/ '' is not null
           , 'p_descrizione on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_user_oracle is not null or /*default value*/ '' is not null
           , 'p_user_oracle on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_password_oracle is not null or /*default value*/ '' is not null
           , 'p_password_oracle on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_dislocazione is not null or /*default value*/ '' is not null
           , 'p_dislocazione on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_dislocazione_temporanea is not null or /*default value*/ 'default' is not null
           , 'p_dislocazione_temporanea on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_installazione is not null or /*default value*/ 'default' is not null
           , 'p_installazione on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_versione is not null or /*default value*/ 'default' is not null
           , 'p_versione on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_dislocazione_dimensionamenti is not null or /*default value*/ 'default' is not null
           , 'p_dislocazione_dimensionamenti on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_lingua is not null or /*default value*/ 'default' is not null
           , 'p_lingua on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_link_oracle is not null or /*default value*/ 'default' is not null
           , 'p_link_oracle on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_database_link is not null or /*default value*/ 'default' is not null
           , 'p_database_link on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_servizio is not null or /*default value*/ 'default' is not null
           , 'p_servizio on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_database_driver is not null or /*default value*/ 'default' is not null
           , 'p_database_driver on istanze_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_istanza_amministratore is not null or /*default value*/ 'default' is not null
           , 'p_istanza_amministratore on istanze_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_istanza is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_istanza => p_istanza
                           )
           , 'not existsId on istanze_tpk.ins'
           );
   begin
      insert into ISTANZE
      (
        istanza
      , progetto
      , ente
      , descrizione
      , user_oracle
      , password_oracle
      , dislocazione
      , dislocazione_temporanea
      , installazione
      , versione
      , dislocazione_dimensionamenti
      , note
      , lingua
      , link_oracle
      , database_link
      , servizio
      , database_driver
      , istanza_amministratore
      )
      values
      (
        p_istanza
      , p_progetto
      , p_ente
      , p_descrizione
      , p_user_oracle
      , p_password_oracle
      , p_dislocazione
      , p_dislocazione_temporanea
      , p_installazione
      , p_versione
      , p_dislocazione_dimensionamenti
      , p_note
      , p_lingua
      , p_link_oracle
      , p_database_link
      , p_servizio
      , p_database_driver
      , p_istanza_amministratore
      );
      d_result := 0;
   exception
      when others then
         d_result := sqlcode;
   end;
   return d_result;
end ins; -- istanze_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_istanza  in ISTANZE.istanza%type
, p_OLD_istanza  in ISTANZE.istanza%type default null
, p_NEW_progetto  in ISTANZE.progetto%type default null
, p_OLD_progetto  in ISTANZE.progetto%type default null
, p_NEW_ente  in ISTANZE.ente%type default null
, p_OLD_ente  in ISTANZE.ente%type default null
, p_NEW_descrizione  in ISTANZE.descrizione%type default null
, p_OLD_descrizione  in ISTANZE.descrizione%type default null
, p_NEW_user_oracle  in ISTANZE.user_oracle%type default null
, p_OLD_user_oracle  in ISTANZE.user_oracle%type default null
, p_NEW_password_oracle  in ISTANZE.password_oracle%type default null
, p_OLD_password_oracle  in ISTANZE.password_oracle%type default null
, p_NEW_dislocazione  in ISTANZE.dislocazione%type default null
, p_OLD_dislocazione  in ISTANZE.dislocazione%type default null
, p_NEW_dislocazione_temporanea  in ISTANZE.dislocazione_temporanea%type default null
, p_OLD_dislocazione_temporanea  in ISTANZE.dislocazione_temporanea%type default null
, p_NEW_installazione  in ISTANZE.installazione%type default null
, p_OLD_installazione  in ISTANZE.installazione%type default null
, p_NEW_versione  in ISTANZE.versione%type default null
, p_OLD_versione  in ISTANZE.versione%type default null
, p_NEW_dislocazione_dimensionam  in ISTANZE.dislocazione_dimensionamenti%type default null
, p_OLD_dislocazione_dimensionam  in ISTANZE.dislocazione_dimensionamenti%type default null
, p_NEW_note  in ISTANZE.note%type default null
, p_OLD_note  in ISTANZE.note%type default null
, p_NEW_lingua  in ISTANZE.lingua%type default null
, p_OLD_lingua  in ISTANZE.lingua%type default null
, p_NEW_link_oracle  in ISTANZE.link_oracle%type default null
, p_OLD_link_oracle  in ISTANZE.link_oracle%type default null
, p_NEW_database_link  in ISTANZE.database_link%type default null
, p_OLD_database_link  in ISTANZE.database_link%type default null
, p_NEW_servizio  in ISTANZE.servizio%type default null
, p_OLD_servizio  in ISTANZE.servizio%type default null
, p_NEW_database_driver  in ISTANZE.database_driver%type default null
, p_OLD_database_driver  in ISTANZE.database_driver%type default null
, p_NEW_istanza_amministratore  in ISTANZE.istanza_amministratore%type default null
, p_OLD_istanza_amministratore  in ISTANZE.istanza_amministratore%type default null
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
p_OLD_progetto is not null or
p_OLD_ente is not null or
p_OLD_descrizione is not null or
p_OLD_user_oracle is not null or
p_OLD_password_oracle is not null or
p_OLD_dislocazione is not null or
p_OLD_dislocazione_temporanea is not null or
p_OLD_installazione is not null or
p_OLD_versione is not null or
p_OLD_dislocazione_dimensionam is not null or
p_OLD_note is not null or
p_OLD_lingua is not null or
p_OLD_link_oracle is not null or
p_OLD_database_link is not null or
p_OLD_servizio is not null or
p_OLD_database_driver is not null or
p_OLD_istanza_amministratore is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on istanze_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_istanza, p_NEW_istanza )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_istanza => d_key.istanza
                                       )
           , 'existsId on istanze_tpk.upd'
           );
   update ISTANZE
   set
       istanza = decode( p_check_OLD, 0,p_NEW_istanza, decode(p_NEW_istanza, p_OLD_istanza,istanza,p_NEW_istanza))
     , progetto = decode( p_check_OLD, 0,p_NEW_progetto, decode(p_NEW_progetto, p_OLD_progetto,progetto,p_NEW_progetto))
     , ente = decode( p_check_OLD, 0,p_NEW_ente, decode(p_NEW_ente, p_OLD_ente,ente,p_NEW_ente))
     , descrizione = decode( p_check_OLD, 0,p_NEW_descrizione, decode(p_NEW_descrizione, p_OLD_descrizione,descrizione,p_NEW_descrizione))
     , user_oracle = decode( p_check_OLD, 0,p_NEW_user_oracle, decode(p_NEW_user_oracle, p_OLD_user_oracle,user_oracle,p_NEW_user_oracle))
     , password_oracle = decode( p_check_OLD, 0,p_NEW_password_oracle, decode(p_NEW_password_oracle, p_OLD_password_oracle,password_oracle,p_NEW_password_oracle))
     , dislocazione = decode( p_check_OLD, 0,p_NEW_dislocazione, decode(p_NEW_dislocazione, p_OLD_dislocazione,dislocazione,p_NEW_dislocazione))
     , dislocazione_temporanea = decode( p_check_OLD, 0,p_NEW_dislocazione_temporanea, decode(p_NEW_dislocazione_temporanea, p_OLD_dislocazione_temporanea,dislocazione_temporanea,p_NEW_dislocazione_temporanea))
     , installazione = decode( p_check_OLD, 0,p_NEW_installazione, decode(p_NEW_installazione, p_OLD_installazione,installazione,p_NEW_installazione))
     , versione = decode( p_check_OLD, 0,p_NEW_versione, decode(p_NEW_versione, p_OLD_versione,versione,p_NEW_versione))
     , dislocazione_dimensionamenti = decode( p_check_OLD, 0,p_NEW_dislocazione_dimensionam, decode(p_NEW_dislocazione_dimensionam, p_OLD_dislocazione_dimensionam,dislocazione_dimensionamenti,p_NEW_dislocazione_dimensionam))
     , note = decode( p_check_OLD, 0,p_NEW_note, decode(p_NEW_note, p_OLD_note,note,p_NEW_note))
     , lingua = decode( p_check_OLD, 0,p_NEW_lingua, decode(p_NEW_lingua, p_OLD_lingua,lingua,p_NEW_lingua))
     , link_oracle = decode( p_check_OLD, 0,p_NEW_link_oracle, decode(p_NEW_link_oracle, p_OLD_link_oracle,link_oracle,p_NEW_link_oracle))
     , database_link = decode( p_check_OLD, 0,p_NEW_database_link, decode(p_NEW_database_link, p_OLD_database_link,database_link,p_NEW_database_link))
     , servizio = decode( p_check_OLD, 0,p_NEW_servizio, decode(p_NEW_servizio, p_OLD_servizio,servizio,p_NEW_servizio))
     , database_driver = decode( p_check_OLD, 0,p_NEW_database_driver, decode(p_NEW_database_driver, p_OLD_database_driver,database_driver,p_NEW_database_driver))
     , istanza_amministratore = decode( p_check_OLD, 0,p_NEW_istanza_amministratore, decode(p_NEW_istanza_amministratore, p_OLD_istanza_amministratore,istanza_amministratore,p_NEW_istanza_amministratore))
   where
     istanza = d_key.istanza
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( progetto = p_OLD_progetto or ( p_OLD_progetto is null and ( p_check_OLD is null or progetto is null ) ) )
           and ( ente = p_OLD_ente or ( p_OLD_ente is null and ( p_check_OLD is null or ente is null ) ) )
           and ( descrizione = p_OLD_descrizione or ( p_OLD_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( user_oracle = p_OLD_user_oracle or ( p_OLD_user_oracle is null and ( p_check_OLD is null or user_oracle is null ) ) )
           and ( password_oracle = p_OLD_password_oracle or ( p_OLD_password_oracle is null and ( p_check_OLD is null or password_oracle is null ) ) )
           and ( dislocazione = p_OLD_dislocazione or ( p_OLD_dislocazione is null and ( p_check_OLD is null or dislocazione is null ) ) )
           and ( dislocazione_temporanea = p_OLD_dislocazione_temporanea or ( p_OLD_dislocazione_temporanea is null and ( p_check_OLD is null or dislocazione_temporanea is null ) ) )
           and ( installazione = p_OLD_installazione or ( p_OLD_installazione is null and ( p_check_OLD is null or installazione is null ) ) )
           and ( versione = p_OLD_versione or ( p_OLD_versione is null and ( p_check_OLD is null or versione is null ) ) )
           and ( dislocazione_dimensionamenti = p_OLD_dislocazione_dimensionam or ( p_OLD_dislocazione_dimensionam is null and ( p_check_OLD is null or dislocazione_dimensionamenti is null ) ) )
           and ( note = p_OLD_note or ( p_OLD_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( lingua = p_OLD_lingua or ( p_OLD_lingua is null and ( p_check_OLD is null or lingua is null ) ) )
           and ( link_oracle = p_OLD_link_oracle or ( p_OLD_link_oracle is null and ( p_check_OLD is null or link_oracle is null ) ) )
           and ( database_link = p_OLD_database_link or ( p_OLD_database_link is null and ( p_check_OLD is null or database_link is null ) ) )
           and ( servizio = p_OLD_servizio or ( p_OLD_servizio is null and ( p_check_OLD is null or servizio is null ) ) )
           and ( database_driver = p_OLD_database_driver or ( p_OLD_database_driver is null and ( p_check_OLD is null or database_driver is null ) ) )
           and ( istanza_amministratore = p_OLD_istanza_amministratore or ( p_OLD_istanza_amministratore is null and ( p_check_OLD is null or istanza_amministratore is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on istanze_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- istanze_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_istanza  in ISTANZE.istanza%type
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
                                        p_istanza => p_istanza
                                       )
           , 'existsId on istanze_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on istanze_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on istanze_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on istanze_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update ISTANZE '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( istanza ', p_istanza, ' )', 0, null ), ' and ( istanza is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- istanze_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_istanza  in ISTANZE.istanza%type
, p_progetto  in ISTANZE.progetto%type default null
, p_ente  in ISTANZE.ente%type default null
, p_descrizione  in ISTANZE.descrizione%type default null
, p_user_oracle  in ISTANZE.user_oracle%type default null
, p_password_oracle  in ISTANZE.password_oracle%type default null
, p_dislocazione  in ISTANZE.dislocazione%type default null
, p_dislocazione_temporanea  in ISTANZE.dislocazione_temporanea%type default null
, p_installazione  in ISTANZE.installazione%type default null
, p_versione  in ISTANZE.versione%type default null
, p_dislocazione_dimensionamenti  in ISTANZE.dislocazione_dimensionamenti%type default null
, p_note  in ISTANZE.note%type default null
, p_lingua  in ISTANZE.lingua%type default null
, p_link_oracle  in ISTANZE.link_oracle%type default null
, p_database_link  in ISTANZE.database_link%type default null
, p_servizio  in ISTANZE.servizio%type default null
, p_database_driver  in ISTANZE.database_driver%type default null
, p_istanza_amministratore  in ISTANZE.istanza_amministratore%type default null
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
p_progetto is not null or
p_ente is not null or
p_descrizione is not null or
p_user_oracle is not null or
p_password_oracle is not null or
p_dislocazione is not null or
p_dislocazione_temporanea is not null or
p_installazione is not null or
p_versione is not null or
p_dislocazione_dimensionamenti is not null or
p_note is not null or
p_lingua is not null or
p_link_oracle is not null or
p_database_link is not null or
p_servizio is not null or
p_database_driver is not null or
p_istanza_amministratore is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on istanze_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_istanza => p_istanza
                                       )
           , 'existsId on istanze_tpk.del'
           );
   delete from ISTANZE
   where
     istanza = p_istanza
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( progetto = p_progetto or ( p_progetto is null and ( p_check_OLD is null or progetto is null ) ) )
           and ( ente = p_ente or ( p_ente is null and ( p_check_OLD is null or ente is null ) ) )
           and ( descrizione = p_descrizione or ( p_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( user_oracle = p_user_oracle or ( p_user_oracle is null and ( p_check_OLD is null or user_oracle is null ) ) )
           and ( password_oracle = p_password_oracle or ( p_password_oracle is null and ( p_check_OLD is null or password_oracle is null ) ) )
           and ( dislocazione = p_dislocazione or ( p_dislocazione is null and ( p_check_OLD is null or dislocazione is null ) ) )
           and ( dislocazione_temporanea = p_dislocazione_temporanea or ( p_dislocazione_temporanea is null and ( p_check_OLD is null or dislocazione_temporanea is null ) ) )
           and ( installazione = p_installazione or ( p_installazione is null and ( p_check_OLD is null or installazione is null ) ) )
           and ( versione = p_versione or ( p_versione is null and ( p_check_OLD is null or versione is null ) ) )
           and ( dislocazione_dimensionamenti = p_dislocazione_dimensionamenti or ( p_dislocazione_dimensionamenti is null and ( p_check_OLD is null or dislocazione_dimensionamenti is null ) ) )
           and ( note = p_note or ( p_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( lingua = p_lingua or ( p_lingua is null and ( p_check_OLD is null or lingua is null ) ) )
           and ( link_oracle = p_link_oracle or ( p_link_oracle is null and ( p_check_OLD is null or link_oracle is null ) ) )
           and ( database_link = p_database_link or ( p_database_link is null and ( p_check_OLD is null or database_link is null ) ) )
           and ( servizio = p_servizio or ( p_servizio is null and ( p_check_OLD is null or servizio is null ) ) )
           and ( database_driver = p_database_driver or ( p_database_driver is null and ( p_check_OLD is null or database_driver is null ) ) )
           and ( istanza_amministratore = p_istanza_amministratore or ( p_istanza_amministratore is null and ( p_check_OLD is null or istanza_amministratore is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on istanze_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_istanza => p_istanza
                                             )
            , 'existsId on istanze_tpk.del'
            );
end del; -- istanze_tpk.del
--------------------------------------------------------------------------------
function get_progetto
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.progetto%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_progetto
 DESCRIZIONE: Getter per attributo progetto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.progetto%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.progetto%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_progetto'
           );
   select progetto
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_progetto'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'progetto')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_progetto'
                    );
   end if;
   return  d_result;
end get_progetto; -- istanze_tpk.get_progetto
--------------------------------------------------------------------------------
function get_ente
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.ente%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_ente
 DESCRIZIONE: Getter per attributo ente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.ente%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.ente%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_ente'
           );
   select ente
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_ente'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'ente')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_ente'
                    );
   end if;
   return  d_result;
end get_ente; -- istanze_tpk.get_ente
--------------------------------------------------------------------------------
function get_descrizione
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.descrizione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione
 DESCRIZIONE: Getter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.descrizione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.descrizione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_descrizione'
           );
   select descrizione
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_descrizione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_descrizione'
                    );
   end if;
   return  d_result;
end get_descrizione; -- istanze_tpk.get_descrizione
--------------------------------------------------------------------------------
function get_user_oracle
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.user_oracle%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_user_oracle
 DESCRIZIONE: Getter per attributo user_oracle di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.user_oracle%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.user_oracle%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_user_oracle'
           );
   select user_oracle
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_user_oracle'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'user_oracle')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_user_oracle'
                    );
   end if;
   return  d_result;
end get_user_oracle; -- istanze_tpk.get_user_oracle
--------------------------------------------------------------------------------
function get_password_oracle
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.password_oracle%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_password_oracle
 DESCRIZIONE: Getter per attributo password_oracle di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.password_oracle%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.password_oracle%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_password_oracle'
           );
   select password_oracle
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_password_oracle'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'password_oracle')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_password_oracle'
                    );
   end if;
   return  d_result;
end get_password_oracle; -- istanze_tpk.get_password_oracle
--------------------------------------------------------------------------------
function get_dislocazione
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.dislocazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_dislocazione
 DESCRIZIONE: Getter per attributo dislocazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.dislocazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.dislocazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_dislocazione'
           );
   select dislocazione
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_dislocazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'dislocazione')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_dislocazione'
                    );
   end if;
   return  d_result;
end get_dislocazione; -- istanze_tpk.get_dislocazione
--------------------------------------------------------------------------------
function get_dislocazione_temporanea
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.dislocazione_temporanea%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_dislocazione_temporanea
 DESCRIZIONE: Getter per attributo dislocazione_temporanea di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.dislocazione_temporanea%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.dislocazione_temporanea%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_dislocazione_temporanea'
           );
   select dislocazione_temporanea
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_dislocazione_temporanea'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'dislocazione_temporanea')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_dislocazione_temporanea'
                    );
   end if;
   return  d_result;
end get_dislocazione_temporanea; -- istanze_tpk.get_dislocazione_temporanea
--------------------------------------------------------------------------------
function get_installazione
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.installazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_installazione
 DESCRIZIONE: Getter per attributo installazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.installazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.installazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_installazione'
           );
   select installazione
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_installazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'installazione')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_installazione'
                    );
   end if;
   return  d_result;
end get_installazione; -- istanze_tpk.get_installazione
--------------------------------------------------------------------------------
function get_versione
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.versione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_versione
 DESCRIZIONE: Getter per attributo versione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.versione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.versione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_versione'
           );
   select versione
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_versione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'versione')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_versione'
                    );
   end if;
   return  d_result;
end get_versione; -- istanze_tpk.get_versione
--------------------------------------------------------------------------------
function get_dislocazione_dimensionamen
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.dislocazione_dimensionamenti%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_dislocazione_dimensionamen
 DESCRIZIONE: Getter per attributo dislocazione_dimensionamenti di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.dislocazione_dimensionamenti%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.dislocazione_dimensionamenti%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_dislocazione_dimensionamen'
           );
   select dislocazione_dimensionamenti
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_dislocazione_dimensionamen'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'dislocazione_dimensionamenti')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_dislocazione_dimensionamen'
                    );
   end if;
   return  d_result;
end get_dislocazione_dimensionamen; -- istanze_tpk.get_dislocazione_dimensionamen
--------------------------------------------------------------------------------
function get_note
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.note%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_note
 DESCRIZIONE: Getter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.note%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.note%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_note'
           );
   select note
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_note'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'note')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_note'
                    );
   end if;
   return  d_result;
end get_note; -- istanze_tpk.get_note
--------------------------------------------------------------------------------
function get_lingua
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.lingua%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_lingua
 DESCRIZIONE: Getter per attributo lingua di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.lingua%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.lingua%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_lingua'
           );
   select lingua
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_lingua'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'lingua')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_lingua'
                    );
   end if;
   return  d_result;
end get_lingua; -- istanze_tpk.get_lingua
--------------------------------------------------------------------------------
function get_link_oracle
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.link_oracle%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_link_oracle
 DESCRIZIONE: Getter per attributo link_oracle di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.link_oracle%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.link_oracle%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_link_oracle'
           );
   select link_oracle
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_link_oracle'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'link_oracle')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_link_oracle'
                    );
   end if;
   return  d_result;
end get_link_oracle; -- istanze_tpk.get_link_oracle
--------------------------------------------------------------------------------
function get_database_link
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.database_link%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_database_link
 DESCRIZIONE: Getter per attributo database_link di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.database_link%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.database_link%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_database_link'
           );
   select database_link
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_database_link'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'database_link')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_database_link'
                    );
   end if;
   return  d_result;
end get_database_link; -- istanze_tpk.get_database_link
--------------------------------------------------------------------------------
function get_servizio
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.servizio%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_servizio
 DESCRIZIONE: Getter per attributo servizio di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.servizio%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.servizio%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_servizio'
           );
   select servizio
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_servizio'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'servizio')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_servizio'
                    );
   end if;
   return  d_result;
end get_servizio; -- istanze_tpk.get_servizio
--------------------------------------------------------------------------------
function get_database_driver
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.database_driver%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_database_driver
 DESCRIZIONE: Getter per attributo database_driver di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.database_driver%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.database_driver%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_database_driver'
           );
   select database_driver
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_database_driver'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'database_driver')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_database_driver'
                    );
   end if;
   return  d_result;
end get_database_driver; -- istanze_tpk.get_database_driver
--------------------------------------------------------------------------------
function get_istanza_amministratore
(
  p_istanza  in ISTANZE.istanza%type
) return ISTANZE.istanza_amministratore%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_istanza_amministratore
 DESCRIZIONE: Getter per attributo istanza_amministratore di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.istanza_amministratore%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result ISTANZE.istanza_amministratore%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.get_istanza_amministratore'
           );
   select istanza_amministratore
   into   d_result
   from   ISTANZE
   where
   istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on istanze_tpk.get_istanza_amministratore'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'istanza_amministratore')
                    , ' AFC_DDL.IsNullable on istanze_tpk.get_istanza_amministratore'
                    );
   end if;
   return  d_result;
end get_istanza_amministratore; -- istanze_tpk.get_istanza_amministratore
--------------------------------------------------------------------------------
procedure set_istanza
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.istanza%type default null
) is
/******************************************************************************
 NOME:        set_istanza
 DESCRIZIONE: Setter per attributo istanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_istanza'
           );
   update ISTANZE
   set istanza = p_value
   where
   istanza = p_istanza
   ;
end set_istanza; -- istanze_tpk.set_istanza
--------------------------------------------------------------------------------
procedure set_progetto
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.progetto%type default null
) is
/******************************************************************************
 NOME:        set_progetto
 DESCRIZIONE: Setter per attributo progetto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_progetto'
           );
   update ISTANZE
   set progetto = p_value
   where
   istanza = p_istanza
   ;
end set_progetto; -- istanze_tpk.set_progetto
--------------------------------------------------------------------------------
procedure set_ente
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.ente%type default null
) is
/******************************************************************************
 NOME:        set_ente
 DESCRIZIONE: Setter per attributo ente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_ente'
           );
   update ISTANZE
   set ente = p_value
   where
   istanza = p_istanza
   ;
end set_ente; -- istanze_tpk.set_ente
--------------------------------------------------------------------------------
procedure set_descrizione
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.descrizione%type default null
) is
/******************************************************************************
 NOME:        set_descrizione
 DESCRIZIONE: Setter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_descrizione'
           );
   update ISTANZE
   set descrizione = p_value
   where
   istanza = p_istanza
   ;
end set_descrizione; -- istanze_tpk.set_descrizione
--------------------------------------------------------------------------------
procedure set_user_oracle
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.user_oracle%type default null
) is
/******************************************************************************
 NOME:        set_user_oracle
 DESCRIZIONE: Setter per attributo user_oracle di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_user_oracle'
           );
   update ISTANZE
   set user_oracle = p_value
   where
   istanza = p_istanza
   ;
end set_user_oracle; -- istanze_tpk.set_user_oracle
--------------------------------------------------------------------------------
procedure set_password_oracle
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.password_oracle%type default null
) is
/******************************************************************************
 NOME:        set_password_oracle
 DESCRIZIONE: Setter per attributo password_oracle di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_password_oracle'
           );
   update ISTANZE
   set password_oracle = p_value
   where
   istanza = p_istanza
   ;
end set_password_oracle; -- istanze_tpk.set_password_oracle
--------------------------------------------------------------------------------
procedure set_dislocazione
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.dislocazione%type default null
) is
/******************************************************************************
 NOME:        set_dislocazione
 DESCRIZIONE: Setter per attributo dislocazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_dislocazione'
           );
   update ISTANZE
   set dislocazione = p_value
   where
   istanza = p_istanza
   ;
end set_dislocazione; -- istanze_tpk.set_dislocazione
--------------------------------------------------------------------------------
procedure set_dislocazione_temporanea
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.dislocazione_temporanea%type default null
) is
/******************************************************************************
 NOME:        set_dislocazione_temporanea
 DESCRIZIONE: Setter per attributo dislocazione_temporanea di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_dislocazione_temporanea'
           );
   update ISTANZE
   set dislocazione_temporanea = p_value
   where
   istanza = p_istanza
   ;
end set_dislocazione_temporanea; -- istanze_tpk.set_dislocazione_temporanea
--------------------------------------------------------------------------------
procedure set_installazione
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.installazione%type default null
) is
/******************************************************************************
 NOME:        set_installazione
 DESCRIZIONE: Setter per attributo installazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_installazione'
           );
   update ISTANZE
   set installazione = p_value
   where
   istanza = p_istanza
   ;
end set_installazione; -- istanze_tpk.set_installazione
--------------------------------------------------------------------------------
procedure set_versione
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.versione%type default null
) is
/******************************************************************************
 NOME:        set_versione
 DESCRIZIONE: Setter per attributo versione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_versione'
           );
   update ISTANZE
   set versione = p_value
   where
   istanza = p_istanza
   ;
end set_versione; -- istanze_tpk.set_versione
--------------------------------------------------------------------------------
procedure set_dislocazione_dimensionamen
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.dislocazione_dimensionamenti%type default null
) is
/******************************************************************************
 NOME:        set_dislocazione_dimensionamen
 DESCRIZIONE: Setter per attributo dislocazione_dimensionamenti di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_dislocazione_dimensionamen'
           );
   update ISTANZE
   set dislocazione_dimensionamenti = p_value
   where
   istanza = p_istanza
   ;
end set_dislocazione_dimensionamen; -- istanze_tpk.set_dislocazione_dimensionamen
--------------------------------------------------------------------------------
procedure set_note
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.note%type default null
) is
/******************************************************************************
 NOME:        set_note
 DESCRIZIONE: Setter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_note'
           );
   update ISTANZE
   set note = p_value
   where
   istanza = p_istanza
   ;
end set_note; -- istanze_tpk.set_note
--------------------------------------------------------------------------------
procedure set_lingua
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.lingua%type default null
) is
/******************************************************************************
 NOME:        set_lingua
 DESCRIZIONE: Setter per attributo lingua di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_lingua'
           );
   update ISTANZE
   set lingua = p_value
   where
   istanza = p_istanza
   ;
end set_lingua; -- istanze_tpk.set_lingua
--------------------------------------------------------------------------------
procedure set_link_oracle
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.link_oracle%type default null
) is
/******************************************************************************
 NOME:        set_link_oracle
 DESCRIZIONE: Setter per attributo link_oracle di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_link_oracle'
           );
   update ISTANZE
   set link_oracle = p_value
   where
   istanza = p_istanza
   ;
end set_link_oracle; -- istanze_tpk.set_link_oracle
--------------------------------------------------------------------------------
procedure set_database_link
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.database_link%type default null
) is
/******************************************************************************
 NOME:        set_database_link
 DESCRIZIONE: Setter per attributo database_link di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_database_link'
           );
   update ISTANZE
   set database_link = p_value
   where
   istanza = p_istanza
   ;
end set_database_link; -- istanze_tpk.set_database_link
--------------------------------------------------------------------------------
procedure set_servizio
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.servizio%type default null
) is
/******************************************************************************
 NOME:        set_servizio
 DESCRIZIONE: Setter per attributo servizio di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_servizio'
           );
   update ISTANZE
   set servizio = p_value
   where
   istanza = p_istanza
   ;
end set_servizio; -- istanze_tpk.set_servizio
--------------------------------------------------------------------------------
procedure set_database_driver
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.database_driver%type default null
) is
/******************************************************************************
 NOME:        set_database_driver
 DESCRIZIONE: Setter per attributo database_driver di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_database_driver'
           );
   update ISTANZE
   set database_driver = p_value
   where
   istanza = p_istanza
   ;
end set_database_driver; -- istanze_tpk.set_database_driver
--------------------------------------------------------------------------------
procedure set_istanza_amministratore
(
  p_istanza  in ISTANZE.istanza%type
, p_value  in ISTANZE.istanza_amministratore%type default null
) is
/******************************************************************************
 NOME:        set_istanza_amministratore
 DESCRIZIONE: Setter per attributo istanza_amministratore di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_istanza => p_istanza
                                        )
           , 'existsId on istanze_tpk.set_istanza_amministratore'
           );
   update ISTANZE
   set istanza_amministratore = p_value
   where
   istanza = p_istanza
   ;
end set_istanza_amministratore; -- istanze_tpk.set_istanza_amministratore
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_istanza  in varchar2 default null
, p_progetto  in varchar2 default null
, p_ente  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_user_oracle  in varchar2 default null
, p_password_oracle  in varchar2 default null
, p_dislocazione  in varchar2 default null
, p_dislocazione_temporanea  in varchar2 default null
, p_installazione  in varchar2 default null
, p_versione  in varchar2 default null
, p_dislocazione_dimensionamenti  in varchar2 default null
, p_note  in varchar2 default null
, p_lingua  in varchar2 default null
, p_link_oracle  in varchar2 default null
, p_database_link  in varchar2 default null
, p_servizio  in varchar2 default null
, p_database_driver  in varchar2 default null
, p_istanza_amministratore  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( istanza ', p_istanza, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( progetto ', p_progetto , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( ente ', p_ente , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione ', p_descrizione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( user_oracle ', p_user_oracle , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( password_oracle ', p_password_oracle , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( dislocazione ', p_dislocazione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( dislocazione_temporanea ', p_dislocazione_temporanea , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( installazione ', p_installazione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( versione ', p_versione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( dislocazione_dimensionamenti ', p_dislocazione_dimensionamenti , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( note ', p_note , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( lingua ', p_lingua , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( link_oracle ', p_link_oracle , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( database_link ', p_database_link , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( servizio ', p_servizio , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( database_driver ', p_database_driver , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( istanza_amministratore ', p_istanza_amministratore , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- istanze_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_istanza  in varchar2 default null
, p_progetto  in varchar2 default null
, p_ente  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_user_oracle  in varchar2 default null
, p_password_oracle  in varchar2 default null
, p_dislocazione  in varchar2 default null
, p_dislocazione_temporanea  in varchar2 default null
, p_installazione  in varchar2 default null
, p_versione  in varchar2 default null
, p_dislocazione_dimensionamenti  in varchar2 default null
, p_note  in varchar2 default null
, p_lingua  in varchar2 default null
, p_link_oracle  in varchar2 default null
, p_database_link  in varchar2 default null
, p_servizio  in varchar2 default null
, p_database_driver  in varchar2 default null
, p_istanza_amministratore  in varchar2 default null
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
   d_statement := ' select ISTANZE.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from ISTANZE '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_istanza => p_istanza
                                 , p_progetto => p_progetto
                                 , p_ente => p_ente
                                 , p_descrizione => p_descrizione
                                 , p_user_oracle => p_user_oracle
                                 , p_password_oracle => p_password_oracle
                                 , p_dislocazione => p_dislocazione
                                 , p_dislocazione_temporanea => p_dislocazione_temporanea
                                 , p_installazione => p_installazione
                                 , p_versione => p_versione
                                 , p_dislocazione_dimensionamenti => p_dislocazione_dimensionamenti
                                 , p_note => p_note
                                 , p_lingua => p_lingua
                                 , p_link_oracle => p_link_oracle
                                 , p_database_link => p_database_link
                                 , p_servizio => p_servizio
                                 , p_database_driver => p_database_driver
                                 , p_istanza_amministratore => p_istanza_amministratore
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- istanze_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_istanza  in varchar2 default null
, p_progetto  in varchar2 default null
, p_ente  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_user_oracle  in varchar2 default null
, p_password_oracle  in varchar2 default null
, p_dislocazione  in varchar2 default null
, p_dislocazione_temporanea  in varchar2 default null
, p_installazione  in varchar2 default null
, p_versione  in varchar2 default null
, p_dislocazione_dimensionamenti  in varchar2 default null
, p_note  in varchar2 default null
, p_lingua  in varchar2 default null
, p_link_oracle  in varchar2 default null
, p_database_link  in varchar2 default null
, p_servizio  in varchar2 default null
, p_database_driver  in varchar2 default null
, p_istanza_amministratore  in varchar2 default null
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
   d_statement := ' select count( * ) from ISTANZE '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_istanza => p_istanza
                                 , p_progetto => p_progetto
                                 , p_ente => p_ente
                                 , p_descrizione => p_descrizione
                                 , p_user_oracle => p_user_oracle
                                 , p_password_oracle => p_password_oracle
                                 , p_dislocazione => p_dislocazione
                                 , p_dislocazione_temporanea => p_dislocazione_temporanea
                                 , p_installazione => p_installazione
                                 , p_versione => p_versione
                                 , p_dislocazione_dimensionamenti => p_dislocazione_dimensionamenti
                                 , p_note => p_note
                                 , p_lingua => p_lingua
                                 , p_link_oracle => p_link_oracle
                                 , p_database_link => p_database_link
                                 , p_servizio => p_servizio
                                 , p_database_driver => p_database_driver
                                 , p_istanza_amministratore => p_istanza_amministratore
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- istanze_tpk.count_rows
--------------------------------------------------------------------------------
end istanze_tpk;
/

