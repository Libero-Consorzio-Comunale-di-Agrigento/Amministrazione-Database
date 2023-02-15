﻿Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('ENTI', 'PK', 1, 'ENTI_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('PROGETTI', 'PK', 1, 'PROG_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('ISTANZE', 'PK', 1, 'ISTA_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('ISTANZE', 'FK', 1, 'ISTA_PROG_FK', NULL, 
    NULL, 'S', NULL, 'PROGETTI', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('ISTANZE', 'FK', 2, 'ISTA_ENTI_FK', NULL, 
    NULL, 'S', NULL, 'ENTI', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('RUOLI', 'PK', 1, 'RUOL_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIRITTI_ACCESSO', 'PK', 1, 'DIRI_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIRITTI_ACCESSO', 'FK', 2, 'DIRI_RUOL_FK', NULL, 
    NULL, 'S', NULL, 'RUOLI', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIRITTI_ACCESSO', 'FK', 3, 'DIRI_ISTA_FK', NULL, 
    NULL, 'S', NULL, 'ISTANZE', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('MODULI', 'FK', 1, 'MODU_PROG_FK', NULL, 
    NULL, 'S', NULL, 'PROGETTI', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('MODULI', 'PK', 1, 'MODU_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIRITTI_ACCESSO', 'FK', 4, 'DIRI_MODU_FK', NULL, 
    NULL, 'S', NULL, 'MODULI', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DISABILITAZIONI', 'PK', 1, 'DISA_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('UTENTI', 'PK', 1, 'UTEN_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('PROGETTI', 'ED', 2, 'PROG_ED', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('PROGETTI', 'ED', 1, 'PROG_CAAC_ED', NULL, 
    'PROG_ED', 'N', 'FINE', NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('ISTANZE', 'ED', 1, 'ISTA_CAAC_ED', NULL, 
    'ISTA_ED', 'N', 'FINE', NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('ISTANZE', 'ED', 2, 'ISTA_ED', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('MODULI', 'ED', 2, 'MODU_ED', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('MODULI', 'ED', 1, 'MODU_CAAC_ED', NULL, 
    'MODU_ED', 'N', 'FINE', NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIAC', 'ED', 1, 'DIRI_CAAC_ED', NULL, 
    'DIRI_ED', 'N', 'FINE', NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIAC', 'ED', 2, 'DIRI_ED', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('UTENTI_GRUPPO', 'PK', 1, 'UTGR_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('UTENTI_GRUPPO', 'FK', 2, 'UTGR_GRUP_FK', NULL, 
    NULL, 'S', NULL, 'UTENTI', NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('UTENTI_GRUPPO', 'FK', 1, 'UTGR_UTEN_FK', NULL, 
    NULL, 'S', NULL, 'UTENTI', NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('STATI_TERRITORI', 'EI', 1, 'STTE_EI', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('STATI_TERRITORI', 'PK', 1, 'STTE_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('COMUNI', 'PK', 1, 'COMU_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIRITTI_ACCESSO', 'UP', 4, 'DIRI_MODU_CHK_ISTA_UP', NULL, 
    NULL, 'N', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('COMUNI', 'FK', 1, 'COMU_STTE_FK', NULL, 
    NULL, 'S', NULL, 'STATI_TERRITORI', 'DENOMINAZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIRITTI_ACCESSO', 'UP', 1, 'DIRI_ISTA_PROG_UP', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIRITTI_ACCESSO', 'UP', 3, 'DIRI_MODU_PROG_UP', NULL, 
    'DIRI_MODU_CHK_ISTA_UP', 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIRITTI_ACCESSO', 'UP', 2, 'DIRI_ISTA_CHK_MODU_UP', NULL, 
    NULL, 'N', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('RUOLI', 'UP', 4, 'RUOL_CHK2_MODU_UP', NULL, 
    NULL, 'N', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('RUOLI', 'FK', 1, 'RUOL_MODU_FK', NULL, 
    NULL, 'S', NULL, 'MODULI', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('RUOLI', 'FK', 2, 'RUOL_PROG_FK', NULL, 
    NULL, 'S', NULL, 'PROGETTI', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('RUOLI', 'UP', 2, 'RUOL_CHK2_PROG_UP', NULL, 
    NULL, 'N', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('RUOLI', 'UP', 1, 'RUOL_CHK_PROG_UP', NULL, 
    NULL, 'N', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('RUOLI', 'UP', 3, 'RUOL_CHK_MODU_UP', NULL, 
    NULL, 'N', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIRITTI_ACCESSO', 'FR', 1, 'DIRI_RUOL_FR', NULL, 
    NULL, 'S', NULL, 'RUOLI', NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('LINGUE', 'PK', 1, 'LING_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('GRUPPI_LINGUISTICI', 'PK', 1, 'GRLI_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('GRUPPI_LINGUISTICI', 'FK', 1, 'GRLI_LING_FK', NULL, 
    NULL, 'S', NULL, 'LINGUE', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('GRUPPI_LINGUISTICI', 'FK', 2, 'GRLI_LING_AL_FK', NULL, 
    NULL, 'S', NULL, 'LINGUE', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('LINGUE', 'BD', 1, 'LING_BD', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('GRUPPI_LINGUISTICI', 'FR', 2, 'GRLI_LING_AL_FR', NULL, 
    NULL, 'S', NULL, 'LINGUE', NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('GRUPPI_LINGUISTICI', 'FR', 1, 'GRLI_LING_FR', NULL, 
    NULL, 'S', NULL, 'LINGUE', NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('TIPI_CREDENZIALE', 'PK', 1, 'TICR_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('LIVELLI_SICUREZZA', 'PK', 1, 'LISI_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('CREDENZIALI_LIVELLO', 'PK', 1, 'CRLI_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('SERVIZI', 'FK', 1, 'ISTA_SERV_FK', NULL, 
    NULL, 'S', NULL, 'ISTANZE', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('TIPI_CREDENZIALE', 'EU', 2, 'TICR_UPD_EU', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('CREDENZIALI_LIVELLO', 'FK', 1, 'CRLI_LISI_FK', NULL, 
    NULL, 'S', NULL, 'LIVELLI_SICUREZZA', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('TIPI_CREDENZIALE', 'ED', 1, 'TICR_UPD_ED', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('TIPI_CREDENZIALE', 'EI', 1, 'TICR_EI', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('TIPI_CREDENZIALE', 'EU', 1, 'TICR_DEL_EU', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('CREDENZIALI', 'PK', 1, 'CRED_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('SERVIZI', 'PK', 1, 'SERV_PK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('CREDENZIALI', 'UK', 1, 'CRED_CHIAVE_AK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('SERVIZI', 'UK', 1, 'SERV_AK', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('SERVIZI', 'FK', 2, 'MODU_SERV_FK', NULL, 
    NULL, 'S', NULL, 'MODULI', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('SERVIZI', 'FK', 3, 'LISI_SERV_FK', NULL, 
    NULL, 'S', NULL, 'LIVELLI_SICUREZZA', 'DESCRIZIONE', 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('STATI_TERRITORI', 'EU', 1, 'STTE_EU', NULL, 
    NULL, 'S', NULL, NULL, NULL, 
    'X', 'X');
Insert into KEY_CONSTRAINT
   (OGGETTO, TIPO, SEQUENZA, NOME, NOTE, 
    LABEL_SUCCESS, FLAG_ABORT, LABEL_FAILURE, RIF_OGGETTO, RIF_DESCRIPTOR, 
    CASCADE_UPDATE, CASCADE_DELETE)
 Values
   ('DIRITTI_ACCESSO', 'FK', 1, 'DIRI_UTEN_FK', NULL, 
    NULL, 'S', NULL, 'UTENTI', 'NOMINATIVO', 
    'X', 'X');
COMMIT;