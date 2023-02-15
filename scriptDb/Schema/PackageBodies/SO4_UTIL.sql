CREATE OR REPLACE PACKAGE BODY so4_util IS
/******************************************************************************
 NOME:        so4_util_no_so4.
 DESCRIZIONE: Procedure e Funzioni della struttura organizzativa che AD4
              utilizza.
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  01/03/2012 SNeg   Package FINTO in caso di non esistenza user SO4
*******************************************************************************/
   s_revisione_body Afc.t_revision := '000';
   FUNCTION VERSIONE RETURN VARCHAR2 IS
   /******************************************************************************
    NOME:        VERSIONE
    DESCRIZIONE: Restituisce versione e revisione di distribuzione del package.
    RITORNA:     stringa VARCHAR2 contenente versione e revisione.
    NOTE:        Primo numero  : versione compatibilita del Package.
                 Secondo numero: revisione del Package specification.
                 Terzo numero  : revisione del Package body.
   ******************************************************************************/
   BEGIN
      RETURN Afc.VERSION( s_revisione, s_revisione_body );
   END VERSIONE;
   FUNCTION get_struttura
   ( p_utente_o_gruppo VARCHAR2
   , p_DATA   VARCHAR2 DEFAULT TO_CHAR(TRUNC(SYSDATE),'dd/mm/yyyy')
   )
   RETURN Afc.t_ref_cursor
   IS
      d_ref_cursor      Afc.t_ref_cursor;
   BEGIN
      RETURN d_ref_cursor;
   END get_struttura;
   FUNCTION ALLINEA
   RETURN NUMBER
   IS
   BEGIN
      RETURN null;
   END ALLINEA;
function get_assegnazione_prev
( p_utente                             ad4_utenti.utente%type
) return varchar2 is
BEGIN
   RETURN null;
END;
function is_soggetto_componente(p_ni number) return afc_error.t_error_number is
   begin
      return 0; -- non esiste integrazione con SO4
   end;
END so4_util;
/

