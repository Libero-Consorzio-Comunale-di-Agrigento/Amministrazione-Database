CREATE OR REPLACE package body istanza_amm_pkg
is
/******************************************************************************
 NOME:        istanze_amm_pkg
 DESCRIZIONE: Gestione tabella ISTANZE.
 ANNOTAZIONI: NON DEVE AVERE DIPENDENZE DA ALTRI PACKAGE DI CUI SI DA VISIBILITA'
              AD ALTRI UTENTI (ISTANZE_TPK, ISTANZA, ...) per evitare problemi
              in creazione sinonimi e attribuzione grant per quei package
              tramite ADMIN_AD4.
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   24/07/2007  MMalferrari  Prima emissione.
 001   21/04/2010  MMalferrari  A37545.0.0: Una volta inserito, non e piu possibile
                                eliminare link oracle su un'istanza.
                                Modificata upd_commit.
******************************************************************************/
   s_revisione_body   constant AFC.t_revision := '001';
   function versione
      return varchar2
   is
/******************************************************************************
 NOME:        versione
 DESCRIZIONE: Versione e revisione di distribuzione del package.
 RITORNA:     varchar2 stringa contenente versione e revisione.
 NOTE:        Primo numero  : versione compatibilita del Package.
              Secondo numero: revisione del Package specification.
              Terzo numero  : revisione del Package body.
******************************************************************************/
   begin
      return AFC.version (s_revisione, s_revisione_body);
   end versione;
   procedure init_log
   is
      PRAGMA AUTONOMOUS_TRANSACTION;
   begin
      delete key_error_log
       where ERROR_TEXT = 'UPD_ISTANZA_AMM'
         and ERROR_TYPE = 'l';
      commit;
   exception
      when others
      then
         raise;
   end;
   function get_istanza_ad4
      return ISTANZE.istanza%type
   is
/******************************************************************************
 NOME:        get_istanza_ad4
 DESCRIZIONE: Cerca codice istanza collegata allo user AD4 nel progetto AD4.
 RITORNA:     varchar2: codice istanza collegata allo user AD4 o null se non la
                        trova.
 NOTE:        --
******************************************************************************/
      d_return   ISTANZE.istanza%type;
   begin
      begin
         select min (istanza)
           into d_return
           from ISTANZE
          where upper (user_oracle) = 'AD4' and progetto = 'AD4';
      exception
         when no_data_found
         then
            d_return := null;
      end;
      return d_return;
   end get_istanza_ad4;
   FUNCTION get_ISTANZA_AMMINISTRATORE
   ( p_DATABASE_USER  IN ISTANZE.USER_ORACLE%TYPE
   , p_ISTANZA IN ISTANZE.ISTANZA%TYPE DEFAULT NULL
   ) RETURN ISTANZE.ISTANZA_AMMINISTRATORE%TYPE IS
