//MESSAGGIO_RICHIESTARow: import @7-13A400E6
package common.AmvRegistrazioneFine;

import java.util.*;
import com.codecharge.db.*;
//End MESSAGGIO_RICHIESTARow: import

//MESSAGGIO_RICHIESTARow: class head @7-5FB9C3EB
public class MESSAGGIO_RICHIESTARow {
//End MESSAGGIO_RICHIESTARow: class head

//MESSAGGIO_RICHIESTARow: declare fiels @7-29297AE8
    private TextField MSG = new TextField("MSG", "MSG");
    private TextField MSG_PROCEDURE = new TextField("MSG_PROCEDURE", "");
//End MESSAGGIO_RICHIESTARow: declare fiels

//MESSAGGIO_RICHIESTARow: constructor @7-BA6AEAC5
    public MESSAGGIO_RICHIESTARow() {
    }
//End MESSAGGIO_RICHIESTARow: constructor

//MESSAGGIO_RICHIESTARow: method(s) of MSG @8-A56D10C1
    public TextField getMSGField() {
        return MSG;
    }

    public String getMSG() {
        return MSG.getValue();
    }

    public void setMSG(String value) {
        this.MSG.setValue(value);
    }
//End MESSAGGIO_RICHIESTARow: method(s) of MSG

//MESSAGGIO_RICHIESTARow: method(s) of MSG_PROCEDURE @30-02275677
    public TextField getMSG_PROCEDUREField() {
        return MSG_PROCEDURE;
    }

    public String getMSG_PROCEDURE() {
        return MSG_PROCEDURE.getValue();
    }

    public void setMSG_PROCEDURE(String value) {
        this.MSG_PROCEDURE.setValue(value);
    }
//End MESSAGGIO_RICHIESTARow: method(s) of MSG_PROCEDURE

//MESSAGGIO_RICHIESTARow: class tail @7-FCB6E20C
}
//End MESSAGGIO_RICHIESTARow: class tail

