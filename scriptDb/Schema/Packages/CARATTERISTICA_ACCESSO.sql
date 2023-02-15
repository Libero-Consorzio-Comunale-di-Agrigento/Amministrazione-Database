CREATE OR REPLACE PACKAGE Caratteristica_Accesso IS /* MASTER_LINK */
/******************************************************************************
 NOME:        CARATTERISTICA_ACCESSO.
 DESCRIZIONE: Package per gestione CARATTERISTICHE_ACCESSO.
 DIPENDENZE:
 ANNOTAZIONI: Salvato nella directory ins di AD4 nel file caac.pkg.
 ECCEZIONI:.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    12/04/2005 MM     Prima emissione.
 2    25/05/2006 MM     V4.5.
 3    22/03/2007 MM     A20271.0.0.: modificata update_caratteristica.
 4    24/04/2007 MM     A20741.0.0: aggiunte procedure del.
 05   13/07/2007 MM     Adeguamento versione a nuovi standard e creazione
                        funzione exists_id.
06    21/09/2018 SN    Aggiunto campo gg_prima_riutilizzo_pwd
07    16/10/2018 SN    Aggiunta VERIFICA_PASSWORD_STORICO_OK
******************************************************************************/
   -- Revisione del Package
   s_revisione CONSTANT Afc.t_revision := 'V1.06' ;
-- VERSIONE DEL PACKAGE.
/******************************************************************************
 NOME:        VERSIONE.
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
******************************************************************************/
    FUNCTION  VERSIONE /* SLAVE_COPY */
    RETURN VARCHAR2;
    PROCEDURE GET /* SLAVE_COPY */
    ( p_tipo_accesso IN OUT VARCHAR2
    , p_progetto VARCHAR2
    , p_istanza VARCHAR2
    , p_modulo VARCHAR2
    , p_utente VARCHAR2
    , p_accesso IN OUT VARCHAR2
    , p_accesso_se IN OUT VARCHAR2
    , p_traccia IN OUT VARCHAR2
    , p_giorni_traccia IN OUT NUMBER
    , p_tentativi_pwd IN OUT NUMBER
    , p_val_pwd IN OUT NUMBER
    , p_sleep IN OUT NUMBER
    , p_single_sign_on IN OUT VARCHAR2
    , p_ldap IN OUT VARCHAR2
    , p_min_lung_pwd IN OUT NUMBER
    , p_mod_pwd_primo_accesso IN OUT VARCHAR2
    , p_arch_traccia IN OUT VARCHAR2
    , p_disl_traccia IN OUT VARCHAR2
    , p_car_speciali_pwd IN OUT VARCHAR2
    , p_num_obb_pwd IN OUT VARCHAR2
    , p_giorni_prima_riutilizzo_pwd  IN OUT NUMBER);
   function exists_id /* SLAVE_COPY */
   ( p_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type
   , p_progetto  in CARATTERISTICHE_ACCESSO.progetto%type
   , p_istanza  in CARATTERISTICHE_ACCESSO.istanza%type
   , p_modulo  in CARATTERISTICHE_ACCESSO.modulo%type
   , p_utente  in CARATTERISTICHE_ACCESSO.utente%type) return number;
   FUNCTION GET /* SLAVE_COPY */
   ( p_caratteristica VARCHAR2
   , p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2 DEFAULT NULL
   , p_modulo VARCHAR2 DEFAULT NULL
   , p_utente VARCHAR2 DEFAULT NULL) RETURN VARCHAR2;
    PROCEDURE GET_EFFETTIVE /* SLAVE_COPY */
    ( p_tipo_accesso IN OUT VARCHAR2
    , p_progetto VARCHAR2
    , p_istanza VARCHAR2
    , p_modulo VARCHAR2
    , p_utente VARCHAR2
    , p_accesso IN OUT VARCHAR2
    , p_accesso_se IN OUT VARCHAR2
    , p_traccia IN OUT VARCHAR2
    , p_giorni_traccia IN OUT NUMBER
    , p_tentativi_pwd IN OUT NUMBER
    , p_val_pwd IN OUT NUMBER
    , p_sleep IN OUT NUMBER
    , p_single_sign_on IN OUT VARCHAR2
    , p_ldap IN OUT VARCHAR2
    , p_min_lung_pwd IN OUT NUMBER
    , p_mod_pwd_primo_accesso IN OUT VARCHAR2
    , p_arch_traccia IN OUT VARCHAR2
    , p_disl_traccia IN OUT VARCHAR2
    , p_car_speciali_pwd IN OUT VARCHAR2
    , p_num_obb_pwd IN OUT VARCHAR2
    , p_giorni_prima_riutilizzo_pwd  IN OUT NUMBER);
   FUNCTION GET_DESC /* SLAVE_COPY */
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2) RETURN VARCHAR2;
   FUNCTION GET_DESC_ACCESSO /* SLAVE_COPY */
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2) RETURN VARCHAR2;
   FUNCTION GET_DESC_PASSWORD /* SLAVE_COPY */
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2) RETURN VARCHAR2;
   FUNCTION GET_DESC_AUTENTICAZIONE /* SLAVE_COPY */
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2) RETURN VARCHAR2;
   FUNCTION GET_DESC_DETTAGLI /* SLAVE_COPY */
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2)   RETURN VARCHAR2;
   FUNCTION GET_VAL_PWD /* SLAVE_COPY */
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2) RETURN NUMBER;
   FUNCTION GET_TENTATIVI_PWD /* SLAVE_COPY */
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2) RETURN NUMBER;
   FUNCTION GET_ACCESSO /* SLAVE_COPY */
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2) RETURN VARCHAR2;
   FUNCTION GET_TRACCIA /* SLAVE_COPY */
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2) RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_DIAC_IS_NUM_OBB_PWD
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto che la password debba contenere almeno un numero.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
******************************************************************************/
   FUNCTION GET_DIAC_IS_NUM_OBB_PWD /* SLAVE_COPY */
   ( p_utente VARCHAR2 ) RETURN VARCHAR2;
