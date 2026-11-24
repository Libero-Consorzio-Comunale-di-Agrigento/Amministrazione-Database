--liquibase formatted sql

--changeset mturra:201901301231_279 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE So4_Util IS
/******************************************************************************
 NOME:        SO4.
 DESCRIZIONE: Procedure e Funzioni della struttura organizzativa che AD4
              utilizza.
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  00  24/10/2006 MM     Creazione
  01  08/08/2008 SN     Inserimento nuove function
  02  26/03/2010 SNeg   Aggiunta get_utenti_aoo_ruolo_gruppo
 03  27/04/2011 SNeg   Tolta get_utenti_aoo_ruolo_gruppo
 04  01/03/2012 SNeg    Aggiunta  is_soggetto_componente
******************************************************************************/
   s_revisione Afc.t_revision := 'V1.04';
   FUNCTION VERSIONE RETURN VARCHAR2;
   /******************************************************************************
    NOME:        get_struttura
    DESCRIZIONE: Ottiene la struttura di tutti i ruoli ed i gruppi di tipo 'O'
                 corrispondenti alle unita' organizzative a cui l'utente/gruppo
                 appartiene alla data specificata.
    PARAMETRI:   p_utente_o_gruppo varchar2  codice utente da controllare
                 DATA   varchar2 DEFAULT oggi Data per cui effettuare la verifica
    RITORNA:     Afc.t_ref_cursor REF CURSOR contenente la struttura di tutti i ruoli
                 ed i gruppi di tipo 'O' corrispondenti alle unita' organizzative a
                 cui l'utente appartiene alla data specificata.
                 Nel dettaglio ogni record del ref_cursor contiene la concatenazione
                 con carattere [ di:
                   R(se ruolo)/O(gruppo)/U(utente)#codice#descrizione/nominativo<#codice_gruppo_associato_al_ruolo>
                 dalla radice fino all'utente compreso.
                 Se p_utente_o_gruppo e nullo (o = '%'), il ref cursor contiene
                 l'intera struttura organizzativa.
                 Ad esempio, se l'utente UTENTE appartiene a UO1 (figlia di UO0) ed
                 a UO21 (figlia di UO1 a sua volta figlia di UO0) ed ha i ruoli R1,
                 R2 e R3 (associati ai gruppi r1, r2 ed r3), il ref cursor contiene i
                 seguenti record:
                   O#UO0#descrUO0[O#UO1#descrUO1[U#UTENTE#nominativo
                   O#UO0#descrUO0[O#UO2#descrUO2[O#UO21#descrUO21[U#UTENTE#nominativo
                   R#R1#descR1#r1[U#UTENTE#nominativo
                   R#R2#descR2#r2[U#UTENTE#nominativo
                   R#R3#descR3#r3[U#UTENTE#nominativo
   ******************************************************************************/
   FUNCTION get_struttura
   ( p_utente_o_gruppo VARCHAR2
   , p_DATA   VARCHAR2 DEFAULT TO_CHAR(TRUNC(SYSDATE),'dd/mm/yyyy')
   )
   RETURN Afc.t_ref_cursor;
   FUNCTION ALLINEA
   RETURN NUMBER;
   function get_assegnazione_prev
   ( p_utente                          ad4_utenti.utente%type
   ) return varchar2;
    function is_soggetto_componente(p_ni number) return afc_error.t_error_number;
END So4_Util;
/

