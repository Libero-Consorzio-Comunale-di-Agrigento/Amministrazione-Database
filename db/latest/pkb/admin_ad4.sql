--liquibase formatted sql

--changeset mturra:201901301231_106 runOnChange:true stripComments:false endDelimiter:/

CREATE OR REPLACE PACKAGE BODY Admin_Ad4
IS
/******************************************************************************
 NOME:        Admin_Ad4
 DESCRIZIONE: Funzioni di amministrazione.
 ANNOTAZIONI: .
REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Creazione.
 001  02/07/2007 MM     Creazione procedure pubbliche:
                        CREATE_SYNONYM, CREATE_SYNONYMS,
                        DROP_SYNONYM, DROP_SYNONYMS,
                        CREA_SYN_GRANT_AD4_SECONDARI,
                        DROP_SYN_GRANT_AD4_SECONDARI.
                        Creazione procedure private:
                        GESTIONE_SINONIMI_GRANT, GRANT_TO_REVOKE_FROM_ALL
                        Modifiche a:
                        CREATE_PUBLIC_SYNONYM, CREATE_PUBLIC_SYNONYMS,
                        DROP_PUBLIC_SYNONYM, DROP_PUBLIC_SYNONYMS,
                        EXECUTE_GRANT_OR_REVOKE, GRANT_TO, GRANT_TO_ALL,
                        REVOKE_FROM, REVOKE_FROM_ALL.
 002  30/10/2008 SN     Creazione procedure per aggiornamento slave
 003  28/12/2009 SNeg   Gestione anche vista INCARICHI
 004  05/02/2010 Sneg   Aggiunta creazione sinonimo per comuni_totali_tpk
 005  23/02/2012 SN     Possibilita di killare tutte le sessioni di una istanza
 006  13/11/2017 SN     Grant anche x utenti_gruppi e PAESI_TERRITORI_AG_ENTRATE
 007  05/06/2018 SN     Nuovi oggetti per le grant
 008  15/03/2019 SN     Bug #30638 Non creare sinonimi a oggetti standard Oracle
 009  12/11/2019 SNeg   Gestire solo le grant di oggetti esistenti in ad4 Feature #38297
 010  04/12/2019 SNeg   Gestire la max x trovare la max lunghezza da usare
 011  10/02/2020 SN     Distribuzione tabella ASSISTENTE_VIRTUALE e VISTA_COMUNI_STORICI Feature #40511
 012  25/02/2020 SN     Distribuzione ASSISTENTE_VIRTUALE_PKG Feature #40511
 013  22/10/2020 SN     Nel package admin_ad4 creare anche la procedure grant_to #45527
 014  01/12/2020 SN     Nel package admin_ad4 gestire anche gli oggetti relativi alla privacy #46516
 015  10/12/2020 SN     Nel package admin_ad4 gestire anche schedula_rigenera_so #46649
 016  10/02/2021 SN     Per errore la grant sulla tabella enti non è effettuata #48167
******************************************************************************/
   s_revisione_body   constant AFC.t_revision := '016';
--   TYPE b_object_rec IS RECORD (
--      object_name   ALL_OBJECTS.object_name%type
--   );
--   TYPE b_object_tab IS TABLE OF b_object_rec
--      INDEX BY BINARY_INTEGER;
--   b_index                     BINARY_INTEGER := 0;
--   TabTVDB                     b_object_tab;
--   TabPDB                      b_object_tab;
--   TabTVCM                     b_object_tab;
--   TabPCM                      b_object_tab;
--   TabTVBS                     b_object_tab;
--   TabPBS                      b_object_tab;
--   TabTVASL                    b_object_tab;
--   TabPXDK                     b_object_tab;
--   TabAS4                      b_object_tab;
   function versione
      return varchar2
   is
/******************************************************************************
 NOME:        versione
 DESCRIZIONE: Versione e revisione di distribuzione del package.
 RITORNA:     varchar2 stringa contenente versione e revisione.
 NOTE:        Primo numero  : versione compatibilita' del Package.
              Secondo numero: revisione del Package specification.
              Terzo numero  : revisione del Package body.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
 001  02/07/2007 MM     Introduzione nuovi standard di versione.
******************************************************************************/
   begin
      return AFC.version (s_revisione, s_revisione_body);
   end versione;
   PROCEDURE EXECUTE_GRANT_OR_REVOKE
/******************************************************************************
 NOME:        EXECUTE_GRANT_OR_REVOKE
 DESCRIZIONE:
 ARGOMENTI:   --
 NOTE:        --.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
 001  02/07/2007 MM     Gestione errori ORA-01917, ORA-01918.
******************************************************************************/
   (
      p_execute_grant_or_revoke   IN       VARCHAR2
    , p_user                      IN       VARCHAR2
    , p_what                      IN       VARCHAR2
    , p_error                     IN OUT   VARCHAR2
    , p_privilege                 IN       VARCHAR2 DEFAULT 'all'
    , p_owner                     IN       VARCHAR2 default USER
   )
   IS
      d_what      all_objects.OBJECT_NAME%type := upper(p_what);
      d_to_from   VARCHAR2 (200);
      d_owner     ALL_SYNONYMS.owner%type := nvl(p_owner, USER);
      d_statement VARCHAR2 (1000);
   BEGIN
--dbms_output.put_line('sys_context '||sys_context('USERENV','CURRENT_USER'));
      IF upper(d_owner) = sys_context('USERENV','CURRENT_USER')
      THEN
         BEGIN
            IF p_execute_grant_or_revoke = 'grant'
            THEN
               d_to_from := ' to ' || p_user || ' with grant option';
            ELSE
               d_to_from := ' from ' || p_user;
            END IF;
            d_statement := p_execute_grant_or_revoke
                        || ' '
                        || p_privilege
                        || ' on '
                        || d_owner
                        ||'.'
                        || d_what
                        || d_to_from;
--dbms_output.put_line('EXECUTE_GRANT_OR_REVOKE: '||d_statement);
            Afc.SQL_EXECUTE ( d_statement );
         EXCEPTION
            WHEN OTHERS
            THEN
               IF    SQLCODE = -1927
                  OR (SQLCODE in (-1917, -1918) AND INSTR (p_error, SQLERRM) > 0)
                  OR (SQLCODE in (-942, -4042) AND p_execute_grant_or_revoke = 'revoke')
               THEN
                  -- ORA-01927: original grantor must REVOKE privileges
                  -- ORA-01917: user or role does not exist
                  -- ORA-01918: user does not exist
                  -- ORA-00942: table or view does not exist
                  -- ORA-04042: procedure, function, package, or package body does not exist
                  NULL;
               ELSE
                  p_error :=
                        p_error
                     || CHR (10)
                     || '('
                     || p_user
                     || ') '
                     || ' '
                     || d_statement
                     || ': '
                     || SQLERRM;
               END IF;
         END;
         BEGIN
            select DISTINCT p_what||'_SLAVE'
              into d_what
              from all_objects
             where object_name = p_what||'_SLAVE'
               and owner = d_owner
            ;
            if user = 'AD4' and p_what = 'UTENTI' then
               d_what := 'UTENTI_VIEW';
            end if;
            execute_grant_or_revoke(p_execute_grant_or_revoke, p_user, d_what, p_error, p_privilege, p_owner);
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               null;
         END;
      ELSE  -- d_owner <> USER.
         d_statement := 'begin '||
                               d_owner||'.admin_ad4.execute_grant_or_revoke(:p_execute_grant_or_revoke, :p_user, :p_what, :p_error, :p_privilege, :p_owner); '||
                        'end;';
--dbms_output.put_line('EXECUTE_GRANT_OR_REVOKE: '||d_statement);
         execute immediate d_statement
                     using IN p_execute_grant_or_revoke, IN p_user, IN d_what, IN OUT p_error, IN p_privilege, IN p_owner;
      END IF;
   END EXECUTE_GRANT_OR_REVOKE;
   PROCEDURE EXECUTE_GRANT
/******************************************************************************
 NOME:        EXECUTE_GRANT
 DESCRIZIONE:
 ARGOMENTI:   --
 NOTE:        --.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
******************************************************************************/
   (
      p_user        IN       VARCHAR2
    , p_what        IN       VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN       VARCHAR2 DEFAULT 'all'
    , p_error       IN OUT   VARCHAR2
    , p_owner       IN       VARCHAR2 default USER
   )
   IS
   BEGIN
      EXECUTE_GRANT_OR_REVOKE (p_execute_grant_or_revoke => 'grant', p_user => p_user, p_what => p_what, p_privilege => p_privilege, p_error => p_error, p_owner => p_owner);
   END EXECUTE_GRANT;
   PROCEDURE REVOKE_GRANT
