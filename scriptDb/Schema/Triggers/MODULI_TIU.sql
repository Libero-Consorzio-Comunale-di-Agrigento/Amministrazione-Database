CREATE OR REPLACE TRIGGER MODULI_TIU
/******************************************************************************
 NOME:        MODULI_TIU
 DESCRIZIONE: Invio mail se modificato campo Amministratore per il modulo
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
                        Generato in automatico.
 1    21/05/2019 SN    Invio mail se modificato amministratore (si/no) x un modulo
 2    08/02/2021 SN    Tracciare informazioni nella eventi di tipo APPTRC se
                       modificato amministratore (si/no) x un modulo Feature #48075
******************************************************************************/
   BEFORE INSERT OR UPDATE ON MODULI FOR EACH ROW
DECLARE
   integrity_error         EXCEPTION;
   errno                   INTEGER;
   errmsg                  CHAR(200);
   dummy                   INTEGER;
   FOUND                   BOOLEAN;
   d_min_lung_pwd          NUMBER(2);
   v_messaggio             VARCHAR2 (2000);
   v_id_evento             eventi.id_evento%TYPE;
BEGIN
            -- rev. 2 inizio
            if nvl(:old.amministratore,'N') != nvl(:new.amministratore,'N') then

                    if nvl(:old.amministratore,'N') ='N' and nvl(:new.amministratore,'S')= 'S' then
                      v_messaggio := ' Il modulo in oggetto e'''' diventato AMMINISTRATORE';
                    else
                      v_messaggio := ' Il modulo in oggetto NON e'''' piu'''' AMMINISTRATORE';
                    end if;

            -- rev. 1 inizio
                IF upper(NVL (registro_utility.leggi_stringa ('PRODUCTS/AD4/UTENTE'
                                                , 'NotificaModificaAmministratoreSiNo'
                                                , 1
                  )
                , 'NO')) != 'NO' THEN
                -- invio mail x notificare attributi modificati del modulo
                  DECLARE --invio in post-event cosi va soltanto se effettivamente ha avuto successo
                   a_istruzione   VARCHAR2 (2000);
                   a_messaggio    VARCHAR2 (2000);
                BEGIN
                   a_istruzione :='begin utente.SEND_MSG_MODIFICA_UTENTE('''
                      || ' modulo: '
                      || replace(:new.MODULO,'''','''''')
                      || ' descrizione: '
                      || replace(:new.descrizione,'''','''''')
                      || '    Modificato ! '', ''';
                      a_istruzione := a_istruzione || v_messaggio;
                      a_istruzione := a_istruzione
                      ||'''); end;';
                      null;
                   a_messaggio := 'ERRORE in invio mail per modifiche modulo ('''
                      || replace(:new.modulo,'''','''''')
                      || replace(:new.descrizione,'''','''''')
                      || ''')';
                   integritypackage.set_postevent (a_istruzione, a_messaggio);
                END;
                END IF;
                -- rev. 1 fine
               v_id_evento:= ad4_evento.INSERT_EVENTO
                            ( p_testo        =>substr('Modulo Amministratore('''
                                         ||:new.modulo
                                         || ''') modificato attributo amministratore : '
                                         || :new.amministratore, 1, 2000)
                             , p_db_user    => user
                             , p_data       => to_char(sysdate, 'dd/mm/yyyy hh24:mi:ss' )
                             , p_notificato => 0 --eventi
                             , p_gravita    => 'I'
                             , p_tipo       => 'APPTRC'
                             , p_annotazioni => replace(v_messaggio,'''''','''')
                             , p_utente     => :new.modulo -- p_utente
                             , p_modulo    => null
                             , p_istanza     => null);
            END IF;
            -- rev. 2 fine
EXCEPTION
   WHEN integrity_error THEN
        Integritypackage.InitNestLevel;
        RAISE_APPLICATION_ERROR(errno, errmsg);
   WHEN OTHERS THEN
        Integritypackage.InitNestLevel;
       RAISE;
END;
/


