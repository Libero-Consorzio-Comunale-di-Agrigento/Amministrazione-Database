--liquibase formatted sql

--changeset mturra:201901301231_317 runOnChange:true endDelimiter:/ stripComments:false


CREATE OR REPLACE procedure CARATTERISTICHE_ACCESSO_PM
/******************************************************************************
 NOME:        CARATTERISTICHE_ACCESSO_PM
 DESCRIZIONE: <Descrizione procedure>
 ARGOMENTI:   a_<arg1> IN OUT <type> <Descrizione argomento 1>
              a_<arg2> IN OUT <type> <Descrizione argomento 2>
 ECCEZIONI:   nnnnn, <Descrizione eccezione>
 ANNOTAZIONI: Salvata nella directory ins di AD4 con nome CAAC_PM.CRP
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 1    20/01/2003 MM     Introduzione del campo LDAP.
 2    24/09/2018 SN    Aggiunto campo gg_prima_riutilizzo_pwd
******************************************************************************/
( p_insert_update varchar2, old_caac_id IN number, old_progetto IN varchar2
, old_istanza IN varchar2, old_modulo IN varchar2, old_utente IN varchar2
, new_caac_id IN OUT number, new_tipo_accesso IN varchar2, new_progetto IN varchar2
, new_istanza IN varchar2, new_modulo IN varchar2, new_utente IN varchar2
, new_accesso IN varchar2, new_accesso_se IN varchar2, new_traccia IN varchar2
, new_giorni_traccia IN number, new_tentativi_password IN number
, new_validita_password IN number, new_sleep IN number
, new_single_sign_on IN varchar2, new_ldap IN varchar2
, new_min_lunghezza_pwd IN number, new_mod_pwd_primo_accesso IN varchar2
, new_archiviazione_traccia IN varchar2, new_dislocazione_traccia IN varchar2
, new_car_speciali_pwd IN varchar2, new_num_obb_pwd IN varchar2
, new_gg_prima_riutilizzo_pwd IN number
, p_move_file IN number default 0)
is
   d_caac_id  NUMBER;
begin
   if p_insert_update = 'I' then
      new_caac_id := CARATTERISTICA_ACCESSO.INSERT_CARATTERISTICA
                     ( new_caac_id, new_tipo_accesso , new_progetto
                     , new_istanza , new_modulo , new_utente
                     , new_accesso , new_accesso_se , new_traccia
                     , new_giorni_traccia , new_tentativi_password
                     , new_validita_password , new_sleep
                     , new_single_sign_on , new_ldap
                     , new_min_lunghezza_pwd , new_mod_pwd_primo_accesso
                     , new_archiviazione_traccia , new_dislocazione_traccia
                     , new_car_speciali_pwd , new_num_obb_pwd, new_gg_prima_riutilizzo_pwd );
   elsif p_insert_update = 'U' then
      CARATTERISTICA_ACCESSO.UPDATE_CARATTERISTICA
      ( old_caac_id, old_progetto, old_istanza, old_modulo, old_utente
      , new_caac_id, new_tipo_accesso , new_progetto
      , new_istanza , new_modulo , new_utente
      , new_accesso , new_accesso_se , new_traccia
      , new_giorni_traccia , new_tentativi_password
      , new_validita_password , new_sleep
      , new_single_sign_on , new_ldap
      , new_min_lunghezza_pwd , new_mod_pwd_primo_accesso
      , new_archiviazione_traccia , new_dislocazione_traccia
      , new_car_speciali_pwd , new_num_obb_pwd, new_gg_prima_riutilizzo_pwd, p_move_file );
   end if;
end;
/
