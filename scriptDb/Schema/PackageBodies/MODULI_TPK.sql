CREATE OR REPLACE package body moduli_tpk is
/******************************************************************************
 NOME:        moduli_tpk
 DESCRIZIONE: Gestione tabella MODULI.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore      Descrizione.
 000   11/05/2009  mmalferrari  Prima emissione.
 001   15/11/2018  snegroni  Generazione automatica.
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '001 - 15/11/2018';
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
end versione; -- moduli_tpk.versione
--------------------------------------------------------------------------------
function PK
(
 p_modulo  in MODULI.modulo%type
) return t_PK is /* SLAVE_COPY */
/******************************************************************************
 NOME:        PK
 DESCRIZIONE: Costruttore di un t_PK dati gli attributi della chiave
******************************************************************************/
   d_result t_PK;
begin
   d_result.modulo := p_modulo;
   DbC.PRE ( not DbC.PreOn or canHandle (
                                          p_modulo => d_result.modulo
                                        )
           , 'canHandle on moduli_tpk.PK'
           );
   return  d_result;
end PK; -- moduli_tpk.PK
--------------------------------------------------------------------------------
function can_handle
(
 p_modulo  in MODULI.modulo%type
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
          p_modulo is null
       )
   then
      d_result := 0;
   end if;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on moduli_tpk.can_handle'
            );
   return  d_result;
end can_handle; -- moduli_tpk.can_handle
--------------------------------------------------------------------------------
function canHandle
(
 p_modulo  in MODULI.modulo%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        canHandle
 DESCRIZIONE: La chiave specificata rispetta tutti i requisiti sugli attributi componenti.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: true se la chiave e manipolabile, false altrimenti.
 NOTE:        Wrapper boolean di can_handle (cfr. can_handle).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( can_handle (
                                                              p_modulo => p_modulo
                                                            )
                                               );
begin
   return  d_result;
end canHandle; -- moduli_tpk.canHandle
--------------------------------------------------------------------------------
function exists_id
(
 p_modulo  in MODULI.modulo%type
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
                                         p_modulo => p_modulo
                                        )
           , 'canHandle on moduli_tpk.exists_id'
           );
   begin
      select 1
      into   d_result
      from   MODULI
      where
      modulo = p_modulo
      ;
   exception
      when no_data_found then
         d_result := 0;
   end;
   DbC.POST ( d_result = 1  or  d_result = 0
            , 'd_result = 1  or  d_result = 0 on moduli_tpk.exists_id'
            );
   return  d_result;
end exists_id; -- moduli_tpk.exists_id
--------------------------------------------------------------------------------
function existsId
(
 p_modulo  in MODULI.modulo%type
) return boolean is /* SLAVE_COPY */
/******************************************************************************
 NOME:        existsId
 DESCRIZIONE: Esistenza riga con chiave indicata.
 NOTE:        Wrapper boolean di exists_id (cfr. exists_id).
******************************************************************************/
   d_result constant boolean := AFC.to_boolean ( exists_id (
                                                            p_modulo => p_modulo
                                                           )
                                               );
begin
   return  d_result;
end existsId; -- moduli_tpk.existsId
--------------------------------------------------------------------------------
procedure ins
(
  p_modulo  in MODULI.modulo%type
, p_descrizione  in MODULI.descrizione%type
, p_descrizione_al1  in MODULI.descrizione_al1%type default null
, p_descrizione_al2  in MODULI.descrizione_al2%type default null
, p_progetto  in MODULI.progetto%type
, p_note  in MODULI.note%type default null
, p_amministratore  in MODULI.amministratore%type default 'N'
) is
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
begin
   -- Check Mandatory on Insert

   DbC.PRE ( not DbC.PreOn or p_descrizione is not null or /*default value*/ '' is not null
           , 'p_descrizione on moduli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al1 on moduli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al2 on moduli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_progetto is not null or /*default value*/ '' is not null
           , 'p_progetto on moduli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on moduli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_amministratore is not null or /*default value*/ 'default' is not null
           , 'p_amministratore on moduli_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_modulo is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_modulo => p_modulo
                           )
           , 'not existsId on moduli_tpk.ins'
           );
   insert into MODULI
   (
     modulo
   , descrizione
   , descrizione_al1
   , descrizione_al2
   , progetto
   , note
   , amministratore
   )
   values
   (
     p_modulo
, p_descrizione
, p_descrizione_al1
, p_descrizione_al2
, p_progetto
, p_note
, p_amministratore
   );
