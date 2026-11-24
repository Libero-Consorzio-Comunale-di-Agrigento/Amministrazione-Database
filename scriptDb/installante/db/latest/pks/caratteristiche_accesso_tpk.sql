--liquibase formatted sql

--changeset mturra:201901301231_221 runOnChange:true stripComments:false endDelimiter:/


CREATE OR REPLACE package caratteristiche_accesso_tpk is /* MASTER_LINK */
/******************************************************************************
 NOME:        caratteristiche_accesso_tpk
 DESCRIZIONE: Gestione tabella CARATTERISTICHE_ACCESSO.
 ANNOTAZIONI: .
 REVISIONI:   Table Revision: 21/09/2018 11:33:28
              SiaPKGen Revision: V1.05.014.
              SiaTPKDeclare Revision: V1.17.001.
 <CODE>
 Rev.  Data        Autore      Descrizione.
 00    21/09/2018  snegroni  Generazione automatica.
 </CODE>
******************************************************************************/
   -- Revisione del Package
   s_revisione constant AFC.t_revision := 'V1.00';
   s_table_name constant AFC.t_object_name := 'CARATTERISTICHE_ACCESSO';
   subtype t_rowtype is CARATTERISTICHE_ACCESSO%rowtype;
   -- Tipo del record primary key
