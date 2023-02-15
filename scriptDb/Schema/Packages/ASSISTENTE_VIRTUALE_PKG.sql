CREATE OR REPLACE package assistente_virtuale_pkg is
/******************************************************************************
 NOME: ASSISTENTE_VIRTUALE_PKG

 DESCRIZIONE: Funzione per la determinazione del url di accesso alla documentazione condivisa
 ANNOTAZIONI:
 REVISIONI:

 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 00   25/02/2020 AD     Creazione.

 *****************************************************************************/
    function get_link_av (p_area_applicativa in varchar2, p_modulo_software in varchar2, p_pagina_applicativa in varchar2, p_lingua in varchar2 default null)
    return varchar2;
end;
/

