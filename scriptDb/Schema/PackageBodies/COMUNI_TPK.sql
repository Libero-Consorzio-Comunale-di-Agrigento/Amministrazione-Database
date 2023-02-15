CREATE OR REPLACE package body comuni_tpk is
/******************************************************************************
 NOME:        comuni_tpk
 DESCRIZIONE: Gestione tabella COMUNI.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore      Descrizione.
 000   01/10/2010  snegroni  Prima emissione.
 001   10/02/2020  SNegroni  Generazione automatica.
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '001 - 10/02/2020';
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
end versione; -- comuni_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.provincia_stato := p_provincia_stato;
d_result.comune := p_comune;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_provincia_stato => d_result.provincia_stato,
p_comune => d_result.comune
                                        )
           , 'canHandle on comuni_tpk.PK'
           );
   return  d_result;
end PK; -- comuni_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
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
          p_provincia_stato is null
or p_comune is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on comuni_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- comuni_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_provincia_stato => p_provincia_stato
,p_comune => p_comune
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- comuni_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
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
                                         p_provincia_stato => p_provincia_stato
,p_comune => p_comune
                                        )
           , 'canHandle on comuni_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   COMUNI
      where
      provincia_stato = p_provincia_stato
and comune = p_comune
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on comuni_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- comuni_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_provincia_stato => p_provincia_stato
,p_comune => p_comune
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- comuni_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_provincia_stato  in COMUNI.provincia_stato%type default null
,p_comune  in COMUNI.comune%type default null
, p_denominazione  in COMUNI.denominazione%type default null
, p_denominazione_al1  in COMUNI.denominazione_al1%type default null
, p_denominazione_al2  in COMUNI.denominazione_al2%type default null
, p_denominazione_breve  in COMUNI.denominazione_breve%type default null
, p_denominazione_breve_al1  in COMUNI.denominazione_breve_al1%type default null
, p_denominazione_breve_al2  in COMUNI.denominazione_breve_al2%type default null
, p_capoluogo_provincia  in COMUNI.capoluogo_provincia%type default null
, p_cap  in COMUNI.cap%type default null
, p_provincia_tribunale  in COMUNI.provincia_tribunale%type default null
, p_comune_tribunale  in COMUNI.comune_tribunale%type default null
, p_provincia_distretto  in COMUNI.provincia_distretto%type default null
, p_comune_distretto  in COMUNI.comune_distretto%type default null
, p_data_soppressione  in COMUNI.data_soppressione%type default null
, p_provincia_fusione  in COMUNI.provincia_fusione%type default null
, p_comune_fusione  in COMUNI.comune_fusione%type default null
, p_sigla_cfis  in COMUNI.sigla_cfis%type default null
, p_consolato  in COMUNI.consolato%type default null
, p_tipo_consolato  in COMUNI.tipo_consolato%type default null
, p_utente_aggiornamento  in COMUNI.utente_aggiornamento%type default null
, p_data_aggiornamento  in COMUNI.data_aggiornamento%type default null
, p_data_istituzione  in COMUNI.data_istituzione%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert

   DbC.PRE ( not DbC.PreOn or p_denominazione is not null or /*default value*/ 'default' is not null
           , 'p_denominazione on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al1 on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al2 on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_breve is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_breve on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_breve_al1 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_breve_al1 on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_breve_al2 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_breve_al2 on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_capoluogo_provincia is not null or /*default value*/ 'default' is not null
           , 'p_capoluogo_provincia on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_cap is not null or /*default value*/ 'default' is not null
           , 'p_cap on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_provincia_tribunale is not null or /*default value*/ 'default' is not null
           , 'p_provincia_tribunale on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_comune_tribunale is not null or /*default value*/ 'default' is not null
           , 'p_comune_tribunale on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_provincia_distretto is not null or /*default value*/ 'default' is not null
           , 'p_provincia_distretto on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_comune_distretto is not null or /*default value*/ 'default' is not null
           , 'p_comune_distretto on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_soppressione is not null or /*default value*/ 'default' is not null
           , 'p_data_soppressione on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_provincia_fusione is not null or /*default value*/ 'default' is not null
           , 'p_provincia_fusione on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_comune_fusione is not null or /*default value*/ 'default' is not null
           , 'p_comune_fusione on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_sigla_cfis is not null or /*default value*/ 'default' is not null
           , 'p_sigla_cfis on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_consolato is not null or /*default value*/ 'default' is not null
           , 'p_consolato on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_tipo_consolato is not null or /*default value*/ 'default' is not null
           , 'p_tipo_consolato on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_utente_aggiornamento on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_data_aggiornamento on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_istituzione is not null or /*default value*/ 'default' is not null
           , 'p_data_istituzione on comuni_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_provincia_stato is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
or (   p_comune is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_provincia_stato => p_provincia_stato
,p_comune => p_comune
                           )
           , 'not existsId on comuni_tpk.ins'
           );
   insert into COMUNI
   (
     provincia_stato
,comune
   , denominazione
   , denominazione_al1
   , denominazione_al2
   , denominazione_breve
   , denominazione_breve_al1
   , denominazione_breve_al2
   , capoluogo_provincia
   , cap
   , provincia_tribunale
   , comune_tribunale
   , provincia_distretto
   , comune_distretto
   , data_soppressione
   , provincia_fusione
   , comune_fusione
   , sigla_cfis
   , consolato
   , tipo_consolato
   , utente_aggiornamento
   , data_aggiornamento
   , data_istituzione
   )
   values
   (
     p_provincia_stato
,p_comune
, p_denominazione
, p_denominazione_al1
, p_denominazione_al2
, p_denominazione_breve
, p_denominazione_breve_al1
, p_denominazione_breve_al2
, p_capoluogo_provincia
, p_cap
, p_provincia_tribunale
, p_comune_tribunale
, p_provincia_distretto
, p_comune_distretto
, p_data_soppressione
, p_provincia_fusione
, p_comune_fusione
, p_sigla_cfis
, p_consolato
, p_tipo_consolato
, p_utente_aggiornamento
, p_data_aggiornamento
, p_data_istituzione
   );
