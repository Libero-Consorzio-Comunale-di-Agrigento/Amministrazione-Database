CREATE OR REPLACE PACKAGE Ad4web IS  /* MASTER_LINK */
/******************************************************************************
 NOME:        AD4WEB.
 DESCRIZIONE: .
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    26/11/2002 MM     Creazione.
 1    04/12/2006 MM     Aggiornamenti V4.5
 2    27/08/2007 MM     Gestione campo ISTANZA_AMMINISTRATORE di ISTANZE.
 3    05/08/2001 MM     A39253.0.0: Da ad4web non è possibile risalire al
                        codice del soggetto associato all'utente.
                        Modificata GET_DATI_SOGGETTO.
 4    21/08/2018 SN    Aggiunti parametri x aggiunta campi tabella utente
 5    09/06/2020 SN    Gestione visualizzazione errore Feature #42907
******************************************************************************/
SUBTYPE html           IS VARCHAR2(32000);
      FUNCTION VERSIONE /* SLAVE_COPY */
      RETURN VARCHAR2;
      FUNCTION PROGETTI_PM ( p_progetto     IN VARCHAR2
                           , p_progetto_old IN VARCHAR2
                           , p_descrizione  IN VARCHAR2
                           , p_priorita     IN NUMBER
                           , p_note         IN VARCHAR2)
      RETURN VARCHAR2;
      PROCEDURE  PROGETTI_PD( p_progetto     IN VARCHAR2);
      FUNCTION MODULI_PM ( p_modulo      IN VARCHAR2
                         , p_modulo_old  IN VARCHAR2
                         , p_progetto    IN VARCHAR2
                         , p_descrizione IN VARCHAR2
                         , p_note        IN VARCHAR2
                         , p_amministratore in  varchar2)
       RETURN VARCHAR2;
       FUNCTION GET_DATI_ISTANZA /* SLAVE_COPY */
       ( p_istanza IN VARCHAR2)
       RETURN VARCHAR2;
       FUNCTION ISTANZE_PM ( p_istanza                 IN VARCHAR2
                          , p_istanza_old             IN VARCHAR2
                          , p_progetto                IN VARCHAR2
                          , p_descrizione             IN VARCHAR2
                          , p_ente                    IN VARCHAR2
                          , p_user_oracle             IN VARCHAR2
                          , p_password_oracle         IN VARCHAR2
                          , p_pwd_modified            IN VARCHAR2
                          , p_dislocazione            IN VARCHAR2
                          , p_dislocazione_temporanea IN VARCHAR2
                          , p_lingua                  IN VARCHAR2
                          , p_link_oracle             IN VARCHAR2
                          , p_database_link           IN VARCHAR2
                          , p_servizio                IN VARCHAR2
                          , p_note                    IN VARCHAR2
                          , p_database_driver         IN VARCHAR2
                          , p_istanza_amministratore  IN VARCHAR2
                          , p_istanza_amministratore_old  IN VARCHAR2)
       RETURN VARCHAR2;
       FUNCTION GET_DATI_SERVIZIO /* SLAVE_COPY */
       ( p_id_servizio IN NUMBER )
       RETURN VARCHAR2;
       FUNCTION SERVIZI_PM ( p_id_servizio             IN NUMBER
                           , p_istanza                 IN VARCHAR2
                           , p_modulo                  IN VARCHAR2
                           , p_livello                 IN VARCHAR2
                           , p_abilitazione            IN VARCHAR2
                           , p_multiplo                IN VARCHAR2
                           , p_gruppo_abilitazione     IN VARCHAR2
                           , p_tipo_notifica           IN VARCHAR2
                           , p_mail_notifiche          IN VARCHAR2
                           , p_ccr_notifiche           IN VARCHAR2
                           , p_recupero_password       IN VARCHAR2
                           , p_tag_cim                 IN VARCHAR2)
       RETURN NUMBER;
       FUNCTION GET_DATI_SOGGETTO /* SLAVE_COPY */
       ( p_soggetto IN NUMBER)
       RETURN VARCHAR2;
       FUNCTION GET_DATI_UTENTE /* SLAVE_COPY */
       ( p_utente IN VARCHAR2)
       RETURN VARCHAR2;
       FUNCTION GET_DATI_ENTE /* SLAVE_COPY */
       ( p_ente IN VARCHAR2)
       RETURN VARCHAR2;
       FUNCTION GET_UTENTI_SOGGETTO /* SLAVE_COPY */
       ( p_soggetto IN NUMBER)
       RETURN VARCHAR2;
       FUNCTION GET_SITUAZIONE_AMBIENTE /* SLAVE_COPY */
       ( p_progetti IN VARCHAR2
       , p_istanze  IN VARCHAR2
       , p_moduli   IN VARCHAR2
       , p_servizi  IN VARCHAR2)
       RETURN CLOB;
       FUNCTION UTENTI_PM  ( p_id_utente        IN NUMBER
                           , p_utente           IN VARCHAR2
                           , p_nominativo       IN VARCHAR2
                           , p_password         IN VARCHAR2
                           , p_pwd_modified     IN VARCHAR2
                           , p_rinnovo_password IN VARCHAR2
                           , p_stato            IN VARCHAR2
                           , p_lingua           IN VARCHAR2
                           , p_gruppo_lavoro    IN VARCHAR2
                           , p_importanza       IN VARCHAR2
                           , p_note             IN VARCHAR2
                           , p_utente_agg       IN VARCHAR2
                           , p_soggetto         IN NUMBER
                           -- rev 4 .inizio
                           , p_amministratore in utenti.amministratore%TYPE
                           , p_info_identificazione in utenti.info_identificazione%TYPE)
                           -- rev 4.fine
       RETURN VARCHAR2;
      FUNCTION GRUPPO_PM ( p_utente           IN VARCHAR2
                          , p_utente_old       IN VARCHAR2
                         , p_nominativo       IN VARCHAR2
                    , p_note             IN VARCHAR2
                    , p_utente_agg       IN VARCHAR2)
       RETURN VARCHAR2;
       FUNCTION ENTI_PM ( p_ente IN VARCHAR2
                        , p_ente_old IN VARCHAR2
                        , p_descrizione IN VARCHAR2
                        , p_bitmap IN VARCHAR2
                        , p_disegno IN VARCHAR2
                        , p_note IN VARCHAR2
                        , p_soggetto IN NUMBER)
       RETURN VARCHAR2;
       FUNCTION GET_DATI_DIAC /* SLAVE_COPY */
       ( p_utente IN VARCHAR2
       , p_modulo        IN VARCHAR2
       , p_istanza       IN VARCHAR2)
       RETURN VARCHAR2;
       FUNCTION DIRITTI_ACCESSO_PM ( p_sequenza      IN NUMBER
                                   , p_utente        IN VARCHAR2
                                   , p_modulo        IN VARCHAR2
                                   , p_modulo_old    IN VARCHAR2
                                   , p_istanza       IN VARCHAR2
                                   , p_istanza_old   IN VARCHAR2
                                   , p_ruolo         IN VARCHAR2
                                   , p_note          IN VARCHAR2)
       RETURN NUMBER;
       FUNCTION RUOLI_PM ( p_ruolo       IN VARCHAR2
                         , p_ruolo_old   IN VARCHAR2
                         , p_descrizione IN VARCHAR2
                         , p_progetto    IN VARCHAR2
                         , p_modulo      IN VARCHAR2
                         , p_profilo     IN NUMBER
                         , p_stato         IN VARCHAR2
                         , p_gruppo_lavoro IN VARCHAR2
                         , p_gruppo_so     IN VARCHAR2)
       RETURN VARCHAR2;
       d_package_var VARCHAR2(1);
       FUNCTION GET_DATI_RICHIESTA /* SLAVE_COPY */
       ( p_id_richiesta IN NUMBER )
       RETURN VARCHAR2;
       PROCEDURE UTGR_INSERT ( p_utente IN VARCHAR2
                             , p_gruppo IN VARCHAR2
                             , p_utente_mod in varchar2 default null  );
       PROCEDURE UTGR_DELETE ( p_utente IN VARCHAR2
                             , p_gruppo IN VARCHAR2
                             , p_utente_mod in varchar2 default null);
       FUNCTION CHECK_DIAC_CAAC_UTEN_GRUPPO /* SLAVE_COPY */
       ( p_utente IN VARCHAR2
       , p_gruppo IN VARCHAR2)
       RETURN clob;
       PROCEDURE Soggetti_Pm
       ( p_soggetto IN OUT NUMBER
       , p_cognome IN VARCHAR2
       , p_nome IN VARCHAR2
       , p_sesso IN VARCHAR2
       , p_data_nas IN VARCHAR2
       , p_comune_nas IN NUMBER
       , p_provincia_nas IN NUMBER
       , p_cod_fis IN VARCHAR2
       , p_comune IN  NUMBER
       , p_provincia IN  NUMBER
       , p_cap IN VARCHAR2
       , p_indirizzo IN VARCHAR2
       , p_indirizzo_web IN VARCHAR2
       , p_telefono IN VARCHAR2
       , p_fax IN VARCHAR2
       , p_note IN VARCHAR2
       , p_utente_agg IN VARCHAR2);
   /******************************************************************************
    NOME:        GET_BLOB_LINK
    DESCRIZIONE: Restituisce l'html del link alla servlet per aprire il file
                 associato al blob/clob/bfile.
    PARAMETRI:   p_table    tabella da cui estrarre il file
                p_field    nome del campo della tabella di tipo blob/clob/bfile
                 p_where    condizione per estrarre il file dalla tabella
                 p_mimetype mimetype da utilizzare per la visualizzazione
                 p_nomefile nome del file da visualizzare
    RITORNA:     stringa varchar2 contenente LINK.
   ******************************************************************************/
      FUNCTION GET_BLOB_LINK /* SLAVE_COPY */
      ( p_table IN VARCHAR2
      , p_field IN VARCHAR2
      , p_where IN VARCHAR2
      , p_mimetype IN VARCHAR2
     , p_nomefile IN VARCHAR2)
      RETURN VARCHAR2;
   /******************************************************************************
    NOME:        GET_EVEN_LINK
    DESCRIZIONE: Restituisce l'html del link alla servlet per aprire il file
                 associato all'evento.
    PARAMETRI:   p_id_evento  identificativo dell'evento.
    RITORNA:     stringa varchar2 contenente LINK.
   ******************************************************************************/
      FUNCTION GET_EVEN_LINK
      ( p_id_evento NUMBER )
      RETURN VARCHAR2;
     FUNCTION TAB_FOLDER_VERTICAL /* SLAVE_COPY */
     (in_link               IN VARCHAR2,
      in_href               IN VARCHAR2,
      in_active             IN VARCHAR2 DEFAULT 'N'
     ) RETURN html;
     FUNCTION GET_GUIDA_LDAP (
       p_chiave    VARCHAR2
     ) RETURN CLOB;
   FUNCTION GET_ALBERO_ACCESSI /* SLAVE_COPY */
   (
      P_ACCESSO_ID   VARCHAR2,
      P_ACCESSO_ID_OLD   VARCHAR2
   )
   RETURN CLOB;
   FUNCTION GET_ALBERO_GRUPPI /* SLAVE_COPY */
   ( P_ID   VARCHAR2
   , P_ID_PADRE VARCHAR2) RETURN CLOB;
   FUNCTION GET_GRUPPI_DIAC_UTENTE
