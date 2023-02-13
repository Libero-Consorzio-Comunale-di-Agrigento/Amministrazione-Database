--liquibase formatted sql

--changeset snegroni:20190905_1219 runOnChange:true endDelimiter:/ stripComments:false


CREATE OR REPLACE PROCEDURE EVENTI_ELIMINAZIONE is
/******************************************************************************
 NOME:        EVENTI_ELIMINAZIONE.
 DESCRIZIONE: Eliminazione dalla tabella eventi.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  24/09/2020 SN     cancellare anche gli eventi più vecchi di 1 anno
******************************************************************************/
      v_LogAccessRetention number;
      v_LogAccessRetention_MIN number := 180;
      d_progetto              progetti.progetto%TYPE;
      d_tipo_accesso          VARCHAR2 (1);
      d_accesso               VARCHAR2 (1);
      d_accesso_se            VARCHAR2 (2);
      d_traccia               VARCHAR2 (1);
      d_giorni_traccia        INTEGER;
      d_tentativi_pwd         INTEGER;
      d_val_pwd               INTEGER;
      d_giorni_pwd            INTEGER;
      d_pwd_da_modificare     VARCHAR2 (2);
      d_single_sign_on        VARCHAR2 (2);
      d_sleep                 NUMBER (3);
      d_ldap                  VARCHAR2 (2);
      d_min_lung_pwd          NUMBER (2);
      d_mod_pwd_primo_uso     VARCHAR2 (2);
      d_arch_traccia          VARCHAR2 (2);
      d_disl_traccia          VARCHAR2 (100);
      d_car_speciali_pwd      VARCHAR2 (2);
      d_num_obb_pwd           VARCHAR2 (2);
      d_tipo_autenticazione   VARCHAR2 (40);
      d_note                  VARCHAR2 (2000);
      d_dep                   VARCHAR2 (2000);
      d_num_tentativi         INTEGER;
      d_giorni_prima_riutilizzo_pwd INTEGER;
      begin
      for v_acce in (
                      select utente, modulo, istanza
                       from diritti_accesso
                      where trunc(ultimo_accesso) = trunc(sysdate)-1
                    ) loop
              BEGIN
               SELECT progetto
                 INTO d_progetto
                 FROM istanze
                WHERE istanza = v_acce.istanza;
               d_tipo_accesso := 'D';
               accesso.get_caac_effettive (d_tipo_accesso,
                                   d_progetto,
                                   v_acce.istanza,
                                   v_acce.modulo,
                                   v_acce.utente,
                                   d_accesso,
                                   d_accesso_se,
                                   d_traccia,
                                   d_giorni_traccia,
                                   d_tentativi_pwd,
                                   d_val_pwd,
                                   d_sleep,
                                   d_single_sign_on,
                                   d_ldap,
                                   d_min_lung_pwd,
                                   d_mod_pwd_primo_uso,
                                   d_arch_traccia,
                                   d_disl_traccia,
                                   d_car_speciali_pwd,
                                   d_num_obb_pwd,
                                   d_giorni_prima_riutilizzo_pwd
                                  );
             exception
             when no_data_found then
                -- non trovato il diritto di accesso
                d_giorni_traccia := 180;
             end;
         v_LogAccessRetention := greatest(NVL
                  (LOWER
                      (registro_utility.leggi_stringa
                                                     ('PRODUCTS/AUTHENTICATION',
                                                      'LogAccessRetention',
                                                      0
                                                     )
                      ),
                   v_LogAccessRetention_MIN
                  ) ,v_LogAccessRetention_MIN );
         -- solo se non prevista archiviazione o eliminazione differita.
         IF    NVL (d_arch_traccia, 'NO') = 'NO'
            OR NVL
                  (LOWER
                      (registro_utility.leggi_stringa
                                                     ('PRODUCTS/AD4/ACCESSI',
                                                      'EliminazioneDifferita',
                                                      0
                                                     )
                      ),
                   'no'
                  ) = 'no'
         THEN
            DELETE      accessi
                  WHERE DATA < TRUNC (SYSDATE) - greatest(v_LogAccessRetention, NVL (d_giorni_traccia, v_LogAccessRetention_MIN))
                    AND utente = v_acce.utente
                    AND modulo = v_acce.modulo
                    AND istanza = v_acce.istanza
                    ;
                    -- serve cancellare da eventi?
                    dbms_output.put_line('utente = '|| v_acce.utente ||' modulo = '|| v_acce.modulo ||' istanza = '|| v_acce.istanza );
             dbms_output.put_line(' cancellazione utente ' || v_acce.utente || ':' || SQL%ROWCOUNT || ' (' ||greatest(v_LogAccessRetention, NVL (d_giorni_traccia, v_LogAccessRetention_MIN)) ||')');
         END IF;
         commit;
     end Loop;
     delete from eventi
           where data < sysdate - 365
             and rownum <=50000;
end;
/
