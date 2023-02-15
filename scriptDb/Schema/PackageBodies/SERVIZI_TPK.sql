CREATE OR REPLACE PACKAGE BODY servizi_tpk is
/******************************************************************************
 NOME:        servizi_tpk
 DESCRIZIONE: Gestione tabella SERVIZI.
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
end versione; -- servizi_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_id_servizio  in SERVIZI.id_servizio%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.id_servizio := p_id_servizio;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_id_servizio => d_result.id_servizio
                                        )
           , 'canHandle on servizi_tpk.PK'
           );
   return  d_result;
end PK; -- servizi_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_id_servizio  in SERVIZI.id_servizio%type
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
          p_id_servizio is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on servizi_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- servizi_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_id_servizio  in SERVIZI.id_servizio%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_id_servizio => p_id_servizio
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- servizi_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_id_servizio  in SERVIZI.id_servizio%type
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
                                         p_id_servizio => p_id_servizio
                                        )
           , 'canHandle on servizi_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   SERVIZI
      where
      id_servizio = p_id_servizio
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on servizi_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- servizi_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_id_servizio  in SERVIZI.id_servizio%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_id_servizio => p_id_servizio
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- servizi_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_id_servizio  in SERVIZI.id_servizio%type default null
, p_modulo  in SERVIZI.modulo%type
, p_istanza  in SERVIZI.istanza%type
, p_livello  in SERVIZI.livello%type default null
, p_abilitazione  in SERVIZI.abilitazione%type default 'C'
, p_gruppo_abilitazione  in SERVIZI.gruppo_abilitazione%type
, p_mail_notifiche  in SERVIZI.mail_notifiche%type
, p_ccr_notifiche  in SERVIZI.ccr_notifiche%type default null
, p_multiplo  in SERVIZI.multiplo%type default 'N'
, p_tipo_notifica  in SERVIZI.tipo_notifica%type default null
, p_recupero_password  in SERVIZI.recupero_password%type default 'N'
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ '' is not null
           , 'p_modulo on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_istanza is not null or /*default value*/ '' is not null
           , 'p_istanza on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_livello is not null or /*default value*/ 'default' is not null
           , 'p_livello on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_abilitazione is not null or /*default value*/ 'default' is not null
           , 'p_abilitazione on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_gruppo_abilitazione is not null or /*default value*/ '' is not null
           , 'p_gruppo_abilitazione on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_mail_notifiche is not null or /*default value*/ '' is not null
           , 'p_mail_notifiche on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ccr_notifiche is not null or /*default value*/ 'default' is not null
           , 'p_ccr_notifiche on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_multiplo is not null or /*default value*/ 'default' is not null
           , 'p_multiplo on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_tipo_notifica is not null or /*default value*/ 'default' is not null
           , 'p_tipo_notifica on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_recupero_password is not null or /*default value*/ 'default' is not null
           , 'p_recupero_password on servizi_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id_servizio is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id_servizio => p_id_servizio
                           )
           , 'not existsId on servizi_tpk.ins'
           );
   insert into SERVIZI
   (
     id_servizio
   , modulo
   , istanza
   , livello
   , abilitazione
   , gruppo_abilitazione
   , mail_notifiche
   , ccr_notifiche
   , multiplo
   , tipo_notifica
   , recupero_password
   )
   values
   (
     p_id_servizio
   , p_modulo
   , p_istanza
   , p_livello
   , p_abilitazione
   , p_gruppo_abilitazione
   , p_mail_notifiche
   , p_ccr_notifiche
   , p_multiplo
   , p_tipo_notifica
   , p_recupero_password
   );
