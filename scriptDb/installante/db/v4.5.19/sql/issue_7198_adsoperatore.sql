--liquibase formatted sql

--changeset mmonari:crea_utente_adsoperatore

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select nvl(max(1),0) from utenti where nominativo='ADSOPERATORE';

begin
   insert into utenti (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
   values ('ADSOPERA', '', 'ADSOPERATORE', 'ROjyTl6', to_date('20-03-2024', 'dd-mm-yyyy'), 'SI', 'SI', null, 0, 'U', 'U', 'I', 'def', null, null, to_date('20-03-2024 11:34:41', 'dd-mm-yyyy hh24:mi:ss'), 'AD4', to_date('20-03-2024 11:36:06', 'dd-mm-yyyy hh24:mi:ss'), 'N', null);

   insert into diritti_accesso (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
   values ('ADSOPERA', 'AD4', 'AD4', 'AMM', 1, null, 0, null, null);

   insert into diritti_accesso (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
   values ('ADSOPERA', 'AD4WEB', 'AD4', 'AMM', null, null, null, null, null);
end;
/
