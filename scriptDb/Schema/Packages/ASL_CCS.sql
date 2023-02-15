CREATE OR REPLACE PACKAGE ASL_CCS is
function ASL_EXISTS_ID
(in_regione_asl          in number
,in_codice_asl           in number
,in_errore               in number default 1
) return number;
function ASL_COMUNE_EXISTS_ID
(in_provincia            in number
,in_comune               in number
,in_regione_asl          in number
,in_codice_asl           in number
,in_errore               in number default 1
) return number;
function GET_ASL_DESCRIZIONE
(in_regione_asl          in number
,in_codice_asl           in number
) return varchar2;
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
procedure ASL_COMUNE_INS
(in_provincia            in number
,in_comune               in number
,in_regione_asl          in number
,in_codice_asl           in number
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
end;
/

