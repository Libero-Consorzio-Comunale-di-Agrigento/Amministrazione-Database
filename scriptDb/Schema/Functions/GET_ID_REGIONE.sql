CREATE OR REPLACE FUNCTION Get_Id_Regione
/******************************************************************************
 NOME:        GET_ID_REGIONE
 DESCRIZIONE: Dato un codice regione, ritorna il suo id.
 PARAMETRI:   p_regione number Codice regione.
 RITORNA:     number id_regione
 ECCEZIONI:   -
 ANNOTAZIONI: Salvata nella directory ins di AD4 con nome GET_IDREG.CRF.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    03/02/2004 MM     Prima emissione.
 1    01/12/2006 MM     Richiamo funzione di package.
******************************************************************************/
( p_regione NUMBER)
RETURN NUMBER
IS
BEGIN
   RETURN Regione.get_id(p_regione);
END;
/

