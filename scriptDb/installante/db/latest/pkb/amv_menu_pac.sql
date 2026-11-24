--liquibase formatted sql

--changeset mturra:201901301231_118 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY Amv_Menu_Pac IS
FUNCTION VERSIONE  RETURN VARCHAR2 IS
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
******************************************************************************/
BEGIN
   RETURN 'V1.0 del 22/09/2005';
END VERSIONE;
PROCEDURE VOCI_PD  (
  P_UTENTE IN VARCHAR2
, P_VOCE IN VARCHAR2
) IS
d_stringa VARCHAR2(1000);
BEGIN
   d_stringa:= 'delete from '||p_utente||'.AMV_VOCI '||
               ' WHERE VOCE = '''||P_VOCE||'''';
   IF d_stringa IS NOT NULL THEN
      EXECUTE IMMEDIATE d_stringa;
   END IF;
END VOCI_PD;

PROCEDURE VOCI_PIU  (
  P_UTENTE IN VARCHAR2
, P_INSERT_UPDATE IN VARCHAR2
, P_VOCE IN VARCHAR2
, P_PROGETTO IN VARCHAR2
, P_ACRONIMO IN VARCHAR2
, P_ACRONIMO_AL1 IN VARCHAR2
, P_ACRONIMO_AL2 IN VARCHAR2
, P_TITOLO IN VARCHAR2
, P_TITOLO_AL1 IN VARCHAR2
, P_TITOLO_AL2 IN VARCHAR2
, P_TIPO_VOCE IN VARCHAR2
, P_TIPO IN VARCHAR2
, P_MODULO IN VARCHAR2
, P_STRINGA IN VARCHAR2
, P_PROFILO IN VARCHAR2
, P_VOCE_GUIDA IN VARCHAR2
, P_PROPRIETA IN VARCHAR2
, P_NOTE IN VARCHAR2
, P_PADRE IN NUMBER
, P_PADRE_OLD IN NUMBER
, P_ABIL_MODULO IN VARCHAR2
, P_SEQUENZA IN NUMBER
, P_RUOLO IN VARCHAR2
, P_ABILITAZIONI IN NUMBER
, P_VOCE_OLD IN VARCHAR2) IS
d_stringa VARCHAR2(1000);
BEGIN
   d_stringa := 'begin '||p_utente||'.AMV_VOCI_PIU('
   ||''''||P_INSERT_UPDATE||''''
   ||','''||P_VOCE||''''
   ||','''||P_PROGETTO||''''
   ||','''||P_ACRONIMO||''''
   ||','''||P_ACRONIMO_AL1||''''
   ||','''||P_ACRONIMO_AL2||''''
   ||','''||P_TITOLO||''''
   ||','''||P_TITOLO_AL1||''''
   ||','''||P_TITOLO_AL2||''''
   ||','''||P_TIPO_VOCE||''''
   ||','''||P_TIPO||''''
   ||','''||P_MODULO||''''
   ||','''||P_STRINGA||''''
   ||','''||P_PROFILO||''''
   ||','''||P_VOCE_GUIDA||''''
   ||','''||P_PROPRIETA||''''
   ||','''||P_NOTE||''''
   ||','''||P_PADRE||''''
   ||','''||P_PADRE_OLD||''''
   ||','''||P_ABIL_MODULO||''''
   ||','''||P_SEQUENZA||''''
   ||','''||P_RUOLO||''''
   ||','''||P_ABILITAZIONI||''''
   ||','''||P_VOCE_OLD||''''
   ||');END;';
EXECUTE IMMEDIATE d_stringa;
EXCEPTION WHEN OTHERS THEN
   RAISE;
END VOCI_PIU;

FUNCTION GET_ALBERO_MENU
( P_USER      IN VARCHAR2
, P_UTENTE    IN  VARCHAR2
, P_ISTANZA   IN  VARCHAR2
, P_MODULO    IN  VARCHAR2
, P_PROGETTO  IN  VARCHAR2
, P_VOCE_L2   IN  VARCHAR2
, P_VOCE_L3   IN  VARCHAR2
, P_RUOLO     IN  VARCHAR2
, P_VOCE_SEL  IN  VARCHAR2
, P_ID        IN  VARCHAR2
) RETURN CLOB IS
d_amount        BINARY_INTEGER := 32767;
d_clob          CLOB := EMPTY_CLOB() ;
d_stringa VARCHAR2(1000);
BEGIN
   dbms_lob.createTemporary(d_clob,TRUE,dbms_lob.SESSION);
   d_stringa := 'begin dbms_lob.append(:d_clob,'
   ||p_user||'.Amv_menu.get_albero_menu('
   ||''''||P_UTENTE||''''
   ||','''||P_ISTANZA||''''
   ||','''||P_MODULO||''''
   ||','''||P_PROGETTO||''''
   ||','''||P_VOCE_L2||''''
   ||','''||P_VOCE_L3||''''
   ||','''||P_RUOLO||''''
   ||','''||P_VOCE_SEL||''''
   ||','''||P_ID ||''''
   ||'));END;';

   EXECUTE IMMEDIATE d_stringa USING IN OUT d_clob;
   RETURN d_clob;
END GET_ALBERO_MENU;

FUNCTION get_voce_rc
( p_user     IN VARCHAR2
, p_voce     IN VARCHAR2
, p_modulo   IN VARCHAR2
, p_ruolo    IN VARCHAR2
, p_padre    IN VARCHAR2
)
RETURN voci_rc
IS
   p_rc voci_rc;
BEGIN
   IF p_modulo IS NOT NULL AND p_voce IS NOT NULL THEN
      OPEN p_rc FOR
         'select VOCE, PROGETTO, TITOLO, TIPO_VOCE'||
         ', decode (tipo_voce,''N'',decode(tipo,''A'',''V'',tipo),tipo) TIPO_LIST '||
         ', TIPO, MODULO, STRINGA, VOCE_GUIDA, NOTE'||
   ','''||p_padre||''' PADRE_OLD'||
   ','''||p_modulo||''' MODULO_SEL'||
   ','''||p_ruolo||''' RUOLO_SEL'||
         ', decode('||
              p_user||'.Amv_Menu.get_abilitazione('''||p_voce||''', '''||p_ruolo||''', '''||p_modulo||''','||p_padre||'),0,-1,1,1,0)  stato_abil'||
         '  from '||p_user||'.amv_voci'||
         ' where voce = '''||p_voce||'''';
   ELSE
      OPEN p_rc FOR 'select 1 from dual where 1=2';
   END IF;
   RETURN p_rc;
NULL;
END get_voce_rc;

FUNCTION get_voci_guida_rc
( p_user     IN VARCHAR2
, p_progetto IN VARCHAR2
)
RETURN voci_rc
IS
   p_rc voci_rc;
BEGIN
   IF p_progetto IS NOT NULL THEN
      OPEN p_rc FOR
         'SELECT VOCE, TITOLO||'' (''||VOCE||'')'' TITOLO '||
         'FROM '||p_user||'.AMV_VOCI'||
         ' WHERE PROGETTO = '''||p_progetto||''' AND TIPO <> ''M'''||
         ' ORDER BY TITOLO';
   ELSE
      OPEN p_rc FOR 'select 1 from dual where 1=2';
   END IF;
   RETURN p_rc;
