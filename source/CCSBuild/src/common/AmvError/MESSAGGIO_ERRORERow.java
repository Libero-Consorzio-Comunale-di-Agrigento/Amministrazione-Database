//MESSAGGIO_ERRORERow: import @5-9084D9E8
package common.AmvError;

import java.util.*;
import com.codecharge.db.*;
//End MESSAGGIO_ERRORERow: import

//MESSAGGIO_ERRORERow: class head @5-920A4DBD
public class MESSAGGIO_ERRORERow {
//End MESSAGGIO_ERRORERow: class head

//MESSAGGIO_ERRORERow: declare fiels @5-BCCBB2E5
    private TextField MSG = new TextField("MSG", "MSG");
    private TextField CUSTOM_MSG = new TextField("CUSTOM_MSG", "CUSTOM_MSG");
//End MESSAGGIO_ERRORERow: declare fiels

//MESSAGGIO_ERRORERow: constructor @5-2A03B133
    public MESSAGGIO_ERRORERow() {
    }
//End MESSAGGIO_ERRORERow: constructor

//MESSAGGIO_ERRORERow: method(s) of MSG @6-A56D10C1
    public TextField getMSGField() {
        return MSG;
    }

    public String getMSG() {
        return MSG.getValue();
    }

    public void setMSG(String value) {
        this.MSG.setValue(value);
    }
//End MESSAGGIO_ERRORERow: method(s) of MSG

//MESSAGGIO_ERRORERow: method(s) of CUSTOM_MSG @17-F04F81AC
    public TextField getCUSTOM_MSGField() {
        return CUSTOM_MSG;
    }

    public String getCUSTOM_MSG() {
        return CUSTOM_MSG.getValue();
    }

    public void setCUSTOM_MSG(String value) {
        this.CUSTOM_MSG.setValue(value);
    }
//End MESSAGGIO_ERRORERow: method(s) of CUSTOM_MSG

//MESSAGGIO_ERRORERow: class tail @5-FCB6E20C
}
//End MESSAGGIO_ERRORERow: class tail

