//AD4_CARATTERISTICHE_ACCESSORow: import @38-057DA08A
package ad4web.AD4CopiaUtenti;

import java.util.*;
import com.codecharge.db.*;
//End AD4_CARATTERISTICHE_ACCESSORow: import

//AD4_CARATTERISTICHE_ACCESSORow: class head @38-05775727
public class AD4_CARATTERISTICHE_ACCESSORow {
//End AD4_CARATTERISTICHE_ACCESSORow: class head

//AD4_CARATTERISTICHE_ACCESSORow: declare fiels @38-3DACCD80
    private TextField CAAC_DESC = new TextField("CAAC_DESC", "CAAC_DESC");
    private TextField DESC_ACCESSO = new TextField("DESC_ACCESSO", "DESC_ACCESSO");
    private TextField OLD_CAAC_ID = new TextField("OLD_CAAC_ID", "OLD_CAAC_ID");
    private TextField CAAC_ID = new TextField("CAAC_ID", "CAAC_ID");
    private TextField OLD_MODULO = new TextField("OLD_MODULO", "OLD_MODULO");
    private TextField MODULO = new TextField("MODULO", "MODULO");
    private TextField INSERT_UPDATE = new TextField("INSERT_UPDATE", "INSERT_UPDATE");
    private TextField JOB_ATTIVO = new TextField("JOB_ATTIVO", "JOB_ATTIVO");
    private TextField DESC_PASSWORD = new TextField("DESC_PASSWORD", "DESC_PASSWORD");
    private TextField OLD_PROGETTO = new TextField("OLD_PROGETTO", "OLD_PROGETTO");
    private TextField PROGETTO = new TextField("PROGETTO", "PROGETTO");
    private TextField OLD_UTENTE = new TextField("OLD_UTENTE", "OLD_UTENTE");
    private TextField UTENTE = new TextField("UTENTE", "UTENTE");
    private TextField TIPO_ACCESSO = new TextField("TIPO_ACCESSO", "TIPO_ACCESSO");
    private TextField DESC_AUTENTICAZIONE = new TextField("DESC_AUTENTICAZIONE", "DESC_AUTENTICAZIONE");
    private TextField OLD_ISTANZA = new TextField("OLD_ISTANZA", "OLD_ISTANZA");
    private TextField ISTANZA = new TextField("ISTANZA", "ISTANZA");
    private TextField CAAC_DESC_Hidden = new TextField("CAAC_DESC_Hidden", "CAAC_DESC");
    private TextField ACCESSO_SE = new TextField("ACCESSO_SE", "ACCESSO_SE");
    private TextField ACCESSO_SE_DESC = new TextField("ACCESSO_SE_DESC", "ACCESSO_SE_DESC");
    private TextField ACCESSO = new TextField("ACCESSO", "ACCESSO");
    private TextField TRACCIA = new TextField("TRACCIA", "TRACCIA");
    private LongField GIORNI_TRACCIA = new LongField("GIORNI_TRACCIA", "GIORNI_TRACCIA");
    private LongField NUM_FILE_ARCHIVIATI = new LongField("NUM_FILE_ARCHIVIATI", "NUM_FILE_ARCHIVIATI");
    private DoubleField DIM_FILE_ARCHIVIATI = new DoubleField("DIM_FILE_ARCHIVIATI", "DIM_FILE_ARCHIVIATI");
    private LongField SPOSTA_FILE_ARCH = new LongField("SPOSTA_FILE_ARCH", "SPOSTA_FILE_ARCH");
    private TextField ARCHIVIAZIONE_TRACCIA = new TextField("ARCHIVIAZIONE_TRACCIA", "ARCHIVIAZIONE_TRACCIA");
    private TextField LABEL_ARCH = new TextField("LABEL_ARCH", "LABEL_ARCH");
    private TextField DISLOCAZIONE_TRACCIA = new TextField("DISLOCAZIONE_TRACCIA", "DISLOCAZIONE_TRACCIA");
    private TextField INPUT_ARCH_CLASS = new TextField("INPUT_ARCH_CLASS", "INPUT_ARCH_CLASS");
    private TextField CKB_ARCH_CLASS = new TextField("CKB_ARCH_CLASS", "CKB_ARCH_CLASS");
    private TextField DISLOCAZIONE_TRACCIA_OLD = new TextField("DISLOCAZIONE_TRACCIA_OLD", "DISLOCAZIONE_TRACCIA_OLD");
    private LongField SLEEP = new LongField("SLEEP", "SLEEP");
    private LongField VALIDITA_PASSWORD = new LongField("VALIDITA_PASSWORD", "VALIDITA_PASSWORD");
    private LongField TENTATIVI_PASSWORD = new LongField("TENTATIVI_PASSWORD", "TENTATIVI_PASSWORD");
    private TextField MOD_PWD_PRIMO_ACCESSO = new TextField("MOD_PWD_PRIMO_ACCESSO", "MOD_PWD_PRIMO_ACCESSO");
    private LongField MIN_LUNGHEZZA_PWD = new LongField("MIN_LUNGHEZZA_PWD", "MIN_LUNGHEZZA_PWD");
    private LongField MIN_LUNGHEZZA_PWD_OLD = new LongField("MIN_LUNGHEZZA_PWD_OLD", "MIN_LUNGHEZZA_PWD_OLD");
    private TextField AMMESSI_CAR_SPECIALI_PWD = new TextField("AMMESSI_CAR_SPECIALI_PWD", "AMMESSI_CAR_SPECIALI_PWD");
    private TextField AMMESSI_CAR_SPECIALI_PWD_OLD = new TextField("AMMESSI_CAR_SPECIALI_PWD_OLD", "AMMESSI_CAR_SPECIALI_PWD_OLD");
    private TextField NUMERI_OBB_PWD = new TextField("NUMERI_OBB_PWD", "NUMERI_OBB_PWD");
    private TextField NUMERI_OBB_PWD_OLD = new TextField("NUMERI_OBB_PWD_OLD", "NUMERI_OBB_PWD_OLD");
    private TextField LDAP = new TextField("LDAP", "LDAP");
    private TextField SINGLE_SIGN_ON = new TextField("SINGLE_SIGN_ON", "SINGLE_SIGN_ON");
    private TextField DESC_DETTAGLI_LABEL = new TextField("DESC_DETTAGLI_LABEL", "DESC_DETTAGLI");
    private TextField DESC_DETTAGLI = new TextField("DESC_DETTAGLI", "DESC_DETTAGLI");
//End AD4_CARATTERISTICHE_ACCESSORow: declare fiels

//AD4_CARATTERISTICHE_ACCESSORow: constructor @38-DB845149
    public AD4_CARATTERISTICHE_ACCESSORow() {
    }
//End AD4_CARATTERISTICHE_ACCESSORow: constructor

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of CAAC_DESC @80-D71EE3CC
    public TextField getCAAC_DESCField() {
        return CAAC_DESC;
    }

