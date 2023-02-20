//AD4_ISTANZERow: import @56-C13C580B
package ad4web.AD4Istanza;

import java.util.*;
import com.codecharge.db.*;
//End AD4_ISTANZERow: import

//AD4_ISTANZERow: class head @56-48954B3A
public class AD4_ISTANZERow {
//End AD4_ISTANZERow: class head

//AD4_ISTANZERow: declare fiels @56-5CFE5D4B
    private TextField DESC_PROGETTO = new TextField("DESC_PROGETTO", "DESC_PROGETTO");
    private TextField CARATTERISTICHE = new TextField("CARATTERISTICHE", "CARATTERISTICHE");
    private TextField ABILITAZIONI = new TextField("ABILITAZIONI", "ABILITAZIONI");
    private TextField REGISTRO = new TextField("REGISTRO", "REGISTRO");
    private TextField ISTANZA = new TextField("ISTANZA", "ISTANZA");
    private TextField ISTANZA_ORIG = new TextField("ISTANZA_ORIG", "ISTANZA_ORIG");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private TextField ENTE = new TextField("ENTE", "ENTE");
    private TextField PROGETTO = new TextField("PROGETTO", "PROGETTO");
    private TextField LINGUA = new TextField("LINGUA", "LINGUA");
    private TextField DISLOCAZIONE = new TextField("DISLOCAZIONE", "DISLOCAZIONE");
    private TextField DISLOCAZIONE_TEMPORANEA = new TextField("DISLOCAZIONE_TEMPORANEA", "DISLOCAZIONE_TEMPORANEA");
    private TextField USER_ORACLE = new TextField("USER_ORACLE", "USER_ORACLE");
    private TextField PASSWORD_ORACLE = new TextField("PASSWORD_ORACLE", "PASSWORD_ORACLE");
    private TextField PWD_MODIFIED = new TextField("PWD_MODIFIED", "PWD_MODIFIED");
    private TextField ISTANZA_AMMINISTRATORE = new TextField("ISTANZA_AMMINISTRATORE", "ISTANZA_AMMINISTRATORE");
    private TextField ISTANZA_AMMINISTRATORE_ORIG = new TextField("ISTANZA_AMMINISTRATORE_ORIG", "ISTANZA_AMMINISTRATORE_ORIG");
    private TextField LINK_ORACLE = new TextField("LINK_ORACLE", "LINK_ORACLE");
    private TextField DATABASE_LINK = new TextField("DATABASE_LINK", "DATABASE_LINK");
    private TextField DATABASE_DRIVER = new TextField("DATABASE_DRIVER", "DATABASE_DRIVER");
    private TextField SERVIZIO = new TextField("SERVIZIO", "SERVIZIO");
    private TextField NOTE = new TextField("NOTE", "NOTE");
    private TextField INSTALLAZIONE = new TextField("INSTALLAZIONE", "INSTALLAZIONE");
    private TextField VERSIONE = new TextField("VERSIONE", "VERSIONE");
    private TextField USRORCL = new TextField("USRORCL", "USER_ORACLE");
//End AD4_ISTANZERow: declare fiels

//AD4_ISTANZERow: constructor @56-F9A335F0
    public AD4_ISTANZERow() {
    }
//End AD4_ISTANZERow: constructor

//AD4_ISTANZERow: method(s) of DESC_PROGETTO @135-BEFF43EA
    public TextField getDESC_PROGETTOField() {
        return DESC_PROGETTO;
    }

    public String getDESC_PROGETTO() {
        return DESC_PROGETTO.getValue();
    }

