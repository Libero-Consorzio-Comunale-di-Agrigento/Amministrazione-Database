CREATE OR REPLACE package body caratteristiche_accesso_tpk is
/******************************************************************************
 NOME:        caratteristiche_accesso_tpk
 DESCRIZIONE: Gestione tabella CARATTERISTICHE_ACCESSO.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore      Descrizione.
 000   21/09/2018  snegroni  Generazione automatica.
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '000 - 21/09/2018';
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
end versione; -- caratteristiche_accesso_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.caac_id := p_caac_id;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_caac_id => d_result.caac_id
                                        )
           , 'canHandle on caratteristiche_accesso_tpk.PK'
           );
   return  d_result;
end PK; -- caratteristiche_accesso_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
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
          p_caac_id is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on caratteristiche_accesso_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- caratteristiche_accesso_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_caac_id => p_caac_id
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- caratteristiche_accesso_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
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
                                         p_caac_id => p_caac_id
                                        )
           , 'canHandle on caratteristiche_accesso_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   CARATTERISTICHE_ACCESSO
      where
      caac_id = p_caac_id
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on caratteristiche_accesso_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- caratteristiche_accesso_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_caac_id => p_caac_id
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- caratteristiche_accesso_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type default null
, p_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type
, p_progetto  in CARATTERISTICHE_ACCESSO.progetto%type default null
, p_istanza  in CARATTERISTICHE_ACCESSO.istanza%type default null
, p_modulo  in CARATTERISTICHE_ACCESSO.modulo%type default null
, p_utente  in CARATTERISTICHE_ACCESSO.utente%type default null
, p_accesso  in CARATTERISTICHE_ACCESSO.accesso%type default null
, p_accesso_se  in CARATTERISTICHE_ACCESSO.accesso_se%type default null
, p_traccia  in CARATTERISTICHE_ACCESSO.traccia%type default null
, p_giorni_traccia  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default null
, p_tentativi_password  in CARATTERISTICHE_ACCESSO.tentativi_password%type default null
, p_validita_password  in CARATTERISTICHE_ACCESSO.validita_password%type default null
, p_single_sign_on  in CARATTERISTICHE_ACCESSO.single_sign_on%type default 'SI'
, p_sleep  in CARATTERISTICHE_ACCESSO.sleep%type default null
, p_ldap  in CARATTERISTICHE_ACCESSO.ldap%type default 'NO'
, p_min_lunghezza_pwd  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default 0
, p_mod_pwd_primo_accesso  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default 'NO'
, p_archiviazione_traccia  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default 'NO'
, p_dislocazione_traccia  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default null
, p_ammessi_car_speciali_pwd  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default 'SI'
, p_numeri_obb_pwd  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default 'NO'
, p_gg_prima_riutilizzo_pwd  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert

   DbC.PRE ( not DbC.PreOn or p_tipo_accesso is not null or /*default value*/ '' is not null
           , 'p_tipo_accesso on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_progetto is not null or /*default value*/ 'default' is not null
           , 'p_progetto on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_istanza is not null or /*default value*/ 'default' is not null
           , 'p_istanza on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ 'default' is not null
           , 'p_modulo on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente is not null or /*default value*/ 'default' is not null
           , 'p_utente on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_accesso is not null or /*default value*/ 'default' is not null
           , 'p_accesso on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_accesso_se is not null or /*default value*/ 'default' is not null
           , 'p_accesso_se on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_traccia is not null or /*default value*/ 'default' is not null
           , 'p_traccia on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_giorni_traccia is not null or /*default value*/ 'default' is not null
           , 'p_giorni_traccia on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_tentativi_password is not null or /*default value*/ 'default' is not null
           , 'p_tentativi_password on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_validita_password is not null or /*default value*/ 'default' is not null
           , 'p_validita_password on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_single_sign_on is not null or /*default value*/ 'default' is not null
           , 'p_single_sign_on on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_sleep is not null or /*default value*/ 'default' is not null
           , 'p_sleep on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ldap is not null or /*default value*/ 'default' is not null
           , 'p_ldap on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_min_lunghezza_pwd is not null or /*default value*/ 'default' is not null
           , 'p_min_lunghezza_pwd on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_mod_pwd_primo_accesso is not null or /*default value*/ 'default' is not null
           , 'p_mod_pwd_primo_accesso on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_archiviazione_traccia is not null or /*default value*/ 'default' is not null
           , 'p_archiviazione_traccia on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_dislocazione_traccia is not null or /*default value*/ 'default' is not null
           , 'p_dislocazione_traccia on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ammessi_car_speciali_pwd is not null or /*default value*/ 'default' is not null
           , 'p_ammessi_car_speciali_pwd on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_numeri_obb_pwd is not null or /*default value*/ 'default' is not null
           , 'p_numeri_obb_pwd on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_gg_prima_riutilizzo_pwd is not null or /*default value*/ 'default' is not null
           , 'p_gg_prima_riutilizzo_pwd on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_caac_id is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_caac_id => p_caac_id
                           )
           , 'not existsId on caratteristiche_accesso_tpk.ins'
           );
   insert into CARATTERISTICHE_ACCESSO
   (
     caac_id
   , tipo_accesso
   , progetto
   , istanza
   , modulo
   , utente
   , accesso
   , accesso_se
   , traccia
   , giorni_traccia
   , tentativi_password
   , validita_password
   , single_sign_on
   , sleep
   , ldap
   , min_lunghezza_pwd
   , mod_pwd_primo_accesso
   , archiviazione_traccia
   , dislocazione_traccia
   , ammessi_car_speciali_pwd
   , numeri_obb_pwd
   , gg_prima_riutilizzo_pwd
   )
   values
   (
     p_caac_id
, p_tipo_accesso
, p_progetto
, p_istanza
, p_modulo
, p_utente
, p_accesso
, p_accesso_se
, p_traccia
, p_giorni_traccia
, p_tentativi_password
, p_validita_password
, p_single_sign_on
, p_sleep
, p_ldap
, p_min_lunghezza_pwd
, p_mod_pwd_primo_accesso
, nvl( p_archiviazione_traccia, 'NO' )
, p_dislocazione_traccia
, nvl( p_ammessi_car_speciali_pwd, 'SI' )
, nvl( p_numeri_obb_pwd, 'NO' )
, p_gg_prima_riutilizzo_pwd
   );
