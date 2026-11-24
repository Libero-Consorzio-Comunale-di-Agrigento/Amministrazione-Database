--liquibase formatted sql

--changeset mturra:201901301231_203 runOnChange:true stripComments:false endDelimiter:/

CREATE OR REPLACE PACKAGE Admin_Ad4
IS
/******************************************************************************
 NOME:        ADMIN_AD4.
 DESCRIZIONE: Funzioni di amministrazione.
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 00   28/11/2005 MM     Creazione.
 01   02/07/2007 MM     Creazione procedure: CREATE_SYNONYM, CREATE_SYNONYMS,
                        DROP_SYNONYM, DROP_SYNONYMS,
                        CREA_SYN_GRANT_AD4_SECONDARI e
                        DROP_SYN_GRANT_AD4_SECONDARI.
 02   06/05/2008 SN     Inserimento controllo che se installazione MASTER
                        vengono trattati solo gli user oracle esistenti nei
                        cursori
 03   30/10/2008 SN     Creazione procedure per aggiornamento slave
 04   23/02/2012 SN     Possibilita di killare tutte le sessioni di una istanza
 05   10/02/2020 SN     Spostate le variabili nel package specification
 06   22/10/2020 SN   Nel package admin_ad4 creare anche la procedure grant_to #45527
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.06';


   TYPE b_object_rec IS RECORD (
      object_name   ALL_OBJECTS.object_name%type
   );
   TYPE b_object_tab IS TABLE OF b_object_rec
      INDEX BY BINARY_INTEGER;
   b_index                     BINARY_INTEGER := 0;
   TabTVDB                     b_object_tab;
   TabPDB                      b_object_tab;
   TabTVCM                     b_object_tab;
   TabPCM                      b_object_tab;
   TabTVBS                     b_object_tab;
   TabPBS                      b_object_tab;
   TabTVASL                    b_object_tab;
   TabPXDK                     b_object_tab;
   TabAS4                      b_object_tab;
   cursor c_grant_users(p_user IN VARCHAR2, p_owner IN VARCHAR2) is
      SELECT DISTINCT upper (user_oracle) user_oracle
                 FROM ISTANZE
                WHERE upper (user_oracle) <> upper (p_owner)
                  AND user_oracle <> 'SI4'
                  AND p_user = 'ALL'
                  AND (   (    istanza_amministratore is not null
                           AND istanza_amministratore in (
                                     select istanza
                                       from istanze ista
                                      where upper (ista.user_oracle) =
                                                                     upper (p_owner))
                          )
                       or (istanza_amministratore is null AND upper (p_owner) =
                                                                               'AD4'
                          )
                      )
                  AND ((exists (select 'x'
                                  from istanze
                                 where progetto = 'AD4'
                                    and instr(installazione||'.','.MASTER.') >0)
                       and user_oracle in (select username from all_users)
                       )
                       OR
                       not exists (select 'x'
                                     from istanze
                                    where progetto = 'AD4'
                                      and instr(installazione||'.','.MASTER.') >0))
      UNION
      SELECT UPPER(p_user)
        FROM dual
       WHERE p_user <> 'ALL'
   ;
   cursor c_synonym_users(p_user IN VARCHAR2, p_owner IN VARCHAR2) is
      SELECT DISTINCT upper (user_oracle) user_oracle
                 FROM ISTANZE
                WHERE upper (user_oracle) <> 'AD4'
                  AND user_oracle <> 'SI4'
                  AND p_user = 'ALL'
                  AND (   (    istanza_amministratore is not null
                           AND istanza_amministratore in (
                                     select istanza
                                       from istanze ista
                                      where upper (ista.user_oracle) =
                                                                     upper (p_owner))
                          )
                       or (istanza_amministratore is null AND upper (p_owner) =
                                                                               'AD4'
                          )
                       or (upper(user_oracle) = upper (p_owner)
                          )
                      )
                      AND ((exists (select 'x'
                                  from istanze
                                 where progetto = 'AD4'
                                    and instr(installazione||'.','.MASTER.') >0)
                       and user_oracle in (select username from all_users)
                       )
                       OR
                       not exists (select 'x'
                                     from istanze
                                    where progetto = 'AD4'
                                      and instr(installazione||'.','.MASTER.') >0))
      UNION
      SELECT UPPER(p_user)
        FROM dual
       WHERE p_user <> 'ALL'
   ;
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
******************************************************************************/
   FUNCTION VERSIONE
      RETURN VARCHAR2;
/******************************************************************************
 NOME:        GRANT_TO
 DESCRIZIONE: Assegna le grant all'utente passato.
 PARAMETRI:   p_user utente oracle a cui devono essere assegnate le grant.
 RITORNA:     stringa contenente la concatenazione di eventuali errori.
******************************************************************************/
   FUNCTION GRANT_TO (
      p_user        IN   VARCHAR2
    , p_what        IN   VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN   VARCHAR2 DEFAULT 'all'
    , p_owner       IN   VARCHAR2 default USER
   )
      RETURN VARCHAR2;

