--liquibase formatted sql

--changeset mturra:201901301231_237 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE Gruppo IS /* MASTER_LINK */
/******************************************************************************
 NOME:        GRUPPO
 DESCRIZIONE: Package per gestione GRUPPI di UTENTI.
              Funzioni di gestione dei diritti e delle caratteristiche di accesso
              di un   utente derivanti dalla sua appartenenza ad un gruppo.
 ANNOTAZIONI: -
 ECCEZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Prima emissione.
 1    26/06/2003 MM     Se veniva eliminato un utente da un gruppo, eliminava
                        tutti i suoi diritti di accesso anche se non derivanti
                        dall'appartenenza ad un gruppo.
 2    03/11/2004 VA     Gestione tipo_utente=O (Organizzazione), eliminando
                        controllo su tipo_utente = G
 3    19/11/2004 VA     Gestione del diritto d'accesso relativo al gruppo con
                        importanza piu alta (0 alta - 99 bassa).
 4    27/10/2006 MM     Introduzione delle funzioni:
                        - get_id
                        - get_descrizione
                        - get_tipo
                        - get_codice
                        - set_descrizione
                        - ins
                        - ins_uo
 5    23/04/2007 MM     A20741.0.0: modificate procedure diac_gruppo_delete e
                        caac_gruppo_delete; create diac_caac_gruppo_delete,
                        is_diac_del, riempi_diac_eliminati, exists_descrizione.
 6    06/05/2008 MM     Modifica nome parametro procedure ins (da p_descrizione
                        a p_desc) per TOO MANY DECLARATION
 7    14/11/2008 MM     A29921.0.0:: modificata ins_uo.
 8    01/06/2010 SNeg   Calcolo dell'importanza considerando tutti i gruppi
                        attraverso i quali il diritto e ereditato.
                        I gruppi di struttura organizzativa ('O') hanno a parita
                        di importanza diritto maggiore.
                        In caso di gruppi di tipo 'O' prende i diritti del padre
                        più "vicino"
 9    11/08/2010  MM    Modificata next_id in modo che il calcolo del primo
                        numero libero per il codice dei gruppi di tipo O,
                        consideri sia i prefissi maisculi che minuscoli per
                        evitare errore in esecuzione della ins del gruppo se
                        esiste gia uno uguale ma maiuscolo (cioe' sto cercando
                        di inserire o55 ma mi da errore perche' esiate O55).
10  25/10/2011  SNeg Modificate le exists per performance
11  12/04/2017  SNeg Modificata assegnazione del gruppo x fare vincere quelli di
                     tipo 'O'
12 30/01/2019 Sneg Acquisire solo diritti di accesso coerenti x modulo di Amministrazione SI/NO
                              a quanto dichiarato x utente.
13 22/05/2019  SNeg utilizzo funzione global_utility.get_registro_amministratore
******************************************************************************/
Revisione VARCHAR2(30) := '13';
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
******************************************************************************/
   FUNCTION  VERSIONE /* SLAVE_COPY */
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
   PROCEDURE ins
   ( p_descrizione  IN UTENTI.nominativo%TYPE
   , p_codice IN OUT UTENTI.Utente%TYPE
   , p_id     IN OUT UTENTI.id_utente%TYPE
   , p_tipo   IN UTENTI.tipo_utente%TYPE  DEFAULT 'G'
   , p_gruppo_lavoro IN UTENTI.gruppo_lavoro%TYPE DEFAULT NULL);
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
   PROCEDURE ins
   ( p_desc  IN UTENTI.nominativo%TYPE
   , p_tipo  IN UTENTI.tipo_utente%TYPE  DEFAULT 'G'
   , p_codice  IN UTENTI.Utente%TYPE  DEFAULT NULL
   , p_id  IN UTENTI.id_utente%TYPE  DEFAULT NULL
   , p_gruppo_lavoro IN UTENTI.gruppo_lavoro%TYPE DEFAULT NULL);
/******************************************************************************
 NOME:        ins_uo
 DESCRIZIONE: Inserimento di un gruppo di tipo 'O'.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
   PROCEDURE ins_uo
   ( p_descrizione  IN UTENTI.nominativo%TYPE
   , p_codice  IN OUT UTENTI.Utente%TYPE
   , p_id  IN OUT UTENTI.id_utente%TYPE
   , p_gruppo_lavoro IN UTENTI.gruppo_lavoro%TYPE DEFAULT NULL);
/******************************************************************************
 NOME:        GET_ID
 DESCRIZIONE: Restituisce l'id del gruppo.
 PARAMETRI:   p_gruppo: codice gruppo.
 RITORNA:     number.
******************************************************************************/
   FUNCTION GET_ID /* SLAVE_COPY */
   (p_gruppo IN VARCHAR2)  RETURN NUMBER;
