//AD4_AMBIENTERow: import @6-0926EC42
package ad4web.AD4Ambiente;

import java.util.*;
import com.codecharge.db.*;
//End AD4_AMBIENTERow: import

//AD4_AMBIENTERow: class head @6-D00562DB
public class AD4_AMBIENTERow {
//End AD4_AMBIENTERow: class head

//AD4_AMBIENTERow: declare fiels @6-E65E7E69
    private TextField AMBIENTE = new TextField("AMBIENTE", "AMBIENTE");
    private TextField EXTERNAL_AUTENTICATION = new TextField("EXTERNAL_AUTENTICATION", "EXTERNAL_AUTENTICATION");
//End AD4_AMBIENTERow: declare fiels

//AD4_AMBIENTERow: constructor @6-ECB1F722
    public AD4_AMBIENTERow() {
    }
//End AD4_AMBIENTERow: constructor

//AD4_AMBIENTERow: method(s) of AMBIENTE @7-F16E6BDE
    public TextField getAMBIENTEField() {
        return AMBIENTE;
    }

    public String getAMBIENTE() {
        return AMBIENTE.getValue();
    }

    public void setAMBIENTE(String value) {
        this.AMBIENTE.setValue(value);
    }
//End AD4_AMBIENTERow: method(s) of AMBIENTE

//AD4_AMBIENTERow: method(s) of EXTERNAL_AUTENTICATION @15-984359D3
    public TextField getEXTERNAL_AUTENTICATIONField() {
        return EXTERNAL_AUTENTICATION;
    }

    public String getEXTERNAL_AUTENTICATION() {
        return EXTERNAL_AUTENTICATION.getValue();
    }

    public void setEXTERNAL_AUTENTICATION(String value) {
        this.EXTERNAL_AUTENTICATION.setValue(value);
    }
//End AD4_AMBIENTERow: method(s) of EXTERNAL_AUTENTICATION

//AD4_AMBIENTERow: class tail @6-FCB6E20C
}
//End AD4_AMBIENTERow: class tail

