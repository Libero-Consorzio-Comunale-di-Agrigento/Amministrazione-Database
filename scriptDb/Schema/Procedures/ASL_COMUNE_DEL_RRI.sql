CREATE OR REPLACE procedure ASL_COMUNE_DEL_RRI
/******************************************************************************
 NOME:        ASL_COMUNE_DEL_RRI
 DESCRIZIONE: Se la ASL non e' associata a nessun comune, la elimina.
 ARGOMENTI:   p_regione_asl  IN number codice regione.
              p_codice_asl   IN number codice asl.
 ECCEZIONI:   -
 ANNOTAZIONI: Salvata nella directory ins di AD4 con nome ASCO_DEL_RRI
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    20/02/2003 MM     Prima emissione.
******************************************************************************/
( p_regione_asl IN number
, p_codice_asl IN number
)
is
   d_esiste number;
begin
   begin
      select count(1)
        into d_esiste
        from asl_comune
       where regione_asl = p_regione_asl
         and codice_asl  = p_codice_asl
      ;
   end;
   if d_esiste = 0 then
      delete asl
      where regione_asl = p_regione_asl
         and codice_asl  = p_codice_asl
     ;
   end if;
end;
/