end ins; -- caratteristiche_accesso_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type default null
, p_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type
, p_progetto  in CARATTERISTICHE_ACCESSO.progetto%type default null
, p_istanza  in CARATTERISTICHE_ACCESSO.istanza%type default null
, p_modulo  in CARATTERISTICHE_ACCESSO.modulo%type default null
, p_utente  in CARATTERISTICHE_ACCESSO.utente%type default null
, p_accesso  in CARATTERISTICHE_ACCESSO.accesso%type default null
, p_accesso_se  in CARATTERISTICHE_ACCESSO.accesso_se%type default null
, p_traccia  in CARATTERISTICHE_ACCESSO.traccia%type default null
, p_giorni_traccia  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default null
, p_tentativi_password  in CARATTERISTICHE_ACCESSO.tentativi_password%type default null
, p_validita_password  in CARATTERISTICHE_ACCESSO.validita_password%type default null
, p_single_sign_on  in CARATTERISTICHE_ACCESSO.single_sign_on%type default 'SI'
, p_sleep  in CARATTERISTICHE_ACCESSO.sleep%type default null
, p_ldap  in CARATTERISTICHE_ACCESSO.ldap%type default 'NO'
, p_min_lunghezza_pwd  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default 0
, p_mod_pwd_primo_accesso  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default 'NO'
, p_archiviazione_traccia  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default 'NO'
, p_dislocazione_traccia  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default null
, p_ammessi_car_speciali_pwd  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default 'SI'
, p_numeri_obb_pwd  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default 'NO'
, p_gg_prima_riutilizzo_pwd  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default null
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

   DbC.PRE ( not DbC.PreOn or p_tipo_accesso is not null or /*default value*/ '' is not null
           , 'p_tipo_accesso on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_progetto is not null or /*default value*/ 'default' is not null
           , 'p_progetto on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_istanza is not null or /*default value*/ 'default' is not null
           , 'p_istanza on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ 'default' is not null
           , 'p_modulo on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_utente is not null or /*default value*/ 'default' is not null
           , 'p_utente on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_accesso is not null or /*default value*/ 'default' is not null
           , 'p_accesso on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_accesso_se is not null or /*default value*/ 'default' is not null
           , 'p_accesso_se on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_traccia is not null or /*default value*/ 'default' is not null
           , 'p_traccia on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_giorni_traccia is not null or /*default value*/ 'default' is not null
           , 'p_giorni_traccia on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_tentativi_password is not null or /*default value*/ 'default' is not null
           , 'p_tentativi_password on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_validita_password is not null or /*default value*/ 'default' is not null
           , 'p_validita_password on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_single_sign_on is not null or /*default value*/ 'default' is not null
           , 'p_single_sign_on on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_sleep is not null or /*default value*/ 'default' is not null
           , 'p_sleep on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ldap is not null or /*default value*/ 'default' is not null
           , 'p_ldap on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_min_lunghezza_pwd is not null or /*default value*/ 'default' is not null
           , 'p_min_lunghezza_pwd on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_mod_pwd_primo_accesso is not null or /*default value*/ 'default' is not null
           , 'p_mod_pwd_primo_accesso on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_archiviazione_traccia is not null or /*default value*/ 'default' is not null
           , 'p_archiviazione_traccia on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_dislocazione_traccia is not null or /*default value*/ 'default' is not null
           , 'p_dislocazione_traccia on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ammessi_car_speciali_pwd is not null or /*default value*/ 'default' is not null
           , 'p_ammessi_car_speciali_pwd on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_numeri_obb_pwd is not null or /*default value*/ 'default' is not null
           , 'p_numeri_obb_pwd on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_gg_prima_riutilizzo_pwd is not null or /*default value*/ 'default' is not null
           , 'p_gg_prima_riutilizzo_pwd on caratteristiche_accesso_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_caac_id is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_caac_id => p_caac_id
                           )
           , 'not existsId on caratteristiche_accesso_tpk.ins'
           );
   insert into CARATTERISTICHE_ACCESSO
   (
     caac_id
   , tipo_accesso
   , progetto
   , istanza
   , modulo
   , utente
   , accesso
   , accesso_se
   , traccia
   , giorni_traccia
   , tentativi_password
   , validita_password
   , single_sign_on
   , sleep
   , ldap
   , min_lunghezza_pwd
   , mod_pwd_primo_accesso
   , archiviazione_traccia
   , dislocazione_traccia
   , ammessi_car_speciali_pwd
   , numeri_obb_pwd
   , gg_prima_riutilizzo_pwd
   )
   values
   (
     p_caac_id
, p_tipo_accesso
, p_progetto
, p_istanza
, p_modulo
, p_utente
, p_accesso
, p_accesso_se
, p_traccia
, p_giorni_traccia
, p_tentativi_password
, p_validita_password
, p_single_sign_on
, p_sleep
, p_ldap
, p_min_lunghezza_pwd
, p_mod_pwd_primo_accesso
, nvl( p_archiviazione_traccia, 'NO' )
, p_dislocazione_traccia
, nvl( p_ammessi_car_speciali_pwd, 'SI' )
, nvl( p_numeri_obb_pwd, 'NO' )
, p_gg_prima_riutilizzo_pwd
   ) returning caac_id
   into d_result;
   return d_result;
