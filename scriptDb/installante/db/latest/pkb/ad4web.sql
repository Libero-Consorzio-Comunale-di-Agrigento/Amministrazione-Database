--liquibase formatted sql

--changeset mturra:201901301231_102 runOnChange:true stripComments:false

CREATE OR REPLACE PACKAGE BODY Ad4web
IS


element_escape    constant varchar2(1)   := '\';

/******************************************************************************
 NOME:        AD4WEB.
 DESCRIZIONE: .
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    26/11/2002 MM     Creazione.
 1    30/07/2012 SN      Utilizzo dei %TYPE nella dichiarazione delle variabili
 4   21/08/2018  SN      Aggiunti parametri x aggiunta campi tabella utente
 5   21/08/2019  AD      Modificata    FUNCTION ISTANZE_PM per corretta gestione dei campi
                                    versione e installazione
 6  27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
                       (modificato quanto introdotto precedentemente con indicazione md5)
 7    09/06/2020 SN    Gestione visualizzazione errore Feature #42907
 8    09/06/2020 SN    Aggiornando un soggetto sistemare utente modifica, competenza e competenza_esclusiva Bug #40685
                       competenza forzata = AD4 e competenza_esclusiva nulla
 9    14/04/2021 SN    Errore http 400 se nel url c'è [ o | come parametro Bug #49624
******************************************************************************/

   FUNCTION VERSIONE
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0                      Creazione.
 1    30/07/2012 SN     Utilizzo dei %TYPE nella dichiarazione delle variabili
******************************************************************************/
   BEGIN
      RETURN 'V1.8';
   END VERSIONE;
   FUNCTION PROGETTI_PM
/******************************************************************************
 NOME:        PROGETTI_PM
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     --.
 NOTE:
******************************************************************************/
   (
      p_progetto       IN   VARCHAR2
    , p_progetto_old   IN   VARCHAR2
    , p_descrizione    IN   VARCHAR2
    , p_priorita       IN   NUMBER
    , p_note           IN   VARCHAR2
   )
      RETURN VARCHAR2
   IS
      d_esiste         NUMBER (1);
      d_progetto       VARCHAR2 (8);
      d_progetto_old   VARCHAR2 (8);
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_progetto IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_progetto'' obbligatorio.'
                                 );
      END IF;
      d_progetto := UPPER (p_progetto);
      d_progetto_old := UPPER (p_progetto_old);
      /*-----------------------------------------------------
                    Controllo esistenza progetto.
      -----------------------------------------------------*/
      BEGIN
         SELECT 1
           INTO d_esiste
           FROM PROGETTI
          WHERE progetto = d_progetto_old
         UNION
         SELECT 1
           FROM dual
          WHERE d_progetto_old IS NULL;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR
               (-20999
              , 'Progetto originale non piu'' esistente. Impossibile aggiornare il progetto.'
              , TRUE
               );
         WHEN OTHERS
         THEN
            RAISE;
      END;
/*-----------------------------------------------------
           INSERIMENTO progetto
-----------------------------------------------------*/
      IF d_progetto_old IS NULL
      THEN
         BEGIN
            INSERT INTO PROGETTI
                        (progetto, descrizione, priorita, note
                        )
                 VALUES (d_progetto, p_descrizione, p_priorita, p_note
                        );
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE_APPLICATION_ERROR (-20999, Si4.get_error (SQLERRM)
                                      , TRUE);
         END;
      ELSE
/*-----------------------------------------------------
         AGGIORNAMENTO progetto
-----------------------------------------------------*/
         BEGIN
            UPDATE PROGETTI
               SET progetto = d_progetto
                 , descrizione = p_descrizione
                 , priorita = p_priorita
                 , note = p_note
             WHERE progetto = d_progetto_old;
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
      RETURN d_progetto;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END PROGETTI_PM;
   PROCEDURE PROGETTI_PD
/******************************************************************************
 NOME:        PROGETTI_PD
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     --.
 NOTE:
******************************************************************************/
   (p_progetto IN VARCHAR2)
   IS
      d_progetto    VARCHAR2 (8);
      d_check       NUMBER (1)      := 0;
      d_error       NUMBER (1)      := 0;
      d_msg_error   VARCHAR2 (1000);
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_progetto IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_progetto'' obbligatorio.'
                                 );
      END IF;
      d_progetto := UPPER (p_progetto);
      /*-----------------------------------------------------
           Verifica se progetto puo' essere eliminato
      -----------------------------------------------------*/
      d_msg_error :=
            'Impossibile eliminare il progetto '
         || d_progetto
         || '.'
         || CHR (10)
         || CHR (13)
         || 'Esistono:'
         || CHR (10)
         || CHR (13);
      SELECT COUNT (1)
        INTO d_check
        FROM MODULI
       WHERE progetto = d_progetto;
      IF d_check > 0
      THEN
         d_error := d_check;
         d_msg_error := d_msg_error || '- MODULI' || CHR (10) || CHR (13);
      END IF;
      SELECT COUNT (1)
        INTO d_check
        FROM ISTANZE
       WHERE progetto = d_progetto;
      IF d_check > 0
      THEN
         d_error := d_check;
         d_msg_error := d_msg_error || '- ISTANZE' || CHR (10) || CHR (13);
      END IF;
      SELECT COUNT (1)
        INTO d_check
        FROM RUOLI
       WHERE progetto = d_progetto;
      IF d_check > 0
      THEN
         d_error := d_check;
         d_msg_error := d_msg_error || '- RUOLI' || CHR (10) || CHR (13);
      END IF;
      SELECT COUNT (1)
        INTO d_check
        FROM CARATTERISTICHE_ACCESSO
       WHERE progetto = d_progetto;
      IF d_check > 0
      THEN
         d_error := d_check;
         d_msg_error :=
               d_msg_error
            || '- CARATTERISTICHE DI ACCESSO'
            || CHR (10)
            || CHR (13);
      END IF;
/*-----------------------------------------------------
         ELIMINAZIONE progetto
-----------------------------------------------------*/
      IF d_error = 0
      THEN
         BEGIN
            DELETE      PROGETTI
                  WHERE progetto = d_progetto;
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      ELSE
         d_msg_error := d_msg_error || 'ad esso collegati.';
         RAISE_APPLICATION_ERROR (-20999, d_msg_error, TRUE);
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END PROGETTI_PD;
   FUNCTION RUOLI_PM
