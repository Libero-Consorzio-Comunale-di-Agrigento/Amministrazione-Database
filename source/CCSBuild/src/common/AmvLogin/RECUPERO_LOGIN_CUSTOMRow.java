//RECUPERO_LOGIN_CUSTOMRow: import @17-FFA1893A
package common.AmvLogin;

import java.util.*;
import com.codecharge.db.*;
//End RECUPERO_LOGIN_CUSTOMRow: import

//RECUPERO_LOGIN_CUSTOMRow: class head @17-5CD92638
public class RECUPERO_LOGIN_CUSTOMRow {
//End RECUPERO_LOGIN_CUSTOMRow: class head

//RECUPERO_LOGIN_CUSTOMRow: declare fiels @17-DDBB853A
    private TextField LOSTMSGCUSTOM = new TextField("LOSTMSGCUSTOM", "LOSTMSGCUSTOM");
//End RECUPERO_LOGIN_CUSTOMRow: declare fiels

//RECUPERO_LOGIN_CUSTOMRow: constructor @17-FE965BD8
    public RECUPERO_LOGIN_CUSTOMRow() {
    }
//End RECUPERO_LOGIN_CUSTOMRow: constructor

//RECUPERO_LOGIN_CUSTOMRow: method(s) of LOSTMSGCUSTOM @18-EC1F40D7
    public TextField getLOSTMSGCUSTOMField() {
        return LOSTMSGCUSTOM;
    }

    public String getLOSTMSGCUSTOM() {
        return LOSTMSGCUSTOM.getValue();
    }

    public void setLOSTMSGCUSTOM(String value) {
        this.LOSTMSGCUSTOM.setValue(value);
    }
//End RECUPERO_LOGIN_CUSTOMRow: method(s) of LOSTMSGCUSTOM

//RECUPERO_LOGIN_CUSTOMRow: class tail @17-FCB6E20C
}
//End RECUPERO_LOGIN_CUSTOMRow: class tail