end ins; -- moduli_tpk.ins
--------------------------------------------------------------------------------
function ins
(
  p_modulo  in MODULI.modulo%type
, p_descrizione  in MODULI.descrizione%type
, p_descrizione_al1  in MODULI.descrizione_al1%type default null
, p_descrizione_al2  in MODULI.descrizione_al2%type default null
, p_progetto  in MODULI.progetto%type
, p_note  in MODULI.note%type default null
, p_amministratore  in MODULI.amministratore%type default 'N'
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
           , 'p_descrizione on moduli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al1 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al1 on moduli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_descrizione_al2 is not null or /*default value*/ 'default' is not null
           , 'p_descrizione_al2 on moduli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_progetto is not null or /*default value*/ '' is not null
           , 'p_progetto on moduli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_note is not null or /*default value*/ 'default' is not null
           , 'p_note on moduli_tpk.ins'
           );
   DbC.PRE ( not DbC.PreOn or p_amministratore is not null or /*default value*/ 'default' is not null
           , 'p_amministratore on moduli_tpk.ins'
           );
   DbC.PRE (  not DbC.PreOn
           or (   p_modulo is null and /*default value*/ '' is not null ) -- PK nullable on insert
           or not existsId (
                             p_modulo => p_modulo
                           )
           , 'not existsId on moduli_tpk.ins'
           );
   insert into MODULI
   (
     modulo
   , descrizione
   , descrizione_al1
   , descrizione_al2
   , progetto
   , note
   , amministratore
   )
   values
   (
     p_modulo
, p_descrizione
, p_descrizione_al1
, p_descrizione_al2
, p_progetto
, p_note
, p_amministratore
   );
   d_result := 0;
   return d_result;