/******************************************************************************
 NOME:        REVOKE_GRANT
 DESCRIZIONE:
 ARGOMENTI:   --
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
******************************************************************************/
   (
      p_user        IN       VARCHAR2
    , p_what        IN       VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN       VARCHAR2 DEFAULT 'all'
    , p_error       IN OUT   VARCHAR2
    , p_owner       IN       VARCHAR2 default USER
   )
   IS
   BEGIN
      EXECUTE_GRANT_OR_REVOKE (p_execute_grant_or_revoke => 'revoke', p_user => p_user, p_what => p_what, p_privilege => p_privilege
                             , p_error => p_error, p_owner => p_owner);
   END REVOKE_GRANT;
   procedure gestione_sinonimo_grant
/******************************************************************************
 NOME:        GESTIONE_SINONIMO_GRANT
 DESCRIZIONE: Droppa, crea il sinonimo all'oggetto, assegna, revoca le
              grant ad esso.
 ARGOMENTI:   p_drop_create:  C: Create
                              D: Drop
                              DC / CD: Droppa e crea
                              G: Grant
                              R: Revoke
                              GR / RG: Revoca e Assegna
              p_user:         utente a cui applicare l'operazione
              p_what:         DB:  oggetti di base
                              CM:  gestione Comuni
                              BS:  gestione Banche e Sportelli
                              ASL: gestione ASL
                              ALL: tutti gli oggetti
              p_synonym_name: nome del sinonimo
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  02/07/2007 MM     Prima emissione.
******************************************************************************/
   (
      p_drop_create   IN       VARCHAR2
    , p_user          in       varchar2
    , p_object        IN       VARCHAR2
    , p_synonym_name  IN       VARCHAR2
    , p_privilege     IN       VARCHAR2 DEFAULT 'ALL'
    , p_error         IN OUT   varchar2
    , p_owner                  VARCHAR2 default USER
   )
   IS
      d_privilege   VARCHAR2 (30)    := nvl (p_privilege, 'ALL');
      d_error       VARCHAR2 (32767);
   BEGIN
      if instr (p_drop_create, 'D') > 0
      then
         DROP_SYNONYM (p_user => p_user, p_object => p_object, p_error => d_error, p_owner => p_owner);
      end if;
      if instr (p_drop_create, 'C') > 0
      then
         CREATE_SYNONYM (p_user => p_user, p_synonym_name => p_synonym_name, p_object => p_object, p_error => d_error, p_owner => p_owner);
      end if;
      if instr (p_drop_create, 'R') > 0
      then
         REVOKE_GRANT (p_user => p_user, p_what => p_object, p_privilege => d_privilege, p_error => d_error, p_owner => p_owner);
      end if;
      if instr (p_drop_create, 'G') > 0
      then
         EXECUTE_GRANT (p_user => p_user, p_what => p_object, p_privilege => d_privilege, p_error => d_error, p_owner => p_owner);
      end if;
      p_error := p_error||d_error;
   end gestione_sinonimo_grant;
   PROCEDURE GESTIONE_SINONIMI_GRANT
/******************************************************************************
 NOME:        GESTIONE_SINONIMI_GRANT
 DESCRIZIONE: Droppa, crea tutti i sinonimi agli oggetti, assegna, revoca le
              grant ad essi.
 ARGOMENTI:   p_user:         utente a cui applicare l'operazione
              p_what:         DB:  oggetti di base
                              CM:  gestione Comuni
                              BS:  gestione Banche e Sportelli
                              ASL: gestione ASL
                              ALL: tutti gli oggetti
              p_drop_create:  C: Create
                              D: Drop
                              DC / CD: Droppa e crea
                              G: Grant
                              R: Revoke
                              GR / RG: Revoca e Assegna
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  02/07/2007 MM     Prima emissione.
******************************************************************************/
   (
      p_drop_create   IN       VARCHAR2
    , p_user          in       varchar2
    , p_what          IN       VARCHAR2
    , p_privilege     IN       VARCHAR2 DEFAULT 'ALL'
    , p_error         IN OUT   varchar2
    , p_owner                  VARCHAR2 default USER
   )
   IS
      d_error       VARCHAR2 (32767);
   BEGIN
      IF p_what NOT IN ('DB', 'CM', 'BS', 'ASL', 'ALL', 'XDK')
      THEN
         gestione_sinonimo_grant(p_drop_create => p_drop_create, p_user => p_user, p_object => p_what, p_synonym_name => '', p_privilege => p_privilege, p_error => d_error, p_owner => p_owner);
      ELSE
         IF p_what IN ('DB', 'ALL')
         THEN
-------------------------------------------------------------------
--         Droppa e/o rea sinonimi degli oggetti di base
-------------------------------------------------------------------
            -- TABLE - VIEW
            FOR j IN NVL (TabTVDB.FIRST, 1) .. NVL (TabTVDB.LAST, 0)
            LOOP
               gestione_sinonimo_grant(p_drop_create => p_drop_create, p_user => p_user, p_object => TabTVDB (j).object_name, p_synonym_name => '', p_privilege => p_privilege, p_error => d_error, p_owner => p_owner);
            END LOOP;
            -- FUNCTION / PACKAGE
            FOR j IN NVL (TabPDB.FIRST, 1) .. NVL (TabPDB.LAST, 0)
            LOOP
               gestione_sinonimo_grant(p_drop_create, p_user, TabPDB (j).object_name, '', 'execute', d_error, p_owner);
            END LOOP;
            IF upper(p_user) in ('A00', 'SI') AND UPPER(p_owner) = 'AD4' THEN
               gestione_sinonimo_grant(p_drop_create => 'G', p_user => p_user, p_object => 'SI4AU', p_synonym_name => '', p_privilege => 'execute', p_error => d_error, p_owner => p_owner);
            END IF;
         END IF;
         IF p_what IN ('CM', 'ALL')
         THEN
-------------------------------------------------------------------
--     Droppa e/o crea sinonimi degli oggetti gestione COMUNI
-------------------------------------------------------------------
            -- TABLE - VIEW
            FOR j IN NVL (TabTVCM.FIRST, 1) .. NVL (TabTVCM.LAST, 0)
            LOOP
               gestione_sinonimo_grant(p_drop_create, p_user, TabTVCM (j).object_name, '', p_privilege, d_error, p_owner);
            END LOOP;
            -- FUNCTION / PACKAGE
            FOR j IN NVL (TabPCM.FIRST, 1) .. NVL (TabPCM.LAST, 0)
            LOOP
               gestione_sinonimo_grant(p_drop_create, p_user, TabPCM (j).object_name, '', 'execute', d_error, p_owner);
            END LOOP;
         END IF;
         IF p_what IN ('BS', 'ALL')
         THEN
-------------------------------------------------------------------
--  Droppa e/o crea sinonimi oggetti gestione BANCHE e SPORTELLI
-------------------------------------------------------------------
            -- TABLE - VIEW
            FOR j IN NVL (TabTVBS.FIRST, 1) .. NVL (TabTVBS.LAST, 0)
            LOOP
               gestione_sinonimo_grant(p_drop_create, p_user, TabTVBS (j).object_name, '', p_privilege, d_error, p_owner);
            END LOOP;
            -- FUNCTION / PACKAGE
            FOR j IN NVL (TabPBS.FIRST, 1) .. NVL (TabPBS.LAST, 0)
            LOOP
               gestione_sinonimo_grant(p_drop_create, p_user, TabPBS (j).object_name, '', 'execute', d_error, p_owner);
            END LOOP;
         END IF;
         IF p_what IN ('ASL', 'ALL')
         THEN
-------------------------------------------------------------------
--        Droppa e/o crea sinonimi oggetti gestione ASL
-------------------------------------------------------------------
            -- TABLE - VIEW
            FOR j IN NVL (TabTVASL.FIRST, 1) .. NVL (TabTVASL.LAST, 0)
            LOOP
               gestione_sinonimo_grant(p_drop_create, p_user, TabTVASL (j).object_name, '', p_privilege, d_error, p_owner);
            END LOOP;
         END IF;
         IF p_what IN ('XDK', 'ALL') AND UPPER(p_owner) = USER
         THEN
