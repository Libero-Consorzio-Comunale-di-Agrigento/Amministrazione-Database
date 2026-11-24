--liquibase formatted sql

--changeset snegroni:201905021235 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW UTENTI_VERIFICHE_VIEW
(
    UTENTE,
    NOMINATIVO,
    IS_UTENZA_TECNICA_ADS,
    SOGGETTO_NUMERO,
    SOGGETTO_NOME,
    SOGGETTO_CODICE_FISCALE,
    IS_IN_STRUTTURA_ORGANIZZATIVA,
    IS_UTENZA_LDAP,
    ULTIMO_TENTATIVO,
    "DIRITTI: MODULO(ISTANZA)",
    "NUOVE INFO ->",
    "DISABILITARE (SI/NO=VUOTO)",
    "NOMINATIVO LDAP(CAMBIARE)",
    NUOVO_SOGGETTO_ABBINARE,
    NUOVO_COGNOME,
    NUOVO_NOME,
    NUOVO_CODICE_FISCALE
)
AS
    SELECT u.utente,
           u.nominativo,
           UTENTI_VERIFICHE_SISTEMAZIONI.get_is_utenza_ads (u.utente)
               is_utenza_tecnica_ads,
           s.soggetto
               soggetto_numero,
           s.nome
               soggetto_nome,
           s.codice_fiscale
               soggetto_codice_fiscale,
           UTENTI_VERIFICHE_SISTEMAZIONI.get_is_so4_componente (s.soggetto)
               is_in_struttura_organizzativa,
           UTENTI_VERIFICHE_SISTEMAZIONI.GET_IS_UTENZA_LDAP (u.utente)
               IS_UTENZA_LDAP,
           ad4_utente.get_ultiMo_tentativo (u.utente)
               ultimo_tentativo,
           UTENTI_VERIFICHE_SISTEMAZIONI.get_elenco_diac (u.utente, '%')
               "DIRITTI: MODULO(ISTANZA)",
           ''
               "NUOVE INFO ->",
           ''
               "DISABILITARE (SI/NO=VUOTO)",
           ''
               "NOMINATIVO LDAP(CAMBIARE)",
           ''
               nuovo_soggetto_abbinare,
           ''
               nuovo_cognome,
           ''
               nuovo_nome,
           ''
               nuovo_codice_fiscale
      FROM utenti u, utenti_soggetti us, soggetti s
     WHERE     u.utente = us.utente(+)
           AND us.soggetto = s.soggetto(+)
           AND u.stato = 'U'
           AND tipo_utente = 'U';
