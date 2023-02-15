CREATE OR REPLACE package DbC is
/******************************************************************************
 Machinery to support Design-by-Contract.
 REVISION.
 Rev.  Date        Author  Description.
 00    16/03/2005  CZ      First release.
 01    03/01/2006  CZ      Hhandling of clauses which evaluate to null; version and revision.
 02    12/04/2006  FT      Sobstitution of ampersand character with token 'and' in revision 01 to solve problems during the package execution in SQL*Plus.
 03    30/08/2006  FT      Modifica dichiarazione subtype per incompatibilità con versione 7 di Oracle.
 04    08/07/2011  FT      Allineati i commenti col nuovo standard di plsqldoc.
 05    02/01/2012  FT      Eliminato riga di commento per problema con Jsd (BO26262)
******************************************************************************/
   -- Variabile utilizzata per la definizione del subtype t_revision
   d_revision varchar2(30);
   -- Type definition of the package version
   subtype t_revision is d_revision%type;
   -- Package revision value
   s_revisione constant t_revision := 'V1.05';
   /******************************************************************************
    Restituisce versione e revisione di distribuzione del package.
    %return varchar2: contiene versione e revisione.
    %note <UL>
          <LI> Primo numero: versione compatibilita del Package.</LI>
          <LI> Secondo numero: revisione del Package specification.</LI>
          <LI> Terzo numero: revisione del Package body.</LI>
          </UL>
   ******************************************************************************/
   function versione return t_revision;
   pragma restrict_references( versione, WNDS, WNPS );
   pragma restrict_references( DbC, WNDS );
   -- Type of numeric error codes associated to the exceptions
   subtype t_error_number is binary_integer;
   -- Exception for diagnostics for the precondition violations
   precondition_violation           exception;
   -- Error number for diagnostics for the precondition violations
   precondition_number              constant t_error_number := -20101;
   pragma exception_init( precondition_violation, -20101 );
   -- Exception for diagnostics for the postcondition violations
   postcondition_violation          exception;
   -- Error number for diagnostics for the postcondition violations
   postcondition_number             constant number := -20102;
   pragma exception_init( postcondition_violation, -20102 );
   -- Exception for diagnostics for the assertion violations
   assertion_violation              exception;
   -- Error number for diagnostics for the assertion violations
   assertion_number                 constant number := -20103;
   pragma exception_init( assertion_violation, -20103 );
   -- Exception for diagnostics for the invariant violations
   invariant_violation              exception;
   -- Error number for diagnostics for the invariant violations
   invariant_number                 constant number := -20104;
   pragma exception_init( invariant_violation, -20104 );
   /******************************************************************************
    To check precondition clauses
    %param p_condition condizione booleana da valutare
    %param p_message messaggio da visualizzare nel caso in cui p_condition abbia valore FALSE
   ******************************************************************************/
   procedure PRE
   ( p_condition in boolean
   , p_message in varchar2 := null
   );
   pragma restrict_references( PRE, WNDS );
   /******************************************************************************
    To know if check of precondition clauses is on
   ******************************************************************************/
   function pre_on
   return number;
   pragma restrict_references( pre_on, WNDS );
   /******************************************************************************
    Wrapper booleano di pre_on.
    %note cfr. pre_on.
   ******************************************************************************/
   function PreOn
   return boolean;
   pragma restrict_references( PreOn, WNDS );
   /******************************************************************************
    To switch on/off the check of precondition clauses
    %param p_on valore da utilizzare per settare l'interruttore relativo alle pre-condizioni
   ******************************************************************************/
   procedure pre_set
   ( p_on in number
   );
   /******************************************************************************
    Wrapper booleano di pre_set.
    %note cfr. pre_set.
   ******************************************************************************/
   procedure PreSet
   ( p_on in boolean
   );
   /******************************************************************************
    To check postcondition clauses
    %param p_condition condizione booleana da valutare
    %param p_message messaggio da visualizzare nel caso in cui p_condition abbia valore FALSE
   ******************************************************************************/
   procedure POST
   ( p_condition in boolean
   , p_message in varchar2 := null
   );
   pragma restrict_references( POST, WNDS );
   /******************************************************************************
    To know if check of postcondition clauses is on
   ******************************************************************************/
   function post_on
   return number;
   pragma restrict_references( post_on, WNDS );
   /******************************************************************************
    Wrapper booleano di post_on.
    %note cfr. post_on.
   ******************************************************************************/
   function PostOn
   return boolean;
   pragma restrict_references( PostOn, WNDS );
   /******************************************************************************
    To switch on/off the check of postcondition clauses
    %param p_on valore da utilizzare per settare l'interruttore relativo alle post-condizioni
   ******************************************************************************/
   procedure post_set
   ( p_on in number
   );
   /******************************************************************************
    Wrapper booleano di post_set.
    %note cfr. post_set.
   ******************************************************************************/
   procedure PostSet
   ( p_on in boolean
   );
   /******************************************************************************
    To check assertion clauses
    %param p_condition condizione booleana da valutare
    %param p_message messaggio da visualizzare nel caso in cui p_condition abbia valore FALSE
   ******************************************************************************/
   procedure ASSERTION
   ( p_condition in boolean
   , p_message in varchar2 := null
   );
   pragma restrict_references( ASSERTION, WNDS );
   /******************************************************************************
    To know if check of assertion clauses is on
   ******************************************************************************/
   function assertion_on
   return number;
   pragma restrict_references( assertion_on, WNDS );
   /******************************************************************************
    Wrapper booleano di assertion_on.
    %note cfr. assertion_on.
   ******************************************************************************/
   function AssertionOn
   return boolean;
   pragma restrict_references( AssertionOn, WNDS );
   /******************************************************************************
    To switch on/off the check of assertion clauses
    %param p_on valore da utilizzare per settare l'interruttore relativo alle asserzioni
   ******************************************************************************/
   procedure assertion_set
   ( p_on in number
   );
   /******************************************************************************
    Wrapper booleano di assertion_set.
    %note cfr. assertion_set.
   ******************************************************************************/
   procedure AssertionSet
   ( p_on in boolean
   );
   /******************************************************************************
    To check invariant clauses
    %param p_condition condizione booleana da valutare
    %param p_message messaggio da visualizzare nel caso in cui p_condition abbia valore FALSE
   ******************************************************************************/
   procedure INVARIANT
   ( p_condition in boolean
   , p_message in varchar2 := null
   );
   pragma restrict_references( INVARIANT, WNDS );
   /******************************************************************************
    To know if check of invariant clauses is on
   ******************************************************************************/
   function invariant_on
   return number;
   pragma restrict_references( invariant_on, WNDS );
   /******************************************************************************
    Wrapper booleano di invariant_on.
    %note cfr. invariant_on.
   ******************************************************************************/
   function InvariantOn
   return boolean;
   pragma restrict_references( InvariantOn, WNDS );
   /******************************************************************************
    To switch on/off the check of invariant clauses
    %param p_on valore da utilizzare per settare l'interruttore relativo al'invariante
   ******************************************************************************/
   procedure invariant_set
   ( p_on in number
   );
   /******************************************************************************
    Wrapper booleano di invariant_set.
    %note cfr. invariant_set.
   ******************************************************************************/
   procedure InvariantSet
   ( p_on in boolean
   );
end DbC;
/