-------------------------------------------------------------------
--        Droppa e/o crea sinonimi oggetti XDK
-------------------------------------------------------------------
            -- FUNCTION / PACKAGE
            FOR j IN NVL (TabPXDK.FIRST, 1) .. NVL (TabPXDK.LAST, 0)
            LOOP
               gestione_sinonimo_grant(p_drop_create, p_user, TabPXDK (j).object_name, '', 'execute', d_error, p_owner);
            END LOOP;
         END IF;
      END IF;
      declare
      num number := length(p_error);
      v_err varchar2(32767) := substr(d_error,1,2000);
      begin
      if length(p_error) + length( d_error) > 32767 then
      raise_application_error(-20999,'Errore:' || p_error||d_error);
      end if;
      null;
      end;
      p_error := p_error||d_error;
   END GESTIONE_SINONIMI_GRANT;
   FUNCTION GRANT_TO
/******************************************************************************
 NOME:        GRANT_TO
 DESCRIZIONE: Assegna le grant all'utente passato.
 PARAMETRI:   p_user      utente oracle a cui devono essere assegnate le grant.
              p_what      DB:  oggetti di base
                          CM:  gestione Comuni
                          BS:  gestione Banche e Sportelli
                          ASL: gestione ASL
                          ALL: tutti gli oggetti
                          nome singolo oggetto
                          default 'ALL'.
              p_privilege privilegio da attribuire all'utente p_user per
                          l'accesso alle tabelle (per package, procedure e
                          function viene dato sempre execute).
                          default 'all'
 RITORNA:     stringa contenente la concatenazione di eventuali errori.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
 001  02/07/2007 MM     Lancio GESTIONE_SINONIMI_GRANT
******************************************************************************/
   (
      p_user        IN   VARCHAR2
    , p_what        IN   VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN   VARCHAR2 DEFAULT 'all'
    , p_owner       IN   VARCHAR2 default USER
   )
      RETURN VARCHAR2
   IS
      d_errore   VARCHAR2 (32767);
   BEGIN
      GESTIONE_SINONIMI_GRANT ('G', p_user, p_what, p_privilege, d_errore, p_owner);
      RETURN d_errore;
   END GRANT_TO;

    PROCEDURE GRANT_TO
/******************************************************************************
 NOME:        GRANT_TO
 DESCRIZIONE: Assegna le grant all'utente passato.
 PARAMETRI:   p_user      utente oracle a cui devono essere assegnate le grant.
              p_what      DB:  oggetti di base
                          CM:  gestione Comuni
                          BS:  gestione Banche e Sportelli
                          ASL: gestione ASL
                          ALL: tutti gli oggetti
                          nome singolo oggetto
                          default 'ALL'.
              p_privilege privilegio da attribuire all'utente p_user per
                          l'accesso alle tabelle (per package, procedure e
                          function viene dato sempre execute).
                          default 'all'
 RITORNA:     stringa contenente la concatenazione di eventuali errori.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 013  22/10/2020 SNeg     Prima emissione.
******************************************************************************/
   (
      p_user        IN   VARCHAR2
    , p_what        IN   VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN   VARCHAR2 DEFAULT 'all'
    , p_owner       IN   VARCHAR2 default USER
   )
   IS
      d_errore   VARCHAR2 (32767);
   BEGIN
     d_errore := Grant_to (
                                      p_user
                                    , p_what
                                    , p_privilege
                                    , p_owner
                                   );
     IF d_errore IS NOT NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999, d_errore);
      END IF;
  END;

   PROCEDURE GRANT_TO_REVOKE_FROM_ALL
/******************************************************************************
 NOME:        GRANT_TO_REVOKE_FROM_ALL
 DESCRIZIONE: Assegna le grant a tutti gli utenti oracle per cui esiste almeno
              un record nella tabella ISTANZE.
 ARGOMENTI:   p_grant_or_revoke: G
                                 R
                                 RG / GR
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  02/07/2007 MM     Prima emissione.
******************************************************************************/
   (
      p_grant_or_revoke IN VARCHAR2
    , p_what            IN   VARCHAR2 DEFAULT 'ALL'
    , p_privilege       IN   VARCHAR2 DEFAULT 'all'
    , p_owner           IN   VARCHAR2 default USER
   )
   IS
      d_errore   VARCHAR2 (32767);
      d_temp     VARCHAR2 (32767);
   BEGIN
      IF p_what <> 'XDK'
      THEN
         FOR c_grantee IN c_grant_users('ALL', p_owner)
         LOOP
            if instr(p_grant_or_revoke, 'R') > 0 then
               d_temp := REVOKE_FROM (c_grantee.user_oracle, p_what, p_privilege, p_owner);
            end if;
            if instr(p_grant_or_revoke, 'G') > 0 then
               d_temp := GRANT_TO (c_grantee.user_oracle, p_what, p_privilege, p_owner);
            end if;
            if d_temp is not null
            then
            -- ORA-00942: table or view does not exists
            -- ORA-04042:  procedure, function, package, or package body does not exist
               if (  (instr(d_temp, 'ORA-00942: ') = 0 and instr(d_temp, 'ORA-04042: ') = 0)
                     or (instr(d_temp, 'ORA-00942: ') > 0 and instr(nvl(d_errore, ' '), 'ORA-00942: ') = 0)
                     or (instr(d_temp, 'ORA-04042: ') > 0 and instr(nvl(d_errore, ' '), 'ORA-04042: ') = 0)
                   )
               and length(nvl(d_errore,0)) + length(d_temp) + 1 <= 32767
               THEN
                  d_errore := d_errore || d_temp || chr (10);
               else
                  if substr(d_errore, -4) <> '...'
                  then
                     d_errore := d_errore || '...';
                  end if;
               end if;
            end if;
         END LOOP;
         NULL;
      END IF;
      IF p_what IN ('ALL', 'XDK')
      THEN
         if instr(p_grant_or_revoke, 'R') > 0 then
            d_temp := REVOKE_FROM ('public', 'XDK', 'execute');
         end if;
         if instr(p_grant_or_revoke, 'G') > 0 then
            d_temp := GRANT_TO ('public', 'XDK', 'execute');
         end if;
         if d_temp is not null
         then
            if (  instr(d_temp, 'ORA-00942: ') = 0
               or (instr(d_temp, 'ORA-00942: ') > 0 and instr(nvl(d_errore, ' '), 'ORA-00942: ') = 0)
               )
             and length(nvl(d_errore,0)) + length(d_temp) + 1 <= 32767
            THEN
               d_errore := d_errore || d_temp || chr (10);
            else
               if substr(d_errore, -4) <> '...'
               then
                  d_errore := d_errore || '...';
               end if;
            end if;
         end if;
      END IF;
      IF d_errore IS NOT NULL
      THEN
         RAISE_APPLICATION_ERROR (-20999, d_errore);
      END IF;
   END GRANT_TO_REVOKE_FROM_ALL;
   PROCEDURE GRANT_TO_ALL
/******************************************************************************
 NOME:        GRANT_TO_ALL
 DESCRIZIONE: Assegna le grant a tutti gli utenti oracle per cui esiste almeno
              un record nella tabella ISTANZE.
 ARGOMENTI:   --
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
 001  02/07/2007 MM     Lancio GRANT_TO_REVOKE_FROM_ALL.
******************************************************************************/
   (
      p_what        IN   VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN   VARCHAR2 DEFAULT 'all'
    , p_owner       IN   VARCHAR2 default USER
   )
   IS
   BEGIN
      GRANT_TO_REVOKE_FROM_ALL('G', p_what, p_privilege, p_owner);
   END GRANT_TO_ALL;
   FUNCTION REVOKE_FROM
/******************************************************************************
 NOME:        REVOKE_FROM
 DESCRIZIONE: Revoca le grant all'utente passato.
 PARAMETRI:   p_user      utente oracle a cui devono essere assegnate le grant.
              p_what      DB:  oggetti di base
                          CM:  gestione Comuni
                          BS:  gestione Banche e Sportelli
                          ASL: gestione ASL
                          ALL: tutti gli oggetti
                          nome singolo oggetto
                          default 'ALL'.
              p_privilege privilegio da revocare all'utente p_user per
                          l'accesso alle tabelle (per package, procedure e
                          function viene dato sempre execute).
                          default 'all'
 RITORNA:     stringa contenente la concatenazione di eventuali errori.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
 001  02/07/2007 MM     Lancio GESTIONE_SINONIMI_GRANT.
******************************************************************************/
   (
      p_user        IN   VARCHAR2
    , p_what        IN   VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN   VARCHAR2 DEFAULT 'all'
    , p_owner       IN   VARCHAR2 default USER
   )
      RETURN VARCHAR2
   IS
      d_errore   VARCHAR2 (32767);
   BEGIN
      GESTIONE_SINONIMI_GRANT ('R', p_user, p_what, p_privilege, d_errore, p_owner);
      RETURN d_errore;
   END REVOKE_FROM;
   PROCEDURE REVOKE_FROM_ALL