/******************************************************************************
 NOME:        GRANT_TO
 DESCRIZIONE: Assegna le grant all'utente passato.
 PARAMETRI:   p_user utente oracle a cui devono essere assegnate le grant.
 RITORNA:     stringa contenente la concatenazione di eventuali errori.
******************************************************************************/
   PROCEDURE GRANT_TO (
      p_user        IN   VARCHAR2
    , p_what        IN   VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN   VARCHAR2 DEFAULT 'all'
    , p_owner       IN   VARCHAR2 default USER
   );
/******************************************************************************
 NOME:        EXECUTE_GRANT_OR_REVOKE
 DESCRIZIONE:
******************************************************************************/
   PROCEDURE EXECUTE_GRANT_OR_REVOKE
   (
      p_execute_grant_or_revoke   IN       VARCHAR2
    , p_user                      IN       VARCHAR2
    , p_what                      IN       VARCHAR2
    , p_error                     IN OUT   VARCHAR2
    , p_privilege                 IN       VARCHAR2 DEFAULT 'all'
    , p_owner                     IN       VARCHAR2 default USER
   );
/******************************************************************************
 NOME:        GRANT_TO_ALL
 DESCRIZIONE: Assegna le grant a tutti gli utenti oracle per cui esiste almeno
              un record nella tabella ISTANZE.
 ARGOMENTI:   --
******************************************************************************/
   PROCEDURE GRANT_TO_ALL (
      p_what        IN   VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN   VARCHAR2 DEFAULT 'all'
    , p_owner       IN   VARCHAR2 default USER
   );
/******************************************************************************
 NOME:        REVOKE_FROM
 DESCRIZIONE: Revoca le grant all'utente passato.
 PARAMETRI:   p_user utente oracle a cui devono essere assegnate le grant.
              p_what DB:  oggetti di base
                     CM:  gestione Comuni
                     BS:  gestione Banche e Sportelli
                     ASL: gestione ASL
                     ALL: tutti gli oggetti
 RITORNA:     stringa contenente la concatenazione di eventuali errori.
******************************************************************************/
   FUNCTION REVOKE_FROM (
      p_user        IN   VARCHAR2
    , p_what        IN   VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN   VARCHAR2 DEFAULT 'all'
    , p_owner       IN   VARCHAR2 default USER
   )
      RETURN VARCHAR2;
/******************************************************************************
 NOME:        REVOKE_FROM_ALL
 DESCRIZIONE: revoca le grant a tutti gli utenti oracle per cui esiste almeno
              un record nella tabella ISTANZE.
 ARGOMENTI:   p_what      DB:  oggetti di base
                          CM:  gestione Comuni
                          BS:  gestione Banche e Sportelli
                          ASL: gestione ASL
                          ALL: tutti gli oggetti
                          nome singolo oggetto
              p_privilege privilegio da revocare agli utenti per
                          l'accesso alle tabelle (per package, procedure e
                          function viene dato sempre execute).
                          default 'all'
******************************************************************************/
   PROCEDURE REVOKE_FROM_ALL (
      p_what        IN   VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN   VARCHAR2 DEFAULT 'all'
    , p_owner       IN   VARCHAR2 default USER
   );
/******************************************************************************
 NOME:        CREATE_SYNONYM
 DESCRIZIONE: Crea il sinonimo per l'oggetto dato e ritorna eventuali
              errori in p_error.
              Se il sinonimo esiste gia'
                 se corrisponde (stesso oggetto e stesso proprietario),
                     non fa nulla,
                 altrimenti
                     droppa il sinonimo e lo ricrea.
 ARGOMENTI:   p_user:         utente in cui creare il sinonimo
              p_synonym_name: nome da assegnare al sinonimo
              p_object:       oggetto di cui fare il sinonimo
              p_error:        eventuale errore in creazione sinonimo.
******************************************************************************/
   PROCEDURE CREATE_SYNONYM (
      p_user                    VARCHAR2
    , p_synonym_name            VARCHAR2
    , p_object                  VARCHAR2
    , p_error          IN OUT   VARCHAR2
    , p_owner                   VARCHAR2 default USER
   );
