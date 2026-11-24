--liquibase formatted sql

--changeset mmonari:popola_campo_eventi_sesssionid

update eventi
   set session_id = to_number(afc.get_stringparm(testo, 'session_id'))
 where session_id is null
   and to_number(afc.get_stringparm(testo, 'session_id')) is not null;