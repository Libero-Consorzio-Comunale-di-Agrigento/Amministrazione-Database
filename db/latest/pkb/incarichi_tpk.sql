--liquibase formatted sql

--changeset mturra:201901301231_143 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY incarichi_tpk is
/******************************************************************************
 NOME:        incarichi_tpk
 DESCRIZIONE: Gestione tabella INCARICHI.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   12/02/2010  snegroni  Prima emissione.
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
end versione; -- incarichi_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_incarico  in INCARICHI.incarico%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.incarico := p_incarico;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_incarico => d_result.incarico
                                        )
           , 'canHandle on incarichi_tpk.PK'
           );
   return  d_result;
end PK; -- incarichi_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_incarico  in INCARICHI.incarico%type
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
          p_incarico is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on incarichi_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- incarichi_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_incarico  in INCARICHI.incarico%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_incarico => p_incarico
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- incarichi_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_incarico  in INCARICHI.incarico%type
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
                                         p_incarico => p_incarico
                                        )
           , 'canHandle on incarichi_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   INCARICHI
      where
      incarico = p_incarico
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on incarichi_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- incarichi_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_incarico  in INCARICHI.incarico%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_incarico => p_incarico
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- incarichi_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_incarico  in INCARICHI.incarico%type
, p_descrizione  in INCARICHI.descrizione%type
, p_descrizione_al1  in INCARICHI.descrizione_al1%type default null
, p_descrizione_al2  in INCARICHI.descrizione_al2%type default null
, p_responsabilita  in INCARICHI.responsabilita%type default null
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert
   DbC.PRE ( not DbC.PreOn or p_descrizione is not null or /*default value*/ '' is not null
           , 'p_descrizione on incarichi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al1 on incarichi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al2 on incarichi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_responsabilita is not null or /*default value*/ 'default' is not null
           , 'p_responsabilita on incarichi_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_incarico is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_incarico => p_incarico
                           )
           , 'not existsId on incarichi_tpk.ins'
           );
   insert into INCARICHI
   (
     incarico
   , descrizione
   , descrizione_al1
   , descrizione_al2
   , responsabilita
   )
   values
   (
     p_incarico
   , p_descrizione
   , p_descrizione_al1
   , p_descrizione_al2
   , p_responsabilita
   );
end ins; -- incarichi_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_incarico  in INCARICHI.incarico%type
, p_descrizione  in INCARICHI.descrizione%type
, p_descrizione_al1  in INCARICHI.descrizione_al1%type default null
, p_descrizione_al2  in INCARICHI.descrizione_al2%type default null
, p_responsabilita  in INCARICHI.responsabilita%type default null
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
           , 'p_descrizione on incarichi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al1 on incarichi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al2 on incarichi_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_responsabilita is not null or /*default value*/ 'default' is not null
           , 'p_responsabilita on incarichi_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_incarico is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_incarico => p_incarico
                           )
           , 'not existsId on incarichi_tpk.ins'
           );
   begin
      insert into INCARICHI
      (
        incarico
      , descrizione
      , descrizione_al1
      , descrizione_al2
      , responsabilita
      )
      values
      (
        p_incarico
      , p_descrizione
      , p_descrizione_al1
      , p_descrizione_al2
      , p_responsabilita
      );
      d_result := 0;
   exception
      when others then
         d_result := sqlcode;
   end;
   return d_result;
