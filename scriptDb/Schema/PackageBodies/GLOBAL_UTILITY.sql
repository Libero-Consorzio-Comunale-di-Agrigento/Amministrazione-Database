CREATE OR REPLACE PACKAGE BODY global_utility
AS

/******************************************************************************
 NOME:        global_utility
 DESCRIZIONE: Contiene oggetti di utilita generale.
 REVISIONI:
 Rev. Data        Autore  Descrizione
 ---- ----------  ------  ----------------------------------------------------
 1    04/02/2011  SN     Prima emissione
 2    30/04/2013  SN     Aggiunta inizializza sessione
 3    22/05/2019  sne    Aggiunte get e set registro_amministratore
 4    03/01/2020  sne    Aggiunte get e set registro_autenticazione_md5 Bug #31005
 5    27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
 6    31/03/2020  SN    Non si può usare una variabile locale per verificare valori che possono cambiare
                        durante l'attività altrimenti se cambio il valore non viene correttamente letto. Feature #40748
******************************************************************************/

/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
 Rev. Data        Autore  Descrizione
 ---- ----------  ------  ----------------------------------------------------
 1    04/02/2011  SNeg      Prima emissione
******************************************************************************/

v_registro_amministratore registro.valore%TYPE:= null;
v_registro_crypt_algorithm registro.valore%TYPE:= null;

FUNCTION VERSIONE  RETURN VARCHAR2 IS
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
 Rev. Data        Autore  Descrizione
 ---- ----------  ------  ----------------------------------------------------
 1    04/02/2011  SNeg      Prima emissione
******************************************************************************/
BEGIN
   RETURN 'V4.0 del 03/01/2020';
END VERSIONE;
PROCEDURE Compile_DB
/******************************************************************************
 NOME:        Compile_DB
 DESCRIZIONE: Compilazione di tutti gli oggetti invalidi presenti nel DB con una funzione standard di sys.
 ARGOMENTI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data        Autore  Descrizione
 ---- ----------  ------  ----------------------------------------------------
 1    04/02/2011  SNeg      Prima emissione
 2   30/04/2013   SNeg      Aggiunta compile_db
 3   22/05/2019   snegroni  Aggiunte get e set registro_amministratore
******************************************************************************/
 (p_show_err number default 1)
IS
BEGIN
sys.utl_recomp.recomp_serial;
if p_show_err = 1 then
    DECLARE
       d_invalid_obj   INTEGER := 0;
    BEGIN
       SELECT COUNT ( * )
       INTO d_invalid_obj
       FROM obj
       WHERE status = 'INVALID';
       IF d_invalid_obj > 0
       THEN
          raise_application_error (-20999
                                 , 'Esistono ' || d_invalid_obj || ' oggetti non validabili nel DB'
          );
       END IF;
    END;
end iF;
END Compile_DB;
/******************************************************************************
 NOME:        inizializza_sessione
 DESCRIZIONE: Usato da Tomcat x azzerare la sessione
 ARGOMENTI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data        Autore  Descrizione
 ---- ----------  ------  ----------------------------------------------------
 2   30/04/2013   SNeg      Prima emissione
******************************************************************************/
FUNCTION inizializza_sessione
   RETURN NUMBER
IS
BEGIN
   DBMS_SESSION.modify_package_state (DBMS_SESSION.reinitialize);
   RETURN 0;
END inizializza_sessione;



   PROCEDURE set_registro_amministratore
/******************************************************************************
 DESCRIZIONE: Valorizza una variabile locale in base al valore contenuto nel registro
              'PRODUCTS/AD4/UTENTE', 'Accesso ModuloAMM solo se UtenteAMM',
 ARGOMENTI:
 ECCEZIONI:   -
 Rev. Data        Autore  Descrizione
 ---- ----------  ------  ----------------------------------------------------
 3   22/05/2019   snegroni  Aggiunte get e set registro_amministratore
******************************************************************************/
IS
BEGIN
  v_registro_amministratore := NVL
                  (LOWER
                      (registro_utility.leggi_stringa
                                                     ('PRODUCTS/AD4/UTENTE',
                                                      'Accesso ModuloAMM solo se UtenteAMM',
                                                      0
                                                     )
                      ),
                   'no'
                  );
END;

 FUNCTION get_registro_amministratore RETURN varchar2
