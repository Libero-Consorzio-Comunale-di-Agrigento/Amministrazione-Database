CREATE OR REPLACE TRIGGER EVENTI_TIU
/******************************************************************************
 NOME:        EVENTI_TIU
 DESCRIZIONE: Trigger for Set DATA Integrity
                          Set FUNCTIONAL Integrity
                       on Table EVENTI
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 04/11/2003 MM     Creazione
    1 06/06/2007 MM     Gestione della sequence EVEN_SQ
    2 26/01/2021 SN     Valorizzare il campo session_id con la sessione Bug #47610
******************************************************************************/
   BEFORE INSERT OR UPDATE ON EVENTI
FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   FOUND            BOOLEAN;
BEGIN
   BEGIN  -- Set DATA Integrity
      /* NONE */ NULL;
      -- Rev. 1 06/06/2007 MM     Gestione della sequence EVEN_SQ
      if :NEW.ID_EVENTO IS NULL and not DELETING then
         select EVEN_SQ.NEXTVAL
           into :new.ID_EVENTO
           from dual;
      end if;
      -- Rev. 1 06/06/2007 MM fine mod.
   END;
   :new.session_id := TO_NUMBER (AFC.GET_STRINGPARM (:new.TESTO, 'session_id')); --rev. 2
  BEGIN  -- Set FUNCTIONAL Integrity
      IF Integritypackage.GetNestLevel = 0 THEN
         Integritypackage.NextNestLevel;
         BEGIN  -- Global FUNCTIONAL Integrity at Level 0
         -- Rev. 1 06/06/2007 MM     Gestione della sequence EVEN_SQ
--            --  Column "ACCE_ID" attribuisce MAX+1
--            IF :NEW.ID_EVENTO IS NULL THEN
--               :NEW.ID_EVENTO := Si4.NEXT_ID('EVENTI','ID_EVENTO');
--            END IF;
            null;
            -- Rev. 1 06/06/2007 MM fine mod.
         END;
         Integritypackage.PreviousNestLevel;
      END IF;
      Integritypackage.NextNestLevel;
      BEGIN  -- Full FUNCTIONAL Integrity at Any Level
         /* NONE */ NULL;
      END;
      Integritypackage.PreviousNestLevel;
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