/******************************************************************************
 NOME:        GET_DESCRIZIONE
 DESCRIZIONE: Restituisce la descrizione del gruppo.
 PARAMETRI:   p_gruppo codice del gruppo
 RITORNA:     stringa varchar2.
******************************************************************************/
   FUNCTION GET_DESCRIZIONE /* SLAVE_COPY */
   (p_gruppo IN VARCHAR2)   RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_TIPO
 DESCRIZIONE: Restituisce il tipo del gruppo.
 PARAMETRI:   p_gruppo: codice gruppo.
 RITORNA:     stringa varchar2.
******************************************************************************/
   FUNCTION GET_TIPO /* SLAVE_COPY */
   (p_gruppo IN VARCHAR2) RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_CODICE
 DESCRIZIONE: Restituisce il codice del gruppo data la descrizione.
 PARAMETRI:   p_gruppo: codice gruppo.
 RITORNA:     stringa varchar2.
******************************************************************************/
   FUNCTION GET_CODICE /* SLAVE_COPY */
   (p_descrizione IN VARCHAR2) RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_GRUPPO_LAVORO
 DESCRIZIONE: Restituisce il  gruppo di lavoro del gruppo dato il codice.
 PARAMETRI:   p_gruppo: codice gruppo.
 RITORNA:     stringa varchar2.
******************************************************************************/
   FUNCTION GET_GRUPPO_LAVORO /* SLAVE_COPY */
   (p_gruppo IN VARCHAR2) RETURN VARCHAR2;
/******************************************************************************
 NOME:        SET_DESCRIZIONE
 DESCRIZIONE: Aggiornamento del campo DESCRIZIONE col valore p_value.
 PARAMETRI:   p_gruppo:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
   PROCEDURE SET_DESCRIZIONE
   ( p_gruppo IN UTENTI.Utente%TYPE
   , p_value  IN UTENTI.nominativo%TYPE);
/******************************************************************************
 NOME:        SET_STATO
 DESCRIZIONE: Aggiornamento del campo STATO col valore p_value.
 PARAMETRI:   p_gruppo:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
   PROCEDURE SET_STATO
   ( p_gruppo IN UTENTI.Utente%TYPE
   , p_value  IN UTENTI.STATO%TYPE) ;
/******************************************************************************
 NOME:        IS_RUOLO
 DESCRIZIONE: Verifica se il gruppo e' associato ad un ruolo.
 PARAMETRI:   p_gruppo varchar2 codice gruppo da verificare.
 RITORNA:     number: 1 se e' un ruolo, 0 altrimenti
******************************************************************************/
   FUNCTION IS_RUOLO /* SLAVE_COPY */
   ( p_gruppo  VARCHAR2) RETURN NUMBER;
/******************************************************************************
 NOME:        exists_gruppo
 DESCRIZIONE: Esistenza gruppo con codice indicato.
 PARAMETRI:   p_gruppo codice gruppo.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
FUNCTION exists_gruppo /* SLAVE_COPY */
( p_gruppo  IN UTENTI.utente%TYPE
) RETURN NUMBER;
/******************************************************************************
 NOME:        exists_descrizione
 DESCRIZIONE: Esistenza riga con descrizione indicato.
 PARAMETRI:   p_username username utente.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
FUNCTION exists_descrizione /* SLAVE_COPY */
( p_descrizione  IN UTENTI.nominativo%TYPE
) RETURN NUMBER;
/******************************************************************************
 NOME:        DIAC_CAAC_GRUPPO_DELETE
 DESCRIZIONE: Toglie agli utenti del gruppo i diritti e le caratteristiche di
              accesso derivati dall'appartenenza al gruppo.
              Per ogni servizio a cui il gruppo e' abilitato (filtrati da
              p_modulo e p_istanza):
                  - Per ogni utente del gruppo abilitato al servizio (tutti oppure
                  ad esclusione di quelli che hanno diritto di accesso personalizzato)
                  filtrati da p_utente:
                     elimina gli eventuali diritti e caratteristiche di accesso esistenti.
 ARGOMENTI:   p_gruppo:    codice gruppo
              p_utente:    codice utente o %.
              p_modulo:    codice modulo o %.
              p_istanza:   codice istanza o %.
              p_modifica:  indica se oggetto della modifica devranno essere
                           tutti gli utenti del gruppo (valore del parametro:
                           'T') oppure dovranno essere esclusi gli utenti con
                           diritto di accesso personalizzato (valore del
                           parametro: 'E').
******************************************************************************/
   PROCEDURE DIAC_CAAC_GRUPPO_DELETE
   ( p_gruppo    IN VARCHAR2
   , p_utente    IN VARCHAR2
   , p_modulo    IN VARCHAR2
   , p_istanza   IN VARCHAR2
   , p_modifica  IN VARCHAR2
   , p_diac_caac IN VARCHAR2
   );
