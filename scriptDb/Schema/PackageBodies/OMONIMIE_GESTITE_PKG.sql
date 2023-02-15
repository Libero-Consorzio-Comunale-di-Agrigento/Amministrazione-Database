CREATE OR REPLACE package body omonimie_gestite_pkg is
/******************************************************************************
 NOME:        omonimie_gestite_pkg
 DESCRIZIONE: Gestione tabella OMONIMIE_GESTITE.
 ANNOTAZIONI: .
 REVISIONI:   .
 Rev.  Data        Autore  Descrizione.
 000   19/05/2011  snegroni  Prima emissione.
******************************************************************************/
   s_revisione_body      constant AFC.t_revision := '000';
--------------------------------------------------------------------------------
function versione
return varchar2 is /* SLAVE_COPY */
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
end versione; -- omonimie_gestite_tpk.versione
--------------------------------------------------------------------------------
procedure ins_upd
( p_id_omonimia  in OMONIMIE_GESTITE.id_omonimia%type default null
, p_primario  in OMONIMIE_GESTITE.primario%type default null
, p_secondario  in OMONIMIE_GESTITE.secondario%type default null
, p_scelto_primario  in OMONIMIE_GESTITE.scelto_primario%type default null
, p_unificato  in OMONIMIE_GESTITE.unificato%type default 0
, p_copiato  in OMONIMIE_GESTITE.copiato%type default 0
, p_ignorare  in OMONIMIE_GESTITE.ignorare%type default 0
, p_note  in OMONIMIE_GESTITE.note%type default null
, p_nominativo_primario  in OMONIMIE_GESTITE.nominativo_primario%type default null
, p_nominativo_secondario  in OMONIMIE_GESTITE.nominativo_secondario%type default null
, p_utente_agg  in OMONIMIE_GESTITE.utente_agg%type default null
) is
/******************************************************************************
 NOME:        ins_upd
 DESCRIZIONE: Inserimento di una riga con chiave e attributi indicati se non esiste altrimenti modifica record
 PARAMETRI:   Chiavi e attributi della table.
******************************************************************************/
v_primario OMONIMIE_GESTITE.primario%type;
v_secondario OMONIMIE_GESTITE.secondario%type;
v_id_omonimia OMONIMIE_GESTITE.id_omonimia%type;
begin
   if p_scelto_primario = 1 then
      v_primario := p_secondario;
      v_secondario := p_primario;
   end if;
   v_id_omonimia := nvl(p_id_omonimia,get_id (p_primario, p_secondario) );
   -- non uso solo il parametro cosi va bene anche se chiamata da utente copia o unifica profilo
   -- Check Mandatory on Insert
   if v_id_omonimia  is not null then
     OMONIMIE_GESTITE_TPK.UPD ( P_CHECK_OLD => 0
                                                 , p_NEW_id_omonimia => v_id_omonimia
                                                 , P_NEW_PRIMARIO => v_primario
                                                 , P_NEW_SECONDARIO => v_secondario
                                                 , P_NEW_SCELTO_PRIMARIO => p_scelto_primario
                                                 , P_NEW_UNIFICATO => p_unificato
                                                 , P_NEW_COPIATO => p_copiato
                                                 , P_NEW_IGNORARE => p_ignorare
                                                 , P_NEW_NOTE => p_note
                                                 , p_NEW_nominativo_primario => p_nominativo_primario
                                                 , p_NEW_nominativo_secondario => p_nominativo_secondario
                                                 , P_NEW_UTENTE_AGG => p_utente_agg
                                                 , P_NEW_DATA_AGG => sysdate );
   else
        OMONIMIE_GESTITE_TPK.INS ( P_ID_OMONIMIA
                                                    , V_PRIMARIO
                                                    , V_SECONDARIO
                                                    , P_SCELTO_PRIMARIO
                                                    , P_UNIFICATO
                                                    , P_COPIATO
                                                    , P_IGNORARE
                                                    , P_NOTE
                                                    , P_NOMINATIVO_PRIMARIO
                                                    , P_NOMINATIVO_SECONDARIO
                                                    , P_UTENTE_AGG
                                                    , P_DATA_AGG => sysdate );
   end if;
end ins_upd; -- omonimie_gestite_tpk.ins
--------------------------------------------------------------------------------
  function get_id
   ( p_primario  in OMONIMIE_GESTITE.primario%type
   , p_secondario  in OMONIMIE_GESTITE.secondario%type
   ) return omonimie_gestite.id_omonimia%TYPE
   IS
      v_id_omonimia omonimie_gestite.id_omonimia%TYPE;
   BEGIN
      begin
        select id_omonimia
           into v_id_omonimia
          from omonimie_gestite
        where primario = p_primario
            and secondario = p_secondario;
           exception
           when no_data_found then
              v_id_omonimia := null;
        end;
        return v_id_omonimia;
   END;
PROCEDURE ins_upd_column (
   p_primario     IN OMONIMIE_GESTITE.primario%TYPE,
   p_secondario   IN OMONIMIE_GESTITE.secondario%TYPE,
   p_column       IN VARCHAR2,
   p_value        IN VARCHAR2 DEFAULT NULL,
   p_utente_agg   IN OMONIMIE_GESTITE.utente_agg%TYPE DEFAULT NULL)
IS
   v_id_omonimia   omonimie_gestite.id_omonimia%TYPE;
   v_unificato     omonimie_gestite.unificato%TYPE := 0;
   v_copiato       omonimie_gestite.copiato%TYPE := 0;
BEGIN
   v_id_omonimia := get_id (p_primario, p_secondario);
   IF p_column = 'UNIFICATO'
   THEN
      v_unificato := p_value;
   ELSIF p_column = 'COPIATO'
   THEN
      v_copiato := p_value;
   END IF;
   IF v_id_omonimia IS NULL
   THEN
      OMONIMIE_GESTITE_TPK.INS (P_ID_OMONIMIA   => NULL,
                                P_PRIMARIO      => p_primario,
                                P_SECONDARIO    => p_secondario,
                                P_UNIFICATO     => v_unificato,
                                P_COPIATO       => v_copiato,
                                P_UTENTE_AGG    => p_utente_agg);
   ELSE              -- esiste record per la omonimia aggiorno solo la colonna
      OMONIMIE_GESTITE_TPK.upd_column (p_id_omonimia     => v_id_omonimia,
                                       p_column          => p_column,
                                       p_value           => p_value,
                                       p_literal_value   => 1);
   END IF;
END;
END OMONIMIE_GESTITE_PKG;
/

