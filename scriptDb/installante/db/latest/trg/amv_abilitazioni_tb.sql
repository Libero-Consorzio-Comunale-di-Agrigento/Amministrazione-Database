--liquibase formatted sql

--changeset mturra:201901301231_355 runOnChange:true stripComments:false


create or replace trigger AMV_ABILITAZIONI_TB
   before INSERT or UPDATE or DELETE on AMV_ABILITAZIONI
/******************************************************************************
 NOME:        AMV_ABILITAZIONI_TC
 DESCRIZIONE: Trigger for Custom Functional Check
                       at INSERT or UPDATE or DELETE on Table AMV_ABILITAZIONI
 ANNOTAZIONI: Esegue inizializzazione tabella di POST Event.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    __/__/____ __     Prima emissione.
******************************************************************************/
BEGIN
   /* RESET PostEvent for Custom Functional Check */
   IF IntegrityPackage.GetNestLevel = 0 THEN
      IntegrityPackage.InitNestLevel;
   END IF;
END;

/

