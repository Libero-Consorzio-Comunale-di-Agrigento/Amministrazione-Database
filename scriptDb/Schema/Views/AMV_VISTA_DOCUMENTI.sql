CREATE OR REPLACE FORCE VIEW AMV_VISTA_DOCUMENTI
(ID_DOCUMENTO, ID_TIPOLOGIA, DES_TIPOLOGIA, ID_CATEGORIA, DES_CATEGORIA, 
 ID_ARGOMENTO, DES_ARGOMENTO, ID_RILEVANZA, DES_RILEVANZA, IMPORTANZA, 
 SEQUENZA, ID_AREA, TITOLO, TIPO_TESTO, TESTO, 
 LINK, IMMAGINE, ICONA, DATA_RIFERIMENTO, INIZIO_PUBBLICAZIONE, 
 FINE_PUBBLICAZIONE, AUTORE, NOME_AUTORE, DATA_INSERIMENTO, UTENTE_AGGIORNAMENTO, 
 NOME_UTENTE, DATA_ULTIMA_MODIFICA, UTENTE, EDIT, ID_SEZIONE, 
 DIRITTO, STATO, REVISIONE, DES_SEZIONE, ID_DOCUMENTO_PADRE, 
 XML, ABSTRACT)
BEQUEATH DEFINER
AS 
SELECT DOCU.ID_DOCUMENTO
  , DOCU.ID_TIPOLOGIA
 , TIPO.NOME DES_TIPOLOGIA
 , DOCU.ID_CATEGORIA
 , CATE.NOME DES_CATEGORIA
 , DOCU.ID_ARGOMENTO
 , ARGO.NOME DES_ARGOMENTO
 , DOCU.ID_RILEVANZA
 , RILE.NOME DES_RILEVANZA
 , RILE.IMPORTANZA
 , DOCU.SEQUENZA SEQUENZA
 , DOCU.ID_AREA
 , DOCU.TITOLO TITOLO
 , DOCU.TIPO_TESTO TIPO_TESTO
 , DOCU.TESTO TESTO
 , DOCU.LINK LINK
 , DOCU.IMMAGINE
 , DOCU.ICONA
 , DOCU.DATA_RIFERIMENTO
 , DOCU.INIZIO_PUBBLICAZIONE
 , DOCU.FINE_PUBBLICAZIONE
 , DOCU.AUTORE
 , UTEN.NOMINATIVO NOME_AUTORE
 , DOCU.DATA_INSERIMENTO DATA_INSERIMENTO
 , DOCU.UTENTE_AGGIORNAMENTO
 , UAGG.NOMINATIVO NOME_UTENTE
 , DOCU.DATA_AGGIORNAMENTO DATA_ULTIMA_MODIFICA
 , UINT.UTENTE
 , DECODE( Amv_Revisione.get_diritto_modifica(UINT.UTENTE, DOCU.ID_DOCUMENTO, DOCU.REVISIONE)
   , 1, 'SI', 'NO' ) EDIT
   , DOCU.ID_SEZIONE
   , Amv_Documento.get_diritto_doc(UINT.UTENTE,DOCU.ID_DOCUMENTO) DIRITTO
   , DOCU.STATO
   , DOCU.REVISIONE
  , SEZI.NOME
  , DOCU.ID_DOCUMENTO_PADRE
 , DOCU.XML
  , DOCU.ABSTRACT
 FROM AMV_DOCUMENTI DOCU
  , AMV_TIPOLOGIE TIPO
  , AMV_CATEGORIE CATE
  , AMV_ARGOMENTI ARGO
  , AMV_RILEVANZE RILE
  , AMV_SEZIONI SEZI
  , AD4_UTENTI UTEN
  , AD4_UTENTI UAGG
 , AD4_UTENTI UINT
WHERE UTEN.UTENTE (+) = DOCU.AUTORE
  AND UAGG.UTENTE (+) = DOCU.UTENTE_AGGIORNAMENTO
  AND TIPO.ID_TIPOLOGIA = DOCU.ID_TIPOLOGIA
  AND CATE.ID_CATEGORIA = DOCU.ID_CATEGORIA
  AND RILE.ID_RILEVANZA = DOCU.ID_RILEVANZA
  AND ARGO.ID_ARGOMENTO = DOCU.ID_ARGOMENTO
 AND SEZI.ID_SEZIONE = DOCU.ID_SEZIONE
  AND ( Amv_Documento.get_diritto_doc(UINT.UTENTE, DOCU.ID_DOCUMENTO) IN('R','C','V','A')
 AND Amv_Documento.DOCUMENTO_DATA_CHK(DOCU.ID_DOCUMENTO) = 1
 OR  Amv_Documento.get_diritto_doc(UINT.UTENTE, DOCU.ID_DOCUMENTO) = 'U'
   OR  DOCU.AUTORE = UINT.UTENTE
  );

COMMENT ON TABLE AMV_VISTA_DOCUMENTI IS 'vista con tutti i campi decodificati';