END get_voci_guida_rc;


FUNCTION get_voci_padre_rc
( p_user     IN VARCHAR2
, p_modulo   IN VARCHAR2
) RETURN voci_rc
IS
   p_rc voci_rc;
BEGIN
   IF p_modulo IS NOT NULL THEN
      OPEN p_rc FOR
         'SELECT DISTINCT a.ABILITAZIONE ABILITAZIONE, NVL(v.TITOLO,'' - -'') TITOLO '||
         'FROM '||p_user||'.AMV_VOCI v, '||p_user||'.AMV_ABILITAZIONI a '||
         ' WHERE v.VOCE (+) = a.VOCE_MENU'||
         ' AND (NVL(v.TIPO, ''M'') = ''M'''||
         ' OR (NVL(v.TIPO, ''A'') = ''A'''||
         ' AND NVL(v.TIPO_VOCE, ''N'') = ''N''))'||
         ' AND NVL(a.MODULO,'''||p_modulo||''') = '''||p_modulo||''''||
         ' ORDER BY TITOLO';
   ELSE
      OPEN p_rc FOR 'select 1 from dual where 1=2';
   END IF;
   RETURN p_rc;
END get_voci_padre_rc;

PROCEDURE GUIDA_PIU  (
  P_USER IN VARCHAR2
, P_INSERT_UPDATE IN VARCHAR2
, P_GUIDA IN VARCHAR2
, P_SEQUENZA IN NUMBER
, P_ALIAS IN VARCHAR2
, P_TITOLO IN VARCHAR2
, P_VOCE_MENU  IN VARCHAR2
, P_VOCE_RIF IN VARCHAR2
) IS
d_stringa VARCHAR2(1000);
BEGIN
   IF P_INSERT_UPDATE = 'I' THEN
      d_stringa:= 'INSERT INTO '||p_user||'.AMV_GUIDE (GUIDA, SEQUENZA, ALIAS, TITOLO, VOCE_MENU, VOCE_RIF) '||
                  'VALUES ( '''||P_GUIDA||''', '''||P_SEQUENZA||''', '''||P_ALIAS||''', '''||P_TITOLO||''', '''||P_VOCE_MENU||''', '''||P_VOCE_RIF||''')'
      ;
   ELSIF P_INSERT_UPDATE = 'U' THEN
      d_stringa:= 'UPDATE '||p_user||'.AMV_GUIDE SET '||
                  'SEQUENZA = '||P_SEQUENZA||','||
                  'ALIAS = '''||P_ALIAS||''','||
                  'TITOLO = '''||P_TITOLO||''','||
                  'VOCE_MENU = '''||P_VOCE_MENU||''','||
                  'VOCE_RIF = '''||P_VOCE_RIF||''''||
                  'WHERE GUIDA = '''||P_GUIDA||''''||
      '  AND SEQUENZA = '||P_SEQUENZA;
   END IF;
   IF d_stringa IS NOT NULL THEN
      EXECUTE IMMEDIATE d_stringa;
   END IF;
END GUIDA_PIU;

PROCEDURE GUIDE_PD  (
  P_USER IN VARCHAR2
, P_GUIDA IN VARCHAR2
, P_SEQUENZA IN NUMBER
) IS
d_stringa VARCHAR2(1000);
BEGIN
   --RAISE_APPLICATION_ERROR(-20999,'Eliminazione');
   d_stringa:= 'delete from '||p_user||'.AMV_GUIDE '||
               ' WHERE GUIDA = '''||P_GUIDA||''''||
               ' AND SEQUENZA = '||P_SEQUENZA;
   IF d_stringa IS NOT NULL THEN
      EXECUTE IMMEDIATE d_stringa;
   END IF;
END GUIDE_PD;

FUNCTION get_guide_rc
( p_user        IN VARCHAR2
, p_id          IN VARCHAR2
, p_id_sessione IN VARCHAR2
)--nvl('{ID}','{Id}')
RETURN guide_rc
IS
   p_rc guide_rc;
BEGIN
   IF p_user IS NOT NULL AND NVL(p_id, p_id_sessione) IS NOT NULL THEN
      OPEN p_rc FOR
'SELECT g.GUIDA'||
', g.TITOLO'||
', g.SEQUENZA'||
', v.TITOLO VOCE_RIF'||
','||p_user||'.Amv_Menu.GET_LINK_STRINGA(v.stringa,v.modulo)||DECODE(UPPER(alias),''WEB'','''',DECODE(INSTR('||p_user||'.Amv_Menu.GET_LINK_STRINGA(v.stringa,v.modulo),''?''),0,''?'',''&'||''')||''MVAV=''||alias) URL_RIF'||
', ''Modifica'' Modifica'||
' FROM '||p_user||'.AMV_GUIDE g, '||p_user||'.AMV_VOCI v '||
' WHERE g.GUIDA = '''||NVL(p_id,p_id_sessione)||''''||
' AND v.VOCE = g.VOCE_MENU'||
' ORDER BY g.SEQUENZA';
--          'select VOCE, PROGETTO, TITOLO, TIPO_VOCE'||
--          ', decode (tipo_voce,''N'',decode(tipo,''A'',''V'',tipo),tipo) TIPO_LIST '||
--          ', TIPO, MODULO, STRINGA, VOCE_GUIDA, NOTE'||
--    ','''||p_padre||''' PADRE_OLD'||
--    ','''||p_modulo||''' MODULO_SEL'||
--    ','''||p_ruolo||''' RUOLO_SEL'||
--          ', decode('||
--               p_user||'.Amv_Menu.get_abilitazione('''||p_voce||''', '''||p_ruolo||''', '''||p_modulo||''','||p_padre||'),0,-1,1,1,0)  stato_abil'||
--          '  from '||p_user||'.amv_voci'||
--          ' where voce = '''||p_voce||'''';
   ELSE
      OPEN p_rc FOR 'select 1 from dual where 1=2';
   END IF;
   RETURN p_rc;
END get_guide_rc;

FUNCTION get_guida_rc
( p_user     IN VARCHAR2
, p_guida    IN VARCHAR2
, p_sequenza IN VARCHAR2
)
RETURN guide_rc
IS
   p_rc guide_rc;
BEGIN
   IF p_user IS NOT NULL AND p_guida IS NOT NULL AND p_sequenza IS NOT NULL THEN
      OPEN p_rc FOR
      'SELECT * '||
      'FROM '||p_user||'.AMV_GUIDE '||
      'WHERE GUIDA = '''||p_guida||''' '||
      'AND SEQUENZA = '''||p_sequenza||'''';
   ELSE
      OPEN p_rc FOR 'select 1 from dual where 1=2';
   END IF;
   RETURN p_rc;
END get_guida_rc;
END Amv_Menu_Pac;
/