end ins; -- moduli_tpk.ins
--------------------------------------------------------------------------------
procedure upd
(
  p_check_OLD  in integer default 0
, p_NEW_modulo  in MODULI.modulo%type
, p_OLD_modulo  in MODULI.modulo%type default null
, p_NEW_descrizione  in MODULI.descrizione%type default afc.default_null('MODULI.descrizione')
, p_OLD_descrizione  in MODULI.descrizione%type default null
, p_NEW_descrizione_al1  in MODULI.descrizione_al1%type default afc.default_null('MODULI.descrizione_al1')
, p_OLD_descrizione_al1  in MODULI.descrizione_al1%type default null
, p_NEW_descrizione_al2  in MODULI.descrizione_al2%type default afc.default_null('MODULI.descrizione_al2')
, p_OLD_descrizione_al2  in MODULI.descrizione_al2%type default null
, p_NEW_progetto  in MODULI.progetto%type default afc.default_null('MODULI.progetto')
, p_OLD_progetto  in MODULI.progetto%type default null
, p_NEW_note  in MODULI.note%type default afc.default_null('MODULI.note')
, p_OLD_note  in MODULI.note%type default null
, p_NEW_amministratore  in MODULI.amministratore%type default afc.default_null('MODULI.amministratore')
, p_OLD_amministratore  in MODULI.amministratore%type default null
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
p_OLD_descrizione is not null
 or p_OLD_descrizione_al1 is not null
 or p_OLD_descrizione_al2 is not null
 or p_OLD_progetto is not null
 or p_OLD_note is not null
 or p_OLD_amministratore is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on moduli_tpk.upd'
           );
   d_key := PK (
                nvl( p_OLD_modulo, p_NEW_modulo )
               );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_modulo => d_key.modulo
                                       )
           , 'existsId on moduli_tpk.upd'
           );
   update MODULI
   set
       modulo = NVL( p_NEW_modulo, DECODE( AFC.IS_DEFAULT_NULL( 'MODULI.modulo' ), 1, modulo,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_modulo, null, modulo, null ) ) ) )
     , descrizione = NVL( p_NEW_descrizione, DECODE( AFC.IS_DEFAULT_NULL( 'MODULI.descrizione' ), 1, descrizione,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_descrizione, null, descrizione, null ) ) ) )
     , descrizione_al1 = NVL( p_NEW_descrizione_al1, DECODE( AFC.IS_DEFAULT_NULL( 'MODULI.descrizione_al1' ), 1, descrizione_al1,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_descrizione_al1, null, descrizione_al1, null ) ) ) )
     , descrizione_al2 = NVL( p_NEW_descrizione_al2, DECODE( AFC.IS_DEFAULT_NULL( 'MODULI.descrizione_al2' ), 1, descrizione_al2,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_descrizione_al2, null, descrizione_al2, null ) ) ) )
     , progetto = NVL( p_NEW_progetto, DECODE( AFC.IS_DEFAULT_NULL( 'MODULI.progetto' ), 1, progetto,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_progetto, null, progetto, null ) ) ) )
     , note = NVL( p_NEW_note, DECODE( AFC.IS_DEFAULT_NULL( 'MODULI.note' ), 1, note,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_note, null, note, null ) ) ) )
     , amministratore = NVL( p_NEW_amministratore, DECODE( AFC.IS_DEFAULT_NULL( 'MODULI.amministratore' ), 1, amministratore,
                 DECODE( p_CHECK_OLD, 0, null, DECODE( p_OLD_amministratore, null, amministratore, null ) ) ) )
   where
     modulo = d_key.modulo
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( descrizione = p_OLD_descrizione or ( p_OLD_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( descrizione_al1 = p_OLD_descrizione_al1 or ( p_OLD_descrizione_al1 is null and ( p_check_OLD is null or descrizione_al1 is null ) ) )
           and ( descrizione_al2 = p_OLD_descrizione_al2 or ( p_OLD_descrizione_al2 is null and ( p_check_OLD is null or descrizione_al2 is null ) ) )
           and ( progetto = p_OLD_progetto or ( p_OLD_progetto is null and ( p_check_OLD is null or progetto is null ) ) )
           and ( note = p_OLD_note or ( p_OLD_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( amministratore = p_OLD_amministratore or ( p_OLD_amministratore is null and ( p_check_OLD is null or amministratore is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   afc.default_null(NULL);
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on moduli_tpk.upd'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
end upd; -- moduli_tpk.upd
--------------------------------------------------------------------------------
procedure upd_column
(
  p_modulo  in MODULI.modulo%type
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
                                        p_modulo => p_modulo
                                       )
           , 'existsId on moduli_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or p_column is not null
           , 'p_column is not null on moduli_tpk.upd_column'
           );
   DbC.PRE ( not DbC.PreOn or AFC_DDL.HasAttribute( s_table_name, p_column )
           , 'AFC_DDL.HasAttribute on moduli_tpk.upd_column'
           );
   DbC.PRE ( p_literal_value in ( 0, 1 ) or p_literal_value is null
           , 'p_literal_value on moduli_tpk.upd_column; p_literal_value = ' || p_literal_value
           );
   if p_literal_value = 1
   or p_literal_value is null
   then
      d_literal := '''';
   end if;
   d_statement := ' declare '
               || '    d_row_found number; '
               || ' begin '
               || '    update MODULI '
               || '       set ' || p_column || ' = ' || d_literal || p_value || d_literal
               || '     where 1 = 1 '
               || nvl( AFC.get_field_condition( ' and ( modulo ', p_modulo, ' )', 0, null ), ' and ( modulo is null ) ' )
               || '    ; '
               || '    d_row_found := SQL%ROWCOUNT; '
               || '    if d_row_found < 1 '
               || '    then '
               || '       raise_application_error ( AFC_ERROR.modified_by_other_user_number, AFC_ERROR.modified_by_other_user_msg ); '
               || '    end if; '
               || ' end; ';
   AFC.SQL_execute( d_statement );
end upd_column; -- moduli_tpk.upd_column
--------------------------------------------------------------------------------
procedure del
(
  p_check_old  in integer default 0
, p_modulo  in MODULI.modulo%type
, p_descrizione  in MODULI.descrizione%type default null
, p_descrizione_al1  in MODULI.descrizione_al1%type default null
, p_descrizione_al2  in MODULI.descrizione_al2%type default null
, p_progetto  in MODULI.progetto%type default null
, p_note  in MODULI.note%type default null
, p_amministratore  in MODULI.amministratore%type default null
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
 or p_progetto is not null
 or p_note is not null
 or p_amministratore is not null
                    )
                    and (  nvl( p_check_OLD, -1 ) = 0
                        )
                  )
           , ' "OLD values" is not null on moduli_tpk.del'
           );
   DbC.PRE ( not DbC.PreOn or existsId (
                                         p_modulo => p_modulo
                                       )
           , 'existsId on moduli_tpk.del'
           );
   delete from MODULI
   where
     modulo = p_modulo
   and (   p_check_OLD = 0
        or (   1 = 1
           and ( descrizione = p_descrizione or ( p_descrizione is null and ( p_check_OLD is null or descrizione is null ) ) )
           and ( descrizione_al1 = p_descrizione_al1 or ( p_descrizione_al1 is null and ( p_check_OLD is null or descrizione_al1 is null ) ) )
           and ( descrizione_al2 = p_descrizione_al2 or ( p_descrizione_al2 is null and ( p_check_OLD is null or descrizione_al2 is null ) ) )
           and ( progetto = p_progetto or ( p_progetto is null and ( p_check_OLD is null or progetto is null ) ) )
           and ( note = p_note or ( p_note is null and ( p_check_OLD is null or note is null ) ) )
           and ( amministratore = p_amministratore or ( p_amministratore is null and ( p_check_OLD is null or amministratore is null ) ) )
           )
       )
   ;
   d_row_found := SQL%ROWCOUNT;
   DbC.ASSERTION ( not DbC.AssertionOn or d_row_found <= 1
                 , 'd_row_found <= 1 on moduli_tpk.del'
                 );
   if d_row_found < 1
   then
      raise_application_error ( AFC_ERROR.modified_by_other_user_number
                              , AFC_ERROR.modified_by_other_user_msg
                              );
   end if;
   DbC.POST ( not DbC.PostOn or not existsId (
                                               p_modulo => p_modulo
                                             )
            , 'existsId on moduli_tpk.del'
            );
end del; -- moduli_tpk.del
--------------------------------------------------------------------------------
function get_descrizione
(
  p_modulo  in MODULI.modulo%type
) return MODULI.descrizione%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione
 DESCRIZIONE: Getter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     MODULI.descrizione%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result MODULI.descrizione%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.get_descrizione'
           );
   select descrizione
   into   d_result
   from   MODULI
   where
   modulo = p_modulo
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on moduli_tpk.get_descrizione'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione')
                    , ' AFC_DDL.IsNullable on moduli_tpk.get_descrizione'
                    );
   end if;
   return  d_result;
end get_descrizione; -- moduli_tpk.get_descrizione
--------------------------------------------------------------------------------
function get_descrizione_al1
(
  p_modulo  in MODULI.modulo%type
) return MODULI.descrizione_al1%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione_al1
 DESCRIZIONE: Getter per attributo descrizione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     MODULI.descrizione_al1%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result MODULI.descrizione_al1%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.get_descrizione_al1'
           );
   select descrizione_al1
   into   d_result
   from   MODULI
   where
   modulo = p_modulo
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on moduli_tpk.get_descrizione_al1'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione_al1')
                    , ' AFC_DDL.IsNullable on moduli_tpk.get_descrizione_al1'
                    );
   end if;
   return  d_result;
end get_descrizione_al1; -- moduli_tpk.get_descrizione_al1
--------------------------------------------------------------------------------
function get_descrizione_al2
(
  p_modulo  in MODULI.modulo%type
) return MODULI.descrizione_al2%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_descrizione_al2
 DESCRIZIONE: Getter per attributo descrizione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     MODULI.descrizione_al2%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result MODULI.descrizione_al2%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.get_descrizione_al2'
           );
   select descrizione_al2
   into   d_result
   from   MODULI
   where
   modulo = p_modulo
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on moduli_tpk.get_descrizione_al2'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'descrizione_al2')
                    , ' AFC_DDL.IsNullable on moduli_tpk.get_descrizione_al2'
                    );
   end if;
   return  d_result;
end get_descrizione_al2; -- moduli_tpk.get_descrizione_al2
--------------------------------------------------------------------------------
function get_progetto
(
  p_modulo  in MODULI.modulo%type
) return MODULI.progetto%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_progetto
 DESCRIZIONE: Getter per attributo progetto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     MODULI.progetto%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result MODULI.progetto%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.get_progetto'
           );
   select progetto
   into   d_result
   from   MODULI
   where
   modulo = p_modulo
   ;
  -- Check Mandatory Attribute on Table
  if (true)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on moduli_tpk.get_progetto'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'progetto')
                    , ' AFC_DDL.IsNullable on moduli_tpk.get_progetto'
                    );
   end if;
   return  d_result;
end get_progetto; -- moduli_tpk.get_progetto
--------------------------------------------------------------------------------
function get_note
(
  p_modulo  in MODULI.modulo%type
) return MODULI.note%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_note
 DESCRIZIONE: Getter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     MODULI.note%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result MODULI.note%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.get_note'
           );
   select note
   into   d_result
   from   MODULI
   where
   modulo = p_modulo
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on moduli_tpk.get_note'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'note')
                    , ' AFC_DDL.IsNullable on moduli_tpk.get_note'
                    );
   end if;
   return  d_result;
end get_note; -- moduli_tpk.get_note
--------------------------------------------------------------------------------
function get_amministratore
(
  p_modulo  in MODULI.modulo%type
) return MODULI.amministratore%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_amministratore
 DESCRIZIONE: Getter per attributo amministratore di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     MODULI.amministratore%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result MODULI.amministratore%type;
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.get_amministratore'
           );
   select amministratore
   into   d_result
   from   MODULI
   where
   modulo = p_modulo
   ;
  -- Check Mandatory Attribute on Table
  if (false)  -- is Mandatory on Table ?
  then -- Result must be not null
      DbC.POST ( not DbC.PostOn  or  d_result is not null
               , 'd_result is not null on moduli_tpk.get_amministratore'
               );
   else -- Column must nullable on table
      DbC.ASSERTION ( not DbC.AssertionOn  or  AFC_DDL.IsNullable ( s_table_name, 'amministratore')
                    , ' AFC_DDL.IsNullable on moduli_tpk.get_amministratore'
                    );
   end if;
   return  d_result;
end get_amministratore; -- moduli_tpk.get_amministratore
--------------------------------------------------------------------------------
procedure set_modulo
(
  p_modulo  in MODULI.modulo%type
, p_value  in MODULI.modulo%type default null
) is
/******************************************************************************
 NOME:        set_modulo
 DESCRIZIONE: Setter per attributo modulo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.set_modulo'
           );
   update MODULI
   set modulo = p_value
   where
   modulo = p_modulo
   ;
end set_modulo; -- moduli_tpk.set_modulo
--------------------------------------------------------------------------------
procedure set_descrizione
(
  p_modulo  in MODULI.modulo%type
, p_value  in MODULI.descrizione%type default null
) is
/******************************************************************************
 NOME:        set_descrizione
 DESCRIZIONE: Setter per attributo descrizione di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.set_descrizione'
           );
   update MODULI
   set descrizione = p_value
   where
   modulo = p_modulo
   ;
end set_descrizione; -- moduli_tpk.set_descrizione
--------------------------------------------------------------------------------
procedure set_descrizione_al1
(
  p_modulo  in MODULI.modulo%type
, p_value  in MODULI.descrizione_al1%type default null
) is
/******************************************************************************
 NOME:        set_descrizione_al1
 DESCRIZIONE: Setter per attributo descrizione_al1 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.set_descrizione_al1'
           );
   update MODULI
   set descrizione_al1 = p_value
   where
   modulo = p_modulo
   ;
end set_descrizione_al1; -- moduli_tpk.set_descrizione_al1
--------------------------------------------------------------------------------
procedure set_descrizione_al2
(
  p_modulo  in MODULI.modulo%type
, p_value  in MODULI.descrizione_al2%type default null
) is
/******************************************************************************
 NOME:        set_descrizione_al2
 DESCRIZIONE: Setter per attributo descrizione_al2 di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.set_descrizione_al2'
           );
   update MODULI
   set descrizione_al2 = p_value
   where
   modulo = p_modulo
   ;
end set_descrizione_al2; -- moduli_tpk.set_descrizione_al2
--------------------------------------------------------------------------------
procedure set_progetto
(
  p_modulo  in MODULI.modulo%type
, p_value  in MODULI.progetto%type default null
) is
/******************************************************************************
 NOME:        set_progetto
 DESCRIZIONE: Setter per attributo progetto di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.set_progetto'
           );
   update MODULI
   set progetto = p_value
   where
   modulo = p_modulo
   ;
end set_progetto; -- moduli_tpk.set_progetto
--------------------------------------------------------------------------------
procedure set_note
(
  p_modulo  in MODULI.modulo%type
, p_value  in MODULI.note%type default null
) is
/******************************************************************************
 NOME:        set_note
 DESCRIZIONE: Setter per attributo note di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.set_note'
           );
   update MODULI
   set note = p_value
   where
   modulo = p_modulo
   ;
end set_note; -- moduli_tpk.set_note
--------------------------------------------------------------------------------
procedure set_amministratore
(
  p_modulo  in MODULI.modulo%type
, p_value  in MODULI.amministratore%type default null
) is
/******************************************************************************
 NOME:        set_amministratore
 DESCRIZIONE: Setter per attributo amministratore di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
begin
   DbC.PRE ( not DbC.PreOn or  existsId (
                                          p_modulo => p_modulo
                                        )
           , 'existsId on moduli_tpk.set_amministratore'
           );
   update MODULI
   set amministratore = p_value
   where
   modulo = p_modulo
   ;
end set_amministratore; -- moduli_tpk.set_amministratore
--------------------------------------------------------------------------------
function where_condition /* SLAVE_COPY */
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_modulo  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
, p_progetto  in varchar2 default null
, p_note  in varchar2 default null
, p_amministratore  in varchar2 default null
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
               || AFC.get_field_condition( ' and ( modulo ', p_modulo, ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione ', p_descrizione , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione_al1 ', p_descrizione_al1 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( descrizione_al2 ', p_descrizione_al2 , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( progetto ', p_progetto , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( note ', p_note , ' )', p_QBE, null )
               || AFC.get_field_condition( ' and ( amministratore ', p_amministratore , ' )', p_QBE, null )
               || ' ) ' || p_other_condition
               ;
   return d_statement;
end where_condition; --- moduli_tpk.where_condition
--------------------------------------------------------------------------------
function get_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_order_by in varchar2 default null
, p_extra_columns in varchar2 default null
, p_extra_condition in varchar2 default null
, p_modulo  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
, p_progetto  in varchar2 default null
, p_note  in varchar2 default null
, p_amministratore  in varchar2 default null
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
   d_statement := ' select MODULI.* '
               || afc.decode_value( p_extra_columns, null, null, ' , ' || p_extra_columns )
               || ' from MODULI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_modulo => p_modulo
                                 , p_descrizione => p_descrizione
                                 , p_descrizione_al1 => p_descrizione_al1
                                 , p_descrizione_al2 => p_descrizione_al2
                                 , p_progetto => p_progetto
                                 , p_note => p_note
                                 , p_amministratore => p_amministratore
                                 )
               || ' ' || p_extra_condition
               || afc.decode_value( p_order_by, null, null, ' order by ' || p_order_by )
               ;
   d_ref_cursor := AFC_DML.get_ref_cursor( d_statement );
   return d_ref_cursor;
end get_rows; -- moduli_tpk.get_rows
--------------------------------------------------------------------------------
function count_rows
( p_QBE  in number default 0
, p_other_condition in varchar2 default null
, p_modulo  in varchar2 default null
, p_descrizione  in varchar2 default null
, p_descrizione_al1  in varchar2 default null
, p_descrizione_al2  in varchar2 default null
, p_progetto  in varchar2 default null
, p_note  in varchar2 default null
, p_amministratore  in varchar2 default null
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
   d_statement := ' select count( * ) from MODULI '
               || where_condition(
                                   p_QBE => p_QBE
                                 , p_other_condition => p_other_condition
                                 , p_modulo => p_modulo
                                 , p_descrizione => p_descrizione
                                 , p_descrizione_al1 => p_descrizione_al1
                                 , p_descrizione_al2 => p_descrizione_al2
                                 , p_progetto => p_progetto
                                 , p_note => p_note
                                 , p_amministratore => p_amministratore
                                 );
   d_result := AFC.SQL_execute( d_statement );
   return d_result;
end count_rows; -- moduli_tpk.count_rows
--------------------------------------------------------------------------------

end moduli_tpk;
/

