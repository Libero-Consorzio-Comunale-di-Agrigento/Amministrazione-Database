Insert into CREDENZIALI
   (ID_CREDENZIALE, TIPO_CREDENZIALE, CHIAVE_CREDENZIALE, EMITTENTE, STATO, 
    UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO)
 Values
   (1, 'CRS', 'CN=Regione Lombardia Certification Authority Cittadini, OU=Servizi di certificazione, O=Actalis S.p.A., C=IT', 'CN=Regione Lombardia Certification Authority Cittadini, OU=Servizi di certificazione, O=Actalis S.p.A., C=IT', 'U', 
    NULL, TO_DATE('06/19/2006 15:24:30', 'MM/DD/YYYY HH24:MI:SS'));
Insert into CREDENZIALI
   (ID_CREDENZIALE, TIPO_CREDENZIALE, CHIAVE_CREDENZIALE, EMITTENTE, STATO, 
    UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO)
 Values
   (2, 'CRS', 'CN=Regione Lombardia Certification Authority Cittadini, OU=Servizi di certificazione, O=I.T. Telecom S.R.L., C=IT', 'CN=Regione Lombardia Certification Authority Cittadini, OU=Servizi di certificazione, O=I.T. Telecom S.R.L., C=IT', 'U', 
    NULL, TO_DATE('06/19/2006 15:23:48', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;