subtype t_caac_id  is CARATTERISTICHE_ACCESSO.caac_id%type;
   type t_PK is record
   (
caac_id  t_caac_id
   );
   -- Versione e revisione
   function versione /* SLAVE_COPY */
   return varchar2;
   pragma restrict_references( versione, WNDS );
   -- Costruttore di record chiave
   function PK /* SLAVE_COPY */
   (
    p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return t_PK;
   pragma restrict_references( PK, WNDS );
   -- Controllo integrita chiave
   function can_handle /* SLAVE_COPY */
   (
    p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return number;
   pragma restrict_references( can_handle, WNDS );
   -- Controllo integrita chiave
   -- wrapper boolean
   function canHandle /* SLAVE_COPY */
   (
    p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return boolean;
   pragma restrict_references( canhandle, WNDS );
    -- Esistenza riga con chiave indicata
   function exists_id /* SLAVE_COPY */
   (
    p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return number;
   pragma restrict_references( exists_id, WNDS );
   -- Esistenza riga con chiave indicata
   -- wrapper boolean
   function existsId /* SLAVE_COPY */
   (
    p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return boolean;
   pragma restrict_references( existsid, WNDS );
   -- Inserimento di una riga
   procedure ins
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type default null
   , p_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type
   , p_progetto  in CARATTERISTICHE_ACCESSO.progetto%type default null
   , p_istanza  in CARATTERISTICHE_ACCESSO.istanza%type default null
   , p_modulo  in CARATTERISTICHE_ACCESSO.modulo%type default null
   , p_utente  in CARATTERISTICHE_ACCESSO.utente%type default null
   , p_accesso  in CARATTERISTICHE_ACCESSO.accesso%type default null
   , p_accesso_se  in CARATTERISTICHE_ACCESSO.accesso_se%type default null
   , p_traccia  in CARATTERISTICHE_ACCESSO.traccia%type default null
   , p_giorni_traccia  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default null
   , p_tentativi_password  in CARATTERISTICHE_ACCESSO.tentativi_password%type default null
   , p_validita_password  in CARATTERISTICHE_ACCESSO.validita_password%type default null
   , p_single_sign_on  in CARATTERISTICHE_ACCESSO.single_sign_on%type default 'SI'
   , p_sleep  in CARATTERISTICHE_ACCESSO.sleep%type default null
   , p_ldap  in CARATTERISTICHE_ACCESSO.ldap%type default 'NO'
   , p_min_lunghezza_pwd  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default 0
   , p_mod_pwd_primo_accesso  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default 'NO'
   , p_archiviazione_traccia  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default 'NO'
   , p_dislocazione_traccia  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default null
   , p_ammessi_car_speciali_pwd  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default 'SI'
   , p_numeri_obb_pwd  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default 'NO'
   , p_gg_prima_riutilizzo_pwd  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default null
   );
   function ins  /*+ SOA  */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type default null
   , p_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type
   , p_progetto  in CARATTERISTICHE_ACCESSO.progetto%type default null
   , p_istanza  in CARATTERISTICHE_ACCESSO.istanza%type default null
   , p_modulo  in CARATTERISTICHE_ACCESSO.modulo%type default null
   , p_utente  in CARATTERISTICHE_ACCESSO.utente%type default null
   , p_accesso  in CARATTERISTICHE_ACCESSO.accesso%type default null
   , p_accesso_se  in CARATTERISTICHE_ACCESSO.accesso_se%type default null
   , p_traccia  in CARATTERISTICHE_ACCESSO.traccia%type default null
   , p_giorni_traccia  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default null
   , p_tentativi_password  in CARATTERISTICHE_ACCESSO.tentativi_password%type default null
   , p_validita_password  in CARATTERISTICHE_ACCESSO.validita_password%type default null
   , p_single_sign_on  in CARATTERISTICHE_ACCESSO.single_sign_on%type default 'SI'
   , p_sleep  in CARATTERISTICHE_ACCESSO.sleep%type default null
   , p_ldap  in CARATTERISTICHE_ACCESSO.ldap%type default 'NO'
   , p_min_lunghezza_pwd  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default 0
   , p_mod_pwd_primo_accesso  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default 'NO'
   , p_archiviazione_traccia  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default 'NO'
   , p_dislocazione_traccia  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default null
   , p_ammessi_car_speciali_pwd  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default 'SI'
   , p_numeri_obb_pwd  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default 'NO'
   , p_gg_prima_riutilizzo_pwd  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default null
   ) return number;
   -- Aggiornamento di una riga
   procedure upd  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_NEW_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_OLD_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type default null
   , p_NEW_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type default afc.default_null('CARATTERISTICHE_ACCESSO.tipo_accesso')
   , p_OLD_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type default null
   , p_NEW_progetto  in CARATTERISTICHE_ACCESSO.progetto%type default afc.default_null('CARATTERISTICHE_ACCESSO.progetto')
   , p_OLD_progetto  in CARATTERISTICHE_ACCESSO.progetto%type default null
   , p_NEW_istanza  in CARATTERISTICHE_ACCESSO.istanza%type default afc.default_null('CARATTERISTICHE_ACCESSO.istanza')
   , p_OLD_istanza  in CARATTERISTICHE_ACCESSO.istanza%type default null
   , p_NEW_modulo  in CARATTERISTICHE_ACCESSO.modulo%type default afc.default_null('CARATTERISTICHE_ACCESSO.modulo')
   , p_OLD_modulo  in CARATTERISTICHE_ACCESSO.modulo%type default null
   , p_NEW_utente  in CARATTERISTICHE_ACCESSO.utente%type default afc.default_null('CARATTERISTICHE_ACCESSO.utente')
   , p_OLD_utente  in CARATTERISTICHE_ACCESSO.utente%type default null
   , p_NEW_accesso  in CARATTERISTICHE_ACCESSO.accesso%type default afc.default_null('CARATTERISTICHE_ACCESSO.accesso')
   , p_OLD_accesso  in CARATTERISTICHE_ACCESSO.accesso%type default null
   , p_NEW_accesso_se  in CARATTERISTICHE_ACCESSO.accesso_se%type default afc.default_null('CARATTERISTICHE_ACCESSO.accesso_se')
   , p_OLD_accesso_se  in CARATTERISTICHE_ACCESSO.accesso_se%type default null
   , p_NEW_traccia  in CARATTERISTICHE_ACCESSO.traccia%type default afc.default_null('CARATTERISTICHE_ACCESSO.traccia')
   , p_OLD_traccia  in CARATTERISTICHE_ACCESSO.traccia%type default null
   , p_NEW_giorni_traccia  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default afc.default_null('CARATTERISTICHE_ACCESSO.giorni_traccia')
   , p_OLD_giorni_traccia  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default null
   , p_NEW_tentativi_password  in CARATTERISTICHE_ACCESSO.tentativi_password%type default afc.default_null('CARATTERISTICHE_ACCESSO.tentativi_password')
   , p_OLD_tentativi_password  in CARATTERISTICHE_ACCESSO.tentativi_password%type default null
   , p_NEW_validita_password  in CARATTERISTICHE_ACCESSO.validita_password%type default afc.default_null('CARATTERISTICHE_ACCESSO.validita_password')
   , p_OLD_validita_password  in CARATTERISTICHE_ACCESSO.validita_password%type default null
   , p_NEW_single_sign_on  in CARATTERISTICHE_ACCESSO.single_sign_on%type default afc.default_null('CARATTERISTICHE_ACCESSO.single_sign_on')
   , p_OLD_single_sign_on  in CARATTERISTICHE_ACCESSO.single_sign_on%type default null
   , p_NEW_sleep  in CARATTERISTICHE_ACCESSO.sleep%type default afc.default_null('CARATTERISTICHE_ACCESSO.sleep')
   , p_OLD_sleep  in CARATTERISTICHE_ACCESSO.sleep%type default null
   , p_NEW_ldap  in CARATTERISTICHE_ACCESSO.ldap%type default afc.default_null('CARATTERISTICHE_ACCESSO.ldap')
   , p_OLD_ldap  in CARATTERISTICHE_ACCESSO.ldap%type default null
   , p_NEW_min_lunghezza_pwd  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default afc.default_null('CARATTERISTICHE_ACCESSO.min_lunghezza_pwd')
   , p_OLD_min_lunghezza_pwd  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default null
   , p_NEW_mod_pwd_primo_accesso  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default afc.default_null('CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso')
   , p_OLD_mod_pwd_primo_accesso  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default null
   , p_NEW_archiviazione_traccia  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default afc.default_null('CARATTERISTICHE_ACCESSO.archiviazione_traccia')
   , p_OLD_archiviazione_traccia  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default null
   , p_NEW_dislocazione_traccia  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default afc.default_null('CARATTERISTICHE_ACCESSO.dislocazione_traccia')
   , p_OLD_dislocazione_traccia  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default null
   , p_NEW_ammessi_car_speciali_pwd  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default afc.default_null('CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd')
   , p_OLD_ammessi_car_speciali_pwd  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default null
   , p_NEW_numeri_obb_pwd  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default afc.default_null('CARATTERISTICHE_ACCESSO.numeri_obb_pwd')
   , p_OLD_numeri_obb_pwd  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default null
   , p_NEW_gg_prima_riutilizzo_pwd  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default afc.default_null('CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd')
   , p_OLD_gg_prima_riutilizzo_pwd  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default null
   );
   -- Aggiornamento del campo di una riga
   procedure upd_column
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_column         in varchar2
   , p_value          in varchar2 default null
   , p_literal_value  in number   default 1
   );
   -- Cancellazione di una riga
   procedure del  /*+ SOA  */
   (
     p_check_OLD  in integer default 0
   , p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_tipo_accesso  in CARATTERISTICHE_ACCESSO.tipo_accesso%type default null
   , p_progetto  in CARATTERISTICHE_ACCESSO.progetto%type default null
   , p_istanza  in CARATTERISTICHE_ACCESSO.istanza%type default null
   , p_modulo  in CARATTERISTICHE_ACCESSO.modulo%type default null
   , p_utente  in CARATTERISTICHE_ACCESSO.utente%type default null
   , p_accesso  in CARATTERISTICHE_ACCESSO.accesso%type default null
   , p_accesso_se  in CARATTERISTICHE_ACCESSO.accesso_se%type default null
   , p_traccia  in CARATTERISTICHE_ACCESSO.traccia%type default null
   , p_giorni_traccia  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default null
   , p_tentativi_password  in CARATTERISTICHE_ACCESSO.tentativi_password%type default null
   , p_validita_password  in CARATTERISTICHE_ACCESSO.validita_password%type default null
   , p_single_sign_on  in CARATTERISTICHE_ACCESSO.single_sign_on%type default null
   , p_sleep  in CARATTERISTICHE_ACCESSO.sleep%type default null
   , p_ldap  in CARATTERISTICHE_ACCESSO.ldap%type default null
   , p_min_lunghezza_pwd  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default null
   , p_mod_pwd_primo_accesso  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default null
   , p_archiviazione_traccia  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default null
   , p_dislocazione_traccia  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default null
   , p_ammessi_car_speciali_pwd  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default null
   , p_numeri_obb_pwd  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default null
   , p_gg_prima_riutilizzo_pwd  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default null
   );
-- Getter per attributo tipo_accesso di riga identificata da chiave
   function get_tipo_accesso /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.tipo_accesso%type;
   pragma restrict_references( get_tipo_accesso, WNDS );
-- Getter per attributo progetto di riga identificata da chiave
   function get_progetto /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.progetto%type;
   pragma restrict_references( get_progetto, WNDS );
-- Getter per attributo istanza di riga identificata da chiave
   function get_istanza /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.istanza%type;
   pragma restrict_references( get_istanza, WNDS );
-- Getter per attributo modulo di riga identificata da chiave
   function get_modulo /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.modulo%type;
   pragma restrict_references( get_modulo, WNDS );
-- Getter per attributo utente di riga identificata da chiave
   function get_utente /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.utente%type;
   pragma restrict_references( get_utente, WNDS );
-- Getter per attributo accesso di riga identificata da chiave
   function get_accesso /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.accesso%type;
   pragma restrict_references( get_accesso, WNDS );
-- Getter per attributo accesso_se di riga identificata da chiave
   function get_accesso_se /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.accesso_se%type;
   pragma restrict_references( get_accesso_se, WNDS );
-- Getter per attributo traccia di riga identificata da chiave
   function get_traccia /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.traccia%type;
   pragma restrict_references( get_traccia, WNDS );
-- Getter per attributo giorni_traccia di riga identificata da chiave
   function get_giorni_traccia /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.giorni_traccia%type;
   pragma restrict_references( get_giorni_traccia, WNDS );
-- Getter per attributo tentativi_password di riga identificata da chiave
   function get_tentativi_password /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.tentativi_password%type;
   pragma restrict_references( get_tentativi_password, WNDS );
-- Getter per attributo validita_password di riga identificata da chiave
   function get_validita_password /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.validita_password%type;
   pragma restrict_references( get_validita_password, WNDS );
-- Getter per attributo single_sign_on di riga identificata da chiave
   function get_single_sign_on /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.single_sign_on%type;
   pragma restrict_references( get_single_sign_on, WNDS );
-- Getter per attributo sleep di riga identificata da chiave
   function get_sleep /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.sleep%type;
   pragma restrict_references( get_sleep, WNDS );
-- Getter per attributo ldap di riga identificata da chiave
   function get_ldap /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.ldap%type;
   pragma restrict_references( get_ldap, WNDS );
-- Getter per attributo min_lunghezza_pwd di riga identificata da chiave
   function get_min_lunghezza_pwd /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type;
   pragma restrict_references( get_min_lunghezza_pwd, WNDS );
-- Getter per attributo mod_pwd_primo_accesso di riga identificata da chiave
   function get_mod_pwd_primo_accesso /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type;
   pragma restrict_references( get_mod_pwd_primo_accesso, WNDS );
-- Getter per attributo archiviazione_traccia di riga identificata da chiave
   function get_archiviazione_traccia /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.archiviazione_traccia%type;
   pragma restrict_references( get_archiviazione_traccia, WNDS );
-- Getter per attributo dislocazione_traccia di riga identificata da chiave
   function get_dislocazione_traccia /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.dislocazione_traccia%type;
   pragma restrict_references( get_dislocazione_traccia, WNDS );
-- Getter per attributo ammessi_car_speciali_pwd di riga identificata da chiave
   function get_ammessi_car_speciali_pwd /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type;
   pragma restrict_references( get_ammessi_car_speciali_pwd, WNDS );
-- Getter per attributo numeri_obb_pwd di riga identificata da chiave
   function get_numeri_obb_pwd /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type;
   pragma restrict_references( get_numeri_obb_pwd, WNDS );
-- Getter per attributo gg_prima_riutilizzo_pwd di riga identificata da chiave
   function get_gg_prima_riutilizzo_pwd /* SLAVE_COPY */
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   ) return CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type;
   pragma restrict_references( get_gg_prima_riutilizzo_pwd, WNDS );
