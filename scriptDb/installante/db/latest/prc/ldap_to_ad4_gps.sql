--liquibase formatted sql

--changeset mturra:ldap_to_ad4_gps_prc runOnChange:true endDelimiter:/ stripComments:false runAlways:true

--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition-sql-check expectedResult:1 select  count(*)  from all_procedures p, user_synonyms o where object_name = 'RAPPORTI_INDIVIDUALI_PKG' and procedure_name = 'UPD_EMAIL' and o.table_name = p.object_name and o.synonym_name = 'GPS_RAPPORTI_INDIVIDUALI_PKG';


CREATE OR REPLACE PROCEDURE LDAP_TO_AD4_GPS
IS
    /******************************************************************************
    NOME:        LDAP_TO_AD4_GPS
    DESCRIZIONE: Richiesta dal Galliera:
                 gestire un job che:
    per tutti gli utenti di ad4 ottenga ni in gps (x questo meglio una funzione o una query?)
    cerchi su ldap, nel campo employeeID,  x il valore ni in gps collegato all'utenza e fa alcuni controlli e sistemazioni:
     - se il nominativo in ad4 è diverso da quello ldap (sAMAaccountName) va allineato al nominativo di ldap (se esiste già record con lo stesso nominativo come ci dobbiamo comportare?)
     - se è valorizzato il campo mail (attributo mail di ldap) va aggiornata l'informazione in gps (sempre o si controlla se diverso?), serve una funzione che ad4 possa richiamare x fare questa attività.
    GPS:
    ora su svi gps ci sono nel pkg rapporti_individuali_pkd 2 metodi:
    rapporti_individuali_pkd.utente_get_ni
    rapporti_individuali_pkd.upd_email
    il primo è la funzione che dato un codice utente di ad4 ti da l'ni di gps
    la seconda è la procedure che dato ni di gps ed una email fa l'update su rapporti_individuali

    PER VEDERE le SEGNALAZIONI:
     select *
     from eventi
     where tipo = 'APPTRC'
     order by 1 desc;

    CONFIGURAZIONE:
     GPS: grant execute on rapporti_individuali_pkg to ad4;
     AD4: create synonym gps_rapporti_individuali_pkg for gps.rapporti_individuali_pkg;

    NOTE: ATTENZIONE!!!!!!!!!! la password nel registro deve essere in chiaro!!!!!!!!!!!!

    IL package verrà creato in aggiornamento solo se si hanno le grant da GPS, x essere eseguito dovrà essere schedulato.

    REVISIONI: .
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    001 12/03/2021  SNeg  Prima emissione
   ******************************************************************************/
    SUBTYPE ldap_attribute IS VARCHAR2 (4000);

    l_ldap_host                ldap_attribute;
    l_ldap_port                ldap_attribute;
    l_ldap_user                ldap_attribute;
    l_ldap_passwd              ldap_attribute;
    l_ldap_base                ldap_attribute;
    ldap_camponominativo       ldap_attribute;
    ldap_tipo_autenticazione   ldap_attribute;
    d_chiave                   VARCHAR2 (30) := 'PRODUCTS/LDAPCONFIG';
    d_tipo_connessione         VARCHAR2 (10) := 'ldap';
    d_separatore               VARCHAR2 (1) := ',';


    l_retval                   PLS_INTEGER;
    l_session                  DBMS_LDAP.session;
    l_attrs                    DBMS_LDAP.string_collection;
    l_message                  DBMS_LDAP.MESSAGE;
    l_entry                    DBMS_LDAP.MESSAGE;
    l_attr_name                VARCHAR2 (256);
    l_ber_element              DBMS_LDAP.ber_element;
    l_vals                     DBMS_LDAP.string_collection;
    v_ni                       NUMBER;
    v_id_evento                NUMBER;
    v_nome_campo_attributo_mail varchar2(30) := 'mail';
