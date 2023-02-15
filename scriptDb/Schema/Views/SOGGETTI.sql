CREATE OR REPLACE FORCE VIEW SOGGETTI
(SOGGETTO, NOME, SESSO, DATA_NASCITA, PROVINCIA_NAS, 
 COMUNE_NAS, CODICE_FISCALE, INDIRIZZO, CAP, COMUNE, 
 PROVINCIA, TELEFONO, FAX, INDIRIZZO_WEB, NOTE, 
 UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, COMPETENZA, COMPETENZA_ESCLUSIVA, DAL)
BEQUEATH DEFINER
AS 
SELECT anso.ni Soggetto,
          anso.denominazione NOME,
          anso.sesso SESSO,
          anso.data_nas DATA_NASCITA,
          anso.provincia_nas PROVINCIA_NAS,
          anso.comune_nas COMUNE_NAS,
          anso.Codice_Fiscale Codice_Fiscale,
          anso.indirizzo_res INDIRIZZO,
          anso.cap_res CAP,
          anso.comune_res Comune,
          anso.provincia_res Provincia,
          anso.tel_res TELEFONO,
          anso.fax_res FAX,
          anso.indirizzo_web INDIRIZZO_WEB,
          anso.note NOTE,
          anso.Utente UTENTE_AGGIORNAMENTO,
          anso.data_agg DATA_AGGIORNAMENTO,
          anso.COMPETENZA,
          anso.COMPETENZA_ESCLUSIVA,
          anso.dal
     FROM AS4_ANAGRAFE_SOGGETTI anso
    WHERE ANSO.AL IS NULL;


