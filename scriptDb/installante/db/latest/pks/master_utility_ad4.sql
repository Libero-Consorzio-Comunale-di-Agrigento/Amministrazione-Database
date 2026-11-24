--liquibase formatted sql

--changeset mturra:201901301231_254 runOnChange:true stripComments:false endDelimiter:/


create or replace package MASTER_UTILITY_AD4 is /* MASTER_LINK */
/******************************************************************************
 NOME:        MAsTER_UTILITY.
 DESCRIZIONE: .
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    29/11/2006 MM     Creazione.
 1    11/01/2010 MM     Gestione master link e slave copy.
******************************************************************************/
FUNCTION VERSIONE
RETURN VARCHAR2;
PROCEDURE REFRESH_SLAVE /* SLAVE_COPY */
(p_db_link varchar2);
PROCEDURE REFRESH_SLAVES;
PROCEDURE REFRESH_SLAVES
( p_refresh_group in varchar2);
PROCEDURE CHECK_SLAVES;
PROCEDURE REFRESH_ALL_SLAVES;
end MASTER_UTILITY_AD4;
/

