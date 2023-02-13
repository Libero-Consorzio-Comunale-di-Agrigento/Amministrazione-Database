--liquibase formatted sql

--changeset mturra:201901301231_232 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE Diritto_Accesso IS /* MASTER_LINK */
/******************************************************************************
 NOME:        DIRITTO_ACCESSO.
 DESCRIZIONE: Package per gestione DIRITTI_ACCESSO.
 DIPENDENZE:
 ANNOTAZIONI: Salvato nella directory ins di AD4 nel file DIAC.PKG.
 ECCEZIONI:.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    30/06/2005 MM     Prima emissione.
 1    24/04/2007 MM     A20741.0.0: inserita procedure del.
 2    15/03/2010 SNeg   Nella esiste condizionata la rigenera_so
******************************************************************************/
-- Indica se deve essere effettuata la rigenerazione della struttura organizzativa
d_rigenera_so integer := 1;
-- VERSIONE DEL PACKAGE.
/******************************************************************************
 NOME:        VERSIONE.
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
******************************************************************************/
    FUNCTION  VERSIONE /* SLAVE_COPY */
    RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_RUOLO.
 DESCRIZIONE: Restituisce il ruolo associato al diritto di accesso.
 PARAMETRI:   --
 RITORNA:     stringa varchar2.
******************************************************************************/
   FUNCTION GET_RUOLO /* SLAVE_COPY */
   ( p_utente IN VARCHAR2
   , p_modulo IN VARCHAR2
   , p_istanza IN VARCHAR2) RETURN VARCHAR2;
/******************************************************************************
 NOME:        AGGIORNA_GRUPPO.
 DESCRIZIONE: Verifica se il diritto e le caratteristiche di accesso dell'utente
              o del gruppo sono identiche a quelle di uno dei gruppi a cui
           appartiene, se cosi' e' aggiorna il campo gruppo con il codice
              corrispondente.
 ARGOMENTI:   --
******************************************************************************/
   PROCEDURE AGGIORNA_GRUPPO ( p_utente IN VARCHAR2
                             , p_modulo IN VARCHAR2
                             , p_istanza IN VARCHAR2);
/******************************************************************************
 NOME:        ESISTE.
 DESCRIZIONE: Verifica esistenza diritto di accesso.
 PARAMETRI:   CHIAVE
 RITORNA:     number.
******************************************************************************/
   FUNCTION ESISTE( p_utente IN VARCHAR2
                  , p_modulo IN VARCHAR2
                  , p_istanza IN VARCHAR2
                  , p_rigenera_so in VARCHAR2 default 'SI')
   RETURN NUMBER;
/******************************************************************************
 NOME:        CHECK_GRUPPO.
 DESCRIZIONE: Dato un gruppo ed un diritto di accesso verifica se e' stato
              personalizzato od eliminato.
 PARAMETRI:   CHIAVE
 RITORNA:     number 0: diritto esiste in gruppo ma non in utente.
                     1: diritto esiste in gruppo e in utente.
                     2: diritto esiste in gruppo e in utente ma con ruolo o
                        caratteristiche diverse.
******************************************************************************/
   FUNCTION CHECK_GRUPPO /* SLAVE_COPY */
   ( p_gruppo  IN VARCHAR2
   , p_utente  IN VARCHAR2
   , p_istanza IN VARCHAR2
   , p_modulo  IN VARCHAR2)
   RETURN NUMBER;
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
   PROCEDURE del
      ( p_ISTANZA  IN DIRITTI_ACCESSO.ISTANZA%TYPE
      , p_MODULO  IN DIRITTI_ACCESSO.MODULO%TYPE
      , p_UTENTE IN DIRITTI_ACCESSO.UTENTE%TYPE
      , p_RUOLO IN DIRITTI_ACCESSO.RUOLO%TYPE  DEFAULT NULL
      , p_SEQUENZA IN DIRITTI_ACCESSO.SEQUENZA%TYPE  DEFAULT NULL
      , p_ULTIMO_ACCESSO  IN DIRITTI_ACCESSO.ULTIMO_ACCESSO%TYPE DEFAULT NULL
      , p_NUMERO_ACCESSI  IN DIRITTI_ACCESSO.NUMERO_ACCESSI%TYPE DEFAULT NULL
      , p_GRUPPO  IN DIRITTI_ACCESSO.GRUPPO%TYPE DEFAULT NULL
      , p_NOTE  IN DIRITTI_ACCESSO.NOTE%TYPE DEFAULT NULL
      , p_check_OLD  IN INTEGER DEFAULT 0
   );
END Diritto_Accesso;
/

