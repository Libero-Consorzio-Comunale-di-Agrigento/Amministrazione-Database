--liquibase formatted sql

--changeset mturra:201901301231_171 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY ruoli_tpk is
/******************************************************************************
 NOME:        ruoli_tpk
 DESCRIZIONE: Gestione tabella RUOLI.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   28/12/2009  snegroni  Prima emissione.
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
end versione; -- ruoli_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_ruolo  in RUOLI.ruolo%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.ruolo := p_ruolo;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_ruolo => d_result.ruolo
                                        )
           , 'canHandle on ruoli_tpk.PK'
           );
   return  d_result;
end PK; -- ruoli_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_ruolo  in RUOLI.ruolo%type
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
          p_ruolo is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on ruoli_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- ruoli_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_ruolo  in RUOLI.ruolo%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_ruolo => p_ruolo
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- ruoli_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_ruolo  in RUOLI.ruolo%type
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
                                         p_ruolo => p_ruolo
                                        )
           , 'canHandle on ruoli_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   RUOLI
      where
      ruolo = p_ruolo
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on ruoli_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- ruoli_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_ruolo  in RUOLI.ruolo%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_ruolo => p_ruolo
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- ruoli_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_ruolo  in RUOLI.ruolo%type
, p_descrizione  in RUOLI.descrizione%type
, p_progetto  in RUOLI.progetto%type default null
, p_modulo  in RUOLI.modulo%type default null
, p_profilo  in RUOLI.profilo%type default null
, p_descrizione_al1  in RUOLI.descrizione_al1%type default null
, p_descrizione_al2  in RUOLI.descrizione_al2%type default null
, p_stato  in RUOLI.stato%type default 'U'
, p_gruppo_lavoro  in RUOLI.gruppo_lavoro%type default 'N'
, p_gruppo_so  in RUOLI.gruppo_so%type default 'N'
, p_incarico  in RUOLI.incarico%type default 'N'
, p_responsabilita  in RUOLI.responsabilita%type default 'N'
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_descrizione is not null or /*default value*/ '' is not null
           , 'p_descrizione on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_progetto is not null or /*default value*/ 'default' is not null
           , 'p_progetto on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ 'default' is not null
           , 'p_modulo on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_profilo is not null or /*default value*/ 'default' is not null
           , 'p_profilo on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al1 on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al2 on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_stato is not null or /*default value*/ 'default' is not null
           , 'p_stato on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_gruppo_lavoro is not null or /*default value*/ 'default' is not null
           , 'p_gruppo_lavoro on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_gruppo_so is not null or /*default value*/ 'default' is not null
           , 'p_gruppo_so on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_incarico is not null or /*default value*/ 'default' is not null
           , 'p_incarico on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_responsabilita is not null or /*default value*/ 'default' is not null
           , 'p_responsabilita on ruoli_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_ruolo is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_ruolo => p_ruolo
                           )
           , 'not existsId on ruoli_tpk.ins'
           );
   insert into RUOLI
   (
     ruolo
   , descrizione
   , progetto
   , modulo
   , profilo
   , descrizione_al1
   , descrizione_al2
   , stato
   , gruppo_lavoro
   , gruppo_so
   , incarico
   , responsabilita
   )
   values
   (
     p_ruolo
   , p_descrizione
   , p_progetto
   , p_modulo
   , p_profilo
   , p_descrizione_al1
   , p_descrizione_al2
   , p_stato
   , p_gruppo_lavoro
   , p_gruppo_so
   , p_incarico
   , p_responsabilita
   );
