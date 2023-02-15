CREATE OR REPLACE PACKAGE Codice_Fiscale IS
/******************************************************************************
 NOME:        CODICE_FISCALE.
 DESCRIZIONE: Package per gestione CODICE_FISCALE.
 ANNOTAZIONI: Salvato nella directory ins di AD4 nel file codfis.pks.
 ECCEZIONI:.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    17/01/2003 MM     Creazione.
 2    03/05/2007 MM     A20821.0.0: aggiunti i parametri p_cognome e p_nome
                        alle funzioni controllo e gestito nuovo errore in
                        get_error_msg.
 3    14/05/2007 MM     Duplicazione funzioni CONTROLLO con parametro
                        p_data_nascita date.
 4    25/02/2008 MM     Creazione procedure GET_DATI e funzioni
                        GET_PROVINCIA_NAS, GET_COMUNE_NAS, GET_DATA_NAS,
                        GET_SESSO.
******************************************************************************/
   FUNCTION  VERSIONE RETURN VARCHAR2;
/******************************************************************************
 NOME:        CREA.
 DESCRIZIONE: Determinazione Codice Fiscale.
 ARGOMENTI:   p_cognome:        cognome dell'individuo
              p_nome:           nome dell'individuo
              p_data:           data di nascita dell'individuo
              p_codice_catasto: codice catasto del comune di nascita dell'individuo
              p_sesso:          sesso dell'individuo
              p_codice_fiscale:   codice fiscale dell'individuo calcolato
 ECCEZIONI:   -20940: I parametri 'p_cognome', 'p_nome', 'p_data', '
                      'p_codice_catasto' e ''p_sesso'' NON possono essere NULLI.
 ANNOTAZIONI: presa da P00(PanamaASS).
******************************************************************************/
   PROCEDURE CREA ( p_cognome        IN     VARCHAR2
                  , p_nome           IN     VARCHAR2
                  , p_data           IN     DATE
                  , p_codice_catasto IN     VARCHAR2
                  , p_sesso          IN     VARCHAR2
                  , p_codice_fiscale IN OUT VARCHAR2);
/******************************************************************************
 NOME:        CREA.
 DESCRIZIONE: Determinazione Codice Fiscale
              Lancia la procedura omonima dopo aver calcolato, dal codice del
              comune e della provincia di nascita, il codice catasto corrispondente.
 ARGOMENTI:   p_cognome:        cognome dell'individuo
              p_nome:           nome dell'individuo
              p_data:           data di nascita dell'individuo
              p_comune_nas:     codice del comune di nascita dell'individuo
              p_provincia_nas:  codice della provincia di nascita dell'individuo
              p_sesso:          sesso dell'individuo
              p_codice_fiscale:   codice fiscale dell'individuo calcolato.
 ECCEZIONI:   -20920: Errore in selezione Codice Catasto.
******************************************************************************/
   PROCEDURE CREA ( p_cognome        IN     VARCHAR2
                  , p_nome           IN     VARCHAR2
                  , p_data           IN     DATE
                  , p_comune_nas     IN     NUMBER
                  , p_provincia_nas  IN     NUMBER
                  , p_sesso          IN     VARCHAR2
                  , p_codice_fiscale IN OUT VARCHAR2);
   FUNCTION CREA
/******************************************************************************
 NOME:        CREA.
 DESCRIZIONE: Determinazione Codice Fiscale
 ARGOMENTI:   .
 ECCEZIONI:   -20920: Errore in selezione Codice Catasto.
 ANNOTAZIONI: -
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 0    17/01/2003 MM     Creazione.
******************************************************************************/
   ( p_cognome        IN     VARCHAR2
   , p_nome           IN     VARCHAR2
   , p_data           IN     DATE
   , p_comune_nas     IN     NUMBER
   , p_provincia_nas  IN     NUMBER
   , p_sesso          IN     VARCHAR2)
   RETURN VARCHAR2;
