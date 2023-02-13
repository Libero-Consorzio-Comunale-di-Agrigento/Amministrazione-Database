--liquibase formatted sql

--changeset mturra:201901301231_114 runOnChange:true stripComments:false

CREATE OR REPLACE PACKAGE BODY Amvweb AS


FUNCTION versione  RETURN VARCHAR2 IS
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 ECCEZIONI:   --
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    05/02/2004 AO     Creazione.
 ******************************************************************************/
BEGIN
   RETURN d_versione||' Rev.'||d_revisione;
END versione;
PROCEDURE set_preferenza (
  p_stringa VARCHAR2
, p_valore  VARCHAR2
, p_modulo  VARCHAR2 DEFAULT NULL
, p_utente  VARCHAR2 DEFAULT NULL
) IS
d_valore   VARCHAR2(2000);
d_chiave   VARCHAR2(512);
BEGIN
-- Composizione della chiave con cui andare a scrivere su registro
   IF p_utente IS NOT NULL THEN
      d_chiave := 'SI4_DB_USERS/'||p_utente||'|'||USER;
      IF p_modulo IS NOT NULL THEN
         d_chiave := d_chiave||'/PRODUCTS/'||UPPER(p_modulo);
     END IF;
   ELSE
      d_chiave := 'DB_USERS/'||USER;
      IF p_modulo IS NOT NULL THEN
         d_chiave := d_chiave||'/PRODUCTS/'||UPPER(p_modulo);
     END IF;
   END IF;
   Registro_Utility.crea_chiave(d_chiave, FALSE);
   Registro_Utility.elimina_stringa(d_chiave ,p_stringa, FALSE);
-- Se il valore della preferenza e gia presente a livello piu generale non si effettua la registrazione.
   IF p_valore != NVL(get_preferenza (p_stringa, p_modulo, p_utente),' ') THEN
         Registro_Utility.scrivi_stringa(d_chiave, p_stringa, p_valore);
   END IF;
END set_preferenza;
FUNCTION get_preferenza (p_stringa VARCHAR2, p_modulo VARCHAR2 DEFAULT NULL, p_utente VARCHAR2 DEFAULT NULL) RETURN VARCHAR2 IS
d_valore VARCHAR2(2000);
d_chiave VARCHAR2(512);
BEGIN
-- Ricerca preferenza a livello utente di sistema
   d_valore := get_preferenza_utente (p_stringa, p_modulo , p_utente);
-- Ricerca preferenza a livello di db user
   IF d_valore IS NULL THEN
      d_valore := get_preferenza_portale (p_stringa, p_modulo);
   END IF;
-- Ricerca preferenza a livello generale per lo specifico modulo
   IF d_valore IS NULL AND p_modulo IS NOT NULL THEN
      d_chiave := 'PRODUCTS/'||UPPER(p_modulo);
      Registro_Utility.leggi_stringa (d_chiave,p_stringa, d_valore, FALSE);
   END IF;
-- Ricerca preferenza a livello generale
   IF d_valore IS NULL THEN
      d_chiave := 'PRODUCTS/AMV';
      Registro_Utility.leggi_stringa (d_chiave,p_stringa, d_valore, FALSE);
   END IF;
   RETURN d_valore;
END get_preferenza;
FUNCTION get_preferenza (
  p_stringa VARCHAR2
, p_id_sezione NUMBER
, p_modulo VARCHAR2 DEFAULT NULL
, p_utente VARCHAR2 DEFAULT NULL
) RETURN VARCHAR2 IS
d_valore VARCHAR2(2000);
d_chiave VARCHAR2(512);
BEGIN
-- Ricerca preferenza a livello di sezione
   IF p_id_sezione != 0 THEN
      d_valore := Amv_Sezione.get_preferenza (p_id_sezione, REPLACE(p_stringa,' ','_'));
   END IF;
-- Ricerca preferenza da registro
   IF d_valore IS NULL OR p_id_sezione = 0 THEN
      d_valore := get_preferenza (p_stringa, p_modulo, p_utente);
   END IF;
   RETURN d_valore;
