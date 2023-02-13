--liquibase formatted sql

--changeset mturra:201901301231_99 runOnChange:true endDelimiter:/ stripComments:false


create or replace function utente_is_member(p_gruppo varchar2,p_utente varchar2)
return number
IS
v_ritorno number := 0;
begin
begin
SELECT    nvl(max(1),0)
   into   v_ritorno
         FROM utenti_gruppo grup
        WHERE utente != 'ric_abil'
          and utente = p_utente
   START WITH  gruppo = p_gruppo
   CONNECT BY PRIOR utente = gruppo;
exception
when no_data_found then
  v_ritorno := 0;
end;
return v_ritorno;
end;
/

