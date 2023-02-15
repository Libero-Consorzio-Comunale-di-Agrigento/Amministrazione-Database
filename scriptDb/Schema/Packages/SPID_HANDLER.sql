CREATE OR REPLACE package spid_handler is
    /******************************************************************************
     NOME:          spid_handler
     DESCRIZIONE:   Package che contiene i metodi per la gestione dell'identità SPID
     ARGOMENTI:
     RITORNA:
     ECCEZIONI:
     ANNOTAZIONI:
     REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ --------------------------------------------------------
     1    24/11/2017 AD     Prima Emissione
     2    08/02/2018 AD     Modificata conferma_dati_soggetto per gestire il parametro
                            p_profilo e lanciare il set del profilo anche in aggiornamento
     3    28/02/2018 AD     Aggiunto parametro telefono residenza in conferma dei dati anagrafici
                            del soggetto
    ******************************************************************************/
    s_revisione AFC.t_revision := 'V1.03';
    -- Version and revision
    function versione return AFC.t_revision;
    function spid2ad4(p_codice_fiscale in varchar2     -- codice fiscale del soggetto
    ,p_cognome in varchar2            -- cognome del soggetto
    ,p_nome in varchar2               -- nome del soggetto
    ,p_indirizzo_mail in varchar2     -- indirizzo mail del soggetto
    ,p_spid_id in varchar2            -- spid_id valorizzato solo se accesso mediante spid
    ,p_profilo in varchar2            -- utente di gruppo per attribuzione profilo
    ,p_new_utente out number          -- parametro numerico per identificare se è stato creato un nuobo utente (0/1)
    ) return number;                  -- ni del soggetto anagrafico
    procedure conferma_dati_soggetto( p_ni in number
    ,p_cognome in varchar2             -- cognome del soggetto
    ,p_nome in varchar2                -- nome del soggetto
    ,p_indirizzo_mail in varchar2      -- indirizzo mail del soggetto
    ,p_indirizzo_res in varchar2       -- indirizzo di residenza
    ,p_cap_res in varchar2             -- cap di residenza
    ,p_tel_res in varchar2             -- telefono di residenza
    ,p_denominazione_comune_res in varchar2      -- denominazione comune residenza
    ,p_denominazione_provincia_res in varchar2    --denominazione provincia residenza
    ,p_profilo in varchar2 default null -- ripasso il profilo per gestire uno lancio della procedure
    );
end;
/