/******************************************************************************
 DESCRIZIONE: Restituisce la variabile locale valorizzata in base al registro
              'PRODUCTS/AD4/UTENTE', 'Accesso ModuloAMM solo se UtenteAMM',
 ARGOMENTI:
 ECCEZIONI:   -
 Rev. Data        Autore  Descrizione
 ---- ----------  ------  ----------------------------------------------------
 3   22/05/2019   snegroni  Aggiunte get e set registro_amministratore
 6    31/03/2020  SN    Non si può usare una variabile locale per verificare valori che possono cambiare
                        durante l'attività altrimenti se cambio il valore non viene correttamente letto. Feature #40748
******************************************************************************/
IS
BEGIN
    return NVL
                  (LOWER
                      (registro_utility.leggi_stringa
                                                     ('PRODUCTS/AD4/UTENTE',
                                                      'Accesso ModuloAMM solo se UtenteAMM',
                                                      0
                                                     )
                      ),
                   'no'
                  );
END;


PROCEDURE set_registro_crypt_algorithm
/******************************************************************************
 DESCRIZIONE: Valorizza una variabile locale in base al valore contenuto nel registro
              'PRODUCTS/AUTHENTICATION', 'CryptAlgorithm',
 ARGOMENTI:
 ECCEZIONI:   -
 Rev. Data        Autore  Descrizione
 ---- ----------  ------  ----------------------------------------------------
 4    03/01/2020  sne    Aggiunte get e set registro_autenticazione_md5 Bug #31005
 5    27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
******************************************************************************/
IS
BEGIN
  v_registro_crypt_algorithm := NVL
                  (upper
                      (registro_utility.leggi_stringa
                                                     ('PRODUCTS/AUTHENTICATION',
                                                      'CryptAlgorithm',
                                                      0
                                                     )
                      ),
                   'STANDARD'
                  );

END;

FUNCTION get_registro_crypt_algorithm RETURN varchar2
/******************************************************************************
 DESCRIZIONE: Restituisce la variabile locale valorizzata in base al registro
              'PRODUCTS/AUTHENTICATION', 'CryptAlgorithm',
 ARGOMENTI:
 ECCEZIONI:   -
 Rev. Data        Autore  Descrizione
 ---- ----------  ------  ----------------------------------------------------
 4    03/01/2020  sne    Aggiunte get e set registro_autenticazione_md5 Bug #31005
 5    27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
 6    31/03/2020  SN    Non si può usare una variabile locale per verificare valori che possono cambiare
                        durante l'attività altrimenti se cambio il valore non viene correttamente letto. Feature #40748
******************************************************************************/
IS
BEGIN
--    return v_registro_crypt_algorithm;
    return NVL
                  (upper
                      (registro_utility.leggi_stringa
                                                     ('PRODUCTS/AUTHENTICATION',
                                                      'CryptAlgorithm',
                                                      0
                                                     )
                      ),
                   'STANDARD'
                  );
END;

FUNCTION get_crypt_algorithm RETURN VARCHAR2
/******************************************************************************
 DESCRIZIONE: Decide l'algoritmo da utilizzare
             Verifica cosa è scritto nel registro 'PRODUCTS/AUTHENTICATION', 'CryptAlgorithm'
             Se non presente o nullo => 'STANDARD'
             Se "latest" usa quello disponibile x versione Oracle
             Altrimenti usa il valore indicato nel registro
 ARGOMENTI:
 ECCEZIONI:   -
******************************************************************************/
IS
    d_algorithm_by_version utenti_salt.algoritmo%type := get_crypt_algorithm_by_version;
    d_algorithm_registro utenti_salt.algoritmo%type := get_registro_crypt_algorithm;
    d_algorithm_name utenti_salt.algoritmo%type := get_registro_crypt_algorithm;
begin
    if d_algorithm_registro ='STANDARD' then
       d_algorithm_name := 'STANDARD';
    elsif d_algorithm_registro ='LATEST' then -- lo estrae minuscolo
       d_algorithm_name := get_crypt_algorithm_by_version;
    end if;
    return d_algorithm_name;
END get_crypt_algorithm;

FUNCTION get_crypt_algorithm_by_version RETURN VARCHAR2
/******************************************************************************
 DESCRIZIONE: utilizza l'algoritmo predefinito in base alla versione di oracle
 ARGOMENTI:
 ECCEZIONI:   -
******************************************************************************/
IS
    d_algorithm_name utenti_salt.algoritmo%type;
begin
    CASE
    WHEN dbms_db_version.version < 10 THEN RAISE_APPLICATION_ERROR (-20999,'Versione DBMS non prevista.');
    WHEN dbms_db_version.version = 10 THEN d_algorithm_name:= 'MD5';
    WHEN dbms_db_version.version = 11 THEN d_algorithm_name:= 'SH1';
    WHEN dbms_db_version.version >= 12 THEN d_algorithm_name:= 'SH512';
    end case;

    return d_algorithm_name;
END get_crypt_algorithm_by_version;
-----------------------------------------------------------------------------------------------------------------------------

BEGIN
set_registro_amministratore;
set_registro_crypt_algorithm;
END global_utility;
/

