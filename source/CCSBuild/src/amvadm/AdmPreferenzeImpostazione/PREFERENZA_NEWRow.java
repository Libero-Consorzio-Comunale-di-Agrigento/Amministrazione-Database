//PREFERENZA_NEWRow: import @45-7693BFCE
package amvadm.AdmPreferenzeImpostazione;

import java.util.*;
import com.codecharge.db.*;
//End PREFERENZA_NEWRow: import

//PREFERENZA_NEWRow: class head @45-97C23FDF
public class PREFERENZA_NEWRow {
//End PREFERENZA_NEWRow: class head

//PREFERENZA_NEWRow: declare fiels @45-D4EE4C02
    private TextField STRINGA_LABEL = new TextField("STRINGA_LABEL", "STRINGA_ALIAS");
    private TextField COMMENTO = new TextField("COMMENTO", "COMMENTO");
    private TextField STRINGA_ALIAS = new TextField("STRINGA_ALIAS", "STRINGA_ALIAS");
    private TextField MODULO = new TextField("MODULO", "MODULO");
    private TextField VALORE = new TextField("VALORE", "VALORE");
//End PREFERENZA_NEWRow: declare fiels

//PREFERENZA_NEWRow: constructor @45-15CEE7FD
    public PREFERENZA_NEWRow() {
    }
//End PREFERENZA_NEWRow: constructor

//PREFERENZA_NEWRow: method(s) of STRINGA_LABEL @60-9A500603
    public TextField getSTRINGA_LABELField() {
        return STRINGA_LABEL;
    }

    public String getSTRINGA_LABEL() {
        return STRINGA_LABEL.getValue();
    }

    public void setSTRINGA_LABEL(String value) {
        this.STRINGA_LABEL.setValue(value);
    }
//End PREFERENZA_NEWRow: method(s) of STRINGA_LABEL

//PREFERENZA_NEWRow: method(s) of COMMENTO @55-0F4C155C
    public TextField getCOMMENTOField() {
        return COMMENTO;
    }

    public String getCOMMENTO() {
        return COMMENTO.getValue();
    }

    public void setCOMMENTO(String value) {
        this.COMMENTO.setValue(value);
    }
//End PREFERENZA_NEWRow: method(s) of COMMENTO

//PREFERENZA_NEWRow: method(s) of STRINGA_ALIAS @46-F532F3B2
    public TextField getSTRINGA_ALIASField() {
        return STRINGA_ALIAS;
    }

    public String getSTRINGA_ALIAS() {
        return STRINGA_ALIAS.getValue();
    }

    public void setSTRINGA_ALIAS(String value) {
        this.STRINGA_ALIAS.setValue(value);
    }
//End PREFERENZA_NEWRow: method(s) of STRINGA_ALIAS

//PREFERENZA_NEWRow: method(s) of MODULO @56-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End PREFERENZA_NEWRow: method(s) of MODULO

//PREFERENZA_NEWRow: method(s) of VALORE @47-BC514AA0
    public TextField getVALOREField() {
        return VALORE;
    }

    public String getVALORE() {
        return VALORE.getValue();
    }

    public void setVALORE(String value) {
        this.VALORE.setValue(value);
    }
//End PREFERENZA_NEWRow: method(s) of VALORE

//PREFERENZA_NEWRow: class tail @45-FCB6E20C
}
//End PREFERENZA_NEWRow: class tail