end ins; -- caratteristiche_accesso_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_OLD_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type default null
, p_NEW_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type default afc.default_null('CARATTERISTICHE_ACCESSO.tipo_accesso')
, p_OLD_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type default null
, p_NEW_progetto  in CARATTERISTICHE_ACCESSO.progetto%type default afc.default_null('CARATTERISTICHE_ACCESSO.progetto')
, p_OLD_progetto  in CARATTERISTICHE_ACCESSO.progetto%type default null
, p_NEW_istanza  in CARATTERISTICHE_ACCESSO.istanza%type default afc.default_null('CARATTERISTICHE_ACCESSO.istanza')
, p_OLD_istanza  in CARATTERISTICHE_ACCESSO.istanza%type default null
, p_NEW_modulo  in CARATTERISTICHE_ACCESSO.modulo%type default afc.default_null('CARATTERISTICHE_ACCESSO.modulo')
, p_OLD_modulo  in CARATTERISTICHE_ACCESSO.modulo%type default null
, p_NEW_utente  in CARATTERISTICHE_ACCESSO.utente%type default afc.default_null('CARATTERISTICHE_ACCESSO.utente')
, p_OLD_utente  in CARATTERISTICHE_ACCESSO.utente%type default null
, p_NEW_accesso  in CARATTERISTICHE_ACCESSO.accesso%type default afc.default_null('CARATTERISTICHE_ACCESSO.accesso')
, p_OLD_accesso  in CARATTERISTICHE_ACCESSO.accesso%type default null
, p_NEW_accesso_se  in CARATTERISTICHE_ACCESSO.accesso_se%type default afc.default_null('CARATTERISTICHE_ACCESSO.accesso_se')
, p_OLD_accesso_se  in CARATTERISTICHE_ACCESSO.accesso_se%type default null
, p_NEW_traccia  in CARATTERISTICHE_ACCESSO.traccia%type default afc.default_null('CARATTERISTICHE_ACCESSO.traccia')
, p_OLD_traccia  in CARATTERISTICHE_ACCESSO.traccia%type default null
, p_NEW_giorni_traccia  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default afc.default_null('CARATTERISTICHE_ACCESSO.giorni_traccia')
, p_OLD_giorni_traccia  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default null
, p_NEW_tentativi_password  in CARATTERISTICHE_ACCESSO.tentativi_password%type default afc.default_null('CARATTERISTICHE_ACCESSO.tentativi_password')
, p_OLD_tentativi_password  in CARATTERISTICHE_ACCESSO.tentativi_password%type default null
, p_NEW_validita_password  in CARATTERISTICHE_ACCESSO.validita_password%type default afc.default_null('CARATTERISTICHE_ACCESSO.validita_password')
, p_OLD_validita_password  in CARATTERISTICHE_ACCESSO.validita_password%type default null
, p_NEW_single_sign_on  in CARATTERISTICHE_ACCESSO.single_sign_on%type default afc.default_null('CARATTERISTICHE_ACCESSO.single_sign_on')
, p_OLD_single_sign_on  in CARATTERISTICHE_ACCESSO.single_sign_on%type default null
, p_NEW_sleep  in CARATTERISTICHE_ACCESSO.sleep%type default afc.default_null('CARATTERISTICHE_ACCESSO.sleep')
, p_OLD_sleep  in CARATTERISTICHE_ACCESSO.sleep%type default null
, p_NEW_ldap  in CARATTERISTICHE_ACCESSO.ldap%type default afc.default_null('CARATTERISTICHE_ACCESSO.ldap')
, p_OLD_ldap  in CARATTERISTICHE_ACCESSO.ldap%type default null
, p_NEW_min_lunghezza_pwd  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default afc.default_null('CARATTERISTICHE_ACCESSO.min_lunghezza_pwd')
, p_OLD_min_lunghezza_pwd  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default null
, p_NEW_mod_pwd_primo_accesso  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default afc.default_null('CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso')
, p_OLD_mod_pwd_primo_accesso  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default null
, p_NEW_archiviazione_traccia  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default afc.default_null('CARATTERISTICHE_ACCESSO.archiviazione_traccia')
, p_OLD_archiviazione_traccia  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default null
, p_NEW_dislocazione_traccia  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default afc.default_null('CARATTERISTICHE_ACCESSO.dislocazione_traccia')
, p_OLD_dislocazione_traccia  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default null
, p_NEW_ammessi_car_speciali_pwd  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default afc.default_null('CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd')
, p_OLD_ammessi_car_speciali_pwd  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default null
, p_NEW_numeri_obb_pwd  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default afc.default_null('CARATTERISTICHE_ACCESSO.numeri_obb_pwd')
, p_OLD_numeri_obb_pwd  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default null
, p_NEW_gg_prima_riutilizzo_pwd  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default afc.default_null('CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd')
, p_OLD_gg_prima_riutilizzo_pwd  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default null
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
p_OLD_tipo_accesso is not null
 or p_OLD_progetto is not null
 or p_OLD_istanza is not null
 or p_OLD_modulo is not null
 or p_OLD_utente is not null
 or p_OLD_accesso is not null
 or p_OLD_accesso_se is not null
 or p_OLD_traccia is not null
 or p_OLD_giorni_traccia is not null
 or p_OLD_tentativi_password is not null
 or p_OLD_validita_password is not null
 or p_OLD_single_sign_on is not null
 or p_OLD_sleep is not null
 or p_OLD_ldap is not null
 or p_OLD_min_lunghezza_pwd is not null
 or p_OLD_mod_pwd_primo_accesso is not null
 or p_OLD_archiviazione_traccia is not null
 or p_OLD_dislocazione_traccia is not null
 or p_OLD_ammessi_car_speciali_pwd is not null
 or p_OLD_numeri_obb_pwd is not null
 or p_OLD_gg_prima_riutilizzo_pwd is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on caratteristiche_accesso_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_caac_id, p_NEW_caac_id )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_caac_id => d_key.caac_id
                                       )
           , 'existsId on caratteristiche_accesso_tpk.upd'
           );
   update CARATTERISTICHE_ACCESSO
   set
       caac_id = NVL( p_NEW_caac_id, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.caac_id' ), 1, caac_id,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_caac_id, null, caac_id, null ) ) ) )
     , tipo_accesso = NVL( p_NEW_tipo_accesso, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.tipo_accesso' ), 1, tipo_accesso,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_tipo_accesso, null, tipo_accesso, null ) ) ) )
     , progetto = NVL( p_NEW_progetto, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.progetto' ), 1, progetto,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_progetto, null, progetto, null ) ) ) )
     , istanza = NVL( p_NEW_istanza, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.istanza' ), 1, istanza,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_istanza, null, istanza, null ) ) ) )
     , modulo = NVL( p_NEW_modulo, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.modulo' ), 1, modulo,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_modulo, null, modulo, null ) ) ) )
     , utente = NVL( p_NEW_utente, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.utente' ), 1, utente,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_utente, null, utente, null ) ) ) )
     , accesso = NVL( p_NEW_accesso, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.accesso' ), 1, accesso,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_accesso, null, accesso, null ) ) ) )
     , accesso_se = NVL( p_NEW_accesso_se, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.accesso_se' ), 1, accesso_se,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_accesso_se, null, accesso_se, null ) ) ) )
     , traccia = NVL( p_NEW_traccia, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.traccia' ), 1, traccia,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_traccia, null, traccia, null ) ) ) )
     , giorni_traccia = NVL( p_NEW_giorni_traccia, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.giorni_traccia' ), 1, giorni_traccia,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_giorni_traccia, null, giorni_traccia, null ) ) ) )
     , tentativi_password = NVL( p_NEW_tentativi_password, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.tentativi_password' ), 1, tentativi_password,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_tentativi_password, null, tentativi_password, null ) ) ) )
     , validita_password = NVL( p_NEW_validita_password, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.validita_password' ), 1, validita_password,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_validita_password, null, validita_password, null ) ) ) )
     , single_sign_on = NVL( p_NEW_single_sign_on, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.single_sign_on' ), 1, single_sign_on,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_single_sign_on, null, single_sign_on, null ) ) ) )
     , sleep = NVL( p_NEW_sleep, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.sleep' ), 1, sleep,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_sleep, null, sleep, null ) ) ) )
     , ldap = NVL( p_NEW_ldap, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.ldap' ), 1, ldap,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_ldap, null, ldap, null ) ) ) )
     , min_lunghezza_pwd = NVL( p_NEW_min_lunghezza_pwd, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.min_lunghezza_pwd' ), 1, min_lunghezza_pwd,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_min_lunghezza_pwd, null, min_lunghezza_pwd, null ) ) ) )
     , mod_pwd_primo_accesso = NVL( p_NEW_mod_pwd_primo_accesso, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso' ), 1, mod_pwd_primo_accesso,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_mod_pwd_primo_accesso, null, mod_pwd_primo_accesso, null ) ) ) )
     , archiviazione_traccia = NVL( p_NEW_archiviazione_traccia, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.archiviazione_traccia' ), 1, archiviazione_traccia,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_archiviazione_traccia, null, archiviazione_traccia, null ) ) ) )
     , dislocazione_traccia = NVL( p_NEW_dislocazione_traccia, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.dislocazione_traccia' ), 1, dislocazione_traccia,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_dislocazione_traccia, null, dislocazione_traccia, null ) ) ) )
     , ammessi_car_speciali_pwd = NVL( p_NEW_ammessi_car_speciali_pwd, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd' ), 1, ammessi_car_speciali_pwd,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_ammessi_car_speciali_pwd, null, ammessi_car_speciali_pwd, null ) ) ) )
     , numeri_obb_pwd = NVL( p_NEW_numeri_obb_pwd, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.numeri_obb_pwd' ), 1, numeri_obb_pwd,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_numeri_obb_pwd, null, numeri_obb_pwd, null ) ) ) )
     , gg_prima_riutilizzo_pwd = NVL( p_NEW_gg_prima_riutilizzo_pwd, DECODE( AFC.IS_DEFAULT_NULL( 'CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd' ), 1, gg_prima_riutilizzo_pwd,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_gg_prima_riutilizzo_pwd, null, gg_prima_riutilizzo_pwd, null ) ) ) )
   where
     caac_id = d_key.caac_id
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( tipo_accesso = p_OLD_tipo_accesso or ( p_OLD_tipo_accesso is null and ( p_check_OLD is null or tipo_accesso is null ) ) )
           and ( progetto = p_OLD_progetto or ( p_OLD_progetto is null and ( p_check_OLD is null or progetto is null ) ) )
           and ( istanza = p_OLD_istanza or ( p_OLD_istanza is null and ( p_check_OLD is null or istanza is null ) ) )
           and ( modulo = p_OLD_modulo or ( p_OLD_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( utente = p_OLD_utente or ( p_OLD_utente is null and ( p_check_OLD is null or utente is null ) ) )
           and ( accesso = p_OLD_accesso or ( p_OLD_accesso is null and ( p_check_OLD is null or accesso is null ) ) )
           and ( accesso_se = p_OLD_accesso_se or ( p_OLD_accesso_se is null and ( p_check_OLD is null or accesso_se is null ) ) )
           and ( traccia = p_OLD_traccia or ( p_OLD_traccia is null and ( p_check_OLD is null or traccia is null ) ) )
           and ( giorni_traccia = p_OLD_giorni_traccia or ( p_OLD_giorni_traccia is null and ( p_check_OLD is null or giorni_traccia is null ) ) )
           and ( tentativi_password = p_OLD_tentativi_password or ( p_OLD_tentativi_password is null and ( p_check_OLD is null or tentativi_password is null ) ) )
           and ( validita_password = p_OLD_validita_password or ( p_OLD_validita_password is null and ( p_check_OLD is null or validita_password is null ) ) )
           and ( single_sign_on = p_OLD_single_sign_on or ( p_OLD_single_sign_on is null and ( p_check_OLD is null or single_sign_on is null ) ) )
           and ( sleep = p_OLD_sleep or ( p_OLD_sleep is null and ( p_check_OLD is null or sleep is null ) ) )
           and ( ldap = p_OLD_ldap or ( p_OLD_ldap is null and ( p_check_OLD is null or ldap is null ) ) )
           and ( min_lunghezza_pwd = p_OLD_min_lunghezza_pwd or ( p_OLD_min_lunghezza_pwd is null and ( p_check_OLD is null or min_lunghezza_pwd is null ) ) )
           and ( mod_pwd_primo_accesso = p_OLD_mod_pwd_primo_accesso or ( p_OLD_mod_pwd_primo_accesso is null and ( p_check_OLD is null or mod_pwd_primo_accesso is null ) ) )
           and ( archiviazione_traccia = p_OLD_archiviazione_traccia or ( p_OLD_archiviazione_traccia is null and ( p_check_OLD is null or archiviazione_traccia is null ) ) )
           and ( dislocazione_traccia = p_OLD_dislocazione_traccia or ( p_OLD_dislocazione_traccia is null and ( p_check_OLD is null or dislocazione_traccia is null ) ) )
           and ( ammessi_car_speciali_pwd = p_OLD_ammessi_car_speciali_pwd or ( p_OLD_ammessi_car_speciali_pwd is null and ( p_check_OLD is null or ammessi_car_speciali_pwd is null ) ) )
           and ( numeri_obb_pwd = p_OLD_numeri_obb_pwd or ( p_OLD_numeri_obb_pwd is null and ( p_check_OLD is null or numeri_obb_pwd is null ) ) )
           and ( gg_prima_riutilizzo_pwd = p_OLD_gg_prima_riutilizzo_pwd or ( p_OLD_gg_prima_riutilizzo_pwd is null and ( p_check_OLD is null or gg_prima_riutilizzo_pwd is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on caratteristiche_accesso_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- caratteristiche_accesso_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
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
                                        p_caac_id => p_caac_id
                                       )
           , 'existsId on caratteristiche_accesso_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on caratteristiche_accesso_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on caratteristiche_accesso_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on caratteristiche_accesso_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update CARATTERISTICHE_ACCESSO '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( caac_id ', p_caac_id, ' )', 0, null ), ' and ( caac_id is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- caratteristiche_accesso_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type default null
, p_progetto  in CARATTERISTICHE_ACCESSO.progetto%type default null
, p_istanza  in CARATTERISTICHE_ACCESSO.istanza%type default null
, p_modulo  in CARATTERISTICHE_ACCESSO.modulo%type default null
, p_utente  in CARATTERISTICHE_ACCESSO.utente%type default null
, p_accesso  in CARATTERISTICHE_ACCESSO.accesso%type default null
, p_accesso_se  in CARATTERISTICHE_ACCESSO.accesso_se%type default null
, p_traccia  in CARATTERISTICHE_ACCESSO.traccia%type default null
, p_giorni_traccia  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default null
, p_tentativi_password  in CARATTERISTICHE_ACCESSO.tentativi_password%type default null
, p_validita_password  in CARATTERISTICHE_ACCESSO.validita_password%type default null
, p_single_sign_on  in CARATTERISTICHE_ACCESSO.single_sign_on%type default null
, p_sleep  in CARATTERISTICHE_ACCESSO.sleep%type default null
, p_ldap  in CARATTERISTICHE_ACCESSO.ldap%type default null
, p_min_lunghezza_pwd  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default null
, p_mod_pwd_primo_accesso  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default null
, p_archiviazione_traccia  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default null
, p_dislocazione_traccia  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default null
, p_ammessi_car_speciali_pwd  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default null
, p_numeri_obb_pwd  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default null
, p_gg_prima_riutilizzo_pwd  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default null
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
p_tipo_accesso is not null
 or p_progetto is not null
 or p_istanza is not null
 or p_modulo is not null
 or p_utente is not null
 or p_accesso is not null
 or p_accesso_se is not null
 or p_traccia is not null
 or p_giorni_traccia is not null
 or p_tentativi_password is not null
 or p_validita_password is not null
 or p_single_sign_on is not null
 or p_sleep is not null
 or p_ldap is not null
 or p_min_lunghezza_pwd is not null
 or p_mod_pwd_primo_accesso is not null
 or p_archiviazione_traccia is not null
 or p_dislocazione_traccia is not null
 or p_ammessi_car_speciali_pwd is not null
 or p_numeri_obb_pwd is not null
 or p_gg_prima_riutilizzo_pwd is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on caratteristiche_accesso_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_caac_id => p_caac_id
                                       )
           , 'existsId on caratteristiche_accesso_tpk.del'
           );
   delete from CARATTERISTICHE_ACCESSO
   where
     caac_id = p_caac_id
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( tipo_accesso = p_tipo_accesso or ( p_tipo_accesso is null and ( p_check_OLD is null or tipo_accesso is null ) ) )
           and ( progetto = p_progetto or ( p_progetto is null and ( p_check_OLD is null or progetto is null ) ) )
           and ( istanza = p_istanza or ( p_istanza is null and ( p_check_OLD is null or istanza is null ) ) )
           and ( modulo = p_modulo or ( p_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( utente = p_utente or ( p_utente is null and ( p_check_OLD is null or utente is null ) ) )
           and ( accesso = p_accesso or ( p_accesso is null and ( p_check_OLD is null or accesso is null ) ) )
           and ( accesso_se = p_accesso_se or ( p_accesso_se is null and ( p_check_OLD is null or accesso_se is null ) ) )
           and ( traccia = p_traccia or ( p_traccia is null and ( p_check_OLD is null or traccia is null ) ) )
           and ( giorni_traccia = p_giorni_traccia or ( p_giorni_traccia is null and ( p_check_OLD is null or giorni_traccia is null ) ) )
           and ( tentativi_password = p_tentativi_password or ( p_tentativi_password is null and ( p_check_OLD is null or tentativi_password is null ) ) )
           and ( validita_password = p_validita_password or ( p_validita_password is null and ( p_check_OLD is null or validita_password is null ) ) )
           and ( single_sign_on = p_single_sign_on or ( p_single_sign_on is null and ( p_check_OLD is null or single_sign_on is null ) ) )
           and ( sleep = p_sleep or ( p_sleep is null and ( p_check_OLD is null or sleep is null ) ) )
           and ( ldap = p_ldap or ( p_ldap is null and ( p_check_OLD is null or ldap is null ) ) )
           and ( min_lunghezza_pwd = p_min_lunghezza_pwd or ( p_min_lunghezza_pwd is null and ( p_check_OLD is null or min_lunghezza_pwd is null ) ) )
           and ( mod_pwd_primo_accesso = p_mod_pwd_primo_accesso or ( p_mod_pwd_primo_accesso is null and ( p_check_OLD is null or mod_pwd_primo_accesso is null ) ) )
           and ( archiviazione_traccia = p_archiviazione_traccia or ( p_archiviazione_traccia is null and ( p_check_OLD is null or archiviazione_traccia is null ) ) )
           and ( dislocazione_traccia = p_dislocazione_traccia or ( p_dislocazione_traccia is null and ( p_check_OLD is null or dislocazione_traccia is null ) ) )
           and ( ammessi_car_speciali_pwd = p_ammessi_car_speciali_pwd or ( p_ammessi_car_speciali_pwd is null and ( p_check_OLD is null or ammessi_car_speciali_pwd is null ) ) )
           and ( numeri_obb_pwd = p_numeri_obb_pwd or ( p_numeri_obb_pwd is null and ( p_check_OLD is null or numeri_obb_pwd is null ) ) )
           and ( gg_prima_riutilizzo_pwd = p_gg_prima_riutilizzo_pwd or ( p_gg_prima_riutilizzo_pwd is null and ( p_check_OLD is null or gg_prima_riutilizzo_pwd is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on caratteristiche_accesso_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_caac_id => p_caac_id
                                             )
            , 'existsId on caratteristiche_accesso_tpk.del'
            );
end del; -- caratteristiche_accesso_tpk.del
--------------------------------------------------------------------------------
function get_tipo_accesso
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.tipo_accesso%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_tipo_accesso
 DESCRIZIONE: Getter per attributo tipo_accesso di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.tipo_accesso%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.tipo_accesso%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_tipo_accesso'
           );
   select tipo_accesso
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_tipo_accesso'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'tipo_accesso')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_tipo_accesso'
                    );
   end if;
   return  d_result;
end get_tipo_accesso; -- caratteristiche_accesso_tpk.get_tipo_accesso
--------------------------------------------------------------------------------
function get_progetto
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.progetto%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_progetto
 DESCRIZIONE: Getter per attributo progetto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.progetto%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.progetto%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_progetto'
           );
   select progetto
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_progetto'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'progetto')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_progetto'
                    );
   end if;
   return  d_result;
end get_progetto; -- caratteristiche_accesso_tpk.get_progetto
--------------------------------------------------------------------------------
function get_istanza
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.istanza%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_istanza
 DESCRIZIONE: Getter per attributo istanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.istanza%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.istanza%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_istanza'
           );
   select istanza
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_istanza'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'istanza')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_istanza'
                    );
   end if;
   return  d_result;
end get_istanza; -- caratteristiche_accesso_tpk.get_istanza
--------------------------------------------------------------------------------
function get_modulo
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.modulo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_modulo
 DESCRIZIONE: Getter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.modulo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.modulo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_modulo'
           );
   select modulo
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_modulo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'modulo')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_modulo'
                    );
   end if;
   return  d_result;
end get_modulo; -- caratteristiche_accesso_tpk.get_modulo
--------------------------------------------------------------------------------
function get_utente
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.utente%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_utente
 DESCRIZIONE: Getter per attributo utente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.utente%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.utente%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_utente'
           );
   select utente
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_utente'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'utente')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_utente'
                    );
   end if;
   return  d_result;
end get_utente; -- caratteristiche_accesso_tpk.get_utente
--------------------------------------------------------------------------------
function get_accesso
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.accesso%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_accesso
 DESCRIZIONE: Getter per attributo accesso di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.accesso%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.accesso%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_accesso'
           );
   select accesso
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_accesso'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'accesso')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_accesso'
                    );
   end if;
   return  d_result;
end get_accesso; -- caratteristiche_accesso_tpk.get_accesso
--------------------------------------------------------------------------------
function get_accesso_se
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.accesso_se%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_accesso_se
 DESCRIZIONE: Getter per attributo accesso_se di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.accesso_se%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.accesso_se%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_accesso_se'
           );
   select accesso_se
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_accesso_se'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'accesso_se')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_accesso_se'
                    );
   end if;
   return  d_result;
end get_accesso_se; -- caratteristiche_accesso_tpk.get_accesso_se
--------------------------------------------------------------------------------
function get_traccia
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.traccia%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_traccia
 DESCRIZIONE: Getter per attributo traccia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.traccia%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.traccia%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_traccia'
           );
   select traccia
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_traccia'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'traccia')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_traccia'
                    );
   end if;
   return  d_result;
end get_traccia; -- caratteristiche_accesso_tpk.get_traccia
--------------------------------------------------------------------------------
function get_giorni_traccia
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.giorni_traccia%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_giorni_traccia
 DESCRIZIONE: Getter per attributo giorni_traccia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.giorni_traccia%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.giorni_traccia%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_giorni_traccia'
           );
   select giorni_traccia
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_giorni_traccia'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'giorni_traccia')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_giorni_traccia'
                    );
   end if;
   return  d_result;
end get_giorni_traccia; -- caratteristiche_accesso_tpk.get_giorni_traccia
--------------------------------------------------------------------------------
function get_tentativi_password
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.tentativi_password%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_tentativi_password
 DESCRIZIONE: Getter per attributo tentativi_password di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.tentativi_password%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.tentativi_password%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_tentativi_password'
           );
   select tentativi_password
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_tentativi_password'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'tentativi_password')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_tentativi_password'
                    );
   end if;
   return  d_result;
end get_tentativi_password; -- caratteristiche_accesso_tpk.get_tentativi_password
--------------------------------------------------------------------------------
function get_validita_password
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.validita_password%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_validita_password
 DESCRIZIONE: Getter per attributo validita_password di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.validita_password%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.validita_password%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_validita_password'
           );
   select validita_password
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_validita_password'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'validita_password')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_validita_password'
                    );
   end if;
   return  d_result;
end get_validita_password; -- caratteristiche_accesso_tpk.get_validita_password
--------------------------------------------------------------------------------
function get_single_sign_on
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.single_sign_on%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_single_sign_on
 DESCRIZIONE: Getter per attributo single_sign_on di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.single_sign_on%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.single_sign_on%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_single_sign_on'
           );
   select single_sign_on
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_single_sign_on'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'single_sign_on')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_single_sign_on'
                    );
   end if;
   return  d_result;
