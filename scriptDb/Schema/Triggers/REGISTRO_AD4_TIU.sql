CREATE OR REPLACE TRIGGER REGISTRO_AD4_TIU
/******************************************************************************
 NOME:        REGISTRO_AD4_TIU
 DESCRIZIONE: Trigger for Check FUNCTIONAL Integrity
                            Set FUNCTIONAL Integrity
                       at INSERT or UPDATE or DELETE on Table REGISTRO
 ECCEZIONI:
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
    0 __/__/____ __
    1 19/10/2018 SN     Calcolo password criptata da fare in autonomous transaction
    1 02/04/2019 SN     Schedula job se modificati attributi per gestire coerenza
                        Amministratore/Operatore x moduli/utenti
    2 22/05/2019 SN     Ricalcolo variabile con impostazioni registro
                        'PRODUCTS/AD4/UTENTE', 'Accesso ModuloAMM solo se UtenteAMM',
    3 20/12/2019 SN     Spostamento ricalcolo di rev.2 nel trigger di before
    4 02/03/2020 Sn     Crypt con metodo STANDARD Feature #40748
    5 27/02/2020  SN    Aggiungere la gestione di password criptate con più algoritmi e con salt. Feature #40748
                       (modificato quanto introdotto precedentemente con indicazione md5)
******************************************************************************/
BEFORE DELETE OR INSERT OR UPDATE
ON REGISTRO REFERENCING NEW AS NEW OLD AS OLD
FOR EACH ROW
DECLARE
      a_istruzione   VARCHAR2 (4000);
      a_messaggio    VARCHAR2 (4000);
      v_num_modu_amm number;
      v_num_uten_amm number;
      d_job          number;
BEGIN
   a_istruzione := 'BEGIN registro_ad4_pkg.CHK_DI_POST('||AFC.QUOTE(:NEW.CHIAVE)||', '||AFC.QUOTE(:NEW.STRINGA)||', '||AFC.QUOTE(:NEW.VALORE)||', '||AFC.QUOTE(:OLD.CHIAVE)||', '||AFC.QUOTE(:OLD.STRINGA)||', '||AFC.QUOTE(:OLD.VALORE)||'); END;';
   a_messaggio := '';
   integritypackage.set_postevent (a_istruzione, a_messaggio);
   -- se il campo contiene una password di utenza ldap deve essere criptato.
   if upper(:new.stringa) = upper('PwdConnessioneLDAP') and
        upper(nvl(:new.valore,'x')) != upper(nvl(:old.valore,'x')) -- se cambia la password
        and instr(upper(:new.commento),'CRIPTATA') >0 -- deve essere criptata
        then
         crypt.crypt_password (:new.valore, 'STANDARD'); -- rev. 4
    end if;
   -- se introduco gestione coerenza modulo e utente x accessi si devono ricalcolare tutti
   -- se lo riporto a no si devono rifare i calcoli?
   if updating and upper(:new.stringa) = upper('Accesso ModuloAMM solo se UtenteAMM') and
        upper(nvl(:new.valore,'x')) != upper(nvl(:old.valore,'x')) and -- se cambia
        upper(nvl(:new.valore,'x')) in ('SI','YES')  and-- se cambia
        upper(nvl(:old.valore,'x')) not  in ('SI','YES')  -- se cambia
        then
         -- rev. 2 inizio
--         global_utility.set_registro_amministratore;  rev.3
         -- rev. 2 fine
         -- da fare solo se esistono moduli con Amministratore = si  o utenti con Amministratore = si
         select count(*)
            into v_num_modu_amm
            from moduli
          where nvl(amministratore,'N') != 'N';

         select count(*)
            into v_num_uten_amm
            from utenti
          where nvl(amministratore,'N') != 'N';
          if v_num_modu_amm > 0 or v_num_uten_amm > 0 then
             d_job :=  job_utility.crea_commit
                ( p_what => 'begin utente.RISISTEMA_DIAC_TUTTI_UTENTI; end;'
                , p_next_date => sysdate
                , p_interval => ''
                , p_broken => 0
                );
            end if;
      null;
    end if;
EXCEPTION
   WHEN OTHERS
   THEN
      raise;
END;
/