/******************************************************************************
 NOME:        CONTROLLO.
 DESCRIZIONE: Controllo CODICE FISCALE e PARTITA IVA.
              Lancia la funzione omonima dopo aver calcolato, dal codice del
              comune e della provincia di nascita, il codice catasto corrispondente.
  PARAMETRI:  p_codice_fiscale    codice fiscale o partita iva
              p_sesso             assume il valore relativo o il valore '*' se si
                                  desidera un controllo limitato del codice fiscale.
              p_comune_nas        codice del comune di nascita; e significativo
                                  solo per un controllo completo del codice fiscale.
              p_provincia_nas     codice della provincia di nascita; e significativo
                                  solo per un controllo completo del codice fiscale.
              p_data_nascita      data di nascita dell'individuo in formato
                                  dd/mm/yyyy; e' significativa solo per un controllo
                                  completo del codice fiscale.
              p_cognome           cognome dell'individuo; e' significativo solo
                                  per un controllo completo del codice fiscale.
              p_nome              nome dell'individuo; e' significativo solo per un
                                  controllo completo del codice fiscale.
    RITORNA:  NUMBER  Codice di errore
******************************************************************************/
   FUNCTION CONTROLLO ( p_codice_fiscale IN VARCHAR2
                      , p_sesso          IN VARCHAR2
                      , p_comune_nas     IN NUMBER
                      , p_provincia_nas  IN NUMBER
                      , p_data_nascita   IN VARCHAR2 DEFAULT NULL
                      , p_cognome        IN VARCHAR2 DEFAULT NULL
                      , p_nome           IN VARCHAR2 DEFAULT NULL)
   RETURN NUMBER;
/******************************************************************************
 NOME:        CONTROLLO.
 DESCRIZIONE: Controllo CODICE FISCALE e PARTITA IVA.
              I controlli operati sono:
              Cod.Fiscale :  a)- che il dato sia di 16 caratteri di cui:
                                 1^,2^,3^,4^,5^,6^,12^,16^ carattere alfa-
                                 betico maiuscolo, il 9^ alfabetico maiu-
                                 scolo e compreso tra i previsti per i mesi,
                                 7^,8^,10^,11^,13^,14^,15^ numerici.
                             b)- che 12^,13^,14^,15^ siano = al codice cata-
                                 sto.
                             c)- che il giorno di nascita sia corretto, ri-
                                 spetto al mese ed anno di nascita.
                             d)- che il check (16^ carattere) sia corretto.
              Il controllo parziale del codice fiscale che si ottiene passando
              il valore "*" nel sesso non esegue i punti b)- e c)- .
  PARAMETRI:  p_codice_fiscale    codice fiscale o partita iva
              p_sesso             assume il valore relativo o il valore '*' se si
                                  desidera un controllo limitato del codice fiscale.
              p_cod_catasto       codice catasto del comune di nascita; e'
                                  significativo solo per un controllo completo
                                  del codice fiscale.
              p_data_nascita      data di nascita dell'individuo in formato
                                  dd/mm/yyyy; e' significativa solo per un controllo
                                  completo del codice fiscale.
              p_cognome           cognome dell'individuo; e' significativo solo
                                  per un controllo completo del codice fiscale.
              p_nome              nome dell'individuo; e' significativo solo per un
                                  controllo completo del codice fiscale.
    RITORNA:  NUMBER  Codice di errore:
                0:  OK.
              - 1: Lunghezza errata.
              - 2: Codice errato. In corrispondenza degli identificativi del
                   nominativo, del 1. carattere del codice catasto o del
                   carattere di controllo devono comparire solo caratteri
                   alfabetici [A - Z].
              - 3: Codice errato. In corrispondenza degli identificativi di
                   anno e giorno di nascita e ultimi 3 caratteri del codice
                   catasto devono essere numerici [0 - 9].
              - 4: Codice errato. In corrispondenza del mese deve esserci un
                   carattere alfabetico significativo.
              - 5: Codice errato. Il Codice Catasto del Comune di Nascita deve
                   essere lo stesso del Codice Fiscale.
              - 6: Giorno di nascita errato.
              - 7: Peso relativo ai primi 15 caratteri del Codice Fiscale errato.
              - 8: Ultimo carattere errato.
              - 9: Check del codice fiscale errato.
              -10: Data di nascita e codice fiscale incompatibili.
              -11: Cognome / nome e codice fiscale incompatibili.
              -19: Errore non gestito.
              -21: Codice errato. Tutti i caratteri della Partita IVA devono essere
                   numerici.
              -22: Codice errato. Controllo check Partita IVA fallito.
******************************************************************************/
   FUNCTION CONTROLLO ( p_codice_fiscale IN VARCHAR2
                      , p_sesso          IN VARCHAR2 DEFAULT NULL
                      , p_cod_catasto    IN VARCHAR2 DEFAULT NULL
                      , p_data_nascita   IN VARCHAR2 DEFAULT NULL
                      , p_cognome        IN VARCHAR2 DEFAULT NULL
                      , p_nome           IN VARCHAR2 DEFAULT NULL)
   RETURN NUMBER;