/******************************************************************************
 NOME:        CAAC_GRUPPO_DELETE
 DESCRIZIONE: Toglie agli utenti del gruppo le caratteristiche di accesso
              derivate dall'appartenenza al gruppo.
              Per ogni servizio a cui il gruppo e' abilitato (filtrati da
              p_modulo e p_istanza):
              - Per ogni utente del gruppo abilitato al servizio (tutti oppure
                ad esclusione di quelli che hanno diritto di accesso personalizzato)
                filtrati da p_utente:
                elimina le eventuali caratteristiche di accesso esistenti.
 ARGOMENTI:   p_gruppo:   codice gruppo
              p_utente:   codice utente o %.
              p_modulo:   codice modulo o %.
              p_istanza:  codice istanza o %.
              p_modifica: indica se oggetto della modifica devranno essere
                          tutti gli utenti del gruppo (valore del parametro:
                          'T') oppure dovranno essere esclusi gli utenti con
                          diritto di accesso personalizzato (valore del
                          parametro: 'E').
******************************************************************************/
      PROCEDURE CAAC_GRUPPO_DELETE ( p_gruppo   IN VARCHAR2
                                   , p_utente   IN VARCHAR2
                                   , p_modulo   IN VARCHAR2
                                   , p_istanza  IN VARCHAR2
                                   , p_modifica IN VARCHAR2
                                   );
/******************************************************************************
 NOME:        CAAC_GRUPPO_INSERT
 DESCRIZIONE: Associa agli utenti del gruppo le caratteristiche di accesso
              derivate dall'appartenenza al gruppo.
              Per ogni servizio a cui il gruppo e' abilitato (filtrati da
              p_modulo e p_istanza):
               - verifica se il gruppo ha delle caratteristiche di accesso
                 personalizzate;
               - Per ogni utente del gruppo abilitato al servizio (tutti oppure
                 ad esclusione di quelli che hanno diritto di accesso personalizzato)
                 filtrati da p_utente:
                 se il gruppo ha delle caratteristiche di accesso personalizzate,
                    associa all'utente le caratteristiche di accesso del gruppo
                    (inserisce o aggiorna quelle eventualmente esistenti);
                 altrimenti,
                    elimina le eventuali caratteristiche di accesso esistenti.
 ARGOMENTI:   p_gruppo:   codice gruppo
              p_utente:   codice utente o %.
              p_modulo:   codice modulo o %.
              p_istanza:  codice istanza o %.
              p_modifica: indica se oggetto della modifica devranno essere
                          tutti gli utenti del gruppo (valore del parametro:
                          'T') oppure dovranno essere esclusi gli utenti con
                          diritto di accesso personalizzato (valore del
                          parametro: 'E').
******************************************************************************/
      PROCEDURE CAAC_GRUPPO_INSERT ( p_gruppo   IN VARCHAR2
                                   , p_utente   IN VARCHAR2
                                   , p_modulo   IN VARCHAR2
                                   , p_istanza  IN VARCHAR2
                                   , p_modifica IN VARCHAR2
                                   );
/******************************************************************************
 NOME:        CAAC_GRUPPO_UPDATE
 DESCRIZIONE: Associa agli utenti del gruppo le caratteristiche di accesso
              del gruppo.
              Verifica se il gruppo ha delle caratteristiche di accesso
              personalizzate e se non sono quelle di default;
              se cosi' e'
                 aggiorna le caratteristiche di accesso degli utenti del gruppo
                 (tutti oppure ad esclusione di quelli che hanno diritto di
                 accesso personalizzato);
              altrimenti,
                 elimina le eventuali caratteristiche di accesso esistenti.
 ARGOMENTI:   p_gruppo:   codice gruppo
              p_modulo:   codice modulo.
              p_istanza:  codice istanza.
              p_progetto: codice progetto.
              p_modifica: indica se oggetto della modifica devranno essere
                          tutti gli utenti del gruppo (valore del parametro:
                          'T') oppure dovranno essere esclusi gli utenti con
                          diritto di accesso personalizzato (valore del
                          parametro: 'E').
******************************************************************************/
      PROCEDURE CAAC_GRUPPO_UPDATE ( p_gruppo   IN VARCHAR2
                                   , p_modulo   IN VARCHAR2
                                   , p_istanza  IN VARCHAR2
                                   , p_progetto IN VARCHAR2
                                   , p_modifica IN VARCHAR2
                                   );
