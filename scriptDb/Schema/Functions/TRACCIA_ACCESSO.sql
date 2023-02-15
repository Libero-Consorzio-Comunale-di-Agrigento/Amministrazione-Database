CREATE OR REPLACE FUNCTION Traccia_Accesso
( p_session_id NUMBER
, p_istanza VARCHAR2
, p_modulo VARCHAR2
, p_utente VARCHAR2
, p_testo VARCHAR2
)
RETURN NUMBER IS
   PRAGMA AUTONOMOUS_TRANSACTION;
tmpVar NUMBER;
/******************************************************************************
 NOME:        TRACCIA_ACCESSO
 DESCRIZIONE: .
 PARAMETRI:   p_session_id number     numero identificativo della sessione
              p_istanza    varchar2   codice istanza a cui l'utente e connesso
              p_modulo     varchar2   codice modulo a cui l'utente e connesso
              p_utente     varchar2   codice utente applicativo connesso
           p_testo      varchar2   testo della traccia da registrare
 RITORNA:     number: identificativo dell'accesso registrato.
 ANNOTAZIONI: Salvata nella directory ins di AD4 con nome TRAC.CRF.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    24/05/2005 MM     Prima emissione.
******************************************************************************/
BEGIN
  tmpVar := Accesso.REGISTRA_ACCESSO(p_session_id, 'TRC', p_istanza, p_modulo, p_utente, p_testo, NULL);
  COMMIT;
  RETURN tmpVar;
EXCEPTION
   WHEN OTHERS THEN
      ROLLBACK;
      RETURN -1;
END Traccia_Accesso;
/