/******************************************************************************
 NOME:        IS_LDAPUSER
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista l'autenticazione via LDAP.
 PARAMETRI:   p_utente varchar2 utente da autenticare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' prevista
                l'autenticazione via LDAP,
              0 altrimenti.
******************************************************************************/
    FUNCTION IS_LDAPUSER /* SLAVE_COPY */
    ( p_utente VARCHAR2
    , p_tipo_utente varchar2 default null ) RETURN NUMBER;
/******************************************************************************
 NOME:        GET_MINPWDLENGTH
 DESCRIZIONE: Ottiene il valore piu' grande della minima lunghezza della password
              settata per l'utente (considerando tutti i suoi diritti di accesso).
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     il valore piy grande della minima lunghezza della password.
******************************************************************************/
    FUNCTION GET_MINPWDLENGTH /* SLAVE_COPY */
    ( p_utente VARCHAR2
    , p_tipo_utente varchar2 default null ) RETURN NUMBER;

/******************************************************************************
 NOME:        GET_MIN_MAX_TENTATIVI_PWD
 DESCRIZIONE: Ottiene il valore piu' piccolo del numero dei tentativi
              settata per l'utente (considerando tutti i suoi diritti di accesso).
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     il valore piu' piccolo del numero dei tentativi
******************************************************************************/
    FUNCTION GET_MIN_MAX_TENTATIVI_PWD
   ( p_utente VARCHAR2
   , p_tipo_utente varchar2 default null )
   RETURN NUMBER;

/******************************************************************************
 NOME:        GET_DIAC_MAX_MINPWDLENGTH
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto il valore piu' grande della minima lunghezza della password
           per l'utente.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
******************************************************************************/
   FUNCTION IS_CAR_SPECIALI_PWD /* SLAVE_COPY */
   ( p_utente VARCHAR2
   , p_tipo_utente varchar2 default null ) RETURN NUMBER;