BEGIN
    ldap_tipo_autenticazione :=
        registro_utility.leggi_stringa (UPPER (d_chiave),
                                        'TIPOAUTENTICAZIONE',
                                        1);
    l_ldap_host :=
        registro_utility.leggi_stringa (UPPER (d_chiave), 'SERVER', 1);
    l_ldap_port :=
        registro_utility.leggi_stringa (UPPER (d_chiave), 'PORTA', 1);
    l_ldap_user :=
        registro_utility.leggi_stringa (UPPER (d_chiave),
                                        'UTENTECONNESSIONELDAP',
                                        1);
    l_ldap_passwd :=
        registro_utility.leggi_stringa (UPPER (d_chiave),
                                        'PWDCONNESSIONELDAP',
                                        1);

    IF INSTR (
           registro_pac.get_commento (UPPER (d_chiave),
                                      'PwdConnessioneLDAP',
                                      'AD4'),
           ' criptata ') >
       0
    THEN
        raise_application_error (
            -20999,
            'ATTENZIONE : la password nel registro deve essere in chiaro');
    END IF;

    l_ldap_base :=
        registro_utility.leggi_stringa (UPPER (d_chiave), 'ROOTUSERS', 1);

    --    IF l_ldap_base IS NOT NULL
    --    THEN
    --        l_ldap_base := d_separatore || l_ldap_base;
    --    END IF;

    ldap_camponominativo :=
        registro_utility.leggi_stringa (UPPER (d_chiave),
                                        'CampoNominativoInLDAP',
                                        0);

    --- chiamate
    -- Choose to raise exceptions.
    DBMS_LDAP.USE_EXCEPTION := TRUE;

    -- Connect to the LDAP server.
    l_session :=
        DBMS_LDAP.init (hostname => l_ldap_host, portnum => l_ldap_port);

    l_retval :=
        DBMS_LDAP.simple_bind_s (ld       => l_session,
                                 dn       => l_ldap_user,
                                 passwd   => l_ldap_passwd);

    FOR v_utente IN (SELECT *
                       FROM utenti
                      WHERE stato = 'U' AND tipo_utente = 'U')
    LOOP
        -- recupero ni dal personale
        v_ni := gps_rapporti_individuali_pkg.utente_get_ni (v_utente.utente);

        -- Get all attributes
        l_attrs (1) := v_nome_campo_attributo_mail;
        l_attrs (2) := ldap_camponominativo; -- di solito 'samaccountname'
        l_retval :=
            DBMS_LDAP.search_s (
                ld         => l_session,
                base       => l_ldap_base,
                scope      => DBMS_LDAP.SCOPE_SUBTREE,
                filter     =>
                       '(&(employeeID='
                    || v_ni
                    || ')(!(|(userAccountControl=514)(userAccountControl=66050)(userAccountControl=66082))))',--solo validi
                attrs      => l_attrs,
                attronly   => 0,
                res        => l_message);

        IF DBMS_LDAP.count_entries (ld => l_session, msg => l_message) > 0
        THEN
            -- Get all the entries returned by our search.
            l_entry :=
                DBMS_LDAP.first_entry (ld => l_session, msg => l_message);

           <<entry_loop>>
            WHILE l_entry IS NOT NULL
            LOOP
                -- Get all the attributes for this entry.
                --            DBMS_OUTPUT.PUT_LINE ('---------------------------------------');
                l_attr_name :=
                    DBMS_LDAP.first_attribute (ld          => l_session,
                                               ldapentry   => l_entry,
                                               ber_elem    => l_ber_element);

               <<attributes_loop>>
                WHILE l_attr_name IS NOT NULL
                LOOP
                v_id_evento := null; -- azzera per inserire nuovo evento
                    -- Get all the values for this attribute.
                    l_vals :=
                        DBMS_LDAP.get_values (ld          => l_session,
                                              ldapentry   => l_entry,
                                              attr        => l_attr_name);

                   <<values_loop>>
                    FOR i IN l_vals.FIRST .. l_vals.LAST
                    LOOP
                        IF     upper(l_attr_name)  = upper(ldap_camponominativo)
                           AND UPPER (l_vals (i)) !=
                               UPPER (v_utente.nominativo)
                        THEN
