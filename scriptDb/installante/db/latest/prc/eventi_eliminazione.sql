--liquibase formatted sql

--changeset snegroni:20190905_1219 runOnChange:true endDelimiter:/ stripComments:false


create or replace procedure eventi_eliminazione(p_giorni in number default 90) is
   /******************************************************************************
    NOME:        EVENTI_ELIMINAZIONE.
    DESCRIZIONE: Eliminazione dalla tabella eventi.
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    001  24/09/2020 SN     cancellare anche gli eventi più vecchi di 1 anno
    002  12/03/2024 MM     #69900 - spostate letture registro fuori dal loop
                           Gestione del backup su eventi_storico
                           Refactor completo
                           Recupera il valore di LogAccessRetention.
                           Determina il numero di giorni da considerare.
                           Archivia gli eventi per ogni giorno spostandoli nella tabella eventi_storico.
                           Aggiorna gli eventi LOGON a LOGOFF in base all’intervallo di date specificato.
    003  11/03/2026 MM     #83142 - nuovi campi monitoraggio
   ******************************************************************************/
   v_logaccessretention     number;
   v_logaccessretention_min number := 90;
   d_data_start             date;
   d_data                   date;
   d_data_end               date;
   d_giorni                 number;
begin
   -- Recupera il valore di LogAccessRetention
   v_logaccessretention := greatest(nvl(lower(registro_utility.leggi_stringa('PRODUCTS/AUTHENTICATION'
                                                                            ,'LogAccessRetention'
                                                                            ,0))
                                       ,v_logaccessretention_min)
                                   ,v_logaccessretention_min);

   -- Determina il numero di giorni da considerare
   select greatest(p_giorni, v_logaccessretention) into d_giorni from dual;

   -- Trova la data di inizio
   select min(trunc(data)) into d_data_start from eventi;

   -- Calcola la data di fine
   select (trunc(sysdate) - 1) - d_giorni into d_data_end from dual;

   -- Calcola il totale dei giorni
   d_giorni := d_data_end - d_data_start + 1;

   -- Archivia gli eventi per ogni giorno
   for c_giorni in 0 .. d_giorni
   loop
      d_data := d_data_start + c_giorni;
      begin
         for eve in (select * from eventi e where data between d_data and d_data + .99999)
         loop
            insert into eventi_storico
               (id_evento
               ,testo
               ,annotazioni
               ,tipo
               ,gravita
               ,db_user
               ,utente
               ,modulo
               ,istanza
               ,data
               ,notificato
               ,file_locator
               ,stato
               ,session_id
               ,tipo_entita
               ,id_entita
               ,url_entita
               ,entita
               ,indirizzo_ip)
            values
               (eve.id_evento
               ,eve.testo
               ,eve.annotazioni
               ,eve.tipo
               ,eve.gravita
               ,eve.db_user
               ,eve.utente
               ,eve.modulo
               ,eve.istanza
               ,eve.data
               ,eve.notificato
               ,eve.file_locator
               ,eve.stato
               ,eve.session_id
               ,eve.tipo_entita
               ,eve.id_entita
               ,eve.url_entita
               ,eve.entita
               ,eve.indirizzo_ip);
            delete from eventi e where id_evento = eve.id_evento;
         end loop;
         commit;
      end;
   end loop;

   -- Aggiorna gli eventi LOGON a LOGOFF
   d_giorni := (trunc(sysdate) - 1) - d_data_end + 1;
   for c_giorni in 0 .. d_giorni
   loop
      d_data := (trunc(sysdate) - 1) - c_giorni;
      begin
         update eventi
            set tipo        = 'LOGOFF'
               ,annotazioni = annotazioni || ' (sessione chiusa)'
          where data between d_data and d_data + .99999
            and tipo || '' = 'LOGON';
         commit;
      end;
   end loop;
end;
/