/******************************************************************************
 NOME:        REVOKE_FROM_ALL
 DESCRIZIONE: revoca le grant a tutti gli utenti oracle per cui esiste almeno
              un record nella tabella ISTANZE.
 ARGOMENTI:   p_what      DB:  oggetti di base
                          CM:  gestione Comuni
                          BS:  gestione Banche e Sportelli
                          ASL: gestione ASL
                          ALL: tutti gli oggetti
                          nome singolo oggetto
              p_privilege privilegio da revocare agli utenti per
                          l'accesso alle tabelle (per package, procedure e
                          function viene dato sempre execute).
                          default 'all'
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
 001  02/07/2007 MM     Lancio GRANT_TO_REVOKE_FROM_ALL.
******************************************************************************/
   (
      p_what        IN   VARCHAR2 DEFAULT 'ALL'
    , p_privilege   IN   VARCHAR2 DEFAULT 'all'
    , p_owner       IN   VARCHAR2 default USER
   )
   IS
   BEGIN
      GRANT_TO_REVOKE_FROM_ALL('R', p_what, p_privilege, p_owner);
   END REVOKE_FROM_ALL;
   PROCEDURE CREATE_SYNONYM
/******************************************************************************
 NOME:        CREATE_SYNONYM
 DESCRIZIONE: Crea il sinonimo per l'oggetto dato e ritorna eventuali
              errori in p_error.
              Se il sinonimo esiste gia'
                 se corrisponde (stesso oggetto e stesso proprietario),
                     non fa nulla,
                 altrimenti
                     droppa il sinonimo e lo ricrea.
 ARGOMENTI:   p_user:         utente in cui creare il sinonimo
              p_synonym_name: nome da assegnare al sinonimo
              p_object:       oggetto di cui fare il sinonimo
              p_error:        eventuale errore in creazione sinonimo.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  02/07/2007 MM     Prima emissione.
 010  04/12/2019 SNeg   Gestire la max x trovare la max lunghezza da usare
******************************************************************************/
   (
      p_user                    VARCHAR2
    , p_synonym_name            VARCHAR2
    , p_object                  VARCHAR2
    , p_error          IN OUT   VARCHAR2
    , p_owner                   VARCHAR2 default USER
   )
   IS
      d_esiste         INTEGER;
      d_statement      VARCHAR2 (4000);
      d_table_owner    ALL_SYNONYMS.table_owner%type;
      d_table_name     ALL_SYNONYMS.table_name%type;
      d_synonym_name   VARCHAR2(100);
      d_object         ALL_OBJECTS.object_name%type := upper(p_object);
   BEGIN
      if d_object is not null
      then
         d_synonym_name := p_synonym_name;
         if d_synonym_name is null
         then
            IF     SUBSTR (UPPER (p_object), 1, 4) NOT IN ('AD4_', 'DBMS')
               AND SUBSTR (UPPER (p_object), 1, 3) NOT IN ('XML', 'XSL')
            THEN
               d_synonym_name := 'AD4_';
            END IF;
            d_synonym_name := d_synonym_name || d_object;
         end if;
         DECLARE
            d_length integer;
            d_suffisso varchar2(4);
         BEGIN
            select max(data_length) -- rev. 10
              into d_length
              from all_tab_columns
             where table_name = 'ALL_SYNONYMS'
               and column_name = 'SYNONYM_NAME'
            ;
            IF length(d_synonym_name) > d_length THEN
               d_suffisso := substr(d_synonym_name, -4);
               d_length := LENGTH(d_synonym_name) - d_length;
               IF d_suffisso not in ('_TPK', '_PKG') THEN
                  d_synonym_name := substr(d_synonym_name, 1, length(d_synonym_name) - d_length);
               ELSE
                  d_synonym_name := substr(d_synonym_name, 1, length(d_synonym_name) - 4 - d_length)||d_suffisso;
               END IF;
            END IF;
         END;
         IF upper (p_user) || '.' || UPPER (d_synonym_name) <>
            UPPER (p_owner) || '.' || d_object
         THEN
            d_statement := 'create or replace ';
            if nvl (upper (p_user), 'PUBLIC') = 'PUBLIC'
            then
               d_statement := d_statement || 'PUBLIC synonym ';
               BEGIN
                  select DISTINCT p_object||'_SLAVE'
                    into d_object
                    from all_objects
                   where object_name = p_object||'_SLAVE'
                     and owner = p_owner
                  ;
                  if user = 'AD4' and p_object = 'UTENTI' then
                     d_object := 'UTENTI_VIEW';
                  end if;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     d_object := upper(p_object);
               END;
            else
               d_statement := d_statement || 'synonym ' || upper (p_user) || '.';
            end if;
            d_statement :=
                  d_statement
               || UPPER (d_synonym_name)
               || ' for '
               || p_owner
               || '.'
               || d_object;
            select count(1)
              into d_esiste
              from all_objects
             where object_name = upper(d_object)
               and owner = p_owner;
            if d_esiste = 0
            then
               p_error :=
                     p_error
                  || CHR (10)
                  || '('
                  || p_user
                  || ') '
                  || ' '
                  || d_statement
                  || ': oggetto inesistente.';
            else
               BEGIN
   --DBMS_OUTPUT.PUT_LINE(d_statement);
                  Afc.SQL_EXECUTE (d_statement);
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     p_error :=
                           p_error
                        || CHR (10)
                        || '('
                        || nvl (upper (p_user), 'PUBLIC')
                        || ') '
                        || ' '
                        || d_statement
                        || ': '
                        || SQLERRM;
               END;
            end if;
         end if;
      end if;
   END CREATE_SYNONYM;
   PROCEDURE CREATE_PUBLIC_SYNONYM
/******************************************************************************
 NOME:        CREATE_PUBLIC_SYNONYM
 DESCRIZIONE: Crea il sinonimo pubblico per l'oggetto dato e ritorna eventuali
              errori in p_error.
              Se il sinonimo pubblico esiste gia'
                 se corrisponde (stesso oggetto e stesso proprietario), non fa
                 nulla,
                 altrimenti droppa il sinonimo e lo ricrea.
 ARGOMENTI:   p_synonym_name: nome da assegnare al sinonimo
              p_object:       oggetto di cui fare il sinonimo
              p_error:        eventuale errore in creazione sinonimo.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
 001  02/07/2007 MM     Lancio CREATE_SYNONYM
******************************************************************************/
   (
      p_synonym_name            VARCHAR2
    , p_object                  VARCHAR2
    , p_error          IN OUT   VARCHAR2
   )
   IS
   BEGIN
      CREATE_SYNONYM ('PUBLIC', p_synonym_name, p_object, p_error);
   END CREATE_PUBLIC_SYNONYM;
   FUNCTION CREATE_SYNONYMS
/******************************************************************************
 NOME:        CREATE_SYNONYMS
 DESCRIZIONE: Crea tutti i sinonimi agli oggetti.
 ARGOMENTI:   p_user:      utente in cui creare il sinonimo
              p_what:      DB:  oggetti di base
                           CM:  gestione Comuni
                           BS:  gestione Banche e Sportelli
                           ASL: gestione ASL
                           ALL: tutti gli oggetti
 RITORNA:     stringa contenente la concatenazione di eventuali errori.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  02/07/2007 MM     Prima emissione.
******************************************************************************/
   (
      p_user   IN   VARCHAR2 default 'ALL'
    , p_what   IN   VARCHAR2 default 'ALL'
    , p_owner  IN   VARCHAR2 default USER
   ) RETURN VARCHAR2
   IS
      d_error   varchar2 (32767);
   BEGIN
      FOR c_users_rec IN c_synonym_users(p_user, p_owner)
      LOOP
         GESTIONE_SINONIMI_GRANT ('C', c_users_rec.user_oracle, p_what, '', d_error, p_owner);
      END LOOP;
      RETURN d_error;
   END CREATE_SYNONYMS;
   PROCEDURE CREATE_SYNONYMS