/******************************************************************************
 NOME:        IS_CAR_SPECIALI_PWD
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista la possibilita' di utilizzare caratteri speciali nella
              password.
 PARAMETRI:   p_utente varchar2 utente da autenticare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' previsto
              0 altrimenti.
******************************************************************************/
   FUNCTION IS_NUM_OBB_PWD /* SLAVE_COPY */
   ( p_utente VARCHAR2
   , p_tipo_utente varchar2 default null ) RETURN NUMBER;
/******************************************************************************
 NOME:        IS_NUM_OBB_PWD
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista la presenza obbligatoria di almeno un numero nella
              password.
 PARAMETRI:   p_utente varchar2 utente da autenticare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' previsto
              0 altrimenti.
******************************************************************************/
    FUNCTION GET_DIAC_MAX_MINPWDLENGTH /* SLAVE_COPY */
    ( p_utente VARCHAR2 ) RETURN VARCHAR2;
/******************************************************************************
 NOME:        IS_PWD_DA_MOD_PRIMO_USO
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista la modifica della password al primo accesso.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' prevista
                la modifica della password al primo accesso.
              0 altrimenti.
******************************************************************************/
    FUNCTION IS_PWD_DA_MOD_PRIMO_USO /* SLAVE_COPY */
    ( p_utente VARCHAR2
    , p_tipo_utente varchar2 default null) RETURN NUMBER;
/******************************************************************************
 NOME:        GET_DIAC_ISPWDDAMOD
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto il valore effettivo del campo MOD_PWD_PRIMO_ACCESSO
           per l'utente.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
******************************************************************************/
   FUNCTION GET_DIAC_ISPWDDAMOD /* SLAVE_COPY */
   ( p_utente VARCHAR2 ) RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_DIAC_ISLDAPUSER
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto il valore effettivo del campo LDAP per l'utente.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
******************************************************************************/
    FUNCTION GET_DIAC_ISLDAPUSER /* SLAVE_COPY */
    ( p_utente VARCHAR2 ) RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_DIAC_IS_CAR_SPECIALI_PWD
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto che la password possa contenere caratteri speciali.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
******************************************************************************/
   FUNCTION GET_DIAC_IS_CAR_SPECIALI_PWD /* SLAVE_COPY */
   ( p_utente VARCHAR2 )  RETURN VARCHAR2;

/******************************************************************************
 NOME:        GET_DIAC_MAX_GG_RIUSO_PWD
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto il valore piu' grande el minimo numero di giorni per riutilizzo della password
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
******************************************************************************/
    FUNCTION GET_DIAC_MAX_GG_RIUSO_PWD /* SLAVE_COPY */
    ( p_utente VARCHAR2 ) RETURN VARCHAR2;

