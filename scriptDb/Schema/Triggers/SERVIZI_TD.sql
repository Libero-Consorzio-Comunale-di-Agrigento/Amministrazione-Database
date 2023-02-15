CREATE OR REPLACE TRIGGER SERVIZI_TD
/******************************************************************************
 NOME:        SERVIZI_TD
 DESCRIZIONE: Trigger for Set FUNCTIONAL Integrity
                        Check REFERENTIAL Integrity
                       at DELETE on Table SERVIZI
 ANNOTAZIONI: Richiama Procedure RUOLI_TD
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
1   19/01/2012 SNeg modificata variabile user_oracle
******************************************************************************/
   BEFORE DELETE ON SERVIZI
FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   dummy            INTEGER;
   FOUND            BOOLEAN;
BEGIN
   BEGIN -- Set FUNCTIONAL Integrity on DELETE
      IF Integritypackage.GetNestLevel = 0 THEN
         Integritypackage.NextNestLevel;
         BEGIN  -- Global FUNCTIONAL Integrity at Level 0
            DECLARE
               d_user istanze.user_oracle%type;
            BEGIN
               SELECT user_oracle
                 INTO d_user
                 FROM ISTANZE
                WHERE Istanza = :OLD.Istanza
               ;
               Registro_Pac.SET_AD4_STRING('RICHIESTA_ABILITAZIONE/MAIL/CIM', 'Tag', '', :OLD.MODULO, d_user);
            EXCEPTION
               WHEN NO_DATA_FOUND THEN
                  NULL;
               WHEN OTHERS THEN
                  RAISE;
            END;
         END;
         Integritypackage.PreviousNestLevel;
      END IF;
   END;
   BEGIN  -- Check REFERENTIAL Integrity on DELETE
      /* NONE */ NULL;
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


