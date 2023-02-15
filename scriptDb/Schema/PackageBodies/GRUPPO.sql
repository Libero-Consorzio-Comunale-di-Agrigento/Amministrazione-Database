CREATE OR REPLACE PACKAGE BODY Gruppo IS
/******************************************************************************
 NOME:        GRUPPO
 DESCRIZIONE: Package per gestione GRUPPI di UTENTI.
              Funzioni di gestione dei diritti e delle caratteristiche di accesso
              di un   utente derivanti dalla sua appartenenza ad un gruppo.
 ANNOTAZIONI: -
 ECCEZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Prima emissione.
 1    26/06/2003 MM     Se veniva eliminato un utente da un gruppo, eliminava
                        tutti i suoi diritti di accesso anche se non derivanti
                        dall'appartenenza ad un gruppo.
 2    03/11/2004 VA     Gestione tipo_utente=O (Organizzazione), eliminando
                        controllo su tipo_utente = G
 3    19/11/2004 VA     Gestione del diritto d'accesso relativo al gruppo con
                        importanza piu alta (0 alta - 99 bassa).
 4    27/10/2006 MM     Introduzione delle funzioni:
                        - get_id
                        - get_descrizione
                        - get_tipo
                        - get_codice
                        - set_descrizione
                        - ins
                        - ins_uo
 5    23/04/2007 MM     A20741.0.0: modificate procedure diac_gruppo_delete e
                        caac_gruppo_delete; create diac_caac_gruppo_delete,
                        is_diac_del, riempi_diac_eliminati, exists_descrizione.
 6    06/05/2008 MM     Modifica nome parametro procedure ins (da p_descrizione
                        a p_desc) per TOO MANY DECLARATION
 7    14/11/2008 MM     A29921.0.0:: modificata ins_uo.
 8    01/06/2010 SNeg   Calcolo dell'importanza considerando tutti i gruppi
                        attraverso i quali il diritto e ereditato.
                        I gruppi di struttura organizzativa ('O') hanno a parita
                        di importanza diritto maggiore.
                        In caso di gruppi di tipo 'O' prende i diritti del padre
                        più "vicino"
 9    11/08/2010  MM    Modificata next_id in modo che il calcolo del primo
                        numero libero per il codice dei gruppi di tipo O,
                        consideri sia i prefissi maisculi che minuscoli per
                        evitare errore in esecuzione della ins del gruppo se
                        esiste gia uno uguale ma maiuscolo (cioe' sto cercando
                        di inserire o55 ma mi da errore perche' esiate O55).
10  25/10/2011  SNeg Modificate le exists per performance
11  12/04/2017  SNeg Modificata assegnazione del gruppo x fare vincere quelli di
                     tipo 'O'
12 30/01/2019  Sneg Acquisire solo diritti di accesso coerenti x modulo di Amministrazione SI/NO
                              a quanto dichiarato x utente.
13 22/05/2019  SNeg utilizzo funzione global_utility.get_registro_amministratore
14 23/12/2019  SNeg Esaminare solo i gruppi Bug #34783
15 23/12/2019  SNeg Compatibilita con versione 10.20.0.3 Bug #41150
******************************************************************************/
   TYPE DiacGruppoRec IS RECORD  ( progetto VARCHAR2(10)
                                 , Istanza  VARCHAR2(10)
                                 , modulo   VARCHAR2(10)
                                 , ruolo    VARCHAR2(8)
                                 , note     VARCHAR2(2000));
   TYPE DiacTab   IS TABLE OF DiacGruppoRec INDEX BY BINARY_INTEGER;
   TYPE DiacDelRec IS RECORD ( Utente    VARCHAR2(8)
                             , Istanza   VARCHAR2(10)
                             , modulo    VARCHAR2(10)
                             , ruolo     VARCHAR2(8)
                             , Gruppo    VARCHAR2(8));
   TYPE DiacDelTab IS TABLE OF DiacDelRec INDEX BY BINARY_INTEGER;
   TYPE UtenteRec IS RECORD ( Utente VARCHAR2(8)
                            , tipo_utente VARCHAR2(1));
   TYPE UtentiTab IS TABLE OF UtenteRec INDEX BY BINARY_INTEGER;
   dRegistroVerificaAmm varchar2(2) ;
   FUNCTION VERSIONE  RETURN VARCHAR2 IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 ECCEZIONI:   --
 ANNOTAZIONI: --
******************************************************************************/
   BEGIN
      RETURN 'V1.'||Revisione;
   END VERSIONE;
   PROCEDURE ins
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
   ( p_desc  IN UTENTI.nominativo%TYPE
   , p_tipo   IN UTENTI.tipo_utente%TYPE  DEFAULT 'G'
   , p_codice IN UTENTI.Utente%TYPE  DEFAULT NULL
   , p_id     IN UTENTI.id_utente%TYPE  DEFAULT NULL
   , p_gruppo_lavoro IN UTENTI.gruppo_lavoro%TYPE DEFAULT NULL)
   IS
      d_codice UTENTI.Utente%TYPE := p_codice;
      d_id     UTENTI.id_utente%TYPE := p_id;
   BEGIN
      ins (p_desc, d_codice, d_id, p_tipo, p_gruppo_lavoro);
   END ins;
   PROCEDURE ins
/******************************************************************************
 NOME:        ins
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
   ( p_descrizione  IN UTENTI.nominativo%TYPE
   , p_codice IN OUT UTENTI.Utente%TYPE
   , p_id     IN OUT UTENTI.id_utente%TYPE
   , p_tipo   IN UTENTI.tipo_utente%TYPE  DEFAULT 'G'
   , p_gruppo_lavoro IN UTENTI.gruppo_lavoro%TYPE DEFAULT NULL)
   IS
   v_esistono integer;
   BEGIN
      IF p_tipo = 'U' THEN
         RAISE_APPLICATION_ERROR(-20999, 'Inserimento gruppo: tipo non valido (''U'').');
      END IF;
      Utente.calcola_utente(p_codice, p_id);
      -- controllo che non esista gia un utente con lo stesso nominativo e utente
      -- non considerando maiuscole o minuscole
      select count(*)
        into v_esistono
        from utenti
       where upper(nominativo) = upper(p_descrizione)
          or upper(utente) = upper(p_codice);
      if v_esistono = 0 then
         INSERT INTO UTENTI (nominativo, tipo_utente, Utente, id_utente, gruppo_lavoro)
                     VALUES (p_descrizione, p_tipo, p_codice, p_id, p_gruppo_lavoro)
         ;
      else -- esiste gia
         RAISE_APPLICATION_ERROR(-20999, 'Inserimento gruppo: nominativo('||p_descrizione || ') o codice('|| p_codice||') GIA'' PRESENTE');
      end if;
   END ins;
   FUNCTION next_id
   /******************************************************************************
    NOME:        next_id
    DESCRIZIONE: Ottiene il primo id libero.
    PARAMETRI:   p_prefix varchar2 prefisso da anteporre al primo numero libero.
    RITORNA:     varchar2: id creato
    ANNOTAZIONI: --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
       4  22/12/2006 MM    Prima emissione.
       9 11/08/2010  MM    Modificata in modo che il calcolo del primo
                           numero libero per il codice dei gruppi di tipo O,
                           consideri sia i prefissi maisculi che minuscoli per
                           evitare errore in esecuzione della ins del gruppo se
                           esiste gia uno uguale ma maiuscolo (cioe' sto cercando
                           di inserire o55 ma mi da errore perche' esiste O55).
   ******************************************************************************/
   ( p_prefix  VARCHAR2)
   RETURN VARCHAR2 IS
      d_return   VARCHAR2(30);
      d_tmpVar   VARCHAR2(30);
   BEGIN
      IF p_prefix IS NOT NULL THEN
         SELECT p_prefix||TO_CHAR(NVL(MAX(TO_NUMBER(SUBSTR(Utente, LENGTH(p_prefix) + 1))),0) + 1)
           INTO d_return
           FROM UTENTI
          WHERE upper(Utente) LIKE upper(p_prefix)||'%'
            AND Afc.is_number(SUBSTR(Utente, LENGTH(p_prefix) + 1)) = 1
         ;
      END IF;
      RETURN d_return;
   END next_id;
   PROCEDURE ins_uo
/******************************************************************************
 NOME:        ins_uo
 DESCRIZIONE: Inserimento di un gruppo di tipo 'O'.
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
   ( p_descrizione  IN UTENTI.nominativo%TYPE
   , p_codice  IN OUT UTENTI.Utente%TYPE
   , p_id  IN OUT UTENTI.id_utente%TYPE
   , p_gruppo_lavoro IN UTENTI.gruppo_lavoro%TYPE DEFAULT NULL)
   IS
      d_gruppo_lavoro utenti.GRUPPO_LAVORO%type:=nvl(p_gruppo_lavoro, 'def');
   BEGIN
      p_codice := next_id('o');
      ins(p_descrizione, p_codice, p_id, 'O', d_gruppo_lavoro);
   END ins_uo;
   FUNCTION GET_ID
/******************************************************************************
 NOME:        GET_ID
 DESCRIZIONE: Restituisce l'id del gruppo.
 PARAMETRI:   p_gruppo: codice gruppo.
 RITORNA:     number.
 ECCEZIONI:   --
 ANNOTAZIONI: --
******************************************************************************/
   (p_gruppo IN VARCHAR2) RETURN NUMBER
   IS /* SLAVE_COPY */
      d_id NUMBER;
   BEGIN
      SELECT id_utente
        INTO d_id
        FROM UTENTI
       WHERE Utente = p_gruppo
         AND tipo_utente <> 'U'
      ;
      RETURN d_id;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         RETURN -1;
      WHEN OTHERS THEN
         RAISE;
   END GET_ID;
   FUNCTION GET_DESCRIZIONE
/******************************************************************************
 NOME:        GET_DESCRIZIONE
 DESCRIZIONE: Restituisce la descrizione del gruppo.
 PARAMETRI:   p_gruppo: codice gruppo.
 RITORNA:     stringa varchar2.
 ECCEZIONI:   --
 ANNOTAZIONI: --
******************************************************************************/
   (p_gruppo IN VARCHAR2) RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_desc VARCHAR2(1000);
   BEGIN
      SELECT nominativo
        INTO d_desc
        FROM UTENTI
       WHERE Utente = p_gruppo
         AND tipo_utente <> 'U'
      ;
      RETURN d_desc;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         RETURN '';
      WHEN OTHERS THEN
         RAISE;
   END GET_DESCRIZIONE;
   FUNCTION GET_TIPO
/******************************************************************************
 NOME:        GET_TIPO
 DESCRIZIONE: Restituisce il tipo del gruppo.
 PARAMETRI:   p_gruppo: codice gruppo.
 RITORNA:     stringa varchar2.
 ECCEZIONI:   --
 ANNOTAZIONI: --
******************************************************************************/
   (p_gruppo IN VARCHAR2) RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_tipo VARCHAR2(1);
   BEGIN
      SELECT tipo_utente
        INTO d_tipo
        FROM UTENTI
       WHERE Utente = p_gruppo
         AND tipo_utente <> 'U'
      ;
      RETURN d_tipo;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         RETURN '';
      WHEN OTHERS THEN
         RAISE;
   END GET_TIPO;
   FUNCTION GET_CODICE
