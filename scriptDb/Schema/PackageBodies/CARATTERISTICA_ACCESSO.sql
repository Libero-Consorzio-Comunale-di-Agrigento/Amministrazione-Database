CREATE OR REPLACE PACKAGE BODY Caratteristica_Accesso IS
/******************************************************************************
 NOME:        CARATTERISTICA_ACCESSO.
 DESCRIZIONE: Package body per gestione CARATTERISTICHE_ACCESSO.
 ANNOTAZIONI: Salvato nella directory ins di AD4 nel file uten.pkg.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    13/01/2003 MM     Creazione.
 1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
 2    29/11/2005 MM     Inserimento procedure VALIDITA_PWD.
      01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                        DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                        e NUMERI_OBB_PWD.
      25/05/2006 MM     Creazione GESTISCI_PWDLENGTH.
 3    22/03/2007 MM     A20271.0.0.: modificata update_caratteristica.
 4    24/04/2007 MM     A20741.0.0: aggiunte procedure del.
 005  13/07/2007 MM     Adeguamento versione a nuovi standard.
                        Modifica a check_password.
 006  24/10/2013 SN    Validita password valutata su tutti i diritti di
                      accesso esistenti.
 007  04/01/2016 SN    Corretta valutazione degli attributi di accesso
 008  21/09/2018 SN    Aggiunto campo gg_prima_riutilizzo_pwd
 009  16/10/2018 SN    Aggiunto campo gg_prima_riutilizzo_pwd nella get_desc_password
 10   10/12/2018 SN    Gestione caratteristiche di tipo utenza. (GET)
 011  11/01/2019 SNeg In caso di utenza ldap aggiornare pwd sempre = NO
 12   02/03/2020 SNeg  Considerare la password già criptata Feature #40748
 013 12/02/2021 SN   Allargare il campo per la gestione della password con i nuovi algoritmi Bug #48221
 014 30/03/2021 SN   Inserire le informazioni relative al numero tentativi relativo alla password #49220
******************************************************************************/
   s_revisione_body      CONSTANT Afc.t_revision := '014';
   dep_utente utenti.utente%TYPE :='AD4';
   dep_utente_is_ldap number(1):=nvl(caratteristica_accesso.is_ldapuser(dep_utente),0);
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
 0    13/01/2003 MM     Creazione.
 005  13/07/2007 MM     Adeguamento versione a nuovi standard e creazione
                        funzione exists_id.
******************************************************************************/
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   BEGIN
      RETURN Afc.VERSION ( s_revisione, s_revisione_body );
   END VERSIONE;
    FUNCTION GET_DIAC_VAL_PWD
/******************************************************************************
 NOME:        GGET_DIAC_VAL_PWD
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto il valore piu' grande della minima lunghezza della password
           per l'utente.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    21/01/2003 MM     Creazione.
******************************************************************************/
   ( p_utente VARCHAR2 )
   RETURN VARCHAR2;

    FUNCTION GET_DIAC_TENTATIVI_PWD
   ( p_utente VARCHAR2 )
   RETURN VARCHAR2;

   function exists_id
   ( p_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type
   , p_progetto  in CARATTERISTICHE_ACCESSO.progetto%type
   , p_istanza  in CARATTERISTICHE_ACCESSO.istanza%type
   , p_modulo  in CARATTERISTICHE_ACCESSO.modulo%type
   , p_utente  in CARATTERISTICHE_ACCESSO.utente%type
   ) return number is /* SLAVE_COPY */
   /******************************************************************************
    NOME:        exists_id
    DESCRIZIONE: Esistenza riga con chiave indicata.
    PARAMETRI:   Attributi chiave.
    RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
   ******************************************************************************/
      d_result number;
   begin
      begin
         select 1
         into   d_result
         from   CARATTERISTICHE_ACCESSO
         where  tipo_accesso = p_tipo_accesso
         and    progetto = p_progetto
         and    istanza = p_istanza
         and    modulo = p_modulo
         and    utente = p_utente
         ;
      exception
         when no_data_found then
            d_result := 0;
      end;
      return  d_result;
   end exists_id; -- caratteristiche_accesso_tpk.exists_id
   FUNCTION GET_MAXVALPWD
