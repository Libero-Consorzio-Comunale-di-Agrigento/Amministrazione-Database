CREATE OR REPLACE package BODY DB_UTILITY
is
 -- Revisione del Package
   s_revisione_body      constant AFC.t_revision := '000 - 15/04/2019';
/******************************************************************************
 NOME:        DB_UTILITY
 DESCRIZIONE: Gestisce oggetti di database
 ARGOMENTI:
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    15/04/2019 SNeg   Prima emissione
******************************************************************************/
function versione
return varchar2 is
/******************************************************************************
 NOME:        versione
 DESCRIZIONE: Versione e revisione di distribuzione del package.
 RITORNA:     varchar2 stringa contenente versione e revisione.
 NOTE:        Primo numero  : versione compatibilità del Package.
              Secondo numero: revisione del Package specification.
              Terzo numero  : revisione del Package body.
******************************************************************************/
begin
   return AFC.version ( s_revisione, s_revisione_body );
end versione; -- anagrafici_pkg.versione
--------------------------------------------------------------------------------
PROCEDURE CREATE_TABELLA (p_nome_oggetto varchar2
                         ,p_statement varchar2)
IS
/******************************************************************************
 NOME:        CREATE_TABELLA
 DESCRIZIONE: Creazione di una tabella se non esistente
 ARGOMENTI:
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    15/04/2019 SNeg   Prima emissione
******************************************************************************/
v_trovato number;
begin
 select count(*)
  into v_trovato
  from user_tables
 where table_name  = upper(p_nome_oggetto);
    if v_trovato = 0 then
    execute immediate (p_statement);
    end if;
end;



PROCEDURE CREATE_SEQUENZA (p_nome_oggetto varchar2
                          ,p_statement varchar2)
IS
/******************************************************************************
 NOME:        CREATE_SEQUENZA
 DESCRIZIONE: Creazione di un oggetto se non esistente
 ARGOMENTI:
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    15/04/2019 SNeg   Prima emissione
******************************************************************************/
v_trovato number;
begin
 select count(*)
  into v_trovato
    from user_sequences
 where sequence_name  = upper(p_nome_oggetto);
    if v_trovato = 0 then
       execute immediate (p_statement);
    end if;
 end;

 PROCEDURE CREATE_INDICE (p_nome_oggetto varchar2
                          ,p_statement varchar2)
IS
/******************************************************************************
 NOME:        CREATE_INDICE
 DESCRIZIONE: Creazione di un oggetto se non esistente
 ARGOMENTI:
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    15/04/2019 SNeg   Prima emissione
******************************************************************************/
v_trovato number;
begin
 select count(*)
  into v_trovato
    from user_indexes
 where index_name  = p_nome_oggetto;
    if v_trovato = 0 then
        execute immediate (p_statement);
    end if;
end;

PROCEDURE CREATE_CONSTRAINT (p_nome_oggetto varchar2
                            ,p_statement varchar2)
IS
/******************************************************************************
 NOME:        CREATE_CONSTRAINT
 DESCRIZIONE: Creazione di un oggetto se non esistente
 ARGOMENTI:
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    15/04/2019 SNeg   Prima emissione
******************************************************************************/
v_trovato number;
begin
 select count(*)
  into v_trovato
  from user_constraints
 where constraint_name  = upper(p_nome_oggetto);
    if v_trovato = 0 then
        execute immediate (p_statement);
    end if;
end;


PROCEDURE ADD_COLUMN (p_nome_oggetto varchar2
                     ,p_nome_colonna varchar2
                     ,p_statement varchar2)
IS
/******************************************************************************
 NOME:        ADD_COLUMN
 DESCRIZIONE: Creazione di un oggetto se non esistente
 ARGOMENTI:
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    15/04/2019 SNeg   Prima emissione
******************************************************************************/
v_trovato number;
begin
 select count(*)
  into v_trovato
  from user_tab_columns
 where table_name = upper(p_nome_oggetto)
   and column_name = upper(p_nome_colonna);
    if v_trovato = 0 then
        execute immediate (p_statement);
    end if;
end;

PROCEDURE INS_DB     (p_statement_controllare varchar2
                     ,p_statement varchar2)
                     IS
/******************************************************************************
 NOME:        INS_DB
 DESCRIZIONE: Inserimento di un record se non esistente
 ARGOMENTI:
 ECCEZIONI:
 ANNOTAZIONI:
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    07/05/2019 SNeg   Prima emissione
******************************************************************************/
v_trovato number;
d_controllo varchar2(32767);
begin
   d_controllo :='begin select count(*) into :v_trovato from ' || p_statement_controllare ||';end;';
   execute immediate d_controllo
      using IN OUT v_trovato;
    if v_trovato = 0 then
        execute immediate (p_statement);
    end if;
end;

END;
/

