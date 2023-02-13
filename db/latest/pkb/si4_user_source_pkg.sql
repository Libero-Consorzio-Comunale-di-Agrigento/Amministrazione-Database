--liquibase formatted sql

--changeset mturra:201901301231_179 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY SI4_USER_SOURCE_PKG
IS
/******************************************************************************
 NOME:        SI4_USER_SOURCE_PKG
 DESCRIZIONE: Package di gestione SI4_USER_SOURCE.
 ANNOTAZIONI: .
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  23/08/2007 MM     Creazione.
******************************************************************************/
   s_revisione_body   constant AFC.t_revision := '000';
   TYPE param IS RECORD (
      NAME    VARCHAR2 (4000)
    , VALUE   VARCHAR2 (4000)
   );
   TYPE tabella_param IS TABLE OF param
      INDEX BY BINARY_INTEGER;
   v_tabella_param   tabella_param;
   function versione
      return varchar2
   is
/******************************************************************************
 NOME:        versione
 DESCRIZIONE: Versione e revisione di distribuzione del package.
 RITORNA:     varchar2 stringa contenente versione e revisione.
 NOTE:        Primo numero  : versione compatibilita del Package.
              Secondo numero: revisione del Package specification.
              Terzo numero  : revisione del Package body.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  23/08/2007 MM     Prima emissione.
******************************************************************************/
   begin
      return afc.VERSION(s_revisione, s_revisione_body);
   end versione;
   procedure addParm
   ( p_name varchar2
   , p_value varchar2)
/******************************************************************************
 NOME:        addParm
 DESCRIZIONE: Aggiunge il parametro ed il relativo valore alla tabella dei
              parametri.
              Verranno utilizzati dalla procedure EXECUTE per l'esecuzione del
              sorgente PL/Sql memorizzato in SI4_USER_SOURCE.
              La tabella dei parametri viene svuotata nella procedure EXECUTE.
 ARGOMENTI:   p_name          IN varchar2 nome del parametro.
              p_value         IN varchar2 valore del parametro.
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    01/07/2008 MM     Prima emissione.
******************************************************************************/
   is
   begin
      v_tabella_param((v_tabella_param.count) + 1).name := p_name;
      v_tabella_param((v_tabella_param.count)).value := p_value;
   end;
   PROCEDURE execute
   ( p_name in SI4_USER_SOURCE.NAME%TYPE
   , p_type in SI4_USER_SOURCE.TYPE%TYPE
   , p_ignore_exists in NUMBER DEFAULT 0
   , p_prefix in varchar2 default null
   )
/******************************************************************************
 NOME:        execute
 DESCRIZIONE: Esegue il sorgente PL/Sql memorizzato in SI4_USER_SOURCE
              sostituendo eventuali parametri presenti nella tabella dei
              parametri.
 ARGOMENTI:   p_name          IN varchar2 nome del sorgente da eseguire.
              p_type          IN varchar2 tipo del sorgente da eseguire.
              p_ignore_exists IN number   indica se ignorare l'errore indicante
                                          l'esistenza dell'oggetto.
              p_prefix        IN varchar2 eventuale prefisso da anteporre alla
                                          tabella SI4_USER_SOURCE (serve se la
                                          non è proprietaria ma di un altro user
                                          quindi si accede via sinonimo).
 ECCEZIONI:   -
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    23/08/2007 MM     Prima emissione.
******************************************************************************/
   is
      d_name SI4_USER_SOURCE.NAME%TYPE;
      d_type SI4_USER_SOURCE.TYPE%TYPE;
      d_text clob;
      d_suso_table varchar2(30) := 'SI4_USER_SOURCE';
      d_prefix varchar2(15) := p_prefix;
   begin
      d_name := upper(p_name);
      d_type := upper(p_type);
      begin
         if d_prefix is not null
         then
            if substr(d_prefix, length(d_prefix)) <> '_'
            then
               d_prefix := d_prefix||'_';
            end if;
            d_suso_table := d_prefix||d_suso_table;
         end if;
         execute immediate 'select text '||
                           '  from '|| d_suso_table ||
                           ' where name = '''|| d_name ||''''||
                           '   and type = nvl('''|| d_type||''', type)'
            into d_text
         ;
      exception
         when no_data_found
         then
            raise_application_error(-20999, 'Oggetto  '''||d_name||''' di tipo '||d_type||' non esistente!');
         when too_many_rows
         then
            raise_application_error(-20999, 'Esistono piu'' oggetti con nome '''||d_name||'''. Specificare il tipo!');
         when others
         then
            raise;
      end;
      FOR i IN 1 .. v_tabella_param.COUNT
      LOOP
         d_text := afc_lob.replace_clob(d_text, v_tabella_param (i).NAME, v_tabella_param (i).VALUE);
      END LOOP;
      v_tabella_param.delete;
      begin
         afc_lob.sql_execute(d_text);
      exception
         when others
         then
            if p_ignore_exists = 1
            then
               declare
                  d_type varchar2(30);
               begin
                  begin
                     select object_type
                       into d_type
                       from user_objects
                      where object_name = d_name
                        and object_type = p_type
                     ;
                  exception
                     when no_data_found
                     then
                        d_type := null;
                  end;
                  if p_type IN ('TABLE', 'SEQUENCE', 'VIEW', 'PROCEDURE', 'FUNCTION', 'PACKAGE', 'PACKAGE BODY')
                  then
                     if sqlcode <> -955 -- name is already used by an existing object
                     or p_type <> nvl(d_type, 'xxxxxxxxx')
                     then
                        raise;
                     end if;
                  elsif p_type = 'INDEX'
                  then
                     if sqlcode <> -955 -- name is already used by an existing object
                     then
                        raise;
                     end if;
                  elsif p_type = 'TRIGGER'
                  then
                     if sqlcode <> -4081 -- trigger already exists
                     or p_type <> nvl(d_type, 'xxxxxxxxx')
                     then
                        raise;
                     end if;
                  elsif p_type = 'PK CONSTRAINT'
                  then
                     if sqlcode <> -2260 -- table can have only one primary key
                     then
                        raise;
                     end if;
                  elsif p_type = 'UNIQUE CONSTRAINT'
                  then
                     if sqlcode <> -2261 --such unique or primary key already exists in the table
                     then
                        raise;
                     end if;
                  elsif d_type = 'CHECK CONSTRAINT'
                  then
                     if sqlcode <> -2264 -- name already used by an existing constraint
                     then
                        raise;
                     end if;
                  elsif p_type = 'FK CONSTRAINT'
                  then
                     if sqlcode <> -2275 --such a referential constraint already exists in the table
                     then
                        raise;
                     end if;
                  end if;
               end;
            else
               raise;
            end if;
      end;
   exception
      when others
      then
         raise;
   end execute;
END SI4_USER_SOURCE_PKG;
/

