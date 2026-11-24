--liquibase formatted sql

--changeset snegroni:201905021533

declare
v_trovato number;
begin
 select count(*)
  into v_trovato
  from user_tables
 where table_name  = upper('MODULI_STORICO');
    if v_trovato = 0 then
    execute immediate ('CREATE TABLE MODULI_STORICO
(
  ID_EVENTO        NUMBER NOT NULL,
  MODULO           VARCHAR2(10) NOT NULL,
  DESCRIZIONE      VARCHAR2(40) NOT NULL,
  DESCRIZIONE_AL1  VARCHAR2(40),
  DESCRIZIONE_AL2  VARCHAR2(40),
  PROGETTO         VARCHAR2(8) NOT NULL,
  NOTE             VARCHAR2(2000),
  AMMINISTRATORE   VARCHAR2(1) DEFAULT ''N'',
  DATA             DATE NOT NULL,
  OPERAZIONE       VARCHAR2(2),
  BI_RIFERIMENTO   NUMBER,
  UTENTE_AGG       VARCHAR2(8),
  USER_ORACLE      VARCHAR2(30),
  INFO             VARCHAR2(2000),
  PROGRAMMA        VARCHAR2(50)
)');
    end if;
end;
/

--rollback drop table MODULI_STORICO;