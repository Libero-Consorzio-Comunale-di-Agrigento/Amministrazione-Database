CREATE OR REPLACE package body SI4_XML is
/******************************************************************************
 NOME:        SI4_XML.
 DESCRIZIONE: Funzioni per la gestione di file XML.
 ANNOTAZIONI: Attualmente le funzioni sono mirate ai soli file XML ottenuti
              tramite select.
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000 21/11/2005  MM     Creazione.
 001 29/11/2007  MM     Creazione get_escSeq, get_invalidCharEscSeq e get_xml;
                        modificate append_xml e append_select.
 002 27/05/2020  SN     Sostituire dbms_xmlparser. dbms_xmldom.e xslprocessor
                        con DBMS_dbms_xmlparser. DBMS_dbms_xmldom.and DBMS_XSLPROCESSOR  Bug#42420
******************************************************************************/
   s_revisione_body constant VARCHAR2(30) := '002';
   function versione return VARCHAR2 is
   /******************************************************************************
    NOME:        versione.
    DESCRIZIONE: Restituisce versione e revisione di distribuzione del package.
    RITORNA:     VARCHAR2 stringa contenente versione e revisione.
    NOTE:        Primo numero  : versione compatibilita del Package.
                 Secondo numero: revisione del Package specification.
                 Terzo numero  : revisione del Package body.
   ******************************************************************************/
   begin
      return s_revisione||'.'||nvl(s_revisione_body,'000');
   end versione;
   function IMPORT
   /******************************************************************************
    NOME:        IMPORT.
    DESCRIZIONE: Dato un BFILE riempie la tabella/vista data con i record in esso
                contenuti.
    PARAMETRI:   p_file  IN OUT BFILE:    link al file sul server.
                p_table IN     VARCHAR2: tabella / vista da popolare.
    RITORNA:     NUMBER:  0 se il file non esiste o non contiene record;
                        -1 se si e' verificato un errore;
                            numero dei record inseriti, altrimenti.
    NOTE:        .
    REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     000  05/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_file   IN OUT BFILE
   , p_table  IN     VARCHAR2
   , p_rowtag IN     VARCHAR2 default 'ROW')
   return NUMBER
   IS
      xmlClob  CLOB := null;
      iReturn  integer;
   begin
      if NOT(DBMS_LOB.fileexists(p_file) = 1) then
         return null;
      else
         begin
            DBMS_LOB.OPEN(p_file, DBMS_LOB.LOB_READONLY);
            DBMS_LOB.createtemporary(xmlClob, true);
            DBMS_LOB.LOADFROMFILE(xmlClob, p_file, dbms_lob.getLength(p_file));
            DBMS_LOB.CLOSE(p_file);
            -- associa all'elemento riga il tag passato
            xmlgen.setRowTag(p_rowtag);
--            xmlgen.useLowerCaseTagNames(); -- set the tag names to be all in lower case.
            iReturn := xmlgen.insertXML(p_table, xmlClob);
            dbms_lob.freetemporary(xmlClob);
            return iReturn;
         exception
            when no_data_found then
               dbms_lob.freetemporary(xmlClob);
               xmlgen.resetOptions;
               raise;
            when others then
               xmlgen.resetOptions;
               raise;
         end;
      end if;
   exception
      when no_data_found then
         return null;
      when others then
         raise;
         return -1;
   end IMPORT;
   procedure append_XML
   /******************************************************************************
    NOME:         append_XML.
    DESCRIZIONE:  Inserisce i tag di tipo "p_tag_name" del documento XML
                  "p_doc_append" nel documento XML "p_doc_orig" salvando
                  il risultato sul file "p_file".
                  Se "p_agg_num" val e 1, aggiorna l'attributo "num" di "p_tag_name"
                  in modo che parta dal massimo num di "p_doc_orig" + 1.
    ARGOMENTI:    p_doc_orig   IN dbms_xmldom.DOMDocument: documento originale.
                  p_doc_append IN dbms_xmldom.DOMDocument: documento da includere.
                  p_tag_name   IN VARCHAR2:  tag di p_doc_append da includere in
                                             p_doc_orig.
                  p_file       IN VARCHAR2:  percorso completo del file risultante
                                             su file system
                  p_agg_num    IN NUMBER     se = 1, aggiorna l'attributo "num" di
                                             "p_tag_name" in modo che parta dal
                                             massimo num di "p_doc_orig" + 1.
                                             se = 0, non modifica i tag di tipo
                                             "p_tag_name" da includere;
                                             se = 2, non mette l'attributo "num".
                                             default 1.
    NOTE:        .
    REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     000  05/12/2005 MM     Prima emissione.
     001  22/01/2008 SN     Gestione valore 2 del parametro p_agg_num.
   ******************************************************************************/
   ( p_doc_orig   IN dbms_xmldom.DOMDocument
   , p_doc_append IN dbms_xmldom.DOMDocument
   , p_tag_name   IN VARCHAR2
   , p_file       IN VARCHAR2
   , p_agg_num    IN NUMBER default 1)
   IS
      xmlRootElement    dbms_xmldom.DOMElement;
      xmlNodeListOrig   dbms_xmldom.DOMNodeList;
      xmlNodeOrigNum    number;
      xmlNodeListAppend dbms_xmldom.DOMNodeList;
      xmlNodeAppendNum  number;
      xmlNodeAppend     dbms_xmldom.DOMNode;
      xmlNewNode        dbms_xmldom.DOMNode;
      xmlRootNode       dbms_xmldom.DOMNode;
      xmlElem           dbms_xmldom.DOMElement;
   begin
      -- ottiene il numero di elementi di tipo "p_tag_name" del documento originale.
      xmlNodeListOrig := dbms_xmldom.getElementsByTagName(p_doc_orig, p_tag_name);
      xmlNodeOrigNum  := dbms_xmldom.getLength(xmlNodeListOrig);
      -- ottiene l'elemento root del documento originale e crea il nodo corrispondente.
      xmlRootElement := dbms_xmldom.getDocumentElement(p_doc_orig);
      xmlRootNode    := dbms_xmldom.makeNode(xmlRootElement);
      -- ottiene il numero di elementi di tipo "p_tag_name" del documento da inserire.
      xmlNodeListAppend := dbms_xmldom.getElementsByTagName(p_doc_append, p_tag_name);
      xmlNodeAppendNum  := dbms_xmldom.getLength(xmlNodeListAppend);
      -- per ognuno degli elementi di tipo "p_tag_name" del documento da inserire,
      --    ottiene il nodo corrispondente;
      --    lo associa al documento originale;
      --    crea l'elemento corrispondente al nodo;
      --    se richiesto (p_agg_num = 1) ne modifica l'attributo "num";
      --    appende effettivamente il nodo al documento originale.
      for i in 0..xmlNodeAppendNum - 1 loop
         xmlNodeAppend := dbms_xmldom.item(xmlNodeListAppend, i);
         xmlNewNode    := dbms_xmldom.adoptNode(p_doc_orig, xmlNodeAppend);
         xmlElem       := dbms_xmldom.makeElement(xmlNodeAppend);
         if p_agg_num = 1 then
            dbms_xmldom.setAttribute(xmlElem, 'num', i + xmlNodeOrigNum + 1);
         end if;
         --Modifica SN 22/01/2008
         if p_agg_num = 2 then
            dbms_xmldom.removeAttribute(xmlElem, 'num');
         end if;
         --Fine modifica SN 22/01/2008
         xmlNewNode := dbms_xmldom.appendchild(xmlRootNode, dbms_xmldom.makeNode(xmlElem));
      end loop;
