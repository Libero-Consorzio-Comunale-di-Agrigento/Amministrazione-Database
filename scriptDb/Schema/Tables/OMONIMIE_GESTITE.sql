CREATE TABLE OMONIMIE_GESTITE
(
  ID_OMONIMIA            NUMBER(10)             NOT NULL,
  PRIMARIO               VARCHAR2(10 BYTE),
  SECONDARIO             VARCHAR2(10 BYTE),
  SCELTO_PRIMARIO        NUMBER(1),
  UNIFICATO              NUMBER(1)              DEFAULT 0,
  COPIATO                NUMBER(1)              DEFAULT 0,
  IGNORARE               NUMBER(1)              DEFAULT 0,
  NOTE                   VARCHAR2(4000 BYTE),
  NOMINATIVO_PRIMARIO    VARCHAR2(40 BYTE),
  NOMINATIVO_SECONDARIO  VARCHAR2(40 BYTE),
  UTENTE_AGG             VARCHAR2(8 BYTE),
  DATA_AGG               DATE
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

COMMENT ON TABLE OMONIMIE_GESTITE IS 'Vengono memorizzate le omonimie che sono state gestite in AD4 relativamente a appartenenza a gruppi, diritti di accesso, caratteristiche di accesso e soggetto abbinato.';

COMMENT ON COLUMN OMONIMIE_GESTITE.PRIMARIO IS 'Utente su cui riversare.';

COMMENT ON COLUMN OMONIMIE_GESTITE.SECONDARIO IS 'Utente di origine.';

COMMENT ON COLUMN OMONIMIE_GESTITE.SCELTO_PRIMARIO IS 'Se è stato scelto dall''utente o proposto dall''applicativo.';

COMMENT ON COLUMN OMONIMIE_GESTITE.UNIFICATO IS 'Rimane attivo solo utente su cui riversare.';

COMMENT ON COLUMN OMONIMIE_GESTITE.COPIATO IS 'Utente di origine rimane comunque attivo.';

COMMENT ON COLUMN OMONIMIE_GESTITE.IGNORARE IS 'Se non far vedere da applicativo.';

COMMENT ON COLUMN OMONIMIE_GESTITE.NOTE IS 'Commenti.';

COMMENT ON COLUMN OMONIMIE_GESTITE.NOMINATIVO_PRIMARIO IS 'Nominativo utente primario al momento dell''attività.';

COMMENT ON COLUMN OMONIMIE_GESTITE.NOMINATIVO_SECONDARIO IS 'Nominativo utente secondario al momento dell''attività.';



