--liquibase formatted sql

--changeset mturra:201901301231_135 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY Diritto_Accesso IS
   FUNCTION VERSIONE
/******************************************************************************
 NOME:        VERSIONE.
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 ECCEZIONI:   --
 ANNOTAZIONI: Il secondo numero della versione corrisponde alla revisione
              del package.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    30/06/2005 MM     Creazione.
 2    15/03/2010 SNeg   Nella esiste condizionata la rigenera_so
******************************************************************************/
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   BEGIN
      RETURN 'V1.2';
   END VERSIONE;
   FUNCTION GET_RUOLO
/******************************************************************************
 NOME:        GET_RUOLO.
 DESCRIZIONE: Restituisce il ruolo associato al diritto di accesso.
 PARAMETRI:   --
 RITORNA:     stringa varchar2.
 ECCEZIONI:   --
 ANNOTAZIONI: .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    30/06/2005 MM     Creazione.
******************************************************************************/
   ( p_utente IN VARCHAR2
   , p_modulo IN VARCHAR2
   , p_istanza IN VARCHAR2)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_ruolo VARCHAR2(8);
   BEGIN
      SELECT ruolo
        INTO d_ruolo
        FROM DIRITTI_ACCESSO
       WHERE Utente = p_utente
         AND modulo = p_modulo
         AND Istanza = p_istanza
      ;
      RETURN d_ruolo;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         RAISE_APPLICATION_ERROR(-20999,'Diritto inesistente! (Utente: '||p_utente||' - Modulo: '||p_modulo||' - Istanza: '||p_istanza||')');
      WHEN OTHERS THEN
         RAISE;
   END GET_RUOLO;
   PROCEDURE AGGIORNA_GRUPPO
/******************************************************************************
 NOME:        AGGIORNA_GRUPPO.
 DESCRIZIONE: Verifica se il diritto e le caratteristiche di accesso dell'utente
              o del gruppo sono identiche a quelle di uno dei gruppi a cui
           appartiene, se cosi' e' aggiorna il campo gruppo con il codice
              corrispondente.
 PARAMETRI:   --
 RITORNA:     stringa varchar2.
 ECCEZIONI:   --
 ANNOTAZIONI: .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    30/06/2005 MM     Creazione.
******************************************************************************/
   ( p_utente IN VARCHAR2
   , p_modulo IN VARCHAR2
   , p_istanza IN VARCHAR2)
   IS
      d_tipo_accesso_uten VARCHAR2(1);
      d_tipo_accesso_grup VARCHAR2(1);
      d_esiste_caac_uten  NUMBER(1);
      d_isCaacEqual       NUMBER(1);
      d_isAccessoGruppo   BOOLEAN := FALSE;
   BEGIN
      FOR utgr IN   (SELECT utgr.Gruppo, diac.ruolo ruolo_gruppo, ista.progetto
                          , diac2.ruolo ruolo_utente, diac2.Gruppo gruppo_utente
                       FROM UTENTI_GRUPPO utgr, UTENTI uten
                          , DIRITTI_ACCESSO diac, ISTANZE ista
                          , DIRITTI_ACCESSO diac2
                      WHERE utgr.Utente   = p_utente
                        AND uten.Utente   = utgr.Gruppo
                        AND ista.Istanza  = p_istanza
                        AND diac.Utente   = utgr.Gruppo
                        AND diac.modulo   = p_modulo
                        AND diac2.Istanza = ista.Istanza
                        AND diac2.Utente  = utgr.Utente
                        AND diac2.modulo  = p_modulo
                        AND diac2.Istanza = ista.Istanza
                      ORDER BY NVL(uten.importanza,99) DESC, utgr.Gruppo)
      LOOP
         SELECT COUNT(1)
           INTO d_esiste_caac_uten
           FROM CARATTERISTICHE_ACCESSO
          WHERE tipo_accesso = 'D'
            AND progetto = utgr.progetto
            AND Istanza  = p_istanza
            AND modulo   = p_modulo
            AND Utente   = p_utente
         ;
         d_tipo_accesso_uten := 'D';
         d_tipo_accesso_grup := 'G';
       d_isCaacEqual := Caratteristica_Accesso.is_equal( d_tipo_accesso_uten, utgr.progetto, p_istanza, p_modulo, p_utente
                                                         , d_tipo_accesso_grup, utgr.progetto, p_istanza, p_modulo, utgr.Gruppo);
         d_isAccessoGruppo := (d_esiste_caac_uten = 0 OR d_isCaacEqual = 1) AND
                              utgr.gruppo_utente IS NULL AND
                              utgr.ruolo_gruppo = utgr.ruolo_utente;
         IF d_isAccessoGruppo THEN
            UPDATE DIRITTI_ACCESSO
               SET Gruppo = utgr.Gruppo
             WHERE Utente  = p_utente
               AND modulo  = p_modulo
               AND Istanza = p_istanza
            ;
            Gruppo.caac_gruppo_insert(utgr.Gruppo, p_utente, p_modulo, p_istanza, 'E');
            EXIT;
         END IF;
      END LOOP;
   END AGGIORNA_GRUPPO;
   FUNCTION ESISTE
