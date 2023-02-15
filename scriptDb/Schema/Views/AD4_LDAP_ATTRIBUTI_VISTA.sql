CREATE OR REPLACE FORCE VIEW AD4_LDAP_ATTRIBUTI_VISTA
(DN, NOME_ATTRIBUTO, VALORE_ATTRIBUTO)
BEQUEATH DEFINER
AS 
SELECT   ldst.dn,  --ldap_utility.get_name ('CN=' || descrizione),
   'MEMBER',
   LTRIM (
   ldap_utility.formatta_struttura (struttura, '', 'G')
   || ','
   || ldap_utility.get_RootOU,
   ','
   )
   gruppo
  FROM   struttura_utenti_temp stut1, ad4_ldap_struttura ldst
 WHERE ldst.figlio = stut1.figlio
   AND stut1.tipo_figlio = 'U'    -- sono utenti
   AND tipo_padre = 'O'
   AND DECODE (INSTR (struttura, '#'),
   0, 'NO',
   SUBSTR (struttura, 1, INSTR (struttura, '#') - 1)) =
   'O'
   AND caratteristica_accesso.is_ldapuser (stut1.figlio) = 1
   -- solo utenti con accesso ldap
   AND utente.get_stato (stut1.figlio, 'Y') = 'U'
   --TUTTE LE ASSEGNAZIONI   -- membro di un gruppo solo se assegnazione prevalente
   -- AND EXISTS
   --  (SELECT 'x'
   --  FROM utenti_profilo
   --    WHERE  ad4_utente = stut1.figlio
   --    AND padre = gruppo
   --    AND assegnazione_prevalente = 1)
   UNION ALL
   SELECT   ldst.dn, -- persona membra del gruppo ruolo-unità_organizzativa
   --ldap_utility.get_name ('CN=' || stut.descrizione),
   'MEMBER',
   LTRIM (
   ldap_utility.formatta_struttura (
   SUBSTR (stut.struttura, 1, INSTR (stut.struttura, '[U#'))
   || SUBSTR (stut_ruolo.struttura,
  1,
  INSTR (stut_ruolo.struttura, '[U#')),
   stut_ruolo.padre,
   'O'
   ),
   ','
   )
   || ','
   || ldap_utility.get_RootOU
   gruppo
  FROM   struttura_utenti_temp stut,
   struttura_utenti_temp stut_ruolo,
   utenti_profilo utpr,
   utenti u,
   ad4_ldap_struttura ldst
 WHERE ldst.figlio = stut.figlio
   AND    --
   -- WHERE  -- utpr.ruolo = stut_ruolo.padre-- modifica Stefania
   --   utpr.ruolo --AGSDE -- modifica Stefania
   stut_ruolo.padre = u.utente
   AND U.GRUPPO_LAVORO = utpr.ruolo  -- modifica Stefania
   AND u.utente LIKE 'o%'   -- modifica Stefania
   AND U.TIPO_UTENTE = 'O'  -- modifica Stefania
   AND utpr.ad4_utente = stut.figlio
   AND stut_ruolo.figlio = stut.figlio
   AND utpr.gruppo = stut.padre
   AND stut.tipo_figlio = 'U'  -- sono utenti
   AND stut.tipo_padre = 'O'    -- è membro del gruppo
   AND DECODE (
   INSTR (stut_ruolo.struttura, '#'),
   0,
   'NO',
   SUBSTR (stut_ruolo.struttura,
  1,
  INSTR (stut_ruolo.struttura, '#') - 1)
   ) = 'R'    -- è un RUOLO
   AND caratteristica_accesso.is_ldapuser (utpr.ad4_utente) = 1
   -- solo utenti con accesso ldap
   AND utente.get_stato (stut.figlio, 'Y') = 'U'
   AND stut_ruolo.tipo_figlio = 'U'  -- sono utenti
   AND stut_ruolo.tipo_padre = 'O' -- è membro del gruppo
   AND DECODE (
   INSTR (stut_ruolo.struttura, '#'),
   0,
   'NO',
   SUBSTR (stut_ruolo.struttura,
  1,
  INSTR (stut_ruolo.struttura, '#') - 1)
   ) = 'R'    -- è un RUOLO
   AND utente.get_stato (stut_ruolo.figlio, 'Y') = 'U'
   UNION ALL
   SELECT   ldst.dn,   --  gruppo INCARICHI-unità_organizzativa
   --ldap_utility.get_name ('CN=' || stut.descrizione),
   'MEMBER',
   LTRIM (
   ldap_utility.formatta_struttura (
   SUBSTR (stut.struttura, 1, INSTR (stut.struttura, '[U#'))
   || 'I#'
   || ruol.ruolo
   || '#'
   || ruol.descrizione
   || '[',
   ruol.descrizione,
   'G'
   ),
   ','
   )
   || ','
   || ldap_utility.get_RootOU
   gruppo
  FROM   struttura_utenti_temp stut,
   ruoli ruol,
   utenti_profilo utpr,
   ad4_ldap_struttura ldst
 WHERE ldst.figlio = stut.figlio
   AND utpr.ruolo = ruol.ruolo
   AND utpr.gruppo = stut.padre
   AND utpr.ad4_utente = stut.figlio
   AND stut.tipo_figlio = 'U'  -- sono utenti
   AND stut.tipo_padre = 'O'    -- è membro del gruppo
   AND stut.gruppo_so = 'N'
   AND ruol.incarico = 'S'
   AND caratteristica_accesso.is_ldapuser (utpr.ad4_utente) = 1
   -- solo utenti con accesso ldap
   AND utente.get_stato (utpr.ad4_utente, 'Y') = 'U'
   UNION ALL
   SELECT   ldst.dn, --ldap_utility.get_name ('CN=' || descrizione) -- tutti gli attributi previsti nei registri
   --   ,
   stringa,
   ldap_utility.calcola_attributo (valore,
     stut.figlio,
     stut.descrizione)
  FROM   struttura_utenti_temp stut, registro, ad4_ldap_struttura ldst
 WHERE ldst.figlio = stut.figlio
   AND tipo_figlio = 'U'    -- sono utenti
   AND tipo_padre = 'O'
   AND DECODE (INSTR (struttura, '#'),
   0, 'NO',
   SUBSTR (struttura, 1, INSTR (struttura, '#') - 1)) =
   'O'
   AND chiave = 'PRODUCTS/LDAPCONFIG/ATTRIBUTI'
   AND stringa != '(Predefinito)'
   AND caratteristica_accesso.is_ldapuser (stut.figlio) = 1
   -- solo utenti con accesso ldap
   AND utente.get_stato (stut.figlio, 'Y') = 'U'
   -- attributi anche se NON ha assegnazione prevalente