end get_single_sign_on; -- caratteristiche_accesso_tpk.get_single_sign_on
--------------------------------------------------------------------------------
function get_sleep
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.sleep%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_sleep
 DESCRIZIONE: Getter per attributo sleep di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.sleep%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.sleep%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_sleep'
           );
   select sleep
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_sleep'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'sleep')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_sleep'
                    );
   end if;
   return  d_result;
end get_sleep; -- caratteristiche_accesso_tpk.get_sleep
--------------------------------------------------------------------------------
function get_ldap
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.ldap%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_ldap
 DESCRIZIONE: Getter per attributo ldap di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.ldap%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.ldap%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_ldap'
           );
   select ldap
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_ldap'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'ldap')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_ldap'
                    );
   end if;
   return  d_result;
end get_ldap; -- caratteristiche_accesso_tpk.get_ldap
--------------------------------------------------------------------------------
function get_min_lunghezza_pwd
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_min_lunghezza_pwd
 DESCRIZIONE: Getter per attributo min_lunghezza_pwd di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_min_lunghezza_pwd'
           );
   select min_lunghezza_pwd
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_min_lunghezza_pwd'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'min_lunghezza_pwd')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_min_lunghezza_pwd'
                    );
   end if;
   return  d_result;
end get_min_lunghezza_pwd; -- caratteristiche_accesso_tpk.get_min_lunghezza_pwd
--------------------------------------------------------------------------------
function get_mod_pwd_primo_accesso
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_mod_pwd_primo_accesso
 DESCRIZIONE: Getter per attributo mod_pwd_primo_accesso di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_mod_pwd_primo_accesso'
           );
   select mod_pwd_primo_accesso
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_mod_pwd_primo_accesso'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'mod_pwd_primo_accesso')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_mod_pwd_primo_accesso'
                    );
   end if;
   return  d_result;
