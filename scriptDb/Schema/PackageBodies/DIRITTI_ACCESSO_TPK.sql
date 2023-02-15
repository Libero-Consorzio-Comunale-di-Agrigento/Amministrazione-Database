CREATE OR REPLACE PACKAGE BODY diritti_accesso_tpk is
/******************************************************************************
 NOME:        diritti_accesso_tpk
 DESCRIZIONE: Gestione tabella DIRITTI_ACCESSO.
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
end versione; -- diritti_accesso_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.utente := p_utente;
d_result.modulo := p_modulo;
d_result.istanza := p_istanza;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_utente => d_result.utente,
p_modulo => d_result.modulo,
p_istanza => d_result.istanza
                                        )
           , 'canHandle on diritti_accesso_tpk.PK'
           );
   return  d_result;
end PK; -- diritti_accesso_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
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
          p_utente is null
or p_modulo is null
or p_istanza is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on diritti_accesso_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- diritti_accesso_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_utente => p_utente
,p_modulo => p_modulo
,p_istanza => p_istanza
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- diritti_accesso_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
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
                                         p_utente => p_utente
,p_modulo => p_modulo
,p_istanza => p_istanza
                                        )
           , 'canHandle on diritti_accesso_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   DIRITTI_ACCESSO
      where
      utente = p_utente
and modulo = p_modulo
and istanza = p_istanza
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on diritti_accesso_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- diritti_accesso_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_utente => p_utente
,p_modulo => p_modulo
,p_istanza => p_istanza
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- diritti_accesso_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_ruolo  in DIRITTI_ACCESSO.ruolo%type
, p_sequenza  in DIRITTI_ACCESSO.sequenza%type default null
, p_ultimo_accesso  in DIRITTI_ACCESSO.ultimo_accesso%type default null
, p_numero_accessi  in DIRITTI_ACCESSO.numero_accessi%type default null
, p_gruppo  in DIRITTI_ACCESSO.gruppo%type default null
, p_note  in DIRITTI_ACCESSO.note%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_ruolo is not null or /*default value*/ '' is not null
           , 'p_ruolo on diritti_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_sequenza is not null or /*default value*/ 'default' is not null
           , 'p_sequenza on diritti_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ultimo_accesso is not null or /*default value*/ 'default' is not null
           , 'p_ultimo_accesso on diritti_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_numero_accessi is not null or /*default value*/ 'default' is not null
           , 'p_numero_accessi on diritti_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_gruppo is not null or /*default value*/ 'default' is not null
           , 'p_gruppo on diritti_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on diritti_accesso_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_utente is null and /*default value*/ '' is not null ) -- PK nullable on insert
or (   p_modulo is null and /*default value*/ '' is not null ) -- PK nullable on insert
or (   p_istanza is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_utente => p_utente
,p_modulo => p_modulo
,p_istanza => p_istanza
                           )
           , 'not existsId on diritti_accesso_tpk.ins'
           );
   insert into DIRITTI_ACCESSO
   (
     utente
,modulo
,istanza
   , ruolo
   , sequenza
   , ultimo_accesso
   , numero_accessi
   , gruppo
   , note
   )
   values
   (
     p_utente
,p_modulo
,p_istanza
   , p_ruolo
   , p_sequenza
   , p_ultimo_accesso
   , p_numero_accessi
   , p_gruppo
   , p_note
   );