end ins; -- servizi_tpk.ins
--------------------------------------------------------------------------------
function ins  /*+ SOA  */
(
  p_id_servizio  in SERVIZI.id_servizio%type default null
, p_modulo  in SERVIZI.modulo%type
, p_istanza  in SERVIZI.istanza%type
, p_livello  in SERVIZI.livello%type default null
, p_abilitazione  in SERVIZI.abilitazione%type default 'C'
, p_gruppo_abilitazione  in SERVIZI.gruppo_abilitazione%type
, p_mail_notifiche  in SERVIZI.mail_notifiche%type
, p_ccr_notifiche  in SERVIZI.ccr_notifiche%type default null
, p_multiplo  in SERVIZI.multiplo%type default 'N'
, p_tipo_notifica  in SERVIZI.tipo_notifica%type default null
, p_recupero_password  in SERVIZI.recupero_password%type default 'N'
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
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ '' is not null
           , 'p_modulo on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_istanza is not null or /*default value*/ '' is not null
           , 'p_istanza on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_livello is not null or /*default value*/ 'default' is not null
           , 'p_livello on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_abilitazione is not null or /*default value*/ 'default' is not null
           , 'p_abilitazione on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_gruppo_abilitazione is not null or /*default value*/ '' is not null
           , 'p_gruppo_abilitazione on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_mail_notifiche is not null or /*default value*/ '' is not null
           , 'p_mail_notifiche on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ccr_notifiche is not null or /*default value*/ 'default' is not null
           , 'p_ccr_notifiche on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_multiplo is not null or /*default value*/ 'default' is not null
           , 'p_multiplo on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_tipo_notifica is not null or /*default value*/ 'default' is not null
           , 'p_tipo_notifica on servizi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_recupero_password is not null or /*default value*/ 'default' is not null
           , 'p_recupero_password on servizi_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_id_servizio is null and /*default value*/ 'default null' is not null ) -- PK nullable on insert
           or not existsId (
                             p_id_servizio => p_id_servizio
                           )
           , 'not existsId on servizi_tpk.ins'
           );
   begin
      insert into SERVIZI
      (
        id_servizio
      , modulo
      , istanza
      , livello
      , abilitazione
      , gruppo_abilitazione
      , mail_notifiche
      , ccr_notifiche
      , multiplo
      , tipo_notifica
      , recupero_password
      )
      values
      (
        p_id_servizio
      , p_modulo
      , p_istanza
      , p_livello
      , p_abilitazione
      , p_gruppo_abilitazione
      , p_mail_notifiche
      , p_ccr_notifiche
      , p_multiplo
      , p_tipo_notifica
      , p_recupero_password
      ) returning id_servizio
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
end ins; -- servizi_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_id_servizio  in SERVIZI.id_servizio%type
, p_OLD_id_servizio  in SERVIZI.id_servizio%type default null
, p_NEW_modulo  in SERVIZI.modulo%type default null
, p_OLD_modulo  in SERVIZI.modulo%type default null
, p_NEW_istanza  in SERVIZI.istanza%type default null
, p_OLD_istanza  in SERVIZI.istanza%type default null
, p_NEW_livello  in SERVIZI.livello%type default null
, p_OLD_livello  in SERVIZI.livello%type default null
, p_NEW_abilitazione  in SERVIZI.abilitazione%type default null
, p_OLD_abilitazione  in SERVIZI.abilitazione%type default null
, p_NEW_gruppo_abilitazione  in SERVIZI.gruppo_abilitazione%type default null
, p_OLD_gruppo_abilitazione  in SERVIZI.gruppo_abilitazione%type default null
, p_NEW_mail_notifiche  in SERVIZI.mail_notifiche%type default null
, p_OLD_mail_notifiche  in SERVIZI.mail_notifiche%type default null
, p_NEW_ccr_notifiche  in SERVIZI.ccr_notifiche%type default null
, p_OLD_ccr_notifiche  in SERVIZI.ccr_notifiche%type default null
, p_NEW_multiplo  in SERVIZI.multiplo%type default null
, p_OLD_multiplo  in SERVIZI.multiplo%type default null
, p_NEW_tipo_notifica  in SERVIZI.tipo_notifica%type default null
, p_OLD_tipo_notifica  in SERVIZI.tipo_notifica%type default null
, p_NEW_recupero_password  in SERVIZI.recupero_password%type default null
, p_OLD_recupero_password  in SERVIZI.recupero_password%type default null
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
p_OLD_modulo is not null or
p_OLD_istanza is not null or
p_OLD_livello is not null or
p_OLD_abilitazione is not null or
p_OLD_gruppo_abilitazione is not null or
p_OLD_mail_notifiche is not null or
p_OLD_ccr_notifiche is not null or
p_OLD_multiplo is not null or
p_OLD_tipo_notifica is not null or
p_OLD_recupero_password is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on servizi_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_id_servizio, p_NEW_id_servizio )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id_servizio => d_key.id_servizio
                                       )
           , 'existsId on servizi_tpk.upd'
           );
   update SERVIZI
   set
       id_servizio = decode( p_check_OLD, 0,p_NEW_id_servizio, decode(p_NEW_id_servizio, p_OLD_id_servizio,id_servizio,p_NEW_id_servizio))
     , modulo = decode( p_check_OLD, 0,p_NEW_modulo, decode(p_NEW_modulo, p_OLD_modulo,modulo,p_NEW_modulo))
     , istanza = decode( p_check_OLD, 0,p_NEW_istanza, decode(p_NEW_istanza, p_OLD_istanza,istanza,p_NEW_istanza))
     , livello = decode( p_check_OLD, 0,p_NEW_livello, decode(p_NEW_livello, p_OLD_livello,livello,p_NEW_livello))
     , abilitazione = decode( p_check_OLD, 0,p_NEW_abilitazione, decode(p_NEW_abilitazione, p_OLD_abilitazione,abilitazione,p_NEW_abilitazione))
     , gruppo_abilitazione = decode( p_check_OLD, 0,p_NEW_gruppo_abilitazione, decode(p_NEW_gruppo_abilitazione, p_OLD_gruppo_abilitazione,gruppo_abilitazione,p_NEW_gruppo_abilitazione))
     , mail_notifiche = decode( p_check_OLD, 0,p_NEW_mail_notifiche, decode(p_NEW_mail_notifiche, p_OLD_mail_notifiche,mail_notifiche,p_NEW_mail_notifiche))
     , ccr_notifiche = decode( p_check_OLD, 0,p_NEW_ccr_notifiche, decode(p_NEW_ccr_notifiche, p_OLD_ccr_notifiche,ccr_notifiche,p_NEW_ccr_notifiche))
     , multiplo = decode( p_check_OLD, 0,p_NEW_multiplo, decode(p_NEW_multiplo, p_OLD_multiplo,multiplo,p_NEW_multiplo))
     , tipo_notifica = decode( p_check_OLD, 0,p_NEW_tipo_notifica, decode(p_NEW_tipo_notifica, p_OLD_tipo_notifica,tipo_notifica,p_NEW_tipo_notifica))
     , recupero_password = decode( p_check_OLD, 0,p_NEW_recupero_password, decode(p_NEW_recupero_password, p_OLD_recupero_password,recupero_password,p_NEW_recupero_password))
   where
     id_servizio = d_key.id_servizio
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( modulo = p_OLD_modulo or ( p_OLD_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( istanza = p_OLD_istanza or ( p_OLD_istanza is null and ( p_check_OLD is null or istanza is null ) ) )
           and ( livello = p_OLD_livello or ( p_OLD_livello is null and ( p_check_OLD is null or livello is null ) ) )
           and ( abilitazione = p_OLD_abilitazione or ( p_OLD_abilitazione is null and ( p_check_OLD is null or abilitazione is null ) ) )
           and ( gruppo_abilitazione = p_OLD_gruppo_abilitazione or ( p_OLD_gruppo_abilitazione is null and ( p_check_OLD is null or gruppo_abilitazione is null ) ) )
           and ( mail_notifiche = p_OLD_mail_notifiche or ( p_OLD_mail_notifiche is null and ( p_check_OLD is null or mail_notifiche is null ) ) )
           and ( ccr_notifiche = p_OLD_ccr_notifiche or ( p_OLD_ccr_notifiche is null and ( p_check_OLD is null or ccr_notifiche is null ) ) )
           and ( multiplo = p_OLD_multiplo or ( p_OLD_multiplo is null and ( p_check_OLD is null or multiplo is null ) ) )
           and ( tipo_notifica = p_OLD_tipo_notifica or ( p_OLD_tipo_notifica is null and ( p_check_OLD is null or tipo_notifica is null ) ) )
           and ( recupero_password = p_OLD_recupero_password or ( p_OLD_recupero_password is null and ( p_check_OLD is null or recupero_password is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on servizi_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- servizi_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_id_servizio  in SERVIZI.id_servizio%type
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
                                        p_id_servizio => p_id_servizio
                                       )
           , 'existsId on servizi_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on servizi_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on servizi_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on servizi_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update SERVIZI '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( id_servizio ', p_id_servizio, ' )', 0, null ), ' and ( id_servizio is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- servizi_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_id_servizio  in SERVIZI.id_servizio%type
, p_modulo  in SERVIZI.modulo%type default null
, p_istanza  in SERVIZI.istanza%type default null
, p_livello  in SERVIZI.livello%type default null
, p_abilitazione  in SERVIZI.abilitazione%type default null
, p_gruppo_abilitazione  in SERVIZI.gruppo_abilitazione%type default null
, p_mail_notifiche  in SERVIZI.mail_notifiche%type default null
, p_ccr_notifiche  in SERVIZI.ccr_notifiche%type default null
, p_multiplo  in SERVIZI.multiplo%type default null
, p_tipo_notifica  in SERVIZI.tipo_notifica%type default null
, p_recupero_password  in SERVIZI.recupero_password%type default null
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
p_modulo is not null or
p_istanza is not null or
p_livello is not null or
p_abilitazione is not null or
p_gruppo_abilitazione is not null or
p_mail_notifiche is not null or
p_ccr_notifiche is not null or
p_multiplo is not null or
p_tipo_notifica is not null or
p_recupero_password is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on servizi_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_id_servizio => p_id_servizio
                                       )
           , 'existsId on servizi_tpk.del'
           );
   delete from SERVIZI
   where
     id_servizio = p_id_servizio
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( modulo = p_modulo or ( p_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( istanza = p_istanza or ( p_istanza is null and ( p_check_OLD is null or istanza is null ) ) )
           and ( livello = p_livello or ( p_livello is null and ( p_check_OLD is null or livello is null ) ) )
           and ( abilitazione = p_abilitazione or ( p_abilitazione is null and ( p_check_OLD is null or abilitazione is null ) ) )
           and ( gruppo_abilitazione = p_gruppo_abilitazione or ( p_gruppo_abilitazione is null and ( p_check_OLD is null or gruppo_abilitazione is null ) ) )
           and ( mail_notifiche = p_mail_notifiche or ( p_mail_notifiche is null and ( p_check_OLD is null or mail_notifiche is null ) ) )
           and ( ccr_notifiche = p_ccr_notifiche or ( p_ccr_notifiche is null and ( p_check_OLD is null or ccr_notifiche is null ) ) )
           and ( multiplo = p_multiplo or ( p_multiplo is null and ( p_check_OLD is null or multiplo is null ) ) )
           and ( tipo_notifica = p_tipo_notifica or ( p_tipo_notifica is null and ( p_check_OLD is null or tipo_notifica is null ) ) )
           and ( recupero_password = p_recupero_password or ( p_recupero_password is null and ( p_check_OLD is null or recupero_password is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on servizi_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_id_servizio => p_id_servizio
                                             )
            , 'existsId on servizi_tpk.del'
            );
end del; -- servizi_tpk.del
--------------------------------------------------------------------------------
function get_modulo
(
  p_id_servizio  in SERVIZI.id_servizio%type
) return SERVIZI.modulo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_modulo
 DESCRIZIONE: Getter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SERVIZI.modulo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SERVIZI.modulo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.get_modulo'
           );
   select modulo
   into   d_result
   from   SERVIZI
   where
   id_servizio = p_id_servizio
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on servizi_tpk.get_modulo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'modulo')
                    , ' AFC_DDL.IsNullable on servizi_tpk.get_modulo'
                    );
   end if;
   return  d_result;
end get_modulo; -- servizi_tpk.get_modulo
--------------------------------------------------------------------------------
function get_istanza
(
  p_id_servizio  in SERVIZI.id_servizio%type
) return SERVIZI.istanza%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_istanza
 DESCRIZIONE: Getter per attributo istanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SERVIZI.istanza%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SERVIZI.istanza%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.get_istanza'
           );
   select istanza
   into   d_result
   from   SERVIZI
   where
   id_servizio = p_id_servizio
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on servizi_tpk.get_istanza'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'istanza')
                    , ' AFC_DDL.IsNullable on servizi_tpk.get_istanza'
                    );
   end if;
   return  d_result;
end get_istanza; -- servizi_tpk.get_istanza
--------------------------------------------------------------------------------
function get_livello
(
  p_id_servizio  in SERVIZI.id_servizio%type
) return SERVIZI.livello%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_livello
 DESCRIZIONE: Getter per attributo livello di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SERVIZI.livello%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SERVIZI.livello%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.get_livello'
           );
   select livello
   into   d_result
   from   SERVIZI
   where
   id_servizio = p_id_servizio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on servizi_tpk.get_livello'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'livello')
                    , ' AFC_DDL.IsNullable on servizi_tpk.get_livello'
                    );
   end if;
   return  d_result;
