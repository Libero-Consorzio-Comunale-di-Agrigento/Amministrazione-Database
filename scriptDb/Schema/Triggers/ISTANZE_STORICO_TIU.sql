CREATE OR REPLACE TRIGGER ISTANZE_STORICO_TIU
/******************************************************************************
       NOME:        ISTANZE_STORICO_TIU
       DESCRIZIONE: Trigger for salvare dati storici
                             at INSERT or UPDATE or DELETE on Table ISTANZE
       ECCEZIONI:
       ANNOTAZIONI: -
       REVISIONI:
       Rev. Data       Autore Descrizione
       ---- ---------- ------ ------------------------------------------------------
          1 06/03/2020 SNeg   Prima distribuzione
      ******************************************************************************/
   AFTER INSERT OR UPDATE OR DELETE
   ON ISTANZE
   FOR EACH ROW
DECLARE
   integrity_error EXCEPTION;
   errno    INTEGER;
   errmsg   CHAR (200);
   dummy    INTEGER;
   FOUND    BOOLEAN;
   d_id_evento number;
   FUNCTION get_id_evento
   RETURN NUMBER is
   v_id_evento number;
   BEGIN
   select isst_sq.nextval
               into v_id_evento
               from dual;
   return v_id_evento;
   END;
   PROCEDURE inserisci (p_operazione VARCHAR2, p_info VARCHAR2, p_id_evento number default null, p_id_evento_rif number default null)
   IS
   v_id_evento number;
   BEGIN
     if p_id_evento is null then
       v_id_evento:= get_id_evento;
     else -- passato id evento
        v_id_evento := p_id_evento;
     end if;
      IF p_info = 'NEW'
      THEN
         INSERT INTO ISTANZE_STORICO (ID_EVENTO
                                      , ISTANZA
                                      , PROGETTO
                                      , ENTE
                                      , DESCRIZIONE
                                      , USER_ORACLE
                                      , PASSWORD_ORACLE
                                      , DISLOCAZIONE
                                      , DISLOCAZIONE_TEMPORANEA
                                      , INSTALLAZIONE
                                      , VERSIONE
                                      , DISLOCAZIONE_DIMENSIONAMENTI
                                      , NOTE
                                      , LINGUA
                                      , LINK_ORACLE
                                      , DATABASE_LINK
                                      , SERVIZIO
                                      , DATABASE_DRIVER
                                      , ISTANZA_AMMINISTRATORE
                                      , DATA
                                      , OPERAZIONE
                                      , BI_RIFERIMENTO
                                      , UTENTE_AGGIORNAMENTO
                                      , USER_ORACLE_MODIFICA
                                      , INFO
                                      , PROGRAMMA   )
           VALUES   ( v_id_evento
                    , :new.ISTANZA
                    , :new.PROGETTO
                    , :new.ENTE
                    , :new.DESCRIZIONE
                    , :new.USER_ORACLE
                    , :new.PASSWORD_ORACLE
                    , :new.DISLOCAZIONE
                    , :new.DISLOCAZIONE_TEMPORANEA
                    , :new.INSTALLAZIONE
                    , :new.VERSIONE
                    , :new.DISLOCAZIONE_DIMENSIONAMENTI
                    , :new.NOTE
                    , :new.LINGUA
                    , :new.LINK_ORACLE
                    , :new.DATABASE_LINK
                    , :new.SERVIZIO
                    , :new.DATABASE_DRIVER
                    , :new.ISTANZA_AMMINISTRATORE
                    , SYSDATE
                    , p_operazione
                    , p_id_evento_rif
                    , SI4.UTENTE
                    , USER
                    , gv_$session_pkg.get_info (USERENV ('sessionid'))
                    , gv_$session_pkg.get_program (USERENV ('sessionid')));
      ELSIF p_info = 'OLD'
      THEN
          INSERT INTO ISTANZE_STORICO (ID_EVENTO
                                      , ISTANZA
                                      , PROGETTO
                                      , ENTE
                                      , DESCRIZIONE
                                      , USER_ORACLE
                                      , PASSWORD_ORACLE
                                      , DISLOCAZIONE
                                      , DISLOCAZIONE_TEMPORANEA
                                      , INSTALLAZIONE
                                      , VERSIONE
                                      , DISLOCAZIONE_DIMENSIONAMENTI
                                      , NOTE
                                      , LINGUA
                                      , LINK_ORACLE
                                      , DATABASE_LINK
                                      , SERVIZIO
                                      , DATABASE_DRIVER
                                      , ISTANZA_AMMINISTRATORE
                                      , DATA
                                      , OPERAZIONE
                                      , BI_RIFERIMENTO
                                      , UTENTE_AGGIORNAMENTO
                                      , USER_ORACLE_MODIFICA
                                      , INFO
                                      , PROGRAMMA   )
           VALUES   ( v_id_evento
                    , :old.ISTANZA
                    , :old.PROGETTO
                    , :old.ENTE
                    , :old.DESCRIZIONE
                    , :old.USER_ORACLE
                    , :old.PASSWORD_ORACLE
                    , :old.DISLOCAZIONE
                    , :old.DISLOCAZIONE_TEMPORANEA
                    , :old.INSTALLAZIONE
                    , :old.VERSIONE
                    , :old.DISLOCAZIONE_DIMENSIONAMENTI
                    , :old.NOTE
                    , :old.LINGUA
                    , :old.LINK_ORACLE
                    , :old.DATABASE_LINK
                    , :old.SERVIZIO
                    , :old.DATABASE_DRIVER
                    , :old.ISTANZA_AMMINISTRATORE
                    , SYSDATE
                    , p_operazione
                    , p_id_evento_rif
                    , SI4.UTENTE
                    , USER
                    , gv_$session_pkg.get_info (USERENV ('sessionid'))
                    , gv_$session_pkg.get_program (USERENV ('sessionid')));
      END IF;
   END;