/******************************************************************************
 NOME:       GET_MAXVALPWD
 DESCRIZIONE: Ottiene il valore piu'piccolo dellavalidita della password
              settata per l'utente (considerando tutti i suoi diritti di accesso).
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     il valore piu'piccolo della durata della password.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  1   24/10/2013 SN   Nuova introduzione
******************************************************************************/
   ( p_utente VARCHAR2
   , p_tipo_utente varchar2 default null )
   RETURN NUMBER
   IS /* SLAVE_COPY */
      d_return                NUMBER := null ; --0;
      d_tipo_accesso          VARCHAR2(1) := 'D';
      d_accesso               VARCHAR2(1);
      d_accesso_se            VARCHAR2(2);
      d_traccia               VARCHAR2(1);
      d_giorni_traccia        NUMBER(3);
      d_tentativi_pwd         NUMBER(2);
      d_sleep                 NUMBER(3);
      d_single_sign_on        VARCHAR2(2);
      d_ldap                  VARCHAR2(2);
      d_lung_pwd          NUMBER(2) := 0;
      d_mod_pwd_primo_accesso VARCHAR2(2);
      d_arch_traccia        VARCHAR2(2);
      d_disl_traccia        VARCHAR2(100);
      d_car_speciali_pwd      VARCHAR2(2);
      d_num_obb_pwd           VARCHAR2(2);
      d_gg_prima_riutilizzo_pwd  CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
      d_tipo_utente           varchar2(1) := nvl(p_tipo_utente, 'E');
      d_min_val_pwd          number(3);
   BEGIN
      FOR DIAC IN (SELECT ista.progetto, DIAC.Istanza, DIAC.modulo, DIAC.Utente
                     FROM ISTANZE ista, DIRITTI_ACCESSO DIAC
                    WHERE DIAC.Utente  = p_utente
                      AND ista.Istanza = DIAC.Istanza)
      LOOP
         if d_tipo_utente = 'E' then
            d_tipo_utente := utente.GET_TIPO_UTENTE(p_utente);
         end if;
         if d_tipo_utente = 'U' then
            d_tipo_accesso := 'D';
         else
            d_tipo_accesso := 'G';
         end if;
         get_effettive ( d_tipo_accesso, DIAC.progetto, DIAC.Istanza
                       , DIAC.modulo, DIAC.Utente, d_accesso, d_accesso_se
                       , d_traccia, d_giorni_traccia, d_tentativi_pwd
                       , d_min_val_pwd, d_sleep, d_single_sign_on, d_ldap
                       , d_lung_pwd, d_mod_pwd_primo_accesso, d_arch_traccia
                       , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
         IF d_min_val_pwd is not null and d_min_val_pwd < nvl(d_return,100000) THEN
            d_return := d_min_val_pwd;
         END IF;
      END LOOP;
      RETURN d_return;
   END GET_MAXVALPWD;
   PROCEDURE GET
/******************************************************************************
 NOME:        GET.
 DESCRIZIONE: restituisce le caratteristiche di accesso al Progetto / Istanza /
              Modulo / Utente.
 ARGOMENTI:   p_tipo_accesso   IN OUT VARCHAR2    tipo di caratteristiche di
                                                  accesso (P / I / M / D).
              p_progetto       IN     VARCHAR2    progetto a cui si accede.
              p_istanza        IN     VARCHAR2    istanza a cui si accede.
              p_modulo         IN     VARCHAR2    modulo a cui si accede.
              p_utente         IN     VARCHAR2    utente che accede.
              p_accesso        IN OUT VARCHAR2    accesso ammesso.
              p_accesso_se     IN OUT VARCHAR2    senza eccezioni o super utente.
              p_traccia        IN OUT VARCHAR2    tipo di traccia sugli accessi.
              p_tentativi_pwd  IN OUT NUMBER      numero di tentativi di accesso
                                                  ammessi.
              p_val_pwd        IN OUT NUMBER      numeri di giorni di validita'
                                                  della password.
              p_sleep          IN OUT NUMBER      numero di minuti di inattivita'
                                                  dopo cui l'applicazione va in
                                                  sleep.
              p_single_sign_on IN OUT VARCHAR2    abilitazione al single sign on.
              p_ldap           IN OUT VARCHAR2    autenticazione utenti via LDAP.
              p_min_lung_pwd   IN OUT NUMBER      Lunghezza minima della password.
              p_mod_pwd_primo_accesso IN OUT VARCHAR2 Indica se l'utente deve
                                                  modificare la password al primo
                                                  accesso successivo alla modifica
                                                  della stessa da parte di un utente
                                                  diverso.
              p_arch_traccia   IN OUT VARCHAR2    attivazione archiviazione traccia.
              p_disl_traccia   IN OUT VARCHAR2    percorso su file system del db
                                                  in cui viene salvata la traccia.
              p_car_speciali_pwd IN OUT VARCHAR2  Indica se nella password sono
                                                  ammessi caratteri speciali.
              p_num_obb_pwd    IN OUT VARCHAR2    Indica se nella password deve
                                                  essere presente almeno un numero.
 ECCEZIONI:   20999, Errore Bloccante: 'Almeno il codice del progetto deve
                                        essere significativo',
                                       'Tipo di accesso non gestito.'
 ANNOTAZIONI: chiamata da GET_CAAC_EFFETTIVE.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    16/01/2001 MM     Prima emissione.
 1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
 2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                        DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                        e NUMERI_OBB_PWD.
 3    22/03/2007 MM     A20271.0.0.: modificata update_caratteristica.
 4    24/04/2007 MM     A20741.0.0: aggiunte procedure del.
 10   10/12/2018 SN     Gestione caratteristiche di tipo utenza. (GET)
 011  11/01/2019 SNeg   In caso di utenza ldap aggiornare pwd sempre = NO
******************************************************************************/
   ( p_tipo_accesso IN OUT VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2
   , p_accesso IN OUT VARCHAR2
   , p_accesso_se IN OUT VARCHAR2
   , p_traccia IN OUT VARCHAR2
   , p_giorni_traccia IN OUT NUMBER
   , p_tentativi_pwd IN OUT NUMBER
   , p_val_pwd IN OUT NUMBER
   , p_sleep IN OUT NUMBER
   , p_single_sign_on IN OUT VARCHAR2
   , p_ldap IN OUT VARCHAR2
   , p_min_lung_pwd IN OUT NUMBER
   , p_mod_pwd_primo_accesso IN OUT VARCHAR2
   , p_arch_traccia IN OUT VARCHAR2
   , p_disl_traccia IN OUT VARCHAR2
   , p_car_speciali_pwd IN OUT VARCHAR2
   , p_num_obb_pwd IN OUT VARCHAR2
   , p_giorni_prima_riutilizzo_pwd  IN OUT NUMBER)
   IS /* SLAVE_COPY */
      d_accesso           VARCHAR2(1);
      d_accesso_se        VARCHAR2(2);
      d_traccia           VARCHAR2(1);
      d_giorni_traccia    NUMBER(3);
      d_tentativi_pwd     NUMBER(2);
      d_val_pwd           NUMBER(3);
      d_sleep             NUMBER(3);
      d_single_sign_on    VARCHAR2(2);
      d_ldap              VARCHAR2(2);
      d_min_lung_pwd      NUMBER(2);
      d_mod_pwd_primo_uso VARCHAR2(2);
      d_arch_traccia      VARCHAR2(2);
      d_disl_traccia      VARCHAR2(100);
      d_car_speciali_pwd  VARCHAR2(2);
      d_num_obb_pwd       VARCHAR2(2);
      d_giorni_prima_riutilizzo_pwd number;
   BEGIN
      -- Memorizza valori di DEFAULT.
      d_accesso           := NVL(p_accesso,'L');
      d_accesso_se        := NVL(p_accesso_se,'NO');
      d_traccia           := NVL(p_traccia,'M');
      d_giorni_traccia    := NVL(p_giorni_traccia,60);
      d_tentativi_pwd     := p_tentativi_pwd;
      d_val_pwd           := p_val_pwd;
      d_sleep             := p_sleep;
      d_single_sign_on    := NVL(p_single_sign_on,'NO');
      d_ldap              := NVL(p_ldap,'NO');
      d_min_lung_pwd      := p_min_lung_pwd;
      d_mod_pwd_primo_uso := NVL(p_mod_pwd_primo_accesso,'NO');
      d_arch_traccia      := NVL(p_arch_traccia,'NO');
      d_disl_traccia      := p_disl_traccia;
      d_car_speciali_pwd  := NVL(p_car_speciali_pwd,'SI');
      d_num_obb_pwd       := NVL(p_num_obb_pwd,'NO');
      d_giorni_prima_riutilizzo_pwd := p_giorni_prima_riutilizzo_pwd;
      IF p_progetto IS NULL THEN
         RAISE_APPLICATION_ERROR(-20999,'Almeno il codice del progetto deve essere significativo');
      END IF;
      IF p_tipo_accesso IN ('P','I','M','G','D') THEN
         DECLARE
            d_statement varchar2(32767);
         BEGIN
--            d_statement  := 'SELECT  NVL(Accesso,'''||d_accesso||'''), NVL(accesso_se,'''||d_accesso_se||'''), NVL(traccia,'''||d_traccia||'''),'||
--                            '        NVL(giorni_traccia,'||d_giorni_traccia||'),tentativi_password,'||
--                            '        validita_password, sleep, NVL(single_sign_on, '''||d_single_sign_on||'''), NVL(ldap, '''||d_ldap||'''),'||
--                            '        min_lunghezza_pwd, NVL(mod_pwd_primo_accesso, '''||d_mod_pwd_primo_uso||'''),'||
--                            '        NVL(archiviazione_traccia, '''||d_arch_traccia||'''), dislocazione_traccia,'||
--                            '        NVL(ammessi_car_speciali_pwd, '''||d_car_speciali_pwd||'''), NVL(numeri_obb_pwd, '''||d_num_obb_pwd||''')'||
--                            '   FROM CARATTERISTICHE_ACCESSO'||
--                            '  WHERE tipo_accesso = '''||p_tipo_accesso||''''||
--                            '    AND progetto = '''||p_progetto||'''';
--            if p_tipo_accesso in ('I', 'G', 'D')
--            then
--                d_statement  :=  d_statement ||' AND istanza = '''||p_istanza||'''';
--            end if;
--            if p_tipo_accesso in ('M', 'G', 'D')
--            then
--                d_statement  :=  d_statement ||' AND modulo = '''||p_modulo||'''';
--            end if;
--            if p_tipo_accesso in ('G', 'D')
--            then
--                d_statement  :=  d_statement ||' AND utente = '''||p_utente||'''';
--            end if;
--            execute immediate d_statement
--               into p_accesso, p_accesso_se, p_traccia, p_giorni_traccia,
--                    p_tentativi_pwd, p_val_pwd, p_sleep, p_single_sign_on, p_ldap,
--                    p_min_lung_pwd, p_mod_pwd_primo_accesso, p_arch_traccia, p_disl_traccia,
--                    p_car_speciali_pwd, p_num_obb_pwd;
/* okkkkkkkkkkkkkkkk originale
            SELECT NVL(Accesso,d_accesso), NVL(accesso_se,d_accesso_se), NVL(traccia,d_traccia),
                   NVL(giorni_traccia,d_giorni_traccia),tentativi_password,
                   validita_password, sleep, NVL(single_sign_on, d_single_sign_on), NVL(ldap, d_ldap),
                   min_lunghezza_pwd, NVL(mod_pwd_primo_accesso, d_mod_pwd_primo_uso),
                   NVL(archiviazione_traccia, d_arch_traccia), dislocazione_traccia,
                   NVL(ammessi_car_speciali_pwd, d_car_speciali_pwd), NVL(numeri_obb_pwd, d_num_obb_pwd),
                   nvl(GG_PRIMA_RIUTILIZZO_PWD,p_giorni_prima_riutilizzo_pwd)
              INTO p_accesso, p_accesso_se, p_traccia, p_giorni_traccia,
                   p_tentativi_pwd, p_val_pwd, p_sleep, p_single_sign_on, p_ldap,
                   p_min_lung_pwd, p_mod_pwd_primo_accesso, p_arch_traccia, p_disl_traccia,
                   p_car_speciali_pwd, p_num_obb_pwd,
                   p_giorni_prima_riutilizzo_pwd
              FROM CARATTERISTICHE_ACCESSO
             WHERE tipo_accesso = p_tipo_accesso
               AND progetto = p_progetto
               AND ((p_tipo_accesso IN ('I','G','D') AND istanza = p_istanza) or p_tipo_accesso NOT IN ('I','G','D'))
               AND ((p_tipo_accesso IN ('M','G','D') AND modulo = p_modulo) or p_tipo_accesso NOT IN ('M','G','D'))
               AND ((p_tipo_accesso IN ('G','D') AND utente = p_utente) or p_tipo_accesso NOT IN ('G','D'))
            ;
            *****************************************/
            -- rev.11 inizio
--            if  dep_utente_is_ldap--nvl(is_ldapuser(p_utente),0)
--             != 1 then
--              d_mod_pwd_primo_uso := 'NO';
--            end if;
            -- rev.11 fine
            SELECT NVL(min(Accesso),d_accesso), NVL(min(accesso_se),d_accesso_se), NVL(min(traccia),d_traccia),
                   NVL(max(giorni_traccia),d_giorni_traccia),nvl(min(tentativi_password), d_tentativi_pwd),
                   nvl(max(validita_password), d_val_pwd), max(sleep), NVL(max(single_sign_on), d_single_sign_on), NVL(max(ldap), d_ldap),
                   nvl(max(min_lunghezza_pwd),p_min_lung_pwd), NVL(max(mod_pwd_primo_accesso), d_mod_pwd_primo_uso),
                   NVL(max(archiviazione_traccia), d_arch_traccia), max(dislocazione_traccia),
                   NVL(min(ammessi_car_speciali_pwd), d_car_speciali_pwd)-- no vince sul si
                   ,
                   NVL(max(numeri_obb_pwd), d_num_obb_pwd),
                   nvl(max(GG_PRIMA_RIUTILIZZO_PWD),p_giorni_prima_riutilizzo_pwd)
              INTO p_accesso, p_accesso_se, p_traccia, p_giorni_traccia,
                   p_tentativi_pwd, p_val_pwd, p_sleep, p_single_sign_on, p_ldap,
                   p_min_lung_pwd, p_mod_pwd_primo_accesso, p_arch_traccia, p_disl_traccia,
                   p_car_speciali_pwd, p_num_obb_pwd,
                   p_giorni_prima_riutilizzo_pwd
            from(
            SELECT Accesso, accesso_se,traccia,
                   giorni_traccia, tentativi_password,
                   validita_password, sleep, single_sign_on, ldap,
                   min_lunghezza_pwd, mod_pwd_primo_accesso,
                   archiviazione_traccia, dislocazione_traccia,
                   ammessi_car_speciali_pwd, numeri_obb_pwd,
                   GG_PRIMA_RIUTILIZZO_PWD
              FROM CARATTERISTICHE_ACCESSO
             WHERE tipo_accesso = p_tipo_accesso
               AND progetto = p_progetto
               AND ((p_tipo_accesso IN ('I','G','D') AND istanza = p_istanza) or p_tipo_accesso NOT IN ('I','G','D'))
               AND ((p_tipo_accesso IN ('M','G','D') AND modulo = p_modulo) or p_tipo_accesso NOT IN ('M','G','D'))
               AND ((p_tipo_accesso IN ('G','D') AND utente = p_utente) or p_tipo_accesso NOT IN ('G','D'))
                  )
            ;
         EXCEPTION WHEN NO_DATA_FOUND THEN
            p_tipo_accesso          := '';
            p_accesso               := d_accesso;
            p_accesso_se            := d_accesso_se;
            p_traccia               := d_traccia;
            p_giorni_traccia        := d_giorni_traccia;
            p_tentativi_pwd         := d_tentativi_pwd;
            p_val_pwd               := d_val_pwd;
            p_sleep                 := d_sleep;
            p_single_sign_on        := d_single_sign_on;
            p_ldap                  := d_ldap;
            p_min_lung_pwd          := d_min_lung_pwd;
            p_mod_pwd_primo_accesso := d_mod_pwd_primo_uso;
            p_arch_traccia          := d_arch_traccia;
            p_disl_traccia          := d_disl_traccia;
            p_car_speciali_pwd      := d_car_speciali_pwd;
            p_num_obb_pwd           := d_num_obb_pwd;
            p_giorni_prima_riutilizzo_pwd  := d_giorni_prima_riutilizzo_pwd;
         END;
      ELSE
         RAISE_APPLICATION_ERROR(-20999,'Tipo di accesso non gestito.'||CHR(10)||
                                        '   Valori possibili:'||CHR(10)||'      P - Progetto'||CHR(10)||
                                        '      I - Istanza'||CHR(10)||'      M - Modulo'||CHR(10)||
                                        '      G - Diritto di Gruppo'||CHR(10)||'      D - Diritto Accesso');
      END IF;
   END GET;
   PROCEDURE GET_EFFETTIVE
/******************************************************************************
 NOME:        GET_EFFETTIVE.
 DESCRIZIONE: Recupera le caratteristiche effettive dell'accesso.
 ARGOMENTI:   p_tipo_accesso  IN OUT VARCHAR2   tipo di accesso.
                                                IN:  P / I / M / D.
                                                OUT: tipo di accesso effettivo.
              p_progetto      IN     VARCHAR2   progetto a cui si accede.
              p_istanza       IN     VARCHAR2   istanza a cui si accede.
              p_modulo        IN     VARCHAR2   modulo a cui si accede.
              p_utente        IN     VARCHAR2   utente che accede.
              p_accesso       IN OUT VARCHAR2   accesso ammesso
                                                IN:  valore iniziale.
                                                OUT: accesso ammesso per
                                                     p_tipo_accesso effettivo.
              p_accesso_se    IN OUT VARCHAR2   senza eccezioni o super utente.
                                                IN:  valore iniziale.
                                                OUT: senza eccezioni o super
                                                     utente per p_tipo_accesso
                                                     effettivo.
              p_traccia       IN OUT VARCHAR2   tipo di traccia sugli accessi.
                                                IN:  valore iniziale.
                                                OUT: tipo di traccia sugli
                                                     accessi per p_tipo_accesso
                                                     effettivo.
              p_tentativi_pwd IN OUT NUMBER     numero di tentativi di accesso
                                                ammessi.
                                                IN:  valore iniziale.
                                                OUT: numero di tentativi di
                                                     accesso ammessi per
                                                     p_tipo_accesso effettivo.
              p_val_pwd       IN OUT NUMBER     numero di giorni di validita'
                                                della password.
                                                IN:  valore iniziale.
                                                OUT: numero di giorni di
                                                     validita' della password
                                                     per p_tipo_accesso
                                                     effettivo.
              p_sleep         IN OUT NUMBER     numero di minuti di inattivita'
                                                dopo cui l'applicazione va in
                                                sleep.
                                                IN:  valore iniziale.
                                                OUT: numero di minuti di
                                                     inattivita' per
                                                     p_tipo_accesso effettivo.
                                                     dopo cui l'applicazione va
                                                     in sleep.
             p_single_sign_on IN OUT VARCHAR2   abilitazione al single sign on.
                                                IN:  valore iniziale.
                                                OUT: stringa 'SI' se il single
                                                     sign on e' abilitato per
                                                     p_tipo_accesso effettivo,
                                                     'NO' altrimenti.
             p_ldap           IN OUT VARCHAR2   autenticazione utenti via LDAP.
                                                IN:  valore iniziale.
                                                OUT: stringa 'SI' se l'autenticazione
                                                     utenti avviene via LDAP per
                                                     p_tipo_accesso effettivo,
                                                     'NO' altrimenti.
             p_min_lung_pwd   IN OUT NUMBER     Lunghezza minima della password.
             p_mod_pwd_primo_accesso IN OUT VARCHAR2 Indica se l'utente deve
                                                modificare la password al primo
                                                accesso successivo alla modifica
                                                della stessa da parte di un utente
                                                diverso.
             p_arch_traccia   IN OUT VARCHAR2   attivazione archiviazione traccia.
             p_disl_traccia   IN OUT VARCHAR2   percorso su file system del db
                                                in cui viene salvata la traccia.
             p_car_speciali_pwd IN OUT VARCHAR2 Indica se nella password sono
                                                ammessi caratteri speciali.
             p_num_obb_pwd    IN OUT VARCHAR2   Indica se nella password deve
                                                essere presente almeno un numero.
 ECCEZIONI:   Non possiede eccezioni proprie, ma lancia la procedure
              GET_CARATTERISTICHE_ACCESSO
              che potrebbe risultare in una eccezione.
 ANNOTAZIONI: chiamata da CHECK_ACCESSO.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    01/02/2001 MM     Prima emissione.
 1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
 2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                        DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                        e NUMERI_OBB_PWD.
008   21/09/2018 SN    Aggiunto campo gg_prima_riutilizzo_pwd
******************************************************************************/
   ( p_tipo_accesso IN OUT VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2
   , p_accesso IN OUT VARCHAR2
   , p_accesso_se IN OUT VARCHAR2
   , p_traccia IN OUT VARCHAR2
   , p_giorni_traccia IN OUT NUMBER
   , p_tentativi_pwd IN OUT NUMBER
   , p_val_pwd IN OUT NUMBER
   , p_sleep IN OUT NUMBER
   , p_single_sign_on IN OUT VARCHAR2
   , p_ldap IN OUT VARCHAR2
   , p_min_lung_pwd IN OUT NUMBER
   , p_mod_pwd_primo_accesso IN OUT VARCHAR2
   , p_arch_traccia IN OUT VARCHAR2
   , p_disl_traccia IN OUT VARCHAR2
   , p_car_speciali_pwd IN OUT VARCHAR2
   , p_num_obb_pwd IN OUT VARCHAR2
   , p_giorni_prima_riutilizzo_pwd  IN OUT NUMBER)
   IS /* SLAVE_COPY */
      d_dep_tiac            VARCHAR2(1);
      d_tipo_accesso        VARCHAR2(1);
      d_super_utente        VARCHAR2(2);
      d_accesso             VARCHAR2(1);
      d_accesso_se          VARCHAR2(2);
      d_traccia             VARCHAR2(1);
      d_giorni_traccia      NUMBER(3);
      d_giorni_traccia_old  NUMBER(3);
      d_tentativi_pwd       NUMBER(2);
      d_val_pwd             NUMBER(3);
      d_sleep               NUMBER(3);
      d_single_sign_on      VARCHAR2(2);
      d_ldap                VARCHAR2(2);
      d_min_lung_pwd        NUMBER(2);
      d_mod_pwd_primo_uso VARCHAR2(2);
      d_arch_traccia      VARCHAR2(2);
      d_disl_traccia      VARCHAR2(100);
      d_arch_traccia_dep  VARCHAR2(2);
      d_disl_traccia_dep  VARCHAR2(100);
      d_car_speciali_pwd  VARCHAR2(2);
      d_num_obb_pwd       VARCHAR2(2);
      TYPE TipoAccTab IS VARRAY(5) OF varchar2(1);-- INDEX BY BINARY_INTEGER;
      TabTipoAcc        TipoAccTab := TipoAccTab(null, null, null, null, null);
      indice            BINARY_INTEGER := 0;
      d_gg_prima_riutilizzo_pwd  CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
   BEGIN
      d_tipo_accesso       := LTRIM(p_tipo_accesso);
      FOR c_caac in (select DISTINCT tipo_accesso
                       from caratteristiche_accesso
                      where progetto = p_progetto
                    )
      LOOP
         INTEGRITYPACKAGE.LOG('PROGETTO: '||P_PROGETTO);
         d_super_utente       := 'NO';
         d_accesso            := LTRIM(p_accesso);
         d_accesso_se         := LTRIM(p_accesso_se);
         d_traccia            := LTRIM(p_traccia);
         d_giorni_traccia     := p_giorni_traccia;
         d_giorni_traccia_old := p_giorni_traccia;
         d_tentativi_pwd      := p_tentativi_pwd;
         d_val_pwd            := p_val_pwd;
         d_sleep              := p_sleep;
         d_single_sign_on     := NVL(p_single_sign_on,'SI');
         d_ldap               := NVL(p_ldap,'NO');
         d_min_lung_pwd       := p_min_lung_pwd;
         d_mod_pwd_primo_uso  := NVL(p_mod_pwd_primo_accesso,'NO');
         d_arch_traccia       := NVL(p_arch_traccia,'NO');
         d_disl_traccia       := p_disl_traccia;
         d_car_speciali_pwd   := NVL(p_car_speciali_pwd,'SI');
         d_num_obb_pwd        := NVL(p_num_obb_pwd,'NO');
         d_gg_prima_riutilizzo_pwd := p_giorni_prima_riutilizzo_pwd;
         if c_caac.tipo_accesso = 'P'
         then
            indice := 1;
         elsif c_caac.tipo_accesso = 'I'
         then
            indice := 2;
         elsif c_caac.tipo_accesso = 'M'
         then
            indice := 3;
         elsif c_caac.tipo_accesso = 'G'
         then
            indice := 4;
         elsif c_caac.tipo_accesso = 'D'
         then
            indice := 5;
         end if;
         TabTipoAcc(indice) := c_caac.tipo_accesso;
      END LOOP;
INTEGRITYPACKAGE.LOG('ESAMINO '||D_tipo_accesso);
      IF d_tipo_accesso in ('D') and TabTipoAcc(5) = 'D'
      or d_tipo_accesso in ('G') and TabTipoAcc(4) = 'G'
      THEN
         -- Controllo SUPER USER.
INTEGRITYPACKAGE.LOG('d_ACCESSO_SE '||d_ACCESSO_SE);
         p_tipo_accesso := d_tipo_accesso;
         GET( p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente
            , d_accesso, d_super_utente, d_traccia, d_giorni_traccia
            , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
            , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_uso, d_arch_traccia_dep
            , d_disl_traccia_dep, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
INTEGRITYPACKAGE.LOG('d_super_utente '||d_super_utente);
INTEGRITYPACKAGE.LOG('d_ACCESSO_SE '||d_ACCESSO_SE);
      END IF;
      -- Leggo caratteristiche del PROGETTO.
      IF TabTipoAcc(1) = 'P'
      THEN
         p_tipo_accesso := 'P';
   INTEGRITYPACKAGE.LOG('LEGGO '||p_tipo_accesso);
         GET ( p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente
             , d_accesso, d_accesso_se, d_traccia, d_giorni_traccia
             , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
             , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_uso, d_arch_traccia
             , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
         IF p_tipo_accesso IS NOT NULL THEN
            d_dep_tiac := p_tipo_accesso;
   INTEGRITYPACKAGE.LOG('SCRIVO '||p_tipo_accesso);
         END IF;
      END IF;
      IF (NVL(d_accesso_se,'NO') = 'NO' OR NVL(d_super_utente,'NO') = 'SI')
      AND d_tipo_accesso <> 'P' THEN
         IF TabTipoAcc(2) = 'I'
         THEN
            -- Se le caratteristiche del progetto prevedono eccezioni oppure Super User,
            -- leggo caratteristiche dell' ISTANZA.
            p_tipo_accesso := 'I';
   INTEGRITYPACKAGE.LOG('LEGGO '||p_tipo_accesso);
            GET ( p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente
                , d_accesso, d_accesso_se, d_traccia, d_giorni_traccia
                , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
                , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_uso, d_arch_traccia
                , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
            IF p_tipo_accesso IS NOT NULL THEN
               d_dep_tiac := p_tipo_accesso;
   INTEGRITYPACKAGE.LOG('SCRIVO '||p_tipo_accesso);
            END IF;
            -- Memorizza il numero di giorni_traccia previsti per Progetto / Istanza
            d_giorni_traccia_old := d_giorni_traccia;
         END IF;
      END IF;
      IF TabTipoAcc(3) = 'M'
      THEN
         IF (NVL(d_accesso_se,'NO') = 'NO' OR NVL(d_super_utente,'NO') = 'SI')
         AND d_tipo_accesso <> 'I' THEN
            -- Se le caratteristiche dell' istanza prevedono eccezioni oppure Super User,
            -- leggo caratteristiche del MODULO.
            p_tipo_accesso := 'M';
INTEGRITYPACKAGE.LOG('LEGGO '||p_tipo_accesso);
            GET ( p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente
                , d_accesso, d_accesso_se, d_traccia, d_giorni_traccia
                , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
                , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_uso, d_arch_traccia_dep
                , d_disl_traccia_dep, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
            IF NVL(d_arch_traccia, 'NO') = 'SI' AND d_disl_traccia IS NOT NULL THEN
               IF d_giorni_traccia IS NULL THEN
                  d_giorni_traccia := d_giorni_traccia_old;
               ELSE
                  d_giorni_traccia_old := d_giorni_traccia;
               END IF;
            END IF;
            IF p_tipo_accesso IS NOT NULL THEN
               d_dep_tiac := p_tipo_accesso;
  INTEGRITYPACKAGE.LOG('SCRIVO '||p_tipo_accesso||' '||D_LDAP);
            END IF;
         END IF;
      END IF;
      IF TabTipoAcc(4) = 'G'
      THEN
         IF (NVL(d_accesso_se,'NO') = 'NO' OR NVL(d_super_utente,'NO') = 'SI')
         AND d_tipo_accesso = 'G' THEN
            -- Se le caratteristiche del modulo prevedono eccezioni oppure Super User,
            -- leggo caratteristiche del GRUPPO.
            p_tipo_accesso := 'G';
INTEGRITYPACKAGE.LOG('LEGGO '||p_tipo_accesso);
            GET ( p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente
                , d_accesso, d_super_utente, d_traccia, d_giorni_traccia
                , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
                , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_uso, d_arch_traccia_dep
                , d_disl_traccia_dep, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
            IF NVL(d_arch_traccia, 'NO') = 'SI' AND d_disl_traccia IS NOT NULL THEN
               IF d_giorni_traccia IS NULL THEN
                  d_giorni_traccia := d_giorni_traccia_old;
               ELSE
                  d_giorni_traccia_old := d_giorni_traccia;
               END IF;
            END IF;
            IF p_tipo_accesso IS NOT NULL THEN
               d_dep_tiac := p_tipo_accesso;
INTEGRITYPACKAGE.LOG('SCRIVO '||p_tipo_accesso);
            END IF;
         END IF;
      END IF;
      IF TabTipoAcc(5) = 'D'
      THEN
         IF (NVL(d_accesso_se,'NO') = 'NO' OR NVL(d_super_utente,'NO') = 'SI')
         AND d_tipo_accesso = 'D' THEN
            -- Se le caratteristiche del modulo prevedono eccezioni oppure Super User,
            -- leggo caratteristiche dell' UTENTE.
            p_tipo_accesso := 'D';
INTEGRITYPACKAGE.LOG('LEGGO '||p_tipo_accesso);
            GET ( p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente
                , d_accesso, d_super_utente, d_traccia, d_giorni_traccia
                , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
                , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_uso, d_arch_traccia_dep
                , d_disl_traccia_dep, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
            IF NVL(d_arch_traccia, 'NO') = 'SI' AND d_disl_traccia IS NOT NULL THEN
               IF d_giorni_traccia IS NULL THEN
                  d_giorni_traccia := d_giorni_traccia_old;
               ELSE
                  d_giorni_traccia_old := d_giorni_traccia;
               END IF;
            END IF;
            IF p_tipo_accesso IS NOT NULL THEN
               d_dep_tiac := p_tipo_accesso;
INTEGRITYPACKAGE.LOG('SCRIVO '||p_tipo_accesso);
            END IF;
         end if;
      END IF;
      p_tipo_accesso          := d_dep_tiac;
      p_accesso               := d_accesso;
      p_accesso_se            := d_accesso_se;
      p_traccia               := d_traccia;
      p_giorni_traccia        := d_giorni_traccia;
      p_tentativi_pwd         := d_tentativi_pwd;
      p_val_pwd               := d_val_pwd;
      p_sleep                 := d_sleep;
      p_single_sign_on        := d_single_sign_on;
      p_ldap                  := d_ldap;
      p_min_lung_pwd          := d_min_lung_pwd;
      p_mod_pwd_primo_accesso := d_mod_pwd_primo_uso;
      p_arch_traccia          := d_arch_traccia;
      p_disl_traccia          := d_disl_traccia;
      p_car_speciali_pwd      := d_car_speciali_pwd;
      p_num_obb_pwd           := d_num_obb_pwd;
      p_giorni_prima_riutilizzo_pwd := d_gg_prima_riutilizzo_pwd;
      INTEGRITYPACKAGE.LOG('ESCE '||p_tipo_accesso);
   END GET_EFFETTIVE;
   PROCEDURE GET_EFFETTIVE_DIAC
/******************************************************************************
 NOME:        GET_EFFETTIVE_DIAC
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista la caratteristica.
 PARAMETRI:   p_utente          varchar2 codice utente da verificare.
              p_caratteristica  varchar2 caratteristica da verificare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' prevista
                la caratteristica,
              0 altrimenti.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  2    04/01/2006 MM     Creazione.
  006  24/10/2013 SN    Validita password valutata su tutti i diritti di
                      accesso esistenti.

  008  21/09/2018 SN    Aggiunto campo gg_prima_riutilizzo_pwd
  011  11/01/2019 SNeg In caso di utenza ldap aggiornare pwd sempre = NO
******************************************************************************/
   ( p_utente IN VARCHAR2
   , p_ldap IN OUT VARCHAR2
   , p_min_lung_pwd IN OUT NUMBER
   , p_pwd_da_mod_primo_uso IN OUT VARCHAR2
   , p_car_speciali_pwd IN OUT VARCHAR2
   , p_num_obb_pwd IN OUT VARCHAR2
   , p_val_pwd IN OUT VARCHAR2
   , p_gg_prima_riutilizzo_pwd IN OUT NUMBER)
   IS /* SLAVE_COPY */
      d_tipo_accesso          VARCHAR2(1) := 'D';
      d_accesso               VARCHAR2(1);
      d_accesso_se            VARCHAR2(2);
      d_traccia               VARCHAR2(1);
      d_giorni_traccia        NUMBER(3);
      d_tentativi_pwd         NUMBER(2);
      d_val_pwd               NUMBER(3);
      d_sleep                 NUMBER(3);
      d_single_sign_on        VARCHAR2(2);
      d_ldap                  VARCHAR2(2);
      d_min_lung_pwd          NUMBER(2);
      d_mod_pwd_primo_accesso VARCHAR2(2);
      d_arch_traccia          VARCHAR2(2);
      d_disl_traccia          VARCHAR2(100);
      d_car_speciali_pwd      VARCHAR2(2);
      d_num_obb_pwd           VARCHAR2(2);
      d_gg_prima_riutilizzo_pwd  CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
   BEGIN
      FOR DIAC IN (SELECT ista.progetto, DIAC.Istanza, DIAC.modulo, DIAC.Utente
                     FROM ISTANZE ista, DIRITTI_ACCESSO DIAC
                    WHERE ista.Istanza = DIAC.Istanza
                      AND DIAC.Utente  = p_utente)
      LOOP
         IF UTENTE.GET_TIPO_UTENTE(P_UTENTE) = 'U' THEN
            d_tipo_accesso := 'D';
         ELSE
            d_tipo_accesso := 'G';
         END IF;
         get_effettive ( d_tipo_accesso, DIAC.progetto, DIAC.Istanza
                       , DIAC.modulo, DIAC.Utente, d_accesso, d_accesso_se
                       , d_traccia, d_giorni_traccia, d_tentativi_pwd
                       , d_val_pwd, d_sleep, d_single_sign_on, d_ldap
                       , d_min_lung_pwd, d_mod_pwd_primo_accesso, d_arch_traccia
                       , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
         IF NVL(d_ldap,'NO') = 'SI' THEN
            p_ldap := d_ldap;
         END IF;
         IF NVL(d_mod_pwd_primo_accesso,'NO') = 'SI'  THEN
         -- rev.11 inizio
           if IS_LDAPUSER(p_utente )
            != 1 then
            p_pwd_da_mod_primo_uso := d_mod_pwd_primo_accesso;
           else
            p_pwd_da_mod_primo_uso := 'NO';
           end if;
         -- rev.11 fine
         END IF;
         IF NVL(d_car_speciali_pwd,'SI') = 'NO'THEN
            p_car_speciali_pwd := d_car_speciali_pwd;
         END IF;
         IF NVL(d_num_obb_pwd,'NO') = 'SI' THEN
            p_num_obb_pwd := d_num_obb_pwd;
         END IF;
         IF NVL(d_min_lung_pwd,0) > NVL(p_min_lung_pwd,0) THEN
            p_min_lung_pwd := d_min_lung_pwd;
         END IF;
         -- Modifica Stefania x calcolo val password 24/10/2013
         IF d_val_pwd is not null and d_val_pwd < NVL(p_val_pwd,100000) THEN
            p_val_pwd := d_val_pwd;
         END IF;
         -- FINE - Modifica Stefania x calcolo lung password 24/10/2013
         -- rev.8 inizio
         IF NVL(d_gg_prima_riutilizzo_pwd,0) > NVL(p_gg_prima_riutilizzo_pwd,0) THEN
            p_gg_prima_riutilizzo_pwd := d_gg_prima_riutilizzo_pwd;
         END IF;
         -- rev.8 fine
      END LOOP;
   END GET_EFFETTIVE_DIAC;
   FUNCTION GET_DESC
/******************************************************************************
 NOME:        GET_DESC.
 DESCRIZIONE: restituisce la descrizione delle caratteristiche effettive di
              accesso al Progetto / Istanza / Modulo / Utente.
 PARAMETRI:   p_tipo_accesso   IN     VARCHAR2    tipo di caratteristiche di
                                                  accesso (P / I / M / D).
              p_progetto       IN     VARCHAR2    progetto a cui si accede.
              p_istanza        IN     VARCHAR2    istanza a cui si accede.
              p_modulo         IN     VARCHAR2    modulo a cui si accede.
              p_utente         IN     VARCHAR2    utente che accede.
 RITORNA:     varchar2 stringa descrittiva delle caratteristiche di accesso
                      richieste.
 ECCEZIONI:   20999, Errore Bloccante: 'Almeno il codice del progetto deve
                                        essere significativo',
                                       'Tipo di accesso non gestito.'
 NOTE:        --.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
 2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                        DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                        e NUMERI_OBB_PWD.
******************************************************************************/
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
     d_return              VARCHAR2(32000);
   BEGIN
      d_return := get_desc_accesso(p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente)||CHR(13)||CHR(10)||CHR(13)||CHR(10)||
                 get_desc_password(p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente)||CHR(13)||CHR(10)||CHR(13)||CHR(10)||
              get_desc_autenticazione(p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente);
      RETURN d_return;
   END GET_DESC;
   FUNCTION GET_DESC_ACCESSO
/******************************************************************************
 NOME:        GET_DESC_ACCESSO.
 DESCRIZIONE: restituisce la descrizione delle caratteristiche effettive di
              accesso al Progetto / Istanza / Modulo / Utente.
 PARAMETRI:   p_tipo_accesso   IN     VARCHAR2    tipo di caratteristiche di
                                                  accesso (P / I / M / D).
              p_progetto       IN     VARCHAR2    progetto a cui si accede.
              p_istanza        IN     VARCHAR2    istanza a cui si accede.
              p_modulo         IN     VARCHAR2    modulo a cui si accede.
              p_utente         IN     VARCHAR2    utente che accede.
 RITORNA:     varchar2 stringa descrittiva delle caratteristiche di accesso
                      richieste.
 ECCEZIONI:   20999, Errore Bloccante: 'Almeno il codice del progetto deve
                                        essere significativo',
                                       'Tipo di accesso non gestito.'
 NOTE:        --.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    30/05/2005 MM      Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
 2    01/12/2005 MM      Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                         DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                         e NUMERI_OBB_PWD.
******************************************************************************/
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_tipo_accesso        VARCHAR2(1);
      d_accesso             VARCHAR2(1);
      d_accesso_se          VARCHAR2(2);
      d_traccia             VARCHAR2(1);
      d_giorni_traccia      NUMBER(3);
      d_tentativi_pwd       NUMBER(2);
      d_val_pwd             NUMBER(3);
      d_sleep               NUMBER(3);
      d_single_sign_on      VARCHAR2(2);
      d_ldap                VARCHAR2(2);
      d_min_lung_pwd        NUMBER(2);
      d_mod_pwd_primo_uso   VARCHAR2(2);
      d_arch_traccia        VARCHAR2(2);
      d_disl_traccia        VARCHAR2(100);
      d_car_speciali_pwd    VARCHAR2(2);
      d_num_obb_pwd         VARCHAR2(2);
      d_accesso_desc        VARCHAR2(100);
      d_accesso_se_desc     VARCHAR2(100);
      d_traccia_desc        VARCHAR2(100);
      d_arch_traccia_desc   VARCHAR2(100);
      d_sleep_desc          VARCHAR2(100);
      d_return              VARCHAR2(2000);
      d_gg_prima_riutilizzo_pwd  CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
   BEGIN
      d_tipo_accesso := p_tipo_accesso;
     BEGIN
         GET_EFFETTIVE(d_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente
                      , d_accesso, d_accesso_se, d_traccia, d_giorni_traccia
                      , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
                      , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_uso, d_arch_traccia
                      , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
      EXCEPTION WHEN OTHERS THEN
         RETURN '';
      END;
      IF NVL(d_accesso,'L') = 'I' THEN
         d_accesso_desc := 'Inibito';
      ELSIF NVL(d_accesso,'L') = 'L' THEN
         d_accesso_desc := 'Libero';
      ELSIF NVL(d_accesso,'L') = 'S' THEN
         d_accesso_desc := 'Segnalato';
      ELSIF NVL(d_accesso,'L') = 'U' THEN
         d_accesso_desc := 'Unico';
      END IF;
      IF NVL(d_Traccia,'M') = 'F' THEN
         d_Traccia_desc := 'alle singole Funzioni';
      ELSIF NVL(d_Traccia,'M') = 'M' THEN
         d_Traccia_desc := 'al Modulo';
      ELSIF NVL(d_Traccia,'M') = 'I' THEN
         d_Traccia_desc := 'alle singole Istruzioni';
      END IF;
      d_arch_traccia_desc := 'archiviazione traccia ';
     IF NVL(d_arch_traccia,'NO') = 'NO' THEN
         d_arch_traccia_desc := d_arch_traccia_desc||'non prevista';
      ELSE
         d_arch_traccia_desc := d_arch_traccia_desc||' prevista e memorizzata sul database nella cartella '||d_disl_traccia;
      END IF;
      IF NVL(d_Accesso_SE,'NO') = 'SI' THEN
         IF d_tipo_accesso = 'P' THEN
            d_Accesso_SE_desc := 'Le Caratteristiche di accesso al Progetto NON prevedono eccezioni.';
         ELSIF d_tipo_accesso = 'I' THEN
            d_Accesso_SE_desc := 'Le Caratteristiche di accesso all''Istanza NON prevedono eccezioni.';
         ELSIF d_tipo_accesso = 'M' THEN
            d_Accesso_SE_desc := 'Le Caratteristiche di accesso al Modulo NON prevedono eccezioni.';
         ELSIF d_tipo_accesso IN ('D','G') THEN
            d_Accesso_SE_desc := 'Super Utente.';
         END IF;
       d_Accesso_SE_desc := d_Accesso_SE_desc||CHR(13)||CHR(10);
      ELSE
         d_Accesso_SE_desc := '';
      END IF;
      IF NVL(d_sleep,-1) = -1 THEN
         d_sleep_desc := 'illimitati';
      ELSE
         d_sleep_desc := TO_CHAR(d_sleep);
      END IF;
      d_return := d_Accesso_SE_desc||
                'Controllo '||d_Accesso_desc||'.'||CHR(13)||CHR(10)||
                 'Traccia dell''accesso '||d_Traccia_desc ||
                 ' conservata per giorni '||TO_CHAR(NVL(d_Giorni_Traccia,60))||'; '||
                 d_arch_traccia_desc||'.'||CHR(13)||CHR(10)||
                 'Minuti di inattivita dopo cui e necessario rieffettuare la connessione: '||d_sleep_desc||'.';
      RETURN d_return;
   END GET_DESC_ACCESSO;
   FUNCTION GET_DESC_PASSWORD
/******************************************************************************
 NOME:        GET_DESC_PASSWORD
 DESCRIZIONE: restituisce la descrizione delle caratteristiche effettive di
              accesso al Progetto / Istanza / Modulo / Utente.
 PARAMETRI:   p_tipo_accesso   IN     VARCHAR2    tipo di caratteristiche di
                                                  accesso (P / I / M / D).
              p_progetto       IN     VARCHAR2    progetto a cui si accede.
              p_istanza        IN     VARCHAR2    istanza a cui si accede.
              p_modulo         IN     VARCHAR2    modulo a cui si accede.
              p_utente         IN     VARCHAR2    utente che accede.
 RITORNA:     varchar2 stringa descrittiva delle caratteristiche di accesso
                      richieste.
 ECCEZIONI:   20999, Errore Bloccante: 'Almeno il codice del progetto deve
                                        essere significativo',
                                       'Tipo di accesso non gestito.'
 NOTE:        --.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
 2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                        DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                        e NUMERI_OBB_PWD.
 9    16/10/2018 SN    Aggiunto campo gg_prima_riutilizzo_pwd nella get_desc_password
 014  30/03/2021 SN   Inserire le informazioni relative al numero tentativi relativo alla password #49220
******************************************************************************/
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_tipo_accesso        VARCHAR2(1);
      d_accesso             VARCHAR2(1);
      d_accesso_se          VARCHAR2(2);
      d_traccia             VARCHAR2(1);
      d_giorni_traccia      NUMBER(3);
      d_tentativi_pwd       NUMBER(2);
      d_val_pwd             NUMBER(3);
      d_sleep               NUMBER(3);
      d_single_sign_on      VARCHAR2(2);
      d_ldap                VARCHAR2(2);
      d_min_lung_pwd        NUMBER(2);
      d_mod_pwd_primo_uso   VARCHAR2(2);
      d_arch_traccia        VARCHAR2(2);
      d_disl_traccia        VARCHAR2(100);
      d_car_speciali_pwd    VARCHAR2(2);
      d_num_obb_pwd         VARCHAR2(2);
      d_tentativi_pwd_desc  VARCHAR2(100);
      d_val_pwd_desc        VARCHAR2(1000);
     d_return              VARCHAR2(2000);
     d_gg_prima_riutilizzo_pwd  varchar2(20); -- CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE; ci finisce una descrizione
   BEGIN
      d_tipo_accesso := p_tipo_accesso;
     BEGIN
         GET_EFFETTIVE(d_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente
                      , d_accesso, d_accesso_se, d_traccia, d_giorni_traccia
                      , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
                      , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_uso, d_arch_traccia
                      , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
         IF p_tipo_accesso = 'D' THEN
            GET_EFFETTIVE_DIAC(p_utente, d_ldap, d_min_lung_pwd, d_mod_pwd_primo_uso, d_car_speciali_pwd, d_num_obb_pwd, d_val_pwd, d_gg_prima_riutilizzo_pwd);
            d_tentativi_pwd := GET_MIN_MAX_TENTATIVI_PWD(p_utente, utente.get_tipo_utente(p_utente)); -- rev.014
         END IF;
      EXCEPTION WHEN OTHERS THEN
         RETURN '';
      END;
      IF NVL(d_Val_Pwd,-1) = -1 THEN
         d_val_pwd_desc := 'Password sempre valida';
      ELSIF NVL(d_val_pwd,-1) = 0 THEN
         d_val_pwd_desc := 'Password mai valida';
      ELSE
         d_val_pwd_desc := 'Password valida per '||d_val_pwd||' giorni';
      END IF;
      IF NVL(d_min_lung_pwd,0) = 0 THEN
         d_val_pwd_desc := d_val_pwd_desc||' e lunga a piacere;';
      ELSE
         d_val_pwd_desc := d_val_pwd_desc||' e lunga almeno '||d_min_lung_pwd||' caratteri;';
      END IF;
      d_val_pwd_desc := d_val_pwd_desc||CHR(13)||CHR(10);
      IF NVL(d_car_speciali_pwd,'SI') = 'NO' THEN
         d_val_pwd_desc := d_val_pwd_desc||'non ';
      END IF;
     d_val_pwd_desc := d_val_pwd_desc||'sono ammessi caratteri speciali nella password e ';
      IF NVL(d_num_obb_pwd,'NO') = 'NO' THEN
         d_val_pwd_desc := d_val_pwd_desc||'non ';
      END IF;
     d_val_pwd_desc := d_val_pwd_desc||'e'' obbligatorio che nella password sia presente almeno un numero;'||CHR(13)||CHR(10);
      IF NVL(d_mod_pwd_primo_uso,'NO') = 'NO' THEN
         d_val_pwd_desc := d_val_pwd_desc||'non ';
      END IF;
      d_val_pwd_desc := d_val_pwd_desc||'e'' prevista la modifica della password al primo accesso da parte dell''utente';
      IF NVL(d_tentativi_pwd,-1) = -1 THEN
         d_tentativi_pwd_desc := 'illimitati';
      ELSE
         d_tentativi_pwd_desc := TO_CHAR(d_tentativi_pwd);
      END IF;
      d_val_pwd_desc := d_val_pwd_desc||'.'||CHR(13)||CHR(10)||
                 'Tentativi accesso permessi: '||d_tentativi_pwd_desc||'.';
      -- Rev. 9 Inizio
      d_val_pwd_desc := d_val_pwd_desc||CHR(13)||CHR(10)||'Prima di riutilizzare una password devono passare giorni: ' ;
      IF NVL(d_gg_prima_riutilizzo_pwd,-1) = -1 THEN
         d_gg_prima_riutilizzo_pwd := 'illimitati';
      ELSE
         d_gg_prima_riutilizzo_pwd := TO_CHAR(d_gg_prima_riutilizzo_pwd);
      END IF;
      d_val_pwd_desc :=  d_val_pwd_desc||d_gg_prima_riutilizzo_pwd||'.';
      -- Rev. 9 Fine
      d_return := d_val_pwd_desc;
      RETURN d_return;
   END GET_DESC_PASSWORD;
   FUNCTION GET_DESC_AUTENTICAZIONE
/******************************************************************************
 NOME:        GET_DESC_AUTENTICAZIONE.
 DESCRIZIONE: restituisce la descrizione delle caratteristiche effettive di
              accesso al Progetto / Istanza / Modulo / Utente.
 PARAMETRI:   p_tipo_accesso   IN     VARCHAR2    tipo di caratteristiche di
                                                  accesso (P / I / M / D).
              p_progetto       IN     VARCHAR2    progetto a cui si accede.
              p_istanza        IN     VARCHAR2    istanza a cui si accede.
              p_modulo         IN     VARCHAR2    modulo a cui si accede.
              p_utente         IN     VARCHAR2    utente che accede.
 RITORNA:     varchar2 stringa descrittiva delle caratteristiche di accesso
                      richieste.
 ECCEZIONI:   20999, Errore Bloccante: 'Almeno il codice del progetto deve
                                        essere significativo',
                                       'Tipo di accesso non gestito.'
 NOTE:        --.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
 2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                        DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                        e NUMERI_OBB_PWD.
******************************************************************************/
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_tipo_accesso        VARCHAR2(1);
      d_accesso             VARCHAR2(1);
      d_accesso_se          VARCHAR2(2);
      d_traccia             VARCHAR2(1);
      d_giorni_traccia      NUMBER(3);
      d_tentativi_pwd       NUMBER(2);
      d_val_pwd             NUMBER(3);
      d_sleep               NUMBER(3);
      d_single_sign_on      VARCHAR2(2);
      d_ldap                VARCHAR2(2);
      d_min_lung_pwd        NUMBER(2);
      d_mod_pwd_primo_uso VARCHAR2(2);
      d_arch_traccia        VARCHAR2(2);
      d_disl_traccia        VARCHAR2(100);
      d_car_speciali_pwd    VARCHAR2(2);
      d_num_obb_pwd         VARCHAR2(2);
      d_single_sign_on_desc VARCHAR2(100);
      d_ldap_desc           VARCHAR2(100);
     d_return              VARCHAR2(2000);
     d_gg_prima_riutilizzo_pwd  CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
   BEGIN
      d_tipo_accesso := p_tipo_accesso;
     BEGIN
         GET_EFFETTIVE(d_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente
                      , d_accesso, d_accesso_se, d_traccia, d_giorni_traccia
                      , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
                      , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_uso, d_arch_traccia
                      , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
      IF IS_LDAPUSER(p_utente)
       = 1 THEN
         d_ldap := 'SI';
      END IF;
      EXCEPTION WHEN OTHERS THEN
         RETURN '';
      END;
      IF NVL(d_Single_Sign_On,'NO') = 'NO' THEN
         d_single_sign_on_desc := 'NON ';
      END IF;
      d_single_sign_on_desc := d_single_sign_on_desc||'abilitato';
      IF NVL(d_ldap,'NO') = 'SI' THEN
         d_ldap_desc := 'LDAP.';
      ELSE
         d_ldap_desc := 'AD4.';
      END IF;
      d_return := 'Autenticazione tramite '||d_LDap_desc;
      RETURN d_return;
   END GET_DESC_AUTENTICAZIONE;
   FUNCTION GET_DESC_DETTAGLI
/******************************************************************************
 NOME:        GET_DESC_DETTAGLI.
 DESCRIZIONE: restituisce la descrizione dei dettagli delle caratteristiche
              effettive di accesso al Progetto / Istanza / Modulo / Utente.
 PARAMETRI:   p_tipo_accesso   IN     VARCHAR2    tipo di caratteristiche di
                                                  accesso (P / I / M / D).
              p_progetto       IN     VARCHAR2    progetto a cui si accede.
              p_istanza        IN     VARCHAR2    istanza a cui si accede.
              p_modulo         IN     VARCHAR2    modulo a cui si accede.
              p_utente         IN     VARCHAR2    utente che accede.
 RITORNA:     varchar2 stringa descrittiva
 ECCEZIONI:   --
 NOTE:        --.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    03/01/2006 MM     Creazione.
 006  24/10/2013 SN    Validita password valutata su tutti i diritti di
                      accesso esistenti.
 007  04/01/2016 SN    Corretta valutazione degli attributi di accesso
 014 30/03/2021 SN   Inserire le informazioni relative al numero tentativi relativo alla password #49220
******************************************************************************/
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return                 VARCHAR2(32000);
      d_tipo_accesso_eff       VARCHAR2(1);
      d_tipo_accesso        VARCHAR2(1);
      d_accesso             VARCHAR2(1);
      d_accesso_se          VARCHAR2(2);
      d_traccia             VARCHAR2(1);
      d_giorni_traccia      NUMBER(3);
      d_tentativi_pwd       NUMBER(2);
      d_tentativi_pwd_eff   NUMBER(2);
      d_val_pwd             NUMBER(3);
      d_sleep               NUMBER(3);
      d_single_sign_on      VARCHAR2(2);
      d_ldap                VARCHAR2(2);
      d_min_lung_pwd        NUMBER(2);
      d_mod_pwd_primo_uso   VARCHAR2(2);
      d_arch_traccia        VARCHAR2(2);
      d_disl_traccia        VARCHAR2(100);
      d_car_speciali_pwd    VARCHAR2(2);
      d_num_obb_pwd         VARCHAR2(2);
      d_minlungpwd_eff         INTEGER;
      d_ModPwdPrimoAccesso_eff VARCHAR2(2);
      d_LDap_eff               VARCHAR2(2);
      d_car_speciali_pwd_eff   VARCHAR2(2);
      d_num_obb_pwd_eff        VARCHAR2(2);
      d_val_pwd_eff           NUMBER(3);
      d_gg_prima_riutilizzo_pwd  CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
      d_gg_prima_riutilizzo_pwd_eff  CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
   BEGIN
      d_tipo_accesso_eff := GET('tipo_accesso', p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente);
      IF p_tipo_accesso <> d_tipo_accesso_eff THEN
         d_return := 'Le caratteristiche di accesso sono ereditate dal';
         IF d_tipo_accesso_eff = 'P' THEN
            d_return := d_return||' PROGETTO.'||CHR(13)||CHR(10);
         ELSIF d_tipo_accesso_eff = 'I' THEN
            d_return := d_return||'l'' ISTANZA.'||CHR(13)||CHR(10);
         ELSIF d_tipo_accesso_eff = 'M' THEN
            d_return := d_return||' MODULO.'||CHR(13)||CHR(10);
         ELSIF d_tipo_accesso_eff = 'G' THEN
            d_return := d_return||' GRUPPO.'||CHR(13)||CHR(10);
         END IF;
      END IF;
      IF p_tipo_accesso = 'D' THEN
         d_tipo_accesso := 'D';
         GET( d_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente
            , d_accesso, d_accesso_se, d_traccia, d_giorni_traccia
            , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
            , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_uso, d_arch_traccia
            , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
         GET_EFFETTIVE_DIAC(p_utente, d_LDap_eff, d_minlungpwd_eff, d_ModPwdPrimoAccesso_eff, d_car_speciali_pwd_eff, d_num_obb_pwd_eff,d_val_pwd_eff, d_gg_prima_riutilizzo_pwd_eff);
         d_tentativi_pwd_eff := GET_MIN_MAX_TENTATIVI_PWD(p_utente, utente.get_tipo_utente(p_utente));
         IF NVL(d_min_lung_pwd,0) <> NVL(d_minlungpwd_eff,0) THEN
            d_return := d_return||CHR(13)||CHR(10)||'La LUNGHEZZA minima della PASSWORD dipende dall''appartenenza dell''utente a:';
            d_return := d_return||CHR(13)||CHR(10)||get_diac_max_minpwdlength(p_utente);
         END IF;
         IF NVL(d_mod_pwd_primo_uso,'NO') <> NVL(d_ModPwdPrimoAccesso_eff,'NO') THEN
            d_return := d_return||CHR(13)||CHR(10)||'La necessita di MODIFICARE la PASSWORD al PRIMO ACCESSO dipende dall''appartenenza dell''utente a:';
            d_return := d_return||CHR(13)||CHR(10)||get_diac_ispwddamod(p_utente);
         END IF;
         IF NVL(d_car_speciali_pwd,'SI') <> NVL(d_car_speciali_pwd_eff,'SI') THEN
            d_return := d_return||CHR(13)||CHR(10)||'L''impossibilita'' di utilizzare CARATTERI SPECIALI nella PASSWORD dipende dall''appartenenza dell''utente a:';
            d_return := d_return||CHR(13)||CHR(10)||GET_DIAC_IS_CAR_SPECIALI_PWD(p_utente);
         END IF;
         IF NVL(d_num_obb_pwd,'NO') <> NVL(d_num_obb_pwd_eff,'NO') THEN
            d_return := d_return||CHR(13)||CHR(10)||'L''obbligatorieta'' che la PASSWORD contenga ALMENO UN NUMERO dipende dall''appartenenza dell''utente a:';
            d_return := d_return||CHR(13)||CHR(10)||GET_DIAC_IS_NUM_OBB_PWD(p_utente);
         END IF;
         d_LDap := GET('ldap', p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente);
         IF NVL(d_LDap,'NO') <> NVL(d_LDap_eff,'NO') THEN
            d_return := d_return||CHR(13)||CHR(10)||'L''AUTENTICAZIONE tramite LDAP dipende dall''appartenenza dell''utente a:';
            d_return := d_return||CHR(13)||CHR(10)||get_diac_isldapuser(p_utente);
         END IF;
         --006 e 007 modifica
         IF NVL(d_val_pwd,0) <> NVL(d_val_pwd_eff,0) THEN
            d_return := d_return||CHR(13)||CHR(10)||'La VALIDITA massima della PASSWORD dipende dall''appartenenza dell''utente a:';
            d_return := d_return||CHR(13)||CHR(10)|| GET_DIAC_VAL_PWD(p_utente);
         END IF;
         -- rev. 014 inizio
         IF NVL(d_tentativi_pwd,0) <> NVL(d_tentativi_pwd_eff,0) THEN
            d_return := d_return||CHR(13)||CHR(10)||'Il numero dei TENTATIVI di accesso dipende dall''appartenenza dell''utente a:';
            d_return := d_return||CHR(13)||CHR(10)|| GET_DIAC_TENTATIVI_PWD(p_utente);
         END IF;
         -- rev. 014 fine
         --008
         IF NVL(d_gg_prima_riutilizzo_pwd_eff,0) <> NVL(d_gg_prima_riutilizzo_pwd,0) THEN
            d_return := d_return||CHR(13)||CHR(10)||'I giorni MINIMI di riutilizzo della PASSWORD dipendono dall''appartenenza dell''utente a:';
            d_return := d_return||CHR(13)||CHR(10)|| GET_DIAC_MAX_GG_RIUSO_PWD(p_utente);
         END IF;
      END IF;
      RETURN d_return;
   END GET_DESC_DETTAGLI;
   FUNCTION GET
/******************************************************************************
 NOME:        GET
 DESCRIZIONE: .
 PARAMETRI:   .
 RITORNA:     .
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
  2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                         DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                         e NUMERI_OBB_PWD.
******************************************************************************/
   ( p_caratteristica VARCHAR2
   , p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2 DEFAULT NULL
   , p_modulo VARCHAR2 DEFAULT NULL
   , p_utente VARCHAR2 DEFAULT NULL)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return                VARCHAR2(32000);
      d_tipo_accesso          VARCHAR2(1);
      d_accesso               VARCHAR2(1);
      d_accesso_se            VARCHAR2(2);
      d_traccia               VARCHAR2(1);
      d_giorni_traccia        NUMBER(3);
      d_tentativi_pwd         NUMBER(2);
      d_val_pwd               NUMBER(3);
      d_sleep                 NUMBER(3);
      d_single_sign_on        VARCHAR2(2);
      d_ldap                  VARCHAR2(2);
      d_min_lung_pwd          NUMBER(2);
      d_mod_pwd_primo_accesso VARCHAR2(2);
      d_arch_traccia        VARCHAR2(2);
      d_disl_traccia        VARCHAR2(100);
      d_car_speciali_pwd      VARCHAR2(2);
      d_num_obb_pwd           VARCHAR2(2);
      d_gg_prima_riutilizzo_pwd  CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
   BEGIN
      IF p_tipo_accesso = 'G' THEN
         d_tipo_accesso := p_tipo_accesso;
      ELSE
         d_tipo_accesso := 'D';
      END IF;
      get_effettive( d_tipo_accesso, p_progetto, p_istanza
                   , p_modulo, p_utente, d_accesso, d_accesso_se
                   , d_traccia, d_giorni_traccia, d_tentativi_pwd
                   , d_val_pwd, d_sleep, d_single_sign_on, d_ldap
                   , d_min_lung_pwd, d_mod_pwd_primo_accesso
                   , d_arch_traccia, d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
      IF LOWER(p_caratteristica) = 'tipo_accesso' THEN
         d_return := d_tipo_accesso;
      ELSIF LOWER(p_caratteristica) = 'accesso' THEN
         d_return := d_accesso;
      ELSIF LOWER(p_caratteristica) = 'accesso_se' THEN
         d_return := d_accesso_se;
      ELSIF LOWER(p_caratteristica) = 'traccia' THEN
         d_return := d_traccia;
      ELSIF LOWER(p_caratteristica) = 'giorni_traccia' THEN
         d_return := d_giorni_traccia;
      ELSIF LOWER(p_caratteristica) = 'tentativi_password' THEN
         d_return := d_tentativi_pwd;
      ELSIF LOWER(p_caratteristica) = 'validita_password' THEN
         d_return := d_val_pwd;
      ELSIF LOWER(p_caratteristica) = 'single_sign_on' THEN
         d_return := d_single_sign_on;
      ELSIF LOWER(p_caratteristica) = 'sleep' THEN
         d_return := d_sleep;
      ELSIF LOWER(p_caratteristica) = 'ldap' THEN
         d_return := d_ldap;
      ELSIF LOWER(p_caratteristica) = 'min_lunghezza_pwd' THEN
         d_return := d_min_lung_pwd;
      ELSIF LOWER(p_caratteristica) = 'mod_pwd_primo_accesso' THEN
         d_return := d_mod_pwd_primo_accesso;
      ELSIF LOWER(p_caratteristica) = 'archiviazione_traccia' THEN
         d_return := d_arch_traccia;
      ELSIF LOWER(p_caratteristica) = 'dislocazione_traccia' THEN
         d_return := d_disl_traccia;
      ELSIF LOWER(p_caratteristica) = 'ammessi_car_speciali_pwd' THEN
         d_return := d_car_speciali_pwd;
      ELSIF LOWER(p_caratteristica) = 'numeri_obb_pwd' THEN
         d_return := d_num_obb_pwd;
     ELSE
       d_return := '';
     END IF;
    RETURN d_return;
   END GET;
   FUNCTION GET_VAL_PWD
/******************************************************************************
 NOME:        GET_VAL_PWD
 DESCRIZIONE: .
 PARAMETRI:   .
 RITORNA:     .
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    12/04/2005 MM     Creazione.
******************************************************************************/
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2)
   RETURN NUMBER
   IS /* SLAVE_COPY */
   BEGIN
      RETURN TO_NUMBER(GET_MAXVALPWD(p_utente, p_tipo_accesso));
      --RETURN TO_NUMBER(GET('validita_password',p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente));
   END GET_VAL_PWD;
   FUNCTION GET_TENTATIVI_PWD
/******************************************************************************
 NOME:        GET_TENTATIVI_PWD
 DESCRIZIONE: .
 PARAMETRI:   .
 RITORNA:     .
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    27/04/2005 MM     Creazione.
******************************************************************************/
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2)
   RETURN NUMBER
   IS /* SLAVE_COPY */
   BEGIN
      RETURN TO_NUMBER(GET('tentativi_password',p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente));
   END GET_TENTATIVI_PWD;
FUNCTION GET_ACCESSO
/******************************************************************************
 NOME:        GET_ACCESSO
 DESCRIZIONE: .
 PARAMETRI:   .
 RITORNA:     .
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    27/04/2005 MM     Creazione.
******************************************************************************/
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   BEGIN
      RETURN GET('accesso',p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente);
   END GET_ACCESSO;
FUNCTION GET_TRACCIA
/******************************************************************************
 NOME:        GET_TRACCIA
 DESCRIZIONE: .
 PARAMETRI:   .
 RITORNA:     .
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    27/04/2005 MM     Creazione.
******************************************************************************/
   ( p_tipo_accesso VARCHAR2
   , p_progetto VARCHAR2
   , p_istanza VARCHAR2
   , p_modulo VARCHAR2
   , p_utente VARCHAR2)
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
   BEGIN
      RETURN GET('traccia',p_tipo_accesso, p_progetto, p_istanza, p_modulo, p_utente);
   END GET_TRACCIA;
   FUNCTION GET_DIAC_CARATTERISTICA
/******************************************************************************
 NOME:        GET_DIAC_CARATTERISTICA
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              prevista la caratteristica data con il valore dato.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
              p_caratteristica
           p_valore
 RITORNA:     Elenco dei diritti di accesso.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    21/01/2003 MM     Creazione.
 014 30/03/2021 SN   Inserire le informazioni relative al numero tentativi relativo alla password #49220
******************************************************************************/
   ( p_utente VARCHAR2
   , p_caratteristica VARCHAR2
   , p_valore NUMBER )
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return                VARCHAR2(32000);
   BEGIN
      FOR caac IN (
         SELECT 'Progetto: '||caac.progetto||' - '||prog.descrizione||
                DECODE(caac.Istanza,NULL,'',CHR(9)||' Istanza: '||caac.Istanza||' - '||i.descrizione)||
                DECODE(caac.modulo,NULL,'',CHR(9)||' Modulo: '||caac.modulo||' - '||modu.descrizione) descrizione,
                caac.min_lunghezza_pwd,
                caac.validita_password,
                NVL(caac.mod_pwd_primo_accesso,'NO') mod_pwd_primo_accesso,
                NVL(caac.ldap,'NO') ldap,
                NVL(caac.ammessi_car_speciali_pwd,'SI') car_speciali_pwd,
                NVL(caac.numeri_obb_pwd,'NO') num_obb_pwd,
                caac.gg_prima_riutilizzo_pwd,
                caac.tentativi_password -- rev. 014
           FROM CARATTERISTICHE_ACCESSO caac, PROGETTI prog, ISTANZE i, MODULI modu
          WHERE prog.progetto = caac.progetto
            AND i.Istanza(+) = caac.Istanza
            AND modu.modulo(+) = caac.modulo
            AND EXISTS (SELECT 1
                          FROM DIRITTI_ACCESSO DIAC, ISTANZE ista, PROGETTI prog
                         WHERE DIAC.Utente   = p_utente
                           AND ista.Istanza  = DIAC.Istanza
                           AND prog.progetto = ista.progetto
                           AND ( (ista.progetto = caac.progetto AND
                                  caac.Istanza IS NULL AND
                                  caac.modulo IS NULL AND
                                  caac.Utente IS NULL
                                 )
                                 OR
                                 (ista.progetto = caac.progetto AND
                                  DIAC.Istanza = caac.Istanza AND
                                  caac.modulo IS NULL AND
                                  caac.Utente IS NULL
                                 )
                                 OR
                                 (ista.progetto = caac.progetto AND
                                  caac.Istanza IS NULL AND
                                  DIAC.modulo = caac.modulo AND
                                  caac.Utente IS NULL
                                 )
                                 OR
                                 (ista.progetto = caac.progetto AND
                                  DIAC.Istanza = caac.Istanza AND
                                  DIAC.modulo = caac.modulo AND
                                  DIAC.Utente = caac.Utente
                                 )
                               )
                        )
         )
      LOOP
         IF (LOWER(p_caratteristica) = 'min_lunghezza_pwd' AND caac.min_lunghezza_pwd = p_valore)
         OR (LOWER(p_caratteristica) = 'min_validita_pwd' AND caac.validita_password = p_valore)
         OR (LOWER(p_caratteristica) = 'mod_pwd_primo_accesso' AND ((caac.mod_pwd_primo_accesso = 'NO' AND p_valore = 0) OR (caac.mod_pwd_primo_accesso = 'SI' AND p_valore = 1)))
         OR (LOWER(p_caratteristica) = 'ldap' AND ((caac.ldap = 'NO' AND p_valore = 0) OR (caac.ldap = 'SI' AND p_valore = 1)))
         OR (LOWER(p_caratteristica) = 'car_speciali_pwd' AND ((caac.car_speciali_pwd = 'NO' AND p_valore = 0) OR (caac.car_speciali_pwd = 'SI' AND p_valore = 1)))
         OR (LOWER(p_caratteristica) = 'num_obb_pwd' AND ((caac.num_obb_pwd = 'NO' AND p_valore = 0) OR (caac.num_obb_pwd = 'SI' AND p_valore = 1)))
         -- rev.8 inizio
         OR (LOWER(p_caratteristica) = 'max_gg_riuso_pwd' AND caac.gg_prima_riutilizzo_pwd = p_valore)
         -- rev.8 fine
         OR (LOWER(p_caratteristica) = 'tentativi_password' AND caac.tentativi_password = p_valore) -- rev. 014
         THEN
            d_return := d_return||caac.descrizione||CHR(13)||CHR(10);
         END IF;
      END LOOP;
     RETURN d_return;
   EXCEPTION WHEN OTHERS THEN
      RETURN '';
   END GET_DIAC_CARATTERISTICA;
   FUNCTION GET_DIAC_MAX_MINPWDLENGTH
/******************************************************************************
 NOME:        GET_DIAC_MAX_MINPWDLENGTH
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto il valore piu' grande della minima lunghezza della password
           per l'utente.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    21/01/2003 MM     Creazione.
******************************************************************************/
   ( p_utente VARCHAR2 )
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return                VARCHAR2(32000);
      d_max_minlungpwd        NUMBER(2) := 0;
   BEGIN
      d_max_minlungpwd := GET_MINPWDLENGTH(p_utente);
      IF NVL(d_max_minlungpwd,0) > 0 THEN
         d_return := GET_DIAC_CARATTERISTICA(p_utente, 'min_lunghezza_pwd', d_max_minlungpwd);
      END IF;
      RETURN d_return;
   EXCEPTION WHEN OTHERS THEN
      RETURN '';
   END GET_DIAC_MAX_MINPWDLENGTH;
   FUNCTION GET_DIAC_ISPWDDAMOD
/******************************************************************************
 NOME:        GET_DIAC_ISPWDDAMOD
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto il valore effettivo del campo MOD_PWD_PRIMO_ACCESSO
           per l'utente.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    21/01/2003 MM     Creazione.
******************************************************************************/
   ( p_utente VARCHAR2 )
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_isPwdDaMod            NUMBER(2) := 0;
   BEGIN
      d_isPwdDaMod := IS_PWD_DA_MOD_PRIMO_USO(p_utente);
     RETURN GET_DIAC_CARATTERISTICA(p_utente, 'mod_pwd_primo_accesso', d_isPwdDaMod);
   EXCEPTION WHEN OTHERS THEN
      RETURN '';
   END GET_DIAC_ISPWDDAMOD;
   FUNCTION GET_DIAC_ISLDAPUSER
/******************************************************************************
 NOME:        GET_DIAC_ISLDAPUSER
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto il valore effettivo del campo LDAP per l'utente.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    21/01/2003 MM     Creazione.
******************************************************************************/
   ( p_utente VARCHAR2 )
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_isldapuser            NUMBER(2) := 0;
   BEGIN
      d_isldapuser := IS_LDAPUSER(p_utente);
     RETURN GET_DIAC_CARATTERISTICA(p_utente, 'ldap', d_isldapuser);
   EXCEPTION WHEN OTHERS THEN
      RETURN '';
   END GET_DIAC_ISLDAPUSER;
   FUNCTION GET_DIAC_IS_CAR_SPECIALI_PWD
/******************************************************************************
 NOME:        GET_DIAC_IS_CAR_SPECIALI_PWD
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto che la password possa contenere caratteri speciali.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    03/01/2006 MM     Creazione.
******************************************************************************/
   ( p_utente VARCHAR2 )
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_car_speciali_pwd                NUMBER(2) := 0;
   BEGIN
      d_car_speciali_pwd := IS_CAR_SPECIALI_PWD(p_utente);
     RETURN GET_DIAC_CARATTERISTICA(p_utente, 'car_speciali_pwd', d_car_speciali_pwd);
   EXCEPTION WHEN OTHERS THEN
      RETURN '';
   END GET_DIAC_IS_CAR_SPECIALI_PWD;
   FUNCTION GET_DIAC_IS_NUM_OBB_PWD
/******************************************************************************
 NOME:        GET_DIAC_IS_NUM_OBB_PWD
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto che la password debba contenere almeno un numero.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    03/01/2006 MM     Creazione.
******************************************************************************/
   ( p_utente VARCHAR2 )
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_is_num_obb_pwd        NUMBER(2) := 0;
   BEGIN
      d_is_num_obb_pwd := IS_NUM_OBB_PWD(p_utente);
     RETURN GET_DIAC_CARATTERISTICA(p_utente, 'num_obb_pwd', d_is_num_obb_pwd);
   EXCEPTION WHEN OTHERS THEN
      RETURN '';
   END GET_DIAC_IS_NUM_OBB_PWD;
   FUNCTION GET_DIAC_VAL_PWD
/******************************************************************************
 NOME:        GGET_DIAC_VAL_PWD
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto il valore piu' grande della minima lunghezza della password
           per l'utente.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    21/01/2003 MM     Creazione.
******************************************************************************/
   ( p_utente VARCHAR2 )
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return                VARCHAR2(32000);
      d_min_val_pwd        NUMBER(2) := 0;
   BEGIN
      d_min_val_pwd := GET_MAXVALPWD(p_utente);
      IF NVL(d_min_val_pwd,0) > 0 THEN
         d_return := GET_DIAC_CARATTERISTICA(p_utente, 'min_validita_pwd', d_min_val_pwd);
      END IF;
      RETURN d_return;
   EXCEPTION WHEN OTHERS THEN
      RETURN '';
   END GET_DIAC_VAL_PWD;



   FUNCTION GET_DIAC_TENTATIVI_PWD
/******************************************************************************
 NOME:        GET_DIAC_TENTATIVI_PWD
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto il valore piu' basso del numero dei tentativi per l'utente.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 014 30/03/2021 SN   Inserire le informazioni relative al numero tentativi relativo alla password #49220
******************************************************************************/
   ( p_utente VARCHAR2 )
   RETURN VARCHAR2
   IS /* SLAVE_COPY */
      d_return                VARCHAR2(32000);
      d_min_tentativi_pwd        NUMBER(2) := 0;
   BEGIN
      d_min_tentativi_pwd := GET_MIN_MAX_TENTATIVI_PWD(p_utente, utente.get_tipo_utente(p_utente));
      IF NVL(d_min_tentativi_pwd,0) > 0 THEN
         d_return := GET_DIAC_CARATTERISTICA(p_utente, 'tentativi_password', d_min_tentativi_pwd);
      END IF;
      RETURN d_return;
   EXCEPTION WHEN OTHERS THEN
      RETURN '';
   END GET_DIAC_TENTATIVI_PWD;
   FUNCTION IS_CARATTERISTICA
/******************************************************************************
 NOME:        IS_CARATTERISTICA
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista la caratteristica.
 PARAMETRI:   p_utente          varchar2 codice utente da verificare.
              p_caratteristica  varchar2 caratteristica da verificare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' prevista
                la caratteristica,
              0 altrimenti.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  2    04/01/2006 MM     Creazione.
  011  11/01/2019 SNeg In caso di utenza ldap aggiornare pwd sempre = NO
******************************************************************************/
   ( p_utente VARCHAR2
   , p_caratteristica VARCHAR2
   , p_tipo_utente varchar2 default null)
   RETURN NUMBER
   IS /* SLAVE_COPY */
      d_return                NUMBER := 0;
      d_tipo_accesso          VARCHAR2(1) := 'D';
      d_accesso               VARCHAR2(1);
      d_accesso_se            VARCHAR2(2);
      d_traccia               VARCHAR2(1);
      d_giorni_traccia        NUMBER(3);
      d_tentativi_pwd         NUMBER(2);
      d_val_pwd               NUMBER(3);
      d_sleep                 NUMBER(3);
      d_single_sign_on        VARCHAR2(2);
      d_ldap                  VARCHAR2(2);
      d_min_lung_pwd          NUMBER(2);
      d_mod_pwd_primo_accesso VARCHAR2(2);
      d_arch_traccia          VARCHAR2(2);
      d_disl_traccia          VARCHAR2(100);
      d_car_speciali_pwd      VARCHAR2(2);
      d_num_obb_pwd           VARCHAR2(2);
      d_tipo_utente           varchar2(1) := nvl(p_tipo_utente, 'E');
      d_gg_prima_riutilizzo_pwd  CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
   BEGIN
      IF LOWER(p_caratteristica) NOT IN ('ldap', 'pwd_da_mod_primo_uso', 'car_speciali_pwd', 'num_obb_pwd') THEN
         RAISE_APPLICATION_ERROR(-20999, 'Funzione non gestita per caratteristica '''||p_caratteristica||'''.');
      END IF;
      FOR DIAC IN (SELECT ista.progetto, DIAC.Istanza, DIAC.modulo, DIAC.Utente
                     FROM ISTANZE ista, DIRITTI_ACCESSO DIAC
                    WHERE ista.Istanza = DIAC.Istanza
                      AND DIAC.Utente  = p_utente)
      LOOP
         if d_tipo_utente = 'E' then
            d_tipo_utente := utente.GET_TIPO_UTENTE(p_utente);
         end if;
         if d_tipo_utente = 'U' then
            d_tipo_accesso := 'D';
         else
            d_tipo_accesso := 'G';
         end if;
         get_effettive ( d_tipo_accesso, DIAC.progetto, DIAC.Istanza
                       , DIAC.modulo, DIAC.Utente, d_accesso, d_accesso_se
                       , d_traccia, d_giorni_traccia, d_tentativi_pwd
                       , d_val_pwd, d_sleep, d_single_sign_on, d_ldap
                       , d_min_lung_pwd, d_mod_pwd_primo_accesso, d_arch_traccia
                       , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
--  INTEGRITYPACKAGE.LOG('p_caratteristica: '||p_caratteristica);
--  INTEGRITYPACKAGE.LOG('d_num_obb_pwd: '||NVL(d_num_obb_pwd,'NO'));
--  INTEGRITYPACKAGE.LOG('d_tipo_accesso, diac.progetto, diac.istanza,
--  diac.modulo, diac.utente: '||d_tipo_accesso||', '||diac.progetto||',
-- '||diac.Istanza||', '||diac.modulo||', '||diac.Utente);
         IF (NVL(d_ldap,'NO') = 'SI' AND LOWER(p_caratteristica) = 'ldap')
         OR (NVL(d_mod_pwd_primo_accesso,'NO') = 'SI' AND LOWER(p_caratteristica) = 'pwd_da_mod_primo_uso'
                 -- rev.11 inizio
                 and nvl(is_ldapuser(p_utente),0)
                 != 1)
                 -- rev.11 fine
         OR (NVL(d_num_obb_pwd,'NO') = 'SI' AND LOWER(p_caratteristica) = 'num_obb_pwd') THEN
            d_return := 1;
            EXIT;
         END IF;
         IF LOWER(p_caratteristica) = 'car_speciali_pwd' THEN
            d_return := 1;
            IF NVL(d_car_speciali_pwd,'SI') = 'NO' THEN
               d_return := 0;
               EXIT;
            END IF;
         END IF;
      END LOOP;
      RETURN d_return;
   END IS_CARATTERISTICA;
   FUNCTION IS_LDAPUSER
/******************************************************************************
 NOME:        IS_LDAPUSER
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista l'autenticazione via LDAP.
 PARAMETRI:   p_utente varchar2 utente da autenticare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' prevista
                l'autenticazione via LDAP,
              0 altrimenti.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
  2    04/01/2006 MM     Chiamata alla funzione IS_CARATTERISTICA.
******************************************************************************/
   ( p_utente VARCHAR2
   , p_tipo_utente varchar2 default null )
   RETURN NUMBER
   IS /* SLAVE_COPY */
   BEGIN
      RETURN IS_CARATTERISTICA(p_utente, 'ldap', p_tipo_utente);
   END IS_LDAPUSER;
   FUNCTION IS_PWD_DA_MOD_PRIMO_USO
/******************************************************************************
 NOME:        IS_PWD_DA_MOD_PRIMO_USO
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista la modifica della password al primo accesso.
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' prevista
                la modifica della password al primo accesso.
              0 altrimenti.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
  2    04/01/2006 MM     Chiamata alla funzione IS_CARATTERISTICA.
******************************************************************************/
   ( p_utente VARCHAR2
   , p_tipo_utente varchar2 default null )
   RETURN NUMBER
   IS /* SLAVE_COPY */
   BEGIN
      RETURN IS_CARATTERISTICA(p_utente,'pwd_da_mod_primo_uso', p_tipo_utente);
   END IS_PWD_DA_MOD_PRIMO_USO;
   FUNCTION IS_CAR_SPECIALI_PWD
/******************************************************************************
 NOME:        IS_CAR_SPECIALI_PWD
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista la possibilita' di utilizzare caratteri speciali nella
              password.
 PARAMETRI:   p_utente varchar2 utente da autenticare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' previsto
              0 altrimenti.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  2    04/01/2006 MM     Creazione.
******************************************************************************/
   ( p_utente VARCHAR2
   , p_tipo_utente varchar2 default null )
   RETURN NUMBER
   IS /* SLAVE_COPY */
   BEGIN
      RETURN IS_CARATTERISTICA(p_utente,'car_speciali_pwd', p_tipo_utente);
   END IS_CAR_SPECIALI_PWD;
   FUNCTION IS_NUM_OBB_PWD
/******************************************************************************
 NOME:        IS_NUM_OBB_PWD
 DESCRIZIONE: Verifica se l'utente dato ha almeno un diritto di accesso per cui
              sia prevista la presenza obbligatoria di almeno un numero nella
              password.
 PARAMETRI:   p_utente varchar2 utente da autenticare.
 RITORNA:     1 se p_utente ha almeno un diritto di accesso per cui e' previsto
              0 altrimenti.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  2    04/01/2006 MM     Creazione.
******************************************************************************/
   ( p_utente VARCHAR2
   , p_tipo_utente varchar2 default null )
   RETURN NUMBER
   IS /* SLAVE_COPY */
   BEGIN
      RETURN IS_CARATTERISTICA(p_utente, 'num_obb_pwd', p_tipo_utente);
   END IS_NUM_OBB_PWD;
   FUNCTION GET_MINPWDLENGTH
/******************************************************************************
 NOME:        GET_MINPWDLENGTH
 DESCRIZIONE: Ottiene il valore piu' grande della minima lunghezza della password
              settata per l'utente (considerando tutti i suoi diritti di accesso).
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     il valore piu' grande della minima lunghezza della password.
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
  2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                        DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                        e NUMERI_OBB_PWD.
******************************************************************************/
   ( p_utente VARCHAR2
   , p_tipo_utente varchar2 default null )
   RETURN NUMBER
   IS /* SLAVE_COPY */
      d_return                NUMBER := 0;
      d_tipo_accesso          VARCHAR2(1) := 'D';
      d_accesso               VARCHAR2(1);
      d_accesso_se            VARCHAR2(2);
      d_traccia               VARCHAR2(1);
      d_giorni_traccia        NUMBER(3);
      d_tentativi_pwd         NUMBER(2);
      d_val_pwd               NUMBER(3);
      d_sleep                 NUMBER(3);
      d_single_sign_on        VARCHAR2(2);
      d_ldap                  VARCHAR2(2);
      d_min_lung_pwd          NUMBER(2) := 0;
      d_mod_pwd_primo_accesso VARCHAR2(2);
      d_arch_traccia        VARCHAR2(2);
      d_disl_traccia        VARCHAR2(100);
      d_car_speciali_pwd      VARCHAR2(2);
      d_num_obb_pwd           VARCHAR2(2);
      d_tipo_utente           varchar2(1) := nvl(p_tipo_utente, 'E');
      d_gg_prima_riutilizzo_pwd CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
   BEGIN
      FOR DIAC IN (SELECT ista.progetto, DIAC.Istanza, DIAC.modulo, DIAC.Utente
                     FROM ISTANZE ista, DIRITTI_ACCESSO DIAC
                    WHERE DIAC.Utente  = p_utente
                      AND ista.Istanza = DIAC.Istanza)
      LOOP
         if d_tipo_utente = 'E' then
            d_tipo_utente := utente.GET_TIPO_UTENTE(p_utente);
         end if;
         if d_tipo_utente = 'U' then
            d_tipo_accesso := 'D';
         else
            d_tipo_accesso := 'G';
         end if;
         get_effettive ( d_tipo_accesso, DIAC.progetto, DIAC.Istanza
                       , DIAC.modulo, DIAC.Utente, d_accesso, d_accesso_se
                       , d_traccia, d_giorni_traccia, d_tentativi_pwd
                       , d_val_pwd, d_sleep, d_single_sign_on, d_ldap
                       , d_min_lung_pwd, d_mod_pwd_primo_accesso, d_arch_traccia
                       , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
         IF NVL(d_min_lung_pwd,0) > d_return THEN
            d_return := d_min_lung_pwd;
         END IF;
      END LOOP;
      RETURN d_return;
   END GET_MINPWDLENGTH;

   FUNCTION GET_MIN_MAX_TENTATIVI_PWD
/******************************************************************************
 NOME:        GET_MIN_MAX_TENTATIVI_PWD
 DESCRIZIONE: Ottiene il valore piu' piccolo del numero dei tentativi
              settata per l'utente (considerando tutti i suoi diritti di accesso).
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     il valore piu' piccolo del numero dei tentativi
 ECCEZIONI:   --
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  1   11/03/2019 SN     Introdotto x verificare trasversalmente il numero dei tentativi
******************************************************************************/
   ( p_utente VARCHAR2
   , p_tipo_utente varchar2 default null )
   RETURN NUMBER
   IS /* SLAVE_COPY */
      d_return                NUMBER := 0;
      d_tipo_accesso          VARCHAR2(1) := 'D';
      d_accesso               VARCHAR2(1);
      d_accesso_se            VARCHAR2(2);
      d_traccia               VARCHAR2(1);
      d_giorni_traccia        NUMBER(3);
      d_tentativi_pwd         NUMBER(2);
      d_val_pwd               NUMBER(3);
      d_sleep                 NUMBER(3);
      d_single_sign_on        VARCHAR2(2);
      d_ldap                  VARCHAR2(2);
      d_min_lung_pwd          NUMBER(2) := 0;
      d_mod_pwd_primo_accesso VARCHAR2(2);
      d_arch_traccia        VARCHAR2(2);
      d_disl_traccia        VARCHAR2(100);
      d_car_speciali_pwd      VARCHAR2(2);
      d_num_obb_pwd           VARCHAR2(2);
      d_tipo_utente           varchar2(1) := nvl(p_tipo_utente, 'E');
      d_gg_prima_riutilizzo_pwd CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
   BEGIN
      d_return := null;
      FOR DIAC IN (SELECT ista.progetto, DIAC.Istanza, DIAC.modulo, DIAC.Utente
                     FROM ISTANZE ista, DIRITTI_ACCESSO DIAC
                    WHERE DIAC.Utente  = p_utente
                      AND ista.Istanza = DIAC.Istanza)
      LOOP
         if d_tipo_utente = 'E' then
            d_tipo_utente := utente.GET_TIPO_UTENTE(p_utente);
         end if;
         if d_tipo_utente = 'U' then
            d_tipo_accesso := 'D';
         else
            d_tipo_accesso := 'G';
         end if;
         get_effettive ( d_tipo_accesso, DIAC.progetto, DIAC.Istanza
                       , DIAC.modulo, DIAC.Utente, d_accesso, d_accesso_se
                       , d_traccia, d_giorni_traccia, d_tentativi_pwd
                       , d_val_pwd, d_sleep, d_single_sign_on, d_ldap
                       , d_min_lung_pwd, d_mod_pwd_primo_accesso, d_arch_traccia
                       , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
         IF d_tentativi_pwd is not null and d_tentativi_pwd < nvl(d_return,1000) THEN
            d_return := d_tentativi_pwd;
         END IF;
      END LOOP;
      RETURN d_return;
   END GET_MIN_MAX_TENTATIVI_PWD;

   FUNCTION IS_DEFAULT
/******************************************************************************
 NOME:        IS_DEFAULT
 DESCRIZIONE: <Descrizione procedura>
 ARGOMENTI:   a_<arg1> IN OUT <type> <Descrizione argomento 1>
              a_<arg2> IN OUT <type> <Descrizione argomento 2>
 ECCEZIONI:   nnnnn, <Descrizione eccezione>
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
  2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                         DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                         e NUMERI_OBB_PWD.
******************************************************************************/
   ( new_tipo_accesso IN VARCHAR2, new_progetto IN VARCHAR2
   , new_istanza IN VARCHAR2, new_modulo IN VARCHAR2, new_utente IN VARCHAR2
   , new_accesso IN VARCHAR2, new_accesso_se IN VARCHAR2, new_traccia IN VARCHAR2
   , new_giorni_traccia IN NUMBER, new_tentativi_password IN NUMBER
   , new_validita_password IN NUMBER, new_sleep IN NUMBER
   , new_single_sign_on IN VARCHAR2, new_ldap IN VARCHAR2
   , new_min_lunghezza_pwd IN NUMBER, new_mod_pwd_primo_accesso IN VARCHAR2
   , new_arch_traccia IN VARCHAR2, new_disl_traccia IN VARCHAR2
   , new_car_speciali_pwd IN VARCHAR2, new_num_obb_pwd IN VARCHAR2,NEW_GG_PRIMA_RIUTILIZZO_PWD IN NUMBER)
   RETURN NUMBER
   IS /* SLAVE_COPY */
      d_tipo_accesso VARCHAR2(1);
      d_accesso VARCHAR2(200);
      d_accesso_se VARCHAR2(200);
      d_traccia VARCHAR2(200);
      d_giorni_traccia NUMBER;
      d_tentativi_pwd NUMBER;
      d_val_pwd NUMBER;
      d_sleep NUMBER;
      d_single_sign_on VARCHAR2(200);
      d_ldap VARCHAR2(200);
      d_min_lung_pwd NUMBER;
      d_mod_pwd_primo_accesso VARCHAR2(200);
      d_arch_traccia VARCHAR2(200);
      d_disl_traccia VARCHAR2(200);
      d_car_speciali_pwd      VARCHAR2(2);
      d_num_obb_pwd           VARCHAR2(2);
      d_dep_tiac VARCHAR2(1);
      d_default  NUMBER(1) := 0;
      d_gg_prima_riutilizzo_pwd  CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
   BEGIN
      -- se e' un superutente sicuramente ha le caratteristiche personalizzate
      IF ( new_tipo_accesso <> 'D' AND new_tipo_accesso <> 'G')
      OR NVL(new_accesso_se, 'NO') = 'NO' THEN
         -- Verifica se sono stati inseriti/modificati dei dati significativi (<> default)
         /****************************************/
         /*             Progetto                 */
         /****************************************/
         IF new_tipo_accesso = 'P' THEN
            d_dep_tiac := 'P';
            d_accesso := 'L';
            d_accesso_se := 'NO';
            d_traccia := 'M';
            d_giorni_traccia := 60;
            d_tentativi_pwd := -1;
            d_val_pwd := -1;
            d_sleep := -1;
            d_single_sign_on := 'NO';
            d_ldap := 'NO';
            d_min_lung_pwd := 0;
            d_mod_pwd_primo_accesso := 'NO';
            d_arch_traccia := 'NO';
            d_disl_traccia := TO_CHAR(NULL);
            d_car_speciali_pwd := 'SI';
            d_num_obb_pwd := 'NO';
            d_gg_prima_riutilizzo_pwd := -1;
         ELSE
            /****************************************/
            /*          Istanza e Modulo            */
            /****************************************/
            -- Leggo caratteristiche del PROGETTO.
            d_tipo_accesso := 'P';
            GET ( d_tipo_accesso, new_progetto, new_istanza, new_modulo, new_utente
                , d_accesso, d_accesso_se, d_traccia, d_giorni_traccia
                , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
                , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_accesso, d_arch_traccia
                , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
            IF d_tipo_accesso IS NOT NULL THEN
               d_dep_tiac := d_tipo_accesso;
            ELSE
               d_dep_tiac := new_tipo_accesso;
            END IF;
            IF new_tipo_accesso IN ('D','G') THEN
            /****************************************/
            /*           Utente o Gruppo            */
            /****************************************/
               -- Leggo caratteristiche dell'ISTANZA.
               d_tipo_accesso := 'I';
               GET ( d_tipo_accesso, new_progetto, new_istanza, new_modulo, new_utente
                   , d_accesso, d_accesso_se, d_traccia, d_giorni_traccia
                   , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
                   , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_accesso, d_arch_traccia
                   , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
               IF d_tipo_accesso IS NOT NULL THEN
                  d_dep_tiac := d_tipo_accesso;
               END IF;
               -- Leggo caratteristiche del MODULO.
               d_tipo_accesso := 'M';
               GET ( d_tipo_accesso, new_progetto, new_istanza, new_modulo, new_utente
                   , d_accesso, d_accesso_se, d_traccia, d_giorni_traccia
                   , d_tentativi_pwd, d_val_pwd, d_sleep, d_single_sign_on
                   , d_ldap, d_min_lung_pwd, d_mod_pwd_primo_accesso, d_arch_traccia
                   , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
               IF d_tipo_accesso IS NOT NULL THEN
                  d_dep_tiac := d_tipo_accesso;
               END IF;
            END IF;
         END IF;
       INTEGRITYPACKAGE.LOG('d_dep_tiac: '||d_dep_tiac);
         IF  d_dep_tiac IS NOT NULL
         AND NVL(new_accesso, 'L') = d_accesso
         AND NVL(new_accesso_se, 'NO') = d_accesso_se
         AND NVL(new_traccia, 'M') = d_traccia
         AND NVL(new_giorni_traccia, 60) = d_giorni_traccia
         AND NVL(new_tentativi_password,-1) = NVL(d_tentativi_pwd,-1)
         AND NVL(new_validita_password,-1) = NVL(d_val_pwd,-1)
         AND NVL(new_sleep,-1) = NVL(d_sleep,-1)
         AND NVL(new_single_sign_on, 'NO') = d_single_sign_on
         AND NVL(new_ldap, 'NO') = d_ldap
         AND NVL(new_min_lunghezza_pwd, 0) = NVL(d_min_lung_pwd, 0)
         AND NVL(new_mod_pwd_primo_accesso,'NO') = d_mod_pwd_primo_accesso
         AND NVL(new_arch_traccia,'NO') = d_arch_traccia
         AND NVL(new_disl_traccia, ' ') = NVL(d_disl_traccia, ' ')
         AND NVL(new_car_speciali_pwd,'SI') = d_car_speciali_pwd
         AND NVL(new_num_obb_pwd,'NO') = d_num_obb_pwd
          AND NVL(NEW_gg_prima_riutilizzo_pwd,-1) = d_gg_prima_riutilizzo_pwd
           THEN
            d_default := 1;
         END IF;
      END IF;
     RETURN d_default;
   END IS_DEFAULT;
   PROCEDURE UPDATE_CARATTERISTICA
/******************************************************************************
 NOME:        UPDATE_CARATTERISTICA
 DESCRIZIONE: <Descrizione procedura>
 ARGOMENTI:   a_<arg1> IN OUT <type> <Descrizione argomento 1>
              a_<arg2> IN OUT <type> <Descrizione argomento 2>
 ECCEZIONI:   nnnnn, <Descrizione eccezione>
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    20/01/2003 MM
 3    22/03/2007 MM     A20271.0.0.
008   21/09/2018 SN    Aggiunto campo gg_prima_riutilizzo_pwd
******************************************************************************/
   ( old_caac_id IN NUMBER, old_progetto IN VARCHAR2
   , old_istanza IN VARCHAR2, old_modulo IN VARCHAR2, old_utente IN VARCHAR2
   , new_caac_id IN NUMBER, new_tipo_accesso IN VARCHAR2, new_progetto IN VARCHAR2
   , new_istanza IN VARCHAR2, new_modulo IN VARCHAR2, new_utente IN VARCHAR2
   , new_accesso IN VARCHAR2, new_accesso_se IN VARCHAR2, new_traccia IN VARCHAR2
   , new_giorni_traccia IN NUMBER, new_tentativi_password IN NUMBER
   , new_validita_password IN NUMBER, new_sleep IN NUMBER
   , new_single_sign_on IN VARCHAR2, new_ldap IN VARCHAR2
   , new_min_lunghezza_pwd IN NUMBER, new_mod_pwd_primo_accesso IN VARCHAR2
   , new_arch_traccia IN VARCHAR2, new_disl_traccia IN VARCHAR2
   , new_car_speciali_pwd IN VARCHAR2, new_num_obb_pwd IN VARCHAR2
   , new_gg_prima_riutilizzo_pwd  IN  NUMBER
   , p_move_file IN NUMBER DEFAULT 0)
   IS
      d_default NUMBER(1);
      d_old_disl_traccia VARCHAR2(100);
   BEGIN
      -- Verifica se sono stati inseriti/modificati dei dati significativi (<> default)
      d_default := IS_DEFAULT( new_tipo_accesso, new_progetto, new_istanza, new_modulo, new_utente
                             , new_accesso, new_accesso_se
                             , new_traccia, new_giorni_traccia
                             , new_tentativi_password, new_validita_password
                             , new_sleep, new_single_sign_on, new_ldap
                             , new_min_lunghezza_pwd, new_mod_pwd_primo_accesso
                             , new_arch_traccia, new_disl_traccia
                             , new_car_speciali_pwd, new_num_obb_pwd,new_gg_prima_riutilizzo_pwd);
      -- Rev.3    22/03/2007 MM     A20271.0.0.
      IF d_default = 0 then
         if  new_accesso is null
         and new_accesso_se is null
         and new_traccia is null
         and new_giorni_traccia is null
         and new_tentativi_password is null
         and new_validita_password is null
         and new_sleep is null
         and new_single_sign_on is null
         and new_ldap is null
         and new_min_lunghezza_pwd is null
         and new_mod_pwd_primo_accesso is null
         and new_arch_traccia is null
         and new_disl_traccia is null
         and new_car_speciali_pwd is null
         and new_num_obb_pwd is null
         and new_gg_prima_riutilizzo_pwd is null then
            d_default := 1;
         end if;
      end if;
      -- Rev.3    22/03/2007 MM     A20271.0.0. fine mod.
      Caratteristiche_Accesso_Pu( old_caac_id, old_progetto, old_istanza, old_modulo
                                , old_utente, new_caac_id, new_progetto, new_istanza
                                , new_modulo, new_utente);
INTEGRITYPACKAGE.LOG('UPDATE_CARATTERISTICA d_default: '||d_default);
      IF d_default = 1 THEN
         DELETE CARATTERISTICHE_ACCESSO
          WHERE caac_id = old_caac_id;
      ELSE
         SELECT dislocazione_traccia
           INTO d_old_disl_traccia
           FROM CARATTERISTICHE_ACCESSO
          WHERE caac_id = old_caac_id
         ;
         UPDATE CARATTERISTICHE_ACCESSO
            SET progetto                 = new_progetto,
                Istanza                  = new_istanza,
                modulo                   = new_modulo,
                Utente                   = new_utente,
                Accesso                  = new_accesso,
                accesso_se               = new_accesso_se,
                traccia                  = new_traccia,
                giorni_traccia           = new_giorni_traccia,
                archiviazione_traccia    = nvl(new_arch_traccia, 'NO'),
                dislocazione_traccia     = new_disl_traccia,
                tentativi_password       = new_tentativi_password,
                validita_password        = new_validita_password,
                sleep                    = new_sleep,
                single_sign_on           = new_single_sign_on,
                ldap                     = new_ldap,
                min_lunghezza_pwd        = new_min_lunghezza_pwd,
                mod_pwd_primo_accesso    = new_mod_pwd_primo_accesso,
                ammessi_car_speciali_pwd = nvl(new_car_speciali_pwd, 'SI'),
                numeri_obb_pwd           = nvl(new_num_obb_pwd, 'NO'),
                gg_prima_riutilizzo_pwd  = new_gg_prima_riutilizzo_pwd
          WHERE caac_id = old_caac_id
         ;
         IF  NVL(new_disl_traccia,' ') <> ' '
         AND NVL(new_disl_traccia, ' ') <> NVL(d_old_disl_traccia, ' ') THEN
            DECLARE
               d_dir_alias_old    VARCHAR2(30);
               d_dir_alias        VARCHAR2(30);
               d_err              INTEGER;
               d_testo            VARCHAR2(2000);
            BEGIN
               d_dir_alias := Afc_Bfile.next_directory('AD4',new_disl_traccia);
               -- create_directory ritorna:
               -- 0  OK
               -- -1 errore in creazione dell'alias
               -- -2 errore in creazione del file di test nella directory
               -- -3 errore in eliminazione del file di test dalla directory
               d_err := Afc_Bfile.create_directory(d_dir_alias,new_disl_traccia);
               IF d_err = -1 THEN
                  RAISE_APPLICATION_ERROR(-20999,'Errore in creazione dell''alias della directory '||new_disl_traccia);
               ELSIF d_err = -2 THEN
                  RAISE_APPLICATION_ERROR(-20999,'Errore in creazione del file di test nella directory '||new_disl_traccia);
               END IF;
               IF NVL(d_old_disl_traccia, ' ') <> ' ' AND p_move_file = 1 THEN
                  BEGIN
                     Ad4_Evento.COPY_FILE(d_old_disl_traccia, new_disl_traccia, 'log_______.xml');
                  EXCEPTION WHEN OTHERS THEN
                     RAISE;
                  END;
                  -- DELETEFILE TUTTI dalla d_dir_alias_old!!!
                  d_dir_alias_old := Afc_Bfile.GET_DIRNAME(d_old_disl_traccia,'AD4_');
                  FOR cur_eventi IN (SELECT Ad4_Evento.GET_FILENAME(id_evento) even_file
                                       FROM EVENTI
                                      WHERE file_locator IS NOT NULL
                                        AND NVL(Ad4_Evento.get_dir_alias(id_evento),' ') = d_dir_alias_old)
                  LOOP
                     BEGIN
                        DBMS_BACKUP_RESTORE.DELETEFILE(cur_eventi.even_file);
                     EXCEPTION WHEN OTHERS THEN
                        NULL;
                     END;
                  END LOOP;
                  -- aggiorna il file locator
                  FOR cur_even IN (SELECT id_evento, Ad4_Evento.get_filename(id_evento,0) even_file
                                     FROM EVENTI
                                     WHERE file_locator IS NOT NULL
                                     AND NVL(Ad4_Evento.get_dir_alias(id_evento),' ') = d_dir_alias_old)
                  LOOP
                     d_testo := Ad4_Evento.GET_TESTO(cur_even.id_evento)||CHR(10)||'Spostato in '||new_disl_traccia||' il '||TO_CHAR(SYSDATE,'dd/mm/yyyy hh24:mi:ss');
                     Ad4_Evento.SET_FILE_LOCATOR(cur_even.id_evento, d_dir_alias, cur_even.even_file);
                     Ad4_Evento.SET_TESTO(cur_even.id_evento,d_testo);
                  END LOOP;
               END IF;
            END;
         END IF;
      END IF;
   END UPDATE_CARATTERISTICA;
   FUNCTION INSERT_CARATTERISTICA
/******************************************************************************
 NOME:        INSERT_CARATTERISTICA
 DESCRIZIONE: <Descrizione procedura>
 ARGOMENTI:   a_<arg1> IN OUT <type> <Descrizione argomento 1>
              a_<arg2> IN OUT <type> <Descrizione argomento 2>
 ECCEZIONI:   nnnnn, <Descrizione eccezione>
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  1    30/05/2005 MM     Introduzione del campo LDAP e MIN_LUNGHEZZA_PWD.
  2    01/12/2005 MM     Gestione nuovi campi ARCHIVIAZIONE_TRACCIA,
                         DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD
                         e NUMERI_OBB_PWD.
008   21/09/2018 SN    Aggiunto campo gg_prima_riutilizzo_pwd
******************************************************************************/
   ( new_caac_id IN NUMBER, new_tipo_accesso IN VARCHAR2, new_progetto IN VARCHAR2
   , new_istanza IN VARCHAR2, new_modulo IN VARCHAR2, new_utente IN VARCHAR2
   , new_accesso IN VARCHAR2, new_accesso_se IN VARCHAR2, new_traccia IN VARCHAR2
   , new_giorni_traccia IN NUMBER, new_tentativi_password IN NUMBER
   , new_validita_password IN NUMBER, new_sleep IN NUMBER
   , new_single_sign_on IN VARCHAR2, new_ldap IN VARCHAR2
   , new_min_lunghezza_pwd IN NUMBER, new_mod_pwd_primo_accesso IN VARCHAR2
   , new_arch_traccia IN VARCHAR2, new_disl_traccia IN VARCHAR2
   , new_car_speciali_pwd IN VARCHAR2, new_num_obb_pwd IN VARCHAR2
   , new_gg_prima_riutilizzo_pwd  IN  NUMBER)
   RETURN NUMBER
   IS
      d_id      INTEGER;
      d_esiste  NUMBER(1);
      d_default NUMBER(1);
   BEGIN
      -- Verifica se sono stati inseriti/modificati dei dati significativi (<> default)
      d_default := IS_DEFAULT( new_tipo_accesso, new_progetto, new_istanza, new_modulo, new_utente
                             , new_accesso, new_accesso_se
                             , new_traccia, new_giorni_traccia
                             , new_tentativi_password, new_validita_password
                             , new_sleep, new_single_sign_on, new_ldap
                             , new_min_lunghezza_pwd, new_mod_pwd_primo_accesso
                             , new_arch_traccia, new_disl_traccia
                             , new_car_speciali_pwd, new_num_obb_pwd,new_gg_prima_riutilizzo_pwd);
INTEGRITYPACKAGE.LOG('INSERT_CARATTERISTICA d_default: '||d_default);
      IF d_default = 0 THEN
         Caratteristiche_Accesso_Pi(new_progetto, new_istanza, new_modulo, new_utente);
         BEGIN
            SELECT 1
              INTO d_esiste
              FROM CARATTERISTICHE_ACCESSO
             WHERE (     tipo_accesso       = new_tipo_accesso
                     AND progetto           = new_progetto
                     AND NVL(Istanza,'xxx') = DECODE(tipo_accesso,'I',new_istanza,'G',new_istanza,'D',new_istanza,NVL(Istanza,'xxx'))
                     AND NVL(modulo,'xxx')  = DECODE(tipo_accesso,'M',new_modulo,'G',new_modulo,'D',new_modulo,NVL(modulo,'xxx'))
                     AND NVL(Utente,'xxx')  = DECODE(tipo_accesso,'G',new_utente,'D',new_utente,NVL(Utente,'xxx'))
                   )
                OR caac_id = new_caac_id
            ;
            IF SQL%RowCount > 0 THEN
               RAISE_APPLICATION_ERROR(-20999,'Accesso gia gestito');
            END IF;
         EXCEPTION
            WHEN NO_DATA_FOUND THEN
               d_esiste := 0;
         END;
         IF d_esiste = 0 AND d_default = 0 THEN
            d_id := NVL(new_caac_id, Si4.NEXT_ID('CARATTERISTICHE_ACCESSO','CAAC_ID'));
            INSERT INTO CARATTERISTICHE_ACCESSO (CAAC_ID, TIPO_ACCESSO, PROGETTO, Istanza,
                        MODULO, Utente, Accesso, ACCESSO_SE, TRACCIA, GIORNI_TRACCIA,
                        TENTATIVI_PASSWORD, VALIDITA_PASSWORD, SLEEP, SINGLE_SIGN_ON,
                        LDAP, MIN_LUNGHEZZA_PWD, MOD_PWD_PRIMO_ACCESSO, ARCHIVIAZIONE_TRACCIA,
                        DISLOCAZIONE_TRACCIA, AMMESSI_CAR_SPECIALI_PWD, NUMERI_OBB_PWD,gg_prima_riutilizzo_pwd)
               VALUES ( d_id, new_tipo_accesso, new_progetto, new_istanza, new_modulo
                      , new_utente, new_accesso, new_accesso_se, new_traccia
                      , new_giorni_traccia, new_tentativi_password, new_validita_password
                      , new_sleep, new_single_sign_on, new_ldap, new_min_lunghezza_pwd
                      , new_mod_pwd_primo_accesso, nvl(new_arch_traccia, 'NO'), new_disl_traccia
                      , nvl(new_car_speciali_pwd, 'SI'), nvl(new_num_obb_pwd, 'NO'),new_gg_prima_riutilizzo_pwd)
            ;
         END IF;
      END IF;
     RETURN d_id;
   END INSERT_CARATTERISTICA;
   FUNCTION IS_EQUAL
/******************************************************************************
 NOME:        IS_EQUAL
 DESCRIZIONE: <Descrizione procedura>
 ARGOMENTI:   a_<arg1> IN OUT <type> <Descrizione argomento 1>
              a_<arg2> IN OUT <type> <Descrizione argomento 2>
 ECCEZIONI:   nnnnn, <Descrizione eccezione>
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    29/06/2005 MM
******************************************************************************/
   ( p_tipo_accesso_1 IN OUT VARCHAR2, p_progetto_1 IN VARCHAR2
   , p_istanza_1 IN VARCHAR2, p_modulo_1 IN VARCHAR2, p_utente_1 IN VARCHAR2
   , p_tipo_accesso_2 IN OUT VARCHAR2, p_progetto_2 IN VARCHAR2
   , p_istanza_2 IN VARCHAR2, p_modulo_2 IN VARCHAR2, p_utente_2 IN VARCHAR2)
   RETURN NUMBER
   IS /* SLAVE_COPY */
      d_accesso_1 VARCHAR2(200);
      d_accesso_se_1 VARCHAR2(200);
      d_traccia_1 VARCHAR2(200);
      d_giorni_traccia_1 NUMBER;
      d_tentativi_pwd_1 NUMBER;
      d_val_pwd_1 NUMBER;
      d_sleep_1 NUMBER;
      d_single_sign_on_1 VARCHAR2(200);
      d_ldap_1 VARCHAR2(200);
      d_min_lung_pwd_1 NUMBER;
      d_mod_pwd_primo_accesso_1 VARCHAR2(200);
      d_arch_traccia_1          VARCHAR2(2);
      d_disl_traccia_1          VARCHAR2(100);
      d_car_speciali_pwd_1      VARCHAR2(2);
      d_num_obb_pwd_1           VARCHAR2(2);
      d_accesso_2 VARCHAR2(200);
      d_accesso_se_2 VARCHAR2(200);
      d_traccia_2 VARCHAR2(200);
      d_giorni_traccia_2 NUMBER;
      d_tentativi_pwd_2 NUMBER;
      d_val_pwd_2 NUMBER;
      d_sleep_2 NUMBER;
      d_single_sign_on_2 VARCHAR2(200);
      d_ldap_2 VARCHAR2(200);
      d_min_lung_pwd_2 NUMBER;
      d_mod_pwd_primo_accesso_2 VARCHAR2(200);
      d_arch_traccia_2      VARCHAR2(2);
      d_disl_traccia_2      VARCHAR2(100);
      d_car_speciali_pwd_2      VARCHAR2(2);
      d_num_obb_pwd_2           VARCHAR2(2);
     d_return NUMBER(1);
      d_gg_prima_riutilizzo_pwd_1 CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
      d_gg_prima_riutilizzo_pwd_2 CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
   BEGIN
      GET_EFFETTIVE ( p_tipo_accesso_1, p_progetto_1, p_istanza_1, p_modulo_1
                    , p_utente_1, d_accesso_1, d_accesso_se_1, d_traccia_1
                    , d_giorni_traccia_1, d_tentativi_pwd_1, d_val_pwd_1, d_sleep_1
                    , d_single_sign_on_1, d_ldap_1, d_min_lung_pwd_1
                    , d_mod_pwd_primo_accesso_1, d_arch_traccia_1, d_disl_traccia_1
                    , d_car_speciali_pwd_1, d_num_obb_pwd_1,d_gg_prima_riutilizzo_pwd_1);
      GET_EFFETTIVE ( p_tipo_accesso_2, p_progetto_2, p_istanza_2, p_modulo_2
                    , p_utente_2, d_accesso_2, d_accesso_se_2, d_traccia_2
                    , d_giorni_traccia_2, d_tentativi_pwd_2, d_val_pwd_2
                    , d_sleep_2, d_single_sign_on_2, d_ldap_2, d_min_lung_pwd_2
                    , d_mod_pwd_primo_accesso_2, d_arch_traccia_2, d_disl_traccia_2
                    , d_car_speciali_pwd_2, d_num_obb_pwd_2, d_gg_prima_riutilizzo_pwd_2);
      IF NVL(d_accesso_1,'L') <> NVL(d_accesso_2,'L')
      OR NVL(d_accesso_se_1,'NO') <> NVL(d_accesso_se_2,'NO')
      OR NVL(d_traccia_1,'M') <> NVL(d_traccia_2,'M')
      OR NVL(d_giorni_traccia_1,60) <> NVL(d_giorni_traccia_2,60)
      OR NVL(d_tentativi_pwd_1, -1) <> NVL(d_tentativi_pwd_2, -1)
      OR NVL(d_val_pwd_1, -1) <> NVL(d_val_pwd_2, -1)
      OR NVL(d_sleep_1, -1) <> NVL(d_sleep_2, -1)
      OR NVL(d_single_sign_on_1,'SI') <> NVL(d_single_sign_on_2,'SI')
      OR NVL(d_ldap_1,'NO') <> NVL(d_ldap_2,'NO')
      OR NVL(d_min_lung_pwd_1, -1) <> NVL(d_min_lung_pwd_2, -1)
      OR NVL(d_mod_pwd_primo_accesso_1,'NO') <> NVL(d_mod_pwd_primo_accesso_2,'NO')
      OR NVL(d_arch_traccia_1,'NO') <> NVL(d_arch_traccia_2,'NO')
      OR NVL(d_disl_traccia_1,'NO') <> NVL(d_disl_traccia_2,'NO')
      OR NVL(d_car_speciali_pwd_1,'SI') <> NVL(d_car_speciali_pwd_2,'SI')
      OR NVL(d_num_obb_pwd_1,'NO') <> NVL(d_num_obb_pwd_2,'NO')
      OR NVL(d_gg_prima_riutilizzo_pwd_1, -1) <> NVL(d_gg_prima_riutilizzo_pwd_2, -1) THEN
      -- caratteristiche diverse
         d_return := 0;
      ELSE
         d_return := 1;
      END IF;
     RETURN d_return;
   EXCEPTION WHEN OTHERS THEN
      RETURN NULL;
   END IS_EQUAL;
   PROCEDURE VALIDITA_PWD
/******************************************************************************
 NOME:        VALIDITA_PWD.
 DESCRIZIONE: Controlla che non sia stato superato il numero di tentativi di
              accesso ammessi e che la data della password non sia scaduta.
 ARGOMENTI:   p_utente        IN VARCHAR2 utente che accede.
              p_tentativi_pwd IN INTEGER  numero di tentativi di accesso ammessi.
              p_val_pwd       IN INTEGER  giorni di validita della password.
 ECCEZIONI:   20999, Errore bloccante: 'Utente non definito', 'E' stato raggiunto
                     il numero massimo di tentativi ammessi.'
              20101, Warning: 'La data di validita della password e' scaduta.'
 ANNOTAZIONI: chiamata da ACCESSO.REGISTRA_ACCESSO.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    29/11/2005 MM     Prima emissione.
******************************************************************************/
   ( p_utente VARCHAR2
   , p_tentativi_pwd INTEGER
   , p_val_pwd INTEGER)
   IS /* SLAVE_COPY */
      d_num_tentativi INTEGER;
      d_giorni_pwd    INTEGER;
      d_rinnovo_pwd   VARCHAR2(2);
      d_msg           VARCHAR2(2000);
      d_err_msg       INTEGER;
      d_pwd_da_mod    VARCHAR2(2);
   BEGIN
   -- controlla che il numero di tentativi di accesso non superi il massimo.
      BEGIN
         SELECT SYSDATE - data_password, NVL(rinnovo_password,'SI'),numero_tentativi,
                pwd_da_modificare
           INTO d_giorni_pwd, d_rinnovo_pwd, d_num_tentativi, d_pwd_da_mod
           FROM UTENTI
          WHERE Utente = p_utente
         ;
      EXCEPTION WHEN NO_DATA_FOUND THEN
         d_err_msg := -20999;
         d_msg     := 'Utente non definito';
      END;
      IF p_tentativi_pwd IS NOT NULL AND NVL(d_err_msg,0) = 0 AND NVL(d_msg,' ') = ' ' THEN
         IF d_num_tentativi >= p_tentativi_pwd THEN
            d_err_msg := -20999;
            d_msg     := 'E'' stato raggiunto il numero massimo di tentativi ammessi.'
                      ||CHR(10)||'La connessione non e'' piu'' attivabile.'
                      ||CHR(10)||CHR(10)||'Avvisare l'' Amministratore del Sistema';
         END IF;
      END IF;
      -- controlla la validita' della password e se la password deve essere modificata
      -- dall'utente al primo accesso.
      IF (   (p_val_pwd IS NOT NULL AND NVL(d_err_msg,0) = 0)
         OR (NVL(d_pwd_da_mod,'NO') = 'SI')
       )
     AND NVL(d_msg,' ') = ' ' THEN
         IF d_giorni_pwd > p_val_pwd THEN
            d_msg := 'La validita'' della password e'' scaduta.'||CHR(10)||CHR(10);
         ELSIF NVL(d_pwd_da_mod,'NO') = 'SI' THEN
            d_msg := 'La password deve essere modificata prima dei successivi accessi.'||CHR(10)||CHR(10);
         END IF;
         IF d_rinnovo_pwd = 'SI' AND NVL(d_msg,' ') <> ' ' THEN
            d_msg := d_msg||'Modificare la password di connessione al Sistema';
            IF p_tentativi_pwd IS NOT NULL THEN
               d_num_tentativi := p_tentativi_pwd - NVL(d_num_tentativi,0) - 1;
               d_msg := d_msg||'.'||CHR(10)||CHR(10)||'Accessi rimasti: '||d_num_tentativi;
            END IF;
            d_err_msg := -20101;
         ELSIF d_rinnovo_pwd = 'NO' AND NVL(d_msg,' ') <> ' ' THEN
            d_msg     := d_msg||'Avvisare l'' Amministratore del Sistema';
            d_err_msg := -20999;
         END IF;
      END IF;
      IF NVL(d_err_msg,0) <> 0 OR NVL(d_msg,' ') <> ' ' THEN
         RAISE_APPLICATION_ERROR(d_err_msg,d_msg);
      END IF;
   END VALIDITA_PWD;
   PROCEDURE VALIDITA_PWD
/******************************************************************************
 NOME:        VALIDITA_PWD.
 DESCRIZIONE: Controlla che non sia stato superato il numero di tentativi di
              accesso ammessi e che la data della password non sia scaduta.
 ARGOMENTI:   p_utente  IN VARCHAR2 utente che accede.
              p_modulo  IN VARCHAR2 modulo a cui accede.
              p_istanza IN VARCHAR2 istanza a cui accede.
 ECCEZIONI:   20999, Errore bloccante: 'Utente non definito', 'E' stato raggiunto
                     il numero massimo di tentativi ammessi.'
              20101, Warning: 'La data di validita della password e' scaduta.'
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    29/11/2005 MM     Prima emissione.
******************************************************************************/
( p_utente VARCHAR2
, p_modulo VARCHAR2
, p_istanza VARCHAR2)
IS /* SLAVE_COPY */
   d_progetto_modu VARCHAR2(10);
   d_progetto_ista VARCHAR2(10);
   d_num_tentativi INTEGER;
   d_giorni_pwd    INTEGER;
BEGIN
   IF p_istanza IS NULL OR p_modulo IS NULL THEN
      RAISE_APPLICATION_ERROR(-20999, 'Parametri modulo e istanza obbligatori!');
   ELSE
      BEGIN
         SELECT modu.progetto, ista.progetto
           INTO d_progetto_modu, d_progetto_ista
           FROM MODULI modu, ISTANZE ista
          WHERE modu.modulo  = p_modulo
            AND ista.Istanza = p_istanza
         ;
         IF NVL(d_progetto_modu,'xxx') <> NVL(d_progetto_ista,'xxx') THEN
            RAISE_APPLICATION_ERROR(-20999, 'Parametri modulo e istanza obbligatori!');
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20999, 'Modulo ('||p_modulo||') o Istanza ('||p_istanza||') non presenti!');
         WHEN OTHERS THEN
            RAISE;
      END;
   END IF;
   d_num_tentativi := get_tentativi_pwd('D', d_progetto_modu, p_istanza, p_modulo, p_utente);
   d_giorni_pwd := TO_NUMBER(get('validita_password', 'D', d_progetto_modu, p_istanza, p_modulo, p_utente));
   VALIDITA_PWD(p_utente, d_num_tentativi, d_giorni_pwd);
END VALIDITA_PWD;
FUNCTION IS_PASSWORD_VALIDA
/******************************************************************************
 NOME:        IS_PASSWORD_VALIDA
 DESCRIZIONE: Dato un codice utente verifica che la sua password:
              - sia maggiore della lunghezza minima prevista;
              - contenga almeno un numero se previsto;
              - non contenga dei caratteri speciali se non previsto.
 PARAMETRI:   p_utente        IN varchar2 codice utente di cui verificare la
                                          password
 RITORNA:     number:  ritorno della funzione ICRYPT.IS_PASSWORD_UTENTE_VALIDA;
                       cioe':
                       1   = Verifica effettuata con successo.
                       0   = La password non puo' essere nulla.
                      -1   = La password deve essere di almeno 'p_min_length' caratteri.
                      -2   = La password non puo' contenere caratteri speciali.
                      -3   = La password deve contenere almeno un numero.
                      -4   = Non sono passati i giorni necessari al riutilizzo della password.
 ECCEZIONI:   -20999: Utente '<p_utente>' non definito.
 NOTE:        .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
  009 13/07/2007 MM    Creazione.
******************************************************************************/
( p_utente VARCHAR2 )
RETURN NUMBER
IS /* SLAVE_COPY */
   d_return             INTEGER;
   d_num_obb_pwd        INTEGER;
   d_car_speciali_pwd   INTEGER;
   d_min_pwd_length     INTEGER;
   d_validita           INTEGER;
BEGIN
   d_num_obb_pwd        := nvl(IS_NUM_OBB_PWD(p_utente), 0);
   d_car_speciali_pwd   := nvl(IS_CAR_SPECIALI_PWD(p_utente), 1);
   d_min_pwd_length     := nvl(GET_MINPWDLENGTH(p_utente), 0);
   d_validita:= CRYPT.IS_PASSWORD_UTENTE_VALIDA(p_utente, d_min_pwd_length, d_num_obb_pwd, d_car_speciali_pwd);
   if d_validita = 1 then
      d_validita := VERIFICA_PASSWORD_STORICO_OK (p_utente);
   end if;
     return d_validita;
END IS_PASSWORD_VALIDA;
FUNCTION GET_INVALID_PWD_LOG
/******************************************************************************
 NOME:        GESTISCI_PWDLENGTH.
 DESCRIZIONE: Controlla la coerenza della password di ogni utente a cui le
              caratteristiche si applicano (lunghezza, eventuale presenza di
              caratteri speciali e presenza obbligatoria di almeno un numero);
              controlla inoltre che la password non sia 'password'.
              Se cosi non e' e l'utente non ha la possibilita' di rinnovarsi
              autonomamente la password,
                    aggiunge il nominativo ed il codice dell'utente al clob
                    ritornato dalla funzione.
 PARAMETRI:   --.
 RITORNA:     CLOB: elenco degli eventuali utenti con password non coerente
              alle caratteristiche previste ma senza possibilita' di rinnovo
              autonoma.
 ECCEZIONI:   .
 ANNOTAZIONI: per lanciarla da SQL*Plus:
              set long 32000
              set linesize 135
              col GET_INVALID_PWD_LOG format a32000
              select caratteristica_accesso.get_invalid_pwd_log from dual;
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 2    25/05/2006 MM     Prima emissione.
******************************************************************************/
( p_progetto IN VARCHAR2 DEFAULT '%'
, p_istanza IN VARCHAR2 DEFAULT '%'
, p_modulo IN VARCHAR2 DEFAULT '%'
, p_utente IN VARCHAR2 DEFAULT '%')
RETURN CLOB
IS /* SLAVE_COPY */
   d_amount          BINARY_INTEGER := 32767;
   d_clob            CLOB := EMPTY_CLOB();
   d_clob_dep        CLOB := EMPTY_CLOB();
   d_log             VARCHAR2(32767);
   d_num_log         NUMBER := 0;
   d_ver_pwd         NUMBER;
BEGIN
   dbms_lob.createTemporary(d_clob,TRUE,dbms_lob.SESSION);
   FOR c_utente IN (select distinct diac.utente, uten.nominativo, uten.rinnovo_password
                      from utenti uten, diritti_accesso diac, istanze ista
                     where uten.utente = diac.utente
                       and ista.istanza = diac.istanza
                       and uten.tipo_utente = 'U'
                       and diac.utente like p_utente
                       and diac.istanza like p_istanza
                       and diac.modulo like p_modulo
                       and ista.progetto like p_progetto
                  order by nominativo)
   LOOP
      d_ver_pwd := IS_PASSWORD_VALIDA(c_utente.Utente);
      if d_ver_pwd = 1 then -- password valida
         -- verifica che la password non sia 'password'
         BEGIN
              d_ver_pwd := Crypt.verifica_password(c_utente.nominativo,'password');
         EXCEPTION WHEN OTHERS THEN
            d_ver_pwd := 1;
         END;
      else
         d_ver_pwd := 1;
      end if;
      IF d_ver_pwd = 1 THEN
         IF NVL(c_utente.rinnovo_password, 'SI') <> 'SI' THEN
            IF d_num_log = 0 THEN
               d_log := 'Utenti con PASSWORD NON COERENTE con le caratteristiche previste ma SENZA possibilita'' di RINNOVO.'||CHR(13)||CHR(10)||
                        'Si consiglia di modificare e comunicare la nuova password a tali utenti per permettere loro di entrare negli applicativi.'||CHR(13)||CHR(10)||CHR(13)||CHR(10);
               dbms_lob.createTemporary(d_clob_dep,TRUE,dbms_lob.SESSION);
               d_amount := LENGTH(d_log);
               dbms_lob.WRITE(d_clob_dep, d_amount, 1, d_log);
               dbms_lob.APPEND(d_clob, d_clob_dep);
            END IF;
            d_num_log := d_num_log + 1;
            d_log := '- '||c_utente.nominativo||' ('|| c_utente.Utente ||') '||CHR(13)||CHR(10);
            d_amount := LENGTH(d_log);
            dbms_lob.createTemporary(d_clob_dep,TRUE,dbms_lob.SESSION);
            dbms_lob.WRITE(d_clob_dep, d_amount, 1, d_log);
            dbms_lob.APPEND(d_clob,d_clob_dep);
         END IF;
      END IF;
   END LOOP;
   RETURN d_clob;
END GET_INVALID_PWD_LOG;
PROCEDURE del
   ( p_CAAC_ID  IN CARATTERISTICHE_ACCESSO.CAAC_ID%TYPE
   , p_TIPO_ACCESSO IN CARATTERISTICHE_ACCESSO.TIPO_ACCESSO%TYPE DEFAULT NULL
   , p_PROGETTO  IN CARATTERISTICHE_ACCESSO.PROGETTO%TYPE DEFAULT NULL
   , p_ISTANZA  IN CARATTERISTICHE_ACCESSO.ISTANZA%TYPE DEFAULT NULL
   , p_MODULO  IN CARATTERISTICHE_ACCESSO.MODULO%TYPE DEFAULT NULL
   , p_UTENTE IN CARATTERISTICHE_ACCESSO.UTENTE%TYPE DEFAULT NULL
   , p_ACCESSO IN CARATTERISTICHE_ACCESSO.ACCESSO%TYPE  DEFAULT NULL
   , p_ACCESSO_SE  IN CARATTERISTICHE_ACCESSO.ACCESSO_SE%TYPE DEFAULT NULL
   , p_TRACCIA  IN CARATTERISTICHE_ACCESSO.TRACCIA%TYPE DEFAULT NULL
   , p_GIORNI_TRACCIA  IN CARATTERISTICHE_ACCESSO.GIORNI_TRACCIA%TYPE DEFAULT NULL
   , p_TENTATIVI_PASSWORD  IN CARATTERISTICHE_ACCESSO.TENTATIVI_PASSWORD%TYPE DEFAULT NULL
   , p_VALIDITA_PASSWORD  IN CARATTERISTICHE_ACCESSO.VALIDITA_PASSWORD%TYPE DEFAULT NULL
   , p_SINGLE_SIGN_ON  IN CARATTERISTICHE_ACCESSO.SINGLE_SIGN_ON%TYPE  DEFAULT NULL
   , p_SLEEP  IN CARATTERISTICHE_ACCESSO.SLEEP%TYPE DEFAULT NULL
   , p_LDAP  IN CARATTERISTICHE_ACCESSO.LDAP%TYPE  DEFAULT NULL
   , p_MIN_LUNGHEZZA_PWD  IN CARATTERISTICHE_ACCESSO.MIN_LUNGHEZZA_PWD%TYPE  DEFAULT NULL
   , p_MOD_PWD_PRIMO_ACCESSO  IN CARATTERISTICHE_ACCESSO.MOD_PWD_PRIMO_ACCESSO%TYPE  DEFAULT NULL
   , p_ARCHIVIAZIONE_TRACCIA  IN CARATTERISTICHE_ACCESSO.ARCHIVIAZIONE_TRACCIA%TYPE  DEFAULT NULL
   , p_DISLOCAZIONE_TRACCIA  IN CARATTERISTICHE_ACCESSO.DISLOCAZIONE_TRACCIA%TYPE  DEFAULT NULL
   , p_AMMESSI_CAR_SPECIALI_PWD  IN CARATTERISTICHE_ACCESSO.AMMESSI_CAR_SPECIALI_PWD%TYPE DEFAULT NULL
   , p_NUMERI_OBB_PWD  IN CARATTERISTICHE_ACCESSO.NUMERI_OBB_PWD%TYPE DEFAULT NULL
   , p_gg_prima_riutilizzo_pwd IN CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE DEFAULT NULL
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
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
008   21/09/2018 SN    Aggiunto campo gg_prima_riutilizzo_pwd
******************************************************************************/
   d_row_found NUMBER;
BEGIN
   DELETE FROM CARATTERISTICHE_ACCESSO
   WHERE CAAC_ID = p_CAAC_ID
     AND (  p_check_old = 0
         OR p_check_old = 1
            AND ( TIPO_ACCESSO = p_TIPO_ACCESSO OR TIPO_ACCESSO IS NULL AND p_TIPO_ACCESSO IS NULL )
            AND ( PROGETTO = p_PROGETTO OR PROGETTO IS NULL AND p_PROGETTO IS NULL )
            AND ( ISTANZA = p_ISTANZA OR ISTANZA IS NULL AND p_ISTANZA IS NULL )
            AND ( MODULO = p_MODULO OR MODULO IS NULL AND p_MODULO IS NULL )
            AND ( UTENTE = p_UTENTE OR UTENTE IS NULL AND p_UTENTE IS NULL )
            AND ( ACCESSO = p_ACCESSO OR ACCESSO IS NULL AND p_ACCESSO IS NULL )
            AND ( ACCESSO_SE = p_ACCESSO_SE OR ACCESSO_SE IS NULL AND p_ACCESSO_SE IS NULL )
            AND ( TRACCIA = p_TRACCIA OR TRACCIA IS NULL AND p_TRACCIA IS NULL )
            AND ( GIORNI_TRACCIA = p_GIORNI_TRACCIA OR GIORNI_TRACCIA IS NULL AND p_GIORNI_TRACCIA IS NULL )
            AND ( TENTATIVI_PASSWORD = p_TENTATIVI_PASSWORD OR TENTATIVI_PASSWORD IS NULL AND p_TENTATIVI_PASSWORD IS NULL )
            AND ( VALIDITA_PASSWORD = p_VALIDITA_PASSWORD OR VALIDITA_PASSWORD IS NULL AND p_VALIDITA_PASSWORD IS NULL )
            AND ( SINGLE_SIGN_ON = p_SINGLE_SIGN_ON OR SINGLE_SIGN_ON IS NULL AND p_SINGLE_SIGN_ON IS NULL )
            AND ( SLEEP = p_SLEEP OR SLEEP IS NULL AND p_SLEEP IS NULL )
            AND ( LDAP = p_LDAP OR LDAP IS NULL AND p_LDAP IS NULL )
            AND ( MIN_LUNGHEZZA_PWD = p_MIN_LUNGHEZZA_PWD OR MIN_LUNGHEZZA_PWD IS NULL AND p_MIN_LUNGHEZZA_PWD IS NULL )
            AND ( MOD_PWD_PRIMO_ACCESSO = p_MOD_PWD_PRIMO_ACCESSO OR MOD_PWD_PRIMO_ACCESSO IS NULL AND p_MOD_PWD_PRIMO_ACCESSO IS NULL )
            AND ( ARCHIVIAZIONE_TRACCIA = p_ARCHIVIAZIONE_TRACCIA OR ARCHIVIAZIONE_TRACCIA IS NULL AND p_ARCHIVIAZIONE_TRACCIA IS NULL )
            AND ( DISLOCAZIONE_TRACCIA = p_DISLOCAZIONE_TRACCIA OR DISLOCAZIONE_TRACCIA IS NULL AND p_DISLOCAZIONE_TRACCIA IS NULL )
            AND ( AMMESSI_CAR_SPECIALI_PWD = p_AMMESSI_CAR_SPECIALI_PWD OR AMMESSI_CAR_SPECIALI_PWD IS NULL AND p_AMMESSI_CAR_SPECIALI_PWD IS NULL )
            AND ( NUMERI_OBB_PWD = p_NUMERI_OBB_PWD OR NUMERI_OBB_PWD IS NULL AND p_NUMERI_OBB_PWD IS NULL )
         )
   ;
   d_row_found := SQL%ROWCOUNT;
   IF d_row_found < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number, Afc_Error.modified_by_other_user_msg );
   END IF;
END del;
PROCEDURE del
   ( p_TIPO_ACCESSO IN CARATTERISTICHE_ACCESSO.TIPO_ACCESSO%TYPE
   , p_PROGETTO  IN CARATTERISTICHE_ACCESSO.PROGETTO%TYPE
   , p_ISTANZA  IN CARATTERISTICHE_ACCESSO.ISTANZA%TYPE DEFAULT NULL
   , p_MODULO  IN CARATTERISTICHE_ACCESSO.MODULO%TYPE DEFAULT NULL
   , p_UTENTE IN CARATTERISTICHE_ACCESSO.UTENTE%TYPE DEFAULT NULL
   , p_ACCESSO IN CARATTERISTICHE_ACCESSO.ACCESSO%TYPE  DEFAULT NULL
   , p_ACCESSO_SE  IN CARATTERISTICHE_ACCESSO.ACCESSO_SE%TYPE DEFAULT NULL
   , p_TRACCIA  IN CARATTERISTICHE_ACCESSO.TRACCIA%TYPE DEFAULT NULL
   , p_GIORNI_TRACCIA  IN CARATTERISTICHE_ACCESSO.GIORNI_TRACCIA%TYPE DEFAULT NULL
   , p_TENTATIVI_PASSWORD  IN CARATTERISTICHE_ACCESSO.TENTATIVI_PASSWORD%TYPE DEFAULT NULL
   , p_VALIDITA_PASSWORD  IN CARATTERISTICHE_ACCESSO.VALIDITA_PASSWORD%TYPE DEFAULT NULL
   , p_SINGLE_SIGN_ON  IN CARATTERISTICHE_ACCESSO.SINGLE_SIGN_ON%TYPE  DEFAULT NULL
   , p_SLEEP  IN CARATTERISTICHE_ACCESSO.SLEEP%TYPE DEFAULT NULL
   , p_LDAP  IN CARATTERISTICHE_ACCESSO.LDAP%TYPE  DEFAULT NULL
   , p_MIN_LUNGHEZZA_PWD  IN CARATTERISTICHE_ACCESSO.MIN_LUNGHEZZA_PWD%TYPE  DEFAULT NULL
   , p_MOD_PWD_PRIMO_ACCESSO  IN CARATTERISTICHE_ACCESSO.MOD_PWD_PRIMO_ACCESSO%TYPE  DEFAULT NULL
   , p_ARCHIVIAZIONE_TRACCIA  IN CARATTERISTICHE_ACCESSO.ARCHIVIAZIONE_TRACCIA%TYPE  DEFAULT NULL
   , p_DISLOCAZIONE_TRACCIA  IN CARATTERISTICHE_ACCESSO.DISLOCAZIONE_TRACCIA%TYPE  DEFAULT NULL
   , p_AMMESSI_CAR_SPECIALI_PWD  IN CARATTERISTICHE_ACCESSO.AMMESSI_CAR_SPECIALI_PWD%TYPE DEFAULT NULL
   , p_NUMERI_OBB_PWD  IN CARATTERISTICHE_ACCESSO.NUMERI_OBB_PWD%TYPE DEFAULT NULL
      , p_gg_prima_riutilizzo_pwd IN CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE DEFAULT NULL
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
   DELETE FROM CARATTERISTICHE_ACCESSO
   WHERE TIPO_ACCESSO = p_TIPO_ACCESSO
     AND PROGETTO = p_PROGETTO
     AND ( ISTANZA = p_ISTANZA OR ISTANZA IS NULL AND p_ISTANZA IS NULL AND p_TIPO_ACCESSO IN ('P','M'))
     AND ( MODULO = p_MODULO OR MODULO IS NULL AND p_MODULO IS NULL AND p_TIPO_ACCESSO IN ('P','I') )
     AND ( UTENTE = p_UTENTE OR UTENTE IS NULL AND p_UTENTE IS NULL AND p_TIPO_ACCESSO NOT IN ('D','G'))
     AND (  p_check_old = 0
         OR p_check_old = 1
            AND ( ACCESSO = p_ACCESSO OR ACCESSO IS NULL AND p_ACCESSO IS NULL )
            AND ( ACCESSO_SE = p_ACCESSO_SE OR ACCESSO_SE IS NULL AND p_ACCESSO_SE IS NULL )
            AND ( TRACCIA = p_TRACCIA OR TRACCIA IS NULL AND p_TRACCIA IS NULL )
            AND ( GIORNI_TRACCIA = p_GIORNI_TRACCIA OR GIORNI_TRACCIA IS NULL AND p_GIORNI_TRACCIA IS NULL )
            AND ( TENTATIVI_PASSWORD = p_TENTATIVI_PASSWORD OR TENTATIVI_PASSWORD IS NULL AND p_TENTATIVI_PASSWORD IS NULL )
            AND ( VALIDITA_PASSWORD = p_VALIDITA_PASSWORD OR VALIDITA_PASSWORD IS NULL AND p_VALIDITA_PASSWORD IS NULL )
            AND ( SINGLE_SIGN_ON = p_SINGLE_SIGN_ON OR SINGLE_SIGN_ON IS NULL AND p_SINGLE_SIGN_ON IS NULL )
            AND ( SLEEP = p_SLEEP OR SLEEP IS NULL AND p_SLEEP IS NULL )
            AND ( LDAP = p_LDAP OR LDAP IS NULL AND p_LDAP IS NULL )
            AND ( MIN_LUNGHEZZA_PWD = p_MIN_LUNGHEZZA_PWD OR MIN_LUNGHEZZA_PWD IS NULL AND p_MIN_LUNGHEZZA_PWD IS NULL )
            AND ( MOD_PWD_PRIMO_ACCESSO = p_MOD_PWD_PRIMO_ACCESSO OR MOD_PWD_PRIMO_ACCESSO IS NULL AND p_MOD_PWD_PRIMO_ACCESSO IS NULL )
            AND ( ARCHIVIAZIONE_TRACCIA = p_ARCHIVIAZIONE_TRACCIA OR ARCHIVIAZIONE_TRACCIA IS NULL AND p_ARCHIVIAZIONE_TRACCIA IS NULL )
            AND ( DISLOCAZIONE_TRACCIA = p_DISLOCAZIONE_TRACCIA OR DISLOCAZIONE_TRACCIA IS NULL AND p_DISLOCAZIONE_TRACCIA IS NULL )
            AND ( AMMESSI_CAR_SPECIALI_PWD = p_AMMESSI_CAR_SPECIALI_PWD OR AMMESSI_CAR_SPECIALI_PWD IS NULL AND p_AMMESSI_CAR_SPECIALI_PWD IS NULL )
            AND ( NUMERI_OBB_PWD = p_NUMERI_OBB_PWD OR NUMERI_OBB_PWD IS NULL AND p_NUMERI_OBB_PWD IS NULL )
         )
   ;
   d_row_found := SQL%ROWCOUNT;
   IF d_row_found < 1
   THEN
      RAISE_APPLICATION_ERROR ( Afc_Error.modified_by_other_user_number, Afc_Error.modified_by_other_user_msg );
   END IF;
END del;

/******************************************************************************
 NOME:        GET_DIAC_MAX_GG_RIUSO_PWD
 DESCRIZIONE: Elenco dei diritti di accesso dell'utente per cui e' stato
              previsto il valore piu' grande el minimo numero di giorni per riutilizzo della password
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     Elenco dei diritti di accesso.
******************************************************************************/
    FUNCTION GET_DIAC_MAX_GG_RIUSO_PWD /* SLAVE_COPY */
    ( p_utente VARCHAR2 ) RETURN VARCHAR2
       IS /* SLAVE_COPY */
      d_return                VARCHAR2(32000);
      d_max_gg_riuso_pwd        NUMBER(2) := 0;
   BEGIN
      d_max_gg_riuso_pwd := GET_GG_PRIMA_RIUTILIZZO_PWD(p_utente);
      IF NVL(d_max_gg_riuso_pwd,0) > 0 THEN
         d_return := GET_DIAC_CARATTERISTICA(p_utente, 'max_gg_riuso_pwd', d_max_gg_riuso_pwd);
      END IF;
      RETURN d_return;
   EXCEPTION WHEN OTHERS THEN
      RETURN '';
   END GET_DIAC_MAX_GG_RIUSO_PWD;

/******************************************************************************
 NOME:        GET_GG_PRIMA_RIUTILIZZO_PWD
 DESCRIZIONE: Ottiene il valore piu' grande del minimo numero di giorni per riutilizzo della password
 PARAMETRI:   p_utente varchar2 codice utente da verificare.
 RITORNA:     il valore piy grande del minimo numero di giorni per riutilizzo della password
******************************************************************************/
    FUNCTION GET_GG_PRIMA_RIUTILIZZO_PWD /* SLAVE_COPY */
    ( p_utente VARCHAR2
    , p_tipo_utente varchar2 default null ) RETURN NUMBER
       IS /* SLAVE_COPY */
      d_return                NUMBER := null ; --0;
      d_tipo_accesso          VARCHAR2(1) := 'D';
      d_accesso               VARCHAR2(1);
      d_accesso_se            VARCHAR2(2);
      d_traccia               VARCHAR2(1);
      d_giorni_traccia        NUMBER(3);
      d_tentativi_pwd         NUMBER(2);
      d_sleep                 NUMBER(3);
      d_single_sign_on        VARCHAR2(2);
      d_ldap                  VARCHAR2(2);
      d_lung_pwd              NUMBER(2) := 0;
      d_mod_pwd_primo_accesso VARCHAR2(2);
      d_arch_traccia          VARCHAR2(2);
      d_disl_traccia          VARCHAR2(100);
      d_car_speciali_pwd      VARCHAR2(2);
      d_num_obb_pwd           VARCHAR2(2);
      d_gg_prima_riutilizzo_pwd  CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%TYPE;
      d_tipo_utente           varchar2(1) := nvl(p_tipo_utente, 'E');
      d_min_val_pwd           number(3);
   BEGIN
      FOR DIAC IN (SELECT ista.progetto, DIAC.Istanza, DIAC.modulo, DIAC.Utente
                     FROM ISTANZE ista, DIRITTI_ACCESSO DIAC
                    WHERE DIAC.Utente  = p_utente
                      AND ista.Istanza = DIAC.Istanza)
      LOOP
         if d_tipo_utente = 'E' then
            d_tipo_utente := utente.GET_TIPO_UTENTE(p_utente);
         end if;
         if d_tipo_utente = 'U' then
            d_tipo_accesso := 'D';
         else
            d_tipo_accesso := 'G';
         end if;
         get_effettive ( d_tipo_accesso, DIAC.progetto, DIAC.Istanza
                       , DIAC.modulo, DIAC.Utente, d_accesso, d_accesso_se
                       , d_traccia, d_giorni_traccia, d_tentativi_pwd
                       , d_min_val_pwd, d_sleep, d_single_sign_on, d_ldap
                       , d_lung_pwd, d_mod_pwd_primo_accesso, d_arch_traccia
                       , d_disl_traccia, d_car_speciali_pwd, d_num_obb_pwd, d_gg_prima_riutilizzo_pwd);
         IF d_gg_prima_riutilizzo_pwd is not null and d_gg_prima_riutilizzo_pwd > nvl(d_return,0) THEN
            d_return := d_gg_prima_riutilizzo_pwd;
         END IF;
      END LOOP;
      RETURN d_return;
   END GET_GG_PRIMA_RIUTILIZZO_PWD;

 FUNCTION VERIFICA_PASSWORD_STORICO_OK
/******************************************************************************
 NOME:        VERIFICA_PASSWORD_STORICO_OK.
 DESCRIZIONE: dati un nominativo utente e la password, verifica che la
              password passata non sia stata già usata dallo stesso utente
              prima dei giorni previsti.
 PARAMETRI:   p_nominativo IN varchar2: nominativo utente.
              p_password   IN varchar2: password utente criptata.
 RITORNA:     number: 1 = verifica effettuata con successo.
                      -4 = verifica fallita.
 ECCEZIONI:   -20999: Utente '<p_nominativo>' non definito.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    16/10/2018 SNeg    Prima emissione
 12   02/03/2020 SNeg  Considerare la password già criptata Feature #40748
  013 12/02/2021 SN   Allargare il campo per la gestione della password con i nuovi algoritmi  Bug #48221
******************************************************************************/
   ( p_utente IN VARCHAR2
   , p_password IN VARCHAR2 default NULL
   )
      RETURN NUMBER
   IS
      d_password   utenti.password%TYPE; -- rev.13
      d_actual_uten_pwd   utenti.password%TYPE; -- rev.13
      d_uten_pwd   utenti.password%TYPE; -- rev.13
      d_utente     VARCHAR2 (8);
      d_return     NUMBER (1);
      d_new_password   utenti.password%TYPE; -- rev.13
      d_max_gg_riutilizzo_pwd number;
      d_usata number := 0;
      s_key_auth   VARCHAR2 (512) := 'PRODUCTS/AUTHENTICATION';
   BEGIN

--     raise_application_error (-20999, ' ci sono');
      BEGIN
         SELECT utente, password
           INTO d_utente, d_actual_uten_pwd
           FROM UTENTI
          WHERE utente = p_utente;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE_APPLICATION_ERROR (-20999
                                   ,    'Utente '''
                                     || p_utente
                                     || ''' non definito.'
                                    );
         WHEN OTHERS
         THEN
            RAISE;
      END;

      d_password := nvl(p_password, d_actual_uten_pwd) ; -- la ricavo dal db non ho modo di saperla p_password;

     d_max_gg_riutilizzo_pwd :=  caratteristica_accesso.GET_GG_PRIMA_RIUTILIZZO_PWD(d_utente);
     dbms_output.put_line('gg_prima_riuso' || d_max_gg_riutilizzo_pwd ||'.' ||d_utente||'.' ||d_new_password ||'.' || d_max_gg_riutilizzo_pwd );
     -- verifica se password usata in passato
     select count(1)
       into d_usata
       from password_storico
      where utente = d_utente
        and operazione in ('I', 'AI')
        and password = d_new_password
        and d_new_password is not null
        and sysdate - data_password < d_max_gg_riutilizzo_pwd;

     IF d_usata = 0
      THEN
         d_return := 1;
      ELSE
         d_return := -4;
      END IF;

      RETURN d_return;
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END VERIFICA_PASSWORD_STORICO_OK;

END Caratteristica_Accesso;
/

