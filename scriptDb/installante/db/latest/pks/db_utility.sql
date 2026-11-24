--liquibase formatted sql

--changeset snegroni:201905021526 runOnChange:true stripComments:false endDelimiter:/

CREATE OR REPLACE package DB_UTILITY
is
 -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
--------------------------------------------------------------------------------
/******************************************************************************
 NOME:        TRASCO_PKG
 DESCRIZIONE: Gestisce la trasco dei dati per migrazione alla nuova struttura.
 ARGOMENTI:
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    15/04/2019 SNeg   Prima emissione
 1    07/05/2019 SNeg   Introdotta ins_db
******************************************************************************/
-- Versione e revisione
   function versione
   return varchar2;
PROCEDURE CREATE_TABELLA (p_nome_oggetto varchar2
                         ,p_statement varchar2);

PROCEDURE CREATE_SEQUENZA (p_nome_oggetto varchar2
                          ,p_statement varchar2);

 PROCEDURE CREATE_INDICE (p_nome_oggetto varchar2
                          ,p_statement varchar2);

PROCEDURE CREATE_CONSTRAINT (p_nome_oggetto varchar2
                            ,p_statement varchar2);

PROCEDURE ADD_COLUMN (p_nome_oggetto varchar2
                     ,p_nome_colonna varchar2
                     ,p_statement varchar2);

PROCEDURE INS_DB     (p_statement_controllare varchar2
                     ,p_statement varchar2);

END;
/


