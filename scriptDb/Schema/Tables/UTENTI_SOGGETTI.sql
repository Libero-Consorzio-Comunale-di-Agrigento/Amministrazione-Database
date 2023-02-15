CREATE TABLE UTENTI_SOGGETTI
(
  UTENTE    VARCHAR2(8 BYTE)                    NOT NULL,
  SOGGETTO  NUMBER                              NOT NULL
)
TABLESPACE AD4
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            MAXSIZE          UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );

COMMENT ON TABLE UTENTI_SOGGETTI IS 'UTSO - Legame tra l''utente ed i dati anagrafici';



