CREATE OR REPLACE PACKAGE AD4_CCS is
/******************************************************************************
 NOME:        AD4_CCS
 DESCRIZIONE: Package di servizio per pagine CCS
 ANNOTAZIONI: -
 ECCEZIONI:-
 REVISIONI:
 Rev. Data         Autore Descrizione
 ---- -------------- -------- ------------------------------------------------------
 0    xx/xx/2019 AD      Prima emissione.
 1    10/02/2020  SNeg  Gestione nuova colonna data_istituzione
******************************************************************************/
subtype html  is varchar2(32000);
subtype html2 is clob;

FUNCTION  VERSIONE /* SLAVE_COPY */
RETURN VARCHAR2;

function get_win_feature
return varchar2;
function FIELD_FOCUS
(in_form_name                 in varchar2
,in_field_name                in varchar2
)
return html;
function LINK_MULTI_LOV
(in_page                    in varchar2
,in_features                in varchar2
,in_title                   in varchar2
,in_form                    in varchar2
,in_fields                  in varchar2
) return html;
function FORMAT_DATA_TRUNC
(in_data              in date
) return varchar2;
function TO_DATA
(in_data              in varchar2
) return date;
function INIZIALIZZA
return varchar2;
function TO_REPORT_LINK
(in_report_link        in varchar2,
 in_report_icon        in number,
 in_report_title       in varchar2,
 in_report_name        in varchar2,
 in_report_parameter   in varchar2
) return html;
function GET_HEADER
(in_risorsa_id         in varchar2
) return html;
function GET_OPERATORE
(in_utente             in varchar2
,in_data               in date
) return html;
function TAB_FOLDER
(in_link               in varchar2,
 in_href               in varchar2,
 in_active             in varchar2 default 'N'
) return html;
function object_asl_exists return number;
function REGIONE_EXISTS_ID
(in_regione              in number
,in_errore               in number default 1
) return number;
function FILTER_SEARCH
(in_filter_value         in varchar2
) return html;
function PROVINCIA_EXISTS_ID
(in_provincia            in number
,in_errore               in number default 1
) return number;
function COMUNE_EXISTS_ID
(in_provincia_stato      in number
,in_comune               in number
,in_errore               in number default 1
) return number;
function ASL_EXISTS_ID
(in_regione_asl          in number
,in_codice_asl           in number
,in_errore               in number default 1
) return number;
function ZONA_ASL_EXISTS_ID
(in_id_zona_asl          in number
,in_errore               in number default 1
) return number;
function STATO_TERRITORIO_EXISTS_ID
(in_stato_territorio     in number
,in_errore               in number default 1
) return number;
function ASL_COMUNE_EXISTS_ID
(in_provincia            in number
,in_comune               in number
,in_regione_asl          in number
,in_codice_asl           in number
,in_errore               in number default 1
) return number;
function ZONA_ASL_COMUNE_EXISTS_ID
(in_id_zona_asl          in number
,in_provincia            in number
,in_comune               in number
,in_errore               in number default 1
) return number;
function GET_REGIONE_DENOMINAZIONE
(in_regione              in number
) return varchar2;
function GET_PROVINCIA_DENOMINAZIONE
(in_provincia            in number
) return varchar2;
function GET_PROVINCIA_ID_FROM_DENOMIN
(in_denominazione        in varchar2
) return number;
function GET_PROVINCIA_ID_FROM_SIGLA
(in_sigla                in varchar2
) return number;
function GET_COMUNE_DENOMINAZIONE
(in_provincia_stato      in number
,in_comune               in number
) return varchar2;
function GET_ASL_DESCRIZIONE
(in_regione_asl          in number
,in_codice_asl           in number
) return varchar2;
--function GET_ZONA_ASL_TITOLO
--(in_id_zona_asl          in number
--) return varchar2;
procedure REGIONE_INS
(in_regione              in number
,in_denominazione        in varchar2
,in_id_regione           in number
,in_utente               in varchar2
);
procedure REGIONE_UPD
(in_regione              in number
,in_denominazione        in varchar2
,in_id_regione           in number
,in_utente in varchar2
);
procedure REGIONE_DEL
(in_regione              in number
);
procedure PROVINCIA_INS
(in_provincia            in number
,in_denominazione        in varchar2
,in_regione              in number
,in_sigla                in varchar2
,in_utente               in varchar2
);
procedure PROVINCIA_UPD
(in_provincia            in number
,in_denominazione        in varchar2
,in_regione              in number
,in_sigla                in varchar2
,in_utente               in varchar2
);
procedure PROVINCIA_DEL
(in_provincia            in number
);
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
);
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
);
procedure COMUNE_DEL
(in_provincia_stato      in number
,in_comune               in number
);
procedure ASL_INS
(in_regione_asl          in number
,in_codice_asl           in number
,in_descrizione          in varchar2
,in_utente               in varchar2
,in_attiva               in varchar2
);
procedure ASL_UPD
(in_regione_asl          in number
,in_codice_asl           in number
,in_descrizione          in varchar2
,in_utente               in varchar2
,in_attiva               in varchar2
);
procedure ASL_DEL
(in_regione_asl          in number
,in_codice_asl           in number
);
--procedure ZONA_ASL_INS
--(in_id_zona_asl          in number
--,in_codice_regione       in number
--,in_codice_zona          in varchar2
--,in_titolo               in varchar2
--,in_val_distretto_lea    in varchar2
--);
--
--procedure ZONA_ASL_UPD
--(in_id_zona_asl          in number
--,in_codice_regione       in number
--,in_codice_zona          in varchar2
--,in_titolo               in varchar2
--,in_val_distretto_lea    in varchar2
--);
--
--procedure ZONA_ASL_DEL
--(in_id_zona_asl          in number
--);
procedure STATO_TERRITORIO_INS
(in_stato_territorio     in number
,in_denominazione        in varchar2
,in_sigla                in varchar2
,in_desc_cittadinanza    in varchar2
,in_raggruppamento       in number
,in_stato_appartenenza   in number
,in_utente               in varchar2
);
procedure STATO_TERRITORIO_UPD
(in_stato_territorio     in number
,in_denominazione        in varchar2
,in_sigla                in varchar2
,in_desc_cittadinanza    in varchar2
,in_raggruppamento       in number
,in_stato_appartenenza   in number
,in_utente               in varchar2
);
procedure STATO_TERRITORIO_DEL
(in_stato_territorio     in number
);
procedure ASL_COMUNE_INS
(in_COD_COMUNE_ASL       in VARCHAR2
,in_COD_ASL              in VARCHAR2
,in_proposta             in varchar2
,in_attiva               in varchar2
,in_utente               in varchar2
);
procedure ASL_COMUNE_UPD
(in_provincia            in number
,in_comune               in number
,in_regione_asl          in number
,in_codice_asl           in number
,in_proposta             in varchar2
,in_attiva               in varchar2
,in_utente               in varchar2
);
procedure ASL_COMUNE_DEL
(in_provincia            in number
,in_comune               in number
,in_regione_asl          in number
,in_codice_asl           in number
);
--procedure ZONA_ASL_COMUNE_INS
--(in_id_zona_asl          in number
--,in_provincia            in number
--,in_comune               in number
--);
--
--procedure ZONA_ASL_COMUNE_DEL
--(in_id_zona_asl          in number
--,in_provincia            in number
--,in_comune               in number
--);
function GET_BANCA_DENOMINAZIONE
(in_ABI             in varchar2
) return varchar2;
function UTENTE_RESET_TENTATIVI
(in_utente             in varchar2
) return html;
procedure BANCA_INS
(in_ABI               in varchar2
,in_denominazione     in varchar2
,in_CIN_ABI           in varchar2
);
procedure BANCA_UPD
(in_ABI              in varchar2
,in_denominazione        in varchar2
,in_CIN_ABI           in varchar2
);
procedure banca_DEL
(in_ABI              in varchar2
);
function banca_EXISTS_ID
(in_abi              in number
,in_errore               in number default 1
) return number;
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
);
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
);
procedure sportello_DEL
(in_abi      in varchar2
,in_cab              in varchar2
);