/******************************************************************************
 NOME:        GET_GG_PRIMA_RIUTILIZZO_PWD
 DESCRIZIONE: Ottiene il valore piu' grande del minimo numero di giorni per riutilizzo della password
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     il valore piy grande del minimo numero di giorni per riutilizzo della password
******************************************************************************/
    FUNCTION GET_GG_PRIMA_RIUTILIZZO_PWD /* SLAVE_COPY */
    ( p_utente VARCHAR2
    , p_tipo_utente varchar2 default null ) RETURN NUMBER;

    PROCEDURE UPDATE_CARATTERISTICA ( old_caac_id IN NUMBER, old_progetto IN VARCHAR2
    , old_istanza IN VARCHAR2, old_modulo IN VARCHAR2, old_utente IN VARCHAR2
    , new_caac_id IN NUMBER, new_tipo_accesso IN VARCHAR2, new_progetto IN VARCHAR2
    , new_istanza IN VARCHAR2, new_modulo IN VARCHAR2, new_utente IN VARCHAR2
    , new_accesso IN VARCHAR2, new_accesso_se IN VARCHAR2, new_traccia IN VARCHAR2
    , new_giorni_traccia IN NUMBER, new_tentativi_password IN NUMBER
    , new_validita_password IN NUMBER, new_sleep IN NUMBER
    , new_single_sign_on IN VARCHAR2, new_ldap IN VARCHAR2
    , new_min_lunghezza_pwd IN NUMBER, new_mod_pwd_primo_accesso IN VARCHAR2
    , new_arch_traccia IN VARCHAR2, new_disl_traccia IN VARCHAR2
    , new_car_speciali_pwd IN VARCHAR2, new_num_obb_pwd IN VARCHAR2
    , new_gg_prima_riutilizzo_pwd  IN  NUMBER
    , p_move_file IN NUMBER DEFAULT 0);
    FUNCTION INSERT_CARATTERISTICA
   ( new_caac_id IN NUMBER, new_tipo_accesso IN VARCHAR2, new_progetto IN VARCHAR2
   , new_istanza IN VARCHAR2, new_modulo IN VARCHAR2, new_utente IN VARCHAR2
   , new_accesso IN VARCHAR2, new_accesso_se IN VARCHAR2, new_traccia IN VARCHAR2
   , new_giorni_traccia IN NUMBER, new_tentativi_password IN NUMBER
   , new_validita_password IN NUMBER, new_sleep IN NUMBER
   , new_single_sign_on IN VARCHAR2, new_ldap IN VARCHAR2
   , new_min_lunghezza_pwd IN NUMBER, new_mod_pwd_primo_accesso IN VARCHAR2
   , new_arch_traccia IN VARCHAR2, new_disl_traccia IN VARCHAR2
   , new_car_speciali_pwd IN VARCHAR2, new_num_obb_pwd IN VARCHAR2
   , new_gg_prima_riutilizzo_pwd  IN  NUMBER) RETURN NUMBER;
   FUNCTION IS_EQUAL /* SLAVE_COPY */
   ( p_tipo_accesso_1 IN OUT VARCHAR2, p_progetto_1 IN VARCHAR2
   , p_istanza_1 IN VARCHAR2, p_modulo_1 IN VARCHAR2, p_utente_1 IN VARCHAR2
   , p_tipo_accesso_2 IN OUT VARCHAR2, p_progetto_2 IN VARCHAR2
   , p_istanza_2 IN VARCHAR2, p_modulo_2 IN VARCHAR2, p_utente_2 IN VARCHAR2) RETURN NUMBER;
   PROCEDURE VALIDITA_PWD /* SLAVE_COPY */
   ( p_utente VARCHAR2
   , p_tentativi_pwd INTEGER
   , p_val_pwd INTEGER);
   PROCEDURE VALIDITA_PWD /* SLAVE_COPY */
   ( p_utente VARCHAR2
   , p_modulo VARCHAR2
   , p_istanza VARCHAR2);
