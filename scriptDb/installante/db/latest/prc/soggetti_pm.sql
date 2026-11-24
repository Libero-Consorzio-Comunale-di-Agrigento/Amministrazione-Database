--liquibase formatted sql

--changeset mturra:201901301231_340 runOnChange:true endDelimiter:/ stripComments:false

CREATE OR REPLACE PROCEDURE SOGGETTI_PM
/******************************************************************************
 NOME:        SOGGETTI_PM.
 DESCRIZIONE: Procedure per inserimento / aggiornamento di un soggetto anagrafico.
 ARGOMENTI:   p_soggetto       IN OUT number   Riceve (Update) o restituisce (Insert)
                                               il numero indiiduale del soggetto.
              p_nome           IN     varchar2 Nominativo del soggetto.
              p_sesso          IN     varchar2 Sesso del soggetto.
                                               Valori possibili: F/M/null.
              p_data_nascita   IN     varchar2 Data di nascita del soggetto come
                                               stringa in formato dd/mm/yyyy.
              p_provincia_nas  IN     number   Codice della provincia di nascita
                                               del soggetto.
              p_comune_nas     IN     number   Codice del comune di nascita
                                               del soggetto.
              p_codice_fiscale IN     varchar2 Codice fiscale del soggetto.
              p_indirizzo      IN     varchar2 Indirizzo del soggetto.
              p_cap            IN     varchar2 CAP di residenza del soggetto.
              p_comune         IN     number   Codice della provincia di residenza
                                               del soggetto.
              p_provincia      IN     number   Codice del comune di residenza
                                               del soggetto.
              p_telefono       IN     varchar2 Numero di telefono del soggetto.
              p_fax            IN     varchar2 Numero di fax del soggetto.
              p_indirizzo_web  IN     varchar2 Indirizzo web del soggetto.
              p_note           IN     varchar2 Note del soggetto.
              p_modifica       IN     varchar2 Totale (T) o Parziale (P).
                                               In inserimento e sempre totale.
                                               In aggiornamento:
                                               Se totale tutti i valori passati
                                               vengono aggiornati (indipendentemente
                                               dal fatto che siano nulli o meno),
                                               altrimenti aggiorna i soli dati
                                               significativi (not null).
              p_trascodifica   IN     varchar2 'S' = Si, 'N' = No
 ECCEZIONI:  --
 ANNOTAZIONI: Richiamata da SOGGETTO.UPDATE_SOGGETTO.
              Salvata nella directory ins di AD4 con nome AS4_SOGG.CRP.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 18/12/2002 MM      Creazione.
    1 22/05/2003 MM      Sostituzione user con substr(user,1,8).
   2 15/06/2006 MM      Gestione campo COMPETENZA.
   3 01/04/2008 SN      Inserimento di un record se in fase di trascodifica.
   4 05/06/2018 SN      Con Anagrafica dalla 4.4 non usabile returning
   5 17/10/2018 SN      Corretto uso parametro sbagliato in inserimento soggetto
                        usare p_sogg
   6 27/06/2019 SN      UNIFICATA la procedure in modo che se presente AS4
                        usi la sequence altrimenti faccia NEXT_ID
   7 02/07/2019 SN      Se sono in registrazione ricerco fra i soggetti SOLO
                        x codice fiscale e che NON sia in struttura.
                        Se non esiste la struttura il controllo di presenza in
                        struttura restituisce sempre 0.
                        X consentire inserimento di un soggetto NUOVO con dati uguali
                        ad uno esistente se quello esistente non è legato ad
                        utenza inserita nel gruppo ric_abil.
                        Questo x poter avere una utenza interna e una esterna,
                        ad esempio per i dipendenti.
 8    26/09/2019 SNeg   Aggiunta gestione: competenza_esclusiva, utente_aggiornamento Bug #36471
******************************************************************************/
( p_soggetto IN OUT NUMBER
, p_nome IN VARCHAR2
, p_sesso IN VARCHAR2
, p_data_nascita IN VARCHAR2
, p_provincia_nas IN NUMBER
, p_comune_nas IN NUMBER
, p_codice_fiscale IN VARCHAR2
, p_indirizzo IN VARCHAR2
, p_cap IN VARCHAR2
, p_comune IN NUMBER
, p_provincia IN NUMBER
, p_telefono IN VARCHAR2
, p_fax IN VARCHAR2
, p_indirizzo_web IN VARCHAR2
, p_note IN VARCHAR2
, p_modifica IN VARCHAR2 DEFAULT 'T'
, p_trascodifica IN VARCHAR2 DEFAULT 'N'
, p_competenza in varchar2 default to_char(null)
, p_competenza_esclusiva in varchar2 default to_char(null)
, p_utente_agg in varchar2 default to_char(null))
IS
   d_competenza VARCHAR2(8) := p_competenza;
   v_num_as4_sogg_sq   NUMBER := 0;
   PROCEDURE INSERIMENTO
   (p_sogg in out number)
   IS
   BEGIN
      INSERT INTO SOGGETTI ( Soggetto, nome, sesso, data_nascita
                           , provincia_nas, comune_nas
                           , Codice_Fiscale, indirizzo, cap, Comune, Provincia
                           , telefono, fax, indirizzo_web, note
                           , utente_aggiornamento, data_aggiornamento, competenza, competenza_esclusiva)
                           -- rev.5 inizio
                    VALUES ( p_sogg, UPPER(p_nome), p_sesso, TO_DATE(p_data_nascita,'dd/mm/yyyy')
                           -- rev.5 fine
                           , p_provincia_nas, p_comune_nas
                           , p_codice_fiscale, p_indirizzo, p_cap
                           , p_comune, p_provincia
                           , p_telefono, p_fax, p_indirizzo_web, p_note
                           , nvl(p_utente_agg, Si4.Utente), SYSDATE, d_competenza, p_competenza_esclusiva)
                           -- Rev.4
                           --returning soggetto into p_sogg non piu utilizzabile
      ;
  EXCEPTION WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20999,'Fallito inserimento del soggetto '||p_nome||'.'||CHR(10)||REPLACE(SQLERRM,'ORA-',''));
  END;
