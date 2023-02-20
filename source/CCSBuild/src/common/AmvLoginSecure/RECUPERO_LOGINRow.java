//RECUPERO_LOGINRow: import @12-1B738CF9
package common.AmvLoginSecure;

import java.util.*;
import com.codecharge.db.*;
//End RECUPERO_LOGINRow: import

//RECUPERO_LOGINRow: class head @12-D54769EB
public class RECUPERO_LOGINRow {
//End RECUPERO_LOGINRow: class head

//RECUPERO_LOGINRow: declare fiels @12-592A7B6A
    private TextField LOSTMSG = new TextField("LOSTMSG", "LOSTMSG");
//End RECUPERO_LOGINRow: declare fiels

//RECUPERO_LOGINRow: constructor @12-479F278E
    public RECUPERO_LOGINRow() {
    }
//End RECUPERO_LOGINRow: constructor

//RECUPERO_LOGINRow: method(s) of LOSTMSG @13-F4C2C624
    public TextField getLOSTMSGField() {
        return LOSTMSG;
    }

    public String getLOSTMSG() {
        return LOSTMSG.getValue();
    }

    public void setLOSTMSG(String value) {
        this.LOSTMSG.setValue(value);
    }
//End RECUPERO_LOGINRow: method(s) of LOSTMSG

//RECUPERO_LOGINRow: class tail @12-FCB6E20C
}
//End RECUPERO_LOGINRow: class tail

