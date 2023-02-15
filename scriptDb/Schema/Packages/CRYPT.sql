CREATE OR REPLACE PACKAGE Crypt IS
/******************************************************************************
 NOME:        CRYPT.
 DESCRIZIONE: Package per gestione Crittografia Password.
 ANNOTAZIONI: Il package viene distribuito con il body criptato.
              La specification viene salvata nella directory ins di AD4 nel
              file crypt.pks.
              Il body cryptato viene salvato nella directory ins di AD4 nel
              file crypt.plb.
              Il body non cryptato viene salvato nella directory:
                 Ins\Ver\File da NON DISTRIBUIRE
              di AD4 nel file crypt.pkb.
 ECCEZIONI:   -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    20/02/2003 MM     Creazione commento.
 1    02/01/2006 MM     Gestione caratteri minuscoli in CRYPT_PASSWORD.
 02   19/07/2007 MM     1. Adeguamento versione ai nuovi standard.
                        2. Creazione IS_PASSWORD_VALIDA,
                           IS_PASSWORD_UTENTE_VALIDA.
 03 15/11/2017  SN     Introduzione crypt con md5
 04 27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
                       (modificato quanto introdotto precedentemente con indicazione md5)
 05 31/03/2020  SN    Prevedere il default per crypt_password. Feature #40748
******************************************************************************/
   -- Revisione del Package
   s_revisione CONSTANT Afc.t_revision := 'V1.05';

   -- Versione e revisione
   FUNCTION  VERSIONE RETURN VARCHAR2;

   /***************************************************************************
    NOME:        CRYPT_PASSWORD.
    DESCRIZIONE: Data una stringa in input, restituisce nello stesso parametro
                 la stringa criptata.
    ARGOMENTI:   p_password IN OUT varchar2: stringa da criptare.
                 p_algorithm_name IN varchar2: indica algoritmo usato x criptare
   ***************************************************************************/
   PROCEDURE CRYPT_PASSWORD    ( p_password IN OUT VARCHAR2
                               , p_algorithm_name IN varchar2 default 'STANDARD');
   /***************************************************************************
    NOME:        VERIFICA_PASSWORD.
    DESCRIZIONE: Dati un nominativo utente e la password, verifica che la
                 password passata corrisponda effettivamente a quella dell'
                 utente.
    PARAMETRI:   p_nominativo IN varchar2: nominativo utente.
                 p_password   IN varchar2: password utente NON criptata.
    RITORNA:     number: 1 = verifica effettuata con successo.
                         0 = verifica fallita.
    ECCEZIONI:   -20999: Utente '<p_nominativo>' non definito.
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 05 31/03/2020  SN    Prevedere il default per crypt_password. Feature #40748
   ***************************************************************************/
   FUNCTION  VERIFICA_PASSWORD ( p_nominativo IN VARCHAR2
                               , p_password   IN VARCHAR2) RETURN NUMBER;
   /***************************************************************************
    NOME:        GENERA_PASSWORD.
    DESCRIZIONE: Viene generata una password di lunghezza p_len.
    PARAMETRI:   p_len:          lunghezza della password da generare.
                 p_car_speciali: indica se la password generata puo' contenere
                                 caratteri speciali.
                                 Valori possibili: 1 oppure 0.
                 p_num_obbl:     indica se deve essere presente almeno un numero
                                 nella password generata.
                                 Valori possibili: 1 oppure 0.
    RITORNA:     varchar2: password generata.
    ECCEZIONI:   -
    ANNOTAZIONI: Caratteri speciali possibili:
                 [ \ ] ^ _ | ! @ # $ % & ? ( ) * + , - . / : ; < = >
   ***************************************************************************/
   FUNCTION  GENERA_PASSWORD   ( p_len          IN NUMBER
                               , p_car_speciali IN NUMBER DEFAULT 0
                               , p_num_obbl     IN NUMBER DEFAULT 0) RETURN VARCHAR2;
/******************************************************************************
 NOME:        IS_PASSWORD_VALIDA.
 DESCRIZIONE: Data una password verifica che soddisfi le caratteristiche
              precisate:
              - che sia maggiore della lunghezza minima data (p_min_length)
              - se p_is_num_obb = 1, che debba essere presente almeno un numero
              - se p_is_car_spec = 0, che non contenga dei caratteri speciali.
 PARAMETRI:   p_password      IN varchar2 password da verificare
              p_min_length    IN number   lunghezza minima della password
              p_is_num_obb    IN number   indica se deve essere presente almeno
                                          un numero.
              p_is_car_spec   IN number   indica se puo' contenere dei caratteri
                                          speciali.
 RITORNA:     number:  1   = Verifica effettuata con successo.
                       0   = La password non puo' essere nulla.
                      -1   = La password deve essere di almeno 'p_min_length' caratteri.
                      -2   = La password non puo' contenere caratteri speciali.
                      -3   = La password deve contenere almeno un numero.
******************************************************************************/
   function IS_PASSWORD_VALIDA   ( p_password IN VARCHAR2
                                 , p_min_length IN NUMBER
                                 , p_is_num_obb IN NUMBER
                                 , p_is_car_spec IN NUMBER) return number;
   /******************************************************************************
    NOME:        IS_PASSWORD_UTENTE_VALIDA.
    DESCRIZIONE: Dato un codice utente verifica che la sua password soddisfi le
                 caratteristiche precisate:
                 - che sia maggiore della lunghezza minima data (p_min_length)
                 - se p_is_num_obb = 1, che debba essere presente almeno un numero
                 - se p_is_car_spec = 0, che non contenga dei caratteri speciali.
    PARAMETRI:   p_utente        IN varchar2 codice utente di cui verificare la
                                             password
                 p_min_length    IN number   lunghezza minima della password
                 p_is_num_obb    IN number   indica se deve essere presente almeno
                                             un numero.
                 p_is_car_spec   IN number   indica se puo' contenere dei caratteri
                                             speciali.
    RITORNA:     number:  ritorno della funzione IS_PASSWORD_VALIDA;
                          cioe':
                          1   = Verifica effettuata con successo.
                          0   = La password non puo' essere nulla.
                         -1   = La password deve essere di almeno 'p_min_length' caratteri.
                         -2   = La password non puo' contenere caratteri speciali.
                         -3   = La password deve contenere almeno un numero.
    ECCEZIONI:   -20999: Utente '<p_utente>' non definito.
   ******************************************************************************/
   function IS_PASSWORD_UTENTE_VALIDA  ( p_utente        IN   VARCHAR2
                                       , p_min_length    IN   NUMBER
                                       , p_is_num_obb    IN   NUMBER
                                       , p_is_car_spec   IN   NUMBER) RETURN NUMBER;
   FUNCTION crypt (p_str IN VARCHAR2, p_key IN RAW)
      RETURN RAW;

   FUNCTION decrypt (p_data IN RAW, p_key IN RAW)
      RETURN VARCHAR2;
END;
/