/******************************************************************************
 NOME:        CREATE_SYNONYMS
 DESCRIZIONE: Crea tutti i sinonimi agli oggetti.
 ARGOMENTI:   p_user:      utente in cui creare il sinonimo
              p_what:      DB:  oggetti di base
                           CM:  gestione Comuni
                           BS:  gestione Banche e Sportelli
                           ASL: gestione ASL
                           ALL: tutti gli oggetti
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  02/07/2007 MM     Prima emissione.
******************************************************************************/
   (
      p_user   IN   VARCHAR2 default 'ALL'
    , p_what   IN   VARCHAR2 default 'ALL'
    , p_owner  IN   VARCHAR2 default USER
   )
   IS
      d_error   varchar2 (32767);
   BEGIN
      d_error := CREATE_SYNONYMS(p_user, p_what, p_owner);
      if d_error is not null
      then
         raise_application_error (-20999, d_error);
      end if;
   END CREATE_SYNONYMS;
   PROCEDURE CREATE_PUBLIC_SYNONYMS
/******************************************************************************
 NOME:        CREATE_PUBLIC_SYNONYMS
 DESCRIZIONE: Crea tutti i sinonimi pubblici agli oggetti.
 ARGOMENTI:   p_what DB:  oggetti di base
                     CM:  gestione Comuni
                     BS:  gestione Banche e Sportelli
                     ASL: gestione ASL
                     ALL: tutti gli oggetti
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
 001  02/07/2007 MM     Lancio GESTIONE_SINONIMI_GRANT
******************************************************************************/
   (p_what IN VARCHAR2 DEFAULT 'ALL')
   IS
      d_error   varchar2 (32767);
   BEGIN
      GESTIONE_SINONIMI_GRANT ('C', '', p_what, '', d_error);
      if d_error is not null
      then
         raise_application_error (-20999, d_error);
      end if;
   END CREATE_PUBLIC_SYNONYMS;
   PROCEDURE DROP_SYNONYM
/******************************************************************************
 NOME:        DROP_SYNONYM
 DESCRIZIONE: Droppa il sinonimo per l'oggetto dato e ritorna eventuali
              errori in p_error.
 ARGOMENTI:   p_user:         utente in cui creare il sinonimo
              p_synonym_name: nome da assegnare al sinonimo
              p_object:       oggetto di cui fare il sinonimo
              p_error:        eventuale errore in creazione sinonimo.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  02/07/2007 MM     Prima emissione.
******************************************************************************/
   (
      p_user     in       varchar2
    , p_object   in       VARCHAR2
    , p_error    IN OUT   VARCHAR2
    , p_owner    IN       VARCHAR2 DEFAULT USER
   )
   IS
      d_synonym_name   ALL_SYNONYMS.synonym_name%type;
      d_statement      varchar2 (1000) := 'drop ';
      d_owner          ALL_SYNONYMS.owner%type := upper(p_owner);
   BEGIN
      SELECT synonym_name
        INTO d_synonym_name
        FROM ALL_SYNONYMS
       WHERE table_owner = UPPER (d_owner)
         AND table_name = UPPER (p_object)
         AND owner = nvl (UPPER (p_user), 'PUBLIC');
      if nvl (UPPER (p_user), 'PUBLIC') = 'PUBLIC'
      then
         d_statement := d_statement || 'PUBLIC synonym ';
      else
         d_statement := d_statement || 'synonym ' || UPPER (p_user) || '.';
      end if;
      d_statement := d_statement || d_synonym_name;
      --dbms_output.put_line ('DROP_SYNONYM '||d_statement);
      Afc.SQL_EXECUTE (d_statement);
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         NULL;
      WHEN OTHERS
      THEN
         IF SQLCODE = -1432
         THEN       -- ORA-01432: public synonym to be dropped does not exist
            NULL;
         ELSE
            p_error :=
                  p_error
               || CHR (10)
               || '('
               || nvl (upper (p_user), 'PUBLIC')
               || ') '
               || ' '
               || d_statement
               || ': '
               || SQLERRM;
         END IF;
   END DROP_SYNONYM;
   PROCEDURE DROP_PUBLIC_SYNONYM
/******************************************************************************
 NOME:        DROP_PUBLIC_SYNONYM
 DESCRIZIONE: Droppa il sinonimo pubblico per l'oggetto dato e ritorna eventuali
              errori in p_error.
 ARGOMENTI:   p_synonym_name: nome da assegnare al sinonimo
              p_object:       oggetto di cui fare il sinonimo
              p_error:        eventuale errore in creazione sinonimo.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
 001  02/07/2007 MM     Lancio DROP_SYNONYM
******************************************************************************/
   (p_object VARCHAR2, p_error IN OUT VARCHAR2)
   IS
   BEGIN
      DROP_SYNONYM ('', p_object, p_error);
   END DROP_PUBLIC_SYNONYM;
   FUNCTION DROP_SYNONYMS
/******************************************************************************
 NOME:        DROP_SYNONYMS
 DESCRIZIONE: Droppa tutti i sinonimi agli oggetti.
 ARGOMENTI:   p_user:         utente in cui creare il sinonimo
              p_what DB:  oggetti di base
                     CM:  gestione Comuni
                     BS:  gestione Banche e Sportelli
                     ASL: gestione ASL
                     ALL: tutti gli oggetti
 RITORNA:     stringa contenente la concatenazione di eventuali errori.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  02/07/2007 MM     Prima emissione.
******************************************************************************/
   (
      p_user   in   varchar2
    , p_what   IN   VARCHAR2 DEFAULT 'ALL'
    , p_owner  IN   VARCHAR2 DEFAULT USER
   ) RETURN VARCHAR2
   IS
      d_error   varchar2 (32767);
   BEGIN
      GESTIONE_SINONIMI_GRANT ('D', p_user, p_what, '', d_error, p_owner);
      RETURN d_error;
   END DROP_SYNONYMS;
   PROCEDURE DROP_SYNONYMS
/******************************************************************************
 NOME:        DROP_SYNONYMS
 DESCRIZIONE: Droppa tutti i sinonimi agli oggetti.
 ARGOMENTI:   p_user:         utente in cui creare il sinonimo
              p_what DB:  oggetti di base
                     CM:  gestione Comuni
                     BS:  gestione Banche e Sportelli
                     ASL: gestione ASL
                     ALL: tutti gli oggetti
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  02/07/2007 MM     Prima emissione.
******************************************************************************/
   (
      p_user   in   varchar2
    , p_what   IN   VARCHAR2 DEFAULT 'ALL'
    , p_owner  IN   VARCHAR2 DEFAULT USER
   )
   IS
      d_error   varchar2 (32767);
   BEGIN
      d_error := DROP_SYNONYMS (p_user, p_what, p_owner);
      if d_error is not null
      then
         raise_application_error (-20999, d_error);
      end if;
   END DROP_SYNONYMS;
   PROCEDURE DROP_PUBLIC_SYNONYMS
/******************************************************************************
 NOME:        DROP_PUBLIC_SYNONYMS
 DESCRIZIONE: Droppa tutti i sinonimi pubblici agli oggetti.
 ARGOMENTI:   p_what DB:  oggetti di base
                     CM:  gestione Comuni
                     BS:  gestione Banche e Sportelli
                     ASL: gestione ASL
                     ALL: tutti gli oggetti
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  28/11/2005 MM     Prima emissione.
 001  02/07/2007 MM     Lancio GESTIONE_SINONIMI_GRANT
******************************************************************************/
   (p_what IN VARCHAR2 DEFAULT 'ALL')
   IS
      d_error   varchar2 (32767);
   BEGIN
      GESTIONE_SINONIMI_GRANT ('D', '', p_what, '', d_error);
      if d_error is not null
      then
         raise_application_error (-20999, d_error);
      end if;
   END DROP_PUBLIC_SYNONYMS;
   PROCEDURE CREA_SYN_GRANT_AD4_SECONDARI