end ins; -- diritti_accesso_tpk.ins
--------------------------------------------------------------------------------
function ins  /*+ SOA  */
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_ruolo  in DIRITTI_ACCESSO.ruolo%type
, p_sequenza  in DIRITTI_ACCESSO.sequenza%type default null
, p_ultimo_accesso  in DIRITTI_ACCESSO.ultimo_accesso%type default null
, p_numero_accessi  in DIRITTI_ACCESSO.numero_accessi%type default null
, p_gruppo  in DIRITTI_ACCESSO.gruppo%type default null
, p_note  in DIRITTI_ACCESSO.note%type default null
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
   DbC.PRE ( not DbC.PreOn or p_ruolo is not null or /*default value*/ '' is not null
           , 'p_ruolo on diritti_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_sequenza is not null or /*default value*/ 'default' is not null
           , 'p_sequenza on diritti_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_ultimo_accesso is not null or /*default value*/ 'default' is not null
           , 'p_ultimo_accesso on diritti_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_numero_accessi is not null or /*default value*/ 'default' is not null
           , 'p_numero_accessi on diritti_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_gruppo is not null or /*default value*/ 'default' is not null
           , 'p_gruppo on diritti_accesso_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on diritti_accesso_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_utente is null and /*default value*/ '' is not null ) -- PK nullable on insert
or (   p_modulo is null and /*default value*/ '' is not null ) -- PK nullable on insert
or (   p_istanza is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_utente => p_utente
,p_modulo => p_modulo
,p_istanza => p_istanza
                           )
           , 'not existsId on diritti_accesso_tpk.ins'
           );
   begin
      insert into DIRITTI_ACCESSO
      (
        utente
,modulo
,istanza
      , ruolo
      , sequenza
      , ultimo_accesso
      , numero_accessi
      , gruppo
      , note
      )
      values
      (
        p_utente
,p_modulo
,p_istanza
      , p_ruolo
      , p_sequenza
      , p_ultimo_accesso
      , p_numero_accessi
      , p_gruppo
      , p_note
      );
      d_result := 0;
   exception
      when others then
         d_result := sqlcode;
   end;
   return d_result;
end ins; -- diritti_accesso_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_utente  in DIRITTI_ACCESSO.utente%type
, p_OLD_utente  in DIRITTI_ACCESSO.utente%type default null
, p_NEW_modulo  in DIRITTI_ACCESSO.modulo%type
, p_OLD_modulo  in DIRITTI_ACCESSO.modulo%type default null
, p_NEW_istanza  in DIRITTI_ACCESSO.istanza%type
, p_OLD_istanza  in DIRITTI_ACCESSO.istanza%type default null
, p_NEW_ruolo  in DIRITTI_ACCESSO.ruolo%type default null
, p_OLD_ruolo  in DIRITTI_ACCESSO.ruolo%type default null
, p_NEW_sequenza  in DIRITTI_ACCESSO.sequenza%type default null
, p_OLD_sequenza  in DIRITTI_ACCESSO.sequenza%type default null
, p_NEW_ultimo_accesso  in DIRITTI_ACCESSO.ultimo_accesso%type default null
, p_OLD_ultimo_accesso  in DIRITTI_ACCESSO.ultimo_accesso%type default null
, p_NEW_numero_accessi  in DIRITTI_ACCESSO.numero_accessi%type default null
, p_OLD_numero_accessi  in DIRITTI_ACCESSO.numero_accessi%type default null
, p_NEW_gruppo  in DIRITTI_ACCESSO.gruppo%type default null
, p_OLD_gruppo  in DIRITTI_ACCESSO.gruppo%type default null
, p_NEW_note  in DIRITTI_ACCESSO.note%type default null
, p_OLD_note  in DIRITTI_ACCESSO.note%type default null
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
p_OLD_ruolo is not null or
p_OLD_sequenza is not null or
p_OLD_ultimo_accesso is not null or
p_OLD_numero_accessi is not null or
p_OLD_gruppo is not null or
p_OLD_note is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on diritti_accesso_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_utente, p_NEW_utente )
,nvl( p_OLD_modulo, p_NEW_modulo )
,nvl( p_OLD_istanza, p_NEW_istanza )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_utente => d_key.utente
,p_modulo => d_key.modulo
,p_istanza => d_key.istanza
                                       )
           , 'existsId on diritti_accesso_tpk.upd'
           );
   update DIRITTI_ACCESSO
   set
       utente = decode( p_check_OLD, 0,p_NEW_utente, decode(p_NEW_utente, p_OLD_utente,utente,p_NEW_utente))
