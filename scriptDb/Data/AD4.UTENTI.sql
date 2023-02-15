﻿Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('o1', 1, 'Amministratore AD4 - Amm. Database', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'O', 
    'U', '*', 'AD4AMM', NULL, NULL, 
    TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('o2', 2, 'Operatore', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'O', 
    'U', '*', 'OPE', NULL, NULL, 
    TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('_GR_AMM_', 3, 'Gruppo Utenti Amministratori', NULL, NULL, 
    NULL, 'SI', NULL, NULL, 'G', 
    'U', '*', 'def', NULL, NULL, 
    TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), '_GR_AMM_', TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), 'S', 'Gruppo Utenti Amministratori');
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('_GR_APP_', 4, 'Gruppo Utenti Applicativi', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'G', 
    'U', '*', 'def', NULL, NULL, 
    TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('AD4', 5, 'AD4', 'EY', NULL, 
    NULL, 'NO', NULL, NULL, 'U', 
    'U', '*', 'def', NULL, NULL, 
    TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), 'AD4', TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('ric_abil', 6, 'Richieste di Abilitazione', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'G', 
    'U', '*', 'def', NULL, NULL, 
    TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:16:55', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('GRPLDAP', 7, 'Gruppo Utenti LDAP', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'G', 
    'U', '*', 'def', NULL, NULL, 
    TO_DATE('02/15/2023 00:16:56', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:16:56', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('GUESTS', 8, 'Gruppo Guest', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'G', 
    'U', '*', 'def', NULL, NULL, 
    TO_DATE('02/15/2023 00:16:56', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:16:56', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('ADMINS', 9, 'Gruppo Amministratori', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'G', 
    'U', '*', 'def', NULL, NULL, 
    TO_DATE('02/15/2023 00:16:56', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:16:56', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('GUEST', 10, 'GUEST', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'U', 
    'U', '*', 'def', NULL, NULL, 
    TO_DATE('02/15/2023 00:16:56', 'MM/DD/YYYY HH24:MI:SS'), 'GUEST', TO_DATE('02/15/2023 00:16:56', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('AS4', 11, 'AS4', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'U', 
    'U', '*', 'def', NULL, NULL, 
    TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'AS4', TO_DATE('02/15/2023 00:18:18', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('o5', 16, 'AMMINISTRATORE STRUTTURA ORGANIZZATIVA', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'O', 
    'U', '*', 'SO4AMM', NULL, NULL, 
    TO_DATE('02/15/2023 00:40:07', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:40:07', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('o6', 17, 'SO4 SEGRETERIA DI DIREZIONE', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'O', 
    'U', '*', 'SO4SEG', NULL, NULL, 
    TO_DATE('02/15/2023 00:40:07', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:40:07', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('o7', 18, 'VISUALIZZATORE STRUTTURA ORGANIZZATIVA', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'O', 
    'U', '*', 'SO4VIS', NULL, NULL, 
    TO_DATE('02/15/2023 00:40:07', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:40:07', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('WS_G_ANA', 12, 'Gruppo Web Service ANAGRAFICI', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'G', 
    'U', '*', 'def', NULL, NULL, 
    TO_DATE('02/15/2023 00:38:27', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:38:27', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('o3', 13, 'Anagrafica Amministratore', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'O', 
    'U', '*', 'AS4AMM', NULL, NULL, 
    TO_DATE('02/15/2023 00:38:29', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:38:29', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('o4', 14, 'Anagrafica Utente', NULL, NULL, 
    NULL, 'NO', NULL, NULL, 'O', 
    'U', '*', 'AS4OPE', NULL, NULL, 
    TO_DATE('02/15/2023 00:38:29', 'MM/DD/YYYY HH24:MI:SS'), NULL, TO_DATE('02/15/2023 00:38:29', 'MM/DD/YYYY HH24:MI:SS'), 'N', NULL);
Insert into UTENTI
   (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, 
    RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, 
    STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, 
    DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
 Values
   ('SO4', 15, 'SO4', '~OY', TO_DATE('02/15/2023 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    'SI', 'NO', NULL, 0, 'U', 
    'U', 'I', NULL, NULL, NULL, 
    TO_DATE('02/15/2023 00:40:05', 'MM/DD/YYYY HH24:MI:SS'), 'SO4', TO_DATE('02/15/2023 00:40:05', 'MM/DD/YYYY HH24:MI:SS'), NULL, NULL);
COMMIT;