/******************************************************************************
 NOME:        RUOLI_PM
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     --.
 NOTE:
******************************************************************************/
   (
      p_ruolo           IN   VARCHAR2
    , p_ruolo_old       IN   VARCHAR2
    , p_descrizione     IN   VARCHAR2
    , p_progetto        IN   VARCHAR2
    , p_modulo          IN   VARCHAR2
    , p_profilo         IN   NUMBER
    , p_stato           IN   VARCHAR2
    , p_gruppo_lavoro   IN   VARCHAR2
    , p_gruppo_so       IN   VARCHAR2
   )
      RETURN VARCHAR2
   IS
      d_esiste      NUMBER (1);
      d_ruolo       VARCHAR2 (8);
      d_ruolo_old   VARCHAR2 (8);
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_ruolo IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_ruolo'' obbligatorio.'
                                 );
      END IF;
      /*-----------------------------------------------------
                   Verifica valori passati.
      -----------------------------------------------------*/
      IF p_stato NOT IN ('U', 'R')
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                ,    'Valore '''
                                  || p_stato
                                  || ''' non ammesso per parametro ''p_stato''.'
                                 );
      END IF;
      IF p_gruppo_lavoro NOT IN ('S', 'N')
      THEN
         RAISE_APPLICATION_ERROR
                         (-20999
                        ,    'Valore '''
                          || p_gruppo_lavoro
                          || ''' non ammesso per parametro ''p_gruppo_lavoro''.'
                         );
      END IF;
      IF p_gruppo_so NOT IN ('S', 'N')
      THEN
         RAISE_APPLICATION_ERROR
                             (-20999
                            ,    'Valore '''
                              || p_gruppo_so
                              || ''' non ammesso per parametro ''p_gruppo_so''.'
                             );
      END IF;
      d_ruolo := UPPER (p_ruolo);
      d_ruolo_old := UPPER (p_ruolo_old);
      /*-----------------------------------------------------
                    Controllo esistenza ruolo.
      -----------------------------------------------------*/
      BEGIN
         SELECT 1
           INTO d_esiste
           FROM RUOLI
          WHERE ruolo = d_ruolo_old
         UNION
         SELECT 1
           FROM dual
          WHERE d_ruolo_old IS NULL;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR
               (-20999
              , 'Ruolo originale non piu'' esistente. Impossibile aggiornare il ruolo.'
              , TRUE
               );
         WHEN OTHERS
         THEN
            RAISE;
      END;
/*-----------------------------------------------------
           INSERIMENTO ruolo
-----------------------------------------------------*/
      IF d_ruolo_old IS NULL
      THEN
         BEGIN
            INSERT INTO RUOLI
                        (ruolo, descrizione, progetto, modulo
                       , profilo, stato, gruppo_lavoro, gruppo_so
                        )
                 VALUES (d_ruolo, p_descrizione, p_progetto, p_modulo
                       , p_profilo, p_stato, p_gruppo_lavoro, p_gruppo_so
                        );
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE_APPLICATION_ERROR (-20999, Si4.get_error (SQLERRM)
                                      , TRUE);
         END;
      ELSE
/*-----------------------------------------------------
         AGGIORNAMENTO ruolo
-----------------------------------------------------*/
         BEGIN
            UPDATE RUOLI
               SET ruolo = d_ruolo
                 , descrizione = p_descrizione
                 , progetto = p_progetto
                 , modulo = p_modulo
                 , profilo = p_profilo
                 , stato = p_stato
                 , gruppo_lavoro = p_gruppo_lavoro
                 , gruppo_so = p_gruppo_so
             WHERE ruolo = d_ruolo_old;
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
      RETURN d_ruolo;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END RUOLI_PM;
   FUNCTION MODULI_PM
/******************************************************************************
 NOME:        MODULI_PM
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     --.
 NOTE:
******************************************************************************/
   (
      p_modulo        IN   VARCHAR2
    , p_modulo_old    IN   VARCHAR2
    , p_progetto      IN   VARCHAR2
    , p_descrizione   IN   VARCHAR2
    , p_note          IN   VARCHAR2
    , p_amministratore in  varchar2
   )
      RETURN VARCHAR2
   IS
      d_esiste       NUMBER (1);
      d_modulo_old   VARCHAR2 (8);
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_modulo IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_modulo'' obbligatorio.'
                                 );
      END IF;
      d_modulo_old := UPPER (p_modulo_old);
      /*-----------------------------------------------------
                    Controllo esistenza modulo.
      -----------------------------------------------------*/
      BEGIN
         SELECT 1
           INTO d_esiste
           FROM MODULI
          WHERE modulo = d_modulo_old
         UNION
         SELECT 1
           FROM dual
          WHERE d_modulo_old IS NULL;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR
               (-20999
              , 'Modulo originale non piu'' esistente. Impossibile aggiornare il Modulo.'
              , TRUE
               );
         WHEN OTHERS
         THEN
            RAISE;
      END;
/*-----------------------------------------------------
           INSERIMENTO modulo
-----------------------------------------------------*/
      IF d_modulo_old IS NULL
      THEN
         BEGIN
            INSERT INTO MODULI
                        (modulo, descrizione, progetto, note, amministratore
                        )
                 VALUES (UPPER (p_modulo), p_descrizione, p_progetto, p_note, p_amministratore
                        );
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE_APPLICATION_ERROR (-20999, Si4.get_error (SQLERRM)
                                      , TRUE);
         END;
      ELSE
/*-----------------------------------------------------
         AGGIORNAMENTO modulo
-----------------------------------------------------*/
         BEGIN
            UPDATE MODULI
               SET modulo = UPPER (p_modulo)
                 , descrizione = p_descrizione
                 , progetto = p_progetto
                 , note = p_note
                 , amministratore = p_amministratore
             WHERE modulo = d_modulo_old;
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
      RETURN '';
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END MODULI_PM;
   FUNCTION GET_DATI_ISTANZA
/******************************************************************************
 NOME:        GET_DATI_ISTANZA
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati dell'istanza.
 NOTE:
******************************************************************************/
   (p_istanza IN VARCHAR2)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_stringa   VARCHAR2 (4000);
   BEGIN
      SELECT    'L''istanza e'' associata all''Ente <strong>'
             || ent.descrizione
             || '</strong>'
             || ' e utilizza la Lingua <strong>'
             || livi.descrizione
             || '</strong>.'
             || '<br>Dislocazione: '
             || ista.dislocazione
             || DECODE (ista.dislocazione_temporanea
                      , NULL, TO_CHAR (NULL)
                      , ' - Temporanea: ' || ista.dislocazione_temporanea
                       )
             || '<br><strong>Connessione</strong>'
             || '<ul style="margin-top:0;margin-bottom:0"><li>User: '
             || ista.user_oracle
             || '.</li>'
             || decode
                   (ista.progetto
                  , 'AD4', to_char (null)
                  ,  '<li>Istanza Amministratore: '
                        || '<strong>'
                        || nvl(ista.istanza_amministratore, istanza_amm_pkg.GET_ISTANZA_AD4())
                        || ' - '
                        || istanza.GET_DESCRIZIONE
                                                  (nvl(ista.istanza_amministratore, istanza_amm_pkg.GET_ISTANZA_AD4()))
                        || '</strong>'
                        || '.</li>'
                   )
             || DECODE (ista.link_oracle
                      , NULL, TO_CHAR (NULL)
                      , '<li>Link Oracle: ' || ista.link_oracle || '.</li>'
                       )
             || DECODE (ista.database_link
                      , NULL, TO_CHAR (NULL)
                      , '<li>Database Link: ' || ista.database_link
                        || '.</li>'
                       )
             || '</ul>'
             || DECODE
                   (ista.versione
                  , NULL, 'L''Istanza <strong>NON</strong> risulta <strong>installata</strong>.<br>'
                  ,    '<strong>Installazione</strong>'
                    || DECODE
                          (ista.installazione
                         , NULL, ''
                         ,    '<ul style="margin-top:0;margin-bottom:0"><li>Risultano installati i Componenti '
                           || ista.installazione
                           || '.</li> '
                          )
                    || '<li>Istanza aggiornata alla Versione <strong>'
                    || ista.versione
                    || '</strong>.</li></ul>'
                   )
             || DECODE (ista.note
                      , NULL, 'Nessuna nota associata all''istanza'
                      , 'Note: ' || ista.note
                       )
             || '.'
        INTO d_stringa
        FROM ISTANZE ista, ENTI ent, LINGUE_VIEW livi
       WHERE ista.Istanza = p_istanza
         AND ista.ente = ent.ente
         AND livi.lingua = ista.lingua(+);
      RETURN d_stringa;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_DATI_ISTANZA;
   FUNCTION ISTANZE_PM
/******************************************************************************
 NOME:        ISTANZE_PM
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     --.
 NOTE:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 6  27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
                       (modificato quanto introdotto precedentemente con indicazione md5)
******************************************************************************/
   (
      p_istanza                      IN   VARCHAR2
    , p_istanza_old                  IN   VARCHAR2
    , p_progetto                     IN   VARCHAR2
    , p_descrizione                  IN   VARCHAR2
    , p_ente                         IN   VARCHAR2
    , p_user_oracle                  IN   VARCHAR2
    , p_password_oracle              IN   VARCHAR2
    , p_pwd_modified                 IN   VARCHAR2
    , p_dislocazione                 IN   VARCHAR2
    , p_dislocazione_temporanea      IN   VARCHAR2
    , p_lingua                       IN   VARCHAR2
    , p_link_oracle                  IN   VARCHAR2
    , p_database_link                IN   VARCHAR2
    , p_servizio                     IN   VARCHAR2
    , p_note                         IN   VARCHAR2
    , p_database_driver              IN   VARCHAR2
    , p_istanza_amministratore       IN   VARCHAR2
    , p_istanza_amministratore_old   IN   VARCHAR2
   )
      RETURN VARCHAR2
   IS
      d_esiste        NUMBER (1);
      d_istanza_old   istanze.istanza%TYPE;
      d_pwd           istanze.password_oracle%TYPE;
      d_installazione ISTANZE.INSTALLAZIONE%type;
      d_versione ISTANZE.versIONE%type;
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_istanza IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_istanza'' obbligatorio.'
                                 );
      END IF;
      d_istanza_old := UPPER (p_istanza_old);
      /*-----------------------------------------------------
                    Controllo esistenza istanza.
      -----------------------------------------------------*/
      BEGIN
         SELECT 1
           INTO d_esiste
           FROM ISTANZE
          WHERE Istanza = d_istanza_old
         UNION
         SELECT 1
           FROM dual
          WHERE d_istanza_old IS NULL;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR
               (-20999
              , 'Istanza originale non piu'' esistente. Impossibile aggiornare l''Istanza.'
              , TRUE
               );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      d_pwd := p_password_oracle;
      IF p_pwd_modified = 'Y'
      THEN
         Crypt.CRYPT_PASSWORD (d_pwd,'STANDARD'); -- rev. 6
      END IF;
/*-----------------------------------------------------
           INSERIMENTO istanza
-----------------------------------------------------*/
      IF d_istanza_old IS NULL
      THEN
         BEGIN
            ISTANZA_AMM_PKG.INS_COMMIT
                     (p_ISTANZA                      => UPPER (p_istanza)
                    , p_PROGETTO                     => p_progetto
                    , p_ENTE                         => p_ente
                    , p_DESCRIZIONE                  => p_descrizione
                    , p_DATABASE_USER                => p_user_oracle
                    , p_DATABASE_PASSWORD            => d_pwd
                    , p_DISLOCAZIONE                 => p_dislocazione
                    , p_DISLOCAZIONE_TEMPORANEA      => p_dislocazione_temporanea
                    , p_NOTE                         => p_note
                    , p_LINGUA                       => p_lingua
                    , p_LINK_ORACLE                  => p_link_oracle
                    , p_DATABASE_LINK                => p_database_link
                    , p_DATABASE_DRIVER              => p_database_driver
                    , p_SERVIZIO                     => p_servizio
                    , p_ISTANZA_AMMINISTRATORE       => p_ISTANZA_AMMINISTRATORE
                     );
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE_APPLICATION_ERROR (-20999, Si4.get_error (SQLERRM)
                                      , TRUE);
         END;
      ELSE
/*-----------------------------------------------------
         AGGIORNAMENTO istanza
-----------------------------------------------------*/
         BEGIN
               -- aggiunti in versione 4.5.16
            d_versione := istanze_tpk.get_versione(UPPER (p_istanza));
            d_installazione := istanze_tpk.get_installazione(UPPER (p_istanza));
            --
            ISTANZA_AMM_PKG.upd_commit
                (p_NEW_ISTANZA                      => UPPER (p_istanza)
               , p_NEW_PROGETTO                     => p_progetto
               , p_NEW_ENTE                         => p_ente
               , p_NEW_DESCRIZIONE                  => p_descrizione
               , p_NEW_DATABASE_USER                => p_user_oracle
               , p_NEW_DATABASE_PASSWORD            => d_pwd
               , p_NEW_DISLOCAZIONE                 => p_dislocazione
               , p_NEW_DISLOCAZIONE_TEMPORANEA      => p_dislocazione_temporanea
               , p_NEW_NOTE                         => p_note
               , p_NEW_LINGUA                       => p_lingua
               , p_NEW_LINK_ORACLE                  => p_link_oracle
               , p_NEW_DATABASE_LINK                => p_database_link
               , p_NEW_DATABASE_DRIVER              => p_database_driver
               , p_NEW_SERVIZIO                     => p_servizio
               , p_NEW_ISTANZA_AMMINISTRATORE       => p_ISTANZA_AMMINISTRATORE
               , p_OLD_ISTANZA                      => d_ISTANZA_OLD
               , p_OLD_ISTANZA_AMMINISTRATORE       => p_ISTANZA_AMMINISTRATORE_OLD
               -- aggiunti in versione 4.5.16
               ,p_new_installazione => d_installazione
               ,p_new_versione => d_versione
               --
                );
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
      RETURN '';
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END ISTANZE_PM;
   FUNCTION GET_DATI_SERVIZIO
/******************************************************************************
 NOME:        GET_DATI_SERVIZIO
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati del servizio.
 NOTE:
******************************************************************************/
   (p_id_servizio IN NUMBER)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_stringa   VARCHAR2 (4000);
   BEGIN
      SELECT    DECODE
                   (serv.livello
                  , NULL, TO_CHAR (NULL)
                  ,    'Il servizio permette l''accesso ad utenti che forniscano credenziali con <strong>livello di sicurezza '
                    || lisi.descrizione
                    || '</strong>.<br>'
                   )
             || 'L''<strong>abilitazione</strong> di un utente al servizio in seguito a registrazione da portale avviene in modalita'' <strong>'
             || DECODE (abilitazione
                      , 'A', 'Automatica'
                      , 'C', 'Controllata'
                      , abilitazione
                       )
             || '</strong>'
             || ' e '
             || DECODE (multiplo
                      , 'N', '<strong>NON</strong> '
                      , TO_CHAR (NULL)
                       )
             || 'puo'' essere richiesta <strong>piu'' volte</strong>.'
             || '<br>Una volta abilitatati al servizio in seguito a richiesta da portale, gli utenti apparteranno al <strong>gruppo '
             || uten.nominativo
             || '</strong>.'
             || DECODE
                   (tipo_notifica
                  , NULL, TO_CHAR (NULL)
                  ,    '<br>I <strong>messaggi</strong> generati in fase di abilitazione del servizio vengono <strong>notificati via '
                    || INITCAP (tipo_notifica)
                    || '</strong>'
                    || DECODE
                          (LOWER (serv.TIPO_NOTIFICA)
                         , 'mail', ' (tag: '
                            || NVL
                                  (LOWER
                                      (Registro_Pac.get_ad4_string
                                           ('RICHIESTA_ABILITAZIONE/MAIL/CIM'
                                          , 'Tag'
                                          , serv.MODULO
                                          , UPPER (ISTA.USER_ORACLE)
                                           )
                                      )
                                 , 'mail'
                                  )
                            || ')'
                         , ''
                          )
                    || '; in essi viene incluso l''indirizzo Mail dell''Ente <strong>'
                    || mail_notifiche
                    || '</strong> a cui perverranno eventuali <strong>risposte da parte di entita'' terze</strong> coinvolte nella fase di abilitazione di un utente al servizio.'
                    || DECODE
                          (ccr_notifiche
                         , NULL, TO_CHAR (NULL)
                         ,    '<br>I Messaggi Automatici generati in fase di abilitazione del servizio vengono inviati in <strong>CCR</strong> (per conoscenza nascosto) all''indirizzo Mail <strong>'
                           || ccr_notifiche
                           || '</strong>.'
                          )
                   )
             || '<br>Il <strong>recupero</strong> della <strong>password</strong> da parte dell''utente '
             || DECODE (NVL (serv.recupero_password, 'N')
                      , 'N', '<strong>NON</strong> '
                      , ''
                       )
             || 'e'' <strong>abilitato</strong>.'
        INTO d_stringa
        FROM SERVIZI serv, LIVELLI_SICUREZZA lisi, UTENTI uten, ISTANZE ista
       WHERE serv.id_servizio = p_id_servizio
         AND ista.Istanza = serv.Istanza
         AND lisi.livello(+) = serv.livello
         AND uten.Utente = serv.gruppo_abilitazione;
      RETURN d_stringa;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_DATI_SERVIZIO;
   FUNCTION SERVIZI_PM
/******************************************************************************
 NOME:        SERVIZI_PM
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     --.
 NOTE:
******************************************************************************/
   (
      p_id_servizio           IN   NUMBER
    , p_istanza               IN   VARCHAR2
    , p_modulo                IN   VARCHAR2
    , p_livello               IN   VARCHAR2
    , p_abilitazione          IN   VARCHAR2
    , p_multiplo              IN   VARCHAR2
    , p_gruppo_abilitazione   IN   VARCHAR2
    , p_tipo_notifica         IN   VARCHAR2
    , p_mail_notifiche        IN   VARCHAR2
    , p_ccr_notifiche         IN   VARCHAR2
    , p_recupero_password     IN   VARCHAR2
    , p_tag_cim               IN   VARCHAR2
   )
      RETURN NUMBER
   IS
      d_esiste        NUMBER (1);
      d_ista_des      istanze.descrizione%TYPE;
      d_modu_des      moduli.descrizione%TYPE;
      d_id_servizio   NUMBER;
      d_user          istanze.user_oracle%TYPE;
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_istanza IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_istanza'' obbligatorio.'
                                 );
      END IF;
      IF p_modulo IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_modulo'' obbligatorio.'
                                 );
      END IF;
      IF p_abilitazione IS NULL
      THEN
         RAISE_APPLICATION_ERROR
                                (-20999
                               , 'Parametro ''p_abilitazione'' obbligatorio.'
                                );
      END IF;
      IF p_multiplo IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_multiplo'' obbligatorio.'
                                 );
      END IF;
      IF p_gruppo_abilitazione IS NULL
      THEN
         RAISE_APPLICATION_ERROR
                         (-20999
                        , 'Parametro ''p_gruppo_abilitazione'' obbligatorio.'
                         );
      END IF;
      IF p_mail_notifiche IS NULL
      THEN
         RAISE_APPLICATION_ERROR
                              (-20999
                             , 'Parametro ''p_mail_notifiche'' obbligatorio.'
                              );
      END IF;
      /*-----------------------------------------------------
                    Controllo esistenza modulo.
      -----------------------------------------------------*/
      BEGIN
         SELECT descrizione
           INTO d_modu_des
           FROM MODULI
          WHERE modulo = p_modulo;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR (-20999
                                   ,    'Modulo ('''
                                     || p_modulo
                                     || ''') non definito.'
                                   , TRUE
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
                    Controllo esistenza istanza.
      -----------------------------------------------------*/
      BEGIN
         SELECT descrizione, user_oracle
           INTO d_ista_des, d_user
           FROM ISTANZE
          WHERE Istanza = p_istanza;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR (-20999
                                   ,    'Istanza ('''
                                     || p_istanza
                                     || ''') non definita.'
                                   , TRUE
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
                    Controllo esistenza livello.
      -----------------------------------------------------*/
      IF p_livello IS NOT NULL
      THEN
         BEGIN
            SELECT 1
              INTO d_esiste
              FROM LIVELLI_SICUREZZA
             WHERE livello = p_livello;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               RAISE_APPLICATION_ERROR (-20999
                                      ,    'Livello di sicurezza ('''
                                        || p_livello
                                        || ''') non definito.'
                                      , TRUE
                                       );
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
      /*-----------------------------------------------------
            Controllo esistenza gruppo di abilitazione.
      -----------------------------------------------------*/
      BEGIN
         SELECT 1
           INTO d_esiste
           FROM UTENTI
          WHERE Utente = p_gruppo_abilitazione AND tipo_utente = 'G';
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR (-20999
                                   ,    'Gruppo di utenti ('''
                                     || p_gruppo_abilitazione
                                     || ''') non definito.'
                                   , TRUE
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
          Controllo compatibilita' tra modulo ed istanza.
      -----------------------------------------------------*/
      BEGIN
         SELECT 1
           INTO d_esiste
           FROM ISTANZE ista, PROGETTI prog
          WHERE ista.Istanza = p_istanza
            AND prog.progetto = ista.progetto
            AND EXISTS (SELECT 1
                          FROM MODULI
                         WHERE progetto = ista.progetto AND modulo = p_modulo);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR (-20999
                                   ,    'Istanza "'
                                     || d_ista_des
                                     || '" ('''
                                     || p_istanza
                                     || ''') e Modulo "'
                                     || d_modu_des
                                     || '" ('''
                                     || p_modulo
                                     || ''') incompatibili.'
                                   , TRUE
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      d_id_servizio := p_id_servizio;
/*-----------------------------------------------------
           INSERIMENTO servizio
-----------------------------------------------------*/
      IF d_id_servizio IS NULL
      THEN
         BEGIN
            d_id_servizio := Si4.NEXT_ID ('SERVIZI', 'ID_SERVIZIO');
            INSERT INTO SERVIZI
                        (id_servizio, modulo, Istanza, livello
                       , abilitazione, multiplo, gruppo_abilitazione
                       , tipo_notifica, mail_notifiche, ccr_notifiche
                       , recupero_password
                        )
                 VALUES (d_id_servizio, p_modulo, p_istanza, p_livello
                       , p_abilitazione, p_multiplo, p_gruppo_abilitazione
                       , p_tipo_notifica, p_mail_notifiche, p_ccr_notifiche
                       , p_recupero_password
                        );
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      ELSE
/*-----------------------------------------------------
         AGGIORNAMENTO servizio
-----------------------------------------------------*/
         BEGIN
            UPDATE SERVIZI
               SET modulo = p_modulo
                 , Istanza = p_istanza
                 , livello = p_livello
                 , abilitazione = p_abilitazione
                 , multiplo = p_multiplo
                 , gruppo_abilitazione = p_gruppo_abilitazione
                 , tipo_notifica = p_tipo_notifica
                 , mail_notifiche = p_mail_notifiche
                 , ccr_notifiche = p_ccr_notifiche
                 , recupero_password = p_recupero_password
             WHERE id_servizio = d_id_servizio;
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
      Registro_Pac.SET_AD4_STRING ('RICHIESTA_ABILITAZIONE/MAIL/CIM'
                                 , 'Tag'
                                 , p_tag_cim
                                 , p_modulo
                                 , d_user
                                  );
      RETURN d_id_servizio;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END SERVIZI_PM;
   FUNCTION GET_DATI_SOGGETTO
/******************************************************************************
 NOME:        GET_DATI_SOGGETTO
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati del soggetto.
 NOTE:
******************************************************************************/
   (p_soggetto IN NUMBER)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_stringa   VARCHAR2 (4000);
   BEGIN
      SELECT    '<strong>Registrazione Anagrafica</strong>'
             || '<dl style="margin-top:0;margin-bottom:0"><dd>'
             || Soggetto.GET_NOME (p_soggetto) || ' (Num. Ind.: '|| p_soggetto ||')'
             || '</dd>'
             || DECODE (Soggetto.GET_CODICE_FISCALE (p_soggetto)
                      , NULL, TO_CHAR (NULL)
                      ,    '<dd>Codice Fiscale: '
                        || Soggetto.GET_CODICE_FISCALE (p_soggetto)
                        || '</dd>'
                       )
             || DECODE (Soggetto.GET_INDIRIZZO_COMPLETO (p_soggetto)
                      , NULL, TO_CHAR (NULL)
                      ,    '<dd>Residente in '
                        || Soggetto.GET_INDIRIZZO_COMPLETO (p_soggetto)
                        || '</dd>'
                       )
             || '</dl>'
             || DECODE
                   (Soggetto.GET_TELEFONO (p_soggetto)
                  , NULL, DECODE
                       (Soggetto.GET_FAX (p_soggetto)
                      , NULL, DECODE
                           (Soggetto.GET_INDIRIZZO_WEB (p_soggetto)
                          , NULL, TO_CHAR (NULL)
                          , '<strong>Recapiti</strong><dl style="margin-top:0;margin-bottom:0">'
                           )
                      , '<strong>Recapiti</strong><dl style="margin-top:0;margin-bottom:0">'
                       )
                  , '<strong>Recapiti</strong><dl style="margin-top:0;margin-bottom:0">'
                   )
             || DECODE (Soggetto.GET_TELEFONO (p_soggetto)
                      , NULL, TO_CHAR (NULL)
                      ,    '<dd>Telefono: '
                        || Soggetto.GET_TELEFONO (p_soggetto)
                        || '</dd>'
                       )
             || DECODE (Soggetto.GET_FAX (p_soggetto)
                      , NULL, TO_CHAR (NULL)
                      , '<dd>Fax: ' || Soggetto.GET_FAX (p_soggetto)
                        || '</dd>'
                       )
             || DECODE (Soggetto.GET_INDIRIZZO_WEB (p_soggetto)
                      , NULL, TO_CHAR (NULL)
                      ,    '<dd>Indirizzo Web: '
                        || Soggetto.GET_INDIRIZZO_WEB (p_soggetto)
                        || '</dd></dl>'
                       )
        INTO d_stringa
        FROM dual
       WHERE NVL (p_soggetto, 0) > 0;
      RETURN d_stringa;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_DATI_SOGGETTO;
   FUNCTION GET_DATI_ENTE
/******************************************************************************
 NOME:        GET_DATI_ENTE
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati dell'ente.
 NOTE:
******************************************************************************/
   (p_ente IN VARCHAR2)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_stringa   VARCHAR2 (4000);
   BEGIN
      SELECT    '<strong>Codice: </strong>'
             || ente
             || DECODE (BITMAP
                      , TO_CHAR (NULL), ''
                      , '<br>Bitmap dell''Ente: ' || BITMAP
                       )
             || DECODE (disegno
                      , TO_CHAR (NULL), ''
                      , '<br>Disegno: ' || disegno
                       )
             || DECODE (NVL (Soggetto, 0)
                      , 0, ''
                      , '<br>' || GET_DATI_SOGGETTO (Soggetto)
                       )
        INTO d_stringa
        FROM ENTI
       WHERE ente = p_ente;
      RETURN d_stringa;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_DATI_ENTE;
   FUNCTION GET_DATI_UTENTE
/******************************************************************************
 NOME:        GET_DATI_UTENTE
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati dell'utente.
 NOTE:
******************************************************************************/
   (p_utente IN VARCHAR2)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_stringa   VARCHAR2 (4000);
   BEGIN
      SELECT    DECODE (DECODE (NVL (stato, 'U'), 'A', 'U', stato)
                      , 'U', '<strong>Attivo</strong>'
                         || DECODE (NVL (rinnovo_password, 'SI')
                                  , 'SI', ' con '
                                  , ' senza '
                                   )
                         || 'possibilita'' di rinnovo password autonoma.'
                       )
             || DECODE
                      (NVL (stato, 'U')
                     , 'S', '<strong><font color="#ff0000">Sospeso</font></strong>'
                      )
             || DECODE
                     (NVL (stato, 'U')
                    , 'R', '<strong><font color="#ff0000">Revocato</font></strong>'
                     )
             || '<br>'
             || '<strong>Codice: </strong>'
             || Utente
             || decode (amministratore
                       ,null
                       , ''
                       ,'S'
                       ,'<br><strong>Utenza di amministrazione</strong>'
                       ,'')
             || decode( info_identificazione
                      , null
                      ,''
                      , '<br><strong>Informazioni di identificazione: </strong>'||  info_identificazione
                      )
             || decode (utente.get_gruppo_lavoro (Utente,'N',0)
                       ,null
                       ,''
                       , '<br><strong>Gruppo di Lavoro: </strong>'
                         ||utente.get_gruppo_lavoro (Utente,'N',0)
                         ||' - '
                         || ruoli_tpk.get_descrizione (utente.get_gruppo_lavoro (Utente,'N',0)  )
                        )
             || DECODE (NVL (Utente.GET_SOGGETTO (Utente, 'Y'), 0)
                      , 0, '<br>'
                      ,    '<br>'
                        || GET_DATI_SOGGETTO (Utente.GET_SOGGETTO (Utente))
                       )
             || DECODE (Get_Gruppi_Utente (Utente)
                      , NULL, ''
                      ,    '<strong>Gruppi di appartenenza</strong>'
                        || '<dl style="margin-top:0;margin-bottom:0"><dd>'
                        || Get_Gruppi_Utente (Utente, 'N', 'Y')
                        || '.'
                        || '</dd></dl>'
                       )
             || 'L''utente '
             || DECODE
                   (ultimo_tentativo
                  , NULL, 'non ha <strong>mai fatto accesso</strong> ad un prodotto del Sistema Informativo'
                  ,    'ha fatto <strong>accesso</strong> ad un prodotto del Sistema Informativo in data <strong>'
                    || TO_CHAR (ultimo_tentativo, 'dd/mm/yyyy')
                   )
             || '</strong>'
             || DECODE (NVL (numero_tentativi, 0)
                      , 0, TO_CHAR (NULL)
                      ,    ' immettendo una <strong>password errata '
                        || numero_tentativi
                        || ' volte</strong>'
                       )
             || '.'
        INTO d_stringa
        FROM UTENTI
       WHERE Utente = p_utente;
      RETURN d_stringa;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_DATI_UTENTE;
   FUNCTION GET_UTENTI_SOGGETTO
/******************************************************************************
 NOME:        GET_UTENTI_SOGGETTO
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati del soggetto.
 NOTE:
******************************************************************************/
   (p_soggetto IN NUMBER)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_stringa   VARCHAR2 (4000);
   BEGIN
      FOR UTENTI IN (SELECT      '<li>'
                              || Utente.GET_NOMINATIVO (utso.Utente, 'Y')
                              || '</li>' Utente
                         FROM UTENTI_SOGGETTI utso
                        WHERE utso.Soggetto = p_soggetto
                     ORDER BY utso.Utente)
      LOOP
         d_stringa := d_stringa || UTENTI.Utente;
      END LOOP;
      IF d_stringa IS NOT NULL
      THEN
         d_stringa :=
            '<ul style="margin-top:0;margin-bottom:0">' || d_stringa
            || '</ul>';
      END IF;
      RETURN d_stringa;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_UTENTI_SOGGETTO;
   FUNCTION GET_UTENTI_SERVIZIO
/******************************************************************************
 NOME:        GET_UTENTI_SERVIZIO
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2.
 NOTE:
******************************************************************************/
   (
      p_istanza    IN   VARCHAR2
    , p_modulo     IN   VARCHAR2
    , p_progetti   IN   VARCHAR2
    , p_istanze    IN   VARCHAR2
    , p_moduli     IN   VARCHAR2
    , p_servizi    IN   VARCHAR2
   )
      RETURN CLOB
   IS /* SLAVE_COPY */
      d_stringa    VARCHAR2 (32000);
-- gestione tramite clob
      d_amount     BINARY_INTEGER   := 32767;
      d_clob       CLOB             := EMPTY_CLOB ();
      d_clob_dep   CLOB             := EMPTY_CLOB ();
   BEGIN
--   dbms_lob.freetemporary(d_clob);
      dbms_lob.createTemporary (d_clob, TRUE, dbms_lob.SESSION);
      d_stringa := '<dl style="margin-top:0;margin-bottom:0;margin-left:10">';
      d_amount := LENGTH (d_stringa);
--   dbms_lob.freetemporary(d_clob_dep);
      dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
      dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
      dbms_lob.APPEND (d_clob, d_clob_dep);
--   dbms_lob.freetemporary(d_clob_dep);
      FOR UTENTI IN (SELECT DISTINCT uten.Utente, uten.nominativo
                                   , uten.tipo_utente
                                FROM UTENTI uten, DIRITTI_ACCESSO DIAC
                               WHERE uten.Utente = DIAC.Utente
                                 AND DIAC.modulo = NVL (p_modulo, DIAC.modulo)
                                 AND DIAC.Istanza =
                                                 NVL (p_istanza, DIAC.Istanza)
                            ORDER BY 3, 1)
      LOOP
         d_stringa :=
            '<tr><td><dd style="margin-left:50">';
         IF UTENTI.tipo_utente = 'U'
         THEN
            d_stringa :=
                  d_stringa
               || '<img src="../common/images/AD4/Uten.gif" BORDER=0> Utente: <a CLASS="AFCDataLink" href="AD4Utente.do?UTENTE=';
         ELSE
            d_stringa :=
                  d_stringa
               || '<img src="../common/images/AD4/Gruppo.gif" BORDER=0> Gruppo: <a CLASS="AFCDataLink" href="AD4Gruppo.do?GRUPPO=';
         END IF;
         d_stringa :=
               d_stringa
            || UTENTI.Utente
            || '">'
            || UTENTI.Utente
            || ' - '
            || UTENTI.nominativo
            || '</a></dd></td></tr>';
         d_amount := LENGTH (d_stringa);
--      dbms_lob.freetemporary(d_clob_dep);
         dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
         dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
         dbms_lob.APPEND (d_clob, d_clob_dep);
--      dbms_lob.freetemporary(d_clob_dep);
      END LOOP;
      d_stringa := '</dl>';
      d_amount := LENGTH (d_stringa);
--   dbms_lob.freetemporary(d_clob_dep);
      dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
      dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
      dbms_lob.APPEND (d_clob, d_clob_dep);
--   dbms_lob.freetemporary(d_clob_dep);
      RETURN d_clob;
   END GET_UTENTI_SERVIZIO;
   FUNCTION GET_MODULI_PROGETTO
/******************************************************************************
 NOME:        GET_MODULI_PROGETTO
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2.
 NOTE:
******************************************************************************/
   (
      p_progetto   IN   VARCHAR2
    , p_progetti   IN   VARCHAR2
    , p_istanze    IN   VARCHAR2
    , p_moduli     IN   VARCHAR2
    , p_servizi    IN   VARCHAR2
   )
      RETURN CLOB
   IS /* SLAVE_COPY */
      d_stringa    VARCHAR2 (32000);
      d_moduli     VARCHAR2 (32000);
      d_pagina     VARCHAR2 (32000);
      d_modu_par   VARCHAR2 (32000);
      d_expand     VARCHAR2 (200)
               := '<img src="../common/images/AD4/NodoExpand.gif" BORDER=0 >';
      d_collapse   VARCHAR2 (200)
             := '<img src="../common/images/AD4/NodoCollapse.gif" BORDER=0 >';
      d_bitmap     VARCHAR2 (200);
-- gestione tramite clob
      d_amount     BINARY_INTEGER   := 32767;
      d_clob       CLOB             := EMPTY_CLOB ();
      d_clob_dep   CLOB             := EMPTY_CLOB ();
   BEGIN
--   dbms_lob.freetemporary(d_clob);
      dbms_lob.createTemporary (d_clob, TRUE, dbms_lob.SESSION);
      d_moduli := '||' || p_moduli || '||';
      d_stringa := '<dl style="margin-top:0;margin-bottom:0;margin-left:5">';
--   dbms_lob.freetemporary(d_clob_dep);
      dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
      d_amount := LENGTH (d_stringa);
      dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
      dbms_lob.APPEND (d_clob, d_clob_dep);
--   dbms_lob.freetemporary(d_clob_dep);
      d_pagina :=
            'AD4Ambiente.do?PROGETTO='
         || p_progetti
         || '&ISTANZA='
         || p_istanze
         || '&SERVIZIO='
         || p_servizi
         || '&MODULO=';
      FOR MODULI IN (SELECT   modulo, descrizione
                         FROM MODULI
                        WHERE progetto = p_progetto
                     ORDER BY 1)
      LOOP
         d_modu_par := '';
         d_moduli := '||' || p_moduli || '||';
         IF INSTR (d_moduli, '||' || MODULI.modulo || '||') > 0
         THEN
            d_bitmap := d_collapse;
            d_moduli :=
                      REPLACE (d_moduli, '||' || MODULI.modulo || '||', '||');
            IF d_moduli <> '||||'
            THEN
               d_modu_par :=
                     d_modu_par || SUBSTR (d_moduli, 3, LENGTH (d_moduli) - 4);
            END IF;
         ELSE
            d_bitmap := d_expand;
            IF d_moduli <> '||||'
            THEN
               d_modu_par := d_modu_par || SUBSTR (d_moduli, 3);
            END IF;
            d_modu_par := d_modu_par || MODULI.modulo;
         END IF;
         d_stringa :=
               '<tr><td><dd><a CLASS="AFCDataLink" href="'
            || d_pagina
            || d_modu_par
            || '">'
            || d_bitmap
            || '<img src="../common/images/AD4/Modu.gif" BORDER=0></a>'
            || ' Modulo: <a CLASS="AFCDataLink" href="AD4Modulo.do?MVID=1&MODULO='
            || moduli.modulo
            || '&PROGETTO='
            || p_progetto
            || '">'
            || moduli.modulo
            || ' - '
            || moduli.descrizione
            || '</a></dd></td></tr>';
--   dbms_lob.freetemporary(d_clob_dep);
         d_stringa := replace (d_stringa, '|','%7C'); -- rev. 9
         dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
         d_amount := LENGTH (d_stringa);
         dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
         dbms_lob.APPEND (d_clob, d_clob_dep);
--      dbms_lob.freetemporary(d_clob_dep);
         d_moduli := '||' || p_moduli || '||';
         IF INSTR (d_moduli, '||' || MODULI.modulo || '||') > 0
         THEN
--   dbms_lob.freetemporary(d_clob_dep);
            dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
            d_clob_dep :=
               GET_UTENTI_SERVIZIO (''
                                  , MODULI.modulo
                                  , p_progetti
                                  , p_istanze
                                  , p_moduli
                                  , p_servizi
                                   );
            dbms_lob.APPEND (d_clob, d_clob_dep);
--         dbms_lob.freetemporary(d_clob_dep);
         END IF;
      END LOOP;
      d_stringa := '</dl>';
      d_stringa := replace (d_stringa, '|','%7C'); -- rev. 9
      d_amount := LENGTH (d_stringa);
--   dbms_lob.freetemporary(d_clob_dep);
      dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
      dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
      dbms_lob.APPEND (d_clob, d_clob_dep);
--   dbms_lob.freetemporary(d_clob_dep);
      RETURN d_clob;
   END GET_MODULI_PROGETTO;
   FUNCTION GET_ISTANZE_PROGETTO
/******************************************************************************
 NOME:        GET_ISTANZE_PROGETTO
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2.
 NOTE:
******************************************************************************/
   (
      p_progetto   IN   VARCHAR2
    , p_progetti   IN   VARCHAR2
    , p_istanze    IN   VARCHAR2
    , p_moduli     IN   VARCHAR2
    , p_servizi    IN   VARCHAR2
   )
      RETURN CLOB
   IS /* SLAVE_COPY */
      d_stringa    VARCHAR2 (32000);
      d_istanze    VARCHAR2 (32000);
      d_pagina     VARCHAR2 (2000);
      d_ista_par   VARCHAR2 (2000);
      d_expand     VARCHAR2 (200)
               := '<img src="../common/images/AD4/NodoExpand.gif" BORDER=0 >';
      d_collapse   VARCHAR2 (200)
             := '<img src="../common/images/AD4/NodoCollapse.gif" BORDER=0 >';
      d_bitmap     VARCHAR2 (200);
-- gestione tramite clob
      d_amount     BINARY_INTEGER   := 32767;
      d_clob       CLOB             := EMPTY_CLOB ();
      d_clob_dep   CLOB             := EMPTY_CLOB ();
   BEGIN
--   dbms_lob.freetemporary(d_clob);
      dbms_lob.createTemporary (d_clob, TRUE, dbms_lob.SESSION);
      d_istanze := '||' || p_istanze || '||';
      d_stringa := '<dl style="margin-top:0;margin-bottom:0;margin-left:5">';
--   dbms_lob.freetemporary(d_clob_dep);
      dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
      d_amount := LENGTH (d_stringa);
      dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
      dbms_lob.APPEND (d_clob, d_clob_dep);
--   dbms_lob.freetemporary(d_clob_dep);
      d_pagina :=
            'AD4Ambiente.do?PROGETTO='
         || p_progetti
         || '&MODULO='
         || p_moduli
         || '&SERVIZIO='
         || p_servizi
         || '&ISTANZA=';
      FOR ISTANZE IN (SELECT   Istanza, descrizione
                          FROM ISTANZE
                         WHERE progetto = p_progetto
                      ORDER BY 1)
      LOOP
         d_ista_par := '';
         d_istanze := '||' || p_istanze || '||';
         IF INSTR (d_istanze, '||' || ISTANZE.Istanza || '||') > 0
         THEN
            d_bitmap := d_collapse;
            d_istanze :=
                   REPLACE (d_istanze, '||' || ISTANZE.Istanza || '||', '||');
            IF d_istanze <> '||||'
            THEN
               d_ista_par :=
                   d_ista_par || SUBSTR (d_istanze, 3, LENGTH (d_istanze) - 4);
            END IF;
         ELSE
            d_bitmap := d_expand;
            IF d_istanze <> '||||'
            THEN
               d_ista_par := d_ista_par || SUBSTR (d_istanze, 3);
            END IF;
            d_ista_par := d_ista_par || ISTANZE.Istanza;
         END IF;
         d_stringa :=
               '<tr><td><dd><a CLASS="AFCDataLink" href="'
            || d_pagina
            || d_ista_par
            || '">'
            || d_bitmap
            || '<img src="../common/images/AD4/Ista.gif" BORDER=0></a>'
            || ' Istanza: <a CLASS="AFCDataLink" href="AD4Istanza.do?MVID=1&ISTANZA='
            || istanze.istanza
            || '&PROGETTO='
            || p_progetto
            || '">'
            || istanze.istanza
            || ' - '
            || istanze.descrizione
            || '</a></dd></td></tr>';
--   dbms_lob.freetemporary(d_clob_dep);
         d_stringa := replace (d_stringa, '|','%7C'); -- rev. 9
         dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
         d_amount := LENGTH (d_stringa);
         dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
         dbms_lob.APPEND (d_clob, d_clob_dep);
--      dbms_lob.freetemporary(d_clob_dep);
         d_istanze := '||' || p_istanze || '||';
         IF INSTR (d_istanze, '||' || ISTANZE.Istanza || '||') > 0
         THEN
--         dbms_lob.freetemporary(d_clob_dep);
            dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
            d_clob_dep :=
               GET_UTENTI_SERVIZIO (ISTANZE.Istanza
                                  , ''
                                  , p_progetti
                                  , p_istanze
                                  , p_moduli
                                  , p_servizi
                                   );
            dbms_lob.APPEND (d_clob, d_clob_dep);
--         dbms_lob.freetemporary(d_clob_dep);
         END IF;
      END LOOP;
      d_stringa := '</dl>';
      d_amount := LENGTH (d_stringa);
--   dbms_lob.freetemporary(d_clob_dep);
      dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
      dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
      dbms_lob.APPEND (d_clob, d_clob_dep);
--   dbms_lob.freetemporary(d_clob_dep);
      RETURN d_clob;
   END GET_ISTANZE_PROGETTO;
   FUNCTION GET_SERVIZI_PROGETTO
/******************************************************************************
 NOME:        GET_SERVIZI_PROGETTO
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2.
 NOTE:
******************************************************************************/
   (
      p_progetto   IN   VARCHAR2
    , p_progetti   IN   VARCHAR2
    , p_istanze    IN   VARCHAR2
    , p_moduli     IN   VARCHAR2
    , p_servizi    IN   VARCHAR2
   )
      RETURN CLOB
   IS /* SLAVE_COPY */
      d_stringa    VARCHAR2 (32000);
      d_servizi    VARCHAR2 (32000);
      d_pagina     VARCHAR2 (32000);
      d_serv_par   VARCHAR2 (32000);
      d_expand     VARCHAR2 (200)
               := '<img src="../common/images/AD4/NodoExpand.gif" BORDER=0 >';
      d_collapse   VARCHAR2 (200)
             := '<img src="../common/images/AD4/NodoCollapse.gif" BORDER=0 >';
      d_bitmap     VARCHAR2 (200);
-- gestione tramite clob
      d_amount     BINARY_INTEGER   := 32767;
      d_clob       CLOB             := EMPTY_CLOB ();
      d_clob_dep   CLOB             := EMPTY_CLOB ();
   BEGIN
--   dbms_lob.freetemporary(d_clob);
      dbms_lob.createTemporary (d_clob, TRUE, dbms_lob.SESSION);
      d_servizi := '||' || p_servizi || '||';
      d_stringa := '<dl style="margin-top:0;margin-bottom:0;margin-left:5">';
--   dbms_lob.freetemporary(d_clob_dep);
      dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
      d_amount := LENGTH (d_stringa);
      dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
      dbms_lob.APPEND (d_clob, d_clob_dep);
--   dbms_lob.freetemporary(d_clob_dep);
      d_pagina :=
            'AD4Ambiente.do?PROGETTO='
         || p_progetti
         || '&MODULO='
         || p_moduli
         || '&ISTANZA='
         || p_istanze
         || '&SERVIZIO=';
      FOR SERVIZI IN (SELECT DISTINCT serv.id_servizio, serv.Istanza
                                    , serv.modulo
                                    ,    'Istanza: '
                                      || ista.descrizione
                                      || ' - Modulo: '
                                      || modu.descrizione descrizione
                                 FROM SERVIZI serv, ISTANZE ista, MODULI modu
                                WHERE ista.Istanza = serv.Istanza
                                  AND modu.modulo = serv.modulo
                                  AND EXISTS (
                                         SELECT 1
                                           FROM ISTANZE
                                          WHERE Istanza = serv.Istanza
                                            AND progetto = p_progetto)
                                  AND EXISTS (
                                         SELECT 1
                                           FROM MODULI
                                          WHERE modulo = serv.modulo
                                            AND progetto = p_progetto)
                             ORDER BY 1)
      LOOP
         d_serv_par := '';
         d_servizi := '||' || p_servizi || '||';
         IF INSTR (d_servizi, '||' || SERVIZI.id_servizio || '||') > 0
         THEN
            d_bitmap := d_collapse;
            d_servizi :=
               REPLACE (d_servizi, '||' || SERVIZI.id_servizio || '||', '||');
            IF d_servizi <> '||||'
            THEN
               d_serv_par :=
                   d_serv_par || SUBSTR (d_servizi, 3, LENGTH (d_servizi) - 4);
            END IF;
         ELSE
            d_bitmap := d_expand;
            IF d_servizi <> '||||'
            THEN
               d_serv_par := d_serv_par || SUBSTR (d_servizi, 3);
            END IF;
            d_serv_par := d_serv_par || SERVIZI.id_servizio;
         END IF;
         d_stringa :=
               '<tr><td><dd><a CLASS="AFCDataLink" href="'
            || d_pagina
            || d_serv_par
            || '">'
            || d_bitmap
            || '<img src="../common/images/AD4/Serv.gif" BORDER=0></a>'
            || ' Servizio '
            || servizi.id_servizio
            || ': <a CLASS="AFCDataLink" href="AD4Servizio.do?MVID=1&ID_SERVIZIO='
            || servizi.id_servizio
            || '&PROGETTO='
            || p_progetto
            || '">'
            || servizi.descrizione
            || '</a></dd></td></tr>';
         d_stringa := replace (d_stringa, '|','%7C'); -- rev. 9
--      dbms_lob.freetemporary(d_clob_dep);
         dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
         d_amount := LENGTH (d_stringa);
         dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
         dbms_lob.APPEND (d_clob, d_clob_dep);
--      dbms_lob.freetemporary(d_clob_dep);
         d_servizi := '||' || p_servizi || '||';
         IF INSTR (d_servizi, '||' || SERVIZI.id_servizio || '||') > 0
         THEN
--         dbms_lob.freetemporary(d_clob_dep);
            dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
            d_clob_dep :=
               GET_UTENTI_SERVIZIO (SERVIZI.Istanza
                                  , SERVIZI.modulo
                                  , p_progetti
                                  , p_istanze
                                  , p_moduli
                                  , p_servizi
                                   );
            dbms_lob.APPEND (d_clob, d_clob_dep);
--         dbms_lob.freetemporary(d_clob_dep);
         END IF;
      END LOOP;
      d_stringa := '</dl>';
      d_amount := LENGTH (d_stringa);
--   dbms_lob.freetemporary(d_clob_dep);
      dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
      dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
      dbms_lob.APPEND (d_clob, d_clob_dep);
--   dbms_lob.freetemporary(d_clob_dep);
      RETURN d_clob;
   END GET_SERVIZI_PROGETTO;
   FUNCTION GET_PAR_AMBIENTE
/******************************************************************************
 NOME:        GET_PAR_AMBIENTE
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2.
 NOTE:
******************************************************************************/
   (p_url_par IN VARCHAR2, p_scelta IN VARCHAR2)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_url_par   VARCHAR2 (2000);
      d_return    VARCHAR2 (2000);
   BEGIN
      d_return := '';
      d_url_par := '||' || p_url_par || '||';
      IF INSTR (d_url_par, '||' || p_scelta || '||') > 0
      THEN
         d_url_par := REPLACE (d_url_par, '||' || p_scelta || '||', '||');
         IF d_url_par <> '||||'
         THEN
            d_return :=
                     d_return || SUBSTR (d_url_par, 3, LENGTH (d_url_par) - 4);
         END IF;
      ELSE
         IF d_url_par <> '||||'
         THEN
            d_return := d_return || SUBSTR (d_url_par, 3);
         END IF;
         d_return := d_return || p_scelta;
      END IF;
      RETURN d_return;
   END GET_PAR_AMBIENTE;
   FUNCTION GET_SITUAZIONE_AMBIENTE
/******************************************************************************
 NOME:        GET_SITUAZIONE_AMBIENTE
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2.
 NOTE:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 9    14/04/2021 SN    Errore http 400 se nel url c'è [ o | come parametro Bug #49624
******************************************************************************/
   (
      p_progetti   IN   VARCHAR2
    , p_istanze    IN   VARCHAR2
    , p_moduli     IN   VARCHAR2
    , p_servizi    IN   VARCHAR2
   )
      RETURN CLOB
   IS /* SLAVE_COPY */
      d_progetti   VARCHAR2 (32000);
      d_prog_par   VARCHAR2 (32000);
      d_stringa    VARCHAR2 (32000);
      d_pagina     VARCHAR2 (32000);
      d_expand     VARCHAR2 (200)
               := '<img src="../common/images/AD4/NodoExpand.gif" BORDER=0 >';
      d_collapse   VARCHAR2 (200)
             := '<img src="../common/images/AD4/NodoCollapse.gif" BORDER=0 >';
      d_bitmap     VARCHAR2 (200);
-- gestione tramite clob
      d_amount     BINARY_INTEGER   := 32767;
      d_clob       CLOB             := EMPTY_CLOB ();
      d_clob_dep   CLOB             := EMPTY_CLOB ();
   BEGIN
      d_pagina := 'AD4Ambiente.do?PROGETTO=';
      d_stringa := '<table width="100%">';
--   dbms_lob.freetemporary(d_clob);
      dbms_lob.createTemporary (d_clob, TRUE, dbms_lob.SESSION);
      FOR PROGETTI IN (SELECT   progetto, descrizione
                           FROM PROGETTI
                       ORDER BY 1)
      LOOP
         d_prog_par := '';
         d_progetti := '||' || p_progetti || '||';
         IF INSTR (d_progetti, '||' || PROGETTI.progetto || '||') > 0
         THEN
            d_bitmap := d_collapse;
         ELSE
            d_bitmap := d_expand;
         END IF;
         d_prog_par := GET_PAR_AMBIENTE (d_progetti, PROGETTI.progetto);
         d_stringa :=
               '<tr><td><a CLASS="AFCDataLink" href="'
            || d_pagina
            || d_prog_par
            || '">'
            || d_bitmap
            || '<img src="../common/images/AD4/Prog.gif" BORDER=0 ></a>'
            || ' Progetto: <a CLASS="AFCDataLink" href="AD4Progetto.do?PROGETTO='
            || progetti.progetto
            || '">'
            || progetti.progetto
            || ' - '
            || progetti.descrizione
            || '</a></td></tr>';
         d_stringa := REPLACE (d_stringa, '||||', '||');
         d_stringa := replace (d_stringa, '|','%7C'); -- rev. 9
         d_amount := LENGTH (d_stringa);
--      dbms_lob.freetemporary(d_clob_dep);
         dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
         dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
         dbms_lob.APPEND (d_clob, d_clob_dep);
--      dbms_lob.freetemporary(d_clob_dep);
         d_progetti := '||' || p_progetti || '||';
         IF INSTR (d_progetti, '||' || PROGETTI.progetto || '||') > 0
         THEN
--   dbms_lob.freetemporary(d_clob_dep);
            dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
            d_clob_dep :=
               GET_SERVIZI_PROGETTO (PROGETTI.progetto
                                   , p_progetti
                                   , p_istanze
                                   , p_moduli
                                   , p_servizi
                                    );
         d_stringa := replace (d_clob_dep, '|','%7C'); -- rev. 9
            dbms_lob.APPEND (d_clob, d_clob_dep);
--         dbms_lob.freetemporary(d_clob_dep);
            dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
            d_clob_dep :=
               GET_ISTANZE_PROGETTO (PROGETTI.progetto
                                   , p_progetti
                                   , p_istanze
                                   , p_moduli
                                   , p_servizi
                                    );
         d_stringa := replace (d_clob_dep, '|','%7C'); -- rev. 9
            dbms_lob.APPEND (d_clob, d_clob_dep);
--         dbms_lob.freetemporary(d_clob_dep);
            dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
            d_clob_dep :=
               GET_MODULI_PROGETTO (PROGETTI.progetto
                                  , p_progetti
                                  , p_istanze
                                  , p_moduli
                                  , p_servizi
                                   );
         d_stringa := replace (d_clob_dep, '|','%7C'); -- rev. 9
            dbms_lob.APPEND (d_clob, d_clob_dep);
--         dbms_lob.freetemporary(d_clob_dep);
         END IF;
      END LOOP;
      d_stringa := '</table>';
      d_amount := LENGTH (d_stringa);
--   dbms_lob.freetemporary(d_clob_dep);
      dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
      dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
      dbms_lob.APPEND (d_clob, d_clob_dep);
--   dbms_lob.freetemporary(d_clob_dep);
      RETURN d_clob;
   END GET_SITUAZIONE_AMBIENTE;
   FUNCTION UTENTI_PM
/******************************************************************************
 NOME:        UTENTI_PM
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati dell'utente.
 NOTE:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 4   21/08/2018  SN Aggiunti parametri x aggiunta campi tabella utente
******************************************************************************/
   (
      p_id_utente          IN   NUMBER
    , p_utente             IN   VARCHAR2
    , p_nominativo         IN   VARCHAR2
    , p_password           IN   VARCHAR2
    , p_pwd_modified       IN   VARCHAR2
    , p_rinnovo_password   IN   VARCHAR2
    , p_stato              IN   VARCHAR2
    , p_lingua             IN   VARCHAR2
    , p_gruppo_lavoro      IN   VARCHAR2
    , p_importanza         IN   VARCHAR2
    , p_note               IN   VARCHAR2
    , p_utente_agg         IN   VARCHAR2
    , p_soggetto           IN   NUMBER
   -- rev 4 .inizio
   , p_amministratore in utenti.amministratore%TYPE
   , p_info_identificazione in utenti.info_identificazione%TYPE
   -- rev 4.fine
   )
      RETURN VARCHAR2
   IS
      d_utente     utenti.utente%TYPE;
      d_upd_sogg   VARCHAR2 (1) := 'N';
   BEGIN
      d_utente := UPPER (p_utente);
      IF p_id_utente IS NOT NULL
      THEN
         Utente.INITIALIZE (d_utente);
      ELSE
         Utente.INIT;
      END IF;
      Utente.SET_UTENTE (d_utente);
      Utente.SET_NOMINATIVO (p_nominativo);
      IF NVL (p_pwd_modified, 'N') = 'Y'
      THEN
         Utente.SET_PASSWORD (p_password);
      END IF;
      Utente.SET_RINNOVO_PASSWORD (UPPER (p_rinnovo_password));
      Utente.SET_STATO (UPPER (p_stato));
      Utente.SET_LINGUA (UPPER (p_lingua));
      Utente.SET_GRUPPO_LAVORO (p_gruppo_lavoro);
      Utente.SET_IMPORTANZA (p_importanza);
      Utente.SET_NOTE (p_note);
      Utente.SET_UTENTE_AGG (p_utente_agg);
      utente.set_amministratore(p_amministratore);
      utente.set_info_identificazione(p_info_identificazione);
      IF NVL (p_soggetto, 0) > 0
      THEN
         UPDATE UTENTI_SOGGETTI
            SET Soggetto = p_soggetto
          WHERE Utente = d_utente;
         IF SQL%ROWCOUNT = 0
         THEN
            INSERT INTO UTENTI_SOGGETTI
                        (Soggetto, Utente
                        )
                 VALUES (p_soggetto, d_utente
                        );
         END IF;
      ELSIF NVL (p_soggetto, 0) < 0
      THEN
         DELETE      UTENTI_SOGGETTI
               WHERE Utente = d_utente;
      END IF;
--   RAISE_APPLICATION_ERROR(-20999,'ECCO (nom=' || p_nominativo|| ')'|| d_utente || ':'||d_upd_sogg);
      Utente.UPDATE_UTENTE (d_utente, d_upd_sogg);
      RETURN d_utente;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END UTENTI_PM;
   FUNCTION ENTI_PM
/******************************************************************************
 NOME:        ENTI_PM
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati dell'ente.
 NOTE:
******************************************************************************/
   (
      p_ente          IN   VARCHAR2
    , p_ente_old      IN   VARCHAR2
    , p_descrizione   IN   VARCHAR2
    , p_bitmap        IN   VARCHAR2
    , p_disegno       IN   VARCHAR2
    , p_note          IN   VARCHAR2
    , p_soggetto      IN   NUMBER
   )
      RETURN VARCHAR2
   IS
      d_esiste     NUMBER (1);
      d_ente       enti.ente%TYPE;
      d_ente_old   enti.ente%TYPE;
      d_soggetto   NUMBER (8);
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_ente IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_ente'' obbligatorio.'
                                 );
      END IF;
      d_ente := UPPER (p_ente);
      d_ente_old := UPPER (p_ente_old);
      d_soggetto := p_soggetto;
      IF d_soggetto <= 0
      THEN
         d_soggetto := TO_NUMBER (NULL);
      END IF;
      /*-----------------------------------------------------
                    Controllo esistenza ente.
      -----------------------------------------------------*/
      BEGIN
         SELECT 1
           INTO d_esiste
           FROM ENTI
          WHERE ente = d_ente_old
         UNION
         SELECT 1
           FROM dual
          WHERE d_ente_old IS NULL;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR
               (-20999
              , 'Ente originale non piu'' esistente. Impossibile aggiornare l''ente.'
              , TRUE
               );
         WHEN OTHERS
         THEN
            RAISE;
      END;
/*-----------------------------------------------------
           INSERIMENTO ente
-----------------------------------------------------*/
      IF d_ente_old IS NULL
      THEN
         BEGIN
            INSERT INTO ENTI
                        (ente, descrizione, BITMAP, note, disegno
                       , Soggetto
                        )
                 VALUES (d_ente, p_descrizione, p_bitmap, p_note, p_disegno
                       , d_soggetto
                        );
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE_APPLICATION_ERROR (-20999, Si4.get_error (SQLERRM)
                                      , TRUE);
         END;
      ELSE
/*-----------------------------------------------------
         AGGIORNAMENTO ente
-----------------------------------------------------*/
         BEGIN
            UPDATE ENTI
               SET ente = d_ente
                 , descrizione = p_descrizione
                 , BITMAP = p_bitmap
                 , note = p_note
                 , disegno = p_disegno
                 , Soggetto = d_soggetto
             WHERE ente = d_ente_old;
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
      RETURN d_ente;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END ENTI_PM;
   FUNCTION GRUPPO_PM
/******************************************************************************
 NOME:        GRUPPO_PM
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati dell'utente.
 NOTE:
******************************************************************************/
   (
      p_utente       IN   VARCHAR2
    , p_utente_old   IN   VARCHAR2
    , p_nominativo   IN   VARCHAR2
    , p_note         IN   VARCHAR2
    , p_utente_agg   IN   VARCHAR2
   )
      RETURN VARCHAR2
   IS
      d_gruppo       utenti.utente%TYPE;
      d_gruppo_old   utenti.utente%TYPE;
      d_esiste       NUMBER (1);
      d_id           NUMBER;
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_utente IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_utente'' obbligatorio.'
                                 );
      END IF;
      d_gruppo := UPPER (p_utente);
      d_gruppo_old := UPPER (p_utente_old);
      /*-----------------------------------------------------
                    Controllo esistenza gruppo.
      -----------------------------------------------------*/
      BEGIN
         SELECT 1
           INTO d_esiste
           FROM UTENTI
          WHERE Utente = d_gruppo_old
         UNION
         SELECT 1
           FROM dual
          WHERE d_gruppo_old IS NULL;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR
               (-20999
              , 'Gruppo originale non piu'' esistente. Impossibile aggiornare il gruppo.'
              , TRUE
               );
         WHEN OTHERS
         THEN
            RAISE;
      END;
/*-----------------------------------------------------
           INSERIMENTO gruppo
-----------------------------------------------------*/
      IF d_gruppo_old IS NULL
      THEN
         SELECT NVL (MAX (id_utente), 0) + 1
           INTO d_id
           FROM UTENTI;
         INSERT INTO UTENTI
                     (ID_UTENTE, Utente, NOMINATIVO, NOTE, TIPO_UTENTE
                    , UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO
                     )
              VALUES (d_id, d_gruppo, UPPER (p_nominativo), p_note, 'G'
                    , p_utente_agg, SYSDATE
                     );
      ELSE
/*-----------------------------------------------------
           AGGIORNAMENTO gruppo
-----------------------------------------------------*/
         UPDATE UTENTI
            SET Utente = d_gruppo
              , NOMINATIVO = UPPER (p_nominativo)
              , NOTE = p_note
              , UTENTE_AGGIORNAMENTO = p_utente_agg
              , DATA_AGGIORNAMENTO = SYSDATE
          WHERE Utente = d_gruppo_old;
      END IF;
      RETURN d_gruppo;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END GRUPPO_PM;
   FUNCTION GET_DATI_DIAC
/******************************************************************************
 NOME:        GET_DATI_DIAC
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati del diritto di accesso.
 NOTE:
******************************************************************************/
   (
      p_utente    IN   VARCHAR2
    , p_modulo    IN   VARCHAR2
    , p_istanza   IN   VARCHAR2
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_stringa   VARCHAR2 (4000);
   BEGIN
      SELECT    'L''utente accede con ruolo <strong>'
             || ruol.descrizione
             || '</strong>.'
             || DECODE (numero_accessi
                      , NULL, TO_CHAR (NULL)
                      ,    '<br>Ha fatto accesso al servizio '
                        || numero_accessi
                        || ' volte; l''ultima in data '
                        || TO_CHAR (ultimo_accesso, 'dd/mm/yyyy')
                        || '.'
                       )
             || DECODE
                   (Gruppo
                  , NULL, TO_CHAR (NULL)
                  ,    '<br>Il Diritto di Accesso deriva dall''<strong>appartenenza</strong> dell''utente al <strong>gruppo '
                    || uten.nominativo
                    || '</strong>.'
                   )
             || DECODE (DIAC.note
                      , NULL, TO_CHAR (NULL)
                      , '<br>Note: ' || DIAC.note
                       )
        INTO d_stringa
        FROM DIRITTI_ACCESSO DIAC, RUOLI ruol, UTENTI uten
       WHERE DIAC.Istanza = p_istanza
         AND DIAC.modulo = p_modulo
         AND DIAC.Utente = p_utente
         AND ruol.ruolo = DIAC.ruolo
         AND DIAC.Gruppo = uten.Utente(+);
      RETURN d_stringa;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_DATI_DIAC;
   FUNCTION DIRITTI_ACCESSO_PM
/******************************************************************************
 NOME:        DIRITTI_ACCESSO_PM
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati dell'utente.
 NOTE:
******************************************************************************/
   (
      p_sequenza      IN   NUMBER
    , p_utente        IN   VARCHAR2
    , p_modulo        IN   VARCHAR2
    , p_modulo_old    IN   VARCHAR2
    , p_istanza       IN   VARCHAR2
    , p_istanza_old   IN   VARCHAR2
    , p_ruolo         IN   VARCHAR2
    , p_note          IN   VARCHAR2
   )
      RETURN NUMBER
   IS
      d_esiste        NUMBER (1);
      d_modu_des      moduli.descrizione%TYPE;
      d_ista_des      istanze.descrizione%TYPE;
      d_ruol_des      ruoli.descrizione%TYPE;
      d_progetto      VARCHAR2 (10);
      d_prog_des      progetti.descrizione%TYPE;
      d_sequenza      NUMBER;
      d_ruolo_old     ruoli.ruolo%TYPE;
      d_gruppo_old    ruoli.ruolo%TYPE;
      d_diac_gruppo   VARCHAR2 (1);
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_utente IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_utente'' obbligatorio.'
                                 );
      END IF;
      IF p_modulo IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_modulo'' obbligatorio.'
                                 );
      END IF;
      IF p_istanza IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_istanza'' obbligatorio.'
                                 );
      END IF;
      IF p_ruolo IS NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Parametro ''p_ruolo'' obbligatorio.'
                                 );
      END IF;
      /*-----------------------------------------------------
                    Controllo esistenza utente.
      -----------------------------------------------------*/
      BEGIN
         SELECT 1
           INTO d_esiste
           FROM UTENTI
          WHERE Utente = p_utente;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR (-20999
                                   ,    'Utente ('''
                                     || p_utente
                                     || ''') non definito.'
                                   , TRUE
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
                    Controllo esistenza modulo.
      -----------------------------------------------------*/
      BEGIN
         SELECT descrizione
           INTO d_modu_des
           FROM MODULI
          WHERE modulo = p_modulo;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR (-20999
                                   ,    'Modulo ('''
                                     || p_modulo
                                     || ''') non definito.'
                                   , TRUE
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
                    Controllo esistenza istanza.
      -----------------------------------------------------*/
      BEGIN
         SELECT descrizione
           INTO d_ista_des
           FROM ISTANZE
          WHERE Istanza = p_istanza;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR (-20999
                                   ,    'Istanza ('''
                                     || p_istanza
                                     || ''') non definita.'
                                   , TRUE
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
                    Controllo esistenza ruolo.
      -----------------------------------------------------*/
      BEGIN
         SELECT descrizione
           INTO d_ruol_des
           FROM RUOLI
          WHERE ruolo = p_ruolo;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR (-20999
                                   ,    'Ruolo ('''
                                     || p_ruolo
                                     || ''') non definito.'
                                   , TRUE
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
          Controllo compatibilita' tra modulo ed istanza.
      -----------------------------------------------------*/
      BEGIN
         SELECT ista.progetto, prog.descrizione
           INTO d_progetto, d_prog_des
           FROM ISTANZE ista, PROGETTI prog
          WHERE ista.Istanza = p_istanza
            AND prog.progetto = ista.progetto
            AND EXISTS (SELECT 1
                          FROM MODULI
                         WHERE progetto = ista.progetto AND modulo = p_modulo);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR (-20999
                                   ,    'Istanza "'
                                     || d_ista_des
                                     || '" ('''
                                     || p_istanza
                                     || ''') e Modulo "'
                                     || d_modu_des
                                     || '" ('''
                                     || p_modulo
                                     || ''') incompatibili.'
                                   , TRUE
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*----------------------------------------------------------
         Controllo compatibilita' tra ruolo, progetto e modulo.
      ----------------------------------------------------------*/
      BEGIN
         SELECT 1
           INTO d_esiste
           FROM RUOLI
          WHERE d_progetto LIKE NVL (progetto, '%')
            AND p_modulo LIKE NVL (modulo, '%')
            AND ruolo = p_ruolo;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR (-20999
                                   ,    'Ruolo "'
                                     || d_ruol_des
                                     || '" ('''
                                     || p_ruolo
                                     || '''), Progetto "'
                                     || d_prog_des
                                     || '" ('''
                                     || d_progetto
                                     || ''') e Modulo "'
                                     || d_modu_des
                                     || '" ('''
                                     || p_modulo
                                     || ''') incompatibili.'
                                   , TRUE
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
             Calcolo della sequenza, se non specificata.
      -----------------------------------------------------*/
      d_sequenza := p_sequenza;
      IF d_sequenza IS NULL
      THEN
         BEGIN
            SELECT NVL (MAX (sequenza), 0) + 1
              INTO d_sequenza
              FROM DIRITTI_ACCESSO
             WHERE Utente = p_utente;
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
      /*-----------------------------------------------------
                 INSERIMENTO DIRITTO di ACCESSO
      -----------------------------------------------------*/
      IF p_modulo_old IS NULL AND p_istanza_old IS NULL
      THEN
         BEGIN
            INSERT INTO DIRITTI_ACCESSO
                        (Utente, modulo, Istanza, ruolo, sequenza
                       , ultimo_accesso, numero_accessi, Gruppo
                       , note
                        )
                 VALUES (p_utente, p_modulo, p_istanza, p_ruolo, d_sequenza
                       , TO_DATE (NULL), TO_NUMBER (NULL), TO_CHAR (NULL)
                       , p_note
                        );
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      ELSE
         /*-----------------------------------------------------
                  AGGIORNAMENTO DIRITTO di ACCESSO
         -----------------------------------------------------*/
         BEGIN
            SELECT Gruppo, ruolo
              INTO d_gruppo_old, d_ruolo_old
              FROM DIRITTI_ACCESSO
             WHERE Utente = p_utente
               AND modulo = p_modulo_old
               AND Istanza = p_istanza_old;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               RAISE_APPLICATION_ERROR
                  (-20999
                 , 'Diritto di Accesso originale non piu'' esistente. Impossibile aggiornare il diritto di accesso.'
                 , TRUE
                  );
            WHEN OTHERS
            THEN
               RAISE;
         END;
         /*-----------------------------------------------------
          Verifica se il DIRITTO di ACCESSO deriva da un gruppo
         -----------------------------------------------------*/
         d_diac_gruppo := 'N';
         IF d_gruppo_old IS NOT NULL
         THEN
            IF     p_modulo = p_modulo_old
               AND p_istanza = p_istanza_old
               AND p_ruolo = d_ruolo_old
            THEN
               d_diac_gruppo := 'Y';
            END IF;
         END IF;
         /*-----------------------------------------------------
                  AGGIORNAMENTO DIRITTO di ACCESSO
         -----------------------------------------------------*/
         BEGIN
            UPDATE DIRITTI_ACCESSO
               SET Utente = p_utente
                 , modulo = p_modulo
                 , Istanza = p_istanza
                 , ruolo = p_ruolo
                 , sequenza = d_sequenza
                 , Gruppo = DECODE (d_diac_gruppo, 'Y', Gruppo, '')
                 , note = p_note
             WHERE Utente = p_utente
               AND modulo = p_modulo_old
               AND Istanza = p_istanza_old;
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
      RETURN d_sequenza;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END DIRITTI_ACCESSO_PM;
   FUNCTION GET_DATI_RICHIESTA
/******************************************************************************
 NOME:        GET_DATI_RICHIESTA
 DESCRIZIONE: .
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente i dati della richiesta.
 NOTE:
******************************************************************************/
   (p_id_richiesta IN NUMBER)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_stringa   VARCHAR2 (4000);
   BEGIN
      SELECT    'Richiesta <strong>'
             || DECODE (RIAB.stato
                      , 'C', 'da prendere in carico'
                      , 'A', 'approvata'
                      , 'F', 'fallita'
                      , 'R', 'respinta'
                      , 'in stato non definito'
                       )
             || '</strong>.<br>'
             || '<strong>Notifica</strong> '
             || DECODE (RIAB.indirizzo_notifica
                      , NULL, ''
                      , 'a ' || LOWER (RIAB.indirizzo_notifica || ' ')
                       )
             || 'via '
             || NVL (LOWER (RIAB.tipo_notifica)
                   , '<em>tipo notifica non definito</em>'
                    )
             || '<strong>'
             || DECODE (RIAB.notificata
                      , 'S', ' avvenuta correttamente'
                      , 'N', ' non avvenuta'
                      , 'F', ' fallita'
                       )
             || '</strong>.'
             || DECODE (RIAB.note_notifica
                      , NULL, ''
                      , '<br>Precisazione messaggio: ' || RIAB.note_notifica
                       )
             || DECODE (RIAB.note, NULL, '', '<br>Note: ' || RIAB.note)
        INTO d_stringa
        FROM RICHIESTE_ABILITAZIONE RIAB
       WHERE RIAB.id_richiesta = p_id_richiesta;
      RETURN d_stringa;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_DATI_RICHIESTA;
   PROCEDURE UTGR_INSERT (p_utente IN VARCHAR2, p_gruppo IN VARCHAR2, p_utente_mod in varchar2 default null)
   IS
      d_is_UO   NUMBER;
   BEGIN
      if  p_utente_mod is not null then
        SI4.SET_ACCESSO_UTENTE(p_utente_mod, null, 'AD4WEB', null, null, null, null,null, null, 'AD4', null, null);
      end if;
      IF p_gruppo = 'ric_abil'
      THEN
         RAISE_APPLICATION_ERROR
                    (-20999
                   , 'Gruppo ''Richieste di abilitazione'' non modificabile.'
                    );
      END IF;
-- Controlla che il gruppo in cui si va ad inserire non sia un' UO
-- (tipo_utente = 'O')
      SELECT DECODE (tipo_utente, 'O', 1, 0)
        INTO d_is_UO
        FROM UTENTI
       WHERE Utente = p_gruppo;
      IF d_is_UO = 1
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Struttura Organizzativa non modificabile.'
                                 );
      ELSE
         IF p_utente <> '%'
         THEN
            SELECT DECODE (tipo_utente, 'O', 1, 0)
              INTO d_is_UO
              FROM UTENTI
             WHERE Utente = p_utente;
            IF d_is_UO = 1
            THEN
               RAISE_APPLICATION_ERROR
                                 (-20999
                                , 'Struttura Organizzativa non modificabile.'
                                 );
            ELSE
               INSERT INTO UTENTI_GRUPPO
                           (Utente, Gruppo
                           )
                    VALUES (p_utente, p_gruppo
                           );
            END IF;
         ELSE
            /*---------------------------------------------------------------------------------
               inserisce tutti gli UTENTI / gruppi, dopo aver controllato che:
               - non siano UO (tipo_utente = 'O');
               - non siano gia' presenti nel gruppo;
               - non appartengano ad altri gruppi (IN quanto ereditano l'appartenenza al gruppo
                 dal padre);
               per i gruppi controlla, inoltre, che:
               - il gruppo non venga inserito in se stesso;
               - il codice Gruppo non SIA 'ric_abil';
               - SIA evitata la ricorsione (il Gruppo che si va ad inserire, non contenga gia'
               - il Gruppo IN cui viene inserito).
            ---------------------------------------------------------------------------------*/
            INSERT INTO UTENTI_GRUPPO
                        (Utente, Gruppo)
               SELECT DISTINCT Utente, p_gruppo
                          FROM UTENTI
                         WHERE Utente <>
                                  p_gruppo
                                      -- non inserisce il gruppo in se stesso
                           AND tipo_utente <> 'O'          -- non inserisce UO
                           AND Utente NOT IN (
                                  SELECT Utente
                                            -- utente gia' presente nel gruppo
                                    FROM UTENTI_GRUPPO
                                   WHERE Gruppo = p_gruppo)
                           AND NOT EXISTS (
                                  SELECT 1
                                    FROM UTENTI_GRUPPO
                                             -- non appartiene ad altri gruppi
                                   WHERE Utente = UTENTI.Utente)
                           AND (   tipo_utente = 'U'
                                OR (    tipo_utente <> 'U'
                                    AND Utente NOT IN (
                                           SELECT     Gruppo
                                                -- evita ricorsione tra gruppi
                                                 FROM UTENTI_GRUPPO
                                           CONNECT BY PRIOR Gruppo = Utente
                                           START WITH Utente = p_gruppo)
                                    AND Utente <>
                                           'ric_abil'
                                           -- gruppo 'ric_abil' non inseribile
                                   )
                               );
         END IF;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END UTGR_INSERT;
   PROCEDURE UTGR_DELETE (p_utente IN VARCHAR2, p_gruppo IN VARCHAR2, p_utente_mod in varchar2 default null)
   IS
      d_is_UO   NUMBER;
   BEGIN
      if  p_utente_mod is not null then
        SI4.SET_ACCESSO_UTENTE(p_utente_mod, null, 'AD4WEB', null, null, null, null,null, null, 'AD4', null, null);
      end if;
      IF p_gruppo = 'ric_abil'
      THEN
         RAISE_APPLICATION_ERROR
                    (-20999
                   , 'Gruppo ''Richieste di abilitazione'' non modificabile.'
                    );
      END IF;
-- Controlla che il gruppo da cui si va ad eliminare non sia un' UO
-- (tipo_utente = 'O')
      SELECT DECODE (tipo_utente, 'O', 1, 0)
        INTO d_is_UO
        FROM UTENTI
       WHERE Utente = p_gruppo;
      IF d_is_UO = 1
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                , 'Struttura Organizzativa non modificabile.'
                                 );
      ELSE
         DELETE      UTENTI_GRUPPO
               WHERE Utente LIKE p_utente AND Gruppo = p_gruppo;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END UTGR_DELETE;
FUNCTION CHECK_DIAC_CAAC_UTEN_GRUPPO
( p_utente IN VARCHAR2
, p_gruppo IN VARCHAR2)
RETURN CLOB
IS /* SLAVE_COPY */
   iloop       INTEGER := 0;
   d_return    VARCHAR2(32000);
   d_bitmap    VARCHAR2(2000);
   d_title_div VARCHAR2(2000);
   d_pers      BOOLEAN := FALSE;
   d_check     INTEGER;
-- gestione tramite clob
   d_amount          BINARY_INTEGER := 32767;
   d_clob            CLOB := EMPTY_CLOB() ;
   d_clob_dep        CLOB := EMPTY_CLOB() ;
BEGIN
   dbms_lob.createTemporary(d_clob,TRUE,dbms_lob.SESSION);
   d_return := '<table width="100%">';
   d_amount := LENGTH(d_return);
   dbms_lob.WRITE(d_clob, d_amount, 1, d_return);
   FOR diac_gruppo IN (SELECT DIAC.Istanza, ista.descrizione ista_desc
                            , DIAC.modulo, modu.descrizione modu_desc
                            , DIAC.ruolo
                         FROM DIRITTI_ACCESSO DIAC, ISTANZE ista, MODULI modu
                        WHERE DIAC.Utente  = p_gruppo
                          AND ista.Istanza = DIAC.Istanza
                          AND modu.modulo  = DIAC.modulo
                        ORDER BY sequenza)
   LOOP
      dbms_lob.createTemporary(d_clob_dep,TRUE,dbms_lob.SESSION);
      d_pers := FALSE;
      iloop  := iloop + 1;
      BEGIN
        d_check := Diritto_Accesso.check_gruppo(p_gruppo, p_utente, diac_gruppo.Istanza, diac_gruppo.modulo);
        IF d_check = 2 THEN
         -- diritto esiste in gruppo e in utente ma con ruolo diverso
            d_title_div := 'Accesso ereditato dal gruppo e personalizzato.';
            d_bitmap    := '<img src="../common/images/AD4/modifica.gif" BORDER=0 title="'||d_title_div||'">';
            d_pers      := TRUE;
         ELSIF d_check = 1 THEN
         -- diritto esiste in gruppo e in utente
            d_title_div := 'Accesso ereditato dal gruppo.';
            d_bitmap := '<img src="../common/images/AD4/ok.gif" BORDER=0 title="'||d_title_div||'">';
         ELSIF d_check = 0 THEN
         -- diritto esiste in gruppo ma non in utente
            d_title_div := 'Accesso eliminato.';
            d_bitmap := '<img src="../common/images/AD4/elimina.gif" BORDER=0 align="right" title="'||d_title_div||'">';
         END IF;
      END;
      d_return := '<tr><td title="'||d_title_div||'">'||iLoop||'. <strong>Istanza:</strong> '||diac_gruppo.ista_desc||' - <strong>Modulo:</strong> '||diac_gruppo.modu_desc||'</td><td align="right">'||d_bitmap||'</td></tr>'||CHR(10)||CHR(13);
      d_amount := LENGTH(d_return);
      dbms_lob.WRITE(d_clob_dep, d_amount, 1, d_return);
      dbms_lob.APPEND(d_clob,d_clob_dep);
   END LOOP;
   dbms_lob.createTemporary(d_clob_dep,TRUE,dbms_lob.SESSION);
   d_return := '</table>';
   d_amount := LENGTH(d_return);
   dbms_lob.WRITE(d_clob_dep, d_amount, 1, d_return);
   dbms_lob.APPEND(d_clob,d_clob_dep);
   RETURN d_clob;
END CHECK_DIAC_CAAC_UTEN_GRUPPO;
   PROCEDURE Soggetti_Pm
/******************************************************************************
 NOME:        SOGGETTI_PM.
 DESCRIZIONE: Aggiornamento dei dati anagrafici di un soggetto esistente.
 ARGOMENTI:   p_soggetto:          codice del soggetto.
              p_comune:          comune di residenza del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 0.
              p_provincia:       provincia di residenza del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 0.
              p_cap:             cap di residenza del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_indirizzo:       indirizzo di residenza del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_indirizzo_web:   indirizzo e-mail del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_telefono:        numero di telefono del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_fax:             numero di fax del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
           p_utente_in_uso:   se Y mette l'utente 'In Uso',
                              altrimenti lo lascia nello stato in cui e'.
 ECCEZIONI:   -20999: Parametro 'p_utente' obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 8    09/06/2020 SN    Aggiornando un soggetto sistemare utente modifica, competenza e competenza_esclusiva Bug #40685
******************************************************************************/
   (
      p_soggetto        IN OUT   NUMBER
    , p_cognome         IN       VARCHAR2
    , p_nome            IN       VARCHAR2
    , p_sesso           IN       VARCHAR2
    , p_data_nas        IN       VARCHAR2
    , p_comune_nas      IN       NUMBER
    , p_provincia_nas   IN       NUMBER
    , p_cod_fis         IN       VARCHAR2
    , p_comune          IN       NUMBER
    , p_provincia       IN       NUMBER
    , p_cap             IN       VARCHAR2
    , p_indirizzo       IN       VARCHAR2
    , p_indirizzo_web   IN       VARCHAR2
    , p_telefono        IN       VARCHAR2
    , p_fax             IN       VARCHAR2
    , p_note            IN       VARCHAR2
    , p_utente_agg      IN       VARCHAR2
   )
   IS
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF NVL (p_sesso, 'M') NOT IN ('F', 'M')
      THEN
         RAISE_APPLICATION_ERROR (-20999
                                ,    'Valore '
                                  || p_sesso
                                  || ' non ammesso per parametro ''p_sesso''.'
                                 );
      END IF;
      /*-----------------------------------------------------
          Inizializzazione attributi del soggetto.
      -----------------------------------------------------*/
      Soggetto.INITIALIZE (P_soggetto);
      /*-----------------------------------------------------
        Associa al soggetto associato all'utente passato gli
      attributi significativi.
      -----------------------------------------------------*/
      IF NVL (RTRIM (p_cognome), ' ') <> 'NO'
      THEN
         Soggetto.SET_COGNOME (UPPER (RTRIM (p_cognome)));
      END IF;
      IF NVL (RTRIM (p_nome), ' ') <> 'NO'
      THEN
         Soggetto.SET_NOME (UPPER (RTRIM (p_nome)), 0);
      END IF;
      IF NVL (p_sesso, ' ') <> 'NO'
      THEN
         Soggetto.SET_SESSO (p_sesso);
      END IF;
      IF NVL (p_data_nas, ' ') <> 'NO'
      THEN
         Soggetto.SET_DATA_NASCITA (p_data_nas);
      END IF;
      IF NVL (p_comune_nas, 0) = 0 AND NVL (p_provincia_nas, -1) > 199
      THEN
         Soggetto.SET_COMUNE_NAS (0);
      ELSIF NVL (p_comune_nas, -1) <> 0
      THEN
         Soggetto.SET_COMUNE_NAS (p_comune_nas);
      END IF;
      IF NVL (p_provincia_nas, -1) <> 0
      THEN
         Soggetto.SET_PROVINCIA_NAS (p_provincia_nas);
      END IF;
      IF NVL (p_cod_fis, ' ') <> 'NO'
      THEN
         Soggetto.SET_CODICE_FISCALE (p_cod_fis);
      END IF;
      IF NVL (p_comune, -1) <> 0
      THEN
         Soggetto.SET_COMUNE (p_comune);
      END IF;
      IF NVL (p_provincia, -1) <> 0
      THEN
         Soggetto.SET_PROVINCIA (p_provincia);
      END IF;
      IF NVL (p_indirizzo, ' ') <> 'NO'
      THEN
         Soggetto.SET_INDIRIZZO (p_indirizzo);
      END IF;
      IF NVL (p_cap, ' ') <> 'NO'
      THEN
         Soggetto.SET_CAP (p_cap);
      END IF;
      IF NVL (p_fax, ' ') <> 'NO'
      THEN
         Soggetto.SET_FAX (p_fax);
      END IF;
      IF NVL (p_telefono, ' ') <> 'NO'
      THEN
         Soggetto.SET_TELEFONO (p_telefono);
      END IF;
      IF NVL (p_indirizzo_web, ' ') <> 'NO'
      THEN
         Soggetto.SET_INDIRIZZO_WEB (p_indirizzo_web);
      END IF;
      IF NVL (p_note, ' ') <> 'NO'
      THEN
         Soggetto.SET_NOTE (p_note);
      END IF;
/*-----------------------------------------------------
         Aggiorna il soggetto .
-----------------------------------------------------*/
      IF p_utente_agg IS NOT NULL
      THEN
         Si4.Utente := p_utente_agg;
      END IF;
      -- rev.8 inizio
      soggetto.SET_UTENTE_AGGIORNAMENTO (Si4.Utente);
      soggetto.set_competenza ('AD4');
      soggetto.set_competenza_esclusiva ('');
      -- rev.8 fine
      Soggetto.UPDATE_SOGGETTO (p_soggetto);
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END Soggetti_Pm;
   FUNCTION GET_BLOB_LINK
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
   NOTE:
  Rev. Data       Autore Descrizione
   ---- ---------- ------ ------------------------------------------------------
   0    22/02/2006 MM     Inserimento commento.
  ******************************************************************************/
   (
      p_table      IN   VARCHAR2
    , p_field      IN   VARCHAR2
    , p_where      IN   VARCHAR2
    , p_mimetype   IN   VARCHAR2
    , p_nomefile   IN   VARCHAR2
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return   VARCHAR2 (32000);
   BEGIN
      d_return :=
            '/UploadDownload/uploaddownloadservlet?'
         || 'dataSource='
         || NVL (Amvweb.get_preferenza ('AD4_JDBC'), 'jdbc/ad4')
         || '&table='
         || p_table
         || '&blobfield='
         || p_field
         || '&wherecondition='
         || p_where
         || '&mimetype='
         || p_mimetype;
      d_return :=
            '<a class="AFCDataLink" onclick="popup_center('''
         || d_return
         || ''',''Visualizza File'', 600, 600, ''no'');"><img class="" title="'
         || p_nomefile
         || '" alt="'
         || p_nomefile
         || '" src="../common/images/AMV/xml.gif" border="0"></a>';
      RETURN d_return;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN ' ';
   END GET_BLOB_LINK;
   FUNCTION GET_EVEN_LINK
  /******************************************************************************
   NOME:        GET_EVEN_LINK
   DESCRIZIONE: Restituisce l'html del link alla servlet per aprire il file
                associato all'evento.
   PARAMETRI:   p_id_evento  identificativo dell'evento.
   RITORNA:     stringa varchar2 contenente LINK.
   NOTE:
  Rev. Data       Autore Descrizione
   ---- ---------- ------ ------------------------------------------------------
   0    22/02/2006 MM     Inserimento commento.
  ******************************************************************************/
   (p_id_evento NUMBER)
      RETURN VARCHAR2
   IS
      d_esiste   NUMBER (1);
      d_return   VARCHAR2 (4000);
   BEGIN
      -- CONTROLLA esistenza del record.
      SELECT 1
        INTO d_esiste
        FROM EVENTI
       WHERE id_evento = p_id_evento AND file_locator IS NOT NULL;
      d_return :=
         GET_BLOB_LINK ('EVENTI'
                      , 'FILE_LOCATOR'
                      , 'where ' || 'id_evento%3D' || p_id_evento
                      , Ad4_Evento.GET_LOCATOR_MIMETYPE (p_id_evento)
                      , Ad4_Evento.GET_FILENAME (p_id_evento, 0)
                       );
      RETURN d_return;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN ' ';
   END GET_EVEN_LINK;
   FUNCTION TAB_FOLDER_VERTICAL (
      in_link     IN   VARCHAR2
    , in_href     IN   VARCHAR2
    , in_active   IN   VARCHAR2 DEFAULT 'N'
   )
      RETURN html
   IS /* SLAVE_COPY */
      class_begin   html;
      class_body    html := 'AFCGuida';
      class_end     html;
      class_link    html;
      image         html := '../Themes/AFC/GuidaBlank.gif';
      out_html      html := NULL;
   BEGIN
      class_link := class_body || 'Link';
      IF in_active = 'S'
      THEN
         class_body := class_body || 'Sel';
      END IF;
      class_body := class_body || 'HR';
      class_begin := class_body || 'L';
      class_end := class_body || 'R';
      out_html :=
            out_html
         || '<TABLE cellpadding="0" cellspacing="0" border="0"><tr><td align="right" valign="top" CLASS="'
         || class_begin
         || '"><img src="'
         || image
         || '" ></td><td align="right" valign="center" nowrap CLASS="'
         || class_body
         || '"> <a CLASS="'
         || class_link
         || '" href="'
         || in_href
         || '">'
         || in_link
         || '</a> </td> <td align="right" valign="top" CLASS="'
         || class_end
         || '"><img src="'
         || image
         || '" ></td></tr></TABLE>';
      RETURN out_html;
   END;
   FUNCTION GET_GUIDA_LDAP (p_chiave VARCHAR2)
      RETURN CLOB
   IS /* SLAVE_COPY */
      i            INTEGER                 := 0;
      d_Chiave     REGISTRO.chiave%TYPE;
      d_stringa    REGISTRO.stringa%TYPE;
      d_descr      VARCHAR2 (100);
      d_href       VARCHAR2 (1000);
      d_selected   VARCHAR2 (2)            := 'N';
      d_amount     BINARY_INTEGER          := 32767;
      d_char       VARCHAR2 (32767);
      d_clob       CLOB                    := EMPTY_CLOB ();
   BEGIN
      d_Chiave := 'PRODUCTS/LDAPCONFIG';
      IF NVL (p_chiave, 'PRODUCTS/LDAPCONFIG') = d_Chiave
      THEN
         d_selected := 'S';
      END IF;
      d_href :=
            '../ad4web/AD4LdapConfig.do?MVPD=AD4LDAP&MVL1=AD4LDAP&MVL2=AD4LDAPC&MVVC=AD4LDAPC&chiave='
         || d_chiave;
--   d_char := tab_folder_vertical('Princ.', d_href, d_selected);
      d_char :=
            d_char
         || '<td>'
         || Afc_Html.tab_folder ('Principale', d_href, d_selected);
      -- Ciclo sui server alternativi
      WHILE i <= 9
      LOOP
         BEGIN
            d_Chiave := 'PRODUCTS/LDAPCONFIG/SERVER' || i;
            SELECT d_Chiave chiave
                 , NVL (Registro_Utility.get_stringa (d_Chiave
                                                    , '(Predefinito)'
                                                     )
                      , ' '
                       ) stringa
                 , 'Alternativo ' || i descrizione
                 , DECODE (d_chiave, p_chiave, 'S', 'N') selected
              INTO d_Chiave
                 , d_stringa
                 , d_descr
                 , d_selected
              FROM dual;
            d_href :=
                  '../ad4web/AD4LdapConfig.do?MVPD=AD4LDAP&MVL1=AD4LDAP&MVL2=AD4LDAPC&MVVC=AD4LDAPC&chiave='
               || d_chiave;
--         d_char := d_char||tab_folder_vertical(d_descr, d_href, d_selected);
            d_char :=
                  d_char
               || '<td>'
               || Afc_Html.tab_folder (d_descr, d_href, d_selected);
         EXCEPTION
            WHEN OTHERS
            THEN
               NULL;
         END;
         i := i + 1;
      END LOOP;
--   d_char := '<div>'||d_char||'</div>';
      d_char :=
            '<table border="0" cellspacing="0" cellpadding="0"><tr>'
         || d_char
         || '</tr></table>';
      dbms_lob.createTemporary (d_clob, TRUE, dbms_lob.SESSION);
      d_amount := LENGTH (d_char);
      dbms_lob.writeappend (d_clob, d_amount, d_char);
      RETURN d_clob;
   END GET_GUIDA_LDAP;
   FUNCTION GET_ALBERO_ACCESSI (
      P_ACCESSO_ID       VARCHAR2
    , P_ACCESSO_ID_OLD   VARCHAR2
   )
      RETURN CLOB
   IS /* SLAVE_COPY */
      d_accesso_id       VARCHAR2 (2000)  := NVL (P_ACCESSO_ID, '0');
      d_accesso_id_old   VARCHAR2 (2000)  := NVL (P_ACCESSO_ID_OLD, '0');
      d_stringa          VARCHAR2 (32000);
      d_pagina           VARCHAR2 (32000) := 'AD4AccessiTree.do?ID=';
      d_expand           VARCHAR2 (200)
         := '<img src="../common/images/AMV/cmspnode.gif" BORDER=0 align="AbsMiddle">';
      d_collapse         VARCHAR2 (200)
         := '<img src="../common/images/AMV/cmsmnode.gif" BORDER=0 align="AbsMiddle">';
      d_vert_line        VARCHAR2 (200)
         := '<img src="../common/images/AMV/cmsvertline.gif" BORDER=0 align="AbsMiddle">';
      d_bitmap           VARCHAR2 (200);
      d_immagine         VARCHAR2 (200);
      d_prima_quadra     INTEGER          := 0;
      d_seconda_quadra   INTEGER          := 0;
      d_terza_quadra     INTEGER          := 0;
-- gestione tramite clob
      d_amount           BINARY_INTEGER   := 32767;
      d_clob             CLOB             := EMPTY_CLOB ();
      d_clob_dep         CLOB             := EMPTY_CLOB ();
   BEGIN
      d_prima_quadra := INSTR (d_accesso_id, '[');
      IF d_prima_quadra > 0
      THEN
         d_seconda_quadra := INSTR (d_accesso_id, '[', d_prima_quadra + 1);
      END IF;
      IF d_seconda_quadra > 0
      THEN
         d_terza_quadra := INSTR (d_accesso_id, '[', d_seconda_quadra + 1);
      END IF;
      d_stringa := '<table width="50%"><tr><td valign="top" nowrap>';
      dbms_lob.createTemporary (d_clob, TRUE, dbms_lob.SESSION);
      d_amount := LENGTH (d_stringa);
      dbms_lob.WRITE (d_clob, d_amount, 1, d_stringa);
      IF INSTR (d_accesso_id_old, d_accesso_id) > 0 AND d_terza_quadra = 0
      THEN
         IF d_prima_quadra = 0
         THEN
            d_accesso_id := '0';
         ELSE
            d_accesso_id :=
               SUBSTR (d_accesso_id
                     , 1
                     , GREATEST (d_prima_quadra, d_seconda_quadra) - 1
                      );
         END IF;
      END IF;
      FOR acce IN (SELECT   figlio, padre, descrizione, immagine
                          , immagine_open, nodo, livello, Utente
                       FROM VISTA_ACCESSI
                      WHERE padre = '0'
                         OR padre = d_accesso_id
                         OR (    padre =
                                    SUBSTR (d_accesso_id
                                          , 1
                                          , d_prima_quadra - 1
                                           )
                             AND d_prima_quadra > 0
                            )
                         OR (    padre =
                                    SUBSTR (d_accesso_id
                                          , 1
                                          , d_seconda_quadra - 1
                                           )
                             AND d_seconda_quadra > 0
                            )
                         OR (    padre =
                                    SUBSTR (d_accesso_id
                                          , 1
                                          , d_terza_quadra - 1
                                           )
                             AND d_terza_quadra > 0
                            )
                   ORDER BY 1)
      LOOP
         d_stringa := ' ';
         d_pagina :=
               'AD4AccessiTree.do?ID='
            || acce.figlio
            || CHR (38)
            || 'ID_OLD='
            || d_accesso_id
            || CHR (38)
            || 'UTENTE='
            || acce.Utente;
         IF INSTR (d_accesso_id, acce.figlio) > 0
         THEN
            d_bitmap := d_collapse;
            d_immagine := acce.immagine_open;
         ELSE
            d_bitmap := d_expand;
            d_immagine := acce.immagine;
         END IF;
         IF acce.livello > 0
         THEN
            d_stringa :=
               LPAD (d_stringa
                   , LENGTH (d_vert_line) * acce.livello
                   , d_vert_line
                    );
         END IF;
         IF acce.nodo = 'S'
         THEN
            d_stringa :=
                     d_stringa || '<a href="' || d_pagina || '">' || d_bitmap;
         END IF;
         d_stringa :=
               d_stringa
            || '<img src="'
            || d_immagine
            || '" BORDER=0 align="AbsMiddle"></a>'
            || '<a align="AbsMiddle" href="'
            || d_pagina
            || '">'
            || acce.descrizione
            || '</a><br>';
         d_stringa := replace (d_stringa,'[','%5B'); -- rev. 9
         d_stringa := replace (d_stringa,'[','%5B'); -- rev. 9
         d_amount := LENGTH (d_stringa);
         dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
         dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
         dbms_lob.APPEND (d_clob, d_clob_dep);
      END LOOP;
      d_stringa := '</td></tr></table>';
      d_amount := LENGTH (d_stringa);
      dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
      dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
      dbms_lob.APPEND (d_clob, d_clob_dep);
      RETURN d_clob;
   END GET_ALBERO_ACCESSI;
   FUNCTION GET_ALBERO_GRUPPI (P_ID VARCHAR2, P_ID_PADRE VARCHAR2)
      RETURN CLOB
   IS /* SLAVE_COPY */
      d_id          VARCHAR2 (2000)  := NVL (P_ID, '0');
      d_id_padre    VARCHAR2 (2000)  := NVL (P_ID_padre, '0');
      d_stringa     VARCHAR2 (32000);
      d_righe       VARCHAR2 (32000);
      d_nodo_img    VARCHAR2 (200)
         := '<img src="../common/images/AD4/cmnode.gif" BORDER=0 align="AbsMiddle">';
      d_vert_line   VARCHAR2 (200)
         := '<img src="../common/images/AMV/cmsvertline.gif" BORDER=0 align="AbsMiddle">';
-- gestione tramite clob
      d_amount      BINARY_INTEGER   := 32767;
      d_clob        CLOB             := EMPTY_CLOB ();
      d_clob_dep    CLOB             := EMPTY_CLOB ();
      d_loop        NUMBER           := 0;
   BEGIN
      IF d_id = '0'
      THEN
         d_stringa := '<div id="0"></div><div id="0[f">';
         dbms_lob.createTemporary (d_clob, TRUE, dbms_lob.SESSION);
         d_amount := LENGTH (d_stringa);
         dbms_lob.WRITE (d_clob, d_amount, 1, d_stringa);
      END IF;
      FOR utgr IN (SELECT   vgut.FIGLIO
                          , vgut.Utente || ' - ' || nominativo DESCRIZIONE
                          , DECODE (uten.tipo_utente
                                  , 'U', 'AD4Utente.do?UTENTE=' || vgut.Utente
                                  , 'AD4Gruppo.do?GRUPPO=' || vgut.Utente
                                   ) URL
                          , DECODE (DECODE (uten.tipo_utente
                                          , 'O', DECODE (ruol.gruppo_so
                                                       , 'S', 'R'
                                                       , uten.tipo_utente
                                                        )
                                          , uten.tipo_utente
                                           )
                                  , 'U', '../common/images/AD4/Uten.gif'
                                  , 'G', '../common/images/AD4/Gruppo.gif'
                                  , 'O', '../common/images/AD4/UniOrg.gif'
                                  , 'R', '../common/images/AD4/kuser.gif'
                                  , ' '
                                   ) BITMAP
                          , DECODE (uten.tipo_utente
                                  , 'O', DECODE (ruol.gruppo_so
                                               , 'S', 'R'
                                               , uten.tipo_utente
                                                )
                                  , uten.tipo_utente
                                   ) TIPOUTENTE
                          , figli.esistono NODO, vgut.Utente
                          , NVL (vgut.Gruppo, '0') Gruppo, vgut.LIVELLO
                          , vgut.PADRE
                          , NVL (vgut.GRUPPO_PREC, '0') GRUPPO_PREC
                          , DECODE (DECODE (uten.tipo_utente
                                          , 'O', DECODE (ruol.gruppo_so
                                                       , 'S', 'R'
                                                       , uten.tipo_utente
                                                        )
                                          , uten.tipo_utente
                                           )
                                  , 'U', 'Utente: '
                                  , 'O', 'Unita'': '
                                  , 'R', 'Ruolo: '
                                  , 'Gruppo: '
                                   ) LABEL
                       FROM VISTA_GRUPPI_UTENTI vgut
                          , UTENTI uten
                          , RUOLI ruol
                          , (SELECT   DECODE (COUNT (1)
                                            , 0, 'N'
                                            , 'S'
                                             ) esistono, Gruppo
                                 FROM UTENTI_GRUPPO
                             GROUP BY Gruppo) figli
                      WHERE uten.Utente = vgut.Utente
                        AND figli.Gruppo(+) = vgut.Utente
                        AND ruol.ruolo = NVL (uten.gruppo_lavoro, 'def')
                        AND vgut.Gruppo = d_id
                        AND vgut.PADRE =
                               DECODE (d_id
                                     , '0', vgut.padre
                                     , DECODE (d_id_padre
                                             , '0', vgut.padre
                                             , d_id_padre || '[' || d_id
                                              )
                                      )
                   ORDER BY 5, 2)
      LOOP
         d_loop := d_loop + 1;
         d_stringa := ' ';
         IF utgr.livello > 0
         THEN
            d_righe :=
               TRIM (LPAD (d_stringa
                         , (LENGTH (d_vert_line) * utgr.livello) + 1
                         , d_vert_line
                          )
                    );
         END IF;
         d_stringa :=
               '<div id="'
            || utgr.figlio
            || '" style="vertical-align:top;" nowrap>'
            || d_righe;
         IF utgr.nodo = 'S'
         THEN
            d_stringa :=
                  d_stringa
               || '<a href="javascript:void(0);" onclick="apri('''
               || utgr.Utente
               || ''', '''
               || utgr.Gruppo
               || ''', '''
               || utgr.figlio
               || '[f'');this.onclick=function(){apri('''
               || utgr.Gruppo
               || ''', '''
               || utgr.gruppo_prec
               || ''', '''
               || utgr.padre
               || '[f'');}">';
            d_stringa := d_stringa || d_nodo_img;
            d_stringa :=
                  d_stringa
               || '<img src="'
               || utgr.BITMAP
               || '" BORDER=0 align="AbsMiddle"></a>';
         ELSE
            d_stringa :=
                  d_stringa
               || '<img src="../common/images/AMV/cmsnode.gif" BORDER=0 align="AbsMiddle">'
               || '<img src="'
               || utgr.BITMAP
               || '" BORDER=0 align="AbsMiddle">';
         END IF;
         d_stringa :=
               d_stringa
            || utgr.LABEL
            || '<a href="'
            || utgr.url
            || '">'
            || utgr.descrizione
            || '</a>';
         d_stringa := d_stringa || '</div>';
         IF utgr.nodo = 'S'
         THEN
            d_stringa :=
                      d_stringa || '<div id="' || utgr.figlio || '[f"></div>';
         END IF;
         d_amount := LENGTH (d_stringa);
         dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
         dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
         IF d_loop = 1 AND d_id <> '0'
         THEN
            dbms_lob.createTemporary (d_clob, TRUE, dbms_lob.SESSION);
            d_amount := LENGTH (d_stringa);
            dbms_lob.WRITE (d_clob, d_amount, 1, d_stringa);
         ELSE
            dbms_lob.APPEND (d_clob, d_clob_dep);
         END IF;
      END LOOP;
      IF d_id = '0'
      THEN
         d_stringa := '</div>';
         d_amount := LENGTH (d_stringa);
         dbms_lob.createTemporary (d_clob_dep, TRUE, dbms_lob.SESSION);
         dbms_lob.WRITE (d_clob_dep, d_amount, 1, d_stringa);
         dbms_lob.APPEND (d_clob, d_clob_dep);
         dbms_lob.freetemporary (d_clob_dep);
      END IF;
      RETURN d_clob;
   END GET_ALBERO_GRUPPI;
