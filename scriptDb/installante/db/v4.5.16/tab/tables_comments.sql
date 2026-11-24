--liquibase formatted sql

--changeset mturra:2019032811215 runOnChange=true

COMMENT ON TABLE COMUNI IS 'COMU - Tabella dei comuni italiani';
COMMENT ON COLUMN COMUNI.DENOMINAZIONE IS 'Descrizione ufficiale del comune';
COMMENT ON COLUMN COMUNI.DENOMINAZIONE_BREVE IS 'Descrizione breve del comune';
COMMENT ON TABLE CONSOLATI IS 'CONS - Tabella dei consolati';
COMMENT ON TABLE DIRITTI_ACCESSO IS 'DIAC - Tabella dei diritti di accesso';
COMMENT ON TABLE DISABILITAZIONI IS 'DISA - Tabella delle disabilitazioni';
COMMENT ON TABLE DUAL_PB IS 'DUPB - Tabella di appoggio';
COMMENT ON COLUMN EVENTI.GRAVITA IS 'Gravita dell''evento';
COMMENT ON COLUMN KEY_ERROR.DESCRIZIONE IS 'Descrizione ERRORE <NLS>';
COMMENT ON TABLE MODULI IS 'MODU - Tabella dei moduli applicativi';
COMMENT ON COLUMN MODULI.AMMINISTRATORE IS 'Indica se modulo  utilizzato per funzioni di AMMINISTRAZIONE';
COMMENT ON TABLE PROVINCE IS 'PROV - Tabella delle province italiane';
COMMENT ON COLUMN PROVINCE.DENOMINAZIONE IS 'Dominazione della provincia';
COMMENT ON TABLE RAGGRUPPAMENTI_STATI IS 'RAST - Tabella dei raggruppamenti degli stati';
COMMENT ON COLUMN RAGGRUPPAMENTI_STATI.DESCRIZIONE IS 'Descrizione';
COMMENT ON TABLE REGIONI IS 'REGI - Tabella delle regioni italiane';
COMMENT ON COLUMN REGIONI.DENOMINAZIONE IS 'Denominazione della regione';
COMMENT ON TABLE REGISTRO IS 'REGS - Voci di Registro';
COMMENT ON COLUMN RUOLI.INCARICO IS 'Se incarico = S/N';
COMMENT ON TABLE SO4_SO_TEMP IS 'SSTE - Tabella temporanea per memorizzazione struttura SO4';
COMMENT ON TABLE SO_TEMP IS 'SOTE - Tabella temporanea per memorizzazione struttura AD4';
COMMENT ON TABLE STATI_TERRITORI IS 'STTE - Tabella degli stati e dei territori';
COMMENT ON COLUMN STATI_TERRITORI.DENOMINAZIONE IS 'Denominazione dello stato/territorio estero';
COMMENT ON COLUMN STATI_TERRITORI.DESC_CITTADINANZA IS 'Descrizione della cittadinanza';
COMMENT ON COLUMN UTENTI.AMMINISTRATORE IS 'Indica se utenza utilizzata per funzioni di AMMINISTRAZIONE';
COMMENT ON COLUMN UTENTI.INFO_IDENTIFICAZIONE IS 'Informazioni per individuare chi utilizza una specifica utenza di AMMINISTRAZIONE';
COMMENT ON COLUMN DIRITTI_ACCESSO_STORICO.BI_RIFERIMENTO IS 'id_evento di Before Image di riferimento';
COMMENT ON TABLE UTENTI_SOGGETTI_STORICO IS 'UTSS - UTENTI SOGGETTI STORICO';
COMMENT ON COLUMN UTENTI_SOGGETTI_STORICO.OPERAZIONE IS 'I=Insert, D=Delete, BI=Before Image, AI=After Image';
COMMENT ON COLUMN UTENTI_SOGGETTI_STORICO.BI_RIFERIMENTO IS 'id_evento di Before Image di riferimento';
COMMENT ON TABLE UTENTI_GRUPPO_STORICO IS 'UTGS - UTENTI GRUPPO STORICO';

COMMENT ON COLUMN UTENTI_GRUPPO_STORICO.BI_RIFERIMENTO IS 'id_evento di Before Image di riferimento';

COMMENT ON COLUMN UTENTI_GRUPPO_STORICO.OPERAZIONE IS 'I=Insert, D=Delete, BI=Before Image, AI=After Image';

COMMENT ON TABLE UTENTI_STORICO IS 'UTES - UTENTI STORICO';

COMMENT ON COLUMN UTENTI_STORICO.BI_RIFERIMENTO IS 'id_evento di Before Image di riferimento';

COMMENT ON COLUMN UTENTI_STORICO.OPERAZIONE IS 'I=Insert, D=Delete, BI=Before Image, AI=After Image';