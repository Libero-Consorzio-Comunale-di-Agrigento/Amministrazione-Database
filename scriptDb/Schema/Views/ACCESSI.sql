CREATE OR REPLACE FORCE VIEW ACCESSI
(ACCE_ID, SESSION_ID, DATA, LOG, UTENTE, 
 ISTANZA, MODULO, DB_USER, TERMINALE, NOTE, 
 ID_CREDENZIALE, TIPO_AUTENTICAZIONE, STATO)
BEQUEATH DEFINER
AS 
SELECT ID_EVENTO ACCE_ID,
          --TO_NUMBER (AFC.GET_STRINGPARM (TESTO, 'session_id')) SESSION_ID,
          SESSION_ID, --#57534
          DATA DATA,
          SUBSTR (TIPO, 4) LOG,
          UTENTE UTENTE,
          ISTANZA ISTANZA,
          MODULO MODULO,
          DB_USER DB_USER,
          AFC.GET_STRINGPARM (TESTO, 'terminale') TERMINALE,
          ANNOTAZIONI NOTE,
          TO_NUMBER (AFC.GET_STRINGPARM (TESTO, 'id_credenziale'))
             ID_CREDENZIALE,
          AFC.GET_STRINGPARM (TESTO, 'tipo_autenticazione')
             TIPO_AUTENTICAZIONE,
          STATO
     FROM EVENTI
    WHERE                                        --SUBSTR (TIPO, 1, 3) = 'LOG'
         TIPO LIKE 'LOG%';

COMMENT ON TABLE ACCESSI IS 'ACCE - Vista delle registrazioni di accesso';



