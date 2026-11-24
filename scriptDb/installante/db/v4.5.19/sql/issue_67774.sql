--liquibase formatted sql

--changeset fmarzulli:2024_02_19_issue_67774

--preconditions onFail:MARK_RAN onError:HALT

update registro
set commento = 'Timeout per la connessione al server LDAP espresso in millisecondi. Disponibile con webutils 2022.11 e successive.'
where chiave like 'PRODUCTS/LDAPCONFIG%' and stringa ='Timeout';