END get_preferenza;
FUNCTION get_preferenza_utente (p_stringa VARCHAR2, p_modulo VARCHAR2 DEFAULT NULL, p_utente VARCHAR2 DEFAULT NULL) RETURN VARCHAR2 IS
d_valore VARCHAR2(2000) := '';
d_chiave VARCHAR2(512);
BEGIN
-- Ricerca preferenza a livello utente di sistema con e senza modulo
   IF p_utente IS NOT NULL AND p_modulo IS NOT NULL THEN
      d_chiave := 'SI4_DB_USERS/'||p_utente||'|'||USER||'/PRODUCTS/'||UPPER(p_modulo);
      Registro_Utility.leggi_stringa (d_chiave,p_stringa, d_valore, FALSE);
   END IF;
   IF p_utente IS NOT NULL AND d_valore IS NULL THEN
      d_chiave := 'SI4_DB_USERS/'||p_utente||'|'||USER;
      Registro_Utility.leggi_stringa (d_chiave,p_stringa, d_valore, FALSE);
   END IF;
   RETURN d_valore;
END get_preferenza_utente;
FUNCTION get_preferenza_portale (p_stringa VARCHAR2, p_modulo VARCHAR2 DEFAULT NULL) RETURN VARCHAR2 IS
d_valore VARCHAR2(2000) := '';
d_chiave VARCHAR2(512);
BEGIN
-- Ricerca preferenza a livello di db user con e senza modulo
   IF p_modulo IS NOT NULL THEN
      d_chiave := 'DB_USERS/'||USER||'/PRODUCTS/'||UPPER(p_modulo);
      Registro_Utility.leggi_stringa (d_chiave,p_stringa, d_valore, FALSE);
   END IF;
   IF d_valore IS NULL THEN
      d_chiave := 'DB_USERS/'||USER;
      Registro_Utility.leggi_stringa (d_chiave,p_stringa, d_valore, FALSE);
   END IF;
   RETURN d_valore;
END get_preferenza_portale;
FUNCTION get_returnpage (p_stringa VARCHAR2) RETURN VARCHAR2 IS
d_return VARCHAR2(2000) := '';
d_img_src VARCHAR2(512) := '../common/images/AMV/undo.gif';
BEGIN
-- Ricerca preferenza a livello di db user con e senza modulo
   IF p_stringa IS NOT NULL THEN
      d_return := '<a href="'||p_stringa||'" title="Ritorna all''applicazione precedente"><img border ="0" align="absmiddle" src="'||d_img_src||'" ></a>';
   END IF;
   RETURN d_return;
END get_returnpage;
FUNCTION is_preferenza (p_stringa VARCHAR2, p_modulo VARCHAR2 DEFAULT NULL, p_utente VARCHAR2 DEFAULT NULL) RETURN VARCHAR2 IS
d_valore VARCHAR2(2000);
d_chiave VARCHAR2(512);
BEGIN
-- Ricerca preferenza a livello utente di sistema
   IF p_utente IS NOT NULL AND p_modulo IS NOT NULL THEN
      d_chiave := 'SI4_DB_USERS/'||p_utente||'|'||USER||'/PRODUCTS/'||UPPER(p_modulo);
      Registro_Utility.leggi_stringa (d_chiave,p_stringa, d_valore, FALSE);
     IF d_valore IS NOT NULL THEN
        RETURN 1;
     ELSE
        RETURN 0;
     END IF;
   END IF;
-- Ricerca preferenza a livello di db user
   IF p_modulo IS NOT NULL THEN
      d_chiave := 'DB_USERS/'||USER||'/PRODUCTS/'||UPPER(p_modulo);
      Registro_Utility.leggi_stringa (d_chiave,p_stringa, d_valore, FALSE);
     IF d_valore IS NOT NULL THEN
        RETURN 1;
     ELSE
        RETURN 0;
     END IF;
   ELSE
      d_chiave := 'DB_USERS/'||USER;
      Registro_Utility.leggi_stringa (d_chiave,p_stringa, d_valore, FALSE);
     IF d_valore IS NOT NULL THEN
        RETURN 1;
     ELSE
        RETURN 0;
     END IF;
   END IF;