BEGIN
   if d_competenza is null then
      BEGIN
         SELECT MIN(progetto)
           INTO d_competenza
           FROM ISTANZE
          WHERE UPPER(user_oracle) = UPPER(USER)
         ;
      EXCEPTION WHEN OTHERS THEN
         d_competenza := '';
      END;
   end if;
   IF p_soggetto IS NULL THEN
      -- verifica se esiste gia un soggetto con stesso nome, data_nascita, provincia_nas,
     -- comune_nas, codice_fiscale, indirizzo, comune e provincia
     BEGIN
     -- rev.7 inizio
      IF utente.s_registrazione_da_web_on = 0 THEN -- NON sono in registrazione
         SELECT MIN(Soggetto)
         INTO p_soggetto
         FROM soggetti
        WHERE nome                       = p_nome
          AND nvl(data_nascita, to_date('2222222','j'))
                                         = nvl(TO_DATE(p_data_nascita,'dd/mm/yyyy'), to_date('2222222','j'))
          AND nvl(provincia_nas,-1)      = nvl(p_provincia_nas,-1)
          AND nvl(comune_nas ,-1)        = nvl(p_comune_nas,-1)
          AND nvl(Codice_Fiscale ,' ')   = nvl(p_codice_fiscale,' ')
        ;
        ELSE -- in registrazione
        SELECT MIN(Soggetto)
         INTO p_soggetto
         FROM SOGGETTI s
        WHERE Codice_Fiscale = p_codice_fiscale
          -- rev. 4 inizio
          AND (SELECT ad4_soggetto.is_soggetto_componente(s.SOGGETTO) FROM dual) = 0
           -- sono in registrazione verifico se esiste un soggetto con i dati
           -- richiesti che non sia in struttura quindi adatto a registrazione
          -- rev. 4 fine
          ;
        END IF;
     -- rev.7 fine
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            p_soggetto := TO_NUMBER(NULL);
         WHEN OTHERS THEN
            p_soggetto := TO_NUMBER(NULL);
            RAISE_APPLICATION_ERROR(-20999,'Errore in selezione soggetto.'||CHR(10)||REPLACE(SQLERRM,'ORA-',''));
      END;
   END IF;
   IF p_soggetto IS NULL THEN
      -- Rev.6 inizio
      -- modifica x Anagrafica 4.4
      -- rev. 5 inizio
      BEGIN
      EXECUTE IMMEDIATE 'begin select count(*) into :v_num_as4 from all_objects where object_name = ''AS4_SOGG_SQ'';end;'
                USING OUT v_num_as4_sogg_sq;
      IF v_num_as4_sogg_sq > 0 then
      EXECUTE IMMEDIATE 'begin select as4_sogg_sq.nextval into :p_soggetto from dual;end;'
                USING OUT p_soggetto;-- questa procedure viene creata solo se esiste AS4
      ELSE -- non esiste la sequence di as4
        p_soggetto := Si4.NEXT_ID('SOGGETTI','SOGGETTO');
     END IF;
     EXCEPTION WHEN OTHERS THEN
             RAISE_APPLICATION_ERROR(SQLCODE,'Impossibile associare il numero individuale al nuovo soggetto ('||p_nome||').'||CHR(10)||SQLERRM);
     END;
     -- rev. 6 fine
      -- Rev. 4 fine
      inserimento(p_soggetto);
   ELSE
      IF p_soggetto IS NOT NULL THEN
         BEGIN
            UPDATE SOGGETTI
               SET nome                 = DECODE(p_modifica, 'T', p_nome, DECODE(RTRIM(LTRIM(p_nome)), TO_CHAR(NULL), nome, UPPER(p_nome)))
                 , sesso                = DECODE(p_modifica, 'T', p_sesso, DECODE(p_sesso, TO_CHAR(NULL), sesso, p_sesso))
                 , data_nascita         = DECODE(p_modifica, 'T', TO_DATE(p_data_nascita,'dd/mm/yyyy'), DECODE(p_data_nascita, TO_CHAR(NULL), data_nascita, TO_DATE(p_data_nascita,'dd/mm/yyyy')))
                 , provincia_nas        = DECODE(p_modifica, 'T', p_provincia_nas, DECODE(p_provincia_nas, TO_NUMBER(NULL), provincia_nas, p_provincia_nas))
                 , comune_nas           = DECODE(p_modifica, 'T', p_comune_nas, DECODE(p_comune_nas, TO_NUMBER(NULL), comune_nas, p_comune_nas))
                 , Codice_Fiscale       = DECODE(p_modifica, 'T', p_codice_fiscale, DECODE(p_codice_fiscale, TO_CHAR(NULL), Codice_Fiscale, p_codice_fiscale))
                 , indirizzo            = DECODE(p_modifica, 'T', p_indirizzo, DECODE(p_indirizzo, TO_CHAR(NULL), indirizzo, p_indirizzo))
                 , cap                  = DECODE(p_modifica, 'T', p_cap, DECODE(p_cap, TO_CHAR(NULL), cap, p_cap))
                 , Comune               = DECODE(p_modifica, 'T', p_comune, DECODE(p_comune, TO_NUMBER(NULL), Comune, p_comune))
                 , Provincia            = DECODE(p_modifica, 'T', p_provincia, DECODE(p_provincia, TO_NUMBER(NULL), Provincia, p_provincia))
                 , telefono             = DECODE(p_modifica, 'T', p_telefono, DECODE(p_telefono, TO_CHAR(NULL), telefono, p_telefono))
                 , fax                  = DECODE(p_modifica, 'T', p_fax, DECODE(p_fax, TO_CHAR(NULL), fax, p_fax))
                 , indirizzo_web        = DECODE(p_modifica, 'T', p_indirizzo_web, DECODE(p_indirizzo_web, TO_CHAR(NULL), indirizzo_web, p_indirizzo_web))
                 , note                 = DECODE(p_modifica, 'T', p_note, DECODE(p_note, TO_CHAR(NULL), note, p_note))
                 , utente_aggiornamento = nvl(p_utente_agg, Si4.Utente)
                 , data_aggiornamento   = SYSDATE
                 , competenza           = d_competenza
                 , competenza_esclusiva = p_competenza_esclusiva
             WHERE Soggetto = p_soggetto
            ;
            if sql%ROWCOUNT = 0 and p_trascodifica = 'S' then
            -- non era presente il record e stiamo trascodificando
               inserimento(p_soggetto);
            end if;
         EXCEPTION WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20999,'Fallito aggiornamento del soggetto '||p_nome||' ('||p_soggetto||').'||CHR(10)||REPLACE(SQLERRM,'ORA-',''));
         END;
      END IF;
   END IF;
END;
/
