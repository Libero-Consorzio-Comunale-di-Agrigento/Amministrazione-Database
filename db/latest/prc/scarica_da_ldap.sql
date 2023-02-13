--liquibase formatted sql

--changeset mturra:201901301231_339 runOnChange:true endDelimiter:/ stripComments:false


CREATE OR REPLACE PROCEDURE scarica_da_ldap (
   p_server    VARCHAR2,
   p_dn        VARCHAR2 DEFAULT 'XXXX',
   p_scarica_da_ldap varchar2 default 'SI')
IS
   SUBTYPE ldap_attribute IS VARCHAR2 (4000);
   ldap_url                   ldap_attribute;
   ldap_user                  ldap_attribute;
   ldap_password              ldap_attribute;
   ldap_camponominativo       ldap_attribute;
   ldap_tipo_autenticazione   ldap_attribute;
   ldap_root_users            ldap_attribute;
   v_def_ins_group            VARCHAR2 (1000);
   v_utente                   utenti.utente%TYPE;
   v_gruppo                   utenti.utente%TYPE;
   d_chiave                   VARCHAR2 (30)
                                 := 'PRODUCTS/LDAPCONFIG/' || p_server;
   v_nominativo_effettivo     utenti.nominativo%TYPE;
   PROCEDURE get_parametri_ldap
   /******************************************************************************
    NOME:        get_parametri_ldap.
    DESCRIZIONE: Legge la tabella REGISTRO per le impostazioni.Se anche ci sono piu
    server ldap impostati legge comunque solo i dati relativi al server principale.
    NOTE:
   ******************************************************************************/
   IS
      d_tipo_connessione   VARCHAR2 (10) := 'ldap';
   BEGIN
      ldap_tipo_autenticazione :=
         registro_utility.leggi_stringa (UPPER (d_chiave),
                                         'TIPOAUTENTICAZIONE',
                                         1);
      IF ldap_tipo_autenticazione = 'SSL'
      THEN
         d_tipo_connessione := 'ldaps';
      END IF;
      ldap_url :=
            d_tipo_connessione
         || '://'
         || registro_utility.leggi_stringa (UPPER (d_chiave), 'SERVER', 1)
         || ':'
         || registro_utility.leggi_stringa (UPPER (d_chiave), 'PORTA', 1);
      ldap_user :=
         registro_utility.leggi_stringa (UPPER (d_chiave),
                                         'UTENTECONNESSIONELDAP',
                                         1);
      ldap_password :=
         registro_utility.leggi_stringa (UPPER (d_chiave),
                                         'PWDCONNESSIONELDAP',
                                         1);
      ldap_root_users :=
         registro_utility.leggi_stringa (UPPER (d_chiave), 'ROOTUSERS', 1);
      ldap_camponominativo :=
         registro_utility.leggi_stringa (UPPER ('PRODUCTS/LDAPCONFIG'),
                                         'CampoNominativoInLDAP',
                                         0);
   END;
