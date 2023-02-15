CREATE OR REPLACE FORCE VIEW AD4_LDAP_STRUTTURA_VISTA
(DN, TIPO, DESCRIZIONE, FIGLIO)
BEQUEATH DEFINER
AS 
SELECT ldap_utility.get_rootou dn,
 'ORGANIZATIONALUNIT' tipo,
 LTRIM (afc.get_substr (ldap_utility.get_rootou, ',', 'P'), 'OU=')
 descrizione,
 NULL figlio
  FROM DUAL
   UNION ALL
   SELECT DISTINCT --aoo
 LTRIM (
 'OU='
 || codice_aoo
 || ldap_utility.get_DELIMITATORE
 || progr_aoo
 || ',OU=' --  || ldap_utility.concatena (amministrazione
 --   , so4.so4_util.get_des_amministrazione(amministrazione)
 --  )
 || amministrazione
 || ','
 || ldap_utility.get_rootou,
 ',')
 dn,
 'ORGANIZATIONALUNIT' tipo,
 amministrazione descrizione,
 NULL figlio
  FROM anag_unita
 WHERE codice_aoo IS NOT NULL
   UNION ALL
   SELECT DISTINCT --aoo
 LTRIM ('OU=' || amministrazione || ',' || ldap_utility.get_rootou,
  ',')
 dn,
 'ORGANIZATIONALUNIT' tipo,
 amministrazione descrizione,
 NULL figlio
  FROM anag_unita
 WHERE codice_aoo IS NOT NULL
   UNION ALL
   SELECT LTRIM (
 ldap_utility.formatta_struttura (struttura, figlio, 'O')
 || ','
 || ldap_utility.get_rootou,
 ',')
 dn,
 'ORGANIZATIONALUNIT' tipo,
 SUBSTR (
 LTRIM (
 ldap_utility.formatta_struttura (struttura, figlio, 'O')
 || ','
 || ldap_utility.get_rootou,
 ','),
 4 -- salto OU=
  ,
   INSTR (
   LTRIM (
   ldap_utility.formatta_struttura (struttura,
   figlio,
   'O')
   || ','
   || ldap_utility.get_rootou,
   ','),
   ',OU=')
 - 4)
 descrizione,
 figlio
  FROM struttura_utenti_temp struttura_utenti
 WHERE  tipo_figlio = 'O'
 AND gruppo_so = 'N'  -- non sono ruoli
 AND EXISTS
  (SELECT 'x'
  FROM utenti_gruppo
 WHERE utente = struttura_utenti.figlio
 OR gruppo = struttura_utenti.figlio)
   UNION ALL
   SELECT LTRIM (
 ldap_utility.formatta_struttura (struttura, figlio, 'G')
 || ','
 || ldap_utility.get_rootou,
 ','),
 'GROUP',
 SUBSTR (
 LTRIM (
 ldap_utility.formatta_struttura (struttura, figlio, 'G')
 || ','
 || ldap_utility.get_rootou,
 ','),
 4 -- salto OU=
  ,
   INSTR (
   LTRIM (
   ldap_utility.formatta_struttura (struttura,
   figlio,
   'G')
   || ','
   || ldap_utility.get_rootou,
   ','),
   ',OU=')
 - 4)
 descrizione,
 figlio
  FROM struttura_utenti_temp struttura_utenti
 WHERE  tipo_figlio = 'O'
 AND gruppo_so = 'N'  -- non sono ruoli
 AND EXISTS
  (SELECT 'x'
  FROM utenti_gruppo
 WHERE utente = struttura_utenti.figlio
 OR gruppo = struttura_utenti.figlio)
   UNION ALL
   SELECT   --  gruppo ruolo-unita_organizzativa
   LTRIM (
 ldap_utility.formatta_struttura (
 SUBSTR (stut.struttura,
   1,
   INSTR (stut.struttura, '[U#'))
 || SUBSTR (stut_ruolo.struttura,
   1,
   INSTR (stut_ruolo.struttura, '[U#')),
 stut_ruolo.padre,
 'O'),
 ',')
 || ','
 || ldap_utility.get_rootou
 dn,
 'GROUP',
 SUBSTR (
 LTRIM (
 ldap_utility.formatta_struttura (
 SUBSTR (stut.struttura,
   1,
   INSTR (stut.struttura, '[U#'))
 || SUBSTR (stut_ruolo.struttura,
   1,
   INSTR (stut_ruolo.struttura, '[U#')),
 stut_ruolo.padre,
 'O'),
 ',')
 || ','
 || ldap_utility.get_rootou,
 4 -- salto OU=
  ,
   INSTR (
   LTRIM (
   ldap_utility.formatta_struttura (
   SUBSTR (stut.struttura,
  1,
  INSTR (stut.struttura, '[U#'))
   || SUBSTR (stut_ruolo.struttura,
  1,
  INSTR (stut_ruolo.struttura, '[U#')),
   stut_ruolo.padre,
   'O'),
   ',')
   || ','
   || ldap_utility.get_rootou,
   ',OU=')
 - 4)
 descrizione,
 stut_ruolo.padre
  FROM struttura_utenti_temp stut,
 struttura_utenti_temp stut_ruolo,
 utenti_profilo utpr,
 utenti u
 WHERE -- utpr.ruolo = stut_ruolo.padre-- modifica Stefania
  --   utpr.ruolo --AGSDE -- modifica Stefania
  stut_ruolo.padre = u.utente
 AND U.GRUPPO_LAVORO = utpr.ruolo -- modifica Stefania
 and u.utente like 'o%' -- modifica Stefania
 AND U.TIPO_UTENTE = 'O' -- modifica Stefania
 AND utpr.gruppo = stut.padre
 AND utpr.ad4_utente = stut.figlio
 AND utpr.ad4_utente = stut_ruolo.figlio
 AND stut.tipo_figlio = 'U' -- sono utenti
 AND stut.tipo_padre = 'O' --  membro del gruppo
 AND stut.gruppo_so = 'N'
 AND DECODE (
  INSTR (stut_ruolo.struttura, '#'),
  0, 'NO',
  SUBSTR (stut_ruolo.struttura,
 1,
 INSTR (stut_ruolo.struttura, '#') - 1)) = 'R' --  un RUOLO
 AND stut_ruolo.tipo_figlio = 'U' -- sono utenti
 AND stut_ruolo.tipo_padre = 'O' --  membro del gruppo
 AND DECODE (
  INSTR (stut_ruolo.struttura, '#'),
  0, 'NO',
  SUBSTR (stut_ruolo.struttura,
 1,
 INSTR (stut_ruolo.struttura, '#') - 1)) = 'R' --  un RUOLO
 AND caratteristica_accesso.is_ldapuser (utpr.ad4_utente) = 1
 --   -- solo utenti con accesso ldap
 AND utente.get_stato (stut_ruolo.figlio, 'Y') = 'U'
   UNION ALL
   SELECT -- le persone suppongo siano nell'albero indicato nei registri
   'CN=' || descrizione || ',' || ldap_utility.get_RootUsers,
 'PERSON',
 descrizione,
 figlio
  FROM struttura_utenti_temp
 WHERE  DECODE (tipo_figlio, 'U', utente.get_stato (figlio, 'Y'), 'N') =
  'U'
 AND tipo_figlio = 'U'   -- sono utenti
 AND tipo_padre = 'O'
 AND DECODE (INSTR (struttura, '#'),
 0, 'NO',
 SUBSTR (struttura, 1, INSTR (struttura, '#') - 1)) =
  'O'
 AND caratteristica_accesso.is_ldapuser (figlio) = 1
   -- solo utenti con accesso ldap
   UNION ALL
   SELECT  --  gruppo INCARICHI-unita_organizzativa
   LTRIM (
 ldap_utility.formatta_struttura (
 SUBSTR (stut.struttura,
   1,
   INSTR (stut.struttura, '[U#'))
 || 'I#'
 || ruol.ruolo
 || '#'
 || ruol.descrizione
 || '[',
 ruol.descrizione,
 'G'),
 ',')
 || ','
 || ldap_utility.get_RootOU
 dn,
 'GROUP',
 SUBSTR (
 LTRIM (
 ldap_utility.formatta_struttura (
 SUBSTR (stut.struttura,
   1,
   INSTR (stut.struttura, '[U#'))
 || 'I#'
 || ruol.ruolo
 || '#'
 || ruol.descrizione
 || '[',
 ruol.descrizione,
 'G'),
 ',')
 || ','
 || ldap_utility.get_RootOU,
 4 -- salto OU=
  ,
   INSTR (
   LTRIM (
   ldap_utility.formatta_struttura (
   SUBSTR (stut.struttura,
  1,
  INSTR (stut.struttura, '[U#'))
   || 'I#'
   || ruol.ruolo
   || '#'
   || ruol.descrizione
   || '[',
   ruol.descrizione,
   'G'),
   ',')
   || ','
   || ldap_utility.get_RootOU,
   ',OU=')
 - 4)
 descrizione,
 ruol.ruolo
  FROM struttura_utenti_temp stut, ruoli ruol, utenti_profilo utpr
 WHERE  utpr.ruolo = ruol.ruolo
 AND utpr.gruppo = stut.padre
 AND utpr.ad4_utente = stut.figlio
 AND stut.tipo_figlio = 'U' -- sono utenti
 AND stut.tipo_padre = 'O' --  membro del gruppo
 AND stut.gruppo_so = 'N'
 AND ruol.incarico = 'S'
 AND caratteristica_accesso.is_ldapuser (utpr.ad4_utente) = 1
 -- solo utenti con accesso ldap
 AND utente.get_stato (utpr.ad4_utente, 'Y') = 'U'
   UNION ALL
   SELECT --DISTINCT  --  elenco RUOLI
   LTRIM (
 'CN='
 || ldap_utility.pulisci (
 ruolo
 || ldap_utility.get_delimitatore
 || SUBSTR (descrizione, 1, 40))
 || ',OU=RUOLI,'
 || ldap_utility.get_RootOU,
 ',')
 dn,
 'GROUP' tipo,
 ruolo
 || ldap_utility.get_delimitatore
 || SUBSTR (descrizione, 1, 40)
 descrizione,
 NULL figlio
  FROM ruoli
 WHERE gruppo_lavoro = 'S' AND gruppo_so = 'S'
   UNION ALL
   SELECT 'OU=RUOLI,' || ldap_utility.get_RootOU dn,
 'ORGANIZATIONALUNIT' tipo,
 'RUOLI' descrizione,
 NULL figlio
  FROM DUAL;