-- Setter per attributo caac_id di riga identificata da chiave
   procedure set_caac_id
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.caac_id%type default null
   );
-- Setter per attributo tipo_accesso di riga identificata da chiave
   procedure set_tipo_accesso
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.tipo_accesso%type default null
   );
-- Setter per attributo progetto di riga identificata da chiave
   procedure set_progetto
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.progetto%type default null
   );
-- Setter per attributo istanza di riga identificata da chiave
   procedure set_istanza
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.istanza%type default null
   );
-- Setter per attributo modulo di riga identificata da chiave
   procedure set_modulo
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.modulo%type default null
   );
-- Setter per attributo utente di riga identificata da chiave
   procedure set_utente
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.utente%type default null
   );
-- Setter per attributo accesso di riga identificata da chiave
   procedure set_accesso
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.accesso%type default null
   );
-- Setter per attributo accesso_se di riga identificata da chiave
   procedure set_accesso_se
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.accesso_se%type default null
   );
-- Setter per attributo traccia di riga identificata da chiave
   procedure set_traccia
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.traccia%type default null
   );
-- Setter per attributo giorni_traccia di riga identificata da chiave
   procedure set_giorni_traccia
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.giorni_traccia%type default null
   );
-- Setter per attributo tentativi_password di riga identificata da chiave
   procedure set_tentativi_password
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.tentativi_password%type default null
   );