BEGIN
   get_parametri_ldap;
   if nvl(p_scarica_da_ldap,'SI') = 'SI' then
      EXECUTE IMMEDIATE ('truncate table ldap_attributi');
      EXECUTE IMMEDIATE ('truncate table ldap_struttura');
      FOR i IN 65 .. 90
      LOOP                                                             --'A to 'Z
         FOR x IN 65 .. 90
         LOOP                                                          --'A to 'Z
            finmatica_ldap.download_schema (
               ldap_url,                   -- 'ldap://ldapserver.ausl.pr.it:389',
               ldap_user,                -- 'cn=cup,ou=apps,dc=ausl,dc=pr,dc=it',
               ldap_password-- 'CupPwd2014'                                         --'a5lXeHPxY'
               ,
               1                                                      --p_livello
                ,
               LTRIM (ldap_root_users                     --'dc=ausl,dc=pr,dc=it'
                                     , ','),
                  '(&(objectClass=Person)('
               || ldap_camponominativo                          --            uid
               || '='
               || CHR (i)
               || CHR (x)
               || '*))'
            );
            COMMIT;
         END LOOP;
      END LOOP;
   end if; -- fine controllo se scaricare da ldap.
   BEGIN
      SELECT valore
        INTO v_def_ins_group
        FROM registro
       WHERE stringa = 'DefaultInsertGroups' AND chiave = d_chiave;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         IF INSTR (d_chiave, 'SERVER') > 0
         THEN
            -- cerco sul server generico
            BEGIN
               SELECT valore
                 INTO v_def_ins_group
                 FROM registro
                WHERE     stringa = 'DefaultInsertGroups'
                      AND chiave = afc.get_substr (d_chiave,
                                                   '/SERVER',
                                                   1,
                                                   'I');
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  v_def_ins_group := NULL;
            END;
         END IF;
   END;
   FOR v_persona
      IN (SELECT LTRIM (afc.get_substr (dn,
                                        ',',
                                        1,
                                        'I'),
                        'UID=')
                    nominativo
            FROM ldap_struttura
           WHERE     categoria = 'PERSON'
                 --                        AND   dn like '%'||p_dn|| '%'
                 AND LTRIM (afc.get_substr (dn,
                                            ',',
                                            1,
                                            'I'),
                            'UID=') =
                        SUBSTR (
                           p_dn,
                           1,
                           DECODE (INSTR (p_dn, '@'),
                                   0, LENGTH (p_dn),
                                   INSTR (p_dn, '@') - 1)))
   LOOP
    v_nominativo_effettivo :=
         UPPER (
            finmatica_ldap.manage_ad4 (
               username    => v_persona.nominativo,
               serverkey   => 'PRODUCTS/LDAPCONFIG/' || p_server));
      DBMS_OUTPUT.put_line (
         v_nominativo_effettivo || ':' || 'PRODUCTS/LDAPCONFIG/' || p_server);
      IF    utente.exists_nominativo (v_nominativo_effettivo) != 0
         OR utente.exists_nominativo (
               SUBSTR (v_nominativo_effettivo, 1, INSTR (v_nominativo_effettivo, '@') - 1)) != 0
      THEN
         -- ESISTE GIA lo metto nei gruppi così sistemo se deve diventare ldap
         -- se in gruppi non ci deve essere si sistemeranno al login
         v_utente := utente.get_utente (v_nominativo_effettivo);
          DBMS_OUTPUT.put_line ('utente' || v_utente);
         v_gruppo := afc.get_substr (v_def_ins_group, ',');
          DBMS_OUTPUT.put_line ('v_def_ins_group' || v_gruppo);
         WHILE v_gruppo IS NOT NULL
         LOOP
            INSERT INTO utenti_gruppo (utente, gruppo)
               SELECT v_utente, v_gruppo
                 FROM DUAL
                WHERE NOT EXISTS
                         (SELECT 1
                            FROM utenti_gruppo
                           WHERE utente = v_utente AND gruppo = v_gruppo);
            v_gruppo := afc.get_substr (v_def_ins_group, ',');
         END LOOP;
      END IF;
      --      IF utente.exists_nominativo (v_persona.nominativo) = 0
      --      THEN
      --         -- non esiste, va creato
      --         INSERT INTO utenti (nominativo, password, data_password)
      --           VALUES   (v_persona.nominativo, '', SYSDATE);
      --      END IF;
      --      v_utente := utente.get_utente (v_persona.nominativo);
      --      v_gruppo := afc.get_substr (v_def_ins_group, ',');
      --      WHILE v_gruppo IS NOT NULL
      --      LOOP
      --         INSERT INTO utenti_gruppo (utente, gruppo)
      --            SELECT   v_utente, v_gruppo
      --              FROM   DUAL
      --             WHERE   NOT EXISTS
      --                        (SELECT   1
      --                           FROM   utenti_gruppo
      --                          WHERE   utente = v_utente AND gruppo = v_gruppo);
      --         v_gruppo := afc.get_substr (v_def_ins_group, ',');
      --      END LOOP;
      accesso.login_setup (v_nominativo_effettivo,
                           'PRODUCTS/LDAPCONFIG/' || p_server
                           , 'S' ); -- forzo sistemazione
   END LOOP;
commit;
END;
/

