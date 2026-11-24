--liquibase formatted sql

--changeset snegroni:20190612_1636 runOnChange:true endDelimiter:/ stripComments:false

create or replace procedure schedula_rigenera_so(p_utente in utenti.utente%type) is
   d_next_date date;
   d_esegue    number(1) := 0;
   v_job       number;
begin
   select nvl(max(1),0)
     into d_esegue
     from user_jobs
    where what like '%begin utente.rigenera_so(%' || p_utente || '%); end;%'
      and broken = 'N';

   if d_esegue = 0 then

      d_next_date := sysdate + 0.0001;

      dbms_job.submit(job       => v_job
                     ,what      => 'begin utente.rigenera_so(''' || p_utente ||'''); end;'
                     ,next_date => d_next_date);
      --commit; #74137
   end if;
end;
/