-- Setter per attributo validita_password di riga identificata da chiave
   procedure set_validita_password
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.validita_password%type default null
   );
-- Setter per attributo single_sign_on di riga identificata da chiave
   procedure set_single_sign_on
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.single_sign_on%type default null
   );
-- Setter per attributo sleep di riga identificata da chiave
   procedure set_sleep
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.sleep%type default null
   );
-- Setter per attributo ldap di riga identificata da chiave
   procedure set_ldap
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.ldap%type default null
   );
-- Setter per attributo min_lunghezza_pwd di riga identificata da chiave
   procedure set_min_lunghezza_pwd
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.min_lunghezza_pwd%type default null
   );
-- Setter per attributo mod_pwd_primo_accesso di riga identificata da chiave
   procedure set_mod_pwd_primo_accesso
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.mod_pwd_primo_accesso%type default null
   );
-- Setter per attributo archiviazione_traccia di riga identificata da chiave
   procedure set_archiviazione_traccia
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.archiviazione_traccia%type default null
   );
-- Setter per attributo dislocazione_traccia di riga identificata da chiave
   procedure set_dislocazione_traccia
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.dislocazione_traccia%type default null
   );
-- Setter per attributo ammessi_car_speciali_pwd di riga identificata da chiave
   procedure set_ammessi_car_speciali_pwd
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.ammessi_car_speciali_pwd%type default null
   );
