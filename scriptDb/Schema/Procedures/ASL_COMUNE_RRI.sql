CREATE OR REPLACE procedure ASL_COMUNE_RRI
/******************************************************************************
 NOME:        ASL_COMUNE_RRI
 DESCRIZIONE: Se viene inserita una nuova ASL o aggiornata una esistente con il
              campo PROPOSTA = 'S', controlla che non esistano gia' altri
              comuni per la stessa ASL con PROPOSTA = 'S'.
 ARGOMENTI:   p_provincia    IN number
              p_comune       IN number
              p_regione_asl  IN number
              p_codice_asl   IN number
              p_new_proposta IN varchar2
 ECCEZIONI:   20999, Esiste gia' un Comune 'da proporre' per la ASL.
 ANNOTAZIONI: Salvata nella directory ins di AD4 con nome ASCO_RRI.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    22/05/2002 MM     Prima emissione.
******************************************************************************/
( p_provincia IN number
, p_comune IN number
, p_regione_asl IN number
, p_codice_asl IN number
, p_new_proposta IN varchar2
)
is
   d_esiste_proposta number;
begin
   if nvl(p_new_proposta,'N') = 'S' then
      begin
         select count(1)
           into d_esiste_proposta
           from asl_comune
          where nvl(proposta,'N') = 'S'
            and regione_asl = p_regione_asl
            and codice_asl  = p_codice_asl
            and (  provincia <> p_provincia
                or comune    <> p_comune)
         ;
      end;
      if d_esiste_proposta > 0 then
         raise_application_error(-20999,'Esiste gia'' un Comune ''da proporre'' per la ASL.');
      end if;
   end if;
end;
/

