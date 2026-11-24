--liquibase formatted sql

--changeset mmonari:utenti_soggetti_tiu-69703 runOnChange:true stripComments:false

create or replace trigger utenti_soggetti_tiu
/******************************************************************************
     NOME:        UTENTI_SOGGETTI_TIU
     DESCRIZIONE: Trigger per impostare una password di default per soggetti a cui viene associata un'utenza senza password
                           at INSERT or UPDATE or DELETE on Table UTENTI
     ECCEZIONI:
     ANNOTAZIONI: -
     REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
        1 22/01/2024 AD     Prima distribuzione #69703
   ******************************************************************************/
   after insert on ad4.utenti_soggetti
   for each row
declare
   integrity_error exception;
   errno           integer;
   errmsg          char(200);
   dummy           integer;
   found           boolean;
   d_pwd           varchar2(200);
   d_metodo        varchar2(3);
   d_nome          soggetti.nome%type;
   d_data_nas      varchar2(10);
begin
   if utente.get_password(:new.utente) is null then
      d_metodo := nvl(upper(registro_utility.leggi_stringa('PRODUCTS/AD4/UTENTE'
                                                          ,'SettaPWDDefault'
                                                          ,0))
                     ,'NO');
      if d_metodo in ('SI', 'YES', 'CF') then
         d_pwd := soggetto.get_codice_fiscale(:new.soggetto);
         if d_pwd = 'XXXXXXXXXXXXXXXX' then
            d_pwd := '';
         end if;
      elsif d_metodo = 'CN' then
         d_data_nas := replace(soggetto.get_data_nascita(:new.soggetto), '/', '');
         d_nome     := replace(replace(replace(replace(soggetto.get_denominazione(:new.soggetto)
                                                      ,''''
                                                      ,'')
                                              ,' '
                                              ,'')
                                      ,'-'
                                      ,'')
                              ,'_');
         if d_nome is null or d_data_nas is null then
            d_pwd := null;
         else
            d_pwd := d_nome || d_data_nas;
         end if;
      end if;
      if d_pwd is not null then
         crypt.crypt_password(d_pwd);
         update utenti set password = d_pwd where utente = :new.utente;
      end if;
   end if;
exception
   when others then
      null;
end;
/
