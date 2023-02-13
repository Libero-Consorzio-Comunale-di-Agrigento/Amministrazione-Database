--liquibase formatted sql

--changeset mturra:201901301231_447 runOnChange:true stripComments:false


create or replace force view utenti_gruppi as
select grup.utente gruppo
     , grup.nominativo gruppo_denominazione
     , uten.utente utente
     , uten.nominativo utente_nominativo
from utenti uten
   , utenti grup
where utente_is_member(grup.utente,uten.utente) = 1
  and uten.tipo_utente = 'U'
  and uten.stato = 'U'
  and grup.tipo_utente != 'U'
  and grup.stato = 'U';