end get_livello; -- servizi_tpk.get_livello
--------------------------------------------------------------------------------
function get_abilitazione
(
  p_id_servizio  in SERVIZI.id_servizio%type
) return SERVIZI.abilitazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_abilitazione
 DESCRIZIONE: Getter per attributo abilitazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SERVIZI.abilitazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SERVIZI.abilitazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.get_abilitazione'
           );
   select abilitazione
   into   d_result
   from   SERVIZI
   where
   id_servizio = p_id_servizio
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on servizi_tpk.get_abilitazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'abilitazione')
                    , ' AFC_DDL.IsNullable on servizi_tpk.get_abilitazione'
                    );
   end if;
   return  d_result;
end get_abilitazione; -- servizi_tpk.get_abilitazione
--------------------------------------------------------------------------------
function get_gruppo_abilitazione
(
  p_id_servizio  in SERVIZI.id_servizio%type
) return SERVIZI.gruppo_abilitazione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_gruppo_abilitazione
 DESCRIZIONE: Getter per attributo gruppo_abilitazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SERVIZI.gruppo_abilitazione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SERVIZI.gruppo_abilitazione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.get_gruppo_abilitazione'
           );
   select gruppo_abilitazione
   into   d_result
   from   SERVIZI
   where
   id_servizio = p_id_servizio
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on servizi_tpk.get_gruppo_abilitazione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'gruppo_abilitazione')
                    , ' AFC_DDL.IsNullable on servizi_tpk.get_gruppo_abilitazione'
                    );
   end if;
   return  d_result;