/******************************************************************************
 NOME:        CREATE_PUBLIC_SYNONYM
 DESCRIZIONE: Crea il sinonimo pubblico per l'oggetto dato e ritorna eventuali
              errori in p_error.
              Se il sinonimo pubblico esiste gia'
                 se corrisponde (stesso oggetto e stesso proprietario), non fa
                 nulla,
                 altrimenti droppa il sinonimo e lo ricrea.
 ARGOMENTI:   p_synonym_name: nome da assegnare al sinonimo
              p_object:       oggetto di cui fare il sinonimo
              p_error:        eventuale errore in creazione sinonimo.
******************************************************************************/
   PROCEDURE CREATE_PUBLIC_SYNONYM (
      p_synonym_name            VARCHAR2
    , p_object                  VARCHAR2
    , p_error          IN OUT   VARCHAR2
   );
/******************************************************************************
 NOME:        CREATE_SYNONYMS
 DESCRIZIONE: Crea tutti i sinonimi agli oggetti.
 ARGOMENTI:   p_user:      utente in cui creare il sinonimo
              p_what:      DB:  oggetti di base
                           CM:  gestione Comuni
                           BS:  gestione Banche e Sportelli
                           ASL: gestione ASL
                           ALL: tutti gli oggetti
 RITORNA:     stringa contenente la concatenazione di eventuali errori.
******************************************************************************/
   FUNCTION CREATE_SYNONYMS
   (
      p_user   IN   VARCHAR2 default 'ALL'
    , p_what   IN   VARCHAR2 default 'ALL'
    , p_owner  IN   VARCHAR2 default USER
   ) RETURN VARCHAR2;
/******************************************************************************
 NOME:        CREATE_SYNONYMS
 DESCRIZIONE: Crea tutti i sinonimi agli oggetti.
 ARGOMENTI:   p_user:      utente in cui creare il sinonimo
              p_what:      DB:  oggetti di base
                           CM:  gestione Comuni
                           BS:  gestione Banche e Sportelli
                           ASL: gestione ASL
                           ALL: tutti gli oggetti
 RITORNA:     stringa contenente la concatenazione di eventuali errori.
******************************************************************************/
   PROCEDURE CREATE_SYNONYMS
   (
      p_user   IN   VARCHAR2 default 'ALL'
    , p_what   IN   VARCHAR2 default 'ALL'
    , p_owner  IN   VARCHAR2 default USER
   );
/******************************************************************************
 NOME:        CREATE_PUBLIC_SYNONYMS
 DESCRIZIONE: Crea tutti i sinonimi pubblici agli oggetti.
 ARGOMENTI:   --
******************************************************************************/
   PROCEDURE CREATE_PUBLIC_SYNONYMS (p_what IN VARCHAR2 DEFAULT 'ALL');
/******************************************************************************
 NOME:        DROP_SYNONYM
 DESCRIZIONE: Droppa il sinonimo per l'oggetto dato e ritorna eventuali
              errori in p_error.
 ARGOMENTI:   p_user:         utente in cui creare il sinonimo
              p_synonym_name: nome da assegnare al sinonimo
              p_object:       oggetto di cui fare il sinonimo
              p_error:        eventuale errore in creazione sinonimo.
******************************************************************************/
   PROCEDURE DROP_SYNONYM (
      p_user     in       varchar2
    , p_object   in       VARCHAR2
    , p_error    IN OUT   VARCHAR2
    , p_owner    IN       VARCHAR2 DEFAULT USER
   );
/******************************************************************************
 NOME:        DROP_PUBLIC_SYNONYM
 DESCRIZIONE: Droppa il sinonimo pubblico per l'oggetto dato e ritorna eventuali
              errori in p_error.
 ARGOMENTI:   p_object:       oggetto di cui droppare il sinonimo
              p_error:        eventuale errore in eliminazione sinonimo.
******************************************************************************/
   PROCEDURE DROP_PUBLIC_SYNONYM (p_object VARCHAR2, p_error IN OUT VARCHAR2);
/******************************************************************************
 NOME:        DROP_SYNONYMS
 DESCRIZIONE: Droppa tutti i sinonimi agli oggetti.
 ARGOMENTI:   p_user:         utente in cui creare il sinonimo
              p_what DB:  oggetti di base
                     CM:  gestione Comuni
                     BS:  gestione Banche e Sportelli
                     ASL: gestione ASL
                     ALL: tutti gli oggetti
 RITORNA:     stringa contenente la concatenazione di eventuali errori.
******************************************************************************/
   FUNCTION DROP_SYNONYMS
   (
      p_user   in   varchar2
    , p_what   IN   VARCHAR2 DEFAULT 'ALL'
    , p_owner  IN   VARCHAR2 DEFAULT USER
   ) RETURN VARCHAR2;
/******************************************************************************
 NOME:        DROP_SYNONYMS
 DESCRIZIONE: Droppa tutti i sinonimi agli oggetti.
 ARGOMENTI:   p_user:         utente in cui creare il sinonimo
              p_what DB:  oggetti di base
                     CM:  gestione Comuni
                     BS:  gestione Banche e Sportelli
                     ASL: gestione ASL
                     ALL: tutti gli oggetti
******************************************************************************/
   PROCEDURE DROP_SYNONYMS (
      p_user   in   varchar2
    , p_what   IN   VARCHAR2 DEFAULT 'ALL'
    , p_owner  IN   VARCHAR2 DEFAULT USER
   );
