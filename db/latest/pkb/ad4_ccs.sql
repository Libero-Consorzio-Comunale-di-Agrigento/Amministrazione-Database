--liquibase formatted sql

--changeset mturra:201901301231_104 runOnChange:true stripComments:false

CREATE OR REPLACE package body AD4_CCS is
report_servlet         varchar2(200) := '/jasperserver/jasperservlet';
report_name_param      vArchar2(200) := 'REPORT';
report_project_param   varchar2(200) := 'PROJECT';
report_project_value   varchar2(200) := 'AD4WEB';
raise_max              constant number(5)     := 20967;
raise_prefix           constant varchar2(200) := 'AD4_CCS: ';
break                  constant varchar2(5) := chr(10);
image_path             constant varchar2(512) := '../images/';
image_size             constant varchar2(100) := 'width="18" heigth="18"';

 FUNCTION versione
      RETURN VARCHAR2
   IS                              /* SLAVE_COPY */
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 ECCEZIONI:   --
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    xx/xx/2019 AD     Creazione.
 1    10/02/2020  SNeg  Gestione nuova colonna data_istituzione
******************************************************************************/
   BEGIN
      RETURN 'V1.1';
   END versione;

function get_win_feature
return varchar2
is
begin
    return '''dependent=yes, titlebar=no, scrollbars=yes, center=yes, help=no, resizable=no, status=no, width=600,height=150'''; --,left=200,top=200''';
end;


function FIELD_FOCUS
(in_form_name                 in varchar2
,in_field_name                in varchar2
)
return html
is
  out_js                         html;
begin
  out_js := '<script language="JavaScript">'||break||
            '  document.forms['''||in_form_name||'''].'||in_field_name||'.focus();'||break||
            '</script>';
  return out_js;
end;
function FORMAT_DATA_TRUNC
(in_data              in date
) return varchar2
is
begin
  return to_char(in_data,'dd/mm/yyyy');
end;
function TO_DATA
(in_data              in varchar2
) return date
is
  sep0                   number(2);  -- ultimo carattere validato
  sep1                   number(2);  -- prossimo separatore
  anno                   varchar2(4) := null;
  rif_data               varchar2(20):= null;
  out_data               date;
  errore                 varchar2(255) := null;
begin
  if in_data is null then
    return null;
  end if;
  if replace(in_data,'/',null) like '01010001%' then
    return to_date('01010001','ddmmyyyy');
  end if;
  /* Formato standard di Oracle */
  begin
    rif_data := to_char(to_date(in_data,'dd-mon-yy'),'dd/mm/yyyy');
  exception when others then
    rif_data := null;
  end;
  if upper(in_data) in ('NOW') then
    rif_data := to_char(sysdate,'dd/mm/yyyy');
  end if;
  /* Possibili separatori */
  rif_data := nvl(rif_data,in_data);
  rif_data := replace(rif_data,' ','/');
  rif_data := replace(rif_data,'.','/');
  rif_data := replace(rif_data,'-','/');
  rif_data := replace(rif_data,':','/');
  /*  Parsing Separatori */
  sep0 := 0;
  while instr(rif_data,'/') > 0
  loop
    sep1     := instr(rif_data,'/',sep0+1,1);
    if sep1 = 0 then
      exit;
    elsif sep1-sep0 = 1 then
      rif_data:=  '00'||
                       substr(rif_data,sep1+1       );
    elsif sep0       = 4
      and sep1-sep0 <= 5 then
      rif_data:=       substr(rif_data,1     ,sep0  )||
                  lpad(substr(rif_data,sep0+1,sep1-sep0-1),4,'0')||
                       substr(rif_data,sep1+1       );
    elsif sep1-sep0 <= 3 then
      rif_data:=       substr(rif_data,1     ,sep0  )||
                  lpad(substr(rif_data,sep0+1,sep1-sep0-1),2,'0')||
                       substr(rif_data,sep1+1       );
    else
      sep0 := sep0+2;
      if sep0 = 6 then
        sep0 := 8;
      end if;
    end if;
  end loop;
  /* Controlli */
  if    instr(rif_data,'/') > 0 then
    errore   := 'Separatori non corretti '||rif_data||' risultato di ';
  elsif lpad(translate(rif_data,'0123456789','9999999999'),20,'9') != lpad('9',20,'9') then
    errore   := 'Caratteri non validi';
  elsif nvl(length(rif_data), 0) not between 6 and 14 then
    errore   := 'Formato non riconosciuto';
  elsif to_number(substr(rif_data,3,2)) not between 1 and 12 then
    errore   := 'Mese non valido';
  else
    anno := substr(rif_data,5,4);
    if nvl(length(anno),0) = 3 then
      anno := to_char(to_date(anno,'yyy'),'yyyy');
    elsif nvl(length(anno), 0) = 2 then
      begin
        anno := to_char(to_date(anno,'rr'),'yyyy');
      exception when others then
        anno := to_char(to_date(anno,'yy'),'yyyy');
      end;
    elsif nvl(length(anno), 0) = 1 then
      begin
        anno := to_char(to_date(lpad(anno,2,'0'),'rr'),'yyyy');
      exception when others then
        anno := to_char(to_date(lpad(anno,2,'0'),'yy'),'yyyy');
      end;
    end if;
    rif_data := substr(rif_data,1,4)||anno||substr(rif_data,9);
    if anno = 0 then
       errore := 'L''anno ZERO non esiste';
    elsif to_number(substr(rif_data,1,2)) not between 1 and to_number(to_char(last_day(to_date(substr(rif_data,3,6),'mmyyyy')),'dd')) then
       errore   := 'Giorno non compreso tra 1 e l''ultimo giorno del mese';
    end if;
  end if;
  if errore is not null then
    raise_application_error(-20901,raise_prefix||errore||' ('||in_data||')');
  else
    if rif_data is null then
      out_data := null;
    elsif length(rif_data) = 8 then
      out_data := to_date(rif_data,'ddmmyyyy');
    elsif length(rif_data) = 10 then
      out_data := to_date(rif_data,'ddmmyyyyhh24');
    elsif length(rif_data) = 12 then
      out_data := to_date(rif_data,'ddmmyyyyhh24mi');
    elsif length(rif_data) = 14 then
      out_data := to_date(rif_data,'ddmmyyyyhh24miss');
    end if;
  end if;
  return out_data;
end;
function INIZIALIZZA
return varchar2
is
  pragma autonomous_transaction;
begin
  execute immediate 'alter session set nls_numeric_characters = '', ''';
  execute immediate 'alter session set nls_sort = binary';
  execute immediate 'alter session set nls_date_format = ''dd/mm/yyyy''';
  commit;
  return null;
end;
function LINK_MULTI_LOV
(in_page                    in varchar2
,in_features                in varchar2
,in_title                   in varchar2
,in_form                    in varchar2
,in_fields                  in varchar2
) return html
is
  page                         varchar2(2000);
  out_html                     html := null;
