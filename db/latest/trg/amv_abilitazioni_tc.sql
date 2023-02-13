--liquibase formatted sql

--changeset mturra:201901301231_356 runOnChange:true stripComments:false


create or replace trigger AMV_ABILITAZIONI_TC
   after INSERT or UPDATE or DELETE on AMV_ABILITAZIONI
/******************************************************************************
 NOME:        AMV_ABILITAZIONI_TC
 DESCRIZIONE: Trigger for Custom Functional Check
                    after INSERT or UPDATE or DELETE on Table AMV_ABILITAZIONI
 ANNOTAZIONI: Esegue operazioni di POST Event prenotate.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    __/__/____ __     Prima emissione.
******************************************************************************/
BEGIN
   /* EXEC PostEvent for Custom Functional Check */
   IntegrityPackage.Exec_PostEvent;
END;

/

