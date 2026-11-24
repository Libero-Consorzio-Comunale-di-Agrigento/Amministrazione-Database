--liquibase formatted sql

--changeset mmonari:correggi_ruolo_AD4AMM

--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:1 select nvl(max(1),0) from ruoli where ruolo='AD4AMM' and modulo is null;

begin
   update ad4_ruoli set modulo = 'AD4WEB' where ruolo = 'AD4AMM';

   insert into ad4_diritti_accesso
      (utente
      ,modulo
      ,istanza
      ,ruolo)
      select utente
            ,'AD4' modulo
            ,'AD4' istanza
            ,gruppo_lavoro
        from ad4_utenti u
       where u.gruppo_lavoro in ('AD4AMM')
         and not exists (select 1
                from ad4_diritti_accesso
               where utente = u.utente
                 and modulo = 'AD4'
                 and istanza = 'AD4');
end;
/
