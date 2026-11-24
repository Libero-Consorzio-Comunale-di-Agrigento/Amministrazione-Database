--liquibase formatted sql

--changeset snegroni:202003051311 runOnChange:true stripComments:false endDelimiter:/

CREATE OR REPLACE package UTENTI_SALT_PKG is
/******************************************************************************
 NOME: UTENTI_SALT_PKG

 DESCRIZIONE: Funzione per la determinazione del url di accesso alla documentazione condivisa
 ANNOTAZIONI:
 REVISIONI:

 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 00   03/03/2020 SN     Creazione.
 01   22/09/2020 Neg    Introduzione get_algoritmo_usato

 *****************************************************************************/
  --genera una stringa di 128bit(32 caratteri) utilizzata come SALT. In futuro si potrà aumentare la lunghezza del salt modificando l'algoritmo di HASH utilizzato
function generate_salt
return varchar2;

PROCEDURE SISTEMA_PASSWORD (p_utente varchar2, p_password IN OUT varchar2, p_algoritmo IN OUT varchar2);

function get_algoritmo_usato
(
  p_utente  in UTENTI_SALT.utente%type
) return UTENTI_SALT.algoritmo%type;

end;
/