FUNCTION GET_GRUPPI_DIAC_UTENTE
/******************************************************************************
NOME:        GET_GRUPPI_DIAC_UTENTE
DESCRIZIONE: Elenco gruppi di appartenenza e diritti di accesso per i gruppi
PARAMETRI:   --
RITORNA:     stringa varchar2.
NOTE:
******************************************************************************/
(p_utente IN VARCHAR2)
   RETURN CLOB
IS                                                            /* SLAVE_COPY */
   d_stringa    VARCHAR2 (32000);
   -- gestione tramite clob
   d_clob       CLOB := EMPTY_CLOB ();
BEGIN
   DBMS_LOB.createTemporary (d_clob, TRUE, DBMS_LOB.SESSION);
  -- DBMS_LOB.WRITE (d_clob,LENGTH ('<tr>'),1, '<tr>');
   DBMS_LOB.WRITE (d_clob,LENGTH ('<table width="100%">'),1,'<table width="100%">');
   FOR v_utgr
      IN (SELECT   decode(rownum,0,'','<br>') ||  '<tr><td class="AFCDataTD">'
                 || gruppo.get_descrizione (gruppo)
                   gruppo_html,
                 gruppo
            FROM utenti_gruppo
           WHERE utente = p_utente)
   LOOP
      DBMS_LOB.WRITEAPPEND (d_clob,
                            LENGTH (v_utgr.gruppo_html),
                            v_utgr.gruppo_html);