,modulo = decode( p_check_OLD, 0,p_NEW_modulo, decode(p_NEW_modulo, p_OLD_modulo,modulo,p_NEW_modulo))
,istanza = decode( p_check_OLD, 0,p_NEW_istanza, decode(p_NEW_istanza, p_OLD_istanza,istanza,p_NEW_istanza))
     , ruolo = decode( p_check_OLD, 0,p_NEW_ruolo, decode(p_NEW_ruolo, p_OLD_ruolo,ruolo,p_NEW_ruolo))
     , sequenza = decode( p_check_OLD, 0,p_NEW_sequenza, decode(p_NEW_sequenza, p_OLD_sequenza,sequenza,p_NEW_sequenza))
     , ultimo_accesso = decode( p_check_OLD, 0,p_NEW_ultimo_accesso, decode(p_NEW_ultimo_accesso, p_OLD_ultimo_accesso,ultimo_accesso,p_NEW_ultimo_accesso))
     , numero_accessi = decode( p_check_OLD, 0,p_NEW_numero_accessi, decode(p_NEW_numero_accessi, p_OLD_numero_accessi,numero_accessi,p_NEW_numero_accessi))
     , gruppo = decode( p_check_OLD, 0,p_NEW_gruppo, decode(p_NEW_gruppo, p_OLD_gruppo,gruppo,p_NEW_gruppo))
     , note = decode( p_check_OLD, 0,p_NEW_note, decode(p_NEW_note, p_OLD_note,note,p_NEW_note))
   where
     utente = d_key.utente