-- Setter per attributo numeri_obb_pwd di riga identificata da chiave
   procedure set_numeri_obb_pwd
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.numeri_obb_pwd%type default null
   );
-- Setter per attributo gg_prima_riutilizzo_pwd di riga identificata da chiave
   procedure set_gg_prima_riutilizzo_pwd
   (
     p_caac_id  in CARATTERISTICHE_ACCESSO.caac_id%type
   , p_value  in CARATTERISTICHE_ACCESSO.gg_prima_riutilizzo_pwd%type default null
   );
   -- where_condition per statement di ricerca
   function where_condition /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_caac_id  in varchar2 default null
   , p_tipo_accesso  in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_istanza  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_utente  in varchar2 default null
   , p_accesso  in varchar2 default null
   , p_accesso_se  in varchar2 default null
   , p_traccia  in varchar2 default null
   , p_giorni_traccia  in varchar2 default null
   , p_tentativi_password  in varchar2 default null
   , p_validita_password  in varchar2 default null
   , p_single_sign_on  in varchar2 default null
   , p_sleep  in varchar2 default null
   , p_ldap  in varchar2 default null
   , p_min_lunghezza_pwd  in varchar2 default null
   , p_mod_pwd_primo_accesso  in varchar2 default null
   , p_archiviazione_traccia  in varchar2 default null
   , p_dislocazione_traccia  in varchar2 default null
   , p_ammessi_car_speciali_pwd  in varchar2 default null
   , p_numeri_obb_pwd  in varchar2 default null
   , p_gg_prima_riutilizzo_pwd  in varchar2 default null
   ) return AFC.t_statement;
   -- righe corrispondenti alla selezione indicata
   function get_rows  /*+ SOA  */ /* SLAVE_COPY */
   ( p_QBE  in number default 0
   , p_other_condition in varchar2 default null
   , p_order_by in varchar2 default null
   , p_extra_columns in varchar2 default null
   , p_extra_condition in varchar2 default null
   , p_caac_id  in varchar2 default null
   , p_tipo_accesso  in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_istanza  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_utente  in varchar2 default null
   , p_accesso  in varchar2 default null
   , p_accesso_se  in varchar2 default null
   , p_traccia  in varchar2 default null
   , p_giorni_traccia  in varchar2 default null
   , p_tentativi_password  in varchar2 default null
   , p_validita_password  in varchar2 default null
   , p_single_sign_on  in varchar2 default null
   , p_sleep  in varchar2 default null
   , p_ldap  in varchar2 default null
   , p_min_lunghezza_pwd  in varchar2 default null
   , p_mod_pwd_primo_accesso  in varchar2 default null
   , p_archiviazione_traccia  in varchar2 default null
   , p_dislocazione_traccia  in varchar2 default null
   , p_ammessi_car_speciali_pwd  in varchar2 default null
   , p_numeri_obb_pwd  in varchar2 default null
   , p_gg_prima_riutilizzo_pwd  in varchar2 default null
   ) return AFC.t_ref_cursor;
   -- Numero di righe corrispondente alla selezione indicata
   -- Almeno un attributo deve essere valido (non null)
   function count_rows /* SLAVE_COPY */
   ( p_QBE in number default 0
   , p_other_condition in varchar2 default null
   , p_caac_id  in varchar2 default null
   , p_tipo_accesso  in varchar2 default null
   , p_progetto  in varchar2 default null
   , p_istanza  in varchar2 default null
   , p_modulo  in varchar2 default null
   , p_utente  in varchar2 default null
   , p_accesso  in varchar2 default null
   , p_accesso_se  in varchar2 default null
   , p_traccia  in varchar2 default null
   , p_giorni_traccia  in varchar2 default null
   , p_tentativi_password  in varchar2 default null
   , p_validita_password  in varchar2 default null
   , p_single_sign_on  in varchar2 default null
   , p_sleep  in varchar2 default null
   , p_ldap  in varchar2 default null
   , p_min_lunghezza_pwd  in varchar2 default null
   , p_mod_pwd_primo_accesso  in varchar2 default null
   , p_archiviazione_traccia  in varchar2 default null
   , p_dislocazione_traccia  in varchar2 default null
   , p_ammessi_car_speciali_pwd  in varchar2 default null
   , p_numeri_obb_pwd  in varchar2 default null
   , p_gg_prima_riutilizzo_pwd  in varchar2 default null
   ) return integer;
end caratteristiche_accesso_tpk;
/