--      dbms_output.put_line('dopo loop: '||p_file);
      -- salva il documento ottenuto in p_file
      dbms_xmldom.writetofile(p_doc_orig, p_file);
--      dbms_output.put_line('fine');
   exception
      when others then
         xmlgen.resetOptions;
         raise;
   end append_XML;
   function get_xml
   /******************************************************************************
    NOME:        get_xml.
    DESCRIZIONE: Ottiene un XML data una select ed eventualmente il tag da
                 associare al nodo contenenete le singole righe estratte dalla
                 select.
    PARAMETRI:   p_select IN varchar2: select di riempimento.
                 p_rowtag IN varchar2: tag del nodo corrispondente alla riga;
                                       default 'ROW'.
    RITORNA:     clob: XML contenente il risultato della select.
    NOTE:        .
    REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     000  05/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_select IN varchar2
   , p_rowtag IN varchar2 default 'ROW')
   RETURN clob
   IS
      xmlClob                Clob := null;
      d_select              varchar2(32000);
      d_count               integer := 1000000;
   begin
      -- esegue preventivamente la select per contare il numero di record
      -- che estrarra' e settare cosi' il numero di righe del XML generato
      declare
         d_dep varchar2(32000);
      begin
         d_dep := 'select count('||substr(p_select, 8);
         d_select := afc.get_substr(d_dep, ' from ');
         d_select := d_select||') from '||d_dep;
      end;
      begin
         EXECUTE IMMEDIATE d_select INTO d_count;
      exception when others then
         null;
      end;
      -- setta il numero massimo di righe del XML
      xmlgen.setMaxRows(d_count);
     -- usa l'indicatore null per indicare che una colonna ha valore nullo:
     -- <COLONNA NULL="TRUE"/>
      xmlgen.useNullAttributeIndicator(TRUE);
      -- associa all'elemento riga il tag passato
      xmlgen.setRowTag(p_rowtag);
      -- ottiene XML dalla select e lo deposita nel Clob
      xmlClob := xmlgen.getXML(p_select);
      RETURN xmlClob;
   exception
      when others then
--         dbms_output.put_line('others');
         dbms_lob.freetemporary(xmlClob);
         raise;
   end get_xml;
   function get_DomDocument
   /******************************************************************************
    NOME:        get_DomDocument.
    DESCRIZIONE: Ottiene un documento XML data una select ed eventualmente il tag
                 da associare al nodo contenenete le singole righe estratte dalla
                 select.
    PARAMETRI:   p_select IN varchar2: select di riempimento.
                 p_rowtag IN varchar2: tag del nodo corrispondente alla riga;
                                       default 'ROW'.
    RITORNA:     dbms_xmldom.DOMDocument: documento XML contenente il risultato della
                                    select.
    NOTE:        .
    REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     000  05/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_select IN varchar2
   , p_rowtag IN varchar2 default 'ROW')
   RETURN dbms_xmldom.DOMDocument
   IS
      xmlClob                Clob := null;
      xmlDoc               dbms_xmldom.DOMDocument;
      xmlClobParser        dbms_xmlparser.Parser;
   begin
      -- crea il parser
      xmlClobParser := dbms_xmlparser.newParser;
      -- ottiene XML dalla select e lo deposita nel Clob
      xmlClob := get_XML(p_select, p_rowtag);
      -- esegue il parse del clob e lo deposita nel Clob
      dbms_xmlparser.parseClob(xmlClobParser, xmlClob);
      -- ottiene il documento dal clob parsato
      xmlDoc := dbms_xmlparser.getDocument(xmlClobParser);
      dbms_xmlparser.freeParser(xmlClobParser);
      dbms_lob.freetemporary(xmlClob);
      RETURN xmlDoc;
   exception
      when others then
--         dbms_output.put_line('others');
         dbms_lob.freetemporary(xmlClob);
         dbms_xmlparser.freeParser(xmlClobParser);
         raise;
   end get_DomDocument;
   function get_DomDocument
   ( p_xmlfile IN OUT BFILE)
   /******************************************************************************
    NOME:        get_DomDocument.
    DESCRIZIONE: Ottiene un documento dato un puntatore ad un file XML su file
                system (BFILE).
    PARAMETRI:   p_xmlfile IN OUT BFILE.
    RITORNA:     dbms_xmldom.DOMDocument: documento contenente il file XML.
    NOTE:        .
    REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     000  05/12/2005 MM     Prima emissione.
   ******************************************************************************/
   RETURN dbms_xmldom.DOMDocument
   IS
      xmlClob                Clob := null;
      xmlClobParser        dbms_xmlparser.Parser;
      xmlDoc            dbms_xmldom.DOMDocument;
   begin
      -- controlla esistenza del file
      if dbms_lob.fileexists(p_xmlfile) = 1 then
         -- apre il file, lo carica in un clob e lo chiude
         DBMS_LOB.OPEN(p_xmlfile, DBMS_LOB.LOB_READONLY);
         DBMS_LOB.createtemporary(xmlClob, true);
         DBMS_LOB.LOADFROMFILE(xmlClob, p_xmlfile, dbms_lob.getLength(p_xmlfile));
         DBMS_LOB.CLOSE(p_xmlfile);
         -- crea il parser
         xmlClobParser := dbms_xmlparser.newParser;
         -- esegue il parse del clob e lo deposita nel Clob
         dbms_xmlparser.parseClob(xmlClobParser, xmlClob);
         -- ottiene il documento dal clob parsato
         xmlDoc := dbms_xmlparser.getDocument(xmlClobParser);
         dbms_xmlparser.freeParser(xmlClobParser);
         dbms_lob.freetemporary(xmlClob);
      else
         -- se non esiste il file, crea un documento contenete un XML vuoto
         xmlDoc := get_DOMDocument('select 1 from dual where 1 = 0','');
     end if;
      RETURN xmlDoc;
   exception
      when others then
         dbms_lob.freetemporary(xmlClob);
         dbms_xmlparser.freeParser(xmlClobParser);
         raise;
   end get_DomDocument;
   function append_select
   /******************************************************************************
    NOME:        append_select_in_xml.
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
    NOTE:        .
    REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     000  05/12/2005 MM     Prima emissione.
     001  22/01/2008 SN     Gestione valore 2 del parametro p_agg_num.
   ******************************************************************************/
   ( p_directory IN varchar2
   , p_file      IN varchar2
   , p_select    IN varchar2
   , p_rowtag    IN varchar2 default 'ROW'
   , p_agg_num   IN number   default 1)
   RETURN BFILE
   IS
      xmlDocSelect dbms_xmldom.DOMDocument;
      xmlDocFile   dbms_xmldom.DOMDocument;
      d_path       varchar(4000);
      d_dir        varchar(4000);
      d_SrcLoc     bfile;
   begin
      d_path := AFC_BFILE.get_dirPath(p_directory);
      d_dir  := AFC_BFILE.get_dirName(p_directory);
      if d_dir is null then
         raise_application_error(-20999, 'Nome o percorso della Directory ('||d_dir||') inesistente.');
      end if;
      d_SrcLoc     := BFILENAME(d_dir, p_file);
      xmlDocFile   := get_DOMDocument(d_SrcLoc);
      xmlDocSelect := get_DOMDocument(p_select, p_rowtag);
      --Modifica SN 22/01/2008
      --append_xml(xmlDocFile, xmlDocSelect, p_rowtag, d_path||'/'||p_file);
      append_xml(xmlDocFile, xmlDocSelect, p_rowtag, d_path||'/'||p_file, p_agg_num);
      --Fine modifica SN 22/01/2008
      dbms_xmldom.freeDocument(xmlDocSelect);
      dbms_xmldom.freeDocument(xmlDocFile);
      RETURN BFILENAME(d_dir, p_file);
   exception
      when others then