begin
  if upper(in_page) like '%.DO%' then
     page := in_page;
  else
     page := in_page||'.do';
  end if;
  out_html := out_html
  ||'<a class="AFCDataLink"'
  ||' href="javascript:showMultiLOV('''||page||''','''||in_features||''','''||in_form||''','''||in_fields||''');"'
  ||'>'
  ||'<img src="../images/lov.gif" '||image_size||' border="0" alt="'||in_title||'">'
  ||'</a>'
  ;
  return out_html;
end;
function TO_REPORT_LINK
(in_report_link         in varchar2,
 in_report_icon         in number,
 in_report_title        in varchar2,
 in_report_name         in varchar2,
 in_report_parameter    in varchar2
) return html
is
  report_html  html;
begin
  raise_application_error (-20999,'Impossibile generare report');
  return report_html ;
exception when no_data_found then
  return null;
end;
function GET_HEADER
(in_risorsa_id         in varchar2
) return html
is
  risorsa                 html;
begin
--  risorsa := san_struttura.get_descrizione(amb_risorsa.get_struttura(in_risorsa_id));
--  risorsa := risorsa||' - '||san_ambulatorio.get_descrizione(amb_risorsa.get_ambulatorio(in_risorsa_id));
--  risorsa := risorsa||' ('||amb_risorsa.get_tipo_agenda(in_risorsa_id)||')';
--  risorsa := '<font style="font-size: 120%; font-weight: bolder; ">'
--  ||risorsa
--  ||'</font>'
--  ;
  return risorsa;
exception when no_data_found then
  return null;
end;
function GET_OPERATORE
(in_utente             in varchar2
,in_data               in date
) return html
is
  operatore               html;
begin
  operatore := operatore
  --||nvl(san_utente.get_nominativo(in_utente),in_utente)
  ||' '||to_char(in_data,'dd/mm/yyyy hh24:mi')
  ;
  return operatore;
end;
function TAB_FOLDER
(in_link               in varchar2,
 in_href               in varchar2,
 in_active             in varchar2 default 'N'
) return html
is
  class_begin html;
  class_body  html := 'AFCGuida';
  class_end   html;
  class_link  html;
  image       html := '../Themes/AFC/GuidaBlank.gif';
  out_html    html := null;
begin
  if in_active = 'S' then
    class_body  := class_body||'Sel';
  end if;
  class_begin := class_body||'L';
  class_end   := class_body||'R';
  class_link  := class_body||'Link';
  out_html := out_html||'
<table cellpadding="0" cellspacing="0" border="0"><tr>
<td align="left" valign="top" class="'||class_begin||'">
  <img src="'||image||'" >
</td>
<td align="left" valign="center" nowrap class="'||class_body||'">
  <a class="'||class_link||'" href="'||in_href||'">'||in_link||'</a>
</td>
<td align="left" valign="top" class="'||class_end||'">
  <img src="'||image||'" >
</td>
</tr></table>
';
  return out_html;
end;
function FILTER_SEARCH
(in_filter_value       in varchar2
) return html
is
  image                          varchar2(512);
  title                          varchar2(512);
  out_html                       html := null;
begin
  if in_filter_value is null then
    image := '../images/filtro_off.gif';
    title := 'Filtro non attivo';
  else
    image := '../images/filtro_on.gif';
    title := 'Filtro attivo';
  end if;
  out_html := out_html
  ||'<img src="'||image||'" '||image_size||' border="0" alt="'||title||'">'
  ;
  return out_html;
end;
function object_asl_exists return number
is
    d_result    number;
begin
    begin
        select 1
          into d_result
          from user_objects
         where object_name = 'ASL'
           and object_type = 'TABLE'
           ;
    exception when no_data_found then
        d_result := 0;
    end;
    return d_result;
end;
function REGIONE_EXISTS_ID
(in_regione              in number
,in_errore               in number default 1
) return number
is
  esito                  number := 0;
begin
  select 1
    into esito
    from ad4_regioni
   where regione = in_regione
     and rownum        = 1
  ;
  return esito;
exception when no_data_found then
  if in_errore > 0 then
    if in_regione is null then
      raise_application_error(-20901,raise_prefix||'Regione non specificata');
    else
      raise_application_error(-20902,raise_prefix||'Regione '||in_regione||' inesistente');
    end if;
  else
    return esito;
  end if;
end;
function PROVINCIA_EXISTS_ID
(in_provincia            in number
,in_errore               in number default 1
) return number
is
  esito                  number := 0;
begin
  select 1
    into esito
    from ad4_province
   where provincia = in_provincia
     and rownum        = 1
  ;
  return esito;
exception when no_data_found then
  if in_errore > 0 then
    if in_provincia is null then
      raise_application_error(-20901,raise_prefix||'Provincia non specificata');
    else
      raise_application_error(-20902,raise_prefix||'Provincia '||in_provincia||' inesistente');
    end if;
  else
    return esito;
  end if;
end;
function COMUNE_EXISTS_ID
(in_provincia_stato      in number
,in_comune               in number
,in_errore               in number default 1
) return number
is
  esito                  number := 0;
begin
  select 1
    into esito
    from ad4_comuni
   where provincia_stato = in_provincia_stato
     and comune          = in_comune
     and rownum        = 1
  ;
  return esito;
exception when no_data_found then
  if in_errore > 0 then
    if in_provincia_stato is null then
      raise_application_error(-209017,raise_prefix||'Provincia non specificata');
    elsif in_comune is null then
      raise_application_error(-20918,raise_prefix||'Comune non specificato');
    else
      raise_application_error(-20919,raise_prefix||'Provincia-Comune '||in_provincia_stato||'-'||in_comune||' inesistente');
    end if;
  else
    return esito;
  end if;
end;
function ASL_EXISTS_ID
(in_regione_asl          in number
,in_codice_asl           in number
,in_errore               in number default 1
) return number
is
  esito                  number := 0;
  d_statement            varchar2(4000);
begin
  if object_asl_exists = 1 then
    d_statement := 'begin :esito := asl_ccs.ASL_EXISTS_ID(:in_regione_asl,:in_codice_asl,:in_errore); end;';
      execute immediate d_statement
         using in out esito, in_regione_asl,in_codice_asl,in_errore;
  end if;
  return esito;
end;
function ZONA_ASL_EXISTS_ID
(in_id_zona_asl          in number
,in_errore               in number default 1
) return number
is
  esito                  number := 0;
begin
raise_application_error(-20920, 'ERRORE non esiste ZONE ASL');
  return -1;
--  select 1
--    into esito
--    from zone_asl
--   where id_zona_asl = in_id_zona_asl
--     and rownum        = 1
--  ;
--  return esito;
--exception when no_data_found then
--  if in_errore > 0 then
--    if in_id_zona_asl is null then
--      raise_application_error(-20923,raise_prefix||'Zona Asl non specificata');
--    else
--      raise_application_error(-20924,raise_prefix||'Zona Asl '||in_id_zona_asl||' inesistente');
--    end if;
--  else
--    return esito;
--  end if;
end;
function STATO_TERRITORIO_EXISTS_ID
(in_stato_territorio     in number
,in_errore               in number default 1
) return number
is
  esito                  number := 0;
begin
  select 1
    into esito
    from ad4_stati_territori
   where stato_territorio = in_stato_territorio
     and rownum        = 1
  ;
  return esito;
exception when no_data_found then
  if in_errore > 0 then
    if in_stato_territorio is null then
      raise_application_error(-20925,raise_prefix||'Stato territorio non specificato');
    else
      raise_application_error(-20926,raise_prefix||'Stato Territorio '||in_stato_territorio||' inesistente');
    end if;
  else
    return esito;
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
  d_statement            varchar2(4000);
begin
  if object_asl_exists = 1 then
    d_statement := 'begin :esito := asl_ccs.ASL_COMUNE_EXISTS_ID(:in_provincia,:in_comune,:in_regione_asl,:in_codice_asl,:in_errore); end;';
      execute immediate d_statement
         using in out esito, in_provincia,in_comune,in_regione_asl,in_codice_asl,in_errore;
  end if;
  return esito;
end;
function ZONA_ASL_COMUNE_EXISTS_ID
(in_id_zona_asl          in number
,in_provincia            in number
,in_comune               in number
,in_errore               in number default 1
) return number
is
  esito                  number := 0;
begin
raise_application_error(-20920, 'NON esiste zone asl comuni');
  return -1;
--  select 1
--    into esito
--    from zone_asl_comuni
--   where id_zona_asl = in_id_zona_asl
--     and provincia   = in_provincia
--     and comune      = in_comune
--     and rownum      = 1
--  ;
--  return esito;
--exception when no_data_found then
--  if in_errore > 0 then
--    if in_id_zona_asl is null then
--      raise_application_error(-20952,raise_prefix||'Id Zona ASL non specificato');
--    elsif in_provincia is null then
--      raise_application_error(-20953,raise_prefix||'Provincia non specificata');
--    elsif in_comune is null then
--      raise_application_error(-20954,raise_prefix||'Comune non specificato');
--    else
--      raise_application_error(-20955,raise_prefix||'Id Zona-Provincia-Comune '||in_id_zona_asl||'-'||in_provincia||'-'||in_comune||' inesistente');
--    end if;
--  else
--    return esito;
--  end if;
end;
function GET_REGIONE_DENOMINAZIONE
(in_regione              in number
) return varchar2
is
  regi_denominazione            ad4_regioni.denominazione%type;
begin
  select denominazione
    into regi_denominazione
    from ad4_regioni
   where regione = in_regione
     and rownum    = 1
  ;
  return regi_denominazione;
exception when no_data_found then
  return null;
end;
function GET_PROVINCIA_DENOMINAZIONE
(in_provincia            in number
) return varchar2
is
  prov_denominazione            ad4_province.denominazione%type;
begin
  select denominazione
    into prov_denominazione
    from ad4_province
   where provincia = in_provincia
     and rownum    = 1
  ;
  return prov_denominazione;
exception when no_data_found then
  return null;
end;
function GET_BANCA_DENOMINAZIONE
(in_ABI             in varchar2
) return varchar2
is
  BANCA_denominazione            ad4_BANCHE.denominazione%type;
begin
  select denominazione
    into BANCA_denominazione
    from ad4_BANCHE
   where ABI = IN_ABI
     and rownum    = 1
  ;
  return BANCA_denominazione;
exception when no_data_found then
  return null;
end;
function GET_PROVINCIA_ID_FROM_DENOMIN
(in_denominazione        in varchar2
) return number
is
  prov_provincia            ad4_province.provincia%type;
begin
  select provincia
    into prov_provincia
    from ad4_province
   where denominazione = in_denominazione
     and rownum    = 1
  ;
  return prov_provincia;
exception when no_data_found then
  return null;
end;
function GET_PROVINCIA_ID_FROM_SIGLA
(in_sigla                in varchar2
) return number
is
  prov_provincia            ad4_province.provincia%type;
begin
  select provincia
    into prov_provincia
    from ad4_province
   where upper(sigla) = upper(in_sigla)
     and rownum    = 1
  ;
  return prov_provincia;
exception when no_data_found then
  return null;
end;
function GET_COMUNE_DENOMINAZIONE
(in_provincia_stato      in number
,in_comune               in number
) return varchar2
is
  comu_denominazione            ad4_comuni.denominazione%type;
begin
  select denominazione
    into comu_denominazione
    from ad4_comuni
   where provincia_stato = in_provincia_stato
     and comune          = in_comune
     and rownum    = 1
  ;
  return comu_denominazione;
exception when no_data_found then
  return null;
end;
function GET_ASL_DESCRIZIONE
(in_regione_asl          in number
,in_codice_asl           in number
) return varchar2
is
  ret_value            varchar2(2000):= null;
  d_statement            varchar2(4000);
begin
  if object_asl_exists = 1 then
    d_statement := 'begin :ret_value := asl_ccs.GET_ASL_DESCRIZIONE(:in_regione_asl,:in_codice_asl); end;';
      execute immediate d_statement
         using in out ret_value, in_regione_asl,in_codice_asl;
  end if;
  return ret_value;
end;
--function GET_ZONA_ASL_TITOLO
--(in_id_zona_asl          in number
--) return varchar2
--is
--  zona_titolo            zone_asl.titolo%type;
--begin
--  select titolo
--    into zona_titolo
--    from zone_asl
--   where id_zona_asl = in_id_zona_asl
--     and rownum    = 1
--  ;
--  return zona_titolo;
--exception when no_data_found then
--  return null;
--end;
procedure REGIONE_INS
(in_regione              in number
,in_denominazione        in varchar2
,in_id_regione           in number
,in_utente               in varchar2
)
is
begin
  if in_regione is null then
    raise_application_error(-20903,raise_prefix||'Regione obbligatoria');
  elsif regione_exists_id(in_regione,0) = 1 then
    raise_application_error(-20904,raise_prefix||'Regione già presente ('||in_regione||')');
  end if;
  if in_id_regione is null then
    raise_application_error(-20905,raise_prefix||'Id Regione richiesto');
  end if;
  insert into ad4_regioni
    (regione
    ,denominazione
    ,id_regione
    ,utente_aggiornamento
    ,data_aggiornamento
    )
  values
    (in_regione
    ,upper(in_denominazione)
    ,in_id_regione
    ,in_utente
    ,sysdate
    );
end;
procedure REGIONE_UPD
(in_regione              in number
,in_denominazione        in varchar2
,in_id_regione           in number
,in_utente               in varchar2
)
is
begin
  if in_id_regione is null then
    raise_application_error(-20906,raise_prefix||'Id Regione richiesto');
  end if;
  update ad4_regioni
     set denominazione        = upper(in_denominazione)
        ,id_regione           = in_id_regione
        ,utente_aggiornamento = in_utente
        ,data_aggiornamento   = sysdate
   where regione  = in_regione
  ;
  if sql%notfound then
    raise_application_error(-20907,raise_prefix||'Errore aggiornamento Regione('||in_regione||')');
  end if;
end;
procedure REGIONE_DEL
(in_regione              in number
)
is
    dummy   number;
begin
  begin
    select provincia
      into dummy
      from ad4_province
     where regione = in_regione;
    raise too_many_rows;
  exception when too_many_rows then
    raise_application_error(-20907,raise_prefix||'Errore eliminazione Regione ('||in_regione||'): esistono Province collegate');
  when no_data_found then
    null;
  end;
  delete ad4_regioni
   where regione = in_regione
  ;
  if sql%notfound then
    raise_application_error(-20908,raise_prefix||'Errore eliminazione Regione ('||in_regione||')');
  end if;
end;
procedure PROVINCIA_INS
(in_provincia            in number
,in_denominazione        in varchar2
,in_regione              in number
,in_sigla                in varchar2
,in_utente               in varchar2
)
is
begin
  if in_provincia is null then
    raise_application_error(-20909,raise_prefix||'Provincia obbligatoria');
  elsif provincia_exists_id(in_provincia,0) = 1 then
    raise_application_error(-20910,raise_prefix||'Provincia già presente ('||in_provincia||')');
  end if;
  if in_regione is null then
    raise_application_error(-20915,raise_prefix||'Regione obbligatoria');
  end if;
  if in_sigla is null then
    raise_application_error(-20916,raise_prefix||'Sigla obbligatoria');
  end if;
  insert into ad4_province
    (provincia
    ,denominazione
    ,regione
    ,sigla
    ,utente_aggiornamento
    ,data_aggiornamento
    )
  values
    (in_provincia
    ,upper(in_denominazione)
    ,in_regione
    ,upper(in_sigla)
    ,in_utente
    ,sysdate
    );
end;
procedure PROVINCIA_UPD
(in_provincia            in number
,in_denominazione        in varchar2
,in_regione              in number
,in_sigla                in varchar2
,in_utente               in varchar2
)
is
begin
  if in_regione is null then
    raise_application_error(-20911,raise_prefix||'Regione obbligatoria');
  end if;
  if in_sigla is null then
    raise_application_error(-20912,raise_prefix||'Sigla obbligatoria');
  end if;
  update ad4_province
     set denominazione        = upper(in_denominazione)
        ,regione              = in_regione
        ,sigla                = upper(in_sigla)
        ,utente_aggiornamento = in_utente
        ,data_aggiornamento   = sysdate
   where provincia  = in_provincia
  ;
  if sql%notfound then
    raise_application_error(-20913,raise_prefix||'Errore aggiornamento Provincia('||in_provincia||')');
  end if;
end;
procedure PROVINCIA_DEL
(in_provincia            in number
)
is
    dummy   number;
begin
  begin
    select comune
      into dummy
      from ad4_comuni
     where provincia_stato = in_provincia;
    raise too_many_rows;
  exception when too_many_rows then
    raise_application_error(-20907,raise_prefix||'Errore eliminazione Provincia ('||in_provincia||'): esistono Comuni collegati');
  when no_data_found then
    null;
  end;
  delete ad4_province
   where provincia = in_provincia
  ;
  if sql%notfound then
    raise_application_error(-20914,raise_prefix||'Errore eliminazione Provincia ('||in_provincia||')');
  end if;
end;
procedure COMUNE_INS
(in_provincia_stato      in number
,in_comune               in number
,in_denominazione        in varchar2
,in_denominazione_breve  in varchar2
,in_capoluogo_provincia  in varchar2
,in_cap                  in number
,in_provincia_tribunale  in number
,in_comune_tribunale     in number
,in_data_soppressione    in varchar2
,in_provincia_fusione    in number
,in_comune_fusione       in number
,in_sigla_cfis           in varchar2
,in_utente               in varchar2
,in_data_istituzione     in varchar2
)
is
begin
  if in_provincia_stato is null then
    raise_application_error(-20927,raise_prefix||'Provincia obbligatoria');
  elsif in_comune is null then
    raise_application_error(-20928,raise_prefix||'Comune obbligatorio');
  elsif comune_exists_id(in_provincia_stato,in_comune,0) = 1 then
    raise_application_error(-20929,raise_prefix||'Comune già presente ('||in_provincia_stato||'-'||in_comune||')');
  end if;
  insert into ad4_comuni
    (provincia_stato
    ,comune
    ,denominazione
    ,denominazione_breve
    ,capoluogo_provincia
    ,cap
    ,provincia_tribunale
    ,comune_tribunale
    ,data_soppressione
    ,provincia_fusione
    ,comune_fusione
    ,sigla_cfis
    ,utente_aggiornamento
    ,data_aggiornamento
    ,data_istituzione
    )
  values
    (in_provincia_stato
    ,in_comune
    ,upper(in_denominazione)
    ,upper(in_denominazione_breve)
    ,in_capoluogo_provincia
    ,in_cap
    ,in_provincia_tribunale
    ,in_comune_tribunale
    ,ad4_ccs.to_data(in_data_soppressione)
    ,in_provincia_fusione
    ,in_comune_fusione
    ,in_sigla_cfis
    ,in_utente
    ,sysdate
    ,ad4_ccs.to_data(in_data_istituzione)
    );
end;
procedure COMUNE_UPD
(in_provincia_stato      in number
,in_comune               in number
,in_denominazione        in varchar2
,in_denominazione_breve  in varchar2
,in_capoluogo_provincia  in varchar2
,in_cap                  in number
,in_provincia_tribunale  in number
,in_comune_tribunale     in number
,in_data_soppressione    in varchar2
,in_provincia_fusione    in number
,in_comune_fusione       in number
,in_sigla_cfis           in varchar2
,in_utente               in varchar2
,in_data_istituzione     in varchar2
)
is
begin
  update ad4_comuni
     set denominazione            = upper(in_denominazione)
        ,denominazione_breve      = upper(in_denominazione_breve)
        ,capoluogo_provincia      = in_capoluogo_provincia
        ,cap                      = in_cap
        ,provincia_tribunale      = in_provincia_tribunale
        ,comune_tribunale         = in_comune_tribunale
        ,data_soppressione        = ad4_ccs.to_data(in_data_soppressione)
        ,provincia_fusione        = in_provincia_fusione
        ,comune_fusione           = in_comune_fusione
        ,sigla_cfis               = in_sigla_cfis
        ,utente_aggiornamento     = in_utente
        ,data_aggiornamento       = sysdate
        ,data_istituzione         = ad4_ccs.to_data(in_data_istituzione)
   where provincia_stato  = in_provincia_stato
     and comune           = in_comune
  ;
  if sql%notfound then
    raise_application_error(-20930,raise_prefix||'Errore aggiornamento Comune('||in_provincia_stato||'-'||in_comune||')');
  end if;
end;
procedure COMUNE_DEL
(in_provincia_stato      in number
,in_comune               in number
)
is
begin
  delete ad4_comuni
   where provincia_stato  = in_provincia_stato
     and comune           = in_comune
  ;
  if sql%notfound then
    raise_application_error(-20931,raise_prefix||'Errore eliminazione Comune('||in_provincia_stato||'-'||in_comune||')');
  end if;
end;
procedure ASL_INS
(in_regione_asl          in number
,in_codice_asl           in number
,in_descrizione          in varchar2
,in_utente               in varchar2
,in_attiva               in varchar2
)
is
    d_statement            varchar2(4000);
begin
  if object_asl_exists = 1 then
    d_statement := 'begin asl_ccs.ASL_INS(:in_regione_asl,:in_codice_asl,:IN_DESCRIZIONE,:IN_UTENTE,:IN_ATTIVA); end;';
      execute immediate d_statement
         using in in_regione_asl,in_codice_asl,in_descrizione,in_utente,in_attiva;
  end if;
end;
procedure ASL_UPD
(in_regione_asl          in number
,in_codice_asl           in number
,in_descrizione          in varchar2
,in_utente               in varchar2
,in_attiva               in varchar2
)
is
    d_statement            varchar2(4000);
begin
  if object_asl_exists = 1 then
    d_statement := 'begin asl_ccs.ASL_upd(:in_regione_asl,:in_codice_asl,:IN_DESCRIZIONE,:IN_UTENTE,:IN_ATTIVA); end;';
      execute immediate d_statement
         using in in_regione_asl,in_codice_asl,in_descrizione,in_utente,in_attiva;
  end if;
end;
procedure ASL_DEL
(in_regione_asl          in number
,in_codice_asl           in number
)
is
    d_statement            varchar2(4000);
begin
  if object_asl_exists = 1 then
    d_statement := 'begin asl_ccs.ASL_del(:in_regione_asl,:in_codice_asl); end;';
      execute immediate d_statement
         using in in_regione_asl,in_codice_asl;
  end if;
end;
--procedure ZONA_ASL_INS
--(in_id_zona_asl          in number
--,in_codice_regione       in number
--,in_codice_zona          in varchar2
--,in_titolo               in varchar2
--,in_val_distretto_lea    in varchar2
--)
--is
--begin
--  if in_id_zona_asl is null then
--    raise_application_error(-20937,raise_prefix||'Zona obbligatoria');
--  elsif regione_exists_id(in_id_zona_asl,0) = 1 then
--    raise_application_error(-20938,raise_prefix||'Zona già presente ('||in_id_zona_asl||')');
--  end if;
--  if in_codice_regione is null then
--    raise_application_error(-20939,raise_prefix||'Codice Regione obbligatorio');
--  end if;
--  if in_codice_zona is null then
--    raise_application_error(-20940,raise_prefix||'Codice Zona obbligatorio');
--  end if;
--  if in_titolo is null then
--    raise_application_error(-20941,raise_prefix||'Titolo obbligatorio');
--  end if;
--  insert into zone_asl
--    (id_zona_asl
--    ,codice_regione
--    ,codice_zona
--    ,titolo
--    ,val_distretto_lea
--    )
--  values
--    (in_id_zona_asl
--    ,in_codice_regione
--    ,upper(in_codice_zona)
--    ,upper(in_titolo)
--    ,upper(in_val_distretto_lea)
--    );
--end;
--procedure ZONA_ASL_UPD
--(in_id_zona_asl          in number
--,in_codice_regione       in number
--,in_codice_zona          in varchar2
--,in_titolo               in varchar2
--,in_val_distretto_lea    in varchar2
--)
--is
--begin
--  if in_codice_regione is null then
--    raise_application_error(-20942,raise_prefix||'Codice Regione obbligatorio');
--  end if;
--  if in_codice_zona is null then
--    raise_application_error(-20943,raise_prefix||'Codice Zona obbligatorio');
--  end if;
--  if in_titolo is null then
--    raise_application_error(-20944,raise_prefix||'Titolo obbligatorio');
--  end if;
--  update zone_asl
--     set titolo            = upper(in_titolo)
--        ,codice_regione    = in_codice_regione
--        ,codice_zona       = upper(in_codice_zona)
--        ,val_distretto_lea = upper(in_val_distretto_lea)
--   where id_zona_asl  = in_id_zona_asl
--  ;
--  if sql%notfound then
--    raise_application_error(-20945,raise_prefix||'Errore aggiornamento Zona('||in_id_zona_asl||')');
--  end if;
--end;
--procedure ZONA_ASL_DEL
--(in_id_zona_asl          in number
--)
--is
--begin
--  delete zone_asl
--   where id_zona_asl = in_id_zona_asl
--  ;
--  if sql%notfound then
--    raise_application_error(-20946,raise_prefix||'Errore eliminazione Zona ('||in_id_zona_asl||')');
--  end if;
--end;
procedure STATO_TERRITORIO_INS
(in_stato_territorio     in number
,in_denominazione        in varchar2
,in_sigla                in varchar2
,in_desc_cittadinanza    in varchar2
,in_raggruppamento       in number
,in_stato_appartenenza   in number
,in_utente               in varchar2
)
is
begin
  if in_stato_territorio is null then
    raise_application_error(-20947,raise_prefix||'Stato territorio obbligatorio');
  elsif stato_territorio_exists_id(in_stato_territorio,0) = 1 then
    raise_application_error(-20948,raise_prefix||'Stato territorio già presente ('||in_stato_territorio||')');
  end if;
  insert into ad4_stati_territori
    (stato_territorio
    ,denominazione
    ,sigla
    ,desc_cittadinanza
    ,raggruppamento
    ,stato_appartenenza
    ,utente_aggiornamento
    )
  values
    (in_stato_territorio
    ,upper(in_denominazione)
    ,upper(in_sigla)
    ,upper(in_desc_cittadinanza)
    ,in_raggruppamento
    ,in_stato_appartenenza
    ,in_utente
    );
end;
procedure STATO_TERRITORIO_UPD
(in_stato_territorio     in number
,in_denominazione        in varchar2
,in_sigla                in varchar2
,in_desc_cittadinanza    in varchar2
,in_raggruppamento       in number
,in_stato_appartenenza   in number
,in_utente               in varchar2
)
is
begin
  update ad4_stati_territori
     set denominazione        = upper(in_denominazione)
        ,sigla                = upper(in_sigla)
        ,desc_cittadinanza    = upper(in_desc_cittadinanza)
        ,raggruppamento       = in_raggruppamento
        ,stato_appartenenza   = in_stato_appartenenza
        ,utente_aggiornamento = in_utente
   where stato_territorio  = in_stato_territorio
  ;
  if sql%notfound then
    raise_application_error(-20949,raise_prefix||'Errore aggiornamento Stato territorio('||in_stato_territorio||')');
  end if;
end;
procedure STATO_TERRITORIO_DEL
(in_stato_territorio     in number
)
is
begin
  delete ad4_stati_territori
   where stato_territorio  = in_stato_territorio
  ;
  if sql%notfound then
    raise_application_error(-20946,raise_prefix||'Errore eliminazione Stato territorio('||in_stato_territorio||')');
  end if;
end;
procedure ASL_COMUNE_INS
(in_COD_COMUNE_ASL       in VARCHAR2
,in_COD_ASL              in VARCHAR2
,in_proposta             in varchar2
,in_attiva               in varchar2
,in_utente               in varchar2
)
is
    d_statement            varchar2(4000);
    IN_PROVINCIA           NUMBER := TO_NUMBER(SUBSTR(in_COD_COMUNE_ASL,1,INSTR(in_COD_COMUNE_ASL,'#')-1));
    IN_COMUNE              NUMBER := TO_NUMBER(SUBSTR(in_COD_COMUNE_ASL,INSTR(in_COD_COMUNE_ASL,'#')+1));
    in_regione_asl         NUMBER := TO_NUMBER(SUBSTR(in_COD_ASL,1,INSTR(in_COD_ASL,'#')-1));
    in_CODICE_asl          NUMBER := TO_NUMBER(SUBSTR(in_COD_ASL,INSTR(in_COD_ASL,'#')+1));
begin
  if object_asl_exists = 1 then
    d_statement := 'begin asl_ccs.ASL_COMUNE_INS(:IN_PROVINCIA,:IN_COMUNE,:in_regione_asl,:in_codice_asl,:IN_proposta,:IN_ATTIVA,:IN_UTENTE); end;';
      execute immediate d_statement
         using in in_provincia,in_comune,in_regione_asl,in_codice_asl,in_proposta,in_attiva,in_utente;
  end if;
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
  d_statement            varchar2(4000);
begin
  if object_asl_exists = 1 then
    d_statement := 'begin asl_ccs.ASL_COMUNE_UPD(:IN_PROVINCIA,:IN_COMUNE,:in_regione_asl,:in_codice_asl,:IN_proposta,:IN_ATTIVA,:IN_UTENTE); end;';
      execute immediate d_statement
         using in in_provincia,in_comune,in_regione_asl,in_codice_asl,in_proposta,in_attiva,in_utente;
  end if;
end;
procedure ASL_COMUNE_DEL
(in_provincia            in number
,in_comune               in number
,in_regione_asl          in number
,in_codice_asl           in number
)
is
d_statement            varchar2(4000);
begin
  if object_asl_exists = 1 then
    d_statement := 'begin asl_ccs.ASL_COMUNE_del(:IN_PROVINCIA,:IN_COMUNE,:in_regione_asl,:in_codice_asl); end;';
      execute immediate d_statement
         using in in_provincia,in_comune,in_regione_asl,in_codice_asl;
  end if;
end;
--procedure ZONA_ASL_COMUNE_INS
--(in_id_zona_asl          in number
--,in_provincia            in number
--,in_comune               in number
--)
--is
--begin
--  if in_provincia is null then
--    raise_application_error(-20963,raise_prefix||'Provincia obbligatoria');
--  elsif in_comune is null then
--    raise_application_error(-20964,raise_prefix||'Comune obbligatorio');
--  elsif in_id_zona_asl is null then
--    raise_application_error(-20965,raise_prefix||'Zona Asl obbligatoria');
--  elsif zona_asl_comune_exists_id(in_id_zona_asl,in_provincia,in_comune,0) = 1 then
--    raise_application_error(-20966,raise_prefix||'Comune Zona Asl già presente '||in_id_zona_asl||'-'||in_provincia||'-'||in_comune||' inesistente');
--  end if;
--  insert into zone_asl_comuni
--    (id_zona_asl
--    ,provincia
--    ,comune
--    )
--  values
--    (in_id_zona_asl
--    ,in_provincia
--    ,in_comune
--    );
--end;
--
--
--procedure ZONA_ASL_COMUNE_DEL
--(in_id_zona_asl          in number
--,in_provincia            in number
--,in_comune               in number
--)
--is
--begin
--  delete zone_asl_comuni
--   where id_zona_asl = in_id_zona_asl
--     and provincia   = in_provincia
--     and comune      = in_comune
--  ;
--  if sql%notfound then
--    raise_application_error(-20967,raise_prefix||'Errore eliminazione Zona Asl Comune('||in_id_zona_asl||'-'||in_provincia||'-'||in_comune||')');
--  end if;
--end;
function UTENTE_RESET_TENTATIVI
(in_utente             in varchar2
) return html
is
  out_html           html;
  pragma autonomous_transaction;
begin
  update ad4_utenti
     set numero_tentativi = 0
        ,pwd_da_modificare = 'SI'
   where utente = in_utente
  ;
  commit;
  return out_html;
end;
function banca_EXISTS_ID
(in_abi              in number
,in_errore               in number default 1
) return number
is
  esito                  number := 0;
begin
  select 1
    into esito
    from ad4_banche
   where abi = in_abi
     and rownum        = 1
  ;
  return esito;
exception when no_data_found then
  if in_errore > 0 then
    if in_abi is null then
      raise_application_error(-20901,raise_prefix||'ABI non specificato');
    else
      raise_application_error(-20902,raise_prefix||'ABI '||in_abi||' inesistente');
    end if;
  else
    return esito;
  end if;
end;
procedure BANCA_INS
(in_ABI               in varchar2
,in_denominazione     in varchar2
,in_CIN_ABI           in varchar2
)
is
begin
  if in_ABI is null then
    raise_application_error(-20903,raise_prefix||'ABI obbligatorio');
  elsif banca_exists_id(in_abi,0) = 1 then
    raise_application_error(-20904,raise_prefix||'ABI già presente ('||in_abi||')');
  end if;
  insert into ad4_banche
    (abi
    ,denominazione
    ,cin_abi
    )
  values
    (in_ABI
    ,upper(in_denominazione)
    ,in_CIN_ABI
    );
end;
procedure BANCA_UPD
(in_ABI              in varchar2
,in_denominazione        in varchar2
,in_CIN_ABI           in varchar2
)
is
begin
  if in_ABI is null then
    raise_application_error(-20906,raise_prefix||'ABI richiesto');
  end if;
  update ad4_BANCHE
     set denominazione        = upper(in_denominazione)
        ,CIN_ABI           = in_CIN_ABI
   where ABI  = in_ABI
  ;
  if sql%notfound then
    raise_application_error(-20907,raise_prefix||'Errore aggiornamento Banca('||in_abi||')');
  end if;
end;
procedure banca_DEL
(in_ABI              in varchar2
)
is
    DUMMY   VARCHAR2(5);
begin
  begin
    select CAB
      into dummy
      from ad4_SPORTELLI
     where ABI = in_ABI;
    raise too_many_rows;
  exception when too_many_rows then
    raise_application_error(-20907,raise_prefix||'Errore eliminazione Banca ('||in_abi||'): esistono Sportelli collegati');
  when no_data_found then
    null;
  end;
  delete ad4_banche
   where abi   = in_abi
  ;
  if sql%notfound then
    raise_application_error(-20962,raise_prefix||'Errore eliminazione Banca('||in_abi||') inesistente');
  end if;
end;
function sportello_EXISTS_ID
(in_abi      in varchar2
,in_cab               in varchar2
,in_errore               in number default 1
) return number
is
  esito                  number := 0;
begin
  select 1
    into esito
    from ad4_sportelli
   where abi = in_abi
     and cab          = in_cab
     and rownum        = 1
  ;
  return esito;
exception when no_data_found then
  if in_errore > 0 then
    if in_abi is null then
      raise_application_error(-209017,raise_prefix||'ABI Banca non specificata');
    elsif in_cab is null then
      raise_application_error(-20918,raise_prefix||'CAB Sportello non specificato');
    else
      raise_application_error(-20919,raise_prefix||'ABI-CAB '||in_abi||'-'||in_cab||' inesistente');
    end if;
  else
    return esito;
  end if;
end;
procedure SPORTELLO_INS
(in_ABI                  in VARCHAR2
,in_CAB                  in VARCHAR2
,in_cin_cab             in VARCHAR2
,in_descrizione             in VARCHAR2
,in_indirizzo             in VARCHAR2
,in_localita             in VARCHAR2
,in_comune             in VARCHAR2
,in_provincia             in VARCHAR2
,in_cap             in VARCHAR2
,in_dipendenza             in VARCHAR2
,in_bic             in VARCHAR2
)
is
begin
  if in_ABI is null then
    raise_application_error(-20927,raise_prefix||'ABI Banca obbligatorio');
  elsif in_cab is null then
    raise_application_error(-20928,raise_prefix||'CAB Sportello obbligatorio');
  elsif sportello_exists_id(in_abi,in_cab,0) = 1 then
    raise_application_error(-20929,raise_prefix||'Sportello già presente ('||in_abi||'-'||in_cab||')');
  end if;
  insert into ad4_sportelli
    (abi
    ,cab
    ,cin_cab
    ,descrizione
    ,indirizzo
    ,localita
    ,comune
    ,provincia
    ,cap
    ,dipendenza
    ,bic
    )
  values
    (in_abi
    ,in_cab
    ,in_cin_cab
    ,in_descrizione
    ,in_indirizzo
    ,in_localita
    ,in_comune
    ,in_provincia
    ,in_cap
    ,in_dipendenza
    ,in_bic
    );
end;
procedure SPORTELLO_UPD
(in_ABI                  in VARCHAR2
,in_CAB                  in VARCHAR2
,in_cin_cab             in VARCHAR2
,in_descrizione             in VARCHAR2
,in_indirizzo             in VARCHAR2
,in_localita             in VARCHAR2
,in_comune             in VARCHAR2
,in_provincia             in VARCHAR2
,in_cap             in VARCHAR2
,in_dipendenza             in VARCHAR2
,in_bic             in VARCHAR2
)
is
begin
  update ad4_sportelli
     set descrizione             = upper(in_descrizione)
        ,cin_cab      = in_cin_cab
        ,indirizzo      = in_indirizzo
        ,cap                      = in_cap
        ,localita      = in_localita
        ,comune         = in_comune
        ,provincia        = in_provincia
        ,dipendenza        = in_dipendenza
        ,bic           = in_bic
   where ABI  = in_ABI
     and CAB           = in_CAB
  ;
  if sql%notfound then
    raise_application_error(-20930,raise_prefix||'Errore aggiornamento Sportello ('||in_abi||'-'||in_cab||')');
  end if;
end;
procedure sportello_DEL
(in_abi      in varchar2
,in_cab              in varchar2
)
is
begin
  delete ad4_sportelli
   where abi  = in_abi
     and cab           = in_cab
  ;
  if sql%notfound then
    raise_application_error(-20931,raise_prefix||'Errore eliminazione Sportello ('||in_abi||'-'||in_cab||')');
  end if;
end;


FUNCTION GET_TRADUZIONE
(IN_TABELLA   IN VARCHAR2
,IN_COLONNA  IN VARCHAR2
,IN_KEY           IN VARCHAR2
,IN_LINGUA     IN VARCHAR2
) RETURN VARCHAR2 IS
RET_VALUE   VARCHAR2(4000) := NULL;
BEGIN
    BEGIN
        SELECT TESTO
           INTO RET_VALUE
          FROM KEY_DICTIONARY K
        WHERE TABELLA = IN_TABELLA
            AND COLONNA = IN_COLONNA
            AND PK = IN_KEY
            AND LINGUA = IN_LINGUA;
    EXCEPTION WHEN NO_DATA_FOUND THEN
        RET_VALUE := NULL;
    END;
    RETURN RET_VALUE;
END;

procedure traduzione_ins
(in_TABELLA IN VARCHAR2
,IN_COLONNA IN VARCHAR2
,IN_PK IN VARCHAR2
,IN_LINGUA IN VARCHAR2
,IN_TESTO IN VARCHAR2
) is
    DUMMY   NUMBER(1);
begin
   BEGIN
    SELECT 1
        INTO DUMMY
        FROM KEY_DICTIONARY
        WHERE TABELLA = IN_TABELLA
            AND COLONNA = IN_COLONNA
            AND PK = IN_PK
            AND LINGUA = IN_LINGUA;
    UPDATE KEY_DICTIONARY SET TESTO = upper(IN_TESTO) WHERE TABELLA = in_TABELLA AND COLONNA =IN_COLONNA AND PK = IN_PK AND LINGUA =IN_LINGUA;
   EXCEPTION WHEN NO_DATA_FOUND THEN -- NON ESISTE IL RECORD
    INSERT INTO KEY_DICTIONARY (TABELLA,COLONNA,PK,LINGUA,TESTO) VALUES (in_TABELLA,IN_COLONNA,IN_PK,IN_LINGUA,upper(IN_TESTO));
   END;
end;

procedure comune_breve_traduzione_ins
(in_provincia_stato in number
,in_comune in number
,in_lingua in varchar2
,in_denominazione_alt varchar2
) is
begin
   traduzione_ins('COMUNI','DENOMINAZIONE_BREVE',TO_CHAR(IN_PROVINCIA_STATO)||'|'||TO_CHAR(IN_COMUNE),in_lingua,IN_DENOMINAZIONE_ALT);
end;


procedure comune_traduzione_ins
(in_provincia_stato in number
,in_comune in number
,in_lingua in varchar2
,in_denominazione_alt varchar2
) is
begin
   traduzione_ins('COMUNI','DENOMINAZIONE',TO_CHAR(IN_PROVINCIA_STATO)||'|'||TO_CHAR(IN_COMUNE),in_lingua,IN_DENOMINAZIONE_ALT);
end;

procedure BANCA_traduzione_ins
(in_ABI in VARCHAR2
,in_lingua in varchar2
,in_denominazione_alt varchar2
) is
begin
   traduzione_ins('BANCHE','DENOMINAZIONE',IN_ABI,in_lingua,IN_DENOMINAZIONE_ALT);
end;

procedure SPORTELLO_traduzione_ins
(in_ABI in VARCHAR2
,in_CAB in VARCHAR2
,in_lingua in varchar2
,in_denominazione_alt varchar2
) is
begin
   traduzione_ins('SPORTELLI','DESCRIZIONE',IN_ABI||'|'||IN_CAB,in_lingua,IN_DENOMINAZIONE_ALT);
end;



procedure traduzione_del
(in_tabella in varchar2
,in_colonna in varchar2
,in_key in varchar2
,in_lingua in varchar2)
is
begin
    delete key_dictionary WHERE TABELLA =in_tabella AND COLONNA =in_colonna AND PK = in_key AND LINGUA =in_lingua;
end;

procedure comune_traduzione_del
(in_provincia_stato in number
,in_comune in number
,in_lingua in varchar2
) is
begin
    traduzione_del( 'COMUNI', 'DENOMINAZIONE' , TO_CHAR(IN_PROVINCIA_STATO)||'|'||TO_CHAR(IN_COMUNE),in_lingua);
end;

procedure comune_breve_traduzione_del
(in_provincia_stato in number
,in_comune in number
,in_lingua in varchar2
) is
begin
    traduzione_del( 'COMUNI', 'DENOMINAZIONE_BREVE' , TO_CHAR(IN_PROVINCIA_STATO)||'|'||TO_CHAR(IN_COMUNE),in_lingua);
end;

procedure banca_traduzione_del
(in_abi in varchar2
,in_lingua in varchar2
) is
begin
    traduzione_del( 'BANCHE', 'DENOMINAZIONE' , IN_ABI,in_lingua);
end;

procedure SPORTELLO_traduzione_del
(in_abi in varchar2
,IN_CAB IN VARCHAR2
,in_lingua in varchar2
) is
begin
    traduzione_del( 'SPORTELLI', 'DESCRIZIONE' , IN_ABI||'|'||IN_CAB, in_lingua);
end;

function get_link_traduzione_comune
(in_provincia_stato in number
,in_comune in number
) RETURN VARCHAR2
 is
    RET_VALUE   VARCHAR2(32000) := NULL;
begin
    FOR SEL_LINGUE IN (
        select replace(object_name,'COMUNI_','') LINGUA
        from user_objects
        where object_name like 'COMUNI\_%' escape '\'
         and replace(object_name,'COMUNI_','') in (select lingua from lingue)
          and object_type = 'VIEW'
  )  loop
        RET_VALUE := RET_VALUE ||'<a class="AFCDataLink" href="#" onclick="window.open(''Ad4DizionariComuneTraduzione.do?PROVINCIA_STATO='||in_provincia_stato||'&COMUNE='||in_comune||'&LINGUA='||sel_lingue.lingua||''',''translateWin'','||ad4_ccs.get_win_feature||')" title="Imposta traduzione"><img height="18" src="../images/Lingua'||SEL_LINGUE.LINGUA||'.png" width="18" border="0"></a>';
    END LOOP;
    return RET_VALUE;
