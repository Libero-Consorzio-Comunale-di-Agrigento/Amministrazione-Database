//AD4_GRUPPIRow: import @6-4B4528B8
package ad4web.AD4GruppiTreeInclusa;

import java.util.*;
import com.codecharge.db.*;
//End AD4_GRUPPIRow: import

//AD4_GRUPPIRow: class head @6-58EAF1C0
public class AD4_GRUPPIRow {
//End AD4_GRUPPIRow: class head

//AD4_GRUPPIRow: declare fiels @6-BC7F3A42
    private TextField ALBERO = new TextField("ALBERO", "ALBERO");
//End AD4_GRUPPIRow: declare fiels

//AD4_GRUPPIRow: constructor @6-5258B075
    public AD4_GRUPPIRow() {
    }
//End AD4_GRUPPIRow: constructor

//AD4_GRUPPIRow: method(s) of ALBERO @7-5C6E707F
    public TextField getALBEROField() {
        return ALBERO;
    }

    public String getALBERO() {
        return ALBERO.getValue();
    }

    public void setALBERO(String value) {
        this.ALBERO.setValue(value);
    }
//End AD4_GRUPPIRow: method(s) of ALBERO

//AD4_GRUPPIRow: class tail @6-FCB6E20C
}
//End AD4_GRUPPIRow: class tail

