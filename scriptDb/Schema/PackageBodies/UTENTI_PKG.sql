CREATE OR REPLACE PACKAGE BODY UTENTI_PKG IS
/******************************************************************************
 NOME:        UTENTE.
 DESCRIZIONE: Package body per gestione UTENTI.
 ANNOTAZIONI: Salvato nella directory ins di AD4 nel file uten.pkg.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  26/02/2009 MM     Creazione.
******************************************************************************/
   s_revisione_body      CONSTANT Afc.t_revision := '000';
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
 000  26/02/2009 MM     Creazione.
******************************************************************************/
RETURN VARCHAR2
IS
BEGIN
   RETURN Afc.VERSION ( s_revisione, s_revisione_body );
END VERSIONE;
procedure rinomina_utente
/******************************************************************************
 NOME:        rinomina_utente
 DESCRIZIONE: Rinomina p_utente in p_nuovo_utente.
 PARAMETRI:   p_utente:     codice dell'utente.
              p_utente:     nuovo codice da associare a p_utente.
 ECCEZIONI:   -
 ANNOTAZIONI: Vengono DISABILITATI TRIGGER di UTENTI, UTENTI_GRUPPO,
              DIRITTI_ACCESSO.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  26/02/2009 MM     Creazione.
******************************************************************************/
( p_utente in utenti.UTENTE%type
, p_nominativo in utenti.nominativo%type
, p_nuovo_utente in utenti.utente%TYPE
, p_nuovo_nominativo in utenti.nominativo%type
)
IS
   d_utente                utenti.utente%TYPE := p_utente;
   d_nuovo_utente          utenti.utente%TYPE := nvl(p_nuovo_utente, p_utente);
   d_id_utente             utenti.id_utente%TYPE;
   d_nominativo            utenti.nominativo%type := p_nominativo;
   d_nuovo_nominativo      utenti.nominativo%type := nvl(p_nuovo_nominativo, p_nominativo);
   d_tipo_utente           utenti.tipo_utente%TYPE;
   d_password              utenti.password%type;
   d_data_password         VARCHAR2(10);
   d_rinnovo_password      utenti.rinnovo_password%type;
   d_pwd_da_modificare     utenti.pwd_da_modificare%type;
   d_stato                 utenti.stato%type;
   d_note                  utenti.note%type;
   d_lingua                utenti.lingua%type;
   d_gruppo_lavoro         utenti.gruppo_lavoro%type;
   d_importanza            utenti.importanza%type;
   d_utente_aggiornamento  utenti.utente_aggiornamento%TYPE;
   d_ultimo_tentativo      VARCHAR2(19);
   d_numero_tentativi      utenti.numero_tentativi%TYPE;
   d_data_inserimento      VARCHAR2(19);
   d_data_aggiornamento    VARCHAR2(19);
   pragma autonomous_transaction;
   v_master_link           varchar2(2000);
   d_statement             varchar2(2000);
