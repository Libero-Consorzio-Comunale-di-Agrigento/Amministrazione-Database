--liquibase formatted sql

--changeset mturra:201901301231_126 runOnChange:true stripComments:false


CREATE OR REPLACE PACKAGE BODY COMUNE IS
   FUNCTION  VERSIONE
   /******************************************************************************
    NOME:        VERSIONE
    DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
    PARAMETRI:   --
    RITORNA:     stringa varchar2 contenente versione e data.
    NOTE:        Il secondo numero della versione corrisponde alla revisione
                 del package.
   ******************************************************************************/
   RETURN varchar2 IS
   BEGIN
      RETURN '1.3';
   END VERSIONE;
   Function GET_DENOMINAZIONE
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE%TYPE IS
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE.
    DESCRIZIONE: Restituisce DENOMINAZIONE del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Denominazione del comune identificato dalla chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
      d_ritorno COMUNI.DENOMINAZIONE%TYPE;
   BEGIN
      SELECT DENOMINAZIONE
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_DENOMINAZIONE;
   Function GET_DENOMINAZIONE_AL1
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE_AL1%TYPE IS
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_AL1.
    DESCRIZIONE: Restituisce prima DENOMINAZIONE alternativa del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Prima Denominazione alternativa del comune identificato dalla
                 chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
      d_ritorno COMUNI.DENOMINAZIONE_AL1%TYPE;
   BEGIN
      SELECT DENOMINAZIONE_AL1
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_DENOMINAZIONE_AL1;
   Function GET_DENOMINAZIONE_AL2
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE_AL2%TYPE IS
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_AL2.
    DESCRIZIONE: Restituisce seconda DENOMINAZIONE alternativa del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Seconda Denominazione alternativa del comune identificato dalla
                 chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
      d_ritorno COMUNI.DENOMINAZIONE_AL2%TYPE;
   BEGIN
      SELECT DENOMINAZIONE_AL2
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_DENOMINAZIONE_AL2;
   Function GET_DENOMINAZIONE_BREVE
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE_BREVE%TYPE IS
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_BREVE.
    DESCRIZIONE: Restituisce DENOMINAZIONE_BREVE del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Denominazione breve del comune identificato dalla chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
      d_ritorno COMUNI.DENOMINAZIONE_BREVE%TYPE;
   BEGIN
      SELECT DENOMINAZIONE_BREVE
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_DENOMINAZIONE_BREVE;
   Function GET_DENOMINAZIONE_BREVE_AL1
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE_BREVE_AL1%TYPE IS
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_BREVE_AL1.
    DESCRIZIONE: Restituisce prima DENOMINAZIONE_BREVE alternativa del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Prima denominazione breve alternativa del comune identificato
                 dalla chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
      d_ritorno COMUNI.DENOMINAZIONE_BREVE_AL1%TYPE;
   BEGIN
      SELECT DENOMINAZIONE_BREVE_AL1
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_DENOMINAZIONE_BREVE_AL1;
   Function GET_DENOMINAZIONE_BREVE_AL2
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.DENOMINAZIONE_BREVE_AL2%TYPE IS
   /******************************************************************************
    NOME:        GET_DENOMINAZIONE_BREVE_AL2.
    DESCRIZIONE: Restituisce seconda DENOMINAZIONE_BREVE alternativa del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Seconda denominazione breve alternativa del comune identificato
                 dalla chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
      d_ritorno COMUNI.DENOMINAZIONE_BREVE_AL2%TYPE;
   BEGIN
      SELECT DENOMINAZIONE_BREVE_AL2
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_DENOMINAZIONE_BREVE_AL2;
   Function IS_CAPOLUOGO_PROVINCIA
   ( p_provincia IN number
   , p_comune IN number)
   RETURN number IS
   /******************************************************************************
    NOME:        IS_CAPOLUOGO_PROVINCIA.
    DESCRIZIONE: Restituisce 1 se il comune e' capoluogo di provincia, 0 se non lo e'.
    PARAMETRI:   Campi chiave.
    RITORNA:     NUMBER 1/0.
                 Se la select non trova record, ritorna NULL.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
      d_ritorno NUMBER;
   BEGIN
      SELECT DECODE(NVL(CAPOLUOGO_PROVINCIA,'N'),'S',1,0)
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END IS_CAPOLUOGO_PROVINCIA;
   Function GET_CAP
   ( p_provincia IN number
   , p_comune IN number)
   RETURN varchar2 IS
   /******************************************************************************
    NOME:        GET_CAP.
    DESCRIZIONE: Restituisce CAP del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     CAP del comune identificato dalla chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
   d_ritorno VARCHAR2(5);
   BEGIN
      SELECT LPAD(CAP,5,'0')
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_CAP;
   Procedure GET_COMUNE_TRIBUNALE
   ( p_provincia IN number
   , p_comune IN number
   , p_provincia_tribunale IN OUT number
   , p_comune_tribunale IN OUT number)
   IS
   /******************************************************************************
    NOME:         GET_COMUNE_TRIBUNALE.
    DESCRIZIONE: Seleziona il comune in cui ha sede il tribunale competente.
    ARGOMENTI:   Campi chiave.
                 p_provincia_tribunale OUT codice provincia in cui ha sede il tribunale
                 p_comune_tribunale    OUT codice comune in cui ha sede il tribunale
    RITORNA:     Denominazione del comune identificato dalla chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
   BEGIN
      SELECT provincia_tribunale, comune_tribunale
        INTO p_provincia_tribunale, p_comune_tribunale
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
   END GET_COMUNE_TRIBUNALE;
   Procedure GET_COMUNE_DISTRETTO
   ( p_provincia IN number
   , p_comune IN number
   , p_provincia_distretto IN OUT number
   , p_comune_distretto IN OUT number)
   IS
   /******************************************************************************
    NOME:        GET_COMUNE_DISTRETTO.
    DESCRIZIONE: Seleziona il comune in cui ha sede il distretto.
    ARGOMENTI:   Campi chiave.
                 p_provincia_distretto OUT codice provincia in cui ha sede il distretto
                 p_comune_distretto    OUT codice comune in cui ha sede il distretto
    RITORNA:     Denominazione del comune identificato dalla chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
   BEGIN
      SELECT provincia_distretto, comune_distretto
        INTO p_provincia_distretto, p_comune_distretto
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
   END GET_COMUNE_DISTRETTO;
   Function GET_DATA_SOPPRESSIONE
   ( p_provincia IN number
   , p_comune IN number)
   RETURN varchar2 IS
   /******************************************************************************
    NOME:        GET_DATA_SOPPRESSIONE.
    DESCRIZIONE: Restituisce eventuale data di soppressione del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Data di soppressione del comune identificato dalla chiave in
                 formato dd/mm/yyyy.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    3    10/02/2020  SNeg  Gestione nuova colonna data_istituzione Prima emissione.
   ******************************************************************************/
   d_ritorno VARCHAR2(10);
   BEGIN
      SELECT to_char(data_soppressione,'dd/mm/yyyy')
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_DATA_SOPPRESSIONE;

   Function GET_DATA_ISTITUZIONE
   ( p_provincia IN number
   , p_comune IN number)
   RETURN varchar2 IS
   /******************************************************************************
    NOME:        GET_DATA_ISTITUZIONE.
    DESCRIZIONE: Restituisce eventuale data di istituzione del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     Data di istituzione del comune identificato dalla chiave in
                 formato dd/mm/yyyy.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
   d_ritorno VARCHAR2(10);
   BEGIN
      SELECT to_char(data_soppressione,'dd/mm/yyyy')
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_DATA_ISTITUZIONE;

   Procedure GET_COMUNE_FUSIONE
   ( p_provincia IN number
   , p_comune IN number
   , p_provincia_fusione IN OUT number
   , p_comune_fusione IN OUT number)
   IS
   /******************************************************************************
    NOME:        GET_COMUNE_FUSIONE.
    DESCRIZIONE: Seleziona il comune in cui ha e' confluito.
    ARGOMENTI:   Campi chiave.
                 p_provincia_fusione OUT codice provincia in cui e' confluito
                 p_comune_fusione    OUT codice comune in cui e' confluito
    RITORNA:     Denominazione del comune identificato dalla chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
   BEGIN
      SELECT provincia_fusione, comune_fusione
        INTO p_provincia_fusione, p_comune_fusione
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
   END GET_COMUNE_FUSIONE;
   Function IS_SOPPRESSO
   ( p_provincia IN number
   , p_comune IN number)
   RETURN number IS
   /******************************************************************************
    NOME:        IS_SOPPRESSO.
    DESCRIZIONE: Restituisce 1 se il comune e soppresso, 0 se non lo e'.
    PARAMETRI:   Campi chiave.
    RITORNA:     NUMBER 1/0.
                 Se la select non trova record, ritorna NULL.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
      d_ritorno NUMBER;
   BEGIN
      select decode(data_soppressione, null, decode(provincia_fusione, null, 0, decode(comune_fusione, null, 0, 1)),1)
        into d_ritorno
        from comuni comu
       where comune = p_comune
         and provincia_stato = p_provincia
      ;
      return d_ritorno;
   END IS_SOPPRESSO;
   Function GET_SIGLA_CFIS
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.SIGLA_CFIS%TYPE IS
   /******************************************************************************
    NOME:        GET_SIGLA_CFIS.
    DESCRIZIONE: Restituisce SIGLA_CFIS del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     SIGLA_CFIS del comune identificato dalla chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
      d_ritorno COMUNI.SIGLA_CFIS%TYPE;
   BEGIN
      SELECT SIGLA_CFIS
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_SIGLA_CFIS;
   Function GET_CONSOLATO
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.CONSOLATO%TYPE IS
   /******************************************************************************
    NOME:        GET_CONSOLATO.
    DESCRIZIONE: Restituisce CONSOLATO del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     CONSOLATO del comune identificato dalla chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
      d_ritorno COMUNI.CONSOLATO%TYPE;
   BEGIN
      SELECT CONSOLATO
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_CONSOLATO;
   Function GET_TIPO_CONSOLATO
   ( p_provincia IN number
   , p_comune IN number)
   RETURN COMUNI.TIPO_CONSOLATO%TYPE IS
   /******************************************************************************
    NOME:        GET_TIPO_CONSOLATO.
    DESCRIZIONE: Restituisce TIPO_CONSOLATO del comune.
    PARAMETRI:   Campi chiave.
    RITORNA:     TIPO_CONSOLATO del comune identificato dalla chiave.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    02/05/2007 MM     Prima emissione.
   ******************************************************************************/
      d_ritorno COMUNI.TIPO_CONSOLATO%TYPE;
   BEGIN
      SELECT TIPO_CONSOLATO
        INTO d_ritorno
        FROM COMUNI
       WHERE PROVINCIA_STATO = p_provincia
         AND COMUNE = p_comune
      ;
      return d_ritorno;
   END GET_TIPO_CONSOLATO;
   Procedure GET_DATI
   ( p_denominazione IN varchar2
   , p_sigla_provincia IN varchar2
   , p_comune IN OUT number
   , p_provincia IN OUT number
   , p_cap IN OUT number
   , p_cfis IN OUT varchar2
   , p_soppresso IN OUT number) IS
   /******************************************************************************
    NOME:        GET_DATI.
    DESCRIZIONE: Restituisce il codice del COMUNE.
    PARAMETRI:
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM     Prima emissione.
   ******************************************************************************/
   d_err varchar2(100);
   BEGIN
      d_err:= ' con denominazione '||upper(p_denominazione);
      if p_sigla_provincia is not null then
         d_err:= d_err||' e sigla provincia '||p_sigla_provincia;
      end if;
      d_err:= d_err||'.';
      if p_sigla_provincia is not null then
         p_provincia := provincia.get_provincia('',p_sigla_provincia);
      end if;
      if nvl(p_soppresso,0) = 0 then
         BEGIN
            select comu.comune, comu.provincia_stato, comu.cap, comu.sigla_cfis
              into p_comune, p_provincia, p_cap, p_cfis
              from comuni comu
                 , province prov
             where comu.denominazione = upper(p_denominazione)
               and comu.provincia_stato = nvl(p_provincia, comu.provincia_stato)
               and comu.comune = nvl(p_comune, comu.comune)
               and comu.provincia_stato = prov.provincia(+)
               and comu.data_soppressione is null
               and comu.provincia_fusione is null
               and comu.comune_fusione is null
            ;
            p_soppresso := 0;
         EXCEPTION
            WHEN NO_DATA_FOUND THEN
               if p_soppresso is null then
                  p_soppresso := 1;
               else
                  d_err:= 'Non esiste comune'||d_err;
                  raise_application_error(-20999,d_err);
               end if;
            WHEN TOO_MANY_ROWS THEN
               d_err:= 'Esistono piu'' comuni'||d_err;
               raise_application_error(-20999,d_err);
            WHEN OTHERS THEN
               raise;
         END;
      end if;
      if nvl(p_soppresso,0) = 1 then
         BEGIN
            select comu.comune, comu.provincia_stato, comu.cap, comu.sigla_cfis
              into p_comune, p_provincia, p_cap, p_cfis
              from comuni comu
                 , province prov
             where comu.denominazione = upper(p_denominazione)
               and comu.provincia_stato = nvl(p_provincia, comu.provincia_stato)
               and comu.comune = nvl(p_comune, comu.comune)
               and comu.provincia_stato = prov.provincia
               and ( comu.data_soppressione is not null or
                     ( comu.provincia_fusione is not null and
                       comu.comune_fusione is not null)
                   )
            ;
         EXCEPTION
            WHEN NO_DATA_FOUND THEN
               d_err:= 'Non esiste comune'||d_err;
               raise_application_error(-20999,d_err);
            WHEN TOO_MANY_ROWS THEN
               d_err:= 'Esistono piu'' comuni'||d_err;
               raise_application_error(-20999,d_err);
            WHEN OTHERS THEN
               raise;
         END;
      end if;
   EXCEPTION
      WHEN OTHERS THEN
         raise;
   END GET_DATI;
   Function GET_PROVINCIA
   ( p_denominazione IN varchar2
   , p_sigla_provincia IN varchar2
   , p_soppresso IN number default null)
   RETURN number IS
   /******************************************************************************
    NOME:        GET_PROVINCIA.
    DESCRIZIONE: Restituisce il codice della PROVINCIA.
    PARAMETRI:
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM     Prima emissione.
   ******************************************************************************/
   d_ritorno number;
   d_comune number;
   d_cap number(5);
   d_cfis varchar2(4);
   d_soppresso number(1);
   BEGIN
     d_soppresso := p_soppresso;
      GET_DATI(p_denominazione, p_sigla_provincia, d_comune, d_ritorno, d_cap, d_cfis, d_soppresso);
      return d_ritorno;
   EXCEPTION
      WHEN OTHERS THEN
         raise;
   END GET_PROVINCIA;
   Function GET_CFIS
   ( p_denominazione IN varchar2
   , p_sigla_provincia IN varchar2
   , p_soppresso IN number default null)
   RETURN varchar2 IS
   /******************************************************************************
    NOME:        GET_CFIS.
    DESCRIZIONE: Restituisce SIGLA FISCALE del comune.
    PARAMETRI:
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM     Prima emissione.
   ******************************************************************************/
   d_ritorno varchar2(4);
   d_comune number;
   d_provincia number(5);
   d_cap varchar2(4);
   d_soppresso number(1);
   BEGIN
     d_soppresso := p_soppresso;
      GET_DATI(p_denominazione, p_sigla_provincia, d_comune, d_provincia, d_cap, d_ritorno, d_soppresso);
      return d_ritorno;
   EXCEPTION
      WHEN OTHERS THEN
         raise;
   END GET_CFIS;
   Function GET_CAP
   ( p_denominazione IN varchar2
   , p_sigla_provincia IN varchar2
   , p_soppresso IN number default null)
   RETURN number IS
   /******************************************************************************
    NOME:        GET_CAP.
    DESCRIZIONE: Restituisce il CAP del comune.
    PARAMETRI:
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM     Prima emissione.
   ******************************************************************************/
   d_ritorno number;
   d_comune number;
   d_provincia number(5);
   d_cfis varchar2(4);
   d_soppresso number(1);
   BEGIN
     d_soppresso := p_soppresso;
      GET_DATI(p_denominazione, p_sigla_provincia, d_comune, d_provincia, d_ritorno, d_cfis, d_soppresso);
      return d_ritorno;
   EXCEPTION
      WHEN OTHERS THEN
         raise;
   END GET_CAP;
   Function IS_SOPPRESSO
   ( p_denominazione IN varchar2
   , p_sigla_provincia IN varchar2)
   RETURN number IS
   /******************************************************************************
    NOME:        IS_SOPPRESSO.
    DESCRIZIONE: Restituisce 1 se il comune e soppresso, 0 se non lo e'.
    PARAMETRI:
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM     Prima emissione.
   ******************************************************************************/
   d_provincia number;
   d_comune number;
   d_cap number(5);
   d_cfis varchar2(4);
   d_ritorno number;
   BEGIN
      GET_DATI(p_denominazione, p_sigla_provincia, d_comune, d_provincia, d_cap, d_cfis, d_ritorno);
      return d_ritorno;
   EXCEPTION
      WHEN OTHERS THEN
         raise;
   END IS_SOPPRESSO;
   Function GET_COMUNE
   ( p_denominazione IN varchar2
   , p_sigla_provincia IN varchar2
   , p_soppresso IN number default null)
   RETURN number IS
   /******************************************************************************
    NOME:        GET_COMUNE.
    DESCRIZIONE: Restituisce il codice del COMUNE.
    PARAMETRI:
    RITORNA:
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    0    03/06/2005 MM     Prima emissione.
   ******************************************************************************/
   d_ritorno number;
   d_provincia number;
   d_cap number(5);
   d_cfis varchar2(4);
   d_soppresso number(1);
   BEGIN
     d_soppresso := p_soppresso;
      GET_DATI(p_denominazione, p_sigla_provincia, d_ritorno, d_provincia, d_cap, d_cfis, d_soppresso);
      return d_ritorno;
   EXCEPTION
      WHEN OTHERS THEN
         raise;
   END GET_COMUNE;
   Function GET_CODICE_ISTAT
   ( p_sigla_cfis IN varchar2
   , p_data in date DEFAULT SYSDATE)
   RETURN varchar2 IS
   /******************************************************************************
    NOME:        GET_CODICE_ISTAT.
    DESCRIZIONE: Restituisce il CODICE ISTAT del comune ottenuto con:
                          lpad(to_char(provincia),3,'0')||lpad(to_char(comune),3,'0')
    PARAMETRI:   Campo SIGLA_CFIS e data alla quale ottenere il valore
    RITORNA:     GET_CODICE_ISTAT del comune identificato dalla sigla CFIS.
    ECCEZIONI:
    ANNOTAZIONI:
    REVISIONI:
    Rev. Data       Autore Descrizione
    ---- ---------- ------ ------------------------------------------------------
    1    27/07/2011 SNeg   Prima emissione.
   ******************************************************************************/
      d_ritorno varchar2(6);
   BEGIN
      SELECT lpad(to_char(provincia_stato),3,'0')||lpad(to_char(comune),3,'0')
        INTO d_ritorno
        FROM COMUNI
       WHERE SIGLA_CFIS = p_sigla_cfis
          AND nvl(data_soppressione,trunc(sysdate) )  >= trunc(p_data)
          AND not exists (select 1 from comuni com
                                 where sigla_cfis = comuni.sigla_cfis
                                    AND nvl(data_soppressione,trunc(sysdate) )  >=  trunc(p_data)
                                    AND nvl(com.data_soppressione,trunc(sysdate)) < nvl( comuni.data_soppressione,trunc(sysdate))
                                   )
      ;
      return d_ritorno;
      exception
      when too_many_rows then
      raise_application_error(-20999,'Esistono piu'' comuni con la stessa sigla CFIS ' || p_sigla_cfis || ' alla data richiesta ' || to_char(p_data,'dd/mm/yyyy'));
   END GET_CODICE_ISTAT;
END COMUNE;
/