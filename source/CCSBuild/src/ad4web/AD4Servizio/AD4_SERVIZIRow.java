//AD4_SERVIZIRow: import @38-71AFC553
package ad4web.AD4Servizio;

import java.util.*;
import com.codecharge.db.*;
//End AD4_SERVIZIRow: import

//AD4_SERVIZIRow: class head @38-2B4FB4ED
public class AD4_SERVIZIRow {
//End AD4_SERVIZIRow: class head

//AD4_SERVIZIRow: declare fiels @38-CB02CEBE
    private TextField DESC_PROGETTO = new TextField("DESC_PROGETTO", "DESC_PROGETTO");
    private TextField ABILITAZIONI = new TextField("ABILITAZIONI", "ABILITAZIONI");
    private LongField ID_SERVIZIO = new LongField("ID_SERVIZIO", "ID_SERVIZIO");
    private TextField MODULO = new TextField("MODULO", "MODULO");
    private TextField ISTANZA = new TextField("ISTANZA", "ISTANZA");
    private TextField LIVELLO = new TextField("LIVELLO", "LIVELLO");
    private TextField ABILITAZIONE = new TextField("ABILITAZIONE", "ABILITAZIONE");
    private TextField MULTIPLO = new TextField("MULTIPLO", "MULTIPLO");
    private TextField GRUPPO_ABILITAZIONE = new TextField("GRUPPO_ABILITAZIONE", "GRUPPO_ABILITAZIONE");
    private TextField TIPO_NOTIFICA = new TextField("TIPO_NOTIFICA", "TIPO_NOTIFICA");
    private TextField TAG_CIM = new TextField("TAG_CIM", "TAG_CIM");
    private TextField MAIL_NOTIFICHE = new TextField("MAIL_NOTIFICHE", "MAIL_NOTIFICHE");
    private TextField CCR_NOTIFICHE = new TextField("CCR_NOTIFICHE", "CCR_NOTIFICHE");
    private TextField RECUPERO_PASSWORD = new TextField("RECUPERO_PASSWORD", "RECUPERO_PASSWORD");
//End AD4_SERVIZIRow: declare fiels

//AD4_SERVIZIRow: constructor @38-8447D09E
    public AD4_SERVIZIRow() {
    }
//End AD4_SERVIZIRow: constructor

//AD4_SERVIZIRow: method(s) of DESC_PROGETTO @175-BEFF43EA
    public TextField getDESC_PROGETTOField() {
        return DESC_PROGETTO;
    }

    public String getDESC_PROGETTO() {
        return DESC_PROGETTO.getValue();
    }

