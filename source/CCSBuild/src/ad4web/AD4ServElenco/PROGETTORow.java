//PROGETTORow: import @160-0B984133
package ad4web.AD4ServElenco;

import java.util.*;
import com.codecharge.db.*;
//End PROGETTORow: import

//PROGETTORow: class head @160-7470C3A1
public class PROGETTORow {
//End PROGETTORow: class head

//PROGETTORow: declare fiels @160-270EB9B8
    private TextField DESC_PROGETTO = new TextField("DESC_PROGETTO", "DESC_PROGETTO");
    private TextField AD4_DIRITTI_ACCESSO_Insert = new TextField("AD4_DIRITTI_ACCESSO_Insert", "");
//End PROGETTORow: declare fiels

//PROGETTORow: constructor @160-83377FD3
    public PROGETTORow() {
    }
//End PROGETTORow: constructor

//PROGETTORow: method(s) of DESC_PROGETTO @161-BEFF43EA
    public TextField getDESC_PROGETTOField() {
        return DESC_PROGETTO;
    }

    public String getDESC_PROGETTO() {
        return DESC_PROGETTO.getValue();
    }

    public void setDESC_PROGETTO(String value) {
        this.DESC_PROGETTO.setValue(value);
    }
//End PROGETTORow: method(s) of DESC_PROGETTO

//PROGETTORow: method(s) of AD4_DIRITTI_ACCESSO_Insert @165-CB14FDBA
    public TextField getAD4_DIRITTI_ACCESSO_InsertField() {
        return AD4_DIRITTI_ACCESSO_Insert;
    }

    public String getAD4_DIRITTI_ACCESSO_Insert() {
        return AD4_DIRITTI_ACCESSO_Insert.getValue();
    }

    public void setAD4_DIRITTI_ACCESSO_Insert(String value) {
        this.AD4_DIRITTI_ACCESSO_Insert.setValue(value);
    }
//End PROGETTORow: method(s) of AD4_DIRITTI_ACCESSO_Insert

//PROGETTORow: class tail @160-FCB6E20C
}
//End PROGETTORow: class tail