end ins; -- incarichi_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_incarico  in INCARICHI.incarico%type
, p_OLD_incarico  in INCARICHI.incarico%type default null
, p_NEW_descrizione  in INCARICHI.descrizione%type default afc.default_null('INCARICHI.descrizione')
, p_OLD_descrizione  in INCARICHI.descrizione%type default null
, p_NEW_descrizione_al1  in INCARICHI.descrizione_al1%type default afc.default_null('INCARICHI.descrizione_al1')
, p_OLD_descrizione_al1  in INCARICHI.descrizione_al1%type default null
, p_NEW_descrizione_al2  in INCARICHI.descrizione_al2%type default afc.default_null('INCARICHI.descrizione_al2')
, p_OLD_descrizione_al2  in INCARICHI.descrizione_al2%type default null
, p_NEW_responsabilita  in INCARICHI.responsabilita%type default afc.default_null('INCARICHI.responsabilita')
, p_OLD_responsabilita  in INCARICHI.responsabilita%type default null
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
 or p_OLD_descrizione_al1 is not null
 or p_OLD_descrizione_al2 is not null
 or p_OLD_responsabilita is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on incarichi_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_incarico, p_NEW_incarico )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_incarico => d_key.incarico
                                       )
           , 'existsId on incarichi_tpk.upd'
           );
   update INCARICHI
   set
       incarico = nvl( p_NEW_incarico, decode( afc.is_default_null( 'INCARICHI.incarico'), 1, incarico, null) )
     , descrizione = nvl( p_NEW_descrizione, decode( afc.is_default_null( 'INCARICHI.descrizione'), 1, descrizione, null) )
     , descrizione_al1 = nvl( p_NEW_descrizione_al1, decode( afc.is_default_null( 'INCARICHI.descrizione_al1'), 1, descrizione_al1, null) )
     , descrizione_al2 = nvl( p_NEW_descrizione_al2, decode( afc.is_default_null( 'INCARICHI.descrizione_al2'), 1, descrizione_al2, null) )
     , responsabilita = nvl( p_NEW_responsabilita, decode( afc.is_default_null( 'INCARICHI.responsabilita'), 1, responsabilita, null) )
   where
     incarico = d_key.incarico
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( descrizione = p_OLD_descrizione or ( p_OLD_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( descrizione_al1 = p_OLD_descrizione_al1 or ( p_OLD_descrizione_al1 is null and ( p_check_OLD is null or descrizione_al1 is null ) ) )
           and ( descrizione_al2 = p_OLD_descrizione_al2 or ( p_OLD_descrizione_al2 is null and ( p_check_OLD is null or descrizione_al2 is null ) ) )
           and ( responsabilita = p_OLD_responsabilita or ( p_OLD_responsabilita is null and ( p_check_OLD is null or responsabilita is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on incarichi_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- incarichi_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_incarico  in INCARICHI.incarico%type
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
                                        p_incarico => p_incarico
                                       )
           , 'existsId on incarichi_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on incarichi_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on incarichi_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on incarichi_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update INCARICHI '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( incarico ', p_incarico, ' )', 0, null ), ' and ( incarico is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- incarichi_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_incarico  in INCARICHI.incarico%type
, p_descrizione  in INCARICHI.descrizione%type default null
, p_descrizione_al1  in INCARICHI.descrizione_al1%type default null
, p_descrizione_al2  in INCARICHI.descrizione_al2%type default null
, p_responsabilita  in INCARICHI.responsabilita%type default null
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
 or p_descrizione_al1 is not null
 or p_descrizione_al2 is not null
 or p_responsabilita is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on incarichi_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_incarico => p_incarico
                                       )
           , 'existsId on incarichi_tpk.del'
           );
   delete from INCARICHI
   where
     incarico = p_incarico
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( descrizione = p_descrizione or ( p_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( descrizione_al1 = p_descrizione_al1 or ( p_descrizione_al1 is null and ( p_check_OLD is null or descrizione_al1 is null ) ) )
           and ( descrizione_al2 = p_descrizione_al2 or ( p_descrizione_al2 is null and ( p_check_OLD is null or descrizione_al2 is null ) ) )
           and ( responsabilita = p_responsabilita or ( p_responsabilita is null and ( p_check_OLD is null or responsabilita is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on incarichi_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_incarico => p_incarico
                                             )
            , 'existsId on incarichi_tpk.del'
            );
end del; -- incarichi_tpk.del
--------------------------------------------------------------------------------
function get_descrizione
(
  p_incarico  in INCARICHI.incarico%type
) return INCARICHI.descrizione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione
 DESCRIZIONE: Getter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     INCARICHI.descrizione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result INCARICHI.descrizione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_incarico => p_incarico
                                        )
           , 'existsId on incarichi_tpk.get_descrizione'
           );
   select descrizione
   into   d_result
   from   INCARICHI
   where
   incarico = p_incarico
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on incarichi_tpk.get_descrizione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione')
                    , ' AFC_DDL.IsNullable on incarichi_tpk.get_descrizione'
                    );
   end if;
   return  d_result;
end get_descrizione; -- incarichi_tpk.get_descrizione
--------------------------------------------------------------------------------
function get_descrizione_al1
(
  p_incarico  in INCARICHI.incarico%type
) return INCARICHI.descrizione_al1%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione_al1
 DESCRIZIONE: Getter per attributo descrizione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     INCARICHI.descrizione_al1%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result INCARICHI.descrizione_al1%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_incarico => p_incarico
                                        )
           , 'existsId on incarichi_tpk.get_descrizione_al1'
           );
   select descrizione_al1
   into   d_result
   from   INCARICHI
   where
   incarico = p_incarico
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on incarichi_tpk.get_descrizione_al1'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione_al1')
                    , ' AFC_DDL.IsNullable on incarichi_tpk.get_descrizione_al1'
                    );
   end if;
   return  d_result;
end get_descrizione_al1; -- incarichi_tpk.get_descrizione_al1
--------------------------------------------------------------------------------
function get_descrizione_al2
(
  p_incarico  in INCARICHI.incarico%type
) return INCARICHI.descrizione_al2%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione_al2
 DESCRIZIONE: Getter per attributo descrizione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     INCARICHI.descrizione_al2%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result INCARICHI.descrizione_al2%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_incarico => p_incarico
                                        )
           , 'existsId on incarichi_tpk.get_descrizione_al2'
           );
   select descrizione_al2
   into   d_result
   from   INCARICHI
   where
   incarico = p_incarico
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on incarichi_tpk.get_descrizione_al2'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione_al2')
                    , ' AFC_DDL.IsNullable on incarichi_tpk.get_descrizione_al2'
                    );
   end if;
   return  d_result;
