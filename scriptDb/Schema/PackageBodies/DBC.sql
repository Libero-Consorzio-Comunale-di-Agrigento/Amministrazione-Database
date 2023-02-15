CREATE OR REPLACE package body DbC is
/******************************************************************************
 Machinery to support Design-by-Contract.
 REVISION.
 Rev.  Date        Author  Description.
 ----  ----------  ------  ----------------------------------------------------
 000   16/03/2005  CZ      First release.
 001   03/01/2006  CZ      Clauses that evaluate to null reported as exceptions; version and revision.
 002   12/04/2006  FT      sostitution of ampersand character with token 'and' in revision 001 to solve problems during the package execution in SQL*Plus.
 003   30/08/2006  FT      Modifica dichiarazione subtype per incompatibilità con versione 7 di Oracle.
 004   08/07/2011  FT      Allineati i commenti col nuovo standard di plsqldoc.
******************************************************************************/
   -- Package body revision value
   s_revisione_body t_revision := '004';
   -- Interruttore per pre-condizioni
   s_pre_on boolean := false;
   -- Interruttore per post-condizioni
   s_post_on boolean := false;
   -- Interruttore per asserzioni
   s_assertion_on boolean := false;
   -- Interruttore per invariante
   s_invariant_on boolean := false;
   -- Variabile utilizzata per la definizione del subtype d_message
   d_message varchar2(1000);
   -- Type per messaggio da visualizzare in caso di condizione valutata FALSE
   subtype t_message is d_message%type;
--------------------------------------------------------------------------------
/******************************************************************************
 Returns the package release version and revision
******************************************************************************/
function versione
return t_revision
is
   d_result varchar2(10);
begin
   d_result := s_revisione || '.' || s_revisione_body;
   return d_result;
end versione;
--------------------------------------------------------------------------------
/******************************************************************************
 Composizione messaggio di errore.
******************************************************************************/
function clause_prefix
( p_clause_number in number
)
return t_message
is
   d_result t_message;
begin
   if p_clause_number = precondition_number then
      d_result := 'PRE';
   elsif p_clause_number = postcondition_number then
      d_result := 'POST';
   elsif p_clause_number = assertion_number then
      d_result := 'ASSERTION';
   else
      d_result := 'INVARIANT';
   end if;
   return d_result;
end; -- DbC.clause_prefix
--------------------------------------------------------------------------------
/******************************************************************************
 Valutazione condizione in esame.
******************************************************************************/
procedure assert
( p_condition in boolean
, p_message in varchar2
, p_error_number in t_error_number
)
is
   d_message t_message;
begin
   -- both
   -- o  null booolean conditions and
   -- o  conditions that evaluate to false
   -- will be regarded and reported as exceptions
   if p_condition is null
   or not p_condition
   then
      d_message := clause_prefix( p_error_number );
      if p_condition is null then
         d_message := d_message || ': boolean expression evaluates to null';
      elsif not p_condition then
         d_message := d_message || ' violation';
      end if;
      if p_message is not null then
         d_message := d_message || ': ' || p_message;
      end if;
      dbms_output.put_line( d_message );
      raise_application_error( p_error_number, d_message, true );
   end if;
end; -- DbC.assert
--------------------------------------------------------------------------------
/******************************************************************************
 To check precondition clauses
******************************************************************************/
procedure PRE
( p_condition in boolean
, p_message in varchar2 -- := null
)
is
begin
   if s_pre_on then
      assert( p_condition, p_message, precondition_number );
   end if;
end; -- DbC.PRE
--------------------------------------------------------------------------------
/******************************************************************************
 To know if check of precondition clauses is on
******************************************************************************/
function pre_on
return number
is
   d_result number;
begin
   if PreOn then
      d_result := 1;
   else
      d_result := 0;
   end if;
   return  d_result;
end; -- DbC.pre_on
--------------------------------------------------------------------------------
/******************************************************************************
 Wrapper booleano di pre_on.
******************************************************************************/
function PreOn
return boolean
is
   d_result boolean := s_pre_on;
begin
   return  d_result;
end; -- DbC.PreOn
--------------------------------------------------------------------------------
/******************************************************************************
 To switch on/off the check of precondition clauses
******************************************************************************/
procedure pre_set
( p_on in number
)
is
begin
   if p_on = 1 then
      PreSet( true );
   else
      PreSet( false );
   end if;
end; -- DbC.PreSet
--------------------------------------------------------------------------------
/******************************************************************************
 Wrapper booleano di pre_set.
******************************************************************************/
procedure PreSet
( p_on in boolean
)
is
begin
   s_pre_on := p_on;
