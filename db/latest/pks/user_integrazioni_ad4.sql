--liquibase formatted sql

--changeset snegorni:20190604_1229 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE PACKAGE USER_INTEGRAZIONI_AD4
authid current_user
IS
/******************************************************************************
 NOME:        user_integrazioni.
 DESCRIZIONE: Funzioni di amministrazione.
 ANNOTAZIONI:  Richiamata da utente che deve fare i sinonimi verso ad4 con:
 <user oracle ad4>.user_integrazioni_ad4.CREATE_PRIVATE_SYNONYMS('ALL',user);
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 000  04/06/2019 SNeg   Introdotta per gestione sinonimi
******************************************************************************/

   TYPE b_object_rec IS RECORD (
      object_name   ALL_OBJECTS.object_name%type
   );
   TYPE b_object_tab IS TABLE OF b_object_rec
      INDEX BY BINARY_INTEGER;
   b_index                     BINARY_INTEGER := 0;
   TabTVDB                     b_object_tab;
   TabPDB                      b_object_tab;
   TabTVCM                     b_object_tab;
   TabPCM                      b_object_tab;
   TabTVBS                     b_object_tab;
   TabPBS                      b_object_tab;
   TabTVASL                    b_object_tab;
   TabPXDK                     b_object_tab;
   TabAS4                      b_object_tab;

   v_user_proprietario varchar2(30);
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.04';
/******************************************************************************
 NOME:        VERSIONE
 DESCRIZIONE: Restituisce la versione e la data di distribuzione del package.
 PARAMETRI:   --
 RITORNA:     stringa varchar2 contenente versione e data.
 NOTE:        Il secondo numero della versione corrisponde alla revisione
              del package.
******************************************************************************/
   FUNCTION VERSIONE
      RETURN VARCHAR2;
   PROCEDURE CREATE_SYNONYM (
      p_user                    VARCHAR2
    , p_synonym_name            VARCHAR2
    , p_object                  VARCHAR2
    , p_error          IN OUT   VARCHAR2
    , p_owner                   VARCHAR2 default v_user_proprietario
   );

  PROCEDURE CREATE_PRIVATE_SYNONYMS (p_what IN VARCHAR2 DEFAULT 'ALL'
   , p_user IN VARCHAR2 DEFAULT  null);
END user_integrazioni_ad4;
/
