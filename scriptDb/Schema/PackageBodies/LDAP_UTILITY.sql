CREATE OR REPLACE PACKAGE BODY LDAP_UTILITY
IS
   /******************************************************************************
    NOME:        .
    DESCRIZIONE: .
    ANNOTAZIONI: .
    REVISIONI: .
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    000  25/09/2006  MM    Creazione.
    001  17/3/2008   SN    Inserimento allineamento struttura ldap con AD4.
    002  24/09/2009 SNeg   Sistemazioni varie
    003  18/01/2010  SNeg   Modifica struttura albero ldap generato.
    004  09/02/2010 SNeg   Scarico da ldap a pezzi per evitare limit exceeded
    005  12/03/2010 SNeg   Valorizzazione variabili se iniziano con =
    006 13/05/2010  SNeg  Elimino protezione apici se non inizia con =.
    007  21/05/2010  SNeg  Sistemazione Formatta struttura per dati corretti
    008  29/06/2010  SNeg  Rigenera struttura anche se non necessario allineamento
                           Ldap
    009  06/08/2010  SNeg  Verifica se cambiata solo la descrizione
    010  29/09/2010  SNeg   Inserimento funzione di concatenazione
    011  28/03/2012  SNeg  Predisposta mail di attività di allineamento
    012 30/07/2012   SNeg  Sistemazioni varie per problemi rilevati
    013 21/09/2012  Sneg  Lettura da registro per non modificare utenti "bloccati"
                         mediante impostazione di valori su AD
    014 21/11/2012  SNeg Modifica al commento nel log
    015 19/02/2014 SN  Tolto raddoppio apici sul nome
    016 19/02/2014 SN  Tolto raddoppio apici sul nome
    017 28/05/2018 SN  Default parametro per schedulare il job a null
   ******************************************************************************/
   s_revisione_body CONSTANT   VARCHAR2 (30) := '017';
   SUBTYPE ldap_attribute IS VARCHAR2 (4000);
   ldap_url                    ldap_attribute;
   ldap_user                   ldap_attribute;
   ldap_password               ldap_attribute;
   ldap_rootou                 ldap_attribute;
   ldap_contestogruppi         ldap_attribute;
   ldap_root                   ldap_attribute;
   ldap_tipo_autenticazione    ldap_attribute;
   ldap_suffissonominativo     ldap_attribute;
   ldap_mapping                ldap_attribute;
   ldap_case                   ldap_attribute;
   so4_mapping                 ldap_attribute;
   ldap_ignore_attr_errors     ldap_attribute;
   ldap_root_users             ldap_attribute;
   ldap_root_users_disabled    ldap_attribute;
   ldap_noupdate_campo         ldap_attribute;
   ldap_noupdate_valore        ldap_attribute;
   v_trovato_dn                ldap_attribute;
   ldap_camponominativo        ldap_attribute;
   d_separatore                VARCHAR2 (1) := ',';
   ldap_delimitatore           VARCHAR2(2);
   v_id_allegato               ALLINEAMENTO_SO4_LDAP.ID_ALLEGATO%TYPE:= 0;
   v_nome_allegato             ALLINEAMENTO_SO4_LDAP.NOME%TYPE;
   d_clob                      CLOB := EMPTY_CLOB();
   v_dicitura_allineamento     VARCHAR2(100):= 'Allineamento AD4-LDAP del ';
   v_dicitura_allineamento_inizio VARCHAR2(100);
   d_sender_email              VARCHAR2(2000);
   d_registration_email        VARCHAR2(2000);
   v_intestazione_messaggi varchar2(2000);
   FUNCTION versione
      RETURN VARCHAR2
   IS
   /******************************************************************************
    NOME:        versione.
    DESCRIZIONE: Restituisce versione e revisione di distribuzione del package.
    RITORNA:     VARCHAR2 stringa contenente versione e revisione.
    NOTE:        Primo numero  : versione compatibilita del Package.
                 Secondo numero: revisione del Package specification.
                 Terzo numero  : revisione del Package body.
   ******************************************************************************/
   BEGIN
      RETURN s_revisione || '.' || NVL (s_revisione_body, '000');
   END versione;
   FUNCTION esegui_calcolo
   (p_valore varchar2,
   p_prima varchar2 default  null,
   p_dopo varchar2 default null)
   return ldap_attribute
   IS
   d_valore ldap_attribute:= ltrim(ltrim(p_valore),'=');
   BEGIN
      execute immediate
      'begin :d_valore := '|| p_prima|| d_valore || p_dopo || ';end; '
      using OUT d_valore;
      return d_valore;
   END;
   function sistema_case (p_valore varchar2)
   return ldap_attribute
   is
   begin
     return esegui_calcolo(replace(p_valore,'''',''''''),ldap_case || '(''', ''')');
   end;
   procedure scrivi_allegato (p_testo varchar2)
   IS
   v_giorni_conservazione number ;
   v_data_allineamento date:= sysdate;
   BEGIN
      if v_id_allegato = 0 then -- non ancora inserito nulla
          -- Pulizia allegati più vecchi dei giorni di conservazione previsti
          v_giorni_conservazione   :=
             nvl(registro_utility.leggi_stringa ('PRODUCTS/LDAPCONFIG'
                                           , 'GiorniConservazione'
                                           ,0
             ),90);
             -- commentata cancellazione allegati
           delete ALLINEAMENTO_SO4_LDAP
           where trunc(sysdate) - trunc(data) > v_giorni_conservazione;
           commit;
          select ALSL_SQ.NEXTVAL
               into v_iD_ALLEGATO
               from dual;
          v_nome_allegato := replace(rtrim(v_dicitura_allineamento,' del '),' ','_')||'_'|| to_char(v_data_allineamento,'yyyy_mm_dd_hh24_mi' )||'.txt';
          v_dicitura_allineamento_inizio := v_dicitura_allineamento|| to_char(v_data_allineamento, 'dd/mm/yyyy hh24:mi:ss');
          Insert into ALLINEAMENTO_SO4_LDAP
              (ID_ALLEGATO, NOME, note)
          Values
               (v_iD_ALLEGATO,v_nome_allegato, v_dicitura_allineamento_inizio);
          commit;
          d_clob := afc_lob.c_ADD (d_clob,  chr(10) ||v_dicitura_allineamento_inizio|| chr(10)|| chr(10)) ;
          update ALLINEAMENTO_SO4_LDAP
             set allegato = d_clob
           where id_allegato = v_id_allegato;
          commit;
      end if; --  non esiste allegato
      d_clob := afc_lob.c_ADD (d_clob, v_intestazione_messaggi || chr(10) || p_testo|| chr(10)) ;
      v_intestazione_messaggi := null;
      update ALLINEAMENTO_SO4_LDAP
         set allegato = d_clob
       where id_allegato = v_id_allegato;
      commit;
   END;
   procedure scrivi_segnalazione(p_testo varchar2, p_livello varchar2 default 0)
   is
   begin
   if false and p_livello <= 2 then
    key_error_log_tpk.ins (p_error_id => null
                                  , p_error_session => USERENV('sessionid')
                                  , p_error_date => SYSDATE
                                  , p_error_text => p_testo
                                  , p_error_user => USER
                                  , p_error_usertext => NULL
                                  , p_error_type => 'S'
                                 );
                                 COMMIT;
    end if;
   end;
   --
   FUNCTION crea_server_alternativo
      RETURN VARCHAR2
   IS
      i          INTEGER := 0;
      d_test     VARCHAR2 (1000);
      d_chiave   VARCHAR2 (1000);
   BEGIN
      WHILE i <= 9
      LOOP
         BEGIN
            d_chiave   := 'PRODUCTS/LDAPCONFIG/SERVER' || i;
            SELECT NVL (registro_utility.get_stringa (d_chiave
                                                    , '(Predefinito)'
                        )
                      , ' '
                   )
            INTO d_test
            FROM DUAL;
         EXCEPTION
            WHEN OTHERS
            THEN
               IF SQLCODE = -20922
               THEN
                  d_test   := '';
                  EXIT;
               ELSE
                  RAISE;
               END IF;
         END;
         i   := i + 1;
      END LOOP;
      IF d_test IS NULL
      THEN
         BEGIN
            INSERT INTO registro
           (
               chiave, stringa, valore, commento
           )
               SELECT d_chiave
                    , stringa
                    , DECODE (LOWER (stringa), 'contestogruppi', valore, '')
                    , commento
               FROM registro
               WHERE chiave = 'PRODUCTS/LDAPCONFIG'
                     AND LOWER (stringa) IN
                              ('(predefinito)'
                             , 'server'
                             , 'porta'
                             , 'utenteconnessioneldap'
                             , 'pwdconnessioneldap'
                             , 'contesto'
                             , 'suffissonominativo'
                             , 'contestogruppi'
                             , 'rootou'
                             , 'tipoautenticazione' -- e gli altri?????????????
                                                   );
         EXCEPTION
            WHEN OTHERS
            THEN
               RAISE;
         END;
         RETURN d_chiave;
      ELSE
         raise_application_error (-20999
                                , 'Il numero massimo di server alternativi e'' 9.'
         );
      END IF;
   END crea_server_alternativo;
   PROCEDURE get_parametri_ldap
   /******************************************************************************
    NOME:        get_parametri_ldap.
    DESCRIZIONE: Legge la tabella REGISTRO per le impostazioni.Se anche ci sono più
    server ldap impostati legge comunque solo i dati relativi al server principale.
    NOTE:
   ******************************************************************************/
   IS
      d_chiave             VARCHAR2 (30) := 'PRODUCTS/LDAPCONFIG';
      d_tipo_connessione   VARCHAR2 (10) := 'ldap';
   BEGIN
      ldap_noupdate_campo    :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'NoUpdateLDAPCampo'
                                       , 1
         );
      ldap_noupdate_valore  :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'NoUpdateLDAPValore'
                                       , 1
         );
      ldap_tipo_autenticazione   :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'TIPOAUTENTICAZIONE'
                                       , 1
         );
      IF ldap_tipo_autenticazione = 'SSL'
      THEN
         d_tipo_connessione   := 'ldaps';
      END IF;
         d_sender_email   :=
         replace(registro_utility.leggi_stringa ('PRODUCTS/LDAPCONFIG/NOTIFICA'
                                       ,'Sender'
                                       , 1
         ) ,' ' ,'');
         d_registration_email   :=
         registro_utility.leggi_stringa ('PRODUCTS/LDAPCONFIG/NOTIFICA'
                                       ,'RegistrationEmailAddress'
                                       , 1
         );
      ldap_url   :=
            d_tipo_connessione
         || '://'
         || registro_utility.leggi_stringa (UPPER (d_chiave)
                                          , 'SERVER'
                                          , 1
            )
         || ':'
         || registro_utility.leggi_stringa (UPPER (d_chiave)
                                          , 'PORTA'
                                          , 1
            );
      ldap_user   :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'UTENTECONNESSIONELDAP'
                                       , 1
         );
      ldap_password   :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'PWDCONNESSIONELDAP'
                                       , 1
         );
      ldap_rootou   :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'ROOTOU'
                                       , 1
         );
      IF ldap_rootou IS NOT NULL
      THEN
         ldap_rootou   := d_separatore || ldap_rootou;
      END IF;
      ldap_contestogruppi   :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'CONTESTOGRUPPI'
                                       , 1
         );
      ldap_root   :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'CONTESTO'
                                       , 1
         );
      ldap_suffissonominativo   :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'SUFFISSONOMINATIVO'
                                       , 1
         );
      ldap_mapping   :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'LDAPMAPPING'
                                       , 1
         );
       ldap_case :=
        nvl( registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'LdapCase'
                                       , 1
         ),'lower');
      so4_mapping   :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'SO4MAPPING'
                                       , 1
         );
      ldap_ignore_attr_errors   :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'IGNOREATTRIBUTEERRORS'
                                       , 1
         );
      ldap_root_users   :=
         registro_utility.leggi_stringa (UPPER (d_chiave)
                                       , 'ROOTUSERS'
                                       , 1
         );
      ldap_root_users_disabled   :=
         NVL (registro_utility.leggi_stringa (UPPER (d_chiave)
                                            , 'ROOTUSERSDISABLED'
                                            , 1
              )
            , ldap_root_users
         );
      ldap_delimitatore   :=
         nvl(registro_utility.leggi_stringa (UPPER (d_chiave)
                                            , 'DELIMITATORE'
                                            , 1
         ),'##');
      ldap_camponominativo :=
         registro_utility.leggi_stringa (UPPER ('PRODUCTS/LDAPCONFIG')
                                       , 'CampoNominativoInLDAP'
                                       , 0)
         ;
   END;
   FUNCTION pulisci (p_stringa varchar2)
   RETURN VARCHAR2
   IS
   BEGIN
      return replace(replace(replace(replace(replace(p_stringa,',',''),';',''),'"',''),':',''),'/','');
   END;
   FUNCTION concatena (p_stringa1 varchar2
                     , p_stringa2 varchar2
   )
      RETURN VARCHAR2
   IS
   BEGIN
      IF ldap_delimitatore IS NULL
      THEN
         get_parametri_ldap;      -- carica gli attributi per lavorare su ldap
      END IF;
      return p_stringa1 || ldap_delimitatore || p_stringa2;
   END;
    FUNCTION get_delimitatore
      RETURN VARCHAR2
   IS
   BEGIN
      IF ldap_delimitatore IS NULL
      THEN
         get_parametri_ldap;      -- carica gli attributi per lavorare su ldap
      END IF;
      return  ldap_delimitatore;
    END;
       FUNCTION get_RootOu
      RETURN VARCHAR2
   IS
   BEGIN
      IF ldap_rootou IS NULL
      THEN
         get_parametri_ldap;      -- carica gli attributi per lavorare su ldap
      END IF;
      return ltrim( ldap_rootou,',');
    END;
    FUNCTION get_RootUsers
      RETURN VARCHAR2
   IS
   BEGIN
      IF ldap_root_Users IS NULL
      THEN
         get_parametri_ldap;      -- carica gli attributi per lavorare su ldap
      END IF;
      return  ldap_root_users;
    END;
   FUNCTION formatta_struttura (p_struttura varchar2
                              , p_figlio varchar2
                              , p_tipo varchar2  -- O,G
   )
      RETURN VARCHAR2
   IS
      d_ldap_struttura    VARCHAR2 (32767);
      d_struttura         VARCHAR2 (32767);
      v_tipo              VARCHAR2 (100);
      v_nome              VARCHAR2 (32767);
      v_utente_o_gruppo   varchar2(50);
      v_nominativo        varchar2(50);
      v_desc_abbreviata   VARCHAR2 (1000);
      v_aoo               VARCHAR2 (100);
      v_separa_in_utente  VARCHAR2 (1) := '-';
      v_tipo_ldap         VARCHAR2 (2);
      v_amministrazione   anag_unita.amministrazione%TYPE;
      v_utente_ad4        utenti.utente%TYPE;
      v_lun_descrizione   constant number  := 36;
      v_progr_aoo       ANAG_UNITA.PROGR_AOO%TYPE;
   BEGIN
      IF ldap_delimitatore IS NULL
      THEN
         get_parametri_ldap;      -- carica gli attributi per lavorare su ldap
      END IF;
      -- formatta la struttura
      -- tipo_utente#utente#descrizione
      -- Es:input:  O#UO2#Uo2[O#UO1#Uo1[U#STEFANIA#STEFANIA
      -- Es:output: ,OU=Uo1,OU=Uo2
      IF v_tipo IS NULL
      THEN
         v_tipo   := p_tipo;
      END IF;
--      IF p_tipo = 'G'
--      THEN
--         v_tipo_ldap   := 'CN';
--      ELSE
         v_tipo_ldap   := 'OU';
--      END IF;
      IF INSTR (p_struttura
              , '['
         ) > 1
      THEN
         d_struttura   :=
            SUBSTR (p_struttura
                  , 1
                  , INSTR (p_struttura
                         , '['
                         , -1
                         , 1
                    )
            );
         WHILE INSTR (d_struttura
                    , '['
               ) > 0                         --'G#CUCU#Cucu[G#CACCOLI#Caccoli'
                                                                   -- ,CN=Cucu
         LOOP
            v_tipo   :=
               NVL (SUBSTR (d_struttura
                          , 1
                          , INSTR (d_struttura
                                 , '#'
                            )
                            - 1
                    )
                  , p_tipo
               );
            IF v_tipo != 'I'
            THEN  --  NON e un incarico
            IF v_tipo != 'R'
            THEN                                      -- non e un ruolo di So4
               -- es: O#3#3-AOOPMO[O#31#3.1-AOOPMO[O#310#3.1.0/AOOPMO
               v_nome   := -- per questo esempio = 3-AOOPMO
                  SUBSTR (d_struttura
                        , INSTR (d_struttura
                               , '#'
                               , 1
                               , 2
                          )
                          + 1
                        ,   INSTR (d_struttura
                                 , '['
                            )
                          - INSTR (d_struttura
                                 , '#'
                                 , 1
                                 , 2
                            )
                          - 1
                  );
               v_utente_o_gruppo   := -- per questo esempio = 3
                  SUBSTR (d_struttura
                        , INSTR (d_struttura
                               , '#'
                               , 1
                          )
                          + 1
                        ,   INSTR (d_struttura
                                 , '#'
                                 , 1
                                 , 2
                            )
                          - INSTR (d_struttura
                                 , '#'
                                 , 1
                            )
                          - 1
                  );
            ELSIF v_tipo ='R' THEN-- ruolo di SO4 es: R#SCRIVANI#Accesso alla Scrivania Virtuale#SCRIVANI[U#STEFANIA
            v_tipo_ldap   := 'CN';
            v_desc_abbreviata := v_utente_o_gruppo; -- tengo valore della ou precedente
               v_nome   := -- per questo esempio = Accesso alla Scrivania Virtuale
                  SUBSTR (d_struttura
                        , INSTR (d_struttura
                               , '#'
                               , 1
                               , 2
                          )
                          + 1
                        ,   INSTR (d_struttura
                                 , '#'
                                 , 1
                                 , 3
                            )
                          - INSTR (d_struttura
                                 , '#'
                                 , 1
                                 , 2
                            )
                          - 1
                  );
               v_utente_o_gruppo   := -- per questo esempio = SCRIVANI
                  SUBSTR (d_struttura
                        , INSTR (d_struttura
                               , '#'
                               , 1
                          )
                          + 1
                        ,   INSTR (d_struttura
                                 , '#'
                                 , 1
                                 , 2
                            )
                          - INSTR (d_struttura
                                 , '#'
                                 , 1
                            )
                          - 1
                  );
            END IF;
            IF utente.get_tipo_utente (v_utente_o_gruppo) = 'U'
            THEN
               v_nominativo   :=
                  utente.get_nominativo (v_utente_o_gruppo
                                       , 'Y'
                                       , 0
                  );
            ELSE
               v_nominativo   := gruppo.get_descrizione (v_utente_o_gruppo);
            END IF;
            IF INSTR (v_nominativo
                    , '-'
               ) > 0
            THEN
               v_separa_in_utente   := '-';
            ELSE
               v_separa_in_utente   := '/';
            END IF;
            if v_tipo != 'R' then
         -- informazioni non presenti per ruolo
         -- utente in ad4 creato con nominativo = descr_abbreviata ||'-' || v_aoo
            v_desc_abbreviata   :=
               SUBSTR (v_nominativo
                     , 1
                     , INSTR (v_nominativo
                            , v_separa_in_utente
                       )
                       - 1
               );
            v_aoo   :=
               SUBSTR (v_nominativo
                     , INSTR (v_nominativo
                            , v_separa_in_utente
                       )
                       + 1
               );
                        -- ricalcolo le info
            select trim(codice_uo)
                 , trim(decode(des_abb,codice_uo,substr(DES_UNITA_ORGANIZZATIVA,1,v_lun_descrizione), des_abb))
                 , trim(amministrazione)
                 , trim(utente_ad4)
                 , trim(progr_aoo)
              into v_utente_o_gruppo
                 , v_desc_abbreviata
                 , v_amministrazione
                 , v_utente_ad4
                 , v_progr_aoo
              from anag_unita
             where des_abb =  v_desc_abbreviata
               and CODICE_AOO = v_aoo
               and utente_ad4 = v_utente_o_gruppo;
            end if;
            END IF; -- non e I
            IF v_tipo = 'I' then
            -- es: 'O#0#0-AOOPMO[I#H#Direttore Generale['
            v_tipo_ldap   := 'CN';
            v_desc_abbreviata := v_utente_o_gruppo; -- tengo valore della ou precedente
               v_utente_o_gruppo   :=
                  SUBSTR (d_struttura
                        , INSTR (d_struttura
                               , '#'
                               , 1
                               , 2
                          )
                          + 1
                        ,   INSTR (d_struttura
                                 , '['
                            )
                          - INSTR (d_struttura
                                 , '#'
                                 , 1
                                 , 2
                            )
                          - 1
                  );
               v_nome   :=
                  SUBSTR (d_struttura
                        , INSTR (d_struttura
                               , '#'
                               , 1
                          )
                          + 1
                        ,   INSTR (d_struttura
                                 , '#'
                                 , 1
                                 , 2
                            )
                          - INSTR (d_struttura
                                 , '#'
                                 , 1
                            )
                          - 1
                  );
             END IF;
             if v_tipo != 'R' then
            d_ldap_struttura   :=
                  ','
                  ||v_tipo_ldap
               || '='
               || pulisci(v_utente_o_gruppo)
               || ldap_delimitatore
--               || replace(replace(v_aoo,',',''),';','')
--               || ldap_delimitatore
               || pulisci(v_desc_abbreviata)
               || ldap_delimitatore
               || pulisci(v_utente_ad4) --le metto anche per i padri negli incarichi
               || d_ldap_struttura;
              else -- v_tipo = 'R'
               d_ldap_struttura   :=
                  ','
                  ||v_tipo_ldap
               || '='
               || pulisci(v_utente_o_gruppo)
               || ldap_delimitatore
--               || replace(replace(v_aoo,',',''),';','')
--               || ldap_delimitatore
               || pulisci(v_desc_abbreviata)
--               || ldap_delimitatore
--               || pulisci(v_utente_ad4) --le metto anche per i padri negli incarichi
               || d_ldap_struttura;
              end if;
            d_struttura   :=
               SUBSTR (d_struttura
                     , INSTR (d_struttura
                            , '['
                       )
                       + 1
               );
         END LOOP;
      ELSE                                               -- non sottostrutture
         d_ldap_struttura   := '';
      END IF;
      IF v_tipo != 'R' and v_tipo != 'I' -- non e ruolo o incarico
      THEN
       if p_figlio is not null then
         -- attacco davanti le informazioni del figlio
         IF utente.get_tipo_utente (p_figlio) = 'U'
         THEN
            v_nominativo   :=
               utente.get_nominativo (p_figlio
                                    , 'Y'
                                    , 0
               );
         ELSE
            v_nominativo   := gruppo.get_descrizione (p_figlio);
         END IF;
         IF INSTR (v_nominativo
                 , '-'
            ) > 0
         THEN
            v_separa_in_utente   := '-';
         ELSE
            v_separa_in_utente   := '/';
         END IF;
         if v_tipo != 'R' then
         -- informazioni non presenti per ruolo
         v_desc_abbreviata   :=
            SUBSTR (v_nominativo
                  , 1
                  , INSTR (v_nominativo
                         , v_separa_in_utente
                    )
                    - 1
            );
         v_aoo   :=
            SUBSTR (v_nominativo
                  , INSTR (v_nominativo
                         , v_separa_in_utente
                    )
                    + 1
            );
                  -- ricalcolo le info
           select trim(codice_uo)
                 , trim(decode(des_abb,codice_uo,substr(DES_UNITA_ORGANIZZATIVA,1,v_lun_descrizione), des_abb))
                 , trim(amministrazione)
                 , trim(utente_ad4)
                 , trim(progr_aoo)
              into v_utente_o_gruppo
                 , v_desc_abbreviata
                 , v_amministrazione
                 , v_utente_ad4
                 , v_progr_aoo
              from anag_unita
             where des_abb =  v_desc_abbreviata
               and CODICE_AOO = v_aoo
               and utente_ad4= p_figlio;
            end if;
         d_ldap_struttura   :=
               ','
               ||v_tipo_ldap
            || '='
            || pulisci(v_utente_o_gruppo) -- era p_figlio
            || ldap_delimitatore
--            || replace(replace(v_aoo,',',''),';','')
--            || ldap_delimitatore
            || pulisci(v_desc_abbreviata)
            || ldap_delimitatore
            || pulisci(v_utente_ad4)
            || d_ldap_struttura;
          end if; -- solo se indicato p_figlio
         IF p_tipo = 'G'
         THEN
            --creazione del gruppo con stesso nome della ou
            d_ldap_struttura   :=
                  ','
               || 'CN='
               || pulisci(v_utente_o_gruppo) --  nvl(p_figlio,v_utente_o_gruppo)
               || ldap_delimitatore
--               || replace(replace(v_aoo,',',''),';','')
--               || ldap_delimitatore
               || pulisci(v_desc_abbreviata)
               || ldap_delimitatore
               || pulisci(v_utente_ad4)
               || d_ldap_struttura;
         END IF;
      END IF;
      -- aggiunta info amministrazione e aoo
      d_ldap_struttura := d_ldap_struttura || ',OU='||v_aoo || ldap_delimitatore||v_progr_aoo || ',OU='|| v_amministrazione;
      RETURN d_ldap_struttura;
      EXCEPTION
      WHEN OTHERS THEN
        return -1;
   END formatta_struttura;
   FUNCTION get_name (p_cn varchar2)
      RETURN VARCHAR2
   IS
      d_return   VARCHAR2 (32000);
   BEGIN
      IF ldap_url IS NULL
      THEN
         get_parametri_ldap;      -- carica gli attributi per lavorare su ldap
      END IF;
      d_return   :=
         finmatica_ldap.get_distinguished_name (ldap_url
                                              , ldap_user
                                              , ldap_password
                                              , LTRIM (ldap_root_users
                                                     , ','
                                                )
                                              , p_cn
         );
      IF d_return IS NULL
      THEN
         d_return   := p_cn;
      END IF;
      RETURN d_return;
   END;
      PROCEDURE set_attributo (p_dn varchar2
                          , p_stringa varchar2
                          , p_valore varchar2
                          , p_scrivi_allegato number default 1
   )
   IS
   v_errore varchar2(5000);
   BEGIN
      IF ldap_url IS NULL OR ldap_user IS NULL
      THEN
         get_parametri_ldap;
      END IF;
      scrivi_segnalazione('prima add attribute',2);
      scrivi_segnalazione( p_dn
                                  ||':'|| p_stringa
                                  ||':'|| p_valore,2);
     if upper(p_stringa) != 'UNICODEPWD' then
        --scrivi_allegato(p_stringa || ':'|| p_dn||' attributo  '|| p_stringa ||' = '|| p_valore);
        if p_scrivi_allegato = 1 then
           scrivi_allegato('           '||
                         AFC.GET_SUBSTR(p_dn,',','P')||' <<<' ||  p_stringa ||'>>> = '|| p_valore);
        end if;
     end if;
    v_errore :=  finmatica_ldap.add_attribute (ldap_url
                                  , ldap_user
                                  , ldap_password
                                  , p_dn
                                  , p_stringa
                                  , p_valore
      );
      if v_errore != 'OK' then
         v_errore := finmatica_ldap.modify_attribute (ldap_url
                                                  , ldap_user
                                                  , ldap_password
                                                  , p_dn
                                                  , p_stringa
                                                  , p_valore
                                                      );
--                 IF UPPER (ldap_ignore_attr_errors) = 'YES'
--                  THEN
--                     NULL;
--                  ELSE
           if v_errore != 'OK' then
             scrivi_allegato ('>> ERRORE modifica attributo ' || p_stringa || '=' || p_valore|| ' per dn:' || p_dn|| ' Errore: ' || v_errore);
           end if;
      end if;
   END;
   FUNCTION setpassword (p_nominativo varchar2, p_valore varchar2)
      RETURN NUMBER
   AS
      d_dn   VARCHAR2 (32000);
   BEGIN
      d_dn   := get_name ('CN=' || p_nominativo);
      IF d_dn IS NOT NULL
      THEN
         set_attributo (d_dn
                      , 'unicodePwd'
                      , p_valore
         ,0);
      END IF;
      RETURN 0;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN -1;
   END;
   PROCEDURE invio_mail (p_nome varchar2
                       , p_subject varchar2
                       , p_text_msg varchar2
                       , p_id_allegato number default null
                       , p_nome_allegato varchar2 default null
   )
   IS
      d_notifica_tag   VARCHAR2 (100);
      d_destinatario   VARCHAR2 (4000);
      d_err            INTEGER;
      d_subject        VARCHAR2 (2000);
      d_text_msg       VARCHAR2 (2000);
      dbencpwd         VARCHAR2 (2000);
   BEGIN
      d_notifica_tag   :=
         NVL (registro_utility.leggi_stringa ('PRODUCTS/LDAPCONFIG/NOTIFICA/CIM'
                                            , 'Tag'
                                            , 1
              )
            , 'mail'
         );
      IF p_nome IS NULL
      THEN
         raise_application_error (-20999, 'Impossibile inviare mail a ' || p_nome );
      ELSE
         d_err       := si4cim.initializemessage (d_notifica_tag);
         /*-----------------------------------------------------
               Inizializza il Mittente.
         -----------------------------------------------------*/
         si4cim.setsender (senderip => ''
                               , sendername => ''
                               , id => ''
                               , name => 'Messaggio automatico'
                               , company => ''
                               , address => ''
                               , code => ''
                               , city => ''
                               , province => ''
                               , state => ''
                               , email => d_sender_email
                               , phonenumber => ''
                               -- chi riceve la mail se la vede autoinviata
                               , faxnumber => ''
         );
         /*-----------------------------------------------------
                      Inizializza il Destinatario.
         -----------------------------------------------------*/
         si4cim.addrecipient (id => ''
                                  , name => p_nome
                                  , company => ''
                                  , address => ''
                                  , code => ''
                                  , city => ''
                                  , province => ''
                                  , state => ''
                                  , email => p_nome
                                  , phonenumber => ''
                                  , faxnumber => ''
         );
         /*-----------------------------------------------------
                  Predispone l'oggetto del messaggio.
         -----------------------------------------------------*/
         si4cim.setsubject (p_subject);
         /*-----------------------------------------------------
                  Predispone il testo del messaggio.
         -----------------------------------------------------*/
         si4cim.settext (p_text_msg);
       /*-----------------------------------------------------
            Predispone l'allegato al messaggio.
        -----------------------------------------------------*/
         if p_id_allegato is not null then
            SELECT PASSWORD_ORACLE
               INTO dbencpwd
              FROM ISTANZE
            WHERE ISTANZA = 'AD4';
               ad4_si4cim.addAttachment('oracle', 'oracle.jdbc.driver.OracleDriver', 'jdbc:default:connection', 'AD4', dbEncPwd,
                             'ALLINEAMENTO_SO4_LDAP', 'ALLEGATO', 'ID_ALLEGATO='||p_id_allegato, 'CLOB',p_nome_allegato);
          end if;
         /*-----------------------------------------------------
                         Invia il messaggio.
         -----------------------------------------------------*/
         d_err       := si4cim.send;
      END IF;
   END invio_mail;
   procedure download_schema( p_radice varchar2, p_filtro varchar2, p_livello number default 0)
   is
   begin
   finmatica_ldap.download_schema (ldap_url
                                  , ldap_user
                                  , ldap_password
                                  , p_livello
                                  , LTRIM (p_radice, ',')
                                  , p_filtro
                                  );
   end;
   PROCEDURE ricarica_struttura_ldap
   IS
   BEGIN
      -- caricamento tavole per situazione LDAP dopo spostamento OU.
      DELETE ldap_struttura;
      DELETE ldap_attributi;
      -- devo spezzare altrimenti rischio LIMIT EXCEEDED
      -- caratteri prima dei numeri
      -- Attenzione non fa differenza fra maiuscole e minuscole
      -- quindi non devo scaricare due volte le lettere
      for radice in (select ldap_rootou inizio, 'objectclass=OrganizationalUnit' obj_cl
                       from dual
                     UNION
                     select ldap_rootou inizio, 'objectclass=Group'
                       from dual
                     UNION
                     select ldap_root_users, --'&(objectClass=user)(objectCategory=Person)'
                                             '(&(&(objectClass=user)(objectCategory=Person))(!(userAccountControl:1.2.840.113556.1.4.803:=2)))'
                       from dual)
      -- se puntano alla stessa radice scarico solo una volta.
      LOOP
         download_schema (radice.inizio,'&((&('||radice.obj_cl||')(name<=0*)) (!(&('||radice.obj_cl||')(name=0*))))',1);
         -- scarico i numeri
         for i in 0..9 loop
         download_schema (radice.inizio,'(&('||radice.obj_cl||')(name='|| i||'*))',1);
         end loop;
         commit;
         download_schema (radice.inizio,'&(&((&('||radice.obj_cl||')(name>=9*))(!(&('||radice.obj_cl||')(name=a*)))(&(objectclass='||radice.obj_cl||')(name<=A*)))',1);
         for i in 65.. 90 loop --'A to 'Z
         download_schema (radice.inizio,'(&('||radice.obj_cl||')(name='|| chr(i)||'*))',1);
         end loop;
         commit;
         download_schema (radice.inizio,'&((&('||radice.obj_cl||')(name>=Z*))(&((&('||radice.obj_cl||')(name<=a*))(!(&(objectclass='||radice.obj_cl||')(name=a*)))))',1);
         commit;
         download_schema (radice.inizio,'&((&('||radice.obj_cl||')(name>=z*))(!(&('||radice.obj_cl||')(name=z*))))',1);
         COMMIT;
      END LOOP;
   END;
   procedure DO_LDAP_MAPPING
   IS
      d_err_id      INTEGER := 0;
      d_trc         varchar2(1000);
      d_dn          VARCHAR2 (4000);
      d_loop_sum    integer := 0;
   BEGIN
      -- mapping di quanto presente in ad4 in LDAP
      -- analisi della tavola ldap_struttura per vedere se UNITA ORGANIZZATIVE in ad4 ha
      -- cambiato posizione
      DECLARE
         v_dn_ldap   ldap_struttura.dn%TYPE;
         v_dn_ad4    ad4_ldap_struttura.dn%TYPE;
         d_loop      INTEGER := 0;
         cursor c_lstru is
           SELECT dn, categoria, LENGTH (dn) lun
             FROM ldap_struttura l
            WHERE categoria in( 'ORGANIZATIONALUNIT','GROUP')
            and exists (SELECT 1
                         FROM ldap_struttura ldst, ad4_ldap_struttura adld
                        WHERE substr( afc.get_substr( ldst.dn,',','P') , instr(afc.get_substr( ldst.dn,',','P'),ldap_delimitatore,1,2)+2)
                              = substr( afc.get_substr( adld.dn,',','P') , instr(afc.get_substr( adld.dn,',','P'),ldap_delimitatore,1,2)+2)
                              and ldst.dn like 'OU=%'
                              and adld.dn like 'OU=%'
                              and ldst.categoria = 'ORGANIZATIONALUNIT'
                              and adld.tipo = 'ORGANIZATIONALUNIT'
                              and AFC.COUNTOCCURRENCEOF(afc.get_substr( ldst.dn,',','P'),ldap_delimitatore ) = 2
                              and AFC.COUNTOCCURRENCEOF(afc.get_substr( adld.dn,',','P'),ldap_delimitatore ) = 2
                              AND ldst.dn = l.dn
                              and l.dn like 'OU=%')
             MINUS
             SELECT dn, tipo, LENGTH (dn)
             FROM ad4_ldap_struttura
             WHERE tipo in( 'ORGANIZATIONALUNIT','GROUP')
             ORDER BY 3,1,2 ;
         v_lstru c_lstru%ROWTYPE;
         cursor c_aoo is
           SELECT dn, categoria, LENGTH (dn) lun
             FROM ldap_struttura
            WHERE categoria =( 'ORGANIZATIONALUNIT')
                 AND AFC.COUNTOCCURRENCEOF(afc.get_substr( dn,',','P'),ldap_delimitatore ) = 1
                 AND EXISTS ( SELECT 1
                                         FROM ldap_struttura ldst, ad4_ldap_struttura adld
                                       WHERE afc.get_substr( afc.get_substr( ldst.dn,',','P') ,ldap_delimitatore,'U','F')
                                                   = afc.get_substr( afc.get_substr( adld.dn,',','P') ,ldap_delimitatore,'U','F')
                                            and ldst.dn like 'OU=%'
                                            and adld.dn like 'OU=%'
                                            and AFC.COUNTOCCURRENCEOF(afc.get_substr( ldst.dn,',','P'),ldap_delimitatore ) = 1
                                            and AFC.COUNTOCCURRENCEOF(afc.get_substr( adld.dn,',','P'),ldap_delimitatore ) = 1
                                            AND ldst.dn = ldap_struttura.dn
                                            and  ldap_struttura.dn like 'OU=%')
             MINUS
             SELECT dn, tipo, LENGTH (dn)
             FROM ad4_ldap_struttura
             WHERE tipo =( 'ORGANIZATIONALUNIT')
                  AND  AFC.COUNTOCCURRENCEOF(afc.get_substr( dn,',','P'),ldap_delimitatore ) = 1
             ORDER BY 3,1,2 ;
             v_aoo c_aoo%ROWTYPE;
            BEGIN
             scrivi_segnalazione('inizio aoo' || to_char(sysdate ,'hh24:mi:ss'));
             v_intestazione_messaggi := chr(10) ||'SISTEMAZIONE AOO' || chr(10)||chr(10) ;
            -- controllo se da rinominare delle aoo
            loop
               BEGIN
                  open c_aoo;
                  fetch c_aoo into v_aoo;
                  exit when  c_aoo%NOTFOUND ;-- tutto quello che e in ldap ma non uguale in AD4
                     d_trc := 'AOO da sistemare in ldap';
                     BEGIN
                        -- controllo se cambiata descrizione  ossia confronto
                        --il codice se il resto e cambiato faccio la rename.
                        SELECT DISTINCT ldst.dn, adld.dn
                           INTO v_dn_ldap, v_dn_ad4
                         FROM ldap_struttura ldst, ad4_ldap_struttura adld
                       WHERE afc.get_substr( afc.get_substr( ldst.dn,',','P') ,ldap_delimitatore,'U','F')
                                   = afc.get_substr( afc.get_substr( adld.dn,',','P') ,ldap_delimitatore,'U','F')
                            and ldst.dn like 'OU=%'
                            and adld.dn like 'OU=%'
                            and AFC.COUNTOCCURRENCEOF(afc.get_substr( ldst.dn,',','P'),ldap_delimitatore ) = 1
                            and AFC.COUNTOCCURRENCEOF(afc.get_substr( adld.dn,',','P'),ldap_delimitatore ) = 1
                            AND ldst.dn = v_aoo.dn
                            and  v_aoo.dn like 'OU=%'
                        ;
                        -- spostamento
                        d_trc :='RINOMINA '||  v_dn_ldap ||' A: ' || v_dn_ad4;
                        integritypackage.LOG(d_trc);
                        scrivi_allegato (d_trc);
                        finmatica_ldap.rename (ldap_url
                                             , ldap_user
                                             , ldap_password
                                             , v_dn_ldap
                                             , v_dn_ad4
                        );
                         d_loop   := d_loop + 1;
                     EXCEPTION
                        WHEN NO_DATA_FOUND
                        THEN
                           NULL;
                     -- non cancello, cancello alla fine
                     --                  -- cancellazione voce di ldap
                     --                  finmatica_ldap.unbind (ldap_url
                     --                                       , ldap_user
                     --                                       , ldap_password
                     --                                       , v_lstru.dn
                     --                                        );
                     END;
                  close c_aoo;
                   IF d_loop > 0
                   THEN
                      ricarica_struttura_ldap;-- ricarico ogni volta altrimenti errore
                      d_loop := 0;
                      integritypackage.LOG('ricarica_struttura_ldap dopo AOO');
                   END IF;
                  exception
                  when others then
                     close c_aoo;
                     raise;
                  END;
              end loop;
               scrivi_segnalazione('inizio ou da sistemare' || to_char(sysdate ,'hh24:mi:ss'));
               v_intestazione_messaggi := chr(10) ||'SISTEMAZIONE UNITA ORGANIZZATIVE' || chr(10) || chr(10) ;
            loop
               BEGIN
                  open c_lstru;
                  fetch c_lstru into v_lstru;
                  exit when c_lstru%NOTFOUND;-- tutto quello che e in ldap ma non uguale in AD4
                     d_trc := 'OU da sistemare in ldap';
                     BEGIN
                        -- controllo se cambiata descrizione o codice ossia confronto
                        -- utente_ad4 se il resto e cambiato faccio la rename.
                        -- controllo se presente in struttura di AD4 in diversa posizione
                        SELECT DISTINCT ldst.dn, adld.dn
                        INTO v_dn_ldap, v_dn_ad4
                         FROM ldap_struttura ldst, ad4_ldap_struttura adld
                        WHERE substr( afc.get_substr( ldst.dn,',','P') , instr(afc.get_substr( ldst.dn,',','P'),ldap_delimitatore,1,2)+2)
                              = substr( afc.get_substr( adld.dn,',','P') , instr(afc.get_substr( adld.dn,',','P'),ldap_delimitatore,1,2)+2)
                              and ldst.dn like 'OU=%'
                              and adld.dn like 'OU=%'
                              and ldst.categoria = 'ORGANIZATIONALUNIT'
                              and adld.tipo = 'ORGANIZATIONALUNIT'
                              and AFC.COUNTOCCURRENCEOF(afc.get_substr( ldst.dn,',','P'),ldap_delimitatore ) = 2
                              and AFC.COUNTOCCURRENCEOF(afc.get_substr( adld.dn,',','P'),ldap_delimitatore ) = 2
                              AND ldst.dn = v_lstru.dn
                              and v_lstru.dn like 'OU=%'
                        ;
                        -- spostamento
                        d_trc :='RINOMINA '||  v_dn_ldap ||' A: ' || v_dn_ad4;
                         integritypackage.LOG(d_trc);
                         scrivi_allegato(d_trc);
                        finmatica_ldap.rename (ldap_url
                                             , ldap_user
                                             , ldap_password
                                             , v_dn_ldap
                                             , v_dn_ad4
                        );
                         d_loop   := d_loop + 1;
                     EXCEPTION
                        WHEN NO_DATA_FOUND
                        THEN
                           NULL;
                     -- non cancello, cancello alla fine
                     --                  -- cancellazione voce di ldap
                     --                  finmatica_ldap.unbind (ldap_url
                     --                                       , ldap_user
                     --                                       , ldap_password
                     --                                       , v_lstru.dn
                     --                                        );
                     END;
                     -- devo rinominare anche il cn....
                     d_trc := 'CN da sistemare in ldap';
                     BEGIN
                        -- controllo se cambiata descrizione o codice ossia confronto
                        -- utente_ad4 se il resto e cambiato faccio la rename.
                        -- controllo se presente in struttura di AD4 in diversa posizione
                        SELECT DISTINCT upper(replace (afc.get_substr(ldst.dn,',','P'),'OU','CN') ||','|| (substr(adld.dn,instr(adld.dn,',')+1))) --ldst.dn
                                      , adld.dn
                        INTO v_dn_ldap, v_dn_ad4
                         FROM ldap_struttura ldst, ad4_ldap_struttura adld
                        WHERE substr( afc.get_substr( ldst.dn,',','P') , instr(afc.get_substr( ldst.dn,',','P'),ldap_delimitatore,1,2)+2)
                              = substr( afc.get_substr( adld.dn,',','P') , instr(afc.get_substr( adld.dn,',','P'),ldap_delimitatore,1,2)+2)
                              and ldst.dn like 'CN=%'
                              and adld.dn like 'CN=%'
                              and ldst.categoria = 'GROUP'
                              and adld.tipo = 'GROUP'
                              and AFC.COUNTOCCURRENCEOF(afc.get_substr( ldst.dn,',','P'),ldap_delimitatore ) = 2
                              and AFC.COUNTOCCURRENCEOF(afc.get_substr( adld.dn,',','P'),ldap_delimitatore ) = 2
                              AND ldst.dn = replace (afc.get_substr(v_lstru.dn,',','P'),'OU','CN') ||','|| (v_lstru.dn)
                              and v_lstru.dn like 'OU=%'
                        ;
                        -- spostamento
                        d_trc := 'RINOMINA ' || v_dn_ldap || ' A: ' || v_dn_ad4;
                         integritypackage.LOG(d_trc);
                         scrivi_allegato(d_trc);
                         scrivi_segnalazione(d_trc,2);
                        finmatica_ldap.rename (ldap_url
                                             , ldap_user
                                             , ldap_password
                                             , v_dn_ldap
                                             , v_dn_ad4
                        );
                        scrivi_segnalazione('cambio ' ||ldap_camponominativo || ' di ' || v_dn_ad4 || '='||        substr(v_dn_ad4,4 -- salto CN=
                                                       ,instr(v_dn_ad4,',OU=')-4),2);
                        scrivi_allegato('CAMBIO ' ||ldap_camponominativo || ' DI: ' || v_dn_ad4 || '='||        substr(v_dn_ad4,4 -- salto CN=
                                                       ,instr(v_dn_ad4,',OU=')-4));
                        set_attributo (v_dn_ad4
                                  , ldap_camponominativo
                                  , substr(v_dn_ad4,4 -- salto CN=
                                                       ,instr(v_dn_ad4,',OU=')-4)
                                  , 0 );
                        d_loop   := d_loop + 1;
                     EXCEPTION
                        WHEN NO_DATA_FOUND
                        THEN
                           NULL;
                     -- non cancello, cancello alla fine
                     --                  -- cancellazione voce di ldap
                     --                  finmatica_ldap.unbind (ldap_url
                     --                                       , ldap_user
                     --                                       , ldap_password
                     --                                       , v_lstru.dn
                     --                                        );
                     END;
                  close c_lstru;
                   IF d_loop > 0
                   THEN
                      ricarica_struttura_ldap; -- ricarico ogni volta altrimenti errore
                      d_loop := 0;
                      scrivi_segnalazione('ricarica_struttura_ldap dopo rename ORGANIZATIONALUNIT e GROUP');
                   END IF;
                  exception
                  when others then
                     close c_lstru;
                     raise;
                  END;
              end loop;
               d_loop_sum := d_loop_sum + d_loop;
               IF d_loop > 0
               THEN
                  ricarica_struttura_ldap;
                  d_loop := 0;
                  integritypackage.LOG('ricarica_struttura_ldap dopo rename ORGANIZATIONALUNIT e GROUP');
               END IF;
               -- analisi della tavola ldap_struttura per vedere se qualcosa in ad4 ha
               -- cambiato posizione
               scrivi_segnalazione ('quanto in ldap ma non uguale in AD4 3');
               declare
               i number;
               begin
               select count(*)
                  into i
                  FROM ldap_struttura
                   WHERE categoria = 'ORGANIZATIONALUNIT';
               end;
               scrivi_segnalazione('tutto quanto in ad4 ma non in ldap' || to_char(sysdate,'hh24:mi:ss'));
               v_intestazione_messaggi := chr(10)|| 'INSERIMENTO ELEMENTI MANCANTI' || chr(10) || chr(10);
            FOR v_lstru
            IN -- tutto quello che e in AD4 ma non esiste in Ldap
             (SELECT  dn
                    , tipo
                    , UPPER (descrizione) descrizione
                    , DECODE (tipo, 'PERSON', 3, 'GROUP', 2, 1)
                    , figlio
                    , LENGTH (dn) lunghezza_dn
               FROM ad4_ldap_struttura alst
               WHERE NOT EXISTS
                       (SELECT 'x'
                          FROM ldap_struttura ldst
                         WHERE ldst.dn = alst.dn
                             AND  ldst.categoria = alst.tipo)
               ORDER BY 4, 6 )
            LOOP
               d_loop := d_loop +1;
               DECLARE
                  d_valore      ldap_attribute;
                  d_useracccontrol integer;
               BEGIN
                  -- devo inserire in ldap
                  IF v_lstru.tipo = 'GROUP'
                  THEN
                     d_trc :='Inserimento  GROUP ' || v_lstru.dn;
                     integritypackage.LOG (d_trc);
                     scrivi_allegato(d_trc);
                     finmatica_ldap.bind_group (ldap_url
                                              , ldap_user
                                              , ldap_password
                                              , sistema_case(v_lstru.dn)
                                              , sistema_case(substr(v_lstru.dn,4 -- salto OU=
                                                       ,instr(v_lstru.dn,',OU=')-4))
                     );
                     BEGIN
                     if upper(ldap_camponominativo) is not null
                     and upper(ldap_camponominativo) != 'CN' then
                      d_trc :=d_trc || ' attributo ' || ldap_camponominativo ||'='|| substr(v_lstru.dn,4 -- salto OU=
                                                       ,instr(v_lstru.dn,',OU=')-4);
                     set_attributo (v_lstru.dn
                                  , ldap_camponominativo
                                  , substr(v_lstru.dn,4 -- salto OU=
                                                       ,instr(v_lstru.dn,',OU=')-4)
                                  , 0 );
                     end if;
                     exception
                     WHEN OTHERS
                           THEN
                             IF SQLCODE = -29532 THEN
                              DECLARE
                                 d_err   VARCHAR2 (32000);
                              BEGIN
                                 d_err            :=
                                    SUBSTR (SQLERRM
                                          , 1
                                          , 1940
                                    );
                                 ROLLBACK;
                                 SELECT keel_sq.NEXTVAL
                                 INTO d_err_id
                                 FROM DUAL;
                                 key_error_log_tpk.ins (p_error_id => d_err_id
                                                      , p_error_session => USERENV('sessionid')
                                                      , p_error_date => SYSDATE
                                                      , p_error_text => 'Errore in sistemazione LDAP gruppo: '
                                                                       || ' Impossibile impostare '||ldap_camponominativo
                                                                       || v_lstru.dn
                                                                       || ':'
                                                                       || d_err
                                                      , p_error_user => USER
                                                      , p_error_usertext => NULL
                                                      , p_error_type => 'E'
                                 );
                                 scrivi_allegato( '>>ERRORE in sistemazione LDAP gruppo: '
                                                                       || ' Impossibile impostare '||ldap_camponominativo
                                                                       || v_lstru.dn
                                                                       || ':'
                                                                       || d_err);
                              END;
                              end if;
                      END;
                  ELSIF v_lstru.tipo = 'ORGANIZATIONALUNIT'
                  THEN
                     d_trc :='Inserimento ORGANIZATIONALUNIT ' || v_lstru.dn;
                      integritypackage.LOG(d_trc);
                      scrivi_allegato(d_trc);
                     finmatica_ldap.bind_ou (ldap_url
                                           , ldap_user
                                           , ldap_password
                                           , sistema_case(v_lstru.dn)
                                           , sistema_case(substr(v_lstru.dn,4 -- salto OU=
                                                       ,instr(v_lstru.dn,',OU=')-4))
                     );
                  ELSIF v_lstru.tipo = 'PERSON'
                  THEN
                     -- cerco prima se gia presente nella struttura
                     -- anche disabilitato
                     v_trovato_dn   :=
                        finmatica_ldap.get_distinguished_name (ldap_url
                                                             , ldap_user
                                                             , ldap_password
                                                             , LTRIM (ldap_root_users
                                                                    , ','
                                                               )
                                                             , ldap_camponominativo||'='
                                                               || v_lstru.descrizione
                        );
--                        v_trovato_dn   := -- togliere per prova visto che non funziona samaccountname
--                        finmatica_ldap.get_distinguished_name (ldap_url
--                                                             , ldap_user
--                                                             , ldap_password
--                                                             , LTRIM (ldap_root_users
--                                                                    , ','
--                                                               )
--                                                             , 'name='
--                                                               || v_lstru.descrizione
--                        );
                     IF v_trovato_dn IS NULL
                     THEN
                        -- creo utente
                        d_trc := 'Inserimento PERSON ' || v_lstru.dn;
                         integritypackage.LOG(d_trc);
                         scrivi_allegato(d_trc);
                        finmatica_ldap.bind (ldap_url
                                           , ldap_user
                                           , ldap_password
                                           , sistema_case(v_lstru.dn)
                        );
                        begin
                        -- sistemazione tutti attributi solo se nuovo utente
                        if upper(ldap_camponominativo) is not null
                        and upper(ldap_camponominativo) != 'CN'  then
                        d_trc := d_trc || ldap_camponominativo||'='
                                                               || LOWER (v_lstru.descrizione);
                        --scrivi_allegato ('ATTRIBUTO ' || ldap_camponominativo ||' = ' || lower(v_lstru.descrizione) || ' per:'|| v_lstru.dn);
                        set_attributo (v_lstru.dn
                                     , ldap_camponominativo
                                     , LOWER (v_lstru.descrizione)
                        );
                        end if;
                        exception
                        WHEN OTHERS
                           THEN
                             IF SQLCODE = -29532 THEN
                              DECLARE
                                 d_err   VARCHAR2 (32000);
                              BEGIN
                                 d_err            :=
                                    SUBSTR (SQLERRM
                                          , 1
                                          , 1940
                                    );
                                 ROLLBACK;
                                 SELECT keel_sq.NEXTVAL
                                 INTO d_err_id
                                 FROM DUAL;
                                 key_error_log_tpk.ins (p_error_id => d_err_id
                                                      , p_error_session => USERENV('sessionid')
                                                      , p_error_date => SYSDATE
                                                      , p_error_text => 'Errore in sistemazione LDAP utente: '
                                                                       || ' Impossibile impostare '||ldap_camponominativo||', gia esistente'
                                                                       || v_lstru.dn
                                                                       || ':'
                                                                       || d_err
                                                      , p_error_user => USER
                                                      , p_error_usertext => NULL
                                                      , p_error_type => 'E'
                                 );
                                 scrivi_allegato('>>Errore in sistemazione LDAP utente: '
                                                                       || ' Impossibile impostare '||ldap_camponominativo||', gia esistente'
                                                                       || v_lstru.dn
                                                                       || ':'
                                                                       || d_err);
                              END;
                              end if;
                        END;
--                        set_attributo (v_lstru.dn
--                                     , 'name'
--                                     , LOWER (v_lstru.descrizione)
--                        );
                        if upper(ldap_camponominativo) = 'SAMACCOUNTNAME' then
                        -- solo per active directory
                           set_attributo (v_lstru.dn
                                        , 'userPrincipalName'
                                        , LOWER (v_lstru.descrizione)
                                          || ldap_suffissonominativo
                           );
                        end if;
                        FOR v_attr
                        IN (SELECT stringa, valore
                            FROM registro
                            WHERE chiave = 'PRODUCTS/LDAPCONFIG/ATTRIBUTI/CREAZIONE'
                                  AND LOWER (stringa) != 'defaultpassword'
                                  AND LOWER (stringa) != '(predefinito)'
                                  AND valore is not null)
                        LOOP
                         d_valore := calcola_attributo(v_attr.valore, v_lstru.figlio,v_lstru.descrizione);
                           -- calcola attributo
                          if d_valore is not null then
                           set_attributo (v_lstru.dn
                                        , v_attr.stringa
                                        , d_valore
                           );
                           end if;
                        END LOOP;
                        if ldap_tipo_autenticazione = 'SSL' then
                        -- solo con ssl posso fare alcune attivita
                        DECLARE
                           ldap_new_pwd   ldap_attribute;
                           d_defpwd       VARCHAR2 (100);
                           d_errore       VARCHAR2 (100);
                        BEGIN
                           -- tiene conto delle policy su ad4 e sistema utente
                           -- conservando la pwd in ad4 se previsto.
                           BEGIN
                              SELECT calcola_attributo(valore, v_lstru.figlio,v_lstru.descrizione) valore
                              INTO d_defpwd
                              FROM registro
                              WHERE chiave = 'PRODUCTS/LDAPCONFIG/ATTRIBUTI/CREAZIONE'
                                    AND LOWER (stringa) = 'defaultpassword';
                           EXCEPTION
                              WHEN OTHERS
                              THEN
                                 d_defpwd   := NULL;
                           END;
                           ldap_new_pwd   :=
                              NVL (d_defpwd, utente.genera_password(v_lstru.figlio));
                           integritypackage.LOG(v_lstru.figlio || '   nuova password '
                                                || ldap_new_pwd);
--                           set_attributo (v_lstru.dn
--                                        , 'userAccountControl'
--                                        , '512'           -- utente abilitato. (era 512)
--                           );
                            d_trc := 'Get Attributo userAccountControl';
                            IF finmatica_ldap.is_accountenabled (ldap_url
                                                              , ldap_user
                                                              , ldap_password
                                                              , v_lstru.dn
                              ) = 0
                           THEN
                            d_useracccontrol   :=
                                     finmatica_ldap.get_attribute (ldap_url
                                                                 , ldap_user
                                                                 , ldap_password
                                                                 , v_lstru.dn
                                                                 , 'userAccountControl'
                                     );
                                   --abilita l'account (attributo userAccountControl - 2)
                                   d_trc := 'Sistemazione userAccountControl da '|| d_useracccontrol;
                                   set_attributo(v_lstru.dn
                                                                 , 'userAccountControl'
                                                                 , nvl(d_useracccontrol,0)
                                                                   - 2
                                                                 , 0 );
--                                  d_errore := finmatica_ldap.modify_attribute (ldap_url
--                                                                 , ldap_user
--                                                                 , ldap_password
--                                                                 , v_lstru.dn
--                                                                 , 'userAccountControl'
--                                                                 , nvl(d_useracccontrol,0)
--                                                                   - 2
--                                  );
                           end if;
                           d_trc := 'Set Attributo unicodePwd';
                           set_attributo (v_lstru.dn
                                        , 'unicodePwd'
                                        , ldap_new_pwd
                                        , 0 );
                           d_trc := 'Set Attributo pwdlastset';
                           set_attributo (v_lstru.dn
                                        , 'pwdlastset'
                                        , '0'
                                        , 0
                           -- password da cambiare al primo login.
                           -- il login va fatto da sistema operativo,
                           -- l'applicativo non fa entrare
                           );
                           COMMIT;
                           IF d_registration_email IS NOT NULL -- previsto invio via mail
                           THEN
                              DECLARE
                              d_nome soggetti.nome%TYPE;
                              BEGIN
                                 d_nome:=INITCAP (soggetto.get_nome (utente.get_soggetto (v_lstru.figlio, 'Y'),'Y'));
                                 integritypackage.LOG('   invio_mail per crezione account di '
                                                      || d_nome);
                                 invio_mail (d_nome
                                           , 'Attivazione utente '
                                           ,    'Attivazione utente '
                                             || d_nome
                                             || ' avvenuta con successo.'
                                             || CHR (10)
                                             || ' Login:    '
                                             || v_lstru.descrizione
                                             || CHR (10)
                                             || ' Password: '
                                             || ldap_new_pwd
                                             || CHR (10)
                                             || 'ATTENZIONE la password dovra essere cambiata al primo accesso'
                                            , 'RegistrationEmailAddress'
                                 );
                                 scrivi_allegato('Inviata mail di attivazione utente per :' || v_lstru.descrizione);
                              EXCEPTION
                                 WHEN OTHERS
                                 THEN
                                    DECLARE
                                       d_err   VARCHAR2 (32000);
                                    BEGIN
                                       d_err            :=
                                          SUBSTR (SQLERRM
                                                , 1
                                                , 1940
                                          );
                                       ROLLBACK;
                                       SELECT keel_sq.NEXTVAL
                                       INTO d_err_id
                                       FROM DUAL;
                                       key_error_log_tpk.ins (p_error_id => d_err_id
                                                            , p_error_session => USERENV('sessionid')
                                                            , p_error_date => SYSDATE
                                                            , p_error_text => 'Errore in sistemazione attributi LDAP utente: '
                                                                             || v_lstru.dn
                                                                             || ' - INVIO MAIL:'
                                                                             || d_err
                                                            , p_error_user => USER
                                                            , p_error_usertext => NULL
                                                            , p_error_type => 'E'
                                       );
                                       scrivi_allegato( '>>Errore in sistemazione attributi LDAP utente: '
                                                                             || v_lstru.dn
                                                                             || ' - INVIO MAIL:'
                                                                             || d_err);
                                    END;
                              END;
                           END IF;
                        EXCEPTION
                           WHEN OTHERS
                           THEN
                              DECLARE
                                 d_err   VARCHAR2 (32000);
                              BEGIN
                                 d_err            :=
                                    SUBSTR (SQLERRM
                                          , 1
                                          , 1940
                                    );
                                 ROLLBACK;
                                 SELECT keel_sq.NEXTVAL
                                 INTO d_err_id
                                 FROM DUAL;
                                 key_error_log_tpk.ins (p_error_id => d_err_id
                                                      , p_error_session => USERENV('sessionid')
                                                      , p_error_date => SYSDATE
                                                      , p_error_text => 'Errore  '|| d_trc|| ' utente: '
                                                                       || v_lstru.dn
                                                                       || ':'
                                                                       || d_err
                                                      , p_error_user => USER
                                                      , p_error_usertext => NULL
                                                      , p_error_type => 'E'
                                 );
                                 scrivi_allegato( '>>Errore ' || d_trc || ' utente: '
                                                                       || v_lstru.dn
                                                                       || ':'
                                                                       || d_err);
                              END;
                        END;
                        END IF; -- fine solo se ssl
                     ELSE  -- trovato dn della persona
                        DECLARE
                                d_useracccontrol   INTEGER;
                                d_errore varchar2(100);
                             BEGIN
                             if upper(v_trovato_dn) != upper(v_lstru.dn) then
                                 -- spostamento
                                 d_trc := 'Rinomina PERSONA '
                                                      || v_trovato_dn
                                                      || ' A: '
                                                      || v_lstru.dn;
                                 integritypackage.LOG(d_trc);
                                 scrivi_allegato(d_trc);
                                 finmatica_ldap.rename (ldap_url
                                              , ldap_user
                                              , ldap_password
                                              , v_trovato_dn
                                              , v_lstru.dn);
                             end if; -- sono veramente diversi
                            v_trovato_dn := v_lstru.dn;
                           -- messo visto che l'ho spostato
                            -- controllare se in stato disabilitato
                           -- ed eventualmente riabilitare!!!!!!!!!!!!!!!!
                           IF finmatica_ldap.is_accountenabled (ldap_url
                                                              , ldap_user
                                                              , ldap_password
                                                              , v_trovato_dn
                              ) = 0
                           THEN
                           if  ldap_tipo_autenticazione = 'SSL' then -- attivita si puo fare
                                integritypackage.LOG(   ' riabilita '
                                                || v_trovato_dn
                                                || ' - '
                                                || v_lstru.dn);
                                -- togliere da membro gruppo disabilitati
                                scrivi_allegato ( 'RIABILITARE: '
                                                || v_trovato_dn
                                                || ' - '
                                                || v_lstru.dn);
                                  -- legge l'attributo userAccountControl
                                  d_useracccontrol   :=
                                     finmatica_ldap.get_attribute (ldap_url
                                                                 , ldap_user
                                                                 , ldap_password
                                                                 , v_trovato_dn
                                                                 , 'userAccountControl'
                                     );
                                   --abilita l'account (attributo userAccountControl - 2)
                                   set_attributo(v_trovato_dn
                                                                 , 'userAccountControl'
                                                                 , nvl(d_useracccontrol,0)
                                                                   - 2
                                                                 , 0 );
--                                  d_errore := finmatica_ldap.modify_attribute (ldap_url
--                                                                 , ldap_user
--                                                                 , ldap_password
--                                                                 , v_trovato_dn
--                                                                 , 'userAccountControl'
--                                                                 , nvl(d_useracccontrol,0)
--                                                                   - 2
--                                  );
                                  if d_errore != 'OK' then
                                  scrivi_allegato ('>> ERRORE in riabilitazione per dn:' || v_trovato_dn|| ' Errore: ' || d_errore);
                                  end if;
                              ELSE -- attivita non si puo fare
                                  scrivi_allegato ('>> ERRORE in riabilitazione per dn:' || v_trovato_dn|| ' la connessione deve essere SSL');
                              END IF;
                     -- togliere membro del gruppo disabilitati
                     d_trc := 'Tolto da Gruppo disabilitati ' || v_trovato_dn;
                     scrivi_allegato(d_trc);
                     if finmatica_ldap.is_Member_Of ( ldap_url
                                                    , ldap_user
                                                    , ldap_password
                                                    , ldap_root_users_disabled
                                                    , v_trovato_dn)
                     then
                        d_errore := finmatica_ldap.remove_attribute (ldap_url
                                     , ldap_user
                                     , ldap_password
                                     , ldap_root_users_disabled
                                     , 'MEMBER'
                                     , v_trovato_dn
                                       );
                          if d_errore != 'OK' then
                          scrivi_allegato ('>> ERRORE eliminazione dal gruppo disabilitati per dn:' || v_trovato_dn|| ' Errore: ' || d_errore);
                          end if;
                     end if;
                           END IF;
                        END;
                     END IF;
                  END IF;
               END;
            END LOOP;
            d_loop_sum := d_loop_sum + d_loop;
            IF d_loop > 0
               THEN
                  ricarica_struttura_ldap;
                  d_loop:= 0;
                  integritypackage.LOG('ricarica_struttura_ldap dopo quanto in AD4 ma non in Ldap');
            END IF;
            scrivi_segnalazione ('quanto in ldap ma non uguale in AD41');
            declare
               i number;
               begin
               select count(*)
                  into i
                  FROM ldap_struttura
                   WHERE categoria = 'ORGANIZATIONALUNIT';
               scrivi_segnalazione('OU :' || i);
               end;
               v_intestazione_messaggi := chr(10) ||'ELIMINAZIONE ELEMENTI' || chr(10) || chr(10);
               FOR v_lstru
               IN -- tutto quello che e in ldap ma non uguale in AD4
                  (SELECT dn
                        , categoria
                        , DECODE (categoria, 'PERSON', 3, 'GROUP', 2, 1)
                   FROM ldap_struttura
                   MINUS
                   SELECT dn
                        , tipo
                        , DECODE (tipo, 'PERSON', 3, 'GROUP', 2, 1)
                   FROM ad4_ldap_struttura
                   ORDER BY 3)
               LOOP
                  DECLARE
                     d_useracccontrol   INTEGER;
                     d_errore varchar2(5000);
                  BEGIN
                     d_loop   := d_loop + 1;
                     -- controllo se presente in struttura di AD4 in diversa posizione
                     SELECT ldst.dn dn_ldap, adld.dn dn_ad4
                     INTO v_dn_ldap, v_dn_ad4
                     FROM ldap_struttura ldst, ad4_ldap_struttura adld
                     WHERE      (SUBSTR (ldst.dn
                                            , 1
                                            , INSTR (ldst.dn
                                                   , ','
                                              )
                                              - 1
                                      )
                               ) =  (SUBSTR (adld.dn
                                                , 1
                                                , INSTR (adld.dn
                                                       , ','
                                                  )
                                                  - 1
                                          )
                                   )
                           AND  (ldst.dn) !=  (adld.dn)
                           AND  (ldst.dn) =  (v_lstru.dn);
                     -- spostamento
                     d_trc := 'RINOMINA '
                                          || v_dn_ldap
                                          || ' A: '
                                          || v_dn_ad4;
                     integritypackage.LOG(d_trc);
                     scrivi_allegato(d_trc);
                     finmatica_ldap.rename (ldap_url
                                          , ldap_user
                                          , ldap_password
                                          , v_dn_ldap
                                          , v_dn_ad4
                     );
                  EXCEPTION
                     WHEN NO_DATA_FOUND
                     THEN
                        -- cancellazione voce di ldap
                        IF v_lstru.categoria = 'PERSON'
                        THEN
                           d_trc:=    'DISABILITATA persona: '
                                                || v_lstru.dn
                                                || ' inserita nel gruppo: '
                                                || ldap_root_users_disabled;
                           integritypackage.LOG(d_trc);
                           scrivi_allegato(d_trc);
                           IF finmatica_ldap.is_accountenabled (ldap_url
                                                              , ldap_user
                                                              , ldap_password
                                                              , v_lstru.dn
                              ) != 0
                           THEN
                           if ldap_tipo_autenticazione = 'SSL' THEN --attivita si puo fare
                              -- legge l'attributo userAccountControl
                              d_useracccontrol   :=
                                 finmatica_ldap.get_attribute (ldap_url
                                                             , ldap_user
                                                             , ldap_password
                                                             , v_lstru.dn
--                                                             , SUBSTR (v_lstru.dn
--                                                                     , 1
--                                                                     , INSTR (v_lstru.dn
--                                                                            , ','
--                                                                       )
--                                                               )
--                                                               || ldap_root_users_disabled
                                                             , 'userAccountControl'
                                 );
                               --disabilita l'account (attributo userAccountControl + 2)
                               set_attributo(v_lstru.dn, 'userAccountControl'
                                                             , nvl(d_useracccontrol,0) + 2
                                                               ,0);
--                              d_errore := finmatica_ldap.modify_attribute (ldap_url
--                                                             , ldap_user
--                                                             , ldap_password
--                                                             , v_lstru.dn
----                                                             , SUBSTR (v_lstru.dn
----                                                                     , 1
----                                                                     , INSTR (v_lstru.dn
----                                                                            , ','
----                                                                       )
----                                                               )
----                                                               || ldap_root_users_disabled
--                                                             , 'userAccountControl'
--                                                             , nvl(d_useracccontrol,0)
--                                                               + 2
--                              );
                               if d_errore != 'OK' then
                                     scrivi_allegato ('>> ERRORE disabilitazione per dn:' || v_lstru.dn|| ' Errore: ' || d_errore);
                               end if;
                              ELSE -- attivita non si puo fare
                                     scrivi_allegato ('>> ERRORE disabilitazione per dn:' || v_lstru.dn|| ' la connessione deve essere SSL' );
                              END IF;
                           END IF;
                           -- mettere membro del gruppo disabilitati
                           d_trc := v_lstru.dn || ' inserimento gruppo disabilitati';
                           set_attributo(ldap_root_users_disabled
                                  , 'MEMBER'
                                  , v_lstru.dn
                                  , 0 );
--                           d_errore := finmatica_ldap.add_attribute (ldap_url
--                                  , ldap_user
--                                  , ldap_password
--                                  , ldap_root_users_disabled
--                                  , 'MEMBER'
--                                  , v_lstru.dn
--                                    );
--                            scrivi_allegato (d_trc);
--                             if d_errore != 'OK' then
--                                     scrivi_allegato ('>> ERRORE inserimento gruppo disabiliati per dn:' || v_lstru.dn|| ' Errore: ' || d_errore);
--                               end if;
                           ELSE -- non e una persona
                             integrityPackage.log ('  cancella '||v_lstru.dn);
                             d_trc :='Cancella ' || v_lstru.dn;
                             scrivi_allegato (d_trc);
                             finmatica_ldap.unbind (ldap_url
                                                  , ldap_user
                                                  , ldap_password
                                                  , v_lstru.dn
                                                   );
                        END IF;
                  END;
               END LOOP;
               d_loop_sum := d_loop_sum + d_loop;
               IF d_loop > 0
               THEN
                  ricarica_struttura_ldap;
                  d_loop := 0;
                  integritypackage.LOG('ricarica_struttura_ldap dopo cancellazione PERSON/GROUP');
               END IF;
            END;
            /*
scrivi_segnalazione ('cancellazione');
            FOR v_lstru
               IN -- tutto quello che e in ldap ma non uguale in AD4
                  (SELECT UPPER (dn) dn
                        , categoria
                        , DECODE (categoria,  'GROUP', 2, 3)
                   FROM ldap_struttura
                  where categoria != 'PERSON'
                   MINUS
                   SELECT UPPER (dn)
                        , tipo
                        , DECODE (tipo,  'GROUP', 2, 3)
                   FROM ad4_ldap_struttura
                  where tipo != 'PERSON'
                   ORDER BY 3)
               LOOP
                   BEGIN
                         --cancellazione voce di ldap
                                                integrityPackage.log ('  cancella '||v_lstru.dn);
                                                finmatica_ldap.unbind (ldap_url
                                                                     , ldap_user
                                                                     , ldap_password
                                                                     , v_lstru.dn
                                                                      );
                   END;
               END LOOP;
               -- fine cancellazione
               */ -- prova di cancellazione
     --       scrivi_segnalazione ('member 1');
 --           declare
--               i number;
--               begin
--               select count(*)
--                  into i
--                  FROM ldap_struttura
--                   WHERE categoria = 'ORGANIZATIONALUNIT';
--               end;
--            -- ricaricare la situazione degli utenti
--            -- NON HO MAI TOCCATO GLI UTENTI NON IMPORTA RICARICARE
--            IF d_loop_sum > 0
--               THEN
--                  ad4_viste_to_tabelle;
--                  integritypackage.LOG('ricarica tabelle ad4');
--               END IF;
           -- inserire in tabella gli attributi
           execute immediate 'truncate table ad4_ldap_attributi';
           INSERT INTO ad4_ldap_attributi (DN, NOME_ATTRIBUTO, VALORE_ATTRIBUTO)
           SELECT upper(DN), upper(NOME_ATTRIBUTO)
                , decode(upper(nome_attributo),'MEMBER',upper(VALORE_ATTRIBUTO), ltrim(rtrim(VALORE_ATTRIBUTO)))
                -- gli attributi member sono appartenenze e vanno bene tutti maiuscoli.
                -- gli altri attributi voglio poter scegliere se maiuscoli, minuscoli...
           FROM ad4_ldap_attributi_vista;
          COMMIT;
            -- controllo degli attributi
            -- attributo member da verificare sempre
            v_intestazione_messaggi := chr(10) ||'SISTEMAZIONE APPARTENENZE AI GRUPPI' || chr(10)||chr(10) ;
            FOR v_mem_ldap
            IN (SELECT  upper (valore_attributo) dn
                     , upper(nome_attributo) MEMBER
                     ,  (dn) gruppo
                FROM ldap_attributi ldat, ldap_struttura ldst
                WHERE ldat.id_dn = ldst.id_dn
                      AND upper (nome_attributo) = 'MEMBER'
                MINUS
                SELECT  (dn), nome_attributo,  (valore_attributo)
                FROM ad4_ldap_attributi)
            LOOP
            declare
            d_errore varchar2(5000);
            begin
--           scrivi_segnalazione(' rimuove '
--                                    || v_mem_ldap.dn
--                                    || ' da '
--                                    || v_mem_ldap.gruppo);
               -- tutto quello che e in ldap ma non piu in ad4 va tolto
               d_trc :='Rimuove  ' ||v_mem_ldap.dn
                                    || ' da '
                                    || v_mem_ldap.gruppo;
               integritypackage.LOG(  d_trc);
               scrivi_allegato(d_trc);
               d_errore := finmatica_ldap.remove_attribute (ldap_url
                                              , ldap_user
                                              , ldap_password
                                              , v_mem_ldap.gruppo
                                              , 'member'
                                              , v_mem_ldap.dn
               );
                if d_errore != 'OK' then
                                     scrivi_allegato ('>> ERRORE eliminazione dal GRUPPO ' || v_mem_ldap.gruppo || ' per dn:' || v_mem_ldap.dn|| ' Errore: ' || d_errore);
                 end if;
               end;
            END LOOP;
            scrivi_segnalazione ('attributi');
--            declare
--               i number;
--               begin
--               select count(*)
--                  into i
--                  FROM ldap_struttura
--                   WHERE categoria = 'ORGANIZATIONALUNIT';
--               scrivi_segnalazione('OU :' || i);
--               end;
            -- tolte UPPER!!!!!!!!!!!!!
            v_intestazione_messaggi := chr(10)|| 'SISTEMAZIONE ATTRIBUTI' || chr(10) ||chr(10);
            FOR v_attr_ldap
            IN (
            /*SELECT  (dn) dn
                     ,  (nome_attributo) attributo
                     , valore_attributo valore_attr--upper(valore_attributo) valore_attr
                FROM ad4_ldap_attributi
                MINUS
                SELECT  (dn), 'MEMBER', upper(valore_attributo)
                FROM ldap_attributi ldat, ldap_struttura ldst
                WHERE ldat.id_dn = ldst.id_dn
                      AND upper (nome_attributo) = 'MEMBEROF'
                MINUS
                SELECT  (dn)
                     , upper (nome_attributo)
                     , valore_attributo --upper(valore_attributo)
                FROM ldap_attributi ldat, ldap_struttura ldst
                WHERE ldat.id_dn = ldst.id_dn
                      --AND  upper(nome_attributo) = 'MEMBER'*/
                      -- inserito ordinamento per mettere prima attributi member
                SELECT  (dn) dn
                     ,  (nome_attributo) attributo
                     , valore_attributo valore_attr--upper(valore_attributo) valore_attr
                     , decode (nome_attributo, 'MEMBER',1,2)
                FROM ad4_ldap_attributi
                MINUS
                SELECT  (dn), 'MEMBER', upper(valore_attributo), 1
                FROM ldap_attributi ldat, ldap_struttura ldst
                WHERE ldat.id_dn = ldst.id_dn
                      AND upper (nome_attributo) = 'MEMBEROF'
                MINUS
                SELECT  (dn)
                     , upper (nome_attributo)
                     , valore_attributo --upper(valore_attributo)
                     , decode (upper(nome_attributo), 'MEMBER',1,2)
                FROM ldap_attributi ldat, ldap_struttura ldst
                WHERE ldat.id_dn = ldst.id_dn
                order by dn, 4, 2
                      )
            LOOP
               d_dn   := v_attr_ldap.dn;
               --d_trc := 'Attributo ' || v_attr_ldap.attributo || ' valore ' || v_attr_ldap.valore_attr;
               -- tutto quello che e in ad4 ma non in ldap va inserito
               IF upper(v_attr_ldap.attributo) = 'MEMBER'
               THEN
                  -- se il dn contiene la virgola vuol dire che non aveva trovato il cn
                  -- (quindi nel campo dn c'e rimasto il cn) in ldap prima
                  -- dell'allineamento ma ora potrebbe essere stato inserito.
                  IF INSTR (d_dn
                          , ','
                     ) = 0
                  THEN
                     d_dn   := get_name (d_dn);
                  END IF;
--                  IF INSTR (d_dn
--                          , ','
--                     ) > 0
--                  THEN
--                  scrivi_segnalazione(' inserisce '
--                                          || d_dn
--                                          || ' in '
--                                          || v_attr_ldap.valore_attr);
--                     d_trc:= 'Inserimento '
--                                          || d_dn
--                                          || ' in '
--                                          || v_attr_ldap.valore_attr;
                     integritypackage.LOG( d_trc);
                    -- scrivi_allegato( d_trc);
                     begin
                        set_attributo ( v_attr_ldap.valore_attr
                                  , v_attr_ldap.attributo
                                  ,d_dn
                        );
                     exception
                        when others then
                         scrivi_segnalazione('member' || v_attr_ldap.valore_attr
                                  ||':'|| v_attr_ldap.attributo);
                         scrivi_segnalazione(d_dn);
                         scrivi_allegato('>>ERRORE in assegnazione member ' || v_attr_ldap.valore_attr
                                  ||':'|| v_attr_ldap.attributo);
                         raise;
                     end;
                  --END IF;
               ELSE -- non attributo member
                   declare
                   v_trovato number := 0;
                   begin
                   begin
                   -- lo devo fare solo se non è bloccato in base ai valori nei registri
                   select 1
                     into v_trovato
                     from ldap_attributi a
                        , ldap_struttura s
                   where  a.id_dn = s.id_dn
                     and  A.NOME_ATTRIBUTO = ldap_noupdate_campo
                     and  a.valore_attributo = ldap_noupdate_valore
                     and  s.dn = d_dn;
                   exception
                   when no_data_found then v_trovato := 0;
                   end;
                   if v_trovato = 0 then -- non è bloccato
                   declare
                   d_attributo varchar2(32767);
                   begin
                      d_attributo   :=
                                     finmatica_ldap.get_attribute (ldap_url
                                                                 , ldap_user
                                                                 , ldap_password
                                                                 , d_dn
                                                                 , v_attr_ldap.attributo
                                     );
                      if nvl(d_attributo,'valorenullovalore') != nvl(v_attr_ldap.valore_attr,'valorenullovalore')
                         or( d_attributo is not null and v_attr_ldap.valore_attr is not null) then -- valori sono diversi
                         integritypackage.LOG('attribuire a '
                                           || v_attr_ldap.attributo
                                           || ' = '
                                           || v_attr_ldap.valore_attr);
                      begin
    --                     d_trc := 'ATTRIBUTO ' || v_attr_ldap.attributo ||' PER: '|| d_dn || '='||v_attr_ldap.valore_attr;
    --                     scrivi_allegato (d_trc);
                         set_attributo ( d_dn
                                      , v_attr_ldap.attributo
                                      ,v_attr_ldap.valore_attr
                         );
                      exception
                         when others then
                          scrivi_segnalazione(d_dn||':'|| v_attr_ldap.attributo);
                          scrivi_segnalazione(v_attr_ldap.valore_attr
                                      );
                          scrivi_allegato ('>> ERRORE assegnazione attributo '|| v_attr_ldap.attributo|| ' a dn:' || d_dn);
                          raise;
                      end;
                      end if; -- valori sono diversi
                   end;
                   end if; -- non è bloccato
                   end;
               END IF;
            END LOOP;
            v_intestazione_messaggi := chr(10);
--   EXCEPTION WHEN OTHERS THEN
--   raise_application_error (-20999,'ERRORE ' || d_trc, TRUE);
   END do_ldap_mapping;
   PROCEDURE ad4_viste_to_tabelle
   IS
   BEGIN
    scrivi_segnalazione('inizio carico tabelle ad4_ldap' || to_char(sysdate ,'hh24:mi:ss'));
   -- sistemazione tabella che contiene quanto presente in ad4
            execute immediate 'truncate table struttura_utenti_temp';
            INSERT INTO struttura_utenti_temp(DESCRIZIONE, FIGLIO, TIPO_FIGLIO,
                                             PADRE, TIPO_PADRE, STRUTTURA,
                                             LIVELLO, ORD, GRUPPO_SO
                                             )
            SELECT upper(DESCRIZIONE), FIGLIO, upper(TIPO_FIGLIO),
                   PADRE, upper(TIPO_PADRE), STRUTTURA,
                   LIVELLO, upper(ORD), upper(GRUPPO_SO)
              FROM struttura_utenti;
            COMMIT;
            get_parametri_ldap;
            execute immediate 'truncate table ad4_ldap_struttura';
            INSERT INTO ad4_ldap_struttura
               (DN, TIPO, DESCRIZIONE,   FIGLIO)
               SELECT upper(DN), upper(TIPO), upper(DESCRIZIONE),   FIGLIO
                 FROM ad4_ldap_struttura_vista;
            COMMIT;
            integrityPackage.log('riempita ad4_ldap_struttura');
    scrivi_segnalazione('fine carico tabelle ad4_ldap' || to_char(sysdate ,'hh24:mi:ss'));
   END;
   PROCEDURE spedizione_mail
  IS
      d_destination_email varchar2(100);
      begin
      if v_id_allegato != 0 then
         d_destination_email   :=
         registro_utility.leggi_stringa ('PRODUCTS/LDAPCONFIG/NOTIFICA'
                                       ,'Address'
                                       , 1
         );
      IF d_destination_email IS NULL
      THEN
        null;
         raise_application_error (-20999
                                , 'Impossibile inviare mail di attività di allineamento So4-Ad4-Ldap'
                                  || '. Indirizzo di spedizione nullo (chiave ''PRODUCTS/LDAPCONFIG/NOTIFICA'' stringa ''Address'').'
         );
     ELSE
         invio_mail (p_nome =>  d_destination_email
                       , p_subject =>v_dicitura_allineamento_inizio
                       , p_text_msg => 'In allegato il file con le operazioni effettuate per allineamento Ad4-Ldap'
                       , p_id_allegato => v_id_allegato
                       , p_nome_allegato => v_nome_allegato);
      END IF;
      end if;
      end;
   PROCEDURE allinea_ldap (p_rigenera_so integer DEFAULT 1
                         , p_debug integer DEFAULT 0
   )
   /******************************************************************************
    NOME:        allinea_ldap.
    DESCRIZIONE: Ribalta i dati presenti in AD4 in Ldap.
                L'attività utilizza delle tabelle di passaggio nelle quali mette
                i dati delle viste che altrimenti sarebbero troppo lunghe da gestire.
    PARAMETRI:  p_rigenera_so: indica se effettuare l'allineamento dei dati di
                struttura da SO4 a AD4 0=non fare nulla, 1=fare allineamento dati
                p_debug: indica se scrivere informazioni per il debug, se 0=no debug
                altrimenti scrive le info.
    NOTE:        Primo numero  : versione compatibilita del Package.
                 Secondo numero: revisione del Package specification.
                 Terzo numero  : revisione del Package body.
   ******************************************************************************/
   IS
      d_cn_utente   ldap_attribute;
      rigenera_so_err exception;
      v_messaggio   VARCHAR2 (100);
      d_evento      NUMBER;
      d_err_id      INTEGER := 0;
   BEGIN
   d_clob  := EMPTY_CLOB();
   v_id_allegato := 0;
   BEGIN
    scrivi_segnalazione('inizio' || to_char(sysdate ,'hh24:mi:ss'));
   d_evento      :=
         ad4_evento.insert_evento (p_testo => 'Allineamento So4-Ldap'
                                 , p_db_user => 'AD4'
                                 , p_data => 'no'
                                 , p_notificato => 0
                                 , p_gravita => 'I'
                                 , p_tipo => 'APPTRC'
                                 , p_annotazioni => 'Allineamento'
                                 , p_utente => 'AD4'
                                 , p_modulo => 'AD4'
                                 , p_istanza => 'AD4'
         );
      IF p_debug = 1
      THEN
         integritypackage.setdebugon;
      ELSE
         integritypackage.setdebugoff;
      END IF;
      get_parametri_ldap;         -- carica gli attributi per lavorare su ldap
      integritypackage.LOG ('get_parametri_ldap; -- carica gli attributi ');
      IF p_rigenera_so = 1 THEN
         null;
         d_err_id   := utente.rigenera_so ('%');
      END IF;
         integritypackage.LOG ('rigenera_so error_id: ' || d_err_id);
      IF LOWER (ldap_mapping) = 'yes'
      THEN
         IF d_err_id != 0
         THEN
            v_messaggio   := 'Allineamento Ad4 da SO4 errore: ' || d_err_id;
            RAISE rigenera_so_err;           -- errore in allineamento con SO4
         -- va poi in others come gestione errore.
         ELSE
            -- allineamento senza errori da SO4
            -- sistemazione tabella che contiene quanto presente in ad4
            ad4_viste_to_tabelle;
            d_err_id   := 0;
            -- tenere presente che allineamento viene fatto in notturna quindi che
            -- bisogna togliere da ldap quello che non c'e piu in ad4
            -- e bisogna replicare ad4 in ldap mettendo le cose nuove e sistemando
            -- la struttura.
            -- Tenere conto che le classi java per inserire un oggetto se non esiste
            -- il sottoramo lo creano
            -- Se esiste gia l'oggetto uguale non da errore.
            ricarica_struttura_ldap;
            integritypackage.LOG ('ricarica_struttura_ldap');
             -- si deve allineare partendo da quanto in AD4
            -- richiamo allineamento struttura con SO4
            do_ldap_mapping;
         END IF;
      END IF; -- ldap_mapping
      if v_id_allegato != 0 then -- c'è qualcosa da segnalare, viene inviata la mail.
          scrivi_allegato ('TERMINATO '|| v_dicitura_allineamento || to_char(sysdate, 'dd/mm/yyyy hh24:mi:ss'));
          spedizione_mail;
      end if;
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         DECLARE
            d_err   VARCHAR2 (32000);
         BEGIN
            d_err            :=
               SUBSTR (SQLERRM
                     , 1
                     , 1940
               );
            ROLLBACK;
            SELECT keel_sq.NEXTVAL
            INTO d_err_id
            FROM DUAL;
            IF v_messaggio IS NULL
            THEN                                    -- non altri errori prima.
               v_messaggio   := v_dicitura_allineamento_inizio;
            END IF;
            key_error_log_tpk.ins (p_error_id => d_err_id
                                 , p_error_session => USERENV ('sessionid')
                                 , p_error_date => SYSDATE
                                 , p_error_text => v_messaggio || d_err
                                 , p_error_user => 'AD4'--USER
                                 , p_error_usertext => v_messaggio || d_err
                                 , p_error_type => 'E'
            );
           scrivi_allegato ( v_messaggio || d_err);
           scrivi_allegato ('FINE CON ERRORE'|| v_dicitura_allineamento|| to_char(sysdate, 'dd/mm/yyyy hh24:mi:ss'));
           begin
           spedizione_mail;
           -- se errore in spedizione mail era gia stato segnalato
           exception when others then
            scrivi_allegato ('ERRORE INVIO MAIL ');
           end;
           --RAISE;         -- per fase di controllo DA TOGLIERRE!!!!!!!!!!!!!!!!!!
         END;
       END;
   END;
   FUNCTION allinea_ldap_job (p_num_ore_dopo_mezzanotte number DEFAULT NULL )
      RETURN NUMBER
   IS
      d_job   NUMBER;
   BEGIN
      d_job       :=
         job_utility.crea (p_what => 'begin ldap_utility.allinea_ldap(); end;'
                         , p_next_date => TRUNC (SYSDATE + 1)
                                         + (p_num_ore_dopo_mezzanotte / (24)) -- ore dopo mezzanotte
                         , p_interval =>   'TRUNC (SYSDATE + 1) +('
                                        || p_num_ore_dopo_mezzanotte
                                        || ' / 24)'
         );
      RETURN d_job;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END allinea_ldap_job;
   PROCEDURE scarica_ldap
   IS
   BEGIN
     get_parametri_ldap;
     ricarica_struttura_ldap;
   END;
   FUNCTION aggiungi_aoo_amm (p_utente_ad4_uo VARCHAR2)
       RETURN VARCHAR2
    IS
       v_aoo_amm   VARCHAR2 (1000);
    BEGIN
       BEGIN
          SELECT    ',OU='
                 || codice_aoo
                 || ldap_delimitatore
                 || progr_aoo
                 || ',OU='
                 || amministrazione
            INTO v_aoo_amm
            FROM anag_unita
           WHERE utente_ad4 = p_utente_ad4_uo;
       EXCEPTION
          WHEN OTHERS
          THEN
             v_aoo_amm := 'ERRORE in determinazione aoo e amm';
       END;
       RETURN v_aoo_amm;
    END;
   FUNCTION  CALCOLA_ATTRIBUTO(p_valore varchar2
                             , p_utente varchar2
                             , p_descrizione varchar2 )
   RETURN varchar2
   IS
   /******************************************************************************
    NOME:         CALCOLA_ATTRIBUTO
    DESCRIZIONE: Restituisce valore calcolato in base alla stringa indicata sui
                 registri sotto la voce PRODUCTS/LDAPCONFIG/ATTRIBUTI.
    RITORNA:     VARCHAR2 stringa contenente valore calcolato.
    NOTE:
    REVISIONI: .
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    006 13/05/2010  SNeg  Elimino protezione apici se non inizia con =.
    015 19/02/2014 SN  Tolto raddoppio apici sul nome
   ******************************************************************************/
   d_nome        soggetti.nome%TYPE;
   d_nome_vero   soggetti.nome%TYPE;
   d_cognome     soggetti.nome%TYPE;
   d_email       soggetti.indirizzo_web%TYPE;
   d_valore       ldap_attribute;
   d_descrizione varchar2(2000) := replace(p_descrizione,'''','''''');
   BEGIN
   -- sistemazione variabili con attributi utente
      d_valore := p_valore;
      d_nome   :=
         replace(INITCAP (soggetto.get_nome (utente.get_soggetto (p_utente
                                                        , 'Y'
                                     )
                                   , 'Y'
                  )
         ),'''','''''')
         ;
--      d_nome_vero   :=
--        replace( SUBSTR (d_nome
--               , INSTR (d_nome
--                      , '  '
--                 )
--                 + 2
--         ),'''','''''');
      d_nome_vero   := -- REV 15 - già raddoppiati apici nel calcolo del nome
        SUBSTR (d_nome
               , INSTR (d_nome
                      , '  '
                 )
                 + 2
                );
      -- tengo solo nome
      d_cognome   :=
         replace(INITCAP (soggetto.get_cognome (utente.get_soggetto (p_utente
                                                           , 'Y'
                                        )
                                      , 'Y'
                  )
         ),'''','''''');
      d_email   :=
         replace(LOWER (soggetto.get_indirizzo_web (utente.get_soggetto (p_utente
                                                               , 'Y'
                                            )
                                          , 'Y'
                )
         ),'''','''''');
         -- se inizia con = deve essere valorizzato il valore????
         d_valore   :=
            REPLACE (d_valore
                   , '%utente%'
                   ,  (p_utente)
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%UTENTE%'
                   ,  (p_utente)
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%username%'
                   ,  (d_descrizione)
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%USERNAME%'
                   ,  (d_descrizione)
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%nome%'
                   , d_nome_vero
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%NOME%'
                   , d_nome_vero
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%cognome%'
                   , d_cognome
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%COGNOME%'
                   , d_cognome
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%email%'
                   , d_email
            );
         d_valore   :=
            REPLACE (d_valore
                   , '%EMAIL%'
                   , d_email
            );
         -- se inizia con = deve essere valorizzato il valore
         if substr(ltrim(d_valore),1,1) = '=' then
            d_valore := esegui_calcolo(d_valore);
         else
         -- non e da calcolare tolgo protezione apici
            d_valore := replace (d_valore,'''''','''');
         end if;
   return d_valore;
   exception
   when others then
      scrivi_segnalazione('val' ||p_valore ||'.'|| p_utente ||'.'|| d_descrizione) ;
      raise;
   END;
END ldap_utility;
/