end get_descrizione_al2; -- incarichi_tpk.get_descrizione_al2
--------------------------------------------------------------------------------
function get_responsabilita
(
  p_incarico  in INCARICHI.incarico%type
) return INCARICHI.responsabilita%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_responsabilita
 DESCRIZIONE: Getter per attributo responsabilita di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     INCARICHI.responsabilita%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result INCARICHI.responsabilita%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_incarico => p_incarico
                                        )
           , 'existsId on incarichi_tpk.get_responsabilita'
           );
   select responsabilita
   into   d_result
   from   INCARICHI
   where
   incarico = p_incarico
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on incarichi_tpk.get_responsabilita'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'responsabilita')
                    , ' AFC_DDL.IsNullable on incarichi_tpk.get_responsabilita'
                    );
   end if;
   return  d_result;
end get_responsabilita; -- incarichi_tpk.get_responsabilita
--------------------------------------------------------------------------------
procedure set_incarico
(
  p_incarico  in INCARICHI.incarico%type
, p_value  in INCARICHI.incarico%type default null
) is
/******************************************************************************
 NOME:        set_incarico
 DESCRIZIONE: Setter per attributo incarico di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_incarico => p_incarico
                                        )
           , 'existsId on incarichi_tpk.set_incarico'
           );
   update INCARICHI
   set incarico = p_value
   where
   incarico = p_incarico
   ;
end set_incarico; -- incarichi_tpk.set_incarico
--------------------------------------------------------------------------------
procedure set_descrizione
(
  p_incarico  in INCARICHI.incarico%type
, p_value  in INCARICHI.descrizione%type default null
) is
/******************************************************************************
 NOME:        set_descrizione
 DESCRIZIONE: Setter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_incarico => p_incarico
                                        )
           , 'existsId on incarichi_tpk.set_descrizione'
           );
   update INCARICHI
   set descrizione = p_value
   where
   incarico = p_incarico
   ;
end set_descrizione; -- incarichi_tpk.set_descrizione
--------------------------------------------------------------------------------
procedure set_descrizione_al1
(
  p_incarico  in INCARICHI.incarico%type
, p_value  in INCARICHI.descrizione_al1%type default null
) is
/******************************************************************************
 NOME:        set_descrizione_al1
 DESCRIZIONE: Setter per attributo descrizione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_incarico => p_incarico
                                        )
           , 'existsId on incarichi_tpk.set_descrizione_al1'
           );
   update INCARICHI
   set descrizione_al1 = p_value
   where
   incarico = p_incarico
   ;
end set_descrizione_al1; -- incarichi_tpk.set_descrizione_al1
--------------------------------------------------------------------------------
procedure set_descrizione_al2
(
  p_incarico  in INCARICHI.incarico%type
, p_value  in INCARICHI.descrizione_al2%type default null
) is
/******************************************************************************
 NOME:        set_descrizione_al2
 DESCRIZIONE: Setter per attributo descrizione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_incarico => p_incarico
                                        )
           , 'existsId on incarichi_tpk.set_descrizione_al2'
           );
   update INCARICHI
   set descrizione_al2 = p_value
   where
   incarico = p_incarico
   ;
end set_descrizione_al2; -- incarichi_tpk.set_descrizione_al2
--------------------------------------------------------------------------------
procedure set_responsabilita
(
  p_incarico  in INCARICHI.incarico%type
, p_value  in INCARICHI.responsabilita%type default null
) is
/******************************************************************************
 NOME:        set_responsabilita
 DESCRIZIONE: Setter per attributo responsabilita di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_incarico => p_incarico
                                        )
           , 'existsId on incarichi_tpk.set_responsabilita'
           );
   update INCARICHI
   set responsabilita = p_value
   where
   incarico = p_incarico
   ;
end set_responsabilita; -- incarichi_tpk.set_responsabilita
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_incarico  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( incarico ', p_incarico, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione ', p_descrizione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione_al1 ', p_descrizione_al1 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione_al2 ', p_descrizione_al2 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( responsabilita ', p_responsabilita , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- incarichi_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_incarico  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
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
   d_statement := ' select INCARICHI.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from INCARICHI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_incarico => p_incarico
                                 , p_descrizione => p_descrizione
                                 , p_descrizione_al1 => p_descrizione_al1
                                 , p_descrizione_al2 => p_descrizione_al2
                                 , p_responsabilita => p_responsabilita
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- incarichi_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_incarico  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
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
   d_statement := ' select count( * ) from INCARICHI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_incarico => p_incarico
                                 , p_descrizione => p_descrizione
                                 , p_descrizione_al1 => p_descrizione_al1
                                 , p_descrizione_al2 => p_descrizione_al2
                                 , p_responsabilita => p_responsabilita
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- incarichi_tpk.count_rows
--------------------------------------------------------------------------------
end incarichi_tpk;
/