/******************************************************************************
 NOME:        CREA_SYN_GRANT_AD4_SECONDARI
 DESCRIZIONE: Crea i sinonimi privati e assegna le grant all'utente dato di
              tutte le funzioni, package, procedure, tabelle e viste che gia'
              non possiede.
 ARGOMENTI:   p_grantee:   utente a cui assegnare le grant ed in cui creare i
                           sinonimi.
 NOTE:        - Controlla che l'utente dato sia associato ad un'istanza del
                progetto AD4.
              - I sinonimi si chiamano come gli oggetti originali.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  02/07/2007 MM     Prima emissione.
******************************************************************************/
   (p_grantee IN VARCHAR2)
   IS
      d_grantee   ALL_SYNONYMS.owner%type       := upper (p_grantee);
      d_grantor   ALL_SYNONYMS.table_owner%type := upper (user);       --upper(p_grantor);
      d_error     varchar2 (32676);
      d_check     integer          := 0;
      CURSOR c_ad4_obj (p_grantor in varchar2, p_grantee in varchar2)
      IS
         SELECT object_name
           FROM all_objects
          WHERE owner = p_grantor
            AND object_type in
                        ('FUNCTION', 'PACKAGE', 'PROCEDURE', 'TABLE', 'VIEW')
            AND object_name <> 'ADMIN_AD4'
            AND (   object_type <> 'PROCEDURE'
                 OR SUBSTR (object_name, -3, 3) NOT IN ('_PI', '_PU', '_PD')
                )
            AND (   object_type <> 'PACKAGE'
                 OR (    SUBSTR (object_name, 1, 3) NOT IN ('XML', 'XSL')
                     AND SUBSTR (object_name, 1, 5) <> 'DBMS_'
                    )
                )
            AND INSTR (object_name, '$') = 0
            AND object_name NOT IN (
                          SELECT object_name
                            FROM all_objects
                           WHERE owner = p_grantee
                                 and object_type <> 'SYNONYM');
   BEGIN
      select count (1)
        into d_check
        from istanze
       where upper (user_oracle) = d_grantee and progetto = 'AD4';
      if d_check > 0
      then
         FOR v_obj IN c_ad4_obj (d_grantor, d_grantee)
         LOOP
            CREATE_SYNONYM (d_grantee
                          , v_obj.object_name
                          , v_obj.object_name
                          , d_error
                           );
            EXECUTE_GRANT (p_user       => d_grantee
                         , p_what       => v_obj.object_name
                         , p_error      => d_error
                          );
         END LOOP;
         IF d_error IS NOT NULL
         THEN
            RAISE_APPLICATION_ERROR
                                  (-20999
                                 ,    'Errori in creazione sinonimi / grant:'
                                   || CHR (10)
                                   || d_error
                                  );
         END IF;
      else
         RAISE_APPLICATION_ERROR (-20999
                                ,    'Errori in creazione sinonimi / grant:'
                                  || ' l''utente '
                                  || d_grantee
                                  || ' non appartiene al progetto ''AD4''.'
                                 );
      end if;
   END CREA_SYN_GRANT_AD4_SECONDARI;
   PROCEDURE DROP_SYN_GRANT_AD4_SECONDARI
/******************************************************************************
 NOME:        DROP_SYN_GRANT_AD4_SECONDARI
 DESCRIZIONE: Elimina i sinonimi e revoca le grant all'utente dato di tutti gli
              oggetti di AD4.
 ARGOMENTI:   p_grantee:   utente a cui revocare le grant ed in cui eliminare i
                           sinonimi privati.
 NOTE:        --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  02/07/2007 MM     Prima emissione.
******************************************************************************/
   (p_grantee IN VARCHAR2)
   IS
      d_grantee   ALL_SYNONYMS.owner%type       := upper (p_grantee);
      d_grantor   ALL_SYNONYMS.table_owner%type := upper (user);       --upper(p_grantor);
      d_error     varchar2 (32676);
      CURSOR c_ad4_obj (p_grantor in varchar2, p_grantee in varchar2)
      IS
         SELECT synonym_name
           FROM all_synonyms
          WHERE owner = p_grantee AND table_owner = p_grantor;
   BEGIN
      FOR v_obj IN c_ad4_obj (d_grantor, d_grantee)
      LOOP
         DROP_SYNONYM (d_grantee, v_obj.synonym_name, d_error);
         REVOKE_GRANT (p_user       => d_grantee
                     , p_what       => v_obj.synonym_name
                     , p_error      => d_error
                      );
      END LOOP;
      IF d_error IS NOT NULL
      THEN
         RAISE_APPLICATION_ERROR
                               (-20999
                              ,    'Errori in eliminazione sinonimi / grant:'
                                || CHR (10)
                                || d_error
                               );
      END IF;
   END DROP_SYN_GRANT_AD4_SECONDARI;
   PROCEDURE grant_da_anagrafe_as4 (
      p_user_anagrafe                  in     ISTANZE.user_oracle%type
    , p_user_amministratore            in     ISTANZE.user_oracle%type
    , p_error                          in out varchar2)
   is
      d_statement varchar2(32767);
   begin
      FOR j IN NVL (TabAs4.FIRST, 1) .. NVL (TabAs4.LAST, 0)
      LOOP
         d_statement := 'begin '|| p_user_anagrafe ||'.admin_xx4.execute_grant(:p_user, :p_what, :p_privilege, :p_error); end;';
         execute immediate d_statement
                     using IN p_user_amministratore, IN TabAs4(j).object_name, IN 'ALL', IN OUT p_error;
      END LOOP;
   end grant_da_anagrafe_as4;
   PROCEDURE crea_syn_anagrafe_as4 (
      p_user_anagrafe                  in     ISTANZE.user_oracle%type
    , p_user_amministratore            in     ISTANZE.user_oracle%type
    , p_error                          in out varchar2)
   is
      d_isToDrop     boolean := TRUE;
      d_owner        ALL_SYNONYMS.owner%type;
      d_synonym_name ALL_SYNONYMS.synonym_name%type;
   begin
      FOR j IN NVL (TabAs4.FIRST, 1) .. NVL (TabAs4.LAST, 0)
      LOOP
         d_synonym_name := 'AS4_'||TabAs4(j).object_name;
         begin
            SELECT table_owner
              INTO d_owner
              FROM ALL_SYNONYMS
             WHERE synonym_name = d_synonym_name
               AND owner = p_user_amministratore;
         exception
            when no_data_found
            then
               d_isToDrop := FALSE;
         end;
         if nvl(d_owner, 'xxxxxxxx') <> p_user_anagrafe
         then
            if d_isToDrop
            then
               begin
                  DROP_SYNONYM ( p_user       => p_user_amministratore
                               , p_object     => d_synonym_name
                               , p_owner      => d_owner
                               , p_error      => p_error);
               end;
            end if;
            CREATE_SYNONYM ( p_user => p_user_amministratore
                           , p_synonym_name => d_synonym_name
                           , p_object => TabAs4(j).object_name
                           , p_owner => p_user_anagrafe
                           , p_error => p_error);
         end if;
      END LOOP;
   end crea_syn_anagrafe_as4;
/******************************************************************************
 NOME:        KILL_ISTANZA_SESSION.
 DESCRIZIONE: elimina tutte le sessioni attive per l'istanza indicata
 ARGOMENTI:   p_istanza IN VARCHAR2 istanza a cui si accede.
 ECCEZIONI:   Non possiede eccezioni proprie, ma lancia la procedure
              KILL_SESSION
              che potrebbe risultare in una eccezione.
 05   23/02/2012 SN     Possibilita di killare tutte le sessioni di una istanza
******************************************************************************/
   PROCEDURE KILL_ISTANZA_SESSION (
      p_istanza   VARCHAR2
   )
      IS
      d_err_code   NUMBER:= 0;
   BEGIN
      -- seleziona tutte le sessioni attive dell''istanza.
      FOR sess IN (SELECT acce.SESSION_ID, acce.utente, acce.modulo, acce.istanza
                     FROM ACCESSI acce
                    WHERE acce.Istanza = p_istanza
                      AND acce.LOG = 'ON'
                      and nvl(gv_$session_master_pkg.get_status(acce.SESSION_ID), 'NO') != 'KILLED')
      LOOP
         BEGIN
            KILL_SESSION (sess.session_id);
            UPDATE ACCESSI
               SET LOG = 'OFF'
                 , NOTE =
                      'Sessione chiusa da successivo accesso su richiesta dell''utente.'
             WHERE Istanza = sess.istanza
               AND MODULO = sess.modulo
               AND Utente = sess.utente
               AND SESSION_ID = sess.session_id;
         EXCEPTION
         when others then
         d_err_code := 1;
         END;
      END LOOP;
      if d_err_code = 1 then
       RAISE_APPLICATION_ERROR
               (-20998
              ,    'Rilevati processi attivi che l''Amministratore Database non e'' in grado di terminare.'
                || CHR (10)
                || 'Probabili cause: il processo principale non ha i privilegi necessari per ''abortire'' sessioni.'
                || CHR (10)
                || CHR (10)
                || 'Avvisare l''Amministratore del Sistema'
                , true
               );
      end if;
   END KILL_ISTANZA_SESSION;
   PROCEDURE KILL_USER_SESSION