--       d_stringa:= CHECK_DIAC_CAAC_UTEN_GRUPPO(p_utente, v_utgr.gruppo);
      FOR v_diac
         IN (SELECT '<br>Con diritto su modulo: '
                    || moduli_tpk.get_descrizione (diac.modulo)
                    || ', istanza '
                    || ISTANZA.GET_DESCRIZIONE (diac.istanza)
                    || ' e ruolo <strong>'
                    || ruol.descrizione
                    || '</strong>.'
                       diac_html
               FROM DIRITTI_ACCESSO DIAC, RUOLI ruol
              WHERE DIAC.gruppo = v_utgr.gruppo
                   AND DIAC.Utente = p_utente
                   AND ruol.ruolo = DIAC.ruolo)
      LOOP
         DBMS_LOB.WRITEAPPEND (d_clob, LENGTH (v_diac.diac_html),v_diac.diac_html);
      END LOOP;
    --  DBMS_LOB.WRITEAPPEND (d_clob, LENGTH (d_stringa),d_stringa);
      DBMS_LOB.WRITEAPPEND (d_clob, LENGTH ('&nbsp;</td></tr>'),'&nbsp;</td></tr>');
   END LOOP;
  -- DBMS_LOB.WRITEAPPEND (d_clob, LENGTH ('</tr>'), '</tr>');
  DBMS_LOB.WRITEAPPEND (d_clob, LENGTH ('</table>'), '</table>');
   RETURN d_clob;
