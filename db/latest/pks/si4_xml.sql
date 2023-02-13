--liquibase formatted sql

--changeset mturra:201901301231_278 runOnChange:true stripComments:false endDelimiter:/

CREATE OR REPLACE PACKAGE Si4_Xml IS
/******************************************************************************
 NOME:        AFC_XML.
 DESCRIZIONE: Funzioni per la gestione di file XML.
 ANNOTAZIONI: Attualmente le funzioni sono mirate ai soli file XML ottenuti
              tramite select.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 01   05/12/2005 MM     Prima emissione.
 01   29/11/2007 MM     Creazione get_escSeq, get_invalidCharEscSeq e get_xml.
 02 27/05/2020  SN     Sostituire dbms_xmlparser. dbms_xmldom.e xslprocessor
                        con DBMS_xmlparser. DBMS_xmldom.and DBMS_XSLPROCESSOR  Bug#42420
******************************************************************************/
   s_revisione CONSTANT VARCHAR2(30) := 'V1.01';
   /******************************************************************************
    NOME:        versione.
    DESCRIZIONE: Restituisce versione e revisione di distribuzione del package.
    RITORNA:     VARCHAR2 stringa contenente versione e revisione.
    NOTE:        Primo numero  : versione compatibilita del Package.
                 Secondo numero: revisione del Package specification.
                 Terzo numero  : revisione del Package body.
   ******************************************************************************/
   FUNCTION versione RETURN VARCHAR2;
   /******************************************************************************
    NOME:        IMPORT.
    DESCRIZIONE: Dato un BFILE riempie la tabella/vista data con i record in esso
                contenuti.
    PARAMETRI:   p_file  IN OUT BFILE:    link al file sul server.
                p_table IN     VARCHAR2: tabella / vista da popolare.
    RITORNA:     NUMBER: 0  se il file non esiste o non contiene record;
                        -1 se si e' verificato un errore;
                            numero dei record inseriti, altrimenti. .
   ******************************************************************************/
   FUNCTION IMPORT
   ( p_file   IN OUT BFILE
   , p_table  IN     VARCHAR2
   , p_rowtag IN     VARCHAR2 DEFAULT 'ROW'
   ) RETURN NUMBER;
   /******************************************************************************
    NOME:        append_XML.
    DESCRIZIONE: Inserisce i tag di tipo "p_tag_name" del documento XML
                "p_doc_append" nel documento XML "p_doc_orig" salvando
                il risultato sul file "p_file".
                 Se "p_agg_num" val e 1, aggiorna l'attributo "num" di "p_tag_name"
             in modo che parta dal massimo num di "p_doc_orig" + 1.
    ARGOMENTI:   p_doc_orig   IN xmldom.DOMDocument: documento originale.
                 p_doc_append IN xmldom.DOMDocument: documento da includere.
                 p_tag_name   IN VARCHAR2: tag di p_doc_append da includere in
                                           p_doc_orig.
                 p_file       IN VARCHAR2: percorso completo del file risultante
                                           su file system
                 p_agg_num    IN NUMBER    se = 1, aggiorna l'attributo "num" di
                                           "p_tag_name" in modo che parta dal
                                           massimo num di "p_doc_orig" + 1.
                                           se = 0, non modifica i tag di tipo
                                           "p_tag_name" da includere.
                                           se = 2, non mette l'attributo "num".
                                           default 1.
   ******************************************************************************/
   PROCEDURE append_XML
   ( p_doc_orig   IN dbms_xmldom.DOMDocument
   , p_doc_append IN dbms_xmldom.DOMDocument
   , p_tag_name   IN VARCHAR2
   , p_file       IN VARCHAR2
   , p_agg_num    IN NUMBER DEFAULT 1
   );
   /******************************************************************************
    NOME:        get_xml.
    DESCRIZIONE: Ottiene un XML data una select ed eventualmente il tag da
                 associare al nodo contenenete le singole righe estratte dalla
                 select.
    PARAMETRI:   p_select IN varchar2: select di riempimento.
                 p_rowtag IN varchar2: tag del nodo corrispondente alla riga;
                                       default 'ROW'.
    RITORNA:     clob: XML contenente il risultato della select.
   ******************************************************************************/
   function get_xml
   ( p_select IN varchar2
   , p_rowtag IN varchar2 default 'ROW')
   RETURN clob;
   /******************************************************************************
    NOME:        get_DomDocument.
    DESCRIZIONE: Ottiene un documento XML data una select ed eventualmente il tag
                da associare al nodo contenenete le singole righe estratte dalla
                 select.
    PARAMETRI:   p_select IN varchar2: select di riempimento.
                p_rowtag IN varchar2: tag del nodo corrispondente alla riga;
                                       default 'ROW'.
    RITORNA:     xmldom.DOMDocument: documento XML contenente il risultato della
                                    select.
   ******************************************************************************/
   FUNCTION get_DomDocument
   ( p_select IN VARCHAR2
   , p_rowtag IN VARCHAR2  DEFAULT 'ROW'
   ) RETURN dbms_xmldom.DOMDocument;
   /******************************************************************************
    NOME:        get_DomDocument.
    DESCRIZIONE: Ottiene un documento dato un puntatore ad un file su file
                system (BFILE).
    PARAMETRI:   p_xmlfile IN OUT BFILE.
    RITORNA:     xmldom.DOMDocument: documento contenente il file.
   ******************************************************************************/
   FUNCTION get_DomDocument
   ( p_xmlfile IN OUT BFILE
   ) RETURN dbms_xmldom.DOMDocument;
   /******************************************************************************
    NOME:        append_select.
    DESCRIZIONE: Crea o modifica (append) un documento XML in base alla select
                data associando all'elemento corrispondente ad ogni singola riga
                 il nome p_rowtag e salva il risultato nel file p_dir_alias/p_file.
                 Se "p_agg_num" vale 1, aggiorna l'attributo "num" di "p_rowtag"
             in modo che parta dal massimo num + 1.
    ARGOMENTI:   p_directory IN varchar2: alias o percorso completo della directory
                                         in cui salvare il file.
                 p_file      IN varchar2: nome del file.
                 p_select    IN varchar2: select con cui viene riempito il file XML.
                 p_rowtag    IN varchar2: nome del tag da associare alla riga.
                 p_agg_num   IN number    se = 1, aggiorna l'attributo "num" di
                                          "p_rowtag" in modo che parta dal
                                          massimo num + 1.
                                          se = 0, non modifica i tag;
                                          se = 2, non mette l'attributo "num".
                                          default 1.
   ******************************************************************************/
   FUNCTION append_select
   ( p_directory IN VARCHAR2
   , p_file      IN VARCHAR2
   , p_select    IN VARCHAR2
   , p_rowtag    IN VARCHAR2 DEFAULT 'ROW'
   , p_agg_num   IN NUMBER   DEFAULT 1
   ) RETURN BFILE;
   /******************************************************************************
    NOME:        append_select.
    DESCRIZIONE: Crea o modifica (append) un documento XML in base alla select
                data associando all'elemento corrispondente ad ogni singola riga
                 il nome p_rowtag e salva il risultato nel file p_dir_alias/p_file.
                 Se "p_agg_num" vale 1, aggiorna l'attributo "num" di "p_rowtag"
             in modo che parta dal massimo num + 1.
    PARAMETRI:   p_directory IN varchar2: alias o percorso completo della directory
                                         in cui salvare il file.
                 p_file      IN varchar2: nome del file.
                 p_select    IN varchar2: select con cui viene riempito il file XML.
                 p_rowtag    IN varchar2: nome del tag da associare alla riga.
                 p_agg_num   IN number    se = 1, aggiorna l'attributo "num" di
                                          "p_rowtag" in modo che parta dal
                                          massimo num + 1.
                                          se = 0, non modifica i tag;
                                          se = 2, non mette l'attributo "num".
                                          default 1.
   ******************************************************************************/
   PROCEDURE append_select
   ( p_directory IN VARCHAR2
   , p_file      IN VARCHAR2
   , p_select    IN VARCHAR2
   , p_rowtag    IN VARCHAR2 DEFAULT 'ROW'
   , p_agg_num   IN NUMBER   DEFAULT 1
   );
   /******************************************************************************
    NOME:        append_select.
    DESCRIZIONE: Crea o modifica (append) un documento XML in base alla select
                data associando all'elemento corrispondente ad ogni singola riga
                 il nome p_rowtag e salva il risultato nel file p_bfile.
                 Se "p_agg_num" vale 1, aggiorna l'attributo "num" di "p_rowtag"
             in modo che parta dal massimo num + 1.
    ARGOMENTI:   p_bfile     IN OUT bfile: puntatore al file su disco.
                 p_select    IN varchar2:  select con cui viene riempito il file XML.
                 p_rowtag    IN varchar2:  nome del tag da associare alla riga.
                 p_agg_num   IN number     se = 1, aggiorna l'attributo "num" di
                                           "p_rowtag" in modo che parta dal
                                           massimo num + 1.
                                           se = 0, non modifica i tag;
                                           se = 2, non mette l'attributo "num".
                                           default 1.
   ******************************************************************************/
   FUNCTION append_select
   ( p_bfile   IN OUT BFILE
   , p_select  IN VARCHAR2
   , p_rowtag  IN VARCHAR2 DEFAULT 'ROW'
   , p_agg_num IN NUMBER   DEFAULT 1
   ) RETURN BFILE;
   /******************************************************************************
    NOME:        copy.
    DESCRIZIONE: Copia un file in un altro file.
    ARGOMENTI:   p_path_from IN varchar2: directory contenente il file da copiare.
                 p_file_from IN varchar2: nome del file da copiare.
                 p_path_to IN varchar2: directory che conterra' il file copiato.
                 p_file_to IN varchar2: nome del file una volta copiato.
   ******************************************************************************/
   PROCEDURE copy
   ( p_path_from IN VARCHAR2
   , p_file_from IN VARCHAR2
   , p_path_to IN VARCHAR2
   , p_file_to IN VARCHAR2);
   /******************************************************************************
    NOME:         get_escSeq.
    DESCRIZIONE:  Conversione di una stringa nella corrispondente sequenza di
                  escape XML utilizzando le entità'' del tipo &#n; dove n
                  corrisponde al valore ASCII a 7 bit di ogni carattere che
                  compone la stringa.
    PARAMETRI:    p_stringa IN varchar2: stringa da convertire.
    RITORNA:      varchar2 stringa convertita.
   ******************************************************************************/
   function get_escSeq
   (p_stringa varchar2) return varchar2;
   /******************************************************************************
    NOME:         get_invalidCharEscSeq.
    DESCRIZIONE:  Conversione di una stringa nella corrispondente stringa in cui
                  i caratteri '<','>','&','''','"','!','?' vengono sostituiti con
                  i caratteri di escape XML utilizzando le entità'' del tipo &#n;
                  dove n corrisponde al valore ASCII a 7 bit del carattere.
    PARAMETRI:    p_stringa IN varchar2: stringa da convertire.
    RITORNA:      varchar2 stringa convertita.
   ******************************************************************************/
   function get_invalidCharEscSeq
   (p_stringa IN varchar2) return varchar2;
END Si4_Xml;
/
