--liquibase formatted sql

--changeset mmonari:issue72035_updateCAAC

--preconditions onFail:MARK_RAN onError:HALT

begin
   for caac in (select *
                  from caratteristiche_accesso
                 where ammessi_car_speciali_pwd = 'NO'
                 order by caac_id)
   loop
      begin
         update caratteristiche_accesso
            set ammessi_car_speciali_pwd = 'SI'
          where ammessi_car_speciali_pwd = 'NO'
            and caac_id = caac.caac_id;
      exception
         when others then
            key_error_log_tpk.ins(p_error_id       => ''
                                 ,p_error_session  => caac.caac_id
                                 ,p_error_date     => trunc(sysdate)
                                 ,p_error_text     => 'mancato aggiornamento ammessi_car_speciali_pwd'
                                 ,p_error_user     => 'AGGAD4'
                                 ,p_error_usertext => 'issue 72035'
                                 ,p_error_type     => 'E');
      end;
   end loop;
end;
/
