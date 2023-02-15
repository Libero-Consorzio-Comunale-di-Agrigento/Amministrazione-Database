CREATE OR REPLACE TRIGGER REGISTRO_TB
/******************************************************************************
 NOME:        REGISTRO_TB
 DESCRIZIONE:
 ECCEZIONI:
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    1 20/12/2019 SN     Ricalcolo variabile con impostazioni registro
                        'PRODUCTS/AD4/UTENTE', 'Accesso ModuloAMM solo se UtenteAMM'
    2 03/01/2020  sne    Calcolo registro_autentic_md5 Bug #31005
    3 27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
                       (modificato quanto introdotto precedentemente con indicazione md5)
******************************************************************************/
BEFORE INSERT
    OR UPDATE
    OR DELETE
ON REGISTRO
BEGIN
   -- RESET PostEvent for Custom Functional Check
   IF Integritypackage.GetNestLevel = 0 THEN
      Integritypackage.InitNestLevel;
   END IF;

   global_utility.set_registro_amministratore;
   global_utility.set_registro_crypt_algorithm;
END;
/


