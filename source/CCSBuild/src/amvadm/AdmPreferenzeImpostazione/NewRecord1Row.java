//NewRecord1Row: import @45-7693BFCE
package amvadm.AdmPreferenzeImpostazione;

import java.util.*;
import com.codecharge.db.*;
//End NewRecord1Row: import

//NewRecord1Row: class head @45-847B7188
public class NewRecord1Row {
//End NewRecord1Row: class head

//NewRecord1Row: declare fiels @45-096976CD
    private TextField COMMENTO = new TextField("COMMENTO", "COMMENTO");
    private TextField STRINGA_ALIAS = new TextField("STRINGA_ALIAS", "STRINGA_ALIAS");
    private TextField MODULO = new TextField("MODULO", "MODULO");
    private TextField VALORE = new TextField("VALORE", "VALORE");
//End NewRecord1Row: declare fiels

//NewRecord1Row: constructor @45-905A5748
    public NewRecord1Row() {
    }
//End NewRecord1Row: constructor

//NewRecord1Row: method(s) of COMMENTO @55-0F4C155C
    public TextField getCOMMENTOField() {
        return COMMENTO;
    }

    public String getCOMMENTO() {
        return COMMENTO.getValue();
    }

    public void setCOMMENTO(String value) {
        this.COMMENTO.setValue(value);
    }
//End NewRecord1Row: method(s) of COMMENTO

//NewRecord1Row: method(s) of STRINGA_ALIAS @46-F532F3B2
    public TextField getSTRINGA_ALIASField() {
        return STRINGA_ALIAS;
    }

    public String getSTRINGA_ALIAS() {
        return STRINGA_ALIAS.getValue();
    }

    public void setSTRINGA_ALIAS(String value) {
        this.STRINGA_ALIAS.setValue(value);
    }
//End NewRecord1Row: method(s) of STRINGA_ALIAS

//NewRecord1Row: method(s) of MODULO @56-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End NewRecord1Row: method(s) of MODULO

//NewRecord1Row: method(s) of VALORE @47-BC514AA0
    public TextField getVALOREField() {
        return VALORE;
    }

    public String getVALORE() {
        return VALORE.getValue();
    }

    public void setVALORE(String value) {
        this.VALORE.setValue(value);
    }
//End NewRecord1Row: method(s) of VALORE

//NewRecord1Row: class tail @45-FCB6E20C
}
//End NewRecord1Row: class tail

