--liquibase formatted sql

--changeset mturra:201901301231_435 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW AMV_VISTA_DOCUMENTI
(id_documento, id_tipologia, des_tipologia, id_categoria, des_categoria, id_argomento, des_argomento, id_rilevanza, des_rilevanza, importanza, sequenza, id_area, titolo, tipo_testo, testo, link, immagine, icona, data_riferimento, inizio_pubblicazione, fine_pubblicazione, autore, nome_autore, data_inserimento, utente_aggiornamento, nome_utente, data_ultima_modifica, utente, edit, id_sezione, diritto, stato, revisione, des_sezione, id_documento_padre, xml, abstract)
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
comment on table AMV_VISTA_DOCUMENTI is 'vista con tutti i campi decodificati';
