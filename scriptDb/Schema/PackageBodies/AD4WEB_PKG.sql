CREATE OR REPLACE PACKAGE BODY Ad4web_pkg IS
FUNCTION VERSIONE  RETURN VARCHAR2 IS
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione di ad4web.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione.
 NOTE:        --
******************************************************************************/
v_versione istanze.versione%TYPE;
BEGIN
   select versione
     into v_versione
     from istanze
    where istanza = 'AD4';
   RETURN v_versione;
END VERSIONE;
END Ad4web_pkg;
/

