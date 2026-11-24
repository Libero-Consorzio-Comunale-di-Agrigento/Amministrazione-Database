--liquibase formatted sql

--changeset mturra:201901301231_108 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY afc_bfile
IS
/******************************************************************************
 Funzioni per la gestione dei Binary File del DataBase.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  000 05/12/2005 MF     Prima emissione.
  001 04/07/2007 MM     Modifica a create_directory per togliere dipendenza da si4_xml; inserimento funzione ClobtoBfile.
  002 23/10/2009 MM     Modifica a funzione ClobToFile per compatibilita' con oracle 8.
  003 25/02/2010 VA     ClobToBfile: corretta generazione del locator, passandogli il parametro p_alias_directory invece di d_dir_path.
  004 14/07/2011 FT     Allineati i commenti col nuovo standard di plsqldoc.
******************************************************************************/
   -- Package body revision value
   s_revisione_body   CONSTANT VARCHAR2 (30) := '004';
   /******************************************************************************
    Restituisce versione e revisione di distribuzione del package.
   ******************************************************************************/
   FUNCTION versione
      RETURN VARCHAR2
   IS
   BEGIN
      RETURN s_revisione || '.' || NVL (s_revisione_body, '000');
   END versione;
   /******************************************************************************
    Ottiene il nome del Path di File System sulla base del DirName indicato.
    REVISIONI.
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    000  05/12/2005 MF     Prima emissione.
   ******************************************************************************/
   FUNCTION get_dirpath (p_dirname IN VARCHAR2)
      RETURN VARCHAR2
   IS
      d_alias   VARCHAR2 (2000);
      d_path    VARCHAR2 (4000);
   BEGIN
      IF INSTR (p_dirname, '/') = 0 AND INSTR (p_dirname, '\') = 0
      THEN
         d_alias := p_dirname;
      ELSE
         d_alias := get_dirname (p_dirname);
      END IF;
      -- ottiene il percorso fisico dal nome interno della directory
      SELECT directory_path
        INTO d_path
        FROM all_directories
       WHERE directory_name = d_alias;
      RETURN d_path;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN '';
   END get_dirpath;
   /******************************************************************************
    Ottiene il nome logico della directory sulla base del Path di File System.
    REVISIONI.
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    000  05/12/2005 MF     Prima emissione.
   ******************************************************************************/
   FUNCTION get_dirname (p_dirpath IN VARCHAR2, p_prefix IN VARCHAR2
            DEFAULT '')
      RETURN VARCHAR2
   IS
      d_name   VARCHAR2 (4000);
   BEGIN
      IF INSTR (p_dirpath, '/') = 0 AND INSTR (p_dirpath, '\') = 0
      THEN
         -- conytrollo esistenza nome interno della Directory
         SELECT directory_name
           INTO d_name
           FROM all_directories
          WHERE directory_name = p_dirpath;
      ELSE
         -- ottiene il nome interno del percorso fisico
         SELECT MIN (directory_name)
           INTO d_name
           FROM all_directories
          WHERE directory_path = p_dirpath
            AND directory_name LIKE p_prefix || '%';
      END IF;
      RETURN d_name;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         RETURN '';
   END get_dirname;
   /******************************************************************************
    Elimina l'alias di directory passato.
    REVISIONI.
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
     000  05/12/2005 MM     Prima emissione.
   ******************************************************************************/
   PROCEDURE drop_directory
   (p_alias_dir VARCHAR2)
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
      tmpvar   NUMBER := 0;
   BEGIN
      BEGIN
         afc.sql_execute ('drop directory ' || p_alias_dir);
      EXCEPTION
         WHEN OTHERS
         THEN
            NULL;
      END;
   END drop_directory;
   /******************************************************************************
    Crea un alias di directory per il percorso passato.
    REVISIONI.
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
     000 05/12/2005 MM     Prima emissione.
     001 04/07/2007 MM     Modifica a create_directory per togliere dipendenza da si4_xml.
   ******************************************************************************/
   FUNCTION create_directory
   (
      p_alias_dir   VARCHAR2,
      p_path_dir    VARCHAR2,
      p_replace     NUMBER DEFAULT 1,
      p_test        NUMBER DEFAULT 1
   )
      RETURN NUMBER
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
      tmpvar   NUMBER           := 0;
      d_exec   VARCHAR2 (32000);
   BEGIN
      IF p_path_dir IS NOT NULL
      THEN
         BEGIN
            d_exec := 'create ';
            IF p_replace = 1
            THEN
               d_exec := d_exec || 'or replace ';
            END IF;
            d_exec :=
                  d_exec
               || 'directory '
               || p_alias_dir
               || ' as '''
               || p_path_dir
               || '''';
            afc.sql_execute (d_exec);
         EXCEPTION
            WHEN OTHERS
            THEN
               tmpvar := -1;
         END;
      END IF;
      IF tmpvar = 0 AND p_test = 1
      THEN
         -- test su directory
         DECLARE
            d_file      BFILE;
            d_clob      CLOB;
            d_stringa   VARCHAR2 (100) := 'Questo e'' un file di test.';
         BEGIN
            d_file := BFILENAME (p_alias_dir, 'test.txt');
            DBMS_LOB.createtemporary (d_clob, FALSE, DBMS_LOB.CALL);
            DBMS_LOB.WRITE (d_clob, LENGTH (d_stringa), 1, d_stringa);
            d_file := clobtobfile (d_clob, p_alias_dir, 'test.txt');
         --d_file := Si4_Xml.append_select(d_file, 'select 1 dummy from dual');
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
               drop_directory (p_alias_dir);
               RETURN -2;
         END;
         BEGIN
            DBMS_BACKUP_RESTORE.deletefile (p_path_dir || '/test.xml');
         EXCEPTION
            WHEN OTHERS
            THEN
               RETURN -3;
         END;
      END IF;
      RETURN tmpvar;
   END create_directory;
   /******************************************************************************
    Ottiene un alias di directory per il percorso passato.
    REVISIONI.
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
     000  05/12/2005 MM     Prima emissione.
   ******************************************************************************/
   FUNCTION next_directory
   (p_prefix VARCHAR2, p_path VARCHAR2)
      RETURN VARCHAR2
   IS
      d_return   VARCHAR2 (30);
      d_tmpvar   VARCHAR2 (30);
   BEGIN
      IF p_prefix IS NOT NULL
      THEN
         d_return := get_dirname (p_path, p_prefix || '_');
         IF d_return IS NULL
         THEN
            SELECT    p_prefix
                   || '_'
                   || TO_CHAR
                             (  NVL
                                   (MAX (TO_NUMBER (SUBSTR (directory_name,
                                                              LENGTH (p_prefix)
                                                            + 2
                                                           )
                                                   )
                                        ),
                                    0
                                   )
                              + 1
                             )
              INTO d_return
              FROM all_directories
             WHERE directory_name LIKE p_prefix || '_%'
               AND afc.is_number (SUBSTR (directory_name,
                                          LENGTH (p_prefix) + 2)
                                 ) = 1;
         END IF;
      END IF;
      RETURN d_return;
   END next_directory;
   /******************************************************************************
    Scrive in un file sul file system del db il contenuto di un clob.
    REVISIONI.
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
     001 04/07/2007 MM     Prima emissione.
     002 23/10/2009 MM
     003 25/02/2010 VA     Corretto generazione del locator, passandogli il parametro p_alias_directory invece di d_dir_path.
   ******************************************************************************/
   FUNCTION clobtobfile
   (
      p_clob              CLOB,
      p_alias_directory   VARCHAR2,
      p_filename          VARCHAR2
   )
      RETURN BFILE
   IS
      source_pic             BFILE;
      buffer                 VARCHAR2 (32767);
      buffer_size   CONSTANT BINARY_INTEGER     := 32767;
      amount                 BINARY_INTEGER;
      offset                 NUMBER (38);
      file_handle            UTL_FILE.file_type;
      dest_pic               BFILE;
      d_dir_path             VARCHAR2 (1000);
      FUNCTION openfile (p_where IN VARCHAR2)
         RETURN UTL_FILE.file_type
      IS
      BEGIN
         -- ---------------------------------
         -- Apre il file in write mode
         -- ---------------------------------
         file_handle :=
            UTL_FILE.fopen (LOCATION          => p_where,
                            filename          => p_filename,
                            open_mode         => 'w',
                            max_linesize      => buffer_size
                           );
         RETURN file_handle;
      EXCEPTION
         WHEN UTL_FILE.invalid_mode
         THEN
            raise_application_error (-20999, 'Invalid Mode Parameter');
         WHEN UTL_FILE.invalid_path
         THEN
            raise_application_error (-20999, 'Invalid File Location');
         WHEN UTL_FILE.invalid_filehandle
         THEN
            raise_application_error (-20999, 'Invalid Filehandle');
         WHEN UTL_FILE.invalid_operation
         THEN
            raise_application_error (-20999, 'Invalid Operation');
         WHEN UTL_FILE.read_error
         THEN
            raise_application_error (-20999, 'Read Error');
         WHEN UTL_FILE.internal_error
         THEN
            raise_application_error (-20999, 'Internal Error');
         WHEN UTL_FILE.invalid_maxlinesize
         THEN
            raise_application_error (-20999, 'Line Size Exceeds 32K');
         WHEN OTHERS
         THEN
            raise_application_error (-20999, 'Unknown UTL_FILE Error');
      END;
   BEGIN
      d_dir_path := get_dirpath (p_alias_directory);
      BEGIN
         file_handle := openfile (p_alias_directory);
      EXCEPTION
         WHEN OTHERS
         THEN
            BEGIN
               file_handle := openfile (d_dir_path);
            EXCEPTION
               WHEN OTHERS
               THEN
                  RAISE;
            END;
      END;
      amount := buffer_size;
      offset := 1;
-- ---------------------------------------------------
--         Legge dal clob e scrive su disco
-- ---------------------------------------------------
      WHILE amount >= buffer_size
      LOOP
         DBMS_LOB.READ (lob_loc      => p_clob,
                        amount       => amount,
                        offset       => offset,
                        buffer       => buffer
                       );
         DBMS_OUTPUT.put_line ('DBMS_LOB.READ ');
         offset := offset + amount;
         UTL_FILE.put_line (FILE => file_handle, buffer => buffer);
         DBMS_OUTPUT.put_line ('UTL_FILE.PUT_LINE ');
      END LOOP;
      UTL_FILE.fclose (FILE => file_handle);
      RETURN BFILENAME (p_alias_directory, p_filename);
   EXCEPTION
      WHEN OTHERS
      THEN
         IF UTL_FILE.is_open (FILE => file_handle)
         THEN
            UTL_FILE.fclose (FILE => file_handle);
         END IF;
         RAISE;
   END clobtobfile;
END afc_bfile;
/
