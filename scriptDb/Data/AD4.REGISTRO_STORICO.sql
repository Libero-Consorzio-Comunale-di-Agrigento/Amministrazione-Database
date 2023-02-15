Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (1, 'PRODUCTS/AUTHENTICATION', 'LoginURL', 'Login URL Shibboleth', NULL, 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (2, 'PRODUCTS/AUTHENTICATION', 'LogoutURL', 'Logout URL Shibboleth', NULL, 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (3, 'PRODUCTS/AD4/UTENTE', 'NotificaModificaAmministratoreSiNo', 'Invio mail di notifica se per un utente viene modificato il tipo di utenza (Amministratore o meno)  (Valori possibili: SI/NO - default: NO).', NULL, 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (4, 'PRODUCTS/AD4/UTENTE/NOTIFICA', '(Predefinito)', NULL, NULL, 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (5, 'PRODUCTS/AD4/UTENTE/NOTIFICA', 'Sender', 'Indirizzo e-mail che spedisce le notifiche automatiche generate in fase di modifiche relative agli utenti.', NULL, 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (6, 'PRODUCTS/AD4/UTENTE/NOTIFICA', 'NotificaMail', 'Indirizzo al quale notificare le modifiche relative agli utenti.', NULL, 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (7, 'PRODUCTS/AD4/UTENTE/NOTIFICA', 'Tag', 'Tag da utilizzare per l''invio delle mail automatiche generate in fase di modifica degli utenti. Il tag presente nel file si4cim.cfg del server (default: mail).', NULL, 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (8, 'PRODUCTS/AUTHENTICATION', 'ExternalAutentication', 'Parametro di registro che indica se autenticazione esterna quale metodo viene usato (shibboleth/cas/... - default:null => autenticazione in ad4 o ldap)', NULL, 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (9, 'PRODUCTS/AD4/ASSISTENTE_VIRTUALE', 'UtenzaAssistenteVirtuale', NULL, NULL, 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (10, 'PRODUCTS/AD4/ASSISTENTE_VIRTUALE', 'PwdUtenzaAssistenteVirtuale', NULL, NULL, 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (11, 'PRODUCTS/AD4/ASSISTENTE_VIRTUALE', 'UrlAssistenteVirtuale', NULL, 'https://assistente-virtuale.e-pal.it', 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (12, 'PRODUCTS/AUTHENTICATION', 'CryptAlgorithm', 'Algoritmo x criptare (Valori possibili: STANDARD=metodo usato fino ad ora, LATEST=metodo in base alla versione Oracle, metodo specifico - default STANDARD)', 'STANDARD', 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
Insert into REGISTRO_STORICO
   (ID_EVENTO, CHIAVE, STRINGA, COMMENTO, VALORE, 
    DATA, OPERAZIONE, BI_RIFERIMENTO, UTENTE_AGG, USER_ORACLE, 
    INFO, PROGRAMMA)
 Values
   (13, 'PRODUCTS/AMV', 'Disabilita Menu se PWD da cambiare', 'Disabilita menù se password scaduta per forzare il cambio password (Valori possibili: SI/NO - default NO)', 'SI', 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'I', NULL, NULL, 'AD4', 
    'SVI-AT-CD-AS2::UNKNOWN::ADSADMIN', 'JDBC Thin Client');
COMMIT;
