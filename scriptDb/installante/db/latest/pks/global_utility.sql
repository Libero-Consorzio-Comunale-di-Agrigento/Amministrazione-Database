--liquibase formatted sql

--changeset mturra:201901301231_236 runOnChange:true stripComments:false endDelimiter:/

CREATE OR REPLACE PACKAGE global_utility
/******************************************************************************
 NOME:        global_utility
 DESCRIZIONE: Contiene oggetti di utilita generale.
 REVISIONI:
 Rev. Data        Autore  Descrizione
 ---- ----------  ------  ----------------------------------------------------
 1    04/02/2011  SN     Prima emissione
 2    30/04/2013  SN     Aggiunta inizializza sessione
 3    22/05/2019  sne    Aggiunte get e set registro_amministratore
 4    03/01/2020  sne    Aggiunte get e set registro_autentic_md5 Bug #31005
 5    27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
******************************************************************************/
AS
   FUNCTION  VERSIONE         RETURN VARCHAR2;
   PROCEDURE Compile_DB (p_show_err number default 1);
/******************************************************************************
 DESCRIZIONE: Compila tutti gli oggetti invalidi del database
 ARGOMENTI:   p_show_err indica se fare segnalazione di errore in caso di esistenza di oggetti invalidi
                                        1= segnalare errore se esistono oggetti invalidi
                                        0 = NON segnalare errore
 ECCEZIONI:   -
******************************************************************************/
   FUNCTION inizializza_sessione RETURN number;
/******************************************************************************
 DESCRIZIONE: Inizializza la sessione pulendo le variabili di package ed
              evitando errori se nel frattempo qualche oggetto di db è diventato
             invalido
 ARGOMENTI:
 ECCEZIONI:   -
******************************************************************************/

   PROCEDURE set_registro_amministratore;
/******************************************************************************
 DESCRIZIONE: Valorizza una variabile locale in base al valore contenuto nel registro
              'PRODUCTS/AD4/UTENTE', 'Accesso ModuloAMM solo se UtenteAMM',
 ARGOMENTI:
 ECCEZIONI:   -
******************************************************************************/
 FUNCTION get_registro_amministratore RETURN varchar2;
/******************************************************************************
 DESCRIZIONE: Restituisce la variabile locale valorizzata in base al registro
              'PRODUCTS/AD4/UTENTE', 'Accesso ModuloAMM solo se UtenteAMM',
 ARGOMENTI:
 ECCEZIONI:   -
******************************************************************************/

   PROCEDURE set_registro_crypt_algorithm;
/******************************************************************************
 DESCRIZIONE: Valorizza una variabile locale in base al valore contenuto nel registro
              'PRODUCTS/AUTHENTICATION', 'CryptAlgorithm',
 ARGOMENTI:
 ECCEZIONI:   -
******************************************************************************/
 FUNCTION get_registro_crypt_algorithm RETURN varchar2;
/******************************************************************************
 DESCRIZIONE: Restituisce la variabile locale valorizzata in base al registro
              'PRODUCTS/AUTHENTICATION', 'CryptAlgorithm',
 ARGOMENTI:
 ECCEZIONI:   -
******************************************************************************/

FUNCTION get_crypt_algorithm_by_version RETURN VARCHAR2;
/******************************************************************************
 DESCRIZIONE: utilizza l'algoritmo predefinito in base alla versione di oracle
 ARGOMENTI:
 ECCEZIONI:   -
******************************************************************************/

FUNCTION get_crypt_algorithm RETURN VARCHAR2;
/******************************************************************************
 DESCRIZIONE: Decide l'algoritmo da utilizzare
             Verifica cosa è scritto nel registro 'PRODUCTS/AUTHENTICATION', 'CryptAlgorithm'
             Se non presente o nullo => 'STANDARD'
             Se "latest" usa quello disponibile x versione Oracle
             Altrimenti può essere valore fisso
 ARGOMENTI:
 ECCEZIONI:   -
******************************************************************************/


END global_utility;
/