/******************************************************************************
NOME:        GET_GRUPPI_DIAC_UTENTE
DESCRIZIONE: .
PARAMETRI:   --
RITORNA:     stringa varchar2.
NOTE:
******************************************************************************/
(p_utente IN VARCHAR2)
   RETURN CLOB;
   FUNCTION GET_DIAC_UTENTE
/******************************************************************************
NOME:        GET_DIAC_UTENTE
DESCRIZIONE: Elenco diritti di accesso diretti all'utente
PARAMETRI:   --
RITORNA:     stringa varchar2.
NOTE:
******************************************************************************/
(p_utente IN VARCHAR2)
   RETURN CLOB;
    FUNCTION GET_CAAC_UTENTE
/******************************************************************************
NOME:        GET_CAAC_UTENTE
DESCRIZIONE: Elenco caratteristiche di accesso dirette all'utente
PARAMETRI:   --
RITORNA:     stringa varchar2.
NOTE:
******************************************************************************/
(p_utente IN VARCHAR2)
   RETURN CLOB;

function GESTIONE_ERRORE
(p_modulo     VARCHAR2
,p_utente     VARCHAR2
,in_error_stack      clob
,in_format           number default 1
) return varchar2
;
/******************************************************************************
 NOME:        GESTIONE_ERRORE
 DESCRIZIONE: Restituisce l'errore da visualizzare.
              Se tra -20000 e -20999 restituisce la descrizione personalizzata.
              Altrimenti inserisce un record nella key_error_log e indica id
              della registrazione inserita
 RITORNA:     varchar2 Errore o codice di errore da visualizzare
******************************************************************************/

END Ad4web;
/