end;

function get_link_traduzione_comune_br
(in_provincia_stato in number
,in_comune in number
) RETURN VARCHAR2
 is
    RET_VALUE   VARCHAR2(32000) := NULL;
begin
    FOR SEL_LINGUE IN (
        select replace(object_name,'COMUNI_','') LINGUA
        from user_objects
        where object_name like 'COMUNI\_%' escape '\'
         and replace(object_name,'COMUNI_','') in (select lingua from lingue)
          and object_type = 'VIEW'
  )  loop
        RET_VALUE := RET_VALUE ||'<a class="AFCDataLink" href="#" onclick="window.open(''Ad4DizionariComuneBreveTraduzione.do?PROVINCIA_STATO='||in_provincia_stato||'&COMUNE='||in_comune||'&LINGUA='||sel_lingue.lingua||''',''translateWin'','||ad4_ccs.get_win_feature||')" title="Imposta traduzione"><img height="18" src="../images/Lingua'||SEL_LINGUE.LINGUA||'.png" width="18" border="0"></a>';
    END LOOP;
    return RET_VALUE;
end;

function get_link_traduzione_banca
(in_abi in varchar2
) RETURN VARCHAR2
 is
    RET_VALUE   VARCHAR2(32000) := NULL;
begin
    FOR SEL_LINGUE IN (
        select replace(object_name,'BANCHE_','') LINGUA
        from user_objects
        where object_name like 'BANCHE\_%' escape '\'
        and replace(object_name,'BANCHE_','') in (select lingua from lingue)
          and object_type = 'VIEW'
  )  loop
        RET_VALUE := RET_VALUE ||'<a class="AFCDataLink" href="#" onclick="window.open(''Ad4DizionariBancaTraduzione.do?ABI='||in_abi||'&LINGUA='||sel_lingue.lingua||''',''translateWin'','||ad4_ccs.get_win_feature||')" title="Imposta traduzione"><img height="18" src="../images/Lingua'||SEL_LINGUE.LINGUA||'.png" width="18" border="0"></a>';
    END LOOP;
    return RET_VALUE;
end;

function get_link_traduzione_sportello
(in_abi in varchar2
,in_cab in varchar2
) RETURN VARCHAR2
 is
    RET_VALUE   VARCHAR2(32000) := NULL;
begin
    FOR SEL_LINGUE IN (
        select replace(object_name,'SPORTELLI_','') LINGUA
        from user_objects
        where object_name like 'SPORTELLI\_%' escape '\'
        and replace(object_name,'SPORTELLI_','') in (select lingua from lingue)
          and object_type = 'VIEW'
  )  loop
            RET_VALUE := RET_VALUE ||'<a class="AFCDataLink" href="#" onclick="window.open(''Ad4DizionariSportelloTraduzione.do?ABI='||in_abi||'&CAB='||in_cab||'&LINGUA='||sel_lingue.lingua||''',''translateWin'','||ad4_ccs.get_win_feature||')" title="Imposta traduzione"><img height="18" src="../images/Lingua'||SEL_LINGUE.LINGUA||'.png" width="18" border="0"></a>';
    END LOOP;
    return RET_VALUE;
end;

end;
/