--liquibase formatted sql

--changeset snegroni:20190612_1636 runOnChange:true endDelimiter:/ stripComments:false

CREATE OR REPLACE procedure schedula_rigenera_so(p_utente in utenti.utente%type)
is
begin
    utente.rigenera_so(p_utente);
end;
/