and modulo = d_key.modulo
and istanza = d_key.istanza
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( ruolo = p_OLD_ruolo or ( p_OLD_ruolo is null and ( p_check_OLD is null or ruolo is null ) ) )
           and ( sequenza = p_OLD_sequenza or ( p_OLD_sequenza is null and ( p_check_OLD is null or sequenza is null ) ) )
           and ( ultimo_accesso = p_OLD_ultimo_accesso or ( p_OLD_ultimo_accesso is null and ( p_check_OLD is null or ultimo_accesso is null ) ) )
           and ( numero_accessi = p_OLD_numero_accessi or ( p_OLD_numero_accessi is null and ( p_check_OLD is null or numero_accessi is null ) ) )
           and ( gruppo = p_OLD_gruppo or ( p_OLD_gruppo is null and ( p_check_OLD is null or gruppo is null ) ) )
           and ( note = p_OLD_note or ( p_OLD_note is null and ( p_check_OLD is null or note is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on diritti_accesso_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- diritti_accesso_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
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
                                        p_utente => p_utente,
p_modulo => p_modulo,
p_istanza => p_istanza
                                       )
           , 'existsId on diritti_accesso_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on diritti_accesso_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on diritti_accesso_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on diritti_accesso_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update DIRITTI_ACCESSO '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( utente ', p_utente, ' )', 0, null ), ' and ( utente is null ) ' )
 || nvl( AFC.get_field_condition( ' and ( modulo ', p_modulo, ' )', 0, null ), ' and ( modulo is null ) ' )
 || nvl( AFC.get_field_condition( ' and ( istanza ', p_istanza, ' )', 0, null ), ' and ( istanza is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- diritti_accesso_tpk.upd_column
--------------------------------------------------------------------------------
procedure upd_column
(
p_utente  in DIRITTI_ACCESSO.utente%type,
p_modulo  in DIRITTI_ACCESSO.modulo%type,
p_istanza  in DIRITTI_ACCESSO.istanza%type
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
p_utente => p_utente
,p_modulo => p_modulo
,p_istanza => p_istanza
              , p_column => p_column
              , p_value => 'to_date( ''' || d_data || ''', ''' || AFC.date_format || ''' )'
              , p_literal_value => 0
              );
end upd_column; -- diritti_accesso_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_utente  in DIRITTI_ACCESSO.utente%type
, p_modulo  in DIRITTI_ACCESSO.modulo%type
, p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_ruolo  in DIRITTI_ACCESSO.ruolo%type default null
, p_sequenza  in DIRITTI_ACCESSO.sequenza%type default null
, p_ultimo_accesso  in DIRITTI_ACCESSO.ultimo_accesso%type default null
, p_numero_accessi  in DIRITTI_ACCESSO.numero_accessi%type default null
, p_gruppo  in DIRITTI_ACCESSO.gruppo%type default null
, p_note  in DIRITTI_ACCESSO.note%type default null
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
p_ruolo is not null or
p_sequenza is not null or
p_ultimo_accesso is not null or
p_numero_accessi is not null or
p_gruppo is not null or
p_note is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on diritti_accesso_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_utente => p_utente,
p_modulo => p_modulo,
p_istanza => p_istanza
                                       )
           , 'existsId on diritti_accesso_tpk.del'
           );
   delete from DIRITTI_ACCESSO
   where
     utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( ruolo = p_ruolo or ( p_ruolo is null and ( p_check_OLD is null or ruolo is null ) ) )
           and ( sequenza = p_sequenza or ( p_sequenza is null and ( p_check_OLD is null or sequenza is null ) ) )
           and ( ultimo_accesso = p_ultimo_accesso or ( p_ultimo_accesso is null and ( p_check_OLD is null or ultimo_accesso is null ) ) )
           and ( numero_accessi = p_numero_accessi or ( p_numero_accessi is null and ( p_check_OLD is null or numero_accessi is null ) ) )
           and ( gruppo = p_gruppo or ( p_gruppo is null and ( p_check_OLD is null or gruppo is null ) ) )
           and ( note = p_note or ( p_note is null and ( p_check_OLD is null or note is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on diritti_accesso_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_utente => p_utente,
p_modulo => p_modulo,
p_istanza => p_istanza
                                             )
            , 'existsId on diritti_accesso_tpk.del'
            );
end del; -- diritti_accesso_tpk.del
--------------------------------------------------------------------------------
function get_ruolo
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
) return DIRITTI_ACCESSO.ruolo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_ruolo
 DESCRIZIONE: Getter per attributo ruolo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     DIRITTI_ACCESSO.ruolo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result DIRITTI_ACCESSO.ruolo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.get_ruolo'
           );
   select ruolo
   into   d_result
   from   DIRITTI_ACCESSO
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on diritti_accesso_tpk.get_ruolo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'ruolo')
                    , ' AFC_DDL.IsNullable on diritti_accesso_tpk.get_ruolo'
                    );
   end if;
   return  d_result;
end get_ruolo; -- diritti_accesso_tpk.get_ruolo
--------------------------------------------------------------------------------
function get_sequenza
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
) return DIRITTI_ACCESSO.sequenza%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_sequenza
 DESCRIZIONE: Getter per attributo sequenza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     DIRITTI_ACCESSO.sequenza%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result DIRITTI_ACCESSO.sequenza%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.get_sequenza'
           );
   select sequenza
   into   d_result
   from   DIRITTI_ACCESSO
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on diritti_accesso_tpk.get_sequenza'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'sequenza')
                    , ' AFC_DDL.IsNullable on diritti_accesso_tpk.get_sequenza'
                    );
   end if;
   return  d_result;
end get_sequenza; -- diritti_accesso_tpk.get_sequenza
--------------------------------------------------------------------------------
function get_ultimo_accesso
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
) return DIRITTI_ACCESSO.ultimo_accesso%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_ultimo_accesso
 DESCRIZIONE: Getter per attributo ultimo_accesso di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     DIRITTI_ACCESSO.ultimo_accesso%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result DIRITTI_ACCESSO.ultimo_accesso%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.get_ultimo_accesso'
           );
   select ultimo_accesso
   into   d_result
   from   DIRITTI_ACCESSO
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on diritti_accesso_tpk.get_ultimo_accesso'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'ultimo_accesso')
                    , ' AFC_DDL.IsNullable on diritti_accesso_tpk.get_ultimo_accesso'
                    );
   end if;
   return  d_result;
end get_ultimo_accesso; -- diritti_accesso_tpk.get_ultimo_accesso
--------------------------------------------------------------------------------
function get_numero_accessi
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
) return DIRITTI_ACCESSO.numero_accessi%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_numero_accessi
 DESCRIZIONE: Getter per attributo numero_accessi di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     DIRITTI_ACCESSO.numero_accessi%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result DIRITTI_ACCESSO.numero_accessi%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.get_numero_accessi'
           );
   select numero_accessi
   into   d_result
   from   DIRITTI_ACCESSO
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on diritti_accesso_tpk.get_numero_accessi'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'numero_accessi')
                    , ' AFC_DDL.IsNullable on diritti_accesso_tpk.get_numero_accessi'
                    );
   end if;
   return  d_result;
end get_numero_accessi; -- diritti_accesso_tpk.get_numero_accessi
--------------------------------------------------------------------------------
function get_gruppo
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
) return DIRITTI_ACCESSO.gruppo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_gruppo
 DESCRIZIONE: Getter per attributo gruppo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     DIRITTI_ACCESSO.gruppo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result DIRITTI_ACCESSO.gruppo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.get_gruppo'
           );
   select gruppo
   into   d_result
   from   DIRITTI_ACCESSO
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on diritti_accesso_tpk.get_gruppo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'gruppo')
                    , ' AFC_DDL.IsNullable on diritti_accesso_tpk.get_gruppo'
                    );
   end if;
   return  d_result;
end get_gruppo; -- diritti_accesso_tpk.get_gruppo
--------------------------------------------------------------------------------
function get_note
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
) return DIRITTI_ACCESSO.note%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_note
 DESCRIZIONE: Getter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     DIRITTI_ACCESSO.note%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result DIRITTI_ACCESSO.note%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.get_note'
           );
   select note
   into   d_result
   from   DIRITTI_ACCESSO
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on diritti_accesso_tpk.get_note'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'note')
                    , ' AFC_DDL.IsNullable on diritti_accesso_tpk.get_note'
                    );
   end if;
   return  d_result;
end get_note; -- diritti_accesso_tpk.get_note
--------------------------------------------------------------------------------
procedure set_utente
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_value  in DIRITTI_ACCESSO.utente%type default null
) is
/******************************************************************************
 NOME:        set_utente
 DESCRIZIONE: Setter per attributo utente di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.set_utente'
           );
   update DIRITTI_ACCESSO
   set utente = p_value
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
end set_utente; -- diritti_accesso_tpk.set_utente
--------------------------------------------------------------------------------
procedure set_modulo
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_value  in DIRITTI_ACCESSO.modulo%type default null
) is
/******************************************************************************
 NOME:        set_modulo
 DESCRIZIONE: Setter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.set_modulo'
           );
   update DIRITTI_ACCESSO
   set modulo = p_value
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
end set_modulo; -- diritti_accesso_tpk.set_modulo
--------------------------------------------------------------------------------
procedure set_istanza
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_value  in DIRITTI_ACCESSO.istanza%type default null
) is
/******************************************************************************
 NOME:        set_istanza
 DESCRIZIONE: Setter per attributo istanza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.set_istanza'
           );
   update DIRITTI_ACCESSO
   set istanza = p_value
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
end set_istanza; -- diritti_accesso_tpk.set_istanza
--------------------------------------------------------------------------------
procedure set_ruolo
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_value  in DIRITTI_ACCESSO.ruolo%type default null
) is
/******************************************************************************
 NOME:        set_ruolo
 DESCRIZIONE: Setter per attributo ruolo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.set_ruolo'
           );
   update DIRITTI_ACCESSO
   set ruolo = p_value
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
end set_ruolo; -- diritti_accesso_tpk.set_ruolo
--------------------------------------------------------------------------------
procedure set_sequenza
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_value  in DIRITTI_ACCESSO.sequenza%type default null
) is
/******************************************************************************
 NOME:        set_sequenza
 DESCRIZIONE: Setter per attributo sequenza di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.set_sequenza'
           );
   update DIRITTI_ACCESSO
   set sequenza = p_value
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
end set_sequenza; -- diritti_accesso_tpk.set_sequenza
--------------------------------------------------------------------------------
procedure set_ultimo_accesso
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_value  in DIRITTI_ACCESSO.ultimo_accesso%type default null
) is
/******************************************************************************
 NOME:        set_ultimo_accesso
 DESCRIZIONE: Setter per attributo ultimo_accesso di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.set_ultimo_accesso'
           );
   update DIRITTI_ACCESSO
   set ultimo_accesso = p_value
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
end set_ultimo_accesso; -- diritti_accesso_tpk.set_ultimo_accesso
--------------------------------------------------------------------------------
procedure set_numero_accessi
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_value  in DIRITTI_ACCESSO.numero_accessi%type default null
) is
/******************************************************************************
 NOME:        set_numero_accessi
 DESCRIZIONE: Setter per attributo numero_accessi di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.set_numero_accessi'
           );
   update DIRITTI_ACCESSO
   set numero_accessi = p_value
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
end set_numero_accessi; -- diritti_accesso_tpk.set_numero_accessi
--------------------------------------------------------------------------------
procedure set_gruppo
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_value  in DIRITTI_ACCESSO.gruppo%type default null
) is
/******************************************************************************
 NOME:        set_gruppo
 DESCRIZIONE: Setter per attributo gruppo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.set_gruppo'
           );
   update DIRITTI_ACCESSO
   set gruppo = p_value
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
end set_gruppo; -- diritti_accesso_tpk.set_gruppo
--------------------------------------------------------------------------------
procedure set_note
(
  p_utente  in DIRITTI_ACCESSO.utente%type
,p_modulo  in DIRITTI_ACCESSO.modulo%type
,p_istanza  in DIRITTI_ACCESSO.istanza%type
, p_value  in DIRITTI_ACCESSO.note%type default null
) is
/******************************************************************************
 NOME:        set_note
 DESCRIZIONE: Setter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                        )
           , 'existsId on diritti_accesso_tpk.set_note'
           );
   update DIRITTI_ACCESSO
   set note = p_value
   where
   utente = p_utente and
modulo = p_modulo and
istanza = p_istanza
   ;
end set_note; -- diritti_accesso_tpk.set_note
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_utente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_istanza  in varchar2 default null
, p_ruolo  in varchar2 default null
, p_sequenza  in varchar2 default null
, p_ultimo_accesso  in varchar2 default null
, p_numero_accessi  in varchar2 default null
, p_gruppo  in varchar2 default null
, p_note  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( utente ', p_utente, ' )', p_QBE, null )
|| AFC.get_field_condition( ' and ( modulo ', p_modulo, ' )', p_QBE, null )
|| AFC.get_field_condition( ' and ( istanza ', p_istanza, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( ruolo ', p_ruolo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( sequenza ', p_sequenza , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( ultimo_accesso ', p_ultimo_accesso , ' )', p_QBE, AFC.date_format )
               || AFC.get_field_condition( ' and ( numero_accessi ', p_numero_accessi , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( gruppo ', p_gruppo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( note ', p_note , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- diritti_accesso_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_utente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_istanza  in varchar2 default null
, p_ruolo  in varchar2 default null
, p_sequenza  in varchar2 default null
, p_ultimo_accesso  in varchar2 default null
, p_numero_accessi  in varchar2 default null
, p_gruppo  in varchar2 default null
, p_note  in varchar2 default null
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
   d_statement := ' select DIRITTI_ACCESSO.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from DIRITTI_ACCESSO '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                 , p_ruolo => p_ruolo
                                 , p_sequenza => p_sequenza
                                 , p_ultimo_accesso => p_ultimo_accesso
                                 , p_numero_accessi => p_numero_accessi
                                 , p_gruppo => p_gruppo
                                 , p_note => p_note
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- diritti_accesso_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_utente  in varchar2 default null
, p_modulo  in varchar2 default null
, p_istanza  in varchar2 default null
, p_ruolo  in varchar2 default null
, p_sequenza  in varchar2 default null
, p_ultimo_accesso  in varchar2 default null
, p_numero_accessi  in varchar2 default null
, p_gruppo  in varchar2 default null
, p_note  in varchar2 default null
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
   d_statement := ' select count( * ) from DIRITTI_ACCESSO '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_utente => p_utente
, p_modulo => p_modulo
, p_istanza => p_istanza
                                 , p_ruolo => p_ruolo
                                 , p_sequenza => p_sequenza
                                 , p_ultimo_accesso => p_ultimo_accesso
                                 , p_numero_accessi => p_numero_accessi
                                 , p_gruppo => p_gruppo
                                 , p_note => p_note
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- diritti_accesso_tpk.count_rows
--------------------------------------------------------------------------------
end diritti_accesso_tpk;
/

