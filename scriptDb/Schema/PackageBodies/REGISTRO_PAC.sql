CREATE OR REPLACE PACKAGE BODY Registro_Pac IS

/******************************************************************************
 NOME:        REGISTRO_PAC
 DESCRIZIONE: Gestione Registro
 ANNOTAZIONI: .
 REVISIONI:
 <CODE>
 Rev.  Data       Autore   Descrizione.
 02    23/02/2021 SNegroni se parametri url criptati non viene interpretato correttamente il riposizionamento #30727
 </CODE>
******************************************************************************/


FUNCTION VERSIONE  RETURN VARCHAR2 IS /* SLAVE_COPY */
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
******************************************************************************/
BEGIN
   RETURN 'V1.2';
END VERSIONE;
FUNCTION get_valore
( p_chiave IN VARCHAR2
, p_stringa IN VARCHAR2
, p_utente IN VARCHAR2)
RETURN VARCHAR2
IS /* SLAVE_COPY */
   d_valore VARCHAR2(32000);
BEGIN
   EXECUTE IMMEDIATE 'select min(valore) from '||NVL(p_utente,'AD4')||'.registro where chiave = '''||p_chiave||''' and stringa = '''||p_stringa||'''' INTO d_valore;
   RETURN d_valore;
END get_valore;
FUNCTION get_commento
( p_chiave IN VARCHAR2
, p_stringa IN VARCHAR2
, p_utente IN VARCHAR2)
RETURN VARCHAR2
IS /* SLAVE_COPY */
   d_commento VARCHAR2(32000);
BEGIN
   EXECUTE IMMEDIATE 'select min(commento) from '||NVL(p_utente,'AD4')||'.registro where chiave = '''||p_chiave||''' and stringa = '''||p_stringa||'''' INTO d_commento;
   RETURN d_commento;
END get_commento;
PROCEDURE STR_DELETE
( p_chiave IN VARCHAR2
, p_stringa IN VARCHAR2
, p_tipor IN VARCHAR2
, p_utente IN VARCHAR2) IS
d_stringa VARCHAR2(1000);
BEGIN
IF p_tipor = 'S' THEN -- Stiamo trattando una stringa
   d_stringa := 'begin '||NVL(p_utente,'AD4')||'.registro_utility.elimina_stringa('''||p_chiave||''','''||p_stringa||''');end;';
ELSE -- stiamo trattando una chiave
   d_stringa := 'begin '||NVL(p_utente,'AD4')||'.registro_utility.elimina_chiave('''||p_chiave||''');end;';
END IF;
EXECUTE IMMEDIATE d_stringa;
EXCEPTION WHEN OTHERS THEN
   RAISE;
END STR_DELETE;
PROCEDURE STR_UPDATE
( p_chiave IN VARCHAR2
, p_chiave_old IN VARCHAR2
, p_stringa IN VARCHAR2
, p_stringa_old IN VARCHAR2
, p_valore IN VARCHAR2
, p_valore_old IN VARCHAR2
, p_commento IN VARCHAR2
, p_tipor IN VARCHAR2
, p_utente IN VARCHAR2)
IS
   d_stringa VARCHAR2(4000);
   d_valore  VARCHAR2(4000);
BEGIN
--   raise_application_error(-20999, ''''||p_chiave||''', '''||p_chiave_old ||''', '''|| p_stringa ||''', '''|| p_stringa_old||''', '''|| p_valore||''', '''|| p_valore_old ||''', '''||p_commento ||''', '''|| p_tipor ||''', '''|| p_utente||'''');
   d_valore := p_valore;
   d_valore := REPLACE(d_valore,'''','''''');
   IF SUBSTR(p_valore,1,1) = CHR(10) OR SUBSTR(p_valore,1,1) = CHR(13) THEN
      d_valore := ' '||d_valore;
   END IF;
   IF p_tipor = 'S' THEN -- Stiamo trattando una stringa
      IF p_chiave_old IS NOT NULL THEN -- siamo in aggiornamento
         d_stringa := 'update '||NVL(p_utente,'AD4')||'.registro'||
                      '   set stringa = '''||p_stringa||''''||
                      '     , valore = '''||d_valore||'''';
         IF p_commento <> 'nonGestito' THEN
            d_stringa := d_stringa ||'   , commento = '''||REPLACE(p_commento,'''','''''')||'''';
         END IF;
        d_stringa := d_stringa ||
                        ' where chiave = '''||p_chiave_old||''''||
                        ' and stringa = '''||p_stringa_old||''''||
                        ' and nvl(valore, ''nonGestito'') = nvl('''||REPLACE(p_valore_old,'''','''''')||''', ''nonGestito'')';
      ELSE -- siamo in inserimento
         d_stringa := 'begin '||NVL(p_utente,'AD4')||'.registro_utility.scrivi_stringa('''||p_chiave||''','''||p_stringa||''','''||d_valore||'''';
       IF p_commento <> 'nonGestito' THEN
          d_stringa := d_stringa||','''||REPLACE(p_commento,'''','''''')||'''';
       END IF;
       d_stringa := d_stringa ||');end;';
      END IF;
   ELSE -- Stiamo trattando una chiave
      IF p_chiave_old IS NOT NULL THEN -- siamo in aggiornamento
         d_stringa := 'update '||NVL(p_utente,'AD4')||'.registro'||
                        ' set chiave = replace(chiave,'''||p_chiave_old||''','''||p_chiave||''')';
      ELSE -- siamo in inserimento
         d_stringa := 'begin '||NVL(p_utente,'AD4')||'.registro_utility.crea_chiave('''||p_chiave||'''); exception when others then if SQLCODE = 20921 then null; end if; end;';
      END IF;
   END IF;
   integritypackage.log(d_stringa);
   EXECUTE IMMEDIATE d_stringa;
EXCEPTION WHEN OTHERS THEN
   RAISE;
END STR_UPDATE;
PROCEDURE STR_UPDATE
( p_chiave IN VARCHAR2
, p_stringa IN VARCHAR2
, p_valore IN VARCHAR2
, p_valore_old IN VARCHAR2
, p_utente IN VARCHAR2) IS
d_stringa VARCHAR2(1000);
BEGIN
   str_update(p_chiave, p_chiave, p_stringa, p_stringa, p_valore, p_valore_old, 'nonGestito', 'S', NVL(p_utente,'AD4'));
END STR_UPDATE;
FUNCTION albero_registro (
   P_GRUPPO_ID   VARCHAR2,
   P_MODULO      VARCHAR2,
   P_PAGE        VARCHAR2,
   P_RICERCA     VARCHAR2,
   P_UTENTE      VARCHAR2
)
   RETURN CLOB
/******************************************************************************
 NOME:        ALBERO_REGISTRO
 DESCRIZIONE: Gestione Registro
 ANNOTAZIONI: .
 REVISIONI:
 <CODE>
 Rev.  Data       Autore   Descrizione.
 02    23/02/2021 SNegroni se parametri url criptati non viene interpretato correttamente il riposizionamento #30727
 </CODE>
******************************************************************************/
IS /* SLAVE_COPY */
d_esiste NUMBER(1) := 0;
BEGIN
-- verifico l'esistenza della vista "P_UTENTE.VISTA_REGISTRO""
SELECT 1
  INTO d_esiste
  FROM ALL_OBJECTS
 WHERE object_name = 'VISTA_REGISTRO'
   AND owner = NVL(p_utente,'AD4');
   RETURN Afc_Html.TREE (P_gruppo_ID,
                         P_MODULO,
                         'REGISTRO',
                         P_PAGE,
                         NVL(p_utente,'AD4')||'.VISTA_REGISTRO',
                         'PADRE',
                         'FIGLIO',
                         'chiave',
                         'padre',
                         NULL,
                         'N',
                         'N',-- rev. 02
                         'N',
                         'N',
                         NULL,
                         NULL,
                         'RG',
                         '|',
                         '../common/images/AMV/',
                         P_COLONNA_IMMAGINE => 'immagine',
                         P_COLONNA_IMMAGINE_OPEN => 'immagine_open',
--                         P_COLONNA_LINK=>'url',
                         P_RICERCA => P_RICERCA,
                         P_SOLO_UNA_FOGLIA_APERTA=> 'S');
EXCEPTION
   WHEN NO_DATA_FOUND THEN
   declare
      d_error           varchar2(1000) := 'Utente non previsto o vista non prevista!';
      d_clob            CLOB := EMPTY_CLOB();
      d_stringa         VARCHAR2(4000);
   BEGIN
      d_stringa := '<tr><td class="AFCErrorDataTD">'||d_error||'</td></tr>';
      dbms_lob.createTemporary(d_clob,TRUE,dbms_lob.SESSION);
      dbms_lob.WRITE(d_clob, LENGTH(d_stringa), 1, d_stringa);
      RETURN d_clob;
   END;
END albero_registro;
FUNCTION get_registro_rc
( p_chiave IN VARCHAR2
, p_stringa IN VARCHAR2
, p_utente IN VARCHAR2
)
RETURN AFC.T_REF_CURSOR--registro_rc
IS /* SLAVE_COPY */
   p_rc       AFC.T_REF_CURSOR;
   d_esiste   NUMBER(1) := 0;
   d_select   VARCHAR2(500);
   d_order_by VARCHAR2(15);
BEGIN
   BEGIN
      SELECT 1
        INTO d_esiste
        FROM ALL_OBJECTS
       WHERE object_name = 'VISTA_REGISTRO'
         AND owner = NVL(p_utente,'AD4');
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
           OPEN p_rc FOR
           SELECT '','','','',''
            FROM dual
        WHERE 1<>1;
         RETURN p_rc;
   END;
   BEGIN
      SELECT COUNT(1)
        INTO d_esiste
        FROM ALL_SOURCE
       WHERE OWNER = NVL(p_utente,'AD4')
         AND NAME = 'REGISTRO_UTILITY'
         AND TYPE = 'PACKAGE'
         AND INSTR(LOWER(TEXT),'get_sequenza_ordinamento') > 0
      ;
      IF d_esiste > 0 THEN
         d_select := 'Registro_Utility.get_sequenza_ordinamento('''||p_chiave||''', stringa) sequenza_ord';
         d_order_by := ' order by 5';
      ELSE
         d_select := 'ROWNUM sequenza_ord';
       d_order_by := ' order by 2';
      END IF;
   END;
   OPEN p_rc FOR
      'select chiave, stringa, valore, commento, '||d_select||
      '  from '||NVL(p_utente,'AD4')||'.registro'||
      ' where chiave = '''||p_chiave||''''||
      '   and stringa like nvl('''||p_stringa||''',''%'')'||
      '   and lower(stringa) <>''(predefinito)'''||
      d_order_by
      ;
   RETURN p_rc;
END get_registro_rc;
FUNCTION EXISTS_STRINGA
( p_chiave IN VARCHAR2
, p_stringa IN VARCHAR2
, p_utente IN VARCHAR2)
RETURN NUMBER
IS /* SLAVE_COPY */
   d_stringa VARCHAR2(4000);
BEGIN
   EXECUTE IMMEDIATE 'select valore '||
        '  from '||NVL(p_utente,'AD4')||'.registro'||
        ' where chiave = '''||p_chiave||''''||
        '   and stringa = '''||p_stringa||''''
   INTO d_stringa;
   RETURN 1;
EXCEPTION WHEN NO_DATA_FOUND THEN
   RETURN 0;
END EXISTS_STRINGA;
FUNCTION EXISTS_CHIAVE
( p_chiave IN VARCHAR2
, p_utente IN VARCHAR2)
RETURN NUMBER
IS /* SLAVE_COPY */
   d_stringa VARCHAR2(4000);
BEGIN
   EXECUTE IMMEDIATE
        'select distinct ''1'' '||
        '  from '||NVL(p_utente,'AD4')||'.registro'||
        ' where chiave = '''||p_chiave||''''
   INTO d_stringa;
   RETURN 1;
EXCEPTION WHEN NO_DATA_FOUND THEN
   RETURN 0;
END EXISTS_CHIAVE;
FUNCTION IS_CHIAVE_MODIFICABILE
( p_chiave IN VARCHAR2
, p_utente IN VARCHAR2)
RETURN NUMBER
IS /* SLAVE_COPY */
   d_stringa VARCHAR2(4000);
   d_return  NUMBER := 0;
BEGIN
   IF EXISTS_CHIAVE(p_chiave, NVL(p_utente,'AD4')) = 1 THEN
      BEGIN
         EXECUTE IMMEDIATE
              'select PADRE '||
              '  from '||NVL(p_utente,'AD4')||'.vista_registro'||
              ' where FIGLIO = '''||p_chiave||''''
         INTO d_stringa;
       IF d_stringa <> '0' THEN
            d_return := 1;
         END IF;
      EXCEPTION WHEN NO_DATA_FOUND THEN
         d_return := 0;
      END;
   END IF;
   RETURN d_return;
END IS_CHIAVE_MODIFICABILE;
FUNCTION GET_CHIAVE
( p_chiave IN VARCHAR2
, p_utente IN VARCHAR2)
RETURN VARCHAR2
IS /* SLAVE_COPY */
   d_return  VARCHAR2(512);
BEGIN
   IF EXISTS_CHIAVE(p_chiave, NVL(p_utente,'AD4')) = 1 THEN
      BEGIN
         EXECUTE IMMEDIATE
              'select CHIAVE '||
              '  from '||NVL(p_utente,'AD4')||'.vista_registro'||
              ' where FIGLIO = '''||p_chiave||''''
         INTO d_return;
      EXCEPTION WHEN NO_DATA_FOUND THEN
         d_return := '';
      END;
   END IF;
   RETURN d_return;
END GET_CHIAVE;
FUNCTION GET_PADRE
( p_chiave IN VARCHAR2
, p_utente IN VARCHAR2)
RETURN VARCHAR2
IS /* SLAVE_COPY */
   d_return  VARCHAR2(512);
BEGIN
   IF EXISTS_CHIAVE(p_chiave, NVL(p_utente,'AD4')) = 1 THEN
      BEGIN
         EXECUTE IMMEDIATE
              'select PADRE '||
              '  from '||NVL(p_utente,'AD4')||'.vista_registro'||
              ' where FIGLIO = '''||p_chiave||''''
         INTO d_return;
       IF d_return = '0' THEN
          d_return := '';
         ELSE
          d_return := d_return||'/';
         END IF;
      EXCEPTION WHEN NO_DATA_FOUND THEN
         d_return := '';
      END;
   END IF;
   RETURN d_return;
END GET_PADRE;
   FUNCTION get_ad4_string
/******************************************************************************
 NOME:        get_ad4_string.
              Legge il valore della stringa p_stringa della chiave p_chiave
              nella tabella registro di p_user con il seguente criterio di
              ricerca:
              1. DB_USERS/<p_user>/PRODUCTS/<p_modulo>/p_chiave;
              2. DB_USERS/<p_user>/p_chiave;
              3. PRODUCTS/<p_modulo>/p_chiave;
              4. PRODUCTS/AD4.
 PARAMETRI:   p_chiave:  chiave del registro in cui cercare la stringa.
              p_stringa: stringa da cercare nel registro.
              p_modulo:  modulo applicativo per cui cercare la stringa.
              p_user:    user oracle a cui appartiene il registro in cui cercare.
 RITORNA:     varchar2 valore della stringa data nella chiave data.
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/10/2005 MM     Prima emissione.
******************************************************************************/
   ( p_chiave VARCHAR2
   , p_stringa VARCHAR2
   , p_modulo VARCHAR2
   , p_user VARCHAR2)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_valore VARCHAR2(2000);
      d_chiave VARCHAR2(512);
      d_user   VARCHAR2(30);
   BEGIN
      d_user := UPPER(NVL(p_user,USER));
   -- Ricerca stringa a livello di db_user con modulo
      IF p_modulo IS NOT NULL THEN
         d_chiave := 'DB_USERS/'||d_user||'/PRODUCTS/'||UPPER(p_modulo);
         IF p_chiave IS NOT NULL THEN
            d_chiave := d_chiave||'/'||p_chiave;
         END IF;
         BEGIN
            Registro_Utility.leggi_stringa (d_chiave, p_stringa, d_valore, FALSE);
         EXCEPTION
            WHEN OTHERS THEN
              d_valore := TO_CHAR(NULL);
         END;
      END IF;
   -- Ricerca preferenza a livello di db user senza modulo
      IF d_valore IS NULL THEN
         d_chiave := 'DB_USERS/'||d_user;
         IF p_chiave IS NOT NULL THEN
            d_chiave := d_chiave||'/'||p_chiave;
         END IF;
         BEGIN
            Registro_Utility.leggi_stringa (d_chiave, p_stringa, d_valore, FALSE);
         EXCEPTION
            WHEN OTHERS THEN
              d_valore := TO_CHAR(NULL);
         END;
      END IF;
   -- Ricerca preferenza a livello generale per lo specifico modulo
      IF d_valore IS NULL AND p_modulo IS NOT NULL THEN
         d_chiave := 'PRODUCTS/'||UPPER(p_modulo);
         IF p_chiave IS NOT NULL THEN
            d_chiave := d_chiave||'/'||p_chiave;
         END IF;
         BEGIN
            Registro_Utility.leggi_stringa (d_chiave, p_stringa, d_valore, FALSE);
         EXCEPTION
            WHEN OTHERS THEN
              d_valore := TO_CHAR(NULL);
         END;
      END IF;
   -- Ricerca preferenza a livello generale
      IF d_valore IS NULL THEN
         d_chiave := 'PRODUCTS/AD4';
         IF p_chiave IS NOT NULL THEN
            d_chiave := d_chiave||'/'||p_chiave;
         END IF;
         BEGIN
            Registro_Utility.leggi_stringa (d_chiave, p_stringa, d_valore, FALSE);
         EXCEPTION
            WHEN OTHERS THEN
              d_valore := TO_CHAR(NULL);
         END;
      END IF;
      RETURN d_valore;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END get_ad4_string;
   PROCEDURE set_ad4_string
/******************************************************************************
 NOME:        set_ad4_string.
              Scrive il valore p_valore nella stringa p_stringa della chiave
              p_chiave nella tabella registro di p_user.
              Se p_valore e' null, la stringa viene eliminata da
                 DB_USERS/<p_user>/PRODUCTS/<p_modulo>/p_chiave.
              Lo scrive in
                 DB_USERS/<p_user>/PRODUCTS/<p_modulo>/p_chiave
              se non esiste gia' con uguale valore in
                 DB_USERS/<p_user>/p_chiave
              o, se non esiste la chiave DB_USERS/<p_user>/p_chiave, in
                 PRODUCTS/<p_modulo>/p_chiave
              o, se non esiste la chiave PRODUCTS/<p_modulo>/p_chiave, in
                 PRODUCTS/AD4.
 ARGOMENTI:   p_chiave:  chiave del registro in cui cercare la stringa.
              p_stringa: stringa da cercare nel registro.
              p_valore:  valore da associare alla stringa, se = null, la stringa
                         viene eliminata da
                         DB_USERS/<p_user>/PRODUCTS/<p_modulo>/p_chiave.
              p_modulo:  modulo applicativo per cui cercare la stringa.
              p_user:    user oracle a cui appartiene il registro in cui cercare.
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    02/10/2005 MM     Prima emissione.
 1    27/10/2009 SNeg   A34873.0.0 Errore "Tag gia presente" in modifica tag.
******************************************************************************/
   ( p_chiave VARCHAR2
   , p_stringa VARCHAR2
   , p_valore VARCHAR2
   , p_modulo VARCHAR2
   , p_user VARCHAR2)
   IS
      d_valore VARCHAR2(2000);
      d_chiave VARCHAR2(512);
      d_user   VARCHAR2(30);
   BEGIN
      d_user := UPPER(NVL(p_user,USER));
      IF p_valore IS NULL THEN
         IF p_modulo IS NOT NULL THEN
          d_chiave := 'DB_USERS/'||d_user||'/PRODUCTS/'||UPPER(p_modulo)||'/'||p_chiave;
         ELSE
          d_chiave := 'DB_USERS/'||d_user||'/'||p_chiave;
         END IF;
       Registro_Utility.elimina_stringa(d_chiave, p_stringa, FALSE);
      ELSE
      -- Ricerca stringa in db_user senza modulo
         IF p_modulo IS NOT NULL THEN
            d_chiave := 'DB_USERS/'||d_user;
            IF p_chiave IS NOT NULL THEN
               d_chiave := d_chiave||'/'||p_chiave;
            END IF;
            BEGIN
               Registro_Utility.leggi_stringa (d_chiave, p_stringa, d_valore, FALSE);
            EXCEPTION
               WHEN OTHERS THEN
                 d_valore := TO_CHAR(NULL);
            END;
   --DBMS_OUTPUT.PUT_LINE(d_chiave||': '||d_valore);
         END IF;
      -- Ricerca preferenza a livello generale per lo specifico modulo
         IF d_valore IS NULL AND p_modulo IS NOT NULL THEN
            d_chiave := 'PRODUCTS/'||UPPER(p_modulo);
            IF p_chiave IS NOT NULL THEN
               d_chiave := d_chiave||'/'||p_chiave;
            END IF;
            BEGIN
               Registro_Utility.leggi_stringa (d_chiave, p_stringa, d_valore, FALSE);
            EXCEPTION
               WHEN OTHERS THEN
                 d_valore := TO_CHAR(NULL);
            END;
   --DBMS_OUTPUT.PUT_LINE(d_chiave||': '||d_valore);
         END IF;
      -- Ricerca preferenza a livello generale
         IF d_valore IS NULL THEN
            d_chiave := 'PRODUCTS/AD4';
            IF p_chiave IS NOT NULL THEN
               d_chiave := d_chiave||'/'||p_chiave;
            END IF;
            BEGIN
               Registro_Utility.leggi_stringa (d_chiave, p_stringa, d_valore, FALSE);
            EXCEPTION
               WHEN OTHERS THEN
                 d_valore := TO_CHAR(NULL);
            END;
   --DBMS_OUTPUT.PUT_LINE(d_chiave||': '||d_valore);
         END IF;
         -- in ogni caso si cancella la voce
         -- verra ricreata solo se valore diverso dal padre
         -- se valore uguale al padre non deve esistere
         IF p_modulo IS NOT NULL THEN
            d_chiave := 'DB_USERS/'||d_user||'/PRODUCTS/'||UPPER(p_modulo);
         ELSE
            d_chiave := 'DB_USERS/'||d_user;
         END IF;
         IF p_chiave IS NOT NULL THEN
            d_chiave := d_chiave||'/'||p_chiave;
         END IF;
         Registro_Utility.elimina_stringa(d_chiave, p_stringa, FALSE);
         -- se il valore passato e' diverso da quello trovato, scrive il valore in db_user
         -- con modulo
         IF LOWER(NVL(d_valore,' ')) <> LOWER(NVL(p_valore,' ')) THEN
            Registro_Utility.crea_chiave(d_chiave, FALSE);
            Registro_Utility.scrivi_stringa (d_chiave, p_stringa, p_valore);
   --DBMS_OUTPUT.PUT_LINE('Setto '||d_chiave||': '||p_valore);
         END IF;
      END IF;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE;
   END set_ad4_string;
END Registro_Pac;
/

