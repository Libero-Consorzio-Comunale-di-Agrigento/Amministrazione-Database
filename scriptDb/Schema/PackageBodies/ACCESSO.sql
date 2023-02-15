CREATE OR REPLACE PACKAGE BODY accesso
IS
   v_LogAccessRetention_MIN number := 180;
   v_autenticazione_esterna varchar2(2000);
   FUNCTION versione
      RETURN VARCHAR2
   IS                              /* SLAVE_COPY */
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 ECCEZIONI:   --
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/12/2002 MM     Creazione.
 11  29/10/2013 SN     Calcolo della validità password in base a tutti i diritti
                      di accesso dell'utente e non quelli specifici dell'accesso
                      che sta effettuando
******************************************************************************/
   BEGIN
      RETURN 'V1.42';
   END versione;
   PROCEDURE check_accesso
/******************************************************************************
 NOME:        CHECK_ACCESSO.
 DESCRIZIONE: verifica la validita' dell'accesso.
 ARGOMENTI:   p_progetto  IN     VARCHAR2   progetto a cui si accede.
              p_istanza   IN     VARCHAR2   istanza a cui si accede.
              p_modulo    IN     VARCHAR2   modulo a cui si accede.
              p_utente    IN     VARCHAR2   utente che accede.
 ECCEZIONI:   - 20999 'Utente non autorizzato!' se stato 'S' o 'R'
              Lancia le procedure:
              GET_CAAC_EFFETTIVE
              VALIDITA_PWD
              CONTROLLO_ACCESSO
              che potrebbero risultare in una eccezione.
              Warning: -20103 (-20101 e -20102 verificatisi contemporaneamente)
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    16/01/2001 MM     Prima emissione.
 2    17/05/2006 MM     Gestione dello stato dell'utente.
 11  29/10/2013 SN     Calcolo della validità password in base a tutti i diritti
                      di accesso dell'utente e non quelli specifici dell'accesso
                      che sta effettuando
 16  25/05/2015 SN    Evitare accesso x utenti che hanno tipo_utente != 'U'
 27 15/11/2018 SNeg  Verificare coerenza fra modulo e utente Amministratori o no
                                 e voce di registro "Accesso ModuloAMM solo se UtenteAMM"
 29 14/01/2019 SNeg   Scritto errore che impedisce accesso anche nella key_error_log
 30 22/05/2019 SNeg utilizzo funzione global_utility.get_registro_amministratore
******************************************************************************/
   (
      p_progetto   VARCHAR2,
      p_istanza    VARCHAR2,
      p_modulo     VARCHAR2,
      p_utente     VARCHAR2
   )
   IS                                                         /* SLAVE_COPY */
      d_tipo_accesso     VARCHAR2 (1);
      d_accesso          VARCHAR2 (1);
      d_accesso_se       VARCHAR2 (2);
      d_traccia          VARCHAR2 (1);
      d_giorni_traccia   INTEGER;
      d_tentativi_pwd    INTEGER;
      d_val_pwd          INTEGER;
      d_err_code         INTEGER;
      d_err_msg          VARCHAR2 (2000);
      d_stato            VARCHAR2 (1);
      d_err_id           NUMBER;
   BEGIN
      -- 17/05/2006 MM: Gestione dello stato dell'utente.
      BEGIN
         SELECT stato
           INTO d_stato
           FROM utenti
          WHERE utente = p_utente;
      EXCEPTION
         WHEN OTHERS
         THEN
            RAISE;
      END;
      IF d_stato IN ('S', 'R') or nvl(utente.get_tipo_utente(p_utente),'X') != 'U'  -- solo tipo_utenti = 'U'
      THEN

               SELECT keel_sq.NEXTVAL
                 INTO d_err_id
                 FROM DUAL;
               -- fa commit implicito
               key_error_log_pkg.ins
                        (p_error_id => d_err_id,
                         p_error_session =>  USERENV ('sessionid'),
                         p_error_date =>SYSDATE,
                         p_ERROR_TEXT => substr('Autenticazione utente('''
                                         || NVL (p_utente, '%')
                                         || ''') Tipo utente errato : '
                                         || d_err_msg, 1, 2000) ,
                         p_error_user => USER,
                         p_ERROR_TYPE => 'E'
                        );
         raise_application_error (-20999, 'Utente non autorizzato!');
      END IF;
      -- 17/05/2006 MM: fine mod.
      d_tipo_accesso := 'D';
      -- Legge caratteristiche effettive di accesso
      get_caac_effettive (d_tipo_accesso,
                          p_progetto,
                          p_istanza,
                          p_modulo,
                          p_utente,
                          d_accesso,
                          d_accesso_se,
                          d_traccia,
                          d_giorni_traccia,
                          d_tentativi_pwd,
                          d_val_pwd
                         );
      d_err_code := 0;
      d_err_msg := '';
      BEGIN
         -- calcolo validita in base a TUTTI i diritti di accesso, non a quello effettivo
         d_val_pwd := caratteristica_accesso.GET_VAL_PWD('' , '', '', '', p_utente);
         d_tentativi_pwd := caratteristica_accesso.GET_MIN_MAX_TENTATIVI_PWD(p_utente);
         -- Controllo validita' password
         validita_pwd (p_utente, d_tentativi_pwd, d_val_pwd);
      EXCEPTION
         WHEN OTHERS
         THEN
            IF SQLCODE = -20999
            THEN
               RAISE;
            ELSE
               d_err_code := SQLCODE;
               d_err_msg := SUBSTR (SQLERRM, 12);
               SELECT keel_sq.NEXTVAL
                 INTO d_err_id
                 FROM DUAL;
               -- fa commit implicito
               key_error_log_pkg.ins
                        (p_error_id => d_err_id,
                         p_error_session =>  USERENV ('sessionid'),
                         p_error_date =>SYSDATE,
                         p_ERROR_TEXT => substr('Autenticazione utente('''
                                         || NVL (p_utente, '%')
                                         || ''') validita password : '
                                         || d_err_msg, 1, 2000) ,
                         p_error_user => USER,
                         p_ERROR_TYPE => 'E'
                        );

                            ad4_evento.INSERT_EVENTO_COMMIT
                            ( p_testo        =>substr('Autenticazione utente('''
                                         || NVL (p_utente, '%')
                                         || ''') validita password : '
                                         || d_err_msg, 1, 2000)
                             , p_db_user    => user
                             , p_data       => to_char(sysdate, 'dd/mm/yyyy hh24:mi:ss' )
                             , p_notificato => 0 --eventi
                             , p_gravita    => 'E'
                             , p_tipo       => 'ERROR'
                             , p_annotazioni => null
                             , p_utente     => p_utente
                             , p_modulo    => null
                             , p_istanza     => null);
               raise;
            END IF;
      END;
      BEGIN
         -- Controllo effettiva possibilita' di accesso
         controllo_accesso (d_tipo_accesso,
                            p_progetto,
                            p_istanza,
                            p_modulo,
                            p_utente,
                            d_accesso,
                            d_accesso_se
                           );
      EXCEPTION
         WHEN OTHERS
         THEN
            IF SQLCODE = -20999 -- modifica Stefania
              or sqlcode > -20000
            THEN
              SELECT keel_sq.NEXTVAL
                 INTO d_err_id
                 FROM DUAL;
               -- fa commit implicito
               key_error_log_pkg.ins
                        (p_error_id => d_err_id,
                         p_error_session =>  USERENV ('sessionid'),
                         p_error_date =>SYSDATE,
                         p_ERROR_TEXT => substr('Autenticazione utente('''
                                         || p_utente
                                         || ''')'
                                         || sqlcode, 1, 2000) ,
                         p_error_user => USER,
                         p_ERROR_TYPE => 'E'
                        );
               RAISE;
            ELSE
               IF NVL (d_err_msg, ' ') <> ' '
               THEN
                  d_err_code := -20103;
                  d_err_msg :=
                        d_err_msg
                     || CHR (10)
                     || CHR (10)
                     || '&'
                     || SUBSTR (SQLERRM, 12);
               ELSE
                  d_err_code := SQLCODE;
                  d_err_msg := SUBSTR (SQLERRM, 12);
               END IF;
            END IF;
      END;
      -- Rev. 27 inizio
      IF NOT (NVL (d_err_msg, ' ') <> ' ' OR d_err_code < 0 )
      -- non ci sono stati errori
        AND  global_utility.get_registro_amministratore -- rev. 30
            != 'no'
      THEN
      -- controllo se accesso consentito
          if p_modulo is not null -- in caso di check iniziale il modulo è nullo
          and nvl(moduli_tpk.get_amministratore(p_modulo),'N') != nvl(utente.get_amministratore(p_utente),'N') then
             -- NON sono coerenti
               SELECT keel_sq.NEXTVAL
                 INTO d_err_id
                 FROM DUAL;
               -- fa commit implicito
               key_error_log_pkg.ins
                        (p_error_id => d_err_id,
                         p_error_session =>  USERENV ('sessionid'),
                         p_error_date =>SYSDATE,
                         p_ERROR_TEXT => substr('Autenticazione utente('''
                                         || NVL (p_utente, '%')
                                         || ''') amministratore utente (si/no) diverso da amministratore modulo (si/no): '
                                         || '(' || p_modulo ||')'
                                         || d_err_msg, 1, 2000) ,
                         p_error_user => USER,
                         p_ERROR_TYPE => 'E'
                        );
            raise_application_error (-20999, 'Utente non autorizzato !');
          end if;
      END IF;
      -- Rev. 27 ifine
      IF NVL (d_err_msg, ' ') <> ' ' OR d_err_code < 0
      THEN
               SELECT keel_sq.NEXTVAL
                 INTO d_err_id
                 FROM DUAL;
               -- fa commit implicito
               key_error_log_pkg.ins
                        (p_error_id => d_err_id,
                         p_error_session =>  USERENV ('sessionid'),
                         p_error_date =>SYSDATE,
                         p_ERROR_TEXT => substr('Autenticazione utente('''
                                         || NVL (p_utente, '%')
                                         || ''') '
                                         || d_err_msg, 1, 2000) ,
                         p_error_user => USER,
                         p_ERROR_TYPE => 'E'
                        );
         raise_application_error (d_err_code, d_err_msg);
      END IF;
   END check_accesso;
   PROCEDURE get_caac_effettive
/******************************************************************************
 NOME:        GET_CAAC_EFFETTIVE.
 DESCRIZIONE: Recupera le caratteristiche effettive dell'accesso.
 ARGOMENTI:   p_tipo_accesso  IN OUT VARCHAR2   tipo di accesso.
                                                IN:  P / I / M / D.
                                                OUT: tipo di accesso effettivo.
              p_progetto      IN     VARCHAR2   progetto a cui si accede.
              p_istanza       IN     VARCHAR2   istanza a cui si accede.
              p_modulo        IN     VARCHAR2   modulo a cui si accede.
              p_utente        IN     VARCHAR2   utente che accede.
              p_accesso       IN OUT VARCHAR2   accesso ammesso
                                                IN:  valore iniziale.
                                                OUT: accesso ammesso per
                                                     p_tipo_accesso effettivo.
              p_accesso_se    IN OUT VARCHAR2   senza eccezioni o super utente.
                                                IN:  valore iniziale.
                                                OUT: senza eccezioni o super
                                                     utente per p_tipo_accesso
                                                     effettivo.
              p_traccia       IN OUT VARCHAR2   tipo di traccia sugli accessi.
                                                IN:  valore iniziale.
                                                OUT: tipo di traccia sugli
                                                     accessi per p_tipo_accesso
                                                     effettivo.
              p_tentativi_pwd IN OUT NUMBER     numero di tentativi di accesso
                                                ammessi.
                                                IN:  valore iniziale.
                                                OUT: numero di tentativi di
                                                     accesso ammessi per
                                                     p_tipo_accesso effettivo.
              p_val_pwd       IN OUT NUMBER     numero di giorni di validita'
                                                della password.
                                                IN:  valore iniziale.
                                                OUT: numero di giorni di
                                                     validita' della password
                                                     per p_tipo_accesso
                                                     effettivo.
              p_sleep         IN OUT NUMBER     numero di minuti di inattivita'
                                                dopo cui l'applicazione va in
                                                sleep.
                                                IN:  valore iniziale.
                                                OUT: numero di minuti di
                                                     inattivita' per
                                                     p_tipo_accesso effettivo.
                                                     dopo cui l'applicazione va
                                                     in sleep.
             p_single_sign_on IN OUT VARCHAR2   abilitazione al single sign on.
                                                IN:  valore iniziale.
                                                OUT: stringa 'SI' se il single
                                                     sign on e' abilitato per
                                                     p_tipo_accesso effettivo,
                                                     'NO' altrimenti.
             p_ldap           IN OUT VARCHAR2   autenticazione utenti via LDAP.
                                                IN:  valore iniziale.
                                                OUT: stringa 'SI' se l'autenticazione
                                                     utenti avviene via LDAP per
                                                     p_tipo_accesso effettivo,
                                                     'NO' altrimenti.
             p_min_lung_pwd   IN OUT NUMBER     Lunghezza minima della password.
             p_mod_pwd_primo_accesso IN OUT VARCHAR2 Indica se l'utente deve
                                                modificare la password al primo
                                                accesso successivo alla modifica
                                                della stessa da parte di un utente
                                                diverso.
             p_arch_traccia   IN OUT VARCHAR2   attivazione archiviazione traccia.
             p_disl_traccia   IN OUT VARCHAR2   percorso su file system del db
                                                in cui viene salvata la traccia.
             p_car_speciali_pwd IN OUT VARCHAR2 Indica se nella password sono
                                                ammessi caratteri speciali.
             p_num_obb_pwd    IN OUT VARCHAR2   Indica se nella password deve
                                                essere presente almeno un numero.
 ECCEZIONI:   Non possiede eccezioni proprie, ma lancia la procedure
              GET_CARATTERISTICHE_ACCESSO che potrebbe risultare in una eccezione.
 ANNOTAZIONI: chiamata da CHECK_ACCESSO.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    01/02/2001 MM     Prima emissione.
 1    20/01/2003 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
 2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                        DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                        e NUMERI_OBB_PWD.
 3    24/09/2018 SN    Aggiunto campo gg_prima_riutilizzo_pwd
******************************************************************************/
   (
      p_tipo_accesso            IN OUT   VARCHAR2,
      p_progetto                         VARCHAR2,
      p_istanza                          VARCHAR2,
      p_modulo                           VARCHAR2,
      p_utente                           VARCHAR2,
      p_accesso                 IN OUT   VARCHAR2,
      p_accesso_se              IN OUT   VARCHAR2,
      p_traccia                 IN OUT   VARCHAR2,
      p_giorni_traccia          IN OUT   NUMBER,
      p_tentativi_pwd           IN OUT   NUMBER,
      p_val_pwd                 IN OUT   NUMBER,
      p_sleep                   IN OUT   NUMBER,
      p_single_sign_on          IN OUT   VARCHAR2,
      p_ldap                    IN OUT   VARCHAR2,
      p_min_lung_pwd            IN OUT   NUMBER,
      p_mod_pwd_primo_accesso   IN OUT   VARCHAR2,
      p_arch_traccia            IN OUT   VARCHAR2,
      p_disl_traccia            IN OUT   VARCHAR2,
      p_car_speciali_pwd        IN OUT   VARCHAR2,
      p_num_obb_pwd             IN OUT   VARCHAR2,
      p_giorni_prima_riutilizzo_pwd IN OUT   NUMBER
   )
   IS                                                         /* SLAVE_COPY */
   BEGIN
      caratteristica_accesso.get_effettive (p_tipo_accesso,
                                            p_progetto,
                                            p_istanza,
                                            p_modulo,
                                            p_utente,
                                            p_accesso,
                                            p_accesso_se,
                                            p_traccia,
                                            p_giorni_traccia,
                                            p_tentativi_pwd,
                                            p_val_pwd,
                                            p_sleep,
                                            p_single_sign_on,
                                            p_ldap,
                                            p_min_lung_pwd,
                                            p_mod_pwd_primo_accesso,
                                            p_arch_traccia,
                                            p_disl_traccia,
                                            p_car_speciali_pwd,
                                            p_num_obb_pwd,
                                            p_giorni_prima_riutilizzo_pwd
                                           );
   END get_caac_effettive;
   PROCEDURE get_caac_effettive
/******************************************************************************
 NOME:        GET_CAAC_EFFETTIVE.
 DESCRIZIONE: Recupera le caratteristiche effettive dell'accesso.
 ARGOMENTI:   p_tipo_accesso  IN OUT VARCHAR2   tipo di accesso.
                                                IN:  P / I / M / D.
                                                OUT: tipo di accesso effettivo.
              p_progetto      IN     VARCHAR2   progetto a cui si accede.
              p_istanza       IN     VARCHAR2   istanza a cui si accede.
              p_modulo        IN     VARCHAR2   modulo a cui si accede.
              p_utente        IN     VARCHAR2   utente che accede.
              p_accesso       IN OUT VARCHAR2   accesso ammesso
                                                IN:  valore iniziale.
                                                OUT: accesso ammesso per
                                                     p_tipo_accesso effettivo.
              p_accesso_se    IN OUT VARCHAR2   senza eccezioni o super utente.
                                                IN:  valore iniziale.
                                                OUT: senza eccezioni o super
                                                     utente per p_tipo_accesso
                                                     effettivo.
              p_traccia       IN OUT VARCHAR2   tipo di traccia sugli accessi.
                                                IN:  valore iniziale.
                                                OUT: tipo di traccia sugli
                                                     accessi per p_tipo_accesso
                                                     effettivo.
              p_tentativi_pwd IN OUT NUMBER     numero di tentativi di accesso
                                                ammessi.
                                                IN:  valore iniziale.
                                                OUT: numero di tentativi di
                                                     accesso ammessi per
                                                     p_tipo_accesso effettivo.
              p_val_pwd       IN OUT NUMBER     numero di giorni di validita'
                                                della password.
                                                IN:  valore iniziale.
                                                OUT: numero di giorni di
                                                     validita' della password
                                                     per p_tipo_accesso
                                                     effettivo.
              p_sleep         IN OUT NUMBER     numero di minuti di inattivita'
                                                dopo cui l'applicazione va in
                                                sleep.
                                                IN:  valore iniziale.
                                                OUT: numero di minuti di
                                                     inattivita' per
                                                     p_tipo_accesso effettivo.
                                                     dopo cui l'applicazione va
                                                     in sleep.
             p_single_sign_on IN OUT VARCHAR2   abilitazione al single sign on.
                                                IN:  valore iniziale.
                                                OUT: stringa 'SI' se il single
                                                     sign on e' abilitato per
                                                     p_tipo_accesso effettivo,
                                                     'NO' altrimenti.
             p_ldap           IN OUT VARCHAR2   autenticazione utenti via LDAP.
                                                IN:  valore iniziale.
                                                OUT: stringa 'SI' se l'autenticazione
                                                     utenti avviene via LDAP per
                                                     p_tipo_accesso effettivo,
                                                     'NO' altrimenti.
             p_min_lung_pwd   IN OUT NUMBER     Lunghezza minima della password.
             p_mod_pwd_primo_accesso IN OUT VARCHAR2 Indica se l'utente deve
                                                modificare la password al primo
                                                accesso successivo alla modifica
                                                della stessa da parte di un utente
                                                diverso.
             p_arch_traccia   IN OUT VARCHAR2   attivazione archiviazione traccia.
             p_disl_traccia   IN OUT VARCHAR2   percorso su file system del db
                                                in cui viene salvata la traccia.
 ECCEZIONI:   Non possiede eccezioni proprie, ma lancia la procedure
              GET_CARATTERISTICHE_ACCESSO che potrebbe risultare in una eccezione.
 ANNOTAZIONI: chiamata da CHECK_ACCESSO.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    01/02/2001 MM     Prima emissione.
 1    20/01/2003 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
 2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA e
                        DISLOCAZIONE_TRACCIA.
 3    24/09/2018 SN     Aggiunto campo gg_prima_utilizzo_pwd
******************************************************************************/
   (
      p_tipo_accesso            IN OUT   VARCHAR2,
      p_progetto                         VARCHAR2,
      p_istanza                          VARCHAR2,
      p_modulo                           VARCHAR2,
      p_utente                           VARCHAR2,
      p_accesso                 IN OUT   VARCHAR2,
      p_accesso_se              IN OUT   VARCHAR2,
      p_traccia                 IN OUT   VARCHAR2,
      p_giorni_traccia          IN OUT   NUMBER,
      p_tentativi_pwd           IN OUT   NUMBER,
      p_val_pwd                 IN OUT   NUMBER,
      p_sleep                   IN OUT   NUMBER,
      p_single_sign_on          IN OUT   VARCHAR2,
      p_ldap                    IN OUT   VARCHAR2,
      p_min_lung_pwd            IN OUT   NUMBER,
      p_mod_pwd_primo_accesso   IN OUT   VARCHAR2
   )
   IS                                                         /* SLAVE_COPY */
      d_arch_traccia       VARCHAR2 (2);
      d_disl_traccia       VARCHAR2 (100);
      d_car_speciali_pwd   VARCHAR2 (2);
      d_num_obb_pwd        VARCHAR2 (2);
      d_giorni_prima_riutilizzo_pwd NUMBER;
   BEGIN
      get_caac_effettive (p_tipo_accesso,
                          p_progetto,
                          p_istanza,
                          p_modulo,
                          p_utente,
                          p_accesso,
                          p_accesso_se,
                          p_traccia,
                          p_giorni_traccia,
                          p_tentativi_pwd,
                          p_val_pwd,
                          p_sleep,
                          p_single_sign_on,
                          p_ldap,
                          p_min_lung_pwd,
                          p_mod_pwd_primo_accesso,
                          d_arch_traccia,
                          d_disl_traccia,
                          d_car_speciali_pwd,
                          d_num_obb_pwd,
                          d_giorni_prima_riutilizzo_pwd
                         );
   END get_caac_effettive;
   PROCEDURE get_caac_effettive
/******************************************************************************
 NOME:        GET_CAAC_EFFETTIVE.
 DESCRIZIONE: Recupera le caratteristiche effettive dell'accesso ad eccezione
              delle propriet` 'single_sign-on' e 'sleep'.
 ARGOMENTI:   p_tipo_accesso  IN OUT VARCHAR2   tipo di accesso.
                                                IN:  P / I / M / D.
                                                OUT: tipo di accesso effettivo.
              p_progetto      IN     VARCHAR2   progetto a cui si accede.
              p_istanza       IN     VARCHAR2   istanza a cui si accede.
              p_modulo        IN     VARCHAR2   modulo a cui si accede.
              p_utente        IN     VARCHAR2   utente che accede.
              p_accesso       IN OUT VARCHAR2   accesso ammesso
                                                IN:  valore iniziale.
                                                OUT: accesso ammesso per
                                                     p_tipo_accesso effettivo.
              p_accesso_se    IN OUT VARCHAR2   senza eccezioni o super utente.
                                                IN:  valore iniziale.
                                                OUT: senza eccezioni o super
                                                     utente per p_tipo_accesso
                                                     effettivo.
              p_traccia       IN OUT VARCHAR2   tipo di traccia sugli accessi.
                                                IN:  valore iniziale.
                                                OUT: tipo di traccia sugli
                                                     accessi per p_tipo_accesso
                                                     effettivo.
              p_tentativi_pwd IN OUT NUMBER     numero di tentativi di accesso
                                                ammessi.
                                                IN:  valore iniziale.
                                                OUT: numero di tentativi di
                                                     accesso ammessi per
                                                     p_tipo_accesso effettivo.
              p_val_pwd       IN OUT NUMBER     numero di giorni di validita'
                                                della password.
                                                IN:  valore iniziale.
                                                OUT: numero di giorni di
                                                     validita' della password
                                                     per p_tipo_accesso
                                                     effettivo.
              p_sleep         IN OUT NUMBER     numero di minuti di inattivita'
                                                dopo cui l'applicazione va in
                                                sleep.
                                                IN:  valore iniziale.
                                                OUT: numero di minuti di
                                                     inattivita' per
                                                     p_tipo_accesso effettivo.
                                                     dopo cui l'applicazione va
                                                     in sleep.
             p_single_sign_on IN OUT VARCHAR2   abilitazione al single sign on.
                                                IN:  valore iniziale.
                                                OUT: stringa 'SI' se il single
                                                     sign on e' abilitato per
                                                     p_tipo_accesso effettivo,
                                                     'NO' altrimenti.
 ECCEZIONI:   Non possiede eccezioni proprie, ma lancia la procedure
              GET_CARATTERISTICHE_ACCESSO
           che potrebbe risultare in una eccezione.
 ANNOTAZIONI: chiamata da CHECK_ACCESSO.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    01/02/2001 MM     Prima emissione.
 1    20/01/2003 MM      Introduzione del campo LDAP.
******************************************************************************/
   (
      p_tipo_accesso     IN OUT   VARCHAR2,
      p_progetto                  VARCHAR2,
      p_istanza                   VARCHAR2,
      p_modulo                    VARCHAR2,
      p_utente                    VARCHAR2,
      p_accesso          IN OUT   VARCHAR2,
      p_accesso_se       IN OUT   VARCHAR2,
      p_traccia          IN OUT   VARCHAR2,
      p_giorni_traccia   IN OUT   NUMBER,
      p_tentativi_pwd    IN OUT   NUMBER,
      p_val_pwd          IN OUT   NUMBER,
      p_sleep            IN OUT   NUMBER,
      p_single_sign_on   IN OUT   VARCHAR2
   )
   IS                                                         /* SLAVE_COPY */
      d_ldap                VARCHAR2 (2) := 'NO';
      d_min_lung_pwd        NUMBER (2)   := 0;
      d_mod_pwd_primo_uso   VARCHAR2 (2) := 'NO';
   BEGIN
      -- Memorizza valori di DEFAULT per .          ;
      get_caac_effettive (p_tipo_accesso,
                          p_progetto,
                          p_istanza,
                          p_modulo,
                          p_utente,
                          p_accesso,
                          p_accesso_se,
                          p_traccia,
                          p_giorni_traccia,
                          p_tentativi_pwd,
                          p_val_pwd,
                          p_sleep,
                          p_single_sign_on,
                          d_ldap,
                          d_min_lung_pwd,
                          d_mod_pwd_primo_uso
                         );
   END get_caac_effettive;
   PROCEDURE get_caac_effettive
/******************************************************************************
 NOME:        GET_CAAC_EFFETTIVE.
 DESCRIZIONE: Recupera le caratteristiche effettive dell'accesso ad eccezione
              delle propriet` 'single_sign-on' e 'sleep'.
 ARGOMENTI:   p_tipo_accesso  IN OUT VARCHAR2   tipo di accesso.
                                                IN:  P / I / M / D.
                                                OUT: tipo di accesso effettivo.
              p_progetto      IN     VARCHAR2   progetto a cui si accede.
              p_istanza       IN     VARCHAR2   istanza a cui si accede.
              p_modulo        IN     VARCHAR2   modulo a cui si accede.
              p_utente        IN     VARCHAR2   utente che accede.
              p_accesso       IN OUT VARCHAR2   accesso ammesso
                                                IN:  valore iniziale.
                                                OUT: accesso ammesso per
                                                     p_tipo_accesso effettivo.
              p_accesso_se    IN OUT VARCHAR2   senza eccezioni o super utente.
                                                IN:  valore iniziale.
                                                OUT: senza eccezioni o super
                                                     utente per p_tipo_accesso
                                                     effettivo.
              p_traccia       IN OUT VARCHAR2   tipo di traccia sugli accessi.
                                                IN:  valore iniziale.
                                                OUT: tipo di traccia sugli
                                                     accessi per p_tipo_accesso
                                                     effettivo.
              p_tentativi_pwd IN OUT NUMBER     numero di tentativi di accesso
                                                ammessi.
                                                IN:  valore iniziale.
                                                OUT: numero di tentativi di
                                                     accesso ammessi per
                                                     p_tipo_accesso effettivo.
              p_val_pwd       IN OUT NUMBER     numero di giorni di validita'
                                                della password.
                                                IN:  valore iniziale.
                                                OUT: numero di giorni di
                                                     validita' della password
                                                     per p_tipo_accesso
                                                     effettivo.
 ECCEZIONI:   Non possiede eccezioni proprie, ma lancia la procedure omonima
              che, a sua volta, chiama la procedure
           GET_CARATTERISTICHE_ACCESSO
              che potrebbe risultare in un'eccezione.
 ANNOTAZIONI: chiamata da CHECK_ACCESSO.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    01/02/2001 MM     Prima emissione.
******************************************************************************/
   (
      p_tipo_accesso     IN OUT   VARCHAR2,
      p_progetto                  VARCHAR2,
      p_istanza                   VARCHAR2,
      p_modulo                    VARCHAR2,
      p_utente                    VARCHAR2,
      p_accesso          IN OUT   VARCHAR2,
      p_accesso_se       IN OUT   VARCHAR2,
      p_traccia          IN OUT   VARCHAR2,
      p_giorni_traccia   IN OUT   NUMBER,
      p_tentativi_pwd    IN OUT   NUMBER,
      p_val_pwd          IN OUT   NUMBER
   )
   IS                                                         /* SLAVE_COPY */
      d_single_sign_on   VARCHAR2 (2) := 'SI';
      d_sleep            NUMBER (3)   := TO_NUMBER (NULL);
   BEGIN
      get_caac_effettive (p_tipo_accesso,
                          p_progetto,
                          p_istanza,
                          p_modulo,
                          p_utente,
                          p_accesso,
                          p_accesso_se,
                          p_traccia,
                          p_giorni_traccia,
                          p_tentativi_pwd,
                          p_val_pwd,
                          d_sleep,
                          d_single_sign_on
                         );
   END get_caac_effettive;
   FUNCTION is_ldapuser
/******************************************************************************
 NOME:        IS_LDAPUSER
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista l'autenticazione via LDAP.
 PARAMETRI:   p_nominativo varchar2 nominativo dell'utente da autenticare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' prevista
                l'autenticazione via LDAP,
              0 altrimenti.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    21/01/2003 MM     Creazione.
 17 09/06/2015  SN   Modificato metodo x ottenere utente usando get_utente
******************************************************************************/
   (p_nominativo VARCHAR2)
      RETURN NUMBER
   IS                                                         /* SLAVE_COPY */
      d_utente   VARCHAR2 (8);
   BEGIN
      d_utente:= utente.get_utente(p_nominativo);
      RETURN caratteristica_accesso.is_ldapuser (d_utente);
   END is_ldapuser;
   FUNCTION is_pwd_da_mod_primo_uso
/******************************************************************************
 NOME:        IS_PWD_DA_MOD_PRIMO_USO
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista la modifica della password al primo accesso.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' prevista
                la modifica della password al primo accesso.
              0 altrimenti.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    21/01/2003 MM     Creazione.
******************************************************************************/
   (p_utente VARCHAR2)
      RETURN NUMBER
   IS                                                         /* SLAVE_COPY */
   BEGIN
      RETURN caratteristica_accesso.is_pwd_da_mod_primo_uso (p_utente);
   END is_pwd_da_mod_primo_uso;
   FUNCTION get_minpwdlength
/******************************************************************************
 NOME:        GET_MINPWDLENGTH
 DESCRIZIONE: Ottiene il valore piu' grande della minima lunghezza della password
              settata per l'utente (considerando tutti i suoi diritti di accesso).
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     il valore piy grande della minima lunghezza della password.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    21/01/2003 MM     Creazione.
******************************************************************************/
   (p_utente VARCHAR2)
      RETURN NUMBER
   IS                                                         /* SLAVE_COPY */
   BEGIN
      RETURN caratteristica_accesso.get_minpwdlength (p_utente);
   END get_minpwdlength;
   PROCEDURE validita_pwd
/******************************************************************************
 NOME:        VALIDITA_PWD.
 DESCRIZIONE: Controlla che non sia stato superato il numero di tentativi di
              accesso ammessi e che la data della password non sia scaduta.
 ARGOMENTI:   p_utente        IN VARCHAR2 utente che accede.
              p_tentativi_pwd IN INTEGER  numero di tentativi di accesso ammessi.
              p_val_pwd       IN INTEGER  giorni di validita della password.
 ECCEZIONI:   20999, Errore bloccante: 'Utente non definito', 'E' stato raggiunto
                     il numero massimo di tentativi ammessi.'
              20101, Warning: 'La data di validita della password e' scaduta.'
 ANNOTAZIONI: chiamata da REGISTRA_ACCESSO.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    17/01/2001 MM     Prima emissione.
 4    12/12/2007 MM     A21996.0.0: In caso di abilitazione di un utente ad un
                        servizio con caratteristiche piu restrittive, puo'
                        succedere che la password esistente non soddisfi i
                        nuovi requisiti.
 8    22/03/2012 SN  Se utenza ldap non segnala mai errori nel controllo
                     password.
 24   08/03/2018 SN   Se crypt con md5 non posso fare controlli su password
 36   17/06/2020 SN   Metodo generico x criptare non sempre md5 Feature #40748
 38   24/09/2020 SN   Utilizzato utenti_salt_pkg per evitare errore se non esiste record #44753
 40   15/02/2021 SN     Posso controllare la password solo se metodo di crypt = STANDARD #30727
******************************************************************************/
   (
      p_utente          VARCHAR2,
      p_tentativi_pwd   INTEGER,
      p_val_pwd         INTEGER
   )
   IS    /* SLAVE_COPY */
      d_num_tentativi   INTEGER;
      d_giorni_pwd      INTEGER;
      d_rinnovo_pwd     VARCHAR2 (2);
      d_msg             VARCHAR2 (2000);
      d_err_msg         INTEGER;
      d_pwd_da_mod      VARCHAR2 (2);
      d_pwd_coerente    INTEGER         := 1;
      d_crypt_algoritm  VARCHAR2 (100) := utenti_salt_pkg.get_algoritmo_usato(p_utente) ;
   BEGIN
       --  solo se utenza NON ldap posso controllare
      if caratteristica_accesso.is_ldapuser(p_utente) != 1 then
      -- controlla che il numero di tentativi di accesso non superi il max
      BEGIN
         SELECT SYSDATE - data_password, NVL (rinnovo_password, 'SI'),
                numero_tentativi, pwd_da_modificare
           INTO d_giorni_pwd, d_rinnovo_pwd,
                d_num_tentativi, d_pwd_da_mod
           FROM utenti
          WHERE utente = p_utente;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            d_err_msg := -20999;
            d_msg := 'Utente non definito';
      END;
      IF     p_tentativi_pwd IS NOT NULL
         AND NVL (d_err_msg, 0) = 0
         AND NVL (d_msg, ' ') = ' '
      THEN
         IF d_num_tentativi >= p_tentativi_pwd
         THEN
            d_err_msg := -20999;
            d_msg :=
                  'E'' stato raggiunto il numero massimo di tentativi ammessi.'
               || CHR (10)
               || 'La connessione non e'' piu'' attivabile.'
               || CHR (10)
               || CHR (10)
               || 'Avvisare l'' Amministratore del Sistema';
         END IF;
      END IF;
      -- Rev.4    12/12/2007 MM     A21996.0.0:
      --Rev. 24 SN controllo md5
      -- rev. 36

      IF NVL (d_err_msg, 0) = 0
      THEN
         d_pwd_coerente :=
                         caratteristica_accesso.is_password_valida (p_utente);
         IF   d_crypt_algoritm != 'STANDARD' and d_pwd_coerente != 0--rev.40
       -- posso controllare solo se password nulla
       -- dopo i controlli viene mantenuto solo errore con password nulla (=0)
         then
          d_pwd_coerente :=1;
         end if;
         IF d_pwd_coerente <> 1
         THEN
            d_pwd_da_mod := 'SI';
         END IF;
      END IF;
      -- controlla la validita della password e se la password deve essere modificata
      -- dall'utente al primo accesso.
      IF     (   (p_val_pwd IS NOT NULL AND NVL (d_err_msg, 0) = 0)
              OR NVL (d_pwd_da_mod, 'NO') = 'SI'
             )
         AND NVL (d_msg, ' ') = ' '
      THEN
      dbms_output.put_line( d_giorni_pwd||' > '||p_val_pwd||'?');
         IF d_giorni_pwd > p_val_pwd
         THEN
            d_msg :=
                  'La validita'' della password e'' scaduta.'
               || CHR (10)
               || CHR (10);
         ELSIF NVL (d_pwd_da_mod, 'NO') = 'SI'
         THEN
            d_msg :=
                  'La password deve essere modificata prima dei successivi accessi.'
               || CHR (10)
               || CHR (10);
         END IF;
         IF d_rinnovo_pwd = 'SI' AND NVL (d_msg, ' ') <> ' '
         THEN
            d_msg :=
                  d_msg || 'Modificare la password di connessione al Sistema';
            IF p_tentativi_pwd IS NOT NULL
            THEN
               d_num_tentativi :=
                                p_tentativi_pwd - NVL (d_num_tentativi, 0)
                                - 1;
               d_msg :=
                     d_msg
                  || '.'
                  || CHR (10)
                  || CHR (10)
                  || 'Accessi rimasti: '
                  || d_num_tentativi;
            END IF;
            d_err_msg := -20101;
         ELSIF d_rinnovo_pwd = 'NO' AND NVL (d_msg, ' ') <> ' '
         THEN
            d_msg := d_msg || 'Avvisare l'' Amministratore del Sistema';
            d_err_msg := -20999;
         END IF;
      END IF;
      -- Rev.4    12/12/2007 MM     A21996.0.0:
      IF d_pwd_coerente = 0
      THEN
         d_msg :=
               'La password non puo'' essere nulla.'
            || CHR (10)
            || CHR (10)
            || d_msg;
      ELSIF d_pwd_coerente = -1
      THEN
         d_msg := 'Lunghezza password errata.' || CHR (10) || CHR (10)
                  || d_msg;
      ELSIF d_pwd_coerente = -2
      THEN
         d_msg :=
               'La password non puo'' contenere caratteri speciali.'
            || CHR (10)
            || CHR (10)
            || d_msg;
      ELSIF d_pwd_coerente = -3
      THEN
         d_msg :=
               'La password deve contenere almeno un numero.'
            || CHR (10)
            || CHR (10)
            || d_msg;
      END IF;
      IF NVL (d_err_msg, 0) <> 0 OR NVL (d_msg, '') <> ''
      THEN
         raise_application_error (d_err_msg, d_msg);
      END IF;
      END IF; -- utenza ldap
   END validita_pwd;
   PROCEDURE controllo_accesso
/******************************************************************************
 NOME:        CONTROLLO_ACCESSO.
 DESCRIZIONE: selezionate tutte le sessioni dell'utente che risultano attive,
              verifica  che la sessione sia effettivamente ancora in attivita';
              se la sessione su DB non e' piu' attiva, aggiorna il campo LOG della
              tabella ACCESSI con il valore OFF, altrimenti rileva la presenza di
              altre sessioni aperte e, a seconda delle caratteristiche di accesso
              dell'utente, lo segnala (warning) o restituisce un errore bloccante.
 ARGOMENTI:   p_tipo_accesso IN VARCHAR2 tipo di caratteristiche di accesso
              (P / I / M / D)
              p_progetto     IN VARCHAR2 progetto a cui si accede.
              p_istanza      IN VARCHAR2 istanza a cui si accede.
              p_modulo       IN VARCHAR2 modulo a cui si accede.
              p_utente       IN VARCHAR2 utente che accede.
              p_accesso      IN VARCHAR2 accesso ammesso per p_tipo_accesso.
              p_super_user   IN VARCHAR2 indica se l'utente e' super utente.
 ECCEZIONI:   20999, Errore Bloccante: 'L'accesso al sistema e' stato inibito.',
                                       'Rilevati accessi al sistema attivati dallo
                                        stesso utente.'
              20102, Warning: 'Rilevati accessi al sistema attivati dallo
                               stesso utente.'
 ANNOTAZIONI: chiamata da CHECK_ACCESSO.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/01/2001 MM     Prima emissione.
 1    05/12/2003 MM     Sostituzione di V$SESSION con sinonimo SESSIONI (punta
                        a V$SESSION se db in 7 a GV$SESSION se db in8).
******************************************************************************/
   (
      p_tipo_accesso   VARCHAR2,
      p_progetto       VARCHAR2,
      p_istanza        VARCHAR2,
      p_modulo         VARCHAR2,
      p_utente         VARCHAR2,
      p_accesso        VARCHAR2,
      p_super_user     VARCHAR2
   )
   IS                                                         /* SLAVE_COPY */
      d_msg                VARCHAR2 (2000);
      d_error              INTEGER;
      d_actual_terminal    VARCHAR2 (100);
      d_actual_sessionid   NUMBER;
   BEGIN
      -- controlla il campo accesso.
      IF p_accesso = 'I'
      THEN
         IF p_tipo_accesso = 'P'
         THEN
            d_msg := 'al Progetto';
         END IF;
         IF p_tipo_accesso = 'I'
         THEN
            d_msg := 'all'' Istanza';
         END IF;
         IF p_tipo_accesso = 'M'
         THEN
            d_msg := 'al Modulo';
         END IF;
         IF p_tipo_accesso = 'D' AND NVL (p_super_user, 'NO') = 'NO'
         THEN
            d_msg := 'dell'' Utente';
         END IF;
         raise_application_error
              (-20999,
                  'L''accesso al sistema e'' stato inibito.'
               || CHR (10)
               || 'La connessione '
               || d_msg
               || ' non e'' attivabile!'
               || CHR (10)
               || CHR (10)
               || 'Tentare piu'' tardi o avvisare l'' Amministratore del Sistema'
              );
      END IF /*accesso I*/;
      IF p_accesso = 'S' OR p_accesso = 'U'
      THEN
         BEGIN
            SELECT USERENV ('sessionid')
              INTO d_actual_sessionid
              FROM DUAL;
--            SELECT min(UPPER(RTRIM(machine,CHR(0))||'::'||TERMINAL||'::'||OSUSER))
--              INTO d_actual_terminal
--              FROM sessioni
--             WHERE audsid  = d_actual_sessionid
--            ;
            d_actual_terminal :=
                          gv_$session_master_pkg.get_info (d_actual_sessionid);
            d_error := 0;
            -- seleziona tutte le sessioni attive dell'utente.
            FOR sess IN (SELECT acce.session_id
                           FROM accessi acce
                          WHERE acce.istanza = p_istanza
                            AND acce.modulo = p_modulo
                            AND acce.utente = p_utente
                            AND acce.LOG = 'ON')
            LOOP
               DECLARE
                  d_sess_status   VARCHAR2 (100);
               BEGIN
                  -- verifica l'esistenza della sessione ancora in attivita'
                  -- (se status = KILLED significa che il processo e' gia' stato
                  -- eliminato)
                  d_sess_status :=
                     UPPER
                          (gv_$session_master_pkg.get_status (sess.session_id)
                          );
                  IF d_sess_status != 'KILLED'
                  THEN
                     d_msg :=
                            gv_$session_master_pkg.get_info (sess.session_id);
--                  SELECT UPPER(RTRIM(machine,CHR(0))||'::'||TERMINAL||'::'||OSUSER)
--                    INTO d_msg
--                    FROM sessioni
--                   WHERE UPPER(RTRIM(machine,CHR(0))||'::'||TERMINAL||'::'||OSUSER) <> d_actual_terminal
--                     AND audsid  = sess.session_id
--                     AND status  != 'KILLED'
--                  ;
                     IF d_msg IS NOT NULL
                     THEN
                        RAISE TOO_MANY_ROWS;
                     END IF;
                  END IF;
               EXCEPTION
--                  WHEN NO_DATA_FOUND THEN
--                     NULL;
                  WHEN OTHERS
                  THEN
                     d_msg :=
                           'Rilevati accessi al sistema attivati dallo stesso utente.'
                        || CHR (10);
                     IF p_accesso = 'U'
                     THEN
                        d_error := -20999;
                        d_msg :=
                              d_msg
                           || 'La connessione  non e'' attivabile!'
                           || CHR (10)
                           || CHR (10)
                           || 'Tentare piu'' tardi o avvisare l'' Amministratore del Sistema';
                     END IF;
                     IF p_accesso = 'S'
                     THEN
                        d_error := -20102;
                        d_msg := d_msg || 'Terminare la sessione attiva?';
                     END IF;
               END;
            END LOOP;
            IF d_error < 0
            THEN
               raise_application_error (d_error, d_msg);
            END IF;
         END;
      END IF /*accesso S o U*/;
   END controllo_accesso;
   PROCEDURE get_caratteristiche_accesso
/******************************************************************************
 NOME:        GET_CARATTERISTICHE_ACCESSO.
 DESCRIZIONE: restituisce le caratteristiche di accesso al Progetto / Istanza /
              Modulo / Utente.
 ARGOMENTI:   p_tipo_accesso   IN OUT VARCHAR2    tipo di caratteristiche di
                                                  accesso (P / I / M / D).
              p_progetto       IN     VARCHAR2    progetto a cui si accede.
              p_istanza        IN     VARCHAR2    istanza a cui si accede.
              p_modulo         IN     VARCHAR2    modulo a cui si accede.
              p_utente         IN     VARCHAR2    utente che accede.
              p_accesso        IN OUT VARCHAR2    accesso ammesso.
              p_accesso_se     IN OUT VARCHAR2    senza eccezioni o super utente.
              p_traccia        IN OUT VARCHAR2    tipo di traccia sugli accessi.
              p_tentativi_pwd  IN OUT NUMBER      numero di tentativi di accesso
                                                  ammessi.
              p_val_pwd        IN OUT NUMBER      numeri di giorni di validita'
                                                  della password.
              p_sleep          IN OUT NUMBER      numero di minuti di inattivita'
                                                  dopo cui l'applicazione va in
                                                  sleep.
              p_single_sign_on IN OUT VARCHAR2    abilitazione al single sign on.
              p_ldap           IN OUT VARCHAR2    autenticazione utenti via LDAP.
              p_min_lung_pwd   IN OUT NUMBER      Lunghezza minima della password.
              p_mod_pwd_primo_accesso IN OUT VARCHAR2 Indica se l'utente deve
                                                  modificare la password al primo
                                                  accesso successivo alla modifica
                                                  della stessa da parte di un utente
                                                  diverso.
              p_arch_traccia   IN OUT VARCHAR2    attivazione archiviazione traccia.
              p_disl_traccia   IN OUT VARCHAR2    percorso su file system del db
                                                  in cui viene salvata la traccia.
              p_car_speciali_pwd IN OUT VARCHAR2  Indica se nella password sono
                                                  ammessi caratteri speciali.
              p_num_obb_pwd    IN OUT VARCHAR2    Indica se nella password deve
                                                  essere presente almeno un numero.
 ECCEZIONI:   20999, Errore Bloccante: 'Almeno il codice del progetto deve
                                        essere significativo',
                                       'Tipo di accesso non gestito.'
 ANNOTAZIONI: chiamata da GET_CAAC_EFFETTIVE.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    16/01/2001 MM     Prima emissione.
 1    20/01/2003 MM      Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
 1    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                        DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                        e NUMERI_OBB_PWD.
******************************************************************************/
   (
      p_tipo_accesso            IN OUT   VARCHAR2,
      p_progetto                         VARCHAR2,
      p_istanza                          VARCHAR2,
      p_modulo                           VARCHAR2,
      p_utente                           VARCHAR2,
      p_accesso                 IN OUT   VARCHAR2,
      p_accesso_se              IN OUT   VARCHAR2,
      p_traccia                 IN OUT   VARCHAR2,
      p_giorni_traccia          IN OUT   NUMBER,
      p_tentativi_pwd           IN OUT   NUMBER,
      p_val_pwd                 IN OUT   NUMBER,
      p_sleep                   IN OUT   NUMBER,
      p_single_sign_on          IN OUT   VARCHAR2,
      p_ldap                    IN OUT   VARCHAR2,
      p_min_lung_pwd            IN OUT   NUMBER,
      p_mod_pwd_primo_accesso   IN OUT   VARCHAR2,
      p_arch_traccia            IN OUT   VARCHAR2,
      p_disl_traccia            IN OUT   VARCHAR2,
      p_car_speciali_pwd        IN OUT   VARCHAR2,
      p_num_obb_pwd             IN OUT   VARCHAR2,
      p_giorni_prima_riutilizzo_pwd IN OUT   NUMBER
   )
   IS                                                         /* SLAVE_COPY */
   BEGIN
      caratteristica_accesso.get (p_tipo_accesso,
                                  p_progetto,
                                  p_istanza,
                                  p_modulo,
                                  p_utente,
                                  p_accesso,
                                  p_accesso_se,
                                  p_traccia,
                                  p_giorni_traccia,
                                  p_tentativi_pwd,
                                  p_val_pwd,
                                  p_sleep,
                                  p_single_sign_on,
                                  p_ldap,
                                  p_min_lung_pwd,
                                  p_mod_pwd_primo_accesso,
                                  p_arch_traccia,
                                  p_disl_traccia,
                                  p_car_speciali_pwd,
                                  p_num_obb_pwd,
                                  p_giorni_prima_riutilizzo_pwd
                                 );
   END get_caratteristiche_accesso;
   PROCEDURE get_caratteristiche_accesso
/******************************************************************************
 NOME:        GET_CARATTERISTICHE_ACCESSO.
 DESCRIZIONE: restituisce le caratteristiche di accesso al Progetto / Istanza /
              Modulo / Utente.
 ARGOMENTI:   p_tipo_accesso   IN OUT VARCHAR2    tipo di caratteristiche di
                                                  accesso (P / I / M / D).
              p_progetto       IN     VARCHAR2    progetto a cui si accede.
              p_istanza        IN     VARCHAR2    istanza a cui si accede.
              p_modulo         IN     VARCHAR2    modulo a cui si accede.
              p_utente         IN     VARCHAR2    utente che accede.
              p_accesso        IN OUT VARCHAR2    accesso ammesso.
              p_accesso_se     IN OUT VARCHAR2    senza eccezioni o super utente.
              p_traccia        IN OUT VARCHAR2    tipo di traccia sugli accessi.
              p_tentativi_pwd  IN OUT NUMBER      numero di tentativi di accesso
                                                  ammessi.
              p_val_pwd        IN OUT NUMBER      numeri di giorni di validita'
                                                  della password.
              p_sleep          IN OUT NUMBER      numero di minuti di inattivita'
                                                  dopo cui l'applicazione va in
                                                  sleep.
              p_single_sign_on IN OUT VARCHAR2    abilitazione al single sign on.
              p_ldap           IN OUT VARCHAR2    autenticazione utenti via LDAP.
              p_min_lung_pwd   IN OUT NUMBER      Lunghezza minima della password.
              p_mod_pwd_primo_accesso IN OUT VARCHAR2 Indica se l'utente deve
                                                  modificare la password al primo
                                                  accesso successivo alla modifica
                                                  della stessa da parte di un utente
                                                  diverso.
              p_arch_traccia   IN OUT VARCHAR2    attivazione archiviazione traccia.
              p_disl_traccia   IN OUT VARCHAR2    percorso su file system del db
                                                  in cui viene salvata la traccia.
 ECCEZIONI:   20999, Errore Bloccante: 'Almeno il codice del progetto deve
                                        essere significativo',
                                       'Tipo di accesso non gestito.'
 ANNOTAZIONI: chiamata da GET_CAAC_EFFETTIVE.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    16/01/2001 MM     Prima emissione.
 1    20/01/2003 MM      Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
 2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA e
                        DISLOCAZIONE_TRACCIA.
******************************************************************************/
   (
      p_tipo_accesso            IN OUT   VARCHAR2,
      p_progetto                         VARCHAR2,
      p_istanza                          VARCHAR2,
      p_modulo                           VARCHAR2,
      p_utente                           VARCHAR2,
      p_accesso                 IN OUT   VARCHAR2,
      p_accesso_se              IN OUT   VARCHAR2,
      p_traccia                 IN OUT   VARCHAR2,
      p_giorni_traccia          IN OUT   NUMBER,
      p_tentativi_pwd           IN OUT   NUMBER,
      p_val_pwd                 IN OUT   NUMBER,
      p_sleep                   IN OUT   NUMBER,
      p_single_sign_on          IN OUT   VARCHAR2,
      p_ldap                    IN OUT   VARCHAR2,
      p_min_lung_pwd            IN OUT   NUMBER,
      p_mod_pwd_primo_accesso   IN OUT   VARCHAR2
   )
   IS                                                         /* SLAVE_COPY */
      d_arch_traccia       VARCHAR2 (2);
      d_disl_traccia       VARCHAR2 (100);
      d_car_speciali_pwd   VARCHAR2 (2);
      d_num_obb_pwd        VARCHAR2 (2);
      d_giorni_prima_riutilizzo_pwd NUMBER;
   BEGIN
      get_caratteristiche_accesso (p_tipo_accesso,
                                   p_progetto,
                                   p_istanza,
                                   p_modulo,
                                   p_utente,
                                   p_accesso,
                                   p_accesso_se,
                                   p_traccia,
                                   p_giorni_traccia,
                                   p_tentativi_pwd,
                                   p_val_pwd,
                                   p_sleep,
                                   p_single_sign_on,
                                   p_ldap,
                                   p_min_lung_pwd,
                                   p_mod_pwd_primo_accesso,
                                   d_arch_traccia,
                                   d_disl_traccia,
                                   d_car_speciali_pwd,
                                   d_num_obb_pwd,
                                   d_giorni_prima_riutilizzo_pwd
                                  );
   END get_caratteristiche_accesso;
   PROCEDURE get_caratteristiche_accesso
/******************************************************************************
 NOME:        GET_CARATTERISTICHE_ACCESSO.
 DESCRIZIONE: restituisce le caratteristiche di accesso al Progetto / Istanza /
              Modulo / Utente.
 ARGOMENTI:   p_tipo_accesso   IN     VARCHAR2   tipo di caratteristiche di
                                                 accesso (P / I / M / D).
              p_progetto       IN     VARCHAR2   progetto a cui si accede.
              p_istanza        IN     VARCHAR2   istanza a cui si accede.
              p_modulo         IN     VARCHAR2   modulo a cui si accede.
              p_utente         IN     VARCHAR2   utente che accede.
              p_accesso        IN OUT VARCHAR2   accesso ammesso.
              p_accesso_se     IN OUT VARCHAR2   senza eccezioni o super utente.
              p_traccia        IN OUT VARCHAR2   tipo di traccia sugli accessi.
              p_tentativi_pwd  IN OUT NUMBER     numero di tentativi di accesso
                                                 ammessi.
              p_val_pwd        IN OUT NUMBER     numeri di giorni di validita'
                                                 della password.
              p_sleep          IN OUT NUMBER     numero di minuti di inattivita'
                                                 dopo cui l'applicazione va in
                                                 sleep.
              p_single_sign_on IN OUT VARCHAR2  abilitazione al single sign on.
 ECCEZIONI:   20999, Errore Bloccante: 'Almeno il codice del progetto deve
                                        essere significativo',
                                       'Tipo di accesso non gestito.'
 ANNOTAZIONI: chiamata da GET_CAAC_EFFETTIVE.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    16/01/2001 MM     Prima emissione.
 1    20/01/2003 MM      Introduzione del campo LDAP.
******************************************************************************/
   (
      p_tipo_accesso     IN OUT   VARCHAR2,
      p_progetto                  VARCHAR2,
      p_istanza                   VARCHAR2,
      p_modulo                    VARCHAR2,
      p_utente                    VARCHAR2,
      p_accesso          IN OUT   VARCHAR2,
      p_accesso_se       IN OUT   VARCHAR2,
      p_traccia          IN OUT   VARCHAR2,
      p_giorni_traccia   IN OUT   NUMBER,
      p_tentativi_pwd    IN OUT   NUMBER,
      p_val_pwd          IN OUT   NUMBER,
      p_sleep            IN OUT   NUMBER,
      p_single_sign_on   IN OUT   VARCHAR2
   )
   IS                                                         /* SLAVE_COPY */
      d_ldap                VARCHAR2 (2) := 'NO';
      d_min_lung_pwd        NUMBER (2)   := 0;
      d_mod_pwd_primo_uso   VARCHAR2 (2) := 'NO';
   BEGIN
      -- Memorizza valori di DEFAULT.
      d_ldap := 'NO';
      get_caratteristiche_accesso (p_tipo_accesso,
                                   p_progetto,
                                   p_istanza,
                                   p_modulo,
                                   p_utente,
                                   p_accesso,
                                   p_accesso_se,
                                   p_traccia,
                                   p_giorni_traccia,
                                   p_tentativi_pwd,
                                   p_val_pwd,
                                   p_sleep,
                                   p_single_sign_on,
                                   d_ldap,
                                   d_min_lung_pwd,
                                   d_mod_pwd_primo_uso
                                  );
   END get_caratteristiche_accesso;
   PROCEDURE get_caratteristiche_accesso
/******************************************************************************
 NOME:        GET_CARATTERISTICHE_ACCESSO.
 DESCRIZIONE: restituisce le caratteristiche di accesso al Progetto / Istanza /
              Modulo / Utente.
 ARGOMENTI:   p_tipo_accesso   IN     VARCHAR2   tipo di caratteristiche di
                                                 accesso (P / I / M / D).
              p_progetto       IN     VARCHAR2   progetto a cui si accede.
              p_istanza        IN     VARCHAR2   istanza a cui si accede.
              p_modulo         IN     VARCHAR2   modulo a cui si accede.
              p_utente         IN     VARCHAR2   utente che accede.
              p_accesso        IN OUT VARCHAR2   accesso ammesso.
              p_accesso_se     IN OUT VARCHAR2   senza eccezioni o super utente.
              p_traccia        IN OUT VARCHAR2   tipo di traccia sugli accessi.
              p_tentativi_pwd  IN OUT NUMBER     numero di tentativi di accesso
                                                 ammessi.
              p_val_pwd        IN OUT NUMBER     numeri di giorni di validita'
                                                 della password.
 ECCEZIONI:   20999, Errore Bloccante: 'Almeno il codice del progetto deve
                                        essere significativo',
                                       'Tipo di accesso non gestito.'
 ANNOTAZIONI: chiamata da GET_CAAC_EFFETTIVE.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    16/01/2001 MM     Prima emissione.
******************************************************************************/
   (
      p_tipo_accesso     IN OUT   VARCHAR2,
      p_progetto                  VARCHAR2,
      p_istanza                   VARCHAR2,
      p_modulo                    VARCHAR2,
      p_utente                    VARCHAR2,
      p_accesso          IN OUT   VARCHAR2,
      p_accesso_se       IN OUT   VARCHAR2,
      p_traccia          IN OUT   VARCHAR2,
      p_giorni_traccia   IN OUT   NUMBER,
      p_tentativi_pwd    IN OUT   NUMBER,
      p_val_pwd          IN OUT   NUMBER
   )
   IS                                                         /* SLAVE_COPY */
      d_single_sign_on      VARCHAR2 (2) := 'SI';
      d_sleep               NUMBER (3)   := TO_NUMBER (NULL);
      d_ldap                VARCHAR2 (2) := 'NO';
      d_min_lung_pwd        NUMBER (2)   := 0;
      d_mod_pwd_primo_uso   VARCHAR2 (2) := 'NO';
   BEGIN
      get_caratteristiche_accesso (p_tipo_accesso,
                                   p_progetto,
                                   p_istanza,
                                   p_modulo,
                                   p_utente,
                                   p_accesso,
                                   p_accesso_se,
                                   p_traccia,
                                   p_giorni_traccia,
                                   p_tentativi_pwd,
                                   p_val_pwd,
                                   d_single_sign_on,
                                   d_sleep,
                                   d_ldap,
                                   d_min_lung_pwd,
                                   d_mod_pwd_primo_uso
                                  );
   END get_caratteristiche_accesso;
   PROCEDURE refresh_slaves (p_refresh_utenti IN VARCHAR2)
   IS
      d_db_link          VARCHAR2 (200);
      d_link_oracle      VARCHAR2 (2000);
      d_esiste           NUMBER;
      d_list             VARCHAR2 (1000);
      d_refresh_utenti   VARCHAR2 (3)    := NVL (p_refresh_utenti, 'ALL');
      pragma autonomous_transaction;
   BEGIN
      select count(1)
        into d_esiste
        from slaves
      ;
      if d_esiste > 0 then
         BEGIN
            EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM MLOG$_EVENTI'
                         INTO d_esiste;
            IF d_esiste > 0
            THEN
               d_list := d_list || ', ' || 'EVENTI';
            END IF;
            EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM MLOG$_CARATTERISTICHE_ACCE'
                         INTO d_esiste;
            IF d_esiste > 0
            THEN
               d_list := d_list || ', ' || 'CARATTERISTICHE_ACCESSO';
            END IF;
            EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM MLOG$_DIRITTI_ACCESSO'
                         INTO d_esiste;
            IF d_esiste > 0
            THEN
               d_list := d_list || ', ' || 'DIRITTI_ACCESSO_SLAVE';
            END IF;
            EXECUTE IMMEDIATE    'SELECT COUNT(1) FROM MLOG$_UTENTI where '''
                              || d_refresh_utenti
                              || ''' = ''ALL'' or ('''
                              || d_refresh_utenti
                              || ''' = ''INS'' and DMLTYPE$$ = ''I'') '
                         INTO d_esiste;
            IF d_esiste > 0
            THEN
               d_list := d_list || ', ' || 'UTENTI_SLAVE';
            END IF;
            EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM MLOG$_UTENTI_GRUPPO'
                         INTO d_esiste;
            IF d_esiste > 0
            THEN
               d_list := d_list || ', ' || 'UTENTI_GRUPPO_SLAVE';
            END IF;
            EXECUTE IMMEDIATE 'SELECT COUNT(1) FROM MLOG$_UTENTI_SOGGETTI'
                         INTO d_esiste;
            IF d_esiste > 0
            THEN
               d_list := d_list || ', ' || 'UTENTI_SOGGETTI';
            END IF;
            d_list := LTRIM (d_list, ',');
            -- X SISTEMARE Materialized view se installazione master/slave
            OPEN master_utility.c_slaves ('AD4');
            FETCH master_utility.c_slaves
             INTO d_db_link, d_link_oracle;
            WHILE master_utility.c_slaves%FOUND
            LOOP
               BEGIN
                  EXECUTE IMMEDIATE (   'begin '
                                     || '   DBMS_SNAPSHOT.REFRESH@'
                                     || d_db_link
                                     || '(LIST => '''
                                     || d_list
                                     || '''); '
                                     || 'END;'
                                    );
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     NULL;
               END;
               FETCH master_utility.c_slaves
                INTO d_db_link, d_link_oracle;
            END LOOP;
            CLOSE master_utility.c_slaves;
            commit;
         EXCEPTION
            WHEN OTHERS
            THEN
               rollback;
               IF master_utility.c_slaves%ISOPEN
               THEN
                  CLOSE master_utility.c_slaves;
               END IF;
               RAISE;
         END;
      end if;
   end;
   FUNCTION SCRIVI_E_COMMIT_TRACCIA
   /******************************************************************************
 NOME:        SCRIVI_E_COMMIT_TRACCIA.
 DESCRIZIONE: Registrazione di un accesso con commit altrimenti se dava errore
              qualche altro controllo poi non salvava le info di accesso.
 PARAMETRI:   p_session_id     IN NUMBER   identificativo della sessione.
              p_log            IN VARCHAR2 tipo di registrazione
                                           (ON/OFF, TRC, OUT, ABT, ...).
              p_istanza        IN VARCHAR2 istanza a cui si accede.
              p_modulo         IN VARCHAR2 modulo a cui si accede.
              p_utente         IN VARCHAR2 utente che accede.
              p_note           IN VARCHAR2 note sull'accesso.
              p_id_credenziale IN NUMBER   id della credenziale con cui
                                           l'utente si e autenticato.
 ECCEZIONI:   20999, Errore Bloccante: 'Impossibile inserire la registrazione
                                        di LOGON/OFF/OUT/ABT/... dell'utente'
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 21    10/02/2016 SN    Necessario per registrare traccia anche in caso di errore delle altre attività
                        Es: numero tentativi, diritto di accesso
 32    26/09/2019 SNeg  Gestiva solo registrazione nella eventi, aggiunto aggiornamento diritti_accesso Bug #32677
******************************************************************************/
    (
       p_session_id number
     , p_log varchar2
     , p_istanza varchar2
     , p_modulo varchar2
     , p_utente varchar2
     , p_note varchar2
     , p_id_credenziale varchar2
     , p_tipo_autenticazione varchar2
     )
     RETURN NUMBER
   IS
   d_acce_id number;
   PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
   -- Rev. 3    06/06/2007 MM     Gestione EVEN_SQ.
          --d_acce_id := Si4.NEXT_ID('EVENTI','ID_EVENTO');
          SELECT even_sq.NEXTVAL
            INTO d_acce_id
            FROM DUAL;
          -- Rev. 3    06/06/2007 MM     fine mod.
          INSERT INTO accessi
                      (acce_id, session_id, DATA, LOG, utente,
                       istanza, modulo, terminale, note,
                       id_credenziale, tipo_autenticazione)
             SELECT d_acce_id, p_session_id, SYSDATE, p_log, p_utente,
                    p_istanza, p_modulo
                                       --, UPPER(RTRIM(machine,CHR(0))||'::'||terminal||'::'||osuser)
                    ,
                    gv_$session_master_pkg.get_info (p_session_id),
                    p_note, p_id_credenziale,
                    DECODE (p_log, 'ON', p_tipo_autenticazione, '')
               FROM DUAL;
          IF SQL%ROWCOUNT = 0
          THEN
             raise_application_error
                 (-20999,
                     'Impossibile inserire la registrazione di ''LOG'
                  || p_log
                  || ''' dell''utente '
                  || p_utente
                  || '.'
                  || CHR (10)
                  || CHR (10)
                  || 'Avvisare l''Amministratore del Sistema'
                 );
          END IF;
          -- rev.32 inizio
         -- Aggiorna la data di ultimo accesso ed il numero degli accessi al modulo.
         UPDATE diritti_accesso
            SET ultimo_accesso = SYSDATE,
                numero_accessi =NVL (numero_accessi, 0) + 1
          WHERE istanza = p_istanza AND modulo = p_modulo
                AND utente = p_utente;
          -- rev.32 fine
   COMMIT;
   return d_acce_id;
   END;
   FUNCTION registra_accesso
/******************************************************************************
 NOME:        REGISTRA_ACCESSO.
 DESCRIZIONE: Registrazione di un accesso di tipo p_log dell'utente p_utente al
              modulo p_modulo con istanza p_istanza.
 PARAMETRI:   p_session_id     IN NUMBER   identificativo della sessione.
              p_log            IN VARCHAR2 tipo di registrazione
                                           (ON/OFF, TRC, OUT, ABT, ...).
              p_istanza        IN VARCHAR2 istanza a cui si accede.
              p_modulo         IN VARCHAR2 modulo a cui si accede.
              p_utente         IN VARCHAR2 utente che accede.
              p_note           IN VARCHAR2 note sull'accesso.
              p_id_credenziale IN NUMBER   id della credenziale con cui
                                           l'utente si e autenticato.
 RITORNA:     NUMBER numero identificativo dell'accesso inserito.
 ECCEZIONI:   20999, Errore Bloccante: 'Istanza 'p_istanza' non esistente.'
                                       'Parametri NON possono essere NULLI'
                                       'Impossibile inserire la registrazione
                                        di LOGON/OFF/OUT/ABT/... dell'utente'
                                       'Sessione non esistente o non piu'
                                        attiva.'
              Lancia, inoltre, la procedure:
              GET_CAAC_EFFETTIVE
              che potrebbe risultare in una eccezione.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    29/01/2001 MM     Prima emissione.
 1    05/12/2003 MM     - Sostituzione di V$SESSION con sinonimo SESSIONI
                        (punta a V$SESSION se db in 7 a GV$SESSION se db in8).
                        - Registrazione della traccia (p_log = TRC) solo se
                        previsto dalle caratteristiche di accesso (traccia = F).
 2    02/12/2005 MM     Cancellazione degli accessi solo se non prevista
                        archiviazione della traccia.
      13/01/2006 MM     gestione registrazione traccia di una pagina: verifica
                        che abbia estensione .do, .html, .htm, .jsp; se cosi'
                        non e' ignora la richiesta di registrazione dell'accesso.
 3    06/06/2007 MM     Gestione EVEN_SQ.
 4    02/10/2008 SN     Aggiornamento snapshot se installazione master/slave
 5    13/12/2010 SN     In caso di login pulisce le sessioni appese x utente
 7    02/09/2011 SN     Eliminata la autonomous transaction da attivare x master/slave
 9   11/04/2013 SN     Corretta segnalazione di errore in registra_accesso se
                      sessione non trovata
10   23/08/2013 SN      Se autenticazione ldap al login devo anche azzerare
                       il numero dei tentativi e NON fare controlli sulla
                       data di variazione password.
 11  29/10/2013 SN     Calcolo della validità password in base a tutti i diritti
                      di accesso dell'utente e non quelli specifici dell'accesso
                      che sta effettuando
12 06/08/2014  SN    Lettura delle sessioni dalla vista per risolvere problemi di lentezza
13 11/03/2015 SN     Pulizia accessi verificando solo utente.
19 25/08/2015 SN    cancello tutti gli accessi appesi indipendentemente da utente
20 05/02/2016 SN    mettendo log=OFF accodo la nota e non cancello il contenuto
21   10/02/2016 SN    Necessario per registrare traccia anche in caso di errore delle altre attività
                        Es: numero tentativi, diritto di accesso
22  12/05/2016  SN  Il numero minimo di giorni di conservazione dei log di accesso deve essere 180,
                   leggo nei registri se ho impostato LogAccessRetention a valore superiore o
                   se nelle caratteristiche di accesso il valore è superiore, vince il più alto.
23  05/08/2016 SN Se non esiste ancora la registrazione di ON x modulo e istanza passati la inserisce.
24  08/03/2018 SN   Se crypt con md5 non posso fare controlli su password
26  04/07/2018 SNeg Per prestazioni non controllare le sessioni da chiudere al login
                    ma schedulare un job.
31  02/09/2019 SNeg Aggiornamento diritti accesso leggendo il valore nella tabella
33  04/11/2019 SNeg Pulizia della eventi schedulabile di notte con la GESTIONE_DATI_LOCALI predisposto job
34  19/11/2019 SNeg sistemazioni per velocizzare registrazione negli accessi
35  21/11/2019 SNeg Spostata valorizzazione dal registro solo quando viene istanziato il package
36   17/06/2020 SN   Metodo generico x criptare non sempre md5 Feature #40748
37   08/09/2020 SN   Per evitare il lock fra le sessioni si aggiorna
                      la diritti di accesso solo 1 volta al giorno #44035
38   24/09/2020 SN   Utilizzato utenti_salt_pkg per evitare errore se non esiste record #44753
40   15/02/2021 SN     Posso controllare la password solo se metodo di crypt = STANDARD #30727
41   07/05/2021 SN   Se TRC devo registrare l'attività Bug #50185
42   18/05/2021 SN   Chiudere sessione indicata senza verificare che sia ancora aperta Bug #50448
******************************************************************************/
   (
      p_session_id       NUMBER,
      p_log              VARCHAR2,
      p_istanza          VARCHAR2,
      p_modulo           VARCHAR2,
      p_utente           VARCHAR2,
      p_note             VARCHAR2,
      p_id_credenziale   NUMBER DEFAULT NULL
   )
      RETURN NUMBER
   IS
      d_acce_id               INTEGER;
      d_progetto              progetti.progetto%TYPE;
      d_tipo_accesso          VARCHAR2 (1);
      d_accesso               VARCHAR2 (1);
      d_accesso_se            VARCHAR2 (2);
      d_traccia               VARCHAR2 (1);
      d_giorni_traccia        INTEGER;
      d_tentativi_pwd         INTEGER;
      d_val_pwd               INTEGER;
      d_giorni_pwd            INTEGER;
      d_pwd_da_modificare     VARCHAR2 (2);
      d_single_sign_on        VARCHAR2 (2);
      d_sleep                 NUMBER (3);
      d_ldap                  VARCHAR2 (2);
      d_min_lung_pwd          NUMBER (2);
      d_mod_pwd_primo_uso     VARCHAR2 (2);
      d_arch_traccia          VARCHAR2 (2);
      d_disl_traccia          VARCHAR2 (100);
      d_car_speciali_pwd      VARCHAR2 (2);
      d_num_obb_pwd           VARCHAR2 (2);
      d_tipo_autenticazione   VARCHAR2 (40);
      d_note                  VARCHAR2 (2000);
      d_dep                   VARCHAR2 (2000);
      d_num_tentativi         INTEGER;
      d_is_istanza_slave      BOOLEAN                  := FALSE;
      d_oracle_link_match     NUMBER                   := 0;
      d_refresh_utenti        VARCHAR2 (3)             := 'INS';
      d_log                   VARCHAR2 (3)             := p_log;
      new_pwd_da_modificare   utenti.pwd_da_modificare%TYPE;
      d_giorni_prima_riutilizzo_pwd number;
      v_LogAccessRetention number;
      v_esiste_logon number :=0;
      d_crypt_algoritm  VARCHAR2 (100) := utenti_salt_pkg.get_algoritmo_usato(p_utente) ;
  --    PRAGMA AUTONOMOUS_TRANSACTION; --  Attivare x MASTER/SLAVE
   BEGIN
-- |//////////////////////////////////////////////|
-- |                                              |
-- |  Operazioni eseguite SEMPRE                  |
-- |                                              |
-- |//////////////////////////////////////////////|



-- +---------------------------------+
-- | Recupero tipo di autenticazione |
-- | dell'utente (LDAP / AD4)        |
-- +---------------------------------+
      d_tipo_autenticazione := 'AD4';
      IF istanza.existsid (p_istanza)
      THEN
         d_is_istanza_slave :=
            INSTR ('.' || istanza.get_installazione (p_istanza), '.SLAVE') >
                                                                            0;
      END IF;
      IF UPPER (d_log) <> 'WPW'
      THEN
         BEGIN
            SELECT afc.get_stringparm (note, 'Autenticazione'), note
              INTO d_tipo_autenticazione, d_note
              FROM utenti
             WHERE utente = p_utente;
         EXCEPTION
            WHEN OTHERS
            THEN
               NULL;
         END;
         IF d_tipo_autenticazione IS NOT NULL
         THEN
            d_dep := afc.get_substr (d_note, 'Autenticazione=LDAP');
            UPDATE utenti
               SET note = d_note
             WHERE utente = p_utente;
            d_tipo_autenticazione := 'LDAP';
         END IF;
      END IF;
-- |/////////////////////////////////////////////////////////////////|
-- |                                                                 |
-- |  INSERIMENTO della registrazione di ACCESSO passata a meno di:  |
-- |  - LOGOFF                                                       |
-- |  - LOGTRC se non prevista la traccia per funzione (F)           |
-- |                                                                 |
-- |/////////////////////////////////////////////////////////////////|
      IF UPPER (d_log) <> 'OFF'
      THEN
-- +---------------------------------+
-- | Controllo parametri obbligatori |
-- +---------------------------------+
         IF    p_session_id IS NULL
            OR d_log IS NULL
            OR p_istanza IS NULL
            OR p_modulo IS NULL
            OR p_utente IS NULL
         THEN
            raise_application_error
                         (-20999,
                             'I seguenti parametri NON possono essere NULLI:'
                          || CHR (10)
                          || 'p_session_id'
                          || CHR (10)
                          || 'd_log'
                          || CHR (10)
                          || 'p_istanza'
                          || CHR (10)
                          || 'p_modulo'
                          || CHR (10)
                          || 'p_utente'
                         );
         END IF;
-- +-----------------------------------------------+
-- | Lettura caratteristiche effettive di accesso. |
-- | solo se autenticazione in ad4 o ldap          |
-- +-----------------------------------------------+
      IF nvl(v_autenticazione_esterna, 'NO') != 'NO' then-- rev.34
         IF UPPER (d_log) <> 'WPW'
         THEN
            BEGIN
               SELECT progetto
                 INTO d_progetto
                 FROM istanze
                WHERE istanza = p_istanza;
               d_tipo_accesso := 'D';
               get_caac_effettive (d_tipo_accesso,
                                   d_progetto,
                                   p_istanza,
                                   p_modulo,
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
                                   d_mod_pwd_primo_uso,
                                   d_arch_traccia,
                                   d_disl_traccia,
                                   d_car_speciali_pwd,
                                   d_num_obb_pwd,
                                   d_giorni_prima_riutilizzo_pwd
                                  );
               -- validita password ottenuta controllando tutti i diritti di accesso
               d_val_pwd := caratteristica_accesso.GET_VAL_PWD('' , '', '', '', p_utente);
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  raise_application_error (-20999,
                                              'Istanza '''
                                           || p_istanza
                                           || ''' non esistente.'
                                          );
               WHEN OTHERS
               THEN
                  RAISE;
            END;
         END IF;
         end if; -- rev. 34
-- +-----------------------------------------------+
-- | Inserimento della registrazione di accesso.   |
-- +-----------------------------------------------+
         -- Rev. 10 INIZIO
         begin -- Rev 23 INIZIO
           select 1
             into v_esiste_logon
             from dual
             where exists (select 1 from accessi
                            where data    >= trunc(sysdate)
                              and utente  = p_utente
                              and istanza = p_istanza
                              and modulo  = p_modulo
                              and (acce_id = p_id_credenziale
                                   or
                                   nvl(id_credenziale,0) = nvl(p_id_credenziale,0)  -- rev.34
                                  )
                              and log = 'ON');
           exception
           when no_data_found
             then v_esiste_logon := 0;
           when others then
              raise_application_error (-20999,'sessione ' || p_session_id);
         end;
         IF v_esiste_logon = 0 then -- rev. 41
            d_acce_id := scrivi_e_commit_traccia (p_session_id , 'ON',
                            p_istanza, p_modulo, p_utente, p_note, p_id_credenziale,
                             d_tipo_autenticazione);
         END IF;   -- Rev 23 FINE
--         IF -- Rev. 10 FINE
         IF (
         (nvl(d_traccia,'x') <> 'M' AND UPPER (d_log) = 'TRC') -- rev. 41
            OR UPPER (d_log) <> 'TRC')
           -- and nvl(v_autenticazione_esterna,'NO') = 'NO' -- rev. 34
         THEN
            DECLARE
               d_traccia   BOOLEAN := TRUE;
            BEGIN
               -- se registra traccia di una pagina, verifica che abbia estensione
               -- .do, .html, .htm, .jsp; se cosi' non e' ignora la richiesta di
               -- registrazione dell'accesso.
               IF     UPPER (d_log) = 'TRC'
                  AND SUBSTR (LOWER (p_note), 1, 7) = 'http://'
               THEN
                  IF     SUBSTR (LOWER (p_note), -3, 3) <> '.do'
                     AND SUBSTR (LOWER (p_note), -4, 4) NOT IN
                                                             ('.jsp', '.htm')
                     AND SUBSTR (LOWER (p_note), -5, 5) <> '.html'
                  THEN
                     d_traccia := FALSE;
                  END IF;
               END IF;
               IF d_traccia
               THEN
               -- Rev. 21   10/02/2016 SN
                 d_acce_id := scrivi_e_commit_traccia (p_session_id , d_log,
                            p_istanza, p_modulo, p_utente, p_note, p_id_credenziale,
                             d_tipo_autenticazione);
               END IF;
            EXCEPTION
               WHEN OTHERS
               THEN
                  RAISE;
            END;
         END IF;
      END IF;
-- |//////////////////////////////////////////////|
-- |                                              |
-- |  Operazioni eseguite SOLO in caso di LOGON   |
-- |                                              |
-- |//////////////////////////////////////////////|
      IF UPPER (d_log) = 'ON' -- lo fa solo se autenticazione in ad4 o ldap
      THEN
-- +----------------------------------------------------------+
-- | - Seleziona tutte le sessioni in crash dell'utente e,    |
-- |   per ognuna aggiorna la registrazione da LOGON a LOGOFF.|
-- | - Aggiorna la data di ultimo accesso ed il numero degli  |
-- |   accessi al modulo.                                     |
-- | - Cancella gli accessi precedenti alla data massima      |
-- |   prevista.                                              |
-- | - Controlla la validita' della password ed aggiorna il   |
-- |   numero di tentativi di accesso e la data di ultimo     |
-- |   tentativo dell'utente.                                 |
-- | - Controlla se l'utente deve modificarsi la password in  |
-- |   questo accesso.                                        |
-- +----------------------------------------------------------+
-- REV 13 inizio controllo solo per utente con uso indice su LOG
-- REV  26 Inzio
/*
         FOR acce IN (SELECT acce_id
                        FROM (SELECT acce_id, session_id
                                FROM accessi
                               WHERE utente = p_utente
                                 AND LOG = 'ON'
                                  ) a                    --, DUAL
                                  -- REV 13  fine
                                  -- rev 12 inizio
                       WHERE not exists (select 1 from sessioni where audsid = a.session_id)
                                  -- rev 12 fine
                       )
         LOOP
            UPDATE accessi
               SET LOG = 'OFF', -- REV 20 mantengo note esistenti e aggiorno solo con LOG = ON
                   note = substr(note || ' - Sessione pendente chiusa, sessione non piu attiva.',1,2000)
             WHERE acce_id = acce.acce_id
               AND LOG = 'ON';
         END LOOP;
*/
-- REV  26 Fine
         -- Aggiorna la data di ultimo accesso ed il numero degli accessi al modulo.
         UPDATE diritti_accesso
            SET ultimo_accesso = SYSDATE,
                numero_accessi = nVL (numero_accessi, 0) + 1
          WHERE istanza = p_istanza
            AND modulo = p_modulo
            AND utente = p_utente
            AND ultimo_accesso < trunc(sysdate); -- rev.37
         -- Cancella gli accessi precedenti alla data massima prevista
         -- rev 22 - Inizio
         -- rev.33  inizio
--         -- Calcolo giorni previsti di LogAccessRetention dal registro
--         -- validi su tutto applicativo
--         v_LogAccessRetention := greatest(NVL
--                  (LOWER
--                      (registro_utility.leggi_stringa
--                                                     ('PRODUCTS/AUTHENTICATION',
--                                                      'LogAccessRetention',
--                                                      0
--                                                     )
--                      ),
--                   v_LogAccessRetention_MIN
--                  ) ,v_LogAccessRetention_MIN );
--         -- solo se non prevista archiviazione o eliminazione differita.
--         IF    NVL (d_arch_traccia, 'NO') = 'NO'
--            OR NVL
--                  (LOWER
--                      (registro_utility.leggi_stringa
--                                                     ('PRODUCTS/AD4/ACCESSI',
--                                                      'EliminazioneDifferita',
--                                                      0
--                                                     )
--                      ),
--                   'no'
--                  ) = 'no'
--         THEN
--            DELETE      accessi
--                  WHERE DATA < TRUNC (SYSDATE) - greatest(v_LogAccessRetention, NVL (d_giorni_traccia, v_LogAccessRetention_MIN))
--                    AND utente = p_utente
----                    AND modulo = p_modulo
----                    AND istanza = p_istanza
--                    ;
--         END IF;
         -- rev. 33 fine
         -- Rev.22 Fine
         -- Rev.4    12/12/2007 MM     A21996.0.0:
         --SELECT SYSDATE - data_password, pwd_da_modificare
          -- Rev.24 SN non posso controllare se md5
          -- rev. 40 inizio
          -- non posso controllare se metodo non standard
              SELECT SYSDATE - data_password,
                    DECODE
                       (pwd_da_modificare,
                        'SI', 'SI',
                        CASE d_crypt_algoritm
                             WHEN 'STANDARD' then
                            DECODE
                                 (caratteristica_accesso.is_password_valida (p_utente),
                                  1, 'NO',
                                  'SI'
                                 )
                         ELSE -- non standard si può solo verificare se nulla (errore=0)
                             DECODE
                             (caratteristica_accesso.is_password_valida (p_utente),
                              0, 'SI', 'NO' )
                         END
                       )
               INTO d_giorni_pwd,
                    d_pwd_da_modificare
               FROM utenti
              WHERE utente = p_utente;
          -- rev. 40 fine
         -- Rev,10   23/08/2013 SN
         --  solo se utenza NON ldap posso controllare
         IF  caratteristica_accesso.is_ldapuser(p_utente) != 1
            or nvl(v_autenticazione_esterna, 'NO') != 'NO' -- rev.34
         THEN
            IF   (d_giorni_pwd > NVL (d_val_pwd, 0) AND d_val_pwd IS NOT NULL)
            OR d_pwd_da_modificare = 'SI'
             THEN
                SELECT NVL (MAX (numero_tentativi), 0) + 1
                  INTO d_num_tentativi
                  FROM utenti
                 WHERE utente = p_utente;
                d_refresh_utenti := 'ALL';
                d_pwd_da_modificare := 'SI';
             ELSE
                d_num_tentativi := 0;
             END IF;
          ELSE --utenza_ldap
             d_num_tentativi := 0;
             new_pwd_da_modificare := 'NO';
          END IF;
         UPDATE utenti
            SET ultimo_tentativo = SYSDATE,
                numero_tentativi = d_num_tentativi,
                pwd_da_modificare = decode(new_pwd_da_modificare,'NO','NO',d_pwd_da_modificare)
          WHERE utente = p_utente;
      END IF /* Accesso ON*/;
-- |//////////////////////////////////////////////|
-- |                                              |
-- |  Operazioni eseguite in caso di:             |
-- |  - LOGOFF                                    |
-- |  - LOGOUT                                    |
-- |  - LOGABT                                    |
-- |                                              |
-- |//////////////////////////////////////////////|
      IF UPPER (d_log) IN ('OFF', 'ABT', 'OUT')
      THEN
-- +---------------------------------------------------------+                                                 |
-- | Aggiorna le registrazioni con la sessione Oracle data   |
-- | da LOGON a LOGOFF.                                      |
-- +---------------------------------------------------------+
         BEGIN
         -- rev. 39 inizio
         -- rev. 42 inizio
         UPDATE eventi
            SET tipo = 'LOGOFF',
                annotazioni = annotazioni || ' '|| p_note
          WHERE session_id  = p_session_id --AND tipo = 'LOGON'
          ;
          IF SQL%ROWCOUNT = 0 then
            UPDATE accessi
               SET LOG = 'OFF',
                   note = note || ' ' || p_note
             WHERE session_id = p_session_id --AND LOG = 'ON'
             ;
          end if;
         -- rev. 42 fine
         -- rev. 39 fine
         EXCEPTION
            WHEN OTHERS
            THEN
               raise_application_error
                  (-20999,
                      'Impossibile aggiornare la registrazione di ''LOGOFF'' dell''utente '
                   || p_utente
                   || '.'
                   || CHR (10)
                   || CHR (10)
                   || 'Avvisare l''Amministratore del Sistema'
                  );
         END;
         -- Rev. 9 modificato controllo fuori dal blocco altrimenti non usciva mai
         IF SQL%ROWCOUNT = 0 -- non trovata sessione
            THEN
               raise_application_error
                  (-20999,
                      'Sessione non esistente o non piu'' attiva.'
                   || CHR (10)
                   || 'Impossibile aggiornare la registrazione di ''LOGOFF'' dell''utente '
                   || p_utente
                   || '.'
                   || CHR (10)
                   || CHR (10)
                   || 'Avvisare l''Amministratore del Sistema'
                  );
            END IF;
            -- FINE mod. Rev. 9
      END IF /* Accesso OFF , ABT, OUT */;
-- |//////////////////////////////////////////////|
-- |                                              |
-- |  Operazioni eseguite SOLO in caso di LOGWPW  |
-- |  (password errata).                          |
-- |                                              |
-- |//////////////////////////////////////////////|
      IF UPPER (d_log) = 'WPW'
      THEN
-- +---------------------------------------------------------+
-- | Aumenta di uno il numero di tentativi di accesso e      |
-- | aggiorna la data di ultimo tentativo dell'utente ad     |
-- | oggi.                                                   |
-- +---------------------------------------------------------+
         UPDATE utenti
            SET ultimo_tentativo = SYSDATE,
                numero_tentativi = NVL (numero_tentativi, 0) + 1 -- rev. 34
          WHERE utente = p_utente;
         d_refresh_utenti := 'ALL';
      END IF;
--      COMMIT;--  Attivare x MASTER/SLAVE
      /* non esistono master/slave
      SELECT COUNT (1)
        INTO d_oracle_link_match
        FROM istanze ista, istanze ista_ad4
       WHERE ista.istanza = p_istanza
         AND ista_ad4.istanza = 'AD4'
         AND NVL (ista.link_oracle, 'x') = NVL (ista_ad4.link_oracle, 'x');
      IF d_oracle_link_match = 0
      THEN
         refresh_slaves (d_refresh_utenti);
      END IF;
      */
      RETURN d_acce_id;
   EXCEPTION
      WHEN OTHERS
      THEN
--         ROLLBACK;--  Attivare x MASTER/SLAVE
         RAISE;
   END registra_accesso;
   PROCEDURE registra_accesso
/******************************************************************************
 NOME:        REGISTRA_ACCESSO.
 DESCRIZIONE: Registrazione di un accesso di tipo p_log dell'utente p_utente al
              modulo p_modulo con istanza p_istanza.
 ARGOMENTI:   p_session_id     IN NUMBER   identificativo della sessione.
              p_log            IN VARCHAR2 tipo di registrazione
                                           (ON/OFF, TRC, OUT, ABT, ...).
              p_istanza        IN VARCHAR2 istanza a cui si accede.
              p_modulo         IN VARCHAR2 modulo a cui si accede.
              p_utente         IN VARCHAR2 utente che accede.
              p_note           IN VARCHAR2 note sull'accesso.
              p_id_credenziale IN NUMBER   id della credenziale con cui
                                           l'utente si e autenticato.
 ECCEZIONI:   Non possiede eccezioni proprie, ma lancia la funzione omonima
              REGISTRA_ACCESSO
              che potrebbe risultare in una eccezione.
 ANNOTAZIONI: Esegue COMMIT!!!
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    29/01/2001 MM     Prima emissione.
 4    12/12/2007 MM     A21996.0.0: In caso di abilitazione di un utente ad un
                        servizio con caratteristiche piu restrittive, puo'
                        succedere che la password esistente non soddisfi i
                        nuovi requisiti.
 39   26/01/2021 SN     In uscita individuare la sessione da chiudere usando il
                        campo specifico Bug #47610
******************************************************************************/
   (
      p_session_id       NUMBER,
      p_log              VARCHAR2,
      p_istanza          VARCHAR2,
      p_modulo           VARCHAR2,
      p_utente           VARCHAR2,
      p_note             VARCHAR2,
      p_id_credenziale   NUMBER DEFAULT NULL
   )
   IS
      d_return   NUMBER;
   BEGIN
      d_return :=
         registra_accesso (p_session_id,
                           p_log,
                           p_istanza,
                           p_modulo,
                           p_utente,
                           p_note,
                           p_id_credenziale
                          );
   END registra_accesso;
   FUNCTION is_logfile
/******************************************************************************
 NOME:        IS_LOGFILE
 DESCRIZIONE: Verifica il formato del nome del file.
 PARAMETRI:   p_filename  nome del file da controllare.
              Controlla che il nome del file:
              . cominci con 'log'
              . i successivi 4 caratteri siano un numero
              . l'ottavo carattere sia '_'
              . i successivi 2 caratteri siano un numero
              . l'estensione del file sia '.xml'
 RITORNA:     number.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    07/12/2005 MM     Prima emissione.
******************************************************************************/
   (p_nomefile IN VARCHAR2)
      RETURN NUMBER
   IS                                                         /* SLAVE_COPY */
      d_return   NUMBER := 0;
   BEGIN
      IF     INSTR (p_nomefile, 1, 3) = 'log'
         AND afc.is_number (SUBSTR (p_nomefile, 4, 4)) = 1
         AND SUBSTR (p_nomefile, 8, 1) = '_'
         AND afc.is_number (SUBSTR (p_nomefile, 9, 2)) = 1
         AND INSTR (p_nomefile, -4, 4) = '.xml'
      THEN
         d_return := 1;
      END IF;
      RETURN d_return;
   END is_logfile;
   PROCEDURE archivia
/******************************************************************************
 NOME:        ARCHIVIA
 DESCRIZIONE: Achivia tutte le registrazioni di accesso nel file XML di
              archiviazione:
              cerca tutti i progetti, istanze, moduli per cui esista almeno
              un accesso;
              per ognuno di essi:
                 verifica se e' prevista l'archiviazione, dove va salvato il
                 file e ogni quanti giorni va archiviata la traccia;
                 se e' prevista l'archiviazione,
                    crea il file XML di archiviazione.
 ARGOMENTI:   --
 NOTE:        Viene eseguito COMMIT.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    07/12/2005 MM     Prima emissione.
******************************************************************************/
   IS
      d_tipo_accesso        VARCHAR2 (1);
      d_modulo              VARCHAR2 (10);
      d_utente              VARCHAR2 (8);
      d_accesso             VARCHAR2 (1);
      d_accesso_se          VARCHAR2 (2);
      d_traccia             VARCHAR2 (1);
      d_giorni_traccia      NUMBER (3);
      d_tentativi_pwd       NUMBER (2);
      d_val_pwd             NUMBER (3);
      d_sleep               NUMBER (3);
      d_single_sign_on      VARCHAR2 (2);
      d_ldap                VARCHAR2 (2);
      d_min_lung_pwd        NUMBER (2);
      d_mod_pwd_primo_uso   VARCHAR2 (2);
      d_arch_traccia        VARCHAR2 (2);
      d_disl_traccia        VARCHAR2 (100);
      d_car_speciali_pwd    VARCHAR2 (2);
      d_num_obb_pwd         VARCHAR2 (2);
      d_file                VARCHAR2 (2000);
      d_dir_alias           VARCHAR2 (2000);
      d_select              VARCHAR2 (32000);
      d_rows                NUMBER;
      d_conta_arch          NUMBER;
      d_giorni_prima_riutilizzo_pwd number;
   BEGIN
      d_conta_arch := 0;
      -- cerca tutti i progetti, istanze, moduli per cui esista almeno un accesso;
      -- per ognuno di essi:
      --    verifica se e prevista l'archiviazione, dove va salvato il file e ogni
      --    quanti giorni va archiviata la traccia;
      --    se e prevista l'archiviazione:
      --       prepara la select per creare il file XML di archiviazione
      FOR acce_cur IN (SELECT DISTINCT ista.progetto, diac.istanza
                                  FROM accessi diac, istanze ista
                                 WHERE ista.istanza = diac.istanza)
      LOOP
         d_tipo_accesso := 'I';
         d_modulo := TO_CHAR (NULL);
         d_utente := TO_CHAR (NULL);
         d_accesso := 'L';
         d_accesso_se := 'NO';
         d_traccia := 'M';
         d_giorni_traccia := 60;
         d_tentativi_pwd := TO_NUMBER (NULL);
         d_val_pwd := TO_NUMBER (NULL);
         d_sleep := TO_NUMBER (NULL);
         d_single_sign_on := 'SI';
         d_ldap := 'NO';
         d_min_lung_pwd := TO_NUMBER (NULL);
         d_mod_pwd_primo_uso := 'NO';
         d_arch_traccia := 'NO';
         d_disl_traccia := TO_CHAR (NULL);
         d_car_speciali_pwd := 'NO';
         d_num_obb_pwd := 'NO';
         get_caac_effettive (d_tipo_accesso,
                             acce_cur.progetto,
                             acce_cur.istanza,
                             d_modulo,
                             d_utente,
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
                             d_mod_pwd_primo_uso,
                             d_arch_traccia,
                             d_disl_traccia,
                             d_car_speciali_pwd,
                             d_num_obb_pwd,
                             d_giorni_prima_riutilizzo_pwd
                            );
         IF NVL (d_arch_traccia, 'NO') = 'SI' AND d_disl_traccia IS NOT NULL
         THEN
            BEGIN
--               DBMS_OUTPUT.PUT_LINE(d_tipo_accesso||' '||acce_cur.progetto||' '||acce_cur.Istanza||' '||d_giorni_traccia);
               d_giorni_traccia := NVL (d_giorni_traccia, 60);
               FOR c IN (SELECT DISTINCT    'log'
                                         || TO_CHAR (DATA, 'yyyy')
                                         || '_'
                                         || TO_CHAR (DATA, 'mm')
                                         || '.xml' nomefile
                                    FROM accessi
                                   WHERE DATA <=
                                              TO_DATE
                                                 (   TO_CHAR (TRUNC (SYSDATE),
                                                              'dd/mm/yyyy'
                                                             )
                                                  || '23:59:59',
                                                  'dd/mm/yyyy hh24:mi:ss'
                                                 )
                                            - d_giorni_traccia
                                     AND (   (    d_tipo_accesso = 'I'
                                              AND istanza = acce_cur.istanza
                                             )
                                          OR (    d_tipo_accesso = 'P'
                                              AND istanza IN (
                                                     SELECT istanza
                                                       FROM istanze
                                                      WHERE progetto =
                                                               acce_cur.progetto)
                                             )
                                         ))
               LOOP
--                 DBMS_OUTPUT.PUT_LINE('   '||c.nomefile);
                  UPDATE accessi
                     SET stato = 'D'
                   WHERE DATA <=
                              TO_DATE (   TO_CHAR (TRUNC (SYSDATE),
                                                   'dd/mm/yyyy'
                                                  )
                                       || '23:59:59',
                                       'dd/mm/yyyy hh24:mi:ss'
                                      )
                            - d_giorni_traccia
                     AND (   (    d_tipo_accesso = 'I'
                              AND istanza = acce_cur.istanza
                             )
                          OR (    d_tipo_accesso = 'P'
                              AND istanza IN (
                                            SELECT istanza
                                              FROM istanze
                                             WHERE progetto =
                                                             acce_cur.progetto)
                             )
                         )
                     AND    'log'
                         || TO_CHAR (DATA, 'yyyy')
                         || '_'
                         || TO_CHAR (DATA, 'mm')
                         || '.xml' = c.nomefile
                     AND stato = 'U';
                  d_rows := SQL%ROWCOUNT;
--                  DBMS_OUTPUT.PUT_LINE('   '||d_rows);
                  IF d_rows > 0
                  THEN
                     --d_dir_alias := nvl(AFC_BFILE.get_dirName(d_disl_traccia),d_disl_traccia);
                     d_file := d_disl_traccia || '/' || c.nomefile;
                     d_conta_arch := d_conta_arch + d_rows;
                     d_select := 'select * from accessi where stato = ''D''';
                     DECLARE
                        d_rowtag      VARCHAR2 (200);
                        d_bfile       BFILE;
                        d_directory   VARCHAR2 (30);
                     BEGIN
                        d_directory :=
                               afc_bfile.get_dirname (d_disl_traccia, 'AD4_');
                        d_rowtag := 'ACCESSO_ROW';
--DBMS_OUTPUT.PUT_LINE('Si4_Xml.append_select('''||d_directory||''', '''||c.nomefile||''', '''||d_select||''', '''||d_rowtag||''');');
                        d_bfile :=
                           si4_xml.append_select (d_directory,
                                                  c.nomefile,
                                                  d_select,
                                                  d_rowtag
                                                 );
                        DELETE      accessi
                              WHERE stato = 'D';
                        DECLARE
                           d_id   NUMBER;
                        BEGIN
                           d_id :=
                              ad4_evento.insert_evento
                                           (   'In file '
                                            || d_file
                                            || ' effettuata il '
                                            || TO_CHAR
                                                      (SYSDATE,
                                                       'dd/mm/yyyy hh24:mi:ss'
                                                      ),
                                            USER,
                                            TO_CHAR (SYSDATE,
                                                     'dd/mm/yyyy hh24:mi:ss'
                                                    ),
                                            0,
                                            'I',
                                            'ARCLOG',
                                            '',
                                            NVL (si4.utente, 'AD4'),
                                            NVL (si4.modulo, 'AD4'),
                                            NVL (si4.istanza, 'AD4')
                                           );
                           ad4_evento.set_file_locator (d_id, d_bfile);
                        END;
--                  DBMS_OUTPUT.PUT_LINE('   prima commit');
                        COMMIT;
                     EXCEPTION
                        WHEN OTHERS
                        THEN
                           DECLARE
                              d_id      NUMBER;
                              d_testo   VARCHAR2 (2000);
                           BEGIN
--                     DBMS_OUTPUT.PUT_LINE('   in exception');
                              ROLLBACK;
                              d_testo :=
                                 'Fallita per progetto ' || acce_cur.progetto;
                              IF acce_cur.istanza IS NOT NULL
                              THEN
                                 d_testo :=
                                         d_testo || ' - ' || acce_cur.istanza;
                              END IF;
                              d_testo := d_testo || ': ' || SQLERRM;
                              d_id :=
                                 ad4_evento.insert_evento
                                            (d_testo,
                                             USER,
                                             TO_CHAR (SYSDATE,
                                                      'dd/mm/yyyy hh24:mi:ss'
                                                     ),
                                             0,
                                             'S',
                                             'ARCLOG',
                                             '',
                                             NVL (si4.utente, 'AD4'),
                                             NVL (si4.modulo, 'AD4'),
                                             NVL (si4.istanza, 'AD4')
                                            );
                              COMMIT;
                              EXIT;
                           END;
                     END;
                  END IF;
               END LOOP;
            EXCEPTION
               WHEN OTHERS
               THEN
                  NULL;
-- se l'archiviazione di uno dei progetti / istanze fallisce continua con i successivi
            END;
         END IF;
      END LOOP;
      IF d_conta_arch = 0
      THEN
         DECLARE
            d_id   NUMBER;
         BEGIN
            d_id :=
               ad4_evento.insert_evento
                                 (p_testo            => 'Nessun accesso da archiviare.',
                                  p_db_user          => USER,
                                  p_data             => TO_CHAR
                                                           (SYSDATE,
                                                            'dd/mm/yyyy hh24:mi:ss'
                                                           ),
                                  p_notificato       => 0,
                                  p_gravita          => 'E',
                                  p_tipo             => 'ARCLOG',
                                  p_annotazioni      => '',
                                  p_utente           => NVL (si4.utente,
                                                             'AD4'),
                                  p_modulo           => NVL (si4.modulo,
                                                             'AD4'),
                                  p_istanza          => NVL (si4.istanza,
                                                             'AD4'
                                                            )
                                 );
         END;
      END IF;
      COMMIT;
   END archivia;
   FUNCTION ripristina
/******************************************************************************
 NOME:        RIPRISTINA
 DESCRIZIONE: Ripristina le registrazioni di accesso archiviate nel file XML
              associate all'evento p_id_evento.
 PARAMETRI:   p_id_evento identificativo dell'evento in cui e' memorizzato
                          il file xml contenente le registrazioni di accesso
                          archiviate da ripristinare.
 RITORNA:     number      numero di registrazioni di accesso ripristinate.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    07/12/2005 MM     Prima emissione.
******************************************************************************/
   (p_id_evento IN NUMBER)
      RETURN NUMBER
   IS
      d_return   NUMBER;
      d_count    NUMBER;
   BEGIN
      SELECT COUNT (1)
        INTO d_count
        FROM accessi
       WHERE stato = 'D';
      IF d_count = 0
      THEN
         d_return :=
                ad4_evento.ripristina (p_id_evento, 'ACCESSI', 'ACCESSO_ROW');
         DELETE      accessi acce
               WHERE acce.stato = 'D'
                 AND EXISTS (
                        SELECT 1
                          FROM accessi acce2
                         WHERE acce2.stato <> 'D'
                           AND NVL (acce.session_id, 0) =
                                                     NVL (acce2.session_id, 0)
                           AND acce.DATA = acce2.DATA
                           AND acce.LOG = acce2.LOG
                           AND NVL (acce.utente, ' ') =
                                                       NVL (acce2.utente, ' ')
                           AND NVL (acce.istanza, ' ') =
                                                      NVL (acce2.istanza, ' ')
                           AND NVL (acce.modulo, ' ') =
                                                       NVL (acce2.modulo, ' ')
                           AND NVL (acce.db_user, ' ') =
                                                      NVL (acce2.db_user, ' ')
                           AND NVL (acce.terminale, ' ') =
                                                    NVL (acce2.terminale, ' ')
                           AND NVL (acce.note, ' ') = NVL (acce2.note, ' ')
                           AND NVL (acce.id_credenziale, 0) =
                                                 NVL (acce2.id_credenziale, 0)
                           AND NVL (acce.tipo_autenticazione, ' ') =
                                          NVL (acce2.tipo_autenticazione, ' '));
         UPDATE accessi
            SET stato = 'A'
          WHERE stato = 'D';
      ELSE
         raise_application_error
            (-20999,
             'Impossibile rispristinare gli accessi. Archiviazione in corso.'
            );
      END IF;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END ripristina;
   FUNCTION elimina_archiviati
/******************************************************************************
 NOME:        ELIMINA_ARCHIVIATI
 DESCRIZIONE: Elimina le registrazioni di accesso precedentemente ripristinate.
 PARAMETRI:   --
 RITORNA:     number   numero di registrazioni di accesso eliminate.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    07/12/2005 MM     Prima emissione.
******************************************************************************/
   RETURN NUMBER
   IS
   BEGIN
      DELETE      accessi
            WHERE stato = 'A';
      RETURN SQL%ROWCOUNT;
   END elimina_archiviati;
   FUNCTION is_job_attivo
/******************************************************************************
 NOME:        IS_JOB_ATTIVO
 DESCRIZIONE: Verifica se il job  e' attivo.
 PARAMETRI:   --
 RITORNA:     number   1 job attivo
                       0 job non attivo o inesistente
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    07/12/2005 MM     Prima emissione.
******************************************************************************/
   (p_testo IN VARCHAR2)
      RETURN NUMBER
   IS
      d_return   NUMBER;
      d_testo    VARCHAR2 (32767);
   BEGIN
      d_testo := '%' || p_testo || '%';
      SELECT DECODE (user_jobs.broken, 'N', 1, 0) attivo
        INTO d_return
        FROM user_jobs
       WHERE user_jobs.what LIKE d_testo
         AND user_jobs.job = (SELECT MIN (user_jobs.job)
                                FROM user_jobs
                               WHERE user_jobs.what LIKE d_testo)
      UNION
      SELECT 0
        FROM DUAL
       WHERE NOT EXISTS (SELECT 1
                           FROM user_jobs
                          WHERE user_jobs.what LIKE d_testo);
      RETURN d_return;
   END is_job_attivo;
   FUNCTION is_job_archiviazione_attivo
/******************************************************************************
 NOME:        IS_JOB_ARCHIVIAZIONE_ATTIVO
 DESCRIZIONE: Verifica se il job di archiviazione e' attivo.
 PARAMETRI:   --
 RITORNA:     number   1 job attivo
                       0 job non attivo o inesistente
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    07/12/2005 MM     Prima emissione.
******************************************************************************/
   RETURN NUMBER
   IS
   BEGIN
      RETURN is_job_attivo ('accesso.archivia');
   END is_job_archiviazione_attivo;
   PROCEDURE elimina_traccia
/******************************************************************************
 NOME:        ELIMINA_TRACCIA
 DESCRIZIONE: Elimina le 'vecchie' registrazioni di accesso:
              per ogni accesso:
                 verifica ogni quanti giorni va eliminata la traccia e che non
                 sia prevista l'archiviazione e procede all'eliminazione.
 ARGOMENTI:   --
 NOTE:        Viene eseguito COMMIT.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    07/12/2005 MM     Prima emissione.
 22
******************************************************************************/
   IS
      d_tipo_accesso     VARCHAR2 (1);
      d_giorni_traccia   NUMBER (3);
      d_arch_traccia     VARCHAR2 (2);
      d_progetto         VARCHAR2 (10) := ' ';
      d_istanza          VARCHAR2 (8)  := ' ';
      d_modulo           VARCHAR2 (10) := ' ';
      d_utente           VARCHAR2 (8)  := ' ';
      v_LogAccessRetention number;
   BEGIN
      FOR acce_cur IN
         (SELECT   ista.progetto, ista.istanza, modulo, utente, acce_id,
                   DATA
              FROM accessi acce, istanze ista
             WHERE acce.istanza = ista.istanza
               AND stato = 'U'
               AND 'NO' =
                      NVL
                         (caratteristica_accesso.get ('archiviazione_traccia',
                                                      'I',
                                                      ista.progetto,
                                                      ista.istanza
                                                     ),
                          'NO'
                         )
          ORDER BY 1, 2, 3, 4)
      LOOP
         IF    d_progetto != acce_cur.progetto
            OR d_istanza != acce_cur.istanza
            OR d_modulo != acce_cur.modulo
            OR d_utente != acce_cur.utente
         THEN
            d_progetto := acce_cur.progetto;
            d_istanza := acce_cur.istanza;
            d_modulo := acce_cur.modulo;
            d_utente := acce_cur.utente;
            d_giorni_traccia :=
               NVL (caratteristica_accesso.get ('giorni_traccia',
                                                'D',
                                                acce_cur.progetto,
                                                acce_cur.istanza,
                                                acce_cur.modulo,
                                                acce_cur.utente
                                               ),
                    60
                   );
         END IF;
         -- REV 22 del 12/05/2016 - INIZIO
         -- Calcolo giorni previsti di LogAccessRetention dal registro
         -- validi su tutto applicativo
         v_LogAccessRetention := NVL
                  (LOWER
                      (registro_utility.leggi_stringa
                                                     ('PRODUCTS/AUTHENTICATION',
                                                      'LogAccessRetention',
                                                      0
                                                     )
                      ),
                   v_LogAccessRetention_MIN
                  );
         IF acce_cur.DATA <=
                 TO_DATE (TO_CHAR (TRUNC (SYSDATE), 'dd/mm/yyyy')
                          || '23:59:59',
                          'dd/mm/yyyy hh24:mi:ss'
                         )
               -  NVL (d_giorni_traccia, v_LogAccessRetention)
               -- REV 22 del 12/05/2016 - FINE
         THEN
            DELETE      eventi
                  WHERE id_evento = acce_cur.acce_id;
         END IF;
      END LOOP;
      DELETE      accessi
            WHERE istanza NOT IN (SELECT istanza
                                    FROM istanze)
              AND DATA <=
                       TO_DATE (   TO_CHAR (TRUNC (SYSDATE), 'dd/mm/yyyy')
                                || '23:59:59',
                                'dd/mm/yyyy hh24:mi:ss'
                               )
                     - 60;
      COMMIT;
   END elimina_traccia;
   PROCEDURE attiva_eliminazione_traccia
/******************************************************************************
 NOME:        ELIMINA_TRACCIA
 DESCRIZIONE: Elimina le 'vecchie' registrazioni di accesso:
              per ogni accesso:
                 verifica ogni quanti giorni va eliminata la traccia e che non
                 sia prevista l'archiviazione e procede all'eliminazione.
 ARGOMENTI:   --
 NOTE:        Viene eseguito COMMIT.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    07/12/2005 MM     Prima emissione.
******************************************************************************/
   (
      p_chiave_new    IN   VARCHAR2,
      p_stringa_new   IN   VARCHAR2,
      p_valore_new    IN   VARCHAR2,
      p_chiave_old    IN   VARCHAR2 DEFAULT NULL,
      p_stringa_old   IN   VARCHAR2 DEFAULT NULL,
      p_valore_old    IN   VARCHAR2 DEFAULT NULL
   )
   IS
      d_attiva_old   VARCHAR2 (10) := 'NA';
      d_attiva_new   VARCHAR2 (10) := 'NA';
      d_job          NUMBER;
      d_action       VARCHAR2 (1);
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      IF    (    p_chiave_new IS NULL
             AND p_stringa_new IS NULL
             AND p_chiave_old IS NULL
             AND p_stringa_old IS NULL
            )
         OR (   (p_chiave_new IS NULL AND p_stringa_new IS NOT NULL)
             OR (p_chiave_old IS NULL AND p_stringa_old IS NOT NULL)
             OR (p_chiave_new IS NOT NULL AND p_stringa_new IS NULL)
             OR (p_chiave_old IS NOT NULL AND p_stringa_old IS NULL)
            )
         OR (    UPPER (p_chiave_new) <> 'PRODUCTS/AD4/ACCESSI'
             AND UPPER (p_chiave_old) <> 'PRODUCTS/AD4/ACCESSI'
            )
         OR (    LOWER (p_stringa_new) <> 'eliminazionedifferita'
             AND LOWER (p_stringa_old) <> 'eliminazionedifferita'
            )
      THEN
         d_action := '?';
      ELSIF p_chiave_new IS NULL AND p_stringa_new IS NULL
      THEN
         d_action := 'D';
      ELSIF p_chiave_old IS NULL AND p_stringa_old IS NULL
      THEN
         d_action := 'I';
      ELSE
         d_action := 'U';
      END IF;
      IF d_action <> '?'
      THEN
         IF     UPPER (p_chiave_new) = 'PRODUCTS/AD4/ACCESSI'
            AND LOWER (p_stringa_new) = 'eliminazionedifferita'
         THEN
            d_attiva_new := LOWER (NVL (p_valore_new, 'no'));
         ELSE
            d_attiva_new := 'no';
         END IF;
         IF     UPPER (p_chiave_old) = 'PRODUCTS/AD4/ACCESSI'
            AND LOWER (p_stringa_old) = 'eliminazionedifferita'
         THEN
            d_attiva_old := LOWER (NVL (p_valore_old, 'no'));
         ELSE
            d_attiva_old := 'no';
         END IF;
         IF d_attiva_new <> d_attiva_old
         THEN
            BEGIN
               SELECT job
                 INTO d_job
                 FROM user_jobs
                WHERE INSTR (LOWER (what), 'accesso.elimina_traccia') > 0;
               DBMS_JOB.remove (d_job);
               COMMIT;
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  NULL;
            END;
            IF d_attiva_new <> 'no'
            THEN
               d_job := TO_NUMBER (NULL);
               DBMS_JOB.submit
                              (job            => d_job,
                               what           => 'begin accesso.elimina_traccia; end;',
                               next_date      => TRUNC (SYSDATE + 1) + 1 / 24,
                               INTERVAL       => 'SYSDATE + 1',
                               no_parse       => TRUE
                              );
            END IF;
            COMMIT;
         END IF;
      END IF;
   END attiva_eliminazione_traccia;
  /******************************************************************************
 NOME:        LOGIN_SETUP
 DESCRIZIONE: Configurazioni da effettuare in seguito ad un login
 ARGOMENTI:   p_nominativo  IN     VARCHAR2   nominativo dell'utente che fa login
              p_server IN VARCHAR2 stringa del server come scritta nel registro
 ECCEZIONI:
              Lancia la procedure:
              RIGENERA_SO
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
   6    25/03/2011 SN     Creata procedure login_setup che esegue configurazioni al login
  14   16/09/2014 SN     Passato anche il server su cui si è autenticato nullo se non utente  ldap
 15    18/02/2015 SN     Aggiunto parametro x forzatura login_setup
 18    09/06/2015 SN     Utilizzata caratteristica_accesso x vedere se utente e ldap
******************************************************************************/
      PROCEDURE LOGIN_SETUP( p_nominativo utenti.nominativo%TYPE
                           , p_server varchar2 default null
                           , p_forzato varchar2 default 'N')
      IS
      d_utente utenti.utente%type;
      d_id number;
      v_todbmapping varchar2(2000);
      BEGIN
      d_utente := utente.get_utente(p_nominativo);
      dbms_output.put_line('utente'||d_utente);
      if  d_utente is not null then -- trovato
       -- se utente ha usato credenziali ldap
       -- copio in ad4 i suoi attributi.
       -- LDAP_TO_AD4.do_ldap_mapping (d_utente, d_server su cui si è autenticato);
         if (nvl(caratteristica_accesso.is_ldapuser(d_utente),0) = 1  or
            nvl(p_forzato,'N') != 'N') then
               BEGIN
                  SELECT lower(valore)
                    INTO v_todbmapping
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
                             INTO v_todbmapping
                             FROM registro
                            WHERE     stringa = 'ToDbMapping'
                                  AND chiave = 'PRODUCTS/LDAPCONFIG';
                        EXCEPTION
                           WHEN NO_DATA_FOUND
                           THEN
                              v_todbmapping := 'no';
                        END;
                     END IF;
               END;
            if v_todbmapping = 'yes'
             then
                LDAP_TO_DB.allinea_DB (p_nominativo, p_server);
             end if;
          end if;
          UTENTE.RIGENERA_SO(d_utente);
       end if;
      END;
 /******************************************************************************
 NOME:        LOGIN_SETUP
 DESCRIZIONE: Configurazioni da effettuare in seguito ad un login
 ARGOMENTI:   p_nominativo  IN     VARCHAR2   nominativo dell'utente che fa login
 ECCEZIONI:
              Lancia la procedure:
              RIGENERA_SO
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
   6    25/03/2011 SN     Creata procedure login_setup che esegue configurazioni al login
  28    08/01/2019 SN     Aggiunta sistemazione dei gruppi in base a utente amministratore o no
******************************************************************************/
      PROCEDURE LOGIN_SETUP(p_nominativo utenti.nominativo%TYPE)
      IS
      d_utente utenti.utente%type;
      BEGIN
        select utente
           into d_utente
          from utenti
        where nominativo = upper(trim(p_nominativo))
        ;
        UTENTE.RIGENERA_SO(d_utente);
        UTENTE.sistema_posizione_gruppi(d_utente);
      END;
begin
     v_autenticazione_esterna := NVL
                  (upper
                      (registro_utility.leggi_stringa
                                                     ('PRODUCTS/AUTHENTICATION',
                                                      'ExternalAutentication',
                                                      0
                                                     )
                      ),
                   'NO'
                  );
END accesso;
/

