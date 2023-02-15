CREATE OR REPLACE TRIGGER OMONIMIE_GESTITE_TIU
/******************************************************************************
 NOME:        OMONIMIE_GESTITE_TIU
 DESCRIZIONE: Trigger for Set DATA Integrity
                          Set FUNCTIONAL Integrity
                       on Table OMONIMIE_GESTITE
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 19/05/2011 SNeg    Creazione
******************************************************************************/
   BEFORE INSERT OR UPDATE ON OMONIMIE_GESTITE FOR EACH ROW
DECLARE
   integrity_error  EXCEPTION;
   errno            INTEGER;
   errmsg           CHAR(200);
   FOUND            BOOLEAN;
BEGIN
   BEGIN  -- Set DATA Integrity
      /* NONE */ NULL;
      -- Rev. 1 06/06/2007 MM     Gestione della sequence EVEN_SQ
      if :NEW.ID_OMONIMIA IS NULL and not DELETING then
         :NEW.ID_OMONIMIA :=  Si4.NEXT_ID('OMONIMIE_GESTITE','ID_OMONIMIA');
      end if;
      if :NEW.nominativo_primario is null then
        :NEW.nominativo_primario:=  utente.get_nominativo(:new.primario);
      end if;
      if :NEW.nominativo_secondario is null then
        :NEW.nominativo_secondario:=  utente.get_nominativo(:new.secondario);
      end if;
      -- Rev. 1 06/06/2007 MM fine mod.
   END;
  BEGIN  -- Set FUNCTIONAL Integrity
      IF Integritypackage.GetNestLevel = 0 THEN
         Integritypackage.NextNestLevel;
         BEGIN  -- Global FUNCTIONAL Integrity at Level 0
            null;
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