/******************************************************************************
 NOME:        ESISTE.
 DESCRIZIONE: Verifica esistenza diritto di accesso.
 PARAMETRI:   CHIAVE
 RITORNA:     number.
 ECCEZIONI:   --
 ANNOTAZIONI: .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    30/06/2005 MM     Creazione.
 1    15/03/2010 SNeg   Nella esiste condizionata la rigenera_so in modo che venga eseguita solo dalle webutils
                                   sul server Tomcat se e la versione  2011.5 o successiva che fa il controllo in authenticate
                                   richiamando accesso.login_setup
******************************************************************************/
   ( p_utente IN VARCHAR2
   , p_modulo IN VARCHAR2
   , p_istanza IN VARCHAR2
   , p_rigenera_so in VARCHAR2 default 'SI')
   RETURN NUMBER
   IS
      d_return       NUMBER := 0;
   BEGIN
    if p_rigenera_so = 'SI' then
       Utente.RIGENERA_SO(p_utente);
    end if;
      SELECT 1
        INTO d_return
        FROM DIRITTI_ACCESSO
       WHERE Utente = p_utente
         AND modulo = p_modulo
         AND Istanza = p_istanza
      ;
      RETURN d_return;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         RETURN d_return;
      WHEN OTHERS THEN
         RAISE;
   END ESISTE;
   FUNCTION CHECK_GRUPPO
/******************************************************************************
 NOME:        CHECK_GRUPPO.
 DESCRIZIONE: Dato un gruppo ed un diritto di accesso verifica se e' stato
              personalizzato od eliminato rispetto a quello del gruppo.
 PARAMETRI:   CHIAVE
 RITORNA:     number 0: diritto esiste in gruppo ma non in utente.
                     1: diritto esiste in gruppo e in utente.
                     2: diritto esiste in gruppo e in utente ma con ruolo o
                        caratteristiche diverse.
 ECCEZIONI:   --
 ANNOTAZIONI: .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    13/10/2005 MM     Creazione.
******************************************************************************/
   ( p_gruppo  IN VARCHAR2
   , p_utente  IN VARCHAR2
   , p_istanza IN VARCHAR2
   , p_modulo  IN VARCHAR2)
   RETURN NUMBER
   IS /* SLAVE_COPY */
      d_gruppo    VARCHAR2(8);
      d_return    NUMBER;
   BEGIN
      BEGIN
         SELECT NVL(Gruppo,'nullo')
           INTO d_gruppo
           FROM DIRITTI_ACCESSO diac
          WHERE diac.Utente  = p_utente
            AND diac.Istanza = p_istanza
            AND diac.modulo  = p_modulo
         ;
        IF d_gruppo <> p_gruppo THEN
         -- diritto esiste in gruppo e in utente ma con ruolo o caratteristiche diverse
            d_return := 2;
         ELSE
         -- diritto esiste in gruppo e in utente
            d_return := 1;
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
         -- diritto esiste in gruppo ma non in utente
            d_return := 0;
      END;
      RETURN d_return;
   END CHECK_GRUPPO;
PROCEDURE del
   ( p_ISTANZA  IN DIRITTI_ACCESSO.ISTANZA%TYPE
   , p_MODULO  IN DIRITTI_ACCESSO.MODULO%TYPE
   , p_UTENTE IN DIRITTI_ACCESSO.UTENTE%TYPE
   , p_RUOLO IN DIRITTI_ACCESSO.RUOLO%TYPE  DEFAULT NULL
   , p_SEQUENZA IN DIRITTI_ACCESSO.SEQUENZA%TYPE  DEFAULT NULL
   , p_ULTIMO_ACCESSO  IN DIRITTI_ACCESSO.ULTIMO_ACCESSO%TYPE DEFAULT NULL
   , p_NUMERO_ACCESSI  IN DIRITTI_ACCESSO.NUMERO_ACCESSI%TYPE DEFAULT NULL
   , p_GRUPPO  IN DIRITTI_ACCESSO.GRUPPO%TYPE DEFAULT NULL
   , p_NOTE  IN DIRITTI_ACCESSO.NOTE%TYPE DEFAULT NULL
   , p_check_OLD  IN INTEGER DEFAULT 0
) IS
/******************************************************************************
 NOME:        del
 DESCRIZIONE: Cancellazione della riga indicata.
 PARAMETRI:   Chiavi e attributi della table.
              p_check_OLD: 0, ricerca senza controllo su attributi precedenti
                           1, ricerca con controllo anche su attributi precedenti.
 NOTE:        Nel caso in cui non venga elaborato alcun record viene lanciata
              l'eccezione -20010 (cfr. AFC_ERROR).
              Se p_check_old = 1, viene controllato se il record corrispondente a
              tutti i campi passati come parametri esiste nella tabella.
******************************************************************************/
   d_row_found NUMBER;
BEGIN
   DELETE FROM DIRITTI_ACCESSO
   WHERE ISTANZA = p_ISTANZA
     AND MODULO = p_MODULO
     AND UTENTE = p_UTENTE
     AND (  p_check_old = 0
         OR p_check_old = 1
            AND ( RUOLO = p_RUOLO OR RUOLO IS NULL AND p_RUOLO IS NULL )
            AND ( SEQUENZA = p_SEQUENZA OR SEQUENZA IS NULL AND p_SEQUENZA IS NULL )
            AND ( ULTIMO_ACCESSO = p_ULTIMO_ACCESSO OR ULTIMO_ACCESSO IS NULL AND p_ULTIMO_ACCESSO IS NULL )
            AND ( NUMERO_ACCESSI = p_NUMERO_ACCESSI OR NUMERO_ACCESSI IS NULL AND p_NUMERO_ACCESSI IS NULL )
            AND ( GRUPPO = p_GRUPPO OR GRUPPO IS NULL AND p_GRUPPO IS NULL )
            AND ( NOTE = p_NOTE OR NOTE IS NULL AND p_NOTE IS NULL )
         )
   ;
   d_row_found := SQL%ROWCOUNT;
   IF d_row_found < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number, Afc_Error.modified_by_other_user_msg );
   END IF;
END del;
END Diritto_Accesso;
/

