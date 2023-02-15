CREATE OR REPLACE FUNCTION IS_VERSIONE_MAGGIORE (
    p_versione_controllare   VARCHAR2,
    p_versione_riferimento   VARCHAR2,
    p_uguale                 NUMBER DEFAULT 1)
    RETURN NUMBER
IS
    /******************************************************************************
     NOME:        IS_VERSIONE_MAGGIORE
     DESCRIZIONE: Function per confronto fra versioni con notazione puntata es.4.5.15
     ARGOMENTI:   p_versione_controllare: versione che si vuole controllare
                  p_versione_riferimento: versione presente (es. sul db)
                  p_uguale: se il confronto ha successo anche se versione uguale
     RITORNA:     number: 1: versione maggiore
                          1: versione = e parametro p_uguale = 1
                          0: versione inferiore
     ECCEZIONI:  Errore se una delle istanze inizia con @ ossia in stato degradato
     ANNOTAZIONI:
     REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
       0  24/07/2019     SN Prima Emissione
    ******************************************************************************/
    v_versione_controllare   VARCHAR2 (20)
                                 := LTRIM (p_versione_controllare, 'V');
    v_versione_riferimento   VARCHAR2 (20)
                                 := LTRIM (p_versione_riferimento, 'V');
    v_numero_controllare     NUMBER;
    v_numero_riferimento     NUMBER;
    v_maggiore_contr         INTEGER := 0;
    v_uguale_contr           INTEGER := 0;
    v_minore_contr           INTEGER := 0;
    v_controllare_maggiore   INTEGER := 0;
    function afc_get_substr
        ( p_stringa    IN OUT varchar2
        , p_separatore IN     varchar2
        ) return VARCHAR2 is
           sStringa     VARCHAR2(32000);
           iPos         INTEGER;
        begin
           iPos := instr(p_stringa,p_separatore);
           if iPos = 0 then
              sStringa  := p_stringa;
              p_stringa := '';
           else
              sStringa  := substr(p_stringa,1,iPos - 1);
              p_stringa := substr(p_stringa,iPos+length(p_separatore));
           end if;
           RETURN sStringa;
        end afc_get_substr;
BEGIN
    IF INSTR (p_versione_controllare, '@') > 0
    THEN
        raise_application_error (
            -20999,
               'Attenzione istanza da controllare '
            || p_versione_controllare
            || ' in stato degradato. Impossibile continuare');
    END IF;

    IF INSTR (p_versione_riferimento, '@') > 0
    THEN
        raise_application_error (
            -20999,
               'Attenzione istanza di riferimento '
            || p_versione_riferimento
            || ' in stato degradato. Impossibile continuare');
    END IF;

    WHILE     v_versione_controllare IS NOT NULL
          AND v_versione_riferimento IS NOT NULL
          AND v_maggiore_contr = 0
          AND v_minore_contr = 0
    LOOP
        v_numero_controllare :=
            TO_NUMBER (afc_get_substr (v_versione_controllare, '.'));
        v_numero_riferimento :=
            TO_NUMBER (afc_get_substr (v_versione_riferimento, '.'));

        IF v_numero_controllare > v_numero_riferimento
        THEN
            v_maggiore_contr := 1;
        ELSIF v_numero_controllare = v_numero_riferimento
        THEN
            v_uguale_contr := 1;
        ELSIF v_numero_controllare < v_numero_riferimento
        THEN
            v_minore_contr := 1;
        END IF;
    END LOOP;

    IF v_maggiore_contr = 1
    THEN                                                       -- era maggiore
        v_controllare_maggiore := 1;
    END IF;

    IF v_minore_contr = 1
    THEN                                                         -- era minore
        v_controllare_maggiore := 0;
    END IF;

    IF     v_versione_controllare IS NULL
       AND v_versione_riferimento IS NULL
       AND p_uguale = 1
       AND v_uguale_contr = 1
       AND v_maggiore_contr = 0
       AND v_minore_contr = 0
    THEN                                            -- vanno bene anche uguali
        v_controllare_maggiore := 1;
    END IF;

    IF     v_versione_controllare IS NOT NULL
       AND v_versione_riferimento IS NULL
       AND v_uguale_contr = 1
       AND v_maggiore_contr = 0
       AND v_minore_contr = 0
    THEN
        -- erano uguali ma più lungo il controllare
        v_controllare_maggiore := 1;
    END IF;

    IF     v_versione_controllare IS NULL
       AND v_versione_riferimento IS NOT NULL
       AND v_uguale_contr = 1
       AND v_maggiore_contr = 0
       AND v_minore_contr = 0
    THEN
        -- erano uguali ma più lungo il confrontare
        v_controllare_maggiore := 0;
    END IF;

    RETURN v_controllare_maggiore;
END;
/

