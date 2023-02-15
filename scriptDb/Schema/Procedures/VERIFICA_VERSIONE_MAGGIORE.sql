CREATE OR REPLACE procedure verifica_versione_maggiore(p_ver_controllare varchar2,
                                                       p_ver_richiesta varchar2)
IS
   BEGIN
      if instr(p_ver_controllare,'@') >0 then
         raise_application_error (-20999, 'Attenzione istanza &1 in stato degradato. Impossibile continuare');
      end if;
      if is_versione_maggiore(ltrim(ltrim(p_ver_controllare,'V'),'@'),p_ver_richiesta) = 0 then
         raise_application_error (-20999, p_ver_controllare || ' deve essere superiore a '|| p_ver_richiesta);
      end if;
   END;
/