/******************************************************************************
 NOME:        get_ISTANZA_AMMINISTRATORE
 DESCRIZIONE: Attributo ISTANZA_AMMINISTRATORE di riga esistente identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     ISTANZE.ISTANZA_AMMINISTRATORE%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
      d_istanza_amm ISTANZE.ISTANZA_AMMINISTRATORE%TYPE;
   BEGIN
      select distinct nvl(ISTANZA_AMMINISTRATORE, get_istanza_ad4())
        into d_istanza_amm
        from istanze
       where upper(user_oracle) = upper(p_DATABASE_USER)
         and istanza <> nvl(p_ISTANZA, ' ')
      ;
      RETURN  d_istanza_amm;
   END;
   function is_istanza_amministratore (p_istanza in ISTANZE.istanza%type)
      return number
   is
/******************************************************************************
 NOME:        is_istanza_amministratore
 DESCRIZIONE: Verifica se l'istanza e' un istanza di amministratore.
 RITORNA:     number: 1 se lo e', 0 altrimenti
 NOTE:        --
******************************************************************************/
      d_return   number (1);
   begin
      begin
         select 1
           into d_return
           from ISTANZE
          where istanza = p_istanza and progetto = 'AD4';
      exception
         when no_data_found
         then
            d_return := 0;
      end;
      return d_return;
   end is_istanza_amministratore;
   FUNCTION count_database_user
   ( p_DATABASE_USER   IN ISTANZE.USER_ORACLE%TYPE
   , p_ISTANZA IN ISTANZE.ISTANZA%TYPE DEFAULT NULL)
   RETURN INTEGER IS
   /******************************************************************************
    NOME:        count_database_user
    DESCRIZIONE: Ritorna il numero di righe della tabella che hanno lo user_oracle
                 dato diverse dall'istanza data (se significativa).
    PARAMETRI:   p_DATABASE_USER
                 p_ISTANZA
    RITORNA:     Numero di righe che rispettano la selezione indicata.
   ******************************************************************************/
      d_count integer;
   BEGIN
      select count(1)
        into d_count
        from istanze
       where upper(user_oracle) = upper(p_DATABASE_USER)
         and istanza <> nvl(p_ISTANZA, ' ')
      ;
      return d_count;
   END;
   procedure crea_admin_xx4_pkg
   ( p_user    in    varchar2
   , p_error   out   varchar2)
   is
      d_clob      clob;
      d_esiste    number;
      d_pac_name  varchar2(30) := 'ADMIN_XX4';
   begin
      begin
         select count(1)
           into d_esiste
           from all_objects
          where object_name = d_pac_name
            and owner = upper(p_user)
         ;
      exception
         when no_data_found
         then
            d_esiste := 0;
      end;
      if d_esiste = 0
      then
         d_clob := si4_user_source_tpk.get_text(d_pac_name||'_PKS', 'PL/SQL BLOCK');
         d_clob := afc_lob.replace_clob(d_clob, '&usr.', p_user||'.');
         afc_lob.sql_execute(d_clob);
         d_clob := si4_user_source_tpk.get_text(d_pac_name||'_PKB', 'PL/SQL BLOCK');
         d_clob := afc_lob.replace_clob(d_clob, '&usr.', p_user||'.');
         afc_lob.sql_execute(d_clob);
      end if;
      afc.SQL_EXECUTE('begin '||p_user||'.si4.sql_execute(''alter package '||d_pac_name||' compile''); end;');
   exception
      when others
      then
         p_error := 'Errore in crezione '||p_user||'.'||d_pac_name||': '|| SQLERRM||chr(10);
   end;
   function compila
    ( p_user in ISTANZE.user_oracle%type
   ) return NUMBER
   is
      d_log    varchar2(32767);
      d_return number := 0;
   begin
      d_log := '   Compila oggetti di '''|| p_user|| '''.'||chr(10);
      add_log(d_log);
      begin
         afc.SQL_EXECUTE(
            'begin '||
               p_user||'.UtilityPackage.compile_all;
            end;');
      exception
         when others
         then
            d_log := '      '||sqlerrm||chr(10);
            add_log(d_log);
            d_return := -1;
      end;
      return d_return;
   end;
   function allinea_soggetti
   ( p_user_amministratore in ISTANZE.user_oracle%type
   ) return NUMBER
   is
      d_log    varchar2(32767);
      d_return number := 0;
   begin
      d_log := '   Inserisce i soggetti nella nuova anagrafe .'||chr(10);
      add_log(d_log);
      declare
         d_clob clob;
      begin
         si4_user_source_pkg.addparm('&usr_amm.', p_user_amministratore||'.');
         si4_user_source_pkg.execute('ALLINEA_SOGGETTI', 'PL/SQL BLOCK');
      exception
         when others
         then
            d_log := '      Fallito allineamento soggetti in '''|| p_user_amministratore ||''': '||sqlerrm||chr(10);
            add_log(d_log);
            d_return := -1;
      end;
      return d_return;
   end;
   function rinomina_soggetti
   ( p_user_amministratore in ISTANZE.user_oracle%type
   ) return NUMBER
   is
      d_obj_type  all_objects.object_type%type;
      d_log       varchar2(32767);
      d_return    number := 0;
   begin
      begin
         select object_type
           into d_obj_type
           from all_objects
          where object_name = 'SOGGETTI_OLD'
            and owner = upper(p_user_amministratore)
         ;
      exception
         when no_data_found
         then
            d_obj_type := 'NO';
      end;
      d_log := '   Rinomina tabella SOGGETTI in '''|| p_user_amministratore|| '''.'||chr(10);
      add_log(d_log);
      begin
         afc.SQL_EXECUTE(
            'begin '||
               'if '''|| d_obj_type || ''' <> ''NO'' '||
               'then '||
               '   '|| p_user_amministratore ||'.afc.SQL_EXECUTE(''drop '|| d_obj_type ||' SOGGETTI_OLD''); '||
               'end if; '||
               p_user_amministratore||'.afc.SQL_EXECUTE(''rename SOGGETTI to SOGGETTI_OLD''); '||
            'end; ');
      end;
      return d_return;
   exception
      when others
      then
         d_log := '      '||sqlerrm||chr(10);
         add_log(d_log);
         return -1;
   end;
   function integrazione_anagrafe_as4
    ( p_user_anagrafe             in      ISTANZE.user_oracle%type
    , p_istanza_amministratore    in      ISTANZE.istanza_amministratore%type
    , p_user_amministratore       in      ISTANZE.user_oracle%type
    , p_old_user_amministratore   in      ISTANZE.user_oracle%type
   ) return NUMBER
   is
/******************************************************************************
 NOME:        integrazione_anagrafe
 DESCRIZIONE: --
 NOTE:        --
******************************************************************************/
      d_istanza_ad4                  ISTANZE.istanza%type;
      d_istanza_amministratore       ISTANZE.istanza_amministratore%type;
      d_obj_type                     all_objects.object_type%type;
      d_progetto                     PROGETTI.progetto%type;
      d_log                          varchar2 (32767);
      d_return                       number := 0;
   begin
      if nvl(p_old_user_amministratore, ' ') <> nvl(p_user_amministratore, ' ')
      then
         d_istanza_ad4 := get_istanza_ad4;
         d_istanza_amministratore :=
                           upper (NVL (p_istanza_amministratore, d_istanza_ad4));
         if is_istanza_amministratore(d_istanza_amministratore) = 1
         then
            begin
               select min(progetto)
                 into d_progetto
                 from istanze
                where upper(user_oracle) = upper(p_user_anagrafe);
            exception
               when no_data_found
               then
                  d_log := '   Istanza non esistente! ('''
                         || p_user_anagrafe
                         || ''')'||chr(10);
                  add_log(d_log);
                  d_return := -1;
            end;
            if d_return = 0
            then
               if d_progetto = 'AS4'
               then
                  if d_istanza_amministratore <> d_istanza_ad4
                  then
                     d_log := '   Grant da anagrafe '''|| p_user_anagrafe|| ''' ad amministratore secondario '''||p_user_amministratore||'''.'||chr(10);
                     add_log(d_log);
                     d_log := '';
                     crea_admin_xx4_pkg(p_user_anagrafe, d_log);
                     if d_log is not null
                     then
                        add_log('      '||d_log);
                        d_return := -6;
                     else
                        admin_ad4.grant_da_anagrafe_as4  ( p_user_anagrafe
                                                         , p_user_amministratore
                                                         , d_log);
                        if upper(p_user_amministratore) <> 'AD4' and d_log is not null
                        then
                           d_log := '   Grant da anagrafe '''|| p_user_anagrafe|| ''' ad amministratore ''AD4''.'||chr(10);
                           add_log(d_log);
                           d_log := '';
                           admin_ad4.grant_da_anagrafe_as4  ( p_user_anagrafe
                                                            , 'AD4'
                                                            , d_log);
                        end if;
                        if d_log is not null
                        then
                           d_log := '      Fallita assegnazione grant da anagrafe '||p_user_anagrafe||': '||d_log||chr(10);
                           add_log(d_log);
                           d_return := -2;
                        else
                           d_log := '   Crea in '''|| p_user_amministratore|| ''' i sinonimi agli oggetti della nuova anagrafe '''|| p_user_anagrafe ||'''.'||chr(10);
                           add_log(d_log);
                           d_log := '';
                           admin_ad4.crea_syn_anagrafe_as4 ( p_user_anagrafe
                                                           , p_user_amministratore
                                                           , d_log);
                           if d_log is not null
                           then
                              d_log := '      Fallita creazione sinonimi dell''anagrafe '||p_user_anagrafe||': '||d_log||chr(10);
                              add_log(d_log);
                              d_return := -3;
                           else
                           -- Se SOGGETTI e' una tabella, la rinomina, crea la vista e
                           -- riassegna agli utenti le grant su SOGGETTI.
                              begin
                                 select object_type
                                   into d_obj_type
                                   from all_objects
                                  where object_name = 'SOGGETTI'
                                    and owner = upper(p_user_amministratore)
                                 ;
                                 if d_obj_type = 'TABLE'
                                 then
                                    d_return := rinomina_soggetti(p_user_amministratore);
                                    if d_return = 0
                                    then
                                       d_log := '   Crea vista SOGGETTI in '''|| p_user_amministratore || '''.'||chr(10);
                                       add_log(d_log);
                                       begin
                                          afc.SQL_EXECUTE(
                                             'begin '||
                                                p_user_amministratore||'.si4_user_source_pkg.execute(''SOGGETTI'', ''VIEW''); '||
                                                p_user_amministratore||'.si4_user_source_pkg.execute(''AS4_SOGGETTI_PM'',''PROCEDURE'');
                                                admin_ad4.grant_to_all(''SOGGETTI'', ''ALL'', '''||p_user_amministratore||''');
                                             end;');
                                       exception
                                          when others
                                          then
                                             d_log := '      Fallita gestione ''SOGGETTI'' in '''||p_user_amministratore||''': '||sqlerrm||chr(10);
                                             add_log(d_log);
                                             d_return := -4;
                                       end;
                                    end if;
                                 end if;
                              exception
                                 when others
                                 then
                                    d_log := '      Impossibile determinare tipo dell'' oggetto ''SOGGETTI'' in '''||p_user_amministratore||''': '||sqlerrm||chr(10);
                                    add_log(d_log);
                                    d_return := -5;
                              end;
                              if d_return = 0
                              then
                                 d_return := allinea_soggetti(p_user_amministratore);
                                 if d_return = 0
                                 then
                                    d_return := compila(p_user_amministratore);
                                 end if;
                              end if;
                           end if;
                        end if;
                     end if;
                  end if;
                  if nvl(p_old_user_amministratore, d_istanza_ad4) <> d_istanza_ad4
                  then
                  -- Ripristina tabella soggetti in p_old_user_amministratore,
                  -- riassegna agli utenti le grant su SOGGETTI e revoca le grant
                  -- da p_user_anagrafe a p_old_user_amministratore.
                     d_return := rinomina_soggetti(p_old_user_amministratore);
                     if d_return = 0
                     then
                        d_log := '   Ripristina tabella SOGGETTI in '''|| p_old_user_amministratore|| '''.'||chr(10);
                        add_log(d_log);
                        begin
                           afc.SQL_EXECUTE(
                              'begin '||
                                 'begin '||
                                 '   '||p_old_user_amministratore||'.afc.SQL_EXECUTE(''alter table soggetti_old drop constraint SOGG_PK''); '||
                                 'exception '||
                                 '   when others '||
                                 '   then '||
                                 '      if sqlcode = -2443 or sqlcode = -942 '||
                                 '      then '||
                                 '         null; '||
                                 '      else '||
                                 '         raise; '||
                                 '      end if; '||
                                 'end; '||
                                 p_old_user_amministratore||'.si4_user_source_pkg.execute(''AD4_SOGGETTI'', ''TABLE''); '||
                                 p_old_user_amministratore||'.si4_user_source_pkg.execute(''SOGG_PK'', ''PK CONSTRAINT''); '||
                                 p_old_user_amministratore||'.si4_user_source_pkg.execute(''AD4_SOGGETTI_PM'',''PROCEDURE'');
                                 admin_ad4.grant_to_all(''SOGGETTI'', ''ALL'', '''|| p_old_user_amministratore ||''');
                              end;');
                        exception
                           when others
                           then
                              d_log := '      Fallito ripristino ''SOGGETTI'' in '''||p_old_user_amministratore||''': '||sqlerrm||chr(10);
                              add_log(d_log);
                              d_return := -8;
                        end;
                     end if;
                     if d_return = 0
                     then
                        d_return := allinea_soggetti(p_old_user_amministratore);
                        if d_return = 0
                        then
                           d_return := compila(p_old_user_amministratore);
                        end if;
                     end if;
                  end if;
               end if;
            end if;
         end if;
      end if;
      return d_return;
   end integrazione_anagrafe_as4;
   FUNCTION aggiorna_diac_caac(
      p_user_istanza              in   ISTANZE.user_oracle%type
    , p_user_amministratore       in   ISTANZE.user_oracle%type
    , p_old_user_amministratore   in   ISTANZE.user_oracle%type
   ) RETURN VARCHAR2
   is
      d_clob clob;
      d_return varchar2(32767);
   begin
      begin
         d_clob := si4_user_source_tpk.get_text('ISTANZA_AMM_ALLINEA_DIAC_CAAC', 'PL/SQL BLOCK');
         d_clob := afc_lob.replace_clob(d_clob, '&usr_istanza', p_user_istanza);
         d_clob := afc_lob.replace_clob(d_clob, '&usr_amm_old', p_old_user_amministratore);
         d_clob := afc_lob.replace_clob(d_clob, '&usr_amm_new', p_user_amministratore);
         afc_lob.sql_execute(d_clob);
      exception
         when others
         then
            d_return := 'Errore in allineamento diritti / caratteristiche da user '''|| p_old_user_amministratore ||''' a user '''|| p_user_amministratore ||''' su user '''|| p_user_istanza || '''' || chr(10) || sqlerrm;
      end;
      return d_return;
   end aggiorna_diac_caac;
   procedure add_log
   ( p_log in varchar2)
   is
      d_log  varchar2(32767);
      d_loop   integer;
      d_length integer := 2000;
      PRAGMA AUTONOMOUS_TRANSACTION;
   begin
      if p_log is not null
      then
         d_log := p_log;
         d_loop := CEIL (length(d_log) / d_length);
         FOR i in 1 .. d_loop
         loop
            d_log := substr(p_log, (d_length * (i - 1)) + 1, d_length);
            insert into key_error_log  ( ERROR_SESSION, ERROR_DATE, ERROR_TEXT
                                       , ERROR_USER, ERROR_USERTEXT, ERROR_TYPE)
            select userenv('sessionid'), sysdate, 'UPD_ISTANZA_AMM'
                 , user, d_log, 'l'
              from dual;
         end loop;
         commit;
      end if;
   exception
      when others
      then
         raise;
   end;
   procedure gestisci_istanza_amm (
      p_user_istanza                 in   ISTANZE.user_oracle%type
    , p_istanza_amministratore       in   ISTANZE.istanza_amministratore%type
    , p_old_istanza_amministratore   in   ISTANZE.istanza_amministratore%type
   )
   is
/******************************************************************************
 NOME:        gestisci_istanza_amm
 DESCRIZIONE: Verifica se l'istanza e' un istanza di amministratore.
 RITORNA:     number 1 se lo e'
                     0 altrimenti
 NOTE:        --
******************************************************************************/
      d_istanza_ad4                  ISTANZE.istanza%type;
      d_istanza_amministratore       ISTANZE.istanza_amministratore%type;
      d_old_istanza_amministratore   ISTANZE.istanza_amministratore%type;
      d_user                         ISTANZE.user_oracle%type;
      d_user_amm                     ISTANZE.user_oracle%type;
      d_old_user_amm                 ISTANZE.user_oracle%type;
      d_log                          varchar2 (32767);
      d_progetto                     PROGETTI.progetto%type;
      PRAGMA AUTONOMOUS_TRANSACTION;
   begin
      d_istanza_ad4 := get_istanza_ad4;
      d_istanza_amministratore :=
                        upper (NVL (p_istanza_amministratore, d_istanza_ad4));
      if is_istanza_amministratore(d_istanza_amministratore) = 0
      then
         raise_application_error(-20999,'L''istanza '''||d_istanza_amministratore||''' non appartiene al progetto AD4!');
      end if;
      d_old_istanza_amministratore :=
                    upper (NVL (p_old_istanza_amministratore, d_istanza_ad4));
      begin
         select username
           into d_user
           from all_users
          where username = upper(p_user_istanza);
      exception
         when no_data_found
         then
            raise_application_error (-20999
                                   ,    'User non esistente! ('''
                                     || p_user_istanza
                                     || ''')'
                                    );
      end;
      begin
         select upper (user_oracle)
           into d_user_amm
           from istanze
          where istanza = d_istanza_amministratore;
      exception
         when no_data_found
         then
            raise_application_error (-20999
                                   ,    'Istanza non esistente! ('''
                                     || d_istanza_amministratore
                                     || ''')'
                                    );
      end;
      begin
         select upper (user_oracle)
           into d_old_user_amm
           from istanze
          where istanza = d_old_istanza_amministratore
         ;
      exception
         when no_data_found
         then
            raise_application_error (-20999
                                   ,    'Istanza non esistente! ('''
                                     || d_old_istanza_amministratore
                                     || ''')'
                                    );
      end;
      declare
         d_return number := 0;
      begin
            begin
               select min(progetto)
                 into d_progetto
                 from istanze
                where upper(user_oracle) = upper(d_user)
                  and progetto = 'AS4'
               ;
               -- eventuale integrazione con anagrafe
               d_log := to_char(sysdate, 'hh24:mi:ss')||'   Integrazione '''|| d_user_amm ||''' con anagrafe '''|| d_user ||'''.'|| chr(10);
               add_log(d_log);
               d_return := integrazione_anagrafe_as4
                                         ( d_user
                                         , d_istanza_amministratore
                                         , d_user_amm
                                         , d_old_user_amm
                                         );
               if d_return < 0
               then
                  rollback;
               end if;
               COMMIT;
            exception
               when no_data_found
               then
                  null;
            end;
      exception
         when others
         then
            rollback;
            add_log(SQLERRM|| chr(10));
      end;
      d_log := to_char(sysdate, 'hh24:mi:ss')||'   Revoca grant a '''|| d_user ||''' su oggetti di '''|| d_old_user_amm ||'''';
      d_log := d_log|| chr(10) ||
         admin_ad4.REVOKE_FROM (p_user       => d_user
                              , p_owner      => d_old_user_amm);
      add_log(d_log);
      d_log := to_char(sysdate, 'hh24:mi:ss')||'   Eliminazione sinonimi in '''|| d_user ||''' su oggetti di '''|| d_old_user_amm ||'''';
      d_log := d_log|| chr(10) ||
         admin_ad4.DROP_SYNONYMS (p_user       => d_user
                                 , p_owner     => d_old_user_amm
                                 );
      add_log(d_log);
      d_log := to_char(sysdate, 'hh24:mi:ss')||'   Assegnazione grant a '''|| d_user ||''' su oggetti di '''|| d_user_amm ||'''';
      d_log := d_log|| chr(10) ||
         admin_ad4.GRANT_TO ( p_user       => d_user
                            , p_owner      => d_user_amm
                            );
      add_log(d_log);
      if d_user_amm <> 'AD4'
      then                             -- per ad4 esistono i sinonimi pubblici
         d_log := to_char(sysdate, 'hh24:mi:ss')||'   Creazione sinonimi in '''|| d_user ||''' su oggetti di '''|| d_user_amm ||'''';
         d_log := d_log|| chr(10) ||
               admin_ad4.CREATE_SYNONYMS ( p_user       => d_user
                                         , p_owner      => d_user_amm
                                         );
         add_log(d_log);
      end if;
      d_log := to_char(sysdate, 'hh24:mi:ss')||'   Allineamento diritti / caratteristiche da user '''|| d_old_user_amm ||''' a user '''|| d_user_amm ||''' su user '''|| p_user_istanza ||''''|| chr(10);
      add_log(d_log);
      d_log := aggiorna_diac_caac(p_user_istanza, d_user_amm, d_old_user_amm);
      if d_log is not null
      then
         rollback;
         add_log(d_log);
      end if;
      COMMIT;
      d_log := to_char(sysdate, 'hh24:mi:ss')||'   Assegnazione eventuali grant da user '''|| p_user_istanza ||''' a user '''|| d_user_amm ||'''.'||chr(10);
      add_log(d_log);
      declare
         d_statement varchar2(32767);
         d_loop      integer := 0;
      begin
         d_log := '   Creazione package '''||p_user_istanza||'.ADMIN_XX4''.'||chr(10);
         add_log(d_log);
         d_log := '';
         crea_admin_xx4_pkg(upper(p_user_istanza), d_log);
         if d_log is not null
         then
            add_log('      '||d_log);
         else
            for c_privs in (select table_name, privilege
                              from ALL_TAB_PRIVS
                             where grantee = d_old_user_amm
                               and grantor = upper(p_user_istanza))
            loop
               begin
                  d_log := '   Grant a '''||d_user_amm||''' su '''||p_user_istanza||'.'||c_privs.table_name||'''.'||chr(10);
                  add_log(d_log);
                  d_log := '';
                  d_statement := 'begin '|| p_user_istanza ||'.admin_xx4.execute_grant (:p_user, :p_what, :p_privilege, :p_error); end;';
                  execute immediate d_statement
                              using IN d_user_amm, IN c_privs.table_name, IN c_privs.privilege, IN OUT d_log;
               exception
                  when others
                  then
                     d_log := SQLERRM||chr(10);
               end;
               if d_log is not null
               then
                  add_log('      '||d_log);
               end if;
               begin
                  d_log := '   Revoca grant a '''||d_user_amm||''' su '''||p_user_istanza||'.'||c_privs.table_name||'''.'||chr(10);
                  add_log(d_log);
                  d_log := '';
                  d_statement := 'begin '|| p_user_istanza ||'.admin_xx4.revoke_grant (:p_user, :p_what, :p_privilege, :p_error); end;';
                  execute immediate d_statement
                              using IN d_old_user_amm, IN c_privs.table_name, IN c_privs.privilege, IN OUT d_log;
               exception
                  when others
                  then
                     d_log := SQLERRM||chr(10);
               end;
               if d_log is not null
               then
                  add_log('      '||d_log);
               end if;
            end loop;
          end if;
      end;
      d_log := to_char(sysdate, 'hh24:mi:ss')||'   Compilazione oggetti di database user '''|| p_user_istanza ||'''.'|| chr(10);
      add_log(d_log);
      begin
         afc.sql_execute('begin '||p_user_istanza||'.utilityPackage.compile_all; end;');
      exception
         when others
         then
            add_log('   '||SQLERRM|| chr(10));
      end;
   EXCEPTION
      WHEN OTHERS
      THEN
         rollback;
         raise;
   end gestisci_istanza_amm;
   procedure upd_istanza_amm
   ( p_NEW_ISTANZA                in istanze.ISTANZA%type
   , p_NEW_DATABASE_USER          in istanze.USER_ORACLE%type
   , p_NEW_ISTANZA_AMMINISTRATORE in istanze.ISTANZA_AMMINISTRATORE%type
   , p_OLD_ISTANZA_AMMINISTRATORE in istanze.ISTANZA_AMMINISTRATORE%type)
   is
   begin
      init_log;
      integrityPackage.NEXTNESTLEVEL;
      UPDATE istanze
         SET ISTANZA_AMMINISTRATORE = p_NEW_ISTANZA_AMMINISTRATORE
       WHERE upper(user_oracle) = upper(p_NEW_DATABASE_USER)
      ;
      integrityPackage.PREVIOUSNESTLEVEL;
      if nvl(p_NEW_ISTANZA_AMMINISTRATORE, get_istanza_ad4()) <> nvl(p_OLD_ISTANZA_AMMINISTRATORE, get_istanza_ad4())
      then
         GESTISCI_ISTANZA_AMM(p_NEW_DATABASE_USER, p_NEW_ISTANZA_AMMINISTRATORE, p_OLD_ISTANZA_AMMINISTRATORE);
      end if;
   end;
   PROCEDURE ins_commit
      ( p_ISTANZA  IN ISTANZE.Istanza%TYPE
      , p_PROGETTO  IN ISTANZE.PROGETTO%TYPE
      , p_ENTE  IN ISTANZE.ENTE%TYPE
      , p_DESCRIZIONE  IN ISTANZE.DESCRIZIONE%TYPE
      , p_DATABASE_USER IN ISTANZE.USER_ORACLE%TYPE
      , p_DATABASE_PASSWORD IN ISTANZE.PASSWORD_ORACLE%TYPE
      , p_DISLOCAZIONE  IN ISTANZE.DISLOCAZIONE%TYPE
      , p_DISLOCAZIONE_TEMPORANEA  IN ISTANZE.DISLOCAZIONE_TEMPORANEA%TYPE  DEFAULT NULL
      , p_INSTALLAZIONE  IN ISTANZE.INSTALLAZIONE%TYPE  DEFAULT NULL
      , p_VERSIONE  IN ISTANZE.VERSIONE%TYPE  DEFAULT NULL
      , p_DISLOCAZIONE_DIMENSIONAMENTI  IN ISTANZE.DISLOCAZIONE_DIMENSIONAMENTI%TYPE  DEFAULT NULL
      , p_NOTE  IN ISTANZE.NOTE%TYPE  DEFAULT NULL
      , p_LINGUA  IN ISTANZE.LINGUA%TYPE  DEFAULT 'I'
      , p_LINK_ORACLE  IN ISTANZE.LINK_ORACLE%TYPE  DEFAULT NULL
      , p_DATABASE_LINK  IN ISTANZE.DATABASE_LINK%TYPE  DEFAULT NULL
      , p_DATABASE_DRIVER  IN ISTANZE.DATABASE_DRIVER%TYPE  DEFAULT NULL
      , p_SERVIZIO  IN ISTANZE.SERVIZIO%TYPE  DEFAULT NULL
      , p_ISTANZA_AMMINISTRATORE IN ISTANZE.ISTANZA_AMMINISTRATORE%TYPE  DEFAULT NULL
   ) IS
   /******************************************************************************
    NOME:        ins_commit
    DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati.
    PARAMETRI:   Chiavi e attributi della table.
    NOTE:        ESEGUE COMMIT!!!
   ******************************************************************************/
      d_count          integer;
      d_altre_ista_amm istanze.istanza_amministratore%type;
      d_ista_amm       istanze.istanza_amministratore%type;
      d_ista_amm_old   istanze.istanza_amministratore%type;
   BEGIN
      begin
         d_count := COUNT_DATABASE_USER(upper(p_DATABASE_USER), p_ISTANZA);
         if d_count > 0 then
            begin
               d_altre_ista_amm := GET_ISTANZA_AMMINISTRATORE(p_database_user);
            exception
               when too_many_rows
               then
                  raise_application_error(-20999,'Esistono istanze legate allo stesso user di database ma con istanza di amministratore diversa.');
            end;
         end if;
      end;
      integrityPackage.NEXTNESTLEVEL;
      INSERT INTO ISTANZE
                  (ISTANZA, PROGETTO, ENTE, DESCRIZIONE, USER_ORACLE
                 , PASSWORD_ORACLE, DISLOCAZIONE, DISLOCAZIONE_TEMPORANEA
                 , INSTALLAZIONE, VERSIONE, DISLOCAZIONE_DIMENSIONAMENTI
                 , NOTE, LINGUA, LINK_ORACLE, DATABASE_LINK
                 , DATABASE_DRIVER, SERVIZIO
                  )
           VALUES (p_ISTANZA, p_PROGETTO, p_ENTE, p_DESCRIZIONE, p_DATABASE_USER
                 , p_DATABASE_PASSWORD, p_DISLOCAZIONE, p_DISLOCAZIONE_TEMPORANEA
                 , p_INSTALLAZIONE, p_VERSIONE, p_DISLOCAZIONE_DIMENSIONAMENTI
                 , p_NOTE, p_LINGUA, p_LINK_ORACLE, p_DATABASE_LINK
                 , p_DATABASE_DRIVER, p_SERVIZIO
                  );
      integrityPackage.PREVIOUSNESTLEVEL;
      begin
         d_ista_amm := nvl(p_ISTANZA_AMMINISTRATORE, get_istanza_ad4());
         d_ista_amm_old := d_ista_amm;
         -- se non esistono altre istanze per lo stesso user e istanza_amministratore <> AD4;
         -- oppure
         -- se ne esistono ma con istanza_amministratore <> da quella nuova
         if (d_count = 0 and get_istanza_ad4() <> d_ista_amm)
         or (d_count > 0 and d_altre_ista_amm <> d_ista_amm)
         then
            d_ista_amm_old := '';
         end if;
         upd_istanza_amm(p_ISTANZA, p_DATABASE_USER, p_ISTANZA_AMMINISTRATORE, d_ista_amm_old);
      end;
      commit;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END ins_commit;
   PROCEDURE upd_commit
      ( p_NEW_ISTANZA  IN ISTANZE.Istanza%TYPE
      , p_NEW_PROGETTO  IN ISTANZE.PROGETTO%TYPE
      , p_NEW_ENTE  IN ISTANZE.ENTE%TYPE
      , p_NEW_DESCRIZIONE  IN ISTANZE.DESCRIZIONE%TYPE
      , p_NEW_DATABASE_USER IN ISTANZE.USER_ORACLE%TYPE
      , p_NEW_DATABASE_PASSWORD IN ISTANZE.PASSWORD_ORACLE%TYPE
      , p_NEW_DISLOCAZIONE  IN ISTANZE.DISLOCAZIONE%TYPE
      , p_NEW_DISLOCAZIONE_TEMPORANEA  IN ISTANZE.DISLOCAZIONE_TEMPORANEA%TYPE
      , p_NEW_INSTALLAZIONE  IN ISTANZE.INSTALLAZIONE%TYPE
      , p_NEW_VERSIONE  IN ISTANZE.VERSIONE%TYPE
      , p_NEW_DISL_DIMENSIONAMENTI  IN ISTANZE.DISLOCAZIONE_DIMENSIONAMENTI%TYPE
      , p_NEW_NOTE  IN ISTANZE.NOTE%TYPE
      , p_NEW_LINGUA  IN ISTANZE.LINGUA%TYPE
      , p_NEW_LINK_ORACLE  IN ISTANZE.LINK_ORACLE%TYPE
      , p_NEW_DATABASE_LINK  IN ISTANZE.DATABASE_LINK%TYPE
      , p_NEW_DATABASE_DRIVER  IN ISTANZE.DATABASE_DRIVER%TYPE
      , p_NEW_SERVIZIO  IN ISTANZE.SERVIZIO%TYPE
      , p_NEW_ISTANZA_AMMINISTRATORE IN ISTANZE.ISTANZA_AMMINISTRATORE%TYPE
      , p_OLD_ISTANZA  IN ISTANZE.Istanza%TYPE
      , p_OLD_ISTANZA_AMMINISTRATORE IN ISTANZE.ISTANZA_AMMINISTRATORE%TYPE
   ) IS
   /******************************************************************************
    NOME:        upd_commit
    DESCRIZIONE: Aggiornamento di una riga con chiave.
    PARAMETRI:   Chiavi e attributi della table
                 p_check_OLD: 0, ricerca senza controllo su attributi precedenti
                              1, ricerca con controllo anche su attributi precedenti.
    NOTE:        ESEGUE COMMIT!!!
                 Nel caso in cui non venga elaborato alcun record viene lanciata
                 l'eccezione -20010 (cfr. AFC_ERROR).
                 Se p_check_old = 1, viene controllato se il record corrispondente a
                 tutti i campi passati come parametri esiste nella tabella.
   ******************************************************************************/
   BEGIN
      ISTANZE_TPK.UPD ( P_CHECK_OLD => 0, P_NEW_ISTANZA => P_NEW_ISTANZA, P_OLD_ISTANZA => P_NEW_ISTANZA
                      , P_NEW_PROGETTO => P_NEW_PROGETTO, P_OLD_PROGETTO => P_NEW_PROGETTO
                      , P_NEW_ENTE => P_NEW_ENTE, P_OLD_ENTE => ''
                      , P_NEW_DESCRIZIONE => P_NEW_DESCRIZIONE, P_OLD_DESCRIZIONE => ''
                      , P_NEW_USER_ORACLE => P_NEW_DATABASE_USER, P_OLD_USER_ORACLE => ''
                      , P_NEW_PASSWORD_ORACLE => P_NEW_DATABASE_PASSWORD, P_OLD_PASSWORD_ORACLE => ''
                      , P_NEW_DISLOCAZIONE => P_NEW_DISLOCAZIONE, P_OLD_DISLOCAZIONE => ''
                      , P_NEW_DISLOCAZIONE_TEMPORANEA => P_NEW_DISLOCAZIONE_TEMPORANEA, P_OLD_DISLOCAZIONE_TEMPORANEA => ''
                      , P_NEW_INSTALLAZIONE => P_NEW_INSTALLAZIONE, P_OLD_INSTALLAZIONE => ''
                      , P_NEW_VERSIONE => P_NEW_VERSIONE, P_OLD_VERSIONE => ''
                      , P_NEW_DISLOCAZIONE_DIMENSIONAM => p_NEW_DISL_DIMENSIONAMENTI, P_OLD_DISLOCAZIONE_DIMENSIONAM => ''
                      , P_NEW_NOTE => P_NEW_NOTE, P_OLD_NOTE => ''
                      , P_NEW_LINGUA => P_NEW_LINGUA, P_OLD_LINGUA => ''
                      , P_NEW_LINK_ORACLE => P_NEW_LINK_ORACLE, P_OLD_LINK_ORACLE => ''
                      , P_NEW_DATABASE_LINK => P_NEW_DATABASE_LINK, P_OLD_DATABASE_LINK => ''
                      , P_NEW_SERVIZIO => P_NEW_SERVIZIO, P_OLD_SERVIZIO => ''
                      , P_NEW_DATABASE_DRIVER => P_NEW_DATABASE_DRIVER, P_OLD_DATABASE_DRIVER => ''
                      , P_NEW_ISTANZA_AMMINISTRATORE => P_NEW_ISTANZA_AMMINISTRATORE, P_OLD_ISTANZA_AMMINISTRATORE => P_OLD_ISTANZA_AMMINISTRATORE);
      if    nvl(p_NEW_ISTANZA_AMMINISTRATORE, ' ') <> nvl(p_OLD_ISTANZA_AMMINISTRATORE, ' ')
      then
         upd_istanza_amm(p_NEW_ISTANZA, p_NEW_DATABASE_USER, p_NEW_ISTANZA_AMMINISTRATORE, p_OLD_ISTANZA_AMMINISTRATORE);
      end if;
      commit;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END upd_commit;
   PROCEDURE del_commit
      ( p_ISTANZA  IN ISTANZE.Istanza%TYPE
      , p_DATABASE_USER IN ISTANZE.USER_ORACLE%TYPE
      , p_ISTANZA_AMMINISTRATORE IN ISTANZE.ISTANZA_AMMINISTRATORE%TYPE
   ) IS
   /******************************************************************************
    NOME:        del_commit
    DESCRIZIONE: Eliminazione di una riga con chiave e attributi indicati.
    PARAMETRI:   Chiavi e attributi della table.
    NOTE:        ESEGUE COMMIT!!!
   ******************************************************************************/
   BEGIN
      declare
         d_count integer;
         d_ista_ad4 istanze.ISTANZA%TYPE;
      begin
         d_count := COUNT_DATABASE_USER(upper(p_DATABASE_USER), p_ISTANZA);
         d_ista_ad4 := get_istanza_ad4();
         -- se non esistono altre istanze per lo stesso user e istanza_amministratore <> AD4;
         if d_count = 0 and d_ista_ad4 <> nvl(p_ISTANZA_AMMINISTRATORE, d_ista_ad4)
         then
            upd_istanza_amm(p_ISTANZA, p_DATABASE_USER, p_ISTANZA_AMMINISTRATORE, d_ista_ad4);
         end if;
      end;
      integrityPackage.NEXTNESTLEVEL;
      delete ISTANZE
       where istanza = p_ISTANZA
      ;
      integrityPackage.PREVIOUSNESTLEVEL;
      commit;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END del_commit;
   function get_log
   return clob
   is
      d_clob_log clob;
   begin
      dbms_lob.createTemporary (d_clob_log, TRUE, dbms_lob.SESSION);
      for logs in   (select error_usertext
                       from key_error_log
                      where error_type = 'l'
                      order by error_id)
      loop
         dbms_lob.writeappend(d_clob_log, length(logs.error_usertext), logs.error_usertext);
      end loop;
      return d_clob_log;
   end;
end istanza_amm_pkg;
/