/******************************************************************************
 NOME:        KILL_USER_SESSION.
 DESCRIZIONE: elimina tutte le sessioni attive dell'utente.
 ARGOMENTI:   p_istanza IN VARCHAR2 istanza a cui si accede.
              p_modulo  IN VARCHAR2 modulo a cui si accede.
              p_utente  IN VARCHAR2 utente che accede.
 ECCEZIONI:   Non possiede eccezioni proprie, ma lancia la procedure
              KILL_SESSION
           che potrebbe risultare in una eccezione.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  25/01/2001 MM     Prima emissione.
******************************************************************************/
   (
      p_istanza   VARCHAR2
    , p_modulo    VARCHAR2
    , p_utente    VARCHAR2
   )
   IS
     -- d_err_code   NUMBER;
   BEGIN
      -- seleziona tutte le sessioni attive dell'utente.
      FOR sess IN (SELECT acce.SESSION_ID
                     FROM ACCESSI acce
                    WHERE acce.Istanza = p_istanza
                      AND acce.MODULO = p_modulo
                      AND acce.Utente = p_utente
                      AND acce.LOG = 'ON'
                      and nvl(gv_$session_master_pkg.get_status(acce.SESSION_ID), 'NO') != 'KILLED')
      LOOP
         BEGIN
            KILL_SESSION (sess.session_id);
            UPDATE ACCESSI
               SET LOG = 'OFF'
                 , NOTE =
                      'Sessione chiusa da successivo accesso su richiesta dell''utente.'
             WHERE Istanza = p_istanza
               AND MODULO = p_modulo
               AND Utente = p_utente
               AND SESSION_ID = sess.session_id;
         END;
      END LOOP;
   END KILL_USER_SESSION;
   PROCEDURE KILL_SESSION
/******************************************************************************
 NOME:        KILL_SESSION.
 DESCRIZIONE: elimina la singola sessione.
 ARGOMENTI:   p_session_id IN NUMBER numero identificativo della sessione.
 ECCEZIONI:   20998, Warning generico: 'Rilevati processi attivi che
                                        l'Amministratore Database non e' in
                                        grado di terminare.'
 ANNOTAZIONI: chiamata da KILL_USER_SESSION.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  25/01/2001 MM     Prima emissione.
******************************************************************************/
   (p_session_id NUMBER)
   IS
      d_msg      VARCHAR2 (2000);
      d_error    INTEGER;
      d_sid      INTEGER;
      d_serial   INTEGER;
   BEGIN
      BEGIN
         -- verifica l'esistenza della sessione ancora in attivita'
         -- (se status = KILLED significa che il processo e' gia' stato
         -- eliminato)
         if gv_$session_master_pkg.GET_STATUS(p_session_id) != 'KILLED' then
            d_sid := gv_$session_master_pkg.GET_SID(p_session_id);
            d_serial := gv_$session_master_pkg.GET_SERIAL#(p_session_id);