END is_preferenza;
FUNCTION get (
  p_stringa VARCHAR2
, p_par1 VARCHAR2 DEFAULT NULL
, p_par2 VARCHAR2 DEFAULT NULL
, p_par3 VARCHAR2 DEFAULT NULL
, p_par4 VARCHAR2 DEFAULT NULL
) RETURN VARCHAR2 IS
d_valore    VARCHAR2(2000);
d_home_page VARCHAR2(100) := 'common/Main.do';
BEGIN
-- Ricerca stringhe in HEADER portale
-- MESSAGGIO (p_par1={Modulo}, p_par2={Utente}, p_par3={UserLogin})
   IF p_stringa = 'HEADER.MESSAGGIO' THEN
      SELECT REPLACE(NVL(Amvweb.get_preferenza('Welcome',p_par1),'Salve! Sei connesso come &'), '&', DECODE(p_par2,NULL,'Anonimo',NVL(p_par3,p_par2))) messaggio
       INTO d_valore
      FROM dual;
   END IF;
-- OGGI
   IF p_stringa = 'HEADER.OGGI' THEN
      SELECT TO_CHAR(SYSDATE,'Day, dd Month yyyy hh24:mi','NLS_DATE_LANGUAGE=Italian')
       INTO d_valore
      FROM dual;
   END IF;
-- NOTE (p_par1={Modulo}, p_par2={Note})
   IF p_stringa = 'HEADER.NOTE' THEN
      SELECT REPLACE(NVL(Amvweb.get_preferenza('Note',p_par1),'&'),'&',p_par2) note
       INTO d_valore
      FROM dual;
   END IF;
-- NUOVI_MSG (p_par1={Utente})
   IF p_stringa = 'HEADER.NUOVI_MSG' THEN
      SELECT Amv_Documento.get_nuovi_msg(p_par1) nuovi_msg
       INTO d_valore
      FROM dual;
   END IF;
-- MENUBAR (p_par1={Utente}, p_par2={Modulo}, p_par3={Ruolo}, p_par4={Istanza})
   IF p_stringa = 'HEADER.MENUBAR' THEN
      DECLARE
     d_usa_home_page NUMBER(1);
     d_servizio NUMBER(1);
     d_registrazione_lite VARCHAR2(100);
     d_pagina_registrazione VARCHAR2(1000) := 'AmvRegistrazioneInizio.do';
     d_link_applicazioni VARCHAR2(2);
     d_href_applicazioni VARCHAR2(1000);
     d_js_applicazioni VARCHAR2(1000);
     BEGIN
        BEGIN
            SELECT 1 INTO d_servizio
              FROM ad4_servizi
             WHERE modulo = p_par2
               AND istanza = p_par4
            AND NVL(p_par1,'GUEST') = 'GUEST'
            ;
       EXCEPTION WHEN NO_DATA_FOUND THEN
          d_servizio := 0;
       END;
--          SELECT DECODE(Amvweb.get_preferenza('HomePage Logged User',p_par2),'NO',1,DECODE(p_par1,'GUEST',0,1))
--             , Amvweb.get_preferenza('Registrazione Lite',p_par2)
--          INTO d_usa_home_page
--             , d_registrazione_lite
--          FROM dual;
        select decode(Amvweb.get_preferenza('HomePage Logged User',p_par2),'NO',1,DECODE(p_par1,'GUEST',0,1))
             , Amvweb.get_preferenza('Registrazione Lite',p_par2)
             , Amvweb.get_preferenza('Link Applicazioni',p_par2)
          into d_usa_home_page
             , d_registrazione_lite
          , d_link_applicazioni
          from dual;
       IF UPPER(d_registrazione_lite) = 'SI' THEN
          d_pagina_registrazione := 'AmvRegistrazioneLiteInizio.do';
         END IF;
        select '<a class="AFCHeaderBarMenu" href='
                 ||decode(d_usa_home_page,1,nvl(amvweb.get_preferenza('HomePage',p_par2),'"../common/Main.do?MVPD=0"'),'"../common/Main.do?MVPD=0"')||' title="Pagina Iniziale">Home</a>'
                 ||decode(nvl(p_par1,'GUEST')
                        ,'GUEST','&nbsp;|&nbsp;<a class="AFCHeaderBarMenu" href="../restrict/SecureLoginPost.jsp" title="Login al portale">Login</a>'
                        ,'&nbsp;|&nbsp;<a class="AFCHeaderBarMenu" href="../common/Logout.do" title="Disconnessione Utente"'||d_js_applicazioni||'>Logout</a>'
                   )
                 ||decode(d_servizio,1,'&nbsp;|&nbsp;<a class="AFCHeaderBarMenu" href="../common/'||d_pagina_registrazione||'?MVTD=Registrazione" title="Richiesta di registrazione al portale" >Registrazione'||'</a>'
                       ,decode(p_par3,'GUEST','',d_href_applicazioni||'&nbsp;|&nbsp;<a class="AFCHeaderBarMenu" href="../restrict/AmvProfilo.do?MVVC=amvprof&amp;MVPD=0"  title="Profilo Utente">Profilo</a>')
                     )
                 ||decode(amvweb.get_preferenza('Menu Bar Documenti',p_par2),null,null,'&nbsp;|&nbsp;<a class="AFCHeaderBarMenu" href="../common/AmvDocumentiRicerca.do?MVVC=amvdocur" title="Ricerca Documenti">'||amvweb.get_preferenza('Menu Bar Documenti',p_par2)||'</a>')
                 ||decode(amv_documento.get_link_documenti('A'),null,null,'&nbsp;|&nbsp;'||amv_documento.get_link_documenti('A','class="AFCHeaderBarMenu"')) menubar
           into d_valore
           from dual;