end get_mod_pwd_primo_accesso; -- caratteristiche_accesso_tpk.get_mod_pwd_primo_accesso
--------------------------------------------------------------------------------
function get_archiviazione_traccia
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.archiviazione_traccia%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_archiviazione_traccia
 DESCRIZIONE: Getter per attributo archiviazione_traccia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.archiviazione_traccia%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.archiviazione_traccia%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_archiviazione_traccia'
           );
   select archiviazione_traccia
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_archiviazione_traccia'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'archiviazione_traccia')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_archiviazione_traccia'
                    );
   end if;
   return  d_result;
end get_archiviazione_traccia; -- caratteristiche_accesso_tpk.get_archiviazione_traccia
--------------------------------------------------------------------------------
function get_dislocazione_traccia
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.dislocazione_traccia%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_dislocazione_traccia
 DESCRIZIONE: Getter per attributo dislocazione_traccia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.dislocazione_traccia%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.dislocazione_traccia%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_dislocazione_traccia'
           );
   select dislocazione_traccia
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_dislocazione_traccia'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'dislocazione_traccia')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_dislocazione_traccia'
                    );
   end if;
   return  d_result;
end get_dislocazione_traccia; -- caratteristiche_accesso_tpk.get_dislocazione_traccia
--------------------------------------------------------------------------------
function get_ammessi_car_speciali_pwd
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_ammessi_car_speciali_pwd
 DESCRIZIONE: Getter per attributo ammessi_car_speciali_pwd di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_ammessi_car_speciali_pwd'
           );
   select ammessi_car_speciali_pwd
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_ammessi_car_speciali_pwd'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'ammessi_car_speciali_pwd')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_ammessi_car_speciali_pwd'
                    );
   end if;
   return  d_result;