end get_gruppo_abilitazione; -- servizi_tpk.get_gruppo_abilitazione
--------------------------------------------------------------------------------
function get_mail_notifiche
(
  p_id_servizio  in SERVIZI.id_servizio%type
) return SERVIZI.mail_notifiche%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_mail_notifiche
 DESCRIZIONE: Getter per attributo mail_notifiche di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SERVIZI.mail_notifiche%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SERVIZI.mail_notifiche%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.get_mail_notifiche'
           );
   select mail_notifiche
   into   d_result
   from   SERVIZI
   where
   id_servizio = p_id_servizio
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on servizi_tpk.get_mail_notifiche'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'mail_notifiche')
                    , ' AFC_DDL.IsNullable on servizi_tpk.get_mail_notifiche'
                    );
   end if;
   return  d_result;
end get_mail_notifiche; -- servizi_tpk.get_mail_notifiche
--------------------------------------------------------------------------------
function get_ccr_notifiche
(
  p_id_servizio  in SERVIZI.id_servizio%type
) return SERVIZI.ccr_notifiche%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_ccr_notifiche
 DESCRIZIONE: Getter per attributo ccr_notifiche di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SERVIZI.ccr_notifiche%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SERVIZI.ccr_notifiche%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.get_ccr_notifiche'
           );
   select ccr_notifiche
   into   d_result
   from   SERVIZI
   where
   id_servizio = p_id_servizio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on servizi_tpk.get_ccr_notifiche'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'ccr_notifiche')
                    , ' AFC_DDL.IsNullable on servizi_tpk.get_ccr_notifiche'
                    );
   end if;
   return  d_result;
