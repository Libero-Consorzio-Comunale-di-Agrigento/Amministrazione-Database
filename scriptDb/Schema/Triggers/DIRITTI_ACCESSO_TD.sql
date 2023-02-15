CREATE OR REPLACE TRIGGER DIRITTI_ACCESSO_TD
/******************************************************************************
 NOME:        DIRITTI_ACCESSO_TD
 DESCRIZIONE: Trigger for Set FUNCTIONAL Integrity
                        Check REFERENTIAL Integrity
                       at DELETE on Table DIRITTI_ACCESSO
 ANNOTAZIONI: Richiama Procedure DIRITTI_ACCESSO_TD
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
 1    03/01/2003 MM     Gestione diritti di gruppo.
 2    03/11/2004 VA     Gestione tipo_utente=O (Organizzazione).
 3    19/01/2012 SNeg user_oracle dimesionato in base alla tabella
******************************************************************************/
   BEFORE DELETE ON DIRITTI_ACCESSO
FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
   dTipoUtente      utenti.tipo_utente%TYPE;
   dProgetto       istanze.progetto%TYPE;
   dUserOracle      istanze.user_oracle%TYPE;
   a_esiste         NUMBER(1);
BEGIN
   BEGIN -- Set FUNCTIONAL Integrity on DELETE
      IF Integritypackage.GetNestLevel = 0 THEN
         Integritypackage.NextNestLevel;
         BEGIN  -- Global FUNCTIONAL Integrity at Level 0
            /* NONE */ NULL;
         END;
         Integritypackage.PreviousNestLevel;
      END IF;
   END;
   BEGIN  -- Check REFERENTIAL Integrity on DELETE
      NULL;
   END;
   DECLARE a_istruzione  VARCHAR2(2000);
           a_messaggio   VARCHAR2(2000);
   BEGIN  -- Set PostEvent Check REFERENTIAL Integrity
      -- se si sta eliminando un diritto di accesso di un gruppo,
      -- elimina lo stesso anche da tutti gli utenti del gruppo per cui il
      -- diritto di accesso non sia stato personalizzato.
      SELECT tipo_utente
        INTO dTipoUtente
        FROM UTENTI
       WHERE Utente = :OLD.Utente
      ;
      BEGIN
         a_messaggio := '';
   -- 03/11/2004
   --    if nvl(dTipoUtente, 'U') = 'G' then
         IF NVL(dTipoUtente, 'U') != 'U' THEN
            a_istruzione := 'begin GRUPPO.DIAC_GRUPPO_DELETE('''||
                            replace(:OLD.Utente,'''','''''')||''', ''%'', '''||
                            :OLD.MODULO||''', '''||:OLD.Istanza||''', ''E''); end;';
         ELSE
            a_istruzione := 'begin UTENTE.AGGIORNA_PWD_DA_MODIFICARE('''||
                            replace(:OLD.Utente,'''','''''')||'''); end;';
         END IF;
         Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
      EXCEPTION
         WHEN OTHERS THEN
            Integritypackage.InitNestLevel;
            RAISE;
      END;
     SELECT progetto, UPPER(user_oracle)
       INTO dProgetto, dUserOracle
      FROM ISTANZE
      WHERE Istanza = :OLD.Istanza
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
                         :NEW.RUOLO||''', '||NVL(TO_CHAR(:NEW.SEQUENZA), '''''')||
                         ', '''||TO_CHAR(:NEW.ULTIMO_ACCESSO,'dd/mm/yyyy hh24:mi:ss')||
                         ''','||NVL(TO_CHAR(:NEW.NUMERO_ACCESSI), '''''')||','''||
                         replace(:NEW.Gruppo,'''','''''')||''','''||
                         replace(:NEW.NOTE,'''','''''')||''','''||
                         replace(:OLD.Utente,'''','''''')||''', '''||:OLD.MODULO||
                         ''', '''||:OLD.Istanza||''', '''||:OLD.RUOLO||''', '||
                         NVL(TO_CHAR(:OLD.SEQUENZA), '''''')||', '''||
                         TO_CHAR(:OLD.ULTIMO_ACCESSO,'dd/mm/yyyy hh24:mi:ss')||''','||
                         NVL(TO_CHAR(:OLD.NUMERO_ACCESSI), '''''')||','''||
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


