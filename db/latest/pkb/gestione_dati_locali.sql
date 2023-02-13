--liquibase formatted sql

--changeset snegroni:201911041606 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY GESTIONE_DATI_LOCALI
IS
    /******************************************************************************
     NOME:        GESTIONE_DATI_LOCALI.
     DESCRIZIONE: Funzioni di amministrazione.
     ANNOTAZIONI:
     REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     00   04/11/2019 SN     Pulizia accessi oltre i giorni indicati per la conservazione
                            fra 180 (costante) , quanto indicato nel registro (LogAccessRetention)
                            e le caratteristiche di accesso coinvolte
    ******************************************************************************/
    -- Revisione del Package
    s_revisione   CONSTANT AFC.t_revision := 'V1.00';

    PROCEDURE ELIMINA_ACCESSI_PRIMA_GIORNI (p_giorni NUMBER)
 /******************************************************************************
 NOME:        ELIMINA_ACCESSI_PRIMA_GIORNI
 DESCRIZIONE: elimina gli accessi non più voluti
 ARGOMENTI:  p_giorni numero di giorni di accessi da conservare, quelli precedenti
             possono essere cancellati.
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  04/11/2019 SN     Nuova Istituzione
******************************************************************************/
IS
    v_LogAccessRetention_MIN   NUMBER := 180;
    max_giorni_traccia         NUMBER := 0;
BEGIN
    SELECT MAX (giorni_traccia)
      INTO max_giorni_traccia
      FROM caratteristiche_accesso
     WHERE giorni_traccia IS NOT NULL;

    DELETE accessi
     WHERE DATA <
             TRUNC (SYSDATE)
           - GREATEST (v_LogAccessRetention_MIN,
                       NVL (max_giorni_traccia, v_LogAccessRetention_MIN));
END;

PROCEDURE ELIMINA_ACCESSI_MAX_RETENTION
 /******************************************************************************
 NOME:        ELIMINA_ACCESSI_MAX_RETENTION
 DESCRIZIONE: elimina gli accessi non più necessari.
 ARGOMENTI:
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 001  04/11/2019 SN     Pulizia accessi oltre i giorni indicati per la conservazione
                        fra 180 (costante) , quanto indicato nel registro (LogAccessRetention)
                        e le caratteristiche di accesso coinvolte
******************************************************************************/
IS
    v_LogAccessRetention            NUMBER;
    v_LogAccessRetention_MIN        NUMBER := 180;
    d_tipo_accesso                  VARCHAR2 (1);
    d_accesso                       VARCHAR2 (1);
    d_accesso_se                    VARCHAR2 (2);
    d_traccia                       VARCHAR2 (1);
    d_giorni_traccia                INTEGER;
    d_tentativi_pwd                 INTEGER;
    d_val_pwd                       INTEGER;
    d_giorni_pwd                    INTEGER;
    d_pwd_da_modificare             VARCHAR2 (2);
    d_single_sign_on                VARCHAR2 (2);
    d_sleep                         NUMBER (3);
    d_ldap                          VARCHAR2 (2);
    d_min_lung_pwd                  NUMBER (2);
    d_mod_pwd_primo_uso             VARCHAR2 (2);
    d_arch_traccia                  VARCHAR2 (2);
    d_disl_traccia                  VARCHAR2 (100);
    d_car_speciali_pwd              VARCHAR2 (2);
    d_num_obb_pwd                   VARCHAR2 (2);
    d_giorni_prima_riutilizzo_pwd   NUMBER;
BEGIN
    -- Calcolo giorni previsti di LogAccessRetention dal registro
    -- validi su tutto applicativo
    v_LogAccessRetention :=
        GREATEST (
            NVL (
                LOWER (
                    registro_utility.leggi_stringa (
                        'PRODUCTS/AUTHENTICATION',
                        'LogAccessRetention',
                        0)),
                v_LogAccessRetention_MIN),
            v_LogAccessRetention_MIN);

    -- solo se non prevista archiviazione o eliminazione differita.
    IF                       --NVL (d_arch_traccia, 'NO') = 'NO'            OR
       -- solo se non prevista eliminazione differita
       NVL (
           LOWER (
               registro_utility.leggi_stringa ('PRODUCTS/AD4/ACCESSI',
                                               'EliminazioneDifferita',
                                               0)),
           'no') =
       'no'
    THEN
        BEGIN
            FOR v_ute IN (SELECT *
                            FROM utenti
                           WHERE ultimo_tentativo > TRUNC (SYSDATE - 1))
            LOOP
                FOR v_acce IN (SELECT *
                                 FROM diritti_accesso
                                WHERE utente = v_ute.utente
                                  and ultimo_accesso is not null)
                LOOP
                    d_tipo_accesso := 'D';
                    accesso.get_caac_effettive (
                        d_tipo_accesso,
                        istanze_tpk.get_progetto (v_acce.istanza),
                        v_acce.istanza,
                        v_acce.modulo,
                        v_ute.utente,
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
                        d_giorni_prima_riutilizzo_pwd);

                    DELETE accessi
                     WHERE     DATA <
                                 TRUNC (SYSDATE)
                               - GREATEST (
                                     v_LogAccessRetention,
                                     NVL (d_giorni_traccia,
                                          v_LogAccessRetention_MIN))
                           AND utente = v_ute.utente;
                END LOOP;
            END LOOP;
        END;
    END IF;
END;
END;
/
