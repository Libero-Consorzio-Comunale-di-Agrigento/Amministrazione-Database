--liquibase formatted sql

--changeset mturra:201901301231_23


create table AMV_DOCUMENTI_BLOB
(
  id_documento NUMBER(10) not null,
  revisione    NUMBER(10) default 0 not null,
  id_blob      NUMBER(10) not null
)
;
alter table AMV_DOCUMENTI_BLOB
  add constraint AMV_DOCUMENTI_BLOB_PK primary key (ID_DOCUMENTO, REVISIONE, ID_BLOB);
alter table AMV_DOCUMENTI_BLOB
  add constraint AMV_DOBL_BLOB_FK foreign key (ID_BLOB)
  references AMV_BLOB (ID_BLOB);
alter table AMV_DOCUMENTI_BLOB
  add constraint AMV_DOBL_DORE_FK foreign key (ID_DOCUMENTO, REVISIONE)
  references AMV_DOCUMENTI_REVISIONI (ID_DOCUMENTO, REVISIONE);