end ins; -- ruoli_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_ruolo  in RUOLI.ruolo%type
, p_descrizione  in RUOLI.descrizione%type
, p_progetto  in RUOLI.progetto%type default null
, p_modulo  in RUOLI.modulo%type default null
, p_profilo  in RUOLI.profilo%type default null
, p_descrizione_al1  in RUOLI.descrizione_al1%type default null
, p_descrizione_al2  in RUOLI.descrizione_al2%type default null
, p_stato  in RUOLI.stato%type default 'U'
, p_gruppo_lavoro  in RUOLI.gruppo_lavoro%type default 'N'
, p_gruppo_so  in RUOLI.gruppo_so%type default 'N'
, p_incarico  in RUOLI.incarico%type default 'N'
, p_responsabilita  in RUOLI.responsabilita%type default 'N'
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
   DbC.PRE ( not DbC.PreOn or p_descrizione is not null or /*default value*/ '' is not null
           , 'p_descrizione on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_progetto is not null or /*default value*/ 'default' is not null
           , 'p_progetto on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_modulo is not null or /*default value*/ 'default' is not null
           , 'p_modulo on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_profilo is not null or /*default value*/ 'default' is not null
           , 'p_profilo on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al1 on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al2 on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_stato is not null or /*default value*/ 'default' is not null
           , 'p_stato on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_gruppo_lavoro is not null or /*default value*/ 'default' is not null
           , 'p_gruppo_lavoro on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_gruppo_so is not null or /*default value*/ 'default' is not null
           , 'p_gruppo_so on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_incarico is not null or /*default value*/ 'default' is not null
           , 'p_incarico on ruoli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_responsabilita is not null or /*default value*/ 'default' is not null
           , 'p_responsabilita on ruoli_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_ruolo is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_ruolo => p_ruolo
                           )
           , 'not existsId on ruoli_tpk.ins'
           );
   begin
      insert into RUOLI
      (
        ruolo
      , descrizione
      , progetto
      , modulo
      , profilo
      , descrizione_al1
      , descrizione_al2
      , stato
      , gruppo_lavoro
      , gruppo_so
      , incarico
      , responsabilita
      )
      values
      (
        p_ruolo
      , p_descrizione
      , p_progetto
      , p_modulo
      , p_profilo
      , p_descrizione_al1
      , p_descrizione_al2
      , p_stato
      , p_gruppo_lavoro
      , p_gruppo_so
      , p_incarico
      , p_responsabilita
      );
      d_result := 0;
   exception
      when others then
         d_result := sqlcode;
   end;
   return d_result;
