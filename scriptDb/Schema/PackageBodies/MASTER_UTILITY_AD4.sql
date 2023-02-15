CREATE OR REPLACE PACKAGE BODY Master_Utility_AD4 IS
FUNCTION VERSIONE  RETURN VARCHAR2 IS
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
******************************************************************************/
BEGIN
   RETURN 'V1.1 del 11/01/2010';
END VERSIONE;
PROCEDURE REFRESH_SLAVES
IS
   PRAGMA AUTONOMOUS_TRANSACTION;
   TYPE t_elenco IS TABLE OF VARCHAR2 (30)
      INDEX BY BINARY_INTEGER;
   d_db_link varchar2(200);
   v_elenco    t_elenco;
   d_link_oracle                         varchar2(2000);
BEGIN
   v_elenco (1) := 'ISTANZE';
   v_elenco (2) := 'PROGETTI';
   v_elenco (3) := 'MODULI';
   v_elenco (4) := 'RUOLI';
   v_elenco (5) := 'UTENTI_SLAVE';
   open master_utility.c_slaves('AD4', 0);
   fetch master_utility.c_slaves into d_db_link, d_link_oracle;
   while master_utility.c_slaves%found loop
      FOR i IN 1 .. v_elenco.COUNT
      LOOP
         begin
            EXECUTE IMMEDIATE (   'begin DBMS_SNAPSHOT.REFRESH@'
                                  || d_db_link
                                  || '('''
                                  || v_elenco (i)
                                  || ''');end;'
                                 );
         exception
            when others then
               null;
         end;
         commit;
         fetch master_utility.c_slaves into d_db_link,d_link_oracle;
      end loop;
   END LOOP;
   close master_utility.c_slaves;
   commit;
exception
   when others then
      if master_utility.c_slaves%ISOPEN then
         close master_utility.c_slaves;
      end if;
      rollback;
      raise;
END;
PROCEDURE REFRESH_SLAVES (
   p_refresh_group in varchar2
)
IS
   PRAGMA AUTONOMOUS_TRANSACTION;
   d_db_link varchar2(200);
   d_link_oracle                         varchar2(2000);
BEGIN
   open master_utility.c_slaves('AD4', 0);
   fetch master_utility.c_slaves into d_db_link,d_link_oracle;
   while master_utility.c_slaves%found loop
      begin
         EXECUTE IMMEDIATE (   'begin DBMS_REFRESH.REFRESH@'
                               || d_db_link
                               || '('''
                               || p_refresh_group
                               || ''');end;'
                              );
      exception
         when others then
            null;
      end;
      fetch master_utility.c_slaves into d_db_link,d_link_oracle;
   end loop;
   close master_utility.c_slaves;
   commit;
exception
   when others then
      if master_utility.c_slaves%ISOPEN then
         close master_utility.c_slaves;
      end if;
      rollback;
      raise;
END;
PROCEDURE CHECK_SLAVES
IS
   PRAGMA AUTONOMOUS_TRANSACTION;
   d_statement                           varchar2(4000);
   d_check                               NUMBER;
   d_stato                               varchar2(1) := 'A';
   d_db_link                             varchar2(200);
   d_link_oracle                         varchar2(2000);
BEGIN
   open master_utility.c_slaves('AD4', 0);
   fetch master_utility.c_slaves into d_db_link, d_link_oracle;
   while master_utility.c_slaves%found loop
      BEGIN
         begin
            insert into slaves(db_link, link_oracle)
            values (d_db_link, d_link_oracle)
            ;
         exception
            when others then
               null;
         end;
         d_statement := 'CALL PROGETTI_TPK.EXISTS_ID@'
                        || d_db_link
                        || '(''AD4'') INTO :d_check'
                        ;
         EXECUTE IMMEDIATE d_statement USING OUT d_check;
      EXCEPTION
         WHEN OTHERS THEN
            d_stato := 'D';
      END;
      if d_stato = 'A' then
         begin
            EXECUTE IMMEDIATE 'BEGIN DBMS_SNAPSHOT.REFRESH@'||d_db_link||'(LIST => ''SLAVES''); END;';
         exception when others then
            null;
         end;
      end if;
      update slaves
         set stato = d_stato
       where db_link = d_db_link
      ;
      fetch master_utility.c_slaves into d_db_link,d_link_oracle;
   end loop;
   close master_utility.c_slaves;
   commit;
exception
   when others then
      if master_utility.c_slaves%ISOPEN then
         close master_utility.c_slaves;
      end if;
      rollback;
      raise;
END;
PROCEDURE REFRESH_SLAVE
(p_db_link varchar2)
IS /* SLAVE_COPY */
   PRAGMA AUTONOMOUS_TRANSACTION;
   d_statement                           varchar2(4000);
   d_check_is_slave                      NUMBER;
   d_stato                               varchar2(1) := 'A';
   d_db_link                             varchar2(200);
   d_link_oracle                         varchar2(2000);
BEGIN
   select count(1)
     into d_check_is_slave
     from slaves
    where db_link = p_db_link
   ;
   if d_check_is_slave > 0 then
      d_statement := 'begin dbms_refresh.refresh(''"AS4"."AS4G"''); end;';
      integritypackage.LOG(d_statement);
      execute immediate d_statement;
   end if;
   begin
      select upper(link_oracle)
        into d_link_oracle
        from istanze
       where progetto = 'SI4SO'
         and instr('.'||installazione||'.', '.MASTER.') > 0
      ;
      begin
         select db_link
           into d_db_link
           from slaves
          where upper(link_oracle) = d_link_oracle
         ;
      exception
         when no_data_found then
            d_db_link := 'NO';
      end;
      if nvl(p_db_link, 'NO') <> d_db_link then
         d_statement := 'begin dbms_refresh.refresh(''"SO4"."SO4G"''); end;';
         integritypackage.LOG(d_statement);
         execute immediate d_statement;
      end if;
   exception
      when no_data_found then
         -- non esiste master di SO4.
         null;
   end;
   if d_check_is_slave > 0 then
      d_statement := 'begin dbms_refresh.refresh(''"AD4"."UTENTIG"''); end;';
      integritypackage.LOG(d_statement);
      execute immediate d_statement;
      d_statement := 'begin dbms_refresh.refresh(''"AD4"."AD4G"''); end;';
      integritypackage.LOG(d_statement);
      execute immediate d_statement;
   end if;
   if nvl(p_db_link, 'NO') = 'NO' then -- siamo sul master
      d_statement := 'begin ldap_utility.allinea_ldap(); end;';
      integritypackage.LOG(d_statement);
      execute immediate d_statement;
   end if;
   commit;
exception
   when others then
      declare
         d_err_text varchar2(2000);
      begin
         d_err_text := substr(sqlerrm, 1, 1950);
         rollback;
         key_error_log_tpk.ins(p_error_date => sysdate, p_error_text => 'Refresh '''||nvl(p_db_link, 'MASTER')||''' fallito:'||d_err_text);
         commit;
      end;
END;
PROCEDURE REFRESH_ALL_SLAVES
IS
   PRAGMA AUTONOMOUS_TRANSACTION;
   d_statement                           varchar2(4000);
   d_check                               NUMBER;
   d_stato                               varchar2(1) := 'A';
   d_db_link                             varchar2(200);
   d_link_oracle                         varchar2(2000);
BEGIN
   open master_utility.c_slaves('AD4');
   fetch master_utility.c_slaves into d_db_link, d_link_oracle;
   while master_utility.c_slaves%found loop
      BEGIN
         d_statement := 'CALL job_utility.crea_commit@'
                        || d_db_link
                        || '(''begin master_utility_ad4.refresh_slave('''''||d_db_link||'''''); end;'', sysdate + 3/1440) into :d_check'
                        ;
         integritypackage.LOG(d_statement);
         EXECUTE IMMEDIATE d_statement USING OUT d_check;
      EXCEPTION
         WHEN OTHERS THEN
            close master_utility.c_slaves;
            raise;
      END;
      fetch master_utility.c_slaves into d_db_link,d_link_oracle;
   end loop;
   close master_utility.c_slaves;
   commit;
   d_check := job_utility.crea_commit('begin master_utility_ad4.refresh_slave(''''); end;', sysdate + 3/1440);
exception
   when others then
      rollback;
      if master_utility.c_slaves%ISOPEN then
         close master_utility.c_slaves;
      end if;
      raise;
END;
end MASTER_UTILITY_AD4;
/