    public void setDESC_PROGETTO(String value) {
        this.DESC_PROGETTO.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of DESC_PROGETTO

//AD4_SERVIZIRow: method(s) of ABILITAZIONI @176-92FFC1C0
    public TextField getABILITAZIONIField() {
        return ABILITAZIONI;
    }

    public String getABILITAZIONI() {
        return ABILITAZIONI.getValue();
    }

    public void setABILITAZIONI(String value) {
        this.ABILITAZIONI.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of ABILITAZIONI

//AD4_SERVIZIRow: method(s) of ID_SERVIZIO @49-6ECD06A2
    public LongField getID_SERVIZIOField() {
        return ID_SERVIZIO;
    }

    public Long getID_SERVIZIO() {
        return ID_SERVIZIO.getValue();
    }

    public void setID_SERVIZIO(Long value) {
        this.ID_SERVIZIO.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of ID_SERVIZIO

//AD4_SERVIZIRow: method(s) of MODULO @46-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of MODULO

//AD4_SERVIZIRow: method(s) of ISTANZA @47-CC23EEBF
    public TextField getISTANZAField() {
        return ISTANZA;
    }

    public String getISTANZA() {
        return ISTANZA.getValue();
    }

    public void setISTANZA(String value) {
        this.ISTANZA.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of ISTANZA

//AD4_SERVIZIRow: method(s) of LIVELLO @48-E164E905
    public TextField getLIVELLOField() {
        return LIVELLO;
    }

    public String getLIVELLO() {
        return LIVELLO.getValue();
    }

    public void setLIVELLO(String value) {
        this.LIVELLO.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of LIVELLO

//AD4_SERVIZIRow: method(s) of ABILITAZIONE @53-6F33EA59
    public TextField getABILITAZIONEField() {
        return ABILITAZIONE;
    }

    public String getABILITAZIONE() {
        return ABILITAZIONE.getValue();
    }

    public void setABILITAZIONE(String value) {
        this.ABILITAZIONE.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of ABILITAZIONE

//AD4_SERVIZIRow: method(s) of MULTIPLO @142-49405660
    public TextField getMULTIPLOField() {
        return MULTIPLO;
    }

    public String getMULTIPLO() {
        return MULTIPLO.getValue();
    }

    public void setMULTIPLO(String value) {
        this.MULTIPLO.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of MULTIPLO

//AD4_SERVIZIRow: method(s) of GRUPPO_ABILITAZIONE @50-1E06FBF5
    public TextField getGRUPPO_ABILITAZIONEField() {
        return GRUPPO_ABILITAZIONE;
    }

    public String getGRUPPO_ABILITAZIONE() {
        return GRUPPO_ABILITAZIONE.getValue();
    }

    public void setGRUPPO_ABILITAZIONE(String value) {
        this.GRUPPO_ABILITAZIONE.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of GRUPPO_ABILITAZIONE

//AD4_SERVIZIRow: method(s) of TIPO_NOTIFICA @51-C59F1488
    public TextField getTIPO_NOTIFICAField() {
        return TIPO_NOTIFICA;
    }

    public String getTIPO_NOTIFICA() {
        return TIPO_NOTIFICA.getValue();
    }

    public void setTIPO_NOTIFICA(String value) {
        this.TIPO_NOTIFICA.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of TIPO_NOTIFICA

//AD4_SERVIZIRow: method(s) of TAG_CIM @188-4970708C
    public TextField getTAG_CIMField() {
        return TAG_CIM;
    }

    public String getTAG_CIM() {
        return TAG_CIM.getValue();
    }

    public void setTAG_CIM(String value) {
        this.TAG_CIM.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of TAG_CIM

//AD4_SERVIZIRow: method(s) of MAIL_NOTIFICHE @52-345AA33C
    public TextField getMAIL_NOTIFICHEField() {
        return MAIL_NOTIFICHE;
    }

    public String getMAIL_NOTIFICHE() {
        return MAIL_NOTIFICHE.getValue();
    }

    public void setMAIL_NOTIFICHE(String value) {
        this.MAIL_NOTIFICHE.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of MAIL_NOTIFICHE

//AD4_SERVIZIRow: method(s) of CCR_NOTIFICHE @141-026F8048
    public TextField getCCR_NOTIFICHEField() {
        return CCR_NOTIFICHE;
    }

    public String getCCR_NOTIFICHE() {
        return CCR_NOTIFICHE.getValue();
    }

    public void setCCR_NOTIFICHE(String value) {
        this.CCR_NOTIFICHE.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of CCR_NOTIFICHE

//AD4_SERVIZIRow: method(s) of RECUPERO_PASSWORD @187-DA89095B
    public TextField getRECUPERO_PASSWORDField() {
        return RECUPERO_PASSWORD;
    }

    public String getRECUPERO_PASSWORD() {
        return RECUPERO_PASSWORD.getValue();
    }

    public void setRECUPERO_PASSWORD(String value) {
        this.RECUPERO_PASSWORD.setValue(value);
    }
//End AD4_SERVIZIRow: method(s) of RECUPERO_PASSWORD

//AD4_SERVIZIRow: class tail @38-FCB6E20C
}
//End AD4_SERVIZIRow: class tail

