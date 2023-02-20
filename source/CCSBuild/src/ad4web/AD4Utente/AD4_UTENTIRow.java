//AD4_UTENTIRow: import @6-54313C62
package ad4web.AD4Utente;

import java.util.*;
import com.codecharge.db.*;
//End AD4_UTENTIRow: import

//AD4_UTENTIRow: class head @6-D8604D31
public class AD4_UTENTIRow {
//End AD4_UTENTIRow: class head

//AD4_UTENTIRow: declare fiels @6-12A59436
    private TextField GENERA_PASSWORD = new TextField("GENERA_PASSWORD", "GENERA_PASSWORD");
    private TextField SVUOTA_REG_ANAGRAFICA = new TextField("SVUOTA_REG_ANAGRAFICA", "SVUOTA_REG_ANAGRAFICA");
    private TextField MOD_REGISTRAZIONE_ANAGRAFICA = new TextField("MOD_REGISTRAZIONE_ANAGRAFICA", "MOD_REGISTRAZIONE_ANAGRAFICA");
    private TextField AUTENTICAZIONE_AD4 = new TextField("AUTENTICAZIONE_AD4", "AUTENTICAZIONE_AD4");
    private TextField ELIMINA_CAAC = new TextField("ELIMINA_CAAC", "ELIMINA_CAAC");
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
    private LongField IS_SO4_USER = new LongField("IS_SO4_USER", "IS_SO4_USER");
    private LongField SOGGETTO = new LongField("SOGGETTO", "SOGGETTO");
    private TextField ID_UTENTE = new TextField("ID_UTENTE", "ID_UTENTE");
    private TextField IS_LDAPUSER = new TextField("IS_LDAPUSER", "IS_LDAPUSER");
    private TextField UTENTE_VIS = new TextField("UTENTE_VIS", "UTENTE_VIS");
    private TextField UTENTE = new TextField("UTENTE", "UTENTE");
    private TextField PASSWORD = new TextField("PASSWORD", "PASSWORD");
    private TextField MODIFICA_PASSWORD = new TextField("MODIFICA_PASSWORD", "MODIFICA_PASSWORD");
    private TextField PWD_MODIFIED = new TextField("PWD_MODIFIED", "PWD_MODIFIED");
    private DateField DATA_PASSWORD = new DateField("DATA_PASSWORD", "DATA_PASSWORD");
    private TextField RINNOVO_PASSWORD = new TextField("RINNOVO_PASSWORD", "RINNOVO_PASSWORD");
    private TextField STATO = new TextField("STATO", "STATO");
    private TextField LINGUA = new TextField("LINGUA", "LINGUA");
    private TextField GRUPPO_LAVORO = new TextField("GRUPPO_LAVORO", "GRUPPO_LAVORO");
    private LongField IMPORTANZA = new LongField("IMPORTANZA", "IMPORTANZA");
    private TextField AMMINISTRATORE = new TextField("AMMINISTRATORE", "AMMINISTRATORE");
    private TextField INFO_IDENTIFICAZIONE = new TextField("INFO_IDENTIFICAZIONE", "INFO_IDENTIFICAZIONE");
    private TextField NOTE = new TextField("NOTE", "NOTE");
    private TextField TITOLO_SOGG = new TextField("TITOLO_SOGG", "TITOLO_SOGG");
    private TextField DATI_SOGGETTO = new TextField("DATI_SOGGETTO", "DATI_SOGGETTO");
    private DateField ULTIMO_TENTATIVO = new DateField("ULTIMO_TENTATIVO", "ULTIMO_TENTATIVO");
    private LongField NUMERO_TENTATIVI = new LongField("NUMERO_TENTATIVI", "NUMERO_TENTATIVI");
    private TextField DATA_INSERIMENTO = new TextField("DATA_INSERIMENTO", "DATA_INSERIMENTO");
    private TextField UTENTE_DATA_AGGIORNAMENTO = new TextField("UTENTE_DATA_AGGIORNAMENTO", "UTENTE_DATA_AGGIORNAMENTO");
    private TextField s_UTENTE = new TextField("s_UTENTE", "UTENTE");
//End AD4_UTENTIRow: declare fiels

//AD4_UTENTIRow: constructor @6-A3432298
    public AD4_UTENTIRow() {
    }
//End AD4_UTENTIRow: constructor

//AD4_UTENTIRow: method(s) of GENERA_PASSWORD @127-0FD1322D
    public TextField getGENERA_PASSWORDField() {
        return GENERA_PASSWORD;
    }

