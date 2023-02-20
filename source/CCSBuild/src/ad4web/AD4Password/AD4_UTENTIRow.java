//AD4_UTENTIRow: import @2-184C3A5A
package ad4web.AD4Password;

import java.util.*;
import com.codecharge.db.*;
//End AD4_UTENTIRow: import

//AD4_UTENTIRow: class head @2-D8604D31
public class AD4_UTENTIRow {
//End AD4_UTENTIRow: class head

//AD4_UTENTIRow: declare fiels @2-09C40D67
    private TextField NUOVA_PASSWORD = new TextField("NUOVA_PASSWORD", "");
    private TextField CONFERMA_PASSWORD = new TextField("CONFERMA_PASSWORD", "");
//End AD4_UTENTIRow: declare fiels

//AD4_UTENTIRow: constructor @2-A3432298
    public AD4_UTENTIRow() {
    }
//End AD4_UTENTIRow: constructor

//AD4_UTENTIRow: method(s) of NUOVA_PASSWORD @4-B45767BC
    public TextField getNUOVA_PASSWORDField() {
        return NUOVA_PASSWORD;
    }

    public String getNUOVA_PASSWORD() {
        return NUOVA_PASSWORD.getValue();
    }

    public void setNUOVA_PASSWORD(String value) {
        this.NUOVA_PASSWORD.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of NUOVA_PASSWORD

//AD4_UTENTIRow: method(s) of CONFERMA_PASSWORD @5-31D97D57
    public TextField getCONFERMA_PASSWORDField() {
        return CONFERMA_PASSWORD;
    }

    public String getCONFERMA_PASSWORD() {
        return CONFERMA_PASSWORD.getValue();
    }

    public void setCONFERMA_PASSWORD(String value) {
        this.CONFERMA_PASSWORD.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of CONFERMA_PASSWORD

//AD4_UTENTIRow: class tail @2-FCB6E20C
}
//End AD4_UTENTIRow: class tail