end; -- DbC.PreSet
--------------------------------------------------------------------------------
/******************************************************************************
 To check postcondition clauses
******************************************************************************/
procedure POST
( p_condition in boolean
, p_message in varchar2 -- := null
)
is
begin
   if s_post_on then
      assert( p_condition, p_message, postcondition_number );
   end if;
end; -- DbC.POST
--------------------------------------------------------------------------------
/******************************************************************************
 To know if check of postcondition clauses is on
******************************************************************************/
function post_on
return number
is
   d_result number;
begin
   if PostOn then
      d_result := 1;
   else
      d_result := 0;
   end if;
   return  d_result;
end; -- DbC.post_on
--------------------------------------------------------------------------------
/******************************************************************************
 Wrapper booleano di post_on.
******************************************************************************/
function PostOn
return boolean
is
   d_result boolean := s_post_on;
begin
   return  d_result;
end; -- DbC.PostOn
--------------------------------------------------------------------------------
/******************************************************************************
 To switch on/off the check of postcondition clauses
******************************************************************************/
procedure post_set
( p_on in number
)
is
begin
   if p_on = 1 then
      PostSet( true );
   else
      PostSet( false );
   end if;
end; -- DbC.post_set
--------------------------------------------------------------------------------
/******************************************************************************
 Wrapper booleano di post_set.
******************************************************************************/
procedure PostSet
( p_on in boolean
)
is
begin
   s_post_on := p_on;
end; -- DbC.PostSet
--------------------------------------------------------------------------------
/******************************************************************************
 To check assertion clauses
******************************************************************************/
procedure ASSERTION
( p_condition in boolean
, p_message in varchar2 -- := null
)
is
begin
   if s_assertion_on then
      assert( p_condition, p_message, assertion_number );
   end if;
end; -- DbC.ASSERTION
--------------------------------------------------------------------------------
/******************************************************************************
 To know if check of assertion clauses is on
******************************************************************************/
function assertion_on
return number
is
   d_result number;
begin
   if AssertionOn then
      d_result := 1;
   else
      d_result := 0;
   end if;
   return  d_result;
end; -- DbC.assertion_on
--------------------------------------------------------------------------------
/******************************************************************************
 Wrapper booleano di assertion_on.
******************************************************************************/
function AssertionOn
return boolean
is
   d_result boolean := s_assertion_on;
begin
   return  d_result;
end; -- DbC.AssertionOn
--------------------------------------------------------------------------------
/******************************************************************************
 To switch on/off the check of assertion clauses
******************************************************************************/
procedure assertion_set
( p_on in number
)
is
begin
   if p_on = 1 then
      AssertionSet( true );
   else
      AssertionSet( false );
   end if;
end; -- DbC.assertion_set
--------------------------------------------------------------------------------
/******************************************************************************
 Wrapper booleano di assertion_set.
******************************************************************************/
procedure AssertionSet
( p_on in boolean
)
is
begin
   s_assertion_on := p_on;
end; -- DbC.AssertionSet
--------------------------------------------------------------------------------
/******************************************************************************
 To check invariant clauses
******************************************************************************/
procedure INVARIANT
( p_condition in boolean
, p_message in varchar2 -- := null
)
is
begin
   if s_invariant_on then
      assert( p_condition, p_message, invariant_number );
   end if;
end; -- DbC.INVARIANT
--------------------------------------------------------------------------------
/******************************************************************************
 To know if check of invariant clauses is on
******************************************************************************/
function invariant_on
return number
is
   d_result number;
begin
   if InvariantOn then
      d_result := 1;
   else
      d_result := 0;
   end if;
   return  d_result;
end; -- DbC.invariant_on
--------------------------------------------------------------------------------
/******************************************************************************
 Wrapper booleano di invariant_on.
******************************************************************************/
function InvariantOn
return boolean
is
   d_result boolean := s_invariant_on;
begin
   return  d_result;
end; -- DbC.InvariantOn
--------------------------------------------------------------------------------
/******************************************************************************
 To switch on/off the check of invariant clauses
******************************************************************************/
procedure invariant_set
( p_on in number
)
is
begin
   if p_on = 1 then
      InvariantSet( true );
   else
      InvariantSet( false );
   end if;
end; -- DbC.invariant_set
--------------------------------------------------------------------------------
/******************************************************************************
 Wrapper booleano di invariant_set.
******************************************************************************/
procedure InvariantSet
( p_on in boolean
)
is
begin
   s_invariant_on := p_on;
end; -- DbC.InvariantSet
--------------------------------------------------------------------------------
end DbC;
/

