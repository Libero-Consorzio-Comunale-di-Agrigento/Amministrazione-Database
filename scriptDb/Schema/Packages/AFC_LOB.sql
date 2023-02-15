CREATE OR REPLACE PACKAGE afc_lob
IS
/******************************************************************************
 Funzioni per la gestione degli oggetti di tipo LOB.
 REVISIONI.
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 00   30/08/2006 FT     Prima emissione.
 01   28/08/2007 FT     Aggiunte 2 versioni di to_blob (per varchar2 e clob), decode_value, versione di to_clob per blob e sql_execute.
 02   11/09/2007 MM     Aggiunta replace_clob.
 03   31/10/2007 FT     Aggiunto nvl_clob.
 04   19/06/2009 MM     Resa pubblica riempi_text_table ed inserimento di set_row_len, c_add, c_replace, c_substr, c_instr, encode_utf8.
 05   14/07/2011 FT     Allineati i commenti col nuovo standard di plsqldoc.
******************************************************************************/
   -- Package revision value
   s_revisione   CONSTANT afc.t_revision := 'V1.05';
   /******************************************************************************
    Restituisce versione e revisione di distribuzione del package.
    %return varchar2: contiene versione e revisione.
    %note <UL>
          <LI> Primo numero: versione compatibilita del Package.</LI>
          <LI> Secondo numero: revisione del Package specification.</LI>
          <LI> Terzo numero: revisione del Package body.</LI>
          </UL>
   ******************************************************************************/
   FUNCTION versione
      RETURN VARCHAR2;
   /******************************************************************************
    Trasforma stringa in CLOB.
    %param p_testo varchar2
    %param p_empty_clob boolean: Se FALSE ritorna NULL, se TRUE ritorna CALL empty_clob
    %return clob
   ******************************************************************************/
   FUNCTION TO_CLOB (
      p_testo        IN   VARCHAR2,
      p_empty_clob   IN   BOOLEAN DEFAULT FALSE
   )
      RETURN CLOB;
   /******************************************************************************
    Trasforma blob in CLOB - overloading per valori blob.
    %param p_testo blob
    %param p_empty_clob boolean: Se FALSE ritorna NULL, se TRUE ritorna CALL empty_clob
    %return clob
   ******************************************************************************/
   FUNCTION TO_CLOB (p_testo IN BLOB, p_empty_clob IN BOOLEAN DEFAULT FALSE)
      RETURN CLOB;
   /******************************************************************************
    Trasforma stringa in BLOB.
    %param p_testo varchar2
    %param p_empty_blob boolean: Se FALSE ritorna NULL, se TRUE ritorna CALL empty_blob
    %return varchar2
   ******************************************************************************/
   FUNCTION to_blob (
      p_testo        IN   VARCHAR2,
      p_empty_blob   IN   BOOLEAN DEFAULT FALSE
   )
      RETURN BLOB;
   /******************************************************************************
    Trasforma clob in BLOB - overloading per type clob
    %param p_testo clob
    %param p_empty_blob boolean: Se FALSE ritorna NULL, se TRUE ritorna CALL empty_blob
    %return blob
   ******************************************************************************/
   FUNCTION to_blob (p_testo IN CLOB, p_empty_blob IN BOOLEAN DEFAULT FALSE)
      RETURN BLOB;
   /******************************************************************************
    Istruzione "decode" per PL/SQL.
    %param p_check_value    valore da controllare
    %param p_against_value  valore di confronto
    %param p_then_result    risultato per uguale
    %param p_else_result    risultato per diverso
    %return varchar2
   ******************************************************************************/
   FUNCTION decode_value (
      p_check_value     IN   CLOB,
      p_against_value   IN   CLOB,
      p_then_result     IN   CLOB,
      p_else_result     IN   CLOB
   )
      RETURN CLOB;
   /******************************************************************************
    Esegue il clob dato.
    %param p_testo IN clob sorgente da eseguire.
   ******************************************************************************/
   PROCEDURE sql_execute (p_testo IN CLOB);
   /******************************************************************************
    Esegue la sostituzione di tutte le occorrenze di p_replaceStr con p_replaceWith nel clob dato.
    %param p_srcClob      IN CLOB      clob da esaminare
    %param p_replaceStr   IN VARCHAR2  stringa da sostituire
    %param p_replaceWith  IN VARCHAR2  stringa con cui sostituire
   ******************************************************************************/
   FUNCTION replace_clob (
      p_srcclob       IN   CLOB,
      p_replacestr    IN   VARCHAR2,
      p_replacewith   IN   VARCHAR2
   )
      RETURN CLOB;
   /******************************************************************************
    Esegue la funzione nvl di oracle su valore di tipo CLOB
    %param p_check_value  IN CLOB      clob da esaminare
    %param p_then_result  IN CLOB      clob da restituire nel caso in cui p_check_value sia EMPTY
   ******************************************************************************/
   FUNCTION nvl_clob (p_check_value IN CLOB, p_then_result IN CLOB)
      RETURN CLOB;
   /******************************************************************************
    Riempie la table p_text_table con il sorgente passato.
    %param p_text_table IN OUT  dbms_sql.varchar2s tabella da riempire.
    %param p_text       IN      clob               testo con cui riempirla.
    %note Eventuale ';' o '/' finale viene eliminato. Per riempire p_text_table vengono isolate le singole righe di sorgente (fino al carattere chr(10)); se la riga cosi' ottenuta ha lunghezza maggiore di 255 caratteri, viene spezzata in piu' righe di lunghezza massima 255.
   ******************************************************************************/
   PROCEDURE riempi_text_table (
      p_text_table   IN OUT   DBMS_SQL.varchar2s,
      p_testo        IN       CLOB
   );
   FUNCTION C_ADD (in_clob IN CLOB, in_text IN VARCHAR2)
      RETURN CLOB;
   FUNCTION C_REPLACE (
      in_clob      IN   CLOB,
      in_search    IN   VARCHAR2,
      in_replace   IN   VARCHAR2
   )
      RETURN CLOB;
   PROCEDURE set_row_len (p_len INTEGER);
   FUNCTION C_SUBSTR (
      in_clob     IN   CLOB,
      in_start    IN   NUMBER,
      in_amount   IN   NUMBER DEFAULT NULL
   )
      RETURN CLOB;
   FUNCTION C_INSTR (
      in_clob      IN   CLOB,
      in_pattern   IN   VARCHAR2,
      in_start     IN   NUMBER DEFAULT 1,
      in_nth       IN   NUMBER DEFAULT 1,
      in_reverse   IN   NUMBER DEFAULT 0
   )
      RETURN NUMBER;
   FUNCTION encode_utf8 (in_text IN CLOB)
      RETURN CLOB;
END afc_lob;
/

