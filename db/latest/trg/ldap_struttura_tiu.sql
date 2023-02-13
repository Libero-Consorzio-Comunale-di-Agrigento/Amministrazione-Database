--liquibase formatted sql

--changeset mturra:201901301231_398 runOnChange:true stripComments:false


CREATE OR REPLACE TRIGGER ldap_struttura_tiu
   BEFORE INSERT
   ON ldap_struttura
   FOR EACH ROW
BEGIN
   :new.dn := upper(:new.dn);
   IF :NEW.id_dn IS NULL
   THEN
      :NEW.id_dn := si4.next_id ('LDAP_STRUTTURA', 'ID_DN');
   END IF;
   :NEW.categoria := UPPER (:NEW.categoria);
END;
/

