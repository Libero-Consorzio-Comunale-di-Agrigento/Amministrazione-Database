CREATE OR REPLACE PACKAGE BODY utente
IS
/******************************************************************************
 NOME:        UTENTE.
 DESCRIZIONE: Package body per gestione UTENTI.
 ANNOTAZIONI: Salvato nella directory ins di AD4 nel file uten.pkg.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 009  13/07/2007 MM     Adeguamento versione a nuovi standard.
                        Modifica a check_password, notifica_richiesta e
                        creazione IS_PASSWORD_VALIDA.
 013  16/02/2009 MM     Creazione SET_UTENTE.
 014  15/03/2010 SNeg   Get non usano piu variabili di package
 015  03/05/2010 SNeg   Copio si4.utente in ad4 se arrivo da a00 o si
 019  10/09/2010 SNeg   Modificati registra_web e aggiorna_web per valorizzare
                        di default la competenza.
                        Tolta slave_copy per get_note per problemi in
                         abilitazione servizi (leggeva su slave info non
                         ancora replicate)
020  17/01/2010 SNeg   Sistemazione errori per revoca e abilitazione utente in  base a SO4
021  06/06/2011 SNeg Modificata get_utente, in base al registro se solo un utente puo essere
                                 legato allo stesso soggetto ritorna errore.
022  31/08/2011 SNeg Modificata upd_utente per memorizzare la password in ad4 in base alla voce di registro
                                 solo per utenti ldap.
023  19/01/2012 SNeg Dimensionate variabili in base alla tabella istanze
024  09/03/2012 SNeg Gestione errori in rigenera_so
025  26/03/2012 SNeg Utente in gruppo ldap se creato quando soggetto già in Struttura Organizzativa
026 12/04/2012 SNeg tolta modifica 2008 per riproporre seconda parte password
027  07/05/2012 SNeg Aggiunta determina utente e determina nominativo e determina_utente_libero
028  19/06/2012 SNeg In rigenera_so modificato comportamento che se eredita diritto di accesso dalla
struttura il diritto viene forzatamente indicato che è ereditato da quel gruppo così se poi verrà
tolto dal gruppo in automatico verrà tolto.
029 09/04/2013 SNeg Tolto il default dello user nella competenza,  metteva lo user
                    su db anziché il progetto
030 23/04/2013 SNeg Non consentire registrazione se una richiesta  già in corso.
031 05/06/2013 SNeg Corretto errore che non consentiva uso della regola del registro
                    per determinazione codice utente e sistemato calcolo nominativo
032 16/07/2013 AD  Corretto errore se separatore nullo
033 19/02/2014 SN  Tolto raddoppio apici sul nome
034 10/06/2014 SN  Modificato per consentire calcolo dell'utente in base al soggetto
                  o a partire dal nominativo
035 12/05/2015 SN Controllo che inserisce in insertsotemp solo se fornito valore non nullo
036 13/02/2015 SN     Modifica x autenticazione ldap
037 04/06/2015 SN     Evitare cancellazione di utenti_gruppo se un ruolo di struttura
                     viene inserito in un gruppo di ad4.
038 22/06/2015 SN    Per rigenerare la struttura prima cancellare i record presenti
039 12/04/2017 SN   Smonatare modifica x ereditare dai gruppi senza curarsi dei diritti
                    diretti per un utente
040 09/05/2018 SN   Controllare il parametro in('SI', 'YES') x integrazione ldap
041 21/09/2018 SN Aggiunti parametri x nuovi campi
042 16/10/2018 SN  Verificata password riutilizzata prima dei giorni previsti
043 07/01/2019 SNeg In rigenera_so verificare che utente sia nel gruppo corretto
                    in base al fatto che utente sia amministratore o meno
044 11/01/2019 SNeg In caso di utenza ldap aggiornare pwd sempre = NO
045 14/01/2019 SNeg Tolta autonomous transaction nella rigenera_so e messa indicazione degli errori nella key_error_log
046 21/01/2019 SNeg Aggiunto invio mail con autonomous transaction
047 21/01/2019 SNeg   Introdotta procedure Send_msg_modifica_utente
048 28/03/2019 SNeg  Introdotta DBMS_APPLICATION_INFO.set_action x allineamento diritti_accesso
049 22/05/2019 SNeg utilizzo funzione global_utility.get_registro_amministratore
050 02/07/2019 SNeg Impostazioni variabili per indicare che si è in registrazione da web #35539
051 21/11/2019 SNeg Chiusura cursori se aperti
052 28/11/2019 SNeg Per la struttura considero solo i gruppi di tipo 'O'Bug #38849
053 28/11/2019 SNeg Per il cim considero il tag con maiuscole e minuscole Bug #38857
054 27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
                       (modificato quanto introdotto precedentemente con indicazione md5)
055 31/03/2020 SN Set utente in inserimento altrimenti non riesce a criptare la password
056 19/05/2020 SN    Sistemare posizionamento nel gruppo standard  indipendentemente dal registro Bug#41840
057 27/05/2020 SN    Funzione per sapere se si sta sistemando posizionamento nei gruppi
058 11/07/2020 SN   Gestione errore in caso di aggiornamento o modifica utenti  Feature #42907
059 24/09/2020 SN   Togliere modifiche rev.50 in quanto in caso di abilitazione al portale
                    gp4web viene creato un nuovo soggetto e non tenuto quello abbinato al dipendente #44856
060 12/02/2021 SN   Allargare il campo per la gestione della password con i nuovi algoritmi Bug #48221
******************************************************************************/
   s_revisione_body   CONSTANT afc.t_revision                  := '060';
------------------------------------------------------------
-- Variabili del package body.
------------------------------------------------------------
   d_body_utente               utenti.utente%TYPE;
   d_body_id_utente            utenti.id_utente%TYPE;
   d_body_soggetto             utenti_soggetti.soggetto%TYPE;
   d_body_new_nominativo       utenti.nominativo%TYPE;
   d_body_old_nominativo       utenti.nominativo%TYPE;
   d_body_old_password         utenti.PASSWORD%TYPE;
   d_body_new_password         utenti.PASSWORD%TYPE;
   d_body_password             utenti.PASSWORD%TYPE;
   d_body_data_password        VARCHAR2 (10);
   d_body_rinnovo_password     utenti.rinnovo_password%TYPE;
   d_body_ultimo_tentativo     VARCHAR2 (19);
   d_body_numero_tentativi     utenti.numero_tentativi%TYPE;
   d_body_note                 utenti.note%TYPE;
   d_body_stato                utenti.stato%TYPE;
   d_body_lingua               utenti.lingua%TYPE;
   d_body_gruppo_lavoro        utenti.gruppo_lavoro%TYPE;
   d_body_importanza           utenti.importanza%TYPE;
   d_body_data_ins             VARCHAR2 (19);
   d_body_utente_agg           utenti.utente%TYPE;
   d_body_data_agg             VARCHAR2 (19);
   d_body_pwd_da_modificare    utenti.pwd_da_modificare%TYPE;
   d_body_flag_psw             VARCHAR2 (1);
   d_body_amministratore utenti.amministratore%TYPE;
   d_body_info_identificazione utenti.info_identificazione%TYPE;
   v_nominativo_stringa            registro.stringa%TYPE :='SintassiNominativoAutomatico';
   v_nominativo_chiave             registro.chiave%TYPE := 'PRODUCTS/AD4/UTENTE';
   v_utente_stringa            registro.stringa%TYPE :='SintassiUtenteAutomatico';
   v_utente_chiave             registro.chiave%TYPE := 'PRODUCTS/AD4/UTENTE';
   v_is_sistemazione_gruppi      boolean:= false; -- rev.57
   FUNCTION versione
/******************************************************************************
 NOME:        VERSIONE.
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 ECCEZIONI:   --
 ANNOTAZIONI: Il secondo numero della versione corrisponde alla revisione
              del package.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    13/01/2003 MM     Creazione.
******************************************************************************/
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   BEGIN
      RETURN afc.VERSION (s_revisione, s_revisione_body);
   END versione;
   PROCEDURE init
/******************************************************************************
 NOME:        INIT.
 DESCRIZIONE: Svuota tutti gli attributi dell'utente (variabili del package body)
              e dell'eventuale soggetto associato.
 ARGOMENTI:   -
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 41 21/09/2018 SN   Aggiunti nuovi campi
******************************************************************************/
   IS
   BEGIN
      d_body_utente := TO_CHAR (NULL);
      d_body_id_utente := TO_NUMBER (NULL);
      d_body_soggetto := TO_NUMBER (NULL);
      d_body_new_nominativo := TO_CHAR (NULL);
      d_body_old_nominativo := TO_CHAR (NULL);
      d_body_password := TO_CHAR (NULL);
      d_body_old_password := TO_CHAR (NULL);
      d_body_new_password := TO_CHAR (NULL);
      d_body_data_password := TO_CHAR (NULL);
      d_body_rinnovo_password := TO_CHAR (NULL);
      d_body_ultimo_tentativo := TO_CHAR (NULL);
      d_body_numero_tentativi := TO_NUMBER (NULL);
      d_body_note := TO_CHAR (NULL);
      d_body_stato := TO_CHAR (NULL);
      d_body_lingua := TO_CHAR (NULL);
      d_body_gruppo_lavoro := TO_CHAR (NULL);
      d_body_importanza := TO_NUMBER (NULL);
      d_body_utente_agg := TO_CHAR (NULL);
      d_body_pwd_da_modificare := TO_CHAR (NULL);
      d_body_amministratore := TO_CHAR (NULL);
      d_body_info_identificazione:= TO_CHAR (NULL);
      d_body_flag_psw := 'N';
      soggetto.init;
   END init;
   PROCEDURE initialize
/******************************************************************************
 NOME:        INITIALIZE.
 DESCRIZIONE: Dato l'utente inizializza tutti i suoi attributi e quelli della
              eventuale registrazione anagrafica associata.
 ARGOMENTI:   p_utente    Codice dell'utente che si vuole inizializzare.
              p_error_msg 1 se si vuole che sia segnalato un errore in caso
                            l'utente passato non esista;
                          0 altrimenti.
 ECCEZIONI:   20999, Utente inesistente.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
 41  21/09/2018 SN      Aggiunta nuovi campi
******************************************************************************/
   (p_utente IN VARCHAR2, p_error_msg IN NUMBER DEFAULT 1)
   IS
   BEGIN
      BEGIN
/******************************************************/
/*     Inizializzazione attributi dell'utente         */
/******************************************************/
         init;
         SELECT utente, id_utente, nominativo,
                nominativo, PASSWORD,
                TO_CHAR (data_password, 'dd/mm/yyyy'), rinnovo_password,
                TO_CHAR (ultimo_tentativo, 'dd/mm/yyyy hh24:mi:ss'),
                numero_tentativi, note, stato,
                NVL (lingua, '*'), gruppo_lavoro, importanza,
                pwd_da_modificare, amministratore, info_identificazione
           INTO d_body_utente, d_body_id_utente, d_body_new_nominativo,
                d_body_old_nominativo, d_body_password,
                d_body_data_password, d_body_rinnovo_password,
                d_body_ultimo_tentativo,
                d_body_numero_tentativi, d_body_note, d_body_stato,
                d_body_lingua, d_body_gruppo_lavoro, d_body_importanza,
                d_body_pwd_da_modificare, d_body_amministratore,
                d_body_info_identificazione
           FROM utenti
          WHERE utenti.utente = p_utente AND tipo_utente = 'U';
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            init;
            IF p_error_msg = 1
            THEN
               raise_application_error (-20999, 'Utente inesistente.');
            END IF;
         WHEN OTHERS
         THEN
            init;
            RAISE;
      END;
/******************************************************/
/*     Inizializzazione attributi dell'eventuale      */
/*     registrazione anagrafica associata.            */
/******************************************************/
      BEGIN
         SELECT utenti_soggetti.soggetto
           INTO d_body_soggetto
           FROM utenti utenti, utenti_soggetti utenti_soggetti
          WHERE utenti.utente = utenti_soggetti.utente
            AND utenti.utente = d_body_utente;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_body_soggetto := TO_NUMBER (NULL);
         WHEN OTHERS
         THEN
            init;
            RAISE;
      END;
      IF d_body_soggetto IS NOT NULL
      THEN
         BEGIN
            soggetto.initialize (d_body_soggetto);
         EXCEPTION
            WHEN OTHERS
            THEN
               init;
               RAISE;
         END;
      END IF;
   END initialize;
   PROCEDURE del
/******************************************************************************
 NOME:        del
 DESCRIZIONE: Eliminazione di una riga con chiave indicata.
 PARAMETRI:   Chiave della table.
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 7    27/10/2006 MM     Prima emissione.
******************************************************************************/
   (p_utente IN utenti.utente%TYPE)
   IS
   BEGIN
      DELETE      utenti
            WHERE utente = p_utente;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END;
   FUNCTION determina_utente_libero
/******************************************************************************
 NOME:        determina_utente_libero
 DESCRIZIONE: Cerca il primo codice utente libero a partire dal nominativo.
 ARGOMENTI:   -
 ECCEZIONI:   -
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
027  07/05/2012 SNeg Aggiunta determina utente e determina nominativo.
******************************************************************************/
(p_nominativo VARCHAR2)
   RETURN VARCHAR2
IS
   v_utente          utenti.utente%TYPE;
   v_lunghezza_max   NUMBER := 8;
   v_lunghezza       NUMBER := 8;
   v_contatore       NUMBER := 1;
BEGIN
   v_utente := SUBSTR (TRANSLATE (p_nominativo, 'a ''.-_@', 'a'), 1, 8);
   WHILE utente.exists_utente (v_utente) = 1
   LOOP
      v_lunghezza := v_lunghezza - 1;
      v_utente := SUBSTR (v_utente, 1, v_lunghezza);
      v_contatore := 1;
      v_utente :=
         SUBSTR (v_utente, 1, v_lunghezza)
         || LPAD (v_contatore, v_lunghezza_max - v_lunghezza, '0');
      WHILE LENGTH (v_contatore) <= v_lunghezza_max - v_lunghezza
            AND utente.exists_utente (v_utente) = 1
      LOOP
         v_contatore := v_contatore + 1;
         v_utente :=
            SUBSTR (v_utente, 1, v_lunghezza)
            || LPAD (v_contatore, v_lunghezza_max - v_lunghezza, '0');
      END LOOP;
   END LOOP;
return v_utente;
END determina_utente_libero;
   PROCEDURE ins
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 7    27/10/2006 MM     Prima emissione.
 025  26/03/2012 SNeg Utente in gruppo ldap se creato quando soggetto già in Struttura Organizzativa
034 10/06/2014 SN  Modificato per consentire calcolo dell'utente in base al soggetto
                  o a partire dal nominativo
040 09/05/2018 SN   Controllare il parametro in('SI', 'YES') x integrazione ldap
041 21/09/2018 SN Aggiunti nuovi campi
055 31/03/2020 SN Set utente in inserimento altrimenti non riesce a criptare la password
******************************************************************************/
   (
      p_nominativo             IN       utenti.nominativo%TYPE,
      p_utente                 IN OUT   utenti.utente%TYPE,
      p_id_utente              IN OUT   utenti.id_utente%TYPE,
      p_password               IN       utenti.PASSWORD%TYPE DEFAULT NULL,
      p_data_password          IN       VARCHAR2 DEFAULT NULL,
      p_rinnovo_password       IN       utenti.rinnovo_password%TYPE
            DEFAULT NULL,
      p_pwd_da_modificare      IN       utenti.pwd_da_modificare%TYPE
            DEFAULT NULL,
      p_stato                  IN       utenti.stato%TYPE DEFAULT NULL,
      p_note                   IN       utenti.note%TYPE DEFAULT NULL,
      p_lingua                 IN       utenti.lingua%TYPE DEFAULT NULL,
      p_gruppo_lavoro          IN       utenti.gruppo_lavoro%TYPE DEFAULT NULL,
      p_importanza             IN       utenti.importanza%TYPE DEFAULT NULL,
      p_utente_aggiornamento   IN       utenti.utente%TYPE DEFAULT NULL,
      p_soggetto               IN       utenti_soggetti.soggetto%TYPE
            DEFAULT NULL,
      p_ultimo_tentativo       IN       VARCHAR2 DEFAULT NULL,
      p_numero_tentativi       IN       utenti.numero_tentativi%TYPE
            DEFAULT NULL,
      p_data_inserimento       IN       VARCHAR2
            DEFAULT TO_CHAR (SYSDATE, 'dd/mm/yyyy hh24:mi:ss'),
      p_data_aggiornamento     IN       VARCHAR2 DEFAULT NULL,
    p_amministratore    in utenti.amministratore%TYPE default null,
    p_info_identificazione    in utenti.info_identificazione%TYPE default null,
      p_is_psw_crypted         IN       NUMBER DEFAULT 0
   )
   IS
      d_mod_sogg   VARCHAR2 (1) := 'N';
      d_utente_in_ldap varchar2(3) := 'NO';
   BEGIN
      integritypackage.LOG ('utente.ins: INIT ');
      init;
      integritypackage.LOG ('utente.ins: calcola_utente ' || p_utente);
      if p_utente is null  -- da calcolare in modo automatico
      then
         if  p_soggetto is not null then -- fornito soggetto
            p_utente := determina_utente(p_soggetto);
         else
            p_utente := determina_utente_libero(upper(p_nominativo));
         end if;
      end if;
      calcola_utente (p_utente, p_id_utente);
      set_utente(p_utente); --rev 55
      integritypackage.LOG (   'utente.ins: set_nominativo '
                            || UPPER (p_nominativo)
                           );
      set_nominativo (UPPER (p_nominativo));
      set_data_password (p_data_password);
      IF NVL (p_is_psw_crypted, 0) = 0
      THEN
         set_password (p_password);
      ELSE
         set_password_crypted (p_password);
      END IF;
      integritypackage.LOG ('utente.ins: set_rinnovo_password ');
      set_rinnovo_password (p_rinnovo_password);
      set_pwd_da_modificare (p_pwd_da_modificare);
      set_stato (NVL (UPPER (p_stato), 'U'));
      set_note (p_note);
      set_lingua (UPPER (p_lingua));
      set_gruppo_lavoro (p_gruppo_lavoro);
      set_importanza (p_importanza);
      set_id_utente (p_id_utente);
      set_ultimo_tentativo (p_ultimo_tentativo);
      set_numero_tentativi (p_numero_tentativi);
      set_data_inserimento (p_data_inserimento);
      set_data_aggiornamento (p_data_aggiornamento);
      set_data_inserimento (p_data_inserimento);
      set_data_aggiornamento (p_data_aggiornamento);
      set_utente_agg (NVL (p_utente_aggiornamento, si4.utente));
      set_amministratore (p_amministratore);
      set_info_identificazione (p_info_identificazione);
      update_utente (p_utente, d_mod_sogg);
      IF p_soggetto IS NOT NULL
      THEN
         DECLARE
            d_sogg   NUMBER;
         BEGIN
            SELECT soggetto
              INTO d_sogg
              FROM soggetti
             WHERE soggetto = p_soggetto;
            INSERT INTO utenti_soggetti
                        (utente, soggetto
                        )
                 VALUES (p_utente, p_soggetto
                        );
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               raise_application_error (-20999,
                                           'Soggetto '
                                        || p_soggetto
                                        || ' inesistente.'
                                       );
         END;
         -- verifico se utente in struttura organizzativa lo metto con accesso ldap
         d_utente_in_ldap   :=
         nvl(upper(registro_utility.leggi_stringa (UPPER ('PRODUCTS/AD4/UTENTE')
                                       , 'AccessoLdapSeInSO4'
                                       , 0
         )),'NO');
         -- Rev.40 inizio
         if d_utente_in_ldap in('SI', 'YES') and so4_util.is_soggetto_componente(p_soggetto) = 1
         and not utente_gruppo.existsId ( p_utente , 'GRPLDAP')  then
         -- Rev. 40 fine
          --creo utente per un componente metto autenticazione Ldap
            utente_gruppo.ins(p_utente,'GRPLDAP');
          end if;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END ins;
   FUNCTION get_tipo_utente
/******************************************************************************
 NOME:        GET_TIPO_UTENTE.
 DESCRIZIONE: Dato l'utente ritorna il TIPO.
 ARGOMENTI:   p_utente:     codice dell'utente.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 7    27/10/2006 MM     Prima emissione.
******************************************************************************/
   (p_utente_o_gruppo IN VARCHAR2)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_utente   VARCHAR2 (8);
   BEGIN
      SELECT tipo_utente
        INTO d_utente
        FROM utenti
       WHERE utente = p_utente_o_gruppo;
      RETURN d_utente;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END get_tipo_utente;
   FUNCTION get_id_utente
/******************************************************************************
 NOME:        GET_ID_UTENTE.
 DESCRIZIONE: Dato l'utente ritorna il suo id.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN NUMBER
   IS /* SLAVE_COPY */
      d_id_utente   number(10);
   BEGIN
      SELECT id_utente
        INTO d_id_utente
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_id_utente;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_id_utente;
   FUNCTION get_nominativo
/******************************************************************************
 NOME:        GET_NOMINATIVO.
 DESCRIZIONE: Dato l'utente ritorna il suo nominativo.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_nominativo   utenti.nominativo%TYPE;
   BEGIN
      SELECT nominativo
        INTO d_nominativo
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_nominativo;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_nominativo;
   FUNCTION get_password
/******************************************************************************
 NOME:        GET_PASSWORD.
 DESCRIZIONE: Dato l'utente ritorna la sua password (criptata).
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_password   utenti.password%TYPE;
   BEGIN
      SELECT password
        INTO d_password
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_password;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_password;
   FUNCTION get_data_password
/******************************************************************************
 NOME:        GET_DATA_PASSWORD.
 DESCRIZIONE: Dato l'utente ritorna la data di ultima modifica della sua
              password come stringa in formato dd/mm/yyyy.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
 17   11/08/2010 MM     Data estratta come stringa
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_data_password   varchar2(10);
   BEGIN
      SELECT TO_CHAR(data_password, 'DD/MM/YYYY')
        INTO d_data_password
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_data_password;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_data_password;
   FUNCTION get_rinnovo_password
/******************************************************************************
 NOME:        GET_RINNOVO_PASSWORD.
 DESCRIZIONE: Dato l'utente, restituisce:
                 SI se l'utente puo' rinnovare la password,
             NO altrimenti.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_rinnovo_password   utenti.rinnovo_password%TYPE;
   BEGIN
      SELECT rinnovo_password
        INTO d_rinnovo_password
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_rinnovo_password;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_rinnovo_password;
   FUNCTION get_ultimo_tentativo
/******************************************************************************
 NOME:        GET_ULTIMO_TENTATIVO.
 DESCRIZIONE: Dato l'utente ritorna la data del suo ultimo tentativo di accesso
              come stringa in formato dd/mm/yyyy hh24:mi:ss.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
 17   11/08/2010 MM     Data estratta come stringa
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_ultimo_tentativo   varchar2(20);
   BEGIN
      SELECT TO_CHAR(ultimo_tentativo, 'DD/MM/YYYY HH24:MI:SS')
        INTO d_ultimo_tentativo
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_ultimo_tentativo;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_ultimo_tentativo;
   FUNCTION get_numero_tentativi
/******************************************************************************
 NOME:        GET_NUMERO_TENTATIVI.
 DESCRIZIONE: Dato l'utente ritorna il numero dei suoi tentativi di accesso
              falliti.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_numero_tentativi   utenti.numero_tentativi%TYPE;
   BEGIN
      SELECT numero_tentativi
        INTO d_numero_tentativi
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_numero_tentativi;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_numero_tentativi;
   FUNCTION get_stato
/******************************************************************************
 NOME:        GET_STATO.
 DESCRIZIONE: Dato l'utente ritorna lo stato ad esso associato.
              Valori possibili:
                 'U': Attivo,
                 'S': Sospeso,
                 'R'; Revocato,
                 'A': Automatico.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_stato   utenti.stato%TYPE;
   BEGIN
      SELECT stato
        INTO d_stato
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_stato;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_stato;
   FUNCTION get_lingua
/******************************************************************************
 NOME:        GET_LINGUA.
 DESCRIZIONE: Dato l'utente ritorna la lingua ad esso associata.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/05/2003 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_lingua   utenti.lingua%TYPE;
   BEGIN
      SELECT lingua
        INTO d_lingua
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_lingua;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_lingua;
   FUNCTION get_gruppo_lavoro
/******************************************************************************
 NOME:        GET_GRUPPO_LAVORO.
 DESCRIZIONE: Dato l'utente ritorna Il gruppo di lavoro ad esso associato.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/05/2003 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_gruppo_lavoro   utenti.gruppo_lavoro%TYPE;
   BEGIN
      SELECT gruppo_lavoro
        INTO d_gruppo_lavoro
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_gruppo_lavoro;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_gruppo_lavoro;
   FUNCTION get_importanza
/******************************************************************************
 NOME:        GET_IMPORTANZA.
 DESCRIZIONE: Dato l'utente ritorna l'importanza ad esso associata.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/05/2003 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN NUMBER
   IS /* SLAVE_COPY */
     d_importanza   utenti.importanza%TYPE;
   BEGIN
      SELECT importanza
        INTO d_importanza
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_importanza;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_importanza;
   FUNCTION get_note
/******************************************************************************
 NOME:        GET_NOTE.
 DESCRIZIONE: Dato l'utente ritorna le note ad esso associate.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
 19   10/09/2010 SNeg   Tolta slave_copy per get_note per problemi in
                         abilitazione servizi (leggeva su slave info non
                         ancora replicate)
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN VARCHAR2
   IS
     d_note   utenti.note%TYPE;
   BEGIN
      SELECT note
        INTO d_note
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_note;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_note;

   /******************************************************************************
 NOME:        GET_AMMINISTRATORE
 DESCRIZIONE: Dato l'utente ritorna il campo AMMINISTRATORE.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
  REVISIONI:
  Rev. Data       Autore Descrizione
  ---- ---------- ------ ------------------------------------------------------
  41   21/09/2018 SNeg   Introduzione campo
******************************************************************************/

    FUNCTION GET_AMMINISTRATORE
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN utenti.AMMINISTRATORE%TYPE
   IS
     d_AMMINISTRATORE   utenti.AMMINISTRATORE%TYPE;
   BEGIN
      SELECT AMMINISTRATORE
        INTO d_AMMINISTRATORE
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_AMMINISTRATORE;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_AMMINISTRATORE;

    /******************************************************************************
 NOME:        GET_INFO_IDENTIFICAZIONE
 DESCRIZIONE: Dato l'utente ritorna il campo INFO_IDENTIFICAZIONE.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
  REVISIONI:
  Rev. Data       Autore Descrizione
  ---- ---------- ------ ------------------------------------------------------
  41   21/09/2018 SNeg   Introduzione campo
******************************************************************************/


    FUNCTION GET_INFO_IDENTIFICAZIONE
   ( p_utente IN VARCHAR2
   , p_initialize IN VARCHAR2 DEFAULT 'N'
   , p_error_msg IN NUMBER DEFAULT 1)
    RETURN utenti.INFO_IDENTIFICAZIONE%TYPE
   IS
     d_INFO_IDENTIFICAZIONE   utenti.INFO_IDENTIFICAZIONE%TYPE;
   BEGIN
      SELECT INFO_IDENTIFICAZIONE
        INTO d_INFO_IDENTIFICAZIONE
        FROM utenti
       WHERE utente = p_utente;
      RETURN d_INFO_IDENTIFICAZIONE;
   EXCEPTION
      WHEN NO_DATA_FOUND then
      IF p_error_msg = 1 THEN
         raise_application_error (-20999, 'Utente inesistente.');
      ELSE
         RETURN '';
      END IF;
      WHEN OTHERS THEN
         IF p_error_msg = 1 THEN
            RAISE;
         ELSE
            RETURN '';
         END IF;
   END get_INFO_IDENTIFICAZIONE;

   FUNCTION get_soggetto
/******************************************************************************
 NOME:        GET_SOGGETTO.
 DESCRIZIONE: Dato l'utente ritorna il numero individuale dell'eventuale
              registrazione anagrafica associata.
 ARGOMENTI:   p_utente:     codice dell'utente.
              p_initialize: se passato con valore 'Y', effettua anche
                            l'inizializzazione di tutti gli attributi
                            dell'utente.
              p_error_msg:  1 se si vuole che sia segnalato un errore in caso
                              l'utente passato non esista;
                            0 se si vuole che sia tornato un valore nullo in caso
                              l'utente passato non esista.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 3    06/02/2004 MM     Introduzione del parametro p_error_msg.
******************************************************************************/
   (
      p_utente       IN   VARCHAR2,
      p_initialize   IN   VARCHAR2 DEFAULT 'N',
      p_error_msg    IN   NUMBER DEFAULT 1
   )
      RETURN NUMBER
   IS /* SLAVE_COPY */
     d_soggetto   utenti_soggetti.soggetto%TYPE;
     d_nominativo utenti.nominativo%TYPE;
   BEGIN
      -- verifica esistenza nominativo
      d_nominativo := utente.get_nominativo(p_utente,p_error_msg => p_error_msg );
      SELECT utenti_soggetti.soggetto
           INTO d_soggetto
           FROM utenti utenti, utenti_soggetti utenti_soggetti
          WHERE utenti.utente = utenti_soggetti.utente
            AND utenti.utente = p_utente;
      RETURN d_soggetto;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         return TO_NUMBER (NULL);
      WHEN OTHERS
      THEN
         RAISE;
   END get_soggetto;
   FUNCTION get_utente
/******************************************************************************
 NOME:        GET_UTENTE.
 DESCRIZIONE: Dato un soggetto, ritorna il codice utente associato.
              Se al soggetto non e' associato nessun utente ritorna null; se al
              soggetto sono associati piu' utenti ritorna il minore.
 ARGOMENTI:   p_soggetto:   identificativo del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 7    29/11/2006 MM     Prima emissione.
021  06/06/2011 SNeg Modificata get_utente, in base al registro se solo un utente puo essere
                                 legato allo stesso soggetto ritorna errore.
******************************************************************************/
   (p_soggetto IN NUMBER)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_utente   utenti.utente%TYPE;
      d_utente_solo_1_soggetto registro.stringa%TYPE;
      d_num_utenti number;
   BEGIN
       d_utente_solo_1_soggetto   :=
         upper(registro_utility.leggi_stringa (UPPER ('PRODUCTS/AD4/UTENTE')
                                       , 'SoggettoLegatoSolo_1_Utente'
                                       , 0
         ));
         if d_utente_solo_1_soggetto != 'NO' then
            SELECT nvl(COUNT(utente),0)
                 into d_num_utenti
                 from utenti_soggetti
             WHERE soggetto = p_soggetto;
             if d_num_utenti > 1 then
                raise_application_error (- 20999,'Impossibile determinare univocamente Utente in base al Soggetto, e'' necessario prima risolvere l''ambiguita''');
             end if;
         end if;
      SELECT MIN (utente)
        INTO d_utente
        FROM utenti_soggetti
       WHERE soggetto = p_soggetto;
      RETURN d_utente;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN '';
   END get_utente;
   FUNCTION get_utente
/******************************************************************************
 NOME:        GET_UTENTE.
 DESCRIZIONE: Dato un soggetto, ritorna il codice utente associato.
           Se al soggetto non e' associato nessun utente ritorna null; se al
              soggetto sono associati piu' utenti ritorna il minore.
 ARGOMENTI:   p_soggetto:   identificativo del soggetto.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 7    29/11/2006 MM     Prima emissione.
 36   13/02/2015 SN     Modifica x autenticazione ldap
******************************************************************************/
   (p_nominativo IN VARCHAR2)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_utente   utenti.utente%TYPE;
   BEGIN
      SELECT utente
        INTO d_utente
        FROM utenti
       WHERE nominativo = p_nominativo;
      RETURN d_utente;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
       BEGIN
          SELECT utente
            INTO d_utente
            FROM utenti
           WHERE nominativo like(p_nominativo ||'@%')
             AND not exists (select 1
                               from utenti u
                             WHERE nominativo  like(p_nominativo ||'@%')
                               AND u.utente != utenti.utente);
          RETURN d_utente;
       EXCEPTION
          WHEN NO_DATA_FOUND
          THEN
             RETURN '';
       END;
   END get_utente;
   PROCEDURE set_utente
/******************************************************************************
 NOME:        SET_UTENTE.
 DESCRIZIONE: Modifica l'attributo UTENTE dell'utente corrente.
 ARGOMENTI:   p_utente: codice utente.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 013  16/02/2009 MM     Prima emissione.
******************************************************************************/
   (p_utente IN VARCHAR2)
   IS
   BEGIN
      d_body_utente := p_utente;
   END set_utente;
   PROCEDURE set_id_utente
/******************************************************************************
 NOME:        SET_ID_UTENTE.
 DESCRIZIONE: Modifica l'attributo ID_UTENTE dell'utente corrente.
 ARGOMENTI:   p_id_utente: identificativo utente.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_id_utente IN NUMBER)
   IS
   BEGIN
      d_body_id_utente := p_id_utente;
   END set_id_utente;
   PROCEDURE set_nominativo
/******************************************************************************
 NOME:        SET_NOMINATIVO.
 DESCRIZIONE: Modifica l'attributo NOMINATIVO dell'utente corrente.
 ARGOMENTI:   p_nominativo: nominativo.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_nominativo IN VARCHAR2)
   IS
   BEGIN
      d_body_new_nominativo := UPPER (p_nominativo);
   END set_nominativo;
   FUNCTION is_password_valida
/******************************************************************************
 NOME:        IS_PASSWORD_VALIDA
 DESCRIZIONE: Dato un codice utente verifica che la sua password:
              - sia maggiore della lunghezza minima prevista;
              - contenga almeno un numero se previsto;
              - non contenga dei caratteri speciali se non previsto.
 PARAMETRI:   p_utente        IN varchar2 codice utente di cui verificare la
                                          password
 RITORNA:     number:  ritorno della funzione ICRYPT.IS_PASSWORD_UTENTE_VALIDA;
                       cioe':
                       1   = Verifica effettuata con successo.
                       0   = La password non puo' essere nulla.
                      -1   = La password deve essere di almeno 'p_min_length' caratteri.
                      -2   = La password non puo' contenere caratteri speciali.
                      -3   = La password deve contenere almeno un numero.
 ECCEZIONI:   -20999: Utente '<p_utente>' non definito.
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  009 13/07/2007 MM    Creazione.
******************************************************************************/
   (p_utente VARCHAR2)
      RETURN NUMBER
   IS /* SLAVE_COPY */
   BEGIN
      RETURN caratteristica_accesso.is_password_valida (p_utente);
   END is_password_valida;
   PROCEDURE check_password