FUNCTION GET_TRADUZIONE
(IN_TABELLA   IN VARCHAR2
,IN_COLONNA  IN VARCHAR2
,IN_KEY           IN VARCHAR2
,IN_LINGUA     IN VARCHAR2
) RETURN VARCHAR2;

procedure comune_traduzione_ins
(in_provincia_stato in number
,in_comune in number
,in_lingua in varchar2
,in_denominazione_alt varchar2
);

procedure BANCA_traduzione_ins
(in_ABI in VARCHAR2
,in_lingua in varchar2
,in_denominazione_alt varchar2
) ;

procedure SPORTELLO_traduzione_ins
(in_ABI in VARCHAR2
,in_CAB in VARCHAR2
,in_lingua in varchar2
,in_denominazione_alt varchar2
) ;

procedure comune_traduzione_del
(in_provincia_stato in number
,in_comune in number
,in_lingua in varchar2
);

procedure banca_traduzione_del
(in_abi in varchar2
,in_lingua in varchar2
) ;

procedure SPORTELLO_traduzione_del
(in_abi in varchar2
,IN_CAB IN VARCHAR2
,in_lingua in varchar2
);

function get_link_traduzione_comune
(in_provincia_stato in number
,in_comune in number
) RETURN VARCHAR2;

function get_link_traduzione_banca
(in_abi in varchar2
) RETURN VARCHAR2;

function get_link_traduzione_sportello
(in_abi in varchar2
,in_cab in varchar2
) RETURN VARCHAR2;

procedure comune_breve_traduzione_ins
(in_provincia_stato in number
,in_comune in number
,in_lingua in varchar2
,in_denominazione_alt varchar2
);

procedure comune_breve_traduzione_del
(in_provincia_stato in number
,in_comune in number
,in_lingua in varchar2
);

function get_link_traduzione_comune_br
(in_provincia_stato in number
,in_comune in number
) RETURN VARCHAR2;
end;
/