end ins; -- comuni_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_provincia_stato  in COMUNI.provincia_stato%type default null
,p_comune  in COMUNI.comune%type default null
, p_denominazione  in COMUNI.denominazione%type default null
, p_denominazione_al1  in COMUNI.denominazione_al1%type default null
, p_denominazione_al2  in COMUNI.denominazione_al2%type default null
, p_denominazione_breve  in COMUNI.denominazione_breve%type default null
, p_denominazione_breve_al1  in COMUNI.denominazione_breve_al1%type default null
, p_denominazione_breve_al2  in COMUNI.denominazione_breve_al2%type default null
, p_capoluogo_provincia  in COMUNI.capoluogo_provincia%type default null
, p_cap  in COMUNI.cap%type default null
, p_provincia_tribunale  in COMUNI.provincia_tribunale%type default null
, p_comune_tribunale  in COMUNI.comune_tribunale%type default null
, p_provincia_distretto  in COMUNI.provincia_distretto%type default null
, p_comune_distretto  in COMUNI.comune_distretto%type default null
, p_data_soppressione  in COMUNI.data_soppressione%type default null
, p_provincia_fusione  in COMUNI.provincia_fusione%type default null
, p_comune_fusione  in COMUNI.comune_fusione%type default null
, p_sigla_cfis  in COMUNI.sigla_cfis%type default null
, p_consolato  in COMUNI.consolato%type default null
, p_tipo_consolato  in COMUNI.tipo_consolato%type default null
, p_utente_aggiornamento  in COMUNI.utente_aggiornamento%type default null
, p_data_aggiornamento  in COMUNI.data_aggiornamento%type default null
, p_data_istituzione  in COMUNI.data_istituzione%type default null
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
           , 'p_denominazione on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al1 on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_al2 on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_breve is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_breve on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_breve_al1 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_breve_al1 on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_denominazione_breve_al2 is not null or /*default value*/ 'default' is not null
           , 'p_denominazione_breve_al2 on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_capoluogo_provincia is not null or /*default value*/ 'default' is not null
           , 'p_capoluogo_provincia on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_cap is not null or /*default value*/ 'default' is not null
           , 'p_cap on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_provincia_tribunale is not null or /*default value*/ 'default' is not null
           , 'p_provincia_tribunale on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_comune_tribunale is not null or /*default value*/ 'default' is not null
           , 'p_comune_tribunale on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_provincia_distretto is not null or /*default value*/ 'default' is not null
           , 'p_provincia_distretto on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_comune_distretto is not null or /*default value*/ 'default' is not null
           , 'p_comune_distretto on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_soppressione is not null or /*default value*/ 'default' is not null
           , 'p_data_soppressione on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_provincia_fusione is not null or /*default value*/ 'default' is not null
           , 'p_provincia_fusione on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_comune_fusione is not null or /*default value*/ 'default' is not null
           , 'p_comune_fusione on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_sigla_cfis is not null or /*default value*/ 'default' is not null
           , 'p_sigla_cfis on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_consolato is not null or /*default value*/ 'default' is not null
           , 'p_consolato on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_tipo_consolato is not null or /*default value*/ 'default' is not null
           , 'p_tipo_consolato on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_utente_aggiornamento on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_aggiornamento is not null or /*default value*/ 'default' is not null
           , 'p_data_aggiornamento on comuni_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_data_istituzione is not null or /*default value*/ 'default' is not null
           , 'p_data_istituzione on comuni_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_provincia_stato is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
or (   p_comune is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_provincia_stato => p_provincia_stato
,p_comune => p_comune
                           )
           , 'not existsId on comuni_tpk.ins'
           );
   insert into COMUNI
   (
     provincia_stato
,comune
   , denominazione
   , denominazione_al1
   , denominazione_al2
   , denominazione_breve
   , denominazione_breve_al1
   , denominazione_breve_al2
   , capoluogo_provincia
   , cap
   , provincia_tribunale
   , comune_tribunale
   , provincia_distretto
   , comune_distretto
   , data_soppressione
   , provincia_fusione
   , comune_fusione
   , sigla_cfis
   , consolato
   , tipo_consolato
   , utente_aggiornamento
   , data_aggiornamento
   , data_istituzione
   )
   values
   (
     p_provincia_stato
,p_comune
, p_denominazione
, p_denominazione_al1
, p_denominazione_al2
, p_denominazione_breve
, p_denominazione_breve_al1
, p_denominazione_breve_al2
, p_capoluogo_provincia
, p_cap
, p_provincia_tribunale
, p_comune_tribunale
, p_provincia_distretto
, p_comune_distretto
, p_data_soppressione
, p_provincia_fusione
, p_comune_fusione
, p_sigla_cfis
, p_consolato
, p_tipo_consolato
, p_utente_aggiornamento
, p_data_aggiornamento
, p_data_istituzione
   );
   d_result := 0;
   return d_result;
end ins; -- comuni_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_provincia_stato  in COMUNI.provincia_stato%type
, p_OLD_provincia_stato  in COMUNI.provincia_stato%type default null
, p_NEW_comune  in COMUNI.comune%type
, p_OLD_comune  in COMUNI.comune%type default null
, p_NEW_denominazione  in COMUNI.denominazione%type default afc.default_null('COMUNI.denominazione')
, p_OLD_denominazione  in COMUNI.denominazione%type default null
, p_NEW_denominazione_al1  in COMUNI.denominazione_al1%type default afc.default_null('COMUNI.denominazione_al1')
, p_OLD_denominazione_al1  in COMUNI.denominazione_al1%type default null
, p_NEW_denominazione_al2  in COMUNI.denominazione_al2%type default afc.default_null('COMUNI.denominazione_al2')
, p_OLD_denominazione_al2  in COMUNI.denominazione_al2%type default null
, p_NEW_denominazione_breve  in COMUNI.denominazione_breve%type default afc.default_null('COMUNI.denominazione_breve')
, p_OLD_denominazione_breve  in COMUNI.denominazione_breve%type default null
, p_NEW_denominazione_breve_al1  in COMUNI.denominazione_breve_al1%type default afc.default_null('COMUNI.denominazione_breve_al1')
, p_OLD_denominazione_breve_al1  in COMUNI.denominazione_breve_al1%type default null
, p_NEW_denominazione_breve_al2  in COMUNI.denominazione_breve_al2%type default afc.default_null('COMUNI.denominazione_breve_al2')
, p_OLD_denominazione_breve_al2  in COMUNI.denominazione_breve_al2%type default null
, p_NEW_capoluogo_provincia  in COMUNI.capoluogo_provincia%type default afc.default_null('COMUNI.capoluogo_provincia')
, p_OLD_capoluogo_provincia  in COMUNI.capoluogo_provincia%type default null
, p_NEW_cap  in COMUNI.cap%type default afc.default_null('COMUNI.cap')
, p_OLD_cap  in COMUNI.cap%type default null
, p_NEW_provincia_tribunale  in COMUNI.provincia_tribunale%type default afc.default_null('COMUNI.provincia_tribunale')
, p_OLD_provincia_tribunale  in COMUNI.provincia_tribunale%type default null
, p_NEW_comune_tribunale  in COMUNI.comune_tribunale%type default afc.default_null('COMUNI.comune_tribunale')
, p_OLD_comune_tribunale  in COMUNI.comune_tribunale%type default null
, p_NEW_provincia_distretto  in COMUNI.provincia_distretto%type default afc.default_null('COMUNI.provincia_distretto')
, p_OLD_provincia_distretto  in COMUNI.provincia_distretto%type default null
, p_NEW_comune_distretto  in COMUNI.comune_distretto%type default afc.default_null('COMUNI.comune_distretto')
, p_OLD_comune_distretto  in COMUNI.comune_distretto%type default null
, p_NEW_data_soppressione  in COMUNI.data_soppressione%type default afc.default_null('COMUNI.data_soppressione')
, p_OLD_data_soppressione  in COMUNI.data_soppressione%type default null
, p_NEW_provincia_fusione  in COMUNI.provincia_fusione%type default afc.default_null('COMUNI.provincia_fusione')
, p_OLD_provincia_fusione  in COMUNI.provincia_fusione%type default null
, p_NEW_comune_fusione  in COMUNI.comune_fusione%type default afc.default_null('COMUNI.comune_fusione')
, p_OLD_comune_fusione  in COMUNI.comune_fusione%type default null
, p_NEW_sigla_cfis  in COMUNI.sigla_cfis%type default afc.default_null('COMUNI.sigla_cfis')
, p_OLD_sigla_cfis  in COMUNI.sigla_cfis%type default null
, p_NEW_consolato  in COMUNI.consolato%type default afc.default_null('COMUNI.consolato')
, p_OLD_consolato  in COMUNI.consolato%type default null
, p_NEW_tipo_consolato  in COMUNI.tipo_consolato%type default afc.default_null('COMUNI.tipo_consolato')
, p_OLD_tipo_consolato  in COMUNI.tipo_consolato%type default null
, p_NEW_utente_aggiornamento  in COMUNI.utente_aggiornamento%type default afc.default_null('COMUNI.utente_aggiornamento')
, p_OLD_utente_aggiornamento  in COMUNI.utente_aggiornamento%type default null
, p_NEW_data_aggiornamento  in COMUNI.data_aggiornamento%type default afc.default_null('COMUNI.data_aggiornamento')
, p_OLD_data_aggiornamento  in COMUNI.data_aggiornamento%type default null
, p_NEW_data_istituzione  in COMUNI.data_istituzione%type default afc.default_null('COMUNI.data_istituzione')
, p_OLD_data_istituzione  in COMUNI.data_istituzione%type default null
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
              Se p_check_old e NULL, gli attributi vengono annullati solo se viene
              indicato anche il relativo attributo OLD.
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
p_OLD_denominazione is not null
 or p_OLD_denominazione_al1 is not null
 or p_OLD_denominazione_al2 is not null
 or p_OLD_denominazione_breve is not null
 or p_OLD_denominazione_breve_al1 is not null
 or p_OLD_denominazione_breve_al2 is not null
 or p_OLD_capoluogo_provincia is not null
 or p_OLD_cap is not null
 or p_OLD_provincia_tribunale is not null
 or p_OLD_comune_tribunale is not null
 or p_OLD_provincia_distretto is not null
 or p_OLD_comune_distretto is not null
 or p_OLD_data_soppressione is not null
 or p_OLD_provincia_fusione is not null
 or p_OLD_comune_fusione is not null
 or p_OLD_sigla_cfis is not null
 or p_OLD_consolato is not null
 or p_OLD_tipo_consolato is not null
 or p_OLD_utente_aggiornamento is not null
 or p_OLD_data_aggiornamento is not null
 or p_OLD_data_istituzione is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on comuni_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_provincia_stato, p_NEW_provincia_stato )