end get_ccr_notifiche; -- servizi_tpk.get_ccr_notifiche
--------------------------------------------------------------------------------
function get_multiplo
(
  p_id_servizio  in SERVIZI.id_servizio%type
) return SERVIZI.multiplo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_multiplo
 DESCRIZIONE: Getter per attributo multiplo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SERVIZI.multiplo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SERVIZI.multiplo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.get_multiplo'
           );
   select multiplo
   into   d_result
   from   SERVIZI
   where
   id_servizio = p_id_servizio
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on servizi_tpk.get_multiplo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'multiplo')
                    , ' AFC_DDL.IsNullable on servizi_tpk.get_multiplo'
                    );
   end if;
   return  d_result;
end get_multiplo; -- servizi_tpk.get_multiplo
--------------------------------------------------------------------------------
function get_tipo_notifica
(
  p_id_servizio  in SERVIZI.id_servizio%type
) return SERVIZI.tipo_notifica%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_tipo_notifica
 DESCRIZIONE: Getter per attributo tipo_notifica di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SERVIZI.tipo_notifica%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SERVIZI.tipo_notifica%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.get_tipo_notifica'
           );
   select tipo_notifica
   into   d_result
   from   SERVIZI
   where
   id_servizio = p_id_servizio
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on servizi_tpk.get_tipo_notifica'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'tipo_notifica')
                    , ' AFC_DDL.IsNullable on servizi_tpk.get_tipo_notifica'
                    );
   end if;
   return  d_result;
