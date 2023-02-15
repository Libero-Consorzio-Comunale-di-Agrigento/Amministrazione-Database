CREATE OR REPLACE PACKAGE BODY ldap_to_db
IS
   /******************************************************************************
    NOME:        ldap_to_db.
    DESCRIZIONE: Funzioni per la scrittura nel DB delle informazioni presenti in LDAP.
                 Viene richiamata da accesso se l'utente ha fatto accesso con
                 credenziali ldap
                 se ToDbMapping = yes
    ANNOTAZIONI: Guarda la voce di registro:
    Insert into REGISTRO   (CHIAVE, STRINGA, COMMENTO, VALORE)
    Values  ('PRODUCTS/LDAPCONFIG', 'ToDbMapping', 'Indica se in fase di login devono essere acquisite informazioni da LDAP', 'no');
    (preparato x distribuzione il package ma il registro si è deciso di non distribuirlo)
    REVISIONI: .
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    00   28/08/2014 SN     Prima emissione
   ******************************************************************************/
   s_revisione_body        CONSTANT VARCHAR2 (30) := '000';
   SUBTYPE ldap_attribute IS VARCHAR2 (4000);
   ldap_url                         ldap_attribute;
   ldap_user                        ldap_attribute;
   ldap_password                    ldap_attribute;
   ldap_filter                      ldap_attribute;
   ldap_rootou                      ldap_attribute;
   ldap_contestogruppi              ldap_attribute;
   ldap_root                        ldap_attribute;
   ldap_tipo_autenticazione         ldap_attribute;
   ldap_suffissonominativo          ldap_attribute;
   ldap_mapping                     ldap_attribute;
   ldap_case                        ldap_attribute;
   so4_mapping                      ldap_attribute;
   ldap_ignore_attr_errors          ldap_attribute;
   ldap_root_users                  ldap_attribute;
   ldap_root_users_disabled         ldap_attribute;
   ldap_noupdate_campo              ldap_attribute;
   ldap_noupdate_valore             ldap_attribute;
   v_trovato_dn                     ldap_attribute;
   ldap_camponominativo             ldap_attribute;
   d_separatore                     VARCHAR2 (1) := ',';
   ldap_delimitatore                VARCHAR2 (2);
   v_id_allegato                    ALLINEAMENTO_SO4_LDAP.ID_ALLEGATO%TYPE := 0;
   v_nome_allegato                  ALLINEAMENTO_SO4_LDAP.NOME%TYPE;
   d_clob                           CLOB := EMPTY_CLOB ();
   v_dicitura_allineamento_inizio   VARCHAR2 (100):= 'Creazione automatica utente con accesso LDAP';
   d_sender_email                   VARCHAR2 (2000);
   d_registration_email             VARCHAR2 (2000);
   v_intestazione_messaggi          VARCHAR2 (2000);
   v_messaggio                      VARCHAR2 (32767);
   FUNCTION versione
      RETURN VARCHAR2
   IS
   /******************************************************************************
    NOME:        versione.
    DESCRIZIONE: Restituisce versione e revisione di distribuzione del package.
    RITORNA:     VARCHAR2 stringa contenente versione e revisione.
    NOTE:        Primo numero  : versione compatibilita del Package.
                 Secondo numero: revisione del Package specification.
                 Terzo numero  : revisione del Package body.
   ******************************************************************************/
   BEGIN
      RETURN s_revisione || '.' || NVL (s_revisione_body, '000');
   END versione;
   FUNCTION esegui_calcolo (p_valore    VARCHAR2,
                            p_prima     VARCHAR2 DEFAULT NULL,
                            p_dopo      VARCHAR2 DEFAULT NULL)
      RETURN ldap_attribute
   IS
      d_valore   ldap_attribute := LTRIM (LTRIM (p_valore), '=');
   BEGIN
      EXECUTE IMMEDIATE
         'begin ' || d_valore || ';end; ';
      RETURN d_valore;
   END;
   FUNCTION sistema_case (p_valore VARCHAR2)
      RETURN ldap_attribute
   IS
   BEGIN
      RETURN esegui_calcolo (REPLACE (p_valore, '''', ''''''),
                             ldap_case || '(''',
                             ''')');
   END;
   PROCEDURE scrivi_messaggio (p_testo VARCHAR2)
   IS
   BEGIN
      IF v_messaggio IS NULL
      THEN
         v_messaggio := v_dicitura_allineamento_inizio || chr(10);
      END IF;                                          --  non esiste allegato
      v_messaggio := v_messaggio || p_testo || chr(10);
   END;
   PROCEDURE scrivi_segnalazione (p_testo      VARCHAR2,
                                  p_livello    VARCHAR2 DEFAULT 0)
   IS
   BEGIN
      key_error_log_tpk.ins(p_error_id         => NULL,
                            p_error_session    => USERENV ('sessionid'),
                            p_error_date       => SYSDATE,
                            p_error_text       => p_testo,
                            p_error_user       => USER,
                            p_error_usertext   => NULL,
                            p_error_type       => 'S');
      COMMIT;
   END;
   --
   FUNCTION crea_server_alternativo
      RETURN VARCHAR2
   IS
      i          INTEGER := 0;
      d_test     VARCHAR2 (1000);
      d_chiave   VARCHAR2 (1000);
   BEGIN
   NULL;
--      WHILE i <= 9
--      LOOP
--         BEGIN
--            d_chiave := 'PRODUCTS/LDAPCONFIG/SERVER' || i;
--
--            SELECT NVL (
--                      registro_utility.get_stringa (d_chiave,
--                                                    '(Predefinito)'),
--                      ' ')
--              INTO d_test
--              FROM DUAL;
--         EXCEPTION
--            WHEN OTHERS
--            THEN
--               IF SQLCODE = -20922
--               THEN
--                  d_test := '';
--                  EXIT;
--               ELSE
--                  RAISE;
--               END IF;
--         END;
--
--         i := i + 1;
--      END LOOP;
--
--      IF d_test IS NULL
--      THEN
--         BEGIN
--            INSERT INTO registro (chiave,
--                                  stringa,
--                                  valore,
--                                  commento)
--               SELECT d_chiave,
--                      stringa,
--                      DECODE (LOWER (stringa), 'contestogruppi', valore, ''),
--                      commento
--                 FROM registro
--                WHERE     chiave = 'PRODUCTS/LDAPCONFIG'
--                      AND LOWER (stringa) IN ('(predefinito)',
--                                              'server',
--                                              'porta',
--                                              'utenteconnessioneldap',
--                                              'pwdconnessioneldap',
--                                              'contesto',
--                                              'suffissonominativo',
--                                              'contestogruppi',
--                                              'rootou',
--                                              'tipoautenticazione'  e gli altri?????????????
--                                                                  );
--         EXCEPTION
--            WHEN OTHERS
--            THEN
--               RAISE;
--         END;
--
--         RETURN d_chiave;
--      ELSE
--         raise_application_error (
--            -20999,
--            'Il numero massimo di server alternativi e'' 9.');
--      END IF;
   END crea_server_alternativo;
   PROCEDURE get_parametri_ldap (p_server VARCHAR2 DEFAULT NULL)
   ----!!!!!!!!!!!!!!!!!!!! modificare che NON può essere nullo
   /******************************************************************************
    NOME:        get_parametri_ldap.
    DESCRIZIONE: Legge la tabella REGISTRO per le impostazioni.
                Se il server è nullo (può esserlo?) legge le impostazioni generiche.
                Altrimenti in base al server leggere quelle del server indicato
                (si utilizza la notazione del registro, SERVER, SERVER0,SERVER1...etc.
    NOTE:
   ******************************************************************************/
   IS
      d_chiave             VARCHAR2 (30) := 'PRODUCTS/LDAPCONFIG'; -- qui si aggiunge il server se non è quello di "base"
      d_tipo_connessione   VARCHAR2 (10) := 'ldap';
   BEGIN
      if p_server is not null then
         d_chiave := d_chiave|| '/'|| p_server;
      end if;
      ldap_tipo_autenticazione :=
         registro_utility.leggi_stringa (UPPER (d_chiave),
                                         'TIPOAUTENTICAZIONE',
                                         1);
      IF ldap_tipo_autenticazione = 'SSL'
      THEN
         d_tipo_connessione := 'ldaps';
      END IF;
      d_sender_email :=
         REPLACE (
            registro_utility.leggi_stringa ('PRODUCTS/LDAPCONFIG/NOTIFICA',
                                            'Sender',
                                            1),
            ' ',
            '');
      ldap_url :=
            d_tipo_connessione
         || '://'
         || registro_utility.leggi_stringa (UPPER (d_chiave), 'SERVER', 1)
         || ':'
         || registro_utility.leggi_stringa (UPPER (d_chiave), 'PORTA', 1);
      ldap_user :=
         registro_utility.leggi_stringa (UPPER (d_chiave),
                                         'UTENTECONNESSIONELDAP',
                                         1);
      ldap_password :=
         registro_utility.leggi_stringa (UPPER (d_chiave),
                                         'PWDCONNESSIONELDAP',
                                         1);
      ldap_rootou :=
         registro_utility.leggi_stringa (UPPER (d_chiave), 'ROOTOU', 1);
      IF ldap_rootou IS NOT NULL
      THEN
         ldap_rootou := d_separatore || ldap_rootou;
      END IF;
      ldap_suffissonominativo :=
         registro_utility.leggi_stringa (UPPER (d_chiave),
                                         'SUFFISSONOMINATIVO',
                                         1);
      ldap_root_users :=
         registro_utility.leggi_stringa (UPPER (d_chiave), 'ROOTUSERS', 1);
      ldap_camponominativo :=
         registro_utility.leggi_stringa (UPPER ('PRODUCTS/LDAPCONFIG'),
                                         'CampoNominativoInLDAP',
                                         0);
   END;
   function get_attributo (p_campo varchar2)
   return varchar2
   IS
   BEGIN
   return finmatica_ldap.get_attribute_with_filter(ldap_url,
                            ldap_user,
                            ldap_password,
                            LTRIM (ldap_root_users, ','),
                            ldap_filter,p_campo);
   END;
   FUNCTION pulisci (p_stringa VARCHAR2)
      RETURN VARCHAR2
   IS
   BEGIN
      RETURN REPLACE (
                REPLACE (
                   REPLACE (REPLACE (REPLACE (p_stringa, ',', ''), ';', ''),
                            '"',
                            ''),
                   ':',
                   ''),
                '/',
                '');
   END;
--   FUNCTION concatena (p_stringa1 VARCHAR2, p_stringa2 VARCHAR2)
--      RETURN VARCHAR2
--   IS
--   BEGIN
--      IF ldap_delimitatore IS NULL
--      THEN
--         get_parametri_ldap;      -- carica gli attributi per lavorare su ldap
--      END IF;
--
--      RETURN p_stringa1 || ldap_delimitatore || p_stringa2;
--   END;
--
--   FUNCTION get_delimitatore
--      RETURN VARCHAR2
--   IS
--   BEGIN
--      IF ldap_delimitatore IS NULL
--      THEN
--         get_parametri_ldap;      -- carica gli attributi per lavorare su ldap
--      END IF;
--
--      RETURN ldap_delimitatore;
--   END;
   FUNCTION get_RootOu
      RETURN VARCHAR2
   IS
   BEGIN
      IF ldap_rootou IS NULL
      THEN
         get_parametri_ldap;      -- carica gli attributi per lavorare su ldap
      END IF;
      RETURN LTRIM (ldap_rootou, ',');
   END;
   FUNCTION get_RootUsers
      RETURN VARCHAR2
   IS
   BEGIN
      IF ldap_root_Users IS NULL
      THEN
         get_parametri_ldap;      -- carica gli attributi per lavorare su ldap
      END IF;
      RETURN ldap_root_users;
   END;
   FUNCTION get_name (p_cn VARCHAR2)
      RETURN VARCHAR2
   IS
      d_return   VARCHAR2 (32000);
   BEGIN
      IF ldap_url IS NULL
      THEN
         get_parametri_ldap;      -- carica gli attributi per lavorare su ldap
      END IF;
      d_return :=
         finmatica_ldap.get_distinguished_name (ldap_url,
                                                ldap_user,
                                                ldap_password,
                                                LTRIM (ldap_root_users, ','),
                                                p_cn);
      IF d_return IS NULL
      THEN
         d_return := p_cn;
      END IF;
      RETURN d_return;
   END;
   PROCEDURE invio_mail (p_nome             VARCHAR2,
                         p_subject          VARCHAR2,
                         p_text_msg         VARCHAR2,
                         p_id_allegato      NUMBER DEFAULT NULL,
                         p_nome_allegato    VARCHAR2 DEFAULT NULL)
   IS
      d_notifica_tag   VARCHAR2 (100);
      d_destinatario   VARCHAR2 (4000);
      d_err            INTEGER;
      d_subject        VARCHAR2 (2000);
      d_text_msg       VARCHAR2 (2000);
      dbencpwd         VARCHAR2 (2000);
   BEGIN
      d_notifica_tag :=
         NVL (
            registro_utility.leggi_stringa (
               'PRODUCTS/LDAPCONFIG/NOTIFICA/CIM',
               'Tag',
               1),
            'mail');
      IF p_nome IS NULL
      THEN
         raise_application_error (-20999,
                                  'Impossibile inviare mail a ' || p_nome);
      ELSE
         d_err := si4cim.initializemessage (d_notifica_tag);
         /*-----------------------------------------------------
               Inizializza il Mittente.
         -----------------------------------------------------*/
         si4cim.setsender (senderip      => '',
                           sendername    => '',
                           id            => '',
                           name          => 'Messaggio automatico',
                           company       => '',
                           address       => '',
                           code          => '',
                           city          => '',
                           province      => '',
                           state         => '',
                           email         => d_sender_email,
                           phonenumber   => ''-- chi riceve la mail se la vede autoinviata
                           ,
                           faxnumber     => '');
         /*-----------------------------------------------------
                      Inizializza il Destinatario.
         -----------------------------------------------------*/
         si4cim.addrecipient (id            => '',
                              name          => p_nome,
                              company       => '',
                              address       => '',
                              code          => '',
                              city          => '',
                              province      => '',
                              state         => '',
                              email         => p_nome,
                              phonenumber   => '',
                              faxnumber     => '');
         /*-----------------------------------------------------
                  Predispone l'oggetto del messaggio.
         -----------------------------------------------------*/
         si4cim.setsubject (p_subject);
         /*-----------------------------------------------------
                  Predispone il testo del messaggio.
         -----------------------------------------------------*/
         si4cim.settext (p_text_msg);
         /*-----------------------------------------------------
              Predispone l'allegato al messaggio.
          -----------------------------------------------------*/
--         IF p_id_allegato IS NOT NULL
--         THEN
--            SELECT PASSWORD_ORACLE
--              INTO dbencpwd
--              FROM ISTANZE
--             WHERE ISTANZA = 'AD4';
--
--            ad4_si4cim.addAttachment ('oracle',
--                                      'oracle.jdbc.driver.OracleDriver',
--                                      'jdbc:default:connection',
--                                      'AD4',
--                                      dbEncPwd,
--                                      'ALLINEAMENTO_SO4_LDAP',
--                                      'ALLEGATO',
--                                      'ID_ALLEGATO=' || p_id_allegato,
--                                      'CLOB',
--                                      p_nome_allegato);
--         END IF;
         /*-----------------------------------------------------
                         Invia il messaggio.
         -----------------------------------------------------*/
         d_err := si4cim.send;
      END IF;
   END invio_mail;
   PROCEDURE SISTEMA_ATTRIBUTO (p_valore         VARCHAR2,
                               p_utente         VARCHAR2,
                               p_campo          VARCHAR2)
   IS
      /******************************************************************************
       NOME:         SISTEMA_ATTRIBUTO
       DESCRIZIONE: Restituisce valore calcolato in base alla stringa indicata sui
                    registri sotto la voce PRODUCTS/LDAPCONFIG/TO_DB/ATTRIBUTI.
       RITORNA:     VARCHAR2 stringa contenente valore calcolato.
       NOTE:
       REVISIONI: .
       Rev. Data       Autore Descrizione
       ---- ---------- ------ ------------------------------------------------------
       006 13/05/2010  SNeg  Elimino protezione apici se non inizia con =.
      ******************************************************************************/
      d_nome          soggetti.nome%TYPE;
      d_nome_vero     soggetti.nome%TYPE;
      d_cognome       soggetti.nome%TYPE;
      d_email         soggetti.indirizzo_web%TYPE;
      d_valore        ldap_attribute;
      d_nominativo    utenti.nominativo%TYPE;
   BEGIN
      -- sistemazione variabili con attributi utente
      d_valore := p_valore;
      d_nome :=
         REPLACE (
            INITCAP (
               soggetto.get_nome (utente.get_soggetto (p_utente, 'Y'), 'Y')),
            '''',
            '''''');
      d_nome_vero :=
         REPLACE (SUBSTR (d_nome, INSTR (d_nome, '  ') + 2), '''', '''''');
      -- tengo solo nome
      d_cognome :=
         REPLACE (
            INITCAP (
               soggetto.get_cognome (utente.get_soggetto (p_utente, 'Y'),
                                     'Y')),
            '''',
            '''''');
      d_email :=
         REPLACE (
            LOWER (
               soggetto.get_indirizzo_web (
                  utente.get_soggetto (p_utente, 'Y'),
                  'Y')),
            '''',
            '''''');
      d_nominativo := utente.get_nominativo(p_utente);
      -- se inizia con = deve essere valorizzato il valore
      d_valore := REPLACE (d_valore, '%utente%', (p_utente));
      d_valore := REPLACE (d_valore, '%UTENTE%', (p_utente));
      d_valore := REPLACE (d_valore, '%username%', (d_nominativo));
      d_valore := REPLACE (d_valore, '%USERNAME%', (d_nominativo));
      d_valore := REPLACE (d_valore, '%nome%', d_nome_vero);
      d_valore := REPLACE (d_valore, '%NOME%', d_nome_vero);
      d_valore := REPLACE (d_valore, '%cognome%', d_cognome);
      d_valore := REPLACE (d_valore, '%COGNOME%', d_cognome);
      d_valore := REPLACE (d_valore, '%email%', d_email);
      d_valore := REPLACE (d_valore, '%EMAIL%', d_email);
      d_valore := REPLACE (d_valore, '%campo%', get_attributo (p_campo)); -- sostituisco il valore preso da LDAP
      d_valore := REPLACE (d_valore, '%campo%', get_attributo (p_campo));
      -- se inizia con = deve essere valorizzato il valore
      IF SUBSTR (LTRIM (d_valore), 1, 1) = '='
      THEN
         d_valore := esegui_calcolo (d_valore);
      ELSE
         -- non e da calcolare tolgo protezione apici NON DOVREBBE CAPITARE
         d_valore := REPLACE (d_valore, '''''', '''');
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
          scrivi_messaggio (   'val' || p_valore || chr(10));
          scrivi_messaggio ('UTENTE = ' || p_utente || chr(10));
          scrivi_messaggio ('ERRORE:' || sqlerrm);
   END;
   function get_stringa_registro (p_chiave varchar2, p_valore varchar2)
   return varchar2 is
   v_stringa varchar2(100);
   begin
    select max(stringa)
      into v_stringa
      from registro
     where valore = p_valore
       and chiave = p_chiave;
    return v_stringa;
   end;
   PROCEDURE DO_DB_MAPPING ( p_nominativo VARCHAR2
                           , p_server    VARCHAR2)
   IS
      d_err_id           INTEGER := 0;
      d_trc              VARCHAR2 (1000);
      d_dn               VARCHAR2 (4000);
      v_utente           utenti.utente%TYPE := utente.get_utente (p_nominativo);
      v_campo            VARCHAR2 (1000);
      v_nome             VARCHAR2 (1000);
      v_cognome          VARCHAR2 (1000);
      v_codice_fiscale   VARCHAR2 (1000);
      d_valore           VARCHAR2 (1000);
      v_soggetto_creato  boolean := false;
      v_nominativo_senza_dom varchar2(1000);
      id_soggetto        ad4_soggetti.soggetto%TYPE;
      v_chiave_registro  registro.chiave%TYPE;
   BEGIN
   dbms_output.put_line('parametri nominativo e server'||p_nominativo || ':'||p_server);
      v_chiave_registro := 'PRODUCTS/LDAPCONFIG/TO_DB/ATTRIBUTI';
      if p_server is not null and
         REGISTRO_PAC.EXISTS_CHIAVE ('PRODUCTS/LDAPCONFIG/' || p_server,'')  = 1 then
            v_chiave_registro := 'PRODUCTS/LDAPCONFIG/'||p_server ||'/TO_DB/ATTRIBUTI';
      end if;
      if instr(p_nominativo,'@') = 0 then
         v_nominativo_senza_dom := p_nominativo;
      else -- contiene dominio
         v_nominativo_senza_dom := substr(p_nominativo,1,instr(p_nominativo,'@')-1);
      end if;
      ldap_filter := ldap_camponominativo||'='|| v_nominativo_senza_dom;
      -- cerco il dn nella struttura
--      v_trovato_dn :=
--         finmatica_ldap.get_distinguished_name (
--            ldap_url,
--            ldap_user,
--            ldap_password,
--            LTRIM (ldap_root_users, ','),
--            ldap_camponominativo || '=' || v_nominativo_senza_dom);
      IF upper(get_attributo ('uid')) != upper(v_nominativo_senza_dom)
      THEN
         --non è di ldap non faccio nulla.
         NULL;
      ELSE
         -- non riesco a sapere se è stato appena creato l'utente
         -- potrei guardare il numero dei tentativi...
         -- se non ha mai fatto accesso sistemo anche gli attributi
         -- in creazione.
         BEGIN
            v_campo := get_stringa_registro(v_chiave_registro ||'/CREAZIONE','%cognome%');
            v_cognome :=get_attributo (v_campo);
            v_campo :=get_stringa_registro(v_chiave_registro ||'/CREAZIONE','%nome%');
            v_nome :=get_attributo (v_campo);
            v_campo :=get_stringa_registro(v_chiave_registro ||'/CREAZIONE','%codice_fiscale%');
            v_codice_fiscale :=get_attributo (v_campo);
                                               key_error_log_tpk.ins (p_error_id         => NULL,
                                          p_error_session    => USERENV ('sessionid'),
                                          p_error_date       => SYSDATE,
                                          p_error_text       => v_codice_fiscale || ':' || id_soggetto ,
                                          p_error_user       => 'AD4',
                                          p_error_usertext   => ' esecuzione',
                                          p_error_type       => 'S');
                   COMMIT;
            IF utente.get_soggetto (v_utente) IS NULL
            THEN                       -- non ha ancora il soggetto associato.
            dbms_output.put_line('no soggeto associato');
               -- cerco se esiste già un soggetto con quel codice fiscale e lo utilizzo
               IF v_codice_fiscale IS NOT NULL
               THEN                               -- fornito il codice fiscale
                  id_soggetto :=
                     soggetto.scegli_fra_soggetti (
                        v_codice_fiscale);
                dbms_output.put_line('esiste soggetto?'|| id_soggetto);
                  IF id_soggetto IS NOT NULL
                  THEN                                  -- soggetto esiste già
                     -- devo controllare gli altri attributi ???
                     -- devo controllare la validità???
                     ad4_utente.initialize (v_utente);
                     ad4_utente.set_soggetto (id_soggetto);
                     ad4_utente.update_utente (v_utente, 'T');
                  --                           ad4_soggetto.set_nome(v_cognome || '  ' || v_nome);
                  --                         ad4_soggetto.set_codice_fiscale(v_codice_fiscale);
                  --                     ad4_soggetto.set_comune_nas(dati.comune_nas);
                  --                     ad4_soggetto.set_provincia_nas(dati.provincia_nas);
                  --                     ad4_soggetto.set_data_nascita(to_char(dati.data_nas,'DD/MM/YYYY'));
                  --                     ad4_soggetto.set_sesso(dati.sesso);
                  --                     ad4_soggetto.set_comune(dati.comune_res);
                  --                     ad4_soggetto.set_provincia(dati.provincia_res);
                  --                     ad4_soggetto.set_indirizzo(dati.indirizzo_res);
                  --                     ad4_soggetto.set_cap(dati.cap_res);
                  --                     ad4_soggetto.set_telefono(dati.tel_res);
                  --                     if ad4_soggetto.get_indirizzo_web(id_soggetto) is null and dati.e_mail is not null then
                  --                        ad4_soggetto.set_indirizzo_web(dati.e_mail);
                  --                     end if;
                  --                        ad4_soggetto.update_soggetto(id_soggetto);
                  END IF;                               -- soggetto esiste già
               END IF;                            -- fornito il codice fiscale
               IF v_codice_fiscale IS NULL OR -- NON fornito il codice fiscale
                                              --(non posso usarne uno esistente)
                                              id_soggetto IS NULL -- soggetto non esiste
               THEN
               v_soggetto_creato := true;
               key_error_log_tpk.ins (p_error_id         => NULL,
                                          p_error_session    => USERENV ('sessionid'),
                                          p_error_date       => SYSDATE,
                                          p_error_text       => v_codice_fiscale || 'X:' || id_soggetto ,
                                          p_error_user       => 'AD4',
                                          p_error_usertext   => ' esecuzione',
                                          p_error_type       => 'S');
                   COMMIT;
                  /* devo inserire il soggetto */
                  --as4_anagrafe_soggetti_pkg.init_ni(id_soggetto); ?????????????ATTENZIONEEEEEEEEEEEEEEEEEEEEEEE
                  dbms_output.put_line('inserire soggetto');
                  ad4_soggetto.init;
                  ad4_soggetto.set_nome (v_cognome || '  ' || v_nome);
                  ad4_soggetto.set_codice_fiscale (v_codice_fiscale);
                  --                  ad4_soggetto.set_comune_nas(dati.comune_nas);
                  --                  ad4_soggetto.set_provincia_nas(dati.provincia_nas);
                  --                  ad4_soggetto.set_data_nascita(to_char(dati.data_nas,'DD/MM/YYYY'));
                  --                  ad4_soggetto.set_sesso(dati.sesso);
                  --                  ad4_soggetto.set_comune(dati.comune_res);
                  --                  ad4_soggetto.set_provincia(dati.provincia_res);
                  --                  ad4_soggetto.set_indirizzo(dati.indirizzo_res);
                  --                  ad4_soggetto.set_cap(dati.cap_res);
                  --                  ad4_soggetto.set_telefono(dati.tel_res);
                  --                  ad4_soggetto.set_indirizzo_web(dati.e_mail);
                  BEGIN
                     ad4_soggetto.update_soggetto (id_soggetto);
                  key_error_log_tpk.ins (p_error_id         => NULL,
                                          p_error_session    => USERENV ('sessionid'),
                                          p_error_date       => SYSDATE,
                                          p_error_text       => v_codice_fiscale || ':=' || id_soggetto ,
                                          p_error_user       => 'AD4',
                                          p_error_usertext   => ' esecuzione',
                                          p_error_type       => 'S');
                   COMMIT;
                  --                  exception
                  --                     when others then
                  --                        w_annotazioni := ad4_evento.get_annotazioni(pid_evento);
                  --                        if length('Errore su individuo '
                  --                                || dati.cognome
                  --                                || ' '
                  --                                || dati.nome
                  --                                || ' ['
                  --                                || dati.ni
                  --                                || ']: '
                  --                                || substr(sqlerrm, 1, 1800)
                  --                                || chr(10)
                  --                                || w_annotazioni) > 2000 then
                  --                           pid_evento := ad4_evento.insert_evento('Log errori ultima richiesta di abilitazione anagrafica'
                  --                                                                 ,user
                  --                                                                 ,to_char(sysdate,'DD/MM/YYYY HH24:MI:SS')
                  --                                                                 ,0
                  --                                                                 ,'I'
                  --                                                                 ,'APPTRC'
                  --                                                                 ,'Errore su individuo '
                  --                                                                  || dati.cognome
                  --                                                                  || ' '
                  --                                                                  || dati.nome
                  --                                                                  || ' ['
                  --                                                                  || dati.ni
                  --                                                                  || ']: '
                  --                                                                  || substr(sqlerrm,1,1800)
                  --                                                                 ,putente
                  --                                                                 ,'GP4WEB'
                  --                                                                 ,p_istanza);
                  --                        else
                  --                           ad4_evento.update_evento(pid_evento
                  --                                                   ,ad4_evento.get_testo(pid_evento)
                  --                                                   ,user
                  --                                                   ,'Errore su individuo '
                  --                                                    || w_utente
                  --                                                    || ' ['
                  --                                                    || dati.ni
                  --                                                    || ']: '
                  --                                                    || substr(sqlerrm,1,1800)
                  --                                                    || chr(10)
                  --                                                    || w_annotazioni
                  --                                                   ,putente
                  --                                                   ,'GP4WEB'
                  --                                                   ,p_istanza);
                  --                        end if;
                  --                        raise;
                  END;
                  ad4_utente.initialize (v_utente);
                  ad4_utente.set_soggetto (id_soggetto);
                  ad4_utente.update_utente (v_utente, 'T');
               END IF;                                                      --
            END IF;                    -- non ha ancora il soggetto associato.
            -- sistemazione tutti attributi solo se nuovo utente
--            IF utente.GET_ULTIMO_TENTATIVO (v_utente) is null
            IF v_soggetto_creato -- creato ora
            THEN
               FOR v_attr
                  IN (SELECT stringa, valore
                        FROM registro
                       WHERE     chiave =
                                    v_chiave_registro ||'/CREAZIONE'
                             AND LOWER (stringa) != 'defaultpassword'
                             AND LOWER (stringa) != '(predefinito)'
                             and lower(valore) not in ('%nome%','%cognome%','%codice_fiscale%')
                             AND valore IS NOT NULL)
               LOOP
                  NULL;
                                        d_valore := get_attributo (v_attr.stringa);
                                        sistema_attributo(v_attr.valore, v_utente, d_valore);
               --                           -- calcola attributo
               --                          if d_valore is not null then
               --                           set_attributo (v_lstru.dn
               --                                        , v_attr.stringa
               --                                        , d_valore
               --                           );
               --                           end if;
               END LOOP;
            END IF;                                    -- utente appena creato
            -- in ogni caso sistemo gli altri attributi
            FOR v_attr
               IN (SELECT stringa, valore
                     FROM registro
                    WHERE     chiave = v_chiave_registro
                          AND LOWER (stringa) != 'defaultpassword'
                          AND LOWER (stringa) != '(predefinito)'
                          AND valore IS NOT NULL)
            LOOP
               sistema_attributo(v_attr.valore, v_utente,v_attr.stringa);
            END LOOP;
         END;
      END IF; -- non è ldap
      v_intestazione_messaggi := CHR (10);
   END;
   --   EXCEPTION WHEN OTHERS THEN
   --   raise_application_error (-20999,'ERRORE ' || d_trc, TRUE);
   PROCEDURE spedizione_mail (p_messaggio varchar2)
   IS
      d_destination_email   VARCHAR2 (100);
   BEGIN
      IF p_messaggio is not null
      THEN
         d_destination_email :=
            registro_utility.leggi_stringa ('PRODUCTS/LDAPCONFIG/NOTIFICA',
                                            'Address',
                                            1);
         IF d_destination_email IS NULL
         THEN
            NULL;
            raise_application_error (
               -20999,
                  'Impossibile inviare mail di attivita di configurazione automatica utente al LOGIN'
               || '. Indirizzo di spedizione nullo (chiave ''PRODUCTS/LDAPCONFIG/NOTIFICA'' stringa ''Address'').');
         ELSE
            invio_mail (
               p_nome            => d_destination_email,
               p_subject         => 'Errore in configurazione automatica utente al LOGIN',
               p_text_msg        => p_messaggio);
         END IF;
      END IF;
   END;
PROCEDURE allinea_db (p_nominativo    VARCHAR2,
                      p_server        VARCHAR2,
                      p_debug         NUMBER DEFAULT 0)
/******************************************************************************
 NOME:        allinea_db.
 DESCRIZIONE: Ribalta i dati presenti in Ldap sul DB.
             L'attivita utilizza delle tabelle di passaggio nelle quali mette
             i dati delle viste che altrimenti sarebbero troppo lunghe da gestire.
 PARAMETRI:  p_rigenera_so: indica se effettuare l'allineamento dei dati di
             struttura da SO4 a AD4 0=non fare nulla, 1=fare allineamento dati
             p_debug: indica se scrivere informazioni per il debug, se 0=no debug
             altrimenti scrive le info.
 NOTE:       Impostando debug=1 viene fatto  l'output delle attività con dbms_output
******************************************************************************/
IS
   d_cn_utente       ldap_attribute;
   rigenera_so_err   EXCEPTION;
   d_evento          NUMBER;
   d_err_id          INTEGER := 0;
   v_server          VARCHAR2 (100);
   v_valore          registro.valore%TYPE;
BEGIN
   BEGIN
   v_messaggio := null;
   BEGIN
      SELECT lower(valore)
        INTO v_valore
        FROM registro
       WHERE stringa = 'ToDbMapping' AND chiave = p_server;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         IF INSTR (p_server, 'SERVER') > 0
         THEN
            -- cerco sul server generico
            BEGIN
               SELECT lower(valore)
                 INTO v_valore
                 FROM registro
                WHERE     stringa = 'ToDbMapping'
                      AND chiave = 'PRODUCTS/LDAPCONFIG';
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  v_valore := 'no';
            END;
         END IF;
   END;
    IF nvl(v_valore, 'no') = 'yes'
--      IF NVL (
--            LOWER (
--               registro_utility.leggi_stringa (p_server, --'PRODUCTS/LDAPCONFIG', ATTENZIONEEEEEEEEEEEEEEEEEEEE poi controllare anche il padre??? se è genericamente per tutti?
--                                               'ToDbMapping',
--                                               0)),
--            'no') = 'yes'
      THEN
       v_server:=  SUBSTR (p_server, INSTR (p_server, 'PRODUCTS/LDAPCONFIG') + 20);
--                   key_error_log_tpk.ins (p_error_id         => NULL,
--                                          p_error_session    => USERENV ('sessionid'),
--                                          p_error_date       => SYSDATE,
--                                          p_error_text       => p_nominativo || ':' || p_server ||'=>' || v_server,
--                                          p_error_user       => 'STE',
--                                          p_error_usertext   => ' esecuzione',
--                                          p_error_type       => 'S');
--                   COMMIT;
         --      d_clob := EMPTY_CLOB ();
         --      v_id_allegato := 0;
         IF p_debug = 1
         THEN
            integritypackage.setdebugon;
         ELSE
            integritypackage.setdebugoff;
         END IF;
         get_parametri_ldap (v_server); -- carica gli attributi per lavorare su ldap
         integritypackage.LOG (
            'get_parametri_ldap; -- carica gli attributi ');
         do_db_mapping (p_nominativo, v_server); -- v_server indica come si chiama nel REGISTRO
         COMMIT;
      END IF;                        -- non previsto ribaltamento nei registri
   EXCEPTION
      WHEN OTHERS
      THEN
         DECLARE
            d_err   VARCHAR2 (32000);
         BEGIN
            d_err := SUBSTR (SQLERRM, 1, 1940);
            ROLLBACK;
            SELECT keel_sq.NEXTVAL INTO d_err_id FROM DUAL;
            scrivi_messaggio ('Sistemazione ACCESSO nominativo ' || p_nominativo);
            scrivi_messaggio ('Server=' || p_server);
            scrivi_messaggio (sqlerrm);
            key_error_log_tpk.ins (p_error_id         => d_err_id,
                                   p_error_session    => USERENV ('sessionid'),
                                   p_error_date       => SYSDATE,
                                   p_error_text       => v_messaggio || d_err,
                                   p_error_user       => 'AD4'          --USER
                                                              ,
                                   p_error_usertext   => v_messaggio || d_err,
                                   p_error_type       => 'E');
            COMMIT;
         --            scrivi_allegato (v_messaggio || d_err);
         --            BEGIN
         --               spedizione_mail ( v_messaggio || d_err);
         --            -- se errore in spedizione mail era gia stato segnalato
         --            EXCEPTION
         --               WHEN OTHERS
         --               THEN
         --                  scrivi_allegato ('ERRORE INVIO MAIL ');
         --            END;
         --RAISE;         -- per fase di controllo DA TOGLIERRE!!!!!!!!!!!!!!!!!!
         END;
   END;
   IF v_messaggio IS NOT NULL
   THEN
      BEGIN
         spedizione_mail (v_messaggio);
      -- se errore in spedizione mail era gia stato segnalato
      EXCEPTION
         WHEN OTHERS
         THEN
         SELECT keel_sq.NEXTVAL INTO d_err_id FROM DUAL;
            key_error_log_tpk.ins (
               p_error_id         => '',
               p_error_session    => USERENV ('sessionid'),
               p_error_date       => SYSDATE,
               p_error_text       => v_messaggio || ' ERRORE INVIO MAIL',
               p_error_user       => USER,
               p_error_usertext   => v_messaggio || sqlerrm,
               p_error_type       => 'E');
      END;
   END IF;
END;
END;
/

