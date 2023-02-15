Insert into EVENTI
   (ID_EVENTO, TESTO, ANNOTAZIONI, TIPO, GRAVITA, 
    DB_USER, UTENTE, MODULO, ISTANZA, DATA, 
    NOTIFICATO, STATO, SESSION_ID)
 Values
   (1, 'Modulo Amministratore(''SI4_AMM'') modificato attributo amministratore : S', ' Il modulo in oggetto e'' diventato AMMINISTRATORE', 'APPTRC', 'I', 
    'AD4', 'SI4_AMM', NULL, NULL, TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 
    0, 'U', NULL);
Insert into EVENTI
   (ID_EVENTO, TESTO, ANNOTAZIONI, TIPO, GRAVITA, 
    DB_USER, UTENTE, MODULO, ISTANZA, DATA, 
    NOTIFICATO, STATO, SESSION_ID)
 Values
   (2, 'Modulo Amministratore(''MAMMLDAP'') modificato attributo amministratore : S', ' Il modulo in oggetto e'' diventato AMMINISTRATORE', 'APPTRC', 'I', 
    'AD4', 'MAMMLDAP', NULL, NULL, TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 
    0, 'U', NULL);
COMMIT;