END GET_GRUPPI_DIAC_UTENTE;
FUNCTION GET_DIAC_UTENTE
/******************************************************************************
NOME:        GET_DIAC_UTENTE
DESCRIZIONE: Elenco diritti di accesso diretti all'utente
PARAMETRI:   --
RITORNA:     stringa varchar2.
NOTE:
******************************************************************************/
(p_utente IN VARCHAR2)
   RETURN CLOB
  IS
  d_stringa    VARCHAR2 (32000);
   -- gestione tramite clob
   d_clob       CLOB := EMPTY_CLOB ();
BEGIN
   DBMS_LOB.createTemporary (d_clob, TRUE, DBMS_LOB.SESSION);
  -- DBMS_LOB.WRITE (d_clob,LENGTH ('<tr>'),1, '<tr>');
   DBMS_LOB.WRITE (d_clob,LENGTH ('<table width="100%">'),1,'<table width="100%">');
      FOR v_diac
         IN (SELECT '<tr><td class="AFCDataTD"><br>Diritto su modulo: '
                    || moduli_tpk.get_descrizione (diac.modulo)
                    || ', istanza '
                    || ISTANZA.GET_DESCRIZIONE (diac.istanza)
                    || ' e ruolo <strong>'
                    || ruol.descrizione
                    || '</strong>.&nbsp;</td></tr>'
                       diac_html
               FROM DIRITTI_ACCESSO DIAC, RUOLI ruol
              WHERE DIAC.gruppo is null
                   AND DIAC.Utente = p_utente
                   AND ruol.ruolo = DIAC.ruolo)
      LOOP
         DBMS_LOB.WRITEAPPEND (d_clob, LENGTH (v_diac.diac_html),v_diac.diac_html);
      END LOOP;
  DBMS_LOB.WRITEAPPEND (d_clob, LENGTH ('</table>'), '</table>');
   RETURN d_clob;
  END  GET_DIAC_UTENTE;
  FUNCTION GET_CAAC_UTENTE