--         dbms_output.put_line('append_select_in_xml others: '||SQLERRM);
         xmlgen.resetOptions;
         raise;
   end append_select;
   PROCEDURE append_select
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
    NOTE:        .
    REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     000  05/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_directory IN varchar2
   , p_file      IN varchar2
   , p_select    IN varchar2
   , p_rowtag    IN varchar2 default 'ROW'
   , p_agg_num   IN number   default 1)
   IS
      d_file BFILE;
   BEGIN
      d_file := append_select(p_directory, p_file, p_select, p_rowtag, p_agg_num);
   EXCEPTION WHEN OTHERS THEN
      raise;
   END append_select;
   function append_select
   /******************************************************************************
    NOME:        append_select.
    DESCRIZIONE: Crea o modifica (append) un documento XML in base alla select
                 data associando all'elemento corrispondente ad ogni singola riga
                 il nome p_rowtag e salva il risultato nel file p_bfile.
                 Se "p_agg_num" vale 1, aggiorna l'attributo "num" di "p_rowtag"
                 in modo che parta dal massimo num + 1.
    PARAMETRI:   p_bfile     IN OUT bfile: puntatore al file su disco.
                 p_select    IN varchar2:  select con cui viene riempito il file XML.
                 p_rowtag    IN varchar2:  nome del tag da associare alla riga.
                 p_agg_num   IN number     se = 1, aggiorna l'attributo "num" di
                                           "p_rowtag" in modo che parta dal
                                           massimo num + 1.
                                           se = 0, non modifica i tag;
                                           se = 2, non mette l'attributo "num".
                                           default 1.
    RITORNA:     bfile.
    NOTE:        .
    REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     000  05/12/2005 MM     Prima emissione.
   ******************************************************************************/
   ( p_bfile   IN OUT bfile
   , p_select  IN varchar2
   , p_rowtag  IN varchar2 default 'ROW'
   , p_agg_num IN number   default 1)
   RETURN BFILE
   IS
      d_path   varchar(4000);
     d_file   varchar(4000);
   begin
      dbms_lob.filegetname(p_bfile, d_path, d_file);
      RETURN append_select(d_path, d_file, p_select, p_rowtag, p_agg_num);
   end append_select;
   procedure copy
   /******************************************************************************
    NOME:        copy.
    DESCRIZIONE: Copia un file in un altro file.
    ARGOMENTI:   p_path_from IN varchar2: directory contenente il file da copiare.
                 p_file_from IN varchar2: nome del file da copiare.
                 p_path_to IN varchar2: directory che conterra' il file copiato.
                 p_file_to IN varchar2: nome del file una volta copiato.
    NOTE:        .
    REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     001  29/11/2007 MM     Inserimento commento.
   ******************************************************************************/
   ( p_path_from IN varchar2
   , p_file_from IN varchar2
   , p_path_to IN varchar2
   , p_file_to IN varchar2)
   IS
      d_alias_from varchar2(2000);
      d_path_to varchar2(2000);
      d_file varchar2(100);
      d_bfile bfile;
      doc dbms_xmldom.DOMDocument;
   BEGIN
      d_alias_from := AFC_BFILE.get_dirName(p_path_from);
      d_path_to := AFC_BFILE.get_dirPath(p_path_to);
      if d_path_to is null and (instr(p_path_to,'/') > 0 or instr(p_path_to,'\') > 0) then
         d_path_to := p_path_to;
      end if;
      d_file := d_path_to||'/'||p_file_to;
      d_bfile := bfilename(d_alias_from, p_file_from);
      doc := get_DomDocument(d_bfile);
      dbms_xmldom.writetofile(doc, d_file);
   END copy;
   function get_escSeq
   (p_stringa IN varchar2) return varchar2
   /******************************************************************************
    NOME:         get_escSeq.
    DESCRIZIONE:  Conversione di una stringa nella corrispondente sequenza di
                  escape XML utilizzando le entità'' del tipo &#n; dove n
                  corrisponde al valore ASCII a 7 bit di ogni carattere che
                  compone la stringa.
    PARAMETRI:    p_stringa IN varchar2: stringa da convertire.
    RITORNA:      varchar2 stringa convertita.
    NOTE:        .
    REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     001  29/11/2007 MM     Creazione.
   ******************************************************************************/
   is
      d_return varchar2(32767);
      iLoop integer := 1;
   begin
      while iLoop <= length(p_stringa) loop
         d_return := d_return||'&#' || ascii(substr(p_stringa,iLoop,1)) || ';';
         iLoop := iLoop + 1;
      end loop;
      return  d_return;
   end;
   function get_invalidCharEscSeq
   (p_stringa IN varchar2) return varchar2
   /******************************************************************************
    NOME:         get_invalidCharEscSeq.
    DESCRIZIONE:  Conversione di una stringa nella corrispondente stringa in cui
                  i caratteri '<','>','&','''','"','!','?' vengono sostituiti con
                  i caratteri di escape XML utilizzando le entità'' del tipo &#n;
                  dove n corrisponde al valore ASCII a 7 bit del carattere.
    PARAMETRI:    p_stringa IN varchar2: stringa da convertire.
    RITORNA:      varchar2 stringa convertita.
    NOTE:        .
    REVISIONI:
     Rev. Data       Autore Descrizione
     ---- ---------- ------ ------------------------------------------------------
     001  29/11/2007 MM     Creazione.
   ******************************************************************************/
   is
      d_return varchar2(32767);
      iLoop integer := 1;
   begin
      while iLoop <= length(p_stringa) loop
         if substr(p_stringa,iLoop,1)
            in ( '<', '>', '&', '''', '"', '!', '?', chr(224), chr(232), chr(233), chr(236), chr(242), chr(249)
               , chr(192), chr(200), chr(201), chr(204), chr(210), chr(217), chr(176), chr(128)
               )
         then
            d_return := d_return||'&#' || ascii(substr(p_stringa,iLoop,1)) || ';';
         else
            d_return := d_return||substr(p_stringa,iLoop,1);
         end if;
         iLoop := iLoop + 1;
      end loop;
      return  d_return;
   end;
end SI4_XML;
/

