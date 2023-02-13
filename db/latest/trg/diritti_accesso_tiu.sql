--liquibase formatted sql

--changeset mturra:201901301231_386 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER DIRITTI_ACCESSO_TIU
/******************************************************************************
 NOME:        DIRITTI_ACCESSO_TIU
 DESCRIZIONE: Trigger for Check FUNCTIONAL Integrity
                            Set FUNCTIONAL Integrity
                       at INSERT or UPDATE or DELETE on Table DIRITTI_ACCESSO
 ECCEZIONI:
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    1 04/02/2013 SNeg   tolto controllo su diritti_accesso_pi solo se a livello = 0
    2 18/04/2013 SNeg   Tolte upper nelle verifiche alla user_tab_privs
******************************************************************************/
BEFORE INSERT OR UPDATE
ON DIRITTI_ACCESSO
FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
BEGIN
   BEGIN  -- Check DATA Integrity on INSERT or UPDATE
      IF UPDATING THEN
         IF :OLD.Gruppo IS NOT NULL AND (:NEW.modulo <> :OLD.modulo OR :NEW.Istanza <> :OLD.Istanza OR :OLD.ruolo <> :NEW.ruolo) THEN
            :NEW.Gruppo := TO_CHAR(NULL);
         END IF;
      END IF;
   END;
   BEGIN  -- Check REFERENTIAL Integrity on INSERT or UPDATE
      IF UPDATING THEN
         Diritti_Accesso_Pu(:OLD.Utente
                       , :OLD.MODULO
                       , :OLD.Istanza
                       , :OLD.RUOLO
                         , :NEW.Utente
                         , :NEW.MODULO
                         , :NEW.Istanza
                         , :NEW.RUOLO
                         );
         NULL;
      END IF;
      IF INSERTING THEN
        -- IF Integritypackage.GetNestLevel = 0 THEN --commentato per rev.1
        -- quando lanciata in post-event non controlla se il diritto
        -- ha tutto in regola (es: inserimento in un gruppo da altro prodotto)
            Diritti_Accesso_Pi(:NEW.Utente,
                               :NEW.MODULO,
                               :NEW.Istanza,
                               :NEW.RUOLO);
            DECLARE  --  Check UNIQUE PK Integrity per la tabella "DIRITTI_ACCESSO"
            CURSOR cpk_diritti_accesso(var_UTENTE VARCHAR,
                            var_MODULO VARCHAR,
                            var_ISTANZA VARCHAR) IS
               SELECT 1
                 FROM   DIRITTI_ACCESSO
                WHERE  Utente = var_UTENTE AND
                       MODULO = var_MODULO AND
                       Istanza = var_ISTANZA;
            mutating         EXCEPTION;
            PRAGMA EXCEPTION_INIT(mutating, -4091);
            BEGIN  -- Check UNIQUE Integrity on PK of "DIRITTI_ACCESSO"
               IF :NEW.Utente IS NOT NULL AND
                  :NEW.MODULO IS NOT NULL AND
                  :NEW.Istanza IS NOT NULL THEN
                  OPEN  cpk_diritti_accesso(:NEW.Utente,
                                 :NEW.MODULO,
                                 :NEW.Istanza);
                  FETCH cpk_diritti_accesso INTO dummy;
                  FOUND := cpk_diritti_accesso%FOUND;
                  CLOSE cpk_diritti_accesso;
                  IF FOUND THEN
                     errno  := -20007;
                     errmsg := 'Identificazione "'||
                               :NEW.Utente||' '||
                               :NEW.MODULO||' '||
                               :NEW.Istanza||
                               '" gia'' presente in Diritti Accesso. La registrazione  non puo'' essere inserita.';
                     RAISE integrity_error;
                  END IF;
               END IF;
            EXCEPTION
               WHEN MUTATING THEN NULL;  -- Ignora Check su UNIQUE PK Integrity
            END;
         --END IF;
      END IF;
   END;
   DECLARE
           dTipoUtente   utenti.tipo_utente%TYPE;
           dProgetto     istanze.progetto%TYPE;
           dUserOracle   istanze.user_oracle%TYPE;
           a_istruzione  VARCHAR2(32000);
           a_messaggio   VARCHAR2(2000);
           a_esiste      NUMBER(2) := 0;
   BEGIN  -- Set PostEvent Check REFERENTIAL Integrity
      -- se si sta inserendo o aggiornando un diritto di accesso di un gruppo,
      -- nserisce o aggiorna lo stesso anche da tutti gli utenti del gruppo per cui il
      -- diritto di accesso non sia stato personalizzato.
      SELECT tipo_utente
        INTO dTipoUtente
        FROM UTENTI
       WHERE Utente = :NEW.Utente
      ;
      -- Set PostEvent Check REFERENTIAL Integrity
      IF UPDATING THEN
         BEGIN
             a_istruzione := 'begin DIRITTO_ACCESSO.AGGIORNA_GRUPPO('''||
                             replace(:NEW.Utente,'''','''''')||''', '''||:NEW.MODULO||
                             ''', '''||:NEW.Istanza||'''); end;';
             a_messaggio := '';
             Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
          EXCEPTION
             WHEN OTHERS THEN
                Integritypackage.InitNestLevel;
                RAISE;
         END;
      END IF;
      a_messaggio := '';