end get_ammessi_car_speciali_pwd; -- caratteristiche_accesso_tpk.get_ammessi_car_speciali_pwd
--------------------------------------------------------------------------------
function get_numeri_obb_pwd
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_numeri_obb_pwd
 DESCRIZIONE: Getter per attributo numeri_obb_pwd di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_numeri_obb_pwd'
           );
   select numeri_obb_pwd
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_numeri_obb_pwd'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'numeri_obb_pwd')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_numeri_obb_pwd'
                    );
   end if;
   return  d_result;
end get_numeri_obb_pwd; -- caratteristiche_accesso_tpk.get_numeri_obb_pwd
--------------------------------------------------------------------------------
function get_gg_prima_riutilizzo_pwd
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
) return CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_gg_prima_riutilizzo_pwd
 DESCRIZIONE: Getter per attributo gg_prima_riutilizzo_pwd di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.get_gg_prima_riutilizzo_pwd'
           );
   select gg_prima_riutilizzo_pwd
   into   d_result
   from   CARATTERISTICHE_ACCESSO
   where
   caac_id = p_caac_id
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on caratteristiche_accesso_tpk.get_gg_prima_riutilizzo_pwd'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'gg_prima_riutilizzo_pwd')
                    , ' AFC_DDL.IsNullable on caratteristiche_accesso_tpk.get_gg_prima_riutilizzo_pwd'
                    );
   end if;
   return  d_result;
