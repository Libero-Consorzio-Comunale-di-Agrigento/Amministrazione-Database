--liquibase formatted sql

--changeset mturra:201901301231_276 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE SI4_USER_SOURCE_PKG AS
/******************************************************************************
 NOME:        SI4_USER_SOURCE_PKG
 DESCRIZIONE: Package di gestione SI4_USER_SOURCE.
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 00   23/08/2007 MM     Creazione.
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione.
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
******************************************************************************/
   FUNCTION VERSIONE
      RETURN VARCHAR2;
/******************************************************************************
 NOME:        addParm
 DESCRIZIONE: Aggiunge il parametro ed il relativo valore alla tabella dei
              parametri.
              Verranno utilizzati dalla procedure EXECUTE per l'esecuzione del
              sorgente PL/Sql memorizzato in SI4_USER_SOURCE.
              La tabella dei parametri viene svuotata nella procedure EXECUTE.
 ARGOMENTI:   p_name          IN varchar2 nome del parametro.
              p_value         IN varchar2 valore del parametro.
******************************************************************************/
   procedure addParm
   ( p_name varchar2
   , p_value varchar2);
/******************************************************************************
 NOME:        execute
 DESCRIZIONE: Esegue il sorgente PL/Sql memorizzato in SI4_USER_SOURCE
              sostituendo eventuali parametri presenti nella tabella dei
              parametri.
 ARGOMENTI:   p_name          IN varchar2 nome del sorgente da eseguire.
              p_type          IN varchar2 tipo del sorgente da eseguire.
              p_ignore_exists IN number   indica se ignorare l'errore indicante
                                          l'esistenza dell'oggetto.
              p_prefix        IN varchar2 eventuale prefisso da anteporre alla
                                          tabella SI4_USER_SOURCE (serve se la
                                          non è proprietaria ma di un altro user
                                          quindi si accede via sinonimo).
******************************************************************************/
   PROCEDURE execute
   ( p_name in SI4_USER_SOURCE.NAME%TYPE
   , p_type in SI4_USER_SOURCE.TYPE%TYPE DEFAULT NULL
   , p_ignore_exists in NUMBER DEFAULT 0
   , p_prefix in varchar2 default null
   );
END SI4_USER_SOURCE_PKG;
/