/******************************************************************************
 NOME:        GET_CODICE
 DESCRIZIONE: Restituisce il codice del gruppo data la descrizione.
 PARAMETRI:   p_gruppo: codice gruppo.
 RITORNA:     stringa varchar2.
 ECCEZIONI:   --
 ANNOTAZIONI: --
******************************************************************************/
   (p_descrizione IN VARCHAR2) RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_codice VARCHAR2(8);
   BEGIN
      SELECT Utente
        INTO d_codice
        FROM UTENTI
       WHERE nominativo = p_descrizione
         AND tipo_utente <> 'U'
      ;
      RETURN d_codice;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         RETURN '';
      WHEN OTHERS THEN
         RAISE;
   END GET_CODICE;
   FUNCTION GET_GRUPPO_LAVORO
/******************************************************************************
 NOME:        GET_GRUPPO_LAVORO
 DESCRIZIONE: Restituisce il  gruppo di lavoro del gruppo dato il codice.
 PARAMETRI:   p_gruppo: codice gruppo.
 RITORNA:     stringa varchar2.
 ECCEZIONI:   --
 ANNOTAZIONI: --
******************************************************************************/
   (p_gruppo IN VARCHAR2) RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_gruppo_lavoro VARCHAR2(8);
   BEGIN
      SELECT gruppo_lavoro
        INTO d_gruppo_lavoro
        FROM UTENTI
       WHERE Utente = p_gruppo
         AND tipo_utente <> 'U'
      ;
      RETURN d_gruppo_lavoro;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         RETURN '';
      WHEN OTHERS THEN
         RAISE;
   END GET_GRUPPO_LAVORO;
   PROCEDURE SET_DESCRIZIONE
/******************************************************************************
 NOME:        SET_DESCRIZIONE
 DESCRIZIONE: Aggiornamento del campo DESCRIZIONE col valore p_value.
 PARAMETRI:   p_ISTANZA:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
   ( p_gruppo IN UTENTI.Utente%TYPE
   , p_value  IN UTENTI.nominativo%TYPE)
   IS
   BEGIN
      UPDATE UTENTI
         SET nominativo = p_value
       WHERE Utente = p_gruppo
         AND tipo_utente <> 'U'
      ;
      IF SQL%ROWCOUNT < 1 THEN
         RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                                 , Afc_Error.modified_by_other_user_msg
                                 );
      END IF;
   END SET_DESCRIZIONE;
   PROCEDURE SET_STATO
/******************************************************************************
 NOME:        SET_STATO
 DESCRIZIONE: Aggiornamento del campo SET_STATO col valore p_value.
 PARAMETRI:   p_gruppo:       chiave.
              p_value:         valore da modificare.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
******************************************************************************/
   ( p_gruppo IN UTENTI.Utente%TYPE
   , p_value  IN UTENTI.STATO%TYPE)
   IS
   BEGIN
      UPDATE UTENTI
         SET stato = p_value
       WHERE Utente = p_gruppo
         AND tipo_utente <> 'U'
       AND stato IN ('A','U','S','R')
      ;
      IF SQL%ROWCOUNT < 1 THEN
         RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number
                                 , Afc_Error.modified_by_other_user_msg
                                 );
      END IF;
   END SET_STATO;
   PROCEDURE riempi_diac_eliminati