/******************************************************************************
 NOME:        GESTISCI_PWDLENGTH.
 DESCRIZIONE: Controlla che la lunghezza della password di ogni utente sia
              maggiore uguale alla minima prevista.
              Se cosi non e:
              se l'utente ha la possibilita' di rinnovarsi autonomamente
                 la password,
                    fa in modo che debba essere modificata al primo accesso
                    dell'utente;
                 altrimenti
                    aggiunge il nominativo ed il codice dell'utente al clob
                    ritornato dalla funzione.
 PARAMETRI:   --.
 RITORNA:     CLOB: elenco degli eventuali utenti con password di lunghezza
              minore alla minima prevista ma senza possibilita' di rinnovo
              autonoma.
 ECCEZIONI:   .
 ANNOTAZIONI: per lanciarla da SQL*Plus:
              set long 32000
              set linesize 135
              col GESTISCI_PWDLENGTH format a32000
              select caratteristica_accesso.GESTISCI_PWDLENGTH from dual;
******************************************************************************/
   FUNCTION GET_INVALID_PWD_LOG /* SLAVE_COPY */
   ( p_progetto IN VARCHAR2 DEFAULT '%'
   , p_istanza IN VARCHAR2 DEFAULT '%'
   , p_modulo IN VARCHAR2 DEFAULT '%'
   , p_utente IN VARCHAR2 DEFAULT '%') RETURN CLOB;
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
******************************************************************************/
   FUNCTION IS_PASSWORD_VALIDA /* SLAVE_COPY */
   ( p_utente VARCHAR2 ) return number;
   PROCEDURE del
      ( p_CAAC_ID  IN CARATTERISTICHE_ACCESSO.CAAC_ID%TYPE
      , p_TIPO_ACCESSO IN CARATTERISTICHE_ACCESSO.TIPO_ACCESSO%TYPE DEFAULT NULL
      , p_PROGETTO  IN CARATTERISTICHE_ACCESSO.PROGETTO%TYPE DEFAULT NULL
      , p_ISTANZA  IN CARATTERISTICHE_ACCESSO.ISTANZA%TYPE DEFAULT NULL
      , p_MODULO  IN CARATTERISTICHE_ACCESSO.MODULO%TYPE DEFAULT NULL
      , p_UTENTE IN CARATTERISTICHE_ACCESSO.UTENTE%TYPE DEFAULT NULL
      , p_ACCESSO IN CARATTERISTICHE_ACCESSO.ACCESSO%TYPE  DEFAULT NULL
      , p_ACCESSO_SE  IN CARATTERISTICHE_ACCESSO.ACCESSO_SE%TYPE DEFAULT NULL
      , p_TRACCIA  IN CARATTERISTICHE_ACCESSO.TRACCIA%TYPE DEFAULT NULL
      , p_GIORNI_TRACCIA  IN CARATTERISTICHE_ACCESSO.GIORNI_TRACCIA%TYPE DEFAULT NULL
      , p_TENTATIVI_PASSWORD  IN CARATTERISTICHE_ACCESSO.TENTATIVI_PASSWORD%TYPE DEFAULT NULL
      , p_VALIDITA_PASSWORD  IN CARATTERISTICHE_ACCESSO.VALIDITA_PASSWORD%TYPE DEFAULT NULL
      , p_SINGLE_SIGN_ON  IN CARATTERISTICHE_ACCESSO.SINGLE_SIGN_ON%TYPE  DEFAULT NULL
      , p_SLEEP  IN CARATTERISTICHE_ACCESSO.SLEEP%TYPE DEFAULT NULL
      , p_LDAP  IN CARATTERISTICHE_ACCESSO.LDAP%TYPE  DEFAULT NULL
      , p_MIN_LUNGHEZZA_PWD  IN CARATTERISTICHE_ACCESSO.MIN_LUNGHEZZA_PWD%TYPE  DEFAULT NULL
      , p_MOD_PWD_PRIMO_ACCESSO  IN CARATTERISTICHE_ACCESSO.MOD_PWD_PRIMO_ACCESSO%TYPE  DEFAULT NULL
      , p_ARCHIVIAZIONE_TRACCIA  IN CARATTERISTICHE_ACCESSO.ARCHIVIAZIONE_TRACCIA%TYPE  DEFAULT NULL
      , p_DISLOCAZIONE_TRACCIA  IN CARATTERISTICHE_ACCESSO.DISLOCAZIONE_TRACCIA%TYPE  DEFAULT NULL
      , p_AMMESSI_CAR_SPECIALI_PWD  IN CARATTERISTICHE_ACCESSO.AMMESSI_CAR_SPECIALI_PWD%TYPE DEFAULT NULL
      , p_NUMERI_OBB_PWD  IN CARATTERISTICHE_ACCESSO.NUMERI_OBB_PWD%TYPE DEFAULT NULL
      , p_gg_prima_riutilizzo_pwd IN CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE DEFAULT NULL
      , p_check_OLD  IN INTEGER DEFAULT 0
   );
   PROCEDURE del
      ( p_TIPO_ACCESSO IN CARATTERISTICHE_ACCESSO.TIPO_ACCESSO%TYPE
      , p_PROGETTO  IN CARATTERISTICHE_ACCESSO.PROGETTO%TYPE
      , p_ISTANZA  IN CARATTERISTICHE_ACCESSO.ISTANZA%TYPE DEFAULT NULL
      , p_MODULO  IN CARATTERISTICHE_ACCESSO.MODULO%TYPE DEFAULT NULL
      , p_UTENTE IN CARATTERISTICHE_ACCESSO.UTENTE%TYPE DEFAULT NULL
      , p_ACCESSO IN CARATTERISTICHE_ACCESSO.ACCESSO%TYPE  DEFAULT NULL
      , p_ACCESSO_SE  IN CARATTERISTICHE_ACCESSO.ACCESSO_SE%TYPE DEFAULT NULL
      , p_TRACCIA  IN CARATTERISTICHE_ACCESSO.TRACCIA%TYPE DEFAULT NULL
      , p_GIORNI_TRACCIA  IN CARATTERISTICHE_ACCESSO.GIORNI_TRACCIA%TYPE DEFAULT NULL
      , p_TENTATIVI_PASSWORD  IN CARATTERISTICHE_ACCESSO.TENTATIVI_PASSWORD%TYPE DEFAULT NULL
      , p_VALIDITA_PASSWORD  IN CARATTERISTICHE_ACCESSO.VALIDITA_PASSWORD%TYPE DEFAULT NULL
      , p_SINGLE_SIGN_ON  IN CARATTERISTICHE_ACCESSO.SINGLE_SIGN_ON%TYPE  DEFAULT NULL
      , p_SLEEP  IN CARATTERISTICHE_ACCESSO.SLEEP%TYPE DEFAULT NULL
      , p_LDAP  IN CARATTERISTICHE_ACCESSO.LDAP%TYPE  DEFAULT NULL
      , p_MIN_LUNGHEZZA_PWD  IN CARATTERISTICHE_ACCESSO.MIN_LUNGHEZZA_PWD%TYPE  DEFAULT NULL
      , p_MOD_PWD_PRIMO_ACCESSO  IN CARATTERISTICHE_ACCESSO.MOD_PWD_PRIMO_ACCESSO%TYPE  DEFAULT NULL
      , p_ARCHIVIAZIONE_TRACCIA  IN CARATTERISTICHE_ACCESSO.ARCHIVIAZIONE_TRACCIA%TYPE  DEFAULT NULL
      , p_DISLOCAZIONE_TRACCIA  IN CARATTERISTICHE_ACCESSO.DISLOCAZIONE_TRACCIA%TYPE  DEFAULT NULL
      , p_AMMESSI_CAR_SPECIALI_PWD  IN CARATTERISTICHE_ACCESSO.AMMESSI_CAR_SPECIALI_PWD%TYPE DEFAULT NULL
      , p_NUMERI_OBB_PWD  IN CARATTERISTICHE_ACCESSO.NUMERI_OBB_PWD%TYPE DEFAULT NULL
      , p_gg_prima_riutilizzo_pwd IN CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE DEFAULT NULL
      , p_check_OLD  IN INTEGER DEFAULT 0
   );

   FUNCTION VERIFICA_PASSWORD_STORICO_OK
/******************************************************************************
 NOME:        VERIFICA_PASSWORD_STORICO_OK.
 DESCRIZIONE: dati un nominativo utente e la password, verifica che la
              password passata non sia stata già usata dallo stesso utente
              prima dei giorni previsti.
 PARAMETRI:   p_nominativo IN varchar2: nominativo utente.
              p_password   IN varchar2: password utente NON criptata.
 RITORNA:     number: 1 = verifica effettuata con successo.
                      0 = verifica fallita.
 ECCEZIONI:   -20999: Utente '<p_nominativo>' non definito.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    16/10/2018 SNeg    Prima emissione
******************************************************************************/
   ( p_utente IN VARCHAR2
   , p_password IN VARCHAR2 default NULL
   )
      RETURN NUMBER;

END Caratteristica_Accesso;
/