    public void setDESC_PROGETTO(String value) {
        this.DESC_PROGETTO.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of DESC_PROGETTO

//AD4_ISTANZERow: method(s) of CARATTERISTICHE @138-F02E3827
    public TextField getCARATTERISTICHEField() {
        return CARATTERISTICHE;
    }

    public String getCARATTERISTICHE() {
        return CARATTERISTICHE.getValue();
    }

    public void setCARATTERISTICHE(String value) {
        this.CARATTERISTICHE.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of CARATTERISTICHE

//AD4_ISTANZERow: method(s) of ABILITAZIONI @136-92FFC1C0
    public TextField getABILITAZIONIField() {
        return ABILITAZIONI;
    }

    public String getABILITAZIONI() {
        return ABILITAZIONI.getValue();
    }

    public void setABILITAZIONI(String value) {
        this.ABILITAZIONI.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of ABILITAZIONI

//AD4_ISTANZERow: method(s) of REGISTRO @169-A7500184
    public TextField getREGISTROField() {
        return REGISTRO;
    }

    public String getREGISTRO() {
        return REGISTRO.getValue();
    }

    public void setREGISTRO(String value) {
        this.REGISTRO.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of REGISTRO

//AD4_ISTANZERow: method(s) of ISTANZA @102-CC23EEBF
    public TextField getISTANZAField() {
        return ISTANZA;
    }

    public String getISTANZA() {
        return ISTANZA.getValue();
    }

    public void setISTANZA(String value) {
        this.ISTANZA.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of ISTANZA

//AD4_ISTANZERow: method(s) of ISTANZA_ORIG @103-8837BB9C
    public TextField getISTANZA_ORIGField() {
        return ISTANZA_ORIG;
    }

    public String getISTANZA_ORIG() {
        return ISTANZA_ORIG.getValue();
    }

    public void setISTANZA_ORIG(String value) {
        this.ISTANZA_ORIG.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of ISTANZA_ORIG

//AD4_ISTANZERow: method(s) of DESCRIZIONE @101-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of DESCRIZIONE

//AD4_ISTANZERow: method(s) of ENTE @104-D1A00E41
    public TextField getENTEField() {
        return ENTE;
    }

    public String getENTE() {
        return ENTE.getValue();
    }

    public void setENTE(String value) {
        this.ENTE.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of ENTE

//AD4_ISTANZERow: method(s) of PROGETTO @142-7637616D
    public TextField getPROGETTOField() {
        return PROGETTO;
    }

    public String getPROGETTO() {
        return PROGETTO.getValue();
    }

    public void setPROGETTO(String value) {
        this.PROGETTO.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of PROGETTO

//AD4_ISTANZERow: method(s) of LINGUA @107-796AF860
    public TextField getLINGUAField() {
        return LINGUA;
    }

    public String getLINGUA() {
        return LINGUA.getValue();
    }

    public void setLINGUA(String value) {
        this.LINGUA.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of LINGUA

//AD4_ISTANZERow: method(s) of DISLOCAZIONE @105-61318B85
    public TextField getDISLOCAZIONEField() {
        return DISLOCAZIONE;
    }

    public String getDISLOCAZIONE() {
        return DISLOCAZIONE.getValue();
    }

    public void setDISLOCAZIONE(String value) {
        this.DISLOCAZIONE.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of DISLOCAZIONE

//AD4_ISTANZERow: method(s) of DISLOCAZIONE_TEMPORANEA @106-AF93A79C
    public TextField getDISLOCAZIONE_TEMPORANEAField() {
        return DISLOCAZIONE_TEMPORANEA;
    }

    public String getDISLOCAZIONE_TEMPORANEA() {
        return DISLOCAZIONE_TEMPORANEA.getValue();
    }

    public void setDISLOCAZIONE_TEMPORANEA(String value) {
        this.DISLOCAZIONE_TEMPORANEA.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of DISLOCAZIONE_TEMPORANEA

//AD4_ISTANZERow: method(s) of USER_ORACLE @108-88B24EB2
    public TextField getUSER_ORACLEField() {
        return USER_ORACLE;
    }

    public String getUSER_ORACLE() {
        return USER_ORACLE.getValue();
    }

    public void setUSER_ORACLE(String value) {
        this.USER_ORACLE.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of USER_ORACLE

//AD4_ISTANZERow: method(s) of PASSWORD_ORACLE @70-2502FC9E
    public TextField getPASSWORD_ORACLEField() {
        return PASSWORD_ORACLE;
    }

    public String getPASSWORD_ORACLE() {
        return PASSWORD_ORACLE.getValue();
    }

    public void setPASSWORD_ORACLE(String value) {
        this.PASSWORD_ORACLE.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of PASSWORD_ORACLE

//AD4_ISTANZERow: method(s) of PWD_MODIFIED @99-70B162A1
    public TextField getPWD_MODIFIEDField() {
        return PWD_MODIFIED;
    }

    public String getPWD_MODIFIED() {
        return PWD_MODIFIED.getValue();
    }

    public void setPWD_MODIFIED(String value) {
        this.PWD_MODIFIED.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of PWD_MODIFIED

//AD4_ISTANZERow: method(s) of ISTANZA_AMMINISTRATORE @176-32190CC0
    public TextField getISTANZA_AMMINISTRATOREField() {
        return ISTANZA_AMMINISTRATORE;
    }

    public String getISTANZA_AMMINISTRATORE() {
        return ISTANZA_AMMINISTRATORE.getValue();
    }

    public void setISTANZA_AMMINISTRATORE(String value) {
        this.ISTANZA_AMMINISTRATORE.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of ISTANZA_AMMINISTRATORE

//AD4_ISTANZERow: method(s) of ISTANZA_AMMINISTRATORE_ORIG @179-6FCD30D8
    public TextField getISTANZA_AMMINISTRATORE_ORIGField() {
        return ISTANZA_AMMINISTRATORE_ORIG;
    }

    public String getISTANZA_AMMINISTRATORE_ORIG() {
        return ISTANZA_AMMINISTRATORE_ORIG.getValue();
    }

    public void setISTANZA_AMMINISTRATORE_ORIG(String value) {
        this.ISTANZA_AMMINISTRATORE_ORIG.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of ISTANZA_AMMINISTRATORE_ORIG

//AD4_ISTANZERow: method(s) of LINK_ORACLE @109-4002E7AA
    public TextField getLINK_ORACLEField() {
        return LINK_ORACLE;
    }

    public String getLINK_ORACLE() {
        return LINK_ORACLE.getValue();
    }

    public void setLINK_ORACLE(String value) {
        this.LINK_ORACLE.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of LINK_ORACLE

//AD4_ISTANZERow: method(s) of DATABASE_LINK @173-0C8EDC25
    public TextField getDATABASE_LINKField() {
        return DATABASE_LINK;
    }

    public String getDATABASE_LINK() {
        return DATABASE_LINK.getValue();
    }

    public void setDATABASE_LINK(String value) {
        this.DATABASE_LINK.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of DATABASE_LINK

//AD4_ISTANZERow: method(s) of DATABASE_DRIVER @174-83DE60FD
    public TextField getDATABASE_DRIVERField() {
        return DATABASE_DRIVER;
    }

    public String getDATABASE_DRIVER() {
        return DATABASE_DRIVER.getValue();
    }

    public void setDATABASE_DRIVER(String value) {
        this.DATABASE_DRIVER.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of DATABASE_DRIVER

//AD4_ISTANZERow: method(s) of SERVIZIO @113-6655FB9B
    public TextField getSERVIZIOField() {
        return SERVIZIO;
    }

    public String getSERVIZIO() {
        return SERVIZIO.getValue();
    }

    public void setSERVIZIO(String value) {
        this.SERVIZIO.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of SERVIZIO

//AD4_ISTANZERow: method(s) of NOTE @114-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of NOTE

//AD4_ISTANZERow: method(s) of INSTALLAZIONE @116-9082B40A
    public TextField getINSTALLAZIONEField() {
        return INSTALLAZIONE;
    }

    public String getINSTALLAZIONE() {
        return INSTALLAZIONE.getValue();
    }

    public void setINSTALLAZIONE(String value) {
        this.INSTALLAZIONE.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of INSTALLAZIONE

//AD4_ISTANZERow: method(s) of VERSIONE @117-D2D0EF90
    public TextField getVERSIONEField() {
        return VERSIONE;
    }

    public String getVERSIONE() {
        return VERSIONE.getValue();
    }

    public void setVERSIONE(String value) {
        this.VERSIONE.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of VERSIONE

//AD4_ISTANZERow: method(s) of USRORCL @170-66EC325D
    public TextField getUSRORCLField() {
        return USRORCL;
    }

    public String getUSRORCL() {
        return USRORCL.getValue();
    }

    public void setUSRORCL(String value) {
        this.USRORCL.setValue(value);
    }
//End AD4_ISTANZERow: method(s) of USRORCL

//AD4_ISTANZERow: class tail @56-FCB6E20C
}
//End AD4_ISTANZERow: class tail