,nvl( p_OLD_comune, p_NEW_comune )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_provincia_stato => d_key.provincia_stato
,p_comune => d_key.comune
                                       )
           , 'existsId on comuni_tpk.upd'
           );
   update COMUNI
   set
       provincia_stato = NVL( p_NEW_provincia_stato, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.provincia_stato' ), 1, provincia_stato,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_provincia_stato, null, provincia_stato, null ) ) ) )
,comune = NVL( p_NEW_comune, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.comune' ), 1, comune,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_comune, null, comune, null ) ) ) )
     , denominazione = NVL( p_NEW_denominazione, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.denominazione' ), 1, denominazione,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_denominazione, null, denominazione, null ) ) ) )
     , denominazione_al1 = NVL( p_NEW_denominazione_al1, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.denominazione_al1' ), 1, denominazione_al1,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_denominazione_al1, null, denominazione_al1, null ) ) ) )
     , denominazione_al2 = NVL( p_NEW_denominazione_al2, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.denominazione_al2' ), 1, denominazione_al2,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_denominazione_al2, null, denominazione_al2, null ) ) ) )
     , denominazione_breve = NVL( p_NEW_denominazione_breve, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.denominazione_breve' ), 1, denominazione_breve,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_denominazione_breve, null, denominazione_breve, null ) ) ) )
     , denominazione_breve_al1 = NVL( p_NEW_denominazione_breve_al1, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.denominazione_breve_al1' ), 1, denominazione_breve_al1,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_denominazione_breve_al1, null, denominazione_breve_al1, null ) ) ) )
     , denominazione_breve_al2 = NVL( p_NEW_denominazione_breve_al2, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.denominazione_breve_al2' ), 1, denominazione_breve_al2,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_denominazione_breve_al2, null, denominazione_breve_al2, null ) ) ) )
     , capoluogo_provincia = NVL( p_NEW_capoluogo_provincia, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.capoluogo_provincia' ), 1, capoluogo_provincia,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_capoluogo_provincia, null, capoluogo_provincia, null ) ) ) )
     , cap = NVL( p_NEW_cap, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.cap' ), 1, cap,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_cap, null, cap, null ) ) ) )
     , provincia_tribunale = NVL( p_NEW_provincia_tribunale, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.provincia_tribunale' ), 1, provincia_tribunale,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_provincia_tribunale, null, provincia_tribunale, null ) ) ) )
     , comune_tribunale = NVL( p_NEW_comune_tribunale, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.comune_tribunale' ), 1, comune_tribunale,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_comune_tribunale, null, comune_tribunale, null ) ) ) )
     , provincia_distretto = NVL( p_NEW_provincia_distretto, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.provincia_distretto' ), 1, provincia_distretto,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_provincia_distretto, null, provincia_distretto, null ) ) ) )
     , comune_distretto = NVL( p_NEW_comune_distretto, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.comune_distretto' ), 1, comune_distretto,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_comune_distretto, null, comune_distretto, null ) ) ) )
     , data_soppressione = NVL( p_NEW_data_soppressione, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.data_soppressione' ), 1, data_soppressione,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_data_soppressione, null, data_soppressione, null ) ) ) )
     , provincia_fusione = NVL( p_NEW_provincia_fusione, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.provincia_fusione' ), 1, provincia_fusione,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_provincia_fusione, null, provincia_fusione, null ) ) ) )
     , comune_fusione = NVL( p_NEW_comune_fusione, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.comune_fusione' ), 1, comune_fusione,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_comune_fusione, null, comune_fusione, null ) ) ) )
     , sigla_cfis = NVL( p_NEW_sigla_cfis, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.sigla_cfis' ), 1, sigla_cfis,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_sigla_cfis, null, sigla_cfis, null ) ) ) )
     , consolato = NVL( p_NEW_consolato, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.consolato' ), 1, consolato,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_consolato, null, consolato, null ) ) ) )
     , tipo_consolato = NVL( p_NEW_tipo_consolato, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.tipo_consolato' ), 1, tipo_consolato,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_tipo_consolato, null, tipo_consolato, null ) ) ) )
     , utente_aggiornamento = NVL( p_NEW_utente_aggiornamento, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.utente_aggiornamento' ), 1, utente_aggiornamento,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_utente_aggiornamento, null, utente_aggiornamento, null ) ) ) )
     , data_aggiornamento = NVL( p_NEW_data_aggiornamento, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.data_aggiornamento' ), 1, data_aggiornamento,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_data_aggiornamento, null, data_aggiornamento, null ) ) ) )
     , data_istituzione = NVL( p_NEW_data_istituzione, DECODE( AFC.IS_DEFAULT_NULL( 'COMUNI.data_istituzione' ), 1, data_istituzione,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_data_istituzione, null, data_istituzione, null ) ) ) )
   where
     provincia_stato = d_key.provincia_stato
and comune = d_key.comune
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( denominazione = p_OLD_denominazione or ( p_OLD_denominazione is null and ( p_check_OLD is null or denominazione is null ) ) )
           and ( denominazione_al1 = p_OLD_denominazione_al1 or ( p_OLD_denominazione_al1 is null and ( p_check_OLD is null or denominazione_al1 is null ) ) )
           and ( denominazione_al2 = p_OLD_denominazione_al2 or ( p_OLD_denominazione_al2 is null and ( p_check_OLD is null or denominazione_al2 is null ) ) )
           and ( denominazione_breve = p_OLD_denominazione_breve or ( p_OLD_denominazione_breve is null and ( p_check_OLD is null or denominazione_breve is null ) ) )
           and ( denominazione_breve_al1 = p_OLD_denominazione_breve_al1 or ( p_OLD_denominazione_breve_al1 is null and ( p_check_OLD is null or denominazione_breve_al1 is null ) ) )
           and ( denominazione_breve_al2 = p_OLD_denominazione_breve_al2 or ( p_OLD_denominazione_breve_al2 is null and ( p_check_OLD is null or denominazione_breve_al2 is null ) ) )
           and ( capoluogo_provincia = p_OLD_capoluogo_provincia or ( p_OLD_capoluogo_provincia is null and ( p_check_OLD is null or capoluogo_provincia is null ) ) )
           and ( cap = p_OLD_cap or ( p_OLD_cap is null and ( p_check_OLD is null or cap is null ) ) )
           and ( provincia_tribunale = p_OLD_provincia_tribunale or ( p_OLD_provincia_tribunale is null and ( p_check_OLD is null or provincia_tribunale is null ) ) )
           and ( comune_tribunale = p_OLD_comune_tribunale or ( p_OLD_comune_tribunale is null and ( p_check_OLD is null or comune_tribunale is null ) ) )
           and ( provincia_distretto = p_OLD_provincia_distretto or ( p_OLD_provincia_distretto is null and ( p_check_OLD is null or provincia_distretto is null ) ) )
           and ( comune_distretto = p_OLD_comune_distretto or ( p_OLD_comune_distretto is null and ( p_check_OLD is null or comune_distretto is null ) ) )
           and ( data_soppressione = p_OLD_data_soppressione or ( p_OLD_data_soppressione is null and ( p_check_OLD is null or data_soppressione is null ) ) )
           and ( provincia_fusione = p_OLD_provincia_fusione or ( p_OLD_provincia_fusione is null and ( p_check_OLD is null or provincia_fusione is null ) ) )
           and ( comune_fusione = p_OLD_comune_fusione or ( p_OLD_comune_fusione is null and ( p_check_OLD is null or comune_fusione is null ) ) )
           and ( sigla_cfis = p_OLD_sigla_cfis or ( p_OLD_sigla_cfis is null and ( p_check_OLD is null or sigla_cfis is null ) ) )
           and ( consolato = p_OLD_consolato or ( p_OLD_consolato is null and ( p_check_OLD is null or consolato is null ) ) )
           and ( tipo_consolato = p_OLD_tipo_consolato or ( p_OLD_tipo_consolato is null and ( p_check_OLD is null or tipo_consolato is null ) ) )
           and ( utente_aggiornamento = p_OLD_utente_aggiornamento or ( p_OLD_utente_aggiornamento is null and ( p_check_OLD is null or utente_aggiornamento is null ) ) )
           and ( data_aggiornamento = p_OLD_data_aggiornamento or ( p_OLD_data_aggiornamento is null and ( p_check_OLD is null or data_aggiornamento is null ) ) )
           and ( data_istituzione = p_OLD_data_istituzione or ( p_OLD_data_istituzione is null and ( p_check_OLD is null or data_istituzione is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on comuni_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- comuni_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
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
                                        p_provincia_stato => p_provincia_stato,
p_comune => p_comune
                                       )
           , 'existsId on comuni_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on comuni_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on comuni_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on comuni_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update COMUNI '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( provincia_stato ', p_provincia_stato, ' )', 0, null ), ' and ( provincia_stato is null ) ' )
 || nvl( AFC.get_field_condition( ' and ( comune ', p_comune, ' )', 0, null ), ' and ( comune is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- comuni_tpk.upd_column
--------------------------------------------------------------------------------
procedure upd_column
(
p_provincia_stato  in COMUNI.provincia_stato%type,
p_comune  in COMUNI.comune%type
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
p_provincia_stato => p_provincia_stato
,p_comune => p_comune
              , p_column => p_column
              , p_value => 'to_date( ''' || d_data || ''', ''' || AFC.date_format || ''' )'
              , p_literal_value => 0
              );
end upd_column; -- comuni_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_provincia_stato  in COMUNI.provincia_stato%type
, p_comune  in COMUNI.comune%type
, p_denominazione  in COMUNI.denominazione%type default null
, p_denominazione_al1  in COMUNI.denominazione_al1%type default null
, p_denominazione_al2  in COMUNI.denominazione_al2%type default null
, p_denominazione_breve  in COMUNI.denominazione_breve%type default null
, p_denominazione_breve_al1  in COMUNI.denominazione_breve_al1%type default null
, p_denominazione_breve_al2  in COMUNI.denominazione_breve_al2%type default null
, p_capoluogo_provincia  in COMUNI.capoluogo_provincia%type default null
, p_cap  in COMUNI.cap%type default null
, p_provincia_tribunale  in COMUNI.provincia_tribunale%type default null
, p_comune_tribunale  in COMUNI.comune_tribunale%type default null
, p_provincia_distretto  in COMUNI.provincia_distretto%type default null
, p_comune_distretto  in COMUNI.comune_distretto%type default null
, p_data_soppressione  in COMUNI.data_soppressione%type default null
, p_provincia_fusione  in COMUNI.provincia_fusione%type default null
, p_comune_fusione  in COMUNI.comune_fusione%type default null
, p_sigla_cfis  in COMUNI.sigla_cfis%type default null
, p_consolato  in COMUNI.consolato%type default null
, p_tipo_consolato  in COMUNI.tipo_consolato%type default null
, p_utente_aggiornamento  in COMUNI.utente_aggiornamento%type default null
, p_data_aggiornamento  in COMUNI.data_aggiornamento%type default null
, p_data_istituzione  in COMUNI.data_istituzione%type default null
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
p_denominazione is not null
 or p_denominazione_al1 is not null
 or p_denominazione_al2 is not null
 or p_denominazione_breve is not null
 or p_denominazione_breve_al1 is not null
 or p_denominazione_breve_al2 is not null
 or p_capoluogo_provincia is not null
 or p_cap is not null
 or p_provincia_tribunale is not null
 or p_comune_tribunale is not null
 or p_provincia_distretto is not null
 or p_comune_distretto is not null
 or p_data_soppressione is not null
 or p_provincia_fusione is not null
 or p_comune_fusione is not null
 or p_sigla_cfis is not null
 or p_consolato is not null
 or p_tipo_consolato is not null
 or p_utente_aggiornamento is not null
 or p_data_aggiornamento is not null
 or p_data_istituzione is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on comuni_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_provincia_stato => p_provincia_stato,
p_comune => p_comune
                                       )
           , 'existsId on comuni_tpk.del'
           );
   delete from COMUNI
   where
     provincia_stato = p_provincia_stato and
comune = p_comune
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( denominazione = p_denominazione or ( p_denominazione is null and ( p_check_OLD is null or denominazione is null ) ) )
           and ( denominazione_al1 = p_denominazione_al1 or ( p_denominazione_al1 is null and ( p_check_OLD is null or denominazione_al1 is null ) ) )
           and ( denominazione_al2 = p_denominazione_al2 or ( p_denominazione_al2 is null and ( p_check_OLD is null or denominazione_al2 is null ) ) )
           and ( denominazione_breve = p_denominazione_breve or ( p_denominazione_breve is null and ( p_check_OLD is null or denominazione_breve is null ) ) )
           and ( denominazione_breve_al1 = p_denominazione_breve_al1 or ( p_denominazione_breve_al1 is null and ( p_check_OLD is null or denominazione_breve_al1 is null ) ) )
           and ( denominazione_breve_al2 = p_denominazione_breve_al2 or ( p_denominazione_breve_al2 is null and ( p_check_OLD is null or denominazione_breve_al2 is null ) ) )
           and ( capoluogo_provincia = p_capoluogo_provincia or ( p_capoluogo_provincia is null and ( p_check_OLD is null or capoluogo_provincia is null ) ) )
           and ( cap = p_cap or ( p_cap is null and ( p_check_OLD is null or cap is null ) ) )
           and ( provincia_tribunale = p_provincia_tribunale or ( p_provincia_tribunale is null and ( p_check_OLD is null or provincia_tribunale is null ) ) )
           and ( comune_tribunale = p_comune_tribunale or ( p_comune_tribunale is null and ( p_check_OLD is null or comune_tribunale is null ) ) )
           and ( provincia_distretto = p_provincia_distretto or ( p_provincia_distretto is null and ( p_check_OLD is null or provincia_distretto is null ) ) )
           and ( comune_distretto = p_comune_distretto or ( p_comune_distretto is null and ( p_check_OLD is null or comune_distretto is null ) ) )
           and ( data_soppressione = p_data_soppressione or ( p_data_soppressione is null and ( p_check_OLD is null or data_soppressione is null ) ) )
           and ( provincia_fusione = p_provincia_fusione or ( p_provincia_fusione is null and ( p_check_OLD is null or provincia_fusione is null ) ) )
           and ( comune_fusione = p_comune_fusione or ( p_comune_fusione is null and ( p_check_OLD is null or comune_fusione is null ) ) )
           and ( sigla_cfis = p_sigla_cfis or ( p_sigla_cfis is null and ( p_check_OLD is null or sigla_cfis is null ) ) )
           and ( consolato = p_consolato or ( p_consolato is null and ( p_check_OLD is null or consolato is null ) ) )
           and ( tipo_consolato = p_tipo_consolato or ( p_tipo_consolato is null and ( p_check_OLD is null or tipo_consolato is null ) ) )
           and ( utente_aggiornamento = p_utente_aggiornamento or ( p_utente_aggiornamento is null and ( p_check_OLD is null or utente_aggiornamento is null ) ) )
           and ( data_aggiornamento = p_data_aggiornamento or ( p_data_aggiornamento is null and ( p_check_OLD is null or data_aggiornamento is null ) ) )
           and ( data_istituzione = p_data_istituzione or ( p_data_istituzione is null and ( p_check_OLD is null or data_istituzione is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on comuni_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_provincia_stato => p_provincia_stato,
p_comune => p_comune
                                             )
            , 'existsId on comuni_tpk.del'
            );
end del; -- comuni_tpk.del
--------------------------------------------------------------------------------
function get_denominazione
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.denominazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione
 DESCRIZIONE: Getter per attributo denominazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.denominazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.denominazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_denominazione'
           );
   select denominazione
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_denominazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_denominazione'
                    );
   end if;
   return  d_result;
end get_denominazione; -- comuni_tpk.get_denominazione
--------------------------------------------------------------------------------
function get_denominazione_al1
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.denominazione_al1%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione_al1
 DESCRIZIONE: Getter per attributo denominazione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.denominazione_al1%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.denominazione_al1%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_denominazione_al1'
           );
   select denominazione_al1
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_denominazione_al1'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione_al1')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_denominazione_al1'
                    );
   end if;
   return  d_result;
end get_denominazione_al1; -- comuni_tpk.get_denominazione_al1
--------------------------------------------------------------------------------
function get_denominazione_al2
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.denominazione_al2%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione_al2
 DESCRIZIONE: Getter per attributo denominazione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.denominazione_al2%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.denominazione_al2%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_denominazione_al2'
           );
   select denominazione_al2
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_denominazione_al2'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione_al2')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_denominazione_al2'
                    );
   end if;
   return  d_result;
end get_denominazione_al2; -- comuni_tpk.get_denominazione_al2
--------------------------------------------------------------------------------
function get_denominazione_breve
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.denominazione_breve%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione_breve
 DESCRIZIONE: Getter per attributo denominazione_breve di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.denominazione_breve%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.denominazione_breve%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_denominazione_breve'
           );
   select denominazione_breve
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_denominazione_breve'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione_breve')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_denominazione_breve'
                    );
   end if;
   return  d_result;
end get_denominazione_breve; -- comuni_tpk.get_denominazione_breve
--------------------------------------------------------------------------------
function get_denominazione_breve_al1
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.denominazione_breve_al1%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione_breve_al1
 DESCRIZIONE: Getter per attributo denominazione_breve_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.denominazione_breve_al1%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.denominazione_breve_al1%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_denominazione_breve_al1'
           );
   select denominazione_breve_al1
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_denominazione_breve_al1'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione_breve_al1')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_denominazione_breve_al1'
                    );
   end if;
   return  d_result;
end get_denominazione_breve_al1; -- comuni_tpk.get_denominazione_breve_al1
--------------------------------------------------------------------------------
function get_denominazione_breve_al2
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.denominazione_breve_al2%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_denominazione_breve_al2
 DESCRIZIONE: Getter per attributo denominazione_breve_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.denominazione_breve_al2%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.denominazione_breve_al2%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_denominazione_breve_al2'
           );
   select denominazione_breve_al2
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_denominazione_breve_al2'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'denominazione_breve_al2')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_denominazione_breve_al2'
                    );
   end if;
   return  d_result;
end get_denominazione_breve_al2; -- comuni_tpk.get_denominazione_breve_al2
--------------------------------------------------------------------------------
function get_capoluogo_provincia
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.capoluogo_provincia%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_capoluogo_provincia
 DESCRIZIONE: Getter per attributo capoluogo_provincia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.capoluogo_provincia%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.capoluogo_provincia%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_capoluogo_provincia'
           );
   select capoluogo_provincia
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_capoluogo_provincia'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'capoluogo_provincia')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_capoluogo_provincia'
                    );
   end if;
   return  d_result;
end get_capoluogo_provincia; -- comuni_tpk.get_capoluogo_provincia
--------------------------------------------------------------------------------
function get_cap
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.cap%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_cap
 DESCRIZIONE: Getter per attributo cap di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.cap%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.cap%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_cap'
           );
   select cap
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_cap'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'cap')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_cap'
                    );
   end if;
   return  d_result;
end get_cap; -- comuni_tpk.get_cap
--------------------------------------------------------------------------------
function get_provincia_tribunale
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.provincia_tribunale%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_provincia_tribunale
 DESCRIZIONE: Getter per attributo provincia_tribunale di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.provincia_tribunale%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.provincia_tribunale%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_provincia_tribunale'
           );
   select provincia_tribunale
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_provincia_tribunale'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'provincia_tribunale')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_provincia_tribunale'
                    );
   end if;
   return  d_result;
end get_provincia_tribunale; -- comuni_tpk.get_provincia_tribunale
--------------------------------------------------------------------------------
function get_comune_tribunale
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.comune_tribunale%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_comune_tribunale
 DESCRIZIONE: Getter per attributo comune_tribunale di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.comune_tribunale%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.comune_tribunale%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_comune_tribunale'
           );
   select comune_tribunale
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_comune_tribunale'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'comune_tribunale')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_comune_tribunale'
                    );
   end if;
   return  d_result;
end get_comune_tribunale; -- comuni_tpk.get_comune_tribunale
--------------------------------------------------------------------------------
function get_provincia_distretto
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.provincia_distretto%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_provincia_distretto
 DESCRIZIONE: Getter per attributo provincia_distretto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.provincia_distretto%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.provincia_distretto%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_provincia_distretto'
           );
   select provincia_distretto
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_provincia_distretto'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'provincia_distretto')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_provincia_distretto'
                    );
   end if;
   return  d_result;
end get_provincia_distretto; -- comuni_tpk.get_provincia_distretto
--------------------------------------------------------------------------------
function get_comune_distretto
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.comune_distretto%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_comune_distretto
 DESCRIZIONE: Getter per attributo comune_distretto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.comune_distretto%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.comune_distretto%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_comune_distretto'
           );
   select comune_distretto
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_comune_distretto'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'comune_distretto')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_comune_distretto'
                    );
   end if;
   return  d_result;
end get_comune_distretto; -- comuni_tpk.get_comune_distretto
--------------------------------------------------------------------------------
function get_data_soppressione
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.data_soppressione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data_soppressione
 DESCRIZIONE: Getter per attributo data_soppressione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.data_soppressione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.data_soppressione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_data_soppressione'
           );
   select data_soppressione
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_data_soppressione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data_soppressione')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_data_soppressione'
                    );
   end if;
   return  d_result;
end get_data_soppressione; -- comuni_tpk.get_data_soppressione
--------------------------------------------------------------------------------
function get_provincia_fusione
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.provincia_fusione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_provincia_fusione
 DESCRIZIONE: Getter per attributo provincia_fusione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.provincia_fusione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.provincia_fusione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_provincia_fusione'
           );
   select provincia_fusione
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_provincia_fusione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'provincia_fusione')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_provincia_fusione'
                    );
   end if;
   return  d_result;
end get_provincia_fusione; -- comuni_tpk.get_provincia_fusione
--------------------------------------------------------------------------------
function get_comune_fusione
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.comune_fusione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_comune_fusione
 DESCRIZIONE: Getter per attributo comune_fusione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.comune_fusione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.comune_fusione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_comune_fusione'
           );
   select comune_fusione
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_comune_fusione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'comune_fusione')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_comune_fusione'
                    );
   end if;
   return  d_result;
end get_comune_fusione; -- comuni_tpk.get_comune_fusione
--------------------------------------------------------------------------------
function get_sigla_cfis
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.sigla_cfis%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_sigla_cfis
 DESCRIZIONE: Getter per attributo sigla_cfis di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.sigla_cfis%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.sigla_cfis%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_sigla_cfis'
           );
   select sigla_cfis
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_sigla_cfis'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'sigla_cfis')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_sigla_cfis'
                    );
   end if;
   return  d_result;
end get_sigla_cfis; -- comuni_tpk.get_sigla_cfis
--------------------------------------------------------------------------------
function get_consolato
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.consolato%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_consolato
 DESCRIZIONE: Getter per attributo consolato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.consolato%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.consolato%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_consolato'
           );
   select consolato
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_consolato'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'consolato')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_consolato'
                    );
   end if;
   return  d_result;
end get_consolato; -- comuni_tpk.get_consolato
--------------------------------------------------------------------------------
function get_tipo_consolato
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.tipo_consolato%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_tipo_consolato
 DESCRIZIONE: Getter per attributo tipo_consolato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.tipo_consolato%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.tipo_consolato%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_tipo_consolato'
           );
   select tipo_consolato
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_tipo_consolato'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'tipo_consolato')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_tipo_consolato'
                    );
   end if;
   return  d_result;
end get_tipo_consolato; -- comuni_tpk.get_tipo_consolato
--------------------------------------------------------------------------------
function get_utente_aggiornamento
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.utente_aggiornamento%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_utente_aggiornamento
 DESCRIZIONE: Getter per attributo utente_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.utente_aggiornamento%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.utente_aggiornamento%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_utente_aggiornamento'
           );
   select utente_aggiornamento
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_utente_aggiornamento'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'utente_aggiornamento')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_utente_aggiornamento'
                    );
   end if;
   return  d_result;
end get_utente_aggiornamento; -- comuni_tpk.get_utente_aggiornamento
--------------------------------------------------------------------------------
function get_data_aggiornamento
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.data_aggiornamento%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data_aggiornamento
 DESCRIZIONE: Getter per attributo data_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.data_aggiornamento%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.data_aggiornamento%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_data_aggiornamento'
           );
   select data_aggiornamento
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_data_aggiornamento'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data_aggiornamento')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_data_aggiornamento'
                    );
   end if;
   return  d_result;
end get_data_aggiornamento; -- comuni_tpk.get_data_aggiornamento
--------------------------------------------------------------------------------
function get_data_istituzione
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
) return COMUNI.data_istituzione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_data_istituzione
 DESCRIZIONE: Getter per attributo data_istituzione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     COMUNI.data_istituzione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result COMUNI.data_istituzione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.get_data_istituzione'
           );
   select data_istituzione
   into   d_result
   from   COMUNI
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on comuni_tpk.get_data_istituzione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'data_istituzione')
                    , ' AFC_DDL.IsNullable on comuni_tpk.get_data_istituzione'
                    );
   end if;
   return  d_result;
end get_data_istituzione; -- comuni_tpk.get_data_istituzione
--------------------------------------------------------------------------------
procedure set_provincia_stato
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.provincia_stato%type default null
) is
/******************************************************************************
 NOME:        set_provincia_stato
 DESCRIZIONE: Setter per attributo provincia_stato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_provincia_stato'
           );
   update COMUNI
   set provincia_stato = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_provincia_stato; -- comuni_tpk.set_provincia_stato
--------------------------------------------------------------------------------
procedure set_comune
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.comune%type default null
) is
/******************************************************************************
 NOME:        set_comune
 DESCRIZIONE: Setter per attributo comune di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_comune'
           );
   update COMUNI
   set comune = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_comune; -- comuni_tpk.set_comune
--------------------------------------------------------------------------------
procedure set_denominazione
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.denominazione%type default null
) is
/******************************************************************************
 NOME:        set_denominazione
 DESCRIZIONE: Setter per attributo denominazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_denominazione'
           );
   update COMUNI
   set denominazione = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_denominazione; -- comuni_tpk.set_denominazione
--------------------------------------------------------------------------------
procedure set_denominazione_al1
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.denominazione_al1%type default null
) is
/******************************************************************************
 NOME:        set_denominazione_al1
 DESCRIZIONE: Setter per attributo denominazione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_denominazione_al1'
           );
   update COMUNI
   set denominazione_al1 = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_denominazione_al1; -- comuni_tpk.set_denominazione_al1
--------------------------------------------------------------------------------
procedure set_denominazione_al2
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.denominazione_al2%type default null
) is
/******************************************************************************
 NOME:        set_denominazione_al2
 DESCRIZIONE: Setter per attributo denominazione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_denominazione_al2'
           );
   update COMUNI
   set denominazione_al2 = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_denominazione_al2; -- comuni_tpk.set_denominazione_al2
--------------------------------------------------------------------------------
procedure set_denominazione_breve
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.denominazione_breve%type default null
) is
/******************************************************************************
 NOME:        set_denominazione_breve
 DESCRIZIONE: Setter per attributo denominazione_breve di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_denominazione_breve'
           );
   update COMUNI
   set denominazione_breve = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_denominazione_breve; -- comuni_tpk.set_denominazione_breve
--------------------------------------------------------------------------------
procedure set_denominazione_breve_al1
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.denominazione_breve_al1%type default null
) is
/******************************************************************************
 NOME:        set_denominazione_breve_al1
 DESCRIZIONE: Setter per attributo denominazione_breve_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_denominazione_breve_al1'
           );
   update COMUNI
   set denominazione_breve_al1 = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_denominazione_breve_al1; -- comuni_tpk.set_denominazione_breve_al1
--------------------------------------------------------------------------------
procedure set_denominazione_breve_al2
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.denominazione_breve_al2%type default null
) is
/******************************************************************************
 NOME:        set_denominazione_breve_al2
 DESCRIZIONE: Setter per attributo denominazione_breve_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_denominazione_breve_al2'
           );
   update COMUNI
   set denominazione_breve_al2 = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_denominazione_breve_al2; -- comuni_tpk.set_denominazione_breve_al2
--------------------------------------------------------------------------------
procedure set_capoluogo_provincia
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.capoluogo_provincia%type default null
) is
/******************************************************************************
 NOME:        set_capoluogo_provincia
 DESCRIZIONE: Setter per attributo capoluogo_provincia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_capoluogo_provincia'
           );
   update COMUNI
   set capoluogo_provincia = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_capoluogo_provincia; -- comuni_tpk.set_capoluogo_provincia
--------------------------------------------------------------------------------
procedure set_cap
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.cap%type default null
) is
/******************************************************************************
 NOME:        set_cap
 DESCRIZIONE: Setter per attributo cap di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_cap'
           );
   update COMUNI
   set cap = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_cap; -- comuni_tpk.set_cap
--------------------------------------------------------------------------------
procedure set_provincia_tribunale
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.provincia_tribunale%type default null
) is
/******************************************************************************
 NOME:        set_provincia_tribunale
 DESCRIZIONE: Setter per attributo provincia_tribunale di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_provincia_tribunale'
           );
   update COMUNI
   set provincia_tribunale = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_provincia_tribunale; -- comuni_tpk.set_provincia_tribunale
--------------------------------------------------------------------------------
procedure set_comune_tribunale
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.comune_tribunale%type default null
) is
/******************************************************************************
 NOME:        set_comune_tribunale
 DESCRIZIONE: Setter per attributo comune_tribunale di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_comune_tribunale'
           );
   update COMUNI
   set comune_tribunale = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_comune_tribunale; -- comuni_tpk.set_comune_tribunale
--------------------------------------------------------------------------------
procedure set_provincia_distretto
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.provincia_distretto%type default null
) is
/******************************************************************************
 NOME:        set_provincia_distretto
 DESCRIZIONE: Setter per attributo provincia_distretto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_provincia_distretto'
           );
   update COMUNI
   set provincia_distretto = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_provincia_distretto; -- comuni_tpk.set_provincia_distretto
--------------------------------------------------------------------------------
procedure set_comune_distretto
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.comune_distretto%type default null
) is
/******************************************************************************
 NOME:        set_comune_distretto
 DESCRIZIONE: Setter per attributo comune_distretto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_comune_distretto'
           );
   update COMUNI
   set comune_distretto = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_comune_distretto; -- comuni_tpk.set_comune_distretto
--------------------------------------------------------------------------------
procedure set_data_soppressione
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.data_soppressione%type default null
) is
/******************************************************************************
 NOME:        set_data_soppressione
 DESCRIZIONE: Setter per attributo data_soppressione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_data_soppressione'
           );
   update COMUNI
   set data_soppressione = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_data_soppressione; -- comuni_tpk.set_data_soppressione
--------------------------------------------------------------------------------
procedure set_provincia_fusione
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.provincia_fusione%type default null
) is
/******************************************************************************
 NOME:        set_provincia_fusione
 DESCRIZIONE: Setter per attributo provincia_fusione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_provincia_fusione'
           );
   update COMUNI
   set provincia_fusione = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_provincia_fusione; -- comuni_tpk.set_provincia_fusione
--------------------------------------------------------------------------------
procedure set_comune_fusione
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.comune_fusione%type default null
) is
/******************************************************************************
 NOME:        set_comune_fusione
 DESCRIZIONE: Setter per attributo comune_fusione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_comune_fusione'
           );
   update COMUNI
   set comune_fusione = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_comune_fusione; -- comuni_tpk.set_comune_fusione
--------------------------------------------------------------------------------
procedure set_sigla_cfis
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.sigla_cfis%type default null
) is
/******************************************************************************
 NOME:        set_sigla_cfis
 DESCRIZIONE: Setter per attributo sigla_cfis di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_sigla_cfis'
           );
   update COMUNI
   set sigla_cfis = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_sigla_cfis; -- comuni_tpk.set_sigla_cfis
--------------------------------------------------------------------------------
procedure set_consolato
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.consolato%type default null
) is
/******************************************************************************
 NOME:        set_consolato
 DESCRIZIONE: Setter per attributo consolato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_consolato'
           );
   update COMUNI
   set consolato = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_consolato; -- comuni_tpk.set_consolato
--------------------------------------------------------------------------------
procedure set_tipo_consolato
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.tipo_consolato%type default null
) is
/******************************************************************************
 NOME:        set_tipo_consolato
 DESCRIZIONE: Setter per attributo tipo_consolato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_tipo_consolato'
           );
   update COMUNI
   set tipo_consolato = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_tipo_consolato; -- comuni_tpk.set_tipo_consolato
--------------------------------------------------------------------------------
procedure set_utente_aggiornamento
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.utente_aggiornamento%type default null
) is
/******************************************************************************
 NOME:        set_utente_aggiornamento
 DESCRIZIONE: Setter per attributo utente_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_utente_aggiornamento'
           );
   update COMUNI
   set utente_aggiornamento = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_utente_aggiornamento; -- comuni_tpk.set_utente_aggiornamento
--------------------------------------------------------------------------------
procedure set_data_aggiornamento
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.data_aggiornamento%type default null
) is
/******************************************************************************
 NOME:        set_data_aggiornamento
 DESCRIZIONE: Setter per attributo data_aggiornamento di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_data_aggiornamento'
           );
   update COMUNI
   set data_aggiornamento = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_data_aggiornamento; -- comuni_tpk.set_data_aggiornamento
--------------------------------------------------------------------------------
procedure set_data_istituzione
(
  p_provincia_stato  in COMUNI.provincia_stato%type
,p_comune  in COMUNI.comune%type
, p_value  in COMUNI.data_istituzione%type default null
) is
/******************************************************************************
 NOME:        set_data_istituzione
 DESCRIZIONE: Setter per attributo data_istituzione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                        )
           , 'existsId on comuni_tpk.set_data_istituzione'
           );
   update COMUNI
   set data_istituzione = p_value
   where
   provincia_stato = p_provincia_stato and
comune = p_comune
   ;
end set_data_istituzione; -- comuni_tpk.set_data_istituzione
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_provincia_stato  in varchar2 default null
, p_comune  in varchar2 default null
, p_denominazione  in varchar2 default null
, p_denominazione_al1  in varchar2 default null
, p_denominazione_al2  in varchar2 default null
, p_denominazione_breve  in varchar2 default null
, p_denominazione_breve_al1  in varchar2 default null
, p_denominazione_breve_al2  in varchar2 default null
, p_capoluogo_provincia  in varchar2 default null
, p_cap  in varchar2 default null
, p_provincia_tribunale  in varchar2 default null
, p_comune_tribunale  in varchar2 default null
, p_provincia_distretto  in varchar2 default null
, p_comune_distretto  in varchar2 default null
, p_data_soppressione  in varchar2 default null
, p_provincia_fusione  in varchar2 default null
, p_comune_fusione  in varchar2 default null
, p_sigla_cfis  in varchar2 default null
, p_consolato  in varchar2 default null
, p_tipo_consolato  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
, p_data_istituzione  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( provincia_stato ', p_provincia_stato, ' )', p_QBE, null )
|| AFC.get_field_condition( ' and ( comune ', p_comune, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione ', p_denominazione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione_al1 ', p_denominazione_al1 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione_al2 ', p_denominazione_al2 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione_breve ', p_denominazione_breve , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione_breve_al1 ', p_denominazione_breve_al1 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( denominazione_breve_al2 ', p_denominazione_breve_al2 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( capoluogo_provincia ', p_capoluogo_provincia , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( cap ', p_cap , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( provincia_tribunale ', p_provincia_tribunale , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( comune_tribunale ', p_comune_tribunale , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( provincia_distretto ', p_provincia_distretto , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( comune_distretto ', p_comune_distretto , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data_soppressione ', p_data_soppressione , ' )', p_QBE, AFC.date_format )
               || AFC.get_field_condition( ' and ( provincia_fusione ', p_provincia_fusione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( comune_fusione ', p_comune_fusione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( sigla_cfis ', p_sigla_cfis , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( consolato ', p_consolato , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( tipo_consolato ', p_tipo_consolato , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( utente_aggiornamento ', p_utente_aggiornamento , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( data_aggiornamento ', p_data_aggiornamento , ' )', p_QBE, AFC.date_format )
               || AFC.get_field_condition( ' and ( data_istituzione ', p_data_istituzione , ' )', p_QBE, AFC.date_format )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- comuni_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_provincia_stato  in varchar2 default null
, p_comune  in varchar2 default null
, p_denominazione  in varchar2 default null
, p_denominazione_al1  in varchar2 default null
, p_denominazione_al2  in varchar2 default null
, p_denominazione_breve  in varchar2 default null
, p_denominazione_breve_al1  in varchar2 default null
, p_denominazione_breve_al2  in varchar2 default null
, p_capoluogo_provincia  in varchar2 default null
, p_cap  in varchar2 default null
, p_provincia_tribunale  in varchar2 default null
, p_comune_tribunale  in varchar2 default null
, p_provincia_distretto  in varchar2 default null
, p_comune_distretto  in varchar2 default null
, p_data_soppressione  in varchar2 default null
, p_provincia_fusione  in varchar2 default null
, p_comune_fusione  in varchar2 default null
, p_sigla_cfis  in varchar2 default null
, p_consolato  in varchar2 default null
, p_tipo_consolato  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
, p_data_istituzione  in varchar2 default null
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
   d_statement := ' select COMUNI.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from COMUNI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                 , p_denominazione => p_denominazione
                                 , p_denominazione_al1 => p_denominazione_al1
                                 , p_denominazione_al2 => p_denominazione_al2
                                 , p_denominazione_breve => p_denominazione_breve
                                 , p_denominazione_breve_al1 => p_denominazione_breve_al1
                                 , p_denominazione_breve_al2 => p_denominazione_breve_al2
                                 , p_capoluogo_provincia => p_capoluogo_provincia
                                 , p_cap => p_cap
                                 , p_provincia_tribunale => p_provincia_tribunale
                                 , p_comune_tribunale => p_comune_tribunale
                                 , p_provincia_distretto => p_provincia_distretto
                                 , p_comune_distretto => p_comune_distretto
                                 , p_data_soppressione => p_data_soppressione
                                 , p_provincia_fusione => p_provincia_fusione
                                 , p_comune_fusione => p_comune_fusione
                                 , p_sigla_cfis => p_sigla_cfis
                                 , p_consolato => p_consolato
                                 , p_tipo_consolato => p_tipo_consolato
                                 , p_utente_aggiornamento => p_utente_aggiornamento
                                 , p_data_aggiornamento => p_data_aggiornamento
                                 , p_data_istituzione => p_data_istituzione
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- comuni_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_provincia_stato  in varchar2 default null
, p_comune  in varchar2 default null
, p_denominazione  in varchar2 default null
, p_denominazione_al1  in varchar2 default null
, p_denominazione_al2  in varchar2 default null
, p_denominazione_breve  in varchar2 default null
, p_denominazione_breve_al1  in varchar2 default null
, p_denominazione_breve_al2  in varchar2 default null
, p_capoluogo_provincia  in varchar2 default null
, p_cap  in varchar2 default null
, p_provincia_tribunale  in varchar2 default null
, p_comune_tribunale  in varchar2 default null
, p_provincia_distretto  in varchar2 default null
, p_comune_distretto  in varchar2 default null
, p_data_soppressione  in varchar2 default null
, p_provincia_fusione  in varchar2 default null
, p_comune_fusione  in varchar2 default null
, p_sigla_cfis  in varchar2 default null
, p_consolato  in varchar2 default null
, p_tipo_consolato  in varchar2 default null
, p_utente_aggiornamento  in varchar2 default null
, p_data_aggiornamento  in varchar2 default null
, p_data_istituzione  in varchar2 default null
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
   d_statement := ' select count( * ) from COMUNI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_provincia_stato => p_provincia_stato
, p_comune => p_comune
                                 , p_denominazione => p_denominazione
                                 , p_denominazione_al1 => p_denominazione_al1
                                 , p_denominazione_al2 => p_denominazione_al2
                                 , p_denominazione_breve => p_denominazione_breve
                                 , p_denominazione_breve_al1 => p_denominazione_breve_al1
                                 , p_denominazione_breve_al2 => p_denominazione_breve_al2
                                 , p_capoluogo_provincia => p_capoluogo_provincia
                                 , p_cap => p_cap
                                 , p_provincia_tribunale => p_provincia_tribunale
                                 , p_comune_tribunale => p_comune_tribunale
                                 , p_provincia_distretto => p_provincia_distretto
                                 , p_comune_distretto => p_comune_distretto
                                 , p_data_soppressione => p_data_soppressione
                                 , p_provincia_fusione => p_provincia_fusione
                                 , p_comune_fusione => p_comune_fusione
                                 , p_sigla_cfis => p_sigla_cfis
                                 , p_consolato => p_consolato
                                 , p_tipo_consolato => p_tipo_consolato
                                 , p_utente_aggiornamento => p_utente_aggiornamento
                                 , p_data_aggiornamento => p_data_aggiornamento
                                 , p_data_istituzione => p_data_istituzione
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- comuni_tpk.count_rows
--------------------------------------------------------------------------------

end comuni_tpk;
/

