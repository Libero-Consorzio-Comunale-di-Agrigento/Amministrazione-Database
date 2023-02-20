//AD4_REGISTRORow: import @6-4B286E4A
package ad4web.AD4RegistroTree;

import java.util.*;
import com.codecharge.db.*;
//End AD4_REGISTRORow: import

//AD4_REGISTRORow: class head @6-12FA7746
public class AD4_REGISTRORow {
//End AD4_REGISTRORow: class head

//AD4_REGISTRORow: declare fiels @6-BC7F3A42
    private TextField ALBERO = new TextField("ALBERO", "ALBERO");
//End AD4_REGISTRORow: declare fiels

//AD4_REGISTRORow: constructor @6-03CDD5DA
    public AD4_REGISTRORow() {
    }
//End AD4_REGISTRORow: constructor

//AD4_REGISTRORow: method(s) of ALBERO @7-5C6E707F
    public TextField getALBEROField() {
        return ALBERO;
    }

    public String getALBERO() {
        return ALBERO.getValue();
    }

    public void setALBERO(String value) {
        this.ALBERO.setValue(value);
    }
//End AD4_REGISTRORow: method(s) of ALBERO

//AD4_REGISTRORow: class tail @6-FCB6E20C
}
//End AD4_REGISTRORow: class tail

