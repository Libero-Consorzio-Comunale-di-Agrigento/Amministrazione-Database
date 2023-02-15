CREATE OR REPLACE TRIGGER UTENTI_GRUPPO_TD
/******************************************************************************
 NOME:        UTENTI_GRUPPO_TD
 DESCRIZIONE: Trigger for Set FUNCTIONAL Integrity
                        Check REFERENTIAL Integrity
                       at DELETE on Table UTENTI_GRUPPO
 ANNOTAZIONI: Richiama Procedure UTENTI_GRUPPO_TD
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
    1 03/01/2002 MM     Eliminazione dei diritti e delle caratteristiche di
                        accesso ereditati dal gruppo.
    2 24/04/2007 MM     A20741.0.0 Sostituite le chiamate a DIAC_GRUPPO_DELETE
                        e CAAC_GRUPPO_DELETE con DIAC_CAAC_GRUPPO_DELETE.
    3 18/04/2013 SNeg   Tolte upper nelle verifiche alla user_tab_privs
    4 27/05/2020 SNeg   controlli sui gruppi _GR_ non modificabili se non da procedura apposita
******************************************************************************/
   BEFORE DELETE ON UTENTI_GRUPPO
FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
   a_istruzione     VARCHAR2(2000);
   a_messaggio      VARCHAR2(2000);
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
   -- 27/05/2020 controlli sui gruppi _GR_
      if  :old.gruppo like '\_GR\_%\_' escape '\' and not utente.get_is_sistemazione_gruppi
      then
         RAISE_APPLICATION_ERROR(-20999, 'Appartenenza ai gruppi standard non modificabile');
      end if;
   BEGIN  -- Check REFERENTIAL Integrity on DELETE
      Utenti_Gruppo_Pd(:OLD.Utente,
                    :OLD.Gruppo);
   END;
   -- eliminazione dei diritti e delle caratteristiche di accesso ereditati dal gruppo.
   BEGIN
      a_messaggio := '';
      a_istruzione := 'begin '||
                         'GRUPPO.DIAC_CAAC_GRUPPO_DELETE('''||
                                 replace(:OLD.Gruppo,'''','''''')||''', '''||
                                 replace(:OLD.Utente,'''','''''')||
                                 ''', ''%'', ''%'', ''E'',''E''); '||
                      'end;';
      Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
   EXCEPTION
      WHEN OTHERS THEN
         Integritypackage.InitNestLevel;
         RAISE;
   END;
   FOR c_diac IN ( SELECT DISTINCT ista.progetto, UPPER(ista.user_oracle) user_oracle
                     FROM DIRITTI_ACCESSO diac, ISTANZE ista
                    WHERE ista.Istanza = diac.Istanza
                      AND diac.Utente = :OLD.Gruppo)
   LOOP
      SELECT COUNT(1)
        INTO a_esiste
        FROM USER_TAB_PRIVS
       WHERE table_name = c_diac.progetto||'_AD4'
         AND grantee IN (user,'PUBLIC')
         AND owner = c_diac.user_oracle
      ;
      IF a_esiste > 0 THEN
         a_messaggio := '';
            a_istruzione := 'begin '||c_diac.user_oracle||'.'||c_diac.progetto||
                            '_AD4.UTENTE_GRUPPO('''||replace(:NEW.Gruppo,'''','''''')||
                            ''', '''||replace(:NEW.Utente,'''','''''')||''','''||
                            replace(:OLD.Gruppo,'''','''''')||''', '''||
                            replace(:OLD.Utente,'''','''''')||'''); end;';
         BEGIN
            Integritypackage.Set_PostEvent(a_istruzione, a_messaggio);
         EXCEPTION
            WHEN OTHERS THEN
               Integritypackage.InitNestLevel;
               RAISE;
         END;
      END IF;
   END LOOP;
EXCEPTION
   WHEN integrity_error THEN
        Integritypackage.InitNestLevel;
        RAISE_APPLICATION_ERROR(errno, errmsg);
   WHEN OTHERS THEN
        Integritypackage.InitNestLevel;
        RAISE;
END;
/


