//AD4_GRUPPIRow: import @6-DCBD683B
package ad4web.AD4GrupElenco;

import java.util.*;
import com.codecharge.db.*;
//End AD4_GRUPPIRow: import

//AD4_GRUPPIRow: class head @6-58EAF1C0
public class AD4_GRUPPIRow {
//End AD4_GRUPPIRow: class head

//AD4_GRUPPIRow: declare fiels @6-28E18554
    private TextField GRUPPO = new TextField("GRUPPO", "GRUPPO");
    private TextField UTENTE = new TextField("UTENTE", "UTENTE");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private TextField IMPORTANZA = new TextField("IMPORTANZA", "IMPORTANZA");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField MODULO = new TextField("MODULO", "MODULO");
    private TextField ISTANZA = new TextField("ISTANZA", "ISTANZA");
//End AD4_GRUPPIRow: declare fiels

//AD4_GRUPPIRow: constructor @6-5258B075
    public AD4_GRUPPIRow() {
    }
//End AD4_GRUPPIRow: constructor

//AD4_GRUPPIRow: method(s) of GRUPPO @22-C886BC8A
    public TextField getGRUPPOField() {
        return GRUPPO;
    }

    public String getGRUPPO() {
        return GRUPPO.getValue();
    }

    public void setGRUPPO(String value) {
        this.GRUPPO.setValue(value);
    }
//End AD4_GRUPPIRow: method(s) of GRUPPO

//AD4_GRUPPIRow: method(s) of UTENTE @70-95517C36
    public TextField getUTENTEField() {
        return UTENTE;
    }

    public String getUTENTE() {
        return UTENTE.getValue();
    }

    public void setUTENTE(String value) {
        this.UTENTE.setValue(value);
    }
//End AD4_GRUPPIRow: method(s) of UTENTE

//AD4_GRUPPIRow: method(s) of DESCRIZIONE @17-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End AD4_GRUPPIRow: method(s) of DESCRIZIONE

//AD4_GRUPPIRow: method(s) of IMPORTANZA @20-B2F8E335
    public TextField getIMPORTANZAField() {
        return IMPORTANZA;
    }

    public String getIMPORTANZA() {
        return IMPORTANZA.getValue();
    }

    public void setIMPORTANZA(String value) {
        this.IMPORTANZA.setValue(value);
    }
//End AD4_GRUPPIRow: method(s) of IMPORTANZA

//AD4_GRUPPIRow: method(s) of AFCNavigator @135-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AD4_GRUPPIRow: method(s) of AFCNavigator

//AD4_GRUPPIRow: method(s) of MODULO @60-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End AD4_GRUPPIRow: method(s) of MODULO

//AD4_GRUPPIRow: method(s) of ISTANZA @61-CC23EEBF
    public TextField getISTANZAField() {
        return ISTANZA;
    }

    public String getISTANZA() {
        return ISTANZA.getValue();
    }

    public void setISTANZA(String value) {
        this.ISTANZA.setValue(value);
    }
//End AD4_GRUPPIRow: method(s) of ISTANZA

//AD4_GRUPPIRow: class tail @6-FCB6E20C
}
//End AD4_GRUPPIRow: class tail

