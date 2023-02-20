//PROGETTIRow: import @62-A88BEE49
package ad4web.AD4ModuElenco;

import java.util.*;
import com.codecharge.db.*;
//End PROGETTIRow: import

//PROGETTIRow: class head @62-FB303601
public class PROGETTIRow {
//End PROGETTIRow: class head

//PROGETTIRow: declare fiels @62-8013DA9A
    private TextField DESC_PROGETTO = new TextField("DESC_PROGETTO", "DESC_PROGETTO");
    private TextField Nuovo = new TextField("Nuovo", "");
    private TextField PROGETTO = new TextField("PROGETTO", "PROGETTO");
//End PROGETTIRow: declare fiels

//PROGETTIRow: constructor @62-45587654
    public PROGETTIRow() {
    }
//End PROGETTIRow: constructor

//PROGETTIRow: method(s) of DESC_PROGETTO @63-BEFF43EA
    public TextField getDESC_PROGETTOField() {
        return DESC_PROGETTO;
    }

    public String getDESC_PROGETTO() {
        return DESC_PROGETTO.getValue();
    }

    public void setDESC_PROGETTO(String value) {
        this.DESC_PROGETTO.setValue(value);
    }
//End PROGETTIRow: method(s) of DESC_PROGETTO

//PROGETTIRow: method(s) of Nuovo @66-42611BD0
    public TextField getNuovoField() {
        return Nuovo;
    }

    public String getNuovo() {
        return Nuovo.getValue();
    }

    public void setNuovo(String value) {
        this.Nuovo.setValue(value);
    }
//End PROGETTIRow: method(s) of Nuovo

//PROGETTIRow: method(s) of PROGETTO @67-7637616D
    public TextField getPROGETTOField() {
        return PROGETTO;
    }

    public String getPROGETTO() {
        return PROGETTO.getValue();
    }

    public void setPROGETTO(String value) {
        this.PROGETTO.setValue(value);
    }
//End PROGETTIRow: method(s) of PROGETTO

//PROGETTIRow: class tail @62-FCB6E20C
}
//End PROGETTIRow: class tail