    public String getGENERA_PASSWORD() {
        return GENERA_PASSWORD.getValue();
    }

    public void setGENERA_PASSWORD(String value) {
        this.GENERA_PASSWORD.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of GENERA_PASSWORD

//AD4_UTENTIRow: method(s) of SVUOTA_REG_ANAGRAFICA @117-07696205
    public TextField getSVUOTA_REG_ANAGRAFICAField() {
        return SVUOTA_REG_ANAGRAFICA;
    }

    public String getSVUOTA_REG_ANAGRAFICA() {
        return SVUOTA_REG_ANAGRAFICA.getValue();
    }

    public void setSVUOTA_REG_ANAGRAFICA(String value) {
        this.SVUOTA_REG_ANAGRAFICA.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of SVUOTA_REG_ANAGRAFICA

//AD4_UTENTIRow: method(s) of MOD_REGISTRAZIONE_ANAGRAFICA @119-05C82DF3
    public TextField getMOD_REGISTRAZIONE_ANAGRAFICAField() {
        return MOD_REGISTRAZIONE_ANAGRAFICA;
    }

    public String getMOD_REGISTRAZIONE_ANAGRAFICA() {
        return MOD_REGISTRAZIONE_ANAGRAFICA.getValue();
    }

    public void setMOD_REGISTRAZIONE_ANAGRAFICA(String value) {
        this.MOD_REGISTRAZIONE_ANAGRAFICA.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of MOD_REGISTRAZIONE_ANAGRAFICA

//AD4_UTENTIRow: method(s) of AUTENTICAZIONE_AD4 @141-7DB74E1F
    public TextField getAUTENTICAZIONE_AD4Field() {
        return AUTENTICAZIONE_AD4;
    }

    public String getAUTENTICAZIONE_AD4() {
        return AUTENTICAZIONE_AD4.getValue();
    }

    public void setAUTENTICAZIONE_AD4(String value) {
        this.AUTENTICAZIONE_AD4.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of AUTENTICAZIONE_AD4

//AD4_UTENTIRow: method(s) of ELIMINA_CAAC @143-35D0C579
    public TextField getELIMINA_CAACField() {
        return ELIMINA_CAAC;
    }

    public String getELIMINA_CAAC() {
        return ELIMINA_CAAC.getValue();
    }

    public void setELIMINA_CAAC(String value) {
        this.ELIMINA_CAAC.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of ELIMINA_CAAC

//AD4_UTENTIRow: method(s) of NOMINATIVO @132-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of NOMINATIVO

//AD4_UTENTIRow: method(s) of IS_SO4_USER @139-EB71448E
    public LongField getIS_SO4_USERField() {
        return IS_SO4_USER;
    }

    public Long getIS_SO4_USER() {
        return IS_SO4_USER.getValue();
    }

    public void setIS_SO4_USER(Long value) {
        this.IS_SO4_USER.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of IS_SO4_USER

//AD4_UTENTIRow: method(s) of SOGGETTO @122-3F2B29F6
    public LongField getSOGGETTOField() {
        return SOGGETTO;
    }

    public Long getSOGGETTO() {
        return SOGGETTO.getValue();
    }

    public void setSOGGETTO(Long value) {
        this.SOGGETTO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of SOGGETTO

//AD4_UTENTIRow: method(s) of ID_UTENTE @146-6F773872
    public TextField getID_UTENTEField() {
        return ID_UTENTE;
    }

    public String getID_UTENTE() {
        return ID_UTENTE.getValue();
    }

    public void setID_UTENTE(String value) {
        this.ID_UTENTE.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of ID_UTENTE

//AD4_UTENTIRow: method(s) of IS_LDAPUSER @145-DDE962E1
    public TextField getIS_LDAPUSERField() {
        return IS_LDAPUSER;
    }

    public String getIS_LDAPUSER() {
        return IS_LDAPUSER.getValue();
    }

    public void setIS_LDAPUSER(String value) {
        this.IS_LDAPUSER.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of IS_LDAPUSER

//AD4_UTENTIRow: method(s) of UTENTE_VIS @151-F106E4AE
    public TextField getUTENTE_VISField() {
        return UTENTE_VIS;
    }

    public String getUTENTE_VIS() {
        return UTENTE_VIS.getValue();
    }

    public void setUTENTE_VIS(String value) {
        this.UTENTE_VIS.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of UTENTE_VIS

//AD4_UTENTIRow: method(s) of UTENTE @156-95517C36
    public TextField getUTENTEField() {
        return UTENTE;
    }

    public String getUTENTE() {
        return UTENTE.getValue();
    }

    public void setUTENTE(String value) {
        this.UTENTE.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of UTENTE

//AD4_UTENTIRow: method(s) of PASSWORD @14-217C0394
    public TextField getPASSWORDField() {
        return PASSWORD;
    }

    public String getPASSWORD() {
        return PASSWORD.getValue();
    }

    public void setPASSWORD(String value) {
        this.PASSWORD.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of PASSWORD

//AD4_UTENTIRow: method(s) of MODIFICA_PASSWORD @164-86E742C6
    public TextField getMODIFICA_PASSWORDField() {
        return MODIFICA_PASSWORD;
    }

    public String getMODIFICA_PASSWORD() {
        return MODIFICA_PASSWORD.getValue();
    }

    public void setMODIFICA_PASSWORD(String value) {
        this.MODIFICA_PASSWORD.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of MODIFICA_PASSWORD

//AD4_UTENTIRow: method(s) of PWD_MODIFIED @88-70B162A1
    public TextField getPWD_MODIFIEDField() {
        return PWD_MODIFIED;
    }

    public String getPWD_MODIFIED() {
        return PWD_MODIFIED.getValue();
    }

    public void setPWD_MODIFIED(String value) {
        this.PWD_MODIFIED.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of PWD_MODIFIED

//AD4_UTENTIRow: method(s) of DATA_PASSWORD @39-BF7CC2DA
    public DateField getDATA_PASSWORDField() {
        return DATA_PASSWORD;
    }

    public Date getDATA_PASSWORD() {
        return DATA_PASSWORD.getValue();
    }

    public void setDATA_PASSWORD(Date value) {
        this.DATA_PASSWORD.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of DATA_PASSWORD

//AD4_UTENTIRow: method(s) of RINNOVO_PASSWORD @103-280CE65F
    public TextField getRINNOVO_PASSWORDField() {
        return RINNOVO_PASSWORD;
    }

    public String getRINNOVO_PASSWORD() {
        return RINNOVO_PASSWORD.getValue();
    }

    public void setRINNOVO_PASSWORD(String value) {
        this.RINNOVO_PASSWORD.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of RINNOVO_PASSWORD

//AD4_UTENTIRow: method(s) of STATO @24-B34568E8
    public TextField getSTATOField() {
        return STATO;
    }

    public String getSTATO() {
        return STATO.getValue();
    }

    public void setSTATO(String value) {
        this.STATO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of STATO

//AD4_UTENTIRow: method(s) of LINGUA @48-796AF860
    public TextField getLINGUAField() {
        return LINGUA;
    }

    public String getLINGUA() {
        return LINGUA.getValue();
    }

    public void setLINGUA(String value) {
        this.LINGUA.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of LINGUA

//AD4_UTENTIRow: method(s) of GRUPPO_LAVORO @31-D83D33C7
    public TextField getGRUPPO_LAVOROField() {
        return GRUPPO_LAVORO;
    }

    public String getGRUPPO_LAVORO() {
        return GRUPPO_LAVORO.getValue();
    }

    public void setGRUPPO_LAVORO(String value) {
        this.GRUPPO_LAVORO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of GRUPPO_LAVORO

//AD4_UTENTIRow: method(s) of IMPORTANZA @79-EF2E2D80
    public LongField getIMPORTANZAField() {
        return IMPORTANZA;
    }

    public Long getIMPORTANZA() {
        return IMPORTANZA.getValue();
    }

    public void setIMPORTANZA(Long value) {
        this.IMPORTANZA.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of IMPORTANZA

//AD4_UTENTIRow: method(s) of AMMINISTRATORE @162-1A240544
    public TextField getAMMINISTRATOREField() {
        return AMMINISTRATORE;
    }

    public String getAMMINISTRATORE() {
        return AMMINISTRATORE.getValue();
    }

    public void setAMMINISTRATORE(String value) {
        this.AMMINISTRATORE.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of AMMINISTRATORE

//AD4_UTENTIRow: method(s) of INFO_IDENTIFICAZIONE @163-03B25198
    public TextField getINFO_IDENTIFICAZIONEField() {
        return INFO_IDENTIFICAZIONE;
    }

    public String getINFO_IDENTIFICAZIONE() {
        return INFO_IDENTIFICAZIONE.getValue();
    }

    public void setINFO_IDENTIFICAZIONE(String value) {
        this.INFO_IDENTIFICAZIONE.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of INFO_IDENTIFICAZIONE

//AD4_UTENTIRow: method(s) of NOTE @22-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of NOTE

//AD4_UTENTIRow: method(s) of TITOLO_SOGG @116-6E1A4BC8
    public TextField getTITOLO_SOGGField() {
        return TITOLO_SOGG;
    }

    public String getTITOLO_SOGG() {
        return TITOLO_SOGG.getValue();
    }

    public void setTITOLO_SOGG(String value) {
        this.TITOLO_SOGG.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of TITOLO_SOGG

//AD4_UTENTIRow: method(s) of DATI_SOGGETTO @42-869C3147
    public TextField getDATI_SOGGETTOField() {
        return DATI_SOGGETTO;
    }

    public String getDATI_SOGGETTO() {
        return DATI_SOGGETTO.getValue();
    }

    public void setDATI_SOGGETTO(String value) {
        this.DATI_SOGGETTO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of DATI_SOGGETTO

//AD4_UTENTIRow: method(s) of ULTIMO_TENTATIVO @18-1A87726F
    public DateField getULTIMO_TENTATIVOField() {
        return ULTIMO_TENTATIVO;
    }

    public Date getULTIMO_TENTATIVO() {
        return ULTIMO_TENTATIVO.getValue();
    }

    public void setULTIMO_TENTATIVO(Date value) {
        this.ULTIMO_TENTATIVO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of ULTIMO_TENTATIVO

//AD4_UTENTIRow: method(s) of NUMERO_TENTATIVI @50-9317BA76
    public LongField getNUMERO_TENTATIVIField() {
        return NUMERO_TENTATIVI;
    }

    public Long getNUMERO_TENTATIVI() {
        return NUMERO_TENTATIVI.getValue();
    }

    public void setNUMERO_TENTATIVI(Long value) {
        this.NUMERO_TENTATIVI.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of NUMERO_TENTATIVI

//AD4_UTENTIRow: method(s) of DATA_INSERIMENTO @80-ECF5354A
    public TextField getDATA_INSERIMENTOField() {
        return DATA_INSERIMENTO;
    }

    public String getDATA_INSERIMENTO() {
        return DATA_INSERIMENTO.getValue();
    }

    public void setDATA_INSERIMENTO(String value) {
        this.DATA_INSERIMENTO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of DATA_INSERIMENTO

//AD4_UTENTIRow: method(s) of UTENTE_DATA_AGGIORNAMENTO @54-342663EF
    public TextField getUTENTE_DATA_AGGIORNAMENTOField() {
        return UTENTE_DATA_AGGIORNAMENTO;
    }

    public String getUTENTE_DATA_AGGIORNAMENTO() {
        return UTENTE_DATA_AGGIORNAMENTO.getValue();
    }

    public void setUTENTE_DATA_AGGIORNAMENTO(String value) {
        this.UTENTE_DATA_AGGIORNAMENTO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of UTENTE_DATA_AGGIORNAMENTO

//AD4_UTENTIRow: method(s) of s_UTENTE @120-A2DA8BBA
    public TextField getS_UTENTEField() {
        return s_UTENTE;
    }

    public String getS_UTENTE() {
        return s_UTENTE.getValue();
    }

    public void setS_UTENTE(String value) {
        this.s_UTENTE.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of s_UTENTE

//AD4_UTENTIRow: class tail @6-FCB6E20C
}
//End AD4_UTENTIRow: class tail

