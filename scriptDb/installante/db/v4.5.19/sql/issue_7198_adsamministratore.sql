--liquibase formatted sql

--changeset mmonari:crea_utente_adsamministratore

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select nvl(max(1),0) from utenti where nominativo='ADSAMMINISTRATORE';

begin
  insert into utenti (UTENTE, ID_UTENTE, NOMINATIVO, PASSWORD, DATA_PASSWORD, RINNOVO_PASSWORD, PWD_DA_MODIFICARE, ULTIMO_TENTATIVO, NUMERO_TENTATIVI, TIPO_UTENTE, STATO, LINGUA, GRUPPO_LAVORO, IMPORTANZA, NOTE, DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, DATA_AGGIORNAMENTO, AMMINISTRATORE, INFO_IDENTIFICAZIONE)
  values ('ADSAMMIN', '', 'ADSAMMINISTRATORE', 'ROjyTl6', to_date('20-03-2024', 'dd-mm-yyyy'), 'SI', 'SI', null, 0, 'U', 'U', 'I', 'def', null, null, to_date('20-03-2024 11:37:54', 'dd-mm-yyyy hh24:mi:ss'), 'AD4', to_date('20-03-2024 12:10:27', 'dd-mm-yyyy hh24:mi:ss'), 'S', 'Nome e Cognome');

  insert into utenti_gruppo
  (utente,gruppo)
  values ('ADSAMMIN','ADMINS');

  insert into diritti_accesso (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
  values ('ADSAMMIN', 'AD4', 'AD4', 'AMM', 1, null, 0, null, null);

  insert into diritti_accesso (UTENTE, MODULO, ISTANZA, RUOLO, SEQUENZA, ULTIMO_ACCESSO, NUMERO_ACCESSI, GRUPPO, NOTE)
  values ('ADSAMMIN', 'AD4WEB', 'AD4', 'AMM', null, null, null, null, null);
end;
/