end ins; -- ruoli_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_ruolo  in RUOLI.ruolo%type
, p_OLD_ruolo  in RUOLI.ruolo%type default null
, p_NEW_descrizione  in RUOLI.descrizione%type default afc.default_null('RUOLI.descrizione')
, p_OLD_descrizione  in RUOLI.descrizione%type default null
, p_NEW_progetto  in RUOLI.progetto%type default afc.default_null('RUOLI.progetto')
, p_OLD_progetto  in RUOLI.progetto%type default null
, p_NEW_modulo  in RUOLI.modulo%type default afc.default_null('RUOLI.modulo')
, p_OLD_modulo  in RUOLI.modulo%type default null
, p_NEW_profilo  in RUOLI.profilo%type default afc.default_null('RUOLI.profilo')
, p_OLD_profilo  in RUOLI.profilo%type default null
, p_NEW_descrizione_al1  in RUOLI.descrizione_al1%type default afc.default_null('RUOLI.descrizione_al1')
, p_OLD_descrizione_al1  in RUOLI.descrizione_al1%type default null
, p_NEW_descrizione_al2  in RUOLI.descrizione_al2%type default afc.default_null('RUOLI.descrizione_al2')
, p_OLD_descrizione_al2  in RUOLI.descrizione_al2%type default null
, p_NEW_stato  in RUOLI.stato%type default afc.default_null('RUOLI.stato')
, p_OLD_stato  in RUOLI.stato%type default null
, p_NEW_gruppo_lavoro  in RUOLI.gruppo_lavoro%type default afc.default_null('RUOLI.gruppo_lavoro')
, p_OLD_gruppo_lavoro  in RUOLI.gruppo_lavoro%type default null
, p_NEW_gruppo_so  in RUOLI.gruppo_so%type default afc.default_null('RUOLI.gruppo_so')
, p_OLD_gruppo_so  in RUOLI.gruppo_so%type default null
, p_NEW_incarico  in RUOLI.incarico%type default afc.default_null('RUOLI.incarico')
, p_OLD_incarico  in RUOLI.incarico%type default null
, p_NEW_responsabilita  in RUOLI.responsabilita%type default afc.default_null('RUOLI.responsabilita')
, p_OLD_responsabilita  in RUOLI.responsabilita%type default null
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
p_OLD_descrizione is not null
 or p_OLD_progetto is not null
 or p_OLD_modulo is not null
 or p_OLD_profilo is not null
 or p_OLD_descrizione_al1 is not null
 or p_OLD_descrizione_al2 is not null
 or p_OLD_stato is not null
 or p_OLD_gruppo_lavoro is not null
 or p_OLD_gruppo_so is not null
 or p_OLD_incarico is not null
 or p_OLD_responsabilita is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on ruoli_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_ruolo, p_NEW_ruolo )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_ruolo => d_key.ruolo
                                       )
           , 'existsId on ruoli_tpk.upd'
           );
   update RUOLI
   set
       ruolo = nvl( p_NEW_ruolo, decode( afc.is_default_null( 'RUOLI.ruolo'), 1, ruolo, null) )
     , descrizione = nvl( p_NEW_descrizione, decode( afc.is_default_null( 'RUOLI.descrizione'), 1, descrizione, null) )
     , progetto = nvl( p_NEW_progetto, decode( afc.is_default_null( 'RUOLI.progetto'), 1, progetto, null) )
     , modulo = nvl( p_NEW_modulo, decode( afc.is_default_null( 'RUOLI.modulo'), 1, modulo, null) )
     , profilo = nvl( p_NEW_profilo, decode( afc.is_default_null( 'RUOLI.profilo'), 1, profilo, null) )
     , descrizione_al1 = nvl( p_NEW_descrizione_al1, decode( afc.is_default_null( 'RUOLI.descrizione_al1'), 1, descrizione_al1, null) )
     , descrizione_al2 = nvl( p_NEW_descrizione_al2, decode( afc.is_default_null( 'RUOLI.descrizione_al2'), 1, descrizione_al2, null) )
     , stato = nvl( p_NEW_stato, decode( afc.is_default_null( 'RUOLI.stato'), 1, stato, null) )
     , gruppo_lavoro = nvl( p_NEW_gruppo_lavoro, decode( afc.is_default_null( 'RUOLI.gruppo_lavoro'), 1, gruppo_lavoro, null) )
     , gruppo_so = nvl( p_NEW_gruppo_so, decode( afc.is_default_null( 'RUOLI.gruppo_so'), 1, gruppo_so, null) )
     , incarico = nvl( p_NEW_incarico, decode( afc.is_default_null( 'RUOLI.incarico'), 1, incarico, null) )
     , responsabilita = nvl( p_NEW_responsabilita, decode( afc.is_default_null( 'RUOLI.responsabilita'), 1, responsabilita, null) )
   where
     ruolo = d_key.ruolo
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( descrizione = p_OLD_descrizione or ( p_OLD_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( progetto = p_OLD_progetto or ( p_OLD_progetto is null and ( p_check_OLD is null or progetto is null ) ) )
           and ( modulo = p_OLD_modulo or ( p_OLD_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( profilo = p_OLD_profilo or ( p_OLD_profilo is null and ( p_check_OLD is null or profilo is null ) ) )
           and ( descrizione_al1 = p_OLD_descrizione_al1 or ( p_OLD_descrizione_al1 is null and ( p_check_OLD is null or descrizione_al1 is null ) ) )
           and ( descrizione_al2 = p_OLD_descrizione_al2 or ( p_OLD_descrizione_al2 is null and ( p_check_OLD is null or descrizione_al2 is null ) ) )
           and ( stato = p_OLD_stato or ( p_OLD_stato is null and ( p_check_OLD is null or stato is null ) ) )
           and ( gruppo_lavoro = p_OLD_gruppo_lavoro or ( p_OLD_gruppo_lavoro is null and ( p_check_OLD is null or gruppo_lavoro is null ) ) )
           and ( gruppo_so = p_OLD_gruppo_so or ( p_OLD_gruppo_so is null and ( p_check_OLD is null or gruppo_so is null ) ) )
           and ( incarico = p_OLD_incarico or ( p_OLD_incarico is null and ( p_check_OLD is null or incarico is null ) ) )
           and ( responsabilita = p_OLD_responsabilita or ( p_OLD_responsabilita is null and ( p_check_OLD is null or responsabilita is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on ruoli_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- ruoli_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_ruolo  in RUOLI.ruolo%type
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
                                        p_ruolo => p_ruolo
                                       )
           , 'existsId on ruoli_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on ruoli_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on ruoli_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on ruoli_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update RUOLI '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( ruolo ', p_ruolo, ' )', 0, null ), ' and ( ruolo is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- ruoli_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_ruolo  in RUOLI.ruolo%type
, p_descrizione  in RUOLI.descrizione%type default null
, p_progetto  in RUOLI.progetto%type default null
, p_modulo  in RUOLI.modulo%type default null
, p_profilo  in RUOLI.profilo%type default null
, p_descrizione_al1  in RUOLI.descrizione_al1%type default null
, p_descrizione_al2  in RUOLI.descrizione_al2%type default null
, p_stato  in RUOLI.stato%type default null
, p_gruppo_lavoro  in RUOLI.gruppo_lavoro%type default null
, p_gruppo_so  in RUOLI.gruppo_so%type default null
, p_incarico  in RUOLI.incarico%type default null
, p_responsabilita  in RUOLI.responsabilita%type default null
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
p_descrizione is not null
 or p_progetto is not null
 or p_modulo is not null
 or p_profilo is not null
 or p_descrizione_al1 is not null
 or p_descrizione_al2 is not null
 or p_stato is not null
 or p_gruppo_lavoro is not null
 or p_gruppo_so is not null
 or p_incarico is not null
 or p_responsabilita is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on ruoli_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_ruolo => p_ruolo
                                       )
           , 'existsId on ruoli_tpk.del'
           );
   delete from RUOLI
   where
     ruolo = p_ruolo
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( descrizione = p_descrizione or ( p_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( progetto = p_progetto or ( p_progetto is null and ( p_check_OLD is null or progetto is null ) ) )
           and ( modulo = p_modulo or ( p_modulo is null and ( p_check_OLD is null or modulo is null ) ) )
           and ( profilo = p_profilo or ( p_profilo is null and ( p_check_OLD is null or profilo is null ) ) )
           and ( descrizione_al1 = p_descrizione_al1 or ( p_descrizione_al1 is null and ( p_check_OLD is null or descrizione_al1 is null ) ) )
           and ( descrizione_al2 = p_descrizione_al2 or ( p_descrizione_al2 is null and ( p_check_OLD is null or descrizione_al2 is null ) ) )
           and ( stato = p_stato or ( p_stato is null and ( p_check_OLD is null or stato is null ) ) )
           and ( gruppo_lavoro = p_gruppo_lavoro or ( p_gruppo_lavoro is null and ( p_check_OLD is null or gruppo_lavoro is null ) ) )
           and ( gruppo_so = p_gruppo_so or ( p_gruppo_so is null and ( p_check_OLD is null or gruppo_so is null ) ) )
           and ( incarico = p_incarico or ( p_incarico is null and ( p_check_OLD is null or incarico is null ) ) )
           and ( responsabilita = p_responsabilita or ( p_responsabilita is null and ( p_check_OLD is null or responsabilita is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on ruoli_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_ruolo => p_ruolo
                                             )
            , 'existsId on ruoli_tpk.del'
            );
end del; -- ruoli_tpk.del
--------------------------------------------------------------------------------
function get_descrizione
(
  p_ruolo  in RUOLI.ruolo%type
) return RUOLI.descrizione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione
 DESCRIZIONE: Getter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RUOLI.descrizione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RUOLI.descrizione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.get_descrizione'
           );
   select descrizione
   into   d_result
   from   RUOLI
   where
   ruolo = p_ruolo
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on ruoli_tpk.get_descrizione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione')
                    , ' AFC_DDL.IsNullable on ruoli_tpk.get_descrizione'
                    );
   end if;
   return  d_result;
end get_descrizione; -- ruoli_tpk.get_descrizione
--------------------------------------------------------------------------------
function get_progetto
(
  p_ruolo  in RUOLI.ruolo%type
) return RUOLI.progetto%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_progetto
 DESCRIZIONE: Getter per attributo progetto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RUOLI.progetto%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RUOLI.progetto%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.get_progetto'
           );
   select progetto
   into   d_result
   from   RUOLI
   where
   ruolo = p_ruolo
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on ruoli_tpk.get_progetto'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'progetto')
                    , ' AFC_DDL.IsNullable on ruoli_tpk.get_progetto'
                    );
   end if;
   return  d_result;
end get_progetto; -- ruoli_tpk.get_progetto
--------------------------------------------------------------------------------
function get_modulo
(
  p_ruolo  in RUOLI.ruolo%type
) return RUOLI.modulo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_modulo
 DESCRIZIONE: Getter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RUOLI.modulo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RUOLI.modulo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.get_modulo'
           );
   select modulo
   into   d_result
   from   RUOLI
   where
   ruolo = p_ruolo
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on ruoli_tpk.get_modulo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'modulo')
                    , ' AFC_DDL.IsNullable on ruoli_tpk.get_modulo'
                    );
   end if;
   return  d_result;
end get_modulo; -- ruoli_tpk.get_modulo
--------------------------------------------------------------------------------
function get_profilo
(
  p_ruolo  in RUOLI.ruolo%type
) return RUOLI.profilo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_profilo
 DESCRIZIONE: Getter per attributo profilo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RUOLI.profilo%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RUOLI.profilo%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.get_profilo'
           );
   select profilo
   into   d_result
   from   RUOLI
   where
   ruolo = p_ruolo
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on ruoli_tpk.get_profilo'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'profilo')
                    , ' AFC_DDL.IsNullable on ruoli_tpk.get_profilo'
                    );
   end if;
   return  d_result;
