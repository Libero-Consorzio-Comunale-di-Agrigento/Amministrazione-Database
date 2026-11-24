--liquibase formatted sql

--changeset mturra:201901301231_165 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY Regione IS
   FUNCTION  VERSIONE /* SLAVE_COPY */
   /******************************************************************************
    NOME:        VERSIONE
    DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
    PARAMETRI:   --
    RITORNA:     stringa varchar2 contenente versione e data.
    NOTE:        Il secondo numero della versione corrisponde alla revisione
                 del package.
   ******************************************************************************/
   RETURN VARCHAR2 IS
   BEGIN
      RETURN '1.1';
   END VERSIONE;
   FUNCTION GET_REGIONE /* SLAVE_COPY */
   ( p_denominazione IN VARCHAR2)
   RETURN NUMBER IS
   /******************************************************************************
    NOME:        GET_REGIONE.
    DESCRIZIONE: Restituisce il codice della REGIONE.
    PARAMETRI:
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM     Prima emissione.
   ******************************************************************************/
   d_ritorno NUMBER;
   BEGIN
      SELECT Regione
        INTO d_ritorno
        FROM REGIONI
         WHERE denominazione = UPPER(p_denominazione)
      ;
      RETURN d_ritorno;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20999,'Regione '||UPPER(p_denominazione)||' non trovata.');
      WHEN OTHERS THEN
         RAISE;
   END GET_REGIONE;
   FUNCTION GET_ID /* SLAVE_COPY */
   /******************************************************************************
    NOME:        GET_ID.
    DESCRIZIONE: Restituisce ID_REGIONE.
    PARAMETRI:
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    01/12/2006 MM     Inclusione nel package.
   ******************************************************************************/
   ( p_denominazione IN VARCHAR2)
   RETURN NUMBER
   IS
      d_ritorno NUMBER;
   BEGIN
      SELECT id_regione
        INTO d_ritorno
        FROM REGIONI
         WHERE denominazione = UPPER(p_denominazione)
      ;
      RETURN d_ritorno;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20999,'Regione '||UPPER(p_denominazione)||' non trovata.');
      WHEN OTHERS THEN
         RAISE;
   END GET_ID;
   FUNCTION Get_Id /* SLAVE_COPY */
   /******************************************************************************
    NOME:        GET_ID
    DESCRIZIONE: Dato un codice regione, ritorna il suo id.
    PARAMETRI:   p_regione number Codice regione.
    RITORNA:     number id_regione
    ECCEZIONI:   -
    ANNOTAZIONI: Salvata nella directory ins di AD4 con nome GET_IDREG.CRF.
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/02/2004 MM     Prima emissione.
   ******************************************************************************/
   ( p_regione NUMBER)
   RETURN NUMBER
   IS
      d_return   NUMBER;
      d_dep      NUMBER := 0;
   BEGIN
      BEGIN
         SELECT 1
          INTO d_dep
           FROM obj
          WHERE object_name = 'COMUNI'
            AND object_type = 'TABLE'
         ;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
           d_dep := 0;
         WHEN OTHERS THEN
           d_dep := -1;
      END;
      IF d_dep = 1 THEN -- CM4
         BEGIN
            SELECT id_regione
              INTO d_return
              FROM REGIONI
             WHERE p_regione = Regione
            ;
         EXCEPTION
            WHEN OTHERS THEN
              NULL;
         END;
      ELSIF d_dep = 0 THEN -- CM3
         BEGIN
            SELECT MAX(Regione)
              INTO d_dep
              FROM REGIONI
            ;
         EXCEPTION
            WHEN OTHERS THEN
              NULL;
         END;
        IF d_dep > 100 THEN
            BEGIN
               SELECT DECODE( Regione, 10, 1, 20, 2, 30, 4, 41, 5, 42, 5, 50, 6, 60, 7, 70, 3, 80, 8
                            , 90, 10, 100, 11, 110, 9, 120, 12, 130, 14, 140, 15, 150, 13, 160, 16
                            , 170, 17, 180, 18, 190, 19, 200, 20)
                 INTO d_return
                 FROM PROVINCE
                WHERE Regione = p_regione
               ;
            EXCEPTION
               WHEN OTHERS THEN
                 NULL;
            END;
         ELSE
           d_return := p_regione;
         END IF;
      END IF;
      RETURN d_return;
   END GET_ID;
   FUNCTION GET_DENOMINAZIONE /* SLAVE_COPY */
   ( p_regione IN NUMBER)
   RETURN VARCHAR2 IS
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE.
    DESCRIZIONE: Restituisce DENOMINAZIONE.
    PARAMETRI:
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM     Prima emissione.
   ******************************************************************************/
   d_ritorno VARCHAR2(40);
   BEGIN
      SELECT denominazione
        INTO d_ritorno
        FROM REGIONI
         WHERE Regione = p_regione
      ;
      RETURN d_ritorno;
   EXCEPTION
      WHEN OTHERS THEN
         RETURN NULL;
   END GET_DENOMINAZIONE;
END Regione;
/

