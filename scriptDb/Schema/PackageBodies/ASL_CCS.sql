CREATE OR REPLACE PACKAGE body ASL_CCS is
raise_prefix           constant varchar2(200) := 'ASL_CCS: ';
function ASL_EXISTS_ID
(in_regione_asl          in number
,in_codice_asl           in number
,in_errore               in number default 1
) return number
is
  esito                  number := 0;
begin
  select 1
    into esito
    from ad4_asl
   where regione_asl = in_regione_asl
     and codice_asl  = in_codice_asl
     and rownum      = 1
  ;
  return esito;
exception when no_data_found then
  if in_errore > 0 then
    if in_regione_asl is null then
      raise_application_error(-20920,raise_prefix||'Regione non specificata');
    elsif in_codice_asl is null then
      raise_application_error(-20921,raise_prefix||'Codice Asl non specificato');
    else
      raise_application_error(-20922,raise_prefix||'Regione-Codice Asl '||in_regione_asl||'-'||in_codice_asl||' inesistente');
    end if;
  else
    return esito;
  end if;
end;
function GET_ASL_DESCRIZIONE
(in_regione_asl          in number
,in_codice_asl           in number
) return varchar2
is
  asl_descrizione            ad4_asl.descrizione%type;
begin
  select descrizione
    into asl_descrizione
    from ad4_asl
   where regione_asl = in_regione_asl
     and codice_asl  = in_codice_asl
     and rownum    = 1
  ;
  return asl_descrizione;
exception when no_data_found then
  return null;
end;
procedure ASL_INS
(in_regione_asl          in number
,in_codice_asl           in number
,in_descrizione          in varchar2
,in_utente               in varchar2
,in_attiva               in varchar2
)
is
begin
  if in_regione_asl is null then
    raise_application_error(-20932,raise_prefix||'Regione obbligatoria');
  elsif in_codice_asl is null then
    raise_application_error(-20933,raise_prefix||'Codice ASL obbligatorio');
  elsif ad4_ccs.comune_exists_id(in_regione_asl,in_codice_asl,0) = 1 then
    raise_application_error(-20934,raise_prefix||'ASL già presente ('||in_regione_asl||'-'||in_codice_asl||')');
  end if;
  insert into ad4_asl
    (regione_asl
    ,codice_asl
    ,descrizione
    ,utente_aggiornamento
    ,data_aggiornamento
    ,attiva
    )
  values
    (in_regione_asl
    ,in_codice_asl
    ,in_descrizione
    ,in_utente
    ,sysdate
    ,in_attiva
    );
end;
procedure ASL_UPD
(in_regione_asl          in number
,in_codice_asl           in number
,in_descrizione          in varchar2
,in_utente               in varchar2
,in_attiva               in varchar2
)
is
begin
  update ad4_asl
     set descrizione            = upper(in_descrizione)
        ,utente_aggiornamento   = in_utente
        ,data_aggiornamento     = sysdate
        ,attiva                 = in_attiva
   where regione_asl     = in_regione_asl
     and codice_asl      = in_codice_asl
  ;
  if sql%notfound then
    raise_application_error(-20935,raise_prefix||'Errore aggiornamento ASL('||in_regione_asl||'-'||in_codice_asl||')');
  end if;
end;
procedure ASL_DEL
(in_regione_asl          in number
,in_codice_asl           in number
)
is
begin
  delete ad4_asl
   where regione_asl     = in_regione_asl
     and codice_asl      = in_codice_asl
  ;
  if sql%notfound then
    raise_application_error(-20936,raise_prefix||'Errore eliminazione ASL('||in_regione_asl||'-'||in_codice_asl||')');
  end if;
end;
function ASL_COMUNE_EXISTS_ID
(in_provincia            in number
,in_comune               in number
,in_regione_asl          in number
,in_codice_asl           in number
,in_errore               in number default 1
) return number
is
  esito                  number := 0;