--                            DBMS_OUTPUT.PUT_LINE (
--                                   'DA MODIFICARE: '
--                                || l_attr_name
--                                || ' = '
--                                || SUBSTR (l_vals (i), 1, 200)
--                                || ' => '
--                                || UPPER (v_utente.nominativo));

                            IF utente.exists_nominativo (UPPER (l_vals (i))) = 1
                            THEN
                                ad4_evento.update_evento (
                                    p_id_evento     => v_id_evento,
                                    p_testo         =>
                                           'ALLINEAMENTO NOTTURNO da LDAP ERRORE!!!!!!!!!!!!!!!!'
                                        || ' nominativo già in uso : '
                                        || UPPER (l_vals (i))
                                        || ' impossibile rinominare : '
                                        || v_utente.nominativo,
                                    p_db_user       => USER,
                                    p_data          => SYSDATE,
                                    p_notificato    => 0,
                                    p_gravita       => 'E',
                                    p_tipo          => 'APPTRC',
                                    p_annotazioni   => '',
                                    p_utente        => 'AD4',
                                    p_modulo        => 'no',
                                    p_istanza       => 'no');
                            ELSE
                                UPDATE utenti
                                   SET nominativo = UPPER (l_vals (i))
                                 WHERE nominativo = v_utente.nominativo;

                                ad4_evento.update_evento (
                                    p_id_evento     => v_id_evento,
                                    p_testo         =>
                                        'ALLINEAMENTO NOTTURNO da LDAP'
                                        || ' nominativo : '
                                        || v_utente.nominativo
                                        || ' rinominato in : '
                                        || UPPER (l_vals (i)),
                                    p_db_user       => USER,
                                    p_data          => SYSDATE,
                                    p_notificato    => 0,
                                    p_gravita       => 'I',
                                    p_tipo          => 'APPTRC',
                                    p_annotazioni   => '',
                                    p_utente        => 'AD4',
                                    p_modulo        => 'no',
                                    p_istanza       => 'no');
                            END IF;
                        ELSIF upper(l_attr_name)  = upper(v_nome_campo_attributo_mail) and l_vals (i) is not null
                        THEN
                            -- lancia aggiornamento che viene fatto solo se mail è diversa
                            --                  DBMS_OUTPUT.PUT_LINE (
                            --                           'AGGIORNAMENTO MAIL : '
                            --                        || l_attr_name
                            --                        || ' = '
                            --                        || SUBSTR (l_vals (i), 1, 200));
                            NULL;

                                          gps_rapporti_individuali_pkg.upd_email(
                                                       p_ni           => v_ni
                                                      ,p_email        => l_vals (i)
                                                      ,p_overwrite    => true
                                                      ,p_onlydifferent=> true
                                                    );

                        END IF;
                    --                    DBMS_OUTPUT.PUT_LINE (
                    --                           'ATTIBUTE_NAME: '
                    --                        || l_attr_name
                    --                        || ' = '
                    --                        || SUBSTR (l_vals (i), 1, 200));
                    END LOOP values_loop;

                    l_attr_name :=
                        DBMS_LDAP.next_attribute (
                            ld          => l_session,
                            ldapentry   => l_entry,
                            ber_elem    => l_ber_element);
                END LOOP attibutes_loop;

                l_entry :=
                    DBMS_LDAP.next_entry (ld => l_session, msg => l_entry);
            END LOOP entry_loop;
        END IF;
    END LOOP;                                             -- loop sugli utenti

    -- Disconnect from the LDAP server.
    l_retval := DBMS_LDAP.unbind_s (ld => l_session);
    commit;
--    DBMS_OUTPUT.PUT_LINE ('L_RETVAL: ' || l_retval);
END;
/