    public String getCAAC_DESC() {
        return CAAC_DESC.getValue();
    }

    public void setCAAC_DESC(String value) {
        this.CAAC_DESC.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of CAAC_DESC

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of DESC_ACCESSO @166-54407892
    public TextField getDESC_ACCESSOField() {
        return DESC_ACCESSO;
    }

    public String getDESC_ACCESSO() {
        return DESC_ACCESSO.getValue();
    }

    public void setDESC_ACCESSO(String value) {
        this.DESC_ACCESSO.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of DESC_ACCESSO

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of OLD_CAAC_ID @170-C6DF052C
    public TextField getOLD_CAAC_IDField() {
        return OLD_CAAC_ID;
    }

    public String getOLD_CAAC_ID() {
        return OLD_CAAC_ID.getValue();
    }

    public void setOLD_CAAC_ID(String value) {
        this.OLD_CAAC_ID.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of OLD_CAAC_ID

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of CAAC_ID @178-B850CA09
    public TextField getCAAC_IDField() {
        return CAAC_ID;
    }

    public String getCAAC_ID() {
        return CAAC_ID.getValue();
    }

    public void setCAAC_ID(String value) {
        this.CAAC_ID.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of CAAC_ID

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of OLD_MODULO @173-A7223DCD
    public TextField getOLD_MODULOField() {
        return OLD_MODULO;
    }

    public String getOLD_MODULO() {
        return OLD_MODULO.getValue();
    }

    public void setOLD_MODULO(String value) {
        this.OLD_MODULO.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of OLD_MODULO

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of MODULO @179-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of MODULO

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of INSERT_UPDATE @176-88976AC4
    public TextField getINSERT_UPDATEField() {
        return INSERT_UPDATE;
    }

    public String getINSERT_UPDATE() {
        return INSERT_UPDATE.getValue();
    }

    public void setINSERT_UPDATE(String value) {
        this.INSERT_UPDATE.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of INSERT_UPDATE

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of JOB_ATTIVO @214-A90ADB06
    public TextField getJOB_ATTIVOField() {
        return JOB_ATTIVO;
    }

    public String getJOB_ATTIVO() {
        return JOB_ATTIVO.getValue();
    }

    public void setJOB_ATTIVO(String value) {
        this.JOB_ATTIVO.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of JOB_ATTIVO

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of DESC_PASSWORD @167-D53BD84A
    public TextField getDESC_PASSWORDField() {
        return DESC_PASSWORD;
    }

    public String getDESC_PASSWORD() {
        return DESC_PASSWORD.getValue();
    }

    public void setDESC_PASSWORD(String value) {
        this.DESC_PASSWORD.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of DESC_PASSWORD

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of OLD_PROGETTO @171-F5D62706
    public TextField getOLD_PROGETTOField() {
        return OLD_PROGETTO;
    }

    public String getOLD_PROGETTO() {
        return OLD_PROGETTO.getValue();
    }

    public void setOLD_PROGETTO(String value) {
        this.OLD_PROGETTO.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of OLD_PROGETTO

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of PROGETTO @180-7637616D
    public TextField getPROGETTOField() {
        return PROGETTO;
    }

    public String getPROGETTO() {
        return PROGETTO.getValue();
    }

    public void setPROGETTO(String value) {
        this.PROGETTO.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of PROGETTO

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of OLD_UTENTE @174-9B7AA864
    public TextField getOLD_UTENTEField() {
        return OLD_UTENTE;
    }

    public String getOLD_UTENTE() {
        return OLD_UTENTE.getValue();
    }

    public void setOLD_UTENTE(String value) {
        this.OLD_UTENTE.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of OLD_UTENTE

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of UTENTE @181-95517C36
    public TextField getUTENTEField() {
        return UTENTE;
    }

    public String getUTENTE() {
        return UTENTE.getValue();
    }

    public void setUTENTE(String value) {
        this.UTENTE.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of UTENTE

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of TIPO_ACCESSO @177-D9722F75
    public TextField getTIPO_ACCESSOField() {
        return TIPO_ACCESSO;
    }

    public String getTIPO_ACCESSO() {
        return TIPO_ACCESSO.getValue();
    }

    public void setTIPO_ACCESSO(String value) {
        this.TIPO_ACCESSO.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of TIPO_ACCESSO

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of DESC_AUTENTICAZIONE @168-59DC1FDC
    public TextField getDESC_AUTENTICAZIONEField() {
        return DESC_AUTENTICAZIONE;
    }

    public String getDESC_AUTENTICAZIONE() {
        return DESC_AUTENTICAZIONE.getValue();
    }

    public void setDESC_AUTENTICAZIONE(String value) {
        this.DESC_AUTENTICAZIONE.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of DESC_AUTENTICAZIONE

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of OLD_ISTANZA @172-3CC89E96
    public TextField getOLD_ISTANZAField() {
        return OLD_ISTANZA;
    }

    public String getOLD_ISTANZA() {
        return OLD_ISTANZA.getValue();
    }

    public void setOLD_ISTANZA(String value) {
        this.OLD_ISTANZA.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of OLD_ISTANZA

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of ISTANZA @182-CC23EEBF
    public TextField getISTANZAField() {
        return ISTANZA;
    }

    public String getISTANZA() {
        return ISTANZA.getValue();
    }

    public void setISTANZA(String value) {
        this.ISTANZA.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of ISTANZA

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of CAAC_DESC_Hidden @175-8369EFCF
    public TextField getCAAC_DESC_HiddenField() {
        return CAAC_DESC_Hidden;
    }

    public String getCAAC_DESC_Hidden() {
        return CAAC_DESC_Hidden.getValue();
    }

    public void setCAAC_DESC_Hidden(String value) {
        this.CAAC_DESC_Hidden.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of CAAC_DESC_Hidden

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of ACCESSO_SE @196-3BE9C85A
    public TextField getACCESSO_SEField() {
        return ACCESSO_SE;
    }

    public String getACCESSO_SE() {
        return ACCESSO_SE.getValue();
    }

    public void setACCESSO_SE(String value) {
        this.ACCESSO_SE.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of ACCESSO_SE

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of ACCESSO_SE_DESC @197-2253EAAF
    public TextField getACCESSO_SE_DESCField() {
        return ACCESSO_SE_DESC;
    }

    public String getACCESSO_SE_DESC() {
        return ACCESSO_SE_DESC.getValue();
    }

    public void setACCESSO_SE_DESC(String value) {
        this.ACCESSO_SE_DESC.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of ACCESSO_SE_DESC

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of ACCESSO @206-EBD40B8B
    public TextField getACCESSOField() {
        return ACCESSO;
    }

    public String getACCESSO() {
        return ACCESSO.getValue();
    }

    public void setACCESSO(String value) {
        this.ACCESSO.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of ACCESSO

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of TRACCIA @143-CC5EC3F9
    public TextField getTRACCIAField() {
        return TRACCIA;
    }

    public String getTRACCIA() {
        return TRACCIA.getValue();
    }

    public void setTRACCIA(String value) {
        this.TRACCIA.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of TRACCIA

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of GIORNI_TRACCIA @160-49E84A2B
    public LongField getGIORNI_TRACCIAField() {
        return GIORNI_TRACCIA;
    }

    public Long getGIORNI_TRACCIA() {
        return GIORNI_TRACCIA.getValue();
    }

    public void setGIORNI_TRACCIA(Long value) {
        this.GIORNI_TRACCIA.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of GIORNI_TRACCIA

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of NUM_FILE_ARCHIVIATI @215-3C02FB62
    public LongField getNUM_FILE_ARCHIVIATIField() {
        return NUM_FILE_ARCHIVIATI;
    }

    public Long getNUM_FILE_ARCHIVIATI() {
        return NUM_FILE_ARCHIVIATI.getValue();
    }

    public void setNUM_FILE_ARCHIVIATI(Long value) {
        this.NUM_FILE_ARCHIVIATI.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of NUM_FILE_ARCHIVIATI

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of DIM_FILE_ARCHIVIATI @216-57332A12
    public DoubleField getDIM_FILE_ARCHIVIATIField() {
        return DIM_FILE_ARCHIVIATI;
    }

    public Double getDIM_FILE_ARCHIVIATI() {
        return DIM_FILE_ARCHIVIATI.getValue();
    }

    public void setDIM_FILE_ARCHIVIATI(Double value) {
        this.DIM_FILE_ARCHIVIATI.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of DIM_FILE_ARCHIVIATI

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of SPOSTA_FILE_ARCH @220-B8AE87D6
    public LongField getSPOSTA_FILE_ARCHField() {
        return SPOSTA_FILE_ARCH;
    }

    public Long getSPOSTA_FILE_ARCH() {
        return SPOSTA_FILE_ARCH.getValue();
    }

    public void setSPOSTA_FILE_ARCH(Long value) {
        this.SPOSTA_FILE_ARCH.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of SPOSTA_FILE_ARCH

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of ARCHIVIAZIONE_TRACCIA @202-F115B426
    public TextField getARCHIVIAZIONE_TRACCIAField() {
        return ARCHIVIAZIONE_TRACCIA;
    }

    public String getARCHIVIAZIONE_TRACCIA() {
        return ARCHIVIAZIONE_TRACCIA.getValue();
    }

    public void setARCHIVIAZIONE_TRACCIA(String value) {
        this.ARCHIVIAZIONE_TRACCIA.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of ARCHIVIAZIONE_TRACCIA

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of LABEL_ARCH @238-125CE9FA
    public TextField getLABEL_ARCHField() {
        return LABEL_ARCH;
    }

    public String getLABEL_ARCH() {
        return LABEL_ARCH.getValue();
    }

    public void setLABEL_ARCH(String value) {
        this.LABEL_ARCH.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of LABEL_ARCH

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of DISLOCAZIONE_TRACCIA @241-2B7F3153
    public TextField getDISLOCAZIONE_TRACCIAField() {
        return DISLOCAZIONE_TRACCIA;
    }

    public String getDISLOCAZIONE_TRACCIA() {
        return DISLOCAZIONE_TRACCIA.getValue();
    }

    public void setDISLOCAZIONE_TRACCIA(String value) {
        this.DISLOCAZIONE_TRACCIA.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of DISLOCAZIONE_TRACCIA

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of INPUT_ARCH_CLASS @240-C2341A2F
    public TextField getINPUT_ARCH_CLASSField() {
        return INPUT_ARCH_CLASS;
    }

    public String getINPUT_ARCH_CLASS() {
        return INPUT_ARCH_CLASS.getValue();
    }

    public void setINPUT_ARCH_CLASS(String value) {
        this.INPUT_ARCH_CLASS.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of INPUT_ARCH_CLASS

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of CKB_ARCH_CLASS @239-F58E420A
    public TextField getCKB_ARCH_CLASSField() {
        return CKB_ARCH_CLASS;
    }

    public String getCKB_ARCH_CLASS() {
        return CKB_ARCH_CLASS.getValue();
    }

    public void setCKB_ARCH_CLASS(String value) {
        this.CKB_ARCH_CLASS.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of CKB_ARCH_CLASS

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of DISLOCAZIONE_TRACCIA_OLD @219-E1F56217
    public TextField getDISLOCAZIONE_TRACCIA_OLDField() {
        return DISLOCAZIONE_TRACCIA_OLD;
    }

    public String getDISLOCAZIONE_TRACCIA_OLD() {
        return DISLOCAZIONE_TRACCIA_OLD.getValue();
    }

    public void setDISLOCAZIONE_TRACCIA_OLD(String value) {
        this.DISLOCAZIONE_TRACCIA_OLD.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of DISLOCAZIONE_TRACCIA_OLD

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of SLEEP @204-7E64BADB
    public LongField getSLEEPField() {
        return SLEEP;
    }

    public Long getSLEEP() {
        return SLEEP.getValue();
    }

    public void setSLEEP(Long value) {
        this.SLEEP.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of SLEEP

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of VALIDITA_PASSWORD @148-953C4285
    public LongField getVALIDITA_PASSWORDField() {
        return VALIDITA_PASSWORD;
    }

    public Long getVALIDITA_PASSWORD() {
        return VALIDITA_PASSWORD.getValue();
    }

    public void setVALIDITA_PASSWORD(Long value) {
        this.VALIDITA_PASSWORD.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of VALIDITA_PASSWORD

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of TENTATIVI_PASSWORD @149-ED650603
    public LongField getTENTATIVI_PASSWORDField() {
        return TENTATIVI_PASSWORD;
    }

    public Long getTENTATIVI_PASSWORD() {
        return TENTATIVI_PASSWORD.getValue();
    }

    public void setTENTATIVI_PASSWORD(Long value) {
        this.TENTATIVI_PASSWORD.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of TENTATIVI_PASSWORD

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of MOD_PWD_PRIMO_ACCESSO @208-9EFA9CB7
    public TextField getMOD_PWD_PRIMO_ACCESSOField() {
        return MOD_PWD_PRIMO_ACCESSO;
    }

    public String getMOD_PWD_PRIMO_ACCESSO() {
        return MOD_PWD_PRIMO_ACCESSO.getValue();
    }

    public void setMOD_PWD_PRIMO_ACCESSO(String value) {
        this.MOD_PWD_PRIMO_ACCESSO.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of MOD_PWD_PRIMO_ACCESSO

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of MIN_LUNGHEZZA_PWD @210-E44CB228
    public LongField getMIN_LUNGHEZZA_PWDField() {
        return MIN_LUNGHEZZA_PWD;
    }

    public Long getMIN_LUNGHEZZA_PWD() {
        return MIN_LUNGHEZZA_PWD.getValue();
    }

    public void setMIN_LUNGHEZZA_PWD(Long value) {
        this.MIN_LUNGHEZZA_PWD.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of MIN_LUNGHEZZA_PWD

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of MIN_LUNGHEZZA_PWD_OLD @242-4E622BCB
    public LongField getMIN_LUNGHEZZA_PWD_OLDField() {
        return MIN_LUNGHEZZA_PWD_OLD;
    }

    public Long getMIN_LUNGHEZZA_PWD_OLD() {
        return MIN_LUNGHEZZA_PWD_OLD.getValue();
    }

    public void setMIN_LUNGHEZZA_PWD_OLD(Long value) {
        this.MIN_LUNGHEZZA_PWD_OLD.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of MIN_LUNGHEZZA_PWD_OLD

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of AMMESSI_CAR_SPECIALI_PWD @209-30552A7D
    public TextField getAMMESSI_CAR_SPECIALI_PWDField() {
        return AMMESSI_CAR_SPECIALI_PWD;
    }

    public String getAMMESSI_CAR_SPECIALI_PWD() {
        return AMMESSI_CAR_SPECIALI_PWD.getValue();
    }

    public void setAMMESSI_CAR_SPECIALI_PWD(String value) {
        this.AMMESSI_CAR_SPECIALI_PWD.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of AMMESSI_CAR_SPECIALI_PWD

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of AMMESSI_CAR_SPECIALI_PWD_OLD @243-31022940
    public TextField getAMMESSI_CAR_SPECIALI_PWD_OLDField() {
        return AMMESSI_CAR_SPECIALI_PWD_OLD;
    }

    public String getAMMESSI_CAR_SPECIALI_PWD_OLD() {
        return AMMESSI_CAR_SPECIALI_PWD_OLD.getValue();
    }

    public void setAMMESSI_CAR_SPECIALI_PWD_OLD(String value) {
        this.AMMESSI_CAR_SPECIALI_PWD_OLD.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of AMMESSI_CAR_SPECIALI_PWD_OLD

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of NUMERI_OBB_PWD @213-0B308A49
    public TextField getNUMERI_OBB_PWDField() {
        return NUMERI_OBB_PWD;
    }

    public String getNUMERI_OBB_PWD() {
        return NUMERI_OBB_PWD.getValue();
    }

    public void setNUMERI_OBB_PWD(String value) {
        this.NUMERI_OBB_PWD.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of NUMERI_OBB_PWD

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of NUMERI_OBB_PWD_OLD @244-FFE1B774
    public TextField getNUMERI_OBB_PWD_OLDField() {
        return NUMERI_OBB_PWD_OLD;
    }

    public String getNUMERI_OBB_PWD_OLD() {
        return NUMERI_OBB_PWD_OLD.getValue();
    }

    public void setNUMERI_OBB_PWD_OLD(String value) {
        this.NUMERI_OBB_PWD_OLD.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of NUMERI_OBB_PWD_OLD

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of LDAP @146-2D2435F0
    public TextField getLDAPField() {
        return LDAP;
    }

    public String getLDAP() {
        return LDAP.getValue();
    }

    public void setLDAP(String value) {
        this.LDAP.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of LDAP

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of SINGLE_SIGN_ON @147-4BECC93B
    public TextField getSINGLE_SIGN_ONField() {
        return SINGLE_SIGN_ON;
    }

    public String getSINGLE_SIGN_ON() {
        return SINGLE_SIGN_ON.getValue();
    }

    public void setSINGLE_SIGN_ON(String value) {
        this.SINGLE_SIGN_ON.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of SINGLE_SIGN_ON

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of DESC_DETTAGLI_LABEL @237-BB2D2A53
    public TextField getDESC_DETTAGLI_LABELField() {
        return DESC_DETTAGLI_LABEL;
    }

    public String getDESC_DETTAGLI_LABEL() {
        return DESC_DETTAGLI_LABEL.getValue();
    }

    public void setDESC_DETTAGLI_LABEL(String value) {
        this.DESC_DETTAGLI_LABEL.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of DESC_DETTAGLI_LABEL

//AD4_CARATTERISTICHE_ACCESSORow: method(s) of DESC_DETTAGLI @222-A8F99F5E
    public TextField getDESC_DETTAGLIField() {
        return DESC_DETTAGLI;
    }

    public String getDESC_DETTAGLI() {
        return DESC_DETTAGLI.getValue();
    }

    public void setDESC_DETTAGLI(String value) {
        this.DESC_DETTAGLI.setValue(value);
    }
//End AD4_CARATTERISTICHE_ACCESSORow: method(s) of DESC_DETTAGLI

//AD4_CARATTERISTICHE_ACCESSORow: class tail @38-FCB6E20C
}
//End AD4_CARATTERISTICHE_ACCESSORow: class tail