/******************************************************************************
NOME:        GET_CAAC_UTENTE
DESCRIZIONE: Elenco caratteristiche di accesso dirette dell'utente
PARAMETRI:   --
RITORNA:     stringa varchar2.
NOTE:
******************************************************************************/
(p_utente IN VARCHAR2)
   RETURN CLOB
  IS
   d_stringa    VARCHAR2 (32000);
   -- gestione tramite clob
   d_clob       CLOB := EMPTY_CLOB ();
BEGIN
   DBMS_LOB.createTemporary (d_clob, TRUE, DBMS_LOB.SESSION);
  -- DBMS_LOB.WRITE (d_clob,LENGTH ('<tr>'),1, '<tr>');
   DBMS_LOB.WRITE (d_clob,LENGTH ('<table width="100%">'),1,'<table width="100%">');
      FOR v_CAAC
         IN (SELECT '<tr><td class="AFCDataTD"><br>Caratteristiche su modulo: '
                    || moduli_tpk.get_descrizione (CAAC.modulo)
                    || ', istanza '
                    || ISTANZA.GET_DESCRIZIONE (CAAC.istanza)
                    || '&nbsp;</td></tr>'
                       caac_html
               FROM CARATTERISTICHE_ACCESSO CAAC
              WHERE CAAC.Utente = p_utente
                   AND CAAC.tipo_accesso = 'D')
      LOOP
         DBMS_LOB.WRITEAPPEND (d_clob, LENGTH (v_CAAC.caac_html),v_CAAC.caac_html);
      END LOOP;
  DBMS_LOB.WRITEAPPEND (d_clob, LENGTH ('</table>'), '</table>');
   RETURN d_clob;
  END  GET_CAAC_UTENTE;