begin
  select 1
    into esito
    from ad4_asl_comune
   where provincia   = in_provincia
     and comune      = in_comune
     and regione_asl = in_regione_asl
     and codice_asl  = in_codice_asl
     and rownum      = 1
  ;
  return esito;
exception when no_data_found then
  if in_errore > 0 then
    if in_regione_asl is null then
      raise_application_error(-20947,raise_prefix||'Regione non specificata');
    elsif in_codice_asl is null then
      raise_application_error(-20948,raise_prefix||'Codice Asl non specificato');
    elsif in_provincia is null then
      raise_application_error(-20949,raise_prefix||'Provincia nonspecificata');
    elsif in_comune is null then
      raise_application_error(-20950,raise_prefix||'Comune non specificato');
    else
      raise_application_error(-20951,raise_prefix||'Provincia-Comune-Regione-Codice '||in_provincia||'-'||in_comune||'-'||in_regione_asl||'-'||in_codice_asl||' inesistente');
    end if;
  else
    return esito;
  end if;
end;
procedure ASL_COMUNE_INS
(in_provincia            in number
,in_comune               in number
,in_regione_asl          in number
,in_codice_asl           in number
,in_proposta             in varchar2
,in_attiva               in varchar2
,in_utente               in varchar2
)
is
begin
  if in_provincia is null then
    raise_application_error(-20956,raise_prefix||'Provincia obbligatoria');
  elsif in_comune is null then
    raise_application_error(-20957,raise_prefix||'Comune obbligatorio');
  elsif in_regione_asl is null then
    raise_application_error(-20958,raise_prefix||'Regione Asl obbligatoria');
  elsif in_codice_asl is null then
    raise_application_error(-20959,raise_prefix||'Codice Asl obbligatorio');
  elsif asl_comune_exists_id(in_provincia,in_comune,in_regione_asl,in_codice_asl,0) = 1 then
    raise_application_error(-20960,raise_prefix||'Comune Asl già presente '||in_provincia||'-'||in_comune||'-'||in_regione_asl||'-'||in_codice_asl||' inesistente');
  end if;
  insert into ad4_asl_comune
    (provincia
    ,comune
    ,regione_asl
    ,codice_asl
    ,proposta
    ,attiva
    ,utente_aggiornamento
    ,data_aggiornamento
    )
  values
    (in_provincia
    ,in_comune
    ,in_regione_asl
    ,in_codice_asl
    ,in_proposta
    ,in_attiva
    ,in_utente
    ,sysdate
    );
end;
procedure ASL_COMUNE_UPD
(in_provincia            in number
,in_comune               in number
,in_regione_asl          in number
,in_codice_asl           in number
,in_proposta             in varchar2
,in_attiva               in varchar2
,in_utente               in varchar2
)
is
begin
  update ad4_asl_comune
     set proposta = in_proposta
        ,attiva   = in_attiva
        ,utente_aggiornamento = in_utente
        ,data_aggiornamento   = sysdate
   where provincia   = in_provincia
     and comune      = in_comune
     and regione_asl = in_regione_asl
     and codice_asl  = in_codice_asl
  ;
  if sql%notfound then
    raise_application_error(-20961,raise_prefix||'Errore aggiornamento Asl Comune('||in_provincia||'-'||in_comune||'-'||in_regione_asl||'-'||in_codice_asl||' inesistente');
  end if;
end;
procedure ASL_COMUNE_DEL
(in_provincia            in number
,in_comune               in number
,in_regione_asl          in number
,in_codice_asl           in number
)
is
begin
  delete ad4_asl_comune
   where provincia   = in_provincia
     and comune      = in_comune
     and regione_asl = in_regione_asl
     and codice_asl  = in_codice_asl
  ;
  if sql%notfound then
    raise_application_error(-20962,raise_prefix||'Errore eliminazione Asl Comune('||in_provincia||'-'||in_comune||'-'||in_regione_asl||'-'||in_codice_asl||' inesistente');
  end if;
end;
end;
/

