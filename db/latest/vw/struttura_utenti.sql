--liquibase formatted sql

--changeset mturra:201901301231_446 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW STRUTTURA_UTENTI
(
    DESCRIZIONE,
    FIGLIO,
    TIPO_FIGLIO,
    PADRE,
    TIPO_PADRE,
    STRUTTURA,
    LIVELLO,
    ORD,
    GRUPPO_SO
)
AS SELECT nominativo
               descrizione,
           --1 28/11/2019 SNeg Per la struttura considero solo i gruppi di tipo 'O'Bug #38849

           utente
               figlio,
           tipo_utente
               tipo_figlio,
           ''
               padre,
           ''
               tipo_padre,
           utente
               struttura,
           0
               livello,
           DECODE (tipo_utente,
                   'U', 'zzzzzzzz',
                   utente.get_ascendenza (utente, '', 'TD'))
               ord,
           DECODE (tipo_utente,
                   'O', NVL (ruoli_tpk.get_gruppo_so (gruppo_lavoro), 'N'),
                   'N')
               gruppo_so
      FROM utenti uten
     WHERE     NOT EXISTS
                   (SELECT 'x'
                      FROM utenti_gruppo
                     WHERE utente = uten.utente)
           AND utente != 'ric_abil'
    UNION ALL
        SELECT DECODE (utente.get_tipo_utente (utente),
                       'U', utente.get_nominativo (utente, 'Y', 0),
                       gruppo.get_descrizione (utente))
                   descrizione,
               utente
                   figlio,
               utente.get_tipo_utente (utente),
               gruppo
                   padre,
               gruppo.get_tipo (gruppo),
               utente.get_ascendenza (utente, gruppo)
                   struttura,
               LEVEL
                   livello,
               utente.get_ascendenza (utente, gruppo, 'TD')
                   ord,
               DECODE (
                   utente.get_tipo_utente (utente),
                   'O', NVL (
                            ruoli_tpk.get_gruppo_so (
                                gruppo.get_gruppo_lavoro (utente)),
                            'N'),
                   'N')
                   gruppo_so
          FROM utenti_gruppo grup
         WHERE utente != 'ric_abil' AND gruppo != 'ric_abil'
    START WITH NOT EXISTS
                   (SELECT 'x'
                      FROM utenti_gruppo
                     WHERE grup.gruppo = utente)
    CONNECT BY     PRIOR utente = gruppo
               -- rev. 1 inizio
               AND utente.get_tipo_utente (gruppo) = 'O'
               AND utente.get_tipo_utente (PRIOR utente) = 'O'
    -- rev. 1 fine
    ORDER BY 8;