end get_profilo; -- ruoli_tpk.get_profilo
--------------------------------------------------------------------------------
function get_descrizione_al1
(
  p_ruolo  in RUOLI.ruolo%type
) return RUOLI.descrizione_al1%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione_al1
 DESCRIZIONE: Getter per attributo descrizione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RUOLI.descrizione_al1%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RUOLI.descrizione_al1%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.get_descrizione_al1'
           );
   select descrizione_al1
   into   d_result
   from   RUOLI
   where
   ruolo = p_ruolo
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on ruoli_tpk.get_descrizione_al1'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione_al1')
                    , ' AFC_DDL.IsNullable on ruoli_tpk.get_descrizione_al1'
                    );
   end if;
   return  d_result;
end get_descrizione_al1; -- ruoli_tpk.get_descrizione_al1
--------------------------------------------------------------------------------
function get_descrizione_al2
(
  p_ruolo  in RUOLI.ruolo%type
) return RUOLI.descrizione_al2%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione_al2
 DESCRIZIONE: Getter per attributo descrizione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RUOLI.descrizione_al2%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RUOLI.descrizione_al2%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.get_descrizione_al2'
           );
   select descrizione_al2
   into   d_result
   from   RUOLI
   where
   ruolo = p_ruolo
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on ruoli_tpk.get_descrizione_al2'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione_al2')
                    , ' AFC_DDL.IsNullable on ruoli_tpk.get_descrizione_al2'
                    );
   end if;
   return  d_result;
