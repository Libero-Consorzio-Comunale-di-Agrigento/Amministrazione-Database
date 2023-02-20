//AD4_ACCESSIRow: import @6-5FC6A384
package ad4web.AD4AccessiTree;

import java.util.*;
import com.codecharge.db.*;
//End AD4_ACCESSIRow: import

//AD4_ACCESSIRow: class head @6-7DE54B12
public class AD4_ACCESSIRow {
//End AD4_ACCESSIRow: class head

//AD4_ACCESSIRow: declare fiels @6-BC7F3A42
    private TextField ALBERO = new TextField("ALBERO", "ALBERO");
//End AD4_ACCESSIRow: declare fiels

//AD4_ACCESSIRow: constructor @6-9FF853A1
    public AD4_ACCESSIRow() {
    }
//End AD4_ACCESSIRow: constructor

//AD4_ACCESSIRow: method(s) of ALBERO @7-5C6E707F
    public TextField getALBEROField() {
        return ALBERO;
    }

    public String getALBERO() {
        return ALBERO.getValue();
    }

    public void setALBERO(String value) {
        this.ALBERO.setValue(value);
    }
//End AD4_ACCESSIRow: method(s) of ALBERO

//AD4_ACCESSIRow: class tail @6-FCB6E20C
}
//End AD4_ACCESSIRow: class tail