-- 03/11/2004
--    if nvl(dTipoUtente, 'U') = 'G' then
      IF NVL(dTipoUtente, 'U') != 'U' THEN
         IF UPDATING THEN
            a_istruzione := 'begin GRUPPO.DIAC_GRUPPO_UPDATE('''||
                            replace(:NEW.Utente,'''','''''')||''', '''||:NEW.MODULO||
                            ''', '''||:NEW.Istanza||''', '''||:NEW.RUOLO||''', '''||
                            REPLACE(:NEW.NOTE,'''','''''')||''', ''E''); end;';
         END IF;
         IF INSERTING THEN
            a_istruzione := 'begin GRUPPO.DIAC_GRUPPO_INSERT('''||
                            replace(:NEW.Utente,'''','''''')||''', ''%'', '''||
                            :NEW.MODULO||''', '''||:NEW.Istanza||''', ''E''); end;';
         END IF;
      ELSE
         IF INSERTING OR INSTR(:NEW.ISTANZA, :OLD.ISTANZA) = 0 OR INSTR(:NEW.MODULO, :OLD.MODULO) = 0 THEN
            a_istruzione := 'begin UTENTE.AGGIORNA_PWD_DA_MODIFICARE('''||
                            replace(:NEW.Utente,'''','''''')||'''); end;';
         END IF;
      END IF;
      BEGIN
         Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
      EXCEPTION
         WHEN OTHERS THEN
            Integritypackage.InitNestLevel;
            RAISE;
      END;
      IF INSERTING
      OR NVL(:NEW.Utente, ' ')  <> NVL(:OLD.Utente, ' ')
      OR NVL(:NEW.MODULO, ' ')  <> NVL(:OLD.MODULO, ' ')
      OR NVL(:NEW.Istanza, ' ') <> NVL(:OLD.Istanza, ' ')
      OR NVL(:NEW.RUOLO, ' ')   <> NVL(:OLD.RUOLO, ' ')
      OR NVL(:NEW.Gruppo, ' ')  <> NVL(:OLD.Gruppo, ' ') THEN
         SELECT progetto, UPPER(user_oracle)
           INTO dProgetto, dUserOracle
           FROM ISTANZE
          WHERE Istanza = :NEW.Istanza
         ;
         SELECT COUNT(1)
           INTO a_esiste
           FROM USER_TAB_PRIVS
          WHERE table_name = dProgetto||'_AD4'
            AND grantee IN (user,'PUBLIC')
            AND owner = upper(dUserOracle)
         ;
         IF a_esiste > 0 THEN
            a_messaggio := '';
            a_istruzione := 'begin '||dUserOracle||'.'||dProgetto||
                            '_AD4.DIRITTO_ACCESSO('''||replace(:NEW.Utente,'''','''''')||
                            ''', '''||:NEW.MODULO||''', '''||:NEW.Istanza||''', '''||
                            :NEW.RUOLO||''', '||NVL(TO_CHAR(:NEW.SEQUENZA),'''''')||
                            ', '''||TO_CHAR(:NEW.ULTIMO_ACCESSO,'dd/mm/yyyy hh24:mi:ss')||
                            ''','||NVL(TO_CHAR(:NEW.NUMERO_ACCESSI),'''''')||','''||
                            replace(:NEW.Gruppo,'''','''''')||''','''||
                            replace(:NEW.NOTE,'''','''''')||''','''||
                            replace(:OLD.Utente,'''','''''')||''', '''||:OLD.MODULO||
                            ''', '''||:OLD.Istanza||''', '''||:OLD.RUOLO||''', '||
                            NVL(TO_CHAR(:OLD.SEQUENZA), '''''')||', '''||
                            TO_CHAR(:OLD.ULTIMO_ACCESSO,'dd/mm/yyyy hh24:mi:ss')||''','||
                            NVL(TO_CHAR(:OLD.NUMERO_ACCESSI),'''''')||','''||
                            replace(:OLD.Gruppo,'''','''''')||''','''||
                            replace(:OLD.NOTE,'''','''''')||'''); end;';
            BEGIN
               Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
            EXCEPTION
               WHEN OTHERS THEN
                  Integritypackage.InitNestLevel;
                  RAISE;
            END;
         END IF;
      END IF;
   EXCEPTION WHEN NO_DATA_FOUND THEN
      NULL;
   END;
EXCEPTION
   WHEN integrity_error THEN
        Integritypackage.InitNestLevel;
        RAISE_APPLICATION_ERROR(errno, errmsg);
   WHEN OTHERS THEN
        Integritypackage.InitNestLevel;
        RAISE;
END;

/