/******************************************************************************
 NOME:        DROP_PUBLIC_SYNONYMS
 DESCRIZIONE: Droppa tutti i sinonimi pubblici agli oggetti.
 ARGOMENTI:   p_what DB:  oggetti di base
                     CM:  gestione Comuni
                     BS:  gestione Banche e Sportelli
                     ASL: gestione ASL
                     ALL: tutti gli oggetti
******************************************************************************/
   PROCEDURE DROP_PUBLIC_SYNONYMS (p_what IN VARCHAR2 DEFAULT 'ALL');
/******************************************************************************
 NOME:        CREA_SYN_GRANT_AD4_SECONDARI
 DESCRIZIONE: Crea i sinonimi privati e assegna le grant all'utente dato di
              tutte le funzioni, package, procedure, tabelle e viste che gia'
              non possiede.
 ARGOMENTI:   p_grantee:   utente a cui assegnare le grant ed in cui creare i
                           sinonimi.
 NOTE:        - Controlla che l'utente dato sia associato ad un'istanza del
                progetto AD4.
              - I sinonimi si chiamano come gli oggetti originali.
******************************************************************************/
   PROCEDURE CREA_SYN_GRANT_AD4_SECONDARI (p_grantee IN VARCHAR2);
/******************************************************************************
 NOME:        DROP_SYN_GRANT_AD4_SECONDARI
 DESCRIZIONE: Elimina i sinonimi e revoca le grant all'utente dato di tutti gli
              oggetti di AD4.
 ARGOMENTI:   p_grantee:   utente a cui revocare le grant ed in cui eliminare i
                           sinonimi privati.
******************************************************************************/
   PROCEDURE DROP_SYN_GRANT_AD4_SECONDARI (p_grantee IN VARCHAR2);
   PROCEDURE grant_da_anagrafe_as4 (
      p_user_anagrafe                  in     ISTANZE.user_oracle%type
    , p_user_amministratore            in     ISTANZE.user_oracle%type
    , p_error                          in out varchar2);
   PROCEDURE crea_syn_anagrafe_as4 (
      p_user_anagrafe                  in     ISTANZE.user_oracle%type
    , p_user_amministratore            in     ISTANZE.user_oracle%type
    , p_error                          in out varchar2);
/******************************************************************************
 NOME:        KILL_ISTANZA_SESSION.
 DESCRIZIONE: elimina tutte le sessioni attive per l'istanza indicata
 ARGOMENTI:   p_istanza IN VARCHAR2 istanza a cui si accede.
 ECCEZIONI:   Non possiede eccezioni proprie, ma lancia la procedure
              KILL_SESSION
              che potrebbe risultare in una eccezione.
 04   23/02/2012 SN     Possibilita di killare tutte le sessioni di una istanza
******************************************************************************/
   PROCEDURE KILL_ISTANZA_SESSION (
      p_istanza   VARCHAR2
   );
/******************************************************************************
 NOME:        KILL_USER_SESSION.
 DESCRIZIONE: elimina tutte le sessioni attive dell'utente.
 ARGOMENTI:   p_istanza IN VARCHAR2 istanza a cui si accede.
              p_modulo  IN VARCHAR2 modulo a cui si accede.
              p_utente  IN VARCHAR2 utente che accede.
 ECCEZIONI:   Non possiede eccezioni proprie, ma lancia la procedure
              KILL_SESSION
              che potrebbe risultare in una eccezione.
******************************************************************************/
   PROCEDURE KILL_USER_SESSION (
      p_istanza   VARCHAR2
    , p_modulo    VARCHAR2
    , p_utente    VARCHAR2
   );
/******************************************************************************
 NOME:        KILL_SESSION.
 DESCRIZIONE: elimina la singola sessione.
 ARGOMENTI:   p_session_id IN NUMBER numero identificativo della sessione.
 ECCEZIONI:   20998, Warning generico: 'Rilevati processi attivi che
                                        l'Amministratore Database non e' in
                                        grado di terminare.'
 ANNOTAZIONI: chiamata da KILL_USER_SESSION.
******************************************************************************/
   PROCEDURE KILL_SESSION (p_session_id NUMBER);
   FUNCTION REFRESH_SLAVES_JOB
   ( p_refresh_group                       in varchar2
   , p_istanza_master                      in varchar2 default si4.istanza
   )
   RETURN varchar2;
   PROCEDURE REFRESH_SLAVES;
END Admin_Ad4;
/