--         SELECT '<a class="AFCHeaderBarMenu" href='
--               ||DECODE(d_usa_home_page,1,NVL(Amvweb.get_preferenza('HomePage',p_par2),'"../common/Main.do?MVPD=0"'),'"../common/Main.do?MVPD=0"')||' title="Pagina Iniziale">Home</a>'
--                ||DECODE(NVL(p_par1,'GUEST')
--                ,'GUEST','&'||'nbsp;|&'||'nbsp;<a class="AFCHeaderBarMenu" href="../restrict/index.do?MVTD=Login" title="Login al portale">Login</a>'
--                        ,'&'||'nbsp;|&'||'nbsp;<a class="AFCHeaderBarMenu" href="../common/Logout.do" title="Disconnessione Utente">Logout</a>'
--              )
--         ||DECODE(d_servizio,1,'&'||'nbsp;|&'||'nbsp;<a class="AFCHeaderBarMenu" href="'||d_pagina_registrazione||'?MVTD=Registrazione" title="Richiesta di registrazione al portale">Registrazione'||'</a>',
--          DECODE(p_par3,'GUEST','','&'||'nbsp;|&'||'nbsp;<a class="AFCHeaderBarMenu" href="../restrict/AmvProfilo.do?MVVC=amvprof&'||'MVPD=0"  title="Profilo Utente">Profilo')||'</a>')
--         ||DECODE(Amvweb.get_preferenza('Menu Bar Documenti',p_par2),NULL,NULL,'&'||'nbsp;|&'||'nbsp;<a class="AFCHeaderBarMenu" href="../common/AmvDocumentiRicerca.do?MVVC=amvdocur" title="Ricerca Documenti">'||Amvweb.get_preferenza('Menu Bar Documenti',p_par2)||'</a>')
--          ||DECODE(Amv_Documento.get_link_documenti('A'),NULL,NULL,'&'||'nbsp;|&'||'nbsp;'||Amv_Documento.get_link_documenti('A','class="AFCHeaderBarMenu"')) menubar
--          INTO d_valore
--          FROM dual;
      END;
   END IF;
   RETURN d_valore;
END get;
FUNCTION SEND_MSG
/******************************************************************************
 NOME:        SEND_MSG
 DESCRIZIONE: Invia un messaggio usando il Si4cimPlSqlJ.
 PARAMETRI:   sender   varchar2 Mittente del messaggio
              receiver varchar2 Destinatario
              subject  varchar2 Oggetto del messaggio
              text     varchar2 Testo del messaggio
              msg_type varchar2 Tipo del messaggio configurati nel SI4CIM
 RITORNA:     varchar2 Eventuale messaggio di errore del SI4CIM
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    30/09/2004 MM     Prima emissione.
******************************************************************************/
( sender IN VARCHAR2
, receiver IN VARCHAR2
, subject IN VARCHAR2
, text IN VARCHAR2
, msg_type IN VARCHAR2
, transport_user IN VARCHAR2 DEFAULT NULL
, transport_pwd IN VARCHAR2 DEFAULT NULL
)
RETURN INTEGER
AS
  d_err NUMBER;
  d_error VARCHAR2(2000);
  ERRORE EXCEPTION;
