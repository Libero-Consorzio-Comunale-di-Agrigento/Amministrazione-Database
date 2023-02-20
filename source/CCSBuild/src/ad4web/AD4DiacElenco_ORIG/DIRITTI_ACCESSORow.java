//DIRITTI_ACCESSORow: import @146-8BF6A47F
package ad4web.AD4DiacElenco_ORIG;

import java.util.*;
import com.codecharge.db.*;
//End DIRITTI_ACCESSORow: import

//DIRITTI_ACCESSORow: class head @146-BE97DD40
public class DIRITTI_ACCESSORow {
//End DIRITTI_ACCESSORow: class head

//DIRITTI_ACCESSORow: declare fiels @146-81758877
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
    private TextField Nuovo = new TextField("Nuovo", "");
    private TextField Copia = new TextField("Copia", "");
    private TextField Unifica = new TextField("Unifica", "");
//End DIRITTI_ACCESSORow: declare fiels

//DIRITTI_ACCESSORow: constructor @146-4F39847D
    public DIRITTI_ACCESSORow() {
    }
//End DIRITTI_ACCESSORow: constructor

//DIRITTI_ACCESSORow: method(s) of NOMINATIVO @147-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End DIRITTI_ACCESSORow: method(s) of NOMINATIVO

//DIRITTI_ACCESSORow: method(s) of Nuovo @151-42611BD0
    public TextField getNuovoField() {
        return Nuovo;
    }

    public String getNuovo() {
        return Nuovo.getValue();
    }

    public void setNuovo(String value) {
        this.Nuovo.setValue(value);
    }
//End DIRITTI_ACCESSORow: method(s) of Nuovo

//DIRITTI_ACCESSORow: method(s) of Copia @162-910137C6
    public TextField getCopiaField() {
        return Copia;
    }

    public String getCopia() {
        return Copia.getValue();
    }

    public void setCopia(String value) {
        this.Copia.setValue(value);
    }
//End DIRITTI_ACCESSORow: method(s) of Copia

//DIRITTI_ACCESSORow: method(s) of Unifica @165-5862B828
    public TextField getUnificaField() {
        return Unifica;
    }

    public String getUnifica() {
        return Unifica.getValue();
    }

    public void setUnifica(String value) {
        this.Unifica.setValue(value);
    }
//End DIRITTI_ACCESSORow: method(s) of Unifica

//DIRITTI_ACCESSORow: class tail @146-FCB6E20C
}
//End DIRITTI_ACCESSORow: class tail