--         SELECT SID, serial#
--           INTO d_sid, d_serial
--           FROM sessioni
--          WHERE audsid = p_session_id AND status != 'KILLED';
            Afc.SQL_EXECUTE (   'alter system kill session '''
                             || d_sid
                             || ','
                             || d_serial
                             || ''''
                            );
         end if;
      EXCEPTION
--         WHEN NO_DATA_FOUND
--         THEN
--            RAISE;
         WHEN OTHERS
         THEN
            RAISE_APPLICATION_ERROR
               (-20998
              ,    'Rilevati processi attivi che l''Amministratore Database non e'' in grado di terminare.'
                || CHR (10)
                || 'Probabili cause: il processo principale non ha i privilegi necessari per ''abortire'' sessioni.'
                || CHR (10)
                || CHR (10)
                || 'Avvisare l''Amministratore del Sistema'
                , true
               );
      END;
   END KILL_SESSION;
   FUNCTION exists_object(p_objectname VARCHAR2)
   return number
   IS
   v_trovato number := 0;
   BEGIN
      select count(distinct object_name)
        into v_trovato
        from user_objects
       where object_name = upper(p_objectname);
      return v_trovato;
   END;

   PROCEDURE add_TVDBTab (p_objectname VARCHAR2)
   IS
   BEGIN
      if exists_object(p_objectname) = 1 then
          b_index := b_index + 1;
          TabTVDB (b_index).object_name := p_objectname;
      end if;
   END;
   PROCEDURE add_PDBTab (p_objectname VARCHAR2)
   IS
   BEGIN
      if exists_object(p_objectname) = 1 then
          b_index := b_index + 1;
          TabPDB (b_index).object_name := p_objectname;
      end if;
   END;
   PROCEDURE add_TVCMTab (p_objectname VARCHAR2)
   IS
   BEGIN
      if exists_object(p_objectname) = 1 then
          b_index := b_index + 1;
          TabTVCM (b_index).object_name := p_objectname;
      end if;
   END;
   PROCEDURE add_PCMTab (p_objectname VARCHAR2)
   IS
   BEGIN
      if exists_object(p_objectname) = 1 then
          b_index := b_index + 1;
          TabPCM (b_index).object_name := p_objectname;
      end if;
   END;
   PROCEDURE add_TVBSTab (p_objectname VARCHAR2)
   IS
   BEGIN
      if exists_object(p_objectname) = 1 then
          b_index := b_index + 1;
          TabTVBS (b_index).object_name := p_objectname;
      end if;
   END;
   PROCEDURE add_PBSTab (p_objectname VARCHAR2)
   IS
   BEGIN
      if exists_object(p_objectname) = 1 then
          b_index := b_index + 1;
          TabPBS (b_index).object_name := p_objectname;
      end if;
   END;
   PROCEDURE add_TVASLTab (p_objectname VARCHAR2)
   IS
   BEGIN
      if exists_object(p_objectname) = 1 then
          b_index := b_index + 1;
          TabTVASL (b_index).object_name := p_objectname;
      end if;
   END;
   PROCEDURE add_PXDKTab (p_objectname VARCHAR2)
   IS
   BEGIN
      if exists_object(p_objectname) = 1 then
          b_index := b_index + 1;
          TabPXDK (b_index).object_name := p_objectname;
      end if;
   END;
   PROCEDURE add_AS4Tab (p_objectname VARCHAR2)
   IS
   BEGIN
      b_index := b_index + 1;
      TabAs4 (b_index).object_name := p_objectname;
   END;
   FUNCTION REFRESH_SLAVES_JOB
   ( p_refresh_group                       in varchar2
   , p_istanza_master                      in varchar2 default si4.istanza
   )
   RETURN varchar2
   is
      d_return varchar2(4000);
   begin
      execute immediate 'CALL master_utility.REFRESH_SLAVES_JOB('''||p_refresh_group||''', '''||p_istanza_master||''') into :d_return' USING OUT d_return;
      return d_return;
   end;
   PROCEDURE REFRESH_SLAVES
   is
      d_return varchar2(4000);
   begin
      execute immediate 'BEGIN master_utility_ad4.REFRESH_SLAVES; END;';
   end;
 /******************************************************************************
 NOME:        VALIDATE_PUBLIC_SYNONYM
 DESCRIZIONE: Tenta query su sinonimi pubblici per fare compilazione automatica
 ARGOMENTI:   p_user IN VARCHAR2 User a cui appartengono gli oggetti il cui
                                 sinonimo pubblico e da compilare.
 ECCEZIONI:
 RITORNA:     numero di oggetti che non si compilano.
ANNOTAZIONI:
******************************************************************************/
BEGIN
-- Riempie le tabelle con gli oggetti da gestire
/*******************************************************/
/*                      DB                             */
/*           Table / View:      34                     */
/*           Function /Package: 32                     */
/*                             ----                    */
/*                              66                   */
/*******************************************************/
   b_index := 0;
   -- Table / View DB
   add_TVDBTab ('ACCESSI');
   add_TVDBTab ('ASSISTENTE_VIRTUALE');  -- rev. 11
   add_TVDBTab ('CARATTERISTICHE_ACCESSO');
   add_TVDBTab ('CREDENZIALI');
   add_TVDBTab ('CREDENZIALI_LIVELLO');
   add_TVDBTab ('CREDENZIALI_UTENTE');
   add_TVDBTab ('DIRITTI_ACCESSO');
   add_TVDBTab ('DISABILITAZIONI');
   add_TVDBTab ('ENTI');
   add_TVDBTab ('EXTERNAL_FUNCTIONS');
   add_TVDBTab ('EVENTI');
   add_TVDBTab ('GRUPPI_LAVORO');
   add_TVDBTab ('GRUPPI_LINGUISTICI');
   add_TVDBTab ('INCARICHI');
   add_TVDBTab ('INFORMATIVE_PRIVACY');
   add_TVDBTab ('ISTANZE');
   add_TVDBTab ('LINGUE');
   add_TVDBTab ('LIVELLI_SICUREZZA');
   add_TVDBTab ('MODULI');
   add_TVDBTab ('PARAMETRI_RICHIESTA');
   add_TVDBTab ('PERSONALIZZAZIONI');
   add_TVDBTab ('PROGETTI');
   add_TVDBTab ('RICHIESTE_ABILITAZIONE');
   add_TVDBTab ('RUOLI');
   add_TVDBTab ('RUOLI_APPLICATIVI');
   add_TVDBTab ('SERVIZI');
   add_TVDBTab ('SLAVES');
   add_TVDBTab ('SI4_USER_SOURCE');
   add_TVDBTab ('SOGGETTI');
   add_TVDBTab ('TIPI_CREDENZIALE');
   add_TVDBTab ('UTENTI');
   add_TVDBTab ('UTENTI_GRUPPO');
   add_TVDBTab ('UTENTI_PRIVACY');
   add_TVDBTab ('UTENTI_SOGGETTI');
   add_TVDBTab ('UTENTI_GRUPPI');
   b_index := 0;
   -- FUNCTION DB
   add_PDBTab ('GET_GRUPPI_UTENTE');
   add_PDBTab ('TRACCIA_ACCESSO');
   -- PACKAGE DB
   add_PDBTab ('ACCESSO');
   add_PDBTab ('ACCESSI_TPK');
   add_PDBTab ('AD4_EVENTO');
   add_PDBTab ('ASSISTENTE_VIRTUALE_PKG');-- rev. 12
   add_PDBTab ('CARATTERISTICA_ACCESSO');
   add_PDBTab ('CARATTERISTICHE_ACCESSO_TPK');
   add_PDBTab ('CODICE_FISCALE');
   add_PDBTab ('CRYPT');
   add_PDBTab ('DIRITTI_ACCESSO_TPK');
   add_PDBTab ('DIRITTO_ACCESSO');
   add_PDBTab ('EXTERNAL_FUNCTIONS_TPK');
   add_PDBTab ('GLOBAL_UTILITY');
   add_PDBTab ('GRUPPO');
   add_PDBTab ('INCARICHI_TPK');
   add_PDBTab ('INFORMATIVE_PRIVACY_PKG');
   add_PDBTab ('INFORMATIVE_PRIVACY_TPK');
   add_PDBTab ('ISTANZA');
   add_PDBTab ('ISTANZE_TPK');
   add_PDBTab ('JFCUTILS');
   add_PDBTab ('MODULI_TPK');
   add_PDBTab ('PARAMETRI_RICHIESTA_TPK');
   add_PDBTab ('PROGETTI_TPK');
   add_PDBTab ('REGISTRO_PAC');
   add_PDBTab ('REGISTRO_UTILITY');
   add_PDBTab ('RICHIESTE_ABILITAZIONE_TPK');
   add_PDBTab ('RUOLI_TPK');
   add_PDBTab ('SERVIZI_PKG');
   add_PDBTab ('SERVIZI_TPK');
   add_PDBTab ('SI4CIM');
   add_PDBTab ('SOGGETTO');
   add_PDBTab ('USER_INTEGRAZIONI_AD4');
   add_PDBTab ('UTENTE');
   add_PDBTab ('UTENTE_GRUPPO');
   add_PDBTab ('UTENTI_PRIVACY_TPK');
   add_PDBTab ('SCHEDULA_RIGENERA_SO');  --15
   add_PDBTab ('SESSIONI_PKG');



/*******************************************************/
/*                      CM                             */
/*           Table / View:       9                     */
/*           Function /Package:  9                     */
/*                             ----                    */
/*                              18                     */
/*******************************************************/
   b_index := 0;
   -- Table / View CM
   add_TVCMTab ('COMUNI');
   add_TVCMTab ('COMUNI_TOTALI');
   add_TVCMTab ('CONSOLATI');
   add_TVCMTab ('PROVINCE');
   add_TVCMTab ('PROVINCIE');
   add_TVCMTab ('RAGGRUPPAMENTI_STATI');
   add_TVCMTab ('REGIONI');
   add_TVCMTab ('STATI_TERRITORI');
   add_TVCMTab ('PAESI_TERRITORI_AG_ENTRATE');
   add_TVCMTab ('VISTA_COMUNI_STORICI'); --rev. 11 rev. 16
   b_index := 0;
   -- Package CM
   add_PCMTab ('COMUNE');
   add_PCMTab ('COMUNI_TPK');
   add_PCMTab ('COMUNI_TOTALI_TPK');
   add_PCMTab ('PROVINCIA');
   add_PCMTab ('REGIONE');
   add_PCMTab ('REGIONI_TPK');
   add_PCMTab ('STATI_TERRITORI_TPK');
   add_PCMTab ('STATI_TERRITORI_PKG');
/*******************************************************/
/*                      BS                             */
/*           Table / View:       2                     */
/*           Function /Package:  2                     */
/*                             ----                    */
/*                               4                     */
/*******************************************************/
   b_index := 0;
   -- Table / View BS
   add_TVBSTab ('BANCHE');
   add_TVBSTab ('SPORTELLI');
   add_PBSTab ('BANCHE_TPK');
   add_PBSTab ('SPORTELLI_TPK');
/*******************************************************/
/*                      ASL                            */
/*           Table / View:       3                     */
/*           Function /Package:  0                     */
/*                             ----                    */
/*                               3                     */
/*******************************************************/
   b_index := 0;
   -- Verifica se il componente e' stato installato
   DECLARE
      d_esiste_comp   INTEGER;
   BEGIN
      SELECT COUNT (1)
        INTO d_esiste_comp
        FROM obj
       WHERE UPPER (object_name) = 'ASL';
      IF d_esiste_comp > 0
      THEN
         -- Table / View ASL
         add_TVASLTab ('ASL');
         add_TVASLTab ('ASL_COMUNE');
         add_TVASLTab ('USL');
      END IF;
   END;
/*******************************************************/
/*                      XDK                            */
/*           Table / View:       0                     */
/*           Function /Package: 22                     */
/*                             ----                    */
/*                              22                     */
/*******************************************************/
/* NON FARE Rev. 008 Bug #30638 INIZIO
   b_index := 0;
   -- Verifica se il componente e' stato installato
   DECLARE
      d_esiste_comp   INTEGER;
   BEGIN
      SELECT COUNT (1)
        INTO d_esiste_comp
        FROM all_synonyms
       WHERE synonym_name = 'DBMS_JAVA'
         AND exists (select 1
                       from user_objects
                      where object_name = 'DBMS_XMLQUERY')
      ;
      IF d_esiste_comp > 0
      THEN
         -- Function /Package XDK
         add_PXDKTab ('DBMS_XMLQUERY');
         add_PXDKTab ('DBMS_XMLSAVE');
         add_PXDKTab ('XMLATTRCOVER');
         add_PXDKTab ('XMLCHARDATACOVER');
         add_PXDKTab ('XMLDOCUMENTCOVER');
         add_PXDKTab ('XMLDOM');
         add_PXDKTab ('XMLDOMIMPLCOVER');
         add_PXDKTab ('XMLDTDCOVER');
         add_PXDKTab ('XMLELEMENTCOVER');
         add_PXDKTab ('XMLENTITYCOVER');
         add_PXDKTab ('XMLGEN');
         add_PXDKTab ('XMLNNMCOVER');
         add_PXDKTab ('XMLNODECOVER');
         add_PXDKTab ('XMLNODELISTCOVER');
         add_PXDKTab ('XMLNOTATIONCOVER');
         add_PXDKTab ('XMLPARSER');
         add_PXDKTab ('XMLPARSERCOVER');
         add_PXDKTab ('XMLPICOVER');
         add_PXDKTab ('XMLTEXTCOVER');
         add_PXDKTab ('XSLPROCESSOR');
         add_PXDKTab ('XSLPROCESSORCOVER');
         add_PXDKTab ('XSLSTYLESHEETCOVER');
      END IF;
   END;
   Rev. 008 Bug #30638 FINE */
   -- Riempie la tabella TabAS4 con gli oggetti da gestire
   b_index := 0;
   add_As4Tab ('ANAGRAFE_SOGGETTI');
   add_As4Tab ('TIPI_SOGGETTO');
   add_As4Tab ('SOGGETTI');
   add_As4Tab ('STORICO_SOGGETTI');
   add_As4Tab ('SOGG_SQ1');
END ;
/
