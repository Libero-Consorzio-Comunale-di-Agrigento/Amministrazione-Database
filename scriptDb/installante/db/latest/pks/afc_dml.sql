--liquibase formatted sql

--changeset mturra:201901301231_207 runOnChange:true stripComments:false endDelimiter:/


create or replace package AFC_DML is
/******************************************************************************
 General utilities for data manipulation
 REVISIONI:.
 Rev.  Data        Autore  Descrizione.
 00    12/05/2005  CZ      Prima emissione.
 01    30/08/2006  FT      Aggiunta di get_ref_cursor.
 02    13/08/2007  FT      Aggiunta function versione (già presente nel body).
 03    08/07/2011  FT      Allineati i commenti col nuovo standard di plsqldoc.
******************************************************************************/
   -- Package revision value
   s_revisione constant VARCHAR2(30) := 'V1.03';
   /******************************************************************************
    Restituisce versione e revisione di distribuzione del package.
    %return varchar2: contiene versione e revisione.
    %note <UL>
          <LI> Primo numero: versione compatibilita del Package.</LI>
          <LI> Secondo numero: revisione del Package specification.</LI>
          <LI> Terzo numero: revisione del Package body.</LI>
          </UL>
   ******************************************************************************/
   function versione return VARCHAR2;
   pragma restrict_references( versione, WNDS, WNPS );
   /******************************************************************************
    Verifica di cancellabbilita per tentativo.
    %param p_table_name: nome della tabella
    %param p_rowid: indice di riga in formato stringa
   ******************************************************************************/
   procedure chk_delete
   ( p_table_name in varchar2
   , p_rowid in varchar2
   );
   /******************************************************************************
    Ritorna un ref cursor contenente l'esecuzione dello statement passato come parametro.
    %param p_statement: statement da eseguire
   ******************************************************************************/
   function get_ref_cursor
   ( p_statement in AFC.t_statement
   ) return AFC.t_ref_cursor;
end AFC_DML;
/