/******************************************************************************
 NOME:        riempi_diac_eliminati
 DESCRIZIONE: Riempie TabDiac (pl/sql table dichiarata come variabile del
              body) con i diritti di accesso che p_utente avrebbe ereditato da
              p_gruppo ma che sono stati esplicitamente eliminati.
 ARGOMENTI:   p_gruppo  IN     codice del gruppo.
              p_utente  IN     codice dell'utente o %
              tabDiac   IN OUT pl/sql table da riempire
 ECCEZIONI:   --
 ANNOTAZIONI: .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 5    23/04/2007 MM     A20741.0.0: creazione.
******************************************************************************/
   ( p_gruppo   IN     VARCHAR2
   , p_utente   IN     VARCHAR2
   , tabDiacDel IN OUT DiacDelTab)
   IS /* SLAVE_COPY */
      indice BINARY_INTEGER := 0;
   BEGIN
      if exists_gruppo(p_gruppo) = 0 then
         raise_application_error(-20999, 'Gruppo '''||p_gruppo||''' non presente.');
      end if;
      if p_utente <> '%' then
         if utente.exists_codice(p_utente) = 0 then
            raise_application_error(-20999, 'Utente '''||p_utente||''' non presente.');
         end if;
      end if;
      tabDiacDel.DELETE;
      /*-----------------------------------------------------
          Riempie TabDiac con tutti i diritti di accesso
          che p_utente avrebbe ereditato da p_gruppo ma
          che sono stati esplicitamente eliminati.
      -----------------------------------------------------*/
      FOR DIAC IN (SELECT DIAC.Istanza, DIAC.modulo, DIAC.ruolo
                     FROM DIRITTI_ACCESSO DIAC
                    WHERE DIAC.Utente  = p_gruppo)
      LOOP
         DECLARE
            d_check INTEGER;
            d_gruppo VARCHAR2(8);
         BEGIN
--DBMS_OUTPUT.PUT_LINE('riempi_diac_eliminati di '||p_gruppo);
            FOR uten IN (SELECT Utente
                           FROM utenti_gruppo
                          WHERE Gruppo  = p_gruppo
                            AND Utente LIKE p_utente
                         UNION
                         SELECT p_utente
                           FROM dual
                          WHERE p_utente <> '%')
            LOOP
               d_check := Diritto_Accesso.check_gruppo(p_gruppo, uten.Utente, DIAC.Istanza, DIAC.modulo);
--DBMS_OUTPUT.PUT_LINE('check_gruppo DI '||uten.Utente||': '||d_check);
               IF d_check = 0 THEN
                  -- diritto esiste in gruppo ma non in utente
                  BEGIN
                     SELECT Utente
                       INTO d_gruppo
                       FROM DIRITTI_ACCESSO
                      WHERE Gruppo  IS NULL
                        AND Istanza = DIAC.Istanza
                        AND modulo  = DIAC.modulo
                      START WITH Utente = p_gruppo
                    CONNECT BY PRIOR Gruppo = Utente
                     ;
                     indice := indice + 1;
                     tabDiacDel(indice).Utente   := uten.Utente;
                     tabDiacDel(indice).Istanza  := DIAC.Istanza;
                     tabDiacDel(indice).modulo   := DIAC.modulo;
                     tabDiacDel(indice).ruolo    := DIAC.ruolo;
                     tabDiacDel(indice).Gruppo   := d_gruppo;
--DBMS_OUTPUT.PUT_LINE(indice||' - Utente: '||uten.Utente||' Gruppo: '||d_gruppo);
                  EXCEPTION
                     WHEN OTHERS THEN
--DBMS_OUTPUT.PUT_LINE(SQLERRM);
                        NULL;
                  END;
               END IF;
            END LOOP;
         END;
      END LOOP;
   END riempi_diac_eliminati;
   FUNCTION exists_progetto
/******************************************************************************
 NOME:        exists_progetto
 DESCRIZIONE: Verifica l'esistenza del progetto passato.
 PARAMETRI:   p_ruolo       IN  codice progetto.
              p_like        IN  indica se considerare valido anche il %.
 RITORNA:     number:  1: esiste progetto;
                       0: non esiste progetto;
                 -1: errore.
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
 10  25/10/2011  SNeg Modificate le exits per performance
******************************************************************************/
   ( p_progetto  IN  VARCHAR2
   , p_like      IN  VARCHAR2
   ) RETURN NUMBER
   IS /* SLAVE_COPY */
      dReturn NUMBER;
   BEGIN
      dReturn := 1;
      if p_like = 'Y' then
         dReturn := 1;
      else
          BEGIN
             SELECT 1
               INTO dReturn
               FROM PROGETTI
              WHERE progetto = p_progetto
             ;
          EXCEPTION
             WHEN TOO_MANY_ROWS THEN
                dReturn := 1;
             WHEN NO_DATA_FOUND THEN
                dReturn := 0;
             WHEN OTHERS THEN
                dReturn := -1;
                RAISE;
          END;
       END IF;
      RETURN dReturn;
   END exists_progetto;
   FUNCTION exists_istanza
/******************************************************************************
 NOME:        exists_istanza
 DESCRIZIONE: Verifica l'esistenza dell'istanza passata.
 PARAMETRI:   p_istanza      IN  codice istanza.
              p_like         IN  indica se considerare valido anche il %.
 RITORNA:     number:  1: esiste istanza;
                       0: non esiste istanza;
                 -1: errore.
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
 10  25/10/2011  SNeg Modificate le exits per performance
******************************************************************************/
   ( p_istanza      IN  VARCHAR2
   , p_like         IN  VARCHAR2
   ) RETURN NUMBER
   IS /* SLAVE_COPY */
      dReturn NUMBER;
   BEGIN
      dReturn := 1;
       if p_like = 'Y' then
         dReturn := 1;
      else
          BEGIN
             SELECT 1
               INTO dReturn
               FROM AD4_ISTANZE
              WHERE Istanza =  p_istanza
             ;
          EXCEPTION
             WHEN TOO_MANY_ROWS THEN
                dReturn := 1;
             WHEN NO_DATA_FOUND THEN
                IF p_like <> 'Y' THEN
                   dReturn := 0;
                ELSE
                   dReturn := 1;
                END IF;
             WHEN OTHERS THEN
                dReturn := -1;
                RAISE;
          END;
        END IF;
      RETURN dReturn;
   END exists_istanza;
   FUNCTION exists_modulo
/******************************************************************************
 NOME:        exists_modulo
 DESCRIZIONE: Verifica l'esistenza del modulo passato.
 PARAMETRI:   p_modulo       IN  codice modulo.
              p_like         IN  indica se considerare valido anche il %.
 RITORNA:     number:  1: esiste modulo;
                       0: non esiste modulo;
                 -1: errore.
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
 10  25/10/2011  SNeg Modificate le exits per performance
******************************************************************************/
   ( p_modulo       IN  VARCHAR2
   , p_like         IN  VARCHAR2
   ) RETURN NUMBER
   IS /* SLAVE_COPY */
      dReturn NUMBER;
   BEGIN
      dReturn := 1;
       if p_like = 'Y' then
         dReturn := 1;
      else
          BEGIN
             SELECT 1
               INTO dReturn
               FROM MODULI
              WHERE modulo = p_modulo
             ;
          EXCEPTION
             WHEN TOO_MANY_ROWS THEN
                dReturn := 1;
             WHEN NO_DATA_FOUND THEN
                dReturn := 0;
             WHEN OTHERS THEN
                dReturn := -1;
                RAISE;
          END;
       END IF;
      RETURN dReturn;
   END exists_modulo;
   FUNCTION exists_utente
/******************************************************************************
 NOME:        exists_utente
 DESCRIZIONE: Verifica l'esistenza del modulo passato.
 PARAMETRI:   p_utente       IN  codice utente.
              p_like         IN  indica se considerare valido anche il %.
 RITORNA:     number:  1: esiste utente;
                       0: non esiste utente;
                 -1: errore.
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
 10  25/10/2011  SNeg Modificate le exits per performance
******************************************************************************/
   ( p_utente       IN  VARCHAR2
   , p_like         IN  VARCHAR2
   ) RETURN NUMBER
   IS /* SLAVE_COPY */
      dReturn NUMBER;
   BEGIN
      dReturn := 1;
       if p_like = 'Y' then
         dReturn := 1;
      else
          BEGIN
             SELECT 1
               INTO dReturn
               FROM UTENTI
              WHERE Utente      =  p_utente
                AND tipo_utente = 'U'
             ;
          EXCEPTION
             WHEN TOO_MANY_ROWS THEN
                dReturn := 1;
             WHEN NO_DATA_FOUND THEN
                dReturn := 0;
             WHEN OTHERS THEN
                dReturn := -1;
                RAISE;
          END;
        END IF;
      RETURN dReturn;
   END exists_utente;
   FUNCTION exists_gruppo
/******************************************************************************
 NOME:        exists_gruppo
 DESCRIZIONE: Verifica l'esistenza del gruppo passato.
 PARAMETRI:   p_gruppo       IN  codice gruppo.
 RITORNA:     number:  1: esiste gruppo;
                       0: non esiste gruppo;
                 -1: errore.
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
 2    03/11/2004 VA     Gestione tipo_utente=O (Organizzazione).
******************************************************************************/
   ( p_gruppo       IN  UTENTI.utente%TYPE
   ) RETURN NUMBER
   IS /* SLAVE_COPY */
      dReturn NUMBER;
   BEGIN
      dReturn := 1;
      BEGIN
         SELECT 1
           INTO dReturn
           FROM UTENTI
          WHERE Utente      = p_gruppo
--  3/11/2004
--        and tipo_utente = 'G'
          AND tipo_utente != 'U'
         ;
      EXCEPTION
         WHEN TOO_MANY_ROWS THEN
            dReturn := -1;
         WHEN NO_DATA_FOUND THEN
            dReturn := 0;
         WHEN OTHERS THEN
            dReturn := -1;
            RAISE;
      END;
      RETURN dReturn;
   END exists_gruppo;
   FUNCTION exists_ruolo
/******************************************************************************
 NOME:        exists_ruolo
 DESCRIZIONE: Verifica l'esistenza del ruolo passato.
 PARAMETRI:   p_ruolo IN  codice ruolo.
 RITORNA:     number:  1: esiste ruolo;
                       0: non esiste ruolo;
                 -1: errore.
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
******************************************************************************/
   ( p_ruolo  IN  VARCHAR2)
   RETURN NUMBER
   IS /* SLAVE_COPY */
      dReturn NUMBER;
   BEGIN
      dReturn := 1;
      BEGIN
         SELECT 1
           INTO dReturn
           FROM RUOLI
          WHERE ruolo = p_ruolo
         ;
      EXCEPTION
         WHEN TOO_MANY_ROWS THEN
            dReturn := 1;
         WHEN NO_DATA_FOUND THEN
            dReturn := 0;
         WHEN OTHERS THEN
            dReturn := -1;
            RAISE;
      END;
      RETURN dReturn;
   END exists_ruolo;
   PROCEDURE riempi_servizi
/******************************************************************************
 NOME:        riempi_servizi
 DESCRIZIONE: Riempie TabServizi (pl/sql table dichiarata come variabile del
              body) con i servizi a cui il gruppo e' abilitato.
              filtrati da p_modulo e p_istanza.
 ARGOMENTI:   p_gruppo  IN  codice del gruppo.
              p_modulo  IN  codice del modulo o %.
              p_istanza IN  codice dell'istanza o %.
 ECCEZIONI:   --
 ANNOTAZIONI: Essendo una procedure PRIVATA, da per scontato che i codici
              passati siano validi (perche' controllati precedentemente).
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
******************************************************************************/
   ( p_gruppo  IN     VARCHAR2
   , p_modulo  IN     VARCHAR2
   , p_istanza IN     VARCHAR2
   , tabDiac   IN OUT DiacTab)
   IS /* SLAVE_COPY */
      indice BINARY_INTEGER := 0;
   BEGIN
      /*-----------------------------------------------------
                         Svuota TabServizi.
      -----------------------------------------------------*/
     TabDiac.DELETE;
      /*-----------------------------------------------------
          Riempie TabServizi con tutti i servizi a cui
        il gruppo e' abilitato filtrati da p_modulo e
        p_istanza.
      -----------------------------------------------------*/
      FOR diac IN (SELECT i.progetto, d.Istanza, d.modulo, d.ruolo, d.note
                     FROM AD4_ISTANZE i, DIRITTI_ACCESSO d
                    WHERE d.Utente  =    p_gruppo
                      AND d.Istanza =    i.Istanza
                      AND d.modulo  LIKE p_modulo
                      AND d.Istanza LIKE p_istanza
                      -- NON posso testare se è conforme a situazione amministratore si-no
                  )
      LOOP
         indice                   := indice + 1;
         TabDiac(indice).progetto := diac.progetto;
         TabDiac(indice).Istanza  := diac.Istanza;
         TabDiac(indice).modulo   := diac.modulo;
         TabDiac(indice).ruolo    := diac.ruolo;
         TabDiac(indice).note     := diac.note;
      END LOOP;
   END riempi_servizi;
   PROCEDURE riempi_utenti
/******************************************************************************
 NOME:        riempi_utenti
 DESCRIZIONE: Riempie TabUtente (pl/sql table dichiarata come variabile del
              body) con gli utenti del gruppo filtrati da p_utente.
           Vengono selezionati tutti gli utenti del gruppo oppure soltanto
           gli utenti che non abbiano personalizzato il diritto di accesso.
 ARGOMENTI:   p_gruppo   IN  codice del gruppo.
              p_utente   IN  codice dell' utente o %.
              p_modulo   IN  codice del modulo o %.
              p_istanza  IN  codice dell'istanza o %.
           p_modifica IN  indica se oggetto della modifica devranno essere
                          tutti gli utenti del gruppo (valore del parametro:
                      'T') oppure dovranno essere esclusi gli utenti con
                      diritto di accesso personalizzato (valore del
                      parametro: 'E').
 ECCEZIONI:   --
 ANNOTAZIONI: Essendo una procedure PRIVATA, da per scontato che i codici
              passati siano validi (perche' controllati precedentemente).
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
******************************************************************************/
   ( p_gruppo   IN     VARCHAR2
   , p_utente   IN     VARCHAR2
   , p_modulo   IN     VARCHAR2
   , p_istanza  IN     VARCHAR2
   , p_modifica IN     VARCHAR2
   , tabUtenti  IN OUT UtentiTab)
   IS /* SLAVE_COPY */
      indice BINARY_INTEGER := 0;
      v_tipo_utente VARCHAR2(1);
   BEGIN
     TabUtenti.DELETE;
      /*-----------------------------------------------------
          Riempie TabUtente con tutti gli utenti del gruppo
        filtrati da p_utente.
      -----------------------------------------------------*/
      IF p_utente <> '%' and p_modifica = 'I' THEN
      -- questo serve nel caso di inserimento di un utente nel
      -- gruppo (momento in cui entrambe le select sottostanti
      -- fallirebbero).
         TabUtenti(1).Utente  := p_utente;
         SELECT tipo_utente
           INTO v_tipo_utente
           FROM UTENTI
          WHERE Utente = p_utente
         ;
         TabUtenti(1).tipo_utente := v_tipo_utente;
      ELSE
         IF p_modifica in ('T', 'I') THEN
         -- seleziona tutti gli utenti del gruppo
            FOR uten IN (SELECT utgr.Utente
                              , ute.tipo_utente
                           FROM UTENTI_GRUPPO utgr
                              , UTENTI        ute
                          WHERE utgr.Gruppo  =   p_gruppo
                            AND utgr.Utente LIKE p_utente
                            AND utgr.Utente  =   ute.Utente)
            LOOP
               indice                   := indice + 1;
               TabUtenti(indice).Utente := uten.Utente;
               TabUtenti(indice).tipo_utente := uten.tipo_utente;
            END LOOP;
         ELSIF p_modifica = 'E' THEN
         -- esclusione degli utenti che hanno il diritto di accesso personalizzato.
            FOR uten IN (SELECT diac.Utente
                              , uten.tipo_utente
                           FROM DIRITTI_ACCESSO diac
                              , UTENTI uten
                          WHERE diac.Gruppo  =    p_gruppo
                            AND diac.Istanza LIKE p_istanza
                            AND diac.modulo  LIKE p_modulo
                            AND diac.Utente  LIKE p_utente
                            AND diac.Utente  =    uten.Utente)
            LOOP
               indice                   := indice + 1;
               TabUtenti(indice).Utente := uten.Utente;
               TabUtenti(indice).tipo_utente := uten.tipo_utente;
            END LOOP;
         END IF;
     END IF;
   END riempi_utenti;
   FUNCTION caac_getinfo
/******************************************************************************
 NOME:        CAAC_GETINFO
 DESCRIZIONE: Verifica se esistono delle caratteristiche di accesso definite
              per il gruppo relativamente al modulo, istanza e progetto passati.
 PARAMETRI:   p_progetto       IN  codice progetto.
              p_istanza        IN  codice istanza.
              p_modulo         IN  codice modulo.
              p_gruppo         IN  codice gruppo.
              p_accesso        OUT accesso definito: I (Inibito), U (Unico),
                                S (Segnalato), L (Libero).
              p_accesso_se     OUT accesso senza eccezioni (SI/NO).
              p_traccia        OUT traccia degli accessi (M - Modulo, F Funzione).
              p_giorni_traccia OUT numero di giorni per cui viene mantenuta la
                                traccia.
              p_tentativi_pwd  OUT numero di tentativi permessi prima della
                                disabilitazione della password.
              p_validita_pwd   OUT numero di giorni di validita' della password.
              p_sleep          OUT numero di minuti di inattivita' dopo cui
                                l'applicazione si disconnette.
              p_SSO            OUT abilitazione al Single Sign On.
              p_ldap           OUT autenticazione tramite LDAP.
              p_lunghezza_pwd  OUT minima lunghezza password
              p_mod_primo_uso  OUT modifica primo uso
 RITORNA:     number:  1: esistono caratteristiche di accesso per il gruppo;
                       0: non esistono caratteristiche di accesso per il gruppo;
                 -1: errore.
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
******************************************************************************/
   ( p_progetto       IN  VARCHAR2
   , p_istanza        IN  VARCHAR2
   , p_modulo         IN  VARCHAR2
   , p_gruppo         IN  VARCHAR2
   , p_accesso        OUT VARCHAR2
   , p_accesso_se     OUT VARCHAR2
   , p_traccia        OUT VARCHAR2
   , p_giorni_traccia OUT NUMBER
   , p_tentativi_pwd  OUT NUMBER
   , p_validita_pwd   OUT NUMBER
   , p_sleep          OUT NUMBER
   , p_SSO            OUT VARCHAR2
   , p_ldap           OUT VARCHAR2
   , p_lunghezza_pwd  OUT NUMBER
   , p_mod_pwd_primo_accesso  OUT VARCHAR2
   , p_amm_car_speciali OUT VARCHAR2
   , p_num_obb_pwd OUT VARCHAR2) RETURN NUMBER
   IS /* SLAVE_COPY */
      dReturn NUMBER(1);
   BEGIN
      p_accesso        := TO_CHAR(NULL);
      p_accesso_se     := TO_CHAR(NULL);
      p_traccia        := TO_CHAR(NULL);
      p_giorni_traccia := TO_NUMBER(NULL);
      p_tentativi_pwd  := TO_NUMBER(NULL);
      p_validita_pwd   := TO_NUMBER(NULL);
      p_sleep          := TO_NUMBER(NULL);
      p_SSO            := TO_CHAR(NULL);
      p_ldap           := TO_CHAR(NULL);
      p_lunghezza_pwd  := 0;
      dReturn          := 1;
      BEGIN
         SELECT Accesso, accesso_se, traccia, giorni_traccia, tentativi_password,
                validita_password, sleep, single_sign_on, ldap,
                min_lunghezza_pwd, mod_pwd_primo_accesso, ammessi_car_speciali_pwd, numeri_obb_pwd
           INTO p_accesso, p_accesso_se, p_traccia, p_giorni_traccia, p_tentativi_pwd,
                p_validita_pwd, p_sleep, p_SSO, p_ldap,
                p_lunghezza_pwd, p_mod_pwd_primo_accesso,
                p_amm_car_speciali, p_num_obb_pwd
           FROM CARATTERISTICHE_ACCESSO
          WHERE tipo_accesso = 'G'
            AND progetto     = p_progetto
            AND Istanza      = p_istanza
            AND modulo       = p_modulo
            AND Utente       = p_gruppo
         ;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            dReturn := 0;
         WHEN OTHERS THEN
            dReturn := -1;
            RAISE;
      END;
      RETURN dReturn;
   END caac_getinfo;
   FUNCTION is_diac_del
/******************************************************************************
 NOME:        is_diac_del
 DESCRIZIONE: .
 ARGOMENTI:   p_gruppo:   codice gruppo.
              p_utente:   codice utente.
              p_modulo:   codice modulo.
              p_istanza:  codice istanza.
              p_ruolo:    codice ruolo.
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 5    23/04/2007 MM     A20741.0.0 Creazione
******************************************************************************/
   ( p_tabDiacDel IN DiacDelTab
   , p_utente     IN VARCHAR2
   , p_modulo     IN VARCHAR2
   , p_istanza    IN VARCHAR2
   , p_ruolo      IN VARCHAR2
   ) return number
   IS /* SLAVE_COPY */
      d_return   integer := 0;
      i          integer;
   BEGIN
   --dbms_output.put_line('is_diac_del quanti record? '||NVL(p_tabDiacDel.LAST,0));
      FOR i IN NVL(p_tabDiacDel.FIRST,1)..NVL(p_tabDiacDel.LAST,0) LOOP
          if  p_tabDiacDel(i).utente = p_utente
          and p_tabDiacDel(i).modulo = p_modulo
          and p_tabDiacDel(i).istanza = p_istanza
          and p_tabDiacDel(i).ruolo = p_ruolo
          then
            d_return := 1;
            EXIT;
           end if;
      END LOOP;
      --dbms_output.put_line('is_diac_del: '||d_return);
      return d_return;
   END is_diac_del;
   FUNCTION IS_RUOLO
   /******************************************************************************
    NOME:        IS_RUOLO
    DESCRIZIONE: Verifica se il gruppo e' associato ad un ruolo.
    PARAMETRI:   p_gruppo varchar2 codice gruppo da verificare.
    RITORNA:     number: 1 se e' un ruolo, 0 altrimenti
    ANNOTAZIONI: --
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
       4  29/12/2006 MM     Prima emissione.
   ******************************************************************************/
   ( p_gruppo  VARCHAR2)
   RETURN NUMBER IS /* SLAVE_COPY */
      d_return   NUMBER := 0;
   BEGIN
      SELECT 1
        INTO d_return
        FROM UTENTI uten
       WHERE uten.tipo_utente = 'O'
         AND uten.Utente = p_gruppo
         AND EXISTS (SELECT 1
                       FROM RUOLI ruol
                      WHERE ruol.ruolo = NVL(uten.gruppo_lavoro, 'def')
                        AND NVL(ruol.gruppo_so, 'N') = 'S' )
      ;
      RETURN d_return;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         RETURN 0;
   END IS_RUOLO;
FUNCTION exists_descrizione
( p_descrizione  IN UTENTI.nominativo%TYPE
) RETURN NUMBER IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        exists_descrizione
 DESCRIZIONE: Esistenza riga con nominativo indicato.
 PARAMETRI:   p_username username utente.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
******************************************************************************/
   d_result NUMBER;
BEGIN
   BEGIN
      SELECT 1
      INTO   d_result
      FROM   UTENTI
      WHERE  nominativo = p_descrizione
        AND  tipo_utente <> 'U'
      ;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         d_result := 0;
   END;
   RETURN  d_result;
END exists_descrizione;
   PROCEDURE DIAC_CAAC_GRUPPO_DELETE
/******************************************************************************
 NOME:        DIAC_CAAC_GRUPPO_DELETE
 DESCRIZIONE: Toglie agli utenti del gruppo i diritti e le caratteristiche di
              accesso derivati dall'appartenenza al gruppo.
              Per ogni servizio a cui il gruppo e' abilitato (filtrati da
              p_modulo e p_istanza):
                  - Per ogni utente del gruppo abilitato al servizio (tutti oppure
                  ad esclusione di quelli che hanno diritto di accesso personalizzato)
                  filtrati da p_utente:
                     elimina gli eventuali diritti e caratteristiche di accesso esistenti.
 ARGOMENTI:   p_gruppo:    codice gruppo
              p_utente:    codice utente o %.
              p_modulo:    codice modulo o %.
              p_istanza:   codice istanza o %.
              p_modifica:  indica se oggetto della modifica devranno essere
                           tutti gli utenti del gruppo (valore del parametro:
                           'T') oppure dovranno essere esclusi gli utenti con
                           diritto di accesso personalizzato (valore del
                           parametro: 'E').
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 5    23/04/2007 MM     A20741.0.0: creazione.
15    23/12/2019 SNeg   Compatibilita con versione 10.20.0.3 Bug #41150
******************************************************************************/
   ( p_gruppo    IN VARCHAR2
   , p_utente    IN VARCHAR2
   , p_modulo    IN VARCHAR2
   , p_istanza   IN VARCHAR2
   , p_modifica  IN VARCHAR2
   , p_diac_caac IN VARCHAR2
   )
   IS
      d_gruppo      VARCHAR2(8);
      tabDiac    DiacTab;
      tabUtenti  UtentiTab;
      TabDiacDel DiacDelTab;
      dRegistroVerificaAmm varchar2(2) :=  global_utility.get_registro_amministratore; -- rev. 13
   BEGIN
--dbms_output.put_line('DIAC_CAAC_GRUPPO_DELETE '||p_gruppo||', '||p_utente||', '||p_modulo||', '||p_istanza||', '||p_modifica);
      /*-----------------------------------------------------
                   Verifica esistenza gruppo.
      -----------------------------------------------------*/
      IF exists_gruppo(p_gruppo) <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Gruppo inesistente ('''||p_gruppo||''').');
      END IF;
      /*-----------------------------------------------------
                   Verifica esistenza utente.
      -----------------------------------------------------*/
      IF exists_utente(p_utente,'Y') <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Utente inesistente ('''||p_utente||''').');
      END IF;
      /*-----------------------------------------------------
                   Verifica esistenza modulo.
      -----------------------------------------------------*/
      IF exists_modulo(p_modulo,'Y') <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Modulo inesistente ('''||p_modulo||''').');
      END IF;
      /*-----------------------------------------------------
                   Verifica esistenza istanza.
      -----------------------------------------------------*/
      IF exists_istanza(p_istanza,'Y') <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Istanza inesistente ('''||p_istanza||''').');
      END IF;
      /*-----------------------------------------------------
            Verifica correttezza parametro p_modifica.
      -----------------------------------------------------*/
      IF p_modifica NOT IN ('T','E') THEN
         RAISE_APPLICATION_ERROR(-20999,'Valore errato parametro p_modifica (Valori Possibili: ''T'' oppure ''E'').');
      END IF;
      /*-----------------------------------------------------
            Verifica correttezza parametro p_diac_caac.
      -----------------------------------------------------*/
      IF p_diac_caac NOT IN ('D','C','E') THEN
         RAISE_APPLICATION_ERROR(-20999,'Valore errato parametro p_diac_caac (Valori Possibili: ''D'', ''C'' oppure ''E'').');
      END IF;
      /*-----------------------------------------------------
          Riempie TabDiacDel con tutti i diritti di
          accesso del gruppo a cui l'utente, pero', non e'
          abilitato.
      -----------------------------------------------------*/
      riempi_diac_eliminati(p_gruppo, p_utente, TabDiacDel);
      /*-----------------------------------------------------
          Riempie tabDiac con tutti i servizi a cui il
          gruppo e' abilitato filtrati da p_modulo e
          p_istanza.
      -----------------------------------------------------*/
      riempi_servizi(p_gruppo, p_modulo, p_istanza, tabDiac);
      /*-----------------------------------------------------
          Per ogni servizio a cui il gruppo e' abilitato,
          riempie TabUtenti con tutti gli utenti abilitati
          filtrati da p_utente.
          Per tutti gli utenti cosi' selezionati, elimina
          le caratteristiche di accesso al servizio
          (tipo di accesso = 'D' o 'G').
      -----------------------------------------------------*/
--dbms_output.put_line('NVL(TabDiac.FIRST,0) '||NVL(TabDiac.FIRST,0));
--dbms_output.put_line('NVL(TabDiac.LAST,0) '||NVL(TabDiac.LAST,0));
      if NVL(TabDiac.LAST,0) = 0 and p_diac_caac = 'D' then -- arriva dall'eliminazione del diritto di accesso del gruppo
         TabDiac(1).modulo := p_modulo;
         TabDiac(1).istanza := p_istanza;
      end if;
      FOR i IN NVL(TabDiac.FIRST,1)..NVL(TabDiac.LAST,0) LOOP
        riempi_utenti( p_gruppo, p_utente, TabDiac(i).modulo
                    , TabDiac(i).Istanza, p_modifica, tabUtenti);
--dbms_output.put_line('NVL(TabUtenti.FIRST,0) '||NVL(TabUtenti.FIRST,0));
--Dbms_output.put_line('NVL(TabUtenti.LAST,0) '||NVL(TabUtenti.LAST,0));
         FOR j IN NVL(TabUtenti.FIRST,1)..NVL(TabUtenti.LAST,0) LOOP
            IF p_diac_caac IN ('D','E') THEN
               BEGIN
--dbms_output.put_line('TabDiac(i).Istanza, TabDiac(i).modulo, TabUtenti(j).Utente '||TabDiac(i).Istanza||', '||TabDiac(i).modulo||', '||TabUtenti(j).Utente);
                  DIRITTO_ACCESSO.DEL(TabDiac(i).Istanza, TabDiac(i).modulo, TabUtenti(j).Utente);
               EXCEPTION
                  WHEN OTHERS THEN
                     IF SQLCODE = Afc_Error.modified_by_other_user_number THEN
                        NULL;
                     END IF;
               END;
            END IF;
            IF p_diac_caac IN ('C','E') THEN
               BEGIN
                  CARATTERISTICA_ACCESSO.DEL('D', TabDiac(i).progetto, TabDiac(i).Istanza, TabDiac(i).modulo, TabUtenti(j).Utente);
               EXCEPTION
                  WHEN OTHERS THEN
                     IF SQLCODE = Afc_Error.modified_by_other_user_number THEN
                        NULL;
                     END IF;
               END;
            END IF;
         -- Assegno all'utente i diritti del gruppo con importanza maggiore
            DECLARE
               d_gruppo varchar2(8);
               d_ruolo  varchar2(8);
               -- rev. 15  inizio introdotte variabili
               d_get_tipo_utente utenti.tipo_utente%TYPE:= utente.get_tipo_utente(TabUtenti(j).Utente);
               d_utente_get_amministratore utenti.amministratore%TYPE := nvl(utente.get_amministratore(TabUtenti(j).Utente),'N');
               d_modulo_get_amministratore moduli.amministratore%TYPE := nvl(moduli_tpk.get_amministratore(TabDiac(i).Modulo),'N');
            BEGIN
--dbms_output.put_line('TabUtenti(j).Utente: '||TabUtenti(j).Utente);
--dbms_output.put_line('p_gruppo: '||p_gruppo);
--dbms_output.put_line('TabDiac(i).Istanza: '||TabDiac(i).Istanza);
--dbms_output.put_line('TabDiac(i).modulo: '||TabDiac(i).modulo);
               SELECT substr(min(lpad (to_char (nvl (uten.importanza, 99)), 2, '0') || utgr.gruppo),3)
                 INTO d_gruppo
                 FROM UTENTI_GRUPPO utgr, UTENTI uten, diritti_accesso diac
                WHERE uten.Utente = utgr.Gruppo
                  AND utgr.Utente = TabUtenti(j).Utente
                  and utgr.gruppo <> p_gruppo
                  and diac.utente = utgr.GRUPPO
                  and diac.ISTANZA = TabDiac(i).Istanza
                  and diac.modulo = TabDiac(i).modulo
--                  -- rev 12 Inizio
                  and (
                  ((d_utente_get_amministratore= d_modulo_get_amministratore --rev. 15
                        and dRegistroVerificaAmm != 'no')
                        OR dRegistroVerificaAmm = 'no'
                       )
                       OR
                        d_get_tipo_utente != 'U' ) -- rev.15
                  -- rev 12 Fine
               ;
               if d_gruppo is not null then
                  d_ruolo := diritto_accesso.GET_RUOLO(d_gruppo, TabDiac(i).modulo, TabDiac(i).istanza);
                  if is_diac_del(TabDiacDel, TabUtenti(j).Utente, TabDiac(i).modulo, TabDiac(i).Istanza, d_ruolo) = 0 then
                     IF p_diac_caac IN ('D','E') THEN
                        Gruppo.DIAC_GRUPPO_INSERT(d_gruppo, TabUtenti(j).Utente, TabDiac(i).modulo, TabDiac(i).Istanza, 'E');
                     END IF;
                     IF p_diac_caac IN ('C','E') THEN
                        Gruppo.CAAC_GRUPPO_INSERT(d_gruppo, TabUtenti(j).Utente, TabDiac(i).modulo, TabDiac(i).Istanza, 'E');
                     END IF;
                  end if;
               end if;
            END;
         END LOOP;
      END LOOP;
   END DIAC_CAAC_GRUPPO_DELETE;
   PROCEDURE CAAC_GRUPPO_DELETE
/******************************************************************************
 NOME:        CAAC_GRUPPO_DELETE
 DESCRIZIONE: Toglie agli utenti del gruppo le caratteristiche di accesso
              derivate dall'appartenenza al gruppo.
              Per ogni servizio a cui il gruppo e' abilitato (filtrati da
           p_modulo e p_istanza):
            - Per ogni utente del gruppo abilitato al servizio (tutti oppure
             ad esclusione di quelli che hanno diritto di accesso personalizzato)
            filtrati da p_utente:
                elimina le eventuali caratteristiche di accesso esistenti.
 ARGOMENTI:   p_gruppo:   codice gruppo
              p_utente:   codice utente o %.
              p_modulo:   codice modulo o %.
              p_istanza:  codice istanza o %.
              p_modifica: indica se oggetto della modifica devranno essere
                       tutti gli utenti del gruppo (valore del parametro:
                     'T') oppure dovranno essere esclusi gli utenti con
                    diritto di accesso personalizzato (valore del
                    parametro: 'E').
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
 2    03/11/2004 VA     Gestione tipo_utente=O (Organizzazione), eliminando
                        controllo su tipo_utente = G
 5    23/04/2007 MM     A20741.0.0: spostato codice in diac_caac_gruppo_delete.
******************************************************************************/
   ( p_gruppo   IN VARCHAR2
   , p_utente   IN VARCHAR2
   , p_modulo   IN VARCHAR2
   , p_istanza  IN VARCHAR2
   , p_modifica IN VARCHAR2
   )
   IS
   BEGIN
      DIAC_CAAC_GRUPPO_DELETE( p_gruppo, p_utente, p_modulo, p_istanza, p_modifica, 'C');
   END CAAC_GRUPPO_DELETE;
   PROCEDURE CAAC_GRUPPO_INSERT
/******************************************************************************
 NOME:        CAAC_GRUPPO_INSERT
 DESCRIZIONE: Associa agli utenti del gruppo le caratteristiche di accesso
              derivate dall'appartenenza al gruppo.
              Per ogni servizio a cui il gruppo e' abilitato (filtrati da
           p_modulo e p_istanza):
            - verifica se il gruppo ha delle caratteristiche di accesso
             personalizzate;
            - Per ogni utente del gruppo abilitato al servizio (tutti oppure
             ad esclusione di quelli che hanno diritto di accesso personalizzato)
            filtrati da p_utente:
              se il gruppo ha delle caratteristiche di accesso personalizzate,
                associa all'utente le caratteristiche di accesso del gruppo
               (inserisce o aggiorna quelle eventualmente esistenti);
             altrimenti,
                elimina le eventuali caratteristiche di accesso esistenti.
 ARGOMENTI:   p_gruppo:   codice gruppo
              p_utente:   codice utente o %.
              p_modulo:   codice modulo o %.
              p_istanza:  codice istanza o %.
              p_modifica: indica se oggetto della modifica devranno essere
                       tutti gli utenti del gruppo (valore del parametro:
                    'T') oppure dovranno essere esclusi gli utenti con
                    diritto di accesso personalizzato (valore del
                    parametro: 'E').
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
******************************************************************************/
   ( p_gruppo   IN VARCHAR2
   , p_utente   IN VARCHAR2
   , p_modulo   IN VARCHAR2
   , p_istanza  IN VARCHAR2
   , p_modifica IN VARCHAR2
   )
   IS
      dCaacId        NUMBER;
      dAccesso       VARCHAR2(1);
      dAccessoSe     VARCHAR2(2);
      dTraccia       VARCHAR2(1);
      dGiorniTraccia INTEGER;
      dTentativiPwd  INTEGER;
      dValiditaPwd   INTEGER;
      dSleep         NUMBER;
      dSSO           VARCHAR2(2);
      dLdap          VARCHAR2(2);
      dMinLunghezzaPwd  INTEGER;
      dModPwdPrimoAccesso VARCHAR2(2);
      d_amm_car_speciali_pwd varchar2(2);
      d_numeri_obb_pwd varchar2(2);
      dEsisteCaac    BOOLEAN;
      tabDiac        DiacTab;
      tabUtenti      UtentiTab;
      vTipoUtente    VARCHAR2(1);
     dDiacPers      NUMBER(1) := 0;
   BEGIN
dbms_output.put_line('ecco');
      /*-----------------------------------------------------
                   Verifica esistenza gruppo.
      -----------------------------------------------------*/
     IF exists_gruppo(p_gruppo) <> 1 THEN
        RAISE_APPLICATION_ERROR(-20999,'Gruppo inesistente ('''||p_gruppo||''').');
     END IF;
      /*-----------------------------------------------------
                   Verifica esistenza utente.
      -----------------------------------------------------*/
     IF exists_utente(p_utente,'Y') <> 1 THEN
        RAISE_APPLICATION_ERROR(-20999,'Utente inesistente ('''||p_utente||''').');
     END IF;
      /*-----------------------------------------------------
                   Verifica esistenza modulo.
      -----------------------------------------------------*/
     IF exists_modulo(p_modulo,'Y') <> 1 THEN
        RAISE_APPLICATION_ERROR(-20999,'Modulo inesistente ('''||p_modulo||''').');
     END IF;
      /*-----------------------------------------------------
                   Verifica esistenza istanza.
      -----------------------------------------------------*/
     IF exists_istanza(p_istanza,'Y') <> 1 THEN
        RAISE_APPLICATION_ERROR(-20999,'Istanza inesistente ('''||p_istanza||''').');
     END IF;
      /*-----------------------------------------------------
            Verifica correttezza parametro p_modifica.
      -----------------------------------------------------*/
     IF p_modifica NOT IN ('T','E') THEN
        RAISE_APPLICATION_ERROR(-20999,'Valore errato parametro p_modifica (Valori Possibili: ''T'' oppure ''E'').');
     END IF;
      /*-----------------------------------------------------
            Servizi a cui il gruppo e' abilitato
         (filtrati da p_modulo e p_istanza).
      -----------------------------------------------------*/
      riempi_servizi(p_gruppo, p_modulo, p_istanza, TabDiac);
      /*-----------------------------------------------------
          Per ogni servizio a cui il gruppo e' abilitato,
        riempie TabUtenti con tutti gli utenti abilitati
        filtrati da p_utente.
        Per tutti gli utenti cosi' selezionati, elimina
        le caratteristiche di accesso al servizio per
        tipo di accesso = 'D'.
      -----------------------------------------------------*/
      FOR i IN NVL(TabDiac.FIRST,1)..NVL(TabDiac.LAST,0) LOOP
         /*-----------------------------------------------------
              Verifica se il gruppo ha delle caratteristiche
               di accesso presonalizzate.
         -----------------------------------------------------*/
         dEsisteCaac := CAAC_GETINFO( TabDiac(i).Progetto, TabDiac(i).Istanza
                           , TabDiac(i).Modulo, p_gruppo, dAccesso, dAccessoSe
                           , dTraccia, dGiorniTraccia, dTentativiPwd
                           , dValiditaPwd, dSleep, dSSO, dLdap, dMinLunghezzaPwd
                           , dModPwdPrimoAccesso, d_amm_car_speciali_pwd, d_numeri_obb_pwd) = 1;
         /*-----------------------------------------------------
              Riempie TabUtenti con gli utenti del gruppo
               filtrati da p_utente.
         -----------------------------------------------------*/
         riempi_utenti( p_gruppo, p_utente, '%', '%', p_modifica, tabUtenti);
         /*-----------------------------------------------------
            Per ogni utente:
                se il gruppo ha delle caratteristiche di accesso
            personalizzate, associa all'utente le
            caratteristiche di accesso del gruppo (inserisce o
            aggiorna quelle eventualmente esistenti);
            altrimenti,
               elimina le eventuali caratteristiche di accesso
            esistenti.
         -----------------------------------------------------*/
         FOR j IN NVL(TabUtenti.FIRST,1)..NVL(TabUtenti.LAST,0) LOOP
            IF TabUtenti(j).tipo_utente != 'U' THEN
               vTipoUtente := 'G';
            ELSE
               vTipoUtente := 'D';
            END IF;
            SELECT COUNT(1)
              INTO dDiacPers
              FROM DIRITTI_ACCESSO
             WHERE NVL(Gruppo, 'xxx') <> p_gruppo
               AND Utente = TabUtenti(j).Utente
               AND modulo = TabDiac(i).modulo
               AND Istanza = TabDiac(i).Istanza
            ;
dbms_output.put_line(dDiacPers||' '||p_gruppo||' '||TabUtenti(j).Utente||' '||TabDiac(i).modulo||' '||TabDiac(i).istanza);
            IF dDiacPers = 0 or p_modifica = 'T' THEN
               IF dEsisteCaAc THEN
                  BEGIN
                     dCaacId := Si4.NEXT_ID('CARATTERISTICHE_ACCESSO','CAAC_ID');
                     INSERT INTO CARATTERISTICHE_ACCESSO ( caac_id, tipo_accesso, progetto
                                                         , Istanza, modulo, Utente, Accesso
                                                         , accesso_se, traccia, giorni_traccia
                                                         , tentativi_password, validita_password
                                                         , sleep, single_sign_on, ldap
                                                         , min_lunghezza_pwd, mod_pwd_primo_accesso
                                                         , ammessi_car_speciali_pwd, numeri_obb_pwd)
                                                  VALUES ( dCaacId, vTipoUtente, TabDiac(i).progetto
                                                         , TabDiac(i).Istanza, TabDiac(i).Modulo
                                                         , TabUtenti(j).Utente, dAccesso
                                                         , dAccessoSe, dTraccia, dGiorniTraccia
                                                         , dtentativiPwd, dvaliditaPwd
                                                         , dSleep, dSSO, dLdap
                                                         , dMinLunghezzaPwd, dModPwdPrimoAccesso
                                                         , d_amm_car_speciali_pwd, d_numeri_obb_pwd)
                     ;
                  EXCEPTION WHEN OTHERS THEN
                     UPDATE CARATTERISTICHE_ACCESSO
                        SET Accesso               = daccesso,
                            accesso_se            = daccessoSe,
                            traccia               = dtraccia,
                            giorni_traccia        = dgiorniTraccia,
                            tentativi_password    = dtentativipwd,
                            validita_password     = dvaliditapwd,
                            sleep                 = dSleep,
                            single_sign_on        = dSSO,
                            ldap                  = dLdap,
                            min_lunghezza_pwd     = dMinLunghezzaPwd,
                            mod_pwd_primo_accesso = dModPwdPrimoAccesso,
                            ammessi_car_speciali_pwd = d_amm_car_speciali_pwd,
                            numeri_obb_pwd        = d_numeri_obb_pwd
                      WHERE tipo_accesso = vTipoUtente
                        AND Utente       = TabUtenti(j).Utente
                        AND modulo       = TabDiac(i).modulo
                        AND Istanza      = TabDiac(i).Istanza
                        AND progetto     = TabDiac(i).progetto
                     ;
                  END;
               ELSE
                  DELETE CARATTERISTICHE_ACCESSO
                   WHERE tipo_accesso = vTipoUtente
                     AND Utente       = TabUtenti(j).Utente
                     AND modulo       = TabDiac(i).modulo
                     AND Istanza      = TabDiac(i).Istanza
                     AND progetto     = TabDiac(i).progetto;
               END IF;
            END IF;
         END LOOP;
      END LOOP;
   END CAAC_GRUPPO_INSERT;
   PROCEDURE CAAC_GRUPPO_UPDATE
/******************************************************************************
 NOME:        CAAC_GRUPPO_UPDATE
 DESCRIZIONE: Associa agli utenti del gruppo le caratteristiche di accesso
              del gruppo.
              Verifica se il gruppo ha delle caratteristiche di accesso
           personalizzate e se non sono quelle di default;
            se cosi' e'
              aggiorna le caratteristiche di accesso degli utenti del gruppo
             (tutti oppure ad esclusione di quelli che hanno diritto di
             accesso personalizzato);
           altrimenti,
              elimina le eventuali caratteristiche di accesso esistenti.
 ARGOMENTI:   p_gruppo:   codice gruppo
              p_modulo:   codice modulo.
              p_istanza:  codice istanza.
           p_progetto: codice progetto.
              p_modifica: indica se oggetto della modifica devranno essere
                       tutti gli utenti del gruppo (valore del parametro:
                    'T') oppure dovranno essere esclusi gli utenti con
                    diritto di accesso personalizzato (valore del
                    parametro: 'E').
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
******************************************************************************/
   ( p_gruppo   IN VARCHAR2
   , p_modulo   IN VARCHAR2
   , p_istanza  IN VARCHAR2
   , p_progetto IN VARCHAR2
   , p_modifica IN VARCHAR2
   )
   IS
      dAccesso   VARCHAR2(1);
      dAccessoSe VARCHAR2(2);
      dTraccia   VARCHAR2(1);
      dGiorniTraccia INTEGER;
      dTentativiPwd  INTEGER;
      dValiditaPwd   INTEGER;
      dSleep         NUMBER;
      dSSO           VARCHAR2(2);
      dLdap          VARCHAR2(2);
      dMinLunghezzaPwd INTEGER;
      dModPwdPrimoAccesso VARCHAR2(2);
      d_amm_car_speciali_pwd varchar2(2);
      d_numeri_obb_pwd varchar2(2);
      dEsisteCaac    BOOLEAN;
      dDefault       BOOLEAN;
      tabDiac        DiacTab;
      tabUtenti      UtentiTab;
   BEGIN
      /*-----------------------------------------------------
                   Verifica esistenza gruppo.
      -----------------------------------------------------*/
      IF exists_gruppo(p_gruppo) <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Gruppo inesistente ('''||p_gruppo||''').');
      END IF;
      /*-----------------------------------------------------
                   Verifica esistenza modulo.
      -----------------------------------------------------*/
      IF exists_modulo(p_modulo,'N') <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Modulo inesistente ('''||p_modulo||''').');
      END IF;
      /*-----------------------------------------------------
                   Verifica esistenza istanza.
      -----------------------------------------------------*/
      IF exists_istanza(p_istanza,'N') <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Istanza inesistente ('''||p_istanza||''').');
      END IF;
      /*-----------------------------------------------------
                   Verifica esistenza progetto.
      -----------------------------------------------------*/
      IF exists_progetto(p_progetto,'N') <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Progetto inesistente ('''||p_progetto||''').');
      END IF;
      /*-----------------------------------------------------
            Verifica correttezza parametro p_modifica.
      -----------------------------------------------------*/
      IF p_modifica NOT IN ('T','E') THEN
         RAISE_APPLICATION_ERROR(-20999,'Valore errato parametro p_modifica (Valori Possibili: ''T'' oppure ''E'').');
      END IF;
      /*-----------------------------------------------------
            Servizi a cui il gruppo e' abilitato
         (filtrati da p_modulo e p_istanza).
      -----------------------------------------------------*/
      riempi_servizi(p_gruppo, p_modulo, p_istanza, tabDiac);
      /*-----------------------------------------------------
        Per ogni servizio a cui il gruppo e' abilitato,
      seleziona gli utenti per cui deve essere effettuata
      la modifica e la esegue per ognuno di essi.
      -----------------------------------------------------*/
      FOR i IN NVL(TabDiac.FIRST,1)..NVL(TabDiac.LAST,0) LOOP
         /*-----------------------------------------------------
              Verifica se il gruppo ha delle caratteristiche
               di accesso personalizzate.
         -----------------------------------------------------*/
         dEsisteCaac := CAAC_GETINFO( TabDiac(i).Progetto, TabDiac(i).Istanza
                                    , TabDiac(i).Modulo, p_gruppo, dAccesso, dAccessoSe
                                    , dTraccia, dGiorniTraccia, dTentativiPwd
                                    , dValiditaPwd, dSleep, dSSO, dLdap
                                    , dMinLunghezzaPwd, dModPwdPrimoAccesso
                                    , d_amm_car_speciali_pwd, d_numeri_obb_pwd) = 1;
         /*-----------------------------------------------------
              Verifica se le caratteristiche di accesso sono
               quelle di default.
         -----------------------------------------------------*/
         dDefault    := dAccesso IS NULL AND dAccessoSe IS NULL AND dTraccia IS NULL AND
                        dGiorniTraccia IS NULL AND dTentativiPwd IS NULL AND
                        dValiditaPwd IS NULL AND dSleep IS NULL AND dSSO IS NULL AND
                        dLDap IS NULL AND dMinLunghezzaPwd= 0 AND dModPwdPrimoAccesso= 'NO';
         /*-----------------------------------------------------
              Riempie TabUtenti con gli utenti del gruppo.
         -----------------------------------------------------*/
         riempi_utenti( p_gruppo, '%', TabDiac(i).Modulo, TabDiac(i).Istanza, p_modifica, tabUtenti);
         /*-----------------------------------------------------
            Per ogni utente:
              se il gruppo ha delle caratteristiche di accesso
              personalizzate e non sono quelle di default,
                 aggiorna o inserisce (se non esistenti) le
                 caratteristiche di accesso degli utenti del
                 gruppo;
              altrimenti,
                 elimina le eventuali caratteristiche di
                 accesso esistenti.
         -----------------------------------------------------*/
         FOR j IN NVL(TabUtenti.FIRST,1)..NVL(TabUtenti.LAST,0) LOOP
            IF dEsisteCaAc AND (NOT dDefault) THEN
            DECLARE
               dEsisteCaac NUMBER(1);
               dCaacId     NUMBER;
               BEGIN
                  SELECT 1
                   INTO dEsisteCaac
                   FROM CARATTERISTICHE_ACCESSO
                  WHERE tipo_accesso = 'D'
                        AND Utente       = TabUtenti(j).Utente
                        AND modulo       = TabDiac(i).modulo
                        AND Istanza      = TabDiac(i).Istanza
                        AND progetto     = TabDiac(i).progetto
                     ;
               EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                     BEGIN
                        dCaacId := Si4.NEXT_ID('CARATTERISTICHE_ACCESSO','CAAC_ID');
                        INSERT INTO CARATTERISTICHE_ACCESSO
                                   ( caac_id, tipo_accesso, progetto, Istanza, modulo, Utente
                                   , Accesso, accesso_se, traccia, giorni_traccia
                                   , tentativi_password, validita_password, sleep
                                   , single_sign_on, ldap
                                   , min_lunghezza_pwd, mod_pwd_primo_accesso
                                   , ammessi_car_speciali_pwd, numeri_obb_pwd)
                            SELECT dCaacId, 'D', TabDiac(i).progetto, TabDiac(i).Istanza
                                   , TabDiac(i).Modulo, TabUtenti(j).Utente, dAccesso
                                   , dAccessoSe, dTraccia, dGiorniTraccia, dtentativiPwd
                                   , dvaliditaPwd, dSleep, dSSO, dLdap
                                   , dMinLunghezzaPwd, dModPwdPrimoAccesso
                                   , d_amm_car_speciali_pwd, d_numeri_obb_pwd
                              FROM DIRITTI_ACCESSO
                             WHERE Utente = TabUtenti(j).Utente
                               AND modulo = TabDiac(i).Modulo
                               AND Istanza = TabDiac(i).Istanza
                        ;
                     EXCEPTION WHEN OTHERS THEN
                        RAISE_APPLICATION_ERROR(-20999,'Errore inserimento delle caratteristiche di accesso dell''utente '||TabUtenti(j).Utente);
                     END;
               END;
               UPDATE CARATTERISTICHE_ACCESSO
                  SET Accesso               = dAccesso,
                      accesso_se            = dAccessoSe,
                      traccia               = dTraccia,
                      giorni_traccia        = dGiorniTraccia,
                      tentativi_password    = dTentativiPwd,
                      validita_password     = dValiditaPwd,
                      sleep                 = dSleep,
                      single_sign_on        = dSSO,
                      ldap                  = dLdap,
                      min_lunghezza_pwd     = dMinLunghezzaPwd,
                      mod_pwd_primo_accesso = dModPwdPrimoAccesso,
                      ammessi_car_speciali_pwd = d_amm_car_speciali_pwd,
                      numeri_obb_pwd        = d_numeri_obb_pwd
                WHERE tipo_accesso = 'D'
                  AND Utente       = TabUtenti(j).Utente
                  AND modulo       = TabDiac(i).modulo
                  AND Istanza      = TabDiac(i).Istanza
                  AND progetto     = TabDiac(i).progetto
               ;
            ELSE
               DELETE CARATTERISTICHE_ACCESSO
                WHERE tipo_accesso = 'D'
                  AND Utente       = TabUtenti(j).Utente
                  AND modulo       = TabDiac(i).modulo
                  AND Istanza      = TabDiac(i).Istanza
                  AND progetto     = TabDiac(i).progetto
               ;
            END IF;
       END LOOP;
      END LOOP;
   END CAAC_GRUPPO_UPDATE;
   PROCEDURE DIAC_GRUPPO_DELETE
/******************************************************************************
 NOME:        DIAC_GRUPPO_DELETE
 DESCRIZIONE: Toglie agli utenti del gruppo i diritti di accesso derivati
              dall'appartenenza al gruppo.
              Per ogni servizio a cui il gruppo e' abilitato (filtrati da
           p_modulo e p_istanza):
            - Per ogni utente del gruppo abilitato al servizio (tutti oppure
             ad esclusione di quelli che hanno diritto di accesso
            personalizzato)   filtrati da p_utente:
                elimina gli eventuali diritti di accesso esistenti.
 ARGOMENTI:   p_gruppo:   codice gruppo
              p_utente:   codice utente o %.
              p_modulo:   codice modulo o %.
              p_istanza:  codice istanza o %.
              p_modifica: indica se oggetto della modifica devranno essere
                       tutti gli utenti del gruppo (valore del parametro:
                    'T') oppure dovranno essere esclusi gli utenti con
                    diritto di accesso personalizzato (valore del
                    parametro: 'E').
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
 1    26/06/2003 MM     Se veniva eliminato un utente da un gruppo, eliminava
                        tutti i suoi diritti di accesso anche se non derivanti
                        dall'appartenenza ad un grupo.
 2    03/11/2004 VA     Gestione tipo_utente=O (Organizzazione), eliminando
                        controllo su tipo_utente = G
 3    03/11/2004 VA     Gestione del diritto d'accesso relativo al gruppo con
                        importanza piu alta (0 alta - 99 bassa).
5    23/04/2007 MM     A20741.0.0: spostato codice in diac_caac_gruppo_delete.
******************************************************************************/
   ( p_gruppo   IN VARCHAR2
   , p_utente   IN VARCHAR2
   , p_modulo   IN VARCHAR2
   , p_istanza  IN VARCHAR2
   , p_modifica IN VARCHAR2
   )
   IS
   BEGIN
      DIAC_CAAC_GRUPPO_DELETE( p_gruppo, p_utente, p_modulo, p_istanza, p_modifica, 'D');
   END DIAC_GRUPPO_DELETE;
   PROCEDURE DIAC_GRUPPO_INSERT
/******************************************************************************
 NOME:        DIAC_GRUPPO_INSERT
 DESCRIZIONE: Aggiunge agli utenti del gruppo i diritti di accesso derivati
              dall'appartenenza al gruppo.
              Per ogni servizio a cui il gruppo e' abilitato (filtrati da
           p_modulo e p_istanza):
            - Per ogni utente del gruppo abilitato al servizio (tutti oppure
             ad esclusione di quelli che hanno diritto di accesso
            personalizzato)   filtrati da p_utente:
                aggiunge o aggiorna (se esistenti) i diritti di accesso.
 ARGOMENTI:   p_gruppo:   codice gruppo
              p_utente:   codice utente o %.
              p_modulo:   codice modulo o %.
              p_istanza:  codice istanza o %.
              p_modifica: indica se oggetto della modifica devranno essere
                       tutti gli utenti del gruppo (valore del parametro:
                    'T') oppure dovranno essere esclusi gli utenti con
                    diritto di accesso personalizzato (valore del
                    parametro: 'E').
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
 1    18/11/2004 VA     Gestione del diritto d'accesso relativo al gruppo con
                       importanza piu alta (0 alta - 99 bassa).
 8    01/06/2010 SNeg   Calcolo dell'importanza considerando tutti i gruppi
                        attraverso i quali il diritto e ereditato.
                        I gruppi di struttura organizzativa ('O') hanno a parita
                        di importanza diritto maggiore.
 11  12/04/2017  SNeg Modificata assegnazione del gruppo x fare vincere quelli di tipo 'O'
 14 23/12/2019   SNeg Esaminare solo i gruppi Bug #34783
******************************************************************************/
   ( p_gruppo   IN VARCHAR2
   , p_utente   IN VARCHAR2
   , p_modulo   IN VARCHAR2
   , p_istanza  IN VARCHAR2
   , p_modifica IN VARCHAR2
   )
   IS
   tabDiac    DiacTab;
   tabUtenti  UtentiTab;
   dGruppo VARCHAR2(8);
   dNewImportanza NUMBER(2);
   dOldImportanza NUMBER(2);
   dNewTipoGruppo varchar2(1);
   dOldTipoGruppo varchar2(1);
   dNewDistanza   NUMBER(2);
   dOldDistanza   NUMBER(2);
   dOldNum number(2);
   v_num number;
   BEGIN
      dRegistroVerificaAmm :=   global_utility.get_registro_amministratore; -- rev. 13
      /*-----------------------------------------------------
                   Verifica esistenza gruppo.
      -----------------------------------------------------*/
     IF exists_gruppo(p_gruppo) <> 1 THEN
        RAISE_APPLICATION_ERROR(-20999,'Gruppo inesistente ('''||p_gruppo||''').');
     END IF;
      /*-----------------------------------------------------
                   Verifica esistenza utente.
      -----------------------------------------------------*/
     IF exists_utente(p_utente,'Y') <> 1 THEN
        RAISE_APPLICATION_ERROR(-20999,'Utente inesistente ('''||p_utente||''').');
     END IF;
      /*-----------------------------------------------------
                   Verifica esistenza modulo.
      -----------------------------------------------------*/
     IF exists_modulo(p_modulo,'Y') <> 1 THEN
        RAISE_APPLICATION_ERROR(-20999,'Modulo inesistente ('''||p_modulo||''').');
     END IF;
      /*-----------------------------------------------------
                   Verifica esistenza istanza.
      -----------------------------------------------------*/
     IF exists_istanza(p_istanza,'Y') <> 1 THEN
        RAISE_APPLICATION_ERROR(-20999,'Istanza inesistente ('''||p_istanza||''').');
     END IF;
      /*-----------------------------------------------------
            Verifica correttezza parametro p_modifica.
      -----------------------------------------------------*/
     IF p_modifica NOT IN ('T','E') THEN
        RAISE_APPLICATION_ERROR(-20999,'Valore errato parametro p_modifica (Valori Possibili: ''T'' oppure ''E'').');
     END IF;
      /*-----------------------------------------------------
            Servizi a cui il gruppo e' abilitato
         (filtrati da p_modulo e p_istanza).
      -----------------------------------------------------*/
      riempi_servizi(p_gruppo, p_modulo, p_istanza, tabDiac);
      FOR i IN NVL(TabDiac.FIRST,1)..NVL(TabDiac.LAST,0) LOOP
         /*-----------------------------------------------------
              Riempie TabUtenti con gli utenti del gruppo
               filtrati da p_utente.
         -----------------------------------------------------*/
        riempi_utenti( p_gruppo, p_utente, '%', '%', 'I', tabUtenti);
        /*-----------------------------------------------------
            Per ogni utente:
                associa all'utente i diritti di accesso del gruppo
            (inserisce o aggiorna quelle eventualmente
            esistenti);
         -----------------------------------------------------*/
        --DBMS_OUTPUT.PUT_LINE('dopo riempi_utenti');
         FOR j IN NVL(TabUtenti.FIRST,1)..NVL(TabUtenti.LAST,0) LOOP
         BEGIN
               SELECT Gruppo
                 INTO dGruppo
                 FROM DIRITTI_ACCESSO
                WHERE Utente  = TabUtenti(j).Utente
                  AND Istanza = TabDiac(i).Istanza
                  AND modulo  = TabDiac(i).Modulo
                  -- rev 12 Inizio
                  and ((((select nvl(utente.get_amministratore(TabUtenti(j).Utente),'N')from dual) = (select nvl(moduli_tpk.get_amministratore(TabDiac(i).Modulo),'N') from dual)
                        and dRegistroVerificaAmm != 'no')
                        OR dRegistroVerificaAmm = 'no'
                       )  or utente.get_tipo_utente(TabUtenti(j).Utente) != 'U' )
                  -- rev 12 Fine
            ;
         EXCEPTION WHEN NO_DATA_FOUND THEN
         if ((nvl(utente.get_amministratore(TabUtenti(j).Utente),'N') = nvl(moduli_tpk.get_amministratore(TabDiac(i).Modulo),'N')
                       and dRegistroVerificaAmm != 'no')
                        OR dRegistroVerificaAmm = 'no'
                       )
                    or utente.get_tipo_utente(TabUtenti(j).Utente) != 'U' then
                INSERT INTO DIRITTI_ACCESSO ( sequenza, Utente, modulo, Istanza, ruolo
                                            , note, Gruppo)
                    SELECT NVL(MAX(diac.sequenza),0) + 1, TabUtenti(j).Utente,
                           TabDiac(i).Modulo, TabDiac(i).Istanza, TabDiac(i).Ruolo,
                           TabDiac(i).Note, p_Gruppo
                      FROM DIRITTI_ACCESSO diac
                     WHERE diac.Utente(+) = TabUtenti(j).Utente
                    -- Controllato nella if prima di fare inserimento
--                  -- rev 12 Inizio
--                  and ((nvl(utente.get_amministratore(TabUtenti(j).Utente),'N') = nvl(moduli_tpk.get_amministratore(TabDiac(i).Modulo),'N')
--                       and dRegistroVerificaAmm != 'no')
--                        OR dRegistroVerificaAmm = 'no'
--                       )
--                  -- rev 12 Fine
               ;
               v_num := sql%ROWCOUNT;
               end if;
         END;
       --DBMS_OUTPUT.PUT_LINE('prima importanza');
       -- modifiche STEFANIA x calcolo importanza
          BEGIN
          -- rev. 14 inizio
 select nvl(min(UTENTE.get_importanza(ug.gruppo)),99) min_importanza
                  , max(decode( utente.get_tipo_utente(ug.gruppo),'O','O',null)) tipo_gruppo
                  , max(level) distanza
                  , count(*)
               into dNewImportanza
                  , dNewTipoGruppo
                  , dNewDistanza
                  , dOldNum
               from utenti_gruppo ug
              where 1 = 1
                and exists (select 1 from diritti_accesso diac
                                      where utente = ug.gruppo
                                         and modulo = TabDiac(i).Modulo
                                         and istanza = TabDiac(i).Istanza
                                         and ((((select nvl(utente.get_amministratore(ug.Utente),'N') from dual)  = (select nvl(moduli_tpk.get_amministratore(diac.Modulo),'N') from dual)
                                                    and dRegistroVerificaAmm != 'no')
                                                      OR dRegistroVerificaAmm = 'no'   )
                                                  OR utente.get_tipo_utente(ug.Utente) != 'U')
                       )
                and gruppo = p_gruppo
--              and ('AD4','AD4') in (select modulo, istanza
--                                        from diritti_accesso diac
--                                       where utente = ug.gruppo
--                                         and ((nvl(utente.get_amministratore(ug.Utente),'N') = nvl(moduli_tpk.get_amministratore(diac.Modulo),'N')
--                        and dRegistroVerificaAmm != 'no')
--                        OR dRegistroVerificaAmm = 'no'
--                       ))
            connect by prior ug.gruppo = ug.utente
              start with ug.utente = TabUtenti(j).Utente;
              -- rev. 14 fine
--DBMS_OUTPUT.PUT_LINE(to_char(sysdate,'dd/mm/yyyy hh24:mi:ss') || ' controllo:gruppo =' ||p_gruppo || ':modulo=' ||  TabDiac(i).Modulo
--|| ':istanza=' || TabDiac(i).Istanza || ':utente=' ||  TabUtenti(j).Utente);
         --DBMS_OUTPUT.PUT_LINE('dopo importanza');
            IF dGruppo IS NULL THEN
            -- Il gruppo e nullo quando il diritto e stato assegnato personalmente (eccezione)
            -- in questo caso l'importanza viene considerata alta(0).
              dOldImportanza := 0;
              dOldTipoGruppo := to_char(null);
              dOldDistanza := null;
            ELSE
               select nvl(min(UTENTE.get_importanza(gruppo)),99) min_importanza
                  , max(decode( utente.get_tipo_utente(gruppo),'O','O',null)) tipo_gruppo
                  , max(level) distanza
               into dOldImportanza
                  , dOldTipoGruppo
                  , dOldDistanza
               from diritti_accesso diri
              where gruppo = dGruppo
            connect by prior gruppo = utente
              start with utente = TabUtenti(j).Utente
               ;
            END IF;
         EXCEPTION
            WHEN OTHERS THEN
            NULL;
         END;
         IF (p_modifica = 'T' and dGruppo is null) -- prima non era assegnato altrimenti considero importanza
            OR dNewImportanza < dOldImportanza
            or (dNewImportanza = dOldImportanza and -- sono importanti uguali vince tipo 'O'
                nvl(dNewTipoGruppo,'x') = 'O' and nvl(dOldTipoGruppo,'x') != 'O' )
            or (dNewImportanza = dOldImportanza --sono importanti uguali e tipo'O' vince diritto piu vicino
                 and nvl(dNewTipoGruppo,'x') = 'O' and nvl(dOldTipoGruppo,'x') = 'O'
                 and dNewDistanza < dOldDistanza)
            -- Inizio Rev.11
            OR -- se il vecchio gruppo era nullo o non di tipo 'O' e il nuovo è 'O' assegno nuovo gruppo
              (nvl(dNewTipoGruppo,'x') = 'O' and
              (nvl(dOldTipoGruppo,'x') != 'O' or dGruppo is null))
            -- Fine Rev.11
             THEN
--DBMS_OUTPUT.PUT_LINE(TabUtenti(j).Utente||': '||p_modifica||' '||dNewImportanza ||' '||dOldImportanza);
               UPDATE DIRITTI_ACCESSO
                  SET Gruppo = p_gruppo,
                      ruolo  = TabDiac(i).Ruolo,
                      note   = TabDiac(i).Note
                WHERE Utente  = TabUtenti(j).Utente
                  AND Istanza = TabDiac(i).Istanza
                  AND modulo  = TabDiac(i).Modulo
               ;
            END IF;
            -- fine modifiche STEFANIA
         END LOOP;
      END LOOP;
     --DBMS_OUTPUT.PUT_LINE('fine');
   END DIAC_GRUPPO_INSERT;
   PROCEDURE DIAC_GRUPPO_UPDATE
/******************************************************************************
 NOME:        DIAC_GRUPPO_UPDATE
 DESCRIZIONE: Associa agli utenti del gruppo i diritti di accesso derivati
              dall'appartenenza al gruppo.
              Per ogni utente del gruppo abilitato al servizio (tutti oppure
           ad esclusione di quelli che hanno diritto di accesso
           personalizzato) filtrati da p_utente:
                aggiorna o aggiunge (se non esistenti) i diritti di accesso.
 ARGOMENTI:   p_gruppo:   codice gruppo.
              p_modulo:   codice modulo.
              p_istanza:  codice istanza.
           p_ruolo:    codice ruolo.
           p_note:     eventuali note associate al diritto di accesso.
              p_modifica: indica se oggetto della modifica devranno essere
                       tutti gli utenti del gruppo (valore del parametro:
                    'T') oppure dovranno essere esclusi gli utenti con
                    diritto di accesso personalizzato (valore del
                    parametro: 'E').
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/01/2003 MM     Creazione.
******************************************************************************/
   ( p_gruppo   IN VARCHAR2
   , p_modulo   IN VARCHAR2
   , p_istanza  IN VARCHAR2
   , p_ruolo    IN VARCHAR2
   , p_note     IN VARCHAR2
   , p_modifica IN VARCHAR2
   )
   IS
   tabUtenti  UtentiTab;
   BEGIN
      /*-----------------------------------------------------
                   Verifica esistenza gruppo.
      -----------------------------------------------------*/
      IF exists_gruppo(p_gruppo) <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Gruppo inesistente ('''||p_gruppo||''').');
      END IF;
      /*-----------------------------------------------------
                   Verifica esistenza modulo.
      -----------------------------------------------------*/
      IF exists_modulo(p_modulo,'N') <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Modulo inesistente ('''||p_modulo||''').');
      END IF;
      /*-----------------------------------------------------
                   Verifica esistenza istanza.
      -----------------------------------------------------*/
      IF exists_istanza(p_istanza,'N') <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Istanza inesistente ('''||p_istanza||''').');
      END IF;
     /*-----------------------------------------------------
                   Verifica esistenza ruolo.
      -----------------------------------------------------*/
      IF exists_ruolo(p_ruolo) <> 1 THEN
         RAISE_APPLICATION_ERROR(-20999,'Ruolo inesistente ('''||p_ruolo||''').');
      END IF;
      /*-----------------------------------------------------
            Verifica correttezza parametro p_modifica.
      -----------------------------------------------------*/
      IF p_modifica NOT IN ('T','E') THEN
         RAISE_APPLICATION_ERROR(-20999,'Valore errato parametro p_modifica (Valori Possibili: ''T'' oppure ''E'').');
      END IF;
      /*-----------------------------------------------------
           Riempie TabUtenti con gli utenti del gruppo.
      -----------------------------------------------------*/
      riempi_utenti( p_gruppo, '%', p_modulo, p_istanza, p_modifica, tabUtenti);
      /*-----------------------------------------------------
        Per ogni utente del gruppo abilitato al servizio
        (tutti oppure ad esclusione di quelli che hanno
        diritto di accesso personalizzato) filtrati da
        p_utente, aggiorna o aggiunge (se non esistenti)
        i diritti di accesso.
      -----------------------------------------------------*/
      FOR i IN NVL(TabUtenti.FIRST,1)..NVL(TabUtenti.LAST,0) LOOP
         UPDATE DIRITTI_ACCESSO
            SET Gruppo  = p_gruppo,
                ruolo   = p_ruolo,
                note    = p_note
          WHERE Utente  = TabUtenti(i).Utente
            AND modulo  = p_modulo
            AND Istanza = p_istanza
         ;
         IF p_modifica = 'T' THEN
            DECLARE
               dInsert NUMBER;
            BEGIN
               SELECT 1
                 INTO dInsert
                 FROM DIRITTI_ACCESSO
                WHERE Utente  = TabUtenti(i).Utente
                  AND Istanza = p_istanza
                  AND modulo  = p_modulo
               ;
             EXCEPTION WHEN NO_DATA_FOUND THEN
               INSERT INTO DIRITTI_ACCESSO ( sequenza, Utente, modulo, Istanza, ruolo
                                           , note, Gruppo)
                    SELECT NVL(MAX(diac.sequenza),0) + 1, TabUtenti(i).Utente,
                           p_modulo, p_istanza, p_ruolo, p_note, p_gruppo
                      FROM DIRITTI_ACCESSO diac
                     WHERE diac.Utente(+) = TabUtenti(i).Utente
               ;
             END;
         END IF;
      END LOOP;
   END DIAC_GRUPPO_UPDATE;
END Gruppo;
/

