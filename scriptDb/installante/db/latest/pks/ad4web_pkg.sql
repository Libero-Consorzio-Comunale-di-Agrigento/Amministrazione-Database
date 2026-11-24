--liquibase formatted sql

--changeset mturra:201901301231_200 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE Ad4web_pkg IS
/******************************************************************************
 NOME:        AD4WEB_PKG.
 DESCRIZIONE: .
 ANNOTAZIONI: --
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    20/08/2007 MM     Creazione.
******************************************************************************/
      FUNCTION VERSIONE              RETURN VARCHAR2;
END Ad4web_pkg;
/

