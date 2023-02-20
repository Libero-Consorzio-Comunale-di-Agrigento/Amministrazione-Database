//ELENCO_CHIAVIRow: import @2-1AC2A0C7
package ad4web.AD4RegistroElenco;

import java.util.*;
import com.codecharge.db.*;
//End ELENCO_CHIAVIRow: import

//ELENCO_CHIAVIRow: class head @2-5EFD0931
public class ELENCO_CHIAVIRow {
//End ELENCO_CHIAVIRow: class head

//ELENCO_CHIAVIRow: declare fiels @2-A79BB7B3
    private TextField STRINGA = new TextField("STRINGA", "STRINGA");
    private TextField VALORE = new TextField("VALORE", "VALORE");
    private TextField COMMENTO = new TextField("COMMENTO", "COMMENTO");
    private TextField CHIAVE = new TextField("CHIAVE", "CHIAVE");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
//End ELENCO_CHIAVIRow: declare fiels

//ELENCO_CHIAVIRow: constructor @2-C3A901FC
    public ELENCO_CHIAVIRow() {
    }
//End ELENCO_CHIAVIRow: constructor

//ELENCO_CHIAVIRow: method(s) of STRINGA @3-A3BF594E
    public TextField getSTRINGAField() {
        return STRINGA;
    }

    public String getSTRINGA() {
        return STRINGA.getValue();
    }

    public void setSTRINGA(String value) {
        this.STRINGA.setValue(value);
    }
//End ELENCO_CHIAVIRow: method(s) of STRINGA

//ELENCO_CHIAVIRow: method(s) of VALORE @21-BC514AA0
    public TextField getVALOREField() {
        return VALORE;
    }

    public String getVALORE() {
        return VALORE.getValue();
    }

    public void setVALORE(String value) {
        this.VALORE.setValue(value);
    }
//End ELENCO_CHIAVIRow: method(s) of VALORE

//ELENCO_CHIAVIRow: method(s) of COMMENTO @30-0F4C155C
    public TextField getCOMMENTOField() {
        return COMMENTO;
    }

    public String getCOMMENTO() {
        return COMMENTO.getValue();
    }

    public void setCOMMENTO(String value) {
        this.COMMENTO.setValue(value);
    }
//End ELENCO_CHIAVIRow: method(s) of COMMENTO

//ELENCO_CHIAVIRow: method(s) of CHIAVE @40-2CA70E12
    public TextField getCHIAVEField() {
        return CHIAVE;
    }

    public String getCHIAVE() {
        return CHIAVE.getValue();
    }

    public void setCHIAVE(String value) {
        this.CHIAVE.setValue(value);
    }
//End ELENCO_CHIAVIRow: method(s) of CHIAVE

//ELENCO_CHIAVIRow: method(s) of AFCNavigator @31-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End ELENCO_CHIAVIRow: method(s) of AFCNavigator

//ELENCO_CHIAVIRow: class tail @2-FCB6E20C
}
//End ELENCO_CHIAVIRow: class tail

