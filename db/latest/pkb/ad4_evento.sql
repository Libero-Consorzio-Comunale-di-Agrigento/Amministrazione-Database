--liquibase formatted sql

--changeset mturra:201901301231_105 runOnChange:true stripComments:false

CREATE OR REPLACE PACKAGE BODY Ad4_Evento AS
   s_dir_num_file NUMBER;
   s_dir_tot_dim  NUMBER;
   FUNCTION VERSIONE
   /******************************************************************************
    NOME:        VERSIONE
    DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
    PARAMETRI:   --
    RITORNA:     stringa varchar2 contenente versione e data.
    NOTE:        Il secondo numero della versione corrisponde alla revisione
                 del package.
   ******************************************************************************/
   RETURN VARCHAR2
   IS
   BEGIN
      RETURN 'V1.3';
   END VERSIONE;
   FUNCTION GET_TESTO
   /******************************************************************************
    NOME:        GET_TESTO
    DESCRIZIONE: Restituisce il testo dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente il testo.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS
      d_return EVENTI.testo%TYPE;
   BEGIN
      SELECT testo
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_TESTO;
   FUNCTION GET_ANNOTAZIONI
   /******************************************************************************
    NOME:        GET_ANNOTAZIONI
    DESCRIZIONE: Restituisce le annotazioni dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente le annotazioni.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS
      d_return EVENTI.annotazioni%TYPE;
   BEGIN
      SELECT annotazioni
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_ANNOTAZIONI;
   FUNCTION GET_TIPO
   /******************************************************************************
    NOME:        GET_TIPO
    DESCRIZIONE: Restituisce il tipo dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente il tipo.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return EVENTI.tipo%TYPE;
   BEGIN
      SELECT tipo
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_TIPO;
   FUNCTION GET_TIPO_DESC
   /******************************************************************************
    NOME:        GET_TIPO_DESC
    DESCRIZIONE: Restituisce la descrizione  del tipo di evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente il tipo.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return VARCHAR2(2000);
   BEGIN
      SELECT DECODE(tipo, 'LOGON', 'Accesso Attivo', 'LOGOFF', 'Accesso Chiuso'
                        , 'LOGOUT', 'Accesso Uscito', 'LOGTRC', 'Accesso Funzione'
                        , 'LOGABT', 'Accesso Uscito con Errore', 'LOGWPW', 'Accesso Password Errata'
                        , 'ERROR', 'Errore', 'ARCLOG', 'Archiviazione Accessi'
                        , 'APPTRC', 'Messaggi applicativi'
                        , TO_CHAR(NULL), '(nulla)', tipo)
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_TIPO_DESC;
   FUNCTION GET_TIPO_DESC_RIDOTTA
   /******************************************************************************
    NOME:        GET_TIPO_DESC_RIDOTTA
    DESCRIZIONE: Restituisce la descrizione  del tipo di evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente il tipo.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_stringa VARCHAR2(100);
   BEGIN
      SELECT DECODE(tipo, 'LOGON', 'Attivo', 'LOGOFF', 'Chiuso'
                        , 'LOGOUT', 'Uscito', 'LOGTRC', 'Funzione'
                        , 'LOGABT', 'Abort', 'LOGWPW', 'Password'
                        , 'ERROR', 'Errore', 'ARCLOG', 'Archiviazione'
                        , 'APPTRC', 'Messaggi'
                        , TO_CHAR(NULL), '(nulla)', tipo)
        INTO d_stringa
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_stringa;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_TIPO_DESC_RIDOTTA;
   FUNCTION GET_GRAVITA
   /******************************************************************************
    NOME:        GET_GRAVITA
    DESCRIZIONE: Restituisce la gravita' dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente la gravita'.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return EVENTI.gravita%TYPE;
   BEGIN
      SELECT gravita
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_GRAVITA;
   FUNCTION GET_DESC_GRAVITA
   /******************************************************************************
    NOME:        GET_DESC_GRAVITA
    DESCRIZIONE: Restituisce la descrizione della gravita' dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente la gravita'.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return VARCHAR2(2000);
   BEGIN
      SELECT DECODE(gravita, 'I', 'Informazione', 'E', 'Esclamazione', 'S', 'Errore Bloccante', gravita)
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_DESC_GRAVITA;
   FUNCTION GET_DB_USER
   /******************************************************************************
    NOME:        GET_DB_USER
    DESCRIZIONE: Restituisce lo user di db dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente lo user di db.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return EVENTI.db_user%TYPE;
   BEGIN
      SELECT db_user
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_DB_USER;
   FUNCTION GET_UTENTE
   /******************************************************************************
    NOME:        GET_UTENTE
    DESCRIZIONE: Restituisce l'utente dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente l'utente.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return EVENTI.Utente%TYPE;
   BEGIN
      SELECT Utente
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_UTENTE;
   FUNCTION GET_MODULO
   /******************************************************************************
    NOME:        GET_MODULO
    DESCRIZIONE: Restituisce il modulo dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente il modulo.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return EVENTI.modulo%TYPE;
   BEGIN
      SELECT modulo
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_MODULO;
   FUNCTION GET_ISTANZA
   /******************************************************************************
    NOME:        GET_ISTANZA
    DESCRIZIONE: Restituisce l'istanza dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente l'istanza.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return EVENTI.Istanza%TYPE;
   BEGIN
      SELECT Istanza
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_ISTANZA;
   FUNCTION GET_DATA
   /******************************************************************************
    NOME:        GET_DATA
    DESCRIZIONE: Restituisce la data di registrazione dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     stringa varchar2 contenente la data in formato
                 'dd/mm/yyyy hh24:mi:ss'.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return VARCHAR2(19);
   BEGIN
      SELECT TO_DATE(DATA,'dd/mm/yyyy hh24:mi:ss')
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_DATA;
   FUNCTION GET_NOTIFICATO
   /******************************************************************************
    NOME:        GET_NOTIFICATO
    DESCRIZIONE: Restituisce 1 se l'evento e' stato notificato, 0 altrimenti.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     number (1/0) contenente l'informazione della notifica.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN NUMBER
   IS /* SLAVE_COPY */
      d_return EVENTI.notificato%TYPE;
   BEGIN
      SELECT notificato
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_NOTIFICATO;
   FUNCTION GET_STATO
   /******************************************************************************
    NOME:        GET_STATO
    DESCRIZIONE: Restituisce il codice dello stato dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     varchar2      contenente codice dello stato.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return EVENTI.stato%TYPE;
   BEGIN
      SELECT stato
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_STATO;
   FUNCTION GET_DESC_STATO
   /******************************************************************************
    NOME:        GET_DESC_STATO
    DESCRIZIONE: Restituisce la descrizione dello stato dell'evento dato.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     varchar2      contenente descrizione dello stato.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return VARCHAR2(2000);
   BEGIN
      SELECT DECODE(stato, 'U', 'In uso', 'D', 'Da archiviare', 'A', 'Archiviato', stato)
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_DESC_STATO;
   FUNCTION GET_FILE_LOCATOR
   /******************************************************************************
    NOME:        GET_FILE_LOCATOR
    DESCRIZIONE: Restituisce l'eventuale puntatore al file associato all'evento.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     BFILE.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    07/12/2005 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN BFILE
   IS /* SLAVE_COPY */
      d_return BFILE;
   BEGIN
      SELECT file_locator
        INTO d_return
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END GET_FILE_LOCATOR;
   FUNCTION GET_FILE_LOCATOR_INFO
   /******************************************************************************
    NOME:        GET_FILE_LOCATOR_INFO
    DESCRIZIONE: Restituisce il nome del file su disco con o senza percorso.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
                p_path        1 se si vuole il percorso completo.
                               0 se si vuole solo il nome del file.
                               2 se si vuole il solo percorso della directory.
                               3 se si vuole l'alias di directory.
    RITORNA:     varchar2.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    07/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER
   , p_path      IN NUMBER DEFAULT 1)
   RETURN VARCHAR2
   IS
      d_return    VARCHAR2(4000);
      d_file      BFILE;
      d_directory VARCHAR2(4000);
     d_nomefile  VARCHAR2(4000);
   BEGIN
      SELECT file_locator
        INTO d_file
        FROM EVENTI
       WHERE id_evento = p_id_evento
         AND file_locator IS NOT NULL
      ;
      dbms_lob.filegetname(d_file, d_directory, d_nomefile);
      IF p_path = 3 THEN
         d_return := d_directory;
      ELSIF p_path = 0 THEN
         d_return := d_nomefile;
      ELSE
         d_return := Afc_Bfile.get_dirPath(d_directory);
       IF d_return IS NULL THEN
            d_return := d_directory;
         END IF;
         IF p_path = 1 THEN
            d_return := d_return||'/'||d_nomefile;
         END IF;
      END IF;
      RETURN d_return;
   EXCEPTION
      WHEN OTHERS THEN
         RETURN '';
   END GET_FILE_LOCATOR_INFO;
   FUNCTION GET_FILENAME
   /******************************************************************************
    NOME:        GET_FILENAME
    DESCRIZIONE: Restituisce il nome del file su disco con o senza percorso.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
                p_path        1 se si vuole il percorso completo.
                               0 se si vuole solo il nome del file.
    RITORNA:     varchar2.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    07/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER
   , p_path      IN NUMBER DEFAULT 1)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return    VARCHAR2(4000);
   BEGIN
      RETURN GET_FILE_LOCATOR_INFO(p_id_evento, p_path);
   EXCEPTION
      WHEN OTHERS THEN
         RETURN '';
   END GET_FILENAME;
   FUNCTION GET_DIR_ALIAS
   /******************************************************************************
    NOME:        GET_DIR_ALIAS
    DESCRIZIONE: Restituisce il nome del file su disco con o senza percorso.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
                p_path        1 se si vuole anche il percorso completo
                               0 se si vuole solo il nome del file.
    RITORNA:     varchar2.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    07/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER)
   RETURN VARCHAR2
   IS
      d_file      BFILE;
      d_directory VARCHAR2(4000);
     d_nomefile  VARCHAR2(4000);
   BEGIN
      SELECT file_locator
        INTO d_file
        FROM EVENTI
       WHERE id_evento = p_id_evento
         AND file_locator IS NOT NULL
      ;
      dbms_lob.filegetname(d_file, d_directory, d_nomefile);
      RETURN d_directory;
   EXCEPTION
      WHEN OTHERS THEN
         RETURN '';
   END GET_DIR_ALIAS;
   PROCEDURE INFO_DIR_ALIAS
   /******************************************************************************
    NOME:        INFO_DIR_ALIAS
    DESCRIZIONE: Ottiene il numero di file presenti nella directory.
    ARGOMENTI:   p_dir_alias   alias o percorso della directory.
                p_file_num    numero di file presenti nella directory.
                 p_file_size   dimensione totale dei file presenti nella directory.
                 p_file_filter eventuale filtro da applicare sul nome del file.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    07/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_dir_alias   IN VARCHAR2
   , p_file_num    IN OUT NUMBER
   , p_file_size   IN OUT NUMBER
   , p_file_filter IN VARCHAR2 DEFAULT '')
   IS
      d_directory VARCHAR2(2000);
      d_nomefile  VARCHAR2(2000);
   BEGIN
      p_file_num  := 0;
      p_file_size := 0;
     d_directory := Afc_Bfile.get_dirName(p_dir_alias,'AD4_');
      FOR cur IN (SELECT id_evento, file_locator
                    FROM EVENTI
                   WHERE file_locator IS NOT NULL
                     AND get_dir_alias(id_evento) = d_directory
                     AND NVL(get_filename(id_evento,0),' ') LIKE NVL(p_file_filter, '%'))
      LOOP
         IF dbms_lob.fileExists(cur.file_locator) = 1 THEN
            p_file_num  := p_file_num + 1;
            p_file_size := p_file_size + dbms_lob.getlength(cur.file_locator);
         END IF;
      END LOOP;
      IF p_file_size > 0 THEN
         p_file_size := TRUNC(p_file_size/1024) + 1;
      END IF;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END INFO_DIR_ALIAS;
   FUNCTION get_dir_num_file
   /******************************************************************************
    NOME:        get_dir_num_file
    DESCRIZIONE: Ottiene il numero di file presenti nella directory.
    PARAMETRI:   p_dir_alias   alias o percorso della directory.
                 p_initialize  indica se si vuole rieseguire la select (1) o
                           leggere il valore della variabile(0).
    RITORNA:     number numero di file presenti.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    07/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_dir_alias   IN VARCHAR2
   , p_file_filter IN VARCHAR2 DEFAULT ''
   , p_initialize  IN NUMBER DEFAULT 0)
   RETURN NUMBER
   IS
   BEGIN
      IF p_initialize <> 0 THEN
         INFO_DIR_ALIAS(p_dir_alias, s_dir_num_file, s_dir_tot_dim, p_file_filter);
      END IF;
     RETURN s_dir_num_file;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END get_dir_num_file;
   FUNCTION get_dir_tot_dim
   /******************************************************************************
    NOME:        get_dir_tot_dim
    DESCRIZIONE: Ottiene la dimensione totale dei file presenti nella directory.
    PARAMETRI:   p_dir_alias   alias o percorso della directory.
                 p_initialize  indica se si vuole rieseguire la select (1) o
                           leggere il valore della variabile (0).
    RITORNA:     number dimensione totale dei file.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    07/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_dir_alias IN VARCHAR2
   , p_file_filter IN VARCHAR2 DEFAULT ''
   , p_initialize IN NUMBER DEFAULT 0)
   RETURN NUMBER
   IS
   BEGIN
      IF p_initialize <> 0 THEN
         INFO_DIR_ALIAS(p_dir_alias, s_dir_num_file, s_dir_tot_dim, p_file_filter);
      END IF;
     RETURN s_dir_tot_dim;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END get_dir_tot_dim;
   PROCEDURE copy_file
   /******************************************************************************
    NOME:        copy_file
    DESCRIZIONE: Copia i file presenti nella directory di partenza in quella di
                arrivo.
    ARGOMENTI:   p_dir_from   alias o percorso directory di partenza.
                p_dir_to     alias o percorso directory di arrivo.
                 p_file_filter eventuale filtro da applicare sul nome del file.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    07/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_dir_from    IN VARCHAR2
   , p_dir_to      IN VARCHAR2
   , p_file_filter IN VARCHAR2)
   IS
      d_directory VARCHAR2(4000);
   BEGIN
     d_directory := Afc_Bfile.get_dirName(p_dir_from,'AD4_');
      FOR cur IN (SELECT id_evento, file_locator, get_filename(id_evento,0) filename
                    FROM EVENTI
                   WHERE file_locator IS NOT NULL
                     AND get_dir_alias(id_evento) = d_directory
                     AND NVL(get_filename(id_evento,0),' ') LIKE NVL(p_file_filter, '%'))
      LOOP
         IF dbms_lob.fileExists(cur.file_locator) = 1 THEN
            Si4_Xml.copy(p_dir_from, cur.filename, p_dir_to, cur.filename);
         END IF;
      END LOOP;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END copy_file;
   FUNCTION CHECK_FILE_LOCATOR
   /******************************************************************************
    NOME:        CHECK_FILE_LOCATOR
    DESCRIZIONE: Verifica l' esistenza del file associato all'evento sia sulla
                tabella che sul file system.
    PARAMETRI:   p_id_evento   identificativo numerico dell'evento.
    RITORNA:     1 se il file locator e' presente ed il file esiste.
                0 se il file locator non e' presente
                -1 se il file locator e' presente ma il file non esiste.
                -2 se l'evento passato non esiste.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    07/12/2005 MM     Prima emissione.
   ******************************************************************************/
   (p_id_evento IN NUMBER)
   RETURN NUMBER
   IS
      d_return NUMBER;
      d_file   BFILE;
   BEGIN
      d_return := -2;
     SELECT 1, file_locator
        INTO d_return, d_file
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      IF d_file IS NULL THEN
         d_return := 0;
      ELSE
         IF dbms_lob.fileExists(d_file) <> 1 THEN
            d_return := -1;
         END IF;
      END IF;
      RETURN d_return;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         RETURN d_return;
      WHEN OTHERS THEN
         IF SQLCODE = -22285 THEN
            RETURN -1;
         ELSE
            RAISE;
         END IF;
   END CHECK_FILE_LOCATOR;
   PROCEDURE SET_TESTO
   /******************************************************************************
    NOME:        SET_TESTO
    DESCRIZIONE: Modifica l'attributo TESTO dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_testo IN VARCHAR2 valore da associare all'attributo TESTO.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER
   , p_testo IN VARCHAR2)
   IS
   BEGIN
      UPDATE EVENTI
         SET testo = p_testo
       WHERE id_evento = p_id_evento
      ;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END SET_TESTO;
   PROCEDURE SET_ANNOTAZIONI
   /******************************************************************************
    NOME:        SET_ANNOTAZIONI
    DESCRIZIONE: Modifica l'attributo ANNOTAZIONI dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_annotazioni IN VARCHAR2 valore da associare all'attributo ANNOTAZIONI.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER
   , p_annotazioni IN VARCHAR2)
   IS
   BEGIN
      UPDATE EVENTI
         SET annotazioni = p_annotazioni
       WHERE id_evento = p_id_evento
      ;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END SET_ANNOTAZIONI;
   PROCEDURE SET_TIPO
   /******************************************************************************
    NOME:        SET_TIPO
    DESCRIZIONE: Modifica l'attributo TIPO dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_tipo IN VARCHAR2 valore da associare all'attributo TIPO.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER
   , p_tipo IN VARCHAR2)
   IS
   BEGIN
      UPDATE EVENTI
         SET tipo = p_tipo
       WHERE id_evento = p_id_evento
      ;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END SET_TIPO;
   PROCEDURE SET_GRAVITA
   /******************************************************************************
    NOME:        SET_GRAVITA
    DESCRIZIONE: Modifica l'attributo GRAVITA dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_gravita IN VARCHAR2 valore da associare all'attributo GRAVITA.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER
   , p_gravita IN VARCHAR2)
   IS
   BEGIN
      UPDATE EVENTI
         SET gravita = p_gravita
       WHERE id_evento = p_id_evento
      ;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END SET_GRAVITA;
   PROCEDURE SET_DB_USER
   /******************************************************************************
    NOME:        SET_DB_USER
    DESCRIZIONE: Modifica l'attributo DB_USER dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_db_user IN VARCHAR2 valore da associare all'attributo DB_USER.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER
   , p_db_user IN VARCHAR2)
   IS
   BEGIN
      UPDATE EVENTI
         SET db_user = p_db_user
       WHERE id_evento = p_id_evento
      ;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END SET_DB_USER;
   PROCEDURE SET_UTENTE
   /******************************************************************************
    NOME:        SET_UTENTE
    DESCRIZIONE: Modifica l'attributo UTENTE dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_utente IN VARCHAR2 valore da associare all'attributo UTENTE.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER
   , p_utente IN VARCHAR2)
   IS
   BEGIN
      UPDATE EVENTI
         SET Utente = p_utente
       WHERE id_evento = p_id_evento
      ;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END SET_UTENTE;
   PROCEDURE SET_MODULO
   /******************************************************************************
    NOME:        SET_MODULO
    DESCRIZIONE: Modifica l'attributo MODULO dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_modulo IN VARCHAR2 valore da associare all'attributo MODULO.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER
   , p_modulo IN VARCHAR2)
   IS
   BEGIN
      UPDATE EVENTI
         SET modulo = p_modulo
       WHERE id_evento = p_id_evento
      ;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END SET_MODULO;
   PROCEDURE SET_ISTANZA
   /******************************************************************************
    NOME:        SET_ISTANZA
    DESCRIZIONE: Modifica l'attributo ISTANZA dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
                 p_istanza IN VARCHAR2 valore da associare all'attributo ISTANZA.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER
   , p_istanza IN VARCHAR2)
   IS
   BEGIN
      UPDATE EVENTI
         SET Istanza = p_istanza
       WHERE id_evento = p_id_evento
      ;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END SET_ISTANZA;
   PROCEDURE SET_DATA
   /******************************************************************************
    NOME:        SET_DATA
    DESCRIZIONE: Modifica l'attributo DATA dell'evento con il valore passato.
    ARGOMENTI:   p_id_evento IN NUMBER   identificativo dell'evento.
                 p_data      IN VARCHAR2 valore da associare all'attributo DATA in
                                         formato 'dd/mm/yyyy hh24:mi:ss'.
    NOTE:        --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN NUMBER
   , p_data IN VARCHAR2)
   IS
   BEGIN
      UPDATE EVENTI
         SET DATA = TO_DATE(p_data,'dd/mm/yyyy hh24:mi:ss')
       WHERE id_evento = p_id_evento
      ;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END SET_DATA;
   -- , , , , , , ,
   PROCEDURE SET_NOTIFICATO
   /******************************************************************************
    NOME:        SET_NOTIFICATO
    DESCRIZIONE: Modifica l'evento in modo che risulti NOTIFICATO.
    ARGOMENTI:   p_id_evento IN NUMBER identificativo dell'evento.
    ECCEZIONI:   --
    ANNOTAZIONI: --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento NUMBER )
   IS
   BEGIN
      SET_NOTIFICATO(p_id_evento, 1);
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END SET_NOTIFICATO;
   PROCEDURE SET_NOTIFICATO
   /******************************************************************************
    NOME:        SET_NOTIFICATO
    DESCRIZIONE: Modifica l'attributo NOTIFICATO dell'evento con il valore
                 passato.
    ARGOMENTI:   p_id_evento  IN NUMBER identificativo dell'evento.
                 p_notificato IN NUMBER valore da associare all'attributo
                                        NOTIFICATO.
    ECCEZIONI:   --
    ANNOTAZIONI: --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento NUMBER
   , p_notificato NUMBER)
   IS
   BEGIN
      UPDATE EVENTI
         SET notificato = p_notificato,
             DATA       = SYSDATE
       WHERE id_evento = p_id_evento
      ;
   END SET_NOTIFICATO;
   PROCEDURE SET_FILE_LOCATOR
   /******************************************************************************
    NOME:        SET_FILE_LOCATOR
    DESCRIZIONE: Modifica l'attributo FILE_LOCATOR dell'evento con il valore
                 passato.
    ARGOMENTI:   p_id_evento    IN NUMBER identificativo dell'evento.
                 p_file_locator IN BFILE  valore da associare all'attributo
                                          FILE_LOCATOR.
    ECCEZIONI:   --
    ANNOTAZIONI: --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    07/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento    IN NUMBER
   , p_file_locator IN BFILE)
   IS /* SLAVE_COPY */
   BEGIN
      UPDATE EVENTI
         SET file_locator = p_file_locator,
             DATA         = SYSDATE
       WHERE id_evento    = p_id_evento
      ;
   END SET_FILE_LOCATOR;
   PROCEDURE SET_FILE_LOCATOR
   /******************************************************************************
    NOME:        SET_FILE_LOCATOR
    DESCRIZIONE: Modifica l'attributo FILE_LOCATOR dell'evento con il valore
                 passato.
    ARGOMENTI:   p_id_evento    IN NUMBER identificativo dell'evento.
                 p_file_locator IN BFILE  valore da associare all'attributo
                                          FILE_LOCATOR.
    ECCEZIONI:   --
    ANNOTAZIONI: --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    07/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento    IN NUMBER
   , p_dir_alias    IN VARCHAR2
   , p_filename     IN VARCHAR2)
   IS
      d_file BFILE;
   BEGIN
      d_file := BFILENAME(p_dir_alias, p_filename);
      SET_FILE_LOCATOR(p_id_evento, d_file);
   END SET_FILE_LOCATOR;
   PROCEDURE UPDATE_EVENTO
   /******************************************************************************
    NOME:        UPDATE_EVENTO
    DESCRIZIONE: Permette l'aggiornamento / inserimento di un evento.
                 Se viene passato un identificativo di evento significativo
                 (p_id_evento not null),
                    aggiorna l'evento con i dati passati (tutti),
                 altrimenti
                    inserisce un nuovo evento con i dati passati.
                 L'evento sara' registrato:
                 - in data p_data
                 - notificato o non notificato (p_notificato)
                 - con gravita' specificatata (p_gravita)
                 - di tipo dato (p_tipo)
                 - contenente il testo dato(p_testo)
                 - relativo allo user di database dato (p_db_user)
                 - con le informazioni aggiuntive date (p_annotazioni)
                 - per l'utente, il modulo e l'istanza  di procedura dati (p_utente
                   p_modulo, p_istanza).
    ARGOMENTI:   p_id_evento   IN NUMBER   Identificativo dell'evento.
                 p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
                 p_data        IN VARCHAR2 Data di registrazione dell'evento in
                                           formato 'dd/mm/yyyy hh24:mi:ss' (se
                                           non specificata assume il valore
                                           sysdate).
                 p_notificato  IN VARCHAR2 Indicatore di avvenuta notifica
                                           dell'evento (se non specificato assume
                                           il valore 0 - NON Notificato).
                 p_gravita     IN VARCHAR2 Gravita' dell'evento (se non specificata
                                           assume il valore 'I' - Informazione).
                 p_tipo        IN VARCHAR2   Tipologia dell'evento (se non specificata
                                           assume il valore 'ERROR').
                 p_annotazioni IN VARCHAR2 Eventuali informazioni aggiuntive
                                           dell'evento.
                 p_utente      IN VARCHAR2 Eventuale utente di procedura
                                           dell'evento.
                 p_modulo      IN VARCHAR2 Eventuale modulo di procedura
                                           dell'evento.
                 p_istanza     IN VARCHAR2 Eventuale  istanza di ambiente
                                           dell'evento.
    ECCEZIONI:   -20999, Il testo e l'utente di database (db_user) dell'evento
                         non possono essere nulli.
    ANNOTAZIONI: -
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
    1    22/11/2005 MM     Modifica del def di p_tipo da REPLAY a ERROR
    3    06/06/2007 MM     Gestione EVEN_SQ.
   ******************************************************************************/
   ( p_id_evento   IN OUT NUMBER
   , p_testo       IN VARCHAR2
   , p_db_user     IN VARCHAR2
   , p_data        IN VARCHAR2
   , p_notificato  IN NUMBER
   , p_gravita     IN VARCHAR2
   , p_tipo        IN VARCHAR2
   , p_annotazioni IN VARCHAR2
   , p_utente      IN VARCHAR2
   , p_modulo      IN VARCHAR2
   , p_istanza     IN VARCHAR2
   )
   IS
      d_tipo       EVENTI.tipo%TYPE       := NVL(p_tipo,'ERROR');
   BEGIN
      IF p_testo IS NULL OR p_db_user IS NULL THEN
         RAISE_APPLICATION_ERROR(-20999, 'Il testo e l''utente di database (db_user) dell''evento non possono essere nulli.');
      END IF;
      IF p_id_evento IS NULL THEN
         -- Rev. 3    06/06/2007 MM     Gestione EVEN_SQ.
         --p_id_evento := Si4.NEXT_ID ('EVENTI','ID_EVENTO');
         select EVEN_SQ.NEXTVAL
           into p_id_evento
           from dual;
         -- Rev. 3    06/06/2007 MM     fine mod.
         INSERT INTO EVENTI ( id_evento, testo, annotazioni, tipo, gravita, db_user, Utente
                            , modulo, Istanza, DATA, notificato)
                     VALUES ( p_id_evento, p_testo
                            , DECODE(p_annotazioni, 'no', NULL, p_annotazioni)
                            , DECODE(d_tipo, 'no', 'ERROR', d_tipo)
                            , DECODE(p_gravita, 'no', NULL, p_gravita)
                            , p_db_user
                            , DECODE(p_utente, 'no', NULL, p_utente)
                            , DECODE(p_modulo, 'no', NULL, p_modulo)
                            , DECODE(p_istanza, 'no', NULL, p_istanza)
                            , DECODE(p_data,'no', SYSDATE,TO_DATE(p_data,'dd/mm/yyyy hh24:mi:ss'))
                            , DECODE(p_notificato, -1, 0, p_notificato))
         ;
      ELSE
         UPDATE EVENTI
           SET testo       = p_testo
              , annotazioni = DECODE(p_annotazioni, 'no', annotazioni, p_annotazioni)
              , tipo        = DECODE(d_tipo, 'no', tipo, d_tipo)
              , gravita     = DECODE(p_gravita, 'no', gravita, p_gravita)
              , db_user     = p_db_user
              , Utente      = DECODE(p_utente, 'no', Utente, p_utente)
              , modulo      = DECODE(p_modulo, 'no', modulo, p_modulo)
              , Istanza     = DECODE(p_istanza, 'no', Istanza, p_istanza)
              , DATA        = DECODE(p_data,'no', DATA, TO_DATE(p_data,'dd/mm/yyyy hh24:mi:ss'))
              , notificato  = DECODE(p_notificato, -1, notificato, p_notificato)
          WHERE id_evento = p_id_evento
         ;
      END IF;
   EXCEPTION
      WHEN OTHERS THEN
           RAISE;
   END UPDATE_EVENTO;
   PROCEDURE UPDATE_EVENTO
   /******************************************************************************
    NOME:        UPDATE_EVENTO
    DESCRIZIONE: Permette l'aggiornamento / inserimento di un evento.
                 Se viene passato un identificativo di evento significativo
                 (p_id_evento not null),
                    aggiorna l'evento con i dati passati (tutti),
                 altrimenti
                    inserisce un nuovo evento con i dati passati.
                 L'evento sara' registrato:
                 - in data odierna
                 - non notificato
                 - con gravita' Informazione
                 - di tipo ERROR
                 - contenente il testo dato(p_testo)
                 - relativo allo user di database dato (p_db_user)
                 - con le informazioni aggiuntive date (p_annotazioni)
                 - per l'utente, il modulo e l'istanza  di procedura dati (p_utente
                   p_modulo, p_istanza).
    ARGOMENTI:   p_id_evento   IN NUMBER   Identificativo dell'evento.
                 p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
                 p_annotazioni IN VARCHAR2 Eventuali informazioni aggiuntive
                                           dell'evento.
                 p_utente      IN VARCHAR2 Eventuale utente di procedura
                                           dell'evento.
                 p_modulo      IN VARCHAR2 Eventuale modulo di procedura
                                           dell'evento.
                 p_istanza     IN VARCHAR2 Eventuale  istanza di ambiente
                                           dell'evento.
    ECCEZIONI:   -20999, Il testo e l'utente di database (db_user) dell'evento
                         non possono essere nulli.
    ANNOTAZIONI: -
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento   IN OUT NUMBER
   , p_testo       IN VARCHAR2
   , p_db_user     IN VARCHAR2
   , p_annotazioni IN VARCHAR2
   , p_utente      IN VARCHAR2
   , p_modulo      IN VARCHAR2
   , p_istanza     IN VARCHAR2
   )
   IS
   BEGIN
      UPDATE_EVENTO ( p_id_evento, p_testo , p_db_user , p_data => 'no'
                    , p_notificato => -1, p_gravita => 'no', p_tipo => 'no'
                    , p_annotazioni => p_annotazioni, p_utente => p_utente
                    , p_modulo => p_modulo, p_istanza => p_istanza);
   EXCEPTION
      WHEN OTHERS THEN
           RAISE;
   END UPDATE_EVENTO;
   PROCEDURE UPDATE_EVENTO
   /******************************************************************************
    NOME:        UPDATE_EVENTO
    DESCRIZIONE: Permette l'aggiornamento / inserimento di un evento.
                 Se viene passato un identificativo di evento significativo
                 (p_id_evento not null),
                    aggiorna l'evento con i dati passati (tutti),
                 altrimenti
                    inserisce un nuovo evento con i dati passati.
                 L'evento sara' registrato:
                 - in data odierna
                 - non notificato
                 - con gravita' Informazione
                 - di tipo ERROR
                 - contenente il testo dato(p_testo)
                 - relativo allo user di database dato (p_db_user).
    ARGOMENTI:   p_id_evento   IN NUMBER   Identificativo dell'evento.
                 p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
    ECCEZIONI:   -20999, Il testo e l'utente di database (db_user) dell'evento
                         non possono essere nulli.
    ANNOTAZIONI: -
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_id_evento IN OUT NUMBER
   , p_testo     IN     VARCHAR2
   , p_db_user   IN     VARCHAR2
   )
   IS
   BEGIN
      UPDATE_EVENTO ( p_id_evento, p_testo, p_db_user
                    , p_annotazioni => 'no', p_utente => 'no', p_modulo => 'no'
                    , p_istanza => 'no');
   EXCEPTION
      WHEN OTHERS THEN
           RAISE;
   END UPDATE_EVENTO;
   FUNCTION INSERT_EVENTO
   /******************************************************************************
    NOME:        INSERT_EVENTO
    DESCRIZIONE: Permette l'inserimento di un evento:
                 - in data p_data
                 - notificato o non notificato (p_notificato)
                 - con gravita' specificatata (p_gravita)
                 - di tipo dato (p_tipo)
                 - contenente il testo dato(p_testo)
                 - relativo allo user di database dato (p_db_user)
                 - con le informazioni aggiuntive date (p_annotazioni)
                 - per l'utente, il modulo e l'istanza  di procedura dati (p_utente
                   p_modulo, p_istanza).
    PARAMETRI:   p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
                 p_data        IN VARCHAR2 Data di registrazione dell'evento (se
                                           non specificata assume il valore
                                           sysdate).
                 p_notificato  IN VARCHAR2 Indicatore di avvenuta notifica
                                           dell'evento (se non specificato assume
                                           il valore 0 - NON Notificato).
                 p_gravita     IN VARCHAR2 Gravita' dell'evento (se non specificata
                                           assume il valore 'I' - Informazione).
                 p_tipo        IN VARCHAR2   Tipologia dell'evento (se non specificata
                                           assume il valore 'REPLAY').
                 p_annotazioni IN VARCHAR2 Eventuali informazioni aggiuntive
                                           dell'evento.
                 p_utente      IN VARCHAR2 Eventuale utente di procedura
                                           dell'evento.
                 p_modulo      IN VARCHAR2 Eventuale modulo di procedura
                                           dell'evento.
                 p_istanza     IN VARCHAR2 Eventuale  istanza di ambiente
                                           dell'evento.
    RITORNA:     number contenente l'identificativo dell'evento.
    NOTE:        --
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_testo       IN VARCHAR2
   , p_db_user     IN VARCHAR2
   , p_data        IN VARCHAR2
   , p_notificato  IN NUMBER
   , p_gravita     IN VARCHAR2
   , p_tipo        IN VARCHAR2
   , p_annotazioni IN VARCHAR2
   , p_utente      IN VARCHAR2
   , p_modulo      IN VARCHAR2
   , p_istanza     IN VARCHAR2
   )
   RETURN NUMBER
   IS
      d_id_evento    EVENTI.id_evento%TYPE;
   BEGIN
      UPDATE_EVENTO ( d_id_evento, p_testo , p_db_user , p_data , p_notificato
                    , p_gravita, p_tipo, p_annotazioni, p_utente, p_modulo, p_istanza);
      RETURN d_id_evento;
   EXCEPTION
      WHEN OTHERS THEN
           RAISE;
   END INSERT_EVENTO;
   FUNCTION INSERT_EVENTO
   /******************************************************************************
    NOME:        INSERT_EVENTO
    DESCRIZIONE: Permette l'inserimento di un evento:
                 - in data odierna
                 - non notificato
                 - con gravita' "Informazione"
                 - di tipo ERROR
                 - contenente il testo specificato (p_testo)
                 - relativo allo user di database specificato (p_db_user)
                 - con le informazioni aggiuntive date (p_annotazioni),
                 - per l'utente, il modulo e l'istanza  di procedura dati (p_utente,
                   p_modulo, p_istanza).
    PARAMETRI:   p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
                 p_annotazioni IN VARCHAR2 Eventuali informazioni aggiuntive
                                           dell'evento.
                 p_utente      IN VARCHAR2 Eventuale utente di procedura
                                           dell'evento.
                 p_modulo      IN VARCHAR2 Eventuale modulo di procedura
                                           dell'evento.
                 p_istanza     IN VARCHAR2 Eventuale  istanza di ambiente
                                           dell'evento.
    RITORNA:     number contenente l'identificativo dell'evento.
    NOTE:        --
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_testo       IN VARCHAR2
   , p_db_user     IN VARCHAR2
   , p_annotazioni IN VARCHAR2
   , p_utente      IN VARCHAR2
   , p_modulo      IN VARCHAR2
   , p_istanza     IN VARCHAR2
   )
   RETURN NUMBER
   IS
      d_id_evento    EVENTI.id_evento%TYPE;
   BEGIN
      UPDATE_EVENTO ( d_id_evento, p_testo, p_db_user
                , p_annotazioni , p_utente , p_modulo, p_istanza);
      RETURN d_id_evento;
   EXCEPTION
      WHEN OTHERS THEN
           RAISE;
   END INSERT_EVENTO;
   FUNCTION INSERT_EVENTO
   /******************************************************************************
    NOME:        INSERT_EVENTO
    DESCRIZIONE: Permette l'inserimento di un evento:
                 - in data odierna
                 - non notificato
                 - con gravita' "Informazione"
                 - di tipo ERROR
                 - contenente il testo specificato (p_testo)
                 - relativo allo user di database specificato (p_db_user).
    PARAMETRI:   p_testo       IN VARCHAR2 Testo dell'evento (deve essere NON
                                           NULLO).
                 p_db_user     IN VARCHAR2 DB User dell'evento (deve essere NON
                                           NULLO).
    RITORNA:     NUMBER contenente l'identificativo dell'evento.
    NOTE:        --
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    17/12/2003 MM     Prima emissione.
   ******************************************************************************/
   ( p_testo   IN VARCHAR2
   , p_db_user IN VARCHAR2
   )
   RETURN NUMBER
   IS
      d_id_evento    EVENTI.id_evento%TYPE;
   BEGIN
      UPDATE_EVENTO(d_id_evento, p_testo, p_db_user);
      RETURN d_id_evento;
   EXCEPTION
      WHEN OTHERS THEN
           RAISE;
   END INSERT_EVENTO;
   FUNCTION ripristina
   ( p_id_evento  IN NUMBER
   , p_table      IN VARCHAR2
   , p_rowtag     IN VARCHAR2 DEFAULT 'row')
   RETURN NUMBER
   IS
      d_bfile     BFILE;
      d_return    NUMBER;
      d_directory VARCHAR2(4000);
      d_nomefile  VARCHAR2(4000);
   BEGIN
      SELECT file_locator
        INTO d_bfile
        FROM EVENTI
       WHERE id_evento = p_id_evento
         AND file_locator IS NOT NULL
      ;
      IF dbms_lob.fileExists(d_bfile) <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999, 'File inesistente.');
      ELSE
         d_return := Si4_Xml.import(d_bfile, p_table, p_rowtag);
      END IF;
      IF d_return < 0 THEN
         RAISE_APPLICATION_ERROR(-20999, 'Errore in fase di import sulla tabella '||p_table||'.');
      END IF;
      dbms_lob.filegetname(d_bfile, d_directory, d_nomefile);
      d_directory := Afc_Bfile.get_dirPath(d_directory);
      INSERT INTO EVENTI (testo, tipo, db_user, Utente, modulo, Istanza, DATA)
                  VALUES ('Ripristino dei dati di '||p_table||' contenuti in file '||d_directory||'/'||d_nomefile, 'ARCLOG', USER, NVL(Si4.Utente,'AD4'), NVL(Si4.modulo,'AD4'), NVL(Si4.Istanza,'AD4'), SYSDATE)
      ;
     RETURN d_return;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         RAISE_APPLICATION_ERROR(-20999, 'File non associato all''evento.');
      WHEN OTHERS THEN
         RAISE;
   END ripristina;
   FUNCTION GET_LOCATOR_MIMETYPE ( p_id_evento NUMBER )
   RETURN VARCHAR2
   IS
      d_file BFILE;
      d_return VARCHAR2(100);
      d_pospunto INTEGER;
      d_estensione VARCHAR2(10);
   BEGIN
      SELECT file_locator
        INTO d_file
        FROM EVENTI
       WHERE id_evento = p_id_evento
      ;
      d_return := GET_FILENAME(p_id_evento, 0);
      d_pospunto := INSTR(d_return, '.', -1);
      IF d_pospunto > 0 THEN
         d_estensione := LOWER(SUBSTR(d_return, d_pospunto + 1));
         IF d_estensione = 'xml' THEN
            d_return := 'application/xml';
         END IF;
      ELSE
         RETURN '';
      END IF;
      RETURN d_return;
   EXCEPTION WHEN NO_DATA_FOUND THEN
      RETURN '';
   END GET_LOCATOR_MIMETYPE;

   PROCEDURE INSERT_EVENTO_COMMIT  ( p_testo       IN VARCHAR2
                             , p_db_user     IN VARCHAR2
                             , p_data        IN VARCHAR2
                             , p_notificato  IN NUMBER
                             , p_gravita     IN VARCHAR2
                             , p_tipo        IN VARCHAR2
                             , p_annotazioni IN VARCHAR2
                             , p_utente      IN VARCHAR2
                             , p_modulo      IN VARCHAR2
                             , p_istanza     IN VARCHAR2)
   IS
   pragma autonomous_transaction;
   v_id_evento number;
   BEGIN
   v_id_evento := INSERT_EVENTO    ( p_testo
                             , p_db_user
                             , p_data
                             , p_notificato
                             , p_gravita
                             , p_tipo
                             , p_annotazioni
                             , p_utente
                             , p_modulo
                             , p_istanza);
   commit;
   END;

END Ad4_Evento;
/
