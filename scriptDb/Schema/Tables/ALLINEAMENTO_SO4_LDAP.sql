CREATE TABLE ALLINEAMENTO_SO4_LDAP
(
  ID_ALLEGATO  NUMBER                           NOT NULL,
  NOME         VARCHAR2(2000 BYTE),
  NOTE         VARCHAR2(2000 BYTE),
  ALLEGATO     CLOB,
  DATA         DATE                             DEFAULT sysdate
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

COMMENT ON TABLE ALLINEAMENTO_SO4_LDAP IS 'ALSL - Log di allineamento fra So4 e Ldap';