end get_descrizione_al2; -- ruoli_tpk.get_descrizione_al2
--------------------------------------------------------------------------------
function get_stato
(
  p_ruolo  in RUOLI.ruolo%type
) return RUOLI.stato%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_stato
 DESCRIZIONE: Getter per attributo stato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RUOLI.stato%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RUOLI.stato%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.get_stato'
           );
   select stato
   into   d_result
   from   RUOLI
   where
   ruolo = p_ruolo
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on ruoli_tpk.get_stato'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'stato')
                    , ' AFC_DDL.IsNullable on ruoli_tpk.get_stato'
                    );
   end if;
   return  d_result;
end get_stato; -- ruoli_tpk.get_stato
--------------------------------------------------------------------------------
function get_gruppo_lavoro
(
  p_ruolo  in RUOLI.ruolo%type
) return RUOLI.gruppo_lavoro%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_gruppo_lavoro
 DESCRIZIONE: Getter per attributo gruppo_lavoro di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RUOLI.gruppo_lavoro%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RUOLI.gruppo_lavoro%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.get_gruppo_lavoro'
           );
   select gruppo_lavoro
   into   d_result
   from   RUOLI
   where
   ruolo = p_ruolo
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on ruoli_tpk.get_gruppo_lavoro'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'gruppo_lavoro')
                    , ' AFC_DDL.IsNullable on ruoli_tpk.get_gruppo_lavoro'
                    );
   end if;
   return  d_result;
