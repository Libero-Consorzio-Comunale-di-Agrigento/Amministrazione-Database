Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('AD4', 'AD4', 'AD4', 'AMM', 1, 
    NULL, 0, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('AD4', 'AD4WEB', 'AD4', 'AMM', NULL, 
    NULL, NULL, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('GRPLDAP', 'MODULDAP', 'ISTALDAP', 'AMM', NULL, 
    NULL, NULL, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('GUESTS', 'AD4WEB', 'AD4', 'GUEST', NULL, 
    NULL, NULL, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('GUEST', 'AD4WEB', 'AD4', 'GUEST', 1, 
    NULL, NULL, 'GUESTS', NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('_GR_AMM_', 'SI4_AMM', 'G_U_AMM', 'AMM', NULL, 
    NULL, NULL, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('_GR_APP_', 'SI4_UTE', 'G_U_APP', 'OPE', NULL, 
    NULL, NULL, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('AD4', 'SI4_UTE', 'G_U_APP', 'OPE', 2, 
    NULL, NULL, '_GR_APP_', NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('GUEST', 'SI4_UTE', 'G_U_APP', 'OPE', 2, 
    NULL, NULL, '_GR_APP_', NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('AS4', 'SI4_UTE', 'G_U_APP', 'OPE', 1, 
    NULL, NULL, '_GR_APP_', NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('GRPLDAP', 'MAMMLDAP', 'ISTALDAP', 'AMM', NULL, 
    NULL, NULL, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('AS4', 'AS4', 'AS4', 'AMM', NULL, 
    NULL, NULL, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('SO4', 'SI4SO', 'SO4', 'AMM', NULL, 
    NULL, NULL, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('WS_G_ANA', 'AS4', 'AS4', 'WS', NULL, 
    NULL, NULL, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('o3', 'AS4', 'AS4', 'AMM', NULL, 
    NULL, NULL, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('o4', 'AS4', 'AS4', 'OPE', NULL, 
    NULL, NULL, NULL, NULL);
Insert into DIRITTI_ACCESSO
   (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, 
    ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
 Values
   ('SO4', 'SI4_UTE', 'G_U_APP', 'OPE', 1, 
    NULL, NULL, '_GR_APP_', NULL);
COMMIT;