/******************************************************************************
 NOME:        DIAC_GRUPPO_DELETE
 DESCRIZIONE: Toglie agli utenti del gruppo i diritti di accesso derivati
              dall'appartenenza al gruppo.
              Per ogni servizio a cui il gruppo e' abilitato (filtrati da
              p_modulo e p_istanza):
              - Per ogni utente del gruppo abilitato al servizio (tutti oppure
                ad esclusione di quelli che hanno diritto di accesso
                personalizzato)   filtrati da p_utente:
                   elimina gli eventuali diritti di accesso esistenti.
 ARGOMENTI:   p_gruppo:   codice gruppo
              p_utente:   codice utente o %.
              p_modulo:   codice modulo o %.
              p_istanza:  codice istanza o %.
              p_modifica: indica se oggetto della modifica devranno essere
                          tutti gli utenti del gruppo (valore del parametro:
                          'T') oppure dovranno essere esclusi gli utenti con
                          diritto di accesso personalizzato (valore del
                          parametro: 'E').
******************************************************************************/
      PROCEDURE DIAC_GRUPPO_DELETE ( p_gruppo   IN VARCHAR2
                                   , p_utente   IN VARCHAR2
                                   , p_modulo   IN VARCHAR2
                                   , p_istanza  IN VARCHAR2
                                   , p_modifica IN VARCHAR2
                                   );
/******************************************************************************
 NOME:        DIAC_GRUPPO_INSERT
 DESCRIZIONE: Aggiunge agli utenti del gruppo i diritti di accesso derivati
              dall'appartenenza al gruppo.
              Per ogni servizio a cui il gruppo e' abilitato (filtrati da
              p_modulo e p_istanza):
              - Per ogni utente del gruppo abilitato al servizio (tutti oppure
                ad esclusione di quelli che hanno diritto di accesso
                personalizzato)   filtrati da p_utente:
                aggiunge o aggiorna (se esistenti) i diritti di accesso.
 ARGOMENTI:   p_gruppo:   codice gruppo
              p_utente:   codice utente o %.
              p_modulo:   codice modulo o %.
              p_istanza:  codice istanza o %.
              p_modifica: indica se oggetto della modifica devranno essere
                          tutti gli utenti del gruppo (valore del parametro:
                          'T') oppure dovranno essere esclusi gli utenti con
                          diritto di accesso personalizzato (valore del
                          parametro: 'E').
******************************************************************************/
      PROCEDURE DIAC_GRUPPO_INSERT ( p_gruppo   IN VARCHAR2
                                   , p_utente   IN VARCHAR2
                                   , p_modulo   IN VARCHAR2
                                   , p_istanza  IN VARCHAR2
                                   , p_modifica IN VARCHAR2
                                   );
/******************************************************************************
 NOME:        DIAC_GRUPPO_UPDATE
 DESCRIZIONE: Associa agli utenti del gruppo i diritti di accesso derivati
              dall'appartenenza al gruppo.
              Per ogni utente del gruppo abilitato al servizio (tutti oppure
              ad esclusione di quelli che hanno diritto di accesso
              personalizzato) filtrati da p_utente:
              aggiorna o aggiunge (se non esistenti) i diritti di accesso.
 ARGOMENTI:   p_gruppo:   codice gruppo.
              p_modulo:   codice modulo.
              p_istanza:  codice istanza.
              p_ruolo:    codice ruolo.
              p_note:     eventuali note associate al diritto di accesso.
              p_modifica: indica se oggetto della modifica devranno essere
                          tutti gli utenti del gruppo (valore del parametro:
                          'T') oppure dovranno essere esclusi gli utenti con
                          diritto di accesso personalizzato (valore del
                          parametro: 'E').
******************************************************************************/
      PROCEDURE DIAC_GRUPPO_UPDATE ( p_gruppo   IN VARCHAR2
                                   , p_modulo   IN VARCHAR2
                                   , p_istanza  IN VARCHAR2
                                   , p_ruolo    IN VARCHAR2
                                   , p_note     IN VARCHAR2
                                   , p_modifica IN VARCHAR2
                                   );
END Gruppo;
/