end get_gruppo_lavoro; -- ruoli_tpk.get_gruppo_lavoro
--------------------------------------------------------------------------------
function get_gruppo_so
(
  p_ruolo  in RUOLI.ruolo%type
) return RUOLI.gruppo_so%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_gruppo_so
 DESCRIZIONE: Getter per attributo gruppo_so di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RUOLI.gruppo_so%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RUOLI.gruppo_so%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.get_gruppo_so'
           );
   select gruppo_so
   into   d_result
   from   RUOLI
   where
   ruolo = p_ruolo
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on ruoli_tpk.get_gruppo_so'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'gruppo_so')
                    , ' AFC_DDL.IsNullable on ruoli_tpk.get_gruppo_so'
                    );
   end if;
   return  d_result;
end get_gruppo_so; -- ruoli_tpk.get_gruppo_so
--------------------------------------------------------------------------------
function get_incarico
(
  p_ruolo  in RUOLI.ruolo%type
) return RUOLI.incarico%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_incarico
 DESCRIZIONE: Getter per attributo incarico di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RUOLI.incarico%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RUOLI.incarico%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.get_incarico'
           );
   select incarico
   into   d_result
   from   RUOLI
   where
   ruolo = p_ruolo
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on ruoli_tpk.get_incarico'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'incarico')
                    , ' AFC_DDL.IsNullable on ruoli_tpk.get_incarico'
                    );
   end if;
   return  d_result;
end get_incarico; -- ruoli_tpk.get_incarico
--------------------------------------------------------------------------------
function get_responsabilita
(
  p_ruolo  in RUOLI.ruolo%type
) return RUOLI.responsabilita%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_responsabilita
 DESCRIZIONE: Getter per attributo responsabilita di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     RUOLI.responsabilita%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result RUOLI.responsabilita%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.get_responsabilita'
           );
   select responsabilita
   into   d_result
   from   RUOLI
   where
   ruolo = p_ruolo
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on ruoli_tpk.get_responsabilita'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'responsabilita')
                    , ' AFC_DDL.IsNullable on ruoli_tpk.get_responsabilita'
                    );
   end if;
   return  d_result;