/******************************************************************************
 NOME:        GET_ERROR_MSG
 DESCRIZIONE: Restituisce messaggio di errore associato a p_error.
 PARAMETRI:   p_error: codice di errore.
 RITORNA:     stringa varchar2 contenente messaggio di errore.
******************************************************************************/
   FUNCTION GET_ERROR_MSG ( p_error IN NUMBER ) RETURN VARCHAR2;
/******************************************************************************
 NOME:        GET_DATI.
 DESCRIZIONE: Ottiene tutte le informazioni che compongono il codice fiscale:
              sesso, data, provincia e comune di nascita.
              ATTENZIONE: la data di NASCITA potrebbe NON essere CORRETTA
              perche' dal c.f. e' possibile determinare solo le ultime 2 cifre
              dell'anno di nascita. Il secolo viene cosi determinato:
              - se 2 cifre sono < 50 e l'anno risultante e' < dell'anno in corso,
                  ritorna il secolo corrente;
              - altrimenti,
                  ritorna 19||2 cifre del c.f.
              Ad esempio:
              01 01 49  diventa 01/01/1949
              01 01 51  diventa 01/01/1951
              01 01 06  diventa 01/01/2006 (ma potrebbe essere anche 1906).
  ARGOMENTI:  p_codice_fiscale   IN  VARCHAR2      codice fiscale
              p_sesso            IN OUT VARCHAR2   sesso (F/M)
              p_data_nas         IN OUT VARCHAR2   data di nascita in formato
                                                   dd/mm/yyyyy.
              p_provincia_nas    IN OUT NUMBER     codice provincia di nascita
              p_comune_nas       IN OUT NUMBER     codice comune di nascita
******************************************************************************/
   PROCEDURE GET_DATI
   ( p_codice_fiscale IN VARCHAR2
   , p_sesso IN OUT VARCHAR2
   , p_data_nas IN OUT VARCHAR2
   , p_provincia_nas IN OUT NUMBER
   , p_comune_nas IN OUT NUMBER);
/******************************************************************************
 NOME:        GET_PROVINCIA_NAS.
 DESCRIZIONE: Ottiene il codice della provincia di nascita.
  PARAMETRI:  p_codice_fiscale    codice fiscale
    RITORNA:  NUMBER  Codice della provincia di nascita.
******************************************************************************/
   FUNCTION GET_PROVINCIA_NAS
   ( p_codice_fiscale IN VARCHAR2) RETURN NUMBER;
/******************************************************************************
 NOME:        GET_COMUNE_NAS.
 DESCRIZIONE: Ottiene il codice del comune di nascita.
  PARAMETRI:  p_codice_fiscale    codice fiscale
    RITORNA:  NUMBER  Codice del comune di nascita.
******************************************************************************/
   FUNCTION GET_COMUNE_NAS
   ( p_codice_fiscale IN VARCHAR2) RETURN NUMBER;
/******************************************************************************
 NOME:        GET_DATA_NAS.
 DESCRIZIONE: Ottiene la data di nascita.
              ATTENZIONE: la data di NASCITA potrebbe NON essere CORRETTA
              perche' dal c.f. e' possibile determinare solo le ultime 2 cifre
              dell'anno di nascita. Il secolo viene cosi determinato:
              - se 2 cifre sono < 50 e l'anno risultante e' < dell'anno in corso,
                  ritorna il secolo corrente;
              - altrimenti,
                  ritorna 19||2 cifre del c.f.
              Ad esempio:
              01 01 49  diventa 01/01/1949
              01 01 51  diventa 01/01/1951
              01 01 06  diventa 01/01/2006 (ma potrebbe essere anche 1906).
  PARAMETRI:  p_codice_fiscale    codice fiscale
    RITORNA:  varchar2  Data di nascita come stringa in formato dd/mm/yyyy.
******************************************************************************/
   FUNCTION GET_DATA_NAS
   ( p_codice_fiscale varchar2) return varchar2;
/******************************************************************************
 NOME:        GET_SESSO
 DESCRIZIONE: Ottiene il sesso.
  PARAMETRI:  p_codice_fiscale    codice fiscale
    RITORNA:  varchar2  Sesso (F/M)
******************************************************************************/
   FUNCTION GET_SESSO
   ( p_codice_fiscale varchar2) return varchar2;
END Codice_Fiscale;
/

