--liquibase formatted sql

--changeset mturra:2019006051231_198 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE Accesso IS /* MASTER_LINK */
/******************************************************************************
 NOME:        ACCESSO
 DESCRIZIONE: Package per gestione ACCESSI al DB.
 ANNOTAZIONI: -
 ECCEZIONI:-20999      Errore bloccante generico.
           -20998      Warning generico.
           -20103      Warning: rilevati altri accessi dello stesso utente
                       e password scaduta con possibilita di rinnovo.
           -20102      Warning: rilevati altri accessi dello stesso utente.
           -20101      Warning: password scaduta con possibilita di rinnovo.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    16/01/2001 MM     Prima emissione.
 1    05/12/2002 MM     - Sostituzione di V$SESSION con sinonimo SESSIONI
                         (punta a V$SESSION se db in 7 a GV$SESSION se in 8).
                        - creazione delle funzioni IS_LDAPUSER,
                          GET_MINPWDLENGTH, FUNZIONEIS_PWD_DA_MOD_PRIMO_USO
 2    29/11/2005 MM     Spostamento funzioni in CARATTERISTICA_ACCESSO.
                 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA e
                        DISLOCAZIONE_TRACCIA in GET_CAAC_EFFETTIVE.
                 MM     Cancellazione degli accessi solo se non prevista
                        archiviazione della traccia.
                 MM     Gestione dello stato dell'utente in funzione
                        CHECK_ACCESSO
                 MM     Funzioni per creazione file di archiviazione degli
                        accessi: (is_logfile,) archivia, ripristina,
                        elimina_archiviati e is_job_archiviazione_attivo.
 3    06/06/2007 MM     Modificata registra_accesso per gestione EVEN_SQ.
 4    12/12/2007 MM     A21996.0.0: modificate registra_accesso e validita_pwd.
 5    25/03/2011 SN     Creata procedure login_setup che esegue configurazioni al login
 6    25/05/2015 SN     Aggiunti parametri a login_setup
 7    24/09/2018 SN     Aggiunto parametro gg_prima_riutilizzo_pwd
******************************************************************************/
      FUNCTION  VERSIONE /* SLAVE_COPY */
      RETURN VARCHAR2;
      PROCEDURE CHECK_ACCESSO /* SLAVE_COPY */
      ( p_progetto VARCHAR2
      , p_istanza VARCHAR2
      , p_modulo VARCHAR2
      , p_utente VARCHAR2);
      PROCEDURE GET_CARATTERISTICHE_ACCESSO /* SLAVE_COPY */
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
      , p_val_pwd IN OUT NUMBER);
      PROCEDURE GET_CARATTERISTICHE_ACCESSO /* SLAVE_COPY */
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
      , p_single_sign_on IN OUT VARCHAR2);
      PROCEDURE GET_CARATTERISTICHE_ACCESSO /* SLAVE_COPY */
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
      , p_mod_pwd_primo_accesso IN OUT VARCHAR2);
      PROCEDURE GET_CARATTERISTICHE_ACCESSO /* SLAVE_COPY */
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
      , p_giorni_prima_riutilizzo_pwd IN OUT   NUMBER);
      PROCEDURE GET_CAAC_EFFETTIVE /* SLAVE_COPY */
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
      , p_val_pwd IN OUT NUMBER);
      PROCEDURE GET_CAAC_EFFETTIVE /* SLAVE_COPY */
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
      , p_single_sign_on IN OUT VARCHAR2);
      PROCEDURE GET_CAAC_EFFETTIVE /* SLAVE_COPY */
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
      , p_mod_pwd_primo_accesso IN OUT VARCHAR2);
      PROCEDURE GET_CAAC_EFFETTIVE /* SLAVE_COPY */
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
      , p_giorni_prima_riutilizzo_pwd IN OUT   NUMBER);
      FUNCTION IS_LDAPUSER /* SLAVE_COPY */
      ( p_nominativo VARCHAR2 ) RETURN NUMBER;
      FUNCTION GET_MINPWDLENGTH /* SLAVE_COPY */
      ( p_utente VARCHAR2 ) RETURN NUMBER;
      FUNCTION IS_PWD_DA_MOD_PRIMO_USO /* SLAVE_COPY */
      ( p_utente VARCHAR2 ) RETURN NUMBER;
      PROCEDURE VALIDITA_PWD /* SLAVE_COPY */
      ( p_utente VARCHAR2
      , p_tentativi_pwd INTEGER
      , p_val_pwd INTEGER);
      PROCEDURE CONTROLLO_ACCESSO /* SLAVE_COPY */
      ( p_tipo_accesso VARCHAR2
     , p_progetto VARCHAR2
     , p_istanza VARCHAR2
     , p_modulo VARCHAR2
     , p_utente VARCHAR2
     , p_accesso VARCHAR2
     , p_super_user VARCHAR2);
      PROCEDURE REGISTRA_ACCESSO ( p_session_id NUMBER
                                 , p_log VARCHAR2
                                 , p_istanza VARCHAR2
                                 , p_modulo VARCHAR2
                                 , p_utente VARCHAR2
                                 , p_note VARCHAR2
                                 , p_id_credenziale NUMBER DEFAULT NULL);
      FUNCTION REGISTRA_ACCESSO ( p_session_id NUMBER
                                , p_log VARCHAR2
                                , p_istanza VARCHAR2
                                , p_modulo VARCHAR2
                                , p_utente VARCHAR2
                                , p_note VARCHAR2
                                , p_id_credenziale NUMBER DEFAULT NULL)
      RETURN NUMBER;
      PROCEDURE ARCHIVIA;
      FUNCTION ripristina ( p_id_evento IN NUMBER) RETURN NUMBER;
      FUNCTION elimina_archiviati RETURN NUMBER;
      FUNCTION is_job_archiviazione_attivo RETURN NUMBER;
      PROCEDURE ELIMINA_TRACCIA;
      PROCEDURE ATTIVA_ELIMINAZIONE_TRACCIA   ( p_chiave_new in varchar2
                                              , p_stringa_new in varchar2
                                              , p_valore_new in varchar2
                                              , p_chiave_old in varchar2 default null
                                              , p_stringa_old in varchar2 default null
                                              , p_valore_old in varchar2 default null);
      PROCEDURE LOGIN_SETUP( p_nominativo utenti.nominativo%TYPE
                          , p_server varchar2 default null
                          , p_forzato varchar2 default 'N');
END Accesso;
/
