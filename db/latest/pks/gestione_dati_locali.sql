--liquibase formatted sql

--changeset snegroni:20191104 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE GESTIONE_DATI_LOCALI
IS
    /******************************************************************************
     NOME:        GESTIONE_DATI_LOCALI.
     DESCRIZIONE: Funzioni di amministrazione.
     ANNOTAZIONI:
     REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     00   04/11/2019 SN     Pulizia accessi oltre i giorni indicati per la conservazione
                            fra 180 (costante) , quanto indicato nel registro (LogAccessRetention)
                            e le caratteristiche di accesso coinvolte
    ******************************************************************************/
    -- Revisione del Package
    s_revisione   CONSTANT AFC.t_revision := 'V1.00';

    PROCEDURE ELIMINA_ACCESSI_MAX_RETENTION;

    PROCEDURE ELIMINA_ACCESSI_PRIMA_GIORNI (p_giorni NUMBER);
END;
/
