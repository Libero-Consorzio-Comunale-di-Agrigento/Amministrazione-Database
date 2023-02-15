CREATE OR REPLACE PACKAGE BODY gv_$session_MASTER_pkg is
/******************************************************************************
 NOME:        sys.GV_$SESSION_tpk
 DESCRIZIONE: Gestione tabella sys.GV_$SESSION.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   06/04/2009  MMalferrari  Prima emissione.
 001   03/12/2010  Sneg        Cambiata exists_id per controllo sessioni master
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '002';
--------------------------------------------------------------------------------
function versione
return varchar2 is
/******************************************************************************
 NOME:        versione
 DESCRIZIONE: Versione e revisione di distribuzione del package.
 RITORNA:     varchar2 stringa contenente versione e revisione.
 NOTE:        Primo numero  : versione compatibilita del Package.
              Secondo numero: revisione del Package specification.
              Terzo numero  : revisione del Package body.
******************************************************************************/
begin
   return AFC.version ( s_revisione, s_revisione_body );
end versione; -- sys.GV_$SESSION_tpk.versione
function get_campo
( p_audsid number
, p_campo in varchar2
) return varchar2
is
   d_result       varchar2(100):=0;
   d_db_link      varchar2(200);
   d_link_oracle  varchar2(2000);
BEGIN
   open master_utility.c_slaves('AD4');
   fetch master_utility.c_slaves into d_db_link,d_link_oracle;
   while master_utility.c_slaves%found loop
      begin
       EXECUTE IMMEDIATE    'begin :d_result := gv_$session_pkg.'||p_campo||'@'
                         || d_db_link
                         || '('||p_audsid||');end;' USING OUT d_result;
      if nvl(d_result, '0') <> '0' then
         exit;
      end if;
      exception
         when others then
             d_result := null;
      end;
      fetch master_utility.c_slaves into d_db_link,d_link_oracle;
   end loop;
   close master_utility.c_slaves;
   return d_result;
end;
function exists_id
(
 p_audsid  in number
) return number is
/******************************************************************************
 NOME:        exists_id
 DESCRIZIONE: Esistenza riga con chiave indicata.
 PARAMETRI:   Attributi chiave.
 RITORNA:     number: 1 se la riga esiste, 0 altrimenti.
 NOTE:        cfr. existsId per ritorno valori boolean.
  REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 001   06/06/2011  Sneg        Messa nvl per ritorno coerente con quanto dichiarato
******************************************************************************/
   d_result number;
begin
   d_result := gv_$session_pkg.exists_id(p_audsid);
   if d_result = 0 then
     d_result := nvl(get_campo(p_audsid, 'exists_id'),0);
   end if;
   return  d_result;
end exists_id; -- sys.GV_$SESSION_tpk.exists_id
--------------------------------------------------------------------------------
function get_sid
(
  p_audsid  in number
) return number is
/******************************************************************************
 NOME:        get_sid
 DESCRIZIONE: Getter per attributo serial# di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.serial#%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result number;
begin
   d_result := gv_$session_pkg.GET_sid(p_audsid);
   if d_result is null then
     d_result := get_campo(p_audsid, 'get_sid');
   end if;
   return  d_result;
end get_sid; -- sys.GV_$SESSION_tpk.get_sid
--------------------------------------------------------------------------------
function get_serial#
(
  p_audsid  in number
) return number is
/******************************************************************************
 NOME:        get_serial#
 DESCRIZIONE: Getter per attributo serial# di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.serial#%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result number;
begin
   d_result := gv_$session_pkg.GET_SERIAL#(p_audsid);
   if d_result is null then
     d_result := get_campo(p_audsid, 'get_serial#');
   end if;
   return  d_result;
end get_serial#; -- sys.GV_$SESSION_tpk.get_serial#
--------------------------------------------------------------------------------
function get_osuser
(
  p_audsid  in number
) return varchar2 is
/******************************************************************************
 NOME:        get_osuser
 DESCRIZIONE: Getter per attributo osuser di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.osuser%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result varchar2(100);
begin
   d_result := gv_$session_pkg.GET_osuser(p_audsid);
   if d_result is null then
     d_result := get_campo(p_audsid, 'get_osuser');
   end if;
   return  d_result;
end get_osuser; -- sys.GV_$SESSION_tpk.get_osuser
--------------------------------------------------------------------------------
function get_machine
(
  p_audsid  in number
) return varchar2 is
/******************************************************************************
 NOME:        get_machine
 DESCRIZIONE: Getter per attributo machine di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.machine%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result varchar2(100);
begin
   d_result := gv_$session_pkg.GET_machine(p_audsid);
   if d_result is null then
     d_result := get_campo(p_audsid, 'get_machine');
   end if;
   return  d_result;
end get_machine; -- sys.GV_$SESSION_tpk.get_machine
--------------------------------------------------------------------------------
function get_terminal
(
  p_audsid  in number
) return varchar2 is
/******************************************************************************
 NOME:        get_terminal
 DESCRIZIONE: Getter per attributo terminal di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.terminal%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result varchar2(100);
begin
   d_result := gv_$session_pkg.GET_terminal(p_audsid);
   if d_result is null then
     d_result := get_campo(p_audsid, 'get_terminal');
   end if;
   return  d_result;
end get_terminal; -- sys.GV_$SESSION_tpk.get_terminal
--------------------------------------------------------------------------------
function get_status
(
  p_audsid  in number
) return varchar2 is
/******************************************************************************
 NOME:        get_status
 DESCRIZIONE: Getter per attributo status di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     sys.GV_$SESSION.terminal%type.
 NOTE:        La riga identificata deve essere presente.
******************************************************************************/
   d_result varchar2(100);
begin
   d_result := gv_$session_pkg.GET_status(p_audsid);
   if d_result is null then
     d_result := get_campo(p_audsid, 'get_status');
   end if;
   return  d_result;
end get_status; -- sys.GV_$SESSION_tpk.get_status
--------------------------------------------------------------------------------
function get_info
(
  p_audsid in number
) return varchar2 is
   d_result varchar2(1000);
begin
   d_result := gv_$session_pkg.GET_info(p_audsid);
   if d_result is null then
     d_result := get_campo(p_audsid, 'get_info');
   end if;
   return  d_result;
end;
end GV_$SESSION_MASTER_pkg;
/

