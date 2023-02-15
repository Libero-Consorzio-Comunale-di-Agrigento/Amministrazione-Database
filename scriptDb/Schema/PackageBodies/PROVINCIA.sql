CREATE OR REPLACE PACKAGE BODY PROVINCIA IS
   FUNCTION  VERSIONE
   /******************************************************************************
    NOME:        VERSIONE
    DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
    PARAMETRI:   --
    RITORNA:     stringa varchar2 contenente versione e data.
    NOTE:        Il secondo numero della versione corrisponde alla revisione
                 del package.
   ******************************************************************************/
   RETURN varchar2 IS /* SLAVE_COPY */
   BEGIN
      RETURN '1.1';
   END VERSIONE;
   Function GET_PROVINCIA
   ( p_denominazione IN varchar2
   , p_sigla IN varchar2 default null)
   RETURN province.provincia%TYPE IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        GET_PROVINCIA.
    DESCRIZIONE: Restituisce il codice della provincia
    PARAMETRI:   p_denominazione denominazione della provincia.
                 p_sigla         sigla della provincia.
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM    Prima emissione.
   ******************************************************************************/
   d_ritorno province.provincia%TYPE;
   d_err     varchar2(100);
   BEGIN
      d_err := upper(p_denominazione);
     if d_err is null then
         d_err := upper(p_sigla);
      elsif upper(p_sigla) is not null then
         d_err := d_err || ' - '||upper(p_sigla);
      end if;
      select provincia
        into d_ritorno
        from province
       where denominazione = upper(nvl(p_denominazione,denominazione))
         and sigla = upper(nvl(p_sigla,sigla))
      ;
      return d_ritorno;
   EXCEPTION
      WHEN NO_DATA_FOUND then
        raise_application_error(-20999,'Provincia '||d_err||' non trovata.');
      WHEN OTHERS then
         raise;
   END GET_PROVINCIA;
   Function GET_REGIONE
   ( p_denominazione IN varchar2)
   RETURN province.regione%TYPE
   IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        GET_REGIONE.
    DESCRIZIONE: Restituisce il codice della regione
    PARAMETRI:   Denominazione Della Provincia.
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM    Prima emissione.
   ******************************************************************************/
   d_ritorno province.regione%TYPE;
   BEGIN
      select regione
        into d_ritorno
        from province
         where denominazione = upper(p_denominazione)
      ;
      return d_ritorno;
   EXCEPTION
      WHEN NO_DATA_FOUND then
        raise_application_error(-20999,'Provincia '||upper(p_denominazione)||' non trovata.');
      WHEN OTHERS then
         raise;
   END GET_REGIONE;
   Function GET_REGIONE
   ( p_provincia IN number)
   RETURN province.regione%TYPE IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        GET_REGIONE.
    DESCRIZIONE: Restituisce il codice della regione
    PARAMETRI:   Chiave.
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM    Prima emissione.
   ******************************************************************************/
   d_ritorno province.regione%TYPE;
   BEGIN
      select regione
        into d_ritorno
        from province
       where provincia = p_provincia
      ;
      return d_ritorno;
   END GET_REGIONE;
   Function GET_DENOMINAZIONE
   ( p_provincia IN number)
   RETURN province.DENOMINAZIONE%TYPE IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE.
    DESCRIZIONE: Restituisce DENOMINAZIONE della provincia
    PARAMETRI:   Chiave.
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM    Prima emissione.
   ******************************************************************************/
   d_ritorno province.DENOMINAZIONE%TYPE;
   BEGIN
      select denominazione
        into d_ritorno
        from province
       where provincia = p_provincia
      ;
      return d_ritorno;
   END GET_DENOMINAZIONE;
   Function GET_DENOMINAZIONE_AL1
   ( p_provincia IN number)
   RETURN province.DENOMINAZIONE_AL1%TYPE IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_AL1.
    DESCRIZIONE: Restituisce prima DENOMINAZIONE alternativa della provincia.
    PARAMETRI:   Chiave.
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM    Prima emissione.
   ******************************************************************************/
   d_ritorno province.DENOMINAZIONE_AL1%TYPE;
   BEGIN
      select denominazione_al1
        into d_ritorno
        from province
       where provincia = p_provincia
      ;
      return d_ritorno;
   END GET_DENOMINAZIONE_AL1;
   Function GET_DENOMINAZIONE_AL2
   ( p_provincia IN number)
   RETURN province.DENOMINAZIONE_AL2%TYPE IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_AL1.
    DESCRIZIONE: Restituisce seconda DENOMINAZIONE alternativa della provincia.
    PARAMETRI:   Chiave.
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM    Prima emissione.
   ******************************************************************************/
   d_ritorno province.DENOMINAZIONE_AL2%TYPE;
   BEGIN
      select denominazione_al2
        into d_ritorno
        from province
       where provincia = p_provincia
      ;
      return d_ritorno;
   END GET_DENOMINAZIONE_AL2;
   Function GET_SIGLA
   ( p_provincia IN number)
   RETURN province.SIGLA%TYPE IS /* SLAVE_COPY */
   /******************************************************************************
    NOME:        GET_SIGLA.
    DESCRIZIONE: Restituisce SIGLA della provincia
    PARAMETRI:   Chiave.
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM    Prima emissione.
   ******************************************************************************/
   d_ritorno province.SIGLA%TYPE;
   BEGIN
      select SIGLA
        into d_ritorno
        from province
       where provincia = p_provincia
      ;
      return d_ritorno;
   END GET_SIGLA;
END PROVINCIA;
/

