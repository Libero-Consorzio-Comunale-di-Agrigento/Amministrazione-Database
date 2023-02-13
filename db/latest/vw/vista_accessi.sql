--liquibase formatted sql

--changeset mturra:201901301231_448 runOnChange:true stripComments:false


CREATE OR REPLACE FORCE VIEW VISTA_ACCESSI
(figlio, padre, descrizione, immagine, immagine_open, nodo, livello, utente)
AS
SELECT   TO_CHAR (  999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMM'))
  )
  )
   || '['
   || TO_CHAR (  99999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMMDD'))
  )
  )
   || '['
   || acce.modulo
   || '-'
   || acce.Istanza
   || '['
   || acce.Utente
 , TO_CHAR (  999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMM'))
  )
  )
   || '['
   || TO_CHAR (  99999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMMDD'))
  )
  )
   || '['
   || acce.modulo
   || '-'
   || acce.Istanza
 , uten.nominativo || ' (' || acce.Utente || ')'
 , '../common/images/AD4/Uten.gif', '../common/images/AD4/Uten.gif'
 , 'N'
  , 3
  , acce.Utente
 FROM ACCESSI acce, MODULI modu, ISTANZE ista, UTENTI uten
   WHERE modu.modulo(+) = acce.modulo
  AND ista.Istanza(+) = acce.Istanza
  AND uten.Utente(+) = acce.Utente
   AND acce.Utente <> 'GUEST'
   GROUP BY TRUNC (acce.DATA)
 , acce.modulo
 , acce.Istanza
 , acce.Utente
  , acce.ACCE_ID
 , modu.DESCRIZIONE
 , ista.DESCRIZIONE
 , uten.nominativo
   UNION
   SELECT   TO_CHAR (  999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMM'))
  )
  )
   || '['
   || TO_CHAR (  99999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMMDD'))
  )
  )
   || '['
   || acce.modulo
   || '-'
   || acce.Istanza
 , TO_CHAR (  999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMM'))
  )
  )
   || '['
   || TO_CHAR (  99999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMMDD'))
  )
  )
 , acce.modulo|| DECODE(modu.DESCRIZIONE, '', '', ': '||modu.DESCRIZIONE)|| ' - ' || acce.Istanza|| DECODE(ista.DESCRIZIONE, '', '', ': '||ista.DESCRIZIONE)
 , '../common/images/AD4/Serv.gif', '../common/images/AD4/Serv.gif'
 , 'S'
  , 2
  , ''
 FROM ACCESSI acce, MODULI modu, ISTANZE ista
   WHERE modu.modulo(+) = acce.modulo
 AND ista.Istanza(+) = acce.Istanza
   and acce.modulo <> 'SI4AU'
   GROUP BY TRUNC (acce.DATA)
 , acce.modulo
 , acce.Istanza
 , modu.DESCRIZIONE
 , ista.DESCRIZIONE
   UNION
   SELECT   TO_CHAR (  999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMM'))
  )
  )
   || '['
   || TO_CHAR (  99999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMMDD'))
  )
  )
 , TO_CHAR (  999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMM')))
  )
 , TO_CHAR (TRUNC (acce.DATA), 'dd/mm/yyyy')
 , '../common/images/AMV/cmsfolderclosed.gif'
 , '../common/images/AMV/cmslastfolderopen.gif'
  , 'S'
  , 1
  , ''
 FROM ACCESSI acce
   GROUP BY TRUNC (acce.DATA)
   UNION
   SELECT   TO_CHAR (  999999
   - (TO_NUMBER (TO_CHAR (TRUNC (acce.DATA), 'yyyyMM')))
  )
 , '0'
 , DECODE (TO_CHAR (TRUNC (acce.DATA), 'mm')
   , '01', 'Gennaio'
   , '02', 'Febbraio'
   , '03', 'Marzo'
   , '04', 'Aprile'
   , '05', 'Maggio'
   , '06', 'Giugno'
   , '07', 'Luglio'
   , '08', 'Agosto'
   , '09', 'Settembre'
   , '10', 'Ottobre'
   , '11', 'Novembre'
   , '12', 'Dicembre'
 )
   || ' '
   || TO_CHAR (TRUNC (acce.DATA), 'yyyy')
 , '../common/images/AMV/cmsfolderclosed.gif'
 , '../common/images/AMV/cmslastfolderopen.gif'
  , 'S'
  , 0
  , ''
 FROM ACCESSI acce
   GROUP BY TRUNC (acce.DATA);