/******************************************************************************
 NOME:        CHECK_PASSWORD.
 DESCRIZIONE: Esegue i controlli di validita' sulla nuova password da associare
              all'utente passato.
 ARGOMENTI:   p_password:     nuova password NON CRIPTATA.
              p_old_password: password attuale NON CRIPTATA.
              p_utente:       codice utente da verificare.
 ECCEZIONI:   -20999 'Password attuale ERRATA !
                     'Nuova Password uguale a Password attuale !'
                     'La password deve essere di almeno x caratteri.'
                     'La password non puo' contenere caratteri speciali.'
                     'La password deve contenere almeno un numero.'
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 6    10/01/2006 MM     Prima emissione.
 009  13/07/2007 MM     Non verifica in caso di utente non esistente (per
                        inserimento password di nuovi utenti da applicativo).
                        Verifica validita' della password tramite
                        crypt.IS_PASSWORD_VALIDA.
 042 16/10/2018 SN  Verificata password riutilizzata prima dei giorni previsti
 054 27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
                       (modificato quanto introdotto precedentemente con indicazione md5)
 060 12/02/2021 SN   Allargare il campo per la gestione della password con i nuovi algoritmi #48221
******************************************************************************/
   (
      p_utente         IN   VARCHAR2,
      p_password       IN   VARCHAR2,
      p_old_password   IN   VARCHAR2
   )
   IS /* SLAVE_COPY */
      d_pwd          utenti.password%TYPE; -- rev. 060
      d_pwd_old      utenti.password%TYPE; -- rev. 060
      d_nominativo   VARCHAR2 (100);
   BEGIN
      --  009  13/07/2007 MM     Non verifica in caso di utente non esistente
      BEGIN
         SELECT nominativo
           INTO d_nominativo
           FROM utenti
          WHERE utente = p_utente;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_nominativo := '';
         WHEN OTHERS
         THEN
            RAISE;
      END;
      --  009  13/07/2007 MM    fine mod.
      IF d_nominativo IS NOT NULL
      THEN
         d_pwd := p_password;
         d_pwd_old := p_old_password;
----------------------------------------------------------------------
--    verifica se e' previsto che la password sia case sensitive
----------------------------------------------------------------------
         IF LOWER (registro_utility.leggi_stringa ('PRODUCTS/AUTHENTICATION',
                                                   'PwdCase',
                                                   0
                                                  )
                  ) <> 'any'
         THEN
            d_pwd := UPPER (d_pwd);
            d_pwd_old := UPPER (d_pwd_old);
         END IF;
         IF NVL (p_old_password, ' ') <> 'da non verificare'
         THEN
----------------------------------------------------------------------
--     verifica che la password attuale passata sia corretta
----------------------------------------------------------------------
            IF crypt.verifica_password (d_nominativo, p_old_password) <> 1
            THEN
               raise_application_error (-20999, 'Password attuale ERRATA !');
            END IF;
----------------------------------------------------------------------
--  verifica che la nuova password sia diversa dalla password attuale
----------------------------------------------------------------------
            IF NVL (d_pwd_old, ' ') = NVL (d_pwd, ' ')
            THEN
               raise_application_error
                                (-20999,
                                 'Nuova Password uguale a Password attuale !'
                                );
            END IF;
         END IF;
----------------------------------------------------------------------
--   verifica che la nuova password sia lunga almeno quanto la
--   lunghezza minima prevista, se puo' contenere caratteri speciali
--   e se deve contenere almeno un numero.
----------------------------------------------------------------------
--  009 13/07/2007 MM Verifica validita' password con crypt.IS_PASSWORD_VALIDA
         DECLARE
            d_is_valid     INTEGER;
            d_minlungpwd   INTEGER;
            d_numobbl      NUMBER (1);
            d_carspec      NUMBER (1);
            d_crypt_algoritm utenti_salt.algoritmo%TYPE;
         BEGIN
            d_minlungpwd :=
                  NVL (caratteristica_accesso.get_minpwdlength (p_utente), 0);
            d_numobbl :=
                    NVL (caratteristica_accesso.is_num_obb_pwd (p_utente), 0);
            d_carspec :=
               NVL (caratteristica_accesso.is_car_speciali_pwd (p_utente), 0);
            d_is_valid :=
               crypt.is_password_valida (d_pwd,
                                         d_minlungpwd,
                                         d_numobbl,
                                         d_carspec
                                        );
--            d_pwd := crypt.crypt_password(utenti_salt_tpk.get_salt(p_utente) || d_pwd, utenti_salt_tpk.get_salt(p_utente))
            if d_is_valid = 1 then
                UTENTI_SALT_PKG.sistema_password(p_utente,d_pwd,d_crypt_algoritm);
                -- rev. 54 inizio
                 -- aggiornamento tabella utenti_salt
                 crypt.crypt_password (d_pwd, d_crypt_algoritm);
                -- rev. 54 fine
               d_is_valid := caratteristica_accesso.VERIFICA_PASSWORD_STORICO_OK (p_utente, d_pwd);
            end if;
            IF d_is_valid = 0
            THEN
               raise_application_error (-20999,
                                        'La password non puo'' essere nulla.'
                                       );
            ELSIF d_is_valid = -1
            THEN
               raise_application_error
                                     (-20999,
                                         'La password deve essere di almeno '
                                      || d_minlungpwd
                                      || ' caratteri.'
                                     );
            ELSIF d_is_valid = -2
            THEN
               raise_application_error
                       (-20999,
                        'La password non puo'' contenere caratteri speciali.'
                       );
            ELSIF d_is_valid = -3
            THEN
               raise_application_error
                              (-20999,
                               'La password deve contenere almeno un numero.'
                              );
            ELSIF d_is_valid = -4
            THEN
               raise_application_error
                              (-20999,
                               'La password non può essere riutilizzata prima dei giorni previsti.'
                              );
            END IF;
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      --  009 13/07/2007 MM: fine mod.
      END IF;
   END check_password;
   PROCEDURE set_password
/******************************************************************************
 NOME:        SET_PASSWORD.
 DESCRIZIONE: Modifica l'attributo PASSWORD dell'utente corrente.
 ARGOMENTI:   p_password:     nuova password NON CRIPTATA.
              p_old_password: password attuale.
 ECCEZIONI:   -20999 'Password attuale ERRATA !
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 6    05/01/2006 MM     Gestione controlli sulla password passata.
 054 27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
                       (modificato quanto introdotto precedentemente con indicazione md5)
 060 12/02/2021 SN   Allargare il campo per la gestione della password con i nuovi algoritmi #48221
******************************************************************************/
   (
      p_password       IN   VARCHAR2,
      p_old_password   IN   VARCHAR2 DEFAULT 'da non verificare'
   )
   IS
      d_pwd       utenti.password%TYPE; -- rev. 060
      d_pwd_old   utenti.password%TYPE; -- rev. 060
      d_crypt_algoritm utenti_salt.algoritmo%TYPE;
   BEGIN
      d_pwd := p_password;
      d_pwd_old := p_old_password;
--dbms_output.put_line('SET_PASSWORD ('''||p_password||''', '''||p_old_password||''')');
      ----------------------------------------------------------------------
      --   effettua i controlli di validita' sulla password passata.
      ----------------------------------------------------------------------
      BEGIN
         check_password (d_body_utente, p_password, p_old_password);
      EXCEPTION
         WHEN OTHERS
         THEN
            RAISE;
      END;
----------------------------------------------------------------------
--                         cripta la password
----------------------------------------------------------------------
      BEGIN
         IF LOWER
               (NVL
                   (registro_utility.leggi_stringa ('PRODUCTS/AUTHENTICATION',
                                                    'PwdCase',
                                                    0
                                                   ),
                    'upper'
                   )
               ) <> 'any'
         THEN
            d_pwd := UPPER (d_pwd);
         END IF;
        UTENTI_SALT_PKG.sistema_password(d_body_utente,d_pwd,d_crypt_algoritm);
         -- aggiornamento tabella utenti_salt
         crypt.crypt_password (d_pwd, d_crypt_algoritm);

--         end;
      EXCEPTION
         WHEN OTHERS
         THEN
            RAISE;
      END;
----------------------------------------------------------------------
--  modifica la data della password e setta il flag che indica che e'
--  stata modificata la password dell'utente
----------------------------------------------------------------------
      IF NVL (d_pwd, ' ') <> NVL (d_body_password, ' ')
      THEN
         set_data_password (TO_CHAR (SYSDATE, 'dd/mm/yyyy'));
         set_numero_tentativi (0);
         IF si4.utente IS NOT NULL
         THEN
            set_utente_agg (si4.utente);
         END IF;
         d_body_flag_psw := 'Y';
      END IF;
      d_body_password := d_pwd;
      d_body_old_password := p_old_password;
      d_body_new_password := p_password;
   END set_password;
   PROCEDURE set_password_crypted
/******************************************************************************
 NOME:        SET_PASSWORD_CRYPTED
 DESCRIZIONE: Modifica l'attributo PASSWORD dell'utente corrente.
 ARGOMENTI:   p_password: STRINGA contenente la password.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_password IN VARCHAR2)
   IS
   BEGIN
      d_body_password := p_password;
   END set_password_crypted;
   PROCEDURE set_data_password
/******************************************************************************
 NOME:        SET_DATA_PASSWORD.
 DESCRIZIONE: Modifica l'attributo DATA_PASSWORD dell'utente corrente.
 ARGOMENTI:   p_data_password: STRINGA contenente la data della password in
                               formato dd/mm/yyyy.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_data_password IN VARCHAR2)
   IS
   BEGIN
      d_body_data_password := p_data_password;
   END set_data_password;
   PROCEDURE set_rinnovo_password
/******************************************************************************
 NOME:        SET_RINNOVO_PASSWORD.
 DESCRIZIONE: Modifica l'attributo RINNOVO_PASSWORD dell'utente corrente.
 ARGOMENTI:   p_rinnovo_password: possibilita' di rinnovo della password.
                                  Valori possibili: SI/NO.
 ECCEZIONI:   20998, Valore 'Rinnovo Password''non valido.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 6    05/01/2006 MM     Gestione controlli sulla password
******************************************************************************/
   (p_rinnovo_password IN VARCHAR2)
   IS
   BEGIN
      IF p_rinnovo_password <> 'SI' AND p_rinnovo_password <> 'NO'
      THEN
         raise_application_error
            (-20998,
             'Valore ''Rinnovo Password'' non valido. (Valori possibili''SI'',''NO'''
            );
      ELSE
         d_body_rinnovo_password := p_rinnovo_password;
      END IF;
   END set_rinnovo_password;
   PROCEDURE set_ultimo_tentativo
/******************************************************************************
 NOME:        SET_ULTIMO_TENTATIVO.
 DESCRIZIONE: Modifica l'attributo ULTIMO_TENTATIVO dell'utente corrente.
 ARGOMENTI:   p_ultimo_tentativo: STRINGA contenente la data dell'ultimo
                                  tentativo di accesso in formato
                                  dd/mm/yyyy hh24:mi:ss.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_ultimo_tentativo IN VARCHAR2)
   IS
   BEGIN
      d_body_ultimo_tentativo := p_ultimo_tentativo;
   END set_ultimo_tentativo;
   PROCEDURE set_numero_tentativi
/******************************************************************************
 NOME:        SET_NUMERO_TENTATIVI.
 DESCRIZIONE: Modifica l'attributo NUMERO_TENTATIVI dell'utente corrente.
 ARGOMENTI:   p_numero_tentativi: numero di tentativi di accesso falliti.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_numero_tentativi IN NUMBER)
   IS
   BEGIN
      d_body_numero_tentativi := p_numero_tentativi;
   END set_numero_tentativi;
   PROCEDURE set_stato
/******************************************************************************
 NOME:        SET_STATO.
 DESCRIZIONE: Modifica l'attributo STATO dell'utente corrente.
 ARGOMENTI:   p_stato: Stato dell'utente.
                       Valori possibili:
                       'U': Attivo,'S': Sospeso,'R'; Revocato,'A': Automatico.'
 ECCEZIONI:   20998,'Valore 'Stato' non valido.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_stato IN VARCHAR2)
   IS
   BEGIN
      IF p_stato <> 'U' AND p_stato <> 'R' AND p_stato <> 'S'
         AND p_stato <> 'A'
      THEN
         raise_application_error
            (-20998,
             'Valore ''Stato'' non valido. (Valori possibili''U'': Attivo,''S'': Sospeso,''R''; Revocato,''A'': Automatico.)'
            );
      ELSE
         d_body_stato := p_stato;
      END IF;
   END set_stato;
   PROCEDURE set_lingua
/******************************************************************************
 NOME:        SET_LINGUA.
 DESCRIZIONE: Modifica l'attributo LINGUA dell'utente corrente.
 ARGOMENTI:   p_lingua: lingua.
 ECCEZIONI:   20998, Lingua non codificata.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/05/2003 MM     Prima emissione.
******************************************************************************/
   (p_lingua IN VARCHAR2)
   IS
      d_esiste   NUMBER;
   BEGIN
      IF p_lingua IS NOT NULL
      THEN
         BEGIN
            SELECT 1
              INTO d_esiste
              FROM lingue_view
             WHERE lingua = p_lingua;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               raise_application_error (-20998,
                                           'Lingua '
                                        || p_lingua
                                        || ' non codificata.'
                                       );
         END;
      END IF;
      d_body_lingua := NVL (p_lingua, 'I');
   END set_lingua;
   PROCEDURE set_gruppo_lavoro
/******************************************************************************
 NOME:        SET_GRUPPO_LAVORO.
 DESCRIZIONE: Modifica l'attributo GRUPPO_LAVORO dell'utente corrente.
 ARGOMENTI:   p_gruppo_lavoro: gruppo di lavoro.
 ECCEZIONI:   20998, Gruppo di lavoro non codificato.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/05/2003 MM     Prima emissione.
******************************************************************************/
   (p_gruppo_lavoro IN VARCHAR2)
   IS
      d_esiste   NUMBER;
   BEGIN
      IF p_gruppo_lavoro IS NOT NULL
      THEN
         BEGIN
            SELECT 1
              INTO d_esiste
              FROM gruppi_lavoro
             WHERE gruppo_lavoro = p_gruppo_lavoro;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               raise_application_error (-20998,
                                           'Gruppo di lavoro '
                                        || p_gruppo_lavoro
                                        || ' non codificato.'
                                       );
         END;
      END IF;
      d_body_gruppo_lavoro := p_gruppo_lavoro;
   END set_gruppo_lavoro;
   PROCEDURE set_importanza
/******************************************************************************
 NOME:        SET_IMPORTANZA.
 DESCRIZIONE: Modifica l'attributo IMPORTANZA dell'utente corrente.
 ECCEZIONI:   20998, L'importanza deve essere minore di 100.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/05/2003 MM     Prima emissione.
******************************************************************************/
   (p_importanza IN NUMBER)
   IS
   BEGIN
      IF p_importanza > 99
      THEN
         raise_application_error (-20998,
                                  'L''importanza deve essere minore di 100.'
                                 );
      ELSE
         d_body_importanza := p_importanza;
      END IF;
   END set_importanza;
   PROCEDURE set_note
/******************************************************************************
 NOME:        SET_NOTE.
 DESCRIZIONE: Modifica l'attributo NOTE dell'utente corrente.
 ARGOMENTI:   p_note: note.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_note IN VARCHAR2)
   IS
   BEGIN
      d_body_note := p_note;
   END set_note;

/******************************************************************************
 NOME:        SET_AMMINISTRATORE.
 DESCRIZIONE: Modifica l'attributo AMMINISTRATORE dell'utente corrente.
 ARGOMENTI:   p_note: note.
******************************************************************************/
    PROCEDURE SET_AMMINISTRATORE             ( p_AMMINISTRATORE IN VARCHAR2 )
    IS
    BEGIN
    d_body_amministratore := p_AMMINISTRATORE ;
    END;

/******************************************************************************
 NOME:        SET_INFO_IDENTIFICAZIONE
 DESCRIZIONE: Modifica l'attributo INFO_IDENTIFICAZIONE dell'utente corrente.
 ARGOMENTI:   p_note: note.
******************************************************************************/
    PROCEDURE SET_INFO_IDENTIFICAZIONE             ( p_INFO_IDENTIFICAZIONE IN VARCHAR2 )
    IS
    BEGIN
    d_body_info_identificazione := p_INFO_IDENTIFICAZIONE ;
    END;
   PROCEDURE set_utente_agg
/******************************************************************************
 NOME:        SET_UTENTE_AGG.
 DESCRIZIONE: Predispone l'utente di aggiornamento.
 ARGOMENTI:   p_utente_agg: codice dell'utente che effettua l'aggiornamento.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/05/2003 MM     Prima emissione.
******************************************************************************/
   (p_utente_agg IN VARCHAR2)
   IS
   BEGIN
      d_body_utente_agg := p_utente_agg;
   END set_utente_agg;
   PROCEDURE set_pwd_da_modificare
/******************************************************************************
 NOME:        SET_PWD_DA_MODIFICARE.
 DESCRIZIONE: Modifica l'attributo PWD_DA_MODIFICARE dell'utente corrente.
 ARGOMENTI:   p_pwd_da_modificare: PWD_DA_MODIFICARE.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_pwd_da_modificare IN VARCHAR2)
   IS
   BEGIN
      d_body_pwd_da_modificare := p_pwd_da_modificare;
   END set_pwd_da_modificare;
   PROCEDURE set_data_aggiornamento
/******************************************************************************
 NOME:        SET_DATA_AGGIORNAMENTO
 DESCRIZIONE: Predispone la data di aggiornamento dell'utente.
 ARGOMENTI:   p_data_aggiornamento: data di aggiornamento utente come stringa
                                    in formato dd/mm/yyyy.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/05/2003 MM     Prima emissione.
******************************************************************************/
   (p_data_aggiornamento IN VARCHAR2)
   IS
   BEGIN
      d_body_data_agg := p_data_aggiornamento;
   END set_data_aggiornamento;
   PROCEDURE set_data_inserimento
/******************************************************************************
 NOME:        SET_DATA_INSERIMENTO
 DESCRIZIONE: Predispone la data di inserimento dell'utente.
 ARGOMENTI:   p_data_inserimento: data di inserimento utente come stringa in
                                  formato dd/mm/yyyy.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/05/2003 MM     Prima emissione.
******************************************************************************/
   (p_data_inserimento IN VARCHAR2)
   IS
   BEGIN
      d_body_data_ins := p_data_inserimento;
   END set_data_inserimento;
   PROCEDURE set_soggetto
/******************************************************************************
 NOME:        SET_SOGGETTO.
 DESCRIZIONE: Associa all'utente una registrazione anagrafica.
 ARGOMENTI:   p_soggetto: numero individuale.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_soggetto IN NUMBER)
   IS
   BEGIN
      d_body_soggetto := p_soggetto;
      BEGIN
         soggetto.initialize (d_body_soggetto);
      EXCEPTION
         WHEN OTHERS
         THEN
            init;
            RAISE;
      END;
   END set_soggetto;
   PROCEDURE calcola_utente
/******************************************************************************
 NOME:        CALCOLA_UTENTE.
 DESCRIZIONE: Procedura da utilizzare in fase di INSERIMENTO di un nuovo utente.
              Calcola l'id e/o il codice utente.
           Passi Eseguiti:
                 Se viene passato l'utente e non l'id,
                    calcola il primo id libero e lo restituisce in p_id_utente;
                 se non viene passato ne' l'utente ne' l'id,
                    calcola il primo id libero per cui non esiste un utente che
                    abbia come codice l'id calcolato, lo restituisce in
                    p_id_utente e riempie p_utente con p_id_utente;
                 se viene passato l'id e non l'utente,
                    riempie p_utente con p_id_utente;
                 se vengono passati entrambi,
                    non fa nulla.'
 ARGOMENTI:   p_utente:    codice utente.
              p_id_utente: identificativo utente.
 ECCEZIONI:   -
 ANNOTAZIONI: Utilizzata da UTENTI_TIU.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (
      p_utente      IN OUT   VARCHAR2,
      p_id_utente   IN OUT   NUMBER
   )
   IS
       ok   INTEGER;
      i    INTEGER;
   BEGIN
      p_utente := RTRIM (p_utente);
--      IF p_id_utente IS NULL
--      THEN
 /******************************************************/
/*                Calcolo ID_UTENTE                   */
 /******************************************************/
         IF p_utente IS NOT NULL
         THEN
            -- calcola il primo id libero e lo restituisce in p_id_utente.
            BEGIN
               p_id_utente := si4.next_id ('UTENTI', 'ID_UTENTE', NULL, 0);
            EXCEPTION
               WHEN OTHERS
               THEN
                  RAISE;
            END;
            IF p_utente is null THEN -- lo devo calcolare altrimenti tengo quello indicato
--             nuova parte x calcolo
--             provo a mettere come utente quello che ho come id_utente
--             se occupato incremento il contatore fino a trovarne uno libero.
            ok := 0;
            i := 0;
            WHILE ok = 0
            LOOP
                if utente.exists_utente (p_id_utente + i) = 0 then
                   p_utente := p_id_utente + i;
                   ok := 1;
                else
                   i := i + 1;
                end if;
            END LOOP;
            p_utente := p_id_utente;
            END IF;
--         ELSE
--            -- calcola il primo id libero per cui non esiste un utente
--            -- che abbia come codice l'id calcolato, lo restituisce in
--            -- p_id_utente e riempie p_utente con p_id_utente;
--            ok := 0;
--            i := 0;
--            WHILE ok = 0
--            LOOP
--               i := i + 1;
--               SELECT NVL (MAX (id_utente), 0) + i
--                 INTO p_id_utente
--                 FROM utenti;
--               BEGIN
--                  SELECT 0
--                    INTO ok
--                    FROM utenti
--                   WHERE utente = TO_CHAR (p_id_utente);
--               EXCEPTION
--                  WHEN NO_DATA_FOUND
--                  THEN
--                     ok := 1;
--               END;
--            END LOOP;
--            p_utente := p_id_utente;
--         END IF;
--      ELSIF p_utente IS NULL
--      THEN
         -- riempie p_utente con p_id_utente.
--         p_utente := p_id_utente;
      END IF;
   END calcola_utente;
   PROCEDURE update_soggetto
/******************************************************************************
 NOME:        UPDATE_SOGGETTO.
 DESCRIZIONE: Inserisce o aggiorna gli attributi della registrazione anagrafica
              da associare all'utente corrente.
           Verifica che il soggetto non sia gia' associato a diverso utente,
           quindi, associa la registrazione anagrafica all'utente corrente
           inserendo o aggiornando la tabella UTENTI_SOGGETTI.
 ARGOMENTI:   p_modifica_sogg: Totale (T) o Parziale (P).
                            Se totale:
                                  tutti i valori passati vengono aggiornati
                                  indipendentemente   dal fatto che siano nulli o
                                  meno,
                            altrimenti:
                               aggiorna i soli dati significativi (not null).
                            Default: 'T'.
 ECCEZIONI:   -20999: Dati anagrafici gia' associati ad un diverso utente.
 ANNOTAZIONI: procedure PRIVATA.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_modifica_sogg IN VARCHAR2 DEFAULT 'T')
   IS
      dep_soggetto   NUMBER;
   BEGIN
      BEGIN
         soggetto.update_soggetto (d_body_soggetto, p_modifica_sogg);
      EXCEPTION
         WHEN OTHERS
         THEN
            RAISE;
      END;
      IF d_body_soggetto IS NOT NULL
      THEN
         -- verifica che il soggetto non sia gia' associato a diverso utente.
         BEGIN
            SELECT COUNT (1)
              INTO dep_soggetto
              FROM utenti_soggetti
             WHERE soggetto = d_body_soggetto AND utente <> d_body_utente;
            IF dep_soggetto > 0
            THEN
               raise_application_error
                     (-20999,
                      'Dati anagrafici gia'' associati ad un diverso utente.'
                     );
            END IF;
         END;
         INSERT INTO utenti_soggetti
                     (utente, soggetto)
            SELECT d_body_utente, d_body_soggetto
              FROM DUAL
             WHERE NOT EXISTS (SELECT 1
                                 FROM utenti_soggetti
                                WHERE utente = d_body_utente);
         IF SQL%ROWCOUNT = 0
         THEN
            UPDATE utenti_soggetti
               SET soggetto = d_body_soggetto
             WHERE utente = d_body_utente;
         END IF;
      END IF;
   END update_soggetto;
   PROCEDURE verifica_utente
/******************************************************************************
 NOME:        VERIFICA_UTENTE.
 DESCRIZIONE: Verifica che il nominativo passato non sia gia' utilizzato da
              diverso utente.
              Se esiste utente con stesso nominativo e si sta inserendo un
           nuovo utente:
                 se l'utente ha gia' dei diritti di accesso
                esce con errore,
             altrimenti
                se esiste un soggetto associato e i dati non corrispondono
                  esce con errore,
               altrimenti
                  restituisce in 'p_utente', il codice trovato.
 ARGOMENTI:   p_utente:         codice dell'utente.
              p_nominativo:     nominativo dell'utente.
              p_nome:           nome del soggetto.
              p_codice_fiscale: codice fiscale del soggetto.
              p_comune_nas:     codice del comune di nascita del soggetto.
              p_provincia_nas:  codice della provincia di nascita del soggetto.
              p_data_nascita:   data di nascita del soggetto come STRINGA in
                                formato dd/mm/yyyy.
              p_sesso:          sesso del soggetto.
                                Valori possibili: M (Maschio) / F (Femmina).
 ECCEZIONI:   -20998: Impossibile associare ad un Utente un Nominativo vuoto.
              -20997: Errore in accesso a UTENTI in fase di registrazione di
                      'p_nominativo'.
              -20996: Errore in accesso a DIRITTI_ACCESSO in fase di registrazione
                      di 'p_nominativo'.
              -20995: Nominativo gia' in uso. Impossibile associare all' Utente il
                      Nominativo 'p_nominativo'.
 ANNOTAZIONI: procedure PRIVATA.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 7    23/10/2006 MM     Gestione registrazione "lite".
******************************************************************************/
   (
      p_utente           OUT      VARCHAR2,
      p_nominativo       IN       VARCHAR2,
      p_nome             IN       VARCHAR2,
      p_codice_fiscale   IN       VARCHAR2,
      p_comune_nas       IN       NUMBER,
      p_provincia_nas    IN       NUMBER,
      p_data_nascita     IN       VARCHAR2,
      p_sesso            IN       VARCHAR2
   )
   IS /* SLAVE_COPY */
      d_utente_stesso_nominativo   VARCHAR2 (8);
      d_sogg_stesso_nominativo     NUMBER;
      dcontrollo                   NUMBER;
      derrore                      BOOLEAN;
   BEGIN
     IF p_nominativo IS NULL
      THEN
         p_utente := TO_CHAR (NULL);
         raise_application_error
                   (-20998,
                    'Impossibile associare ad un Utente un Nominativo vuoto.'
                   );
      ELSE
         derrore := FALSE;
         d_utente_stesso_nominativo := '';
         /*-----------------------------------------------------
                ESISTE UTENTE con STESSO NOMINATIVO??
         -----------------------------------------------------*/
         BEGIN
            SELECT utente
              INTO d_utente_stesso_nominativo
              FROM utenti
             WHERE nominativo = p_nominativo
               AND utente <> NVL (d_body_utente, 'xxxxxxxx');
            dcontrollo := 1;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               dcontrollo := 0;
            WHEN OTHERS
            THEN
               raise_application_error
                  (-20997,
                      'Errore in accesso a UTENTI in fase di registrazione di '
                   || p_nominativo
                   || '.'
                  );
         END;
-- Rev. 7    23/10/2006 MM     Gestione registrazione "lite".
-- Se esiste utente con stesso nominativo, effettua successivi controlli solo se sono
-- stati passati anche dati anagrafici.
--         IF dControllo = 1 THEN -- esiste utente con stesso nominativo.
         IF dcontrollo = 1 AND p_codice_fiscale IS NOT NULL
         THEN                          -- esiste utente con stesso nominativo.
-- Rev. 7    23/10/2006 MM fine mod.
         /*-----------------------------------------------------
                         siamo in INSERIMENTO??
         -----------------------------------------------------*/
            IF d_body_utente IS NULL
            THEN                                              -- inserimento.
               dcontrollo := 0;
               /*-----------------------------------------------------
                         ha GIA' dei DIRITTI di ACCESSO??
               -----------------------------------------------------*/
               BEGIN
                  SELECT COUNT (1)
                    INTO dcontrollo
                    FROM diritti_accesso
                   WHERE utente = d_utente_stesso_nominativo;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     raise_application_error
                        (-20996,
                            'Errore in accesso a DIRITTI_ACCESSO in fase di registrazione di '
                         || p_nominativo
                         || '.'
                        );
               END;
               IF dcontrollo = 0
               THEN                        -- non esistono diritti di accesso.
                  /*-----------------------------------------------------
                              ha GIA' un SOGGETTO ASSOCIATO???
                  -----------------------------------------------------*/
                  BEGIN
                     SELECT soggetto
                       INTO d_sogg_stesso_nominativo
                       FROM utenti_soggetti
                      WHERE utente = d_utente_stesso_nominativo;
                     dcontrollo := 1;
                  EXCEPTION
                     WHEN NO_DATA_FOUND
                     THEN
                        dcontrollo := 0;
                     WHEN OTHERS
                     THEN
                        raise_application_error
                           (-20996,
                               'Errore in accesso a UTENTI_SOGGETTI in fase di registrazione di '
                            || p_nominativo
                            || '.'
                           );
                  END;
                  IF dcontrollo > 0
                  THEN
                     /*-----------------------------------------------------
                              il SOGGETTO ASSOCIATO e' lo STESSO???
                     -----------------------------------------------------*/
                     DECLARE
                        dnome           VARCHAR2 (100);
                        dsesso          VARCHAR2 (1);
                        dcodfis         VARCHAR2 (16);
                        dcomunenas      NUMBER;
                        dprovincianas   NUMBER;
                        ddatanas        VARCHAR2 (10);
                     BEGIN
                        soggetto.initialize (d_sogg_stesso_nominativo);
                        dnome := soggetto.get_nome (d_sogg_stesso_nominativo);
                        dsesso :=
                                soggetto.get_sesso (d_sogg_stesso_nominativo);
                        dcomunenas :=
                           soggetto.get_comune_nas (d_sogg_stesso_nominativo);
                        dprovincianas :=
                           soggetto.get_provincia_nas
                                                    (d_sogg_stesso_nominativo);
                        ddatanas :=
                           soggetto.get_data_nascita
                                                    (d_sogg_stesso_nominativo);
                        dcodfis :=
                           soggetto.get_codice_fiscale
                                                    (d_sogg_stesso_nominativo);
                        IF     UPPER (REPLACE (dnome, ' ', '')) =
                                            UPPER (REPLACE (p_nome, ' ', ''))
                           AND UPPER (dsesso) = UPPER (p_sesso)
                           AND dcomunenas = p_comune_nas
                           AND dprovincianas = p_provincia_nas
                           AND ddatanas = p_data_nascita
                           AND UPPER (dcodfis) = UPPER (p_codice_fiscale)
                        THEN
                           p_utente := d_utente_stesso_nominativo;
                           dcontrollo := 0;
                        END IF;
                     EXCEPTION
                        WHEN OTHERS
                        THEN
                           raise_application_error
                              (-20996,
                                  'Errore in accesso a SOGGETTI in fase di registrazione di '
                               || p_nominativo
                               || '.'
                              );
                     END;
                  END IF;
               END IF;
            END IF;
         END IF;
         derrore := (dcontrollo > 0);
         IF derrore
         THEN
            raise_application_error
               (-20995,
                   'Nominativo gia'' in uso. Impossibile associare all'' Utente il Nominativo '
                || p_nominativo
                || '.'
               );
         END IF;
      END IF;
   END verifica_utente;
   PROCEDURE upd_utente