end get_tipo_notifica; -- servizi_tpk.get_tipo_notifica
--------------------------------------------------------------------------------
function get_recupero_password
(
  p_id_servizio  in SERVIZI.id_servizio%type
) return SERVIZI.recupero_password%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_recupero_password
 DESCRIZIONE: Getter per attributo recupero_password di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     SERVIZI.recupero_password%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result SERVIZI.recupero_password%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.get_recupero_password'
           );
   select recupero_password
   into   d_result
   from   SERVIZI
   where
   id_servizio = p_id_servizio
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on servizi_tpk.get_recupero_password'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'recupero_password')
                    , ' AFC_DDL.IsNullable on servizi_tpk.get_recupero_password'
                    );
   end if;
   return  d_result;
end get_recupero_password; -- servizi_tpk.get_recupero_password
--------------------------------------------------------------------------------
procedure set_id_servizio
(
  p_id_servizio  in SERVIZI.id_servizio%type
, p_value  in SERVIZI.id_servizio%type default null
) is
/******************************************************************************
 NOME:        set_id_servizio
 DESCRIZIONE: Setter per attributo id_servizio di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.set_id_servizio'
           );
   update SERVIZI
   set id_servizio = p_value
   where
   id_servizio = p_id_servizio
   ;
end set_id_servizio; -- servizi_tpk.set_id_servizio
--------------------------------------------------------------------------------
procedure set_modulo
(
  p_id_servizio  in SERVIZI.id_servizio%type
, p_value  in SERVIZI.modulo%type default null
) is
/******************************************************************************
 NOME:        set_modulo
 DESCRIZIONE: Setter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.set_modulo'
           );
   update SERVIZI
   set modulo = p_value
   where
   id_servizio = p_id_servizio
   ;
end set_modulo; -- servizi_tpk.set_modulo
--------------------------------------------------------------------------------
procedure set_istanza
(
  p_id_servizio  in SERVIZI.id_servizio%type
, p_value  in SERVIZI.istanza%type default null
) is
/******************************************************************************
 NOME:        set_istanza
 DESCRIZIONE: Setter per attributo istanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.set_istanza'
           );
   update SERVIZI
   set istanza = p_value
   where
   id_servizio = p_id_servizio
   ;
end set_istanza; -- servizi_tpk.set_istanza
--------------------------------------------------------------------------------
procedure set_livello
(
  p_id_servizio  in SERVIZI.id_servizio%type
, p_value  in SERVIZI.livello%type default null
) is
/******************************************************************************
 NOME:        set_livello
 DESCRIZIONE: Setter per attributo livello di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.set_livello'
           );
   update SERVIZI
   set livello = p_value
   where
   id_servizio = p_id_servizio
   ;
end set_livello; -- servizi_tpk.set_livello
--------------------------------------------------------------------------------
procedure set_abilitazione
(
  p_id_servizio  in SERVIZI.id_servizio%type
, p_value  in SERVIZI.abilitazione%type default null
) is
/******************************************************************************
 NOME:        set_abilitazione
 DESCRIZIONE: Setter per attributo abilitazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.set_abilitazione'
           );
   update SERVIZI
   set abilitazione = p_value
   where
   id_servizio = p_id_servizio
   ;
end set_abilitazione; -- servizi_tpk.set_abilitazione
--------------------------------------------------------------------------------
procedure set_gruppo_abilitazione
(
  p_id_servizio  in SERVIZI.id_servizio%type
, p_value  in SERVIZI.gruppo_abilitazione%type default null
) is
/******************************************************************************
 NOME:        set_gruppo_abilitazione
 DESCRIZIONE: Setter per attributo gruppo_abilitazione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.set_gruppo_abilitazione'
           );
   update SERVIZI
   set gruppo_abilitazione = p_value
   where
   id_servizio = p_id_servizio
   ;
end set_gruppo_abilitazione; -- servizi_tpk.set_gruppo_abilitazione
--------------------------------------------------------------------------------
procedure set_mail_notifiche
(
  p_id_servizio  in SERVIZI.id_servizio%type
, p_value  in SERVIZI.mail_notifiche%type default null
) is
/******************************************************************************
 NOME:        set_mail_notifiche
 DESCRIZIONE: Setter per attributo mail_notifiche di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.set_mail_notifiche'
           );
   update SERVIZI
   set mail_notifiche = p_value
   where
   id_servizio = p_id_servizio
   ;
end set_mail_notifiche; -- servizi_tpk.set_mail_notifiche
--------------------------------------------------------------------------------
procedure set_ccr_notifiche
(
  p_id_servizio  in SERVIZI.id_servizio%type
, p_value  in SERVIZI.ccr_notifiche%type default null
) is
/******************************************************************************
 NOME:        set_ccr_notifiche
 DESCRIZIONE: Setter per attributo ccr_notifiche di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.set_ccr_notifiche'
           );
   update SERVIZI
   set ccr_notifiche = p_value
   where
   id_servizio = p_id_servizio
   ;
end set_ccr_notifiche; -- servizi_tpk.set_ccr_notifiche
--------------------------------------------------------------------------------
procedure set_multiplo
(
  p_id_servizio  in SERVIZI.id_servizio%type
, p_value  in SERVIZI.multiplo%type default null
) is
/******************************************************************************
 NOME:        set_multiplo
 DESCRIZIONE: Setter per attributo multiplo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.set_multiplo'
           );
   update SERVIZI
   set multiplo = p_value
   where
   id_servizio = p_id_servizio
   ;
end set_multiplo; -- servizi_tpk.set_multiplo
--------------------------------------------------------------------------------
procedure set_tipo_notifica
(
  p_id_servizio  in SERVIZI.id_servizio%type
, p_value  in SERVIZI.tipo_notifica%type default null
) is
/******************************************************************************
 NOME:        set_tipo_notifica
 DESCRIZIONE: Setter per attributo tipo_notifica di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.set_tipo_notifica'
           );
   update SERVIZI
   set tipo_notifica = p_value
   where
   id_servizio = p_id_servizio
   ;
end set_tipo_notifica; -- servizi_tpk.set_tipo_notifica
--------------------------------------------------------------------------------
procedure set_recupero_password
(
  p_id_servizio  in SERVIZI.id_servizio%type
, p_value  in SERVIZI.recupero_password%type default null
) is
/******************************************************************************
 NOME:        set_recupero_password
 DESCRIZIONE: Setter per attributo recupero_password di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_id_servizio => p_id_servizio
                                        )
           , 'existsId on servizi_tpk.set_recupero_password'
           );
   update SERVIZI
   set recupero_password = p_value
   where
   id_servizio = p_id_servizio
   ;
end set_recupero_password; -- servizi_tpk.set_recupero_password
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id_servizio  in varchar2 default null
, p_modulo  in varchar2 default null
, p_istanza  in varchar2 default null
, p_livello  in varchar2 default null
, p_abilitazione  in varchar2 default null
, p_gruppo_abilitazione  in varchar2 default null
, p_mail_notifiche  in varchar2 default null
, p_ccr_notifiche  in varchar2 default null
, p_multiplo  in varchar2 default null
, p_tipo_notifica  in varchar2 default null
, p_recupero_password  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( id_servizio ', p_id_servizio, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( modulo ', p_modulo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( istanza ', p_istanza , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( livello ', p_livello , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( abilitazione ', p_abilitazione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( gruppo_abilitazione ', p_gruppo_abilitazione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( mail_notifiche ', p_mail_notifiche , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( ccr_notifiche ', p_ccr_notifiche , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( multiplo ', p_multiplo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( tipo_notifica ', p_tipo_notifica , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( recupero_password ', p_recupero_password , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- servizi_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_id_servizio  in varchar2 default null
, p_modulo  in varchar2 default null
, p_istanza  in varchar2 default null
, p_livello  in varchar2 default null
, p_abilitazione  in varchar2 default null
, p_gruppo_abilitazione  in varchar2 default null
, p_mail_notifiche  in varchar2 default null
, p_ccr_notifiche  in varchar2 default null
, p_multiplo  in varchar2 default null
, p_tipo_notifica  in varchar2 default null
, p_recupero_password  in varchar2 default null
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
   d_statement := ' select SERVIZI.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from SERVIZI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id_servizio => p_id_servizio
                                 , p_modulo => p_modulo
                                 , p_istanza => p_istanza
                                 , p_livello => p_livello
                                 , p_abilitazione => p_abilitazione
                                 , p_gruppo_abilitazione => p_gruppo_abilitazione
                                 , p_mail_notifiche => p_mail_notifiche
                                 , p_ccr_notifiche => p_ccr_notifiche
                                 , p_multiplo => p_multiplo
                                 , p_tipo_notifica => p_tipo_notifica
                                 , p_recupero_password => p_recupero_password
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- servizi_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_id_servizio  in varchar2 default null
, p_modulo  in varchar2 default null
, p_istanza  in varchar2 default null
, p_livello  in varchar2 default null
, p_abilitazione  in varchar2 default null
, p_gruppo_abilitazione  in varchar2 default null
, p_mail_notifiche  in varchar2 default null
, p_ccr_notifiche  in varchar2 default null
, p_multiplo  in varchar2 default null
, p_tipo_notifica  in varchar2 default null
, p_recupero_password  in varchar2 default null
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
   d_statement := ' select count( * ) from SERVIZI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_id_servizio => p_id_servizio
                                 , p_modulo => p_modulo
                                 , p_istanza => p_istanza
                                 , p_livello => p_livello
                                 , p_abilitazione => p_abilitazione
                                 , p_gruppo_abilitazione => p_gruppo_abilitazione
                                 , p_mail_notifiche => p_mail_notifiche
                                 , p_ccr_notifiche => p_ccr_notifiche
                                 , p_multiplo => p_multiplo
                                 , p_tipo_notifica => p_tipo_notifica
                                 , p_recupero_password => p_recupero_password
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- servizi_tpk.count_rows
--------------------------------------------------------------------------------
end servizi_tpk;
/