function GET_ELEMENT
(in_text              in varchar2
,in_separator         in varchar2
,in_position          in pls_integer default 1
) return varchar2
is
  text_from              number(4);
  text_to                number(4);
  text_length            number(4);
  text                   varchar2(4000);
  esc                    varchar2(1) := element_escape;
  not_esc_text           varchar2(4000) := replace(in_text,esc||in_separator,esc||esc);
begin
  if in_position = 1 then
   if substr(not_esc_text,1,1) = in_separator then
        return null; -- element doesn't exists
     end if;
     text_from := 1;
  else
     text_from := instr(not_esc_text,in_separator,1,in_position-1);
   if text_from = 0 then
        return null; -- element doesn't exists
     end if;
   text_from := text_from + 1;
  end if;
  text_to := instr(not_esc_text,in_separator,1,in_position);
  if text_to = 0 then
     text_to := length(not_esc_text)+1;
  end if;
  text_length := text_to - text_from;
  if text_length <= 0 then
     text := null;
  else
     text := substr(in_text,text_from,text_length);
     text := replace(text,esc||in_separator,in_separator);
  end if;
  return text;
end;


function GESTIONE_ERRORE
(p_modulo     VARCHAR2
,p_utente     VARCHAR2
,in_error_stack      clob
,in_format           number default 1
) return varchar2
/******************************************************************************
 NOME:        GESTIONE_ERRORE
 DESCRIZIONE: Restituisce l'errore da visualizzare.
              Se tra -20000 e -20999 restituisce la descrizione personalizzata.
              Altrimenti inserisce un record nella key_error_log e indica id
              della registrazione inserita
 PARAMETRI:   --
 RITORNA:
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    08/06/2020        Creazione.
 7    09/06/2020 SN    Gestione visualizzazione errore Feature #42907
 ******************************************************************************/
