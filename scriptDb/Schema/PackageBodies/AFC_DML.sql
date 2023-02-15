CREATE OR REPLACE package body AFC_DML is
/******************************************************************************
 General utilities for data manipulation.
 REVISIONI:.
 Rev.  Data        Autore  Descrizione.
 000   12/05/2005  CZ      Prima emissione.
 001   30/08/2006  FT      Aggiunta di get_ref_cursor.
 002   04/09/2006  FT      Gestione eccezioni in get_ref_cursor.
 003   02/07/2007  FT      Eliminata dipendenza su SI4.
 004   23/02/2011  SN      Differenziato messaggio di eccezione in funzione del
                           DbC attivato dal trigger 'development_logon_trigger'.
 005   08/07/2011  FT      Allineati i commenti col nuovo standard di plsqldoc.
******************************************************************************/
   -- Package body revision value
   s_revisione_body constant VARCHAR2(30) := '005';
--------------------------------------------------------------------------------
/******************************************************************************
 Restituisce versione e revisione di distribuzione del package.
******************************************************************************/
function versione return VARCHAR2 is
begin
   return s_revisione||'.'||s_revisione_body;
end versione;
--------------------------------------------------------------------------------
/******************************************************************************
 Verifica di cancellabbilita per tentativo
 REVISIONI.
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000   12/05/2005  CZ      Prima emissione.
 003   02/07/2007  FT      Eliminata dipendenza su SI4
******************************************************************************/
procedure chk_delete
( p_table_name in varchar2
, p_rowid in varchar2
) is
   d_statement varchar2(2000);
begin
   DbC.PRE( not DbC.PreOn or p_table_name is not null );
   DbC.PRE(  not DbC.PreOn
          or AFC_DDL.IsTable( p_table_name )
          or AFC_DDL.IsView( p_table_name )
          );
   savepoint chk_delete_savepoint;
   d_statement := 'delete ' || p_table_name || ' where ROWID = chartorowid( ''' || p_rowid || ''')';
   afc.sql_execute( d_statement );
   rollback to chk_delete_savepoint;
exception
   when others then
      -- all exception are re-raised to the caller
      raise;
end; -- AFC_DML.chk_delete
--------------------------------------------------------------------------------
/******************************************************************************
 Ritorna un ref cursor contenente l'esecuzione dello statement passato come parametro.
 REVISIONI.
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  30/08/2006 FT     Prima emissione.
 002  04/09/2006 FT     Gestione eccezioni
 004  23/02/2011 SN     Differenziato messaggio di eccezione in funzione del
                        DbC attivato dal trigger 'development_logon_trigger'.
******************************************************************************/
function get_ref_cursor
( p_statement in AFC.t_statement
) return AFC.t_ref_cursor is
   d_ref_cursor      AFC.t_ref_cursor;
begin
   open d_ref_cursor for p_statement;
   return d_ref_cursor;
exception
   when others then
        if dbc.AssertionOn then
           raise_application_error( -20999, 'statement error: ' || p_statement, true );
        else
            raise;
        end if;
end;
--------------------------------------------------------------------------------
end AFC_DML;
/