BEGIN
      IF INSERTING
      THEN
         inserisci (p_operazione => 'I', p_info => 'NEW');
      ELSIF DELETING
      THEN
         inserisci (p_operazione => 'D', p_info => 'OLD');
      ELSIF UPDATING AND
              (    nvl(:new.ISTANZA                     ,'XyZ') <> nvl(:old.ISTANZA                     ,'XyZ')
                or nvl(:new.PROGETTO                    ,'XyZ') <> nvl(:old.PROGETTO                    ,'XyZ')
                or nvl(:new.ENTE                        ,'XyZ') <> nvl(:old.ENTE                        ,'XyZ')
                or nvl(:new.DESCRIZIONE                 ,'XyZ') <> nvl(:old.DESCRIZIONE                 ,'XyZ')
                or nvl(:new.USER_ORACLE                 ,'XyZ') <> nvl(:old.USER_ORACLE                 ,'XyZ')
                or nvl(:new.PASSWORD_ORACLE             ,'XyZ') <> nvl(:old.PASSWORD_ORACLE             ,'XyZ')
                or nvl(:new.DISLOCAZIONE                ,'XyZ') <> nvl(:old.DISLOCAZIONE                ,'XyZ')
                or nvl(:new.DISLOCAZIONE_TEMPORANEA     ,'XyZ') <> nvl(:old.DISLOCAZIONE_TEMPORANEA     ,'XyZ')
                or nvl(:new.INSTALLAZIONE               ,'XyZ') <> nvl(:old.INSTALLAZIONE               ,'XyZ')
                or nvl(:new.VERSIONE                    ,'XyZ') <> nvl(:old.VERSIONE                    ,'XyZ')
                or nvl(:new.DISLOCAZIONE_DIMENSIONAMENTI,'XyZ') <> nvl(:old.DISLOCAZIONE_DIMENSIONAMENTI,'XyZ')
                or nvl(:new.NOTE                        ,'XyZ') <> nvl(:old.NOTE                        ,'XyZ')
                or nvl(:new.LINGUA                      ,'XyZ') <> nvl(:old.LINGUA                      ,'XyZ')
                or nvl(:new.LINK_ORACLE                 ,'XyZ') <> nvl(:old.LINK_ORACLE                 ,'XyZ')
                or nvl(:new.DATABASE_LINK               ,'XyZ') <> nvl(:old.DATABASE_LINK               ,'XyZ')
                or nvl(:new.SERVIZIO                    ,'XyZ') <> nvl(:old.SERVIZIO                    ,'XyZ')
                or nvl(:new.DATABASE_DRIVER             ,'XyZ') <> nvl(:old.DATABASE_DRIVER             ,'XyZ')
                or nvl(:new.ISTANZA_AMMINISTRATORE      ,'XyZ') <> nvl(:old.ISTANZA_AMMINISTRATORE      ,'XyZ'))
      THEN
         d_id_evento := get_id_evento;
         inserisci (p_operazione => 'BI', p_info => 'OLD', p_id_evento => d_id_evento);
         inserisci (p_operazione => 'AI', p_info => 'NEW', p_id_evento =>  null,p_id_evento_rif =>  d_id_evento);
      END IF;
END;
/