end get_gg_prima_riutilizzo_pwd; -- caratteristiche_accesso_tpk.get_gg_prima_riutilizzo_pwd
--------------------------------------------------------------------------------
procedure set_caac_id
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.caac_id%type default null
) is
/******************************************************************************
 NOME:        set_caac_id
 DESCRIZIONE: Setter per attributo caac_id di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_caac_id'
           );
   update CARATTERISTICHE_ACCESSO
   set caac_id = p_value
   where
   caac_id = p_caac_id
   ;
end set_caac_id; -- caratteristiche_accesso_tpk.set_caac_id
--------------------------------------------------------------------------------
procedure set_tipo_accesso
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.tipo_accesso%type default null
) is
/******************************************************************************
 NOME:        set_tipo_accesso
 DESCRIZIONE: Setter per attributo tipo_accesso di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_tipo_accesso'
           );
   update CARATTERISTICHE_ACCESSO
   set tipo_accesso = p_value
   where
   caac_id = p_caac_id
   ;
end set_tipo_accesso; -- caratteristiche_accesso_tpk.set_tipo_accesso
--------------------------------------------------------------------------------
procedure set_progetto
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.progetto%type default null
) is
/******************************************************************************
 NOME:        set_progetto
 DESCRIZIONE: Setter per attributo progetto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_progetto'
           );
   update CARATTERISTICHE_ACCESSO
   set progetto = p_value
   where
   caac_id = p_caac_id
   ;
end set_progetto; -- caratteristiche_accesso_tpk.set_progetto
--------------------------------------------------------------------------------
procedure set_istanza
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.istanza%type default null
) is
/******************************************************************************
 NOME:        set_istanza
 DESCRIZIONE: Setter per attributo istanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_istanza'
           );
   update CARATTERISTICHE_ACCESSO
   set istanza = p_value
   where
   caac_id = p_caac_id
   ;
end set_istanza; -- caratteristiche_accesso_tpk.set_istanza
--------------------------------------------------------------------------------
procedure set_modulo
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.modulo%type default null
) is
/******************************************************************************
 NOME:        set_modulo
 DESCRIZIONE: Setter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_modulo'
           );
   update CARATTERISTICHE_ACCESSO
   set modulo = p_value
   where
   caac_id = p_caac_id
   ;
end set_modulo; -- caratteristiche_accesso_tpk.set_modulo
--------------------------------------------------------------------------------
procedure set_utente
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.utente%type default null
) is
/******************************************************************************
 NOME:        set_utente
 DESCRIZIONE: Setter per attributo utente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_utente'
           );
   update CARATTERISTICHE_ACCESSO
   set utente = p_value
   where
   caac_id = p_caac_id
   ;
end set_utente; -- caratteristiche_accesso_tpk.set_utente
--------------------------------------------------------------------------------
procedure set_accesso
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.accesso%type default null
) is
/******************************************************************************
 NOME:        set_accesso
 DESCRIZIONE: Setter per attributo accesso di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_accesso'
           );
   update CARATTERISTICHE_ACCESSO
   set accesso = p_value
   where
   caac_id = p_caac_id
   ;
end set_accesso; -- caratteristiche_accesso_tpk.set_accesso
--------------------------------------------------------------------------------
procedure set_accesso_se
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.accesso_se%type default null
) is
/******************************************************************************
 NOME:        set_accesso_se
 DESCRIZIONE: Setter per attributo accesso_se di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_accesso_se'
           );
   update CARATTERISTICHE_ACCESSO
   set accesso_se = p_value
   where
   caac_id = p_caac_id
   ;
end set_accesso_se; -- caratteristiche_accesso_tpk.set_accesso_se
--------------------------------------------------------------------------------
procedure set_traccia
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.traccia%type default null
) is
/******************************************************************************
 NOME:        set_traccia
 DESCRIZIONE: Setter per attributo traccia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_traccia'
           );
   update CARATTERISTICHE_ACCESSO
   set traccia = p_value
   where
   caac_id = p_caac_id
   ;
end set_traccia; -- caratteristiche_accesso_tpk.set_traccia
--------------------------------------------------------------------------------
procedure set_giorni_traccia
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default null
) is
/******************************************************************************
 NOME:        set_giorni_traccia
 DESCRIZIONE: Setter per attributo giorni_traccia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_giorni_traccia'
           );
   update CARATTERISTICHE_ACCESSO
   set giorni_traccia = p_value
   where
   caac_id = p_caac_id
   ;
end set_giorni_traccia; -- caratteristiche_accesso_tpk.set_giorni_traccia
--------------------------------------------------------------------------------
procedure set_tentativi_password
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.tentativi_password%type default null
) is
/******************************************************************************
 NOME:        set_tentativi_password
 DESCRIZIONE: Setter per attributo tentativi_password di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_tentativi_password'
           );
   update CARATTERISTICHE_ACCESSO
   set tentativi_password = p_value
   where
   caac_id = p_caac_id
   ;
end set_tentativi_password; -- caratteristiche_accesso_tpk.set_tentativi_password
--------------------------------------------------------------------------------
procedure set_validita_password
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.validita_password%type default null
) is
/******************************************************************************
 NOME:        set_validita_password
 DESCRIZIONE: Setter per attributo validita_password di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_validita_password'
           );
   update CARATTERISTICHE_ACCESSO
   set validita_password = p_value
   where
   caac_id = p_caac_id
   ;
end set_validita_password; -- caratteristiche_accesso_tpk.set_validita_password
--------------------------------------------------------------------------------
procedure set_single_sign_on
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.single_sign_on%type default null
) is
/******************************************************************************
 NOME:        set_single_sign_on
 DESCRIZIONE: Setter per attributo single_sign_on di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_single_sign_on'
           );
   update CARATTERISTICHE_ACCESSO
   set single_sign_on = p_value
   where
   caac_id = p_caac_id
   ;
end set_single_sign_on; -- caratteristiche_accesso_tpk.set_single_sign_on
--------------------------------------------------------------------------------
procedure set_sleep
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.sleep%type default null
) is
/******************************************************************************
 NOME:        set_sleep
 DESCRIZIONE: Setter per attributo sleep di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_sleep'
           );
   update CARATTERISTICHE_ACCESSO
   set sleep = p_value
   where
   caac_id = p_caac_id
   ;
end set_sleep; -- caratteristiche_accesso_tpk.set_sleep
--------------------------------------------------------------------------------
procedure set_ldap
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.ldap%type default null
) is
/******************************************************************************
 NOME:        set_ldap
 DESCRIZIONE: Setter per attributo ldap di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_ldap'
           );
   update CARATTERISTICHE_ACCESSO
   set ldap = p_value
   where
   caac_id = p_caac_id
   ;
end set_ldap; -- caratteristiche_accesso_tpk.set_ldap
--------------------------------------------------------------------------------
procedure set_min_lunghezza_pwd
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default null
) is
/******************************************************************************
 NOME:        set_min_lunghezza_pwd
 DESCRIZIONE: Setter per attributo min_lunghezza_pwd di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_min_lunghezza_pwd'
           );
   update CARATTERISTICHE_ACCESSO
   set min_lunghezza_pwd = p_value
   where
   caac_id = p_caac_id
   ;
end set_min_lunghezza_pwd; -- caratteristiche_accesso_tpk.set_min_lunghezza_pwd
--------------------------------------------------------------------------------
procedure set_mod_pwd_primo_accesso
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default null
) is
/******************************************************************************
 NOME:        set_mod_pwd_primo_accesso
 DESCRIZIONE: Setter per attributo mod_pwd_primo_accesso di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_mod_pwd_primo_accesso'
           );
   update CARATTERISTICHE_ACCESSO
   set mod_pwd_primo_accesso = p_value
   where
   caac_id = p_caac_id
   ;
end set_mod_pwd_primo_accesso; -- caratteristiche_accesso_tpk.set_mod_pwd_primo_accesso
--------------------------------------------------------------------------------
procedure set_archiviazione_traccia
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default null
) is
/******************************************************************************
 NOME:        set_archiviazione_traccia
 DESCRIZIONE: Setter per attributo archiviazione_traccia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_archiviazione_traccia'
           );
   update CARATTERISTICHE_ACCESSO
   set archiviazione_traccia = p_value
   where
   caac_id = p_caac_id
   ;
end set_archiviazione_traccia; -- caratteristiche_accesso_tpk.set_archiviazione_traccia
--------------------------------------------------------------------------------
procedure set_dislocazione_traccia
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default null
) is
/******************************************************************************
 NOME:        set_dislocazione_traccia
 DESCRIZIONE: Setter per attributo dislocazione_traccia di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_dislocazione_traccia'
           );
   update CARATTERISTICHE_ACCESSO
   set dislocazione_traccia = p_value
   where
   caac_id = p_caac_id
   ;
end set_dislocazione_traccia; -- caratteristiche_accesso_tpk.set_dislocazione_traccia
--------------------------------------------------------------------------------
procedure set_ammessi_car_speciali_pwd
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default null
) is
/******************************************************************************
 NOME:        set_ammessi_car_speciali_pwd
 DESCRIZIONE: Setter per attributo ammessi_car_speciali_pwd di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_ammessi_car_speciali_pwd'
           );
   update CARATTERISTICHE_ACCESSO
   set ammessi_car_speciali_pwd = p_value
   where
   caac_id = p_caac_id
   ;
end set_ammessi_car_speciali_pwd; -- caratteristiche_accesso_tpk.set_ammessi_car_speciali_pwd
--------------------------------------------------------------------------------
procedure set_numeri_obb_pwd
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default null
) is
/******************************************************************************
 NOME:        set_numeri_obb_pwd
 DESCRIZIONE: Setter per attributo numeri_obb_pwd di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_numeri_obb_pwd'
           );
   update CARATTERISTICHE_ACCESSO
   set numeri_obb_pwd = p_value
   where
   caac_id = p_caac_id
   ;
end set_numeri_obb_pwd; -- caratteristiche_accesso_tpk.set_numeri_obb_pwd
--------------------------------------------------------------------------------
procedure set_gg_prima_riutilizzo_pwd
(
  p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
, p_value  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default null
) is
/******************************************************************************
 NOME:        set_gg_prima_riutilizzo_pwd
 DESCRIZIONE: Setter per attributo gg_prima_riutilizzo_pwd di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_caac_id => p_caac_id
                                        )
           , 'existsId on caratteristiche_accesso_tpk.set_gg_prima_riutilizzo_pwd'
           );
   update CARATTERISTICHE_ACCESSO
   set gg_prima_riutilizzo_pwd = p_value
   where
   caac_id = p_caac_id
   ;
end set_gg_prima_riutilizzo_pwd; -- caratteristiche_accesso_tpk.set_gg_prima_riutilizzo_pwd
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_caac_id  in varchar2 default null
, p_tipo_accesso  in varchar2 default null
, p_progetto  in varchar2 default null
, p_istanza  in varchar2 default null
, p_modulo  in varchar2 default null
, p_utente  in varchar2 default null
, p_accesso  in varchar2 default null
, p_accesso_se  in varchar2 default null
, p_traccia  in varchar2 default null
, p_giorni_traccia  in varchar2 default null
, p_tentativi_password  in varchar2 default null
, p_validita_password  in varchar2 default null
, p_single_sign_on  in varchar2 default null
, p_sleep  in varchar2 default null
, p_ldap  in varchar2 default null
, p_min_lunghezza_pwd  in varchar2 default null
, p_mod_pwd_primo_accesso  in varchar2 default null
, p_archiviazione_traccia  in varchar2 default null
, p_dislocazione_traccia  in varchar2 default null
, p_ammessi_car_speciali_pwd  in varchar2 default null
, p_numeri_obb_pwd  in varchar2 default null
, p_gg_prima_riutilizzo_pwd  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( caac_id ', p_caac_id, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( tipo_accesso ', p_tipo_accesso , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( progetto ', p_progetto , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( istanza ', p_istanza , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( modulo ', p_modulo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( utente ', p_utente , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( accesso ', p_accesso , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( accesso_se ', p_accesso_se , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( traccia ', p_traccia , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( giorni_traccia ', p_giorni_traccia , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( tentativi_password ', p_tentativi_password , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( validita_password ', p_validita_password , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( single_sign_on ', p_single_sign_on , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( sleep ', p_sleep , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( ldap ', p_ldap , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( min_lunghezza_pwd ', p_min_lunghezza_pwd , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( mod_pwd_primo_accesso ', p_mod_pwd_primo_accesso , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( archiviazione_traccia ', p_archiviazione_traccia , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( dislocazione_traccia ', p_dislocazione_traccia , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( ammessi_car_speciali_pwd ', p_ammessi_car_speciali_pwd , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( numeri_obb_pwd ', p_numeri_obb_pwd , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( gg_prima_riutilizzo_pwd ', p_gg_prima_riutilizzo_pwd , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- caratteristiche_accesso_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_caac_id  in varchar2 default null
, p_tipo_accesso  in varchar2 default null
, p_progetto  in varchar2 default null
, p_istanza  in varchar2 default null
, p_modulo  in varchar2 default null
, p_utente  in varchar2 default null
, p_accesso  in varchar2 default null
, p_accesso_se  in varchar2 default null
, p_traccia  in varchar2 default null
, p_giorni_traccia  in varchar2 default null
, p_tentativi_password  in varchar2 default null
, p_validita_password  in varchar2 default null
, p_single_sign_on  in varchar2 default null
, p_sleep  in varchar2 default null
, p_ldap  in varchar2 default null
, p_min_lunghezza_pwd  in varchar2 default null
, p_mod_pwd_primo_accesso  in varchar2 default null
, p_archiviazione_traccia  in varchar2 default null
, p_dislocazione_traccia  in varchar2 default null
, p_ammessi_car_speciali_pwd  in varchar2 default null
, p_numeri_obb_pwd  in varchar2 default null
, p_gg_prima_riutilizzo_pwd  in varchar2 default null
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
   d_statement := ' select CARATTERISTICHE_ACCESSO.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from CARATTERISTICHE_ACCESSO '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_caac_id => p_caac_id
                                 , p_tipo_accesso => p_tipo_accesso
                                 , p_progetto => p_progetto
                                 , p_istanza => p_istanza
                                 , p_modulo => p_modulo
                                 , p_utente => p_utente
                                 , p_accesso => p_accesso
                                 , p_accesso_se => p_accesso_se
                                 , p_traccia => p_traccia
                                 , p_giorni_traccia => p_giorni_traccia
                                 , p_tentativi_password => p_tentativi_password
                                 , p_validita_password => p_validita_password
                                 , p_single_sign_on => p_single_sign_on
                                 , p_sleep => p_sleep
                                 , p_ldap => p_ldap
                                 , p_min_lunghezza_pwd => p_min_lunghezza_pwd
                                 , p_mod_pwd_primo_accesso => p_mod_pwd_primo_accesso
                                 , p_archiviazione_traccia => p_archiviazione_traccia
                                 , p_dislocazione_traccia => p_dislocazione_traccia
                                 , p_ammessi_car_speciali_pwd => p_ammessi_car_speciali_pwd
                                 , p_numeri_obb_pwd => p_numeri_obb_pwd
                                 , p_gg_prima_riutilizzo_pwd => p_gg_prima_riutilizzo_pwd
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- caratteristiche_accesso_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_caac_id  in varchar2 default null
, p_tipo_accesso  in varchar2 default null
, p_progetto  in varchar2 default null
, p_istanza  in varchar2 default null
, p_modulo  in varchar2 default null
, p_utente  in varchar2 default null
, p_accesso  in varchar2 default null
, p_accesso_se  in varchar2 default null
, p_traccia  in varchar2 default null
, p_giorni_traccia  in varchar2 default null
, p_tentativi_password  in varchar2 default null
, p_validita_password  in varchar2 default null
, p_single_sign_on  in varchar2 default null
, p_sleep  in varchar2 default null
, p_ldap  in varchar2 default null
, p_min_lunghezza_pwd  in varchar2 default null
, p_mod_pwd_primo_accesso  in varchar2 default null
, p_archiviazione_traccia  in varchar2 default null
, p_dislocazione_traccia  in varchar2 default null
, p_ammessi_car_speciali_pwd  in varchar2 default null
, p_numeri_obb_pwd  in varchar2 default null
, p_gg_prima_riutilizzo_pwd  in varchar2 default null
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
   d_statement := ' select count( * ) from CARATTERISTICHE_ACCESSO '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_caac_id => p_caac_id
                                 , p_tipo_accesso => p_tipo_accesso
                                 , p_progetto => p_progetto
                                 , p_istanza => p_istanza
                                 , p_modulo => p_modulo
                                 , p_utente => p_utente
                                 , p_accesso => p_accesso
                                 , p_accesso_se => p_accesso_se
                                 , p_traccia => p_traccia
                                 , p_giorni_traccia => p_giorni_traccia
                                 , p_tentativi_password => p_tentativi_password
                                 , p_validita_password => p_validita_password
                                 , p_single_sign_on => p_single_sign_on
                                 , p_sleep => p_sleep
                                 , p_ldap => p_ldap
                                 , p_min_lunghezza_pwd => p_min_lunghezza_pwd
                                 , p_mod_pwd_primo_accesso => p_mod_pwd_primo_accesso
                                 , p_archiviazione_traccia => p_archiviazione_traccia
                                 , p_dislocazione_traccia => p_dislocazione_traccia
                                 , p_ammessi_car_speciali_pwd => p_ammessi_car_speciali_pwd
                                 , p_numeri_obb_pwd => p_numeri_obb_pwd
                                 , p_gg_prima_riutilizzo_pwd => p_gg_prima_riutilizzo_pwd
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- caratteristiche_accesso_tpk.count_rows
--------------------------------------------------------------------------------

end caratteristiche_accesso_tpk;
/