--   AND so4_util.get_assegnazione_prev (stut.figlio) = padre
   UNION ALL
   SELECT   --  gruppo ruolo-unita_organizzativa MEMBER OF gruppo del RUOLO
   LTRIM (
   ldap_utility.formatta_struttura (
   SUBSTR (stut.struttura, 1, INSTR (stut.struttura, '[U#'))
   || SUBSTR (stut_ruolo.struttura,
  1,
  INSTR (stut_ruolo.struttura, '[U#')),
   stut_ruolo.padre,
   'O'
   ),
   ','
   )
   || ','
   || ldap_utility.get_RootOU
   dn,
   'MEMBER',
   LTRIM (
   'CN='
   || ldap_utility.pulisci(   ruol.ruolo
   || ldap_utility.get_delimitatore
   || SUBSTR (ruol.descrizione, 1, 40))
   || ',OU=RUOLI,'
   || ldap_utility.get_RootOU,
   ','
   )
  FROM   struttura_utenti_temp stut,
   struttura_utenti_temp stut_ruolo,
   utenti_profilo utpr,
   ruoli ruol,
   utenti u
 WHERE stut_ruolo.padre = u.utente
   AND U.GRUPPO_LAVORO = utpr.ruolo  -- modifica Stefania
   AND u.utente LIKE 'o%'   -- modifica Stefania
   AND U.TIPO_UTENTE = 'O'  -- modifica Stefania
   AND utpr.gruppo = stut.padre
   AND utpr.ad4_utente = stut.figlio
   AND utpr.ad4_utente = stut_ruolo.figlio
   AND stut.tipo_figlio = 'U'  -- sono utenti
   AND stut.tipo_padre = 'O'  --  membro del gruppo
   AND stut.gruppo_so = 'N'
   AND ruol.gruppo_lavoro = 'S'
   AND ruol.gruppo_so = 'S'
   AND utpr.ruolo = ruol.ruolo
   AND DECODE (
   INSTR (stut_ruolo.struttura, '#'),
   0,
   'NO',
   SUBSTR (stut_ruolo.struttura,
  1,
  INSTR (stut_ruolo.struttura, '#') - 1)
   ) = 'R'     --  un RUOLO
   AND stut_ruolo.tipo_figlio = 'U'  -- sono utenti
   AND stut_ruolo.tipo_padre = 'O'  --  membro del gruppo
   AND DECODE (
   INSTR (stut_ruolo.struttura, '#'),
   0,
   'NO',
   SUBSTR (stut_ruolo.struttura,
  1,
  INSTR (stut_ruolo.struttura, '#') - 1)
   ) = 'R'     --  un RUOLO
   AND caratteristica_accesso.is_ldapuser (utpr.ad4_utente) = 1
   --   -- solo utenti con accesso ldap
   AND utente.get_stato (stut_ruolo.figlio, 'Y') = 'U'
   UNION ALL --tutti i cn devono essere membri dei cn che li precedono nell'albero
   SELECT   s.dn,
   'MEMBER',
   'CN'
   || SUBSTR (afc.get_substr (AFC.GET_SUBSTR (AFC.GET_SUBSTR (s.dn,
     ',',
     'P',
     'F'),
    ',',
    'P',
    'F'),
   ',',
   'P',
   'I'), 3)
   || ','
   || AFC.GET_SUBSTR (AFC.GET_SUBSTR (s.dn,
     ',',
     1,
     'U'),
    ',',
    1,
    'U')
  FROM   ad4_ldap_struttura s
 WHERE   dn LIKE 'CN%' AND tipo = 'GROUP'
   AND UPPER (dn) NOT LIKE
   UPPER ('%,OU=RUOLI,' || ldap_utility.get_RootOU)
   AND UPPER (','
  || AFC.GET_SUBSTR (AFC.GET_SUBSTR (s.dn,
    ',',
    1,
    'U'),
   ',',
   1,
   'U')) !=
   UPPER(   ldap_utility.aggiungi_aoo_amm (figlio)
   || ','
   || ldap_utility.get_RootOU)
   AND NOT EXISTS
   (SELECT   *
   FROM   utenti u, ruoli r
  WHERE u.utente = figlio
    AND u.GRUPPO_LAVORO = r.ruolo
    AND r.gruppo_lavoro = 'S'
    AND R.GRUPPO_SO = 'S');