end get_responsabilita; -- ruoli_tpk.get_responsabilita
--------------------------------------------------------------------------------
procedure set_ruolo
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.ruolo%type default null
) is
/******************************************************************************
 NOME:        set_ruolo
 DESCRIZIONE: Setter per attributo ruolo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_ruolo'
           );
   update RUOLI
   set ruolo = p_value
   where
   ruolo = p_ruolo
   ;
end set_ruolo; -- ruoli_tpk.set_ruolo
--------------------------------------------------------------------------------
procedure set_descrizione
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.descrizione%type default null
) is
/******************************************************************************
 NOME:        set_descrizione
 DESCRIZIONE: Setter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_descrizione'
           );
   update RUOLI
   set descrizione = p_value
   where
   ruolo = p_ruolo
   ;
end set_descrizione; -- ruoli_tpk.set_descrizione
--------------------------------------------------------------------------------
procedure set_progetto
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.progetto%type default null
) is
/******************************************************************************
 NOME:        set_progetto
 DESCRIZIONE: Setter per attributo progetto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_progetto'
           );
   update RUOLI
   set progetto = p_value
   where
   ruolo = p_ruolo
   ;
end set_progetto; -- ruoli_tpk.set_progetto
--------------------------------------------------------------------------------
procedure set_modulo
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.modulo%type default null
) is
/******************************************************************************
 NOME:        set_modulo
 DESCRIZIONE: Setter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_modulo'
           );
   update RUOLI
   set modulo = p_value
   where
   ruolo = p_ruolo
   ;
end set_modulo; -- ruoli_tpk.set_modulo
--------------------------------------------------------------------------------
procedure set_profilo
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.profilo%type default null
) is
/******************************************************************************
 NOME:        set_profilo
 DESCRIZIONE: Setter per attributo profilo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_profilo'
           );
   update RUOLI
   set profilo = p_value
   where
   ruolo = p_ruolo
   ;
end set_profilo; -- ruoli_tpk.set_profilo
--------------------------------------------------------------------------------
procedure set_descrizione_al1
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.descrizione_al1%type default null
) is
/******************************************************************************
 NOME:        set_descrizione_al1
 DESCRIZIONE: Setter per attributo descrizione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_descrizione_al1'
           );
   update RUOLI
   set descrizione_al1 = p_value
   where
   ruolo = p_ruolo
   ;
end set_descrizione_al1; -- ruoli_tpk.set_descrizione_al1
--------------------------------------------------------------------------------
procedure set_descrizione_al2
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.descrizione_al2%type default null
) is
/******************************************************************************
 NOME:        set_descrizione_al2
 DESCRIZIONE: Setter per attributo descrizione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_descrizione_al2'
           );
   update RUOLI
   set descrizione_al2 = p_value
   where
   ruolo = p_ruolo
   ;
end set_descrizione_al2; -- ruoli_tpk.set_descrizione_al2
--------------------------------------------------------------------------------
procedure set_stato
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.stato%type default null
) is
/******************************************************************************
 NOME:        set_stato
 DESCRIZIONE: Setter per attributo stato di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_stato'
           );
   update RUOLI
   set stato = p_value
   where
   ruolo = p_ruolo
   ;
end set_stato; -- ruoli_tpk.set_stato
--------------------------------------------------------------------------------
procedure set_gruppo_lavoro
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.gruppo_lavoro%type default null
) is
/******************************************************************************
 NOME:        set_gruppo_lavoro
 DESCRIZIONE: Setter per attributo gruppo_lavoro di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_gruppo_lavoro'
           );
   update RUOLI
   set gruppo_lavoro = p_value
   where
   ruolo = p_ruolo
   ;
end set_gruppo_lavoro; -- ruoli_tpk.set_gruppo_lavoro
--------------------------------------------------------------------------------
procedure set_gruppo_so
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.gruppo_so%type default null
) is
/******************************************************************************
 NOME:        set_gruppo_so
 DESCRIZIONE: Setter per attributo gruppo_so di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_gruppo_so'
           );
   update RUOLI
   set gruppo_so = p_value
   where
   ruolo = p_ruolo
   ;
end set_gruppo_so; -- ruoli_tpk.set_gruppo_so
--------------------------------------------------------------------------------
procedure set_incarico
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.incarico%type default null
) is
/******************************************************************************
 NOME:        set_incarico
 DESCRIZIONE: Setter per attributo incarico di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_incarico'
           );
   update RUOLI
   set incarico = p_value
   where
   ruolo = p_ruolo
   ;
end set_incarico; -- ruoli_tpk.set_incarico
--------------------------------------------------------------------------------
procedure set_responsabilita
(
  p_ruolo  in RUOLI.ruolo%type
, p_value  in RUOLI.responsabilita%type default null
) is
/******************************************************************************
 NOME:        set_responsabilita
 DESCRIZIONE: Setter per attributo responsabilita di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_ruolo => p_ruolo
                                        )
           , 'existsId on ruoli_tpk.set_responsabilita'
           );
   update RUOLI
   set responsabilita = p_value
   where
   ruolo = p_ruolo
   ;
end set_responsabilita; -- ruoli_tpk.set_responsabilita
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_ruolo  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_progetto  in varchar2 default null
, p_modulo  in varchar2 default null
, p_profilo  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
, p_stato  in varchar2 default null
, p_gruppo_lavoro  in varchar2 default null
, p_gruppo_so  in varchar2 default null
, p_incarico  in varchar2 default null
, p_responsabilita  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( ruolo ', p_ruolo, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione ', p_descrizione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( progetto ', p_progetto , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( modulo ', p_modulo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( profilo ', p_profilo , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione_al1 ', p_descrizione_al1 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione_al2 ', p_descrizione_al2 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( stato ', p_stato , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( gruppo_lavoro ', p_gruppo_lavoro , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( gruppo_so ', p_gruppo_so , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( incarico ', p_incarico , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( responsabilita ', p_responsabilita , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- ruoli_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_ruolo  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_progetto  in varchar2 default null
, p_modulo  in varchar2 default null
, p_profilo  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
, p_stato  in varchar2 default null
, p_gruppo_lavoro  in varchar2 default null
, p_gruppo_so  in varchar2 default null
, p_incarico  in varchar2 default null
, p_responsabilita  in varchar2 default null
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
   d_statement := ' select RUOLI.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from RUOLI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_ruolo => p_ruolo
                                 , p_descrizione => p_descrizione
                                 , p_progetto => p_progetto
                                 , p_modulo => p_modulo
                                 , p_profilo => p_profilo
                                 , p_descrizione_al1 => p_descrizione_al1
                                 , p_descrizione_al2 => p_descrizione_al2
                                 , p_stato => p_stato
                                 , p_gruppo_lavoro => p_gruppo_lavoro
                                 , p_gruppo_so => p_gruppo_so
                                 , p_incarico => p_incarico
                                 , p_responsabilita => p_responsabilita
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- ruoli_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_ruolo  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_progetto  in varchar2 default null
, p_modulo  in varchar2 default null
, p_profilo  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
, p_stato  in varchar2 default null
, p_gruppo_lavoro  in varchar2 default null
, p_gruppo_so  in varchar2 default null
, p_incarico  in varchar2 default null
, p_responsabilita  in varchar2 default null
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
   d_statement := ' select count( * ) from RUOLI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_ruolo => p_ruolo
                                 , p_descrizione => p_descrizione
                                 , p_progetto => p_progetto
                                 , p_modulo => p_modulo
                                 , p_profilo => p_profilo
                                 , p_descrizione_al1 => p_descrizione_al1
                                 , p_descrizione_al2 => p_descrizione_al2
                                 , p_stato => p_stato
                                 , p_gruppo_lavoro => p_gruppo_lavoro
                                 , p_gruppo_so => p_gruppo_so
                                 , p_incarico => p_incarico
                                 , p_responsabilita => p_responsabilita
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- ruoli_tpk.count_rows
--------------------------------------------------------------------------------
end ruoli_tpk;
/