is
  err_row     varchar2(4000);
  err_code    number;
  err_text    varchar2(4000);
  err_type    varchar2(1);
  out_log_id  varchar2(16);
  out_message varchar2(4000);
  tmp_debug   varchar2(20);
  pragma autonomous_transaction;
  d_err_id    number;
begin
  begin

    tmp_debug := amvweb.get_preferenza('Debug',p_modulo, p_utente);
--    execute immediate 'select '||user||'.amvweb.get_preferenza(''Debug'',sys_context(''SI4SESSION'',''MODULO''),sys_context(''SI4SESSION'',''UTENTE'')) from dual'
--       into tmp_debug;
  exception when others then
    tmp_debug := null;
  end;
  if nvl(tmp_debug,'NO') = 'SI' then
    return in_error_stack;
  end if;
--  select si4_error_log_sq.nextval
--    into out_log_id
--    from dual
--  ;
--  out_log_id := to_char(sysdate,'dd')
--                ||'.'||lpad(mod(out_log_id,1000),3,'0')
--                ||'.'||substr(out_log_id,1,length(out_log_id)-3)
--  ;
  err_row  := get_element(in_error_stack,chr(10),1);
  begin
    err_code := substr(get_element(err_row,':',1),5);
  exception when others then
    err_code := null;
  end;
  if err_code between 20000 and 20999 then
    err_type    := 'C';
    out_message := '('||substr(err_row,7,3)||') '||substr(err_row,12);
  else
    err_type    := 'O';
    out_message := 'Si è verificato un problema ('||out_log_id||')';
  end if;
  if in_error_stack not like 'ORA-%' then
    err_type    := 'J';
  end if;
  --Scrittura LOG
  begin
  SELECT keel_sq.NEXTVAL
                 INTO d_err_id
                 FROM DUAL;
  key_error_log_pkg.ins
                        (p_error_id => d_err_id,
                         p_error_session =>  USERENV ('sessionid'),
                         p_error_date =>SYSDATE,
                         p_ERROR_TEXT => in_error_stack ,
                         p_error_user => USER,
                         p_ERROR_TYPE => 'S'
                        );
    --Autopulizia
    delete KEY_ERROR_LOG
     where error_date < sysdate-7
       and rownum <= 10
       and error_type = 'S'
    ;
  end;
  -- Formattazione
  if in_format=1 then
    out_message :=
      '<TABLE width="100%" cellspacing="2" cellpadding="5"><TR bgcolor=#0079D6><TD>' -- Sfondo con Errore Windows
    ||'<font style="font-size: 200%; color: white;"><strong>:(</strong></font>'
    ||'<BR></TR><TR bgcolor=white>'
    ||'<font style="font-size: 120%; color: red;">'||out_message||'</font>'
    ||'</TD></TR></TABLE>';
  end if;
  commit;
  return out_message||chr(10);
  -- NO EXCEPTION, eventuali errori dovranno essere rilevati dal log esterno
end;

END Ad4web;
/