BEGIN
   if p_utente is null and p_nuovo_utente is null and p_nominativo is null and p_nuovo_nominativo is null then
      raise_application_error(-20999, 'Impossibile eseguire rinomina_utente senza parametri.');
   end if;
   if nvl(d_utente, ' ') = nvl(d_nuovo_utente, ' ') and nvl(d_nominativo, ' ') = nvl(d_nuovo_nominativo, ' ') then
      raise_application_error(-20999, 'Il nuovo utente e l''originale coincidono.');
   end if;
   if p_utente is not null then
      -- Controllo esistenza p_utente
      if utente.exists_codice(p_utente) = 0 then
         raise_application_error(-20999, 'Impossibile rinominare utente '''||p_utente||''' in '''||p_nuovo_utente||'''. Codice utente '''||p_utente||''' non esistente.');
      end if;
   end if;
   if nvl(d_nuovo_utente, ' ') <> nvl(d_utente, ' ') then
      -- Controllo che non esista p_nuovo_utente
      if utente.exists_codice(p_nuovo_utente) = 1 then
         raise_application_error(-20999, 'Impossibile rinominare utente '''||p_utente||''' in '''||p_nuovo_utente||'''. Codice utente '''||p_nuovo_utente||''' gia'' utilizzato.');
      end if;
   end if;
   if p_nominativo is not null then
      -- Controllo esistenza p_nominativo
      if utente.exists_nominativo(p_nominativo) = 0 then
         raise_application_error(-20999, 'Impossibile rinominare nome utente '''||p_nominativo||''' in '''||p_nuovo_nominativo||'''. Nominativo utente '''||p_nominativo||''' non esistente.');
      end if;
   end if;
   if nvl(d_nuovo_nominativo, ' ') <> nvl(d_nominativo, ' ') then
      -- Controllo che non esista p_nuovo_nominativo
      if utente.exists_nominativo(p_nuovo_nominativo) = 1 then
         raise_application_error(-20999, 'Impossibile rinominare nome utente '''||p_nominativo||''' in '''||p_nuovo_nominativo||'''. Nominativo utente '''||p_nuovo_nominativo||''' gia'' utilizzato.');
      end if;
   end if;
   if p_utente is not null then
      if p_nuovo_utente is null then
         raise_application_error(-20999, 'Impossibile rinominare utente '''||p_utente||''' in '''||p_nuovo_utente||'''.');
      end if;
   end if;
   if p_nuovo_utente is not null then
      if p_utente is null then
         raise_application_error(-20999, 'Impossibile rinominare utente '''||p_utente||''' in '''||p_nuovo_utente||'''.');
      end if;
   end if;
   if p_nominativo is not null then
      if p_nuovo_nominativo is null then
         raise_application_error(-20999, 'Impossibile rinominare nome utente '''||p_nominativo||''' in '''||p_nuovo_nominativo||'''.');
      end if;
   end if;
   if p_nuovo_nominativo is not null then
      if p_nominativo is null then
         raise_application_error(-20999, 'Impossibile rinominare nome utente '''||p_nominativo||''' in '''||p_nuovo_nominativo||'''.');
      end if;
   end if;
   afc.sql_execute ('alter table utenti disable all triggers');
   if nvl(d_nuovo_utente, ' ') != nvl(d_utente, ' ') then
      afc.sql_execute ('alter table utenti_gruppo disable all triggers');
      afc.sql_execute ('alter table diritti_accesso disable all triggers');
      -- devo rinominare il codice
      select nvl(d_nominativo, nominativo), password,
         TO_CHAR(data_password,'dd/mm/yyyy'), rinnovo_password, TO_CHAR(ultimo_tentativo,'dd/mm/yyyy hh24:mm:ss'),
         numero_tentativi, tipo_utente, note,
         id_utente, stato, TO_CHAR(data_inserimento,'dd/mm/yyyy hh24:mm:ss'),
         utente_aggiornamento, TO_CHAR(data_aggiornamento,'dd/mm/yyyy hh24:mm:ss'), lingua,
         gruppo_lavoro, importanza, pwd_da_modificare
        into d_nominativo, d_password, d_data_password, d_rinnovo_password, d_ultimo_tentativo
           , d_numero_tentativi, d_tipo_utente, d_note, d_id_utente, d_stato, d_data_inserimento
           , d_utente_aggiornamento, d_data_aggiornamento, d_lingua, d_gruppo_lavoro
           , d_importanza, d_pwd_da_modificare
        from utenti
       where utente = p_utente
         and nominativo = nvl(d_nominativo, nominativo)
      ;
      -- cambio nominativo dell'originale
      integrityPackage.log('rinomina_utente: update nominativo orig');
      UPDATE utenti
         SET nominativo = SUBSTR (nominativo
                                , 1
                                , 38
                                 ) || '#*'
       WHERE utente = p_utente;
      -- non posso togliere subito l'utente,
      -- ne inserisco uno nuovo "corretto"
      d_id_utente := NULL;
      IF d_tipo_utente = 'U'
      THEN
         integrityPackage.log('rinomina_utente: utente.ins '||d_nuovo_utente);
         integrityPackage.log('
         utente.ins( p_nominativo                => RTRIM ('''||d_nominativo||''', ''#*'')
                   , p_utente                    => '''||d_nuovo_utente||'''
                   , p_id_utente                 => '||d_id_utente||'
                   , p_password                  => '''||d_password||'''
                   , p_data_password             => '''||d_data_password||'''
                   , p_rinnovo_password          => '''||d_rinnovo_password||'''
                   , p_pwd_da_modificare         => '''||d_pwd_da_modificare||'''
                   , p_stato                     => '''||d_stato||'''
                   , p_note                      => '''||d_note||'''
                   , p_lingua                    => '''||d_lingua||'''
                   , p_gruppo_lavoro             => '''||d_gruppo_lavoro||'''
                   , p_importanza                => '''||d_importanza||'''
                   , p_utente_aggiornamento      => '''||d_utente_aggiornamento||'''
                   , p_soggetto                  => NULL
                   , p_ultimo_tentativo          => '''||d_ultimo_tentativo||'''
                   , p_numero_tentativi          => '||d_numero_tentativi||'
                   , p_data_inserimento          => '''||d_data_inserimento||'''
                   , p_data_aggiornamento        => '''||d_data_aggiornamento||'''
                   , p_is_psw_crypted            => 1
                   );');
            utente.init;
            utente.ins( p_nominativo                => RTRIM (d_nominativo, '#*')
                   , p_utente                    => d_nuovo_utente
                   , p_id_utente                 => d_id_utente
                   , p_password                  => d_password
         ,           p_data_password             => d_data_password
                   , p_rinnovo_password          => d_rinnovo_password
                   , p_pwd_da_modificare         => d_pwd_da_modificare
                   , p_stato                     => d_stato
                   , p_note                      => d_note
                   , p_lingua                    => d_lingua
                   , p_gruppo_lavoro             => d_gruppo_lavoro
                   , p_importanza                => d_importanza
                   , p_utente_aggiornamento      => d_utente_aggiornamento
                   , p_soggetto                  => NULL
                   , p_ultimo_tentativo          => d_ultimo_tentativo
                   , p_numero_tentativi          => d_numero_tentativi
                   , p_data_inserimento          => ''--d_data_inserimento
                   , p_data_aggiornamento        => ''--d_data_aggiornamento
                   , p_is_psw_crypted            => 1
                   );
         integrityPackage.log('rinomina_utente: utente.update_utente '||d_nuovo_utente);
         utente.update_utente('N');
      ELSE
         integrityPackage.log('rinomina_utente: gruppo.ins');    -- non utente
         gruppo.ins( p_descrizione        => d_nominativo
                   , p_codice             => d_nuovo_utente
                   , p_id                 => d_id_utente
                   , p_tipo               => d_tipo_utente
                   , p_gruppo_lavoro      => d_gruppo_lavoro
                   );
      END IF;
      integrityPackage.log('rinomina_utente: INSERT INTO diritti_accesso');
      INSERT INTO diritti_accesso
                  (utente, modulo, istanza, ruolo, sequenza, ultimo_accesso
                 , numero_accessi, gruppo, note)
      SELECT p_nuovo_utente, modulo, istanza, ruolo, sequenza
           , ultimo_accesso, numero_accessi, gruppo, note
        FROM diritti_accesso
       WHERE utente = p_utente
      ;
      UPDATE caratteristiche_accesso
         SET utente = p_nuovo_utente
       WHERE utente = p_utente;
      UPDATE credenziali_utente
         SET utente = p_nuovo_utente
       WHERE utente = p_utente;
      UPDATE eventi
         SET utente = p_nuovo_utente
       WHERE utente = p_utente;
      UPDATE richieste_abilitazione
         SET utente = p_nuovo_utente
       WHERE utente = p_utente;
      UPDATE utenti_gruppo
         SET utente = p_nuovo_utente
       WHERE utente = p_utente;
      UPDATE utenti_gruppo
         SET gruppo = p_nuovo_utente
       WHERE gruppo = p_utente;
      UPDATE utenti_soggetti
         SET utente = p_nuovo_utente
       WHERE utente = p_utente;
      -- altri riferimenti a utenti
      integrityPackage.log('rinomina_utente: altri riferimenti a utenti');
      FOR v_utente IN (SELECT *
                         FROM all_users)
      LOOP
         -- potrebbe non esistere
         BEGIN
            SELECT s.MASTER_LINK
              INTO v_master_link
              FROM all_tables tab, all_snapshots s, all_tab_columns tabc
             WHERE tab.table_name = 'AMV_DOCUMENTI'
               AND tab.owner = tabc.owner
               AND tab.owner = v_utente.username
               AND tab.table_name = tabc.table_name
               AND tabc.column_name = 'UTENTE_AGGIORNAMENTO'
               AND s.table_name(+) = tab.table_name
               AND s.owner(+) = tab.owner;
            d_statement :=  'UPDATE '
                             || v_utente.username
                             || '.amv_documenti SET utente_aggiornamento = '''
                             || p_nuovo_utente
                             || ''''
                             || ' WHERE utente_aggiornamento = '''
                             || p_utente
                             || '''';
            if v_master_link is not null then
               d_statement := 'begin '||v_utente.username||'.afc.sql_execute'||v_master_link||'('''||replace(d_statement,'''', '''''')||'''); end;';
            end if;
            afc.sql_execute(d_statement);
            COMMIT;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               NULL;
         END;
      -- potrebbe non esistere
         BEGIN
            SELECT s.MASTER_LINK
              INTO v_master_link
              FROM all_tables tab, all_snapshots s, all_tab_columns tabc
             WHERE tab.table_name = 'AMV_DOCUMENTI'
               AND tab.owner = tabc.owner
               AND tab.owner = v_utente.username
               AND tab.table_name = tabc.table_name
               AND tabc.column_name = 'AUTORE'
               AND s.table_name(+) = tab.table_name
               AND s.owner(+) = tab.owner;
            d_statement :=  'UPDATE '
                             || v_utente.username
                             || '.amv_documenti SET AUTORE = '''
                             || p_nuovo_utente
                             || ''''
                             || ' WHERE AUTORE = '''
                             || p_utente
                             || '''';
            if v_master_link is not null then
               d_statement := 'begin '||v_utente.username||'.afc.sql_execute'||v_master_link||'('''||replace(d_statement,'''', '''''')||'''); end;';
            end if;
            afc.sql_execute(d_statement);
            COMMIT;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               NULL;
         END;
         -- potrebbe non esistere
         BEGIN
            SELECT s.MASTER_LINK
              INTO v_master_link
              FROM all_tables tab, all_snapshots s, all_tab_columns tabc
             WHERE tab.table_name = 'AMV_DOCUMENTI_REVISIONI'
               AND tab.owner = v_utente.username
               AND tab.owner = tabc.owner
               AND tab.table_name = tabc.table_name
               AND tabc.column_name = 'UTENTE_REDAZIONE'
               AND s.table_name(+) = tab.table_name
               AND s.owner(+) = tab.owner;
            d_statement :=  'UPDATE '
                       || v_utente.username
                       || '.amv_documenti_revisioni SET utente_redazione = '''
                       || p_nuovo_utente
                       || ''''
                       || ' WHERE utente_redazione = '''
                       || p_utente
                       || '''';
            if v_master_link is not null then
               d_statement := 'begin '||v_utente.username||'.afc.sql_execute'||v_master_link||'('''||replace(d_statement,'''', '''''')||'''); end;';
            end if;
            afc.sql_execute(d_statement);
            COMMIT;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               NULL;
         END;
         -- potrebbe non esistere
         BEGIN
            SELECT s.MASTER_LINK
              INTO v_master_link
              FROM all_tables tab, all_snapshots s, all_tab_columns tabc
             WHERE tab.table_name = 'AMV_DOCUMENTI_REVISIONI'
               AND tab.owner = v_utente.username
               AND tab.owner = tabc.owner
               AND tab.table_name = tabc.table_name
               AND tabc.column_name = 'UTENTE_VERIFICA'
               AND s.table_name(+) = tab.table_name
               AND s.owner(+) = tab.owner;
            d_statement := 'UPDATE '
                        || v_utente.username
                        || '.amv_documenti_revisioni SET utente_verifica = '''
                        || p_nuovo_utente
                        || ''''
                        || ' WHERE utente_verifica = '''
                        || p_utente
                        || '''';
            if v_master_link is not null then
               d_statement := 'begin '||v_utente.username||'.afc.sql_execute'||v_master_link||'('''||replace(d_statement,'''', '''''')||'''); end;';
            end if;
            afc.sql_execute(d_statement);
            COMMIT;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               NULL;
         END;
         -- potrebbe non esistere
         BEGIN
            SELECT s.MASTER_LINK
              INTO v_master_link
              FROM all_tables tab, all_snapshots s, all_tab_columns tabc
             WHERE tab.table_name = 'AMV_DOCUMENTI'
               AND tab.owner = v_utente.username
               AND tab.owner = tabc.owner
               AND tab.table_name = tabc.table_name
               AND tabc.column_name = 'UTENTE_APPROVAZIONE'
               AND s.table_name(+) = tab.table_name
               AND s.owner(+) = tab.owner;
            d_statement :=  'UPDATE '
                             || v_utente.username
                             || '.amv_documenti SET utente_approvazione = '''
                             || p_nuovo_utente
                             || ''''
                             || ' WHERE utente_approvazione = '''
                             || p_utente
                             || '''';
            if v_master_link is not null then
               d_statement := 'begin '||v_utente.username||'.afc.sql_execute'||v_master_link||'('''||replace(d_statement,'''', '''''')||'''); end;';
            end if;
            afc.sql_execute(d_statement);
            COMMIT;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               NULL;
         END;
         -- potrebbe non esistere
         -- NON SO SU CHE UTENTE SONO LE COMPETENZE
         BEGIN
            SELECT s.MASTER_LINK
              INTO v_master_link
              FROM all_tables t, all_snapshots s
             WHERE t.table_name = 'SI4_COMPETENZE'
               AND t.owner = v_utente.username
               AND s.table_name(+) = t.table_name
               AND s.owner(+) = t.owner
            ;
            d_statement := 'UPDATE '
                        || v_utente.username
                        || '.SI4_COMPETENZE'
                        ||' SET utente = '''
                        || p_nuovo_utente
                        || ''''
                        || ' WHERE utente = '''
                        || p_utente
                        || '''';
            if v_master_link is not null then
               d_statement := 'begin '||v_utente.username||'.afc.sql_execute'||v_master_link||'('''||replace(d_statement,'''', '''''')||'''); end;';
            end if;
            afc.sql_execute(d_statement);
            COMMIT;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               NULL;
         END;
      END LOOP;
      UPDATE comuni
         SET utente_aggiornamento = p_nuovo_utente
       WHERE utente_aggiornamento = p_utente;
      UPDATE consolati
         SET utente_aggiornamento = p_nuovo_utente
       WHERE utente_aggiornamento = p_utente;
      UPDATE credenziali
         SET utente_aggiornamento = p_nuovo_utente
       WHERE utente_aggiornamento = p_utente;
      UPDATE parametri_richiesta
         SET utente_aggiornamento = p_nuovo_utente
       WHERE utente_aggiornamento = p_utente;
      UPDATE province
         SET utente_aggiornamento = p_nuovo_utente
       WHERE utente_aggiornamento = p_utente;
      UPDATE raggruppamenti_stati
         SET utente_aggiornamento = p_nuovo_utente
       WHERE utente_aggiornamento = p_utente;
      UPDATE regioni
         SET utente_aggiornamento = p_nuovo_utente
       WHERE utente_aggiornamento = p_utente;
      UPDATE stati_territori
         SET utente_aggiornamento = p_nuovo_utente
       WHERE utente_aggiornamento = p_utente;
      UPDATE utenti
         SET utente_aggiornamento = p_nuovo_utente
       WHERE utente_aggiornamento = p_utente;
      -- riferimenti a gruppi
      UPDATE diritti_accesso
         SET gruppo = p_nuovo_utente
       WHERE gruppo = p_utente;
      UPDATE servizi
         SET gruppo_abilitazione = p_nuovo_utente
       WHERE gruppo_abilitazione = p_utente;
      UPDATE utenti_gruppo
         SET gruppo = p_nuovo_utente
       WHERE gruppo = p_utente;
      FOR v_utente IN (SELECT *
                         FROM all_users)
      LOOP
         -- potrebbe non esistere
         BEGIN
            SELECT s.MASTER_LINK
              INTO v_master_link
              FROM all_tables tab, all_snapshots s
             WHERE tab.table_name = 'AMV_DIRITTI' AND tab.owner = v_utente.username
               AND s.table_name(+) = tab.table_name
               AND s.owner(+) = tab.owner;
            d_statement :=  'UPDATE '
                             || v_utente.username
                             || '. amv_diritti SET gruppo = '''
                             || p_nuovo_utente
                             || ''''
                             || ' WHERE gruppo = '''
                             || p_utente
                             || '''';
            if v_master_link is not null then
               d_statement := 'begin '||v_utente.username||'.afc.sql_execute'||v_master_link||'('''||replace(d_statement,'''', '''''')||'''); end;';
            end if;
            afc.sql_execute(d_statement);
            COMMIT;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               NULL;
         END;
      END LOOP;
      -- adesso che ho sistemato tutto cancello l'utente
      DELETE      utenti
            WHERE utente = p_utente
      ;
      afc.sql_execute ('alter table utenti_gruppo enable all triggers');
      afc.sql_execute ('alter table diritti_accesso enable all triggers');
   else
      -- devo rinominare il nominativo
      if nvl(p_nominativo, ' ') != nvl(p_nuovo_nominativo, ' ') then
            UPDATE utenti
               SET nominativo = d_nuovo_nominativo
             WHERE nominativo = d_nominativo;
      end if;
   end if;
   afc.sql_execute ('alter table utenti enable all triggers');
END;
END UTENTI_PKG;
/