BEGIN
    BEGIN
               d_err := 0;
               /*-----------------------------------------------------
                  Inizializza il CIM con il tipo di messaggio da
                  inviare.
               -----------------------------------------------------*/
               d_err := ad4_Si4cim.initializeMessage(msg_type);
               /*-----------------------------------------------------
                              Inizializza il Mittente.
               -----------------------------------------------------*/
            IF transport_user IS NOT NULL THEN
                  ad4_Si4cim.setSender
                           ( senderIP  => ''              , senderName  => ''
                           , ID        => ''              , NAME        => sender
                           , company   => ''              , address     => ''
                           , code      => ''              , city        => ''
                           , province  => ''              , state       => ''
                           , email     => sender          , phoneNumber => sender
                           , faxNumber => ''              , USER        => transport_user
                     , PASSWORD  => transport_pwd);
            ELSE
                  ad4_Si4cim.setSender
                           ( senderIP  => ''              , senderName  => ''
                           , ID        => ''              , NAME        => sender
                           , company   => ''              , address     => ''
                           , code      => ''              , city        => ''
                           , province  => ''              , state       => ''
                           , email     => sender          , phoneNumber => sender
                           , faxNumber => '');
               END IF;
               /*-----------------------------------------------------
                            Inizializza il Destinatario.
               -----------------------------------------------------*/
               ad4_Si4cim.addRecipient
                        ( ID        => ''         , NAME        => receiver
                        , company   => ''         , address     => ''
                        , code      => ''         , city        => ''
                        , province  => ''         , state       => ''
                        , email     => receiver   , phoneNumber => receiver
                        , faxNumber => '');
               /*-----------------------------------------------------
                        Predispone l'oggetto del messaggio.
               -----------------------------------------------------*/
               ad4_Si4cim.setSubject(subject);
               /*-----------------------------------------------------
                        Predispone il testo del messaggio.
               -----------------------------------------------------*/
               ad4_Si4cim.setText(text);
               /*-----------------------------------------------------
                               Invia il messaggio.
               -----------------------------------------------------*/
               d_err := ad4_Si4cim.send;
            RETURN d_err;
            EXCEPTION WHEN OTHERS THEN
               d_error := SUBSTR(SQLERRM, 1, 250);
               RAISE ERRORE;
            END;
END SEND_MSG;
PROCEDURE registra_accesso
/******************************************************************************
 NOME:        REGISTRA_ACCESSO
 DESCRIZIONE: setta variabili di package e richiama la registrazione accesso di ad4
              viene richiamata dalle classi java codecharge prima che vengano eseguite istruzioni di DLM o
           chiamate a procedure.
 PARAMETRI:        p_istanza varchar2
                   p_modulo  varchar2
                   p_utente  varchar2
                   p_note    varchar2
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    16/10/2006 AO     Prima emissione.
******************************************************************************/
(
     p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2
   , p_note VARCHAR2
) IS
  d_session_id   NUMBER;
  d_log          VARCHAR2(1000);
  d_ente         VARCHAR2(10);
  d_tipo_traccia VARCHAR2(10);
  d_progetto     VARCHAR2(8);
  d_accesso      VARCHAR2(500);
  d_accesso_se   VARCHAR2(500);
  d_tipo_accesso VARCHAR2(1) := 'D';
  d_giorni_traccia NUMBER(10);
  d_tentativi_pwd  NUMBER(10);
  d_val_pwd        NUMBER(10);
BEGIN
   SELECT ente
         ,progetto
     INTO d_ente
         ,d_progetto
     FROM ad4_istanze
    WHERE istanza = p_istanza;
   ad4_accesso.get_caac_effettive(d_tipo_accesso
                                   ,d_progetto
                                   ,p_istanza
                                   ,p_modulo
                                   ,p_utente
                                   ,d_accesso
                                   ,d_accesso_se
                                   ,d_tipo_traccia
                                   ,d_giorni_traccia
                                   ,d_tentativi_pwd
                                   ,d_val_pwd)
   ;
   SELECT USERENV('sessionid')
     INTO d_session_id
     FROM dual;
   d_log := 'TRC';
   Si4.set_accesso_utente( p_utente, p_note, p_modulo,'',p_istanza,'','',d_ente,'',d_progetto,'','');
   IF d_tipo_traccia = 'I' THEN
      ad4_accesso.registra_accesso(d_session_id,d_log,p_istanza,p_modulo,p_utente,p_note);
   END IF;
EXCEPTION WHEN OTHERS THEN NULL;
END registra_accesso;


END Amvweb;
/