/******************************************************************************
 NOME:        UPDATE_UTENTE.
 DESCRIZIONE: Effettua insert o update di utenti, inserisce eventuale anagrafica
              associata e crea legame tra utente ed anagrafica.
              Restituisce in 'p_utente' il codice dell'utente inserito od
              aggiornato.
 ARGOMENTI:   p_utente:        codice dell'utente inserito.
              p_modifica_sogg: Totale (T), Parziale (P) o da non modificare (N).
                               Se totale:
                                     tutti i valori passati vengono aggiornati
                                     indipendentemente   dal fatto che siano nulli o
                                     meno,
                               se Parziale:
                                  aggiorna i soli dati significativi (not null),
                               se da non modificare (N):
                                  non vengono apportate modifiche al soggetto.
 ECCEZIONI:   -20998: Impossibile associare ad un Utente un Nominativo vuoto.
              -20999: Utente 'd_body_nominativo' non inserito.
                      Utente 'd_body_nominativo' non aggiornato.
                      Parametro ''p_modifica_sogg'' non valido.
                      Valori possibili: Totale (''T''), Parziale (''P'') o da
                      non modificare (''N'').
 ANNOTAZIONI: Chiama CALCOLA_UTENTE e UPDATE_SOGGETTO.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 1    23/05/2003 MM     Gestione campi lingua, gruppo di lavoro ed importanza.
 12   30/07/2008 SN     Memorizzazione nuova password in ad4 solo se voce di
                        registro SavePassword
22  31/08/2011 SNeg Modificata upd_utente per memorizzare la password in ad4 in base alla voce di registro
                                 solo per utenti ldap.
41  21/09/2018  SNeg Aggiunti campi
055 31/03/2020 SN Set utente in inserimento altrimenti non riesce a criptare la password
058 11/07/2020 SN   Gestione errore in caso di aggiornamento o modifica utenti  Feature #42907
060 12/02/2021 SN   Allargare il campo per la gestione della password con i nuovi algoritmi #48221
******************************************************************************/
   (p_utente IN OUT VARCHAR2, p_modifica_sogg IN VARCHAR2)
   IS
      dep_utente      VARCHAR2 (8);
      dep_id_utente   NUMBER;
      d_pwd           utenti.password%TYPE; -- rev. 060
      d_valore        NUMBER        := 0;
   BEGIN
      --raise_application_error (-20999, p_utente || '   ' || p_modifica_sogg);
      integritypackage.LOG ('inizio UPD_UTENTE');
      IF p_modifica_sogg NOT IN ('T', 'P', 'N')
      THEN
         raise_application_error
            (-20999,
             'Parametro ''p_modifica_sogg'' non valido. Valori possibili: Totale (''T''), Parziale (''P'') o da non modificare (''N'').'
            );
      END IF;
      IF d_body_new_nominativo IS NULL
      THEN
         p_utente := TO_CHAR (NULL);
         raise_application_error
                   (-20998,
                    'Impossibile associare ad un Utente un Nominativo vuoto.'
                   );
      ELSE
         IF d_body_utente IS NULL OR d_body_id_utente IS NULL
            OR exists_utente(d_body_utente) = 0 -- rev. 55
         THEN
            /*-----------------------------------------------------
                       INSERIMENTO nuovo utente.
            -----------------------------------------------------*/
            dep_utente := p_utente; -- modifica SN 8/5/2012 p_utente;
            dep_id_utente := d_body_id_utente;
            BEGIN
               calcola_utente (dep_utente, dep_id_utente);
            EXCEPTION
               WHEN OTHERS
               THEN
                  p_utente := TO_CHAR (NULL);
                  RAISE;
            END;
            BEGIN
               integritypackage.LOG ('UPD_UTENTE insert');
               INSERT INTO utenti
                           (utente, nominativo,
                            PASSWORD,
                            data_password,
                            rinnovo_password,
                            ultimo_tentativo,
                            numero_tentativi, tipo_utente,
                            note, id_utente,
                            stato,
                            lingua, gruppo_lavoro,
                            importanza, utente_aggiornamento,
                            data_aggiornamento,
                            data_inserimento,
                            amministratore,
                            info_identificazione
                           )
                    VALUES (dep_utente, d_body_new_nominativo,
                            d_body_password,
                            TO_DATE (NVL (d_body_data_password,
                                          TO_CHAR (SYSDATE, 'dd/mm/yyyy')
                                         ),
                                     'dd/mm/yyyy'
                                    ),
                            NVL (d_body_rinnovo_password, 'SI'),
                            TO_DATE (d_body_ultimo_tentativo,
                                     'dd/mm/yyyy hh24:mi:ss'
                                    ),
                            NVL (d_body_numero_tentativi, 0), 'U',
                            d_body_note, dep_id_utente,
                            NVL (d_body_stato, 'S'),
                            NVL (d_body_lingua, 'I'), d_body_gruppo_lavoro,
                            d_body_importanza, d_body_utente_agg,
                            TO_DATE (d_body_data_agg, 'dd/mm/yyyy hh24:mi:ss'),
                            TO_DATE (d_body_data_ins, 'dd/mm/yyyy hh24:mi:ss'),
                            d_body_amministratore,
                            d_body_info_identificazione
                           );
            EXCEPTION
               WHEN OTHERS
               THEN
                  p_utente := TO_CHAR (NULL);

                -- rev.58 inizio
                  if to_number('-' || substr(sqlerrm,5,5)) between -20999 and -20000 then
                  raise_application_error (
                  '-' || substr(sqlerrm,5,5) ,
                    substr (SQLERRM, 10)
                                           || CHR (10)
                                           ||
                                              'Utente '''
                                           || d_body_new_nominativo
                                           || ''' non inserito.('||d_body_utente_agg||')'

                                          );
                 else
                 raise_application_error ( -20999 ,
                    replace (SQLERRM,'ORA-','')
                                           ||
                                              'Utente '''
                                           || d_body_new_nominativo
                                           || ''' non inserito.('||d_body_utente_agg||')'
                                           || CHR (10)
                                          );
                 end if;
                 -- rev.58 fine
            END;
            d_body_utente := dep_utente;
            d_body_id_utente := dep_id_utente;
            d_body_data_password :=
                   NVL (d_body_data_password, TO_CHAR (SYSDATE, 'dd/mm/yyyy'));
            d_body_rinnovo_password := NVL (d_body_rinnovo_password, 'SI');
            d_body_numero_tentativi := NVL (d_body_numero_tentativi, 0);
            d_body_stato := NVL (d_body_stato, 'U');
            d_body_lingua := NVL (d_body_lingua, 'I');
         ELSE
            /*-----------------------------------------------------
                       AGGIORNAMENTO utente esistente.
            -----------------------------------------------------*/
            DECLARE
               d_registro_savepassword   registro.valore%TYPE;
            BEGIN
               d_registro_savepassword :=
                  NVL
                     (LOWER
                         (registro_utility.leggi_stringa
                                                       ('PRODUCTS/LDAPCONFIG',
                                                        'SavePassword',
                                                        0
                                                       )
                         ),
                      'yes'
                     );
               UPDATE utenti
                  SET nominativo = d_body_new_nominativo,
                      PASSWORD =
                      DECODE (caratteristica_accesso.is_ldapuser (d_body_utente),1
                          ,decode(d_registro_savepassword,
                                 'yes', d_body_password,
                                 PASSWORD),d_body_password
                                ),
                      data_password =
                                  TO_DATE (d_body_data_password, 'dd/mm/yyyy'),
                      rinnovo_password = d_body_rinnovo_password,
                      ultimo_tentativo =
                         TO_DATE (d_body_ultimo_tentativo,
                                  'dd/mm/yyyy hh24:mi:ss'
                                 ),
                      numero_tentativi = d_body_numero_tentativi,
                      note = d_body_note,
                      id_utente = d_body_id_utente,
                      stato = d_body_stato,
                      lingua = NVL (d_body_lingua, 'I'),
                      gruppo_lavoro = d_body_gruppo_lavoro,
                      importanza = d_body_importanza,
                      utente_aggiornamento = d_body_utente_agg,
                      data_aggiornamento = SYSDATE,
                      amministratore = d_body_amministratore,
                      info_identificazione = d_body_info_identificazione
                WHERE utente = d_body_utente;
               IF     d_body_flag_psw = 'Y'
                  AND caratteristica_accesso.is_ldapuser (d_body_utente) = 1
               THEN
                  DECLARE
                     d_release   INTEGER;
                  BEGIN
                     SELECT TO_NUMBER
                               (SUBSTR
                                   (SUBSTR (banner,
                                              INSTR (UPPER (banner),
                                                     'RELEASE')
                                            + 8
                                           ),
                                    1,
                                    DECODE
                                       (INSTR (SUBSTR (banner,
                                                         INSTR (UPPER (banner),
                                                                'RELEASE'
                                                               )
                                                       + 8
                                                      ),
                                               '.'
                                              ),
                                        0, LENGTH
                                               (SUBSTR (banner,
                                                          INSTR
                                                               (UPPER (banner),
                                                                'RELEASE'
                                                               )
                                                        + 8
                                                       )
                                               ),
                                          INSTR
                                              (SUBSTR (banner,
                                                         INSTR (UPPER (banner),
                                                                'RELEASE'
                                                               )
                                                       + 8
                                                      ),
                                               '.'
                                              )
                                        - 1
                                       )
                                   )
                               )
                       INTO d_release
                       FROM v$version
                      WHERE UPPER (banner) LIKE '%ORACLE%';
                     IF d_release < 10
                     THEN
                        d_valore :=
                           si4au.setattributo (d_body_new_nominativo,
                                               d_body_old_password,
                                               'userPassword',
                                               d_body_new_password
                                              );
                     ELSE
                        d_valore :=
                           ldap_utility.setpassword (d_body_new_nominativo,
                                                     d_body_new_password
                                                    );
                     END IF;
                  EXCEPTION
                     WHEN OTHERS
                     THEN
                        d_valore := -1;
                  END;
                  IF d_valore <> 0
                  THEN
                     raise_application_error
                        (-20999,
                            'Il sistema non consente l''aggiornamento della password.'
                         || CHR (10)
                         || REPLACE (SQLERRM, 'ORA-', '')
                        );
                  END IF;
               END IF;
            EXCEPTION
               WHEN OTHERS
               THEN
                  p_utente := TO_CHAR (NULL);
                -- rev.58 inizio
                  if to_number('-' || substr(sqlerrm,5,5)) between -20999 and -20000 then
                  raise_application_error (
                  '-' || substr(sqlerrm,5,5) ,
                    substr (SQLERRM, 10)
                                           || CHR (10)
                                           ||
                                              'Utente '''
                                           || d_body_new_nominativo
                                           || ''' non aggiornato.('||d_body_utente_agg||')'
                                          );
                 else
                 raise_application_error ( -20999 ,
                    replace (SQLERRM,'ORA-','')
                                           ||
                                              'Utente '''
                                           || d_body_new_nominativo
                                           || ''' non aggiornato.('||d_body_utente_agg||')'
                                           || CHR (10)
                                          );
                 end if;
                 -- rev.58 fine
            END;
         END IF;
         /*-----------------------------------------------------
            Eventuale INSERT in SOGGETTI e in UTENTI_SOGGETTI.
         -----------------------------------------------------*/
         IF p_modifica_sogg <> 'N'
         THEN
            BEGIN
               update_soggetto (p_modifica_sogg);
            EXCEPTION
               WHEN OTHERS
               THEN
                  p_utente := TO_CHAR (NULL);
                  RAISE;
            END;
         END IF;
         p_utente := d_body_utente;
      END IF;
   END upd_utente;
   PROCEDURE update_utente_commit
/******************************************************************************
 NOME:        UPDATE_UTENTE.
 DESCRIZIONE: Effettua insert o update di utenti, inserisce eventuale anagrafica
              associata e crea legame tra utente ed anagrafica.
              Restituisce in 'p_utente' il codice dell'utente inserito od
              aggiornato.
 ARGOMENTI:   p_utente:        codice dell'utente inserito.
              p_modifica_sogg: Totale (T), Parziale (P) o da non modificare (N).
                               Se totale:
                                     tutti i valori passati vengono aggiornati
                                     indipendentemente   dal fatto che siano nulli o
                                     meno,
                               se Parziale:
                                  aggiorna i soli dati significativi (not null),
                               se da non modificare (N):
                                  non vengono apportate modifiche al soggetto.
 ECCEZIONI:   -20998: Impossibile associare ad un Utente un Nominativo vuoto.
              -20999: Utente 'd_body_nominativo' non inserito.
                      Utente 'd_body_nominativo' non aggiornato.
                      Parametro ''p_modifica_sogg'' non valido.
                      Valori possibili: Totale (''T''), Parziale (''P'') o da
                      non modificare (''N'').
 ANNOTAZIONI: Chiama CALCOLA_UTENTE e UPDATE_SOGGETTO.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 1    23/05/2003 MM     Gestione campi lingua, gruppo di lavoro ed importanza.
 12   30/07/2008 SN     Memorizzazione nuova password in ad4 solo se voce di
                        registro SavePassword
******************************************************************************/
   (
      p_utente          IN OUT   VARCHAR2,
      p_modifica_sogg   IN       VARCHAR2
   )
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      upd_utente (p_utente, p_modifica_sogg);
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         ROLLBACK;
         RAISE;
   END update_utente_commit;
   PROCEDURE update_utente
/******************************************************************************
 NOME:        UPDATE_UTENTE.
 DESCRIZIONE: Effettua insert o update di utenti, inserisce eventuale anagrafica
              associata e crea legame tra utente ed anagrafica.
 ARGOMENTI:   p_modifica_sogg: Totale (T), Parziale (P) o da non modificare (N).
              Se totale:
                 tutti i valori passati vengono aggiornati indipendentemente
                 dal fatto che siano nulli o meno,
              se Parziale:
                 aggiorna i soli dati significativi (not null),
              se da non modificare (N):
                 non vengono apportate modifiche al soggetto.
 ECCEZIONI: -20999: Parametro ''p_modifica_sogg'' non valido.
                    Valori possibili: Totale (''T''), Parziale (''P'') o da
                    non modificare (''N'').
 ANNOTAZIONI: Lancia la procedure omonima ignorando il parametro di output.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (
      p_utente          IN OUT   VARCHAR2,
      p_modifica_sogg   IN       VARCHAR2
   )
   IS
      d_commit                  NUMBER                 := 0;
      d_registro_savepassword   registro.valore%TYPE;
      d_isldapuser              BOOLEAN                := FALSE;
   BEGIN
      d_registro_savepassword :=
         NVL (LOWER (registro_utility.leggi_stringa ('PRODUCTS/LDAPCONFIG',
                                                     'SavePassword',
                                                     0
                                                    )
                    ),
              'yes'
             );
      IF exists_codice (d_body_utente) = 0
      THEN
         d_isldapuser := TRUE;
      ELSE
         d_isldapuser :=
                        caratteristica_accesso.is_ldapuser (d_body_utente) =
                                                                            1;
      END IF;
      IF     d_body_flag_psw = 'Y'
         AND d_isldapuser
         AND d_registro_savepassword = 'yes'
      THEN
         update_utente_commit (p_utente, p_modifica_sogg);
      ELSE
         upd_utente (p_utente, p_modifica_sogg);
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END update_utente;
   PROCEDURE update_utente
/******************************************************************************
 NOME:        UPDATE_UTENTE.
 DESCRIZIONE: Effettua insert o update di utenti, inserisce eventuale anagrafica
              associata e crea legame tra utente ed anagrafica.
 ARGOMENTI:   p_modifica_sogg: Totale (T), Parziale (P) o da non modificare (N).
              Se totale:
                 tutti i valori passati vengono aggiornati indipendentemente
                 dal fatto che siano nulli o meno,
              se Parziale:
                 aggiorna i soli dati significativi (not null),
              se da non modificare (N):
                 non vengono apportate modifiche al soggetto.
 ECCEZIONI: -20999: Parametro ''p_modifica_sogg'' non valido.
                    Valori possibili: Totale (''T''), Parziale (''P'') o da
                    non modificare (''N'').
 ANNOTAZIONI: Lancia la procedure omonima ignorando il parametro di output.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_modifica_sogg IN VARCHAR2)
   IS
      d_utente   VARCHAR2 (8);
   BEGIN
      update_utente (d_utente, p_modifica_sogg);
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END update_utente;
   PROCEDURE update_utente
/******************************************************************************
 NOME:        UPDATE_UTENTE.
 DESCRIZIONE: Effettua insert o update di utenti, inserisce eventuale anagrafica
              associata e crea legame tra utente ed anagrafica.
 ARGOMENTI:   -
 ECCEZIONI:   -
 ANNOTAZIONI: Lancia la procedure omonima con p_modifica_sogg = 'T'.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   IS
   BEGIN
      update_utente ('T');
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END update_utente;
   PROCEDURE registra_web
/******************************************************************************
 NOME:        REGISTRA_WEB.
 DESCRIZIONE: Registrazione di un utente da un portale.
              Passi Eseguiti:
              - Verifica presenza parametri obbligatori:
                . p_nominativo;
                . p_cognome;
                . p_nome;
                . p_codice_fiscale;
                . p_comune_nas;
                . p_provincia_nas;
                . p_data_nascita;
                . p_sesso;
              - Controllo codice fiscale;
              - Verifica che non esista un utente con stesso nominativo o, se
                esiste gia', che abbia gli stessi dati anagrafici e non abbia
                ancora nessun diritto di accesso.
              - Inserisce o aggiorna l'utente ed il soggetto (per i soli dati
                significativi - not null).
              - Riempie i parametri di output.
           L'utente registrato viene messo in stato SOSPESO.
           In seguito a esplicita conferma dei dati (AGGIORNA_WEB), l'utente
           viene messo 'In Uso'.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente           OUT: codice dell'utente registrato.
              p_mezza_password   OUT: prima meta' della password.
              p_nominativo:           nominativo.
              p_cognome:              cognome del soggetto.
              p_nome:                 nome del soggetto.
              p_codice_fiscale:       codice fiscale del soggetto.
              p_comune        IN OUT: comune di residenza del soggetto.
              p_provincia     IN OUT: provincia di residenza del soggetto.
              p_cap           IN OUT: cap di residenza del soggetto.
              p_indirizzo     IN OUT: indirizzo di residenza del soggetto.
              p_comune_nas:           codice comune di nascita del soggetto.
              p_provincia_nas:        codice provincia di nascita del soggetto.
              p_data_nascita:         data di nascita del soggetto come STRINGA
                                   in formato dd/mm/yyyy.
              p_sesso:                sesso del soggetto.
                                   Valori possibili: M (Maschio) / F (Femmina).
              p_indirizzo_web IN OUT: indirizzo e-mail del soggetto.
              p_telefono      IN OUT: numero di telefono del soggetto.
              p_fax           IN OUT: numero di fax del soggetto.
 ECCEZIONI:   -20999: Parametro obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 7    23/10/2006 MM     Gestione registrazione "lite".
 10   06/02/2008 MM     A25832.0.0: Se un utente si registra e poi, prima che
                        la sua richiesta venga approvata o respinta, si registra
                        nuovamente immettendo gli stessi dati, l'operazione va
                        a buon fine ma il campo della pagina contenente la
                        prima parte della password e vuoto.
 19  10/09/2010 SNeg   Modificati registra_web e aggiorna_web per valorizzare
                        di default la competenza
026 12/04/2012 SNeg tolta modifica 2008 per riproporre seconda parte password
029 09/04/2013 SNeg Tolto il default dello user nella competenza,  metteva lo user
                    su db anziché il progetto
050 02/07/2019 SNeg Impostazioni variabili per indicare che si è in registrazione da web #35539
059 24/09/2020 SN   Togliere modifiche rev.50 in quanto in caso di abilitazione al portale
                    gp4web viene creato un nuovo soggetto e non tenuto quello abbinato al dipendente #44856
060 12/02/2021 SN   Allargare il campo per la gestione della password con i nuovi algoritmi #48221
******************************************************************************/
   (
      p_utente           IN OUT   VARCHAR2,
      p_mezza_password   OUT      VARCHAR2,
      p_nominativo       IN       VARCHAR2,
      p_cognome          IN       VARCHAR2,
      p_nome             IN       VARCHAR2,
      p_codice_fiscale   IN       VARCHAR2,
      p_comune           IN OUT   NUMBER,
      p_provincia        IN OUT   NUMBER,
      p_cap              IN OUT   VARCHAR2,
      p_indirizzo        IN OUT   VARCHAR2,
      p_comune_nas       IN       NUMBER,
      p_provincia_nas    IN       NUMBER,
      p_data_nascita     IN       VARCHAR2,
      p_sesso            IN       VARCHAR2,
      p_indirizzo_web    IN OUT   VARCHAR2,
      p_telefono         IN OUT   VARCHAR2,
      p_fax              IN OUT   VARCHAR2,
      p_competenza       IN       VARCHAR2 default null
   )
   IS
      d_sogg   NUMBER;
      d_nome   VARCHAR2 (100);
   BEGIN
      s_registrazione_da_web_on := 0; -- rev. 59
      /*-----------------------------------------------------
        Svuota gli attributi dell'eventuale utente corrente.
      -----------------------------------------------------*/
      init;
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF TRIM (p_nominativo) IS NULL
      THEN
         raise_application_error (-20999, 'Nominativo Utente obbligatorio.');
      END IF;
      IF TRIM (p_cognome) IS NULL
      THEN
         raise_application_error (-20999, 'Cognome Utente obbligatorio.');
      END IF;
      IF TRIM (p_nome) IS NULL
      THEN
         raise_application_error (-20999, 'Nome Utente obbligatorio.');
      END IF;
-- Rev. 7    23/10/2006 MM     Gestione registrazione "lite": controlla l'obbligatorieta'
-- di Comune, Provincia, Data di nascita, Sesso e verifica la correttezza del codice
-- fiscale solo se passato.
--
--       IF p_codice_fiscale IS NULL THEN
--          RAISE_APPLICATION_ERROR(-20999,'Codice fiscale obbligatorio.');
--       END IF;
      IF TRIM (p_codice_fiscale) IS NOT NULL
      THEN
         IF p_comune_nas IS NULL
         THEN
            raise_application_error (-20999,
                                     'Comune di nascita utente obbligatorio.'
                                    );
         END IF;
         IF p_provincia_nas IS NULL
         THEN
            raise_application_error
                                 (-20999,
                                  'Provincia di nascita utente obbligatoria.'
                                 );
         END IF;
         IF TRIM (p_data_nascita) IS NULL
         THEN
            raise_application_error (-20999,
                                     'Data di nascita utente obbligatoria.'
                                    );
         END IF;
         IF TRIM (p_sesso) IS NULL
         THEN
            raise_application_error (-20999, 'Sesso obbligatorio.');
         END IF;
         /*-----------------------------------------------------
                      Controllo del codice fiscale.
         -----------------------------------------------------*/
         DECLARE
            derror   NUMBER;
            dmsg     VARCHAR2 (2000);
         BEGIN
            derror :=
               codice_fiscale.controllo (p_codice_fiscale,
                                         p_sesso,
                                         p_comune_nas,
                                         p_provincia_nas,
                                         p_data_nascita
                                        );
            IF derror < 0
            THEN
               dmsg := codice_fiscale.get_error_msg (derror);
               raise_application_error (-20900 + derror, dmsg);
            END IF;
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
-- Rev. 7    23/10/2006 MM fine mod.
      /*-----------------------------------------------------
          Verifica che non esista un utente con stesso
          nominativo e se e' stato passato il codice fiscale
          e l'utente esiste gia', che abbia gli stessi dati
          anagrafici e non abbia ancora nessun diritto di
          accesso.
      -----------------------------------------------------*/
      d_nome := RTRIM (p_cognome) || '  ' || RTRIM (p_nome);
      BEGIN
         verifica_utente (p_utente,
                          p_nominativo,
                          d_nome,
                          p_codice_fiscale,
                          p_comune_nas,
                          p_provincia_nas,
                          p_data_nascita,
                          p_sesso
                         );
      EXCEPTION
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
        Se l'utente esisteva gia', lo inizializza,
      altrimenti, setta gli attributi obbligatori per
      l'utente corrente e lo mette in stato 'Sospeso'.
      -----------------------------------------------------*/
      IF p_utente IS NOT NULL
      THEN
         initialize (p_utente);
      ELSE
         if p_nome is not null then -- sto registrando una persona legata all'utente
            p_utente := determina_utente_libero(p_nominativo);
            set_utente (p_utente );
         end if;
         set_nominativo (p_nominativo);
         set_stato ('S');
         soggetto.set_nome (d_nome);
         soggetto.set_codice_fiscale (p_codice_fiscale);
         soggetto.set_comune_nas (p_comune_nas);
         soggetto.set_provincia_nas (p_provincia_nas);
         soggetto.set_data_nascita (p_data_nascita);
         soggetto.set_sesso (p_sesso);
      END IF;
      /*-----------------------------------------------------
          Se l'utente e' nuovo o esiste (senza diritti di
        accesso) ma e' stato sospeso, genera una nuova
        password.
      -----------------------------------------------------*/
--      IF d_body_stato = 'S'
--      THEN
--  se utente esiste ma non ha diritti di accesso probabile non abbia avuto
-- la password, viene rigenerata e data all'utente
         DECLARE
            d_pwd                utenti.password%TYPE; -- rev. 060
            d_seconda_mezza_pwd  utenti.password%TYPE; -- rev. 060
         BEGIN
            BEGIN
               d_pwd := crypt.genera_password (8);
            EXCEPTION
               WHEN OTHERS
               THEN
                  RAISE;
            END;
            p_mezza_password := SUBSTR (d_pwd, 1, LENGTH (d_pwd) / 2);
            d_seconda_mezza_pwd := SUBSTR (d_pwd, (LENGTH (d_pwd) / 2) + 1);
            set_password (d_pwd);
            set_data_password (TO_CHAR (SYSDATE, 'DD/MM/YYYY'));
            set_note ('PWD=' || d_seconda_mezza_pwd);
         END;
--      -- Rev. 10   06/02/2008 MM     A25832.0.0
--      ELSE
--         p_mezza_password := si4.get_stringparm ('UTENTI', 'PWD', p_utente);
--      -- Rev.  10   06/02/2008 MM : fine mod.
--      END IF;
      /*-----------------------------------------------------
        Associa al soggetto corrente gli attributi
      non obbligatori.
      -----------------------------------------------------*/
      soggetto.set_comune (p_comune);
      soggetto.set_provincia (p_provincia);
      soggetto.set_indirizzo (p_indirizzo);
      soggetto.set_cap (p_cap);
      soggetto.set_fax (p_fax);
      soggetto.set_telefono (p_telefono);
      soggetto.set_indirizzo_web (p_indirizzo_web);
      soggetto.set_competenza (p_competenza);
      /*-----------------------------------------------------
        Aggiorna l'utente ed il soggetto (per i soli dati
      significativi - not null).
      -----------------------------------------------------*/
      -- per i controlli per decidere se inserire o meno
      -- il soggetto mi serve sapere utente che si vuole abbinare
      -- rev. 50
      s_utente_registrazione_da_web := p_utente;
      BEGIN
         update_utente_commit (p_utente, 'P');
      EXCEPTION
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
                 Riempie i parametri di output.
      -----------------------------------------------------*/
      d_sogg := utente.get_soggetto (p_utente);
      p_indirizzo := soggetto.get_indirizzo (d_sogg);
      p_cap := soggetto.get_cap (d_sogg);
      p_comune := soggetto.get_comune (d_sogg);
      p_provincia := soggetto.get_provincia (d_sogg);
      p_indirizzo_web := soggetto.get_indirizzo_web (d_sogg);
      p_telefono := soggetto.get_telefono (d_sogg);
      p_fax := soggetto.get_fax (d_sogg);
      -- finita fase di registrazione
      s_utente_registrazione_da_web := NULL;
      s_registrazione_da_web_on := 0;
   EXCEPTION
      WHEN OTHERS
      THEN
         s_utente_registrazione_da_web := NULL;
         s_registrazione_da_web_on := 0;
         RAISE;
   END registra_web;
   PROCEDURE aggiorna_web
/******************************************************************************
 NOME:        AGGIORNA_WEB.
 DESCRIZIONE: Aggiornamento dei dati anagrafici di un utente esistente da un
              portale ed, eventualmente, mette l'utente 'In Uso'.
              Verifica presenza del parametro p_utente.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente:          codice dell'utente.
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
 019   10/09/2010 SNeg   Modificati registra_web e aggiorna_web per valorizzare
                        di default la competenza
029 09/04/2013 SNeg Tolto il default dello user nella competenza,  metteva lo user
                    su db anziché il progetto
******************************************************************************/
   (
      p_utente          IN   VARCHAR2,
      p_comune          IN   NUMBER,
      p_provincia       IN   NUMBER,
      p_cap             IN   VARCHAR2,
      p_indirizzo       IN   VARCHAR2,
      p_indirizzo_web   IN   VARCHAR2,
      p_telefono        IN   VARCHAR2,
      p_fax             IN   VARCHAR2,
      p_utente_in_uso   IN   VARCHAR2,
      p_competenza      IN   VARCHAR2 default null
   )
   IS
      d_utente   VARCHAR2 (8) := p_utente;
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_utente IS NULL
      THEN
         raise_application_error (-20999,
                                  'Parametro ''p_utente'' obbligatorio.'
                                 );
      END IF;
      /*-----------------------------------------------------
          Inizializzazione attributi dell'utente.
      -----------------------------------------------------*/
      initialize (p_utente);
      /*-----------------------------------------------------
                Modifica lo stato dell'utente.
      -----------------------------------------------------*/
      IF NVL (p_utente_in_uso, 'N') = 'Y'
      THEN
         set_stato ('U');
      END IF;
      /*-----------------------------------------------------
        Associa al soggetto associato all'utente passato gli
      attributi significativi.
      -----------------------------------------------------*/
      IF NVL (p_fax, ' ') <> 'NO'
      THEN
         soggetto.set_fax (p_fax);
      END IF;
      IF NVL (p_telefono, ' ') <> 'NO'
      THEN
         soggetto.set_telefono (p_telefono);
      END IF;
      IF NVL (p_indirizzo_web, ' ') <> 'NO'
      THEN
         soggetto.set_indirizzo_web (p_indirizzo_web);
      END IF;
      IF NVL (p_comune, -1) <> 0
      THEN
         soggetto.set_comune (p_comune);
      END IF;
      IF NVL (p_provincia, -1) <> 0
      THEN
         soggetto.set_provincia (p_provincia);
      END IF;
      IF NVL (p_indirizzo, ' ') <> 'NO'
      THEN
         soggetto.set_indirizzo (p_indirizzo);
      END IF;
      IF NVL (p_cap, ' ') <> 'NO'
      THEN
         soggetto.set_cap (p_cap);
      END IF;
      IF NVL (p_competenza, ' ') <> 'NO'
      THEN
         soggetto.set_competenza (p_competenza);
      END IF;
      /*-----------------------------------------------------
               Aggiorna l'utente ed il soggetto .
      -----------------------------------------------------*/
      update_utente_commit (d_utente, 'T');
   EXCEPTION
      WHEN OTHERS
      THEN
         ROLLBACK;
         RAISE;
   END aggiorna_web;
   PROCEDURE aggiorna_web
/******************************************************************************
 NOME:        AGGIORNA_WEB.
 DESCRIZIONE: Aggiornamento dei dati anagrafici di un utente esistente da un
              portale.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente:          codice dell'utente.
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
 ECCEZIONI:   -20999: Parametro 'p_utente' obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              Chiama la funzione omonima senza modificare lo stato dell'utente
           (p_utente_in_uso => 'N').
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (
      p_utente          IN   VARCHAR2,
      p_comune          IN   NUMBER,
      p_provincia       IN   NUMBER,
      p_cap             IN   VARCHAR2,
      p_indirizzo       IN   VARCHAR2,
      p_indirizzo_web   IN   VARCHAR2,
      p_telefono        IN   VARCHAR2,
      p_fax             IN   VARCHAR2
   )
   IS
   BEGIN
      aggiorna_web (p_utente,
                    p_comune,
                    p_provincia,
                    p_cap,
                    p_indirizzo,
                    p_indirizzo_web,
                    p_telefono,
                    p_fax,
                    p_utente_in_uso      => 'N'
                   );
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END aggiorna_web;
   PROCEDURE aggiorna_web
/******************************************************************************
 NOME:        AGGIORNA_WEB.
 DESCRIZIONE: Aggiornamento dei dati anagrafici di un utente esistente da un
              portale.
              Verifica presenza del parametro p_utente.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente:          codice dell'utente.
              p_indirizzo_web:   indirizzo e-mail del soggetto.
                              Per evitare che l'attributo venga
 aggiornato
                         passare 'NO'.
              p_telefono:        numero di telefono del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_fax:             numero di fax del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
 ECCEZIONI:   -20999: Parametro 'p_utente' obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              Chiama la funzione omonima senza i dati di residenza (p_comune => 0,
           p_provincia => 0, p_cap => 'NO').
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (
      p_utente          IN   VARCHAR2,
      p_indirizzo_web   IN   VARCHAR2,
      p_telefono        IN   VARCHAR2,
      p_fax             IN   VARCHAR2
   )
   IS
   BEGIN
      aggiorna_web (p_utente,
                    p_comune             => 0,
                    p_provincia          => 0,
                    p_cap                => 'NO',
                    p_indirizzo          => 'NO',
                    p_indirizzo_web      => p_indirizzo_web,
                    p_telefono           => p_telefono,
                    p_fax                => p_fax
                   );
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END aggiorna_web;
   PROCEDURE aggiorna_web
/******************************************************************************
 NOME:        AGGIORNA_WEB.
 DESCRIZIONE: Aggiornamento dei dati anagrafici di un utente esistente da un
              portale.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente:          codice dell'utente.
              p_indirizzo_web:   indirizzo e-mail del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
              p_telefono:        numero di telefono del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
 ECCEZIONI:   -20999: Parametro 'p_utente' obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              Chiama la funzione omonima senza specificare il numero di fax
              (p_fax => 'NO').
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (
      p_utente          IN   VARCHAR2,
      p_indirizzo_web   IN   VARCHAR2,
      p_telefono        IN   VARCHAR2
   )
   IS
   BEGIN
      aggiorna_web (p_utente, p_indirizzo_web, p_telefono, p_fax => 'NO');
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END aggiorna_web;
   PROCEDURE aggiorna_web
/******************************************************************************
 NOME:        AGGIORNA_WEB.
 DESCRIZIONE: Aggiornamento dei dati anagrafici di un utente esistente da un
              portale.
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_utente:          codice dell'utente.
              p_indirizzo_web:   indirizzo e-mail del soggetto.
                              Per evitare che l'attributo venga aggiornato
                         passare 'NO'.
 ECCEZIONI:   -20999: Parametro 'p_utente' obbligatorio.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              Chiama la funzione omonima senza specificare il numero di telefono
           (p_telefono => 'NO').
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
******************************************************************************/
   (p_utente IN VARCHAR2, p_indirizzo_web IN VARCHAR2)
   IS
   BEGIN
      aggiorna_web (p_utente, p_indirizzo_web, p_telefono => 'NO');
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END aggiorna_web;
   PROCEDURE richiesta_abilitazione
/******************************************************************************
 NOME:        RICHIESTA_ABILITAZIONE.
              Inserimento di una richiesta di abilitazione ad un servizio (Modulo
              - Istanza) da parte di un utente.
              La richiesta viene creata in stato 'Fallita' ('F').
              Sara' la procedure GESTISCI_RICHIESTA che andra' ad associare alla
              richiesta lo stato appropriato ('In carico', 'Abilitata', ...) a
              seconda degli attributi del servizio (Modulo - Istanza) per cui e'
              richiesta l'abilitazione ed effettuera' le operazioni conclusive
              in base ai PARAMETRI_RICHIESTA specifici di ogni singolo servizio.
              Restituisce il numero identificativo della richiesta creata nel
              parametro 'p_id_richiesta'.
              Passi Eseguiti:
              - Verifica presenza campi obbligatori:
                    . p_utente;
                    . p_modulo;
                    . p_istanza;
              - Controllo esistenza utente;
                 - Controllo esistenza modulo;
                 - Controllo esistenza istanza;
                 - Controllo compatibilita' tra modulo ed istanza;
                 - Controllo esistenza diritto di accesso al modulo e all'istanza
                   per cui e' richiesta l'abilitazione da parte dell'utente (NON
                   deve esistere gia').
              - Crea record in RICHIESTE_ABILITAZIONE.
              - Inserisce l'utente nel gruppo degli utenti richiedenti
                un'abilitazione ('ric_abil').
           -----------------------------------------------------
               ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
           -----------------------------------------------------
 ARGOMENTI:   p_id_richiesta OUT: identificativo della richiesta.
              p_utente:           codice utente richiedente l'abilitazione.
              p_modulo:           modulo per cui si richiede l'abilitazione.
              p_istanza:          istanza per cui si richiede l'abilitazione.
 ECCEZIONI:   -20999: Parametro obbligatorio.
              -20999: Utente (<p_utente>) non definito.
              -20999: Modulo (<p_modulo>) non definito.
              -20999: Istanza ('p_istanza') non definita.
              -20999: Istanza (<p_istanza>) e Modulo (<p_modulo>) incompatibili.
              -20999: Utente (<p_utente>) gia' abilitato al servizio
                     (Modulo: <p_modulo> - Istanza: <p_istanza>).
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
030 23/04/2013 SNeg Non consentire registrazione se una richiesta  già in corso.
******************************************************************************/
   (
      p_id_richiesta   OUT      NUMBER,
      p_utente         IN       VARCHAR2,
      p_modulo         IN       VARCHAR2,
      p_istanza        IN       VARCHAR2
   )
   IS
      d_progetto   VARCHAR2 (10);
      d_utente     VARCHAR2 (8);
      d_esiste     NUMBER;
      d_uten_nom   VARCHAR2 (40);
      d_modu_des   VARCHAR2 (40);
      d_ista_des   VARCHAR2 (40);
   BEGIN
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_utente IS NULL
      THEN
         raise_application_error (-20999,
                                  'Parametro ''p_utente'' obbligatorio.'
                                 );
      ELSE
         d_utente := p_utente;
      END IF;
      IF p_modulo IS NULL
      THEN
         raise_application_error (-20999,
                                  'Parametro ''p_modulo'' obbligatorio.'
                                 );
      END IF;
      IF p_istanza IS NULL
      THEN
         raise_application_error (-20999,
                                  'Parametro ''p_istanza'' obbligatorio.'
                                 );
      END IF;
      /*-----------------------------------------------------
                    Controllo esistenza utente.
      -----------------------------------------------------*/
      BEGIN
         SELECT nominativo
           INTO d_uten_nom
           FROM utenti
          WHERE utente = p_utente;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            raise_application_error (-20999,
                                        'Utente ('''
                                     || p_utente
                                     || ''') non definito.'
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
           FROM moduli
          WHERE modulo = p_modulo;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            raise_application_error (-20999,
                                        'Modulo ('''
                                     || p_modulo
                                     || ''') non definito.'
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
           FROM istanze
          WHERE istanza = p_istanza;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            raise_application_error (-20999,
                                        'Istanza ('''
                                     || p_istanza
                                     || ''') non definita.'
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
          Controllo compatibilita' tra modulo ed istanza.
      -----------------------------------------------------*/
      BEGIN
         SELECT progetto
           INTO d_progetto
           FROM istanze
          WHERE istanza = p_istanza
            AND EXISTS (
                       SELECT 1
                         FROM moduli
                        WHERE progetto = istanze.progetto
                              AND modulo = p_modulo);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            raise_application_error (-20999,
                                        'Istanza "'
                                     || d_ista_des
                                     || '" ('''
                                     || p_istanza
                                     || ''') e Modulo "'
                                     || d_modu_des
                                     || '" ('''
                                     || p_modulo
                                     || ''') incompatibili.'
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      /*-----------------------------------------------------
          Controllo esistenza diritto di accesso al modulo
          e all'istanza per cui e' richiesta l'abilitazione
          da parte dell'utente: NON deve esistere gia' a
          meno che il servizio non sia stato definito
          MULTIPLO.
      -----------------------------------------------------*/
      DECLARE
         d_multiplo   VARCHAR2 (1);
      BEGIN
         BEGIN
            d_multiplo := 'N';
            SELECT serv.multiplo
              INTO d_multiplo
              FROM servizi serv
             WHERE serv.modulo = p_modulo AND serv.istanza = p_istanza;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               d_multiplo := 'N';
            WHEN OTHERS
            THEN
               RAISE;
         END;
         IF d_multiplo = 'N'
         THEN
            BEGIN
               d_esiste := 0;
               SELECT 1
                 INTO d_esiste
                 FROM diritti_accesso diac
                WHERE diac.utente = p_utente
                  AND diac.modulo = p_modulo
                  AND diac.istanza = p_istanza;
               IF d_esiste = 1
               THEN
                  raise_application_error
                              (-20999,
                                  'Utente "'
                               || d_uten_nom
                               || '" ('''
                               || p_utente
                               || ''') gia'' abilitato al servizio: Modulo "'
                               || d_modu_des
                               || '" ('''
                               || p_modulo
                               || ''') - Istanza "'
                               || d_ista_des
                               || '" ('''
                               || p_istanza
                               || ''').'
                              );
               END IF;
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  NULL;
               WHEN OTHERS
               THEN
                  RAISE;
            END;
            -- inizio modifica Rev.30
            BEGIN
               d_esiste := 0;
               SELECT max(1)
                 INTO d_esiste
                 FROM richieste_abilitazione riab
                WHERE riab.utente = p_utente
                  AND riab.modulo = p_modulo
                  AND riab.istanza = p_istanza
                  AND stato = 'C' ; -- in carico, richiesta presente
               IF d_esiste = 1
               THEN
                  raise_application_error
                              (-20999,
                                  'Utente "'
                               || d_uten_nom
                               || '" ('''
                               || p_utente
                               || ''') ha una richiesta in corso per il al servizio: Modulo "'
                               || d_modu_des
                               || '" ('''
                               || p_modulo
                               || ''') - Istanza "'
                               || d_ista_des
                               || '" ('''
                               || p_istanza
                               || ''').'
                              );
               END IF;
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  NULL;
               WHEN OTHERS
               THEN
                  RAISE;
            END;
            -- Fine modifica Rev.30
         END IF;
      END;
      /*-----------------------------------------------------
              Crea record in RICHIESTE_ABILITAZIONE.
      -----------------------------------------------------*/
      BEGIN
         BEGIN
            p_id_richiesta :=
                       si4.next_id ('RICHIESTE_ABILITAZIONE', 'ID_RICHIESTA');
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
         INSERT INTO richieste_abilitazione
                     (id_richiesta, utente, modulo, istanza, stato,
                      DATA
                     )
              VALUES (p_id_richiesta, p_utente, p_modulo, p_istanza, 'F',
                      SYSDATE
                     );
      EXCEPTION
         WHEN OTHERS
         THEN
            p_id_richiesta := TO_NUMBER (NULL);
            RAISE;
      END;
      /*-----------------------------------------------------
           Inserisce l'utente nel gruppo degli utenti
         richiedenti un'abilitazione ('ric_abil').
      -----------------------------------------------------*/
      INSERT INTO utenti_gruppo
                  (utente, gruppo)
         SELECT d_utente, 'ric_abil'
           FROM DUAL
          WHERE NOT EXISTS (SELECT 1
                              FROM utenti_gruppo
                             WHERE utente = d_utente AND gruppo = 'ric_abil');
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         ROLLBACK;
         RAISE;
   END richiesta_abilitazione;
   PROCEDURE notifica_richiesta
/******************************************************************************
 NOME:        NOTIFICA_RICHIESTA.
 DESCRIZIONE: Notifica la richiesta identificata da 'p_id_richiesta' se previsto
              il tipo di notifica.
              Attualmente gestisce SOLO MAIL.
              Se lo stato della richiesta e' 'Fallita' ('F'),
                 non fa nulla;
              Se lo stato della richiesta e' 'In Carico' ('C'),
                 la notifica e' prevista se esiste un'azienda che deve confermare
                 i dati dell'individuo che richiede di essere abilitato al
                 servizio in quanto referente dell'azienda stessa.
                 Se non esiste l'azienda, viene spedita la mail all'indirizzo
                 e-mail presente nel campo CCR_NOTIFICHE della tabella SERVIZI
                 se significativo.
                 Quindi, la notifica e' viene spedita se:
                 - esiste in PARAMETRI_RICHIESTA il parametro RS_AZIENDA
                   significativo;
                     oppure se
                 - esiste in SERVIZI il campo CR_NOTIFICHE significativo.
              Se lo stato della richiesta e' 'Abilitata' ('A'),
                 viene mandata la notifica (all'utente richiedente ed all'eventuale
                 azienda di cui e' referente) di avvenuta abilitazione dell'utente
                 al servizio; se, inoltre, e' il primo servizio abilitato all'
                 utente (presenza della chiave 'PWD=' nel campo NOTE della tabella
                 UTENTI, nessun diritto di accesso abilitato a cui l'utente abbia
                 fatto accesso e nessuna richiesta da prendere in carico), viene
                 mandata anche la seconda meta' della password.
              Se lo stato della richiesta e' 'Respinta' ('R'),
                 viene mandata la notifica (all'utente richiedente ed all'eventuale
                 azienda di cui e' referente) che la richiesta di abilitazione dell'
                 utente al servizio e' stata respinta.
              Passi Eseguiti:
              - Verifica presenza campi obbligatori:
                 . p_id_richiesta.
              - Controllo esistenza di un tipo di notifica, se alla richiesta o al
                servizio non e' associato nessun tipo di notifica, non fa nulla.
              - Controllo stato della richiesta (deve essere <> 'F').
              - Verifica se in PARAMETRI_RICHIESTA esiste il parametro RS_AZIENDA
                per selezione nome del destinatario.
              - Seleziona, in base al servizio, l'indirizzo del mittente e
                l'eventuale indirizzo a cui mandare in CC la notifica.
              - Prepara il messaggio in base allo stato della richiesta.
              - Selezione eventuale Ente Mittente (campo SOGGETTO tabella ENTI).
              - Selezione Indirizzo Utente Destinatario.
              - Se la notifica avviene via MAIL:
              - Controllo esistenza indirizzo email mittente (campo
                MAIL_NOTIFICHE della tabella SERVIZI).
              - Se stato richiesta <> 'C':
                  Controllo esistenza indirizzo email utente destinatario.
              - Spedisce il messaggio.
              -  Aggiorna il campo NOTIFICATA di RICHIESTE_ABILITAZIONE a 'Spedita'
                 ('S') se tutto e' bene, a 'Fallita' ('F') altrimenti.
              -----------------------------------------------------
                 ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              -----------------------------------------------------
 ARGOMENTI:   p_id_richiesta: identificativo della richiesta.
              p_note:         eventuali note da aggiungere al messaggio.
 ECCEZIONI:   -20999: Parametro 'p_id_richiesta' obbligatorio.
              -20999: Richiesta N.'<p_id_richiesta> inesistente.
              -20999: Errore in selezione Richiesta N.<p_id_richiesta>.
              -20999: Notifica non prevista per richieste Fallite (Richiesta
                      N.<p_id_richiesta>).
              -20999: Errore in selezione Azienda destinataria Richiesta
                      N.<p_id_richiesta>: deve esistere un solo parametro
                     'AZIENDA'.
              -20999: Errore in selezione Azienda destinataria Richiesta
                      N.<p_id_richiesta>.
              -20999: Errore in selezione Indirizzo Mittente Richiesta
                      N.<p_id_richiesta>.
              -20999: Errore in selezione Ente Mittente Richiesta
                      N.<p_id_richiesta>.
              -20999: Errore in selezione Indirizzo Utente Destinatario Richiesta
                      N.<p_id_richiesta>.
              -20999. Impossibile inviare notifica Richiesta N.<p_id_richiesta>.
                      Dati anagrafici dell''utente non presenti.
              -20999: Impossibile notificare la Richiesta N.<p_id_richiesta>.
                      Indirizzo del Mittente inesistente.
              -20999: Errore in selezione Indirizzo Soggetto Destinatario Richiesta
                      N.<p_id_richiesta>.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              La spedizione delle notifiche avviene tramite CIM utilizzando il tag
              memorizzato nella tabella REGISTRO dello user oracle AD4.
              Il registro viene esplorato nel seguente ordine:
              1. DB_USERS/<user_oracle>/PRODUCTS/<modulo>/RICHIESTA_ABILITAZIONE/MAIL/CIM;
              2. DB_USERS/<user_oracle>/RICHIESTA_ABILITAZIONE/MAIL/CIM;
              3. PRODUCTS/<modulo>/RICHIESTA_ABILITAZIONE/MAIL/CIM;
              4. PRODUCTS/AD4/RICHIESTA_ABILITAZIONE/MAIL/CIM (valore di default = mail).
              L'intero messaggio da spedire puo' essere personalizzato con le
              variabili (possono essere scritte in maiscolo od in minuscolo
              indifferentemente ma non un po' in maiscolo e un po' in minuscolo):
              - Parametri della Richiesta: $p1, ..., $p9 in ordine di id_parametro;
              - Numero identificativo della richiesta:
                $id_richiesta
              - Codice del modulo a cui si richiede di essere abilitati:
                $cod_modulo
              - Descrizione del modulo a cui si richiede di essere abilitati:
                $modulo
              - Codice dell'istanza a cui si richiede di essere abilitati:
                $cod_istanza
              - Descrizione dell'istanza a cui si richiede di essere abilitati:
                $istanza
              - Codice dell'ente a cui si richiede l'abilitazione:
                $cod_ente
              - Descrizione dell'ente a cui si richiede l'abilitazione:
                $ente
              - Nominativo dell'utente (di login) che richiede l'abilitazione:
                $utente_richiedente
              - Dati anagrafici dell'utente che richiede l'abilitazione:
                $soggetto_richiedente
              - Dati anagrafici del soggetto legato all'ente a cui si richiede l'abilitazione:
                $soggetto_ente
              - Indirizzo eMail dell'ente a cui rispondere:
                $mail_notifiche
              - Seconda meta della password attribuita all'utente che richiede l'abilitazione:
                $password
           Il messaggio di notifica predefinito e' memorizzato nella
           tabella REGISTRO dello user oracle AD4 nelle stringhe della chiave
              PRODUCTS/AD4/RICHIESTA_ABILITAZIONE/MAIL/TESTO.
           Per personalizzarli, i messaggi devono venire memorizzati nella
           tabella REGISTRO dello user oracle AD4 nella chiave
           <chiave>/RICHIESTA_ABILITAZIONE/MAIL/TESTO.
           Il registro viene esplorato nel seguente ordine:
           1. DB_USERS/<user_oracle>/PRODUCTS/<modulo>/RICHIESTA_ABILITAZIONE/MAIL/TESTO;
           2. DB_USERS/<user_oracle>/RICHIESTA_ABILITAZIONE/MAIL/TESTO;
           3. PRODUCTS/<modulo>/RICHIESTA_ABILITAZIONE/MAIL/TESTO;
           4. PRODUCTS/AD4/RICHIESTA_ABILITAZIONE/MAIL/TESTO (valori di default)
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 1    13/03/2003 MM     Gestione campo TIPO_NOTIFICA della tabella SERVIZI
                        in fase di spedizione notifiche.
 3    06/02/2004 MM     Modificato il controllo per decidere se
                        recapitare all'utente la seconda met` della password
                        in modo che controlli se:
                        - esiste la chiave 'PWD=' nel campo NOTE della tabella
                          UTENTI;
                        - non esiste nessun diritto di accesso abilitato a cui
                          l'utente abbia fatto accesso;
                        - non esiste nessuna richiesta dello stesso utente da
                          prendere in carico.
 5    06/10/2005 MM     Introduzione della possibilita di modificare il testo
                        delle mail in fase di richiesta di abilitazione dai
                        profotti web.
      24/11/2005 MM     - Soluzione problema BO11769: problemi in notifica
                        approvazione richiesta da web per servizio con notifica
                        tipo "POSTA".
                        - Soluzione problema BO12496: inserimento, come default
                        del messaggio della mail di approvazione dell'
                        indicazione che e possibile ottenere al proprio
                        recapito e-mail le indicazioni per riuscire ad
                        effettuare il login.
                        - Tra gli attributi del soggetto richiedente inserito
                        anche il recapito e-mail.
                        - Dopo la notifica della seconda meta' della password,
                        le note di UTENTI non vengono svuotate ma viene solo
                        eliminata la stringa 'PWD='||<seconda_meta_psw>.
 009  14/12/2007 MM     A24733.0.0: In un portale, se per il servizio e'
                        prevista una notifica via Posta Ordinaria, quando la
                        richiesta di un utente viene abilitata, non viene
                        cancellata la seconda parte della password.
 011  29/07/2008 MM     A28371.0.0: All'interno del servizio di Registrazione
                        di Ad4 bisognerebbe prevedere, oltre alla notifica
                        all'Ente dell'avvenuta registrazione, anche la notifica
                        di richieste di registrazioni pendenti, in modo che
                        l'amministratore possa procedere con la approvazione
                        o il rifiuto.
023  19/01/2012 SNeg Dimensionate variabili in base alla tabella istanze
053 28/11/2019 SNeg Per il cim considero il tag con maiuscole e minuscole Bug #38857
******************************************************************************/
   (
      p_id_richiesta   IN   NUMBER,
      p_note           IN   VARCHAR2 DEFAULT NULL
   )
   IS
      d_stato              VARCHAR2 (1);
      d_utente            utenti.utente%TYPE;
      d_nominativo         utenti.nominativo%TYPE;
      d_sogg_richiedente   VARCHAR2 (2000);
      d_modulo             moduli.modulo%TYPE;
      d_modulo_des          moduli.descrizione%TYPE;
      d_istanza            istanze.istanza%TYPE;
      d_istanza_des        istanze.descrizione%TYPE;
      d_user               istanze.user_oracle%TYPE;
      d_ente               enti.ente%TYPE;
      d_ente_des           enti.descrizione%TYPE;
      d_ente_sogg          VARCHAR2 (2000);
      d_err_number         NUMBER           := -20999;
      d_error              VARCHAR2 (2000);
      d_note               VARCHAR2 (2000);
      d_nome_dest          VARCHAR2 (2000);
      d_indi_dest          VARCHAR2 (100);
      d_pwd                VARCHAR2 (20);
      d_text_int           VARCHAR2 (32000);
      d_text_pwd           VARCHAR2 (200);
      d_text_pwd_cc        VARCHAR2 (200);
      d_text_pie           VARCHAR2 (32000);
      d_text_msg           VARCHAR2 (32000);
      d_text_msg_cc        VARCHAR2 (32000);
      d_tipo_notifica      VARCHAR2 (10);
      d_note_not           VARCHAR2 (2000);
      d_err                INTEGER;
      d_mail_notifiche     VARCHAR2 (100);
      d_ccr_notifiche      VARCHAR2 (100);
      d_sogg_dest          NUMBER (8);
      d_dest_prov_nas      VARCHAR2 (10);
      d_sogg_ente          NUMBER (8);
      d_nome_cc            VARCHAR2 (2000);
      d_indi_cc            VARCHAR2 (100);
      d_cimtag             VARCHAR2 (100);
      d_subject            VARCHAR2 (2000);
      nessuna_notifica     EXCEPTION;
      errore               EXCEPTION;
      javaexc              EXCEPTION;
   BEGIN
      d_error := '';
      /*-----------------------------------------------------
                   Verifica parametri obbligatori.
      -----------------------------------------------------*/
      IF p_id_richiesta IS NULL
      THEN
         d_error := 'Parametro ''p_id_richiesta'' obbligatorio.';
         RAISE errore;
      END IF;
      /*-----------------------------------------------------
              Seleziona gli attributi della richiesta.
      -----------------------------------------------------*/
      BEGIN
         SELECT riab.stato, riab.utente, uten.nominativo, riab.modulo,
                modu.descrizione, riab.istanza, ista.descrizione,
                UPPER (ista.user_oracle),
                NVL (riab.tipo_notifica, serv.tipo_notifica),
                riab.indirizzo_notifica, riab.note_notifica
           INTO d_stato, d_utente, d_nominativo, d_modulo,
                d_modulo_des, d_istanza, d_istanza_des,
                d_user,
                d_tipo_notifica,
                d_indi_dest, d_note_not
           FROM richieste_abilitazione riab,
                utenti uten,
                moduli modu,
                istanze ista,
                servizi serv
          WHERE riab.id_richiesta = p_id_richiesta
            AND riab.istanza = serv.istanza(+)
            AND riab.modulo = serv.modulo(+)
            AND riab.utente = uten.utente(+)
            AND riab.modulo = modu.modulo(+)
            AND riab.istanza = ista.istanza(+);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_error := 'Richiesta N.' || p_id_richiesta || ' inesistente.';
            RAISE errore;
         WHEN OTHERS
         THEN
            d_error :=
                  'Errore in selezione Richiesta N.' || p_id_richiesta || '.';
            RAISE errore;
      END;
      -- Rev.009  14/12/2007 MM     A24733.0.0.
      -- Memorizza eventuale seconda parte della password
      d_pwd := si4.get_stringparm ('UTENTI', 'PWD', d_utente);
      /*-----------------------------------------------------
         Controllo esistenza di un tipo di notifica, se
         alla richiesta non e' associato nessun tipo di
         notifica,
            in caso di approvazione della richiesta,
               elimina la seconda parte della password;
            altrimenti,
               non fa nulla.
      -----------------------------------------------------*/
      IF NVL (UPPER (d_tipo_notifica), 'POSTA') <> 'MAIL'
      THEN
         IF d_stato = 'A'
         THEN
            UPDATE utenti
               SET note = REPLACE (note, 'PWD=' || d_pwd, '')
             WHERE utente = d_utente;
         END IF;
         RAISE nessuna_notifica;
      END IF;
      -- Rev.009  14/12/2007 MM     A24733.0.0.: fine mod.
      /*-----------------------------------------------------
              Controllo stato della richiesta
              (deve essere <> 'F').
      -----------------------------------------------------*/
      IF d_stato = 'F'
      THEN
         d_error :=
               'Notifica non prevista per richieste Fallite (Richiesta N.'
            || p_id_richiesta
            || ').';
         RAISE errore;
      END IF;
      /*-----------------------------------------------------
            Verifica se, in PARAMETRI_RICHIESTA, esiste il
            parametro RS_AZIENDA per selezione nome del
            destinatario.
      -----------------------------------------------------*/
      BEGIN
         SELECT valore
           INTO d_nome_dest
           FROM parametri_richiesta
          WHERE id_richiesta = p_id_richiesta AND parametro = 'RS_AZIENDA';
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_nome_dest := 'NO';
         WHEN TOO_MANY_ROWS
         THEN
            d_error :=
                  'Errore in selezione Azienda destinataria Richiesta N.'
               || p_id_richiesta
               || ': deve esistere un solo parametro ''AZIENDA''.';
            RAISE errore;
         WHEN OTHERS
         THEN
            d_error :=
                  'Errore in selezione Azienda destinataria Richiesta N.'
               || p_id_richiesta
               || '.';
            RAISE errore;
      END;
      /*-----------------------------------------------------
              Seleziona, in base al servizio, l'indirizzo
              del mittente e l'eventuale indirizzo a cui
              mandare in CC la notifica.
      -----------------------------------------------------*/
      BEGIN
         SELECT mail_notifiche, ccr_notifiche
           INTO d_mail_notifiche, d_ccr_notifiche
           FROM servizi
          WHERE modulo = d_modulo AND istanza = d_istanza;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_mail_notifiche := TO_CHAR (NULL);
            d_ccr_notifiche := TO_CHAR (NULL);
         WHEN OTHERS
         THEN
            d_error :=
                  'Errore in selezione Indirizzo Mittente Richiesta N.'
               || p_id_richiesta
               || '.';
            RAISE errore;
      END;
      /*-----------------------------------------------------
          Prepara il messaggio in base allo stato della
          richiesta.
      -----------------------------------------------------*/
--DBMS_OUTPUT.PUT_LINE('d_stato: '||d_stato);
      IF d_stato = 'C'
      THEN
      -- Rev.  011  29/07/2008 MM     A28371.0.0
--         IF d_nome_dest = 'NO' THEN
--            RAISE NESSUNA_NOTIFICA;
--         END IF;
--         d_text_int := Registro_Pac.get_ad4_string('RICHIESTA_ABILITAZIONE/MAIL/TESTO', '1.1. Testo Carico', d_modulo, d_user);
         IF d_nome_dest = 'NO' AND d_ccr_notifiche IS NULL
         THEN
            RAISE nessuna_notifica;
         END IF;
         IF d_nome_dest <> 'NO'
         THEN
            d_text_int :=
               registro_pac.get_ad4_string
                                        ('RICHIESTA_ABILITAZIONE/MAIL/TESTO',
                                         '1.1. Testo Carico',
                                         d_modulo,
                                         d_user
                                        );
         END IF;
      -- Rev.  011  29/07/2008 MM     A28371.0.0: fine mod.
      ELSIF d_stato = 'A'
      THEN
         d_text_int :=
            registro_pac.get_ad4_string ('RICHIESTA_ABILITAZIONE/MAIL/TESTO',
                                         '1.2. Testo Approvata',
                                         d_modulo,
                                         d_user
                                        );
         -- Cerca seconda meta' della password se e' il primo servizio abilitato
         -- all'utente (presenza della chiave 'PWD=' nel campo NOTE della tabella
         -- UTENTI, nessun diritto di accesso abilitato a cui l'utente abbia gia
         -- fatto accesso e nessuna richiesta da prendere in carico).
--         d_pwd := Si4.GET_STRINGPARM('UTENTI', 'PWD', d_utente);
--DBMS_OUTPUT.PUT_LINE('d_pwd: '||d_pwd);
         IF d_pwd IS NOT NULL
         THEN
            BEGIN
               SELECT COUNT (1) rich
                 INTO d_err
                 FROM diritti_accesso
                WHERE utente = d_utente
                  AND modulo <> d_modulo
                  AND istanza <> d_istanza
                  AND numero_accessi > 0;
            EXCEPTION
               WHEN OTHERS
               THEN
                  d_error :=
                        'Errore Controllo se Prima Richiesta - Richiesta N.'
                     || p_id_richiesta
                     || '.';
                  RAISE errore;
            END;
--DBMS_OUTPUT.PUT_LINE('d_err: '||d_err);
            IF d_err = 0
            THEN
               BEGIN
                  SELECT COUNT (1)
                    INTO d_err
                    FROM richieste_abilitazione
                   WHERE id_richiesta <> p_id_richiesta
                     AND utente = d_utente
                     AND stato = 'C';
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     d_error :=
                           'Errore Controllo se Prima Richiesta - Richiesta N.'
                        || p_id_richiesta
                        || '.';
                     RAISE errore;
               END;
            END IF;
--DBMS_OUTPUT.PUT_LINE('d_err: '||d_err);
            IF d_err = 0
            THEN
               UPDATE utenti
                  SET note = REPLACE (note, 'PWD=' || d_pwd, '')
                WHERE utente = d_utente;
               d_text_pwd :=
                  registro_pac.get_ad4_string
                                         ('RICHIESTA_ABILITAZIONE/MAIL/TESTO',
                                          '2. Testo Password',
                                          d_modulo,
                                          d_user
                                         );
               d_text_pwd_cc :=
                  registro_pac.get_ad4_string
                                         ('RICHIESTA_ABILITAZIONE/MAIL/TESTO',
                                          '2.1. Testo Password CC',
                                          d_modulo,
                                          d_user
                                         );
            END IF;
         END IF;
         d_text_pie :=
            registro_pac.get_ad4_string ('RICHIESTA_ABILITAZIONE/MAIL/TESTO',
                                         '3. Precisazione Approvata',
                                         d_modulo,
                                         d_user
                                        );
      ELSIF d_stato = 'R'
      THEN
         d_text_pie :=
            registro_pac.get_ad4_string ('RICHIESTA_ABILITAZIONE/MAIL/TESTO',
                                         '1.3. Testo Respinta',
                                         d_modulo,
                                         d_user
                                        );
      END IF;
      /*-----------------------------------------------------
             Aggiunge eventuali note al messaggio.
      -----------------------------------------------------*/
      IF d_note_not IS NOT NULL
      THEN
         d_text_pie := d_text_pie || CHR (10) || CHR (10) || d_note_not;
      END IF;
      IF p_note IS NOT NULL
      THEN
         d_text_pie := d_text_pie || CHR (10) || CHR (10) || p_note;
      END IF;
      BEGIN
         d_sogg_dest := TO_CHAR (NULL);
         BEGIN
            d_sogg_dest := utente.get_soggetto (d_utente, 'Y');
         EXCEPTION
            WHEN OTHERS
            THEN
               d_error :=
                     'Errore in selezione Indirizzo Utente Destinatario Richiesta N.'
                  || p_id_richiesta
                  || '.';
               RAISE errore;
         END;
         IF d_sogg_dest IS NULL
         THEN
            d_error :=
                  'Impossibile inviare notifica Richiesta N.'
               || p_id_richiesta
               || '. Dati anagrafici dell''utente non presenti.';
            RAISE errore;
         END IF;
         d_dest_prov_nas := soggetto.get_provincia_nas_sigla (d_sogg_dest);
         IF d_dest_prov_nas IS NOT NULL
         THEN
            d_dest_prov_nas :=
                 ' (' || soggetto.get_provincia_nas_sigla (d_sogg_dest)
                 || ')';
         END IF;
         d_sogg_richiedente :=
               CHR (10)
            || CHR (9)
            || soggetto.get_nome (d_sogg_dest)
            || CHR (10)
            || CHR (9)
            || 'Nato a '
            || soggetto.get_des_comune_nas (d_sogg_dest)
            || d_dest_prov_nas
            || ' il '
            || soggetto.get_data_nascita (d_sogg_dest)
            || CHR (10)
            || CHR (9)
            || 'Codice Fiscale: '
            || soggetto.get_codice_fiscale (d_sogg_dest)
            || CHR (10)
            || CHR (9)
            || 'Recapito: '
            || soggetto.get_indirizzo_web (d_sogg_dest)
            || CHR (10);
         d_text_int :=
               registro_pac.get_ad4_string
                                         ('RICHIESTA_ABILITAZIONE/MAIL/TESTO',
                                          '1. Header',
                                          d_modulo,
                                          d_user
                                         )
            || CHR (10)
            || d_text_int;
         d_text_pie :=
               d_text_pie
            || registro_pac.get_ad4_string
                                         ('RICHIESTA_ABILITAZIONE/MAIL/TESTO',
                                          '4. Footer',
                                          d_modulo,
                                          d_user
                                         );
         /*-----------------------------------------------------
                Selezione eventuale Ente Mittente.
         -----------------------------------------------------*/
         BEGIN
            d_sogg_ente := TO_NUMBER (NULL);
            SELECT enti.ente, enti.descrizione, enti.soggetto
              INTO d_ente, d_ente_des, d_sogg_ente
              FROM enti, istanze ista
             WHERE enti.ente = ista.ente AND ista.istanza = d_istanza;
         EXCEPTION
            WHEN OTHERS
            THEN
               d_error :=
                     'Errore in selezione Ente Mittente Richiesta N.'
                  || p_id_richiesta
                  || '.';
               RAISE errore;
         END;
         IF d_sogg_ente IS NOT NULL
         THEN
            soggetto.initialize (d_sogg_ente);
            d_ente_sogg :=
                  CHR (10)
               || CHR (9)
               || soggetto.get_nome (d_sogg_ente)
               || CHR (10)
               || CHR (9)
               || soggetto.get_indirizzo_completo (d_sogg_ente);
         END IF;
         d_text_msg := d_text_int || d_text_pwd || d_text_pie;
         d_text_msg_cc := d_text_int || d_text_pwd_cc || d_text_pie;
         d_subject :=
            registro_pac.get_ad4_string ('RICHIESTA_ABILITAZIONE/MAIL/TESTO',
                                         'Oggetto',
                                         d_modulo,
                                         d_user
                                        );
/**************************************************************************/
/*                         Sostituzione variabili                         */
/**************************************************************************/
/*--------------------------------------------------------------------------
    Seleziona eventuali Parametri Richiesta e li sostituisce nel messaggio.
--------------------------------------------------------------------------*/
         DECLARE
            i   INTEGER := 0;
         BEGIN
            FOR pari IN (SELECT   valore
                             FROM parametri_richiesta
                            WHERE id_richiesta = p_id_richiesta
                         ORDER BY id_parametro)
            LOOP
               i := i + 1;
               IF i < 10
               THEN
                  d_text_msg := REPLACE (d_text_msg, '$p' || i, pari.valore);
                  d_text_msg := REPLACE (d_text_msg, '$P' || i, pari.valore);
                  d_text_msg_cc :=
                              REPLACE (d_text_msg_cc, '$p' || i, pari.valore);
                  d_text_msg_cc :=
                              REPLACE (d_text_msg_cc, '$P' || i, pari.valore);
                  d_subject := REPLACE (d_subject, '$p' || i, pari.valore);
                  d_subject := REPLACE (d_subject, '$P' || i, pari.valore);
               END IF;
            END LOOP;
         END;
         /*--------------------------------------------------------------------------
             Sostituisce variabile $id_richiesta ( o $ID_RICHIESTA) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg := REPLACE (d_text_msg, '$id_richiesta', p_id_richiesta);
         d_text_msg := REPLACE (d_text_msg, '$ID_RICHIESTA', p_id_richiesta);
         d_text_msg_cc :=
                      REPLACE (d_text_msg_cc, '$id_richiesta', p_id_richiesta);
         d_text_msg_cc :=
                      REPLACE (d_text_msg_cc, '$ID_RICHIESTA', p_id_richiesta);
         d_subject := REPLACE (d_subject, '$id_richiesta', p_id_richiesta);
         d_subject := REPLACE (d_subject, '$ID_RICHIESTA', p_id_richiesta);
         /*--------------------------------------------------------------------------
             Sostituisce variabile $cod_modulo ( o $COD_MODULO) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg := REPLACE (d_text_msg, '$cod_modulo', d_modulo);
         d_text_msg := REPLACE (d_text_msg, '$COD_MODULO', d_modulo);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$cod_modulo', d_modulo);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$COD_MODULO', d_modulo);
         d_subject := REPLACE (d_subject, '$cod_modulo', d_modulo);
         d_subject := REPLACE (d_subject, '$COD_MODULO', d_modulo);
         /*--------------------------------------------------------------------------
             Sostituisce variabile $modulo ( o $MODULO) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg := REPLACE (d_text_msg, '$modulo', d_modulo_des);
         d_text_msg := REPLACE (d_text_msg, '$MODULO', d_modulo_des);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$modulo', d_modulo_des);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$MODULO', d_modulo_des);
         d_subject := REPLACE (d_subject, '$modulo', d_modulo_des);
         d_subject := REPLACE (d_subject, '$MODULO', d_modulo_des);
         /*--------------------------------------------------------------------------
             Sostituisce variabile $cod_istanza ( o $COD_ISTANZA) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg := REPLACE (d_text_msg, '$cod_istanza', d_istanza);
         d_text_msg := REPLACE (d_text_msg, '$COD_ISTANZA', d_istanza);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$cod_istanza', d_istanza);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$COD_ISTANZA', d_istanza);
         d_subject := REPLACE (d_subject, '$cod_istanza', d_istanza);
         d_subject := REPLACE (d_subject, '$COD_ISTANZA', d_istanza);
         /*--------------------------------------------------------------------------
             Sostituisce variabile $istanza ( o $ISTANZA) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg := REPLACE (d_text_msg, '$istanza', d_istanza_des);
         d_text_msg := REPLACE (d_text_msg, '$ISTANZA', d_istanza_des);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$istanza', d_istanza_des);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$ISTANZA', d_istanza_des);
         d_subject := REPLACE (d_subject, '$istanza', d_istanza_des);
         d_subject := REPLACE (d_subject, '$ISTANZA', d_istanza_des);
         /*--------------------------------------------------------------------------
             Sostituisce variabile $cod_ente ( o $COD_ENTE) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg := REPLACE (d_text_msg, '$cod_ente', d_ente);
         d_text_msg := REPLACE (d_text_msg, '$COD_ENTE', d_ente);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$cod_ente', d_ente);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$COD_ENTE', d_ente);
         d_subject := REPLACE (d_subject, '$cod_ente', d_ente);
         d_subject := REPLACE (d_subject, '$COD_ENTE', d_ente);
         /*--------------------------------------------------------------------------
             Sostituisce variabile $ente ( o $ENTE) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg := REPLACE (d_text_msg, '$ente', d_ente_des);
         d_text_msg := REPLACE (d_text_msg, '$ENTE', d_ente_des);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$ente', d_ente_des);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$ENTE', d_ente_des);
         d_subject := REPLACE (d_subject, '$ente', d_ente_des);
         d_subject := REPLACE (d_subject, '$ENTE', d_ente_des);
         /*--------------------------------------------------------------------------
             Sostituisce variabile $utente_richiedente ( o $UTENTE_RICHIEDENTE) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg :=
                     REPLACE (d_text_msg, '$utente_richiedente', d_nominativo);
         d_text_msg :=
                     REPLACE (d_text_msg, '$UTENTE_RICHIEDENTE', d_nominativo);
         d_text_msg_cc :=
                  REPLACE (d_text_msg_cc, '$utente_richiedente', d_nominativo);
         d_text_msg_cc :=
                  REPLACE (d_text_msg_cc, '$UTENTE_RICHIEDENTE', d_nominativo);
         d_subject := REPLACE (d_subject, '$utente_richiedente', d_nominativo);
         d_subject := REPLACE (d_subject, '$UTENTE_RICHIEDENTE', d_nominativo);
         /*--------------------------------------------------------------------------
             Sostituisce variabile $soggetto_richiedente ( o $SOGGETTO_RICHIEDENTE) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg :=
             REPLACE (d_text_msg, '$soggetto_richiedente', d_sogg_richiedente);
         d_text_msg :=
             REPLACE (d_text_msg, '$SOGGETTO_RICHIEDENTE', d_sogg_richiedente);
         d_text_msg_cc :=
            REPLACE (d_text_msg_cc,
                     '$soggetto_richiedente',
                     d_sogg_richiedente
                    );
         d_text_msg_cc :=
            REPLACE (d_text_msg_cc,
                     '$SOGGETTO_RICHIEDENTE',
                     d_sogg_richiedente
                    );
         d_subject :=
              REPLACE (d_subject, '$soggetto_richiedente', d_sogg_richiedente);
         d_subject :=
              REPLACE (d_subject, '$SOGGETTO_RICHIEDENTE', d_sogg_richiedente);
         /*--------------------------------------------------------------------------
             Sostituisce variabile $soggetto_ente ( o $SOGGETTO_ENTE) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg := REPLACE (d_text_msg, '$soggetto_ente', d_ente_sogg);
         d_text_msg := REPLACE (d_text_msg, '$SOGGETTO_ENTE', d_ente_sogg);
         d_text_msg_cc :=
                        REPLACE (d_text_msg_cc, '$soggetto_ente', d_ente_sogg);
         d_text_msg_cc :=
                        REPLACE (d_text_msg_cc, '$SOGGETTO_ENTE', d_ente_sogg);
         d_subject := REPLACE (d_subject, '$soggetto_ente', d_ente_sogg);
         d_subject := REPLACE (d_subject, '$SOGGETTO_ENTE', d_ente_sogg);
         /*--------------------------------------------------------------------------
             Sostituisce variabile $mail_notifiche ( o $MAIL_NOTIFICHE) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg :=
                     REPLACE (d_text_msg, '$mail_notifiche', d_mail_notifiche);
         d_text_msg :=
                     REPLACE (d_text_msg, '$MAIL_NOTIFICHE', d_mail_notifiche);
         d_text_msg_cc :=
                  REPLACE (d_text_msg_cc, '$mail_notifiche', d_mail_notifiche);
         d_text_msg_cc :=
                  REPLACE (d_text_msg_cc, '$MAIL_NOTIFICHE', d_mail_notifiche);
         d_subject := REPLACE (d_subject, '$mail_notifiche', d_mail_notifiche);
         d_subject := REPLACE (d_subject, '$MAIL_NOTIFICHE', d_mail_notifiche);
         /*--------------------------------------------------------------------------
             Sostituisce variabile $password ( o $PASSWORD) nel messaggio.
         --------------------------------------------------------------------------*/
         d_text_msg := REPLACE (d_text_msg, '$password', d_pwd);
         d_text_msg := REPLACE (d_text_msg, '$PASSWORD', d_pwd);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$password', d_pwd);
         d_text_msg_cc := REPLACE (d_text_msg_cc, '$PASSWORD', d_pwd);
         d_subject := REPLACE (d_subject, '$password', d_pwd);
         d_subject := REPLACE (d_subject, '$PASSWORD', d_pwd);
/******************************************************/
/*                     Invio MAIL                     */
/******************************************************/
         IF UPPER (d_tipo_notifica) <> 'MAIL'
         THEN
            RAISE nessuna_notifica;
         ELSE
            /*-----------------------------------------------------
                Controllo esistenza indirizzo email mittente
              (campo MAIL_NOTIFICHE della tabella SERVIZI)
              -----------------------------------------------------*/
            IF d_mail_notifiche IS NULL
            THEN
               d_error :=
                     'Impossibile notificare la Richiesta N.'
                  || p_id_richiesta
                  || '. Indirizzo del Mittente inesistente.';
               RAISE errore;
            END IF;
            /*-----------------------------------------------------
                Se richiesta da prendere in carico la notifica
                va mandata all'azienda e all'ente, altrimenti anche
                all'utente.
            -----------------------------------------------------*/
            -- Rev.  011  29/07/2008 MM     A28371.0.0
--            IF d_stato <> 'C' THEN
--               BEGIN
--                  d_nome_cc := Soggetto.GET_NOME(d_sogg_dest);
--                  d_indi_cc := Soggetto.GET_INDIRIZZO_WEB(d_sogg_dest);
--               EXCEPTION
--                  WHEN OTHERS THEN
--                     d_error := 'Errore in selezione Indirizzo Soggetto Destinatario Richiesta N.'||p_id_richiesta||'.';
--                     RAISE ERRORE;
--               END;
--            END IF;
            IF d_stato <> 'C'
            THEN
               BEGIN
                  d_nome_cc := soggetto.get_nome (d_sogg_dest);
                  d_indi_cc := soggetto.get_indirizzo_web (d_sogg_dest);
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     d_error :=
                           'Errore in selezione Indirizzo Soggetto Destinatario Richiesta N.'
                        || p_id_richiesta
                        || '.';
                     RAISE errore;
               END;
            ELSE
               IF d_ccr_notifiche IS NOT NULL
               THEN
                  d_nome_cc := d_ente_des;
                  d_indi_cc := d_ccr_notifiche;
               END IF;
            END IF;
            -- Rev.  011  29/07/2008 MM     A28371.0.0. fine mod.
            /*-----------------------------------------------------
               Se l'indirizzo e il nome del destinatario non sono
               stati riempiti con quelli dell'azienda, vengono
               riempiti   con quelli dell'utente se la richiesta
               e' stata approvata/respinta, con l'indirizzo di
               mail in ccr associato al servizio se la richiesta
               e' in carico.
            -----------------------------------------------------*/
            IF d_indi_dest IS NULL AND d_nome_dest = 'NO'
            THEN
               d_indi_dest := d_indi_cc;
               d_nome_dest := d_nome_cc;
            END IF;
            IF d_indi_dest IS NULL
            THEN
               d_error :=
                     'Errore in selezione Indirizzo Destinatario Richiesta N.'
                  || p_id_richiesta
                  || '.';
               RAISE errore;
            END IF;
            BEGIN
               d_err := 0;
               /*-----------------------------------------------------
                  Inizializza il CIM con il tipo di messaggio da
                  inviare.
               -----------------------------------------------------*/
               d_cimtag :=
                  NVL
                   --   (LOWER rev. 53 inizio
                         (registro_pac.get_ad4_string
                                           ('RICHIESTA_ABILITAZIONE/MAIL/CIM',
                                            'Tag',
                                            d_modulo,
                                            d_user
                                           )
                   --    )rev. 53 fine
                         ,
                      LOWER (d_tipo_notifica)
                     );
               d_err := si4cimplsqlj.initializemessage (d_cimtag);
               /*-----------------------------------------------------
                              Inizializza il Mittente.
               -----------------------------------------------------*/
               si4cimplsqlj.setsender (senderip         => '',
                                       sendername       => '',
                                       ID               => '',
                                       NAME             => d_ente_des,
                                       company          => '',
                                       address          => '',
                                       code             => '',
                                       city             => '',
                                       province         => '',
                                       state            => '',
                                       email            => d_mail_notifiche,
                                       phonenumber      => '',
                                       faxnumber        => ''
                                      );
               /*-----------------------------------------------------
                            Inizializza il Destinatario.
               -----------------------------------------------------*/
               si4cimplsqlj.addrecipient (ID               => '',
                                          NAME             => d_nome_dest,
                                          company          => '',
                                          address          => '',
                                          code             => '',
                                          city             => '',
                                          province         => '',
                                          state            => '',
                                          email            => d_indi_dest,
                                          phonenumber      => '',
                                          faxnumber        => ''
                                         );
               /*-----------------------------------------------------
                   Se il Destinatario CC esiste ed e' diverso dal
                   destinatario principale, lo inizializza.
               -----------------------------------------------------*/
               IF     d_indi_cc IS NOT NULL
                  AND UPPER (NVL (d_indi_cc, ' ')) <> UPPER (d_indi_dest)
                  AND (d_stato <> 'A' OR d_pwd IS NULL)
               THEN
                  si4cimplsqlj.addcc (ID               => '',
                                      NAME             => d_nome_cc,
                                      company          => '',
                                      address          => '',
                                      code             => '',
                                      city             => '',
                                      province         => '',
                                      state            => '',
                                      email            => d_indi_cc,
                                      phonenumber      => '',
                                      faxnumber        => ''
                                     );
               END IF;
               /*-----------------------------------------------------
                   Se il Destinatario CCR esiste, lo inizializza.
               -----------------------------------------------------*/
               IF d_ccr_notifiche IS NOT NULL
               THEN
                  si4cimplsqlj.addbcc (ID               => '',
                                       NAME             => d_ente_des,
                                       company          => '',
                                       address          => '',
                                       code             => '',
                                       city             => '',
                                       province         => '',
                                       state            => '',
                                       email            => d_ccr_notifiche,
                                       phonenumber      => '',
                                       faxnumber        => ''
                                      );
               END IF;
               /*-----------------------------------------------------
                        Predispone l'oggetto del messaggio.
               -----------------------------------------------------*/
               si4cimplsqlj.setsubject (d_subject);
               /*-----------------------------------------------------
                        Predispone il testo del messaggio.
               -----------------------------------------------------*/
               si4cimplsqlj.settext (d_text_msg);
/*-----------------------------------------------------
                Invia il messaggio.
-----------------------------------------------------*/
               d_err := si4cimplsqlj.send;
            EXCEPTION
               WHEN OTHERS
               THEN
                  d_err_number := -20901;
                  d_error := SQLERRM;
                  RAISE javaexc;
            END;
            BEGIN
               IF     d_indi_cc IS NOT NULL
                  AND UPPER (NVL (d_indi_cc, ' ')) <> UPPER (d_indi_dest)
                  AND d_pwd IS NOT NULL
               THEN
                  d_err := 0;
                  /*-----------------------------------------------------
                     Inizializza il CIM con il tipo di messaggio da
                     inviare.
                  -----------------------------------------------------*/
                  d_err := si4cimplsqlj.initializemessage (d_cimtag);
                  /*-----------------------------------------------------
                                 Inizializza il Mittente.
                  -----------------------------------------------------*/
                  si4cimplsqlj.setsender (senderip         => '',
                                          sendername       => '',
                                          ID               => '',
                                          NAME             => d_ente_des,
                                          company          => '',
                                          address          => '',
                                          code             => '',
                                          city             => '',
                                          province         => '',
                                          state            => '',
                                          email            => d_mail_notifiche,
                                          phonenumber      => '',
                                          faxnumber        => ''
                                         );
                  /*-----------------------------------------------------
                               Inizializza il Destinatario.
                  -----------------------------------------------------*/
                  si4cimplsqlj.addrecipient (ID               => '',
                                             NAME             => d_nome_cc,
                                             company          => '',
                                             address          => '',
                                             code             => '',
                                             city             => '',
                                             province         => '',
                                             state            => '',
                                             email            => d_indi_cc,
                                             phonenumber      => '',
                                             faxnumber        => ''
                                            );
                  /*-----------------------------------------------------
                      Se il Destinatario CCR esiste, lo inizializza.
                  -----------------------------------------------------*/
                  IF d_ccr_notifiche IS NOT NULL
                  THEN
                     si4cimplsqlj.addbcc (ID               => '',
                                          NAME             => d_ente_des,
                                          company          => '',
                                          address          => '',
                                          code             => '',
                                          city             => '',
                                          province         => '',
                                          state            => '',
                                          email            => d_ccr_notifiche,
                                          phonenumber      => '',
                                          faxnumber        => ''
                                         );
                  END IF;
                  /*-----------------------------------------------------
                           Predispone l'oggetto del messaggio.
                  -----------------------------------------------------*/
                  si4cimplsqlj.setsubject (d_subject);
                  /*-----------------------------------------------------
                           Predispone il testo del messaggio.
                  -----------------------------------------------------*/
                  si4cimplsqlj.settext (d_text_msg_cc);
/*-----------------------------------------------------
                Invia il messaggio.
-----------------------------------------------------*/
                  d_err := si4cimplsqlj.send;
               END IF;
            EXCEPTION
               WHEN OTHERS
               THEN
                  d_err_number := -20901;
                  d_error := SQLERRM;
                  RAISE javaexc;
            END;
/******************************************************/
/*                 FINE Invio MAIL                    */
/******************************************************/
         END IF;
      END;
      /*-----------------------------------------------------
                 Aggiorna il campo NOTIFICATA.
      -----------------------------------------------------*/
      UPDATE richieste_abilitazione
         SET note_notifica = p_note,
             notificata = 'S'
       WHERE id_richiesta = p_id_richiesta;
      COMMIT;
   EXCEPTION
      WHEN nessuna_notifica
      THEN
         UPDATE richieste_abilitazione
            SET notificata = 'N'
          WHERE id_richiesta = p_id_richiesta;
         COMMIT;
      WHEN errore OR javaexc
      THEN
         ROLLBACK;
         UPDATE richieste_abilitazione
            SET note =
                      DECODE (note, NULL, NULL, note || CHR (10) || CHR (13))
                   || d_error,
                notificata = 'F'
          WHERE id_richiesta = p_id_richiesta;
         COMMIT;
         raise_application_error (d_err_number, d_error);
      WHEN OTHERS
      THEN
         RAISE;
   END notifica_richiesta;
   PROCEDURE gestisci_richiesta
/******************************************************************************
 NOME:        GESTISCI_RICHIESTA.
 DESCRIZIONE: Permette di cambiare lo stato della richiesta, chiudendo, cosi',
              la parte automatica di gestione.
              Una richiesta pur essere portata in stato:
              - 'A': Abilitata;
              - 'R': Respinta;
              - 'C': Da Prendere in Carico.
              Passi Eseguiti:
              - Verifica presenza parametri obbligatori:
                 . p_id_richiesta;
                 . p_stato.
              - Verifica validita' del valore del parametro 'p_stato'.
              - Verifica che la richiesta esista e non sia gia' abilitata.
              - Se la richiesta deve essere 'Abilitata' (p_stato = 'A'),
                  - seleziona il Gruppo di Abilitazione (p_gruppo se passato, il
                    valore di GRUPPO_ABILITAZIONE della tabella SERVIZI altrimenti).
                 - Verifica esistenza dell'eventuale gruppo.
                 - Eventuale inserimento Utente nel Gruppo di Abilitazione con
                   conseguente inserimento dei diritti e delle caratteristiche di
                   accesso del gruppo.
                 - Verifica che il gruppo possieda Diritti di Accesso al Servizio.
                 - Associa il ruolo, se passato, all'utente per l'accesso al servizio
                   a cui viene abilitato.
              - Aggiorna lo stato della richiesta, associando il valore di 'p_stato'
                se tutto e' andato bene, il valore 'F' ('Fallita') altrimenti.
              - Manda eventuale notifica della richiesta.
              -----------------------------------------------------
                  ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
              -----------------------------------------------------
 ARGOMENTI:   p_id_richiesta: identificativo della richiesta.
              p_stato:        stato da associare alla richiesta.
                              Valori possibili: 'A', 'R' oppure 'C'.
              p_gruppo:       gruppo di appartenenza dell'utente.
                              Significativo solo in caso di approvazione della
                              richiesta (p_stato = 'A').
                              Default: null.
              p_ruolo:        ruolo dell'utente nell'accesso al servizio.
                              Significativo solo in caso di approvazione della
                              richiesta (p_stato = 'A').
                              Default: null.
              p_note:         note da accodare all'eventuale notifica della richiesta.
                              Default: null.
 ECCEZIONI:   -20999: Parametro ''p_id_richiesta'' obbligatorio.
              -20999: Una richiesta puo'' essere Abilitata o Respinta o Messa
                      in Carico (Stato = 'A', 'R' oppure 'C').
              -20999: Richiesta N.<p_id_richiesta> gia' abilitata.
              -20999: Richiesta N.<p_id_richiesta> inesistente.
              -20999: Errore in selezione Richiesta N.<p_id_richiesta>.
              -20999: Errore in selezione Gruppo di Abilitazione per la Richiesta
                      N.<p_id_richiesta>.
              -20999: Gruppo di Abilitazione (<d_gruppo>) inesistente. Richiesta
                      N.'||p_id_richiesta||'.
              -20999: Errore in fase di Inserimento Utente nel Gruppo di Abilitazione
                      per la Richiesta N.<p_id_richiesta>.
              -20999: Errore in accesso alla tabella 'DIRITTI_ACCESSO' per la Richiesta
                      N.<p_id_richiesta>.
              -20999: Il gruppo '<d_gruppo>' non possiede Diritti di Accesso al Servizio
                      (Modulo: '<d_modulo>' - Istanza: '<d_istanza>').
              -20999: Il Gruppo oppure il Ruolo deve essere specificato.
              -20999: Errore in fase di Inserimento Diritto di Accesso per la Richiesta
                      N.<p_id_richiesta>.
              -20999: Errore in fase di Aggiornamento Diritto di Accesso per la Richiesta
                      N.<p_id_richiesta>.
 ANNOTAZIONI: ATTENZIONE: viene effettuato COMMIT o ROLLBACK.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Prima emissione.
 5    30/11/2005 MM     Gestione parametro p_note_notifica come
                        personalizzazione del messaggio di notifica.
******************************************************************************/
   (
      p_id_richiesta    IN   NUMBER,
      p_stato           IN   VARCHAR2,
      p_gruppo          IN   VARCHAR2 DEFAULT NULL,
      p_ruolo           IN   VARCHAR2 DEFAULT NULL,
      p_note_notifica   IN   VARCHAR2 DEFAULT NULL,
      p_note            IN   VARCHAR2 DEFAULT NULL
   )
   IS
      d_stato_old     VARCHAR2 (1);
      d_gruppo        VARCHAR2 (8);
      d_utente        VARCHAR2 (8);
      d_modulo        VARCHAR2 (10);
      d_istanza       VARCHAR2 (10);
      d_esiste_diac   NUMBER;
      d_error         VARCHAR2 (2000);
      d_note          VARCHAR2 (2000);
      errore          EXCEPTION;
      nulla           EXCEPTION;
   BEGIN
      d_error := '';
      d_note := '';
      IF p_note IS NOT NULL
      THEN
         d_note := p_note || CHR (10) || CHR (13);
      END IF;
      /*-----------------------------------------------------
          Verifica parametri obbligatori e loro validita'.
      -----------------------------------------------------*/
      IF p_id_richiesta IS NULL
      THEN
         d_error := 'Parametro ''p_id_richiesta'' obbligatorio.';
         RAISE nulla;
      ELSE
         IF    p_stato IS NULL
            OR (p_stato <> 'A' AND p_stato <> 'R' AND p_stato <> 'C')
         THEN
            d_error :=
               'Una richiesta puo'' essere Abilitata o Respinta o Messa in Carico (Stato = ''A'', ''R'' oppure ''C'').';
            RAISE nulla;
         END IF;
      END IF;
      /*-----------------------------------------------------
         Verifica che la richiesta esista e non sia gia'
         abilitata.
      -----------------------------------------------------*/
      BEGIN
         SELECT stato, utente, modulo, istanza
           INTO d_stato_old, d_utente, d_modulo, d_istanza
           FROM richieste_abilitazione
          WHERE id_richiesta = p_id_richiesta;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_error :=
                      'Richiesta N.''' || p_id_richiesta || ''' inesistente.';
            RAISE;
         WHEN OTHERS
         THEN
            d_error :=
                  'Errore in selezione Richiesta N.' || p_id_richiesta || '.';
            RAISE;
      END;
      IF d_stato_old = 'A'
      THEN
         d_error :=
                  'Richiesta N.''' || p_id_richiesta || ''' gia'' abilitata.';
         RAISE nulla;
      END IF;
/******************************************************/
/*            ABILITAZIONE richiesta                  */
/******************************************************/
/*-----------------------------------------------------
     Se la richiesta deve essere 'Abilitata' (p_stato
   = 'A'), seleziona il Gruppo di Abilitazione
   (p_gruppo se passato, altrimenti il valore di
   GRUPPO_ABILITAZIONE della tabella SERVIZI).
-----------------------------------------------------*/
      IF p_stato = 'A'
      THEN
         IF p_gruppo IS NOT NULL
         THEN
            d_gruppo := p_gruppo;
         ELSE
            BEGIN
               SELECT gruppo_abilitazione
                 INTO d_gruppo
                 FROM servizi
                WHERE modulo = d_modulo AND istanza = d_istanza;
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  d_gruppo := TO_CHAR (NULL);
               WHEN OTHERS
               THEN
                  d_error :=
                        'Errore in selezione Gruppo di Abilitazione per la Richiesta N.'
                     || p_id_richiesta
                     || '.';
                  RAISE errore;
            END;
         END IF;
         IF d_gruppo IS NOT NULL
         THEN
            /*-----------------------------------------------------
                       Verifica esistenza del gruppo.
            -----------------------------------------------------*/
            BEGIN
               SELECT utente
                 INTO d_gruppo
                 FROM utenti
                WHERE utente = d_gruppo AND tipo_utente = 'G';
            EXCEPTION
               WHEN OTHERS
               THEN
                  d_error :=
                        'Gruppo di Abilitazione ('
                     || d_gruppo
                     || ') inesistente. Richiesta N.'
                     || p_id_richiesta
                     || '.';
                  RAISE errore;
            END;
            /*-----------------------------------------------------
                       Inserisce l'utente nel gruppo.
            -----------------------------------------------------*/
            BEGIN
               INSERT INTO utenti_gruppo
                           (gruppo, utente)
                  SELECT d_gruppo, d_utente
                    FROM DUAL
                   WHERE NOT EXISTS (
                                SELECT 1
                                  FROM utenti_gruppo
                                 WHERE gruppo = d_gruppo
                                       AND utente = d_utente);
               IF SQL%ROWCOUNT = 0
               THEN
                    /*-----------------------------------------------------
                       Se l'utente appartiene gia' al gruppo, inserisce
                  i diritti e le caratteristiche di accesso del
                  gruppo (altrimenti lo fa il trigger).
                    -----------------------------------------------------*/
                  BEGIN
                     gruppo.diac_gruppo_insert (d_gruppo,
                                                d_utente,
                                                '%',
                                                '%',
                                                'E'
                                               );
                  EXCEPTION
                     WHEN OTHERS
                     THEN
                        d_error := REPLACE (SQLERRM, 'ORA-', '');
                        RAISE errore;
                  END;
                  BEGIN
                     gruppo.caac_gruppo_insert (d_gruppo,
                                                d_utente,
                                                '%',
                                                '%',
                                                'E'
                                               );
                  EXCEPTION
                     WHEN OTHERS
                     THEN
                        d_error := REPLACE (SQLERRM, 'ORA-', '');
                        RAISE errore;
                  END;
               END IF;
            EXCEPTION
               WHEN OTHERS
               THEN
                  d_error :=
                        'Errore in fase di Inserimento Utente nel Gruppo di Abilitazione per la Richiesta N.'
                     || p_id_richiesta
                     || '.'
                     || CHR (10)
                     || CHR (13)
                     || d_error;
                  RAISE errore;
            END;
            /*-----------------------------------------------------
                Verifica che il gruppo possieda Diritti di
            Accesso al Servizio.
            -----------------------------------------------------*/
            BEGIN
               SELECT COUNT (1)
                 INTO d_esiste_diac
                 FROM diritti_accesso
                WHERE utente = d_gruppo
                  AND modulo = d_modulo
                  AND istanza = d_istanza;
            EXCEPTION
               WHEN OTHERS
               THEN
                  d_error :=
                        'Errore in accesso alla tabella ''DIRITTI_ACCESSO'' per la Richiesta N.'
                     || p_id_richiesta
                     || '.';
                  RAISE errore;
            END;
            IF d_esiste_diac = 0
            THEN
               d_error :=
                     'Il gruppo '''
                  || d_gruppo
                  || ''' non possiede Diritti di Accesso al Servizio (Modulo: '''
                  || d_modulo
                  || ''' - Istanza: '''
                  || d_istanza
                  || ''')';
               RAISE errore;
            END IF;
         END IF;
         IF p_ruolo IS NULL AND d_gruppo IS NULL
         THEN
            d_error := 'Il Gruppo oppure il Ruolo deve essere specificato.';
            RAISE errore;
         END IF;
         /*-----------------------------------------------------
           Associa il ruolo, se passato, all'utente per
           l'accesso al servizio a cui viene abilitato.
           Se non e' stato identificato un gruppo di
           abilitazione, inserisce un diritto di accesso al
           servizio per l'utente con ruolo p_ruolo, altrimenti
           aggiorna il diritto di accesso esistente associando
           il ruolo p_ruolo.
         -----------------------------------------------------*/
         IF p_ruolo IS NOT NULL
         THEN
            IF d_gruppo IS NULL
            THEN
               BEGIN
                  INSERT INTO diritti_accesso
                              (utente, modulo, istanza, ruolo
                              )
                       VALUES (d_gruppo, d_modulo, d_istanza, p_ruolo
                              );
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     d_error :=
                           'Errore in fase di Inserimento Diritto di Accesso per la Richiesta N.'
                        || p_id_richiesta
                        || '.';
                     RAISE errore;
               END;
            ELSE
               BEGIN
                  UPDATE diritti_accesso
                     SET ruolo = p_ruolo,
                         gruppo = TO_CHAR (NULL)
                   WHERE utente = d_utente
                     AND modulo = d_modulo
                     AND istanza = d_istanza
                     AND ruolo <> p_ruolo;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     d_error :=
                           'Errore in fase di Aggiornamento Diritto di Accesso per la Richiesta N.'
                        || p_id_richiesta
                        || '.';
                     RAISE errore;
               END;
            END IF;
         END IF;                /* Fine p_ruolo is not null */
                      /******************************************************/
                     /*            FINE ABILITAZIONE richiesta             */
                      /******************************************************/
      END IF;
      /*-----------------------------------------------------
                Aggiorna lo stato della richiesta.
      -----------------------------------------------------*/
      UPDATE richieste_abilitazione
         SET stato = p_stato,
             notificata = DECODE (p_stato, d_stato_old, notificata, 'N'),
             note = d_note
       WHERE id_richiesta = p_id_richiesta;
      COMMIT;
      notifica_richiesta (p_id_richiesta, p_note_notifica);
   EXCEPTION
      WHEN nulla
      THEN
         raise_application_error (-20999, d_error);
      WHEN errore
      THEN
         ROLLBACK;
         UPDATE richieste_abilitazione
            SET stato = 'F',
                notificata = DECODE (p_stato, d_stato_old, notificata, 'N'),
                note = d_note || d_error
          WHERE id_richiesta = p_id_richiesta;
         COMMIT;
         raise_application_error (-20999, d_error);
      WHEN OTHERS
      THEN
         RAISE;
   END gestisci_richiesta;
   FUNCTION calcola_accessi
/******************************************************************************
 NOME:        CALCOLA_ACCESSI.
 DESCRIZIONE: Dato l'utente ritorna il numero totale degli accessi.
 ARGOMENTI:   p_utente:     codice dell'utente.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 4    13/04/2005 MM     Creazione.
******************************************************************************/
   (p_utente IN VARCHAR2)
      RETURN NUMBER
   IS /* SLAVE_COPY */
      d_return   NUMBER;
   BEGIN
      SELECT SUM (NVL (numero_accessi, 0))
        INTO d_return
        FROM diritti_accesso
       WHERE utente = p_utente;
      RETURN d_return;
   END calcola_accessi;
   PROCEDURE aggiorna_pwd_da_modificare
/******************************************************************************
 NOME:        AGGIORNA_PWD_DA_MODIFICARE.
 DESCRIZIONE: Dato l'utente, verifica se e' prevista la modifica della password
              al primo login successivo, se e' cosi' e l'utente non ha ancora
              fatto nessun accesso, setta il campo PWD_DA_MODIFICARE a SI.
 ARGOMENTI:   p_utente:     codice dell'utente.
 ECCEZIONI:   -20999: Utente 'p_utente' non esistente!
 ANNOTAZIONI: Chiamata da DIRITTI_ACCESSO_TIU, DIRITTI_ACCESSO_TD,
              CARATTERISTICHE_ACCESSO_TIU e CARATTERISTICHE_ACCESSO_TD.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 5    21/12/2005 MM     Creazione.
044 11/01/2019 SNeg In caso di utenza ldap aggiornare pwd sempre = NO
******************************************************************************/
   (p_utente IN VARCHAR2)
   IS
      d_ultimo_tentativo    DATE;
      d_tentativi           NUMBER;
      d_rinnovo             VARCHAR2 (2);
      d_pwd_da_modificare   VARCHAR2 (2);
      d_importanza          INTEGER;
      d_is_utente_ldap      NUMBER;
      d_is_pwd_da_mod_primo_uso NUMBER;
   BEGIN
      BEGIN
         SELECT ultimo_tentativo, NVL (rinnovo_password, 'NO'),
                NVL (pwd_da_modificare, 'NO'), NVL (importanza, 0),
                accesso.is_ldapuser(nominativo),
                caratteristica_accesso.is_pwd_da_mod_primo_uso (p_utente)
           INTO d_ultimo_tentativo, d_rinnovo,
                d_pwd_da_modificare, d_importanza,
                d_is_utente_ldap, d_is_pwd_da_mod_primo_uso
           FROM utenti
          WHERE utente = p_utente;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            raise_application_error (-20999,
                                     'Utente ' || p_utente
                                     || ' non esistente!'
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;
      IF     caratteristica_accesso.is_pwd_da_mod_primo_uso (p_utente) = 1
         AND d_ultimo_tentativo IS NULL
         AND d_rinnovo = 'SI'
      THEN
         d_pwd_da_modificare := 'SI';
      END IF;
      IF     caratteristica_accesso.is_pwd_da_mod_primo_uso (p_utente) = 0
         AND d_pwd_da_modificare = 'SI'
         AND d_importanza <> -1
      THEN
         d_pwd_da_modificare := 'NO';
      END IF;
      -- Rev. 44 inizio
      IF d_is_utente_ldap = 1 then
         d_pwd_da_modificare := 'NO';
      END IF;
      -- Rev. 44 fine
      UPDATE utenti
         SET pwd_da_modificare = d_pwd_da_modificare
       WHERE utente = p_utente;
   END aggiorna_pwd_da_modificare;
   FUNCTION genera_password
/******************************************************************************
 NOME:        GENERA_PASSWORD.
 DESCRIZIONE: Dato l'utente genera una password casuale e la associa all'utente.
 PARAMETRI:   p_utente:     codice dell'utente.
              p_lenght:     lunghezza della pwd da generare, se non passata
                            prende quella prevista per l'utente.
 RITORNA:     password generata in chiaro.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 6    05/07/2006 MM     Creazione.
******************************************************************************/
   (
      p_utente   IN   VARCHAR2,
      p_lenght   IN   NUMBER DEFAULT NULL
   )
      RETURN VARCHAR2
   IS
      d_return            VARCHAR2 (32767);
      d_min_pwd_len       INTEGER;
      d_is_car_speciali   INTEGER;
      d_is_num_obb        INTEGER;
   BEGIN
      d_min_pwd_len := caratteristica_accesso.get_minpwdlength (p_utente);
      d_is_car_speciali :=
                        caratteristica_accesso.is_car_speciali_pwd (p_utente);
      d_is_num_obb := caratteristica_accesso.is_num_obb_pwd (p_utente);
      IF d_min_pwd_len > NVL (p_lenght, d_min_pwd_len)
      THEN
         raise_application_error
            (-20999,
                'La lunghezza minima della password prevista per l''utente e'' '
             || p_lenght
             || '.'
            );
      ELSE
         d_min_pwd_len :=
            NVL (p_lenght,
                 caratteristica_accesso.get_minpwdlength (p_utente));
         IF d_min_pwd_len = 0
         THEN
            d_min_pwd_len := 8;
         END IF;
         d_return :=
            crypt.genera_password (d_min_pwd_len,
                                   d_is_car_speciali,
                                   d_is_num_obb
                                  );
         IF LOWER
               (NVL
                   (registro_utility.leggi_stringa ('PRODUCTS/AUTHENTICATION',
                                                    'PwdCase',
                                                    0
                                                   ),
                    'upper'
                   )
               ) <> 'any'
         THEN
            d_return := UPPER (d_return);
         END IF;
         initialize (p_utente);
         set_password (d_return);
         update_utente ('N');
      END IF;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END genera_password;
   FUNCTION get_attributi_password
/******************************************************************************
 NOME:        GET_ATTRIBUTI_PASSWORD.
 DESCRIZIONE: Dato l'utente, calcola gli attributi che deve avere la password.
 PARAMETRI:   p_utente:     codice dell'utente.
 RITORNA:     stringa descrittiva degli attributi della password.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 6    05/07/2006 MM     Creazione.
******************************************************************************/
   (p_utente IN VARCHAR2)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return            VARCHAR2 (32767);
      d_min_pwd_len       INTEGER;
      d_is_car_speciali   INTEGER;
      d_is_num_obb        INTEGER;
      d_nominativo        VARCHAR2 (2000);
   BEGIN
      d_return := 'La password per l''utente selezionato ';
      BEGIN
         d_min_pwd_len := caratteristica_accesso.get_minpwdlength (p_utente);
      EXCEPTION
         WHEN OTHERS
         THEN
            d_min_pwd_len := -1;
      END;
      IF NVL (d_min_pwd_len, 0) >= 0
      THEN
         IF NVL (d_min_pwd_len, 0) = 0
         THEN
            d_return := d_return || 'puo'' essere lunga a piacere, ';
         ELSE
            d_return :=
                  d_return
               || 'deve essere lunga almeno '
               || d_min_pwd_len
               || ' caratteri, ';
         END IF;
      END IF;
      d_is_car_speciali :=
                         caratteristica_accesso.is_car_speciali_pwd (p_utente);
      IF d_is_car_speciali = 0
      THEN
         d_return := d_return || 'non ';
      END IF;
      d_return := d_return || 'puo'' contenere caratteri speciali';
      d_is_num_obb := caratteristica_accesso.is_num_obb_pwd (p_utente);
      d_return := d_return || ' e';
      IF d_is_num_obb = 0
      THEN
         d_return := d_return || ' non ';
      ELSE
         d_return := d_return || 'd ';
      END IF;
      d_return := d_return || 'e'' necessario che contenga almeno un numero.';
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END get_attributi_password;
   FUNCTION get_struttura
/******************************************************************************
 NOME:        GET_STRUTTURA
 DESCRIZIONE:
 PARAMETRI:   p_visualizza      %   tutto l'albero (default)
                                G   solo i Gruppi
                                O   solo i Gruppi mappati alla SO
                                NOU tutto tranne utenti
 RITORNA:
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    25/10/2006 MM     Creazione.
******************************************************************************/
   (p_visualizza IN VARCHAR2 DEFAULT '%')
      RETURN afc.t_ref_cursor
   IS /* SLAVE_COPY */
      d_statement   afc.t_statement;
      dreturn       afc.t_ref_cursor;
   BEGIN
      d_statement :=
            'SELECT *'
         || '  FROM STRUTTURA_UTENTI uten'
         || ' WHERE tipo_utente like decode('''
         || p_visualizza
         || ''', ''NOU'', tipo_utente,'''
         || p_visualizza
         || ''')'
         || '   AND tipo_utente <> decode('''
         || p_visualizza
         || ''', ''NOU'', ''U'',''xx'')';
      dreturn := afc_dml.get_ref_cursor (d_statement);
   END get_struttura;
   FUNCTION get_ascendenza
/******************************************************************************
 NOME:        GET_ASCENDENZA
 DESCRIZIONE:
 PARAMETRI:   p_UTENTE
              P_GRUPPO
              P_CONCATENA C   = Codice
                          D   = Descrizione
                          TC  = Tipo#Codice
                          TD  = Tipo#Descrizione
                          TCD = Tipo#Codice#Descrizione
                          altrimenti TCD
 RITORNA:
 ECCEZIONI:   --
 ANNOTAZIONI: Funziona solo per la struttura organizzativa, o cmq per un albero
              in cui ogni nodo ha un solo padre.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    25/10/2006 MM     Creazione.
   52 28/11/2019 SNeg Per la struttura considero solo i gruppi di tipo 'O'
******************************************************************************/
   (
      p_utente_o_gruppo   IN   VARCHAR2,
      p_gruppo            IN   VARCHAR2,
      p_concatena         IN   VARCHAR2 DEFAULT 'TCD'
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      dreturn        VARCHAR2 (32000);
      d_ascendente   VARCHAR2 (1000);
      d_concatena    VARCHAR2 (3);
      d_tipouten     VARCHAR2 (1);
      d_ruolo        VARCHAR2 (8);
   BEGIN
      d_concatena := p_concatena;
      IF d_concatena NOT IN ('D', 'TC', 'TD', 'C')
      THEN
         d_concatena := 'TCD';
      END IF;
      BEGIN
         SELECT tipo_utente
           INTO d_tipouten
           FROM utenti
          WHERE utente = p_utente_o_gruppo;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_tipouten := '';
      END;
      IF d_tipouten IS NOT NULL
      THEN
         IF d_tipouten = 'O'
         THEN
            d_ruolo :=
                    NVL (gruppo.get_gruppo_lavoro (p_utente_o_gruppo), 'def');
            IF d_ruolo <> 'def'
            THEN
               d_tipouten := 'R';
            END IF;
         END IF;
         IF SUBSTR (d_concatena, 1, 1) = 'T'
         THEN
            dreturn := d_tipouten || '#';
         END IF;
         IF d_concatena IN ('C', 'TC', 'TCD')
         THEN
            IF d_tipouten = 'R'
            THEN
               dreturn := dreturn || d_ruolo;
            ELSE
               dreturn := dreturn || p_utente_o_gruppo;
            END IF;
         END IF;
         IF d_concatena IN ('D', 'TD', 'TCD')
         THEN
            IF d_concatena = 'TCD'
            THEN
               dreturn := dreturn || '#';
            END IF;
            IF d_tipouten <> 'U'
            THEN
               dreturn :=
                     dreturn
                  || NVL (gruppo.get_descrizione (p_utente_o_gruppo),
                          p_utente_o_gruppo
                         );
               IF d_tipouten = 'R'
               THEN
                  dreturn := dreturn || '#' || p_utente_o_gruppo;
               END IF;
            ELSE
               dreturn :=
                     dreturn
                  || NVL (get_nominativo (p_utente_o_gruppo, 'Y', 0),
                          p_utente_o_gruppo
                         );
            END IF;
         END IF;
         FOR padri IN
            (SELECT     gruppo,
                        DECODE
                           (gruppo.get_tipo (gruppo),
                            'O', DECODE
                                      (NVL (gruppo.get_gruppo_lavoro (gruppo),
                                            'def'
                                           ),
                                       'def', gruppo.get_tipo (gruppo),
                                       'R'
                                      ),
                            gruppo.get_tipo (gruppo)
                           ) tipo
                   FROM utenti_gruppo utenti_gruppo
                  WHERE EXISTS (
                           SELECT 1
                             FROM utenti_gruppo utgr
                            WHERE utgr.utente = utenti_gruppo.utente
                              AND utgr.gruppo = utenti_gruppo.gruppo)
             START WITH utente = p_utente_o_gruppo AND gruppo = p_gruppo
             CONNECT BY PRIOR gruppo = utente
             -- rev. 52 inizio
                 and utente.get_tipo_utente (utente) = 'O'
                and  utente.get_tipo_utente (gruppo) = 'O'
             -- rev. 52 fine
             )
         LOOP
            d_tipouten := padri.tipo;
            IF d_tipouten = 'R'
            THEN
               d_ruolo :=
                         NVL (gruppo.get_gruppo_lavoro (padri.gruppo), 'def');
            ELSE
               d_ruolo := '';
            END IF;
            IF SUBSTR (d_concatena, 1, 1) = 'T'
            THEN
               d_ascendente := d_tipouten || '#';
            ELSE
               d_ascendente := '';
            END IF;
            IF d_concatena IN ('C', 'TC', 'TCD')
            THEN
               IF d_tipouten = 'R'
               THEN
                  d_ascendente := d_ascendente || d_ruolo;
               ELSE
                  d_ascendente := d_ascendente || padri.gruppo;
               END IF;
            END IF;
            IF d_concatena IN ('D', 'TD', 'TCD')
            THEN
               IF d_concatena = 'TCD'
               THEN
                  d_ascendente := d_ascendente || '#';
               END IF;
               d_ascendente :=
                     d_ascendente
                  || NVL (gruppo.get_descrizione (padri.gruppo), padri.gruppo);
               IF d_tipouten = 'R'
               THEN
                  d_ascendente := d_ascendente || '#' || padri.gruppo;
               END IF;
            END IF;
            dreturn := d_ascendente || '[' || dreturn;
         END LOOP;
      END IF;
      RETURN dreturn;
   END get_ascendenza;
   FUNCTION get_gruppi (
      p_utente_o_gruppo   VARCHAR2,
      p_codice            VARCHAR2 DEFAULT 'Y',
      p_descrizione       VARCHAR2 DEFAULT 'N'
   )
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        GET_GRUPPI_UTENTE
 DESCRIZIONE: Dato un utente, ritorna i gruppi a cui egli appartiene.
 PARAMETRI:   p_utente varchar2   codice dell'utente
 RITORNA:     varchar2: lista dei gruppi a cui l'utente appartiene separati da
                        virgola
 ANNOTAZIONI: .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    15/03/2001 MM     Prima emissione.
 1    13/01/2003 MM     Aggiunta where condition: gruppo <> 'ric_abil'.
 7    26/10/2006 MM     Inclusione della funzione nel package.
******************************************************************************/
      dep_gruppo   VARCHAR2 (32000);
   BEGIN
      FOR gruppi IN (SELECT      DECODE (p_descrizione,
                                         'N', TO_CHAR (NULL),
                                            uten.nominativo
                                         || DECODE (p_codice,
                                                    'Y', ' (',
                                                    TO_CHAR (NULL)
                                                   )
                                        )
                              || DECODE (p_codice,
                                         'Y', utgr.gruppo,
                                         TO_CHAR (NULL)
                                        )
                              || DECODE (p_descrizione,
                                         'N', TO_CHAR (NULL),
                                         DECODE (p_codice,
                                                 'Y', ')',
                                                 TO_CHAR (NULL)
                                                )
                                        )
                              || ', ' gruppo
                         FROM utenti_gruppo utgr, utenti uten
                        WHERE utgr.utente = p_utente_o_gruppo
                          AND utgr.gruppo = uten.utente
                          AND utgr.gruppo <> 'ric_abil'
                     ORDER BY utgr.gruppo)
      LOOP
         dep_gruppo := dep_gruppo || gruppi.gruppo;
      END LOOP;
      dep_gruppo :=
         SUBSTR (NVL (dep_gruppo, ', '), 1,
                 LENGTH (NVL (dep_gruppo, ', ')) - 2);
      RETURN dep_gruppo;
   END get_gruppi;
--   PROCEDURE riempi_diac_eliminati
--/******************************************************************************
-- NOME:        riempi_diac_eliminati
-- DESCRIZIONE: Riempie TabDiac (pl/sql table dichiarata come variabile del
--              body) con i diritti di accesso che p_utente avrebbe ereditato da
--              p_gruppo ma che sono stati esplicitamente eliminati.
-- ARGOMENTI:   p_gruppo  IN     codice del gruppo.
--              p_utente  IN     codice dell'utente o %
--              tabDiac   IN OUT pl/sql table da riempire
-- ECCEZIONI:   --
-- ANNOTAZIONI: .
-- REVISIONI:
-- Rev. Data       Autore Descrizione
-- ---- ---------- ------ ------------------------------------------------------
-- 7    27/10/2006 MM     Creazione.
--******************************************************************************/
--   ( p_gruppo  IN     VARCHAR2
--   , p_utente  IN     VARCHAR2
--   , tabDiac   IN OUT DiacDelTab)
--   IS
--      indice BINARY_INTEGER := 0;
--   BEGIN
--      if gruppo.exists_gruppo(p_gruppo) = 0 then
--         raise_application_error(-20999, 'Gruppo '''||p_gruppo||''' non presente.');
--      end if;
--      if p_utente <> '%' then
--         if exists_utente(p_utente) = 0 then
--            raise_application_error(-20999, 'Utente '''||p_utente||''' non presente.');
--         end if;
--      end if;
--      indice := NVL(tabDiac.LAST,0);
--      /*-----------------------------------------------------
--          Riempie TabDiac con tutti i diritti di accesso
--          che p_utente avrebbe ereditato da p_gruppo ma
--          che sono stati esplicitamente eliminati.
--      -----------------------------------------------------*/
--      FOR DIAC IN (SELECT DIAC.Istanza, DIAC.modulo, DIAC.ruolo
--                     FROM DIRITTI_ACCESSO DIAC
--                    WHERE DIAC.Utente  = p_gruppo)
--      LOOP
--         DECLARE
--            d_check INTEGER;
--            d_gruppo VARCHAR2(8);
--         BEGIN
----DBMS_OUTPUT.PUT_LINE('riempi_diac_eliminati di '||p_gruppo);
--            FOR uten IN (SELECT Utente
--                           FROM UTENTI_GRUPPO
--                          WHERE Gruppo  = p_gruppo
--                            AND Utente LIKE p_utente)
--            LOOP
--               d_check := Diritto_Accesso.check_gruppo(p_gruppo, uten.Utente, DIAC.Istanza, DIAC.modulo);
----DBMS_OUTPUT.PUT_LINE('check_gruppo DI '||uten.Utente||': '||d_check);
--               IF d_check = 0 THEN
--                  -- diritto esiste in gruppo ma non in utente
--                  BEGIN
--                     SELECT Utente
--                       INTO d_gruppo
--                       FROM DIRITTI_ACCESSO
--                      WHERE Gruppo  IS NULL
--                        AND Istanza = DIAC.Istanza
--                        AND modulo  = DIAC.modulo
--                      START WITH Utente = p_gruppo
--                    CONNECT BY PRIOR Gruppo = Utente
--                     ;
--                     indice                   := indice + 1;
--                     TabDiac(indice).Utente   := uten.Utente;
--                     TabDiac(indice).Istanza  := DIAC.Istanza;
--                     TabDiac(indice).modulo   := DIAC.modulo;
--                     TabDiac(indice).ruolo    := DIAC.ruolo;
--                     TabDiac(indice).Gruppo   := d_gruppo;
----DBMS_OUTPUT.PUT_LINE(indice||' - Utente: '||uten.Utente||' Gruppo: '||d_gruppo);
--                  EXCEPTION
--                     WHEN OTHERS THEN
----DBMS_OUTPUT.PUT_LINE(SQLERRM);
--                        NULL;
--                  END;
--               END IF;
--            END LOOP;
--         END;
--      END LOOP;
--   END riempi_diac_eliminati;
   PROCEDURE getcampistruttura (
      p_item_struttura   IN       VARCHAR2,
      p_tipo             IN OUT   VARCHAR2,
      p_codice           IN OUT   VARCHAR2,
      p_descrizione      IN OUT   VARCHAR2,
      p_ruolo            IN OUT   VARCHAR2
   )
   IS
      d_gruppo_so   VARCHAR2 (1);
   BEGIN
      p_descrizione := p_item_struttura;
      p_tipo := afc.get_substr (p_descrizione, '#');
      p_codice := afc.get_substr (p_descrizione, '#');
      IF INSTR (p_descrizione, '#') > 0
      THEN
         p_ruolo := p_codice;
         p_codice := p_descrizione;
         p_descrizione := afc.get_substr (p_codice, '#');
      ELSE
         p_ruolo := '';
      END IF;
   END getcampistruttura;
   PROCEDURE allineadizstruttura (
      p_codice        IN   VARCHAR2,
      p_tipo          IN   VARCHAR2,
      p_descrizione   IN   VARCHAR2,
      p_ruolo         IN   VARCHAR2
   )
   IS
      d_gruppo_so       VARCHAR2 (1);
      d_gruppo_lavoro   VARCHAR2 (1);
      d_descrizione     VARCHAR2 (40);
      d_codice          VARCHAR2 (40);
   BEGIN
      IF p_tipo = 'R'
      THEN
         BEGIN
            SELECT gruppo_so, gruppo_lavoro, d_descrizione
              INTO d_gruppo_so, d_gruppo_lavoro, d_descrizione
              FROM ruoli
             WHERE ruolo = p_ruolo;
            -- se il ruolo esiste con descrizione diversa, lo aggiorno
            IF p_descrizione <> d_descrizione
            THEN
               UPDATE ruoli
                  SET descrizione = p_descrizione
                WHERE ruolo = p_ruolo;
            END IF;
            IF d_gruppo_lavoro = 'N'
            THEN
               UPDATE ruoli
                  SET gruppo_lavoro = 'S'
                WHERE ruolo = p_ruolo;
            END IF;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               SELECT MIN (ruolo)
                 INTO d_codice
                 FROM ruoli
                WHERE descrizione = p_descrizione;
                  -- se esiste ruolo con stessa descrizione e codice diverso, modifico la descrizione
               -- dell'esistente accodando (II)
               IF d_codice IS NOT NULL
               THEN
                  UPDATE ruoli
                     SET descrizione = SUBSTR (descrizione, 1, 36) || '(II)'
                   WHERE ruolo = d_codice;
               END IF;
               DBMS_OUTPUT.put_line
                  (   'allineaDizStruttura INSERT INTO RUOLI (RUOLO, DESCRIZIONE) VALUES ('''
                   || p_ruolo
                   || ''', '''
                   || p_descrizione
                   || ''')'
                  );
               INSERT INTO ruoli
                           (ruolo, descrizione, gruppo_lavoro
                           )
                    VALUES (p_ruolo, p_descrizione, 'S'
                           );
               d_gruppo_so := 'N';
         END;
      END IF;
      BEGIN
         SELECT nominativo
           INTO d_descrizione
           FROM utenti
          WHERE utente = p_codice;
         -- se UTENTE esiste con nominativo diverso, lo aggiorno
         IF p_descrizione <> d_descrizione
         THEN
            UPDATE utenti
               SET nominativo = p_descrizione
             WHERE utente = p_codice;
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            SELECT MIN (utente)
              INTO d_codice
              FROM utenti
             WHERE nominativo = p_descrizione;
               -- se esiste utente con stesso nominativo e codice diverso, modifico il nominativo
            -- dell'esistente accodando (II)
            IF d_codice IS NOT NULL
            THEN
               UPDATE utenti
                  SET nominativo = SUBSTR (nominativo, 1, 36) || '(II)'
                WHERE utente = d_codice;
            END IF;
            DBMS_OUTPUT.put_line
               ('allineaDizStruttura INSERT INTO UTENTI (Utente, nominativo, tipo_utente, gruppo_lavoro)'
               );
            DBMS_OUTPUT.put_line (   ' VALUES ('''
                                  || p_codice
                                  || ''', '''
                                  || p_descrizione
                                  || ''', '''
                                  || p_tipo
                                  || ''', '''
                                  || p_ruolo
                                  || ''')'
                                 );
            INSERT INTO utenti
                        (utente, nominativo,
                         tipo_utente, gruppo_lavoro
                        )
                 VALUES (p_codice, p_descrizione,
                         DECODE (p_tipo, 'R', 'O', p_tipo), p_ruolo
                        );
------------------------------------------------------------------------------
-- e se esiste gia' un utente con stesso nominativo e codice <> ??????????????
------------------------------------------------------------------------------
      END;
      IF p_tipo = 'R' AND d_gruppo_so = 'N'
      THEN
         UPDATE ruoli
            SET gruppo_so = 'S'
          WHERE ruolo = p_ruolo;
      END IF;
   END allineadizstruttura;
   FUNCTION insertsotemp (p_struttura IN VARCHAR2, p_table IN VARCHAR2)
      RETURN NUMBER
   IS
      d_figlio      VARCHAR2 (100);
      d_padre       VARCHAR2 (100);
      d_tipo        VARCHAR2 (1);
      d_desc        VARCHAR2 (100);
      d_ruolo       VARCHAR2 (8);
      ipos1         INTEGER;
      ipos2         INTEGER;
      d_struttura   VARCHAR2 (1000);
      i             INTEGER         := 0;
      iret          INTEGER         := 0;
   BEGIN
      d_struttura := p_struttura;
      IF SUBSTR (d_struttura, -1, 1) = '['
      THEN
         d_struttura := SUBSTR (d_struttura, 1, LENGTH (d_struttura) - 1);
      END IF;
      iret := afc.countoccurrenceof (d_struttura, '[');
      i := iret;
      WHILE i > 0
      LOOP
         DBMS_OUTPUT.put_line ('d_struttura: ' || d_struttura);
         DBMS_OUTPUT.put_line ('i: ' || i);
         ipos1 := INSTR (d_struttura, '[', 1, i);
         IF i = 1
         THEN
            ipos2 := 0;
         ELSE
            ipos2 := INSTR (d_struttura, '[', 1, i - 1);
         END IF;
         d_figlio := SUBSTR (d_struttura, ipos1 + 1);
         getcampistruttura (d_figlio, d_tipo, d_figlio, d_desc, d_ruolo);
         IF p_table = 'SO4_SO_TEMP'
         THEN
            allineadizstruttura (d_figlio, d_tipo, d_desc, d_ruolo);
         END IF;
         d_padre := SUBSTR (d_struttura, ipos2 + 1, ipos1 - ipos2 - 1);
         getcampistruttura (d_padre, d_tipo, d_padre, d_desc, d_ruolo);
         IF p_table = 'SO4_SO_TEMP'
         THEN
            allineadizstruttura (d_padre, d_tipo, d_desc, d_ruolo);
         END IF;
         DBMS_OUTPUT.put_line (   'IN '
                               || p_table
                               || ': '
                               || d_figlio
                               || ' - '
                               || d_padre
                               || '  '
                               || d_struttura
                              );
         IF p_table = 'SO4_SO_TEMP'
         THEN
           begin
            INSERT INTO so4_so_temp
                        (figlio, padre, struttura)
               SELECT d_figlio, d_padre, d_struttura
                 FROM DUAL
                WHERE NOT EXISTS (
                                  SELECT 1
                                    FROM so4_so_temp
                                   WHERE figlio = d_figlio
                                         AND padre = d_padre);
           exception when others then
             raise_application_error (-20999,'Errore in SO4_SO_TEMP:'|| d_struttura || ' figlio =  ' ||d_figlio || ' :padre=' || d_padre || ' :struttura='|| d_struttura, true);
             end;
         ELSIF p_table = 'SO_TEMP'
         THEN
         begin
            INSERT INTO so_temp
                        (figlio, padre, struttura)
               SELECT d_figlio, d_padre, d_struttura
                 FROM DUAL
                WHERE NOT EXISTS (
                                  SELECT 1
                                    FROM so_temp
                                   WHERE figlio = d_figlio
                                         AND padre = d_padre);
          exception when others then
             raise_application_error (-20999,'Errore in SO_TEMP:'|| d_struttura || ' figlio =  ' ||d_figlio || ' :padre=' || d_padre || ' :struttura='|| d_struttura, true);
             end;
         END IF;
         d_struttura := SUBSTR (d_struttura, 1, ipos1 - 1);
         i := i - 1;
      END LOOP;
      getcampistruttura (d_struttura, d_tipo, d_figlio, d_desc, d_ruolo);
      DBMS_OUTPUT.put_line (   'IN '
                            || p_table
                            || ': '
                            || d_figlio
                            || ' - '
                            || d_padre
                            || '  '
                            || d_struttura
                           );
      IF p_table = 'SO4_SO_TEMP'
      THEN
      begin
         INSERT INTO so4_so_temp
                     (figlio, padre, struttura)
            SELECT d_figlio, '', d_struttura
              FROM DUAL
             WHERE NOT EXISTS (SELECT 1
                                 FROM so4_so_temp
                                WHERE figlio = d_figlio
--                                AND struttura = d_struttura
                                      AND padre IS NULL);
      exception when others then
      raise_application_error (-20999,'Errore in SO4_SO_TEMP:'|| d_struttura || ' figlio =  ' ||d_figlio || ' :padre=' || '' || ' :struttura='|| d_struttura, true);
      end;
      ELSIF p_table = 'SO_TEMP'
      THEN
      begin
         INSERT INTO so_temp
                     (figlio, padre, struttura)
            SELECT d_figlio, '', d_struttura
              FROM DUAL
             WHERE NOT EXISTS (SELECT 1
                                 FROM so_temp
                                WHERE figlio = d_figlio
--                                AND struttura = d_struttura
                                      AND padre IS NULL);
      exception when others then
      raise_application_error (-20999,'Errore in SO_TEMP:'|| d_struttura || ' figlio =  ' ||d_figlio || ' :padre=' || '' || ' :struttura='|| d_struttura, true);
      end;
      END IF;
      RETURN iret;
   END insertsotemp;
   procedure revoca_utente (p_utente IN OUT varchar2)
   IS
   BEGIN
        initialize (p_utente);
        set_stato('R');
        update_utente(p_utente,'N');
   END;
   procedure abilita_utente (p_utente IN OUT varchar2)
   IS
   BEGIN
        initialize (p_utente);
        set_stato('U');
        update_utente(p_utente,'N');
   END;

   PROCEDURE sistema_posizione_gruppi
/******************************************************************************

043 07/01/2019 SNeg In rigenera_so verificare che utente sia nel gruppo corretto
                    in base al fatto che utente sia amministratore o meno
056 19/05/2020 SN    Sistemare posizionamento nel gruppo standard  indipendentemente dal registro Bug#41840
******************************************************************************/
    (p_utente_o_gruppo IN VARCHAR2)
    IS
    BEGIN
    v_is_sistemazione_gruppi := true; -- rev.57
    -- rev.56 il registro viene usato x sistemare i diritti di accesso
--    IF  global_utility.get_registro_amministratore -- rev.49
--        != 'no'
--    THEN
   -- Rev.043 Inizio
--        dbms_output.put_line('AGGIORNA:'||to_char(sysdate,'dd/mm/yyyy hh24:mi:ss'));
        for v_ute in (select decode (nvl(amministratore,'N'),'N','_GR_APP_','_GR_AMM_') gruppo_inserire,
                                    decode (nvl(amministratore,'N'),'N','_GR_AMM_','_GR_APP_') gruppo_togliere,
                                    u.*
                        from utenti u
                       where tipo_utente = 'U'
                         and utente like p_utente_o_gruppo
                         and (not exists (select 1 from utenti_gruppo -- gruppo inserire
                                         where utente = u.utente
                                           and gruppo =  decode (nvl(u.amministratore,'N'),'N','_GR_APP_','_GR_AMM_'))
                              OR exists (select 1 from utenti_gruppo -- gruppo togliere
                                         where utente = u.utente
                                           and gruppo =  decode (nvl(u.amministratore,'N'),'N','_GR_AMM_','_GR_APP_')))) loop
        delete utenti_gruppo
         where utente = v_ute.utente
           and gruppo = v_ute.gruppo_togliere;
--        dbms_output.put_line('cancellati:'|| sql%rowcount);
        insert into utenti_gruppo(utente, gruppo)
        select v_ute.utente, v_ute.gruppo_inserire
          from dual
         where not exists (select 1 from utenti_gruppo
                            where utente = v_ute.utente
                              and gruppo =  v_ute.gruppo_inserire);
--        dbms_output.put_line('inseriti:'|| sql%rowcount);
         UTENTE.AGGIORNA_PWD_DA_MODIFICARE(v_ute.utente);

--        dbms_output.put_line('FINITO:'||to_char(sysdate,'dd/mm/yyyy hh24:mi:ss   #') || v_ute.utente);
        end loop;
      -- Rev.043 Fine
--      dbms_output.put_line('dopo sistemazioni sui gruppi');
--      END IF;

    v_is_sistemazione_gruppi := false;
    exception
    when others then
        v_is_sistemazione_gruppi := false;
        raise;
      end;

   FUNCTION rigenera_so
/******************************************************************************
 NOME:        RIGENERA_SO.
 DESCRIZIONE: Permette di rigenerare l'albero dei gruppi corrispondenti alla
              struttura organizzativa (viene lanciata ad ogni login di ogni
              utente per la rigenerazione del sottoalbero a cui appartiene e
              puo' essere lanciata 'a comando' tramite l'attivazione di un job
              da applicativo per rigenerare l'intero albero).
 ARGOMENTI:   p_utente_o_gruppo: codice utente o gruppo per cui si vuole
                                 rigenerare la struttura (% per rigenerare
                                 l'intero albero).
 ECCEZIONI:   --
 ANNOTAZIONI: La procedure viene eseguita in una transaction separata in quanto
              esegue commit/rollback.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 7    27/10/2006 MM     Creazione.
 8    12/01/2007 MM     Correzione contatore record.
      26/04/2007 MM     A20741.0.0: modificata in caso di chiamata per un
                        singolo utente / gruppo con chiamata ricorsiva sulle
                        unita' che risultano ancora in ad4 ma non dalla
                        struttura ed eliminazione parte di codice per la
                        gestione del 'ricalcolo' dei diritti di accesso di
                        un utente / gruppo in caso di eliminazione da un gruppo
                        in quanto gestito nel trigger di delete.
 14  29/12/2009  SNeg  Aggiunto aggiornamento tabella utente_aoo_ruolo_gruppo
 16  01/06/2010  SNeg  Utente in stato R quando tolto da struttura e stato U
                       quando inserito in struttura.
 17 12/10/2010  SNeg  Messo controllo per problema  a regione marche
20  17/01/2010 SNeg   Sistemazione errori per revoca e abilitazione utente in  base a SO4
028  19/06/2012 SNeg In rigenera_so modificato comportamento che se eredita diritto di accesso dalla
struttura il diritto viene forzatamente indicato che è ereditato da quel gruppo così se poi verrà
tolto dal gruppo in automatico verrà tolto.
035 12/05/2015 SN Controllo che inserisce in insertsotemp solo se fornito valore non nullo
037 04/06/2015 SN     Evitare cancellazione di utenti_gruppo se un ruolo di struttura
                     viene inserito in un gruppo di ad4.
039 12/04/2017 SN   Smonatare modifica x ereditare dai gruppi senza curarsi dei diritti
                    diretti per un utente
045 14/01/2019 SNeg Tolta autonomous transaction nella rigenera_so e messa indicazione degli errori nella key_error_log
******************************************************************************/
   (p_utente_o_gruppo IN VARCHAR2)
      RETURN NUMBER
   IS
--      PRAGMA AUTONOMOUS_TRANSACTION; -- rev. 045
      d_uo_padre          afc.t_ref_cursor;
      d_statement         afc.t_statement;
      d_so4_uo_padre      afc.t_ref_cursor;
      d_so4_struttura     VARCHAR2 (1000);
      d_uo_struttura      VARCHAR2 (1000);
      d_uo_dep            VARCHAR2 (8);
      bcontrolla          BOOLEAN              := TRUE;
      d_so_count          NUMBER               := 0;
      d_so4_so_count      NUMBER               := 0;
      d_check             NUMBER;
      d_utente_o_gruppo   utenti.utente%TYPE;
--      TabDiac DiacDelTab;
      TYPE uotab IS TABLE OF VARCHAR2 (8)
         INDEX BY BINARY_INTEGER;
      d_uotab             uotab;
      i                   INTEGER;
      d_index             BINARY_INTEGER;
      d_err_id            NUMBER               := 0;
      d_AllineaStatoConSO4 REGISTRO.VALORE%TYPE;
   BEGIN

      /* Controlla che sia attiva l'integrazione con la Struttura Organizzativa */
      BEGIN
         SELECT 1
           INTO d_check
           FROM user_objects
          WHERE object_name = 'SO4_UTIL' AND object_type = 'PACKAGE BODY';
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_check := 0;
      END;
      IF d_check = 1
      THEN
         d_AllineaStatoConSO4:= nvl(registro_utility.leggi_stringa (UPPER ('PRODUCTS/AD4/UTENTE')
                                       , 'AllineaStatoConSO4'
                                       , 0
                                 ),'NO');
         d_utente_o_gruppo := NVL (p_utente_o_gruppo, '%');
         DBMS_OUTPUT.put_line
                       (   'Controlla la Struttura Organizzativa per utente '
                        || d_utente_o_gruppo
                       );
         /* Controlla la Struttura Organizzativa */
         d_so4_uo_padre :=
               so4_util.get_struttura (p_utente_o_gruppo      => d_utente_o_gruppo);
         IF d_so4_uo_padre%ISOPEN
         THEN
            DBMS_OUTPUT.put_line ('Esamino il ref cursor');
            FETCH d_so4_uo_padre
             INTO d_so4_struttura;
            WHILE d_so4_uo_padre%FOUND
            LOOP
               if d_so4_struttura is not null then
               -- problema a regione marche messo il controllo
                  d_so4_so_count :=
                                insertsotemp (d_so4_struttura, 'SO4_SO_TEMP');
               end if;
               FETCH d_so4_uo_padre
                INTO d_so4_struttura;
            END LOOP;
            -- 8    12/01/2007 MM     Correzione contatore record.
            SELECT COUNT (1)
              INTO d_so4_so_count
              FROM so4_so_temp;
            d_statement :=
                  ' select struttura'
               || '   from struttura_utenti'
               || '  where figlio like '''
               || REPLACE (d_utente_o_gruppo, '''', '''''')
               || ''''
               || '    and tipo_padre = ''O''';
            d_uo_padre := afc_dml.get_ref_cursor (d_statement);
            FETCH d_uo_padre
             INTO d_uo_struttura;
            WHILE d_uo_padre%FOUND
            LOOP
               -- 035 12/05/2015 SN    Inserimento solo se non nullo
                if d_uo_struttura is not null then
                  d_so_count := insertsotemp (d_uo_struttura, 'SO_TEMP');
               end if;
               FETCH d_uo_padre
                INTO d_uo_struttura;
            END LOOP;
            CLOSE D_UO_PADRE; -- rev. 51 chiusura cursore
            -- 8    12/01/2007 MM     Correzione contatore record.
            SELECT COUNT (1)
              INTO d_so_count
              FROM so_temp;
            DBMS_OUTPUT.put_line ('d_so_count: ' || d_so_count);
            DBMS_OUTPUT.put_line ('d_so4_so_count: ' || d_so4_so_count);
            IF d_so4_so_count > 0 OR d_so_count > 0
            THEN
             if upper(d_AllineaStatoConSO4) = 'YES'
              then
                for v_uten in (SELECT figlio
                                      FROM so4_so_temp
                                    WHERE utente.get_stato (figlio, 'Y') != 'U'
                                        AND utente.get_tipo_utente(figlio) = 'U'
                                        AND  padre like 'o%'
                                    MINUS
                                    SELECT figlio
                                      FROM so_temp
                                    WHERE padre like 'o%'
                                  )
                loop
                -- lo metto in struttura
                -- e tipo Utente
                   begin
                      abilita_utente (v_uten.figlio);
                   end;
                   --commit;
                end loop;
                for v_uten in (SELECT figlio
                                      FROM so_temp
                                    WHERE utente.get_stato (figlio, 'Y') = 'U'
                                        AND utente.get_tipo_utente(figlio) = 'U'
                                        AND padre like 'o%'
                                    MINUS
                                    SELECT figlio
                                      FROM so4_so_temp
                                    WHERE  padre like 'o%'
                                  )
                loop
                -- lo metto in struttura
                -- e tipo Utente
                   begin
                      revoca_utente (v_uten.FIGLIO);
                   end;
                   --commit;
                end loop;
            end if;
               IF d_so4_so_count = d_so_count
               THEN
                  SELECT COUNT (1)
                    INTO d_check
                    FROM (SELECT struttura
                            FROM so4_so_temp
                          MINUS
                          SELECT struttura
                            FROM so_temp);
                  IF d_check = 0
                  THEN
                     bcontrolla := FALSE;
                  END IF;
               END IF;
--bControlla := FALSE;
-- IF bControlla THEN
-- DBMS_OUTPUT.PUT_LINE('bControlla: TRUE');
-- ELSE
-- DBMS_OUTPUT.PUT_LINE('bControlla: FALSE');
-- END IF;
               IF bcontrolla
               THEN
                  BEGIN
                     /*  se in SO4 l'utente non risulta, elimina l'utente da tutti i gruppi di tipo 'O'. */
                     IF NVL (d_so4_so_count, 0) = 0
                     THEN
                        -- cancellazione effettiva
                        DELETE      utenti_gruppo
                              WHERE utente LIKE d_utente_o_gruppo
                                AND gruppo IN (SELECT utente
                                                 FROM utenti
                                                WHERE tipo_utente = 'O');
                     ELSE
                        /* se l'utente risulta in SO4, inserisce l'utente in tutti i gruppi a cui  */
                        /* appartiene in SO4 ed elimina i collegamenti tra le UO padri che         */
                        /* non esistono piu' in SO4.                                               */
                        -- REV 38_INIZIO prima cancello poi inserisco
                        DELETE      utenti_gruppo
                              WHERE utente =
                                       DECODE (d_utente_o_gruppo,
                                               '%', utente,
                                               d_utente_o_gruppo
                                              )
                                AND (utente, gruppo) IN (
                                       SELECT figlio, padre
                                         FROM so_temp
                                        WHERE figlio = utenti_gruppo.utente
                                          AND padre IS NOT NULL
                                          -- Rev.37 INIZIO modifica per mantenere ruolo nel gruppo
                                          AND 'O' = (select tipo_utente
                                                     from utenti
                                                    where utente = so_temp.padre)
                                          -- Rev.37 FINE modifica per mantenere ruolo nel gruppo
                                          AND NOT EXISTS (
                                                 SELECT 1
                                                   FROM so4_so_temp
                                                  WHERE so4_so_temp.figlio =
                                                                so_temp.figlio
                                                    AND so4_so_temp.padre =
                                                                 so_temp.padre));
                        -- REV 38_FINE prima cancello poi inserisco
                        -- inserimento effettivo
                       FOR v_ins in (
                           SELECT figlio, padre
                             FROM so4_so_temp
                            WHERE padre IS NOT NULL
                              AND NOT EXISTS (
                                      SELECT 1
                                        FROM utenti_gruppo
                                       WHERE utente = figlio
                                             AND gruppo = padre)) LOOP
                                INSERT INTO utenti_gruppo
                                    (utente, gruppo)
                                    values (v_ins.figlio, v_ins.padre);
                        --13/06/2012  SNeg inizio
                        -- Inserimento deve essere fatto senza tenere conto
                        -- dei diritti di accesso personalizzati.
                        -- Eseguite le chiamate che si fanno da interfaccia client-server
                        -- nel caso in cui NON si voglia tener conto dei diritti di accesso
                        -- personalizzati
                        -- commentati Rev. 39
--                        GRUPPO.DIAC_GRUPPO_INSERT(v_ins.padre, v_ins.figlio,'%','%','T');
--                        GRUPPO.CAAC_GRUPPO_INSERT(v_ins.padre, v_ins.figlio,'%','%','T');
                        END LOOP;
                        --13/06/2012  SNeg fine
                        -- 26/04/2007 MM     A20741.0.0
--                        DELETE UTENTI_GRUPPO
--                         WHERE (Utente, Gruppo) IN (SELECT figlio, padre
--                                                      FROM SO_TEMP
--                                                     WHERE padre IS NOT NULL
--                                                       AND NOT EXISTS (SELECT 1
--                                                                         FROM SO4_SO_TEMP
--                                                                        WHERE SO4_SO_TEMP.figlio = SO_TEMP.figlio
--                                                                          AND SO4_SO_TEMP.padre = SO_TEMP.padre)
--                                                )
--                        ;
                        IF d_utente_o_gruppo <> '%'
                        THEN
                           d_uotab.DELETE;
                           d_index := 0;
                           FOR agg_collegamenti IN
                              (SELECT DISTINCT figlio uo
                                          FROM so_temp, utenti utenti
                                         WHERE (figlio, padre) NOT IN (
                                                         SELECT figlio,
                                                                padre
                                                           FROM so4_so_temp)
                                           AND utenti.utente = figlio
                                           AND utenti.tipo_utente = 'O')
                           LOOP
                              d_index := d_index + 1;
                              d_uotab (d_index) := agg_collegamenti.uo;
                           END LOOP;
                        END IF;
                     -- 26/04/2007 MM     A20741.0.0: fine mod.
                     END IF;
                  END;
               END IF;
            END IF;
         END IF;
--         COMMIT;-- rev. 045
         -- 26/04/2007 MM     A20741.0.0
         FOR i IN NVL (d_uotab.FIRST, 1) .. NVL (d_uotab.LAST, 0)
         LOOP
            DECLARE
               dep_err_id   NUMBER := 0;
            BEGIN
               dep_err_id := rigenera_so (d_uotab (i));
               IF dep_err_id > d_err_id
               THEN
                  d_err_id := dep_err_id;
               END IF;
            END;
         END LOOP;
      -- 26/04/2007 MM     A20741.0.0: fime mod.
      END IF;
      -- rev. 51 inizio chiusura cursori
      if d_so4_uo_padre%ISOPEN then
         close d_so4_uo_padre;
      end if;
      if d_uo_padre%ISOPEN then
         close d_uo_padre;
      end if;
      -- rev. 51 fine chiusura cursori
      RETURN d_err_id;
   EXCEPTION
      WHEN OTHERS
      THEN
          -- rev. 51 inizio chiusura cursori
          if d_so4_uo_padre%ISOPEN then
             close d_so4_uo_padre;
          end if;
          if d_uo_padre%ISOPEN then
             close d_uo_padre;
          end if;
          -- rev. 51 fine chiusura cursori
         DECLARE
            d_err   VARCHAR2 (32000);
         BEGIN
            d_err := SUBSTR (SQLERRM, 1, 1940);
--            ROLLBACK;            -- rev. 045
            SELECT keel_sq.NEXTVAL
              INTO d_err_id
              FROM DUAL;
              -- fa commit implicito
              key_error_log_pkg.ins
                        (p_error_id => d_err_id,
                         p_error_session =>  USERENV ('sessionid'),
                         p_error_date =>SYSDATE,
                         p_ERROR_TEXT => 'Rigenera Struttura Organizzativa ('''
                                         || NVL (p_utente_o_gruppo, '%')
                                         || ''': '
                                         || d_err,
                         p_error_user => USER,
                         p_ERROR_TYPE => 'E'
                        );
--            COMMIT;
            RETURN d_err_id;
         END;
   END rigenera_so;
   PROCEDURE rigenera_so
/******************************************************************************
 NOME:        RIGENERA_SO.
 DESCRIZIONE: Permette di rigenerare l'albero dei gruppi corrispondenti alla
              struttura organizzativa (viene lanciata ad ogni login di ogni
              utente per la rigenerazione del sottoalbero a cui appartiene e
              puo' essere lanciata 'a comando' tramite l'attivazione di un job
              da applicativo per rigenerare l'intero albero).
 ARGOMENTI:   p_utente_o_gruppo: codice utente o gruppo per cui si vuole
                                 rigenerare la struttura (% per rigenerare
                                 l'intero albero).
 ECCEZIONI:   --
 ANNOTAZIONI: La procedure viene eseguita in una transaction separata in quanto
              esegue commit/rollback.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 7    27/10/2006 MM     Creazione.
 14  29/12/2009  SNeg  Aggiunto aggiornamento tabella utente_aoo_ruolo_gruppo
******************************************************************************/
   (p_utente_o_gruppo IN VARCHAR2)
   IS
      d_err_id   NUMBER := 0;
   BEGIN
      d_err_id := rigenera_so (p_utente_o_gruppo);
   END rigenera_so;
   FUNCTION is_so4_user (p_utente VARCHAR2)
      RETURN NUMBER
   IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        IS_SO4_USER
 DESCRIZIONE: Dato un utente, controlla se appartiene alla struttura organizzativa.
 PARAMETRI:   p_utente varchar2   codice dell'utente
 RITORNA:     number: 1 appartiene
                      0 non appartiene
 ANNOTAZIONI: .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 7    27/11/2006 MM     Creazione.
******************************************************************************/
      dep_esiste   NUMBER;
   BEGIN
      SELECT COUNT (1)
        INTO dep_esiste
        FROM utenti_gruppo utenti_gruppo, utenti utenti
       WHERE utenti_gruppo.utente = p_utente
         AND utenti_gruppo.gruppo = utenti.utente
         AND utenti.tipo_utente = 'O';
      IF dep_esiste >= 1
      THEN
         dep_esiste := 1;
      END IF;
      RETURN dep_esiste;
   END is_so4_user;
   FUNCTION exists_id (p_id_utente IN utenti.id_utente%TYPE)
      RETURN NUMBER
   IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        exists_id
 DESCRIZIONE: Esistenza riga con chiave indicata.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
      d_result   NUMBER;
   BEGIN
      BEGIN
         SELECT 1
           INTO d_result
           FROM utenti
          WHERE id_utente = p_id_utente;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_result := 0;
      END;
      RETURN d_result;
   END exists_id;
   FUNCTION exists_codice (p_utente IN utenti.utente%TYPE)
      RETURN NUMBER
   IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        exists_codice
 DESCRIZIONE: Esistenza riga con codice indicato.
 PARAMETRI:   p_utente codice utente/gruppo.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
      d_result   NUMBER;
   BEGIN
      BEGIN
         SELECT 1
           INTO d_result
           FROM utenti
          WHERE utente = p_utente;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_result := 0;
      END;
      RETURN d_result;
   END exists_codice;
   FUNCTION exists_utente (p_utente IN utenti.utente%TYPE)
      RETURN NUMBER
   IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        exists_utente
 DESCRIZIONE: Esistenza utente con codice utente indicato.
 PARAMETRI:   p_utente codice utente.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
      d_result   NUMBER;
   BEGIN
      BEGIN
         SELECT 1
           INTO d_result
           FROM utenti
          WHERE utente = p_utente AND tipo_utente = 'U';
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_result := 0;
      END;
      RETURN d_result;
   END exists_utente;
   FUNCTION exists_nominativo (p_nominativo IN utenti.nominativo%TYPE)
      RETURN NUMBER
   IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        exists_nominativo
 DESCRIZIONE: Esistenza riga con nominativo indicato.
 PARAMETRI:   p_nominativo nominativo utente/gruppo.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
      d_result   NUMBER;
   BEGIN
      BEGIN
         SELECT 1
           INTO d_result
           FROM utenti
          WHERE nominativo = p_nominativo;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_result := 0;
      END;
      RETURN d_result;
   END exists_nominativo;
   FUNCTION exists_username (p_username IN utenti.nominativo%TYPE)
      RETURN NUMBER
   IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        exists_username
 DESCRIZIONE: Esistenza riga con nominativo indicato.
 PARAMETRI:   p_username username utente.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
      d_result   NUMBER;
   BEGIN
      BEGIN
         SELECT 1
           INTO d_result
           FROM utenti
          WHERE nominativo = p_username AND tipo_utente = 'U';
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_result := 0;
      END;
      RETURN d_result;
   END exists_username;
   PROCEDURE remove_ldap_authentication (p_utente IN utenti.utente%TYPE)
   IS
      d_caac_id                 caratteristiche_accesso.caac_id%TYPE;
      d_tipo_accesso            caratteristiche_accesso.tipo_accesso%TYPE;
      d_progetto                caratteristiche_accesso.progetto%TYPE;
      d_istanza                 caratteristiche_accesso.istanza%TYPE;
      d_modulo                  caratteristiche_accesso.modulo%TYPE;
      d_accesso                 caratteristiche_accesso.accesso%TYPE;
      d_accesso_se              caratteristiche_accesso.accesso_se%TYPE;
      d_traccia                 caratteristiche_accesso.traccia%TYPE;
      d_giorni_traccia          caratteristiche_accesso.giorni_traccia%TYPE;
      d_tentativi_pwd           caratteristiche_accesso.tentativi_password%TYPE;
      d_val_pwd                 caratteristiche_accesso.validita_password%TYPE;
      d_sleep                   caratteristiche_accesso.sleep%TYPE;
      d_single_sign_on          caratteristiche_accesso.single_sign_on%TYPE;
      d_ldap                    caratteristiche_accesso.ldap%TYPE;
      d_min_lung_pwd            caratteristiche_accesso.min_lunghezza_pwd%TYPE;
      d_mod_pwd_primo_accesso   caratteristiche_accesso.mod_pwd_primo_accesso%TYPE;
      d_arch_traccia            caratteristiche_accesso.archiviazione_traccia%TYPE;
      d_disl_traccia            caratteristiche_accesso.dislocazione_traccia%TYPE;
      d_car_speciali_pwd        caratteristiche_accesso.ammessi_car_speciali_pwd%TYPE;
      d_num_obb_pwd             caratteristiche_accesso.numeri_obb_pwd%TYPE;
      d_giorni_prima_riutilizzo_pwd  CARATTERISTICHE_ACCESSO.GG_PRIMA_RIUTILIZZO_PWD%TYPE;
   BEGIN
      FOR diac IN (SELECT diac.modulo, diac.istanza, ista.progetto
                     FROM diritti_accesso diac, istanze ista
                    WHERE ista.istanza = diac.istanza
                      AND diac.utente = p_utente)
      LOOP
         BEGIN
            d_tipo_accesso := 'D';
            caratteristica_accesso.get_effettive (d_tipo_accesso,
                                                  diac.progetto,
                                                  diac.istanza,
                                                  diac.modulo,
                                                  p_utente,
                                                  d_accesso,
                                                  d_accesso_se,
                                                  d_traccia,
                                                  d_giorni_traccia,
                                                  d_tentativi_pwd,
                                                  d_val_pwd,
                                                  d_sleep,
                                                  d_single_sign_on,
                                                  d_ldap,
                                                  d_min_lung_pwd,
                                                  d_mod_pwd_primo_accesso,
                                                  d_arch_traccia,
                                                  d_disl_traccia,
                                                  d_car_speciali_pwd,
                                                  d_num_obb_pwd,
                                                  d_giorni_prima_riutilizzo_pwd
                                                 );
            BEGIN
               SELECT caac_id
                 INTO d_caac_id
                 FROM caratteristiche_accesso
                WHERE tipo_accesso = 'D'
                  AND progetto = diac.progetto
                  AND istanza = diac.istanza
                  AND modulo = diac.modulo
                  AND utente = p_utente;
               d_tipo_accesso := 'D';
               d_ldap := 'NO';
               caratteristica_accesso.update_caratteristica
                                                     (d_caac_id,
                                                      diac.progetto,
                                                      diac.istanza,
                                                      diac.modulo,
                                                      p_utente,
                                                      d_caac_id,
                                                      d_tipo_accesso,
                                                      diac.progetto,
                                                      diac.istanza,
                                                      diac.modulo,
                                                      p_utente,
                                                      d_accesso,
                                                      d_accesso_se,
                                                      d_traccia,
                                                      d_giorni_traccia,
                                                      d_tentativi_pwd,
                                                      d_val_pwd,
                                                      d_sleep,
                                                      d_single_sign_on,
                                                      d_ldap,
                                                      d_min_lung_pwd,
                                                      d_mod_pwd_primo_accesso,
                                                      d_arch_traccia,
                                                      d_disl_traccia,
                                                      d_car_speciali_pwd,
                                                      d_num_obb_pwd,
                                                      0
                                                     );
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  d_caac_id := NULL;
                  d_tipo_accesso := 'D';
                  d_ldap := 'NO';
                  d_caac_id :=
                     caratteristica_accesso.insert_caratteristica
                                                    (d_caac_id,
                                                     d_tipo_accesso,
                                                     diac.progetto,
                                                     diac.istanza,
                                                     diac.modulo,
                                                     p_utente,
                                                     d_accesso,
                                                     d_accesso_se,
                                                     d_traccia,
                                                     d_giorni_traccia,
                                                     d_tentativi_pwd,
                                                     d_val_pwd,
                                                     d_sleep,
                                                     d_single_sign_on,
                                                     d_ldap,
                                                     d_min_lung_pwd,
                                                     d_mod_pwd_primo_accesso,
                                                     d_arch_traccia,
                                                     d_disl_traccia,
                                                     d_car_speciali_pwd,
                                                     d_num_obb_pwd,
                                                     d_giorni_prima_riutilizzo_pwd
                                                    );
               WHEN OTHERS
               THEN
                  RAISE;
            END;
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END LOOP;
   END;
   PROCEDURE elimina_caac_utente (p_utente IN utenti.utente%TYPE)
   IS
   BEGIN
      FOR caac IN (SELECT caac_id
                     FROM caratteristiche_accesso
                    WHERE utente = p_utente AND tipo_accesso = 'D')
      LOOP
         BEGIN
            caratteristiche_accesso_tpk.del (0, caac.caac_id);
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END LOOP;
   END;
   PROCEDURE gestisci_si_si3 (
      p_si_si3                     VARCHAR2,
      p_insert_or_update           VARCHAR2,
      p_new_utente                 VARCHAR2,
      p_new_nominativo             VARCHAR2,
      p_new_password               VARCHAR2,
      p_new_data_password          VARCHAR2,
      p_new_note                   VARCHAR2,
      p_new_lingua                 VARCHAR2,
      p_new_gruppo_lavoro          VARCHAR2,
      p_new_importanza             VARCHAR2,
      p_new_data_accesso           VARCHAR2,
      p_new_tentativi              VARCHAR2,
      p_new_giorni_password        VARCHAR2,
      p_new_traccia                VARCHAR2,
      p_new_tentativi_password     VARCHAR2,
      p_new_accesso                VARCHAR2,
      p_new_rinnovo_password       VARCHAR2,
      p_new_utente_aggiornamento   VARCHAR2,
      p_old_utente                 VARCHAR2,
      p_old_nominativo             VARCHAR2,
      p_old_giorni_password        VARCHAR2,
      p_old_traccia                VARCHAR2,
      p_old_tentativi_password     VARCHAR2,
      p_old_accesso                VARCHAR2,
      p_is_accesso                 NUMBER
   )
   /******************************************************************************
 NOME:        GESTISCI_SI_SI3
 DESCRIZIONE: Gestione modifiche se arrivano da SI o A00
 ARGOMENTI:
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 015  03/05/2010 SNeg   Copio si4.utente in ad4 se arrivo da a00 o si
 023  02/09/2011 SNeg   Tolta autonomous transaction da attivare x MASTER/SLAVE
******************************************************************************/
   IS
      d_modulo              moduli.modulo%TYPE;
      d_istanza             istanze.istanza%TYPE;
      d_progetto            progetti.progetto%TYPE;
      d_new_data_password   utenti.data_password%TYPE;
      d_new_data_accesso    utenti.ultimo_tentativo%TYPE;
      dep_esiste            NUMBER (1);
      d_session_id          NUMBER;
--      PRAGMA AUTONOMOUS_TRANSACTION;--attivare x MASTER/SLAVE
   BEGIN
    -- se vengo da ambiente diverso da ad4 devo copiare
    -- il valore del campo utente anche nel package di ad4.
      execute immediate ('begin si4.utente := '||user || '.si4.utente; end;');
      IF p_si_si3 = 'SI3'
      THEN
         d_progetto := 'SI4';
         d_istanza := 'SI3';
         d_modulo := 'SI4_V3';
      ELSIF p_si_si3 = 'SI'
      THEN
         d_progetto := 'SI4';
         d_istanza := 'SI';
         d_modulo := 'SI4_V2';
      END IF;
      IF p_insert_or_update = 'I'
      THEN
         DECLARE
            dep_id_utente   NUMBER;
            dep_utente      VARCHAR2 (8) := p_new_utente;
         BEGIN
            SELECT COUNT (1)
              INTO dep_esiste
              FROM utenti
             WHERE utente = p_new_utente OR nominativo = p_new_nominativo;
            IF dep_esiste = 0
            THEN
               SELECT NVL (MAX (id_utente), 0) + 1
                 INTO dep_id_utente
                 FROM utenti;
               utente.ins (p_nominativo            => p_new_nominativo,
                           p_utente                => dep_utente,
                           p_id_utente             => dep_id_utente,
                           p_password              => p_new_password,
                           p_data_password         => p_new_data_password,
                           p_rinnovo_password      => p_new_rinnovo_password,
                           p_note                  => p_new_note,
                           p_lingua                => p_new_lingua,
                           p_gruppo_lavoro         => p_new_gruppo_lavoro,
                           p_importanza            => p_new_importanza,
                           p_is_psw_crypted        => 1
                          );
               utente.initialize (p_new_utente);
               utente.set_ultimo_tentativo (p_new_data_accesso);
               utente.set_numero_tentativi (p_new_tentativi);
               utente.update_utente (p_modifica_sogg => 'N');
            END IF;
            integritypackage.LOG ('inserito utente ' || dep_utente);
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
         BEGIN
            DECLARE
               d_esiste   INTEGER;
            BEGIN
               SELECT COUNT (1)
                 INTO d_esiste
                 FROM ad4_diritti_accesso
                WHERE istanza = d_istanza
                  AND modulo = d_modulo
                  AND utente = p_new_utente;
               IF d_esiste = 0
               THEN
                  diritti_accesso_tpk.ins (p_utente       => p_new_utente,
                                               p_modulo       => d_modulo,
                                               p_istanza      => d_istanza,
                                               p_ruolo        => 'AMM'
                                              );
               END IF;
            END;
         END;
      END IF;
      IF p_insert_or_update = 'U'
      THEN
         DECLARE
            dep_note_tab         utenti.note%TYPE;
            dep_nominativo_tab   utenti.nominativo%TYPE;
            dep_psw              utenti.PASSWORD%TYPE;
         BEGIN
            utente.initialize (p_old_utente);
            dep_nominativo_tab := utente.get_nominativo (p_old_utente);
            IF p_old_nominativo = dep_nominativo_tab
            THEN
               utente.set_nominativo (p_new_nominativo);
            END IF;
            IF    p_old_nominativo = dep_nominativo_tab
               OR p_new_nominativo = dep_nominativo_tab
            THEN
               dep_psw := utente.get_password (p_old_utente);
               IF NVL (dep_psw, ' ') <> NVL (p_new_password, ' ')
               THEN
                  utente.set_password_crypted (p_new_password);
               END IF;
               dep_note_tab := utente.get_note (p_new_utente);
               utente.set_data_password (p_new_data_password);
               utente.set_rinnovo_password (p_new_rinnovo_password);
               IF d_istanza = 'SI3'
               THEN
                  utente.set_lingua (p_new_lingua);
                  utente.set_gruppo_lavoro (p_new_gruppo_lavoro);
                  utente.set_importanza (p_new_importanza);
               END IF;
               utente.set_note (p_new_note);
            END IF;
            utente.set_ultimo_tentativo (p_new_data_accesso);
            utente.set_numero_tentativi (p_new_tentativi);
            utente.set_utente_agg (p_new_utente_aggiornamento);
            utente.update_utente (p_modifica_sogg => 'N');
            integritypackage.LOG ('aggiornato utente ' || p_new_utente);
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
      IF    NVL (p_new_giorni_password, 0) <> NVL (p_old_giorni_password, 0)
         OR NVL (p_new_traccia, 'M') <> NVL (p_old_traccia, 'M')
         OR NVL (p_new_tentativi_password, 0) <>
                                             NVL (p_old_tentativi_password, 0)
         OR NVL (p_new_accesso, 'L') <> NVL (p_old_accesso, 'L')
      THEN
         DECLARE
            dep_default_caac            BOOLEAN      := FALSE;
            dep_caac_id                 NUMBER;
            dep_accesso                 VARCHAR2 (1);
            dep_accesso_se              VARCHAR2 (2);
            dep_traccia                 VARCHAR2 (1);
            dep_giorni_traccia          NUMBER;
            dep_tentativi_password      NUMBER;
            dep_validita_password       NUMBER;
            dep_single_sign_on          VARCHAR2 (2);
            dep_sleep                   NUMBER;
            dep_ldap                    VARCHAR2 (2);
            dep_min_lunghezza_pwd       NUMBER;
            dep_mod_pwd_primo_accesso   VARCHAR2 (2);
         BEGIN
            SELECT accesso, accesso_se, traccia,
                   giorni_traccia, tentativi_password,
                   validita_password, single_sign_on, sleep,
                   ldap, min_lunghezza_pwd,
                   mod_pwd_primo_accesso, caac_id
              INTO dep_accesso, dep_accesso_se, dep_traccia,
                   dep_giorni_traccia, dep_tentativi_password,
                   dep_validita_password, dep_single_sign_on, dep_sleep,
                   dep_ldap, dep_min_lunghezza_pwd,
                   dep_mod_pwd_primo_accesso, dep_caac_id
              FROM caratteristiche_accesso
             WHERE utente = p_new_utente
               AND modulo = d_modulo
               AND istanza = d_istanza
               AND progetto = d_progetto;
            dep_default_caac :=
                   NVL (dep_accesso_se, 'NO') = 'NO'
               AND NVL (dep_giorni_traccia, 60) = 60
               AND NVL (dep_single_sign_on, 'SI') = 'SI'
               AND dep_sleep IS NULL
               AND NVL (dep_ldap, 'NO') = 'NO'
               AND NVL (dep_min_lunghezza_pwd, 0) = 0
               AND NVL (dep_mod_pwd_primo_accesso, 'NO') = 'NO';
            IF    NVL (p_new_giorni_password, 0) > 0
               OR NVL (p_new_traccia, 'M') <> 'M'
               OR NVL (p_new_tentativi_password, 0) > 0
               OR NVL (p_new_accesso, 'L') <> 'L'
               OR (NOT dep_default_caac)
            THEN
               caratteristiche_accesso_tpk.set_validita_password
                                            (p_caac_id      => dep_caac_id,
                                             p_value        => p_new_giorni_password
                                            );
               IF d_istanza = 'SI3'
               THEN
                  caratteristiche_accesso_tpk.set_traccia
                                                   (p_caac_id      => dep_caac_id,
                                                    p_value        => dep_traccia
                                                   );
                  caratteristiche_accesso_tpk.set_tentativi_password
                                          (p_caac_id      => dep_caac_id,
                                           p_value        => p_new_tentativi_password
                                          );
                  caratteristiche_accesso_tpk.set_accesso
                                                    (p_caac_id      => dep_caac_id,
                                                     p_value        => p_new_accesso
                                                    );
               END IF;
            ELSE
               caratteristiche_accesso_tpk.del (0, dep_caac_id);
            END IF;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               BEGIN
                  DECLARE
                     d_esiste   INTEGER;
                  BEGIN
                     SELECT COUNT (1)
                       INTO d_esiste
                       FROM diritti_accesso
                      WHERE istanza = d_istanza
                        AND modulo = d_modulo
                        AND utente = p_new_utente;
                     IF d_esiste = 0
                     THEN
                        diritti_accesso_tpk.ins (p_new_utente,
                                                 d_modulo,
                                                 d_istanza,
                                                 'AMM'
                                                );
                     END IF;
                  END;
                  SELECT NVL (MAX (caac_id), 0) + 1
                    INTO dep_caac_id
                    FROM caratteristiche_accesso;
                  caratteristiche_accesso_tpk.ins
                            (p_caac_id                    => dep_caac_id,
                             p_tipo_accesso               => 'D',
                             p_progetto                   => d_progetto,
                             p_istanza                    => d_istanza,
                             p_modulo                     => d_modulo,
                             p_utente                     => p_new_utente,
                             p_accesso                    => p_new_accesso,
                             p_accesso_se                 => 'NO',
                             p_traccia                    => dep_traccia,
                             p_giorni_traccia             => 60,
                             p_tentativi_password         => p_new_tentativi_password,
                             p_validita_password          => p_new_giorni_password,
                             p_single_sign_on             => 'SI',
                             p_sleep                      => TO_NUMBER (NULL),
                             p_ldap                       => 'NO',
                             p_min_lunghezza_pwd          => 0,
                             p_mod_pwd_primo_accesso      => 'NO'
                            );
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     raise_application_error (-20999,
                                                 'ERRORE'
                                              || REPLACE (SQLERRM,
                                                          'ORA-',
                                                          'ora-'
                                                         )
                                             );
               END;
            WHEN OTHERS
            THEN
               RAISE;
         END;
         integritypackage.LOG ('gestite caac utente ' || p_new_utente);
      END IF;
--      COMMIT;--attivare x MASTER/SLAVE
      IF p_is_accesso = 1
      THEN
         integritypackage.LOG ('l''utente ' || p_new_utente || ' accede');
         BEGIN
            accesso.check_accesso (d_progetto,
                                   d_istanza,
                                   d_modulo,
                                   p_new_utente
                                  );
            SELECT USERENV ('sessionid')
              INTO d_session_id
              FROM DUAL;
            accesso.registra_accesso (d_session_id,
                                      'ON',
                                      d_istanza,
                                      d_modulo,
                                      p_new_utente,
                                      NULL
                                     );
            integritypackage.LOG ('registra_accesso utente ' || p_new_utente);
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
      END IF;
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
--         ROLLBACK;--attivare x MASTER/SLAVE
         RAISE;
   END;
   function leggi_registro_invio
   ( p_stringa in varchar2)
   return varchar2
   is /* SLAVE_COPY */
      v_testo              VARCHAR2 (32767);
      d_chiave             registro.chiave%TYPE := 'PRODUCTS/AD4/TESTO_INVIO_PASSWORD';
   begin
      v_testo := registro_utility.leggi_stringa (d_chiave, p_stringa, 0);
      IF v_testo IS NULL THEN
         v_testo := registro_utility.leggi_stringa (d_chiave || '/DEFAULT', p_stringa, 0);
      END IF;
      return v_testo;
   end;
   FUNCTION get_mess_invio_password (p_utente varchar2)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      v_rinnovo            INTEGER;
      d_rinnovo_password   varchar2(2);
      v_testo              VARCHAR2 (32767);
   BEGIN
      -- se ha gia fatto login e rinnovo
      SELECT NVL (SUM (1), 0)
        INTO v_rinnovo
        FROM utenti
       WHERE ultimo_tentativo IS not NULL
         AND utente = p_utente;
      d_rinnovo_password := GET_RINNOVO_PASSWORD(p_utente, 'Y');
      v_testo := leggi_registro_invio('1. Header');
      IF v_rinnovo = 0
      THEN
         -- prima assegnazione
         v_testo := v_testo || leggi_registro_invio('1.1 PrimoInvio');
      ELSE
         -- e un rinnovo
         v_testo := v_testo || leggi_registro_invio('1.2 Rinnovo');
      END IF;
      v_testo := v_testo || leggi_registro_invio('2. Precisazione');
      if d_rinnovo_password = 'SI' then
         if caratteristica_accesso.IS_PWD_DA_MOD_PRIMO_USO(p_utente) = 1 then
             v_testo := v_testo || leggi_registro_invio('2.1 ModificaPwdPrimoAccesso');
         else
             v_testo := v_testo || leggi_registro_invio('2.2 SenzaModificaPwdPrimoAccesso');
         end if;
      end if;
      v_testo   := v_testo || leggi_registro_invio('3. Footer');
      return v_testo;
   END get_mess_invio_password;
   FUNCTION get_ogg_invio_password (p_utente varchar2)
      RETURN VARCHAR2
   IS /* SLAVE_COPY */
      v_oggetto            VARCHAR2 (32767);
   BEGIN
      v_oggetto := leggi_registro_invio('Oggetto');
      return v_oggetto;
   END get_ogg_invio_password;
 /******************************************************************************
 NOME:         unificazione_profilo
 DESCRIZIONE: Unifica il profilo di due utenti tenendone uno e l'altro lo mette a revocato.
 PARAMETRI:   p_utente_alimentare IN utente che rimarra abilitato
                      p_utente_revocare  IN utente da revocare
                     p_soggetto_assegnare IN soggetto da assegnare all'utente da tenere sovrascrive eventuali registrazioni
                     p_tenere_utgr_alimentare varchar2  indica se preservo i gruppi dell'utente da alimentare
                     p_tenere_utgr_revocare varchar2  indica se copiare nell'utente da tenere i gruppi dell'utente da revocare
                     p_tenere_diac_alimentare varchar2  indica se preservo i diritti di accesso dell'utente da alimentare
                     p_tenere_diac_revocare varchar2  indica se copiare nell'utente da tenere i diac dell'utente da revocare
                     p_tenere_caac_alimentare varchar2  indica se preservo carattteristiche di accesso dell'utente da alimentare
                     p_tenere_caac_revocare varchar2  indica se copiare nell'utente da tenere le caac dell'utente da revocare
******************************************************************************/
PROCEDURE  unifica_profilo
( p_utente_alimentare  IN UTENTI.UTENTE%TYPE
, p_utente_revocare  IN UTENTI.UTENTE%TYPE
, p_soggetto_assegnare IN UTENTI_SOGGETTI.SOGGETTO%TYPE default null
, p_tenere_utgr_alimentare varchar2 default 'SI'
, p_copiare_utgr_revocare varchar2 default 'SI'
, p_tenere_diac_alimentare varchar2 default 'SI'
, p_copiare_diac_revocare varchar2 default 'SI'
, p_tenere_caac_alimentare varchar2 default 'SI'
, p_copiare_caac_revocare varchar2 default 'SI'
, p_utente_agg utenti.utente%TYPE default user
)
IS
v_utente_aggiornare utenti.utente%TYPE;
v_modalita_aggiornamento_sogg varchar2(1):= 'P';
BEGIN
--raise_application_error(-20999,p_soggetto_assegnare);
initialize (p_utente_alimentare);
if p_soggetto_assegnare is not null then
    delete utenti_soggetti
    where soggetto = p_soggetto_assegnare;
    --  assegno soggetto
    set_soggetto(p_soggetto_assegnare);
else
v_modalita_aggiornamento_sogg := 'N';
end if;
v_utente_aggiornare := p_utente_alimentare;
update_utente(v_utente_aggiornare,v_modalita_aggiornamento_sogg);
if p_tenere_utgr_alimentare != 'SI' then
    -- cancello gruppi di appartenenza di utente da alimentare
        for grp in (select  gruppo
                     from utenti_gruppo utgr
                   where utente = p_utente_alimentare
                       and utente.get_tipo_utente(utgr.gruppo ) != 'O') loop
    utente_gruppo.del( p_utente => p_utente_alimentare
                               , p_gruppo=> grp.gruppo);
    end loop;
end if;
if p_tenere_diac_alimentare != 'SI' then
-- toglie diritti di accesso diretti
    for diac in (SELECT MODULO, ISTANZA, RUOLO, SEQUENZA,GRUPPO
                      FROM diritti_accesso diac
                     WHERE utente = p_utente_alimentare
                         AND gruppo is null ) loop
    diritti_accesso_tpk.del(p_check_old => 0
                                    , p_utente => p_utente_alimentare
                                    , p_modulo => diac.modulo
                                    , p_istanza   => diac.istanza);
    end loop;
end if;
if p_tenere_caac_alimentare = 'SI' then
    -- toglie caratteristiche dirette per l'utente
    for caac in (select CAAC_ID, TIPO_ACCESSO, PROGETTO,
       ISTANZA, MODULO, UTENTE,
       ACCESSO, ACCESSO_SE, TRACCIA,
       GIORNI_TRACCIA, TENTATIVI_PASSWORD, VALIDITA_PASSWORD,
       SINGLE_SIGN_ON, SLEEP, LDAP,
       MIN_LUNGHEZZA_PWD, MOD_PWD_PRIMO_ACCESSO, ARCHIVIAZIONE_TRACCIA,
       DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD, NUMERI_OBB_PWD
    FROM CARATTERISTICHE_ACCESSO caac
    where tipo_accesso = 'D'
       and utente = p_utente_alimentare) loop
    caratteristiche_accesso_tpk.del(p_check_old => 0, p_caac_id => caac.caac_id);
    end loop;
end if;
copia_profilo(p_utente_alimentare, p_utente_revocare,p_copiare_utgr_revocare, p_copiare_diac_revocare,p_copiare_caac_revocare,1);
-- utente in stato revocato
initialize (p_utente_revocare);
set_stato('R');
v_utente_aggiornare := p_utente_revocare;
update_utente(v_utente_aggiornare,'N');
omonimie_gestite_pkg.ins_upd_column ( p_primario     => p_utente_alimentare
                                                        , p_secondario  => p_utente_revocare
                                                        , p_column       => 'UNIFICATO'
                                                        , p_value          => 1
                                                        , p_utente_agg => p_utente_agg);
END;
/******************************************************************************
 NOME:         copia_profilo
 DESCRIZIONE: Copia il profilo di un utente sull'altro.
 PARAMETRI:   p_utente_alimentare IN utente su cui copiare
                      p_utente_origine  IN utente da cui copiare
                      p_copiare_utgr IN indica se copiare i gruppi di appartenenza
                     p_copiare_diac varchar2  indica se copiare i diritti di accesso
                     p_copiare_caac varchar2  indica se copiare le caratteristiche di accesso
******************************************************************************/
PROCEDURE  copia_profilo
( p_utente_alimentare  IN UTENTI.UTENTE%TYPE
, p_utente_origine  IN UTENTI.UTENTE%TYPE
, p_copiare_utgr varchar2 default 'SI'
, p_copiare_diac varchar2 default 'SI'
, p_copiare_caac  varchar2 default 'SI'
, p_utente_agg utenti.utente%TYPE default user
, p_da_unificare number default 0
)
IS
BEGIN
if p_copiare_utgr = 'SI' then
-- copia gruppi di appartenenza se non gia presente
    for grp in (select  gruppo
                     from utenti_gruppo utgr
                   where utente = p_utente_origine
                       and utente.get_tipo_utente(utgr.gruppo ) != 'O'
                       and not exists ( select 'x'
                                                from utenti_gruppo
                                              where utente = p_utente_alimentare
                                                  and gruppo = utgr.gruppo)) loop
    utente_gruppo. ins( p_utente => p_utente_alimentare
                               , p_gruppo=> grp.gruppo);
    end loop;
end if;
if p_copiare_diac = 'SI' then
-- copia diritti di accesso diretti se non gia presente
    for diac in (SELECT MODULO, ISTANZA, RUOLO, SEQUENZA,GRUPPO
                      FROM diritti_accesso diac
                     WHERE utente = p_utente_origine
                         AND gruppo is null
                           AND NOT EXISTS
                                      (SELECT 'x'
                                         FROM DIRITTI_ACCESSO
                                        WHERE     utente = p_utente_alimentare
                                              AND modulo = diac.modulo
                                              AND istanza = diac.istanza)) loop
    diritti_accesso_tpk.ins( p_utente => p_utente_alimentare
                                    , p_modulo => diac.modulo
                                    , p_istanza   => diac.istanza
                                    , p_ruolo  => diac.ruolo
                                    , p_sequenza   => diac.sequenza
                                    , p_ultimo_accesso => NULL
                                    , p_numero_accessi  => NULL
                                    , p_gruppo  => diac.gruppo);
    end loop;
    -- se devo copiare un ruolo diretto per un modulo istanza che esiste gia devo
    -- sistemare il record esistente
    for diac in (SELECT diac_alimentare.MODULO,diac_alimentare. ISTANZA, diac_alimentare.RUOLO, diac_alimentare.SEQUENZA,diac_origine.GRUPPO,
    diac_origine.modulo modulo_orig, diac_origine.istanza istanza_orig
                      FROM diritti_accesso diac_origine
                              , diritti_accesso diac_alimentare
                     WHERE diac_origine.utente = p_utente_origine
                          AND diac_alimentare.utente = p_utente_alimentare
                          AND diac_alimentare.modulo = diac_origine.modulo
                          AND diac_alimentare.istanza = diac_origine.istanza
                          and diac_alimentare.gruppo is null
                          and diac_origine.gruppo is not null ) loop
     diritti_accesso_tpk.upd
    ( p_check_OLD => 0
    , p_NEW_utente =>  p_utente_alimentare
    , p_NEW_modulo  => diac.modulo
    , p_OLD_modulo => diac.modulo_orig
    , p_NEW_istanza => diac.istanza
    , p_OLD_istanza => diac.istanza_orig
    , p_NEW_ruolo => diac.ruolo
    , p_NEW_gruppo  => null
    , p_OLD_gruppo => diac.gruppo);
    end loop;
end if;
if p_copiare_caac = 'SI' then
    -- copia caratteristiche dirette per l'utente
    -- le altre eventualmente sono gestite in automatico
    for caac in (select TIPO_ACCESSO, PROGETTO,
       ISTANZA, MODULO, UTENTE,
       ACCESSO, ACCESSO_SE, TRACCIA,
       GIORNI_TRACCIA, TENTATIVI_PASSWORD, VALIDITA_PASSWORD,
       SINGLE_SIGN_ON, SLEEP, LDAP,
       MIN_LUNGHEZZA_PWD, MOD_PWD_PRIMO_ACCESSO, ARCHIVIAZIONE_TRACCIA,
       DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD, NUMERI_OBB_PWD
    FROM CARATTERISTICHE_ACCESSO caac
    where tipo_accesso = 'D'
       and utente = p_utente_origine
    and not exists (select 'x'
                            from caratteristiche_accesso
                          where TIPO_ACCESSO = caac.tipo_accesso
                             and PROGETTO = caac.progetto
                             and ISTANZA = caac.istanza
                             and MODULO= caac.modulo
                             and utente = p_utente_alimentare
                             and tipo_accesso = 'D')) loop
    caratteristiche_accesso_tpk.ins
       ( p_tipo_accesso  =>caac.tipo_accesso
       , p_progetto   =>caac.progetto
       , p_istanza  =>caac.istanza
       , p_modulo =>caac.modulo
       , p_utente  => p_utente_alimentare
       , p_accesso   =>caac.accesso
       , p_accesso_se   =>caac.accesso_se
       , p_traccia   =>caac.traccia
       , p_giorni_traccia   =>caac.giorni_traccia
       , p_tentativi_password  =>caac.tentativi_password
       , p_validita_password  =>caac.validita_password
       , p_single_sign_on  =>caac.single_sign_on
       , p_sleep  =>caac.sleep
       , p_ldap  =>caac.ldap
       , p_min_lunghezza_pwd  =>caac.min_lunghezza_pwd
       , p_mod_pwd_primo_accesso  =>caac.mod_pwd_primo_accesso
       , p_archiviazione_traccia   =>caac.archiviazione_traccia
       , p_dislocazione_traccia   =>caac.dislocazione_traccia
       , p_ammessi_car_speciali_pwd   =>caac.ammessi_car_speciali_pwd
       , p_numeri_obb_pwd  =>caac.numeri_obb_pwd);
    end loop;
end if;
if  p_da_unificare != 0 then
    omonimie_gestite_pkg.ins_upd_column ( p_primario     => p_utente_alimentare
                                                            , p_secondario  => p_utente_origine
                                                            , p_column       => 'COPIATO'
                                                            , p_value          => 1
                                                            , p_utente_agg => p_utente_agg);
end if;
END;
FUNCTION  CALCOLA_ATTRIBUTO(p_valore varchar2
                              , p_ni varchar2)
/******************************************************************************
 NOME:        CALCOLA_ATTRIBUTO.
 DESCRIZIONE: Sostituisce al campo indicato il valore per il soggetto specifico
 PARAMETRI:   p_valore: stringa che definisce che attributi usare
              p_ni    : chiave del soggetto
 RITORNA:     stringa varchar2 contenente i valori attualilzzati
 ECCEZIONI:   --
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  033 19/02/2014 SN  Tolto raddoppio apici sul nome
******************************************************************************/
   RETURN varchar2
   IS
   d_nome        soggetti.nome%TYPE;
   d_nome_vero   soggetti.nome%TYPE;
   d_cognome     soggetti.nome%TYPE;
   d_email       soggetti.indirizzo_web%TYPE;
   d_codice_fiscale soggetti.codice_fiscale%type;
   d_valore     varchar2(2000);
  -- d_descrizione varchar2(2000) := replace(p_descrizione,'''','''''');
   BEGIN
   -- sistemazione variabili con attributi utente
      d_valore := p_valore;
      d_nome   :=
         replace(INITCAP (soggetto.get_nome (p_ni
                                   , 'Y'
                  )
         ),'''','''''')
         ;
      d_nome_vero   :=
      -- rev 33 tolto raddoppio apici già fatto trovando il d_nome
         SUBSTR (d_nome
               , INSTR (d_nome
                      , '  '
                 )
                 + 2);
      -- tengo solo nome
      d_cognome   :=
         replace(INITCAP (soggetto.get_cognome (P_NI
                                      , 'Y'
                  )
         ),'''','''''');
      d_email   :=
         replace(LOWER (soggetto.get_indirizzo_web (p_ni
                                          , 'Y'
                )
         ),'''','''''');
      d_codice_fiscale   :=
         replace(soggetto.get_codice_fiscale (p_ni
                                          , 'Y'
                                            )
                ,'''','''''');
         -- se inizia con = deve essere valorizzato il valore????
         d_valore   :=
            REPLACE (d_valore
                   , '%ni%'
                   , p_ni
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%NI%'
                   , p_ni
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%nome%'
                   , d_nome_vero
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%NOME%'
                   , d_nome_vero
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%cognome%'
                   , d_cognome
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%COGNOME%'
                   , d_cognome
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%email%'
                   , d_email
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%EMAIL%'
                   , d_email
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%CODICE_FISCALE%'
                   , d_codice_fiscale
            );
             d_valore   :=
            REPLACE (d_valore
                   , '%codice_fiscale%'
                   , d_codice_fiscale
            );
         -- se inizia con = deve essere valorizzato il valore
         if substr(ltrim(d_valore),1,1) = '=' then
            execute immediate
             'begin :d_valore := '|| ltrim(ltrim(d_valore),'=')|| ';end; '
            using OUT d_valore;
         end if;
   return d_valore;
   END;
function DETERMINA_NOMINATIVO_SOGGETTO(P_NI IN NUMBER)
/******************************************************************************
 NOME:        DETERMINA_NOMINATIVO_SOGGETTO.
 DESCRIZIONE: Dato un soggetto determina il nominativo utilizzando la regola
              impostata nel registro per "SintassiNominativoAutomatico"
 PARAMETRI:   p_ni    : chiave del soggetto
 RITORNA:     stringa varchar2 contenente il nominativo
 ECCEZIONI:   --
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 031 05/06/2013 SNeg Corretto errore che non consentiva uso della regola del registro
                     per determinazione nominativo
 032 16/07/2013 AD  Corretto errore se separatore nullo
******************************************************************************/
return varchar2 is
   primo_campo              varchar2(20);
   secondo_campo            varchar2(20);
   v_sintassi_nominativo    varchar2(2000) ;
   var_sintassi_nominativo varchar2(2000) ;
   w_nominativo             varchar2(100);
   num_caratteri            varchar2(20);
   W_caratteri_ci           varchar2(10);
   W_PREFISSO               VARCHAR2(200):= NULL;
   v_valore                   varchar2(32767);
   v_utente       utenti.utente%TYPE;
   v_nome varchar2(2000);
   v_cognome varchar2(2000);
   v_codice_fiscale varchar2(16);
   v_caratteri_nome number;
   v_caratteri_cognome number;
   v_contatore number :=1;
caratteri_primo_campo    number;
caratteri_secondo_campo   number;
PROGRESSIVO         number := 0;
omonimie_lecco_risolte    number := 0;
v_separatore varchar2(100);
begin
 v_sintassi_nominativo := AMV_MENU.GET_REGISTRO('PRODUCTS/AD4/UTENTE','SintassiNominativoAutomatico');
 var_sintassi_nominativo := v_sintassi_nominativo;
 v_utente :=  utente.get_utente(p_ni);
if substr(ltrim( v_sintassi_nominativo),1,1) = '=' then
    w_nominativo :=  calcola_attributo ( p_valore => v_sintassi_nominativo
                                                       , p_ni => p_ni);
else
  -- se non ha = come primo carattere nella sintassi uso le regole
  v_nome := translate(soggetto.get_nome(p_ni, p_completo => 0),'x''- .,','x');
  v_cognome := translate(soggetto.get_cognome(p_ni),'x''- .,','x');
  if v_cognome is null then
      raise_application_error (-20999 ,'Impossibile determinare UTENTE, non esiste il cognome del soggetto '||p_ni);
  end if;
  v_codice_fiscale := soggetto.get_codice_fiscale(p_ni);
  IF v_SINTASSI_NOMINATIVO IS not NULL then
     IF ( instr(upper(v_sintassi_nominativo),'%NOME%') != 0 or
         instr(upper(v_sintassi_nominativo),'%COGNOME%') != 0 ) then  -- modificato %COGNOM%E
         primo_campo := upper(substr(v_sintassi_nominativo,1,instr(v_sintassi_nominativo,'%',1,2)));
         v_sintassi_nominativo := substr(v_sintassi_nominativo,instr(v_sintassi_nominativo,'%',1,2)+1);
         if substr(ltrim(v_sintassi_nominativo),1,1)= '[' then -- indicata lunghezza
             num_caratteri := nvl(substr(v_sintassi_nominativo,2,instr(v_sintassi_nominativo,']')-2),'40');
             caratteri_primo_campo := to_number(num_caratteri);
             v_sintassi_nominativo := substr(v_sintassi_nominativo,instr(v_sintassi_nominativo,']')+1);
         end if;
         -- contollo se c'e un separatore
         if instr(upper(v_sintassi_nominativo),'%COGNOME%') >0 then
            v_separatore := substr(v_sintassi_nominativo,1,instr(upper(v_sintassi_nominativo),'%COGNOME%')-1 );
            -- aggiunta nvl su length del separatore nel caso questo non fosse stato specificato
            v_sintassi_nominativo := substr(v_sintassi_nominativo,nvl(length(v_separatore),0)+1);
         elsif instr(upper(v_sintassi_nominativo),'%NOME%') >0 then
            v_separatore := substr(v_sintassi_nominativo,1,instr(upper(v_sintassi_nominativo),'%NOME%')-1 );
            -- aggiunta nvl su length del separatore nel caso questo non fosse stato specificato
            v_sintassi_nominativo := substr(v_sintassi_nominativo,nvl(length(v_separatore),0)+1);
         end if;
         secondo_campo := upper(substr(v_sintassi_nominativo,1,instr(v_sintassi_nominativo,'%',1,2)));
         v_sintassi_nominativo := substr(v_sintassi_nominativo,instr(v_sintassi_nominativo,'%',1,2)+1);
         if substr(ltrim(v_sintassi_nominativo),1,1)= '[' then -- indicata lunghezza
             num_caratteri := nvl(substr(v_sintassi_nominativo,2,instr(v_sintassi_nominativo,']')-2),'40');
             caratteri_secondo_campo := to_number(num_caratteri);
         end if;
          if upper(primo_campo) = '%NOME%' then
             v_caratteri_nome := nvl(caratteri_primo_campo,40);
             v_caratteri_cognome := nvl(caratteri_secondo_campo,40);
          else
             v_caratteri_nome :=nvl(caratteri_secondo_campo,40) ;
             v_caratteri_cognome := nvl(caratteri_primo_campo,40);
          end if;
      elsif instr(v_sintassi_nominativo,'%CODICE_FISCALE%') != 0 then     /* usano il CODICE_FISCALE come account */
            W_NOMINATIVO := v_CODICE_FISCALE;
      end if;
  else
      w_nominativo := substr(v_cognome||' '||v_nome,1,40);
  end if;
 if UTENTE.EXISTS_NOMINATIVO(w_nominativo) = 1 and v_sintassi_nominativo= '%CODICE_FISCALE%' then
      raise_application_error (-20999 ,'Esiste gia'' in AD4 un UTENTE con il nominativo:' ||w_nominativo ||' con la regola: '||var_sintassi_nominativo);
   end if;
   -- compongo i campi indicati secondo le regole previste dal registro
  loop
    IF primo_campo IS not NULL THEN
          if upper(primo_campo) = '%NOME%' then
           w_nominativo := substr(v_nome,1,v_caratteri_nome);
         else
             w_nominativo := substr(v_cognome,1,v_caratteri_cognome);
         end if;
         w_nominativo := w_nominativo || v_separatore;
         if upper(secondo_campo) = '%NOME%' then
             w_nominativo := w_nominativo||substr(v_nome,1,v_caratteri_nome);
         elsif upper(secondo_campo) = '%COGNOME%' then
             w_nominativo := w_nominativo||substr(v_cognome,1,v_caratteri_cognome);
         end if;
   end if;
   exit when UTENTE.EXISTS_NOMINATIVO(substr(w_nominativo,1,40)) != 1;
   exit when length(v_nome) <= v_caratteri_nome AND length(v_cognome) <= v_caratteri_cognome;
   -- se sono qui il nome scelto esisteva gia''
   -- per codice fiscale non dovrebbe mai succedere...
   if (v_caratteri_nome != 40 and length(v_nome )> v_caratteri_nome) then
    -- provo prima ad usare caratteri inutilizzati
      v_caratteri_nome := v_caratteri_nome +1;
   elsif (v_caratteri_cognome != 40 and length(v_cognome )> v_caratteri_cognome) then
    -- provo prima ad usare caratteri inutilizzati
      v_caratteri_cognome := v_caratteri_cognome +1;
   end if;
   end loop;
   -- uscito perche ha trovato un nominativo libero?
  end if; -- caso in cui ho usato la regola
   if UTENTE.EXISTS_NOMINATIVO(substr(w_nominativo,1,40)) != 1 then
  null;
   else
  -- ho gia'' usato tutti i caratteri o ho usato una funzione
   loop
    if UTENTE.EXISTS_NOMINATIVO(substr(w_nominativo,1,40 - length(v_contatore))|| v_contatore) != 1
    then
    w_nominativo := substr(w_nominativo,1,40 - length(v_contatore))|| v_contatore;
    exit;
    else
    v_contatore := v_contatore +1 ;
    end if;
    end loop;
   end if;
    if w_nominativo is null then
      raise_application_error (-20999 ,'Impossibile determinare il nominativo per creare UTENTE con la regola:'||var_sintassi_nominativo);
   end if;
   return substr(w_nominativo,1,40);
end;
function CALCOLA_VALORE(p_sintassi varchar2
                       ,p_ni number)
/******************************************************************************
 NOME:        CALCOLA_VALORE.
 DESCRIZIONE: Data una sintassi sostituisce i valori "reali"
 PARAMETRI:   p_sintassi : stringa che contiene la regola per attualizzare i valori
              p_ni    : chiave del soggetto
 RITORNA:     stringa varchar2 con i valori del soggetto secondo la sintassi
                               indicata.
 ECCEZIONI:   --
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
******************************************************************************/
return varchar2 is
    primo_campo              varchar2(20);
    secondo_campo            varchar2(20);
    v_sintassi    varchar2(2000) := p_sintassi ;
    num_caratteri            varchar2(2);
    v_valore                   varchar2(32767);
    v_nome varchar2(2000);
    v_cognome varchar2(2000);
    v_codice_fiscale varchar2(16);
    v_caratteri_nome number;
    v_caratteri_cognome number;
    v_contatore number :=1;
    caratteri_primo_campo    number;
    caratteri_secondo_campo   number;
    v_separatore varchar2(100);
    v_inizio_fisso varchar2(100);
begin
if substr(ltrim( v_sintassi),1,1) = '=' then
    v_valore :=  calcola_attributo ( p_valore => v_sintassi
                                                       , p_ni => p_ni);
else
  -- se non ha = come primo carattere nella sintassi uso le regole
  v_nome := translate(soggetto.get_nome(p_ni, p_completo => 0),'x''- .,','x');
  v_cognome := translate(soggetto.get_cognome(p_ni),'x''- .,','x');
  if v_cognome is null then
      raise_application_error (-20999 ,'Impossibile determinare UTENTE, non esiste il cognome del soggetto '||p_ni);
  end if;
  v_codice_fiscale := soggetto.get_codice_fiscale(p_ni);
  IF v_sintassi IS not NULL then
      IF ( instr(upper(v_sintassi),'%NOME%') != 0 or
         instr(upper(v_sintassi),'%COGNOME%') != 0 ) then  -- modificato %COGNOM%E
         IF  instr(upper(v_sintassi),'%') > 1  then
             v_inizio_fisso := substr(v_sintassi,1,instr(v_sintassi,'%')-1);
         END IF;
         primo_campo := upper(substr(v_sintassi,1,instr(v_sintassi,'%',1,2)));
         v_sintassi := substr(v_sintassi,instr(v_sintassi,'%',1,2)+1);
         if substr(ltrim(v_sintassi),1,1)= '[' then -- indicata lunghezza
             num_caratteri := nvl(substr(v_sintassi,2,instr(v_sintassi,']')-2),'40');
             caratteri_primo_campo := to_number(num_caratteri);
             v_sintassi := substr(v_sintassi,instr(v_sintassi,']')+1);
         end if;
         -- contollo se c'e un separatore
         if instr(upper(v_sintassi),'%COGNOME%') >0 then
            v_separatore := substr(v_sintassi,1,instr(upper(v_sintassi),'%COGNOME%')-1 );
            -- aggiunta nvl su length del separatore nel caso questo non fosse stato specificato
            v_sintassi := substr(v_sintassi,nvl(length(v_separatore),0)+1);
         elsif instr(upper(v_sintassi),'%NOME%') >0 then
            v_separatore := substr(v_sintassi,1,instr(upper(v_sintassi),'%NOME%')-1 );
            -- aggiunta nvl su length del separatore nel caso questo non fosse stato specificato
            v_sintassi := substr(v_sintassi,nvl(length(v_separatore),0)+1);
         end if;
         secondo_campo := upper(substr(v_sintassi,1,instr(v_sintassi,'%',1,2)));
         v_sintassi := substr(v_sintassi,instr(v_sintassi,'%',1,2)+1);
         if substr(ltrim(v_sintassi),1,1)= '[' then -- indicata lunghezza
             num_caratteri := nvl(substr(v_sintassi,2,instr(v_sintassi,']')-2),'40');
             caratteri_secondo_campo := to_number(num_caratteri);
         end if;
          if upper(primo_campo) = '%NOME%' then
             v_caratteri_nome := nvl(caratteri_primo_campo,40);
             v_caratteri_cognome := nvl(caratteri_secondo_campo,40);
          else
             v_caratteri_nome :=nvl(caratteri_secondo_campo,40) ;
             v_caratteri_cognome := nvl(caratteri_primo_campo,40);
          end if;
      elsif instr(v_sintassi,'%CODICE_FISCALE%') != 0 then     /* usano il CODICE_FISCALE come account */
            v_valore := v_CODICE_FISCALE;
      end if;
  else
      v_valore := substr(v_cognome||' '||v_nome,1,40);
  end if;
   -- compongo i campi indicati secondo le regole previste dal registro
    IF primo_campo IS not NULL THEN
          if upper(primo_campo) = '%NOME%' then
           v_valore := v_inizio_fisso || substr(v_nome,1,v_caratteri_nome);
         else
             v_valore := v_inizio_fisso ||  substr(v_cognome,1,v_caratteri_cognome);
         end if;
         v_valore := v_valore || v_separatore;
         if upper(secondo_campo) = '%NOME%' then
             v_valore := v_valore||substr(v_nome,1,v_caratteri_nome);
         elsif upper(secondo_campo) = '%COGNOME%' then
             v_valore := v_valore||substr(v_cognome,1,v_caratteri_cognome);
         end if;
   end if;
end if;
    return substr(translate(v_valore,'x''- .,','x'),1,8);
end calcola_valore;
function determina_utente
/******************************************************************************
 NOME:        determina_utente.
 DESCRIZIONE: Determina il codice da attribuire all utente
 PARAMETRI:   p_ni , ni del soggetto
 RITORNA:     stringa varchar2 contenente il codice con cui creare l'utente
 ECCEZIONI:   --
 ANNOTAZIONI: Al momento si basa sul nominativo determinato.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 027  07/05/2012 SNeg Aggiunta determina utente e determina nominativo.
 031 05/06/2013 SNeg Corretto errore che non consentiva uso della regola del registro
                    per determinazione codice utente.
******************************************************************************/
(p_ni number
) return varchar2
is
v_utente_proposto varchar2(2000) ;
v_sintassi varchar2(2000);
begin
    v_sintassi := AMV_MENU.GET_REGISTRO('PRODUCTS/AD4/UTENTE','SintassiUtenteAutomatico');
    v_utente_proposto :=  utente.get_utente(p_ni);
    if v_utente_proposto is null then
        if v_sintassi is not null then
            v_utente_proposto := calcola_valore(v_sintassi, p_ni);
        else -- sintassi nulla
            v_utente_proposto := determina_nominativo(p_ni);
        end if;
        v_utente_proposto := substr(determina_utente_libero(v_utente_proposto),1,8);
    end if;
    return upper(v_utente_proposto);
end determina_utente;
function determina_nominativo
/******************************************************************************
 NOME:        determina_nominativo.
 DESCRIZIONE: Determina il nominativo da attribuire all 'utente
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente il nominativo con cui creare l'utente
 ECCEZIONI:   --
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 027  07/05/2012 SNeg Aggiunta determina utente e determina nominativo.
******************************************************************************/
( p_ni in number
) return varchar2
is
   v_nominativo ad4_utenti.nominativo%type;
   v_utente_trovato utenti.utente%TYPE;
begin
      select min(u.utente)
        into v_utente_trovato
        from utenti_soggetti us
              , utenti u
       where Us.SOGGETTO = p_ni
           and u.utente (+) = us.utente;
       if v_utente_trovato is null then
         -- utente non trovato
         v_nominativo :=  determina_nominativo_soggetto(p_ni );
       else
          v_nominativo := UTENTE.GET_NOMINATIVO( v_utente_trovato);
       end if;
  return upper(V_nominativo);
end determina_nominativo;
function leggi_regola_nominativo
 return varchar2
 IS
     v_testo              VARCHAR2 (32767);
   begin
      v_testo := registro_utility.leggi_stringa (v_nominativo_chiave, v_nominativo_stringa, 0);
      return v_testo;
 END;
function leggi_commento_regola_nom
 return varchar2
 IS
 BEGIN
 return registro_pac.get_commento
( p_chiave => v_nominativo_chiave
, p_stringa => v_nominativo_stringa
, p_utente => null);
 END;
procedure imposta_registro_regola_nom
(p_valore registro.stringa%TYPE)
 IS
 BEGIN
  registro_utility.scrivi_stringa
( in_chiave    => v_nominativo_chiave
, in_stringa    => v_nominativo_stringa
, in_valore     => p_valore
, in_commento  => null
, in_eccezione  => 0);
 END;

PROCEDURE SEND_MSG_MODIFICA_UTENTE
/******************************************************************************
NOME:        SEND_MSG_MODIFICA_UTENTE
DESCRIZIONE: Invia un messaggio usando il Si4cimPlSqlJ.
PARAMETRI:   subject  varchar2 Oggetto del messaggio
            text     varchar2 Testo del messaggio
ECCEZIONI:   Scrive gli errori nella key_error_log
ANNOTAZIONI: -
REVISIONI:
Rev. Data       Autore Descrizione
---- ---------- ------ ------------------------------------------------------
0    21/01/2019 SN     Prima emissione.
******************************************************************************/
(subject IN VARCHAR2, text IN VARCHAR2)
IS
   v_sender         VARCHAR2 (2000);
   v_receiver       VARCHAR2 (2000);
   d_notifica_tag   VARCHAR2 (2000);
   d_err            NUMBER;
   d_error          VARCHAR2 (2000);
   ERRORE           EXCEPTION;
   d_err_id         NUMBER;
BEGIN
   d_notifica_tag :=
      NVL (
         registro_utility.leggi_stringa ('PRODUCTS/AD4/UTENTE/NOTIFICA',
                                         'Tag',
                                         1),
         'mail');
   v_receiver :=
      NVL (
         registro_utility.leggi_stringa ('PRODUCTS/AD4/UTENTE/NOTIFICA',
                                         'NotificaMail',
                                         1),
         '');
   v_sender :=
      NVL (
         registro_utility.leggi_stringa ('PRODUCTS/AD4/UTENTE/NOTIFICA',
                                         'Sender',
                                         1),
         '');

   BEGIN
      d_err := 0;
      /*-----------------------------------------------------
         Inizializza il CIM con il tipo di messaggio da
         inviare.
      -----------------------------------------------------*/
      d_err := ad4_Si4cim.initializeMessage (d_notifica_tag);
      /*-----------------------------------------------------
                     Inizializza il Mittente.
      -----------------------------------------------------*/
      ad4_Si4cim.setSender (senderIP      => '',
                            senderName    => '',
                            ID            => '',
                            NAME          => v_sender,
                            company       => '',
                            address       => '',
                            code          => '',
                            city          => '',
                            province      => '',
                            state         => '',
                            email         => v_sender,
                            phoneNumber   => v_sender,
                            faxNumber     => '');
      /*-----------------------------------------------------
                   Inizializza il Destinatario.
      -----------------------------------------------------*/
      ad4_Si4cim.addRecipient (ID            => '',
                               NAME          => v_receiver,
                               company       => '',
                               address       => '',
                               code          => '',
                               city          => '',
                               province      => '',
                               state         => '',
                               email         => v_receiver,
                               phoneNumber   => v_receiver,
                               faxNumber     => '');
      /*-----------------------------------------------------
               Predispone l'oggetto del messaggio.
      -----------------------------------------------------*/
      ad4_Si4cim.setSubject (subject);
      /*-----------------------------------------------------
               Predispone il testo del messaggio.
      -----------------------------------------------------*/
      ad4_Si4cim.setText (text);
      /*-----------------------------------------------------
                      Invia il messaggio.
      -----------------------------------------------------*/
      d_err := ad4_Si4cim.send;
   EXCEPTION
      WHEN OTHERS
      THEN
         d_error := SUBSTR (SQLERRM, 1, 1940);

         SELECT keel_sq.NEXTVAL INTO d_err_id FROM DUAL;

         -- fa commit implicito
         key_error_log_pkg.ins (
            p_error_id        => d_err_id,
            p_error_session   => USERENV ('sessionid'),
            p_error_date      => SYSDATE,
            p_ERROR_TEXT      =>    'Errore in invio mail '
                                 || ' tag :'
                                 || d_notifica_tag
                                 || ' provenienza :'
                                 || v_sender
                                 || ' destinatario :'
                                 || v_receiver
                                 || ' subject: '
                                 || subject
                                 || ' Text: '
                                 || text,
            p_error_user      => USER,
            p_ERROR_TYPE      => 'E');
         NULL;
   END;
END SEND_MSG_MODIFICA_UTENTE;

PROCEDURE RISISTEMA_DIAC_UTENTE (p_utente VARCHAR2, p_modulo varchar2 default '%')
IS
BEGIN
DBMS_APPLICATION_INFO.set_action('DIAC UTENTE:' || p_utente || ':'||p_modulo);
   IF global_utility.get_registro_amministratore -- rev.49
      != 'no'
   THEN
     if utente.get_tipo_utente (p_utente ) = 'U' then -- utente
      -- Cancello anche i diritti dati esplicitamente
        delete diritti_accesso DIAC
         where DIAC.Utente = p_utente
           AND modulo like nvl(p_modulo, '%')
           AND (select utente.get_tipo_utente(utente) from dual) ='U'
           AND (select NVL (utente.get_amministratore (p_utente), 'N') from dual) !=
                       (select NVL (moduli_tpk.get_amministratore (Diac.Modulo),'N') from dual);

      -- cancello da tutti i gruppi x i quali al momento ha diritti di accesso con amministratore non coerente con utente
      -- cancello anche le caratteristiche relative
      -- inserisco in tutti i gruppi nei quali era già
      -- ATTENZIONE: sono da fare 2 gruppi x accesso LDAPPPPP????????????????????????????
      -- posso fare anche solo 1 gruppo ma ci dovranno essere 2 moduli
      -- parlato con Angelo... non ci sono altre possibilità
      FOR v_diac
         IN (SELECT DISTINCT GRUPPO
               FROM DIRITTI_ACCESSO DIAC
              WHERE DIAC.Utente = p_utente AND gruppo IS NOT NULL
                    AND modulo like nvl(p_modulo, '%')
                    AND (select utente.get_tipo_utente(utente) from dual) ='U'
                    AND (select NVL (utente.get_amministratore (p_utente), 'N') from dual) !=
                           (select NVL (moduli_tpk.get_amministratore (Diac.Modulo),'N') from dual))
      LOOP
       GRUPPO.DIAC_CAAC_GRUPPO_DELETE(v_diac.Gruppo, p_Utente, '%','%', 'E','E');
         null;
      END LOOP;
      FOR v_ut_gr in (select *
                        from utenti_gruppo ug
                       where utente = p_utente
                          and exists (select 1 from diritti_accesso where utente = ug.gruppo and modulo like nvl(p_modulo, '%')) ) LOOP
         dbms_output.put_line('DIAC_GRUPPO_INSERT:'||to_char(sysdate,'dd/mm/yyyy hh24:mi:ss')||'#'||v_ut_gr.gruppo||'#'|| v_ut_gr.utente);

          GRUPPO.DIAC_GRUPPO_INSERT(v_ut_gr.gruppo, v_ut_gr.utente,'%','%','T');
          GRUPPO.CAAC_GRUPPO_INSERT(v_ut_gr.gruppo, v_ut_gr.utente,'%','%','T');
      END LOOP;

      else -- probabilmente cambiata importanza di un gruppo
           -- fingo di rimetterlo nel gruppo x far risistemare i diritti di accesso
            FOR v_ut_gr in (select *
                        from utenti_gruppo ug
                       where gruppo = p_utente
                          and exists (select 1 from diritti_accesso where utente = ug.gruppo and modulo like nvl(p_modulo, '%'))) LOOP
         dbms_output.put_line('DIAC_GRUPPO_INSERT:'||to_char(sysdate,'dd/mm/yyyy hh24:mi:ss')||'#'||v_ut_gr.gruppo||'#'|| v_ut_gr.utente);

          GRUPPO.DIAC_GRUPPO_INSERT(v_ut_gr.gruppo, '%','%','%','T');
--          GRUPPO.CAAC_GRUPPO_INSERT(v_ut_gr.gruppo, '%','%','%','T'); ha gia le caratteristiche x sistemazione precedente.
      END LOOP;

      end if;
   END IF;

DBMS_APPLICATION_INFO.set_action(null);
END;

PROCEDURE RISISTEMA_DIAC_MODULO (p_modulo VARCHAR2)
IS
/******************************************************************************
NOME:        RISISTEMA_DIAC_MODULO
DESCRIZIONE: chiamata da moduli_tiu
- x un modulo viene modificato amministratore =si/no  risistema i diritti di accesso di quel utente
PARAMETRI:   subject  varchar2 Oggetto del messaggio
            text     varchar2 Testo del messaggio
ECCEZIONI:
ANNOTAZIONI: -
REVISIONI:
Rev. Data       Autore Descrizione
---- ---------- ------ ------------------------------------------------------
0    28/02/2019 SN     Prima emissione.
******************************************************************************/

BEGIN

DBMS_APPLICATION_INFO.set_action('DIAC MODULO:' ||p_modulo);
   IF global_utility.get_registro_amministratore -- rev.49
      != 'no'
   THEN
      for v_diac in (select *
                            from diritti_accesso
                          where modulo = p_modulo
                             and utente.get_tipo_utente(utente) = 'U' ) loop
         RISISTEMA_DIAC_UTENTE(v_diac.utente, v_diac.modulo);
      end loop;
   END IF;
DBMS_APPLICATION_INFO.set_action(null);
END RISISTEMA_DIAC_MODULO;

PROCEDURE RISISTEMA_DIAC_TUTTI_UTENTI
IS
BEGIN

DBMS_APPLICATION_INFO.set_action('RISISTEMA DIAC TUTTI UTENTI');
   IF global_utility.get_registro_amministratore -- rev.49
      != 'no'
   THEN
   for v_ute in (select *
                         from utenti
                        where tipo_utente = 'U')loop
                        RISISTEMA_DIAC_UTENTE(v_ute.utente);
   end loop;
end if;
DBMS_APPLICATION_INFO.set_action(null);
END RISISTEMA_DIAC_TUTTI_UTENTI;



FUNCTION GET_IS_SISTEMAZIONE_GRUPPI return boolean
is
begin
return v_is_sistemazione_gruppi;
end;

END ;
/

